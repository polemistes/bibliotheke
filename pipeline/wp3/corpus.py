# SPDX-License-Identifier: GPL-3.0-or-later
"""WP3: build the normaliser fixture corpus (PLAN.md 7.3) and the structure
fixtures (docs/work-structure-plan.md 7.4).

For each wiki, candidates come from the built catalog (pipeline/out/{wiki}.sqlite),
default view only, one category at a time: a novel chapter, a single poem, a poem
inside a collection, a play, a scan-backed text with page numbers, and pages with
footnotes, illustrations and tables. Each candidate is fetched (Parsoid REST, the
project User-Agent, one request at a time, cached in pipeline/cache/wp0) and kept
when its markup has the feature. The choice is written to
app/src/test/fixtures/corpus.json, so a rerun reproduces it.

    python3 pipeline/wp3/corpus.py            # every wiki, then the structure set
    python3 pipeline/wp3/corpus.py no de      # some wikis
    python3 pipeline/wp3/corpus.py --structure
"""

from __future__ import annotations

import json
import re
import sqlite3
import sys
import unicodedata
from pathlib import Path

sys.path.insert(0, str(Path(__file__).resolve().parents[1] / "wp0"))

import parsoid  # noqa: E402
import wmhttp  # noqa: E402

ROOT = Path(__file__).resolve().parents[2]
OUT = ROOT / "pipeline" / "out"
FIXTURES = ROOT / "app" / "src" / "test" / "fixtures"
CORPUS = FIXTURES / "corpus.json"
WIKIS = ["no", "en", "fr", "de", "it", "sv", "da", "fi", "is", "es"]
TRIES = 12   # fetches per category and wiki at most

DEFAULT_VIEW = ("w.complete = 1 AND w.quality IN ('validated', 'proofread'{unknown}) "
                "AND coalesce(w.type, '') NOT IN ('reference', 'document')")

# category -> (SQL giving page titles, feature check on the raw HTML)
GENRE = "EXISTS (SELECT 1 FROM work_genres wg JOIN genres g ON g.id = wg.genre_id WHERE wg.work_id = w.id AND g.key = '{g}')"
CATEGORIES = {
    "novel-chapter": (
        "SELECT p.page_title FROM works w JOIN parts p ON p.work_id = w.id WHERE {dv} AND " + GENRE.format(g="novel")
        + " AND w.parts_in_catalog = 1 AND w.part_count BETWEEN 6 AND 300 AND p.ord = 2 AND p.kind IN ('chapter', 'text')"
        " ORDER BY w.quality = 'validated' DESC, abs(random() % 1000)",
        lambda s: s["text"] > 3000),
    "poem": (
        "SELECT w.page_title FROM works w WHERE {dv} AND w.type = 'poem' AND w.structure = 'single'"
        " ORDER BY w.quality = 'validated' DESC, abs(random() % 1000)",
        lambda s: s["poem"] > 0),
    "poetry-collection-text": (
        "SELECT p.page_title FROM works w JOIN parts p ON p.work_id = w.id WHERE {dv} AND w.type = 'collection' AND "
        + GENRE.format(g="poetry") + " AND p.kind = 'text' AND p.ord BETWEEN 2 AND 6 ORDER BY abs(random() % 1000)",
        lambda s: s["poem"] > 0),
    "play": (
        "SELECT coalesce(p.page_title, w.page_title) FROM works w LEFT JOIN parts p ON p.work_id = w.id AND p.ord = 1"
        " WHERE {dv} AND " + GENRE.format(g="drama") + " ORDER BY abs(random() % 1000)",
        lambda s: s["text"] > 2000),
    "scan-pages": (
        "SELECT coalesce(p.page_title, w.page_title) FROM works w LEFT JOIN parts p ON p.work_id = w.id AND p.ord = 1"
        " WHERE {dv} AND w.quality_pages IS NOT NULL ORDER BY abs(random() % 1000)",
        lambda s: s["pagenum"] >= 3),
    "footnotes": (
        "SELECT coalesce(p.page_title, w.page_title) FROM works w LEFT JOIN parts p ON p.work_id = w.id AND p.ord = 1"
        " WHERE {dv} ORDER BY w.quality_pages IS NOT NULL DESC, abs(random() % 1000)",
        lambda s: s["refs"] >= 2),
    "illustrations": (
        "SELECT coalesce(p.page_title, w.page_title) FROM works w LEFT JOIN parts p ON p.work_id = w.id AND p.ord = 1"
        " WHERE {dv} AND w.cover_url IS NOT NULL ORDER BY abs(random() % 1000)",
        lambda s: s["img"] >= 1),
    "tables": (
        "SELECT coalesce(p.page_title, w.page_title) FROM works w LEFT JOIN parts p ON p.work_id = w.id AND p.ord = 1"
        " WHERE {dv} ORDER BY abs(random() % 1000)",
        lambda s: s["table"] >= 1),
}

