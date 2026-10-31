# SPDX-License-Identifier: GPL-3.0-or-later
"""WP0: fetch Parsoid HTML and report the shape of the markup we rely on.

    python3 pipeline/wp0/parsoid.py analyse no.wikisource.org 'Peer Gynt/Første akt'
    python3 pipeline/wp0/parsoid.py save no peer-gynt-1 no.wikisource.org 'Peer Gynt/Første akt'

`save` writes app/src/test/fixtures/{wiki}/{slug}/raw.html plus source.json
(title, revision, fetch date, licence notice) and prints the analysis.
"""

from __future__ import annotations

import json
import re
import sys
from datetime import date
from pathlib import Path
from urllib.parse import quote, unquote

from bs4 import BeautifulSoup, Tag

import wmhttp

ROOT = Path(__file__).resolve().parents[2]
FIXTURES = ROOT / "app" / "src" / "test" / "fixtures"


def rest_url(domain: str, title: str) -> str:
    # Subpage slashes must be encoded (%2F); a raw "/" gives rest-no-match.
    return f"https://{domain}/w/rest.php/v1/page/{quote(title.replace(' ', '_'), safe='')}/html"


def fetch(domain: str, title: str) -> str:
    status, html = wmhttp.get(rest_url(domain, title))
    if status != 200:
        raise wmhttp.HttpError(status, rest_url(domain, title), html[:300])
    return html


def outer(el: Tag, limit: int = 400) -> str:
    """Opening tag of el with data-mw/typeof noise trimmed, for reports."""
    attrs = {k: v for k, v in el.attrs.items() if k not in ("data-mw", "about")}
    attr_s = " ".join(f'{k}="{" ".join(v) if isinstance(v, list) else v}"' for k, v in attrs.items())
    text = el.get_text(" ", strip=True)[:60]
    return f"<{el.name} {attr_s}> {text!r}"[:limit]


SELECTORS = {
    "ws-summary": ".ws-summary",
    "ws-noexport": ".ws-noexport",
    "header (#headertemplate, .headertemplate, .ws-header)": "#headertemplate, .headertemplate, .ws-header",
    "pagenum (.pagenum, .ws-pagenum)": ".pagenum, .ws-pagenum",
    "prp-pages-output": ".prp-pages-output",
    "poem (.poem)": ".poem",
    "references list (ol.references, .mw-references)": "ol.references, .mw-references",
    "footnote ref (sup.reference)": "sup.reference",
    "prev/next (.ws-prev, .ws-next, .headertemplate-prev/next)":
        ".ws-prev, .ws-next, [class*='prev'], [class*='next']",
    "COinS (.Z3988)": ".Z3988",
    "noprint": ".noprint",
    "navbox": ".navbox",
    "mw-editsection": ".mw-editsection",
    "images (img)": "img",
    "tables (table)": "table",
}
MICROFORMATS = ["ws-title", "ws-author", "ws-translator", "ws-year", "ws-publisher",
                "ws-place", "ws-type", "ws-chapter", "ws-cover"]


def analyse(html: str, title: str) -> dict:
    soup = BeautifulSoup(html, "lxml")
    body = soup.body
    report: dict = {"bytes": len(html.encode()),
                    "data-mw bytes": sum(len(t.get("data-mw", "")) for t in soup.find_all(attrs={"data-mw": True})),
                    "selectors": {}, "microformats": {}}
    for label, sel in SELECTORS.items():
        found = body.select(sel)
        report["selectors"][label] = {"count": len(found),
                                      "examples": [outer(e) for e in found[:2]]}
    for mf in MICROFORMATS:
        els = body.select(f".{mf}")
        if els:
            report["microformats"][mf] = [e.get_text(" ", strip=True)[:80] for e in els[:2]]
    prefix = "./" + title.replace(" ", "_") + "/"
    subs = []
    for a in body.select("a[rel~='mw:WikiLink']"):
        href = unquote(a.get("href", ""))
        if href.startswith(prefix) and href.split("#")[0] not in subs:
            subs.append(href.split("#")[0])
    report["subpage links"] = {"count": len(subs), "first": subs[:5]}
    summary_subs = [a for s in body.select(".ws-summary") for a in s.select("a[rel~='mw:WikiLink']")]
    report["subpage links inside .ws-summary"] = sum(
        1 for a in summary_subs if unquote(a.get("href", "")).startswith(prefix))
    # Candidate source line-number markup: classes mentioning num/ligne/vers/line.
    classes = {c for el in body.find_all(True) for c in el.get("class", [])}
    report["line-number-ish classes"] = sorted(c for c in classes
                                               if re.search(r"num|ligne|vers|line|zeile", c, re.I)
                                               and c not in ("pagenum", "ws-pagenum"))
    report["all classes"] = sorted(classes)
    report["section elements"] = len(body.find_all("section"))
    report["text chars"] = len(body.get_text())
    return report


def print_report(report: dict) -> None:
    print(f"  bytes={report['bytes']}  data-mw bytes={report['data-mw bytes']}  "
          f"text chars={report['text chars']}  sections={report['section elements']}")
    for label, info in report["selectors"].items():
        if info["count"]:
            print(f"  {info['count']:>4}  {label}")
            for ex in info["examples"]:
                print(f"          {ex}")
    for mf, vals in report["microformats"].items():
        print(f"        .{mf}: {vals}")
    print(f"  subpage links: {report['subpage links']}")
    print(f"  subpage links inside .ws-summary: {report['subpage links inside .ws-summary']}")
    print(f"  line-number-ish classes: {report['line-number-ish classes']}")


def revision_of(html: str) -> int | None:
    m = re.search(r'about="[^"]*/revision/(\d+)"', html)
    return int(m.group(1)) if m else None


def save(wiki: str, slug: str, domain: str, title: str) -> dict:
    html = fetch(domain, title)
    out = FIXTURES / wiki / slug
    out.mkdir(parents=True, exist_ok=True)
    (out / "raw.html").write_text(html, "utf-8")
    report = analyse(html, title)
    source = {
        "wiki": wiki, "domain": domain, "title": title,
        "url": f"https://{domain}/wiki/{quote(title.replace(' ', '_'))}",
        "rest_url": rest_url(domain, title), "revision": revision_of(html),
        "fetched": date.today().isoformat(),
        "licence": "Text from Wikisource; public domain or CC BY-SA 4.0. "
                   "See the page history at the url for contributors.",
        "shape": {k: v["count"] for k, v in report["selectors"].items() if v["count"]},
    }
    (out / "source.json").write_text(json.dumps(source, ensure_ascii=False, indent=1) + "\n", "utf-8")
    return report


if __name__ == "__main__":
    cmd = sys.argv[1]
    if cmd == "analyse":
        domain, title = sys.argv[2], sys.argv[3]
        print(f"{domain} :: {title}")
        print_report(analyse(fetch(domain, title), title))
    elif cmd == "save":
        wiki, slug, domain, title = sys.argv[2:6]
        print(f"{wiki}/{slug} <- {domain} :: {title}")
        print_report(save(wiki, slug, domain, title))
    else:
        sys.exit(__doc__)
