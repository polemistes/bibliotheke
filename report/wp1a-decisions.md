# WP1a: the owner's decisions of 2026-11-01

The WP1a survey (`report/{wiki}-structure.md`, dumps of 2026-10-01) left nine
items for the owner (STATUS.md of 2026-10-31, "Decisions for the owner before
the pipeline is built"). Each was measured on the cached extracts before
deciding; the measurements are in the appendix, the scripts in
`pipeline/wp1a/`. The owner accepted the recommendations as written on
2026-11-01. Rule changes are implemented in `pipeline/wscatalog/` and the
per-wiki configs; PLAN.md carries the amended passages marked "(WP1a,
2026-11-01 …)".

## Decisions

1. **`show_unknown_by_default`**: is `true`, en `true`, de `false`, fr
   `false`. is: hiding unknown works leaves 5 of 250; the unknown ones are
   typed-in sagas and poems. en: unknown works are 60 % of the catalog, about
   half of them government documents (court cases 4,837, proclamations 3,646,
   speeches 3,424, acts 1,735, executive orders 1,594, UN resolutions 1,570),
   the rest typed-in literature, classics included; the type facet must
   classify the documents (5.2 step 3) or the en shelf reads like a law
   library. de and fr: every real text declares a status or carries a
   text-quality template, so the leftovers are shells. On de, 2,029 single
   `{{Zeitschrift}}` pages without scans or status are bibliographies (lists
   of digitised volumes elsewhere) and are now hard-excluded as
   `bibliography` (config `quality.bibliography_templates`). On fr it is 111
   works either way, a third of the sample being title-only pages.

2. **`parts_cap` stays 2,000; the measured size is accepted; two encoding
   tweaks.** Lowering the cap to 500 saves 2.7 MB on en because 175,000 of
   its 341,000 rows come from ordinary books under 50 parts; WS§5.2's 5–8 MB
   estimate was wrong about ordinary books, not about the giants. Decided
   encoding (schema version 2): `parts.index_ref` is the position of the
   part's Index in `works.index_titles` (an integer, not the title) and
   `parts.display_title` is NULL when it equals the last segment of
   `page_title`; per-part `quality_pages` stays. en: 19.5 → 18.3 MB gzipped,
   83 → 67 MB raw SQLite on the device. The suffix encoding (`page_title`
   stored as the suffix after the root title; 16.8 MB / 45 MB) is deferred
   because it complicates open-by-URL and link rewriting.

3. **Reference works are not defined by size.** A work over
   `reference_threshold` parts with no Index type is `reference` only when
   its root's children have no reading-order signal (order `natural` or
   `toc-partial`); a prev/next chain or a covering TOC marks a periodical or
   collection. This retypes exactly the mis-typed works: en Popular Science
   Monthly, Nicene and Post-Nicene Fathers Series I and II, Ante-Nicene
   Fathers, Who's Who in the Far East; is Íslenzkar þjóðsögur og æfintýri.
   Works over `parts_cap` keep `parts_in_catalog = 0` and a live TOC (PLAN.md
   7.1 step 2). A title regex (dictionar, encyclop, lexik, who's who,
   alumni) for works typed book or untyped is a later option: it would catch
   The New International Encyclopædia (Index type book, 10,866 parts) and
   the lexica under 2,000 parts, at the cost of false positives such as The
   Devil's Dictionary.

4. **de colon prefixes: rule 3 extended instead of a prefix list.** In every
   kept-prefix case a root page exists and lists all the members; rule 3
   missed them because the members carry no header template, no scan Index
   and no status, and because the Merian *Topographia* roots say
   `HERKUNFT = [[Topographia Germaniae]]`, not `off`. Two changes, behind
   `structure.headerless_toc_members` (de only): a page with no header and
   no status that is listed by exactly one TOC-like page is its member
   regardless of Index or chain; a root page with a header and an Index that
   lists at least three such pages counts as TOC-like. Measured on de: 6,544
   pages attach under about 25 roots (4,166 validated, 2,378 unknown),
   including 1,512 whose root title differs from the prefix (Hertz, Schedel,
   Gmünd, Stammbuch, Taufmatrikel), all correct in the sample; rule-3
   attachments rose from 17,844 to 24,432 with no losses elsewhere. The de
   default view loses 4,163 single-page town entries and gains the volumes
   as books. `Hantzsch:` (380 validated street-name entries of the *Namenbuch
   der Straßen und Plätze Dresdens*, a lexicon like `Haan:`) joins
   `exclude_title_prefixes`. `Pomologische Monatshefte:` (articles with
   headers) and the three prefixes already grouped by `HERKUNFT`
   (`Rittergüter und Schlösser …:`, `Hervorragende Persönlichkeiten …:`,
   `Meißner Künstler:`) are left alone. A colon prefix shared by at least
   three siblings is stripped from the display title.

5. **PLAN.md WP1 acceptance figures amended** to what the decided rules give:
   `Diktsamling` has 4 parts and 116 missing (the 5 other TOC targets belong
   to other editions by rule 2; "also in" is a WP8 item); the no default view
   holds about 470 works and 2,400 texts (the 1,500 counted title groups
   before grouping), none of them raw OCR; the en budget is the measured
   figure of item 2; `Der Sagenschatz des Königreichs Sachsen` has 1,001
   parts in chain order.

6. **Index progress `MS`/`OCR`/`L` excludes a work only when none of its
   transcluded pages is at level 3 or 4.** The codes are stale: en `Cane
   (Toomer)` carries `L` with 244 proofread pages. Kept: 127 of en's 141 and
   50 of fr's 109 excluded works; what stays excluded is raw OCR or empty.

7. **Coverage keeps the 95 % threshold but counts differently.** Of the 854
   fr works flagged, 616 lacked only front or back matter (typically one to
   four validated title pages) and several "stubs" were short texts inside a
   scan that other works share (no `Hvorledes Dr. Wrangel kom` was at 1 %
   because 221 pages belong to other works). Now: pages that another work
   transcludes are left out of the denominator (`quality.coverage_min`
   0.95), and up to `quality.coverage_slack` (3) untranscluded pages are
   tolerated. Works incomplete by coverage alone: fr 700 → 305, en 43 → 26,
   no 12 → 5 (measured before the rerun).

8. **Text versus chapter: decision 8.9 stands.** The only cheap extra
   signal, the page title's last segment, demotes real texts: 31 % of no's
   texts (Asbjørnsen's tales are `Norske Folkeeventyr (1852)/10`), 36 % of
   fr's (Voltaire's and Flaubert's letters, fabliaux, Bible books), 9 % of
   en's (Montaigne's essays, The Rambler). Over-inclusion costs search
   noise; under-inclusion makes texts unfindable (the pattern E problem).
   If the noise matters, the app ranks such hits lower.

9. **The implementation choices of the reports' section 13 are accepted**
   and recorded in PLAN.md 5.2. Two defects from the eyeball review are
   tickets, not decisions: `scan-volumes` orders no periodicals by file
   name ("Andet Bind" first), and `Fru Bovary` attaches each part twice
   (subpage and colon root page).

## Appendix: measurements behind the decisions

Generated 2026-11-01 from the cached extracts of the 2026-10-01 dumps with
the pipeline at commit eccee8d (before the rule changes above). Scripts:
`pipeline/wp1a/analyze.py` (sections D1–D8 per wiki), `de_prefixes.py`,
`de_rule3.py`, `coverage2.py`, `size2.py`.


# no: evidence for decisions 1–8 (2026-11-01)

Resolution and quality: 1 s; 1,118 works, 1,069 in the catalog.

## D1 unknown works (223)

Title heuristics (first match):

| kind | works |
|---|---|
| other | 223 |

Header templates on the unknown works' root page:

| header | works |
|---|---|
| Topp | 106 |
| (no header template) | 88 |
| Topp2 | 29 |

Single-page unknown works with a colon in the title: 0.
Multi-part unknown works: 14 with 63 parts.

## D2 parts table size by component (cap 2,000, reference works out)

| variant | rows | raw SQLite | gzipped | saving |
|---|---|---|---|---|
| as in the schema | 10,805 | 2.2 MB | 0.61 MB | 0 % |
| no per-part quality_pages | 10,805 | 2.1 MB | 0.59 MB | 4 % |
| page_title as suffix of the root title | 10,805 | 1.8 MB | 0.58 MB | 5 % |
| display_title blank when it equals the last title segment | 10,805 | 2.2 MB | 0.59 MB | 3 % |
| no per-part index_title | 10,805 | 1.8 MB | 0.59 MB | 4 % |
| no quality_pages + no index_title | 10,805 | 1.7 MB | 0.57 MB | 7 % |
| all four | 10,805 | 1.2 MB | 0.51 MB | 16 % |

Rows by work size (parts per work):

| parts per work | works | rows |
|---|---|---|
| 1–50 | 401 | 5,934 |
| 51–100 | 19 | 1,398 |
| 101–200 | 6 | 916 |
| 201–500 | 2 | 726 |
| 501–1000 | 1 | 692 |
| 1001–2000 | 1 | 1,139 |

Parts with a quality_pages JSON: 10,644 of 10,805.
Works table lower bound (structure and quality columns only, no authors, years, descriptions): 0.12 MB gzipped for 1,069 works; works_fts (titles only) 0.02 MB.

## D3 works over reference_threshold (2,000) without an Index type

None.

Reference works typed by the Index type: 0 (over the cap: 0).

Non-reference works with ≥ 50 parts whose title matches a reference-word regex (dictionar|encyclop|lexik|…): 1. Examples:

- `Norsk Haandlexikon for almennyttige Kundskaber` (collection, 1,138 parts, 1,136 texts, order natural, Index type –)

## D6 works excluded as index-faulty (0)

None.

## D7 coverage of the Index's text pages

| coverage | works |
|---|---|
| 50–80 % | 11 |
| 80–90 % | 9 |
| 90–95 % | 9 |
| < 50 % | 12 |
| ≥ 95 % | 318 |

Where the uncovered pages lie, for the 41 works under 95 %:

| case | works |
|---|---|
| only front/back matter uncovered | 21 |
| inner gaps ≥ 5 % (missing chapters) | 18 |
| inner gaps under 5 % | 2 |

Levels of the uncovered pages (all works under 95 %): L1: 1,270, L2: 243, L3: 452, L4: 58.
Works under 90%: 32.
Works under 80%: 23.
Works with inner coverage (between the first and last transcluded page) under 95 %: 18.

20 random works under 95 %:

- `Vor nasjonale Skam` (single, 1 parts, 0 red links, proofread): coverage 87% of 31 pages; uncovered front 0, inner 0, back 4; inner coverage 100%; uncovered levels L3: 3, L4: 1
- `Fremskridt og Fattigdom` (single, 1 parts, 14 red links, unproofread): coverage 1% of 252 pages; uncovered front 4, inner 0, back 245; inner coverage 100%; uncovered levels L1: 237, L2: 6, L3: 6
- `Erindring Michael Sars` (single, 1 parts, 0 red links, proofread): coverage 89% of 9 pages; uncovered front 1, inner 0, back 0; inner coverage 100%; uncovered levels L3: 1
- `Topografisk-historisk-statistisk beskrivelse over Akershus amt` (subpages, 1 parts, 2 red links, proofread): coverage 4% of 340 pages; uncovered front 0, inner 98, back 229; inner coverage 12%; uncovered levels L1: 325, L3: 2
- `Blik ud i Philosophiens Fremtid` (single, 1 parts, 0 red links, proofread): coverage 93% of 14 pages; uncovered front 1, inner 0, back 0; inner coverage 100%; uncovered levels L3: 1
- `Salmar og aandelege songar` (linked, 14 parts, 7 red links, proofread): coverage 70% of 23 pages; uncovered front 2, inner 3, back 2; inner coverage 84%; uncovered levels L3: 7
- `Forsøg til Beskrivelse over Kragerøe Kiøbsted og Langesunds-Fiorden` (subpages, 2 parts, 3 red links, proofread): coverage 88% of 8 pages; uncovered front 0, inner 1, back 0; inner coverage 88%; uncovered levels L3: 1
- `En liden julegave` (linked, 1 parts, 17 red links, proofread): coverage 62% of 8 pages; uncovered front 3, inner 0, back 0; inner coverage 100%; uncovered levels L1: 1, L3: 2
- `Blandt anarkister` (subpages, 10 parts, 0 red links, proofread): coverage 89% of 95 pages; uncovered front 1, inner 9, back 0; inner coverage 90%; uncovered levels L3: 7, L4: 3
- `Bibelen (1891)` (subpages, 26 parts, 41 red links, unproofread): coverage 44% of 203 pages; uncovered front 1, inner 113, back 0; inner coverage 44%; uncovered levels L1: 105, L2: 6, L3: 2, L4: 1
- `Autobiographi` (subpages, 3 parts, 1 red links, proofread): coverage 95% of 19 pages; uncovered front 1, inner 0, back 0; inner coverage 100%; uncovered levels L3: 1
- `Manden i Utah` (single, 1 parts, 19 red links, proofread): coverage 44% of 9 pages; uncovered front 0, inner 0, back 5; inner coverage 100%; uncovered levels L3: 5
- `Studier og portræter` (linked, 1 parts, 5 red links, proofread): coverage 55% of 33 pages; uncovered front 0, inner 0, back 15; inner coverage 100%; uncovered levels L1: 14, L3: 1
- `Hvorledes Dr. Wrangel kom` (single, 1 parts, 0 red links, proofread): coverage 1% of 224 pages; uncovered front 1, inner 221, back 0; inner coverage 1%; uncovered levels L3: 221, L4: 1
- `De vaabenløse` (single, 1 parts, 4 red links, proofread): coverage 8% of 26 pages; uncovered front 0, inner 0, back 24; inner coverage 100%; uncovered levels L3: 24
- `P. A. Munchs Levnet og Breve i Familiekredsen` (subpages, 4 parts, 2 red links, proofread): coverage 88% of 33 pages; uncovered front 3, inner 0, back 1; inner coverage 100%; uncovered levels L3: 4
- `Sokrates skildret gjennem Oversættelser af Platon` (linked, 3 parts, 1 red links, proofread): coverage 75% of 20 pages; uncovered front 0, inner 5, back 0; inner coverage 75%; uncovered levels L3: 5
- `Socialøkonomik` (subpages, 7 parts, 13 red links, proofread): coverage 94% of 17 pages; uncovered front 0, inner 1, back 0; inner coverage 94%; uncovered levels L3: 1
- `Felles trygghet - felles ansvar` (subpages, 7 parts, 0 red links, proofread): coverage 94% of 36 pages; uncovered front 1, inner 0, back 1; inner coverage 100%; uncovered levels L3: 2
- `Ben-Hur` (subpages, 1 parts, 26 red links, proofread): coverage 9% of 58 pages; uncovered front 0, inner 0, back 53; inner coverage 100%; uncovered levels L2: 1, L3: 52

## D8 searchable texts whose page title segment is a chapter label

Searchable texts (reference works excluded): 6,514; with a chapter-like page title segment: 2,036 (31.3 %).

| work type | texts demoted |
|---|---|
| book | 1,955 |
| collection | 63 |
| document | 18 |

| Index type (raw) | texts demoted |
|---|---|
| book | 1,941 |
| (none) | 76 |
| phdthesis | 18 |
| collection | 1 |

30 random texts the page-title rule would demote to chapters:

