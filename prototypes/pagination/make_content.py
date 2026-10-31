# SPDX-License-Identifier: GPL-3.0-or-later
"""WP0 spike: turn raw Parsoid fixtures into reader HTML for the pagination
prototype.

A deliberately small subset of the PLAN.md 7.2 normaliser (the real one is
Kotlin, WP3), including the corrections found in WP0 (docs/wp0-findings.md
section 2): page markers from `span.pagenum` / `span.pr1pn`, source line
numbers extracted before `.ws-noexport` is removed, footnote refs from
`sup.mw-ref`, `dl/dd/dt` kept. Images are dropped (the prototype is offline).

Writes web/content/{name}.js, each defining WSR_PARTS[name] = {title, lang, html},
so the harness can load content from file:// without fetch().

    python3 prototypes/pagination/make_content.py
"""

from __future__ import annotations

import json
import re
from pathlib import Path

from bs4 import BeautifulSoup, NavigableString, Tag

ROOT = Path(__file__).resolve().parents[2]
FIXTURES = ROOT / "app" / "src" / "test" / "fixtures"
OUT = Path(__file__).resolve().parent / "web" / "content"

CONTENT = {
    # name: (fixture, language)
    "sult-02": ("no/sult-02", "nb"),
    "ved-rundarne-1864": ("no/ved-rundarne-1864", "nn"),
    "peer-gynt-1": ("no/peer-gynt-1", "nb"),
    "erlkoenig": ("de/erlkoenig", "de"),
    "kaethe": ("de/kaethe", "de"),
}

ALLOWED = {"h1", "h2", "h3", "h4", "h5", "h6", "p", "div", "span", "br", "hr", "blockquote",
           "ol", "ul", "li", "table", "thead", "tbody", "tr", "th", "td", "figure",
           "figcaption", "sup", "sub", "em", "strong", "i", "b", "small", "a", "section",
           "dl", "dd", "dt"}
STYLE_WHITELIST = {"text-align", "font-style", "font-weight", "font-variant", "text-indent",
                   "margin-left"}
# No "lang": Parsoid stamps the wiki language on prp-pages-output (nb on
# Nynorsk texts); the work language is set on <html> instead.
KEEP_ATTRS = {"class", "id", "href", "data-num", "data-page-title", "data-n", "style",
              "colspan", "rowspan"}
DROP_SELECTORS = [".ws-noexport", ".noprint", ".mw-editsection", ".navbox", ".Z3988",
                  "style", "script", "link", "meta", "img", "figure[typeof~='mw:File']",
                  "span[typeof~='mw:File']", ".mw-cite-backlink"]
LICENCE_TEMPLATE = re.compile(r"^(PD|CC|Offentlig|GFDL)", re.I)


def page_markers(body: Tag, soup: BeautifulSoup) -> None:
    for el in body.select("span.pagenum, span.ws-pagenum, span.pr1pn"):
        num = el.get("data-page-number") or el.get("id", "")
        if el.get("class") and "pr1pn" in el["class"]:
            num = el.get_text(strip=True).strip("[]") or num.removeprefix("Seite_")
        title = (el.get("data-page-name") or el.get("title") or "").replace("_", " ")
        mark = soup.new_tag("span", attrs={"class": "wsr-page", "data-num": num})
        if title:
            mark["data-page-title"] = title
        el.replace_with(mark)


def line_numbers(body: Tag, soup: BeautifulSoup) -> None:
    # fr {{NumVers}}: the visible number is the text of the OptionText span;
    # the outer visibility:hidden wrapper goes too.
    for el in body.select('span.OptionText[title="Vers numérotés"]'):
        ln = soup.new_tag("span", attrs={"class": "wsr-ln-src"})
        ln.string = el.get_text(strip=True)
        target = el.parent if el.parent and el.parent.name == "span" else el
        target.replace_with(ln)
    # de: <div class="ws-noexport"><span class="zeilennummer">5</span></div>
    for el in body.select("span.zeilennummer"):
        ln = soup.new_tag("span", attrs={"class": "wsr-ln-src"})
        ln.string = el.get_text(strip=True)
        wrapper = el.parent if el.parent and el.parent.name == "div" else el
        wrapper.replace_with(ln)


def footnotes(body: Tag, soup: BeautifulSoup) -> None:
    for sup in body.select("sup.mw-ref"):
        a = sup.find("a")
        frag = a["href"].split("#", 1)[1] if a and "#" in a.get("href", "") else ""
        label = sup.get_text("", strip=True).strip("[]").strip()
        new = soup.new_tag("sup", attrs={"class": "wsr-fnref"})
        link = soup.new_tag("a", attrs={"href": f"#{frag}"})
        link.string = label
        new.append(link)
        sup.replace_with(new)
    for ol in body.select("ol.mw-references"):
        section = soup.new_tag("section", attrs={"class": "wsr-footnotes"})
        if ol.get("data-mw-group"):
            section["data-group"] = ol["data-mw-group"]
        new_ol = soup.new_tag("ol")
        for li in ol.find_all("li", recursive=False):
            new_li = soup.new_tag("li", attrs={"id": li.get("id", "")})
            text = li.select_one(".reference-text")
            for child in list((text or li).contents):
                new_li.append(child)
            new_ol.append(new_li)
        section.append(new_ol)
        ol.replace_with(section)


def is_marker(node) -> bool:
    return isinstance(node, Tag) and bool(node.get("class")) and \
        node["class"][0] in ("wsr-page", "wsr-ln-src")


