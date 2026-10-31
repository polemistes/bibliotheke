# fr.wikisource: structure and quality survey (WP1a)

Generated 2026-10-31 by `python -I -m wscatalog.survey fr` from `frwikisource-20261001-pages-articles1.xml-p1p1500000.bz2, frwikisource-20261001-pages-articles1.xml-p1500001p3000000.bz2, frwikisource-20261001-pages-articles1.xml-p3000001p4500000.bz2, frwikisource-20261001-pages-articles1.xml-p4500001p5004401.bz2` (resolution and quality rules: PLAN.md 5.2 steps 1–1h and 5.4; config `pipeline/config/fr.yaml`). Resolution took 47 s on the cached extract.

Input: 537,405 ns0 pages, 56,685 ns0 redirects, 4,030,797 scan pages, 28,847 Index pages, 10,886 pages with the `disambiguation` page prop.

## 1. Works

40,889 works resolved from 524,624 non-excluded ns0 pages; 40,195 enter the catalog after the hard exclusions (section 9). Multi-part works hold 484,586 parts, of which 372,246 are searchable texts.

| structure | works | parts | texts |
|---|---|---|---|
| single | 29,091 | 29,091 | 0 |
| subpages | 9,719 | 347,371 | 252,032 |
| virtual | 851 | 52,726 | 49,104 |
| linked | 815 | 22,874 | 17,765 |
| mixed | 413 | 61,615 | 53,345 |

| type (step 1e) | works |
|---|---|
| periodical-issue | 20,707 |
| book | 14,171 |
| collection | 5,049 |
| single | 543 |
| reference | 274 |
| document | 145 |

Largest works by parts:

| work | structure | type | parts | texts | missing | rules of the parts |
|---|---|---|---|---|---|---|
| L’Encyclopédie | virtual | reference | 41,677 | 41,610 | 0 | container 41632, hierarchy 44 |
| Dictionnaire de Trévoux/6e édition, 1771 | mixed | reference | 31,513 | 31,473 | 20 | container 31256, hierarchy 257 |
| Petit dictionnaire de la langue française (1836) | subpages | reference | 23,874 | 23,825 | 0 | hierarchy 23874 |
| Dictionnaire des patois romans de la Moselle | subpages | reference | 21,247 | 21,230 | 64 | container 21244, hierarchy 3 |
| Petit dictionnaire de la langue française (1860) | subpages | reference | 20,406 | 20,364 | 7 | hierarchy 20406 |
| Correspondance de Voltaire | subpages | collection | 10,426 | 387 | 2 | container 10375, hierarchy 51 |
| Petit dictionnaire de la langue française (Hocquart, 1831) | subpages | reference | 7,352 | 7,333 | 19 | hierarchy 7352 |
| Grand dictionnaire universel du XIXe siècle | subpages | reference | 5,834 | 5,829 | 19902 | container 5402, hierarchy 432 |
| Dictionnaire liégeois-français (Forir) | subpages | reference | 4,534 | 4,515 | 303 | container 4516, hierarchy 18 |
| Le Littré de la Grand’Côte/3e éd., 1903 | subpages | reference | 4,250 | 4,216 | 0 | hierarchy 4250 |
| Encyclopédie, ou Dictionnaire raisonné des sciences, des arts et des métiers | linked | reference | 3,858 | 3,856 | 5 | container 3858 |
| Dictionnaire infernal/6e éd., 1863 | subpages | reference | 3,826 | 3,795 | 0 | container 3825, hierarchy 1 |
| Thresor de la langue françoise | subpages | reference | 3,357 | 3,324 | 4690 | container 3300, hierarchy 57 |
| Cours d’agriculture (Rozier) | subpages | reference | 3,148 | 3,128 | 0 | container 3110, hierarchy 38 |
| Dictionnaire classique de la langue française (Bescherelle Jeune, 1880) | subpages | reference | 3,078 | 3,073 | 0 | hierarchy 3078 |

## 2. Exclusions (step 1)

| reason | pages |
|---|---|
| disambiguation | 8,455 |
| whole-text | 4,271 |
| versions | 55 |

## 3. Patterns A–I (WS§1)

|  | pattern | count |
|---|---|---|
| A | collection of root pages (`structure = linked`) | 815 works, 22,874 parts |
| B | mixed subpages and root pages (`mixed`) | 413 works, 61,615 parts |
| C | work with an oversized page (> oversized_bytes, transcluded scan pages included) | 605 works |
| D | missing root, virtual root (`virtual`) | 851 works, 52,726 parts |
| E | texts inside a subpage collection reached from a disambiguation or versions page | 11,365 parts (9,586 searchable); all searchable subpage texts: 345,292 |
| F | whole-text duplicate subpages (excluded) | 4,271 pages |
| G | reference works (type `reference`) | 274 works, 216,950 parts; 0 pages excluded by title prefix |
| H | deep nesting (parts at depth ≥ 2, i.e. three levels below the root) | 164 works |
| I | several editions: works with an other-versions template | 579 works; 55 versions pages excluded |

Tree depth of multi-part works (levels below the root): 1: 10,644, 2: 990, 3: 129, 4: 29, 5: 5, 6: 1.

## 4. Parent rules and order sources (steps 1b, 1c)

| parent rule | pages |
|---|---|
| container | 372,131 |
| hierarchy | 112,270 |
| own | 40,038 |
| toc | 185 |

| order source (nodes with ≥ 2 children) | nodes |
|---|---|
| toc | 8,327 |
| scan | 2,809 |
| chain | 2,559 |
| scan-volumes | 480 |
| natural | 194 |
| toc-partial | 74 |

`toc-partial` (implementation choice, see section 13): the TOC lists at least half of the children, which keep its order; the rest follow in natural order. 125 `natural` nodes have a TOC that lists fewer than half.

Root pages attached by their header's container link: 9,167; most frequent containers: `Lettres de Madame de Sévigné/Édition Monmerqué` 887; `Œuvres de P. Lachambeaudie. ― Fables et Poésies (1854)` 411; `Les Fables complètes (Viennet)` 213; `Parnasse de la Jeune Belgique` 162; `La Chanson française du XVe au XXe siècle` 147; `La Nuit (Gilkin)` 130; `Œuvres de Sully Prudhomme/Poésies 1866-1872` 128; `Œuvres de Théophile Gautier (Lemerre, 1890)` 124; `Échos de France` 121; `Poésies diverses (Corneille)` 109.

Pages attached by TOC membership (rule 3): 185; most frequent TOC pages: `Poésies Sociales des Ouvriers` 41; `Tamaris (RDDM)` 25; `Le Péché de Madeleine` 25; `Antonia (RDDM)` 12; `Œuvres complètes (Ovide)` 10; `Œuvres complètes de Platon, traduites par Émile Chambry` 8; `Œuvres complètes de François Arago` 6; `Œuvres complètes de Paul Verlaine` 4; `Femmes-Poëtes de la France` 4; `Césarine Dietrich (RDDM)` 3.

## 5. Container links that fail rule 1

36,658 of 412,369 pages whose header carries a container link (8.9 %) point to:

- outside-ns0: 64 (e.g. `Les Grans et Merveilleux Faictz du Seigneur Nemo` → `Livre:Montaiglon - Recueil de poésies françoises des 15e et 16e siècles, tome XI.djvu`, `Monsieur Rousset` → ``, `Le grand ballon captif à vapeur de M. Henri Giffard` → `Auteur:Henri Giffard`, `Phèdre (Racine), Didot, 1854/Acte II` → `Livre:Racine - Œuvres, Didot, 1854.djvu`)
- excluded: 33,176 (e.g. `Le Chemin de Fer du Pacifique et les Expéditions américaines dans l’Ouest` → `Revue des Deux Mondes`, `Histoire d’une minute` → `Le Figaro`, `Le Roi George V` → `Revue des Deux Mondes`, `Beaumarchais, sa vie, ses écrits et son temps/03` → `Revue des Deux Mondes`)
- missing: 3,418 (e.g. `Candide, ou l’Optimisme/Beuchot 1829/Chapitre 6` → `Œuvres de Voltaire/Beuchot/Tome 33`, `Pomme Warner's King` → `Bulletins d’arboriculture, de floriculture et de culture potagère`, `Adrien mourant à son âme` → `Œuvres complètes de Lord Byron`, `Lesley Castle` → `Amour & Amitié et autres œuvres de jeunesse`)

## 6. Prev/next chains across Index boundaries

Of 242,593 next-links between pages that both have an Index, 4,554 (1.9 %) cross to a different Index.
Examples: `Grand dictionnaire universel du XIXe siècle/Aartsen (pierre) (supplément)` (Larousse - Grand dictionnaire universel du XIXe siècle - Tome 16, part. 1, A-B.djvu) → `Grand dictionnaire universel du XIXe siècle/Aarwangen, suisse (supplément 2)` (Larousse - Grand dictionnaire universel du XIXe siècle - Tome 17, part. 1, A.djvu); `Pensées et Fragments inédits de Montesquieu/Notes (I)` (Montesquieu - Pensées et Fragments inédits, t1, 1899.djvu) → `Pensées et Fragments inédits de Montesquieu/V` (Montesquieu - Pensées et Fragments inédits, t2, 1901.djvu); `L’Espagne depuis la révolution de février/01` (Revue des Deux Mondes - 1849 - tome 2.djvu) → `L’Espagne depuis la révolution de février/02` (Revue des Deux Mondes - 1849 - tome 3.djvu); `Chronique de la quinzaine - 14 juin 1918` (Revue des Deux Mondes - 1918 - tome 45.djvu) → `Chronique de la quinzaine - 30 juin 1918` (Revue des Deux Mondes - 1918 - tome 46.djvu).

Root-page works that sit on a prev/next chain but stay separate works (no container, not in a unique TOC; WS§8 item 5): 3,382.

## 7. Orphan trees, oversized pages, red links

**Orphan trees** (virtual roots): 851, holding 52,726 parts. Examples: `Revue pour les Français Octobre 1907` (6); `Lettres de Catherine de Médicis` (1); `Revue des périodiques - Périodiques hollandais` (1); `Revue Musicale de Lyon 1904-03-23` (6); `Lexique de la langue algonquine` (2); `Bibliographie française` (1); `Le Figaro du 23 mars 1881` (1); `Histoire de Chartres` (1).

**Oversized pages** (> 300,000 bytes of wikitext including transcluded scan pages): 1,275. Largest: `Jean part1` 2.0 MB; `Le Système du Monde/Texte entier/Tome 6` 2.0 MB; `Essais/édition Michaud, 1907/Livre II/Chapitre 12` 1.9 MB; `Dictionnaire de la langue française du seizième siècle/A1` 1.9 MB; `Jean Chrysostome/Commentaire sur les Psaumes` 1.9 MB; `Thresor de la langue françoise/Nomenclator` 1.8 MB; `Le Système du Monde/Texte entier/Tome 7` 1.8 MB; `George Sand, sa vie et ses œuvres/Texte entier du tome 3` 1.8 MB; `George Sand, sa vie et ses œuvres/3` 1.8 MB; `Le Coran (Traduction de Savary)/Titre` 1.8 MB.

**Red links in TOCs** (missing parts): 1,043 multi-part works have red links, 72,552 in total; median share of the TOC 55%. Most missing: `Grand dictionnaire universel du XIXe siècle` 19902 of 25736; `Dictionnaire de théologie catholique` 5325 of 7312; `Dictionnaire de Trévoux/4e édition, 1743` 5112 of 5152; `Dictionnaire de Trévoux/5e édition, 1752` 5104 of 5231; `Thresor de la langue françoise` 4690 of 8047; `Dictionnaire historique de l’ancien langage françois/1re éd., 1875` 3462 of 3472; `Nouveau dictionnaire de pédagogie et d’instruction primaire` 1517 of 1665; `Nouvelle Biographie générale` 1252 of 1532.

## 8. Colon prefixes with ≥ 100 root pages

None.

## 9. Quality and completeness (PLAN.md 5.4)

**Hard exclusions**: 694 works — no-text 315, no-text-pages 165, index-faulty 109, under-300-bytes 105.

| tier | works | share | texts in them | source |
|---|---|---|---|---|
| validated | 11,255 | 28.0 % | 46,301 | page-levels 11,130, textquality 115, index-progress 10 |
| proofread | 21,246 | 52.9 % | 288,905 | page-levels 21,138, textquality 97, index-progress 11 |
| unproofread | 7,574 | 18.8 % | 35,838 | page-levels 7,398, textquality 102, index-progress 74 |
| unknown | 120 | 0.3 % | 281 | none 120 |

590 works are proofread by the ≥ 90 % rule. 5,187 of the 7,574 unproofread works have no page above level 1 (raw OCR).

**Incomplete**: 2,740 works — missing-parts 1,200, missing-pages 839, coverage 700, index-progress 1.
Works with missing scan pages inside a transcluded range: 1,086 (76,690 pages).
Coverage was computed for 2,906 works that are their Index's book; 854 cover under 95 % of the Index's text pages.
6 works transclude scan pages of files the dump has no Page: pages for (counted as no signal, not as missing).
Wikisource's own translations (labelled, kept): 0.

Text-quality template values: `100%` 98,462, `Textes validés` 52,459, `75%` 18,054, `50%` 3,194, `25%` 2,661, `00%` 5, `textes validés` 2, `Relu et corrigé` 1.
Index progress values: `C` 16,819, `T` 5,888, `V` 5,061, `OCR` 474, `L` 51, `MS` 39, `D` 8, `X` 2.
Works excluded as index-faulty, by the tier their page levels would give: unproofread 53, proofread 37, none 16, validated 3.

**Unknown works** (no signal): 120 in the catalog after the junk rules; the junk rules removed 105 (under-300-bytes 105).
Size: median 3,211 bytes, p10 677, p90 90,005; multi-part 13.

30 random unknown works:

- `Un coup de dés jamais n’abolira le hasard (édition monospaced)` (single, 58,098 B): __EXPECTWITHOUTSCANS__ 1234567890123456789012345678901234567890123456789012345678901234\|1234567890123456789012345678901234567890123456789012345678901234\| _ 10 2
- `Dictionnaire historique, topographique et biographique de la Mayenne` (subpages, 462,399 B): 
- `Pensées d’une nuit calme (Li Bai)` (single, 1,278 B): __EXPECTWITHOUTSCANS__ 靜夜思, Jing yè sī, Pensées d’une nuit calme === (Tangshi VII. 1. n° 233) 靜夜思 床前明月光， 疑是地上霜。 舉頭望明月， 低頭思故鄉。 Jing yè sī chuáng qián míng yuéguā
- `Commentaires sur Unto This Last de M. K. Gandhi` (single, 36,480 B): __EXPECTWITHOUTSCANS__ Catégorie:Thèses et Mémoires universitairesCatégorie:XXe siècle Gandhi, une pensée moderne == Dans un chapitre de son autobiographie inti
- `Histoire de l'Académie (Année 1746) et Réglement de l'Académie` (single, 1,265 B): Histoire de l'Académie (Année 1746) et Réglement de l'Académie Histoire de l'Académie (Année 1746) et Réglement de l'Académie
- `Seconde dissertation sur les éléments` (single, 2,572 B): Seconde dissertation sur les éléments - Eller Seconde dissertation sur les éléments - Eller
- `Cayenne` (single, 1,871 B): Je me souviens encor de ma première femme Ell' s'appelait Nina, un' vraie putain dans l'âme ! La Reine des morues de la plain' Saint-Denis, Ell' faisait le tapi
- `Lamarckiens et Darwiniens : Discussion de quelques théories sur la formation des espèces` (virtual, 1,121 B): 
- `L’Élémentarisme et son origine` (single, 7,387 B): Catégorie:Esthétique Catégorie:1928 Catégorie:De Stijl La polarité fondamentale de la structure naturelle est conditionnée dans tout ce qui nous entoure, par le
- `Discours à la Sorbonne` (single, 19,274 B): La distribution des prix aux élèves des sections parisiennes de l’Union Française de la Jeunesse a eu lieu le vendredi soir 12 juillet, dans le grand amphithéât
- `Les Pardaillan/XXXIV` (single, 36,904 B): __EXPECTWITHOUTSCANS__ Un double événement empêcha le maréchal de Damville de donner suite, cette nuit-là, à son projet. Chose étrange, en quittant Jeanne de Pi
- `Dictionnaire universel d’histoire et de géographie/14e éd., 1858` (single, 664 B): 
- `Théâtre de Meilhac et Halévy - Tome 7` (single, 419 B): Le prince \| Les Brigands \| La roussotte \| Carmen
- `A Blanqui (Louise Michel)` (single, 4,870 B): A ceux qui donnent leur vie pour le peuple, soit que la terre ait bu leur sang, soit qu’ils aient souffert dans les prisons, le peuple donne une majestueuse glo
- `À un morceau de pain blanc` (single, 1,054 B): C’était hier encore, je dévorais farouche, Mais l’espérance au cœur, cet abject aliment Que nous jetait Ferry, que Favre à l’âme louche Nous faisait digérer ave
- `Grande Ode à la contemplation du Go` (single, 11,836 B): __EXPECTWITHOUTSCANS__ Chinois simplifié : 人有精游艺，予尝观弈棋。 筭馀知造化，着外见几微。 好胜心无已，争先意不低。 当人尽宾主，对面如蛮夷。 财利激于衷，喜怒见于頄。 生杀在于手，与夺指于颐。 戾不殊冰炭，和不侔埙篪。 义不及朋友，情不通夫妻。 珠玉出怀袖，龙蛇走肝脾。 
- `Lettre de réunion des communes de Villeloin et de Coulangé` (single, 449 B): Catégorie:lettres
- `Relation du petit voyage que j’ai fait accompagné d’un ci-devant, charitable et bienfaisant...` (single, 12,956 B): Qui n’a plus qu’un moment à vivre, N’a plus rien à dissimuler. Lorsqu’on a, comme moi, 75 ans passés ; lorsqu’on ne peut plus manger, marcher, parler ; lorsqu’o
- `Traduction:La sottise humaine` (single, 7,429 B): Traduction Wikisource de :ro:Prostia omenească — On raconte qu’il était une fois un homme marié, et cet homme vivait sous le même toit avec sa belle-mère. Sa fe
- `Dissertation sur les éléments ou premiers principes des corps` (single, 2,462 B): Dissertation sur les éléments ou premiers principes des corps - Eller Dissertation sur les éléments ou premiers principes des corps - Eller
- `Un roi tout nu` (single, 404,927 B): Plus tard, quand je serai mort, mes livres serrés entre d’autres livres pourront attirer un regard. Et dès qu’un d’eux sera ouvert, ma pensée jaillira comme auj
- `Dernière Terre (recueil)` (subpages, 36,334 B): Catégorie:Poèmes d’Andrée Vernay Catégorie:Recueils de poèmes
- `Œuvres complètes d'Augustin Cauchy` (single, 2,393 B): Série 1 : Mémoires, notes et articles === Mémoires extrait des mémoires de l'académie des sciences : tome 1, tome 2, tome 3. Notes et articles extrait des compt
- `L’Éducation féministe des filles` (single, 90,005 B): Chapitre Premier. Valeur de l’éducation== On a dit avec raison que les peuples n’ont jamais que le gouvernement qu’ils méritent. Un peuple opprimé qui ne mérite
- `Les Délibérations de la commune de Mozac (France - 63) pendant la Première Guerre mondiale` (single, 18,785 B): __EXPECTWITHOUTSCANS__ Délibérations Délibérations Délibérations Délibérations Introduction === Comment la municipalité de Mozac et ses habitants ont-ils vécu l
- `Étude sur le corset (1907)` (single, 95,274 B): Mon but avait été primitivement de soutenir une thèse sur l’hygiène du vêtement en général, où chaque chapitre eut été consacré à une partie de cette importante
- `Fêtes de la faim/Comparaison de versions` (single, 1,741 B): __EXPECTWITHOUTSCANS__ Il existe deux versions de ce poème. class="wikitable" 1e version !! 2e version
- `Traité de dynamique/Partie 2/Chapitre 3-c` (single, 1,993 B): 
- `En regardant une partie de Go` (single, 2,542 B): __EXPECTWITHOUTSCANS__ 觀棋並序 — 观棋并序, En regardant une partie de Go. Chinois classique : 觀棋並序 五老峰前，白鶴遺址。 長松蔭庭，風日清美。 我時獨游，不逢一士。 誰歟棋者，戶外屨二。 不聞人聲，時聞落子。 紋枰坐對，誰究此味。 空鉤
- `Dénonciation d'un soldat français défaitiste pendant la Première Guerre Mondiale` (single, 2,603 B): __EXPECTWITHOUTSCANS__ « Calan, 21 mai 1915 Monsieur le Préfet, Je trouve qu’il est de mon devoir d’institutrice française de vous faire part des conversations 

**Default view** (validated or proofread, complete, not reference): 30,793 works with 120,352 texts; with unknown works shown: 30,904 works (120,404 texts). Works in the default view whose pages are all level 1: 0. Current setting: `show_unknown_by_default = None`.

## 10. Parts table size (WS§5.2)

| parts_cap | works with catalog parts | parts rows | parts table, gzipped | parts_fts, gzipped |
|---|---|---|---|---|
| 500 | 11,547 | 239,648 | 14.28 MB | 2.31 MB |
| 2,000 | 11,572 | 263,037 | 15.43 MB | 2.50 MB |
| ∞ (all, reference works included) | 11,764 | 494,548 | 24.88 MB | 4.65 MB |
| 2,000, compact encoding | 11,572 | 263,037 | 13.48 MB | 2.50 MB |

Rows count multi-part works only, one row per part plus the root row. 28,431 single-page works would add one row each if stored. Sizes are a SQLite file holding only the table (with its index) at gzip level 9. The compact encoding (not in WS§5.1, measured for the cap decision) stores a part's page_title as the suffix after the work root's title where possible, display_title only when it differs from the last title segment, and no per-part quality_pages.

## 11. Named cases (PLAN.md WP1 acceptance, WS§9)

- `La Légende des siècles`: work, mixed, type collection, 132 parts (128 texts), 0 missing, order scan-volumes, oversized False, quality unproofread (page-levels), complete False (missing-pages).
- `L’Épopée du ver`: part 131 of `La Légende des siècles` (text, display “L’Épopée du ver”, rule container, searchable True).
- `Clarté d’âmes`: part 74 of `La Légende des siècles` (text, display “Clarté d’âmes”, rule container, searchable True).
- `Madame Bovary`: work, mixed, type book, 57 parts (17 texts), 0 missing, order toc, oversized False, quality validated (page-levels), complete True.

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

### A: collections of root pages (815)

1. `Les Amours jaunes`: linked, collection, 94 parts, 0 missing, order scan, validated
    - toc: Les Amours jaunes
    - text: Le Poète et la cigale [container]
    - text: ÇA (`Ça ?`) [container]
    - text: PARIS (`Paris (Corbière)`) [container]
    - text: ÉPITAPHE (`Épitaphe (Corbière)`) [container]
    - text: À l’éternel madame [container]
    - text: Féminin singulier [container]
    - text: Bohême de chic [container]
    - text: Gente Dame [container]
    - text: 1 Sonnet [container]
    - text: Sonnet à sir Bob [container]
    - text: Steam-Boat [container]
    - … 83 more
1. `Voyage autour du monde fait dans les années 1740, 1, 2, 3, 4, par George Anson`: linked, book, 35 parts, 0 missing, order scan, unproofread
    - toc: Voyage autour du monde fait dans les années 1740, 1, 2, 3, 4, par George Anson
    - chapter: Préface (`Voyage autour du monde fait dans les années 1740, 1, 2, 3, 4/Préface`) [container]
    - text: Ch. I (`Voyage autour du monde fait dans les années 1740, 1, 2, 3, 4/Livre I/Ch. I`) [container]
    - text: Ch. II (`Voyage autour du monde fait dans les années 1740, 1, 2, 3, 4/Livre I/Ch. II`) [container]
    - text: Ch. III (`Voyage autour du monde fait dans les années 1740, 1, 2, 3, 4/Livre I/Ch. III`) [container]
    - text: Ch. IV (`Voyage autour du monde fait dans les années 1740, 1, 2, 3, 4/Livre I/Ch. IV`) [container]
    - text: Ch. V (`Voyage autour du monde fait dans les années 1740, 1, 2, 3, 4/Livre I/Ch. V`) [container]
    - text: Ch. VI (`Voyage autour du monde fait dans les années 1740, 1, 2, 3, 4/Livre I/Ch. VI`) [container]
    - text: Ch. VII (`Voyage autour du monde fait dans les années 1740, 1, 2, 3, 4/Livre I/Ch. VII`) [container]
    - text: Ch. VIII (`Voyage autour du monde fait dans les années 1740, 1, 2, 3, 4/Livre I/Ch. VIII`) [container]
    - text: Ch. IX (`Voyage autour du monde fait dans les années 1740, 1, 2, 3, 4/Livre I/Ch. IX`) [container]
    - text: Ch. X (`Voyage autour du monde fait dans les années 1740, 1, 2, 3, 4/Livre I/Ch. X`) [container]
    - … 24 more