- “HJEMOVER GJENNOM SIBIR” (`Gjennom Sibir/20`) in `Gjennom Sibir` (book, Index type book)
- “TROITSKIJ MONASTIR, OG VIDERE SØRETTER” (`Gjennom Sibir/10`) in `Gjennom Sibir` (book, Index type book)
- “Et direktionsmøte” (`Guldaaren/Kapittel 20`) in `Guldaaren` (book, Index type book)
- “Edgar Poe” (`Essays. Fremmede Forfattere/02`) in `Essays. Fremmede Forfattere` (book, Index type book)
- “Fremmedord” (`Litt om skikk og bruk før og nu/10`) in `Litt om skikk og bruk før og nu` (book, Index type book)
- “Somme Kjærringer er slige!” (`Norske Folkeeventyr (1852)/10`) in `Norske Folkeeventyr (1852)` (book, Index type book)
- “Fast Gods og Løsøre. Levende og dødt Gods. Penge” (`Forelæsninger over den norske Retshistorie/30`) in `Forelæsninger over den norske Retshistorie` (book, Index type book)
- “KJELDSEN OG HATTEN” (`Storhertuginnen av Speilsalen/2`) in `Storhertuginnen av Speilsalen` (book, Index type book)
- “Mestertyven” (`Norske Folkeeventyr (1852)/34`) in `Norske Folkeeventyr (1852)` (book, Index type book)
- “Rasehygienen hos de gamle kulturfolk” (`Rasehygiene/9`) in `Rasehygiene` (book, Index type book)
- “Ibsen og H. C. Andersen” (`Samliv med Ibsen/4`) in `Samliv med Ibsen` (book, Index type book)
- “Bagning” (`Lærebog i de forskjellige Grene af Huusholdningen/03`) in `Lærebog i de forskjellige Grene af Huusholdningen` (book, Index type book)
- “Ibsen og Henrikke Holst” (`Samliv med Ibsen/12`) in `Samliv med Ibsen` (book, Index type book)
- “Kvannelederskernes $ang.” (`Det vilde kor/50`) in `Det vilde kor` (book, Index type book)
- “Tidsrummet før Olaf den Hellige. Eidsivatingsloven. Enekongedømmet. Odelens Tilegnelse og Tilbagegivelse. Gulatings- og Frostatingslovene” (`Forelæsninger over den norske Retshistorie/3`) in `Forelæsninger over den norske Retshistorie` (book, Index type book)
- “Rase” (`Rasehygiene (utdrag)/2`) in `Rasehygiene (utdrag)` (book, Index type book)
- “Bidra til systematisk innsamling av data om levekår som kan belyse i hvilken grad man når målene for politikken for mennesker med utviklingshemming og innsatsen innen ulike tjenestegrener” (`Utviklingshemmede skal heller ikke diskrimineres/3`) in `Utviklingshemmede skal heller ikke diskrimineres` (book, Index type book)
- “Fortsættelse: Frigivelse. Frelsesøl. Tyrmsel” (`Forelæsninger over den norske Retshistorie/18`) in `Forelæsninger over den norske Retshistorie` (book, Index type book)
- “Gjenta paa Stiklestad” (`Ferdaminni fraa Sumaren 1860/39`) in `Ferdaminni fraa Sumaren 1860` (book, Index type book)
- “Utgiverens fortale” (`Sverres saga/1`) in `Sverres saga` (book, Index type book)
- “Varorðe” (`Norsk mållære/8`) in `Norsk mållære` (book, Index type book)
- “§ 3.” (`De norske Klostres Historie i Middelalderen/4/3`) in `De norske Klostres Historie i Middelalderen` (book, Index type book)
- “Ølgøgd’nshaugen, ein gamalø Afgudshøi” (`Gamla Reglo aa Rispo/5`) in `Gamla Reglo aa Rispo` (book, Index type book)
- “Fireogtyvende kapitel” (`Andreas Lavik/XXIV`) in `Andreas Lavik` (book, Index type book)
- “Tyrihans som fik Kongsdatteren til at le” (`Norske Folke- og Huldre-eventyr/31`) in `Norske Folke- og Huldre-Eventyr` (book, Index type book)
- “Sammensatte Verba” (`Det oldnorske Verbum/7`) in `Det oldnorske Verbum` (book, Index type book)
- “Presentasjon av alternative modeller” (`Anbefaling av en sunnhetsmerkeordning/4`) in `Anbefaling av en sunnhetsmerkeordning` (document, Index type phdthesis)
- “JONASSEN.” (`Fjerdemand/17`) in `Fjerdemand` (book, Index type book)
- “Norges-kartet” (`Nationalforskeren P. A. Munch/13`) in `Nationalforskeren P. A. Munch` (book, Index type book)
- “Eksisterende regelverk og pågående prosess i EU” (`Anbefaling av en sunnhetsmerkeordning/8`) in `Anbefaling av en sunnhetsmerkeordning` (document, Index type phdthesis)


# is: evidence for decisions 1–8 (2026-11-01)

Resolution and quality: 0 s; 256 works, 250 in the catalog.

## D1 unknown works (243)

Title heuristics (first match):

| kind | works |
|---|---|
| other | 243 |

Header templates on the unknown works' root page:

| header | works |
|---|---|
| Verk | 173 |
| (no header template) | 70 |

Single-page unknown works with a colon in the title: 0.
Multi-part unknown works: 25 with 4,615 parts.

## D2 parts table size by component (cap 2,000, reference works out)

| variant | rows | raw SQLite | gzipped | saving |
|---|---|---|---|---|
| as in the schema | 1,688 | 0.3 MB | 0.07 MB | 0 % |
| no per-part quality_pages | 1,688 | 0.3 MB | 0.07 MB | 0 % |
| page_title as suffix of the root title | 1,688 | 0.2 MB | 0.07 MB | 5 % |
| display_title blank when it equals the last title segment | 1,688 | 0.3 MB | 0.07 MB | 2 % |
| no per-part index_title | 1,688 | 0.3 MB | 0.07 MB | 0 % |
| no quality_pages + no index_title | 1,688 | 0.3 MB | 0.07 MB | 1 % |
| all four | 1,688 | 0.2 MB | 0.07 MB | 8 % |

Rows by work size (parts per work):

| parts per work | works | rows |
|---|---|---|
| 1–50 | 27 | 474 |
| 51–100 | 1 | 92 |
| 101–200 | 2 | 269 |
| 201–500 | 0 | 0 |
| 501–1000 | 1 | 853 |
| 1001–2000 | 0 | 0 |

Parts with a quality_pages JSON: 107 of 1,688.
Works table lower bound (structure and quality columns only, no authors, years, descriptions): 0.02 MB gzipped for 250 works; works_fts (titles only) 0.01 MB.

## D3 works over reference_threshold (2,000) without an Index type

| work | parts | texts | structure | order | depth | tier | type if chain/toc order → not reference |
|---|---|---|---|---|---|---|---|
| Íslenzkar þjóðsögur og æfintýri | 3,058 | 2,964 | mixed | toc | 6 | unknown | collection/book |

Reference works typed by the Index type: 0 (over the cap: 0).

Non-reference works with ≥ 50 parts whose title matches a reference-word regex (dictionar|encyclop|lexik|…): 0. Examples:


## D6 works excluded as index-faulty (0)

None.

## D7 coverage of the Index's text pages

| coverage | works |
|---|---|

## D8 searchable texts whose page title segment is a chapter label

Searchable texts (reference works excluded): 261; with a chapter-like page title segment: 11 (4.2 %).

| work type | texts demoted |
|---|---|
| collection | 8 |
| book | 3 |

| Index type (raw) | texts demoted |
|---|---|
| (none) | 11 |

30 random texts the page-title rule would demote to chapters:

- “[4. kafli]” (`Piltur og stúlka/4`) in `Piltur og stúlka` (collection, Index type –)
- “Viðbætir.” (`Guðmundar saga Arasonar/91`) in `Guðmundar saga Arasonar` (book, Index type –)
- “[7. kafli]” (`Piltur og stúlka/7`) in `Piltur og stúlka` (collection, Index type –)
- “12.Víg Háreks úr Þjóttu” (`Heimskringla/Magnúss saga góða/12`) in `Heimskringla` (book, Index type –)
- “[2. kafli]” (`Piltur og stúlka/2`) in `Piltur og stúlka` (collection, Index type –)
- “[1. kafli]” (`Piltur og stúlka/1`) in `Piltur og stúlka` (collection, Index type –)
- “210 Frá Þorgilsi Hálmusyni” (`Heimskringla/Ólafs saga helga/210`) in `Heimskringla` (book, Index type –)
- “[3. kafli]” (`Piltur og stúlka/3`) in `Piltur og stúlka` (collection, Index type –)
- “[5. kafli]” (`Piltur og stúlka/5`) in `Piltur og stúlka` (collection, Index type –)
- “[8. kafli]” (`Piltur og stúlka/8`) in `Piltur og stúlka` (collection, Index type –)
- “[6. kafli]” (`Piltur og stúlka/6`) in `Piltur og stúlka` (collection, Index type –)


# de: evidence for decisions 1–8 (2026-11-01)

Resolution and quality: 8 s; 26,637 works, 26,035 in the catalog.

## D1 unknown works (4,336)

Title heuristics (first match):

| kind | works |
|---|---|
| other | 4,336 |

Header templates on the unknown works' root page:

| header | works |
|---|---|
| (no header template) | 2,508 |
| Zeitschrift | 1,774 |
| Textdaten | 52 |
| Gesetzestext | 2 |

Single-page unknown works with a colon in the title: 2,577.
Multi-part unknown works: 42 with 1,465 parts.

## D2 parts table size by component (cap 2,000, reference works out)

| variant | rows | raw SQLite | gzipped | saving |
|---|---|---|---|---|
| as in the schema | 54,642 | 10.9 MB | 3.28 MB | 0 % |
| no per-part quality_pages | 54,642 | 10.5 MB | 3.21 MB | 2 % |
| page_title as suffix of the root title | 54,642 | 9.8 MB | 3.22 MB | 2 % |
| display_title blank when it equals the last title segment | 54,642 | 9.8 MB | 3.13 MB | 5 % |
| no per-part index_title | 54,642 | 10.0 MB | 3.23 MB | 1 % |
| no quality_pages + no index_title | 54,642 | 9.5 MB | 3.16 MB | 4 % |
| all four | 54,642 | 7.4 MB | 2.93 MB | 11 % |

Rows by work size (parts per work):

| parts per work | works | rows |
|---|---|---|
| 1–50 | 722 | 10,536 |
| 51–100 | 96 | 7,131 |
| 101–200 | 54 | 7,504 |
| 201–500 | 57 | 21,422 |
| 501–1000 | 9 | 5,721 |
| 1001–2000 | 2 | 2,328 |

Parts with a quality_pages JSON: 45,965 of 54,642.
Works table lower bound (structure and quality columns only, no authors, years, descriptions): 2.35 MB gzipped for 26,035 works; works_fts (titles only) 0.47 MB.

## D3 works over reference_threshold (2,000) without an Index type

None.

Reference works typed by the Index type: 0 (over the cap: 0).

Non-reference works with ≥ 50 parts whose title matches a reference-word regex (dictionar|encyclop|lexik|…): 2. Examples:

- `Paulys Realencyclopädie der classischen Altertumswissenschaft` (collection, 1,325 parts, 1,297 texts, order toc-partial, Index type –)
- `Geographisches Statistisch-Topographisches Lexikon von Franken` (collection, 94 parts, 94 texts, order natural, Index type –)

## D4 de colon prefixes (kept, ≥ 100 root pages)

| prefix | root pages | single works | grouped as parts | excluded | tiers of the single works | grouped under | root page `Prefix` |
|---|---|---|---|---|---|---|---|
| Rittergüter und Schlösser im Königreiche Sachsen: | 620 | 0 | 619 | 1 |  | `Album der Rittergüter und Schlösser im Königreiche Sachsen` 619 | no page |
| Topographia Circuli Burgundici: | 514 | 514 | 0 | 0 | validated 514 |  | work (subpages, 1 parts, TOC lists 509 of the prefix pages) |
| Topographia Austriacarum: | 431 | 431 | 0 | 0 | validated 431 |  | no page |
| Schwere, Elektricität und Magnetismus: | 412 | 412 | 0 | 0 | unknown 412 |  | work (subpages, 124 parts, TOC lists 412 of the prefix pages) |
| Hantzsch: | 390 | 380 | 10 | 0 | validated 380 | `Namenbuch der Straßen und Plätze Dresdens` 10 | no page |
| Allgemeines Deutsches Kommersbuch: | 385 | 385 | 0 | 0 | unknown 385 |  | work (mixed, 4 parts, TOC lists 385 of the prefix pages) |
| Topographia Sueviae: | 382 | 382 | 0 | 0 | validated 382 |  | work (subpages, 1 parts, TOC lists 382 of the prefix pages) |
| Topographia Bohemiae, Moraviae et Silesiae: | 375 | 375 | 0 | 0 | validated 375 |  | work (subpages, 1 parts, TOC lists 371 of the prefix pages) |
| Topographia Electoratus Brandenburgici et Ducatus Pomeraniae: | 338 | 338 | 0 | 0 | validated 338 |  | work (single, 1 parts, TOC lists 337 of the prefix pages) |
| Topographia Franconiae: | 319 | 319 | 0 | 0 | validated 319 |  | work (subpages, 1 parts, TOC lists 314 of the prefix pages) |
| Elektrische Kraft Hertz: | 312 | 312 | 0 | 0 | unknown 312 |  | no page |
| Die Schedelsche Weltchronik (deutsch): | 298 | 298 | 0 | 0 | unknown 298 |  | no page |
| Die Siegel der Deutschen Kaiser und Könige: | 292 | 292 | 0 | 0 | unknown 292 |  | work (linked, 84 parts, TOC lists 15 of the prefix pages) |
| Topographia Superioris Saxoniae: | 265 | 265 | 0 | 0 | validated 265 |  | work (subpages, 1 parts, TOC lists 265 of the prefix pages) |
| Topographia Braunschweig Lüneburg: | 228 | 228 | 0 | 0 | validated 228 |  | work (subpages, 1 parts, TOC lists 228 of the prefix pages) |
| Topographia Alsatiae: | 220 | 220 | 0 | 0 | validated 220 |  | work (subpages, 1 parts, TOC lists 220 of the prefix pages) |
| Topographia Westphaliae: | 217 | 217 | 0 | 0 | validated 217 |  | work (subpages, 1 parts, TOC lists 215 of the prefix pages) |
| Topographia Palatinatus Rheni: | 217 | 217 | 0 | 0 | validated 217 |  | work (subpages, 1 parts, TOC lists 215 of the prefix pages) |
| Topographia Hassiae: | 208 | 208 | 0 | 0 | validated 207, unknown 1 |  | work (subpages, 1 parts, TOC lists 206 of the prefix pages) |
| Hervorragende Persönlichkeiten in Dresden und ihre Wohnungen: | 174 | 0 | 174 | 0 |  | `Mitteilungen des Vereins für Geschichte Dresdens` 174 | part of `Mitteilungen des Vereins für Geschichte Dresdens` |
| Topographia Saxoniae Inferioris: | 172 | 172 | 0 | 0 | validated 172 |  | work (subpages, 1 parts, TOC lists 171 of the prefix pages) |
| Meißner Künstler: | 167 | 0 | 167 | 0 |  | `Lebensläufe Meißner Künstler` 167 | no page |
| Die Ursache des Einschlagens vom Blitze: | 165 | 165 | 0 | 0 | unknown 165 |  | work (subpages, 8 parts, TOC lists 163 of the prefix pages) |
| Med. Topographie Gmuend: | 150 | 150 | 0 | 0 | unknown 150 |  | no page |
| Pomologische Monatshefte: | 135 | 135 | 0 | 0 | validated 135 |  | work (subpages, 52 parts, TOC lists 0 of the prefix pages) |
| Stammbuch berümpter Fürsten, Graffen vnnd Herren Geschlechter: | 128 | 128 | 0 | 0 | unknown 128 |  | work (single, 1 parts, TOC lists 128 of the prefix pages) |
| Topographia Helvetiae, Rhaetiae et Valesiae: | 128 | 128 | 0 | 0 | validated 128 |  | work (subpages, 1 parts, TOC lists 128 of the prefix pages) |
| Taufmatrikel Passau Sankt Stephan: | 123 | 123 | 0 | 0 | unknown 123 |  | no page |
| Eygentliche Beschreibung Aller Stände auff Erden: | 122 | 122 | 0 | 0 | unknown 122 |  | work (single, 1 parts, TOC lists 122 of the prefix pages) |

Of the 4,336 unknown works, 2,577 are single colon-prefixed root pages; 2,104 of those carry a prefix with ≥ 100 root pages: `Schwere, Elektricität und Magnetismus:` 399, `Allgemeines Deutsches Kommersbuch:` 376, `Elektrische Kraft Hertz:` 302, `Die Schedelsche Weltchronik (deutsch):` 296, `Die Ursache des Einschlagens vom Blitze:` 150, `Med. Topographie Gmuend:` 150, `Stammbuch berümpter Fürsten, Graffen vnnd Herren Geschlechter:` 124, `Eygentliche Beschreibung Aller Stände auff Erden:` 122, `Taufmatrikel Passau Sankt Stephan:` 117, `Die Siegel der Deutschen Kaiser und Könige:` 68.
Prefixes with 10–99 root pages among the unknown works: `Die sociale und politische Stellung der Deutschen in den Vereinigten Staaten:` 54, `Heiratsmatrikel Passau Sankt Stephan:` 53, `Ausführliche Vorschriften zur Blitz-Ableitung an allerley Gebäuden:` 45, `Jugendleben und Wanderbilder:` 42, `Die deutschen Kaiser:` 37, `Requiem (Rainer Maria Rilke):` 34, `Ersch-Gruber:` 30, `Schrift und Schrifttum:` 27, `Beschreibung und Abbildung Aller Königl. und Churfürstl. Ein-Züge, Wahl und Crönungs Acta:` 19, `Gesänge aus Charmides und Theone:` 15, `Historisch-Politisch-Geographischer Atlas der gantzen Welt:` 10.

