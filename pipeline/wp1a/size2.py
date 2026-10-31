# SPDX-License-Identifier: GPL-3.0-or-later
"""Decision 2: parts table with index_title as a small integer and
display_title NULL when it equals the last title segment; with and without
suffix titles and per-part quality_pages. Raw (on-device) and gzipped sizes."""
from __future__ import annotations

import gzip
import json
import sqlite3
import sys

from wscatalog import survey

wiki = sys.argv[1]
s = survey.Survey(wiki)
cat = s.in_catalog()
sel = [w for w in cat if w.structure != "single" and w.part_count <= s.cfg.parts_cap and w.type != "reference"]


def measure(index_int=False, blank=False, suffix=False, drop_qp=False):
    con = sqlite3.connect(":memory:")
    con.execute("""CREATE TABLE parts(work_id INTEGER NOT NULL, ord INTEGER NOT NULL,
        depth INTEGER NOT NULL, parent_ord INTEGER, page_title TEXT NOT NULL,
        display_title TEXT, kind TEXT NOT NULL, searchable INTEGER NOT NULL DEFAULT 0,
        bytes INTEGER, scan_from INTEGER, scan_to INTEGER, index_ref ANY,
        quality TEXT, quality_pages TEXT, complete INTEGER, PRIMARY KEY(work_id, ord))""")
    con.execute("CREATE INDEX parts_title ON parts(page_title)")
    rows = []
    for w in sel:
        wid = w.root.rec.page_id if w.root.rec else -(abs(hash(w.root.title)) % 10**9) - 1
        idxs = list(dict.fromkeys(p.node.index for p in w.parts if p.node.index))
        pq = s.q[w.root.title][1]
        for p, q in zip(w.parts, pq):
            n = p.node
            title, display = n.title, n.display or n.title
            if suffix and title.startswith(w.root.title + "/"):
                title = title[len(w.root.title):]
            if blank and (display == n.title.rsplit("/", 1)[-1] or display == n.title):
                display = None
            qp = None if drop_qp else (json.dumps({str(k): v for k, v in q.pages.items()}) if q.pages else None)
            idx = (idxs.index(n.index) if n.index else None) if index_int else n.index
            rows.append((wid, p.ord, p.depth, p.parent_ord, title, display, p.kind, int(p.searchable),
                         s.res.node_bytes(n), n.scan_from, n.scan_to, idx, q.tier, qp, int(q.complete)))
    con.executemany("INSERT INTO parts VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)", rows)
    con.commit()
    raw = con.serialize()
    return len(rows), len(raw), len(gzip.compress(raw, 9))


print(f"# {wiki}: parts table encodings ({len(sel):,} works)\n")
print("| encoding | rows | raw SQLite (on device) | gzipped (download) |\n|---|---|---|---|")
for label, kw in [("schema as written", {}),
                  ("index_title → integer into works.index_titles", dict(index_int=True)),
                  ("+ display_title NULL when = last segment", dict(index_int=True, blank=True)),
                  ("+ no per-part quality_pages", dict(index_int=True, blank=True, drop_qp=True)),
                  ("+ page_title as suffix", dict(index_int=True, blank=True, drop_qp=True, suffix=True)),
                  ("index int + blank display + suffix (keep quality_pages)", dict(index_int=True, blank=True, suffix=True))]:
    n, raw, gz = measure(**kw)
    print(f"| {label} | {n:,} | {raw / 1e6:.1f} MB | {gz / 1e6:.2f} MB |")
