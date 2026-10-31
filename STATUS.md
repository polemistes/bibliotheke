# STATUS

Working state of the implementation of [PLAN.md](PLAN.md). Update this file
before stopping work for any reason (PLAN.md section 0).

Last updated: 2026-11-01

## Current package

**WP0 — Spike and verification: complete.** Current: **WP1 — Catalog
pipeline**. WP1 follows `docs/work-structure-plan.md` (decided by the owner
on 2026-10-31):

- [x] 1. Planning files committed as written
- [x] 2. Section 9 edits applied to PLAN.md, marked "(WS, 2026-10-31 …)"
      and citing the plan as "WS§n"; D1–D9 unchanged
- [x] 3. WP1a: structure and quality survey over the pages-articles dumps of
      no, is, de, fr, en → `report/{wiki}-structure.md`, eyeball review in
      `report/wp1a-review.md`. **The owner's decisions of 2026-11-01** on
      the nine open items are in `report/wp1a-decisions.md` (with the
      measurements behind them); the rule changes they called for are
      implemented and the reports regenerated (numbers below). PLAN.md
      carries the amended passages marked "(WP1a, 2026-11-01 …)";
      `docs/work-structure-plan.md` section 11 summarises the decisions.
- [x] 4. WP1 pipeline, schema version 2 (PLAN.md 5.1–5.6): built and
      committed for the 11 configured wikis plus a generic config for every
      other open Wikisource; catalogs built locally from the 2026-10-01
      dumps (results below). **Acceptance: 17 of 19 checks pass; the two
      that do not are decisions for the owner** (below). The CI workflow
      has not run yet: the repository has no remote.

## WP1 results (dumps of 2026-10-01, built 2026-11-01)

`make catalog WIKI=no` (or `python -I -m wscatalog.build no`) writes
`pipeline/out/{wiki}.sqlite.gz`, `report/{wiki}.md` (coverage report) and
`pipeline/out/manifest.json`. All 11 catalogs build in 8 minutes from the
cached dumps; en, fr and de take most of it.

| wiki | works | gzipped | validated | proofread | unproofread | unknown | default view | `show_unknown_by_default` |
|---|---|---|---|---|---|---|---|---|
| no | 1,069 | 1.0 MB | 73 | 466 | 307 | 223 | 685 | true |
| is | 250 | 0.1 MB | 1 | 6 | 0 | 243 | 246 | true |
| de | 17,388 | 8.0 MB | 9,833 | 6,999 | 266 | 290 | 14,732 | false |
| fr | 40,242 | 28.6 MB | 11,257 | 21,282 | 7,583 | 120 | 31,190 | false |
| en | 55,987 | 36.2 MB | 6,997 | 12,615 | 2,817 | 33,558 | 50,924 | true |
| it | 17,604 | 8.5 MB | 7,763 | 6,946 | 2,895 | 0 | 14,582 | (unset → false) |
| sv | 6,761 | 2.2 MB | 536 | 1,793 | 93 | 4,339 | 2,281 | (unset → false) |
| da | 1,168 | 0.4 MB | 22 | 104 | 203 | 839 | 112 | (unset → false) |
| fi | 13,282 | 1.7 MB | 0 | 36 | 12 | 13,234 | 34 | (unset → false) |
| es | 17,792 | 5.3 MB | 564 | 1,878 | 405 | 14,945 | 2,282 | (unset → false) |
| mul | 28,582 | 5.7 MB | 128 | 680 | 316 | 27,458 | 774 | (unset → false) |

Metadata coverage (share of works; sources in each `report/{wiki}.md`):

| wiki | author | year | genre | Wikidata item |
|---|---|---|---|---|
| no | 81.0 % | 85.4 % | 60.2 % | 35.7 % |
| de | 66.5 % | 97.3 % | 87.5 % | 46.6 % |
| fr | 89.4 % | 98.0 % | 60.7 % | 66.6 % |
| en | 83.5 % | 68.6 % | 68.0 % | 67.3 % |
| it | 87.2 % | 91.6 % | 66.3 % | (not fetched) |

it, sv, da, fi, es and mul were built without Wikidata (owner's decision,
below), so their author, year and genre figures are lower bounds. en types
29,118 works as `document` (court cases, acts, proclamations, executive
orders, resolutions), 54 % of its default view.

**Acceptance (PLAN.md WP1, `python -I -m wscatalog.acceptance no de en`):**
no: year 85.4 % ✓, "Ibsen" finds all 60 works by Ibsen ✓, `excluded`
explains every exclusion ✓, `Diktsamling` 4 parts / 116 missing /
incomplete ✓, `Ved Rundarne (1864)` found as a text of `Diktsamling` ✓,
default view 470 works and 2,365 texts, none raw OCR ✓; **author 81.0 %
(target 90 %) ✗**. de: Sagenschatz 1,001 parts ✓, Heimath virtual with
three volumes ✓, Käthe oversized ✓, the KHM overview not a work ✓, KHM Band 1
(1857) a work ✓, Topographia Hassiae 207 texts ✓, `Allgemeines Deutsches
Kommersbuch:47` a part ✓, exclusions explained ✓. en: Ozymandias finds the
Hutchinson text ✓, Popular Science Monthly a collection ✓, exclusions
explained ✓; **parts budget: parts table 15.4 MB ✓ (budget about 18), but
parts_fts 4.8 MB (budget about 3) ✗**, 20.1 MB together against 21.6 MB.