## D6 works excluded as index-faulty (0)

None.

## D7 coverage of the Index's text pages

| coverage | works |
|---|---|

## D8 searchable texts whose page title segment is a chapter label

Searchable texts (reference works excluded): 48,119; with a chapter-like page title segment: 1,933 (4.0 %).

| work type | texts demoted |
|---|---|
| collection | 1,886 |
| book | 47 |

| Index type (raw) | texts demoted |
|---|---|
| (none) | 1,933 |

30 random texts the page-title rule would demote to chapters:

- “S. 315” (`Ueber die Tugenden/Einleitung`) in `Ueber die Tugenden` (collection, Index type –)
- “Gemeinde Straßdorf” (`Beschreibung des Oberamts Gmünd/Kapitel B 20`) in `Beschreibung des Oberamts Gmünd` (collection, Index type –)
- “Berneck” (`Beschreibung des Oberamts Geislingen/Kapitel B 7`) in `Beschreibung des Oberamts Geislingen` (collection, Index type –)
- “Börstingen” (`Beschreibung des Oberamts Horb/Kapitel B 9`) in `Beschreibung des Oberamts Horb` (collection, Index type –)
- “Aus dem Tagebuche eines hypochondrischen Laien/II” (`Aus dem Tagebuche eines hypochondrischen Laien/II`) in `Aus dem Tagebuche eines hypochondrischen Laien` (collection, Index type –)
- “Wie herr Wilhalm Wernher das schloß Urslingen und den Ramstain an sich erkauft hat, auch von mancherlai handlungen, die im derzeit zu Speir am cammergericht sein begegnet.” (`Zimmerische Chronik/Band 3/Kapitel 9`) in `Zimmerische Chronik` (collection, Index type –)
- “Dettingen, Pfd.” (`Beschreibung des Oberamts Rottenburg/Kapitel B 4`) in `Beschreibung des Oberamts Rottenburg` (collection, Index type –)
- “Dürnau” (`Beschreibung des Oberamts Riedlingen/Kapitel B 14`) in `Beschreibung des Oberamts Riedlingen` (collection, Index type –)
- “Gegenstände des Vertriebs” (`Die grosse Ravensburger Gesellschaft/4`) in `Die grosse Ravensburger Gesellschaft` (collection, Index type –)
- “Der Gürzenich” (`Leben und Thaten des berühmten Ritters Schnapphahnski/XXII. Der Gürzenich`) in `Leben und Thaten des berühmten Ritters Schnapphahnski` (collection, Index type –)
- “Wie kaiser Hainrich denen Obotriten und Wenden vier obriste hauptleüt gesetzt, under denen herr Friderrich freiher von Zimbern ainer gewest; auch wie her Georg, her Gottfrid und her Sigfrid, seine geb” (`Zimmerische Chronik/Band 1/Kapitel 9`) in `Zimmerische Chronik` (collection, Index type –)
- “Wiernsheim” (`Beschreibung des Oberamts Maulbronn/Kapitel B 21`) in `Beschreibung des Oberamts Maulbronn` (book, Index type –)
- “Wie herr Jörg, freiherr von Zimbern, ehe dann der beischlaf zwischen im und frölen Adelgunden, freiin von Hohenclingen beschehen, sambt seiner fraw muter und seim hern bruoder Cuno in ainem gemainen l” (`Zimmerische Chronik/Band 1/Kapitel 18`) in `Zimmerische Chronik` (collection, Index type –)
- “Ingersheim” (`Beschreibung des Oberamts Crailsheim/Kapitel B 8`) in `Beschreibung des Oberamts Crailsheim` (collection, Index type –)
- “Burgberg” (`Beschreibung des Oberamts Heidenheim/Kapitel B 5`) in `Beschreibung des Oberamts Heidenheim` (collection, Index type –)
- “Ettenkirch” (`Beschreibung des Oberamts Tettnang/Kapitel B 5`) in `Beschreibung des Oberamts Tettnang` (collection, Index type –)
- “Gemeinde Eggenreute” (`Beschreibung des Oberamts Wangen/Kapitel B 5`) in `Beschreibung des Oberamts Wangen` (collection, Index type –)
- “Nattheim” (`Beschreibung des Oberamts Heidenheim/Kapitel B 20`) in `Beschreibung des Oberamts Heidenheim` (collection, Index type –)
- “Schietingen” (`Beschreibung des Oberamts Nagold/Kapitel B 27`) in `Beschreibung des Oberamts Nagold` (collection, Index type –)
- “Heisterkirch” (`Beschreibung des Oberamts Waldsee/Kapitel BW7`) in `Beschreibung des Oberamts Waldsee` (collection, Index type –)
- “Gemeinde Neutrauchburg” (`Beschreibung des Oberamts Wangen/Kapitel B 17`) in `Beschreibung des Oberamts Wangen` (collection, Index type –)
- “Die Karawane, Fortsetzung” (`Die Karawane/3`) in `W. Hauffs Werke` (collection, Index type –)
- “Nabern” (`Beschreibung des Oberamts Kirchheim/Kapitel B 10`) in `Beschreibung des Oberamts Kirchheim` (collection, Index type –)
- “Der St. Jakober Krieg” (`Geschichte der Stadt Basel. Erster Band/4. Der Kampf mit Oesterreich/10. Der St. Jakober Krieg`) in `Geschichte der Stadt Basel. Erster Band` (collection, Index type –)
- “U/V/W–Ve” (`Paulys Realencyclopädie der classischen Altertumswissenschaft/Register/v`) in `Paulys Realencyclopädie der classischen Altertumswissenschaft` (collection, Index type –)
- “Reichenbach” (`Beschreibung des Oberamts Waldsee/Kapitel BS4`) in `Beschreibung des Oberamts Waldsee` (collection, Index type –)
- “Bernhausen” (`Beschreibung des Oberamts Saulgau/Kapitel B 1`) in `Beschreibung des Oberamts Saulgau` (collection, Index type –)
- “Obere Rauh-Mühle” (`Beschreibung des Oberamts Böblingen/Kapitel B 18`) in `Beschreibung des Oberamts Böblingen` (collection, Index type –)
- “Berg mit dem Ernsthof” (`Beschreibung des Oberamts Ehingen/Kapitel B 8`) in `Beschreibung des Oberamts Ehingen` (collection, Index type –)
- “Amberg” (`Beschreibung des Oberamts Saulgau/Kapitel B 5`) in `Beschreibung des Oberamts Saulgau` (collection, Index type –)


# fr: evidence for decisions 1–8 (2026-11-01)

Resolution and quality: 50 s; 40,889 works, 40,195 in the catalog.

## D1 unknown works (120)

Title heuristics (first match):

| kind | works |
|---|---|
| other | 118 |
| treaty / convention / agreement | 2 |

Header templates on the unknown works' root page:

| header | works |
|---|---|
| (no header template) | 120 |

Single-page unknown works with a colon in the title: 3.
Multi-part unknown works: 13 with 349 parts.

## D2 parts table size by component (cap 2,000, reference works out)

| variant | rows | raw SQLite | gzipped | saving |
|---|---|---|---|---|
| as in the schema | 263,024 | 67.0 MB | 15.43 MB | 0 % |
| no per-part quality_pages | 263,024 | 64.4 MB | 14.83 MB | 4 % |
| page_title as suffix of the root title | 263,024 | 51.5 MB | 14.43 MB | 7 % |
| display_title blank when it equals the last title segment | 263,024 | 64.9 MB | 15.08 MB | 2 % |
| no per-part index_title | 263,024 | 53.0 MB | 14.77 MB | 4 % |
| no quality_pages + no index_title | 263,024 | 50.5 MB | 14.16 MB | 8 % |
| all four | 263,024 | 33.0 MB | 12.69 MB | 18 % |

Rows by work size (parts per work):

| parts per work | works | rows |
|---|---|---|
| 1–50 | 10,592 | 137,818 |
| 51–100 | 637 | 44,900 |
| 101–200 | 237 | 32,749 |
| 201–500 | 81 | 24,169 |
| 501–1000 | 17 | 11,610 |
| 1001–2000 | 8 | 11,778 |

Parts with a quality_pages JSON: 255,482 of 263,024.
Works table lower bound (structure and quality columns only, no authors, years, descriptions): 4.74 MB gzipped for 40,195 works; works_fts (titles only) 0.80 MB.

## D3 works over reference_threshold (2,000) without an Index type

| work | parts | texts | structure | order | depth | tier | type if chain/toc order → not reference |
|---|---|---|---|---|---|---|---|
| Encyclopédie, ou Dictionnaire raisonné des sciences, des arts et des métiers | 3,858 | 3,856 | linked | scan | 1 | proofread | reference |
| Thresor de la langue françoise | 3,357 | 3,324 | subpages | natural | 2 | unproofread | reference |
| Dictionnaire pratique et historique de la musique | 2,416 | 2,382 | subpages | natural | 2 | proofread | reference |

Reference works typed by the Index type: 271 (over the cap: 16).

Non-reference works with ≥ 50 parts whose title matches a reference-word regex (dictionar|encyclop|lexik|…): 14. Examples:

- `Dictionnaire de théologie catholique` (collection, 1,987 parts, 1,852 texts, order natural, Index type –)
- `Dictionnaire universel d’histoire et de géographie Bouillet Chassang` (collection, 1,896 parts, 1,891 texts, order natural, Index type –)
- `Dictionnaire érotique moderne` (book, 1,717 parts, 1,707 texts, order scan, Index type book)
- `Dictionnaire encyclopédique de la noblesse de France` (collection, 1,089 parts, 1,082 texts, order natural, Index type –)
- `Dictionnaire philosophique/Garnier (1878)` (collection, 637 parts, 628 texts, order natural, Index type collection)
- `Dictionnaire historique littéraire et bibliographie des françaises et des étrangères naturalisées en France` (book, 583 parts, 576 texts, order scan, Index type book)
- `Dictionnaire raisonné de l’architecture française du XIe au XVIe siècle` (collection, 530 parts, 520 texts, order natural, Index type –)
- `Dictionnaire topographique du département des Vosges` (book, 412 parts, 411 texts, order natural, Index type book)
- `La France littéraire, ou Dictionnaire bibliographique` (book, 342 parts, 341 texts, order natural, Index type book)
- `Encyclopédie anarchiste` (book, 276 parts, 251 texts, order toc-partial, Index type book)
- `Dictionnaire historique, topographique et biographique de la Mayenne` (book, 189 parts, 184 texts, order natural, Index type book)
- `Dictionnaire de Trévoux/3e édition, 1732` (collection, 72 parts, 70 texts, order natural, Index type –)
- `Biographies de l’honorable Barthélemi Joliette et de M. le Grand vicaire A. Manseau` (book, 54 parts, 51 texts, order toc, Index type book)
- `Encyclopédie moderne` (collection, 51 parts, 46 texts, order natural, Index type –)

## D6 works excluded as index-faulty (109)

| page-level tier | proofread pages | works |
|---|---|---|
| unproofread | no level ≥ 3 | 43 |
| proofread | some level ≥ 3 | 37 |
| none | no level ≥ 3 | 16 |
| unproofread | some level ≥ 3 | 10 |
| validated | some level ≥ 3 | 3 |

12 random examples (raw progress code, page levels):

- `La Gaule francoise` — `MS` on `Hotman - La Gaule francoise, 1574.djvu`; pages L1: 44, L3: 8; tier by pages unproofread
- `Apologie pour Hérodote` — `MS` on `Estienne - Apologie pour Hérodote I, 1879.djvu`; pages L3: 56; tier by pages proofread
- `Histoire de la république de Florence` — `L` on `Allart - Histoire de la République de Florence.djvu`; pages L1: 4, L3: 2, L4: 2; tier by pages unproofread
- `Histoire de la ville de Bruxelles, tome 1, 1845` — `L` on `Henne A. Wauters A. - Histoire de la ville de Bruxelles, tome 1, 1845.djvu`; pages L0: 4, L3: 192; tier by pages proofread
- `Pénélope, tragédie. (1684)` — `OCR` on `Théâtre français, répertoire complet, tome 37, 1821.djvu`; pages L1: 55, L3: 1; tier by pages unproofread
- `Dictionnaire du patois des environs de Grenoble` — `MS` on `Dictionnaire du patois des environs de Grenoble - Albert Ravanat - 1911.pdf`; pages ; tier by pages None
- `Le prince Henri d’Orléans` — `OCR` on `Bulletin du Comité de l'Asie française, numéro 5, août 1901.pdf`; pages L1: 3; tier by pages unproofread
- `Nécrologie de M. Jean Jacques Rapet` — `L` on `Revue pédagogique, second semestre, 1882.djvu`; pages L1: 1, L3: 2; tier by pages unproofread
- `L’Ymagier (revue)/La Passion` — `OCR` on `L’Ymagier - n°1, 1894.djvu`; pages L3: 6; tier by pages proofread
- `Les Aventures de l'Abbé Prévost` — `L` on `Anatole France - Le Génie latin.djvu`; pages L1: 3, L3: 25; tier by pages unproofread
- `L’école normale et l’éducation professionnelle des élèves-maîtres` — `L` on `Revue pédagogique, second semestre, 1882.djvu`; pages L1: 19; tier by pages unproofread
- `L’Ymagier (revue)/LesMonstres` — `OCR` on `L’Ymagier - n°2, 1895.djvu`; pages L0: 2, L3: 8; tier by pages proofread

## D7 coverage of the Index's text pages

| coverage | works |
|---|---|
| 50–80 % | 159 |
| 80–90 % | 242 |
| 90–95 % | 310 |
| < 50 % | 143 |
| ≥ 95 % | 2052 |

Where the uncovered pages lie, for the 854 works under 95 %:

| case | works |
|---|---|
| only front/back matter uncovered | 616 |
| inner gaps ≥ 5 % (missing chapters) | 178 |
| inner gaps under 5 % | 60 |

Levels of the uncovered pages (all works under 95 %): L1: 8,745, L2: 11, L3: 18,934, L4: 7,095.
Works under 90%: 544.
Works under 80%: 302.
Works with inner coverage (between the first and last transcluded page) under 95 %: 178.

20 random works under 95 %:

- `Solution générale du problème de la photographie des couleurs` (single, 1 parts, 0 red links, validated): coverage 92% of 13 pages; uncovered front 1, inner 0, back 0; inner coverage 100%; uncovered levels L4: 1
- `Au pays des âmes` (single, 1 parts, 0 red links, validated): coverage 83% of 18 pages; uncovered front 3, inner 0, back 0; inner coverage 100%; uncovered levels L4: 3
- `La Volonté et la Fortune` (single, 1 parts, 0 red links, validated): coverage 93% of 29 pages; uncovered front 2, inner 0, back 0; inner coverage 100%; uncovered levels L4: 2
- `Les Transformations du droit. Étude sociologique` (subpages, 6 parts, 3 red links, proofread): coverage 52% of 215 pages; uncovered front 0, inner 104, back 0; inner coverage 52%; uncovered levels L1: 100, L3: 4
- `Le Premier Bouilleur` (subpages, 6 parts, 0 red links, validated): coverage 9% of 418 pages; uncovered front 179, inner 199, back 1; inner coverage 16%; uncovered levels L3: 198, L4: 181
- `Album d’un pessimiste` (single, 1 parts, 0 red links, proofread): coverage 70% of 64 pages; uncovered front 0, inner 0, back 19; inner coverage 100%; uncovered levels L1: 5, L3: 14
- `Un double aveu` (single, 1 parts, 0 red links, validated): coverage 88% of 16 pages; uncovered front 2, inner 0, back 0; inner coverage 100%; uncovered levels L4: 2
- `Westminster et le Château de Windsor` (single, 1 parts, 0 red links, validated): coverage 93% of 14 pages; uncovered front 1, inner 0, back 0; inner coverage 100%; uncovered levels L4: 1
- `Odes (Horace, Mondot)` (subpages, 21 parts, 0 red links, proofread): coverage 33% of 132 pages; uncovered front 0, inner 85, back 4; inner coverage 34%; uncovered levels L1: 28, L2: 1, L3: 60
- `Le Livre des morts des anciens Égyptiens` (subpages, 17 parts, 55 red links, proofread): coverage 81% of 77 pages; uncovered front 2, inner 11, back 2; inner coverage 85%; uncovered levels L1: 6, L3: 9
- `Madagascar` (single, 1 parts, 0 red links, validated): coverage 92% of 13 pages; uncovered front 1, inner 0, back 0; inner coverage 100%; uncovered levels L4: 1
- `La Cité antique, 1864` (subpages, 53 parts, 0 red links, proofread): coverage 94% of 527 pages; uncovered front 3, inner 29, back 0; inner coverage 94%; uncovered levels L4: 32
- `Mémoires (La Rochefoucauld)` (linked, 5 parts, 0 red links, unproofread): coverage 63% of 474 pages; uncovered front 3, inner 174, back 0; inner coverage 63%; uncovered levels L1: 171, L3: 3, L4: 3
- `Légendes slaves du moyen âge (1169–1237)` (subpages, 3 parts, 0 red links, proofread): coverage 95% of 91 pages; uncovered front 3, inner 1, back 1; inner coverage 99%; uncovered levels L1: 2, L3: 1, L4: 2
- `Le palais des thermes et l’hôtel de Cluny` (subpages, 4 parts, 0 red links, proofread): coverage 93% of 91 pages; uncovered front 3, inner 3, back 0; inner coverage 97%; uncovered levels L3: 2, L4: 4
- `Mackenzie King` (single, 1 parts, 9 red links, validated): coverage 12% of 8 pages; uncovered front 6, inner 0, back 1; inner coverage 100%; uncovered levels L1: 1, L3: 2, L4: 4
- `Le Messager Évangélique/1862` (subpages, 20 parts, 38 red links, unproofread): coverage 74% of 227 pages; uncovered front 2, inner 58, back 0; inner coverage 74%; uncovered levels L1: 36, L3: 24
- `Relation des revolutions arrivées a Siam, dans l'année 1688` (subpages, 1 parts, 1 red links, proofread): coverage 42% of 12 pages; uncovered front 0, inner 0, back 7; inner coverage 100%; uncovered levels L1: 5, L3: 2
- `Le Marquis de Pomenars` (single, 1 parts, 0 red links, proofread): coverage 94% of 52 pages; uncovered front 3, inner 0, back 0; inner coverage 100%; uncovered levels L3: 1, L4: 2
- `Histoire de la ville d’Agde depuis sa fondation` (subpages, 3 parts, 2 red links, unproofread): coverage 75% of 40 pages; uncovered front 2, inner 8, back 0; inner coverage 79%; uncovered levels L1: 6, L3: 4

