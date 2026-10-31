# SPDX-License-Identifier: GPL-3.0-or-later
"""One pass over a pages-articles dump: the raw structure and quality signals.

Keeps, per main-namespace page, the header parameters, <pages> tags, scan
page transclusions, ns0 links in document order, the templates of interest
and sizes; per Page: page its ProofreadPage level and size (and its links
when it holds three or more ns0 links, since collection TOCs often live in
scan pages); per Index page its fields. Resolution happens afterwards
(structure.py), on the cached result.

    python -I -m wscatalog.extract no        # writes pipeline/cache/extract/no.pickle
"""

from __future__ import annotations

import gzip
import json
import multiprocessing as mp
import pickle
import re
import subprocess
import sys
import time
from array import array
from collections import Counter
from pathlib import Path
import xml.etree.ElementTree as ET

from . import config as configmod, dumps, wikitext as wt
from .http import CACHE_DIR

EXTRACT_DIR = CACHE_DIR / "extract"
# Bump when the extract's content changes, so that wscatalog.build re-extracts.
EXTRACT_VERSION = 6
REPO_DIR = Path(__file__).resolve().parents[2]
CANONICAL_NS = {
    "media": -2, "special": -1, "talk": 1, "user": 2, "user talk": 3, "project": 4,
    "project talk": 5, "file": 6, "image": 6, "file talk": 7, "mediawiki": 8,
    "mediawiki talk": 9, "template": 10, "template talk": 11, "help": 12,
    "help talk": 13, "category": 14, "category talk": 15, "page": None, "index": None,
    "author": None, "portal": 100, "module": 828, "translation": 114,
}
INTERWIKI = {"w", "wikipedia", "wikt", "wiktionary", "q", "wikiquote", "b", "wikibooks",
             "n", "wikinews", "v", "wikiversity", "voy", "s", "wikisource", "c", "commons",
             "d", "wikidata", "m", "meta", "mw", "species", "wikispecies", "foundation",
             "wmf", "phab", "oldwikisource", "mul", "sources", "incubator", "outreach",
             "wikilivres", "gutenberg", "iarchive", "google", "doi", "isbn", "arxiv"}
LANG_PREFIX = re.compile(r"[a-z]{2,3}(-[a-z]{2,8})*$")
UNSET = 255
META_ROLES = frozenset(("author", "year", "translator", "description", "image", "genre"))


class PageRec:
    """Raw signals of one ns0 page (non-redirect)."""
    __slots__ = ("title", "page_id", "bytes", "hdr", "tags", "refs", "links", "texts",
                 "cats", "tpls", "own_chars", "flags", "snippet", "rev", "ts")

    def __init__(self, title: str, page_id: int, nbytes: int):
        self.title = title
        self.page_id = page_id
        self.bytes = nbytes
        self.hdr: dict[str, str] = {}
        self.tags: list[tuple] = []          # (index, from, to, include, exclude, header, sections)
        self.refs: list[tuple] = []          # (file, num|None) via templates / {{Page:..}}
        self.links: tuple[str, ...] = ()     # ns0 targets, document order, unique
        self.texts: tuple[str | None, ...] = ()
        self.cats: tuple[str, ...] = ()
        self.tpls: frozenset[str] = frozenset()
        self.own_chars = 0
        self.flags = 0                        # bit 0: placeholder
        self.snippet = ""                     # start of the text, typed-in pages only
        self.rev = 0                          # revision id
        self.ts = ""                          # revision timestamp


class Namespaces:
    def __init__(self, cfg: configmod.Config, site_ns: dict[int, str]):
        self.by_name: dict[str, int] = {}
        for num, name in site_ns.items():
            if name:
                self.by_name[name.lower()] = num
        nsjson = REPO_DIR / "docs" / "wp0" / "namespaces.json"
        if nsjson.exists():
            info = json.loads(nsjson.read_text("utf-8")).get(cfg.wiki) or {}
            for num, n in (info.get("namespaces") or {}).items():
                for name in [n.get("name"), n.get("canonical"), *(n.get("aliases") or [])]:
                    if name:
                        self.by_name[name.lower()] = int(num)
        for name, num in CANONICAL_NS.items():
            if num is None:
                num = {"page": cfg.ns.get("page"), "index": cfg.ns.get("index"),
                       "author": cfg.ns.get("author")}[name]
            if num is not None:
                self.by_name.setdefault(name, num)
        self.page_ns = cfg.ns.get("page")
        self.index_ns = cfg.ns.get("index")
        self.page_prefixes = tuple(sorted((n + ":" for n, v in self.by_name.items()
                                           if v == self.page_ns), key=len, reverse=True))

    def split(self, target: str) -> tuple[int | None, str]:
        """(namespace, rest) of a link target; None namespace = interwiki."""
        if ":" not in target:
            return 0, target
        prefix, rest = target.split(":", 1)
        p = prefix.strip().lower().replace("_", " ")
        if p in self.by_name:
            return self.by_name[p], rest
        if p in INTERWIKI or (LANG_PREFIX.match(prefix.strip()) and prefix.strip().islower()):
            return None, rest
        return 0, target


