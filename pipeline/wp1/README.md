# WP1 decision measurements

One-off scripts behind the owner's decisions of 2026-11-01 on the six items
the first WP1 build left open (`report/wp1-decisions.md`). They read the
catalogs built in `pipeline/out/` and the cached extracts and print
markdown. `catalog_queries.py`, `unknown_samples.py` and `fts_rowid_size.py`
were run on the catalogs built before the decisions were implemented, so
their numbers describe the state *before* the changes (the default view
still includes documents, `parts_fts` still has the sparse rowid). Run from
the repository root:

```sh
.venv/bin/python -I pipeline/wp1/unknown_samples.py sv da fi es mul   # item 3
.venv/bin/python -I pipeline/wp1/catalog_queries.py                   # items 1, 4, 5
.venv/bin/python -I pipeline/wp1/extract_queries.py no                # item 1 (also de, es, sv for item 5)
.venv/bin/python -I pipeline/wp1/fts_rowid_size.py en                 # item 2
.venv/bin/python -I pipeline/wp1/textless_toc_sample.py sv > report/sv-textless-toc-sample.md   # item 5, eyeball sample
```

| script | measures |
|---|---|
| `unknown_samples.py` | the unknown works of a wiki by type, structure, genre, author and size, and 30 pseudo-random ones |
| `catalog_queries.py` | no works without an author; en works typed `document`; the de Brass diary, Codex Manesse, es dictionaries and colon prefixes, sv and fi oddities |
| `extract_queries.py` | the Index author fields behind the no misses; headers, categories and links of the de, es and sv pages that stay ungrouped |
| `fts_rowid_size.py` | en `parts_fts` gzipped under sparse and sequential rowids, with and without author names |
| `textless_toc_sample.py` | the sv works the textless-TOC-root rule groups, 30 at random (all 15 on the 2026-10-01 dump) |
