# SPDX-License-Identifier: GPL-3.0-or-later
"""Metadata merge (PLAN.md 5.2 step 3): title, authors, year, language,
genres, type and description of every work, each with its source.

Sources in priority order (5.2 step 3, 3.6):
- title: page_props `displaytitle`, else the page title;
- authors: Wikidata P50 (edition, then work item), else the header's author
  parameter, else the Index's author field; translators likewise (P655);
- year: edition P577, work P577, header, Index, year category;
- language: P407 (edition, then work), the Index's language field (no
  `Malform`), else the wiki's language;
- genres: P136 and P31 (edition, then work), else categories and templates
  of the root page, else derived from the type;
- type: see `Merger.work_type`;
- description: Wikidata description in the wiki's language, the header's
  description (de `KURZBESCHREIBUNG`), Wikidata in English.
"""

from __future__ import annotations

import hashlib
import re
import time
import unicodedata
from dataclasses import dataclass, field
from urllib.parse import quote

from . import wikitext as wt
from .extract import resolve_target
from .structure import Resolver, Work, roman_value
from .wikidata import Wikidata

GENRES = ("novel", "short-story", "poetry", "drama", "essay", "letters", "memoir-biography",
          "history", "religious", "legal-government", "speech", "periodical", "children",
          "reference", "song-hymn", "travel", "philosophy", "science", "folklore", "other")
TYPES = ("book", "poem", "play", "article", "document", "collection", "periodical-issue",
         "other", "reference")
EDITION = "Q3331189"            # version, edition or translation
INVISIBLE = dict.fromkeys(map(ord, "​‌‍‎‏­﻿"))
SEPARATORS = re.compile(r"\s*(?:<br\s*/?>|;|&|\s/\s|\s(?:og|and|und|et|e|y|och|ja)\s)\s*", re.I)
PARTICLES = {"von", "van", "de", "du", "da", "di", "la", "le", "der", "den", "af", "zu", "ten",
             "ter", "del", "della", "des", "dos", "das", "vom", "zur"}
YEAR = re.compile(r"(?<!\d)(\d{3,4})(?!\d)")
DECADE = re.compile(r"(?<!\d)(\d{3})0(?:s|-?tallet|er|-årene|-luvulla)\b", re.I)


def clean_name(s: str) -> str:
    s = unicodedata.normalize("NFC", s.translate(INVISIBLE))
    s = re.sub(r"\s+", " ", s).strip(" ,.;:–-\t")
    return s


def sort_name(name: str) -> str:
    """Last name first: "Johann Wolfgang von Goethe" -> "Goethe, Johann
    Wolfgang von"; names with a comma or of one word are kept."""
    if "," in name or " " not in name:
        return name
    words = name.split(" ")
    i = len(words) - 1
    return f"{words[i]}, {' '.join(words[:i])}"


def parse_year(raw: str | None) -> tuple[int, int] | None:
    """(year, precision) from a header or Index value; precision 9 = year,
    8 = decade (Wikidata's scale)."""
    if not raw:
        return None
    text = wt.strip_markup(raw)
    m = DECADE.search(text)
    y = YEAR.search(text)
    if y and (not m or y.start() < m.start()) and 500 <= int(y.group(1)) <= time.gmtime().tm_year + 1:
        return int(y.group(1)), 9
    if m:
        return int(m.group(1)) * 10, 8
    return None


@dataclass(slots=True)
class Person:
    name: str
    page: str | None = None          # author page title, with namespace (or ns0 on de, fi)
    qid: str | None = None
    sort: str | None = None
    birth: int | None = None
    death: int | None = None

    def key(self) -> str:
        return self.qid or self.page or self.name.casefold()


@dataclass(slots=True)
class Meta:
    display_title: str
    title_source: str
    sort_title: str
    qid: str | None = None
    work_qid: str | None = None
    authors: list[Person] = field(default_factory=list)
    translators: list[Person] = field(default_factory=list)
    author_source: str | None = None
    year: int | None = None
    year_precision: int | None = None
    year_source: str | None = None
    language: str = ""
    language_source: str = "wiki"
    genres: list[str] = field(default_factory=list)
    genre_source: str | None = None
    type: str = "other"
    type_source: str = "default"
    description: str | None = None
    cover_url: str | None = None


