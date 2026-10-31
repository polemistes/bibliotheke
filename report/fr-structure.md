# fr.wikisource: structure and quality survey (WP1a)

Generated 2026-10-31 by `python -I -m wscatalog.survey fr` from `frwikisource-20261001-pages-articles1.xml-p1p1500000.bz2, frwikisource-20261001-pages-articles1.xml-p1500001p3000000.bz2, frwikisource-20261001-pages-articles1.xml-p3000001p4500000.bz2, frwikisource-20261001-pages-articles1.xml-p4500001p5004401.bz2` (resolution and quality rules: PLAN.md 5.2 steps 1–1h and 5.4; config `pipeline/config/fr.yaml`). Resolution took 46 s on the cached extract.

Input: 537,405 ns0 pages, 56,685 ns0 redirects, 4,030,797 scan pages, 28,847 Index pages, 10,886 pages with the `disambiguation` page prop.

## 1. Works

38,805 works resolved from 524,324 non-excluded ns0 pages; 38,172 enter the catalog after the hard exclusions (section 9). Multi-part works hold 486,736 parts, of which 373,370 are searchable texts.

| structure | works | parts | texts |
|---|---|---|---|
| single | 27,001 | 27,001 | 0 |
| subpages | 9,396 | 320,351 | 228,329 |
| virtual | 1,217 | 121,119 | 111,968 |
| linked | 795 | 18,465 | 13,450 |
| mixed | 396 | 26,801 | 19,623 |

| type (step 1e) | works |
|---|---|
| periodical-issue | 19,882 |
| book | 13,455 |
| collection | 4,670 |
| single | 435 |
| reference | 219 |
| document | 144 |

Largest works by parts:

| work | structure | type | parts | texts | missing | rules of the parts |
|---|---|---|---|---|---|---|
| L’Encyclopédie | virtual | reference | 45,535 | 45,462 | 0 | container 41632, hierarchy 3902 |
| Dictionnaire de Trévoux | virtual | reference | 32,173 | 32,111 | 11220 | container 31810, hierarchy 362 |
| Petit dictionnaire de la langue française (1836) | subpages | reference | 23,874 | 23,829 | 0 | hierarchy 23874 |
| Dictionnaire des patois romans de la Moselle | subpages | reference | 21,247 | 21,230 | 64 | container 21244, hierarchy 3 |
| Petit dictionnaire de la langue française (1860) | subpages | reference | 20,406 | 20,367 | 7 | hierarchy 20406 |
| Correspondance de Voltaire | subpages | collection | 10,426 | 387 | 2 | container 10375, hierarchy 51 |
| Petit dictionnaire de la langue française (Hocquart, 1831) | subpages | reference | 7,352 | 7,334 | 19 | hierarchy 7352 |
| Grand dictionnaire universel du XIXe siècle | subpages | reference | 5,834 | 5,801 | 19902 | container 5402, hierarchy 432 |
| Dictionnaire liégeois-français (Forir) | subpages | reference | 4,534 | 4,515 | 303 | container 4516, hierarchy 18 |
| Le Littré de la Grand’Côte | virtual | reference | 4,251 | 4,216 | 0 | hierarchy 4250 |
| Dictionnaire infernal | virtual | reference | 3,829 | 3,797 | 0 | container 3825, hierarchy 3 |
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
| B | mixed subpages and root pages (`mixed`) | 396 works, 26,801 parts |
| C | work with an oversized page (> oversized_bytes, transcluded scan pages included) | 600 works |
| D | missing root, virtual root (`virtual`) | 1,217 works, 121,119 parts |
| E | texts inside a subpage collection reached from a disambiguation or versions page | 30,972 parts (28,899 searchable); all searchable subpage texts: 350,590 |
| F | whole-text duplicate subpages (excluded) | 4,271 pages |
| G | reference works (type `reference`) | 219 works, 219,937 parts; 0 pages excluded by title prefix |
| H | deep nesting (parts at depth ≥ 2, i.e. three levels below the root) | 187 works |
| I | several editions: works with an other-versions template | 563 works; 5 versions pages excluded |

Tree depth of multi-part works (levels below the root): 1: 10,559, 2: 1,058, 3: 146, 4: 34, 5: 6, 6: 1.

## 4. Parent rules and order sources (steps 1b, 1c)

| parent rule | pages |
|---|---|
| container | 361,011 |
| hierarchy | 125,540 |
| own | 37,588 |
| toc | 185 |

| order source (nodes with ≥ 2 children) | nodes |
|---|---|
| toc | 8,253 |
| scan | 2,852 |
| chain | 2,512 |
| natural | 596 |
| toc-partial | 295 |

`toc-partial` (implementation choice, see section 13): the TOC lists at least half of the children, which keep its order; the rest follow in natural order. 232 `natural` nodes have a TOC that lists fewer than half.

Root pages attached by their header's container link: 9,167; most frequent containers: `Lettres de Madame de Sévigné/Édition Monmerqué` 887; `Œuvres de P. Lachambeaudie. ― Fables et Poésies (1854)` 411; `Les Fables complètes (Viennet)` 213; `Parnasse de la Jeune Belgique` 162; `La Chanson française du XVe au XXe siècle` 147; `La Nuit (Gilkin)` 130; `Œuvres de Sully Prudhomme/Poésies 1866-1872` 128; `Œuvres de Théophile Gautier (Lemerre, 1890)` 124; `Échos de France` 121; `Poésies diverses (Corneille)` 109.

Pages attached by TOC membership (rule 3): 185; most frequent TOC pages: `Poésies Sociales des Ouvriers` 41; `Tamaris (RDDM)` 25; `Le Péché de Madeleine` 25; `Antonia (RDDM)` 12; `Œuvres complètes (Ovide)` 10; `Œuvres complètes de Platon, traduites par Émile Chambry` 8; `Œuvres complètes de François Arago` 6; `Œuvres complètes de Paul Verlaine` 4; `Femmes-Poëtes de la France` 4; `Césarine Dietrich (RDDM)` 3.

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

**Orphan trees** (virtual roots): 1,217, holding 121,119 parts. Examples: `Revue Anglo-Américaine` (1); `Zevaco - Triboulet` (8); `Revue Musicale de Lyon 1904-04-06` (8); `Les Mille et Une Nuits` (114); `Essai sur Taine` (1); `Les Illuminés (1868)` (6); `Bibliothèque historique` (1); `L’Impromptu de Versailles` (1).

**Oversized pages** (> 300,000 bytes of wikitext including transcluded scan pages): 1,275. Largest: `Jean part1` 2.0 MB; `Le Système du Monde/Texte entier/Tome 6` 2.0 MB; `Essais/édition Michaud, 1907/Livre II/Chapitre 12` 1.9 MB; `Dictionnaire de la langue française du seizième siècle/A1` 1.9 MB; `Jean Chrysostome/Commentaire sur les Psaumes` 1.9 MB; `Thresor de la langue françoise/Nomenclator` 1.8 MB; `Le Système du Monde/Texte entier/Tome 7` 1.8 MB; `George Sand, sa vie et ses œuvres/Texte entier du tome 3` 1.8 MB; `George Sand, sa vie et ses œuvres/3` 1.8 MB; `Le Coran (Traduction de Savary)/Titre` 1.8 MB.

**Red links in TOCs** (missing parts): 1,009 multi-part works have red links, 56,725 in total; median share of the TOC 53%. Most missing: `Grand dictionnaire universel du XIXe siècle` 19902 of 25736; `Dictionnaire de Trévoux` 11220 of 43393; `Nouveau dictionnaire de pédagogie et d’instruction primaire` 1517 of 1665; `Nouvelle Biographie générale` 1252 of 1524; `Dictionnaire des Arts et des Sciences` 969 of 1189; `Encyclopédie méthodique` 444 of 3211; `Mémoires secrets de Bachaumont (1777-1789)` 417 of 495; `Biographie nationale de Belgique` 381 of 1909.

## 8. Colon prefixes with ≥ 100 root pages

None.

## 9. Quality and completeness (PLAN.md 5.4)

**Hard exclusions**: 633 works — no-text 264, no-text-pages 160, index-faulty 107, under-300-bytes 102.

| tier | works | share | texts in them | source |
|---|---|---|---|---|
| validated | 10,727 | 28.1 % | 45,884 | page-levels 10,644, textquality 73, index-progress 10 |
| proofread | 20,490 | 53.7 % | 289,359 | page-levels 20,387, textquality 93, index-progress 10 |
| unproofread | 6,852 | 18.0 % | 36,923 | page-levels 6,700, textquality 86, index-progress 66 |
| unknown | 103 | 0.3 % | 283 | none 103 |

586 works are proofread by the ≥ 90 % rule. 4,594 of the 6,852 unproofread works have no page above level 1 (raw OCR).

**Incomplete**: 2,577 works — missing-parts 1,138, missing-pages 753, coverage 685, index-progress 1.
Works with missing scan pages inside a transcluded range: 1,000 (76,718 pages).
Coverage was computed for 2,856 works that are their Index's book; 832 cover under 95 % of the Index's text pages.
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
- `Fables (La Fontaine) Barbin & Thierry` (virtual, 1,569 B): Privilege 1
- `A Blanqui (Louise Michel)` (single, 4,870 B): A ceux qui donnent leur vie pour le peuple, soit que la terre ait bu leur sang, soit qu’ils aient souffert dans les prisons, le peuple donne une majestueuse glo
- `Commentaires sur Unto This Last de M. K. Gandhi` (single, 36,480 B): __EXPECTWITHOUTSCANS__ Catégorie:Thèses et Mémoires universitairesCatégorie:XXe siècle Gandhi, une pensée moderne == Dans un chapitre de son autobiographie inti
- `Seconde dissertation sur les éléments` (single, 2,572 B): Seconde dissertation sur les éléments - Eller Seconde dissertation sur les éléments - Eller
- `L’Élémentarisme et son origine` (single, 7,387 B): Catégorie:Esthétique Catégorie:1928 Catégorie:De Stijl La polarité fondamentale de la structure naturelle est conditionnée dans tout ce qui nous entoure, par le
- `Théâtre de Meilhac et Halévy - Tome 7` (single, 419 B): Le prince \| Les Brigands \| La roussotte \| Carmen
- `Lafitte` (single, 16,161 B): I Une figure de Français, devenue légendaire en Louisiane, c’est celle de Jean Lafitte, que la tradition qualifie, tour à tour, de corsaire et de pirate, qui fu
- `Ode à la contemplation du Go` (single, 3,262 B): __EXPECTWITHOUTSCANS__ 觀棋長吟 — 观棋长吟, Ode à la contemplation du Go. Chinois classique : 觀棋長吟 院靜春深晝掩扉，竹間閑看客爭棋。 搜羅神鬼聚胸臆，措致山河入範圍。 局合龍蛇成陣闘，刧殘鴻鴈破行飛。 殺多項羽坑秦卒，敗劇苻堅畏晉師。 座
- `Le Violon brisé, simple histoire` (single, 632 B): thumb\|center\|500px\|Le Violon brisé, illustration Faria (couverture du petit format, 1885) Catégorie:Chansons Catégorie:Chansons d'après
- `Lamarckiens et Darwiniens : Discussion de quelques théories sur la formation des espèces` (virtual, 1,121 B): 
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

**Default view** (validated or proofread, complete, not reference): 29,576 works with 118,684 texts; with unknown works shown: 29,675 works (118,738 texts). Works in the default view whose pages are all level 1: 0. Current setting: `show_unknown_by_default = None`.

## 10. Parts table size (WS§5.2)

| parts_cap | works with catalog parts | parts rows | parts table, gzipped | parts_fts, gzipped |
|---|---|---|---|---|
| 500 | 11,561 | 239,747 | 14.26 MB | 2.28 MB |
| 2,000 | 11,586 | 261,850 | 15.35 MB | 2.46 MB |
| ∞ (all, reference works included) | 11,767 | 496,334 | 25.04 MB | 4.59 MB |
| 2,000, compact encoding | 11,586 | 261,850 | 13.42 MB | 2.46 MB |

Rows count multi-part works only, one row per part plus the root row. 26,405 single-page works would add one row each if stored. Sizes are a SQLite file holding only the table (with its index) at gzip level 9. The compact encoding (not in WS§5.1, measured for the cap decision) stores a part's page_title as the suffix after the work root's title where possible, display_title only when it differs from the last title segment, and no per-part quality_pages.

## 11. Named cases (PLAN.md WP1 acceptance, WS§9)

- `La Légende des siècles`: work, mixed, type collection, 132 parts (128 texts), 0 missing, order toc-partial, oversized False, quality unproofread (page-levels), complete False (missing-pages).
- `L’Épopée du ver`: part 71 of `La Légende des siècles` (text, display “L’Épopée du ver”, rule container, searchable True).
- `Clarté d’âmes`: part 73 of `La Légende des siècles` (text, display “Clarté d’âmes”, rule container, searchable True).
- `Madame Bovary`: work, mixed, type book, 57 parts (17 texts), 0 missing, order toc, oversized False, quality validated (page-levels), complete True.

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

1. `Poètes d’aujourd’hui`: mixed, collection, 8 parts, 51 missing, order toc-partial, unproofread
    - toc: Poètes d’aujourd’hui
    - chapter: Introduction (`Poètes d’aujourd’hui/Introduction`) [container]
    - chapter: Introduction à la première édition (`Poètes d’aujourd’hui/Introduction à la première édition`) [container]
    - text: Henri (`Poètes d’aujourd’hui/Henri Barbusse`) [container]
    - text: Henry (`Poètes d’aujourd’hui/Henry Bataille`) [container]
    - text: Maurice (`Poètes d’aujourd’hui/Maurice Maeterlinck`) [container]
    - chapter: Table détaillée (`Poètes d’aujourd’hui/Table détaillée`) [container]
    - text: Le Dernier Chant d’Orphée [container]
    - text: Sappho (`Poètes d’aujourd’hui/Sappho`) [container]
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
      - chapter: Préface (`Les Symphonies - Idylles héroïques/Préface`) [container]
      - text: Dédicace (`À mon père (Laprade)`) [container]
      - text: Symphonie des saisons [container]
      - text: La Source éternelle [container]
      - text: Les deux Muses (`Les Deux Muses (Laprade)`) [container]
      - text: Entre deux orages [container]
      - text: Consolation (`Consolation (Laprade)`) [container]
      - text: Symphonie du Torrent [container]
      - text: À une jeune fille poète (`À une jeune fille poète (Laprade)`) [container]
      - text: L’Alpe vierge [container]
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
      - chapter: V (`La Bonne aventure (Sue)/1/V`) [container]
      - chapter: VI (`La Bonne aventure (Sue)/1/VI`) [container]
      - chapter: VII (`La Bonne aventure (Sue)/1/VII`) [container]
      - chapter: VIII (`La Bonne aventure (Sue)/1/VIII`) [container]
      - chapter: IX (`La Bonne aventure (Sue)/1/IX`) [container]
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
1. `Histoire des églises et chapelles de Lyon`: mixed, book, 127 parts, 65 missing, order toc-partial, proofread
    - toc: Histoire des églises et chapelles de Lyon
    - chapter: Introduction (`Histoire des églises et chapelles de Lyon/Introduction`) [container]
    - text: La Primatiale et ses annexes : Saint-Étienne et Sainte-Croix (`Histoire des églises et chapelles de Lyon/I`) [container]
    - text: Célestins ; Saint-Bruno des Chartreux ; Frères Prêcheurs de Confort et des Brotteaux ; Saint-Denis de la Croix-Rousse ; Chapelle de l’Hôtel de Ville ; Minimes (`Histoire des églises et chapelles de Lyon/II`) [container]
    - text: Bernardines ; Carmélites ; Clarisses ; Bénédictines de Chazeaux ; Saint-François de Sales ; Calvaire (`Histoire des églises et chapelles de Lyon/III`) [container]
    - text: La Charité ; Cœur-Agonisant ; Charlottes ; Trinitaires ; Visitation (`Histoire des églises et chapelles de Lyon/IV`) [container]
    - text: Saint-Irénée ; Saint-Just ; le Grand Séminaire (`Histoire des églises et chapelles de Lyon/V`) [container]
    - text: Saint-Paul ; Saint-Laurent ; les Recluseries ; Saint-Épipode ; les Grands-Augustins et Notre-Dame-Saint-Louis-Saint-Vincent (`Histoire des églises et chapelles de Lyon/VI`) [container]
    - text: Providence Caille et Petits-Frères de Marie ; Prado ; Saint-Joseph ; Saint-Sacrement ; Antiquaille (`Histoire des églises et chapelles de Lyon/VII`) [container]
    - text: La Rédemption ; Franciscains de la Guillotière dits Picpus ; Notre-Dame-Saint-Louis de la Guillotière ; Saint-Pothin ; Monument des Brotteaux ; l’Immaculée-Conception ; Sainte-Marie-des-Anges ; Montch (`Histoire des églises et chapelles de Lyon/VIII`) [container]
    - text: Commanderie Saint-Antoine ; Missionnaires Saint-Joseph ; Saint-Roch ; religieuses Saint-Joseph ; Franciscaines de la Propagation de la Foi ; sœurs Saint-François d’Assise de la Tour-Pitrat ; Saint-Ber (`Histoire des églises et chapelles de Lyon/IX`) [container]
    - text: Sainte-Blandine ; Adoration-Réparatrice ; Marie-Auxiliatrice ; Marie-Thérèse ; Incurables ; Saint-Viateur ; Trinitaires ; Lazaristes ; Récollets ; chapelle de Balmont (`Histoire des églises et chapelles de Lyon/X`) [container]
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
1. `L’Année terrible`: mixed, collection, 98 parts, 0 missing, order toc-partial, proofread
    - toc: L’Année terrible
    - text: Les 7,500,000 oui (`L’Année terrible/Les 7 500 000 oui`) [container]
    - text: Sedan (`L’Année terrible/Sedan`) [container]
    - text: Choix entre les deux nations (`L’Année terrible/Choix entre les deux nations`) [container]
    - text: À prince, prince et demi (`L’Année terrible/À prince, prince et demi`) [container]
    - text: Dignes l’un de l’autre (`L’Année terrible/Dignes l’un de l’autre`) [container]
    - text: Paris bloqué (`L’Année terrible/Paris bloqué`) [container]
    - text: À petite Jeanne (`L’Année terrible/À petite Jeanne`) [container]
    - text: J’étais le vieux rôdeur sauvage de la mer (`L’Année terrible/J’étais le vieux rôdeur sauvage de la mer`) [container]
    - text: Et voilà donc les jours tragiques revenus ! (`L’Année terrible/Et voilà donc les jours tragiques revenus !`) [container]
    - text: Sept. Le chiffre du mal (`L’Année terrible/Sept. Le chiffre du mal`) [container]
    - text: De Haut de la muraille de Paris, à la nuit tombante (`L’Année terrible/De Haut de la muraille de Paris, à la nuit tombante`) [container]
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
1. `Les Poèmes d’Edgar Poe`: mixed, collection, 39 parts, 0 missing, order toc-partial, unproofread
    - front: Les Poèmes d’Edgar Poe
    - text: Sonnet (`Les Poèmes d’Edgar Poe/Le Tombeau d’Edgar Poe`) [container]
    - text: Le Corbeau (`Le Corbeau (trad. Mallarmé)`) [container]
    - text: Stances à Hélène (`Stances à Hélène (Les Poèmes d’Edgar Poe)`) [container]
    - text: Le Palais Hanté (`Le Palais hanté`) [container]
    - text: Eulalie [container]
    - text: Le Ver Vainqueur (`Le Ver vainqueur`) [container]
    - text: Ulalume (`Les Poèmes d’Edgar Poe/Ulalume`) [container]
    - text: Un Rêve dans un Rêve (`Un rêve dans un rêve`) [container]
    - text: À Quelqu’un Au Paradis (`À quelqu’un au paradis`) [container]
    - text: Ballade de Noces (`Ballade de noces`) [container]
    - text: Lénore [container]
    - … 28 more