def resolve_target(raw: str, current: str, ns: Namespaces) -> str | None:
    """Normalised ns0 title of a link target seen on page current, else None."""
    t = raw.strip()
    if not t:
        return None
    if t.startswith(":"):
        t = t[1:].strip()
    t = t.split("#", 1)[0].strip()
    if not t:
        return None
    if t.startswith("/"):
        t = current + t.rstrip("/")
    elif t.startswith("../"):
        base = current
        while t.startswith("../"):
            if "/" not in base:
                return None
            base = base.rsplit("/", 1)[0]
            t = t[3:]
        t = base + ("/" + t.rstrip("/") if t.strip("/") else "")
    num, _ = ns.split(t)
    if num != 0:
        return None
    t = wt.norm_title(t)
    return t or None


def page_key(name: str) -> tuple[str, int | None]:
    """(file, number) of a Page: title without its namespace prefix."""
    name = wt.norm_title(name)
    if "/" in name:
        f, n = name.rsplit("/", 1)
        if n.isdigit():
            return f, int(n)
    return name, None


def _plain_len(text: str, spans: list[tuple[int, int]]) -> int:
    """Approximate length of the page's own text outside header templates."""
    if spans:
        parts, i = [], 0
        for s, e in sorted(spans):
            parts.append(text[i:s]); i = max(i, e)
        parts.append(text[i:])
        text = "".join(parts)
    t = re.sub(r"<pages\b[^>]*>", " ", text, flags=re.I)
    t = re.sub(r"\[\[[^\[\]]*\]\]", " ", t)                   # links, categories, files
    t = re.sub(r"\{\{\s*[^{}|]*\|?", " ", t).replace("}}", " ")
    t = re.sub(r"<[^>]+>|__[A-Z]+__|'{2,}|\[https?://\S*|&\w+;", " ", t)
    t = re.sub(r"^[\s*#:;=|!{}\-]+", "", t, flags=re.M)
    return len(re.sub(r"\s+", "", t))


