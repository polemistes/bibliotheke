# SPDX-License-Identifier: GPL-3.0-or-later
"""Per-wiki configuration (PLAN.md 5.5): pipeline/config/{wiki}.yaml over
pipeline/config/default.yaml."""

from __future__ import annotations

import copy
import re
from dataclasses import dataclass, field
from pathlib import Path

import yaml

from .wikitext import norm_template, norm_title

CONFIG_DIR = Path(__file__).resolve().parents[1] / "config"


def _merge(base: dict, over: dict) -> dict:
    out = copy.deepcopy(base)
    for k, v in (over or {}).items():
        if isinstance(v, dict) and isinstance(out.get(k), dict):
            out[k] = _merge(out[k], v)
        else:
            out[k] = v
    return out


def _tset(names) -> frozenset[str]:
    return frozenset(norm_template(n) for n in names or [])


def _lower(names) -> list[str]:
    return [n.lower() for n in names or []]


@dataclass
class Config:
    raw: dict
    wiki: str = ""
    dbname: str = ""
    domain: str = ""
    language: str = ""
    ns: dict[str, int] = field(default_factory=dict)
    header_templates: frozenset[str] = frozenset()
    positional_headers: dict[str, dict] = field(default_factory=dict)
    container_params: list[str] = field(default_factory=list)
    prev_next_params: list[tuple[str, str]] = field(default_factory=list)
    current_params: list[str] = field(default_factory=list)
    index_params: list[str] = field(default_factory=list)
    plain_title_params: frozenset[str] = frozenset()
    toc_marker: tuple[str, str] | None = None
    toc_fields: list[str] = field(default_factory=list)
    summary_templates: frozenset[str] = frozenset()
    page_templates: dict[str, int] = field(default_factory=dict)
    chapter_label: re.Pattern | None = None
    oversized_bytes: int = 300_000
    parts_cap: int = 2000
    reference_threshold: int = 2000
    whole_text_subpages: frozenset[str] = frozenset()
    headerless_toc_members: bool = False
    exclude_title_prefixes: tuple[str, ...] = ()
    disambiguation_templates: frozenset[str] = frozenset()
    not_disambiguation_templates: frozenset[str] = frozenset()
    versions_templates: frozenset[str] = frozenset()
    author_page_templates: frozenset[str] = frozenset()
    exclude_templates: frozenset[str] = frozenset()
    exclude_categories: frozenset[str] = frozenset()
    other_versions_templates: frozenset[str] = frozenset()
    show_unknown_by_default: bool | None = None
    status_params: list[str] = field(default_factory=list)
    status_values: dict[str, str] = field(default_factory=dict)
    textquality_templates: frozenset[str] = frozenset()
    textquality_values: dict[str, str] = field(default_factory=dict)
    index_type_fields: list[str] = field(default_factory=list)
    index_types: dict[str, str] = field(default_factory=dict)
    index_progress_fields: list[str] = field(default_factory=list)
    index_progress_values: dict[str, str] = field(default_factory=dict)
    incomplete_templates: frozenset[str] = frozenset()
    junk_templates: frozenset[str] = frozenset()
    bibliography_templates: frozenset[str] = frozenset()
    coverage_min: float = 0.95
    coverage_slack: int = 3
    placeholders: list[str] = field(default_factory=list)
    own_translation_params: list[str] = field(default_factory=list)
    own_translation_values: frozenset[str] = frozenset()
    own_translation_templates: frozenset[str] = frozenset()
    author_params: list[str] = field(default_factory=list)
    year_params: list[str] = field(default_factory=list)
    translator_params: list[str] = field(default_factory=list)
    description_params: list[str] = field(default_factory=list)
    image_params: list[str] = field(default_factory=list)
    index_meta: dict[str, list[str]] = field(default_factory=dict)   # role -> Index fields
    header_placeholders: frozenset[str] = frozenset()
    # metadata merge (5.2 step 3)
    sort_articles: tuple[str, ...] = ()
    language_map: dict[str, str] = field(default_factory=dict)
    genre_categories: dict[str, str] = field(default_factory=dict)
    genre_category_patterns: list[tuple[re.Pattern, str]] = field(default_factory=list)
    genre_templates: dict[str, str] = field(default_factory=dict)
    document_templates: frozenset[str] = frozenset()
    document_template_patterns: re.Pattern | None = None
    document_category_patterns: re.Pattern | None = None
    year_categories: list[tuple[re.Pattern, int]] = field(default_factory=list)
    wikidata_genres: dict[str, str] = field(default_factory=dict)
    wikidata_types: dict[str, str] = field(default_factory=dict)
    wikidata_languages: dict[str, str] = field(default_factory=dict)
    genre_types: dict[str, str] = field(default_factory=dict)

    def templates_of_interest(self) -> frozenset[str]:
        return (self.disambiguation_templates | self.versions_templates
                | self.author_page_templates | self.exclude_templates
                | self.other_versions_templates | self.textquality_templates
                | self.incomplete_templates | self.junk_templates
                | self.header_templates | frozenset(self.positional_headers)
                | self.summary_templates)