## D8 searchable texts whose page title segment is a chapter label

Searchable texts (reference works excluded): 156,498; with a chapter-like page title segment: 56,665 (36.2 %).

| work type | texts demoted |
|---|---|
| book | 49,274 |
| collection | 5,111 |
| periodical-issue | 2,211 |
| document | 69 |

| Index type (raw) | texts demoted |
|---|---|
| book | 48,711 |
| collection | 4,862 |
| journal | 2,198 |
| (none) | 869 |
| phdthesis | 25 |

30 random texts the page-title rule would demote to chapters:

- “Les carrières de Gentilly” (`Sans famille/Édition Thieme, 1902/10`) in `Sans famille/Édition Thieme, 1902` (book, Index type book)
- “Nouvelle blessure et ancienne blessure” (`David Copperfield (Traduction Pichot)/Troisième partie/Chapitre 16`) in `David Copperfield (Traduction Pichot)` (book, Index type book)
- “Ce que Michel n’avait pas prévu” (`Michel et Josephte dans la tourmente/03`) in `Michel et Josephte dans la tourmente` (book, Index type book)
- “Dénonciation” (`Les Mystères de Paris/Partie V/1`) in `Les Mystères de Paris/Partie V` (book, Index type book)
- “Les ambulances des théâtres. — Le dévouement des artistes improvisées infirmières. — Ambulances du Théâtre-Français, de l’Odéon, de la Porte-Saint-Martin, des Variétés. — L’ambulance d’Agar” (`Le Théâtre pendant les jours du Siège et de la Commune/VII`) in `Le Théâtre pendant les jours du Siège et de la Commune` (book, Index type book)
- “Le conseiller Tronchin.” (`Correspondance de Voltaire/1756/Lettre 3104`) in `Correspondance de Voltaire` (collection, Index type collection)
- “Tristesse et pitié” (`Stello/XIX`) in `Stello` (book, Index type book)
- “Billet à la même, 1696” (`Œuvres mêlées de Saint-Évremond/Correspondance/77`) in `Œuvres mêlées de Saint-Évremond` (collection, Index type collection)
- “Pioche et truelle” (`De la Terre à la Lune/Chapitre 14`) in `De la Terre à la Lune` (book, Index type book)
- “II.IV. — Organes de la Fructification” (`La Plante/Partie II, chapitre IV`) in `La Plante` (book, Index type book)
- “L’intérieur des mousquetaires” (`Les Trois Mousquetaires/Chapitre 7`) in `Les Trois Mousquetaires` (book, Index type book)
- “CHAP. CCXXVII” (`Tableau de Paris/227`) in `Tableau de Paris` (book, Index type book)
- “Chap. xx.” (`Les Chroniques de Sire Jean Froissart/Livre III/Chapitre XX`) in `Les Chroniques de Sire Jean Froissart` (book, Index type book)
- “Le Retour” (`L’Âne mort et la femme guillotinée/XVII`) in `L’Âne mort et la femme guillotinée` (book, Index type book)
- “L’Étau” (`Jack/XIII`) in `Jack` (book, Index type book)
- “Le pacte” (`Les mystères de Montréal/XIX`) in `Les mystères de Montréal` (book, Index type book)
- “Chap. X.” (`Œuvres de Champlain/Tome II/Chapitre X`) in `Œuvres de Champlain` (book, Index type –)
- “1 Chroniques” (`Bible Segond 1910/Premier livre des Chroniques`) in `Bible Segond 1910` (book, Index type book)
- “— XXIII.” (`Examen important de Milord Bolingbroke/Édition Garnier/Chapitre 23`) in `Œuvres complètes de Voltaire` (collection, Index type collection)
- “LE DOCTEUR SAMUEL À FRANÇOIS DECONIHOUT” (`Marius Grout - Le vent se lève/3`) in `Le vent se lève` (book, Index type book)
- “Un gouffre qui se creuse” (`L’espion des habits rouges/04`) in `L’espion des habits rouges` (book, Index type book)
- “De Connebert” (`Recueil général et complet des fabliaux des 13e et 14e siècles/CXXVIII`) in `Recueil général et complet des fabliaux des 13e et 14e siècles` (collection, Index type collection)
- “La culotte en jersey de soie” (`La Culotte en jersey de soie/3`) in `La Culotte en jersey de soie` (book, Index type book)
- “Histoire de la Bois-Laurier” (`Thérèse philosophe/2`) in `Thérèse philosophe` (book, Index type book)
- “À Philippe Leparfait” (`Correspondance de Gustave Flaubert/Tome 6/1327`) in `Correspondance de Gustave Flaubert` (book, Index type book)
- “La Réforme judiciaire : II. Les Juges élus et les juges inamovibles” (`La Russie et les Russes/23`) in `La Russie et les Russes` (periodical-issue, Index type journal)
- “À l’office” (`Manuel de civilité pour les petites filles à l’usage des maisons d’éducation/03`) in `Manuel de civilité pour les petites filles à l’usage des maisons d’éducation` (book, Index type book)
- “À Claudius Popelin” (`Correspondance de Gustave Flaubert/Tome 6/1139`) in `Correspondance de Gustave Flaubert` (book, Index type book)
- “— Clément Boulanger.” (`Mes Mémoires (A. Dumas)/09/11`) in `Mes Mémoires (Dumas)` (book, Index type book)
- “Chap. clxxxii.” (`Les Chroniques de Sire Jean Froissart/Livre I, Partie II/Chapitre CLXXXII`) in `Les Chroniques de Sire Jean Froissart` (book, Index type book)


# en: evidence for decisions 1–8 (2026-11-01)

Resolution and quality: 49 s; 57,148 works, 55,867 in the catalog.

## D1 unknown works (33,564)

Title heuristics (first match):

| kind | works |
|---|---|
| other | 16,010 |
| court case (' v. ') | 4,837 |
| proclamation | 3,646 |
| speech / address / remarks / briefing | 3,424 |
| public law / act / statute | 1,735 |
| executive order | 1,594 |
| UN resolution | 1,570 |
| treaty / convention / agreement | 402 |
| letter | 346 |

Header templates on the unknown works' root page:

| header | works |
|---|---|
| Header | 30,830 |
| (no header template) | 2,734 |

Single-page unknown works with a colon in the title: 542.
Multi-part unknown works: 7,589 with 121,347 parts.

## D2 parts table size by component (cap 2,000, reference works out)

| variant | rows | raw SQLite | gzipped | saving |
|---|---|---|---|---|
| as in the schema | 341,139 | 82.6 MB | 19.45 MB | 0 % |
| no per-part quality_pages | 341,139 | 80.1 MB | 18.87 MB | 3 % |
| page_title as suffix of the root title | 341,139 | 60.4 MB | 18.07 MB | 7 % |
| display_title blank when it equals the last title segment | 341,139 | 78.3 MB | 18.79 MB | 3 % |
| no per-part index_title | 341,139 | 71.6 MB | 18.89 MB | 3 % |
| no quality_pages + no index_title | 341,139 | 69.1 MB | 18.32 MB | 6 % |
| all four | 341,139 | 42.8 MB | 16.18 MB | 17 % |

Rows by work size (parts per work):

| parts per work | works | rows |
|---|---|---|
| 1–50 | 17,068 | 175,486 |
| 51–100 | 732 | 50,623 |
| 101–200 | 257 | 34,621 |
| 201–500 | 98 | 29,985 |
| 501–1000 | 25 | 16,814 |
| 1001–2000 | 24 | 33,610 |

Parts with a quality_pages JSON: 243,863 of 341,139.
Works table lower bound (structure and quality columns only, no authors, years, descriptions): 5.63 MB gzipped for 55,867 works; works_fts (titles only) 1.06 MB.

## D3 works over reference_threshold (2,000) without an Index type

| work | parts | texts | structure | order | depth | tier | type if chain/toc order → not reference |
|---|---|---|---|---|---|---|---|
| Alumni Oxonienses: the Members of the University of Oxford, 1715-1886 | 63,225 | 63,216 | subpages | natural | 1 | unknown | reference |
| 1911 Encyclopædia Britannica | 37,420 | 37,278 | subpages | natural | 2 | proofread | reference |
| Dictionary of National Biography, 1885-1900 | 28,089 | 28,081 | mixed | natural | 2 | proofread | reference |
| Catholic Encyclopedia (1913) | 11,629 | 11,592 | subpages | natural | 2 | unproofread | reference |
| Popular Science Monthly | 8,174 | 7,613 | subpages | chain | 3 | proofread | collection/book |
| Nicene and Post-Nicene Fathers: Series II | 7,099 | 1,389 | mixed | chain | 5 | unproofread | collection/book |
| Ante-Nicene Fathers | 5,616 | 1,017 | subpages | chain | 6 | unproofread | collection/book |
| Alumni Oxonienses: the Members of the University of Oxford, 1500-1714 | 5,317 | 5,312 | subpages | natural | 1 | unknown | reference |
| Nicene and Post-Nicene Fathers: Series I | 5,287 | 723 | subpages | chain | 5 | unproofread | collection/book |
| A Dictionary of Music and Musicians | 5,197 | 5,168 | subpages | natural | 2 | proofread | reference |
| A Naval Biographical Dictionary | 4,955 | 4,953 | subpages | toc-partial | 1 | validated | reference |
| United States Statutes at Large | 4,888 | 4,686 | mixed | toc-partial | 6 | proofread | reference |
| Encyclopædia Britannica, Ninth Edition | 3,435 | 3,371 | subpages | natural | 2 | unproofread | reference |
| The Encyclopedia Americana (1920) | 3,166 | 3,162 | subpages | natural | 1 | proofread | reference |
| Appletons' Cyclopædia of American Biography | 2,539 | 2,531 | mixed | natural | 1 | unproofread | reference |
| The Indian Biographical Dictionary (1915) | 2,494 | 2,488 | subpages | toc-partial | 1 | proofread | reference |
| Thom's Irish Who's Who | 2,378 | 2,378 | subpages | natural | 1 | unknown | reference |
| Collier's New Encyclopedia (1921) | 2,368 | 2,363 | subpages | natural | 2 | proofread | reference |
| Royal Naval Biography | 2,322 | 2,317 | subpages | toc-partial | 1 | proofread | reference |
| Who's Who in the Far East | 2,182 | 2,181 | subpages | toc | 1 | unproofread | collection/book |
| Easton's Bible Dictionary (1897) | 2,050 | 2,041 | subpages | natural | 1 | proofread | reference |

Reference works typed by the Index type: 14 (over the cap: 0).

Non-reference works with ≥ 50 parts whose title matches a reference-word regex (dictionar|encyclop|lexik|…): 44. Examples:

- `The New International Encyclopædia` (book, 10,866 parts, 10,864 texts, order natural, Index type book)
- `An Etymological Dictionary of the German Language` (book, 9,961 parts, 9,907 texts, order toc-partial, Index type book)
- `A Dictionary of the Targumim, the Talmud Babli and Yerushalmi, and the Midrashic Literature` (collection, 1,734 parts, 1,721 texts, order natural, Index type –)
- `A Short Biographical Dictionary of English Literature` (collection, 1,693 parts, 1,687 texts, order toc-partial, Index type –)
- `Dictionary of National Biography, 1912 supplement` (collection, 1,637 parts, 1,637 texts, order natural, Index type –)
- `Domestic Encyclopædia (1802)` (collection, 1,612 parts, 1,554 texts, order natural, Index type –)
- `The Dictionary of Australasian Biography` (book, 1,567 parts, 1,566 texts, order natural, Index type book)
- `A Cyclopaedia of Female Biography` (collection, 1,398 parts, 1,398 texts, order toc, Index type –)
- `Dictionary of Greek and Roman Biography and Mythology` (collection, 1,294 parts, 1,253 texts, order natural, Index type –)
- `1922 Encyclopædia Britannica` (collection, 1,215 parts, 1,209 texts, order natural, Index type –)
- `Dictionary of Greek and Roman Geography` (collection, 1,073 parts, 1,064 texts, order natural, Index type –)
- `Dictionary of National Biography, 1901 supplement` (collection, 1,040 parts, 1,040 texts, order scan-volumes, Index type –)
- `Dictionary of Christian Biography and Literature to the End of the Sixth Century` (book, 956 parts, 948 texts, order natural, Index type book)
- `A Dictionary of the Booksellers and Printers who Were at Work in England, Scotland and Ireland from 1641 to 1667` (book, 944 parts, 942 texts, order toc-partial, Index type book)
- `The Biographical Dictionary of America` (collection, 872 parts, 863 texts, order toc-partial, Index type –)

## D6 works excluded as index-faulty (141)

| page-level tier | proofread pages | works |
|---|---|---|
| proofread | some level ≥ 3 | 72 |
| unproofread | some level ≥ 3 | 45 |
| validated | some level ≥ 3 | 10 |
| unproofread | no level ≥ 3 | 9 |
| none | no level ≥ 3 | 5 |

12 random examples (raw progress code, page levels):

- `Proceedings of the Society of Antiquaries of Scotland` — `L` on `Proceedings of the Society of Antiquaries of Scotland - Volume 10.djvu`; pages L3: 6; tier by pages proofread
- `The Gospel of Barnabas` — `MS` on `Ragg Gospel of Barnabas.djvu`; pages L4: 8; tier by pages validated
- `An Amiable Charlatan` — `L` on `An Amiable Charlatan (IA anamiablecharla00oppegoog).djvu`; pages L0: 1, L1: 301, L2: 2, L3: 7; tier by pages unproofread
- `Half-Hours With The Saints and Servants of God` — `L` on `HalfHoursWithTheSaints.djvu`; pages L1: 70, L3: 2, Lmissing: 8; tier by pages unproofread
- `Ora Maritima` — `OCR` on `Ora Maritima.djvu`; pages L3: 2, L4: 4; tier by pages proofread
- `History of the Civil War, 1861-1865` — `L` on `History of the Civil War (Rhodes 1917).pdf`; pages L3: 4, L4: 1; tier by pages proofread
- `Weekly List, National Register of Historic Places (June 13, 1986)` — `L` on `Weekly List 1986-06-13.pdf`; pages L2: 1, L3: 1; tier by pages unproofread
- `Commissioner of Police versus Cherif Abdoulaye alias Haidure` — `OCR` on `Commissioner of Police versus Cherif Abdoulaye alias Haidure.pdf`; pages L3: 1; tier by pages proofread
- `Washington, A Guide to the Evergreen State` — `L` on `Washington, A Guide to the Evergreen State.djvu`; pages L0: 5, L1: 26, L3: 6, L4: 3; tier by pages unproofread
- `Cane (Toomer)` — `L` on `Cane (Toomer).djvu`; pages L0: 1, L3: 244, L4: 1; tier by pages proofread
- `The Ciuile Wares Betweene the Howses of Lancaster and Yorke` — `OCR` on `The ciuile wares betweene the howses of Lancaster and Yorke (IA ciuilewaresbetwe00dani).pdf`; pages L0: 1, L3: 58, L4: 2, Lmissing: 10; tier by pages proofread
- `Minute of Dissent` — `L` on `Gurujadalu English.djvu`; pages L1: 108, L2: 1, L3: 21, Lmissing: 15; tier by pages unproofread