1. `La Légende des siècles`: mixed, collection, 132 parts, 0 missing, order toc-partial, unproofread
    - front: La Légende des siècles
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
      - text: Première rencontre du Christ avec le tombeau (`La Légende des siècles/Première rencontre du Christ avec le tombeau`) [container]
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
1. `Œuvres complètes (Marivaux)`: mixed, collection, 42 parts, 1 missing, order toc-partial, unproofread
    - toc: Œuvres complètes (Marivaux)
    - text: LE PÈRE PRUDENT ET ÉQUITABLE, ou Crispin l’heureux fourbe (`Le Père prudent et équitable`) [container]
    - volume: ANNIBAL (`Annibal`) [container]
      - chapter: Acte I (`Annibal/Acte I`) [container]
      - chapter: Acte II (`Annibal/Acte II`) [container]
      - chapter: Acte III (`Annibal/Acte III`) [container]
      - chapter: Acte IV (`Annibal/Acte IV`) [container]
      - chapter: Acte V (`Annibal/Acte V`) [container]
    - text: LE DÉNOUEMENT IMPRÉVU (`Le Dénouement imprévu`) [container]
    - volume: L’ÎLE DE LA RAISON, ou les petits hommes (`L’Île de la raison`) [container]
      - chapter: Préface (`L’Île de la raison/Préface`) [container]
      - chapter: Prologue (`L’Île de la raison/Prologue`) [container]
    - … 31 more

### D: virtual roots (1,217)

1. `La Petite Roque (recueil)`: virtual, collection, 13 parts, 0 missing, order single, unproofread
    - volume: Édition Conard, 1910 (`La Petite Roque (recueil)/Édition Conard, 1910`)
      - text: La Petite Roque (`La Petite Roque (recueil)/Édition Conard, 1910/La Petite Roque`) [container]
      - text: L’Épave (`La Petite Roque (recueil)/Édition Conard, 1910/L’Épave`) [container]
      - text: L’Ermite (`La Petite Roque (recueil)/Édition Conard, 1910/L’Ermite`) [container]
      - text: Mademoiselle Perle (`La Petite Roque (recueil)/Édition Conard, 1910/Mademoiselle Perle`) [container]
      - text: Rosalie Prudent (`La Petite Roque (recueil)/Édition Conard, 1910/Rosalie Prudent`) [container]
      - text: Sur les Chats (`La Petite Roque (recueil)/Édition Conard, 1910/Sur les chats`) [container]
      - text: Sauvée (`La Petite Roque (recueil)/Édition Conard, 1910/Sauvée`) [container]
      - text: Madame Parisse (`La Petite Roque (recueil)/Édition Conard, 1910/Madame Parisse`) [container]
      - text: Julie Romain (`La Petite Roque (recueil)/Édition Conard, 1910/Julie Romain`) [container]
      - text: Le Père Amable (`La Petite Roque (recueil)/Édition Conard, 1910/Le Père Amable`) [container]
      - text: La Peur (`La Petite Roque (recueil)/Édition Conard, 1910/La Peur`) [container]
    - … 1 more
1. `La Gazette rimée`: virtual, periodical-issue, 2 parts, 0 missing, order natural, validated
    - text: Denys, tyran de Syracuse (`La Gazette rimée/1867/Denys, tyran de Syracuse`)
    - text: Les Légions de Varus (`La Gazette rimée/1867/Les Légions de Varus`) [hierarchy]
1. `Les Rêves morts (Montreuil, deuxième édition)`: virtual, collection, 17 parts, 0 missing, order scan, unproofread
    - text: Les Rêves Morts (`Les Rêves morts (Montreuil, deuxième édition)/Les Rêves Morts`)
    - text: Maniwokon (`Les Rêves morts (Montreuil, deuxième édition)/Maniwokon`) [hierarchy]
    - text: Si vous étiez (`Les Rêves morts (Montreuil, deuxième édition)/Si vous étiez`) [hierarchy]
    - text: Québec français (`Les Rêves morts (Montreuil, deuxième édition)/Québec français`) [hierarchy]
    - text: Québec pittoresque (`Les Rêves morts (Montreuil, deuxième édition)/Québec pittoresque`) [hierarchy]
    - text: Dans les Montagnes Rocheuses Canadiennes (`Les Rêves morts (Montreuil, deuxième édition)/Dans les Montagnes Rocheuses Canadiennes`) [hierarchy]
    - text: La Montagne Castel (`Les Rêves morts (Montreuil, deuxième édition)/La Montagne Castel`) [hierarchy]
    - text: Une tombe dans la forêt (`Les Rêves morts (Montreuil, deuxième édition)/Une tombe dans la forêt`) [hierarchy]
    - text: La mort d’un pin (`Les Rêves morts (Montreuil, deuxième édition)/La mort d’un pin`) [hierarchy]
    - text: Rêve d’antan (`Les Rêves morts (Montreuil, deuxième édition)/Rêve d’antan`) [hierarchy]
    - text: Quand la mort me prendra (`Les Rêves morts (Montreuil, deuxième édition)/Quand la mort me prendra`) [hierarchy]
    - text: Les Fiancés de la Mort (`Les Rêves morts (Montreuil, deuxième édition)/Les Fiancés de la Mort`) [hierarchy]
    - … 5 more
1. `Journal des économistes`: virtual, periodical-issue, 18 parts, 0 missing, order natural, proofread
    - text: Chronique (`Journal des économistes/1 avril 1848/Chronique`)
    - chapter: Introduction (`Journal des économistes/1 avril 1848/Introduction`) [hierarchy]
    - text: Chronique (`Journal des économistes/1 juillet 1848/Chronique`) [hierarchy]
    - text: Chronique (`Journal des économistes/1 mai 1848/Chronique`) [hierarchy]
    - text: Chronique (`Journal des économistes/15 avril 1848/Chronique`) [hierarchy]
    - chapter: Introduction (`Journal des économistes/15 décembre 1849/Introduction`) [hierarchy]
    - chapter: Introduction (`Journal des économistes/15 décembre 1853/Introduction`) [hierarchy]
    - text: Chronique (`Journal des économistes/15 juillet 1848/Chronique`) [hierarchy]
    - text: Chronique (`Journal des économistes/Avril 1844/Chronique`) [hierarchy]
    - text: Compte rendu (`Journal des économistes/Avril 1845/Compte rendu`) [hierarchy]
    - chapter: Introduction (`Journal des économistes/Décembre 1841/Introduction`) [hierarchy]
    - chapter: Introduction (`Journal des économistes/Décembre 1842/Introduction`) [hierarchy]
    - … 6 more
1. `Le Nouveau Spectateur ou Examen des nouvelles pièces de théâtre, cahier 1 (1770)`: virtual, periodical-issue, 4 parts, 0 missing, order scan, proofread
    - text: À M. D. L. (`Le Nouveau Spectateur ou Examen des nouvelles pièces de théâtre, cahier 1 (1770)/À M. D. L.`)
    - chapter: Lettre (`Le Nouveau Spectateur ou Examen des nouvelles pièces de théâtre, cahier 1 (1770)/Lettre`) [hierarchy]
    - text: Vaudeville du Tableau parlant (`Le Nouveau Spectateur ou Examen des nouvelles pièces de théâtre, cahier 1 (1770)/Vaudeville du Tableau parlant`) [hierarchy]
    - text: Duo du Déserteur (`Le Nouveau Spectateur ou Examen des nouvelles pièces de théâtre, cahier 1 (1770)/Duo du Déserteur`) [hierarchy]
1. `Jean.I.Bernoulli.-.Autobiographie.djvu`: virtual, periodical-issue, 2 parts, 0 missing, order single, proofread
    - volume: Autobiographie de Jean I Bernoulli de Bâle (`Jean.I.Bernoulli.-.Autobiographie.djvu/Autobiographie de Jean I Bernoulli de Bâle`)
      - text: Jean.Bernoulli.1684 (`Leibniz-en.francais-Gerhardt.Math.1a7.djvu/Jean.Bernoulli.1684`) [container]
1. `Eulalie ou le grec sans larmes`: virtual, book, 1 parts, 0 missing, order single, proofread
    - chapter: 1 (`Eulalie ou le grec sans larmes/1`)
1. `La Grâce`: virtual, collection, 5 parts, 0 missing, order scan, unproofread
    - chapter: Préface (`La Grâce/Préface`)
    - chapter: Chant I (`La Grâce/Chant I`) [hierarchy]
    - chapter: Chant II (`La Grâce/Chant II`) [hierarchy]
    - chapter: Chant III (`La Grâce/Chant III`) [hierarchy]
    - chapter: Chant IV (`La Grâce/Chant IV`) [hierarchy]
1. `La Russie et l’Angleterre dans l’Asie centrale`: virtual, periodical-issue, 2 parts, 0 missing, order chain, proofread
    - chapter: 01 (`La Russie et l’Angleterre dans l’Asie centrale/01`)
    - chapter: 02 (`La Russie et l’Angleterre dans l’Asie centrale/02`) [hierarchy]
1. `Sidonie ou le français sans peine`: virtual, book, 1 parts, 0 missing, order single, unproofread
    - chapter: 1 (`Sidonie ou le français sans peine/1`)
1. `Portraits et croquis`: virtual, book, 1 parts, 0 missing, order single, proofread
    - chapter: 10 (`Portraits et croquis/10`)
1. `Apologie de la vie monastique`: virtual, book, 3 parts, 0 missing, order scan, unproofread
    - chapter: Livre premier. (`Apologie de la vie monastique/Livre premier.`)
    - chapter: Livre deuxième. (`Apologie de la vie monastique/Livre deuxième.`) [hierarchy]
    - chapter: Livre troisième. (`Apologie de la vie monastique/Livre troisième.`) [hierarchy]
1. `Nouvelles soirées canadiennes`: virtual, periodical-issue, 7 parts, 0 missing, order single, validated
    - volume: Juil-Août 1883 (`Nouvelles soirées canadiennes/Juil-Août 1883`)
      - text: Impressions (`Nouvelles soirées canadiennes/Juil-Août 1883/02`) [hierarchy]
      - text: Souvenirs de Rome (`Nouvelles soirées canadiennes/Juil-Août 1883/03`) [hierarchy]
      - text: Les aqueducs de Rome Ancienne (`Nouvelles soirées canadiennes/Juil-Août 1883/04`) [hierarchy]
      - text: Les associations ouvrières et les grèves (`Nouvelles soirées canadiennes/Juil-Août 1883/05`) [hierarchy]
      - text: Le Comte de Chambord (`Nouvelles soirées canadiennes/Juil-Août 1883/07`) [hierarchy]
      - text: Chronique (`Nouvelles soirées canadiennes/Juil-Août 1883/08`) [hierarchy]
1. `Les Pardaillan`: virtual, periodical-issue, 50 parts, 0 missing, order natural, unproofread
    - chapter: I (`Les Pardaillan/I`)
    - chapter: II (`Les Pardaillan/II`) [hierarchy]
    - chapter: III (`Les Pardaillan/III`) [hierarchy]
    - chapter: IV (`Les Pardaillan/IV`) [hierarchy]
    - chapter: IX (`Les Pardaillan/IX`) [hierarchy]
    - chapter: L (`Les Pardaillan/L`) [hierarchy]
    - chapter: V (`Les Pardaillan/V`) [hierarchy]
    - chapter: VI (`Les Pardaillan/VI`) [hierarchy]
    - chapter: VII (`Les Pardaillan/VII`) [hierarchy]
    - chapter: VIII (`Les Pardaillan/VIII`) [hierarchy]
    - chapter: X (`Les Pardaillan/X`) [hierarchy]
    - chapter: XI (`Les Pardaillan/XI`) [hierarchy]
    - … 38 more
1. `Art et pornographie`: virtual, book, 1 parts, 0 missing, order single, proofread
    - chapter: Partie I (`Art et pornographie/Partie I`)
1. `Le Gaulois`: virtual, periodical-issue, 1 parts, 0 missing, order single, unproofread
    - text: Karl Marx (`Le Gaulois/1883/03/17/Karl Marx`)
1. `Les évangiles et la critique (Meignan)`: virtual, book, 1 parts, 0 missing, order single, proofread
    - chapter: Préface (`Les évangiles et la critique (Meignan)/Préface`)
