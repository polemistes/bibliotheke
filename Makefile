# SPDX-License-Identifier: GPL-3.0-or-later
# Catalog pipeline (PLAN.md 5.6). `make catalog WIKI=no` builds
# pipeline/out/no.sqlite.gz, report/no.md and pipeline/out/manifest.json.

PY ?= .venv/bin/python
WIKI ?= no

.PHONY: venv catalog catalogs extract survey acceptance test

venv: .venv/bin/python
.venv/bin/python:
	python3 -m venv .venv
	.venv/bin/pip install -e 'pipeline[test]'

catalog: venv                      ## dumps -> extract -> catalog -> manifest, for WIKI (one or more)
	$(PY) -I -m wscatalog.build $(WIKI)

catalogs: venv                     ## every wiki with a config of its own
	$(PY) -I -m wscatalog.build --configured

extract: venv
	$(PY) -I -m wscatalog.extract $(WIKI)

survey: venv                       ## structure survey report/{wiki}-structure.md (5.2 step 0)
	$(PY) -I -m wscatalog.survey $(WIKI)

acceptance: venv                   ## WP1 acceptance checks on the built catalogs
	$(PY) -I -m wscatalog.acceptance $(WIKI)

test: venv
	cd pipeline && ../$(PY) -m pytest -q
