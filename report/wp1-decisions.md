# WP1: the owner's decisions of 2026-11-01 (after the first build)

The first WP1 build (catalogs of the 11 configured wikis from the dumps of
2026-10-01) left six items for the owner (STATUS.md of 2026-11-01,
"Decisions for the owner"). Each was measured on the built catalogs and the
cached extracts before deciding; the measurements are in the appendix, the
scripts in `pipeline/wp1/`. The owner accepted the recommendations as
written on 2026-11-01. The changes are implemented in `pipeline/wscatalog/`
and the configs, the catalogs rebuilt, and PLAN.md carries the amended
passages marked "(WP1, 2026-11-01 …)". After the changes all 19 acceptance
checks pass (`python -I -m wscatalog.acceptance no de en`).

## Decisions

1. **The no author target counts the works of the default view.** 203 of
   the 1,069 no works have no author; 166 of them are outside the default
   view anyway (102 unproofread, 64 unknown). The 26 that were in it are
   anonymous by nature: five Eddic poems (*Voluspaa*, *Hymeskvæde*,
   *Hyndleljod*, *Rigs-tula*, *Allvismaal*), four court decisions
   (`…-OTIR`), the 1814 constitution, a 1933 law, two periodical issues,
   a court procedure, the Nordens report, and two works whose Index says
   "Anonym". Two are pipeline misses, *Nordlands Trompet* and *Stjerneskud
   paa Ekeberg*: their Index pages name the author (Petter Dass, Rudolf
   Muus) but the work is not that Index's book, and a rule taking the
   Index author in that case would misattribute anthologies such as *Den
   norske turistforenings årbog*. Two virtual roots have an empty author
   field on their Index page (a wiki fix). The acceptance check
   (`acceptance.py`, `chk_author`) now passes on the default-view share and
   reports both figures: 95.3 % of 425 works in the default view, 81.0 %
   of all works. PLAN.md section 9 is amended.

2. **`parts_fts.rowid` is `parts.id`; the author names stay.** The table
   was rebuilt from the en catalog with identical content (199,166 rows)
   under both rowid schemes:

   | rowid | with author names | without |
   |---|---|---|
   | `work_id * 4096 + ord` | 4.36 MB | 4.07 MB |
   | sequential 1..n | 3.12 MB | 2.95 MB |

   The sparse rowid cost 1.2 MB gzipped, the author names 0.2 MB (STATUS.md
   had the split as 1.0 and 0.6). `parts` now has `id INTEGER PRIMARY KEY`
   and `UNIQUE(work_id, ord)`: the same two b-trees as before (a rowid
   table and the composite autoindex), and VACUUM never renumbers an
   explicit integer primary key, which removes the caveat on the
   `parts.rowid` option. The app joins `parts` on `id`; the `meta` key
   `parts_rowid_stride` is gone. The acceptance measurement now creates
   the copied tables with their own schema (CREATE TABLE AS stored the
   integer key twice): en parts 17.3 MB gzipped with both indexes,
   `parts_fts` 3.2 MB, 20.5 MB together, en catalog 36.2 → 35.1 MB.

3. **`show_unknown_by_default`: sv, da, fi, es, mul `true`; it `false`;
   derived for the generic config.** 30 pseudo-random unknown works per
   wiki (appendix) are real texts throughout: sv hymns and poems by Wallin,
   Frykman, Runeberg, Tegnér, Bellman, Snoilsky, Topelius; da hymns by
   Grundtvig and Ingemann, Andersen's *Sneedronningen*, Levetzow's stories;
   fi poems and newspaper pieces by Aho, Topelius, Manninen, Kaatra; es
   Calderón, Altamirano's *El Zarco*, Pereda, Manrique, López Velarde,
   decrees and speeches; mul poems in Kurdish, Punjabi, Chuvash, Mingrelian,
   Uzbek, Ladin and Friulian and laws in Mongolian and Burmese. About three
   quarters of the unknown works carry an author on sv, da, fi and mul (es
   47 %). Junk in the samples: two stubs of 30 on sv and on mul, none on da,
   fi, es. With the flag false the picker would show fi 34, da 112, mul
   774, sv 2,281, es 2,282 works, the "hard cut empties the small wikis"
   case of PLAN.md 5.4. it has no unknown works, so its flag is moot. The
   es unknown set carried about 1,950 dictionary entries and six
   `Proyecto:` pages, handled under item 5. A wiki whose config leaves the
   flag unset (the generic config, about 70 wikis) now derives it in
   `catalog.Build`: `true` when unknown works outnumber the validated and
   proofread ones. The rule reproduces ten of the eleven explicit
   decisions (no is the deliberate exception) and keeps proofreading-heavy
   wikis on `false` without a survey; `meta` and the manifest record
   `show_unknown_source` (`config` or `derived`). Picker counts after the
   change: sv 6,420, da 875, fi 13,130, es 12,591, mul 28,138.

4. **The app's default type filter hides `document`.** On en 29,118 works
   are typed `document` (28,437 by licence and case templates), 21,462 of
   them unknown, 13,309 without a year; genres legal-government 26,086 and
   speech 3,029. They were 54 % of en's default view. The default view
   (PLAN.md 5.4, `catalog.default_view`, the reports, the acceptance
   checks and the picker count) now excludes `type IN (reference,
   document)`, with an "Include documents" chip in the app (WP2). Hidden
   on the first rebuild: en 28,009 (default view 50,924 → 22,907), de 4,571
   (14,732 → 10,140), es 2,616, it 544, fi 130, da 73, no 67. What goes
   behind the chip: executive orders, proclamations, Supreme Court
   decisions and UN resolutions, but also presidential addresses, the UN
   Charter, the Universal Declaration of Human Rights, the Dunlap Broadside
   and the US Constitution; speeches are documents by the taxonomy
   (`genre_types` speech → document) and by the PD-USGov template. If the
   app should keep speeches visible, its filter can be "document and genre
   not speech" (2,989 en works); left to WP2. Side effect: en's unknown set
   shrinks to 12,096 non-document works.

5. **Collections still stored one work per page: four cases.**
   - *es dictionary entries*: about 2,280 root pages carry suffixes such as
     `(DAC)` (1,576), `(SLC)`, `(CM)`, `(DFV)`, `(MAQ)`, `(DEIE)`, `(DCB)`,
     and 1,918 single works already had genre `reference` from the
     Diccionario categories. `metadata.genre_types` in default.yaml maps
     reference → `reference`, so they are typed `reference` and leave the
     default view (es hides 1,916) while staying searchable as works.
     Grouping them properly needs the de rule-3 extension generalised: the
     entries are headerless and listed by exactly one letter page, but the
     letter pages (`Diccionario de Arquitectura Civil: A`) have a header
     and no Index, so they are not TOC-like today; *Almagesto* (155
     chapter pages under a headerless root) has the same shape. Deferred
     (PLAN.md section 13). The six `Proyecto:` pages in ns0 are excluded
     by prefix.
   - *sv root-page collections*: 23 TOC-only roots were excluded as
     `no-text` (*Fänrik Ståls sägner*, *Frithiofs saga*, *Gluntarne*, *Nya
     dikter*, *Dikter (Löwenhjelm)*), linking 463 single works, 259 of them
     with a prev/next chain. sv's `Titel` template names the page itself as
     container, and the roots have no marker or Index, so rule 3 never
     fired. New rule behind `structure.textless_toc_roots` (sv only): a
     root page with no text of its own that links at least three existing
     pages is TOC-like; membership still needs the chain or a shared Index.
     Result: 15 collections, 204 texts, sv works 6,761 → 6,580; all 15
     checked by eye in `report/sv-textless-toc-sample.md` (right members,
     chain or TOC order, poems as texts and numbered chapters as chapters);
     two groupings are partial (*Dikter (Löwenhjelm)* 9 of 86 links, *Där
     ute. Skisser.* 1 of 12) because the unchained members have no shared
     Index, which the rule leaves alone by design.
   - *de Tagebuch von Hans Brass*: 3,913 pages in the category (3,489
     `TBHB …`), each with its own one-page Index, `HERKUNFT = off` and no
     root page in ns0 (the author page lists them). No rule of 5.2 can
     reach them; grouping needs category- or config-defined virtual
     collections and, at 3,913 parts, category listing in the app to
     resolve the work live. 1,932 of them were in de's default view
     (13 %). Deferred, ticketed in PLAN.md section 13.
   - *Codex Manesse*: the 2,818 songs name non-existent folio pages
     (`Codex Manesse 31v`) or a Heidi template as container, and each has
     its own poet and status. They stay separate poems on purpose: grouped
     into one 2,818-part work over `parts_cap` they would leave author
     browsing and offline search.

6. **The de rule-3 re-sample (25 of 30) is accepted.** The 27-of-30 bar
   was set for the WP1a patterns A, B, D and E; the re-sample checks the
   extension, and what the extension does, the grouping, is right in all
   30. The five misses are ordering and classification defects of kinds
   already listed in STATUS.md (natural order for *Annalen der Physik*,
   editorial links before the TOC, numbered town entries as chapters,
   volume placement). Two follow-ups are ticketed in PLAN.md section 13:
   TOC order from the TOC section only when the root has one, and
   numbered town entries not read as chapters.

Also found while measuring, not decided: fi has seven virtual roots with a
single leaf child, created from titles with slashed dates (`… Tampereella
22/XI 05`), ticketed; the no root *Nordlands Trompet* carries the cleanup
template and stays in the catalog because the junk rules apply to typed-in
works only (it is scan-backed and proofread), which is as designed.

