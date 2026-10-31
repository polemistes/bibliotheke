# SPDX-License-Identifier: GPL-3.0-or-later
"""WP1 acceptance checks (PLAN.md section 9, WP1, with the figures amended
on 2026-11-01) against the built catalogs in pipeline/out/.

    python -I -m wscatalog.acceptance no de en
"""

from __future__ import annotations

import gzip
import json
import sqlite3
import sys
from pathlib import Path

from .catalog import OUT_DIR

REASONS = {"disambiguation", "versions", "author-page", "whole-text", "excluded-template",
           "excluded-category", "no-header", "under-excluded", "no-text-pages", "index-faulty",
           "junk-template", "placeholder", "under-300-bytes", "bibliography", "no-text", "duplicate"}


def one(con, sql, *args):
    row = con.execute(sql, args).fetchone()
    return row[0] if row and len(row) == 1 else row


def work(con, title):
    con.row_factory = sqlite3.Row
    r = con.execute("SELECT * FROM works WHERE page_title = ?", (title,)).fetchone()
    con.row_factory = None
    return r


def table_gz(con, tables: list[str]) -> int:
    """Gzipped size of an SQLite file holding only these tables."""
    mem = sqlite3.connect(":memory:")
    src = con.execute("PRAGMA database_list").fetchall()[0][2]
    mem.execute("ATTACH DATABASE ? AS src", (src,))
    for t in tables:
        mem.execute(f"CREATE TABLE {t} AS SELECT * FROM src.{t}")
    mem.commit()
    mem.execute("DETACH DATABASE src")
    size = len(gzip.compress(mem.serialize(), 9))
    mem.close()
    return size


# ---- checks: (description, function(con) -> (ok, detail)) -----------------
def share_with(con, sql_works: str) -> tuple[int, int]:
    n = one(con, "SELECT count(*) FROM works")
    k = one(con, sql_works)
    return k, n


def chk_author(con):
    k, n = share_with(con, "SELECT count(DISTINCT work_id) FROM work_authors WHERE role = 'author'")
    dv = one(con, "SELECT count(*) FROM works WHERE quality IN ('validated','proofread') AND complete = 1 "
                  "AND type != 'reference'")
    dva = one(con, "SELECT count(DISTINCT w.id) FROM works w JOIN work_authors a ON a.work_id = w.id "
                   "AND a.role = 'author' WHERE quality IN ('validated','proofread') AND complete = 1 "
                   "AND type != 'reference'")
    return k >= 0.9 * n, f"{k / n:.1%} of {n:,} works (default view: {dva / dv:.1%} of {dv:,})"


def chk_year(con):
    k, n = share_with(con, "SELECT count(*) FROM works WHERE year IS NOT NULL")
    return k >= 0.6 * n, f"{k / n:.1%} of {n:,} works"


def chk_ibsen(con):
    hits = con.execute("SELECT w.page_title FROM works_fts f JOIN works w ON w.id = f.rowid "
                       "WHERE works_fts MATCH 'Ibsen'").fetchall()
    by = con.execute("SELECT DISTINCT w.page_title FROM works w JOIN work_authors wa ON wa.work_id = w.id "
                     "JOIN authors a ON a.id = wa.author_id WHERE a.name LIKE '%Ibsen%' "
                     "AND wa.role = 'author'").fetchall()
    found = {h[0] for h in hits}
    return bool(by) and {b[0] for b in by} <= found, \
        f"{len(found)} hits, {len(by)} works by an Ibsen, all found: {set(b[0] for b in by) <= found}"


def chk_excluded(con):
    rows = con.execute("SELECT page_title, reason FROM excluded").fetchall()
    bad = [r for r in rows if not r[1] or (r[1] not in REASONS and not r[1].startswith("reference-prefix"))]
    return bool(rows) and not bad, f"{len(rows):,} rows, {len(bad)} without a known reason"


def chk_work(title, **want):
    def f(con):
        w = work(con, title)
        if w is None:
            ex = one(con, "SELECT reason FROM excluded WHERE page_title = ?", title)
            return False, f"not a work" + (f" (excluded: {ex})" if ex else "")
        got = {k: w[k] for k in want}
        ok = all((v(got[k]) if callable(v) else got[k] == v) for k, v in want.items())
        return ok, ", ".join(f"{k} {got[k]}" for k in want)
    f.__doc__ = title
    return f


def chk_not_work(title):
    def f(con):
        ex = one(con, "SELECT reason FROM excluded WHERE page_title = ?", title)
        part = one(con, "SELECT w.page_title FROM parts p JOIN works w ON w.id = p.work_id "
                        "WHERE p.page_title = ?", title)
        w = work(con, title)
        return w is None, (f"excluded ({ex})" if ex else f"part of {part}" if part else
                           "not in the catalog" if w is None else "is a work")
    return f


def chk_part_of(title, root):
    def f(con):
        hit = one(con, "SELECT w.page_title FROM parts p JOIN works w ON w.id = p.work_id "
                       "WHERE p.page_title = ?", title)
        return hit == root and work(con, title) is None, f"part of {hit}"
    return f


def chk_text_found(query, root=None, title_contains=None):
    def f(con):
        rows = con.execute(
            "SELECT w.page_title, p.page_title FROM parts_fts f JOIN works w ON w.id = f.rowid >> 12 "
            "JOIN parts p ON p.work_id = w.id AND p.ord = (f.rowid & 4095) "
            "WHERE parts_fts MATCH ?", (query,)).fetchall()
        ok = any((root is None or r[0] == root) and (title_contains is None or title_contains in r[1])
                 for r in rows)
        return ok, f"{len(rows)} text hits: " + "; ".join(f"{p} in {w}" for w, p in rows[:4])
    return f


