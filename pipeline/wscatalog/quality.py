# SPDX-License-Identifier: GPL-3.0-or-later
"""Quality and completeness (PLAN.md 5.4, WS§10): tiers, `complete`, hard
exclusions, per work and per part."""

from __future__ import annotations

from collections import Counter
from dataclasses import dataclass, field

from . import wikitext as wt
from .structure import Resolver, Work, Node

RANK = {"validated": 3, "proofread": 2, "unproofread": 1}


@dataclass(slots=True)
class Quality:
    tier: str = "unknown"                 # validated | proofread | unproofread | unknown
    source: str = "none"                  # page-levels | status-template | textquality | index-progress | none
    pages: Counter = field(default_factory=Counter)   # keys 0-4 and 'missing'
    complete: bool = True
    reason: str | None = None             # incomplete_reason
    missing_pages: int = 0
    coverage: float | None = None
    own_translation: bool = False
    excluded: str | None = None           # hard exclusion reason
    mostly: bool = False                  # proofread by the >= 90 % rule
    unresolved: int = 0                   # scan pages of unknown files


def tier_from_pages(c: Counter) -> tuple[str | None, bool]:
    text = {k: v for k, v in c.items() if k in (1, 2, 3, 4)}
    n = sum(text.values())
    if not n:
        return None, False
    if n == text.get(4, 0):
        return "validated", False
    good = text.get(3, 0) + text.get(4, 0)
    if good == n:
        return "proofread", False
    if good >= 0.9 * n:
        return "proofread", True
    return "unproofread", False


def ladder(values: list[str]) -> str | None:
    """Tier of a set of parts that each carry a status or template value."""
    vals = [v for v in values if v in RANK]
    if not vals:
        return None
    if all(v == "validated" for v in vals):
        return "validated"
    good = sum(1 for v in vals if RANK[v] >= 2)
    if good == len(vals) or good >= 0.9 * len(vals):
        return "proofread"
    return "unproofread"


class Assessor:
    def __init__(self, res: Resolver):
        self.res = res
        self.cfg = res.cfg

    def node_pages(self, n: Node) -> tuple[Counter, int, int]:
        """Level counts of the scan pages a page transcludes, the number of
        missing pages and of pages in unknown scan files."""
        c, missing, unresolved = Counter(), 0, 0
        if n.rec is None:
            return c, 0, 0
        for key in self.res.scan_pages(n.rec):
            lvl = self.res.page_level(key)
            if lvl is None:
                unresolved += 1
            elif lvl == "missing":
                c["missing"] += 1
                missing += 1
            else:
                c[lvl] += 1
        return c, missing, unresolved

    def status(self, n: Node) -> str | None:
        raw = (n.rec.hdr.get("status") or "").strip().lower() if n.rec else ""
        return self.cfg.status_values.get(raw) if raw else None

    def textquality(self, n: Node) -> str | None:
        raw = (n.rec.hdr.get("textquality") or "").strip().lower() if n.rec else ""
        return self.cfg.textquality_values.get(raw) if raw else None

    def progress(self, n: Node) -> tuple[str | None, str | None]:
        fields = self.res.indexes.get(n.index or "") or {}
        for f in self.cfg.index_progress_fields:
            raw = (fields.get(f) or "").strip()
            if raw:
                return self.cfg.index_progress_values.get(raw.upper()), raw
        return None, None

    def assess_node(self, n: Node) -> Quality:
        q = Quality()
        q.pages, q.missing_pages, q.unresolved = self.node_pages(n)
        self._tier(q, [n])
        return q

    def _tier(self, q: Quality, nodes: list[Node]) -> None:
        t, mostly = tier_from_pages(q.pages)
        if t:
            q.tier, q.source, q.mostly = t, "page-levels", mostly
            return
        st = [s for s in (self.status(n) for n in nodes) if s]
        t = ladder(st)
        if t:
            q.tier, q.source = t, "status-template"
            return
        t = ladder([v for v in (self.textquality(n) for n in nodes) if v])
        if t:
            q.tier, q.source = t, "textquality"
            return
        for n in nodes:
            p, _ = self.progress(n)
            if p in RANK:
                q.tier, q.source = p, "index-progress"
                return

    def assess(self, w: Work) -> tuple[Quality, list[Quality]]:
        cfg = self.cfg
        part_q = [self.assess_node(p.node) for p in w.parts]
        q = Quality()
        for pq in part_q:
            q.pages.update(pq.pages)
            q.missing_pages += pq.missing_pages
            q.unresolved += pq.unresolved
        nodes = [p.node for p in w.parts]
        self._tier(q, nodes)
        root = w.root if w.root.rec else (nodes[0] if nodes else w.root)
        prog, _ = self.progress(root)
        for p, pq in zip(w.parts, part_q):
            pq.complete = not (self._incomplete_template(p.node))
        # completeness (PLAN.md 5.4), first rule that applies
        if any(self._incomplete_template(n) for n in nodes):
            q.complete, q.reason = False, "template"
        elif w.missing:
            q.complete, q.reason = False, "missing-parts"
        elif q.missing_pages:
            q.complete, q.reason = False, "missing-pages"
        elif prog == "incomplete":
            q.complete, q.reason = False, "index-progress"
        if w.root.rec is not None and w.root.index_type == "book" and self.res.links_to_self(w.root):
            q.coverage = self.coverage(w)
            if q.coverage is not None and q.coverage < 0.95 and q.complete:
                q.complete, q.reason = False, "coverage"
        for pq, p in zip(part_q, w.parts):
            if pq.missing_pages:
                pq.complete = False
        tr = [(n.rec.hdr.get("translator") or "") for n in nodes if n.rec]
        q.own_translation = (any(wt.strip_markup(v).strip() in cfg.own_translation_values for v in tr if v)
                             or any(n.rec.tpls & cfg.own_translation_templates for n in nodes if n.rec))
        q.excluded = self.hard_exclusion(w, q, part_q, prog)
        return q, part_q

    def _incomplete_template(self, n: Node) -> bool:
        if n.rec is None:
            return False
        return bool(n.rec.tpls & self.cfg.incomplete_templates) or self.status(n) == "incomplete"

    def coverage(self, w: Work) -> float | None:
        f = w.root.index
        lv = self.res.levels.get(f or "")
        if lv is None:
            return None
        existing = {i for i, x in enumerate(lv) if x in (1, 2, 3, 4)}
        if not existing:
            return None
        used = set()
        for p in w.parts:
            if p.node.rec:
                used.update(n for ff, n in self.res.scan_pages(p.node.rec) if ff == f)
        return len(used & existing) / len(existing)

    def hard_exclusion(self, w: Work, q: Quality, part_q: list[Quality], prog) -> str | None:
        """PLAN.md 5.4 hard exclusions; junk rules apply to typed-in texts."""
        has_scan = any(pq.pages for pq in part_q)
        text_pages = sum(v for k, v in q.pages.items() if k in (1, 2, 3, 4))
        own = sum(p.node.rec.own_chars for p in w.parts if p.node.rec)
        if has_scan and not text_pages and not q.unresolved and own < 50:
            return "no-text-pages"
        if prog == "faulty":
            return "index-faulty"
        if q.tier == "unknown" and not has_scan:
            tpls = set().union(*(p.node.rec.tpls for p in w.parts if p.node.rec))
            if tpls & self.cfg.junk_templates:
                return "junk-template"
            if any(p.node.rec.flags & 1 for p in w.parts if p.node.rec):
                return "placeholder"
            if w.bytes_total < 300:
                return "under-300-bytes"
        if not has_scan and not q.unresolved and own < 50 and not w.missing:
            return "no-text"
        return None