## D7 coverage of the Index's text pages

| coverage | works |
|---|---|
| 50–80 % | 21 |
| 80–90 % | 13 |
| 90–95 % | 24 |
| < 50 % | 29 |
| ≥ 95 % | 331 |

Where the uncovered pages lie, for the 87 works under 95 %:

| case | works |
|---|---|
| only front/back matter uncovered | 43 |
| inner gaps ≥ 5 % (missing chapters) | 32 |
| inner gaps under 5 % | 12 |

Levels of the uncovered pages (all works under 95 %): L1: 9,410, L2: 142, L3: 1,979, L4: 430.
Works under 90%: 63.
Works under 80%: 50.
Works with inner coverage (between the first and last transcluded page) under 95 %: 32.

20 random works under 95 %:

- `Rudimental Divine Science` (subpages, 1 parts, 0 red links, proofread): coverage 93% of 28 pages; uncovered front 2, inner 0, back 0; inner coverage 100%; uncovered levels L4: 2
- `Memoir of Anthony Norris Groves` (subpages, 3 parts, 24 red links, proofread): coverage 20% of 50 pages; uncovered front 0, inner 1, back 39; inner coverage 91%; uncovered levels L1: 1, L3: 39
- `Psychopathology of Everyday Life` (subpages, 5 parts, 9 red links, validated): coverage 73% of 85 pages; uncovered front 3, inner 3, back 17; inner coverage 95%; uncovered levels L1: 5, L3: 7, L4: 11
- `Indian Independence` (single, 1 parts, 0 red links, validated): coverage 94% of 35 pages; uncovered front 0, inner 0, back 2; inner coverage 100%; uncovered levels L2: 2
- `Paper and Its Uses` (subpages, 24 parts, 0 red links, validated): coverage 83% of 206 pages; uncovered front 3, inner 0, back 33; inner coverage 100%; uncovered levels L4: 36
- `Christmas Fireside Stories: Norwegian Folk & Fairy Tales` (subpages, 5 parts, 29 red links, proofread): coverage 9% of 301 pages; uncovered front 0, inner 180, back 94; inner coverage 13%; uncovered levels L1: 211, L2: 12, L3: 51
- `Flower children; the little cousins of the field and garden` (single, 1 parts, 0 red links, proofread): coverage 17% of 23 pages; uncovered front 1, inner 0, back 18; inner coverage 100%; uncovered levels L1: 2, L2: 3, L3: 1, L4: 13
- `Tacitus, The Histories (Fyfe)` (subpages, 2 parts, 7 red links, proofread): coverage 10% of 126 pages; uncovered front 2, inner 1, back 111; inner coverage 92%; uncovered levels L1: 2, L3: 91, L4: 21
- `The Grand Tour in the Eighteenth Century` (subpages, 10 parts, 7 red links, unproofread): coverage 87% of 263 pages; uncovered front 1, inner 30, back 2; inner coverage 88%; uncovered levels L1: 28, L3: 4, L4: 1
- `Sklar v. Commissioner of Internal Revenue` (subpages, 2 parts, 0 red links, proofread): coverage 93% of 14 pages; uncovered front 1, inner 0, back 0; inner coverage 100%; uncovered levels L3: 1
- `The Path to the Stars` (subpages, 5 parts, 26 red links, proofread): coverage 71% of 98 pages; uncovered front 3, inner 11, back 14; inner coverage 86%; uncovered levels L1: 7, L2: 16, L3: 4, L4: 1
- `Paradise Lost (1667)` (subpages, 11 parts, 0 red links, unproofread): coverage 87% of 114 pages; uncovered front 1, inner 1, back 13; inner coverage 99%; uncovered levels L1: 9, L3: 5, L4: 1
- `One of a Thousand` (subpages, 69 parts, 1123 red links, unproofread): coverage 10% of 713 pages; uncovered front 0, inner 640, back 0; inner coverage 10%; uncovered levels L1: 638, L3: 2
- `Taras Bulba` (subpages, 13 parts, 0 red links, proofread): coverage 94% of 262 pages; uncovered front 1, inner 14, back 0; inner coverage 95%; uncovered levels L4: 15
- `Altai-Himalaya, a Travel Diary` (linked, 4 parts, 0 red links, proofread): coverage 79% of 107 pages; uncovered front 5, inner 1, back 17; inner coverage 99%; uncovered levels L2: 17, L3: 1, L4: 5
- `Statesman's Year-Book 1921` (subpages, 3 parts, 140 red links, proofread): coverage 1% of 1604 pages; uncovered front 5, inner 1090, back 493; inner coverage 1%; uncovered levels L1: 1564, L2: 3, L3: 19, L4: 2
- `The Tragic Drama of the Greeks` (single, 1 parts, 0 red links, validated): coverage 17% of 41 pages; uncovered front 1, inner 0, back 33; inner coverage 100%; uncovered levels L1: 6, L3: 3, L4: 25
- `Above the Battlefield` (single, 1 parts, 0 red links, proofread): coverage 94% of 17 pages; uncovered front 1, inner 0, back 0; inner coverage 100%; uncovered levels L3: 1
- `Maps of Old London` (subpages, 8 parts, 0 red links, proofread): coverage 81% of 26 pages; uncovered front 0, inner 2, back 3; inner coverage 91%; uncovered levels L1: 1, L3: 4
- `The Idols` (single, 1 parts, 0 red links, proofread): coverage 92% of 13 pages; uncovered front 1, inner 0, back 0; inner coverage 100%; uncovered levels L3: 1

## D8 searchable texts whose page title segment is a chapter label

Searchable texts (reference works excluded): 234,173; with a chapter-like page title segment: 20,481 (8.7 %).

| work type | texts demoted |
|---|---|
| book | 14,575 |
| collection | 5,892 |
| document | 12 |
| periodical-issue | 2 |

| Index type (raw) | texts demoted |
|---|---|
| book | 14,194 |
| (none) | 6,221 |
| collection | 52 |
| phdthesis | 12 |
| journal | 2 |

30 random texts the page-title rule would demote to chapters:

- “Companions in Captivity” (`Notes of my Captivity in Russia/Chapter 4`) in `Notes of my Captivity in Russia` (book, Index type book)
- “An Anatomical Disquisition on the Motion of the Heart and Blood in Animals: Chapter III: Of the Motions of Arteries, as seen in the Dissection of Living Animals” (`The Works of William Harvey/An Anatomical Disquisition on the Motion of the Heart and Blood in Animals/Chapter 3`) in `The Works of William Harvey` (book, Index type book)
- “A Ride in a Stage Coach” (`Clotel/Chapter 22`) in `Clotel` (book, Index type book)
- “The Treasure Hunt—The Voice among the Trees” (`Treasure Island (1909)/Chapter 32`) in `Treasure Island (1909)` (book, Index type book)
- “In Which Pooh Goes Visiting and Gets Into a Tight Place” (`Winnie-the-Pooh (1961)/Chapter 2`) in `Winnie-the-Pooh (1961)` (book, Index type book)
- “Radharani, Chapter 6” (`Indira and Other Stories/Radharani/Chapter 6`) in `Indira and Other Stories` (book, Index type book)
- “CCCLIX.&nbsp;Madison to Everett, June 3, 1827.” (`The Records of the Federal Convention of 1787/Volume 3/Appendix A/CCCLIX`) in `The Records of the Federal Convention of 1787` (collection, Index type –)
- “Sacred Books of the East: The Laws of Manus (Vol. XXV), Chapter I.” (`Sacred Books of the East/Volume 25/Laws of Manu/Chapter 1`) in `Sacred Books of the East` (book, Index type book)
- “Astika Parva — Section XXII” (`The Mahabharata/Book 1: Adi Parva/Section 22`) in `The Mahabharat` (collection, Index type –)
- “Khandava-daha Parva — Section CCXXXV” (`The Mahabharata/Book 1: Adi Parva/Section 235`) in `The Mahabharat` (collection, Index type –)
- “Title 6: The Judiciary Part III: Civil Proceedings Chapter 27: Survival” (`Kosrae State Code/Title 6/Part 3/Chapter 27`) in `Kosrae State Code` (collection, Index type –)
- “Supplements to the Third Book, Chapter 29” (`The World as Will and Idea/Volume 3/Chapter 29`) in `The World as Will and Idea` (book, Index type book)
- “The Life and Acts of St. Patrick by Jocelin, translated by Edmund L. Swift Chapter CLIV: A Stone is changed into Milk, and Milk is changed into Stones” (`The Most Ancient Lives of Saint Patrick/The Life and Acts of St. Patrick/Chapter 154`) in `The Most Ancient Lives of Saint Patrick` (book, Index type book)
- “Non-Co-operation Programme” (`The Wheel of Fortune/Chapter 2`) in `The Wheel of Fortune` (book, Index type book)
- “Vol. 2, Appendix” (`Life with the Esquimaux/Volume 2/Appendix`) in `Life with the Esquimaux` (collection, Index type –)
- “The Six and Twentieth Chapter: It is follie to referre Truth or Falsehood to our sufficiencie” (`Montaigne's Essays/Book I/Chapter XXVI`) in `Montaigne's Essays` (collection, Index type –)
- “THE LIVING HUITZILOPOCHTLI” (`The Plumed Serpent/Chapter 22`) in `The Plumed Serpent` (book, Index type book)
- “New France” (`Père Marquette/Chapter 5`) in `Père Marquette` (book, Index type book)
- “Episode 11: Sirens” (`Ulysses (1922)/Chapter 11`) in `Ulysses (1922)` (book, Index type book)
- “"The Path of Duty", Chapter 3” (`The Author of Beltraffio, Pandora, Georgina's Reasons, The Path of Duty, Four Meetings/The Path of Duty/Chapter 3`) in `The Author of Beltraffio, Pandora, Georgina's Reasons, The Path of Duty, Four Meetings` (book, Index type book)
- “Introductory and Historical” (`The Sexual Life of the Child/Chapter 1`) in `The Sexual Life of the Child` (collection, Index type –)
- “No. 20” (`The Rambler/20`) in `The Rambler` (collection, Index type –)
- “The Disappearing Safe” (`The Wheel of Death/Chapter 6`) in `The Wheel of Death` (collection, Index type –)
- “Don Quixote” (`Four and Twenty Minds/Chapter 20`) in `Four and Twenty Minds` (book, Index type book)
- “IV.—Embarkation of the German Army” (`America Fallen!/Chapter 4`) in `America Fallen!` (book, Index type book)
- “XIII.—The Gate in the Sky” (`The Snow Driver/Chapter 13`) in `The Snow Driver` (collection, Index type –)
- “The Life and Acts of St. Patrick by Jocelin, translated by Edmund L. Swift Chapter XIII: How Saint Patrick was Carried into Ireland” (`The Most Ancient Lives of Saint Patrick/The Life and Acts of St. Patrick/Chapter 13`) in `The Most Ancient Lives of Saint Patrick` (book, Index type book)
- “Students by date of entry - 1802” (`List of Carthusians, 1800–1879/1802`) in `List of Carthusians, 1800–1879` (book, Index type book)
- “At the Meeting of the Waters” (`Kopal-Kundala/Part 1/Chapter 1`) in `Kopal-Kundala` (book, Index type book)
- “May Day, Chapter 7” (`Tales of the Jazz Age/May Day/Chapter 7`) in `Tales of the Jazz Age` (book, Index type book)


# de: colon-prefix pages and their root pages