def verse(body: Tag, soup: BeautifulSoup) -> None:
    """div.poem > p (lines split by <br>) -> div.wsr-verse > div.wsr-stanza > span.wsr-line.

    Stanza breaks are blank lines (<br> <br>) only. A <p> boundary ends a line
    but not a stanza: Parsoid puts a whole poem in one <p> on no/fr, while on
    de it splits <p>s around templates, sometimes mid-stanza. The generated
    line counter re-syncs to source line numbers where they exist, so both
    numberings agree.
    """
    for poem in body.select("div.poem"):
        out = soup.new_tag("div", attrs={"class": "wsr-verse"})
        n = 0
        pending: list = []  # markers met before the next line
        stanza = soup.new_tag("div", attrs={"class": "wsr-stanza"})

        def end_stanza() -> None:
            nonlocal stanza
            if stanza.contents:
                if out.contents:
                    out.append(NavigableString("\n"))
                out.append(stanza)
                stanza = soup.new_tag("div", attrs={"class": "wsr-stanza"})

        def flush_line(nodes: list) -> None:
            nonlocal n
            text = "".join(x.get_text() if isinstance(x, Tag) else str(x) for x in nodes)
            if not text.strip():
                pending.extend(x for x in nodes if is_marker(x))
                end_stanza()  # blank line
                return
            items = pending + nodes
            src = next((x for x in items if is_marker(x) and "wsr-ln-src" in x["class"]), None)
            n = int(src.get_text()) if src and src.get_text().isdigit() else n + 1
            line = soup.new_tag("span", attrs={"class": "wsr-line", "data-n": str(n)})
            for x in items:
                line.append(x)
            pending.clear()
            if stanza.contents:
                # Lines are display:block, so this is invisible, but it keeps
                # lines apart in the locator text (bookmark excerpts, search).
                stanza.append(NavigableString("\n"))
            stanza.append(line)

        for child in list(poem.children):
            if isinstance(child, NavigableString):
                continue
            if child.name != "p":
                markers = [child] if is_marker(child) else child.select(".wsr-page, .wsr-ln-src")
                pending.extend(m.extract() for m in markers)
                continue
            current: list = []
            for node in list(child.contents):
                if isinstance(node, Tag) and node.name == "br":
                    flush_line(current)
                    current = []
                else:
                    current.append(node.extract() if isinstance(node, Tag)
                                   else NavigableString(str(node).strip("\n")))
            if current:
                flush_line(current)
        end_stanza()
        poem.replace_with(out)


def drop_licence_tables(body: Tag) -> None:
    for table in body.select("table[typeof~='mw:Transclusion']"):
        try:
            parts = json.loads(table.get("data-mw", "{}")).get("parts", [])
        except json.JSONDecodeError:
            continue
        names = [p["template"]["target"].get("wt", "") for p in parts if isinstance(p, dict) and "template" in p]
        if any(LICENCE_TEMPLATE.match(n) for n in names):
            table.decompose()


def clean(body: Tag) -> None:
    for el in body.find_all(True):
        if el.name not in ALLOWED:
            el.unwrap()
            continue
        for attr in list(el.attrs):
            if attr not in KEEP_ATTRS:
                del el[attr]
        if "class" in el.attrs:
            keep = [c for c in el["class"] if c.startswith("wsr-")]
            if keep:
                el["class"] = keep
            else:
                del el["class"]
        if el.get("id") and not el["id"].startswith(("cite_note", "cite_ref")):
            del el["id"]
        if el.name == "a":
            href = el.get("href", "")
            if not href.startswith("#"):
                el.unwrap()
                continue
        if "style" in el.attrs:
            decls = [d.strip() for d in el["style"].split(";") if ":" in d]
            kept = [d for d in decls if d.split(":", 1)[0].strip().lower() in STYLE_WHITELIST]
            if kept:
                el["style"] = "; ".join(kept)
            else:
                del el["style"]
    # Plain spans and empty paragraphs left behind by unwrapping/removal.
    for el in body.find_all("span"):
        if not el.attrs:
            el.unwrap()
    for el in body.find_all(["p", "div"]):
        if not el.get_text(strip=True) and not el.find(class_=["wsr-page", "wsr-ln-src"]):
            el.decompose()


def normalise(raw: str) -> tuple[str, str]:
    soup = BeautifulSoup(raw, "lxml")
    body = soup.body
    title = soup.title.get_text() if soup.title else ""
    page_markers(body, soup)
    line_numbers(body, soup)
    drop_licence_tables(body)
    for sel in DROP_SELECTORS:
        for el in body.select(sel):
            el.decompose()
    footnotes(body, soup)
    verse(body, soup)
    clean(body)
    html = "".join(str(c) for c in body.contents).strip()
    html = re.sub(r"\n{2,}", "\n", html)
    return title, html


def main() -> None:
    OUT.mkdir(parents=True, exist_ok=True)
    for name, (fixture, lang) in CONTENT.items():
        raw = (FIXTURES / fixture / "raw.html").read_text("utf-8")
        title, html = normalise(raw)
        payload = json.dumps({"title": title, "lang": lang, "html": html}, ensure_ascii=False)
        (OUT / f"{name}.js").write_text(
            f"// Generated by make_content.py from app/src/test/fixtures/{fixture} (Wikisource; "
            f"public domain text, CC BY-SA 4.0 contributor layer).\n"
            f"(window.WSR_PARTS = window.WSR_PARTS || {{}})[{json.dumps(name)}] = {payload};\n",
            "utf-8")
        text_len = len(BeautifulSoup(html, "lxml").get_text())
        print(f"{name}: {len(html):>8} bytes html, {text_len:>7} chars text, "
              f"{html.count('wsr-page')} page marks, {html.count('wsr-line')} verse lines, "
              f"{html.count('wsr-ln-src')} source line numbers, {html.count('wsr-fnref')} fn refs")


if __name__ == "__main__":
    main()
