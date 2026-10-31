# SPDX-License-Identifier: GPL-3.0-or-later
"""Structure spike (2026-10-31): root pages vs subpages from all-titles-in-ns0 dumps.

    curl -A "$UA" -O https://dumps.wikimedia.org/nowikisource/latest/nowikisource-latest-all-titles-in-ns0.gz
    python3 pipeline/wp0/structure_titles.py nowikisource-latest-all-titles-in-ns0.gz ...

Counts include redirects. Evidence: docs/structure/title-counts.txt.
"""
import gzip, sys
from collections import Counter

PREFIXES = ("RE:", "MKL1888:", "ADB:", "BLKÖ:")
for path in sys.argv[1:]:
    titles = set()
    with gzip.open(path, "rt", encoding="utf-8", errors="replace") as f:
        next(f)  # header line "page_title"
        for line in f:
            t = line.rstrip("\n")
            if t:
                titles.add(t)
    roots = {t for t in titles if "/" not in t}
    subs = titles - roots
    depth = Counter(t.count("/") for t in subs)
    parents = Counter(t.split("/")[0] for t in subs)
    roots_with_subs = {p for p in parents if p in roots}
    orphan_trees = {p for p in parents if p not in roots}
    orphan_subpages = sum(n for p, n in parents.items() if p not in roots)
    colon_roots = Counter(t.split(":")[0] + ":" for t in roots if ":" in t and not t.startswith(":"))
    known_prefix_roots = sum(1 for t in roots if t.startswith(PREFIXES))
    texte_entier = sum(1 for t in subs if t.rsplit("/", 1)[1].lower().replace("_", " ") in
                       ("texte entier", "whole text", "full text", "gesamttext", "hele teksten"))
    name = path.rsplit("/", 1)[-1].replace(".gz", "")
    print(f"\n=== {name} (ns0 titles incl. redirects) ===")
    print(f"titles                 {len(titles):>8}")
    print(f"root pages             {len(roots):>8}   of which known lexicon prefixes {known_prefix_roots}")
    print(f"subpages               {len(subs):>8}   depth histogram {dict(sorted(depth.items()))}")
    print(f"roots with subpages    {len(roots_with_subs):>8}   ({100*len(roots_with_subs)/max(1,len(roots)):.1f} % of roots)")
    print(f"orphan trees (root missing) {len(orphan_trees):>5}   holding {orphan_subpages} subpages; e.g. {sorted(orphan_trees)[:4]}")
    print(f"'whole text' subpages  {texte_entier:>8}")
    big = parents.most_common(5)
    print(f"largest subpage trees  {big}")
    print(f"top colon prefixes among roots: {colon_roots.most_common(12)}")