class Merger:
    def __init__(self, res: Resolver, wd: Wikidata | None):
        self.res, self.cfg, self.wd = res, res.cfg, wd
        ext = res.ext
        self.ext = ext
        self.qids: dict[int, str] = ext.get("qids") or {}
        self.defaultsort: dict[int, str] = ext.get("defaultsort") or {}
        self.displaytitle: dict[int, str] = ext.get("displaytitle") or {}
        self.author_pages: dict[str, int] = ext.get("authors") or {}
        self.author_redirects: dict[str, str] = ext.get("author_redirects") or {}
        self.files: set[str] = ext.get("files") or set()
        site_ns = ext.get("site_ns") or {}
        self.author_ns = self.cfg.ns.get("author")
        self.author_prefix = (site_ns.get(self.author_ns) or "") if self.author_ns else ""
        lang = self.cfg.language
        self.langs = tuple(dict.fromkeys(
            [lang] + (["nb", "nn"] if lang == "no" else []) + ["mul"]))
        self.anonymous = {a.casefold() for a in self.cfg.raw.get("metadata", {}).get("anonymous_names") or []}
        self.link_templates = {wt.norm_template(t) for t in
                               self.cfg.raw.get("metadata", {}).get("author_link_templates") or []}

    # ---- people ------------------------------------------------------------
    def author_page(self, target: str) -> str | None:
        """Existing author page for a link target or a plain name."""
        t = wt.norm_title(target.split("#", 1)[0])
        if self.author_ns:
            if ":" not in t or t.split(":", 1)[0].strip().lower() != self.author_prefix.lower():
                ns, rest = self.res.ns.split(t)
                if ns == self.author_ns:
                    t = f"{self.author_prefix}:{wt.norm_title(rest)}"
                elif ns == 0:
                    t = f"{self.author_prefix}:{t}"
                else:
                    return None
            t = self.author_redirects.get(t, t)
            return t if t in self.author_pages else None
        # no Author namespace (de, fi): author pages are ns0 pages
        t = self.res.resolve(t)
        return t if t in self.res.pages else None

    def page_id(self, page: str) -> int | None:
        if self.author_ns:
            return self.author_pages.get(page)
        rec = self.res.pages.get(page)
        return rec.page_id if rec else None

    def people(self, raw: str | None) -> list[Person]:
        """Persons named in a header or Index value: one per author link,
        else names separated by "og", "and", ";", "&", line breaks."""
        if not raw:
            return []
        out: list[Person] = []
        text = wt.clean(raw)
        for name, params, s, e in wt.templates(text, self.link_templates & wt.template_names(text)) \
                if self.link_templates else ():
            n = clean_name(params.get("1", ""))
            if n:
                out.append(Person(n, self.author_page(n)))
        if not out:
            for target, label in wt.links(text):
                ns, rest = self.res.ns.split(target.lstrip(":").strip())
                if ns not in (0, self.author_ns) or not rest.strip():
                    continue
                if ns == 0 and self.author_ns:
                    continue        # an ns0 link names a work, not an author
                name = clean_name(wt.strip_markup(label) if label else re.sub(r"\s*\([^)]*\)$", "", rest))
                if name:
                    out.append(Person(name, self.author_page(target.lstrip(":"))))
        if not out:
            plain = clean_name(wt.strip_markup(re.sub(r"<br\s*/?>", ";", text, flags=re.I)))
            for part in SEPARATORS.split(plain) if plain else ():
                part = clean_name(part)
                if part.count(",") == 1 and len(part.split(",")[1].split()) <= 3:
                    last, first = (x.strip() for x in part.split(","))
                    first = " ".join(w[:1].upper() + w[1:] if w.islower() else w for w in first.split())
                    part = f"{first} {last}".strip()
                    out.append(Person(part, self.author_page(part), sort=f"{last}, {first}"))
                elif part.count(",") > 1:
                    out.extend(Person(p, self.author_page(p)) for p in map(clean_name, part.split(",")) if p)
                elif part:
                    out.append(Person(part, self.author_page(part)))
        seen, res = set(), []
        for p in out:
            if p.name.casefold() in self.anonymous or len(p.name) < 2 or p.name.casefold() in seen:
                continue
            seen.add(p.name.casefold())
            res.append(p)
        return res

    def enrich(self, p: Person) -> Person:
        """QID, sort name and life years from the author page and its item."""
        if p.page:
            pid = self.page_id(p.page)
            if pid is not None:
                p.qid = p.qid or self.qids.get(pid)
                if not p.sort and self.defaultsort.get(pid):
                    p.sort = clean_name(self.defaultsort[pid])
        if p.qid and self.wd:
            birth, death = self.wd.claims(p.qid, "P569"), self.wd.claims(p.qid, "P570")
            p.birth = birth[0][0] if birth else None
            p.death = death[0][0] if death else None
        p.sort = p.sort or sort_name(p.name)
        return p

    def wd_people(self, qids: list[str]) -> list[Person]:
        out = []
        site = self.cfg.dbname
        for q in qids:
            name = self.wd.label(q, self.langs) if self.wd else None
            if not name:
                continue
            page = (self.wd.get(q) or {}).get("s", {}).get(site)
            out.append(Person(clean_name(name), wt.norm_title(page) if page else None, q))
        return out

    # ---- inputs ------------------------------------------------------------
    def root_rec(self, w: Work):
        if w.root.rec is not None:
            return w.root.rec
        return w.parts[0].node.rec if w.parts and w.parts[0].node.rec else None

    def index_fields(self, w: Work) -> dict:
        n = w.root if w.root.rec is not None else (w.parts[0].node if w.parts else w.root)
        return self.res.indexes.get(n.index or "") or {}

    def index_value(self, fields: dict, role: str) -> str | None:
        for f in self.cfg.index_meta.get(role, ()):
            if (fields.get(f) or "").strip():
                return fields[f]
        return None

    def edition_qid(self, w: Work) -> str | None:
        rec = w.root.rec
        if rec is not None and rec.page_id in self.qids:
            return self.qids[rec.page_id]
        if rec is not None and self.res.links_to_self(w.root):
            q = (self.index_value(self.index_fields(w), "qid") or "").strip()
            if re.fullmatch(r"Q\d+", q):
                return q
        return None

    def wanted_qids(self, works: list[Work]) -> tuple[list[str], list[str]]:
        """Edition items, and the author pages' items, for prefetching."""
        eds, authors = [], []
        for w in works:
            q = self.edition_qid(w)
            if q:
                eds.append(q)
            rec = self.root_rec(w)
            fields = self.index_fields(w)
            for raw in (rec.hdr.get("author") if rec else None, self.index_value(fields, "author"),
                        rec.hdr.get("translator") if rec else None, self.index_value(fields, "translator")):
                for p in self.people(raw):
                    if p.page:
                        pid = self.page_id(p.page)
                        if pid in self.qids:
                            authors.append(self.qids[pid])
        return eds, authors

    def prefetch(self, works: list[Work]) -> None:
        if not self.wd:
            return
        eds, authors = self.wanted_qids(works)
        self.wd.ensure(eds)
        work_items = [q for e in eds for q in self.wd.claims(e, "P629")[:1]]
        self.wd.ensure(work_items)
        for q in eds + work_items:
            for prop in ("P50", "P655"):
                authors.extend(self.wd.claims(q, prop))
        self.wd.ensure(authors)

    # ---- the merge ---------------------------------------------------------
    def sort_title(self, title: str, rec) -> str:
        if rec is not None and rec.page_id in self.defaultsort and rec.title == title:
            s = self.defaultsort[rec.page_id]
        else:
            s = title
        s = clean_name(wt.strip_markup(s)).casefold()
        s = s.lstrip("\"'«»„“”‘’‚‹›([¡¿…. ")
        for a in self.cfg.sort_articles:
            if a.endswith("'") and s.startswith(a):
                return s[len(a):].lstrip()
            if s.startswith(a + " "):
                return s[len(a) + 1:].lstrip()
        return s

    def merge(self, w: Work, quality) -> Meta:
        cfg, wd = self.cfg, self.wd
        rec = w.root.rec
        title = w.root.title
        if rec is not None and self.displaytitle.get(rec.page_id):
            dt = clean_name(wt.strip_markup(self.displaytitle[rec.page_id]))
            m = Meta(dt or title, "displaytitle" if dt else "page-title", "")
        else:
            m = Meta(title, "page-title", "")
        m.sort_title = self.sort_title(title, rec)
        hrec = self.root_rec(w)
        hdr = hrec.hdr if hrec is not None else {}
        fields = self.index_fields(w)
        m.qid = self.edition_qid(w)
        items = [m.qid] if m.qid else []
        if m.qid and wd:
            work = wd.claims(m.qid, "P629")[:1]
            if work:
                m.work_qid = work[0]
                items.append(work[0])
            elif EDITION not in wd.claims(m.qid, "P31"):
                m.work_qid = m.qid          # the page links the work item itself
        # authors
        for q in items:
            ps = self.wd_people(wd.claims(q, "P50")) if wd else []
            if ps:
                m.authors, m.author_source = ps, "wikidata"
                break
        if not m.authors:
            for raw, src in ((hdr.get("author"), "header"), (self.index_value(fields, "author"), "index")):
                ps = self.people(raw)
                if ps:
                    m.authors, m.author_source = ps, src
                    break
        if not m.authors and hrec is not None and self.cfg.raw.get("metadata", {}).get("author_categories"):
            # Implementation choice (no): a category named exactly like an
            # existing author page names the author ("Henrik Wergeland").
            ps = [Person(c, self.author_page(c)) for c in hrec.cats if self.author_page(c)]
            if ps:
                m.authors, m.author_source = ps, "category"
        if not m.authors and hdr.get("body_author"):
            # Implementation choice: an author link in the first 600
            # characters of the page's own text (typed-in pages without header)
            ps = [p for p in self.people(hdr["body_author"]) if p.page]
            if ps:
                m.authors, m.author_source = ps, "body"
        for q in items:
            ps = self.wd_people(wd.claims(q, "P655")) if wd else []
            if ps:
                m.translators = ps
                break
        if not m.translators:
            for raw in (hdr.get("translator"), self.index_value(fields, "translator")):
                ps = [p for p in self.people(raw) if p.name not in cfg.own_translation_values]
                if ps:
                    m.translators = ps
                    break
        m.authors = [self.enrich(p) for p in m.authors]
        m.translators = [self.enrich(p) for p in m.translators]
        # year
        for q, src in zip(items, ("wikidata-edition", "wikidata-work")):
            ys = wd.claims(q, "P577") if wd else []
            if ys and ys[0][1] >= 7:
                m.year, m.year_precision, m.year_source = ys[0][0], min(ys[0][1], 11), src
                break
        if m.year is None:
            for raw, src in ((hdr.get("year"), "header"), (self.index_value(fields, "year"), "index")):
                y = parse_year(raw)
                if y:
                    m.year, m.year_precision, m.year_source = y[0], y[1], src
                    break
        if m.year is None and hrec is not None:
            # year, decade or century categories ("Tekster fra 1896", de
            # "1940er Jahre", "13. Jahrhundert"); a century is its middle year
            for pat, prec in cfg.year_categories:
                mm = next((x for x in map(pat.match, hrec.cats) if x), None)
                if mm:
                    g = mm.group(1)
                    v = int(g) if g.isdigit() else roman_value(g.upper())     # fr "XIXe siècle"
                    m.year = (v - 1) * 100 + 50 if prec == 7 else v
                    m.year_precision, m.year_source = prec, "category"
                    break
        # language
        m.language = cfg.language
        for q in items:
            langs = [cfg.wikidata_languages.get(x) for x in (wd.claims(q, "P407") if wd else [])]
            langs = [x for x in langs if x]
            if langs:
                m.language, m.language_source = langs[0], "wikidata"
                break
        else:
            raw = (self.index_value(fields, "language") or "").strip()
            code = cfg.language_map.get(raw.upper()) or (raw.lower() if re.fullmatch(r"[a-z]{2,3}", raw) else None)
            if code:
                m.language, m.language_source = code, "index"
            elif hrec is not None and cfg.language_categories:
                # mul: a category names the text's language ("Volapük", "Tatar")
                code = next((cfg.language_categories[c] for c in hrec.cats if c in cfg.language_categories), None)
                if code:
                    m.language, m.language_source = code, "category"
        # genres and type
        doc = self.is_document(w)
        for q in items:
            gs = [cfg.wikidata_genres[x] for x in (wd.claims(q, "P136") + wd.claims(q, "P31") if wd else [])
                  if x in cfg.wikidata_genres]
            if gs:
                m.genres, m.genre_source = list(dict.fromkeys(gs)), "wikidata"
                break
        if not m.genres and hrec is not None and hdr.get("genre"):
            # a header topic (it `argomento`), read like a category
            gs = self.category_genres(hrec, re.split(r"\s*[/,;]\s*", wt.strip_markup(hdr["genre"])))
            if gs:
                m.genres, m.genre_source = gs, "header"
        if not m.genres and hrec is not None:
            gs = self.category_genres(hrec)
            if gs:
                m.genres, m.genre_source = gs, "category"
        m.type, m.type_source = self.work_type(w, items, m.genres, doc)
        derived = {"document": "legal-government", "periodical-issue": "periodical",
                   "reference": "reference", "poem": "poetry", "play": "drama"}.get(m.type)
        if doc and "legal-government" not in m.genres and not (set(m.genres) & {"speech"}):
            m.genres.append("legal-government")
            m.genre_source = m.genre_source or "template"
        if not m.genres and derived:
            m.genres, m.genre_source = [derived], "type"
        # description: the wiki's language first, then the header, then English
        for langs in (self.langs, None, ("en",)):
            if langs is None:
                d = wt.strip_markup(hdr.get("description") or "")[:500]
            else:
                d = next((self.wd_description(q, langs) for q in (m.work_qid, m.qid)
                          if self.wd_description(q, langs)), None)
            if d:
                m.description = d
                break
        m.cover_url = self.cover(w, hdr, fields)
        return m

    def wd_description(self, qid: str | None, langs: tuple[str, ...]) -> str | None:
        it = self.wd.get(qid) if (self.wd and qid) else None
        return next((it["d"][x] for x in langs if it and it["d"].get(x)), None)

    def category_genres(self, rec, names: list[str] | None = None) -> list[str]:
        cfg = self.cfg
        out = []
        for c in (rec.cats if names is None else names):
            g = cfg.genre_categories.get(c)
            if g is None:
                for pat, gg in cfg.genre_category_patterns:
                    if pat.search(c):
                        g = gg
                        break
            if g:
                out.append(g)
        for t in (rec.tpls if names is None else ()):
            g = cfg.genre_templates.get(t)
            if g:
                out.append(g)
        return list(dict.fromkeys(out))

    def is_document(self, w: Work) -> bool:
        """Government and legal documents (en court cases, proclamations,
        acts, executive orders, resolutions): marked by licence or case
        templates and categories (decision 1 of 2026-11-01)."""
        cfg = self.cfg
        rec = self.root_rec(w)
        if rec is None:
            return False
        if rec.tpls & cfg.document_templates:
            return True
        if cfg.document_template_patterns and any(cfg.document_template_patterns.search(t) for t in rec.tpls):
            return True
        if cfg.document_category_patterns and any(cfg.document_category_patterns.search(c) for c in rec.cats):
            return True
        return False

    def work_type(self, w: Work, items: list[str], genres: list[str], doc: bool) -> tuple[str, str]:
        """Implementation choice, in order: reference (resolution, 5.2 step
        1e); an Index type other than book (journal, dictionary, thesis) when
        the work is that Index's book or has parts; document signals;
        Wikidata P31; Index type book; the resolution's book or collection;
        for a single page, the genre (poetry -> poem, drama -> play); other.
        The Index type of a single page that only takes a few pages out of a
        book's scan says nothing about the page (a poem from a collection)."""
        if w.type == "reference":
            return "reference", "structure"
        own_index = w.root.rec is not None and self.res.links_to_self(w.root)
        itype = w.root.index_type if (own_index or w.structure != "single") else None
        if itype and itype != "book":
            return itype, "index"
        if doc:
            return "document", "template"
        if self.wd:
            for q in reversed(items):        # the work item's P31 first
                for x in self.wd.claims(q, "P31"):
                    t = self.cfg.wikidata_types.get(x)
                    if t:
                        return t, "wikidata"
        if itype == "book":
            return "book", "index"
        if w.structure != "single":
            return (w.type if w.type in ("book", "collection") else "book"), "structure"
        for g in genres:
            t = self.cfg.genre_types.get(g)
            if t:
                return t, "genre"
        return "other", "default"

    def cover(self, w: Work, hdr: dict, fields: dict) -> str | None:
        """Thumbnail URL of the Index's cover image (a page number of the
        scan or a file), or of the header's image (de BILD)."""
        raw = (self.index_value(fields, "image") or hdr.get("image") or "").strip()
        n = w.root if w.root.rec is not None else (w.parts[0].node if w.parts else w.root)
        if not raw:
            return None
        if raw.isdigit() and n.index:
            return self.thumb(n.index, int(raw))
        link = wt.first_link(raw)
        name = link if link else raw
        if ":" in name:
            name = name.split(":", 1)[1]
        name = wt.norm_title(name.split("|", 1)[0])
        if not re.search(r"\.(jpe?g|png|gif|djvu|pdf|tiff?)$", name, re.I):
            return None
        return self.thumb(name, 1 if re.search(r"\.(djvu|pdf|tiff?)$", name, re.I) else None)

    def thumb(self, name: str, page: int | None) -> str:
        """Hashed thumbnail path (330 px, a standard thumbnail width); local
        files when the wiki has a description page for them, else Commons."""
        fn = name.replace(" ", "_")
        h = hashlib.md5(fn.encode()).hexdigest()
        base = (f"https://upload.wikimedia.org/wikisource/{self.cfg.raw.get('upload_dir') or self.cfg.wiki}"
                if name in self.files else "https://upload.wikimedia.org/wikipedia/commons")
        q = quote(fn)
        if page is not None:
            lossy = "lossy-" if re.search(r"\.tiff?$", fn, re.I) else ""
            return f"{base}/thumb/{h[0]}/{h[:2]}/{q}/{lossy}page{page}-330px-{q}.jpg"
        suffix = ".png" if fn.lower().endswith(".svg") else ""
        return f"{base}/thumb/{h[0]}/{h[:2]}/{q}/330px-{q}{suffix}"
