# fr.wikisource: structure and quality survey (WP1a)

Generated 2026-10-31 by `python -I -m wscatalog.survey fr` from `frwikisource-20261001-pages-articles1.xml-p1p1500000.bz2, frwikisource-20261001-pages-articles1.xml-p1500001p3000000.bz2, frwikisource-20261001-pages-articles1.xml-p3000001p4500000.bz2, frwikisource-20261001-pages-articles1.xml-p4500001p5004401.bz2` (resolution and quality rules: PLAN.md 5.2 steps 1–1h and 5.4; config `pipeline/config/fr.yaml`). Resolution took 48 s on the cached extract.

Input: 537,405 ns0 pages, 56,685 ns0 redirects, 4,030,797 scan pages, 28,847 Index pages, 10,886 pages with the `disambiguation` page prop.

## 1. Works

38,669 works resolved from 524,324 non-excluded ns0 pages; 38,040 enter the catalog after the hard exclusions (section 9). Multi-part works hold 486,898 parts, of which 373,936 are searchable texts.

| structure | works | parts | texts |
|---|---|---|---|
| single | 26,850 | 26,850 | 0 |
| subpages | 9,385 | 320,292 | 228,836 |
| virtual | 1,243 | 121,341 | 112,052 |
| linked | 795 | 18,465 | 13,425 |
| mixed | 396 | 26,800 | 19,623 |

| type (step 1e) | works |
|---|---|
| periodical-issue | 19,832 |
| book | 13,373 |
| collection | 4,669 |
| single | 433 |
| reference | 218 |
| document | 144 |

Largest works by parts:

| work | structure | type | parts | texts | missing | rules of the parts |
|---|---|---|---|---|---|---|
| L’Encyclopédie | virtual | reference | 45,535 | 45,462 | 0 | container 41632, hierarchy 3902 |
| Dictionnaire de Trévoux | virtual | reference | 32,173 | 32,110 | 16339 | container 31810, hierarchy 362 |
| Petit dictionnaire de la langue française (1836) | subpages | reference | 23,874 | 23,829 | 0 | hierarchy 23874 |
| Dictionnaire des patois romans de la Moselle | subpages | reference | 21,247 | 21,230 | 64 | container 21244, hierarchy 3 |
| Petit dictionnaire de la langue française (1860) | subpages | reference | 20,406 | 20,367 | 7 | hierarchy 20406 |
| Correspondance de Voltaire | subpages | collection | 10,426 | 387 | 2 | container 10375, hierarchy 51 |
| Petit dictionnaire de la langue française (Hocquart, 1831) | subpages | reference | 7,352 | 7,334 | 19 | hierarchy 7352 |
| Grand dictionnaire universel du XIXe siècle | subpages | reference | 5,834 | 5,801 | 19902 | container 5402, hierarchy 432 |
| Dictionnaire liégeois-français (Forir) | subpages | reference | 4,534 | 4,515 | 303 | container 4516, hierarchy 18 |
| Le Littré de la Grand’Côte | virtual | reference | 4,251 | 4,216 | 0 | hierarchy 4250 |
| Dictionnaire infernal | virtual | reference | 3,829 | 3,796 | 0 | container 3825, hierarchy 3 |
| Thresor de la langue françoise | virtual | reference | 3,333 | 3,315 | 0 | hierarchy 3332 |
| Cours d’agriculture (Rozier) | subpages | reference | 3,148 | 3,128 | 0 | container 3110, hierarchy 38 |
| Dictionnaire classique de la langue française (Bescherelle Jeune, 1880) | subpages | reference | 3,078 | 3,073 | 0 | hierarchy 3078 |
| Néologie, ou Vocabulaire de mots nouveaux | subpages | book | 2,835 | 2,807 | 0 | container 2810, hierarchy 25 |

## 2. Exclusions (step 1)

| reason | pages |
|---|---|
| disambiguation | 8,805 |
| whole-text | 4,271 |
| versions | 5 |

## 3. Patterns A–I (WS§1)

|  | pattern | count |
|---|---|---|
| A | collection of root pages (`structure = linked`) | 795 works, 18,465 parts |
| B | mixed subpages and root pages (`mixed`) | 396 works, 26,800 parts |
| C | work with an oversized page (> oversized_bytes, transcluded scan pages included) | 899 works |
| D | missing root, virtual root (`virtual`) | 1,243 works, 121,341 parts |
| E | texts inside a subpage collection reached from a disambiguation or versions page | 30,984 parts (28,899 searchable); all searchable subpage texts: 351,178 |
| F | whole-text duplicate subpages (excluded) | 4,271 pages |
| G | reference works (type `reference`) | 218 works, 219,937 parts; 0 pages excluded by title prefix |
| H | deep nesting (parts at depth ≥ 2, i.e. three levels below the root) | 186 works |
| I | several editions: works with an other-versions template | 562 works; 5 versions pages excluded |

Tree depth of multi-part works (levels below the root): 1: 10,570, 2: 1,063, 3: 148, 4: 31, 5: 6, 6: 1.

## 4. Parent rules and order sources (steps 1b, 1c)

| parent rule | pages |
|---|---|
| container | 361,011 |
| hierarchy | 125,703 |
| own | 37,426 |
| toc | 184 |

| order source (nodes with ≥ 2 children) | nodes |
|---|---|
| toc | 8,434 |
| scan | 2,695 |
| chain | 2,500 |
| natural | 902 |

527 of the `natural` nodes have a TOC that lists only some of their children (the TOC order did not cover all children).

Root pages attached by their header's container link: 9,167; most frequent containers: `Lettres de Madame de Sévigné/Édition Monmerqué` 887; `Œuvres de P. Lachambeaudie. ― Fables et Poésies (1854)` 411; `Les Fables complètes (Viennet)` 213; `La Chanson française du XVe au XXe siècle` 147; `Parnasse de la Jeune Belgique` 140; `La Nuit (Gilkin)` 130; `Œuvres de Sully Prudhomme/Poésies 1866-1872` 128; `Œuvres de Théophile Gautier (Lemerre, 1890)` 124; `Échos de France` 121; `Poésies diverses (Corneille)` 109.

Pages attached by TOC membership (rule 3): 184; most frequent TOC pages: `Poésies Sociales des Ouvriers` 41; `Le Péché de Madeleine` 25; `Tamaris (RDDM)` 24; `Antonia (RDDM)` 12; `Œuvres complètes (Ovide)` 10; `Œuvres complètes de Platon, traduites par Émile Chambry` 8; `Œuvres complètes de François Arago` 6; `Œuvres complètes de Paul Verlaine` 4; `Femmes-Poëtes de la France` 4; `Césarine Dietrich (RDDM)` 3.

## 5. Container links that fail rule 1

47,778 of 412,369 pages whose header carries a container link (11.6 %) point to:

- outside-ns0: 64 (e.g. `Les Grans et Merveilleux Faictz du Seigneur Nemo` → `Livre:Montaiglon - Recueil de poésies françoises des 15e et 16e siècles, tome XI.djvu`, `Monsieur Rousset` → ``, `Le grand ballon captif à vapeur de M. Henri Giffard` → `Auteur:Henri Giffard`, `Phèdre (Racine), Didot, 1854/Acte II` → `Livre:Racine - Œuvres, Didot, 1854.djvu`)
- excluded: 44,296 (e.g. `Les Grandes manœuvres du Centre` → `Revue des Deux Mondes`, `Anciens poètes français/Louise Labe` → `Revue des Deux Mondes`, `Dictionnaire de théologie catholique/MACAIRE D’ANCYRE` → `Dictionnaire de théologie catholique`, `Beaux-Arts - Du principe des expositions - Le concours en Grèce et de nos jours` → `Revue des Deux Mondes`)
- missing: 3,418 (e.g. `L’Incursion/Chapitre 11` → `L'Incursion`, `L’Enchaînement des choses/Chapitre 1` → `Contes fantastiques d’Hoffmann`, `Candide, ou l’Optimisme/Beuchot 1829/Chapitre 6` → `Œuvres de Voltaire/Beuchot/Tome 33`, `Pomme Warner's King` → `Bulletins d’arboriculture, de floriculture et de culture potagère`)

## 6. Prev/next chains across Index boundaries

Of 242,593 next-links between pages that both have an Index, 4,554 (1.9 %) cross to a different Index.
Examples: `Grand dictionnaire universel du XIXe siècle/algédi s. m. (supplément)` (Larousse - Grand dictionnaire universel du XIXe siècle - Tome 16, part. 1, A-B.djvu) → `Grand dictionnaire universel du XIXe siècle/algedo s. m.` (Larousse - Grand dictionnaire universel du XIXe siècle - Tome 1, part. 1, A-Am.djvu); `De Paris à Bucharest/Chapitre 42` (Le Tour du monde - 11.djvu) → `De Paris à Bucharest/Chapitre 43` (Le Tour du monde - 13.djvu); `Grand dictionnaire universel du XIXe siècle/Aartsen (pierre) (supplément)` (Larousse - Grand dictionnaire universel du XIXe siècle - Tome 16, part. 1, A-B.djvu) → `Grand dictionnaire universel du XIXe siècle/Aarwangen, suisse (supplément 2)` (Larousse - Grand dictionnaire universel du XIXe siècle - Tome 17, part. 1, A.djvu); `Pensées et Fragments inédits de Montesquieu/Notes (I)` (Montesquieu - Pensées et Fragments inédits, t1, 1899.djvu) → `Pensées et Fragments inédits de Montesquieu/V` (Montesquieu - Pensées et Fragments inédits, t2, 1901.djvu).

Root-page works that sit on a prev/next chain but stay separate works (no container, not in a unique TOC; WS§8 item 5): 3,382.

## 7. Orphan trees, oversized pages, red links

**Orphan trees** (virtual roots): 1,243, holding 121,341 parts. Examples: `Contre la seconde réponse de Julien (Augustin d’Hippone)` (4); `Tableaux poétiques` (1); `Littérature étrangère (Barbey d’Aurevilly)` (1); `Lucain, Silius Italicus, Claudien` (2); `Le Dictionnaire raisonné des difficultés` (2); `Maurice Maeterlinck - Théâtre 1, 1903` (1); `Œuvres de Champlain` (18); `Correspondance littéraire, philosophique et critique` (1).

**Oversized pages** (> 300,000 bytes of wikitext including transcluded scan pages): 6,471. Largest: `Œuvres complètes de Saint Augustin (Raulx)` 51.0 MB; `Bible Crampon 1904/Ma` 32.2 MB; `Bible Crampon 1904/Rt` 12.9 MB; `Dictionnaire infernal/6e éd., 1863` 11.7 MB; `Bibliothèque historique et militaire/César/Guerre des Gaules` 11.0 MB; `Maison rustique du XIXe siècle/éd. 1844` 10.5 MB; `Annales de Géographie/tome 14` 9.7 MB; `Bible Crampon 1923/Illustrations/9` 8.9 MB; `Bible Crampon 1923/Illustrations/8` 8.9 MB; `Bible Crampon 1923/Illustrations/7` 8.9 MB.

**Red links in TOCs** (missing parts): 1,009 multi-part works have red links, 64,385 in total; median share of the TOC 54%. Most missing: `Grand dictionnaire universel du XIXe siècle` 19902 of 25736; `Dictionnaire de Trévoux` 16339 of 48512; `Dictionnaire de l’Académie française` 2515 of 2931; `Nouveau dictionnaire de pédagogie et d’instruction primaire` 1517 of 1665; `Nouvelle Biographie générale` 1252 of 1524; `Dictionnaire des Arts et des Sciences` 969 of 1189; `Encyclopédie méthodique` 444 of 3211; `Mémoires secrets de Bachaumont (1777-1789)` 417 of 495.

## 8. Colon prefixes with ≥ 100 root pages

None.

## 9. Quality and completeness (PLAN.md 5.4)

**Hard exclusions**: 629 works — no-text 261, no-text-pages 159, index-faulty 107, under-300-bytes 102.

| tier | works | share | texts in them | source |
|---|---|---|---|---|
| validated | 10,678 | 28.1 % | 45,804 | page-levels 10,595, textquality 73, index-progress 10 |
| proofread | 20,402 | 53.6 % | 290,057 | page-levels 20,299, textquality 93, index-progress 10 |
| unproofread | 6,857 | 18.0 % | 36,871 | page-levels 6,706, textquality 86, index-progress 65 |
| unknown | 103 | 0.3 % | 283 | none 103 |

601 works are proofread by the ≥ 90 % rule. 4,580 of the 6,857 unproofread works have no page above level 1 (raw OCR).

**Incomplete**: 2,566 works — missing-parts 1,138, missing-pages 754, coverage 673, index-progress 1.
Works with missing scan pages inside a transcluded range: 1,000 (76,718 pages).
Coverage was computed for 2,856 works that are their Index's book; 812 cover under 95 % of the Index's text pages.
6 works transclude scan pages of files the dump has no Page: pages for (counted as no signal, not as missing).
Wikisource's own translations (labelled, kept): 0.

Text-quality template values: `100%` 98,456, `Textes validés` 52,459, `75%` 18,047, `50%` 3,194, `25%` 2,638, `00%` 3, `textes validés` 2, `Relu et corrigé` 1.
Index progress values: `C` 16,819, `T` 5,888, `V` 5,061, `OCR` 474, `L` 51, `MS` 39, `D` 8, `X` 2.
Works excluded as index-faulty, by the tier their page levels would give: unproofread 53, proofread 35, none 16, validated 3.

**Unknown works** (no signal): 103 in the catalog after the junk rules; the junk rules removed 102 (under-300-bytes 102).
Size: median 3,838 bytes, p10 891, p90 62,671; multi-part 14.

30 random unknown works:

- `Les bassins à cupule` (subpages, 293,445 B): __EXPECTWITHOUTSCANS__ Les bassins à cupule dans le Centre-Ouest de la Gaule romaine, du Ier siècle avant J.-C. au Ve siècle après J.-C. est un mémoire d'histoi
- `Dénonciation d'un soldat français défaitiste pendant la Première Guerre Mondiale` (single, 2,603 B): __EXPECTWITHOUTSCANS__ « Calan, 21 mai 1915 Monsieur le Préfet, Je trouve qu’il est de mon devoir d’institutrice française de vous faire part des conversations 
- `Histoire de l'Académie Royale des Sciences et des Belles Lettres (1746)` (single, 4,492 B): Sommaire Histoire de l'Académie (Année 1746), p. 1-3 Réglement de l'Académie, p. 3-9 Discours prononcé le jour de la naissance du Roi (1747) - P.L.M. de Maupert
- `Château de Heverlee` (virtual, 2,077 B): __EXPECTWITHOUTSCANS__ Transcription == Une aultre moyenne painture sur bois de la tentation de St. Anthoine, faicte par Jeronimus Bosch, avecq sa casse de bois
- `A Blanqui (Louise Michel)` (single, 4,870 B): A ceux qui donnent leur vie pour le peuple, soit que la terre ait bu leur sang, soit qu’ils aient souffert dans les prisons, le peuple donne une majestueuse glo
- `Commentaires sur Unto This Last de M. K. Gandhi` (single, 36,480 B): __EXPECTWITHOUTSCANS__ Catégorie:Thèses et Mémoires universitairesCatégorie:XXe siècle Gandhi, une pensée moderne == Dans un chapitre de son autobiographie inti
- `Seconde dissertation sur les éléments` (single, 2,572 B): Seconde dissertation sur les éléments - Eller Seconde dissertation sur les éléments - Eller
- `L’Élémentarisme et son origine` (single, 7,387 B): Catégorie:Esthétique Catégorie:1928 Catégorie:De Stijl La polarité fondamentale de la structure naturelle est conditionnée dans tout ce qui nous entoure, par le
- `Théâtre de Meilhac et Halévy - Tome 7` (single, 419 B): Le prince \| Les Brigands \| La roussotte \| Carmen
- `Lafitte` (single, 16,161 B): I Une figure de Français, devenue légendaire en Louisiane, c’est celle de Jean Lafitte, que la tradition qualifie, tour à tour, de corsaire et de pirate, qui fu
- `Ode à la contemplation du Go` (single, 3,262 B): __EXPECTWITHOUTSCANS__ 觀棋長吟 — 观棋长吟, Ode à la contemplation du Go. Chinois classique : 觀棋長吟 院靜春深晝掩扉，竹間閑看客爭棋。 搜羅神鬼聚胸臆，措致山河入範圍。 局合龍蛇成陣闘，刧殘鴻鴈破行飛。 殺多項羽坑秦卒，敗劇苻堅畏晉師。 座
- `Le Violon brisé, simple histoire` (single, 632 B): thumb\|center\|500px\|Le Violon brisé, illustration Faria (couverture du petit format, 1885) Catégorie:Chansons Catégorie:Chansons d'après
- `Archives de l’État en Belgique` (virtual, 923 B): __EXPECTWITHOUTSCANS__ Image == 500px\|center Transcription == Vng tableau des trois roix faicts par Jeronimus bossche sevrant a deux huys ayans pardehors les ar
- `Dictionnaire historique, topographique et biographique de la Mayenne` (subpages, 462,399 B): 
- `Société des Cochons` (single, 3,838 B): Le secrétaire perpétuel de l'Académie des Badouillards, Flambards, Chicards, Braillards et autres Sociétés buvantes Catégorie:Textes sans source scannée Les bad
- `Ordre de Thomas Handy à l'attention de Carl Spaatz` (single, 544 B): Catégorie:Histoire XXe siècle Catégorie:Histoire militaire Catégorie:Politique étasunienne Catégorie:1945 Category:Traduction Wikisource Catégorie:Seconde Guerr
- `Un coup de dés jamais n’abolira le hasard (édition monospaced)` (single, 58,098 B): __EXPECTWITHOUTSCANS__ 1234567890123456789012345678901234567890123456789012345678901234\|1234567890123456789012345678901234567890123456789012345678901234\| _ 10 2
- `Tumulus` (single, 2,530 B): Orbatus === Adieu ! vous n’aurez point de fruits… Adieu, vous que je voulais suivre ! – Enfants qu’on m’enviait, qu’on venait admirer Si charmants qu’à les voir
- `Les Délibérations de la commune de Mozac (France - 63) pendant la Première Guerre mondiale` (single, 18,785 B): __EXPECTWITHOUTSCANS__ Délibérations Délibérations Délibérations Délibérations Introduction === Comment la municipalité de Mozac et ses habitants ont-ils vécu l
- `Précis de la généalogie des comtes et vicomtes de Foix et de Béarn` (single, 20,481 B): __EXPECTWITHOUTSCANS__ Bref PRÉCIS ou RÉSUMÉ, de l’An de Grâce 1664,de la GÉNÉALOGIE de la MAISON des COMTES et VICOMTES de FOIX et de BÉARN. Pour démontrer com
- `Étude sur le corset (1907)` (single, 95,274 B): Mon but avait été primitivement de soutenir une thèse sur l’hygiène du vêtement en général, où chaque chapitre eut été consacré à une partie de cette importante
- `Des devoirs` (single, 12,852 B): Livre I : == Chapitre 1 :=== Bien qu'après avoir, pendant un an, suivi l'enseignement donné par Cratippe, tu doives, mon cher fils Marcus, sous un maître d'une 
- `Lettre à M. Esparon, supérieur et directeur du Séminaire et des écoles de Lyon` (single, 49,775 B): Transcription de Lettres de Mr. P., p.178-197 (Bibliothèque de la Société de Port-Royal, LET. 364 bis) : p. 178 : Je profite avec plaisir de l’honneur que vous 
- `Avadhuta Gita` (single, 37,266 B): __EXPECTWITHOUTSCANS__ Notes de traduction Avadhuta Gîtâ (Devanagari: अवधूत गीता) est un texte de philosophie non-dualiste qui date probablement du 9ème siècle.
- `Qand lo rossignols el foillos` (single, 2,353 B): La version ci-dessous est extraite de Gedichte Der Troubadours in provenzalischer Sprache par C. A. F. Mahn et complété par l’ouvrage Fragments de poésies en La
- `Décret de 1526 de Charles-Quint` (single, 2,941 B): __EXPECTWITHOUTSCANS__ Par ce décret pris en 1526, Charles-Quint fut le premier souverain à interdire l'esclavage des Indiens sur tout le territoire de son empi
- `À M. d’Alembert` (single, 13,763 B): Page\|Rousseau - Collection compl
- `Les Moines de l’Ile-Verte` (single, 625 B): style="background:transparent" valign="top"
- `Testament et inventaire après décès de Jean-Baptiste Blain` (single, 5,386 B): Conservé aux Archives départementales de la Seine-Maritime, fonds du tabellionnage, minutier Coignard, 1751. † J. M. Au nom du Père, du Fils et du Saint-Esprit,
- `Cours de philosophie` (subpages, 280,211 B): __NOTOC__ A. Notions préliminaires === Leçon I. Objet et méthode de la philosophie Leçon II. Objet et méthode de la philosophie (suite) Leçon III. La science et

**Default view** (validated or proofread, complete, not reference): 29,452 works with 118,773 texts; with unknown works shown: 29,551 works (118,827 texts). Works in the default view whose pages are all level 1: 0. Current setting: `show_unknown_by_default = None`.

## 10. Parts table size (WS§5.2)

| parts_cap | works with catalog parts | parts rows | parts table, gzipped | parts_fts, gzipped |
|---|---|---|---|---|
| 500 | 11,575 | 239,896 | 14.29 MB | 2.29 MB |
| 2,000 | 11,600 | 261,999 | 15.38 MB | 2.47 MB |
| ∞ (all, reference works included) | 11,782 | 496,485 | 25.06 MB | 4.61 MB |
| 2,000, compact encoding | 11,600 | 261,999 | 13.43 MB | 2.47 MB |

Rows count multi-part works only, one row per part plus the root row. 26,258 single-page works would add one row each if stored. Sizes are a SQLite file holding only the table (with its index) at gzip level 9. The compact encoding (not in WS§5.1, measured for the cap decision) stores a part's page_title as the suffix after the work root's title where possible, display_title only when it differs from the last title segment, and no per-part quality_pages.

## 11. Named cases (PLAN.md WP1 acceptance, WS§9)

- `La Légende des siècles`: work, mixed, type collection, 132 parts (128 texts), 0 missing, order natural, oversized False, quality unproofread (page-levels), complete False (missing-pages).
- `L’Épopée du ver`: part 132 of `La Légende des siècles` (text, display “L’Épopée du ver”, rule container, searchable True).
- `Clarté d’âmes`: part 49 of `La Légende des siècles` (text, display “Clarté d’âmes”, rule container, searchable True).
- `Madame Bovary`: work, mixed, type book, 57 parts (17 texts), 0 missing, order toc, oversized False, quality validated (page-levels), complete True.

## 13. Implementation notes

- When a page's container link names one of its title ancestors, the tree parent is its deepest existing ancestor below that container (is and en subpages often name the root, skipping an existing section page).
- The Index TOC field (e.g. no `Sammendrag`) counts as TOC links only for the page its Index title field links to; otherwise every member would look TOC-like.
- Display titles come from `current`/`section`/`TITEL`, else the link text in the parent's TOC, else the last title segment. A work's first part row is its root (`front` above 400 characters of own text, else `toc`); `part_count` excludes it.
- Missing parts are red links in the TOCs of the work's root and volumes: subpages of the root always, other titles only in linked or mixed works and on TOC-like pages.
- Junk rules (cleanup or deletion template, placeholder, under 300 bytes) apply to works without scan pages and without any quality signal.

## 12. Random samples for eyeballing (30 per pattern)

### A: collections of root pages (795)

1. `Échec et mat (Feuillet)`: linked, book, 6 parts, 0 missing, order toc, proofread
    - toc: Échec et mat (Feuillet)
    - text: Titre & Personnages (`Feuillet Echec et mat/Titre & Personnages`) [container]
    - chapter: Acte I (`Feuillet Echec et mat/I`) [container]
    - chapter: Acte II (`Feuillet Echec et mat/II`) [container]
    - chapter: Acte III (`Feuillet Echec et mat/III`) [container]
    - chapter: Acte IV (`Feuillet Echec et mat/IV`) [container]
    - chapter: Acte V (`Feuillet Echec et mat/V`) [container]
1. `Œuvres complètes d’Estienne de La Boétie - Bonnefon 1892`: linked, collection, 7 parts, 0 missing, order toc, unproofread
    - toc: Œuvres complètes d’Estienne de La Boétie - Bonnefon 1892
    - chapter: Préface (`Œuvres complètes d’Estienne de La Boétie/Préface`) [container]
    - chapter: Introduction (`Œuvres complètes d’Estienne de La Boétie/Introduction`) [container]
    - text: Discours de la Servitude volontaire (`Discours de la servitude volontaire (édition Bonnefon)`) [container]
    - text: Avertissement au lecteur par M. de Montaigne (`Œuvres complètes d’Estienne de La Boétie/Avertissement au lecteur par M. de Montaigne`) [container]
    - chapter: Lettre à M. de Lansac (`Œuvres complètes d’Estienne de La Boétie/Lettre à M. de Lansac`) [container]
    - chapter: Lettre à M. de Mesmes (`Œuvres complètes d’Estienne de La Boétie/Lettre à M. de Mesmes`) [container]
    - text: Les règles de mariage de Plutarque (`Œuvres complètes d’Estienne de La Boétie/Les Règles de mariage de Plutarque`) [container]
1. `L’Homme-bicycle`: linked, book, 4 parts, 0 missing, order toc, validated
    - toc: L’Homme-bicycle
    - chapter: 01 (`L’Homme bicycle/01`) [container]
    - chapter: 02 (`L’Homme bicycle/02`) [container]
    - chapter: 03 (`L’Homme bicycle/03`) [container]
    - chapter: 04 (`L’Homme bicycle/04`) [container]
1. `Chants révolutionnaires`: linked, collection, 72 parts, 0 missing, order toc, proofread
    - toc: Chants révolutionnaires
    - chapter: Préface (`Chants révolutionnaires - Préface`) [container]
    - text: Eugène Pottier franc-maçon [container]
    - text: Appréciation de Jules Vallès [container]
    - text: À Napoléon Ier [container]
    - text: Abondance [container]
    - text: La Toile d’araignée [container]
    - text: La Sainte Trinité [container]
    - text: Blanqui [container]
    - text: Droits et Devoirs (`Droits et devoirs`) [container]
    - text: Mangin [container]
    - text: Marguerite (`Marguerite (Pottier)`) [container]
    - … 61 more
1. `Les Vies encloses`: linked, collection, 8 parts, 0 missing, order toc, unproofread
    - toc: Les Vies encloses
    - text: Aquarium mental [container]
    - text: Le Soir dans les vitres [container]
    - text: Les Lignes de la main [container]
    - text: Les Malades aux fenêtres [container]
    - text: Le Voyage dans les yeux [container]
    - text: La Tentation des nuages [container]
    - text: L’Âme sous-marine [container]
    - chapter: Épilogue (`Épilogue (Les Vies encloses)`) [container]
1. `Les Heures claires`: linked, book, 30 parts, 0 missing, order toc, proofread
    - toc: Les Heures claires
    - text: Ô la splendeur de notre joie (`Les Heures claires, 1896/1`) [container]
    - text: Quoique nous le voyions fleurir devant nos yeux (`Les Heures claires, 1896/2`) [container]
    - text: Ce chapiteau barbare où des monstres se tordent (`Les Heures claires, 1896/3`) [container]
    - text: Le ciel en nuit s’est déplié (`Les Heures claires, 1896/4`) [container]
    - text: Chaque heure où je pense à ta bonté (`Les Heures claires, 1896/5`) [container]
    - text: Tu arbores parfois cette grâce bénigne (`Les Heures claires, 1896/6`) [container]
    - text: Oh ! Laisse frapper à la porte (`Les Heures claires, 1896/7`) [container]
    - text: Comme aux âges naïfs je t’ai donné mon cœur (`Les Heures claires, 1896/8`) [container]
    - text: Le printemps jeune et bénévole (`Les Heures claires, 1896/9`) [container]
    - text: Viens lentement t’asseoir (`Les Heures claires, 1896/10`) [container]
    - text: Combien elle est facilement ravie (`Les Heures claires, 1896/11`) [container]
    - … 19 more
1. `Œuvres complètes de Platon, traduites par Émile Chambry`: linked, collection, 22 parts, 2 missing, order toc, unproofread
    - front: Œuvres complètes de Platon, traduites par Émile Chambry
    - text: Notice sur le second Alcibiade [container]
    - text: Le second Alcibiade (`Second Alcibiade (trad. Chambry)`) [toc]
    - text: Notice sur l’Hippias mineur [container]
    - text: Hippias mineur (`Hippias mineur (trad. Chambry)`) [container]
    - text: Notice sur le premier Alcibiade [container]
    - text: Le premier Alcibiade (`Premier Alcibiade (trad. Chambry)`) [toc]
    - text: Notice sur l’Apologie de Socrate [container]
    - text: Apologie de Socrate (`Apologie de Socrate (trad. Chambry)`) [container]
    - text: Notice sur le Criton [container]
    - text: Criton (`Criton (trad. Chambry)`) [container]
    - text: Notice sur l’Euthyphron [container]
    - … 11 more
1. `Anthinéa`: linked, book, 22 parts, 0 missing, order scan, unproofread
    - toc: Anthinéa
    - chapter: 00 (`Anthinea/00`) [container]
    - chapter: Première lettre. — Notre Mer (`Anthinea/01/01/01`) [container]
    - chapter: Deuxième lettre. — Premiers pas (`Anthinea/01/01/02`) [container]
    - text: 02bis (`Anthinea/01/01/02bis`) [container]
    - text: Athènes Antique (`Anthinea/01/02`) [container]
    - text: La Naissance de la Raison, notes du Musée Britannique (`Anthinea/01/03`) [container]
    - chapter: 02 (`Anthinea/02`) [container]
    - chapter: 03 (`Anthinea/03`) [container]
    - chapter: 04 (`Anthinea/04`) [container]
    - chapter: 05 (`Anthinea/05`) [container]
    - text: Le faux printemps (`Anthinea/06/01`) [container]
    - … 11 more
