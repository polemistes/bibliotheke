# no.wikisource: structure and quality survey (WP1a)

Generated 2026-10-31 by `python -I -m wscatalog.survey no` from `nowikisource-20261001-pages-articles.xml.bz2` (resolution and quality rules: PLAN.md 5.2 steps 1–1h and 5.4; config `pipeline/config/no.yaml`). Resolution took 1 s on the cached extract.

Input: 11,553 ns0 pages, 464 ns0 redirects, 123,509 scan pages, 1,320 Index pages, 59 pages with the `disambiguation` page prop.

## 1. Works

1,118 works resolved from 11,494 non-excluded ns0 pages; 1,069 enter the catalog after the hard exclusions (section 9). Multi-part works hold 10,410 parts, of which 6,523 are searchable texts.

| structure | works | parts | texts |
|---|---|---|---|
| single | 685 | 685 | 0 |
| subpages | 279 | 6,094 | 3,551 |
| linked | 82 | 1,646 | 1,390 |
| mixed | 38 | 2,542 | 1,554 |
| virtual | 34 | 128 | 28 |

| type (step 1e) | works |
|---|---|
| book | 642 |
| single | 241 |
| collection | 200 |
| periodical-issue | 25 |
| document | 10 |

Largest works by parts:

| work | structure | type | parts | texts | missing | rules of the parts |
|---|---|---|---|---|---|---|
| Norsk Haandlexikon for almennyttige Kundskaber | subpages | collection | 1,138 | 1,136 | 4438 | container 1136, hierarchy 2 |
| Det norske Folks Historie | mixed | book | 691 | 15 | 23 | container 683, hierarchy 8 |
| Historisk Tidsskrift | mixed | collection | 403 | 370 | 86 | container 390, hierarchy 13 |
| Den norske Proces | subpages | book | 321 | 6 | 16 | container 320, hierarchy 1 |
| Folkevennen | mixed | collection | 178 | 164 | 198 | container 161, hierarchy 17 |
| Norske Folke-Sagn | mixed | book | 168 | 167 | 0 | container 168 |
| Norsk Tidsskrift for Videnskab og Litteratur | mixed | collection | 163 | 147 | 27 | container 157, hierarchy 6 |
| Samlede Værker (Kielland) | mixed | book | 157 | 42 | 3 | container 151, hierarchy 6 |
| Den norske Kirkes Historie under Katholicismen | subpages | book | 126 | 2 | 0 | container 126 |
| Kong Christian den Femtes Norske Lov | subpages | book | 118 | 3 | 0 | container 118 |
| Norske Bygdesagn | linked | book | 99 | 99 | 0 | container 99 |
| Mauritz Hansens Noveller og Fortællinger | mixed | book | 96 | 35 | 6 | container 96 |
| Samlede dikt | linked | book | 94 | 92 | 0 | container 94 |
| I gamle Daagaa | mixed | book | 94 | 94 | 0 | container 94 |
| Udvalgte lyriske Digte | linked | collection | 94 | 94 | 0 | container 94 |

## 2. Exclusions (step 1)

| reason | pages |
|---|---|
| disambiguation | 59 |

## 3. Patterns A–I (WS§1)

|  | pattern | count |
|---|---|---|
| A | collection of root pages (`structure = linked`) | 82 works, 1,646 parts |
| B | mixed subpages and root pages (`mixed`) | 38 works, 2,542 parts |
| C | work with an oversized page (> oversized_bytes, transcluded scan pages included) | 11 works |
| D | missing root, virtual root (`virtual`) | 34 works, 128 parts |
| E | texts inside a subpage collection reached from a disambiguation or versions page | 33 parts (33 searchable); all searchable subpage texts: 3,683 |
| F | whole-text duplicate subpages (excluded) | 0 pages |
| G | reference works (type `reference`) | 0 works, 0 parts; 0 pages excluded by title prefix |
| H | deep nesting (parts at depth ≥ 2, i.e. three levels below the root) | 5 works |
| I | several editions: works with an other-versions template | 0 works; 0 versions pages excluded |

Tree depth of multi-part works (levels below the root): 1: 402, 2: 26, 3: 5.

## 4. Parent rules and order sources (steps 1b, 1c)

| parent rule | pages |
|---|---|
| container | 9,784 |
| own | 1,084 |
| hierarchy | 553 |
| toc | 73 |

| order source (nodes with ≥ 2 children) | nodes |
|---|---|
| toc | 398 |
| scan | 57 |
| chain | 14 |
| scan-volumes | 13 |
| toc-partial | 5 |
| natural | 4 |

`toc-partial` (implementation choice, see section 13): the TOC lists at least half of the children, which keep its order; the rest follow in natural order. 1 `natural` nodes have a TOC that lists fewer than half.

Root pages attached by their header's container link: 2,642; most frequent containers: `Norske Folke-Sagn` 160; `Norske Bygdesagn` 99; `Udvalgte lyriske Digte` 94; `Samlede dikt` 94; `I gamle Daagaa` 92; `Nye Digte` 56; `Nyere Digte` 51; `Blandkorn` 45; `Digte (Bull)` 44; `Segner fraa Bygdom IV` 44.

Pages attached by TOC membership (rule 3): 73; most frequent TOC pages: `Vestlandsviser` 29; `Martyra` 19; `Reisebilleder og Digte` 15; `Fra det mørke Fastland` 8; `Diktsamling` 1; `Nyere Digte` 1.

## 5. Container links that fail rule 1

188 of 10,367 pages whose header carries a container link (1.8 %) point to:

- outside-ns0: 65 (e.g. `Ragnhild Jølsen` → `Forfatter:Ragnhild Jølsen`, `Fra Monte Pincio` → `Indeks:Bjørnson - Samlede værker mindeutgave vol 1.djvu`, `Synnøve Solbakken` → `Indeks:Bjørnson - Samlede værker mindeutgave vol 1.djvu`, `Norrøna-Stammen` → `Indeks:Bjørnson - Samlede værker mindeutgave vol 1.djvu`)
- excluded: 9 (e.g. `Bibelen (1891)/Profeten Zefanias` → `Sefanja`, `Bibelen (1891)/Paulus's andet Brev til Tessalonikerne` → `Paulus' andre brev til tessalonikerne`, `Professor Lochmanns Tale ved Immatrikuleringen den 2den Septbr.` → `Morgenbladet`, `Bibelen (1891)/Paulus's Brev til Titus` → `Paulus' brev til Titus`)
- missing: 114 (e.g. `Norges gamle Love/II/Hirdskraa` → `Norges gamle Love/II`, `Mellem Fjeldene` → `Folkelivsromaner og Folkelivsbilleder`, `Haavamaal` → `Gudekvæde`, `Om Ridderen og Rigsraaden Hr. Hauk Erlendssøn, Islands, Oslo og Gulathings Lagmand, og om hans litterære Virksomhed` → `Samlede Afhandlinger`)

## 6. Prev/next chains across Index boundaries

Of 165 next-links between pages that both have an Index, 1 (0.6 %) cross to a different Index.
Examples: `Ferdaminni fraa Sumaren 1860/24` (Vinje - Ferdaminni.djvu) → `Ferdaminni fraa Sumaren 1860/25` (Vinje - Ferdaminni II (1861).djvu).

Root-page works that sit on a prev/next chain but stay separate works (no container, not in a unique TOC; WS§8 item 5): 137.

## 7. Orphan trees, oversized pages, red links

**Orphan trees** (virtual roots): 34, holding 128 parts. Examples: `Brevet til de Ebræer` (1); `Den kristelige Troeslære i dens Grundtræk` (4); `Ezechiels Syner` (1); `Østers-Kulturen` (5); `Illustreret Norges Historie` (7); `Almuens Sanger` (1); `Det nye Testamentet (1886)` (1); `08-156741TVI-OTIR` (1).

**Oversized pages** (> 300,000 bytes of wikitext including transcluded scan pages): 12. Largest: `Gustav den III’s norske Politik` 0.6 MB; `Ordbog over det norske Folkesprog/S` 0.6 MB; `Norske Rigs-Registranter/Christian den 3die` 0.5 MB; `Det norske Folkesprogs Grammatik` 0.5 MB; `En Krønike om Erkebiskopperne i Nidaros` 0.4 MB; `Optegnelser om visitatsreisen i Baahuslen og Borgesyssel 1 Marts – 1 Mai 1594` 0.4 MB; `Jury-Institutionen i Storbritanien, Canada og de forenede Stater af Amerika` 0.4 MB; `Angaaende Forandringer i Lovgivningen om Husmænd` 0.4 MB; `Realitets-Procedure og Dom i Rigsretssagen imod Hs. Excellence Statsminister m. m. Løvenskiold` 0.4 MB; `Biskop Peder Herslebs Embedsskrivelser til Akershus Stifts Gejstlighed` 0.3 MB.

**Red links in TOCs** (missing parts): 81 multi-part works have red links, 6,030 in total; median share of the TOC 50%. Most missing: `Norsk Haandlexikon for almennyttige Kundskaber` 4438 of 5576; `Folkevennen` 198 of 376; `Samlede Skrifter (Moe)` 117 of 121; `Diktsamling` 116 of 120; `Historisk Tidsskrift` 86 of 489; `Samtiden` 77 of 116; `Um Kristi Etterfylgjing` 68 of 69; `Prøver af Landsmaalet i Norge` 61 of 70.

## 8. Colon prefixes with ≥ 100 root pages

None.

## 9. Quality and completeness (PLAN.md 5.4)

**Hard exclusions**: 49 works — no-text 20, junk-template 13, under-300-bytes 12, no-text-pages 3, placeholder 1.

| tier | works | share | texts in them | source |
|---|---|---|---|---|
| validated | 73 | 6.8 % | 217 | page-levels 73 |
| proofread | 466 | 43.6 % | 2,687 | page-levels 466 |
| unproofread | 307 | 28.7 % | 3,569 | page-levels 307 |
| unknown | 223 | 20.9 % | 49 | none 223 |

24 works are proofread by the ≥ 90 % rule. 182 of the 307 unproofread works have no page above level 1 (raw OCR).

**Incomplete**: 123 works — missing-parts 88, missing-pages 17, coverage 12, template 6.
Works with missing scan pages inside a transcluded range: 31 (1,671 pages).
Coverage was computed for 359 works that are their Index's book; 41 cover under 95 % of the Index's text pages.
Wikisource's own translations (labelled, kept): 10.

Index progress values: `OCR` 546, `C` 386, `V` 297, `T` 37, `MS` 3.

**Unknown works** (no signal): 223 in the catalog after the junk rules; the junk rules removed 26 (junk-template 13, under-300-bytes 12, placeholder 1).
Size: median 2,218 bytes, p10 655, p90 21,322; multi-part 14.

30 random unknown works:

- `Et norsk skib` (single, 2,218 B): Et norsk skib av Forfatter:Nordahl Grieg Ti tusen tonn, en verden av jern, går over hav, rugende svart. Stormer og sjø sprænger den mot, finder sin vei, holder 
- `Viggo Hansteen` (single, 8,607 B): Viggo Hansteen av Nordahl Grieg —— Hadde jeg skrevet noe, kom jeg ofte til deg. Varmt falt lys ut i mørket fra Bernhard Herres vei. Stjernene over åsen, skogen 
- `Til Oscar` (single, 1,749 B): At min Sjel af den store Digters Ild gnistrede! Al min Glands skulde ofres dig Oscar, liig Bølgen, som Dagkongens funklende Krone gjenviser. Ogsaa Stjernen beha
- `For Samhold i Norden` (single, 6,350 B): &emsp;Den Tanke, der forsamler os iaften, er sand og alvorlig. Den er intet Hjernespind, ingen forfængelig Flyvetanke fra igaar eller idag. Jeg stoler paa dens 
- `Til Hakon` (single, 3,013 B): En syngende Kilde, Hakon, sprudler som Geisir i Sjelen: Den svulmer om Bredden: Den over de sorte Stene, Skjebnen har spændt om dens stedse urolige, stedse sved
- `Der vester mellom skjær og bå` (single, 807 B): Barnesang av Nordahl Rolfsen Der vester mellom skjær og bå, mellom skjær og bå, der gikk et skip med nødflagg på, gikk med nødflagg på. Det sto nok saktens folk
- `Sprinterne` (single, 336 B): Sprinterne av Nordahl Grieg Niggeren Owens sprinter, germanerne stuper sprengt. Det blonde stadion undres, og Føreren mørkner strengt. Men tenk da med trøst på 
- `Til Stella` (single, 8,038 B): Ha, hvor spænder sig min Sjel &emsp;&emsp;op til Himmel, ned til Hel, over Sol og over Skye til Messias, første Klang i den høie Aandesang, som ved Blivet toned
- `Vand` (single, 2,514 B): Vand av Forfatter:Nordahl Grieg Solen kaster sig mot jorden, som en drægtig tigerinde sprunget ut av rummets jungel, glefsende mot blod i blinde. Kvalt i dyrefa
- `Personverndommen` (single, 29,642 B): Dommer Qvigstad: I denne sak avsa Oslo byrett med tre rettskyndige dommere den 27. oktober 1950 dom med slik domsslutning: «Norsk Film A/S kjennes uberettiget t
- `Høyres valgprogram fra 1888` (single, 2,045 B): Hoires Program == 1. Opretholdelse af Grundloven og de Principer, hvorpaa denne er bygget, samt Bevarelse af Foreningen med Broderriget. Varsomhed ved Spørgsmaa
- `Polonia` (single, 3,233 B): Polonia, Polonia! falder Du? Er Røst i Ørken dit Nødhjælpsskrig? Snoer alt om dit Hjerte Dragen sig og sprudler Edder i din aabne Barm? Er det til din Ligfærd i
- `Barn Jesus i en krybbe lå` (single, 559 B): thumb\|Barn Jesus i en krybbe lå. Niels Gade. Barn Jesus i en krybbe lå, skjønt himlen var hans eie, hans pute her var høy og strå, mørkt var det om hans leie! M
- `Venstres valgprogram fra 1885` (single, 414 B): Repræsentantskabet udtaler, at Hævdelsen af den parlamentariske Statsskik og Tilslut-ningen til Ministeriet Sverdrup bør være Venstres Program for kommende Stor
- `Det forenede norske Arbeiderparti Program 1888` (single, 898 B): Det foredene arbeiderparti program av 1888 == Indførelse av almindelig stemmeret med direkte valg og valg paa fridag. Indførelse av en arbeiderbeskyttelseslov m
- `Til Provst Nils Hertzberg` (single, 2,699 B): Jeg fik, til Svar paa en frimodig Tale, brutale Ramaskrig fra hver en Egn; jeg ved, at dette er et Tidens Tegn: her er jo Dæmring; Hanerne maa gale. Det Værste 
- `I Stormen` (single, 2,464 B): Fløien hviner: Lyraen klinger, skjøndt skjult ved mit Hjerte . . O ti! ti, mens vi henover Skaraen løbe til mosdækket Lye. Stormen fører ikke din Klang til de s
- `Minnesamvær om polarfareren Oskar Omdahl` (single, 1,229 B): Minnesang om polarfareren Oskar Omdahl, ukjent forfatter. Jeg vil synge en minnesang om en mann på sjø og land freidig til sitt virke gikk som en ekte nordmanns
- `Glade jul` (single, 790 B): Glade jul, hellige jul! Glade jul, hellige jul Engler daler ned i skjul. Hit de flyver med paradis grønt, hvor de ser hva for Gud er skjønt. Lønnlig i blant oss
- `Gerd` (single, 835 B): Til min hustru av Nordahl Grieg Bomber falt rundt huset. Blåblek lyste løkten gjennom nattemørket i hotellets gang, mens jeg famlet mot deg for å be deg komme. 
- `Etterstev` (single, 2,461 B): Til Lags aat alle kann ingen gjera; det er no gamalt og vil so vera. Eg tykkjer stødt, at det høver best aa hjelpa den, som det trenger mest. Og kor du bryggjar
- `Omvisning på Stein Skole` (single, 14,507 B): Småskolen=== Vi begynner i småskolen. Her er fargene psykologisk tilpasset de minste barna. De gir uttrykk for vital livskraft, sol og lys. Hver bitte liten det
- `Poesier` (single, 593 B): Den Navnløse Blomsten Fødselsdagen Fantasi Den Elskte Den Elskedes Overfart Min Grav Det første Haandtryk Den første Omfavnelse Det første Kys Kjærlighed og Nød
- `Til Hagbarth (II)` (single, 1,409 B): Skulde jeg glemme dig, Hagbarth, om nogle Fjeldrygge skille os ad, som Tiden, der sætter sit Taageskjold mellem vort Nor og dets Fortid? Endda den glimrer jo kl
- `Den store Feiltagelse` (single, 8,354 B): Den store Feiltagelse &emsp;paa vor Side har været den, at mange trodde, her var to Sprogformer, som tilsidst fløt sammen. &emsp;Den har været Aarsaken til Ukla
- `God dag mann, Økseskaft` (single, 2,364 B): God dag mann, økseskaft. Norsk folkeeventyr Det var en gang en ferjemann som var så tunghørt at han hverken kunne høre eller samle det noen sa :til ham. Han had
- `B. S. Ingenmann` (single, 669 B): Stig frem, o Dag med Dødens Minde Av B.S. Ingemann Stig frem, o Dag med Dødens Minde! I Graven Livers Konge laae: Hvad Jorden eier, maa forsvinde, Hvad Verden g
- `Morgenbladet/1820` (single, 29,290 B): https://urn.nb.no/URN:NBN:no-nb_digavis_morgenbladet_null_null_18200101_2_1_1 (dårlig ikkje upload) hvor er 2? https://urn.nb.no/URN:NBN:no-nb_digavis_morgenbla
- `Stumper` (single, 1,659 B): Hvormeget dog en Tøddel gjør? &emsp;Tag «W»et bort af Wedel. Du seer da, Hvad du aldrig før &emsp;hos denne Slægt har seet. Chor Ecce & c. Fra Sverdrup mangen M
- `Leachman's Norsk Engelsk Lomme-Ordbog` (single, 34,257 B): LEACHMAN'S LEACHMAN'S NORSK-ENGELSK ENGELSK-NORSK LOMME-ORDBOG LOMME-ORDBOG Norwegian-English Pocket Dictionary English-Norwegian Pocket Dictionary AND Manual o

**Default view** (validated or proofread, complete, not reference): 463 works with 2,359 texts; with unknown works shown: 678 works (2,400 texts). Works in the default view whose pages are all level 1: 0. Current setting: `show_unknown_by_default = True`.

## 10. Parts table size (WS§5.2)

| parts_cap | works with catalog parts | parts rows | parts table, gzipped | parts_fts, gzipped |
|---|---|---|---|---|
| 500 | 428 | 8,974 | 0.51 MB | 0.11 MB |
| 2,000 | 430 | 10,805 | 0.61 MB | 0.12 MB |
| ∞ (all, reference works included) | 430 | 10,805 | 0.61 MB | 0.12 MB |
| 2,000, compact encoding | 430 | 10,805 | 0.54 MB | 0.12 MB |

Rows count multi-part works only, one row per part plus the root row. 639 single-page works would add one row each if stored. Sizes are a SQLite file holding only the table (with its index) at gzip level 9. The compact encoding (not in WS§5.1, measured for the cap decision) stores a part's page_title as the suffix after the work root's title where possible, display_title only when it differs from the last title segment, and no per-part quality_pages.

## 11. Named cases (PLAN.md WP1 acceptance, WS§9)

- `Diktsamling`: work, linked, type collection, 4 parts (4 texts), 116 missing, order toc, oversized False, quality unproofread (page-levels), complete False (missing-parts).
- `Ved Rundarne (1864)`: part 4 of `Diktsamling` (text, display “Ved Rundarne”, rule container, searchable True).
- `Arne`: work, subpages, type book, 16 parts (0 texts), 0 missing, order toc, oversized False, quality proofread (page-levels), complete True.
- `Peer Gynt`: work, subpages, type book, 6 parts (6 texts), 0 missing, order toc, oversized False, quality unproofread (page-levels), complete True.
- `Sult`: work, subpages, type book, 8 parts (4 texts), 0 missing, order scan, oversized False, quality proofread (page-levels), complete True.

## 13. Implementation notes

- When a page's container link names one of its title ancestors, the tree parent is its deepest existing ancestor below that container (is and en subpages often name the root, skipping an existing section page).
- The Index TOC field (e.g. no `Sammendrag`) counts as TOC links only for the page its Index title field links to; otherwise every member would look TOC-like.
- Display titles come from `current`/`section`/`TITEL`, else the link text in the parent's TOC, else the last title segment. A work's first part row is its root (`front` above 400 characters of own text, else `toc`); `part_count` excludes it.
- Missing parts are red links in the TOCs of the work's root and volumes: subpages of the root always, other titles only in linked or mixed works and on TOC-like pages.
- Junk rules (cleanup or deletion template, placeholder, under 300 bytes) apply to works without scan pages and without any quality signal.
- Order (step 1c), after the plan's sources: children from several scan files that all have a scan position follow the files in natural order, then the position (`scan-volumes`); else, when the TOC lists at least half of the children, they keep the TOC order and the rest follow in natural order (`toc-partial`); natural order reads roman numerals. So collections are not shown alphabetically (WS§2 rule 3).
- Rule 3: a TOC page is marked as one (de `HERKUNFT = off`) or is its Index's own book; "reached from it by the prev/next chain" is read as: the member's chain starts at the TOC page or its prev or next page is listed on the same TOC page.
- A container with several links (de `HERKUNFT`) resolves to the first link that shares the page's Index, else the first link (the KHM tales link the editions overview first).
- Volume placement: a part attached by its container is placed under the one TOC page (under 2,000 bytes of own text, listing at least three members) of the same work that lists it, unless the container's members already form one prev/next chain.
- A slash inside unclosed parentheses is part of the name (de `X (Die Gartenlaube 1871/17)`), not a subpage separator.
- Exclusions: a page with scan transclusion is never a disambiguation page (fr dictionary roots carry the prop); subpages inherit only portal and author-page exclusions; on de, portal pages are marked by `Themendaten`, `Ortsdaten`, `Listendaten` (and the categories Thema, Orte, Listen when the page has no header), and a root page without header template, scans or status is a portal (`no-header`) unless other pages name it as their container.
- A bare `<pages index=X/>` renders the Index's TOC field, not the book.

## 12. Random samples for eyeballing (30 per pattern)

### A: collections of root pages (82)

1. `En Jægers Erindringer`: linked, book, 6 parts, 0 missing, order toc, validated
    - toc: En Jægers Erindringer
    - text: Sæterpigen og Jægeren [container]
    - text: Føstretiden [container]
    - text: Krogkleven [container]
    - text: I Nordmarken [container]
    - text: Carl [container]
    - text: Skovene [container]
1. `Til dem som forkynner eller lærer i det norske mål (bok)`: linked, book, 6 parts, 0 missing, order toc, proofread
    - front: Til dem som forkynner eller lærer i det norske mål (bok)
    - text: Til dem som forkynner eller lærer i det norske mål [container]
    - text: Bondemålstrævet [container]
    - text: Fra striden i bladene [container]
    - text: Målstræver-sanhet [container]
    - text: Landsgyldigt eller ikke landsgyldigt [container]
    - text: Gammel dansk som et „andet Sprog“ æn gl. norsk [container]
1. `Stjernerne (diktsamling)`: linked, collection, 34 parts, 0 missing, order toc, unproofread
    - toc: Stjernerne (diktsamling)
    - text: Mit hjerte (`Mit hjerte (Stjernerne)`) [container]
    - text: En sang om ingenting [container]
    - text: Lady Gwendoline [container]
    - text: Dialog [container]
    - text: Paul Gauguin [container]
    - text: Sophus Bugge (`Sophus Bugge (dikt)`) [container]
    - text: Mot norden [container]
    - text: I høsten [container]
    - text: Schero (`Scherzo`) [container]
    - text: Vor gyldne ungdom! [container]
    - text: Sub Rosa [container]
    - … 23 more
1. `Norsk billedbog for børn (1890)`: linked, book, 14 parts, 0 missing, order toc, validated
    - toc: Norsk billedbog for børn (1890)
    - text: Glædelig jul og tak for sidst! [container]
    - text: By by barne'! [container]
    - text: So ro, liten tull [container]
    - text: Hoppe sa gaasa [container]
    - text: Han Per og hu Guri [container]
    - text: Kjærringa med staven [container]
    - text: Huttetu, jeg fryser saa [container]
    - text: Gunnar uti Sunde [container]
    - text: Kattepus, kattepus! [container]
    - text: Reiar skulde ut aa gjæte [container]
    - text: Jeg sku' helse [container]
    - … 3 more
1. `Ein Soge-Bundel`: linked, collection, 13 parts, 0 missing, order toc, unproofread
    - front: Ein Soge-Bundel
    - text: So løna dei God-Gjerningar [container]
    - text: Stuten som vilde av og føda seg sjølv [container]
    - text: Brudgumen som miste Brudi si [container]
    - text: Dyre-Styrk og Dyre-Mod [container]
    - text: Den gylte Skog [container]
    - text: Guten og Risen [container]
    - text: Oskelabben og Asnet [container]
    - text: Bukken og Bekren, som bygde upp eit Hus paa Ripleheidi [container]
    - text: Pyk og Sløg og Oskelabben [container]
    - text: Kong Katt paa Heidbergs Borg med fjorom gyltom Krunom [container]
    - text: Halvpening [container]
    - … 2 more