```

=== Topographia Hassiae: (208 members) root `Topographia Hassiae` exists=True toc_like=False
    `Topographia Hassiae`: 23704 B, own 3539, tags 0, refs 1, links 241, tpls ['Center', 'SeitePR', 'Textdaten']
      hdr {'container': '{{center|[[Topographia Germaniae]]}}', 'current': 'Topographia Hassiae', 'index': 'Topographia Hassiae', 'status': 'fertig', 'author': '&#x5B;Stiche: [[Matthäus Merian]] u.a.&#x5D;, Textautor: [[Martin Zeil…', 'year': '1655'}
      node: parent None rule own container None fail 'excluded' index 'Topographia Hassiae' toc 241 toc_source 'body-links' prev None next None
      links[:8] ['Topographia Germaniae', 'Matthäus Merian', 'Martin Zeiller', 'Topographia Hassiae: Beschreibung', 'Topographia Hassiae/Verzeichnis der Bilder', 'Topographia Hassiae: Allendorff an der Werra', 'Topographia Hassiae: Allendorff an der Lomb', 'Topographia Hassiae: Alßfeldt']
  pages linking members: `Topographia Hassiae` 206; `Topographia Germaniae/Register` 203; `Hessisches Hinterland` 6; `Unterfranken` 5; `Wittgenstein` 2; `Topographia Braunschweig Lüneburg: Münden` 1
    `Topographia Hassiae: Allendorff an der Lomb`: 408 B, own 238, tags 0, refs 3, links 0, tpls ['SeitePR', 'TopoEnd', 'TopoHassiaeArt']
      hdr {}
      node: parent None rule own container None fail None index None toc 0 toc_source '' prev None next None
    `Topographia Hassiae: Allendorff an der Werra`: 428 B, own 248, tags 0, refs 3, links 0, tpls ['SeitePR', 'TopoEnd', 'TopoHassiaeArt']
      hdr {}
      node: parent None rule own container None fail None index None toc 0 toc_source '' prev None next None
    `Topographia Hassiae: Idtstein`: 410 B, own 239, tags 0, refs 3, links 0, tpls ['SeitePR', 'TopoHassiaeArt', 'TopoHassiaeEnd']
      hdr {}
      node: parent None rule own container None fail None index None toc 0 toc_source '' prev None next None
  root has 1 subpages, e.g. ['Topographia Hassiae/Verzeichnis der Bilder']
    `Topographia Hassiae/Verzeichnis der Bilder`: 5229 B, own 4709, links to members 0, tpls []

=== Topographia Circuli Burgundici: (514 members) root `Topographia Circuli Burgundici` exists=True toc_like=False
    `Topographia Circuli Burgundici`: 52415 B, own 3950, tags 0, refs 1, links 518, tpls ['Center', 'HAAB', 'SeitePR', 'Textdaten']
      hdr {'container': '{{center|[[Topographia Germaniae]]}}', 'current': 'Topographia Germaniae Inferioris', 'index': 'Topographia Circuli Burgundici', 'status': 'fertig', 'author': 'Herausgeber:<br/>[[Matthäus Merian]]<br/>Textautor:<br/>[[Martin Zeill…', 'year': '1654'}
      node: parent None rule own container None fail 'excluded' index 'Topographia Circuli Burgundici' toc 518 toc_source 'body-links' prev None next None
      links[:8] ['Topographia Germaniae', 'Matthäus Merian', 'Martin Zeiller', 'Topographia Circuli Burgundici: Dedicatio', 'Topographia Circuli Burgundici: Anweisung für den Buchbinder', 'Topographia Circuli Burgundici: Vorrede', 'Topographia Circuli Burgundici: Neue Beschreibung Burgunds und der Niederlande', 'Topographia Circuli Burgundici/Verzeichnis der Bilder']
  pages linking members: `Topographia Circuli Burgundici` 508; `Topographia Germaniae/Register` 498; `Topographia Circuli Burgundici/Verzeichnis der Bilder` 7; `Historisch-Politisch-Geographischer Atlas der gantzen Welt` 1
    `Topographia Circuli Burgundici: Aelst`: 482 B, own 338, tags 0, refs 2, links 0, tpls ['BlockSatzEnd', 'BlockSatzStart', 'SeitePR', 'TopoMerian']
      hdr {}
      node: parent None rule own container None fail None index None toc 3 toc_source 'scan-pages' prev None next None
    `Topographia Circuli Burgundici: Aeth`: 382 B, own 259, tags 0, refs 1, links 0, tpls ['BlockSatzEnd', 'BlockSatzStart', 'SeitePR', 'TopoMerian']
      hdr {}
      node: parent None rule own container None fail None index None toc 6 toc_source 'scan-pages' prev None next None
    `Topographia Circuli Burgundici: Iussey`: 399 B, own 271, tags 0, refs 1, links 0, tpls ['BlockSatzEnd', 'BlockSatzStart', 'SeitePR', 'TopoMerian']
      hdr {}
      node: parent None rule own container None fail None index None toc 0 toc_source '' prev None next None
  root has 1 subpages, e.g. ['Topographia Circuli Burgundici/Verzeichnis der Bilder']
    `Topographia Circuli Burgundici/Verzeichnis der Bilder`: 1028 B, own 427, links to members 7, tpls []

=== Schwere, Elektricität und Magnetismus: (412 members) root `Schwere, Elektricität und Magnetismus` exists=True toc_like=True
    `Schwere, Elektricität und Magnetismus`: 37106 B, own 3957, tags 0, refs 0, links 522, tpls ['Prettytable', 'Projektfortschritt', 'SORTIERUNG:Schwere Elektricitat und Magnetismus', 'Textdaten']
      hdr {'container': 'off', 'current': 'Schwere, Elektricität und Magnetismus', 'status': 'fertig', 'author': '[[Bernhard Riemann]]', 'year': '1880', 'toc_marker': '1'}
      node: parent None rule own container None fail None index None toc 522 toc_source 'body-links' prev None next None
      links[:8] ['Schwere, Elektricität und Magnetismus/Zusammenstellungen', 'Schwere, Elektricität und Magnetismus:019', 'Schwere, Elektricität und Magnetismus/§. 1.', 'Schwere, Elektricität und Magnetismus/§. 2.', 'Schwere, Elektricität und Magnetismus/§. 3.', 'Schwere, Elektricität und Magnetismus/§. 4.', 'Schwere, Elektricität und Magnetismus/§. 5.', 'Schwere, Elektricität und Magnetismus/§. 6.']
  pages linking members: `Schwere, Elektricität und Magnetismus` 412; `Schwere, Elektricität und Magnetismus/Zweiter Abschnitt` 81; `Schwere, Elektricität und Magnetismus/Erster Abschnitt` 66; `Schwere, Elektricität und Magnetismus/§. 26.` 16; `Schwere, Elektricität und Magnetismus/§. 21.` 8; `Schwere, Elektricität und Magnetismus/§. 27.` 8
    `Schwere, Elektricität und Magnetismus:001`: 105 B, own 30, tags 0, refs 0, links 0, tpls ['Bernhard Riemann - Schwere, Elektricität und Magnetismus', 'WsRed']
      hdr {}
      node: parent None rule own container None fail None index None toc 0 toc_source '' prev None next None
    `Schwere, Elektricität und Magnetismus:002`: 135 B, own 55, tags 0, refs 0, links 0, tpls ['Bernhard Riemann - Schwere, Elektricität und Magnetismus']
      hdr {}
      node: parent None rule own container None fail None index None toc 0 toc_source '' prev None next None
    `Schwere, Elektricität und Magnetismus:207`: 2723 B, own 1397, tags 0, refs 0, links 0, tpls ['Bernhard Riemann - Schwere, Elektricität und Magnetismus', 'Idt2', 'MathForm1', 'PageDef2']
      hdr {}
      node: parent None rule own container None fail None index None toc 0 toc_source '' prev None next None
  root has 124 subpages, e.g. ['Schwere, Elektricität und Magnetismus/Achter Abschnitt.', 'Schwere, Elektricität und Magnetismus/Dritter Abschnitt', 'Schwere, Elektricität und Magnetismus/Erster Abschnitt']
    `Schwere, Elektricität und Magnetismus/Achter Abschnitt.`: 2254 B, own 132, links to members 0, tpls ['PageDef2', 'References']
    `Schwere, Elektricität und Magnetismus/Dritter Abschnitt`: 2062 B, own 127, links to members 0, tpls ['PageDef2']

=== Allgemeines Deutsches Kommersbuch: (385 members) root `Allgemeines Deutsches Kommersbuch` exists=True toc_like=True
    `Allgemeines Deutsches Kommersbuch`: 23311 B, own 3216, tags 0, refs 0, links 387, tpls ['Prettytable', 'Projektfortschritt', 'SperrSchrift', 'Textdaten']
      hdr {'container': 'off', 'current': 'Allgemeines Deutsches Kommersbuch', 'status': 'fertig', 'author': 'Schauenburg', 'year': 'zwischen 1896 und 1898', 'toc_marker': '1'}
      node: parent None rule own container None fail None index None toc 386 toc_source 'body-links' prev None next None
      links[:8] ['Allgemeines Deutsches Kommersbuch/Inhalt nach Seiten', 'Allgemeines Deutsches Kommersbuch:227', 'Herzeleid', 'Allgemeines Deutsches Kommersbuch:p1', 'Allgemeines Deutsches Kommersbuch:p2', 'Allgemeines Deutsches Kommersbuch:p3', 'Allgemeines Deutsches Kommersbuch:p4', 'Allgemeines Deutsches Kommersbuch:p5']
  pages linking members: `Allgemeines Deutsches Kommersbuch` 385; `Allgemeines Deutsches Kommersbuch/Inhalt nach Seiten` 355; `Allgemeines Deutsches Kommersbuch/Inhalt alphabethisch` 354; `Allgemeines Deutsches Kommersbuch/Liedverzeichnis` 354; `Joseph Victor von Scheffel` 23; `Herbst` 2
    `Allgemeines Deutsches Kommersbuch:1`: 4153 B, own 2921, tags 0, refs 0, links 0, tpls ['ADKsubtitel', 'ADKtitel', 'Allgemeines Deutsches Kommersbuch', 'Idt', 'LeereSeite', 'References']
      hdr {}
      node: parent None rule own container None fail None index None toc 0 toc_source '' prev None next None
    `Allgemeines Deutsches Kommersbuch:10`: 4067 B, own 2979, tags 0, refs 0, links 1, tpls ['ADKcenter', 'ADKsubtitel', 'ADKtitel', 'AlR', 'Allgemeines Deutsches Kommersbuch', 'Idt']
      hdr {}
      node: parent None rule own container None fail None index None toc 1 toc_source 'body-links' prev None next None
    `Allgemeines Deutsches Kommersbuch:272`: 1582 B, own 1158, tags 0, refs 0, links 0, tpls ['ADKsubtitel', 'ADKtitel', 'AlR', 'Allgemeines Deutsches Kommersbuch', 'Idt', 'Seite']
      hdr {}
      node: parent None rule own container None fail None index None toc 0 toc_source '' prev None next None
  root has 3 subpages, e.g. ['Allgemeines Deutsches Kommersbuch/Inhalt alphabethisch', 'Allgemeines Deutsches Kommersbuch/Inhalt nach Seiten', 'Allgemeines Deutsches Kommersbuch/Liedverzeichnis']
    `Allgemeines Deutsches Kommersbuch/Inhalt alphabethisch`: 109778 B, own 7900, links to members 354, tpls []
    `Allgemeines Deutsches Kommersbuch/Inhalt nach Seiten`: 104465 B, own 2533, links to members 355, tpls []

=== Die Siegel der Deutschen Kaiser und Könige: (292 members) root `Die Siegel der Deutschen Kaiser und Könige` exists=True toc_like=True
    `Die Siegel der Deutschen Kaiser und Könige`: 33248 B, own 2343, tags 0, refs 0, links 43, tpls ['Prettytable', 'Projektfortschritt', 'SORTIERUNG:Siegel der Deutschen Kaiser und Konige #Die', 'Textdaten']
      hdr {'container': 'off', 'current': 'Die Siegel der Deutschen Kaiser und Könige', 'status': 'unvollständig (geplant)', 'author': '[[Otto Posse]]', 'year': '1913', 'toc_marker': '1'}
      node: parent None rule own container None fail None index None toc 43 toc_source 'body-links' prev None next None
      links[:8] ['Die Siegel der deutschen Kaiser und Könige Band 1', 'Die Siegel der deutschen Kaiser und Könige Band 1/Übersicht über die Siegeltafeln', 'Die Siegel der deutschen Kaiser und Könige Band 1/Stammtafel 1', 'Die Siegel der deutschen Kaiser und Könige Band 1/Stammtafel 2', 'Die Siegel der deutschen Kaiser und Könige Band 1/Stammtafel 3', 'Die Siegel der deutschen Kaiser und Könige Band 1/Stammtafel 4', 'Die Siegel der deutschen Kaiser und Könige Band 1/Stammtafel 5', 'Die Siegel der deutschen Kaiser und Könige Band 1/Stammtafel 6']
  pages linking members: `Die Siegel der Deutschen Kaiser und Könige` 15; `Maximilian I.` 2
    `Die Siegel der Deutschen Kaiser und Könige:Band 2:Seite 1`: 9683 B, own 435, tags 0, refs 0, links 21, tpls ['PosseSiegel']
      hdr {}
      node: parent None rule own container None fail None index None toc 21 toc_source 'body-links' prev None next None
    `Die Siegel der Deutschen Kaiser und Könige:Band 2:Seite 10`: 3250 B, own 2491, tags 0, refs 0, links 0, tpls ['PosseSiegel']
      hdr {}
      node: parent None rule own container None fail None index None toc 0 toc_source '' prev None next None
    `Die Siegel der Deutschen Kaiser und Könige:Band 3:Tafel 20`: 250 B, own 86, tags 0, refs 0, links 1, tpls ['PosseSiegelB']
      hdr {}
      node: parent None rule own container None fail None index None toc 1 toc_source 'body-links' prev None next None

=== Die Ursache des Einschlagens vom Blitze: (165 members) root `Die Ursache des Einschlagens vom Blitze` exists=True toc_like=True
    `Die Ursache des Einschlagens vom Blitze`: 19557 B, own 5859, tags 0, refs 0, links 167, tpls ['ISSN', 'Prettytable', 'SORTIERUNG:Ursache des Einschlagens vom Blitze #Die', 'Textdaten']
      hdr {'container': 'off', 'current': 'Die Ursache des Einschlagens vom Blitze', 'status': 'fertig', 'author': '[[Johann Albert Heinrich Reimarus]]', 'year': '1769', 'toc_marker': '1'}
      node: parent None rule own container None fail None index None toc 167 toc_source 'body-links' prev None next None
      links[:8] ['Ausführliche Vorschriften zur Blitz-Ableitung an allerley Gebäuden', 'Die Ursache des Einschlagens vom Blitze/Zitierte Werke', 'Die Ursache des Einschlagens vom Blitze/Personenregister', 'Die Ursache des Einschlagens vom Blitze/Zeittafel', 'Die Ursache des Einschlagens vom Blitze:Seite 1', 'Die Ursache des Einschlagens vom Blitze:Seite 3', 'Die Ursache des Einschlagens vom Blitze:Seite 4', 'Die Ursache des Einschlagens vom Blitze:Seite 5']
  pages linking members: `Die Ursache des Einschlagens vom Blitze` 163; `Die Ursache des Einschlagens vom Blitze/Personenregister` 83; `Die Ursache des Einschlagens vom Blitze/Zitierte Werke` 47
    `Die Ursache des Einschlagens vom Blitze:Nachwort`: 215 B, own 24, tags 0, refs 0, links 0, tpls ['EinschlagendeBlitzeKapitel', 'PageDefEinzV1']
      hdr {}
      node: parent None rule own container None fail None index None toc 0 toc_source '' prev None next None
    `Die Ursache des Einschlagens vom Blitze:Seite 1`: 587 B, own 273, tags 0, refs 0, links 0, tpls ['EinschlagendeBlitze']
      hdr {}
      node: parent None rule own container None fail None index None toc 0 toc_source '' prev None next None
    `Die Ursache des Einschlagens vom Blitze:Seite 58`: 2803 B, own 2075, tags 0, refs 0, links 0, tpls ['EinschlagendeBlitze']
      hdr {}
      node: parent None rule own container None fail None index None toc 0 toc_source '' prev None next None
  root has 8 subpages, e.g. ['Die Ursache des Einschlagens vom Blitze/Personenregister', 'Die Ursache des Einschlagens vom Blitze/Register/Anson', 'Die Ursache des Einschlagens vom Blitze/Register/Franklin']
    `Die Ursache des Einschlagens vom Blitze/Personenregister`: 16284 B, own 4226, links to members 83, tpls ['Kapitaelchen']
    `Die Ursache des Einschlagens vom Blitze/Register/Anson`: 279 B, own 137, links to members 0, tpls []

=== Elektrische Kraft Hertz: (312 members) root `Elektrische Kraft Hertz` exists=False toc_like=False
    Elektrische Kraft Hertz: NO PAGE (excluded? None)
  pages linking members: `Untersuchungen über die Ausbreitung der elektrischen Kraft` 312
    `Elektrische Kraft Hertz:001`: 1891 B, own 1417, tags 0, refs 0, links 0, tpls ['Elektrische Kraft Hertz', 'Idt', 'Line', 'LineCenterSize', 'PageDef', 'SperrSchrift']
      hdr {}
      node: parent None rule own container None fail None index None toc 0 toc_source '' prev None next None
    `Elektrische Kraft Hertz:002`: 2547 B, own 2008, tags 0, refs 0, links 0, tpls ['Elektrische Kraft Hertz', 'Idt', 'PageDef', 'Seite', 'SperrSchrift']
      hdr {}
      node: parent None rule own container None fail None index None toc 0 toc_source '' prev None next None
    `Elektrische Kraft Hertz:157`: 353 B, own 69, tags 0, refs 0, links 0, tpls ['Elektrische Kraft Hertz', 'PageDef', 'Seite']
      hdr {}
      node: parent None rule own container None fail None index None toc 0 toc_source '' prev None next None

=== Die Schedelsche Weltchronik (deutsch): (298 members) root `Die Schedelsche Weltchronik (deutsch)` exists=False toc_like=False
    Die Schedelsche Weltchronik (deutsch): NO PAGE (excluded? None)
  pages linking members: `Schedel’sche Weltchronik` 298; `Judaica` 6; `Die Mordgrube zu Freiberg` 2; `Dante Alighieri` 1; `Bamberg` 1; `Augsburg` 1
    `Die Schedelsche Weltchronik (deutsch):001`: 17840 B, own 9984, tags 0, refs 0, links 75, tpls ['Anker', 'Die Schedelsche Weltchronik (deutsch)', 'Ok']
      hdr {}
      node: parent None rule own container None fail None index None toc 75 toc_source 'body-links' prev None next None
    `Die Schedelsche Weltchronik (deutsch):002`: 4009 B, own 2826, tags 0, refs 0, links 0, tpls ['Die Schedelsche Weltchronik (deutsch)']
      hdr {}
      node: parent None rule own container None fail None index None toc 0 toc_source '' prev None next None
    `Die Schedelsche Weltchronik (deutsch):150`: 10166 B, own 7555, tags 0, refs 0, links 0, tpls ['Die Schedelsche Weltchronik (deutsch)']
      hdr {}
      node: parent None rule own container None fail None index None toc 0 toc_source '' prev None next None

=== Med. Topographie Gmuend: (150 members) root `Med. Topographie Gmuend` exists=False toc_like=False
    Med. Topographie Gmuend: NO PAGE (excluded? None)
  pages linking members: `Versuch einer medizinischen Topographie der Stadt Gmünd` 150; `Bier` 1
    `Med. Topographie Gmuend:001`: 750 B, own 311, tags 0, refs 0, links 0, tpls ['DefaultTextTable', 'LineCenterSize', 'VMTGSeite']
      hdr {}
      node: parent None rule own container None fail None index None toc 0 toc_source '' prev None next None
    `Med. Topographie Gmuend:002`: 666 B, own 501, tags 0, refs 0, links 0, tpls ['DefaultTextTable', 'Seite', 'VMTGSeite']
      hdr {}
      node: parent None rule own container None fail None index None toc 0 toc_source '' prev None next None
    `Med. Topographie Gmuend:076`: 3318 B, own 2710, tags 0, refs 0, links 0, tpls ['DefaultTextTable', 'Seite', 'VMTGSeite']
      hdr {}
      node: parent None rule own container None fail None index None toc 0 toc_source '' prev None next None

=== Stammbuch berümpter Fürsten, Graffen vnnd Herren Geschlechter: (128 members) root `Stammbuch berümpter Fürsten, Graffen vnnd Herren Geschlechter` exists=False toc_like=False
    Stammbuch berümpter Fürsten, Graffen vnnd Herren Geschlechter: NO PAGE (excluded? None)
  pages linking members: `Stammbuch Oder Erzölung aller namhaffter vnd inn Teütschen Historien berümpter Fürsten, Graffen, vnnd Herren Geschlechter` 128; `Burg Plesse` 1
    `Stammbuch berümpter Fürsten, Graffen vnnd Herren Geschlechter:Register 1`: 8476 B, own 2583, tags 0, refs 0, links 26, tpls ['LineCenterSize', 'Stammbuch Hoppenrod']
      hdr {}
      node: parent None rule own container None fail None index None toc 26 toc_source 'body-links' prev None next None
    `Stammbuch berümpter Fürsten, Graffen vnnd Herren Geschlechter:Register 2`: 9584 B, own 2921, tags 0, refs 0, links 31, tpls ['Stammbuch Hoppenrod']
      hdr {}
      node: parent None rule own container None fail None index None toc 31 toc_source 'body-links' prev None next None
    `Stammbuch berümpter Fürsten, Graffen vnnd Herren Geschlechter:Seite 57`: 2641 B, own 1932, tags 0, refs 0, links 0, tpls ['PageDefEinzV', 'Stammbuch Hoppenrod']
      hdr {}
      node: parent None rule own container None fail None index None toc 0 toc_source '' prev None next None

=== Eygentliche Beschreibung Aller Stände auff Erden: (122 members) root `Eygentliche Beschreibung Aller Stände auff Erden` exists=True toc_like=True
    `Eygentliche Beschreibung Aller Stände auff Erden`: 12034 B, own 606, tags 0, refs 0, links 122, tpls ['Prettytable', 'Textdaten']
      hdr {'container': 'off', 'current': 'Eygentliche Beschreibung aller Stände auff Erden, hoher und nidriger, …', 'status': 'fertig', 'author': '[[Hans Sachs]]', 'year': '1568', 'toc_marker': '1'}
      node: parent None rule own container None fail None index None toc 122 toc_source 'body-links' prev None next None
      links[:8] ['Eygentliche Beschreibung Aller Stände auff Erden:Titelblatt', 'Eygentliche Beschreibung Aller Stände auff Erden:Vorrede 1', 'Eygentliche Beschreibung Aller Stände auff Erden:Vorrede 2', 'Eygentliche Beschreibung Aller Stände auff Erden:Vorrede 3', 'Eygentliche Beschreibung Aller Stände auff Erden:Vorrede 4', 'Eygentliche Beschreibung Aller Stände auff Erden:Vorrede 5', 'Eygentliche Beschreibung Aller Stände auff Erden:Der Bapst', 'Eygentliche Beschreibung Aller Stände auff Erden:Der Cardinal']
  pages linking members: `Eygentliche Beschreibung Aller Stände auff Erden` 122; `Kleidung` 7; `Musik` 6; `Heilkunde` 5; `Waffen` 4; `Musikbelletristik` 3
    `Eygentliche Beschreibung Aller Stände auff Erden:Beschluß 1`: 1063 B, own 717, tags 0, refs 0, links 0, tpls ['LineCenterSize', 'Stände', 'Zeile']
      hdr {}
      node: parent None rule own container None fail None index None toc 0 toc_source '' prev None next None
    `Eygentliche Beschreibung Aller Stände auff Erden:Beschluß 2`: 632 B, own 278, tags 0, refs 0, links 1, tpls ['LineCenterSize', 'SperrSchrift', 'Stände', 'Zeile']
      hdr {}
      node: parent None rule own container None fail None index None toc 1 toc_source 'body-links' prev None next None
    `Eygentliche Beschreibung Aller Stände auff Erden:Der Müller`: 580 B, own 312, tags 0, refs 0, links 1, tpls ['Idt', 'LineCenterSize', 'Stände', 'Zeile']
      hdr {}
      node: parent None rule own container None fail None index None toc 1 toc_source 'body-links' prev None next None

=== Hantzsch: (390 members) root `Hantzsch` exists=False toc_like=False
    Hantzsch: NO PAGE (excluded? None)
  pages linking members: `Anton Graff` 1; `Königin Carola-Brücke` 1
    `Hantzsch:Aachener Straße`: 248 B, own 137, tags 0, refs 1, links 0, tpls ['BlockSatzEnd', 'BlockSatzStart', 'Namenbuch der Straßen und Plätze Dresdens', 'SeiteST']
      hdr {}
      node: parent 'Namenbuch der Straßen und Plätze Dresdens' rule toc container None fail None index 'Heft17-18VereinGeschichteDresden1905.pdf' toc 3 toc_source 'scan-pages' prev None next None
    `Hantzsch:Abdankeplatz`: 256 B, own 144, tags 0, refs 1, links 0, tpls ['BlockSatzEnd', 'BlockSatzStart', 'Namenbuch der Straßen und Plätze Dresdens', 'SeiteST']
      hdr {}
      node: parent None rule own container None fail None index 'Heft17-18VereinGeschichteDresden1905.pdf' toc 4 toc_source 'scan-pages' prev None next None
    `Hantzsch:Eisenstuckstraße`: 255 B, own 144, tags 0, refs 1, links 0, tpls ['BlockSatzEnd', 'BlockSatzStart', 'Namenbuch der Straßen und Plätze Dresdens', 'SeiteST']
      hdr {}
      node: parent None rule own container None fail None index 'Heft17-18VereinGeschichteDresden1905.pdf' toc 3 toc_source 'scan-pages' prev None next None

=== Taufmatrikel Passau Sankt Stephan: (123 members) root `Taufmatrikel Passau Sankt Stephan` exists=False toc_like=False
    Taufmatrikel Passau Sankt Stephan: NO PAGE (excluded? None)
  pages linking members: `Taufmatrikel der Pfarrei Passau Sankt Stephan` 123; `Heiratsmatrikel Passau Sankt Stephan:Band 1:Seite 5` 12; `Heiratsmatrikel Passau Sankt Stephan:Band 1:Seite 9` 12; `Heiratsmatrikel Passau Sankt Stephan:Band 1:Seite 10` 9; `Heiratsmatrikel Passau Sankt Stephan:Band 1:Seite 2` 8; `Heiratsmatrikel Passau Sankt Stephan:Band 1:Seite 8` 6
    `Taufmatrikel Passau Sankt Stephan:Band 1:Seite 1`: 2058 B, own 1108, tags 0, refs 0, links 6, tpls ['Kommentar Matrikel', 'Ref', 'Taufmatrikel Passau Sankt Stephan']
      hdr {}
      node: parent None rule own container None fail None index None toc 6 toc_source 'body-links' prev None next None
    `Taufmatrikel Passau Sankt Stephan:Band 1:Seite 10`: 1425 B, own 896, tags 0, refs 0, links 2, tpls ['Kommentar Matrikel', 'Ref', 'Taufmatrikel Passau Sankt Stephan']
      hdr {}
      node: parent None rule own container None fail None index None toc 2 toc_source 'body-links' prev None next None
    `Taufmatrikel Passau Sankt Stephan:Band 1:Seite 43`: 2040 B, own 931, tags 0, refs 0, links 8, tpls ['Idt', 'Kommentar Matrikel', 'Ref', 'Taufmatrikel Passau Sankt Stephan']
      hdr {}
      node: parent None rule own container None fail None index None toc 8 toc_source 'body-links' prev None next None
```