def parse_ns0(title: str, page_id: int, nbytes: int, text: str, cfg: configmod.Config,
              ns: Namespaces, interest: frozenset[str], pos_names: dict) -> PageRec:
    rec = PageRec(title, page_id, nbytes)
    text = wt.clean(text)
    names = wt.template_names(text)
    rec.tpls = frozenset(sys.intern(n) for n in names
                         if n and n[0] not in "#:" and len(n) <= 80
                         and not n.lower().startswith(ns.page_prefixes))
    hdr = rec.hdr
    header_spans: list[tuple[int, int]] = []

    wanted = (cfg.header_templates | frozenset(pos_names)) & names
    for name, params, s, e in wt.templates(text, wanted) if wanted else ():
        header_spans.append((s, e))
        if name in pos_names:
            spec = pos_names[name]
            root = params.get(str(spec.get("container", 0)), "")
            if root and "container" not in hdr:
                hdr["container"] = f"[[{root}]]"
            for role in ("prev", "next"):
                v = params.get(str(spec.get(role, 0)), "")
                if v and role not in hdr:
                    hdr[role] = f"[[{root}/{v}]]" if spec.get("relative") and root else f"[[{v}]]"
            continue
        for role, plist in (("container", cfg.container_params), ("current", cfg.current_params),
                            ("index", cfg.index_params), ("status", cfg.status_params),
                            ("author", cfg.author_params), ("year", cfg.year_params),
                            ("translator", cfg.translator_params),
                            ("description", cfg.description_params), ("image", cfg.image_params),
                            ("genre", cfg.genre_params)):
            for p in plist:
                v = params.get(p, "")
                if v and role in META_ROLES and v.strip().casefold() in cfg.header_placeholders:
                    continue        # "off", "o. J.": try the next parameter (de ENTSTEHUNGSJAHR)
                if v and role not in hdr:
                    hdr[role] = v
                    break
        for pp, np_ in cfg.prev_next_params:
            for role, p in (("prev", pp), ("next", np_)):
                v = params.get(p, "")
                if v and role not in hdr:
                    hdr[role] = f"[[{v}]]" if p in cfg.plain_title_params and "[[" not in v else v
        if cfg.toc_marker and params.get(cfg.toc_marker[0], "").strip().lower() == cfg.toc_marker[1]:
            hdr["toc_marker"] = "1"
    for name, params, s, e in (wt.templates(text, cfg.textquality_templates & names)
                               if cfg.textquality_templates & names else ()):
        v = next((params[p] for p in cfg.textquality_params if params.get(p)), "")
        if v:
            hdr.setdefault("textquality", v.strip())

    for attrs in wt.pages_tags(text):
        idx = wt.norm_title(attrs.get("index", ""))
        header = attrs.get("header", "").lower() in ("1", "yes", "true", "y")
        sections = "|".join(attrs.get(k, "") for k in ("fromsection", "tosection", "onlysection"))
        rec.tags.append((idx, attrs.get("from", ""), attrs.get("to", ""),
                         attrs.get("include", ""), attrs.get("exclude", ""), header,
                         sections.strip("|")))
        if header:
            for role, plist in (("tag_container", cfg.container_params),
                                ("tag_current", cfg.current_params)):
                for p in plist:
                    if attrs.get(p):
                        hdr.setdefault(role, attrs[p])
            for pp, np_ in cfg.prev_next_params:
                if attrs.get(pp):
                    hdr.setdefault("tag_prev", attrs[pp])
                if attrs.get(np_):
                    hdr.setdefault("tag_next", attrs[np_])

    refs = []
    for n in names:
        low = n.lower()
        for pre in ("#lst:", "#lsth:", "#section:", "#section-h:", ":"):
            if low.startswith(pre):
                low, n = low[len(pre):], n[len(pre):]
                break
        if low.startswith(ns.page_prefixes):
            refs.append(n)
    if refs:
        # keep document order: re-scan with positions
        for m in wt.TEMPLATE_START.finditer(text):
            n = m.group(1).strip()
            low = n.lower()
            for pre in ("#lst:", "#lsth:", "#section:", "#section-h:", ":"):
                if low.startswith(pre):
                    low, n = low[len(pre):], n[len(pre):]
                    break
            if low.startswith(ns.page_prefixes):
                rec.refs.append(page_key(n.split(":", 1)[1]))
    ptpl = {n: cfg.page_templates[n] for n in names if n in cfg.page_templates}
    if ptpl:
        for name, params, s, e in wt.templates(text, frozenset(ptpl)):
            v = params.get(str(ptpl[name]), "")
            if v:
                v = v.split("|")[0]
                if v.lower().startswith(ns.page_prefixes):
                    v = v.split(":", 1)[1]
                rec.refs.append(page_key(v))

    seen: dict[str, str | None] = {}
    cats = []
    spans = sorted(header_spans)
    author_ns = cfg.ns.get("author")
    for m in wt.LINK.finditer(text):
        if spans and any(s <= m.start() < e for s, e in spans):
            continue
        raw = m.group(1)
        num, rest = ns.split(raw.lstrip(":").strip()) if raw.strip() else (None, "")
        if num == 14 and not raw.strip().startswith(":"):
            cats.append(wt.norm_title(rest))
            continue
        if num == author_ns and author_ns is not None and "body_author" not in hdr:
            # an author link near the top of the page's own text ("trolig
            # skrevet av [[Forfatter:Gjest Baardsen]]"): author fallback
            before = m.start() - sum(min(e, m.start()) - s for s, e in spans if s < m.start())
            if before < 600:
                hdr["body_author"] = m.group(0)
            continue
        tgt = resolve_target(raw, title, ns)
        if tgt and tgt != title and tgt not in seen:
            seen[tgt] = m.group(2)
    rec.links = tuple(sys.intern(t) for t in seen)
    if any(v for v in seen.values()):
        rec.texts = tuple(seen.values())
    rec.cats = tuple(cats)
    rec.own_chars = _plain_len(text[:200_000], header_spans)
    if any(p in text for p in cfg.placeholders):
        rec.flags |= 1
    if not rec.tags and not rec.refs:
        body = text
        for s, e in sorted(header_spans, reverse=True):
            body = body[:s] + body[e:]
        rec.snippet = wt.strip_markup(body[:3000])[:200]
    return rec


