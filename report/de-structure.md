# de.wikisource: structure and quality survey (WP1a)

Generated 2026-10-31 by `python -I -m wscatalog.survey de` from `dewikisource-20261001-pages-articles.xml.bz2` (resolution and quality rules: PLAN.md 5.2 steps 1–1h and 5.4; config `pipeline/config/de.yaml`). Resolution took 8 s on the cached extract.

Input: 310,980 ns0 pages, 13,381 ns0 redirects, 339,510 scan pages, 7,112 Index pages, 773 pages with the `disambiguation` page prop.

## 1. Works

26,637 works resolved from 80,339 non-excluded ns0 pages; 26,035 enter the catalog after the hard exclusions (section 9). Multi-part works hold 53,748 parts, of which 48,160 are searchable texts.

| structure | works | parts | texts |
|---|---|---|---|
| single | 25,697 | 25,697 | 0 |
| linked | 433 | 20,783 | 20,250 |
| subpages | 372 | 10,193 | 7,690 |
| mixed | 89 | 22,629 | 20,110 |
| virtual | 46 | 143 | 110 |

| type (step 1e) | works |
|---|---|
| single | 25,697 |
| collection | 811 |
| book | 129 |

Largest works by parts:

| work | structure | type | parts | texts | missing | rules of the parts |
|---|---|---|---|---|---|---|
| Paulys Realencyclopädie der classischen Altertumswissenschaft | subpages | collection | 1,325 | 1,297 | 0 | hierarchy 1325 |
| Der Sagenschatz des Königreichs Sachsen | mixed | collection | 1,001 | 1,001 | 0 | container 1001 |
| Zerstreute Blätter | linked | collection | 793 | 780 | 0 | container 787, toc 6 |
| Album der Rittergüter und Schlösser im Königreiche Sachsen | linked | collection | 775 | 757 | 13 | container 775 |
| Christliche Symbolik | subpages | collection | 636 | 633 | 0 | hierarchy 636 |
| Die Gartenlaube (1898) | mixed | collection | 624 | 593 | 91 | toc 594, hierarchy 30 |
| Die Gartenlaube (1899) | mixed | collection | 624 | 595 | 226 | toc 596, hierarchy 28 |
| Die Gartenlaube (1896) | mixed | collection | 613 | 561 | 77 | toc 561, hierarchy 52 |
| Badisches Sagen-Buch | linked | collection | 600 | 596 | 0 | container 600 |
| Die Gartenlaube (1887) | mixed | collection | 525 | 471 | 0 | toc 472, hierarchy 53 |
| Die Gartenlaube (1888) | mixed | collection | 522 | 470 | 37 | toc 470, hierarchy 52 |
| Die Gartenlaube (1854) | mixed | collection | 491 | 438 | 1 | toc 439, hierarchy 52 |
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
| A | collection of root pages (`structure = linked`) | 433 works, 20,783 parts |
| B | mixed subpages and root pages (`mixed`) | 89 works, 22,629 parts |
| C | work with an oversized page (> oversized_bytes, transcluded scan pages included) | 317 works |
| D | missing root, virtual root (`virtual`) | 46 works, 143 parts |
| E | texts inside a subpage collection reached from a disambiguation or versions page | 26 parts (24 searchable); all searchable subpage texts: 8,097 |
| F | whole-text duplicate subpages (excluded) | 0 pages |
| G | reference works (type `reference`) | 0 works, 0 parts; 218,074 pages excluded by title prefix |
| H | deep nesting (parts at depth ≥ 2, i.e. three levels below the root) | 7 works |
| I | several editions: works with an other-versions template | 49 works; 0 versions pages excluded |

Tree depth of multi-part works (levels below the root): 1: 854, 2: 79, 3: 5, 4: 2.

## 4. Parent rules and order sources (steps 1b, 1c)

| parent rule | pages |
|---|---|
| container | 29,101 |
| own | 26,591 |
| toc | 17,844 |
| hierarchy | 6,803 |

| order source (nodes with ≥ 2 children) | nodes |
|---|---|
| chain | 562 |
| toc | 192 |
| toc-partial | 111 |
| natural | 67 |
| scan | 32 |
| scan-volumes | 9 |

`toc-partial` (implementation choice, see section 13): the TOC lists at least half of the children, which keep its order; the rest follow in natural order. 33 `natural` nodes have a TOC that lists fewer than half.

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

**Orphan trees** (virtual roots): 46, holding 143 parts. Examples: `Kataloge der Jahresausstellungen der Kurfürstl.` (1); `Der Handlungsreisende wie er sein soll` (1); `Berliner Erinnerungen` (1); `Heinrich Heine` (2); `Nachtseiten von London` (1); `Aus dem Fuchsleben` (2); `Topographie der historischen und kunstgeschichtlichen Denkmale in den Bezirken Tepl und Marienbad` (2); `Mein erster Hirsch` (1).

**Oversized pages** (> 300,000 bytes of wikitext including transcluded scan pages): 549. Largest: `Ahrenshoop vor und im Krieg` 4.0 MB; `Weg nach Berlin – Birkenwerder` 2.2 MB; `Kunst und Kultur in Ahrenshoop 1945 bis 1948` 2.1 MB; `Paulys Realencyclopädie der classischen Altertumswissenschaft/Register/Hans Gärtner` 2.0 MB; `Jugend (Zeitschrift)/Inhalt 1896–1910` 1.9 MB; `Jugend (Zeitschrift)/Inhalt 1911–1929` 1.8 MB; `Hans Brass und die Religion, 1934` 1.5 MB; `Paulys Realencyclopädie der classischen Altertumswissenschaft/Register/R` 1.5 MB; `Der Merker/Inhalt` 1.5 MB; `Allgemeine Wiener Musik-Zeitung` 1.5 MB.

**Red links in TOCs** (missing parts): 149 multi-part works have red links, 980 in total; median share of the TOC 6%. Most missing: `Die Gartenlaube (1899)` 226 of 850; `Gesammelte Schriften über Musik und Musiker` 114 of 237; `Die Gartenlaube (1898)` 91 of 715; `Die Gartenlaube (1896)` 77 of 690; `Die Gartenlaube (1888)` 37 of 559; `Illustrirte Zeitung` 35 of 115; `Die Grenzboten (1841/1842)` 27 of 52; `Schwäbische Chronik (Thomas Lirer)` 23 of 24.

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
| validated | 14,434 | 55.4 % | 38,240 | page-levels 9,711, status-template 4,723 |
| proofread | 6,999 | 26.9 % | 8,430 | status-template 4,907, page-levels 2,092 |
| unproofread | 266 | 1.0 % | 156 | status-template 157, page-levels 109 |
| unknown | 4,336 | 16.7 % | 1,334 | none 4,336 |

14 works are proofread by the ≥ 90 % rule. 229 of the 266 unproofread works have no page above level 1 (raw OCR).

**Incomplete**: 2,169 works — template 1,991, missing-parts 166, missing-pages 12.
Works with missing scan pages inside a transcluded range: 17 (77 pages).
1 works transclude scan pages of files the dump has no Page: pages for (counted as no signal, not as missing).
Wikisource's own translations (labelled, kept): 0.

Status values (`bearbeitungsstand`, `status`): `fertig` 50,522, `korrigiert` 9,015, `unvollständig` 2,040, `unkorrigiert` 342, `unvollständig (geplant)` 60, `off` 25, `teilkorrigiert` 4, `korrekturprobleme` 3, `fehler korr.` 1.

**Unknown works** (no signal): 4,336 in the catalog after the junk rules; the junk rules removed 296 (under-300-bytes 296).
Size: median 2,967 bytes, p10 1,061, p90 12,410; multi-part 42.

30 random unknown works:

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
- `Preußisches Obertribunal - Rose` (virtual, 36,925 B): No 47. (Crimin.-S. Nr. 6) Mord. Theilnahme durch Anstiftung und Hülfeleistung. Irrthum in der Person des Getödteten. Ist bei dem Morde ein Seitens des Thäters v
- `Die Schedelsche Weltchronik (deutsch):156` (single, 7,265 B): CLV verso: == Das sechst alter ERffurt die groß vnnd gedechtnußwirdig statt ein hawbt Thu e ringer lannds von den alten Erphesfurt genant hat einen hohen berg d

**Default view** (validated or proofread, complete, not reference): 19,334 works with 35,451 texts; with unknown works shown: 23,638 works (36,784 texts). Works in the default view whose pages are all level 1: 0. Current setting: `show_unknown_by_default = None`.

## 10. Parts table size (WS§5.2)

| parts_cap | works with catalog parts | parts rows | parts table, gzipped | parts_fts, gzipped |
|---|---|---|---|---|
| 500 | 929 | 46,593 | 2.83 MB | 0.78 MB |
| 2,000 | 940 | 54,642 | 3.28 MB | 0.86 MB |
| ∞ (all, reference works included) | 940 | 54,642 | 3.28 MB | 0.86 MB |
| 2,000, compact encoding | 940 | 54,642 | 2.99 MB | 0.86 MB |

Rows count multi-part works only, one row per part plus the root row. 25,095 single-page works would add one row each if stored. Sizes are a SQLite file holding only the table (with its index) at gzip level 9. The compact encoding (not in WS§5.1, measured for the cap decision) stores a part's page_title as the suffix after the work root's title where possible, display_title only when it differs from the last title segment, and no per-part quality_pages.

## 11. Named cases (PLAN.md WP1 acceptance, WS§9)

- `Der Sagenschatz des Königreichs Sachsen`: work, mixed, type collection, 1,001 parts (1,001 texts), 0 missing, order chain, oversized False, quality validated (page-levels), complete True.
- `Kinder- und Haus-Märchen Band 1 (1857)`: work, linked, type collection, 86 parts (86 texts), 0 missing, order chain, oversized False, quality validated (page-levels), complete True.
- `Der Froschkönig oder der eiserne Heinrich (1857)`: part 1 of `Kinder- und Haus-Märchen Band 1 (1857)` (text, display “Der Froschkönig oder der eiserne Heinrich”, rule container, searchable True).
- `Kinder und Hausmärchen`: redirect to `Kinder- und Hausmärchen`, which is excluded (excluded-template).
- `Kinder- und Hausmärchen`: excluded (excluded-template).
- `Die Heimath in der neuen Welt`: work, virtual, type collection, 50 parts (33 texts), 0 missing, order chain, oversized False, quality validated (page-levels), complete True.
- `Käthe`: work, single, type single, 1 parts (0 texts), 0 missing, order –, oversized True, quality validated (page-levels), complete True.
- `Erlkönig`: work, single, type single, 1 parts (0 texts), 0 missing, order –, oversized False, quality validated (status-template), complete True.

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

### A: collections of root pages (433)

1. `Allgemeiner Harz-Berg-Kalender für das Schaltjahr 1924`: linked, collection, 4 parts, 2 missing, order toc, unproofread
    - front: Allgemeiner Harz-Berg-Kalender für das Schaltjahr 1924
    - text: Mahnung (`Mahnung (Ernst von Wildenbruch)`) [container]
    - text: Im Harze [container]
    - text: Der stille See [container]
    - text: Der Vogelfang [container]
1. `Traumgekrönt`: linked, collection, 3 parts, 0 missing, order toc, validated
    - front: Traumgekrönt
    - text: Königslied [container]
    - text: Träumen [container]
    - text: Lieben [container]
1. `Sachsen’s Volkssagen`: linked, collection, 26 parts, 0 missing, order chain, validated
    - front: Sachsen’s Volkssagen
    - text: Das Hufeisen an der Nikolaikirche in Leipzig [container]
    - text: Der Trompeter aus Pirna [container]
    - text: Die Katzenmühle bei Buchholz (`Die Katzenmühle bei Buchholz (Ziehnert)`) [container]
    - text: Die sieben Kapellen bei Höckendorf [container]
    - text: Das Marienbild zu Eicha bei Naunhof (`Das Marienbild zu Eicha bei Naunhof (Ziehnert)`) [container]
    - text: Der Jungfernsprung auf dem Oybin bei Zittau [container]
    - text: Die Irrlichter am Grundtümpel bei Wildenau [container]
    - text: Burggraf Jeschke von Dohna [container]
    - text: Das Goldschiffchen in der Kirche zu Ebersdorf (`Das Goldschiffchen in der Kirche zu Ebersdorf (Ziehnert)`) [container]
    - text: Die Entstehung von Schöneck (`Die Entstehung von Schöneck (Ziehnert)`) [container]
    - text: Der Nonnenstein bei Weissig [container]
    - … 15 more
1. `Historische Zeitschrift`: linked, collection, 1 parts, 0 missing, order single, validated
    - front: Historische Zeitschrift
    - text: Der platonische Staat in seiner Bedeutung für die Folgezeit [container]
1. `Allgemeiner Harz-Berg-Kalender für das Schaltjahr 1932`: linked, collection, 5 parts, 2 missing, order toc, unproofread
    - front: Allgemeiner Harz-Berg-Kalender für das Schaltjahr 1932
    - text: Ein neues Lied auf eine alte Weise [container]
    - text: Wo die Oder rauscht [container]
    - text: Die Lisetante [container]
    - text: Oberharzer Glockeninschriften [container]
    - text: Wie die Bergstadt Clausthal königlichen Besuch hatte [container]
1. `Passionsbilder`: linked, collection, 2 parts, 0 missing, order chain, validated
    - front: Passionsbilder
    - text: Eine löbliche Passion [container]
    - text: Das Passionsspiel in Schwäbisch-Gmünd [container]