1. `Fram over Polhavet`: linked, book, 27 parts, 0 missing, order toc, unproofread
    - toc: Fram over Polhavet
    - chapter: INNLEDNING (`Fram over Polhavet I/1`) [container]
    - text: PLANEN (`Fram over Polhavet I/2`) [container]
    - text: FORBEREDELSER (`Fram over Polhavet I/3`) [container]
    - text: AVREISEN (`Fram over Polhavet I/4`) [container]
    - text: FARVEL TIL NORGE (`Fram over Polhavet I/5`) [container]
    - text: REISEN GJENNEM KARA-HAVET (`Fram over Polhavet I/6`) [container]
    - text: RUNDT DEN GAMLE VERDENS NORDENDE (`Fram over Polhavet I/7`) [container]
    - text: VINTERNATTEN (`Fram over Polhavet I/8`) [container]
    - text: FØRSTE JUL OG NYTTÅR OMBORD I &bdquo;FRAM&rdquo; (`Fram over Polhavet I/9`) [container]
    - text: VÅREN OG SOMMEREN 1894 (`Fram over Polhavet I/10`) [container]
    - text: DEN ANNEN HØST I ISEN (`Fram over Polhavet I/11`) [container]
    - … 16 more
1. `Vestlandsviser`: linked, book, 32 parts, 0 missing, order toc, proofread
    - toc: Vestlandsviser
    - text: O, VESTLAND, DU MIN MODERJORD (`O, vestland, du min moderjord`) [container]
    - text: Lilleguts vise [container]
    - text: Sjømandens afskedssang til sin kjæreste [toc]
    - text: En vise om pigerne i Spanien [container]
    - text: Den vidtbereiste Salve [toc]
    - text: Hvad sjøgutterne synger [toc]
    - text: Alene i båden om høstnatte [toc]
    - text: Vårvise [toc]
    - text: Hvad Hans Jørgen tænker [toc]
    - text: Vintervise [toc]
    - text: Det spøger [toc]
    - … 21 more
1. `Fra en svunden tid`: linked, book, 34 parts, 0 missing, order toc, unproofread
    - toc: Fra en svunden tid
    - text: Alfelande [container]
    - text: Guri Kunnan [container]
    - text: Jutulen i Hifjeldet [container]
    - text: Bobben paa Musgjerd [container]
    - text: Indtagelse i berg [container]
    - text: Lisbet Nypen [container]
    - text: Ulykken paa Folden [container]
    - text: Finnernes tryllekyndighed [container]
    - text: Lensmand Nideros [container]
    - text: Brødreskifte [container]
    - text: Hauk-varpet [container]
    - … 23 more
1. `Metope (diktsamling)`: linked, collection, 21 parts, 0 missing, order toc, unproofread
    - front: Metope (diktsamling)
    - text: Metope (`Metope (dikt)`) [container]
    - text: Til de dristige [container]
    - text: Barnet (`Barnet (Metope)`) [container]
    - text: Fryvil [container]
    - text: Det nye rum [container]
    - text: Over skyerne [container]
    - text: Memoirer [container]
    - text: Pietá [container]
    - text: Violer [container]
    - text: Betragtning over Minni [container]
    - text: Idyl (`Idyl (Metope)`) [container]
    - … 10 more
1. `Fra det mørke Fastland`: linked, book, 8 parts, 0 missing, order toc, proofread
    - front: Fra det mørke Fastland
    - text: I Stavanger [toc]
    - text: I Jernbanestationens Venteværelse [toc]
    - text: Stille Betragtninger [toc]
    - text: Det skikkelige Stavanger [toc]
    - text: Sydover Jæderen [toc]
    - text: En Jærbu-Lidenskab [toc]
    - text: Eventyr (`Eventyr (Garborg)`) [toc]
    - text: Jædersk Politik [toc]
1. `Diktsamling`: linked, collection, 4 parts, 116 missing, order toc, unproofread
    - front: Diktsamling
    - text: Leitande efter Blaamann [container]
    - text: Gamle Moder [container]
    - text: Vaaren [toc]
    - text: Ved Rundarne (`Ved Rundarne (1864)`) [container]
1. `Skovstjerner`: linked, book, 26 parts, 0 missing, order toc, validated
    - front: Skovstjerner
    - text: Skovstjernen [container]
    - text: En Moders Drøm [container]
    - text: Duggen (`Duggen (Sinding)`) [container]
    - text: Fiskeren [container]
    - text: Ved Aftenstide [container]
    - text: Den deilige Strand [container]
    - text: Savojarden [container]
    - text: Aftenlandskab [container]
    - text: Vandlilien [container]
    - text: Sneklokken [container]
    - text: Vinterkvælden [container]
    - … 15 more
1. `Mange slags kunst`: linked, book, 12 parts, 0 missing, order toc, unproofread
    - front: Mange slags kunst
    - text: Et par ting om ættesagaen [container]
    - text: Kjærligheten i Kormaks saga [container]
    - text: Rundt fænomenet Villon [container]
    - text: Nogen ord om Hamlets alder [container]
    - text: Ved Ragnhild Jølsens død [container]
    - text: En hovmodig digter [container]
    - text: Professor Catharinus Bang [container]
    - text: Litt om stil [container]
    - text: Tre smaa redegjørelser [container]
    - text: Rytme og rim [container]
    - chapter: Fortale til «Auswanderer» [container]
    - … 1 more
1. `Udvalgte lyriske Digte`: linked, collection, 94 parts, 0 missing, order toc, unproofread
    - front: Udvalgte lyriske Digte
    - text: Ode til Norges Frihed [container]
    - text: Til Oscar (`Til Oscar (Udvalgte lyriske Digte)`) [container]
    - text: Ode til Norges Flag (`Ode til Norges Flag (Udvalgte lyriske Digte)`) [container]
    - text: Napoleon (`Napoleon (Udvalgte lyriske Digte)`) [container]
    - text: Under en Birk (`Under en Birk (Udvalgte lyriske Digte)`) [container]
    - text: Ved de landflygtige Polakkers Afreise [container]
    - text: Gregers Fougner Lundh [container]
    - text: Til Herman Foss [container]
    - text: Jonas Anton Hjelm [container]
    - text: Baron Hoff-Rosenkrone [container]
    - text: Mandens Vrede, Længsel og Sorg [container]
    - … 83 more
1. `Skisser fra fjeldbygden`: linked, book, 11 parts, 0 missing, order toc, unproofread
    - toc: Skisser fra fjeldbygden
    - text: Sollien [container]
    - text: Kirken [container]
    - text: Præstegaarden (`Præstegaarden (Helene Lassen)`) [container]
    - text: Mit kongerige [container]
    - text: Uveirshumoreske [container]
    - text: Paa ski i maaneskin [container]
    - text: Høstslagtningen [container]
    - text: Tull-Ingeborg [container]
    - text: Gamlemor begraves [container]
    - text: Fra bygd til bygd [container]
    - text: Paa skoleeksamen [container]
1. `Lauvduskar`: linked, collection, 45 parts, 0 missing, order single, unproofread
    - toc: Lauvduskar
    - volume: I. (Andre Utgaava) II. (`Lauvduskar I. II`) [container]
      - text: Svein Urædd [container]
      - text: Attersyn paa Livet [container]
      - text: Storm og Stilla [container]
      - text: Den tyngste Sorg og Møda [container]
      - text: Gamle Stev elder «Frøde-Vers» [container]
      - text: Nyare Stev [container]
      - text: Her er det Land, som hugar meg best [container]
      - text: Dei træla, – dei træla [container]
      - text: Høgferd (`Høgferd (Lauvduskar)`) [container]
      - text: Reven paa Alvstad [container]
    - … 34 more
1. `Nat. Digte i prosa`: linked, book, 12 parts, 0 missing, order toc, proofread
    - toc: Nat. Digte i prosa
    - text: MAINÆTTER (`Nat/01`) [container]
    - text: Første nat (`Nat/02`) [container]
    - text: Anden nat (`Nat/03`) [container]
    - text: Tredie nat (`Nat/04`) [container]
    - text: Fjerde nat (`Nat/05`) [container]
    - text: HØSTNÆTTER (`Nat/06`) [container]
    - chapter: I (`Nat/07`) [container]
    - chapter: II (`Nat/08`) [container]
    - chapter: III (`Nat/09`) [container]
    - chapter: IV (`Nat/10`) [container]
    - chapter: V (`Nat/11`) [container]
    - … 1 more
1. `Digte og noveller`: linked, book, 17 parts, 0 missing, order toc, proofread
    - front: Digte og noveller
    - text: Til ordene [container]
    - text: Barnet [container]
    - text: I mørket [container]
    - text: Sommerens forlis [container]
    - text: Mit hjerte [container]
    - text: Avmagt [container]
    - text: Vaar (`Vaar (Bull)`) [container]
    - text: Idyl [container]
    - text: Drøm [container]
    - text: Menons tale [container]
    - text: Lisken [container]
    - … 6 more
1. `Steder og folk`: linked, book, 7 parts, 0 missing, order toc, unproofread
    - front: Steder og folk
    - text: Sætesdal [container]
    - text: Hardanger [container]
    - text: Strandebarm kirke [container]
    - text: Rundt om Vestlandshus [container]
    - text: Byens sjæl [container]
    - text: De tre stammer i Norden [container]
    - text: Abruzzer-sommer [container]
1. `Segner fraa Bygdom II`: linked, book, 28 parts, 0 missing, order toc, unproofread
    - front: Segner fraa Bygdom II
    - text: Fosgubbind [container]
    - text: Selbu [container]
    - text: Potet’n [container]
    - text: Kaffe’n [container]
    - text: Jøvra [container]
    - text: Kjørkja [container]
    - text: Sjursmesda’n [container]
    - text: Bortigarsgubbind aa Tuftend [container]
    - text: Teveldalingind [container]
    - text: Hrr. Petter [container]
    - text: Storsme’n aa Tuftend [container]
    - … 17 more
1. `Den ældre Edda`: linked, book, 2 parts, 19 missing, order toc, proofread
    - front: Den ældre Edda
    - text: Volvens spaadom [container]
    - text: Den Høies ord [container]
1. `Digte (Krag)`: linked, book, 40 parts, 0 missing, order toc, proofread
    - front: Digte (Krag)
    - text: Fandango [container]
    - text: Ouverture [container]
    - text: Valborgsnat [container]
    - text: Mens jeg venter [container]
    - text: Liden Kirsten [container]
    - text: Minde [container]
    - text: Der skreg en fugl [container]
    - text: Sange (`Sange (Krag)`) [container]
    - text: December (`December (Krag)`) [container]
    - text: Moderen synger [container]
    - text: Nytårsnat [container]
    - … 29 more
1. `Steen Steensen Blicher`: linked, book, 7 parts, 0 missing, order toc, proofread
    - front: Steen Steensen Blicher
    - text: Undsets innledning (`Steen Steenersen Blicher/01`) [container]
    - chapter: I (`Steen Steenersen Blicher/02`) [container]
    - chapter: II (`Steen Steenersen Blicher/03`) [container]
    - chapter: III (`Steen Steenersen Blicher/04`) [container]
    - chapter: IV (`Steen Steenersen Blicher/05`) [container]
    - chapter: V (`Steen Steenersen Blicher/06`) [container]
    - chapter: VI (`Steen Steenersen Blicher/07`) [container]
1. `Forraadt (novellesamling)`: linked, book, 3 parts, 1 missing, order toc, proofread
    - toc: Forraadt (novellesamling)
    - text: Madam Høiers Lejefolk [container]
    - text: Karens Jul [container]
    - text: In Asiam profectus est [container]
1. `Femten oder af Horats`: linked, book, 15 parts, 0 missing, order toc, proofread
    - toc: Femten oder af Horats
    - text: Tilegnelse [container]
    - text: Pyrra [container]
    - text: Teuker [container]
    - text: Ved vintertid [container]
    - text: Forsoning [container]
    - text: Til Lyren [container]
    - text: Sølvet [container]
    - text: Hvor tiden haster! [container]
    - text: Tilfredshed [container]
    - text: Nøisomhed [container]
    - text: Romas storhed [container]
    - … 4 more
1. `Studier og portræter`: linked, book, 1 parts, 5 missing, order single, proofread
    - toc: Studier og portræter
    - text: En norsk mor. (Fru Maren Sars) (`En norsk mor`) [container]
1. `Lys og Skygge. Den hemmelighedsfulde Thomas Ryer – Norges farligste Forbryder`: linked, collection, 63 parts, 0 missing, order toc, proofread
    - front: Lys og Skygge. Den hemmelighedsfulde Thomas Ryer – Norges farligste Forbryder
    - text: Et Mennesketyveri [container]
    - chapter: I. Benzinmotoren. (`Et Mennesketyveri/01`) [container]
    - chapter: II. Cyclisten. (`Et Mennesketyveri/02`) [container]
    - chapter: III. Det er ham. (`Et Mennesketyveri/03`) [container]
    - chapter: IV. Ansigt til Ansigt. (`Et Mennesketyveri/04`) [container]
    - chapter: V. En fuldendt Gentleman. (`Et Mennesketyveri/05`) [container]
    - chapter: VI. Lysningen. (`Et Mennesketyveri/06`) [container]
    - chapter: VII. Den stjaalne Hest. (`Et Mennesketyveri/07`) [container]
    - chapter: VIII. Et genialt Kup. (`Et Mennesketyveri/08`) [container]
    - chapter: IX. Seks Tusen Pund. (`Et Mennesketyveri/09`) [container]
    - chapter: X. Et Ord til Afsked. (`Et Mennesketyveri/10`) [container]
    - … 52 more