1. `Revue Musicale de Lyon 1904-03-23`: virtual, periodical-issue, 6 parts, 0 missing, order scan, proofread
    - text: Lieder français (`Revue Musicale de Lyon 1904-03-23/Lieder français`)
    - text: Musiques d'Église (`Revue Musicale de Lyon 1904-03-23/Musiques d'Église`) [hierarchy]
    - text: Chronique Lyonnaise (`Revue Musicale de Lyon 1904-03-23/Chronique Lyonnaise`) [hierarchy]
    - text: Hors Lyon (`Revue Musicale de Lyon 1904-03-23/Hors Lyon`) [hierarchy]
    - text: À travers la presse (`Revue Musicale de Lyon 1904-03-23/À travers la presse`) [hierarchy]
    - text: Nouvelles diverses (`Revue Musicale de Lyon 1904-03-23/Nouvelles diverses`) [hierarchy]
1. `Notre-Dame de Paris, 1844`: virtual, book, 2 parts, 0 missing, order scan, validated
    - chapter: Préface (`Notre-Dame de Paris, 1844/Préface`)
    - chapter: Note (`Notre-Dame de Paris, 1844/Note`) [hierarchy]
1. `Le Mouvement socialiste (Revue)`: virtual, periodical-issue, 2 parts, 0 missing, order natural, unproofread
    - text: janvier-juin 1899 (`Le Mouvement socialiste (Revue)/janvier-juin 1899`)
    - text: janvier-juin 1908 (`Le Mouvement socialiste (Revue)/janvier-juin 1908`) [hierarchy]
1. `Poètes et romanciers modernes de la France`: virtual, periodical-issue, 64 parts, 0 missing, order natural, proofread
    - text: Alexandre Dumas (`Poètes et romanciers modernes de la France/Alexandre Dumas`)
    - text: Alfred de Musset (`Poètes et romanciers modernes de la France/Alfred de Musset`) [hierarchy]
    - text: Alfred de Vigny (`Poètes et romanciers modernes de la France/Alfred de Vigny`) [hierarchy]
    - chapter: XIX. — Alfred de Vigny (Servitude et grandeur militaires) (`Poètes et romanciers modernes de la France/Alfred de Vigny-1835`) [hierarchy]
    - text: Alphonse Karr (`Poètes et romanciers modernes de la France/Alphonse Karr`) [hierarchy]
    - text: André Chénier (`Poètes et romanciers modernes de la France/André Chénier`) [hierarchy]
    - chapter: XV. — M. Ballanche (`Poètes et romanciers modernes de la France/Ballanche`) [hierarchy]
    - chapter: XVI. — M. de Balzac, La Recherche de l’absolu (`Poètes et romanciers modernes de la France/Balzac`) [hierarchy]
    - chapter: XIV. — Benjamin Constant (`Poètes et romanciers modernes de la France/Benjamin Constant`) [hierarchy]
    - text: Béranger (`Poètes et romanciers modernes de la France/Béranger`) [hierarchy]
    - text: Béranger (1832) (`Poètes et romanciers modernes de la France/Béranger (1832)`) [hierarchy]
    - text: Casimir Delavigne (`Poètes et romanciers modernes de la France/Casimir Delavigne`) [hierarchy]
    - … 52 more
1. `Somme contre les Gentils`: virtual, book, 2 parts, 0 missing, order scan, proofread
    - chapter: 10 (`Somme contre les Gentils/Livre premier/10`)
    - chapter: 11 (`Somme contre les Gentils/Livre premier/11`) [hierarchy]
1. `Dictionnaire de spiritualité, ascétique et mystique, doctrine et histoire`: virtual, reference, 13 parts, 0 missing, order natural, proofread
    - text: Adrien Bourdoise (`Dictionnaire de spiritualité, ascétique et mystique, doctrine et histoire/Adrien Bourdoise`)
    - text: Agere contra (`Dictionnaire de spiritualité, ascétique et mystique, doctrine et histoire/Agere contra`) [hierarchy]
    - text: ALIOTTI Girolamo (`Dictionnaire de spiritualité, ascétique et mystique, doctrine et histoire/ALIOTTI Girolamo`) [hierarchy]
    - text: Alzon (Emmanuel-Joseph-Marie-Maurice d’) (`Dictionnaire de spiritualité, ascétique et mystique, doctrine et histoire/Alzon (Emmanuel-Joseph-Marie-Maurice d’)`) [hierarchy]
    - text: Annibal Anfossi (`Dictionnaire de spiritualité, ascétique et mystique, doctrine et histoire/Annibal Anfossi`) [hierarchy]
    - text: Combes (Madame de) (`Dictionnaire de spiritualité, ascétique et mystique, doctrine et histoire/Combes (Madame de)`) [hierarchy]
    - text: Gilles-François de Beauvais (`Dictionnaire de spiritualité, ascétique et mystique, doctrine et histoire/Gilles-François de Beauvais`) [hierarchy]
    - text: Jean Croiset (`Dictionnaire de spiritualité, ascétique et mystique, doctrine et histoire/Jean Croiset`) [hierarchy]
    - text: Jean-André Gintowski Andrzejkiewicz (`Dictionnaire de spiritualité, ascétique et mystique, doctrine et histoire/Jean-André Gintowski Andrzejkiewicz`) [hierarchy]
    - text: Jean-Baptiste Comitin ou Comitino (`Dictionnaire de spiritualité, ascétique et mystique, doctrine et histoire/Jean-Baptiste Comitin ou Comitino`) [hierarchy]
    - text: Joseph Agnelli (`Dictionnaire de spiritualité, ascétique et mystique, doctrine et histoire/Joseph Agnelli`) [hierarchy]
    - text: Pierre Collot (`Dictionnaire de spiritualité, ascétique et mystique, doctrine et histoire/Pierre Collot`) [hierarchy]
    - … 1 more
1. `La Bastille`: virtual, collection, 1 parts, 0 missing, order single, proofread
    - text: Édition Garnier (`La Bastille/Édition Garnier`)
1. `Revue Musicale de Lyon 1904-01-12`: virtual, periodical-issue, 7 parts, 0 missing, order scan, proofread
    - text: César Franck (`Revue Musicale de Lyon 1904-01-12/César Franck`)
    - text: Les Musiciens lyonnais (`Revue Musicale de Lyon 1904-01-12/Les Musiciens lyonnais`) [hierarchy]
    - text: La Tétralogie (`Revue Musicale de Lyon 1904-01-12/La Tétralogie`) [hierarchy]
    - text: Chronique lyonnaise (`Revue Musicale de Lyon 1904-01-12/Chronique lyonnaise`) [hierarchy]
    - text: À Travers la presse (`Revue Musicale de Lyon 1904-01-12/À Travers la presse`) [hierarchy]
    - text: Nouvelles diverses (`Revue Musicale de Lyon 1904-01-12/Nouvelles diverses`) [hierarchy]
    - text: Bibliographie (`Revue Musicale de Lyon 1904-01-12/Bibliographie`) [hierarchy]
1. `Le Quart Livre`: virtual, book, 1 parts, 0 missing, order single, proofread
    - text: Édition Marty-Laveaux, 1870 (`Le Quart Livre/Édition Marty-Laveaux, 1870`)
1. `Poèmes civiques`: virtual, book, 2 parts, 0 missing, order scan, proofread
    - text: Les Muses d’État (`Poèmes civiques/Les Muses d’État`)
    - text: La Chasse aux Vaincus (`Poèmes civiques/La Chasse aux Vaincus`) [hierarchy]
1. `L’Afghanistan, les Russes aux portes de l’Inde`: virtual, book, 13 parts, 0 missing, order scan, validated
    - chapter: Avant-propos (`L’Afghanistan, les Russes aux portes de l’Inde/Avant-propos`)
    - text: Le pays afghan (`L’Afghanistan, les Russes aux portes de l’Inde/Le pays afghan`) [hierarchy]
    - text: Les routes stratégiques (`L’Afghanistan, les Russes aux portes de l’Inde/Les routes stratégiques`) [hierarchy]
    - text: Le quadrilatère (`L’Afghanistan, les Russes aux portes de l’Inde/Le quadrilatère`) [hierarchy]
    - text: La population afghane (`L’Afghanistan, les Russes aux portes de l’Inde/La population afghane`) [hierarchy]
    - text: Forces militaires et tactique des Afghans (`L’Afghanistan, les Russes aux portes de l’Inde/Forces militaires et tactique des Afghans`) [hierarchy]
    - text: Le passé de l’Afghanistan (`L’Afghanistan, les Russes aux portes de l’Inde/Le passé de l’Afghanistan`) [hierarchy]
    - text: La grande invasion (`L’Afghanistan, les Russes aux portes de l’Inde/La grande invasion`) [hierarchy]
    - text: Le coup de main de Merv (`L’Afghanistan, les Russes aux portes de l’Inde/Le coup de main de Merv`) [hierarchy]
    - text: Le plan de Skobeleff (`L’Afghanistan, les Russes aux portes de l’Inde/Le plan de Skobeleff`) [hierarchy]
    - text: Les Anglais dans l’Inde (`L’Afghanistan, les Russes aux portes de l’Inde/Les Anglais dans l’Inde`) [hierarchy]
    - text: Les Anglais dans l’Afghanistan (`L’Afghanistan, les Russes aux portes de l’Inde/Les Anglais dans l’Afghanistan`) [hierarchy]
    - … 1 more
1. `Si le grain ne meurt`: virtual, book, 6 parts, 0 missing, order scan, unproofread
    - chapter: I (`Si le grain ne meurt/Partie I/I`)
    - chapter: II (`Si le grain ne meurt/Partie I/II`) [hierarchy]
    - chapter: III (`Si le grain ne meurt/Partie I/III`) [hierarchy]
    - chapter: IV (`Si le grain ne meurt/Partie I/IV`) [hierarchy]
    - chapter: Appendice (`Si le grain ne meurt/Partie I/Appendice`) [hierarchy]
    - chapter: V (`Si le grain ne meurt/Partie II/V`) [hierarchy]
1. `ADN`: virtual, book, 1 parts, 0 missing, order single, unproofread
    - text: fol. 230v (`ADN/Registre B. 2185/fol. 230v`)

### E: subpage texts reached from a disambiguation or versions page (30,972)

1. text “Métempsycose et Polingéxésies” (`Le Diable à Paris/Série 1/Métempsycose et Polingéxésies`), part 27 of `Le Diable à Paris` (collection, 196 parts, 190 texts)
1. text “(Guerrier)” (`Poésies (Mallarmé, 1914, 8e éd.)/Petit Air (guerrier)`), part 45 of `Poésies (Mallarmé, 1914, 8e éd.)` (collection, 65 parts, 64 texts)
1. text “Mitre” (`Dictionnaire raisonné de l’architecture française du XIe au XVIe siècle/Mitre`), part 371 of `Dictionnaire raisonné de l’architecture française du XIe au XVIe siècle` (book, 530 parts, 520 texts)
1. text “hydroscopie s. f.” (`Dictionnaire français illustré de Larive et Fleury/hydroscopie s. f.`), part 96 of `Dictionnaire français illustré de Larive et Fleury` (reference, 198 parts, 197 texts)
1. text “« Un hymne harmonieux sort des feuilles du tremble… »” (`Les Quatre Vents de l’esprit/Le Livre lyrique/« Un hymne harmonieux sort des feuilles du tremble »`), part 62 of `Les Quatre Vents de l’esprit` (collection, 119 parts, 111 texts)
1. text “Athos (mont)” (`Grand dictionnaire universel du XIXe siècle/Athos (mont)`), part 1426 of `Grand dictionnaire universel du XIXe siècle` (reference, 5,834 parts, 5,801 texts)
1. text “Carriere” (`Encyclopédie méthodique/Art aratoire et du jardinage/Carriere`), part 241 of `Encyclopédie méthodique` (reference, 2,767 parts, 2,669 texts)
1. text “L A P ORTE” (`Alcools/La Porte`), part 19 of `Alcools` (collection, 50 parts, 49 texts)
1. text “La Rouille” (`Mademoiselle Fifi (recueil, Ollendorff 1898)/La Rouille`), part 3 of `Mademoiselle Fifi (recueil, Ollendorff 1898)` (collection, 18 parts, 18 texts)
1. text “Amérique (PARIS EN), ouvrage de M. Laboulaye” (`Grand dictionnaire universel du XIXe siècle/Amérique (PARIS EN), ouvrage de M. Laboulaye`), part 1219 of `Grand dictionnaire universel du XIXe siècle` (reference, 5,834 parts, 5,801 texts)
1. text “PHILIPPEAUX (Pierre), conventionnel français” (`Grand dictionnaire universel du XIXe siècle/PHILIPPEAUX (Pierre), conventionnel français`), part 4916 of `Grand dictionnaire universel du XIXe siècle` (reference, 5,834 parts, 5,801 texts)
1. text “MARIE D’ORLÉANS, fille de Louis-Philippe” (`Grand dictionnaire universel du XIXe siècle/MARIE D’ORLÉANS, fille de Louis-Philippe`), part 4252 of `Grand dictionnaire universel du XIXe siècle` (reference, 5,834 parts, 5,801 texts)
1. text “Ariston (de chio)” (`La Grande Encyclopédie, inventaire raisonné des sciences, des lettres, et des arts/Ariston (de chio)`), part 74 of `La Grande Encyclopédie, inventaire raisonné des sciences, des lettres, et des arts` (reference, 233 parts, 230 texts)
1. text “atracte s. m. (supplément)” (`Grand dictionnaire universel du XIXe siècle/atracte s. m. (supplément)`), part 1602 of `Grand dictionnaire universel du XIXe siècle` (reference, 5,834 parts, 5,801 texts)
1. text “Xerxès i” (`Dictionnaire universel d’histoire et de géographie Bouillet Chassang/Xerxès i`), part 1541 of `Dictionnaire universel d’histoire et de géographie Bouillet Chassang` (collection, 1,872 parts, 1,867 texts)
1. text “Jacques” (`Œuvres complètes de Béranger/Jacques`), part 180 of `Œuvres complètes de Béranger` (collection, 327 parts, 320 texts)
1. text “CHARLES VI, le Bien-Aimé, roi de France, fils du précédent” (`Grand dictionnaire universel du XIXe siècle/CHARLES VI, le Bien-Aimé, roi de France, fils du précédent`), part 2516 of `Grand dictionnaire universel du XIXe siècle` (reference, 5,834 parts, 5,801 texts)
1. text “CÉSAR (Caius Julius Strabon), frère du consul Lucius Julius, orateur et poète” (`Grand dictionnaire universel du XIXe siècle/CÉSAR (Caius Julius Strabon), frère du consul Lucius Julius, orateur et poète`), part 2797 of `Grand dictionnaire universel du XIXe siècle` (reference, 5,834 parts, 5,801 texts)
1. text “La ronde du furet” (`Les Enfantines du bon pays de France/La ronde du furet`), part 130 of `Les Enfantines du bon pays de France` (collection, 347 parts, 342 texts)
1. text “Allègre” (`Dictionnaire universel d’histoire et de géographie Bouillet Chassang/Allègre`), part 923 of `Dictionnaire universel d’histoire et de géographie Bouillet Chassang` (collection, 1,872 parts, 1,867 texts)
1. text “Hommes Illustres de la ville de Rome, abrégé historique, plus connu sous le titre latin, De viris illustribus urbis Romae” (`Grand dictionnaire universel du XIXe siècle/Hommes Illustres de la ville de Rome, abrégé historique, plus connu sous le titre latin, De viris illustribus urbis Romae`), part 3476 of `Grand dictionnaire universel du XIXe siècle` (reference, 5,834 parts, 5,801 texts)
1. text “La Veillée” (`Le Père Milon (recueil)/La Veillée`), part 9 of `Le Père Milon (recueil)` (collection, 23 parts, 23 texts)
1. text “Four-Saint-Germain (rue du)” (`Dictionnaire administratif et historique des rues de Paris et de ses monuments/Four-Saint-Germain (rue du)`), part 753 of `Dictionnaire administratif et historique des rues de Paris et de ses monuments` (reference, 2,142 parts, 2,133 texts)
1. text “Texte (le père Matthieu)” (`La France littéraire, ou Dictionnaire bibliographique/Texte (le père Matthieu)`), part 286 of `La France littéraire, ou Dictionnaire bibliographique` (book, 338 parts, 330 texts)
1. text “Maets” (`Dictionnaire historique et critique/11e éd., 1820/Maets`), part 638 of `Dictionnaire historique et critique` (reference, 1,016 parts, 993 texts)
1. text “Album à aiguilles” (`Cham - Albums du Charivari/Album à aiguilles`), part 2 of `Cham - Albums du Charivari` (book, 88 parts, 88 texts)
1. text “Le Cerf ſe voyant dans l’eau” (`Fables de La Fontaine (éd. Barbin)/2/Le Cerf se voyant dans l’eau`), part 80 of `Fables de La Fontaine (éd. Barbin)` (collection, 135 parts, 126 texts)
1. text “Boulangers (rue des)” (`Dictionnaire administratif et historique des rues de Paris et de ses monuments/Boulangers (rue des)`), part 265 of `Dictionnaire administratif et historique des rues de Paris et de ses monuments` (reference, 2,142 parts, 2,133 texts)
1. chapter “Lettre F” (`Dictionnaire infernal/6e éd., 1863/Lettre F`), part 1574 of `Dictionnaire infernal` (reference, 3,829 parts, 3,797 texts)
1. text “Vue” (`Encyclopédie méthodique/Beaux-Arts/Vue`), part 2280 of `Encyclopédie méthodique` (reference, 2,767 parts, 2,669 texts)

### all searchable subpage texts (chapter titles that the label patterns miss show up here) (350,590)

1. text “CHAP. XCIV” (`Tableau de Paris/094`), part 97 of `Tableau de Paris` (book, 1,064 parts, 1,047 texts)
1. text “L’enfant devant la glace s’étonne…” (`Poèmes français (Rilke)/Poèmes épars/L’enfant devant la glace s’étonne`), part 164 of `Poèmes français (Rilke)` (collection, 215 parts, 206 texts)
1. text “FÉVES” (`Dictionnaire portatif de cuisine, d’office, et de distillation/FÉVES`), part 198 of `Dictionnaire portatif de cuisine, d’office, et de distillation` (reference, 500 parts, 491 texts)
1. text “PHALANGE” (`Petit dictionnaire de la langue française (1860)/PHALANGE`), part 15288 of `Petit dictionnaire de la langue française (1860)` (reference, 20,406 parts, 20,367 texts)
1. text “À mon Tiercelet” (`Poèmes à Lou/À mon Tiercelet`), part 72 of `Poèmes à Lou` (collection, 76 parts, 74 texts)
1. text “Sur la piste des Woongas” (`Les Chasseurs d’or/III. Sur la piste des Woongas`), part 4 of `Les Chasseurs d’or` (book, 20 parts, 19 texts)
1. text “Valentin (Basile)” (`Dictionnaire infernal/6e éd., 1863/Valentin (Basile)`), part 3689 of `Dictionnaire infernal` (reference, 3,829 parts, 3,797 texts)
1. text “DESINTÉRESSÉMENT” (`L’Encyclopédie/1re édition/DESINTÉRESSÉMENT`), part 12267 of `L’Encyclopédie` (reference, 45,535 parts, 45,462 texts)
1. text “Conjurateurs” (`Dictionnaire infernal/6e éd., 1863/Conjurateurs`), part 1113 of `Dictionnaire infernal` (reference, 3,829 parts, 3,797 texts)
1. text “Ambrawer” (`Dictionnaire des patois romans de la Moselle/Articles/Ambrawer`), part 2074 of `Dictionnaire des patois romans de la Moselle` (reference, 21,247 parts, 21,230 texts)
1. text “.Zulma et Batoche” (`Les Bastonnais/03/05`), part 47 of `Les Bastonnais` (book, 81 parts, 76 texts)
1. text “VOLONTAIRE” (`Petit dictionnaire de la langue française (1836)/VOLONTAIRE`), part 23764 of `Petit dictionnaire de la langue française (1836)` (reference, 23,874 parts, 23,829 texts)
1. text “Les Saintes” (`Les Apparus dans mes chemins (Verhaeren)/15 Les Saintes`), part 15 of `Les Apparus dans mes chemins (Verhaeren)` (book, 18 parts, 18 texts)
1. text “La Pendule de Bougival” (`Les Contes du lundi/La Pendule de Bougival`), part 8 of `Les Contes du lundi` (book, 41 parts, 40 texts)
1. text “CARELSCROON ou CARLSCROON” (`Dictionnaire de Trévoux/6e édition, 1771/CARELSCROON ou CARLSCROON`), part 12437 of `Dictionnaire de Trévoux` (reference, 32,173 parts, 32,111 texts)
1. text “LARGESSE” (`Petit dictionnaire de la langue française (1836)/LARGESSE`), part 13009 of `Petit dictionnaire de la langue française (1836)` (reference, 23,874 parts, 23,829 texts)
1. text “BELALCAZAR” (`L’Encyclopédie/1re édition/BELALCAZAR`), part 4726 of `L’Encyclopédie` (reference, 45,535 parts, 45,462 texts)
1. text “PERIN-KARA” (`L’Encyclopédie/1re édition/PERIN-KARA`), part 29674 of `L’Encyclopédie` (reference, 45,535 parts, 45,462 texts)
1. text “À Philippe Leparfait” (`Correspondance de Gustave Flaubert/Tome 6/1357`), part 1366 of `Correspondance de Gustave Flaubert` (book, 2,020 parts, 2,002 texts)
1. text “ANÉMOGRAPHIE” (`Dictionnaire classique de la langue française (Bescherelle Jeune, 1880)/Articles/ANÉMOGRAPHIE`), part 2854 of `Dictionnaire classique de la langue française (Bescherelle Jeune, 1880)` (reference, 3,078 parts, 3,073 texts)
1. text “OBSCUR, OBSCURE” (`Petit dictionnaire de la langue française (1860)/OBSCUR, OBSCURE`), part 13968 of `Petit dictionnaire de la langue française (1860)` (reference, 20,406 parts, 20,367 texts)
1. text “Hhieulon” (`Dictionnaire des patois romans de la Moselle/Articles/Hhieulon`), part 11767 of `Dictionnaire des patois romans de la Moselle` (reference, 21,247 parts, 21,230 texts)
1. text “ESTOQUIAU” (`Dictionnaire de Trévoux/6e édition, 1771/ESTOQUIAU`), part 24528 of `Dictionnaire de Trévoux` (reference, 32,173 parts, 32,111 texts)
1. text “Le Buste” (`L’Âme nue/Le Buste`), part 4 of `L’Âme nue` (book, 128 parts, 124 texts)
1. text “Empave” (`Dictionnaire d’argot ou Guide des gens du monde (2e éd.)/Articles/Empave`), part 236 of `Dictionnaire d’argot ou Guide des gens du monde (2e éd.)` (reference, 832 parts, 782 texts)
1. text “Calinaire” (`Néologie, ou Vocabulaire de mots nouveaux/Calinaire`), part 429 of `Néologie, ou Vocabulaire de mots nouveaux` (book, 2,835 parts, 2,807 texts)
1. text “Vèsker” (`Dictionnaire des patois romans de la Moselle/Articles/Vèsker`), part 20850 of `Dictionnaire des patois romans de la Moselle` (reference, 21,247 parts, 21,230 texts)
1. text “C2” (`Esprit des lois (1777)/L5/C2`), part 40 of `Esprit des lois (1777)` (book, 620 parts, 616 texts)
1. text “Crā” (`Dictionnaire des patois romans de la Moselle/Articles/Crā`), part 6505 of `Dictionnaire des patois romans de la Moselle` (reference, 21,247 parts, 21,230 texts)
1. text “L’Habitation” (`Les Marrons/02`), part 2 of `Les Marrons` (book, 14 parts, 14 texts)

### C: oversized (600)

1. `Lettres persanes (éd. Laboulaye, 1875)`: subpages, book, 3 parts, 0 missing, order scan, unproofread
    - front: Lettres persanes (éd. Laboulaye, 1875)
    - chapter: Préface de l’éditeur (`Lettres persanes (éd. Laboulaye, 1875)/Préface de l’éditeur`) [hierarchy]
    - text: Quelques réflexions (`Lettres persanes (éd. Laboulaye, 1875)/Quelques réflexions`) [hierarchy]
    - chapter: Table analytique et alphabétique (`Lettres persanes (éd. Laboulaye, 1875)/Table analytique et alphabétique`) [hierarchy]
1. `Li romans de Bauduin de Sebourc`: linked, book, 30 parts, 0 missing, order toc, proofread
    - toc: Li romans de Bauduin de Sebourc
    - text: LE ROMAN DE BAUDUIN DE SEBOURC (`Bauduin de Sebourc`) [container]
    - text: , errata, corrections proposées (`Bauduin de Sebourc/AVERTISSEMENT`) [container]
    - chapter: CHANT I (`Bauduin de Sebourc/Chant I`) [container]
    - chapter: CHANT II (`Bauduin de Sebourc/Chant II`) [container]
    - chapter: CHANT III (`Bauduin de Sebourc/Chant III`) [container]
    - chapter: CHANT IV (`Bauduin de Sebourc/Chant IV`) [container]
    - chapter: CHANT V (`Bauduin de Sebourc/Chant V`) [container]
    - chapter: CHANT VI (`Bauduin de Sebourc/Chant VI`) [container]
    - chapter: CHANT VII (`Bauduin de Sebourc/Chant VII`) [container]
    - chapter: CHANT VIII (`Bauduin de Sebourc/Chant VIII`) [container]
    - chapter: CHANT IX (`Bauduin de Sebourc/Chant IX`) [container]
    - … 19 more
1. `Maison rustique du XIXe siècle`: virtual, book, 77 parts, 75 missing, order single, unproofread
    - volume: éd. 1844 (`Maison rustique du XIXe siècle/éd. 1844`)
      - volume: Calendriers (`Maison rustique du XIXe siècle/éd. 1844/Calendriers`) [container]
        - text: Calendrier du jardinier (`Maison rustique du XIXe siècle/éd. 1844/Calendrier du jardinier`) [container]
        - text: Calendrier du forestier (`Maison rustique du XIXe siècle/éd. 1844/Calendrier du forestier`) [container]
        - text: Calendrier du magnanier (`Maison rustique du XIXe siècle/éd. 1844/Calendrier du magnanier`) [container]
      - text: Jardinage en Europe (`Maison rustique du XIXe siècle/éd. 1844/Jardinage en Europe`) [container]
      - volume: Livre 1 (`Maison rustique du XIXe siècle/éd. 1844/Livre 1`) [container]
        - text: ch. 1 (`Maison rustique du XIXe siècle/éd. 1844/Livre 1/ch. 1`) [container]
        - text: . (`Maison rustique du XIXe siècle/éd. 1844/Livre 1/ch. 2`) [container]
        - text: . (`Maison rustique du XIXe siècle/éd. 1844/Livre 1/ch. 3`) [container]
        - text: . (`Maison rustique du XIXe siècle/éd. 1844/Livre 1/ch. 4`) [container]
        - text: . (`Maison rustique du XIXe siècle/éd. 1844/Livre 1/ch. 5`) [container]
    - … 65 more
1. `Le Reveille-matin des François`: mixed, book, 10 parts, 0 missing, order toc, proofread
    - toc: Le Reveille-matin des François
    - text: Dédicace aux français (`Le Reveille-matin des François/Dédicace aux français`) [container]
    - text: Dédicace Elizabeth Reine d'Angleterre (`Le Reveille-matin des François/Dédicace Elizabeth Reine d'Angleterre`) [container]
    - text: Epistre aux polonois (`Le Reveille-matin des François/Epistre aux polonois`) [container]
    - chapter: Lettre duc de Guise (`Reveille-matin des François/Lettre duc de Guise`) [container]
    - text: Dialogisme (`Reveille-matin des François/Dialogisme`) [container]
    - text: Argument premier dialogue (`Reveille-matin des François/Argument premier dialogue`) [container]
    - text: Premier dialogue (`Reveille-matin des François/Premier dialogue`) [container]
    - text: Second dialogue-Couverture (`Reveille-matin des François/Second dialogue-Couverture`) [container]
    - text: Second dialogue-Argument (`Reveille-matin des François/Second dialogue-Argument`) [container]
    - text: Second dialogue (`Reveille-matin des François/Second dialogue`) [container]
