# SPDX-License-Identifier: GPL-3.0-or-later
"""de: size of a rule-3 extension that attaches bare pages (no header, no
scan, no status) listed by exactly one TOC-like page; Zeitschrift pages."""
from __future__ import annotations

from collections import Counter, defaultdict

from wscatalog import survey

s = survey.Survey("de")
res, cfg, ext = s.res, s.cfg, s.ext
pages, nodes = ext["pages"], res.nodes
headers = cfg.header_templates | frozenset(cfg.positional_headers)

def headerless(rec):
    return not (rec.tpls & headers) and not rec.hdr.get("status")

# TOC-like' = the resolver's TOC-like pages, plus root pages with a header,
# an Index (INDEXSEITE) and >= 3 listed headerless pages (de Topographia).
toc_like2 = set(res.toc_like)
for n in nodes.values():
    if n.rec and n.parent is None and "/" not in n.title and n.index and (n.rec.tpls & headers) \
            and sum(1 for t in n.toc if t in pages and headerless(pages[t])) >= 3:
        toc_like2.add(n.title)
print(f"TOC-like pages now {len(res.toc_like):,}; with the Index-root extension {len(toc_like2):,}")

# who lists whom among TOC-like' pages
listers = defaultdict(list)
for t in toc_like2:
    n = nodes.get(t)
    if n is None:
        continue
    for tgt in n.toc:
        if tgt not in res.excluded and tgt != t:
            listers[tgt].append(t)

def bare(rec):
    return headerless(rec)

own_roots = [n for n in nodes.values() if n.rec and n.parent is None and n.rule == "own" and not n.children]
cand = []
for n in own_roots:
    ls = listers.get(n.title, [])
    if len(ls) == 1:
        cand.append((n, ls[0], bare(n.rec)))
print(f"TOC-like pages: {len(res.toc_like):,}; single-page own works listed by exactly one TOC-like page: {len(cand):,}")
b = [c for c in cand if c[2]]
nb = [c for c in cand if not c[2]]
print(f"  bare (no header, scan, status): {len(b):,} → would attach; with colon in title {sum(1 for c in b if ':' in c[0].title):,}")
print(f"  not bare: {len(nb):,} (left alone)")
roots = Counter(c[1] for c in b)
print("  top TOC pages gaining members: " + "; ".join(f"`{k}` {v}" for k, v in roots.most_common(25)))
tiers = Counter(s.wq(s.part_of[c[0].title][0]).tier for c in b)
print(f"  tiers of the attached works today: {dict(tiers)}")
dv = [c for c in b if s.wq(s.part_of[c[0].title][0]).tier in ("validated", "proofread") and s.wq(s.part_of[c[0].title][0]).complete]
print(f"  of which in today's default view: {len(dv):,} (default view today 19,334 works)")
# risk check: attached pages whose TOC page is NOT the colon/slash prefix of their title
odd = [c for c in b if not (c[0].title.startswith(c[1] + ":") or c[0].title.startswith(c[1] + "/") or c[0].title.startswith(c[1] + " "))]
print(f"  attached pages whose title does not start with the TOC page's title: {len(odd):,}; TOC pages: "
      + "; ".join(f"`{k}` {v}" for k, v in Counter(c[1] for c in odd).most_common(12)))
import random as _r
for c in _r.Random(3).sample(odd, min(12, len(odd))):
    print(f"    `{c[0].title}` → `{c[1]}` ({c[0].rec.bytes} B, refs {len(c[0].rec.refs)}, tpls {sorted(c[0].rec.tpls)[:4]})")
# which not-bare singles are listed by one TOC page and share nothing (e.g. Topographia)?
nbroots = Counter(c[1] for c in nb)
print("  not-bare singles listed by one TOC-like page, top TOC pages: " + "; ".join(f"`{k}` {v}" for k, v in nbroots.most_common(15)))
topo = [c for c in nb if c[0].title.startswith("Topographia")]
if topo:
    n, t, _ = topo[0]
    print(f"  example {n.title!r}: index {n.index!r}, container {n.container!r} fail {n.container_fail!r}, hdr container {n.rec.hdr.get('container')!r}; TOC page {t!r} index {nodes[t].index!r}, hdr {dict(list(nodes[t].rec.hdr.items())[:6])}")
    ti = Counter(c[0].index for c in topo)
    print(f"  Topographia members' indexes: {ti.most_common(5)}; listed-by-one: {len(topo)}")
# all Topographia singles regardless of listers
allt = [n for n in own_roots if n.title.startswith("Topographia") and ":" in n.title]
lc = Counter(len(listers.get(n.title, [])) for n in allt)
print(f"  all Topographia colon singles: {len(allt)}; listed by k TOC-like pages: {dict(lc)}")
ex = [n for n in allt if len(listers.get(n.title, [])) >= 2][:3]
for n in ex:
    print(f"    {n.title!r} listed by {listers[n.title]}")

# Zeitschrift pages
z = [w for w in s.in_catalog() if w.root.rec and "zeitschrift" in {t.lower() for t in w.root.rec.tpls} and not (w.root.rec.tpls & (headers - {"zeitschrift"}))]
zs = [w for w in z if w.structure == "single" and not w.root.rec.tags and not w.root.rec.refs]
print(f"\nWorks whose root header is only {{Zeitschrift}}: {len(z):,}; single, without scans: {len(zs):,}; "
      f"tiers {dict(Counter(s.wq(w).tier for w in zs))}; multi-part or scanned: {len(z) - len(zs):,} "
      f"(tiers {dict(Counter(s.wq(w).tier for w in z if w not in zs))})")
unk = [w for w in s.in_catalog() if s.wq(w).tier == "unknown"]
att = {c[0].title for c in b}
rest = [w for w in unk if w.root.title not in att and w not in zs]
print(f"Unknown works now {len(unk):,}; after attaching bare listed pages ({sum(1 for w in unk if w.root.title in att):,}) "
      f"and excluding Zeitschrift bibliographies ({sum(1 for w in unk if w in zs):,}): {len(rest):,}")
hdr = Counter(", ".join(sorted(w.root.rec.tpls & headers)) or "(none)" for w in rest if w.root.rec)
print(f"  remaining unknown by header: {hdr.most_common(6)}")
import random
rng = random.Random(1)
for w in rng.sample(rest, min(20, len(rest))):
    print(f"  - `{w.root.title}` ({w.structure}, {w.bytes_total} B): {w.root.rec.snippet[:100] if w.root.rec else ''}")
