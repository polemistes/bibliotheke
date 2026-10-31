# SPDX-License-Identifier: GPL-3.0-or-later
"""Why rule 3 (TOC membership) does not attach de colon-prefixed member pages
to their root page; what the members and roots look like."""
from __future__ import annotations

import sys
from collections import Counter

from wscatalog import extract, structure, wikitext as wt

ext = extract.load("de")
res = structure.resolve("de", ext)
pages = ext["pages"]
nodes = res.nodes

PREFIXES = ["Topographia Hassiae:", "Topographia Circuli Burgundici:", "Schwere, Elektricität und Magnetismus:",
            "Allgemeines Deutsches Kommersbuch:", "Die Siegel der Deutschen Kaiser und Könige:",
            "Die Ursache des Einschlagens vom Blitze:", "Elektrische Kraft Hertz:",
            "Die Schedelsche Weltchronik (deutsch):", "Med. Topographie Gmuend:", "Stammbuch berümpter Fürsten, Graffen vnnd Herren Geschlechter:",
            "Eygentliche Beschreibung Aller Stände auff Erden:", "Hantzsch:", "Taufmatrikel Passau Sankt Stephan:"]


def show_rec(t, full=False):
    rec = pages.get(t)
    if rec is None:
        print(f"    {t}: NO PAGE (excluded? {res.excluded.get(t)})")
        return
    n = nodes.get(t)
    h = {k: (v[:70] + "…" if len(v) > 70 else v) for k, v in rec.hdr.items()}
    print(f"    `{t}`: {rec.bytes} B, own {rec.own_chars}, tags {len(rec.tags)}, refs {len(rec.refs)}, "
          f"links {len(rec.links)}, tpls {sorted(rec.tpls)[:6]}")
    print(f"      hdr {h}")
    if n:
        print(f"      node: parent {n.parent!r} rule {n.rule} container {n.container!r} fail {n.container_fail!r} "
              f"index {n.index!r} toc {len(n.toc)} toc_source {n.toc_source!r} prev {n.prev!r} next {n.next!r}")
    else:
        print(f"      excluded: {res.excluded.get(t)}")
    if full:
        print(f"      links[:8] {list(rec.links)[:8]}")


for pref in PREFIXES:
    members = sorted(t for t in pages if t.startswith(pref) and "/" not in t)
    root = pref[:-1]
    print(f"\n=== {pref} ({len(members)} members) root `{root}` exists={root in pages} toc_like={root in res.toc_like}")
    show_rec(root, full=True)
    # which TOC-like pages list the members?
    listers = Counter()
    for t in members:
        for n in nodes.values():
            pass
    # cheaper: pages whose links include a member
    mem = set(members)
    linkers = Counter()
    for t2, rec in pages.items():
        if t2 in mem:
            continue
        hit = sum(1 for l in rec.links if l in mem)
        if hit:
            linkers[t2] += hit
    print(f"  pages linking members: " + "; ".join(f"`{k}` {v}" for k, v in linkers.most_common(6)))
    for t in members[:2] + members[len(members) // 2: len(members) // 2 + 1]:
        show_rec(t)
    # subpages of the root (chapters?) and whether they link/transclude the members
    subs = sorted(t for t in pages if t.startswith(root + "/"))
    if subs:
        print(f"  root has {len(subs)} subpages, e.g. {subs[:3]}")
        for s in subs[:2]:
            rec = pages[s]
            hits = sum(1 for l in rec.links if l in mem)
            print(f"    `{s}`: {rec.bytes} B, own {rec.own_chars}, links to members {hits}, tpls {sorted(rec.tpls)[:5]}")