1. `Feuilles éparses`: linked, collection, 18 parts, 0 missing, order toc, validated
    - toc: Feuilles éparses
    - text: Nuit, 1924 (Crevel) [container]
    - text: Métro [container]
    - text: Elle ne suffit pas l’éloquence [container]
    - text: Camille Desmoulins (Crevel) [container]
    - text: La Mysticité quotidienne de Max Jacob [container]
    - text: Le Cygne de Pau [container]
    - text: Je ne vendrai pas la commode de mon grand-père [container]
    - text: Mais si la mort n’était qu’un mot [container]
    - text: Le Poète et la nuit [container]
    - text: Merci, Paul Klee [container]
    - text: Bobards et fariboles [container]
    - … 7 more
1. `Le Feu follet (Drieu La Rochelle)`: linked, book, 1 parts, 0 missing, order single, proofread
    - front: Le Feu follet (Drieu La Rochelle)
    - text: Adieu à Gonzague [container]
1. `Des Ailes`: linked, book, 2 parts, 0 missing, order scan, unproofread
    - toc: Des Ailes
    - text: Luna (Isabelle Kaiser) [container]
    - text: La Ronce (Kaiser) [container]
1. `Satires (Juvénal)`: linked, book, 9 parts, 6 missing, order toc, unproofread
    - toc: Satires (Juvénal)
    - text: Satire III (`Juvénal Satire III (Traduction Raoul)`) [container]
    - text: Satire IV (`Juvénal Satire IV (Traduction Raoul)`) [container]
    - text: Satire V (`Juvénal Satire V (Traduction Raoul)`) [container]
    - text: Satire VI (`Juvénal Satire VI (Traduction Raoul)`) [container]
    - text: Satire VII (`Juvénal Satire VII (Traduction Raoul)`) [container]
    - text: Satire VIII (`Juvénal Satire VIII (Traduction Raoul)`) [container]
    - text: Satire IX (`Juvénal Satire IX (Traduction Raoul)`) [container]
    - text: Satire X (`Juvénal Satire X (Traduction Raoul)`) [container]
    - text: Satire XV (`Juvénal Satire XV (Traduction Raoul)`) [container]
1. `Contes de la Bécasse, Ollendorff`: linked, book, 17 parts, 0 missing, order toc, validated
    - toc: Contes de la Bécasse, Ollendorff
    - text: La Bécasse (`Contes de la bécasse, Ollendorff/01`) [container]
    - text: Ce Cochon de Morin (`Contes de la bécasse, Ollendorff/02`) [container]
    - text: La Folle (`Contes de la bécasse, Ollendorff/03`) [container]
    - text: Pierrot (`Contes de la bécasse, Ollendorff/04`) [container]
    - text: Menuet (`Contes de la bécasse, Ollendorff/05`) [container]
    - text: La Peur (`Contes de la bécasse, Ollendorff/06`) [container]
    - text: Farce normande (`Contes de la bécasse, Ollendorff/07`) [container]
    - text: Les Sabots (`Contes de la bécasse, Ollendorff/08`) [container]
    - text: La Rempailleuse (`Contes de la bécasse, Ollendorff/09`) [container]
    - text: En mer (`Contes de la bécasse, Ollendorff/10`) [container]
    - text: Un Normand (`Contes de la bécasse, Ollendorff/11`) [container]
    - … 6 more
1. `Description de la Grèce`: linked, book, 49 parts, 7 missing, order toc, unproofread
    - toc: Description de la Grèce
    - chapter: Livre V, chapitre I (`Pausanias, Elide-1, chapitre I`) [container]
    - chapter: Livre V, chapitre II (`Pausanias, Elide-1, chapitre II`) [container]
    - chapter: Livre V, chapitre III (`Pausanias, Elide-1, chapitre III`) [container]
    - chapter: Livre V, chapitre IV (`Pausanias, Elide-1, chapitre IV`) [container]
    - chapter: Livre V, chapitre V (`Pausanias, Elide-1, chapitre V`) [container]
    - chapter: Livre V, chapitre VI (`Pausanias, Elide-1, chapitre VI`) [container]
    - chapter: Livre V, chapitre VII (`Pausanias, Elide-1, chapitre VII`) [container]
    - chapter: Livre V, chapitre VIII (`Pausanias, Elide-1, chapitre VIII`) [container]
    - chapter: Livre V, chapitre IX (`Pausanias, Elide-1, chapitre IX`) [container]
    - chapter: Livre V, chapitre X (`Pausanias, Elide-1, chapitre X`) [container]
    - chapter: Livre V, chapitre XI (`Pausanias, Elide-1, chapitre XI`) [container]
    - … 38 more
1. `Catéchisme libertin`: linked, book, 9 parts, 0 missing, order chain, proofread
    - toc: Catéchisme libertin
    - chapter: Avant-Propos (`Le Catéchisme libertin/01`) [container]
    - text: Épître dédicatoire (`Le Catéchisme libertin/02`) [container]
    - text: Oraison à Ste-Magdeleine (`Le Catéchisme libertin/03`) [container]
    - text: Catéchisme Libertin (`Le Catéchisme libertin/04`) [container]
    - text: Oraison à Saint-Garcelin (`Le Catéchisme libertin/05`) [container]
    - text: Litanies des Filles de joie (`Le Catéchisme libertin/06`) [container]
    - text: Poésies libres et nouvelles (`Le Catéchisme libertin/07`) [container]
    - text: Approbation (`Le Catéchisme libertin/08`) [container]
    - text: Inventaire (`Le Catéchisme libertin/09`) [container]
1. `Le Livre de Raison du peintre Hyacinthe Rigaud`: linked, book, 3 parts, 88 missing, order toc, unproofread
    - toc: Le Livre de Raison du peintre Hyacinthe Rigaud
    - text: Le Livre de Raison du peintre Hyacinthe Rigaud - Index - Introduction [toc]
    - text: Le Livre de Raison du peintre Hyacinthe Rigaud – Index par dates - 1681 [container]
    - text: Le Livre de Raison du peintre Hyacinthe Rigaud - 1681, Monsieur Milord [toc]
1. `Le Chaland de la reine (Recueil)`: linked, book, 9 parts, 0 missing, order toc, unproofread
    - toc: Le Chaland de la reine (Recueil)
    - text: Le Chaland de la Reine (`Le Chaland de la reine (recueil)/Le Chaland de la Reine`) [container]
    - text: La Fiancée (`Le Chaland de la reine (recueil)/La Fiancée`) [container]
    - text: Au Feu ! (`Le Chaland de la reine (recueil)/Au Feu !`) [container]
    - text: Mère et Fille (`Le Chaland de la reine (recueil)/Mère et Fille`) [container]
    - text: Catiche (`Le Chaland de la reine (recueil)/Catiche`) [container]
    - text: Fragment de Lettre (`Le Chaland de la reine (recueil)/Fragment de Lettre`) [container]
    - text: Les Poulains (`Le Chaland de la reine (recueil)/Les Poulains`) [container]
    - text: Le Fantôme (`Le Chaland de la reine (recueil)/Le Fantôme`) [container]
    - text: Y a des Loups (`Le Chaland de la reine (recueil)/Y a des Loups`) [container]
1. `Ceux de la glèbe`: linked, book, 7 parts, 0 missing, order toc, validated
    - front: Ceux de la glèbe
    - text: La Genèse (`La Genèse (Lemonnier)`) [container]
    - text: La Glèbe (`La Glèbe (Lemonnier)`) [container]
    - text: Les Concubins [container]
    - text: Les Pidoux et les Colasse [container]
    - text: Le Pèlerinage (`Le Pèlerinage (Lemonnier)`) [container]
    - text: Le Suaire d’amour [container]
    - text: Un Marché (`Un marché`) [container]
1. `Traité du destin et du libre pouvoir`: linked, book, 1 parts, 0 missing, order single, unproofread
    - front: Traité du destin et du libre pouvoir
    - chapter: Avant-propos (`De la liberté et du hasard/Avant-propos`) [container]
1. `Du mystérieux au tragique`: linked, collection, 8 parts, 0 missing, order toc, validated
    - toc: Du mystérieux au tragique
    - text: La Boîte de laque [container]
    - text: L’Homme aux six montres [container]
    - text: L’Entonnoir de cuir [container]
    - text: La Pièce de musée [container]
    - text: Le Pot de caviar [container]
    - text: Le Chasseur de scarabées [container]
    - text: Le Train perdu [container]
    - text: En jouant avec le feu [container]
1. `Œuvres posthumes (Baudelaire) (1908)`: linked, collection, 7 parts, 0 missing, order scan, unproofread
    - toc: Œuvres posthumes (Baudelaire) (1908)
    - text: Les Contes de Champfleury (Baudelaire) [container]
    - text: De la caricature et généralement du comique dans les arts [container]
    - text: Description analytique d'une estampe de Boilly [container]
    - text: Catalogue de la collection de M. Crabbe [container]
    - text: Comment on paie ses dettes quand on a du génie [container]
    - text: Choix de maximes consolantes sur l’amour [container]
    - text: Le comédien Rouvière [container]
1. `Journal et mémoires du marquis D’Argenson`: linked, book, 755 parts, 0 missing, order chain, proofread
    - toc: Journal et mémoires du marquis D’Argenson
    - volume: 1697 à août 1738 (`Journal et mémoires du marquis d’Argenson, Renouard, 1859, T1`) [container]
      - chapter: INTRODUCTION (`Journal et mémoires du marquis d’Argenson/Introduction`) [container]
      - text: La famille d’Argenson (`Journal et mémoires du marquis d’Argenson/La famille d’Argenson`) [container]
      - chapter: 1697. (`Journal et mémoires du marquis d’Argenson/1697`) [container]
      - chapter: 1698. (`Journal et mémoires du marquis d’Argenson/1698`) [container]
      - chapter: 1704 (`Journal et mémoires du marquis d’Argenson/1704`) [container]
      - chapter: 1709. (`Journal et mémoires du marquis d’Argenson/1709`) [container]
      - chapter: 1712. (`Journal et mémoires du marquis d’Argenson/1712`) [container]
      - text: — Liaison (`Journal et mémoires du marquis d’Argenson/Liaison`) [container]
      - chapter: 1713. (`Journal et mémoires du marquis d’Argenson/1713`) [container]
      - chapter: 1716. (`Journal et mémoires du marquis d’Argenson/1716`) [container]
    - … 744 more
1. `La Décadence latine (Anthologie Contemporaine)`: linked, book, 5 parts, 0 missing, order scan, validated
    - toc: La Décadence latine (Anthologie Contemporaine)
    - chapter: I (`La Décadence latine/I`) [container]
    - chapter: II (`La Décadence latine/II`) [container]
    - chapter: III (`La Décadence latine/III`) [container]
    - chapter: IV (`La Décadence latine/IV`) [container]
    - chapter: V (`La Décadence latine/V`) [container]
1. `La foi des hommes`: linked, book, 2 parts, 0 missing, order single, validated
    - front: La foi des hommes
    - volume: L’Amour de la vie [container]
      - chapter: Préface (`Préface du supplément à L'Illustration du 10 février 1912`) [container]
1. `Le Danger des Liaisons`: linked, book, 6 parts, 0 missing, order scan-volumes, unproofread
    - toc: Le Danger des Liaisons
    - chapter: 1 (`Le Danger des liaisons/1/1`) [container]
    - chapter: 2 (`Le Danger des liaisons/1/2`) [container]
    - chapter: 1 (`Le Danger des liaisons/2/1`) [container]
    - chapter: 2 (`Le Danger des liaisons/2/2`) [container]
    - chapter: 1 (`Le Danger des liaisons/3/1`) [container]
    - chapter: 2 (`Le Danger des liaisons/3/2`) [container]
1. `La Bibliothèque des Goncourt`: linked, book, 3 parts, 0 missing, order toc, proofread
    - toc: La Bibliothèque des Goncourt
    - text: La bibliothèque des Goncourt (`Tourneux - La Bibliothèque des Goncourt, 1897/La bibliothèque des Goncourt`) [container]
    - text: L’œuvre des Goncourt — Essai bibliographique (`Tourneux - La Bibliothèque des Goncourt, 1897/L’œuvre des Goncourt — Essai bibliographique`) [container]
    - text: Index (`Tourneux - La Bibliothèque des Goncourt, 1897/Index`) [container]
1. `Symphonies et poèmes`: linked, collection, 1 parts, 0 missing, order single, unproofread
    - toc: Symphonies et poèmes
    - text: À la patrie [container]
1. `Propos d’au-delà`: linked, book, 6 parts, 0 missing, order toc, validated
    - toc: Propos d’au-delà
    - text: Maître Pied [container]
    - text: L’Amour sublime [container]
    - text: Le Meilleur Amour [container]
    - text: Les Filles de Milton [container]
    - text: Entre l’ancien et le nouveau [container]
    - text: Fragment de roman [container]
1. `Le Petit théâtre de Guignol, contenant trois pochades en un acte, imitées de Mourguet et Cie, 1874`: linked, collection, 4 parts, 0 missing, order toc, validated
    - toc: Le Petit théâtre de Guignol, contenant trois pochades en un acte, imitées de Mourguet et Cie, 1874
    - text: Guignol entre deux airs [container]
    - text: La Morale en bâton [container]
    - text: La Vertu récompensée [container]
    - text: Glossaire alphabétique (`Le Petit théâtre de Guignol/Glossaire alphabétique`) [container]
1. `Deux Voyages en Asie au XIIIe siècle`: linked, collection, 196 parts, 0 missing, order scan, validated
    - toc: Deux Voyages en Asie au XIIIe siècle
    - volume: Voyage de Marco Polo [container]
      - volume: Livre 2 (`Voyage de Marco Polo/Livre 2`) [hierarchy]
        - text: De la puissance et de la magnificence de Koubilaï, très grand roi des Tartares (`Voyage de Marco Polo/Livre 2/Chapitre 1`) [container]
        - text: Entete (`Voyage de Marco Polo/Livre 2/Entete`) [container]
        - text: De quelle manière le roi Koubilaï a souffert la rébellion de son oncle Naiam (`Voyage de Marco Polo/Livre 2/Chapitre 2`) [container]
        - text: De quelle manière Koubilaï se précautionna contre ses ennemis (`Voyage de Marco Polo/Livre 2/Chapitre 3`) [container]
        - text: De quelle manière Koubilaï vinquit Naiam (`Voyage de Marco Polo/Livre 2/Chapitre 4`) [container]
        - text: De quelle manière mourut Naiam (`Voyage de Marco Polo/Livre 2/Chapitre 5`) [container]
        - text: Koubilaï impose silence aux juifs et aux mahométans (`Voyage de Marco Polo/Livre 2/Chapitre 6`) [container]
        - text: De quelle manière le Grand Khan récompensa ses soldats (`Voyage de Marco Polo/Livre 2/Chapitre 7`) [container]
        - text: Portrait du roi Koubilaï, de ses femmes et de ses fils (`Voyage de Marco Polo/Livre 2/Chapitre 8`) [container]
    - … 185 more
1. `Description du royaume de Cambodge`: linked, book, 3 parts, 0 missing, order toc, validated
    - toc: Description du royaume de Cambodge
    - chapter: Avant-propos (`Description du royaume du Cambodge/Avant-propos`) [container]
    - text: Notice chronologique (`Description du royaume du Cambodge/Notice chronologique`) [container]
    - text: Description du pays de Tchin-la (`Description du royaume du Cambodge/Description du pays de Tchin-la`) [container]
1. `Le Crépuscule des nymphes`: linked, book, 15 parts, 0 missing, order toc, unproofread
    - toc: Le Crépuscule des nymphes
    - chapter: Chapitre I (`Lêda ou la louange des bienheureuses ténèbres/Chapitre 1`) [container]
    - chapter: Chapitre II (`Lêda ou la louange des bienheureuses ténèbres/Chapitre 2`) [container]
    - chapter: Chapitre III (`Lêda ou la louange des bienheureuses ténèbres/Chapitre 3`) [container]
    - chapter: Chapitre I (`Ariane ou Le chemin de la paix éternelle/Chapitre 1`) [container]
    - chapter: Chapitre II (`Ariane ou Le chemin de la paix éternelle/Chapitre 2`) [container]
    - chapter: Chapitre III (`Ariane ou Le chemin de la paix éternelle/Chapitre 3`) [container]
    - chapter: Chapitre I (`La Maison sur le Nil ou Les apparences de la vertu/Chapitre 1`) [container]
    - chapter: Chapitre II (`La Maison sur le Nil ou Les apparences de la vertu/Chapitre 2`) [container]
    - chapter: Chapitre III (`La Maison sur le Nil ou Les apparences de la vertu/Chapitre 3`) [container]
    - chapter: Chapitre I (`Byblis ou l’Enchantement des larmes/Chapitre 1`) [container]
    - chapter: Chapitre II (`Byblis ou l’Enchantement des larmes/Chapitre 2`) [container]
    - … 4 more
1. `Labyrinthe de Versailles (1677)`: linked, book, 41 parts, 0 missing, order toc, proofread
    - toc: Labyrinthe de Versailles (1677)
    - text: Duc & les Oiſeaux. (`Le Labyrinthe de Versailles/Fable1`) [container]
    - text: Les Cocs & la Perdrix. (`Le Labyrinthe de Versailles/Fable2`) [container]
    - text: Le Coc & le Renard. (`Le Labyrinthe de Versailles/Fable3`) [container]
    - text: Le Coc & le Diamant. (`Le Labyrinthe de Versailles/Fable4`) [container]
    - text: Le Chat pendu & les Rats. (`Le Labyrinthe de Versailles/Fable5`) [container]
    - text: L’aigle & le Renard. (`Le Labyrinthe de Versailles/Fable6`) [container]
    - text: Les Paons & le Geay. (`Le Labyrinthe de Versailles/Fable7`) [container]
    - text: Le Coc & le Coc d’Inde. (`Le Labyrinthe de Versailles/Fable8`) [container]
    - text: Les Paons & la Pie. (`Le Labyrinthe de Versailles/Fable9`) [container]
    - text: Le Serpent & la Lime. (`Le Labyrinthe de Versailles/Fable10`) [container]
    - text: Le Singe & ſes petits. (`Le Labyrinthe de Versailles/Fable11`) [container]
    - … 30 more
1. `La Poésie de G. Leopardi`: linked, book, 4 parts, 0 missing, order scan, unproofread
    - toc: La Poésie de G. Leopardi
    - text: A l’Italie (`À l’Italie (Leopardi)`) [container]
    - text: Sur le monument de Dante [container]
    - text: L’Infini (`La Poésie de Leopardi (trad. Lacaussade)/L’Infini`) [container]
    - text: Posa per sempre [container]
1. `Vers (Jammes, 1894)`: linked, collection, 49 parts, 0 missing, order toc, proofread
    - toc: Vers (Jammes, 1894)
    - text: Dédicace (`Vers, 1894/Dédicace`) [container]
    - text: J’ai été visiter (`Vers, 1894/J’ai été visiter`) [container]
    - text: Dont les fils vers les aventures s'en allèrent (`Vers, 1894/Dont les fils vers les aventures s'en allèrent`) [container]
    - text: Il y a par là (`Vers, 1894/Il y a par là`) [container]
    - text: Les villages (`Vers, 1894/Les villages`) [container]
    - text: Je mettrai (`Vers, 1894/Je mettrai`) [container]
    - text: J’étais gai (`Vers, 1894/J’étais gai`) [container]
    - text: Tu serais nue sur la bruyère (`Vers, 1894/Tu serais nue sur la bruyère`) [container]
    - text: Les grues (`Vers, 1894/Les grues`) [container]
    - text: La maison serait pleine de roses (`Vers, 1894/La maison serait pleine de roses`) [container]
    - text: La poussière des tamis (`Vers, 1894/La poussière des tamis`) [container]
    - … 38 more
1. `Inscriptions de l’Orkhon déchiffrées`: linked, book, 2 parts, 0 missing, order scan, unproofread
    - toc: Inscriptions de l’Orkhon déchiffrées
    - chapter: I. L'alphabet (`Inscriptions de l'Orkhon déchiffrées/I. L'alphabet`) [container]
    - chapter: II. Transcription et traduction des textes (monuments I et II) (`Inscriptions de l'Orkhon déchiffrées/II. Transcription et traduction des textes (monuments I et II)`) [container]

### B: mixed (413)

1. `La France socialiste`: mixed, book, 27 parts, 0 missing, order toc, proofread
    - toc: La France socialiste
    - chapter: Avant-propos (`La France socialiste/Avant-propos`) [container]
    - text: Définitions préliminaires (`La France socialiste/Définitions préliminaires`) [container]
    - text: Pourquoi ce livre ? (`La France socialiste/I`) [container]
    - text: L’Internationale — Karl Marx — Frédéric Engels (`La France socialiste/II`) [container]
    - text: Michel Bakounine — Fin de l’Internationale (`La France socialiste/III`) [container]
    - text: Les Fondateurs du parti socialiste en France (`La France socialiste/IV`) [container]
    - chapter: M. Jules Guesde (`La France socialiste/V`) [container]
    - text: Le Parti ouvrier (`La France socialiste/VI`) [container]
    - text: Les Débuts du collectivisme (`La France socialiste/VII`) [container]
    - text: Premier contact des socialistes allemands et individualistes français (`La France socialiste/VIII`) [container]
    - text: Triomphe du collectivisme (`La France socialiste/IX`) [container]
    - … 16 more
1. `Cours d’archéologie - les Indes, l’Égypte, l’Assyrie, la Palestine`: mixed, book, 7 parts, 0 missing, order scan, validated
    - toc: Cours d’archéologie - les Indes, l’Égypte, l’Assyrie, la Palestine
    - chapter: Introduction (`Cours d’archéologie - les Indes, l’Égypte, l’Assyrie, la Palestine/Introduction`) [container]
    - text: PREMIÈRE LECTURE (`Cours d’archéologie - les Indes, l’Égypte, l’Assyrie, la Palestine/Première lecture`) [container]
    - text: DEUXIÈME LECTURE (`Cours d’archéologie - les Indes, l’Égypte, l’Assyrie, la Palestine/Deuxième lecture`) [container]
    - text: TROISIÈME LECTURE (`Cours d’archéologie - les Indes, l’Égypte, l’Assyrie, la Palestine/Troisième lecture`) [container]
    - text: QUATRIÈME LECTURE (`Cours d’archéologie - les Indes, l’Égypte, l’Assyrie, la Palestine/Quatrième lecture`) [container]
    - text: Cinquième lecture (`Cours d’archéologie - les Indes, l’Égypte, l’Assyrie, la Paletine/Cinquième lecture`) [container]
    - text: Sixième lecture (`Cours d’archéologie - les Indes, l’Égypte, l’Assyrie, la Palestine/Sixième lecture`) [container]
1. `Œuvres de Marguerite d’Oingt`: mixed, book, 6 parts, 0 missing, order toc, proofread
    - toc: Œuvres de Marguerite d’Oingt
    - text: La Chartreuse de Poleteins (`Œuvres de Marguerite d’Oingt/La Chartreuse de Poleteins`) [container]
    - text: Sa famille, ses œuvres (`Œuvres de Marguerite d’Oingt/Sa famille, ses œuvres`) [container]
    - chapter: Notes (`Œuvres de Marguerite d’Oingt/Notes`) [container]
    - text: Meditationes (`Œuvres de Marguerite d’Oingt/Meditationes`) [container]
    - text: Speculum (`Speculum (Marguerite d’Oingt)`) [container]
    - text: La via seiti Biatrix [container]
