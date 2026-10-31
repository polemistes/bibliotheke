# de.wikisource: structure and quality survey (WP1a)

Generated 2026-11-01 by `python -I -m wscatalog.survey de` from `dewikisource-20261001-pages-articles.xml.bz2` (resolution and quality rules: PLAN.md 5.2 steps 1–1h and 5.4; config `pipeline/config/de.yaml`). Resolution took 9 s on the cached extract.

Input: 310,980 ns0 pages, 13,381 ns0 redirects, 339,510 scan pages, 7,112 Index pages, 773 pages with the `disambiguation` page prop.

## 1. Works

19,660 works resolved from 79,949 non-excluded ns0 pages; 17,391 enter the catalog after the hard exclusions (section 9). Multi-part works hold 60,335 parts, of which 53,791 are searchable texts.

| structure | works | parts | texts |
|---|---|---|---|
| single | 18,706 | 18,706 | 0 |
| linked | 447 | 22,486 | 21,547 |
| subpages | 353 | 10,017 | 7,530 |
| mixed | 108 | 27,689 | 24,604 |
| virtual | 46 | 143 | 110 |

| type (step 1e) | works |
|---|---|
| single | 18,706 |
| collection | 818 |
| book | 136 |

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
| Schwere, Elektricität und Magnetismus | mixed | collection | 536 | 523 | 0 | toc 412, container 114, hierarchy 10 |
| Die Gartenlaube (1887) | mixed | collection | 525 | 471 | 0 | toc 472, hierarchy 53 |
| Die Gartenlaube (1888) | mixed | collection | 522 | 470 | 37 | toc 470, hierarchy 52 |
| Topographia Circuli Burgundici | mixed | collection | 509 | 499 | 0 | toc 508, hierarchy 1 |
| Die Gartenlaube (1854) | mixed | collection | 491 | 438 | 1 | toc 439, hierarchy 52 |
| Geschichte von Kloster Heilsbronn | subpages | collection | 484 | 482 | 0 | hierarchy 484 |

## 2. Exclusions (step 1)

| reason | pages |
|---|---|
| reference-prefix | 218,464 |
| author-page | 8,822 |
| excluded-template | 1,763 |
| disambiguation | 773 |
| under-excluded | 668 |
| no-header | 488 |
| excluded-category | 53 |

## 3. Patterns A–I (WS§1)

|  | pattern | count |
|---|---|---|
| A | collection of root pages (`structure = linked`) | 447 works, 22,486 parts |
| B | mixed subpages and root pages (`mixed`) | 108 works, 27,689 parts |
| C | work with an oversized page (> oversized_bytes, transcluded scan pages included) | 317 works |
| D | missing root, virtual root (`virtual`) | 46 works, 143 parts |
| E | texts inside a subpage collection reached from a disambiguation or versions page | 26 parts (24 searchable); all searchable subpage texts: 8,097 |
| F | whole-text duplicate subpages (excluded) | 0 pages |
| G | reference works (type `reference`) | 0 works, 0 parts; 218,464 pages excluded by title prefix |
| H | deep nesting (parts at depth ≥ 2, i.e. three levels below the root) | 7 works |
| I | several editions: works with an other-versions template | 49 works; 0 versions pages excluded |

Tree depth of multi-part works (levels below the root): 1: 867, 2: 80, 3: 5, 4: 2.

## 4. Parent rules and order sources (steps 1b, 1c)

| parent rule | pages |
|---|---|
| container | 29,100 |
| toc | 24,432 |
| own | 19,614 |
| hierarchy | 6,803 |

| order source (nodes with ≥ 2 children) | nodes |
|---|---|
| chain | 561 |
| toc | 221 |
| toc-partial | 115 |
| natural | 63 |
| scan | 32 |
| scan-volumes | 9 |

`toc-partial` (implementation choice, see section 13): the TOC lists at least half of the children, which keep its order; the rest follow in natural order. 29 `natural` nodes have a TOC that lists fewer than half.

Root pages attached by their header's container link: 21,496; most frequent containers: `Der Sagenschatz des Königreichs Sachsen` 999; `Die zehnte Muse` 472; `Deutsche Sagen (Brüder Grimm, Band 1)` 360; `Journal von und für Franken` 354; `Badisches Sagen-Buch II` 352; `Die Volkssagen von Pommern und Rügen` 287; `Das Ausland (1828)` 268; `Badisches Sagen-Buch I` 246; `Buch der Lieder` 242; `Deutsche Sagen (Brüder Grimm, Band 2)` 223.

Pages attached by TOC membership (rule 3): 24,432; most frequent TOC pages: `Die Gartenlaube (1899)` 596; `Die Gartenlaube (1898)` 594; `Die Gartenlaube (1896)` 561; `Topographia Circuli Burgundici` 508; `Die Gartenlaube (1887)` 472; `Die Gartenlaube (1888)` 470; `Die Gartenlaube (1854)` 439; `Topographia Provinciarum Austriacarum` 434; `Die Gartenlaube (1878)` 426; `Die Gartenlaube (1886)` 423.

## 5. Container links that fail rule 1

692 of 29,795 pages whose header carries a container link (2.3 %) point to:

- outside-ns0: 494 (e.g. `Fridericus Rex` → `w:de:Die Weltbühne`, `Die Nacht (Büchner)` → `commons:Image:Georg Büchner - Franzos-Werkausgabe 395.jpg`, `Brunner im Amt` → `w:Die Weltbühne`, `Joebbels` → `w:de:Die Weltbühne`)
- excluded: 155 (e.g. `Altdresdner Familienleben` → `Dresdner Geschichtsverein`, `Sagen aus der Provinz Sachsen VII` → `Zeitschrift für Volkskunde`, `Clausthalischer allgemeiner Harz-Berg-Calender auf das Jahr 1805` → `Clausthal`, `Der Meßner zu Bamberg` → `Alexander Schöppner`)
- missing: 43 (e.g. `Knecht Ruprecht` → `:Bild:De Storm Gedichte 1889 143.gif`, `Agrumi/Der Geliebten` → `Agrumi`, `Ueber die Haide` → `:Bild:De Storm Gedichte 1889 177.gif`, `Allgemeiner Harz-Berg-Kalender für das Jahr 1953` → `Clausthal-Zellerfeld`)

## 6. Prev/next chains across Index boundaries

Of 23,546 next-links between pages that both have an Index, 3,169 (13.5 %) cross to a different Index.
Examples: `TBHB 1953-03-31` (TBHB 1953-03-31) → `TBHB 1953-04-01` (TBHB 1953-04-01); `TBHB 1943-03-22` (TBHB 1943-03-22) → `TBHB 1943-03-28` (TBHB 1943-03-28); `TBHB 1951-08-01` (TBHB 1951-08-01) → `TBHB 1951-08-02` (TBHB 1951-08-02); `TBHB 1935-05-13` (TBHB 1935-05-13) → `TBHB 1935-05-14` (TBHB 1935-05-14).

Root-page works that sit on a prev/next chain but stay separate works (no container, not in a unique TOC; WS§8 item 5): 3,358.

## 7. Orphan trees, oversized pages, red links

**Orphan trees** (virtual roots): 46, holding 143 parts. Examples: `Heinrich Heine` (2); `In Friedrich Rückert’s Haus` (2); `Aus den politischen Salons des neuen Italiens` (1); `Seegeschichten` (1); `Agrumi` (4); `Aus den Arbeitssälen des Kunsthandwerks` (2); `Zwanglose Blätter(1883/9)` (1); `Thierstudien eines Laien` (2).

**Oversized pages** (> 300,000 bytes of wikitext including transcluded scan pages): 549. Largest: `Ahrenshoop vor und im Krieg` 4.0 MB; `Weg nach Berlin – Birkenwerder` 2.2 MB; `Kunst und Kultur in Ahrenshoop 1945 bis 1948` 2.1 MB; `Paulys Realencyclopädie der classischen Altertumswissenschaft/Register/Hans Gärtner` 2.0 MB; `Jugend (Zeitschrift)/Inhalt 1896–1910` 1.9 MB; `Jugend (Zeitschrift)/Inhalt 1911–1929` 1.8 MB; `Hans Brass und die Religion, 1934` 1.5 MB; `Paulys Realencyclopädie der classischen Altertumswissenschaft/Register/R` 1.5 MB; `Der Merker/Inhalt` 1.5 MB; `Allgemeine Wiener Musik-Zeitung` 1.5 MB.

**Red links in TOCs** (missing parts): 151 multi-part works have red links, 982 in total; median share of the TOC 6%. Most missing: `Die Gartenlaube (1899)` 226 of 850; `Gesammelte Schriften über Musik und Musiker` 114 of 237; `Die Gartenlaube (1898)` 91 of 715; `Die Gartenlaube (1896)` 77 of 690; `Die Gartenlaube (1888)` 37 of 559; `Illustrirte Zeitung` 35 of 115; `Die Grenzboten (1841/1842)` 27 of 52; `Schwäbische Chronik (Thomas Lirer)` 23 of 24.

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
| Hantzsch: | 390 | excluded |
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

