# SPDX-License-Identifier: GPL-3.0-or-later
"""Catalog build (PLAN.md 5.2 steps 3-8): metadata merge, the per-wiki
SQLite catalog (schema version 2, 5.3), gzip and sha256, the coverage report
report/{wiki}.md and the wiki's manifest entry.

    python -I -m wscatalog.catalog no is        # pipeline/out/{wiki}.sqlite.gz, report/{wiki}.md
    python -I -m wscatalog.catalog --manifest   # pipeline/out/manifest.json from the entries

Runs on the cached extract (extract.py); Wikidata items are fetched and
cached by wikidata.py (--offline uses the cache only).
"""

from __future__ import annotations

import argparse
import gzip
import hashlib
import json
import shutil
import sqlite3
import sys
import time
import zlib
from collections import Counter
from pathlib import Path

from . import APP_VERSION, extract, quality, sites, structure
from .http import PIPELINE_DIR
from .metadata import GENRES, Merger, Meta
from .wikidata import LANGS, Wikidata

SCHEMA_VERSION = 2
OUT_DIR = PIPELINE_DIR / "out"
REPORT_DIR = PIPELINE_DIR.parent / "report"
# parts_fts is contentless; its rowid addresses the part (parts has a
# composite key): rowid = work_id * 4096 + ord, so work_id = rowid >> 12 and
# ord = rowid & 4095 (ord stays below 4096 because parts_cap is 2,000).
PARTS_ROWID_STRIDE = 4096
DEFAULT_TIERS = ("validated", "proofread")
HUMAN = "Q5"

SCHEMA = """
CREATE TABLE meta(key TEXT PRIMARY KEY, value TEXT);
CREATE TABLE works(
  id INTEGER PRIMARY KEY,
  page_title TEXT NOT NULL UNIQUE,
  display_title TEXT NOT NULL,
  sort_title TEXT NOT NULL,
  qid TEXT,
  work_qid TEXT,
  year INTEGER, year_precision INTEGER, year_source TEXT,
  language TEXT,
  type TEXT,
  structure TEXT NOT NULL,
  is_virtual INTEGER NOT NULL DEFAULT 0,
  entry_title TEXT,
  part_count INTEGER NOT NULL,
  text_count INTEGER NOT NULL,
  missing_part_count INTEGER NOT NULL DEFAULT 0,
  parts_in_catalog INTEGER NOT NULL DEFAULT 1,
  oversized INTEGER NOT NULL DEFAULT 0,
  bytes_total INTEGER,
  index_titles TEXT,
  quality TEXT NOT NULL,
  quality_source TEXT,
  quality_pages TEXT,
  complete INTEGER NOT NULL,
  incomplete_reason TEXT,
  missing_page_count INTEGER NOT NULL DEFAULT 0,
  coverage REAL,
  own_translation INTEGER NOT NULL DEFAULT 0,
  description TEXT,
  cover_url TEXT,
  rev_id INTEGER, touched TEXT,
  author_source TEXT, language_source TEXT, genre_source TEXT, type_source TEXT
);
CREATE TABLE parts(
  work_id INTEGER NOT NULL, ord INTEGER NOT NULL,
  depth INTEGER NOT NULL, parent_ord INTEGER,
  page_title TEXT NOT NULL,
  display_title TEXT,
  kind TEXT NOT NULL,
  searchable INTEGER NOT NULL DEFAULT 0,
  bytes INTEGER, scan_from INTEGER, scan_to INTEGER,
  index_ref INTEGER,
  quality TEXT, quality_pages TEXT, complete INTEGER,
  PRIMARY KEY(work_id, ord));
CREATE TABLE aliases(alias_title TEXT PRIMARY KEY, work_id INTEGER NOT NULL, ord INTEGER);
CREATE VIRTUAL TABLE parts_fts USING fts5(display_title, work_title, author_names, content='',
  tokenize='unicode61 remove_diacritics 2');
CREATE TABLE authors(id INTEGER PRIMARY KEY, name TEXT NOT NULL, sort_name TEXT NOT NULL,
  qid TEXT, birth_year INTEGER, death_year INTEGER, author_page_title TEXT);
CREATE TABLE work_authors(work_id INTEGER, author_id INTEGER, role TEXT, position INTEGER,
  PRIMARY KEY(work_id, author_id, role));
CREATE TABLE genres(id INTEGER PRIMARY KEY, key TEXT UNIQUE);
CREATE TABLE work_genres(work_id INTEGER, genre_id INTEGER, PRIMARY KEY(work_id, genre_id));
CREATE VIRTUAL TABLE works_fts USING fts5(display_title, author_names, content='',
  tokenize='unicode61 remove_diacritics 2');
CREATE TABLE excluded(page_title TEXT PRIMARY KEY, reason TEXT);
"""
INDEXES = """
CREATE INDEX parts_title ON parts(page_title);
CREATE INDEX works_year ON works(year);
CREATE INDEX works_type ON works(type);
CREATE INDEX works_quality ON works(quality);
CREATE INDEX works_sort_title ON works(sort_title);
CREATE INDEX work_genres_genre ON work_genres(genre_id);
CREATE INDEX work_authors_author ON work_authors(author_id);
"""


