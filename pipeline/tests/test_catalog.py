# SPDX-License-Identifier: GPL-3.0-or-later
"""Catalog writer (5.2 steps 4-8, schema 5.3) and the quality filters (5.4)
on fixture rows."""

import gzip
import hashlib
import json
import sqlite3

from wscatalog import catalog
from wscatalog.quality import tier_from_pages
from collections import Counter

from conftest import offline_wikidata


def test_tiers():
    assert tier_from_pages(Counter({4: 10})) == ("validated", False)
    assert tier_from_pages(Counter({4: 5, 3: 5})) == ("proofread", False)
    assert tier_from_pages(Counter({3: 95, 1: 5})) == ("proofread", True)
    assert tier_from_pages(Counter({3: 5, 1: 5})) == ("unproofread", False)
    assert tier_from_pages(Counter({0: 3})) == (None, False)


def build(ext, tmp_path):
    b = catalog.Build("no", ext=ext, wd=offline_wikidata(tmp_path))
    b.write(tmp_path)
    return b, sqlite3.connect(tmp_path / "no.sqlite")


def test_catalog(no_ext, tmp_path):
    b, con = build(no_ext, tmp_path)
    q = lambda sql, *a: con.execute(sql, a).fetchall()
    meta = dict(q("SELECT key, value FROM meta"))
    assert meta["schema_version"] == "2" and meta["wiki"] == "no" and meta["dump_date"] == "20261001"
    works = {r[0]: r for r in q("SELECT page_title, structure, part_count, text_count, "
                                "missing_part_count, quality, complete, incomplete_reason, type FROM works")}
    assert set(works) == {"Peer Gynt", "Digte", "Skannet bok", "Raa tekst"}
    # the collection: three parts, two texts, one red link
    assert works["Digte"][1:8] == ("subpages", 3, 2, 1, "unknown", 0, "missing-parts")
    assert works["Skannet bok"][5:7] == ("proofread", 1)
    assert works["Raa tekst"][5] == "unproofread"
    parts = q("SELECT ord, depth, page_title, display_title, kind, searchable FROM parts "
              "WHERE work_id = (SELECT id FROM works WHERE page_title = 'Digte') ORDER BY ord")
    assert [p[4] for p in parts] == ["toc", "text", "text", "chapter"]
    assert parts[1][3] is None                 # display title = last title segment: stored as NULL
    # every exclusion is explained
    assert dict(q("SELECT page_title, reason FROM excluded")) == {
        "Brand": "disambiguation", "Søppel": "junk-template"}
    assert q("SELECT alias_title, ord FROM aliases") == [("Terje Vigen", 2)]
    # search: works by author, texts by title (parts_fts rowid = parts.id)
    hits = q("SELECT w.page_title FROM works_fts JOIN works w ON w.id = works_fts.rowid "
             "WHERE works_fts MATCH 'Ibsen' ORDER BY w.page_title")
    assert [h[0] for h in hits] == ["Digte", "Peer Gynt"]
    row = q("SELECT rowid FROM parts_fts WHERE parts_fts MATCH 'vidderne'")
    wid = q("SELECT id FROM works WHERE page_title = 'Digte'")[0][0]
    assert row == q("SELECT id FROM parts WHERE work_id = ? AND ord = 1", wid)
    assert meta["show_unknown_source"] == "config"
    authors = q("SELECT name, sort_name, author_page_title FROM authors")
    assert ("Henrik Ibsen", "Ibsen, Henrik", "Forfatter:Henrik Ibsen") in authors
    # gzip and sha256 match the file
    gz = (tmp_path / "no.sqlite.gz").read_bytes()
    assert hashlib.sha256(gz).hexdigest() == b.files["sha256"]
    assert gzip.decompress(gz) == (tmp_path / "no.sqlite").read_bytes()


def test_default_view_and_manifest(no_ext, tmp_path, monkeypatch):
    b, _ = build(no_ext, tmp_path)
    # no shows unknown works by default; raw OCR never
    assert {w.root.title for w in b.default_view(False)} == {"Skannet bok"}
    assert {w.root.title for w in b.default_view(True)} == {"Skannet bok", "Peer Gynt"}
    monkeypatch.setattr(catalog.sites, "site", lambda code: {"autonym": "norsk"})
    e = b.manifest_entry()
    assert e["show_unknown_by_default"] is True and e["default_view_count"] == 2
    assert e["tiers"] == {"validated": 0, "proofread": 1, "unproofread": 1, "unknown": 2}
    assert e["url"] == "no.sqlite.gz" and e["schema_version"] == 2
    (tmp_path / "no.json").write_text(json.dumps(e))
    m = json.loads(catalog.write_manifest(tmp_path).read_text())
    assert [w["code"] for w in m["wikis"]] == ["no"]