## Changes

- `pipeline/wscatalog/catalog.py`: `parts.id`, `parts_fts.rowid = parts.id`,
  `HIDDEN_TYPES`, the derived `show_unknown` and `show_unknown_source` in
  `meta` and the manifest; `report.py`: the default-view section names the
  flag's source and the works hidden by type; `acceptance.py`: author
  target over the default view, `DEFAULT_VIEW` without documents, no
  figures 425 works / 2,300 texts, schema-true size measurement;
  `structure.py` and `config.py`: `textless_toc_roots`.
- Configs: `show_unknown_by_default` sv, da, fi, es, mul `true`, it `false`;
  default.yaml `genre_types` reference → reference, the derived-flag
  comment, `textless_toc_roots: false` (sv `true`); es
  `exclude_title_prefixes: ["Proyecto:"]`.
- `pipeline/tests/test_catalog.py` follows the schema; `pipeline/wp1/`
  holds the measurement scripts and the sv sample script.
- PLAN.md 5.2 steps 1b, 3 and 8, 5.3, 5.4, 5.5, WP1 acceptance, WP2,
  section 13; `docs/work-structure-plan.md` section 12; STATUS.md.

## Appendix: measurements behind the decisions

Run on 2026-11-01 against the catalogs of the first build (before the
changes above) and the cached extracts of the 2026-10-01 dumps; the scripts
in `pipeline/wp1/` reproduce them (their paths and names differ from the
scratch versions that produced this output; the queries are the same,
except that `extract_queries.py sv` counts every textless sv root with
three or more links, 44 roots, 658 links, 387 chained, where the scratch
version counted only the 23 roots the first build had excluded as
`no-text`).
Python tuples and lists are printed as they came.

### Item 1: no works without an author (catalog_queries.py, before the changes)

```
no works without author: [(203,)]
by quality: [('unproofread', 102), ('unknown', 64), ('proofread', 34), ('validated', 3)]
by type: [('other', 114), ('document', 28), ('poem', 25), ('book', 18), ('collection', 10), ('periodical-issue', 8)]
by genre: [('folklore', 64), ('(none)', 60), ('legal-government', 28), ('poetry', 18), ('periodical', 10), ('history', 9), ('song-hymn', 7), ('religious', 2), ('other', 2), ('travel', 1), ('short-story', 1), ('reference', 1), ('novel', 1), ('memoir-biography', 1), ('essay', 1)]
default view without author: [(26,)]
default view (with unknown shown) without author: [(88,)]
--- default view works without author:
   ('10-099305-MED-OTIR', 'document', 'proofread')
   ('11-007057ENE-OTIR', 'document', 'validated')
   ('11-098785MED-OTIR', 'document', 'proofread')
   ('11-119207ENE-OTIR', 'document', 'validated')
   ('Allvismaal', 'poem', 'validated')
   ('Beretning om den danske, den norske og den svenske forening «Nordens» virksomhed i 1921', 'book', 'proofread')
   ('Den Norske Huusven', 'collection', 'proofread')
   ('En bestigelse af Galdhöpiggen', 'other', 'proofread')
   ('Exempler paa tidlig Gudsfrygt hos Børn', 'other', 'proofread')
   ('Henrik Wergeland (Janson)', 'poem', 'proofread')
   ('Hymeskvæde', 'poem', 'proofread')
   ('Hyndleljod', 'poem', 'proofread')
   ('K. M. A. Kvartalshilsen, Vol. 1. Nr. 2.', 'periodical-issue', 'proofread')
   ('Keilhaus opdagelse af Jotunhejmen', 'other', 'proofread')
   ('Kongeriget Norges Grundlov af 17de Mai 1814', 'document', 'proofread')
   ('Lov om reindriften av 12. mai 1933', 'document', 'proofread')
   ('Morgenbladet/1840/306', 'other', 'proofread')
   ('Nogle Erindringer om Professor P. A. Munch', 'other', 'proofread')
   ('Nordlands Trompet', 'book', 'proofread')
   ('Realitets-Procedure og Dom i Rigsretssagen imod Hs. Excellence Statsminister m. m. Løvenskiold', 'other', 'proofread')
   ('Rigs-tula', 'poem', 'proofread')
   ('Stjerneskud paa Ekeberg', 'book', 'proofread')
   ('Topografisk-statistisk beskrivelse over Nedenes amt', 'book', 'proofread')
   ('Voluspaa', 'poem', 'proofread')
   ('Vor nasjonale Skam', 'book', 'proofread')
   ('Østers-Kulturen', 'book', 'proofread')
--- with a translator but no author: [(17,)]
--- 20 random no-author works outside default view:
   ('09-043651TVI-OTIR', 'document', 'unknown', 1)
   ('08-156741TVI-OTIR', 'document', 'unknown', 1)
   ('05-130602TVI-OTIR', 'document', 'unknown', 1)
   ('Urd', 'collection', 'unknown', 1)
   ('08-073828PRI-OTIR', 'document', 'unknown', 1)
   ('Topografisk-statistisk beskrivelse over Bratsberg amt', 'book', 'proofread', 0)
   ('Topografisk-statistisk beskrivelse over Finmarkens amt', 'book', 'proofread', 0)
   ('Ho Stutarkari', 'other', 'unproofread', 1)
   ('Han Andris Spelemann', 'other', 'unproofread', 1)
   ('Om Mag. Niels Svendsen Krønikes Stridigheder med Præsteskabet i Christiania i Aarene 1642–1652', 'other', 'unknown', 1)
   ('Lappecodicillen', 'document', 'unknown', 1)
   ('Store-Stime', 'other', 'unproofread', 1)
   ('Raadmændene i de norske Kjøbstæder i Middelalderen', 'other', 'unproofread', 1)
   ('Venstres valgprogram fra 1891', 'document', 'unproofread', 1)
   ('Stasbakkjen', 'other', 'unproofread', 1)
   ('Der vester mellom skjær og bå', 'other', 'unknown', 1)
   ('Rabben aa Kjos’en', 'other', 'unproofread', 1)
   ('Loketrætta', 'poem', 'unproofread', 1)
   ('Minnesamvær om polarfareren Oskar Omdahl', 'other', 'unknown', 1)
   ('Krististølen', 'other', 'unproofread', 1)
```

### Item 1: the 26 default-view works, with their Index pages' author field (extract_queries.py no)

