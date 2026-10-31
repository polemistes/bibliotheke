# de.wikisource: structure and quality survey (WP1a)

Generated 2026-10-31 by `python -I -m wscatalog.survey de` from `dewikisource-20261001-pages-articles.xml.bz2` (resolution and quality rules: PLAN.md 5.2 steps 1–1h and 5.4; config `pipeline/config/de.yaml`). Resolution took 8 s on the cached extract.

Input: 310,980 ns0 pages, 13,381 ns0 redirects, 339,510 scan pages, 7,112 Index pages, 773 pages with the `disambiguation` page prop.

## 1. Works

26,582 works resolved from 80,343 non-excluded ns0 pages; 25,980 enter the catalog after the hard exclusions (section 9). Multi-part works hold 54,002 parts, of which 47,942 are searchable texts.

| structure | works | parts | texts |
|---|---|---|---|
| single | 25,455 | 25,455 | 0 |
| linked | 425 | 20,702 | 20,120 |
| subpages | 372 | 10,193 | 7,488 |
| virtual | 241 | 483 | 392 |
| mixed | 89 | 22,624 | 19,942 |

| type (step 1e) | works |
|---|---|
| single | 25,455 |
| collection | 974 |
| book | 153 |

Largest works by parts:

| work | structure | type | parts | texts | missing | rules of the parts |
|---|---|---|---|---|---|---|
| Paulys Realencyclopädie der classischen Altertumswissenschaft | subpages | collection | 1,325 | 1,297 | 0 | hierarchy 1325 |
| Der Sagenschatz des Königreichs Sachsen | mixed | collection | 1,001 | 1,001 | 0 | container 1001 |
| Zerstreute Blätter | linked | collection | 793 | 779 | 0 | container 787, toc 6 |
| Album der Rittergüter und Schlösser im Königreiche Sachsen | linked | collection | 775 | 757 | 13 | container 775 |
| Christliche Symbolik | subpages | collection | 636 | 633 | 0 | hierarchy 636 |
| Die Gartenlaube (1898) | mixed | collection | 624 | 592 | 91 | toc 594, hierarchy 30 |
| Die Gartenlaube (1899) | mixed | collection | 624 | 595 | 217 | toc 596, hierarchy 28 |
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
| no-header | 484 |
| excluded-category | 53 |

## 3. Patterns A–I (WS§1)

|  | pattern | count |
|---|---|---|
| A | collection of root pages (`structure = linked`) | 425 works, 20,702 parts |
| B | mixed subpages and root pages (`mixed`) | 89 works, 22,624 parts |
| C | work with an oversized page (> oversized_bytes, transcluded scan pages included) | 317 works |
| D | missing root, virtual root (`virtual`) | 241 works, 483 parts |
| E | texts inside a subpage collection reached from a disambiguation or versions page | 50 parts (42 searchable); all searchable subpage texts: 8,100 |
| F | whole-text duplicate subpages (excluded) | 0 pages |
| G | reference works (type `reference`) | 0 works, 0 parts; 218,074 pages excluded by title prefix |
| H | deep nesting (parts at depth ≥ 2, i.e. three levels below the root) | 9 works |
| I | several editions: works with an other-versions template | 49 works; 0 versions pages excluded |

Tree depth of multi-part works (levels below the root): 1: 1,036, 2: 82, 3: 7, 4: 2.

## 4. Parent rules and order sources (steps 1b, 1c)

| parent rule | pages |
|---|---|
| container | 29,101 |
| own | 26,341 |
| toc | 17,837 |
| hierarchy | 7,064 |

| order source (nodes with ≥ 2 children) | nodes |
|---|---|
| chain | 564 |
| toc | 192 |
| toc-partial | 115 |
| natural | 98 |
| scan | 32 |

`toc-partial` (implementation choice, see section 13): the TOC lists at least half of the children, which keep its order; the rest follow in natural order. 35 `natural` nodes have a TOC that lists fewer than half.

Root pages attached by their header's container link: 21,497; most frequent containers: `Der Sagenschatz des Königreichs Sachsen` 999; `Die zehnte Muse` 472; `Deutsche Sagen (Brüder Grimm, Band 1)` 360; `Journal von und für Franken` 354; `Badisches Sagen-Buch II` 352; `Die Volkssagen von Pommern und Rügen` 287; `Das Ausland (1828)` 268; `Badisches Sagen-Buch I` 246; `Buch der Lieder` 242; `Deutsche Sagen (Brüder Grimm, Band 2)` 223.

Pages attached by TOC membership (rule 3): 17,837; most frequent TOC pages: `Die Gartenlaube (1899)` 596; `Die Gartenlaube (1898)` 594; `Die Gartenlaube (1896)` 561; `Die Gartenlaube (1887)` 472; `Die Gartenlaube (1888)` 470; `Die Gartenlaube (1854)` 439; `Die Gartenlaube (1878)` 426; `Die Gartenlaube (1886)` 423; `Die Gartenlaube (1885)` 415; `Die Gartenlaube (1892)` 394.

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

**Orphan trees** (virtual roots): 241, holding 483 parts. Examples: `Ein sehr kurtzweilige vnd hochnutzliche Fabel ` (1); `Fürstliches Edict Und Ernster Befehl` (1); `Verordnung, betreffend die Militär-Transport-Ordnung für Eisenbahnen im Kriege (Kriegs-Transport-Ordnung) ` (1); `Gesetz, betreffend die Kontrole des Reichshaushalts und des Landeshaushalts von Elsaß-Lothringen für das Etatsjahr 1883` (1); `Gesetz, betreffend die Feststellung eines zweiten Nachtrags zum Reichshaushalts-Etat für das Etatsjahr 1895` (1); `Source-Provider` (1); `Zusatzvertrag zum Handels- und Zollvertrage zwischen dem Deutschen Reiche und der Schweiz ` (2); `Berliner Erinnerungen` (1).

**Oversized pages** (> 300,000 bytes of wikitext including transcluded scan pages): 549. Largest: `Ahrenshoop vor und im Krieg` 4.0 MB; `Weg nach Berlin – Birkenwerder` 2.2 MB; `Kunst und Kultur in Ahrenshoop 1945 bis 1948` 2.1 MB; `Paulys Realencyclopädie der classischen Altertumswissenschaft/Register/Hans Gärtner` 2.0 MB; `Jugend (Zeitschrift)/Inhalt 1896–1910` 1.9 MB; `Jugend (Zeitschrift)/Inhalt 1911–1929` 1.8 MB; `Hans Brass und die Religion, 1934` 1.5 MB; `Paulys Realencyclopädie der classischen Altertumswissenschaft/Register/R` 1.5 MB; `Der Merker/Inhalt` 1.5 MB; `Allgemeine Wiener Musik-Zeitung` 1.5 MB.

**Red links in TOCs** (missing parts): 148 multi-part works have red links, 965 in total; median share of the TOC 6%. Most missing: `Die Gartenlaube (1899)` 217 of 841; `Gesammelte Schriften über Musik und Musiker` 114 of 237; `Die Gartenlaube (1898)` 91 of 715; `Die Gartenlaube (1896)` 77 of 690; `Die Gartenlaube (1888)` 37 of 559; `Illustrirte Zeitung` 35 of 115; `Die Grenzboten (1841/1842)` 27 of 52; `Schwäbische Chronik (Thomas Lirer)` 23 of 24.

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
| validated | 14,419 | 55.5 % | 38,004 | page-levels 9,697, status-template 4,722 |
| proofread | 6,955 | 26.8 % | 8,440 | status-template 4,862, page-levels 2,093 |
| unproofread | 266 | 1.0 % | 157 | status-template 157, page-levels 109 |
| unknown | 4,340 | 16.7 % | 1,339 | none 4,340 |

14 works are proofread by the ≥ 90 % rule. 229 of the 266 unproofread works have no page above level 1 (raw OCR).

**Incomplete**: 2,167 works — template 1,991, missing-parts 164, missing-pages 12.
Works with missing scan pages inside a transcluded range: 17 (77 pages).
1 works transclude scan pages of files the dump has no Page: pages for (counted as no signal, not as missing).
Wikisource's own translations (labelled, kept): 0.

Status values (`bearbeitungsstand`, `status`): `fertig` 50,522, `korrigiert` 9,015, `unvollständig` 2,040, `unkorrigiert` 342, `unvollständig (geplant)` 60, `off` 25, `teilkorrigiert` 4, `korrekturprobleme` 3, `fehler korr.` 1.

**Unknown works** (no signal): 4,340 in the catalog after the junk rules; the junk rules removed 296 (under-300-bytes 296).
Size: median 2,967 bytes, p10 1,063, p90 12,425; multi-part 49.

