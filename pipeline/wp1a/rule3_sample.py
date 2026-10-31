# SPDX-License-Identifier: GPL-3.0-or-later
"""(WP1, 2026-11-01) Works whose membership the rule-3 extension (headerless_toc_members, de)
changed, sampled for eyeballing (STATUS.md: re-sample after decision 4).

    python -I pipeline/wp1a/rule3_sample.py > report/de-rule3-sample.md
"""
import dataclasses, random, sys
from pathlib import Path
from wscatalog import config, extract, structure
ext = extract.load("de")
cfg = config.load("de")
on = structure.Resolver(cfg, ext).run()
off = structure.Resolver(dataclasses.replace(cfg, headerless_toc_members=False), ext).run()
def members(res):
    return {w.root.title: {p.node.title for p in w.parts if p.node is not w.root} for w in res.works}
m_on, m_off = members(on), members(off)
changed = [t for t, ms in m_on.items() if ms != m_off.get(t, set()) and len(ms) > len(m_off.get(t, set()))]
gained = {t: len(m_on[t]) - len(m_off.get(t, set())) for t in changed}
print("# de: works grouped by the rule-3 extension (re-sample, WP1)\n")
print(f"Seed `wp1-rule3-de`. {len(changed)} works gained members; {sum(gained.values()):,} pages attached; "
      f"works {len(off.works):,} -> {len(on.works):,}")
print("largest:", ", ".join(f"{t} (+{g})" for t, g in sorted(gained.items(), key=lambda x: -x[1])[:12]))
by = {w.root.title: w for w in on.works}
rng = random.Random("wp1-rule3-de")
for i, t in enumerate(rng.sample(changed, min(30, len(changed))), 1):
    w = by[t]
    print(f"\n{i}. `{t}`: {w.structure}, {w.type}, {w.part_count} parts ({gained[t]} new), order {w.root.order_source}")
    new = m_on[t] - m_off.get(t, set())
    shown = 0
    for p in w.parts:
        if shown >= 10: break
        mark = " [new]" if p.node.title in new else ""
        print(f"   {'  ' * p.depth}- {p.kind}: {p.node.display or p.node.title} (`{p.node.title}`){mark}")
        shown += 1
    if len(w.parts) > 10:
        print(f"   - … {len(w.parts) - 10} more; last: `{w.parts[-1].node.title}`")