**Hard exclusions**: 2,269 works — bibliography 2,029, under-300-bytes 227, no-text 13.

| tier | works | share | texts in them | source |
|---|---|---|---|---|
| validated | 9,836 | 56.6 % | 43,319 | page-levels 5,111, status-template 4,725 |
| proofread | 6,999 | 40.2 % | 8,505 | status-template 4,907, page-levels 2,092 |
| unproofread | 266 | 1.5 % | 211 | status-template 157, page-levels 109 |
| unknown | 290 | 1.7 % | 1,756 | none 290 |

14 works are proofread by the ≥ 90 % rule. 229 of the 266 unproofread works have no page above level 1 (raw OCR).

**Incomplete**: 2,169 works — template 1,991, missing-parts 168, missing-pages 10.
Works with missing scan pages inside a transcluded range: 15 (77 pages).
1 works transclude scan pages of files the dump has no Page: pages for (counted as no signal, not as missing).
Wikisource's own translations (labelled, kept): 0.

Status values (`bearbeitungsstand`, `status`): `fertig` 50,522, `korrigiert` 9,015, `unvollständig` 2,040, `unkorrigiert` 342, `unvollständig (geplant)` 60, `off` 25, `teilkorrigiert` 4, `korrekturprobleme` 3, `fehler korr.` 1.

**Unknown works** (no signal): 290 in the catalog after the junk rules; the junk rules removed 227 (under-300-bytes 227).
Size: median 3,121 bytes, p10 1,117, p90 57,524; multi-part 45.

30 random unknown works:

- `Ersch-Gruber:Lappenberg (Johann Martin)` (single, 22,476 B): Lappenberg (Johann Martin) , Geschichtsforscher, geboren zu Hamburg am 30. Juli 1794, gestorben daselbst am 28. Nov. 1865, stammte aus einer Familie, in der sch
- `Die Siegel der Deutschen Kaiser und Könige:Band 3:Seite 16` (single, 3,063 B): __NOTOC__ valign="top" style="text-align:justify; padding-left:10px; padding-right:10px;" \| 3. Derselbe. === Or. Stadtarchiv Frankfurt a. M., Kaiserschreiben 8.
- `Olla Potrida` (subpages, 171,358 B): Olla Potrida. Weversche Buchhandlung, Berlin 1778–1797 ab 1796 vereint mit: Das Merkwürdigste aus den neuesten und besten Reisebeschreibungen : mit einem Anhang
- `Allgemeiner Harz-Berg-Kalender für das Jahr 1934` (single, 9,370 B): border="0" cellpadding="2" cellspacing="2" style="margin:1em 1em 1em 0; border:1px #AAA solid; border-collapse:collapse; empty-cells:show;" colspan="6"\| Seite T
- `Ausführliche Vorschriften zur Blitz-Ableitung an allerley Gebäuden:Seite 19` (single, 1,848 B): width="100%" valign="top" style="text-align:justify; padding-left:10px; padding-right:10px;"\| nehme nur, wo es nöthig ist, Metalldrath dazu, und verbinde die üb
- `Allgemeine Bibliographie für Deutschland : monatliches Verzeichniss der wichtigern neuen Erscheinungen der deutschen und ausländischen Literatur` (single, 7,281 B): Allgemeine Bibliographie für Deutschland , Leipzig : Brockhaus 1.1836 - 7.1842[?] 1.1836 = (Teil 1, H. 1-26) + (Teil 2, H. 27-52); = (Gesamtband); = 2.1837 = ; 
- `Die Siegel der Deutschen Kaiser und Könige:Band 2:Seite 19` (single, 3,033 B): __NOTOC__ valign="top" style="text-align:justify; padding-left:10px; padding-right:10px;" \| Tafel 43. == 1. Derselbe. === Or. Stiftsarchiv Naumburg. 1074. St. 2
- `Ausführliche Vorschriften zur Blitz-Ableitung an allerley Gebäuden:Seite 46` (single, 584 B): width="100%" valign="top" style="text-align:justify; padding-left:10px; padding-right:10px;"\| Wie der Mast verletzt worden, und an welchen Theilen? Ob einige St
- `Ausführliche Vorschriften zur Blitz-Ableitung an allerley Gebäuden:Seite 6` (single, 1,709 B): width="100%" valign="top" style="text-align:justify; padding-left:10px; padding-right:10px;"\| Es ist also fürs Erste die ganze First, bis über die Enden hin, wi
- `Mappe : die Malerzeitschrift` (single, 11,628 B): Mappe : die Malerzeitschrift . Geislingen/Steige : C. Maurer Fachmedien GmbH & Co. KG 1.1878 - 2.1879; [N.F.] 1.1881 - 30.1910/11; 56.1936/37 - 62.1942/43,März;
- `Die Siegel der Deutschen Kaiser und Könige:Band 2:Seite 5` (single, 2,998 B): __NOTOC__ valign="top" style="text-align:justify; padding-left:10px; padding-right:10px;" \| 6. Derselbe. === Or. Stadtarchiv Frankfurt a. M. 1376 Juni 30. Umsch
- `Preußisches Obertribunal - Rose` (virtual, 36,925 B): No 47. (Crimin.-S. Nr. 6) Mord. Theilnahme durch Anstiftung und Hülfeleistung. Irrthum in der Person des Getödteten. Ist bei dem Morde ein Seitens des Thäters v
- `Deutsches Reichsgesetzblatt 1945` (single, 18,298 B): __TOC__ Titel== Anmerkung WS: Titelblatt für Teil I und Teil II nicht vorhanden. Inhaltsverzeichnis== Teil I=== border=2 cellpadding=2 cellspacing=2 width="100%
- `Die deutschen Kaiser:Karl der Große` (single, 1,655 B): colspan="2" align="center" \| 200px\|Karl der Große colspan="2" align="center" valign="top"\| Karl der Große. 768-814. valign="top"\| Karl gründete das deutsche Rei
- `Ersch-Gruber:Copet` (single, 752 B): COPET (Coppet) , ein Flecken mit einem über demselben gelegenen schönen Schlosse, eine Stunde von Genf entfernt, führt den Titel einer Baronie und gehört zu den
- `Ausführliche Vorschriften zur Blitz-Ableitung an allerley Gebäuden:Seite 32` (single, 1,931 B): width="100%" valign="top" style="text-align:justify; padding-left:10px; padding-right:10px;"\| der Anfall des Blitzes oben durch eine Auffangungs-Stange vom Dach
- `Schrift und Schrifttum:13` (single, 4,664 B): valign="top" style="text-align:justify; padding-left:10px; padding-right:10px;" \| nie aufgehört Buchstaben zu sein, die zur Bezeichnung von Zahlwerten dienten u
- `Ersch-Gruber:Landeshoheit` (single, 18,682 B): LANDESHOHEIT. Das Wort Landeshoheit, Landesherrlichkeit, wird vielfach als identisch mit Souveränetät gebraucht und bezeichnet so den Inbegriff der dem Oberhaup
- `Schrift und Schrifttum:15` (single, 4,594 B): valign="top" style="text-align:justify; padding-left:10px; padding-right:10px;" \| überrascht werden können und in jedem Fall auf das geführt werden, was für die
- `Kirchenbauwesen (Großh Hess)(1820)` (single, 24,951 B): class="wikitable" Editionsrichtlinien: Als Grundlage dienen die Wikisource:Editionsrichtlinien. Für die Paragrafen werden nicht die Editionsrichtlinien für „Ges
- `Hermes:Band 53` (single, 3,779 B): Inhalt == class="sortable" align="left"\| align="right"\| Seite align="left"\|M. BANG, die Grabschrift des Philosophen Iulianus align="right"\|211 align="left"\|H. D
- `Ausführliche Vorschriften zur Blitz-Ableitung an allerley Gebäuden:Seite 25` (single, 1,835 B): width="100%" valign="top" style="text-align:justify; padding-left:10px; padding-right:10px;"\| deckten Thurm, der keine Eckgräten hat, Metallplatten nicht wohl a
- `Allgemeiner Harz-Berg-Kalender` (subpages, 20,318 B): ... 1780 == Scans bei Commons Inhaltsverzeichnis Indexseite Existierende Artikel 1805 == Scans bei Commons Inhaltsverzeichnis Indexseite Existierende Artikel 18
- `Jahresbericht der Deutschen Mathematiker-Vereinigung` (subpages, 286,504 B): Für Links auf diese Übersichtsseite steht die Vorlage Vorlage:JBMV zur Verfügung. Inhalt == Inhalt der Bände Digitalisate == Band 10 1. Heft: 1909 GDZ Göttingen
- `Hinter den Kulissen des Lebens` (single, 3,777 B): Maria Lazar: Hinter den Kulissen des Lebens „Der Unentrinnbare“, Roman von Viktor Dirsztay mit Zeichnungen von O. Kokoschka, Kurt Wolff Verlag, München. Ein ung
- `Ausführliche Vorschriften zur Blitz-Ableitung an allerley Gebäuden:Seite 43` (single, 1,472 B): width="100%" valign="top" style="text-align:justify; padding-left:10px; padding-right:10px;"\| Ob er bis in Keller, oder bis in die Erde eingedrungen sey – wie t
- `Die Siegel der Deutschen Kaiser und Könige:Band 2:Seite 6` (single, 3,235 B): __NOTOC__ valign="top" style="text-align:justify; padding-left:10px; padding-right:10px;" \| 6. Derselbe. === Or. Geh. Staatsarchiv Berlin. 1374 Mai 28. Umschrif
- `Allgemeiner Harz-Berg-Kalender für das Jahr 1918` (single, 9,644 B): border="0" cellpadding="2" cellspacing="2" style="margin:1em 1em 1em 0; border:1px #AAA solid; border-collapse:collapse; empty-cells:show;" colspan="6"\| Seite T
- `Briefe und Bilder aus dem Großherzogtum Baden und Elsaß` (subpages, 744 B): Bände == /Band 1/ /Band 2/
- `Die deutschen Kaiser:Rudolf von Habsburg und Adolf von Nassau` (single, 1,925 B): align="center"\|200px\|Rudolf von Habsburg align="center"\|200px\|Adolf von Nassau align="center" valign="top"\|Rudolf von Habsburg. 1273–1291. align="center" valign