1. `Les Sept Cordes de la lyre (Recueil)`: linked, collection, 9 parts, 0 missing, order toc, validated
    - toc: Les Sept Cordes de la lyre (Recueil)
    - text: Les Sept Cordes de la lyre (`Les Sept Cordes de la lyre (Lévy, 1869)`) [container]
    - text: Lettres à Marcie [container]
    - text: Carl [container]
    - text: Le Dieu inconnu [container]
    - text: La Fille d’Albano [container]
    - text: Cléopâtre (`Cléopâtre (Sand)`) [container]
    - text: Fragment d’une lettre écrite de Fontainebleau [container]
    - text: Les Fleurs de mai [container]
    - text: Coup d’œil général sur Paris (`Les Sept Cordes de la lyre/Coup d’œil général sur Paris`) [container]
1. `Dernières pages`: linked, book, 16 parts, 0 missing, order scan, proofread
    - toc: Dernières pages
    - text: Dans les bois (Sand) [container]
    - text: Nuit d’hiver (Sand) [container]
    - text: Voyage chez M. Blaise [container]
    - text: La Blonde Phœbé [container]
    - text: Mon Grand-Oncle [container]
    - text: Dialogues et fragments philosophiques, par Ernest Renan [container]
    - text: Le Théâtre des marionnettes de Nohant [container]
    - text: La Laitière et le Pot au lait (Sand) [container]
    - text: À propos de la Nouvelle lettre de Junius [container]
    - text: La Flore de Vichy, par Pascal Jourdan [container]
    - text: Mes Campagnes, par Pauline Flaugergues [container]
    - … 5 more
1. `Chaudes saturnales`: linked, book, 3 parts, 0 missing, order toc, proofread
    - toc: Chaudes saturnales
    - chapter: Première partie (`Chaude saturnales/01`) [container]
    - chapter: Deuxième partie (`Chaude saturnales/02`) [container]
    - chapter: Troisième partie (`Chaude saturnales/03`) [container]
1. `Aux vieux de la vieille !`: linked, book, 19 parts, 0 missing, order toc, proofread
    - toc: Aux vieux de la vieille !
    - text: Pourquoi j’écrivis ces Mémoires. (`Aux Vieux de la vieille/01/01`) [container]
    - text: Ma famille. — Malheurs de mon enfance. — Ma jeunesse à Coulommiers. (`Aux Vieux de la vieille/01/02`) [container]
    - text: Le passage du Saint-Bernard. — Montebello. (`Aux Vieux de la vieille/01/03/01`) [container]
    - text: Marengo. — L’Espagne. (`Aux Vieux de la vieille/01/03/02`) [container]
    - text: Les croix de la Légion-d’Honneur. — Le camp de Boulogne. (`Aux Vieux de la vieille/01/04/01`) [container]
    - text: Austerlitz. — Iéna. — Les Grognards. (`Aux Vieux de la vieille/01/04/02`) [container]
    - text: Eylau. — Friedland. — Tilsitt. (`Aux Vieux de la vieille/01/04/03`) [container]
    - text: À l’Empereur . (`Aux Vieux de la vieille/02/00`) [container]
    - text: Campagne d’Espagne. — Essling. — Wagram. (`Aux Vieux de la vieille/02/01`) [container]
    - chapter: 1810. — 1811. (`Aux Vieux de la vieille/02/02`) [container]
    - text: Campagne de Russie. (`Aux Vieux de la vieille/02/03`) [container]
    - … 8 more
1. `Poussières`: linked, collection, 27 parts, 0 missing, order toc, validated
    - toc: Poussières
    - text: La Haine du Soleil (`La Haine du soleil`) [container]
    - text: Le Buste jaune [container]
    - text: Le Cid (`Le Cid (Barbey d’Aurevilly)`) [container]
    - text: Le vieux Goëland (`Le Vieux Goëland`) [container]
    - text: À qui rêves-tu… (`« À qui rêves-tu »`) [container]
    - text: Débouclez-les, vos longs cheveux… (`« Débouclez-les, vos longs cheveux »`) [container]
    - text: Oh ! les yeux adorés… (`« Oh ! les yeux adorés »`) [container]
    - text: « Oh ! pourquoi voyager ? »… (`« Oh ! pourquoi voyager ? »`) [container]
    - text: Si tu pleures jamais… (`« Si tu pleures jamais »`) [container]
    - text: Oh ! comme tu vieillis !… (`« Oh ! comme tu vieillis ! »`) [container]
    - text: L’Échanson [container]
    - … 16 more
1. `Élégies (Verlaine)`: linked, collection, 12 parts, 0 missing, order toc, validated
    - toc: Élégies (Verlaine)
    - text: À mon âge, je sais [container]
    - text: Je me demande encor [container]
    - text: D’après ce que j’ai vu [container]
    - text: Notre union plutôt véhémente [container]
    - text: Incorrigible, toi [container]
    - text: J’ai dit ailleurs l’orgueil [container]
    - text: Enfin c’est toi ! [container]
    - text: Vrai, là, mais quel bourreau [container]
    - text: Tu fais tant partie intégrante [container]
    - text: Dans le peu de défauts [container]
    - text: Bah ! ce n’est pas à vous [container]
    - … 1 more
1. `Œuvres complètes de Saint-Gelais`: linked, collection, 7 parts, 0 missing, order natural, unproofread
    - front: Œuvres complètes de Saint-Gelais
    - text: A Clement Marot [container]
    - text: Asseuré suis d’estre prys et lyé [container]
    - text: Au Seigneur des Essars N de Herberay [container]
    - text: Blason de l’œil [container]
    - text: Ce sonet fut faict au nom de Madamoiselle de Traves [container]
    - text: Des femmes je suis appelé [container]
    - text: À Pierre de Ronsard [container]
1. `Poésies (Klopstock)`: linked, book, 6 parts, 0 missing, order toc, validated
    - toc: Poésies (Klopstock)
    - text: Les Constellations [container]
    - text: Les Deux Muses (`Les Deux Muses (Klopstock)`) [container]
    - text: Psaume (`Psaume (Klopstock)`) [container]
    - text: Mon Erreur (`Mon erreur`) [container]
    - text: Hermann et Trusnelda [container]
    - text: Hermann chanté par les bardes Werdomar, Kerding et Darmont (`Hermann chanté par les bardes`) [container]
1. `A Selection of the Poetries and Comedies`: linked, collection, 5 parts, 0 missing, order toc, unproofread
    - toc: A Selection of the Poetries and Comedies
    - text: Au lecteur (`Musset - Poésies, édition 1895/Au lecteur`) [container]
    - text: — Dédicace (`Musset - Poésies, édition 1895/La Coupe et les Lèvres — Dédicace`) [container]
    - text: Lucie (`Musset - Poésies, édition 1895/Lucie`) [container]
    - text: La Nuit de Mai (`Musset - Poésies, édition 1895/La Nuit de Mai`) [container]
    - text: La Nuit de Décembre (`Musset - Poésies, édition 1895/La Nuit de Décembre`) [container]
1. `Leçons de littérature allemande`: linked, book, 4 parts, 0 missing, order toc, unproofread
    - toc: Leçons de littérature allemande
    - text: Le Cheval et l’âne (`Le Cheval et l’âne (Gleim)`) [container]
    - text: Le Serpent d’eau (`Le Serpent d’eau (Lessing)`) [container]
    - text: Il existe un autre monde [container]
    - text: L’Enfant de la Peine [container]
1. `Le Bhâgavata Purâna, ou Histoire poétique de Krichna`: linked, book, 90 parts, 0 missing, order toc, proofread
    - toc: Le Bhâgavata Purâna, ou Histoire poétique de Krichna
    - chapter: I. (`Le Bhâgavata Purâna/Livre I/Chapitre 1`) [container]
    - chapter: II. (`Le Bhâgavata Purâna/Livre I/Chapitre 2`) [container]
    - chapter: III. (`Le Bhâgavata Purâna/Livre I/Chapitre 3`) [container]
    - chapter: IV. (`Le Bhâgavata Purâna/Livre I/Chapitre 4`) [container]
    - chapter: V. (`Le Bhâgavata Purâna/Livre I/Chapitre 5`) [container]
    - chapter: VI. (`Le Bhâgavata Purâna/Livre I/Chapitre 6`) [container]
    - chapter: VII. (`Le Bhâgavata Purâna/Livre I/Chapitre 7`) [container]
    - chapter: VIII. (`Le Bhâgavata Purâna/Livre I/Chapitre 8`) [container]
    - chapter: IX. (`Le Bhâgavata Purâna/Livre I/Chapitre 9`) [container]
    - chapter: X. (`Le Bhâgavata Purâna/Livre I/Chapitre 10`) [container]
    - chapter: XI. (`Le Bhâgavata Purâna/Livre I/Chapitre 11`) [container]
    - … 79 more
1. `Les Trente-six Dédicaces pour les trente-six quatrains à Madame`: linked, collection, 36 parts, 0 missing, order toc, validated
    - front: Les Trente-six Dédicaces pour les trente-six quatrains à Madame
    - text: À L’Auteur (`Les Trente-six Dédicaces pour Les Trente-six Quatrains à Madame/À L’Auteur`) [container]
    - text: Au Poète (`Les Trente-six Dédicaces pour Les Trente-six Quatrains à Madame/Au Poète`) [container]
    - text: Au Même (`Les Trente-six Dédicaces pour Les Trente-six Quatrains à Madame/Au Même`) [container]
    - text: À des Bergères (`Les Trente-six Dédicaces pour Les Trente-six Quatrains à Madame/À des Bergères`) [container]
    - text: À Claudine (`Les Trente-six Dédicaces pour Les Trente-six Quatrains à Madame/À Claudine`) [container]
    - text: À mon ami Henri Beauclair (`Les Trente-six Dédicaces pour Les Trente-six Quatrains à Madame/À mon ami Henri Beauclair`) [container]
    - text: Au Alain de Lorgeril (`Les Trente-six Dédicaces pour Les Trente-six Quatrains à Madame/Au Vte Alain de Lorgeril`) [container]
    - text: À un myope (`Les Trente-six Dédicaces pour Les Trente-six Quatrains à Madame/À un myope`) [container]
    - text: À Tartufe (`Les Trente-six Dédicaces pour Les Trente-six Quatrains à Madame/À Tartufe`) [container]
    - text: À mon ami Raoul Gineste (`Les Trente-six Dédicaces pour Les Trente-six Quatrains à Madame/À mon ami Raoul Gineste`) [container]
    - text: À quelques-uns (`Les Trente-six Dédicaces pour Les Trente-six Quatrains à Madame/À plusieurs`) [container]
    - … 25 more
1. `Morale d’Aristote`: linked, book, 5 parts, 2 missing, order scan, unproofread
    - toc: Morale d’Aristote
    - text: Morale à Nicomaque, livres I et II. (`Morale à Nicomaque/1`) [container]
    - chapter: 2 (`Morale à Nicomaque/2`) [container]
    - text: Grande Morale. (`Grande Morale (trad. Ladrange)`) [container]
    - chapter: Livre II (`La Grande Morale/Livre II`) [container]
    - text: Morale à Nicomaque [container]
1. `Novalis (Lichtenberger)`: linked, book, 8 parts, 0 missing, order toc, proofread
    - toc: Novalis (Lichtenberger)
    - chapter: CHAPITRE I (`Novalis (Lichterberger)/Chapitre 1`) [container]
    - chapter: CHAPITRE II (`Novalis (Lichterberger)/Chapitre 2`) [container]
    - chapter: CHAPITRE III (`Novalis (Lichterberger)/Chapitre 3`) [container]
    - chapter: CHAPITRE IV (`Novalis (Lichterberger)/Chapitre 4`) [container]
    - chapter: CHAPITRE V (`Novalis (Lichterberger)/Chapitre 5`) [container]
    - chapter: CHAPITRE VI (`Novalis (Lichterberger)/Chapitre 6`) [container]
    - chapter: Conclusion (`Novalis (Lichterberger)/Conclusion`) [container]
    - text: Bibliographie (`Novalis (Lichterberger)/Bibliographie`) [container]
1. `Dictionaire de l’ancien droit du Canada`: linked, book, 7 parts, 0 missing, order toc, proofread
    - toc: Dictionaire de l’ancien droit du Canada
    - chapter: Avant-propos (`Dictionnaire de l’ancien droit du Canada/Avant-propos`) [container]
    - text: B (`Dictionnaire de l’ancien droit du Canada/B`) [container]
    - chapter: C (`Dictionnaire de l’ancien droit du Canada/C`) [container]
    - chapter: D (`Dictionnaire de l’ancien droit du Canada/D`) [container]
    - text: E (`Dictionnaire de l’ancien droit du Canada/E`) [container]
    - text: F (`Dictionnaire de l’ancien droit du Canada/F`) [container]
    - text: A (`Dictionnaire de l’ancien droit du Canada/A`) [container]
1. `Rapport de Lord Durham sur les affaires de l'Amérique septentrionale britannique`: linked, document, 5 parts, 0 missing, order toc, proofread
    - toc: Rapport de Lord Durham sur les affaires de l'Amérique septentrionale britannique
    - chapter: Introduction (`Rapport de Lord Durham/01`) [container]
    - text: Le Bas-Canada (`Rapport de Lord Durham/02`) [container]
    - text: Les Provinces de l’Est et de Terre Neuve (`Rapport de Lord Durham/03`) [container]
    - text: Dispositions des terres publiques — Émigration (`Rapport de Lord Durham/04`) [container]
    - chapter: Conclusion (`Rapport de Lord Durham/05`) [container]
1. `Le vent se lève`: linked, book, 3 parts, 0 missing, order toc, unproofread
    - toc: Le vent se lève
    - text: JOURNAL DE MONSIEUR ROUSSEAU (`Marius Grout - Le vent se lève/1`) [container]
    - text: JOURNAL DE L’ABBÉ COURTIN (`Marius Grout - Le vent se lève/2`) [container]
    - text: LE DOCTEUR SAMUEL À FRANÇOIS DECONIHOUT (`Marius Grout - Le vent se lève/3`) [container]
1. `Le Numéro treize`: linked, book, 12 parts, 0 missing, order toc, proofread
    - toc: Le Numéro treize
    - text: Le petit villageois (`Le numéro treize/01`) [container]
    - text: La veillée (`Le numéro treize/02`) [container]
    - text: Le père Lascience met la morale en action (`Le numéro treize/03`) [container]
    - text: Le Freluquet (`Le numéro treize/04`) [container]
    - text: La langue de la Toinon (`Le numéro treize/05`) [container]
    - text: Tout droit (`Le numéro treize/06`) [container]
    - text: Le Charme (`Le numéro treize/07`) [container]
    - text: Le Conscrit (`Le numéro treize/08`) [container]
    - text: En route ! (`Le numéro treize/09`) [container]
    - text: L’école (`Le numéro treize/10`) [container]
    - text: Heur et malheur (`Le numéro treize/11`) [container]
    - … 1 more
1. `Le Roman de Violette`: linked, book, 10 parts, 0 missing, order chain, proofread
    - toc: Le Roman de Violette
    - chapter: Avant-Propos (`Le roman de Violette/00`) [container]
    - chapter: Chapitre I. (`Le roman de Violette/01`) [container]
    - chapter: Chapitre II. (`Le roman de Violette/02`) [container]
    - chapter: Chapitre III. (`Le roman de Violette/03`) [container]
    - chapter: Chapitre IV. (`Le roman de Violette/04`) [container]
    - chapter: Chapitre V. (`Le roman de Violette/05`) [container]
    - chapter: Chapitre VI. (`Le roman de Violette/06`) [container]
    - chapter: Chapitre VII. (`Le roman de Violette/07`) [container]
    - chapter: Chapitre VIII. (`Le roman de Violette/08`) [container]
    - chapter: Chapitre IX. (`Le roman de Violette/09`) [container]
1. `Gérard et Delphine — La Porte rouge`: linked, book, 4 parts, 0 missing, order scan, unproofread
    - toc: Gérard et Delphine — La Porte rouge
    - chapter: 1 (`Gérard et Delphine/La Porte rouge/1`) [container]
    - chapter: 2 (`Gérard et Delphine/La Porte rouge/2`) [container]
    - chapter: 3 (`Gérard et Delphine/La Porte rouge/3`) [container]
    - chapter: 25 (`Gérard et Delphine/La Porte rouge/25`) [container]
1. `Nouveaux Contes cruels`: linked, book, 1 parts, 0 missing, order single, validated
    - toc: Nouveaux Contes cruels
    - chapter: Note de l’éditeur (`Nouveaux contes cruels/Note de l’éditeur`) [container]
1. `L’Énéide (traduction Jacques Delille)`: linked, book, 6 parts, 6 missing, order toc, unproofread
    - toc: L’Énéide (traduction Jacques Delille)
    - chapter: Livre I (`L’Énéide (trad. Delille) - I`) [container]
    - chapter: Livre II (`L’Énéide (trad. Delille) - II`) [container]
    - chapter: Livre III (`L’Énéide (trad. Delille) - III`) [container]
    - chapter: Livre IV (`L’Énéide (trad. Delille) - IV`) [container]
    - chapter: Livre VI (`L’Énéide (trad. Delille) - VI`) [container]
    - chapter: Livre VIII (`L’Énéide (trad. Delille) - VIII`) [container]

### B: mixed (396)

1. `Poètes d’aujourd’hui`: mixed, collection, 8 parts, 51 missing, order natural, unproofread
    - toc: Poètes d’aujourd’hui
    - text: Le Dernier Chant d’Orphée [container]
    - text: Henri (`Poètes d’aujourd’hui/Henri Barbusse`) [container]
    - text: Henry (`Poètes d’aujourd’hui/Henry Bataille`) [container]
    - chapter: Introduction (`Poètes d’aujourd’hui/Introduction`) [container]
    - chapter: Introduction à la première édition (`Poètes d’aujourd’hui/Introduction à la première édition`) [container]
    - text: Maurice (`Poètes d’aujourd’hui/Maurice Maeterlinck`) [container]
    - text: Sappho (`Poètes d’aujourd’hui/Sappho`) [container]
    - chapter: Table détaillée (`Poètes d’aujourd’hui/Table détaillée`) [container]
1. `Le Livre d’un inconnu`: mixed, collection, 43 parts, 0 missing, order toc, proofread
    - toc: Le Livre d’un inconnu
    - text: Le Masque (`Le Masque (Haag)`) [container]
    - text: Minuit (`Minuit (Haag)`) [container]
    - text: À la Préfecture (`A la prefecture (Haag)`) [container]
    - text: Ô rêverie, ô fée, ô douce enchanteresse (`Ô rêverie, ô fée, ô douce enchanteresse ! (Haag)`) [container]
    - text: Terreur nocturne (`Terreur Nocturne (Haag)`) [container]
    - text: Nuit de Juin (`Nuit de Juin (Haag)`) [container]
    - text: Paysage (`Paysage (Haag)`) [container]
    - text: La voir, sentir son bras s’appuyer sur le mien (`La voir, sentir son bras s'appuyer sur le mien`) [container]
    - text: Premier Printemps (`Premier Printemps (Haag)`) [container]
    - text: Sais-tu combien il est parfois mélancolique (`Sais-tu combien il est parfois mélancolique (Haag)`) [container]
    - text: Loin du centre élégant où la foule se presse (`Loin du centre élégant où la foule se presse (Haag)`) [container]
    - … 32 more
1. `Idylles héroïques`: mixed, collection, 47 parts, 0 missing, order single, unproofread
    - toc: Idylles héroïques
    - volume: Les Symphonies [container]
      - text: Asile [container]
      - text: Dédicace. Au pays de Forez (`Au pays de Forez`) [container]
      - text: Au pied de la Croix [container]
      - text: Conseils des champs [container]
      - text: Consolation (`Consolation (Laprade)`) [container]
      - text: Entre deux orages [container]
      - text: Feuilles, tombez [container]
      - chapter: I. (`Herman/I`) [container]
      - chapter: II. (`Herman/II`) [container]
      - chapter: III. (`Herman/III`) [container]
    - … 36 more
1. `Œuvres politiques de Machiavel (Louandre)`: mixed, collection, 42 parts, 0 missing, order scan, proofread
    - toc: Œuvres politiques de Machiavel (Louandre)
    - text: Page de titre (`Œuvres politiques de Machiavel (Louandre)/Titre`) [container]
    - text: Avertissement (`Œuvres politiques de Machiavel (Louandre)/Avertissement`) [container]
    - text: Vie de Nicolas Machiavel (`Œuvres politiques de Machiavel (Louandre)/Biographie`) [container]
    - text: Dédicace (`Le Prince/Dédicace`) [container]
    - chapter: Chapitre 1 (`Le Prince/Chapitre 1`) [container]
    - chapter: Chapitre 2 (`Le Prince/Chapitre 2`) [container]
    - chapter: Chapitre 3 (`Le Prince/Chapitre 3`) [container]
    - chapter: Chapitre 4 (`Le Prince/Chapitre 4`) [container]
    - chapter: Chapitre 5 (`Le Prince/Chapitre 5`) [container]
    - chapter: Chapitre 6 (`Le Prince/Chapitre 6`) [container]
    - chapter: Chapitre 7 (`Le Prince/Chapitre 7`) [container]
    - … 31 more
1. `Les Étoiles filantes (Gill)`: mixed, collection, 48 parts, 0 missing, order single, unproofread
    - toc: Les Étoiles filantes (Gill)
    - volume: Le Cap Éternité [container]
      - volume: Traductions d’Horace (Gill) [container]
        - chapter: Préface (`Le Cap Éternité/Préface`) [container]
        - chapter: Prologue (`Le Cap Éternité/Prologue`) [container]
        - text: Le Goéland (`Le Cap Éternité/Chant I`) [container]
        - text: La Cloche (`Le Cap Éternité/Chant II`) [container]
        - text: Le Désespoir (`Le Cap Éternité/Chant III`) [container]
        - text: Le Silence et l’Oubli (`Le Cap Éternité/Chant IV`) [container]
        - text: Clair de lune (`Le Cap Éternité/Chant V`) [container]
        - text: Aurore (`Le Cap Éternité/Chant VI`) [container]
        - text: Ave Maria (`Le Cap Éternité/Chant VII`) [container]
    - … 37 more
1. `Œuvres poétiques (Malherbe)`: mixed, collection, 86 parts, 0 missing, order scan, unproofread
    - toc: Œuvres poétiques (Malherbe)
    - text: Malherbe et ses poésies [container]
    - text: Au Roy Henry le Grand sur la prise de Marseille (`La prise de Marseille I (Malherbe)`) [container]
    - text: Strophe composée pour l’ode qui précède [container]
    - text: Sur le même sujet [container]
    - text: À la reine, pour sa bienvenue en France [container]
    - text: Sur l’attentat commis en la personne de Henry le Grand, le 10 décembre 1605 (`Sur l’attentat commis en la personne du roi, le 19 décembre 1605`) [container]
    - text: Au Roy Henry le Grand sur l’heureux succez du voyage de Sedan (`L’Heureux Succès du voyage de Sedan`) [container]
    - text: Strophe préparée, et non employée, pour terminer l’ode qui précède [container]
    - text: À M. de Bellegarde, grand escuyer de France [container]
    - text: À la Reine, mère du Roy, sur les heureux succez de sa régence (`Les Heureux Succès de la Régence`) [container]
    - text: À la reine, pendant sa régence [container]
    - … 75 more
1. `Œuvres complètes de Molière (Louandre)`: mixed, collection, 63 parts, 0 missing, order natural, proofread
    - front: Œuvres complètes de Molière (Louandre)
    - text: La Comtesse d’Escarbagnas [container]
    - text: La Gloire du dôme du Val-de-Grâce [container]
    - text: Le Bourgeois gentilhomme (`Le Bourgeois gentilhomme/Édition Louandre, 1910`) [container]
    - chapter: Acte I (`Le Bourgeois gentilhomme/Édition Louandre, 1910/Acte I`) [container]
    - chapter: Acte II (`Le Bourgeois gentilhomme/Édition Louandre, 1910/Acte II`) [container]
    - chapter: Acte III (`Le Bourgeois gentilhomme/Édition Louandre, 1910/Acte III`) [container]
    - chapter: Acte IV (`Le Bourgeois gentilhomme/Édition Louandre, 1910/Acte IV`) [container]
    - chapter: Acte V (`Le Bourgeois gentilhomme/Édition Louandre, 1910/Acte V`) [container]
    - text: Noms des personnes qui ont chanté et dansé (`Le Bourgeois gentilhomme/Édition Louandre, 1910/Noms des personnes qui ont chanté et dansé`) [container]
    - text: Notice (`Le Bourgeois gentilhomme/Édition Louandre, 1910/Notice`) [container]
    - text: Personnages (`Le Bourgeois gentilhomme/Édition Louandre, 1910/Personnages`) [container]
    - … 52 more
1. `Le Folk-lore de l’Île-Maurice`: mixed, book, 66 parts, 0 missing, order scan, unproofread
    - toc: Le Folk-lore de l’Île-Maurice
    - volume: Le Folk-lore de l’Île-Maurice (Texte créole) [container]
      - text: Zistoire iève av tourtie dans bord bassin léroi (`Le Folk-lore de l’Île-Maurice (Texte créole)/Le lièvre et la tortue au bord du bassin du roi`) [container]
      - text: Histoire des Colophanes (`Le Folk-lore de l’Île-Maurice (Texte créole)/Histoire des Colophanes`) [container]
      - text: Histoire du lièvre, de l’éléphant et de la baleine (`Le Folk-lore de l’Île-Maurice (Texte créole)/Histoire du lièvre, de l’éléphant et de la baleine`) [container]
      - text: Histoire de Petit-Jean Queue-de-Bœuf (`Le Folk-lore de l’Île-Maurice (Texte créole)/Histoire de Petit-Jean Queue-de-Bœuf`) [container]
      - text: Histoire de bonhomme Francœur (`Le Folk-lore de l’Île-Maurice (Texte créole)/Histoire de bonhomme Francœur`) [container]
      - text: Histoire d’un oiseau qui pondait des œufs d’or (`Le Folk-lore de l’Île-Maurice (Texte créole)/Histoire d’un oiseau qui pondait des œufs d’or`) [container]
      - text: Histoire d’un malin drôle (`Le Folk-lore de l’Île-Maurice (Texte créole)/Histoire d’un malin drôle`) [container]
      - text: Histoire de Jean et de Jeanne (`Le Folk-lore de l’Île-Maurice (Texte créole)/Histoire de Jean et de Jeanne`) [container]
      - text: Histoire de Namcouticouti (`Le Folk-lore de l’Île-Maurice (Texte créole)/Histoire de Namcouticouti`) [container]
      - text: L’éléphant et le lièvre en société (`Le Folk-lore de l’Île-Maurice (Texte créole)/L’éléphant et le lièvre en société`) [container]
    - … 55 more
1. `Archives curieuses de l’Histoire de France`: mixed, collection, 2 parts, 25 missing, order toc, proofread
    - toc: Archives curieuses de l’Histoire de France
    - text: Avertissement (`Archives curieuses de l’Histoire de France/Avertissement`) [container]
    - text: Le réveille-matin des François et de leurs voisins (`Le Réveille-matin des François et de leurs voisins`) [container]
1. `Œuvres complètes de Joachim Du Bellay, tome 3`: mixed, collection, 5 parts, 1 missing, order scan, proofread
    - toc: Œuvres complètes de Joachim Du Bellay, tome 3
    - text: Avertissement (`Œuvres complètes de Joachim Du Bellay, tome 3/Avertissement`) [container]
    - text: Les Antiquités de Rome [container]
    - text: Les Regrets (`Les Regrets (du Bellay)`) [container]
    - text: D’un vanneur de bled aux vents (`Divers jeux rustiques/D’un vanneur de bled aux vents`) [container]
    - text: Autre baiser (`Divers jeux rustiques/Autre baiser`) [container]
1. `Œuvres poétiques de Christine de Pisan`: mixed, collection, 336 parts, 11 missing, order toc, proofread
    - toc: Œuvres poétiques de Christine de Pisan
    - chapter: Introduction (`Œuvres poétiques de Christine de Pisan/Introduction`) [container]
    - volume: CENT BALADES (`Cent Ballades (Christine de Pisan)`) [container]
      - text: Pour acomplir leur bonne voulenté (`Cent Ballades (Christine de Pisan)/Ballade I`) [hierarchy]
      - text: Digne d’estre de lorier couronné (`Cent Ballades (Christine de Pisan)/Ballade II`) [hierarchy]
      - text: Voyez comment amours amans ordonne ! (`Cent Ballades (Christine de Pisan)/Ballade III`) [hierarchy]
      - text: En traïson, non pas par vacellage (`Cent Ballades (Christine de Pisan)/Ballade IV`) [hierarchy]
      - text: Quant cil est mort qui me tenoit en vie (`Cent Ballades (Christine de Pisan)/Ballade V`) [hierarchy]
      - text: Et si ne puis ne garir ne morir (`Cent Ballades (Christine de Pisan)/Ballade VI`) [hierarchy]
      - text: Qui ma vie tenoit joyeuse (`Cent Ballades (Christine de Pisan)/Ballade VII`) [hierarchy]
      - text: C’est bien raison que me doye doloir (`Cent Ballades (Christine de Pisan)/Ballade VIII`) [hierarchy]
      - text: Que mes griefs maulx soyent par toy délivre (`Cent Ballades (Christine de Pisan)/Ballade IX`) [hierarchy]
    - … 325 more
