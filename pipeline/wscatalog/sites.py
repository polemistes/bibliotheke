# SPDX-License-Identifier: GPL-3.0-or-later
"""The Wikisource wikis (PLAN.md 5.1 input 1, 3.1): the sitematrix's
`wikisource` sites plus the multilingual wikisource.org (`mul`), without
closed wikis. Cached in pipeline/cache/sitematrix.json for a week."""

from __future__ import annotations

import json
import time

from . import http

PATH = http.CACHE_DIR / "sitematrix.json"
MAX_AGE_S = 7 * 86400


def wikis(refresh: bool = False) -> list[dict]:
    """[{code, dbname, domain, autonym, name}] sorted by code."""
    if not refresh and PATH.exists() and time.time() - PATH.stat().st_mtime < MAX_AGE_S:
        return json.loads(PATH.read_text("utf-8"))
    data = http.get_json("https://meta.wikimedia.org/w/api.php",
                         {"action": "sitematrix", "format": "json", "formatversion": "2",
                          "smtype": "language|special", "smlangprop": "code|name|localname|site",
                          "smsiteprop": "code|dbname|url", "smstate": "all"})
    out = []
    for key, lang in data["sitematrix"].items():
        if key in ("count", "specials"):
            continue
        for site in lang.get("site", ()):
            if site.get("code") == "wikisource" and "closed" not in site and "private" not in site:
                out.append(dict(code=lang["code"], dbname=site["dbname"],
                                domain=site["url"].split("//", 1)[1], autonym=lang.get("name"),
                                name=lang.get("localname")))
    for site in data["sitematrix"].get("specials", ()):
        if site.get("code") == "sources" and "closed" not in site:      # F§1: wikisource.org
            out.append(dict(code="mul", dbname=site["dbname"], domain=site["url"].split("//", 1)[1],
                            autonym="Wikisource", name="Multilingual"))
    out.sort(key=lambda s: s["code"])
    PATH.parent.mkdir(parents=True, exist_ok=True)
    PATH.write_text(json.dumps(out, ensure_ascii=False, indent=1), "utf-8")
    return out


def site(code: str) -> dict | None:
    return next((s for s in wikis() if s["code"] == code), None)


if __name__ == "__main__":
    for s in wikis(refresh=True):
        print(f"{s['code']:8} {s['dbname']:20} {s['domain']:28} {s['autonym']}")