def pages_json(c: Counter) -> str | None:
    return json.dumps({str(k): v for k, v in sorted(c.items(), key=lambda kv: str(kv[0]))},
                      separators=(",", ":")) if c else None


class Build:
    def __init__(self, wiki: str, offline: bool = False, ext: dict | None = None,
                 wd: Wikidata | None = None):
        t0 = time.time()
        self.wiki = wiki
        self.ext = ext if ext is not None else extract.load(wiki)
        self.res = structure.resolve(wiki, self.ext)
        self.cfg = self.res.cfg
        self.ass = quality.Assessor(self.res)
        self.q = {w.root.title: self.ass.assess(w) for w in self.res.works}
        self.works = [w for w in self.res.works if not self.q[w.root.title][0].excluded]
        self.wd = wd or Wikidata(langs=LANGS + (self.cfg.language,), sites={self.cfg.dbname},
                                 offline=offline)
        self.merger = Merger(self.res, self.wd)
        self.merger.prefetch(self.works)
        # PLAN.md 3.2: on wikis without an Author namespace (de, fi) author
        # pages are ns0 pages; a work whose Wikidata item is a human is one.
        for w in self.works:
            if HUMAN in self.wd.claims(self.merger.edition_qid(w), "P31"):
                self.q[w.root.title][0].excluded = "author-page"
        self.works = [w for w in self.works if not self.q[w.root.title][0].excluded]
        self.meta: dict[str, Meta] = {w.root.title: self.merger.merge(w, self.q[w.root.title][0])
                                      for w in self.works}
        self.ids = self.work_ids()
        self.elapsed = time.time() - t0
        self.dump_date = next((p.split("-")[1] for p in self.ext.get("dump", ()) if "-" in p), "")

    def work_ids(self) -> dict[str, int]:
        """works.id: the root's page id; a virtual root (no page) gets a
        negative id from its title, stable while the title is."""
        ids, used = {}, set()
        for w in self.works:
            if w.root.rec is not None:
                ids[w.root.title] = w.root.rec.page_id
                used.add(w.root.rec.page_id)
        for w in sorted((w for w in self.works if w.root.rec is None), key=lambda w: w.root.title):
            i = -(zlib.crc32(w.root.title.encode()) % 10**9) - 1
            while i in used:
                i -= 1
            ids[w.root.title] = i
            used.add(i)
        return ids

    def parts_in_catalog(self, w) -> bool:
        return w.structure != "single" and w.part_count <= self.cfg.parts_cap and self.meta[w.root.title].type != "reference"

    def default_view(self, unknown: bool) -> list:
        tiers = DEFAULT_TIERS + (("unknown",) if unknown else ())
        return [w for w in self.works if self.q[w.root.title][0].tier in tiers
                and self.q[w.root.title][0].complete and self.meta[w.root.title].type != "reference"]

    # ---- step 6 ------------------------------------------------------------
    def write(self, out_dir: Path = OUT_DIR) -> dict:
        out_dir.mkdir(parents=True, exist_ok=True)
        path = out_dir / f"{self.wiki}.sqlite"
        tmp = path.with_suffix(".sqlite.tmp")
        tmp.unlink(missing_ok=True)
        con = sqlite3.connect(tmp)
        con.executescript("PRAGMA journal_mode=OFF; PRAGMA synchronous=OFF;" + SCHEMA)
        self.counts = Counter()
        self.write_meta(con)
        self.write_works(con)
        self.write_aliases(con)
        self.write_excluded(con)
        con.executescript(INDEXES)
        con.execute("INSERT INTO works_fts(works_fts) VALUES ('optimize')")
        con.execute("INSERT INTO parts_fts(parts_fts) VALUES ('optimize')")
        con.commit()
        con.execute("VACUUM")
        con.close()
        tmp.replace(path)
        gz = out_dir / f"{self.wiki}.sqlite.gz"
        with path.open("rb") as f, gzip.open(gz, "wb", compresslevel=9) as g:
            shutil.copyfileobj(f, g, 1 << 20)
        sha = hashlib.sha256(gz.read_bytes()).hexdigest()
        self.files = dict(sqlite=path.stat().st_size, gz=gz.stat().st_size, sha256=sha)
        return self.files

    def write_meta(self, con) -> None:
        site_ns = self.ext.get("site_ns") or {}
        ns = {k: site_ns.get(v) for k, v in self.cfg.ns.items() if v is not None}
        meta = dict(wiki=self.wiki, domain=self.cfg.domain, dbname=self.cfg.dbname,
                    language=self.cfg.language, schema_version=SCHEMA_VERSION,
                    generated_at=time.strftime("%Y-%m-%dT%H:%M:%SZ", time.gmtime()),
                    dump_date=self.dump_date, pipeline_version=APP_VERSION,
                    namespaces=json.dumps(ns, ensure_ascii=False),
                    show_unknown_by_default=json.dumps(bool(self.cfg.show_unknown_by_default)),
                    parts_rowid_stride=PARTS_ROWID_STRIDE)
        con.executemany("INSERT INTO meta VALUES (?,?)", [(k, str(v)) for k, v in meta.items()])

    def write_works(self, con) -> None:
        res, cfg = self.res, self.cfg
        con.executemany("INSERT INTO genres VALUES (?,?)", list(enumerate(GENRES, 1)))
        genre_id = {g: i for i, g in enumerate(GENRES, 1)}
        authors: dict[str, int] = {}
        for w in self.works:
            title = w.root.title
            wid, m = self.ids[title], self.meta[title]
            q, part_q = self.q[title]
            in_cat = self.parts_in_catalog(w)
            idxs = list(dict.fromkeys(i for p in w.parts for i in p.node.indexes))
            con.execute("INSERT INTO works VALUES (" + ",".join("?" * 37) + ")", (
                wid, title, m.display_title, m.sort_title, m.qid, m.work_qid,
                m.year, m.year_precision, m.year_source, m.language, m.type,
                w.structure, int(w.is_virtual), w.entry_title if w.is_virtual else None,
                w.part_count, w.text_count, len(w.missing), int(in_cat), int(w.oversized),
                w.bytes_total, json.dumps(idxs, ensure_ascii=False) if idxs else None,
                q.tier, q.source, pages_json(q.pages), int(q.complete), q.reason,
                q.missing_pages, round(q.coverage, 4) if q.coverage is not None else None,
                int(q.own_translation), m.description, m.cover_url,
                w.root.rec.rev if w.root.rec else None, w.root.rec.ts if w.root.rec else None,
                m.author_source, m.language_source, m.genre_source, m.type_source))
            names = []
            for role, people in (("author", m.authors), ("translator", m.translators)):
                for pos, p in enumerate(people):
                    k = p.key()
                    if k not in authors:
                        authors[k] = len(authors) + 1
                        con.execute("INSERT INTO authors VALUES (?,?,?,?,?,?,?)",
                                    (authors[k], p.name, p.sort, p.qid, p.birth, p.death, p.page))
                    con.execute("INSERT OR IGNORE INTO work_authors VALUES (?,?,?,?)",
                                (wid, authors[k], role, pos))
                    if role == "author":
                        names.append(p.name)
            author_names = " ".join(names)
            con.executemany("INSERT OR IGNORE INTO work_genres VALUES (?,?)",
                            [(wid, genre_id[g]) for g in m.genres if g in genre_id])
            con.execute("INSERT INTO works_fts(rowid, display_title, author_names) VALUES (?,?,?)",
                        (wid, m.display_title, author_names))
            if not in_cat:
                continue
            rows, fts = [], []
            for p, pq in zip(w.parts, part_q):
                n = p.node
                display = n.display or n.title
                stored = None if display in (n.title, n.title.rsplit("/", 1)[-1]) else display
                rows.append((wid, p.ord, p.depth, p.parent_ord, n.title, stored, p.kind,
                             int(p.searchable), res.node_bytes(n), n.scan_from, n.scan_to,
                             idxs.index(n.index) if n.index in idxs else None,
                             pq.tier, pages_json(pq.pages), int(pq.complete)))
                if p.searchable:
                    fts.append((wid * PARTS_ROWID_STRIDE + p.ord, display, m.display_title, author_names))
            con.executemany("INSERT INTO parts VALUES (" + ",".join("?" * 15) + ")", rows)
            con.executemany("INSERT INTO parts_fts(rowid, display_title, work_title, author_names) "
                            "VALUES (?,?,?,?)", fts)
            self.counts["parts"] += len(rows)
            self.counts["parts_fts"] += len(fts)
        self.counts["authors"] = len(authors)

    def write_aliases(self, con) -> None:
        """Redirects to a work's root page or to a part in the catalog."""
        where: dict[str, tuple[int, int | None]] = {}
        for w in self.works:
            wid = self.ids[w.root.title]
            where[w.root.title] = (wid, None)
            if self.parts_in_catalog(w):
                for p in w.parts:
                    if p.node.rec is not None and p.node.title != w.root.title:
                        where.setdefault(p.node.title, (wid, p.ord))
        rows = []
        for src in self.ext["redirects"]:
            t = self.res.resolve(src)
            if t in where and src not in where:
                rows.append((src, *where[t]))
        for src, t in self.res.duplicate_of.items():     # duplicates found by their title
            if t in where and src not in where:
                rows.append((src, *where[t]))
        con.executemany("INSERT OR IGNORE INTO aliases VALUES (?,?,?)", rows)
        self.counts["aliases"] = len(rows)

    def write_excluded(self, con) -> None:
        """Pages excluded in step 1 and works excluded in step 5, with the
        reason. Implementation choice: pages excluded by a title prefix
        (de lexicon entries, 200,000+) are one row per prefix, `RE:*`."""
        prefixes = Counter()
        rows = []
        for t, reason in self.res.excluded.items():
            if reason == "reference-prefix":
                prefixes[next(p for p in self.cfg.exclude_title_prefixes if t.startswith(p))] += 1
            else:
                rows.append((t, reason))
        rows += [(f"{p}*", f"reference-prefix ({n} pages)") for p, n in prefixes.items()]
        rows += [(w.root.title, self.q[w.root.title][0].excluded) for w in self.res.works
                 if self.q[w.root.title][0].excluded]
        con.executemany("INSERT OR REPLACE INTO excluded VALUES (?,?)", rows)
        self.counts["excluded"] = len(rows)

    # ---- step 8 ------------------------------------------------------------
    def manifest_entry(self) -> dict:
        s = sites.site(self.wiki) or {}
        tiers = Counter(self.q[w.root.title][0].tier for w in self.works)
        unknown = bool(self.cfg.show_unknown_by_default)
        return dict(code=self.wiki, dbname=self.cfg.dbname, domain=self.cfg.domain,
                    language=self.cfg.language, name=s.get("autonym") or self.wiki,
                    url=f"{self.wiki}.sqlite.gz", sha256=self.files["sha256"], size=self.files["gz"],
                    size_uncompressed=self.files["sqlite"], work_count=len(self.works),
                    generated_at=time.strftime("%Y-%m-%dT%H:%M:%SZ", time.gmtime()),
                    dump_date=self.dump_date, schema_version=SCHEMA_VERSION,
                    show_unknown_by_default=unknown,
                    tiers={t: tiers.get(t, 0) for t in ("validated", "proofread", "unproofread", "unknown")},
                    default_view_count=len(self.default_view(unknown)))