# de: rule-3 extension simulation

```
  attached pages whose title does not start with the TOC page's title: 1,512; TOC pages: `Topographia Provinciarum Austriacarum` 434; `Untersuchungen über die Ausbreitung der elektrischen Kraft` 312; `Schedel’sche Weltchronik` 298; `Versuch einer medizinischen Topographie der Stadt Gmünd` 150; `Stammbuch Oder Erzölung aller namhaffter vnd inn Teütschen Historien berümpter Fürsten, Graffen, vnnd Herren Geschlechter` 128; `Taufmatrikel der Pfarrei Passau Sankt Stephan` 123; `Heiratsmatrikel der Pfarrei Passau Sankt Stephan` 55; `Monatsschrift für Pomologie und praktischen Obstbau, Band 1` 12
    `Elektrische Kraft Hertz:172` → `Untersuchungen über die Ausbreitung der elektrischen Kraft` (2585 B, refs 0, tpls ['Elektrische Kraft Hertz', 'Idt', 'Line', 'PageDef'])
    `Topographia Austriacarum: Ottenstein` → `Topographia Provinciarum Austriacarum` (400 B, refs 1, tpls ['BlockSatzEnd', 'BlockSatzStart', 'SeitePR', 'TopoMerian'])
    `Topographia Austriacarum: Alkhofen` → `Topographia Provinciarum Austriacarum` (385 B, refs 1, tpls ['BlockSatzEnd', 'BlockSatzStart', 'SeitePR', 'TopoMerian'])
    `Die Schedelsche Weltchronik (deutsch):257` → `Schedel’sche Weltchronik` (8772 B, refs 0, tpls ['An', 'Anno', 'Die Schedelsche Weltchronik (deutsch)'])
    `Heiratsmatrikel Passau Sankt Stephan:Band 1:Seite 23` → `Heiratsmatrikel der Pfarrei Passau Sankt Stephan` (1037 B, refs 0, tpls ['Heiratsmatrikel Passau Sankt Stephan'])
    `Topographia Austriacarum: Märenberg` → `Topographia Provinciarum Austriacarum` (360 B, refs 1, tpls ['BlockSatzEnd', 'BlockSatzStart', 'SeitePR', 'TopoMerian'])
    `Stammbuch berümpter Fürsten, Graffen vnnd Herren Geschlechter:Seite 61` → `Stammbuch Oder Erzölung aller namhaffter vnd inn Teütschen Historien berümpter Fürsten, Graffen, vnnd Herren Geschlechter` (2134 B, refs 0, tpls ['PageDefEinzV', 'Stammbuch Hoppenrod'])
    `Topographia Austriacarum: Feltzen` → `Topographia Provinciarum Austriacarum` (357 B, refs 1, tpls ['BlockSatzEnd', 'BlockSatzStart', 'SeitePR', 'TopoMerian'])
    `Topographia Austriacarum: Caldenatsch` → `Topographia Provinciarum Austriacarum` (378 B, refs 1, tpls ['BlockSatzEnd', 'BlockSatzStart', 'SeitePR', 'TopoMerian'])
    `Die Schedelsche Weltchronik (deutsch):125` → `Schedel’sche Weltchronik` (11372 B, refs 0, tpls ['Die Schedelsche Weltchronik (deutsch)'])
    `Topographia Austriacarum: Langenlois` → `Topographia Provinciarum Austriacarum` (403 B, refs 1, tpls ['BlockSatzEnd', 'BlockSatzStart', 'SeitePR', 'TopoMerian'])
    `Die Schedelsche Weltchronik (deutsch):017` → `Schedel’sche Weltchronik` (6004 B, refs 0, tpls ['Die Schedelsche Weltchronik (deutsch)'])
  not-bare singles listed by one TOC-like page, top TOC pages: `Ministerialamtsblatt Bayern` 185; `Deutsches Reichsgesetzblatt 1871` 153; `Deutsches Reichsgesetzblatt 1908` 152; `Deutsches Reichsgesetzblatt 1909` 146; `Bundesgesetzblatt des Norddeutschen Bundes 1870` 144; `Deutsches Reichsgesetzblatt 1872` 136; `Deutsches Reichsgesetzblatt 1907` 116; `Deutsches Reichsgesetzblatt 1898` 114; `Bundesgesetzblatt des Norddeutschen Bundes 1868` 113; `Bundesgesetzblatt des Norddeutschen Bundes 1869` 110; `Deutsches Reichsgesetzblatt 1900` 108; `Deutsches Reichsgesetzblatt 1899` 100; `Deutsches Reichsgesetzblatt 1906` 99; `Deutsches Reichsgesetzblatt 1902` 92; `Deutsches Reichsgesetzblatt 1903` 90
  all Topographia colon singles: 4179; listed by k TOC-like pages: {1: 4154, 0: 24, 2: 1}
    'Topographia Circuli Burgundici: Franeker' listed by ['Topographia Circuli Burgundici', 'Historisch-Politisch-Geographischer Atlas der gantzen Welt']

Works whose root header is only {Zeitschrift}: 0; single, without scans: 0; tiers {}; multi-part or scanned: 0 (tiers {})
Unknown works now 4,336; after attaching bare listed pages (2,300) and excluding Zeitschrift bibliographies (0): 2,036
  remaining unknown by header: [('Zeitschrift', 1772), ('(none)', 201), ('Textdaten', 51), ('Gesetzestext', 2)]
  - `Zeitschrift für die Entomologie` (single, 9372 B): Erster Band 1839 == = Inhalt des ersten Heftes I. Beiträge zu einer Monographie der Schildwanzen, vo
  - `Neue Militärische Blätter` (single, 2667 B): __TOC__ MDZ 5.1874 - 14.1879; 1894,Sem.2 - 1895; 1896,2 - 1900,1; 60.1901 - 74.1909; 78/79.1911 - 80
  - `Zeitschrift für das gesamte Getreidewesen` (single, 2717 B): __TOC__ 18.1931 - 19.1932 : Zeitschrift für das gesamte Getreide- und Mühlenwesen 20.1933 - 24.1937 
  - `Deutsche Politik - Wochenschrift` (single, 4650 B): __TOC__ Erschienen Berlin : Dt. Verl.-Ges. für Politik u. Geschichte Weimar ; Berlin : Kiepenheuer [
  - `Jahrbuch für Genealogie, Heraldik und Sphragistik` (single, 4062 B): __TOC__ Jg. 1893 (1894) = (1893-1894), = (1893-1897), , Jg. 1894 (1895) = (1893-1894), = (1893-1897)
  - `Die Siegel der Deutschen Kaiser und Könige:Band 2:Seite 4` (single, 3208 B): __NOTOC__ valign="top" style="text-align:justify; padding-left:10px; padding-right:10px;" | ROOlAnOR
  - `Merkur (Zeitschrift)` (single, 1042662 B): 1947, 1. Jahrgang == Heft 1, 1947, 1. Jahrgang === Hauptbeitrag Lessing, Gotthold Ephraim: Über eine
  - `Schau-ins-Land` (single, 144051 B): __TOC__ Schau-ins-Land: Jahresheft des Breisgau-Geschichtsvereins Schauinsland Uni Freiburg; Jahrgän
  - `Oberdeutsche Zeitschrift für Volkskunde` (single, 5391 B): __TOC__ Ohne VPN-USA bitte die fett hervorgehobenen Links zu den gespiegelten Digitalisaten auf Inte
  - `Die Blaue Bibliothek aller Nationen` (single, 3738 B): Inhaltsverzeichnis == Band 1 Charles Perrault (Feen-Mährchen) Rothkäppchen (S. 3) Die Feen (S. 7) Bl
  - `Göttinger Taschen Calender für das Jahr` (single, 6607 B): __TOC__ Bibliographische Informationen == Herausgeber 1776 - 1777 : Johann Christian Polykarp Erxleb
  - `Archiv für Kulturgeschichte` (single, 19954 B): __TOC__ Frei zugängliche Digitalisate der Bände ab Erscheinungsjahr 1929 werden ab 2025 auf Internet
  - `Neue Bienenzeitung` (single, 6777 B): __TOC__ 1.1902 - 10.1911 == 1.1902 = , (Wisconsin) (1) 2.1903 = (2+3), (Wisconsin) (2) 3.1904 = (2+3
  - `Ars medici : Schweizer Zeitschrift für Hausarztmedizin` (single, 4090 B): __TOC__ Bibliografische Informationen == erschienen 1.1911 - 15.1925 (?) Wien : Eigenverlag (Wien IX
  - `Gardeners' chronicle` (single, 83991 B): __TOC__ 1841 - 1843 (1st series) == Titel nur / title only The Gardeners' chronicle for ... keine Ba
  - `Sammlung der deutschen Abhandlungen` (single, 16208 B): Inhalt / Digitalisate == 1788-89 === Sammlung der deutschen Abhandlungen, welche in der Königlichen 
  - `Umrechnung Flächenmaaße Ober-Hessen (Großh. Hessen) (1819)` (single, 112518 B): Die nachstehende Tafel enthält die Größe der verschiedenen Morgen in der Provinz Ober-Hessen nach Qu
  - `Annalen des Vereins für Nassauische Altertumskunde und Geschichtsforschung` (single, 4018 B): __TOC__ Indizes / Registerbände == 1.1827/30–20.1888 21.1889–40.1913 41.1910/11–60.1943 61.1950–70.1
  - `Ausführliche Vorschriften zur Blitz-Ableitung an allerley Gebäuden:Seite 18` (single, 1904 B): width="100%" valign="top" style="text-align:justify; padding-left:10px; padding-right:10px;"| des Bl
  - `Kunstchronik und Kunstmarkt` (single, 1811 B): __TOC__ Erschienen Leipzig : E.A. Seemann Leipzig : Ernst Hedrich Nachf. G.M.B.H. Vereinigung von Ku