1. `Le Nouveau Gulliver (Desfontaines)`: single, book, 1 parts, 0 missing, order –, proofread
    - text: Le Nouveau Gulliver (Desfontaines)
1. `Collections, F.W. Ross, La Haye, A. Durand, Paris, et al.`: single, book, 1 parts, 0 missing, order –, unproofread
    - text: Collections, F.W. Ross, La Haye, A. Durand, Paris, et al.
1. `L’Unique et sa propriété : Deuxième Partie — Moi`: single, book, 1 parts, 0 missing, order –, proofread
    - text: L’Unique et sa propriété : Deuxième Partie — Moi
1. `Colette - Œuvres complètes (éd. Le Fleuron)`: virtual, book, 1 parts, 0 missing, order single, unproofread
    - text: Claudine à l’école (`Colette - Œuvres complètes (éd. Le Fleuron)/Claudine à l’école`)
1. `Voyages en France en 1787, 1788 et 1789`: subpages, book, 8 parts, 0 missing, order toc, validated
    - toc: Voyages en France en 1787, 1788 et 1789
    - text: Avertissement de la présente édition (`Voyages en France en 1787, 1788 et 1789/Avertissement de la présente édition`) [container]
    - text: Avertissement du traducteur (`Voyages en France en 1787, 1788 et 1789/Avertissement du traducteur`) [container]
    - text: Notice sur Young (`Voyages en France en 1787, 1788 et 1789/Notice sur Young`) [container]
    - chapter: Préface de l’auteur (`Voyages en France en 1787, 1788 et 1789/Préface de l’auteur`) [container]
    - text: Premier voyage (`Voyages en France en 1787, 1788 et 1789/Premier voyage`) [container]
    - text: Second voyage (`Voyages en France en 1787, 1788 et 1789/Second voyage`) [container]
    - text: Troisième voyage (`Voyages en France en 1787, 1788 et 1789/Troisième voyage`) [container]
    - text: Index et errata (`Voyages en France en 1787, 1788 et 1789/Index et errata`) [container]
1. `Tableau de la Géographie de la France`: single, book, 1 parts, 0 missing, order –, proofread
    - text: Tableau de la Géographie de la France
1. `Œuvres de Platon, traduites par Victor Cousin`: linked, collection, 28 parts, 3 missing, order natural, proofread
    - front: Œuvres de Platon, traduites par Victor Cousin
    - text: Apologie de Socrate (`Apologie de Socrate (trad. Cousin)`) [container]
    - text: Argument philosophique (`Apologie de Socrate (trad. Cousin)/Argument philosophique`) [container]
    - text: Dialogue (`Apologie de Socrate (trad. Cousin)/Dialogue`) [container]
    - text: Axiochus (`Axiochus (trad. Cousin)`) [container]
    - text: Clitophon (`Clitophon (trad. Cousin)`) [container]
    - text: Criton (`Criton (trad. Cousin)`) [container]
    - text: Argument philosophique (`Criton (trad. Cousin)/Argument philosophique`) [container]
    - text: Dialogue (`Criton (trad. Cousin)/Dialogue`) [container]
    - text: De la Vertu (`De la Vertu (trad. Cousin)`) [container]
    - text: Démodocus (`Démodocus (trad. Cousin)`) [container]
    - text: Euthyphron (`Euthyphron (trad. Cousin)`) [container]
    - … 17 more
1. `La Foire aux vanités`: subpages, book, 69 parts, 0 missing, order toc-partial, proofread
    - toc: La Foire aux vanités
    - chapter: Préface du traducteur (`La Foire aux vanités/Préface du traducteur`) [container]
    - text: Chiswick Mall (`La Foire aux vanités/01`) [container]
    - text: Où miss Sharp et miss Sedley se disposent à entrer en campagne (`La Foire aux vanités/02`) [container]
    - text: Rebecca en présence de l’ennemi (`La Foire aux vanités/03`) [container]
    - text: La bourse de soie verte (`La Foire aux vanités/04`) [container]
    - text: L’ami Dobbin (`La Foire aux vanités/05`) [container]
    - text: Le Vauxhall (`La Foire aux vanités/06`) [container]
    - text: Crawley de Crawley-la-Reine (`La Foire aux vanités/07`) [container]
    - text: Tout confidentiel (`La Foire aux vanités/08`) [container]
    - text: Portraits de famille (`La Foire aux vanités/09`) [container]
    - text: Miss Sharp commence à se faire des amis (`La Foire aux vanités/10`) [container]
    - … 58 more
1. `Zayde`: subpages, book, 2 parts, 0 missing, order scan, unproofread
    - front: Zayde
    - chapter: Première partie (`Zayde/Première partie`) [hierarchy]
    - chapter: Seconde partie (`Zayde/Seconde partie`) [hierarchy]
1. `L’Arcadie`: single, book, 1 parts, 0 missing, order –, unproofread
    - text: L’Arcadie
1. `George Sand, sa vie et ses œuvres`: subpages, book, 46 parts, 3 missing, order toc-partial, unproofread
    - toc: George Sand, sa vie et ses œuvres
    - text: Portrait de George Sand (`George Sand, sa vie et ses œuvres/Portrait`) [container]
    - chapter: Table des matières des quatre volumes (`George Sand, sa vie et ses œuvres/Table des matières`) [container]
    - volume: volume 1. (`George Sand, sa vie et ses œuvres/1`) [hierarchy]
      - chapter: 1 (`George Sand, sa vie et ses œuvres/1/1`) [container]
      - chapter: 2 (`George Sand, sa vie et ses œuvres/1/2`) [container]
      - chapter: 3 (`George Sand, sa vie et ses œuvres/1/3`) [container]
      - chapter: 4 (`George Sand, sa vie et ses œuvres/1/4`) [container]
      - chapter: 5 (`George Sand, sa vie et ses œuvres/1/5`) [container]
      - chapter: 6 (`George Sand, sa vie et ses œuvres/1/6`) [container]
      - chapter: 7 (`George Sand, sa vie et ses œuvres/1/7`) [hierarchy]
      - chapter: Introduction (`George Sand, sa vie et ses œuvres/1/Introduction`) [container]
    - … 35 more
1. `Affaire des déportés de la Martinique`: single, book, 1 parts, 0 missing, order –, proofread
    - text: Affaire des déportés de la Martinique
1. `Les Mystères de Londres`: subpages, book, 130 parts, 0 missing, order toc, proofread
    - front: Les Mystères de Londres
    - volume: Première partie : Les Gentilshommes de la nuit (`Les Mystères de Londres/1`) [hierarchy]
      - text: Par le brouillard (`Les Mystères de Londres/1/01`) [container]
      - text: Une quête à Temple-Church (`Les Mystères de Londres/1/02`) [container]
      - text: L’avènement d’un lion (`Les Mystères de Londres/1/03`) [container]
      - text: Comment l’amour vient en rêvant (`Les Mystères de Londres/1/04`) [container]
      - text: Le bal (`Les Mystères de Londres/1/05`) [container]
      - text: La fille du pendu (`Les Mystères de Londres/1/06`) [container]
      - text: Edward and C (`Les Mystères de Londres/1/07`) [container]
      - text: Des deux côtés de la rue (`Les Mystères de Londres/1/08`) [container]
      - text: Le centre d’une toile d’araignée (`Les Mystères de Londres/1/09`) [container]
      - text: Faits et gestes de Bob-Lantern (`Les Mystères de Londres/1/10`) [container]
    - … 119 more
1. `Girart de Roussillon (Manuscrit d’Oxford) avec traduction Paul Meyer - 7`: single, periodical-issue, 1 parts, 0 missing, order –, proofread
    - text: Girart de Roussillon (Manuscrit d’Oxford) avec traduction Paul Meyer - 7
1. `Commonitoire (Vincent de Lérins)`: subpages, collection, 1 parts, 1 missing, order single, unproofread
    - toc: Commonitoire (Vincent de Lérins)
    - text: Texte du « Commonitoire » (`Commonitoire (Vincent de Lérins)/2`) [hierarchy]
1. `Doon de la Roche`: subpages, book, 5 parts, 0 missing, order toc, proofread
    - toc: Doon de la Roche
    - chapter: Introduction (`Doon de la Roche/Introduction`) [container]
    - text: Texte du poème de Doon de la Roche (`Doon de la Roche/Poème`) [container]
    - text: Glossaire (`Doon de la Roche/Glossaire`) [container]
    - text: Index des noms propres (`Doon de la Roche/Index`) [container]
    - text: Errata, corrections et additions (`Doon de la Roche/Errata`) [container]
1. `Ma Vie (Wagner)`: subpages, book, 11 parts, 0 missing, order toc-partial, proofread
    - front: Ma Vie (Wagner)
    - chapter: Avant-propos (`Ma Vie (Wagner)/Avant-propos`) [container]
    - chapter: PREMIÈRE PARTIE (1813-1842) (`Ma Vie (Wagner)/01`) [container]
    - chapter: DEUXIÈME PARTIE (1842-1850) (Dresde) (`Ma Vie (Wagner)/02`) [container]
    - text: Index alphabétique (`Ma Vie (Wagner)/Index alphabétique`) [container]
    - chapter: Table par années (`Ma Vie (Wagner)/Table par années`) [container]
    - chapter: Table des matières (`Ma Vie (Wagner)/Table des matières`) [container]
    - chapter: 03 (`Ma Vie (Wagner)/03`) [container]
    - chapter: 04 (`Ma Vie (Wagner)/04`) [container]
    - chapter: Tome 1 (`Ma Vie (Wagner)/Texte entier/Tome 1`) [container]
    - chapter: Tome 2 (`Ma Vie (Wagner)/Texte entier/Tome 2`) [container]
    - chapter: Tome 3 (`Ma Vie (Wagner)/Texte entier/Tome 3`) [container]
1. `Dictionnaire de l’Académie française`: virtual, reference, 416 parts, 0 missing, order natural, proofread
    - text: Abbreviations (`Dictionnaire de l’Académie française/1re éd., 1694/Abbreviations`)
    - text: Epitre (`Dictionnaire de l’Académie française/1re éd., 1694/Epitre`) [hierarchy]
    - text: Index alphabétique - A (`Dictionnaire de l’Académie française/1re éd., 1694/Index alphabétique - A`) [hierarchy]
    - text: Liste (`Dictionnaire de l’Académie française/1re éd., 1694/Liste`) [hierarchy]
    - text: Preface (`Dictionnaire de l’Académie française/1re éd., 1694/Preface`) [hierarchy]
    - text: Privilege (`Dictionnaire de l’Académie française/1re éd., 1694/Privilege`) [hierarchy]
    - chapter: Tome 1 (`Dictionnaire de l’Académie française/1re éd., 1694/Tome 1`) [hierarchy]
    - chapter: Tome 1b (`Dictionnaire de l’Académie française/1re éd., 1694/Tome 1b`) [hierarchy]
    - chapter: Tome 2a (`Dictionnaire de l’Académie française/1re éd., 1694/Tome 2a`) [hierarchy]
    - chapter: Tome 2b (`Dictionnaire de l’Académie française/1re éd., 1694/Tome 2b`) [hierarchy]
    - volume: 2e éd., 1718 (`Dictionnaire de l’Académie française/2e éd., 1718`) [hierarchy]
      - text: A (`Dictionnaire de l’Académie française/2e éd., 1718/A`) [container]
    - … 404 more