```
- 10-099305-MED-OTIR | document | proofread | virtual=1 | hdr=None | tpls=None | index=[('10-099305-MED-OTIR,04.djvu', None, 'book')]
- 11-007057ENE-OTIR | document | validated | virtual=1 | hdr=None | tpls=None | index=[('Maria Amelie.djvu', None, 'phdthesis')]
- 11-098785MED-OTIR | document | proofread | virtual=1 | hdr=None | tpls=None | index=[('Dom FFF.djvu', None, 'book')]
- 11-119207ENE-OTIR | document | validated | virtual=1 | hdr=None | tpls=None | index=[('11-119207ENE-OTIR~03.djvu', None, 'book'), ('Beslutning om uniform.djvu', None, 'book'), ('Kjennelse - lukking av dører.djvu', None, 'book')]
- Allvismaal | poem | validated | virtual=0 | hdr={} | tpls=['PD-old'] | index=[('Edda-kvæde, Gudekvæde.djvu', None, 'book')]
- Beretning om den danske, den norske og den svenske forening «Nordens» virksomhed i 1921 | book | proofread | virtual=0 | hdr={} | tpls=[] | index=[('Norden - Volume 1.djvu', None, 'book')]
- Den Norske Huusven | collection | proofread | virtual=0 | hdr={} | tpls=['Tidsskrift'] | index=[('Den Norske Huusven 1827 Vol 1 nr 2.pdf', '[[Forfatter:Maurits Hansen|Maurits Hansen]]', 'journal'), ('Den Norske Huusven 1827 Vol 1 nr 3.pdf', '[[Forfatter:Maurits Hansen|Maurits Hansen]]', 'journal')]
- En bestigelse af Galdhöpiggen | other | proofread | virtual=0 | hdr={} | tpls=['PD-old'] | index=[('Den norske turistforenings årbog for 1872.djvu', None, 'book')]
- Exempler paa tidlig Gudsfrygt hos Børn | other | proofread | virtual=0 | hdr={} | tpls=[] | index=[('Exempler paa tidlig Gudsfrygt hos Børn.djvu', None, 'book')]
- Henrik Wergeland (Janson) | poem | proofread | virtual=0 | hdr={} | tpls=['PD-old'] | index=[('Vor nasjonale Skam.djvu', 'Anonym', 'book')]
- Hymeskvæde | poem | proofread | virtual=0 | hdr={} | tpls=['PD-old'] | index=[('Edda-kvæde, Gudekvæde.djvu', None, 'book')]
- Hyndleljod | poem | proofread | virtual=0 | hdr={} | tpls=['PD-old'] | index=[('Edda-kvæde, Gudekvæde.djvu', None, 'book')]
- K. M. A. Kvartalshilsen, Vol. 1. Nr. 2. | periodical-issue | proofread | virtual=0 | hdr={} | tpls=['PD-old'] | index=[('Kvartalshilsen (Kvinnelige misjonsarbeidere). 1908 Vol. 1 Nr. 2.pdf', None, 'journal')]
- Keilhaus opdagelse af Jotunhejmen | other | proofread | virtual=0 | hdr={} | tpls=['PD-old'] | index=[('Den norske turistforenings årbog for 1872.djvu', None, 'book')]
- Kongeriget Norges Grundlov af 17de Mai 1814 | document | proofread | virtual=0 | hdr={} | tpls=['PD-Regjeringa'] | index=[('Kongeriget Norges Grundlov 17 V 1814.djvu', None, 'book')]
- Lov om reindriften av 12. mai 1933 | document | proofread | virtual=0 | hdr={} | tpls=[] | index=[('Lov om reindriften av 12. mai 1933.pdf', None, 'book')]
- Morgenbladet/1840/306 | other | proofread | virtual=0 | hdr={} | tpls=['PD-old'] | index=[('Morgenbladet 1840 306.djvu', None, 'journal')]
- Nogle Erindringer om Professor P. A. Munch | other | proofread | virtual=0 | hdr={} | tpls=['PD-old'] | index=[('Historiske Skrifter tilegnede Ludvig Daae.djvu', None, 'book')]
- Nordlands Trompet | book | proofread | virtual=0 | hdr={} | tpls=['Opprydning'] | index=[('Dass - Samlede Skrifter 1.djvu', '[[Forfatter:Petter Dass|Petter Dass]]', 'book')]
- Realitets-Procedure og Dom i Rigsretssagen imod Hs. Excellence Statsminister m. m. Løvenskiold | other | proofread | virtual=0 | hdr={} | tpls=[] | index=[('Realitets-Procedure og Dom i Rigsretssagen imod Hs. Excellence Statsminister m.m. Løvenskiold.djvu', None, None)]
- Rigs-tula | poem | proofread | virtual=0 | hdr={} | tpls=['PD-old'] | index=[('Edda-kvæde, Gudekvæde.djvu', None, 'book')]
- Stjerneskud paa Ekeberg | book | proofread | virtual=0 | hdr={} | tpls=['Forfatterl', 'PD-old', 'Tittel/e', 'Tittel/s'] | index=[('Muus Blandt Kristiania-Vampyrer.pdf', '[[Forfatter:Rudolf Muus|Rudolf Muus]]', 'book'), ('Muus I hovedstadens malmstrøm.pdf', '[[Forfatter:Rudolf Muus|Rudolf Muus]]', 'book'), ('Muus Paa jagt efter Blenda Svanholm.pdf', '[[Forfatter:Rudolf Muus|Rudolf Muus]]', 'book'), ('Muus Nemesis.pdf', '[[Forfatter:Rudolf Muus|Rudolf Muus]]', 'book')]
- Topografisk-statistisk beskrivelse over Nedenes amt | book | proofread | virtual=1 | hdr=None | tpls=None | index=[('Norges land og folk - Nedenes amt 1.djvu', None, 'book')]
- Voluspaa | poem | proofread | virtual=0 | hdr={} | tpls=['PD-old'] | index=[('Edda-kvæde, Gudekvæde.djvu', None, 'book')]
- Vor nasjonale Skam | book | proofread | virtual=0 | hdr={} | tpls=['PD-old'] | index=[('Vor nasjonale Skam.djvu', 'Anonym', 'book')]
- Østers-Kulturen | book | proofread | virtual=1 | hdr=None | tpls=None | index=[('Østers-kulturen.djvu', None, 'book')]
```

### Item 2: en parts_fts under the two rowid schemes (fts_rowid_size.py)

```
en parts_fts rebuilt from the 2026-11-01 catalog with identical content (199,166 rows), optimize + VACUUM, gzip -6:
sparse_rowid_authors         raw  14.4 MB  gzipped  4.36 MB   (rowid = work_id*4096+ord, author_names filled; the catalog itself: 4.8 MB)
sequential_rowid_authors     raw  12.8 MB  gzipped  3.12 MB
sparse_rowid_noauthors       raw  12.1 MB  gzipped  4.07 MB
sequential_rowid_noauthors   raw  10.5 MB  gzipped  2.95 MB   (what the WP1a survey measured)
=> the sparse rowid costs 1.24 MB, author_names 0.17 MB. Script: q/ftsrowid.py
```

### Item 3: unknown works of sv, da, fi, es, mul with 30 pseudo-random works each (unknown_samples.py)

