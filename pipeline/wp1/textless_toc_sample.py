# SPDX-License-Identifier: GPL-3.0-or-later
"""(WP1, 2026-11-01) Works whose membership the textless-TOC-root rule
(structure.textless_toc_roots, sv) changed, sampled for eyeballing
(report/wp1-decisions.md item 5).

    .venv/bin/python -I pipeline/wp1/textless_toc_sample.py > report/sv-textless-toc-sample.md
"""
import dataclasses, random, sys
from wscatalog import config, extract, structure
wiki = sys.argv[1] if len(sys.argv) > 1 else "sv"
ext = extract.load(wiki)
cfg = config.load(wiki)
on = structure.Resolver(dataclasses.replace(cfg, textless_toc_roots=True), ext).run()
off = structure.Resolver(dataclasses.replace(cfg, textless_toc_roots=False), ext).run()
def members(res):
    return {w.root.title: {p.node.title for p in w.parts if p.node is not w.root} for w in res.works}
m_on, m_off = members(on), members(off)
changed = [t for t, ms in m_on.items() if ms != m_off.get(t, set()) and len(ms) > len(m_off.get(t, set()))]
gained = {t: len(m_on[t]) - len(m_off.get(t, set())) for t in changed}
print(f"# {wiki}: works grouped by the textless-TOC-root rule (WP1 item 5)\n")
print(f"Seed `wp1-textless-{wiki}`. {len(changed)} works gained members; {sum(gained.values()):,} pages attached; "
      f"works {len(off.works):,} -> {len(on.works):,}")
print("largest:", ", ".join(f"{t} (+{g})" for t, g in sorted(gained.items(), key=lambda x: -x[1])[:12]))
by = {w.root.title: w for w in on.works}
rng = random.Random(f"wp1-textless-{wiki}")
for i, t in enumerate(rng.sample(changed, min(30, len(changed))), 1):
    w = by[t]
    print(f"\n{i}. `{t}`: {w.structure}, {w.type}, {w.part_count} parts ({gained[t]} new), order {w.root.order_source}")
    new = m_on[t] - m_off.get(t, set())
    shown = 0
    for p in w.parts:
        if shown >= 12: break
        mark = " [new]" if p.node.title in new else ""
        print(f"   {'  ' * p.depth}- {p.kind}: {p.node.display or p.node.title} (`{p.node.title}`){mark}")
        shown += 1
    if len(w.parts) > 12:
        print(f"   - … {len(w.parts) - 12} more; last: `{w.parts[-1].node.title}`")