30 random unknown works:

- `Schwere, Elektricität und Magnetismus:209` (single, 2,484 B): Fingirte Ladungen einzelner Punkte. Die Function kann in doppelter Weise aufgefasst werden, je nachdem man oder als unabhängige Variable ansieht. Wir setzen als
- `Die Schedelsche Weltchronik (deutsch):166` (single, 9,813 B): CLXV verso: == Das sechst alter thumb\|right\|Linea der bebst // Constantinus der ander Jar der werlt .v m .ix c .lxiij.Jar christi .vij c .lxiiij. 5963. Jahr der
- `Stammbuch berümpter Fürsten, Graffen vnnd Herren Geschlechter:Vorwort 10` (single, 2,903 B): Querfurt der halff jhm wider auff. Item da Heinrich der Lewe durch seinen stoltz dem Hertzogthumb Sachsen einen mechtigen abbruch gethan hatte / erweckte Gott a
- `Berlinische Musikalische Zeitung` (single, 30,843 B): Digitalisat== Jg. 1793-1794 (mehr nicht erschienen) , Inhalt== [Spazier, Johann Gottlieb Carl]: Carneval zu Berlin, in: Berlinische Musikalische Zeitung, St. 1,
- `Wochenblatt Papierfabrikation` (single, 5,398 B): __TOC__ Apr. 1943 vereinigt mit Der Papier-Fabrikant bis Jan. 1945 unter dem vereinigten Titel Papier-Fabrikant : Wochenblatt für Papierfabrikation ab Mai 1947 
- `Beiträge zur Geschichte der Stadt Rostock` (single, 3,053 B): __TOC__ Aufsätze als PDF bei Landesbibliographie MV Register zu Band 1-20 mit einer Zeittafel. Hinstorff, Rostock 1938 1.1890/1895 , = , mit Bd. 2 = Heft 1, 189
- `Allgemeines Deutsches Kommersbuch:295` (single, 3,696 B): colspan="2" valign="top" \| 2. Und als man rüstet auf Weihnachtszeit, da war der Neckar gefroren, da stund ein Mann im Pilgramskleid wohl vor des Pfarrhofs Thore
- `Zeitschrift für komprimierte und flüssige Gase sowie für die Pressluftindustrie` (single, 7,778 B): __TOC__ Schriftleitung: Dr.-Ing. A. Sander, Berlin W. 30, Hohenstaufenstraße 44 Erschienen Weimar : Steinert Berlin : Estermann [1897-1898] Hauptsacht. 1.1897/9
- `Taufmatrikel Passau Sankt Stephan:Band 1:Seite 80` (single, 1,047 B): Doctor Metzger Frau Sänglin Aprilis=== 3 Ambrosius filius Joannis Fendlmayr Posaunblaser et Apollonia Coniugum Patrinus Leonardus Hockh Spittlmaister, 30 Anna f
- `Elektrische Kraft Hertz:155` (single, 2,768 B): 9. Die Kräfte elektrischer Schwingungen. schreitende elektrostatische Kraft superponire. Richtiger deuten wir wohl im Sinne unserer Theorie die Erscheinung, ind
- `Allgemeines Deutsches Kommersbuch:47` (single, 2,845 B): colspan="2" valign="top" \| 1. Sie ha=ben Tod und Ver=der=ben gespien: Wir ha=ben es nicht ge=lit=ten, zwei Ko=lonnen Fuß=volk, zwei Batte=rien, wir haben sie ni
- `Gesundheitsfürsorge für das Kindesalter` (single, 1,018 B): __TOC__ Früher bei DigiZeitschriften eine digitale Ausgabe (noch in ZDB, möglicherweise bald in GDZ) 3.1928 = (3+4) 4.1929 = (3+4)
- `Stammbuch berümpter Fürsten, Graffen vnnd Herren Geschlechter:Seite 11` (single, 2,968 B): Weitern vnnd volkommenlichern bericht von diser Edlen Herrschafft / würt (ob gott will) thu o n der Erwürdige vnd wolgelehrte Herr M. Cyriacus Spangenberg in se
- `Der deutsche Imker aus Böhmen` (single, 6,439 B): __TOC__ Zusatz früher: Monatsschrift für die Interessen der Bienenzucht Organ des Deutschen Bienenwirtschaftlichen Zentralvereines für Böhmen per 1903 darin auf
- `Die Schedelsche Weltchronik (deutsch):061` (single, 7,284 B): LX verso: == class="text" \| Das vierd alter Amos tet vbel vor dem herren vnd wardt von seinen knechten geslagen. vnd starb in seinem aigen haws geto e dtet. vnd
- `Hansische Geschichtsblätter` (single, 25,482 B): Diese Linkzusammenstellung ist nicht mehr nötig. Der die ZS herausgebende Hansische Geschichtsverein stellt auf seiner Seite alle Jahrgänge kostenlos zur Verfüg
- `Schwere, Elektricität und Magnetismus:325` (single, 2,005 B): Die Volta-Induction. Neumann’s Gesetz. Die Summe der beiden Ausdrücke (3) und (4) ist das vollständige Differential der Function welche der Function entgegenges
- `Schwere, Elektricität und Magnetismus:087` (single, 2,904 B): Satz von Green. Aus (2) und (3) geht dann ohne weiteres der Satz hervor: Dieser Satz ist gültig, wenn im Innern des Raumes die Functionen und , sowie die ersten
- `Die Stimme : Zentralblatt für Stimm- und Tonbildung, Gesangunterricht und Stimmhygiene` (single, 4,884 B): Die Stimme : Zentralblatt für Stimm- und Tonbildung, Gesangunterricht und Stimmhygiene. Berlin : Trowitzsch 1.1906/07 - 29.1935[?] Zusatz teils : Centralblatt f
- `Minerva : Jahrbuch der gelehrten Welt` (single, 15,559 B): __TOC__ 1.1891/92 - 10.1901/02 == 1.1891/92 Jahrbuch der universitären Welt, hrsg. von Dr. R. Kukula & Karl J. Trübner. Strassburg : Trübner = , = , = , (Stanfo
- `Schwere, Elektricität und Magnetismus:117` (single, 1,936 B): Anziehung eines homogenen elliptischen Cylinders. zu beachten, in denen und positiv sind. Diese Gleichungen geben zu erkennen, dass die positive Summe grösser s
- `Zeitschrift für experimentelle Pathologie und Therapie` (single, 6,152 B): , Berlin 1.1905 - 23.1921 mit 23.1921 Zusammenschluss mit Zeitschrift für die gesamte experimentelle Medizin einschließlich experimentelle Chirurgie 1.1905 - 23
- `Eygentliche Beschreibung Aller Stände auff Erden:Der Kürschner` (single, 623 B): align="center" align="center"\|250px Wol her / ich fütter Röck vnd Schaubn / Mach schürtzbeltz / brustthüch / Vehehaubn / Von Zöbel / Marder / Vehe vnd Lüchsen /
- `Schwere, Elektricität und Magnetismus:265` (single, 2,420 B): Hülfssatz aus der Analysis. Eintrittsstellen, dagegen negativ an allen Austrittsstellen. Bezeichnet man also (nach Zahlwerth und Vorzeichen) mit die Projection 
- `Neue Quartalschrift für katholische Geistliche` (single, 3,330 B): __TOC__ siehe auch die Wiederauflage Theologisch-Praktische Monatschrift zunächst für Seelsorger (3. verbesserte Auflage Prag 1821-1828) 5.1816/17 Neueste Theol
- `Karl Prochaska's illustrirte Monatsbände` (single, 4,649 B): __TOC__ 1.1889/90 (August-Juli) == Band 1 = , = Band 2 = , = Band 3 Wien-Bibliothek, = Band 4 Wien-Bibliothek, = Band 5 Wien-Bibliothek, = Band 6 Wien-Bibliothe
- `Praktische Blätter für Pflanzenbau und Pflanzenschutz` (single, 10,417 B): __TOC__ Bibliographische Informationen == Erschienen München : Bayer. Landwirtschafts-Verl. Freising ; München : Datterer [1925-1945] Stuttgart : Ulmer [1903-19
- `Elektrische Kraft Hertz:107` (single, 2,702 B): 6. Inductionserscheinungen in Isolatoren. schiebung des Leiters AA′ nach oben haben würde. Qualitativ die gleiche Wirkung würde ein oberhalb AA′ angebrachter zw
- `Stammbuch berümpter Fürsten, Graffen vnnd Herren Geschlechter:Seite 4` (single, 2,815 B): von Dingenstat / der sie also zu o m Stifft bracht Anno 1260. Chro. Magd. in vita Ludolphi Metrop. lib. 8. cap. 11. Es seind auch etliche hernacher von den Bisc
- `Magazin für die Mineralogie und mineralogische Technologie` (single, 663 B): 1.1789: digitale-sammlungen.de 2.1790: digitale-sammlungen.de

**Default view** (validated or proofread, complete, not reference): 19,277 works with 35,404 texts; with unknown works shown: 23,585 works (36,741 texts). Works in the default view whose pages are all level 1: 0. Current setting: `show_unknown_by_default = None`.

## 10. Parts table size (WS§5.2)

| parts_cap | works with catalog parts | parts rows | parts table, gzipped | parts_fts, gzipped |
|---|---|---|---|---|
| 500 | 1,114 | 46,837 | 2.85 MB | 0.79 MB |
| 2,000 | 1,125 | 54,886 | 3.31 MB | 0.87 MB |
| ∞ (all, reference works included) | 1,125 | 54,886 | 3.31 MB | 0.87 MB |
| 2,000, compact encoding | 1,125 | 54,886 | 3.01 MB | 0.87 MB |

Rows count multi-part works only, one row per part plus the root row. 24,855 single-page works would add one row each if stored. Sizes are a SQLite file holding only the table (with its index) at gzip level 9. The compact encoding (not in WS§5.1, measured for the cap decision) stores a part's page_title as the suffix after the work root's title where possible, display_title only when it differs from the last title segment, and no per-part quality_pages.

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

### A: collections of root pages (425)

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
1. `Allgemeiner Harz-Berg-Kalender für das Jahr 1935`: linked, collection, 2 parts, 4 missing, order toc, unproofread
    - front: Allgemeiner Harz-Berg-Kalender für das Jahr 1935
    - text: Der Ernst-August-Stollen im Oberharz [container]
    - text: Ein aussterbender Waldbaum im Oberharz [container]
1. `Geschichte der protestantischen Theologie`: linked, collection, 4 parts, 0 missing, order single, validated
    - toc: Geschichte der protestantischen Theologie
    - volume: Spener und der Pietismus [container]
      - text: Die Zeit von Spener und Francke (`Spener und der Pietismus/Die Zeit von Spener und Francke`) [container]
      - text: Joh. Albr. Bengel und seine Schule (`Spener und der Pietismus/Joh. Albr. Bengel und seine Schule`) [container]
      - text: Zinzendorf und die Brüdergemeine (`Spener und der Pietismus/Zinzendorf und die Brüdergemeine`) [container]
1. `Das Grüne Gewölbe: eine Auswahl von Meisterwerken in vier Bänden. Band 1`: linked, collection, 79 parts, 0 missing, order chain, validated
    - front: Das Grüne Gewölbe: eine Auswahl von Meisterwerken in vier Bänden. Band 1
    - text: Geschichte des Grünen Gewölbes (`Sponsel Grünes Gewölbe Band 1/Geschichte des Grünen Gewölbes`) [container]
    - text: Der Inhalt des Grünen Gewölbes – Übersicht über den I. Band des Tafelwerkes (`Sponsel Grünes Gewölbe Band 1/Der Inhalt des Grünen Gewölbes – Übersicht über den I. Band des Tafelwerkes`) [container]
    - text: Tafel 1 (`Sponsel Grünes Gewölbe Band 1/Tafel 1`) [container]
    - text: Tafel 2 (`Sponsel Grünes Gewölbe Band 1/Tafel 2`) [container]
    - text: Tafel 3 (`Sponsel Grünes Gewölbe Band 1/Tafel 3`) [container]
    - text: Tafel 4 (`Sponsel Grünes Gewölbe Band 1/Tafel 4`) [container]
    - text: Tafel 5 (`Sponsel Grünes Gewölbe Band 1/Tafel 5`) [container]
    - text: Tafel 6 (`Sponsel Grünes Gewölbe Band 1/Tafel 6`) [container]
    - text: Tafel 7 (`Sponsel Grünes Gewölbe Band 1/Tafel 7`) [container]
    - text: Tafel 8 (`Sponsel Grünes Gewölbe Band 1/Tafel 8`) [container]
    - text: Tafel 9 (`Sponsel Grünes Gewölbe Band 1/Tafel 9`) [container]
    - … 68 more
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
1. `Kapitän Bergers Kinder`: linked, collection, 1 parts, 2 missing, order single, validated
    - front: Kapitän Bergers Kinder
    - text: Das Piratennest auf Neu-Helgoland [toc]
1. `Dresdner Geschichtsblätter Band 1 (1892 bis 1896)`: linked, collection, 69 parts, 0 missing, order scan, validated
    - front: Dresdner Geschichtsblätter Band 1 (1892 bis 1896)
    - text: 1892–1895: (`Dresdner Geschichtsblätter Band 1 (1892 bis 1896) Todtenschau`) [container]
    - text: Dresdner Geschichtsblätter Band 1 (1892 bis 1896) Vereinsberichte [container]
    - text: Dresdner Geschichtsblätter Band 1 (1892 bis 1896) Vereinsmitglieder [container]
    - text: Dresdens Straßen und Plätze [toc]
    - text: Der Abschiedsbrief des letzten mittelalterlichen Pfarrers von Dresden [toc]
    - chapter: I. Altmarkt Nr. 15 (Goldner Ring) (`Merkwürdige Häuser`) [toc]
    - text: in Dresden (`Zinzendorf in Dresden`) [toc]
    - text: in Dresden (`Goethe in Dresden`) [toc]
    - text: Kurfürst Moritz und die Musik [toc]
    - text: Zeitgenössische Aufzeichnungen über die Einführung der Reformation in Dresden [toc]
    - text: Der erste Dresdner Buchhändler [toc]
    - … 58 more
1. `Acta Mathematica`: linked, collection, 1 parts, 0 missing, order single, validated
    - front: Acta Mathematica
    - text: Kalender-Formeln [container]
1. `Theatri Saxonici Ander Theil`: linked, collection, 2 parts, 0 missing, order natural, validated
    - front: Theatri Saxonici Ander Theil
    - text: Beltzig (`Theatri Saxonici Ander Theil:Belzig`) [container]
    - text: Plauen (`Theatri Saxonici Ander Theil:Plauen`) [container]
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
1. `Meyer’s Universum, oder Abbildung und Beschreibung des Sehenswerthesten und Merkwürdigsten der Natur und Kunst auf der ganzen Erde. Einundzwanzigster Band`: linked, collection, 44 parts, 0 missing, order chain, proofread
    - toc: Meyer’s Universum, oder Abbildung und Beschreibung des Sehenswerthesten und Merkwürdigsten der Natur und Kunst auf der ganzen Erde. Einundzwanzigster Band
    - text: Der neue Bundespalast in Bern [container]
    - text: Die Alhambra (2 Ansichten) (`Die Alhambra`) [container]
    - text: Japan (`Japan (Meyer’s Universum)`) [container]
    - text: Fiume (`Fiume (Meyer’s Universum)`) [container]
    - text: Eastport [container]
    - text: Kano [container]
    - text: Die Tombs in New-York [container]
    - chapter: Cilli [container]
    - text: Mauch-Chunk in Pennsylvanien [container]
    - text: Blick auf Jerusalem vom Oelberg aus [container]
    - text: Das Rathhaus (Court-House) in St. Louis [container]
    - … 33 more
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
1. `Zeitschrift der Gesellschaft für Schleswig-Holsteinische Geschichte`: linked, collection, 2 parts, 0 missing, order toc, validated
    - front: Zeitschrift der Gesellschaft für Schleswig-Holsteinische Geschichte
    - text: Zwei Bauernburgen in Dithmarschen. (`Zwei Bauernburgen in Dithmarschen`) [container]
    - text: Die frühere Irrenpflege in Schleswig-Holstein [container]
1. `Allgemeine deutsche Bibliothek (Musikartikel)`: linked, collection, 1 parts, 0 missing, order single, validated
    - front: Allgemeine deutsche Bibliothek (Musikartikel)
    - text: Auszug eines Schreibens aus – – – vom 27sten Febr. 1788 (`CPE Bach – Auszug eines Schreibens`) [container]
1. `Allgemeiner Harz-Berg-Kalender für das Jahr 1937`: linked, collection, 4 parts, 4 missing, order toc, unproofread
    - front: Allgemeiner Harz-Berg-Kalender für das Jahr 1937
    - text: Die Spiegel [container]
    - text: Die Heimat der Oberharzer [container]
    - text: Die Oberharzer Silberhütten [container]
    - text: Der ehemalige Eisensteins-Bergbau bei Lerbach [container]
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
1. `Namenbuch der Straßen und Plätze Dresdens`: linked, collection, 10 parts, 0 missing, order toc, validated
    - front: Namenbuch der Straßen und Plätze Dresdens
    - text: Aachener Straße (`Hantzsch:Aachener Straße`) [toc]
    - text: Ahlwardtstraße (`Hantzsch:Ahlwardtstraße`) [toc]
    - text: Albrechtstraße (`Hantzsch:Albrechtstraße`) [toc]
    - text: Altenberger Straße (`Hantzsch:Altenberger Straße`) [toc]
    - text: Alträcknitz (`Hantzsch:Alträcknitz`) [toc]
    - text: Angelikastraße (`Hantzsch:Angelikastraße`) [toc]
    - text: Bachstraße (`Hantzsch:Bachstraße`) [toc]
    - text: Blücherstraße (`Hantzsch:Blücherstraße`) [toc]
    - text: Bönischplatz (`Hantzsch:Bönischplatz`) [toc]
    - text: Bürgerwiese (`Hantzsch:An der Bürgerwiese`) [toc]
1. `Illustrirte Zeitung`: linked, collection, 80 parts, 35 missing, order toc-partial, proofread
    - front: Illustrirte Zeitung
    - text: Was wir wollen (`Was wir wollen (Illustrirte Zeitung, 1843)`) [container]
    - text: Helene, Prinzessin von Mecklenburg, verwittwete Herzogin von Orleans [container]
    - text: Die Bergung des Télémaque [container]
    - text: Die Eröffnung des Themsetunnels [container]
    - text: Der Komet (`Der Komet (Illustrirte Zeitung, 1843)`) [container]
    - text: Longchamp (`Longchamp (Illustrirte Zeitung, 1843)`) [container]
    - text: Der Zwerg-Pisang [container]
    - text: Das königliche Hoftheater zu Dresden [container]
    - text: Ein Reisemärchen (`Ein Reisemärchen/Erzählt von Plinius dem Jüngsten`) [container]
    - text: Introduction (`Ein Reisemärchen/Introduction`) [container]
    - chapter: Erstes Capitel (`Ein Reisemärchen/Erstes Capitel`) [container]
    - … 69 more
1. `Schwänke und Streiche aus Westpreussen II`: linked, collection, 3 parts, 0 missing, order chain, validated
    - toc: Schwänke und Streiche aus Westpreussen II
    - text: Die Katze als Kind [container]
    - text: Die dumme Wirtin [container]
    - text: Der dwatsche Hans [container]

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
1. `Die Gartenlaube (1897)`: mixed, collection, 413 parts, 1 missing, order toc-partial, validated
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
    - … 402 more
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
1. `Die Gartenlaube (1874)`: mixed, collection, 402 parts, 0 missing, order toc-partial, validated
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
    - … 391 more
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

### D: virtual roots (241)

1. `Gantz New eröffneter Bartkram `: virtual, collection, 1 parts, 0 missing, order single, proofread
    - text: Gantz New eröffneter Bartkram / Darinnen 24. Sorten allerhand zierliche wolmundirte / teutsch-Frantzösische vnd dieser zeit gebräuchliche Bärte zu finden. (`Gantz New eröffneter Bartkram / Darinnen 24. Sorten allerhand zierliche wolmundirte Bärte zu finden`)
1. `Leipzigs Industrien`: virtual, collection, 2 parts, 0 missing, order natural, validated
    - text: Leipzigs Industrien/Nr. 1. Aetherische Oele und Essenzen
    - text: Leipzigs Industrien/Nr. 2. Ein überwundenes Vorurtheil in einer Bagatellsache [hierarchy]
1. `Gesetz, betreffend den Servistarif und die Klasseneintheilung der Orte. Vom 26. Juli 1897 `: virtual, collection, 1 parts, 0 missing, order single, proofread
    - text: Gesetz, betreffend den Servistarif und die Klasseneintheilung der Orte. / Klasseneintheilung der Orte (`Gesetz, betreffend den Servistarif und die Klasseneintheilung der Orte. Vom 26. Juli 1897 / Klasseneintheilung der Orte`)
1. `Gesetz, betreffend die Kontrole des Reichshaushalts und des Landeshaushalts von Elsaß-Lothringen für das Etatsjahr 1881`: virtual, collection, 1 parts, 0 missing, order single, validated
    - text: Gesetz, betreffend die Kontrole des Reichshaushalts und des Landeshaushalts von Elsaß-Lothringen für das Etatsjahr 1881/82. (`Gesetz, betreffend die Kontrole des Reichshaushalts und des Landeshaushalts von Elsaß-Lothringen für das Etatsjahr 1881/82`)
1. `Gesetz, betreffend die Feststellung eines Nachtrags zum Reichshaushalts-Etat für das Etatsjahr 1881`: virtual, collection, 1 parts, 0 missing, order single, proofread
    - text: Gesetz, betreffend die Feststellung eines Nachtrags zum Reichshaushalts-Etat für das Etatsjahr 1881/82. (`Gesetz, betreffend die Feststellung eines Nachtrags zum Reichshaushalts-Etat für das Etatsjahr 1881/82`)
1. `Bekanntmachung, betreffend die Instruktion zur Ausführung der §§. 19 bis 29 des Gesetzes über die Abwehr und Unterdrückung von Viehseuchen `: virtual, collection, 2 parts, 0 missing, order natural, proofread
    - text: Bekanntmachung, betreffend die Instruktion zur Ausführung der §§. 19 bis 29 des Gesetzes vom 23. Juni 1880/1. Mai 1894 über die Abwehr und Unterdrückung von Viehseuchen. Anlage A. Anweisung für das De (`Bekanntmachung, betreffend die Instruktion zur Ausführung der §§. 19 bis 29 des Gesetzes über die Abwehr und Unterdrückung von Viehseuchen / Anlage A. Desinfektionsverfahren`)
    - text: Bekanntmachung, betreffend die Instruktion zur Ausführung der §§. 19 bis 29 des Gesetzes vom 23. Juni 1880/1. Mai 1894 über die Abwehr und Unterdrückung von Viehseuchen. Anlage B. Anweisung für das Ob (`Bekanntmachung, betreffend die Instruktion zur Ausführung der §§. 19 bis 29 des Gesetzes über die Abwehr und Unterdrückung von Viehseuchen / Anlage B. Obduktionsverfahren`) [hierarchy]
1. `Handels- und Schiffahrtsvertrag zwischen dem Deutschen Reiche und Schweden `: virtual, collection, 2 parts, 0 missing, order natural, proofread
    - text: Handels- und Schiffahrtsvertrag zwischen dem Deutschen Reiche und Schweden. – Schlußprotokoll. (`Handels- und Schiffahrtsvertrag zwischen dem Deutschen Reiche und Schweden / Schlußprotokoll`)
    - text: Handels- und Schiffahrtsvertrag zwischen dem Deutschen Reiche und Schweden. – Tarif A und B. (`Handels- und Schiffahrtsvertrag zwischen dem Deutschen Reiche und Schweden / Tarif A und B`) [hierarchy]
1. `Gesetz, betreffend die Feststellung des Reichshaushalts-Etats für das Etatsjahr 1885`: virtual, collection, 1 parts, 0 missing, order single, proofread
    - text: Gesetz, betreffend die Feststellung des Reichshaushalts-Etats für das Etatsjahr 1885/86. (`Gesetz, betreffend die Feststellung des Reichshaushalts-Etats für das Etatsjahr 1885/86`)
1. `Gesetz, betreffend die Kontrole des Reichshaushalts und des Landeshaushalts von Elsaß-Lothringen für das Etatsjahr 1890`: virtual, collection, 1 parts, 0 missing, order single, validated
    - text: Gesetz, betreffend die Kontrole des Reichshaushalts und des Landeshaushalts von Elsaß-Lothringen für das Etatsjahr 1890/91. (`Gesetz, betreffend die Kontrole des Reichshaushalts und des Landeshaushalts von Elsaß-Lothringen für das Etatsjahr 1890/91`)
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
1. `Wie Gott so wunnderbarlich den vnmenschlichen grossen Bauch `: virtual, collection, 1 parts, 0 missing, order single, validated
    - text: Newe zeitung: Wie Gott so wunnderbarlich den vnmenschlichen vnd vom Teuffel zugerichten grossen Bauch / an der Jungfrawen zu Eszlingen / offenbaret hat / […] (`Wie Gott so wunnderbarlich den vnmenschlichen grossen Bauch / an der Jungfrawen zu Eszlingen / offenbaret hat`)
1. `Heinrich Heine`: virtual, collection, 2 parts, 0 missing, order natural, unknown
    - text: Gesamtverzeichnis der Prosanotizen (`Heinrich Heine/DHA/Gesamtverzeichnis der Prosanotizen`)
    - text: Werkverzeichnis (`Heinrich Heine/DHA/Werkverzeichnis`) [hierarchy]
1. `In und um Tirols Oberammergau`: virtual, collection, 2 parts, 0 missing, order natural, validated
    - text: In und um Tirols Oberammergau/1.
    - text: „Auf den Bergen die Burgen“ und Notburga’s Ruhm und Preis (`In und um Tirols Oberammergau/2.`) [hierarchy]
1. `Erinnerungen aus meinem Leben (Friedrich Hecker)`: virtual, collection, 1 parts, 0 missing, order single, validated
    - text: Der Hexenmeister der Prairie (`Erinnerungen aus meinem Leben (Friedrich Hecker)/2`)
1. `Gesetz, betreffend die Feststellung des Reichshaushalts-Etats für das Etatsjahr 1883`: virtual, collection, 1 parts, 0 missing, order single, proofread
    - text: Gesetz, betreffend die Feststellung des Reichshaushalts-Etats für das Etatsjahr 1883/84. (`Gesetz, betreffend die Feststellung des Reichshaushalts-Etats für das Etatsjahr 1883/84`)
1. `Gesetz, betreffend die Feststellung des Haushalts-Etats des Deutschen Reichs für das Etatsjahr 1878`: virtual, collection, 1 parts, 0 missing, order single, proofread
    - text: Gesetz, betreffend die Feststellung des Haushalts-Etats des Deutschen Reichs für das Etatsjahr 1878/79. (`Gesetz, betreffend die Feststellung des Haushalts-Etats des Deutschen Reichs für das Etatsjahr 1878/79`)
1. `Achtzehnter Jahresbericht des deutschen Mädchenheims zu Paris 1902`: virtual, book, 1 parts, 0 missing, order single, validated
    - chapter: 1903 (`Achtzehnter Jahresbericht des deutschen Mädchenheims zu Paris 1902/1903`)
1. `Gesetz, betreffend die Feststellung eines zweiten Nachtrags zum Reichshaushalts-Etat für das Etatsjahr 1889`: virtual, collection, 1 parts, 0 missing, order single, proofread
    - text: Gesetz, betreffend die Feststellung eines zweiten Nachtrags zum Reichshaushalts-Etat für das Etatsjahr 1889/90. (`Gesetz, betreffend die Feststellung eines zweiten Nachtrags zum Reichshaushalts-Etat für das Etatsjahr 1889/90`)
1. `Gesetz, betreffend die Feststellung eines zweiten Nachtrags zum Reichshaushalts-Etat für das Etatsjahr 1896`: virtual, collection, 1 parts, 0 missing, order single, proofread
    - text: Gesetz, betreffend die Feststellung eines zweiten Nachtrags zum Reichshaushalts-Etat für das Etatsjahr 1896/97. (`Gesetz, betreffend die Feststellung eines zweiten Nachtrags zum Reichshaushalts-Etat für das Etatsjahr 1896/97`)
1. `Gesetz, betreffend die Feststellung des Haushalts-Etats für die Schutzgebiete auf das Etatsjahr 1894`: virtual, collection, 1 parts, 0 missing, order single, proofread
    - text: Gesetz, betreffend die Feststellung des Haushalts-Etats für die Schutzgebiete auf das Etatsjahr 1894/95. (`Gesetz, betreffend die Feststellung des Haushalts-Etats für die Schutzgebiete auf das Etatsjahr 1894/95`)
1. `Programm des Königlichen Realgymnasiums in Gmünd am Schlusse des Schuljahres 1896`: virtual, collection, 1 parts, 0 missing, order single, validated
    - text: Programm des Königlichen Realgymnasiums in Gmünd am Schlusse des Schuljahrs 1896/97 (`Programm des Königlichen Realgymnasiums in Gmünd am Schlusse des Schuljahres 1896/97`)
1. `Gesetz, betreffend die Reichs-Kriegshäfen und die Feststellung eines Nachtrages zum Reichshaushalts-Etat für das Etatsjahr 1883`: virtual, collection, 1 parts, 0 missing, order single, proofread
    - text: Gesetz, betreffend die Reichs-Kriegshäfen und die Feststellung eines Nachtrages zum Reichshaushalts-Etat für das Etatsjahr 1883/84. (`Gesetz, betreffend die Reichs-Kriegshäfen und die Feststellung eines Nachtrages zum Reichshaushalts-Etat für das Etatsjahr 1883/84`)
1. `Bekanntmachung des Textes der Unfallversicherungsgesetze vom 30. Juni 1900 `: virtual, collection, 5 parts, 0 missing, order natural, proofread
    - text: Bau-Unfallversicherungsgesetz. (`Bekanntmachung des Textes der Unfallversicherungsgesetze vom 30. Juni 1900 / Bau-Unfallversicherungsgesetz`)
    - text: Gesetz, betreffend die Abänderung der Unfallversicherungsgesetze. (`Bekanntmachung des Textes der Unfallversicherungsgesetze vom 30. Juni 1900 / Gesetz, betreffend die Abänderung der Unfallversicherungsgesetze`) [hierarchy]
    - text: Gewerbe-Unfallversicherungsgesetz. (`Bekanntmachung des Textes der Unfallversicherungsgesetze vom 30. Juni 1900 / Gewerbe-Unfallversicherungsgesetz`) [hierarchy]
    - text: See-Unfallversicherungsgesetz. (`Bekanntmachung des Textes der Unfallversicherungsgesetze vom 30. Juni 1900 / See-Unfallversicherungsgesetz`) [hierarchy]
    - text: Unfallversicherungsgesetz für Land- und Forstwirthschaft. (`Bekanntmachung des Textes der Unfallversicherungsgesetze vom 30. Juni 1900 / Unfallversicherungsgesetz für Land- und Forstwirthschaft`) [hierarchy]
1. `Zwo Warhafftige`: virtual, collection, 1 parts, 0 missing, order single, validated
    - text: Zwo Warhafftige/ und doch Männiglich zuvor bekante Newe Zeitungen
1. `Gesetz, betreffend die Feststellung eines Nachtrags zum Reichshaushalts-Etat für das Etatsjahr 1895`: virtual, collection, 1 parts, 0 missing, order single, proofread
    - text: Gesetz, betreffend die Feststellung eines Nachtrags zum Reichshaushalts-Etat für das Etatsjahr 1895/96. (`Gesetz, betreffend die Feststellung eines Nachtrags zum Reichshaushalts-Etat für das Etatsjahr 1895/96`)
1. `Gesetz, betreffend die Kontrole des Reichshaushalts, des Landeshaushalts von Elsaß-Lothringen und des Haushalts der Schutzgebiete für das Etatsjahr 1894`: virtual, collection, 1 parts, 0 missing, order single, validated
    - text: Gesetz, betreffend die Kontrole des Reichshaushalts, des Landeshaushalts von Elsaß-Lothringen und des Haushalts der Schutzgebiete für das Etatsjahr 1894/95. (`Gesetz, betreffend die Kontrole des Reichshaushalts, des Landeshaushalts von Elsaß-Lothringen und des Haushalts der Schutzgebiete für das Etatsjahr 1894/95`)
1. `Gesetz, betreffend die Feststellung des Reichshaushalts-Etats für das Etatsjahr 1889`: virtual, collection, 1 parts, 0 missing, order single, validated
    - text: Gesetz, betreffend die Feststellung des Reichshaushalts-Etats für das Etatsjahr 1889/90. (`Gesetz, betreffend die Feststellung des Reichshaushalts-Etats für das Etatsjahr 1889/90`)
1. `Zeitschriften (Illustrierte)`: virtual, collection, 1 parts, 0 missing, order single, unknown
    - text: The St. James' magazine (`Zeitschriften (Illustrierte)/fremdsprachige/The St. James' magazine`)
1. `Gesetz, betreffend die Feststellung des Reichshaushalts-Etats für das Etatsjahr 1897`: virtual, collection, 1 parts, 0 missing, order single, proofread
    - text: Gesetz, betreffend die Feststellung des Reichshaushalts-Etats für das Etatsjahr 1897/98. (`Gesetz, betreffend die Feststellung des Reichshaushalts-Etats für das Etatsjahr 1897/98`)
1. `Ergänzung zum Aufsatze „Ueber den Venusdurchgang vom 8.`: virtual, collection, 1 parts, 0 missing, order single, validated
    - text: Ergänzung zum Aufsatze „Ueber den Venusdurchgang vom 8./9. December 1874“

### E: subpage texts reached from a disambiguation or versions page (50)

1. text “Abel” (`Christliche Symbolik/Abel`), part 5 of `Christliche Symbolik` (collection, 636 parts, 633 texts)
1. text “Von einem, der auszog, das Fürchten zu lernen” (`Anmerkungen zu den Kinder- und Hausmärchen der Brüder Grimm I/Von einem der auszog, das Fürchten zu lernen`), part 4 of `Anmerkungen zu den Kinder- und Hausmärchen der Brüder Grimm I` (collection, 66 parts, 66 texts)
1. text “Der gute Handel” (`Anmerkungen zu den Kinder- und Hausmärchen der Brüder Grimm I/Der gute Handel`), part 8 of `Anmerkungen zu den Kinder- und Hausmärchen der Brüder Grimm I` (collection, 66 parts, 66 texts)
1. text “Ahasver” (`Christliche Symbolik/Ahasver`), part 20 of `Christliche Symbolik` (collection, 636 parts, 633 texts)
1. text “Katze und Maus in Gesellschaft” (`Anmerkungen zu den Kinder- und Hausmärchen der Brüder Grimm I/Katze und Maus in Gesellschaft`), part 2 of `Anmerkungen zu den Kinder- und Hausmärchen der Brüder Grimm I` (collection, 66 parts, 66 texts)
1. text “Die chinesische Panzercorvette „Ting Yuen“” (`Kleine Bilder aus der Gegenwart/Die chinesische Panzercorvette Ting Yuen`), part 8 of `Kleine Bilder aus der Gegenwart` (collection, 15 parts, 15 texts)
1. text “Kleine Bilder aus der Gegenwart. Die letzte Personenpost in Breslau” (`Kleine Bilder aus der Gegenwart/Die letzte Personenpost in Breslau`), part 9 of `Kleine Bilder aus der Gegenwart` (collection, 15 parts, 15 texts)
1. text “Anmerkungen zu den einzelnen Märchen” (`Kinder- und Haus-Märchen Band 3 (1856)/Anmerkungen`), part 6 of `Kinder- und Haus-Märchen Band 3 (1856)` (collection, 11 parts, 10 texts)
1. text “Adler” (`Christliche Symbolik/Adler`), part 13 of `Christliche Symbolik` (collection, 636 parts, 633 texts)
1. text “Der Froschkönig oder der eiserne Heinrich” (`Anmerkungen zu den Kinder- und Hausmärchen der Brüder Grimm I/Der Froschkönig oder der eiserne Heinrich`), part 1 of `Anmerkungen zu den Kinder- und Hausmärchen der Brüder Grimm I` (collection, 66 parts, 66 texts)
1. text “Der „Munk-Affe“” (`Kleine Bilder aus der Gegenwart/Der Munk-Affe`), part 6 of `Kleine Bilder aus der Gegenwart` (collection, 15 parts, 15 texts)
1. text “Die Hand mit dem Messer” (`Anmerkungen zu den Kinder- und Hausmärchen der Brüder Grimm I/Die Hand mit dem Messer`), part 10 of `Anmerkungen zu den Kinder- und Hausmärchen der Brüder Grimm I` (collection, 66 parts, 66 texts)
1. text “Der Brand in Aachen am 29. Juni dieses Jahres” (`Kleine Bilder aus der Gegenwart/Der Brand in Aachen am 29. Juni dieses Jahres`), part 3 of `Kleine Bilder aus der Gegenwart` (collection, 15 parts, 15 texts)
1. text “Marienkind” (`Anmerkungen zu den Kinder- und Hausmärchen der Brüder Grimm I/Marienkind`), part 3 of `Anmerkungen zu den Kinder- und Hausmärchen der Brüder Grimm I` (collection, 66 parts, 66 texts)
1. volume “Erster Band” (`Thalia/Erster Band`), part 1 of `Thalia` (collection, 68 parts, 64 texts)
1. text “Der Schweizer Alpenclub auf der Ausstellung in Zürich” (`Kleine Bilder aus der Gegenwart/Der Schweizer Alpenclub auf der Ausstellung in Zürich`), part 7 of `Kleine Bilder aus der Gegenwart` (collection, 15 parts, 15 texts)
1. text “Kleine Bilder aus der Gegenwart : Wettfahrt von Segelschlitten auf dem Müggelsee bei Berlin” (`Kleine Bilder aus der Gegenwart/Wettfahrt von Segelschlitten auf dem Müggelsee bei Berlin`), part 12 of `Kleine Bilder aus der Gegenwart` (collection, 15 parts, 15 texts)
1. volume “Mährchen und Jugenderinnerungen. Zweiter Theil.” (`Mährchen und Jugenderinnerungen/Zweiter Theil`), part 20 of `Mährchen und Jugenderinnerungen` (collection, 51 parts, 49 texts)
1. text “Der getreue Johannes” (`Anmerkungen zu den Kinder- und Hausmärchen der Brüder Grimm I/Der getreue Johannes`), part 6 of `Anmerkungen zu den Kinder- und Hausmärchen der Brüder Grimm I` (collection, 66 parts, 66 texts)
1. text “Der Tod und der Gänshirt” (`Anmerkungen zu den Kinder- und Hausmärchen der Brüder Grimm I/Der Tod und der Gänshirt`), part 31 of `Anmerkungen zu den Kinder- und Hausmärchen der Brüder Grimm I` (collection, 66 parts, 66 texts)
1. text “Kleine Bilder aus der Gegenwart: Zwei unpolitische „Kongresse“” (`Kleine Bilder aus der Gegenwart/Zwei unpolitische „Kongresse“`), part 13 of `Kleine Bilder aus der Gegenwart` (collection, 15 parts, 15 texts)
1. text “Wie Kinder Schlachtens miteinander gespielt haben” (`Anmerkungen zu den Kinder- und Hausmärchen der Brüder Grimm I/Wie Kinder Schlachtens miteinander gespielt haben`), part 25 of `Anmerkungen zu den Kinder- und Hausmärchen der Brüder Grimm I` (collection, 66 parts, 66 texts)
1. text “Der Wolf und die sieben jungen Geißlein” (`Anmerkungen zu den Kinder- und Hausmärchen der Brüder Grimm I/Der Wolf und die sieben jungen Geißlein`), part 5 of `Anmerkungen zu den Kinder- und Hausmärchen der Brüder Grimm I` (collection, 66 parts, 66 texts)
1. text “Ischia” (`Kleine Bilder aus der Gegenwart/Ischia`), part 11 of `Kleine Bilder aus der Gegenwart` (collection, 15 parts, 15 texts)
1. chapter “2.” (`Kunstketzereien/2.`), part 1 of `Kunstketzereien` (book, 2 parts, 0 texts)
1. text “Kleine Bilder aus der Gegenwart. 3. Im Taucherpavillon der Hygiene-Ausstellung in Berlin.” (`Kleine Bilder aus der Gegenwart/Im Taucherpavillon der Hygiene-Ausstellung in Berlin`), part 10 of `Kleine Bilder aus der Gegenwart` (collection, 15 parts, 15 texts)
1. chapter “1” (`Bilderschau in meinem Zimmer/1`), part 0 of `Bilderschau in meinem Zimmer` (book, 3 parts, 1 texts)
1. text “Der wunderliche Spielmann” (`Anmerkungen zu den Kinder- und Hausmärchen der Brüder Grimm I/Der wunderliche Spielmann`), part 9 of `Anmerkungen zu den Kinder- und Hausmärchen der Brüder Grimm I` (collection, 66 parts, 66 texts)
1. text “Von der Nachtigall und der Blindschleiche” (`Anmerkungen zu den Kinder- und Hausmärchen der Brüder Grimm I/Von der Nachtigall und der Blindschleiche`), part 7 of `Anmerkungen zu den Kinder- und Hausmärchen der Brüder Grimm I` (collection, 66 parts, 66 texts)
1. text “Der Fang der Eintagsfliege” (`Kleine Bilder aus der Gegenwart/Der Fang der Eintagsfliege`), part 4 of `Kleine Bilder aus der Gegenwart` (collection, 15 parts, 15 texts)

### all searchable subpage texts (chapter titles that the label patterns miss show up here) (8,100)

1. text “Am achten Sonntage nach Trinitatis” (`Epistel-Postille (Wilhelm Löhe)/Trinitatis 08`), part 50 of `Epistel-Postille (Wilhelm Löhe)` (collection, 71 parts, 67 texts)
1. text “Bonlanden” (`Beschreibung des Oberamts Stuttgart, Amt/Kapitel B 3`), part 10 of `Beschreibung des Oberamts Stuttgart, Amt` (collection, 35 parts, 27 texts)
1. text “Albrecht Dieterich” (`Paulys Realencyclopädie der classischen Altertumswissenschaft/Register/Albrecht Dieterich`), part 34 of `Paulys Realencyclopädie der classischen Altertumswissenschaft` (collection, 1,325 parts, 1,297 texts)
1. text “Ueber die Herren von Vestenberg” (`Geschichte von Kloster Heilsbronn/Ueber die Herren von Vestenberg`), part 424 of `Geschichte von Kloster Heilsbronn` (collection, 484 parts, 482 texts)
1. text “Maximilian Muttelsee” (`Paulys Realencyclopädie der classischen Altertumswissenschaft/Register/Maximilian Muttelsee`), part 866 of `Paulys Realencyclopädie der classischen Altertumswissenschaft` (collection, 1,325 parts, 1,297 texts)
1. text “Für den kranken Gatten” (`Stunden der Andacht/Für den kranken Gatten`), part 65 of `Stunden der Andacht` (collection, 102 parts, 100 texts)
1. text “Georg Kleinfeller” (`Paulys Realencyclopädie der classischen Altertumswissenschaft/Register/Georg Kleinfeller`), part 391 of `Paulys Realencyclopädie der classischen Altertumswissenschaft` (collection, 1,325 parts, 1,297 texts)
1. text “Fritz Gschnitzer” (`Paulys Realencyclopädie der classischen Altertumswissenschaft/Register/Fritz Gschnitzer`), part 373 of `Paulys Realencyclopädie der classischen Altertumswissenschaft` (collection, 1,325 parts, 1,297 texts)
1. text “Riedeldorf” (`Geschichte von Kloster Heilsbronn/Riedeldorf`), part 372 of `Geschichte von Kloster Heilsbronn` (collection, 484 parts, 482 texts)
1. text “Leiden” (`Christliche Symbolik/Leiden`), part 315 of `Christliche Symbolik` (collection, 636 parts, 633 texts)
1. text “Galerie historischer Enthüllungen 4.&nbsp;Arnold von Winkelried” (`Galerie historischer Enthüllungen/4. Arnold von Winkelried`), part 3 of `Galerie historischer Enthüllungen` (collection, 5 parts, 5 texts)
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

### C: oversized (317)

1. `Indiana`: single, single, 1 parts, 0 missing, order –, validated
    - text: Indiana
1. `Freie Bahn!`: single, single, 1 parts, 0 missing, order –, validated
    - text: Freie Bahn!
1. `Dresden. Berühmte Kunststätten, Band 46, 1. Auflage`: single, single, 1 parts, 0 missing, order –, validated
    - text: Dresden. Berühmte Kunststätten, Band 46, 1. Auflage
1. `Flammenzeichen`: subpages, book, 2 parts, 0 missing, order toc, validated
    - toc: Flammenzeichen
    - chapter: Teil I (`Flammenzeichen/I`) [hierarchy]
    - chapter: Teil II (`Flammenzeichen/II`) [hierarchy]
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
1. `Hans Brass und die Religion, September 1934`: single, single, 1 parts, 0 missing, order –, proofread
    - text: Hans Brass und die Religion, September 1934
1. `In Berlin – Friedrichstraße`: single, single, 1 parts, 0 missing, order –, proofread
    - text: In Berlin – Friedrichstraße
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
1. `Die Musik`: subpages, collection, 2 parts, 0 missing, order toc, unknown
    - front: Die Musik
    - text: Inhaltsverzeichnis 1901–1915 (`Die Musik/Inhaltsverzeichnis 1901–1915`) [hierarchy]
    - text: Inhaltsverzeichnis 1922–1943 (`Die Musik/Inhaltsverzeichnis 1922–1943`) [hierarchy]
1. `Curieuse und sehr wunderbare Relation, von denen sich neuer Dingen in Servien erzeigenden Blut-Saugern oder Vampyrs`: single, single, 1 parts, 0 missing, order –, validated
    - text: Curieuse und sehr wunderbare Relation, von denen sich neuer Dingen in Servien erzeigenden Blut-Saugern oder Vampyrs
1. `Aus eigener Kraft (Wilhelmine von Hillern)`: subpages, book, 2 parts, 0 missing, order toc, validated
    - toc: Aus eigener Kraft (Wilhelmine von Hillern)
    - chapter: Teil I (`Aus eigener Kraft (Wilhelmine von Hillern)/I`) [hierarchy]
    - chapter: Teil II (`Aus eigener Kraft (Wilhelmine von Hillern)/II`) [hierarchy]
1. `Der Sturm`: linked, collection, 2 parts, 0 missing, order toc-partial, validated
    - front: Der Sturm
    - text: Marie Böhm (`Marie Böhm (Lasker-Schüler)`) [container]
    - text: Sturmangriff [container]
1. `Westermanns Monatshefte`: subpages, collection, 1 parts, 0 missing, order single, unknown
    - front: Westermanns Monatshefte
    - text: Autoren-Register (`Westermanns Monatshefte/Autoren-Register`) [hierarchy]
1. `Quellen und Studien zur Verfassungsgeschichte des Deutschen Reiches in Mittelalter und Neuzeit`: linked, collection, 39 parts, 0 missing, order toc, validated
    - front: Quellen und Studien zur Verfassungsgeschichte des Deutschen Reiches in Mittelalter und Neuzeit
    - text: Die Goldene Bulle Kaiser Karls IV. (Teil 1) [container]
    - volume: Die Goldene Bulle Kaiser Karls IV. (Teil 2) [container]
      - text: Goldene Bulle (`Goldene Bulle (Zeumer, 1908)`) [container]
      - text: Wahldekret für Friedrich den Schönen [container]
      - text: Erklärung Heinrichs von Köln über Wahl und Krönung Friedrichs des Schönen [container]
      - text: Urkunde Ludwigs d. B. über das Schwertträgeramt [container]
      - text: Privileg Karls IV. für Trier über das Recht der ersten Stimme (`Privileg Karls IV. für Trier über das Recht der ersten Stimme (Nov. 1346)`) [container]
      - text: Privileg Karls IV. über das Schenkenamt des Königs von Böhmen [container]
      - text: Hofgerichtsurkunde Karls IV. über ein Kurfürstenweistnm, betreffend die kurfürstliche Gerichtshoheit (`Hofgerichtsurkunde Karls IV. über ein Kurfürstenweistum, betreffend die kurfürstliche Gerichtshoheit`) [container]
      - text: Privileg Karls IV. für Trier über das Recht der ersten Stimme (`Privileg Karls IV. für Trier über das Recht der ersten Stimme (Jan. 1354)`) [container]
      - text: Karls IV. Transsumpt einer Urkunde seines Vaters über die pfälzische Kur [container]
    - … 28 more
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
1. `Das Lahntal von seinem Ursprung bis zur Ausmündung`: single, single, 1 parts, 0 missing, order –, validated
    - text: Das Lahntal von seinem Ursprung bis zur Ausmündung
1. `Die Musikalischen Zeitschriften seit ihrer Entstehung bis zur Gegenwart`: single, single, 1 parts, 0 missing, order –, validated
    - text: Die Musikalischen Zeitschriften seit ihrer Entstehung bis zur Gegenwart
1. `Kurzgefaßte Einleitung in die heiligen Schriften (11. Auflage)`: subpages, collection, 12 parts, 0 missing, order scan, validated
    - front: Kurzgefaßte Einleitung in die heiligen Schriften (11. Auflage)
    - volume: Inhaltsverzeichnis (`Kurzgefaßte Einleitung in die heiligen Schriften (11. Auflage)/Inhaltsverzeichnis`) [container]
      - text: Vorbemerkungen (`Kurzgefaßte Einleitung in die heiligen Schriften (11. Auflage)/Vorbemerkungen`) [container]
      - chapter: I. Allgemeiner Teil. (`Kurzgefaßte Einleitung in die heiligen Schriften (11. Auflage)/Allgemeiner Teil (AT)`) [container]
      - chapter: II. Spezieller Teil. (`Kurzgefaßte Einleitung in die heiligen Schriften (11. Auflage)/Erste Abteilung (AT)`) [container]
      - text: Zweite Abteilung: Die poetischen Bücher (`Kurzgefaßte Einleitung in die heiligen Schriften (11. Auflage)/Zweite Abteilung (AT)`) [container]
      - text: Dritte Abteilung: Die prophetischen Bücher (`Kurzgefaßte Einleitung in die heiligen Schriften (11. Auflage)/Dritte Abteilung (AT)`) [container]
      - chapter: Anhang: Die Apokryphen des A. Testaments (`Kurzgefaßte Einleitung in die heiligen Schriften (11. Auflage)/Anhang: Die Apokryphen des A. Testaments`) [container]
      - chapter: I. Allgemeiner Teil. (`Kurzgefaßte Einleitung in die heiligen Schriften (11. Auflage)/Allgemeiner Teil (NT)`) [container]
      - chapter: II. Spezieller Teil. (`Kurzgefaßte Einleitung in die heiligen Schriften (11. Auflage)/Erste Abteilung (NT)`) [container]
      - text: Zweite Abteilung: Die apostolischen Briefe (`Kurzgefaßte Einleitung in die heiligen Schriften (11. Auflage)/Zweite Abteilung (NT)`) [container]
      - text: Dritte Abteilung: Die Prophetie des Neuen Testaments (`Kurzgefaßte Einleitung in die heiligen Schriften (11. Auflage)/Dritte Abteilung (NT)`) [container]
    - … 1 more
1. `Niederrheinische Musik-Zeitung`: single, single, 1 parts, 0 missing, order –, unknown
    - text: Niederrheinische Musik-Zeitung
1. `Das Markgräflerland`: single, single, 1 parts, 0 missing, order –, unknown
    - text: Das Markgräflerland
1. `Künstler, Kunst, Kunstgeschichte`: single, single, 1 parts, 0 missing, order –, proofread
    - text: Künstler, Kunst, Kunstgeschichte
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
1. `Nord und Süd`: linked, collection, 2 parts, 1 missing, order toc, validated
    - front: Nord und Süd
    - text: Ein Aufsatz von Heinrich von Kleist [container]
    - text: Parlamentarismus [container]
1. `Beschreibung des Oberamts Ellwangen`: subpages, collection, 37 parts, 2 missing, order chain, validated
    - front: Beschreibung des Oberamts Ellwangen
    - text: Lage und Umfang (`Beschreibung des Oberamts Ellwangen/Kapitel A 1`) [container]
    - text: Natürliche Beschaffenheit (`Beschreibung des Oberamts Ellwangen/Kapitel A 2`) [container]
    - text: Einwohner (`Beschreibung des Oberamts Ellwangen/Kapitel A 3`) [container]
    - text: Wohnorte (`Beschreibung des Oberamts Ellwangen/Kapitel A 4`) [container]
    - text: Nahrungsstand (`Beschreibung des Oberamts Ellwangen/Kapitel A 5`) [container]
    - text: Gesellschaftlicher Zustand (`Beschreibung des Oberamts Ellwangen/Kapitel A 6`) [container]
    - text: Geschichtlicher Überblick und Alterthümer (`Beschreibung des Oberamts Ellwangen/Kapitel A 7`) [container]
    - text: Ellwangen (`Beschreibung des Oberamts Ellwangen/Kapitel B 1`) [container]
    - text: Benzenzimmern (`Beschreibung des Oberamts Ellwangen/Kapitel B 2`) [container]
    - text: Bühlerthann (`Beschreibung des Oberamts Ellwangen/Kapitel B 3`) [container]
    - text: Bühlerzell (`Beschreibung des Oberamts Ellwangen/Kapitel B 4`) [container]
    - … 26 more
1. `Kleiner Briefkasten (Die Gartenlaube 1881)`: single, single, 1 parts, 0 missing, order –, validated
    - text: Kleiner Briefkasten (Die Gartenlaube 1881)
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

### G: reference works (0)


### H: deep nesting (9)

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
1. `Suomalais-ugrilaisen Seuran Toimituksia `: virtual, collection, 6 parts, 0 missing, order single, validated
    - volume:  Mémoires de la Société Finno-ougrienne (`Suomalais-ugrilaisen Seuran Toimituksia / Mémoires de la Société Finno-ougrienne`)
      - volume: Vergleichende Märchenforschungen [container]
        - text: Das märchen vom zauberring (`Vergleichende Märchenforschungen/Das Märchen vom Zauberring`) [container]
        - text: Die drei zaubergegenstände und die wunderbaren früchte (`Vergleichende Märchenforschungen/Die drei Zaubergegenstände und die wunderbaren Früchte`) [container]
        - text: Das märchen vom zaubervogel (`Vergleichende Märchenforschungen/Das Märchen vom Zaubervogel`) [container]
      - text: Beiträge zur frage nach dem verhältnis zwischen den morgen- und abendländischen märchen (`Beiträge zur Frage nach dem Verhältnis zwischen den morgen- und abendländischen Märchen`) [container]
1. `Suomalais-Ugrilaisen Seuran Aikakauskirja `: virtual, collection, 9 parts, 0 missing, order single, validated
    - volume: Suomalais-Ugrilaisen Seuran Aikakauskirja / Journal de la Société Finno-Ougrienne
      - volume: Bär (Wolf) und Fuchs [container]
        - chapter: Einleitung (`Bär (Wolf) und Fuchs/Einleitung`) [container]
        - text: Das Fuchsmärchen (`Bär (Wolf) und Fuchs/Das Fuchsmärchen`) [container]
        - text: Bär (Wolf) und Fuchs (`Bär (Wolf) und Fuchs/Bär (Wolf) und Fuchs`) [container]
        - text: Nachtrag (`Bär (Wolf) und Fuchs/Nachtrag I`) [container]
        - text: „ (`Bär (Wolf) und Fuchs/Nachtrag II`) [container]
        - text: Bücherverzeichnis (`Bär (Wolf) und Fuchs/Bücherverzeichnis`) [container]
        - text: Bedeutung der Buchstabenzeichen (`Bär (Wolf) und Fuchs/Bedeutung der Buchstabenzeichen`) [container]
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

### I: works with other editions (49)

1. `Gedichte (Theodor Fontane)`: linked, collection, 58 parts, 0 missing, order chain, validated
    - front: Gedichte (Theodor Fontane)
    - text: Guter Rath (`Guter Rath (Fontane)`) [container]
    - text: Der erste Schnee (`Der erste Schnee (Fontane)`) [container]
    - text: Das Fischermädchen (`Das Fischermädchen (Fontane)`) [container]
    - text: Mein Herz [container]
    - text: Herbstmorgen [container]
    - text: An Marie [container]
    - text: Im Ilsethal [container]
    - text: Der Frühling an den Gefangnen [container]
    - text: Im Herbst (`Im Herbst (Fontane)`) [container]
    - text: Wunsch [container]
    - text: Nah und fern [container]
    - … 47 more
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
1. `Hypochonders Mondlied (1838)`: single, single, 1 parts, 0 missing, order –, validated
    - text: Hypochonders Mondlied (1838)
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
1. `Kennst du das Land, wo die Citronen blühn`: single, single, 1 parts, 0 missing, order –, validated
    - text: Kennst du das Land, wo die Citronen blühn
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
1. `Kennst du das Land? wo die Citronen blühn`: single, single, 1 parts, 0 missing, order –, validated
    - text: Kennst du das Land? wo die Citronen blühn
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
1. `Der Weihnachtsabend`: single, single, 1 parts, 0 missing, order –, validated
    - text: Der Weihnachtsabend
1. `Van dantzen`: single, single, 1 parts, 0 missing, order –, validated
    - text: Van dantzen
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
1. `Kinder- und Haus-Märchen Band 2 (1840)`: linked, collection, 101 parts, 0 missing, order chain, validated
    - front: Kinder- und Haus-Märchen Band 2 (1840)
    - text: Der Arme und der Reiche (`Der Arme und der Reiche (1840)`) [container]
    - text: Das singende springende Löweneckerchen (`Das singende springende Löweneckerchen (1840)`) [container]
    - text: Die Gänsemagd (`Die Gänsemagd (1840)`) [container]
    - text: Der junge Riese (`Der junge Riese (1840)`) [container]
    - text: Dat Erdmänneken (`Dat Erdmänneken (1840)`) [container]
    - text: Der König vom goldenen Berg (`Der König vom goldenen Berg (1840)`) [container]
    - text: Die Rabe (`Die Rabe (1840)`) [container]
    - text: Die kluge Bauerntochter (`Die kluge Bauerntochter (1840)`) [container]
    - text: Der alte Hildebrand (`Der alte Hildebrand (1840)`) [container]
    - text: De drei Vügelkens (`De drei Vügelkens (1840)`) [container]
    - text: Das Wasser des Lebens (`Das Wasser des Lebens (1840)`) [container]
    - … 90 more
1. `Der große Stein bei Görlitz`: single, single, 1 parts, 0 missing, order –, validated
    - text: Der große Stein bei Görlitz