**Decisions for the owner** (WP1, 2026-11-01):

1. **no author target.** 81.0 % of all no works have an author, 94.5 % of
   the works in the default view. The rest are anonymous by nature: 62 folk
   legends (`Sagn`), Eddic poems, court decisions, party programmes,
   periodicals, typed-in songs. Count the target over the default view, or
   accept 81 %?
2. **en parts_fts size.** 1.6 MB over the measured 3 MB because the survey
   measured it with rowids 1..n and empty `author_names`; the catalog uses
   rowid = `work_id * 4096 + ord` (1.0 MB) and fills `author_names`
   (0.6 MB). Options: accept (the parts table is 3 MB under its budget);
   drop `author_names` from `parts_fts` (works_fts already has them); or use
   `parts.rowid` as the FTS rowid (compact, but the app must never VACUUM
   the catalog).
3. **`show_unknown_by_default` for it, sv, da, fi, es, mul** (not
   surveyed; unset means false). With unknown works hidden, fi shows 34 of
   13,282 works, mul 774 of 28,582, es 2,282 of 17,792, sv 2,281 of 6,761.
   The same question applies to the ~70 wikis on the generic config.
4. **en documents in the default view.** en shows unknown works by default,
   and 29,118 of its works are typed `document` (54 % of the default view).
   Should the app's default type filter hide `document` the way it hides
   `reference` (WP2)?
5. **Collections still stored one work per page** (not grouped by the
   decided rules; not in the acceptance cases): de *Tagebuch von Hans
   Brass* (3,913 diary entries `TBHB …`) and the Codex Manesse songs (2,818,
   container `Codex Manesse 25r` etc.); es *Diccionario de Arquitectura
   Civil* (1,577 entries) and other dictionaries; sv collections of root
   pages without an Index (`Fänrik Ståls sägner`). Leave for later, or add
   rules?
6. **de rule-3 re-sample: 25 of 30** (below the 27 that WP1a required for
   patterns A, B, D, E); the grouping is right in all 30, the five errors
   are order, tree position and classification (`report/wp1a-review.md`).

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

- Nothing running. WP1 is built; waiting for the owner on the six
  decisions above. Next after them: WP2 (app skeleton).
- **Owner's decision (2026-11-01): Wikidata for it, sv, da, fi, es and mul
  is fetched by the first CI run**, not locally (it would take about an
  hour; the bottleneck is Wikidata's server time and maxlag, not the
  connection). Their local catalogs are built with `--offline` from the
  header and Index data only.

## How to resume / rerun

- Pipeline (see `pipeline/README.md`): `make venv`, then
  `make catalog WIKI=no` (dumps → extract → catalog → manifest),
  `make catalogs` (the 11 configured wikis), `make acceptance WIKI="no de en"`,
  `make test`. From the cache only: `.venv/bin/python -I -m wscatalog.build
  --configured --no-download --offline` (8 minutes). Dumps of the 2026-10-01
  run for the 11 wikis (about 7.5 GB) are in `pipeline/cache/dumps/`,
  extracts in `pipeline/cache/extract/`, Wikidata items (no, is, de, fr, en)
  in `pipeline/cache/wikidata.sqlite`, catalogs in `pipeline/out/` (all
  gitignored). Survey report: `.venv/bin/python -I -m wscatalog.survey no`.
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
- **FTS5 on Android** (for WP2): the schema uses FTS5 (5.3) and the
  tokenizer `unicode61 remove_diacritics 2`; Room supports FTS3/FTS4 only
  and the platform SQLite's FTS5 support differs by Android version, so WP2
  should bundle an SQLite build with FTS5 (e.g. requery/sqlite-android,
  Apache-2.0) or confirm it on API 26.
- **Ordering defects left** (de re-sample): TOC order takes each link's
  first occurrence, so example pages linked in a root's editorial notes
  come first (Kommersbuch, Hertz); periodical articles without a chain or
  TOC are in natural order (*Annalen der Physik*).
- **de residue after decision 4** (unchanged in WP1): 290 unknown works remain, mostly colon
  pages of small page-by-page books whose root is listed by two TOC pages
  (`Ausführliche Vorschriften zur Blitz-Ableitung …:Seite N`) and the
  `Ersch-Gruber:` lexicon entries (30 pages, below the 100-page prefix
  survey threshold).
- **en unknown works** (33,558, shown by default): typed in WP1; 21,132 of
  them are `document` (licence and case templates, law and court
  categories). See decision 4 above.
- The prototype uses plain Views, not Compose (Compose was not in the Gradle
  cache); the production reader uses Compose `AndroidView` + `pointerInput`
  as planned.