```

===== sv
unknown virtual roots: [(8,)]  all virtual: [(52,)]
unknown single works with author / without: [(0, 1033), (1, 3243)]
--- 30 random unknown works, unbiased (title | type | genre | author | bytes | parts | virtual)
   Stilla natt (Mannström) | poem | song-hymn | Joseph Mohr | 1477 | 1 | 0
   O tysta ensamhet | poem | song-hymn | Olof von Dalin | 956 | 1 | 0
   O Gud, min Gud, jag prisar dig | poem | song-hymn | Nils Frykman | 1249 | 1 | 0
   Lovsjungen Herren, låtom oss med fröjdeljud | poem | song-hymn | Johan Olof Wallin | 1281 | 1 | 0
   Flyttfåglarna (Runeberg) | poem | poetry | Johan Ludvig Runeberg | 1547 | 1 | 0
   Brunnsbad i Medevi 1706 | other | None | None | 335 | 1 | 0
   En kvinna gick en gång | poem | song-hymn | Carl Lundgren | 1160 | 1 | 0
   Ångbåtssång | poem | poetry,song-hymn | Otto Lindblad | 781 | 1 | 0
   Visa om förr och nu | poem | poetry | Dan Andersson | 1267 | 1 | 0
   Titanics undergång | poem | song-hymn | Nils Lindström | 2425 | 1 | 0
   De gräsliga våldsdåden i Kongo | other | periodical | None | 443 | 1 | 0
   Gud är mitt allt | poem | song-hymn | None | 847 | 1 | 0
   Tidningsmeddelande om öppnandet av Finska Senatsarkivet för forskare (1859) | other | None | None | 3419 | 1 | 0
   Kommen till mig | poem | song-hymn | None | 528 | 1 | 0
   Djursholm | poem | poetry | Carl Snoilsky | 2940 | 1 | 0
   Stillhet | poem | poetry | Karl Alfred Melin | 693 | 1 | 0
   Grekwännen | poem | poetry | Assar Lindeblad | 2174 | 1 | 0
   Skål i Götiska förbundet | poem | poetry | Esaias Tegnér | 1208 | 1 | 0
   Amen, lov, ära, nu sjunga med fröjd | poem | song-hymn | None | 487 | 1 | 0
   Jag blyges ej vid Jesus | poem | song-hymn | None | 859 | 1 | 0
   Svenske anamma utländska seder | poem | song-hymn | None | 813 | 1 | 0
   Det brister en sträng här nere | poem | song-hymn | Anna Ölander | 690 | 1 | 0
   Regeringsförklaringen 22 mars 1996 | other | None | None | 12549 | 1 | 0
   Ordenshärolder, ta'n edra spiror | poem | poetry,song-hymn | Carl Michael Bellman | 2172 | 1 | 0
   Ännu en gång: Konunga- och höfdingastyrelsen | other | None | None | 9054 | 1 | 0
   O, se ej till vinet | poem | song-hymn | None | 816 | 1 | 0
   Solen haver sig ifrån oss vänt | poem | song-hymn | Johannes Rist | 1661 | 1 | 0
   Sparven om julmorgonen | poem | poetry,song-hymn | Zacharias Topelius | 1218 | 1 | 0
   Längs Rhen | other | None | Vincent Stoltenberg Lerche | 27896 | 1 | 0
   Dödssång (Correspondenten) | poem | poetry | Elias Sehlstedt | 1367 | 1 | 0

===== da
unknown virtual roots: [(1,)]  all virtual: [(4,)]
unknown single works with author / without: [(0, 185), (1, 638)]
--- 30 random unknown works, unbiased (title | type | genre | author | bytes | parts | virtual)
   At samles, skilles ad | poem | song-hymn | None | 1089 | 1 | 0
   Sprogenes Bog | other | None | None | 7912 | 1 | 0
   Den lille bi, den store sol | poem | song-hymn | Christian Richardt | 812 | 1 | 0
   Er Hierte, Aand og Tanker | other | None | None | 868 | 1 | 0
   Nu dagen gik bort | other | None | None | 578 | 1 | 0
   Skitser af Hverdagslivet | other | short-story | Cornelia Levetzow | 1107 | 1 | 0
   Til Danmark | poem | song-hymn | Hans Vilhelm Kaalund | 4861 | 1 | 0
   Folkebevægelsen mod EU m.fl. mod Statsminister Poul Nyrup Rasmussen: Højesterets beslutning af 12. oktober 2000 (Kæretilladelse) | document | legal-government | Højesteret | 1977 | 1 | 0
   Jesus, som iblandt os står | poem | song-hymn | Johan Nordahl Brun | 936 | 1 | 0
   Jeg seer paa Jesu Kinder | other | None | None | 1928 | 1 | 0
   Flittners Gynæologi | other | None | None | 28215 | 1 | 0
   Der står en lind i min faders gård | poem | song-hymn | None | 1803 | 1 | 0
   Storken sidder på bondens tag | poem | song-hymn | B.S. Ingemann | 1171 | 1 | 0
   Den blinde Digter | other | short-story | Cornelia Levetzow | 65570 | 1 | 0
   Du, som går ud fra den levende Gud | poem | song-hymn | 1837 N.F.S. Grundtvig | 2076 | 1 | 0
   Mester Jacob | other | None | None | 3169 | 1 | 0
   Til min Marie | poem | song-hymn | N.F.S. Grundtvig | 1112 | 1 | 0
   Grindavísan | poem | poetry | Christian Pløyen | 7637 | 1 | 0
   Du spørger, min dreng | poem | song-hymn | Andreas Melchior Glückstad | 1539 | 1 | 0
   Afskedsbrev Marius Fiil | other | None | None | 1602 | 1 | 0
   Kimer, I klokker | poem | song-hymn | 1856 N.F.S. Grundtvig | 1101 | 1 | 0
   Er lyset for de lærde blot | other | None | N.F.S Grundtvig | 1753 | 1 | 0
   Stjernen og Lygtemanden | poem | poetry | Hans Vilhelm Kaalund | 2763 | 1 | 0
   Mine Hændelser | other | None | Sigvard Lyche | 99004 | 1 | 0
   Smeden og Bageren | poem | poetry | Johan Herman Wessel | 2748 | 1 | 0
   Sejle op ad åen | poem | song-hymn | Laurits Steffen Pedersen | 2001 | 1 | 0
   Og det var om en løverdag (Tyge Hermansøn) | poem | song-hymn | N.F.S. Grundtvig | 2671 | 1 | 0
   Broder rus | other | short-story | ubekendt | 25900 | 1 | 0
   Sneedronningen | other | folklore | Hans Christian Andersen | 63119 | 1 | 0
   Se dig ud en sommerdag | poem | song-hymn | Jeppe Aakjær | 1643 | 1 | 0

===== fi
unknown virtual roots: [(8,)]  all virtual: [(8,)]
unknown single works with author / without: [(0, 3107), (1, 10082)]
--- 30 random unknown works, unbiased (title | type | genre | author | bytes | parts | virtual)
   Kyytilaki | other | None | None | 29407 | 1 | 0
   Kansakoulut ja yleinen järjestys | other | None | None | 791 | 1 | 0
   Ystävykset (Hellén) | other | None | Immi Hellén | 2086 | 1 | 0
   Toivoton (1901) | poem | poetry | None | 972 | 1 | 0
   Linnut | poem | poetry | Erik Johan Stagnelius | 1753 | 1 | 0
   Kruunaus (Juva) | other | None | Heinrich Heine | 1167 | 1 | 0
   Ragazin mielipuolet | poem | poetry | Carl Snoilsky | 1410 | 1 | 0
   Elan kysymyksiä | other | short-story | Fredrika Runeberg | 3384 | 1 | 0
   Isänmaaton | poem | poetry | None | 747 | 1 | 0
   Vuohensilmä | other | None | Esa Paavo-Kallio | 484 | 1 | 0
   Rauhaa! | poem | poetry | None | 2033 | 1 | 0
   Purjeet sumussa (runo) | other | None | Unto Koskela | 599 | 1 | 0
   Terve, Suomen Suur-voguli! | other | None | Otto Manninen | 1001 | 1 | 0
   Ukkonen | poem | poetry | None | 2114 | 1 | 0
   Työ (Luoto) | other | None | Kaarlo Luoto | 603 | 1 | 0
   Suomi | poem | poetry | Nikolai Kari | 1993 | 1 | 0
   Maanviljelysoppi - Esipuhe | other | None | None | 741 | 1 | 0
   Kytö-Heikki | other | None | Simo Korpela | 2065 | 1 | 0
   Hellmannin herra | other | None | Juhani Aho | 639 | 1 | 0
   Karjala apua huutaa! | other | None | Lauri Soini | 820 | 1 | 0
   Suomen herääminen | poem | poetry | B. F. Godenhjelm | 1407 | 1 | 0
   Puut | other | None | Esaias Tegnér | 1738 | 1 | 0
   Valitus (Noailles) | other | None | Anna de Noailles | 1550 | 1 | 0
   Varjo | other | None | Zacharias Topelius | 3779 | 1 | 0
   Valtioneuvoston automobiilit | other | None | None | 1244 | 1 | 0
   Ennen ja nyt (Soini) | other | None | Lauri Soini | 1022 | 1 | 0
   Työhön! | other | None | Kössi Kaatra | 1223 | 1 | 0
   Varomatonta ammuskelemista | other | None | None | 544 | 1 | 0
   Mökin laittaja | other | None | P. J. Hannikainen | 877 | 1 | 0
   Filosofian bibliografia | other | None | None | 104123 | 1 | 0

===== es
unknown virtual roots: [(32,)]  all virtual: [(49,)]
unknown single works with author / without: [(0, 7804), (1, 6073)]
--- 30 random unknown works, unbiased (title | type | genre | author | bytes | parts | virtual)
   Discurso de la embajadora de Guinea Ecuatorial en EEUU con motivo del 39 aniversario de la independencia | other | history | None | 6879 | 1 | 0
   El Uruguay - Población e historia | article | essay,history | Octavio Velasco del Real | 20173 | 1 | 0
   Tratado de la tribulación | other | None | Vicente de la Fuente | 1273 | 1 | 0
   La barca del pasado | poem | poetry | Andrés Eloy Blanco | 1710 | 1 | 0
   Melancolía, madre mía | poem | poetry | Arturo Borja | 1118 | 1 | 0
   Los milagros de la Argentina | collection | None | Godofredo Daireaux | 335490 | 31 | 0
   El retrato (Reyes) | other | short-story | Arturo Reyes | 13520 | 1 | 0
   No hay burlas con el amor | book | drama | Pedro Calderón de la Barca | 142124 | 3 | 0
   Manifiesto de Francisco Villa al Pueblo de México (1914-09-22) | other | None | None | 11213 | 1 | 0
   Árbol (DAC) | other | reference | None | 523 | 1 | 0
   Decreto del 26 de julio de 1861 | document | legal-government | None | 854 | 1 | 0
   La verdadera gloria | poem | poetry | Julio Matovelle | 4131 | 1 | 0
   El piano de genoveva | poem | poetry | Ramón López Velarde | 1522 | 1 | 0
   Barandal (DAC) | other | reference | None | 445 | 1 | 0
   Sentencia del TOF 3 dictada en el caso del atentado contra la AMIA el 29 de octubre de 2004 (73) | other | None | None | 9415 | 1 | 0
   Tan mala noche me distes | other | None | None | 386 | 1 | 0
   Blancos, Blancos | other | None | Práxedis G. Guerrero | 2067 | 1 | 0
   A la traición de una hermosa | poem | poetry | Ramón López Velarde | 853 | 1 | 0
   Estatuto General del Estado Vasco aprobado en la Magna Asamblea de Municipios Vascos celebrada en Estella el día 14 de Junio de 1931 | other | None | None | 41280 | 1 | 0
   Almas honradas | other | short-story | Arturo Reyes | 9316 | 1 | 0
   El Zarco | book | novel | Ignacio Manuel Altamirano | 310891 | 25 | 0
   Discurso del Diputado Pedro José Peñaloza de la fracción parlamentaria del Partido Revolucionario de los Trabajadores PRT respecto al derrumbe del Edificio Nuevo León de la Unidad Habitacional Tlatelolco a consecuencia del Sismo de 1985 | document | speech | None | 13939 | 1 | 0
   Porque estando él durmiendo le besó su amiga | poem | poetry | Jorge Manrique | 907 | 1 | 0
   Las cañadas | other | short-story | Juan José Morosoli | 2030 | 1 | 0
   La Fe del Amor, Capítulos III-IV | other | None | None | 17743 | 1 | 0
   Himno del Cesar | poem | song-hymn | None | 1126 | 1 | 0
   Escritos de juventud | collection | None | José María de Pereda | 505692 | 60 | 0
   El banquillo | poem | poetry | Olegario Víctor Andrade | 4471 | 1 | 0
   Ley Orgánica de Servicio Público de Ecuador | document | legal-government | None | 178739 | 1 | 0
   Almagesto: Libro II - Capítulo 06 | other | None | None | 46310 | 1 | 0

===== mul
unknown virtual roots: [(66,)]  all virtual: [(88,)]
unknown single works with author / without: [(0, 6186), (1, 20802)]
--- 30 random unknown works, unbiased (title | type | genre | author | bytes | parts | virtual)
   ئیبتیدا بە نام پادشای جەلیل | other | None | حیلمی کاکەیی | 2613 | 1 | 0
   ڈٹھا میں رخسار سوہنے دا | other | None | سچل سرمست | 620 | 1 | 0
   Вăран, хура халăх! | other | None | Турхан Энтри | 1954 | 1 | 0
   Чаа чыл эрттирериниң дугайнда чугаа | other | None | None | 1674 | 1 | 0
   عیللەتی کابووسی فیراقی حەبیب | other | None | سابیری | 900 | 1 | 0
   Funeralstelae of Maaty and Dedari | other | None | None | 4688 | 1 | 0
   Уарунгор (Багъæраты) | other | None | Багъæраты Созыр | 1092 | 1 | 0
   دەوری زەمانە وەها گۆڕاوە | other | None | پیرەمێرد | 1625 | 1 | 0
   Ciantia dal möt vedl | other | None | Jan Willeit | 1453 | 1 | 0
   Монгол Улсын Оюуны өмчийн тухай хууль | other | None | None | 41116 | 1 | 0
   Გინალასალი ზღვადოგვალა | other | None | ემზარი პაპავა | 1043 | 1 | 0
   Amalning olimi | other | None | Abdulhamid Choʻlpon | 1417 | 1 | 0
   ئەو پیاوە گەورەی خۆی بە کەم ئەگرێ | other | None | پیرەمێرد | 1581 | 1 | 0
   Bagoñ dünya emesdir | other | None | None | 305 | 1 | 0
   Сидзæр лæппуйы фыс æмæ бирæгътæ (Гæдиаты) | other | None | Гæдиаты Секъа | 1310 | 1 | 0
   لە درزی پەچەوە | other | None | گۆران | 1959 | 1 | 0
   State Anthem of the Soviet Union | poem | song-hymn | None | 18940 | 1 | 0
   Თაკილი | other | None | რამაზ კუპრა | 1077 | 1 | 0
   Te Ariki Hei Au Koe Noho Ai | other | None | Henry Lyte (1793–1847) | 960 | 1 | 0
   Jurij Libš | other | None | None | 414 | 1 | 0
   Og’riganda | other | None | Abdulhamid Choʻlpon | 1057 | 1 | 0
   Მაფშალიაშ ღურჭულს უჯგუ | other | None | კაკა ჟვანია | 962 | 1 | 0
   Se al mio partì un solo suspir dal pet | other | None | Ermes di Colloredo | 953 | 1 | 0
   Ons Heemecht (leagan Gaeilge) | other | None | Michel Lentz | 1220 | 1 | 0
   Ogahiy g‘azaliga muxammas | other | None | Devoniy | 2059 | 1 | 0
   بخش لِکھنے والیاں جُملیاں نُوں | other | None | وارث شاہ | 796 | 1 | 0
   Fig‘onkim, charx bedodi bila ko‘nglum parishondur... | other | None | Nodim Namangoniy | 1651 | 1 | 0
   کنگن نال زنجیریاں پنج منیاں | other | None | وارث شاہ | 987 | 1 | 0
   Ul mahvashi cholok surub man sori ot... | other | None | Avaz Oʻtar | 454 | 1 | 0
   Салауат батыр(йыр). Башҡорт халыҡ ижады | other | children | Башҡорт халыҡ ижады | 1253 | 1 | 0
```

