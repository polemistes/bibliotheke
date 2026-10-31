# SPDX-License-Identifier: GPL-3.0-or-later
"""Fixture rows for the pipeline tests (PLAN.md 5.6): a synthetic extract
built with the real parser from a few wikitext pages, under the no config."""

from __future__ import annotations

from array import array

import pytest

from wscatalog import config, extract
from wscatalog.extract import UNSET, Namespaces, parse_ns0
from wscatalog.wikidata import Wikidata

SITE_NS = {0: "", 6: "Fil", 10: "Mal", 14: "Kategori", 102: "Forfatter", 104: "Side", 106: "Indeks"}
FILLER = "Dette er en tekst som er lang nok til at den ikke regnes som søppel. " * 6


def make_ext(wiki: str, pages: dict[str, str], *, redirects=None, levels=None, indexes=None,
             qids=None, authors=None, defaultsort=None, disamb=()) -> dict:
    cfg = config.load(wiki)
    ns = Namespaces(cfg, SITE_NS)
    recs = {}
    for pid, (title, text) in enumerate(pages.items(), 1):
        recs[title] = parse_ns0(title, pid, len(text.encode()), text, cfg, ns,
                                cfg.templates_of_interest(), cfg.positional_headers)
    lv, sz = {}, {}
    for f, vals in (levels or {}).items():
        lv[f] = bytearray([UNSET] + list(vals))
        sz[f] = array("I", [0] + [2000] * len(vals))
    return dict(pages=recs, redirects=redirects or {}, levels=lv, sizes=sz, single={}, plinks={},
                indexes=indexes or {}, ids={r.page_id: t for t, r in recs.items()},
                authors=authors or {}, author_redirects={}, files=set(), site_ns=SITE_NS,
                disamb={recs[t].page_id for t in disamb}, qids=qids or {}, displaytitle={},
                defaultsort=defaultsort or {}, dump=[f"{cfg.dbname}-20261001-pages-articles.xml.bz2"],
                wiki=wiki, count=len(recs))


def offline_wikidata(tmp_path, items: dict | None = None) -> Wikidata:
    wd = Wikidata(offline=True, path=tmp_path / "wikidata.sqlite")
    wd.mem.update(items or {})
    return wd


@pytest.fixture
def no_ext():
    """A small no.wikisource: a play, a collection with a missing poem, a
    scanned book, raw OCR, a disambiguation page, junk, a redirect."""
    pages = {
        "Peer Gynt": "{{Topp|forfatter=[[Forfatter:Henrik Ibsen|Henrik Ibsen]]|år=1867}}\n" + FILLER
                     + "\n[[Kategori:Skuespill]]",
        "Digte": "{{Topp|forfatter=Henrik Ibsen|år=1871}}\n'''Indhold'''\n* [[/Paa Vidderne/]]\n"
                 "* [[/Terje Vigen/]]\n* [[/Kapitel 1/]]\n* [[/Mangler/]]\n[[Kategori:Diktsamlinger]]",
        "Digte/Paa Vidderne": "{{Topp|tittel=[[Digte]]|forfatter=Henrik Ibsen}}\n" + FILLER,
        "Digte/Terje Vigen": "{{Topp|tittel=[[Digte]]|forfatter=Henrik Ibsen}}\n" + FILLER,
        "Digte/Kapitel 1": "{{Topp|tittel=[[Digte]]}}\n" + FILLER,
        "Skannet bok": '<pages index="Skannet bok.djvu" from=1 to=3 header=1 />',
        "Raa tekst": '<pages index="Raa.djvu" from=1 to=2 header=1 />',
        "Brand": "{{Peker}}\n* [[Brand (1866)]]\n* [[Brand (1885)]]",
        "Søppel": "{{slett}} kort",
    }
    return make_ext(
        "no", pages,
        redirects={"Terje Vigen": "Digte/Terje Vigen"},
        levels={"Skannet bok.djvu": [4, 4, 3], "Raa.djvu": [1, 1]},
        indexes={"Skannet bok.djvu": {"type": "book", "tittel": "[[Skannet bok]]",
                                      "forfatter": "Løchen, arne", "ar": "1900", "malform": "NN"},
                 "Raa.djvu": {"type": "book", "tittel": "[[Raa tekst]]", "ar": "1880"}},
        authors={"Forfatter:Henrik Ibsen": 9001}, qids={9001: "Q36661"},
        defaultsort={9001: "Ibsen, Henrik"}, disamb=["Brand"])
