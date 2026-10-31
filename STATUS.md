# STATUS

Working state of the implementation of [PLAN.md](PLAN.md). Update this file
before stopping work for any reason (PLAN.md section 0).

Last updated: 2026-10-31

## Current package

**WP0 — Spike and verification** (PLAN.md section 9)

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
- [ ] 7. `docs/wp0-findings.md` written; **VERIFY** items in PLAN.md updated

## Done

- Repository initialised.
- `pipeline/wp0/wmhttp.py`: polite HTTP helper (UA, per-host interval,
  backoff, disk cache in `pipeline/cache/wp0/`). Smoke-tested against
  no.wikisource.org.
- Namespace numbers for the 11 wikis confirmed (`pipeline/wp0/namespaces.py`,
  findings section 1). de and fi have no Author namespace.
- `docs/wp0-findings.md` started; each step appends its section.
- Parsoid endpoint verified; 17 raw fixtures saved (no 5, en 4, fr 5, de 3)
  in `app/src/test/fixtures/`; findings section 2 documents markup shapes.
- Wikidata coverage measured for no (findings section 3): only 13 % of root
  pages have a QID; header microformats / Index pages are the main metadata
  source. WP1 needs the pages-articles dump as an input.
- Pagination prototype (`prototypes/pagination/`): locator round-trip passes
  on all pages of 5 parts in headless Chromium (findings section 4).
- Android WebView prototype on the emulator (AVD `bibliotheke_wp0`): locator
  tests pass; page turns at 60 fps (no skipped vsync); rotation keeps the
  position with a reading anchor (findings 4.5, 4.6).

## In progress

- WP0 step 7: write up remaining findings sections and update the VERIFY
  items in PLAN.md.

## Known problems and open questions

- **Repo URL for the User-Agent is not known yet.** The owner plans
  `https://github.com/<user>/bibliotheke` but the account name is not given.
  Until it is, the User-Agent omits the URL and carries only the contact
  address, which Wikimedia's UA policy accepts:
  `Bibliotheke/0.1.0-dev (develop@robertemilberge.no) <client>/<version>`.
  The URL lives in a single constant so it can be filled in later.
- **STATUS.md location**: PLAN.md section 0 says repo root; sections 6 and 12
  say `docs/STATUS.md`. Kept at the repo root, as section 0 (the working
  instructions) specifies.