1. `Satyricon (Heguin)`: subpages, book, 9 parts, 0 missing, order toc, proofread
    - toc: Satyricon (Heguin)
    - text: Avertissement du traducteur (`Satyricon (Heguin)/Avertissement`) [container]
    - text: Recherches sceptiques sur le Satyricon et son auteur (`Satyricon (Heguin)/Recherches sceptiques`) [container]
    - text: Le Satyricon (`Satyricon (Heguin)/Le Satyricon`) [container]
    - chapter: Notes (`Satyricon (Heguin)/Notes`) [container]
    - volume: Table des chapitres (`Satyricon (Heguin)/Chapitres`) [container]
      - chapter: 1 (`Satyricon (Heguin)/Encolpe`) [container]
      - chapter: 27 (`Satyricon (Heguin)/Trimalchion`) [container]
      - chapter: 79 (`Satyricon (Heguin)/Eumolpe`) [container]
      - text: Fr. 1 (`Satyricon (Heguin)/Fragments`) [container]
1. `Précis de l’abolition de l’esclavage dans les colonies anglaises`: subpages, book, 6 parts, 0 missing, order toc, proofread
    - front: Précis de l’abolition de l’esclavage dans les colonies anglaises
    - text: . (`Précis de l’abolition de l’esclavage dans les colonies anglaises/Introduction`) [container]
    - chapter: Acte d’abolition de l’esclavage dans les colonies anglaises (`Précis de l’abolition de l’esclavage dans les colonies anglaises/1`) [container]
    - text: Résumé des ordres généraux transmis, pour l’exécution de cet acte, par le secrétaire d’État des colonies (`Précis de l’abolition de l’esclavage dans les colonies anglaises/2`) [container]
    - text: Précis de l’application et des effets de l’acte d’abolition de l’esclavage à la Jamaïque, à Antique, à la Guyane et à Maurice (`Précis de l’abolition de l’esclavage dans les colonies anglaises/3`) [container]
    - text: Principaux actes rendus par la métropole, ainsi que par les législatures ou conseil de la Jamaïque, d’Antique, de la Guyane et de Maurice (`Précis de l’abolition de l’esclavage dans les colonies anglaises/4`) [container]
    - chapter: Table chronologique des actes contenus dans ce volume (`Précis de l’abolition de l’esclavage dans les colonies anglaises/5`) [container]
1. `Proudhon - De la Capacité politique des classes ouvrières`: virtual, book, 23 parts, 0 missing, order scan, proofread
    - text: Texte complet (`Proudhon - De la Capacité politique des classes ouvrières/Texte complet`)
    - text: II,5 (`Proudhon - De la Capacité politique des classes ouvrières/II,5`) [hierarchy]
    - text: II,6 (`Proudhon - De la Capacité politique des classes ouvrières/II,6`) [hierarchy]
    - text: II,7 (`Proudhon - De la Capacité politique des classes ouvrières/II,7`) [hierarchy]
    - text: II,8 (`Proudhon - De la Capacité politique des classes ouvrières/II,8`) [hierarchy]
    - text: II,9 (`Proudhon - De la Capacité politique des classes ouvrières/II,9`) [hierarchy]
    - text: II,10 (`Proudhon - De la Capacité politique des classes ouvrières/II,10`) [hierarchy]
    - text: II,11 (`Proudhon - De la Capacité politique des classes ouvrières/II,11`) [hierarchy]
    - text: II,12 (`Proudhon - De la Capacité politique des classes ouvrières/II,12`) [hierarchy]
    - text: II,13 (`Proudhon - De la Capacité politique des classes ouvrières/II,13`) [hierarchy]
    - text: II,14 (`Proudhon - De la Capacité politique des classes ouvrières/II,14`) [hierarchy]
    - text: II,15 (`Proudhon - De la Capacité politique des classes ouvrières/II,15`) [hierarchy]
    - … 11 more
1. `Abrégé de l’histoire générale des voyages`: subpages, book, 225 parts, 0 missing, order toc, proofread
    - toc: Abrégé de l’histoire générale des voyages
    - volume: Tome I (`Abrégé de l’histoire générale des voyages/Tome I`) [hierarchy]
      - text: Avertissement (`Abrégé de l’histoire générale des voyages/Tome I/Avertissement`) [container]
      - text: Notice sur J.-F. Laharpe (`Abrégé de l’histoire générale des voyages/Tome I/Notice sur J.-F. Laharpe`) [container]
      - text: Précis de l’Histoire des Voyages et Découvertes (`Abrégé de l’histoire générale des voyages/Tome I/Précis`) [container]
      - chapter: Préface (`Abrégé de l’histoire générale des voyages/Tome I/Préface`) [container]
      - chapter: CHAPITRE PREMIER (`Abrégé de l’histoire générale des voyages/Tome I/Première partie/Livre I/Chapitre I`) [container]
      - text: CHAP. II (`Abrégé de l’histoire générale des voyages/Tome I/Première partie/Livre I/Chapitre II`) [container]
      - text: CHAP. III (`Abrégé de l’histoire générale des voyages/Tome I/Première partie/Livre I/Chapitre III`) [container]
      - chapter: CHAPITRE PREMIER (`Abrégé de l’histoire générale des voyages/Tome I/Première partie/Livre II/Chapitre I`) [container]
      - text: CHAP. II (`Abrégé de l’histoire générale des voyages/Tome I/Première partie/Livre II/Chapitre II`) [container]
      - text: CHAP. III (`Abrégé de l’histoire générale des voyages/Tome I/Première partie/Livre II/Chapitre III`) [hierarchy]
    - … 214 more
1. `Catégories (Aristote, traduction Saint-Hilaire)`: single, book, 1 parts, 0 missing, order –, proofread
    - text: Catégories (Aristote, traduction Saint-Hilaire)
1. `Le Râmâyana (trad. Fauche)`: subpages, book, 3 parts, 0 missing, order toc-partial, validated
    - toc: Le Râmâyana (trad. Fauche)
    - chapter: Tome 1 (`Le Râmâyana (trad. Fauche)/Tome 1`) [container]
    - chapter: Tome 2 (`Le Râmâyana (trad. Fauche)/Tome 2`) [container]
    - chapter: Livre 1 (`Le Râmâyana (trad. Fauche)/Livre 1`) [container]
1. `La Tentation de saint Antoine – Éd. L. Conard (1910)`: subpages, book, 11 parts, 0 missing, order toc, proofread
    - toc: La Tentation de saint Antoine – Éd. L. Conard (1910)
    - volume: Version de 1872 (`La Tentation de saint Antoine – Éd. L. Conard (1910)/La Tentation de saint Antoine`) [container]
      - text: &nbsp;I.&nbsp; (`La Tentation de saint Antoine – Éd. L. Conard (1910)/La Tentation de saint Antoine/I`) [container]
      - text: &nbsp; II.&nbsp; (`La Tentation de saint Antoine – Éd. L. Conard (1910)/La Tentation de saint Antoine/II`) [container]
      - text: &nbsp;III.&nbsp; (`La Tentation de saint Antoine – Éd. L. Conard (1910)/La Tentation de saint Antoine/III`) [container]
      - text: &nbsp;IV.&nbsp; (`La Tentation de saint Antoine – Éd. L. Conard (1910)/La Tentation de saint Antoine/IV`) [container]
      - text: &nbsp;V.&nbsp; (`La Tentation de saint Antoine – Éd. L. Conard (1910)/La Tentation de saint Antoine/V`) [container]
      - text: &nbsp;VI.&nbsp; (`La Tentation de saint Antoine – Éd. L. Conard (1910)/La Tentation de saint Antoine/VI`) [container]
      - text: &nbsp;VII.&nbsp; (`La Tentation de saint Antoine – Éd. L. Conard (1910)/La Tentation de saint Antoine/VII`) [container]
    - text: Version de 1849 (`La Tentation de saint Antoine – Éd. L. Conard (1910)/La Tentation de saint Antoine (1849)`) [container]
    - text: Version de 1856 (`La Tentation de saint Antoine – Éd. L. Conard (1910)/La Tentation de saint Antoine (1856)`) [container]
    - chapter: Notes (`La Tentation de saint Antoine – Éd. L. Conard (1910)/Notes`) [container]
1. `La Bossue`: single, book, 1 parts, 0 missing, order –, proofread
    - text: La Bossue

### G: reference works (219)

1. `Dictionnaire historique, littéraire et critique de Barral`: subpages, reference, 2 parts, 0 missing, order natural, unproofread
    - front: Dictionnaire historique, littéraire et critique de Barral
    - text: Aristote (`Dictionnaire historique, littéraire et critique de Barral/Aristote`) [container]
    - text: Zoroastre (`Dictionnaire historique, littéraire et critique de Barral/Zoroastre`) [container]
1. `Tables biographiques et bibliographiques`: subpages, reference, 1 parts, 3 missing, order single, proofread
    - toc: Tables biographiques et bibliographiques
    - chapter: Tome 1 (`Tables biographiques et bibliographiques/Tome 1`) [container]
1. `Dictionnaire raisonné d’architecture et des sciences et arts qui s’y rattachent`: single, reference, 1 parts, 0 missing, order –, unproofread
    - text: Dictionnaire raisonné d’architecture et des sciences et arts qui s’y rattachent
1. `Manuel lexique ou Dictionnaire portatif des mots françois`: virtual, reference, 1 parts, 0 missing, order single, proofread
    - text: 1re éd., 1750 (`Manuel lexique ou Dictionnaire portatif des mots françois/1re éd., 1750`)
1. `Petit dictionnaire ministériel`: subpages, reference, 250 parts, 0 missing, order scan, proofread
    - toc: Petit dictionnaire ministériel
    - chapter: Introduction (`Petit dictionnaire ministériel/Texte entier/Introduction`) [hierarchy]
    - text: Abandon (`Petit dictionnaire ministériel/Abandon`) [hierarchy]
    - text: Abbé (`Petit dictionnaire ministériel/Abbé`) [hierarchy]
    - text: A (`Petit dictionnaire ministériel/Texte entier/A`) [hierarchy]
    - text: Aborder (`Petit dictionnaire ministériel/Aborder`) [hierarchy]
    - text: Abrégé (`Petit dictionnaire ministériel/Abrégé`) [hierarchy]
    - text: Abus (`Petit dictionnaire ministériel/Abus`) [hierarchy]
    - text: Abîme (`Petit dictionnaire ministériel/Abîme`) [hierarchy]
    - text: Académie (`Petit dictionnaire ministériel/Académie`) [hierarchy]
    - text: Accepter (`Petit dictionnaire ministériel/Accepter`) [hierarchy]
    - text: Accord (`Petit dictionnaire ministériel/Accord`) [hierarchy]
    - … 239 more
1. `Dictionnaire de la langue française du seizième siècle`: subpages, reference, 336 parts, 5 missing, order natural, unproofread
    - front: Dictionnaire de la langue française du seizième siècle
    - chapter: Tome 1 - A - Appoincté (`Dictionnaire de la langue française du seizième siècle/A1`) [container]
    - chapter: Tome 1 - Appoinctement - Azurin (`Dictionnaire de la langue française du seizième siècle/A2`) [container]
    - text: A (marqué de l’) (`Dictionnaire de la langue française du seizième siècle/A (marqué de l’)`) [container]
    - text: A (préposition) (`Dictionnaire de la langue française du seizième siècle/A (préposition)`) [container]
    - text: Aage (`Dictionnaire de la langue française du seizième siècle/Aage`) [container]
    - text: Abac (d’) et d’aboc (`Dictionnaire de la langue française du seizième siècle/Abac (d’) et d’aboc`) [container]
    - text: Abai (`Dictionnaire de la langue française du seizième siècle/Abai`) [container]
    - text: Abalourdir (`Dictionnaire de la langue française du seizième siècle/Abalourdir`) [container]
    - text: Abandon (`Dictionnaire de la langue française du seizième siècle/Abandon`) [container]
    - text: Abandonnement (`Dictionnaire de la langue française du seizième siècle/Abandonnement`) [container]
    - text: Abandonner (`Dictionnaire de la langue française du seizième siècle/Abandonner`) [container]
    - … 325 more
1. `Le langaige du Bresil`: single, reference, 1 parts, 0 missing, order –, proofread
    - text: Le langaige du Bresil
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
1. `Dictionnaire wallon-français (Lobet)`: subpages, reference, 248 parts, 19 missing, order natural, proofread
    - toc: Dictionnaire wallon-français (Lobet)
    - volume: Index alphabétique - A (`Dictionnaire wallon-français (Lobet)/Index alphabétique - A`) [hierarchy]
      - text: A (`Dictionnaire wallon-français (Lobet)/A`) [container]
      - text: A BAH (`Dictionnaire wallon-français (Lobet)/A BAH`) [container]
      - text: ABA (`Dictionnaire wallon-français (Lobet)/ABA`) [container]
      - text: ABAGUÉ (`Dictionnaire wallon-français (Lobet)/ABAGUÉ`) [container]
      - text: ABAGUEG (`Dictionnaire wallon-français (Lobet)/ABAGUEG`) [container]
      - text: ABAHEG (`Dictionnaire wallon-français (Lobet)/ABAHEG`) [container]
      - text: ABAHI (`Dictionnaire wallon-français (Lobet)/ABAHI`) [container]
      - text: ABAIE (`Dictionnaire wallon-français (Lobet)/ABAIE`) [container]
      - text: ABAIEDON (`Dictionnaire wallon-français (Lobet)/ABAIEDON`) [container]
      - text: ABAIELUMAIN (`Dictionnaire wallon-français (Lobet)/ABAIELUMAIN`) [container]
    - … 237 more
1. `Petit dictionnaire de la langue française (Hocquart, 1831)`: subpages, reference, 7,352 parts, 19 missing, order scan, proofread
    - toc: Petit dictionnaire de la langue française (Hocquart, 1831)
    - chapter: Table des abréviations (`Petit dictionnaire de la langue française (Hocquart, 1831)/Table des abréviations`) [hierarchy]
    - text: Ab Abrupto, Ex Abrupto (`Petit dictionnaire de la langue française (Hocquart, 1831)/Articles/Ab Abrupto, Ex Abrupto`) [hierarchy]
    - text: Ab ovo (`Petit dictionnaire de la langue française (Hocquart, 1831)/Articles/Ab ovo`) [hierarchy]
    - text: Aborigènes (`Petit dictionnaire de la langue française (Hocquart, 1831)/Articles/Aborigènes`) [hierarchy]
    - text: Abornement (`Petit dictionnaire de la langue française (Hocquart, 1831)/Articles/Abornement`) [hierarchy]
    - text: Aborner (`Petit dictionnaire de la langue française (Hocquart, 1831)/Articles/Aborner`) [hierarchy]
    - text: Abortif, Abortive (`Petit dictionnaire de la langue française (Hocquart, 1831)/Articles/Abortif, Abortive`) [hierarchy]
    - text: Abouchement (`Petit dictionnaire de la langue française (Hocquart, 1831)/Articles/Abouchement`) [hierarchy]
    - text: Aboucher (`Petit dictionnaire de la langue française (Hocquart, 1831)/Articles/Aboucher`) [hierarchy]
    - text: About (`Petit dictionnaire de la langue française (Hocquart, 1831)/Articles/About`) [hierarchy]
    - text: Abouter (`Petit dictionnaire de la langue française (Hocquart, 1831)/Articles/Abouter`) [hierarchy]
    - … 7,341 more
1. `Dictionnaire wallon-français (Dasnoy)`: subpages, reference, 1 parts, 0 missing, order single, validated
    - toc: Dictionnaire wallon-français (Dasnoy)
    - text: Avertissement (`Dictionnaire wallon-français (Dasnoy)/Avertissement`) [container]
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
1. `Dictionnaire critique de la langue française`: subpages, reference, 1 parts, 2 missing, order single, unproofread
    - toc: Dictionnaire critique de la langue française
    - text: Noms des auteurs cités (`Dictionnaire critique de la langue française/Auteurs cites`) [container]
1. `Encyclopédie théologique`: virtual, reference, 8 parts, 0 missing, order single, unproofread
    - volume: Morale (`Encyclopédie théologique/Morale`)
      - text: Avis (`Dictionnaire de théologie morale - Pierrot/Avis`) [container]
      - chapter: Introduction (`Dictionnaire de théologie morale - Pierrot/Introduction`) [container]
      - text: Adultere (`Encyclopédie théologique/Morale/Adultere`) [container]
      - text: Adultérin (`Encyclopédie théologique/Morale/Adultérin`) [container]
      - text: Jeu (`Encyclopédie théologique/Morale/Jeu`) [container]
      - text: Luxure (`Encyclopédie théologique/Morale/Luxure`) [container]
      - text: Macération (`Encyclopédie théologique/Morale/Macération`) [container]
