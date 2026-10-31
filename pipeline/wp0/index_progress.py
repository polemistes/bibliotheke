# SPDX-License-Identifier: GPL-3.0-or-later
"""WP0: read the Index-page progress values of the 11 configured wikis.

Each wiki defines its Index fields in MediaWiki:Proofreadpage_index_data_config
(JSON). This prints the progress field's allowed values per wiki and writes
docs/wp0/index-progress.json.

Run from the repo root:  python3 pipeline/wp0/index_progress.py
"""

from __future__ import annotations

import json
from pathlib import Path

import wmhttp
from namespaces import CONFIGURED

OUT = Path(__file__).resolve().parents[2] / "docs" / "wp0" / "index-progress.json"
PROGRESS_NAMES = {"progress", "progres", "progreso", "stato", "estado", "status", "fremgang", "qualità",
                  "avancement"}


def progress_field(domain: str) -> dict | None:
    data = wmhttp.action_api(domain, action="query", prop="revisions", rvprop="content",
                             rvslots="main", redirects="1",
                             titles="MediaWiki:Proofreadpage_index_data_config")
    page = data["query"]["pages"][0]
    if page.get("missing"):
        return None
    text = page["revisions"][0]["slots"]["main"]["content"]
    try:
        config = json.loads(text)
    except json.JSONDecodeError:
        return {"field": None, "unparsed": text[:300]}
    for name, field in config.items():
        values = field.get("values") or {}
        if field.get("data") == "progress" or name.lower() in PROGRESS_NAMES or \
                {"V", "T", "C"} <= set(values):
            return {"field": name, "label": field.get("label"), "values": field.get("values"),
                    "default": field.get("default")}
    return {"field": None, "fields": list(config)}


def main() -> None:
    results = {code: progress_field(domain) for code, domain in CONFIGURED.items()}
    OUT.write_text(json.dumps(results, ensure_ascii=False, indent=1) + "\n")
    for code, r in results.items():
        if not r or not r.get("field"):
            print(f"{code}: no progress field found {r}")
            continue
        values = "; ".join(f"{k} = {v}" for k, v in (r["values"] or {}).items())
        print(f"{code}: field {r['field']!r} (default {r['default']!r}): {values}")


if __name__ == "__main__":
    main()