def _page_links(text: str, ns: Namespaces) -> tuple | None:
    if text.count("[[") < 3:
        return None
    out, seen = [], set()
    for m in wt.LINK.finditer(wt.clean(text)):
        raw = m.group(1).strip()
        if raw.startswith(("/", "../")):
            continue
        tgt = resolve_target(raw, "", ns)
        if tgt and tgt not in seen:
            seen.add(tgt)
            out.append((sys.intern(tgt), m.group(2)))
    return tuple(out) if len(out) >= 3 else None


def _siteinfo_ns(path: Path) -> dict[int, str]:
    out = {}
    proc = subprocess.Popen(["bzip2", "-dc", str(path)], stdout=subprocess.PIPE)
    try:
        for ev, el in ET.iterparse(proc.stdout, events=("end",)):
            tag = el.tag.rsplit("}", 1)[-1]
            if tag == "namespace":
                out[int(el.get("key"))] = el.text or ""
            elif tag == "namespaces":
                break
    finally:
        proc.kill()
        proc.wait()
    return out


def _work(args) -> dict:
    """Parse one dump file (a part of the dump)."""
    path, wiki, site_ns = args
    cfg = configmod.load(wiki)
    ns = Namespaces(cfg, site_ns)
    interest = cfg.templates_of_interest()
    pos_names = cfg.positional_headers
    pages: dict[str, PageRec] = {}
    redirects: dict[str, str] = {}
    levels: dict[str, bytearray] = {}
    sizes: dict[str, array] = {}
    single: dict[str, tuple[int, int]] = {}
    plinks: dict[tuple, tuple] = {}
    indexes: dict[str, dict] = {}
    ids: dict[int, str] = {}
    authors: dict[str, int] = {}
    author_redirects: dict[str, str] = {}
    files: set[str] = set()
    index_fields = set(cfg.container_params + cfg.toc_fields + cfg.index_type_fields
                       + cfg.index_progress_fields + ["malform", "title", "type"]
                       + [f for fs in cfg.index_meta.values() for f in fs])
    page_ns, index_ns, author_ns = ns.page_ns, ns.index_ns, cfg.ns.get("author")
    proc = subprocess.Popen(["bzip2", "-dc", str(path)], stdout=subprocess.PIPE, bufsize=1 << 20)
    context = ET.iterparse(proc.stdout, events=("start", "end"))
    _, root = next(context)
    n = 0
    for ev, el in context:
        if ev != "end" or not el.tag.endswith("}page"):
            continue
        title = nsnum = pid = None
        redirect = None
        text = ""
        nbytes = rev = 0
        ts = ""
        for child in el:
            tag = child.tag.rsplit("}", 1)[-1]
            if tag == "title":
                title = child.text
            elif tag == "ns":
                nsnum = int(child.text)
            elif tag == "id":
                pid = int(child.text)
            elif tag == "redirect":
                redirect = child.get("title")
            elif tag == "revision":
                for c in child:
                    ctag = c.tag.rsplit("}", 1)[-1]
                    if ctag == "text":
                        text = c.text or ""
                        nbytes = int(c.get("bytes") or len(text.encode()))
                    elif ctag == "id":
                        rev = int(c.text)
                    elif ctag == "timestamp":
                        ts = c.text or ""
        if nsnum == 0:
            title = wt.norm_title(title)
            if redirect is not None:
                redirects[title] = wt.norm_title(redirect.split("#", 1)[0])
            else:
                rec = pages[title] = parse_ns0(title, pid, nbytes, text, cfg, ns, interest, pos_names)
                rec.rev, rec.ts = rev, ts
                ids[pid] = title
        elif nsnum == author_ns and author_ns is not None:
            # author pages: page id for the QID in page_props (5.2 step 3)
            if redirect is not None:
                author_redirects[wt.norm_title(title)] = wt.norm_title(redirect.split("#", 1)[0])
            else:
                authors[wt.norm_title(title)] = pid
        elif nsnum == 6 and redirect is None:
            files.add(wt.norm_title(title.split(":", 1)[1]))
        elif nsnum == page_ns and page_ns is not None and redirect is None:
            f, num = page_key(title.split(":", 1)[1])
            m = wt.PAGEQUALITY.search(text)
            lvl = int(m.group(1)) if m else UNSET - 1   # 254: page without a quality tag
            if num is None:
                single[f] = (lvl, nbytes)
            elif num < 100_000:
                lv = levels.get(f)
                if lv is None:
                    lv = levels[f] = bytearray()
                    sizes[f] = array("I")
                if len(lv) <= num:
                    grow = num + 1 - len(lv)
                    lv.extend(bytes([UNSET]) * grow)
                    sizes[f].extend([0] * grow)
                lv[num] = lvl
                sizes[f][num] = min(nbytes, 2**32 - 1)
            pl = _page_links(text, ns)
            if pl:
                plinks[(f, num)] = pl
        elif nsnum == index_ns and index_ns is not None and redirect is None:
            name = wt.norm_title(title.split(":", 1)[1])
            fields = {}
            tps = wt.templates(wt.clean(text), wt.template_names(wt.clean(text)))
            if tps:
                for k, v in tps[0][1].items():
                    if k in index_fields and v:
                        fields[k] = v
            indexes[name] = fields
        n += 1
        root.clear()
    proc.wait()
    return dict(pages=pages, redirects=redirects, levels=levels, sizes=sizes, single=single,
                plinks=plinks, indexes=indexes, ids=ids, authors=authors,
                author_redirects=author_redirects, files=files, count=n)