1. `Récits populaires`: mixed, book, 33 parts, 0 missing, order single, proofread
    - toc: Récits populaires
    - volume: LES CONFESSIONS (1879-1881) (`Les Confessions (Tolstoï)`) [toc]
      - chapter: I (`Les Confessions (Tolstoï)/01`) [container]
      - chapter: II (`Les Confessions (Tolstoï)/02`) [container]
      - chapter: III (`Les Confessions (Tolstoï)/03`) [container]
      - chapter: IV (`Les Confessions (Tolstoï)/04`) [container]
      - chapter: V (`Les Confessions (Tolstoï)/05`) [container]
      - chapter: VI (`Les Confessions (Tolstoï)/06`) [container]
      - chapter: VII (`Les Confessions (Tolstoï)/07`) [container]
      - chapter: VIII (`Les Confessions (Tolstoï)/08`) [container]
      - chapter: IX (`Les Confessions (Tolstoï)/09`) [container]
      - chapter: X (`Les Confessions (Tolstoï)/10`) [container]
    - … 22 more
1. `Mathématiques et mathématiciens`: mixed, book, 44 parts, 0 missing, order scan, validated
    - toc: Mathématiques et mathématiciens
    - chapter: Préface (`Mathématiques et mathématiciens/Préface`) [container]
    - text: Objet et caractère des mathématiques (`Mathématiques et mathématiciens/Chp 1 - Section : Objet et caractère des mathématiques`) [container]
    - text: Notions primitives (`Mathématiques et mathématiciens/Chp 1 - Section : Notions primitives`) [container]
    - text: Méthodes (`Mathématiques et mathématiciens/Chp 1 - Section : Méthodes`) [container]
    - text: Géométrie et Analyse (`Mathématiques et mathématiciens/Chp 1 - Section : Géométrie et Analyse`) [container]
    - text: Les nombres, les symboles et les fonctions (`Mathématiques et mathématiciens/Chp 1 - Section : Les nombres, les symboles et les fonctions`) [container]
    - text: La limite, l’infiniment grand et l’infiniment petit (`Mathématiques et mathématiciens/Chp 1 - Section : La limite, l’infiniment grand et l’infiniment petit`) [container]
    - text: Mathématiques appliquées (`Mathématiques et mathématiciens/Chp 1 - Section : Mathématiques appliquées`) [container]
    - text: Système métrique (`Mathématiques et mathématiciens/Chp 1 - Section : Système métrique`) [container]
    - text: Géométrie descriptive (`Mathématiques et mathématiciens/Chp 1 - Section : Géométrie descriptive`) [container]
    - text: Mécanique (`Mathématiques et mathématiciens/Chp 1 - Section : Mécanique`) [container]
    - … 33 more
1. `Napoléon (Dumas)`: mixed, book, 9 parts, 0 missing, order toc, proofread
    - front: Napoléon (Dumas)
    - text: Napoléon de Buonaparte (`Napoléon (Dumas)/1`) [container]
    - text: Le général Bonaparte (`Napoléon (Dumas)/2`) [container]
    - text: Bonaparte premier consul (`Napoléon (Dumas)/3`) [container]
    - text: Napoléon empereur (`Napoléon (Dumas)/4`) [container]
    - text: Napoléon à l’île d’Elbe et les Cent jours (`Napoléon (Dumas)/5`) [container]
    - text: Napoléon à Sainte-Hélène (`Napoléon (Dumas)/6`) [container]
    - text: Gouvernement de Napoléon (`Napoléon (Dumas)/7`) [container]
    - text: Testament de Napoléon (`Napoléon (Dumas)/8`) [container]
    - text: Proclamation au bivouac avant la bataille d'Austerlitz [toc]
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
1. `Faust (Goethe, trad. Nerval, 1877)`: mixed, book, 72 parts, 0 missing, order scan, validated
    - toc: Faust (Goethe, trad. Nerval, 1877)
    - text: Notice (`Faust (Goethe, trad. Nerval, 1877)/Notice`) [container]
    - text: Préfaces (`Faust (Goethe, trad. Nerval, 1877)/Préfaces`) [container]
    - text: Dédicace (`Faust (Goethe, trad. Nerval, 1877)/Dédicace`) [container]
    - text: FAUST (`Faust (Goethe, trad. Nerval, 1877)/Faust/Entete`) [container]
    - chapter: Prologue sur le théâtre (`Faust (Goethe, trad. Nerval, 1877)/Faust/Prologue sur le théâtre`) [container]
    - chapter: Prologue dans le ciel (`Faust (Goethe, trad. Nerval, 1877)/Faust/Prologue dans le ciel`) [container]
    - chapter: Première partie (`Faust (Goethe, trad. Nerval, 1877)/Faust/Première partie`) [container]
    - chapter: Deuxième partie (`Faust (Goethe, trad. Nerval, 1877)/Faust/Deuxième partie`) [container]
    - text: Intermède (`Faust (Goethe, trad. Nerval, 1877)/Faust/Intermède`) [container]
    - chapter: Troisième partie (`Faust (Goethe, trad. Nerval, 1877)/Faust/Troisième partie`) [container]
    - text: SECOND FAUST (`Faust (Goethe, trad. Nerval, 1877)/Second Faust/Entete`) [container]
    - … 61 more
1. `L’Heptaméron des nouvelles`: mixed, book, 127 parts, 0 missing, order scan-volumes, proofread
    - front: L’Heptaméron des nouvelles
    - volume: Tome premier (`L’Heptaméron des nouvelles/Tome premier`) [container]
      - text: Avertissement (`L’Heptaméron des nouvelles/Avertissement`) [container]
      - text: Oraison funèbre de la mort de l’incomparable Marguerite, Royne de Navarre & Duchesse d’Alençon, composée en latin par Charles de Sainte-Marthe, & traduicte par lui en langue françoise (`L’Heptaméron des nouvelles/Oraison funèbre`) [container]
      - text: Extraits de Brantôme (`L’Heptaméron des nouvelles/Extraits de Brantôme`) [container]
      - text: Notice des manuscrits de l’Heptaméron de la Reine de Navarre. — L. (`L’Heptaméron des nouvelles/Notice des manuscrits`) [container]
      - text: Notice des éditions de l’Heptaméron. — L. (`L’Heptaméron des nouvelles/Notice des éditions`) [container]
      - text: Notice des manuscrits & des éditions des Poésies de la Reine de Navarre. — L. (`L’Heptaméron des nouvelles/Notice des manuscrits & des éditions des Poésies`) [container]
      - text: Notice d’un manuscrit (Instruction en la Religion chrétienne pour les enfants), composé pour la Reine de Navarre. — L. (`L’Heptaméron des nouvelles/Notice d’un manuscrit`) [container]
      - text: Indication de quelques portraits originaux de la Reine de Navarre. — L. (`L’Heptaméron des nouvelles/Indication de quelques portraits`) [container]
      - text: Notice de l’édition de Berne. — M. (`L’Heptaméron des nouvelles/Notice de l’édition de Berne`) [container]
      - chapter: Prologue général (`L’Heptaméron des nouvelles/Prologue`) [container]
    - … 116 more
1. `Les Parisiennes de Paris`: mixed, collection, 22 parts, 0 missing, order toc, proofread
    - toc: Les Parisiennes de Paris
    - text: À Théodore Barrière [container]
    - text: Devant le rideau [container]
    - text: La Femme-Ange (`Les Parisiennes de Paris/1`) [container]
    - text: La Bonne des Grandes occasions (`Les Parisiennes de Paris/2`) [container]
    - text: L’Ingénue de Théâtre (`Les Parisiennes de Paris/3`) [container]
    - text: La Maîtresse qui n’a pas d’âge (`Les Parisiennes de Paris/4`) [container]
    - text: Le Cœur de marbre (`Les Parisiennes de Paris/5`) [container]
    - text: La Dame aux peignoirs (`Les Parisiennes de Paris/6`) [container]
    - text: Galatée idiote (`Les Parisiennes de Paris/7`) [container]
    - text: La Femme de treize ans (`Les Parisiennes de Paris/8`) [container]
    - text: La Jeune fille honnête (`Les Parisiennes de Paris/9`) [container]
    - … 11 more
1. `Rabevel ou Le mal des ardents`: mixed, book, 14 parts, 0 missing, order scan-volumes, proofread
    - toc: Rabevel ou Le mal des ardents
    - volume: Tome I (`Rabevel ou Le mal des ardents/Tome I`) [hierarchy]
      - chapter: Chapitre premier (`Rabevel ou le mal des ardents/01/01`) [container]
      - chapter: Chapitre deuxième (`Rabevel ou le mal des ardents/01/02`) [container]
      - chapter: Chapitre troisième (`Rabevel ou le mal des ardents/01/03`) [container]
      - chapter: Chapitre quatrième (`Rabevel ou le mal des ardents/01/04`) [container]
    - volume: Tome II (`Rabevel ou Le mal des ardents/Tome II`) [hierarchy]
      - chapter: Chapitre premier (`Rabevel ou le mal des ardents/02/01`) [container]
      - chapter: Chapitre deuxième (`Rabevel ou le mal des ardents/02/02`) [container]
      - chapter: Chapitre troisième (`Rabevel ou le mal des ardents/02/03`) [container]
      - chapter: Chapitre quatrième (`Rabevel ou le mal des ardents/02/04`) [container]
    - volume: Tome III (`Rabevel ou Le mal des ardents/Tome III`) [hierarchy]
    - … 3 more
1. `Des artistes`: mixed, book, 8 parts, 69 missing, order scan-volumes, unproofread
    - toc: Des artistes
    - text: Claude Monet (`Des artistes/Claude Monet (1)`) [hierarchy]
    - text: Paul Gauguin (`Des artistes/Paul Gauguin`) [container]
    - text: Vincent van Gogh (`Des artistes/Vincent van Gogh`) [hierarchy]
    - text: Camille Pissarro (`Des artistes/Camille Pissarro`) [hierarchy]
    - text: Auguste Rodin (`Des artistes/Auguste Rodin (3)`) [hierarchy]
    - text: Claude Monet (Figaro) [container]
    - text: Claude Monet (`Des artistes/Claude Monet — Venise`) [hierarchy]
    - text: Eugène Carrière (`Des artistes/Eugène Carrière`) [container]
1. `Contribution de la Guadeloupe à la pensée française`: mixed, book, 4 parts, 7 missing, order scan, proofread
    - toc: Contribution de la Guadeloupe à la pensée française
    - text: (Général Jacques-Coquille) (`Contribution de la Guadeloupe à la pensée française/Jacques-Coquille Dugommier`) [container]
    - text: pagination (test technique) (`Contribution de la Guadeloupe à la pensée française/pagination (test technique)`) [container]
    - text: Germain (`Contribution de la Guadeloupe à la pensée française/Germain Léonard`) [hierarchy]
    - text: (Julien de) (`Le Mauvais Génie (Julien de Mallian)`) [toc]
1. `Œuvres complètes de Charles Péguy`: mixed, book, 46 parts, 2 missing, order scan-volumes, proofread
    - front: Œuvres complètes de Charles Péguy
    - volume: Tome 1 (`Œuvres complètes de Charles Péguy/Tome 1`) [container]
      - chapter: Introduction (`Œuvres complètes de Charles Péguy/Tome 1/Introduction`) [container]
      - chapter: Lettre du provincial (`Œuvres complètes de Charles Péguy/Tome 1/Lettre du provincial`) [container]
      - text: Le triomphe de la république (`Œuvres complètes de Charles Péguy/Tome 1/Le triomphe de la république`) [container]
      - text: Du second provincial (`Œuvres complètes de Charles Péguy/Tome 1/Du second provincial`) [container]
      - text: De la grippe (`Œuvres complètes de Charles Péguy/Tome 1/De la grippe`) [container]
      - text: Encore de la grippe (`Œuvres complètes de Charles Péguy/Tome 1/Encore de la grippe`) [container]
      - text: Toujours de la grippe (`Œuvres complètes de Charles Péguy/Tome 1/Toujours de la grippe`) [container]
      - text: Entre deux trains (`Œuvres complètes de Charles Péguy/Tome 1/Entre deux trains`) [container]
      - text: Deuxième série au provincial (`Œuvres complètes de Charles Péguy/Tome 1/Deuxième série au provincial`) [container]
      - text: Pour ma maison (`Œuvres complètes de Charles Péguy/Tome 1/Pour ma maison`) [container]
    - … 35 more
1. `Zola, Dumas, Maupassant`: mixed, collection, 7 parts, 0 missing, order toc, validated
    - toc: Zola, Dumas, Maupassant
    - chapter: Avant-propos du traducteur (`Zola, Dumas, Maupassant/Avant-propos du traducteur`) [container]
    - text: À la jeunesse (`À la jeunesse (Zola)`) [container]
    - text: Le mysticisme à l’École (`Le Mysticisme à l’école`) [container]
    - text: Zola et Dumas (`Zola, Dumas, Maupassant/Zola et Dumas`) [container]
    - text: Guy de Maupassant (`Zola, Dumas, Maupassant/Guy de Maupassant`) [container]
    - text: Science et religion (`Zola, Dumas, Maupassant/Science et religion`) [container]
    - chapter: Appendice (`Zola, Dumas, Maupassant/Appendice`) [container]
1. `Mémoires artistiques de Mlle Péan de La Roche-Jagu, écrits par elle-même`: mixed, book, 44 parts, 0 missing, order toc, validated
    - toc: Mémoires artistiques de Mlle Péan de La Roche-Jagu, écrits par elle-même
    - chapter: Chapitre . (`Mémoires artistiques de Mlle Péan de La Roche-Jagu, écrits par elle-même/Chapitre I`) [container]
    - chapter: II. (`Mémoires artistiques de Mlle Péan de La Roche-Jagu, écrits par elle-même/Chapitre II`) [container]
    - chapter: III. (`Mémoires artistiques de Mlle Péan de La Roche-Jagu, écrits par elle-même/Chapitre III`) [container]
    - chapter: IV. (`Mémoires artistiques de Mlle Péan de La Roche-Jagu, écrits par elle-même/Chapitre IV`) [container]
    - chapter: V. (`Mémoires artistiques de Mlle Péan de La Roche-Jagu, écrits par elle-même/Chapitre V`) [container]
    - chapter: VI. (`Mémoires artistiques de Mlle Péan de La Roche-Jagu, écrits par elle-même/Chapitre VI`) [container]
    - chapter: VII. (`Mémoires artistiques de Mlle Péan de La Roche-Jagu, écrits par elle-même/Chapitre VII`) [container]
    - chapter: VIII. (`Mémoires artistiques de Mlle Péan de La Roche-Jagu, écrits par elle-même/Chapitre VIII`) [container]
    - chapter: IX. (`Mémoires artistiques de Mlle Péan de La Roche-Jagu, écrits par elle-même/Chapitre IX`) [container]
    - chapter: X. (`Mémoires artistiques de Mlle Péan de La Roche-Jagu, écrits par elle-même/Chapitre X`) [container]
    - chapter: XI. (`Mémoires artistiques de Mlle Péan de La Roche-Jagu, écrits par elle-même/Chapitre XI`) [container]
    - … 33 more
1. `Deux pages de l’histoire d’Amérique`: mixed, book, 7 parts, 0 missing, order toc, validated
    - toc: Deux pages de l’histoire d’Amérique
    - text: Deux pages de l’histoire d’Amérique (`Deux pages de l'histoire d'Amérique/1`) [container]
    - chapter: Chapitre I (`Deux pages de l'histoire d'Amérique/2`) [container]
    - chapter: Chapitre II (`Deux pages de l'histoire d'Amérique/3`) [container]
    - chapter: Chapitre III (`Deux pages de l'histoire d'Amérique/4`) [container]
    - text: Sur le droit des gens (`Deux pages de l’histoire d’Amérique/5`) [container]
    - chapter: Introduction à l’Étude de la Géologie (`Deux pages de l'histoire d'Amérique/6`) [container]
    - text: Le code Napoléon (`Deux pages de l’histoire d’Amérique/7`) [container]
1. `Poésies nouvelles (Tastu)`: mixed, collection, 49 parts, 0 missing, order scan, proofread
    - toc: Poésies nouvelles (Tastu)
    - volume: Peau-d’Âne, mythe. (`Poésies nouvelles (Tastu)/Peau-d’Âne, mythe`) [container]
      - text: Prélude (`Poésies nouvelles (Tastu)/Peau-d’Âne, mythe/Prélude`) [container]
      - text: Le Prologue (`Poésies nouvelles (Tastu)/Peau-d’Âne, mythe/Le Prologue`) [container]
      - text: Première journée (`Poésies nouvelles (Tastu)/Peau-d’Âne, mythe/Première journée`) [container]
      - text: Deuxième journée (`Poésies nouvelles (Tastu)/Peau-d’Âne, mythe/Deuxième journée`) [container]
      - text: Troisième journée (`Poésies nouvelles (Tastu)/Peau-d’Âne, mythe/Troisième journée`) [container]
      - text: L’Épilogue (`Poésies nouvelles (Tastu)/Peau-d’Âne, mythe/L’Épilogue`) [container]
    - text: À M. F. Guizot. Commémoration. (`Poésies nouvelles (Tastu)/À M. F. Guizot. Commémoration`) [container]
    - text: Invocation. (`Poésies nouvelles (Tastu)/Invocation`) [container]
    - text: À Béranger en prison, le 14 juillet 1829. (`Poésies nouvelles (Tastu)/À Béranger en prison, le 14 juillet 1829`) [container]
    - chapter: Chant. (`Poésies nouvelles (Tastu)/Chant`) [container]
    - … 38 more
1. `Observations sur Le festin de pierre`: mixed, book, 4 parts, 0 missing, order toc, unproofread
    - toc: Observations sur Le festin de pierre
    - text: Notice bibliographique (`Observations sur Le festin de pierre/Notice`) [container]
    - text: Observations sur une comédie de Molière intitulée Le festin de pierre [container]
    - chapter: Lettre sur les observations (`Observations sur Le festin de pierre/Lettre sur les Observations`) [container]
    - text: Réponse aux Observations (`Observations sur Le festin de pierre/Réponse aux Observations`) [container]
1. `Poèmes ironiques`: mixed, book, 3 parts, 0 missing, order toc, validated
    - toc: Poèmes ironiques
    - text: Lamentation de la lumière (`Poèmes ironiques/Lamentation de la lumière`) [container]
    - text: Lutte parisienne (`Poèmes ironiques/Lutte parisienne`) [container]
    - text: La révolte de la machine (`Les Billets bleus/La révolte de la machine`) [container]
1. `Recueil des publications scientifiques de Ferdinand de Saussure`: mixed, book, 21 parts, 41 missing, order toc, proofread
    - front: Recueil des publications scientifiques de Ferdinand de Saussure
    - chapter: PRÉFACE (`Recueil des publications scientifiques de Ferdinand de Saussure/Préface`) [hierarchy]
    - volume: TABLE DES MATIÈRES (`Recueil des publications scientifiques de Ferdinand de Saussure/Table des matières`) [container]
      - text: Ἀγαμέμνων [container]
      - text: Védique líbuģā, paléoslave lobŭzati [container]
      - text: Sūdo [container]
      - text: Vieux haut-allemand murg, murgi [container]
      - text: Ἀδήν [container]
      - text: Lūdus [container]
      - text: Grec ἀλϰυών, allemand Schwalbe [container]
      - text: Νυστάζω [container]
      - text: Λύθρον [container]
    - … 10 more
1. `Parnasse de la Jeune Belgique`: mixed, collection, 193 parts, 0 missing, order toc, proofread
    - toc: Parnasse de la Jeune Belgique
    - text: À l’Océan (`À l’Océan (Émile Van Arenbergh)`) [container]
    - text: Sonnet mignard (`Sonnet mignard (Émile Van Arenbergh)`) [container]
    - text: Sonnet d’antan (`Sonnet d’antan (Émile Van Arenbergh)`) [container]
    - text: Sonnet d’hiver (`Sonnet d’hiver (Émile Van Arenbergh)`) [container]
    - text: À la Nuit (`À la Nuit (Émile Van Arenbergh)`) [container]
    - text: La Nymphe (`La Nymphe (Émile Van Arenbergh)`) [container]
    - text: Souvenir (`Souvenir (Émile Van Arenbergh)`) [container]
    - text: Devant la Mer (`Devant la Mer (Émile Van Arenbergh)`) [container]
    - text: Moyen-Âge (`Moyen-Âge (Émile Van Arenbergh)`) [container]
    - text: Sonnet oriental (`Sonnet oriental (Émile Van Arenbergh)`) [container]
    - text: Le Vésuve (`Le Vésuve (Émile Van Arenbergh)`) [container]
    - … 182 more
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
1. `Œuvres complètes de François Arago`: mixed, collection, 478 parts, 0 missing, order scan-volumes, proofread
    - front: Œuvres complètes de François Arago
    - chapter: Introduction (`Œuvres complètes de François Arago/Tome I/Introduction`) [container]
    - text: Histoire de ma jeunesse (`Œuvres complètes de François Arago/Tome I/Histoire de ma jeunesse`) [container]
    - text: Notices biographiques (`Œuvres complètes de François Arago/Tome I/Notices biographiques`) [container]
    - text: Avertissement (`Œuvres complètes de François Arago/Tome I/Notices biographiques : Avertissement`) [container]
    - chapter: Introduction (`Fresnel (Arago)/Introduction`) [container]
    - text: Enfance de Fresnel. — Son entrée à l’École polytechnique et dans le corps des ponts et chaussées. — Sa destitution pour avoir été rejoindre l’armée royale à La Palud (`Fresnel (Arago)/1`) [container]
    - text: Premiers Mémoires de Fresnel (`Fresnel (Arago)/2`) [container]
    - text: Réfraction (`Fresnel (Arago)/3`) [container]
    - text: Interférences (`Fresnel (Arago)/4`) [container]
    - text: Polarisation (`Fresnel (Arago)/5`) [container]
    - text: Caractères principaux du système de l’émission et de celui des ondes. — Motifs sur lesquels Fresnel s’était fondé pour rejeter sans réserve le système de l’émission (`Fresnel (Arago)/6`) [container]
    - … 467 more
1. `À travers l'Afrique`: mixed, book, 39 parts, 0 missing, order toc, validated
    - toc: À travers l'Afrique
    - chapter: Préface (`À travers l'Afrique/Préface`) [container]
    - text: Chapitre01 (`À travers l'Afrique/Chapitre01`) [container]
    - text: Chapitre02 (`À travers l'Afrique/Chapitre02`) [container]
    - text: Chapitre03 (`À travers l'Afrique/Chapitre03`) [container]
    - text: Chapitre04 (`À travers l'Afrique/Chapitre04`) [container]
    - text: Chapitre05 (`À travers l’Afrique/Chapitre05`) [container]
    - text: Chapitre06 (`À travers l’Afrique/Chapitre06`) [container]
    - text: Chapitre07 (`À travers l’Afrique/Chapitre07`) [container]
    - text: Chapitre08 (`À travers l’Afrique/Chapitre08`) [container]
    - text: Chapitre09 (`À travers l’Afrique/Chapitre09`) [container]
    - text: Chapitre10 (`À travers l’Afrique/Chapitre10`) [container]
    - … 28 more
1. `L’Année terrible`: mixed, collection, 98 parts, 0 missing, order scan-volumes, proofread
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
1. `Le Manifeste communiste`: mixed, book, 11 parts, 0 missing, order scan-volumes, proofread
    - toc: Le Manifeste communiste
    - text: Préfaces des auteurs (`Manifeste du parti communiste/Andler/Préface`) [container]
    - text: Le Manifeste communiste (`Manifeste du parti communiste/Andler`) [container]
    - chapter: Appendice. — La collaboration de F. Engels à la Réforme (`Manifeste du parti communiste/Andler/Appendice`) [container]
    - volume: II (`Le Manifeste communiste/II`) [hierarchy]
      - chapter: Introduction historique (`Manifeste du parti communiste/Andler/II/Introduction`) [container]
      - text: Commentaire (`Manifeste du parti communiste/Andler/II/Commentaire`) [container]
      - chapter: 1. Bourgeois et prolétaires (`Manifeste du parti communiste/Andler/II/Commentaire/1`) [container]
      - chapter: 2. Prolétaires et communistes (`Manifeste du parti communiste/Andler/II/Commentaire/2`) [container]
      - chapter: 3. Littérature socialiste et communiste (`Manifeste du parti communiste/Andler/II/Commentaire/3`) [container]
      - chapter: 4. Attitude des communistes devant les divers partis d’opposition (`Manifeste du parti communiste/Andler/II/Commentaire/4`) [container]
      - chapter: Conclusion (`Manifeste du parti communiste/Andler/II/Conclusion`) [container]
1. `Les Poèmes d’Edgar Poe`: mixed, collection, 39 parts, 0 missing, order scan-volumes, unproofread
    - front: Les Poèmes d’Edgar Poe
    - text: Édition 1888 (`Les Poèmes d’Edgar Poe/Édition 1888`) [hierarchy]
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
    - … 28 more
