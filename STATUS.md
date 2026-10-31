# STATUS

Working state of the implementation of [PLAN.md](PLAN.md). Update this file
before stopping work for any reason (PLAN.md section 0).

Last updated: 2026-10-31

## Current package

**WP0 — Spike and verification: complete.** Current: **WP1 — Catalog
pipeline**. WP1 follows `docs/work-structure-plan.md` (decided by the owner
on 2026-10-31):

- [x] 1. Planning files committed as written
- [x] 2. Section 9 edits applied to PLAN.md, marked "(WS, 2026-10-31 …)"
      and citing the plan as "WS§n"; D1–D9 unchanged
- [x] 3. WP1a: structure and quality survey over the pages-articles dumps of
      no, is, de, fr, en → `report/{wiki}-structure.md`, eyeball review in
      `report/wp1a-review.md`; numbers below. **Stopped here: waiting for the
      owner** to set the thresholds and per-wiki `show_unknown_by_default`
      (section "WP1a results" below)
- [ ] 4. WP1 pipeline, schema version 2, acceptance cases of PLAN.md WP1

## WP1a results (dumps of 2026-10-01; details in `report/`)

| wiki | works | in catalog | validated | proofread | unproofread | unknown | incomplete | default view: works (texts) | with unknown shown |
|---|---|---|---|---|---|---|---|---|---|
| no | 1,118 | 1,069 | 73 | 466 | 307 | 223 | 123 | 463 (2,359) | 678 |
| is | 256 | 250 | 1 | 6 | 0 | 243 | 4 | 5 (60) | 246 |
| de | 26,637 | 26,035 | 14,434 | 6,999 | 266 | 4,336 | 2,169 | 19,334 (35,451) | 23,638 |
| fr | 40,889 | 40,195 | 11,255 | 21,246 | 7,574 | 120 | 2,740 | 30,793 (120,350) | 30,904 |
| en | 57,148 | 55,867 | 6,987 | 12,544 | 2,772 | 33,564 | 3,098 | 17,971 (112,724) | 50,859 |

"Default view" = validated or proofread, complete, not `reference` (PLAN.md
5.4). Structure patterns (works): collections of root pages (A) no 82, de
433, fr 815, en 57; mixed (B) no 38, de 89, fr 413, en 85; virtual roots (D)
no 34, de 46, fr 851, en 738; reference works fr 274, en 35.

Parts table (WS§5.2), gzipped SQLite with its index, at `parts_cap` 500 /
2,000 / unlimited (+ parts_fts at 2,000): no 0.51 / 0.61 / 0.61 MB (0.12);
de 2.83 / 3.28 / 3.28 MB (0.86); fr 14.3 / 15.4 / 24.9 MB (2.5); en 16.8 /
19.5 / 30.3 MB (3.2). Rows at 2,000: en 341,139, fr 263,024. A compact
encoding (title suffixes, no per-part quality_pages) saves about 14 %.

Eyeball acceptance (≥ 27 of 30 correct for A, B, D, E): met everywhere
except en B (26/30); see `report/wp1a-review.md`.

**Decisions for the owner before the pipeline is built:**

1. `show_unknown_by_default` for is, de, fr, en (no is already true).
   is: 243 of 250 works are unknown (typed-in sagas and folk tales; off
   leaves 5 works). de: 4,336 unknown, mostly books stored page by page
   under a colon prefix (`Schwere, Elektricität und Magnetismus:382`) and
   leftover bibliography pages; real de texts all declare a status. fr: 120.
   en: 33,564, 60 % of the catalog, classics included (off: 17,971 works).
2. `parts_cap` (decision 8.4): the en parts table is 19.5 MB at 2,000 and
   16.8 MB at 500, well above the plan's 5–8 MB estimate; the compact
   encoding, if wanted, is a schema change.
3. `reference_threshold` (2,000 parts without an Index type): also types
   en *Popular Science Monthly*, the *Nicene and Post-Nicene Fathers* and is
   *Íslenzkar þjóðsögur og æfintýri* (3,058 tales) as reference works;
   lexica below it (no *Norsk Haandlexikon*, 1,138 entries; fr *Dictionnaire
   érotique moderne*, 1,717) stay collections whose entries become texts.