1. `Adrian Ludwig Richter, Maler und Radierer. Verzeichnis seines gesamten graphischen Werkes`: linked, collection, 3 parts, 0 missing, order single, proofread
    - front: Adrian Ludwig Richter, Maler und Radierer. Verzeichnis seines gesamten graphischen Werkes
    - volume: II. Künstlerwiedergaben nach dem Meister (`Adrian Ludwig Richter, Maler und Radierer. Verzeichnis seines gesamten graphischen Werkes - Fortsetzung 1`) [toc]
      - volume: Fortsetzung siehe: Adrian Ludwig Richter, Maler und Radierer. Verzeichnis seines gesamten graphischen Werkes - Fortsetzung 2 (`Adrian Ludwig Richter, Maler und Radierer. Verzeichnis seines gesamten graphischen Werkes - Fortsetzung 2`) [toc]
        - text: Fortsetzung siehe: Adrian Ludwig Richter, Maler und Radierer. Verzeichnis seines gesamten graphischen Werkes - Fortsetzung 3 (`Adrian Ludwig Richter, Maler und Radierer. Verzeichnis seines gesamten graphischen Werkes - Fortsetzung 3`) [toc]
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
1. `Das eigensinnige Schwein / Die kluge Minka / Die guten Störche / Der böse Maler / Der Pudel`: linked, collection, 5 parts, 0 missing, order chain, validated
    - toc: Das eigensinnige Schwein / Die kluge Minka / Die guten Störche / Der böse Maler / Der Pudel
    - text: Das eigensinnige Schwein [container]
    - text: Die kluge Minka [container]
    - text: Die guten Störche [container]
    - text: Der böse Maler [container]
    - text: Der Pudel [container]
1. `Kinder- und Haus-Märchen Band 1 (1840)`: linked, collection, 86 parts, 0 missing, order chain, validated
    - front: Kinder- und Haus-Märchen Band 1 (1840)
    - text: Der Froschkönig oder der eiserne Heinrich (`Der Froschkönig oder der eiserne Heinrich (1840)`) [container]
    - text: Katze und Maus in Gesellschaft (`Katze und Maus in Gesellschaft (1840)`) [container]
    - text: Marienkind (`Marienkind (1840)`) [container]
    - text: Märchen von einem, der auszog das Fürchten zu lernen (`Märchen von einem, der auszog das Fürchten zu lernen (1840)`) [container]
    - text: Der Wolf und die sieben jungen Geislein (`Der Wolf und die sieben jungen Geislein (1840)`) [container]
    - text: Der treue Johannes (`Der treue Johannes (1840)`) [container]
    - text: Der gute Handel (`Der gute Handel (1840)`) [container]
    - text: Der wunderliche Spielmann (`Der wunderliche Spielmann (1840)`) [container]
    - text: Die zwölf Brüder (`Die zwölf Brüder (1840)`) [container]
    - text: Das Lumpengesindel (`Das Lumpengesindel (1840)`) [container]
    - text: Brüderchen und Schwesterchen (`Brüderchen und Schwesterchen (1840)`) [container]
    - … 75 more
1. `Beschreibung einer Zurüstung und nützliche Maschinen`: linked, collection, 2 parts, 0 missing, order toc, validated
    - front: Beschreibung einer Zurüstung und nützliche Maschinen
    - text: N. A. J. Kirchhofs Beschreibung einer Zurüstung welche die anziehende Kraft der Erde gegen die Gewitterwolke und die Nützlichkeit der Blitzableiter sinnlich beweiset (`Beschreibung einer Zurüstung, welche die Nützlichkeit der Blitzableiter sinnlich beweiset`) [container]
    - text: Beschreibung verschiedener nützlicher mechanischer Erfindungen aus Herrn Fergusons Vorlesungen übersetzt. (`Beschreibung verschiedener nützlicher mechanischer Erfindungen`) [container]
1. `Meine Kinderjahre`: linked, collection, 18 parts, 0 missing, order chain, validated
    - front: Meine Kinderjahre
    - text: Meine Eltern (`Meine Kinderjahre (Fontane)/Meine Eltern`) [container]
    - text: Gascogne und Cevennen. Französische Vettern. Unsere Ruppiner Tage (`Meine Kinderjahre (Fontane)/Gascogne und Cevennen`) [container]
    - text: Unsere Übersiedelung nach Swinemünde. Ankunft daselbst (`Meine Kinderjahre (Fontane)/Unsere Übersiedelung`) [container]
    - text: Unser Haus, wie wir’s vorfanden (`Meine Kinderjahre (Fontane)/Unser Haus I`) [container]
    - text: Unser Haus, wie’s wurde (`Meine Kinderjahre (Fontane)/Unser Haus II`) [container]
    - text: Die Stadt; ihre Bewohner und ihre Honoratioren (`Meine Kinderjahre (Fontane)/Die Stadt`) [container]
    - text: Die Schönebergs und die Scherenbergs (`Meine Kinderjahre (Fontane)/Die Schönebergs`) [container]
    - text: Die Krauses (`Meine Kinderjahre (Fontane)/Die Krauses`) [container]
    - text: Wie wir in unserem Hause lebten. Sommer- und Herbsttage; Schlacht- und Backfest (`Meine Kinderjahre (Fontane)/Sommer- und Herbsttage`) [container]
    - text: Wie wir in unserem Hause lebten (Fortsetzung) – „Große Gesellschaft“ (`Meine Kinderjahre (Fontane)/Große Gesellschaft`) [container]
    - text: Was wir in Haus und Stadt erlebten (`Meine Kinderjahre (Fontane)/Was wir in Haus und Stadt erlebten`) [container]
    - … 7 more
1. `Drei Essays`: linked, collection, 4 parts, 0 missing, order scan, validated
    - front: Drei Essays
    - text: Der Sozialismus und die Seele des Menschen [container]
    - text: Aus dem Zuchthaus zu Reading [container]
    - text: Aesthetisches Manifest [container]
    - text: Sonett an die Freiheit [container]
1. `Das Grüne Gewölbe: eine Auswahl von Meisterwerken in vier Bänden. Band 2`: linked, collection, 88 parts, 0 missing, order chain, validated
    - front: Das Grüne Gewölbe: eine Auswahl von Meisterwerken in vier Bänden. Band 2
    - text: Trinkgefässe (`Sponsel Grünes Gewölbe Band 2/Der Inhalt des Grünen Gewölbes – Übersicht über den II. Band des Tafelwerkes – Trinkgefässe`) [container]
    - text: Uhren (`Sponsel Grünes Gewölbe Band 2/Der Inhalt des Grünen Gewölbes – Übersicht über den II. Band des Tafelwerkes – Uhren`) [container]
    - text: Figuren (`Sponsel Grünes Gewölbe Band 2/Der Inhalt des Grünen Gewölbes – Übersicht über den II. Band des Tafelwerkes – Figuren`) [container]
    - text: Limoges und andere Emails (`Sponsel Grünes Gewölbe Band 2/Der Inhalt des Grünen Gewölbes – Übersicht über den II. Band des Tafelwerkes – Limoges und andere Emails`) [container]
    - text: Spiegel (`Sponsel Grünes Gewölbe Band 2/Der Inhalt des Grünen Gewölbes – Übersicht über den II. Band des Tafelwerkes – Spiegel`) [container]
    - text: Reliefs (`Sponsel Grünes Gewölbe Band 2/Der Inhalt des Grünen Gewölbes – Übersicht über den II. Band des Tafelwerkes – Reliefs`) [container]
    - text: Prunkschüsseln und Kannen (`Sponsel Grünes Gewölbe Band 2/Der Inhalt des Grünen Gewölbes – Übersicht über den II. Band des Tafelwerkes – Prunkschüsseln und Kannen`) [container]
    - text: Neue Gefässformen (`Sponsel Grünes Gewölbe Band 2/Der Inhalt des Grünen Gewölbes – Übersicht über den II. Band des Tafelwerkes – Neue Gefässformen`) [container]
    - text: Dinglingers goldenes Kaffeezeug (`Sponsel Grünes Gewölbe Band 2/Der Inhalt des Grünen Gewölbes – Übersicht über den II. Band des Tafelwerkes – Dinglingers goldenes Kaffeezeug`) [container]
    - text: Tafel 1 (`Sponsel Grünes Gewölbe Band 2/Tafel 1`) [container]
    - text: Tafel 2 (`Sponsel Grünes Gewölbe Band 2/Tafel 2`) [container]
    - … 77 more
1. `Schatzkästlein des rheinischen Hausfreundes`: linked, collection, 128 parts, 0 missing, order chain, validated
    - front: Schatzkästlein des rheinischen Hausfreundes
    - text: Allgemeine Betrachtung über das Weltgebäude [container]
    - text: Die Erde und die Sonne [container]
    - text: Denkwürdigkeiten aus dem Morgenlande [container]
    - text: Erstes Rechnungsexempel [container]
    - text: Von den Processionsraupen [container]
    - text: Fortsetzung über die Erde und Sonne [container]
    - text: Zwey Gehülfen des Hausfreundes [container]
    - text: Des Adjunkts Standrede im Gemüßgarten seiner Schwiegermutter [container]
    - text: Von den Schlangen [container]
    - text: Geiz und Verschwendung [container]
    - text: Kindesdank und Undank [container]
    - … 117 more
1. `Gedichte (Gustav Schwab)`: linked, collection, 134 parts, 0 missing, order toc, validated
    - front: Gedichte (Gustav Schwab)
    - text: An einen Freund (`An einen Freund (Schwab)`) [container]
    - text: Zueignung (`Zueignung (Schwab)`) [container]
    - text: Allgegenwart der Geliebten [container]
    - text: Liebe im Winter [container]
    - text: An die Wände einer Bergkapelle angeschrieben [container]
    - text: An die Geliebte [container]
    - text: An die Sterne [container]
    - text: Die stille Stadt [container]
    - text: Die Wolke am Sternenhimmel [container]
    - text: Die Gesänge [container]
    - text: Weiblichkeit [container]
    - … 123 more
1. `Gedichte 2 (Geisheim)`: linked, collection, 158 parts, 0 missing, order chain, validated
    - toc: Gedichte 2 (Geisheim)
    - text: Herbst (`Herbst (Geisheim)`) [container]
    - text: Heimkehr (`Heimkehr (Geisheim)`) [container]
    - text: Den lassen wir ein [container]
    - text: Deutscher Geist [container]
    - text: Beim Wein [container]
    - text: Goldne Zeit [container]
    - text: Die alte Hacke [container]
    - text: Der Böttcher [container]
    - text: Trinklied (`Trinklied (Geisheim)`) [container]
    - text: Pater Guardian [container]
    - text: Jung gewohnt, alt gethan [container]
    - … 147 more
1. `Gedichte (Hugo von Hofmannsthal)`: linked, collection, 38 parts, 0 missing, order chain, validated
    - toc: Gedichte (Hugo von Hofmannsthal)
    - text: Vorfrühling (`Vorfrühling (Hofmannsthal)`) [container]
    - text: Erlebnis [container]
    - text: Vor Tag [container]
    - text: Reiselied [container]
    - text: Die beiden [container]
    - text: Lebenslied [container]
    - text: Gute Stunde [container]
    - text: Dein Antlitz... (`Dein Antlitz`) [container]
    - text: Weltgeheimnis [container]
    - text: Ballade des äußeren Lebens [container]
    - text: Nox portentis gravida [container]
    - … 27 more
1. `Galgenlieder`: linked, collection, 71 parts, 0 missing, order toc-partial, validated
    - front: Galgenlieder
    - text: Nein! [container]
    - text: Das Gebet [container]
    - text: Das große Lalulā [container]
    - text: Der Zwölf-Elf [container]
    - text: Das Mondschaf [container]
    - text: Lunovis [container]
    - text: Der Rabe Ralf [container]
    - text: Fisches Nachtgesang [container]
    - text: Galgenbruders Frühlingslied [container]
    - text: Das Hemmed [container]
    - text: Das Problem [container]
    - … 60 more
1. `Kinder-Verwirr-Buch`: linked, collection, 22 parts, 0 missing, order toc-partial, validated
    - toc: Kinder-Verwirr-Buch
    - text: Kleine Lügen [container]
    - text: Babies [container]
    - text: Kind, spiele! [container]
    - text: Schlängelchen [container]
    - text: Nie bist du ohne Nebendir [container]
    - text: Die Guh gibt Milch und stammt aus Leipzig [container]
    - text: Unter Wasser Bläschen machen [container]
    - text: Kinder, spielt mit einer Zwirnsrolle [container]
    - text: Das Hexenkind (`Das Hexenkind (Ringelnatz)`) [container]
    - text: Den Unterschied bei Mann und Frau [container]
    - text: Emanuel Pips [container]
    - … 11 more
1. `Gedichte von Ludwig Uhland (1815)`: linked, collection, 174 parts, 0 missing, order chain, validated
    - front: Gedichte von Ludwig Uhland (1815)
    - text: Des Dichters Abendgang [container]
    - text: An den Tod [container]
    - text: Harfnerlied am Hochzeitmahle [container]
    - text: Der König auf dem Thurme [container]
    - text: Maiklage [container]
    - text: Lied eines Armen [container]
    - chapter: Gesang der Jünglinge [container]
    - text: Lied des Gärtners [container]
    - text: Die Kapelle [container]
    - text: Die sanften Tage (`Die sanften Tage (Gedichte von Ludwig Uhland (1815))`) [container]
    - text: Im Herbste [container]
    - … 163 more
1. `Annette`: linked, collection, 19 parts, 0 missing, order chain, validated
    - front: Annette
    - text: An Annetten [container]
    - text: Ziblis [container]
    - text: Lyde [container]
    - text: Kunst die Spröden zu fangen (`Kunst, die Spröden zu fangen, Erste Erzählung`) [container]
    - text: Kunst die spröden zu fangen (`Kunst, die Spröden zu fangen, Zwote Erzählung`) [container]
    - text: Triumph der Tugend, Erste Erzählung [container]
    - text: Triumph der Tugend, Zwote Erzählung [container]
    - text: Elegie auf den Tod des Bruders meines Freundes [container]
    - text: Ode an Herrn Professor Zachariae [container]
    - text: An den Schlaf [container]
    - text: Pygmalion (`Pygmalion (Goethe)`) [container]
    - … 8 more