### Item 4: en works typed document (catalog_queries.py)

```
document by type_source: [('template', 28437), ('genre', 577), ('wikidata', 72), ('index', 32)]
document by quality: [('unknown', 21462), ('proofread', 5196), ('validated', 1672), ('unproofread', 788)]
document by genre: [('legal-government', 26086), ('speech', 3029), ('letters', 79), ('science', 20), ('periodical', 13), ('song-hymn', 9), ('religious', 8), ('reference', 5), ('philosophy', 5), ('memoir-biography', 5), ('history', 5), ('travel', 1), ('poetry', 1), ('essay', 1)]
document by century: [(None, 13309), (1, 1), (13, 16), (14, 8), (15, 4), (16, 15), (17, 38), (18, 207), (19, 1016), (20, 6964), (21, 7540)]
default view (val/proofread/unknown, complete, not reference) by type: [('document', 28009), ('other', 11013), ('book', 8129), ('poem', 2077), ('collection', 1070), ('article', 292), ('periodical-issue', 205), ('play', 129)]
default view without document:  [(22915,)]
unknown non-document: [(12096,)]
unknown non-document by type: [('other', 7592), ('poem', 1876), ('book', 1250), ('collection', 1003), ('periodical-issue', 180), ('article', 158), ('play', 34), ('reference', 3)]
--- documents with a non-legal genre, 25 pseudo-random:
   ('V-safe Pregnancy Registry Protocol', 'science', 'template', 'proofread')
   ('Presidential Radio Address - 22 March 1986', 'speech', 'template', 'unknown')
   ('Ether and the Theory of Relativity', 'speech', 'genre', 'unknown')
   ('Peace, Pluralism and Polarization: A Quarterly Reflection - 30th August 2007', 'speech', 'genre', 'unknown')
   ("Joseph Jenkins Roberts' Inaugural Address (1848)", 'speech', 'genre', 'unknown')
   ('Presidential Radio Address - 16 March 2002', 'speech', 'template', 'unknown')
   ('Presidential Radio Address - 24 April 1999', 'speech', 'template', 'unknown')
   ('Presidential Weekly Address - 25 August 2012', 'speech', 'template', 'unknown')
   ('Presidential Radio Address - 18 September 1993', 'speech', 'template', 'unknown')
   ("Princess Elizabeth's 21st Birthday Speech", 'speech', 'template', 'unknown')
   ('Presidential Radio Address - 14 October 2000', 'speech', 'template', 'unknown')
   ('Pearl Harbor speech', 'speech', 'template', 'unknown')
   ('Presidential Radio Address - 16 May 1998', 'speech', 'template', 'unknown')
   ("Harry S. Truman's Inaugural Address", 'speech', 'template', 'unknown')
   ('Arguments Against a War in Iraq', 'speech', 'template', 'unproofread')
   ('Presidential Radio Address - 1 February 1986', 'speech', 'template', 'unknown')
   ("Roosevelt's Fireside Chat, 28 April 1942", 'speech', 'template', 'unknown')
   ('Presidential Weekly Address - 25 February 2017', 'speech', 'template', 'unknown')
   ('Presidential Radio Address - 25 September 2004', 'speech', 'template', 'unknown')
   ('Presidential Radio Address - 26 January 2002', 'speech', 'template', 'unknown')
   ('Presidential Radio Address - 25 December 2001', 'speech', 'template', 'unknown')
   ('Presidential Weekly Address - 4 July 2015', 'speech', 'template', 'unknown')
   ('From Peril to Progress', 'speech', 'template', 'unknown')
   ('Message to Scientology', 'speech', 'genre', 'unknown')
   ('Presidential Radio Address - 6 March 1999', 'speech', 'template', 'unknown')
--- documents without genre, 20 pseudo-random:
--- 20 pseudo-random unknown non-document works:
   ('Portrait and Biographical Record of Suffolk County (Long Island) New York', 'collection', 2493)
   ('Our First Century', 'collection', 23429)
   ("The Busy Man's Magazine", 'collection', 15798)
   ('Scarsdale Inquirer', 'collection', 3348)
   ('American Magazine', 'collection', 30277)
   ('Napa Valley Register', 'collection', 1983)
   ('Moberly Weekly Monitor', 'collection', 1005)
   ('The Trenton Times', 'collection', 1677)
   ("Kate Field's Washington", 'book', 1109)
   ('The Poems of John Donne', 'collection', 744)
   ('St. Petersburg Times', 'collection', 2217)
   ('The House of Ptolemy', 'book', 136324)
   ('Modern Essays of Various Types', 'collection', 8217)
   ('United States of America v. Omar Ahmed Khadr (2007)', 'collection', 67775)
   ('Smart Set (White)', 'collection', 16491)
   ('Ottumwa Tri-Weekly Courier', 'collection', 859)
   ('Ironwood Daily Globe', 'collection', 1991)
   ('The Los Angeles Times', 'collection', 1446)
   ('The Des Moines Tribune', 'collection', 4500)
   ('COUNCIL DECISION 2014/145', 'collection', 22416)
```

### Items 4 and 5: notable en titles; de, es, fi, mul and sv catalog queries

