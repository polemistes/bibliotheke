# STATUS

Working state of the implementation of [PLAN.md](PLAN.md). Update this file
before stopping work for any reason (PLAN.md section 0).

Last updated: 2026-10-31

## Current package

**WP0 — Spike and verification: complete.** Next: **WP1 — Catalog pipeline**
(not started).

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
  helper; responses cached in `pipeline/cache/wp0/` (gitignored).
- `app/src/test/fixtures/{no,en,fr,de}/`: 17 raw Parsoid fixtures with
  `source.json` provenance (no 5, en 4, fr 5, de 3).
- `prototypes/pagination/`: reader CSS/JS, locator tests, browser runner,
  Android WebView prototype (`android/`) with a device test script.
- PLAN.md: VERIFY items resolved; WP0 corrections marked "(WP0 …)".
- User-Agent (repo URL given by the owner after WP0):
  `Bibliotheke/0.1.0-dev (https://github.com/polemistes/bibliotheke; develop@robertemilberge.no) <client>/<version>`;
  the URL is `REPO_URL` in `pipeline/wp0/wmhttp.py`.

## In progress

- Nothing. WP1 starts from PLAN.md section 5 plus findings section 6.

## How to resume / rerun

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
- **For the owner to decide** (findings section 6, WP1 item 6): some
  collections are TOC pages linking separate root pages (de, and also no,
  e.g. Vinje's `Diktsamling`). WP1 will list each linked text as its own
  work; grouping them under the collection would be a later improvement.
- The prototype uses plain Views, not Compose (Compose was not in the Gradle
  cache); the production reader uses Compose `AndroidView` + `pointerInput`
  as planned.
