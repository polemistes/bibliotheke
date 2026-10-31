# SPDX-License-Identifier: GPL-3.0-or-later
"""Merge logic of PLAN.md 5.2 step 3 on fixture rows."""

from wscatalog import structure
from wscatalog.metadata import Merger, parse_year, sort_name

from conftest import offline_wikidata


def test_parse_year():
    assert parse_year("1867") == (1867, 9)
    assert parse_year("[[1905]]") == (1905, 9)
    assert parse_year("ca. 1850") == (1850, 9)
    assert parse_year("1850-tallet") == (1850, 8)
    assert parse_year("off") is None
    assert parse_year("1. utgave") is None
    assert parse_year(None) is None


def test_sort_name():
    assert sort_name("Henrik Ibsen") == "Ibsen, Henrik"
    assert sort_name("Ibsen, Henrik") == "Ibsen, Henrik"
    assert sort_name("Snorri") == "Snorri"


def merger(ext, tmp_path, items=None):
    res = structure.resolve("no", ext)
    return res, Merger(res, offline_wikidata(tmp_path, items))


def test_people(no_ext, tmp_path):
    _, m = merger(no_ext, tmp_path)
    p = m.people("[[Forfatter:Henrik Ibsen|Henrik Ibsen]]")
    assert [(x.name, x.page) for x in p] == [("Henrik Ibsen", "Forfatter:Henrik Ibsen")]
    assert [x.name for x in m.people("Peter Christen Asbjørnsen og Jørgen Moe")] == \
        ["Peter Christen Asbjørnsen", "Jørgen Moe"]
    inv = m.people("Løchen, arne")
    assert [(x.name, x.sort) for x in inv] == [("Arne Løchen", "Løchen, Arne")]
    assert [x.name for x in m.people("{{forfatterl|Claus Frimann|C. Frimann}}")] == ["Claus Frimann"]
    assert m.people("Ukjent") == []
    # a plain name resolves to the existing author page
    assert m.people("Henrik Ibsen")[0].page == "Forfatter:Henrik Ibsen"


def test_merge_sources(no_ext, tmp_path):
    items = {"Q36661": {"l": {"nb": "Henrik Ibsen"}, "d": {}, "s": {},
                        "c": {"P569": [[1828, 11]], "P570": [[1906, 11]]}}}
    res, m = merger(no_ext, tmp_path, items)
    works = {w.root.title: w for w in res.works}
    peer = m.merge(works["Peer Gynt"], None)
    assert [a.name for a in peer.authors] == ["Henrik Ibsen"] and peer.author_source == "header"
    ibsen = peer.authors[0]
    assert (ibsen.qid, ibsen.sort, ibsen.birth, ibsen.death) == ("Q36661", "Ibsen, Henrik", 1828, 1906)
    assert (peer.year, peer.year_precision, peer.year_source) == (1867, 9, "header")
    assert peer.genres == ["drama"] and peer.type == "play"
    assert peer.sort_title == "peer gynt"
    book = m.merge(works["Skannet bok"], None)
    assert [a.name for a in book.authors] == ["Arne Løchen"] and book.author_source == "index"
    assert (book.year, book.year_source) == (1900, "index")
    assert (book.language, book.language_source) == ("nn", "index")
    assert book.type == "book" and book.type_source == "index"
    digte = m.merge(works["Digte"], None)
    assert digte.genres == ["poetry"] and digte.type in ("collection", "book")


def test_document_type(no_ext, tmp_path):
    from conftest import make_ext, FILLER
    ext = make_ext("no", {"Dom 2011": "{{Norsk rettsavgjørelse}}\n" + FILLER})
    res, m = merger(ext, tmp_path)
    meta = m.merge(res.works[0], None)
    assert meta.type == "document" and "legal-government" in meta.genres
