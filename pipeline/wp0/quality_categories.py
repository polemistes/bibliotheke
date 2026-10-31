# SPDX-License-Identifier: GPL-3.0-or-later
"""Structure spike (2026-10-31): scan pages per ProofreadPage quality level per wiki,
from the quality categories (prop=categoryinfo). Evidence: docs/structure/quality-levels.txt."""
import json, sys
import wmhttp
from pathlib import Path
wikis = json.load(open(Path(__file__).resolve().parents[2] / "docs" / "wp0" / "namespaces.json"))
print(f"{'wiki':5} {'total':>8} | " + " | ".join(f"{l:>14}" for l in ["0 no text", "1 not proofread", "2 problematic", "3 proofread", "4 validated"]))
for code, info in wikis.items():
    levels = info.get("quality_levels") or []
    if not levels:
        print(f"{code:5} no quality levels"); continue
    titles = "|".join(f"Category:{l['category']}" for l in levels)
    try:
        d = wmhttp.action_api(info["domain"], action="query", prop="categoryinfo", titles=titles)
    except Exception as e:
        print(f"{code:5} ERROR {str(e)[:100]}"); continue
    by_title = {p["title"].split(":", 1)[1]: p.get("categoryinfo", {}).get("pages", 0) for p in d["query"]["pages"]}
    counts = [by_title.get(l["category"], 0) for l in levels]
    total = sum(counts) or 1
    cells = " | ".join(f"{c:>7} {100*c/total:5.1f}%" for c in counts)
    print(f"{code:5} {total:>8} | {cells}")