**Default view** (validated or proofread, complete, not reference): 14,736 works with 39,818 texts; with unknown works shown: 14,994 works (41,152 texts). Works in the default view whose pages are all level 1: 0. Current setting: `show_unknown_by_default = False`.

## 10. Parts table size (WS§5.2)

| parts_cap | works with catalog parts | parts rows | parts table, raw SQLite | parts table, gzipped | parts_fts, gzipped |
|---|---|---|---|---|---|
| 500 | 941 | 52,147 | 8.5 MB | 2.89 MB | 0.82 MB |
| 2,000 (decided) | 954 | 61,243 | 10.1 MB | 3.36 MB | 0.94 MB |
| ∞ (all, reference works included) | 954 | 61,243 | 10.1 MB | 3.36 MB | 0.94 MB |
| 2,000, index_title as text and display_title always set (WS§5.1 as first written) | 954 | 61,243 | 12.1 MB | 3.56 MB | 0.94 MB |
| 2,000, page_title as suffix of the root title (deferred) | 954 | 61,243 | 9.0 MB | 3.30 MB | 0.94 MB |

Rows count multi-part works only, one row per part plus the root row. 16,437 single-page works would add one row each if stored. Sizes are a SQLite file holding only the table (with its index); gzipped at level 9. The decided encoding (2026-11-01, STATUS.md item 2) stores `index_ref` as the position of the part's Index in `works.index_titles` and `display_title` as NULL when it equals the last segment of `page_title`; per-part `quality_pages` stays. The suffix encoding (page_title stored as the suffix after the work root's title) halves the raw size and is deferred: it complicates open-by-URL and link rewriting.

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
- Decided 2026-11-01 (report/wp1a-decisions.md): with `headerless_toc_members` (de), a page without header template and status that is listed by exactly one TOC-like page is its member regardless of Index or chain, and a root page with a header and an Index that lists at least three such pages is TOC-like (page-by-page books, the Topographia volumes); a work over `reference_threshold` without an Index type is `reference` only when its children have no chain or TOC order; a faulty Index progress code excludes a work only when none of its pages is proofread; coverage counts only the Index's text pages that no other work transcludes and tolerates `coverage_slack` untranscluded pages; a single-page root whose only header is a `bibliography_templates` template (de `Zeitschrift`), without scans or status, is excluded as `bibliography`.

## 12. Random samples for eyeballing (30 per pattern)

### A: collections of root pages (447)

1. `Album der Sächsischen Industrie`: linked, collection, 187 parts, 0 missing, order toc, validated
    - toc: Album der Sächsischen Industrie
    - volume: Album der Sächsischen Industrie Band 1 [container]
      - text: Ueber die Industrie im Allgemeinen und die sächsische ins Besondere [container]
      - text: Kurzer Ueberblick über die Industriezweige der Stadt Chemnitz [container]
      - text: Die Maschinen-Fabrik von Richard Hartmann in Chemnitz [container]
      - text: Die Flachsspinnerei von H. C. Müller in Hirschfelde [container]
      - text: Die Königin Marienhütte zu Niederkainsdorf bei Zwickau [container]
      - text: Ueber Freiberg und seinen Bergbau im Allgemeinen [container]
      - text: Himmelfahrt sammt Abraham Fundgrube [container]
      - text: Der Churprinz Friedrich August Erbstolln [container]
      - text: Die Glas- und Chemische Fabrik von Fr. Chr. Fikentscher bei Zwickau [container]
      - text: Die Orleans-Fabrik von Schmitt und Esche in Zittau [container]
    - … 176 more
1. `Traumgekrönt`: linked, collection, 3 parts, 0 missing, order toc, validated
    - front: Traumgekrönt
    - text: Königslied [container]
    - text: Träumen [container]
    - text: Lieben [container]
1. `Zbiór wierszy o wilamowskich obrzędach i obyczajach oraz Słownik języka wilamowskiego`: linked, collection, 21 parts, 0 missing, order chain, validated
    - toc: Zbiór wierszy o wilamowskich obrzędach i obyczajach oraz Słownik języka wilamowskiego
    - chapter: Vorwort (`Zbiór wierszy o wilamowskich obrzędach i obyczajach/Vorwort`) [container]
    - text: Eine kurze Geschichte von Wilmesau (`Zbiór wierszy o wilamowskich obrzędach i obyczajach/Eine kurze Geschichte von Wilmesau`) [container]
    - text: Unser gesegneter Abp Józef Bilczewski (`Zbiór wierszy o wilamowskich obrzędach i obyczajach/Unser gesegneter Abp Józef Bilczewski`) [container]
    - text: Schon ein Jahr nach der Andacht des Gesegneten (`Zbiór wierszy o wilamowskich obrzędach i obyczajach/Schon ein Jahr nach der Andacht des Gesegneten`) [container]
    - text: Unser schönes Wilmesauer Landgut (`Zbiór wierszy o wilamowskich obrzędach i obyczajach/Unser schönes Wilmesauer Landgut`) [container]
    - text: Wilmesauer Federrupfen (`Zbiór wierszy o wilamowskich obrzędach i obyczajach/Wilmesauer Federrupfen`) [container]
    - text: Die Wilmesauer Smiergüsnika (`Zbiór wierszy o wilamowskich obrzędach i obyczajach/Die Wilmesauer Smiergüsnika`) [container]
    - text: Die Wilmesauer Trauung und Hochzeit (`Zbiór wierszy o wilamowskich obrzędach i obyczajach/Die Wilmesauer Trauung und Hochzeit`) [container]
    - text: Der Frühling (`Zbiór wierszy o wilamowskich obrzędach i obyczajach/Der Frühling`) [container]
    - text: Der Sommer (`Zbiór wierszy o wilamowskich obrzędach i obyczajach/Der Sommer`) [container]
    - text: Der Herbst (`Zbiór wierszy o wilamowskich obrzędach i obyczajach/Der Herbst`) [container]
    - … 10 more
1. `Rudolf Lavant Gedichte (Sammelband)`: linked, collection, 41 parts, 0 missing, order toc-partial, proofread
    - front: Rudolf Lavant Gedichte (Sammelband)
    - text: Die Flinte schießt, der Säbel haut [container]
    - text: Ludwig Uhland (`Ludwig Uhland (Gedicht)`) [container]
    - text: Nach zehn Jahren [container]
    - text: Drei Monate Fabrikarbeiter (`„Drei Monate Fabrikarbeiter“`) [container]
    - text: Den österreichischen Arbeitern [container]
    - text: Eherne Maiglocken [container]
    - text: Pro domo [container]
    - text: Wider die Reaktion [container]
    - text: Robert Blum (`Robert Blum (Gedicht)`) [container]
    - text: An die Frauen [container]
    - text: Bekenntnis (`Bekenntnis (Rudolf Lavant)`) [container]
    - … 30 more
1. `Erzählungen vom Oberharz in Oberharzer Mundart – Heft 7`: linked, book, 3 parts, 0 missing, order chain, proofread
    - front: Erzählungen vom Oberharz in Oberharzer Mundart – Heft 7
    - chapter: III. Noch d’r Militärzeit (`Anno 48: III. Noch d’r Militärzeit`) [container]
    - chapter: IV. Die Errichtung d’r Bargerwähr (`Anno 48: IV. Die Errichtung d’r Bargerwähr`) [container]
    - chapter: V. Gruße Befahring aus Klasthool, oder: Aend gut, Alles gut (`Anno 48: V. Gruße Befahring aus Klasthool, oder: Aend gut, Alles gut`) [container]