# "Author" in the languages of the Wikisource wikis, for generic configs
AUTHOR_NAMES = {n.casefold() for n in (
    "Author", "Autor", "Autore", "Auteur", "Auctor", "Forfatter", "Författare", "Höfundur",
    "Kirjailija", "Автор", "Аўтар", "Autorius", "Autors", "Avtor", "Pisac", "Писац", "Szerző",
    "Yazar", "Müəllif", "Συγγραφέας", "Aŭtoro", "Egilea", "Awdur", "Údar", "Schrijver", "Skrywer",
    "Pengarang", "Penulis", "Tác gia", "Tác giả", "作者", "著者", "저자", "Կատարում", "Հեղինակ",
    "ავტორი", "लेखक", "লেখক", "ലേഖകൻ", "ಕರ್ತೃ", "రచయిత", "ஆசிரியர்", "ผู้แต่ง", "מחבר", "مؤلف",
    "نویسنده", "مصنف", "Muallif")}


def configured() -> list[str]:
    """Wikis with a config of their own (PLAN.md 5.5)."""
    return sorted(p.stem for p in CONFIG_DIR.glob("*.yaml") if p.stem != "default")


def generic(wiki: str) -> dict:
    """Config of a wiki without a file (PLAN.md 5.5: generic default,
    Wikidata-only metadata): domain and dbname from the sitematrix, the
    Page, Index and Author namespaces from the wiki's siteinfo (cached)."""
    import json
    from . import http, sites
    s = sites.site(wiki)
    if s is None:
        raise KeyError(f"{wiki}: not an open Wikisource")
    path = http.CACHE_DIR / "siteinfo" / f"{wiki}.json"
    if path.exists():
        info = json.loads(path.read_text("utf-8"))
    else:
        info = http.action_api(s["domain"], action="query", meta="siteinfo", siprop="namespaces")
        path.parent.mkdir(parents=True, exist_ok=True)
        path.write_text(json.dumps(info, ensure_ascii=False), "utf-8")
    spaces = info["query"]["namespaces"].values()
    by_canonical = {n.get("canonical"): n["id"] for n in spaces}
    ns = {k: by_canonical.get(c) for k, c in (("author", "Author"), ("index", "Index"), ("page", "Page"))}
    if ns["author"] is None:        # the Author namespace's canonical name is local on most wikis
        ns["author"] = next((n["id"] for n in spaces if n["id"] >= 100 and n["id"] % 2 == 0 and
                             {(n.get("name") or "").casefold(), (n.get("canonical") or "").casefold()}
                             & AUTHOR_NAMES), None)
    return {"wiki": wiki, "dbname": s["dbname"], "domain": s["domain"],
            "language": wiki, "namespaces": {k: v for k, v in ns.items() if v is not None}}


