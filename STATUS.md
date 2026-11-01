# STATUS

Working state of the implementation of [PLAN.md](PLAN.md). Update this file
before stopping work for any reason (PLAN.md section 0).

Last updated: 2026-11-01

## Current package

**WP3 — Content fetching, normaliser, downloads: complete** (2026-11-01;
acceptance on the emulator, below). Next: **WP4 — Reader core**. WP0–WP2
are complete (WP2: 18 of 18, commit c9c731f; WP1: 19 of 19, commit 161472a).

WP3 sub-steps:

- [x] 1. `:core-content` (plain Kotlin, Jsoup 1.23.2): normaliser (PLAN.md
      7.2), synthetic parts (7.1 step 6), locator text (7.4), page structure
      from Parsoid `data-mw` and live resolution (7.1), the Parsoid client
      (`with_html`, siteinfo, images); per-wiki settings exported from
      `pipeline/config` (`python -I -m wscatalog.appconfig`, kept in step by
      a pytest)
- [x] 2. Fixture corpus: 111 pages over the 10 wikis plus the structure
      fixtures of WS§7.4 (`pipeline/wp3/corpus.py`, `corpus.json`);
      `FixtureTest` (invariants on every page, golden `expected.html` and
      `expected.json`) and `StructureTest` (live resolution: Diktsamling,
      Sagenschatz, Madame Bovary, Shelley, Käthe, Heimath): 12 tests pass
- [x] 3. App: Room `library.db` (PLAN.md 6.2), work storage, the download
      worker (WorkManager, foreground with a progress notification, resumes
      part by part after a kill or a lost connection), images through the
      app's HTTP client, whole work or chosen parts (R4) from the detail
      screen, the Library screen (size per work, total, delete, retry),
      "Download this page" for links the catalog lacks (live resolution),
      per-page sources and licence (R11), and a plain part viewer
      (WebView, `WebViewAssetLoader`) until the WP4 reader
- [x] 4. Acceptance on the emulator (below)

**WP3 acceptance** (AVD `bibliotheke_wp0`, en catalog installed, over a
train connection with drop-outs): all fixture tests pass (12 of 12 in
`:core-content`, 111 pages). *Pride and Prejudice (1817)* (en, validated,
the root and 60 chapters, 62 parts with the title page) downloaded in the
background from the detail screen; at part 33 the app process was killed
(`kill -9`): Android restarted it and the download went on from part 33;
at part 56 the app was force-stopped: the download waited until the app
was opened and then finished (all 62 parts and their `.json`, 797 kB, raw
page cache cleared). With airplane mode on, `app/acceptance/wp3.sh 62`
stepped through the part viewer with Next: **62 of 62 parts opened
offline**, each with its own title and text on the page (checked from
screenshots: uiautomator sees a WebView's text only now and then, so a
first version of the script that read it reported false blanks). The work
has 61 chapters, one more than the criterion asks. Also checked by hand: the
Library screen (sizes, total), "Download this page" for a link to a wiki
without an installed catalog (is `Tófukvæði`, live resolution, 5.1 kB), and
nb strings on the new screens.

## WP3 notes (implementation choices, for review)

- **`:core-content`** is a plain Kotlin module like `:core-catalog`, so the
  normaliser and live resolution run in host tests against the fixtures.
- **Normaliser**: see PLAN.md 7.2 "(WP3, 2026-11-01)". Header boxes go by
  template name too (fi, is, it headers are not `.ws-noexport`), but only a
  Parsoid transclusion group made of nothing else (it `{{Qualità}}` shares a
  group with the text); it's notice goes by selector. Page markers inside a
  removed TOC stay. Locator text comes from the fragment as parsed back.
  Android's regex engine (ICU) rejects Java's `(?U)`; `unicodeRegex` falls
  back to the plain pattern there (ICU is Unicode-aware by default).
- **Live resolution** (PLAN.md 7.1 "(WP3 …)") is simpler than the pipeline:
  no Index check for rule 3, root pages only from TOC pages or explicit
  TOCs, no virtual roots, the own-text measure on the normalised HTML (fr
  `Madame Bovary` front, the pipeline says toc). Used for works the catalog
  holds without parts, and for links the catalog lacks.
