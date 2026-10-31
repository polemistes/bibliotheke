# Catalog pipeline (`wscatalog`)

Python 3.12+ package for PLAN.md section 5: per-wiki SQLite catalogs
(schema version 2, PLAN.md 5.3), the coverage reports and `manifest.json`.

| module | role |
|---|---|
| `http.py` | polite HTTP: project User-Agent, one request at a time per host, backoff, maxlag waits |
| `dumps.py` | download dump files of the newest complete run, resume, sha1 check |
| `sites.py` | the open Wikisource wikis (sitematrix), cached |
| `config.py` | per-wiki config: `config/{wiki}.yaml` over `config/default.yaml`; a generic config for other wikis |
| `wikitext.py` | templates, parameters, links, `<pages>` tags |
| `extract.py` | one pass over pages-articles → raw signals, cached in `cache/extract/` |
| `structure.py` | exclusions and resolution into works and parts (5.2 steps 1–1h) |
| `quality.py` | tiers, completeness, hard exclusions (5.2 steps 4–5, 5.4) |
| `wikidata.py` | edition, work and author items, cached in `cache/wikidata.sqlite` |
| `metadata.py` | metadata merge: title, authors, year, language, genres, type (5.2 step 3) |
| `catalog.py` | SQLite writer, gzip, sha256, manifest (5.2 steps 6 and 8) |
| `report.py` | coverage report `report/{wiki}.md` (5.2 step 7) |
| `build.py` | end to end: dumps → extract → catalog → manifest |
| `acceptance.py` | PLAN.md WP1 acceptance checks on the built catalogs |
| `survey.py` | structure survey `report/{wiki}-structure.md` (5.2 step 0) |
| `../wp1a/` | one-off measurements behind the owner's decisions of 2026-11-01 (`report/wp1a-decisions.md`) |
| `../wp1/` | one-off measurements behind the owner's decisions after the first build (`report/wp1-decisions.md`) and the sv sample script |

```sh
make venv                      # .venv with the package and pytest
make catalog WIKI=no           # pipeline/out/no.sqlite.gz, report/no.md, pipeline/out/manifest.json
make catalogs                  # every wiki with a config of its own
make acceptance WIKI="no de en"
make test
.venv/bin/python -I -m wscatalog.build --all          # every open Wikisource
.venv/bin/python -I -m wscatalog.catalog no --offline  # rebuild from the cached extract, Wikidata from the cache
```

Dumps, extracts, Wikidata items and HTTP responses live in `pipeline/cache/`,
catalogs and manifest in `pipeline/out/` (both gitignored). Resolution,
quality and metadata rules can be changed and rerun on the cached extract;
header parameter and transclusion settings in a config need a new extract
(`build.py` re-extracts when the dump or `extract.EXTRACT_VERSION` changes).

CI: `.github/workflows/catalog.yml` builds every wiki weekly (one job per
wiki, Wikidata cache restored per wiki) and publishes the gzipped catalogs
and `manifest.json` to the rolling release `catalog-latest` (D6).