1. `Der lustige Ferdinand oder der Goldhirsch`: linked, collection, 90 parts, 0 missing, order single, validated
    - front: Der lustige Ferdinand oder der Goldhirsch
    - volume: Deutsche Volksmärchen aus Schwaben [toc]
      - text: Der Schäfer und die drei Riesen [container]
      - text: Das Vöglein auf der Eiche [container]
      - text: Der Räuber und die Hausthiere [container]
      - text: Aschengrittel [container]
      - text: Der kranke König und seine drei Söhne [container]
      - text: Donner, Blitz und Wetter [container]
      - text: Von drei Schwänen [container]
      - text: Die vier Brüder [container]
      - text: Die Schultheißen-Wahl [container]
      - text: Hans und der Teufel [container]
    - … 79 more
1. `Meyer’s Universum, oder Abbildung und Beschreibung des Sehenswerthesten und Merkwürdigsten der Natur und Kunst auf der ganzen Erde. Vierzehnter Band`: linked, collection, 48 parts, 0 missing, order chain, proofread
    - front: Meyer’s Universum, oder Abbildung und Beschreibung des Sehenswerthesten und Merkwürdigsten der Natur und Kunst auf der ganzen Erde. Vierzehnter Band
    - text: Das Monument auf Bunkershill bei Boston (`Das Monument auf Bunkershill bei Boston in den Vereinigten Staaten von Nordamerika`) [container]
    - text: Die Akademie der schönen Künste in Paris [container]
    - text: Rousseau’s Klause in Montmorency (`Rousseau’s Clause in Montmorency`) [container]
    - text: Schloss Nymphenburg bei München [container]
    - text: Die Elysée’schen Felder und der Präsidentschaftspalast in Paris [container]
    - text: Erlangen (`Erlangen (Meyer’s Universum)`) [container]
    - text: Civita Castellana in Italien [container]
    - text: Maria zum Schnee auf dem Rigi (`„Maria zum Schnee“ auf dem Rigi in der Schweiz`) [container]
    - text: St. Cloud [container]
    - text: Wittenberg (`Wittenberg (Meyer’s Universum)`) [container]
    - text: Autun in Frankreich [container]
    - … 37 more
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
1. `Gedichte (Bürger)`: linked, collection, 65 parts, 0 missing, order chain, validated
    - front: Gedichte (Bürger)
    - text: Die Nachtfeier der Venus [container]
    - text: An ein Maienlüftchen [container]
    - text: Lust am Liebchen [container]
    - text: Stuzertändelei [container]
    - text: Adeline [container]
    - text: An Arist [container]
    - text: Huldigungslied [container]
    - text: Das harte Mädchen [container]
    - text: An den Traumgott [container]
    - text: An die Hofnung [container]
    - text: Herr Bacchus (`Herr Bacchus (Bürger)`) [container]
    - … 54 more
1. `Kinder- und Haus-Märchen Band 2 (1850)`: linked, collection, 124 parts, 0 missing, order chain, validated
    - front: Kinder- und Haus-Märchen Band 2 (1850)
    - text: Der Arme und der Reiche (`Der Arme und der Reiche (1850)`) [container]
    - text: Das singende springende Löweneckerchen (`Das singende springende Löweneckerchen (1850)`) [container]
    - text: Die Gänsemagd (`Die Gänsemagd (1850)`) [container]
    - text: Der junge Riese (`Der junge Riese (1850)`) [container]
    - text: Dat Erdmänneken (`Dat Erdmänneken (1850)`) [container]
    - text: Der König vom goldenen Berg (`Der König vom goldenen Berg (1850)`) [container]
    - text: Die Rabe (`Die Rabe (1850)`) [container]
    - text: Die kluge Bauerntochter (`Die kluge Bauerntochter (1850)`) [container]
    - text: Der alte Hildebrand (`Der alte Hildebrand (1850)`) [container]
    - text: De drei Vügelkens (`De drei Vügelkens (1850)`) [container]
    - text: Das Wasser des Lebens (`Das Wasser des Lebens (1850)`) [container]
    - … 113 more
1. `Hermes:Band 13`: linked, collection, 1 parts, 3 missing, order single, validated
    - front: Hermes:Band 13
    - text: Die Familie des Germanicus [container]
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
1. `Archiv für Religionswissenschaft`: linked, collection, 1 parts, 0 missing, order single, validated
    - front: Archiv für Religionswissenschaft
    - text: Slavische Sagen vom Wechselbalg [container]
1. `Erzählungen vom Oberharz in Oberharzer Mundart – Heft 3`: linked, collection, 3 parts, 0 missing, order chain, validated
    - front: Erzählungen vom Oberharz in Oberharzer Mundart – Heft 3
    - text: Die neie Wasserleiting [container]
    - text: Schwarze Kunst [container]
    - text: Eisenbahn-Station „St. Andreasberg!“ [container]
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
1. `Kinder- und Haus-Märchen Band 2 (1815)`: linked, collection, 70 parts, 0 missing, order chain, validated
    - front: Kinder- und Haus-Märchen Band 2 (1815)
    - text: Der Arme und der Reiche (`Der Arme und der Reiche (1815)`) [container]
    - text: Das singende, springende Löweneckerchen (`Das singende, springende Löweneckerchen (1815)`) [container]
    - text: Die Gänsemagd (`Die Gänsemagd (1815)`) [container]
    - text: Von einem jungen Riesen (`Von einem jungen Riesen (1815)`) [container]
    - text: Dat Erdmänneken (`Dat Erdmänneken (1815)`) [container]
    - text: Der König vom goldenen Berg (`Der König vom goldenen Berg (1815)`) [container]
    - text: Die Rabe (`Die Rabe (1815)`) [container]
    - text: Die kluge Bauerntochter (`Die kluge Bauerntochter (1815)`) [container]
    - text: Der Geist im Glas (`Der Geist im Glas (1815)`) [container]
    - text: De drei Vügelkens (`De drei Vügelkens (1815)`) [container]
    - text: Das Wasser des Lebens (`Das Wasser des Lebens (1815)`) [container]
    - … 59 more
1. `Eichenlaub und Fichtenreis (Sammelband)`: linked, collection, 60 parts, 3 missing, order chain, validated
    - front: Eichenlaub und Fichtenreis (Sammelband)
    - text: Es soll sich grübeln nicht versenken [container]
    - text: Was gelenkt in jungen Tagen [container]
    - text: Das Grübeln und das Härmen [container]
    - text: Du füllst mit lichtem Schimmer [container]
    - text: Goldne Jugendtage [container]
    - text: Nun schimmert in den Zweigen [container]
    - text: Nach langem frohem Ringen [container]
    - text: Wenn nicht der rettende Gedanke [container]
    - text: Was in den Turnerherzen [container]
    - text: In ernster feierlicher Stunde [container]
    - text: Aus dem Bund, den wir geschlossen [container]
    - … 49 more
1. `Siebzig Gedichte`: linked, collection, 70 parts, 0 missing, order chain, validated
    - toc: Siebzig Gedichte
    - text: Hymnus an die Bäume [container]
    - text: Einem Sommer [container]
    - text: Ausflug (`Ausflug (Otto Ernst)`) [container]
    - text: Auflösung [container]
    - text: Spaziergang (`Spaziergang (Otto Ernst)`) [container]
    - text: Winternachmittag an der Elbe [container]
    - text: Wintermärchen [container]
    - text: Allein im Dunkel [container]
    - text: Der Einsame [container]
    - text: Der Ruf [container]
    - text: Goethe und Tasso [container]
    - … 59 more
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
1. `Meyer’s Universum, oder Abbildung und Beschreibung des Sehenswerthesten und Merkwürdigsten der Natur und Kunst auf der ganzen Erde. Achtzehnter Band`: linked, collection, 43 parts, 0 missing, order chain, proofread
    - toc: Meyer’s Universum, oder Abbildung und Beschreibung des Sehenswerthesten und Merkwürdigsten der Natur und Kunst auf der ganzen Erde. Achtzehnter Band
    - text: Die Straße über den Splügen (2 Ansichten) (`Die Straße über den Splügen`) [container]
    - text: König Johann’s I. Grabmal in Batalha [container]
    - text: Das großherzogliche Schloß in Schwerin [container]
    - text: Leon in Mittel-Amerika [container]
    - text: Kastell und Moschee der Semiramis bei Wan [container]
    - text: Die Fälle des Genessee bei Rochester (`Die Fälle des Genessee bei Rochester in Nordamerika`) [container]
    - text: Schloß Leopoldskron bei Salzburg (`Das Schloß Leopoldskron bei Salzburg`) [container]
    - text: Burg und Bad Liebenstein (`Burg und Bad Liebenstein im Herzogthum Meiningen`) [container]
    - text: Das Grab der Maria [container]
    - text: Ruinen von Babylon [container]
    - text: Schloß Willanow bei Warschau [container]
    - … 32 more
