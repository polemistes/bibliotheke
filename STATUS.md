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
- [ ] 3. WP1a: structure and quality survey over the pages-articles dumps of
      no, is, de, fr, en → `report/{wiki}-structure.md`; numbers in STATUS.md;
      **then stop**: the owner sets the thresholds and per-wiki
      `show_unknown_by_default`
- [ ] 4. WP1 pipeline, schema version 2, acceptance cases of PLAN.md WP1

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

- WP1a survey (task 3). Done so far: pipeline package `pipeline/wscatalog/`
  (dump downloader, extractor, structure resolution, quality rules, survey
  report), configs for no, is, de, fr, en in `pipeline/config/`; reports
  for is, no, de written to `report/`. Running: fr and en extraction. Then:
  fr/en reports, eyeball samples for patterns A, B, D, E, numbers below.

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
