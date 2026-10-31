# SPDX-License-Identifier: GPL-3.0-or-later
"""WP0: measure Wikidata metadata coverage for Norwegian works.

1. Enumerate all main-namespace root pages (no "/") on no.wikisource with
   their page props (QID, disambiguation flag).
2. Take 50 evenly spaced root pages that have a QID and are not
   disambiguation pages.
3. Fetch their items, follow P629 (edition -> work), and count how many have
   P577 (date), P136 (genre), P50 (author), P407 (language), directly or via
   the work item.

Writes docs/wp0/wikidata-no.json; prints a Markdown summary.
Run from the repo root:  python3 pipeline/wp0/wikidata_no.py
"""

from __future__ import annotations

import json
from collections import Counter
from pathlib import Path

import wmhttp

ROOT = Path(__file__).resolve().parents[2]
OUT = ROOT / "docs" / "wp0" / "wikidata-no.json"
WIKI = "no.wikisource.org"
SAMPLE_SIZE = 50
WD_API = "https://www.wikidata.org/w/api.php"

PROPS = {"P577": "publication date", "P136": "genre", "P50": "author",
         "P407": "language of work", "P31": "instance of"}
EDITION = "Q3331189"  # version, edition or translation
HUMAN = "Q5"


def root_pages() -> list[dict]:
    pages, cont = [], {}
    while True:
        data = wmhttp.action_api(WIKI, action="query", generator="allpages", gapnamespace=0,
                                 gapfilterredir="nonredirects", gaplimit="max",
                                 prop="pageprops", ppprop="wikibase_item|disambiguation",
                                 **cont)
        for p in data.get("query", {}).get("pages", []):
            if "/" not in p["title"]:
                props = p.get("pageprops", {})
                pages.append({"title": p["title"], "pageid": p["pageid"],
                              "qid": props.get("wikibase_item"),
                              "disambiguation": "disambiguation" in props})
        if "continue" not in data:
            return sorted(pages, key=lambda p: p["title"])
        cont = data["continue"]


def entities(qids: list[str]) -> dict[str, dict]:
    out = {}
    for i in range(0, len(qids), 50):
        data = wmhttp.get_json(WD_API, {
            "action": "wbgetentities", "ids": "|".join(qids[i:i + 50]), "format": "json",
            "props": "claims|labels|descriptions|sitelinks", "languages": "nb|nn|no|da|en",
            "sitefilter": "nowikisource",
        })
        out.update(data["entities"])
    return out


def values(entity: dict, prop: str) -> list:
    vals = []
    for claim in entity.get("claims", {}).get(prop, []):
        snak = claim["mainsnak"]
        if snak.get("snaktype") != "value":
            continue
        v = snak["datavalue"]["value"]
        vals.append(v["id"] if isinstance(v, dict) and "id" in v else
                    v.get("time") if isinstance(v, dict) else v)
    return vals


def labels(qids: set[str]) -> dict[str, str]:
    ents = entities(sorted(qids))
    return {q: (e.get("labels", {}).get("en") or e.get("labels", {}).get("nb") or {}).get("value", q)
            for q, e in ents.items()}


def header_coverage(pages: list[dict]) -> dict:
    """Author/year from the page header microformats, for root pages with or
    without a QID: the fallback source when Wikidata has nothing."""
    from bs4 import BeautifulSoup

    import parsoid

    candidates = [p for p in pages if not p["disambiguation"]]
    step = len(candidates) / SAMPLE_SIZE
    rows = []
    for i in range(SAMPLE_SIZE):
        p = candidates[int(i * step)]
        soup = BeautifulSoup(parsoid.fetch(WIKI, p["title"]), "lxml")
        text = {mf: (el.get_text(" ", strip=True) if (el := soup.select_one(f".{mf}")) else "")
                for mf in ("ws-author", "ws-year", "ws-title")}
        rows.append({"title": p["title"], "qid": p["qid"], **text,
                     "coins": bool(soup.select_one(".Z3988")),
                     "pages_tag": bool(soup.select_one(".prp-pages-output"))})
    n = len(rows)
    return {
        "sample_size": n,
        "with_qid": sum(bool(r["qid"]) for r in rows),
        "ws-author": sum(bool(r["ws-author"]) for r in rows),
        "ws-year": sum(bool(r["ws-year"]) for r in rows),
        "ws-year numeric": sum(any(ch.isdigit() for ch in r["ws-year"]) for r in rows),
        "ws-title": sum(bool(r["ws-title"]) for r in rows),
        "coins": sum(r["coins"] for r in rows),
        "uses <pages>": sum(r["pages_tag"] for r in rows),
        "rows": rows,
    }