1. `Dictionnaire français de Pierre Richelet`: virtual, reference, 50 parts, 4 missing, order natural, unproofread
    - volume: 1re éd., 1680 (`Dictionnaire français de Pierre Richelet/1re éd., 1680`)
      - text: Dédicace (`Dictionnaire français de Pierre Richelet/1re éd., 1680/Dedicace`) [container]
      - text: Avertissements (`Dictionnaire français de Pierre Richelet/1re éd., 1680/Avertissement`) [container]
      - text: Explication des marques (`Dictionnaire français de Pierre Richelet/1re éd., 1680/Explication`) [container]
      - text: Livres cités (`Dictionnaire français de Pierre Richelet/1re éd., 1680/Livres cités`) [container]
      - chapter: Introduction (`Dictionnaire français de Pierre Richelet/1re éd., 1680/Introduction`) [hierarchy]
      - chapter: Tome 1 (`Dictionnaire français de Pierre Richelet/1re éd., 1680/Tome 1/Fascicules`) [hierarchy]
      - text: 001-010 (`Dictionnaire français de Pierre Richelet/1re éd., 1680/Tome 1/001-010`) [hierarchy]
      - chapter: Tome 1a (`Dictionnaire français de Pierre Richelet/1re éd., 1680/Tome 1a`) [container]
    - volume: 4e éd., 1759 (`Dictionnaire français de Pierre Richelet/4e éd., 1759`) [hierarchy]
      - text: A LA TURQUESSE (`Dictionnaire français de Pierre Richelet/4e éd., 1759/A LA TURQUESSE`) [hierarchy]
      - text: ACADÉMICIENNE (`Dictionnaire français de Pierre Richelet/4e éd., 1759/ACADÉMICIENNE`) [hierarchy]
    - … 38 more
1. `Curiosités françaises (1640)`: virtual, reference, 1 parts, 0 missing, order single, proofread
    - text: Titre (`Curiosités françaises (1640)/Titre`)
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
1. `Dictionnaire des proverbes (Quitard)`: subpages, reference, 929 parts, 0 missing, order scan, validated
    - toc: Dictionnaire des proverbes (Quitard)
    - chapter: Préface (`Dictionnaire des proverbes (Quitard)/Préface`) [container]
    - text: a (`Dictionnaire des proverbes (Quitard)/a`) [container]
    - text: abattu (`Dictionnaire des proverbes (Quitard)/abattu`) [container]
    - text: abbaye (`Dictionnaire des proverbes (Quitard)/abbaye`) [container]
    - text: abbé (`Dictionnaire des proverbes (Quitard)/abbé`) [container]
    - text: abomination (`Dictionnaire des proverbes (Quitard)/abomination`) [container]
    - text: abondance (`Dictionnaire des proverbes (Quitard)/abondance`) [container]
    - text: absence (`Dictionnaire des proverbes (Quitard)/absence`) [container]
    - text: absent (`Dictionnaire des proverbes (Quitard)/absent`) [container]
    - text: accommodement (`Dictionnaire des proverbes (Quitard)/accommodement`) [container]
    - text: accord (`Dictionnaire des proverbes (Quitard)/accord`) [container]
    - … 918 more
1. `Biographie des célébrités militaires des armées de terre et de mer de 1789 à 1850`: mixed, reference, 26 parts, 0 missing, order toc-partial, unproofread
    - toc: Biographie des célébrités militaires des armées de terre et de mer de 1789 à 1850
    - text: A (`Biographie des célébrités militaires des armées de terre et de mer de 1789 à 1850/A`) [hierarchy]
    - text: de B à BONAMY (`Biographie des célébrités militaires des armées de terre et de mer de 1789 à 1850/B`) [hierarchy]
    - text: BONAPARTE (`Biographie des célébrités militaires des armées de terre et de mer de 1789 à 1850/BONAPARTE`) [hierarchy]
    - text: de BONCHAMP à BUZEN (`Biographie des célébrités militaires des armées de terre et de mer de 1789 à 1850/B (suite)`) [hierarchy]
    - chapter: C (`Biographie des célébrités militaires des armées de terre et de mer de 1789 à 1850/C`) [hierarchy]
    - chapter: D (`Biographie des célébrités militaires des armées de terre et de mer de 1789 à 1850/D`) [hierarchy]
    - text: E (`Biographie des célébrités militaires des armées de terre et de mer de 1789 à 1850/E`) [hierarchy]
    - text: F (`Biographie des célébrités militaires des armées de terre et de mer de 1789 à 1850/F`) [hierarchy]
    - text: G (`Biographie des célébrités militaires des armées de terre et de mer de 1789 à 1850/G`) [hierarchy]
    - text: H (`Biographie des célébrités militaires des armées de terre et de mer de 1789 à 1850/H`) [hierarchy]
    - chapter: I (`Biographie des célébrités militaires des armées de terre et de mer de 1789 à 1850/I`) [hierarchy]
    - … 15 more
1. `Dictionnaire hydrographique de la France`: subpages, reference, 743 parts, 0 missing, order chain, proofread
    - toc: Dictionnaire hydrographique de la France
    - text: AA (`Dictionnaire hydrographique de la France/AA`) [hierarchy]
    - text: ABIE (`Dictionnaire hydrographique de la France/ABIE`) [hierarchy]
    - text: ABRON (`Dictionnaire hydrographique de la France/ABRON`) [hierarchy]
    - text: ACHASSES (`Dictionnaire hydrographique de la France/ACHASSES`) [hierarchy]
    - text: ACOLASTRE (`Dictionnaire hydrographique de la France/ACOLASTRE`) [hierarchy]
    - text: ACOLIN (`Dictionnaire hydrographique de la France/ACOLIN`) [hierarchy]
    - text: ADOU (`Dictionnaire hydrographique de la France/ADOU`) [hierarchy]
    - text: ADOUR (`Dictionnaire hydrographique de la France/ADOUR`) [hierarchy]
    - text: AGLY (`Dictionnaire hydrographique de la France/AGLY`) [hierarchy]
    - text: AGNON (`Dictionnaire hydrographique de la France/AGNON`) [hierarchy]
    - text: AGOUT (`Dictionnaire hydrographique de la France/AGOUT`) [hierarchy]
    - … 732 more
1. `Petit dictionnaire d'argot parisien`: subpages, reference, 258 parts, 0 missing, order natural, proofread
    - toc: Petit dictionnaire d'argot parisien
    - text: Abattis (`Petit dictionnaire d'argot parisien/Abattis`) [hierarchy]
    - text: Acré (`Petit dictionnaire d'argot parisien/Acré`) [hierarchy]
    - text: Affure (`Petit dictionnaire d'argot parisien/Affure`) [hierarchy]
    - text: Agricher (`Petit dictionnaire d'argot parisien/Agricher`) [hierarchy]
    - text: Aguicher (`Petit dictionnaire d'argot parisien/Aguicher`) [hierarchy]
    - text: Allumer (`Petit dictionnaire d'argot parisien/Allumer`) [hierarchy]
    - text: Aminche (`Petit dictionnaire d'argot parisien/Aminche`) [hierarchy]
    - text: Apache (`Petit dictionnaire d'argot parisien/Apache`) [hierarchy]
    - text: Arguche, argomuche (`Petit dictionnaire d'argot parisien/Arguche, argomuche`) [hierarchy]
    - text: Arlequins (`Petit dictionnaire d'argot parisien/Arlequins`) [hierarchy]
    - text: Avaler sa langue (`Petit dictionnaire d'argot parisien/Avaler sa langue`) [hierarchy]
    - … 247 more
1. `Encyclopédie progressive`: subpages, reference, 4 parts, 4 missing, order single, proofread
    - toc: Encyclopédie progressive
    - volume: Économie politique (`Encyclopédie progressive/Économie politique`) [hierarchy]
      - text: Économie politique (`Encyclopédie progressive/Économie politique/1`) [hierarchy]
      - text: Nomenclature économique (`Encyclopédie progressive/Économie politique/2`) [hierarchy]
      - text: Bibliographie de l’économie politique (`Encyclopédie progressive/Économie politique/3`) [hierarchy]
1. `Dictionnaire encyclopédique de la noblesse de France`: virtual, reference, 1,071 parts, 0 missing, order natural, proofread
    - text: AB INTESTAT (`Dictionnaire encyclopédique de la noblesse de France/Articles/AB INTESTAT`)
    - text: ABAISSE (`Dictionnaire encyclopédique de la noblesse de France/Articles/ABAISSE`) [hierarchy]
    - text: ABAISSEMENT ou ABATTEMENT (`Dictionnaire encyclopédique de la noblesse de France/Articles/ABAISSEMENT ou ABATTEMENT`) [hierarchy]
    - text: ABEILLE (`Dictionnaire encyclopédique de la noblesse de France/Articles/ABEILLE`) [hierarchy]
    - text: ABOUTÉS (`Dictionnaire encyclopédique de la noblesse de France/Articles/ABOUTÉS`) [hierarchy]
    - text: ABRÉGER (`Dictionnaire encyclopédique de la noblesse de France/Articles/ABRÉGER`) [hierarchy]
    - text: ABÎME (`Dictionnaire encyclopédique de la noblesse de France/Articles/ABÎME`) [hierarchy]
    - text: ACCOLADE (`Dictionnaire encyclopédique de la noblesse de France/Articles/ACCOLADE`) [hierarchy]
    - text: ACCOLÉ (`Dictionnaire encyclopédique de la noblesse de France/Articles/ACCOLÉ`) [hierarchy]
    - text: ACCOMPAGNÉ (`Dictionnaire encyclopédique de la noblesse de France/Articles/ACCOMPAGNÉ`) [hierarchy]
    - text: ACCORNÉ (`Dictionnaire encyclopédique de la noblesse de France/Articles/ACCORNÉ`) [hierarchy]
    - text: ACCOSTÉ (`Dictionnaire encyclopédique de la noblesse de France/Articles/ACCOSTÉ`) [hierarchy]
    - … 1,059 more
1. `Dictionnaire portatif de cuisine, d’office, et de distillation`: subpages, reference, 500 parts, 0 missing, order toc, unproofread
    - toc: Dictionnaire portatif de cuisine, d’office, et de distillation
    - text: ABAISSE (`Dictionnaire portatif de cuisine, d’office, et de distillation/ABAISSE`) [container]
    - text: ABATIS (`Dictionnaire portatif de cuisine, d’office, et de distillation/ABATIS`) [container]
    - text: ABRICOT (`Dictionnaire portatif de cuisine, d’office, et de distillation/ABRICOT`) [container]
    - text: AGNEAU (`Dictionnaire portatif de cuisine, d’office, et de distillation/AGNEAU`) [container]
    - text: AIGRE DE CÉDRA (`Dictionnaire portatif de cuisine, d’office, et de distillation/AIGRE DE CÉDRA`) [container]
    - text: AIL (`Dictionnaire portatif de cuisine, d’office, et de distillation/AIL`) [container]
    - text: AÎLERONS (`Dictionnaire portatif de cuisine, d’office, et de distillation/AÎLERONS`) [container]
    - text: ALBRAN ou HALBRAN (`Dictionnaire portatif de cuisine, d’office, et de distillation/ALBRAN ou HALBRAN`) [container]
    - text: ALOSE (`Dictionnaire portatif de cuisine, d’office, et de distillation/ALOSE`) [container]
    - text: ALOUETTE (`Dictionnaire portatif de cuisine, d’office, et de distillation/ALOUETTE`) [container]
    - text: ALOYAU (`Dictionnaire portatif de cuisine, d’office, et de distillation/ALOYAU`) [container]
    - … 489 more
1. `Dictionnaire pratique et historique de la musique`: virtual, reference, 2,416 parts, 0 missing, order natural, proofread
    - text: A (`Dictionnaire pratique et historique de la musique/A`)
    - text: A battuta (`Dictionnaire pratique et historique de la musique/A battuta`) [hierarchy]
    - text: A cadenza (`Dictionnaire pratique et historique de la musique/A cadenza`) [hierarchy]
    - text: A cappella (`Dictionnaire pratique et historique de la musique/A cappella`) [hierarchy]
    - text: A capriccio (`Dictionnaire pratique et historique de la musique/A capriccio`) [hierarchy]
    - text: A e v i a (`Dictionnaire pratique et historique de la musique/A e v i a`) [hierarchy]
    - text: A la mi ré (`Dictionnaire pratique et historique de la musique/A la mi ré`) [hierarchy]
    - text: A piacere (`Dictionnaire pratique et historique de la musique/A piacere`) [hierarchy]
    - text: A tempo (`Dictionnaire pratique et historique de la musique/A tempo`) [hierarchy]
    - text: Abaissement (`Dictionnaire pratique et historique de la musique/Abaissement`) [hierarchy]
    - text: Abaisser (`Dictionnaire pratique et historique de la musique/Abaisser`) [hierarchy]
    - text: Abat-son (`Dictionnaire pratique et historique de la musique/Abat-son`) [hierarchy]
    - … 2,404 more
1. `Encyclopédie des gens du monde`: subpages, reference, 16 parts, 0 missing, order natural, proofread
    - front: Encyclopédie des gens du monde
    - text: A (`Encyclopédie des gens du monde/A`) [container]
    - text: Additions (`Encyclopédie des gens du monde/Additions`) [container]
    - text: Discours préliminaire (`Encyclopédie des gens du monde/Discours préliminaire`) [container]
    - text: Signatures des auteurs (`Encyclopédie des gens du monde/Signatures`) [container]
    - text: Sophistication (`Encyclopédie des gens du monde/Sophistication`) [container]
    - text: Sophocle (`Encyclopédie des gens du monde/Sophocle`) [container]
    - text: Sophonie (`Encyclopédie des gens du monde/Sophonie`) [container]
    - text: Sophonisbe (`Encyclopédie des gens du monde/Sophonisbe`) [container]
    - text: Soprano (`Encyclopédie des gens du monde/Soprano`) [container]
    - text: Sorbes (`Encyclopédie des gens du monde/Sorbes`) [container]
    - text: Spectre (`Encyclopédie des gens du monde/Spectre`) [container]
    - … 5 more
1. `Dictionnaire de l’ancien langage françois`: virtual, reference, 7 parts, 0 missing, order natural, unproofread
    - volume: 1re éd., 1766 (`Dictionnaire de l’ancien langage françois/1re éd., 1766`)
      - text: Truffe (`Dictionnaire de l’ancien langage françois/1re éd., 1766/Truffe`) [container]
    - text: 1re éd., 1767 (`Dictionnaire de l’ancien langage françois/1re éd., 1767`) [hierarchy]
    - volume: Nlle éd., 1768 (`Dictionnaire de l’ancien langage françois/Nlle éd., 1768`) [hierarchy]
      - chapter: Tome 1 (`Dictionnaire de l’ancien langage françois/Nlle éd., 1768/Tome 1`) [container]
      - text: Discours sur l'origine et les révolutions des Langues Celtique & Françoise (`Dictionnaire de l’ancien langage françois/Nlle éd., 1768/Discours`) [container]
      - text: Index alphabétique - Z (`Dictionnaire de l’ancien langage françois/Nlle éd., 1768/Index alphabétique - Z`) [container]
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
1. `Honnorat - Dictionnaire provençal-français`: single, reference, 1 parts, 0 missing, order –, unproofread
    - text: Honnorat - Dictionnaire provençal-français
1. `Dictionnaire de la Bible - Vigouroux`: mixed, reference, 800 parts, 0 missing, order toc-partial, proofread, excluded index-faulty
    - front: Dictionnaire de la Bible - Vigouroux
    - chapter: Lettre du cardinal Richard, archevêque de Paris (`Dictionnaire de la Bible/Lettre du cardinal Richard, archevêque de Paris`) [container]
    - text: Lettres du cardinal Bourret, évêque de Rodez (`Dictionnaire de la Bible/Lettre du cardinal Bourret, évêque de Rodez`) [container]
    - text: A. (`Dictionnaire de la Bible/A.`) [container]
    - text: A et Ω (`Dictionnaire de la Bible/A et Omega`) [container]
    - text: AAGAARD (`Dictionnaire de la Bible/Aagard`) [container]
    - text: AARHUSIUS (`Dictionnaire de la Bible/Aarhusius`) [container]
    - text: AARON (`Dictionnaire de la Bible/Aaron`) [container]
    - text: AARONITES (`Dictionnaire de la Bible/Aaronites`) [container]
    - text: AASBAÏ (`Dictionnaire de la Bible/Aasbaï`) [container]
    - text: AB (`Dictionnaire de la Bible/Ab`) [container]
    - text: ABADDON (`Dictionnaire de la Bible/Abaddon`) [container]
    - … 789 more

### H: deep nesting (187)

1. `Œuvres de Théophile Gautier (Lemerre, 1890)`: linked, collection, 316 parts, 0 missing, order toc-partial, unproofread
    - toc: Œuvres de Théophile Gautier (Lemerre, 1890)
    - text: Sonnet VI (`Sonnet. « Avant cet heureux jour »`) [container]
    - text: Élégie IV (`Élégie. « Ma charmante, depuis »`) [container]
    - text: Sonnet VII (`Sonnet. « Avec ce siècle infâme »`) [container]
    - text: Paris (`Paris (Gautier)`) [container]
    - text: Un vers de Wordsworth (`Un Vers de Wordsworth`) [container]
    - text: Débauche [container]
    - text: Le Bengali (`Le Bengali (Gautier)`) [container]
    - text: Le Cavalier poursuivi [container]
    - text: Albertus [container]
    - text: Le Nuage (`Le Nuage (Gautier)`) [container]
    - text: Les Colombes (`Les Colombes (Gautier)`) [container]
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
1. `George Sand, sa vie et ses œuvres`: subpages, book, 46 parts, 3 missing, order toc-partial, unproofread
    - toc: George Sand, sa vie et ses œuvres
    - text: Portrait de George Sand (`George Sand, sa vie et ses œuvres/Portrait`) [container]
    - chapter: Table des matières des quatre volumes (`George Sand, sa vie et ses œuvres/Table des matières`) [container]
    - volume: volume 1. (`George Sand, sa vie et ses œuvres/1`) [hierarchy]
      - chapter: 1 (`George Sand, sa vie et ses œuvres/1/1`) [container]
      - chapter: 2 (`George Sand, sa vie et ses œuvres/1/2`) [container]
      - chapter: 3 (`George Sand, sa vie et ses œuvres/1/3`) [container]
      - chapter: 4 (`George Sand, sa vie et ses œuvres/1/4`) [container]
      - chapter: 5 (`George Sand, sa vie et ses œuvres/1/5`) [container]
      - chapter: 6 (`George Sand, sa vie et ses œuvres/1/6`) [container]
      - chapter: 7 (`George Sand, sa vie et ses œuvres/1/7`) [hierarchy]
      - chapter: Introduction (`George Sand, sa vie et ses œuvres/1/Introduction`) [container]
    - … 35 more