1. `La Légende des siècles`: mixed, collection, 132 parts, 0 missing, order scan-volumes, unproofread
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
1. `Œuvres complètes de Benvenuto Cellini`: mixed, collection, 14 parts, 2 missing, order toc, proofread
    - toc: Œuvres complètes de Benvenuto Cellini
    - chapter: CHAPITRE V. (`Mémoires (Cellini)/t2-l7-5`) [container]
    - volume: Tome 1 (`Œuvres complètes de Benvenuto Cellini/tome 1`) [container]
      - text: Dédicace (`Mémoires (Cellini)/t1-Dédicace`) [container]
      - chapter: Préface (`Mémoires (Cellini)/t1-Préface`) [container]
      - chapter: I. (`Mémoires (Cellini)/t1-l1-1`) [container]
      - chapter: II. (`Mémoires (Cellini)/t1-l1-2`) [container]
      - chapter: III. (`Mémoires (Cellini)/t1-l1-3`) [container]
      - chapter: IV. (`Mémoires (Cellini)/t1-l1-4`) [container]
      - chapter: V. (`Mémoires (Cellini)/t1-l1-5`) [container]
      - chapter: VI. (`Mémoires (Cellini)/t1-l1-6`) [container]
      - chapter: I. (`Mémoires (Cellini)/t1-l2-1`) [container]
    - … 3 more

### D: virtual roots (851)

1. `Chansons et Monologues (Bruant)`: virtual, book, 1 parts, 0 missing, order single, proofread
    - text: Le Boulevard des Étudiants (`Chansons et Monologues (Bruant)/Le Boulevard des Étudiants`)
1. `Nouvelle Revue Française`: virtual, periodical-issue, 1 parts, 0 missing, order single, unproofread
    - text: Jules Renard : Nos frères farouches, Ragotte (`Nouvelle Revue Française/1909, volume 2/Notes/Jules Renard : Nos frères farouches, Ragotte`)
1. `Études sur le roman anglais contemporains`: virtual, periodical-issue, 1 parts, 0 missing, order single, proofread
    - text: Les romans de Wilkie Collins (`Études sur le roman anglais contemporains/Les romans de Wilkie Collins`)
1. `Le Roman en France pendant le XIXe siècle (Eugène Gilbert)`: virtual, book, 2 parts, 0 missing, order scan, proofread
    - chapter: 6 (`Le Roman en France pendant le XIXe siècle (Eugène Gilbert)/III/6`)
    - text: Index des noms cités (`Le Roman en France pendant le XIXe siècle (Eugène Gilbert)/Index des noms cités`) [hierarchy]
1. `Bulletins d’arboriculture, de floriculture et de culture potagère`: virtual, periodical-issue, 30 parts, 0 missing, order scan-volumes, unproofread
    - text: Pêche Mme Narcisse Gaujard (`Bulletins d’arboriculture, de floriculture et de culture potagère/Pêche Mme Narcisse Gaujard`)
    - text: Pommes Belle-Fleur (`Bulletins d’arboriculture, de floriculture et de culture potagère/Pommes Belle-Fleur`) [hierarchy]
    - text: Poire Crassane Du Mortier (`Bulletins d’arboriculture, de floriculture et de culture potagère/Poire Crassane Du Mortier`) [hierarchy]
    - text: Pêche Grosse Mignonne (`Bulletins d’arboriculture, de floriculture et de culture potagère/Pêche Grosse Mignonne`) [hierarchy]
    - text: Pomme Borawinskji (`Bulletins d’arboriculture, de floriculture et de culture potagère/Pomme Borawinskji`) [hierarchy]
    - text: Pêche Alexandra Nobless (`Bulletins d’arboriculture, de floriculture et de culture potagère/Pêche Alexandra Nobless`) [hierarchy]
    - text: Poire Ananas de Courtrai (`Bulletins d’arboriculture, de floriculture et de culture potagère/Poire Ananas de Courtrai`) [hierarchy]
    - text: Prune Columbia (`Bulletins d’arboriculture, de floriculture et de culture potagère/Prune Columbia`) [hierarchy]
    - text: Pêche Dagmar (`Bulletins d’arboriculture, de floriculture et de culture potagère/Pêche Dagmar`) [hierarchy]
    - text: Poire Délices Cuvelier (`Bulletins d’arboriculture, de floriculture et de culture potagère/Poire Délices Cuvelier`) [hierarchy]
    - text: Pomme Petit Emma (`Bulletins d’arboriculture, de floriculture et de culture potagère/Pomme Petit Emma`) [hierarchy]
    - text: Poire Beurré Degallait (`Bulletins d’arboriculture, de floriculture et de culture potagère/Poire Beurré Degallait`) [hierarchy]
    - … 18 more
1. `De la maçonnerie parmi les chrétiens`: virtual, book, 1 parts, 0 missing, order single, validated
    - chapter: Section 2 (`De la maçonnerie parmi les chrétiens/Section 2`)
1. `Portraits et Souvenirs littéraires`: virtual, book, 2 parts, 0 missing, order scan, proofread
    - text: Gérard de Nerval (`Portraits et Souvenirs littéraires/Gérard de Nerval`)
    - text: Madame Émile de Girardin (`Portraits et Souvenirs littéraires/Madame Émile de Girardin`) [hierarchy]
1. `Sermons sur l’Écriture`: virtual, book, 1 parts, 0 missing, order single, proofread
    - text: Troisième série Sermons divers (`Sermons sur l’Écriture/Troisième série Sermons divers`)
1. `Trésor de livres rares et précieux - Graesse, 1861`: virtual, reference, 1 parts, 0 missing, order single, unproofread
    - text: Encyclopédie méthodique (`Trésor de livres rares et précieux - Graesse, 1861/Encyclopédie méthodique`)
1. `Corneille, Pierre - Œuvres, Marty-Laveaux, 1862, tome 5/Héraclius, empereur d'Orient`: virtual, collection, 1 parts, 0 missing, order single, proofread
    - text: Personnages (`Corneille, Pierre - Œuvres, Marty-Laveaux, 1862, tome 5/Héraclius, empereur d'Orient/Personnages`)
1. `La Dame du lac`: virtual, collection, 9 parts, 0 missing, order single, proofread
    - volume: Defauconpret, 1836 (`La Dame du lac/Defauconpret, 1836`)
      - chapter: Introduction à la Dame du Lac (`La Dame du lac/Defauconpret, 1836/Introduction`) [hierarchy]
      - chapter: Chant premier (`La Dame du lac/Defauconpret, 1836/Chant premier`) [hierarchy]
      - chapter: Chant second (`La Dame du lac/Defauconpret, 1836/Chant second`) [hierarchy]
      - chapter: Chant troisième (`La Dame du lac/Defauconpret, 1836/Chant troisième`) [hierarchy]
      - chapter: Chant quatrième (`La Dame du lac/Defauconpret, 1836/Chant quatrième`) [hierarchy]
      - chapter: Chant cinquième (`La Dame du lac/Defauconpret, 1836/Chant cinquième`) [hierarchy]
      - chapter: Chant sixième (`La Dame du lac/Defauconpret, 1836/Chant sixième`) [hierarchy]
      - chapter: Notes de la Dame du Lac (`La Dame du lac/Defauconpret, 1836/Notes de la Dame du Lac`) [hierarchy]
1. `Toute Royauté qui`: virtual, collection, 1 parts, 0 missing, order single, proofread
    - chapter: 1587 (`Toute Royauté qui/1587`)
1. `Revue Musicale de Lyon 1904-04-20`: virtual, book, 7 parts, 0 missing, order scan, validated
    - text: La Tétralogie (`Revue Musicale de Lyon 1904-04-20/La Tétralogie`)
    - text: L'Année musicale à Paris (`Revue Musicale de Lyon 1904-04-20/L'Année musicale à Paris`) [hierarchy]
    - text: Lieder français (`Revue Musicale de Lyon 1904-04-20/Lieder français`) [hierarchy]
    - text: Chronique lyonnaise (`Revue Musicale de Lyon 1904-04-20/Chronique lyonnaise`) [hierarchy]
    - text: À travers la presse (`Revue Musicale de Lyon 1904-04-20/À travers la presse`) [hierarchy]
    - text: Nouvelles diverses (`Revue Musicale de Lyon 1904-04-20/Nouvelles diverses`) [hierarchy]
    - text: Bibliographie (`Revue Musicale de Lyon 1904-04-20/Bibliographie`) [hierarchy]
1. `Correspondance de H. de Balzac, 1/1821`: virtual, book, 4 parts, 0 missing, order chain, validated
    - chapter: XI (`Correspondance de H. de Balzac, 1/1821/XI`)
    - chapter: XII (`Correspondance de H. de Balzac, 1/1821/XII`) [hierarchy]
    - chapter: XIII (`Correspondance de H. de Balzac, 1/1821/XIII`) [hierarchy]
    - chapter: XIV (`Correspondance de H. de Balzac, 1/1821/XIV`) [hierarchy]
1. `Manuel d’Épictète (trad. Joly)`: virtual, book, 1 parts, 0 missing, order single, proofread
    - text: Traduction (`Manuel d’Épictète (trad. Joly)/Traduction`)
1. `Les mathématiques en Grèce au milieu du Vème siècle`: virtual, book, 2 parts, 0 missing, order scan, proofread
    - chapter: Avant-propos (`Les mathématiques en Grèce au milieu du Vème siècle/Avant-propos`)
    - chapter: Appendice (`Les mathématiques en Grèce au milieu du Vème siècle/Appendice`) [hierarchy]
1. `Grammaire de la langue volofe`: virtual, book, 2 parts, 0 missing, order scan, unproofread
    - chapter: Préface (`Grammaire de la langue volofe/Préface`)
    - text: Division (`Grammaire de la langue volofe/Division`) [hierarchy]
1. `Histoire socialiste`: virtual, book, 188 parts, 0 missing, order natural, proofread
    - volume: Consulat et Empire (`Histoire socialiste/Consulat et Empire`)
      - text: LE CONSULAT (`Histoire socialiste/Consulat et Empire/00`) [container]
      - text: Chap. I. — La France au lendemain du 18 brumaire (`Histoire socialiste/Consulat et Empire/01`) [container]
      - text: Chap. II. — Le Consulat provisoire : (`Histoire socialiste/Consulat et Empire/02`) [container]
      - text: Chap. III. — Bonaparte, premier consul, « complète » la Constitution de l’an VIII (`Histoire socialiste/Consulat et Empire/02A`) [container]
      - text: La Politique religieuse. (`Histoire socialiste/Consulat et Empire/03`) [container]
      - text: Chap. I. — Le Régime de la Séparation (`Histoire socialiste/Consulat et Empire/04`) [container]
      - text: Chap. II. — Le Concordat et les Articles organiques (`Histoire socialiste/Consulat et Empire/05`) [container]
      - text: Chap. I. — La guerre ou la paix (`Histoire socialiste/Consulat et Empire/06`) [container]
      - text: Chap. II. — La guerre contre l’Autriche (`Histoire socialiste/Consulat et Empire/07`) [container]
      - text: Chap. III. — La guerre contre l’Angleterre (`Histoire socialiste/Consulat et Empire/08`) [container]
      - text: L’Écrasement de la Liberté. (`Histoire socialiste/Consulat et Empire/09`) [container]
    - … 176 more
1. `L’Univers`: virtual, periodical-issue, 1 parts, 0 missing, order single, unproofread
    - text: Karl Marx (`L’Univers/1883/03/19/Karl Marx`)
1. `Anne… la maison aux pignons verts`: virtual, book, 1 parts, 0 missing, order single, unproofread
    - chapter: Chapitre I (`Anne… la maison aux pignons verts/Chapitre I`)
1. `Maurice Maeterlinck - Théâtre 1, 1903`: virtual, book, 1 parts, 0 missing, order single, proofread
    - chapter: Préface (`Maurice Maeterlinck - Théâtre 1, 1903/Préface`)
1. `ONU`: virtual, book, 18 parts, 13 missing, order single, unproofread
    - volume: 1964 (`ONU/Résolutions et décisions du Conseil de sécurité/1964`)
      - text: Membres du Conseil de sécurité en 1964 (`ONU/Résolutions et décisions du Conseil de sécurité/1964/01`) [container]
      - text: Résolutions adoptées et décisions prises par le Conseil de sécurité en 1964 (`ONU/Résolutions et décisions du Conseil de sécurité/1964/02`) [container]
      - text: Question relative à la Zone du canal de Panama (`ONU/Résolutions et décisions du Conseil de sécurité/1964/03`) [container]
      - text: Résolution 186 du Conseil de sécurité des Nations unies [container]
      - text: Résolution 187 du Conseil de sécurité des Nations unies [container]
      - text: Résolution 192 du Conseil de sécurité des Nations unies [container]
      - text: Résolution 193 du Conseil de sécurité des Nations unies [container]
      - text: Résolution 194 du Conseil de sécurité des Nations unies [container]
      - text: Résolution 198 du Conseil de sécurité des Nations unies [container]
      - text: Résolution 188 du Conseil de sécurité des Nations unies [container]
      - text: Résolution 189 du Conseil de sécurité des Nations unies [container]
    - … 6 more
1. `Poëmes et légendes (Heine)`: virtual, collection, 1 parts, 0 missing, order single, unproofread
    - chapter: Préface (`Poëmes et légendes (Heine)/Préface`)
1. `Somme contre les Gentils`: virtual, book, 2 parts, 0 missing, order scan, proofread
    - chapter: 10 (`Somme contre les Gentils/Livre premier/10`)
    - chapter: 11 (`Somme contre les Gentils/Livre premier/11`) [hierarchy]
1. `Les Nouvelles littéraires, 15 décembre 1928`: virtual, periodical-issue, 2 parts, 0 missing, order natural, validated
    - text: Bergson au Japon par Shūzō Kuki (`Les Nouvelles littéraires, 15 décembre 1928/Bergson au Japon par Shūzō Kuki`)
    - text: Le Bergsonnisme dans l’histoire de la philosophie (`Les Nouvelles littéraires, 15 décembre 1928/Le Bergsonnisme dans l’histoire de la philosophie`) [hierarchy]
1. `Le Christianisme et l’esclavage`: virtual, book, 2 parts, 0 missing, order scan, proofread
    - chapter: Préface (`Le Christianisme et l’esclavage/Préface`)
    - text: Texte (`Le Christianisme et l’esclavage/Texte`) [hierarchy]
1. `Correspondance de H. de Balzac, 1/1829`: virtual, book, 6 parts, 0 missing, order chain, validated
    - chapter: XXVI (`Correspondance de H. de Balzac, 1/1829/XXVI`)
    - chapter: XXVII (`Correspondance de H. de Balzac, 1/1829/XXVII`) [hierarchy]
    - chapter: XXVIII (`Correspondance de H. de Balzac, 1/1829/XXVIII`) [hierarchy]
    - chapter: XXIX (`Correspondance de H. de Balzac, 1/1829/XXIX`) [hierarchy]
    - chapter: XXX (`Correspondance de H. de Balzac, 1/1829/XXX`) [hierarchy]
    - chapter: XXXI (`Correspondance de H. de Balzac, 1/1829/XXXI`) [hierarchy]
1. `Le Déserteur (Monsigny)`: virtual, book, 16 parts, 0 missing, order scan, proofread
    - text: Ouverture (`Le Déserteur (Monsigny)/Ouverture`)
    - text: I, 1 (`Le Déserteur (Monsigny)/I, 1`) [hierarchy]
    - chapter: Acte I, Scène 2 (`Le Déserteur (Monsigny)/I, 2`) [hierarchy]
    - chapter: Acte I, Scène 3 (`Le Déserteur (Monsigny)/I, 3`) [hierarchy]
    - chapter: Acte I, Scène 4 (`Le Déserteur (Monsigny)/I, 4`) [hierarchy]
    - chapter: Acte I, Scène 5 (`Le Déserteur (Monsigny)/I, 5`) [hierarchy]
    - chapter: Acte I, Scène 6 (`Le Déserteur (Monsigny)/I, 6`) [hierarchy]
    - chapter: Acte I, Scène 7 (`Le Déserteur (Monsigny)/I, 7`) [hierarchy]
    - text: Entr’acte I (`Le Déserteur (Monsigny)/Entr’acte I`) [hierarchy]
    - text: II, 1 (`Le Déserteur (Monsigny)/II, 1`) [hierarchy]
    - chapter: Acte II, Scène 2 (`Le Déserteur (Monsigny)/II, 2`) [hierarchy]
    - chapter: Acte II, Scène 3 (`Le Déserteur (Monsigny)/II, 3`) [hierarchy]
    - … 4 more
1. `Les Ennéades de Plotin, tome II.`: virtual, book, 1 parts, 0 missing, order single, proofread
    - chapter: Notes (trad. Bouillet) (`Les Ennéades de Plotin, tome II./Ennéade III, livre II/Notes (trad. Bouillet)`)
1. `L’Allemand après la Guerre. L’Anglais et le Français, langues Internationales`: virtual, book, 1 parts, 0 missing, order single, proofread
    - chapter: Avant-propos (`L’Allemand après la Guerre. L’Anglais et le Français, langues Internationales/Avant-propos`)

### E: subpage texts reached from a disambiguation or versions page (11,365)

1. text “Sentences de Phocylide” (`Poètes Moralistes de la Grèce/Sentences de Phocylide`), part 20 of `Poètes Moralistes de la Grèce` (collection, 25 parts, 25 texts)
1. text “Faire une cavalcade” (`Dictionnaire érotique moderne/Faire une cavalcade`), part 867 of `Dictionnaire érotique moderne` (book, 1,717 parts, 1,707 texts)
1. text “Arbre à suif” (`Dictionnaire philosophique/Garnier (1878)/Arbre à suif`), part 59 of `Dictionnaire philosophique/Garnier (1878)` (collection, 637 parts, 628 texts)
1. text “Le Joueur de Cithare (bilingue)” (`Fables d’Ésope (trad. Chambry, 1927)/Le Joueur de Cithare (bilingue)`), part 139 of `Fables d’Ésope (trad. Chambry, 1927)` (collection, 725 parts, 722 texts)
1. text “Juin 1724” (`Journal et mémoires de Mathieu Marais/1724/Juin`), part 59 of `Journal et mémoires de Mathieu Marais` (book, 72 parts, 71 texts)
1. text “Première préface” (`Les Diaboliques/Première préface`), part 10 of `Les Diaboliques` (book, 10 parts, 9 texts)
1. text “La Femme de l’ouvrier” (`Poésies (Mallarmé, 1914, 8e éd.)/La Femme de l’ouvrier`), part 36 of `Poésies (Mallarmé, 1914, 8e éd.)` (collection, 65 parts, 64 texts)
1. text “L’Auto-ballon” (`Deux et deux font cinq/L’Auto-ballon`), part 32 of `Deux et deux font cinq` (collection, 65 parts, 62 texts)
1. text “Mutisme” (`Néologie, ou Vocabulaire de mots nouveaux/Mutisme (2)`), part 2548 of `Néologie, ou Vocabulaire de mots nouveaux` (book, 2,835 parts, 2,807 texts)
1. text “Réponse” (`Poèmes et Paysages/Réponse`), part 29 of `Poèmes et Paysages` (collection, 90 parts, 88 texts)
1. text “Première représentation de Jacques Callot, à la Porte-Saint-Martin ; réouverture de l’Opéra-Comique ; reprise de la Vie parisienne aux Variétés” (`Semaine théâtrale/Première représentation de Jacques Callot, à la Porte-Saint-Martin ; réouverture de l’Opéra-Comique ; reprise de la Vie parisienne aux Variétés`), part 12 of `Semaine théâtrale` (periodical-issue, 21 parts, 21 texts)
1. text “Courir” (`Dictionnaire érotique moderne/Courir`), part 597 of `Dictionnaire érotique moderne` (book, 1,717 parts, 1,707 texts)
1. text “Clair de lune” (`Les Orientales/Clair de lune`), part 12 of `Les Orientales` (collection, 43 parts, 41 texts)
1. chapter “Chapitre 6” (`Mémoires (Vidocq)/Chapitre 6`), part 7 of `Mémoires (Vidocq)` (book, 37 parts, 1 texts)
1. text “Coin (le) de l’Amitié” (`Œuvres complètes de Béranger/Le Coin de l’Amitié`), part 88 of `Œuvres complètes de Béranger` (collection, 327 parts, 320 texts)
1. text “Chouart” (`Dictionnaire érotique moderne/Chouart`), part 490 of `Dictionnaire érotique moderne` (book, 1,717 parts, 1,707 texts)
1. text “Jane” (`Poèmes antiques/Jane`), part 48 of `Poèmes antiques` (collection, 56 parts, 54 texts)
1. text “Les Bandits corses” (`Au soleil (recueil)/Les Bandits corses`), part 13 of `Au soleil (recueil)` (book, 18 parts, 18 texts)
1. text “Petit cadeau” (`Dictionnaire érotique moderne/Petit cadeau`), part 1383 of `Dictionnaire érotique moderne` (book, 1,717 parts, 1,707 texts)
1. text “Rêverie” (`Œuvres posthumes (Musset)/Rêverie`), part 22 of `Œuvres posthumes (Musset)` (collection, 31 parts, 31 texts)
1. text “L’Orgueil” (`L’Abîme (Rollinat)/L’Orgueil`), part 43 of `L’Abîme (Rollinat)` (collection, 89 parts, 89 texts)
1. chapter “III. Zarka” (`Femmes slaves (RDDM)/Zarka`), part 3 of `Femmes slaves (RDDM)` (periodical-issue, 10 parts, 0 texts)
1. text “Faîtière” (`Dictionnaire raisonné de l’architecture française du XIe au XVIe siècle/Faîtière`), part 248 of `Dictionnaire raisonné de l’architecture française du XIe au XVIe siècle` (collection, 530 parts, 520 texts)
1. text “La chanson de la vieille fileuse” (`Chansons populaires de la Basse-Bretagne/La chanson de la vieille fileuse`), part 59 of `Chansons populaires de la Basse-Bretagne` (book, 243 parts, 239 texts)
1. text “Mensonge cotonneux” (`Dictionnaire érotique moderne/Mensonge cotonneux`), part 1230 of `Dictionnaire érotique moderne` (book, 1,717 parts, 1,707 texts)
1. text “(l’Autoclète, Phonographe, l’Art et la Science)” (`Les Minutes de sable mémorial/Guignol`), part 5 of `Les Minutes de sable mémorial` (book, 21 parts, 19 texts)
1. text “Délits locaux” (`Dictionnaire philosophique/Garnier (1878)/Délits locaux`), part 220 of `Dictionnaire philosophique/Garnier (1878)` (collection, 637 parts, 628 texts)
1. text “Amuser” (`Dictionnaire érotique moderne/Amuser`), part 108 of `Dictionnaire érotique moderne` (book, 1,717 parts, 1,707 texts)
1. chapter “Chapitre V-1” (`Le Zend-Avesta (trad. Darmesteter)/Volume I/Introduction/Chapitre V-1`), part 22 of `Le Zend-Avesta (trad. Darmesteter)/Volume I` (book, 167 parts, 122 texts)
1. text “Élégie italienne. Tel j’étais autrefois” (`Œuvres poétiques de Chénier (Moland, 1889)/Élégie italienne. Tel j’étais autrefois`), part 156 of `Œuvres poétiques de Chénier (Moland, 1889)` (collection, 304 parts, 290 texts)

### all searchable subpage texts (chapter titles that the label patterns miss show up here) (345,292)

1. text “Vassal” (`Thresor de la langue françoise/Vassal`), part 3023 of `Thresor de la langue françoise` (reference, 3,357 parts, 3,324 texts)
1. text “FAUX-SAUNIER” (`L’Encyclopédie/1re édition/FAUX-SAUNIER`), part 11928 of `L’Encyclopédie` (reference, 41,677 parts, 41,610 texts)
1. text “— M. le comte d’Artois” (`Souvenirs d’un page de la cour de Louis XVI/M. le comte d’Artois`), part 7 of `Souvenirs d’un page de la cour de Louis XVI` (book, 42 parts, 41 texts)
1. text “Laisse CCCXXXI” (`L’entrée d’Espagne/Laisse CCCXXXI`), part 204 of `L’entrée d’Espagne` (book, 698 parts, 688 texts)
1. text “Lassitude” (`Le Coffret de santal (éd. 1879)/Lassitude`), part 112 of `Le Coffret de santal (éd. 1879)` (collection, 112 parts, 108 texts)
1. text “Index Alphabétique” (`Mémoires inédits de madame la comtesse de Genlis (ladvocat)/Index Alphabétique`), part 3 of `Mémoires inédits de madame la comtesse de Genlis (ladvocat)` (book, 3 parts, 1 texts)
1. text “IX — Le voleur volé” (`Vengeance fatale/IX — Le voleur volé`), part 14 of `Vengeance fatale` (book, 25 parts, 23 texts)
1. text “Notre-Dame des Biffins” (`Les Croix de bois/Notre-Dame des Biffins`), part 10 of `Les Croix de bois` (book, 17 parts, 17 texts)
1. text “STATO DELLI PRESIDII, lo” (`L’Encyclopédie/1re édition/STATO DELLI PRESIDII, lo`), part 34885 of `L’Encyclopédie` (reference, 41,677 parts, 41,610 texts)
1. text “Cuisses grosses mais fuselées” (`Odes en son honneur/Cuisses grosses mais fuselées`), part 8 of `Odes en son honneur` (collection, 19 parts, 19 texts)
1. text “sans prép.” (`Dictionnaire français illustré de Larive et Fleury/sans prép.`), part 47 of `Dictionnaire français illustré de Larive et Fleury` (reference, 198 parts, 197 texts)
1. text “PERGAMO” (`L’Encyclopédie/1re édition/PERGAMO`), part 25802 of `L’Encyclopédie` (reference, 41,677 parts, 41,610 texts)
1. text “BITILISE” (`L’Encyclopédie/1re édition/BITILISE`), part 1431 of `L’Encyclopédie` (reference, 41,677 parts, 41,610 texts)
1. text “Le château de Maran” (`Mathilde, Mémoires d’une jeune femme/Partie II/25`), part 49 of `Mathilde, Mémoires d’une jeune femme` (book, 107 parts, 101 texts)
1. text “Abschinnen” (`Dictionnaire minier et métallurgique allemand-français/Abschinnen`), part 141 of `Dictionnaire minier et métallurgique allemand-français` (reference, 570 parts, 570 texts)
1. text “Octavie, ou l’Illusion” (`Œuvres complètes de Gérard de Nerval - Tome V/Octavie, ou l’Illusion`), part 5 of `Œuvres complètes de Gérard de Nerval - Tome V` (collection, 16 parts, 16 texts)
1. text “Janvier. Il neige !” (`Poésies (Amélie Gex)/Janvier. Il neige !`), part 2 of `Poésies (Amélie Gex)` (book, 85 parts, 79 texts)
1. text “Ramasse-moi mon lamba” (`La Race inconnue/Ramasse-moi mon lamba`), part 5 of `La Race inconnue` (book, 25 parts, 25 texts)
1. text “La tubéreuse” (`Les Éblouissements/La tubéreuse`), part 126 of `Les Éblouissements` (book, 169 parts, 166 texts)
1. text “QUINTE” (`Petit dictionnaire de la langue française (1860)/QUINTE`), part 16851 of `Petit dictionnaire de la langue française (1860)` (reference, 20,406 parts, 20,364 texts)
1. text “KILKENNY” (`L’Encyclopédie/1re édition/KILKENNY`), part 17573 of `L’Encyclopédie` (reference, 41,677 parts, 41,610 texts)
1. text “ORIGINAIREMENT” (`Petit dictionnaire de la langue française (1860)/ORIGINAIREMENT`), part 14290 of `Petit dictionnaire de la langue française (1860)` (reference, 20,406 parts, 20,364 texts)
1. text “L’Épousée-Démon” (`Beautés de la poésie anglaise/L’Épousée-Démon`), part 20 of `Beautés de la poésie anglaise` (collection, 52 parts, 49 texts)
1. text “PHILOSOPHIE II. Philosophie et religion” (`Dictionnaire de théologie catholique/PHILOSOPHIE II. Philosophie et religion`), part 1380 of `Dictionnaire de théologie catholique` (collection, 1,987 parts, 1,852 texts)
1. text “Le cordon” (`Théorie de la grande guerre/Livre VI/Chapitre 22`), part 22 of `Théorie de la grande guerre/Livre VI` (book, 30 parts, 30 texts)
1. text “Justice” (`Dictionnaire des mœurs/Justice`), part 539 of `Dictionnaire des mœurs` (reference, 719 parts, 711 texts)
1. text “&nbsp;&nbsp;CLIV. Usbek à ses femmes. (Roman.)” (`Lettres persanes/Lettre 154`), part 161 of `Lettres persanes` (book, 169 parts, 163 texts)
1. text “Lexique” (`Essai sur le patois d’Hérémence/II/27`), part 54 of `Essai sur le patois d’Hérémence` (book, 56 parts, 35 texts)
1. text “Papion” (`Dictionnaire des patois romans de la Moselle/Articles/Papion`), part 15447 of `Dictionnaire des patois romans de la Moselle` (reference, 21,247 parts, 21,230 texts)
1. text “ELETTE” (`L’Encyclopédie/1re édition/ELETTE`), part 10025 of `L’Encyclopédie` (reference, 41,677 parts, 41,610 texts)

### C: oversized (605)

1. `La Princesse de Clèves, édition Lepetit, 1820`: subpages, collection, 5 parts, 0 missing, order toc, validated
    - toc: La Princesse de Clèves, édition Lepetit, 1820
    - volume: &nbsp;&nbsp;&nbsp;Première partie (`La Princesse de Clèves, édition Lepetit, 1820/Première partie`) [hierarchy]
      - text: Texte entier=édit (`La Princesse de Clèves, édition Lepetit, 1820/Première partie/Texte entier=édit`) [hierarchy]
    - text: &nbsp;&nbsp;&nbsp;Seconde partie (`La Princesse de Clèves, édition Lepetit, 1820/Seconde partie`) [hierarchy]
    - text: &nbsp;&nbsp;&nbsp;Troisième partie (`La Princesse de Clèves, édition Lepetit, 1820/Troisième partie`) [hierarchy]
    - text: &nbsp;&nbsp;&nbsp;Quatrième partie (`La Princesse de Clèves, édition Lepetit, 1820/Quatrième partie`) [hierarchy]
1. `Œuvres complètes de Diderot`: mixed, collection, 102 parts, 4 missing, order scan-volumes, proofread
    - front: Œuvres complètes de Diderot
    - text: Avertissement (`Œuvres complètes de Diderot/Avertissement`) [container]
    - volume: Principes philosophiques sur la matière et le mouvement [container]
      - text: Notice préliminaire (`Principes philosophiques sur la matière et le mouvement/Notice`) [hierarchy]
    - text: Sur les femmes [container]
    - text: notice (`Les Bijoux indiscrets/notice`) [container]
    - chapter: 0 (`Les Bijoux indiscrets/0`) [container]
    - chapter: 1 (`Les Bijoux indiscrets/1`) [container]
    - chapter: 2 (`Les Bijoux indiscrets/2`) [container]
    - chapter: 3 (`Les Bijoux indiscrets/3`) [container]
    - chapter: 4 (`Les Bijoux indiscrets/4`) [container]
    - chapter: 5 (`Les Bijoux indiscrets/5`) [container]
    - … 91 more
1. `Aventures d'Hercule Hardi (Eugène Sue)`: single, collection, 1 parts, 0 missing, order –, validated
    - text: Aventures d'Hercule Hardi (Eugène Sue)
