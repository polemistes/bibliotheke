# Catalog pipeline (`wscatalog`)

Python 3.12+ package for PLAN.md section 5. So far it covers the WP1a
structure and quality survey; the catalog writer (5.2 steps 3, 6–8) follows
once the owner has set the thresholds (STATUS.md).

| module | role |
|---|---|
| `http.py` | polite HTTP: project User-Agent, one request at a time per host, backoff |
| `dumps.py` | download dump files of the newest complete run, resume, sha1 check |
| `config.py` | per-wiki config: `config/{wiki}.yaml` over `config/default.yaml` |
| `wikitext.py` | templates, parameters, links, `<pages>` tags |
| `extract.py` | one pass over pages-articles → raw signals, cached in `cache/extract/` |
| `structure.py` | exclusions and resolution into works and parts (5.2 steps 1–1h) |
| `quality.py` | tiers, completeness, hard exclusions (5.4) |
| `survey.py` | `report/{wiki}-structure.md` (5.2 step 0) |

```sh
python3 -m venv .venv && .venv/bin/pip install -e pipeline
.venv/bin/python -I -m wscatalog.dumps nowikisource articles page_props
.venv/bin/python -I -m wscatalog.extract no
.venv/bin/python -I -m wscatalog.survey no
```

Dumps, extracts and HTTP responses live in `pipeline/cache/` (gitignored).
Resolution and quality rules can be changed and re-run on the cached
extract; header parameter and transclusion settings in a config need a new
extract.
