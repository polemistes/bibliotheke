# SPDX-License-Identifier: GPL-3.0-or-later
"""WP1a structure and quality survey (PLAN.md 5.2 step 0, WS§6, §10.8 item 4).

    python -I -m wscatalog.survey no is de fr en     # writes report/{wiki}-structure.md

Runs the resolution (structure.py) and the quality rules (quality.py) on the
cached extract and reports the numbers the owner needs to set parts_cap, the
chapter-label patterns, the de prefix list and show_unknown_by_default.
"""

from __future__ import annotations

import gzip
import json
import random
import re
import sqlite3
import statistics
import sys
import time
from collections import Counter
from pathlib import Path

from . import extract, quality, structure, wikitext as wt

REPORT_DIR = Path(__file__).resolve().parents[2] / "report"
NAMED = {
    "no": ["Diktsamling", "Ved Rundarne (1864)", "Arne", "Peer Gynt", "Sult"],
    "de": ["Der Sagenschatz des Königreichs Sachsen", "Kinder- und Haus-Märchen Band 1 (1857)",
           "Der Froschkönig oder der eiserne Heinrich (1857)", "Kinder und Hausmärchen",
           "Kinder- und Hausmärchen", "Die Heimath in der neuen Welt", "Käthe", "Erlkönig"],
    "fr": ["La Légende des siècles", "L’Épopée du ver", "Clarté d’âmes", "Madame Bovary"],
    "en": ["The Complete Poetical Works of Percy Bysshe Shelley (ed. Hutchinson, 1914)",
           "Ozymandias", "Ozymandias (Shelley)",
           "Frankenstein, or the Modern Prometheus (Revised Edition, 1831)",
           "1911 Encyclopædia Britannica"],
    "is": [],
}
TIERS = ["validated", "proofread", "unproofread", "unknown"]


def pct(a: int, b: int) -> str:
    return f"{100 * a / b:.1f} %" if b else "–"


def md_table(header: list[str], rows: list[list]) -> list[str]:
    out = ["| " + " | ".join(header) + " |", "|" + "---|" * len(header)]
    out += ["| " + " | ".join(str(c) for c in r) + " |" for r in rows]
    return out


def esc(s: str) -> str:
    return (s or "").replace("|", "\\|").replace("\n", " ")