1. `Pour l’histoire de la science hellène`: mixed, book, 18 parts, 0 missing, order scan, unproofread
    - toc: Pour l’histoire de la science hellène
    - chapter: Préface (`Pour l’histoire de la science hellène/Préface`) [container]
    - chapter: Introduction (`Pour l’histoire de la science hellène/Introduction`) [container]
    - chapter: 1 (`Pour l’histoire de la science hellène/1`) [container]
    - chapter: 2 (`Pour l’histoire de la science hellène/2`) [container]
    - chapter: 3 (`Pour l’histoire de la science hellène/3`) [container]
    - chapter: 4 (`Pour l’histoire de la science hellène/4`) [container]
    - chapter: 5 (`Pour l’histoire de la science hellène/5`) [container]
    - chapter: 6 (`Pour l’histoire de la science hellène/6`) [container]
    - chapter: 7 (`Pour l’histoire de la science hellène/7`) [container]
    - chapter: 8 (`Pour l’histoire de la science hellène/8`) [container]
    - chapter: 9 (`Pour l’histoire de la science hellène/9`) [container]
    - … 7 more
1. `La Bonne aventure (Sue)/1`: mixed, book, 65 parts, 0 missing, order single, proofread
    - toc: La Bonne aventure (Sue)/1
    - volume: La Bonne aventure (Sue) [container]
      - chapter: I (`La Bonne aventure (Sue)/1/I`) [container]
      - chapter: II (`La Bonne aventure (Sue)/1/II`) [container]
      - chapter: III (`La Bonne aventure (Sue)/1/III`) [container]
      - chapter: IV (`La Bonne aventure (Sue)/1/IV`) [container]
      - chapter: IX (`La Bonne aventure (Sue)/1/IX`) [container]
      - chapter: V (`La Bonne aventure (Sue)/1/V`) [container]
      - chapter: VI (`La Bonne aventure (Sue)/1/VI`) [container]
      - chapter: VII (`La Bonne aventure (Sue)/1/VII`) [container]
      - chapter: VIII (`La Bonne aventure (Sue)/1/VIII`) [container]
      - chapter: X (`La Bonne aventure (Sue)/1/X`) [container]
    - … 54 more
1. `Les Chansons des rues et des bois`: mixed, collection, 79 parts, 0 missing, order toc, proofread
    - toc: Les Chansons des rues et des bois
    - chapter: PRÉFACE (`Les Chansons des rues et des bois/Préface`) [container]
    - text: LE CHEVAL (`Le Cheval`) [container]
    - text: . I : Ordre du jour de Floréal (`Ordre du jour de Floréal`) [container]
    - text: Orphée au bois du Caystre (`« Orphée, au bois du Caystre »`) [container]
    - text: . III : ΨΥΧΗ (`ΨΥΧΗ`) [container]
    - text: . IV : Le poète bat aux champs (`Le poète bat aux champs`) [container]
    - text: . V : Interruption à une lecture de Platon (`Interruption à une lecture de Platon`) [container]
    - text: . VI (`« Quand les guignes furent mangées »`) [container]
    - text: . VI : Genio libri (`Genio libri`) [container]
    - text: . I : Paulo « minora » canamus (`Paulo « minora » canamus`) [container]
    - text: . II : Réalité (`Réalité`) [container]
    - … 68 more
1. `Histoire des églises et chapelles de Lyon`: mixed, book, 127 parts, 65 missing, order natural, proofread
    - toc: Histoire des églises et chapelles de Lyon
    - text: Abbaye Saint-Pierre (`Histoire des églises et chapelles de Lyon/Abbaye Saint-Pierre`) [container]
    - text: Ainay (`Histoire des églises et chapelles de Lyon/Ainay`) [container]
    - text: Annonciation (`Histoire des églises et chapelles de Lyon/Annonciation`) [container]
    - text: Antiquaille (`Histoire des églises et chapelles de Lyon/Antiquaille`) [container]
    - text: Antonins (`Histoire des églises et chapelles de Lyon/Antonins`) [container]
    - text: Bernardines (`Histoire des églises et chapelles de Lyon/Bernardines`) [container]
    - text: Bon-Pasteur (`Histoire des églises et chapelles de Lyon/Bon-Pasteur`) [container]
    - text: Béchevelin (`Histoire des églises et chapelles de Lyon/Béchevelin`) [container]
    - text: Bénédictines de Chazeaux (`Histoire des églises et chapelles de Lyon/Bénédictines de Chazeaux`) [container]
    - text: Calvaire (`Histoire des églises et chapelles de Lyon/Calvaire`) [container]
    - text: Carmes-Déchaussés (`Histoire des églises et chapelles de Lyon/Carmes-Déchaussés`) [container]
    - … 116 more
1. `L’Idée fixe du savant Cosinus`: mixed, book, 121 parts, 0 missing, order toc, proofread
    - front: L’Idée fixe du savant Cosinus
    - chapter: Préface (`L’Idée fixe du savant Cosinus/Préface`) [container]
    - text: L’enfance de Zéphyrin (`L’Idée fixe du savant Cosinus/01`) [container]
    - text: Comme quoi Cosinus devint un grand savant (`L’Idée fixe du savant Cosinus/02`) [container]
    - text: Le docteur Cosinus a mal aux dents (`L’Idée fixe du savant Cosinus/03`) [container]
    - text: Cosinus commet une méprise arithmétique (`L’Idée fixe du savant Cosinus/04`) [container]
    - text: Terribles conséquences de la méprise de Cosinus (`L’Idée fixe du savant Cosinus/05`) [container]
    - text: Cosinus myriapode (`L’Idée fixe du savant Cosinus/06`) [container]
    - text: reçoit une douche (`L’Idée fixe du savant Cosinus/07`) [container]
    - text: Cosinus fait ses préparatifs de départ (`L’Idée fixe du savant Cosinus/08`) [container]
    - text: ! (`L’Idée fixe du savant Cosinus/09`) [container]
    - text: Cosinus manque son premier départ (`L’Idée fixe du savant Cosinus/10`) [container]
    - … 110 more
1. `L’Année terrible`: mixed, collection, 98 parts, 0 missing, order natural, proofread
    - toc: L’Année terrible
    - text: 1 janvier (`L’Année terrible/1er janvier`) [container]
    - text: Ah ! c’est un rêve ! non ! nous n’y consentons point (`L’Année terrible/Ah ! c’est un rêve ! non ! nous n’y consentons point`) [container]
    - text: Après les victoires de Bapaume, de Dijon, et de Villersexel (`L’Année terrible/Après les victoires de Bapaume, de Dijon, et de Villersexel`) [container]
    - text: Au canon le V. H. (`L’Année terrible/Au canon le V. H.`) [container]
    - text: Aux rêveurs de monarchie (`L’Année terrible/Aux rêveurs de monarchie`) [container]
    - text: Avant la conclusion du traité (`L’Année terrible/Avant la conclusion du traité`) [container]
    - text: Bancroft (`L’Année terrible/Bancroft`) [container]
    - text: Bêtise de la guerre (`L’Année terrible/Bêtise de la guerre`) [container]
    - text: Capitulation (`L’Année terrible/Capitulation`) [container]
    - text: Choix entre les deux nations (`L’Année terrible/Choix entre les deux nations`) [container]
    - text: Coup sur coup. Deuil sur deuil. Ah ! l’épreuve redouble (`L’Année terrible/Coup sur coup. Deuil sur deuil. Ah ! l’épreuve redouble`) [container]
    - … 87 more
1. `Césarine Dietrich (RDDM)`: mixed, periodical-issue, 5 parts, 2 missing, order toc, proofread
    - toc: Césarine Dietrich (RDDM)
    - text: - 14 juillet 1870 (`Chronique de la quinzaine - 14 juillet 1870`) [toc]
    - text: - 31 juillet 1870 (`Chronique de la quinzaine - 31 juillet 1870`) [toc]
    - text: - 14 août 1870 (`Chronique de la quinzaine - 14 août 1870`) [toc]
    - chapter: Première partie (`Césarine Dietrich (RDDM)/01`) [hierarchy]
    - chapter: Dernière partie (`Césarine Dietrich (RDDM)/04`) [hierarchy]
1. `L’Œuvre d’une nuit de mai`: mixed, collection, 40 parts, 0 missing, order single, proofread
    - toc: L’Œuvre d’une nuit de mai
    - volume: Cousine Phillis [container]
      - text: Élisabeth Gaskell et ses ouvrages [container]
      - chapter: Chapitre 1 (`Cousine Phillis/01`) [container]
      - chapter: Chapitre 2 (`Cousine Phillis/02`) [container]
      - chapter: Chapitre 3 (`Cousine Phillis/03`) [container]
      - chapter: Chapitre 4 (`Cousine Phillis/04`) [container]
      - chapter: Chapitre 5 (`Cousine Phillis/05`) [container]
      - chapter: Chapitre 6 (`Cousine Phillis/06`) [container]
      - chapter: Chapitre 7 (`Cousine Phillis/07`) [container]
      - chapter: Chapitre 8 (`Cousine Phillis/08`) [container]
      - chapter: Chapitre 9 (`Cousine Phillis/09`) [container]
    - … 29 more
1. `Les Poèmes d’Edgar Poe`: mixed, collection, 39 parts, 0 missing, order natural, unproofread
    - front: Les Poèmes d’Edgar Poe
    - text: Annabel Lee [container]
    - text: Ballade de Noces (`Ballade de noces`) [container]
    - text: Chanson (`Chanson (Les Poèmes d’Edgar Poe)`) [container]
    - text: Eldorado [container]
    - text: Eulalie [container]
    - text: Féerie (`Féerie (Les Poèmes d’Edgar Poe)`) [container]
    - text: Israfel (`Israfel (Poe)`) [container]
    - text: La Cité en la Mer (`La Cité en la mer (Les Poèmes d’Edgar Poe)`) [container]
    - text: La Dormeuse (`La Dormeuse (Les Poèmes d’Edgar Poe)`) [container]
    - text: La Romance [container]
    - text: La Vallée de l’Inquiétude (`La Vallée de l’inquiétude`) [container]
    - … 28 more
1. `La Légende des siècles`: mixed, collection, 132 parts, 0 missing, order natural, unproofread
    - front: La Légende des siècles
    - text: La Légende des siècles, tome II [container]
    - volume: Première série, 1859 (`La Légende des siècles/1e série, 1859`) [container]
      - text: Dédicace (`La Légende des siècles/Dédicace`) [container]
      - chapter: Préface (`La Légende des siècles/Préface`) [container]
      - text: Le Sacre de la femme (`La Légende des siècles/Le Sacre de la femme`) [container]
      - text: La Conscience (`La Légende des siècles/La Conscience`) [container]
      - text: Puissance égale bonté (`La Légende des siècles/Puissance égale bonté`) [container]
      - text: Les Lions (`La Légende des siècles/Les Lions`) [container]
      - text: Le Temple (`La Légende des siècles/Le Temple`) [container]
      - text: Booz endormi (`La Légende des siècles/Booz endormi`) [container]
      - text: Dieu invisible au philosophe (`La Légende des siècles/Dieu invisible au philosophe`) [container]
    - … 121 more
1. `La Vie de Marianne (éd. Charpentier)`: mixed, book, 13 parts, 0 missing, order toc, proofread
    - toc: La Vie de Marianne (éd. Charpentier)
    - text: Notice (`La Vie de Marianne/Notice`) [container]
    - text: VIE DE MARIANNE, (`La Vie de Marianne (éd. Charpentier)/Partie 01`) [container]
    - chapter: Partie 02 (`La Vie de Marianne (éd. Charpentier)/Partie 02`) [container]
    - chapter: Partie 03 (`La Vie de Marianne (éd. Charpentier)/Partie 03`) [container]
    - chapter: Partie 04 (`La Vie de Marianne (éd. Charpentier)/Partie 04`) [container]
    - chapter: Partie 05 (`La Vie de Marianne (éd. Charpentier)/Partie 05`) [container]
    - chapter: Partie 06 (`La Vie de Marianne (éd. Charpentier)/Partie 06`) [container]
    - chapter: Partie 07 (`La Vie de Marianne (éd. Charpentier)/Partie 07`) [container]
    - chapter: Partie 08 (`La Vie de Marianne (éd. Charpentier)/Partie 08`) [container]
    - chapter: Partie 09 (`La Vie de Marianne (éd. Charpentier)/Partie 09`) [container]
    - chapter: Partie 10 (`La Vie de Marianne (éd. Charpentier)/Partie 10`) [container]
    - … 2 more
1. `Abolition de l'esclavage (Schœlcher)`: mixed, book, 7 parts, 0 missing, order scan, proofread
    - toc: Abolition de l'esclavage (Schœlcher)
    - text: Au peuple (`Abolition de l'esclavage (Schœlcher)/Au peuple`) [container]
    - chapter: Avant-Propos (`Abolition de l'esclavage (Schœlcher)/Avant-propos`) [container]
    - text: Position de la question (`Abolition de l'esclavage (Schœlcher)/Position`) [container]
    - text: Antériorité de la question (`Abolition de l'esclavage (Schœlcher)/Antériorité`) [container]
    - chapter: CHAPITRE I (`Abolition de l'esclavage (Schœlcher)/Chapitre I`) [container]
    - chapter: Chapitre II (`Abolition de l’esclavage (Schœlcher)/Chapitre II`) [container]
    - chapter: Chapitre III (`Abolition de l’esclavage (Schœlcher)/Chapitre III`) [container]
1. `Polikouchka`: mixed, book, 25 parts, 0 missing, order scan, proofread
    - toc: Polikouchka
    - volume: Table (`Polikouchka/Table`) [hierarchy]
      - chapter: Chapitre 1 (`Polikouchka/Chapitre 1`) [container]
      - chapter: Chapitre 2 (`Polikouchka/Chapitre 2`) [container]
      - chapter: Chapitre 3 (`Polikouchka/Chapitre 3`) [container]
      - chapter: Chapitre 4 (`Polikouchka/Chapitre 4`) [container]
      - chapter: Chapitre 5 (`Polikouchka/Chapitre 5`) [container]
      - chapter: Chapitre 6 (`Polikouchka/Chapitre 6`) [container]
      - chapter: Chapitre 7 (`Polikouchka/Chapitre 7`) [container]
      - chapter: Chapitre 8 (`Polikouchka/Chapitre 8`) [container]
      - chapter: Chapitre 9 (`Polikouchka/Chapitre 9`) [container]
      - chapter: Chapitre 10 (`Polikouchka/Chapitre 10`) [container]
    - … 14 more
1. `Poésies (Amélie Gex)`: mixed, book, 85 parts, 0 missing, order toc, proofread
    - toc: Poésies (Amélie Gex)
    - volume: LE POÈME DE L’ANNÉE (`Le Poème de l’année`) [container]
      - text: Janvier. Il neige ! (`Poésies (Amélie Gex)/Janvier. Il neige !`) [container]
      - text: Mars. Sonnet (`Poésies (Amélie Gex)/Mars`) [container]
      - text: Allégro printanier (`Poésies (Amélie Gex)/Allégro printanier`) [container]
      - chapter: La part du Pauvre. Sonnet (`Poésies (Amélie Gex)/La part du Pauvre`) [container]
      - chapter: Chant d’avril (`Poésies (Amélie Gex)/Chant d’avril`) [container]
      - text: L’Avril des tombes. Sonnet (`Poésies (Amélie Gex)/L’Avril des tombes`) [container]
      - text: Primavera : L’Aube lointaine. Sonnet (`Poésies (Amélie Gex)/Primavera : L’Aube lointaine`) [container]
      - text: Chanson (`Poésies (Amélie Gex)/Chanson`) [container]
      - text: Le Pardon. Sonnet (`Poésies (Amélie Gex)/Le Pardon`) [container]
      - text: En Mai comme les églantines. Sonnet (`Poésies (Amélie Gex)/Sonnet (En Mai comme les églantines)`) [container]
    - … 74 more
1. `Essais sur la conception matérialiste de l’histoire`: mixed, collection, 2 parts, 2 missing, order toc, proofread
    - toc: Essais sur la conception matérialiste de l’histoire
    - chapter: Préface (`Essais sur la conception matérialiste de l’histoire/Préface`) [container]
    - text: Manifeste du parti communiste (`Manifeste du parti communiste/Lafargue`) [container]
1. `Les Minutes de sable mémorial`: mixed, book, 21 parts, 0 missing, order scan, proofread
    - toc: Les Minutes de sable mémorial
    - chapter: Avant-Propos (`Les Minutes de sable mémorial/AvantPropos`) [container]
    - text: Linteau (`Les Minutes de sable mémorial/Linteau`) [container]
    - text: Lieds Funèbres (`Les Minutes de sable mémorial/LiedsFunébres`) [container]
    - text: Les trois Meubles du Mage (`Les Minutes de sable mémorial/TroisMeublesMage`) [container]
    - text: (l’Autoclète, Phonographe, l’Art et la Science) (`Les Minutes de sable mémorial/Guignol`) [container]
    - text: Berceuse du mort (`Les Minutes de sable mémorial/BerceuseMort`) [container]
    - text: L’Opium (`Les Minutes de sable mémorial/Opium`) [container]
    - text: La Châsse (`Les Minutes de sable mémorial/Châsse`) [container]
    - text: Tapisseries (`Les Minutes de sable mémorial/Tapisseries`) [container]
    - text: Les Cinq Sens (`Les Minutes de sable mémorial/CinqSens`) [container]
    - text: L’Homme à la hache (`Les Minutes de sable mémorial/HommeHache`) [container]
    - … 10 more
1. `Poèmes (Verhaeren, 1895)`: mixed, collection, 111 parts, 0 missing, order scan, validated
    - toc: Poèmes (Verhaeren, 1895)
    - text: SONNET (`Sonnet (Verhaeren)`) [container]
    - text: LÀ-BAS (`Là-bas 2 (Verhaeren)`) [container]
    - text: SILENCIEUSEMENT (`Silencieusement (Verhaeren)`) [container]
    - text: UN SOIR (`Un soir 2 (Verhaeren)`) [container]
    - text: QUELQUES-UNS (`Quelques-uns (Verhaeren)`) [container]
    - text: LES FLAMANDES (`Poèmes (Verhaeren, 1895)/Les Flamandes`) [container]
    - text: LES VIEUX MAÎTRES (`Les vieux maîtres (Verhaeren)`) [container]
    - text: LA VACHÈRE (`La vachère (Verhaeren)`) [container]
    - text: ART FLAMAND (`Art flamand (Verhaeren)`) [container]
    - text: LES PLAINES (`Les plaines (Verhaeren)`) [container]
    - text: KATO (`Kato (Verhaeren)`) [container]
    - … 100 more
1. `La Cathédrale (Huysmans)`: mixed, book, 17 parts, 0 missing, order scan, unproofread
    - toc: La Cathédrale (Huysmans)
    - chapter: I. (`La Cathédrale (Huysmans)/I`) [container]
    - chapter: II. (`La Cathédrale (Huysmans)/II`) [container]
    - chapter: III. (`La Cathédrale (Huysmans)/III`) [container]
    - chapter: IV. (`La Cathédrale (Huysmans)/IV`) [container]
    - chapter: V. (`La Cathédrale (Huysmans)/V`) [container]
    - chapter: VI. (`La Cathédrale (Huysmans)/VI`) [container]
    - chapter: VII. (`La Cathédrale (Huysmans)/VII`) [container]
    - text: Le Couronnement de la Vierge [container]
    - chapter: VIII. (`La Cathédrale (Huysmans)/VIII`) [container]
    - chapter: IX. (`La Cathédrale (Huysmans)/IX`) [container]
    - chapter: X. (`La Cathédrale (Huysmans)/X`) [container]
    - … 6 more
1. `Œuvres complètes (Marivaux)`: mixed, collection, 42 parts, 1 missing, order natural, unproofread
    - toc: Œuvres complètes (Marivaux)
    - text: ANNIBAL (`Annibal`) [container]
    - chapter: Acte I (`Annibal/Acte I`) [container]
    - chapter: Acte II (`Annibal/Acte II`) [container]
    - chapter: Acte III (`Annibal/Acte III`) [container]
    - chapter: Acte IV (`Annibal/Acte IV`) [container]
    - chapter: Acte V (`Annibal/Acte V`) [container]
    - text: FÉLICIE (`Félicie`) [container]
    - text: LA DISPUTE (`La Dispute`) [container]
    - text: LA FAUSSE SUIVANTE (`La Fausse Suivante ou le Fourbe puni`) [container]
    - chapter: Acte I (`La Fausse Suivante/Acte I`) [container]
    - chapter: Acte II (`La Fausse Suivante/Acte II`) [container]
    - … 31 more

### D: virtual roots (1,243)

1. `Voyages de François Bernier`: virtual, book, 1 parts, 0 missing, order single, proofread
    - text: Histoire de la dernière révolution des États du grand Mogol (`Voyages de François Bernier/Histoire de la dernière révolution des États du grand Mogol`)
1. `La Rochefoucauld (Bourdeau)`: virtual, book, 1 parts, 0 missing, order single, proofread
    - text: Bibliographie (`La Rochefoucauld (Bourdeau)/Bibliographie`)
1. `Les Cartes du dépôt de la guerre`: virtual, periodical-issue, 2 parts, 0 missing, order scan, validated
    - chapter: 01 (`Les Cartes du dépôt de la guerre/01`)
    - chapter: 02 (`Les Cartes du dépôt de la guerre/02`) [hierarchy]
1. `Les Mississipiens`: virtual, book, 1 parts, 0 missing, order single, proofread
    - text: Notice (`Les Mississipiens/Notice`)
1. `Revue Musicale de Lyon 1904-01-05`: virtual, periodical-issue, 6 parts, 0 missing, order scan, proofread
    - text: Le Crépuscule des Dieux (fin) (`Revue Musicale de Lyon 1904-01-05/Le Crépuscule des Dieux (fin)`)
    - text: La Littérature de l'orgue (`Revue Musicale de Lyon 1904-01-05/La Littérature de l'orgue`) [hierarchy]
    - text: À Travers la presse (`Revue Musicale de Lyon 1904-01-05/À Travers la presse`) [hierarchy]
    - text: Chronique lyonnaise (`Revue Musicale de Lyon 1904-01-05/Chronique lyonnaise`) [hierarchy]
    - text: Les Concerts (`Revue Musicale de Lyon 1904-01-05/Les Concerts`) [hierarchy]
    - text: Nouvelles diverses (`Revue Musicale de Lyon 1904-01-05/Nouvelles diverses`) [hierarchy]
1. `Revue littéraire, 1859`: virtual, periodical-issue, 3 parts, 0 missing, order natural, proofread
    - chapter: 03 (`Revue littéraire, 1859/03`)
    - chapter: 05 (`Revue littéraire, 1859/05`) [hierarchy]
    - chapter: 06 (`Revue littéraire, 1859/06`) [hierarchy]
1. `Dissertation sur la mort de Henri IV`: virtual, collection, 1 parts, 0 missing, order single, proofread
    - text: Édition Garnier (`Dissertation sur la mort de Henri IV/Édition Garnier`)
1. `Pages retrouvées`: virtual, book, 1 parts, 0 missing, order single, proofread
    - chapter: Préface (`Pages retrouvées/Préface`)
1. `Histoire de la décadence et de la chute de l’Empire romain, traduction Guizot, tome 9`: virtual, book, 3 parts, 0 missing, order natural, validated
    - chapter: XLIX (`Histoire de la décadence et de la chute de l’Empire romain, traduction Guizot, tome 9/XLIX`)
    - chapter: XLVII (`Histoire de la décadence et de la chute de l’Empire romain, traduction Guizot, tome 9/XLVII`) [hierarchy]
    - chapter: XLVIII (`Histoire de la décadence et de la chute de l’Empire romain, traduction Guizot, tome 9/XLVIII`) [hierarchy]
1. `Homélies contre les anoméens`: virtual, book, 12 parts, 0 missing, order scan, unproofread
    - text: Premier Homélie (`Homélies contre les anoméens/Premier Homélie`)
    - text: Deuxième Homélie (`Homélies contre les anoméens/Deuxième Homélie`) [hierarchy]
    - text: Troisième Homélie (`Homélies contre les anoméens/Troisième Homélie`) [hierarchy]
    - text: Quatrième Homélie (`Homélies contre les anoméens/Quatrième Homélie`) [hierarchy]
    - text: Cinquième Homélie (`Homélies contre les anoméens/Cinquième Homélie`) [hierarchy]
    - text: Sixième Homélie (`Homélies contre les anoméens/Sixième Homélie`) [hierarchy]
    - text: Septième Homélie (`Homélies contre les anoméens/Septième Homélie`) [hierarchy]
    - text: Huitième Homélie (`Homélies contre les anoméens/Huitième Homélie`) [hierarchy]
    - text: Neuvième Homélie (`Homélies contre les anoméens/Neuvième Homélie`) [hierarchy]
    - text: Dixième Homélie (`Homélies contre les anoméens/Dixième Homélie`) [hierarchy]
    - text: Onzième Homélie (`Homélies contre les anoméens/Onzième Homélie`) [hierarchy]
    - text: Douzième Homélie (`Homélies contre les anoméens/Douzième Homélie`) [hierarchy]
1. `Avant l’amour`: virtual, periodical-issue, 25 parts, 0 missing, order chain, validated
    - chapter: 01 (`Avant l’amour/01`)
    - chapter: 02 (`Avant l’amour/02`) [hierarchy]
    - chapter: 03 (`Avant l’amour/03`) [hierarchy]
    - chapter: 04 (`Avant l’amour/04`) [hierarchy]
    - chapter: 05 (`Avant l’amour/05`) [hierarchy]
    - chapter: 06 (`Avant l’amour/06`) [hierarchy]
    - chapter: 07 (`Avant l’amour/07`) [hierarchy]
    - chapter: 08 (`Avant l’amour/08`) [hierarchy]
    - chapter: 09 (`Avant l’amour/09`) [hierarchy]
    - chapter: 10 (`Avant l’amour/10`) [hierarchy]
    - chapter: 11 (`Avant l’amour/11`) [hierarchy]
    - chapter: 12 (`Avant l’amour/12`) [hierarchy]
    - … 13 more
1. `Œuvres complètes d’Alexis de Tocqueville, Lévy`: virtual, book, 23 parts, 0 missing, order natural, unproofread
    - text: Correspondance (`Œuvres complètes d’Alexis de Tocqueville, Lévy/Correspondance`)
    - text: Correspondance et œuvres posthumes (`Œuvres complètes d’Alexis de Tocqueville, Lévy/Correspondance et œuvres posthumes`) [hierarchy]
    - text: Mélanges, fragments historiques et notes (`Œuvres complètes d’Alexis de Tocqueville, Lévy/Mélanges, fragments historiques et notes`) [hierarchy]
    - text: Nouvelle correspondance entièrement inédite (`Œuvres complètes d’Alexis de Tocqueville, Lévy/Nouvelle correspondance entièrement inédite`) [hierarchy]
    - volume: Rapport sur les affaires d’Afrique (`Œuvres complètes d’Alexis de Tocqueville, Lévy/Rapport sur les affaires d’Afrique`) [hierarchy]
      - text: Objet et plan de ce rapport (`Œuvres complètes d’Alexis de Tocqueville, Lévy/Rapport sur les affaires d’Afrique/Plan`) [container]
      - chapter: Première partie. — Domination et gouvernement des indigènes (`Œuvres complètes d’Alexis de Tocqueville, Lévy/Rapport sur les affaires d’Afrique/Première partie`) [container]
      - chapter: Seconde partie. — Administration civile. — Gouvernement des Européens (`Œuvres complètes d’Alexis de Tocqueville, Lévy/Rapport sur les affaires d’Afrique/Seconde partie`) [container]
      - text: Rapport sur les camps agricoles (`Œuvres complètes d’Alexis de Tocqueville, Lévy/Rapport sur les affaires d’Afrique/Rapport sur les camps agricoles`) [container]
    - volume: Études économiques, politiques et littéraires (`Œuvres complètes d’Alexis de Tocqueville, Lévy/Études économiques, politiques et littéraires`) [hierarchy]
      - chapter: Avant-propos du vol. IX (`Œuvres complètes d’Alexis de Tocqueville, Lévy/Avant-propos du vol. IX`) [container]
      - text: Discours de réception à l’Académie française (`Œuvres complètes d’Alexis de Tocqueville, Lévy/Discours de réception à l’Académie française`) [container]
    - … 11 more
1. `Magasin d'Éducation et de Récréation`: virtual, periodical-issue, 1 parts, 0 missing, order single, validated
    - chapter: Tome XIII (`Magasin d'Éducation et de Récréation/Tome XIII`)
1. `La Princesse d’Élide`: virtual, collection, 1 parts, 0 missing, order single, unproofread
    - text: Édition Garnier, 1904 (`La Princesse d’Élide/Édition Garnier, 1904`)
1. `Fêtes de la faim`: virtual, collection, 1 parts, 0 missing, order single, unknown
    - text: Comparaison de versions (`Fêtes de la faim/Comparaison de versions`)
1. `L’Activation de l’Énergie`: virtual, book, 6 parts, 0 missing, order scan, proofread
    - text: Membres du Comité scientifique et du Comité général (`L’Activation de l’Énergie/Membres du Comité scientifique et du Comité général`)
    - text: Avertissement (`L’Activation de l’Énergie/Avertissement`) [hierarchy]
    - text: L’Heure de choisir (`L’Activation de l’Énergie/L’Heure de choisir`) [hierarchy]
    - text: L’Atomisme de l’Esprit (`L’Activation de l’Énergie/L’Atomisme de l’Esprit`) [hierarchy]
    - text: La Montée de l’Autre (`L’Activation de l’Énergie/La Montée de l’Autre`) [hierarchy]
    - text: Universalisation et Union (`L’Activation de l’Énergie/Universalisation et Union`) [hierarchy]
1. `Histoire parlementaire de la Révolution française`: virtual, book, 8 parts, 0 missing, order scan, proofread
    - chapter: Introduction (`Histoire parlementaire de la Révolution française/Introduction`)
    - volume: Livre Premier (`Histoire parlementaire de la Révolution française/Livre Premier`) [hierarchy]
      - chapter: Chapitre 1 (`Histoire parlementaire de la Révolution française/Livre Premier/Chapitre 1`) [hierarchy]
      - chapter: Chapitre 2 (`Histoire parlementaire de la Révolution française/Livre Premier/Chapitre 2`) [hierarchy]
      - chapter: Chapitre 3 (`Histoire parlementaire de la Révolution française/Livre Premier/Chapitre 3`) [hierarchy]
    - volume: Livre Deuxième (`Histoire parlementaire de la Révolution française/Livre Deuxième`) [hierarchy]
      - chapter: Chapitre 1 (`Histoire parlementaire de la Révolution française/Livre Deuxième/Chapitre 1`) [hierarchy]
      - chapter: Chapitre 2 (`Histoire parlementaire de la Révolution française/Livre Deuxième/Chapitre 2`) [hierarchy]