1. `De hundrede aar`: linked, book, 3 parts, 0 missing, order toc, proofread
    - front: De hundrede aar
    - text: Henrik Wergeland (`Henrik Wergeland (Bull)`) [container]
    - text: Silhouettslægten [container]
    - text: Bjørnstjerne Bjørnson (`Bjørnstjerne Bjørnson (Bull)`) [container]
1. `Udvalgte mindre juridiske afhandlinger (1901)`: linked, book, 2 parts, 5 missing, order toc, proofread
    - toc: Udvalgte mindre juridiske afhandlinger (1901)
    - text: Til retsvidenskabens metode [container]
    - text: Ihering–Windscheid, et bidrag til belysning af retsforskningen i det nittende aarhundrede (`Ihering – Windscheid`) [container]
1. `Biskop Jens Nilssøns visitatsbøger og reiseoptegnelser 1574–1597`: linked, book, 21 parts, 6 missing, order toc, unproofread
    - toc: Biskop Jens Nilssøns visitatsbøger og reiseoptegnelser 1574–1597
    - volume: Biskop Jens Nilssøns liv og virksomhed 1538–1600 [container]
      - chapter: I. (`Biskop Jens Nilssøns liv og virksomhed 1538–1600/1`) [container]
      - chapter: II. (`Biskop Jens Nilssøns liv og virksomhed 1538–1600/2`) [container]
      - chapter: III. (`Biskop Jens Nilssøns liv og virksomhed 1538–1600/3`) [container]
      - chapter: IV. (`Biskop Jens Nilssøns liv og virksomhed 1538–1600/4`) [container]
      - chapter: V. (`Biskop Jens Nilssøns liv og virksomhed 1538–1600/5`) [container]
      - chapter: VI. (`Biskop Jens Nilssøns liv og virksomhed 1538–1600/6`) [container]
      - chapter: VII. (`Biskop Jens Nilssøns liv og virksomhed 1538–1600/7`) [container]
      - chapter: VIII. (`Biskop Jens Nilssøns liv og virksomhed 1538–1600/8`) [container]
      - chapter: IX. (`Biskop Jens Nilssøns liv og virksomhed 1538–1600/9`) [container]
      - chapter: X. (`Biskop Jens Nilssøns liv og virksomhed 1538–1600/10`) [container]
    - … 10 more

### B: mixed (38)

1. `Fru Bovary`: mixed, book, 6 parts, 0 missing, order scan, proofread
    - toc: Fru Bovary
    - chapter: Første del (`Fru Bovary/1`) [container]
    - text: Fru Bovary: Første del [container]
    - chapter: Annen del (`Fru Bovary/2`) [container]
    - text: Fru Bovary: Annen del [container]
    - chapter: Tredje del (`Fru Bovary/3`) [container]
    - text: Fru Bovary: Tredje del [container]
1. `Samlede Skrifter (Moe)`: mixed, book, 4 parts, 117 missing, order toc, proofread
    - toc: Samlede Skrifter (Moe)
    - text: Indledningsdigt [container]
    - text: Fanitullen [container]
    - text: Om Vaaren (`Om Vaaren (Moe)`) [container]
    - chapter: Forord (`Samlede Skrifter (Moe)/Forord`) [container]
1. `Symra`: mixed, book, 26 parts, 0 missing, order toc, proofread
    - toc: Symra
    - text: Fyrestev [container]
    - text: Gamle Norig [container]
    - text: Nordmannen [container]
    - text: Dei gamle Fjelli [container]
    - text: Heimvegen [container]
    - text: Gamle Grendi [container]
    - text: Vaardagen [container]
    - text: Sumarkvelden [container]
    - text: Haustvisa [container]
    - text: Hugen [container]
    - text: Von og Minne [container]
    - … 15 more
1. `Det norske Folks Historie`: mixed, book, 691 parts, 23 missing, order scan-volumes, proofread
    - toc: Det norske Folks Historie
    - volume: I-1-1 (`Det norske Folks Historie/1`) [hierarchy]
      - chapter: 1. Norges geographiske og physiske Forhold. (`Det norske Folks Historie/1/1`) [container]
      - chapter: 2. Urbeboere. Finner. Oldtidslevninger. (`Det norske Folks Historie/1/2`) [container]
      - chapter: 3. Levninger fra ældre Befolkninger i Sydsverige og Danmark. (`Det norske Folks Historie/1/3`) [container]
      - chapter: 4. Nordmændene og den germaniske Stamme. (`Det norske Folks Historie/1/4`) [container]
      - chapter: 5. Germanernes ældste Hjem og Omgivelser. Kelter, Skyther, Tschuder. (`Det norske Folks Historie/1/5`) [container]
      - chapter: 6. Grækernes og Romernes Efterretninger om Norden. (`Det norske Folks Historie/1/6`) [container]
      - chapter: 7. Oldgermaniske Vandringssagn. (`Det norske Folks Historie/1/7`) [container]
      - chapter: 8. Indvandringsvejen. Gardarike. Russer. (`Det norske Folks Historie/1/8`) [container]
      - chapter: 9. De germaniske Folks Navn i Almindelighed, og vore Forfædres i Særdeleshed. (`Det norske Folks Historie/1/9`) [container]
      - chapter: 10. Goterne og den gotiske Kulturperiode i Norden. Gauter, Daner o. fl. (`Det norske Folks Historie/1/10`) [container]
    - … 680 more
1. `Historisk Tidsskrift`: mixed, collection, 403 parts, 86 missing, order scan-volumes, unproofread
    - toc: Historisk Tidsskrift
    - volume: Andet Bind (`Historisk Tidsskrift/Anden Række/Andet Bind`) [hierarchy]
      - text: Om nogle middelalderske Slægter i det vestenfjeldske Norge [container]
      - text: To utrykte Digte af Petter Dass [container]
      - text: Stiftsprovst Dr. Theol. Otto Holmboes Dagbogs-Optegnelser 1751–1773 [container]
      - text: Armfeldts Tog Nordenfjelds 1718 (`Armfeldts tog nordenfjelds 1718`) [container]
      - text: Vikingetogenes tidligste Udgangspunkter [container]
      - text: Erik af Pommerns, Danmarks, Sveriges og Norges Konges, Giftermaal med Philippa, Prindsesse af England [container]
      - text: Den Bergenske Biskop Arnes Bibliothek [container]
      - text: Uddrag af Bergens Kongsgaards Regnskaber 1516–23 (`Uddrag af Bergens kongsgaards regnskaber 1516–23`) [container]
      - text: Ravnebryllupet i Kraakelund [container]
      - text: Om en paatænkt Restauration af Throndhjems Domkirke i Begyndelsen af det 18de Aarhundrede (`Om en paatænkt restauration af Trondhjems domkirke i begyndelsen af det 18de aarhundrede`) [container]
    - … 392 more
1. `Statholderen i Judæa og andre noveller`: mixed, book, 18 parts, 0 missing, order scan, proofread
    - toc: Statholderen i Judæa og andre noveller
    - text: De dødes messe. (`Statholderen i Judæa og andre noveller/01`) [container]
    - chapter: 01 (`Statholderen/01`) [container]
    - text: Gestas. (`Statholderen i Judæa og andre noveller/02`) [container]
    - chapter: 02 (`Statholderen/02`) [container]
    - text: Leslie Wood. (`Statholderen i Judæa og andre noveller/03`) [container]
    - chapter: 03 (`Statholderen/03`) [container]
    - text: Scolastica. (`Statholderen i Judæa og andre noveller/04`) [container]
    - chapter: 04 (`Statholderen/04`) [container]
    - text: Statholderen i Judæa. (`Statholderen i Judæa og andre noveller/05`) [container]
    - chapter: 05 (`Statholderen/05`) [container]
    - text: Legenden om den hellige Oliverie og den hellige Liberette. (`Statholderen i Judæa og andre noveller/06`) [container]
    - … 7 more
1. `Nils Kjær Samlede skrifter`: mixed, collection, 20 parts, 4 missing, order scan, proofread
    - toc: Nils Kjær Samlede skrifter
    - volume: Bind 5. Epistler og noveller (`Nils Kjær Samlede skrifter/5`) [container]
      - text: EPISTLER (`Nils Kjær Epistler`) [container]
      - text: Hugormen [container]
      - text: Epistel angaaende sjøormen [container]
      - text: Magsveir [container]
      - text: Julibrev fra Son [container]
      - text: Rousseaus skygge [container]
      - text: Vestlandsreise [container]
      - text: Brekkestøbreve [container]
      - text: Schillers svigermor [container]
      - text: Den stjaalne frak [container]
    - … 9 more
1. `Tragedier (Sofokles)`: mixed, collection, 9 parts, 0 missing, order toc, unproofread
    - front: Tragedier (Sofokles)
    - chapter: Fortale (`Tragedier (Sofokles)/Fortale`) [container]
    - chapter: Indledning (`Tragedier (Sofokles)/Indledning`) [container]
    - text: Kong Oidipus [container]
    - text: Oidipus i Kolonos [container]
    - text: Antigone [container]
    - text: Elektra [container]
    - text: Kvinderne i Trakis [container]
    - text: Aias [container]
    - text: Filoktetes [container]
1. `Heimskringlam`: mixed, book, 6 parts, 0 missing, order toc, proofread
    - front: Heimskringlam
    - text: Prologus (`Heimskringlam/Prologus`) [container]
    - text: Heimskringlam (`Heimskringlam (tekst)`) [container]
    - text: Almindelige bemærkninger (`Heimskringlam/Almindelige bemærkninger`) [container]
    - chapter: Tillæg (`Heimskringlam/Tillæg`) [container]
    - text: Bilag (`Heimskringlam/Bilag`) [container]
    - text: Slutbemærkning (`Heimskringlam/Slutbemærkning`) [container]
1. `Blandkorn`: mixed, book, 47 parts, 0 missing, order toc, unproofread
    - toc: Blandkorn
    - text: Fyreord (`Blandkorn/Fyreord`) [container]
    - text: Attersyn [container]
    - text: Den gamle Kona [container]
    - text: Klungen [container]
    - text: D’er leidt [container]
    - text: D’er gildt [container]
    - chapter: 1859 [container]
    - text: Det var no Du det [container]
    - text: Løn som forskyldt [container]
    - text: Du lyt faa med deg dei Bedste [container]
    - text: D’er Strid med det [container]
    - … 36 more
1. `Vidar`: mixed, collection, 13 parts, 34 missing, order scan-volumes, unproofread
    - toc: Vidar
    - volume: 1887 (`Vidar/1887`) [hierarchy]
      - text: Om Jury [container]
      - text: Stemninger i Danmark og Norge i Anledning af og nærmest efter Adskillelsen [container]
      - text: Anton Martin Schweigaards Dom om Andreas Høyer [container]
      - text: Et utrykt Brev fra Henrik Wergeland [container]
      - text: En Hexeproces fra Østerdalen i det 17de Aarhundrede [container]
      - text: Om Johan Nicolai Madvig [container]
    - chapter: 1888 (`Vidar/1888`) [hierarchy]
    - text: Fra Professor Christian Lassens Ungdom [container]
    - text: Paul Botten Hansen (Daae) [container]
    - chapter: 1889 (`Vidar/1889`) [hierarchy]
    - … 2 more
1. `Amok`: mixed, book, 3 parts, 0 missing, order toc, proofread
    - front: Amok
    - text: Stefan Zweig (`Amok/00`) [container]
    - text: Amokløper [container]
    - text: Brennende hemmelighet [container]
1. `Efterlatte arbeider`: mixed, book, 31 parts, 0 missing, order scan, proofread
    - front: Efterlatte arbeider
    - chapter: 01 (`Efterladte arbeider/01`) [container]
    - text: Katungen døde (ca. 1891). (`Efterlatte arbeider/01`) [container]
    - chapter: 02 (`Efterladte arbeider/02`) [container]
    - text: Svenskebirka (ca. 1896). (`Efterlatte arbeider/02`) [container]
    - chapter: 03 (`Efterladte arbeider/03`) [container]
    - text: Ørnen (ca. 1896). (`Efterlatte arbeider/03`) [container]
    - chapter: 04 (`Efterladte arbeider/04`) [container]
    - text: Digerheim Herreborg (1896). (`Efterlatte arbeider/04`) [container]
    - chapter: 05 (`Efterladte arbeider/05`) [container]
    - text: Lidt om en Herregaardsslegt (1897). (`Efterlatte arbeider/05`) [container]
    - chapter: 06 (`Efterladte arbeider/06`) [container]
    - … 20 more