1. `Deutsche Hausmärchen`: linked, collection, 51 parts, 0 missing, order chain, validated
    - front: Deutsche Hausmärchen
    - text: Der Fischerssohn, der Rappe und der Schimmel [container]
    - text: Das Schneiderlein und die drei Hunde [container]
    - text: Die Prinzessin von Tiefenthal [container]
    - text: Von den achtzehen Soldaten [container]
    - text: Das goldne Königreich [container]
    - text: Die Königstochter im Berge Muntserrat [container]
    - text: Die schlechten Kameraden [container]
    - text: Der goldene Hirsch [container]
    - text: Der Metzgergesell [container]
    - text: Der graue Wackenstein [container]
    - text: Die getreue Frau [container]
    - … 40 more
1. `Schleswig-Holsteinische Sagen`: linked, collection, 9 parts, 0 missing, order chain, validated
    - toc: Schleswig-Holsteinische Sagen
    - text: Die Abelssage [container]
    - text: Die schwarze Greth [container]
    - text: Das Glück der Grafen Rantzau [container]
    - text: Schacken-Sage [container]
    - text: Bredstedter Sage [container]
    - text: Friesische Sage [container]
    - text: Das liebe Brodt [container]
    - text: Die Unterirdischen [container]
    - text: Glocken im See [container]
1. `Schriften des Vereins für Geschichte des Bodensees und seiner Umgebung`: linked, collection, 1 parts, 0 missing, order single, validated
    - front: Schriften des Vereins für Geschichte des Bodensees und seiner Umgebung
    - text: Ravensburg und sein Verkehrsleben in den letzten dreihundert Jahren [container]
1. `Briefe eines Leipziger Studenten (1572-1574)`: linked, collection, 1 parts, 1 missing, order single, validated
    - front: Briefe eines Leipziger Studenten (1572-1574)
    - text: Briefe eines Leipziger Studenten aus den Jahren 1572 bis 1574 [toc]
1. `Die malerischen und romantischen Stellen des Odenwaldes in ihrer Vorzeit und Gegenwart`: linked, collection, 2 parts, 12 missing, order natural, validated
    - front: Die malerischen und romantischen Stellen des Odenwaldes in ihrer Vorzeit und Gegenwart
    - text: Der Burggeist Rodenstein [container]
    - text: Legende von der heiligen Hildegunde [container]
1. `Sagen aus der Provinz Sachsen III`: linked, collection, 6 parts, 0 missing, order chain, validated
    - toc: Sagen aus der Provinz Sachsen III
    - text: Das Johannisopfer [container]
    - text: Die Klage [container]
    - text: Die weisse Gestalt [container]
    - text: Das Hühnenbett [container]
    - text: Das Hühnenschwert [container]
    - text: Die alte Turmmauer [container]
1. `Dideldum!`: linked, collection, 13 parts, 0 missing, order chain, validated
    - toc: Dideldum!
    - text: Individualität [container]
    - text: Wankelmuth [container]
    - text: Trinklied (`Trinklied (Busch)`) [container]
    - text: Anleitung zu historischen Portraits [container]
    - text: Trübe Aussicht [container]
    - text: Idiosyncrasie [container]
    - text: Der Maulwurf (`Der Maulwurf (Busch)`) [container]
    - text: Romanze (`Romanze (Busch)`) [container]
    - text: Die Kirmeß [container]
    - text: Der Cylinder [container]
    - text: Summa summarum [container]
    - … 2 more
1. `Die sociale und politische Stellung der Deutschen in den Vereinigten Staaten`: linked, book, 55 parts, 0 missing, order toc, validated
    - front: Die sociale und politische Stellung der Deutschen in den Vereinigten Staaten
    - chapter: 1 (`Die sociale und politische Stellung der Deutschen in den Vereinigten Staaten:Titel:1`) [toc]
    - chapter: 2 (`Die sociale und politische Stellung der Deutschen in den Vereinigten Staaten:Titel:2`) [toc]
    - chapter: 1 (`Die sociale und politische Stellung der Deutschen in den Vereinigten Staaten:Inhalt:1`) [toc]
    - chapter: 2 (`Die sociale und politische Stellung der Deutschen in den Vereinigten Staaten:Inhalt:2`) [toc]
    - chapter: 1 (`Die sociale und politische Stellung der Deutschen in den Vereinigten Staaten:Seite 1`) [toc]
    - chapter: 2 (`Die sociale und politische Stellung der Deutschen in den Vereinigten Staaten:Seite 2`) [toc]
    - chapter: 3 (`Die sociale und politische Stellung der Deutschen in den Vereinigten Staaten:Seite 3`) [toc]
    - chapter: 4 (`Die sociale und politische Stellung der Deutschen in den Vereinigten Staaten:Seite 4`) [toc]
    - chapter: 5 (`Die sociale und politische Stellung der Deutschen in den Vereinigten Staaten:Seite 5`) [toc]
    - chapter: 6 (`Die sociale und politische Stellung der Deutschen in den Vereinigten Staaten:Seite 6`) [toc]
    - chapter: 7 (`Die sociale und politische Stellung der Deutschen in den Vereinigten Staaten:Seite 7`) [toc]
    - … 44 more

### B: mixed (108)

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
1. `Westfälische Zeitschrift`: mixed, collection, 7 parts, 0 missing, order natural, validated
    - front: Westfälische Zeitschrift
    - text: Belagerung und Zerstörung des Schlosses Arnsberg 1762 [container]
    - text: Das Paderbornsche Bataillon im siebenjährigen Kriege [container]
    - text: Der Kaffee-Lärm in Paderborn 1781 [container]
    - text: Die Stuckenbrocker Senne [container]
    - text: Fehdebrief an die Stadt Bochum [container]
    - text: Autoren (`Westfälische Zeitschrift/Autoren`) [hierarchy]
    - text: Inhaltsverzeichnis (`Westfälische Zeitschrift/Inhalt`) [hierarchy]
1. `Neue Zeitschrift für Musik`: mixed, collection, 12 parts, 0 missing, order toc-partial, validated
    - front: Neue Zeitschrift für Musik
    - text: Inhaltsverzeichnis 1834–1844 (`Neue Zeitschrift für Musik/Inhaltsverzeichnis 1834–1844 Nummern`) [hierarchy]
    - text: Inhaltsverzeichnis 1834–1844 nach Bänden (`Neue Zeitschrift für Musik/Inhaltsverzeichnis 1834–1844`) [hierarchy]
    - text: Sammlung von Musik-Stücken alter und neuer Zeit 1838–1841 (`Neue Zeitschrift für Musik/Beilagen`) [hierarchy]
    - text: Inhaltsverzeichnis 1845–1868 (`Neue Zeitschrift für Musik/Inhaltsverzeichnis 1845–1868`) [hierarchy]
    - text: Inhaltsverzeichnis 1869–1919 (`Neue Zeitschrift für Musik/Inhaltsverzeichnis 1869–1919`) [hierarchy]
    - text: Inhaltsverzeichnis 1920–1950 (`Neue Zeitschrift für Musik/Inhaltsverzeichnis 1920–1950`) [hierarchy]
    - text: Inhaltsverzeichnis 1950–2000 (`Neue Zeitschrift für Musik/Inhaltsverzeichnis 1950-2000`) [hierarchy]
    - text: Inhaltsverzeichnis 2001 folgende (`Neue Zeitschrift für Musik/Inhaltsverzeichnis 2001 folgende`) [hierarchy]
    - text: Beethoven’s neunte Symphonie [container]
    - text: Musikalische Haus- und Lebensregeln [container]
    - text: Tarnowski, Concert- und Salonmusik [container]
    - … 1 more