1. `Tragödien nebst einem lyrischen Intermezzo`: linked, collection, 7 parts, 0 missing, order natural, validated
    - front: Tragödien nebst einem lyrischen Intermezzo
    - text: Almansor (`Almansor (Heine)`) [container]
    - text: Du sollst mich liebend umschließen [container]
    - text: Freundschaft, Liebe, Stein der Weisen [container]
    - text: Ich glaub’ nicht an den Himmel (`Ich glaub' nicht an den Himmel`) [container]
    - text: Lyrisches Intermezzo [container]
    - text: Schöne, helle, goldne Sterne [container]
    - text: William Ratcliff [container]
1. `Das Buch für Alle`: linked, collection, 2 parts, 0 missing, order natural, validated
    - front: Das Buch für Alle
    - text: A. Noel † [container]
    - text: Die Astronomie als Entlastungszeugin [container]
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
1. `Neue vermischte Gedichte`: linked, collection, 87 parts, 0 missing, order toc-partial, validated
    - front: Neue vermischte Gedichte
    - text: An eine junge Mahlerinn [container]
    - text: Mein Ideal [container]
    - text: Selbstunterhaltung in der Mitternachtsstunde [container]
    - text: Prométheûs [container]
    - text: An den König von Preussen Friedrich Wilhelm den Zweyten [container]
    - text: An meinen ältesten Sohn [container]
    - text: Augustus und ein junger Grieche [container]
    - text: Der zwey und zwanzigste April 1788 [container]
    - text: An Elisen [container]
    - text: Stella an den Geliebten [container]
    - text: An Herzberg [container]
    - … 76 more
1. `Orchideen`: linked, collection, 19 parts, 0 missing, order chain, validated
    - front: Orchideen
    - text: Die schwarze Kugel [container]
    - text: Dr. Lederer [container]
    - text: Das dicke Wasser [container]
    - text: Das Präparat [container]
    - text: Chimäre [container]
    - text: Der Schrecken [container]
    - text: Jörn Uhl [container]
    - text: Das Gehirn [container]
    - text: Eine Suggestion [container]
    - text: G. M. [container]
    - text: Bologneser Tränen [container]
    - … 8 more
1. `Sagen und Märchen aus Ostgalizien und der Bukowina I`: linked, collection, 4 parts, 0 missing, order chain, validated
    - toc: Sagen und Märchen aus Ostgalizien und der Bukowina I
    - text: Die Mühle des Teufels [container]
    - text: Der versunkene Meierhof [container]
    - text: Der Brunnen und die drei Tannen [container]
    - text: Rollerbselein [container]
1. `Deutsche Rundschau`: linked, collection, 2 parts, 2 missing, order toc-partial, validated
    - front: Deutsche Rundschau
    - text: Wilhelmine von Hillern. Eine literarische Studie (`Wilhelmine von Hillern (Goldbaum)`) [container]
    - text: Das Buch Annette [container]

### B: mixed (89)

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
1. `Mitteilungen des Vereins für Chemnitzer Geschichte Dritter und Vierter Band`: mixed, collection, 15 parts, 0 missing, order toc, proofread
    - front: Mitteilungen des Vereins für Chemnitzer Geschichte Dritter und Vierter Band
    - text: Allgemeiner Bericht (`Mitteilungen des Vereins für Chemnitzer Geschichte Dritter und Vierter Band/Allgemeiner Bericht (3)`) [hierarchy]
    - text: Katalog der Bibliothek (`Mitteilungen des Vereins für Chemnitzer Geschichte Dritter und Vierter Band/Katalog der Bibliothek`) [hierarchy]
    - text: Mitglieder-Verzeichnis (`Mitteilungen des Vereins für Chemnitzer Geschichte Dritter und Vierter Band/Mitgliederverzeichniss`) [hierarchy]
    - text: Kassenbericht 1879–81 (`Mitteilungen des Vereins für Chemnitzer Geschichte Dritter und Vierter Band/Kassenbericht 1879–81`) [hierarchy]
    - text: die Chemnitzer Taxordnung der Vorsprechen vom 13. Juni 1412 (`Die Chemnitzer Taxordnung der Vorsprechen vom 13. Juni 1412`) [toc]
    - text: über Chemnitzer Ratswillküren (`Über Chemnitzer Ratswillküren`) [toc]
    - text: Johann Theophilus Lessing und das Chemnitzer Lyceum zu Ende des vorigen und zu Anfang dieses Jahrhunderts (`Johann Theophilus Lessing und das Chemnitzer Lyceum`) [toc]
    - text: J. Th. Lessings Gedicht über die Überschwemmung der Gablenz in deutscher Übersetzung. (`Johann Theophilus Lessings Gedicht über die Überschwemmung der Gablenz`) [toc]
    - text: Allgemeiner Bericht (`Mitteilungen des Vereins für Chemnitzer Geschichte Dritter und Vierter Band/Allgemeiner Bericht (4)`) [hierarchy]
    - text: Kassenbericht 1882–83 (`Mitteilungen des Vereins für Chemnitzer Geschichte Dritter und Vierter Band/Kassenbericht 1882–83`) [hierarchy]
    - text: Adam Andreä und das Chemnitzer Lyceum in der ersten Hälfte des 17. Jahrhunderts [toc]
    - … 4 more
1. `Die Gartenlaube (1855)`: mixed, collection, 377 parts, 0 missing, order toc-partial, validated
    - front: Die Gartenlaube (1855)
    - text: Die weiße Rose [toc]
    - text: Die drei Gesellen [toc]
    - text: Ein Besuch im großen Schuldgefängniß zu London [toc]
    - text: Die Verstopfung [toc]
    - text: Militärische Kleinigkeiten [toc]
    - text: Die russische Armee [toc]
    - text: Ein Dienstritt [toc]
    - text: Deutsche Söldner in England [toc]
    - text: Die Brennstoffe, deren wir uns bedienen [toc]
    - text: Des Adlers Horst [toc]
    - text: Vom Wolkenhimmel [toc]
    - … 366 more
1. `Die Gartenlaube (1861)`: mixed, collection, 356 parts, 0 missing, order toc-partial, validated
    - front: Die Gartenlaube (1861)
    - text: An unsere Leser! (`An unsere Leser! (Die Gartenlaube 1861/1)`) [toc]
    - text: Im hohen Hause [toc]
    - text: Elf Blutzeugen deutscher Freiheit [toc]
    - text: In der Wildniß. Aus den Wäldern von Ecuador (`In der Wildniß`) [toc]
    - text: Die literarischen Häuser Berlins [toc]
    - text: Die Vogelberge [toc]
    - text: Zur Geschichte des Aberglaubens (`Zur Geschichte des Aberglaubens (Die Gartenlaube 1861/1)`) [toc]
    - text: Die Puste-Post [toc]
    - text: Ein Redactionsbureau in der Wildniß [toc]
    - text: Unter schönen deutschen Damen (`Unsere schönen deutschen Damen`) [toc]
    - text: Deutsche Spielhöllen [toc]
    - … 345 more
1. `Die Gartenlaube (1854)`: mixed, collection, 491 parts, 1 missing, order toc-partial, validated
    - front: Die Gartenlaube (1854)
    - text: Katharina (`Katharina (Die Gartenlaube 1854)`) [toc]
    - text: Zwei Verzweifelte [toc]
    - text: Der alte Herr [toc]
    - text: Was die Schaufenster sind etc. [toc]
    - text: Das Herz und sein Herzeleid [toc]
    - text: St. Louis [toc]
    - text: Jagd in Amerika [toc]
    - text: Ein Brief von Ida Pfeiffer aus Californien [toc]
    - text: Höhere Industriestreiche [toc]
    - text: Literarisches (`Literarisches (Die Gartenlaube 1854/1)`) [toc]
    - text: Dreimal denselben Weg [toc]
    - … 480 more
1. `Clausthalischer allgemeiner Harz-Berg-Calender auf das Jahr 1805`: mixed, collection, 14 parts, 2 missing, order toc, unproofread
    - front: Clausthalischer allgemeiner Harz-Berg-Calender auf das Jahr 1805
    - text: Bremens edle Tochter [container]
    - text: Vom Aberglauben [container]
    - text: Die Braut oder das Geld [container]
    - text: Bediente beym Bergamte zu Clausthal (`Clausthalischer allgemeiner Harz-Berg-Calender auf das Jahr 1805/Bediente beym Bergamte zu Clausthal`) [hierarchy]
    - text: Bediente beym Bergamte zu Zellerfeld (`Clausthalischer allgemeiner Harz-Berg-Calender auf das Jahr 1805/Bediente beym Bergamte zu Zellerfeld`) [hierarchy]
    - text: Bediente beym Communion-Unterharzischen Berg-Amte zu Goslar (`Clausthalischer allgemeiner Harz-Berg-Calender auf das Jahr 1805/Bediente beym Communion-Unterharzischen Berg-Amte zu Goslar`) [hierarchy]
    - text: Der Holländer und die Wirthin [container]
    - text: Der Indianer und der Pflanzer [container]
    - text: Bey Punitz fährt der Teufel mit deinem Schneider zum Fenster hinaus [container]
    - text: Wahrer Adel bedarf keinen Adelsbrief [container]
    - text: Muth und Geistesgegenwart leiten den tapfern Mann in Gefahren [container]
    - … 3 more
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
1. `Die Gartenlaube (1856)`: mixed, collection, 362 parts, 1 missing, order toc-partial, validated
    - front: Die Gartenlaube (1856)
    - text: Vorurtheile [toc]
    - text: Kriegsbilder aus der Krim [toc]
    - text: Das Haar im gesunden und kranken Zustande [toc]
    - text: Zur Biographie Goethe’s [toc]
    - text: Eine untergegangene Stadt [toc]
    - text: Brehm [toc]
    - text: Auch eine Trauung [toc]
    - text: Steppenbild (`Steppenbild (Die Gartenlaube 1856/1)`) [toc]
    - text: Tod unter Flötenspiel [toc]
    - text: Das Pfennig-Magazin (`Das Pfennig-Magazin (Die Gartenlaube 1856/1)`) [toc]
    - text: Allgemeiner Briefkasten (`Allgemeiner Briefkasten (Die Gartenlaube 1856/1)`) [toc]
    - … 351 more
1. `Allgemeiner Harz-Berg-Kalender für das Jahr 1931`: mixed, collection, 5 parts, 5 missing, order toc, unproofread
    - front: Allgemeiner Harz-Berg-Kalender für das Jahr 1931
    - text: Dr Holshaer [container]
    - text: Die alten Zechenhäuser im Oberharz [container]
    - text: Aus dem Sagenschatz des Oberharzes (`Aus dem Sagenschatz des Oberharzes (1931)`) [container]
    - text: Verfallener Stollen [container]
    - text: Beamten-Verzeichnis (`Allgemeiner Harz-Berg-Kalender für das Jahr 1931/Beamten-Verzeichnis`) [hierarchy]
1. `Codex diplomaticus Anhaltinus`: mixed, collection, 39 parts, 0 missing, order toc, proofread
    - toc: Codex diplomaticus Anhaltinus
    - text: Codex diplomaticus Anhaltinus. Erster Theil: 936–1212. Dritte Abtheilung: 1170. Nov. 18.–1212. Febr. (`Codex diplomaticus Anhaltinus/Band 1-3`) [hierarchy]
    - text: Codex diplomaticus Anhaltinus. Zweiter Theil. 1212–1300. (`Codex diplomaticus Anhaltinus/Band 2`) [hierarchy]
    - volume: Codex diplomaticus Anhaltinus. Dritter Theil. 1301-1350. (`Codex diplomaticus Anhaltinus/Band 3`) [hierarchy]
      - text: Die Herzöge von Sachsen sowie die Fürsten Albrecht II und Waldemar I von Anhalt versichern der Stadt Berlin, sie nach des Markgrafen Waldemar von Brandenburg Ableben bei ihren Gewohnheiten, Rechten un (`Bestätigung der Rechte Berlins nach dem Ableben des Markgrafen Waldemar von Brandenburg`) [container]
      - text: Erzbischof Otto von Magdeburg schliesst zum Zweck der Eroberung der Mark Brandenburg für den (angeblichen) Markgrafen Waldemar ein Bündniss mit den Herzögen Rudolf II und Otto von Sachsen, sowie mit d (`Bündniss zum Zweck der Eroberung der Mark Brandenburg für den (angeblichen) Markgrafen Waldemar`) [container]
      - text: Markgraf Waldemar von Brandenburg erlässt auf Bitten des Grafen Albrecht II von Anhalt den Bürgern zu Berlin und Cöln eine Abgabe an die dortigen Mühlen und fügt andere Bestätigungen und Vergabungen h (`Der falsche Markgraf Waldemar von Brandenburg erlässt den Bürgern zu Berlin und Cöln eine Abgabe an die dortigen Mühlen`) [container]
      - text: Die Fürsten Albrecht II und Waldemar I von Anhalt stellen den Städten der Mark Brandenburg einen Schutzbrief aus. (`Die Fürsten Albrecht II und Waldemar I von Anhalt stellen den Städten der Mark Brandenburg einen Schutzbrief aus`) [container]
      - text: Erzbischof Otto von Magdeburg verspricht gegen Verpfändung der Altmark dem Markgrafen Waldemar von Brandenburg, den Herzögen Rudolf II und Otto von Sachsen, sowie den Fürsten Albrecht II und Waldemar  (`Erzbischof Otto von Magdeburg verspricht gegen Verpfändung der Altmark dem Markgrafen Waldemar von Brandenburg seine Hilfe zur Eroberung der Mark Brandenburg`) [container]
      - text: Hennig und Franke Ruthenig versprechen dem Markgrafen Waldemar, dem Herzoge Rudolf II von Sachsen, sowie den Fürsten Albrecht II und Waldemar I von Anhalt, ihnen ihr Haus Saarmund offen zu halten (`Hennig und Franke Ruthenig versprechen dem Markgrafen Waldemar, ihnen ihr Haus Saarmund offen zu halten`) [container]
      - text: Kaiser Ludwig belehnt den Fürsten Bernhard III von Anhalt mit dem Fürstenthum Anhalt und mit der Grafschaft Aschanien, wie sie vordem Graf Otto II von Anhalt vom Reiche zu Lehen gehabt. (`Kaiser Ludwig belehnt den Fürsten Bernhard III von Anhalt mit dem Fürstenthum Anhalt und mit der Grafschaft Aschanien`) [container]
      - text: Kaiser Ludwig sendet Ulrich von Bebenburg in das Fürstenthum Anhalt, um den Fürsten Bernhard III in den Besitz der ihm verliehenen Reichslehen zu setzen. (`Kaiser Ludwig sendet Ulrich von Bebenburg in das Fürstenthum Anhalt, um den Fürsten Bernhard III in den Besitz der ihm verliehenen Reichslehen zu setzen`) [container]
    - … 28 more