def main() -> None:
    pages = root_pages()
    with_qid = [p for p in pages if p["qid"] and not p["disambiguation"]]
    disamb = sum(p["disambiguation"] for p in pages)
    step = len(with_qid) / SAMPLE_SIZE
    sample = [with_qid[int(i * step)] for i in range(SAMPLE_SIZE)]

    items = entities([p["qid"] for p in sample])
    work_qids = sorted({w for p in sample for w in values(items[p["qid"]], "P629")})
    works = entities(work_qids) if work_qids else {}

    rows, p31_counter, p407_counter, badge_counter = [], Counter(), Counter(), Counter()
    for p in sample:
        ed = items[p["qid"]]
        p31 = values(ed, "P31")
        work_ids = values(ed, "P629")
        work = works.get(work_ids[0]) if work_ids else None
        row = {"title": p["title"], "qid": p["qid"], "p31": p31, "work_qid": work_ids[0] if work_ids else None,
               "is_edition": EDITION in p31, "is_human": HUMAN in p31,
               "badges": ed.get("sitelinks", {}).get("nowikisource", {}).get("badges", [])}
        for prop in ("P577", "P136", "P50", "P407"):
            direct = values(ed, prop)
            via_work = values(work, prop) if work else []
            row[prop] = {"edition": direct, "work": via_work}
        rows.append(row)
        p31_counter.update(p31)
        p407_counter.update(row["P407"]["edition"] or row["P407"]["work"] or ["(none)"])
        badge_counter.update(row["badges"] or ["(none)"])

    names = labels({q for q in list(p31_counter) + list(p407_counter) + list(badge_counter)
                    if q.startswith("Q")})

    def count(prop: str, where: str) -> int:
        if where == "edition":
            return sum(bool(r[prop]["edition"]) for r in rows)
        if where == "work only":
            return sum(bool(r[prop]["work"]) and not r[prop]["edition"] for r in rows)
        return sum(bool(r[prop]["edition"] or r[prop]["work"]) for r in rows)

    summary = {
        "root_pages": len(pages), "root_pages_with_qid": sum(bool(p["qid"]) for p in pages),
        "disambiguation_root_pages": disamb, "sample_size": len(sample),
        "editions": sum(r["is_edition"] for r in rows), "humans": sum(r["is_human"] for r in rows),
        "with_work_item": sum(bool(r["work_qid"]) for r in rows),
        "coverage": {prop: {"edition": count(prop, "edition"), "work only": count(prop, "work only"),
                            "either": count(prop, "either")} for prop in ("P577", "P136", "P50", "P407")},
        "p31": {f"{q} {names.get(q, q)}": n for q, n in p31_counter.most_common()},
        "p407": {f"{q} {names.get(q, q)}": n for q, n in p407_counter.most_common()},
        "badges": {f"{q} {names.get(q, q)}": n for q, n in badge_counter.most_common()},
    }
    headers = header_coverage(pages)
    OUT.parent.mkdir(parents=True, exist_ok=True)
    OUT.write_text(json.dumps({"summary": summary, "sample": rows, "header_coverage": headers},
                              ensure_ascii=False, indent=1) + "\n")

    s = summary
    print(f"Root pages: {s['root_pages']}; with QID: {s['root_pages_with_qid']} "
          f"({100 * s['root_pages_with_qid'] / s['root_pages']:.0f} %); "
          f"disambiguation: {s['disambiguation_root_pages']}")
    print(f"Sample: {s['sample_size']} (every {step:.1f}th root page with a QID); "
          f"editions (P31 Q3331189): {s['editions']}; humans: {s['humans']}; "
          f"with P629 work item: {s['with_work_item']}\n")
    print("| property | on edition item | only via work (P629) | either |")
    print("|---|---|---|---|")
    for prop, c in s["coverage"].items():
        print(f"| {prop} {PROPS[prop]} | {c['edition']}/{len(rows)} | {c['work only']} | "
              f"**{c['either']}/{len(rows)}** |")
    for key in ("p31", "p407", "badges"):
        print(f"\n{key}: " + "; ".join(f"{k}: {v}" for k, v in s[key].items()))
    h = headers
    print(f"\nHeader microformats, {h['sample_size']} evenly spaced root pages "
          f"(any QID status; {h['with_qid']} have a QID):")
    for key in ("ws-author", "ws-year", "ws-year numeric", "ws-title", "coins", "uses <pages>"):
        print(f"  {key}: {h[key]}/{h['sample_size']}")


if __name__ == "__main__":
    main()