1. `La Bossue`: single, book, 1 parts, 0 missing, order –, proofread
    - text: La Bossue
1. `Catalogue général des manuscrits des bibliothèques publiques de France`: virtual, book, 1 parts, 0 missing, order single, unproofread
    - text: Rennes (`Catalogue général des manuscrits des bibliothèques publiques de France/Rennes`)
1. `Raoul de Cambrai`: subpages, book, 7 parts, 0 missing, order toc, validated
    - toc: Raoul de Cambrai
    - chapter: Introduction (`Raoul de Cambrai/Introduction`) [container]
    - chapter: Appendice (`Raoul de Cambrai/Appendice`) [container]
    - text: Raoul de Cambrai (`Raoul de Cambrai/Raoul de Cambrai`) [container]
    - text: Vocabulaire (`Raoul de Cambrai/Vocabulaire`) [container]
    - chapter: Table des noms (`Raoul de Cambrai/Table des noms`) [container]
    - chapter: Table des rimes (`Raoul de Cambrai/Table des rimes`) [container]
    - text: Errata (`Raoul de Cambrai/Errata`) [container]
1. `Revue des Deux Mondes/Table générale 1831-1874/Table alphabétique par noms d’auteurs`: single, book, 1 parts, 0 missing, order –, proofread
    - text: Revue des Deux Mondes/Table générale 1831-1874/Table alphabétique par noms d’auteurs
1. `Histoire de ma vie (Sand, édition Gerhard)`: subpages, book, 82 parts, 0 missing, order scan-volumes, unproofread
    - toc: Histoire de ma vie (Sand, édition Gerhard)
    - chapter: Introduction de Wikisource (`Histoire de ma vie (Sand, édition Gerhard)/Introduction de Wikisource`) [container]
    - text: Texte entier1 (`Histoire de ma vie (Sand, édition Gerhard)/Texte entier1`) [container]
    - volume: PREMIÈRE PARTIE. (`Histoire de ma vie (Sand, édition Gerhard)/1`) [container]
      - chapter: 01 (`Histoire de ma vie (Sand, édition Gerhard)/1/01`) [container]
      - chapter: 02 (`Histoire de ma vie (Sand, édition Gerhard)/1/02`) [container]
      - chapter: 03 (`Histoire de ma vie (Sand, édition Gerhard)/1/03`) [container]
      - chapter: 04 (`Histoire de ma vie (Sand, édition Gerhard)/1/04`) [container]
      - chapter: 05 (`Histoire de ma vie (Sand, édition Gerhard)/1/05`) [container]
      - chapter: 07 (`Histoire de ma vie (Sand, édition Gerhard)/1/07`) [container]
      - chapter: 08 (`Histoire de ma vie (Sand, édition Gerhard)/1/08`) [container]
      - chapter: 09 (`Histoire de ma vie (Sand, édition Gerhard)/1/09`) [container]
    - … 71 more
1. `Œuvres complètes de Charles Péguy`: mixed, book, 46 parts, 2 missing, order scan-volumes, proofread
    - front: Œuvres complètes de Charles Péguy
    - volume: Tome 1 (`Œuvres complètes de Charles Péguy/Tome 1`) [container]
      - chapter: Introduction (`Œuvres complètes de Charles Péguy/Tome 1/Introduction`) [container]
      - chapter: Lettre du provincial (`Œuvres complètes de Charles Péguy/Tome 1/Lettre du provincial`) [container]
      - text: Le triomphe de la république (`Œuvres complètes de Charles Péguy/Tome 1/Le triomphe de la république`) [container]
      - text: Du second provincial (`Œuvres complètes de Charles Péguy/Tome 1/Du second provincial`) [container]
      - text: De la grippe (`Œuvres complètes de Charles Péguy/Tome 1/De la grippe`) [container]
      - text: Encore de la grippe (`Œuvres complètes de Charles Péguy/Tome 1/Encore de la grippe`) [container]
      - text: Toujours de la grippe (`Œuvres complètes de Charles Péguy/Tome 1/Toujours de la grippe`) [container]
      - text: Entre deux trains (`Œuvres complètes de Charles Péguy/Tome 1/Entre deux trains`) [container]
      - text: Deuxième série au provincial (`Œuvres complètes de Charles Péguy/Tome 1/Deuxième série au provincial`) [container]
      - text: Pour ma maison (`Œuvres complètes de Charles Péguy/Tome 1/Pour ma maison`) [container]
    - … 35 more
1. `L’Île inconnue`: subpages, book, 4 parts, 0 missing, order scan, unproofread
    - toc: L’Île inconnue
    - chapter: Introduction (`L’Île inconnue/Introduction`) [container]
    - chapter: 1 (`L’Île inconnue/1`) [container]
    - chapter: 2 (`L’Île inconnue/2`) [container]
    - chapter: 3 (`L’Île inconnue/3`) [container]
1. `Principes de la science sociale`: mixed, book, 62 parts, 0 missing, order scan-volumes, proofread
    - toc: Principes de la science sociale
    - text: Principes de la science sociale - Tome 1 [container]
    - chapter: Préface (`Principes de la science sociale/Préface`) [container]
    - chapter: CHAPITRE Ier. De la science et des méthodes de la science (`Principes de la science sociale/01`) [container]
    - chapter: CHAPITRE II. De l’homme, sujet de la science sociale (`Principes de la science sociale/02`) [container]
    - chapter: CHAPITRE III. De l’accroissement dans la quantité de l’espèce humaine (`Principes de la science sociale/03`) [container]
    - chapter: CHAPITRE IV. De l’occupation de la terre (`Principes de la science sociale/04`) [container]
    - chapter: CHAPITRE V. Continuation du même sujet (`Principes de la science sociale/05`) [container]
    - chapter: CHAPITRE VI. De la valeur (`Principes de la science sociale/06`) [container]
    - chapter: CHAPITRE VII. De la richesse (`Principes de la science sociale/07`) [container]
    - chapter: CHAPITRE VIII. De la formation de la société (`Principes de la science sociale/08`) [container]
    - chapter: CHAPITRE IX. De l’appropriation (`Principes de la science sociale/09`) [container]
    - … 51 more
1. `Une oubliée — Madame Cottin d’après sa correspondance`: subpages, book, 10 parts, 0 missing, order scan, unproofread
    - toc: Une oubliée — Madame Cottin d’après sa correspondance
    - chapter: 1 (`Une oubliée — Madame Cottin d’après sa correspondance/1`) [container]
    - chapter: 2 (`Une oubliée — Madame Cottin d’après sa correspondance/2`) [container]
    - text: Bibliographie (`Une oubliée — Madame Cottin d’après sa correspondance/Bibliographie`) [container]
    - chapter: Appendice 1 (`Une oubliée — Madame Cottin d’après sa correspondance/Appendice 1`) [container]
    - chapter: Appendice 2 (`Une oubliée — Madame Cottin d’après sa correspondance/Appendice 2`) [container]
    - chapter: Appendice 3 (`Une oubliée — Madame Cottin d’après sa correspondance/Appendice 3`) [container]
    - chapter: Appendice 4 (`Une oubliée — Madame Cottin d’après sa correspondance/Appendice 4`) [container]
    - chapter: Appendice 5 (`Une oubliée — Madame Cottin d’après sa correspondance/Appendice 5`) [container]
    - chapter: Appendice 6 (`Une oubliée — Madame Cottin d’après sa correspondance/Appendice 6`) [container]
    - chapter: Appendice 7 (`Une oubliée — Madame Cottin d’après sa correspondance/Appendice 7`) [container]
1. `L’Empire des tsars et les Russes`: subpages, book, 122 parts, 0 missing, order scan-volumes, proofread
    - front: L’Empire des tsars et les Russes
    - chapter: Tome 1 (`L’Empire des tsars et les Russes/Texte entier/Tome 1`) [container]
    - volume: TOME 1. LE PAYS ET LES HABITANTS (`L’Empire des tsars et les Russes/Tome 1`) [container]
      - chapter: Avant-propos de la troisième édition (`L’Empire des tsars et les Russes/Tome 1/Avant-propos de la troisième édition`) [container]
      - chapter: Avant-propos de la deuxième édition (`L’Empire des tsars et les Russes/Tome 1/Avant-propos de la deuxième édition`) [container]
      - chapter: Préface de la première édition (`L’Empire des tsars et les Russes/Tome 1/Préface de la première édition`) [container]
      - volume: Table des matières (`L’Empire des tsars et les Russes/Tome 1/Table des matières`) [container]
        - volume: LIVRE I (`L’Empire des tsars et les Russes/Tome 1/Livre 1`) [container]
          - chapter: Chapitre 1 (`L’Empire des tsars et les Russes/Tome 1/Livre 1/Chapitre 1`) [container]
          - chapter: Chapitre 2 (`L’Empire des tsars et les Russes/Tome 1/Livre 1/Chapitre 2`) [container]
          - chapter: Chapitre 3 (`L’Empire des tsars et les Russes/Tome 1/Livre 1/Chapitre 3`) [container]
        - volume: LIVRE II (`L’Empire des tsars et les Russes/Tome 1/Livre 2`) [container]
    - … 111 more
1. `Les Cahiers de Malte Laurids Brigge`: single, book, 1 parts, 0 missing, order –, proofread
    - text: Les Cahiers de Malte Laurids Brigge
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
1. `Bible Darby 1885`: subpages, book, 73 parts, 0 missing, order toc, proofread
    - toc: Bible Darby 1885
    - chapter: Préface (`Bible Darby 1885/Préface`) [container]
    - text: Signes (`Bible Darby 1885/Signes`) [container]
    - text: Dates chronologiques (`Bible Darby 1885/Dates chronologiques`) [container]
    - text: Poids et mesures (`Bible Darby 1885/Poids et mesures`) [container]
    - chapter: Table chronologique des rois et des prophètes de Juda et d’Israël (`Bible Darby 1885/Table chronologique des rois et des prophètes de Juda et d’Israël`) [container]
    - volume: ANCIEN TESTAMENT (`Bible Darby 1885/Ancien Testament`) [container]
      - text: NOUVEAU TESTAMENT (`Bible Darby 1885/Nouveau Testament`) [container]
    - text: La Genèse (`Bible Darby 1885/Genèse`) [container]
    - text: L’Exode (`Bible Darby 1885/Exode`) [container]
    - text: Le Lévitique (`Bible Darby 1885/Lévitique`) [container]
    - text: Les Nombres (`Bible Darby 1885/Nombres`) [container]
    - … 62 more
1. `Le Roman d’un bas-bleu`: single, book, 1 parts, 0 missing, order –, proofread
    - text: Le Roman d’un bas-bleu
1. `Histoire archéologique de l’époque gallo-romaine de la ville de Rennes`: subpages, book, 3 parts, 0 missing, order scan, unproofread
    - toc: Histoire archéologique de l’époque gallo-romaine de la ville de Rennes
    - chapter: Première partie (`Histoire archéologique de l’époque gallo-romaine de la ville de Rennes/Première partie`) [container]
    - chapter: Deuxième partie (`Histoire archéologique de l’époque gallo-romaine de la ville de Rennes/Deuxième partie`) [container]
    - chapter: Troisième partie (`Histoire archéologique de l’époque gallo-romaine de la ville de Rennes/Troisième partie`) [container]
1. `Le Vicomte de Bragelonne`: subpages, book, 271 parts, 0 missing, order scan, validated
    - toc: Le Vicomte de Bragelonne
    - chapter: 2 (`Le Vicomte de Bragelonne/Texte entier/2`) [container]
    - chapter: 3 (`Le Vicomte de Bragelonne/Texte entier/3`) [container]
    - text: La lettre (`Le Vicomte de Bragelonne/Chapitre I`) [container]
    - text: Le messager (`Le Vicomte de Bragelonne/Chapitre II`) [container]
    - text: L’entrevue (`Le Vicomte de Bragelonne/Chapitre III`) [container]
    - text: Le père et le fils (`Le Vicomte de Bragelonne/Chapitre IV`) [container]
    - text: Où il sera parlé de Cropoli, de Cropole et d’un grand peintre inconnu (`Le Vicomte de Bragelonne/Chapitre V`) [container]
    - text: L’inconnu (`Le Vicomte de Bragelonne/Chapitre VI`) [container]
    - text: Parry (`Le Vicomte de Bragelonne/Chapitre VII`) [container]
    - text: Ce qu’était Sa Majesté Louis XIV à l’âge de vingt-deux ans (`Le Vicomte de Bragelonne/Chapitre VIII`) [container]
    - text: Où l’inconnu de l’hôtellerie des Médicis perd son incognito (`Le Vicomte de Bragelonne/Chapitre IX`) [container]
    - … 260 more
1. `Histoire de ma vie (Sand, Calmann-Levy 1855)`: subpages, book, 83 parts, 0 missing, order scan-volumes, proofread
    - toc: Histoire de ma vie (Sand, Calmann-Levy 1855)
    - chapter: Tome 1 (`Histoire de ma vie (Sand, Calmann-Levy 1855)/Texte entier/Tome 1`) [container]
    - volume: 1 (`Histoire de ma vie (Sand, Calmann-Levy 1855)/1`) [container]
      - chapter: 01 (`Histoire de ma vie (Sand, Calmann-Levy 1855)/1/01`) [container]
      - chapter: 02 (`Histoire de ma vie (Sand, Calmann-Levy 1855)/1/02`) [container]
      - chapter: 03 (`Histoire de ma vie (Sand, Calmann-Levy 1855)/1/03`) [container]
      - chapter: 04 (`Histoire de ma vie (Sand, Calmann-Levy 1855)/1/04`) [container]
      - chapter: 05 (`Histoire de ma vie (Sand, Calmann-Levy 1855)/1/05`) [container]
      - chapter: 06 (`Histoire de ma vie (Sand, Calmann-Levy 1855)/1/06`) [container]
      - chapter: 07 (`Histoire de ma vie (Sand, Calmann-Levy 1855)/1/07`) [container]
      - chapter: 08 (`Histoire de ma vie (Sand, Calmann-Levy 1855)/1/08`) [container]
      - chapter: 09 (`Histoire de ma vie (Sand, Calmann-Levy 1855)/1/09`) [container]
    - … 72 more
1. `Anthologie des poètes français contemporains`: mixed, collection, 98 parts, 206 missing, order toc-partial, unproofread
    - front: Anthologie des poètes français contemporains
    - chapter: Préface (`Anthologie des poètes français contemporains/Préface`) [container]
    - chapter: Introduction (`Anthologie des poètes français contemporains/Introduction`) [container]
    - text: Gautier (Théophile) (`Anthologie des poètes français contemporains/Théophile Gautier`) [container]
    - text: Sainte-Beuve (`Anthologie des poètes français contemporains/Sainte-Beuve`) [container]
    - text: Barbier (Auguste) (`Anthologie des poètes français contemporains/Auguste Barbier`) [container]
    - text: Soulary (Joséphin) (`Anthologie des poètes français contemporains/Soulary Joséphin`) [container]
    - text: Lacaussade (Auguste) (`Anthologie des poètes français contemporains/Lacaussade (Auguste)`) [container]
    - text: Banville (Théodore de) (`Anthologie des poètes français contemporains/Théodore de Banville`) [container]
    - text: Grenier (Édouard) (`Anthologie des poètes français contemporains/Grenier Edouard`) [container]
    - text: Bornier (Henri de) (`Anthologie des poètes français contemporains/Henri de Bornier`) [container]
    - text: Leconte de Lisle (`Anthologie des poètes français contemporains/Leconte de Lisle`) [container]
    - … 87 more