1. `Du Laocoon`: virtual, book, 27 parts, 0 missing, order scan, proofread
    - text: Avertissement (`Du Laocoon/Avertissement`)
    - chapter: Préface (`Du Laocoon/Préface`) [hierarchy]
    - chapter: I (`Du Laocoon/I`) [hierarchy]
    - chapter: II (`Du Laocoon/II`) [hierarchy]
    - chapter: III (`Du Laocoon/III`) [hierarchy]
    - chapter: IV (`Du Laocoon/IV`) [hierarchy]
    - chapter: V (`Du Laocoon/V`) [hierarchy]
    - chapter: VI (`Du Laocoon/VI`) [hierarchy]
    - chapter: VII (`Du Laocoon/VII`) [hierarchy]
    - chapter: VIII (`Du Laocoon/VIII`) [hierarchy]
    - chapter: IX (`Du Laocoon/IX`) [hierarchy]
    - chapter: X (`Du Laocoon/X`) [hierarchy]
    - … 15 more
1. `Dictionnaire critique et raisonné du langage vicieux`: virtual, book, 2 parts, 0 missing, order scan, unproofread
    - chapter: Préface (`Dictionnaire critique et raisonné du langage vicieux/Préface`)
    - text: A (`Dictionnaire critique et raisonné du langage vicieux/A`) [hierarchy]
1. `Souvenirs d’un vieux critique`: virtual, book, 1 parts, 0 missing, order single, proofread
    - text: Le P. Emmanuel d’Alzon (mes souvenirs) (`Souvenirs d’un vieux critique/1re série/Le P. Emmanuel d’Alzon (mes souvenirs)`)
1. `Les Protocoles des Sages de Sion`: virtual, book, 80 parts, 0 missing, order natural, unproofread
    - volume: Boutmi (`Les Protocoles des Sages de Sion/Boutmi`)
      - volume: Texte (`Les Protocoles des Sages de Sion/Boutmi/Texte`) [hierarchy]
        - chapter: 01 (`Les Protocoles des Sages de Sion/Boutmi/Texte/01`) [hierarchy]
        - chapter: 02 (`Les Protocoles des Sages de Sion/Boutmi/Texte/02`) [hierarchy]
        - chapter: 03 (`Les Protocoles des Sages de Sion/Boutmi/Texte/03`) [hierarchy]
        - chapter: 04 (`Les Protocoles des Sages de Sion/Boutmi/Texte/04`) [hierarchy]
        - chapter: 05 (`Les Protocoles des Sages de Sion/Boutmi/Texte/05`) [hierarchy]
        - chapter: 06 (`Les Protocoles des Sages de Sion/Boutmi/Texte/06`) [hierarchy]
        - chapter: 07 (`Les Protocoles des Sages de Sion/Boutmi/Texte/07`) [hierarchy]
        - chapter: 08 (`Les Protocoles des Sages de Sion/Boutmi/Texte/08`) [hierarchy]
        - chapter: 09 (`Les Protocoles des Sages de Sion/Boutmi/Texte/09`) [hierarchy]
        - chapter: 10 (`Les Protocoles des Sages de Sion/Boutmi/Texte/10`) [hierarchy]
    - … 68 more
1. `Histoire de la décadence et de la chute de l’Empire romain, traduction Guizot, tome 7`: virtual, book, 7 parts, 0 missing, order natural, validated
    - text: Avertissement (`Histoire de la décadence et de la chute de l’Empire romain, traduction Guizot, tome 7/Avertissement`)
    - text: Observations générales (`Histoire de la décadence et de la chute de l’Empire romain, traduction Guizot, tome 7/Observations générales`) [hierarchy]
    - text: Post-scriptum (`Histoire de la décadence et de la chute de l’Empire romain, traduction Guizot, tome 7/Post-scriptum`) [hierarchy]
    - chapter: XL (`Histoire de la décadence et de la chute de l’Empire romain, traduction Guizot, tome 7/XL`) [hierarchy]
    - chapter: XLI (`Histoire de la décadence et de la chute de l’Empire romain, traduction Guizot, tome 7/XLI`) [hierarchy]
    - chapter: XXXIX (`Histoire de la décadence et de la chute de l’Empire romain, traduction Guizot, tome 7/XXXIX`) [hierarchy]
    - chapter: XXXVIII (`Histoire de la décadence et de la chute de l’Empire romain, traduction Guizot, tome 7/XXXVIII`) [hierarchy]
1. `Inventaire general`: virtual, book, 4 parts, 6 missing, order natural, unproofread
    - volume: 01 (`Inventaire general/01`)
      - text: Pierreries et vases d’or enrichis de pierreries (`Inventaire general/01/01`) [hierarchy]
    - volume: 03 (`Inventaire general/03`) [hierarchy]
      - text: Miroirs (`Inventaire general/03/01`) [hierarchy]
1. `Lettres de Catherine de Médicis`: virtual, book, 1 parts, 0 missing, order single, unproofread
    - chapter: Table chronologique des lettres (`Lettres de Catherine de Médicis/01/Table chronologique des lettres`)
1. `Notes Georgiques`: virtual, collection, 5 parts, 0 missing, order scan, proofread
    - chapter: Livre I (`Notes Georgiques/Livre I`)
    - text: Titre (`Notes Georgiques/Titre`) [hierarchy]
    - chapter: Livre II (`Notes Georgiques/Livre II`) [hierarchy]
    - chapter: Livre III (`Notes Georgiques/Livre III`) [hierarchy]
    - chapter: Livre IV (`Notes Georgiques/Livre IV`) [hierarchy]
1. `Les Français peints par eux-mêmes`: virtual, book, 1 parts, 0 missing, order single, unproofread
    - text: Le Pair de France (`Les Français peints par eux-mêmes/Le Pair de France`)
1. `Alexandre Dumas et son œuvre (Glinel)`: virtual, book, 1 parts, 0 missing, order single, proofread
    - chapter: Appendice (`Alexandre Dumas et son œuvre (Glinel)/Appendice`)
1. `« J’ai l’esprit tout ennuyé »`: virtual, collection, 1 parts, 0 missing, order single, proofread
    - text: Buon, 1587 (`« J’ai l’esprit tout ennuyé »/Buon, 1587`)
1. `Dictionnaire françois-latin de Jean Nicot`: virtual, reference, 4 parts, 1 missing, order single, proofread
    - volume: 1re éd., 1573 (`Dictionnaire françois-latin de Jean Nicot/1re éd., 1573`)
      - text: Titre (`Dictionnaire françois-latin de Jean Nicot/1re éd., 1573/Titre`) [container]
      - text: Dedicace (`Dictionnaire françois-latin de Jean Nicot/1re éd., 1573/Dedicace`) [container]
      - text: Avertissement (`Dictionnaire françois-latin de Jean Nicot/1re éd., 1573/Avertissement`) [container]
1. `La Chanson de Roland`: virtual, book, 500 parts, 1 missing, order natural, unproofread
    - volume: Joseph Bédier (`La Chanson de Roland/Joseph Bédier`)
      - chapter: Avant-Propos (`La Chanson de Roland/Joseph Bédier/Avant-Propos`) [hierarchy]
      - volume: La Chanson de Roland (`La Chanson de Roland/Joseph Bédier/La Chanson de Roland`) [hierarchy]
        - volume: Présentation bilingue (`La Chanson de Roland/Joseph Bédier/La Chanson de Roland/Bilingue`) [hierarchy]
          - text: 1 à 50 (`La Chanson de Roland/Joseph Bédier/La Chanson de Roland/Bilingue/001-050`) [hierarchy]
          - text: 51 à 100 (`La Chanson de Roland/Joseph Bédier/La Chanson de Roland/Bilingue/051-100`) [hierarchy]
          - text: 101 à 150 (`La Chanson de Roland/Joseph Bédier/La Chanson de Roland/Bilingue/101-150`) [hierarchy]
          - text: 151 à 200 (`La Chanson de Roland/Joseph Bédier/La Chanson de Roland/Bilingue/151-200`) [hierarchy]
          - text: 201 à 250 (`La Chanson de Roland/Joseph Bédier/La Chanson de Roland/Bilingue/201-250`) [hierarchy]
          - text: 251 à 291 (`La Chanson de Roland/Joseph Bédier/La Chanson de Roland/Bilingue/251-291`) [hierarchy]
        - text: 001-050 (`La Chanson de Roland/Joseph Bédier/La Chanson de Roland/Sequence/001-050`) [hierarchy]
        - text: 051-100 (`La Chanson de Roland/Joseph Bédier/La Chanson de Roland/Sequence/051-100`) [hierarchy]
    - … 488 more

### E: subpage texts reached from a disambiguation or versions page (30,984)

1. text “Sous-yeux” (`Encyclopédie méthodique/Art aratoire et du jardinage/Sous-yeux`), part 919 of `Encyclopédie méthodique` (reference, 2,767 parts, 2,669 texts)
1. text “Le Cantonnier” (`Poésies (Mallarmé, 1914, 8e éd.)/Le Cantonnier`), part 34 of `Poésies (Mallarmé, 1914, 8e éd.)` (collection, 65 parts, 64 texts)
1. text “Treillis” (`Dictionnaire raisonné de l’architecture française du XIe au XVIe siècle/Treillis`), part 483 of `Dictionnaire raisonné de l’architecture française du XIe au XVIe siècle` (book, 530 parts, 520 texts)
1. text “Hohenzollern” (`Dictionnaire français illustré de Larive et Fleury/Hohenzollern`), part 84 of `Dictionnaire français illustré de Larive et Fleury` (reference, 198 parts, 197 texts)
1. text “Nouvelle chanson” (`Les Chants du crépuscule/Nouvelle chanson sur un vieil air`), part 24 of `Les Chants du crépuscule` (collection, 41 parts, 40 texts)
1. text “Athis (fils de limniace)” (`Grand dictionnaire universel du XIXe siècle/Athis (fils de limniace)`), part 1419 of `Grand dictionnaire universel du XIXe siècle` (reference, 5,834 parts, 5,801 texts)
1. text “Calleux” (`Encyclopédie méthodique/Art aratoire et du jardinage/Calleux`), part 230 of `Encyclopédie méthodique` (reference, 2,767 parts, 2,669 texts)
1. text “Spéculation” (`Encyclopédie des gens du monde/Spéculation`), part 15 of `Encyclopédie des gens du monde` (reference, 16 parts, 15 texts)
1. text “Confessions d’une femme” (`Le Père Milon (recueil)/Confession d’une femme`), part 3 of `Le Père Milon (recueil)` (collection, 23 parts, 23 texts)
1. text “AMÉRIQUE — Histoire” (`Grand dictionnaire universel du XIXe siècle/AMÉRIQUE — Histoire`), part 1221 of `Grand dictionnaire universel du XIXe siècle` (reference, 5,834 parts, 5,801 texts)
1. text “PHILIPPE DE CHAMPAGNE, célèbre peintre” (`Grand dictionnaire universel du XIXe siècle/PHILIPPE DE CHAMPAGNE, célèbre peintre`), part 4870 of `Grand dictionnaire universel du XIXe siècle` (reference, 5,834 parts, 5,801 texts)
1. text “MARIE DE FRANCE, femme poète française” (`Grand dictionnaire universel du XIXe siècle/MARIE DE FRANCE, femme poète française`), part 4238 of `Grand dictionnaire universel du XIXe siècle` (reference, 5,834 parts, 5,801 texts)
1. text “angoisse s. f.” (`La Grande Encyclopédie, inventaire raisonné des sciences, des lettres, et des arts/angoisse s. f.`), part 68 of `La Grande Encyclopédie, inventaire raisonné des sciences, des lettres, et des arts` (reference, 233 parts, 230 texts)
1. text “atrabilaire adj.” (`Grand dictionnaire universel du XIXe siècle/atrabilaire adj.`), part 1594 of `Grand dictionnaire universel du XIXe siècle` (reference, 5,834 parts, 5,801 texts)
1. text “Xanthus de lydie” (`Dictionnaire universel d’histoire et de géographie Bouillet Chassang/Xanthus de lydie`), part 1536 of `Dictionnaire universel d’histoire et de géographie Bouillet Chassang` (collection, 1,872 parts, 1,867 texts)
1. text “Refus (le)” (`Œuvres complètes de Béranger/Le Refus`), part 269 of `Œuvres complètes de Béranger` (collection, 327 parts, 320 texts)
1. text “CHARLES IV, fils et successeur du précédent” (`Grand dictionnaire universel du XIXe siècle/CHARLES IV, fils et successeur du précédent`), part 2503 of `Grand dictionnaire universel du XIXe siècle` (reference, 5,834 parts, 5,801 texts)
1. text “CATHERINE DE LORRAINE” (`Grand dictionnaire universel du XIXe siècle/CATHERINE DE LORRAINE`), part 2385 of `Grand dictionnaire universel du XIXe siècle` (reference, 5,834 parts, 5,801 texts)
1. text “Promenons-nous dans les bois (chanson de jeu)” (`Chansons et rondes enfantines/Promenons-nous dans les bois`), part 49 of `Chansons et rondes enfantines` (collection, 59 parts, 57 texts)
1. text “Alkmaar” (`Dictionnaire universel d’histoire et de géographie Bouillet Chassang/Alkmaar`), part 889 of `Dictionnaire universel d’histoire et de géographie Bouillet Chassang` (collection, 1,872 parts, 1,867 texts)
1. text “HOHENZOLLERN (PRINCIPAUTÉS DE), anciennes principautés suzeraines de l’Allemagne du Sud, ayant fait partie de la Confédération germanique jusqu’en 1849” (`Grand dictionnaire universel du XIXe siècle/HOHENZOLLERN (PRINCIPAUTÉS DE), anciennes principautés suzeraines de l’Allemagne du Sud, ayant fait partie de la Confédération germanique jusqu’en 1849`), part 3422 of `Grand dictionnaire universel du XIXe siècle` (reference, 5,834 parts, 5,801 texts)
1. volume “ODES” (`Œuvres poétiques de Chénier (Moland, 1889)/Odes`), part 236 of `Œuvres poétiques de Chénier (Moland, 1889)` (collection, 304 parts, 290 texts)
1. text “Fontaines (rue des)” (`Dictionnaire administratif et historique des rues de Paris et de ses monuments/Fontaines (rue des)`), part 742 of `Dictionnaire administratif et historique des rues de Paris et de ses monuments` (reference, 2,142 parts, 2,133 texts)
1. text “Sorel des Flottes” (`La France littéraire, ou Dictionnaire bibliographique/Sorel des Flottes`), part 271 of `La France littéraire, ou Dictionnaire bibliographique` (book, 338 parts, 330 texts)
1. text “Lugo 2” (`Dictionnaire historique et critique/11e éd., 1820/Lugo 2`), part 621 of `Dictionnaire historique et critique` (reference, 1,016 parts, 993 texts)
1. text “Le Salon pour rire 1874” (`Cham - Albums du Charivari/Le Salon pour rire 1874`), part 40 of `Cham - Albums du Charivari` (book, 88 parts, 88 texts)
1. text “L’Aigle, la Laye, & la Chate” (`Fables de La Fontaine (éd. Barbin)/1/L’Aigle, la Laie, et la Chatte`), part 6 of `Fables de La Fontaine (éd. Barbin)` (collection, 135 parts, 126 texts)
1. text “Bornes (rue des Trois-)” (`Dictionnaire administratif et historique des rues de Paris et de ses monuments/Bornes (rue des Trois-)`), part 255 of `Dictionnaire administratif et historique des rues de Paris et de ses monuments` (reference, 2,142 parts, 2,133 texts)
1. chapter “Chapitre I” (`La Tiare de Salomon/Chapitre I`), part 1 of `La Tiare de Salomon` (book, 2 parts, 0 texts)
1. text “Varier” (`Encyclopédie méthodique/Beaux-Arts/Varier`), part 2282 of `Encyclopédie méthodique` (reference, 2,767 parts, 2,669 texts)

### all searchable subpage texts (chapter titles that the label patterns miss show up here) (351,178)