1. `Institutes coutumières`: virtual, book, 23 parts, 0 missing, order natural, unproofread
    - volume: 1607 (`Institutes coutumières/1607`)
      - chapter: I. (`Institutes coutumières/1607/Livre I`) [container]
      - chapter: II. (`Institutes coutumières/1607/Livre II`) [container]
      - chapter: III. (`Institutes coutumières/1607/Livre III`) [container]
      - chapter: IIII. (`Institutes coutumières/1607/Livre IIII`) [container]
      - chapter: Livre V (`Institutes coutumières/1607/Livre V`) [container]
      - chapter: Livre VI (`Institutes coutumières/1607/Livre VI`) [container]
    - volume: 1679 (`Institutes coutumières/1679`) [hierarchy]
      - chapter: Livre I (`Institutes coutumières/1679/Livre I`) [container]
      - chapter: Livre II (`Institutes coutumières/1679/Livre II`) [container]
      - chapter: Livre III (`Institutes coutumières/1679/Livre III`) [container]
      - chapter: Livre IV (`Institutes coutumières/1679/Livre IV`) [container]
    - … 11 more
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
1. `Nicette et Milou`: subpages, book, 32 parts, 0 missing, order single, validated
    - toc: Nicette et Milou
    - volume: LA PETITE NICETTE (`Nicette et Milou/La Petite Nicette`) [container]
      - volume: LE GRAND MILOU (`Nicette et Milou/Le Grand Milou`) [container]
        - chapter: Chapitre I (`Nicette et Milou/La Petite Nicette/01`) [container]
        - chapter: Chapitre II (`Nicette et Milou/La Petite Nicette/02`) [container]
        - chapter: Chapitre III (`Nicette et Milou/La Petite Nicette/03`) [container]
        - chapter: Chapitre IV (`Nicette et Milou/La Petite Nicette/04`) [container]
        - chapter: Chapitre V (`Nicette et Milou/La Petite Nicette/05`) [container]
        - chapter: Chapitre VI (`Nicette et Milou/La Petite Nicette/06`) [container]
        - chapter: Chapitre VII (`Nicette et Milou/La Petite Nicette/07`) [container]
        - chapter: Chapitre VIII (`Nicette et Milou/La Petite Nicette/08`) [container]
        - chapter: Chapitre IX (`Nicette et Milou/La Petite Nicette/09`) [container]
    - … 21 more
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
1. `Sourires pincés`: subpages, book, 29 parts, 0 missing, order toc, validated
    - toc: Sourires pincés
    - volume: POINTES SÈCHES (`Sourires pincés/I`) [container]
      - volume: SOURIRES PINCÉS (`Sourires pincés/IV`) [container]
        - text: Les Poules (`Sourires pincés/I/I`) [container]
        - text: Les Perdrix (`Sourires pincés/I/II`) [container]
        - text: Aller et retour (`Sourires pincés/I/III`) [container]
        - text: Sauf votre respect (`Sourires pincés/I/IV`) [container]
        - text: La Pioche (`Sourires pincés/I/V`) [container]
        - text: Les Lapins (`Sourires pincés/I/VI`) [container]
        - text: La Trompette (`Sourires pincés/I/VII`) [container]
        - text: Le Cauchemar (`Sourires pincés/I/VIII`) [container]
        - text: Coup de Théâtre (`Sourires pincés/I/IX`) [container]
    - … 18 more
1. `Histoire de France (Jules Michelet)`: virtual, collection, 357 parts, 3 missing, order natural, proofread
    - chapter: Notes des Guerres de religion (`Histoire de France (Jules Michelet)/Notes des Guerres de religion`)
    - volume: édition 1893 (`Histoire de France (Jules Michelet)/édition 1893`) [hierarchy]
      - volume: Tome premier (`Histoire de France (Jules Michelet)/édition 1893/Tome I`) [container]
        - chapter: Préface de 1869 (`Histoire de France (Jules Michelet)/édition 1893/Préface de 1869`) [container]
        - chapter: Table de la Préface (`Histoire de France (Jules Michelet)/édition 1893/Table de la Préface`) [container]
        - chapter: Préface de 1861 (`Histoire de France (Jules Michelet)/édition 1893/Moyen Âge/Préface de 1861`) [container]
        - volume: LIVRE I (`Histoire de France (Jules Michelet)/édition 1893/Moyen Âge/Livre 1`) [container]
          - chapter: Chapitre 1 (`Histoire de France (Jules Michelet)/édition 1893/Moyen Âge/Livre 1/Chapitre 1`) [container]
          - chapter: Chapitre 2 (`Histoire de France (Jules Michelet)/édition 1893/Moyen Âge/Livre 1/Chapitre 2`) [container]
          - chapter: Chapitre 3 (`Histoire de France (Jules Michelet)/édition 1893/Moyen Âge/Livre 1/Chapitre 3`) [container]
          - chapter: Chapitre 4 (`Histoire de France (Jules Michelet)/édition 1893/Moyen Âge/Livre 1/Chapitre 4`) [container]
        - volume: LIVRE II (`Histoire de France (Jules Michelet)/édition 1893/Moyen Âge/Livre 2`) [container]
    - … 345 more
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
1. `Les Derniers Bretons`: subpages, book, 13 parts, 2 missing, order toc, validated
    - front: Les Derniers Bretons
    - volume: I (`Les Derniers Bretons/Tome 1`) [hierarchy]
      - text: Dédicace (`Les Derniers Bretons/Tome 1/Dédicace`) [container]
      - chapter: Introduction (`Les Derniers Bretons/Tome 1/Introduction`) [container]
      - volume: PREMIÈRE PARTIE. — La Bretagne et les Bretons (`Les Derniers Bretons/Tome 1/1`) [container]
        - text: . — Le Pays de Léon (`Les Derniers Bretons/Tome 1/1/1`) [container]
        - text: . — La Cornouaille (`Les Derniers Bretons/Tome 1/1/2`) [container]
        - text: . — Le Pays de Tréguier (`Les Derniers Bretons/Tome 1/1/3`) [container]
    - volume: II (`Les Derniers Bretons/Tome 2`) [container]
      - text: . — Le Pays de Vannes (`Les Derniers Bretons/Tome 2/0`) [container]
      - volume: DEUXIÈME PARTIE. — (`Les Derniers Bretons/Tome 2/2`) [container]
        - text: . — Poésies populaires de la Bretagne (`Les Derniers Bretons/Tome 2/2/1`) [container]
    - … 2 more
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
1. `Mémoires (Cardinal de Retz)`: subpages, book, 52 parts, 0 missing, order toc-partial, proofread
    - toc: Mémoires (Cardinal de Retz)
    - chapter: Livre 1 (`Mémoires (Cardinal de Retz)/Livre 1`) [container]
    - chapter: Livre 2 (`Mémoires (Cardinal de Retz)/Livre 2`) [container]
    - chapter: Livre 3 (`Mémoires (Cardinal de Retz)/Livre 3`) [container]
    - chapter: Livre 4 (`Mémoires (Cardinal de Retz)/Livre 4`) [container]
    - chapter: Livre 5 (`Mémoires (Cardinal de Retz)/Livre 5`) [container]
    - volume: Table (`Mémoires (Cardinal de Retz)/Table`) [hierarchy]
      - text: Notice sur le cardinal de Retz et sur ses Mémoires. (`Mémoires (Cardinal de Retz)/Notice`) [container]
      - volume: Livre premier (`Mémoires (Cardinal de Retz)/Livre premier`) [container]
        - chapter: Section 1 (`Mémoires (Cardinal de Retz)/Livre premier/Section 1`) [container]
        - chapter: Section 2 (`Mémoires (Cardinal de Retz)/Livre premier/Section 2`) [container]
        - chapter: Section 3 (`Mémoires (Cardinal de Retz)/Livre premier/Section 3`) [container]
    - … 41 more
1. `Voyages très extraordinaires de Saturnin Farandoul`: subpages, book, 57 parts, 0 missing, order toc-partial, validated
    - front: Voyages très extraordinaires de Saturnin Farandoul
    - volume: DEUXIÈME PARTIE LES DEUX AMÉRIQUES LE TOUR DU MONDE EN PLUS DE 80 JOURS (`Voyages très extraordinaires de Saturnin Farandoul/02`) [container]
      - chapter: 01 (`Voyages très extraordinaires de Saturnin Farandoul/02/01`) [container]
      - chapter: 02 (`Voyages très extraordinaires de Saturnin Farandoul/02/02`) [container]
      - chapter: 03 (`Voyages très extraordinaires de Saturnin Farandoul/02/03`) [container]
      - chapter: 04 (`Voyages très extraordinaires de Saturnin Farandoul/02/04`) [container]
      - chapter: 05 (`Voyages très extraordinaires de Saturnin Farandoul/02/05`) [container]
      - chapter: 06 (`Voyages très extraordinaires de Saturnin Farandoul/02/06`) [container]
      - chapter: 07 (`Voyages très extraordinaires de Saturnin Farandoul/02/07`) [container]
      - chapter: 08 (`Voyages très extraordinaires de Saturnin Farandoul/02/08`) [container]
      - chapter: 09 (`Voyages très extraordinaires de Saturnin Farandoul/02/09`) [container]
      - chapter: 10 (`Voyages très extraordinaires de Saturnin Farandoul/02/10`) [container]
    - … 46 more
1. `Le Tour du monde`: virtual, periodical-issue, 88 parts, 1 missing, order natural, proofread
    - volume: Volume 1 (`Le Tour du monde/Volume 1`)
      - chapter: Préface (`Le Tour du monde/Volume 1/Préface`) [hierarchy]
      - text: Liste des gravures (`Le Tour du monde/Volume 1/Liste des gravures`) [hierarchy]
      - text: Liste des cartes (`Le Tour du monde/Volume 1/Liste des cartes`) [hierarchy]
    - volume: Volume 2 (`Le Tour du monde/Volume 2`) [hierarchy]
      - volume: Voyage d’un naturaliste (`Le Tour du monde/Volume 2/Voyage d’un naturaliste`) [hierarchy]
        - chapter: Première partie (`Le Tour du monde/Volume 2/Voyage d’un naturaliste/01`) [hierarchy]
        - chapter: Deuxième partie (`Le Tour du monde/Volume 2/Voyage d’un naturaliste/02`) [hierarchy]
        - chapter: Troisième partie (`Le Tour du monde/Volume 2/Voyage d’un naturaliste/03`) [hierarchy]
      - volume: Voyage aux grands lacs d’Afrique orientale (`Le Tour du monde/Volume 2/Voyage aux grands lacs d’Afrique orientale`) [hierarchy]
        - text: Première livraison (`Le Tour du monde/Volume 2/Voyage aux grands lacs d’Afrique orientale/1`) [hierarchy]
        - text: Deuxième livraison (`Le Tour du monde/Volume 2/Voyage aux grands lacs d’Afrique orientale/2`) [hierarchy]
    - … 76 more
1. `Intermèdes`: subpages, collection, 96 parts, 0 missing, order toc, proofread
    - toc: Intermèdes
    - chapter: Introduction — Sur les évolutions rationnelles (`Intermèdes/Introduction`) [container]
    - text: PENTÉLIQUES (`Intermèdes/Pentéliques`) [container]
    - text: Les Parques (`Intermèdes/Les Parques`) [container]
    - text: Apollon (`Intermèdes/Apollon`) [container]
    - text: Artémis (`Intermèdes/Artémis`) [container]
    - text: Aphrodite Ourania (`Intermèdes/Aphrodite Ourania`) [container]
    - text: Athéna (`Intermèdes/Athéna`) [container]
    - text: Eros (`Intermèdes/Eros`) [container]
    - text: LES AURORES (`Intermèdes/Les Aurores`) [container]
    - text: Aurore première (`Intermèdes/Aurore première`) [container]
    - text: Aurore d’amour (`Intermèdes/Aurore d’amour`) [container]
    - … 85 more
1. `Fables de La Fontaine (éd. Barbin)`: mixed, collection, 135 parts, 0 missing, order natural, proofread
    - toc: Fables de La Fontaine (éd. Barbin)
    - volume: 1 (`Fables de La Fontaine (éd. Barbin)/1`) [container]
      - text: À Monseigneur le Dauphin (`Fables de La Fontaine (éd. Barbin)/1/À Monseigneur le Dauphin`) [container]
      - text: Les Voleurs & l’Aſne (`Fables de La Fontaine (éd. Barbin)/1/Les Voleurs et l’Âne`) [container]
      - volume: Table des Fables Contenuës dans cette premiere Partie (`Fables de La Fontaine (éd. Barbin)/1/Table alphabétique`) [container]
        - text: ’Aigle & l’Eſcarbot (`Fables de La Fontaine (éd. Barbin)/1/L’Aigle et l’Escarbot`) [container]
        - text: L’Aigle, la Laye, & la Chate (`Fables de La Fontaine (éd. Barbin)/1/L’Aigle, la Laie, et la Chatte`) [container]
        - text: L’Aſne chargé d’Eponges, & l’Aſne chargé de Sel (`Fables de La Fontaine (éd. Barbin)/1/L’Âne chargé d’éponges, et l’Âne chargé de sel`) [container]
        - text: L’Aſtrologue qui ſe laiſſe tomber dans un Puits (`Fables de La Fontaine (éd. Barbin)/1/L’Astrologue qui se laisse tomber dans un puits`) [container]
        - text: A Belette que eſt entrée dans un Grenier (`Fables de La Fontaine (éd. Barbin)/1/La Belette entrée dans un Grenier`) [container]
        - text: La Beſace (`Fables de La Fontaine (éd. Barbin)/1/La Besace`) [container]
        - text: A Chauve-Souris & les deux Belettes (`Fables de La Fontaine (éd. Barbin)/1/La Chauve-souris et les deux Belettes`) [container]
    - … 124 more
1. `Biographie universelle ancienne et moderne`: virtual, reference, 486 parts, 8 missing, order natural, unproofread
    - volume: 1re éd., 1811 (`Biographie universelle ancienne et moderne/1re éd., 1811`)
      - text: Aa (Petrus van der) (`Biographie universelle ancienne et moderne/1re éd., 1811/Aa (Petrus van der)`) [hierarchy]
      - text: Aa (Pieter van der) (`Biographie universelle ancienne et moderne/1re éd., 1811/Aa (Pieter van der)`) [hierarchy]
      - text: Aar-toion (`Biographie universelle ancienne et moderne/1re éd., 1811/Aar-toion`) [hierarchy]
      - text: Abacco (`Biographie universelle ancienne et moderne/1re éd., 1811/Abacco`) [hierarchy]
      - text: Abad (`Biographie universelle ancienne et moderne/1re éd., 1811/Abad`) [hierarchy]
      - text: Abad Ier (`Biographie universelle ancienne et moderne/1re éd., 1811/Abad Ier`) [hierarchy]
      - text: Abad III (`Biographie universelle ancienne et moderne/1re éd., 1811/Abad III`) [hierarchy]
      - text: Abaddirs (`Biographie universelle ancienne et moderne/1re éd., 1811/Abaddirs`) [hierarchy]
      - text: Abaffi (Michel) (`Biographie universelle ancienne et moderne/1re éd., 1811/Abaffi (Michel)`) [hierarchy]
      - text: Abaffi ou Apaffi (Michel) (`Biographie universelle ancienne et moderne/1re éd., 1811/Abaffi ou Apaffi (Michel)`) [hierarchy]
      - text: Abailard (Pierre) (`Biographie universelle ancienne et moderne/1re éd., 1811/Abailard (Pierre)`) [hierarchy]
    - … 474 more
1. `Œuvres complètes de Saint-Just`: mixed, collection, 40 parts, 33 missing, order toc, unproofread
    - toc: Œuvres complètes de Saint-Just
    - chapter: Avant-propos (`Œuvres complètes de Saint-Just/Avant-propos`) [hierarchy]
    - chapter: Introduction (`Œuvres complètes de Saint-Just/Introduction`) [hierarchy]
    - chapter: PREMIÈRE PARTIE (`Œuvres complètes de Saint-Just/1`) [hierarchy]
    - volume: DEUXIÈME PARTIE (`Œuvres complètes de Saint-Just/2`) [container]
      - chapter: I (`Œuvres complètes de Saint-Just/2/I`) [hierarchy]
      - chapter: II (`Œuvres complètes de Saint-Just/2/II`) [hierarchy]
      - chapter: III (`Œuvres complètes de Saint-Just/2/III`) [hierarchy]
      - chapter: IV (`Œuvres complètes de Saint-Just/2/IV`) [hierarchy]
      - chapter: V (`Œuvres complètes de Saint-Just/2/V`) [hierarchy]
      - chapter: VI (`Œuvres complètes de Saint-Just/2/VI`) [hierarchy]
      - chapter: VII (`Œuvres complètes de Saint-Just/2/VII`) [hierarchy]
    - … 29 more
