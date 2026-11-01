# SPDX-License-Identifier: GPL-3.0-or-later
"""Exports the per-wiki settings the app shares with the pipeline (PLAN.md 5.5,
7.1, 7.2) to core-content's wiki-profiles.json, so pipeline/config stays the
one source: the structure keys for live resolution and the `content` keys of
the normaliser.

    python -I -m wscatalog.appconfig          # writes the file
    python -I -m wscatalog.appconfig --check  # exit 1 when it is out of date
"""

from __future__ import annotations

import json
import sys
from pathlib import Path

from . import config

OUT = (Path(__file__).resolve().parents[2] / "core-content" / "src" / "main" / "resources"
       / "app" / "bibliotheke" / "wikisource" / "content" / "wiki-profiles.json")
VERSION = 1


def profile(c: config.Config) -> dict:
    s, content = c.raw["structure"], c.raw.get("content") or {}
    return {
        "wiki": c.wiki,
        "domain": c.domain,
        "language": c.language,
        "header_templates": sorted(c.header_templates),
        "positional_headers": {k: v for k, v in sorted(c.positional_headers.items())},
        "container_params": c.container_params,
        "prev_next_params": [list(p) for p in c.prev_next_params],
        "current_params": c.current_params,
        "index_params": c.index_params,
        "plain_title_params": sorted(c.plain_title_params),
        "toc_marker": list(c.toc_marker) if c.toc_marker else None,
        "toc_fields": c.toc_fields,
        "summary_templates": sorted(c.summary_templates),
        "chapter_label_regex": c.chapter_label.pattern if c.chapter_label else None,
        "whole_text_subpages": sorted(c.whole_text_subpages),
        "navigation_link_texts": sorted(c.navigation_texts),
        "ordinals": dict(sorted(c.ordinals.items())),
        "incomplete_templates": sorted(c.incomplete_templates),
        "headerless_toc_members": c.headerless_toc_members,
        "reference_threshold": c.reference_threshold,
        "page_number_selectors": list(content.get("page_number_selectors") or []),
        "line_number_selectors": list(content.get("line_number_selectors") or []),
        "header_selectors": list(content.get("header_selectors") or []),
        "drop_selectors": list(content.get("drop_selectors") or []),
        "drop_templates": sorted(config._tset(content.get("drop_templates"))),
        "licence_templates": list(content.get("licence_templates") or []),
        "oversized_chars": int(content.get("oversized_chars") or 100_000),
        "oversized_bytes": int(s["oversized_bytes"]),
    }


def export() -> str:
    wikis = {w: profile(config.load(w)) for w in config.configured()}
    # wikis without a file of their own: default.yaml alone; the app fills in domain and language
    generic = profile(config.load("generic", over={}))
    generic.update(wiki="", domain="", language="")
    doc = {"version": VERSION, "generic": generic, "wikis": wikis}
    return json.dumps(doc, ensure_ascii=False, indent=1) + "\n"


def main(argv: list[str]) -> int:
    text = export()
    if "--check" in argv:
        current = OUT.read_text("utf-8") if OUT.exists() else ""
        if current != text:
            print(f"{OUT} is out of date: run python -I -m wscatalog.appconfig", file=sys.stderr)
            return 1
        return 0
    OUT.parent.mkdir(parents=True, exist_ok=True)
    OUT.write_text(text, "utf-8")
    print(f"wrote {OUT} ({len(text)} bytes, {text.count(chr(10))} lines)")
    return 0


if __name__ == "__main__":
    sys.exit(main(sys.argv[1:]))