1. `Levende og døde`: mixed, book, 29 parts, 0 missing, order toc, unproofread
    - toc: Levende og døde
    - text: SMAA PORTRÆTTER (`Levende og døde/Smaa portrætter`) [container]
    - text: En gammel kjærlighetshistorie [container]
    - text: Halfdan Kjerulf [container]
    - text: Welhaven (`Welhaven (Collett Vogt)`) [container]
    - text: H. C. Andersen [container]
    - chapter: 1876 [container]
    - text: Thomasine Lie [container]
    - text: Portrætter [container]
    - text: Da Erik Vullum døde [container]
    - text: Holger Drachmann [container]
    - text: Fredrik Collett [container]
    - … 18 more
1. `Nyere Digte`: mixed, book, 55 parts, 13 missing, order toc, proofread
    - toc: Nyere Digte
    - chapter: I (`Nyere Digte/I`) [container]
    - text: Suttungs Mjød [container]
    - text: Ved Havet (`Ved Havet (Nyere Digte)`) [container]
    - text: Elveløbet [container]
    - text: En Vaarnat [container]
    - text: Hvile i Skoven [container]
    - text: Den forladte Kirke [container]
    - text: Svindende Haab [container]
    - text: Troubadouren [container]
    - text: Alfernes Hvisken [container]
    - text: Paa Fjeldet [container]
    - … 44 more
1. `Nye Digte`: mixed, book, 62 parts, 0 missing, order toc, proofread
    - toc: Nye Digte
    - text: Poesien [container]
    - chapter: I (`Nye Digte/I`) [container]
    - text: Visen om stolt Kunigunde [container]
    - text: Morgensang [container]
    - text: Og atter ser jeg [container]
    - text: Andagt [container]
    - text: Lær mig, o skov [container]
    - text: Stilhed [container]
    - text: Dengang du gik [container]
    - text: I de forunderlige, blonde nætter [container]
    - text: Samtale [container]
    - … 51 more
1. `Tragedier (Evripides)`: mixed, collection, 6 parts, 0 missing, order toc, unproofread
    - front: Tragedier (Evripides)
    - chapter: Indledning (`Tragedier (Evripides)/Indledning`) [container]
    - text: Medea [container]
    - text: Ifigeneia i Avlis [container]
    - text: Ifigeneia i tavrernes land [container]
    - text: Hippolytos [container]
    - text: Bakkantinderne [container]
1. `Bogtrykkeren Benjamin Franklin`: mixed, book, 8 parts, 0 missing, order toc, proofread
    - front: Bogtrykkeren Benjamin Franklin
    - chapter: Forord (`Bogtrykkeren Benjamin Franklin/Forord`) [container]
    - chapter: Indledning (`Bogtrykkeren Benjamin Franklin/1`) [container]
    - text: Uddrag af Benjamin Franklins Selvbiografi (`Bogtrykkeren Benjamin Franklin/2`) [container]
    - text: Veien til Velstand [container]
    - text: Gode Raad til en ung Haandverker [container]
    - text: Nogle Vink for de som gjerne vil blive velstaaende [container]
    - text: Fløiten [container]
    - text: Middel til at fylde alle Folks Lommer med Penge [container]
1. `Samtiden`: mixed, collection, 39 parts, 77 missing, order scan-volumes, unproofread
    - toc: Samtiden
    - volume: Første aargang (`Samtiden/1890`) [container]
      - text: Om drømme [container]
      - text: „På Guds Veje“ (`Om „På Guds Veje“`) [container]
      - text: Home-rule-bevægelsen i Irland [container]
      - text: Russiske sekter [container]
      - text: Clemenceau [container]
      - text: Under skvætlæderet [container]
      - text: Magdalene Thoresen [container]
      - text: Fra Kjøbenhavn [container]
      - text: En uheldig statsunderstøttelse [container]
      - text: Fyrst Kropotkin [container]
    - … 28 more
1. `Syn og segn`: mixed, collection, 13 parts, 41 missing, order toc, unproofread
    - toc: Syn og segn
    - volume: Fyrste aargangen (`Syn og segn/1895`) [hierarchy]
      - text: Store-Jon Aakre [container]
      - text: Ho Rangdi [container]
      - text: Friedrich Nietzsche [container]
    - volume: Andre aargangen (`Syn og segn/1896`) [hierarchy]
      - text: Anders Rongli [container]
      - text: Eit møte [container]
      - text: Hansliguten [container]
      - text: Daa stor-uksen skulde slagtast [container]
      - text: Til fots [container]
      - text: Stormen (Hovden) [container]
    - … 2 more
1. `Den gamle Richards Kunst at blive rig og lykkelig tilligemed godt Raad til en ung Mand`: mixed, book, 3 parts, 0 missing, order toc, proofread
    - front: Den gamle Richards Kunst at blive rig og lykkelig tilligemed godt Raad til en ung Mand
    - chapter: Forord (`Den gamle Richards Kunst at blive rig og lykkelig tilligemed godt Raad til en ung Mand/Forord`) [container]
    - text: Den gamle Richards Kunst at blive rig og lykkelig [container]
    - text: Godt Raad til en ung Mand [container]
1. `Samlede Værker (Kielland)`: mixed, book, 157 parts, 3 missing, order scan-volumes, proofread
    - toc: Samlede Værker (Kielland)
    - volume: Første Bind (`Samlede Værker (Kielland)/1`) [container]
      - text: Novelletter [container]
      - text: Haabet er lysegrønt [container]
      - volume: Garman og Worse [container]
        - chapter: XXI. (`Garman og Worse/21`) [hierarchy]
        - chapter: XXII. (`Garman og Worse/22`) [hierarchy]
        - chapter: XXIII. (`Garman og Worse/23`) [hierarchy]
        - chapter: XXIV. (`Garman og Worse/24`) [hierarchy]
        - chapter: XXV. (`Garman og Worse/25`) [hierarchy]
        - chapter: XXVI. (`Garman og Worse/26`) [hierarchy]
      - text: Tre Smaastykker for Scenen [container]
    - … 146 more
1. `I gamle Daagaa`: mixed, book, 94 parts, 0 missing, order toc, unproofread
    - front: I gamle Daagaa
    - text: Huldre i Sjoli’n [container]
    - text: Huldre ve Flaakka [container]
    - text: Brings-Huldre [container]
    - text: Skardaa-Gubbin og Darthus-Kall’n [container]
    - text: Jenta fraa Heringsta som vart inn-tikjin [container]
    - text: Bess-Leiken [container]
    - text: Brure fraa Ransverk (`Brure fraa Ransverk (Kleiven)`) [container]
    - text: Trolle paa Heidals-Skogje og Blankenborg-Vetlgutann [container]
    - text: Prestberg-Jutul’n og Blessom’n [container]
    - text: Gjestbo’s-Hopen paa Staalom. Ho Eli Fugelsang [container]
    - text: Huldre i Ellingsbø-Rusten [container]
    - … 83 more
1. `Blandt Nordpolens Naboer`: mixed, book, 13 parts, 0 missing, order toc, proofread
    - front: Blandt Nordpolens Naboer
    - chapter: Indledning (`Blandt Nordpolens Naboer/1`) [container]
    - text: De to Peary-expeditioner (`Blandt Nordpolens Naboer/2`) [container]
    - text: En fattig kyst (`Blandt Nordpolens Naboer/3`) [container]
    - text: De indfødte ved Smiths sund (`Blandt Nordpolens Naboer/4`) [container]
    - text: Jagt (`Blandt Nordpolens Naboer5/`) [container]
    - text: Eskimoernes slædereiser. Den nord-grønlandske hund (`Blandt Nordpolens Naboer/6`) [container]
    - text: Nordover paa den grønlandske indlandsis (`Blandt Nordpolens Naboer/7`) [container]
    - text: Paa vandring mod nye kyster (`Blandt Nordpolens Naboer/8`) [container]
    - text: Hjemover gjennem skyerne (`Blandt Nordpolens Naboer/9`) [container]
    - text: Eskimoernes levevis, skikke, karakter, moral og sociale forhold (`Blandt Nordpolens Naboer/10`) [container]
    - text: Intelligents og kunstnerisk begavelse. Religiøse forestillinger, skikke etc. (`Blandt Nordpolens Naboer/11`) [container]
    - … 2 more
1. `Mauritz Hansens Noveller og Fortællinger`: mixed, book, 96 parts, 6 missing, order scan-volumes, proofread
    - toc: Mauritz Hansens Noveller og Fortællinger
    - volume: Første Bind (`Mauritz Hansens Noveller og Fortællinger/1`) [container]
      - text: Den Unævnelige [container]
      - text: Emil [container]
      - text: Fødselsdagsgaven [container]
      - text: Othar af Bretagne [container]
      - text: Luren [container]
      - text: Bergmanden (`Bergmanden (Maurits Hansen)`) [container]
      - text: Snedkerkonen [container]
      - text: Svigerdatteren [container]
      - text: Den gale Christian [container]
      - text: De to Søstre [container]
    - … 85 more
1. `Norsk Tidsskrift for Videnskab og Litteratur`: mixed, collection, 163 parts, 27 missing, order toc, unproofread
    - toc: Norsk Tidsskrift for Videnskab og Litteratur
    - volume: Første Aargang (`Norsk Tidsskrift for Videnskab og Litteratur/1`) [hierarchy]
      - chapter: Forord (`Forord (Norsk Tidsskrift for Videnskab og Litteratur 1)`) [container]
      - text: Brudstykker af Kong Haakon Magnussøns Historie (`Brudstykker af Kong Haakon V Magnussøns Historie`) [container]
      - text: Udledelse af nogle norske Sted- og Folkenavne [container]
      - text: i vor Kirke (`Kroningsaktens Betydning i vor Kirke`) [container]
      - text: Bidrag til Norges Historie under Unionen [container]
      - text: Nordmændenes Boliger og daglige Sysler [container]
      - text: B. Moes Tidsskrift for norsk Personalhistorie [container]
      - text: Norge i Tegninger. 1ste Hefte [container]
      - text: Nor, Tidsskrift for Videnskab og Litteratur [container]
      - text: Norges gamle Love för 1387 (`Norges gamle Love før 1387 (Fritzner)`) [container]
    - … 152 more
1. `Martyra`: mixed, book, 21 parts, 0 missing, order toc, proofread
    - toc: Martyra
    - text: Roger Bacon [toc]
    - text: Guttenberg [toc]
    - text: Ramus [toc]
    - text: Giordano Bruno [toc]
    - text: Thomas Campanella [toc]
    - text: Etienne Dolet [toc]
    - text: Galilei [toc]
    - text: Bernard Pallissy [toc]
    - text: Blaine Pascal [toc]
    - text: Denis Papin [toc]
    - text: John Fitch [toc]
    - … 10 more
1. `Populær-videnskabelige Foredrag`: mixed, collection, 9 parts, 0 missing, order scan, proofread
    - toc: Populær-videnskabelige Foredrag
    - chapter: Forord (`Populær-videnskabelige Foredrag/Forord`) [container]
    - chapter: Forord (`Populær-videnskabeligeForedrag/Forord`) [container]
    - text: Irlands gamle Aandskultur [container]
    - text: Braavalla-Slaget [container]
    - text: Nordboerne i Irland [container]
    - text: Piræus-Løven i Venedig og dens Indskrifter [container]
    - text: Om Sprogstriden [container]
    - text: Samhold i Norden [container]
    - text: Mindeord om P. A. Munch [container]
1. `Theologisk Tidsskrift for den evangelisk-lutherske Kirke i Norge`: mixed, book, 24 parts, 15 missing, order scan-volumes, unproofread
    - toc: Theologisk Tidsskrift for den evangelisk-lutherske Kirke i Norge
    - chapter: Andet Bind (`Theologisk Tidsskrift for den evangelisk-lutherske Kirke i Norge/2`) [hierarchy]
    - chapter: 3 (`Om Mag. Niels Svendsen Krønikes Stridigheder med Præsteskabet i Christiania i Aarene 1642–1652/3`) [container]
    - text: Mag. Jørgen Erichsson, den tredje evangeliske Superintendent i Stavanger Stift [container]
    - text: Bidrag til den tromsøiske „Vækkelses“ Historie [container]
    - text: Et Bidrag til Mag. Kjeld Stubs Historie [container]
    - chapter: Fjerde Bind (`Theologisk Tidsskrift for den evangelisk-lutherske Kirke i Norge/4`) [hierarchy]
    - chapter: 2 (`Syv throndhjemske Præsters Bønskrifter til Kirkestyrelsen om Skjærpelse i Kirketugten og Oprettelse af en Kirkeret/2`) [container]
    - chapter: Første Bind (`Theologisk Tidsskrift for den evangelisk-lutherske Kirke i Norge/1`) [hierarchy]
    - chapter: 1 (`Om Mag. Niels Svendsen Krønikes Stridigheder med Præsteskabet i Christiania i Aarene 1642–1652/1`) [container]
    - chapter: 2 (`Om Mag. Niels Svendsen Krønikes Stridigheder med Præsteskabet i Christiania i Aarene 1642–1652/2`) [container]
    - chapter: Niende Bind (`Theologisk Tidsskrift for den evangelisk-lutherske Kirke i Norge/9`) [hierarchy]
    - … 13 more