1. `Romanzero`: mixed, collection, 70 parts, 0 missing, order chain, validated
    - front: Romanzero
    - text: Wenn man an dir Verrath geübt [container]
    - text: Rhampsenit [container]
    - text: Der weiße Elephant [container]
    - text: Schelm von Bergen [container]
    - text: Valkyren [container]
    - text: Schlachtfeld bei Hastings [container]
    - text: Carl I. [container]
    - text: Maria Antoinette [container]
    - text: Pomare [container]
    - text: Der Apollogott [container]
    - text: Kleines Volk [container]
    - … 59 more
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
1. `Allgemeiner Harz-Berg-Kalender für das Jahr 1931`: mixed, collection, 5 parts, 5 missing, order toc, unproofread
    - front: Allgemeiner Harz-Berg-Kalender für das Jahr 1931
    - text: Dr Holshaer [container]
    - text: Die alten Zechenhäuser im Oberharz [container]
    - text: Aus dem Sagenschatz des Oberharzes (`Aus dem Sagenschatz des Oberharzes (1931)`) [container]
    - text: Verfallener Stollen [container]
    - text: Beamten-Verzeichnis (`Allgemeiner Harz-Berg-Kalender für das Jahr 1931/Beamten-Verzeichnis`) [hierarchy]
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
1. `Schwere, Elektricität und Magnetismus`: mixed, collection, 536 parts, 0 missing, order toc-partial, validated
    - front: Schwere, Elektricität und Magnetismus
    - volume: Zusammenstellungen (`Schwere, Elektricität und Magnetismus/Zusammenstellungen`) [hierarchy]
      - text: Inhaltsverzeichnis (`Schwere, Elektricität und Magnetismus/Inhaltsverzeichnis`) [container]
      - chapter: Erster Abschnitt. Die Potentialfunction. (`Schwere, Elektricität und Magnetismus/Erster Abschnitt`) [container]
      - text: §. 1. Newton’s Gravitationsgesetz. (`Schwere, Elektricität und Magnetismus/§. 1.`) [container]
      - text: §. 2. Die Potentialfunction. (`Schwere, Elektricität und Magnetismus/§. 2.`) [container]
      - text: §. 3. Die Gleichung von Laplace. (`Schwere, Elektricität und Magnetismus/§. 3.`) [container]
      - text: §. 4. Specieller Fall: Anziehung einer Kugelschale, deren Dichtigkeit nur vom Radius vector abhängt. (`Schwere, Elektricität und Magnetismus/§. 4.`) [container]
      - text: §. 5. Anziehung einer homogenen Kugel. (`Schwere, Elektricität und Magnetismus/§. 5.`) [container]
      - text: §. 6. Die Function und ihre ersten Derivirten fur einen inneren Punkt. (`Schwere, Elektricität und Magnetismus/§. 6.`) [container]
      - text: §. 7. Transformation von . (`Schwere, Elektricität und Magnetismus/§. 7.`) [container]
      - text: §. 8. Fortsetzung. (`Schwere, Elektricität und Magnetismus/§. 8.`) [container]
    - … 525 more
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
1. `Alterthümer, Geschichten und Sagen der Herzogthümer Bremen und Verden`: mixed, collection, 56 parts, 0 missing, order toc-partial, validated
    - front: Alterthümer, Geschichten und Sagen der Herzogthümer Bremen und Verden
    - text: Die Herzogthümer Bremen und Verden. Das Land und seine Bewohner. Ueberblick ihrer Beschaffenheit und Geschichte (`Alterthümer, Geschichten und Sagen der Herzogthümer Bremen und Verden/Das Land und seine Bewohner`) [container]
    - text: Das Bremische Moor (`Alterthümer, Geschichten und Sagen der Herzogthümer Bremen und Verden/Das Bremische Moor`) [container]
    - text: Die Marschen (`Alterthümer, Geschichten und Sagen der Herzogthümer Bremen und Verden/Die Marschen`) [container]
    - text: Die in den Herzogthümern Bremen und Verden noch vorhandenen alten Grabhügel und Steindenkmäler (`Alterthümer, Geschichten und Sagen der Herzogthümer Bremen und Verden/Grabhügel und Steindenkmäler`) [container]
    - text: Plinius und Tacitus über das Land und Volk der Chauken (`Alterthümer, Geschichten und Sagen der Herzogthümer Bremen und Verden/Plinius und Tacitus über das Land und Volk der Chauken`) [container]
    - text: Eine Scene aus dem Zuge der Sachsen nach Britannien (`Alterthümer, Geschichten und Sagen der Herzogthümer Bremen und Verden/Eine Scene aus dem Zuge der Sachsen nach Britannien`) [container]
    - text: St. Willehad, der erste Bischof von Bremen (`Alterthümer, Geschichten und Sagen der Herzogthümer Bremen und Verden/St. Willehad, der erste Bischof von Bremen`) [container]
    - text: St. Ansgar, der erste Erzbischof von Bremen (`Alterthümer, Geschichten und Sagen der Herzogthümer Bremen und Verden/St. Ansgar, der erste Erzbischof von Bremen`) [container]
    - text: Eine Urkunde des Erzbischofs Adalbert vom Jahre 1059 (`Alterthümer, Geschichten und Sagen der Herzogthümer Bremen und Verden/Eine Urkunde des Erzbischofs Adalbert vom Jahre 1059`) [container]
    - text: Eine Nordpol-Expedition der Friesen im 11. Jahrhundert (`Alterthümer, Geschichten und Sagen der Herzogthümer Bremen und Verden/Eine Nordpol-Expedition der Friesen im 11. Jahrhundert`) [container]
    - text: Verzeichniß der Heiligen, von welchem die Kirchen der Herzogthümer den Namen führen (`Alterthümer, Geschichten und Sagen der Herzogthümer Bremen und Verden/Verzeichniß der Heiligen`) [container]
    - … 45 more
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
1. `Topographia Westphaliae`: mixed, collection, 217 parts, 0 missing, order toc, validated
    - front: Topographia Westphaliae
    - text: Beschreibung (`Topographia Westphaliae: Beschreibung`) [toc]
    - text: Verzeichnis der Bilder (`Topographia Westphaliae/Verzeichnis der Bilder`) [hierarchy]
    - text: Verzeichnüß (`Topographia Westphaliae: Verzeichnüß`) [toc]
    - text: Verzeichnuß / deren in Kupffer gestochenen Stätte vnd Orten (`Topographia Westphaliae: Verzeichnuß / deren in Kupffer gestochenen Stätte vnd Orten`) [toc]
    - text: Aach (`Topographia Westphaliae: Aach`) [toc]
    - text: Arnsberg (`Topographia Westphaliae: Arnsberg`) [toc]
    - text: Arnswiller (`Topographia Westphaliae: Arnswiller`) [toc]
    - text: Aurich (`Topographia Westphaliae: Aurich`) [toc]
    - text: Bedbur (`Topographia Westphaliae: Bedbur`) [toc]
    - text: Berchem (`Topographia Westphaliae: Berchem`) [toc]
    - text: Bielfeld (`Topographia Westphaliae: Bielfeld`) [toc]
    - … 206 more
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
1. `Topographia Palatinatus Rheni`: mixed, collection, 216 parts, 0 missing, order toc, validated
    - front: Topographia Palatinatus Rheni
    - chapter: Vorrede (`Topographia Palatinatus Rheni: Vorrede`) [toc]
    - text: Verzeichnis der Bilder (`Topographia Palatinatus Rheni/Verzeichnis der Bilder`) [hierarchy]
    - text: Nahmen-Register (`Topographia Palatinatus Rheni: Nahmen-Register`) [toc]
    - chapter: Register der Oerter (`Topographia Palatinatus Rheni: Register der Oerter`) [toc]
    - text: Bericht an den Buchbinder (`Topographia Palatinatus Rheni: Bericht an den Buchbinder`) [toc]
    - text: Adeltzheim (`Topographia Palatinatus Rheni: Adeltzheim`) [toc]
    - text: Altzheim (`Topographia Palatinatus Rheni: Altzheim`) [toc]
    - text: Altzstatt (`Topographia Palatinatus Rheni: Altzstatt`) [toc]
    - text: Anweiler (`Topographia Palatinatus Rheni: Anweiler`) [toc]
    - text: Arentzhausen (`Topographia Palatinatus Rheni: Arentzhausen`) [toc]
    - text: Argenthal (`Topographia Palatinatus Rheni: Argenthal`) [toc]
    - … 205 more
