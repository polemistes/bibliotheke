# SPDX-License-Identifier: GPL-3.0-or-later
"""(WP1, 2026-11-01) Extract-side measurements behind report/wp1-decisions.md
items 1 (Index authors of the no works without an author) and 5 (how the
ungrouped collections are linked on de, es and sv). Reads the cached
extracts and, for item 1, the no catalog in pipeline/out/.

    .venv/bin/python -I pipeline/wp1/extract_queries.py no|de|es|sv
"""
import collections, re, sqlite3, sys
from wscatalog import extract
from wscatalog.catalog import OUT_DIR
wiki = sys.argv[1]
d = extract.load(wiki); pages, redirects, indexes = d["pages"], d["redirects"], d["indexes"]
def show(t):
    p = pages.get(t)
    if p is None:
        print(f"- `{t}`: missing (redirect to {redirects.get(t)})"); return
    print(f"- `{t}`: hdr {p.hdr}; templates {sorted(p.tpls)[:8]}; categories {p.cats[:6]}; "
          f"own chars {p.own_chars}; links {p.links[:6]}{' …' if len(p.links) > 6 else ''}; scans {bool(p.tags or p.refs)}")

if wiki == "no":
    con = sqlite3.connect(f"file:{OUT_DIR / 'no.sqlite'}?mode=ro", uri=True)
    rows = con.execute("select page_title, type, quality, index_titles, is_virtual from works w where "
                       "not exists(select 1 from work_authors a where a.work_id=w.id and a.role='author') and "
                       "quality in ('validated','proofread') and complete=1 and type not in ('reference','document') "
                       "order by page_title").fetchall()
    print(f"## no: the {len(rows)} default-view works without an author, with their Index pages' author field\n")
    for t, ty, qu, idx, virt in rows:
        ind = [(i, (indexes.get(i) or {}).get("forfatter"), (indexes.get(i) or {}).get("type")) for i in (eval(idx) if idx else [])]
        p = pages.get(t)
        print(f"- `{t}` | {ty} | {qu} | virtual {virt} | header {p.hdr if p else None} | templates "
              f"{sorted(p.tpls) if p else None} | Index author {ind}")
elif wiki == "de":
    brass = [t for t, p in pages.items() if "Tagebuch von Hans Brass" in p.cats]
    man = [t for t, p in pages.items() if "Codex Manesse" in p.cats]
    print("## de\n")
    print(f"- category Tagebuch von Hans Brass: {len(brass)} pages, {sum(t.startswith('TBHB') for t in brass)} with the TBHB prefix")
    show("TBHB 1945-05-05"); show("Tagebuch von Hans Brass"); show("Hans Brass")
    print(f"- category Codex Manesse: {len(man)} pages; by (container, status):",
          collections.Counter((pages[t].hdr.get("container", "")[:32], pages[t].hdr.get("status")) for t in man).most_common(8))
    for t in man[:3]: show(t)
    show("Codex Manesse")
elif wiki == "es":
    c = collections.Counter(m.group(1) for t in pages for m in [re.search(r"\(([A-Z]{2,5})\)$", t)] if m)
    print("## es\n")
    print("- title suffixes (ABBR):", c.most_common(10))
    cc = collections.Counter(cat for p in pages.values() for cat in p.cats
                             if cat.startswith("Diccionario") and not (len(cat) == 13 and cat[-2] == " "))
    print("- categories Diccionario… (not the letter categories):", cc.most_common(8))
    for t in ["Árbol (DAC)", "Diccionario de Arquitectura Civil", "Diccionario de Arquitectura Civil: A",
              "Lujo/1 (DFV)", "Fe (DCB)", "Almagesto", "Almagesto: Libro II - Capítulo 06", "Proyecto:Literatura"]:
        show(t)
elif wiki == "sv":
    print("## sv\n")
    roots = []
    for t, p in pages.items():
        if "/" in t or p.own_chars >= 50 or p.tags or p.refs:
            continue
        links = [redirects.get(l, l) for l in p.links if l in pages or l in redirects]
        if len(links) >= 3:
            chained = sum(1 for l in links if l in pages and (pages[l].hdr.get("prev") or pages[l].hdr.get("next")))
            roots.append((t, len(links), chained, p.hdr.get("author")))
    print(f"- root pages with no text of their own that link >= 3 existing pages: {len(roots)}; "
          f"linked pages {sum(r[1] for r in roots)}, of which with a prev/next chain {sum(r[2] for r in roots)}")
    for r in sorted(roots, key=lambda x: -x[1])[:25]:
        print("  -", r)
    for t in ["Fänrik Ståls sägner", "Vårt land", "Fänrik Stål"]:
        show(t)