1. `Das Teutsche Reichs-Archiv`: mixed, collection, 11 parts, 0 missing, order toc, proofread
    - front: Das Teutsche Reichs-Archiv
    - text: Das Teutsche Reichs-Archiv. Band 1 (`Das Teutsche Reichs-Archiv/Band 1`) [hierarchy]
    - text: Die Hoch-Teutsche Übersetzung der güldenen Bull Kaysers Carl des Vierten (`Goldene Bulle (Neuhochdeutsche Übersetzung, 1713)`) [container]
    - text: Römischer Käyserlicher Majestät Abschied, auf dem Reichs-Tag zu Wormbs, Anno 1521. aufgericht. (`Reichstagsabschied von 1521`) [container]
    - text: Decretum, daß die gefreyte Reichsritterschafft in Erkennung der Process soll in acht genommen werden (`Dekret über die Beachtung von Prozessen der Reichsritterschaft von 1591`) [container]
    - text: König Sigismundi Privilegium vor die Stadt Schwäbischen Gemünd [...] / de Anno 1433 (`Kaiser Sigismund: Privileg für Schwäbisch Gmünd, 1433`) [container]
    - text: Käysers Frederici III. vor die Stadt Gemünd über die gemeine Pürsch / genannt Mundat, de Anno 1475 (`Kaiser Friedrich III.: Jagdprivileg für Schwäbisch Gmünd, 1475`) [container]
    - text: Käysers Ferdinandi I. Lehen-Brieff [...] über das Blut zur richten / auch Stock und Galgen / [...] / de Anno 1559. (`Kaiser Ferdinand I.: Lehensbrief über die Blutgerichtsbarkeit, 1559`) [container]
    - text: Käysers Leopoldi Lehen-Brieff vor den Burgermeister der Stadt Gemünd / über das Faßzieher Amt / de Anno 1659 (`Kaiser Leopold I.: Lehensbrief über das Faßzieher-Amt`) [container]
    - text: Käysers Leopoldi Confirmatio Privilegiorum der Stadt Gemünd / de Anno 1659 (`Kaiser Leopold I.: Bestätigung der Privilegien für Schwäbisch Gmünd, 1659`) [container]
    - text: Käysers Caroli IV. Mandatum an die Stadt Hamburg / die an die Grafen zu Hollstein und Schauenburg zu leistende Erb-Huldigung betreffend / de Anno 1377. (`Kaiser Karl IV.: Mandatum an die Stadt Hamburg die zu leistende Erb-Huldigung betreffend, 1377`) [container]
    - text: Kauff-Brieff / krafft dessen die Stadt Gemünd in Schwaben denen Bubenhofischen Vormündern und ihren Mindeln / das Gut klein Siesen vor 17500. fl. verkaufft / de Anno 1575. (`Kauff-Brieff krafft dessen die Stadt Gemünd das Gut klein Siesen verkaufft`) [container]
1. `Die Gartenlaube (1882)`: mixed, collection, 396 parts, 0 missing, order toc-partial, validated
    - front: Die Gartenlaube (1882)
    - text: Der heimliche Gast [toc]
    - text: Zum hundertjährigen Geburtstage Friedrich Fröbel’s (`Zum hundertjährigen Geburtstage Friedrich Fröbel’s (Gartenlaube 1882)`) [toc]
    - text: Mohammed und sein Werk [toc]
    - text: Agnes von Zesyma [toc]
    - text: Das Innere der Albrechtsburg zu Meißen [toc]
    - text: Der alte Glöckner [toc]
    - text: Der Hirscheber [toc]
    - text: Das Gift des Speichels [toc]
    - text: Das neue Wien [toc]
    - text: Adolf Wilbrandt (`Adolf Wilbrandt (Die Gartenlaube 1882/2)`) [toc]
    - text: Der Kienhusar [toc]
    - … 385 more
1. `Die Gartenlaube (1899)`: mixed, collection, 624 parts, 226 missing, order toc-partial, proofread
    - front: Die Gartenlaube (1899)
    - text: Tierleben im Schwarzen Meer [toc]
    - text: Die Sichtbarkeit der Töne und die Abbildung des Gesanges [toc]
    - text: Das Schweigen im Walde [toc]
    - text: Neue Heilbäder [toc]
    - text: Die Leuchten unserer Väter [toc]
    - text: Abendstunde [toc]
    - text: Mein Töchterlein [toc]
    - text: Fräulein Johanne [toc]
    - text: Die „Frau von Auvernier“ [toc]
    - text: Die Wahrheit und die Legende über die Pariser Bastille [toc]
    - text: Ein Seminar für allgemeine Krankenpflege [toc]
    - … 613 more
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
1. `Die Gartenlaube (1892)`: mixed, collection, 422 parts, 0 missing, order toc-partial, proofread
    - front: Die Gartenlaube (1892)
    - text: Die neue Zeit (`Die neue Zeit (Proelß)`) [toc]
    - text: Prosit Neujahr! (`Prosit Neujahr! (Die Gartenlaube 1892/1)`) [toc]
    - text: Der Tod der Kaiserin Agrippina [toc]
    - text: Der Zeitgeist im Hausstande [toc]
    - text: Venetianisches Ständchen [toc]
    - text: Aus den Werkstätten des Lichts [toc]
    - text: Neunzig Jahre Männermode [toc]
    - text: Unschuldig verurtheilt! [toc]
    - text: Dom Pedro [toc]
    - text: Die Schlacht auf den katalaunischen Feldern [toc]
    - text: Seine Excellenz [toc]
    - … 411 more
1. `Neue Thalia`: mixed, collection, 71 parts, 0 missing, order chain, validated
    - front: Neue Thalia
    - volume: Erster Band (`Neue Thalia/Erster Band`) [hierarchy]
      - text: Die Zerstörung von Troja [container]
      - text: Ogier von Dänemark. (`Ogier von Dänemark`) [container]
      - text: Ueber den Grund des Vergnügens an tragischen Gegenständen. (`Ueber den Grund des Vergnügens an tragischen Gegenständen`) [container]
      - text: Erinnerung an die Schweitz. (`Erinnerung an die Schweitz`) [container]
      - text: Dido [container]
      - text: Der erste Mai [container]
      - text: Ueber die tragische Kunst. (`Ueber die tragische Kunst`) [container]
      - text: Gustav Adolph von Schweden [container]
      - text: Der Rheinfall [container]
      - text: Didos Tod [container]
    - … 60 more
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
1. `Die Gartenlaube (1880)`: mixed, collection, 387 parts, 0 missing, order toc-partial, validated
    - front: Die Gartenlaube (1880)
    - text: Ledige Kinder [toc]
    - text: Das Denkmal der Königin Luise für Berlin [toc]
    - text: Die Spielwuth in San Francisco [toc]
    - text: Geborgtes Sonnenlicht [toc]
    - text: Die Cremoneser Geige [toc]
    - text: König Friedrich Wilhelm der Erste und die Akademie der Wissenschaften in Berlin [toc]
    - text: Ein deutscher Waldbaum der Zukunft [toc]
    - text: Er ist todt [toc]
    - text: Amerikanischer Arbeiterverein für deutsche Einwanderer [toc]
    - text: In Deutschland verloren (`In Deutschland verloren (Die Gartenlaube 1880/1)`) [toc]
    - text: Aus der Schülerzeit [toc]
    - … 376 more
1. `Die Gartenlaube (1853)`: mixed, collection, 384 parts, 0 missing, order toc-partial, validated
    - front: Die Gartenlaube (1853)
    - text: An unsere Freunde und Leser! (`An unsere Freunde und Leser (Gartenlaube 1853)`) [toc]
    - text: Ein Mutterherz [toc]
    - text: Der Deutsche in Amerika [toc]
    - text: Luftschifffahrt [toc]
    - text: Ein neues musikalisches Instrument [toc]
    - text: Der Reichthum der Vereinigten Staaten [toc]
    - text: Wie man Universalerbe wird [toc]
    - text: Montenegro und die Montenegriner [toc]
    - text: Ein großer Mann im niedern Stande [toc]
    - text: Ein Märchen und die Natur [toc]
    - text: Eine alte Jungfer [toc]
    - … 373 more
1. `Die Gartenlaube (1870)`: mixed, collection, 409 parts, 0 missing, order toc-partial, validated
    - front: Die Gartenlaube (1870)
    - text: Doctor Reinhard [toc]
    - text: An mein Vaterland [toc]
    - text: Um eines Vogels willen [toc]
    - text: Schulkindkrankheiten oder Schulkrankheiten? [toc]
    - text: Mosigkau und Adelaide [toc]
    - text: Wie man Deutsche in Italien behandelt [toc]
    - text: Der Student auf Ferien [toc]
    - text: Vom „alten Fritz“ in Westphalen [toc]
    - text: Ein deutscher Prinz in Amerika [toc]
    - text: Die Eisenschmieden im Stubaithale [toc]
    - text: Pauline Lucca [toc]
    - … 398 more
1. `Kalender für den Kreis Niederbarnim`: mixed, collection, 3 parts, 0 missing, order toc, proofread
    - toc: Kalender für den Kreis Niederbarnim
    - text: Inhaltsverzeichnis (`Kalender für den Kreis Niederbarnim/Inhaltsverzeichnis`) [hierarchy]
    - text: Die Industriebahn Tegel–Friedrichsfelde und Hafen Tegel [container]
    - text: Als der Heiden Götzenbilder ragten [container]
1. `Die Gartenlaube (1889)`: mixed, collection, 445 parts, 0 missing, order toc-partial, validated
    - front: Die Gartenlaube (1889)
    - text: Lore von Tollen [toc]
    - text: Die Wiege und das Grab der Hohenstaufen [toc]
    - text: Die Vermählung der Todten [toc]
    - text: Das nervöse Herz [toc]
    - text: Der Eissport [toc]
    - text: Wohnungsnoth der Arbeiterinnen [toc]
    - text: Gefangennahme Thusneldas durch Germanicus [toc]
    - text: Ein Gnadengesuch [toc]
    - text: Amor und Psyche (`Amor und Psyche (Die Gartenlaube 1889/1)`) [toc]
    - text: Portrait Kaiser Wilhelms II. (`Porträt Kaiser Wilhelms II.`) [toc]
    - text: Die schwarze Kunst des 19. Jahrhunderts [toc]
    - … 434 more
1. `Der Dienst des Pfarrers`: mixed, collection, 45 parts, 1 missing, order toc, validated
    - toc: Der Dienst des Pfarrers
    - text: Der Dienst Jesu (`Der Dienst des Pfarrers/Der Dienst Jesu`) [container]
    - text: Der Dienst der Apostel (`Der Dienst des Pfarrers/Der Dienst der Apostel`) [container]
    - text: Der Dienst des Pfarrers und seine Vorbedingungen (`Der Dienst des Pfarrers/Der Dienst des Pfarrers und seine Vorbedingungen`) [container]
    - text: Die Vorbereitung zur Predigt (`Der Dienst des Pfarrers/Die Vorbereitung zur Predigt`) [container]
    - text: Die Art der Predigt (`Der Dienst des Pfarrers/Die Art der Predigt`) [container]
    - text: Die Kasualrede (`Der Dienst des Pfarrers/Die Kasualrede`) [container]
    - text: Das liturgische Handeln (`Der Dienst des Pfarrers/Das liturgische Handeln`) [container]
    - text: Die Unterweisung der Jugend (`Der Dienst des Pfarrers/Die Unterweisung der Jugend`) [container]
    - text: Die Seelsorge (`Der Dienst des Pfarrers/Die Seelsorge`) [container]
    - text: Die peripherischen Arbeiten des Pfarramtes (`Der Dienst des Pfarrers/Die peripherischen Arbeiten des Pfarramtes`) [container]
    - text: Das außeramtliche (Privat-)Leben des Geistlichen (`Der Dienst des Pfarrers/Das außeramtliche (Privat-)Leben des Geistlichen`) [container]
    - … 34 more
1. `Die Gartenlaube (1876)`: mixed, collection, 405 parts, 0 missing, order toc-partial, validated
    - front: Die Gartenlaube (1876)
    - text: Im Hause des Commerzienrathes [toc]
    - text: Louise [toc]
    - text: Um eines Knopfes Dicke [toc]
    - text: Das rothe Quartal [toc]
    - text: Ein entlaufener Lehrling [toc]
    - text: Der Spiritismus, eine geistige Verirrung unserer Zeit [toc]
    - text: Das Bremerhavener Unglück [toc]
    - text: Schutz den Krähen [toc]
    - text: Waisenkinder auf der Haide [toc]
    - text: Aus dem jüdischen Familienleben [toc]
    - text: Auf der Spanischen Treppe [toc]
    - … 394 more
1. `Wendische Sagen, Märchen und abergläubische Gebräuche`: mixed, book, 24 parts, 0 missing, order scan-volumes, validated
    - front: Wendische Sagen, Märchen und abergläubische Gebräuche
    - text: [Die Ludken bei Tzschelln] (`Die Ludken bei Tzschelln`) [container]
    - text: Die kluge Tochter des Bauers [container]
    - chapter: Kapitel I (`Wendische Sagen, Märchen und abergläubische Gebräuche/Kapitel I`) [container]
    - chapter: Kapitel II (`Wendische Sagen, Märchen und abergläubische Gebräuche/Kapitel II`) [container]
    - chapter: Kapitel III (`Wendische Sagen, Märchen und abergläubische Gebräuche/Kapitel III`) [container]
    - chapter: V. (`Wendische Sagen, Märchen und abergläubische Gebräuche/Kapitel V`) [container]
    - chapter: VI. (`Wendische Sagen, Märchen und abergläubische Gebräuche/Kapitel VI`) [container]
    - chapter: VII. (`Wendische Sagen, Märchen und abergläubische Gebräuche/Kapitel VII`) [container]
    - chapter: VIII. (`Wendische Sagen, Märchen und abergläubische Gebräuche/Kapitel VIII`) [container]
    - chapter: IX. (`Wendische Sagen, Märchen und abergläubische Gebräuche/Kapitel IX`) [container]
    - chapter: X. (`Wendische Sagen, Märchen und abergläubische Gebräuche/Kapitel X`) [container]
    - … 13 more