- **Synthetic parts**: `Part.index` stays the page's place in the tree,
  `sub` numbers the slices; `app://part/{index}` names the first slice and
  the reader (WP4) finds the anchor among the slices. Titles: the heading,
  or "Pages 3–71" from the page labels (localised in the app).
- **Download**: one WorkManager job per work, `APPEND_OR_REPLACE`, so
  choosing more parts while one runs queues them behind it; the part tree is
  stored before any content (7.1 step 5); every page fetched is kept in
  `raw/` until the job ends, so live resolution and a retry fetch each page
  once; each finished part is written (atomically) and recorded at once.
  Images over 5 MB, icons under 32 px and hosts other than
  `*.wikimedia.org` are skipped; an image that fails leaves the text
  readable. The foreground notification needs POST_NOTIFICATIONS on Android
  13+ (asked on the first download; the download runs either way).
- **Licence per work**: from `with_html` (`license.title`/`url`), shown with
  the list of every wiki page of the copy and its history (R11).
- **Part viewer**: a scrolling WebView (JavaScript off) with Previous/Next
  and the list of parts; `app://part/` links move within the work, other
  links ask before opening the browser (7.2 rule 3). The paginated reader
  is WP4.
- **Corpus gaps** (the category queries need genres, and it, sv, da, fi,
  es, mul were built without Wikidata; filled with sampled pages): fi has
  no novel chapter, poem, collection text or play; is no collection text,
  play, footnotes, illustrations or tables; da no collection text or play;
  it no scan-backed page; sv no play; fr no tables; no no illustrations.
  `it/gerusalemme-liberata-dedica` is a page without text (a marker only).

WP2 sub-steps:

- [x] 1. Gradle project at the repo root (AGP 9.1.1, Kotlin 2.4.10, Gradle
      9.6.1; `gradle/libs.versions.toml`) and the catalog query layer in
      the plain Kotlin module `:core-catalog` (see "WP2 notes" below)
- [x] 2. Network: `PoliteHttp` (User-Agent, one request in flight per host,
      backoff on 429/5xx/I/O honouring `Retry-After`), online search
      client, resumable catalog download verified by sha256, catalog store
- [x] 3. App module: Hilt, settings (DataStore), catalog download worker
      (WorkManager, progress, daily update check), wiki picker (first run
      and "Manage catalogs")
- [x] 4. Browse with facets (genre, period with year range, author, type,
      language; counts per facet) and the four quality chips; sort by
      title or year
- [x] 5. Search: offline works and texts across the installed catalogs,
      the "n more hidden by your quality filter" row (expands to show them,
      dimmed and labelled), online search per wiki with hits resolved into
      the catalog; open by URL (VIEW intent for `*.wikisource.org/wiki/`,
      shared text, or a link pasted into search)
- [x] 6. Work detail: authors (tap = browse by author), year, language,
      type, genres, cover, quality and completeness labels of 5.4, "4 of
      120 texts transcribed", size estimate, parts tree with each part's
      own tier when it differs, the searched text highlighted, source,
      licence note, contributors notice, page history, Wikidata
- [x] 7. Settings (app language, the chips as switches, catalogs, update
      check), About (long name, credits, non-affiliation notice, GPL,
      source link); strings in en, nb, nn and `values-no` (copy of nb)
- [x] 8. Acceptance on the emulator (below)

