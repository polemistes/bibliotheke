# WP0 spike scripts

Throwaway scripts that produced the evidence in
[docs/wp0-findings.md](../../docs/wp0-findings.md). Run from the repo root
with Python 3.12+; they need `requests`, `beautifulsoup4` and `lxml`.
Responses are cached in `pipeline/cache/wp0/` (gitignored), so re-runs do
not hit the wikis again. WP1 replaces this with the real pipeline package.

| script | output | findings |
|---|---|---|
| `wmhttp.py` | polite HTTP helper: User-Agent, per-host interval, backoff, cache | – |
| `namespaces.py` | `docs/wp0/sitematrix.json`, `docs/wp0/namespaces.json` | §1 |
| `search.py` | search a wiki for sample pages | §2 |
| `parsoid.py` | `analyse` a page's markup; `save` a fixture to `app/src/test/fixtures/` | §2 |
| `wikidata_no.py` | `docs/wp0/wikidata-no.json` | §3 |
| `index_progress.py` | `docs/wp0/index-progress.json` | §5 |

```sh
python3 pipeline/wp0/namespaces.py
python3 pipeline/wp0/parsoid.py analyse no.wikisource.org "Arne"
python3 pipeline/wp0/parsoid.py save no arne no.wikisource.org "Arne"
```