1. `Die Gartenlaube (1859)`: mixed, collection, 335 parts, 0 missing, order toc-partial, validated
    - front: Die Gartenlaube (1859)
    - text: Er betet [toc]
    - text: Aerztliche Strafpredigten – Den Müttern hustender Kinder [toc]
    - text: Eine deutsche Frau in der Fremde [toc]
    - text: Wanderungen im südlichen Rußland. 1. Mitten in den Heuschrecken (`Mitten in den Heuschrecken`) [toc]
    - text: Gräfin Lichtenau (`Preußische Licht- und Schattenbilder 1. Gräfin Lichtenau`) [toc]
    - text: Ein Besuch bei Kane, dem Nordpolfahrer [toc]
    - text: Schönbrunn [toc]
    - text: Das sicherste Schutzmittel gegen die Entzündung feiner Gewebe und anderer leicht feuerfangender Gegenstände (`Das sicherste Schutzmittel gegen die Entzündung feiner Gewebe`) [toc]
    - text: Savoyen in Paris [toc]
    - text: Wat sek de Schwaalkes vertellen [toc]
    - text: Das germanische Nationalmuseum zu Nürnberg [toc]
    - … 324 more
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
1. `Die Gartenlaube (1863)`: mixed, collection, 400 parts, 0 missing, order toc-partial, validated
    - front: Die Gartenlaube (1863)
    - text: Die Tochter des Fälschers [toc]
    - text: Zwei Dichter und ein Dichterasyl [toc]
    - text: Des Freiherren von Trenck letzte Stunden [toc]
    - text: Heilung und erste Hülfeleistung bei Knochenbrüchen [toc]
    - text: Zum Nordcap [toc]
    - text: Aus dem Leben eines deutschen Thierbändigers [toc]
    - text: Die Reisepredigt in Schwaben [toc]
    - text: In der Buoy-Kette an der Themse [toc]
    - text: In einer persischen Turnhalle [toc]
    - text: Aesthetische Briefe für’s Haus [toc]
    - text: Geheimnißvoller Auswandererzug [toc]
    - … 389 more
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
1. `Die Gartenlaube (1881)`: mixed, collection, 417 parts, 0 missing, order toc-partial, validated
    - front: Die Gartenlaube (1881)
    - text: Adelbert von Chamisso (`Adelbert von Chamisso (Die Gartenlaube 1881/1)`) [toc]
    - text: Schule und Nervosität [toc]
    - text: Die Gebrüder Grimm und der Minister Hassenpflug [toc]
    - text: Zum Jubeljahr der Lebensversicherungs-Gesellschaft zu Leipzig [toc]
    - text: Niágara [toc]
    - text: Elephantenleben in der Wildniß [toc]
    - text: Luther’s Einzug in Worms [toc]
    - text: Eine amerikanische Strandheldin [toc]
    - text: Spottmünzen [toc]
    - text: Vermißt! (`Vermißt! (Gartenlaube 1881/1)`) [toc]
    - text: Die Sorge [toc]
    - … 406 more

### D: virtual roots (46)