1. text “Ratichonné” (`Supplément au Dictionnaire argotique/Ratichonné`), part 313 of `Supplément au Dictionnaire argotique` (reference, 407 parts, 369 texts)
1. text “Légende de l'Homme à la verge rouge” (`Légendes bruxelloises/Légende de l'Homme à la verge rouge`), part 24 of `Légendes bruxelloises` (collection, 32 parts, 30 texts)
1. text “EAU” (`Dictionnaire portatif de cuisine, d’office, et de distillation/EAU`), part 171 of `Dictionnaire portatif de cuisine, d’office, et de distillation` (reference, 500 parts, 491 texts)
1. text “PAVÉ” (`Petit dictionnaire de la langue française (1860)/PAVÉ`), part 14922 of `Petit dictionnaire de la langue française (1860)` (reference, 20,406 parts, 20,367 texts)
1. text “Notice” (`François le Champi/Notice`), part 1 of `François le Champi` (book, 28 parts, 1 texts)
1. text “Giornata … 1” (`Racine et Shakespeare (édition Martineau, 1928)/Appendice II/Giornata … 1`), part 26 of `Racine et Shakespeare (édition Martineau, 1928)` (book, 44 parts, 15 texts)
1. text “AGHAIS” (`L’Encyclopédie/1re édition/AGHAIS`), part 795 of `L’Encyclopédie` (reference, 45,535 parts, 45,462 texts)
1. text “ACCOMMODEMENT” (`Dictionnaire de Trévoux/6e édition, 1771/ACCOMMODEMENT`), part 1221 of `Dictionnaire de Trévoux` (reference, 32,173 parts, 32,110 texts)
1. text “Membrane” (`Dictionnaire pratique et historique de la musique/Membrane`), part 1312 of `Dictionnaire pratique et historique de la musique` (reference, 2,416 parts, 2,386 texts)
1. text “Intér’” (`Dictionnaire des patois romans de la Moselle/Intér’`), part 1751 of `Dictionnaire des patois romans de la Moselle` (reference, 21,247 parts, 21,230 texts)
1. text “Protocole sur les privilèges et immunités de la Communauté” (`Traité instituant la Communauté européenne du charbon et de l'acier (1951)/Protocole sur les privilèges et immunités de la Communauté`), part 3 of `Traité instituant la Communauté européenne du charbon et de l'acier (1951)` (book, 7 parts, 7 texts)
1. text “VERSER” (`Petit dictionnaire de la langue française (1836)/VERSER`), part 23400 of `Petit dictionnaire de la langue française (1836)` (reference, 23,874 parts, 23,829 texts)
1. text “Pisser” (`Dictionnaire d’argot ou Guide des gens du monde (2e éd.)/Articles/Pisser`), part 541 of `Dictionnaire d’argot ou Guide des gens du monde (2e éd.)` (reference, 832 parts, 782 texts)
1. text “La Pendule de Bougival” (`Les Contes du lundi/La Pendule de Bougival`), part 8 of `Les Contes du lundi` (book, 41 parts, 40 texts)
1. text “HELA” (`L’Encyclopédie/1re édition/HELA`), part 18523 of `L’Encyclopédie` (reference, 45,535 parts, 45,462 texts)
1. text “JACOBIN, JACOBINE” (`Petit dictionnaire de la langue française (1836)/JACOBIN, JACOBINE`), part 12643 of `Petit dictionnaire de la langue française (1836)` (reference, 23,874 parts, 23,829 texts)
1. text “UNIVERSALISTES” (`L’Encyclopédie/1re édition/UNIVERSALISTES`), part 43082 of `L’Encyclopédie` (reference, 45,535 parts, 45,462 texts)
1. text “CÉSARION” (`Dictionnaire de Trévoux/6e édition, 1771/CÉSARION`), part 18615 of `Dictionnaire de Trévoux` (reference, 32,173 parts, 32,110 texts)
1. text “À sa nièce Caroline” (`Correspondance de Gustave Flaubert/Tome 6/1330`), part 1339 of `Correspondance de Gustave Flaubert` (book, 2,020 parts, 2,002 texts)
1. text “ANÉMOMÉTRIE” (`Dictionnaire classique de la langue française (Bescherelle Jeune, 1880)/Articles/ANÉMOMÉTRIE`), part 2856 of `Dictionnaire classique de la langue française (Bescherelle Jeune, 1880)` (reference, 3,078 parts, 3,073 texts)
1. text “NARINE” (`Petit dictionnaire de la langue française (1860)/NARINE`), part 13601 of `Petit dictionnaire de la langue française (1860)` (reference, 20,406 parts, 20,367 texts)
1. text “Holmandè” (`Dictionnaire des patois romans de la Moselle/Articles/Holmandè`), part 11446 of `Dictionnaire des patois romans de la Moselle` (reference, 21,247 parts, 21,230 texts)
1. text “PLATANISTE” (`L’Encyclopédie/1re édition/PLATANISTE`), part 30629 of `L’Encyclopédie` (reference, 45,535 parts, 45,462 texts)
1. text “. XX” (`Le Robinson suisse/XX`), part 20 of `Le Robinson suisse` (book, 44 parts, 43 texts)
1. text “Serpillière” (`Dictionnaire d’argot ou Guide des gens du monde (1ère éd.)/Articles/Serpillière`), part 647 of `Dictionnaire d’argot ou Guide des gens du monde (1ère éd.)` (reference, 822 parts, 777 texts)
1. text “Aérage” (`Néologie, ou Vocabulaire de mots nouveaux/Aérage`), part 68 of `Néologie, ou Vocabulaire de mots nouveaux` (book, 2,835 parts, 2,807 texts)
1. text “Tuyau” (`Dictionnaire des patois romans de la Moselle/Articles/Tuyau`), part 20528 of `Dictionnaire des patois romans de la Moselle` (reference, 21,247 parts, 21,230 texts)
1. text “Pélée” (`Dictionnaire universel d’histoire et de géographie Bouillet Chassang/Pélée`), part 1376 of `Dictionnaire universel d’histoire et de géographie Bouillet Chassang` (collection, 1,872 parts, 1,867 texts)
1. text “Çopēye” (`Dictionnaire des patois romans de la Moselle/Articles/Çopēye`), part 6184 of `Dictionnaire des patois romans de la Moselle` (reference, 21,247 parts, 21,230 texts)
1. text “La Vie à deux” (`À genoux (Adelphe Froger)/La Vie à deux`), part 40 of `À genoux (Adelphe Froger)` (book, 137 parts, 133 texts)

### C: oversized (899)

1. `Augustin d’Hippone`: virtual, book, 244 parts, 0 missing, order natural, unproofread
    - volume: Accord des Evangiles (`Augustin d’Hippone/Accord des Evangiles`)
      - chapter: Livre Premier (`Augustin d’Hippone/Accord des Evangiles/Livre Premier`) [hierarchy]
      - chapter: Livre Second (`Augustin d’Hippone/Accord des Evangiles/Livre Second`) [hierarchy]
      - chapter: Livre Troisième (`Augustin d’Hippone/Accord des Evangiles/livre troisième`) [hierarchy]
      - chapter: Livre Quatrième (`Augustin d’Hippone/Accord des Evangiles/livre quatrième`) [hierarchy]
    - text: Commentaire de l’épître aux Galates (`Augustin d’Hippone/Commentaire de l’épître aux Galates`) [hierarchy]
    - text: Contre les Académiciens (`Augustin d’Hippone/Contre les Académiciens`) [hierarchy]
    - volume: De la doctrine chrétienne (`Augustin d’Hippone/De la doctrine chrétienne`) [hierarchy]
      - chapter: Prologue (`Augustin d’Hippone/De la doctrine chrétienne/Prologue`) [hierarchy]
      - chapter: LIVRE PREMIER. (`Augustin d’Hippone/De la doctrine chrétienne/Livre premier`) [hierarchy]
      - chapter: Livre II (`Augustin d’Hippone/De la doctrine chrétienne/Livre II`) [hierarchy]
      - chapter: Livre III (`Augustin d’Hippone/De la doctrine chrétienne/Livre III`) [hierarchy]
    - … 232 more
1. `Les Œuvres poétiques de M. Bertaut`: linked, book, 2 parts, 0 missing, order scan, unproofread
    - front: Les Œuvres poétiques de M. Bertaut
    - text: « Bien-heureux est celuy » [container]
    - text: Chanson (« Les Cieux inexorables ») [container]
1. `Ma vie (Cardan)`: subpages, book, 57 parts, 0 missing, order toc, proofread
    - toc: Ma vie (Cardan)
    - chapter: Introduction (`Ma vie (Cardan)/Introduction`) [container]
    - chapter: Avant-propos (`Ma vie (Cardan)/Avant-propos`) [container]
    - text: . Manaissance (`Ma vie (Cardan)/Chapitre II`) [container]
    - text: . Stature etforme ducorps (`Ma vie (Cardan)/Chapitre V`) [container]
    - text: . De l’exercice (`Ma vie (Cardan)/Chapitre VII`) [container]
    - text: . Régime alimentaire (`Ma vie (Cardan)/Chapitre VIII`) [container]
    - text: . Méditations surlesmoyens deperpétuer monnom (`Ma vie (Cardan)/Chapitre IX`) [container]
    - text: . Prudence (`Ma vie (Cardan)/Chapitre XI`) [container]
    - text: . Vertus etconstance (`Ma vie (Cardan)/Chapitre XIV`) [container]
    - text: . Ennemis etrivaux (`Ma vie (Cardan)/Chapitre XVI`) [container]
    - text: . Mesgoûts (`Ma vie (Cardan)/Chapitre XVIII`) [container]
    - … 46 more
1. `Antiquités judaïques`: subpages, book, 31 parts, 0 missing, order natural, unproofread
    - front: Antiquités judaïques
    - volume: Livre I (`Antiquités judaïques/Livre I`) [hierarchy]
      - chapter: 1 (`Antiquités judaïques/Livre I/1`) [hierarchy]
      - chapter: 14 (`Antiquités judaïques/Livre I/14`) [hierarchy]
    - volume: Livre II (`Antiquités judaïques/Livre II`) [hierarchy]
      - chapter: 1 (`Antiquités judaïques/Livre II/1`) [hierarchy]
      - chapter: 2 (`Antiquités judaïques/Livre II/2`) [hierarchy]
      - chapter: 3 (`Antiquités judaïques/Livre II/3`) [hierarchy]
      - chapter: 4 (`Antiquités judaïques/Livre II/4`) [hierarchy]
      - chapter: 5 (`Antiquités judaïques/Livre II/5`) [hierarchy]
      - chapter: 6 (`Antiquités judaïques/Livre II/6`) [hierarchy]
      - chapter: 7 (`Antiquités judaïques/Livre II/7`) [hierarchy]
    - … 20 more
1. `Les Bastonnais`: subpages, book, 81 parts, 0 missing, order toc, validated
    - front: Les Bastonnais
    - volume: LIVRE (`Les Bastonnais/01`) [container]
      - text: .Les fusées bleues (`Les Bastonnais/01/01`) [container]
      - text: .Sur l’autre rive (`Les Bastonnais/01/02`) [container]
      - text: .Au château (`Les Bastonnais/01/03`) [container]
      - text: .Sur la place de la cathédrale (`Les Bastonnais/01/04`) [container]
      - text: .Les dépêches (`Les Bastonnais/01/05`) [container]
      - text: .Les pleurs de Pauline (`Les Bastonnais/01/06`) [container]
      - text: .La jolie rebelle (`Les Bastonnais/01/07`) [container]
      - text: .L’ermite de Montmorency (`Les Bastonnais/01/08`) [container]
      - text: .Le hurlement du loup (`Les Bastonnais/01/09`) [container]
      - text: .La cassette (`Les Bastonnais/01/10`) [container]
    - … 70 more
1. `Petit dictionnaire de la langue française (1836)`: subpages, reference, 23,874 parts, 0 missing, order scan, proofread
    - toc: Petit dictionnaire de la langue française (1836)
    - text: Avertissement (`Petit dictionnaire de la langue française (1836)/Texte entier/Avertissement`) [hierarchy]
    - chapter: Table des abréviations (`Petit dictionnaire de la langue française (1836)/Texte entier/Table des abréviations`) [hierarchy]
    - text: A (`Petit dictionnaire de la langue française (1836)/A`) [hierarchy]
    - text: ABAISSEMENT (`Petit dictionnaire de la langue française (1836)/ABAISSEMENT`) [hierarchy]
    - text: ABAISSER (`Petit dictionnaire de la langue française (1836)/ABAISSER`) [hierarchy]
    - text: ABANDON (`Petit dictionnaire de la langue française (1836)/ABANDON`) [hierarchy]
    - text: ABANDONNEMENT (`Petit dictionnaire de la langue française (1836)/ABANDONNEMENT`) [hierarchy]
    - text: ABANDONNER (`Petit dictionnaire de la langue française (1836)/ABANDONNER`) [hierarchy]
    - text: ABASOURDIR (`Petit dictionnaire de la langue française (1836)/ABASOURDIR`) [hierarchy]
    - text: ABAT-FOIN (`Petit dictionnaire de la langue française (1836)/ABAT-FOIN`) [hierarchy]
    - text: ABAT-JOUR (`Petit dictionnaire de la langue française (1836)/ABAT-JOUR`) [hierarchy]
    - … 23,863 more
1. `Jean part2`: single, book, 1 parts, 0 missing, order –, unproofread
    - text: Jean part2
1. `Au pays des pierres (Eugène Le Roy)`: linked, book, 24 parts, 0 missing, order toc, validated
    - toc: Au pays des pierres (Eugène Le Roy)
    - text: La Belle Coutelière (`Au pays des pierres/La Belle Coutelière`) [container]
    - chapter: Chapitre I (`La Belle Coutelière/01`) [container]
    - chapter: Chapitre II (`La Belle Coutelière/02`) [container]
    - chapter: Chapitre III (`La Belle Coutelière/03`) [container]
    - chapter: Chapitre IV (`La Belle Coutelière/04`) [container]
    - chapter: Chapitre V (`La Belle Coutelière/05`) [container]
    - chapter: Chapitre VI (`La Belle Coutelière/06`) [container]
    - chapter: Chapitre VII (`La Belle Coutelière/07`) [container]
    - chapter: Chapitre VIII (`La Belle Coutelière/08`) [container]
    - text: Roquejoffre (`Au pays des pierres/Roquejoffre`) [container]
    - chapter: Chapitre I (`Roquejoffre/01`) [container]
    - … 13 more
1. `Discours du roi de Wurtemberg`: single, periodical-issue, 1 parts, 0 missing, order –, proofread
    - text: Discours du roi de Wurtemberg
1. `Mélanges d’histoire des religions`: subpages, book, 5 parts, 0 missing, order chain, proofread
    - toc: Mélanges d’histoire des religions
    - chapter: Préface (`Mélanges d’histoire des religions/Introduction à l’analyse de quelques phénomènes religieux`) [container]
    - text: Essai (`Mélanges d’histoire des religions/Essai sur la nature et la fonction du sacrifice`) [container]
    - text: L’Origine magiques australiennes (`Mélanges d’histoire des religions/L’Origine des pouvoirs magiques dans les sociétés australiennes`) [container]
    - text: Étude sommaire (`Mélanges d’histoire des religions/Étude sommaire de la représentation du temps dans la religion et la magie`) [container]
    - chapter: Table desmatières (`Mélanges d’histoire des religions/Table des matières`) [container]
1. `Les Œuvres et les Hommes`: subpages, book, 88 parts, 2 missing, order toc, unproofread
    - front: Les Œuvres et les Hommes
    - volume: I. Les Philosophes et les Écrivains religieux (`Les Œuvres et les Hommes/Les Philosophes et les Écrivains religieux (1860)`) [hierarchy]
      - chapter: Préface (`Les Œuvres et les Hommes/Les Philosophes et les Écrivains religieux (1860)/Préface`) [hierarchy]
      - text: Saint Thomas d’Aquin (`Les Œuvres et les Hommes/Les Philosophes et les Écrivains religieux (1860)/Saint Thomas d’Aquin`) [hierarchy]
      - text: Jean Reynaud (`Les Œuvres et les Hommes/Les Philosophes et les Écrivains religieux (1860)/Jean Reynaud`) [hierarchy]
      - text: Donoso Cortès (`Les Œuvres et les Hommes/Les Philosophes et les Écrivains religieux (1860)/Donoso Cortès`) [hierarchy]
      - text: Saisset (`Les Œuvres et les Hommes/Les Philosophes et les Écrivains religieux (1860)/Saisset`) [hierarchy]
      - text: Saint-René Taillandier (`Les Œuvres et les Hommes/Les Philosophes et les Écrivains religieux (1860)/Saint-René Taillandier`) [hierarchy]
      - text: Jules Simon (`Les Œuvres et les Hommes/Les Philosophes et les Écrivains religieux (1860)/Jules Simon`) [hierarchy]
      - text: Vera (`Les Œuvres et les Hommes/Les Philosophes et les Écrivains religieux (1860)/Vera`) [hierarchy]
      - text: Du Mysticisme et de Saint-Martin (`Les Œuvres et les Hommes/Les Philosophes et les Écrivains religieux (1860)/Du Mysticisme et de Saint-Martin`) [hierarchy]
      - text: L’abbé Mitraud (`Les Œuvres et les Hommes/Les Philosophes et les Écrivains religieux (1860)/L’abbé Mitraud`) [hierarchy]
    - … 77 more
1. `Robinson Crusoé (Borel)`: subpages, book, 118 parts, 0 missing, order natural, validated
    - toc: Robinson Crusoé (Borel)
    - text: Robinson (`Robinson Crusoé (Borel)/1`) [container]
    - text: La Tempête (`Robinson Crusoé (Borel)/2`) [container]
    - text: Robinson Marchand de Guin (`Robinson Crusoé (Borel)/3`) [container]
    - text: Robinson Captif (`Robinson Crusoé (Borel)/4`) [container]
    - text: Première Aiguade (`Robinson Crusoé (Borel)/5`) [container]
    - text: Robinson et Xury vainqueurs d’un Lion (`Robinson Crusoé (Borel)/6`) [container]
    - text: Propositions des trois Colons (`Robinson Crusoé (Borel)/7`) [container]
    - text: Naufrage (`Robinson Crusoé (Borel)/8`) [container]
    - text: Seuls Restes de l’Équipage (`Robinson Crusoé (Borel)/9`) [container]
    - text: Le Radeau (`Robinson Crusoé (Borel)/10`) [container]
    - text: La Chambre du Capitaine (`Robinson Crusoé (Borel)/11`) [container]
    - … 107 more
1. `La Salle d’armes`: virtual, book, 35 parts, 0 missing, order natural, proofread
    - volume: I (`La Salle d’armes/I`)
      - chapter: 01 (`La Salle d’armes/I — Pauline/01`) [container]
      - chapter: 02 (`La Salle d’armes/I — Pauline/02`) [container]
      - chapter: 03 (`La Salle d’armes/I — Pauline/03`) [container]
      - chapter: 04 (`La Salle d’armes/I — Pauline/04`) [container]
      - chapter: 05 (`La Salle d’armes/I — Pauline/05`) [container]
      - chapter: 06 (`La Salle d’armes/I — Pauline/06`) [container]
      - chapter: 07 (`La Salle d’armes/I — Pauline/07`) [container]
      - chapter: 08 (`La Salle d’armes/I — Pauline/08`) [container]
      - chapter: 09 (`La Salle d’armes/I — Pauline/09`) [container]
      - chapter: 10 (`La Salle d’armes/I — Pauline/10`) [container]
      - chapter: 11 (`La Salle d’armes/I — Pauline/11`) [container]
    - … 23 more
1. `Phèdre (trad. Robin)`: subpages, book, 1 parts, 2 missing, order single, validated
    - front: Phèdre (trad. Robin)
    - text: Notice du Phèdre (`Phèdre (trad. Robin)/Notice`) [hierarchy]
1. `L’Ancien Régime et la Révolution`: subpages, book, 7 parts, 0 missing, order scan, validated
    - toc: L’Ancien Régime et la Révolution
    - chapter: Avant-propos (`L’Ancien Régime et la Révolution/Avant-propos`) [container]
    - chapter: Livre 1 (`L’Ancien Régime et la Révolution/Livre 1`) [container]
    - chapter: Livre 2 (`L’Ancien Régime et la Révolution/Livre 2`) [container]
    - chapter: CHAPITRE III (`L’Ancien Régime et la Révolution/Livre 3`) [container]
    - chapter: APPENDICE (`L’Ancien Régime et la Révolution/Appendice`) [container]
    - chapter: Notes (`L’Ancien Régime et la Révolution/Notes`) [container]
    - chapter: Table (`L’Ancien Régime et la Révolution/Table`) [container]
1. `Les Illuminés (1852)`: subpages, book, 7 parts, 0 missing, order toc, validated
    - toc: Les Illuminés (1852)
    - text: La Bibliothèque de mon oncle (`Les Illuminés (1852)/La Bibliothèque de mon oncle`) [container]
    - text: . (Raoul Spifame.) (`Les Illuminés (1852)/Le Roi de Bicêtre`) [container]
    - text: Histoire de l’abbé de Bucquoy (`Les Illuminés (1852)/Histoire de l’abbé de Bucquoy`) [container]
    - text: . (Restif.) (`Les Illuminés (1852)/Les Confidences de Nicolas`) [container]
    - text: Cazotte (`Les Illuminés (1852)/Cazotte`) [container]
    - text: Cagliostro (`Les Illuminés (1852)/Cagliostro`) [container]
    - text: Quintus Aucler (`Les Illuminés (1852)/Quintus Aucler`) [container]
1. `Les Grands Cimetières sous la lune`: subpages, book, 15 parts, 0 missing, order toc, validated
    - front: Les Grands Cimetières sous la lune
    - chapter: Avant-propos (`Les Grands Cimetières sous la lune/0`) [container]
    - volume: Première partie (`Les Grands Cimetières sous la lune/I`) [container]
      - chapter: Première partie — I (`Les Grands Cimetières sous la lune/I/1`) [container]
      - chapter: Première partie — II (`Les Grands Cimetières sous la lune/I/2`) [container]
      - chapter: Première partie — III (`Les Grands Cimetières sous la lune/I/3`) [container]
      - chapter: Première partie — IV (`Les Grands Cimetières sous la lune/I/4`) [container]
    - volume: Deuxième partie (`Les Grands Cimetières sous la lune/II`) [container]
      - chapter: Deuxième partie — I (`Les Grands Cimetières sous la lune/II/1`) [container]
      - chapter: Deuxième partie — II (`Les Grands Cimetières sous la lune/II/2`) [container]
      - chapter: Deuxième partie — III (`Les Grands Cimetières sous la lune/II/3`) [container]
    - volume: Troisième partie (`Les Grands Cimetières sous la lune/III`) [container]
    - … 4 more
1. `Tableaux de la nature`: subpages, collection, 11 parts, 0 missing, order toc, proofread
    - front: Tableaux de la nature
    - chapter: Préface (`Tableaux de la nature/Préface`) [hierarchy]
    - text: Invocation (`Tableaux de la nature/Invocation`) [hierarchy]
    - text: La Forêt (`Tableaux de la nature/La Forêt`) [hierarchy]
    - text: Le Soir au bord de la Mer (`Tableaux de la nature/Le Soir au bord de la mer`) [hierarchy]
    - text: Le Soir dans une Vallée (`Tableaux de la nature/Le Soir, dans une vallée`) [hierarchy]
    - text: Nuit de Printemps (`Tableaux de la nature/Nuit de printemps`) [hierarchy]
    - text: Nuit d’Automne (`Tableaux de la nature/Nuit d’automne`) [hierarchy]
    - text: Le Printemps, l’Été et l’Hiver (`Tableaux de la nature/Le Printemps, l’été et l’hiver`) [hierarchy]
    - text: La Mer (`Tableaux de la nature/La Mer`) [hierarchy]
    - text: L’Amour de la Campagne (`Tableaux de la nature/L’Amour de la campagne`) [hierarchy]
    - text: Les Adieux (`Tableaux de la nature/Les Adieux`) [hierarchy]
1. `Mémoires de la duchesse de Nemours`: single, book, 1 parts, 0 missing, order –, unproofread
    - text: Mémoires de la duchesse de Nemours
1. `L’Héroïne chrétienne du Canada, ou Vie de Mlle Le Ber`: subpages, book, 31 parts, 0 missing, order toc, proofread
    - toc: L’Héroïne chrétienne du Canada, ou Vie de Mlle Le Ber
    - chapter: Introduction (`L’Héroïne chrétienne du Canada, ou Vie de Mlle Le Ber/00`) [container]
    - volume: Enfance de . Son séjour au Pensionnat des Ursulines à Québec. (`L’Héroïne chrétienne du Canada, ou Vie de Mlle Le Ber/01`) [container]
      - chapter: I. — Famille de . Sa Naissance. Son Baptême. Sa première éducation (`L’Héroïne chrétienne du Canada, ou Vie de Mlle Le Ber/01/01`) [container]
      - chapter: II. — entre au Pensionnat des Ursulines de Québec. Sa Mortification. Sa Politesse. Sa Charité pour ses compagnes (`L’Héroïne chrétienne du Canada, ou Vie de Mlle Le Ber/01/02`) [container]
      - chapter: III. — fait paraître par la simplicité de ses petits meubles, l’horreur qu’elle a déjà pour les vanités du monde, et donne à ses compagnes des exemples d’une admirable humilité (`L’Héroïne chrétienne du Canada, ou Vie de Mlle Le Ber/01/03`) [container]
      - chapter: IV. — Fidélité de à garder le silence prescrit aux pensionnaires, et à marcher en la sainte présence de Dieu. Son amour pour la méditation (`L’Héroïne chrétienne du Canada, ou Vie de Mlle Le Ber/01/04`) [container]
      - chapter: V. — Dévotion de envers les Saints Anges. Sa piété envers Marie. Sa religion envers le Très-Saint Sacrement (`L’Héroïne chrétienne du Canada, ou Vie de Mlle Le Ber/01/05`) [container]
    - volume: Depuis la sortie de Ber du Pensionnat, jusqu’à son entrée à la Congrégation en qualité de Recluse. (`L’Héroïne chrétienne du Canada, ou Vie de Mlle Le Ber/02`) [container]
      - chapter: I. — Rentrée dans le monde, demeure fidèle à tous les exercices de piété, qu’elle avait pratiqués au pensionnat. La modestie de ses vêtements (`L’Héroïne chrétienne du Canada, ou Vie de Mlle Le Ber/02/01`) [container]
      - chapter: II. — Pour conserver le trésor de son innocence, Ber ne se produit dans aucune assemblée du monde. Elle fréquente les Sœurs de la Congrégation (`L’Héroïne chrétienne du Canada, ou Vie de Mlle Le Ber/02/02`) [container]
      - chapter: III. — La mort édifiante d’une des Sœurs de la Congrégation, détermine à vivre entièrement séparée du monde. Elle fait vœu de Chasteté pour cinq ans (`L’Héroïne chrétienne du Canada, ou Vie de Mlle Le Ber/02/03`) [container]
    - … 20 more
1. `Mémoires de Joinville`: mixed, book, 10 parts, 0 missing, order toc, unproofread
    - toc: Mémoires de Joinville
    - text: . (`Mémoires de Joinville/Avertissement`) [container]
    - text: Notice sur Joinville (`Mémoires de Joinville/Notice sur Joinville`) [container]
    - text: Tableau du règne de Saint-Louis (`Mémoires de Joinville/Tableau du règne de Saint-Louis`) [container]
    - text: Récit officiel de l’expédition de saint Louis. (`Mémoires de Joinville/Récit officiel de l’expédition de saint Louis`) [container]
    - text: Épître dédicatoire. (`Mémoires de Joinville/Épître dédicatoire`) [container]
    - chapter: Préface (`Mémoires de Joinville/Préface`) [container]
    - chapter: Première partie de l’histoire. (`Mémoires de Joinville/Première partie de l’histoire`) [container]
    - chapter: Seconde partie de l’histoire. (`Mémoires de Joinville/Seconde partie de l’histoire`) [container]
    - text: Variantes (`Collection complète des mémoires relatifs à l’histoire de France/Variantes`) [container]
    - text: qui accompagnèrent saint Louis. (`Mémoires de Joinville/Liste des Chevaliers qui accompagnèrent saint Louis`) [container]
1. `La Mère (Gorki)`: subpages, book, 62 parts, 0 missing, order toc, proofread
    - front: La Mère (Gorki)
    - text: Une visite à Anna Zalomova, l’héroïne de la mère. (`La Mère (Gorki)/Une visite à Anna Zalomova`) [container]
    - volume: Première partie (`La Mère (Gorki)/1`) [container]
      - chapter: I (`La Mère (Gorki)/1/1`) [container]
      - chapter: II (`La Mère (Gorki)/1/2`) [container]
      - chapter: III (`La Mère (Gorki)/1/3`) [container]
      - chapter: IV (`La Mère (Gorki)/1/4`) [container]
      - chapter: V (`La Mère (Gorki)/1/5`) [container]
      - chapter: VI (`La Mère (Gorki)/1/6`) [container]
      - chapter: VII (`La Mère (Gorki)/1/7`) [container]
      - chapter: VIII (`La Mère (Gorki)/1/8`) [container]
      - chapter: IX (`La Mère (Gorki)/1/9`) [container]
    - … 51 more
1. `Stendhal - Correspondance - Tome I`: linked, book, 1 parts, 0 missing, order single, unproofread
    - front: Stendhal - Correspondance - Tome I
    - chapter: 1 (`Stendhal - Correspondance/Tome 1/0001`) [container]
1. `Clio`: single, book, 1 parts, 0 missing, order –, unproofread
    - text: Clio
1. `La Filleule`: subpages, book, 2 parts, 0 missing, order scan, proofread
    - toc: La Filleule
    - chapter: 1 (`La Filleule/1`) [container]
    - chapter: 2 (`La Filleule/2`) [container]
1. `Au soir de la pensée`: subpages, book, 15 parts, 0 missing, order natural, proofread
    - front: Au soir de la pensée
    - chapter: CHAPITRE PREMIER (`Au soir de la pensée/Chapitre 1`) [container]
    - chapter: CHAPITRE II (`Au soir de la pensée/Chapitre 2`) [container]
    - chapter: CHAPITRE III (`Au soir de la pensée/Chapitre 3`) [container]
    - chapter: CHAPITRE IV (`Au soir de la pensée/Chapitre 4`) [container]
    - chapter: Chapitre 5 (`Au soir de la pensée/Chapitre 5`) [container]
    - chapter: Chapitre 6 (`Au soir de la pensée/Chapitre 6`) [container]
    - chapter: CHAPITRE VII (`Au soir de la pensée/Chapitre 7`) [container]
    - chapter: CHAPITRE VIII (`Au soir de la pensée/Chapitre 8`) [container]
    - chapter: CHAPITRE IX (`Au soir de la pensée/Chapitre 9`) [container]
    - chapter: CHAPITRE X (`Au soir de la pensée/Chapitre 10`) [container]
    - chapter: CHAPITRE XI (`Au soir de la pensée/Chapitre 11`) [container]
    - … 4 more
1. `Catégories (Aristote, traduction Saint-Hilaire)`: single, book, 1 parts, 0 missing, order –, proofread
    - text: Catégories (Aristote, traduction Saint-Hilaire)
1. `Antoinette`: subpages, book, 27 parts, 0 missing, order chain, proofread
    - front: Antoinette
    - chapter: 1 (`Antoinette/1`) [container]
    - chapter: 2 (`Antoinette/2`) [container]
    - chapter: 3 (`Antoinette/3`) [container]
    - chapter: 4 (`Antoinette/4`) [container]
    - chapter: 5 (`Antoinette/5`) [container]
    - chapter: 6 (`Antoinette/6`) [container]
    - chapter: 7 (`Antoinette/7`) [container]
    - chapter: 8 (`Antoinette/8`) [container]
    - chapter: 9 (`Antoinette/9`) [container]
    - chapter: 10 (`Antoinette/10`) [container]
    - chapter: 11 (`Antoinette/11`) [container]
    - … 16 more
1. `La Légende de Gösta Berling`: subpages, book, 31 parts, 0 missing, order toc, validated
    - front: La Légende de Gösta Berling
    - chapter: Note du traducteur (`La Légende de Gösta Berling/Note du traducteur`) [container]
    - volume: Introduction (`La Légende de Gösta Berling/Introduction`) [container]
      - text: — Le pasteur (`La Légende de Gösta Berling/Introduction/1`) [container]
      - text: — Le mendiant (`La Légende de Gösta Berling/Introduction/2`) [hierarchy]
    - chapter: Chapitre I. — Lepaysage (`La Légende de Gösta Berling/I`) [container]
    - chapter: Chapitre II. — Lanuit deNoël (`La Légende de Gösta Berling/II`) [container]
    - chapter: Chapitre III. — Ledîner deNoël (`La Légende de Gösta Berling/III`) [container]
    - chapter: Chapitre IV. — Lesloups (`La Légende de Gösta Berling/IV`) [container]
    - chapter: Chapitre V. — Lebal àEkebu (`La Légende de Gösta Berling/V`) [container]
    - chapter: Chapitre VI. — Lesvieilles voitures (`La Légende de Gösta Berling/VI`) [container]
    - chapter: Chapitre VII. — Legrand Ours deGurlita (`La Légende de Gösta Berling/VII`) [container]
    - … 20 more
1. `La Première Tentation de Saint Antoine`: subpages, book, 22 parts, 0 missing, order toc, proofread
    - front: La Première Tentation de Saint Antoine
    - volume: Préface (`La Première Tentation de Saint Antoine/Préface`) [container]
      - chapter: I (`La Première Tentation de Saint Antoine/Préface/I`) [container]
      - chapter: II (`La Première Tentation de Saint Antoine/Préface/II`) [container]
      - chapter: III (`La Première Tentation de Saint Antoine/Préface/III`) [container]
    - chapter: I (`La Première Tentation de Saint Antoine/I`) [container]
    - chapter: II (`La Première Tentation de Saint Antoine/II`) [container]
    - chapter: III (`La Première Tentation de Saint Antoine/III`) [container]
    - volume: Fragments (`La Première Tentation de Saint Antoine/Fragments`) [container]
      - chapter: I (`La Première Tentation de Saint Antoine/Fragments/I`) [container]
      - chapter: II (`La Première Tentation de Saint Antoine/Fragments/II`) [container]
      - chapter: III (`La Première Tentation de Saint Antoine/Fragments/III`) [container]
    - … 11 more

### G: reference works (218)

1. `Dictionnaire des métaphores de Victor Hugo`: single, reference, 1 parts, 0 missing, order –, unproofread, excluded no-text
    - text: Dictionnaire des métaphores de Victor Hugo
1. `Grand dictionnaire universel du XIXe siècle`: subpages, reference, 5,834 parts, 19902 missing, order natural, unproofread
    - front: Grand dictionnaire universel du XIXe siècle
    - text: & (`Grand dictionnaire universel du XIXe siècle/&`) [hierarchy]
    - text: A (sans accent) (`Grand dictionnaire universel du XIXe siècle/A (sans accent)`) [container]
    - text: a bas le feu ! loc. interj. (`Grand dictionnaire universel du XIXe siècle/a bas le feu ! loc. interj.`) [container]
    - text: a bas le monde ! loc. interj. (`Grand dictionnaire universel du XIXe siècle/a bas le monde ! loc. interj.`) [container]
    - text: a battuta loc. adv. (`Grand dictionnaire universel du XIXe siècle/a battuta loc. adv.`) [container]
    - text: a bout de bord loc. adv. (`Grand dictionnaire universel du XIXe siècle/a bout de bord loc. adv.`) [container]
    - text: a bras-le-corps loc. adv. (`Grand dictionnaire universel du XIXe siècle/a bras-le-corps loc. adv.`) [container]
    - text: a capella loc. adv. (`Grand dictionnaire universel du XIXe siècle/a capella loc. adv.`) [container]
    - text: a capricio loc. adv. (`Grand dictionnaire universel du XIXe siècle/a capricio loc. adv.`) [container]
    - text: a ce que loc. conj. (`Grand dictionnaire universel du XIXe siècle/a ce que loc. conj.`) [container]
    - text: a fortiori loc. adv. (`Grand dictionnaire universel du XIXe siècle/a fortiori loc. adv.`) [container]
    - … 5,823 more
1. `Syllabaire moderne universel`: single, reference, 1 parts, 0 missing, order –, proofread
    - text: Syllabaire moderne universel
1. `Explication des abréviations maritimes les plus usuelles`: subpages, reference, 84 parts, 0 missing, order natural, validated
    - front: Explication des abréviations maritimes les plus usuelles
    - text: Against all risks (`Explication des abréviations maritimes les plus usuelles/Articles/Against all risks`) [container]
    - text: Article 10 de la Police d’Assurance d’Anvers (`Explication des abréviations maritimes les plus usuelles/Articles/Article 10 de la Police d’Assurance d’Anvers`) [container]
    - text: Bill of Lading (`Explication des abréviations maritimes les plus usuelles/Articles/Bill of Lading`) [container]
    - text: Dead-freight (`Explication des abréviations maritimes les plus usuelles/Articles/Dead-freight`) [container]
    - text: Dead-weight (`Explication des abréviations maritimes les plus usuelles/Articles/Dead-weight`) [container]
    - text: Errors and omissions excepted (`Explication des abréviations maritimes les plus usuelles/Articles/Errors and omissions excepted`) [container]
    - text: General Average (`Explication des abréviations maritimes les plus usuelles/Articles/General Average`) [container]
    - text: Hundredweight (`Explication des abréviations maritimes les plus usuelles/Articles/Hundredweight`) [container]
    - text: Less car load (`Explication des abréviations maritimes les plus usuelles/Articles/Less car load`) [container]
    - text: Livres avoir du poids (`Explication des abréviations maritimes les plus usuelles/Articles/Livres avoir du poids`) [container]
    - text: Sauf imprévus (`Explication des abréviations maritimes les plus usuelles/Articles/Sauf imprévus`) [container]
    - … 73 more
1. `Biographie universelle, ou Dictionnaire historique`: single, reference, 1 parts, 0 missing, order –, unproofread
    - text: Biographie universelle, ou Dictionnaire historique
1. `Dictionnaire historique, géographique et biographique de Maine-et-Loire`: subpages, reference, 157 parts, 78 missing, order natural, unproofread
    - toc: Dictionnaire historique, géographique et biographique de Maine-et-Loire
    - text: ABA-ANG (`Dictionnaire historique, géographique et biographique de Maine-et-Loire/ABA-ANG`) [container]
    - text: Adam (Nicolas) (`Dictionnaire historique, géographique et biographique de Maine-et-Loire/Adam (Nicolas)`) [container]
    - text: Albert (le Père) (`Dictionnaire historique, géographique et biographique de Maine-et-Loire/Albert (le Père)`) [container]
    - text: Bruxolle (la) (`Dictionnaire historique, géographique et biographique de Maine-et-Loire/Bruxolle (la)`) [container]
    - volume: Index alphabétique - A (`Dictionnaire historique, géographique et biographique de Maine-et-Loire/Index alphabétique - A`) [hierarchy]
      - text: Abaldus (`Dictionnaire historique, géographique et biographique de Maine-et-Loire/Abaldus`) [container]
      - text: Abatis (l’) (`Dictionnaire historique, géographique et biographique de Maine-et-Loire/Abatis (l’)`) [container]
      - text: Abatis (les) (`Dictionnaire historique, géographique et biographique de Maine-et-Loire/Abatis (les)`) [container]
      - text: Abattoir (l’) (`Dictionnaire historique, géographique et biographique de Maine-et-Loire/Abattoir (l’)`) [container]
      - text: Abbaye (l’) (`Dictionnaire historique, géographique et biographique de Maine-et-Loire/Abbaye (l’)`) [container]
      - text: Abbaye de Ferrières (l’) (`Dictionnaire historique, géographique et biographique de Maine-et-Loire/Abbaye de Ferrières (l’)`) [container]
    - … 146 more
1. `Dictionnaire historique, critique et bibliographique`: virtual, reference, 87 parts, 2 missing, order natural, unproofread
    - volume: 1re éd., 1766 (`Dictionnaire historique, critique et bibliographique/1re éd., 1766`)
      - text: Zoile (`Dictionnaire historique, critique et bibliographique/1re éd., 1766/Zoile`) [container]
      - text: Zonare (jean) (`Dictionnaire historique, critique et bibliographique/1re éd., 1766/Zonare (jean)`) [container]
      - text: Zonca (victor) (`Dictionnaire historique, critique et bibliographique/1re éd., 1766/Zonca (victor)`) [container]
      - text: Zopyre (`Dictionnaire historique, critique et bibliographique/1re éd., 1766/Zopyre`) [container]
      - text: Zoroastre (`Dictionnaire historique, critique et bibliographique/1re éd., 1766/Zoroastre`) [container]
      - text: Zorobabel (`Dictionnaire historique, critique et bibliographique/1re éd., 1766/Zorobabel`) [container]
      - text: Zosime (pape) (`Dictionnaire historique, critique et bibliographique/1re éd., 1766/Zosime (pape)`) [container]
      - text: Zosime (comte) (`Dictionnaire historique, critique et bibliographique/1re éd., 1766/Zosime (comte)`) [container]
      - text: Zosime (désert) (`Dictionnaire historique, critique et bibliographique/1re éd., 1766/Zosime (désert)`) [container]
      - text: Zouch (richard) (`Dictionnaire historique, critique et bibliographique/1re éd., 1766/Zouch (richard)`) [container]
      - text: Zuccharo (frederic) (`Dictionnaire historique, critique et bibliographique/1re éd., 1766/Zuccharo (frederic)`) [container]
    - … 75 more
1. `Dictionnaire complet de la langue française`: mixed, reference, 21 parts, 0 missing, order scan, proofread
    - toc: Dictionnaire complet de la langue française
    - volume: Nouveau dictionnaire de la langue française [container]
      - text: Signes et abréviations (`Nouveau dictionnaire de la langue française/Signes et abréviations`) [hierarchy]
      - chapter: Lettre A (`Nouveau dictionnaire de la langue française/A`) [hierarchy]
      - chapter: Lettre B (`Nouveau dictionnaire de la langue française/B`) [hierarchy]
      - chapter: Lettre C (`Nouveau dictionnaire de la langue française/C`) [hierarchy]
      - chapter: Lettre D (`Nouveau dictionnaire de la langue française/D`) [hierarchy]
      - chapter: Lettre E (`Nouveau dictionnaire de la langue française/E`) [hierarchy]
      - chapter: Lettre F (`Nouveau dictionnaire de la langue française/F`) [hierarchy]
      - chapter: Lettre O (`Nouveau dictionnaire de la langue française/O`) [hierarchy]
      - chapter: Lettre T (`Nouveau dictionnaire de la langue française/T`) [hierarchy]
      - chapter: Lettre U (`Nouveau dictionnaire de la langue française/U`) [hierarchy]
    - … 10 more
1. `Dictionnaire critique de la langue française`: subpages, reference, 1 parts, 2 missing, order single, unproofread
    - toc: Dictionnaire critique de la langue française
    - text: Noms des auteurs cités (`Dictionnaire critique de la langue française/Auteurs cites`) [container]
1. `Petit Dictionnaire de l’Ancien Français`: subpages, reference, 28 parts, 0 missing, order toc, proofread
    - toc: Petit Dictionnaire de l’Ancien Français
    - chapter: Avant-propos (`Petit Dictionnaire de l’Ancien Français/Avant-propos`) [container]
    - text: Étymologie (`Petit Dictionnaire de l’Ancien Français/Étymologie`) [container]
    - text: Abréviations (`Petit Dictionnaire de l’Ancien Français/Abréviations`) [container]
    - text: A (`Petit Dictionnaire de l’Ancien Français/A`) [container]
    - text: B (`Petit Dictionnaire de l’Ancien Français/B`) [container]
    - chapter: C (`Petit Dictionnaire de l’Ancien Français/C`) [container]
    - chapter: D (`Petit Dictionnaire de l’Ancien Français/D`) [container]
    - text: E (`Petit Dictionnaire de l’Ancien Français/E`) [container]
    - text: F (`Petit Dictionnaire de l’Ancien Français/F`) [container]
    - text: G (`Petit Dictionnaire de l’Ancien Français/G`) [container]
    - text: H (`Petit Dictionnaire de l’Ancien Français/H`) [container]
    - … 17 more
1. `Placet des Libraires associés au comte d’Argenson`: single, reference, 1 parts, 0 missing, order –, proofread
    - text: Placet des Libraires associés au comte d’Argenson
1. `Cours d’agriculture (Rozier)`: subpages, reference, 3,148 parts, 0 missing, order natural, proofread
    - toc: Cours d’agriculture (Rozier)
    - text: ABAISSEMENT (`Cours d’agriculture (Rozier)/ABAISSEMENT`) [container]
    - text: ABAISSER (`Cours d’agriculture (Rozier)/ABAISSER`) [container]
    - text: ABAT-FOIN (`Cours d’agriculture (Rozier)/ABAT-FOIN`) [container]
    - text: ABATIS (`Cours d’agriculture (Rozier)/ABATIS`) [container]
    - text: ABATTEMENT (`Cours d’agriculture (Rozier)/ABATTEMENT`) [container]
    - text: ABATTEMENT (supplément) (`Cours d’agriculture (Rozier)/ABATTEMENT (supplément)`) [container]
    - text: ABATTIS (`Cours d’agriculture (Rozier)/ABATTIS`) [container]
    - text: ABATTRE (`Cours d’agriculture (Rozier)/ABATTRE`) [container]
    - text: ABATTRE DU PIED (`Cours d’agriculture (Rozier)/ABATTRE DU PIED`) [container]
    - text: ABATTURE (`Cours d’agriculture (Rozier)/ABATTURE`) [container]
    - text: ABATTURES (`Cours d’agriculture (Rozier)/ABATTURES`) [container]
    - … 3,137 more
1. `Nouvelle Biographie générale`: subpages, reference, 272 parts, 1252 missing, order natural, unproofread
    - front: Nouvelle Biographie générale
    - text: * BAADEN - DURLACH (Marguerite DE) (`Nouvelle Biographie générale/* BAADEN - DURLACH (Marguerite DE)`) [hierarchy]
    - text: * BAADER (Amélie) (`Nouvelle Biographie générale/* BAADER (Amélie)`) [hierarchy]
    - text: * BAADER (François-Xavier DE) (`Nouvelle Biographie générale/* BAADER (François-Xavier DE)`) [hierarchy]
    - text: * BAADER (Jean) (`Nouvelle Biographie générale/* BAADER (Jean)`) [hierarchy]
    - text: * BAADER (Jean-Michel) (`Nouvelle Biographie générale/* BAADER (Jean-Michel)`) [hierarchy]
    - text: * BAADER (Tobie) (`Nouvelle Biographie générale/* BAADER (Tobie)`) [hierarchy]
    - text: * BAAHDIN-MEHEMET-GEBET-AMEH (`Nouvelle Biographie générale/* BAAHDIN-MEHEMET-GEBET-AMEH`) [hierarchy]
    - text: * BAAHDIN-MEHEMET-GEMET-AMEH (`Nouvelle Biographie générale/* BAAHDIN-MEHEMET-GEMET-AMEH`) [hierarchy]
    - text: * BAAK-HATTIGH (Jean) (`Nouvelle Biographie générale/* BAAK-HATTIGH (Jean)`) [hierarchy]
    - text: * BAAL, roi de Tyr (`Nouvelle Biographie générale/* BAAL, roi de Tyr`) [hierarchy]
    - text: * BAALEN (Pierre de) (`Nouvelle Biographie générale/* BAALEN (Pierre de)`) [hierarchy]
    - … 261 more
1. `Dictionnaire raisonné d’architecture et des sciences et arts qui s’y rattachent`: single, reference, 1 parts, 0 missing, order –, unproofread
    - text: Dictionnaire raisonné d’architecture et des sciences et arts qui s’y rattachent
1. `Dictionnaire historique, littéraire et critique de Barral`: subpages, reference, 2 parts, 0 missing, order natural, unproofread
    - front: Dictionnaire historique, littéraire et critique de Barral
    - text: Aristote (`Dictionnaire historique, littéraire et critique de Barral/Aristote`) [container]
    - text: Zoroastre (`Dictionnaire historique, littéraire et critique de Barral/Zoroastre`) [container]
1. `Atlas universel d’histoire et géographie`: subpages, reference, 98 parts, 176 missing, order scan, unproofread
    - front: Atlas universel d’histoire et géographie
    - chapter: Introduction (`Atlas universel d’histoire et géographie/Introduction`) [container]
    - volume: Table détaillée (`Atlas universel d’histoire et géographie/Table détaillée`) [hierarchy]
      - text: Afrique (`Atlas universel d’histoire et géographie/Table détaillée/Afrique`) [container]
    - text: Titres (`Atlas universel d’histoire et géographie/Titres`) [container]
    - chapter: Avant-propos (`Atlas universel d’histoire et géographie/Avant-propos`) [container]
    - chapter: Préface (`Atlas universel d’histoire et géographie/Préface`) [container]
    - text: Eres (`Atlas universel d’histoire et géographie/Eres`) [container]
    - text: Principales ères (`Atlas universel d’histoire et géographie/Principales ères`) [container]
    - text: Concordance années olympiques, de Rome et de Jésus-Christ (`Atlas universel d’histoire et géographie/Concordance années olympiques, de Rome et de Jésus-Christ`) [container]
    - text: Concordance Années de l’Hégire (`Atlas universel d’histoire et géographie/Concordance Années de l’Hégire`) [container]
    - text: Concordance Années des Archontes (`Atlas universel d’histoire et géographie/Concordance Années des Archontes`) [container]
    - … 87 more
1. `Grand vocabulaire françois`: virtual, reference, 4 parts, 1 missing, order single, proofread
    - volume: 2e éd., 1767 (`Grand vocabulaire françois/2e éd., 1767`)
      - chapter: Introduction (`Grand vocabulaire françois/2e éd., 1767/Introduction`) [hierarchy]
      - chapter: Tome 1 (`Grand vocabulaire françois/2e éd., 1767/Tome 1`) [container]
      - text: abloquié adj. (`Grand vocabulaire françois/2e éd., 1767/abloquié adj.`) [container]
1. `Dictionnaire de l’ancien langage françois`: virtual, reference, 7 parts, 0 missing, order natural, unproofread
    - volume: 1re éd., 1766 (`Dictionnaire de l’ancien langage françois/1re éd., 1766`)
      - text: Truffe (`Dictionnaire de l’ancien langage françois/1re éd., 1766/Truffe`) [container]
    - text: 1re éd., 1767 (`Dictionnaire de l’ancien langage françois/1re éd., 1767`) [hierarchy]
    - volume: Nlle éd., 1768 (`Dictionnaire de l’ancien langage françois/Nlle éd., 1768`) [hierarchy]
      - chapter: Tome 1 (`Dictionnaire de l’ancien langage françois/Nlle éd., 1768/Tome 1`) [container]
      - text: Discours sur l'origine et les révolutions des Langues Celtique & Françoise (`Dictionnaire de l’ancien langage françois/Nlle éd., 1768/Discours`) [container]
      - text: Index alphabétique - Z (`Dictionnaire de l’ancien langage françois/Nlle éd., 1768/Index alphabétique - Z`) [container]
1. `Le grand dictionnaire historique`: virtual, reference, 563 parts, 0 missing, order natural, unproofread
    - text: éd. de 1717 (`Le grand dictionnaire historique/éd. de 1717`)
    - volume: éd. de 1759 (`Le grand dictionnaire historique/éd. de 1759`) [hierarchy]
      - text: A (`Le grand dictionnaire historique/éd. de 1759/A`) [hierarchy]
      - text: Aa (`Le grand dictionnaire historique/éd. de 1759/Aa`) [hierarchy]
      - text: Aa ou Aas (`Le grand dictionnaire historique/éd. de 1759/Aa ou Aas`) [hierarchy]
      - text: Aab (`Le grand dictionnaire historique/éd. de 1759/Aab`) [hierarchy]
      - text: Aach (`Le grand dictionnaire historique/éd. de 1759/Aach`) [hierarchy]
      - text: Aad (`Le grand dictionnaire historique/éd. de 1759/Aad`) [hierarchy]
      - text: Aagard (Christian) (`Le grand dictionnaire historique/éd. de 1759/Aagard (Christian)`) [hierarchy]
      - text: Aagard (Nicolas) (`Le grand dictionnaire historique/éd. de 1759/Aagard (Nicolas)`) [hierarchy]
      - text: Aahus (`Le grand dictionnaire historique/éd. de 1759/Aahus`) [hierarchy]
      - text: Aain-Charin (`Le grand dictionnaire historique/éd. de 1759/Aain-Charin`) [hierarchy]
    - … 551 more
1. `Dictionnaire de la Bible - Vigouroux`: mixed, reference, 800 parts, 0 missing, order natural, proofread, excluded index-faulty
    - front: Dictionnaire de la Bible - Vigouroux
    - text: Index alphabétique - A (`Dictionnaire de la Bible - Vigouroux/Index alphabétique - A`) [hierarchy]
    - text: Index alphabétique - B (`Dictionnaire de la Bible - Vigouroux/Index alphabétique - B`) [hierarchy]
    - text: Index alphabétique - C (`Dictionnaire de la Bible - Vigouroux/Index alphabétique - C`) [hierarchy]
    - text: Index alphabétique - D (`Dictionnaire de la Bible - Vigouroux/Index alphabétique - D`) [hierarchy]
    - text: Index alphabétique - E (`Dictionnaire de la Bible - Vigouroux/Index alphabétique - E`) [hierarchy]
    - text: Index alphabétique - F (`Dictionnaire de la Bible - Vigouroux/Index alphabétique - F`) [hierarchy]
    - text: Index alphabétique - G (`Dictionnaire de la Bible - Vigouroux/Index alphabétique - G`) [hierarchy]
    - text: Index alphabétique - H (`Dictionnaire de la Bible - Vigouroux/Index alphabétique - H`) [hierarchy]
    - text: Index alphabétique - I (`Dictionnaire de la Bible - Vigouroux/Index alphabétique - I`) [hierarchy]
    - text: Index alphabétique - J (`Dictionnaire de la Bible - Vigouroux/Index alphabétique - J`) [hierarchy]
    - text: Index alphabétique - K (`Dictionnaire de la Bible - Vigouroux/Index alphabétique - K`) [hierarchy]
    - … 789 more
1. `Les Véritables régles de l’ortografe francéze`: subpages, reference, 6 parts, 0 missing, order scan, validated
    - front: Les Véritables régles de l’ortografe francéze
    - chapter: Chapitre I (`Les Véritables régles de l’ortografe francéze/Chapitre I`) [container]
    - chapter: Chapitre II (`Les Véritables régles de l’ortografe francéze/Chapitre II`) [container]
    - chapter: Chapitre III (`Les Véritables régles de l’ortografe francéze/Chapitre III`) [container]
    - chapter: Chapitre IV (`Les Véritables régles de l’ortografe francéze/Chapitre IV`) [container]
    - chapter: Chapitre Dernier (`Les Véritables régles de l’ortografe francéze/Chapitre Dernier`) [container]
    - text: Extrait dv Privileie du Roi (`Les Véritables régles de l’ortografe francéze/Extrait dv Privileie du Roi`) [container]
1. `Dictionnaire touareg – français`: subpages, reference, 33 parts, 0 missing, order scan, unproofread
    - toc: Dictionnaire touareg – français
    - text: Avertissement. (`Dictionnaire touareg – français/Avertissement`) [container]
    - text: B (`Dictionnaire touareg – français/B`) [container]
    - text: CH (`Dictionnaire touareg – français/CH`) [container]
    - chapter: D (`Dictionnaire touareg – français/D`) [container]
    - text: Ḍ (`Dictionnaire touareg – français/Ḍ`) [container]
    - text: F (`Dictionnaire touareg – français/F`) [container]
    - text: F ⴼ du début à ⴼⵔ effer, tăfereouit (`Dictionnaire touareg – français/F1`) [container]
    - text: F ⴼ de ⴼⵔ efer à la fin (`Dictionnaire touareg – français/F2`) [container]
    - text: G , Ġ (`Dictionnaire touareg – français/G`) [container]
    - text: H (`Dictionnaire touareg – français/H`) [container]
    - chapter: I (`Dictionnaire touareg – français/I`) [container]
    - … 22 more
1. `Dictionnaire wallon-français (Cambresier)`: subpages, reference, 2,590 parts, 0 missing, order scan, proofread
    - toc: Dictionnaire wallon-français (Cambresier)
    - chapter: Préface (`Dictionnaire wallon-français (Cambresier)/Préface`) [container]
    - text: Abréviations (`Dictionnaire wallon-français (Cambresier)/Abréviations`) [container]
    - text: A (`Dictionnaire wallon-français (Cambresier)/Section complète - A`) [container]
    - text: B (`Dictionnaire wallon-français (Cambresier)/Section complète - B`) [container]
    - chapter: C (`Dictionnaire wallon-français (Cambresier)/Section complète - C`) [container]
    - chapter: D (`Dictionnaire wallon-français (Cambresier)/Section complète - D`) [container]
    - text: E (`Dictionnaire wallon-français (Cambresier)/Section complète - E`) [container]
    - text: F (`Dictionnaire wallon-français (Cambresier)/Section complète - F`) [container]
    - text: G (`Dictionnaire wallon-français (Cambresier)/Section complète - G`) [container]
    - text: H (`Dictionnaire wallon-français (Cambresier)/Section complète - H`) [container]
    - chapter: I (`Dictionnaire wallon-français (Cambresier)/Section complète - I`) [container]
    - … 2,579 more
1. `Dictionnaire des locutions latines`: single, reference, 1 parts, 0 missing, order –, validated
    - text: Dictionnaire des locutions latines
1. `Dictionnaire liégeois-français (Forir)`: subpages, reference, 4,534 parts, 303 missing, order natural, proofread
    - front: Dictionnaire liégeois-français (Forir)
    - text: Amoiss (`Dictionnaire liégeois-français (Forir)/Amoiss`) [hierarchy]
    - volume: Index alphabétique - A (`Dictionnaire liégeois-français (Forir)/Index alphabétique - A`) [hierarchy]
      - text: A (`Dictionnaire liégeois-français (Forir)/A`) [container]
      - text: A, Â (`Dictionnaire liégeois-français (Forir)/A, Â`) [container]
      - text: Aairî (`Dictionnaire liégeois-français (Forir)/Aairî`) [container]
      - text: Âb (`Dictionnaire liégeois-français (Forir)/Âb`) [container]
      - text: Aba (`Dictionnaire liégeois-français (Forir)/Aba`) [container]
      - text: Abagué (`Dictionnaire liégeois-français (Forir)/Abagué`) [container]
      - text: Abaguech (`Dictionnaire liégeois-français (Forir)/Abaguech`) [container]
      - text: Abahech (`Dictionnaire liégeois-français (Forir)/Abahech`) [container]
      - text: Abahî (`Dictionnaire liégeois-français (Forir)/Abahî`) [container]
    - … 4,523 more
1. `Dictionnaire des mœurs`: subpages, reference, 719 parts, 0 missing, order scan, validated
    - toc: Dictionnaire des mœurs
    - chapter: Avant-propos (`Dictionnaire des mœurs/Texte entier/Avant-propos`) [hierarchy]
    - text: Abandon (`Dictionnaire des mœurs/Abandon`) [hierarchy]
    - text: Abbesse (`Dictionnaire des mœurs/Abbesse`) [hierarchy]
    - text: Abbé (`Dictionnaire des mœurs/Abbé`) [hierarchy]
    - text: A (`Dictionnaire des mœurs/Texte entier/A`) [hierarchy]
    - text: Abominable (`Dictionnaire des mœurs/Abominable`) [hierarchy]
    - text: Absence (`Dictionnaire des mœurs/Absence`) [hierarchy]
    - text: Absolu (`Dictionnaire des mœurs/Absolu`) [hierarchy]
    - text: Abstinence (`Dictionnaire des mœurs/Abstinence`) [hierarchy]
    - text: Abstrait (`Dictionnaire des mœurs/Abstrait`) [hierarchy]
    - text: Absurdité (`Dictionnaire des mœurs/Absurdité`) [hierarchy]
    - … 708 more
1. `Nouveau Dictionnaire de la langue française de Dochez`: subpages, reference, 4 parts, 0 missing, order scan, proofread
    - toc: Nouveau Dictionnaire de la langue française de Dochez
    - text: Titre (`Nouveau Dictionnaire de la langue française de Dochez/Titre`) [container]
    - chapter: Tome 1a (`Nouveau Dictionnaire de la langue française de Dochez/Tome 1a`) [container]
    - text: Avertissement de l'auteur (`Nouveau Dictionnaire de la langue française de Dochez/Avertissement de l'auteur`) [container]
    - text: Discours préliminaire (`Nouveau Dictionnaire de la langue française de Dochez/Discours préliminaire`) [container]
1. `Dictionnaire français de Pierre Richelet`: virtual, reference, 50 parts, 4 missing, order natural, unproofread
    - volume: 1re éd., 1680 (`Dictionnaire français de Pierre Richelet/1re éd., 1680`)
      - text: Avertissements (`Dictionnaire français de Pierre Richelet/1re éd., 1680/Avertissement`) [container]
      - text: Dédicace (`Dictionnaire français de Pierre Richelet/1re éd., 1680/Dedicace`) [container]
      - text: Explication des marques (`Dictionnaire français de Pierre Richelet/1re éd., 1680/Explication`) [container]
      - chapter: Introduction (`Dictionnaire français de Pierre Richelet/1re éd., 1680/Introduction`) [hierarchy]
      - text: Livres cités (`Dictionnaire français de Pierre Richelet/1re éd., 1680/Livres cités`) [container]
      - text: 001-010 (`Dictionnaire français de Pierre Richelet/1re éd., 1680/Tome 1/001-010`) [hierarchy]
      - chapter: Tome 1 (`Dictionnaire français de Pierre Richelet/1re éd., 1680/Tome 1/Fascicules`) [hierarchy]
      - chapter: Tome 1a (`Dictionnaire français de Pierre Richelet/1re éd., 1680/Tome 1a`) [container]
    - volume: 4e éd., 1759 (`Dictionnaire français de Pierre Richelet/4e éd., 1759`) [hierarchy]
      - text: A LA TURQUESSE (`Dictionnaire français de Pierre Richelet/4e éd., 1759/A LA TURQUESSE`) [hierarchy]
      - text: ACADÉMICIENNE (`Dictionnaire français de Pierre Richelet/4e éd., 1759/ACADÉMICIENNE`) [hierarchy]
    - … 38 more
1. `Dictionnaire de théologie catholique`: virtual, reference, 1,987 parts, 0 missing, order natural, unproofread
    - chapter: 1. VALENTIN, pape (`Dictionnaire de théologie catholique/1. VALENTIN, pape`)
    - chapter: 1. VEGA (André de) (`Dictionnaire de théologie catholique/1. VEGA (André de)`) [hierarchy]
    - chapter: 1. VIGOR Simon (`Dictionnaire de théologie catholique/1. VIGOR Simon`) [hierarchy]
    - chapter: 2. TORRES (Jérôme de) (Torrensis) (`Dictionnaire de théologie catholique/2. TORRES (Jérôme de) (Torrensis)`) [hierarchy]
    - chapter: 2. VALENTIN, gnostique I. Vie et écrits. (`Dictionnaire de théologie catholique/2. VALENTIN, gnostique I. Vie et écrits.`) [hierarchy]
    - chapter: 2. VALENTIN, gnostique II. Doctrine (`Dictionnaire de théologie catholique/2. VALENTIN, gnostique II. Doctrine`) [hierarchy]
    - chapter: 2. VALENTIN, gnostique III. Disciples. (`Dictionnaire de théologie catholique/2. VALENTIN, gnostique III. Disciples.`) [hierarchy]
    - chapter: 2. VEGA (Christophe de) (`Dictionnaire de théologie catholique/2. VEGA (Christophe de)`) [hierarchy]
    - chapter: 2. VIGOR Simon (`Dictionnaire de théologie catholique/2. VIGOR Simon`) [hierarchy]
    - chapter: 3. VALENTIN, appolinariste (`Dictionnaire de théologie catholique/3. VALENTIN, appolinariste`) [hierarchy]
    - chapter: 3. VEGA (Emmanuel de) (`Dictionnaire de théologie catholique/3. VEGA (Emmanuel de)`) [hierarchy]
    - text: Aaron (`Dictionnaire de théologie catholique/Aaron`) [hierarchy]
    - … 1,975 more
1. `Petit dictionnaire des infirmières`: subpages, reference, 313 parts, 0 missing, order scan, proofread
    - front: Petit dictionnaire des infirmières
    - text: Abcès (`Petit dictionnaire des infirmières/Abcès`) [hierarchy]
    - text: Abdomen (`Petit dictionnaire des infirmières/Abdomen`) [hierarchy]
    - text: Aisselle (`Petit dictionnaire des infirmières/Aisselle`) [hierarchy]
    - text: Albuminurie (`Petit dictionnaire des infirmières/Albuminurie`) [hierarchy]
    - text: Ammoniacal (`Petit dictionnaire des infirmières/Ammoniacal`) [hierarchy]
    - text: Ammoniaque (`Petit dictionnaire des infirmières/Ammoniaque`) [hierarchy]
    - text: Amputer (`Petit dictionnaire des infirmières/Amputer`) [hierarchy]
    - text: Anatomie (`Petit dictionnaire des infirmières/Anatomie`) [hierarchy]
    - text: Anémie (`Petit dictionnaire des infirmières/Anémie`) [hierarchy]
    - text: Anesthésie (`Petit dictionnaire des infirmières/Anesthésie`) [hierarchy]
    - text: Angine (`Petit dictionnaire des infirmières/Angine`) [hierarchy]
    - … 302 more

### H: deep nesting (186)

1. `Le Correcteur typographe (Brossard)`: subpages, book, 177 parts, 0 missing, order toc, proofread
    - front: Le Correcteur typographe (Brossard)
    - volume: Volume (`Le Correcteur typographe (Brossard)/volume 1`) [container]
      - chapter: Avant-propos (`Le Correcteur typographe (Brossard)/volume 1/Avant-propos`) [container]
      - volume: 01 (`Le Correcteur typographe (Brossard)/volume 1/01`) [container]
        - chapter: 01 (`Le Correcteur typographe (Brossard)/volume 1/01/01`) [container]
        - chapter: 02 (`Le Correcteur typographe (Brossard)/volume 1/01/02`) [container]
        - chapter: 03 (`Le Correcteur typographe (Brossard)/volume 1/01/03`) [container]
        - chapter: 04 (`Le Correcteur typographe (Brossard)/volume 1/01/04`) [container]
      - volume: 02 (`Le Correcteur typographe (Brossard)/volume 1/02`) [container]
        - chapter: 01 (`Le Correcteur typographe (Brossard)/volume 1/02/01`) [container]
        - chapter: 02 (`Le Correcteur typographe (Brossard)/volume 1/02/02`) [container]
        - chapter: 03 (`Le Correcteur typographe (Brossard)/volume 1/02/03`) [container]
    - … 166 more
1. `Anna Karénine (trad. Faguet)`: subpages, book, 248 parts, 0 missing, order single, proofread
    - toc: Anna Karénine (trad. Faguet)
    - volume: Table des matières (`Anna Karénine (trad. Faguet)/Table des matières`) [hierarchy]
      - volume: Première Partie (`Anna Karénine (trad. Faguet)/Partie I`) [container]
        - chapter: Chapitre 1 (`Anna Karénine (trad. Faguet)/Partie I/Chapitre 1`) [container]
        - chapter: Chapitre 2 (`Anna Karénine (trad. Faguet)/Partie I/Chapitre 2`) [container]
        - chapter: Chapitre 3 (`Anna Karénine (trad. Faguet)/Partie I/Chapitre 3`) [container]
        - chapter: Chapitre 4 (`Anna Karénine (trad. Faguet)/Partie I/Chapitre 4`) [container]
        - chapter: Chapitre 5 (`Anna Karénine (trad. Faguet)/Partie I/Chapitre 5`) [container]
        - chapter: Chapitre 6 (`Anna Karénine (trad. Faguet)/Partie I/Chapitre 6`) [container]
        - chapter: Chapitre 7 (`Anna Karénine (trad. Faguet)/Partie I/Chapitre 7`) [container]
        - chapter: Chapitre 8 (`Anna Karénine (trad. Faguet)/Partie I/Chapitre 8`) [container]
        - chapter: Chapitre 9 (`Anna Karénine (trad. Faguet)/Partie I/Chapitre 9`) [container]
    - … 237 more
1. `Patois de la commune de Vionnaz (Bas-Valais)`: subpages, book, 30 parts, 0 missing, order toc, proofread
    - toc: Patois de la commune de Vionnaz (Bas-Valais)
    - chapter: Introduction (`Patois de la commune de Vionnaz (Bas-Valais)/Introduction`) [container]
    - text: Transcription des sons (`Patois de la commune de Vionnaz (Bas-Valais)/Transcription des sons`) [container]
    - volume: Phonologie (`Patois de la commune de Vionnaz (Bas-Valais)/Phonologie`) [container]
      - chapter: 1 (`Patois de la commune de Vionnaz (Bas-Valais)/Phonologie/1`) [container]
      - chapter: 2 (`Patois de la commune de Vionnaz (Bas-Valais)/Phonologie/2`) [container]
      - volume: 3 (`Patois de la commune de Vionnaz (Bas-Valais)/Phonologie/3`) [container]
        - chapter: 1 (`Patois de la commune de Vionnaz (Bas-Valais)/Phonologie/3/1`) [container]
        - chapter: 2 (`Patois de la commune de Vionnaz (Bas-Valais)/Phonologie/3/2`) [container]
      - chapter: 4 (`Patois de la commune de Vionnaz (Bas-Valais)/Phonologie/4`) [container]
      - volume: 5 (`Patois de la commune de Vionnaz (Bas-Valais)/Phonologie/5`) [container]
        - chapter: 1 (`Patois de la commune de Vionnaz (Bas-Valais)/Phonologie/5/1`) [container]
    - … 19 more
1. `Poésie (Rilke, trad. Betz)`: subpages, collection, 158 parts, 0 missing, order toc, proofread
    - toc: Poésie (Rilke, trad. Betz)
    - chapter: Introduction (`Poésie (Rilke, trad. Betz)/Introduction`) [container]
    - text: Avertissement du traducteur (`Poésie (Rilke, trad. Betz)/Avertissement`) [container]
    - volume: PREMIÈRES POÉSIES (`Poésie (Rilke, trad. Betz)/Premières poésies`) [container]
      - volume: CHANTS DE L’AUBE (`Poésie (Rilke, trad. Betz)/Chants de l’aube`) [container]
        - text: Air populaire (`Poésie (Rilke, trad. Betz)/Premières poésies/Air populaire`) [container]
        - text: Cimetière (`Poésie (Rilke, trad. Betz)/Premières poésies/Cimetière`) [container]
        - text: L’Angelot (`Poésie (Rilke, trad. Betz)/Premières poésies/L’Angelot`) [container]
        - text: C’était le jour (`Poésie (Rilke, trad. Betz)/Premières poésies/C’était le jour`) [container]
        - text: Ciel (`Poésie (Rilke, trad. Betz)/Premières poésies/Ciel`) [container]
        - text: Le temps était gris (`Poésie (Rilke, trad. Betz)/Premières poésies/Le temps était gris`) [container]
        - text: Tournesols (`Poésie (Rilke, trad. Betz)/Premières poésies/Tournesols`) [container]
    - … 147 more
1. `Histoire de la chimie`: subpages, book, 13 parts, 10 missing, order natural, unproofread
    - toc: Histoire de la chimie
    - volume: Tome 1 (`Histoire de la chimie/Tome 1`) [hierarchy]
      - chapter: Table des matières détaillée (`Histoire de la chimie/Tome 1/Table des matières détaillée`) [container]
      - text: Dédicace (`Histoire de la chimie/Tome 1/Dédicace`) [container]
      - chapter: Préface (`Histoire de la chimie/Tome 1/Préface`) [container]
      - text: Coup d’œil général sur le progrès de la science (`Histoire de la chimie/Tome 1/Coup d’œil général sur le progrès de la science`) [container]
      - volume: . Depuis les premiers temps historiques jusqu’à Thalès. (620 ans avant J.-C.) (`Histoire de la chimie/Tome 1/Première époque/Première section`) [container]
        - text: Extrême Orient. Chinois et Japonais (`Histoire de la chimie/Tome 1/Première époque/Première section/Extrême Orient. Chinois et Japonais`) [container]
        - text: Indiens (Aryas) (`Histoire de la chimie/Tome 1/Première époque/Première section/Indiens (Aryas)`) [container]
        - volume: Égyptiens, Phéniciens, Hébreux (`Histoire de la chimie/Tome 1/Première époque/Première section/Égyptiens, Phéniciens, Hébreux`) [container]
          - text: Paragraphe 1 (`Histoire de la chimie/Tome 1/Première époque/Première section/Égyptiens, Phéniciens, Hébreux/Paragraphe 1`) [container]
          - text: Paragraphe 2 (`Histoire de la chimie/Tome 1/Première époque/Première section/Égyptiens, Phéniciens, Hébreux/Paragraphe 2`) [container]
    - … 2 more
1. `Œuvres de Florian`: subpages, collection, 32 parts, 103 missing, order toc, unproofread
    - toc: Œuvres de Florian
    - volume: FABLES PAR ORDRE ALPHABÉTIQUE (`Œuvres de Florian/Fables`) [container]
      - volume: 1 (`Œuvres de Florian/Fables/1`) [container]
        - chapter: La Fable et la Vérité (`Œuvres de Florian/Fables/1/La Fable et la Vérité`) [container]
        - text: Le Bœuf, le Cheval et l’Âne (`Œuvres de Florian/Fables/1/Le Bœuf, le Cheval et l’Âne`) [container]
        - text: Le Chat et le Miroir (`Œuvres de Florian/Fables/1/Le Chat et le Miroir`) [container]
        - text: Le Calife (`Œuvres de Florian/Fables/1/Le Calife`) [container]
        - text: La Mort (`Œuvres de Florian/Fables/1/La Mort`) [container]
        - text: Le Rossignol et le Prince (`Œuvres de Florian/Fables/1/Le Rossignol et le Prince`) [container]
      - volume: 2 (`Œuvres de Florian/Fables/2`) [container]
        - text: Le Grillon (`Œuvres de Florian/Fables/2/Le Grillon`) [container]
        - text: La Pie et la Colombe (`Œuvres de Florian/Fables/2/La Pie et la Colombe`) [container]
    - … 21 more
1. `Voyage en Orient (Nerval)`: mixed, book, 205 parts, 0 missing, order natural, validated
    - toc: Voyage en Orient (Nerval)
    - volume: APPENDICE (`Voyage en Orient (Nerval)/Appendice`) [container]
      - chapter: I (`Voyage en Orient (Nerval)/Appendice/I`) [container]
      - chapter: II (`Voyage en Orient (Nerval)/Appendice/II`) [container]
      - chapter: III (`Voyage en Orient (Nerval)/Appendice/III`) [container]
      - chapter: IV (`Voyage en Orient (Nerval)/Appendice/IV`) [container]
      - chapter: V (`Voyage en Orient (Nerval)/Appendice/V`) [container]
      - chapter: VI (`Voyage en Orient (Nerval)/Appendice/VI`) [container]
      - chapter: VII (`Voyage en Orient (Nerval)/Appendice/VII`) [container]
      - chapter: VIII (`Voyage en Orient (Nerval)/Appendice/VIII`) [container]
      - chapter: IX (`Voyage en Orient (Nerval)/Appendice/IX`) [container]
      - chapter: X (`Voyage en Orient (Nerval)/Appendice/X`) [container]
    - … 194 more
1. `Les Métamorphoses (Apulée)`: virtual, book, 46 parts, 0 missing, order single, proofread
    - volume: Traduction Bastien, 1787 (`Les Métamorphoses (Apulée)/Traduction Bastien, 1787`)
      - volume: I (`Les Métamorphoses (Apulée)/Traduction Bastien, 1787/I`) [hierarchy]
        - text: Avis sur cette nouvelle édition (`Les Métamorphoses (Apulée)/Traduction Bastien, 1787/I/Avis`) [container]
        - text: Extrait du dictionnaire historique, par une société de gens de lettres (`Les Métamorphoses (Apulée)/Traduction Bastien, 1787/I/Extrait du dictionnaire historique`) [container]
        - text: Extrait du dictionnaire de Moréry (`Les Métamorphoses (Apulée)/Traduction Bastien, 1787/I/Extrait du dictionnaire de Moréry`) [container]
        - text: Extrait du dictionnaire historique et critique de Bayle (`Les Métamorphoses (Apulée)/Traduction Bastien, 1787/I/Extrait du dictionnaire historique et critique de Bayle`) [container]
        - text: Vie d’Apulée (`Les Métamorphoses (Apulée)/Traduction Bastien, 1787/I/Vie d’Apulée`) [container]
        - text: Sommaires (`Les Métamorphoses (Apulée)/Traduction Bastien, 1787/I/Sommaires`) [container]
        - chapter: Livre I (`Les Métamorphoses (Apulée)/Traduction Bastien, 1787/I/Livre I`) [container]
        - text: Remarques sur le Livre I (`Les Métamorphoses (Apulée)/Traduction Bastien, 1787/I/Remarques sur le Livre I`) [container]
        - chapter: Livre II (`Les Métamorphoses (Apulée)/Traduction Bastien, 1787/I/Livre II`) [container]
        - text: Remarques sur le Livre II (`Les Métamorphoses (Apulée)/Traduction Bastien, 1787/I/Remarques sur le Livre II`) [container]
    - … 34 more
1. `Œuvres complètes de La Fontaine (Marty-Laveaux)`: mixed, collection, 62 parts, 9 missing, order natural, proofread
    - front: Œuvres complètes de La Fontaine (Marty-Laveaux)
    - volume: Tome 1 (`Œuvres complètes de La Fontaine (Marty-Laveaux)/Tome 1`) [hierarchy]
      - text: Avertissement (`Œuvres complètes de La Fontaine (Marty-Laveaux)/Tome 1/Avertissement`) [container]
      - text: À Monseigneur le Dauphin (`Œuvres complètes de La Fontaine (Marty-Laveaux)/Tome 1/À Monseigneur le Dauphin`) [container]
      - text: Preface (`Œuvres complètes de La Fontaine (Marty-Laveaux)/Tome 1/Preface`) [container]
      - text: La vie d’Esope le Phrygien (`Œuvres complètes de La Fontaine (Marty-Laveaux)/Tome 1/La vie d’Esope le Phrygien`) [container]
      - text: A Monseigneur le Dauphin (`Œuvres complètes de La Fontaine (Marty-Laveaux)/Tome 1/A Monseigneur le Dauphin`) [container]
      - chapter: Livre premier (`Œuvres complètes de la Fontaine (Marty-Laveaux)/Tome 1/Livre premier`) [container]
      - chapter: Livre deuxiéme (`Œuvres complètes de la Fontaine (Marty-Laveaux)/Tome 1/Livre deuxiéme`) [container]
      - chapter: Livre troisiéme (`Œuvres complètes de la Fontaine (Marty-Laveaux)/Tome 1/Livre troisiéme`) [container]
      - chapter: Livre quatriéme (`Œuvres complètes de la Fontaine (Marty-Laveaux)/Tome 1/Livre quatriéme`) [container]
      - chapter: Livre cinquiéme (`Œuvres complètes de la Fontaine (Marty-Laveaux)/Tome 1/Livre cinquiéme`) [container]
    - … 51 more
1. `La Bonne aventure (Sue)/1`: mixed, book, 65 parts, 0 missing, order single, proofread
    - toc: La Bonne aventure (Sue)/1
    - volume: La Bonne aventure (Sue) [container]
      - chapter: I (`La Bonne aventure (Sue)/1/I`) [container]
      - chapter: II (`La Bonne aventure (Sue)/1/II`) [container]
      - chapter: III (`La Bonne aventure (Sue)/1/III`) [container]
      - chapter: IV (`La Bonne aventure (Sue)/1/IV`) [container]
      - chapter: IX (`La Bonne aventure (Sue)/1/IX`) [container]
      - chapter: V (`La Bonne aventure (Sue)/1/V`) [container]
      - chapter: VI (`La Bonne aventure (Sue)/1/VI`) [container]
      - chapter: VII (`La Bonne aventure (Sue)/1/VII`) [container]
      - chapter: VIII (`La Bonne aventure (Sue)/1/VIII`) [container]
      - chapter: X (`La Bonne aventure (Sue)/1/X`) [container]
    - … 54 more
1. `Le Livre des Esprits`: subpages, book, 38 parts, 0 missing, order toc, proofread
    - toc: Le Livre des Esprits
    - chapter: Conclusion (`Le Livre des Esprits/Conclusion`) [container]
    - volume: Table des matières (`Le Livre des Esprits/Table des matières`) [container]
      - text: Avis sur cette nouvelle édition (`Le Livre des Esprits/Avis sur cette nouvelle édition`) [container]
      - chapter: Introduction (`Le Livre des Esprits/Introduction`) [container]
      - text: Prolégomènes (`Le Livre des Esprits/Prolégomènes`) [container]
      - volume: LIVRE PREMIER. (`Le Livre des Esprits/Livre premier`) [container]
        - chapter: Chapitre I (`Le Livre des Esprits/Livre premier/Chapitre I`) [container]
        - chapter: Chapitre II (`Le Livre des Esprits/Livre premier/Chapitre II`) [container]
        - chapter: Chapitre III (`Le Livre des Esprits/Livre premier/Chapitre III`) [container]
        - chapter: Chapitre IV (`Le Livre des Esprits/Livre premier/Chapitre IV`) [container]
      - volume: LIVRE DEUXIÈME. (`Le Livre des Esprits/Livre deuxième`) [container]
    - … 27 more
1. `Les Propos d’Alain (1920)`: subpages, book, 359 parts, 0 missing, order toc, proofread
    - toc: Les Propos d’Alain (1920)
    - volume: Tome 1 (`Les Propos d’Alain (1920)/Tome 1`) [hierarchy]
      - chapter: I. (`Les Propos d’Alain (1920)/Tome 1/001`) [container]
      - chapter: II (`Les Propos d’Alain (1920)/Tome 1/002`) [container]
      - chapter: III. (`Les Propos d’Alain (1920)/Tome 1/003`) [container]
      - chapter: IV. (`Les Propos d’Alain (1920)/Tome 1/004`) [container]
      - chapter: V. (`Les Propos d’Alain (1920)/Tome 1/005`) [container]
      - chapter: VI. (`Les Propos d’Alain (1920)/Tome 1/006`) [container]
      - chapter: VII. (`Les Propos d’Alain (1920)/Tome 1/007`) [container]
      - chapter: VIII. (`Les Propos d’Alain (1920)/Tome 1/008`) [container]
      - chapter: IX. (`Les Propos d’Alain (1920)/Tome 1/009`) [container]
      - chapter: X. (`Les Propos d’Alain (1920)/Tome 1/010`) [container]
    - … 348 more
1. `Œuvres de Théophile Gautier (Lemerre, 1890)`: linked, collection, 316 parts, 0 missing, order natural, unproofread
    - toc: Œuvres de Théophile Gautier (Lemerre, 1890)
    - text: Albertus [container]
    - text: Allitérations [container]
    - text: Ambition (`Ambition (Gautier)`) [container]
    - text: Après la bataille (`Après la Bataille (Gautier)`) [container]
    - text: Après le Bal [container]
    - text: Au bois de Boulogne (`Au Bois de Boulogne`) [container]
    - text: Aux Mânes de l’Empereur (`Aux mânes de l’Empereur`) [container]
    - text: Ave Maria (`Ave Maria (Gautier)`) [container]
    - text: Baiser rose, Baiser bleu [container]
    - text: Bonbons et Pommes vertes [container]
    - text: Cariatides [container]
    - … 305 more
1. `Lexique étymologique du breton moderne`: subpages, reference, 38 parts, 0 missing, order toc, proofread
    - toc: Lexique étymologique du breton moderne
    - chapter: Introduction (`Lexique étymologique du breton moderne/Introduction`) [hierarchy]
    - text: Instruction pour l’usage du lexique (`Lexique étymologique du breton moderne/Instruction pour l’usage du lexique`) [hierarchy]
    - text: A (`Lexique étymologique du breton moderne/Lettre A`) [hierarchy]
    - text: B (`Lexique étymologique du breton moderne/Lettre B`) [hierarchy]
    - text: K (`Lexique étymologique du breton moderne/Lettre K`) [hierarchy]
    - chapter: D (`Lexique étymologique du breton moderne/Lettre D`) [hierarchy]
    - text: E (`Lexique étymologique du breton moderne/Lettre E`) [hierarchy]
    - text: F (`Lexique étymologique du breton moderne/Lettre F`) [hierarchy]
    - text: G (`Lexique étymologique du breton moderne/Lettre G`) [hierarchy]
    - text: H (`Lexique étymologique du breton moderne/Lettre H`) [hierarchy]
    - text: CH (`Lexique étymologique du breton moderne/Lettre CH`) [hierarchy]
    - … 27 more
1. `George Sand, sa vie et ses œuvres`: subpages, book, 46 parts, 3 missing, order natural, unproofread
    - toc: George Sand, sa vie et ses œuvres
    - volume: volume 1. (`George Sand, sa vie et ses œuvres/1`) [hierarchy]
      - chapter: 1 (`George Sand, sa vie et ses œuvres/1/1`) [container]
      - chapter: 2 (`George Sand, sa vie et ses œuvres/1/2`) [container]
      - chapter: 3 (`George Sand, sa vie et ses œuvres/1/3`) [container]
      - chapter: 4 (`George Sand, sa vie et ses œuvres/1/4`) [container]
      - chapter: 5 (`George Sand, sa vie et ses œuvres/1/5`) [container]
      - chapter: 6 (`George Sand, sa vie et ses œuvres/1/6`) [container]
      - chapter: 7 (`George Sand, sa vie et ses œuvres/1/7`) [hierarchy]
      - chapter: Introduction (`George Sand, sa vie et ses œuvres/1/Introduction`) [container]
    - volume: volume 2. (`George Sand, sa vie et ses œuvres/2`) [hierarchy]
      - chapter: 8 (`George Sand, sa vie et ses œuvres/2/8`) [hierarchy]
    - … 35 more
1. `Lettres de Madame de Sévigné`: virtual, book, 916 parts, 0 missing, order natural, proofread
    - volume: Édition Capmas 1876 (`Lettres de Madame de Sévigné/Édition Capmas 1876`)
      - chapter: 1. Lettre du 20 février 1671 (`Lettres de Madame de Sévigné/Édition Capmas 1876/1`) [hierarchy]
    - volume: Édition Monmerqué (`Lettres de Madame de Sévigné/Édition Monmerqué`) [hierarchy]
      - chapter: Lettre 1, sans date (Sévigné) [container]
      - chapter: Lettre 2, sans date (Sévigné) [container]
      - chapter: Lettre 3, sans date (Sévigné) [container]
      - chapter: Lettre 4, 1646 (Sévigné) [container]
      - chapter: Lettre 5, 1646 (Sévigné) [container]
      - chapter: Lettre 7, 1648 (Sévigné) [container]
      - chapter: Lettre 8, 1648 (Sévigné) [container]
      - chapter: Lettre 9, 1648 (Sévigné) [container]
      - chapter: Lettre 10, 1649 (Sévigné) [container]
    - … 904 more
1. `La Femme et le Féminisme avant la Révolution`: subpages, book, 21 parts, 0 missing, order toc, unproofread
    - front: La Femme et le Féminisme avant la Révolution
    - text: Bibliographie (`La Femme et le Féminisme avant la Révolution/Bibliographie`) [container]
    - volume: Table des chapitres (`La Femme et le Féminisme avant la Révolution/Table des chapitres`) [container]
      - chapter: Introduction (`La Femme et le Féminisme avant la Révolution/Introduction`) [container]
      - volume: PREMIÈRE PARTIE (`La Femme et le Féminisme avant la Révolution/I`) [container]
        - chapter: 1 (`La Femme et le Féminisme avant la Révolution/I/1`) [container]
        - chapter: 2 (`La Femme et le Féminisme avant la Révolution/I/2`) [container]
        - chapter: 3 (`La Femme et le Féminisme avant la Révolution/I/3`) [container]
        - chapter: 4 (`La Femme et le Féminisme avant la Révolution/I/4`) [container]
        - chapter: 5 (`La Femme et le Féminisme avant la Révolution/I/5`) [container]
        - chapter: 6 (`La Femme et le Féminisme avant la Révolution/I/6`) [container]
        - chapter: 7 (`La Femme et le Féminisme avant la Révolution/I/7`) [container]
    - … 10 more
1. `Les Œuvres de François Rabelais (Éditions Marty-Laveaux)`: subpages, book, 392 parts, 0 missing, order natural, unproofread
    - front: Les Œuvres de François Rabelais (Éditions Marty-Laveaux)
    - text: Avertissement2 (`Les Œuvres de François Rabelais (Éditions Marty-Laveaux)/Avertissement2`) [container]
    - text: Avertissement3 (`Les Œuvres de François Rabelais (Éditions Marty-Laveaux)/Avertissement3`) [container]
    - text: Bibliographie (`Les Œuvres de François Rabelais (Éditions Marty-Laveaux)/Bibliographie`) [container]
    - text: Biographie (`Les Œuvres de François Rabelais (Éditions Marty-Laveaux)/Biographie`) [container]
    - volume: EpistreDuLimousin (`Les Œuvres de François Rabelais (Éditions Marty-Laveaux)/EpistreDuLimousin`) [container]
      - text: Commentaire (`Les Œuvres de François Rabelais (Éditions Marty-Laveaux)/EpistreDuLimousin/Commentaire`) [hierarchy]
    - volume: Gargantua (`Les Œuvres de François Rabelais (Éditions Marty-Laveaux)/Gargantua`) [hierarchy]
      - chapter: Chapitre XII (`Les Œuvres de François Rabelais (Éditions Marty-Laveaux)/Gargantua/12`) [container]
      - chapter: Chapitre XVII (`Les Œuvres de François Rabelais (Éditions Marty-Laveaux)/Gargantua/17`) [container]
      - chapter: Chapitre XVIII (`Les Œuvres de François Rabelais (Éditions Marty-Laveaux)/Gargantua/18`) [container]
      - chapter: Chapitre XIX (`Les Œuvres de François Rabelais (Éditions Marty-Laveaux)/Gargantua/19`) [container]
    - … 381 more
1. `France, Algérie et colonies`: subpages, book, 69 parts, 0 missing, order scan, proofread
    - toc: France, Algérie et colonies
    - volume: France (`France, Algérie et colonies/France`) [container]
      - text: Étendue, nom, frontières (`France, Algérie et colonies/France/01`) [container]
      - volume: Monts, Plateaux, Régions naturelles (`France, Algérie et colonies/France/02`) [container]
        - chapter: 01 (`France, Algérie et colonies/France/02/01`) [container]
        - chapter: 02 (`France, Algérie et colonies/France/02/02`) [container]
        - chapter: 03 (`France, Algérie et colonies/France/02/03`) [container]
        - chapter: 04 (`France, Algérie et colonies/France/02/04`) [container]
        - chapter: 05 (`France, Algérie et colonies/France/02/05`) [container]
        - chapter: 06 (`France, Algérie et colonies/France/02/06`) [container]
      - volume: Fleuves, rivières et torrents. Rivages, îles de la mer (`France, Algérie et colonies/France/03`) [container]
        - chapter: 01 (`France, Algérie et colonies/France/03/01`) [container]
    - … 58 more
1. `Les Gais Lurons (recueil)`: subpages, collection, 22 parts, 0 missing, order toc, validated
    - toc: Les Gais Lurons (recueil)
    - text: Markheim (`Les Gais Lurons (recueil)/Markheim`) [container]
    - text: Janet la Revenante (`Les Gais Lurons (recueil)/Janet la Revenante`) [container]
    - text: Olalla (`Les Gais Lurons (recueil)/Olalla`) [container]
    - volume: Le Trésor de Franchard (`Les Gais Lurons (recueil)/Le Trésor de Franchard`) [container]
      - volume: Les Gais Lurons (`Les Gais Lurons (recueil)/Les Gais Lurons`) [container]
        - text: Eilean Aros (`Les Gais Lurons (recueil)/Les Gais Lurons/1`) [container]
        - text: Ce que le naufrage avait apporté en Aros (`Les Gais Lurons (recueil)/Les Gais Lurons/2`) [container]
        - text: Le rivage et les eaux de Sandag-Bay (`Les Gais Lurons (recueil)/Les Gais Lurons/3`) [container]
        - text: La Tempête (`Les Gais Lurons (recueil)/Les Gais Lurons/4`) [container]
        - text: L’Homme sorti de la mer (`Les Gais Lurons (recueil)/Les Gais Lurons/5`) [container]
      - volume: Will du Moulin (`Les Gais Lurons (recueil)/Will du Moulin`) [container]
    - … 11 more
1. `Romans et Contes de Théophile Gautier`: subpages, collection, 36 parts, 0 missing, order single, validated
    - toc: Romans et Contes de Théophile Gautier
    - volume: Jettatura (`Romans et Contes de Théophile Gautier/Jettatura`) [container]
      - volume: Avatar (`Romans et Contes de Théophile Gautier/Avatar`) [container]
        - chapter: 1 (`Romans et Contes de Théophile Gautier/Avatar/1`) [container]
        - chapter: 2 (`Romans et Contes de Théophile Gautier/Avatar/2`) [container]
        - chapter: 3 (`Romans et Contes de Théophile Gautier/Avatar/3`) [container]
        - chapter: 4 (`Romans et Contes de Théophile Gautier/Avatar/4`) [container]
        - chapter: 5 (`Romans et Contes de Théophile Gautier/Avatar/5`) [container]
        - chapter: 6 (`Romans et Contes de Théophile Gautier/Avatar/6`) [container]
        - chapter: 7 (`Romans et Contes de Théophile Gautier/Avatar/7`) [container]
        - chapter: 8 (`Romans et Contes de Théophile Gautier/Avatar/8`) [container]
        - chapter: 9 (`Romans et Contes de Théophile Gautier/Avatar/9`) [container]
    - … 25 more
1. `Chairs profanes`: subpages, collection, 27 parts, 0 missing, order toc, validated
    - toc: Chairs profanes
    - text: Sonnet liminaire (`Chairs profanes/Sonnet liminaire`) [container]
    - volume: Idylles (`Chairs profanes/Idylles`) [container]
      - volume: Sonnets guerriers (`Chairs profanes/Sonnets guerriers`) [container]
        - chapter: I (`Chairs profanes/Idylles/I`) [container]
        - chapter: II (`Chairs profanes/Idylles/II`) [container]
        - chapter: III (`Chairs profanes/Idylles/III`) [container]
        - chapter: IV (`Chairs profanes/Idylles/IV`) [container]
        - chapter: V (`Chairs profanes/Idylles/V`) [container]
        - chapter: I. Garde républicain (`Chairs profanes/Sonnets guerriers/Garde républicain`) [container]
        - chapter: II. Chasseur (`Chairs profanes/Sonnets guerriers/Chasseur`) [container]
        - chapter: III. Dragon (`Chairs profanes/Sonnets guerriers/Dragon`) [container]
    - … 16 more
1. `Recherches statistiques sur l’aliénation mentale faites à l’hospice de Bicêtre`: subpages, book, 52 parts, 0 missing, order toc, proofread
    - toc: Recherches statistiques sur l’aliénation mentale faites à l’hospice de Bicêtre
    - chapter: PRÉFACE (`Recherches statistiques sur l’aliénation mentale faites à l’hospice de Bicêtre/PRÉFACE`) [container]
    - text: COUP D’ŒIL HISTORIQUE (`Recherches statistiques sur l’aliénation mentale faites à l’hospice de Bicêtre/COUP D’ŒIL HISTORIQUE`) [container]
    - chapter: CHAPITRE I. — A.Admissions. (`Recherches statistiques sur l’aliénation mentale faites à l’hospice de Bicêtre/I/I/A`) [container]
    - text: B.Aliénés évacués des hôpitaux. (`Recherches statistiques sur l’aliénation mentale faites à l’hospice de Bicêtre/I/I/B`) [container]
    - text: C.Rechutes. (`Recherches statistiques sur l’aliénation mentale faites à l’hospice de Bicêtre/I/I/C`) [container]
    - text: D.Réintégrations. (`Recherches statistiques sur l’aliénation mentale faites à l’hospice de Bicêtre/I/I/D`) [container]
    - chapter: CHAPITRE II. — Causes. (`Recherches statistiques sur l’aliénation mentale faites à l’hospice de Bicêtre/I/II/Causes`) [container]
    - volume: . — Causes prédisposantes. (`Recherches statistiques sur l’aliénation mentale faites à l’hospice de Bicêtre/I/II/Sec 1`) [container]
      - text: Art 1 (`Recherches statistiques sur l’aliénation mentale faites à l’hospice de Bicêtre/I/II/Sec 1/Art 1`) [container]
      - text: Art 2 (`Recherches statistiques sur l’aliénation mentale faites à l’hospice de Bicêtre/I/II/Sec 1/Art 2`) [container]
      - text: Art 3 (`Recherches statistiques sur l’aliénation mentale faites à l’hospice de Bicêtre/I/II/Sec 1/Art 3`) [container]
    - … 41 more
1. `Les Travailleurs de la mer`: subpages, book, 140 parts, 0 missing, order toc, validated
    - toc: Les Travailleurs de la mer
    - text: Dédicace (`Les Travailleurs de la mer/Dédicace`) [container]
    - chapter: Préface (`Les Travailleurs de la mer/Préface`) [container]
    - volume: L’ARCHIPEL DE LA MANCHE (`Les Travailleurs de la mer/L’archipel de la Manche`) [container]
      - chapter: 01 (`Les Travailleurs de la mer/L’archipel de la Manche/01`) [hierarchy]
      - chapter: 02 (`Les Travailleurs de la mer/L’archipel de la Manche/02`) [hierarchy]
      - chapter: 03 (`Les Travailleurs de la mer/L’archipel de la Manche/03`) [hierarchy]
      - chapter: 04 (`Les Travailleurs de la mer/L’archipel de la Manche/04`) [hierarchy]
      - chapter: 05 (`Les Travailleurs de la mer/L’archipel de la Manche/05`) [container]
      - chapter: 06 (`Les Travailleurs de la mer/L’archipel de la Manche/06`) [container]
      - chapter: 07 (`Les Travailleurs de la mer/L’archipel de la Manche/07`) [container]
      - chapter: 08 (`Les Travailleurs de la mer/L’archipel de la Manche/08`) [container]
    - … 129 more
1. `Petit cours d’histoire de Belgique`: subpages, book, 43 parts, 0 missing, order toc, proofread
    - toc: Petit cours d’histoire de Belgique
    - chapter: Préface (`Petit cours d’histoire de Belgique/Préface`) [container]
    - text: Domination romaine. (`Petit cours d’histoire de Belgique/p01`) [container]
    - volume: Domination franque. (`Petit cours d’histoire de Belgique/p02`) [container]
      - text: Les Mérovingiens. (`Petit cours d’histoire de Belgique/p02/ch1`) [container]
      - text: Les Maires du Palais. (`Petit cours d’histoire de Belgique/p02/ch2`) [container]
      - text: Charlemagne. (`Petit cours d’histoire de Belgique/p02/ch3`) [container]
      - text: L’empire après Charlemagne. (`Petit cours d’histoire de Belgique/p02/ch4`) [container]
    - volume: La Féodalité. (`Petit cours d’histoire de Belgique/p03`) [container]
      - text: ch1 (`Petit cours d’histoire de Belgique/p03/ch1`) [container]
      - text: ch2 (`Petit cours d’histoire de Belgique/p03/ch2`) [container]
    - volume: Période communale. (`Petit cours d’histoire de Belgique/p04`) [container]
    - … 32 more
1. `Œuvres complètes de H. de Balzac`: mixed, collection, 276 parts, 0 missing, order natural, unproofread
    - toc: Œuvres complètes de H. de Balzac
    - chapter: Dernière partie (`Béatrix/Dernière partie`) [container]
    - chapter: (première partie) (`Béatrix/Première partie`) [container]
    - text: Facino Cane [container]
    - text: Ferragus, chef des Dévorants (`Ferragus`) [container]
    - text: Gaudissart II [container]
    - text: Histoire de la grandeur et de la décadence de César Birotteau [container]
    - chapter: Chapitre I. César à son apogée (`Histoire de la grandeur et de la décadence de César Birotteau/Chapitre I. César à son apogée`) [container]
    - chapter: Chapitre II. César aux prises avec le malheur (`Histoire de la grandeur et de la décadence de César Birotteau/Chapitre II. César aux prises avec le malheur`) [container]
    - chapter: Préface à l’Histoire des Treize (`Histoire des Treize/Préface`) [container]
    - text: La Bourse (Balzac) [container]
    - text: La Cousine Bette (ed. Houssiaux) [container]
    - … 265 more
1. `La France juive`: subpages, book, 32 parts, 0 missing, order natural, proofread
    - toc: La France juive
    - text: INDEX DES NOMS CITÉS (`La France juive/Index`) [container]
    - chapter: Introduction (`La France juive/Introduction`) [container]
    - volume: LIVRE CINQUIÈME. Paris juif et la société française (`La France juive/Livre Cinquième`) [container]
      - chapter: 1 (`La France juive/Livre Cinquième/1`) [container]
      - chapter: 2 (`La France juive/Livre Cinquième/2`) [container]
      - chapter: 3 (`La France juive/Livre Cinquième/3`) [container]
      - chapter: 4 (`La France juive/Livre Cinquième/4`) [container]
    - volume: LIVRE DEUXIÈME. Le Juif dans l’histoire de France (`La France juive/Livre Deuxième`) [container]
      - chapter: I (`La France juive/Livre Deuxième/I`) [container]
      - chapter: II (`La France juive/Livre Deuxième/II`) [container]
      - chapter: III (`La France juive/Livre Deuxième/III`) [container]
    - … 21 more
1. `Curiositez françoises`: virtual, reference, 66 parts, 52 missing, order natural, proofread
    - volume: 1re éd., 1640 (`Curiositez françoises/1re éd., 1640`)
      - text: A (`Curiositez françoises/1re éd., 1640/A`) [container]
      - text: Aage (`Curiositez françoises/1re éd., 1640/Aage`) [container]
      - text: Ab hoc (`Curiositez françoises/1re éd., 1640/Ab hoc`) [container]
      - text: Abattre (`Curiositez françoises/1re éd., 1640/Abattre`) [container]
      - text: Abbaye (`Curiositez françoises/1re éd., 1640/Abbaye`) [container]
      - text: Abbayer (`Curiositez françoises/1re éd., 1640/Abbayer`) [container]
      - text: Abboutissans (`Curiositez françoises/1re éd., 1640/Abboutissans`) [container]
      - text: Abbreve (`Curiositez françoises/1re éd., 1640/Abbreve`) [container]
      - text: Abbé (`Curiositez françoises/1re éd., 1640/Abbé`) [container]
      - text: Ablativo (`Curiositez françoises/1re éd., 1640/Ablativo`) [container]
      - text: Abry (`Curiositez françoises/1re éd., 1640/Abry`) [container]
    - … 54 more
1. `La Révolution surréaliste`: virtual, periodical-issue, 3 parts, 0 missing, order single, unproofread
    - volume: Table des numéros (`La Révolution surréaliste/Table des numéros`)
      - volume: numéro 9-10 (`La Révolution surréaliste/Table des numéros/numéro 9-10`) [hierarchy]
        - text: Revue de la Presse (`La Révolution surréaliste/Table des numéros/numéro 9-10/Revue de la Presse`) [hierarchy]
1. `Œuvres de Saint Bernard`: mixed, book, 43 parts, 66 missing, order natural, unproofread
    - front: Œuvres de Saint Bernard
    - text: Du combat spirituel (ou encore de la lutte des vertus et des vices) [container]
    - chapter: Lettre CCCXXXVIII (Saint Bernard) [container]
    - text: Sermon V de saint Bernard pour la nativité du Seigneur [container]
    - text: Sermon XLVII de saint Bernard sur le Cantique des Cantiques [container]
    - text: Bref apostolique. (`Œuvres de Saint Bernard/Bref apostolique`) [container]
    - volume: Histoire de saint Bernard et de son siècle (`Œuvres de Saint Bernard/Histoire de saint Bernard et de son siècle`) [hierarchy]
      - chapter: Introduction. (`Œuvres de Saint Bernard/Histoire de saint Bernard et de son siècle/0`) [container]
      - volume: 1 (`Œuvres de Saint Bernard/Histoire de saint Bernard et de son siècle/1`) [container]
        - chapter: 01 (`Œuvres de Saint Bernard/Histoire de saint Bernard et de son siècle/1/01`) [container]
        - chapter: 02 (`Œuvres de Saint Bernard/Histoire de saint Bernard et de son siècle/1/02`) [container]
        - chapter: 03 (`Œuvres de Saint Bernard/Histoire de saint Bernard et de son siècle/1/03`) [container]
    - … 32 more

### I: works with other editions (562)

1. `La Commune de Paris (Kropotkine, 1937)`: single, book, 1 parts, 0 missing, order –, validated
    - text: La Commune de Paris (Kropotkine, 1937)
1. `Recueil de contes populaires slaves (traduction Léger)`: subpages, book, 34 parts, 0 missing, order scan, validated
    - toc: Recueil de contes populaires slaves (traduction Léger)
    - chapter: Introduction (`Recueil de contes populaires slaves (traduction Léger)/Introduction`) [container]
    - chapter: I (`Recueil de contes populaires slaves (traduction Léger)/I`) [container]
    - chapter: II (`Recueil de contes populaires slaves (traduction Léger)/II`) [container]
    - chapter: III (`Recueil de contes populaires slaves (traduction Léger)/III`) [container]
    - chapter: IV (`Recueil de contes populaires slaves (traduction Léger)/IV`) [container]
    - chapter: V (`Recueil de contes populaires slaves (traduction Léger)/V`) [container]
    - chapter: VI (`Recueil de contes populaires slaves (traduction Léger)/VI`) [container]
    - chapter: VII (`Recueil de contes populaires slaves (traduction Léger)/VII`) [container]
    - chapter: VIII (`Recueil de contes populaires slaves (traduction Léger)/VIII`) [container]
    - chapter: IX (`Recueil de contes populaires slaves (traduction Léger)/IX`) [container]
    - chapter: X (`Recueil de contes populaires slaves (traduction Léger)/X`) [container]
    - … 23 more
1. `Les contes choisis (Aulnoy)`: subpages, collection, 10 parts, 0 missing, order scan, proofread
    - toc: Les contes choisis (Aulnoy)
    - text: Notice sur madame D’Aulnoy (`Les contes choisis (Aulnoy)/Notice sur madame D’Aulnoy`) [container]
    - text: La Belle aux Cheveux d’Or (`Les contes choisis (Aulnoy)/La Belle aux Cheveux d’Or`) [container]
    - text: Rameau d’Or (`Les contes choisis (Aulnoy)/Le Rameau d'or`) [container]
    - text: Le Rameau d’or (`Les contes choisis (Aulnoy)/Le Rameau d’or`) [container]
    - text: La Bonne Petite Souris (`Les contes choisis (Aulnoy)/La Bonne Petite Souris`) [container]
    - text: Le Mouton (`Les contes choisis (Aulnoy)/Le Mouton`) [container]
    - text: Finette Cendron (`Les contes choisis (Aulnoy)/Finette Cendron`) [container]
    - text: La Princesse Rosette (`Les contes choisis (Aulnoy)/La Princesse Rosette`) [container]
    - text: L’Oiseau Bleu (`Les contes choisis (Aulnoy)/L’Oiseau Bleu`) [container]
    - text: La Chatte Blanche (`Les contes choisis (Aulnoy)/La Chatte Blanche`) [container]
1. `Contes inédits (Poe)`: subpages, collection, 21 parts, 0 missing, order toc, validated
    - toc: Contes inédits (Poe)
    - chapter: Avant-propos (`Contes inédits (Poe)/Avant-propos`) [container]
    - text: Le rendez-vous (`Contes inédits (Poe)/Le Rendez-vous`) [container]
    - text: Éléonore (`Contes inédits (Poe)/Éléonore`) [container]
    - text: La caisse oblongue (`Contes inédits (Poe)/La Caisse oblongue`) [container]
    - text: Le cadavre accusateur (`Contes inédits (Poe)/Le Cadavre accusateur`) [container]
    - text: Le système du docteur Goudron et du professeur Plume (`Contes inédits (Poe)/Le Système du docteur Goudron et du professeur Plume`) [container]
    - text: Un homme usé (`Contes inédits (Poe)/Un homme usé`) [container]
    - text: La semaine des trois dimanches (`Contes inédits (Poe)/La Semaine des trois dimanches`) [container]
    - text: Le sphinx (`Contes inédits (Poe)/Le Sphinx`) [container]
    - text: Les lunettes (`Contes inédits (Poe)/Les Lunettes`) [container]
    - text: Les débuts littéraires de Thingum Bob (`Contes inédits (Poe)/Les Débuts littéraires de Thingum Bob`) [container]
    - … 10 more
1. `Jules César (Shakespeare, trad. Hugo)`: single, collection, 1 parts, 0 missing, order –, validated
    - text: Jules César (Shakespeare, trad. Hugo)
1. `Histoire grecque (Trad. Talbot)`: subpages, collection, 7 parts, 0 missing, order scan, proofread
    - front: Histoire grecque (Trad. Talbot)
    - chapter: LIVRE PREMIER. (`Histoire grecque (Trad. Talbot)/Livre 1`) [container]
    - chapter: Livre 2 (`Histoire grecque (Trad. Talbot)/Livre 2`) [container]
    - chapter: Livre 3 (`Histoire grecque (Trad. Talbot)/Livre 3`) [container]
    - chapter: Livre 4 (`Histoire grecque (Trad. Talbot)/Livre 4`) [container]
    - chapter: Livre 5 (`Histoire grecque (Trad. Talbot)/Livre 5`) [container]
    - chapter: Livre 6 (`Histoire grecque (Trad. Talbot)/Livre 6`) [container]
    - chapter: Livre 7 (`Histoire grecque (Trad. Talbot)/Livre 7`) [container]
1. `Recueil général et complet des fabliaux des 13e et 14e siècles`: subpages, collection, 178 parts, 0 missing, order natural, proofread
    - front: Recueil général et complet des fabliaux des 13e et 14e siècles
    - text: AvantPropos (`Recueil général et complet des fabliaux des 13e et 14e siècles/AvantPropos`) [container]
    - text: Des Deux Bordéors ribauz (`Recueil général et complet des fabliaux des 13e et 14e siècles/I`) [container]
    - text: Des Trois Boçus (`Recueil général et complet des fabliaux des 13e et 14e siècles/II`) [container]
    - text: Du Vair Palefroi (`Recueil général et complet des fabliaux des 13e et 14e siècles/III`) [container]
    - text: Des Trois Avugles de Compiengne (`Recueil général et complet des fabliaux des 13e et 14e siècles/IV`) [container]
    - text: Le Cuvier (`Recueil général et complet des fabliaux des 13e et 14e siècles/IX`) [container]
    - text: Le Vilain anier (`Recueil général et complet des fabliaux des 13e et 14e siècles/Le Vilain anier`) [hierarchy]
    - text: Le Vilain mire (`Recueil général et complet des fabliaux des 13e et 14e siècles/Le Vilain mire`) [hierarchy]
    - text: du premier volume (`Recueil général et complet des fabliaux des 13e et 14e siècles/NotesVariantesTome1`) [hierarchy]
    - chapter: Tome I (`Recueil général et complet des fabliaux des 13e et 14e siècles/Tome I`) [hierarchy]
    - text: TomeI (`Recueil général et complet des fabliaux des 13e et 14e siècles/TomeI`) [hierarchy]
    - … 167 more
1. `Variations sur le Carnaval de Venise (RDDM)`: single, periodical-issue, 1 parts, 0 missing, order –, proofread
    - text: Variations sur le Carnaval de Venise (RDDM)
1. `Mattea (Hetzel, illustré 1854)`: subpages, collection, 7 parts, 0 missing, order chain, proofread
    - toc: Mattea (Hetzel, illustré 1854)
    - chapter: I. (`Mattea (Hetzel, illustré 1854)/Chapitre 01`) [container]
    - chapter: II. (`Mattea (Hetzel, illustré 1854)/Chapitre 02`) [container]
    - chapter: III. (`Mattea (Hetzel, illustré 1854)/Chapitre 03`) [container]
    - chapter: IV. (`Mattea (Hetzel, illustré 1854)/Chapitre 04`) [container]
    - chapter: V. (`Mattea (Hetzel, illustré 1854)/Chapitre 05`) [container]
    - chapter: VI. (`Mattea (Hetzel, illustré 1854)/Chapitre 06`) [container]
    - chapter: VII. (`Mattea (Hetzel, illustré 1854)/Chapitre 07`) [container]
1. `Fables d’Ésope (trad. Chambry, 1927)`: subpages, collection, 725 parts, 0 missing, order natural, unproofread
    - toc: Fables d’Ésope (trad. Chambry, 1927)
    - text: Approbation (`Fables d’Ésope (trad. Chambry, 1927)/Approbation`) [container]
    - chapter: Avant-Propos (`Fables d’Ésope (trad. Chambry, 1927)/Avant-Propos`) [container]
    - text: Borée et le Soleil (bilingue) (`Fables d’Ésope (trad. Chambry, 1927)/Borée et le Soleil (bilingue)`) [hierarchy]
    - text: Diogène en voyage (bilingue) (`Fables d’Ésope (trad. Chambry, 1927)/Diogène en voyage (bilingue)`) [hierarchy]
    - text: Diogène et le Chauve (bilingue) (`Fables d’Ésope (trad. Chambry, 1927)/Diogène et le Chauve (bilingue)`) [hierarchy]
    - text: Hermès et la Terre (bilingue) (`Fables d’Ésope (trad. Chambry, 1927)/Hermès et la Terre (bilingue)`) [hierarchy]
    - text: Hermès et le Statuaire (bilingue) (`Fables d’Ésope (trad. Chambry, 1927)/Hermès et le Statuaire (bilingue)`) [hierarchy]
    - text: Hermès et les Artisans (bilingue) (`Fables d’Ésope (trad. Chambry, 1927)/Hermès et les Artisans (bilingue)`) [hierarchy]
    - text: Hermès et Tirésias (bilingue) (`Fables d’Ésope (trad. Chambry, 1927)/Hermès et Tirésias (bilingue)`) [hierarchy]
    - text: Héraclès et Athéna (bilingue) (`Fables d’Ésope (trad. Chambry, 1927)/Héraclès et Athéna (bilingue)`) [hierarchy]
    - text: Héraclès et Plutus (bilingue) (`Fables d’Ésope (trad. Chambry, 1927)/Héraclès et Plutus (bilingue)`) [hierarchy]
    - … 714 more
1. `Un coup de dés jamais n’abolira le hasard (édition monospaced)`: single, single, 1 parts, 0 missing, order –, unknown
    - text: Un coup de dés jamais n’abolira le hasard (édition monospaced)
1. `Les Crimes de l’amour`: mixed, book, 8 parts, 0 missing, order toc, unproofread
    - toc: Les Crimes de l’amour
    - text: Avis des Éditeurs (`Les Crimes de l’amour/Avis des Éditeurs`) [container]
    - text: Juliette et Raunai (`Les Crimes de l’amour/Juliette et Raunai`) [container]
    - text: Idée sur les Romans (`Les Crimes de l’amour/Idée sur les Romans`) [container]
    - text: L’auteur des Crimes de l’Amour à Villeterque (`Les Crimes de l’amour/L’auteur des Crimes de l’Amour à Villeterque`) [container]
    - text: Le Marquis de Sade. (`Les Crimes de l’amour/Le Marquis de Sade. — L’Homme`) [container]
    - text: Le Marquis de Sade. (`Les Crimes de l’amour/Le Marquis de Sade. — Ses Écrits`) [container]
    - chapter: Section des Piques. (`Les Crimes de l’amour/Section des Piques`) [container]
    - text: Sade - Lettre du 30 Floréal, An X, Pélagie, au ministre de la justice [container]
1. `La Sauvage apprivoisée (trad. Hugo)`: single, collection, 1 parts, 0 missing, order –, validated
    - text: La Sauvage apprivoisée (trad. Hugo)
1. `L’Anti-Justine ou les délices de l’Amour (1864)`: subpages, book, 53 parts, 0 missing, order toc, proofread
    - toc: L’Anti-Justine ou les délices de l’Amour (1864)
    - text: Bibliographie (`L’Anti-Justine ou les délices de l’Amour (1864)/00-1`) [container]
    - chapter: Introduction (`L’Anti-Justine ou les délices de l’Amour (1864)/00-2`) [container]
    - chapter: Préface (`L’Anti-Justine ou les délices de l’Amour (1864)/00-3`) [container]
    - chapter: Chapitre — premier. — De l’enfant qui bande (`L’Anti-Justine ou les délices de l’Amour (1864)/1`) [container]
    - chapter: Chapitre — II. — Du con soyeux (`L’Anti-Justine ou les délices de l’Amour (1864)/2`) [container]
    - chapter: Chapitre — III. — De la mère foutue (`L’Anti-Justine ou les délices de l’Amour (1864)/3`) [container]
    - chapter: Chapitre — IV. — D’un autre beau-frère cocu (`L’Anti-Justine ou les délices de l’Amour (1864)/4`) [container]
    - chapter: Chapitre — V. — Du bon mari Spartiate (`L’Anti-Justine ou les délices de l’Amour (1864)/5`) [container]
    - chapter: Chapitre — VI. — De l’époux qui se fait enculeur (`L’Anti-Justine ou les délices de l’Amour (1864)/6`) [container]
    - chapter: Chapitre — VII. — Du conin au poil follet (`L’Anti-Justine ou les délices de l’Amour (1864)/7`) [container]
    - chapter: Chapitre — VIII. — Des conditions de mariage (`L’Anti-Justine ou les délices de l’Amour (1864)/8`) [container]
    - … 42 more
1. `Chants et Chansons (Pierre Dupont)`: mixed, collection, 46 parts, 0 missing, order natural, proofread
    - front: Chants et Chansons (Pierre Dupont)
    - chapter: 1852 (`Chants et Chansons (Pierre Dupont)/1852`) [container]
    - text: Belzébut (`Chants et Chansons (Pierre Dupont)/Belzébut`) [container]
    - text: Hegésippe Moreau (`Chants et Chansons (Pierre Dupont)/Hegésippe Moreau`) [container]
    - text: Je veux battre les noix (`Chants et Chansons (Pierre Dupont)/Je veux battre les noix`) [container]
    - text: Kossuth (`Chants et Chansons (Pierre Dupont)/Kossuth`) [container]
    - text: La Blonde (`Chants et Chansons (Pierre Dupont)/La Blonde`) [container]
    - text: La Brune (`Chants et Chansons (Pierre Dupont)/La Brune`) [container]
    - text: La Chanson du blé (`Chants et Chansons (Pierre Dupont)/La Chanson du blé`) [container]
    - text: La Châtaine (`Chants et Chansons (Pierre Dupont)/La Châtaine`) [container]
    - text: La Fille du peuple (`Chants et Chansons (Pierre Dupont)/La Fille du peuple`) [container]
    - text: La Fête (`Chants et Chansons (Pierre Dupont)/La Fête`) [container]
    - … 35 more
1. `À la mère polonaise (traduction Ladislas Mickiewicz)`: single, book, 1 parts, 0 missing, order –, validated
    - text: À la mère polonaise (traduction Ladislas Mickiewicz)
1. `Le Livre de la jungle (trad. Fabulet et Humières)`: subpages, book, 14 parts, 0 missing, order scan, validated
    - toc: Le Livre de la jungle (trad. Fabulet et Humières)
    - text: Les Frères de Mowgli (`Le Livre de la jungle (trad. Fabulet et Humières)/Les Frères de Mowgli`) [container]
    - text: Chanson de chasse du clan de Seeonee (`Le Livre de la jungle (trad. Fabulet et Humières)/Chanson de chasse du clan de Seeonee`) [container]
    - text: La chasse de Kaa (`Le Livre de la jungle (trad. Fabulet et Humières)/La chasse de Kaa`) [container]
    - text: Chanson de route des Bandar-Log (`Le Livre de la jungle (trad. Fabulet et Humières)/Chanson de route des Bandar-Log`) [container]
    - text: « Au tigre, au tigre ! » (`Le Livre de la jungle (trad. Fabulet et Humières)/« Au tigre, au tigre ! »`) [container]
    - text: La Chanson de Mowgli (`Le Livre de la jungle (trad. Fabulet et Humières)/La Chanson de Mowgli`) [container]
    - text: Le Phoque blanc (`Le Livre de la jungle (trad. Fabulet et Humières)/Le Phoque blanc`) [container]
    - text: Lukannon (`Le Livre de la jungle (trad. Fabulet et Humières)/Lukannon`) [container]
    - text: « Rikki-Tikki-Tavi » (`Le Livre de la jungle (trad. Fabulet et Humières)/« Rikki-Tikki-Tavi »`) [container]
    - text: L’Ode de Darzee (`Le Livre de la jungle (trad. Fabulet et Humières)/L’Ode de Darzee`) [container]
    - text: Toomai des Éléphants (`Le Livre de la jungle (trad. Fabulet et Humières)/Toomai des Éléphants`) [container]
    - … 3 more
1. `Gustave III (éd. Dentu)`: single, collection, 1 parts, 0 missing, order –, proofread
    - text: Gustave III (éd. Dentu)
1. `La Famille Elliot`: subpages, book, 26 parts, 0 missing, order toc, validated
    - toc: La Famille Elliot
    - text: Notice biographique sur Jane Austen, auteur de la Famille Elliot (`La Famille Elliot/Notice biographique`) [container]
    - chapter: Note du traducteur (`La Famille Elliot/Note du traducteur`) [container]
    - chapter: Chapitre I (`La Famille Elliot/1`) [container]
    - chapter: Chapitre II (`La Famille Elliot/2`) [container]
    - chapter: Chapitre III (`La Famille Elliot/3`) [container]
    - chapter: Chapitre IV (`La Famille Elliot/4`) [container]
    - chapter: Chapitre V (`La Famille Elliot/5`) [container]
    - chapter: Chapitre VI (`La Famille Elliot/6`) [container]
    - chapter: Chapitre VII (`La Famille Elliot/7`) [container]
    - chapter: Chapitre VIII (`La Famille Elliot/8`) [container]
    - chapter: Chapitre IX (`La Famille Elliot/9`) [container]
    - … 15 more
1. `Constitution de la République de Madagascar de 1992 révisée en 1998`: single, book, 1 parts, 0 missing, order –, unproofread
    - text: Constitution de la République de Madagascar de 1992 révisée en 1998
1. `Le Semeur de cendres`: subpages, collection, 11 parts, 0 missing, order scan, unproofread
    - toc: Le Semeur de cendres
    - text: Ô JEUNESSE, FERVENT ET CLAIR FOYER D’AMOUR (`Le Semeur de cendres/« Ô jeunesse, fervent et clair foyer d’amour »`) [container]
    - text: NUIT D’OMBRE, NUIT TRAGIQUE, Ô NUIT DÉSESPÉRÉE (`Le Semeur de cendres/« Nuit d’ombre, nuit tragique, ô nuit désespérée »`) [container]
    - text: TU RANGEAIS EN CHANTANT POUR LE REPAS DU SOIR (`Le Semeur de cendres/« Tu rangeais en chantant pour le repas du soir »`) [container]
    - text: CE SOIR, SUR LE CHEMIN SONORE DU COTEAU (`Le Semeur de cendres/« Ce soir, sur le chemin sonore du coteau »`) [container]
    - text: VOUS QUI SUR MON FRONT, TOUTE EN LARMES (`Le Semeur de cendres/« Vous qui sur mon front, toute en larmes »`) [container]
    - text: UN SOIR, AU TEMPS DU SOMBRE ÉQUINOXE D’AUTOMNE (`Le Semeur de cendres/« Un soir, au temps du sombre équinoxe d’automne »`) [container]
    - text: « La Pensée est une eau sans cesse jaillissante » (`Le Semeur de cendres/« La Pensée est une eau sans cesse jaillissante »`) [container]
    - text: « Il est si tard, il fait, cette nuit de novembre » (`Le Semeur de cendres/« Il est si tard, il fait, cette nuit de novembre »`) [container]
    - text: « Ah ! Seigneur, Dieu des cœurs robustes, répondez » (`Le Semeur de cendres/« Ah ! Seigneur, Dieu des cœurs robustes, répondez »`) [container]
    - text: « Goûte, me dit le Soir de juin avec douceur » (`Le Semeur de cendres/« Goûte, me dit le Soir de juin avec douceur »`) [container]
    - text: « Je vais sur la pelouse humide de rosée » (`Le Semeur de cendres/« Je vais sur la pelouse humide de rosée »`) [container]
1. `Essais`: virtual, book, 371 parts, 11 missing, order natural, unproofread
    - volume: exemplaire de Bordeaux, 1588-1592 (`Essais/exemplaire de Bordeaux, 1588-1592`)
      - volume: Livre II (`Essais/Livre II`) [container]
        - text: LES CHAPITRES DU LIVRE &nbsp; SECOND (`Essais/Livre II/Entete`) [container]
        - text: 1 D l’inconstance de nos actions. (`Essais/Livre II/Chapitre 1`) [hierarchy]
        - text: 2De l’ivrognerie. (`Essais/Livre II/Chapitre 2`) [container]
        - text: 3Coustume de l’isle de Cea. (`Essais/Livre II/Chapitre 3`) [container]
        - text: 4A demain les affaires. (`Essais/Livre II/Chapitre 4`) [container]
        - text: 5De la conscience. (`Essais/Livre II/Chapitre 5`) [container]
        - text: 6De l’exercitation. (`Essais/Livre II/Chapitre 6`) [container]
        - text: 7Des recompenses d’honneur. (`Essais/Livre II/Chapitre 7`) [container]
        - text: 8De l’affection des peres aux enfans. (`Essais/Livre II/Chapitre 8`) [container]
        - text: 9Des armes des Parthes (`Essais/Livre II/Chapitre 9`) [container]
    - … 359 more
1. `Petits châteaux de Bohême (Didier, 1853)`: mixed, book, 23 parts, 0 missing, order toc, proofread
    - toc: Petits châteaux de Bohême (Didier, 1853)
    - text: À un ami (`Petits châteaux de Bohême (Didier, 1853)/À un ami`) [container]
    - text: PREMIER CHÂTEAU (`Petits châteaux de Bohême (Didier, 1853)/Premier château`) [container]
    - text: Avril (`Avril (Nerval)`) [container]
    - text: Fantaisie (`Fantaisie (Nerval)`) [container]
    - text: La Grand’mère [container]
    - text: La Cousine [container]
    - text: Pensée de Byron [container]
    - text: Gaieté [container]
    - text: Politique (`Politique (Nerval)`) [container]
    - text: Le Point noir [container]
    - text: Les Papillons (`Les Papillons (Nerval)`) [container]
    - … 12 more
1. `La Tempête (Shakespeare, trad. Hugo)`: single, collection, 1 parts, 0 missing, order –, validated
    - text: La Tempête (Shakespeare, trad. Hugo)
1. `Catherine Morland`: subpages, periodical-issue, 31 parts, 0 missing, order chain, proofread
    - toc: Catherine Morland
    - chapter: I (`Catherine Morland/I`) [hierarchy]
    - chapter: II (`Catherine Morland/II`) [hierarchy]
    - chapter: III (`Catherine Morland/III`) [hierarchy]
    - chapter: IV (`Catherine Morland/IV`) [hierarchy]
    - chapter: V (`Catherine Morland/V`) [hierarchy]
    - chapter: VI (`Catherine Morland/VI`) [hierarchy]
    - chapter: VII (`Catherine Morland/VII`) [hierarchy]
    - chapter: VIII (`Catherine Morland/VIII`) [hierarchy]
    - chapter: IX (`Catherine Morland/IX`) [hierarchy]
    - chapter: X (`Catherine Morland/X`) [hierarchy]
    - chapter: XI (`Catherine Morland/XI`) [hierarchy]
    - … 20 more
1. `Histoire d’un paysan`: subpages, book, 62 parts, 0 missing, order toc, proofread
    - toc: Histoire d’un paysan
    - text: Les États Généraux, I (`Histoire d’un paysan/1/01`) [container]
    - text: Les États Généraux, II (`Histoire d’un paysan/1/02`) [container]
    - text: Les États Généraux, III (`Histoire d’un paysan/1/03`) [container]
    - text: Les États Généraux, IV (`Histoire d’un paysan/1/04`) [container]
    - text: Les États Généraux, V (`Histoire d’un paysan/1/05`) [container]
    - text: Les États Généraux, VI (`Histoire d’un paysan/1/06`) [container]
    - text: Les États Généraux, VII (`Histoire d’un paysan/1/07`) [container]
    - text: Les États Généraux, VIII (`Histoire d’un paysan/1/08`) [container]
    - text: Les États Généraux, IX (`Histoire d’un paysan/1/09`) [container]
    - text: Les États Généraux, X (`Histoire d’un paysan/1/10`) [container]
    - text: Les États Généraux, XI (`Histoire d’un paysan/1/11`) [container]
    - … 51 more
1. `Histoires grotesques et sérieuses`: mixed, collection, 11 parts, 0 missing, order scan, validated
    - toc: Histoires grotesques et sérieuses
    - text: Le Mystère de Marie Roget (`Histoires grotesques et sérieuses/Le Mystère de Marie Roget`) [container]
    - text: Le Joueur d’échecs de Maelzel (`Histoires grotesques et sérieuses/Le Joueur d’échecs de Maelzel`) [container]
    - text: Éléonora (`Histoires grotesques et sérieuses/Éléonora`) [container]
    - text: Un Événement à Jérusalem (`Histoires grotesques et sérieuses/Un événement à Jérusalem`) [container]
    - text: L’Ange du Bizarre (`Histoires grotesques et sérieuses/L’Ange du bizarre`) [container]
    - text: Le Système du docteur Goudron et du professeur Plume (`Histoires grotesques et sérieuses/Le Système du docteur Goudron et du professeur Plume`) [container]
    - text: Le Domaine d’Arnheim (`Histoires grotesques et sérieuses/Le Domaine d’Arnheim`) [container]
    - text: Le Cottage Landor (`Histoires grotesques et sérieuses/Le Cottage Landor`) [container]
    - text: Philosophie de l’Ameublement (`Histoires grotesques et sérieuses/Philosophie de l’ameublement`) [container]
    - text: La Genèse d’un poëme (`Histoires grotesques et sérieuses/La Genèse d’un poème`) [container]
    - text: Le Corbeau (traduit par Charles Baudelaire) [container]
1. `Méditations poétiques`: virtual, collection, 145 parts, 0 missing, order single, validated
    - volume: Édition de 1860 (`Méditations poétiques/Édition de 1860`)
      - chapter: PRÉFACE GÉNÉRALE (`Œuvres complètes de Lamartine (1860)/Préface générale`) [container]
      - text: Des Méditations (`Œuvres complètes de Lamartine (1860)/Tome 1/Des Méditations`) [container]
      - text: Des Destinées de la Poésie (`Œuvres complètes de Lamartine (1860)/Tome 1/Des Destinées de la Poésie`) [container]
      - text: L’Isolement (`Œuvres complètes de Lamartine (1860)/Tome 1/L’Isolement`) [container]
      - text: Commentaire de la première Méditation (`Œuvres complètes de Lamartine (1860)/Tome 1/L’Isolement/Commentaire`) [container]
      - text: L’Homme (`Œuvres complètes de Lamartine (1860)/Tome 1/L’Homme`) [container]
      - text: Commentaire de la deuxième Méditation (`Œuvres complètes de Lamartine (1860)/Tome 1/L’Homme/Commentaire`) [container]
      - text: À Elvire (`Œuvres complètes de Lamartine (1860)/Tome 1/À Elvire`) [container]
      - text: Commentaire de la troisième Méditation (`Œuvres complètes de Lamartine (1860)/Tome 1/À Elvire/Commentaire`) [container]
      - text: Le Soir (`Œuvres complètes de Lamartine (1860)/Tome 1/Le Soir`) [container]
      - text: Commentaire de la quatrième Méditation (`Œuvres complètes de Lamartine (1860)/Tome 1/Le Soir/Commentaire`) [container]
    - … 133 more
1. `Autre guitare (partition)`: single, book, 1 parts, 0 missing, order –, proofread
    - text: Autre guitare (partition)
1. `L’Homme machine (1796)`: single, book, 1 parts, 0 missing, order –, proofread
    - text: L’Homme machine (1796)