# docs/work-structure-plan.md 7.4: (wiki, slug, title, case)
STRUCTURE = [
    ("de", "sagenschatz-sachsen", "Der Sagenschatz des Königreichs Sachsen", "A"),
    ("de", None, "@member:Der Sagenschatz des Königreichs Sachsen:1", "A"),
    ("de", None, "@member:Der Sagenschatz des Königreichs Sachsen:2", "A"),
    ("de", "khm-1857-band-1", "Kinder- und Haus-Märchen Band 1 (1857)", "A, I"),
    ("de", None, "@member:Kinder- und Haus-Märchen Band 1 (1857):1", "A, I"),
    ("no", "diktsamling", "Diktsamling", "A, red links"),
    ("no", "ved-rundarne-1864", "Ved Rundarne (1864)", "A, red links"),
    ("no", None, "@member:Diktsamling:prev-of:Ved Rundarne (1864)", "A"),
    ("fr", "legende-des-siecles", "La Légende des siècles", "B"),
    ("fr", "legende-clarte-d-ames", "La Légende des siècles/Clarté d’âmes", "B"),
    ("fr", None, "@alias:L’Épopée du ver", "B"),
    ("de", "kaethe", "Käthe", "C"),
    ("de", "heimath-zweiter-band", "Die Heimath in der neuen Welt/Zweiter Band", "D"),
    ("de", "heimath-erster-band", "Die Heimath in der neuen Welt/Erster Band", "D"),
    ("en", "shelley-hutchinson", "The Complete Poetical Works of Percy Bysshe Shelley (ed. Hutchinson, 1914)", "E"),
    ("en", "shelley-hutchinson-ozymandias", "The Complete Poetical Works of Percy Bysshe Shelley (ed. Hutchinson, 1914)/Ozymandias", "E"),
    ("en", "ozymandias-1818", "Ozymandias", "E"),
    ("en", "ozymandias-shelley-versions", "Ozymandias (Shelley)", "E"),
    ("fr", "madame-bovary", "Madame Bovary", "H"),
    ("fr", "madame-bovary-1", "Madame Bovary/Première partie", "H"),
    ("fr", "madame-bovary-1-1", "Madame Bovary/Première partie/1", "H"),
]


def slugify(title: str) -> str:
    s = title.lower()
    for a, b in (("ø", "o"), ("æ", "ae"), ("å", "a"), ("ß", "ss"), ("œ", "oe"), ("þ", "th"), ("ð", "d")):
        s = s.replace(a, b)
    s = unicodedata.normalize("NFKD", s).encode("ascii", "ignore").decode()
    s = re.sub(r"[^a-z0-9]+", "-", s).strip("-")
    return s[:60].rstrip("-")


def shape(html: str) -> dict:
    return {
        "pagenum": len(re.findall(r'class="[^"]*\b(?:pagenum|ws-pagenum|pr1pn|PageNumber)\b', html)),
        "refs": len(re.findall(r'class="mw-ref reference"', html)),
        "img": len([m for m in re.finditer(r'<img [^>]*width="(\d+)"', html) if int(m.group(1)) >= 100]),
        "table": html.count("<table") - html.count('class="mw-empty'),
        "poem": html.count('typeof="mw:Extension/poem'),
        "text": len(re.sub(r"<[^>]+>", "", re.sub(r"data-mw='[^']*'", "", html))),
    }


def load_corpus() -> dict:
    return json.loads(CORPUS.read_text("utf-8")) if CORPUS.exists() else {"pages": {}}


def save_corpus(c: dict) -> None:
    c["pages"] = dict(sorted(c["pages"].items()))
    CORPUS.write_text(json.dumps(c, ensure_ascii=False, indent=1) + "\n", "utf-8")


def save_page(corpus: dict, wiki: str, slug: str, domain: str, title: str, category: str) -> None:
    key = f"{wiki}/{slug}"
    if (FIXTURES / wiki / slug / "raw.html").exists():
        entry = corpus["pages"].setdefault(key, {"title": title})
    else:
        parsoid.save(wiki, slug, domain, title)
        entry = corpus["pages"].setdefault(key, {"title": title})
    cats = set(entry.get("categories", [])) | {category}
    entry["categories"] = sorted(cats)
    save_corpus(corpus)


def build_wiki(wiki: str, corpus: dict) -> None:
    db = sqlite3.connect(f"file:{OUT / (wiki + '.sqlite')}?mode=ro", uri=True)
    meta = dict(db.execute("SELECT key, value FROM meta"))
    domain = meta["domain"]
    dv = DEFAULT_VIEW.format(unknown=", 'unknown'" if meta.get("show_unknown_by_default") == "true" else "")
    have = {c for k, v in corpus["pages"].items() if k.startswith(wiki + "/") for c in v.get("categories", [])}
    used = {v["title"] for k, v in corpus["pages"].items() if k.startswith(wiki + "/")}
    for cat, (sql, ok) in CATEGORIES.items():
        if cat in have:
            continue
        titles = [r[0] for r in db.execute(sql.format(dv=dv) + " LIMIT 200")]
        tried = 0
        for t in titles:
            if t in used or tried >= TRIES:
                continue
            tried += 1
            try:
                html = parsoid.fetch(domain, t)
            except wmhttp.HttpError as e:
                print(f"  {wiki} {cat}: {t}: {e}")
                continue
            s = shape(html)
            if not ok(s):
                continue
            slug = slugify(t)
            print(f"  {wiki} {cat}: {t} -> {slug} {s}", flush=True)
            save_page(corpus, wiki, slug, domain, t, cat)
            used.add(t)
            break
        else:
            print(f"  {wiki} {cat}: none found in {tried} tries")
    db.close()