```
=== en notable titles
   %Declaration of Independence% [('Declaration of Independence (Israel)', 'document', 'unknown', 'template'), ('United States Declaration of Independence (Dunlap Broadside)', 'document', 'validated', 'template'), ('Texas Declaration of Independence', 'other', 'unknown', 'default'), ('Irish Declaration of Independence', 'other', 'unknown', 'default')] (22,)
   Magna Carta% [('Magna Carta (trans. Davis)', 'other', 'unknown', 'default')] (1,)
   The Federalist% [('The Federalist (Ford)', 'periodical-issue', 'proofread', 'wikidata'), ('The Federalist (Dawson)', 'periodical-issue', 'validated', 'wikidata'), ('The Federalist (Gideon)', 'book', 'unproofread', 'index')] (3,)
   Gettysburg Address% [] (0,)
   Emancipation Proclamation% [] (0,)
   Common Sense% [('Common Sense (1776)', 'book', 'validated', 'index'), ('Common Sense (newspaper)', 'periodical-issue', 'unproofread', 'wikidata')] (2,)
   %Second Inaugural Address% [("Thomas Jefferson's Second Inaugural Address", 'document', 'unknown', 'template'), ("Abraham Lincoln's Second Inaugural Address", 'document', 'validated', 'template'), ("Bill Clinton's Second Inaugural Address", 'document', 'unknown', 'template'), ("Ronald Reagan's Second Inaugural Address", 'document', 'unknown', 'template')] (21,)
   Charter of the United Nations% [('Charter of the United Nations and Statute of the International Court of Justice', 'document', 'validated', 'template')] (1,)
   %Communist Manifesto% [] (0,)
   Pearl Harbor% [('Pearl Harbor speech', 'document', 'unknown', 'template')] (1,)
   State of the Union% [] (0,)
en document works with no year: (13309,) non-document without year: (4269,) of (26869,)
en non-document works with an author: (23070,)
en speeches by type: [('article', 2), ('book', 56), ('collection', 8), ('document', 3029)]
en default view (val/proofread/unknown complete, not ref) with genre speech, document type:  (2989,)
=== de Hans Brass / Manesse
excluded Brass: [('Hans Brass', 'author-page')]
excluded Manesse: [('Codex Manesse (Thema)', 'excluded-template')]
works Manesse: [('Under der linden (Codex Manesse)', 'single', 1, 0, 'poem', 'validated'), ('Neuzeitliches Register (Codex Manesse)', 'single', 1, 0, 'other', 'proofread'), ('Codex Manesse', 'single', 1, 0, 'poem', 'proofread')]
aliases Manesse: []
parts Manesse: [(154208, 34, 0, None, 'Die Manesse’sche Sammlung, ein französisches Raubstück', None, 'text', 1, 13968, None, None, None, 'validated', '{"4":1}', 1)]
=== es reference-genre works (40):
   ('Árbol (DAC)', 'single', 0, 1, 0, 'unknown', 'other')
   ('Barandal (DAC)', 'single', 0, 1, 0, 'unknown', 'other')
   ('Relación, Analogía (SLC)', 'single', 0, 1, 0, 'unknown', 'other')
   ('Torta (DAC)', 'single', 0, 1, 0, 'unknown', 'other')
   ('Escalera de caracol y ojo (DAC)', 'single', 0, 1, 0, 'unknown', 'other')
   ('Ristrel (DAC)', 'single', 0, 1, 0, 'unknown', 'other')
   ('Obra (DAC)', 'single', 0, 1, 0, 'unknown', 'other')
   ('Equino (DAC)', 'single', 0, 1, 0, 'unknown', 'other')
   ('Caja (DAC)', 'single', 0, 1, 0, 'unknown', 'other')
   ('Antis (DAC)', 'single', 0, 1, 0, 'unknown', 'other')
   ('Fe (DCB)', 'single', 0, 1, 0, 'unknown', 'other')
   ('Balcón (DAC)', 'single', 0, 1, 0, 'unknown', 'other')
   ('Restos, Escombros, Ruinas (SLC)', 'single', 0, 1, 0, 'unknown', 'other')
   ('Tores (DAC)', 'single', 0, 1, 0, 'unknown', 'other')
   ('Istriado (DAC)', 'single', 0, 1, 0, 'unknown', 'other')
   ('Empelechador (DAC)', 'single', 0, 1, 0, 'unknown', 'other')
   ('Rezumarse (DAC)', 'single', 0, 1, 0, 'unknown', 'other')
   ('Numen (DAC)', 'single', 0, 1, 0, 'unknown', 'other')
   ('Canto (DAC)', 'single', 0, 1, 0, 'unknown', 'other')
   ('Antecoro (DAC)', 'single', 0, 1, 0, 'unknown', 'other')
   ('Cristianismo (DCB)', 'single', 0, 1, 0, 'unknown', 'other')
   ('Azogar (DAC)', 'single', 0, 1, 0, 'unknown', 'other')
   ('Desierto, Deshabitado, Solitario (SLC)', 'single', 0, 1, 0, 'unknown', 'other')
   ('Tizón (DAC)', 'single', 0, 1, 0, 'unknown', 'other')
   ('Imoscapo (DAC)', 'single', 0, 1, 0, 'unknown', 'other')
   ('Arrasado (DAC)', 'single', 0, 1, 0, 'unknown', 'other')
   ('Desapuntalado - Desapuntalar (DAC)', 'single', 0, 1, 0, 'unknown', 'other')
   ('Nicho (DAC)', 'single', 0, 1, 0, 'unknown', 'other')
   ('Grapa (DAC)', 'single', 0, 1, 0, 'unknown', 'other')
   ('Entrecolumnio (DAC)', 'single', 0, 1, 0, 'unknown', 'other')
   ('Cangrejo (DAC)', 'single', 0, 1, 0, 'unknown', 'other')
   ('Andamiada (DAC)', 'single', 0, 1, 0, 'unknown', 'other')
   ('Atributos (DAC)', 'single', 0, 1, 0, 'unknown', 'other')
   ('Castidad, Continencia (SLC)', 'single', 0, 1, 0, 'unknown', 'other')
   ('Tirada (DAC)', 'single', 0, 1, 0, 'unknown', 'other')
   ('Arista (DAC)', 'single', 0, 1, 0, 'unknown', 'other')
   ('Crasicie (DAC)', 'single', 0, 1, 0, 'unknown', 'other')
   ('Retrete (DAC)', 'single', 0, 1, 0, 'unknown', 'other')
   ('Mútulos (DAC)', 'single', 0, 1, 0, 'unknown', 'other')
   ('Gradecillas (DAC)', 'single', 0, 1, 0, 'unknown', 'other')
es reference-genre by structure/virtual: [('linked', 0, 5), ('mixed', 0, 1), ('single', 0, 1918), ('subpages', 0, 13), ('virtual', 1, 17)]
es parts of 'Lujo': [(0, 'Lujo/1 (DFV)', 'text', 1), (1, 'Lujo/2 (DFV)', 'text', 1)]
es Proyecto: works: (6,) [('Proyecto:Diccionarios, enciclopedias y manuales',), ('Proyecto:Documentos históricos',), ('Proyecto:Legislación sobre derechos de autor',), ('Proyecto:Literatura',), ('Proyecto:Paleta de colores',)]
es oversized: (59,)
=== fi odd titles: [(-270702707, '15 12–13', 'virtual', 1, '15 12–13/IV 97', 1), (-280926826, 'Puoluepäivien lopettajaisista Tampereella 22', 'virtual', 1, 'Puoluepäivien lopettajaisista Tampereella 22/XI 05', 1), (-221268301, 'Romeo ja Julija', 'virtual', 1, 'Romeo ja Julija/Näytelmän henkilöt', 1), (-834254088, 'Toiminta koulussa', 'virtual', 1, 'Toiminta koulussa/oppilaitoksessa kriisien käsittelemiseksi', 1)]
fi parts of '15 12–13': [(0, '15 12–13/IV 97', 'text')]
fi virtual roots with 1 part: (7,) [('Toiminta koulussa', 'Toiminta koulussa/oppilaitoksessa kriisien käsittelemiseksi'), ('Savokarjalaisten Vuosijuhlassa 18 28', 'Savokarjalaisten Vuosijuhlassa 18 28/II 81'), ('Juhlaruno Vaasan Laulu- ja Soittojuhlassa 18 18–21', 'Juhlaruno Vaasan Laulu- ja Soittojuhlassa 18 18–21/VI. 94'), ('Puoluepäivien lopettajaisista Tampereella 22', 'Puoluepäivien lopettajaisista Tampereella 22/XI 05'), ('15 12–13', '15 12–13/IV 97'), ('Elias Lönnrot’in juhlassa Haminassa 18 17', 'Elias Lönnrot’in juhlassa Haminassa 18 17/IV 82'), ('Romeo ja Julija', 'Romeo ja Julija/Näytelmän henkilöt')]
=== mul virtual roots: (88,) [('Historie o těžkých protivenstvích církve české', 1, None), ('禮記', 3, '禮記/大學 證釋本'), ('KGD', 1, None), ('Ancient Greek', 1, None), ('Crimine e punition', 1, None), ('Ban', 443, 'Ban/Aji Kreket'), ('Velký Olomouc', 1, None), ('L Crime de l Padre Amaro', 4, 'L Crime de l Padre Amaro/II'), ('Uz', 10, 'Uz/Dodim eshit'), ('Karaj Awazy', 1, None), ('Kodeks dyplomatyczny klasztoru tynieckiego', 1, None), ('မွန်ရာဇဝင်', 11, 'မွန်ရာဇဝင်/နိဒါန်း')]
mul 'Main Page' / portal-like works: (3,)
=== sv virtual/collection with 1 part: (31,) [('Septemberlyran', 'virtual', 1, 1, 1219), ('Hufvudstadsbladet', 'virtual', 1, 1, 433), ('Svenska folk-visor från forntiden', 'virtual', 1, 1, 5849), ('Swenske och Lappeske ABC', 'virtual', 1, 1, 18358), ('Fornvännen', 'virtual', 1, 1, 31263), ('Samlade skrifter af Zacharias Topelius', 'virtual', 1, 1, 660), ('Sveriges Gamla Lagar - Band I/Glossarium öfver äldre Svenska', 'virtual', 1, 1, 731), ('Läsning i blandade ämnen', 'virtual', 1, 1, 40867), ('SSGL7', 'virtual', 1, 1, 1399), ('Björneborgarnas marsch', 'linked', 0, 1, 14330)]
```

### Item 5: the ungrouped collections in the catalogs

