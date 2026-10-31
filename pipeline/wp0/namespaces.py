# SPDX-License-Identifier: GPL-3.0-or-later
"""WP0: confirm namespace numbers for the 11 configured wikis.

Writes docs/wp0/namespaces.json and docs/wp0/sitematrix.json, and prints a
Markdown summary table for docs/wp0-findings.md.

Run from the repo root:  python3 pipeline/wp0/namespaces.py
"""

from __future__ import annotations

import json
from pathlib import Path

import wmhttp

ROOT = Path(__file__).resolve().parents[2]
OUT = ROOT / "docs" / "wp0"

CONFIGURED = {
    "no": "no.wikisource.org", "en": "en.wikisource.org",
    "fr": "fr.wikisource.org", "de": "de.wikisource.org",
    "it": "it.wikisource.org", "sv": "sv.wikisource.org",
    "da": "da.wikisource.org", "fi": "fi.wikisource.org",
    "is": "is.wikisource.org", "es": "es.wikisource.org",
    "mul": "wikisource.org",
}

# Local and canonical names of the author namespace on the configured wikis.
# Matched case-insensitively against namespace names and aliases.
AUTHOR_NAMES = {"author", "forfatter", "auteur", "autor", "autore",
                "författare", "kirjailija", "höfundur"}


def sitematrix() -> list[dict]:
    data = wmhttp.get_json("https://meta.wikimedia.org/w/api.php", {
        "action": "sitematrix", "format": "json", "formatversion": "2",
        "smsiteprop": "url|dbname|code|sitename", "smlangprop": "code|name|localname|site",
    })
    wikis = []
    for key, entry in data["sitematrix"].items():
        if key == "count":
            continue
        sites = entry if key == "specials" else entry.get("site", [])
        for site in sites:
            if key == "specials":
                if site.get("code") != "sources":
                    continue
                lang = {"code": "mul", "name": "multilingual", "localname": "Multilingual"}
            else:
                if site.get("code") != "wikisource":
                    continue
                lang = entry
            wikis.append({
                "code": lang["code"], "autonym": lang.get("name"),
                "english_name": lang.get("localname"), "url": site["url"],
                "dbname": site["dbname"], "sitename": site.get("sitename"),
                "closed": bool(site.get("closed", False)),
            })
    return sorted(wikis, key=lambda w: w["code"])


def siteinfo(domain: str) -> dict:
    data = wmhttp.action_api(domain, action="query", meta="siteinfo|proofreadinfo",
                             siprop="general|namespaces|namespacealiases",
                             piprop="namespaces|qualitylevels")
    q = data["query"]
    general = q["general"]
    aliases: dict[int, list[str]] = {}
    for alias in q.get("namespacealiases", []):
        aliases.setdefault(alias["id"], []).append(alias["alias"])
    namespaces = {}
    for ns in q["namespaces"].values():
        if ns["id"] < 100 and ns["id"] not in (0,):
            continue  # core namespaces are identical on every wiki
        namespaces[ns["id"]] = {"name": ns["name"], "canonical": ns.get("canonical"),
                                "aliases": aliases.get(ns["id"], [])}
    author = [nid for nid, ns in namespaces.items()
              if {n.lower() for n in [ns["name"], ns["canonical"] or "", *ns["aliases"]]}
              & AUTHOR_NAMES and nid % 2 == 0]
    pr = q.get("proofreadnamespaces") or q.get("proofreadinfo", {}).get("namespaces", {})
    return {
        "domain": domain,
        "sitename": general["sitename"],
        "lang": general["lang"],
        "dbname": general["wikiid"],
        "rtl": "rtl" in general,
        "mainpage": general["mainpage"],
        "generator": general["generator"],
        "author_ns": author[0] if len(author) == 1 else author or None,
        "index_ns": pr.get("index", {}).get("id"),
        "page_ns": pr.get("page", {}).get("id"),
        "quality_levels": q.get("proofreadqualitylevels"),
        "namespaces": namespaces,
    }


def main() -> None:
    OUT.mkdir(parents=True, exist_ok=True)
    matrix = sitematrix()
    (OUT / "sitematrix.json").write_text(json.dumps(matrix, ensure_ascii=False, indent=1) + "\n")
    open_wikis = [w for w in matrix if not w["closed"]]
    print(f"Sitematrix: {len(matrix)} Wikisource wikis, {len(open_wikis)} open, "
          f"{len(matrix) - len(open_wikis)} closed\n")

    results = {code: siteinfo(domain) for code, domain in CONFIGURED.items()}
    (OUT / "namespaces.json").write_text(json.dumps(results, ensure_ascii=False, indent=1) + "\n")

    print("| wiki | sitename | lang | dbname | Author | Index | Page | other content-ish ns (>=100, even) |")
    print("|---|---|---|---|---|---|---|---|")
    for code, r in results.items():
        known = {r["author_ns"], r["index_ns"], r["page_ns"]}
        others = ", ".join(f"{nid} {ns['name']}" for nid, ns in sorted(r["namespaces"].items())
                           if nid >= 100 and nid % 2 == 0 and nid not in known)
        author = r["author_ns"]
        author_name = r["namespaces"][author]["name"] if isinstance(author, int) else "?"
        index_name = r["namespaces"].get(r["index_ns"], {}).get("name", "?")
        page_name = r["namespaces"].get(r["page_ns"], {}).get("name", "?")
        print(f"| {code} | {r['sitename']} | {r['lang']} | {r['dbname']} | "
              f"{author} {author_name} | {r['index_ns']} {index_name} | "
              f"{r['page_ns']} {page_name} | {others} |")


if __name__ == "__main__":
    main()