1. `Paris, ses organes, ses fonctions et sa vie dans la seconde moitié du XIXe siècle`: subpages, book, 46 parts, 0 missing, order toc, proofread
    - toc: Paris, ses organes, ses fonctions et sa vie dans la seconde moitié du XIXe siècle
    - chapter: Table analytique (Index des 6 volumes) (`Paris, ses organes, ses fonctions et sa vie dans la seconde moitié du XIXe siècle/Index`) [container]
    - text: À Frédéric Fovard (`Paris, ses organes, ses fonctions et sa vie dans la seconde moitié du XIXe siècle/Dédicace`) [container]
    - chapter: Introduction (`Paris, ses organes, ses fonctions et sa vie dans la seconde moitié du XIXe siècle/Introduction`) [container]
    - text: Ch. I : LA POSTE AUX LETTRES (`Paris, ses organes, ses fonctions et sa vie dans la seconde moitié du XIXe siècle/I`) [container]
    - text: Ch. II : LES TÉLÉGRAPHES (`Paris, ses organes, ses fonctions et sa vie dans la seconde moitié du XIXe siècle/II`) [container]
    - text: Ch. III : LES VOITURES PUBLIQUES (`Paris, ses organes, ses fonctions et sa vie dans la seconde moitié du XIXe siècle/III`) [container]
    - text: Ch. IV : LES CHEMINS DE FER (`Paris, ses organes, ses fonctions et sa vie dans la seconde moitié du XIXe siècle/IV`) [container]
    - text: Ch. V : LA SEINE À PARIS (`Paris, ses organes, ses fonctions et sa vie dans la seconde moitié du XIXe siècle/V`) [container]
    - text: PIÈCES JUSTIFICATIVES (`Paris, ses organes, ses fonctions et sa vie dans la seconde moitié du XIXe siècle/T1 PJ`) [container]
    - text: Ch. VI : L’ALIMENTATION (`Paris, ses organes, ses fonctions et sa vie dans la seconde moitié du XIXe siècle/VI`) [container]
    - text: Ch. VII : LE PAIN, LA VIANDE ET LE VIN (`Paris, ses organes, ses fonctions et sa vie dans la seconde moitié du XIXe siècle/VII`) [container]
    - … 35 more
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
1. `Les Papiers posthumes du Pickwick Club`: subpages, book, 59 parts, 0 missing, order scan-volumes, validated
    - toc: Les Papiers posthumes du Pickwick Club
    - chapter: I (`Les Papiers posthumes du Pickwick Club/Texte entier/I`) [container]
    - chapter: Chapitre I — Les pickwickiens (`Les Papiers posthumes du Pickwick Club/Tome I/I.`) [container]
    - chapter: Chapitre II — Le premier jour de voyage et la première soirée d’aventures, avec leurs conséquences (`Les Papiers posthumes du Pickwick Club/Tome I/II.`) [container]
    - chapter: Chapitre III — Une nouvelle connaissance. Histoire d’un clown. Une interruption désagréable et une rencontre fâcheuse (`Les Papiers posthumes du Pickwick Club/Tome I/III.`) [container]
    - chapter: Chapitre IV — La petite guerre. De nouveaux amis. Une invitation pour la campagne (`Les Papiers posthumes du Pickwick Club/Tome I/IV.`) [container]
    - chapter: Chapitre V — Faisant voir entre autres choses comment entreprit de conduire une voiture, et de monter un cheval ; et comment l’un et l’autre en vinrent à bout (`Les Papiers posthumes du Pickwick Club/Tome I/V.`) [container]
    - chapter: Chapitre VI — Une soirée du bon vieux temps. Histoire racontée par un ecclésiastique (`Les Papiers posthumes du Pickwick Club/Tome I/VI.`) [container]
    - chapter: Chapitre VII — Comment , au lieu de tirer le pigeon et de tuer la corneille, tira la corneille et blessa le pigeon. Comment le club de la Crosse de Dingley-Dell lutta contre celui de Muggleton, et com (`Les Papiers posthumes du Pickwick Club/Tome I/VII.`) [container]
    - chapter: Chapitre VIII — Faisant voir clairement que la route du véritable amour n’est pas aussi unie qu’un chemin de fer (`Les Papiers posthumes du Pickwick Club/Tome I/VIII.`) [container]
    - chapter: Chapitre IX — La découverte et la poursuite (`Les Papiers posthumes du Pickwick Club/Tome I/IX.`) [container]
    - chapter: Chapitre X — Destiné à dissiper tous les doutes qui pourraient exister sur le désintéressement de (`Les Papiers posthumes du Pickwick Club/Tome I/X.`) [container]
    - … 48 more
1. `Rimes de Dante (trad. Fertiault)`: single, book, 1 parts, 0 missing, order –, unproofread
    - text: Rimes de Dante (trad. Fertiault)
1. `L’Ami commun`: subpages, book, 69 parts, 0 missing, order scan-volumes, validated
    - toc: L’Ami commun
    - text: À la découverte (`L’Ami commun/I/1`) [container]
    - chapter: tome 1 (`L’Ami commun/texte entier/tome 1`) [hierarchy]
    - text: L’homme de quelque part (`L’Ami commun/I/2`) [container]
    - text: Un nouveau personnage (`L’Ami commun/I/3`) [container]
    - text: La famille Wilfer (`L’Ami commun/I/4`) [container]
    - text: Boffins Bower (`L’Ami commun/I/5`) [container]
    - text: À la dérive (`L’Ami commun/I/6`) [container]
    - text: Où mister Wegg cherche une partie de lui-même (`L’Ami commun/I/7`) [container]
    - text: Mister Boffin en consultation (`L’Ami commun/I/8`) [container]
    - text: Consultation de mister et de missis Boffin (`L’Ami commun/I/9`) [container]
    - text: Contrat de mariage (`L’Ami commun/I/10`) [container]
    - … 58 more
1. `Histoire universelle — Index général`: single, book, 1 parts, 0 missing, order –, validated
    - text: Histoire universelle — Index général
1. `L’Enfant de l’Amour`: single, collection, 1 parts, 0 missing, order –, proofread
    - text: L’Enfant de l’Amour
1. `Tableau de la Géographie de la France`: single, book, 1 parts, 0 missing, order –, proofread
    - text: Tableau de la Géographie de la France
1. `Grammaire de l’hébreu biblique`: subpages, book, 224 parts, 0 missing, order toc, proofread
    - toc: Grammaire de l’hébreu biblique
    - chapter: Avant-propos. (`Grammaire de l’hébreu biblique/Avant-propos`) [container]
    - text: Abréviations et Signes conventionnels. (`Grammaire de l’hébreu biblique/Abréviations et Signes conventionnels`) [container]
    - text: Définition de la grammaire hébraïque. (`Grammaire de l’hébreu biblique/Introduction/Paragraphe 1`) [container]
    - text: Place de l’hébreu parmi les langues sémitiques. (`Grammaire de l’hébreu biblique/Introduction/Paragraphe 2`) [container]
    - text: Histoire de l’hébreu biblique. (`Grammaire de l’hébreu biblique/Introduction/Paragraphe 3`) [container]
    - text: Histoire de la grammaire hébraïque. (`Grammaire de l’hébreu biblique/Introduction/Paragraphe 4`) [container]
    - text: Tableau comparatif des alphabets (`Grammaire de l’hébreu biblique/Introduction/Tableau comparatif`) [container]
    - text: Les consonnes : graphie et prononciation. (`Grammaire de l’hébreu biblique/Écriture/Paragraphe 5`) [container]
    - text: Les voyelles : graphie et prononciation. (`Grammaire de l’hébreu biblique/Écriture/Paragraphe 6`) [container]
    - text: Des consonnes (matres lectionis) indiquant le timbre ou la quantité des voyelles. (`Grammaire de l’hébreu biblique/Écriture/Paragraphe 7`) [container]
    - text: Du shewa. (`Grammaire de l’hébreu biblique/Écriture/Paragraphe 8`) [container]
    - … 213 more

### G: reference works (274)

1. `Dictionnaire des abréviations latines et françaises`: subpages, reference, 14 parts, 0 missing, order toc, proofread
    - front: Dictionnaire des abréviations latines et françaises
    - text: Avertissement sur la troisième édition (`Dictionnaire des abréviations latines et françaises/Avertissement sur la troisième édition`) [container]
    - chapter: Préface de la première édition (`Dictionnaire des abréviations latines et françaises/Préface de la première édition`) [container]
    - volume: De la brachygraphie du Moyen Âge (`Dictionnaire des abréviations latines et françaises/De la brachygraphie du Moyen Âge`) [container]
      - text: Abréviations par sigles (`Dictionnaire des abréviations latines et françaises/De la brachygraphie du Moyen Âge/I`) [container]
      - text: Abréviations par contraction (`Dictionnaire des abréviations latines et françaises/De la brachygraphie du Moyen Âge/II`) [container]
      - text: Abréviations par suspension (`Dictionnaire des abréviations latines et françaises/De la brachygraphie du Moyen Âge/III`) [container]
      - text: Abréviations par signes abréviatifs (`Dictionnaire des abréviations latines et françaises/De la brachygraphie du Moyen Âge/IV`) [container]
      - text: Abréviations par lettres supérieures (`Dictionnaire des abréviations latines et françaises/De la brachygraphie du Moyen Âge/V`) [container]
      - text: Abréviations par lettres abréviatives (`Dictionnaire des abréviations latines et françaises/De la brachygraphie du Moyen Âge/VI`) [container]
      - text: Abréviations par lettres monogrammatiques ou conjointes, et par lettres enclavées, renversées et retournées (`Dictionnaire des abréviations latines et françaises/De la brachygraphie du Moyen Âge/VII`) [container]
      - chapter: VIII. — par signes particuliers (`Dictionnaire des abréviations latines et françaises/De la brachygraphie du Moyen Âge/VIII`) [container]
    - … 3 more
1. `Tresor de recherches et antiquitez gauloises et françoises/Nlle éd., 1750`: subpages, reference, 1 parts, 0 missing, order single, proofread
    - toc: Tresor de recherches et antiquitez gauloises et françoises/Nlle éd., 1750
    - chapter: Tome 1 (`Tresor de recherches et antiquitez gauloises et françoises/Nlle éd., 1750/Tome 1`) [container]
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
1. `Biographie universelle ancienne et moderne/1re éd., 1811`: subpages, reference, 203 parts, 2 missing, order natural, proofread
    - toc: Biographie universelle ancienne et moderne/1re éd., 1811
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
    - … 192 more
1. `Nouveau Dictionnaire universel de la langue française`: mixed, reference, 3 parts, 1 missing, order scan, unproofread
    - toc: Nouveau Dictionnaire universel de la langue française
    - chapter: Tome 1 (`Glossaire raisonné de la langue écrite et parlée/3e éd., 1855/Tome 1`) [container]
    - chapter: Tome 1 (`Nouveau Dictionnaire universel de la langue française/Tome 1`) [container]
    - chapter: Préface (`Nouveau Dictionnaire universel de la langue française/Préface`) [container]
1. `Dictionnaire raisonné du mobilier français de l’époque carlovingienne à la Renaissance`: mixed, reference, 110 parts, 0 missing, order natural, proofread
    - toc: Dictionnaire raisonné du mobilier français de l’époque carlovingienne à la Renaissance
    - text: Espalière (`Dictionnaire raisonné du mobilier français de l'époque carlovingienne à la Renaissance/Espalière`) [container]
    - text: Estacheur (`Dictionnaire raisonné du mobilier français de l'époque carlovingienne à la Renaissance/Estacheur`) [container]
    - volume: tome 1 (`Dictionnaire raisonné du mobilier français de l’époque carlovingienne à la Renaissance/tome 1`) [hierarchy]
      - text: Armoire (`Dictionnaire raisonné du mobilier français de l’époque carlovingienne à la Renaissance/Armoire`) [container]
      - text: Autel (`Dictionnaire raisonné du mobilier français de l’époque carlovingienne à la Renaissance/Autel`) [container]
      - text: Bahut (`Dictionnaire raisonné du mobilier français de l’époque carlovingienne à la Renaissance/Bahut`) [container]
      - text: Banc (`Dictionnaire raisonné du mobilier français de l’époque carlovingienne à la Renaissance/Banc`) [container]
      - text: Berceau (`Dictionnaire raisonné du mobilier français de l’époque carlovingienne à la Renaissance/Berceau`) [container]
      - text: Buffet (`Dictionnaire raisonné du mobilier français de l’époque carlovingienne à la Renaissance/Buffet`) [container]
      - text: Bustail (`Dictionnaire raisonné du mobilier français de l’époque carlovingienne à la Renaissance/Bustail`) [container]
      - text: Cabinet (`Dictionnaire raisonné du mobilier français de l’époque carlovingienne à la Renaissance/Cabinet`) [container]
    - … 99 more
1. `Dictionnaire de la prononciation de la langue française`: subpages, reference, 17 parts, 18 missing, order toc, proofread
    - toc: Dictionnaire de la prononciation de la langue française
    - text: Épigraphe (`Dictionnaire de la prononciation de la langue française/Épigraphe`) [container]
    - chapter: Avant-propos (`Dictionnaire de la prononciation de la langue française/Avant-propos`) [container]
    - text: De la nécessité d’un alphabet rationnel et phonétique (`Dictionnaire de la prononciation de la langue française/De la nécessité d’un alphabet rationnel et phonétique`) [container]
    - text: Avantages d’un alphabet universel (`Dictionnaire de la prononciation de la langue française/Avantages d’un alphabet universel`) [container]
    - text: Application du principe rationnel (`Dictionnaire de la prononciation de la langue française/Application du principe rationnel`) [container]
    - chapter: Préface (`Dictionnaire de la prononciation de la langue française/Préface`) [container]
    - text: Avertissement pour l’usage du dictionnaire (`Dictionnaire de la prononciation de la langue française/Avertissement pour l’usage du dictionnaire`) [container]
    - text: K (`Dictionnaire de la prononciation de la langue française/K`) [container]
    - text: Q (`Dictionnaire de la prononciation de la langue française/Q`) [container]
    - text: U (`Dictionnaire de la prononciation de la langue française/U`) [container]
    - chapter: V (`Dictionnaire de la prononciation de la langue française/V`) [container]
    - … 6 more
1. `Biographie universelle, ou Dictionnaire historique`: single, reference, 1 parts, 0 missing, order –, unproofread
    - text: Biographie universelle, ou Dictionnaire historique
1. `Curiositez françoises/Par lettres`: single, reference, 1 parts, 0 missing, order –, validated
    - text: Curiositez françoises/Par lettres
1. `Honnorat - Dictionnaire provençal-français`: single, reference, 1 parts, 0 missing, order –, unproofread
    - text: Honnorat - Dictionnaire provençal-français
1. `Dictionnaire grammatical de la langue françoise/Nlle éd., 1768`: single, reference, 1 parts, 4 missing, order –, unproofread
    - text: Dictionnaire grammatical de la langue françoise/Nlle éd., 1768
1. `La Psychanalyse`: subpages, reference, 4 parts, 0 missing, order toc, proofread
    - front: La Psychanalyse
    - text: Présentation (`La Psychanalyse/Présentation`) [container]
    - text: Dictionnaire (`La Psychanalyse/Dictionnaire`) [container]
    - text: Bibliographie (`La Psychanalyse/Bibliographie`) [container]
    - text: Index (`La Psychanalyse/Index`) [container]
1. `Dictionnaire infernal/6e éd., 1863`: subpages, reference, 3,826 parts, 0 missing, order scan, proofread
    - front: Dictionnaire infernal/6e éd., 1863
    - chapter: PRÉFACE (`Dictionnaire infernal/6e éd., 1863/Préface`) [hierarchy]
    - text: Aaron (`Dictionnaire infernal/6e éd., 1863/Aaron`) [container]
    - text: Abaddon (`Dictionnaire infernal/6e éd., 1863/Abaddon`) [container]
    - text: Abadie (`Dictionnaire infernal/6e éd., 1863/Abadie`) [container]
    - text: Abalam (`Dictionnaire infernal/6e éd., 1863/Abalam`) [container]
    - chapter: Lettre A (`Dictionnaire infernal/6e éd., 1863/Lettre A`) [container]
    - text: Abano (`Dictionnaire infernal/6e éd., 1863/Abano`) [container]
    - text: Abaris (`Dictionnaire infernal/6e éd., 1863/Abaris`) [container]
    - text: Abd-el-Azys (`Dictionnaire infernal/6e éd., 1863/Abd-el-Azys`) [container]
    - text: Abdeel (`Dictionnaire infernal/6e éd., 1863/Abdeel`) [container]
    - text: Abeilard (`Dictionnaire infernal/6e éd., 1863/Abeilard`) [container]
    - … 3,815 more
1. `Dictionnaire universel de matière médicale et de thérapeutique`: virtual, reference, 1 parts, 0 missing, order single, unproofread
    - text: Or (médecine) (`Dictionnaire universel de matière médicale et de thérapeutique/Or (médecine)`)
1. `Curiosités françaises (1640)`: virtual, reference, 1 parts, 0 missing, order single, proofread
    - text: Titre (`Curiosités françaises (1640)/Titre`)
1. `Petit Dictionnaire libéral`: subpages, reference, 221 parts, 0 missing, order natural, validated
    - toc: Petit Dictionnaire libéral
    - text: ABAISSEMENT (`Petit Dictionnaire libéral/ABAISSEMENT`) [hierarchy]
    - text: ABANDON (`Petit Dictionnaire libéral/ABANDON`) [hierarchy]
    - text: ABATTOIR (`Petit Dictionnaire libéral/ABATTOIR`) [hierarchy]
    - text: ABATTRE (`Petit Dictionnaire libéral/ABATTRE`) [hierarchy]
    - text: ABUS (`Petit Dictionnaire libéral/ABUS`) [hierarchy]
    - text: ABÎME (`Petit Dictionnaire libéral/ABÎME`) [hierarchy]
    - text: ACADÉMIE (`Petit Dictionnaire libéral/ACADÉMIE`) [hierarchy]
    - text: ADHÉSION (`Petit Dictionnaire libéral/ADHÉSION`) [hierarchy]
    - text: ALARME (`Petit Dictionnaire libéral/ALARME`) [hierarchy]
    - text: ALLIANCE (`Petit Dictionnaire libéral/ALLIANCE`) [hierarchy]
    - text: ALLÉGORIE (`Petit Dictionnaire libéral/ALLÉGORIE`) [hierarchy]
    - … 210 more
1. `Dictionnaire historique et critique/11e éd./Tome 16`: single, reference, 1 parts, 0 missing, order –, proofread, excluded no-text
    - text: Dictionnaire historique et critique/11e éd./Tome 16
1. `L’Enfer de la Bibliothèque nationale`: subpages, reference, 13 parts, 0 missing, order toc, proofread
    - toc: L’Enfer de la Bibliothèque nationale
    - chapter: Préface (`L’Enfer de la Bibliothèque nationale/Préface`) [container]
    - text: 1 à 99 (`L’Enfer de la Bibliothèque nationale/1`) [container]
    - text: 100 à 199 (`L’Enfer de la Bibliothèque nationale/2`) [container]
    - text: 200 à 294 (`L’Enfer de la Bibliothèque nationale/3`) [container]
    - text: 295 à 400 (`L’Enfer de la Bibliothèque nationale/4`) [container]
    - text: 401 à 499 (`L’Enfer de la Bibliothèque nationale/5`) [container]
    - text: 500 à 599 (`L’Enfer de la Bibliothèque nationale/6`) [container]
    - text: 600 à 699 (`L’Enfer de la Bibliothèque nationale/7`) [container]
    - text: 700 à 799 (`L’Enfer de la Bibliothèque nationale/8`) [container]
    - text: 800 à 825 (`L’Enfer de la Bibliothèque nationale/9`) [container]
    - text: 901 à 939 (`L’Enfer de la Bibliothèque nationale/10`) [container]
    - … 2 more
1. `Dictionnaire infernal/3e éd., 1844`: single, reference, 1 parts, 1 missing, order –, proofread
    - text: Dictionnaire infernal/3e éd., 1844
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
1. `Dictionnaire de l’ancien langage françois/1re éd., 1767`: single, reference, 1 parts, 0 missing, order –, unproofread
    - text: Dictionnaire de l’ancien langage françois/1re éd., 1767