1. `Die Gartenlaube (1860)`: mixed, collection, 381 parts, 0 missing, order toc-partial, validated
    - front: Die Gartenlaube (1860)
    - text: Vorwärts (`Vorwärts (Die Gartenlaube 1860)`) [toc]
    - text: Die Huberbäuerin [toc]
    - text: Die neueste Nordpolfahrt des Capitain M’Clintock [toc]
    - text: Das Nervensystem. Unorganische und organische Körper; Organismen mit Nerven [toc]
    - text: Magier und Geister in Berlin [toc]
    - text: Luther am Sarge seines Töchterleins [toc]
    - text: Der alte wandernde Spielmann [toc]
    - text: Eine naturwissenschaftliche Reise in Panama [toc]
    - text: Schiller-Lotterie [toc]
    - text: Aus dem Gedenkbuche der Gartenlaube (`Aus dem Gedenkbuche der Gartenlaube (Die Gartenlaube 1860/2)`) [toc]
    - text: Isolde (`Isolde (Novelle)`) [toc]
    - … 370 more
1. `Die Gartenlaube (1898)`: mixed, collection, 624 parts, 91 missing, order toc-partial, proofread
    - front: Die Gartenlaube (1898)
    - text: Allerlei von Reineke (`Allerlei von Reinecke`) [toc]
    - text: Das bayrische Bier auf Reisen [toc]
    - text: Eine neue Art, Bratenreste zu verwenden [toc]
    - volume: Antons Erben [toc]
      - chapter: Teil I (`Antons Erben/I`) [hierarchy]
      - chapter: Teil II (`Antons Erben/II`) [hierarchy]
    - text: Das Acetylengas [toc]
    - text: Ein Sommernachtstraum (`Ein Sommernachtstraum (Die Gartenlaube 1898)`) [toc]
    - text: Erkältung (`Erkältung (Die Gartenlaube 1898/1)`) [toc]
    - text: Wie lehrt man die Vögel „auf Kommando“ singen? [toc]
    - text: Ein Tag an Bord eines Eisbrechers [toc]
    - … 613 more
1. `Biographisches Jahrbuch und Deutscher Nekrolog`: mixed, collection, 3 parts, 0 missing, order toc, validated
    - front: Biographisches Jahrbuch und Deutscher Nekrolog
    - text: Autorenverzeichnis (`Biographisches Jahrbuch und Deutscher Nekrolog/Autoren`) [hierarchy]
    - text: Inhaltsverzeichnis nach Bänden (`Biographisches Jahrbuch und Deutscher Nekrolog/Inhalt`) [hierarchy]
    - text: [Ludwig Speidel, Schriftsteller] (`Ludwig Speidel, Schriftsteller`) [container]
1. `Jugend (Zeitschrift)`: mixed, collection, 6 parts, 0 missing, order toc-partial, proofread
    - front: Jugend (Zeitschrift)
    - text: 01-15. Jg, 1896–1910 (`Jugend (Zeitschrift)/Inhalt 1896–1910`) [hierarchy]
    - text: 16-34. Jg, 1911–1929 (`Jugend (Zeitschrift)/Inhalt 1911–1929`) [hierarchy]
    - text: 35-45. Jg, 1930–1940 (`Jugend (Zeitschrift)/Inhalt 1930–1940`) [hierarchy]
    - text: Der Schuft [container]
    - text: Elegie bei einer Tasse Mocca [container]
    - text: Gespräch einer Hausschnecke mit sich selbst [container]

### D: virtual roots (46)

1. `Archiv für die zeichnenden Künste`: virtual, collection, 1 parts, 0 missing, order single, proofread
    - text: Notizen über Hieronymus van Aeken, genannt Bosch, und Alart Du Hameel (`Archiv für die zeichnenden Künste/Jahrgang 7-8/Notizen über Hieronymus van Aeken, genannt Bosch, und Alart Du Hameel`)
1. `Nachtseiten von London`: virtual, collection, 1 parts, 0 missing, order single, validated
    - text: Nachtseiten von London/2
1. `Leipzigs Industrien`: virtual, collection, 2 parts, 0 missing, order natural, validated
    - text: Leipzigs Industrien/Nr. 1. Aetherische Oele und Essenzen
    - text: Leipzigs Industrien/Nr. 2. Ein überwundenes Vorurtheil in einer Bagatellsache [hierarchy]
1. `Zeitschriften (Veterinärmedizin)`: virtual, collection, 1 parts, 0 missing, order single, unknown
    - text: The veterinarian (`Zeitschriften (Veterinärmedizin)/fremdsprachige/The veterinarian : a monthly journal of veterinary science`)
1. `Gallerie berühmter Firmengründer`: virtual, collection, 1 parts, 0 missing, order single, validated
    - text: Gallerie berühmter Firmengründer 1. Ein Leipziger Buchhändler (`Gallerie berühmter Firmengründer/1. Ein Leipziger Buchhändler`)
1. `Source-Provider`: virtual, collection, 1 parts, 0 missing, order single, unknown
    - text: Landesarchiv Baden-Württemberg (`Source-Provider/Landesarchiv Baden-Württemberg`)
1. `Topographie der historischen und kunstgeschichtlichen Denkmale in den Bezirken Tepl und Marienbad`: virtual, collection, 2 parts, 0 missing, order scan, proofread
    - text: Maiersgrün (`Topographie der historischen und kunstgeschichtlichen Denkmale in den Bezirken Tepl und Marienbad/Maiersgrün`)
    - text: Tepl, Pfarrkirche zu S. Ägidius (`Topographie der historischen und kunstgeschichtlichen Denkmale in den Bezirken Tepl und Marienbad/Tepl, Pfarrkirche zu S. Ägidius`) [hierarchy]
1. `Neuer Görlitzer Anzeiger`: virtual, collection, 2 parts, 0 missing, order natural, validated
    - text: Heimatbilder #38: Berzdorf auf dem Eigen I. (`Neuer Görlitzer Anzeiger/Berzdorf auf dem Eigen I`)
    - text: Heimatbilder #39: Berzdorf auf dem Eigen II. (`Neuer Görlitzer Anzeiger/Berzdorf auf dem Eigen II`) [hierarchy]
1. `Erinnerungen aus meinem Leben (Friedrich Hecker)`: virtual, collection, 1 parts, 0 missing, order single, validated
    - text: Der Hexenmeister der Prairie (`Erinnerungen aus meinem Leben (Friedrich Hecker)/2`)
1. `Flugblätter der Weissen Rose`: virtual, book, 6 parts, 0 missing, order chain, validated
    - chapter: I (`Flugblätter der Weissen Rose/I`)
    - chapter: II (`Flugblätter der Weissen Rose/II`) [hierarchy]
    - chapter: III (`Flugblätter der Weissen Rose/III`) [hierarchy]
    - chapter: IV (`Flugblätter der Weissen Rose/IV`) [hierarchy]
    - chapter: V (`Flugblätter der Weissen Rose/V`) [hierarchy]
    - chapter: VI (`Flugblätter der Weissen Rose/VI`) [hierarchy]
1. `Ausgewählte Abhandlungen des Bischofs Aphraates`: virtual, collection, 8 parts, 0 missing, order chain, validated
    - text: Abhandlung über den Glauben (`Ausgewählte Abhandlungen des Bischofs Aphraates/Abhandlung über den Glauben`)
    - text: Abhandlung über die Liebe (`Ausgewählte Abhandlungen des Bischofs Aphraates/Abhandlung über die Liebe`) [hierarchy]
    - text: Abhandlung über das Fasten (`Ausgewählte Abhandlungen des Bischofs Aphraates/Abhandlung über das Fasten`) [hierarchy]
    - text: Abhandlung über das Gebet (`Ausgewählte Abhandlungen des Bischofs Aphraates/Abhandlung über das Gebet`) [hierarchy]
    - text: Abhandlung über die Buße (`Ausgewählte Abhandlungen des Bischofs Aphraates/Abhandlung über die Buße`) [hierarchy]
    - text: Abhandlung über das Pascha (`Ausgewählte Abhandlungen des Bischofs Aphraates/Abhandlung über das Pascha`) [hierarchy]
    - text: Abhandlung über die Jungfräulichkeit und Heiligkeit gegen die Juden (`Ausgewählte Abhandlungen des Bischofs Aphraates/Abhandlung über die Jungfräulichkeit und Heiligkeit gegen die Juden`) [hierarchy]
    - text: Abhandlung über den Tod und die letzten Zeiten (`Ausgewählte Abhandlungen des Bischofs Aphraates/Abhandlung über den Tod und die letzten Zeiten`) [hierarchy]
1. `Aus deutschen Lustschlössern`: virtual, collection, 2 parts, 0 missing, order natural, validated
    - text: Aus deutschen Lustschlössern/1. Auf dem Winterkasten
    - text: Aus deutschen Lustschlössern 2. Der vorletzte der fränkischen Hohenzollern (`Aus deutschen Lustschlössern/2. Der vorletzte der fränkischen Hohenzollern`) [hierarchy]
1. `In Friedrich Rückert’s Haus`: virtual, collection, 2 parts, 0 missing, order natural, validated
    - text: In Friedrich Rückert’s Haus/1
    - text: In Friedrich Rückert’s Haus/2 [hierarchy]
1. `Berliner Erinnerungen`: virtual, collection, 1 parts, 0 missing, order single, validated
    - text: Berliner Erinnerungen/1. Spontini
1. `Die Heimath in der neuen Welt`: virtual, collection, 50 parts, 0 missing, order chain, validated
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
1. `Fundamento de Esperanto`: virtual, collection, 1 parts, 0 missing, order single, validated
    - text: Grammatik (`Fundamento de Esperanto/Grammatik`)
1. `Kataloge der Jahresausstellungen der Kurfürstl.`: virtual, collection, 1 parts, 0 missing, order single, unknown
    - text: Kataloge der Jahresausstellungen der Kurfürstl./Königl. Sächsischen Akademie der Künste