1. `Strenglege`: mixed, book, 18 parts, 0 missing, order toc, unproofread
    - front: Strenglege
    - text: Den oldnorske Oversætters Fortale (`Strenglege/Den oldnorske Oversætters Fortale`) [container]
    - text: Den franske Samlers Fortale (`Strenglege/Den franske Samlers Fortale`) [container]
    - text: Gujamars Sang [container]
    - text: Eskjas Sang [container]
    - text: Ekvitans Sang [container]
    - text: Bisklarets Sang [container]
    - text: Laustiks Sang [container]
    - text: Desirés Sang [container]
    - text: Chetovels Sang [container]
    - text: Douns Sang [container]
    - text: To elskendes Sang [container]
    - … 7 more

### D: virtual roots (34)

1. `10-099305-MED-OTIR`: virtual, book, 1 parts, 0 missing, order single, proofread
    - chapter: 04 (`10-099305-MED-OTIR/04`)
1. `Afholdssagens historie`: virtual, book, 5 parts, 0 missing, order scan, proofread
    - chapter: Forord (`Afholdssagens historie/Forord`)
    - chapter: I (`Afholdssagens historie/I`) [hierarchy]
    - chapter: II (`Afholdssagens historie/II`) [hierarchy]
    - chapter: III (`Afholdssagens historie/III`) [hierarchy]
    - chapter: XXII (`Afholdssagens historie/XXII`) [hierarchy]
1. `Sigurd Slembe`: virtual, book, 1 parts, 0 missing, order single, proofread
    - chapter: 1 (`Sigurd Slembe/1/1`)
1. `Ezechiels Syner`: virtual, collection, 1 parts, 0 missing, order single, unknown, excluded under-300-bytes
    - text: Résumé på fransk (`Ezechiels Syner/Résumé`)
1. `Topografisk-statistisk beskrivelse over Finmarkens amt`: virtual, book, 5 parts, 3 missing, order scan-volumes, proofread
    - volume: 1 (`Topografisk-statistisk beskrivelse over Finmarkens amt/1`)
      - chapter: Innledning (`Topografisk-statistisk beskrivelse over Finmarkens amt/1/2`) [container]
      - chapter: 3 (`Topografisk-statistisk beskrivelse over Finmarkens amt/1/3`) [container]
    - volume: 2 (`Topografisk-statistisk beskrivelse over Finmarkens amt/2`) [hierarchy]
      - text: Finnekonger (`Topografisk-statistisk beskrivelse over Finmarkens amt/2/67`) [container]
1. `Illustreret Norges Historie`: virtual, book, 7 parts, 0 missing, order scan, proofread
    - chapter: 17 (`Illustreret Norges Historie/4/17`)
    - chapter: 18 (`Illustreret Norges Historie/4/18`) [hierarchy]
    - chapter: 19 (`Illustreret Norges Historie/4/19`) [hierarchy]
    - chapter: 20 (`Illustreret Norges Historie/4/20`) [hierarchy]
    - chapter: 21 (`Illustreret Norges Historie/4/21`) [hierarchy]
    - chapter: 22 (`Illustreret Norges Historie/4/22`) [hierarchy]
    - chapter: 23 (`Illustreret Norges Historie/4/23`) [hierarchy]
1. `Det nye Testamentet (1886)`: virtual, collection, 1 parts, 0 missing, order single, unknown
    - text: Juleevangeliet (`Det nye Testamentet (1886)/Juleevangeliet`)
1. `Norske kongesagaer`: virtual, book, 4 parts, 0 missing, order scan, proofread
    - text: Oversætterens fortale (`Norske kongesagaer/Oversætterens fortale`)
    - text: Snorre Sturlasøns fortale (`Norske kongesagaer/Snorre Sturlasøns fortale`) [hierarchy]
    - text: Ynglinge-saga (`Norske kongesagaer/Ynglinge-saga`) [hierarchy]
    - text: Halvdan Svartes saga (`Norske kongesagaer/Halvdan Svartes saga`) [hierarchy]
1. `En Sommer i Finnmarken, Russisk Lapland og Nordkarelen`: virtual, book, 2 parts, 0 missing, order scan, proofread
    - chapter: Forord (`En Sommer i Finnmarken, Russisk Lapland og Nordkarelen/Forord`)
    - chapter: 1 (`En Sommer i Finnmarken, Russisk Lapland og Nordkarelen/1`) [hierarchy]
1. `08-073828PRI-OTIR`: virtual, book, 1 parts, 0 missing, order single, unknown
    - chapter: 06 (`08-073828PRI-OTIR/06`)
1. `08-156741TVI-OTIR`: virtual, book, 1 parts, 0 missing, order single, unknown
    - chapter: 01 (`08-156741TVI-OTIR/01`)
1. `Topografisk-statistisk beskrivelse over Bratsberg amt`: virtual, book, 6 parts, 7 missing, order scan-volumes, proofread
    - volume: 1 (`Topografisk-statistisk beskrivelse over Bratsberg amt/1`)
      - chapter: 1 (`Topografisk-statistisk beskrivelse over Bratsberg amt/1/1`) [container]
      - text: Beliggenhed (`Topografisk-statistisk beskrivelse over Bratsberg amt/1/2`) [container]
      - text: Gammel norsk tidsregning og primstaven (`Topografisk-statistisk beskrivelse over Bratsberg amt/1/95`) [container]
    - volume: 2 (`Topografisk-statistisk beskrivelse over Bratsberg amt/2`) [hierarchy]
      - text: Vinje herred (`Topografisk-statistisk beskrivelse over Bratsberg amt/30`) [container]
1. `Ordbog over det gamle norske Sprog`: virtual, book, 6 parts, 0 missing, order scan, proofread
    - text: A (`Ordbog over det gamle norske Sprog/A`)
    - text: a (`Ordbog over det gamle norske Sprog/a`) [hierarchy]
    - text: á 1 (`Ordbog over det gamle norske Sprog/á 1`) [hierarchy]
    - text: á 2 (`Ordbog over det gamle norske Sprog/á 2`) [hierarchy]
    - text: á 3 (`Ordbog over det gamle norske Sprog/á 3`) [hierarchy]
    - text: at 1 (`Ordbog over det gamle norske Sprog/at 1`) [hierarchy]
1. `Den kristelige Troeslære i dens Grundtræk`: virtual, book, 4 parts, 0 missing, order scan, proofread
    - chapter: Forord (`Den kristelige Troeslære i dens Grundtræk/Forord`)
    - chapter: Indledning (`Den kristelige Troeslære i dens Grundtræk/Indledning`) [hierarchy]
    - chapter: 1 (`Den kristelige Troeslære i dens Grundtræk/1`) [hierarchy]
    - chapter: 2 (`Den kristelige Troeslære i dens Grundtræk/2`) [hierarchy]
1. `Almuens Sanger`: virtual, book, 1 parts, 0 missing, order single, proofread
    - text: Forerindring (`Almuens Sanger/Forerindring`)
1. `05-130602TVI-OTIR`: virtual, book, 1 parts, 0 missing, order single, unknown
    - chapter: 06 (`05-130602TVI-OTIR/06`)
1. `Lappisk Mytologi`: virtual, book, 1 parts, 0 missing, order single, unknown, excluded no-text-pages
    - chapter: Forord (`Lappisk Mytologi/Forord`)
1. `Karl Marx`: virtual, book, 3 parts, 0 missing, order scan, proofread
    - chapter: Forord (`Karl Marx/Forord`)
    - chapter: 1 (`Karl Marx/1`) [hierarchy]
    - text: Etterskrift (`Karl Marx/Etterskrift`) [hierarchy]
1. `11-007057ENE-OTIR`: virtual, document, 1 parts, 0 missing, order single, validated
    - chapter: 08 (`11-007057ENE-OTIR/08`)
1. `Niels Klims underjordiske Reise`: virtual, book, 11 parts, 0 missing, order scan, proofread
    - chapter: Fortale (`Niels Klims underjordiske Reise/Fortale`)
    - chapter: Første Kapitel (`Niels Klims underjordiske Reise/Første Kapitel`) [hierarchy]
    - chapter: Andet Kapitel (`Niels Klims underjordiske Reise/Andet Kapitel`) [hierarchy]
    - chapter: Tredie Kapitel (`Niels Klims underjordiske Reise/Tredie Kapitel`) [hierarchy]
    - chapter: Fierde Kapitel (`Niels Klims underjordiske Reise/Fierde Kapitel`) [hierarchy]
    - chapter: Femte Kapitel (`Niels Klims underjordiske Reise/Femte Kapitel`) [hierarchy]
    - chapter: Siette Kapitel (`Niels Klims underjordiske Reise/Siette Kapitel`) [hierarchy]
    - chapter: Syvende Kapitel (`Niels Klims underjordiske Reise/Syvende Kapitel`) [hierarchy]
    - chapter: Ottende Kapitel (`Niels Klims underjordiske Reise/Ottende Kapitel`) [hierarchy]
    - text: Abelins Tillæg (`Niels Klims underjordiske Reise/Abelins Tillæg`) [hierarchy]
    - chapter: Efterskrift (`Niels Klims underjordiske Reise/Efterskrift`) [hierarchy]
1. `Det nye Testamente (1856)`: virtual, collection, 3 parts, 0 missing, order natural, proofread
    - text: Evangelium paa Christi Fødselsdag (`Det nye Testamente (1856)/Evangelium paa Christi Fødselsdag`)
    - text: St Johannis 2det Brev (`Det nye Testamente (1856)/St Johannis 2det Brev`) [hierarchy]
    - text: St Johannis 3die Brev (`Det nye Testamente (1856)/St Johannis 3die Brev`) [hierarchy]
1. `Ær dæt Fårnuftigt at have Religion`: virtual, book, 1 parts, 0 missing, order single, proofread
    - chapter: Fortale (`Ær dæt Fårnuftigt at have Religion/Fortale`)
1. `Østers-Kulturen`: virtual, book, 5 parts, 0 missing, order scan, proofread
    - chapter: 11 (`Østers-Kulturen/11`)
    - chapter: 12 (`Østers-Kulturen/12`) [hierarchy]
    - chapter: 13 (`Østers-Kulturen/13`) [hierarchy]
    - chapter: 14 (`Østers-Kulturen/14`) [hierarchy]
    - chapter: 15 (`Østers-Kulturen/15`) [hierarchy]
1. `Brevet til de Ebræer`: virtual, book, 1 parts, 0 missing, order single, proofread
    - chapter: Fortale (`Brevet til de Ebræer/Fortale`)
1. `Norge i 1814, fra russiske Arkiver`: virtual, book, 3 parts, 0 missing, order scan, proofread
    - chapter: 1 (`Norge i 1814, fra russiske Arkiver/1`)
    - chapter: 2 (`Norge i 1814, fra russiske Arkiver/2`) [hierarchy]
    - chapter: 3 (`Norge i 1814, fra russiske Arkiver/3`) [hierarchy]
1. `11-098785MED-OTIR`: virtual, book, 1 parts, 0 missing, order single, proofread
    - chapter: 01 (`11-098785MED-OTIR/01`)
1. `Urd`: virtual, collection, 1 parts, 0 missing, order single, unknown
    - text: Pianist Sigvart Høgh-Nilsen (`Urd/1907/Pianist Sigvart Høgh-Nilsen`)
1. `Jødedommen`: virtual, book, 1 parts, 0 missing, order single, proofread
    - chapter: 1 (`Jødedommen/1`)
1. `Gudekvæde`: virtual, book, 2 parts, 0 missing, order scan, validated
    - text: Forerindring (`Gudekvæde/Forerindring`)
    - text: Fyriord (`Gudekvæde/Fyriord`) [hierarchy]
1. `Reisehaandbog over Norge`: virtual, book, 2 parts, 0 missing, order scan, unproofread
    - chapter: Forord (`Reisehaandbog over Norge/Forord`)
    - chapter: 1 (`Reisehaandbog over Norge/1`) [hierarchy]

### E: subpage texts reached from a disambiguation or versions page (33)

