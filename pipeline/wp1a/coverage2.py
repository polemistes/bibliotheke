# SPDX-License-Identifier: GPL-3.0-or-later
"""Decision 7: candidate coverage rules compared on one wiki.

    .venv/bin/python -I coverage2.py <wiki>
"""
from __future__ import annotations

import random
import sys
from collections import Counter

from wscatalog import survey

wiki = sys.argv[1]
s = survey.Survey(wiki)
res, cat = s.res, s.in_catalog()


# pages of each scan file transcluded by each work (to find pages that belong
# to another work in the same scan: several texts bound in one volume)
from collections import defaultdict
extra = {}
users = defaultdict(lambda: defaultdict(set))     # file -> work title -> pages
for w in cat:
    for p in w.parts:
        if p.node.rec:
            for ff, n in res.scan_pages(p.node.rec):
                if n is not None:
                    users[ff][w.root.title].add(n)


def spans(w):
    f = w.root.index
    lv = res.levels.get(f)
    existing = {i for i, x in enumerate(lv) if x in (1, 2, 3, 4)}
    used = set()
    for p in w.parts:
        if p.node.rec:
            used.update(n for ff, n in res.scan_pages(p.node.rec) if ff == f)
    used &= existing
    others = set()
    for t, pg in users.get(f, {}).items():
        if t != w.root.title:
            others |= pg
    ex2 = existing - (others - used)
    extra[w.root.title] = (len(used) / len(ex2) if ex2 else 1.0, len(ex2) - len(used))
    if not used:
        return existing, used, 0, 0, len(existing), 0.0, Counter(lv[i] for i in existing)
    lo, hi = min(used), max(used)
    unc = existing - used
    front = sum(1 for i in unc if i < lo)
    back = sum(1 for i in unc if i > hi)
    inner = len(unc) - front - back
    return existing, used, front, inner, back, len(used) / (len(used) + inner), Counter(lv[i] for i in unc)


rows = []
for w in cat:
    q = s.wq(w)
    if q.coverage is None:
        continue
    other = q.reason not in (None, "coverage")   # another incompleteness rule already applies
    existing, used, front, inner, back, icov, lv = spans(w)
    rows.append((w, q, q.coverage, icov, front, inner, back, other, lv, *extra[w.root.title]))

RULES = {
    "R0 total < 95 % (current)": lambda c, ic, fr, inn, bk, co, uo: c < .95,
    "R1 total < 90 %": lambda c, ic, fr, inn, bk, co, uo: c < .90,
    "R2 inner < 95 % or total < 50 %": lambda c, ic, fr, inn, bk, co, uo: ic < .95 or c < .50,
    "R3 inner < 95 % or total < 80 %": lambda c, ic, fr, inn, bk, co, uo: ic < .95 or c < .80,
    "R4 inner < 90 % or total < 50 %": lambda c, ic, fr, inn, bk, co, uo: ic < .90 or c < .50,
    "R5 pages of other works excluded, < 95 %": lambda c, ic, fr, inn, bk, co, uo: co < .95,
    "R6 R5 and more than 3 pages uncovered": lambda c, ic, fr, inn, bk, co, uo: co < .95 and uo > 3,
    "R7 R5 at 90 %": lambda c, ic, fr, inn, bk, co, uo: co < .90,
    "R8 R5 at 90 % and more than 3 pages uncovered": lambda c, ic, fr, inn, bk, co, uo: co < .90 and uo > 3,
}
print(f"# {wiki}: coverage rules over {len(rows):,} works that are their Index's book\n")
print("| rule | flagged | flagged with no other incompleteness reason |\n|---|---|---|")
flag = {}
for name, fn in RULES.items():
    f = [r for r in rows if fn(*r[2:7], r[9], r[10])]
    flag[name] = f
    print(f"| {name} | {len(f):,} | {sum(1 for r in f if not r[7]):,} |")

CMP = sys.argv[2] if len(sys.argv) > 2 else "R2 inner < 95 % or total < 50 %"
print(f"\nComparison rule: {CMP}")
# what the comparison rule un-flags relative to R0, and the levels of their uncovered pages
r0 = {id(r[0]) for r in flag["R0 total < 95 % (current)"]}
r2 = {id(r[0]) for r in flag[CMP]}
unflag = [r for r in rows if id(r[0]) in r0 and id(r[0]) not in r2]
lv = Counter()
for r in unflag:
    lv.update(r[8])
print(f"\nR0-only (un-flagged by the comparison rule): {len(unflag):,} works; levels of their uncovered pages: "
      + ", ".join(f"L{k}: {v:,}" for k, v in sorted(lv.items())))
rng = random.Random("cov2")
print(f"\n15 random works that the comparison rule un-flags (sole reason coverage under R0):\n")
sole = [r for r in unflag if not r[7]]
for w, q, c, ic, fr, inn, bk, other, lvl, co, uo in rng.sample(sole, min(15, len(sole))):
    print(f"- `{survey.esc(w.root.title)}` ({w.structure}, {w.part_count} parts, {len(w.missing)} red links, {q.tier}): "
          f"total {c:.0%}, inner {ic:.0%}, excl. other works {co:.0%} ({uo} pages); uncovered front {fr}, inner {inn}, back {bk}; levels "
          + ", ".join(f"L{k}: {v}" for k, v in sorted(lvl.items())))
print(f"\n15 random works that the comparison rule still flags with coverage as the sole reason:\n")
keep = [r for r in flag[CMP] if not r[7]]
for w, q, c, ic, fr, inn, bk, other, lvl, co, uo in rng.sample(keep, min(15, len(keep))):
    print(f"- `{survey.esc(w.root.title)}` ({w.structure}, {w.part_count} parts, {len(w.missing)} red links, {q.tier}): "
          f"total {c:.0%}, inner {ic:.0%}, excl. other works {co:.0%} ({uo} pages); uncovered front {fr}, inner {inn}, back {bk}; levels "
          + ", ".join(f"L{k}: {v}" for k, v in sorted(lvl.items())))
# default view effect
dv_now = sum(1 for w in cat if s.wq(w).complete and s.wq(w).tier in ("validated", "proofread") and w.type != "reference")
gain = sum(1 for r in sole if r[1].tier in ("validated", "proofread") and r[0].type != "reference")
print(f"\nDefault view now {dv_now:,} works; the comparison rule would add {gain:,} validated/proofread works to it.")