1. `Nouveau Dictionnaire de la langue française de Dochez`: subpages, reference, 4 parts, 0 missing, order scan, proofread
    - toc: Nouveau Dictionnaire de la langue française de Dochez
    - text: Titre (`Nouveau Dictionnaire de la langue française de Dochez/Titre`) [container]
    - chapter: Tome 1a (`Nouveau Dictionnaire de la langue française de Dochez/Tome 1a`) [container]
    - text: Avertissement de l'auteur (`Nouveau Dictionnaire de la langue française de Dochez/Avertissement de l'auteur`) [container]
    - text: Discours préliminaire (`Nouveau Dictionnaire de la langue française de Dochez/Discours préliminaire`) [container]
1. `Dictionaire général et curieux`: subpages, reference, 25 parts, 0 missing, order natural, unproofread
    - front: Dictionaire général et curieux
    - text: A Messieurs les imprimeurs (`Dictionaire général et curieux/A Messieurs`) [hierarchy]
    - text: Abandonner (`Dictionaire général et curieux/Abandonner`) [hierarchy]
    - text: Abastardir (`Dictionaire général et curieux/Abastardir`) [hierarchy]
    - text: Abattre (`Dictionaire général et curieux/Abattre`) [hierarchy]
    - text: Abbaisser (`Dictionaire général et curieux/Abbaisser`) [hierarchy]
    - text: Abbé (`Dictionaire général et curieux/Abbé`) [hierarchy]
    - text: Abeilles (`Dictionaire général et curieux/Abeilles`) [hierarchy]
    - text: Abolition (`Dictionaire général et curieux/Abolition`) [hierarchy]
    - text: Abondance (`Dictionaire général et curieux/Abondance`) [hierarchy]
    - text: Abord (`Dictionaire général et curieux/Abord`) [hierarchy]
    - text: Age (`Dictionaire général et curieux/Age`) [hierarchy]
    - … 14 more
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
1. `Dictionnaire historique Juigné/1re éd., 1627`: subpages, reference, 5 parts, 2 missing, order natural, unproofread
    - front: Dictionnaire historique Juigné/1re éd., 1627
    - text: Aristote (`Dictionnaire historique Juigné/1re éd., 1627/Aristote`) [container]
    - text: Index alphabétique - A (`Dictionnaire historique Juigné/1re éd., 1627/Index alphabétique - A`) [hierarchy]
    - text: Index alphabétique - E (`Dictionnaire historique Juigné/1re éd., 1627/Index alphabétique - E`) [hierarchy]
    - text: Index alphabétique - Z (`Dictionnaire historique Juigné/1re éd., 1627/Index alphabétique - Z`) [hierarchy]
    - text: Zoroastre (`Dictionnaire historique Juigné/1re éd., 1627/Zoroastre`) [container]
1. `Dictionnaire de Trévoux/5e édition, 1752`: subpages, reference, 127 parts, 5104 missing, order natural, proofread
    - front: Dictionnaire de Trévoux/5e édition, 1752
    - text: Abater (`Dictionnaire de Trévoux/5e édition, 1752/Abater`) [container]
    - volume: Index alphabétique - A (`Dictionnaire de Trévoux/5e édition, 1752/Index alphabétique - A`) [hierarchy]
      - chapter: Introduction (`Dictionnaire de Trévoux/5e édition, 1752/Introduction`) [container]
      - text: A (lettre) (`Dictionnaire de Trévoux/5e édition, 1752/A (lettre)`) [container]
      - text: A s. m. (`Dictionnaire de Trévoux/5e édition, 1752/A s. m.`) [container]
      - text: A (verbe) (`Dictionnaire de Trévoux/5e édition, 1752/A (verbe)`) [container]
      - text: A (exclamation) (`Dictionnaire de Trévoux/5e édition, 1752/A (exclamation)`) [container]
      - text: A (prép.) (`Dictionnaire de Trévoux/5e édition, 1752/A (prép.)`) [container]
      - text: aa (`Dictionnaire de Trévoux/5e édition, 1752/aa`) [container]
      - text: Aa (monnaie) (`Dictionnaire de Trévoux/5e édition, 1752/Aa (monnaie)`) [container]
      - text: Aa (rivières) (`Dictionnaire de Trévoux/5e édition, 1752/Aa (rivières)`) [container]
    - … 116 more
1. `Dictionnaire pratique et historique de la musique`: subpages, reference, 2,416 parts, 0 missing, order natural, proofread
    - front: Dictionnaire pratique et historique de la musique
    - chapter: V (`Dictionnaire pratique et historique de la musique/V`) [hierarchy]
    - chapter: M (`Dictionnaire pratique et historique de la musique/M`) [hierarchy]
    - text: Allargando (`Dictionnaire pratique et historique de la musique/Allargando`) [container]
    - text: Avertissement (`Dictionnaire pratique et historique de la musique/Avertissement`) [container]
    - text: Ces (`Dictionnaire pratique et historique de la musique/Ces`) [container]
    - text: Clairon (`Dictionnaire pratique et historique de la musique/Clairon`) [container]
    - text: E (`Dictionnaire pratique et historique de la musique/E`) [container]
    - text: E u o u a e (`Dictionnaire pratique et historique de la musique/E u o u a e`) [hierarchy]
    - text: Ecphonèse (`Dictionnaire pratique et historique de la musique/Ecphonèse`) [hierarchy]
    - text: Effet (`Dictionnaire pratique et historique de la musique/Effet`) [hierarchy]
    - text: Eis (`Dictionnaire pratique et historique de la musique/Eis`) [hierarchy]
    - … 2,405 more
1. `Origines de la langue française/Nlle éd. posth., 1694`: linked, reference, 1 parts, 2 missing, order single, unproofread
    - toc: Origines de la langue française/Nlle éd. posth., 1694
    - text: de Caseneuve, 1694 (`Origines de la langue française/de Caseneuve, 1694`) [container]
1. `Thresor de la langue françoise`: subpages, reference, 3,357 parts, 4690 missing, order natural, unproofread
    - front: Thresor de la langue françoise
    - chapter: I (`Thresor de la langue françoise/I`) [hierarchy]
    - chapter: V (`Thresor de la langue françoise/V`) [hierarchy]
    - chapter: X (`Thresor de la langue françoise/X`) [hierarchy]
    - chapter: L (`Thresor de la langue françoise/L`) [hierarchy]
    - chapter: C (`Thresor de la langue françoise/C`) [hierarchy]
    - chapter: D (`Thresor de la langue françoise/D`) [hierarchy]
    - chapter: M (`Thresor de la langue françoise/M`) [hierarchy]
    - text: 005-066 (`Thresor de la langue françoise/005-066`) [hierarchy]
    - text: A (`Thresor de la langue françoise/A`) [hierarchy]
    - text: B (`Thresor de la langue françoise/B`) [hierarchy]
    - text: Burre (participe) (`Thresor de la langue françoise/Burre (participe)`) [container]
    - … 3,346 more
1. `Dictionnaire historique et critique/11e éd./Tome 06`: single, reference, 1 parts, 0 missing, order –, unproofread, excluded no-text
    - text: Dictionnaire historique et critique/11e éd./Tome 06

### H: deep nesting (164)

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
1. `Œuvres de Saint Bernard`: mixed, book, 43 parts, 32 missing, order scan-volumes, proofread
    - front: Œuvres de Saint Bernard
    - text: Bref apostolique (`Œuvres de Saint Bernard/Bref apostolique`) [container]
    - chapter: Préface (`Œuvres de Saint Bernard/Préface`) [container]
    - volume: 1 — Histoire de saint Bernard et de son siècle (`Œuvres de Saint Bernard/Histoire de saint Bernard et de son siècle`) [hierarchy]
      - chapter: Introduction. (`Œuvres de Saint Bernard/Histoire de saint Bernard et de son siècle/0`) [container]
      - volume: 1 (`Œuvres de Saint Bernard/Histoire de saint Bernard et de son siècle/1`) [container]
        - chapter: 01 (`Œuvres de Saint Bernard/Histoire de saint Bernard et de son siècle/1/01`) [container]
        - chapter: 02 (`Œuvres de Saint Bernard/Histoire de saint Bernard et de son siècle/1/02`) [container]
        - chapter: 03 (`Œuvres de Saint Bernard/Histoire de saint Bernard et de son siècle/1/03`) [container]
        - chapter: 04 (`Œuvres de Saint Bernard/Histoire de saint Bernard et de son siècle/1/04`) [container]
        - chapter: 05 (`Œuvres de Saint Bernard/Histoire de saint Bernard et de son siècle/1/05`) [container]
        - chapter: 06 (`Œuvres de Saint Bernard/Histoire de saint Bernard et de son siècle/1/06`) [container]
    - … 32 more
1. `Philosophie anatomique. Des monstruosités humaines`: subpages, book, 33 parts, 1 missing, order single, proofread
    - front: Philosophie anatomique. Des monstruosités humaines
    - volume: Table des matières détaillée (`Philosophie anatomique. Des monstruosités humaines/Table des matières`) [container]
      - text: Dédicace (`Philosophie anatomique. Des monstruosités humaines/Dédicace`) [container]
      - chapter: Préface (`Philosophie anatomique. Des monstruosités humaines/Préface`) [container]
      - text: Discours préliminaire (`Philosophie anatomique. Des monstruosités humaines/Discours préliminaire`) [container]
      - volume: Sur plusieurs déformations du crâne de l’homme (`Philosophie anatomique. Des monstruosités humaines/Sur plusieurs déformations du crâne de l’homme`) [container]
        - text: § I (`Philosophie anatomique. Des monstruosités humaines/Sur plusieurs déformations du crâne de l’homme/§ I`) [container]
        - text: § II (`Philosophie anatomique. Des monstruosités humaines/Sur plusieurs déformations du crâne de l’homme/§ II`) [container]
        - text: § III (`Philosophie anatomique. Des monstruosités humaines/Sur plusieurs déformations du crâne de l’homme/§ III`) [container]
        - text: § IV (`Philosophie anatomique. Des monstruosités humaines/Sur plusieurs déformations du crâne de l’homme/§ IV`) [container]
      - text: Considérations d’où sont déduites des règles pour l’observation des monstres et pour leur classification (`Philosophie anatomique. Des monstruosités humaines/Considérations d’où sont déduites des règles pour l’observation des monstres et pour leur classification`) [container]
      - text: ,etc. (`Philosophie anatomique. Des monstruosités humaines/Des faits anatomiques et physiologiques de l’anencéphalie, observés sur un anencéphale humain né à Paris en mars 1821`) [container]
    - … 22 more
1. `Encyclopédie méthodique`: mixed, reference, 2,874 parts, 1139 missing, order toc-partial, unproofread
    - front: Encyclopédie méthodique
    - volume: Encyclopédie méthodique Agriculture (`Encyclopédie méthodique/Agriculture`) [hierarchy]
      - chapter: Tome 1 (`Encyclopédie méthodique/Agriculture/Tome 1`) [container]
    - volume: Encyclopédie méthodique Amusements (`Encyclopédie méthodique/Amusements`) [hierarchy]
      - volume: Index alphabétique - A (`Encyclopédie méthodique/Amusements/Index alphabétique - A`) [hierarchy]
        - text: Abeilles (`Encyclopédie méthodique/Amusements/Abeilles`) [container]
        - text: Academie de jeu (`Encyclopédie méthodique/Amusements/Academie de jeu`) [container]
        - text: Acoustique (`Encyclopédie méthodique/Amusements/Acoustique`) [container]
        - text: Acrobates (hist. anc.) (`Encyclopédie méthodique/Amusements/Acrobates (hist. anc.)`) [container]
        - text: Adresse des mains (`Encyclopédie méthodique/Amusements/Adresse des mains`) [container]
        - text: Agathes & dendrites imitées (`Encyclopédie méthodique/Amusements/Agathes & dendrites imitées`) [container]
        - text: Aigue-marine imitée (`Encyclopédie méthodique/Amusements/Aigue-marine imitée`) [container]
    - … 2,863 more
1. `Les Misérables (1908)`: subpages, book, 442 parts, 0 missing, order scan-volumes, proofread
    - front: Les Misérables (1908)
    - volume: Tome 1 (`Les Misérables (1908)/Tome 1`) [hierarchy]
      - chapter: Préface (`Les Misérables (1908)/Tome 1/Préface`) [container]
      - text: FANTINE (`Les Misérables (1908)/Tome 1/Fantine`) [container]
      - volume: LIVRE PREMIER (`Les Misérables (1908)/Tome 1/Livre 1`) [container]
        - chapter: 01 (`Les Misérables (1908)/Tome 1/Livre 1/01`) [container]
        - chapter: 02 (`Les Misérables (1908)/Tome 1/Livre 1/02`) [container]
        - chapter: 03 (`Les Misérables (1908)/Tome 1/Livre 1/03`) [container]
        - chapter: 04 (`Les Misérables (1908)/Tome 1/Livre 1/04`) [container]
        - chapter: 05 (`Les Misérables (1908)/Tome 1/Livre 1/05`) [container]
        - chapter: 06 (`Les Misérables (1908)/Tome 1/Livre 1/06`) [container]
        - chapter: 07 (`Les Misérables (1908)/Tome 1/Livre 1/07`) [container]
    - … 431 more
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
1. `Dictionnaire de Trévoux/6e édition, 1771`: mixed, reference, 31,513 parts, 20 missing, order natural, proofread
    - front: Dictionnaire de Trévoux/6e édition, 1771
    - text: Économie (`Dictionnaire de Trévoux, 1771/Économie`) [container]
    - chapter: I (`Dictionnaire de Trévoux/6e édition, 1771/I`) [container]
    - chapter: C (`Dictionnaire de Trévoux/6e édition, 1771/C`) [container]
    - chapter: CI (`Dictionnaire de Trévoux/6e édition, 1771/CI`) [container]
    - chapter: D (`Dictionnaire de Trévoux/6e édition, 1771/D`) [container]
    - chapter: DI (`Dictionnaire de Trévoux/6e édition, 1771/DI`) [container]
    - chapter: DIX (`Dictionnaire de Trévoux/6e édition, 1771/DIX`) [container]
    - text: A (`Dictionnaire de Trévoux/6e édition, 1771/A`) [container]
    - text: A BASSE-NOTE (`Dictionnaire de Trévoux/6e édition, 1771/A BASSE-NOTE`) [container]
    - text: A CAUSE (`Dictionnaire de Trévoux/6e édition, 1771/A CAUSE`) [container]
    - text: A LA FIN (`Dictionnaire de Trévoux/6e édition, 1771/A LA FIN`) [container]
    - … 31,502 more
1. `Ornithologie du Canada`: linked, book, 214 parts, 0 missing, order toc, proofread
    - toc: Ornithologie du Canada
    - text: Épître dédicatoire (`Ornithologie du Canada, 1ère partie/Épitre dédicatoire`) [container]
    - chapter: Préface à la seconde édition (`Ornithologie du Canada, 1ère partie/Préface à la seconde édition`) [container]
    - text: Observations préliminaires (`Ornithologie du Canada, 1ère partie/Observations préliminaires`) [container]
    - text: Notions (`Ornithologie du Canada, 1ère partie/Notions sur la structure des Oiseaux`) [container]
    - text: Les Aigles du Canada (`Ornithologie du Canada, 1ère partie/Les Aigles du Canada`) [container]
    - text: L’Aigle doré (`Ornithologie du Canada, 1ère partie/L’Aigle doré`) [container]
    - text: L’Aigle de Washington (`Ornithologie du Canada, 1ère partie/L’Aigle de Washington`) [container]
    - text: L’Aigle à tête blanche (`Ornithologie du Canada, 1ère partie/L’Aigle à tête blanche`) [container]
    - text: Les Hiboux du Canada (`Ornithologie du Canada, 1ère partie/Les Hiboux du Canada`) [container]
    - text: Le Chat-Huant (`Ornithologie du Canada, 1ère partie/Le Chat-Huant`) [container]
    - text: Le Chat-Huant de Laponie (`Ornithologie du Canada, 1ère partie/Le Chat-Huant de Laponie`) [container]
    - … 203 more
1. `Histoire socialiste de la France contemporaine`: mixed, book, 117 parts, 0 missing, order natural, proofread
    - toc: Histoire socialiste de la France contemporaine
    - chapter: Conclusion : le Bilan social du XIX siècle (`Histoire socialiste de la France contemporaine/Tome XII/Conclusion`) [hierarchy]
    - chapter: Introduction (`Histoire socialiste/Introduction`) [container]
    - chapter: 19 (`Histoire socialiste/La Commune/19`) [container]
    - volume: La Constituante (1789-1791) (`Histoire socialiste/La Constituante`) [container]
      - text: Errata et observations (`Histoire socialiste/La Constituante/Errata et observations`) [hierarchy]
    - text: Causes de la Révolution (`Histoire socialiste/La Constituante/Causes de la Révolution`) [container]
    - text: Constitution civile du clergé (`Histoire socialiste/La Constituante/Constitution civile du clergé`) [container]
    - text: Journées révolutionnaires (`Histoire socialiste/La Constituante/Journées révolutionnaires`) [container]
    - text: La Fuite à Varennes (`Histoire socialiste/La Constituante/La Fuite à Varennes`) [container]
    - text: La Fédération (`Histoire socialiste/La Constituante/La Fédération`) [container]
    - text: La Vie municipale (`Histoire socialiste/La Constituante/La Vie municipale`) [container]
    - … 106 more
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
1. `Les Pères de l’Église`: mixed, book, 266 parts, 2 missing, order scan-volumes, unproofread
    - toc: Les Pères de l’Église
    - volume: Tome 1 (`Les Pères de l’Église/Tome 1`) [hierarchy]
      - text: Dédicace (`Les Pères de l’Église/Tome 1/Dédicace`) [container]
      - chapter: Préface (`Les Pères de l’Église/Tome 1/Préface`) [container]
      - text: Discours préliminaire (`Les Pères de l’Église/Tome 1/Discours préliminaire`) [container]
      - text: Tableau historique du premier siècle de l’Église (`Les Pères de l’Église/Tome 1/Tableau historique du premier siècle de l’Église`) [container]
      - chapter: Notes du tableau historique (`Les Pères de l’Église/Tome 1/Notes du tableau historique du premier siècle`) [container]
      - chapter: Table des événements qui se sont passés pendant le premier siècle (`Les Pères de l’Église/Tome 1/Table des événements qui se sont passés pendant le premier siècle`) [container]
      - text: Conciles et papes pendant le premier siècle (`Les Pères de l’Église/Tome 1/Conciles et papes pendant le premier siècle`) [container]
      - text: Saint Clément, pape. — Notice (`Les Pères de l’Église/Tome 1/Notice sur saint Clément, pape`) [container]
      - text: Épître aux Corinthiens (`Les Pères de l’Église/Tome 1/Épître aux Corinthiens (saint Clément)`) [hierarchy]
      - volume: Ouvrages dont les auteurs sont inconnus (`Les Pères de l’Église/Tome 1/Auteurs inconnus`) [container]
    - … 255 more
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
1. `Théorie des corps de nombres algébriques`: subpages, book, 12 parts, 0 missing, order single, proofread
    - front: Théorie des corps de nombres algébriques
    - volume: Première partie (`Théorie des corps de nombres algébriques/première partie`) [container]
      - volume: Chapitre premier (`Théorie des corps de nombres algébriques/première partie/chapitre premier`) [container]
        - volume: S 1 (`Théorie des corps de nombres algébriques/première partie/chapitre premier/S1`) [container]
          - chapter: Théorème (`Théorie des corps de nombres algébriques/première partie/chapitre premier/S1/théorème1`) [container]
        - volume: S 2 (`Théorie des corps de nombres algébriques/première partie/chapitre premier/S2`) [container]
          - chapter: Théorème 2 (`Théorie des corps de nombres algébriques/première partie/chapitre premier/S2/théorème2`) [container]
          - chapter: Théorème 3 (`Théorie des corps de nombres algébriques/première partie/chapitre premier/S2/théorème3`) [container]
          - chapter: Théorème 4 (`Théorie des corps de nombres algébriques/première partie/chapitre premier/S2/théorème4`) [container]
        - volume: S 3 (`Théorie des corps de nombres algébriques/première partie/chapitre premier/S3`) [container]
          - chapter: Théorème 5 (`Théorie des corps de nombres algébriques/première partie/chapitre premier/S3/théorème5`) [container]
      - volume: Chapitre (`Théorie des corps de nombres algébriques/première partie/chapitre II`) [container]
    - … 1 more
1. `Iconologie (Cesare Ripa, 1643)`: subpages, book, 456 parts, 0 missing, order toc, proofread
    - toc: Iconologie (Cesare Ripa, 1643)
    - text: Abondance (`Iconologie (Cesare Ripa, 1643)/I/Abondance`) [container]
    - text: Academie (`Iconologie (Cesare Ripa, 1643)/I/Academie`) [container]
    - chapter: Acte vertueux (`Iconologie (Cesare Ripa, 1643)/I/Acte vertueux`) [container]
    - text: Amitié (`Iconologie (Cesare Ripa, 1643)/I/Amitié`) [container]
    - text: Amour dompté (`Iconologie (Cesare Ripa, 1643)/I/Amour dompté`) [container]
    - text: Amour vertueux (`Iconologie (Cesare Ripa, 1643)/I/Amour vertueux`) [container]
    - text: Ame courtoiſe (`Iconologie (Cesare Ripa, 1643)/I/Ame courtoise`) [container]
    - text: Agriculture (`Iconologie (Cesare Ripa, 1643)/I/Agriculture`) [container]
    - text: Amour diuin (`Iconologie (Cesare Ripa, 1643)/I/Amour divin`) [container]
    - text: Amour de la Patrie (`Iconologie (Cesare Ripa, 1643)/I/Amour de la patrie`) [container]
    - text: Ame bien-heureuſe (`Iconologie (Cesare Ripa, 1643)/I/Ame bien-heureuse`) [container]
    - … 445 more
1. `Dupleix et l’Inde française/1`: mixed, book, 46 parts, 0 missing, order scan, proofread
    - toc: Dupleix et l’Inde française/1
    - chapter: 13 (`Dupleix et l’Inde française/1/13`) [container]
    - volume: Dupleix et l’Inde française [container]
      - chapter: 0 (`Dupleix et l’Inde française/1/0`) [container]
      - chapter: 1 (`Dupleix et l’Inde française/1/1`) [container]
      - chapter: 2 (`Dupleix et l’Inde française/1/2`) [container]
      - chapter: III (`Dupleix et l’Inde française/1/3`) [container]
      - chapter: IV (`Dupleix et l’Inde française/1/4`) [container]
      - chapter: V (`Dupleix et l’Inde française/1/5`) [container]
      - chapter: VI (`Dupleix et l’Inde française/1/6`) [container]
      - chapter: VII (`Dupleix et l’Inde française/1/7`) [container]
      - chapter: VIII (`Dupleix et l’Inde française/1/8`) [container]
    - … 35 more
1. `Œuvres de Florian`: subpages, collection, 32 parts, 103 missing, order toc, unproofread
    - toc: Œuvres de Florian
    - volume: FABLES PAR ORDRE ALPHABÉTIQUE (`Œuvres de Florian/Fables`) [container]
      - volume: 1 (`Œuvres de Florian/Fables/1`) [container]
        - text: La Fable et la Vérité (`Œuvres de Florian/Fables/1/La Fable et la Vérité`) [container]
        - text: Le Bœuf, le Cheval et l’Âne (`Œuvres de Florian/Fables/1/Le Bœuf, le Cheval et l’Âne`) [container]
        - text: Le Chat et le Miroir (`Œuvres de Florian/Fables/1/Le Chat et le Miroir`) [container]
        - text: Le Calife (`Œuvres de Florian/Fables/1/Le Calife`) [container]
        - text: La Mort (`Œuvres de Florian/Fables/1/La Mort`) [container]
        - text: Le Rossignol et le Prince (`Œuvres de Florian/Fables/1/Le Rossignol et le Prince`) [container]
      - volume: 2 (`Œuvres de Florian/Fables/2`) [container]
        - text: Le Grillon (`Œuvres de Florian/Fables/2/Le Grillon`) [container]
        - text: La Pie et la Colombe (`Œuvres de Florian/Fables/2/La Pie et la Colombe`) [container]
    - … 21 more
1. `L’Empire des tsars et les Russes`: subpages, book, 122 parts, 0 missing, order scan-volumes, proofread
    - front: L’Empire des tsars et les Russes
    - chapter: Tome 1 (`L’Empire des tsars et les Russes/Texte entier/Tome 1`) [container]
    - volume: TOME 1. LE PAYS ET LES HABITANTS (`L’Empire des tsars et les Russes/Tome 1`) [container]
      - chapter: Avant-propos de la troisième édition (`L’Empire des tsars et les Russes/Tome 1/Avant-propos de la troisième édition`) [container]
      - chapter: Avant-propos de la deuxième édition (`L’Empire des tsars et les Russes/Tome 1/Avant-propos de la deuxième édition`) [container]
      - chapter: Préface de la première édition (`L’Empire des tsars et les Russes/Tome 1/Préface de la première édition`) [container]
      - volume: Table des matières (`L’Empire des tsars et les Russes/Tome 1/Table des matières`) [container]
        - volume: LIVRE I (`L’Empire des tsars et les Russes/Tome 1/Livre 1`) [container]
          - chapter: Chapitre 1 (`L’Empire des tsars et les Russes/Tome 1/Livre 1/Chapitre 1`) [container]
          - chapter: Chapitre 2 (`L’Empire des tsars et les Russes/Tome 1/Livre 1/Chapitre 2`) [container]
          - chapter: Chapitre 3 (`L’Empire des tsars et les Russes/Tome 1/Livre 1/Chapitre 3`) [container]
        - volume: LIVRE II (`L’Empire des tsars et les Russes/Tome 1/Livre 2`) [container]
    - … 111 more
1. `Œuvres de Théophile Gautier (Lemerre, 1890)`: linked, collection, 316 parts, 0 missing, order scan-volumes, unproofread
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
1. `Fontaine aux Perles`: mixed, book, 190 parts, 0 missing, order toc, proofread
    - front: Fontaine aux Perles
    - chapter: CHAPITRE . Les deux voyageurs (`Fontaine aux Perles/1. Les deux voyageurs`) [hierarchy]
    - chapter: II. Dans les taillis (`Fontaine aux Perles/2. Dans les taillis`) [hierarchy]
    - chapter: III. Le garde-française (`Fontaine aux Perles/3. Le garde-française`) [hierarchy]
    - chapter: IV. Bleuette (`Fontaine aux Perles/4. Bleuette`) [hierarchy]
    - chapter: V. Maison déchue (`Fontaine aux Perles/5. Maison déchue`) [hierarchy]
    - chapter: VI. Le retour de la chasse (`Fontaine aux Perles/6. Le retour de la chasse`) [hierarchy]
    - chapter: VII. Le souper (`Fontaine aux Perles/7. Le souper`) [hierarchy]
    - chapter: VIII. La comtesse Anne (`Fontaine aux Perles/8. La comtesse Anne`) [hierarchy]
    - chapter: IX. La piste de Bleuette (`Fontaine aux Perles/9. La piste de Bleuette`) [hierarchy]
    - chapter: X. Aventures de nuit (`Fontaine aux Perles/10. Aventures de nuit`) [hierarchy]
    - chapter: XI. Le souterrain (`Fontaine aux Perles/11. Le souterrain`) [hierarchy]
    - … 179 more
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
1. `Les pères du système taoïste`: subpages, book, 47 parts, 0 missing, order scan, proofread
    - toc: Les pères du système taoïste
    - chapter: Préface (`Les pères du système taoïste/Préface`) [container]
    - text: Lao-Tzeu (`Les pères du système taoïste/Tao-Tei-King`) [container]
    - volume: Lie-tzeu (`Les pères du système taoïste/Lie-tzeu`) [container]
      - volume: Tchoang-tzeu (`Les pères du système taoïste/Tchoang-tzeu`) [container]
        - chapter: 1. Genèse et transformations. (`Les pères du système taoïste/Lie-tzeu/1. Genèse et transformations`) [container]
        - chapter: 2. Simplicité naturelle. (`Les pères du système taoïste/Lie-tzeu/2. Simplicité naturelle`) [container]
        - chapter: 3. États psychiques. (`Les pères du système taoïste/Lie-tzeu/3. États psychiques`) [container]
        - chapter: 4. Extinction et union. (`Les pères du système taoïste/Lie-tzeu/4. Extinction et union`) [container]
        - chapter: 5. Le continu cosmique (`Les pères du système taoïste/Lie-tzeu/5. Le continu cosmique`) [container]
        - chapter: 6. Fatalité (`Les pères du système taoïste/Lie-tzeu/6. Fatalité`) [container]
        - chapter: 7. Yang-tchou (`Les pères du système taoïste/Lie-tzeu/7. Yang-tchou`) [container]
    - … 36 more
1. `En voyage, tome II (Hugo, éd. 1910)`: subpages, book, 115 parts, 0 missing, order toc, proofread
    - toc: En voyage, tome II (Hugo, éd. 1910)
    - text: FRAGMENT D’UN VOYAGE AUX ALPES. (`En voyage, tome II (Hugo, éd. 1910)/Fragment`) [container]
    - text: Fragment d’un voyage aux Alpes (`En voyage, tome II (Hugo, éd. 1910)/Fragment d’un voyage aux Alpes`) [container]
    - volume: FRANCE ET BELGIQUE. (`En voyage, tome II (Hugo, éd. 1910)/France et Belgique`) [container]
      - volume: BRETAGNE ET NORMANDIE. (`En voyage, tome II (Hugo, éd. 1910)/France et Belgique/A`) [container]
        - text: Meulan. — Évreux (`En voyage, tome II (Hugo, éd. 1910)/France et Belgique/A/1`) [container]
        - text: Brest (`En voyage, tome II (Hugo, éd. 1910)/France et Belgique/A/2`) [container]
        - text: Karnac (`En voyage, tome II (Hugo, éd. 1910)/France et Belgique/A/3`) [container]
        - text: Nantes (`En voyage, tome II (Hugo, éd. 1910)/France et Belgique/A/4`) [container]
        - text: Tours (`En voyage, tome II (Hugo, éd. 1910)/France et Belgique/A/5`) [container]
        - text: Étampes (`En voyage, tome II (Hugo, éd. 1910)/France et Belgique/A/6`) [container]
        - text: Montereau. — Coulommiers (`En voyage, tome II (Hugo, éd. 1910)/France et Belgique/A/7`) [container]
    - … 104 more
1. `Théorie analytique des probabilités`: subpages, book, 30 parts, 0 missing, order toc, proofread
    - toc: Théorie analytique des probabilités
    - chapter: table des matières (`Théorie analytique des probabilités/Table détaillée`) [container]
    - text: Avertissement de la seconde édition (`Théorie analytique des probabilités/Avertissement de la seconde édition`) [container]
    - text: Avertissement de la troisième édition (`Théorie analytique des probabilités/Avertissement de la troisième édition`) [container]
    - chapter: INTRODUCTION. (`Théorie analytique des probabilités/Introduction`) [container]
    - volume: LIVRE I. CALCUL DES FONCTIONS GÉNÉRATRICES (`Théorie analytique des probabilités/Livre 1`) [container]
      - volume: Première partie (`Théorie analytique des probabilités/Livre 1/Première partie`) [container]
        - chapter: Chapitre 1 (`Théorie analytique des probabilités/Livre 1/Première partie/Chapitre 1`) [container]
        - chapter: Chapitre 2 (`Théorie analytique des probabilités/Livre 1/Première partie/Chapitre 2`) [container]
      - volume: Seconde partie (`Théorie analytique des probabilités/Livre 1/Seconde partie`) [container]
        - chapter: Chapitre 1 (`Théorie analytique des probabilités/Livre 1/Seconde partie/Chapitre 1`) [container]
        - chapter: Chapitre 2 (`Théorie analytique des probabilités/Livre 1/Seconde partie/Chapitre 2`) [container]
    - … 19 more
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
1. `L’Imitation de Jésus-Christ (Lamennais)`: subpages, book, 128 parts, 0 missing, order toc, proofread
    - toc: L’Imitation de Jésus-Christ (Lamennais)
    - text: Frontispice (`L’Imitation de Jésus-Christ (Lamennais)/Frontispice`) [container]
    - text: Avis de l’éditeur (`L’Imitation de Jésus-Christ (Lamennais)/Avis de l’éditeur`) [container]
    - chapter: I. Éloges de l’Imitation (`L’Imitation de Jésus-Christ (Lamennais)/Éloges de l’Imitation`) [container]
    - chapter: II. Auteur de l’Imitation (`L’Imitation de Jésus-Christ (Lamennais)/Auteur de l’Imitation`) [container]
    - chapter: III. Traduction de F. de Lamennais (`L’Imitation de Jésus-Christ (Lamennais)/Traduction de F. de Lamennais`) [container]
    - chapter: IV. Bibliographie (`L’Imitation de Jésus-Christ (Lamennais)/Bibliographie`) [container]
    - volume: Table des chapitres (`L’Imitation de Jésus-Christ (Lamennais)/Table`) [container]
      - chapter: Préface (`L’Imitation de Jésus-Christ (Lamennais)/Préface`) [container]
      - volume: LIVRE PREMIER (`L’Imitation de Jésus-Christ (Lamennais)/Livre premier`) [container]
        - chapter: 01 (`L’Imitation de Jésus-Christ (Lamennais)/Livre premier/01`) [container]
        - chapter: 02 (`L’Imitation de Jésus-Christ (Lamennais)/Livre premier/02`) [container]
    - … 117 more
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

### I: works with other editions (579)

1. `Saynètes et Monologues`: mixed, collection, 16 parts, 0 missing, order scan-volumes, proofread
    - toc: Saynètes et Monologues
    - text: La Famille Dubois [container]
    - text: L’Affaire de la rue Beaubourg [container]
    - text: Le Voyage à trois-étoiles [container]
    - text: Le Bilboquet [container]
    - text: La Bonne (`La Bonne (Charles Cros)`) [container]
    - text: Le Capitaliste [container]
    - text: Mon ami Naz [container]
    - text: Le Hareng saur (`Saynètes et Monologues/Le Hareng saur`) [container]
    - text: Un coup de rasoir [container]
    - text: Le Maître d’armes [container]
    - text: Autrefois [container]
    - … 5 more
1. `Petits Poèmes en prose`: mixed, collection, 47 parts, 1 missing, order scan, validated
    - toc: Petits Poèmes en prose
    - text: L’Étranger [container]
    - text: La Chambre double [container]
    - text: Chacun sa chimère [container]
    - text: Le Fou et la Vénus [container]
    - text: Le Chien et le Flacon [container]
    - text: Le Mauvais Vitrier [container]
    - text: À une heure du matin [container]
    - text: La Femme sauvage et la Petite-Maîtresse [container]
    - text: Les Foules [container]
    - text: Les Veuves [container]
    - text: Le Vieux Saltimbanque [container]
    - … 36 more
1. `« J’ai l’esprit tout ennuyé »/Buon, 1587`: single, collection, 1 parts, 0 missing, order –, proofread
    - text: « J’ai l’esprit tout ennuyé »/Buon, 1587
1. `Le Banquet (trad. Cousin)`: subpages, book, 2 parts, 0 missing, order toc, proofread
    - front: Le Banquet (trad. Cousin)
    - chapter: Notes (`Le Banquet (trad. Cousin)/Notes`) [hierarchy]
    - chapter: Notes Additionnelles (`Le Banquet (trad. Cousin)/Notes Additionnelles`) [hierarchy]
1. `Variations sur un sujet/Averses ou Critique`: single, periodical-issue, 1 parts, 0 missing, order –, unproofread
    - text: Variations sur un sujet/Averses ou Critique
1. `Les Plaintes d’une Amoureuse (trad. Hugo)`: single, collection, 1 parts, 0 missing, order –, validated
    - text: Les Plaintes d’une Amoureuse (trad. Hugo)
1. `Pensées d’août`: subpages, collection, 56 parts, 0 missing, order toc, proofread
    - toc: Pensées d’août
    - text: Avertissement (`Pensées d’août/Avertissement`) [container]
    - text: Pensée d’août (`Pensées d’août/Pensée d’août`) [container]
    - chapter: M. Jean, maître d’école (`Pensées d’août/Monsieur Jean`) [hierarchy]
    - text: À madame Tastu (`Pensées d’août/À madame Tastu`) [hierarchy]
    - text: À M. Achille du Clésieux (`Pensées d’août/À M. Achille du Clésieux`) [hierarchy]
    - text: Au Thil où vous aimez (`Pensées d’août/« Au Thil où vous aimez passer les mois fleuris »`) [hierarchy]
    - text: Ainsi l’on dit de vous (`Pensées d’août/« Ainsi l’on dit de vous, Madame, ainsi vous êtes »`) [hierarchy]
    - text: À mes amis Grégoire et Collombet (`Pensées d’août/À mes amis Grégoire et Collombet`) [hierarchy]
    - text: À Victor Pavie (`Pensées d’août/À Victor Pavie, le soir de son mariage`) [hierarchy]
    - text: Heureux loin de Paris (`Pensées d’août/Heureux loin de Paris`) [hierarchy]
    - text: de sainte Thérèse, à Jésus crucifié (`Pensées d’août/Sonnet de sainte Thérèse`) [hierarchy]
    - … 45 more
1. `Variations sur un sujet/La Cour`: single, periodical-issue, 1 parts, 0 missing, order –, unproofread
    - text: Variations sur un sujet/La Cour
1. `Des délits et des peines (trad. Collin de Plancy)`: subpages, book, 58 parts, 0 missing, order scan, proofread
    - toc: Des délits et des peines (trad. Collin de Plancy)
    - text: . (`Des délits et des peines (trad. Collin de Plancy)/Avertissement du traducteur`) [container]
    - text: . (`Des délits et des peines (trad. Collin de Plancy)/Notice sur Beccaria`) [container]
    - volume: DES DÉLITS ET DES PEINES. (`Des délits et des peines (trad. Collin de Plancy)/Des délits et des peines`) [hierarchy]
      - text: del’auteur (`Des délits et des peines (trad. Collin de Plancy)/Des délits et des peines/Préface`) [container]
      - text: . (`Des délits et des peines (trad. Collin de Plancy)/Des délits et des peines/Chapitre I`) [container]
      - text: . (`Des délits et des peines (trad. Collin de Plancy)/Des délits et des peines/Chapitre II`) [container]
      - text: . (`Des délits et des peines (trad. Collin de Plancy)/Des délits et des peines/Chapitre III`) [container]
      - text: . (`Des délits et des peines (trad. Collin de Plancy)/Des délits et des peines/Chapitre IV`) [container]
      - text: Supplément au chapitre IV (`Des délits et des peines (trad. Collin de Plancy)/Des délits et des peines/Supplément au chapitre IV`) [container]
      - text: . (`Des délits et des peines (trad. Collin de Plancy)/Des délits et des peines/Chapitre V`) [container]
      - text: . (`Des délits et des peines (trad. Collin de Plancy)/Des délits et des peines/Chapitre VI`) [container]
    - … 47 more
1. `La Nouvelle Emma`: subpages, book, 54 parts, 0 missing, order toc, proofread
    - toc: La Nouvelle Emma
    - text: Avertissement (`La Nouvelle Emma/Avertissement`) [container]
    - chapter: I (`La Nouvelle Emma/1`) [container]
    - chapter: II (`La Nouvelle Emma/2`) [container]
    - chapter: III (`La Nouvelle Emma/3`) [container]
    - chapter: IV (`La Nouvelle Emma/4`) [container]
    - chapter: V (`La Nouvelle Emma/5`) [container]
    - chapter: VI (`La Nouvelle Emma/6`) [container]
    - chapter: VII (`La Nouvelle Emma/7`) [container]
    - chapter: VIII (`La Nouvelle Emma/8`) [container]
    - chapter: IX (`La Nouvelle Emma/9`) [container]
    - chapter: X (`La Nouvelle Emma/10`) [container]
    - … 43 more
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
1. `La tragédie de Locrine (trad. Hugo)`: single, book, 1 parts, 0 missing, order –, proofread
    - text: La tragédie de Locrine (trad. Hugo)
1. `Les Aventures de Sherlock Holmes`: subpages, collection, 7 parts, 0 missing, order toc, validated
    - toc: Les Aventures de Sherlock Holmes
    - chapter: Préface (`Les Aventures de Sherlock Holmes/Préface`) [container]
    - text: L’Escarboucle bleue (`Les Aventures de Sherlock Holmes/L’Escarboucle bleue`) [container]
    - text: Aventure de la bande mouchetée (`Les Aventures de Sherlock Holmes/Aventure de la bande mouchetée`) [container]
    - text: Le pouce de l’ingénieur (`Les Aventures de Sherlock Holmes/Le Pouce de l’ingénieur`) [container]
    - text: L’aristocratique célibataire (`Les Aventures de Sherlock Holmes/L’Aristocratique célibataire`) [container]
    - text: Le diadème de béryls (`Les Aventures de Sherlock Holmes/Le Diadème de béryls`) [container]
    - text: Les Hêtres Pourpres (`Les Aventures de Sherlock Holmes/Les Hêtres pourpres`) [container]
1. `Les Rayons et les Ombres`: subpages, collection, 45 parts, 0 missing, order toc, unproofread
    - front: Les Rayons et les Ombres
    - chapter: Préface (`Les Rayons et les Ombres/Préface`) [container]
    - text: Fonction du poëte (`Les Rayons et les Ombres/Fonction du poëte`) [container]
    - text: Le sept août mil huit cent vingt-neuf (`Les Rayons et les Ombres/Le Sept Août 1829`) [container]
    - text: Au roi Louis-Philippe après un arrêt de mort (`Les Rayons et les Ombres/Au roi Louis-Philippe après l’arrêt de mort prononcé le 12 juillet 1839`) [container]
    - text: Regard jeté dans une mansarde (`Les Rayons et les Ombres/Regard jeté dans une mansarde`) [container]
    - text: On croyait dans ces temps où le pâtre nocturne (`Les Rayons et les Ombres/« On croyait dans ces temps où le pâtre nocturne »`) [container]
    - text: Sur un homme populaire (`Les Rayons et les Ombres/Sur un homme populaire`) [container]
    - text: Le monde et le siècle (`Les Rayons et les Ombres/Le Monde et le Siècle`) [container]
    - text: À M. le duc de *** (`Les Rayons et les Ombres/À M. le d. de ***`) [container]
    - text: À Mademoiselle Fanny de P. (`Les Rayons et les Ombres/À Mademoiselle Fanny de P.`) [container]
    - text: Comme dans les étangs assoupis sous les bois (`Les Rayons et les Ombres/« Comme dans les étangs assoupis sous les bois »`) [container]
    - … 34 more
1. `Michel Strogoff (théâtre 1881)`: single, book, 1 parts, 0 missing, order –, proofread
    - text: Michel Strogoff (théâtre 1881)
1. `Le Portier des Chartreux`: subpages, book, 2 parts, 0 missing, order toc, proofread
    - toc: Le Portier des Chartreux
    - chapter: Première partie (`Le Portier des Chartreux/Première partie`) [container]
    - chapter: Seconde partie (`Le Portier des Chartreux/Deuxième partie`) [container]
1. `Histoire de dom Bougre, portier des chartreux, éd. 1920`: subpages, book, 3 parts, 0 missing, order toc, proofread
    - toc: Histoire de dom Bougre, portier des chartreux, éd. 1920
    - chapter: Préface (`Histoire de dom Bougre, portier des chartreux, éd. 1920/000`) [container]
    - chapter: Première partie (`Histoire de dom Bougre, portier des chartreux, éd. 1920/001`) [container]
    - chapter: Seconde partie (`Histoire de dom Bougre, portier des chartreux, éd. 1920/002`) [container]
1. `Poèmes et Poésies (Dierx)`: subpages, collection, 22 parts, 0 missing, order toc, unproofread
    - toc: Poèmes et Poésies (Dierx)
    - text: La Vision d’Ève (`Poèmes et Poésies (Dierx)/La Vision d’Ève`) [hierarchy]
    - text: La Fée Hamonde (`Poèmes et Poésies (Dierx)/La Fée Hamonde`) [hierarchy]
    - text: Crépuscule (`Poèmes et Poésies (Dierx)/Crépuscule`) [hierarchy]
    - text: L’Image (`Poèmes et Poésies (Dierx)/L’Image`) [hierarchy]
    - text: Après le Bain (`Poèmes et Poésies (Dierx)/Après le bain`) [hierarchy]
    - text: Salvator Rosa (`Poèmes et Poésies (Dierx)/Salvator Rosa`) [hierarchy]
    - text: Souré-Ha (`Poèmes et Poésies (Dierx)/Souré-Ha`) [hierarchy]
    - text: En chemin (`Poèmes et Poésies (Dierx)/En chemin`) [hierarchy]
    - text: La Soif (`Poèmes et Poésies (Dierx)/La Soif`) [hierarchy]
    - text: Soleil couchant (`Poèmes et Poésies (Dierx)/Soleil couchant`) [hierarchy]
    - text: L’Œil (`Poèmes et Poésies (Dierx)/L’Œil`) [hierarchy]
    - … 11 more
1. `Les Mémoires d’une chanteuse allemande`: subpages, book, 22 parts, 0 missing, order toc, proofread
    - toc: Les Mémoires d’une chanteuse allemande
    - chapter: Préface de l’édition de 1911 (`Les Mémoires d’une chanteuse allemande/00-1`) [container]
    - chapter: Préface de l’éditeur allemand (`Les Mémoires d’une chanteuse allemande/00-2`) [container]
    - chapter: Préface du Destinataire (`Les Mémoires d’une chanteuse allemande/00-3`) [container]
    - chapter: Introduction de Guillaume Apollinaire pour l’édition de 1913 (`Les Mémoires d’une chanteuse allemande/00-4`) [container]
    - text: Epistolæ novæ obscurorum virorum (`Les Mémoires d’une chanteuse allemande/00-5`) [container]
    - text: Présentation (`Les Mémoires d’une chanteuse allemande/01-0`) [container]
    - chapter: Chapitre premier — L’amour conjugal (`Les Mémoires d’une chanteuse allemande/01-1`) [container]
    - chapter: Chapitre II — Leçons d’amour (`Les Mémoires d’une chanteuse allemande/01-2`) [container]
    - chapter: Chapitre III — Marguerite (`Les Mémoires d’une chanteuse allemande/01-3`) [container]
    - chapter: Chapitre IV — Philosophie de l’amour physique (`Les Mémoires d’une chanteuse allemande/01-4`) [container]
    - chapter: Chapitre V — Franzl (`Les Mémoires d’une chanteuse allemande/01-5`) [container]
    - … 11 more
1. `Églogue napolitaine (RDDM)`: single, periodical-issue, 1 parts, 0 missing, order –, validated
    - text: Églogue napolitaine (RDDM)
1. `Les Précoces`: subpages, book, 17 parts, 0 missing, order single, validated
    - toc: Les Précoces
    - volume: Sommaire (`Les Précoces/Sommaire`) [hierarchy]
      - chapter: Chapitre I (`Les Précoces/Chapitre 1`) [container]
      - chapter: Chapitre II (`Les Précoces/Chapitre 2`) [container]
      - chapter: Chapitre III (`Les Précoces/Chapitre 3`) [container]
      - chapter: Chapitre IV (`Les Précoces/Chapitre 4`) [container]
      - chapter: Chapitre V (`Les Précoces/Chapitre 5`) [container]
      - chapter: Chapitre VI (`Les Précoces/Chapitre 6`) [container]
      - chapter: Chapitre VII (`Les Précoces/Chapitre 7`) [container]
      - chapter: Chapitre VIII (`Les Précoces/Chapitre 8`) [container]
      - chapter: Chapitre IX (`Les Précoces/Chapitre 9`) [container]
      - chapter: Chapitre X (`Les Précoces/Chapitre 10`) [container]
    - … 6 more
1. `Le Coffret de santal (éd. 1879)`: subpages, collection, 112 parts, 0 missing, order toc, proofread
    - toc: Le Coffret de santal (éd. 1879)
    - chapter: Préface (`Le Coffret de santal (éd. 1879)/Préface`) [container]
    - text: La Vie idéale (`Le Coffret de santal (éd. 1879)/La Vie idéale`) [container]
    - text: Nocturne (`Le Coffret de santal (éd. 1879)/Nocturne`) [container]
    - text: L’Orgue (`Le Coffret de santal (éd. 1879)/L’Orgue`) [container]
    - text: Ronde flamande (`Le Coffret de santal (éd. 1879)/Ronde flamande`) [container]
    - text: Rendez-vous (`Le Coffret de santal (éd. 1879)/Rendez-vous`) [container]
    - text: Roses et Muguets (`Le Coffret de santal (éd. 1879)/Roses et Muguets`) [container]
    - text: La Dame en pierre (`Le Coffret de santal (éd. 1879)/La Dame en pierre`) [container]
    - text: Romance (`Le Coffret de santal (éd. 1879)/Romance`) [container]
    - text: Transition (`Le Coffret de santal (éd. 1879)/Transition`) [container]
    - text: Destinée (`Le Coffret de santal (éd. 1879)/Destinée`) [container]
    - … 101 more
1. `Pages oubliées. Poèmes en prose`: linked, collection, 5 parts, 0 missing, order toc, validated
    - toc: Pages oubliées. Poèmes en prose
    - text: Causerie d’hiver (`Divagations/Édition La Revue des Lettres et des Arts 1867-1868/Causerie d’hiver`) [container]
    - text: Pauvre Enfant pâle (`Divagations/Édition La Revue des Lettres et des Arts 1867-1868/Pauvre Enfant pâle`) [container]
    - text: L’Orgue de barbarie (`Divagations/Édition La Revue des Lettres et des Arts 1867-1868/L’Orgue de barbarie`) [container]
    - text: L’Orphelin (`Divagations/Édition La Revue des Lettres et des Arts 1867-1868/L’Orphelin`) [container]
    - text: La Pipe (`Divagations/Édition La Revue des Lettres et des Arts 1867-1868/La Pipe`) [container]
1. `L’Âne d’or ou les Métamorphoses`: subpages, book, 11 parts, 1 missing, order toc, unproofread
    - toc: L’Âne d’or ou les Métamorphoses
    - chapter: I (`L’Âne d’or ou les Métamorphoses/I`) [container]
    - chapter: II (`L’Âne d’or ou les Métamorphoses/II`) [container]
    - chapter: III (`L’Âne d’or ou les Métamorphoses/III`) [container]
    - chapter: IV (`L’Âne d’or ou les Métamorphoses/IV`) [container]
    - chapter: V (`L’Âne d’or ou les Métamorphoses/V`) [container]
    - chapter: VI (`L’Âne d’or ou les Métamorphoses/VI`) [container]
    - chapter: VII (`L’Âne d’or ou les Métamorphoses/VII`) [container]
    - chapter: VIII (`L’Âne d’or ou les Métamorphoses/VIII`) [container]
    - chapter: IX (`L’Âne d’or ou les Métamorphoses/IX`) [container]
    - chapter: X (`L’Âne d’or ou les Métamorphoses/X`) [container]
    - chapter: XI (`L’Âne d’or ou les Métamorphoses/XI`) [container]
1. `La Fille du Ciel (RDDM)`: single, periodical-issue, 1 parts, 0 missing, order –, proofread
    - text: La Fille du Ciel (RDDM)
1. `Le Viol de Lucrèce (trad. Hugo)`: single, collection, 1 parts, 0 missing, order –, proofread
    - text: Le Viol de Lucrèce (trad. Hugo)
1. `Les Gaietés`: subpages, collection, 71 parts, 0 missing, order toc, proofread
    - toc: Les Gaietés
    - text: La Romance de mademoiselle Justine (`Les Gaietés/La Romance de mademoiselle Justine`) [container]
    - text: Le grand Marcheur (`Les Gaietés/Le grand Marcheur`) [container]
    - text: Le Lavement (`Les Gaietés/Le Lavement`) [container]
    - text: La petite Ouvrière (`Les Gaietés/La petite Ouvrière`) [container]
    - text: Les Consolations (`Les Gaietés/Les Consolations`) [container]
    - text: L’Abbesse d’un Couvent comme il y en a encore beaucoup (`Les Gaietés/L’Abbesse d’un Couvent comme il y en a encore beaucoup`) [container]
    - text: Madame Barbe-bleu, ou l’Ogresse (`Les Gaietés/Madame Barbe-bleu, ou l’Ogresse`) [container]
    - text: Les Culottes (`Les Gaietés/Les Culottes`) [container]
    - text: Ariane et Bacchus (`Les Gaietés/Ariane et Bacchus`) [container]
    - text: L’Hermaphrodite (`Les Gaietés/L’Hermaphrodite`) [container]
    - text: La Relique de saint Nicolas (`Les Gaietés/La Relique de saint Nicolas`) [container]
    - … 60 more
1. `Critias (trad. Cousin)`: subpages, book, 1 parts, 0 missing, order single, proofread
    - front: Critias (trad. Cousin)
    - chapter: Notes (`Critias (trad. Cousin)/Notes`) [hierarchy]
1. `Chansons et rondes enfantines`: mixed, collection, 59 parts, 0 missing, order toc, proofread
    - front: Chansons et rondes enfantines
    - chapter: Avant-propos (`Chansons et rondes enfantines/Avant-propos`) [container]
    - text: Ah ! mon beau château (ronde) (`Chansons et rondes enfantines/Ah ! mon beau château`) [container]
    - text: Ah ! vous dirai-je, maman (`Chansons et rondes enfantines/Ah ! vous dirai-je, maman`) [container]
    - text: Ainsi font, font, font (`Chansons et rondes enfantines/Ainsi font, font, font`) [container]
    - text: L’Alouette et le Pinson (`Chansons et rondes enfantines/L’Alouette et le Pinson`) [container]
    - text: À ma main droite (`Chansons et rondes enfantines/À ma main droite`) [container]
    - text: À Paris, sur un petit cheval gris (`Chansons et rondes enfantines/À Paris, sur un petit cheval gris`) [container]
    - text: Arlequin tient sa boutique (`Chansons et rondes enfantines/Arlequin tient sa boutique`) [container]
    - text: Au clair de la lune (`Chansons et rondes enfantines/Au clair de la lune`) [container]
    - text: Autrefois le rat de ville (`Chansons et rondes enfantines/Autrefois le rat de ville`) [container]
    - text: Aux quatre coins de Paris (`Chansons et rondes enfantines/Aux quatre coins de Paris`) [container]
    - … 48 more
1. `L’Arbre de Noël (Dostoïevski)`: single, collection, 1 parts, 0 missing, order –, unproofread
    - text: L’Arbre de Noël (Dostoïevski)

