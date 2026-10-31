# SPDX-License-Identifier: GPL-3.0-or-later
"""Per-wiki configuration (PLAN.md 5.5): pipeline/config/{wiki}.yaml over
pipeline/config/default.yaml."""

from __future__ import annotations

import copy
import re
from dataclasses import dataclass, field
from pathlib import Path

import yaml

from .wikitext import norm_template

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
    exclude_title_prefixes: tuple[str, ...] = ()
    disambiguation_templates: frozenset[str] = frozenset()
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
    placeholders: list[str] = field(default_factory=list)
    own_translation_params: list[str] = field(default_factory=list)
    own_translation_values: frozenset[str] = frozenset()
    author_params: list[str] = field(default_factory=list)
    year_params: list[str] = field(default_factory=list)

    def templates_of_interest(self) -> frozenset[str]:
        return (self.disambiguation_templates | self.versions_templates
                | self.author_page_templates | self.exclude_templates
                | self.other_versions_templates | self.textquality_templates
                | self.incomplete_templates | self.junk_templates
                | self.header_templates | frozenset(self.positional_headers)
                | self.summary_templates)


def load(wiki: str) -> Config:
    default = yaml.safe_load((CONFIG_DIR / "default.yaml").read_text("utf-8"))
    path = CONFIG_DIR / f"{wiki}.yaml"
    over = yaml.safe_load(path.read_text("utf-8")) if path.exists() else {"wiki": wiki}
    raw = _merge(default, over)
    s, q = raw["structure"], raw["quality"]
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
        exclude_title_prefixes=tuple(raw.get("exclude_title_prefixes") or []),
        disambiguation_templates=_tset(raw.get("disambiguation_templates")),
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
        placeholders=list(q.get("placeholders") or []),
        own_translation_params=_lower((q.get("own_translation") or {}).get("params")),
        own_translation_values=frozenset((q.get("own_translation") or {}).get("values") or []),
        author_params=_lower((raw.get("header_params") or {}).get("author")),
        year_params=_lower((raw.get("header_params") or {}).get("year")),
    )
