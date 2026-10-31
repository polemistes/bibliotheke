# SPDX-License-Identifier: GPL-3.0-or-later
"""Evidence for the STATUS.md owner decisions 1-8 (2026-11-01).

    .venv/bin/python -I analyze.py <wiki>   -> markdown on stdout

Reuses wscatalog.survey.Survey (resolution + quality on the cached extract)
and adds the measurements the decisions need. Read-only; writes nothing
into the repository.
"""
from __future__ import annotations

import gzip
import json
import random
import re
import sqlite3
import sys
import time
from collections import Counter, defaultdict

from wscatalog import quality, survey, structure
from wscatalog.structure import parent_titles

REF_RE = re.compile(r"dictionar|dictionn|encyclop|lexik|lexic|wörterbuch|ordbo[gk]|who.s who|alumni|biograph", re.I)
DOC_RE = [("court case (' v. ')", re.compile(r" v\. ")),
          ("proclamation", re.compile(r"^Proclamation", re.I)),
          ("executive order", re.compile(r"^Executive Order", re.I)),
          ("UN resolution", re.compile(r"^United Nations .*Resolution", re.I)),
          ("public law / act / statute", re.compile(r"\bAct\b|Public Law|Statute", re.I)),
          ("speech / address / remarks / briefing", re.compile(r"speech|address|remarks|briefing|statement", re.I)),
          ("treaty / convention / agreement", re.compile(r"treaty|convention|agreement|protocol", re.I)),
          ("letter", re.compile(r"^Letter", re.I))]


def md(header, rows):
    return survey.md_table(header, rows)


def esc(s):
    return survey.esc(s)


def last_segment(title: str) -> str:
    pts = parent_titles(title)
    return title[len(pts[0]) + 1:] if pts else title