1. text “Malakias” (`Bibelen (1891)/Profeten Malakias`), part 14 of `Bibelen (1891)` (book, 26 parts, 26 texts)
1. text “Juleevangeliet” (`Bibelen (1891)/Juleevangeliet`), part 26 of `Bibelen (1891)` (book, 26 parts, 26 texts)
1. text “Johannes’s første almindelige Brev” (`Bibelen (1891)/Johannes's første almindelige Brev`), part 21 of `Bibelen (1891)` (book, 26 parts, 26 texts)
1. text “Joel” (`Bibelen (1891)/Profeten Joel`), part 4 of `Bibelen (1891)` (book, 26 parts, 26 texts)
1. text “Nahum” (`Bibelen (1891)/Profeten Nahum`), part 9 of `Bibelen (1891)` (book, 26 parts, 26 texts)
1. text “Evangelium paa Christi Fødselsdag” (`Det nye Testamente (1856)/Evangelium paa Christi Fødselsdag`), part 0 of `Det nye Testamente (1856)` (collection, 3 parts, 3 texts)
1. text “Peters andet almindelige Brev” (`Bibelen (1891)/Peters andet almindelige Brev`), part 20 of `Bibelen (1891)` (book, 26 parts, 26 texts)
1. text “Ruts Bog” (`Bibelen (1891)/Ruts Bog`), part 1 of `Bibelen (1891)` (book, 26 parts, 26 texts)
1. text “Judas’ Brev” (`Det nye Testamente (1883-1889)/Judas' Brev`), part 19 of `Det nye Testamente (1883-1889)` (book, 20 parts, 6 texts)
1. text “03 (Lukkede dører)” (`11-119207ENE-OTIR/03 (Lukkede dører)`), part 2 of `11-119207ENE-OTIR` (book, 3 parts, 3 texts)
1. text “Judas’s almindelige Brev” (`Bibelen (1891)/Judas's almindelige Brev`), part 24 of `Bibelen (1891)` (book, 26 parts, 26 texts)
1. text “Johannes’s andet Brev” (`Bibelen (1891)/Johannes's andet almindelige Brev`), part 22 of `Bibelen (1891)` (book, 26 parts, 26 texts)
1. text “Obadias” (`Bibelen (1891)/Profeten Obadias`), part 6 of `Bibelen (1891)` (book, 26 parts, 26 texts)
1. text “Jonas” (`Bibelen (1891)/Profeten Jonas`), part 7 of `Bibelen (1891)` (book, 26 parts, 26 texts)
1. text “Ruths Bog” (`Trende Bibelske Bøger/Ruths Bog`), part 2 of `Trende Bibelske Bøger` (book, 3 parts, 3 texts)
1. text “Apostelen Pauli Brev til Romararne” (`Det nye Testamente (1883-1889)/Brev til Romararne`), part 11 of `Det nye Testamente (1883-1889)` (book, 20 parts, 6 texts)
1. text “Paulus’s Brev til Filemon” (`Bibelen (1891)/Paulus's Brev til Filemon`), part 17 of `Bibelen (1891)` (book, 26 parts, 26 texts)
1. text “Evangeliet etter Markus” (`Det nye Testamente (1883-1889)/Markus`), part 4 of `Det nye Testamente (1883-1889)` (book, 20 parts, 6 texts)
1. text “Johannes’s Aabenbaring” (`Bibelen (1891)/Johannes's Aabenbaring`), part 25 of `Bibelen (1891)` (book, 26 parts, 26 texts)
1. text “Prædikerens Bog” (`Bibelen (1891)/Prædikerens Bog`), part 2 of `Bibelen (1891)` (book, 26 parts, 26 texts)
1. text “Mika” (`Bibelen (1891)/Profeten Mika`), part 8 of `Bibelen (1891)` (book, 26 parts, 26 texts)
1. text “03 (Uniform)” (`11-119207ENE-OTIR/03 (Uniform)`), part 1 of `11-119207ENE-OTIR` (book, 3 parts, 3 texts)
1. text “Paulus’s Brev til Titus” (`Bibelen (1891)/Paulus's Brev til Titus`), part 16 of `Bibelen (1891)` (book, 26 parts, 26 texts)
1. text “Peters første almindelige Brev” (`Bibelen (1891)/Peters første almindelige Brev`), part 19 of `Bibelen (1891)` (book, 26 parts, 26 texts)
1. text “Haggaj” (`Bibelen (1891)/Profeten Haggaj`), part 12 of `Bibelen (1891)` (book, 26 parts, 26 texts)
1. text “Johannes’s tredje Brev” (`Bibelen (1891)/Johannes's tredje almindelige Brev`), part 23 of `Bibelen (1891)` (book, 26 parts, 26 texts)
1. text “Amos” (`Bibelen (1891)/Profeten Amos`), part 5 of `Bibelen (1891)` (book, 26 parts, 26 texts)
1. text “Paulus’s andet Brev til Tessalonikerne” (`Bibelen (1891)/Paulus's andet Brev til Tessalonikerne`), part 15 of `Bibelen (1891)` (book, 26 parts, 26 texts)
1. text “Sakarias” (`Bibelen (1891)/Profeten Sakarias`), part 13 of `Bibelen (1891)` (book, 26 parts, 26 texts)
1. text “Zefanias” (`Bibelen (1891)/Profeten Zefanias`), part 11 of `Bibelen (1891)` (book, 26 parts, 26 texts)

### all searchable subpage texts (chapter titles that the label patterns miss show up here) (3,683)

1. text “Reiseskizzer.” (`Ephemerer/21`), part 21 of `Ephemerer` (book, 21 parts, 21 texts)
1. text “Abascal” (`Norsk Haandlexikon for almennyttige Kundskaber/Abascal`), part 158 of `Norsk Haandlexikon for almennyttige Kundskaber` (collection, 1,138 parts, 1,136 texts)
1. text “Ferdmanns-Kvila” (`I Heimegrendi/2`), part 2 of `I Heimegrendi` (book, 11 parts, 11 texts)
1. text “Aldstone-Moor” (`Norsk Haandlexikon for almennyttige Kundskaber/Aldstone-Moor`), part 697 of `Norsk Haandlexikon for almennyttige Kundskaber` (collection, 1,138 parts, 1,136 texts)
1. text “Hvorfor knælte han” (`Negeren med de hvite hænder/30`), part 30 of `Negeren med de hvite hænder` (book, 39 parts, 39 texts)
1. text “§ 14.” (`De norske Klostres Historie i Middelalderen/3/14`), part 35 of `De norske Klostres Historie i Middelalderen` (book, 58 parts, 56 texts)
1. text “Alen” (`Norsk Haandlexikon for almennyttige Kundskaber/Alen`), part 703 of `Norsk Haandlexikon for almennyttige Kundskaber` (collection, 1,138 parts, 1,136 texts)
1. text “Holbergs gamle Dage” (`Ludvig Holberg (Daae)/9`), part 9 of `Ludvig Holberg (Daae)` (book, 10 parts, 9 texts)
1. text “Stallo-vagge” (`Lappiske Eventyr og Folkesagn/Stallo-vagge`), part 38 of `Lappiske Eventyr og Folkesagn` (book, 53 parts, 52 texts)
1. text “Paulus’s Brev til Filemon” (`Bibelen (1891)/Paulus's Brev til Filemon`), part 17 of `Bibelen (1891)` (book, 26 parts, 26 texts)
1. text “Norgesbannerets Drøm” (`Digte (Lie)/04`), part 4 of `Digte (Lie)` (book, 28 parts, 28 texts)
1. text “Albatani” (`Norsk Haandlexikon for almennyttige Kundskaber/Albatani`), part 627 of `Norsk Haandlexikon for almennyttige Kundskaber` (collection, 1,138 parts, 1,136 texts)
1. text “Akiba” (`Norsk Haandlexikon for almennyttige Kundskaber/Akiba`), part 551 of `Norsk Haandlexikon for almennyttige Kundskaber` (collection, 1,138 parts, 1,136 texts)
1. text “SLUTNING.” (`Fjerdemand/42`), part 42 of `Fjerdemand` (book, 42 parts, 42 texts)
1. text “Alcobaça” (`Norsk Haandlexikon for almennyttige Kundskaber/Alcobaça`), part 677 of `Norsk Haandlexikon for almennyttige Kundskaber` (collection, 1,138 parts, 1,136 texts)
1. text “Viktor Rydberg” (`Essays. Fremmede Forfattere/01`), part 1 of `Essays. Fremmede Forfattere` (book, 8 parts, 8 texts)
1. text “Fogden i Vadsø, som gjorde sig til en Stallo” (`Lappiske Eventyr og Folkesagn/Fogden i Vadsø, som gjorde sig til en Stallo`), part 33 of `Lappiske Eventyr og Folkesagn` (book, 53 parts, 52 texts)
1. text “FRITZI OG LOTTE” (`Storhertuginnen av Speilsalen/33`), part 33 of `Storhertuginnen av Speilsalen` (book, 37 parts, 37 texts)
1. text “Vildt” (`Lærebog i de forskjellige Grene af Huusholdningen/31`), part 31 of `Lærebog i de forskjellige Grene af Huusholdningen` (book, 63 parts, 62 texts)
1. text “Nye Svenske Intriger. Kronprindsens Besøg 1788. Stemninger i Christiania under den franske Revolution” (`Det gamle Christiania/16`), part 16 of `Det gamle Christiania` (book, 22 parts, 21 texts)
1. text “Albendorf” (`Norsk Haandlexikon for almennyttige Kundskaber/Albendorf`), part 634 of `Norsk Haandlexikon for almennyttige Kundskaber` (collection, 1,138 parts, 1,136 texts)
1. text “Adramyttium” (`Norsk Haandlexikon for almennyttige Kundskaber/Adramyttium`), part 346 of `Norsk Haandlexikon for almennyttige Kundskaber` (collection, 1,138 parts, 1,136 texts)
1. text “Amrit” (`Norsk Haandlexikon for almennyttige Kundskaber/Amrit`), part 1067 of `Norsk Haandlexikon for almennyttige Kundskaber` (collection, 1,138 parts, 1,136 texts)
1. text “Adolf af Nassau” (`Norsk Haandlexikon for almennyttige Kundskaber/Adolf af Nassau`), part 335 of `Norsk Haandlexikon for almennyttige Kundskaber` (collection, 1,138 parts, 1,136 texts)
1. text “Aar og Dag” (`Norsk Haandlexikon for almennyttige Kundskaber/Aar og Dag`), part 129 of `Norsk Haandlexikon for almennyttige Kundskaber` (collection, 1,138 parts, 1,136 texts)
1. text “Slutnings-Ord” (`Excentriske Prædikanter/Slutt`), part 1 of `Excentriske Prædikanter` (book, 2 parts, 1 texts)
1. text “Troerne drives bort fra skibene.” (`Iliaden/15`), part 31 of `Iliaden` (book, 50 parts, 49 texts)
1. text “§ 1.” (`De norske Klostres Historie i Middelalderen/3/1`), part 22 of `De norske Klostres Historie i Middelalderen` (book, 58 parts, 56 texts)
1. text “Vedlegg 8” (`Anbefaling av en sunnhetsmerkeordning/Vedlegg 8`), part 20 of `Anbefaling av en sunnhetsmerkeordning` (document, 27 parts, 27 texts)
1. text “Andre akt” (`John Gabriel Borkman/Andre akt`), part 3 of `John Gabriel Borkman` (book, 5 parts, 2 texts)

### C: oversized (11)

1. `Realitets-Procedure og Dom i Rigsretssagen imod Hs. Excellence Statsminister m. m. Løvenskiold`: single, single, 1 parts, 0 missing, order –, proofread
    - text: Realitets-Procedure og Dom i Rigsretssagen imod Hs. Excellence Statsminister m. m. Løvenskiold
1. `Norske Rigs-Registranter`: subpages, book, 19 parts, 11 missing, order single, unproofread
    - front: Norske Rigs-Registranter
    - volume: Første Bind (`Norske Rigs-Registranter/1`) [hierarchy]
      - chapter: Forord (`Norske Rigs-Registranter/I/Forord`) [container]
      - text: Frederik den 1ste (`Norske Rigs-Registranter/Frederik den 1ste`) [container]
      - text: Christian den 3die (`Norske Rigs-Registranter/Christian den 3die`) [container]
      - chapter: 1559 (`Norske Rigs-Registranter/1559`) [container]
      - chapter: 1560 (`Norske Rigs-Registranter/1560`) [container]
      - chapter: 1561 (`Norske Rigs-Registranter/1561`) [container]
      - chapter: 1562 (`Norske Rigs-Registranter/1562`) [container]
      - chapter: 1563 (`Norske Rigs-Registranter/1563`) [container]
      - chapter: 1564 (`Norske Rigs-Registranter/1564`) [container]
      - chapter: 1565 (`Norske Rigs-Registranter/1565`) [container]
    - … 8 more
1. `Ordbog over det norske Folkesprog`: subpages, book, 32 parts, 0 missing, order toc, proofread
    - toc: Ordbog over det norske Folkesprog
    - text: Tittel (`Ordbog over det norske Folkesprog/Tittel`) [container]
    - chapter: Fortale (`Ordbog over det norske Folkesprog/Fortale`) [container]
    - text: Anmærkninger om Udtalen (`Ordbog over det norske Folkesprog/Anmærkninger om Udtalen`) [container]
    - text: Forkortninger (`Ordbog over det norske Folkesprog/Forkortninger`) [container]
    - text: A (`Ordbog over det norske Folkesprog/A`) [container]
    - text: Aa (`Ordbog over det norske Folkesprog/Aa`) [container]
    - text: B (`Ordbog over det norske Folkesprog/B`) [container]
    - chapter: D (`Ordbog over det norske Folkesprog/D`) [container]
    - text: E (`Ordbog over det norske Folkesprog/E`) [container]
    - text: F (`Ordbog over det norske Folkesprog/F`) [container]
    - text: G (`Ordbog over det norske Folkesprog/G`) [container]
    - … 21 more
