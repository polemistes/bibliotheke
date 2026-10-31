# SPDX-License-Identifier: GPL-3.0-or-later
"""Structure resolution: pages -> works and parts (PLAN.md 5.2 steps 1-1h).

Runs on the cached extract (extract.py). The app runs the same rules live on
Parsoid HTML (PLAN.md 7.1). Choices made where the plan is silent are marked
"Implementation choice" and listed in the survey report.
"""

from __future__ import annotations

import re
from collections import Counter, defaultdict
from dataclasses import dataclass, field

from . import config as configmod, wikitext as wt
from .extract import PageRec, Namespaces, resolve_target, page_key, UNSET

FILE_EXT = re.compile(r"\.(djvu|pdf|tiff?|jpe?g|png|gif|webp)$", re.I)
NATURAL = re.compile(r"(\d+)")


def parent_titles(title: str, strict: bool = False) -> list[str]:
    """Proper title prefixes at subpage separators, longest first.

    Implementation choice: a slash inside unclosed parentheses or brackets is
    part of the name, not a subpage separator (de "X (Die Gartenlaube 1871/17)").
    With strict=True (used when no ancestor page exists), a slash with a space
    on either side or between digits is punctuation too (de "Etatsjahr
    1885/86.", "vom 8./9. December", "Bartkram / Darinnen").
    """
    out, depth = [], 0
    for i, ch in enumerate(title):
        if ch in "([":
            depth += 1
        elif ch in ")]":
            depth = max(0, depth - 1)
        elif ch == "/" and depth == 0 and 0 < i < len(title) - 1:
            if strict:
                a, b = title[i - 1], title[i + 1]
                if a == " " or b == " " or ((a.isdigit() or a == ".") and b.isdigit()):
                    continue
            out.append(title[:i])
    return out[::-1]


def is_subpage(title: str) -> bool:
    """Has a strict subpage separator (see parent_titles)."""
    return bool(parent_titles(title, strict=True))


def natural_key(title: str):
    return [int(p) if p.isdigit() else p.lower() for p in NATURAL.split(title)]


@dataclass(slots=True)
class Node:
    title: str
    rec: PageRec | None                      # None for a virtual root
    container: str | None = None
    container_fail: str | None = None        # outside-ns0 | excluded | missing
    prev: str | None = None
    next: str | None = None
    indexes: tuple[str, ...] = ()
    index: str | None = None
    index_type: str | None = None            # canonical (book, collection, ...)
    index_type_raw: str | None = None
    header_tag: bool = False                 # <pages header=1>
    scan_from: int | None = None
    scan_to: int | None = None
    toc: list[str] = field(default_factory=list)
    toc_texts: dict[str, str] = field(default_factory=dict)
    toc_source: str = ""
    toc_pages: set = field(default_factory=set)   # scan pages that hold TOC links
    parent: str | None = None
    rule: str = "own"                        # container | hierarchy | toc | own
    children: list[str] = field(default_factory=list)
    order_source: str = ""
    display: str = ""
    current: str | None = None


@dataclass(slots=True)
class Part:
    node: Node
    ord: int
    depth: int
    parent_ord: int | None
    kind: str
    searchable: bool


@dataclass(slots=True)
class Work:
    root: Node
    parts: list[Part]
    is_virtual: bool
    structure: str
    part_count: int
    text_count: int
    missing: list[str]
    oversized: bool
    bytes_total: int
    type: str
    max_depth: int
    entry_title: str


