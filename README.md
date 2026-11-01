# Bibliotheke for Wikisource books

An Android app for reading the public-domain and freely licensed texts hosted
on the [Wikisource](https://wikisource.org) projects. It offers a catalog of
readable works from every Wikisource language edition, downloads works for
offline reading, and shows them in a paginated, book-like reader.

The app is under development and has not been released yet. The current state
of the work is in [STATUS.md](STATUS.md); the plan it follows is
[PLAN.md](PLAN.md).

## What it does

- Browses a catalog of complete works per Wikisource, offline, by language,
  genre, century, author, work type and text quality, with full-text search
  over titles and authors.
- Downloads a whole work or chosen chapters in the background, with the images
  and footnotes, so reading needs no connection.
- Reads in pages turned by swiping, with bookmarks, a chapter list, a progress
  slider and search within the work; the reading position survives font
  changes, rotation and app restarts.
- Offers colour schemes, bundled fonts, text size, line height, margins,
  justification and hyphenation.
- Can show the original edition's page numbers and line numbers as Wikisource
  shows them, and footnotes as popups.
- Credits the source page, its licence and the Wikisource contributors for
  every text.

The user interface is planned in English, Norwegian Bokmål and Nynorsk,
French, German, Italian, Swedish, Danish, Finnish, Icelandic and Spanish.

## How it is built

Two parts:

- **Catalog pipeline** (`pipeline/`, Python 3.12). A weekly GitHub Actions
  workflow reads the Wikisource dumps and Wikidata, resolves wiki pages into
  works and parts, grades text quality and writes one SQLite catalog per
  wiki plus a manifest. The catalogs are published as assets of the rolling
  release `catalog-latest`. See [pipeline/README.md](pipeline/README.md).
- **Android app** (`app/`, Kotlin, Jetpack Compose, min SDK 26). The app
  downloads the catalogs it needs and queries them in place. Texts are
  fetched as Parsoid HTML from the wiki, normalised and stored on the
  device; the reader is a WebView with CSS column pagination.
  `core-catalog/` holds the catalog queries and `core-content/` the
  normaliser, both as plain Kotlin modules with host tests over a fixture
  corpus.

The app talks only to Wikimedia servers and to the catalog host on GitHub.
It has no analytics, no accounts and no third-party trackers.

### Building

```sh
# catalog pipeline
make venv
make catalog WIKI=no
make test

# app (needs an Android SDK; set sdk.dir in local.properties)
./gradlew :core-catalog:test :core-content:test :app:assembleDebug
```

## Licence

Bibliotheke is free software under the
[GNU General Public License, version 3 or later](LICENSE). The bundled
reader fonts are under the SIL Open Font License; their licence files are
kept beside them in `app/src/main/assets/reader/fonts/`. The texts shown in
the app come from Wikisource and are public domain or freely licensed, as
stated on each source page; Wikisource contributions are under CC BY-SA.

Contact: develop@robertemilberge.no

Bibliotheke is an independent app and is not affiliated with or endorsed by
the Wikimedia Foundation. Wikisource is a trademark of the Wikimedia
Foundation.
