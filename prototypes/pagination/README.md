# Pagination prototype (WP0)

Spike for PLAN.md 8.1 and 7.4: one CSS column per screen page, pages turned
by translating the content, and `(part, charOffset)` locators that survive
font-size and geometry changes. Findings: [docs/wp0-findings.md](../../docs/wp0-findings.md)
section 4. This is throwaway spike code; WP3/WP4 build the real normaliser
and reader.

| file | purpose |
|---|---|
| `make_content.py` | minimal normaliser: raw Parsoid fixtures → `web/content/*.js` |
| `web/reader.css`, `web/reader.js` | pagination engine and locator maths |
| `web/test.js` | locator tests, run inside the page |
| `web/harness.html` | interactive viewer (`?content=sult-02`, arrow keys, `+`/`-`) and test entry point (`&test=1`) |
| `run_browser_test.sh`, `summarise.py` | run the tests in headless Chromium at phone size |
| `android/` | WebView prototype for the emulator (swipe overlay, frame timing) |

```sh
python3 prototypes/pagination/make_content.py     # regenerate content
prototypes/pagination/run_browser_test.sh         # writes docs/wp0/pagination-browser.json
```

The content files contain Wikisource texts (public domain; CC BY-SA 4.0
contributor layer); see `source.json` beside each fixture for the source page.