class Survey:
    def __init__(self, wiki: str):
        self.wiki = wiki
        t0 = time.time()
        self.ext = extract.load(wiki)
        self.res = structure.resolve(wiki, self.ext)
        self.cfg = self.res.cfg
        self.ass = quality.Assessor(self.res)
        self.q = {}
        for w in self.res.works:
            self.q[w.root.title] = self.ass.assess(w)
        self.elapsed = time.time() - t0
        self.rng = random.Random(f"wp1a-{wiki}")
        self.by_title = {w.root.title: w for w in self.res.works}
        self.part_of = {}
        for w in self.res.works:
            for p in w.parts:
                self.part_of[p.node.title] = (w, p)

    def wq(self, w):
        return self.q[w.root.title][0]

    def in_catalog(self):
        return [w for w in self.res.works if not self.wq(w).excluded]

    # ---- sections ------------------------------------------------------
    def header(self) -> list[str]:
        e, r = self.ext, self.res
        scan = sum(1 for lv in e["levels"].values() for x in lv if x != 255) + len(e["single"])
        return [
            f"# {self.wiki}.wikisource: structure and quality survey (WP1a)",
            "",
            f"Generated {time.strftime('%Y-%m-%d')} by `python -I -m wscatalog.survey {self.wiki}` "
            f"from `{', '.join(e['dump'])}` (resolution and quality rules: PLAN.md 5.2 "
            f"steps 1–1h and 5.4; config `pipeline/config/{self.wiki}.yaml`). "
            f"Resolution took {self.elapsed:.0f} s on the cached extract.",
            "",
            f"Input: {len(e['pages']):,} ns0 pages, {len(e['redirects']):,} ns0 redirects, "
            f"{scan:,} scan pages, {len(e['indexes']):,} Index pages, "
            f"{len(e['disamb']):,} pages with the `disambiguation` page prop.",
            "",
        ]

    def works_section(self) -> list[str]:
        works = self.res.works
        cat = self.in_catalog()
        out = ["## 1. Works", ""]
        st = Counter(w.structure for w in works)
        ty = Counter(w.type for w in works)
        parts = sum(w.part_count for w in works if w.structure != "single")
        texts = sum(w.text_count for w in works)
        out.append(f"{len(works):,} works resolved from {len(self.res.nodes) - len(self.res.virtual):,} "
                   f"non-excluded ns0 pages; {len(cat):,} enter the catalog after the hard "
                   f"exclusions (section 9). Multi-part works hold {parts:,} parts, of which "
                   f"{texts:,} are searchable texts.")
        out.append("")
        out += md_table(["structure", "works", "parts", "texts"],
                        [[k, f"{v:,}", f"{sum(w.part_count for w in works if w.structure == k):,}",
                          f"{sum(w.text_count for w in works if w.structure == k):,}"]
                         for k, v in st.most_common()])
        out.append("")
        out += md_table(["type (step 1e)", "works"], [[k, f"{v:,}"] for k, v in ty.most_common()])
        out.append("")
        big = sorted(works, key=lambda w: -w.part_count)[:15]
        out.append("Largest works by parts:")
        out.append("")
        out += md_table(["work", "structure", "type", "parts", "texts", "missing", "rules of the parts"],
                        [[esc(w.root.title), w.structure, w.type, f"{w.part_count:,}", f"{w.text_count:,}",
                          len(w.missing),
                          ", ".join(f"{k} {v}" for k, v in Counter(p.node.rule for p in w.parts[1:]).most_common())]
                         for w in big])
        out.append("")
        return out

    def exclusions_section(self) -> list[str]:
        out = ["## 2. Exclusions (step 1)", ""]
        ex = Counter(self.res.excluded.values())
        out += md_table(["reason", "pages"], [[k, f"{v:,}"] for k, v in ex.most_common()] or [["–", 0]])
        out.append("")
        return out

    def patterns(self) -> dict[str, list]:
        r = self.res
        works = self.res.works
        # Pattern E (WS§1): texts that exist only inside a collection and are
        # reached from a disambiguation or versions page (en `Ozymandias
        # (Shelley)` -> the Hutchinson poem). Sampled: subpage parts linked
        # from an excluded disambiguation or versions page.
        e_texts, seen = [], set()
        for t, why in r.excluded.items():
            if why not in ("disambiguation", "versions"):
                continue
            for tgt in self.ext["pages"][t].links:
                tgt = r.resolve(tgt)
                if tgt in self.part_of and tgt not in seen:
                    w, p = self.part_of[tgt]
                    if p.node is not w.root and tgt.startswith(w.root.title + "/") \
                            and w.type != "reference":     # reference parts are not in the catalog
                        seen.add(tgt)
                        e_texts.append((w, p))
        e_hidden = [(w, p) for w in works for p in w.parts
                    if p.searchable and p.node.title.startswith(w.root.title + "/")]
        ov = self.cfg.other_versions_templates
        return {
            "A": [w for w in works if w.structure == "linked"],
            "B": [w for w in works if w.structure == "mixed"],
            "C": [w for w in works if w.oversized],
            "D": [w for w in works if w.structure == "virtual"],
            "E": e_texts,
            "E*": e_hidden,
            "F": [t for t, why in r.excluded.items() if why == "whole-text"],
            "G": [w for w in works if w.type == "reference"],
            "Gx": [t for t, why in r.excluded.items() if why == "reference-prefix"],
            "H": [w for w in works if w.max_depth >= 2],
            "I": [w for w in works if any(p.node.rec and p.node.rec.tpls & ov for p in w.parts)],
            "Iv": [t for t, why in r.excluded.items() if why == "versions"],
        }

    def patterns_section(self, pats) -> list[str]:
        out = ["## 3. Patterns A–I (WS§1)", ""]
        rows = [
            ["A", "collection of root pages (`structure = linked`)", f"{len(pats['A']):,} works, "
             f"{sum(w.part_count for w in pats['A']):,} parts"],
            ["B", "mixed subpages and root pages (`mixed`)", f"{len(pats['B']):,} works, "
             f"{sum(w.part_count for w in pats['B']):,} parts"],
            ["C", "work with an oversized page (> oversized_bytes, transcluded scan pages included)",
             f"{len(pats['C']):,} works"],
            ["D", "missing root, virtual root (`virtual`)", f"{len(pats['D']):,} works, "
             f"{sum(w.part_count for w in pats['D']):,} parts"],
            ["E", "texts inside a subpage collection reached from a disambiguation or versions page",
             f"{len(pats['E']):,} parts ({sum(p.searchable for _, p in pats['E']):,} searchable); "
             f"all searchable subpage texts: {len(pats['E*']):,}"],
            ["F", "whole-text duplicate subpages (excluded)", f"{len(pats['F']):,} pages"],
            ["G", "reference works (type `reference`)", f"{len(pats['G']):,} works, "
             f"{sum(w.part_count for w in pats['G']):,} parts; {len(pats['Gx']):,} pages excluded by title prefix"],
            ["H", "deep nesting (parts at depth ≥ 2, i.e. three levels below the root)",
             f"{len(pats['H']):,} works"],
            ["I", "several editions: works with an other-versions template", f"{len(pats['I']):,} works; "
             f"{len(pats['Iv']):,} versions pages excluded"],
        ]
        out += md_table(["", "pattern", "count"], rows)
        out.append("")
        depth = Counter(w.max_depth + 1 for w in self.res.works if w.structure != "single")
        out.append("Tree depth of multi-part works (levels below the root): "
                   + ", ".join(f"{k}: {v:,}" for k, v in sorted(depth.items())) + ".")
        out.append("")
        return out

    def rules_section(self) -> list[str]:
        r = self.res
        nodes = [n for n in r.nodes.values() if n.rec is not None]
        out = ["## 4. Parent rules and order sources (steps 1b, 1c)", ""]
        rules = Counter(n.rule for n in nodes)
        out += md_table(["parent rule", "pages"], [[k, f"{v:,}"] for k, v in rules.most_common()])
        out.append("")
        orders = Counter(n.order_source for n in r.nodes.values() if len(n.children) > 1)
        out += md_table(["order source (nodes with ≥ 2 children)", "nodes"],
                        [[k, f"{v:,}"] for k, v in orders.most_common()])
        out.append("")
        partial = sum(1 for n in r.nodes.values() if n.order_source == "natural" and n.toc)
        out.append(f"`toc-partial` (implementation choice, see section 13): the TOC lists at least "
                   f"half of the children, which keep its order; the rest follow in natural order. "
                   f"{partial:,} `natural` nodes have a TOC that lists fewer than half.")
        out.append("")
        top = Counter(n.parent for n in nodes if n.rule == "container" and "/" not in n.title)
        out.append(f"Root pages attached by their header's container link: "
                   f"{sum(top.values()):,}; most frequent containers: "
                   + "; ".join(f"`{esc(k)}` {v}" for k, v in top.most_common(10)) + ".")
        out.append("")
        tocs = Counter(n.parent for n in nodes if n.rule == "toc")
        out.append(f"Pages attached by TOC membership (rule 3): {sum(tocs.values()):,}; "
                   "most frequent TOC pages: "
                   + "; ".join(f"`{esc(k)}` {v}" for k, v in tocs.most_common(10)) + ".")
        out.append("")
        return out

    def container_section(self) -> list[str]:
        r = self.res
        out = ["## 5. Container links that fail rule 1", ""]
        fails = [n for n in r.nodes.values() if n.container_fail]
        withc = sum(1 for n in r.nodes.values() if n.rec and r.hdr(n.rec, "container")
                    and wt.first_link(r.hdr(n.rec, "container")))
        out.append(f"{len(fails):,} of {withc:,} pages whose header carries a container link "
                   f"({pct(len(fails), withc)}) point to:")
        out.append("")
        for why in ("outside-ns0", "excluded", "missing"):
            ex = [n for n in fails if n.container_fail == why]
            sample = ", ".join(f"`{esc(n.title)}` → `{esc(wt.first_link(r.hdr(n.rec, 'container')))}`"
                               for n in self.rng.sample(ex, min(4, len(ex))))
            out.append(f"- {why}: {len(ex):,}" + (f" (e.g. {sample})" if ex else ""))
        out.append("")
        return out

    def chain_section(self) -> list[str]:
        r = self.res
        c = r.chain_cross
        out = ["## 6. Prev/next chains across Index boundaries", ""]
        total = c["same"] + c["cross"]
        out.append(f"Of {total:,} next-links between pages that both have an Index, "
                   f"{c['cross']:,} ({pct(c['cross'], total)}) cross to a different Index.")
        ex = [n for n in r.nodes.values() if n.next in r.nodes and n.index and r.nodes[n.next].index
              and n.index != r.nodes[n.next].index]
        if ex:
            out.append("Examples: " + "; ".join(
                f"`{esc(n.title)}` ({esc(n.index)}) → `{esc(n.next)}` ({esc(r.nodes[n.next].index)})"
                for n in self.rng.sample(ex, min(4, len(ex)))) + ".")
        chains = Counter()
        for n in r.nodes.values():
            if n.rec and "/" not in n.title and n.parent is None and (n.next in r.nodes or n.prev in r.nodes):
                chains["root works on a chain"] += 1
        out.append("")
        out.append(f"Root-page works that sit on a prev/next chain but stay separate works "
                   f"(no container, not in a unique TOC; WS§8 item 5): {chains['root works on a chain']:,}.")
        out.append("")
        return out

    def orphans_section(self) -> list[str]:
        r = self.res
        out = ["## 7. Orphan trees, oversized pages, red links", ""]
        virt = [w for w in r.works if w.is_virtual]
        out.append(f"**Orphan trees** (virtual roots): {len(virt):,}, holding "
                   f"{sum(w.part_count for w in virt):,} parts. Examples: "
                   + "; ".join(f"`{esc(w.root.title)}` ({w.part_count})"
                               for w in self.rng.sample(virt, min(8, len(virt)))) + ".")
        out.append("")
        sizes = []
        for n in r.nodes.values():
            b = r.node_bytes(n)
            if b > self.cfg.oversized_bytes:
                sizes.append((b, n.title))
        sizes.sort(reverse=True)
        out.append(f"**Oversized pages** (> {self.cfg.oversized_bytes:,} bytes of wikitext including "
                   f"transcluded scan pages): {len(sizes):,}. Largest: "
                   + "; ".join(f"`{esc(t)}` {b / 1e6:.1f} MB" for b, t in sizes[:10]) + ".")
        out.append("")
        ratios = []
        for w in r.works:
            if w.structure == "single":
                continue
            listed = len(w.missing) + w.part_count
            if w.missing:
                ratios.append((len(w.missing) / listed, w))
        out.append(f"**Red links in TOCs** (missing parts): {len(ratios):,} multi-part works "
                   f"have red links, {sum(len(w.missing) for _, w in ratios):,} in total"
                   + (f"; median share of the TOC {statistics.median(r_ for r_, _ in ratios):.0%}"
                      if ratios else "") + ". Most missing: "
                   + "; ".join(f"`{esc(w.root.title)}` {len(w.missing)} of {len(w.missing) + w.part_count}"
                               for _, w in sorted(ratios, key=lambda x: -len(x[1].missing))[:8]) + ".")
        out.append("")
        return out

    def prefix_section(self) -> list[str]:
        roots = [t for t in self.ext["pages"] if "/" not in t and ":" in t]
        c = Counter(t.split(":", 1)[0] + ":" for t in roots)
        out = ["## 8. Colon prefixes with ≥ 100 root pages", ""]
        rows = [[esc(k), f"{v:,}", "excluded" if k in self.cfg.exclude_title_prefixes else "kept"]
                for k, v in c.most_common() if v >= 100]
        out += md_table(["prefix", "root pages", "status"], rows) if rows else ["None."]
        out.append("")
        return out

    def quality_section(self) -> list[str]:
        works = self.res.works
        cat = self.in_catalog()
        out = ["## 9. Quality and completeness (PLAN.md 5.4)", ""]
        ex = Counter(self.wq(w).excluded for w in works if self.wq(w).excluded)
        out.append(f"**Hard exclusions**: {sum(ex.values()):,} works — "
                   + (", ".join(f"{k} {v:,}" for k, v in ex.most_common()) or "none") + ".")
        out.append("")
        tiers = Counter(self.wq(w).tier for w in cat)
        src = Counter((self.wq(w).tier, self.wq(w).source) for w in cat)
        texts = Counter()
        for w in cat:
            texts[self.wq(w).tier] += w.text_count
        rows = []
        for t in TIERS:
            rows.append([t, f"{tiers[t]:,}", pct(tiers[t], len(cat)), f"{texts[t]:,}",
                         ", ".join(f"{s} {v:,}" for (tt, s), v in src.most_common() if tt == t)])
        out += md_table(["tier", "works", "share", "texts in them", "source"], rows)
        mostly = sum(1 for w in cat if self.wq(w).mostly)
        raw = sum(1 for w in cat if self.wq(w).tier == "unproofread"
                  and not sum(v for k, v in self.wq(w).pages.items() if k in (2, 3, 4)))
        out.append("")
        out.append(f"{mostly:,} works are proofread by the ≥ 90 % rule. {raw:,} of the "
                   f"{tiers['unproofread']:,} unproofread works have no page above level 1 (raw OCR).")
        out.append("")
        inc = Counter(self.wq(w).reason for w in cat if not self.wq(w).complete)
        out.append(f"**Incomplete**: {sum(inc.values()):,} works — "
                   + (", ".join(f"{k} {v:,}" for k, v in inc.most_common()) or "none") + ".")
        mp = [w for w in cat if self.wq(w).missing_pages]
        out.append(f"Works with missing scan pages inside a transcluded range: {len(mp):,} "
                   f"({sum(self.wq(w).missing_pages for w in mp):,} pages).")
        cov = [self.wq(w).coverage for w in cat if self.wq(w).coverage is not None]
        if cov:
            out.append(f"Coverage was computed for {len(cov):,} works that are their Index's book; "
                       f"{sum(c < 0.95 for c in cov):,} cover under 95 % of the Index's text pages.")
        unres = sum(1 for w in cat if self.wq(w).unresolved)
        if unres:
            out.append(f"{unres:,} works transclude scan pages of files the dump has no Page: "
                       f"pages for (counted as no signal, not as missing).")
        own = sum(1 for w in cat if self.wq(w).own_translation)
        out.append(f"Wikisource's own translations (labelled, kept): {own:,}.")
        out.append("")
        # raw signal vocabularies
        st = Counter((n.rec.hdr.get("status") or "").strip().lower() for n in self.res.nodes.values()
                     if n.rec and n.rec.hdr.get("status"))
        if st:
            out.append("Status values (`" + "`, `".join(self.cfg.status_params) + "`): "
                       + ", ".join(f"`{esc(k)}` {v:,}" for k, v in st.most_common(15)) + ".")
        tq = Counter((n.rec.hdr.get("textquality") or "").strip() for n in self.res.nodes.values()
                     if n.rec and n.rec.hdr.get("textquality"))
        if tq:
            out.append("Text-quality template values: "
                       + ", ".join(f"`{esc(k)}` {v:,}" for k, v in tq.most_common(12)) + ".")
        prog = Counter()
        for f in self.res.indexes.values():
            for fld in self.cfg.index_progress_fields:
                if f.get(fld):
                    prog[f[fld].strip()] += 1
                    break
        if prog:
            out.append("Index progress values: "
                       + ", ".join(f"`{esc(k)}` {v:,}" for k, v in prog.most_common(12)) + ".")
            fl = [w for w in works if self.wq(w).excluded == "index-faulty"]
            if fl:
                lv = Counter(quality.tier_from_pages(self.wq(w).pages)[0] or "none" for w in fl)
                out.append(f"Works excluded as index-faulty, by the tier their page levels would "
                           f"give: " + ", ".join(f"{k} {v:,}" for k, v in lv.most_common()) + ".")
        out.append("")
        # unknown works
        unk = [w for w in cat if self.wq(w).tier == "unknown"]
        ex_unk = [w for w in works if self.wq(w).excluded in ("junk-template", "placeholder", "under-300-bytes")]
        out.append(f"**Unknown works** (no signal): {len(unk):,} in the catalog after the junk rules; "
                   f"the junk rules removed {len(ex_unk):,} (" + ", ".join(
                       f"{k} {v:,}" for k, v in Counter(self.wq(w).excluded for w in ex_unk).most_common())
                   + ").")
        if unk:
            sizes = sorted(w.bytes_total for w in unk)
            out.append(f"Size: median {sizes[len(sizes) // 2]:,} bytes, p10 {sizes[len(sizes) // 10]:,}, "
                       f"p90 {sizes[9 * len(sizes) // 10]:,}; multi-part {sum(1 for w in unk if w.structure != 'single'):,}.")
            out.append("")
            out.append("30 random unknown works:")
            out.append("")
            for w in self.rng.sample(unk, min(30, len(unk))):
                n = w.root if w.root.rec else self.res.nodes[w.entry_title]
                head = re.sub(r"\s+", " ", wt.strip_markup(self.page_text(n)))[:160]
                out.append(f"- `{esc(w.root.title)}` ({w.structure}, {w.bytes_total:,} B): {esc(head)}")
        out.append("")
        # default view
        dv = [w for w in cat if self.wq(w).complete and self.wq(w).tier in ("validated", "proofread")
              and w.type != "reference"]
        du = [w for w in cat if self.wq(w).complete and self.wq(w).tier == "unknown" and w.type != "reference"]
        rawdv = sum(1 for w in dv if self.wq(w).pages.get(1) and not sum(
            v for k, v in self.wq(w).pages.items() if k in (2, 3, 4)))
        out.append(f"**Default view** (validated or proofread, complete, not reference): "
                   f"{len(dv):,} works with {sum(w.text_count for w in dv):,} texts; "
                   f"with unknown works shown: {len(dv) + len(du):,} works "
                   f"({sum(w.text_count for w in dv + du):,} texts). Works in the default view whose "
                   f"pages are all level 1: {rawdv}. Current setting: "
                   f"`show_unknown_by_default = {self.cfg.show_unknown_by_default}`.")
        out.append("")
        return out

    def page_text(self, n) -> str:
        return n.rec.snippet if n.rec else ""

    def size_section(self) -> list[str]:
        out = ["## 10. Parts table size (WS§5.2)", ""]
        rows = []
        cat = self.in_catalog()
        singles = sum(1 for w in cat if w.structure == "single")
        for cap, compact in ((500, False), (2000, False), (None, False), (2000, True)):
            sel = [w for w in cat if w.structure != "single"
                   and (cap is None or (w.part_count <= cap and w.type != "reference"))]
            nrows, gz, fts = self.measure(sel, compact)
            label = f"{cap:,}" if cap else "∞ (all, reference works included)"
            rows.append([label + (", compact encoding" if compact else ""), f"{len(sel):,}",
                         f"{nrows:,}", f"{gz / 1e6:.2f} MB", f"{fts / 1e6:.2f} MB"])
        out += md_table(["parts_cap", "works with catalog parts", "parts rows", "parts table, gzipped",
                         "parts_fts, gzipped"], rows)
        out.append("")
        out.append(f"Rows count multi-part works only, one row per part plus the root row. "
                   f"{singles:,} single-page works would add one row each if stored. Sizes are a "
                   f"SQLite file holding only the table (with its index) at gzip level 9. The "
                   f"compact encoding (not in WS§5.1, measured for the cap decision) stores a part's "
                   f"page_title as the suffix after the work root's title where possible, "
                   f"display_title only when it differs from the last title segment, and no "
                   f"per-part quality_pages.")
        out.append("")
        return out

    def measure(self, works, compact: bool = False) -> tuple[int, int, int]:
        con = sqlite3.connect(":memory:")
        con.execute("""CREATE TABLE parts(work_id INTEGER NOT NULL, ord INTEGER NOT NULL,
            depth INTEGER NOT NULL, parent_ord INTEGER, page_title TEXT NOT NULL,
            display_title TEXT NOT NULL, kind TEXT NOT NULL, searchable INTEGER NOT NULL DEFAULT 0,
            bytes INTEGER, scan_from INTEGER, scan_to INTEGER, index_title TEXT,
            quality TEXT, quality_pages TEXT, complete INTEGER, PRIMARY KEY(work_id, ord))""")
        con.execute("CREATE INDEX parts_title ON parts(page_title)")
        rows, fts_rows = [], []
        for w in works:
            wid = w.root.rec.page_id if w.root.rec else -(abs(hash(w.root.title)) % 10**9) - 1
            pq = self.q[w.root.title][1]
            for p, q in zip(w.parts, pq):
                n = p.node
                title, display = n.title, n.display or n.title
                qp = json.dumps({str(k): v for k, v in q.pages.items()}) if q.pages else None
                if compact:
                    if title.startswith(w.root.title + "/"):
                        title = title[len(w.root.title):]
                    if display == n.title.rsplit("/", 1)[-1] or display == n.title:
                        display = ""
                    qp = None
                rows.append((wid, p.ord, p.depth, p.parent_ord, title, display, p.kind,
                             int(p.searchable), self.res.node_bytes(n), n.scan_from, n.scan_to, n.index,
                             q.tier, qp, int(q.complete)))
                if p.searchable:
                    fts_rows.append((n.display, w.root.title, ""))
        con.executemany("INSERT INTO parts VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)", rows)
        con.commit()
        size = len(gzip.compress(con.serialize(), 9))
        con.close()
        con = sqlite3.connect(":memory:")
        con.execute("CREATE VIRTUAL TABLE parts_fts USING fts5(display_title, work_title, author_names, content='')")
        con.executemany("INSERT INTO parts_fts VALUES (?,?,?)", fts_rows)
        con.commit()
        fts = len(gzip.compress(con.serialize(), 9))
        con.close()
        return len(rows), size, fts

    def named_section(self) -> list[str]:
        out = ["## 11. Named cases (PLAN.md WP1 acceptance, WS§9)", ""]
        names = NAMED.get(self.wiki, [])
        if not names:
            return out + ["None for this wiki.", ""]
        for t in names:
            t2 = self.res.resolve(t)
            if t != t2 and t2 in self.res.excluded:
                out.append(f"- `{esc(t)}`: redirect to `{esc(t2)}`, which is excluded "
                           f"({self.res.excluded[t2]}).")
            elif t in self.res.excluded:
                hits = [(w, p) for w in self.res.works for p in w.parts
                        if p.searchable and p.node.display.strip(" .") == t]
                out.append(f"- `{esc(t)}`: excluded ({self.res.excluded[t]})"
                           + (f"; search finds {len(hits)} text(s) with this title: " + "; ".join(
                               f"`{esc(p.node.title)}` in `{esc(w.root.title)}`" for w, p in hits[:6])
                              if hits else "") + ".")
            elif t2 in self.by_title:
                w = self.by_title[t2]
                q = self.wq(w)
                out.append(f"- `{esc(t)}`" + (f" (redirect to `{esc(t2)}`)" if t2 != t else "")
                           + f": work, {w.structure}, type {w.type}, {w.part_count:,} parts "
                           f"({w.text_count:,} texts), {len(w.missing)} missing, order "
                           f"{w.root.order_source or '–'}, oversized {w.oversized}, quality {q.tier} "
                           f"({q.source}), complete {q.complete}"
                           + (f" ({q.reason})" if q.reason else "")
                           + (f", **excluded: {q.excluded}**" if q.excluded else "") + ".")
            elif t2 in self.part_of:
                w, p = self.part_of[t2]
                out.append(f"- `{esc(t)}`: part {p.ord} of `{esc(w.root.title)}` ({p.kind}, "
                           f"display “{esc(p.node.display)}”, rule {p.node.rule}, searchable {p.searchable}).")
            elif t2 in self.ext["pages"]:
                out.append(f"- `{esc(t)}`: page exists but is not resolved (excluded root?).")
            else:
                hits = [(w, p) for w in self.res.works for p in w.parts
                        if p.searchable and p.node.display == t]
                if hits:
                    out.append(f"- `{esc(t)}`: no page; found as the text " + "; ".join(
                        f"`{esc(p.node.title)}` in `{esc(w.root.title)}`" for w, p in hits[:5]) + ".")
                else:
                    out.append(f"- `{esc(t)}`: no such page.")
        out.append("")
        return out

    def toc(self, w, limit: int = 12) -> list[str]:
        out = []
        for p in w.parts[:limit]:
            out.append(f"    {'  ' * p.depth}- {p.kind}: {esc(p.node.display or p.node.title)}"
                       + (f" (`{esc(p.node.title)}`)" if p.node.display != p.node.title else "")
                       + (f" [{p.node.rule}]" if p.ord else ""))
        if len(w.parts) > limit:
            out.append(f"    - … {len(w.parts) - limit:,} more")
        return out

    def samples_section(self, pats) -> list[str]:
        out = ["## 12. Random samples for eyeballing (30 per pattern)", ""]
        for key, label in (("A", "A: collections of root pages"), ("B", "B: mixed"),
                           ("D", "D: virtual roots"),
                           ("E", "E: subpage texts reached from a disambiguation or versions page"),
                           ("E*", "all searchable subpage texts (chapter titles that the label patterns miss show up here)"),
                           ("C", "C: oversized"), ("G", "G: reference works"), ("H", "H: deep nesting"),
                           ("I", "I: works with other editions")):
            items = pats[key]
            out.append(f"### {label} ({len(items):,})")
            out.append("")
            for it in self.rng.sample(items, min(30, len(items))):
                if key in ("E", "E*"):
                    w, p = it
                    out.append(f"1. {p.kind} “{esc(p.node.display)}” (`{esc(p.node.title)}`), part {p.ord} "
                               f"of `{esc(w.root.title)}` ({w.type}, {w.part_count:,} parts, {w.text_count:,} texts)")
                    continue
                w = it
                q = self.wq(w)
                out.append(f"1. `{esc(w.root.title)}`: {w.structure}, {w.type}, {w.part_count:,} parts, "
                           f"{len(w.missing)} missing, order {w.root.order_source or '–'}, {q.tier}"
                           + (f", excluded {q.excluded}" if q.excluded else ""))
                out += self.toc(w)
            out.append("")
        return out

    def notes_section(self) -> list[str]:
        return [
            "## 13. Implementation notes", "",
            "- When a page's container link names one of its title ancestors, the tree parent is "
            "its deepest existing ancestor below that container (is and en subpages often name "
            "the root, skipping an existing section page).",
            "- The Index TOC field (e.g. no `Sammendrag`) counts as TOC links only for the page "
            "its Index title field links to; otherwise every member would look TOC-like.",
            "- Display titles come from `current`/`section`/`TITEL`, else the link text in the "
            "parent's TOC, else the last title segment. A work's first part row is its root "
            "(`front` above 400 characters of own text, else `toc`); `part_count` excludes it.",
            "- Missing parts are red links in the TOCs of the work's root and volumes: subpages "
            "of the root always, other titles only in linked or mixed works and on TOC-like pages.",
            "- Junk rules (cleanup or deletion template, placeholder, under 300 bytes) apply to "
            "works without scan pages and without any quality signal.",
            "- Order (step 1c): when no source covers all children but the TOC lists at least "
            "half, the listed children keep the TOC order and the rest follow in natural order "
            "(`toc-partial`), so that collections are not shown alphabetically (WS§2 rule 3).",
            "- Rule 3: a TOC page is marked as one (de `HERKUNFT = off`) or is its Index's own "
            "book; \"reached from it by the prev/next chain\" is read as: the member's chain "
            "starts at the TOC page or its prev or next page is listed on the same TOC page.",
            "- A container with several links (de `HERKUNFT`) resolves to the first link that "
            "shares the page's Index, else the first link (the KHM tales link the editions "
            "overview first).",
            "- Volume placement: a part attached by its container is placed under the one TOC "
            "page (under 2,000 bytes of own text, listing at least three members) of the same "
            "work that lists it, unless the container's members already form one prev/next chain.",
            "- A slash inside unclosed parentheses is part of the name (de `X (Die Gartenlaube "
            "1871/17)`), not a subpage separator.",
            "- Exclusions: a page with scan transclusion is never a disambiguation page (fr "
            "dictionary roots carry the prop); subpages inherit only portal and author-page "
            "exclusions; on de, portal pages are marked by `Themendaten`, `Ortsdaten`, "
            "`Listendaten` (and the categories Thema, Orte, Listen when the page has no header), "
            "and a root page without header template, scans or status is a portal "
            "(`no-header`) unless other pages name it as their container.",
            "- A bare `<pages index=X/>` renders the Index's TOC field, not the book.",
            "",
        ]

    def write(self) -> Path:
        pats = self.patterns()
        lines = (self.header() + self.works_section() + self.exclusions_section()
                 + self.patterns_section(pats) + self.rules_section() + self.container_section()
                 + self.chain_section() + self.orphans_section() + self.prefix_section()
                 + self.quality_section() + self.size_section() + self.named_section()
                 + self.notes_section() + self.samples_section(pats))
        REPORT_DIR.mkdir(exist_ok=True)
        path = REPORT_DIR / f"{self.wiki}-structure.md"
        path.write_text("\n".join(lines) + "\n", "utf-8")
        return path

    def summary(self) -> dict:
        cat = self.in_catalog()
        tiers = Counter(self.wq(w).tier for w in cat)
        dv = [w for w in cat if self.wq(w).complete and self.wq(w).tier in ("validated", "proofread")
              and w.type != "reference"]
        du = [w for w in cat if self.wq(w).complete and self.wq(w).tier == "unknown" and w.type != "reference"]
        return dict(works=len(self.res.works), catalog=len(cat), tiers=dict(tiers),
                    default=len(dv), default_unknown=len(dv) + len(du),
                    structure=dict(Counter(w.structure for w in self.res.works)))


def main(wikis: list[str]) -> None:
    for w in wikis:
        s = Survey(w)
        path = s.write()
        print(f"{w}: {path} {json.dumps(s.summary())}", file=sys.stderr)


if __name__ == "__main__":
    main(sys.argv[1:])
