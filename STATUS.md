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
- [ ] 4. WP1 pipeline, schema version 2, acceptance cases of PLAN.md WP1.
      In progress (see "In progress" below): catalog writer, metadata merge,
      Wikidata cache, coverage report, manifest, build orchestrator,
      Makefile, CI workflow, tests and acceptance checks are built and
      committed; configs exist for all 11 configured wikis.

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

- **Task 4, the WP1 pipeline** (2026-11-01). Done and committed:
  `wscatalog.catalog` (SQLite schema version 2, gzip, sha256, manifest
  entry), `metadata` (5.2 step 3), `wikidata` (items cached in
  `pipeline/cache/wikidata.sqlite`), `report` (`report/{wiki}.md`),
  `sites` (sitematrix), `build` (dumps → extract → catalog → manifest),
  `acceptance` (PLAN.md WP1 checks); `Makefile`; `.github/workflows/catalog.yml`;
  `pipeline/tests` (fixture rows); configs for no, is, de, fr, en, it, sv,
  da, fi, es, mul. The three eyeball tickets are fixed; the de rule-3
  re-sample is in `report/wp1a-review.md` (25 of 30).
- Running when last updated: `python -I -m wscatalog.catalog de fr en`
  fetching Wikidata for fr and en (slow: Wikidata maxlag). If interrupted,
  rerun it; fetched items are cached and committed per batch.
- Still to do: re-extract fr and en (extract version 6) and rebuild all
  catalogs; en acceptance checks; final STATUS.md. Done since: Wikidata
  mappings in `default.yaml`, en document typing, PLAN.md notes, README.
- **Owner's decision (2026-11-01): Wikidata for it, sv, da, fi, es and mul
  is fetched by the first CI run**, not locally (it would take about an
  hour; the bottleneck is Wikidata's server time and maxlag, not the
  connection). Their local catalogs are built with `--offline` from the
  header and Index data only, so their author, year and genre figures in
  `report/{wiki}.md` are lower bounds.

## How to resume / rerun

- Pipeline: `python3 -m venv .venv && .venv/bin/pip install -e pipeline`
  (needs PyYAML, requests). Dumps (2026-10-01 run, about 6.4 GB in
  `pipeline/cache/dumps/`, gitignored):
  `.venv/bin/python -I -m wscatalog.dumps nowikisource articles page_props`.
  Extract (cached in `pipeline/cache/extract/{wiki}.pickle`):
  `.venv/bin/python -I -m wscatalog.extract no`. Survey report:
  `.venv/bin/python -I -m wscatalog.survey no` → `report/no-structure.md`.
  Resolution and quality rules can be changed and re-run without
  re-extracting; header parameter and transclusion settings in the config
  need a fresh extract.
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
- **Tickets from the eyeball review** (report/wp1a-review.md, decision 9):
  `scan-volumes` orders no periodicals by scan file name ("Andet Bind"
  first, *Historisk Tidsskrift*); `Fru Bovary` attaches each part twice
  (subpage and colon root page); the Kommersbuch TOC gives "hier" as a
  display title (link text of a navigation link).
- **de residue after decision 4**: 290 unknown works remain, mostly colon
  pages of small page-by-page books whose root is listed by two TOC pages
  (`Ausführliche Vorschriften zur Blitz-Ableitung …:Seite N`) and the
  `Ersch-Gruber:` lexicon entries (30 pages, below the 100-page prefix
  survey threshold).
- **en unknown works** (33,564, shown by default): about half are
  government documents (court cases, proclamations, speeches, acts,
  executive orders, UN resolutions). The metadata merge (5.2 step 3) must
  type them as documents, or the en shelf reads like a law library.
- The prototype uses plain Views, not Compose (Compose was not in the Gradle
  cache); the production reader uses Compose `AndroidView` + `pointerInput`
  as planned.