def load(wiki: str) -> Config:
    default = yaml.safe_load((CONFIG_DIR / "default.yaml").read_text("utf-8"))
    path = CONFIG_DIR / f"{wiki}.yaml"
    over = yaml.safe_load(path.read_text("utf-8")) if path.exists() else generic(wiki)
    raw = _merge(default, over)
    raw["wiki"] = wiki      # the file name; YAML would read a bare `no` as false
    s, q, m, wd = raw["structure"], raw["quality"], raw.get("metadata") or {}, raw.get("wikidata") or {}
    hp = raw.get("header_params") or {}
    labels = [raw["structure"]["chapter_label_regex_default"]]
    if s.get("chapter_label_regex"):
        labels.append(s["chapter_label_regex"])
    return Config(
        raw=raw,
        wiki=raw["wiki"],
        dbname=raw.get("dbname") or f"{raw['wiki']}wikisource",
        domain=raw.get("domain") or f"{raw['wiki']}.wikisource.org",
        language=raw.get("language") or raw["wiki"],
        ns=raw.get("namespaces", {}),
        header_templates=_tset(raw.get("header_templates")),
        positional_headers={norm_template(k): v for k, v in (raw.get("positional_headers") or {}).items()},
        container_params=_lower(s["container_params"]),
        prev_next_params=[tuple(_lower(p)) for p in s["prev_next_params"]],
        current_params=_lower(s["current_params"]),
        index_params=_lower(s["index_params"]),
        plain_title_params=frozenset(_lower(s.get("plain_title_params"))),
        toc_marker=tuple(x.lower() for x in s["toc_marker"]) if s.get("toc_marker") else None,
        toc_fields=_lower(s.get("toc_fields")),
        summary_templates=_tset(s.get("summary_templates")),
        page_templates={norm_template(k): int(v) for k, v in (s.get("page_templates") or {}).items()},
        chapter_label=re.compile("|".join(f"(?:{p})" for p in labels), re.I),
        oversized_bytes=int(s["oversized_bytes"]),
        parts_cap=int(s["parts_cap"]),
        reference_threshold=int(s["reference_threshold"]),
        whole_text_subpages=frozenset(t.lower() for t in s.get("whole_text_subpages") or []),
        headerless_toc_members=bool(s.get("headerless_toc_members")),
        exclude_title_prefixes=tuple(raw.get("exclude_title_prefixes") or []),
        disambiguation_templates=_tset(raw.get("disambiguation_templates")),
        not_disambiguation_templates=_tset(raw.get("not_disambiguation_templates")),
        versions_templates=_tset(raw.get("versions_templates")),
        author_page_templates=_tset(raw.get("author_page_templates")),
        exclude_templates=_tset(raw.get("exclude_templates")),
        exclude_categories=frozenset(raw.get("exclude_categories") or []),
        other_versions_templates=_tset(raw.get("other_versions_templates")),
        show_unknown_by_default=q.get("show_unknown_by_default"),
        status_params=_lower(q.get("status_params")),
        status_values={k.lower(): v for k, v in (q.get("status_values") or {}).items()},
        textquality_templates=_tset(q.get("textquality_templates")),
        textquality_values={str(k).lower(): v for k, v in (q.get("textquality_values") or {}).items()},
        index_type_fields=_lower(q.get("index_type_fields")),
        index_types={str(k).lower(): v for k, v in (q.get("index_types") or {}).items()},
        index_progress_fields=_lower(q.get("index_progress_fields")),
        index_progress_values={str(k).upper(): v for k, v in (q.get("index_progress_values") or {}).items()},
        incomplete_templates=_tset(q.get("incomplete_templates")),
        junk_templates=_tset(q.get("junk_templates")),
        bibliography_templates=_tset(q.get("bibliography_templates")),
        coverage_min=float(q.get("coverage_min", 0.95)),
        coverage_slack=int(q.get("coverage_slack", 3)),
        placeholders=list(q.get("placeholders") or []),
        own_translation_params=_lower((q.get("own_translation") or {}).get("params")),
        own_translation_values=frozenset((q.get("own_translation") or {}).get("values") or []),
        own_translation_templates=_tset(q.get("own_translation_templates")),
        author_params=_lower(hp.get("author")),
        year_params=_lower(hp.get("year")),
        translator_params=list(dict.fromkeys(
            _lower(hp.get("translator")) + _lower((q.get("own_translation") or {}).get("params")))),
        description_params=_lower(hp.get("description")),
        image_params=_lower(hp.get("image")),
        header_placeholders=frozenset(str(v).casefold() for v in m.get("header_placeholders") or []),
        index_meta={k: _lower(v) for k, v in (raw.get("index_fields") or {}).items()},
        sort_articles=tuple(a.lower() for a in m.get("sort_articles") or []),
        language_map={str(k).upper(): v for k, v in (m.get("language_map") or {}).items()},
        genre_categories={norm_title(k): v for k, v in (m.get("genre_categories") or {}).items()},
        genre_category_patterns=[(re.compile(p, re.I), g) for p, g in m.get("genre_category_patterns") or []],
        genre_templates={norm_template(k): v for k, v in (m.get("genre_templates") or {}).items()},
        document_templates=_tset(m.get("document_templates")),
        document_template_patterns=_regex(m.get("document_template_patterns")),
        document_category_patterns=_regex(m.get("document_category_patterns")),
        year_categories=[(re.compile(p), int(prec)) for p, prec in m.get("year_categories") or []],
        wikidata_genres=dict(wd.get("genres") or {}),
        wikidata_types=dict(wd.get("types") or {}),
        wikidata_languages=dict(wd.get("languages") or {}),
        genre_types=dict(m.get("genre_types") or {}),
    )


def _regex(patterns) -> re.Pattern | None:
    return re.compile("|".join(f"(?:{p})" for p in patterns), re.I) if patterns else None

