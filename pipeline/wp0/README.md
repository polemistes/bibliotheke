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

Structure and quality spikes added on 2026-10-31 while planning
[docs/work-structure-plan.md](../../docs/work-structure-plan.md); their raw
outputs are in [docs/structure/](../../docs/structure/). `quality_works_no.py`
is the prototype of the WP1a structure survey.

| script | output | plan section |
|---|---|---|
| `structure_titles.py` | root pages vs subpages per wiki from `all-titles-in-ns0` dumps | 1 |
| `structure_inspect.py` | header template parameters and link targets of a fixture or live page | 3 |
| `search_counts.py` | CirrusSearch hit counts for quality templates and scan transclusion | 10 |
| `quality_categories.py` | scan pages per proofreading level on the 11 wikis | 10 |
| `quality_works_no.py` | per-work quality tiers for no from the pages-articles dump | 6, 10 |

```sh
python3 pipeline/wp0/namespaces.py
python3 pipeline/wp0/parsoid.py analyse no.wikisource.org "Arne"
python3 pipeline/wp0/parsoid.py save no arne no.wikisource.org "Arne"
```