1. `Les Mystères du confessionnal`: subpages, book, 29 parts, 0 missing, order toc, proofread
    - toc: Les Mystères du confessionnal
    - chapter: PROLOGUE (`Les Mystères du confessionnal/Prologue`) [container]
    - text: AVERTISSEMENT (`Les Mystères du confessionnal/Avertissement`) [container]
    - volume: DISSERTATION (`Les Mystères du confessionnal/Dissertation sur le sixième commandement`) [container]
      - chapter: 01 (`Les Mystères du confessionnal/Dissertation sur le sixième commandement/01`) [container]
      - chapter: 02 (`Les Mystères du confessionnal/Dissertation sur le sixième commandement/02`) [container]
      - chapter: 03 (`Les Mystères du confessionnal/Dissertation sur le sixième commandement/03`) [container]
      - chapter: 04 (`Les Mystères du confessionnal/Dissertation sur le sixième commandement/04`) [container]
      - chapter: 05 (`Les Mystères du confessionnal/Dissertation sur le sixième commandement/05`) [container]
    - volume: SUPPLÉMENT (`Les Mystères du confessionnal/Supplément au traité du mariage`) [container]
      - chapter: 01 (`Les Mystères du confessionnal/Supplément au traité du mariage/01`) [container]
      - volume: 02 (`Les Mystères du confessionnal/Supplément au traité du mariage/02`) [container]
    - … 18 more
1. `La Pierre de Lune`: subpages, book, 56 parts, 0 missing, order toc, proofread
    - toc: La Pierre de Lune
    - chapter: PROLOGUE : L’assaut de Seringapatam (`La Pierre de Lune/Prologue`) [container]
    - volume: Première période : Perte du Diamant (1848) (`La Pierre de Lune/I`) [container]
      - chapter: 01 (`La Pierre de Lune/I/01`) [container]
      - chapter: 02 (`La Pierre de Lune/I/02`) [container]
      - chapter: 03 (`La Pierre de Lune/I/03`) [container]
      - chapter: 04 (`La Pierre de Lune/I/04`) [container]
      - chapter: 05 (`La Pierre de Lune/I/05`) [container]
      - chapter: 06 (`La Pierre de Lune/I/06`) [container]
      - chapter: 07 (`La Pierre de Lune/I/07`) [container]
      - chapter: 08 (`La Pierre de Lune/I/08`) [container]
      - chapter: 09 (`La Pierre de Lune/I/09`) [container]
    - … 45 more
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
1. `Les Petits poèmes grecs`: subpages, collection, 186 parts, 97 missing, order toc, unproofread
    - toc: Les Petits poèmes grecs
    - chapter: Préface de l’éditeur. (`Les Petits poèmes grecs/Préface de l’éditeur`) [container]
    - chapter: Introduction. (`Les Petits poèmes grecs/Introduction`) [container]
    - chapter: Préface d’Orphée. (`Les Petits poèmes grecs/Orphée/Orphée et ses œuvres`) [container]
    - text: L’Argonautique. (`Les Petits poèmes grecs/Orphée/L’Argonautique`) [container]
    - text: HYMNES. (`Les Petits poèmes grecs/Orphée/Hymnes`) [container]
    - text: LES PIERRES. (`Les Petits poèmes grecs/Orphée/Les Pierres`) [container]
    - chapter: Préface. (`Les Petits poèmes grecs/Homère/Notice`) [container]
    - text: Hymne I. À Apollon. (`Les Petits poèmes grecs/Homère/Hymne I`) [container]
    - text: &#160;&#160;&#160;—&#160;&#160;&#160; II. À Mercure. (`Les Petits poèmes grecs/Homère/Hymne II`) [container]
    - text: &#160;&#160;&#160;—&#160;&#160;&#160; III. À Vénus. (`Les Petits poèmes grecs/Homère/Hymne III`) [container]
    - text: &#160;&#160;&#160;—&#160;&#160;&#160; IV. À Cérès. (`Les Petits poèmes grecs/Homère/Hymne IV`) [container]
    - … 175 more
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
1. `La Bonne aventure (Sue)/1`: mixed, book, 65 parts, 0 missing, order single, proofread
    - toc: La Bonne aventure (Sue)/1
    - volume: La Bonne aventure (Sue) [container]
      - chapter: I (`La Bonne aventure (Sue)/1/I`) [container]
      - chapter: II (`La Bonne aventure (Sue)/1/II`) [container]
      - chapter: III (`La Bonne aventure (Sue)/1/III`) [container]
      - chapter: IV (`La Bonne aventure (Sue)/1/IV`) [container]
      - chapter: V (`La Bonne aventure (Sue)/1/V`) [container]
      - chapter: VI (`La Bonne aventure (Sue)/1/VI`) [container]
      - chapter: VII (`La Bonne aventure (Sue)/1/VII`) [container]
      - chapter: VIII (`La Bonne aventure (Sue)/1/VIII`) [container]
      - chapter: IX (`La Bonne aventure (Sue)/1/IX`) [container]
      - chapter: X (`La Bonne aventure (Sue)/1/X`) [container]
    - … 54 more
1. `Le Livre de Pâtisserie`: subpages, book, 29 parts, 0 missing, order toc, unproofread
    - toc: Le Livre de Pâtisserie
    - chapter: PRÉFACE (`Le Livre de Pâtisserie/Préface`) [container]
    - volume: CONSIDÉRATIONS PRÉLIMINAIRES (`Le Livre de Pâtisserie/Considérations préliminaires`) [container]
      - text: Conseils aux jeunes pâtissiers (`Le Livre de Pâtisserie/Considérations préliminaires/I`) [container]
      - text: Termes de pâtisserie (`Le Livre de Pâtisserie/Considérations préliminaires/II`) [container]
      - text: Du four et de la cuisson (`Le Livre de Pâtisserie/Considérations préliminaires/III`) [container]
    - volume: PREMIÈRE PARTIE (`Le Livre de Pâtisserie/Première Partie`) [container]
      - volume: DEUXIÈME PARTIE (`Le Livre de Pâtisserie/Deuxième Partie`) [container]
        - text: Premières préparations (`Le Livre de Pâtisserie/Première Partie/Chapitre I`) [container]
        - text: Détrempes (`Le Livre de Pâtisserie/Première Partie/Chapitre II`) [container]
        - text: Entrées et hors-d’œuvre de four (`Le Livre de Pâtisserie/Première Partie/Chapitre III`) [container]
        - text: Crèmes d’amandes pâtissières et autres recettes (`Le Livre de Pâtisserie/Première Partie/Chapitre IV`) [container]
    - … 18 more

### I: works with other editions (563)

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
1. `Chants et Chansons (Pierre Dupont)`: mixed, collection, 46 parts, 0 missing, order toc-partial, proofread
    - front: Chants et Chansons (Pierre Dupont)
    - text: Notice sur Pierre Dupont, par Ch. Baudelaire (`Chants et Chansons (Pierre Dupont)/Notice sur Pierre Dupont (Ch. Baudelaire)`) [container]
    - chapter: Préface de l’auteur (`Chants et Chansons (Pierre Dupont)/Préface de l’auteur`) [container]
    - text: Les Bœufs (`Chants et Chansons (Pierre Dupont)/Les Bœufs`) [container]
    - chapter: Le Chant des ouvriers (`Chants et Chansons (Pierre Dupont)/Le Chant des ouvriers`) [container]
    - text: Belzébut (`Chants et Chansons (Pierre Dupont)/Belzébut`) [container]
    - text: La Blonde (`Chants et Chansons (Pierre Dupont)/La Blonde`) [container]
    - chapter: Le Chant des transportés (`Chants et Chansons (Pierre Dupont)/Le Chant des transportés`) [container]
    - text: Les Louis d’or (`Chants et Chansons (Pierre Dupont)/Les Louis d’or`) [container]
    - text: La Fille du peuple (`Chants et Chansons (Pierre Dupont)/La Fille du peuple`) [container]
    - chapter: 1852 (`Chants et Chansons (Pierre Dupont)/1852`) [container]
    - text: Le Chien de berger (`Chants et Chansons (Pierre Dupont)/Le Chien de berger`) [container]
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
1. `Œuvres de Descartes`: virtual, book, 89 parts, 58 missing, order natural, unproofread
    - volume: Édition Adam et Tannery (`Œuvres de Descartes/Édition Adam et Tannery`)
      - volume: Correspondance (`Œuvres de Descartes/Édition Adam et Tannery/Correspondance`) [hierarchy]
        - chapter: Préface (`Œuvres de Descartes/Édition Adam et Tannery/Correspondance/Préface`) [container]
        - chapter: Introduction à la Correspondance de Descartes (`Œuvres de Descartes/Édition Adam et Tannery/Correspondance/Introduction à la Correspondance de Descartes`) [container]
        - chapter: I (`Œuvres de Descartes/Édition Adam et Tannery/Correspondance/Lettre I`) [container]
        - chapter: II (`Œuvres de Descartes/Édition Adam et Tannery/Correspondance/Lettre II`) [container]
        - chapter: III (`Œuvres de Descartes/Édition Adam et Tannery/Correspondance/Lettre III`) [container]
        - chapter: IV (`Œuvres de Descartes/Édition Adam et Tannery/Correspondance/Lettre IV`) [container]
        - chapter: V (`Œuvres de Descartes/Édition Adam et Tannery/Correspondance/Lettre V`) [container]
        - text: VI bis (`Œuvres de Descartes/Édition Adam et Tannery/Correspondance/Lettre VI bis`) [container]
        - chapter: VII (`Œuvres de Descartes/Édition Adam et Tannery/Correspondance/Lettre VII`) [container]
        - chapter: VIII (`Œuvres de Descartes/Édition Adam et Tannery/Correspondance/Lettre VIII`) [container]
    - … 77 more
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
1. `Essais`: virtual, book, 371 parts, 11 missing, order natural, unproofread
    - volume: exemplaire de Bordeaux, 1588-1592 (`Essais/exemplaire de Bordeaux, 1588-1592`)
      - text: 30D’un enfant monstrueux (`Essais/Livre II/Chapitre 30`) [container]
      - text: 31De la colere (`Essais/Livre II/Chapitre 31`) [container]
      - text: 32Deffense de Seneque & de Plutarque (`Essais/Livre II/Chapitre 32`) [container]
      - text: 33L’Histoire de Spurina (`Essais/Livre II/Chapitre 33`) [container]
      - text: 34Observations sur les moyens de faire la guerre de Jules Cæsar (`Essais/Livre II/Chapitre 34`) [container]
      - text: 35De trois bonnes femmes (`Essais/Livre II/Chapitre 35`) [container]
      - text: 36Des plus excellens hommes (`Essais/Livre II/Chapitre 36`) [container]
      - text: 37De la ressemblance des enfans aux peres (`Essais/Livre II/Chapitre 37`) [container]
      - volume: Livre II (`Essais/Livre II`) [container]
        - text: LES CHAPITRES DU LIVRE &nbsp; SECOND (`Essais/Livre II/Entete`) [container]
        - text: 1 D l’inconstance de nos actions. (`Essais/Livre II/Chapitre 1`) [hierarchy]
    - … 359 more
1. `Autre guitare (partition)`: single, book, 1 parts, 0 missing, order –, proofread
    - text: Autre guitare (partition)
1. `L’Homme machine (1796)`: single, book, 1 parts, 0 missing, order –, proofread
    - text: L’Homme machine (1796)
1. `Poésies (Mallarmé, 1914, 8e éd.)`: subpages, collection, 65 parts, 0 missing, order toc, proofread
    - toc: Poésies (Mallarmé, 1914, 8e éd.)
    - text: SALUT (`Poésies (Mallarmé, 1914, 8e éd.)/Salut`) [container]
    - text: Le Guignon (`Poésies (Mallarmé, 1914, 8e éd.)/Le Guignon`) [container]
    - text: Apparition (`Poésies (Mallarmé, 1914, 8e éd.)/Apparition`) [container]
    - text: Placet futile (`Poésies (Mallarmé, 1914, 8e éd.)/Placet futile`) [container]
    - text: Le Pitre Châtié (`Poésies (Mallarmé, 1914, 8e éd.)/Le Pitre châtié`) [container]
    - text: Une négresse par le démon secouée (`Poésies (Mallarmé, 1914, 8e éd.)/« Une négresse par le démon secouée »`) [container]
    - text: Soupir (`Poésies (Mallarmé, 1914, 8e éd.)/Soupir`) [container]
    - text: Les Fenêtres (`Poésies (Mallarmé, 1914, 8e éd.)/Les Fenêtres`) [container]
    - text: Les Fleurs (`Poésies (Mallarmé, 1914, 8e éd.)/Les Fleurs`) [container]
    - text: Renouveau (`Poésies (Mallarmé, 1914, 8e éd.)/Renouveau`) [container]
    - text: Angoisse (`Poésies (Mallarmé, 1914, 8e éd.)/Angoisse`) [container]
    - … 54 more
1. `La Comédie des erreurs (trad. Hugo)`: single, collection, 1 parts, 0 missing, order –, proofread
    - text: La Comédie des erreurs (trad. Hugo)
1. `Ubu enchaîné`: linked, book, 2 parts, 0 missing, order single, validated
    - front: Ubu enchaîné
    - volume: Ubu roi (1900) [container]
      - text: Chanson du décervelage (`Ubu roi (1900)/Chanson du décervelage`) [container]
1. `Thérèse philosophe (Enfer-402)`: subpages, book, 3 parts, 0 missing, order scan, proofread
    - toc: Thérèse philosophe (Enfer-402)
    - chapter: 01 (`Thérèse philosophe (Enfer-402)/01`) [container]
    - chapter: 02 (`Thérèse philosophe (Enfer-402)/02`) [container]
    - chapter: 03 (`Thérèse philosophe (Enfer-402)/03`) [container]
1. `Le Roi Jean (trad. Hugo)`: single, collection, 1 parts, 0 missing, order –, validated
    - text: Le Roi Jean (trad. Hugo)
1. `Chants et chansons populaires de la France`: mixed, collection, 5 parts, 0 missing, order toc, proofread
    - toc: Chants et chansons populaires de la France
    - text: Le Conscrit de 1810 [container]
    - text: FANCHON (`Chants et chansons populaires de la France/Fanchon`) [container]
    - text: PARIS A 5 HEURES DU SOIR (`Tableau de Paris à cinq heures du soir`) [container]
    - text: LA MACHINE INFERNALE (`La Machine infernale`) [container]
    - text: MONSIEUR ET MADAME DENIS (`Chants et chansons populaires de la France/Monsieur et Madame Denis`) [container]
1. `Méditations poétiques (éd. originale 1820)`: subpages, collection, 25 parts, 0 missing, order toc, unproofread
    - toc: Méditations poétiques (éd. originale 1820)
    - text: Avertissement de l’éditeur (`Méditations poétiques (éd. originale 1820)/Avertissement de l'éditeur`) [container]
    - text: L’Isolement (`Méditations poétiques (éd. originale 1820)/L’Isolement`) [container]
    - text: L’Homme (`Méditations poétiques (éd. originale 1820)/L’Homme`) [container]
    - text: Le Soir (`Méditations poétiques (éd. originale 1820)/Le Soir`) [container]
    - text: L’Immortalité (`Méditations poétiques (éd. originale 1820)/L’Immortalité`) [container]
    - text: Le Vallon (`Méditations poétiques (éd. originale 1820)/Le Vallon`) [container]
    - text: Le Désespoir (`Méditations poétiques (éd. originale 1820)/Le Désespoir`) [container]
    - text: La Providence à l’homme (`Méditations poétiques (éd. originale 1820)/La Providence à l’homme`) [container]
    - text: Souvenir (`Méditations poétiques (éd. originale 1820)/Souvenir`) [container]
    - text: L’Enthousiasme (`Méditations poétiques (éd. originale 1820)/L’Enthousiasme`) [container]
    - text: Le Lac (`Méditations poétiques (éd. originale 1820)/Le Lac`) [container]
    - … 14 more
1. `Le Corbeau (traduit par Stéphane Mallarmé)`: single, book, 1 parts, 0 missing, order –, validated
    - text: Le Corbeau (traduit par Stéphane Mallarmé)
1. `Poèmes et Sylves`: subpages, collection, 96 parts, 0 missing, order toc, proofread
    - toc: Poèmes et Sylves
    - volume: LE PÈLERIN PASSIONNÉ (`Poèmes et Sylves/Le Pèlerin passionné`) [hierarchy]
      - text: Agnès (`Poèmes et Sylves/Le Pèlerin passionné/Agnès`) [container]
      - text: Le Dit d’un chevalier qui se souvient (`Poèmes et Sylves/Le Pèlerin passionné/Le Dit d’un chevalier qui se souvient`) [container]
      - volume: Autant en emporte le vent (`Poèmes et Sylves/Le Pèlerin passionné/Autant en emporte le vent`) [container]
        - text: Épître (`Poèmes et Sylves/Le Pèlerin passionné/Autant en emporte le vent/Épître`) [container]
        - text: L’Investiture (`Poèmes et Sylves/Le Pèlerin passionné/Autant en emporte le vent/L’Investiture`) [container]
        - text: Chanson (Les courlis dans les roseaux…) (`Poèmes et Sylves/Le Pèlerin passionné/Autant en emporte le vent/Chanson (Les courlis dans les roseaux…)`) [container]
        - text: Chanson (On a marché sur les fleurs…) (`Poèmes et Sylves/Le Pèlerin passionné/Autant en emporte le vent/Chanson (On a marché sur les fleurs…)`) [container]
        - text: Chanson (Vous, avec vos yeux…) (`Poèmes et Sylves/Le Pèlerin passionné/Autant en emporte le vent/Chanson (Vous, avec vos yeux…)`) [container]
        - text: Chœur (`Poèmes et Sylves/Le Pèlerin passionné/Autant en emporte le vent/Chœur`) [container]
        - text: Une jeune fille parle (`Poèmes et Sylves/Le Pèlerin passionné/Autant en emporte le vent/Une jeune fille parle`) [container]
    - … 85 more
1. `Pauline (RDDM)`: subpages, periodical-issue, 2 parts, 0 missing, order chain, validated
    - toc: Pauline (RDDM)
    - chapter: Première partie (`Pauline (RDDM)/1`) [hierarchy]
    - chapter: Dernière partie (`Pauline (RDDM)/2`) [hierarchy]