```

# Parts-table encodings

# en: parts table encodings (18,204 works)

| encoding | rows | raw SQLite (on device) | gzipped (download) |
|---|---|---|---|
| schema as written | 341,139 | 82.6 MB | 19.45 MB |
| index_title → integer into works.index_titles | 341,139 | 71.6 MB | 18.92 MB |
| + display_title NULL when = last segment | 341,139 | 67.3 MB | 18.25 MB |
| + no per-part quality_pages | 341,139 | 64.8 MB | 17.67 MB |
| + page_title as suffix | 341,139 | 42.8 MB | 16.19 MB |
| index int + blank display + suffix (keep quality_pages) | 341,139 | 45.2 MB | 16.77 MB |

# fr: parts table encodings (11,572 works)

| encoding | rows | raw SQLite (on device) | gzipped (download) |
|---|---|---|---|
| schema as written | 263,024 | 67.0 MB | 15.43 MB |
| index_title → integer into works.index_titles | 263,024 | 53.0 MB | 14.80 MB |
| + display_title NULL when = last segment | 263,024 | 50.9 MB | 14.44 MB |
| + no per-part quality_pages | 263,024 | 48.4 MB | 13.82 MB |
| + page_title as suffix | 263,024 | 33.0 MB | 12.71 MB |
| index int + blank display + suffix (keep quality_pages) | 263,024 | 35.5 MB | 13.34 MB |

# Refined coverage rules (R5–R8) on no, fr, en

## no


## fr

# fr: coverage rules over 2,906 works that are their Index's book

| rule | flagged | flagged with no other incompleteness reason |
|---|---|---|
| R0 total < 95 % (current) | 854 | 700 |
| R1 total < 90 % | 544 | 419 |
| R2 inner < 95 % or total < 50 % | 279 | 175 |
| R3 inner < 95 % or total < 80 % | 387 | 272 |
| R4 inner < 90 % or total < 50 % | 218 | 133 |
| R5 pages of other works excluded, < 95 % | 824 | 672 |
| R6 R5 and more than 3 pages uncovered | 439 | 305 |
| R7 R5 at 90 % | 503 | 380 |
| R8 R5 at 90 % and more than 3 pages uncovered | 308 | 194 |

Comparison rule: R6 R5 and more than 3 pages uncovered

R0-only (un-flagged by the comparison rule): 415 works; levels of their uncovered pages: L1: 380, L2: 1, L3: 5,544, L4: 3,052

15 random works that the comparison rule un-flags (sole reason coverage under R0):

- `La promesse du Seigneur` (single, 1 parts, 0 red links, proofread): total 91%, inner 100%, excl. other works 91% (2 pages); uncovered front 2, inner 0, back 0; levels L4: 2
- `Bai èfant` (single, 1 parts, 0 red links, validated): total 92%, inner 100%, excl. other works 92% (1 pages); uncovered front 0, inner 0, back 1; levels L4: 1
- `La Paix conquise` (single, 1 parts, 0 red links, validated): total 83%, inner 100%, excl. other works 83% (1 pages); uncovered front 1, inner 0, back 0; levels L4: 1
- `Le Vaillant Troubadour` (single, 1 parts, 0 red links, validated): total 40%, inner 100%, excl. other works 100% (0 pages); uncovered front 0, inner 0, back 3; levels L4: 3
- `Notes sur les études coréennes et japonaises` (single, 1 parts, 0 red links, validated): total 90%, inner 100%, excl. other works 90% (3 pages); uncovered front 3, inner 0, back 0; levels L4: 3
- `Batte les coqs` (single, 1 parts, 0 red links, proofread): total 94%, inner 94%, excl. other works 94% (1 pages); uncovered front 0, inner 1, back 0; levels L3: 1
- `Ordonnance de police de MM. les officiers et gouverneurs du Palais Royal` (single, 1 parts, 0 red links, proofread): total 93%, inner 100%, excl. other works 93% (1 pages); uncovered front 1, inner 0, back 0; levels L3: 1
- `Les Incendiaires` (single, 1 parts, 0 red links, validated): total 91%, inner 100%, excl. other works 91% (1 pages); uncovered front 1, inner 0, back 0; levels L4: 1
- `Melchior (Sand — illustré, Hetzel 1853)` (subpages, 4 parts, 0 red links, validated): total 3%, inner 100%, excl. other works 77% (3 pages); uncovered front 185, inner 0, back 128; levels L4: 313
- `Les Bancs de la promenade` (single, 1 parts, 0 red links, validated): total 75%, inner 100%, excl. other works 75% (2 pages); uncovered front 2, inner 0, back 0; levels L4: 2
- `Deux testaments inédits : Alexandre Scot, Jean-Jacques Bouchard` (single, 1 parts, 0 red links, validated): total 80%, inner 100%, excl. other works 80% (2 pages); uncovered front 2, inner 0, back 0; levels L4: 2
- `Le Mariage tel qu’il fut et tel qu’il est !` (single, 1 parts, 0 red links, validated): total 86%, inner 100%, excl. other works 86% (3 pages); uncovered front 3, inner 0, back 0; levels L4: 3
- `Une nouvelle source de l’Histoire` (single, 1 parts, 0 red links, validated): total 73%, inner 100%, excl. other works 73% (3 pages); uncovered front 3, inner 0, back 0; levels L4: 3
- `Le Japon, ses institutions, ses produits, ses relations avec l’Europe` (single, 1 parts, 0 red links, proofread): total 95%, inner 100%, excl. other works 95% (2 pages); uncovered front 2, inner 0, back 0; levels L3: 2
- `La Vision du grand canal royal des deux mers (1888)` (single, 1 parts, 0 red links, validated): total 82%, inner 100%, excl. other works 82% (3 pages); uncovered front 1, inner 0, back 2; levels L4: 3

15 random works that the comparison rule still flags with coverage as the sole reason:

- `Barzaz Breiz/1867` (single, 1 parts, 0 red links, unproofread): total 5%, inner 100%, excl. other works 5% (18 pages); uncovered front 1, inner 0, back 17; levels L1: 12, L3: 6
- `Camille Lemonnier (Bazalgette)` (subpages, 6 parts, 0 red links, proofread): total 91%, inner 93%, excl. other works 91% (6 pages); uncovered front 1, inner 5, back 0; levels L3: 6
- `Collection d’un amateur. Objets d’art de la Corée, de la Chine et du Japon` (single, 1 parts, 0 red links, proofread): total 2%, inner 100%, excl. other works 2% (105 pages); uncovered front 105, inner 0, back 0; levels L3: 105
- `Œuvres complètes (Tolstoï)/Tome XXVI/Appendice` (single, 1 parts, 0 red links, proofread): total 1%, inner 100%, excl. other works 40% (6 pages); uncovered front 495, inner 0, back 2; levels L3: 491, L4: 6
- `Sur l’apostasie de l’économie actuelle` (single, 1 parts, 0 red links, validated): total 70%, inner 100%, excl. other works 70% (10 pages); uncovered front 0, inner 0, back 10; levels L4: 10
- `Arminius ou les Frères ennemis` (single, 1 parts, 0 red links, unproofread): total 95%, inner 100%, excl. other works 95% (6 pages); uncovered front 6, inner 0, back 0; levels L1: 5, L3: 1
- `Une Rencontre au détachement` (single, 1 parts, 0 red links, proofread): total 11%, inner 100%, excl. other works 81% (10 pages); uncovered front 227, inner 0, back 137; levels L3: 336, L4: 28
- `Le Bonheur conjugal (trad. Bienstock)` (linked, 9 parts, 0 red links, proofread): total 43%, inner 96%, excl. other works 91% (15 pages); uncovered front 190, inner 6, back 0; levels L3: 172, L4: 24
- `Quatrevingt-treize` (subpages, 18 parts, 0 red links, proofread): total 95%, inner 97%, excl. other works 95% (26 pages); uncovered front 10, inner 15, back 1; levels L3: 12, L4: 14
- `Ode sur le temps` (single, 1 parts, 0 red links, validated): total 40%, inner 100%, excl. other works 40% (9 pages); uncovered front 1, inner 0, back 8; levels L1: 4, L3: 2, L4: 3
- `Essai sur le perfectionnement des arts chimiques en France` (subpages, 4 parts, 0 red links, validated): total 94%, inner 100%, excl. other works 94% (6 pages); uncovered front 6, inner 0, back 0; levels L4: 6
- `Les Amours secrètes de M. Mayeux` (single, 1 parts, 0 red links, validated): total 90%, inner 100%, excl. other works 90% (4 pages); uncovered front 4, inner 0, back 0; levels L4: 4
- `Ode au colonel Denfert-Rochereau` (single, 1 parts, 0 red links, validated): total 76%, inner 100%, excl. other works 76% (5 pages); uncovered front 5, inner 0, back 0; levels L4: 5
- `Hans, fantaisie allégorique pour tous les âges` (single, 1 parts, 0 red links, proofread): total 92%, inner 100%, excl. other works 92% (5 pages); uncovered front 3, inner 0, back 2; levels L3: 5
- `L’orfèvrerie française aux XVIIIe et XIXe siècles` (single, 1 parts, 0 red links, proofread): total 64%, inner 100%, excl. other works 64% (5 pages); uncovered front 4, inner 0, back 1; levels L1: 2, L3: 3

Default view now 30,793 works; the comparison rule would add 370 validated/proofread works to it.

## en

# en: coverage rules over 418 works that are their Index's book

| rule | flagged | flagged with no other incompleteness reason |
|---|---|---|
| R0 total < 95 % (current) | 87 | 43 |
| R1 total < 90 % | 63 | 24 |
| R2 inner < 95 % or total < 50 % | 46 | 13 |
| R3 inner < 95 % or total < 80 % | 62 | 23 |
| R4 inner < 90 % or total < 50 % | 39 | 7 |
| R5 pages of other works excluded, < 95 % | 84 | 40 |
| R6 R5 and more than 3 pages uncovered | 66 | 26 |
| R7 R5 at 90 % | 59 | 20 |
| R8 R5 at 90 % and more than 3 pages uncovered | 52 | 16 |

Comparison rule: R6 R5 and more than 3 pages uncovered

R0-only (un-flagged by the comparison rule): 21 works; levels of their uncovered pages: L1: 6, L2: 2, L3: 835, L4: 43

15 random works that the comparison rule un-flags (sole reason coverage under R0):

- `CollegeSource, Inc. v. AcademyOne, Inc.` (subpages, 1 parts, 0 red links, proofread): total 92%, inner 100%, excl. other works 92% (1 pages); uncovered front 1, inner 0, back 0; levels L3: 1
- `United States v. Approximately 64,695 Pounds of Shark Fins` (subpages, 1 parts, 0 red links, proofread): total 88%, inner 100%, excl. other works 88% (1 pages); uncovered front 1, inner 0, back 0; levels L3: 1
- `“Trench Town Rock”: Reggae Music, Landscape Inscription, and the Making of Place in Kingston, Jamaica` (single, 1 parts, 0 red links, validated): total 83%, inner 100%, excl. other works 83% (2 pages); uncovered front 0, inner 0, back 2; levels L4: 2
- `The Idols` (single, 1 parts, 0 red links, proofread): total 92%, inner 100%, excl. other works 92% (1 pages); uncovered front 1, inner 0, back 0; levels L3: 1
- `United States v. Kattar` (subpages, 1 parts, 0 red links, proofread): total 93%, inner 100%, excl. other works 93% (1 pages); uncovered front 1, inner 0, back 0; levels L3: 1
- `Pensions Act 1849` (subpages, 7 parts, 0 red links, proofread): total 50%, inner 100%, excl. other works 50% (1 pages); uncovered front 0, inner 0, back 1; levels L4: 1
- `Interim Report (TRC Canada)` (subpages, 7 parts, 0 red links, proofread): total 94%, inner 100%, excl. other works 94% (2 pages); uncovered front 1, inner 0, back 1; levels L4: 2
- `Indian Independence` (single, 1 parts, 0 red links, validated): total 94%, inner 100%, excl. other works 94% (2 pages); uncovered front 0, inner 0, back 2; levels L2: 2
- `Sklar v. Commissioner of Internal Revenue` (subpages, 2 parts, 0 red links, proofread): total 93%, inner 100%, excl. other works 93% (1 pages); uncovered front 1, inner 0, back 0; levels L3: 1
- `A father of women, and other poems` (subpages, 17 parts, 0 red links, validated): total 91%, inner 100%, excl. other works 91% (3 pages); uncovered front 3, inner 0, back 0; levels L4: 3
- `A Legend of Montrose` (subpages, 25 parts, 0 red links, proofread): total 60%, inner 100%, excl. other works 98% (4 pages); uncovered front 133, inner 0, back 0; levels L3: 130, L4: 3
- `Rudimental Divine Science` (subpages, 1 parts, 0 red links, proofread): total 93%, inner 100%, excl. other works 93% (2 pages); uncovered front 2, inner 0, back 0; levels L4: 2
- `Flying Saucers in East Germany` (single, 1 parts, 0 red links, proofread): total 75%, inner 100%, excl. other works 75% (1 pages); uncovered front 0, inner 0, back 1; levels L3: 1
- `Margaret Fuller (Hedge)` (single, 1 parts, 0 red links, proofread): total 2%, inner 100%, excl. other works 100% (0 pages); uncovered front 84, inner 0, back 254; levels L3: 338
- `The neutral territory of Moresnet` (single, 1 parts, 0 red links, validated): total 91%, inner 100%, excl. other works 91% (1 pages); uncovered front 1, inner 0, back 0; levels L4: 1

15 random works that the comparison rule still flags with coverage as the sole reason:

- `United Nations Convention on the Law of the Sea` (subpages, 27 parts, 0 red links, proofread): total 94%, inner 100%, excl. other works 94% (13 pages); uncovered front 0, inner 0, back 13; levels L3: 12, L4: 1
- `Christian Healing` (subpages, 1 parts, 0 red links, validated): total 79%, inner 100%, excl. other works 79% (6 pages); uncovered front 6, inner 0, back 0; levels L4: 6
- `Bible (Literal Standard Version)` (subpages, 73 parts, 0 red links, unproofread): total 68%, inner 68%, excl. other works 68% (6 pages); uncovered front 0, inner 6, back 0; levels L1: 3, L2: 3
- `The Children's Plutarch: Tales of the Romans` (subpages, 26 parts, 0 red links, validated): total 87%, inner 92%, excl. other works 87% (25 pages); uncovered front 1, inner 14, back 10; levels L4: 25
- `Telugu-English Dictionary` (subpages, 264 parts, 0 red links, unproofread): total 85%, inner 100%, excl. other works 85% (71 pages); uncovered front 0, inner 0, back 71; levels L1: 45, L3: 26
- `The cutters' practical guide to the cutting of ladies' garments` (single, 1 parts, 0 red links, proofread): total 79%, inner 100%, excl. other works 79% (28 pages); uncovered front 0, inner 0, back 28; levels L3: 25, L4: 3
- `Socrates (Voltaire)` (mixed, 11 parts, 0 red links, proofread): total 78%, inner 97%, excl. other works 93% (8 pages); uncovered front 29, inner 3, back 0; levels L1: 1, L3: 8, L4: 23
- `Folks from Dixie` (subpages, 12 parts, 0 red links, proofread): total 95%, inner 95%, excl. other works 95% (13 pages); uncovered front 0, inner 13, back 0; levels L3: 12, L4: 1
- `Paper and Its Uses` (subpages, 24 parts, 0 red links, validated): total 83%, inner 100%, excl. other works 83% (36 pages); uncovered front 3, inner 0, back 33; levels L4: 36
- `The Land of the Veda` (subpages, 14 parts, 0 red links, proofread): total 90%, inner 91%, excl. other works 90% (52 pages); uncovered front 2, inner 50, back 0; levels L3: 40, L4: 12
- `My Brilliant Career` (subpages, 40 parts, 0 red links, proofread): total 49%, inner 100%, excl. other works 49% (117 pages); uncovered front 0, inner 0, back 117; levels L1: 115, L3: 2
- `Gandhiism versus Socialism` (single, 1 parts, 0 red links, proofread): total 75%, inner 100%, excl. other works 75% (8 pages); uncovered front 5, inner 0, back 3; levels L3: 8
- `The Chess-Player's Text Book` (subpages, 15 parts, 0 red links, proofread): total 92%, inner 96%, excl. other works 92% (11 pages); uncovered front 0, inner 5, back 6; levels L1: 2, L3: 9
- `Altai-Himalaya, a Travel Diary` (linked, 4 parts, 0 red links, proofread): total 79%, inner 99%, excl. other works 79% (23 pages); uncovered front 5, inner 1, back 17; levels L2: 17, L3: 1, L4: 5
- `Letters from America` (subpages, 15 parts, 0 red links, proofread): total 92%, inner 92%, excl. other works 92% (15 pages); uncovered front 0, inner 15, back 0; levels L4: 15

Default view now 17,971 works; the comparison rule would add 17 validated/proofread works to it.

