# de.wikisource: structure and quality survey (WP1a)

Generated 2026-10-31 by `python -I -m wscatalog.survey de` from `dewikisource-20261001-pages-articles.xml.bz2` (resolution and quality rules: PLAN.md 5.2 steps 1–1h and 5.4; config `pipeline/config/de.yaml`). Resolution took 8 s on the cached extract.

Input: 310,980 ns0 pages, 13,381 ns0 redirects, 339,510 scan pages, 7,112 Index pages, 773 pages with the `disambiguation` page prop.

## 1. Works

26,617 works resolved from 80,339 non-excluded ns0 pages; 26,015 enter the catalog after the hard exclusions (section 9). Multi-part works hold 53,774 parts, of which 47,732 are searchable texts.

| structure | works | parts | texts |
|---|---|---|---|
| single | 25,673 | 25,673 | 0 |
| linked | 431 | 20,734 | 20,149 |
| subpages | 372 | 10,193 | 7,488 |
| mixed | 89 | 22,629 | 19,947 |
| virtual | 52 | 218 | 148 |

| type (step 1e) | works |
|---|---|
| single | 25,673 |
| collection | 800 |
| book | 144 |

Largest works by parts:

| work | structure | type | parts | texts | missing | rules of the parts |
|---|---|---|---|---|---|---|
| Paulys Realencyclopädie der classischen Altertumswissenschaft | subpages | collection | 1,325 | 1,297 | 0 | hierarchy 1325 |
| Der Sagenschatz des Königreichs Sachsen | mixed | collection | 1,001 | 1,001 | 0 | container 1001 |
| Zerstreute Blätter | linked | collection | 793 | 779 | 0 | container 787, toc 6 |
| Album der Rittergüter und Schlösser im Königreiche Sachsen | linked | collection | 775 | 757 | 13 | container 775 |
| Christliche Symbolik | subpages | collection | 636 | 633 | 0 | hierarchy 636 |
| Die Gartenlaube (1898) | mixed | collection | 624 | 592 | 91 | toc 594, hierarchy 30 |
| Die Gartenlaube (1899) | mixed | collection | 624 | 595 | 226 | toc 596, hierarchy 28 |
| Die Gartenlaube (1896) | mixed | collection | 613 | 561 | 77 | toc 561, hierarchy 52 |
| Badisches Sagen-Buch | linked | collection | 600 | 596 | 0 | container 600 |
| Die Gartenlaube (1887) | mixed | collection | 525 | 468 | 0 | toc 472, hierarchy 53 |
| Die Gartenlaube (1888) | mixed | collection | 522 | 470 | 37 | toc 470, hierarchy 52 |
| Die Gartenlaube (1854) | mixed | collection | 491 | 434 | 1 | toc 439, hierarchy 52 |
| Geschichte von Kloster Heilsbronn | subpages | collection | 484 | 482 | 0 | hierarchy 484 |
| Die Gartenlaube (1878) | mixed | collection | 478 | 426 | 0 | toc 426, hierarchy 52 |
| Die Gartenlaube (1886) | mixed | collection | 475 | 422 | 5 | toc 423, hierarchy 52 |

## 2. Exclusions (step 1)

| reason | pages |
|---|---|
| reference-prefix | 218,074 |
| author-page | 8,822 |
| excluded-template | 1,763 |
| disambiguation | 773 |
| under-excluded | 668 |
| no-header | 488 |
| excluded-category | 53 |

## 3. Patterns A–I (WS§1)

|  | pattern | count |
|---|---|---|
| A | collection of root pages (`structure = linked`) | 431 works, 20,734 parts |
| B | mixed subpages and root pages (`mixed`) | 89 works, 22,629 parts |
| C | work with an oversized page (> oversized_bytes, transcluded scan pages included) | 317 works |
| D | missing root, virtual root (`virtual`) | 52 works, 218 parts |
| E | texts inside a subpage collection reached from a disambiguation or versions page | 50 parts (42 searchable); all searchable subpage texts: 7,883 |
| F | whole-text duplicate subpages (excluded) | 0 pages |
| G | reference works (type `reference`) | 0 works, 0 parts; 218,074 pages excluded by title prefix |
| H | deep nesting (parts at depth ≥ 2, i.e. three levels below the root) | 7 works |
| I | several editions: works with an other-versions template | 49 works; 0 versions pages excluded |

Tree depth of multi-part works (levels below the root): 1: 857, 2: 80, 3: 5, 4: 2.

## 4. Parent rules and order sources (steps 1b, 1c)

| parent rule | pages |
|---|---|
| container | 29,101 |
| own | 26,565 |
| toc | 17,844 |
| hierarchy | 6,829 |

| order source (nodes with ≥ 2 children) | nodes |
|---|---|
| chain | 563 |
| toc | 192 |
| toc-partial | 115 |
| natural | 77 |
| scan | 32 |

`toc-partial` (implementation choice, see section 13): the TOC lists at least half of the children, which keep its order; the rest follow in natural order. 35 `natural` nodes have a TOC that lists fewer than half.

Root pages attached by their header's container link: 21,497; most frequent containers: `Der Sagenschatz des Königreichs Sachsen` 999; `Die zehnte Muse` 472; `Deutsche Sagen (Brüder Grimm, Band 1)` 360; `Journal von und für Franken` 354; `Badisches Sagen-Buch II` 352; `Die Volkssagen von Pommern und Rügen` 287; `Das Ausland (1828)` 268; `Badisches Sagen-Buch I` 246; `Buch der Lieder` 242; `Deutsche Sagen (Brüder Grimm, Band 2)` 223.

Pages attached by TOC membership (rule 3): 17,844; most frequent TOC pages: `Die Gartenlaube (1899)` 596; `Die Gartenlaube (1898)` 594; `Die Gartenlaube (1896)` 561; `Die Gartenlaube (1887)` 472; `Die Gartenlaube (1888)` 470; `Die Gartenlaube (1854)` 439; `Die Gartenlaube (1878)` 426; `Die Gartenlaube (1886)` 423; `Die Gartenlaube (1885)` 415; `Die Gartenlaube (1892)` 394.

## 5. Container links that fail rule 1

692 of 29,795 pages whose header carries a container link (2.3 %) point to:

- outside-ns0: 494 (e.g. `Fridericus Rex` → `w:de:Die Weltbühne`, `Die Nacht (Büchner)` → `commons:Image:Georg Büchner - Franzos-Werkausgabe 395.jpg`, `Brunner im Amt` → `w:Die Weltbühne`, `Joebbels` → `w:de:Die Weltbühne`)
- excluded: 155 (e.g. `Altdresdner Familienleben` → `Dresdner Geschichtsverein`, `Sagen aus der Provinz Sachsen VII` → `Zeitschrift für Volkskunde`, `Clausthalischer allgemeiner Harz-Berg-Calender auf das Jahr 1805` → `Clausthal`, `Der Meßner zu Bamberg` → `Alexander Schöppner`)
- missing: 43 (e.g. `Knecht Ruprecht` → `:Bild:De Storm Gedichte 1889 143.gif`, `Agrumi/Der Geliebten` → `Agrumi`, `Ueber die Haide` → `:Bild:De Storm Gedichte 1889 177.gif`, `Allgemeiner Harz-Berg-Kalender für das Jahr 1953` → `Clausthal-Zellerfeld`)

## 6. Prev/next chains across Index boundaries

Of 23,546 next-links between pages that both have an Index, 3,169 (13.5 %) cross to a different Index.
Examples: `TBHB 1953-03-31` (TBHB 1953-03-31) → `TBHB 1953-04-01` (TBHB 1953-04-01); `TBHB 1943-03-22` (TBHB 1943-03-22) → `TBHB 1943-03-28` (TBHB 1943-03-28); `TBHB 1951-08-01` (TBHB 1951-08-01) → `TBHB 1951-08-02` (TBHB 1951-08-02); `TBHB 1935-05-13` (TBHB 1935-05-13) → `TBHB 1935-05-14` (TBHB 1935-05-14).

Root-page works that sit on a prev/next chain but stay separate works (no container, not in a unique TOC; WS§8 item 5): 3,355.

## 7. Orphan trees, oversized pages, red links

**Orphan trees** (virtual roots): 52, holding 218 parts. Examples: `Source-Provider` (1); `Heinrich Heine` (2); `Der Handlungsreisende wie er sein soll` (1); `In und um Tirols Oberammergau` (2); `Aus den Arbeitssälen des Kunsthandwerks` (2); `Ausgewählte Abhandlungen des Bischofs Aphraates` (8); `Zeitschriften (Veterinärmedizin)` (1); `Aus den politischen Salons des neuen Italiens` (1).

**Oversized pages** (> 300,000 bytes of wikitext including transcluded scan pages): 549. Largest: `Ahrenshoop vor und im Krieg` 4.0 MB; `Weg nach Berlin – Birkenwerder` 2.2 MB; `Kunst und Kultur in Ahrenshoop 1945 bis 1948` 2.1 MB; `Paulys Realencyclopädie der classischen Altertumswissenschaft/Register/Hans Gärtner` 2.0 MB; `Jugend (Zeitschrift)/Inhalt 1896–1910` 1.9 MB; `Jugend (Zeitschrift)/Inhalt 1911–1929` 1.8 MB; `Hans Brass und die Religion, 1934` 1.5 MB; `Paulys Realencyclopädie der classischen Altertumswissenschaft/Register/R` 1.5 MB; `Der Merker/Inhalt` 1.5 MB; `Allgemeine Wiener Musik-Zeitung` 1.5 MB.

**Red links in TOCs** (missing parts): 148 multi-part works have red links, 974 in total; median share of the TOC 6%. Most missing: `Die Gartenlaube (1899)` 226 of 850; `Gesammelte Schriften über Musik und Musiker` 114 of 237; `Die Gartenlaube (1898)` 91 of 715; `Die Gartenlaube (1896)` 77 of 690; `Die Gartenlaube (1888)` 37 of 559; `Illustrirte Zeitung` 35 of 115; `Die Grenzboten (1841/1842)` 27 of 52; `Schwäbische Chronik (Thomas Lirer)` 23 of 24.

## 8. Colon prefixes with ≥ 100 root pages

| prefix | root pages | status |
|---|---|---|
| RE: | 98,892 | excluded |
| MKL1888: | 38,529 | excluded |
| ADB: | 30,780 | excluded |
| BLKÖ: | 27,210 | excluded |
| Zedler: | 16,549 | excluded |
| Boetticher: | 5,257 | excluded |
| Haan: | 801 | excluded |
| Rittergüter und Schlösser im Königreiche Sachsen: | 620 | kept |
| Topographia Circuli Burgundici: | 514 | kept |
| Topographia Austriacarum: | 431 | kept |
| Schwere, Elektricität und Magnetismus: | 412 | kept |
| Hantzsch: | 390 | kept |
| Allgemeines Deutsches Kommersbuch: | 385 | kept |
| Topographia Sueviae: | 382 | kept |
| Topographia Bohemiae, Moraviae et Silesiae: | 375 | kept |
| Topographia Electoratus Brandenburgici et Ducatus Pomeraniae: | 338 | kept |
| Topographia Franconiae: | 319 | kept |
| Elektrische Kraft Hertz: | 312 | kept |
| Die Schedelsche Weltchronik (deutsch): | 298 | kept |
| Die Siegel der Deutschen Kaiser und Könige: | 292 | kept |
| Topographia Superioris Saxoniae: | 265 | kept |
| Topographia Braunschweig Lüneburg: | 228 | kept |
| Topographia Alsatiae: | 220 | kept |
| Topographia Westphaliae: | 217 | kept |
| Topographia Palatinatus Rheni: | 217 | kept |
| Topographia Hassiae: | 208 | kept |
| Hervorragende Persönlichkeiten in Dresden und ihre Wohnungen: | 174 | kept |
| Topographia Saxoniae Inferioris: | 172 | kept |
| Meißner Künstler: | 167 | kept |
| Die Ursache des Einschlagens vom Blitze: | 165 | kept |
| Med. Topographie Gmuend: | 150 | kept |
| Pomologische Monatshefte: | 135 | kept |
| Stammbuch berümpter Fürsten, Graffen vnnd Herren Geschlechter: | 128 | kept |
| Topographia Helvetiae, Rhaetiae et Valesiae: | 128 | kept |
| Taufmatrikel Passau Sankt Stephan: | 123 | kept |
| Eygentliche Beschreibung Aller Stände auff Erden: | 122 | kept |

## 9. Quality and completeness (PLAN.md 5.4)

**Hard exclusions**: 602 works — no-text 306, under-300-bytes 296.

| tier | works | share | texts in them | source |
|---|---|---|---|---|
| validated | 14,414 | 55.4 % | 37,936 | page-levels 9,691, status-template 4,723 |
| proofread | 6,999 | 26.9 % | 8,306 | status-template 4,907, page-levels 2,092 |
| unproofread | 266 | 1.0 % | 156 | status-template 157, page-levels 109 |
| unknown | 4,336 | 16.7 % | 1,334 | none 4,336 |

14 works are proofread by the ≥ 90 % rule. 229 of the 266 unproofread works have no page above level 1 (raw OCR).

**Incomplete**: 2,168 works — template 1,991, missing-parts 165, missing-pages 12.
Works with missing scan pages inside a transcluded range: 17 (77 pages).
1 works transclude scan pages of files the dump has no Page: pages for (counted as no signal, not as missing).
Wikisource's own translations (labelled, kept): 0.

Status values (`bearbeitungsstand`, `status`): `fertig` 50,522, `korrigiert` 9,015, `unvollständig` 2,040, `unkorrigiert` 342, `unvollständig (geplant)` 60, `off` 25, `teilkorrigiert` 4, `korrekturprobleme` 3, `fehler korr.` 1.

**Unknown works** (no signal): 4,336 in the catalog after the junk rules; the junk rules removed 296 (under-300-bytes 296).
Size: median 2,967 bytes, p10 1,061, p90 12,410; multi-part 42.

30 random unknown works:

- `Die Schedelsche Weltchronik (deutsch):166` (single, 9,813 B): CLXV verso: == Das sechst alter thumb\|right\|Linea der bebst // Constantinus der ander Jar der werlt .v m .ix c .lxiij.Jar christi .vij c .lxiiij. 5963. Jahr der
- `Stammbuch berümpter Fürsten, Graffen vnnd Herren Geschlechter:Vorwort 10` (single, 2,903 B): Querfurt der halff jhm wider auff. Item da Heinrich der Lewe durch seinen stoltz dem Hertzogthumb Sachsen einen mechtigen abbruch gethan hatte / erweckte Gott a
- `Berlinische Musikalische Zeitung` (single, 30,843 B): Digitalisat== Jg. 1793-1794 (mehr nicht erschienen) , Inhalt== [Spazier, Johann Gottlieb Carl]: Carneval zu Berlin, in: Berlinische Musikalische Zeitung, St. 1,
- `Berichte der staatlichen Höhlenkommission` (single, 6,692 B): im Zeitraum 1 (1920) - 2,3 (1921): Berichte der Staatlichen Höhlenkommission im Zeitraum 2,4 (1921) - 3,1/2 (1922): Berichte der Bundeshöhlenkommission 1.1920 z
- `Rhea` (single, 3,167 B): Erstes Heft 1846 , Inhalt des ersten Heftes. Vorwort. Seite V Zur Weihe. Seite VII Protokoll der ornithologischen Section der Gesellschaft deutscher Naturforsch
- `Allgemeines Deutsches Kommersbuch:295` (single, 3,696 B): colspan="2" valign="top" \| 2. Und als man rüstet auf Weihnachtszeit, da war der Neckar gefroren, da stund ein Mann im Pilgramskleid wohl vor des Pfarrhofs Thore
- `Die Messtechnik (Zeitschrift)` (single, 1,551 B): __TOC__ 1.1925 - 21.1945 nachgewiesen 1.1925 2.1926 3.1927 von UB Michigan erbeten 23.10.2025 (Digitalisierung dauert ca. 1 Jahr) 4.1928 5.1929 = = (Digitalisie
- `Taufmatrikel Passau Sankt Stephan:Band 1:Seite 80` (single, 1,047 B): Doctor Metzger Frau Sänglin Aprilis=== 3 Ambrosius filius Joannis Fendlmayr Posaunblaser et Apollonia Coniugum Patrinus Leonardus Hockh Spittlmaister, 30 Anna f
- `Elektrische Kraft Hertz:155` (single, 2,768 B): 9. Die Kräfte elektrischer Schwingungen. schreitende elektrostatische Kraft superponire. Richtiger deuten wir wohl im Sinne unserer Theorie die Erscheinung, ind
- `Allgemeines Deutsches Kommersbuch:47` (single, 2,845 B): colspan="2" valign="top" \| 1. Sie ha=ben Tod und Ver=der=ben gespien: Wir ha=ben es nicht ge=lit=ten, zwei Ko=lonnen Fuß=volk, zwei Batte=rien, wir haben sie ni
- `Verhandlungen der Deutschen Gesellschaft für Gynäkologie` (single, 3,222 B): __TOC__ Ab 17.1922 zugl. einzelne Bd. von: Archiv für Gynäkologie (ISSN: 0003-9128) Register 1.1886 - 15.1913 = () 1.1886 = (1), = (1), = (1-3) 2.1888 = (2), = 
- `Stammbuch berümpter Fürsten, Graffen vnnd Herren Geschlechter:Seite 11` (single, 2,968 B): Weitern vnnd volkommenlichern bericht von diser Edlen Herrschafft / würt (ob gott will) thu o n der Erwürdige vnd wolgelehrte Herr M. Cyriacus Spangenberg in se
- `Deutsche illustrierte Bienenzeitung` (single, 8,758 B): darin aufgegangen : Deutscher Bienenfreund (3.1867,8 - 41.1905) Europäische Bienenzucht auf amerikanischer Grundlage (1.1906,Jan. - 2.1907,8) Die Imkerschule (1
- `Die Schedelsche Weltchronik (deutsch):061` (single, 7,284 B): LX verso: == class="text" \| Das vierd alter Amos tet vbel vor dem herren vnd wardt von seinen knechten geslagen. vnd starb in seinem aigen haws geto e dtet. vnd
- `Hansische Geschichtsblätter` (single, 25,482 B): Diese Linkzusammenstellung ist nicht mehr nötig. Der die ZS herausgebende Hansische Geschichtsverein stellt auf seiner Seite alle Jahrgänge kostenlos zur Verfüg
- `Schwere, Elektricität und Magnetismus:325` (single, 2,005 B): Die Volta-Induction. Neumann’s Gesetz. Die Summe der beiden Ausdrücke (3) und (4) ist das vollständige Differential der Function welche der Function entgegenges
- `Schwere, Elektricität und Magnetismus:087` (single, 2,904 B): Satz von Green. Aus (2) und (3) geht dann ohne weiteres der Satz hervor: Dieser Satz ist gültig, wenn im Innern des Raumes die Functionen und , sowie die ersten
- `Zeitschrift für die österreichischen Mittelschulen` (single, 950 B): __TOC__ 1.1923/24(1924) = 2.1925 = 3.1926/27(1928) =
- `Physikalisch-chemisches Centralblatt` (single, 2,305 B): __TOC__ 1.1904 = , = , = , = 2.1905 = , = , = , = 3.1906 = , = , = , = , = 4.1907/08 = , = , = , = , = , = 5.1908 = , = , = , = , = 6.1908/09 = , = , = , = , =
- `Schwere, Elektricität und Magnetismus:117` (single, 1,936 B): Anziehung eines homogenen elliptischen Cylinders. zu beachten, in denen und positiv sind. Diese Gleichungen geben zu erkennen, dass die positive Summe grösser s
- `Zeitschrift für experimentelle Pathologie und Therapie` (single, 6,152 B): , Berlin 1.1905 - 23.1921 mit 23.1921 Zusammenschluss mit Zeitschrift für die gesamte experimentelle Medizin einschließlich experimentelle Chirurgie 1.1905 - 23
- `Eygentliche Beschreibung Aller Stände auff Erden:Der Kürschner` (single, 623 B): align="center" align="center"\|250px Wol her / ich fütter Röck vnd Schaubn / Mach schürtzbeltz / brustthüch / Vehehaubn / Von Zöbel / Marder / Vehe vnd Lüchsen /
- `Schwere, Elektricität und Magnetismus:265` (single, 2,420 B): Hülfssatz aus der Analysis. Eintrittsstellen, dagegen negativ an allen Austrittsstellen. Bezeichnet man also (nach Zahlwerth und Vorzeichen) mit die Projection 
- `Quartalschrift für katholische Geistliche` (single, 2,104 B): __TOC__ siehe auch die Wiederauflage Theologisch-Praktische Monatschrift zunächst für Seelsorger (3. verbesserte Auflage Prag 1821-1828) 1.1812 Erster Band Zwey
- `Deutsche Forst-Zeitung` (single, 8,722 B): __TOC__ Bibliographische Informationen == Aufgegangen in: Der deutsche Forstwirt Beilage: Des Försters Feierabende 1886[?] – 1924 Beilage: Forstliche Rundschau 
- `Aus dem Archiv der Deutschen Seewarte` (single, 20,826 B): 1.1878 - 63.1943/44,2[?] (1878) bis 57 (1937) Bundesamt für Seeschifffahrt und Hydrographie https://digitale-bibliothek.bsh.de/viewer/toc/67938/ (1875) bis (191
- `Elektrische Kraft Hertz:107` (single, 2,702 B): 6. Inductionserscheinungen in Isolatoren. schiebung des Leiters AA′ nach oben haben würde. Qualitativ die gleiche Wirkung würde ein oberhalb AA′ angebrachter zw
- `Stammbuch berümpter Fürsten, Graffen vnnd Herren Geschlechter:Seite 4` (single, 2,815 B): von Dingenstat / der sie also zu o m Stifft bracht Anno 1260. Chro. Magd. in vita Ludolphi Metrop. lib. 8. cap. 11. Es seind auch etliche hernacher von den Bisc
- `Beyträge zu der Naturgeschichte des Schweizerlandes` (single, 686 B): 1.1775: uni-goettingen.de 1.1775,2: digitale-sammlungen.de
- `Zeitschriften (Kunst)` (virtual, 26,947 B): __TOC__ Bibliographic information == new title 1: The new monthly assemblée new title 2: The court magazine : containing original papers Nationallizenz 1806 - 1

**Default view** (validated or proofread, complete, not reference): 19,315 works with 35,199 texts; with unknown works shown: 23,619 works (36,532 texts). Works in the default view whose pages are all level 1: 0. Current setting: `show_unknown_by_default = None`.

## 10. Parts table size (WS§5.2)

| parts_cap | works with catalog parts | parts rows | parts table, gzipped | parts_fts, gzipped |
|---|---|---|---|---|
| 500 | 933 | 46,617 | 2.83 MB | 0.78 MB |
| 2,000 | 944 | 54,666 | 3.28 MB | 0.86 MB |
| ∞ (all, reference works included) | 944 | 54,666 | 3.28 MB | 0.86 MB |
| 2,000, compact encoding | 944 | 54,666 | 2.99 MB | 0.86 MB |

Rows count multi-part works only, one row per part plus the root row. 25,071 single-page works would add one row each if stored. Sizes are a SQLite file holding only the table (with its index) at gzip level 9. The compact encoding (not in WS§5.1, measured for the cap decision) stores a part's page_title as the suffix after the work root's title where possible, display_title only when it differs from the last title segment, and no per-part quality_pages.

## 11. Named cases (PLAN.md WP1 acceptance, WS§9)

- `Der Sagenschatz des Königreichs Sachsen`: work, mixed, type collection, 1,001 parts (1,001 texts), 0 missing, order chain, oversized False, quality validated (page-levels), complete True.
- `Kinder- und Haus-Märchen Band 1 (1857)`: work, linked, type collection, 86 parts (86 texts), 0 missing, order chain, oversized False, quality validated (page-levels), complete True.
- `Der Froschkönig oder der eiserne Heinrich (1857)`: part 1 of `Kinder- und Haus-Märchen Band 1 (1857)` (text, display “Der Froschkönig oder der eiserne Heinrich”, rule container, searchable True).
- `Kinder und Hausmärchen`: redirect to `Kinder- und Hausmärchen`, which is excluded (excluded-template).
- `Kinder- und Hausmärchen`: excluded (excluded-template).
- `Die Heimath in der neuen Welt`: work, virtual, type book, 50 parts (2 texts), 0 missing, order chain, oversized False, quality validated (page-levels), complete True.
- `Käthe`: work, single, type single, 1 parts (0 texts), 0 missing, order –, oversized True, quality validated (page-levels), complete True.
- `Erlkönig`: work, single, type single, 1 parts (0 texts), 0 missing, order –, oversized False, quality validated (status-template), complete True.

## 13. Implementation notes

- When a page's container link names one of its title ancestors, the tree parent is its deepest existing ancestor below that container (is and en subpages often name the root, skipping an existing section page).
- The Index TOC field (e.g. no `Sammendrag`) counts as TOC links only for the page its Index title field links to; otherwise every member would look TOC-like.
- Display titles come from `current`/`section`/`TITEL`, else the link text in the parent's TOC, else the last title segment. A work's first part row is its root (`front` above 400 characters of own text, else `toc`); `part_count` excludes it.
- Missing parts are red links in the TOCs of the work's root and volumes: subpages of the root always, other titles only in linked or mixed works and on TOC-like pages.
- Junk rules (cleanup or deletion template, placeholder, under 300 bytes) apply to works without scan pages and without any quality signal.
- Order (step 1c): when no source covers all children but the TOC lists at least half, the listed children keep the TOC order and the rest follow in natural order (`toc-partial`), so that collections are not shown alphabetically (WS§2 rule 3).
- Rule 3: a TOC page is marked as one (de `HERKUNFT = off`) or is its Index's own book; "reached from it by the prev/next chain" is read as: the member's chain starts at the TOC page or its prev or next page is listed on the same TOC page.
- A container with several links (de `HERKUNFT`) resolves to the first link that shares the page's Index, else the first link (the KHM tales link the editions overview first).
- Volume placement: a part attached by its container is placed under the one TOC page (under 2,000 bytes of own text, listing at least three members) of the same work that lists it, unless the container's members already form one prev/next chain.
- A slash inside unclosed parentheses is part of the name (de `X (Die Gartenlaube 1871/17)`), not a subpage separator.
- Exclusions: a page with scan transclusion is never a disambiguation page (fr dictionary roots carry the prop); subpages inherit only portal and author-page exclusions; on de, portal pages are marked by `Themendaten`, `Ortsdaten`, `Listendaten` (and the categories Thema, Orte, Listen when the page has no header), and a root page without header template, scans or status is a portal (`no-header`) unless other pages name it as their container.
- A bare `<pages index=X/>` renders the Index's TOC field, not the book.

## 12. Random samples for eyeballing (30 per pattern)

### A: collections of root pages (431)

1. `Turngedichte`: linked, collection, 33 parts, 0 missing, order toc, validated
    - front: Turngedichte
    - text: Turnermarsch (`Turner-Marsch`) [container]
    - text: Klimmzug [container]
    - text: Freiübungen [container]
    - text: Während der Riesenwelle [container]
    - text: Am Barren [container]
    - text: Ringkampf [container]
    - text: Zum Aufstellen der Geräte [container]
    - text: Wettlauf [container]
    - text: Fußball [container]
    - text: Der Athlet [container]
    - text: Am Hängetau [container]
    - … 22 more
1. `Schweizer-Skizzen`: linked, collection, 9 parts, 0 missing, order chain, validated
    - toc: Schweizer-Skizzen
    - text: Von Lindau bis Konstanz (`Polnische Dichtung in deutschem Gewande/Von Lindau bis Konstanz`) [container]
    - text: Mer de glace (`Polnische Dichtung in deutschem Gewande/Mer de glace`) [container]
    - text: Solothurn (`Polnische Dichtung in deutschem Gewande/Solothurn`) [container]
    - text: Mont Blanc (`Polnische Dichtung in deutschem Gewande/Mont Blanc`) [container]
    - text: Verneyaz (`Polnische Dichtung in deutschem Gewande/Verneyaz`) [container]
    - text: Gießbach (`Polnische Dichtung in deutschem Gewande/Gießbach`) [container]
    - text: Chillon (`Polnische Dichtung in deutschem Gewande/Chillon`) [container]
    - text: Rigi (`Polnische Dichtung in deutschem Gewande/Rigi`) [container]
    - text: Mein Adler (`Polnische Dichtung in deutschem Gewande/Mein Adler`) [container]
1. `Geschichte der protestantischen Theologie`: linked, collection, 4 parts, 0 missing, order single, validated
    - toc: Geschichte der protestantischen Theologie
    - volume: Spener und der Pietismus [container]
      - text: Die Zeit von Spener und Francke (`Spener und der Pietismus/Die Zeit von Spener und Francke`) [container]
      - text: Joh. Albr. Bengel und seine Schule (`Spener und der Pietismus/Joh. Albr. Bengel und seine Schule`) [container]
      - text: Zinzendorf und die Brüdergemeine (`Spener und der Pietismus/Zinzendorf und die Brüdergemeine`) [container]
1. `Tagebuch einer musikalischen Reise`: linked, collection, 25 parts, 0 missing, order chain, validated
    - front: Tagebuch einer musikalischen Reise
    - text: Titel (`Burney Tagebuch 3/Titel`) [container]
    - chapter: Vorrede des Uebersetzers (`Burney Tagebuch 3/Vorrede des Uebersetzers`) [container]
    - text: Böhmen (`Burney Tagebuch 3/Böhmen`) [container]
    - text: Prag (`Burney Tagebuch 3/Prag`) [container]
    - text: Dresden (`Burney Tagebuch 3/Dresden`) [container]
    - text: Leipzig (`Burney Tagebuch 3/Leipzig`) [container]
    - text: Berlin (`Burney Tagebuch 3/Berlin 1`) [container]
    - text: Potsdam (`Burney Tagebuch 3/Potsdam`) [container]
    - text: Berlin (`Burney Tagebuch 3/Berlin 2`) [container]
    - text: Hamburg (`Burney Tagebuch 3/Hamburg`) [container]
    - text: Bremen (`Burney Tagebuch 3/Bremen`) [container]
    - … 14 more
1. `Kinder- und Haus-Märchen Band 1 (1857)`: linked, collection, 86 parts, 0 missing, order chain, validated
    - front: Kinder- und Haus-Märchen Band 1 (1857)
    - text: Der Froschkönig oder der eiserne Heinrich (`Der Froschkönig oder der eiserne Heinrich (1857)`) [container]
    - text: Katze und Maus in Gesellschaft (`Katze und Maus in Gesellschaft (1857)`) [container]
    - text: Marienkind (`Marienkind (1857)`) [container]
    - text: Mährchen von einem, der auszog das Fürchten zu lernen (`Mährchen von einem, der auszog das Fürchten zu lernen (1857)`) [container]
    - text: Der Wolf und die sieben jungen Geislein (`Der Wolf und die sieben jungen Geislein (1857)`) [container]
    - text: Der treue Johannes (`Der treue Johannes (1857)`) [container]
    - text: Der gute Handel (`Der gute Handel (1857)`) [container]
    - text: Der wunderliche Spielmann (`Der wunderliche Spielmann (1857)`) [container]
    - text: Die zwölf Brüder (`Die zwölf Brüder (1857)`) [container]
    - text: Das Lumpengesindel (`Das Lumpengesindel (1857)`) [container]
    - text: Brüderchen und Schwesterchen (`Brüderchen und Schwesterchen (1857)`) [container]
    - … 75 more
1. `Buch der Katastrophen`: linked, collection, 20 parts, 0 missing, order chain, validated
    - front: Buch der Katastrophen
    - text: Aus einem rheinischen Städtchen [container]
    - text: Der gute Mensch [container]
    - text: Onkel Bogumil trinkt [container]
    - text: Die Bluse [container]
    - text: Vom treuen Leser [container]
    - text: Lillichens Verlobung [container]
    - text: Der Tierfreund [container]
    - text: Die vierbeinige Gefahr [container]
    - text: Theater (`Theater (Schmitz)`) [container]
    - text: Der interessante Kopf [container]
    - text: Beethoven (`Beethoven (Schmitz)`) [container]
    - … 9 more
1. `Zeitung für die elegante Welt`: linked, collection, 8 parts, 0 missing, order natural, proofread
    - front: Zeitung für die elegante Welt
    - text: Die dießjährige Kunstausstellung in Dresden (`Die dießjährige Kunstausstellung in Dresden (1807)`) [container]
    - text: Die Dresdner Ausstellung von 1808 und Prof. Seidelmanns Kopie der Madonna von Raphael [container]
    - text: Die Kunstausstellung in Dresden. Erster Brief. (`Die Kunstausstellung in Dresden. Erster Brief`) [container]
    - text: Einige Worte über die Gemähldeausstellung im Brühlschen Garten in Dresden (`Einige Worte über die Gemähldeausstellung im Brühlschen Garten in Dresden (1804)`) [container]
    - text: Erinnerungen von der Dresdner Kunstausstellung im Jahre 1802 [container]
    - text: Etwas über die Dresdener Kunstausstellung von 1811 [container]
    - text: Ueber die diesjährige Kunstausstellung zu Dresden [container]
    - text: Ueber einige leicht übersehene Gemälde der jetzigen Dresdner Ausstellung [container]
1. `Sagen aus der Provinz Sachsen VII`: linked, collection, 8 parts, 0 missing, order chain, validated
    - toc: Sagen aus der Provinz Sachsen VII
    - text: Pumput [container]
    - text: Der Spuk in der Scheune [container]
    - text: Der Spuk in den Hürden [container]
    - text: Der Spuk im Teiche [container]
    - text: Der Schatz unter der Tenne [container]
    - text: Das Schatzfeuer [container]
    - text: Der Drachenbaum [container]
    - text: Der Teufelsstein bei Zerbst [container]
1. `Märchen (Montzheimer)`: linked, collection, 7 parts, 0 missing, order chain, validated
    - front: Märchen (Montzheimer)
    - text: Die Harfe der Königin [container]
    - text: Der Erdbeerkönig [container]
    - text: Die wilde Marinka [container]
    - text: Was Hannchen im Walde erlebte [container]
    - text: Springmännchen und Goldfasan [container]
    - text: Stiefmütterchen [container]
    - text: Das dankbare Heinzelmännchen [container]
1. `Gedichte (Heine, 1822)`: linked, collection, 19 parts, 0 missing, order toc-partial, validated
    - front: Gedichte (Heine, 1822)
    - text: Die Wundermaid [toc]
    - text: Minnegruß [toc]
    - text: Ständchen eines Mauren [toc]
    - text: Der schlimmste Wurm: des Zweifels Dolchgedanken [container]
    - text: Zufrieden nicht mit deinem Eigenthume [container]
    - text: An den Hofrath Georg S. in Göttingen [container]
    - text: An J. B. R. [container]
    - text: Die Welt war mir nur eine Marterkammer [container]
    - text: Die Nacht auf dem Drachenfels [container]
    - text: An Str. (`Wenn der Frühling kommt mit dem Sonnenschein`) [container]
    - text: An Alexander, Pr. von W. (`Eine große Landstraß’ ist unsre Erd’`) [container]
    - … 8 more
1. `Deutsche Sagen (Brüder Grimm, Band 2)`: linked, collection, 223 parts, 0 missing, order chain, validated
    - front: Deutsche Sagen (Brüder Grimm, Band 2)
    - text: Der heilige Salzfluß [container]
    - text: Der heilige See der Hertha [container]
    - text: Der heilige Wald der Semnonen [container]
    - text: Die Wanderung der Ansivaren [container]
    - text: Die Seefahrt der Usipier [container]
    - text: Wanderung der Gothen [container]
    - text: Die eingefallene Brücke [container]
    - text: Warum die Gothen in Griechenland eingebrochen [container]
    - text: Fridigern [container]
    - text: Des Königs Grab [container]
    - text: Athaulfs Tod [container]
    - … 212 more
1. `Jahrbuch der Preußischen Kunstsammlungen`: linked, collection, 1 parts, 0 missing, order single, validated
    - front: Jahrbuch der Preußischen Kunstsammlungen
    - text: Zu einem Bilde von Hieronymus Bosch in der Figdorschen Sammlung in Wien [container]
1. `Adolf Loos – Sämtliche Schriften`: linked, collection, 63 parts, 0 missing, order chain, validated
    - front: Adolf Loos – Sämtliche Schriften
    - chapter: Vorwort 1921/1931 (Loos) [container]
    - text: Lederwaren und Gold- und Silberschmiedekunst [container]
    - text: Die Herrenmode [container]
    - text: Der neue Stil und die Bronze-Industrie [container]
    - text: Interieurs [container]
    - text: Die Interieurs in der Rotunde [container]
    - text: Das Sitzmöbel [container]
    - text: Glas und Ton [container]
    - text: Das Luxusfuhrwerk [container]
    - text: Die Plumber [container]
    - text: Die Herrenhüte [container]
    - … 52 more
1. `Aus dem Märchenschatz der Kaschubei`: linked, collection, 20 parts, 0 missing, order chain, validated
    - front: Aus dem Märchenschatz der Kaschubei
    - text: Der kluge Matz [container]
    - text: Die ungetreue Frau [container]
    - text: Der fromme Tagelöhner und der habgierige Nachbar [container]
    - text: Der Soldat und der Jude [container]
    - text: Der Jungbrunnen [container]
    - text: Drei Gläser Wein [container]
    - text: Der Schmied vor der Höllentür [container]
    - text: Bestrafte Habgier [container]
    - text: Die umgeschmiedete Frau [container]
    - text: Der starke Gottlieb [container]
    - text: Der Soldat und der Teufel [container]
    - … 9 more
1. `BKV Erste Ausgabe Band 38`: linked, collection, 26 parts, 0 missing, order toc, validated
    - toc: BKV Erste Ausgabe Band 38
    - chapter: Einleitung über Leben und Schriften des Aphraates [toc]
    - text: Ausgewählte Abhandlungen des Bischofs Aphraates von Mar Matthäus [toc]
    - chapter: Einleitung über Leben und Schriften des Rabulas [toc]
    - text: Panegyrikus auf Rabulas von einem gleichzeitigen Edessener [toc]
    - text: Bekehrung des Rabulas aus der Biographie des Akömeten Alexander (`Bericht über die Bekehrung des Rabulas aus der Biographie des Akömeten Alexander`) [toc]
    - text: Canones für Mönche [toc]
    - text: Kanones für Priester und Kleriker (`Canones für Priester und Kleriker`) [toc]
    - text: Predigt gegen den Nestorianismus, gehalten zu Konstantinopel [toc]
    - text: Fragment eines Briefes des Rabulas an den hl. Cyrillus [toc]
    - chapter: Brief des hl. Cyrillus an Rabulas [toc]
    - chapter: Brief des Rabulas an den Bischof Gemellinus von Perrha [toc]
    - … 15 more
1. `Die Schnurren des Rochus Mang`: linked, collection, 46 parts, 0 missing, order chain, validated
    - front: Die Schnurren des Rochus Mang
    - text: Notizen über Rochus Mang [container]
    - text: Der Raubschütz von der Irxenklamm [container]
    - text: Wie der Dokter dem Vierhäuslschneider sein schweres Leiden kuriert hat [container]
    - text: Die Seelenwanderung [container]
    - text: Wie der heilige Sankt Petrus nicht hat dreschen mögen [container]
    - text: Von den tapferen Zandlingern [container]
    - text: Flurprozession [container]
    - text: Was aus den hundert Hirschen des Dionys Pfezzerer geworden ist [container]
    - text: Vom Zecken Toni, den die Wildsäu gfressen haben [container]
    - text: Pfingstwunder [container]
    - text: Zwei Paar Füß [container]
    - … 35 more
1. `Freiburg im Breisgau. Die Stadt und ihre Bauten`: linked, collection, 54 parts, 6 missing, order toc, proofread
    - front: Freiburg im Breisgau. Die Stadt und ihre Bauten
    - text: Die Stadt und ihre Umgebung [container]
    - text: Geschichtlicher Ueberblick [container]
    - text: Die Baubehörden [container]
    - text: Technischer Unterricht [container]
    - text: Die Baustoffe [container]
    - text: Die Industrie-Anlagen [container]
    - text: Heizung und Beleuchtung [container]
    - text: Elektrische Uhren [container]
    - text: Das Feuerlöschwesen [container]
    - text: Das Plakatwesen [container]
    - text: Landstrassen, Kreisstrassen und Kreiswege [container]
    - … 43 more
1. `Meyer’s Universum, oder Abbildung und Beschreibung des Sehenswerthesten und Merkwürdigsten der Natur und Kunst auf der ganzen Erde. Siebenzehnter Band`: linked, collection, 47 parts, 0 missing, order chain, proofread
    - toc: Meyer’s Universum, oder Abbildung und Beschreibung des Sehenswerthesten und Merkwürdigsten der Natur und Kunst auf der ganzen Erde. Siebenzehnter Band
    - text: Pompeji (`Pompeji (Meyer’s Universum)`) [container]
    - text: Der große Geyser auf Island (`Der große Geyser in Island`) [container]
    - text: Hohen-Salzburg [container]
    - text: Der See Nicaragua an der Mündung des Las Lajas [container]
    - text: Tiflis [container]
    - text: Die Kapelle von Coldspring am Hudson [container]
    - text: Die Geroldsauer Kaskade bei Baden-Baden [container]
    - text: Das Moselthal [container]
    - text: Morgenscene auf dem obern Mississippi (`Morgenscene auf dem oberen Mississippi`) [container]
    - text: Bei Caën an der bretagne’schen Küste [container]
    - text: Das Grabmal der heil. Genoveva in der Frauenkirche bei Andernach (`Das Grabmal der heiligen Genoveva in der Frauenkirche bei Andernach`) [container]
    - … 36 more
1. `Die Schnupftabaksdose (Sammelband)`: linked, collection, 33 parts, 0 missing, order toc, validated
    - toc: Die Schnupftabaksdose (Sammelband)
    - text: War einmal ein Schwefelholz [container]
    - text: „Nein,“ schimpfte die Ringelnatter [container]
    - text: Es war ein Brikett [container]
    - text: Das Schlüsselloch [container]
    - text: Es trafen sich von ungefähr [container]
    - text: Ein Pflasterstein, der war einmal [container]
    - text: [Der Ohrwurm mochte die Taube nicht leiden] (`Ohrwurm und Taube`) [container]
    - text: Es waren einmal zwei Gummischuh [container]
    - text: Es bildete sich ein Gemisch [container]
    - text: Lackschuh sprach zum Wasserstiebel [container]
    - text: Ein Taschenkrebs und ein Känguruh [container]
    - … 22 more
1. `Taschenbuch von der Donau 1824`: linked, collection, 73 parts, 0 missing, order toc-partial, validated
    - front: Taschenbuch von der Donau 1824
    - text: Crescentia und ihre Schwestern [container]
    - text: Oden aus Horaz [container]
    - text: Konradin. Bruchstück von L. Uhland (`Konradin (Uhland)`) [container]
    - text: Erinnerung und Freude [container]
    - text: Die Burg [container]
    - text: Drey Sonette an Grafen Reinhardt (`Drey Sonnette an Grafen Reinhardt`) [container]
    - text: Dem Könige von Würtemberg [container]
    - text: An Herrn Baron von Laßberg [container]
    - text: Der Kirchhof (`Der Kirchhof (Conz)`) [container]
    - text: An Göttinn Freya [container]
    - text: Nänie [container]
    - … 62 more
1. `Theatri Saxonici Ander Theil`: linked, collection, 2 parts, 0 missing, order natural, validated
    - front: Theatri Saxonici Ander Theil
    - text: Beltzig (`Theatri Saxonici Ander Theil:Belzig`) [container]
    - text: Plauen (`Theatri Saxonici Ander Theil:Plauen`) [container]
1. `Allgemeine deutsche Bibliothek (Musikartikel)`: linked, collection, 1 parts, 0 missing, order single, validated
    - front: Allgemeine deutsche Bibliothek (Musikartikel)
    - text: Auszug eines Schreibens aus – – – vom 27sten Febr. 1788 (`CPE Bach – Auszug eines Schreibens`) [container]
1. `Allgemeiner Harz-Berg-Kalender für das Schaltjahr 1924`: linked, collection, 4 parts, 2 missing, order toc, unproofread
    - front: Allgemeiner Harz-Berg-Kalender für das Schaltjahr 1924
    - text: Mahnung (`Mahnung (Ernst von Wildenbruch)`) [container]
    - text: Im Harze [container]
    - text: Der stille See [container]
    - text: Der Vogelfang [container]
1. `Biographisches Jahrbuch für Altertumskunde`: linked, collection, 1 parts, 2 missing, order single, validated
    - front: Biographisches Jahrbuch für Altertumskunde
    - text: Adolf Philippi (Selbstbiographie) (`Adolf Philippi (Biographisches Jahrbuch für Altertumskunde)`) [container]
1. `Passionsbilder`: linked, collection, 2 parts, 0 missing, order chain, validated
    - front: Passionsbilder
    - text: Eine löbliche Passion [container]
    - text: Das Passionsspiel in Schwäbisch-Gmünd [container]
1. `Palaeontographica`: linked, collection, 2 parts, 1 missing, order toc, validated
    - front: Palaeontographica
    - text: Ueber ein bisher nicht beschriebenes Exemplar von Eurypterus aus devonischen Schichten des Staates New York in Nord-Amerika [container]
    - text: Ueber die Crustaceen der Steinkohlenformation von Saarbrücken [container]
1. `Seifenblasen`: linked, collection, 14 parts, 0 missing, order chain, validated
    - front: Seifenblasen
    - text: Prolog (`Prolog (Laßwitz: Seifenblasen)`) [container]
    - text: Auf der Seifenblase [container]
    - text: Stäubchen [container]
    - text: Apoikis [container]
    - text: Aladdins Wunderlampe [container]
    - text: Aus dem Tagebuche einer Ameise [container]
    - text: Musen und Weise [container]
    - text: Unverwüstlich [container]
    - text: Der Traumfabrikant [container]
    - text: Psychotomie [container]
    - text: Mirax [container]
    - … 3 more
1. `Die schöne Müllerin`: linked, collection, 25 parts, 0 missing, order chain, validated
    - front: Die schöne Müllerin
    - text: Der Dichter, als Prolog [container]
    - text: Wanderschaft [container]
    - text: Wohin? (`Wohin`) [container]
    - text: Halt! [container]
    - text: Danksagung an den Bach [container]
    - text: Am Feierabend [container]
    - text: Der Neugierige [container]
    - text: Das Mühlenleben [container]
    - text: Ungeduld [container]
    - text: Morgengruß [container]
    - text: Des Müllers Blumen [container]
    - … 14 more
1. `Schwänke und Streiche aus Westpreussen II`: linked, collection, 3 parts, 0 missing, order chain, validated
    - toc: Schwänke und Streiche aus Westpreussen II
    - text: Die Katze als Kind [container]
    - text: Die dumme Wirtin [container]
    - text: Der dwatsche Hans [container]
1. `Allgemeiner Harz-Berg-Kalender 1941`: linked, collection, 1 parts, 0 missing, order single, proofread
    - front: Allgemeiner Harz-Berg-Kalender 1941
    - text: Ein berühmter Apotheker in Clausthal (`Ein berühmter Apotheker in Clausthal – Johann Christoph Ilsemann`) [container]

### B: mixed (89)

1. `Die Gartenlaube (1866)`: mixed, collection, 405 parts, 0 missing, order toc-partial, validated
    - front: Die Gartenlaube (1866)
    - text: Goldelse [toc]
    - text: Die Sängerrunde am Weinsberger Thurm [toc]
    - text: Kleine Ursachen, große Wirkung [toc]
    - text: Die Mutter Gottes [toc]
    - text: Eine Alpenstraße [toc]
    - text: Coburgische Erinnerungen an König Leopold von Belgien [toc]
    - text: Onkel und Neffe (`Onkel und Neffe (Die Gartenlaube 1866/1)`) [toc]
    - text: Neuestes Zeloten-Gesangbuch [toc]
    - text: Der Elephant in der Gefangenschaft [toc]
    - text: Eine vergessene Freundin Schiller’s [toc]
    - text: Ein ungarisches Reiterleben [toc]
    - … 394 more
1. `Die Gartenlaube (1897)`: mixed, collection, 414 parts, 1 missing, order toc-partial, validated
    - front: Die Gartenlaube (1897)
    - text: Die Hansebrüder [toc]
    - text: Die Electricität im Hause [toc]
    - text: New Yorks Riesenhäuser [toc]
    - text: Zwischen Ligny und Belle-Alliance [toc]
    - text: Auf ewig vereint [toc]
    - text: Dürer malt seine Frau [toc]
    - text: Ein festlicher Tag [toc]
    - text: Ein Streit um die Stunde [toc]
    - text: Die Sängerin der Heimatliebe [toc]
    - text: Der Schuß auf den Wal [toc]
    - text: ’s Ringl [toc]
    - … 403 more
1. `Kleinrussische Novellen`: mixed, collection, 4 parts, 0 missing, order chain, validated
    - front: Kleinrussische Novellen
    - text: Georg Adam: Ein Jahrhundert kleinrussischer Litteratur (`Ein Jahrhundert kleinrussischer Litteratur`) [container]
    - text: Natur (`Kleinrussische Novellen/Natur`) [container]
    - text: Eine Unzivilisierte (`Kleinrussische Novellen/Eine Unzivilisierte`) [container]
    - text: Eine Schlacht (`Kleinrussische Novellen/Eine Schlacht`) [container]
1. `Zeitschrift für Mathematik und Physik`: mixed, collection, 2 parts, 0 missing, order toc-partial, validated
    - front: Zeitschrift für Mathematik und Physik
    - chapter: Inhalt (`Zeitschrift für Mathematik und Physik/Inhalt`) [hierarchy]
    - text: Die räumliche und zeitliche Ausbreitung der Gravitation [container]
1. `Zeitschrift der Gesellschaft für Erdkunde zu Berlin`: mixed, collection, 72 parts, 0 missing, order toc, validated
    - front: Zeitschrift der Gesellschaft für Erdkunde zu Berlin
    - volume: Zweiter Band (`Zeitschrift der Gesellschaft für Erdkunde zu Berlin/Zweiter Band`) [hierarchy]
      - text: Russische wissenschaftliche Expeditionen im Jahre 1864 und 1865 in Türkistān. (`Russische wissenschaftliche Expeditionen im Jahre 1864 und 1865 in Türkistān`) [container]
      - text: Der Aufstand der Muhamedaner im westlichen China. (`Der Aufstand der Muhamedaner im westlichen China`) [container]
      - text: Die Wege des Landhandels zwischen Rußland und China. (`Die Wege des Landhandels zwischen Rußland und China`) [container]
      - text: Nowaja Semlje. (`Nowaja Semlje`) [container]
    - volume: Vierter Band (`Zeitschrift der Gesellschaft für Erdkunde zu Berlin/Vierter Band`) [hierarchy]
      - text: Die höhlenbewohnenden Kannibalen in Süd-Afrika [container]
    - text: P. v. Ssemenof’s Forschungsreisen in den Trans-Ilischen Alatau und zum Issyk-Kul, (`P. v. Ssemenof’s Forschungsreisen in den Trans-Ilischen Alatau und zum Issyk-Kul`) [container]
    - volume: Fünfter Band. (`Zeitschrift der Gesellschaft für Erdkunde zu Berlin/Fünfter Band`) [hierarchy]
      - text: Eine Besteigung des Pik von Teneriffa [container]
      - text: Wahrnehmungen auf einer Fahrt von Chartūm nach dem Gazellen-Fluß, Januar–März 1869 (`Wahrnehmungen auf einer Fahrt von Chartūm nach dem Gazellen-Fluß`) [container]
    - … 61 more
1. `Die Gartenlaube (1871)`: mixed, collection, 429 parts, 0 missing, order toc-partial, validated
    - front: Die Gartenlaube (1871)
    - text: Pulver und Gold [toc]
    - text: Ein bemoostes Haupt [toc]
    - text: Im Hauptquartier des Prinzen Friedrich Karl (`Im Hauptquartier des Prinzen Friedrich Karl (Die Gartenlaube 1871)`) [toc]
    - text: Eine französische Poststation im deutschen Walde [toc]
    - text: In den Steppen von Afrika [toc]
    - text: Auf unseren Vorposten vor Paris [toc]
    - text: Die Kämpfe um Brie und Villiers [toc]
    - text: Einiges über den Aberglauben des deutschen Soldaten im Kriege [toc]
    - text: Aus den ersten Tagen des Krieges [toc]
    - text: Die deutschen Kinder [toc]
    - text: Ein Sohn sucht seine Eltern [toc]
    - … 418 more
1. `Westfälische Zeitschrift`: mixed, collection, 7 parts, 0 missing, order natural, validated
    - front: Westfälische Zeitschrift
    - text: Belagerung und Zerstörung des Schlosses Arnsberg 1762 [container]
    - text: Das Paderbornsche Bataillon im siebenjährigen Kriege [container]
    - text: Der Kaffee-Lärm in Paderborn 1781 [container]
    - text: Die Stuckenbrocker Senne [container]
    - text: Fehdebrief an die Stadt Bochum [container]
    - text: Autoren (`Westfälische Zeitschrift/Autoren`) [hierarchy]
    - text: Inhaltsverzeichnis (`Westfälische Zeitschrift/Inhalt`) [hierarchy]
1. `Die Constitutionen der europäischen Staaten seit den letzten 25 Jahren (Band 2)`: mixed, collection, 44 parts, 0 missing, order chain, proofread
    - front: Die Constitutionen der europäischen Staaten seit den letzten 25 Jahren (Band 2)
    - volume: 4. Polen (`Die Constitutionen der europäischen Staaten seit den letzten 25 Jahren (Band 2)/Polen`) [container]
      - text: Freiheitsbrief der königl. Städte in Polen vom 14. Apr. 1791 [container]
      - text: Constitution vom 3. Mai 1791 [container]
      - text: Constitution des Herzogthums Warschau vom 22. July 1807 (`Constitution des Herzogthums Warschau vom 22. Jul. 1807`) [container]
      - text: Constitution des Königreiches Polen vom 27. Nov. 1815 [container]
    - volume: 5. Cracau (`Die Constitutionen der europäischen Staaten seit den letzten 25 Jahren (Band 2)/Cracau`) [container]
      - text: Constitution der freien Stadt Cracau vom 3. Mai 1815 [container]
    - volume: 6. Teutschland (`Die Constitutionen der europäischen Staaten seit den letzten 25 Jahren (Band 2)/Teutschland`) [container]
      - text: Die teutsche Bundesacte vom 8. Juny 1815 [container]
      - text: Ständeverfassung in Tyrol vom 24. März 1816 [container]
      - text: Königl. Decret vom 22. Mai 1815 [container]
    - … 33 more
1. `Thalia`: mixed, collection, 68 parts, 0 missing, order chain, validated
    - front: Thalia
    - volume: Erster Band (`Thalia/Erster Band`) [hierarchy]
      - text: [Zuschrift] (`Thalia/Erster Band/Zuschrift`) [container]
      - text: Was kann eine gute stehende Schaubühne eigentlich wirken? [container]
      - text: Merkwürdiges Beispiel einer weiblichen Rache [container]
      - text: Dom Karlos (`Dom Karlos – Teil 1`) [container]
      - chapter: Brief eines reisenden Dänen [container]
      - text: Repertorium des Mannheimer Nationaltheaters [container]
      - text: Wallensteinischer Theaterkrieg. (`Wallensteinischer Theaterkrieg`) [container]
      - text: Dramaturgische Preißfragen [container]
      - text: An die Freude. (`An die Freude (Schiller)`) [container]
      - text: Ueber moderne Größe [container]
    - … 57 more
1. `Die Gartenlaube (1890)`: mixed, collection, 352 parts, 0 missing, order toc-partial, validated
    - front: Die Gartenlaube (1890)
    - text: Am Rheinfall bei Schaffhausen [toc]
    - text: Ueber Gehörinstrumente für Schwerhörige [toc]
    - text: Theodor Fontane (`Theodor Fontane (Gottschall)`) [toc]
    - text: Amor der Honigdieb [toc]
    - text: Neujahrsgruß für 1890 [toc]
    - text: Die Entdeckung der Nilquellen und Stanleys jüngster Afrikazug [toc]
    - text: Quitt [toc]
    - text: Der Bazar [toc]
    - text: Fürsorge für Genesende (`Fürsorge für Genesende (Die Gartenlaube 1890/1)`) [toc]
    - text: Gewitter auf dem Sonnblick [toc]
    - text: Des Todes Ernte unter unseren Dichtern [toc]
    - … 341 more
1. `Die Gartenlaube (1894)`: mixed, collection, 417 parts, 0 missing, order toc-partial, proofread
    - front: Die Gartenlaube (1894)
    - text: Ein Invalidenheim [toc]
    - text: Die Perle (`Die Perle (Bernhard)`) [toc]
    - text: Deutsche Zecher am Missouri [toc]
    - text: Im Fieber [toc]
    - text: Die alte Gartenlaube [toc]
    - text: Der Brautzug [toc]
    - text: Sperbereule auf der Eichhornjagd [toc]
    - text: Märchen (`Märchen (Die Gartenlaube 1894)`) [toc]
    - text: Eine Schatzkammer deutschen Humors [toc]
    - text: Die Sprengstoffe der Neuzeit [toc]
    - text: Etwas von der Kolportage [toc]
    - … 406 more
1. `Die Gartenlaube (1874)`: mixed, collection, 404 parts, 0 missing, order toc-partial, validated
    - front: Die Gartenlaube (1874)
    - text: Es werde Licht! [toc]
    - text: Die zweite Frau [toc]
    - text: Eine Weihe [toc]
    - text: Ein New-Yorker Millionär [toc]
    - text: Aus meinem Gefängniß- und Fluchtleben [toc]
    - text: Brautleute im Sachsenlande [toc]
    - text: Noch einmal die „literarische Freibeuterei“ [toc]
    - text: Lebensretter [toc]
    - text: Für unsere New-Yorker Leser [toc]
    - text: K. S. in R. [toc]
    - text: Ehren-Dotation für Roderich Benedix (`Ehren-Dotation für Roderich Benedix (Die Gartenlaube 1874/1)`) [toc]
    - … 393 more
1. `Die Gartenlaube (1857)`: mixed, collection, 336 parts, 0 missing, order toc-partial, validated
    - front: Die Gartenlaube (1857)
    - text: Die Schmarotzer des Menschen. Der Bandwurm (`Der Bandwurm (Bock)`) [toc]
    - text: Erinnerungen von der deutschen Flotte [toc]
    - text: Die Gemsjagd [toc]
    - text: Deutsche Gräber [toc]
    - text: Pariser Industrie [toc]
    - text: Goethe’s wilde Wochen [toc]
    - text: Schweizer Angelegenheit [toc]
    - text: Das rutschende Dorf Tschappina [toc]
    - text: Naturbetrachtungen im Zimmer [toc]
    - text: Der englische Perserkrieg um Herat [toc]
    - text: Skizzen aus dem Hamburger Volksleben [toc]
    - … 325 more
1. `Die Gartenlaube (1891)`: mixed, collection, 389 parts, 0 missing, order toc-partial, validated
    - front: Die Gartenlaube (1891)
    - text: Eine unbedeutende Frau [toc]
    - text: Unschuldig verurtheilt! (`Unschuldig verurtheilt! (Die Gartenlaube 1891)`) [toc]
    - text: Deutsche Träume [toc]
    - text: Neunzig Jahre Frauenmode [toc]
    - text: Bei Robert Koch [toc]
    - text: Truggeister [toc]
    - text: Badisches Lehrerinnenheim in Lichtenthal bei Baden-Baden [toc]
    - text: Ein weltbewegender Fund [toc]
    - text: Etwas vom guten Vater Haydn [toc]
    - text: Der Streit zwischen Krimhild und Brunhild vor dem Münster zu Worms [toc]
    - text: Eine neue Waschmaschine [toc]
    - … 378 more
1. `Die Gartenlaube (1883)`: mixed, collection, 371 parts, 0 missing, order toc-partial, validated
    - front: Die Gartenlaube (1883)
    - text: Gebannt und erlöst [toc]
    - text: Franz Defregger [toc]
    - text: Die Kautschuksammler am Amazonenstrom [toc]
    - text: Schilf-Lottchen [toc]
    - text: Sylvesternacht (`Sylvesternacht (Hans Hopfen)`) [toc]
    - text: Unsere Jagdhunde [toc]
    - text: Die „Ritter von der Straße“ (`Die Ritter von der Straße`) [toc]
    - text: Zur Heilung des Schreibekrampfes [toc]
    - text: Das türkische Derwischthum in seiner heutigen Gestalt und Bedeutung [toc]
    - text: Die Rabenauer Möbelindustrie [toc]
    - text: Friedrich König, der Erfinder der „Schnellpresse“ [toc]
    - … 360 more
1. `Biographisches Jahrbuch und Deutscher Nekrolog`: mixed, collection, 3 parts, 0 missing, order toc, validated
    - front: Biographisches Jahrbuch und Deutscher Nekrolog
    - text: Autorenverzeichnis (`Biographisches Jahrbuch und Deutscher Nekrolog/Autoren`) [hierarchy]
    - text: Inhaltsverzeichnis nach Bänden (`Biographisches Jahrbuch und Deutscher Nekrolog/Inhalt`) [hierarchy]
    - text: [Ludwig Speidel, Schriftsteller] (`Ludwig Speidel, Schriftsteller`) [container]
1. `Die Gartenlaube (1884)`: mixed, collection, 425 parts, 1 missing, order toc-partial, proofread
    - front: Die Gartenlaube (1884)
    - text: Neujahres-Gruß [toc]
    - text: Ein armes Mädchen [toc]
    - text: Die Scrophulose, eine sociale Krankheit [toc]
    - text: Ditta’s Zopf Eine Dorfgeschichte aus den Abruzzen (`Ditta’s Zopf`) [toc]
    - text: Die irrende Justiz und ihre Sühne (`Die irrende Justiz und ihre Sühne (Die Gartenlaube 1884/1)`) [toc]
    - text: Armin’s Triumphzug [toc]
    - text: Die heiligen drei Könige mit ihrem Stern [toc]
    - text: Die Sitze in den Eisenbahnwagen [toc]
    - text: Einfache Ermittelung des Kohlensäuregehalts der Zimmerluft [toc]
    - text: Wiederum eine neue Kaffeemaschine [toc]
    - text: Luftdichter Fensterverschluß [toc]
    - … 414 more
1. `Annalen der Physik`: mixed, collection, 126 parts, 0 missing, order natural, proofread
    - front: Annalen der Physik
    - text: Acht und zwanzigste Reihe von Experimental-Untersuchungen über Elektricität [container]
    - text: Achte Reihe von Experimental-Untersuchungen über Elektricität [container]
    - volume: Annalen der Physik und Chemie, Band LX [container]
      - text: Ueber elektrische Abbildungen [container]
      - text: Untersuchung über das vom Prof. Moser zu Königsberg entdeckte dunkle Licht, und über die Erzeugung von Wärmebildern [container]
      - text: Erwiderung an die HH. Fizeau und Daguerre [container]
      - text: Ein Experiment zum Beweise, daß im Quecksilberdampf latentes Licht vorhanden sey [container]
      - text: Entwicklung der Lehre von dem Glanze [container]
      - text: Das specifische Gewicht der Schwefelsäure bei verschiedenen Graden der Verdünnung [container]
      - text: Ueber den Nebenstrom im getheilten Schließungsdraht der Batterie (`Ueber den Nebenstrom im getheilten Schließungsdrahte der Batterie`) [container]
      - text: Ein Paar Bemerkungen über die neue Theorie in Herrn Professor Chr. Doppler’s Schrift: „Ueber das farbige Licht der Doppelsterne und einiger anderer Gestirne des Himmels“ (`Ein Paar Bemerkungen über die neue Theorie in Herrn Professor Chr. Doppler’s Schrift`) [container]
    - … 115 more
1. `W. Hogarth’s Zeichnungen, nach den Originalen in Stahl gestochen`: mixed, collection, 99 parts, 0 missing, order toc, validated
    - toc: W. Hogarth’s Zeichnungen, nach den Originalen in Stahl gestochen
    - volume: W. Hogarth’s Zeichnungen, nach den Originalen in Stahl gestochen. Erste Abtheilung (`W. Hogarth’s Zeichnungen, nach den Originalen in Stahl gestochen/Erste Abtheilung`) [container]
      - text: Lebensgeschichte Hogarths von Dr. Franz Kottenkamp (`Lebensgeschichte Hogarths`) [container]
      - chapter: Vorrede von G. C. Lichtenberg [container]
      - text: Die Heirath nach der Mode. Sechs Blätter (`Die Heirath nach der Mode. Erstes Blatt`) [container]
      - text: Die Heirath nach der Mode. Zweites Blatt [container]
      - text: Die Heirath nach der Mode. Drittes Blatt [container]
      - text: Die Heirath nach der Mode. Viertes Blatt [container]
      - text: Die Heirath nach der Mode. Fünftes Blatt [container]
      - text: Die Heirath nach der Mode. Sechstes Blatt [container]
      - text: Herumstreichende Comödiantinnen, die sich in einer Scheune ankleiden [container]
      - text: Die Punschgesellschaft [container]
    - … 88 more
1. `Die Gartenlaube (1875)`: mixed, collection, 395 parts, 1 missing, order toc-partial, validated
    - front: Die Gartenlaube (1875)
    - text: Zum neuen Jahr (`Zum neuen Jahr (Ernst Ziel)`) [toc]
    - text: Das Capital [toc]
    - text: Tristan und Isolde im alten und neuen Liede [toc]
    - text: Zur Abstammungslehre [toc]
    - text: Die Hauptacteurs im Drama „Arnim“ [toc]
    - text: Ein Herz auf dem Thron [toc]
    - text: Am Grabe eines Märtyrers [toc]
    - text: Das Pariser Opernhaus [toc]
    - text: Ein Grab im Unterland (`Ein Grab im Unterland (Die Gartenlaube 1875/1)`) [toc]
    - text: Ein deutsches Polizeiblatt und dessen Ausbeute [toc]
    - text: Aus dem nächtlichen Thierleben in der Oase [toc]
    - … 384 more
1. `Die Gartenlaube (1886)`: mixed, collection, 475 parts, 5 missing, order toc-partial, proofread
    - front: Die Gartenlaube (1886)
    - text: Zum neuen Jahr (`Zum neuen Jahr (Hermann Lingg)`) [toc]
    - text: Was will das werden? [toc]
    - text: Die Andere [toc]
    - text: Dem Kaiser und König Wilhelm I. [toc]
    - text: Ueber unbewußtes Zählen [toc]
    - text: Berliner Winter [toc]
    - text: Murillo’s Betteljungen [toc]
    - text: Plauderei [toc]
    - text: Eine Papierstatistik der „Gartenlaube“ [toc]
    - text: Auf dem Anschuß (`Auf dem Anschuß (Die Gartenlaube 1886/2)`) [toc]
    - text: Die Nadeln [toc]
    - … 464 more
1. `Märchen für die Jugend`: mixed, collection, 66 parts, 0 missing, order chain, validated
    - front: Märchen für die Jugend
    - text: Dank ist der Welt Lohn [container]
    - text: Undank ist der Welt Lohn [container]
    - text: Der Fuchs und die Gans [container]
    - text: Das goldene Salzfaß, der goldene Haspel und der Tannenzweig [container]
    - text: Die Goldtochter und die Hörnertochter [container]
    - text: Die Zwergmännchen [container]
    - text: Bienchens Haus [container]
    - text: Von der Stadt Sedelfia und dem Vogel Fabian [container]
    - text: Von dem Schaaf, das eine Königstochter trug [container]
    - text: Das Rauhthier [container]
    - text: Wache, Wache, Ronde raus! [container]
    - … 55 more
1. `Mitteilungen des Vereins für Chemnitzer Geschichte Erster und Zweiter Band`: mixed, collection, 15 parts, 0 missing, order toc, validated
    - front: Mitteilungen des Vereins für Chemnitzer Geschichte Erster und Zweiter Band
    - text: Allgemeiner Bericht (`Mitteilungen des Vereins für Chemnitzer Geschichte Erster und Zweiter Band/Allgemeiner Bericht (1)`) [hierarchy]
    - text: Bericht über das Archiv (`Mitteilungen des Vereins für Chemnitzer Geschichte Erster und Zweiter Band/Bericht über das Archiv`) [hierarchy]
    - text: Bericht über die Bibliothek (`Mitteilungen des Vereins für Chemnitzer Geschichte Erster und Zweiter Band/Bericht über die Bibliothek`) [hierarchy]
    - text: Mitgliederverzeichniss (`Mitteilungen des Vereins für Chemnitzer Geschichte Erster und Zweiter Band/Mitgliederverzeichniss (1)`) [hierarchy]
    - text: Kassenbericht 1873–74 (`Mitteilungen des Vereins für Chemnitzer Geschichte Erster und Zweiter Band/Kassenbericht 1873–74`) [hierarchy]
    - chapter: Anhang (`Mitteilungen des Vereins für Chemnitzer Geschichte Erster und Zweiter Band/Anhang`) [hierarchy]
    - text: Allgemeiner Bericht (`Mitteilungen des Vereins für Chemnitzer Geschichte Erster und Zweiter Band/Allgemeiner Bericht (2)`) [hierarchy]
    - text: Katalog der Bibliothek (`Mitteilungen des Vereins für Chemnitzer Geschichte Erster und Zweiter Band/Katalog der Bibliothek`) [hierarchy]
    - text: Bericht über das Museum (`Mitteilungen des Vereins für Chemnitzer Geschichte Erster und Zweiter Band/Bericht über das Museum`) [hierarchy]
    - text: Mitglieder-Verzeichniss (`Mitteilungen des Vereins für Chemnitzer Geschichte Erster und Zweiter Band/Mitgliederverzeichniss (2)`) [hierarchy]
    - text: Nekrologe: (`Mitteilungen des Vereins für Chemnitzer Geschichte Erster und Zweiter Band/Nekrologe`) [hierarchy]
    - … 4 more
1. `Die Schaubühne – Die Weltbühne`: mixed, collection, 23 parts, 0 missing, order natural, validated
    - front: Die Schaubühne – Die Weltbühne
    - text: Begnadigung [container]
    - text: Das Lied von der Gleichgültigkeit (`Das Lied von der Gleichgültigkeit (Tucholsky)`) [container]
    - text: Das Lächeln der Mona Lisa [container]
    - text: Das Reichsarchiv [container]
    - text: Das Schach [container]
    - text: Der bewachte Kriegsschauplatz [container]
    - text: Der Dicke in Rußland [container]
    - text: Der Prozeß [container]
    - text: Deutsch (`Deutsch (Tucholsky)`) [container]
    - text: Die Katze spielt mit der Maus [container]
    - text: Die neuen Troubadoure [container]
    - … 12 more
1. `Der Sagenschatz des Königreichs Sachsen`: mixed, collection, 1,001 parts, 0 missing, order chain, validated
    - front: Der Sagenschatz des Königreichs Sachsen
    - text: Wie die Raute in das sächsische Wappenschild gekommen ist [container]
    - text: Sage von dem unvergänglichen Bestehen des Hauses Sachsen [container]
    - text: Das Gesicht der Herzogin Agnes [container]
    - text: Traum der Churfürstin Margarethe [container]
    - text: Der Traum des Churfürsten Friedrich III. oder des Weisen [container]
    - text: Friedrich der Weise und sein Bruder werden aus großer Lebensgefahr gerettet [container]
    - text: Friedrich’s des Weisen Tod verkündet ein Hirsch [container]
    - text: Der Tod des Erzbischoffs von Magdeburg Ernst wird verkündigt [container]
    - text: Luther prophezeit einem sächsischen Prinzen den Tod [container]
    - text: Unglückliches Vorzeichen der Schlacht bei Mühlberg [container]
    - text: Dem Churfürsten Johann Friedrich wird sein Tod verkündigt [container]
    - … 990 more
1. `Fliegende Blätter`: mixed, collection, 241 parts, 1 missing, order toc-partial, proofread
    - front: Fliegende Blätter
    - text: Liste der Verfasser (`Fliegende Blätter/Verfasser`) [hierarchy]
    - text: Liste der Illustratoren (`Fliegende Blätter/Illustratoren`) [hierarchy]
    - volume: 1. 1845 (`Fliegende Blätter (Band 1)`) [container]
      - text: Das Heidelberger Fass [container]
      - text: Fliegende Blätter Heft 1 (Band 1) [container]
      - text: Sprüchwörter. (`Sprüchwörter (Fliegende Blätter Nr. 1)`) [container]
      - text: Weltgeschichte. (`Weltgeschichte (Fliegende Blätter Nr. 1)`) [container]
      - text: Deutsches Schauspiel zu Venedig (`Deutsches Schauspiel zu Venedig (Fliegende Blätter Nr. 2)`) [container]
      - text: Fliegende Blätter Heft 2 (Band 1) [container]
      - text: Die Viborger [container]
      - text: II 13 (`Sprüchwörter (Fliegende Blätter Nr. 2)`) [container]
    - … 230 more
1. `Die Gartenlaube (1864)`: mixed, collection, 398 parts, 0 missing, order toc-partial, validated
    - front: Die Gartenlaube (1864)
    - text: Neujahrsgebet (`Neujahrsgebet (Die Gartenlaube 1864)`) [toc]
    - text: Der Kranz am Marterl [toc]
    - text: Der polnische Schreiber [toc]
    - text: Im Kerker der Hoffnungslosen [toc]
    - text: Unsere Bären [toc]
    - text: Ein Name für das erste deutsche Kriegsschiff [toc]
    - text: Der Tod und das Begräbniß eines Freiheitskämpfers [toc]
    - text: Berichtigung (`Berichtigung (Die Gartenlaube 1864/1)`) [toc]
    - text: Für die braven Schleswig-Holsteiner (`Für die braven Schleswig-Holsteiner (Die Gartenlaube 1864/1)`) [toc]
    - text: Das ewige Licht [toc]
    - text: Die Kinderzeche in Dinkelsbühl [toc]
    - … 387 more
1. `Die Gartenlaube (1879)`: mixed, collection, 390 parts, 0 missing, order toc-partial, validated
    - front: Die Gartenlaube (1879)
    - text: Das Evangelium der Toleranz [toc]
    - text: Vernünftige Gedanken einer Hausmutter. 1. Der Mutter Stufenjahre (`Vernünftige Gedanken einer Hausmutter (1)`) [toc]
    - text: Madonna Violanta [toc]
    - text: Ein Wort am Grabe Karl Gutzkow’s [toc]
    - text: Die Kunst im Hause [toc]
    - text: Neujahr in China [toc]
    - text: Ein verfolgter Räuber [toc]
    - text: Das Satyrspiel von 1878 [toc]
    - text: Des Kaisers Lector [toc]
    - text: Ein Augenblick [toc]
    - text: Deutsches Frauenleben im Mittelalter. 7. Bei Spiel und Tanz. (`Deutsches Frauenleben im Mittelalter (Die Gartenlaube 1879/2)`) [toc]
    - … 379 more
1. `Die Gartenlaube (1893)`: mixed, collection, 411 parts, 3 missing, order toc-partial, proofread
    - front: Die Gartenlaube (1893)
    - text: Am „Eisernen Thor“ [toc]
    - text: Auf Geben und Nehmen [toc]
    - text: Vom Struwwelpeter [toc]
    - text: Heimstätten für Genesende [toc]
    - text: Der schüchterne Freier [toc]
    - text: Warum schweben die Wolken [toc]
    - text: Im Dschungl [toc]
    - text: Frühlingsblüthen [toc]
    - text: Eine Zauberwurzel [toc]
    - text: Friedrich von Esmarch (`Friedrich von Esmarch (Die Gartenlaube 1893)`) [toc]
    - text: Die letzten Büffel von Nordamerika [toc]
    - … 400 more
1. `Die Gartenlaube (1877)`: mixed, collection, 424 parts, 0 missing, order toc-partial, validated
    - front: Die Gartenlaube (1877)
    - text: Jubelgruß der „Gartenlaube“ [toc]
    - text: Aus gährender Zeit [toc]
    - text: Die Zeit der Rosen [toc]
    - text: Aus dem Herzensleben unseres Lieblingsdichters [toc]
    - text: Kanossa [toc]
    - text: Der Spiritismus vor dem Gerichtshofe der Wissenschaft [toc]
    - text: Parlamentarische Photographien aus Versailles [toc]
    - text: Die Taufe des heiligen Aristophanes [toc]
    - text: Nochmals die Eisenbahnen [toc]
    - text: Gratulation bei der Gutsherrschaft [toc]
    - text: Aus dem Nachlasse Herbert König’s [toc]
    - … 413 more

### D: virtual roots (52)

1. `Zeitschriften (Veterinärmedizin)`: virtual, collection, 1 parts, 0 missing, order single, unknown
    - text: The veterinarian (`Zeitschriften (Veterinärmedizin)/fremdsprachige/The veterinarian : a monthly journal of veterinary science`)
1. `Handeln die Thiere nur aus Instinct oder mit Ueberlegung?`: virtual, book, 1 parts, 0 missing, order single, validated
    - chapter: 1 (`Handeln die Thiere nur aus Instinct oder mit Ueberlegung?/1`)
1. `Geschichtsauffassung und Geschichtsschreibung`: virtual, book, 7 parts, 0 missing, order natural, validated
    - chapter: I. Das Alte und die neuen Probleme (`Geschichtsauffassung und Geschichtsschreibung/I. Das Alte und die neuen Probleme`)
    - chapter: II. Vorläufer und Vorbilder (`Geschichtsauffassung und Geschichtsschreibung/II. Vorläufer und Vorbilder`) [hierarchy]
    - chapter: III. Scholastischer Humanismus (`Geschichtsauffassung und Geschichtsschreibung/III. Scholastischer Humanismus`) [hierarchy]
    - chapter: IV. Humanistische Weltchroniken (`Geschichtsauffassung und Geschichtsschreibung/IV. Humanistische Weltchroniken`) [hierarchy]
    - chapter: V. Entdecker und Kritiker (`Geschichtsauffassung und Geschichtsschreibung/V. Entdecker und Kritiker`) [hierarchy]
    - chapter: VI. Germania illustrata (`Geschichtsauffassung und Geschichtsschreibung/VI. Germania illustrata`) [hierarchy]
    - chapter: VII. Die humanistische Hofgeschichtschreibung unter Kaiser Maximilian (`Geschichtsauffassung und Geschichtsschreibung/VII. Die humanistische Hofgeschichtschreibung unter Kaiser Maximilian`) [hierarchy]
1. `Galerie historischer Enthüllungen`: virtual, collection, 5 parts, 0 missing, order natural, validated
    - text: Galerie historischer Enthüllungen 1.&nbsp;Wilhelm Tell und der Rütlibund (`Galerie historischer Enthüllungen/1. Wilhelm Tell und der Rütlibund`)
    - text: Galerie historischer Enthüllungen/2. Die Sage von dem Heldentode der vierhundert Pforzheimer [hierarchy]
    - text: Galerie historischer Enthüllungen/3. Agnes Bernauer [hierarchy]
    - text: Galerie historischer Enthüllungen 4.&nbsp;Arnold von Winkelried (`Galerie historischer Enthüllungen/4. Arnold von Winkelried`) [hierarchy]
    - text: Galerie historischer Enthüllungen 5.&nbsp;Kaufmännische Noblesse (`Galerie historischer Enthüllungen/5. Kaufmännische Noblesse`) [hierarchy]
1. `Die Verfälschung der Nahrungs- und Genußmittel`: virtual, collection, 2 parts, 0 missing, order natural, validated
    - text: Die Verfälschung der Nahrungs und Genußmittel. 2. Das Bier. (`Die Verfälschung der Nahrungs- und Genußmittel/2. Das Bier`)
    - text: Die Verfälschung der Nahrungs und Genußmittel. 3. Die chemischen Biersurrogate. (`Die Verfälschung der Nahrungs- und Genußmittel/3. Die chemischen Biersurrogate`) [hierarchy]
1. `Kunstketzereien`: virtual, book, 2 parts, 0 missing, order natural, validated
    - chapter: 1 (`Kunstketzereien/1`)
    - chapter: 2. (`Kunstketzereien/2.`) [hierarchy]
1. `Preußisches Obertribunal - Rose`: virtual, collection, 1 parts, 0 missing, order single, unknown
    - text: Rosahl (`Preußisches Obertribunal - Rose/Rosahl`)
1. `Wikisource`: virtual, collection, 1 parts, 0 missing, order single, unknown
    - text: Hilf mit! (`Wikisource/Hilf mit!`)
1. `Zeitschriften (Kunst)`: virtual, collection, 5 parts, 0 missing, order natural, unknown
    - text: La belle assemblée (`Zeitschriften (Kunst)/fremdsprachige/La belle assemblée : being Bell's court and fashionable magazine, addressed particulary to the ladies`)
    - text: The ladies' cabinet of fashion, music & romance (`Zeitschriften (Kunst)/fremdsprachige/The ladies' cabinet of fashion, music & romance`) [hierarchy]
    - text: The ladies' companion and monthly magazine (`Zeitschriften (Kunst)/fremdsprachige/The ladies' companion and monthly magazine`) [hierarchy]
    - text: The lady's magazine or entertaining companion for the fair sex : (`Zeitschriften (Kunst)/fremdsprachige/The lady's magazine or entertaining companion for the fair sex`) [hierarchy]
    - text: The new monthly assemblée (`Zeitschriften (Kunst)/fremdsprachige/The new monthly assemblée`) [hierarchy]
1. `Reiseerinnerungen`: virtual, collection, 1 parts, 0 missing, order single, validated
    - text: Reiseerinnerungen/I. Bamberg
1. `Neuer Görlitzer Anzeiger`: virtual, collection, 2 parts, 0 missing, order natural, validated
    - text: Heimatbilder #38: Berzdorf auf dem Eigen I. (`Neuer Görlitzer Anzeiger/Berzdorf auf dem Eigen I`)
    - text: Heimatbilder #39: Berzdorf auf dem Eigen II. (`Neuer Görlitzer Anzeiger/Berzdorf auf dem Eigen II`) [hierarchy]
1. `Topographie der historischen und kunstgeschichtlichen Denkmale in den Bezirken Tepl und Marienbad`: virtual, collection, 2 parts, 0 missing, order scan, proofread
    - text: Maiersgrün (`Topographie der historischen und kunstgeschichtlichen Denkmale in den Bezirken Tepl und Marienbad/Maiersgrün`)
    - text: Tepl, Pfarrkirche zu S. Ägidius (`Topographie der historischen und kunstgeschichtlichen Denkmale in den Bezirken Tepl und Marienbad/Tepl, Pfarrkirche zu S. Ägidius`) [hierarchy]
1. `Die Heimath in der neuen Welt`: virtual, book, 50 parts, 0 missing, order chain, validated
    - volume: Die Heimath in der neuen Welt, Erster Band (`Die Heimath in der neuen Welt/Erster Band`)
      - text: An den Leser. (`Die Heimath in der neuen Welt/Erster Band/An den Leser`) [hierarchy]
      - text: An meine amerikanischen Freunde! (`Die Heimath in der neuen Welt/Erster Band/An meine amerikanischen Freunde!`) [hierarchy]
      - chapter: Erster Brief. (`Die Heimath in der neuen Welt/Erster Band/Erster Brief`) [container]
      - chapter: Zweiter Brief. (`Die Heimath in der neuen Welt/Erster Band/Zweiter Brief`) [container]
      - chapter: Dritter Brief. (`Die Heimath in der neuen Welt/Erster Band/Dritter Brief`) [container]
      - chapter: Vierter Brief. (`Die Heimath in der neuen Welt/Erster Band/Vierter Brief`) [container]
      - chapter: Fünfter Brief. (`Die Heimath in der neuen Welt/Erster Band/Fünfter Brief`) [container]
      - chapter: Sechster Brief. (`Die Heimath in der neuen Welt/Erster Band/Sechster Brief`) [container]
      - chapter: Siebenter Brief. (`Die Heimath in der neuen Welt/Erster Band/Siebenter Brief`) [container]
      - chapter: Achter Brief. (`Die Heimath in der neuen Welt/Erster Band/Achter Brief`) [container]
      - chapter: Neunter Brief. (`Die Heimath in der neuen Welt/Erster Band/Neunter Brief`) [container]
    - … 38 more
1. `Erinnerungen aus meinem Leben (Friedrich Hecker)`: virtual, collection, 1 parts, 0 missing, order single, validated
    - text: Der Hexenmeister der Prairie (`Erinnerungen aus meinem Leben (Friedrich Hecker)/2`)
1. `Aus der Beamtenwelt`: virtual, collection, 4 parts, 0 missing, order natural, validated
    - text: Aus dem Beamtenleben/Nr. 1. Der Herr Director (`Aus der Beamtenwelt/Nr. 1. Der Herr Director`)
    - text: Aus der Beamtenwelt/Nr. 2. Ein Ruheposten [hierarchy]
    - text: Aus der Beamtenwelt/Nr. 3. Das Stipendium [hierarchy]
    - text: Aus der Beamtenwelt Nr.&nbsp;4. Der Postschein (`Aus der Beamtenwelt/Nr. 4. Der Postschein`) [hierarchy]
1. `Seegeschichten`: virtual, collection, 1 parts, 0 missing, order single, validated
    - text: Seegeschichten 2. Die Springstange (`Seegeschichten/2. Die Springstange`)
1. `Aus den Arbeitssälen des Kunsthandwerks`: virtual, collection, 2 parts, 0 missing, order natural, validated
    - text: Aus den Arbeitssälen des Kunsthandwerks/Die Goldschmiedekunst
    - text: Aus den Arbeitssälen des Kunsthandwerks/Stil und Mode [hierarchy]
1. `Aus dem Fuchsleben`: virtual, collection, 2 parts, 0 missing, order natural, validated
    - text: Aus dem Fuchsleben/I
    - text: Aus dem Fuchsleben/II [hierarchy]
1. `Fundamento de Esperanto`: virtual, collection, 1 parts, 0 missing, order single, validated
    - text: Grammatik (`Fundamento de Esperanto/Grammatik`)
1. `Zwanglose Blätter(1883/9)`: virtual, collection, 1 parts, 0 missing, order single, validated
    - text: Briefkasten (`Zwanglose Blätter(1883/9)/Briefkasten`)
1. `Bilderschau in meinem Zimmer`: virtual, book, 3 parts, 0 missing, order natural, validated
    - chapter: 1 (`Bilderschau in meinem Zimmer/1`)
    - text: Bilderschau in meinem Zimmer/2 [hierarchy]
    - chapter: 3 (`Bilderschau in meinem Zimmer/3`) [hierarchy]
1. `Der Handlungsreisende wie er sein soll`: virtual, book, 1 parts, 0 missing, order single, validated
    - chapter: 6 (`Der Handlungsreisende wie er sein soll/6`)
1. `Kleine Bilder aus der Gegenwart`: virtual, collection, 15 parts, 0 missing, order natural, proofread
    - text: Kleine Bilder aus der Gegenwart. Nr. 8. Aus der Welt der Reclame. (`Kleine Bilder aus der Gegenwart/Aus der Welt der Reclame`)
    - text: Bei J. Wickersheimer (`Kleine Bilder aus der Gegenwart/Bei J. Wickersheimer`) [hierarchy]
    - text: Das fescheste Zeugl (`Kleine Bilder aus der Gegenwart/Das fescheste Zeugl`) [hierarchy]
    - text: Der Brand in Aachen am 29. Juni dieses Jahres (`Kleine Bilder aus der Gegenwart/Der Brand in Aachen am 29. Juni dieses Jahres`) [hierarchy]
    - text: Der Fang der Eintagsfliege (`Kleine Bilder aus der Gegenwart/Der Fang der Eintagsfliege`) [hierarchy]
    - text: Der Hafenbrand in Hamburg am 31. Mai (`Kleine Bilder aus der Gegenwart/Der Hafenbrand in Hamburg am 31. Mai`) [hierarchy]
    - text: Der „Munk-Affe“ (`Kleine Bilder aus der Gegenwart/Der Munk-Affe`) [hierarchy]
    - text: Der Schweizer Alpenclub auf der Ausstellung in Zürich (`Kleine Bilder aus der Gegenwart/Der Schweizer Alpenclub auf der Ausstellung in Zürich`) [hierarchy]
    - text: Die chinesische Panzercorvette „Ting Yuen“ (`Kleine Bilder aus der Gegenwart/Die chinesische Panzercorvette Ting Yuen`) [hierarchy]
    - text: Kleine Bilder aus der Gegenwart. Die letzte Personenpost in Breslau (`Kleine Bilder aus der Gegenwart/Die letzte Personenpost in Breslau`) [hierarchy]
    - text: Kleine Bilder aus der Gegenwart. 3. Im Taucherpavillon der Hygiene-Ausstellung in Berlin. (`Kleine Bilder aus der Gegenwart/Im Taucherpavillon der Hygiene-Ausstellung in Berlin`) [hierarchy]
    - text: Ischia (`Kleine Bilder aus der Gegenwart/Ischia`) [hierarchy]
    - … 3 more
1. `Gallerie berühmter Firmengründer`: virtual, collection, 1 parts, 0 missing, order single, validated
    - text: Gallerie berühmter Firmengründer 1. Ein Leipziger Buchhändler (`Gallerie berühmter Firmengründer/1. Ein Leipziger Buchhändler`)
1. `Mein erster Hirsch`: virtual, collection, 1 parts, 0 missing, order single, validated
    - text: Mein erster Hirsch/1881
1. `Begegnungen mit Zeitgenossen`: virtual, collection, 1 parts, 0 missing, order single, validated
    - text: Begegnungen mit Zeitgenossen/Nr. 1. A. v. Humboldt
1. `Mährchen und Jugenderinnerungen`: virtual, collection, 51 parts, 0 missing, order chain, validated
    - volume: Erster Theil (`Mährchen und Jugenderinnerungen/Erster Theil`)
      - text: Geschichte von den sieben bunten Mäusen [container]
      - text: Prinzessin Svanvithe [container]
      - text: Der Wolf und die Nachtigall, oder wie zwei arme Königskinder verwandelt und zuletzt nach vieler Noth doch wieder zu Menschen geschaffen wurden [container]
      - text: Der Wolf und die Nachtigall (Schwedisches Volksmährchen) [container]
      - text: Klas Avenstaken [container]
      - text: Paiwai und Paiwuzzo [container]
      - text: Die Neun Berge bei Rambin [container]
      - text: Schneeflöckchen [container]
      - text: Erdwurm [container]
      - text: Rattenkönig Birlibi [container]
      - text: Der Schlangenkönig [container]
    - … 39 more
1. `Kataloge der Jahresausstellungen der Kurfürstl.`: virtual, collection, 1 parts, 0 missing, order single, unknown
    - text: Kataloge der Jahresausstellungen der Kurfürstl./Königl. Sächsischen Akademie der Künste
1. `Zeitschriften (Illustrierte)`: virtual, collection, 1 parts, 0 missing, order single, unknown
    - text: The St. James' magazine (`Zeitschriften (Illustrierte)/fremdsprachige/The St. James' magazine`)
1. `In Friedrich Rückert’s Haus`: virtual, collection, 2 parts, 0 missing, order natural, validated
    - text: In Friedrich Rückert’s Haus/1
    - text: In Friedrich Rückert’s Haus/2 [hierarchy]

### E: subpage texts reached from a disambiguation or versions page (50)

1. text “Anmerkungen zu den einzelnen Märchen” (`Kinder- und Haus-Märchen Band 3 (1856)/Anmerkungen`), part 6 of `Kinder- und Haus-Märchen Band 3 (1856)` (collection, 11 parts, 10 texts)
1. text “Von einem, der auszog, das Fürchten zu lernen” (`Anmerkungen zu den Kinder- und Hausmärchen der Brüder Grimm I/Von einem der auszog, das Fürchten zu lernen`), part 4 of `Anmerkungen zu den Kinder- und Hausmärchen der Brüder Grimm I` (collection, 66 parts, 66 texts)
1. text “Der Froschkönig oder der eiserne Heinrich” (`Anmerkungen zu den Kinder- und Hausmärchen der Brüder Grimm I/Der Froschkönig oder der eiserne Heinrich`), part 1 of `Anmerkungen zu den Kinder- und Hausmärchen der Brüder Grimm I` (collection, 66 parts, 66 texts)
1. text “Der „Munk-Affe“” (`Kleine Bilder aus der Gegenwart/Der Munk-Affe`), part 6 of `Kleine Bilder aus der Gegenwart` (collection, 15 parts, 15 texts)
1. chapter “1” (`Bilderschau in meinem Zimmer/1`), part 0 of `Bilderschau in meinem Zimmer` (book, 3 parts, 1 texts)
1. text “Bilderschau in meinem Zimmer/2” (`Bilderschau in meinem Zimmer/2`), part 1 of `Bilderschau in meinem Zimmer` (book, 3 parts, 1 texts)
1. chapter “1” (`Kunstketzereien/1`), part 0 of `Kunstketzereien` (book, 2 parts, 0 texts)
1. text “Die Hand mit dem Messer” (`Anmerkungen zu den Kinder- und Hausmärchen der Brüder Grimm I/Die Hand mit dem Messer`), part 10 of `Anmerkungen zu den Kinder- und Hausmärchen der Brüder Grimm I` (collection, 66 parts, 66 texts)
1. text “Der Brand in Aachen am 29. Juni dieses Jahres” (`Kleine Bilder aus der Gegenwart/Der Brand in Aachen am 29. Juni dieses Jahres`), part 3 of `Kleine Bilder aus der Gegenwart` (collection, 15 parts, 15 texts)
1. text “Marienkind” (`Anmerkungen zu den Kinder- und Hausmärchen der Brüder Grimm I/Marienkind`), part 3 of `Anmerkungen zu den Kinder- und Hausmärchen der Brüder Grimm I` (collection, 66 parts, 66 texts)
1. volume “Erster Band” (`Thalia/Erster Band`), part 1 of `Thalia` (collection, 68 parts, 64 texts)
1. text “Der Schweizer Alpenclub auf der Ausstellung in Zürich” (`Kleine Bilder aus der Gegenwart/Der Schweizer Alpenclub auf der Ausstellung in Zürich`), part 7 of `Kleine Bilder aus der Gegenwart` (collection, 15 parts, 15 texts)
1. text “Der Hafenbrand in Hamburg am 31. Mai” (`Kleine Bilder aus der Gegenwart/Der Hafenbrand in Hamburg am 31. Mai`), part 5 of `Kleine Bilder aus der Gegenwart` (collection, 15 parts, 15 texts)
1. text “Kleine Bilder aus der Gegenwart : Wettfahrt von Segelschlitten auf dem Müggelsee bei Berlin” (`Kleine Bilder aus der Gegenwart/Wettfahrt von Segelschlitten auf dem Müggelsee bei Berlin`), part 12 of `Kleine Bilder aus der Gegenwart` (collection, 15 parts, 15 texts)
1. volume “Mährchen und Jugenderinnerungen. Zweiter Theil.” (`Mährchen und Jugenderinnerungen/Zweiter Theil`), part 20 of `Mährchen und Jugenderinnerungen` (collection, 51 parts, 49 texts)
1. text “Der getreue Johannes” (`Anmerkungen zu den Kinder- und Hausmärchen der Brüder Grimm I/Der getreue Johannes`), part 6 of `Anmerkungen zu den Kinder- und Hausmärchen der Brüder Grimm I` (collection, 66 parts, 66 texts)
1. text “Bei J. Wickersheimer” (`Kleine Bilder aus der Gegenwart/Bei J. Wickersheimer`), part 1 of `Kleine Bilder aus der Gegenwart` (collection, 15 parts, 15 texts)
1. text “Der gestiefelte Kater” (`Anmerkungen zu den Kinder- und Hausmärchen der Brüder Grimm I/Der gestiefelte Kater`), part 38 of `Anmerkungen zu den Kinder- und Hausmärchen der Brüder Grimm I` (collection, 66 parts, 66 texts)
1. text “Ischia” (`Kleine Bilder aus der Gegenwart/Ischia`), part 11 of `Kleine Bilder aus der Gegenwart` (collection, 15 parts, 15 texts)
1. text “Der Wolf und die sieben jungen Geißlein” (`Anmerkungen zu den Kinder- und Hausmärchen der Brüder Grimm I/Der Wolf und die sieben jungen Geißlein`), part 5 of `Anmerkungen zu den Kinder- und Hausmärchen der Brüder Grimm I` (collection, 66 parts, 66 texts)
1. text “Wie Kinder Schlachtens miteinander gespielt haben” (`Anmerkungen zu den Kinder- und Hausmärchen der Brüder Grimm I/Wie Kinder Schlachtens miteinander gespielt haben`), part 25 of `Anmerkungen zu den Kinder- und Hausmärchen der Brüder Grimm I` (collection, 66 parts, 66 texts)
1. text “Kleine Bilder aus der Gegenwart: Zwei unpolitische „Kongresse“” (`Kleine Bilder aus der Gegenwart/Zwei unpolitische „Kongresse“`), part 13 of `Kleine Bilder aus der Gegenwart` (collection, 15 parts, 15 texts)
1. text “Der Fang der Eintagsfliege” (`Kleine Bilder aus der Gegenwart/Der Fang der Eintagsfliege`), part 4 of `Kleine Bilder aus der Gegenwart` (collection, 15 parts, 15 texts)
1. text “Pan” (`Christliche Symbolik/Pan`), part 385 of `Christliche Symbolik` (collection, 636 parts, 633 texts)
1. text “Der wunderliche Spielmann” (`Anmerkungen zu den Kinder- und Hausmärchen der Brüder Grimm I/Der wunderliche Spielmann`), part 9 of `Anmerkungen zu den Kinder- und Hausmärchen der Brüder Grimm I` (collection, 66 parts, 66 texts)
1. text “Von der Nachtigall und der Blindschleiche” (`Anmerkungen zu den Kinder- und Hausmärchen der Brüder Grimm I/Von der Nachtigall und der Blindschleiche`), part 7 of `Anmerkungen zu den Kinder- und Hausmärchen der Brüder Grimm I` (collection, 66 parts, 66 texts)
1. chapter “3” (`Bilderschau in meinem Zimmer/3`), part 2 of `Bilderschau in meinem Zimmer` (book, 3 parts, 1 texts)
1. text “A” (`Fliegende Blätter/Inhalt Band 1`), part 147 of `Fliegende Blätter` (collection, 241 parts, 231 texts)
1. text “Das fescheste Zeugl” (`Kleine Bilder aus der Gegenwart/Das fescheste Zeugl`), part 2 of `Kleine Bilder aus der Gegenwart` (collection, 15 parts, 15 texts)
1. text “Abel” (`Christliche Symbolik/Abel`), part 5 of `Christliche Symbolik` (collection, 636 parts, 633 texts)

### all searchable subpage texts (chapter titles that the label patterns miss show up here) (7,883)

1. text “Für den kranken Gatten” (`Stunden der Andacht/Für den kranken Gatten`), part 65 of `Stunden der Andacht` (collection, 102 parts, 100 texts)
1. text “Georg Kleinfeller” (`Paulys Realencyclopädie der classischen Altertumswissenschaft/Register/Georg Kleinfeller`), part 391 of `Paulys Realencyclopädie der classischen Altertumswissenschaft` (collection, 1,325 parts, 1,297 texts)
1. text “Fritz Gschnitzer” (`Paulys Realencyclopädie der classischen Altertumswissenschaft/Register/Fritz Gschnitzer`), part 373 of `Paulys Realencyclopädie der classischen Altertumswissenschaft` (collection, 1,325 parts, 1,297 texts)
1. text “Riedeldorf” (`Geschichte von Kloster Heilsbronn/Riedeldorf`), part 372 of `Geschichte von Kloster Heilsbronn` (collection, 484 parts, 482 texts)
1. text “Leiden” (`Christliche Symbolik/Leiden`), part 315 of `Christliche Symbolik` (collection, 636 parts, 633 texts)
1. text “Richard Foerster” (`Paulys Realencyclopädie der classischen Altertumswissenschaft/Register/Richard Foerster`), part 1012 of `Paulys Realencyclopädie der classischen Altertumswissenschaft` (collection, 1,325 parts, 1,297 texts)
1. text “Romanze XIV: Apo und Meliore” (`Romanzen vom Rosenkranz/Romanze XIV: Apo und Meliore`), part 16 of `Romanzen vom Rosenkranz` (collection, 24 parts, 24 texts)
1. text “Eduardo Luigi De Stefani” (`Paulys Realencyclopädie der classischen Altertumswissenschaft/Register/Eduardo Luigi De Stefani`), part 208 of `Paulys Realencyclopädie der classischen Altertumswissenschaft` (collection, 1,325 parts, 1,297 texts)
1. text “Otto Crusius” (`Paulys Realencyclopädie der classischen Altertumswissenschaft/Register/Otto Crusius`), part 898 of `Paulys Realencyclopädie der classischen Altertumswissenschaft` (collection, 1,325 parts, 1,297 texts)
1. text “Achstetten” (`Beschreibung des Oberamts Laupheim/Achstetten`), part 9 of `Beschreibung des Oberamts Laupheim` (collection, 50 parts, 43 texts)
1. text “Malmersdorf” (`Geschichte von Kloster Heilsbronn/Malmersdorf`), part 295 of `Geschichte von Kloster Heilsbronn` (collection, 484 parts, 482 texts)
1. text “Gemeinde Berkheim” (`Beschreibung des Oberamts Leutkirch/Berkheim`), part 9 of `Beschreibung des Oberamts Leutkirch` (collection, 32 parts, 25 texts)
1. text “Wie nach der letzsten schlacht, so die Cimbri verloren, etliche von inen wider in das Teutschland komen, die sich vor dem Schwarzwald an dem Negker nidergelassen, dieselbig art erbawen und bewonet hab” (`Zimmerische Chronik/Band 1/Kapitel 2`), part 5 of `Zimmerische Chronik` (collection, 159 parts, 139 texts)
1. text “Aglishardt” (`Beschreibung des Oberamts Urach/Kapitel B 4`), part 11 of `Beschreibung des Oberamts Urach` (collection, 36 parts, 29 texts)
1. text “Carl Watzinger” (`Paulys Realencyclopädie der classischen Altertumswissenschaft/Register/Carl Watzinger`), part 150 of `Paulys Realencyclopädie der classischen Altertumswissenschaft` (collection, 1,325 parts, 1,297 texts)
1. text “Monate” (`Christliche Symbolik/Monate`), part 349 of `Christliche Symbolik` (collection, 636 parts, 633 texts)
1. text “Alt-Steußlingen mit Briel” (`Beschreibung des Oberamts Ehingen/Kapitel B 6`), part 13 of `Beschreibung des Oberamts Ehingen` (collection, 55 parts, 48 texts)
1. text “Ringsheim” (`Die Kunstdenkmäler des Grossherzogthums Baden. Band 6/Ringsheim`), part 17 of `Die Kunstdenkmäler des Grossherzogthums Baden. Band 6` (collection, 30 parts, 30 texts)
1. text “Dritter Brieff” (`Merckwürdige Nachricht aus Ost-Indien/Dritter Brieff`), part 3 of `Merckwürdige Nachricht aus Ost-Indien` (collection, 7 parts, 7 texts)
1. text “71 Bei dem Grabe einer alten armen Frau” (`Melpomene/Band 1/071 Bei dem Grabe einer alten armen Frau`), part 92 of `Melpomene` (collection, 223 parts, 221 texts)
1. text “Vom falsch vnd beschisß” (`Doctor Brants Narrenschiff/Von Falschheit und Betrug`), part 105 of `Doctor Brants Narrenschiff` (collection, 118 parts, 118 texts)
1. text “Die Etappe” (`Ob-Ost/Die Etappe`), part 3 of `Ob-Ost` (collection, 12 parts, 12 texts)
1. text “Stödtlen” (`Beschreibung des Oberamts Ellwangen/Kapitel B 19`), part 26 of `Beschreibung des Oberamts Ellwangen` (collection, 37 parts, 36 texts)
1. text “Heumaden” (`Beschreibung des Oberamts Stuttgart, Amt/Kapitel B 10`), part 17 of `Beschreibung des Oberamts Stuttgart, Amt` (collection, 35 parts, 27 texts)
1. text “Von etlichen seltzamen handlungen, die sich bei zeiten herrn Gotfridt Wernhers freiherrn von Zimbern zue Mösskirch und in der herrschaft zue Guetenstain begeben haben.” (`Zimmerische Chronik/Band 2/Kapitel 44`), part 123 of `Zimmerische Chronik` (collection, 159 parts, 139 texts)
1. text “Berichtigungen” (`Beschreibung des Oberamts Laupheim/Berichtigungen`), part 49 of `Beschreibung des Oberamts Laupheim` (collection, 50 parts, 43 texts)
1. text “Inhaltsverzeichnis 1901–1915” (`Die Musik/Inhaltsverzeichnis 1901–1915`), part 1 of `Die Musik` (collection, 2 parts, 2 texts)
1. text “Döffingen” (`Beschreibung des Oberamts Böblingen/Kapitel B 9`), part 16 of `Beschreibung des Oberamts Böblingen` (collection, 25 parts, 18 texts)
1. text “Tafeln Mosis” (`Christliche Symbolik/Tafeln Mosis`), part 568 of `Christliche Symbolik` (collection, 636 parts, 633 texts)
1. text “Eulalia” (`Rosen-Monate heiliger Frauen/Eulalia`), part 9 of `Rosen-Monate heiliger Frauen` (collection, 61 parts, 60 texts)

### C: oversized (317)

1. `Zeitschrift der Gesellschaft für Erdkunde zu Berlin`: mixed, collection, 72 parts, 0 missing, order toc, validated
    - front: Zeitschrift der Gesellschaft für Erdkunde zu Berlin
    - volume: Zweiter Band (`Zeitschrift der Gesellschaft für Erdkunde zu Berlin/Zweiter Band`) [hierarchy]
      - text: Russische wissenschaftliche Expeditionen im Jahre 1864 und 1865 in Türkistān. (`Russische wissenschaftliche Expeditionen im Jahre 1864 und 1865 in Türkistān`) [container]
      - text: Der Aufstand der Muhamedaner im westlichen China. (`Der Aufstand der Muhamedaner im westlichen China`) [container]
      - text: Die Wege des Landhandels zwischen Rußland und China. (`Die Wege des Landhandels zwischen Rußland und China`) [container]
      - text: Nowaja Semlje. (`Nowaja Semlje`) [container]
    - volume: Vierter Band (`Zeitschrift der Gesellschaft für Erdkunde zu Berlin/Vierter Band`) [hierarchy]
      - text: Die höhlenbewohnenden Kannibalen in Süd-Afrika [container]
    - text: P. v. Ssemenof’s Forschungsreisen in den Trans-Ilischen Alatau und zum Issyk-Kul, (`P. v. Ssemenof’s Forschungsreisen in den Trans-Ilischen Alatau und zum Issyk-Kul`) [container]
    - volume: Fünfter Band. (`Zeitschrift der Gesellschaft für Erdkunde zu Berlin/Fünfter Band`) [hierarchy]
      - text: Eine Besteigung des Pik von Teneriffa [container]
      - text: Wahrnehmungen auf einer Fahrt von Chartūm nach dem Gazellen-Fluß, Januar–März 1869 (`Wahrnehmungen auf einer Fahrt von Chartūm nach dem Gazellen-Fluß`) [container]
    - … 61 more
1. `Die Gartenlaube (1890)`: mixed, collection, 352 parts, 0 missing, order toc-partial, validated
    - front: Die Gartenlaube (1890)
    - text: Am Rheinfall bei Schaffhausen [toc]
    - text: Ueber Gehörinstrumente für Schwerhörige [toc]
    - text: Theodor Fontane (`Theodor Fontane (Gottschall)`) [toc]
    - text: Amor der Honigdieb [toc]
    - text: Neujahrsgruß für 1890 [toc]
    - text: Die Entdeckung der Nilquellen und Stanleys jüngster Afrikazug [toc]
    - text: Quitt [toc]
    - text: Der Bazar [toc]
    - text: Fürsorge für Genesende (`Fürsorge für Genesende (Die Gartenlaube 1890/1)`) [toc]
    - text: Gewitter auf dem Sonnblick [toc]
    - text: Des Todes Ernte unter unseren Dichtern [toc]
    - … 341 more
1. `In Berlin – Friedrichstraße`: single, single, 1 parts, 0 missing, order –, proofread
    - text: In Berlin – Friedrichstraße
1. `Chemnitz am Ende des XIX Jahrhunderts in Wort und Bild`: subpages, collection, 14 parts, 0 missing, order chain, validated
    - front: Chemnitz am Ende des XIX Jahrhunderts in Wort und Bild
    - chapter: Einleitung (`Chemnitz am Ende des XIX Jahrhunderts in Wort und Bild/Einleitung`) [container]
    - chapter: Inhalt (`Chemnitz am Ende des XIX Jahrhunderts in Wort und Bild/Inhalt`) [container]
    - chapter: Register (`Chemnitz am Ende des XIX Jahrhunderts in Wort und Bild/Register`) [container]
    - chapter: Vorwort (`Chemnitz am Ende des XIX Jahrhunderts in Wort und Bild/Vorwort`) [container]
    - text: Erste Gruppe (`Chemnitz am Ende des XIX Jahrhunderts in Wort und Bild/Erste Gruppe`) [container]
    - text: Zweite Gruppe (`Chemnitz am Ende des XIX Jahrhunderts in Wort und Bild/Zweite Gruppe`) [container]
    - text: Dritte Gruppe (`Chemnitz am Ende des XIX Jahrhunderts in Wort und Bild/Dritte Gruppe`) [container]
    - text: Vierte Gruppe (`Chemnitz am Ende des XIX Jahrhunderts in Wort und Bild/Vierte Gruppe`) [container]
    - text: Fünfte Gruppe (`Chemnitz am Ende des XIX Jahrhunderts in Wort und Bild/Fünfte Gruppe`) [container]
    - text: Sechste Gruppe (`Chemnitz am Ende des XIX Jahrhunderts in Wort und Bild/Sechste Gruppe`) [container]
    - text: Siebente Gruppe (`Chemnitz am Ende des XIX Jahrhunderts in Wort und Bild/Siebente Gruppe`) [container]
    - … 3 more
1. `Reichsgesetzblatt (Deutschland)`: linked, collection, 78 parts, 0 missing, order chain, proofread
    - front: Reichsgesetzblatt (Deutschland)
    - text: Inhaltsverzeichnis (`Bundesgesetzblatt des Norddeutschen Bundes 1867`) [toc]
    - text: Inhaltsverzeichnis (`Bundesgesetzblatt des Norddeutschen Bundes 1868`) [toc]
    - text: Inhaltsverzeichnis (`Bundesgesetzblatt des Norddeutschen Bundes 1869`) [toc]
    - text: Inhaltsverzeichnis (`Bundesgesetzblatt des Norddeutschen Bundes 1870`) [toc]
    - text: Inhaltsverzeichnis (`Deutsches Reichsgesetzblatt 1871`) [toc]
    - text: Inhaltsverzeichnis (`Deutsches Reichsgesetzblatt 1872`) [toc]
    - text: Inhaltsverzeichnis (`Deutsches Reichsgesetzblatt 1873`) [toc]
    - text: Inhaltsverzeichnis (`Deutsches Reichsgesetzblatt 1874`) [toc]
    - text: Inhaltsverzeichnis (`Deutsches Reichsgesetzblatt 1875`) [toc]
    - text: Inhaltsverzeichnis (`Deutsches Reichsgesetzblatt 1876`) [toc]
    - text: Inhaltsverzeichnis (`Deutsches Reichsgesetzblatt 1877`) [toc]
    - … 67 more
1. `Der Kampf in Dresden im Mai 1849`: single, single, 1 parts, 0 missing, order –, validated
    - text: Der Kampf in Dresden im Mai 1849
1. `Curieuse und sehr wunderbare Relation, von denen sich neuer Dingen in Servien erzeigenden Blut-Saugern oder Vampyrs`: single, single, 1 parts, 0 missing, order –, validated
    - text: Curieuse und sehr wunderbare Relation, von denen sich neuer Dingen in Servien erzeigenden Blut-Saugern oder Vampyrs
1. `Sächsische Volkstrachten und Bauernhäuser (1896)`: single, single, 1 parts, 0 missing, order –, validated
    - text: Sächsische Volkstrachten und Bauernhäuser (1896)
1. `Numerische Mathematik`: single, single, 1 parts, 0 missing, order –, unknown
    - text: Numerische Mathematik
1. `Der Held von Berlin`: single, single, 1 parts, 0 missing, order –, validated
    - text: Der Held von Berlin
1. `Othello, der Mohr von Venedig`: single, single, 1 parts, 0 missing, order –, validated
    - text: Othello, der Mohr von Venedig
1. `Die Gartenlaube (1872)`: mixed, collection, 438 parts, 0 missing, order toc-partial, validated
    - front: Die Gartenlaube (1872)
    - text: Zum neuen Jahr! [toc]
    - text: Am Altar [toc]
    - text: Ein Stillleben in der Havel [toc]
    - text: Der Wundermann auf der G-Saite [toc]
    - text: Der Landwehr blutigster Tag vor Metz [toc]
    - text: Im Palmengarten eines Depossedirten [toc]
    - text: Für einen deutschen Geisteskämpfer [toc]
    - text: Die barmherzige Schwester [toc]
    - text: Französische Wissenschaft [toc]
    - text: Instinct oder Ueberlegung? (`Instinct oder Ueberlegung? (Die Gartenlaube 1872/1)`) [toc]
    - text: Heinrich Kruse (`Heinrich Kruse (Die Gartenlaube 1872/1)`) [toc]
    - … 427 more
1. `Über die Entstehungszeit des Sachsenspiegels und die Ableitung des Schwabenspiegels aus dem Deutschenspiegel`: single, single, 1 parts, 0 missing, order –, validated
    - text: Über die Entstehungszeit des Sachsenspiegels und die Ableitung des Schwabenspiegels aus dem Deutschenspiegel
1. `Die Gartenlaube (1866)`: mixed, collection, 405 parts, 0 missing, order toc-partial, validated
    - front: Die Gartenlaube (1866)
    - text: Goldelse [toc]
    - text: Die Sängerrunde am Weinsberger Thurm [toc]
    - text: Kleine Ursachen, große Wirkung [toc]
    - text: Die Mutter Gottes [toc]
    - text: Eine Alpenstraße [toc]
    - text: Coburgische Erinnerungen an König Leopold von Belgien [toc]
    - text: Onkel und Neffe (`Onkel und Neffe (Die Gartenlaube 1866/1)`) [toc]
    - text: Neuestes Zeloten-Gesangbuch [toc]
    - text: Der Elephant in der Gefangenschaft [toc]
    - text: Eine vergessene Freundin Schiller’s [toc]
    - text: Ein ungarisches Reiterleben [toc]
    - … 394 more
1. `Der Schimmelreiter`: single, single, 1 parts, 0 missing, order –, validated
    - text: Der Schimmelreiter
1. `Die Gartenlaube (1862)`: mixed, collection, 318 parts, 0 missing, order toc-partial, validated
    - front: Die Gartenlaube (1862)
    - text: Thränen der Rose [toc]
    - text: Der Letzte seines Stammes [toc]
    - text: Meister Philipp [toc]
    - text: Kleine amerikanische Sittenbilder. 2. Ein amerikanischer Wahltag (`Ein amerikanischer Wahltag`) [toc]
    - text: Aus der Fremde. Die letzten Menschenfresser (`Die letzten Menschenfresser`) [toc]
    - text: Reisen und Reisende in der Schweiz [toc]
    - text: Ludwig Köhler [toc]
    - text: Ein buchhändlerisches Flottengeschäftchen [toc]
    - text: Ein Nichtamnestirter [toc]
    - text: Das danisirte Irrenhaus [toc]
    - text: Persische Schauspiele [toc]
    - … 307 more
1. `Die Musikalischen Zeitschriften seit ihrer Entstehung bis zur Gegenwart`: single, single, 1 parts, 0 missing, order –, validated
    - text: Die Musikalischen Zeitschriften seit ihrer Entstehung bis zur Gegenwart
1. `Kleiner Briefkasten (Die Gartenlaube 1885)`: single, single, 1 parts, 0 missing, order –, proofread
    - text: Kleiner Briefkasten (Die Gartenlaube 1885)
1. `Monatshefte für Mathematik`: single, single, 1 parts, 0 missing, order –, unknown
    - text: Monatshefte für Mathematik
1. `The Musical Quarterly`: single, single, 1 parts, 0 missing, order –, unknown
    - text: The Musical Quarterly
1. `Hans Brass und die Religion, Oktober 1934`: single, single, 1 parts, 0 missing, order –, proofread
    - text: Hans Brass und die Religion, Oktober 1934
1. `Die Gartenlaube (1886)`: mixed, collection, 475 parts, 5 missing, order toc-partial, proofread
    - front: Die Gartenlaube (1886)
    - text: Zum neuen Jahr (`Zum neuen Jahr (Hermann Lingg)`) [toc]
    - text: Was will das werden? [toc]
    - text: Die Andere [toc]
    - text: Dem Kaiser und König Wilhelm I. [toc]
    - text: Ueber unbewußtes Zählen [toc]
    - text: Berliner Winter [toc]
    - text: Murillo’s Betteljungen [toc]
    - text: Plauderei [toc]
    - text: Eine Papierstatistik der „Gartenlaube“ [toc]
    - text: Auf dem Anschuß (`Auf dem Anschuß (Die Gartenlaube 1886/2)`) [toc]
    - text: Die Nadeln [toc]
    - … 464 more
1. `Das Lahntal von seinem Ursprung bis zur Ausmündung`: single, single, 1 parts, 0 missing, order –, validated
    - text: Das Lahntal von seinem Ursprung bis zur Ausmündung
1. `Geschichte von Kloster Heilsbronn`: subpages, collection, 484 parts, 0 missing, order natural, validated
    - front: Geschichte von Kloster Heilsbronn
    - text: Abdias Wickner (`Geschichte von Kloster Heilsbronn/Abdias Wickner`) [hierarchy]
    - text: Adam Francisci (`Geschichte von Kloster Heilsbronn/Adam Francisci`) [hierarchy]
    - text: Adelhofen (`Geschichte von Kloster Heilsbronn/Adelhofen`) [hierarchy]
    - text: Adelmannsdorf (`Geschichte von Kloster Heilsbronn/Adelmannsdorf`) [hierarchy]
    - text: Adelsdorf (`Geschichte von Kloster Heilsbronn/Adelsdorf`) [hierarchy]
    - text: Aich (`Geschichte von Kloster Heilsbronn/Aich`) [hierarchy]
    - text: Altendettelsau (`Geschichte von Kloster Heilsbronn/Altendettelsau`) [hierarchy]
    - text: Altentrüdingen (`Geschichte von Kloster Heilsbronn/Altentrüdingen`) [hierarchy]
    - text: Altheim (`Geschichte von Kloster Heilsbronn/Altheim`) [hierarchy]
    - text: Ammerndorf (`Geschichte von Kloster Heilsbronn/Ammerndorf`) [hierarchy]
    - text: Ammonschönbronn (`Geschichte von Kloster Heilsbronn/Ammonschönbronn`) [hierarchy]
    - … 473 more
1. `Rote Tage`: single, single, 1 parts, 0 missing, order –, validated
    - text: Rote Tage
1. `Die Gartenlaube (1885)`: mixed, collection, 467 parts, 5 missing, order toc-partial, proofread
    - front: Die Gartenlaube (1885)
    - text: Zum neuen Jahr 1885 [toc]
    - text: Die Frau mit den Karfunkelsteinen [toc]
    - text: Karsten Lehr [toc]
    - text: Die Nihilisten. I. Alexander der Zweite und die Reform (`Die Nihilisten`) [toc]
    - text: Elfenbein und Palmöl [toc]
    - text: Zum 100jährigen Geburtstag Jakob Grimm’s [toc]
    - text: Eine originelle Notenzeichnung [toc]
    - text: Der Urlauber [toc]
    - text: Das neue Leipziger Gewandhaus [toc]
    - text: Desdemona (`Desdemona (Die Gartenlaube 1885/1)`) [toc]
    - text: Das Zippelhaus in Hamburg [toc]
    - … 456 more
1. `Nord und Süd`: linked, collection, 2 parts, 1 missing, order toc, validated
    - front: Nord und Süd
    - text: Ein Aufsatz von Heinrich von Kleist [container]
    - text: Parlamentarismus [container]
1. `Faust - Der Tragödie zweiter Teil`: single, single, 1 parts, 0 missing, order –, validated
    - text: Faust - Der Tragödie zweiter Teil
1. `Raisonirendes Journal vom deutschen Theater zu Hamburg (1.–13. Stück)`: single, single, 1 parts, 0 missing, order –, proofread
    - text: Raisonirendes Journal vom deutschen Theater zu Hamburg (1.–13. Stück)
1. `Kleiner Briefkasten (Die Gartenlaube 1880)`: single, single, 1 parts, 0 missing, order –, validated
    - text: Kleiner Briefkasten (Die Gartenlaube 1880)

### G: reference works (0)


### H: deep nesting (7)

1. `Annalen der Physik`: mixed, collection, 126 parts, 0 missing, order natural, proofread
    - front: Annalen der Physik
    - text: Acht und zwanzigste Reihe von Experimental-Untersuchungen über Elektricität [container]
    - text: Achte Reihe von Experimental-Untersuchungen über Elektricität [container]
    - volume: Annalen der Physik und Chemie, Band LX [container]
      - text: Ueber elektrische Abbildungen [container]
      - text: Untersuchung über das vom Prof. Moser zu Königsberg entdeckte dunkle Licht, und über die Erzeugung von Wärmebildern [container]
      - text: Erwiderung an die HH. Fizeau und Daguerre [container]
      - text: Ein Experiment zum Beweise, daß im Quecksilberdampf latentes Licht vorhanden sey [container]
      - text: Entwicklung der Lehre von dem Glanze [container]
      - text: Das specifische Gewicht der Schwefelsäure bei verschiedenen Graden der Verdünnung [container]
      - text: Ueber den Nebenstrom im getheilten Schließungsdraht der Batterie (`Ueber den Nebenstrom im getheilten Schließungsdrahte der Batterie`) [container]
      - text: Ein Paar Bemerkungen über die neue Theorie in Herrn Professor Chr. Doppler’s Schrift: „Ueber das farbige Licht der Doppelsterne und einiger anderer Gestirne des Himmels“ (`Ein Paar Bemerkungen über die neue Theorie in Herrn Professor Chr. Doppler’s Schrift`) [container]
    - … 115 more
1. `Geschichte der Stadt Basel. Zweiten Bandes zweiter Teil`: subpages, collection, 8 parts, 0 missing, order chain, validated
    - front: Geschichte der Stadt Basel. Zweiten Bandes zweiter Teil
    - text: Inhaltsübersicht (`Geschichte der Stadt Basel. Zweiten Bandes zweiter Teil/Inhaltsübersicht`) [container]
    - volume: Stadt und Gesellschaft von der rudolfinischen Zeit bis zur Reformation (`Geschichte der Stadt Basel. Zweiten Bandes zweiter Teil/8. Stadt und Gesellschaft von der rudolfinischen Zeit bis zur Reformation`) [container]
      - text: Schule und Gelehrsamkeit (`Geschichte der Stadt Basel. Zweiten Bandes zweiter Teil/8. Stadt und Gesellschaft von der rudolfinischen Zeit bis zur Reformation/5. Schule und Gelehrsamkeit`) [container]
      - text: Lebensformen und Gesinnung (`Geschichte der Stadt Basel. Zweiten Bandes zweiter Teil/8. Stadt und Gesellschaft von der rudolfinischen Zeit bis zur Reformation/7. Lebensformen und Gesinnung`) [container]
      - volume: 6. Die Kirche (`Geschichte der Stadt Basel. Zweiten Bandes zweiter Teil/8. Stadt und Gesellschaft von der rudolfinischen Zeit bis zur Reformation/6. Die Kirche`) [hierarchy]
        - chapter: Erster Teil (`Geschichte der Stadt Basel. Zweiten Bandes zweiter Teil/8. Stadt und Gesellschaft von der rudolfinischen Zeit bis zur Reformation/6. Die Kirche/1`) [container]
        - chapter: Zweiter Teil (`Geschichte der Stadt Basel. Zweiten Bandes zweiter Teil/8. Stadt und Gesellschaft von der rudolfinischen Zeit bis zur Reformation/6. Die Kirche/2`) [container]
    - text: Anmerkungen und Belege (`Geschichte der Stadt Basel. Zweiten Bandes zweiter Teil/Anmerkungen und Belege`) [container]
1. `Journal von und für Franken`: linked, collection, 398 parts, 0 missing, order toc-partial, proofread
    - front: Journal von und für Franken
    - chapter: Vorrede (`Vorrede (Journal von und für Franken)`) [container]
    - text: Abermahls ein medicinischer Charlatan in Franken [container]
    - text: Actenstücke die Aufnahme der Französischen Emigranten in Franken betreffend [container]
    - volume: An die Herausgeber des Journals von und für Franken [container]
      - text: Erklärung auf die Aufforderung des Herrn Professor Feders in Wirzburg [container]
      - text: Mein Nachtrag zu der vorläufigen Antwort der Herausgeber des Journals v. und für Franken auf die versuchte Apologie des Herrn P. Cajetan a S. Andrea. d. Z. Priorn [container]
      - text: Bestättigung der traurigen Geschichte des P. Anians, nebst der Kerkergeschichte des P. Mansuet Oehningers, Capuciners zu Wirzburg [container]
      - text: Schreiben der sämmtlichen Obrigkeiten der Capuciner-Provinz in Franken an die Herausgeber dieses Journals, mit Anmerkungen von den letztern begleitet [container]
      - text: Über die Industrie-Anstalten im Wirzburgischen [container]
      - text: Meine Erklärung gegen das, was Herr Hofrath Seuffert zu Wirzburg über mein Urtheil, das Industrie-Wesen im Hochstifte Wirzburg betreffend, im Magazin zur Beförderung des Schulwesens im katholischen Te (`Meine Erklärung gegen das, was Herr Hofrath Seuffert zu Wirzburg über mein Urtheil, das Industrie-Wesen im Hochstifte Wirzburg betreffend, im Magazin zur Beförderung des Schulwesens im katholischen Teutschlande, 1ten B. ersten Hefte S.15 erinnert hat`) [container]
      - volume: III. Heft S. 341 (`Miscellaneen (Journal von und für Franken, Band 2, 3)`) [container]
    - … 387 more
1. `Der Todesgang des armenischen Volkes`: subpages, book, 38 parts, 0 missing, order scan, validated
    - front: Der Todesgang des armenischen Volkes
    - text: Titel (`Der Todesgang des armenischen Volkes/Titel`) [hierarchy]
    - chapter: Vorwort. (`Der Todesgang des armenischen Volkes/Vorwort`) [hierarchy]
    - volume: Erster Teil. – Die Tatsachen. (`Der Todesgang des armenischen Volkes/Erster Teil`) [hierarchy]
      - volume: Erstes Kapitel (`Der Todesgang des armenischen Volkes/Erster Teil/Erstes Kapitel`) [hierarchy]
        - volume: Erster Teil (`Der Todesgang des armenischen Volkes/Erster Teil/Erstes Kapitel/Erster Teil`) [hierarchy]
          - chapter: Erster Abschnitt (`Der Todesgang des armenischen Volkes/Erster Teil/Erstes Kapitel/Erster Teil/Erster Abschnitt`) [hierarchy]
          - chapter: Dritter Abschnitt (`Der Todesgang des armenischen Volkes/Erster Teil/Erstes Kapitel/Erster Teil/Dritter Abschnitt`) [hierarchy]
          - chapter: Zweiter Abschnitt (`Der Todesgang des armenischen Volkes/Erster Teil/Erstes Kapitel/Erster Teil/Zweiter Abschnitt`) [hierarchy]
          - chapter: Vierter Abschnitt (`Der Todesgang des armenischen Volkes/Erster Teil/Erstes Kapitel/Erster Teil/Vierter Abschnitt`) [hierarchy]
        - volume: Zweiter Teil (`Der Todesgang des armenischen Volkes/Erster Teil/Erstes Kapitel/Zweiter Teil`) [hierarchy]
          - chapter: Erster Abschnitt (`Der Todesgang des armenischen Volkes/Erster Teil/Erstes Kapitel/Zweiter Teil/Erster Abschnitt`) [hierarchy]
    - … 27 more
1. `Album der Rittergüter und Schlösser im Königreiche Sachsen`: linked, collection, 775 parts, 13 missing, order natural, proofread
    - front: Album der Rittergüter und Schlösser im Königreiche Sachsen
    - volume: Leipziger Kreis. (`Album der Rittergüter und Schlösser im Königreiche Sachsen I. Section`) [container]
      - text: Rötha (`Rittergüter und Schlösser im Königreiche Sachsen: Rötha`) [container]
      - text: Grosszschocher (`Rittergüter und Schlösser im Königreiche Sachsen: Grosszschocher`) [container]
      - text: Knauthayn (`Rittergüter und Schlösser im Königreiche Sachsen: Knauthayn`) [container]
      - text: Dölitz (`Rittergüter und Schlösser im Königreiche Sachsen: Dölitz`) [container]
      - text: Püchau (`Rittergüter und Schlösser im Königreiche Sachsen: Püchau`) [container]
      - text: Zedtlitz (`Rittergüter und Schlösser im Königreiche Sachsen: Zedtlitz`) [container]
      - text: Thallwitz (`Rittergüter und Schlösser im Königreiche Sachsen: Thallwitz`) [container]
      - text: Wachau (`Rittergüter und Schlösser im Königreiche Sachsen: Wachau (Leipzig)`) [container]
      - text: Gnandstein (`Rittergüter und Schlösser im Königreiche Sachsen: Gnandstein`) [container]
      - text: Pomsen (`Rittergüter und Schlösser im Königreiche Sachsen: Pomsen`) [container]
    - … 764 more
1. `Frau Auguste Pattberg geb. von Kettner`: linked, collection, 38 parts, 0 missing, order toc, validated
    - front: Frau Auguste Pattberg geb. von Kettner
    - text: Ein altes Volkslied [container]
    - text: Die Wanderschaft [container]
    - text: Auf die urkundliche Berichtigung der Volkssage vom Minneberg [container]
    - text: Antikritik [container]
    - text: Das Neckarthal [container]
    - volume: Volkssage von der Burg Stolzeneck [container]
      - text: Der Minneberg [container]
      - volume: Volkssage (`Volkssage (Badische Wochenschrift Nr. 2)`) [container]
        - text: Volkssage (`Volkssage (Badische Wochenschrift Nr. 34)`) [container]
    - text: Volkssage (`Volkssage (Badische Wochenschrift Nr. 19)`) [container]
    - text: Nachricht von einigen Volksfesten [container]
    - … 27 more
1. `Adrian Ludwig Richter, Maler und Radierer. Verzeichnis seines gesamten graphischen Werkes`: linked, collection, 3 parts, 0 missing, order single, proofread
    - front: Adrian Ludwig Richter, Maler und Radierer. Verzeichnis seines gesamten graphischen Werkes
    - volume: II. Künstlerwiedergaben nach dem Meister (`Adrian Ludwig Richter, Maler und Radierer. Verzeichnis seines gesamten graphischen Werkes - Fortsetzung 1`) [toc]
      - volume: Fortsetzung siehe: Adrian Ludwig Richter, Maler und Radierer. Verzeichnis seines gesamten graphischen Werkes - Fortsetzung 2 (`Adrian Ludwig Richter, Maler und Radierer. Verzeichnis seines gesamten graphischen Werkes - Fortsetzung 2`) [toc]
        - text: Fortsetzung siehe: Adrian Ludwig Richter, Maler und Radierer. Verzeichnis seines gesamten graphischen Werkes - Fortsetzung 3 (`Adrian Ludwig Richter, Maler und Radierer. Verzeichnis seines gesamten graphischen Werkes - Fortsetzung 3`) [toc]

### I: works with other editions (49)

1. `Archiv für das Studium der neueren Sprachen und Literaturen`: linked, collection, 13 parts, 0 missing, order natural, validated
    - front: Archiv für das Studium der neueren Sprachen und Literaturen
    - text: Der Rabe (`Der Rabe (Übersetzung Neidhardt)`) [container]
    - volume: Friedrich Gottlob Wetzel als Beiträger zu Heinrich von Kleists „Berliner Abendblättern“ [container]
      - text: Guter Rath (`Guter Rath (Wetzel)`) [container]
      - text: Zeichen [container]
      - text: Der Kreis [container]
      - text: Schönheit [container]
      - text: Austausch [container]
      - text: Gut und Schlecht [container]
      - text: Eigentliches Leben [container]
      - text: Richtschnur [container]
      - text: An die Recensenten der Elemente der Staatskunst von Adam Müller [container]
    - … 2 more
1. `Kinder- und Haus-Märchen Band 3 (1856)`: mixed, collection, 11 parts, 0 missing, order natural, validated
    - front: Kinder- und Haus-Märchen Band 3 (1856)
    - text: Der gestiefelte Kater (`Der gestiefelte Kater (1856)`) [container]
    - text: Der Mann vom Galgen (`Der Mann vom Galgen (1856)`) [container]
    - text: Der starke Hans (`Der starke Hans (1856)`) [container]
    - text: Die böse Schwiegermutter (`Die böse Schwiegermutter (1856)`) [container]
    - text: Die Laus (`Die Laus (1856)`) [container]
    - text: Anmerkungen zu den einzelnen Märchen (`Kinder- und Haus-Märchen Band 3 (1856)/Anmerkungen`) [container]
    - text: Bruchstücke (`Kinder- und Haus-Märchen Band 3 (1856)/Bruchstücke`) [container]
    - text: Literatur (`Kinder- und Haus-Märchen Band 3 (1856)/Literatur`) [container]
    - chapter: Register zur Literatur (`Kinder- und Haus-Märchen Band 3 (1856)/Register`) [container]
    - text: Zeugnisse (`Kinder- und Haus-Märchen Band 3 (1856)/Zeugnisse`) [container]
    - text: Märchenhafte Bruchstücke in Volksliedern (`Märchenhafte Bruchstücke in Volksliedern (1856)`) [container]
1. `Die Volkssagen der Stadt Freiburg im Breisgau`: linked, collection, 62 parts, 0 missing, order chain, validated
    - front: Die Volkssagen der Stadt Freiburg im Breisgau
    - text: Der Köhler [container]
    - text: Zähringen [container]
    - text: Der Herr von Kyburg [container]
    - text: Freiburg’s Gründung [container]
    - text: Ein Todtenbaum (`Ein Todtenbaum (Volkssagen)`) [container]
    - text: Der versteinerte Herzog (`Der versteinerte Herzog (Volkssagen)`) [container]
    - text: Das Münster [container]
    - text: Die Silbergrube bei Zähringen [container]
    - text: Der unterirdische Gang in das Münster (`Der unterirdische Gang in das Münster (Volkssagen)`) [container]
    - text: Das Silberglöckchen [container]
    - text: Das Nonnenbild am Münsterchor [container]
    - … 51 more
1. `Kinder- und Haus-Märchen Band 1 (1819)`: linked, collection, 86 parts, 0 missing, order chain, validated
    - front: Kinder- und Haus-Märchen Band 1 (1819)
    - text: Der Froschkönig oder der eiserne Heinrich (`Der Froschkönig oder der eiserne Heinrich (1819)`) [container]
    - text: Katz und Maus in Gesellschaft (`Katz und Maus in Gesellschaft (1819)`) [container]
    - text: Marienkind (`Marienkind (1819)`) [container]
    - text: Märchen von einem, der auszog, das Fürchten zu lernen (`Märchen von einem, der auszog, das Fürchten zu lernen (1819)`) [container]
    - text: Der Wolf und die sieben jungen Geislein (`Der Wolf und die sieben jungen Geislein (1819)`) [container]
    - text: Der getreue Johannes (`Der getreue Johannes (1819)`) [container]
    - text: Der gute Handel (`Der gute Handel (1819)`) [container]
    - text: Der wunderliche Spielmann (`Der wunderliche Spielmann (1819)`) [container]
    - text: Die zwölf Brüder (`Die zwölf Brüder (1819)`) [container]
    - text: Das Lumpengesindel (`Das Lumpengesindel (1819)`) [container]
    - text: Brüderchen und Schwesterchen (`Brüderchen und Schwesterchen (1819)`) [container]
    - … 75 more
1. `Kinder- und Haus-Märchen Band 1 (1837)`: linked, collection, 86 parts, 0 missing, order chain, validated
    - front: Kinder- und Haus-Märchen Band 1 (1837)
    - text: Der Froschkönig oder der eiserne Heinrich (`Der Froschkönig oder der eiserne Heinrich (1837)`) [container]
    - text: Katze und Maus in Gesellschaft (`Katze und Maus in Gesellschaft (1837)`) [container]
    - text: Marienkind (`Marienkind (1837)`) [container]
    - text: Märchen von einem, der auszog, das Fürchten zu lernen (`Märchen von einem, der auszog, das Fürchten zu lernen (1837)`) [container]
    - text: Der Wolf und die sieben jungen Geislein (`Der Wolf und die sieben jungen Geislein (1837)`) [container]
    - text: Der treue Johannes (`Der treue Johannes (1837)`) [container]
    - text: Der gute Handel (`Der gute Handel (1837)`) [container]
    - text: Der wunderliche Spielmann (`Der wunderliche Spielmann (1837)`) [container]
    - text: Die zwölf Brüder (`Die zwölf Brüder (1837)`) [container]
    - text: Das Lumpengesindel (`Das Lumpengesindel (1837)`) [container]
    - text: Brüderchen und Schwesterchen (`Brüderchen und Schwesterchen (1837)`) [container]
    - … 75 more
1. `Ode an die Freude`: single, single, 1 parts, 0 missing, order –, validated
    - text: Ode an die Freude
1. `Der Fuchs und die Gänse (1812)`: single, single, 1 parts, 0 missing, order –, validated
    - text: Der Fuchs und die Gänse (1812)
1. `103 Gedichte`: linked, collection, 89 parts, 0 missing, order toc-partial, validated
    - front: 103 Gedichte
    - text: Überall [container]
    - text: Schiffer-Sentiment [container]
    - text: Vorm Brunnen in Wimpfen (`Vorm Brunnen in Wimpfen (103 Gedichte)`) [container]
    - text: An der Alten Elster [container]
    - text: Wer hört ein Stäubchen lachen? [container]
    - text: Morgenwonne [container]
    - text: Humpelnde Welt [container]
    - text: Fand meinen einen Handschuh wieder [container]
    - text: Einer meiner Bürsten [container]
    - text: Meine Schuhsohlen [container]
    - text: Die Litfaßsäulen [container]
    - … 78 more
1. `Wünschelruthe`: linked, collection, 186 parts, 0 missing, order toc-partial, validated
    - front: Wünschelruthe
    - text: Wünschelruthe (`Wünschelruthe (Gedicht)`) [container]
    - text: Erzählung [container]
    - text: Lindaraja’s Gefangenschaft und Befreiung [container]
    - text: Tanzreim [container]
    - text: Volkslieder (`Volkslieder (Wünschelruthe)`) [container]
    - text: Geschichten und Schnurren [container]
    - text: Spruch von der Bibel [container]
    - text: Lied (`Lied (Wünschelruthe Nro. 3)`) [container]
    - text: Aus Shakespears Much ado about nothing [container]
    - text: Klagen und Tröstung [container]
    - text: Triolett [container]
    - … 175 more
1. `Morgenblatt (Cotta)`: linked, collection, 5 parts, 0 missing, order toc, validated
    - front: Morgenblatt (Cotta)
    - text: Hypochonders Mondlied (`Hypochonders Mondlied (1836)`) [container]
    - text: Die Judenbuche [container]
    - text: Die rechte Gabe [container]
    - text: Rose und Lilie [container]
    - text: Bei der Marien-Bergkirche von L. [container]
1. `Kinder- und Haus-Märchen Band 2 (1837)`: linked, collection, 91 parts, 0 missing, order chain, validated
    - front: Kinder- und Haus-Märchen Band 2 (1837)
    - text: Der Arme und der Reiche (`Der Arme und der Reiche (1837)`) [container]
    - text: Das singende springende Löweneckerchen (`Das singende springende Löweneckerchen (1837)`) [container]
    - text: Die Gänsemagd (`Die Gänsemagd (1837)`) [container]
    - text: Der junge Riese (`Der junge Riese (1837)`) [container]
    - text: Dat Erdmänneken (`Dat Erdmänneken (1837)`) [container]
    - text: Der König vom goldenen Berg (`Der König vom goldenen Berg (1837)`) [container]
    - text: Die Rabe (`Die Rabe (1837)`) [container]
    - text: Die kluge Bauerntochter (`Die kluge Bauerntochter (1837)`) [container]
    - text: Der alte Hildebrand (`Der alte Hildebrand (1837)`) [container]
    - text: De drei Vügelkens (`De drei Vügelkens (1837)`) [container]
    - text: Das Wasser des Lebens (`Das Wasser des Lebens (1837)`) [container]
    - … 80 more
1. `Badisches Sagen-Buch`: linked, collection, 600 parts, 0 missing, order toc, validated
    - front: Badisches Sagen-Buch
    - volume: Badisches Sagen-Buch. Erste Abtheilung: Vom Bodensee bis zur Ortenau. (`Badisches Sagen-Buch I`) [container]
      - chapter: Vorwort des Herausgebers (`Vorwort des Herausgebers (Badisches Sagen-Buch)`) [container]
      - text: Baden (`Baden (Heß)`) [container]
      - text: Vorspiel (`Vorspiel (Badisches Sagen-Buch)`) [container]
      - chapter: Einleitung (`Einleitung (Badisches Sagen-Buch)`) [container]
      - text: Die Schöpfung des Bodensee’s (`Die Schöpfung des Bodensee’s (Badisches Sagen-Buch)`) [container]
      - text: Die Prinzessin vom Bodensee [container]
      - text: Conradin am See [container]
      - text: Der Reiter und der Bodensee (`Der Reiter und der Bodensee (Badisches Sagen-Buch)`) [container]
      - text: Graf Gero von Montfort (`Graf Gero von Montfort (Badisches Sagen-Buch)`) [container]
      - text: Tegelstein [container]
    - … 589 more
1. `Kinder- und Haus-Märchen Band 1 (1857)`: linked, collection, 86 parts, 0 missing, order chain, validated
    - front: Kinder- und Haus-Märchen Band 1 (1857)
    - text: Der Froschkönig oder der eiserne Heinrich (`Der Froschkönig oder der eiserne Heinrich (1857)`) [container]
    - text: Katze und Maus in Gesellschaft (`Katze und Maus in Gesellschaft (1857)`) [container]
    - text: Marienkind (`Marienkind (1857)`) [container]
    - text: Mährchen von einem, der auszog das Fürchten zu lernen (`Mährchen von einem, der auszog das Fürchten zu lernen (1857)`) [container]
    - text: Der Wolf und die sieben jungen Geislein (`Der Wolf und die sieben jungen Geislein (1857)`) [container]
    - text: Der treue Johannes (`Der treue Johannes (1857)`) [container]
    - text: Der gute Handel (`Der gute Handel (1857)`) [container]
    - text: Der wunderliche Spielmann (`Der wunderliche Spielmann (1857)`) [container]
    - text: Die zwölf Brüder (`Die zwölf Brüder (1857)`) [container]
    - text: Das Lumpengesindel (`Das Lumpengesindel (1857)`) [container]
    - text: Brüderchen und Schwesterchen (`Brüderchen und Schwesterchen (1857)`) [container]
    - … 75 more
1. `An die Freude (Beethoven)`: single, single, 1 parts, 0 missing, order –, proofread
    - text: An die Freude (Beethoven)
1. `Der Rabe (Übersetzung Eben)`: single, single, 1 parts, 0 missing, order –, validated
    - text: Der Rabe (Übersetzung Eben)
1. `Das von den Juden getödtete Mägdlein (Deutsche Sagen)`: single, single, 1 parts, 0 missing, order –, validated
    - text: Das von den Juden getödtete Mägdlein (Deutsche Sagen)
1. `Kinder- und Haus-Märchen Band 2 (1819)`: linked, collection, 84 parts, 0 missing, order natural, validated
    - front: Kinder- und Haus-Märchen Band 2 (1819)
    - text: Armuth und Demuth führen zum Himmel (`Armuth und Demuth führen zum Himmel (1819)`) [container]
    - text: Das alte Mütterchen (`Das alte Mütterchen (1819)`) [container]
    - text: Das blaue Licht (`Das blaue Licht (1819)`) [container]
    - text: Das Dietmarsische Lügen-Märchen (`Das Dietmarsische Lügen-Märchen (1819)`) [container]
    - text: Das eigensinnige Kind (`Das eigensinnige Kind (1819)`) [container]
    - text: Das Eselein (`Das Eselein (1819)`) [container]
    - text: Das Hausgesinde (`Das Hausgesinde (1819)`) [container]
    - text: Das Hirtenbüblein (`Das Hirtenbüblein (1819)`) [container]
    - text: Das junggeglühte Männlein (`Das junggeglühte Männlein (1819)`) [container]
    - text: Das Lämmchen und Fischchen (`Das Lämmchen und Fischchen (1819)`) [container]
    - text: Das Märchen vom Schlauraffenland (`Das Märchen vom Schlauraffenland (1819)`) [container]
    - … 73 more
1. `Kinder- und Haus-Märchen Band 1 (1812)`: linked, collection, 90 parts, 0 missing, order natural, validated
    - front: Kinder- und Haus-Märchen Band 1 (1812)
    - text: Allerlei-Rauh (`Allerlei-Rauh (1812)`) [container]
    - text: Aschenputtel (`Aschenputtel (1812)`) [container]
    - text: Blaubart (`Blaubart (1812)`) [container]
    - text: Brüderchen und Schwesterchen (`Brüderchen und Schwesterchen (1812)`) [container]
    - text: Das arme Mädchen (`Das arme Mädchen (1812)`) [container]
    - text: Das Birnli will nit fallen (`Das Birnli will nit fallen (1812)`) [container]
    - text: Das Goldei (`Das Goldei (1812)`) [container]
    - text: Das gute Pflaster (`Das gute Pflaster (1812)`) [container]
    - text: Das Lumpengesindel (`Das Lumpengesindel (1812)`) [container]
    - text: Das Mordschloß (`Das Mordschloß (1812)`) [container]
    - text: Der alte Großvater und der Enkel (`Der alte Großvater und der Enkel (1812)`) [container]
    - … 79 more
1. `Ausgewählte Gedichte (Poe)`: linked, collection, 18 parts, 1 missing, order chain, validated
    - front: Ausgewählte Gedichte (Poe)
    - text: An Helene [container]
    - text: Die Glocken [container]
    - text: Ulalume [container]
    - text: Schweigen (`Schweigen (Poe)`) [container]
    - text: Die Schläferin [container]
    - text: Die Stadt im Meer [container]
    - text: Annabel Lee [container]
    - text: An Annie [container]
    - text: An F . . . s. (`An F....s`) [container]
    - text: Eulalie [container]
    - text: An . . . . (`An ....`) [container]
    - … 7 more
1. `Was die Heimat erzählt`: mixed, collection, 224 parts, 0 missing, order chain, validated
    - front: Was die Heimat erzählt
    - chapter: Vorwort (`Was die Heimat erzählt/Vorwort`) [hierarchy]
    - text: Die westliche Lausitz [container]
    - text: Das Schloss zu Radeberg [container]
    - text: Altrabrig (`Altrabrig (Störzner)`) [container]
    - text: Das alte Grabgewölbe bei Radeberg [container]
    - text: Der Freudenberg in Radeberg [container]
    - text: Ehemaliger Bergbau bei Radeberg [container]
    - text: Die Radeberger Bürgerwehr [container]
    - text: Das Todaustreiben in Radeberg [container]
    - text: Der Pestkirchhof in Radeberg [container]
    - text: Eine Erinnerung an den Vater des Dichters Langbein [container]
    - … 213 more
1. `Der Weihnachts-Abend`: single, single, 1 parts, 0 missing, order –, validated
    - text: Der Weihnachts-Abend
1. `Mährchenkranz für Kinder, der erheiternden Unterhaltung besonders im Familienkreise`: linked, collection, 28 parts, 0 missing, order chain, validated
    - front: Mährchenkranz für Kinder, der erheiternden Unterhaltung besonders im Familienkreise
    - text: Frau Holle (`Frau Holle (Lehnert)`) [container]
    - text: Rothkäppchen (`Rothkäppchen (Lehnert)`) [container]
    - text: Die Grasmücke [container]
    - text: Schneeweißchen und Rosenroth [container]
    - text: Aschenbrödel (`Aschenbrödel (Lehnert)`) [container]
    - text: Die drei Federn (`Die drei Federn (Lehnert)`) [container]
    - text: Das Himmelsschäfchen [container]
    - text: Martin und Ilse [container]
    - text: Von dem Maßholderbaum [container]
    - text: Das Feld mit Hagebuchen [container]
    - text: Der Wunderstein [container]
    - … 17 more
1. `Kinder- und Haus-Märchen Band 1 (1850)`: linked, collection, 86 parts, 0 missing, order chain, validated
    - front: Kinder- und Haus-Märchen Band 1 (1850)
    - text: Der Froschkönig oder der eiserne Heinrich (`Der Froschkönig oder der eiserne Heinrich (1850)`) [container]
    - text: Katze und Maus in Gesellschaft (`Katze und Maus in Gesellschaft (1850)`) [container]
    - text: Marienkind (`Marienkind (1850)`) [container]
    - text: Mährchen von einem, der auszog das Fürchten zu lernen (`Mährchen von einem, der auszog das Fürchten zu lernen (1850)`) [container]
    - text: Der Wolf und die sieben jungen Geislein (`Der Wolf und die sieben jungen Geislein (1850)`) [container]
    - text: Der treue Johannes (`Der treue Johannes (1850)`) [container]
    - text: Der gute Handel (`Der gute Handel (1850)`) [container]
    - text: Der wunderliche Spielmann (`Der wunderliche Spielmann (1850)`) [container]
    - text: Die zwölf Brüder (`Die zwölf Brüder (1850)`) [container]
    - text: Das Lumpengesindel (`Das Lumpengesindel (1850)`) [container]
    - text: Brüderchen und Schwesterchen (`Brüderchen und Schwesterchen (1850)`) [container]
    - … 75 more
1. `Der Sagenschatz des Königreichs Sachsen`: mixed, collection, 1,001 parts, 0 missing, order chain, validated
    - front: Der Sagenschatz des Königreichs Sachsen
    - text: Wie die Raute in das sächsische Wappenschild gekommen ist [container]
    - text: Sage von dem unvergänglichen Bestehen des Hauses Sachsen [container]
    - text: Das Gesicht der Herzogin Agnes [container]
    - text: Traum der Churfürstin Margarethe [container]
    - text: Der Traum des Churfürsten Friedrich III. oder des Weisen [container]
    - text: Friedrich der Weise und sein Bruder werden aus großer Lebensgefahr gerettet [container]
    - text: Friedrich’s des Weisen Tod verkündet ein Hirsch [container]
    - text: Der Tod des Erzbischoffs von Magdeburg Ernst wird verkündigt [container]
    - text: Luther prophezeit einem sächsischen Prinzen den Tod [container]
    - text: Unglückliches Vorzeichen der Schlacht bei Mühlberg [container]
    - text: Dem Churfürsten Johann Friedrich wird sein Tod verkündigt [container]
    - … 990 more
1. `Volkssagen und volksthümliche Denkmale der Lausitz`: linked, collection, 100 parts, 0 missing, order chain, validated
    - front: Volkssagen und volksthümliche Denkmale der Lausitz
    - text: Der schwarze Hund (`Der schwarze Hund (Gräve, 1839)`) [container]
    - text: Die Landeskrone [container]
    - text: Der Schatz auf dem Oybin [container]
    - text: Der Feuerpuhtz [container]
    - text: Das wandernde Stiefelpaar [container]
    - text: Die Wunderblume (`Die Wunderblume (Gräve)`) [container]
    - text: Der Mönch (`Der Mönch (Gräve, 1839)`) [container]
    - text: Das kleine graue Männchen bei Kamenz [container]
    - text: Die Wehklage (`Die Wehklage (Gräve)`) [container]
    - text: Das Todaustreiben – der weiße Mann [container]
    - text: Der Pan Dietrich [container]
    - … 89 more
1. `Der Kübelreiter (1921)`: single, single, 1 parts, 0 missing, order –, validated
    - text: Der Kübelreiter (1921)
1. `Beim Bau der chinesischen Mauer`: single, single, 1 parts, 0 missing, order –, validated
    - text: Beim Bau der chinesischen Mauer
1. `Kinder- und Haus-Märchen Band 2 (1843)`: linked, collection, 117 parts, 0 missing, order chain, validated
    - front: Kinder- und Haus-Märchen Band 2 (1843)
    - text: Der Arme und der Reiche (`Der Arme und der Reiche (1843)`) [container]
    - text: Das singende springende Löweneckerchen (`Das singende springende Löweneckerchen (1843)`) [container]
    - text: Die Gänsemagd (`Die Gänsemagd (1843)`) [container]
    - text: Der junge Riese (`Der junge Riese (1843)`) [container]
    - text: Dat Erdmänneken (`Dat Erdmänneken (1843)`) [container]
    - text: Der König vom goldenen Berg (`Der König vom goldenen Berg (1843)`) [container]
    - text: Die Rabe (`Die Rabe (1843)`) [container]
    - text: Die kluge Bauerntochter (`Die kluge Bauerntochter (1843)`) [container]
    - text: Der alte Hildebrand (`Der alte Hildebrand (1843)`) [container]
    - text: De drei Vügelkens (`De drei Vügelkens (1843)`) [container]
    - text: Das Wasser des Lebens (`Das Wasser des Lebens (1843)`) [container]
    - … 106 more
1. `Kennst du das Land? wo die Citronen blühn`: single, single, 1 parts, 0 missing, order –, validated
    - text: Kennst du das Land? wo die Citronen blühn
1. `Doctor Brants Narrenschiff`: subpages, collection, 118 parts, 1 missing, order chain, validated
    - front: Doctor Brants Narrenschiff
    - text: Inhaltsverzeichnis (`Doctor Brants Narrenschiff/Inhaltsverzeichnis`) [container]
    - text: Protestation (`Doctor Brants Narrenschiff/Protestation`) [container]
    - text: Eyn vorred jn das Narrenschiff (`Doctor Brants Narrenschiff/Vorrede`) [container]
    - text: Von vnnützen bu e chern (`Doctor Brants Narrenschiff/Der Büchernarr`) [container]
    - text: Von gu o ten ra e ten (`Doctor Brants Narrenschiff/Der Narr im Rath`) [container]
    - text: Von gyttikeyt (`Doctor Brants Narrenschiff/Von Geiznarren`) [container]
    - text: Von nuwen fünden (`Doctor Brants Narrenschiff/Von Modenarren`) [container]
    - text: Von altten narren (`Doctor Brants Narrenschiff/Von alten Narren`) [container]
    - text: Von ler der kynd (`Doctor Brants Narrenschiff/Von Kinderverziehern`) [container]
    - text: Von zwittracht machen (`Doctor Brants Narrenschiff/Von Zwietrachtstiftern`) [container]
    - text: Nit volgen gu o ttem rat (`Doctor Brants Narrenschiff/Von unberathnen Narren`) [container]
    - … 107 more

