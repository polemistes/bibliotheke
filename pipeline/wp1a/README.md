# WP1a decision measurements

One-off scripts behind the owner's decisions of 2026-11-01
(`report/wp1a-decisions.md`, STATUS.md items 1–9). They run on the cached
extracts (`pipeline/cache/extract/{wiki}.pickle`) through the survey module
and print markdown; they were written against the resolver as of commit
eccee8d (before the rule changes those decisions led to), so their numbers
describe the state *before* the changes. Run from the repository root:

```sh
.venv/bin/python -I pipeline/wp1a/analyze.py en        # decisions 1–8, one wiki
.venv/bin/python -I pipeline/wp1a/de_prefixes.py       # de colon-prefix pages and their roots (item 4)
.venv/bin/python -I pipeline/wp1a/de_rule3.py          # size of the rule-3 extension on de (item 4)
.venv/bin/python -I pipeline/wp1a/coverage2.py fr "R6 R5 and more than 3 pages uncovered"   # item 7
.venv/bin/python -I pipeline/wp1a/size2.py en          # parts-table encodings (item 2)
```

| script | measures |
|---|---|
| `analyze.py` | unknown works by kind and header; parts-table size by component; works over `reference_threshold` with their order source; de colon prefixes; index-faulty works by page-level tier; where uncovered scan pages lie; texts a page-title chapter rule would demote |
| `de_prefixes.py` | header, Index and links of colon-prefixed de pages and of the root pages that list them |
| `de_rule3.py` | how many pages the rule-3 extension attaches, under which roots, and what remains unknown |
| `coverage2.py` | candidate coverage rules R0–R8 compared, with samples of what each un-flags and keeps |
| `size2.py` | raw and gzipped size of the parts table under the candidate encodings |
