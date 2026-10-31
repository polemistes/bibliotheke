# SPDX-License-Identifier: GPL-3.0-or-later
"""(WP1, 2026-11-01) Catalog-side measurements behind report/wp1-decisions.md
items 1 (no works without an author), 4 (en documents) and 5 (collections
stored one work per page). Reads the built catalogs in pipeline/out/.

    .venv/bin/python -I pipeline/wp1/catalog_queries.py
"""
import sqlite3
from wscatalog.catalog import OUT_DIR
def con(w): return sqlite3.connect(f"file:{OUT_DIR / f'{w}.sqlite'}?mode=ro", uri=True)
RANDOM = "abs((w.id * 2654435761) % 1000003)"
DV = "quality in ('validated','proofread') and complete=1 and type!='reference'"   # the default view before item 4

no = con("no"); q = lambda s: no.execute(s).fetchall()
NA = "not exists(select 1 from work_authors a where a.work_id=w.id and a.role='author')"
print("## item 1: no works without an author\n")
print("- total:", q(f"select count(*) from works w where {NA}")[0][0])
print("- by quality:", q(f"select quality, count(*) from works w where {NA} group by 1 order by 2 desc"))
print("- by type:", q(f"select type, count(*) from works w where {NA} group by 1 order by 2 desc"))
print("- by genre:", q(f"select coalesce(g.key,'(none)'), count(*) from works w left join work_genres wg on wg.work_id=w.id "
                       f"left join genres g on g.id=wg.genre_id where {NA} group by 1 order by 2 desc"))
print("- in the default view:", q(f"select count(*) from works w where {NA} and {DV}")[0][0], "of", q(f"select count(*) from works w where {DV}")[0][0])
print("- with a translator but no author:", q(f"select count(*) from works w where {NA} and exists(select 1 from work_authors a where a.work_id=w.id and a.role='translator')")[0][0])
print("\nDefault-view works without an author (title | type | quality | virtual | index_titles):\n")
for r in q(f"select page_title, type, quality, is_virtual, index_titles from works w where {NA} and {DV} order by page_title"):
    print("-", " | ".join(str(x) for x in r))

en = con("en"); q = lambda s: en.execute(s).fetchall()
print("\n## item 4: en works typed document\n")
print("- by type source:", q("select type_source, count(*) from works where type='document' group by 1 order by 2 desc"))
print("- by quality:", q("select quality, count(*) from works where type='document' group by 1 order by 2 desc"))
print("- by genre:", q("select coalesce(g.key,'(none)'), count(*) from works w left join work_genres wg on wg.work_id=w.id "
                       "left join genres g on g.id=wg.genre_id where w.type='document' group by 1 order by 2 desc"))
print("- without a year:", q("select count(*) from works where type='document' and year is null")[0][0],
      "; by century:", q("select (year/100)+1, count(*) from works where type='document' and year is not null group by 1 order by 1"))
print("- default view (unknown shown) by type:", q("select type, count(*) from works where quality in ('validated','proofread','unknown') and complete=1 and type!='reference' group by 1 order by 2 desc"))
print("- unknown works that are not documents, by type:", q("select type, count(*) from works where quality='unknown' and type!='document' group by 1 order by 2 desc"))
print("- speeches by type:", q("select w.type, count(*) from works w join work_genres wg on wg.work_id=w.id join genres g on g.id=wg.genre_id where g.key='speech' group by 1"))
print("\n25 pseudo-random documents with a genre other than legal-government:\n")
for r in q(f"""select w.page_title, g.key, w.type_source, w.quality from works w join work_genres wg on wg.work_id=w.id join genres g on g.id=wg.genre_id
  where w.type='document' and g.key != 'legal-government' order by {RANDOM} limit 25"""): print("-", r)
print("\nNotable titles:")
for t in ["%Declaration of Independence%", "Magna Carta%", "The Federalist%", "Common Sense%", "%Second Inaugural Address%",
          "Charter of the United Nations%", "Universal Declaration of Human Rights", "Pearl Harbor%", "Constitution of the United States of America"]:
    print("-", t, en.execute("select page_title, type, quality, type_source from works where page_title like ? limit 4", (t,)).fetchall())

print("\n## item 5: collections stored one work per page\n")
de = con("de"); q = lambda s: de.execute(s).fetchall()
print("- de TBHB works:", q("select count(*), min(page_title), max(page_title), group_concat(distinct quality) from works where page_title like 'TBHB%'"))
print("- de Brass pages in the default view:", q(f"select count(*) from works w where {DV} and (page_title like 'TBHB%' or page_title like 'Hans Brass und%' or page_title like 'Brass Lektüre%')")[0][0],
      "of", q(f"select count(*) from works w where {DV}")[0][0])
print("- de works with Manesse in the title:", q("select page_title, structure, part_count, type, quality from works where page_title like '%Manesse%'"))
print("- de colon prefixes among single works (top 8):", q("select substr(page_title,1,instr(page_title,':')-1) p, count(*) from works where structure='single' and instr(page_title,':')>0 group by p order by 2 desc limit 8"))
es = con("es"); q = lambda s: es.execute(s).fetchall()
print("- es reference-genre works by structure/virtual:", q("select w.structure, w.is_virtual, count(*) from works w join work_genres wg on wg.work_id=w.id join genres g on g.id=wg.genre_id where g.key='reference' group by 1,2"))
print("- es Diccionario de Arquitectura Civil works:", q("select page_title, structure, part_count, quality, type from works where page_title like 'Diccionario de Arquitectura Civil%' order by page_title limit 8"))
print("- es colon prefixes among single works (>= 15):", q("select substr(page_title,1,instr(page_title,':')-1) p, count(*) from works where structure='single' and instr(page_title,':')>0 group by p having count(*)>=15 order by 2 desc"))
print("- es Proyecto: works:", q("select page_title from works where page_title like 'Proyecto:%'"))
sv = con("sv"); q = lambda s: sv.execute(s).fetchall()
print("- sv works with Fänrik in the title / excluded:", q("select page_title, structure, part_count from works where page_title like 'Fänrik Ståls%'"), q("select * from excluded where page_title like 'Fänrik%'"))
fi = con("fi"); q = lambda s: fi.execute(s).fetchall()
print("- fi virtual roots with one part:", q("select page_title, (select group_concat(page_title) from parts p where p.work_id=w.id) from works w where is_virtual=1 and part_count=1"))