1. `Angaaende Forandringer i Lovgivningen om Husmænd`: single, book, 1 parts, 0 missing, order –, proofread
    - text: Angaaende Forandringer i Lovgivningen om Husmænd
1. `En Krønike om Erkebiskopperne i Nidaros`: single, book, 1 parts, 0 missing, order –, unproofread
    - text: En Krønike om Erkebiskopperne i Nidaros
1. `Historisk Tidsskrift`: mixed, collection, 403 parts, 86 missing, order scan-volumes, unproofread
    - toc: Historisk Tidsskrift
    - volume: Andet Bind (`Historisk Tidsskrift/Anden Række/Andet Bind`) [hierarchy]
      - text: Om nogle middelalderske Slægter i det vestenfjeldske Norge [container]
      - text: To utrykte Digte af Petter Dass [container]
      - text: Stiftsprovst Dr. Theol. Otto Holmboes Dagbogs-Optegnelser 1751–1773 [container]
      - text: Armfeldts Tog Nordenfjelds 1718 (`Armfeldts tog nordenfjelds 1718`) [container]
      - text: Vikingetogenes tidligste Udgangspunkter [container]
      - text: Erik af Pommerns, Danmarks, Sveriges og Norges Konges, Giftermaal med Philippa, Prindsesse af England [container]
      - text: Den Bergenske Biskop Arnes Bibliothek [container]
      - text: Uddrag af Bergens Kongsgaards Regnskaber 1516–23 (`Uddrag af Bergens kongsgaards regnskaber 1516–23`) [container]
      - text: Ravnebryllupet i Kraakelund [container]
      - text: Om en paatænkt Restauration af Throndhjems Domkirke i Begyndelsen af det 18de Aarhundrede (`Om en paatænkt restauration af Trondhjems domkirke i begyndelsen af det 18de aarhundrede`) [container]
    - … 392 more
1. `Theologisk Tidsskrift for den evangelisk-lutherske Kirke i Norge`: mixed, book, 24 parts, 15 missing, order scan-volumes, unproofread
    - toc: Theologisk Tidsskrift for den evangelisk-lutherske Kirke i Norge
    - chapter: Andet Bind (`Theologisk Tidsskrift for den evangelisk-lutherske Kirke i Norge/2`) [hierarchy]
    - chapter: 3 (`Om Mag. Niels Svendsen Krønikes Stridigheder med Præsteskabet i Christiania i Aarene 1642–1652/3`) [container]
    - text: Mag. Jørgen Erichsson, den tredje evangeliske Superintendent i Stavanger Stift [container]
    - text: Bidrag til den tromsøiske „Vækkelses“ Historie [container]
    - text: Et Bidrag til Mag. Kjeld Stubs Historie [container]
    - chapter: Fjerde Bind (`Theologisk Tidsskrift for den evangelisk-lutherske Kirke i Norge/4`) [hierarchy]
    - chapter: 2 (`Syv throndhjemske Præsters Bønskrifter til Kirkestyrelsen om Skjærpelse i Kirketugten og Oprettelse af en Kirkeret/2`) [container]
    - chapter: Første Bind (`Theologisk Tidsskrift for den evangelisk-lutherske Kirke i Norge/1`) [hierarchy]
    - chapter: 1 (`Om Mag. Niels Svendsen Krønikes Stridigheder med Præsteskabet i Christiania i Aarene 1642–1652/1`) [container]
    - chapter: 2 (`Om Mag. Niels Svendsen Krønikes Stridigheder med Præsteskabet i Christiania i Aarene 1642–1652/2`) [container]
    - chapter: Niende Bind (`Theologisk Tidsskrift for den evangelisk-lutherske Kirke i Norge/9`) [hierarchy]
    - … 13 more
1. `Biskop Jens Nilssøns visitatsbøger og reiseoptegnelser 1574–1597`: linked, book, 21 parts, 6 missing, order toc, unproofread
    - toc: Biskop Jens Nilssøns visitatsbøger og reiseoptegnelser 1574–1597
    - volume: Biskop Jens Nilssøns liv og virksomhed 1538–1600 [container]
      - chapter: I. (`Biskop Jens Nilssøns liv og virksomhed 1538–1600/1`) [container]
      - chapter: II. (`Biskop Jens Nilssøns liv og virksomhed 1538–1600/2`) [container]
      - chapter: III. (`Biskop Jens Nilssøns liv og virksomhed 1538–1600/3`) [container]
      - chapter: IV. (`Biskop Jens Nilssøns liv og virksomhed 1538–1600/4`) [container]
      - chapter: V. (`Biskop Jens Nilssøns liv og virksomhed 1538–1600/5`) [container]
      - chapter: VI. (`Biskop Jens Nilssøns liv og virksomhed 1538–1600/6`) [container]
      - chapter: VII. (`Biskop Jens Nilssøns liv og virksomhed 1538–1600/7`) [container]
      - chapter: VIII. (`Biskop Jens Nilssøns liv og virksomhed 1538–1600/8`) [container]
      - chapter: IX. (`Biskop Jens Nilssøns liv og virksomhed 1538–1600/9`) [container]
      - chapter: X. (`Biskop Jens Nilssøns liv og virksomhed 1538–1600/10`) [container]
    - … 10 more
1. `Fru Bovary`: mixed, book, 6 parts, 0 missing, order scan, proofread
    - toc: Fru Bovary
    - chapter: Første del (`Fru Bovary/1`) [container]
    - text: Fru Bovary: Første del [container]
    - chapter: Annen del (`Fru Bovary/2`) [container]
    - text: Fru Bovary: Annen del [container]
    - chapter: Tredje del (`Fru Bovary/3`) [container]
    - text: Fru Bovary: Tredje del [container]
1. `Norsk Tidsskrift for Videnskab og Litteratur`: mixed, collection, 163 parts, 27 missing, order toc, unproofread
    - toc: Norsk Tidsskrift for Videnskab og Litteratur
    - volume: Første Aargang (`Norsk Tidsskrift for Videnskab og Litteratur/1`) [hierarchy]
      - chapter: Forord (`Forord (Norsk Tidsskrift for Videnskab og Litteratur 1)`) [container]
      - text: Brudstykker af Kong Haakon Magnussøns Historie (`Brudstykker af Kong Haakon V Magnussøns Historie`) [container]
      - text: Udledelse af nogle norske Sted- og Folkenavne [container]
      - text: i vor Kirke (`Kroningsaktens Betydning i vor Kirke`) [container]
      - text: Bidrag til Norges Historie under Unionen [container]
      - text: Nordmændenes Boliger og daglige Sysler [container]
      - text: B. Moes Tidsskrift for norsk Personalhistorie [container]
      - text: Norge i Tegninger. 1ste Hefte [container]
      - text: Nor, Tidsskrift for Videnskab og Litteratur [container]
      - text: Norges gamle Love för 1387 (`Norges gamle Love før 1387 (Fritzner)`) [container]
    - … 152 more
1. `Det norske Folkesprogs Grammatik`: single, book, 1 parts, 7 missing, order –, unproofread
    - text: Det norske Folkesprogs Grammatik

### G: reference works (0)


### H: deep nesting (5)

1. `Samlede Værker (Kielland)`: mixed, book, 157 parts, 3 missing, order scan-volumes, proofread
    - toc: Samlede Værker (Kielland)
    - volume: Første Bind (`Samlede Værker (Kielland)/1`) [container]
      - text: Novelletter [container]
      - text: Haabet er lysegrønt [container]
      - volume: Garman og Worse [container]
        - chapter: XXI. (`Garman og Worse/21`) [hierarchy]
        - chapter: XXII. (`Garman og Worse/22`) [hierarchy]
        - chapter: XXIII. (`Garman og Worse/23`) [hierarchy]
        - chapter: XXIV. (`Garman og Worse/24`) [hierarchy]
        - chapter: XXV. (`Garman og Worse/25`) [hierarchy]
        - chapter: XXVI. (`Garman og Worse/26`) [hierarchy]
      - text: Tre Smaastykker for Scenen [container]
    - … 146 more
1. `Det norske Folks Historie`: mixed, book, 691 parts, 23 missing, order scan-volumes, proofread
    - toc: Det norske Folks Historie
    - volume: I-1-1 (`Det norske Folks Historie/1`) [hierarchy]
      - chapter: 1. Norges geographiske og physiske Forhold. (`Det norske Folks Historie/1/1`) [container]
      - chapter: 2. Urbeboere. Finner. Oldtidslevninger. (`Det norske Folks Historie/1/2`) [container]
      - chapter: 3. Levninger fra ældre Befolkninger i Sydsverige og Danmark. (`Det norske Folks Historie/1/3`) [container]
      - chapter: 4. Nordmændene og den germaniske Stamme. (`Det norske Folks Historie/1/4`) [container]
      - chapter: 5. Germanernes ældste Hjem og Omgivelser. Kelter, Skyther, Tschuder. (`Det norske Folks Historie/1/5`) [container]
      - chapter: 6. Grækernes og Romernes Efterretninger om Norden. (`Det norske Folks Historie/1/6`) [container]
      - chapter: 7. Oldgermaniske Vandringssagn. (`Det norske Folks Historie/1/7`) [container]
      - chapter: 8. Indvandringsvejen. Gardarike. Russer. (`Det norske Folks Historie/1/8`) [container]
      - chapter: 9. De germaniske Folks Navn i Almindelighed, og vore Forfædres i Særdeleshed. (`Det norske Folks Historie/1/9`) [container]
      - chapter: 10. Goterne og den gotiske Kulturperiode i Norden. Gauter, Daner o. fl. (`Det norske Folks Historie/1/10`) [container]
    - … 680 more
1. `Nils Kjær Samlede skrifter`: mixed, collection, 20 parts, 4 missing, order scan, proofread
    - toc: Nils Kjær Samlede skrifter
    - volume: Bind 5. Epistler og noveller (`Nils Kjær Samlede skrifter/5`) [container]
      - text: EPISTLER (`Nils Kjær Epistler`) [container]
      - text: Hugormen [container]
      - text: Epistel angaaende sjøormen [container]
      - text: Magsveir [container]
      - text: Julibrev fra Son [container]
      - text: Rousseaus skygge [container]
      - text: Vestlandsreise [container]
      - text: Brekkestøbreve [container]
      - text: Schillers svigermor [container]
      - text: Den stjaalne frak [container]
    - … 9 more
1. `Folkevennen`: mixed, collection, 178 parts, 198 missing, order scan-volumes, unproofread
    - toc: Folkevennen
    - volume: Første Aargang (`Folkevennen/1852`) [hierarchy]
      - text: Til Tidsskriftets Læsere [container]
      - text: Et Par Smaatræk af Folkelivet [container]
      - text: Lidt om Bøger og Læsning [container]
      - text: Nogle Sprogbemærkninger [container]
      - text: Han Nils Prest [container]
      - text: Om Aarsagerne til Arbejdslønnens Forskjellighed [container]
      - text: Om Landalmuens Næringsdrift og Levemaade [container]
      - text: Henrik Wergeland – Fædrelandskjærlighed [container]
    - volume: Anden Aargang (`Folkevennen/1853`) [hierarchy]
      - text: Gjætlugutin [container]
    - … 167 more
1. `Diplomatarium Norvegicum`: subpages, book, 20 parts, 2 missing, order single, unproofread
    - toc: Diplomatarium Norvegicum
    - volume: 1 (`Diplomatarium Norvegicum/1`) [hierarchy]
      - chapter: Forord (`Diplomatarium Norvegicum/1/Forord`) [container]
      - chapter: 3 (`Diplomatarium Norvegicum/1/3`) [container]
      - chapter: 7 (`Diplomatarium Norvegicum/1/7`) [container]
      - chapter: 8 (`Diplomatarium Norvegicum/1/8`) [container]
      - chapter: 51 (`Diplomatarium Norvegicum/1/51`) [container]
      - chapter: 177 (`Diplomatarium Norvegicum/1/177`) [container]
      - volume: Hjælpemidler til Beregningen af de i norske Oldbreve forekommende Tidsbestemmelser (`Diplomatarium Norvegicum/1/Hjælpemidler`) [hierarchy]
        - chapter: I. (`Diplomatarium Norvegicum/1/Hjælpemidler/I`) [hierarchy]
        - chapter: II. (`Diplomatarium Norvegicum/1/Hjælpemidler/II`) [hierarchy]
        - chapter: III. (`Diplomatarium Norvegicum/1/Hjælpemidler/III`) [hierarchy]
    - … 9 more

### I: works with other editions (0)