class Analysis(survey.Survey):
    def __init__(self, wiki):
        super().__init__(wiki)
        self.out = []
        self.cat = self.in_catalog()

    def p(self, *lines):
        self.out.extend(lines)

    # ---- decision 1: unknown works, what are they --------------------------
    def unknown_section(self):
        unk = [w for w in self.cat if self.wq(w).tier == "unknown"]
        self.p(f"## D1 unknown works ({len(unk):,})", "")
        if not unk:
            return
        kinds = Counter()
        for w in unk:
            hit = next((k for k, rx in DOC_RE if rx.search(w.root.title)), "other")
            kinds[hit] += 1
        self.p("Title heuristics (first match):", "")
        self.p(*md(["kind", "works"], [[k, f"{v:,}"] for k, v in kinds.most_common()]))
        tpls = Counter()
        for w in unk:
            n = w.root if w.root.rec else self.res.nodes[w.entry_title]
            h = n.rec.tpls & (self.cfg.header_templates | frozenset(self.cfg.positional_headers))
            tpls[", ".join(sorted(h)) or "(no header template)"] += 1
        self.p("", "Header templates on the unknown works' root page:", "")
        self.p(*md(["header", "works"], [[esc(k), f"{v:,}"] for k, v in tpls.most_common(8)]))
        colon = sum(1 for w in unk if w.structure == "single" and ":" in w.root.title and "/" not in w.root.title)
        self.p("", f"Single-page unknown works with a colon in the title: {colon:,}.")
        multi = [w for w in unk if w.structure != "single"]
        self.p(f"Multi-part unknown works: {len(multi):,} with {sum(w.part_count for w in multi):,} parts.")
        self.p("")

    # ---- decision 2: size components -------------------------------------
    def measure_variant(self, works, drop_qp=False, suffix=False, blank_display=False, drop_index=False):
        con = sqlite3.connect(":memory:")
        con.execute("""CREATE TABLE parts(work_id INTEGER NOT NULL, ord INTEGER NOT NULL,
            depth INTEGER NOT NULL, parent_ord INTEGER, page_title TEXT NOT NULL,
            display_title TEXT NOT NULL, kind TEXT NOT NULL, searchable INTEGER NOT NULL DEFAULT 0,
            bytes INTEGER, scan_from INTEGER, scan_to INTEGER, index_title TEXT,
            quality TEXT, quality_pages TEXT, complete INTEGER, PRIMARY KEY(work_id, ord))""")
        con.execute("CREATE INDEX parts_title ON parts(page_title)")
        rows = []
        for w in works:
            wid = w.root.rec.page_id if w.root.rec else -(abs(hash(w.root.title)) % 10**9) - 1
            pq = self.q[w.root.title][1]
            for p, q in zip(w.parts, pq):
                n = p.node
                title, display = n.title, n.display or n.title
                qp = json.dumps({str(k): v for k, v in q.pages.items()}) if q.pages else None
                if suffix and title.startswith(w.root.title + "/"):
                    title = title[len(w.root.title):]
                if blank_display and (display == n.title.rsplit("/", 1)[-1] or display == n.title):
                    display = ""
                if drop_qp:
                    qp = None
                idx = None if drop_index else n.index
                rows.append((wid, p.ord, p.depth, p.parent_ord, title, display, p.kind,
                             int(p.searchable), self.res.node_bytes(n), n.scan_from, n.scan_to, idx,
                             q.tier, qp, int(q.complete)))
        con.executemany("INSERT INTO parts VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)", rows)
        con.commit()
        raw = len(con.serialize())
        size = len(gzip.compress(con.serialize(), 9))
        con.close()
        return len(rows), size, raw

    def works_table_estimate(self):
        con = sqlite3.connect(":memory:")
        con.execute("""CREATE TABLE works(id INTEGER PRIMARY KEY, page_title TEXT NOT NULL UNIQUE,
            display_title TEXT NOT NULL, sort_title TEXT NOT NULL, type TEXT, structure TEXT NOT NULL,
            is_virtual INTEGER NOT NULL DEFAULT 0, entry_title TEXT, part_count INTEGER NOT NULL,
            text_count INTEGER NOT NULL, missing_part_count INTEGER NOT NULL DEFAULT 0,
            parts_in_catalog INTEGER NOT NULL DEFAULT 1, oversized INTEGER NOT NULL DEFAULT 0,
            bytes_total INTEGER, index_titles TEXT, quality TEXT NOT NULL, quality_source TEXT,
            quality_pages TEXT, complete INTEGER NOT NULL, incomplete_reason TEXT,
            missing_page_count INTEGER NOT NULL DEFAULT 0, coverage REAL,
            own_translation INTEGER NOT NULL DEFAULT 0)""")
        rows, fts = [], []
        for i, w in enumerate(self.cat):
            q = self.wq(w)
            idx = sorted({p.node.index for p in w.parts if p.node.index})
            rows.append((w.root.rec.page_id if w.root.rec else -i - 1, w.root.title, w.root.title,
                         w.root.title.lower(), w.type, w.structure, int(w.is_virtual), w.entry_title,
                         w.part_count, w.text_count, len(w.missing),
                         int(w.part_count <= self.cfg.parts_cap and w.type != "reference"),
                         int(w.oversized), w.bytes_total, json.dumps(idx) if idx else None, q.tier,
                         q.source, json.dumps({str(k): v for k, v in q.pages.items()}) if q.pages else None,
                         int(q.complete), q.reason, q.missing_pages, q.coverage, int(q.own_translation)))
            fts.append((w.root.title, ""))
        con.executemany("INSERT INTO works VALUES (" + ",".join("?" * 23) + ")", rows)
        for col in ("type", "quality", "sort_title"):
            con.execute(f"CREATE INDEX works_{col} ON works({col})")
        con.commit()
        size = len(gzip.compress(con.serialize(), 9))
        con.close()
        con = sqlite3.connect(":memory:")
        con.execute("CREATE VIRTUAL TABLE works_fts USING fts5(display_title, author_names, content='')")
        con.executemany("INSERT INTO works_fts VALUES (?,?)", fts)
        con.commit()
        f = len(gzip.compress(con.serialize(), 9))
        con.close()
        return size, f

    def size_section(self):
        self.p("## D2 parts table size by component (cap 2,000, reference works out)", "")
        sel = [w for w in self.cat if w.structure != "single"
               and w.part_count <= self.cfg.parts_cap and w.type != "reference"]
        variants = [("as in the schema", {}),
                    ("no per-part quality_pages", dict(drop_qp=True)),
                    ("page_title as suffix of the root title", dict(suffix=True)),
                    ("display_title blank when it equals the last title segment", dict(blank_display=True)),
                    ("no per-part index_title", dict(drop_index=True)),
                    ("no quality_pages + no index_title", dict(drop_qp=True, drop_index=True)),
                    ("all four", dict(drop_qp=True, suffix=True, blank_display=True, drop_index=True))]
        rows = []
        base = None
        for label, kw in variants:
            n, gz, raw = self.measure_variant(sel, **kw)
            base = base or gz
            rows.append([label, f"{n:,}", f"{raw / 1e6:.1f} MB", f"{gz / 1e6:.2f} MB", f"{100 * (base - gz) / base:.0f} %"])
        self.p(*md(["variant", "rows", "raw SQLite", "gzipped", "saving"], rows))
        # distribution of rows over works by part count
        buckets = [(0, 50), (50, 100), (100, 200), (200, 500), (500, 1000), (1000, 2000)]
        brows = []
        for a, b in buckets:
            ws = [w for w in sel if a < w.part_count <= b]
            brows.append([f"{a + 1}–{b}", f"{len(ws):,}", f"{sum(len(w.parts) for w in ws):,}"])
        self.p("", "Rows by work size (parts per work):", "")
        self.p(*md(["parts per work", "works", "rows"], brows))
        qp = sum(1 for w in sel for q in self.q[w.root.title][1] if q.pages)
        allrows = sum(len(w.parts) for w in sel)
        self.p("", f"Parts with a quality_pages JSON: {qp:,} of {allrows:,}.")
        ws, wf = self.works_table_estimate()
        self.p(f"Works table lower bound (structure and quality columns only, no authors, years, "
               f"descriptions): {ws / 1e6:.2f} MB gzipped for {len(self.cat):,} works; works_fts "
               f"(titles only) {wf / 1e6:.2f} MB.")
        self.p("")

    # ---- decision 3: reference typing --------------------------------------
    def reference_section(self):
        thr = self.cfg.reference_threshold
        self.p(f"## D3 works over reference_threshold ({thr:,}) without an Index type", "")
        big = [w for w in self.res.works if w.part_count > thr and not (
            w.root.index_type or (w.is_virtual and w.parts and w.parts[0].node.index_type))]
        rows = []
        for w in sorted(big, key=lambda w: -w.part_count):
            alt = "collection/book" if w.root.order_source in ("chain", "toc") else "reference"
            rows.append([esc(w.root.title), f"{w.part_count:,}", f"{w.text_count:,}", w.structure,
                         w.root.order_source or "–", w.max_depth + 1, self.wq(w).tier, alt])
        self.p(*md(["work", "parts", "texts", "structure", "order", "depth", "tier", "type if chain/toc order → not reference"], rows) if rows else ["None."])
        byidx = [w for w in self.res.works if w.type == "reference" and w not in big]
        self.p("", f"Reference works typed by the Index type: {len(byidx):,} "
               f"(over the cap: {sum(1 for w in byidx if w.part_count > self.cfg.parts_cap):,}).")
        rx = [w for w in self.res.works if w.type != "reference" and w.part_count >= 50 and REF_RE.search(w.root.title)]
        self.p("", f"Non-reference works with ≥ 50 parts whose title matches a reference-word regex "
               f"(dictionar|encyclop|lexik|…): {len(rx):,}. Examples:", "")
        for w in sorted(rx, key=lambda w: -w.part_count)[:15]:
            self.p(f"- `{esc(w.root.title)}` ({w.type}, {w.part_count:,} parts, {w.text_count:,} texts, "
                   f"order {w.root.order_source or '–'}, Index type {w.root.index_type_raw or '–'})")
        self.p("")

    # ---- decision 4: de colon prefixes -----------------------------------
    def prefix_section(self):
        if self.wiki != "de":
            return
        self.p("## D4 de colon prefixes (kept, ≥ 100 root pages)", "")
        roots = [t for t in self.ext["pages"] if "/" not in t and ":" in t]
        c = Counter(t.split(":", 1)[0] + ":" for t in roots)
        rows = []
        for pref, n in c.most_common():
            if n < 100 or pref in self.cfg.exclude_title_prefixes:
                continue
            titles = [t for t in roots if t.startswith(pref)]
            single = grouped = excluded = 0
            tiers = Counter()
            parents = Counter()
            for t in titles:
                if t in self.res.excluded:
                    excluded += 1
                    continue
                if t in self.part_of:
                    w, p = self.part_of[t]
                    if p.node is w.root and w.structure == "single":
                        single += 1
                        tiers[self.wq(w).tier] += 1
                    else:
                        grouped += 1
                        parents[w.root.title] += 1
            rootname = pref[:-1]
            rt = self.res.resolve(rootname)
            if rt in self.res.excluded:
                root_state = f"excluded ({self.res.excluded[rt]})"
            elif rt in self.by_title:
                w = self.by_title[rt]
                listed = sum(1 for t in titles if t in set(w.root.toc))
                root_state = f"work ({w.structure}, {w.part_count} parts, TOC lists {listed} of the prefix pages)"
            elif rt in self.part_of:
                root_state = f"part of `{esc(self.part_of[rt][0].root.title)}`"
            elif rt in self.ext["pages"]:
                root_state = "page exists, unresolved"
            else:
                root_state = "no page"
            rows.append([esc(pref), n, single, grouped, excluded,
                         ", ".join(f"{k} {v}" for k, v in tiers.most_common()),
                         "; ".join(f"`{esc(k)}` {v}" for k, v in parents.most_common(2)),
                         root_state])
        self.p(*md(["prefix", "root pages", "single works", "grouped as parts", "excluded",
                    "tiers of the single works", "grouped under", f"root page `Prefix`"], rows))
        unk = [w for w in self.cat if self.wq(w).tier == "unknown"]
        pref_unk = Counter()
        for w in unk:
            t = w.root.title
            if w.structure == "single" and ":" in t and "/" not in t:
                pref_unk[t.split(":", 1)[0] + ":"] += 1
        big = {k: v for k, v in pref_unk.items() if c.get(k, 0) >= 100}
        self.p("", f"Of the {len(unk):,} unknown works, {sum(pref_unk.values()):,} are single colon-prefixed "
               f"root pages; {sum(big.values()):,} of those carry a prefix with ≥ 100 root pages: "
               + ", ".join(f"`{esc(k)}` {v}" for k, v in sorted(big.items(), key=lambda x: -x[1])) + ".")
        small = Counter({k: v for k, v in pref_unk.items() if 10 <= c.get(k, 0) < 100})
        self.p(f"Prefixes with 10–99 root pages among the unknown works: "
               + ", ".join(f"`{esc(k)}` {v}" for k, v in small.most_common(20)) + ".")
        self.p("")

    # ---- decision 6: index progress ----------------------------------------
    def faulty_section(self):
        fl = [w for w in self.res.works if self.wq(w).excluded == "index-faulty"]
        self.p(f"## D6 works excluded as index-faulty ({len(fl):,})", "")
        if not fl:
            self.p("None.", "")
            return
        rows = []
        byt = Counter()
        for w in fl:
            q = self.wq(w)
            t, _ = quality.tier_from_pages(q.pages)
            good = q.pages.get(3, 0) + q.pages.get(4, 0)
            n = sum(v for k, v in q.pages.items() if k in (1, 2, 3, 4))
            byt[(t or "none", "some level ≥ 3" if good else "no level ≥ 3")] += 1
        self.p(*md(["page-level tier", "proofread pages", "works"],
                   [[a, b, v] for (a, b), v in byt.most_common()]))
        rng = random.Random("faulty")
        self.p("", "12 random examples (raw progress code, page levels):", "")
        for w in rng.sample(fl, min(12, len(fl))):
            q = self.wq(w)
            root = w.root if w.root.rec else self.res.nodes[w.entry_title]
            _, raw = self.ass.progress(root)
            lv = ", ".join(f"L{k}: {v}" for k, v in sorted(q.pages.items(), key=lambda x: str(x[0])))
            self.p(f"- `{esc(w.root.title)}` — `{raw}` on `{esc(root.index or '')}`; pages {lv}; "
                   f"tier by pages {quality.tier_from_pages(q.pages)[0]}")
        self.p("")

    # ---- decision 7: coverage ----------------------------------------------
    def coverage_section(self):
        self.p("## D7 coverage of the Index's text pages", "")
        rows = []
        buckets = Counter()
        detail = []
        for w in self.cat:
            q = self.wq(w)
            if q.coverage is None:
                continue
            c = q.coverage
            b = "< 50 %" if c < .5 else "50–80 %" if c < .8 else "80–90 %" if c < .9 else "90–95 %" if c < .95 else "≥ 95 %"
            buckets[b] += 1
            if c >= .95:
                continue
            f = w.root.index
            lv = self.res.levels.get(f)
            existing = {i for i, x in enumerate(lv) if x in (1, 2, 3, 4)}
            used = set()
            for p in w.parts:
                if p.node.rec:
                    used.update(n for ff, n in self.res.scan_pages(p.node.rec) if ff == f)
            used &= existing
            unc = existing - used
            if not used:
                detail.append((w, c, len(existing), 0, 0, 0, len(unc), "nothing used"))
                continue
            lo, hi = min(used), max(used)
            front = sum(1 for i in unc if i < lo)
            back = sum(1 for i in unc if i > hi)
            inner = len(unc) - front - back
            inner_cov = len(used) / (len(used) + inner)
            lvl_unc = Counter(lv[i] for i in unc)
            detail.append((w, c, len(existing), front, inner, back, inner_cov, lvl_unc))
        self.p(*md(["coverage", "works"], [[k, v] for k, v in sorted(buckets.items())]))
        if not detail:
            self.p("")
            return
        kinds = Counter()
        for w, c, ex, front, inner, back, ic, lu in detail:
            if ic == "nothing used":
                kinds["nothing of the Index used"] += 1
            elif inner == 0:
                kinds["only front/back matter uncovered"] += 1
            elif ic >= .95:
                kinds["inner gaps under 5 %"] += 1
            else:
                kinds["inner gaps ≥ 5 % (missing chapters)"] += 1
        self.p("", f"Where the uncovered pages lie, for the {len(detail):,} works under 95 %:", "")
        self.p(*md(["case", "works"], [[k, v] for k, v in kinds.most_common()]))
        # levels of uncovered pages
        lvtot = Counter()
        for d in detail:
            if isinstance(d[7], Counter):
                lvtot.update(d[7])
        self.p("", "Levels of the uncovered pages (all works under 95 %): "
               + ", ".join(f"L{k}: {v:,}" for k, v in sorted(lvtot.items())) + ".")
        for thr in (.9, .8):
            n = sum(1 for d in detail if d[1] < thr)
            self.p(f"Works under {thr:.0%}: {n:,}.")
        n_inner = sum(1 for d in detail if d[6] != 'nothing used' and d[6] < .95)
        self.p(f"Works with inner coverage (between the first and last transcluded page) under 95 %: {n_inner:,}.")
        rng = random.Random("coverage")
        self.p("", "20 random works under 95 %:", "")
        for w, c, ex, front, inner, back, ic, lu in rng.sample(detail, min(20, len(detail))):
            q = self.wq(w)
            self.p(f"- `{esc(w.root.title)}` ({w.structure}, {w.part_count} parts, {len(w.missing)} red links, "
                   f"{q.tier}): coverage {c:.0%} of {ex} pages; uncovered front {front}, inner {inner}, back {back}"
                   + (f"; inner coverage {ic:.0%}" if ic != "nothing used" else "")
                   + (f"; uncovered levels " + ", ".join(f"L{k}: {v}" for k, v in sorted(lu.items())) if isinstance(lu, Counter) else ""))
        self.p("")

    # ---- decision 8: page-title chapter rule -------------------------------
    def chapter_section(self):
        self.p("## D8 searchable texts whose page title segment is a chapter label", "")
        rx = self.cfg.chapter_label
        demote = []
        total = 0
        for w in self.res.works:
            if w.type == "reference":
                continue
            for p in w.parts[1:]:
                if not p.searchable:
                    continue
                total += 1
                seg = last_segment(p.node.title)
                if rx.search(seg.strip()):
                    demote.append((w, p, seg))
        self.p(f"Searchable texts (reference works excluded): {total:,}; with a chapter-like page title "
               f"segment: {len(demote):,} ({survey.pct(len(demote), total)}).", "")
        byt = Counter(w.type for w, _, _ in demote)
        byi = Counter(w.root.index_type_raw or "(none)" for w, _, _ in demote)
        self.p(*md(["work type", "texts demoted"], [[k, f"{v:,}"] for k, v in byt.most_common()]))
        self.p("")
        self.p(*md(["Index type (raw)", "texts demoted"], [[esc(k), f"{v:,}"] for k, v in byi.most_common(8)]))
        rng = random.Random("chapter")
        self.p("", "30 random texts the page-title rule would demote to chapters:", "")
        for w, p, seg in rng.sample(demote, min(30, len(demote))):
            self.p(f"- “{esc(p.node.display)}” (`{esc(p.node.title)}`) in `{esc(w.root.title)}` ({w.type}, Index type {w.root.index_type_raw or '–'})")
        # how many works would change type book<->collection
        self.p("")

    def run(self):
        self.p(f"# {self.wiki}: evidence for decisions 1–8 ({time.strftime('%Y-%m-%d')})", "")
        self.p(f"Resolution and quality: {self.elapsed:.0f} s; {len(self.res.works):,} works, "
               f"{len(self.cat):,} in the catalog.", "")
        self.unknown_section()
        self.size_section()
        self.reference_section()
        self.prefix_section()
        self.faulty_section()
        self.coverage_section()
        self.chapter_section()
        return "\n".join(self.out) + "\n"


if __name__ == "__main__":
    a = Analysis(sys.argv[1])
    sys.stdout.write(a.run())