4. de colon prefixes: 36 prefixes have ≥ 100 root pages (report de §8);
   7 are excluded (`RE:`, `MKL1888:`, `ADB:`, `BLKÖ:`, `Zedler:`,
   `Boetticher:`, `Haan:`). Among the kept: `Topographia …:` (Merian,
   reference-like), `Hantzsch:` (Dresden street names), and books stored
   page by page (`Schwere, Elektricität und Magnetismus:`, `Elektrische
   Kraft Hertz:`, `Allgemeines Deutsches Kommersbuch:`), each page now a
   separate work. Options: exclude by prefix, keep, or a new rule treating
   `Prefix:` like a subpage separator (not in the plan).
5. Acceptance figures in PLAN.md WP1 that the decided rules do not give:
   `Diktsamling` has 4 parts, not 9 (5 of its 9 existing TOC targets are
   same-titled poems of other books whose own headers name another
   container, rule 2); the no default view is 463 works, not about 1,500
   (the estimate counted title groups; grouping folds e.g. 403
   *Historisk Tidsskrift* articles into one work; 2,359 texts); the en
   budget (item 2). The other named cases pass: Sagenschatz 1,001 parts in
   chain order, Heimath one virtual root with three volumes, Ozymandias
   finds the Hutchinson text (and three others), Käthe oversized, the KHM
   overview excluded as a topic page, KHM Band 1 (1857) a work.
6. Index progress `MS`/`OCR`/`L` as a hard exclusion: on en, 82 of the 141
   works it excludes would be proofread or validated by their page levels
   (fr 40 of 109). Keep, or exclude only works without proofread pages?
   (no's `Fremgang` is free text; the codes are ignored there.)
7. Coverage under 95 % of the Index's text pages marks 854 fr works
   incomplete (of 2,906 checked); keep the 95 % threshold?
8. Text versus chapter: label patterns tuned per language (decision 8.9).
   Titled chapters of prose books still count as searchable texts (all
   searchable subpage texts: no 3,683, de 8,097, fr 345,288, en 429,663,
   reference works included; samples in report §12 "all searchable subpage
   texts"). A stricter rule would need a signal beyond the title (not in
   the plan).
9. Implementation choices made to fill gaps in WS§4 (each report §13):
   container ancestor and volume placement, rule 3 reading of "reached by
   the chain", HERKUNFT links preferring the shared Index, `toc-partial`
   and `scan-volumes` order, slash punctuation, de portal markers, bare
   `<pages>`, fr dictionary roots, editions under versions pages.

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
- `app/src/test/fixtures/{no,en,fr,de}/`: 17 raw Parsoid fixtures with
  `source.json` provenance (no 5, en 4, fr 5, de 3).
- `prototypes/pagination/`: reader CSS/JS, locator tests, browser runner,
  Android WebView prototype (`android/`) with a device test script.
- PLAN.md: VERIFY items resolved; WP0 corrections marked "(WP0 …)".
- User-Agent (repo URL given by the owner after WP0):
  `Bibliotheke/0.1.0-dev (https://github.com/polemistes/bibliotheke; develop@robertemilberge.no) <client>/<version>`;
  the URL is `REPO_URL` in `pipeline/wscatalog/http.py` (and in the WP0
  spike helper `pipeline/wp0/wmhttp.py`).

## In progress

- Nothing running. WP1a is complete; task 4 (the WP1 pipeline) waits for
  the owner's decisions above. Built so far in `pipeline/wscatalog/`: dump
  downloader, extractor, structure resolution (PLAN.md 5.2 steps 1–1h),
  quality rules (5.4), survey report; configs in `pipeline/config/`.

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
- The prototype uses plain Views, not Compose (Compose was not in the Gradle
  cache); the production reader uses Compose `AndroidView` + `pointerInput`
  as planned.