1. `Zeitschriften (Illustrierte)`: virtual, collection, 1 parts, 0 missing, order single, unknown
    - text: The St. James' magazine (`Zeitschriften (Illustrierte)/fremdsprachige/The St. James' magazine`)
1. `Agrumi`: virtual, collection, 4 parts, 0 missing, order scan, validated
    - text: Der Geliebten (`Agrumi/Der Geliebten`)
    - text: Nachtgesang I (`Agrumi/Nachtgesang I`) [hierarchy]
    - text: Die launenhafte Geliebte (`Agrumi/Die launenhafte Geliebte`) [hierarchy]
    - chapter: Gesang zur Tarantella (`Agrumi/Gesang zur Tarantella`) [hierarchy]
1. `Handeln die Thiere nur aus Instinct oder mit Ueberlegung?`: virtual, book, 1 parts, 0 missing, order single, validated
    - chapter: 1 (`Handeln die Thiere nur aus Instinct oder mit Ueberlegung?/1`)
1. `Bilder aus dem jüdischen Leben`: virtual, collection, 1 parts, 0 missing, order single, validated
    - text: Bilder aus dem jüdischen Leben/1. Polnische Schnorrer
1. `Topographie der historischen und kunstgeschichtlichen Denkmale in den Bezirken Tepl und Marienbad`: virtual, collection, 2 parts, 0 missing, order scan, proofread
    - text: Maiersgrün (`Topographie der historischen und kunstgeschichtlichen Denkmale in den Bezirken Tepl und Marienbad/Maiersgrün`)
    - text: Tepl, Pfarrkirche zu S. Ägidius (`Topographie der historischen und kunstgeschichtlichen Denkmale in den Bezirken Tepl und Marienbad/Tepl, Pfarrkirche zu S. Ägidius`) [hierarchy]
1. `Geschichtsauffassung und Geschichtsschreibung`: virtual, book, 7 parts, 0 missing, order natural, validated
    - chapter: I. Das Alte und die neuen Probleme (`Geschichtsauffassung und Geschichtsschreibung/I. Das Alte und die neuen Probleme`)
    - chapter: II. Vorläufer und Vorbilder (`Geschichtsauffassung und Geschichtsschreibung/II. Vorläufer und Vorbilder`) [hierarchy]
    - chapter: III. Scholastischer Humanismus (`Geschichtsauffassung und Geschichtsschreibung/III. Scholastischer Humanismus`) [hierarchy]
    - chapter: IV. Humanistische Weltchroniken (`Geschichtsauffassung und Geschichtsschreibung/IV. Humanistische Weltchroniken`) [hierarchy]
    - chapter: V. Entdecker und Kritiker (`Geschichtsauffassung und Geschichtsschreibung/V. Entdecker und Kritiker`) [hierarchy]
    - chapter: VI. Germania illustrata (`Geschichtsauffassung und Geschichtsschreibung/VI. Germania illustrata`) [hierarchy]
    - chapter: VII. Die humanistische Hofgeschichtschreibung unter Kaiser Maximilian (`Geschichtsauffassung und Geschichtsschreibung/VII. Die humanistische Hofgeschichtschreibung unter Kaiser Maximilian`) [hierarchy]
1. `Zwanglose Blätter(1883/9)`: virtual, collection, 1 parts, 0 missing, order single, validated
    - text: Briefkasten (`Zwanglose Blätter(1883/9)/Briefkasten`)
1. `Galerie historischer Enthüllungen`: virtual, collection, 5 parts, 0 missing, order natural, validated
    - text: Galerie historischer Enthüllungen 1.&nbsp;Wilhelm Tell und der Rütlibund (`Galerie historischer Enthüllungen/1. Wilhelm Tell und der Rütlibund`)
    - text: Galerie historischer Enthüllungen/2. Die Sage von dem Heldentode der vierhundert Pforzheimer [hierarchy]
    - text: Galerie historischer Enthüllungen/3. Agnes Bernauer [hierarchy]
    - text: Galerie historischer Enthüllungen 4.&nbsp;Arnold von Winkelried (`Galerie historischer Enthüllungen/4. Arnold von Winkelried`) [hierarchy]
    - text: Galerie historischer Enthüllungen 5.&nbsp;Kaufmännische Noblesse (`Galerie historischer Enthüllungen/5. Kaufmännische Noblesse`) [hierarchy]
1. `Nachtseiten von London`: virtual, collection, 1 parts, 0 missing, order single, validated
    - text: Nachtseiten von London/2
1. `Wikisource`: virtual, collection, 1 parts, 0 missing, order single, unknown
    - text: Hilf mit! (`Wikisource/Hilf mit!`)
1. `Zeitschriften (Kunst)`: virtual, collection, 5 parts, 0 missing, order natural, unknown
    - text: La belle assemblée (`Zeitschriften (Kunst)/fremdsprachige/La belle assemblée : being Bell's court and fashionable magazine, addressed particulary to the ladies`)
    - text: The ladies' cabinet of fashion, music & romance (`Zeitschriften (Kunst)/fremdsprachige/The ladies' cabinet of fashion, music & romance`) [hierarchy]
    - text: The ladies' companion and monthly magazine (`Zeitschriften (Kunst)/fremdsprachige/The ladies' companion and monthly magazine`) [hierarchy]
    - text: The lady's magazine or entertaining companion for the fair sex : (`Zeitschriften (Kunst)/fremdsprachige/The lady's magazine or entertaining companion for the fair sex`) [hierarchy]
    - text: The new monthly assemblée (`Zeitschriften (Kunst)/fremdsprachige/The new monthly assemblée`) [hierarchy]
1. `Aus der Beamtenwelt`: virtual, collection, 4 parts, 0 missing, order natural, validated
    - text: Aus dem Beamtenleben/Nr. 1. Der Herr Director (`Aus der Beamtenwelt/Nr. 1. Der Herr Director`)
    - text: Aus der Beamtenwelt/Nr. 2. Ein Ruheposten [hierarchy]
    - text: Aus der Beamtenwelt/Nr. 3. Das Stipendium [hierarchy]
    - text: Aus der Beamtenwelt Nr.&nbsp;4. Der Postschein (`Aus der Beamtenwelt/Nr. 4. Der Postschein`) [hierarchy]
1. `Aus meiner Pilgertasche`: virtual, collection, 3 parts, 0 missing, order natural, validated
    - text: Aus meiner Pilgertasche/1. Eine Begegnung mit General Radowitz
    - text: Aus meiner Pilgertasche/2. Herr von Senkenberg [hierarchy]
    - text: Aus meiner Pilgertasche/3. [hierarchy]
1. `Aus den politischen Salons des neuen Italiens`: virtual, collection, 1 parts, 0 missing, order single, validated
    - text: Aus den politischen Salons des neuen Italiens/1. Die Frau des Märtyrers
1. `Kataloge der Jahresausstellungen der Kurfürstl.`: virtual, collection, 1 parts, 0 missing, order single, unknown
    - text: Kataloge der Jahresausstellungen der Kurfürstl./Königl. Sächsischen Akademie der Künste
1. `Erinnerungen aus meinem Leben (Friedrich Hecker)`: virtual, collection, 1 parts, 0 missing, order single, validated
    - text: Der Hexenmeister der Prairie (`Erinnerungen aus meinem Leben (Friedrich Hecker)/2`)
1. `Heinrich Heine`: virtual, collection, 2 parts, 0 missing, order natural, unknown
    - text: Gesamtverzeichnis der Prosanotizen (`Heinrich Heine/DHA/Gesamtverzeichnis der Prosanotizen`)
    - text: Werkverzeichnis (`Heinrich Heine/DHA/Werkverzeichnis`) [hierarchy]
1. `Source-Provider`: virtual, collection, 1 parts, 0 missing, order single, unknown
    - text: Landesarchiv Baden-Württemberg (`Source-Provider/Landesarchiv Baden-Württemberg`)
1. `Mein erster Hirsch`: virtual, collection, 1 parts, 0 missing, order single, validated
    - text: Mein erster Hirsch/1881
1. `Seegeschichten`: virtual, collection, 1 parts, 0 missing, order single, validated
    - text: Seegeschichten 2. Die Springstange (`Seegeschichten/2. Die Springstange`)
1. `Zedler`: virtual, collection, 1 parts, 0 missing, order single, unknown
    - text: Quellen (`Zedler/Quellen`)
1. `Archiv für die zeichnenden Künste`: virtual, collection, 1 parts, 0 missing, order single, proofread
    - text: Notizen über Hieronymus van Aeken, genannt Bosch, und Alart Du Hameel (`Archiv für die zeichnenden Künste/Jahrgang 7-8/Notizen über Hieronymus van Aeken, genannt Bosch, und Alart Du Hameel`)
1. `Reiseerinnerungen`: virtual, collection, 1 parts, 0 missing, order single, validated
    - text: Reiseerinnerungen/I. Bamberg
1. `Erinnerungen aus dem letzten Kriege`: virtual, collection, 3 parts, 0 missing, order natural, validated
    - text: Erinnerungen aus dem letzten Kriege/12. Das rothe Haus
    - text: Erinnerungen aus dem letzten Kriege/13. Die Kirche von Argenteuil [hierarchy]
    - text: Erinnerungen aus dem letzten Kriege/Nr. 14. Madelon (`Erinnerungen aus dem letzten Kriege/14. Madelon`) [hierarchy]
1. `Der Wille zum Stil`: virtual, collection, 2 parts, 0 missing, order natural, proofread
    - text: „Der Wille zum Stil“ (`Der Wille zum Stil/1`)
    - text: „Der Wille zum Stil“ (Neugestaltung von Leben, Kunst und Technik) (`Der Wille zum Stil/2`) [hierarchy]
1. `Aus deutschen Lustschlössern`: virtual, collection, 2 parts, 0 missing, order natural, validated
    - text: Aus deutschen Lustschlössern/1. Auf dem Winterkasten
    - text: Aus deutschen Lustschlössern 2. Der vorletzte der fränkischen Hohenzollern (`Aus deutschen Lustschlössern/2. Der vorletzte der fränkischen Hohenzollern`) [hierarchy]
1. `Hoff Budde`: virtual, collection, 1 parts, 0 missing, order single, unknown
    - text: Nr. 271 (`Hoff Budde/Test/Nr. 271`)
1. `Zwanglose Blätter(1883/8)`: virtual, collection, 1 parts, 0 missing, order single, validated
    - text: Briefkasten (`Zwanglose Blätter(1883/8)/Briefkasten`)
1. `Der Handlungsreisende wie er sein soll`: virtual, book, 1 parts, 0 missing, order single, validated
    - chapter: 6 (`Der Handlungsreisende wie er sein soll/6`)
1. `Gallerie berühmter Firmengründer`: virtual, collection, 1 parts, 0 missing, order single, validated
    - text: Gallerie berühmter Firmengründer 1. Ein Leipziger Buchhändler (`Gallerie berühmter Firmengründer/1. Ein Leipziger Buchhändler`)

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
1. text “In Friedrich Rückert’s Haus/2” (`In Friedrich Rückert’s Haus/2`), part 1 of `In Friedrich Rückert’s Haus` (collection, 2 parts, 2 texts)
1. text “Giftrezepte der Illuminaten und ein berüchtigtes Protokoll” (`Geschichte des Illuminaten-Ordens/Giftrezepte der Illuminaten und ein berüchtigtes Protokoll`), part 17 of `Geschichte des Illuminaten-Ordens` (collection, 25 parts, 24 texts)
1. text “Gesellschaftlicher Zustand” (`Beschreibung des Oberamts Mergentheim/Kapitel A 6`), part 6 of `Beschreibung des Oberamts Mergentheim` (collection, 56 parts, 56 texts)
1. text “Das Diaconissenhaus als Schule” (`Etwas aus der Geschichte des Diaconissenhauses Neuendettelsau/§. 4. Das Diaconissenhaus als Schule`), part 7 of `Etwas aus der Geschichte des Diaconissenhauses Neuendettelsau` (collection, 14 parts, 14 texts)
1. text “Rudolf Däbritz” (`Paulys Realencyclopädie der classischen Altertumswissenschaft/Register/Rudolf Däbritz`), part 1047 of `Paulys Realencyclopädie der classischen Altertumswissenschaft` (collection, 1,325 parts, 1,297 texts)
1. text “Inhaltsverzeichnis Die Schaubühne” (`Die Schaubühne – Die Weltbühne/Inhalt Schaubühne`), part 13 of `Die Schaubühne – Die Weltbühne` (collection, 23 parts, 23 texts)
1. text “„Wilflingen” (`Beschreibung des Oberamts Ellwangen/Kapitel B 22`), part 29 of `Beschreibung des Oberamts Ellwangen` (collection, 37 parts, 36 texts)
1. text “Würzburg” (`Geschichte von Kloster Heilsbronn/Würzburg`), part 290 of `Geschichte von Kloster Heilsbronn` (collection, 484 parts, 482 texts)
1. text “Der Bullerborn” (`Westphälische Sagen und Geschichten/Der Bullerborn`), part 13 of `Westphälische Sagen und Geschichten` (collection, 42 parts, 42 texts)
1. text “Danksagung für die Leiden Christi” (`Evangelien-Postille (Wilhelm Löhe)/Danksagung`), part 27 of `Evangelien-Postille (Wilhelm Löhe)` (collection, 100 parts, 77 texts)
1. text “75 Kupferstiche” (`Topographia Colonia et al./Verzeichnis der Bilder`), part 1 of `Topographia Colonia et al.` (collection, 63 parts, 60 texts)
1. text “Benützte Quellen (gedruckte und ungedruckte)” (`Geschichte der Pfarrei Sachsen bei Ansbach und der zugehörigen Orte/Benützte Quellen`), part 9 of `Geschichte der Pfarrei Sachsen bei Ansbach und der zugehörigen Orte` (book, 14 parts, 2 texts)
1. text “Beerbach” (`Geschichte von Kloster Heilsbronn/Beerbach`), part 307 of `Geschichte von Kloster Heilsbronn` (collection, 484 parts, 482 texts)
1. text “Leidingendorf” (`Geschichte von Kloster Heilsbronn/Leidingendorf`), part 337 of `Geschichte von Kloster Heilsbronn` (collection, 484 parts, 482 texts)
1. text “Otfried Deubner” (`Paulys Realencyclopädie der classischen Altertumswissenschaft/Register/Otfried Deubner`), part 896 of `Paulys Realencyclopädie der classischen Altertumswissenschaft` (collection, 1,325 parts, 1,297 texts)
1. text “Einzelne Monate der Ausgabe-Rechnung vom J. 1391” (`Aachener Stadtrechnungen aus dem XIV. Jahrhundert/Einzelne Monate der Ausgabe-Rechnung vom J. 1391`), part 65 of `Aachener Stadtrechnungen aus dem XIV. Jahrhundert` (collection, 71 parts, 69 texts)
1. text “Theodor Kipp” (`Paulys Realencyclopädie der classischen Altertumswissenschaft/Register/Theodor Kipp`), part 1128 of `Paulys Realencyclopädie der classischen Altertumswissenschaft` (collection, 1,325 parts, 1,297 texts)
1. text “Gerhard von Kügelgens Portraits von Goethe, Wieland, Schiller, und Herder” (`Jugendleben und Wanderbilder/Band 2 Gerhard von Kügelgens Portraits von Goethe, Wieland, Schiller, und Herder`), part 49 of `Jugendleben und Wanderbilder` (book, 53 parts, 8 texts)
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
1. `Allgemeine musikalische Zeitung (Wien)`: single, single, 1 parts, 0 missing, order –, unknown, excluded bibliography
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
1. `Annalen der Physik`: mixed, collection, 181 parts, 0 missing, order natural, proofread
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
    - … 170 more
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