1. `Zeitschriften (Illustrierte)`: virtual, collection, 1 parts, 0 missing, order single, unknown
    - text: The St. James' magazine (`Zeitschriften (Illustrierte)/fremdsprachige/The St. James' magazine`)
1. `Der Wille zum Stil`: virtual, collection, 2 parts, 0 missing, order natural, proofread
    - text: „Der Wille zum Stil“ (`Der Wille zum Stil/1`)
    - text: „Der Wille zum Stil“ (Neugestaltung von Leben, Kunst und Technik) (`Der Wille zum Stil/2`) [hierarchy]
1. `Heinrich Heine`: virtual, collection, 2 parts, 0 missing, order natural, unknown
    - text: Gesamtverzeichnis der Prosanotizen (`Heinrich Heine/DHA/Gesamtverzeichnis der Prosanotizen`)
    - text: Werkverzeichnis (`Heinrich Heine/DHA/Werkverzeichnis`) [hierarchy]
1. `Handeln die Thiere nur aus Instinct oder mit Ueberlegung?`: virtual, book, 1 parts, 0 missing, order single, validated
    - chapter: 1 (`Handeln die Thiere nur aus Instinct oder mit Ueberlegung?/1`)
1. `Zeitschriften (Kunst)`: virtual, collection, 5 parts, 0 missing, order natural, unknown
    - text: La belle assemblée (`Zeitschriften (Kunst)/fremdsprachige/La belle assemblée : being Bell's court and fashionable magazine, addressed particulary to the ladies`)
    - text: The ladies' cabinet of fashion, music & romance (`Zeitschriften (Kunst)/fremdsprachige/The ladies' cabinet of fashion, music & romance`) [hierarchy]
    - text: The ladies' companion and monthly magazine (`Zeitschriften (Kunst)/fremdsprachige/The ladies' companion and monthly magazine`) [hierarchy]
    - text: The lady's magazine or entertaining companion for the fair sex : (`Zeitschriften (Kunst)/fremdsprachige/The lady's magazine or entertaining companion for the fair sex`) [hierarchy]
    - text: The new monthly assemblée (`Zeitschriften (Kunst)/fremdsprachige/The new monthly assemblée`) [hierarchy]
1. `Agrumi`: virtual, collection, 4 parts, 0 missing, order scan, validated
    - text: Der Geliebten (`Agrumi/Der Geliebten`)
    - text: Nachtgesang I (`Agrumi/Nachtgesang I`) [hierarchy]
    - text: Die launenhafte Geliebte (`Agrumi/Die launenhafte Geliebte`) [hierarchy]
    - chapter: Gesang zur Tarantella (`Agrumi/Gesang zur Tarantella`) [hierarchy]
1. `In und um Tirols Oberammergau`: virtual, collection, 2 parts, 0 missing, order natural, validated
    - text: In und um Tirols Oberammergau/1.
    - text: „Auf den Bergen die Burgen“ und Notburga’s Ruhm und Preis (`In und um Tirols Oberammergau/2.`) [hierarchy]
1. `Zwanglose Blätter(1883/9)`: virtual, collection, 1 parts, 0 missing, order single, validated
    - text: Briefkasten (`Zwanglose Blätter(1883/9)/Briefkasten`)
1. `Aus den Arbeitssälen des Kunsthandwerks`: virtual, collection, 2 parts, 0 missing, order natural, validated
    - text: Aus den Arbeitssälen des Kunsthandwerks/Die Goldschmiedekunst
    - text: Aus den Arbeitssälen des Kunsthandwerks/Stil und Mode [hierarchy]
1. `Seegeschichten`: virtual, collection, 1 parts, 0 missing, order single, validated
    - text: Seegeschichten 2. Die Springstange (`Seegeschichten/2. Die Springstange`)
1. `Zedler`: virtual, collection, 1 parts, 0 missing, order single, unknown
    - text: Quellen (`Zedler/Quellen`)
1. `Hoff Budde`: virtual, collection, 1 parts, 0 missing, order single, unknown
    - text: Nr. 271 (`Hoff Budde/Test/Nr. 271`)
1. `Geschichtsauffassung und Geschichtsschreibung`: virtual, book, 7 parts, 0 missing, order natural, validated
    - chapter: I. Das Alte und die neuen Probleme (`Geschichtsauffassung und Geschichtsschreibung/I. Das Alte und die neuen Probleme`)
    - chapter: II. Vorläufer und Vorbilder (`Geschichtsauffassung und Geschichtsschreibung/II. Vorläufer und Vorbilder`) [hierarchy]
    - chapter: III. Scholastischer Humanismus (`Geschichtsauffassung und Geschichtsschreibung/III. Scholastischer Humanismus`) [hierarchy]
    - chapter: IV. Humanistische Weltchroniken (`Geschichtsauffassung und Geschichtsschreibung/IV. Humanistische Weltchroniken`) [hierarchy]
    - chapter: V. Entdecker und Kritiker (`Geschichtsauffassung und Geschichtsschreibung/V. Entdecker und Kritiker`) [hierarchy]
    - chapter: VI. Germania illustrata (`Geschichtsauffassung und Geschichtsschreibung/VI. Germania illustrata`) [hierarchy]
    - chapter: VII. Die humanistische Hofgeschichtschreibung unter Kaiser Maximilian (`Geschichtsauffassung und Geschichtsschreibung/VII. Die humanistische Hofgeschichtschreibung unter Kaiser Maximilian`) [hierarchy]

### E: subpage texts reached from a disambiguation or versions page (26)

1. text “Von einem, der auszog, das Fürchten zu lernen” (`Anmerkungen zu den Kinder- und Hausmärchen der Brüder Grimm I/Von einem der auszog, das Fürchten zu lernen`), part 4 of `Anmerkungen zu den Kinder- und Hausmärchen der Brüder Grimm I` (collection, 66 parts, 66 texts)
1. text “Die zwölf Brüder” (`Anmerkungen zu den Kinder- und Hausmärchen der Brüder Grimm I/Die zwölf Brüder`), part 11 of `Anmerkungen zu den Kinder- und Hausmärchen der Brüder Grimm I` (collection, 66 parts, 66 texts)
1. text “Hans Dumm” (`Anmerkungen zu den Kinder- und Hausmärchen der Brüder Grimm I/Hans Dumm`), part 60 of `Anmerkungen zu den Kinder- und Hausmärchen der Brüder Grimm I` (collection, 66 parts, 66 texts)
1. text “Adam” (`Christliche Symbolik/Adam`), part 12 of `Christliche Symbolik` (collection, 636 parts, 633 texts)
1. text “Adler” (`Christliche Symbolik/Adler`), part 13 of `Christliche Symbolik` (collection, 636 parts, 633 texts)
1. text “Ahasver” (`Christliche Symbolik/Ahasver`), part 20 of `Christliche Symbolik` (collection, 636 parts, 633 texts)
1. text “Wie Kinder Schlachtens miteinander gespielt haben” (`Anmerkungen zu den Kinder- und Hausmärchen der Brüder Grimm I/Wie Kinder Schlachtens miteinander gespielt haben`), part 25 of `Anmerkungen zu den Kinder- und Hausmärchen der Brüder Grimm I` (collection, 66 parts, 66 texts)
1. text “Marienkind” (`Anmerkungen zu den Kinder- und Hausmärchen der Brüder Grimm I/Marienkind`), part 3 of `Anmerkungen zu den Kinder- und Hausmärchen der Brüder Grimm I` (collection, 66 parts, 66 texts)
1. text “A” (`Fliegende Blätter/Inhalt Band 1`), part 147 of `Fliegende Blätter` (collection, 241 parts, 231 texts)
1. text “Der Tod und der Gänshirt” (`Anmerkungen zu den Kinder- und Hausmärchen der Brüder Grimm I/Der Tod und der Gänshirt`), part 31 of `Anmerkungen zu den Kinder- und Hausmärchen der Brüder Grimm I` (collection, 66 parts, 66 texts)
1. text “Der Wolf und die sieben jungen Geißlein” (`Anmerkungen zu den Kinder- und Hausmärchen der Brüder Grimm I/Der Wolf und die sieben jungen Geißlein`), part 5 of `Anmerkungen zu den Kinder- und Hausmärchen der Brüder Grimm I` (collection, 66 parts, 66 texts)
1. text “Katze und Maus in Gesellschaft” (`Anmerkungen zu den Kinder- und Hausmärchen der Brüder Grimm I/Katze und Maus in Gesellschaft`), part 2 of `Anmerkungen zu den Kinder- und Hausmärchen der Brüder Grimm I` (collection, 66 parts, 66 texts)
1. chapter “1” (`Handeln die Thiere nur aus Instinct oder mit Ueberlegung?/1`), part 0 of `Handeln die Thiere nur aus Instinct oder mit Ueberlegung?` (book, 1 parts, 0 texts)
1. text “Der gestiefelte Kater” (`Anmerkungen zu den Kinder- und Hausmärchen der Brüder Grimm I/Der gestiefelte Kater`), part 38 of `Anmerkungen zu den Kinder- und Hausmärchen der Brüder Grimm I` (collection, 66 parts, 66 texts)
1. text “Von der Nachtigall und der Blindschleiche” (`Anmerkungen zu den Kinder- und Hausmärchen der Brüder Grimm I/Von der Nachtigall und der Blindschleiche`), part 7 of `Anmerkungen zu den Kinder- und Hausmärchen der Brüder Grimm I` (collection, 66 parts, 66 texts)
1. text “Abel” (`Christliche Symbolik/Abel`), part 5 of `Christliche Symbolik` (collection, 636 parts, 633 texts)
1. text “Der Froschkönig oder der eiserne Heinrich” (`Anmerkungen zu den Kinder- und Hausmärchen der Brüder Grimm I/Der Froschkönig oder der eiserne Heinrich`), part 1 of `Anmerkungen zu den Kinder- und Hausmärchen der Brüder Grimm I` (collection, 66 parts, 66 texts)
1. text “Der getreue Johannes” (`Anmerkungen zu den Kinder- und Hausmärchen der Brüder Grimm I/Der getreue Johannes`), part 6 of `Anmerkungen zu den Kinder- und Hausmärchen der Brüder Grimm I` (collection, 66 parts, 66 texts)
1. volume “Erster Band” (`Thalia/Erster Band`), part 1 of `Thalia` (collection, 68 parts, 64 texts)
1. text “Der wunderliche Spielmann” (`Anmerkungen zu den Kinder- und Hausmärchen der Brüder Grimm I/Der wunderliche Spielmann`), part 9 of `Anmerkungen zu den Kinder- und Hausmärchen der Brüder Grimm I` (collection, 66 parts, 66 texts)
1. text “Anmerkungen zu den einzelnen Märchen” (`Kinder- und Haus-Märchen Band 3 (1856)/Anmerkungen`), part 6 of `Kinder- und Haus-Märchen Band 3 (1856)` (collection, 11 parts, 10 texts)
1. text “Auferstehung” (`Christliche Symbolik/Auferstehung`), part 41 of `Christliche Symbolik` (collection, 636 parts, 633 texts)
1. text “Der gute Handel” (`Anmerkungen zu den Kinder- und Hausmärchen der Brüder Grimm I/Der gute Handel`), part 8 of `Anmerkungen zu den Kinder- und Hausmärchen der Brüder Grimm I` (collection, 66 parts, 66 texts)
1. text “Hirsch” (`Christliche Symbolik/Hirsch`), part 233 of `Christliche Symbolik` (collection, 636 parts, 633 texts)
1. text “Pan” (`Christliche Symbolik/Pan`), part 385 of `Christliche Symbolik` (collection, 636 parts, 633 texts)
1. text “Die Hand mit dem Messer” (`Anmerkungen zu den Kinder- und Hausmärchen der Brüder Grimm I/Die Hand mit dem Messer`), part 10 of `Anmerkungen zu den Kinder- und Hausmärchen der Brüder Grimm I` (collection, 66 parts, 66 texts)

### all searchable subpage texts (chapter titles that the label patterns miss show up here) (8,097)

1. text “Karl Klee” (`Paulys Realencyclopädie der classischen Altertumswissenschaft/Register/Karl Klee`), part 735 of `Paulys Realencyclopädie der classischen Altertumswissenschaft` (collection, 1,325 parts, 1,297 texts)
1. text “Wie herr Gotfridt Wernher freiherr von Zimbern zu marggraf Christoffen von Baden und nachgends zu herzog Ulrichen von Würtemberg an hove kommen und bei denen etliche jar zue hoff bliben.” (`Zimmerische Chronik/Band 2/Kapitel 41`), part 120 of `Zimmerische Chronik` (collection, 159 parts, 149 texts)
1. text “94 Bei der Leiche eines Mordbrenners, der enthauptet wurde” (`Melpomene/Band 1/094 Bei der Leiche eines Mordbrenners, der enthauptet wurde`), part 115 of `Melpomene` (collection, 223 parts, 221 texts)
1. text “Inhaltsverzeichnis” (`Musikblätter des Anbruch/Inhaltsverzeichnis`), part 1 of `Musikblätter des Anbruch` (collection, 1 parts, 1 texts)
1. text “Das Stadtbild” (`Geschichte der Stadt Basel. Erster Band/2. Die rudolfinische Zeit/2. Das Stadtbild`), part 7 of `Geschichte der Stadt Basel. Erster Band` (collection, 30 parts, 25 texts)
1. text “Gemeinde Klein-Sachsenheim” (`Beschreibung des Oberamts Vaihingen/Kapitel B 12`), part 19 of `Beschreibung des Oberamts Vaihingen` (collection, 28 parts, 21 texts)
1. text “Vierundzwanzigster Brief.” (`Die Heimath in der neuen Welt/Zweiter Band/Vierundzwanzigster Brief`), part 27 of `Die Heimath in der neuen Welt` (collection, 50 parts, 33 texts)
1. text “Giftrezepte der Illuminaten und ein berüchtigtes Protokoll” (`Geschichte des Illuminaten-Ordens/Giftrezepte der Illuminaten und ein berüchtigtes Protokoll`), part 17 of `Geschichte des Illuminaten-Ordens` (collection, 25 parts, 24 texts)
1. text “Gesellschaftlicher Zustand” (`Beschreibung des Oberamts Mergentheim/Kapitel A 6`), part 6 of `Beschreibung des Oberamts Mergentheim` (collection, 56 parts, 56 texts)
1. text “Das Diaconissenhaus als Schule” (`Etwas aus der Geschichte des Diaconissenhauses Neuendettelsau/§. 4. Das Diaconissenhaus als Schule`), part 7 of `Etwas aus der Geschichte des Diaconissenhauses Neuendettelsau` (collection, 14 parts, 14 texts)
1. text “Rudolf Däbritz” (`Paulys Realencyclopädie der classischen Altertumswissenschaft/Register/Rudolf Däbritz`), part 1047 of `Paulys Realencyclopädie der classischen Altertumswissenschaft` (collection, 1,325 parts, 1,297 texts)
1. text “Inhaltsverzeichnis Die Schaubühne” (`Die Schaubühne – Die Weltbühne/Inhalt Schaubühne`), part 13 of `Die Schaubühne – Die Weltbühne` (collection, 23 parts, 23 texts)
1. text “„Wilflingen” (`Beschreibung des Oberamts Ellwangen/Kapitel B 22`), part 29 of `Beschreibung des Oberamts Ellwangen` (collection, 37 parts, 36 texts)
1. text “Würzburg” (`Geschichte von Kloster Heilsbronn/Würzburg`), part 290 of `Geschichte von Kloster Heilsbronn` (collection, 484 parts, 482 texts)
1. text “Der Bullerborn” (`Westphälische Sagen und Geschichten/Der Bullerborn`), part 13 of `Westphälische Sagen und Geschichten` (collection, 42 parts, 42 texts)
1. text “Danksagung für die Leiden Christi” (`Evangelien-Postille (Wilhelm Löhe)/Danksagung`), part 27 of `Evangelien-Postille (Wilhelm Löhe)` (collection, 100 parts, 77 texts)
1. text “75 Kupferstiche” (`Topographia Colonia et al./Verzeichnis der Bilder`), part 1 of `Topographia Colonia et al.` (collection, 1 parts, 1 texts)
1. text “Benützte Quellen (gedruckte und ungedruckte)” (`Geschichte der Pfarrei Sachsen bei Ansbach und der zugehörigen Orte/Benützte Quellen`), part 9 of `Geschichte der Pfarrei Sachsen bei Ansbach und der zugehörigen Orte` (book, 14 parts, 2 texts)
1. text “Beerbach” (`Geschichte von Kloster Heilsbronn/Beerbach`), part 307 of `Geschichte von Kloster Heilsbronn` (collection, 484 parts, 482 texts)
1. text “Leidingendorf” (`Geschichte von Kloster Heilsbronn/Leidingendorf`), part 337 of `Geschichte von Kloster Heilsbronn` (collection, 484 parts, 482 texts)
1. text “Otfried Deubner” (`Paulys Realencyclopädie der classischen Altertumswissenschaft/Register/Otfried Deubner`), part 896 of `Paulys Realencyclopädie der classischen Altertumswissenschaft` (collection, 1,325 parts, 1,297 texts)
1. text “Einzelne Monate der Ausgabe-Rechnung vom J. 1391” (`Aachener Stadtrechnungen aus dem XIV. Jahrhundert/Einzelne Monate der Ausgabe-Rechnung vom J. 1391`), part 65 of `Aachener Stadtrechnungen aus dem XIV. Jahrhundert` (collection, 71 parts, 69 texts)
1. text “Theodor Kipp” (`Paulys Realencyclopädie der classischen Altertumswissenschaft/Register/Theodor Kipp`), part 1128 of `Paulys Realencyclopädie der classischen Altertumswissenschaft` (collection, 1,325 parts, 1,297 texts)
1. text “Gerhard von Kügelgens Portraits von Goethe, Wieland, Schiller, und Herder” (`Jugendleben und Wanderbilder/Band 2 Gerhard von Kügelgens Portraits von Goethe, Wieland, Schiller, und Herder`), part 7 of `Jugendleben und Wanderbilder` (collection, 11 parts, 7 texts)
1. text “Wie herzog Berchtolt von Zeringen apt Huldreichen von Sant Gallen überzogen und darneben die freiherschaft Zimbern beraubt, verbrent und jemerlichen verderbt hat.” (`Zimmerische Chronik/Band 1/Kapitel 15`), part 17 of `Zimmerische Chronik` (collection, 159 parts, 149 texts)
1. text “Hohentwiel” (`Beschreibung des Oberamts Tuttlingen/Kapitel B 24`), part 31 of `Beschreibung des Oberamts Tuttlingen` (collection, 31 parts, 31 texts)
1. text “Am Himmelfahrtsfeste 1834. (Mark. 16, 19)” (`Predigten für die festliche Hälfte des Kirchenjahres/Am Himmelfahrtsfeste 1834`), part 33 of `Predigten für die festliche Hälfte des Kirchenjahres` (collection, 40 parts, 39 texts)
1. text “Am neunzehnten Sonntage nach Trinitatis” (`Evangelien-Postille (Wilhelm Löhe)/Trinitatis 19`), part 87 of `Evangelien-Postille (Wilhelm Löhe)` (collection, 100 parts, 77 texts)
1. text “Maximilian Lambertz” (`Paulys Realencyclopädie der classischen Altertumswissenschaft/Register/Maximilian Lambertz`), part 865 of `Paulys Realencyclopädie der classischen Altertumswissenschaft` (collection, 1,325 parts, 1,297 texts)
1. text “Völuspâ.” (`Die Edda (Simrock 1876)/Ältere Edda/Völuspâ`), part 1 of `Die Edda (Simrock 1876)` (collection, 44 parts, 42 texts)

### C: oversized (317)

1. `Gesetz, betreffend die Quartierleistung für die bewaffnete Macht während des Friedenszustandes`: single, single, 1 parts, 0 missing, order –, validated
    - text: Gesetz, betreffend die Quartierleistung für die bewaffnete Macht während des Friedenszustandes
1. `Die Gartenlaube (1870)`: mixed, collection, 409 parts, 0 missing, order toc-partial, validated
    - front: Die Gartenlaube (1870)
    - text: Doctor Reinhard [toc]
    - text: An mein Vaterland [toc]
    - text: Um eines Vogels willen [toc]
    - text: Schulkindkrankheiten oder Schulkrankheiten? [toc]
    - text: Mosigkau und Adelaide [toc]
    - text: Wie man Deutsche in Italien behandelt [toc]
    - text: Der Student auf Ferien [toc]
    - text: Vom „alten Fritz“ in Westphalen [toc]
    - text: Ein deutscher Prinz in Amerika [toc]
    - text: Die Eisenschmieden im Stubaithale [toc]
    - text: Pauline Lucca [toc]
    - … 398 more
1. `Indiana`: single, single, 1 parts, 0 missing, order –, validated
    - text: Indiana
1. `Die Gartenlaube (1880)`: mixed, collection, 387 parts, 0 missing, order toc-partial, validated
    - front: Die Gartenlaube (1880)
    - text: Ledige Kinder [toc]
    - text: Das Denkmal der Königin Luise für Berlin [toc]
    - text: Die Spielwuth in San Francisco [toc]
    - text: Geborgtes Sonnenlicht [toc]
    - text: Die Cremoneser Geige [toc]
    - text: König Friedrich Wilhelm der Erste und die Akademie der Wissenschaften in Berlin [toc]
    - text: Ein deutscher Waldbaum der Zukunft [toc]
    - text: Er ist todt [toc]
    - text: Amerikanischer Arbeiterverein für deutsche Einwanderer [toc]
    - text: In Deutschland verloren (`In Deutschland verloren (Die Gartenlaube 1880/1)`) [toc]
    - text: Aus der Schülerzeit [toc]
    - … 376 more
1. `Kleiner Briefkasten (Die Gartenlaube 1877)`: single, single, 1 parts, 0 missing, order –, validated
    - text: Kleiner Briefkasten (Die Gartenlaube 1877)
1. `Freie Bahn!`: single, single, 1 parts, 0 missing, order –, validated
    - text: Freie Bahn!
1. `Die Geschichtschreiber der deutschen Vorzeit (2. Gesamtausgabe)`: linked, book, 20 parts, 0 missing, order toc, validated
    - front: Die Geschichtschreiber der deutschen Vorzeit (2. Gesamtausgabe)
    - text: Isidors Geschichte der Gothen, Vandalen, Sueven, nebst Auszügen aus der Kirchengeschichte des Beda Venerabilis (`Isidors Geschichte der Gothen, Vandalen, Sueven`) [container]
    - text: Aus Liudprands Werken [container]
    - text: Das Leben der Kaiserin Adalheid [container]
    - volume: Die Chronik des Thietmar von Merseburg [container]
      - chapter: Erstes Buch (`Die Chronik des Thietmar von Merseburg/Erstes Buch`) [hierarchy]
      - chapter: Zweites Buch (`Die Chronik des Thietmar von Merseburg/Zweites Buch`) [hierarchy]
      - chapter: Drittes Buch (`Die Chronik des Thietmar von Merseburg/Drittes Buch`) [hierarchy]
      - chapter: Viertes Buch (`Die Chronik des Thietmar von Merseburg/Viertes Buch`) [hierarchy]
      - chapter: Fünftes Buch (`Die Chronik des Thietmar von Merseburg/Fünftes Buch`) [hierarchy]
      - chapter: Sechstes Buch (`Die Chronik des Thietmar von Merseburg/Sechstes Buch`) [hierarchy]
      - chapter: Siebentes Buch (`Die Chronik des Thietmar von Merseburg/Siebentes Buch`) [hierarchy]
    - … 9 more
1. `Verordnung, die Einführung des Preußischen Militair-Strafrechts im ganzen Bundesgebiete betreffend`: single, single, 1 parts, 0 missing, order –, validated
    - text: Verordnung, die Einführung des Preußischen Militair-Strafrechts im ganzen Bundesgebiete betreffend
1. `Leben und Thaten des ehrwürdigen Paters Simpertus`: single, single, 1 parts, 0 missing, order –, validated
    - text: Leben und Thaten des ehrwürdigen Paters Simpertus
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
1. `Geschichte des Deutschen Buchhandels Band 1`: subpages, book, 26 parts, 0 missing, order chain, validated
    - front: Geschichte des Deutschen Buchhandels Band 1
    - chapter: Kapitel 1: Gutenberg und seine Vorläufer. (`Geschichte des Deutschen Buchhandels Band 1/Erstes Kapitel`) [container]
    - chapter: Kapitel 2: Die Ausbreitung der neuen Kunst in Deutschland. (`Geschichte des Deutschen Buchhandels Band 1/Zweites Kapitel`) [container]
    - chapter: Kapitel 3: Die Verbreitung der neuen Kunst im Auslande. (`Geschichte des Deutschen Buchhandels Band 1/Drittes Kapitel`) [container]
    - chapter: Kapitel 4: Das Äußere des Buchs. (`Geschichte des Deutschen Buchhandels Band 1/Viertes Kapitel`) [container]
    - chapter: Kapitel 5: Der buchhändlerische Geschäftsbetrieb bis zur Reformation. (`Geschichte des Deutschen Buchhandels Band 1/Fünftes Kapitel`) [container]
    - chapter: Kapitel 6: Der Buchhandel in seinem Verhältnis zum Humanismus. (`Geschichte des Deutschen Buchhandels Band 1/Sechstes Kapitel`) [container]
    - chapter: Kapitel 7: Luther. (Die Reformation und der Buchhandel.) (`Geschichte des Deutschen Buchhandels Band 1/Siebentes Kapitel`) [container]
    - chapter: Kapitel 8: Die frankfurter Messe. (`Geschichte des Deutschen Buchhandels Band 1/Achtes Kapitel`) [container]
    - chapter: Kapitel 9: Die Büchercensur und die Preßverfolgungen. (`Geschichte des Deutschen Buchhandels Band 1/Neuntes Kapitel`) [container]
    - chapter: Kapitel 10: Die frankfurter Bücherkommission. (`Geschichte des Deutschen Buchhandels Band 1/Zehntes Kapitel`) [container]
    - chapter: Kapitel 11: Der Nachdruck. (`Geschichte des Deutschen Buchhandels Band 1/Elftes Kapitel`) [container]
    - … 15 more
1. `Die Gartenlaube (1896)`: mixed, collection, 613 parts, 77 missing, order toc-partial, proofread
    - front: Die Gartenlaube (1896)
    - text: Wie bekämpft man die Abmagerung? (`Wie bekämpft man die Abmagerung`) [toc]
    - text: Das Opfer eines Kaisertraums [toc]
    - text: Vielliebchen (`Vielliebchen (Die Gartenlaube 1896)`) [toc]
    - text: Havelschilf [toc]
    - text: Wirtschaftliche Hochschulen für Mädchen [toc]
    - text: Neujahrsmorgen vor Paris [toc]
    - text: Der Einzug der Sieger [toc]
    - text: Angriff von Kormoranen auf Reihernester [toc]
    - text: Vetter Emils Statue [toc]
    - text: Rudolf Leuckart [toc]
    - text: Glatte Bahn! (`Glatte Bahn`) [toc]
    - … 602 more
1. `Signale für die musikalische Welt`: subpages, book, 1 parts, 0 missing, order single, unknown
    - front: Signale für die musikalische Welt
    - chapter: Inhalt (`Signale für die musikalische Welt/Inhalt`) [hierarchy]
1. `Vier Monate auf den Marquesas-Inseln`: subpages, book, 2 parts, 0 missing, order chain, validated
    - toc: Vier Monate auf den Marquesas-Inseln
    - chapter: 1. Theil (`Vier Monate auf den Marquesas-Inseln/1`) [container]
    - chapter: Zweiter Theil (`Vier Monate auf den Marquesas-Inseln/2`) [container]
1. `Hans Brass und die Juden, 1934 bis 1936`: single, single, 1 parts, 0 missing, order –, proofread
    - text: Hans Brass und die Juden, 1934 bis 1936
1. `Erzählungen vom Oberharz in Oberharzer Mundart`: single, single, 1 parts, 0 missing, order –, proofread
    - text: Erzählungen vom Oberharz in Oberharzer Mundart
1. `Isis`: linked, collection, 2 parts, 0 missing, order toc-partial, proofread
    - front: Isis
    - text: Die allgemeine deutsche Erziehungsanstalt in Keilhau bey Rudolstadt betreffend [container]
    - text: Isis, Heft 1 (`Isis (1817, Heft 1)`) [container]
1. `Quellensammlung zur Geschichte der Deutschen Reichsverfassung in Mittelalter und Neuzeit`: mixed, collection, 39 parts, 2 missing, order toc, proofread
    - front: Quellensammlung zur Geschichte der Deutschen Reichsverfassung in Mittelalter und Neuzeit
    - text: Wormser Konkordat [container]
    - text: Einladung zum Reichstag (Königswahl) 1125 (`Einladung zum Reichstag (1125)`) [container]
    - text: Lehnsgesetz Lothars III. (`Lehensgesetz Lothars III.`) [container]
    - text: Sachsenspiegel (`Sachsenspiegel (Auszug)`) [container]
    - text: Kaiserliches Land- und Lehenrechtsbuch, sogenannter Schwabenspiegel (Auszug) (`Schwabenspiegel (Auszug)`) [container]
    - text: König Ludwigs des Baiern Reichs-Landfriede [container]
    - text: Kurverein von Rhense [container]
    - text: Revers Kaiser Ludwigs d. B. für das Erzstift Mainz bezüglich der Einführung der kaiserlichen geschriebenen Rechte am kaiserlichen Hofgerichte [container]
    - text: Reichsvikariat wegen Gefangenschaft des Königs (`Reichsvikariat wegen Gefangenschaft Königs Wenzels`) [container]
    - text: Mainzer Kurverein [container]
    - text: Absetzung König Wenzels [container]
    - … 28 more
1. `Die Gartenlaube (1892)`: mixed, collection, 422 parts, 0 missing, order toc-partial, proofread
    - front: Die Gartenlaube (1892)
    - text: Die neue Zeit (`Die neue Zeit (Proelß)`) [toc]
    - text: Prosit Neujahr! (`Prosit Neujahr! (Die Gartenlaube 1892/1)`) [toc]
    - text: Der Tod der Kaiserin Agrippina [toc]
    - text: Der Zeitgeist im Hausstande [toc]
    - text: Venetianisches Ständchen [toc]
    - text: Aus den Werkstätten des Lichts [toc]
    - text: Neunzig Jahre Männermode [toc]
    - text: Unschuldig verurtheilt! [toc]
    - text: Dom Pedro [toc]
    - text: Die Schlacht auf den katalaunischen Feldern [toc]
    - text: Seine Excellenz [toc]
    - … 411 more
1. `Beschreibung der Gemeinde Speicher`: subpages, collection, 7 parts, 0 missing, order natural, validated
    - front: Beschreibung der Gemeinde Speicher
    - chapter: Einleitung (`Beschreibung der Gemeinde Speicher/Einleitung`) [hierarchy]
    - text: Beschreibung der Gemeinde Speicher. (Fortsetzung 1) (`Beschreibung der Gemeinde Speicher/Fortsetzung 1`) [hierarchy]
    - text: Fortsetzung 2 (`Beschreibung der Gemeinde Speicher/Fortsetzung 2`) [hierarchy]
    - text: Fortsetzung 3 (`Beschreibung der Gemeinde Speicher/Fortsetzung 3`) [hierarchy]
    - text: Fortsetzung 4 (`Beschreibung der Gemeinde Speicher/Fortsetzung 4`) [hierarchy]
    - text: Glossar (`Beschreibung der Gemeinde Speicher/Glossar`) [hierarchy]
    - chapter: Register (`Beschreibung der Gemeinde Speicher/Register`) [hierarchy]
1. `Cod. hist. fol. 250`: single, single, 1 parts, 0 missing, order –, unproofread
    - text: Cod. hist. fol. 250
1. `Kleiner Briefkasten (Die Gartenlaube 1873)`: single, single, 1 parts, 0 missing, order –, validated
    - text: Kleiner Briefkasten (Die Gartenlaube 1873)
1. `Die Gartenlaube (1889)`: mixed, collection, 445 parts, 0 missing, order toc-partial, validated
    - front: Die Gartenlaube (1889)
    - text: Lore von Tollen [toc]
    - text: Die Wiege und das Grab der Hohenstaufen [toc]
    - text: Die Vermählung der Todten [toc]
    - text: Das nervöse Herz [toc]
    - text: Der Eissport [toc]
    - text: Wohnungsnoth der Arbeiterinnen [toc]
    - text: Gefangennahme Thusneldas durch Germanicus [toc]
    - text: Ein Gnadengesuch [toc]
    - text: Amor und Psyche (`Amor und Psyche (Die Gartenlaube 1889/1)`) [toc]
    - text: Portrait Kaiser Wilhelms II. (`Porträt Kaiser Wilhelms II.`) [toc]
    - text: Die schwarze Kunst des 19. Jahrhunderts [toc]
    - … 434 more
1. `Salvatore`: single, single, 1 parts, 0 missing, order –, proofread
    - text: Salvatore
1. `Die Gartenlaube (1895)`: mixed, collection, 442 parts, 1 missing, order toc-partial, proofread
    - front: Die Gartenlaube (1895)
    - text: Buen Retiro [toc]
    - text: Die „Behauptungen“ der Damen [toc]
    - text: Um eine Kleinigkeit [toc]
    - text: Kopfschmerzen [toc]
    - text: Die „letzten Goten“ [toc]
    - text: Nydia [toc]
    - text: Auf der Parforcejagd [toc]
    - text: Fenstervorhänge für Arbeitszimmer [toc]
    - text: Ein günstiger Augenblick [toc]
    - text: Der „Gartenlaube-Walzer“ von Johann Strauß [toc]
    - text: Unsere Kunstbeilage [toc]
    - … 431 more
1. `Bemerkungen über den Feldzug gegen Rußland in den Jahren 1812 und 1813`: subpages, collection, 1 parts, 0 missing, order single, validated
    - front: Bemerkungen über den Feldzug gegen Rußland in den Jahren 1812 und 1813
    - text: Verzeichnis der Namen (`Bemerkungen über den Feldzug gegen Rußland in den Jahren 1812 und 1813/Namen`) [hierarchy]
1. `Curieuse und sehr wunderbare Relation, von denen sich neuer Dingen in Servien erzeigenden Blut-Saugern oder Vampyrs`: single, single, 1 parts, 0 missing, order –, validated
    - text: Curieuse und sehr wunderbare Relation, von denen sich neuer Dingen in Servien erzeigenden Blut-Saugern oder Vampyrs
1. `Ein kurtzweilig lesen von Dyl Vlenspiegel`: single, single, 1 parts, 0 missing, order –, validated
    - text: Ein kurtzweilig lesen von Dyl Vlenspiegel
1. `Allgemeine musikalische Zeitung (Wien)`: single, single, 1 parts, 0 missing, order –, unknown
    - text: Allgemeine musikalische Zeitung (Wien)
1. `Großherzoglich Hessisches AGBGB`: single, single, 1 parts, 0 missing, order –, proofread
    - text: Großherzoglich Hessisches AGBGB

### G: reference works (0)


### H: deep nesting (7)

1. `Album der Rittergüter und Schlösser im Königreiche Sachsen`: linked, collection, 775 parts, 13 missing, order scan-volumes, proofread
    - front: Album der Rittergüter und Schlösser im Königreiche Sachsen
    - text: H1 (`Album der Rittergüter und Schlösser im Königreiche Sachsen III. Section/H1`) [container]
    - text: H2 (`Album der Rittergüter und Schlösser im Königreiche Sachsen III. Section/H2`) [container]
    - text: H3 (`Album der Rittergüter und Schlösser im Königreiche Sachsen III. Section/H3`) [container]
    - text: H4 (`Album der Rittergüter und Schlösser im Königreiche Sachsen III. Section/H4`) [container]
    - text: H5 (`Album der Rittergüter und Schlösser im Königreiche Sachsen III. Section/H5`) [container]
    - text: H6 (`Album der Rittergüter und Schlösser im Königreiche Sachsen III. Section/H6`) [container]
    - text: H8 (`Album der Rittergüter und Schlösser im Königreiche Sachsen III. Section/H8`) [container]
    - text: H9 (`Album der Rittergüter und Schlösser im Königreiche Sachsen III. Section/H9`) [container]
    - text: H10 (`Album der Rittergüter und Schlösser im Königreiche Sachsen III. Section/H10`) [container]
    - text: H11 (`Album der Rittergüter und Schlösser im Königreiche Sachsen III. Section/H11`) [container]
    - text: H12 (`Album der Rittergüter und Schlösser im Königreiche Sachsen III. Section/H12`) [container]
    - … 764 more
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
1. `Adrian Ludwig Richter, Maler und Radierer. Verzeichnis seines gesamten graphischen Werkes`: linked, collection, 3 parts, 0 missing, order single, proofread
    - front: Adrian Ludwig Richter, Maler und Radierer. Verzeichnis seines gesamten graphischen Werkes
    - volume: II. Künstlerwiedergaben nach dem Meister (`Adrian Ludwig Richter, Maler und Radierer. Verzeichnis seines gesamten graphischen Werkes - Fortsetzung 1`) [toc]
      - volume: Fortsetzung siehe: Adrian Ludwig Richter, Maler und Radierer. Verzeichnis seines gesamten graphischen Werkes - Fortsetzung 2 (`Adrian Ludwig Richter, Maler und Radierer. Verzeichnis seines gesamten graphischen Werkes - Fortsetzung 2`) [toc]
        - text: Fortsetzung siehe: Adrian Ludwig Richter, Maler und Radierer. Verzeichnis seines gesamten graphischen Werkes - Fortsetzung 3 (`Adrian Ludwig Richter, Maler und Radierer. Verzeichnis seines gesamten graphischen Werkes - Fortsetzung 3`) [toc]
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

### I: works with other editions (49)

1. `Kennst du das Land, wo die Citronen blühn`: single, single, 1 parts, 0 missing, order –, validated
    - text: Kennst du das Land, wo die Citronen blühn
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
1. `Flugzeuggedanken (Sammelband)`: linked, collection, 103 parts, 0 missing, order toc, validated
    - front: Flugzeuggedanken (Sammelband)
    - text: Einsamer Spazierflug [container]
    - text: Versöhnung (`Versöhnung (Ringelnatz)`) [container]
    - text: Fallschirmsprung meiner Begleiterin [container]
    - text: Ein Freund erzählt mir [container]
    - text: Bär aus dem Käfig entkommen [container]
    - text: Helfen [container]
    - text: Frühling (`Frühling (Ringelnatz)`) [container]
    - text: Flugzeug am Winterhimmel [container]
    - text: Der Sänger (`Der Sänger (Ringelnatz)`) [container]
    - text: Gedenken an Wedekind [container]
    - text: An der alten Elster [container]
    - … 92 more
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
1. `Van dantzen`: single, single, 1 parts, 0 missing, order –, validated
    - text: Van dantzen
1. `Beim Bau der chinesischen Mauer`: single, single, 1 parts, 0 missing, order –, validated
    - text: Beim Bau der chinesischen Mauer
1. `Gedichte Fontane (Ausgabe 1905)`: linked, collection, 199 parts, 0 missing, order toc-partial, validated
    - front: Gedichte Fontane (Ausgabe 1905)
    - text: Guter Rath (`Guter Rath (Fontane, 1905)`) [container]
    - text: Memento [container]
    - text: Im Garten [container]
    - text: O trübe diese Tage nicht [container]
    - text: Herbstmorgen (`Herbstmorgen (Fontane, 1905)`) [container]
    - text: Der Kranich (`Der Kranich (Fontane, 1905)`) [container]
    - text: Bekenntniß (`Bekenntniß (Fontane, 1905)`) [container]
    - text: Ein Jäger (`Ein Jäger (Fontane, 1905)`) [container]
    - text: Alles still! (`Alles still (Fontane, 1905)`) [container]
    - text: Einem Kranken [container]
    - text: Frühling (`Frühling (Fontane)`) [container]
    - … 188 more
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
1. `Kennst du das Land? wo die Citronen blühn`: single, single, 1 parts, 0 missing, order –, validated
    - text: Kennst du das Land? wo die Citronen blühn
1. `Neues Lausitzisches Magazin`: linked, collection, 32 parts, 0 missing, order natural, validated
    - front: Neues Lausitzisches Magazin
    - text: Das Bild unsers Worbs [container]
    - text: Die Siedlungsverhältnisse von Berzdorf auf dem Eigen. (`Die Siedlungsverhältnisse von Berzdorf auf dem Eigen`) [container]
    - text: Sprey (`Die slavischen Orts- und Flurnamen der Oberlausitz: Sprey`) [container]
    - text: Tschellen, Tschelln, Tzschelln, Zschellen (`Die slavischen Orts- und Flurnamen der Oberlausitz: Tzschelln`) [container]
    - text: Heinr. Gottlob Gräve (`Heinrich Gottlob Gräve (Nekrolog)`) [container]
    - text: Karl Benjamin Preusker (`Karl Benjamin Preusker (NLM, 1858)`) [container]
    - text: Lebensbeschreibung des verstorbenen Herrn Rekt. M. Horns, in Kamenz. (`Lebensbeschreibung des verstorbenen Herrn Rekt. M. Horns, in Kamenz`) [container]
    - text: Carl Joachim Thomas Haupt (`Nachruf Carl Joachim Thomas Haupt`) [container]
    - text: Ode an das alte und das neue Jar. (`Ode (Lausizische Monatsschrift 1795)`) [container]
    - volume: Volkssagen in der Lausitz (Dornick und S.) [container]
      - text: Querxe [container]
    - … 21 more
1. `Der Kübelreiter (1921)`: single, single, 1 parts, 0 missing, order –, validated
    - text: Der Kübelreiter (1921)
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
1. `An die Freude (Beethoven)`: single, single, 1 parts, 0 missing, order –, proofread
    - text: An die Freude (Beethoven)
1. `Kinder- und Haus-Märchen Band 1 (1843)`: linked, collection, 86 parts, 0 missing, order chain, validated
    - front: Kinder- und Haus-Märchen Band 1 (1843)
    - text: Der Froschkönig oder der eiserne Heinrich (`Der Froschkönig oder der eiserne Heinrich (1843)`) [container]
    - text: Katze und Maus in Gesellschaft (`Katze und Maus in Gesellschaft (1843)`) [container]
    - text: Marienkind (`Marienkind (1843)`) [container]
    - text: Mährchen von einem, der auszog das Fürchten zu lernen (`Mährchen von einem, der auszog das Fürchten zu lernen (1843)`) [container]
    - text: Der Wolf und die sieben jungen Geislein (`Der Wolf und die sieben jungen Geislein (1843)`) [container]
    - text: Der treue Johannes (`Der treue Johannes (1843)`) [container]
    - text: Der gute Handel (`Der gute Handel (1843)`) [container]
    - text: Der wunderliche Spielmann (`Der wunderliche Spielmann (1843)`) [container]
    - text: Die zwölf Brüder (`Die zwölf Brüder (1843)`) [container]
    - text: Das Lumpengesindel (`Das Lumpengesindel (1843)`) [container]
    - text: Brüderchen und Schwesterchen (`Brüderchen und Schwesterchen (1843)`) [container]
    - … 75 more
1. `Der Rabe (Übersetzung Eben)`: single, single, 1 parts, 0 missing, order –, validated
    - text: Der Rabe (Übersetzung Eben)
1. `Morgenblatt (Cotta)`: linked, collection, 5 parts, 0 missing, order toc, validated
    - front: Morgenblatt (Cotta)
    - text: Hypochonders Mondlied (`Hypochonders Mondlied (1836)`) [container]
    - text: Die Judenbuche [container]
    - text: Die rechte Gabe [container]
    - text: Rose und Lilie [container]
    - text: Bei der Marien-Bergkirche von L. [container]
1. `Ode an die Freude`: single, single, 1 parts, 0 missing, order –, validated
    - text: Ode an die Freude
1. `Der große Stein bei Görlitz`: single, single, 1 parts, 0 missing, order –, validated
    - text: Der große Stein bei Görlitz
1. `Das von den Juden getödtete Mägdlein (Deutsche Sagen)`: single, single, 1 parts, 0 missing, order –, validated
    - text: Das von den Juden getödtete Mägdlein (Deutsche Sagen)
1. `Beim Bau der Chinesischen Mauer (Sammelband)`: linked, collection, 21 parts, 0 missing, order chain, validated
    - toc: Beim Bau der Chinesischen Mauer (Sammelband)
    - text: Beim Bau der Chinesischen Mauer (1931) [container]
    - text: Zur Frage der Gesetze [container]
    - text: Das Stadtwappen [container]
    - text: Von den Gleichnissen [container]
    - text: Die Wahrheit über Sancho Pansa [container]
    - text: Das Schweigen der Sirenen [container]
    - text: Prometheus (`Prometheus (Kafka)`) [container]
    - text: Der Jäger Gracchus [container]
    - text: Der Schlag ans Hoftor [container]
    - text: Eine Kreuzung [container]
    - text: Die Brücke [container]
    - … 10 more
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

