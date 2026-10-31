# SPDX-License-Identifier: GPL-3.0-or-later
"""(WP1, 2026-11-01) Unknown works of the wikis not covered by the WP1a
survey: breakdown and 30 pseudo-random works each, behind
report/wp1-decisions.md item 3 (show_unknown_by_default). Reads the built
catalogs in pipeline/out/.

    .venv/bin/python -I pipeline/wp1/unknown_samples.py sv da fi es mul
"""
import sqlite3, sys
from wscatalog.catalog import OUT_DIR
# A stable pseudo-random order; abs() keeps virtual roots (negative ids) from sorting first.
RANDOM = "abs((w.id * 2654435761) % 1000003)"
for w in sys.argv[1:] or ["sv", "da", "fi", "es", "mul"]:
    con = sqlite3.connect(f"file:{OUT_DIR / f'{w}.sqlite'}?mode=ro", uri=True)
    q = lambda s: con.execute(s).fetchall()
    n = q("select count(*) from works where quality='unknown'")[0][0]
    print(f"\n## {w}: {n:,} unknown works\n")
    print("- by type:", q("select type, count(*) from works where quality='unknown' group by 1 order by 2 desc"))
    print("- by structure:", q("select structure, count(*) from works where quality='unknown' group by 1 order by 2 desc"))
    print("- by genre:", q("select g.key, count(*) from works w join work_genres wg on wg.work_id=w.id "
                          "join genres g on g.id=wg.genre_id where w.quality='unknown' group by 1 order by 2 desc"))
    print("- with an author:", q("select count(distinct work_id) from work_authors a join works w on w.id=a.work_id "
                                 "where w.quality='unknown' and a.role='author'")[0][0])
    print("- bytes at the 10/25/50/75/90th percentile:",
          [q(f"select bytes_total from works where quality='unknown' order by bytes_total limit 1 offset {int(n*p)}")[0][0]
           for p in (0.1, 0.25, 0.5, 0.75, 0.9)], "; under 1,000 bytes:",
          q("select count(*) from works where quality='unknown' and bytes_total < 1000")[0][0])
    print("- virtual roots among them:", q("select count(*) from works where quality='unknown' and is_virtual=1")[0][0])
    print("\n30 pseudo-random unknown works (title | type | genre | author | bytes | parts):\n")
    for r in q(f"""select w.page_title, w.type,
        (select group_concat(g.key) from work_genres wg join genres g on g.id=wg.genre_id where wg.work_id=w.id),
        (select group_concat(a.name) from work_authors wa join authors a on a.id=wa.author_id where wa.work_id=w.id and wa.role='author'),
        w.bytes_total, w.part_count from works w where w.quality='unknown' order by {RANDOM} limit 30"""):
        print("-", " | ".join(str(x) for x in r))