MIN_PER_WIKI = 8
FEATURES = ("poem", "pagenum", "refs", "img", "table")


def fill_wiki(wiki: str, corpus: dict) -> None:
    """Wikis with too few genres for the category queries (built without Wikidata):
    sample default-view pages up to MIN_PER_WIKI, preferring features not yet present."""
    have = [k for k in corpus["pages"] if k.startswith(wiki + "/")]
    if len(have) >= MIN_PER_WIKI:
        return
    db = sqlite3.connect(f"file:{OUT / (wiki + '.sqlite')}?mode=ro", uri=True)
    meta = dict(db.execute("SELECT key, value FROM meta"))
    dv = DEFAULT_VIEW.format(unknown=", 'unknown'" if meta.get("show_unknown_by_default") == "true" else "")
    titles = [r[0] for r in db.execute(
        "SELECT coalesce(p.page_title, w.page_title) FROM works w LEFT JOIN parts p ON p.work_id = w.id AND p.ord = 1"
        f" WHERE {dv} ORDER BY w.quality_pages IS NOT NULL DESC, abs(random() % 1000) LIMIT 60")]
    db.close()
    used = {corpus["pages"][k]["title"] for k in have}
    present = set()
    for k in have:
        raw = FIXTURES / k / "raw.html"
        if raw.exists():
            present |= {f for f, n in shape(raw.read_text("utf-8")).items() if f in FEATURES and n > 0}
    count = len(have)
    for t in titles:
        if count >= MIN_PER_WIKI:
            break
        if t in used:
            continue
        try:
            html = parsoid.fetch(meta["domain"], t)
        except wmhttp.HttpError as e:
            print(f"  {wiki} sample: {t}: {e}")
            continue
        sh = shape(html)
        new = {f for f in FEATURES if sh[f] > 0} - present
        # first pages adding a feature; after 30 candidates any text of some length
        if not new and (titles.index(t) < 30 or sh["text"] < 1500):
            continue
        print(f"  {wiki} sample: {t} -> {slugify(t)} {sh}")
        save_page(corpus, wiki, slugify(t), meta["domain"], t, "sample")
        present |= new
        used.add(t)
        count += 1
    if count < MIN_PER_WIKI:
        print(f"  {wiki}: only {count} pages")


def member(db: sqlite3.Connection, spec: str) -> str | None:
    """@member:Root:n (n-th part), @member:Root:prev-of:Title, @alias:Title."""
    if spec.startswith("@alias:"):
        r = db.execute("SELECT p.page_title FROM aliases a JOIN parts p ON p.work_id = a.work_id AND p.ord = a.ord "
                       "WHERE a.alias_title = ?", (spec[7:],)).fetchone()
        return r[0] if r else spec[7:]
    _, root, *rest = spec.split(":")
    wid = db.execute("SELECT id FROM works WHERE page_title = ?", (root,)).fetchone()[0]
    if rest[0] == "prev-of":
        ordv = db.execute("SELECT ord FROM parts WHERE work_id = ? AND page_title = ?", (wid, rest[1])).fetchone()[0]
        return db.execute("SELECT page_title FROM parts WHERE work_id = ? AND ord = ?", (wid, ordv - 1)).fetchone()[0]
    return db.execute("SELECT page_title FROM parts WHERE work_id = ? AND ord = ?", (wid, int(rest[0]))).fetchone()[0]


def build_structure(corpus: dict) -> None:
    for wiki, slug, title, case in STRUCTURE:
        db = sqlite3.connect(f"file:{OUT / (wiki + '.sqlite')}?mode=ro", uri=True)
        domain = dict(db.execute("SELECT key, value FROM meta"))["domain"]
        if title.startswith("@"):
            title = member(db, title)
        db.close()
        slug = slug or slugify(title)
        print(f"  structure {case}: {wiki}/{slug} <- {title}")
        save_page(corpus, wiki, slug, domain, title, f"structure-{case}")


def main(argv: list[str]) -> None:
    corpus = load_corpus()
    # the WP0 fixtures, as they were chosen
    for d in sorted(FIXTURES.glob("*/*/source.json")):
        src = json.loads(d.read_text("utf-8"))
        corpus["pages"].setdefault(f"{src['wiki']}/{d.parent.name}", {"title": src["title"], "categories": ["wp0"]})
    if argv == ["--structure"]:
        build_structure(corpus)
        return
    for wiki in argv or WIKIS:
        print(wiki, flush=True)
        build_wiki(wiki, corpus)
        fill_wiki(wiki, corpus)
    if not argv:
        build_structure(corpus)


if __name__ == "__main__":
    main(sys.argv[1:])