**WP2 acceptance** (`app/acceptance/wp2.sh` on AVD `bibliotheke_wp0`,
airplane mode on, en and no catalogs downloaded first): for nn and for nb,
browse no offline (618 works = the manifest's `default_view_count`), genre
+ period + language (5 works) + author (1 work) combine, work detail,
search with the hidden-matches row, settings, about, picker from the
cached manifest, open by URL: 18 of 18 ok. Also checked by hand: the
Documents chip (no 618 → 685, the 67 documents of WP1 decision 4), the type
facet, online search on no.wikisource.org, the first-run download of en and
no from the local catalog server. Screens were read from `uiautomator`
dumps; no English string left on any screen in nb or nn (the AndroidX
library strings for nn are overridden in `values-nn/library_strings.xml`,
since the libraries ship nb but not nn and Android falls back to English).

WP1 followed `docs/work-structure-plan.md` (decided by the owner on
2026-10-31):

- [x] 1. Planning files committed as written
- [x] 2. Section 9 edits applied to PLAN.md, marked "(WS, 2026-10-31 …)"
      and citing the plan as "WS§n"; D1–D9 unchanged
- [x] 3. WP1a: structure and quality survey over the pages-articles dumps of
      no, is, de, fr, en → `report/{wiki}-structure.md`, eyeball review in
      `report/wp1a-review.md`. **The owner's decisions of 2026-11-01** on
      the nine open items are in `report/wp1a-decisions.md` (with the
      measurements behind them); the rule changes they called for are
      implemented and the reports regenerated. PLAN.md
      carries the amended passages marked "(WP1a, 2026-11-01 …)";
      `docs/work-structure-plan.md` section 11 summarises the decisions.
- [x] 4. WP1 pipeline, schema version 2 (PLAN.md 5.1–5.6): built and
      committed for the 11 configured wikis plus a generic config for every
      other open Wikisource; catalogs built locally from the 2026-10-01
      dumps (results below).
- [x] 5. **The owner's decisions of 2026-11-01 on the six items the first
      build left open** are in `report/wp1-decisions.md` (with the
      measurements; scripts in `pipeline/wp1/`): the author target counted
      over the default view, `parts_fts.rowid = parts.id`,
      `show_unknown_by_default` for the six unsurveyed wikis and a derived
      flag for the generic config, `document` hidden from the default
      view, the es dictionary entries typed `reference`, the sv
      textless-TOC-root rule, the de re-sample accepted. Implemented,
      catalogs rebuilt, PLAN.md amended "(WP1, 2026-11-01 …)",
      `docs/work-structure-plan.md` section 12. **Acceptance: 19 of 19
      checks pass.**

## WP2 notes (implementation choices, for review)

- **SQLite with FTS5**: `androidx.sqlite:sqlite-bundled` 2.6.2 (AndroidX,
  Apache-2.0) bundles its own SQLite with FTS5 on every Android version,
  and the same build runs on the host JVM. This answers the WP1 note on
  FTS5 on API 26 without requery. Catalogs are opened read-only through
  its driver and queried in place (PLAN.md 6.1); no Room for catalogs.
- **`:core-catalog`** is a plain Kotlin/JVM module (D5 says single module
  to start): it lets every catalog query run in host unit tests against a
  fixture catalog with the pipeline's schema
  (`core-catalog/src/test/resources/schema-v2.sql`) and against the real
  catalogs in `pipeline/out` when present. `RealCatalogTest` checks that
  the app's default view counts exactly the manifest's
  `default_view_count` on all 11 wikis (it does).
- **Filter semantics** (PLAN.md 5.4): the "Include unverified texts" chip
  starts as the wiki's `show_unknown_by_default`, so on no, en, sv … it
  starts on and can be turned off. With no type selected the type rule
  hides `reference` and `document` (documents behind their chip); selecting
  a type in the type facet shows exactly that type, including `reference`
  and `document`. Type facet counts ignore the type rule so they show what
  selecting a type gives. The author facet counts every role (translators
  too).
- **Search**: every word is a prefix match (`"word"*`), over works_fts and
  parts_fts (parts joined on `id`); a text is shown by its own tier and
  completeness (a proofread poem in an unfinished collection keeps its
  standing, PLAN.md 5.4) and its work's type. Matches the chips hide,
  reference works included, are counted for the "n more hidden" row and
  returned, marked, when the user expands it, so nothing disappears
  silently.
- Host timings on en (desktop): count 22 ms, browse at offset 20,000 73 ms,
  facet counts 26–43 ms, search "the" 189 ms, "t" 253 ms.
- **Catalog host for debug builds**: until the CI release `catalog-latest`
  exists, debug builds read `http://10.0.2.2:8765/` (the emulator's host):
  run `python3 -m http.server 8765 --bind 127.0.0.1` in `pipeline/out`.
  Release builds use the GitHub release URL; `-PcatalogBaseUrl=…`
  overrides either (D6).
- **compileSdk 37** (platform 37.0 installed with sdkmanager; the Compose
  BOM 2026.08.00, navigation 2.10 and Coil 3.6 need it), targetSdk 36.
  Hilt 2.60.1 (2.59 cannot read Kotlin 2.4 metadata). OkHttp without
  Retrofit (D5 allows either; one endpoint so far).
- **Covers** load through Coil with the app's OkHttp client, User-Agent and
  one request per host.
- **Licence per work** is not in the catalog: the detail screen says the
  text is public domain or freely licensed and that the source page names
  the licence, plus the CC BY-SA contributors notice (R11). WP3 can take
  the licence template from the fetched page, or the pipeline can add a
  column.
- Tests: `./gradlew :core-catalog:test` (with
  `JAVA_HOME=/opt/android-studio/jbr`; no JDK 17 is installed, so the
  module targets JVM 17 bytecode from JBR 25 instead of using a toolchain).

## WP1 results (dumps of 2026-10-01, rebuilt 2026-11-01 after the decisions)

`make catalog WIKI=no` (or `python -I -m wscatalog.build no`) writes
`pipeline/out/{wiki}.sqlite.gz`, `report/{wiki}.md` (coverage report) and
`pipeline/out/manifest.json`. All 11 catalogs build in 8 minutes from the
cached dumps; en, fr and de take most of it. "Default view" = validated or
proofread (plus unknown where the flag is true), complete, not `reference`
or `document` (PLAN.md 5.4); it is the wiki picker's number.

| wiki | works | gzipped | validated | proofread | unproofread | unknown | default view | documents hidden | `show_unknown_by_default` |
|---|---|---|---|---|---|---|---|---|---|
| no | 1,069 | 0.9 MB | 73 | 466 | 307 | 223 | 618 | 67 | true |
| is | 250 | 0.1 MB | 1 | 6 | 0 | 243 | 246 | 0 | true |
| de | 17,388 | 7.9 MB | 9,833 | 6,999 | 266 | 290 | 10,140 | 4,571 | false |
| fr | 40,242 | 27.8 MB | 11,257 | 21,282 | 7,583 | 120 | 30,924 | 263 | false |
| en | 55,987 | 35.1 MB | 6,997 | 12,615 | 2,817 | 33,558 | 22,907 | 28,009 | true |
| it | 17,604 | 8.3 MB | 7,763 | 6,946 | 2,895 | 0 | 14,038 | 544 | false (moot) |
| sv | 6,580 | 2.2 MB | 536 | 1,793 | 93 | 4,158 | 6,420 | 3 | true |
| da | 1,168 | 0.4 MB | 22 | 104 | 203 | 839 | 875 | 73 | true |
| fi | 13,282 | 1.7 MB | 0 | 36 | 12 | 13,234 | 13,130 | 130 | true |
| es | 17,786 | 5.2 MB | 564 | 1,878 | 405 | 14,939 | 12,591 | 2,616 | true |
| mul | 28,582 | 5.6 MB | 128 | 680 | 316 | 27,458 | 28,138 | 2 | true |

Against the first build of the same day: sv lost 181 works to the
textless-TOC-root rule (15 collections, 204 texts; `report/sv-textless-toc-sample.md`),
es lost the six `Proyecto:` pages and hides 1,916 dictionary entries typed
`reference`; every wiki hides its documents from the default view; en is
1.1 MB smaller from the sequential `parts_fts` rowid.

Metadata coverage (share of works; sources in each `report/{wiki}.md`):

| wiki | author | year | genre | Wikidata item |
|---|---|---|---|---|
| no | 81.0 % | 85.4 % | 60.2 % | 35.7 % |
| de | 66.5 % | 97.3 % | 87.5 % | 46.6 % |
| fr | 89.4 % | 98.0 % | 60.7 % | 66.6 % |
| en | 83.5 % | 68.6 % | 68.0 % | 67.3 % |
| it | 87.2 % | 91.6 % | 66.3 % | (not fetched) |

it, sv, da, fi, es and mul were built without Wikidata (owner's decision,
below), so their author, year and genre figures are lower bounds.

**Acceptance (PLAN.md WP1, `python -I -m wscatalog.acceptance no de en`): 19
of 19 pass.** no: author 95.3 % of the 425 works in the default view ✓ (81.0 %
of all works; target counted over the default view since
report/wp1-decisions.md item 1), year 85.4 % ✓, "Ibsen" finds all 60 works
by Ibsen ✓, `excluded` explains every exclusion ✓, `Diktsamling` 4 parts /
116 missing / incomplete ✓, `Ved Rundarne (1864)` found as a text of
`Diktsamling` ✓, default view 425 works and 2,264 texts, none raw OCR ✓
(figures amended, item 4). de: Sagenschatz 1,001 parts ✓, Heimath virtual
with three volumes ✓, Käthe oversized ✓, the KHM overview not a work ✓, KHM
Band 1 (1857) a work ✓, Topographia Hassiae 207 texts ✓, `Allgemeines
Deutsches Kommersbuch:47` a part ✓, exclusions explained ✓. en: Ozymandias
finds the Hutchinson text ✓, Popular Science Monthly a collection ✓,
exclusions explained ✓, parts table 17.3 MB gzipped with both indexes and
parts_fts 3.2 MB (budget about 18 + 3; item 2) ✓, 20.5 MB together.

**Decisions taken by the owner on 2026-11-01 after the first build**
(details, evidence and the changes in `report/wp1-decisions.md`):

1. The no author target counts the works of the default view (95 %); the
   works without an author are anonymous by nature.
2. `parts.id INTEGER PRIMARY KEY` is the `parts_fts` rowid; author names
   stay in `parts_fts` (the sparse rowid cost 1.2 MB on en, the names 0.2).
3. `show_unknown_by_default`: sv, da, fi, es, mul true, it false; a wiki on
   the generic config derives the flag (true when unknown works outnumber
   the validated and proofread ones); the manifest says which.
4. The app's default type filter hides `document` as it hides `reference`,
   with an "Include documents" chip (WP2); the picker count excludes them.
5. es dictionary entries typed `reference`; `Proyecto:` pages excluded; sv
   root-page collections grouped by the textless-TOC-root rule
   (`structure.textless_toc_roots`, sv only, 15 of 15 in the sample); the
   de Brass diary and the es dictionary grouping deferred (PLAN.md section
   13); the Codex Manesse songs stay separate poems on purpose.
6. The de rule-3 re-sample (25 of 30, grouping right in all 30) accepted;
   the two ordering fixes ticketed.

## WP1a results (dumps of 2026-10-01; regenerated 2026-11-01 after the decisions)

| wiki | works | in catalog | validated | proofread | unproofread | unknown | incomplete | default view: works (texts) | with unknown shown | `show_unknown_by_default` |
|---|---|---|---|---|---|---|---|---|---|---|
| no | 1,118 | 1,069 | 73 | 466 | 307 | 223 | 116 | 470 (2,368) | 685 | true |
| is | 256 | 250 | 1 | 6 | 0 | 243 | 4 | 5 (60) | 246 | true |
| de | 19,660 | 17,391 | 9,836 | 6,999 | 266 | 290 | 2,169 | 14,736 (39,818) | 14,994 | false |
| fr | 40,889 | 40,245 | 11,258 | 21,283 | 7,584 | 120 | 2,355 | 31,192 (120,743) | 31,303 | false |
| en | 57,148 | 55,994 | 6,997 | 12,616 | 2,817 | 33,564 | 3,134 | 18,044 (113,220) | 50,932 | true |

"Default view" = validated or proofread, complete, not `reference` (PLAN.md
5.4). Changes against the 2026-10-31 run: de lost 6,977 single-page
"works" (Merian *Topographia* town entries and page-by-page books now
grouped under their root pages, decision 4) and 2,029 `{{Zeitschrift}}`
bibliography pages (hard-excluded, decision 1); fr and en gained works in
the default view from the coverage rule (decision 7: fr 700 → 305 works
incomplete by coverage alone) and the Index-progress rule (decision 6: en
excludes 14 works as index-faulty instead of 141, fr 59 instead of 109);
en has 30 reference works instead of 35 (decision 3: *Popular Science
Monthly*, the Nicene Fathers series and *Who's Who in the Far East* are
collections or books; is *Íslenzkar þjóðsögur og æfintýri* is a
collection).

Parts table (WS§5.2) at `parts_cap` 2,000 with the decided encoding
(decision 2: `index_ref` integer, `display_title` NULL when it equals the
last title segment): see section 10 of each report; en about 18 MB gzipped
plus 3 MB of `parts_fts`, 67 MB raw SQLite.

Eyeball acceptance (≥ 27 of 30 correct for A, B, D, E): met everywhere
except en B (26/30); see `report/wp1a-review.md`. The review was done on the
2026-10-31 run; the works that the decisions changed (the grouped de
colon-prefix books, the retyped en periodicals) were spot-checked, not
re-sampled.

**Decisions taken by the owner on 2026-11-01** (details and evidence in
`report/wp1a-decisions.md`):

1. `show_unknown_by_default`: is true, en true, de false, fr false; de
   `{{Zeitschrift}}` bibliography pages excluded (`bibliography_templates`).
2. `parts_cap` 2,000 confirmed; measured size accepted; `parts.index_ref`
   integer and nullable `display_title` (PLAN.md 5.3); suffix encoding
   deferred.
3. Over `reference_threshold` without an Index type is `reference` only
   without a chain or TOC order.
4. de: rule-3 extension `headerless_toc_members` (PLAN.md 5.2 step 1b);
   `Hantzsch:` excluded by prefix; a colon prefix shared by three siblings
   is stripped from display titles.
5. PLAN.md WP1 acceptance figures amended to the measured values.
6. Index progress `MS`/`OCR`/`L` excludes only works without a proofread
   page.
7. Coverage: pages that other works transclude do not count;
   `coverage_slack` 3; `coverage_min` 0.95.
8. No page-title chapter rule (decision 8.9 stands).
9. The reports' section 13 implementation choices accepted.

## WP0 checklist

- [x] 0. Repository initialised: git, `LICENSE` (GPL-3.0-or-later, D8),
      `.gitignore`, this file
- [x] 1. Polite HTTP helper for spike scripts (User-Agent, one request at a
      time per host, backoff on 429/5xx)
- [x] 2. Siteinfo namespace numbers for the 11 configured wikis
- [x] 3. Parsoid REST endpoint and HTML shape (`.ws-pagenum`, `.ws-summary`,
      `<poem>`, references) on no/en/fr/de; 3 raw fixtures per wiki
- [x] 4. Wikidata coverage for 50 Norwegian works (P577 / P136 / P50)
- [x] 5. Pagination prototype in a browser harness: CSS columns, locator
      round-trip after font-size change
- [x] 6. Pagination prototype in an Android WebView on the emulator: swipe
      overlay, frame timing (60 fps target)
- [x] 7. `docs/wp0-findings.md` written; **VERIFY** items in PLAN.md updated

WP0 acceptance: met (see the summary at the top of `docs/wp0-findings.md`).

## Done

- `docs/wp0-findings.md`: all results, with raw evidence in `docs/wp0/`.
- `pipeline/wp0/`: spike scripts (see its README) and the polite HTTP
  helper; responses cached in `pipeline/cache/wp0/` (gitignored). Added
  2026-10-31: structure and quality spikes (`structure_*.py`,
  `search_counts.py`, `quality_*.py`) behind `docs/work-structure-plan.md`.
- `pipeline/wp1a/`: the measurements behind the decisions of 2026-11-01
  (README there); `report/wp1a-decisions.md` holds the decisions and the
  measurements' output.
- `app/src/test/fixtures/{no,en,fr,de}/`: 17 raw Parsoid fixtures with
  `source.json` provenance (no 5, en 4, fr 5, de 3).
- `prototypes/pagination/`: reader CSS/JS, locator tests, browser runner,
  Android WebView prototype (`android/`) with a device test script.
- PLAN.md: VERIFY items resolved; WP0 corrections marked "(WP0 …)"; WS
  edits marked "(WS, 2026-10-31 …)"; WP1a decisions marked "(WP1a,
  2026-11-01 …)".
- User-Agent (repo URL given by the owner after WP0):
  `Bibliotheke/0.1.0-dev (https://github.com/polemistes/bibliotheke; develop@robertemilberge.no) <client>/<version>`;
  the URL is `REPO_URL` in `pipeline/wscatalog/http.py` (and in the WP0
  spike helper `pipeline/wp0/wmhttp.py`).

## In progress

- Nothing running. WP3 is complete; next: WP4 (reader core).
- **Owner's decision (2026-11-01): Wikidata for it, sv, da, fi, es and mul
  is fetched by the first CI run**, not locally (it would take about an
  hour; the bottleneck is Wikidata's server time and maxlag, not the
  connection). Their local catalogs are built with `--offline` from the
  header and Index data only.

## How to resume / rerun

- Content (WP3): `./gradlew :core-content:test` (fixtures in
  `app/src/test/fixtures`; `-PupdateGolden=true` rewrites the golden files
  after a reviewed change). Corpus: `python3 -I pipeline/wp3/corpus.py`
  (needs requests, beautifulsoup4, lxml; responses cached in
  `pipeline/cache/wp0`). App settings export:
  `.venv/bin/python -I -m wscatalog.appconfig`. Acceptance: download a
  work in the app, open it in the viewer at its first part, airplane mode
  on, `PATH=$PATH:$HOME/Android/Sdk/platform-tools app/acceptance/wp3.sh
  <parts> Neste`.
- App (WP2): `JAVA_HOME=/opt/android-studio/jbr ./gradlew :core-catalog:test
  :app:assembleDebug :app:lintDebug` from the repo root (`local.properties`
  with `sdk.dir` is gitignored). Catalog server for debug builds:
  `cd pipeline/out && python3 -m http.server 8765 --bind 127.0.0.1`.
  Acceptance: start the emulator (below), install
  `app/build/outputs/apk/debug/app-debug.apk`, download en and no in the
  app, then `PATH=$PATH:$HOME/Android/Sdk/platform-tools
  app/acceptance/wp2.sh` (screenshots in `$OUT`, default
  `/tmp/wp2-acceptance`).

- Pipeline (see `pipeline/README.md`): `make venv`, then
  `make catalog WIKI=no` (dumps → extract → catalog → manifest),
  `make catalogs` (the 11 configured wikis), `make acceptance WIKI="no de en"`,
  `make test`. From the cache only: `.venv/bin/python -I -m wscatalog.build
  --configured --no-download --offline` (8 minutes). Dumps of the 2026-10-01
  run for the 11 wikis (about 7.5 GB) are in `pipeline/cache/dumps/`,
  extracts in `pipeline/cache/extract/`, Wikidata items (no, is, de, fr, en)
  in `pipeline/cache/wikidata.sqlite`, catalogs in `pipeline/out/` (all
  gitignored). Survey report: `.venv/bin/python -I -m wscatalog.survey no`.
  WP1 decision measurements: `pipeline/wp1/` (README there); the sv sample
  regenerates with `.venv/bin/python -I pipeline/wp1/textless_toc_sample.py sv`.
  Resolution, quality and metadata rules can be rerun on the cached
  extract; `build` re-extracts when the dump or `EXTRACT_VERSION` changes.
- CI: `.github/workflows/catalog.yml` (weekly; publishes to the release
  `catalog-latest`); needs the GitHub remote `polemistes/bibliotheke`.
- Browser tests: `prototypes/pagination/run_browser_test.sh` (needs
  `chromium`).
- Android build: `cd prototypes/pagination/android &&
  JAVA_HOME=/opt/android-studio/jbr ./gradlew assembleRelease` (Gradle 9.6.1,
  AGP 9.1.1; `local.properties` with `sdk.dir` is gitignored).
- Emulator: AVD `bibliotheke_wp0` (Pixel 7, Android 16), started headless
  with `emulator -avd bibliotheke_wp0 -port 5584 -no-window -no-audio -gpu host`,
  then `SERIAL=emulator-5584 prototypes/pagination/android/run_device_test.sh`.

## Known problems and open questions

- **WP3 not done, by plan or for later**: the live TOC with a filter box for
  reference works (WS§7.1; downloading one resolves every entry, so it
  needs the entry picker first); refresh with `If-None-Match` and merging
  re-resolved parts (7.1 step 3); de's `{{SeitePR}}` page markers carry no
  scan page title when the link is missing; generated line numbers count a
  bold title line inside a poem (Erlkönig: two lines numbered 5 after the
  re-sync, F§4.4); it's source line numbers (*Adda*: "5Cheti recessi", the
  number run into the line) are not in its `line_number_selectors` yet (WP5).