def chk_virtual_volumes(title, n):
    def f(con):
        w = work(con, title)
        if w is None:
            return False, "not a work"
        vols = one(con, "SELECT count(*) FROM parts WHERE work_id = ? AND depth = 0 AND kind = 'volume'", w["id"])
        return w["is_virtual"] == 1 and vols == n, f"is_virtual {w['is_virtual']}, {vols} volumes"
    return f


def chk_no_default_view(con):
    rows = con.execute("SELECT text_count, quality_pages FROM works WHERE quality IN ('validated','proofread') "
                       "AND complete = 1 AND type != 'reference'").fetchall()
    texts = sum(r[0] for r in rows)
    ocr = 0
    for _, qp in rows:
        c = json.loads(qp) if qp else {}
        tp = sum(c.get(k, 0) for k in "1234")
        if tp and (c.get("1", 0) + c.get("2", 0)) > 0.1 * tp:
            ocr += 1
    ok = abs(len(rows) - 470) <= 25 and abs(texts - 2400) <= 240 and ocr == 0
    return ok, f"{len(rows):,} works, {texts:,} texts, {ocr} with more than 10 % unproofread pages"


def chk_en_budget(con):
    parts = table_gz(con, ["parts"])
    fts = table_gz(con, [t for t in ("parts_fts_data", "parts_fts_idx", "parts_fts_docsize", "parts_fts_config")])
    rows = one(con, "SELECT count(*) FROM parts")
    return parts <= 20e6 and fts <= 3.5e6, \
        f"parts {rows:,} rows, {parts / 1e6:.1f} MB gzipped; parts_fts {fts / 1e6:.1f} MB gzipped"


CHECKS = {
    "no": [
        ("≥ 90 % of works have an author", chk_author),
        ("≥ 60 % of works have a year", chk_year),
        ("FTS query \"Ibsen\" returns Ibsen's works", chk_ibsen),
        ("`excluded` explains every exclusion", chk_excluded),
        ("`Diktsamling`: 4 parts, 116 missing, incomplete",
         chk_work("Diktsamling", part_count=4, missing_part_count=116, complete=0)),
        ("`Ved Rundarne (1864)` found as a text of `Diktsamling`",
         chk_text_found('"Ved Rundarne"', root="Diktsamling")),
        ("default view about 470 works and 2,400 texts, none raw OCR", chk_no_default_view),
    ],
    "de": [
        ("`excluded` explains every exclusion", chk_excluded),
        ("`Der Sagenschatz des Königreichs Sachsen`: one work, 1,001 parts",
         chk_work("Der Sagenschatz des Königreichs Sachsen", part_count=1001)),
        ("`Die Heimath in der neuen Welt`: virtual root, three volumes",
         chk_virtual_volumes("Die Heimath in der neuen Welt", 3)),
        ("`Käthe` is oversized", chk_work("Käthe", oversized=1)),
        ("`Kinder und Hausmärchen` (editions overview) is not a work", chk_not_work("Kinder und Hausmärchen")),
        ("`Kinder- und Haus-Märchen Band 1 (1857)` is a work",
         chk_work("Kinder- und Haus-Märchen Band 1 (1857)", structure=lambda v: v != "")),
        ("`Topographia Hassiae`: one work of about 200 texts",
         chk_work("Topographia Hassiae", text_count=lambda v: 150 <= v <= 250)),
        ("`Allgemeines Deutsches Kommersbuch:47` is a part, not a work",
         chk_part_of("Allgemeines Deutsches Kommersbuch:47", "Allgemeines Deutsches Kommersbuch")),
    ],
    "en": [
        ("`excluded` explains every exclusion", chk_excluded),
        ("`Ozymandias` finds the Hutchinson text", chk_text_found("Ozymandias", title_contains="Hutchinson")),
        ("*Popular Science Monthly* is a collection",
         chk_work("Popular Science Monthly", type=lambda v: v in ("collection", "periodical-issue"))),
        ("parts table within the budget (about 18 MB + 3 MB parts_fts gzipped)", chk_en_budget),
    ],
}


def run(wikis: list[str], out_dir: Path = OUT_DIR) -> list[tuple[str, str, bool, str]]:
    results = []
    for wiki in wikis:
        path = out_dir / f"{wiki}.sqlite"
        if not path.exists():
            results.append((wiki, "catalog built", False, f"{path} missing"))
            continue
        con = sqlite3.connect(f"file:{path}?mode=ro", uri=True)
        for desc, fn in CHECKS.get(wiki, [("`excluded` explains every exclusion", chk_excluded)]):
            try:
                ok, detail = fn(con)
            except Exception as exc:          # a broken check is a failed check
                ok, detail = False, f"error: {exc}"
            results.append((wiki, desc, ok, detail))
        con.close()
    return results


def main(argv: list[str]) -> int:
    res = run(argv or ["no", "de", "en"])
    for wiki, desc, ok, detail in res:
        print(f"{'✓' if ok else '✗'} {wiki}: {desc}: {detail}")
    return 0 if all(r[2] for r in res) else 1


if __name__ == "__main__":
    sys.exit(main(sys.argv[1:]))