class Resolver:
    def __init__(self, cfg: configmod.Config, ext: dict):
        self.cfg = cfg
        self.ext = ext
        self.pages: dict[str, PageRec] = ext["pages"]
        self.redirects: dict[str, str] = ext["redirects"]
        self.levels = ext["levels"]
        self.sizes = ext["sizes"]
        self.single = ext["single"]
        self.plinks = ext["plinks"]
        self.indexes = ext["indexes"]
        self.ns = Namespaces(cfg, ext["site_ns"])
        self.excluded: dict[str, str] = {}
        self.nodes: dict[str, Node] = {}
        self.virtual: set[str] = set()
        self.works: list[Work] = []
        self.stats: Counter = Counter()
        self.chain_cross = Counter()

    # ---- helpers -------------------------------------------------------
    def resolve(self, title: str | None) -> str | None:
        seen = 0
        while title in self.redirects and seen < 5:
            title = self.redirects[title]
            seen += 1
        return title

    def container_target(self, raw: str, n: Node) -> tuple[str | None, str | None]:
        """Container of n from a header value. Implementation choice: when the
        value links several existing pages, the first one sharing n's Index
        wins (de HERKUNFT "[[Kinder- und Hausmärchen]]. … [[… Band 1 (1843)|Band 1]]"
        links the editions overview first); otherwise the first link."""
        first, fail = None, None
        for i, (link, _) in enumerate(wt.links(raw)):
            t, f = self._target(link, n.title)
            if i == 0:
                fail = f
            if t is None:
                continue
            if first is None:
                first = t
            if n.indexes and set(n.indexes) & set(self.indexes_of.get(t, ())):
                return t, None
        return (first, None) if first else (None, fail)

    def link_target(self, raw: str | None, current: str) -> tuple[str | None, str | None]:
        """(resolved ns0 title, failure) of the first wikilink in raw."""
        link = wt.first_link(raw or "")
        if link is None:
            return None, None
        return self._target(link, current)

    def _target(self, link: str, current: str) -> tuple[str | None, str | None]:
        t = resolve_target(link, current, self.ns)
        if t is None:
            return None, "outside-ns0"
        t = self.resolve(t)
        if t == current:
            return None, None
        if t in self.excluded:
            return None, "excluded"
        if t not in self.pages:
            return None, "missing"
        return t, None

    def hdr(self, rec: PageRec, role: str) -> str | None:
        v = rec.hdr.get(role) or rec.hdr.get("tag_" + role)
        if v or role not in ("container",):
            return v
        for tag in rec.tags:
            if tag[5]:
                fields = self.indexes.get(tag[0]) or {}
                for p in self.cfg.container_params:
                    if fields.get(p):
                        return fields[p]
        return None

    def index_type(self, name: str | None) -> tuple[str | None, str | None]:
        fields = self.indexes.get(name or "") or {}
        for f in self.cfg.index_type_fields:
            raw = (fields.get(f) or "").strip().lower()
            if raw:
                return self.cfg.index_types.get(raw), raw
        return None, None

    def tag_pages(self, tag: tuple) -> list[tuple[str, int | None]]:
        """Scan pages (file, num) transcluded by one <pages> tag."""
        idx, fr, to, inc, exc, _ = tag
        f = idx
        def num(v):
            nonlocal f
            v = (v or "").strip()
            if "/" in v:
                ff, n = v.rsplit("/", 1)
                if n.strip().isdigit():
                    f = wt.norm_title(ff)
                    return int(n)
            return int(v) if v.isdigit() else None
        if not (fr or "").strip() and not (to or "").strip() and not (inc or "").strip():
            # A bare <pages index=X/> renders the Index's TOC field, not the
            # book (fr `Madame Bovary`: 57 chapter links, no page markers).
            return self.index_toc(idx)[0]
        a, b = num(fr), num(to)
        lv = self.levels.get(f)
        if a is None and b is None:
            if (inc or "").strip():
                nums = set()
            else:
                nums = set() if lv is None else {i for i, x in enumerate(lv) if x != UNSET}
        else:
            if a is None:
                a = 1
            if b is None:
                b = (len(lv) - 1) if lv is not None else a
            nums = set(range(a, b + 1)) if 0 <= b - a <= 20000 else set()
        nums |= wt.page_ranges(inc)
        nums -= wt.page_ranges(exc)
        return [(f, n) for n in sorted(nums)]

    def page_level(self, key: tuple[str, int | None]):
        """Level 0-4, 'missing', or None when the scan file is unknown."""
        f, n = key
        if n is None:
            if f in self.single:
                v = self.single[f][0]
                return 1 if v >= 5 else v
            return "missing" if FILE_EXT.search(f) else None
        lv = self.levels.get(f)
        if lv is None:
            return "missing" if FILE_EXT.search(f) else None
        if n < len(lv) and lv[n] != UNSET:
            return 1 if lv[n] >= 5 else lv[n]
        return "missing"

    def page_bytes(self, key) -> int:
        f, n = key
        if n is None:
            return self.single.get(f, (0, 0))[1]
        s = self.sizes.get(f)
        return s[n] if s is not None and n < len(s) else 0

    def scan_pages(self, rec: PageRec) -> list[tuple[str, int | None]]:
        out = []
        for tag in rec.tags:
            out.extend(self.tag_pages(tag))
        out.extend(rec.refs)
        return out

    # ---- step 0 --------------------------------------------------------
    def exclusions(self) -> None:
        cfg, disamb = self.cfg, self.ext["disamb"]
        headers = cfg.header_templates | frozenset(cfg.positional_headers)
        # Pages that other pages' headers name as their container are collection
        # roots: the heuristic portal rules below (category, no header) never
        # exclude them; an explicit portal template does.
        named = set()
        for t, rec in self.pages.items():
            raw = self.hdr(rec, "container")
            cands = [self.resolve(x) for x in (resolve_target(link, t, self.ns)
                                                for link, _ in wt.links(raw or "")) if x]
            cands = [c for c in cands if c in self.pages and c != t]
            if not cands:
                continue
            mine = set(self.page_indexes(rec))
            shared = [c for c in cands if mine & set(self.page_indexes(self.pages[c]))]
            named.add(shared[0] if shared else cands[0])
        for t, rec in self.pages.items():
            portal_ok = t not in named
            reason = None
            if (rec.page_id in disamb or rec.tpls & cfg.disambiguation_templates) \
                    and not rec.tags and not rec.refs:
                # A page that transcludes scans is not a disambiguation page
                # (fr dictionary roots carry the prop through {{Dct}}).
                reason = "disambiguation"
            elif rec.tpls & cfg.versions_templates:
                reason = "versions"
            elif rec.tpls & cfg.author_page_templates:
                reason = "author-page"
            elif cfg.exclude_title_prefixes and t.startswith(cfg.exclude_title_prefixes):
                reason = "reference-prefix"
            elif "/" in t and t.rsplit("/", 1)[1].lower() in cfg.whole_text_subpages:
                reason = "whole-text"
            elif rec.tpls & cfg.exclude_templates and not rec.tpls & headers:
                reason = "excluded-template"
            elif portal_ok and set(rec.cats) & cfg.exclude_categories and not rec.tpls & headers:
                reason = "excluded-category"
            elif (portal_ok and cfg.raw.get("exclude_headerless_roots") and ":" not in t
                  and not is_subpage(t) and not rec.tpls & headers and not rec.tags
                  and not rec.refs and not rec.hdr.get("status")):
                # Implementation choice (de): every de text has a header template
                # (WS§10.7), so a root page without header, scans or status is a
                # portal, topic or bibliography page (step 1 "portals in ns0").
                reason = "no-header"
            if reason:
                self.excluded[t] = reason
        # Subpages of an excluded page (author bibliographies, portal subpages)
        # are excluded with it, unless an existing ancestor below it is a text.
        inherit = {"author-page", "excluded-template", "excluded-category", "no-header"}
        for t in self.pages:
            if t in self.excluded:
                continue
            for p in parent_titles(t):
                if p in self.pages:
                    if self.excluded.get(p) in inherit:
                        self.excluded[t] = "under-excluded"
                    break

    # ---- step 1a -------------------------------------------------------
    def page_indexes(self, rec: PageRec) -> tuple[str, ...]:
        idx = []
        if rec.hdr.get("index"):
            idx.append(wt.norm_title(wt.strip_markup(rec.hdr["index"])))
        for tg in [tg for tg in rec.tags if tg[5]] + [tg for tg in rec.tags if not tg[5]]:
            if tg[0]:
                idx.append(tg[0])
        idx.extend(f for f, num in rec.refs if num is not None)
        return tuple(dict.fromkeys(i for i in idx if i))

    def records(self) -> None:
        self.indexes_of = {t: self.page_indexes(rec) for t, rec in self.pages.items()
                           if t not in self.excluded}
        for t, rec in self.pages.items():
            if t in self.excluded:
                continue
            n = Node(t, rec)
            n.indexes = self.indexes_of[t]
            raw = self.hdr(rec, "container")
            if raw:
                n.container, n.container_fail = self.container_target(raw, n)
            for role in ("prev", "next"):
                tgt, _ = self.link_target(self.hdr(rec, role), t)
                setattr(n, role, tgt)
            cur = self.hdr(rec, "current")
            if cur:
                n.current = wt.strip_markup(cur)[:200] or None
            n.header_tag = any(tg[5] for tg in rec.tags)
            nums = [r for r in rec.refs if r[1] is not None]
            n.index = n.indexes[0] if n.indexes else None
            n.index_type, n.index_type_raw = self.index_type(n.index)
            pages = []
            for tg in rec.tags:
                pages = self.tag_pages(tg)
                if pages:
                    break
            if not pages and nums:
                pages = [r for r in nums if r[0] == nums[0][0]]
            nn = [p[1] for p in pages if p[1] is not None]
            if nn:
                n.scan_from, n.scan_to = min(nn), max(nn)
            self.toc_of(n)
            self.nodes[t] = n

    def index_toc(self, idx: str | None, current: str = "") -> tuple[list, list]:
        """Scan pages transcluded by, and (target, text) links in, the TOC
        field of Index idx."""
        keys, pairs = [], []
        fields = self.indexes.get(idx or "") or {}
        for fname in self.cfg.toc_fields:
            v = fields.get(fname)
            if not v:
                continue
            for m in wt.TEMPLATE_START.finditer(v):
                name = m.group(1).strip().lstrip(":")
                if name.lower().startswith(self.ns.page_prefixes):
                    key = page_key(name.split(":", 1)[1])
                    keys.append(key)
                    pairs.extend(self.plinks.get(key) or ())
            for raw, text in wt.links(v):
                t = resolve_target(raw, current, self.ns)
                if t:
                    pairs.append((t, text))
        return keys, pairs

    def links_to_self(self, n: Node) -> bool:
        link = wt.first_link(self.hdr(n.rec, "container") or "")
        t = resolve_target(link, n.title, self.ns) if link else None
        return t is not None and self.resolve(t) == n.title

    def toc_of(self, n: Node) -> None:
        """Ordered ns0 TOC links: Index TOC field (header=1), transcluded scan
        pages with links, then the page's own body links."""
        rec = n.rec
        out: dict[str, str | None] = {}
        sources = []
        def add(pairs, src):
            got = False
            for tgt, text in pairs:
                tgt = self.resolve(tgt)
                if tgt and tgt != n.title and tgt not in out:
                    out[tgt] = text
                    got = True
            if got:
                sources.append(src)
        # The Index TOC field belongs to the Index's own book: the page its
        # title field links to (otherwise every member would look TOC-like),
        # and any page with a bare <pages index=X/>, which renders it.
        bare = [tg[0] for tg in rec.tags if not tg[1].strip() and not tg[2].strip() and not tg[3].strip()]
        toc_indexes = list(dict.fromkeys(
            bare + ([n.index] if n.header_tag and self.links_to_self(n) else [])))
        for idx in toc_indexes:
            keys, pairs = self.index_toc(idx, n.title)
            n.toc_pages.update(keys)
            add(pairs, "summary")
        pairs = []
        for key in self.scan_pages(rec):
            pl = self.plinks.get(key)
            if pl:
                n.toc_pages.add(key)
                pairs.extend(pl)
        add(pairs, "scan-pages")
        own = zip(rec.links, rec.texts or [None] * len(rec.links))
        add(own, "summary" if rec.tpls & self.cfg.summary_templates else "body-links")
        n.toc = list(out)
        n.toc_texts = {k: v for k, v in out.items() if v}
        n.toc_source = "+".join(dict.fromkeys(sources))

    # ---- step 1b -------------------------------------------------------
    def existing_prefix(self, title: str) -> str | None:
        for p in parent_titles(title):
            if p in self.nodes:
                return p
        return None

    def assign_parents(self) -> None:
        nodes = self.nodes
        # TOC-like root pages (rule 3): marked as TOC (de HERKUNFT = off) or
        # the Index's own book (its title field links the page).
        listed: dict[str, list[str]] = defaultdict(list)
        self.toc_like = set()
        for n in nodes.values():
            if self.existing_prefix(n.title) or is_subpage(n.title):
                continue
            targets = [t for t in n.toc if t not in self.excluded]
            if len(targets) >= 3 and (n.rec.hdr.get("toc_marker")
                                      or (n.index_type_raw and self.links_to_self(n))):
                self.toc_like.add(n.title)
                for t in targets:
                    if t in nodes and not is_subpage(t):
                        listed[t].append(n.title)
        # "Reached from it by the prev/next chain": the chain runs from the TOC
        # page (the first member names it as prev, de VORIGER) or through its
        # members (the page's prev or next is listed on the same TOC page, de
        # Gartenlaube articles); a chain of separate works that merely cite
        # each other (UN resolutions) does not qualify.
        prev_of: dict[str, list[str]] = defaultdict(list)
        for n in nodes.values():
            if n.prev:
                prev_of[n.prev].append(n.title)
        def reach(toc: str) -> set[str]:
            seen, todo = set(), list(prev_of.get(toc, ())) + ([nodes[toc].next] if nodes[toc].next else [])
            while todo and len(seen) < 20000:
                cur = todo.pop()
                if cur in seen or cur not in nodes or cur == toc:
                    continue
                seen.add(cur)
                if nodes[cur].next:
                    todo.append(nodes[cur].next)
            return seen
        reach_cache: dict[str, set[str]] = {}
        toc_sets: dict[str, set[str]] = {}
        for n in nodes.values():
            t = n.title
            if n.container:
                c = n.container
                n.rule = "container"
                # Implementation choice: when the container is a title ancestor,
                # keep the deepest existing ancestor below it as the tree parent.
                if t.startswith(c + "/"):
                    n.parent = self.existing_prefix(t) or c
                else:
                    n.parent = c
            elif self.existing_prefix(t) or is_subpage(t):
                n.rule = "hierarchy"
                p = self.existing_prefix(t)
                if p is None:
                    p = parent_titles(t, strict=True)[-1]
                    self.virtual.add(p)
                n.parent = p
            elif len(listed.get(t, ())) == 1:
                toc = nodes[listed[t][0]]
                if toc.title not in reach_cache:
                    reach_cache[toc.title] = reach(toc.title)
                members = toc_sets.setdefault(toc.title, set(toc.toc))
                chained = (t in reach_cache[toc.title] or n.prev in members or n.next in members)
                if set(n.indexes) & set(toc.indexes) or chained:
                    n.rule = "toc"
                    n.parent = toc.title
        for v in self.virtual:
            vn = Node(v, None, rule="virtual")
            nodes[v] = vn
        self._break_cycles()
        self._place_under_volumes()
        for n in nodes.values():
            if n.parent:
                nodes[n.parent].children.append(n.title)

    def ancestors(self, title: str) -> list[str]:
        out, cur, seen = [], self.nodes[title].parent, set()
        while cur is not None and cur not in seen and len(out) < 20:
            seen.add(cur); out.append(cur); cur = self.nodes[cur].parent
        return out

    def _place_under_volumes(self) -> None:
        """Implementation choice: a page attached by its container stays in
        that work, but when exactly one other page inside the same work lists
        it in its TOC (a volume page), it is placed under that page (no
        `Historisk Tidsskrift`: 366 articles name the periodical root, the
        volume pages list them)."""
        nodes = self.nodes
        listed: dict[str, list[str]] = defaultdict(list)
        for d in nodes.values():
            if d.rec is None or not d.toc or d.parent is None:
                continue
            if self.own_text(d) >= 2000:     # a chapter with a navigation list, not a TOC page
                continue
            # a volume page lists several members; a page that only links its
            # neighbours (prev/next in the body) is not one
            members = [t for t in d.toc if t not in (d.prev, d.next) and t in nodes
                       and nodes[t].rule == "container" and nodes[t].parent != d.title]
            if len(members) < 3:
                continue
            for t in members:
                listed[t].append(d.title)
        members_of: dict[str, list[str]] = defaultdict(list)
        for n in nodes.values():
            if n.rule == "container" and n.parent:
                members_of[n.parent].append(n.title)
        chained = {p for p, ms in members_of.items() if len(ms) > 1 and self.chain_order(ms)}
        for t, ds in listed.items():
            tn = nodes[t]
            if tn.parent in chained:      # the flat chain is the reading order (de Sagenschatz)
                continue
            cands = [d for d in ds if tn.parent in self.ancestors(d) and t not in self.ancestors(d)]
            if len(cands) == 1:
                tn.parent = cands[0]
                self.stats["placed-under-volume"] += 1

    def _break_cycles(self) -> None:
        nodes = self.nodes
        for start in list(nodes):
            path, seen, hops = [], set(), 0
            cur = start
            while cur is not None and cur not in seen:
                seen.add(cur)
                path.append(cur)
                node = nodes[cur]
                if node.rule == "container":
                    hops += 1
                if hops > 6 or len(path) > 15:
                    break
                cur = node.parent
            if cur is not None and (cur in seen or hops > 6 or len(path) > 15):
                cut = nodes[path[-1]]
                self.stats["cycles-broken" if cur in seen else "depth-capped"] += 1
                if is_subpage(cut.title) and cut.rule == "container":
                    p = self.existing_prefix(cut.title)
                    cut.parent, cut.rule = p, ("hierarchy" if p else "own")
                    if p is None:
                        cut.parent = None
                else:
                    cut.parent, cut.rule = None, "own"

    # ---- step 1c -------------------------------------------------------
    def chain_order(self, kids: list[str]) -> list[str] | None:
        """kids in prev/next chain order when the next links or the prev links
        alone chain all of them, else None."""
        ks = set(kids)
        for links in ("next", "prev"):
            succ = {}
            for k in kids:
                o = getattr(self.nodes[k], links)
                if o in ks and o != k:
                    a, b = (k, o) if links == "next" else (o, k)
                    succ.setdefault(a, b)
            if not succ:
                continue
            targets = set(succ.values())
            heads = [k for k in kids if k not in targets]
            if len(heads) == 1:
                seq, cur, seen = [], heads[0], set()
                while cur is not None and cur not in seen:
                    seen.add(cur); seq.append(cur); cur = succ.get(cur)
                if len(seq) == len(kids):
                    return seq
        return None

    def order(self, n: Node) -> None:
        kids = n.children
        if len(kids) <= 1:
            n.order_source = "single" if kids else ""
            return
        seq = self.chain_order(kids)
        if seq:
            n.children, n.order_source = seq, "chain"
            return
        if n.toc:
            pos = {t: i for i, t in enumerate(n.toc)}
            if all(k in pos for k in kids):
                n.children, n.order_source = sorted(kids, key=pos.__getitem__), "toc"
                return
        idxs = {self.nodes[k].index for k in kids}
        if len(idxs) == 1 and None not in idxs and all(self.nodes[k].scan_from is not None for k in kids):
            n.children = sorted(kids, key=lambda k: (self.nodes[k].scan_from, natural_key(k)))
            n.order_source = "scan"
            return
        # Implementation choice: when no source covers all children but the
        # TOC lists at least half of them, the listed ones keep the TOC order
        # and the rest follow in natural order (WS§2 rule 3: reading order,
        # not alphabetical; de Gartenlaube volumes, Heine's Gedichte).
        if n.toc:
            pos = {t: i for i, t in enumerate(n.toc)}
            listed = [k for k in kids if k in pos]
            if len(listed) * 2 >= len(kids):
                rest = sorted((k for k in kids if k not in pos), key=natural_key)
                n.children = sorted(listed, key=pos.__getitem__) + rest
                n.order_source = "toc-partial"
                return
        n.children, n.order_source = sorted(kids, key=natural_key), "natural"

    # ---- step 1d-1h ----------------------------------------------------
    def display(self, n: Node, parent: Node | None) -> str:
        # A `current`/TITEL value shared by siblings or repeating the parent's
        # title names the book, not the part (de subpage books).
        if n.current and not (parent is not None and (
                n.current in (parent.current, parent.display, parent.title)
                or self.shared_current.get(parent.title, {}).get(n.current, 0) > 1)):
            return n.current
        if parent is not None and n.title in parent.toc_texts:
            t = wt.strip_markup(parent.toc_texts[n.title])
            if t:
                return t[:200]
        return parent_titles(n.title) and n.title[len(parent_titles(n.title)[0]) + 1:] or n.title

    def own_text(self, n: Node) -> int:
        if n.rec is None:
            return 0
        total = n.rec.own_chars
        for key in self.scan_pages(n.rec):
            if key in n.toc_pages:
                continue
            lvl = self.page_level(key)
            if isinstance(lvl, int) and lvl >= 1:
                total += self.page_bytes(key)
        return total

    def node_bytes(self, n: Node) -> int:
        if n.rec is None:
            return 0
        return n.rec.bytes + sum(self.page_bytes(k) for k in self.scan_pages(n.rec))

    def build_works(self) -> None:
        cfg, nodes = self.cfg, self.nodes
        self.shared_current = {}
        for n in nodes.values():
            self.order(n)
            if len(n.children) > 1:
                self.shared_current[n.title] = Counter(
                    nodes[c].current for c in n.children if nodes[c].current)
        for root in nodes.values():
            if root.parent is not None:
                continue
            parts: list[Part] = []
            is_virtual = root.rec is None
            root.display = root.title
            if not root.children:
                parts.append(Part(root, 0, 0, None, "text", False))
            else:
                kind = "front" if self.own_text(root) > 400 else "toc"
                if not is_virtual:
                    parts.append(Part(root, 0, 0, None, kind, False))
                stack = [(c, 0, None, root) for c in reversed(root.children)]
                while stack:
                    t, depth, pord, parent = stack.pop()
                    nd = nodes[t]
                    nd.display = self.display(nd, parent)
                    if nd.children:
                        k, s = "volume", False
                    else:
                        s = not cfg.chapter_label.search(nd.display.strip())
                        k = "text" if s else "chapter"
                    part = Part(nd, len(parts), depth, pord, k, s)
                    parts.append(part)
                    for c in reversed(nd.children):
                        stack.append((c, depth + 1, part.ord, nd))
            members = [p for p in parts if p.node is not root]
            sub = sum(1 for p in members if p.node.title.startswith(root.title + "/"))
            if is_virtual:
                structure = "virtual"
            elif not members:
                structure = "single"
            elif sub == len(members):
                structure = "subpages"
            elif sub == 0:
                structure = "linked"
            else:
                structure = "mixed"
            missing = self.missing_parts(root, parts, structure)
            nbytes = sum(self.node_bytes(p.node) for p in parts)
            oversized = any(self.node_bytes(p.node) > cfg.oversized_bytes for p in parts)
            text_count = sum(1 for p in members if p.searchable)
            part_count = len(members) or 1
            itype = root.index_type or (members[0].node.index_type if is_virtual and members else None)
            if itype:
                wtype = itype
            elif part_count > cfg.reference_threshold:
                wtype = "reference"
            elif not members:
                wtype = "single"
            else:
                leaves = [p for p in members if p.kind in ("text", "chapter")]
                wtype = "collection" if leaves and text_count * 2 >= len(leaves) else "book"
            self.works.append(Work(
                root=root, parts=parts, is_virtual=is_virtual, structure=structure,
                part_count=part_count, text_count=text_count, missing=missing,
                oversized=oversized, bytes_total=nbytes, type=wtype,
                max_depth=max((p.depth for p in members), default=-1),
                entry_title=members[0].node.title if is_virtual and members else root.title))

    def missing_parts(self, root: Node, parts: list[Part], structure: str) -> list[str]:
        """Red links in the TOCs of the work: subpages of the root always;
        other titles only on TOC-like pages and in linked or mixed works."""
        out = {}
        linked = structure in ("linked", "mixed") or root.title in self.toc_like
        for p in parts:
            nd = p.node
            if not nd.children and p.node is not root:
                continue
            for t in nd.toc:
                if t in self.pages or t in self.excluded:
                    continue
                if t.startswith(root.title + "/") or (linked and not is_subpage(t)):
                    out.setdefault(t, None)
        return list(out)

    def run(self) -> "Resolver":
        self.exclusions()
        self.records()
        self.assign_parents()
        self.build_works()
        for n in self.nodes.values():
            if n.next and n.next in self.nodes and n.index and self.nodes[n.next].index:
                self.chain_cross["same" if n.index == self.nodes[n.next].index else "cross"] += 1
        return self


def resolve(wiki: str, ext: dict | None = None) -> Resolver:
    from . import extract
    cfg = configmod.load(wiki)
    return Resolver(cfg, ext if ext is not None else extract.load(wiki)).run()