- **For the owner (WP2)**: speeches stay behind the "Include documents"
  chip with the other documents (wp1-decisions item 4 left "document and
  genre not speech" to WP2; not done, so the default view still matches
  the pipeline's count exactly). Say if speeches should be shown by
  default.
- **WP2 not done, by plan or for later**: UI strings in the other nine
  languages (WP6); Compose previews, Compose UI tests and ktlint/detekt
  (section 12; the acceptance runs through adb instead); the seed `no`
  catalog in the APK (section 13 recommendation); a one-letter search on
  en takes about 0.25 s on the host, so more on a phone (the search
  debounces 250 ms).

- **STATUS.md location**: PLAN.md section 0 says repo root; sections 6 and 12
  say `docs/STATUS.md`. Kept at the repo root, as section 0 (the working
  instructions) specifies.
- **Frame timing was measured on the emulator** with a desktop-class host
  GPU; WP4 should repeat it on a real mid-range phone (N2).
- **Decided 2026-10-31** (supersedes findings section 6, WP1 item 6):
  collections that link separate root pages (de, no) are grouped under
  their TOC page; members become searchable texts. Quality: unproofread
  and incomplete works hidden by default, unknown shown on no after junk
  rules. Everything in `docs/work-structure-plan.md` sections 8 and 10.8;
  evidence in `docs/structure/`; spike scripts in `pipeline/wp0/` (README).
- **Tickets from the eyeball review: fixed in WP1** (duplicate parts,
  ordinal words in natural order, navigation link texts; PLAN.md 5.2
  step 6 note).
- **FTS5 on Android**: solved in WP2 with `androidx.sqlite:sqlite-bundled`
  (see "WP2 notes").
- **Ordering defects left** (de re-sample): TOC order takes each link's
  first occurrence, so example pages linked in a root's editorial notes
  come first (Kommersbuch, Hertz); periodical articles without a chain or
  TOC are in natural order (*Annalen der Physik*). Ticketed with the WP1
  decisions (item 6, PLAN.md section 13), together with the numbered town
  entries read as chapters (*Topographia Helvetiae*).
- **de residue after decision 4** (unchanged in WP1): 290 unknown works remain, mostly colon
  pages of small page-by-page books whose root is listed by two TOC pages
  (`Ausführliche Vorschriften zur Blitz-Ableitung …:Seite N`) and the
  `Ersch-Gruber:` lexicon entries (30 pages, below the 100-page prefix
  survey threshold).
- **Documents hidden by default** (decided 2026-11-01, wp1-decisions item
  4): en 28,009, de 4,571, es 2,616, it 544, fi 130, da 73, no 67. Speeches
  go with them (the taxonomy types genre speech as `document`, and en's
  presidential addresses carry PD-USGov anyway); WP2 may keep them visible
  with "document and genre not speech" (2,989 en works). en's unknown
  works outside documents: 12,096.
- **Deferred groupings** (wp1-decisions item 5, PLAN.md section 13): the
  de *Tagebuch von Hans Brass* (3,913 diary pages, 1,932 of them in the
  default view, no root page; needs category-defined virtual collections
  and category listing in the app); the es dictionary entries (typed
  `reference` for now) and *Almagesto* chapters (rule-3 extension for es,
  where a TOC-like page may itself be a part with a header or a headerless
  root listing colon-prefixed pages); seven fi virtual roots with a single
  leaf child from titles with slashed dates. On sv, members without a
  prev/next chain or a shared Index stay separate works (*Dikter
  (Löwenhjelm)* 9 of 86, *Där ute. Skisser.* 1 of 12). The Codex Manesse
  songs stay separate poems by decision.
- The prototype uses plain Views, not Compose (Compose was not in the Gradle
  cache); the production reader uses Compose `AndroidView` + `pointerInput`
  as planned.