```
=== de TBHB
[(3489, 'TBHB 1934-08', 'TBHB 1959-04-04', 49520771, 'validated,proofread,unproofread', 'single')]
[('TBHB 1945-05-05', 'single', 1, 'validated', 'other'), ('TBHB 1945-01-24', 'single', 1, 'validated', 'other'), ('TBHB 1951-01-02', 'single', 1, 'proofread', 'other'), ('TBHB 1950-06-23', 'single', 1, 'validated', 'other'), ('TBHB 1935-08', 'single', 1, 'proofread', 'other')]
root candidates: [('Hans Brass und die Religion, August 1934', 'single', 1, 'proofread', 'other'), ('Hans Brass und die Religion, 1934', 'single', 1, 'proofread', 'other'), ('Hans Brass und die Frauen, Januar 1935', 'single', 1, 'proofread', 'other'), ('Hans Brass und die Bunte Stube', 'single', 1, 'proofread', 'other'), ('Hans Brass und seine Träume', 'single', 1, 'proofread', 'other'), ('Hans Brass und die Religion, September 1934', 'single', 1, 'proofread', 'other'), ('Hans Brass und die Religion, Oktober 1934', 'single', 1, 'proofread', 'other'), ('Hans Brass und die Religion, November 1934', 'single', 1, 'proofread', 'other'), ('Hans Brass und die Religion, Dezember 1934', 'single', 1,  …
=== de Codex Manesse
[(1, 'single', 'proofread', 'poem')]
[('Codex Manesse', 'single', 1, 'proofread', 'poem')]
manesse folio roots: [('Codex Manesse', 'single', 1, 0)]
manesse in excluded: [('Codex Manesse (Thema)', 'excluded-template')]
=== es dictionaries
[(6, 'unknown', 'linked,single')]
[('Diccionario de Arquitectura Civil - E', 'single', 1, 'unknown', 'other'), ('Diccionario de Arquitectura Civil - A', 'single', 1, 'unknown', 'other'), ('Diccionario de Arquitectura Civil - C', 'single', 1, 'unknown', 'other'), ('Diccionario de Arquitectura Civil', 'linked', 24, 'unknown', 'collection'), ('Diccionario de Arquitectura Civil - D', 'single', 1, 'unknown', 'other')]
es reference-genre works: [(1954, 'unproofread,unknown,proofread,validated', 'linked,subpages,single,mixed,virtual')]
es reference-genre prefixes: []
es reference-genre without slash: [('', 1950), ('Sinónimos de la Lengua Castellana', 1), ('Lima', 1), ('Juntas', 1), ('Diccionario de Educación, Instrucción y Enseñanza', 1)]
es Diccionario root pages: [('Diccionario histórico geográfico, biográfico y bibliográfico de la provincia de Ciudad Real (1890)', 'subpages', 1, 1, 'unknown', 'collection'), ('Diccionario de autoridades', 'linked', 15, 13, 'unproofread', 'collection'), ('Diccionario Filosófico', 'linked', 1, 0, 'proofread', 'book'), ('Diccionario de Arquitectura Civil', 'linked', 24, 23, 'unknown', 'collection'), ('Diccionario crítico-burlesco', 'linked', 2, 2, 'unknown', 'collection'), ('Diccionario razonado (DCB)', 'single', 1, 0, 'unknown', 'other'), ('Diccionario, Vocabulario (SLC)', 'single', 1, 0, 'unknown', 'other'), ('Diccionario de Arquitectura Civil - A', 'single', 1, 0, 'unknown', 'other'), ('Dic …
es excluded Diccionario: [('Diccionario Geográfico de Chile', 'disambiguation'), ('Diccionario histórico geográfico, biográfico y bibliográfico de la provincia de Ciudad Real', 'versions'), ('Diccionario de Arquitectura Civil: Índice adicional', 'no-text'), ('Diccionario crítico-burlesco: Índice', 'no-text'), ('Diccionario Filosófico - Tomo IX: Índice', 'no-text')]
=== sv Fänrik
[]
sv excluded Fänrik: [('Fänrik Ståls sägner', 'no-text')]
sv single-page unknown works whose title has a slash (subpage-like): [(73,)]
sv slash prefixes among single works: [('Koral1697', 73), ('Lagfarenhets-Bibliothek del 5 avdelning 1', 29), ('Talismanen', 28), ('Sherlock Holmes äventyr del 4', 1), ('Sherlock Holmes äventyr del 3', 1), ('På tigh förtröstar jagh', 1), ('Min Gudh ', 1), ('Handbook', 1)]
=== es slash prefixes among single works: [('Correo de Comercio: 24 de marzo de 1810', 7), ('Correo de Comercio: 12 de mayo de 1810', 7), ('Correo de Comercio: 14 de abril de 1810', 6), ('Correo de Comercio: 5 de mayo de 1810', 5), ('Correo de Comercio: 19 de mayo de 1810', 5), ('Correo de Comercio: 17 de marzo de 1810', 5), ('Correo de Comercio: 10 de marzo de 1810', 5), ('Correo de Comercio: 31 de marzo de 1810', 4), ('Correo de Comercio: 28 de abril de 1810', 4), ('Correo de Comercio: 21 de abril de 1810', 4), ('Decreto 2', 3), ('Decreto 1', 3), ('Correo de Comercio: 7 de abril de 1810', 3), ('Correo de Comercio: 3 de marzo de 1810', 3), ('Correo de Comercio: 26 de mayo de 1810', 3)]
=== de slash prefixes among single works: [('Allerlei Kurzweil (Die Gartenlaube 1887', 16), ('Kleine Bilder aus der Gegenwart', 15), ('Kleiner Briefkasten (Die Gartenlaube 1888', 12), ('Kleiner Briefkasten (Die Gartenlaube 1876', 12), ('Kleiner Briefkasten (Die Gartenlaube 1863', 11), ('Kleiner Briefkasten (Die Gartenlaube 1875', 7), ('Besoldungsgesetz ', 6), ('Schach (Die Gartenlaube 1887', 5), ('Gesetz, betreffend die Feststellung eines Nachtrags zum Reichshaushalts-Etat für das Etatsjahr 1897', 5), ('Bekanntmachung des Textes der Unfallversicherungsgesetze vom 30. Juni 1900 ', 5), ('Zusatzvertrag zum Handels- und Schiffahrtsvertrage zwischen Deutschland und Rußland (1904) ', 4), ('Kleiner …
=== de colon prefixes among single works: [('Pomologische Monatshefte', 123), ('Die Siegel der Deutschen Kaiser und Könige', 59), ('Ausführliche Vorschriften zur Blitz-Ableitung an allerley Gebäuden', 45), ('Ersch-Gruber', 30), ('Schrift und Schrifttum', 27), ('Die deutschen Kaiser', 9), ('Topographia Circuli Burgundici', 6), ('Reales Staats- Zeitungs- und Conversations-Lexicon (1719)', 6), ('Neumanns Orts-Lexikon des Deutschen Reichs 1894', 6), ('Topographia Franconiae', 5), ('Vollständiges Staats-, Post- und Zeitungslexikon von Sachsen', 4), ('Topographia Bohemiae, Moraviae et Silesiae', 4), ('Gesetz, betreffend die Feststellung eines Nachtrags zum Reichshaushalts-Etat für das Etatsjahr 18 …
```

### Item 5: de categories Tagebuch von Hans Brass and Codex Manesse (extract_queries.py de)

```
cat 'Tagebuch von Hans Brass': 3913 ; with TBHB prefix: 3489 ; sample: ['Thim-Sieberg', 'Nächtliche Straße', '17. Juni 1953', 'Ahrenshoop, 1936', 'Ahrenshoop, 1937', 'Ahrenshoop, 1938']
cat 'Codex Manesse': 2818 ; sample: ['Under der linden (Codex Manesse)', 'Swes muot ze fröiden sî gestalt', 'Vf dem berge vnd in dem tal', 'Jch gruͤſſe mit geſange die ſvͤſſen', 'Ich gruͤsse mit gesange die svͤssen', 'Mir ſint dú rich vn̄ dú lant vndertan', 'Mir sint dú rich vnd dú lant vndertan', 'Sit das ich si so gar herzeclichen minne']
Manesse songs by (container[:40], status), top 10: [(('Codex Manesse 31v', 'korrigiert'), 26), (('Codex Manesse 31r', 'korrigiert'), 24), (('Codex Manesse 6v', 'korrigiert'), 15), (('Codex Manesse 32r', 'korrigiert'), 15), (('{{Heidi|cpg848|0290}} 147v', 'korrigiert'), 14), (('{{Heidi|cpg848|0356}} 180v', 'korrigiert'), 14), (('{{Heidi|cpg848|0280}} 142v', 'korrigiert'), 13), (('Codex Manesse 14r', 'korrigiert'), 12), (('Codex Manesse 18v', 'korrigiert'), 12), (('Codex Manesse 27v', 'korrigiert'), 12)]
   Under der linden (Codex Manesse) {'container': 'off', 'current': 'Under der linden', 'status': 'fertig', 'author': '[[Walther von der Vogelweide]]', 'year': 'um 1200', 'toc_marker': '1'} ['Heidi', 'References', 'Textdaten']
   Swes muot ze fröiden sî gestalt {'container': 'Deutsche Liederdichter des 13. Jahrhunderts', 'current': 'Swes muot ze fröiden sî gestalt', 'status': 'fertig', 'author': '[[Friedrich von Leiningen|Friderich von Liningen]]', 'year': '1978', 'description': 'Mittelhochdeutsches Lied'} ['BlockSatzEnd', 'BlockSatzStart', 'Idt', 'Right', 'Seite', 'Textdaten', 'Zeile']
   Vf dem berge vnd in dem tal {'container': '{{Heidi|cpg848|0553}} 279r', 'current': 'Vf dem berge vnd in dem tal', 'status': 'fertig', 'author': '[[Neidhart]]', 'year': '13. Jahrhundert'} ['Heidi', 'SORTIERUNG:Vf dem berge vnd in dem tal', 'Seite', 'Textdaten']
```

### Item 5: es dictionary pages (extract_queries.py es)