def write_manifest(out_dir: Path = OUT_DIR, previous: Path | None = None) -> Path:
    """manifest.json from every pipeline/out/{wiki}.json entry (PLAN.md 5.2
    step 8, 6.1); `url` is relative to the manifest's own URL. With
    previous (the published manifest), wikis not rebuilt in this run keep
    their entry, so one failed wiki does not drop out of the catalog list."""
    entries = {e["code"]: e for e in (json.loads(p.read_text("utf-8"))
                                      for p in sorted(out_dir.glob("*.json")) if p.name != "manifest.json")}
    if previous is not None and previous.exists():
        for e in json.loads(previous.read_text("utf-8")).get("wikis", ()):
            entries.setdefault(e["code"], e)
    entries = sorted(entries.values(), key=lambda e: e["code"])
    data = dict(schema_version=SCHEMA_VERSION,
                generated_at=time.strftime("%Y-%m-%dT%H:%M:%SZ", time.gmtime()), wikis=entries)
    path = out_dir / "manifest.json"
    path.write_text(json.dumps(data, ensure_ascii=False, indent=1) + "\n", "utf-8")
    return path


def build(wiki: str, offline: bool = False, out_dir: Path = OUT_DIR) -> Build:
    from .report import write_report
    b = Build(wiki, offline=offline)
    b.write(out_dir)
    entry = b.manifest_entry()
    (out_dir / f"{wiki}.json").write_text(json.dumps(entry, ensure_ascii=False, indent=1) + "\n", "utf-8")
    path = write_report(b)
    print(f"{wiki}: {len(b.works):,} works, {b.counts['parts']:,} parts, "
          f"{b.files['gz'] / 1e6:.2f} MB gzipped ({b.files['sqlite'] / 1e6:.1f} MB raw), "
          f"default view {entry['default_view_count']:,}; {b.wd.fetched:,} Wikidata items fetched; "
          f"{b.elapsed:.0f} s; report {path}", file=sys.stderr)
    return b


def main(argv: list[str]) -> None:
    ap = argparse.ArgumentParser(prog="wscatalog.catalog")
    ap.add_argument("wikis", nargs="*")
    ap.add_argument("--offline", action="store_true", help="Wikidata from the cache only")
    ap.add_argument("--manifest", action="store_true", help="write out/manifest.json")
    ap.add_argument("--previous", type=Path, help="published manifest: keep entries of wikis not built")
    ap.add_argument("--out", type=Path, default=OUT_DIR)
    args = ap.parse_args(argv)
    for w in args.wikis:
        build(w, offline=args.offline, out_dir=args.out)
    if args.manifest:
        print(write_manifest(args.out, previous=args.previous), file=sys.stderr)


if __name__ == "__main__":
    main(sys.argv[1:])
