# SPDX-License-Identifier: GPL-3.0-or-later
"""(WP1, 2026-11-01) Gzipped size of en parts_fts under the two rowid
schemes, with and without author names, on identical content rebuilt from
the catalog (report/wp1-decisions.md item 2). Writes four scratch files
next to the catalog and deletes them.

    .venv/bin/python -I pipeline/wp1/fts_rowid_size.py en
"""
import gzip, os, sqlite3, sys
from wscatalog.catalog import OUT_DIR
wiki = sys.argv[1] if len(sys.argv) > 1 else "en"
src = sqlite3.connect(f"file:{OUT_DIR / f'{wiki}.sqlite'}?mode=ro", uri=True)
rows = src.execute("""select p.work_id, p.ord, coalesce(p.display_title, replace(p.page_title, rtrim(p.page_title, replace(p.page_title,'/','')), '')),
  w.display_title, (select group_concat(a.name, ' ') from work_authors wa join authors a on a.id=wa.author_id where wa.work_id=w.id and wa.role='author')
  from parts p join works w on w.id=p.work_id where p.searchable=1 order by p.work_id, p.ord""").fetchall()
print(f"{wiki}: {len(rows):,} searchable parts")
TOK = "tokenize='unicode61 remove_diacritics 2'"
def build(name, rowid_fn, authors=True):
    path = OUT_DIR / f"_{name}.sqlite"
    path.unlink(missing_ok=True)
    con = sqlite3.connect(path)
    con.execute(f"CREATE VIRTUAL TABLE parts_fts USING fts5(display_title, work_title, author_names, content='', {TOK})")
    con.executemany("INSERT INTO parts_fts(rowid, display_title, work_title, author_names) VALUES (?,?,?,?)",
                    [(rowid_fn(i, r), r[2], r[3], (r[4] if authors else None)) for i, r in enumerate(rows, 1)])
    con.execute("INSERT INTO parts_fts(parts_fts) VALUES ('optimize')"); con.commit(); con.execute("VACUUM"); con.close()
    raw, gz = os.path.getsize(path), len(gzip.compress(path.read_bytes(), 9))
    path.unlink()
    print(f"{name:28} raw {raw / 1e6:5.1f} MB  gzipped {gz / 1e6:5.2f} MB")
build("sparse_rowid_authors", lambda i, r: r[0] * 4096 + r[1])
build("sequential_rowid_authors", lambda i, r: i)
build("sparse_rowid_noauthors", lambda i, r: r[0] * 4096 + r[1], authors=False)
build("sequential_rowid_noauthors", lambda i, r: i, authors=False)