SQL_STR = r"'((?:[^'\\]|\\.)*)'"


def _unescape(v: str) -> str:
    return re.sub(r"\\(.)", lambda m: {"n": "\n", "t": "\t", "0": ""}.get(m.group(1), m.group(1)), v)


def page_props(dbname: str) -> dict:
    """disambiguation flags, Wikidata items, display titles and default sort
    keys by page id, from the page_props dump."""
    out = dict(disamb=set(), qids={}, displaytitle={}, defaultsort={})
    try:
        path = dumps.local(dbname, "page_props")[0]
    except FileNotFoundError:
        return out
    pat_d = re.compile(r"\((\d+),'disambiguation',")
    pat_q = re.compile(r"\((\d+),'wikibase_item','(Q\d+)'")
    pat_s = re.compile(r"\((\d+),'(displaytitle|defaultsort)'," + SQL_STR)
    with gzip.open(path, "rt", encoding="utf-8", errors="replace") as f:
        for line in f:
            out["disamb"].update(int(x) for x in pat_d.findall(line))
            out["qids"].update((int(a), b) for a, b in pat_q.findall(line))
            for pid, prop, val in pat_s.findall(line):
                out[prop][int(pid)] = _unescape(val)
    return out


def run(wiki: str, workers: int = 6) -> dict:
    cfg = configmod.load(wiki)
    parts = dumps.local(cfg.dbname, "articles")
    site_ns = _siteinfo_ns(parts[0])
    t0 = time.time()
    if len(parts) == 1:
        results = [_work((parts[0], wiki, site_ns))]
    else:
        with mp.get_context("fork").Pool(min(workers, len(parts))) as pool:
            results = pool.map(_work, [(p, wiki, site_ns) for p in parts])
    out = results[0]
    for r in results[1:]:
        for k in ("pages", "redirects", "single", "plinks", "indexes", "ids", "authors",
                  "author_redirects", "files"):
            out[k].update(r[k])
        for f, lv in r["levels"].items():
            if f not in out["levels"]:
                out["levels"][f], out["sizes"][f] = lv, r["sizes"][f]
                continue
            a, sa = out["levels"][f], out["sizes"][f]
            if len(a) < len(lv):
                grow = len(lv) - len(a)
                a.extend(bytes([UNSET]) * grow)
                sa.extend([0] * grow)
            for i, v in enumerate(lv):
                if v != UNSET:
                    a[i] = v
                    sa[i] = r["sizes"][f][i]
        out["count"] += r["count"]
    out.update(page_props(cfg.dbname))
    out["site_ns"] = site_ns
    out["dump"] = [p.name for p in parts]
    out["wiki"] = wiki
    out["version"] = EXTRACT_VERSION
    print(f"{wiki}: {out['count']} pages parsed in {time.time() - t0:.0f} s; "
          f"ns0 {len(out['pages'])} + {len(out['redirects'])} redirects; "
          f"Page: {sum(len(v) for v in out['levels'].values()) and sum(1 for v in out['levels'].values() for x in v if x != UNSET)} "
          f"+ {len(out['single'])} single; indexes {len(out['indexes'])}", file=sys.stderr)
    EXTRACT_DIR.mkdir(parents=True, exist_ok=True)
    with (EXTRACT_DIR / f"{wiki}.pickle").open("wb") as f:
        pickle.dump(out, f, protocol=pickle.HIGHEST_PROTOCOL)
    return out


def load(wiki: str) -> dict:
    with (EXTRACT_DIR / f"{wiki}.pickle").open("rb") as f:
        return pickle.load(f)


if __name__ == "__main__":
    from wscatalog import extract as _module   # pickle classes under their package name
    for w in sys.argv[1:]:
        _module.run(w)