```
== Diccionario de Arquitectura Civil: A
    title = 'Diccionario de Arquitectura Civil: A'
    page_id = 52677
    bytes = 6985
    hdr = {'container': '[[Diccionario de Arquitectura Civil]]', 'author': 'Benito Bails'}
    tags = []
    refs = []
    links = ('Ábaco (DAC)', 'Abanzamiento (DAC)', 'Aboquillado (DAC)', 'Abrazadera (DAC)', 'Acanalado (DAC)', 'Accesorias (DAC)', 'Acepillado (DAC)', 'Acepillar (DAC)', 'Acera (DAC)', 'Acicalar (DAC)', 'Acodalar (DAC)', 'Acodillado (DAC)', 'Acrótera (DAC)', 'Acústica (DAC)', 'Adaraxas (DAC)', 'Adobe (DAC)', 'Afestonado (DAC)', 'Agallas (DAC)', 'Aguada (DAC)', 'Aguas (DAC)', 'Aguaviasto (DAC)', 'Aguilón (DAC)', 'Aguja (DAC)', 'Aislado (DAC)', 'Ajuntar (DAC)', 'Ala (DAC)', 'Alacena (DAC)', 'Alaveo (DAC)', 'Al
    texts = ('Ábaco', 'Abanzamiento', 'Aboquillado', 'Abrazadera', 'Acanalado', 'Accesorios', 'Acepillado', 'Acepillar', 'Acera', 'Acicalar', 'Acodalar', 'Acodillado', 'Acrótera', 'Acústica', 'Adaraxas', 'Adobe', 'Afestonado', 'Agallas', 'Aguada', 'Aguas', 'Aguaviasto', 'Aguilón', 'Aguja', 'Aislado', 'Ajuntar', 'Ala', 'Alacena', 'Alaveo', 'Albañil', 'Albardilla', 'Albura', 'Alcantarilla', 'Aldavía', 'Alero', 'Alfeizar', 'Aljibe', 'Alineación', 'Alma', 'Almazarrón', 'Almendras', 'Almohadillados', 'Almohadón'
    cats = ('Subpáginas',)
    tpls = frozenset({'C', 'Encabezado'})
    own_chars = 454
    flags = 0
    snippet = 'Índice completo de la letra A = border="2" cellpadding="4" cellspacing="0" style="margin: 1em 1em 1em 1em; background: #fdf5e6; font-size: 100%;" width="98%" style="width: 33%"| style="width: 33%"| st'
    rev = 1197469
    ts = '2021-10-02T19:35:50Z'
== Almagesto
    title = 'Almagesto'
    page_id = 195103
    bytes = 32701
    hdr = {}
    tags = []
    refs = []
    links = ('Almagesto: Prefacio', 'Almagesto: Introducción', 'Almagesto: Libro I - Capítulo 01', 'Almagesto: Libro I - Capítulo 02', 'Almagesto: Libro I - Capítulo 03', 'Almagesto: Libro I - Capítulo 04', 'Almagesto: Libro I - Capítulo 05', 'Almagesto: Libro I - Capítulo 06', 'Almagesto: Libro I - Capítulo 07', 'Almagesto: Libro I - Capítulo 08', 'Almagesto: Libro I - Capítulo 09', 'Almagesto: Libro I - Capítulo 10', 'Almagesto: Libro I - Capítulo 11', 'Almagesto: Libro I - Capítulo 12', 'Almagesto: Libro
    texts = ('\'\'\'<span style="color: #0a4404">Prefacio del traductor al inglés</span>\'\'\'', '\'\'\'<span style="color: #0a4404">Introducción</span>\'\'\'', '\'\'\'<span style="color: #020812">Capítulo 1</span> - Prefacio de Ptolomeo \'\'\'', '\'\'\'<span style="color: #020812">Capítulo 2</span> - Sobre el fin de los Teoremas \'\'\'', '\'\'\'<span style="color: #020812">Capítulo</span> <span style="color: #831139">3</span> - Que los Cielos se mueven como una Esfera  \'\'\'', '\'\'\'<span style="color: #
    cats = ('Textos científicos', 'ES-A', 'Obras de Claudio Ptolomeo', 'Literatura de la Antigua Grecia', 'Textos antiguos', 'Astronomía', 'Almagesto')
    tpls = frozenset({'Listaref'})
    own_chars = 3109
    flags = 0
    snippet = 'thumb|450px|Claudius Ptolemaeus y su obra el Almagesto Almagesto de Claudio Ptolomeo == El Almagesto (Al-Majisti) o Sintaxis del Tratado de Matemáticas fue escrito entre los años 138 d. C. al 161 d. C'
    rev = 1555321
    ts = '2025-06-13T17:40:24Z'
== Almagesto: Libro II - Capítulo 06
    title = 'Almagesto: Libro II - Capítulo 06'
    page_id = 195160
    bytes = 46310
    hdr = {}
    tags = []
    refs = []
    links = ('Almagesto: Libro II - Capítulo 05', 'Almagesto', 'Almagesto: Libro II - Capítulo 07', 'Almagesto: Libro I - Capítulo 15', 'Almagesto: Libro II - Capítulo 01', 'Almagesto: Libro II - Capítulo 02', 'Almagesto: Libro II - Capítulo 03', 'Almagesto: Libro II - Capítulo 04', 'Almagesto: Libro II - Capítulo 08', 'Almagesto: Libro II - Capítulo 09', 'Almagesto: Libro II - Capítulo 10', 'Almagesto: Libro II - Capítulo 11', 'Almagesto: Libro II - Capítulo 12', 'Almagesto: Libro II - Capítulo 13', 'Almag
    texts = ("'''Capítulo Anterior'''", '\'\'\'<span style="color: #065049">Contenidos</span>\'\'\'', "'''Capítulo Siguiente'''", "'''Tabla de la Inclinación (de la Eclíptica)'''", '<span style="color: #831139">\'\'\'01\'\'\'</span>', '<span style="color: #831139">\'\'\'02\'\'\'</span>', '<span style="color: #831139">\'\'\'03\'\'\'</span>', '<span style="color: #0d4f06">\'\'\'04\'\'\'</span>', '<span style="color: #831139">\'\'\'08\'\'\'</span>', '<span style="color: #831139">\'\'\'09\'\'\'</span>', '<span 
    cats = ('Almagesto',)
    tpls = frozenset({'Listaref'})
    own_chars = 25361
    flags = 0
    snippet = 'class="wikitable" align="center" | Capítulo Anterior ||align="center" | Contenidos || align="center" | Capítulo Siguiente Exposición de las Características Especiales, de Paralelo en Paralelo}== Por e'
    rev = 1380543
    ts = '2023-10-23T15:31:53Z'
es categories 'Diccionario…' (not letter cats): [('Diccionario de Arquitectura Civil', 1577), ('Diccionario Filosófico - Tomo IX', 65), ('Diccionario crítico-burlesco', 58), ('Diccionario de Educación, Instrucción y Enseñanza', 57), ('Diccionarios', 22), ('Diccionario geográfico-estadístico-histórico de España y sus posesiones de Ultramar', 14), ('Diccionario biográfico de Chile', 8), ('Diccionario de autoridades. Tomo I', 1), ('Diccionario de autoridades', 1), ('Diccionario Geográfico de la República de Chile', 1)]
es colon prefixes among single works (>= 15): [('Discurso', 392), ('Almagesto', 155), ('Correo de Comercio', 66), ('Historia de Cristóbal Colon y de sus viajes (Tomo I)', 26), ('Ley sobre Derechos de Autor y Derechos Conexos, de 14 de octubre de 1982, (cr)', 25), ('Código de Trabajo, de 27 de agosto de 1943, (cr)', 21), ('Proclama', 17), ('Las metamorfosis', 15)]
es hyphen prefixes ' - ' among single works (>= 15): [('Almagesto: Libro V', 19), ('MINUGUA', 18), ('Almagesto: Libro I', 16)]
```

### Item 5: sv TOC-only roots excluded as no-text and their chains (extract_queries.py sv)

```
sv no-text roots with >= 3 links to existing pages: 23 of 155
   ('Sabbatstoner, sånger för söndagsskolan, 1888', 116, 'Sabbatstoner, sånger för söndagsskolan, 1888', None)
   ('Dikter (Löwenhjelm)', 86, 'Dikter', 'Harriet Löwenhjelm')
   ('Nya dikter (Fröding)', 43, 'Nya dikter', 'Gustaf Fröding')
   ('Fänrik Ståls sägner', 35, 'Fänrik Ståls sägner', 'Johan Ludvig Runeberg')
   ('Gluntarne', 30, 'Gluntarne', 'Gunnar Wennerberg')
   ('Frithiofs saga', 24, 'Frithiofs saga', 'Esaias Tegnér')
   ('Bacchi Tempel', 15, 'Bacchi Tempel', 'Carl Michael Bellman')
   ('Svea Folk-Kalender: Elias Sehlstedt', 15, 'Svea Folk-Kalender:Elias Sehlstedt', 'Elias Sehlstedt')
   ('Mattoidens sånger', 13, 'Mattoidens sånger', 'Gustaf Fröding')
   ('Gralstänk (diktsamling)', 12, 'Gralstänk', 'Gustaf Fröding')
   ('Där ute. Skisser.', 12, 'Där ute. Skisser.', 'Henning Berger')
   ('86 Clark Street. Där ute. Samt andra noveller.', 12, '86 Clark Street.', 'Henning Berger')
   ('Svarvstolen', 9, 'Svarvstolen', 'Henning Berger')
   ('Översättningar (Fröding)', 7, 'Översättningar', 'Gustaf Fröding')
   ('7 sonetter till nobla damer och döda libertiner', 7, '7 sonetter till nobla damer och döda libertiner', 'Harriet Löwenhjelm')
link targets that are single works in the catalog: 463
```

### 

```
23 TOC-only roots: with {{Innehåll börjar}}: 2 ; linked members with a prev/next chain: 259 ; without: 212
```
