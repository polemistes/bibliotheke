# fr.wikisource: structure and quality survey (WP1a)

Generated 2026-10-31 by `python -I -m wscatalog.survey fr` from `frwikisource-20261001-pages-articles1.xml-p1p1500000.bz2, frwikisource-20261001-pages-articles1.xml-p1500001p3000000.bz2, frwikisource-20261001-pages-articles1.xml-p3000001p4500000.bz2, frwikisource-20261001-pages-articles1.xml-p4500001p5004401.bz2` (resolution and quality rules: PLAN.md 5.2 steps 1–1h and 5.4; config `pipeline/config/fr.yaml`). Resolution took 47 s on the cached extract.

Input: 537,405 ns0 pages, 56,685 ns0 redirects, 4,030,797 scan pages, 28,847 Index pages, 10,886 pages with the `disambiguation` page prop.

## 1. Works

40,889 works resolved from 524,611 non-excluded ns0 pages; 40,195 enter the catalog after the hard exclusions (section 9). Multi-part works hold 484,573 parts, of which 372,241 are searchable texts.

| structure | works | parts | texts |
|---|---|---|---|
| single | 29,091 | 29,091 | 0 |
| subpages | 9,719 | 347,360 | 252,029 |
| virtual | 851 | 52,725 | 49,103 |
| linked | 815 | 22,874 | 17,765 |
| mixed | 413 | 61,614 | 53,344 |

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
| whole-text | 4,284 |
| versions | 55 |

## 3. Patterns A–I (WS§1)

|  | pattern | count |
|---|---|---|
| A | collection of root pages (`structure = linked`) | 815 works, 22,874 parts |
| B | mixed subpages and root pages (`mixed`) | 413 works, 61,614 parts |
| C | work with an oversized page (> oversized_bytes, transcluded scan pages included) | 602 works |
| D | missing root, virtual root (`virtual`) | 851 works, 52,725 parts |
| E | texts inside a subpage collection reached from a disambiguation or versions page | 11,365 parts (9,586 searchable); all searchable subpage texts: 345,288 |
| F | whole-text duplicate subpages (excluded) | 4,284 pages |
| G | reference works (type `reference`) | 274 works, 216,950 parts; 0 pages excluded by title prefix |
| H | deep nesting (parts at depth ≥ 2, i.e. three levels below the root) | 164 works |
| I | several editions: works with an other-versions template | 579 works; 55 versions pages excluded |

Tree depth of multi-part works (levels below the root): 1: 10,644, 2: 990, 3: 129, 4: 29, 5: 5, 6: 1.

## 4. Parent rules and order sources (steps 1b, 1c)

| parent rule | pages |
|---|---|
| container | 372,120 |
| hierarchy | 112,268 |
| own | 40,038 |
| toc | 185 |

| order source (nodes with ≥ 2 children) | nodes |
|---|---|
| toc | 8,326 |
| scan | 2,809 |
| chain | 2,561 |
| scan-volumes | 479 |
| natural | 194 |
| toc-partial | 74 |

`toc-partial` (implementation choice, see section 13): the TOC lists at least half of the children, which keep its order; the rest follow in natural order. 125 `natural` nodes have a TOC that lists fewer than half.

Root pages attached by their header's container link: 9,167; most frequent containers: `Lettres de Madame de Sévigné/Édition Monmerqué` 887; `Œuvres de P. Lachambeaudie. ― Fables et Poésies (1854)` 411; `Les Fables complètes (Viennet)` 213; `Parnasse de la Jeune Belgique` 162; `La Chanson française du XVe au XXe siècle` 147; `La Nuit (Gilkin)` 130; `Œuvres de Sully Prudhomme/Poésies 1866-1872` 128; `Œuvres de Théophile Gautier (Lemerre, 1890)` 124; `Échos de France` 121; `Poésies diverses (Corneille)` 109.

Pages attached by TOC membership (rule 3): 185; most frequent TOC pages: `Poésies Sociales des Ouvriers` 41; `Tamaris (RDDM)` 25; `Le Péché de Madeleine` 25; `Antonia (RDDM)` 12; `Œuvres complètes (Ovide)` 10; `Œuvres complètes de Platon, traduites par Émile Chambry` 8; `Œuvres complètes de François Arago` 6; `Œuvres complètes de Paul Verlaine` 4; `Femmes-Poëtes de la France` 4; `Césarine Dietrich (RDDM)` 3.

## 5. Container links that fail rule 1

36,658 of 412,358 pages whose header carries a container link (8.9 %) point to:

- outside-ns0: 64 (e.g. `Les Grans et Merveilleux Faictz du Seigneur Nemo` → `Livre:Montaiglon - Recueil de poésies françoises des 15e et 16e siècles, tome XI.djvu`, `Monsieur Rousset` → ``, `Le grand ballon captif à vapeur de M. Henri Giffard` → `Auteur:Henri Giffard`, `Phèdre (Racine), Didot, 1854/Acte II` → `Livre:Racine - Œuvres, Didot, 1854.djvu`)
- excluded: 33,176 (e.g. `Le Chemin de Fer du Pacifique et les Expéditions américaines dans l’Ouest` → `Revue des Deux Mondes`, `Histoire d’une minute` → `Le Figaro`, `Le Roi George V` → `Revue des Deux Mondes`, `Beaumarchais, sa vie, ses écrits et son temps/03` → `Revue des Deux Mondes`)
- missing: 3,418 (e.g. `Candide, ou l’Optimisme/Beuchot 1829/Chapitre 6` → `Œuvres de Voltaire/Beuchot/Tome 33`, `Pomme Warner's King` → `Bulletins d’arboriculture, de floriculture et de culture potagère`, `Adrien mourant à son âme` → `Œuvres complètes de Lord Byron`, `Lesley Castle` → `Amour & Amitié et autres œuvres de jeunesse`)

## 6. Prev/next chains across Index boundaries

Of 242,586 next-links between pages that both have an Index, 4,551 (1.9 %) cross to a different Index.
Examples: `Grand dictionnaire universel du XIXe siècle/Aatvidaberg, suède (supplément 2)` (Larousse - Grand dictionnaire universel du XIXe siècle - Tome 17, part. 1, A.djvu) → `Grand dictionnaire universel du XIXe siècle/aavora s. m.` (Larousse - Grand dictionnaire universel du XIXe siècle - Tome 1, part. 1, A-Am.djvu); `Pensées et Fragments inédits de Montesquieu/Notes (I)` (Montesquieu - Pensées et Fragments inédits, t1, 1899.djvu) → `Pensées et Fragments inédits de Montesquieu/V` (Montesquieu - Pensées et Fragments inédits, t2, 1901.djvu); `L’Espagne depuis la révolution de février/01` (Revue des Deux Mondes - 1849 - tome 2.djvu) → `L’Espagne depuis la révolution de février/02` (Revue des Deux Mondes - 1849 - tome 3.djvu); `Chronique de la quinzaine - 14 août 1918` (Revue des Deux Mondes - 1918 - tome 46.djvu) → `Chronique de la quinzaine - 31 août 1918` (Revue des Deux Mondes - 1918 - tome 47.djvu).

Root-page works that sit on a prev/next chain but stay separate works (no container, not in a unique TOC; WS§8 item 5): 3,382.

## 7. Orphan trees, oversized pages, red links

**Orphan trees** (virtual roots): 851, holding 52,725 parts. Examples: `Poètes, romanciers et écrivains contemporains` (1); `Œuvres complètes de Maximilien de Robespierre` (39); `Esprit des lois (1777)` (620); `Revue Musicale de Lyon 1904-02-10` (8); `Le Mahâbhârata (traduction Fauche)` (9); `Agrippa d'Aubigné - Œuvres complètes tome troisième, 1874` (35); `Du baptême (Augustin)` (5); `Le Saint-Concile de Trente (trad. Chanut, 1674)` (1).

**Oversized pages** (> 300,000 bytes of wikitext including transcluded scan pages): 1,265. Largest: `Jean part1` 2.0 MB; `Le Système du Monde/Texte entier/Tome 6` 2.0 MB; `Essais/édition Michaud, 1907/Livre II/Chapitre 12` 1.9 MB; `Dictionnaire de la langue française du seizième siècle/A1` 1.9 MB; `Jean Chrysostome/Commentaire sur les Psaumes` 1.9 MB; `Thresor de la langue françoise/Nomenclator` 1.8 MB; `Le Système du Monde/Texte entier/Tome 7` 1.8 MB; `George Sand, sa vie et ses œuvres/Texte entier du tome 3` 1.8 MB; `George Sand, sa vie et ses œuvres/3` 1.8 MB; `Le Coran (Traduction de Savary)/Titre` 1.8 MB.

**Red links in TOCs** (missing parts): 1,043 multi-part works have red links, 72,552 in total; median share of the TOC 55%. Most missing: `Grand dictionnaire universel du XIXe siècle` 19902 of 25736; `Dictionnaire de théologie catholique` 5325 of 7312; `Dictionnaire de Trévoux/4e édition, 1743` 5112 of 5152; `Dictionnaire de Trévoux/5e édition, 1752` 5104 of 5231; `Thresor de la langue françoise` 4690 of 8047; `Dictionnaire historique de l’ancien langage françois/1re éd., 1875` 3462 of 3472; `Nouveau dictionnaire de pédagogie et d’instruction primaire` 1517 of 1665; `Nouvelle Biographie générale` 1252 of 1532.

## 8. Colon prefixes with ≥ 100 root pages

None.

## 9. Quality and completeness (PLAN.md 5.4)

**Hard exclusions**: 694 works — no-text 315, no-text-pages 165, index-faulty 109, under-300-bytes 105.

| tier | works | share | texts in them | source |
|---|---|---|---|---|
| validated | 11,255 | 28.0 % | 46,300 | page-levels 11,130, textquality 115, index-progress 10 |
| proofread | 21,246 | 52.9 % | 288,903 | page-levels 21,138, textquality 97, index-progress 11 |
| unproofread | 7,574 | 18.8 % | 35,836 | page-levels 7,398, textquality 102, index-progress 74 |
| unknown | 120 | 0.3 % | 281 | none 120 |

590 works are proofread by the ≥ 90 % rule. 5,187 of the 7,574 unproofread works have no page above level 1 (raw OCR).

**Incomplete**: 2,740 works — missing-parts 1,200, missing-pages 839, coverage 700, index-progress 1.
Works with missing scan pages inside a transcluded range: 1,086 (76,690 pages).
Coverage was computed for 2,906 works that are their Index's book; 854 cover under 95 % of the Index's text pages.
6 works transclude scan pages of files the dump has no Page: pages for (counted as no signal, not as missing).
Wikisource's own translations (labelled, kept): 0.

Text-quality template values: `100%` 98,460, `Textes validés` 52,451, `75%` 18,054, `50%` 3,193, `25%` 2,661, `00%` 5, `textes validés` 2, `Relu et corrigé` 1.
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
- `Fables (La Fontaine) Barbin & Thierry` (virtual, 1,569 B): Privilege 1
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

**Default view** (validated or proofread, complete, not reference): 30,793 works with 120,350 texts; with unknown works shown: 30,904 works (120,402 texts). Works in the default view whose pages are all level 1: 0. Current setting: `show_unknown_by_default = None`.

## 10. Parts table size (WS§5.2)

| parts_cap | works with catalog parts | parts rows | parts table, gzipped | parts_fts, gzipped |
|---|---|---|---|---|
| 500 | 11,547 | 239,636 | 14.28 MB | 2.31 MB |
| 2,000 | 11,572 | 263,024 | 15.43 MB | 2.50 MB |
| ∞ (all, reference works included) | 11,764 | 494,535 | 24.89 MB | 4.66 MB |
| 2,000, compact encoding | 11,572 | 263,024 | 13.48 MB | 2.50 MB |

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

1. `Théâtre (Flaubert)`: linked, collection, 24 parts, 0 missing, order scan, proofread
    - toc: Théâtre (Flaubert)
    - text: Personnages (`Le Candidat (éd. L. Conard, 1910)/Personnages`) [container]
    - chapter: Acte I (`Le Candidat (éd. L. Conard, 1910)/Acte I`) [container]
    - chapter: Acte II (`Le Candidat (éd. L. Conard, 1910)/Acte II`) [container]
    - chapter: Acte III (`Le Candidat (éd. L. Conard, 1910)/Acte III`) [container]
    - chapter: Acte IV (`Le Candidat (éd. L. Conard, 1910)/Acte IV`) [container]
    - text: Premier Tableau (`Le Château des cœurs/Premier Tableau`) [container]
    - text: Deuxième Tableau (`Le Château des cœurs/Deuxième Tableau`) [container]
    - text: Troisième Tableau (`Le Château des cœurs/Troisième Tableau`) [container]
    - text: Quatrième Tableau (`Le Château des cœurs/Quatrième Tableau`) [container]
    - text: Cinquième Tableau (`Le Château des cœurs/Cinquième Tableau`) [container]
    - text: Sixième Tableau (`Le Château des cœurs/Sixième Tableau`) [container]
    - … 13 more
1. `Rose et Ninette, Le trésor d’Arlatan, La Fédor, …`: linked, book, 10 parts, 0 missing, order toc, proofread
    - toc: Rose et Ninette, Le trésor d’Arlatan, La Fédor, …
    - text: Rose et Ninette [container]
    - text: Le Trésor d’Arlatan (`Le trésor d’Arlatan`) [container]
    - text: La Fédor [container]
    - text: Au Fort Montrouge [container]
    - text: À la Salpêtrière [container]
    - text: Souvenir d’un Chef de Cabinet [container]
    - text: La Leçon d’Histoire [container]
    - text: Les Sanguinaires [container]
    - text: Le Brise-Cailloux [container]
    - text: La Fête des Toits [container]
1. `Revue du Pays de Caux N°5 Novembre 1903`: linked, periodical-issue, 5 parts, 0 missing, order toc, validated
    - toc: Revue du Pays de Caux N°5 Novembre 1903
    - text: Ce qui se passe dans le monde. (`Revue du Pays de Caux N°5 novembre 1903/I`) [container]
    - text: La France et l’Italie. (`Revue du Pays de Caux N°5 novembre 1903/II`) [container]
    - text: La richesse italienne. (`Revue du Pays de Caux N°5 novembre 1903/III`) [container]
    - text: L’Union latine. (`Revue du Pays de Caux N°5 novembre 1903/IV`) [container]
    - text: Changements prochains. (`Revue du Pays de Caux N°5 novembre 1903/V`) [container]
1. `La Fille d’Aristide`: linked, book, 8 parts, 0 missing, order toc, validated
    - toc: La Fille d’Aristide
    - text: À sa sacrée majesté royale et impériale l'impératrice reine de Hongrie et de Bohème (`La Fille d'Aristide/Épître`) [container]
    - text: Acteurs (`La Fille d'Aristide/Acteurs`) [container]
    - chapter: Acte premier (`La Fille d'Aristide/Acte I`) [container]
    - chapter: Acte II (`La Fille d'Aristide/Acte II`) [container]
    - chapter: Acte III (`La Fille d'Aristide/Acte III`) [container]
    - chapter: Acte IV (`La Fille d'Aristide/Acte IV`) [container]
    - chapter: Acte V (`La Fille d'Aristide/Acte V`) [container]
    - text: Approbation (`La Fille d'Aristide/Approbation`) [container]
1. `Dictionnaire des expressions vicieuses usitées dans un grand nombre de départements et notamment dans la ci-devant Province de Lorraine`: linked, reference, 25 parts, 0 missing, order toc, proofread
    - toc: Dictionnaire des expressions vicieuses usitées dans un grand nombre de départements et notamment dans la ci-devant Province de Lorraine
    - text: Avertissement (`Dictionnaire des expressions vicieuses/Avertissement`) [container]
    - text: A (`Dictionnaire des expressions vicieuses/A`) [container]
    - text: B (`Dictionnaire des expressions vicieuses/B`) [container]
    - chapter: C (`Dictionnaire des expressions vicieuses/C`) [container]
    - chapter: D (`Dictionnaire des expressions vicieuses/D`) [container]
    - text: E (`Dictionnaire des expressions vicieuses/E`) [container]
    - text: F (`Dictionnaire des expressions vicieuses/F`) [container]
    - text: G (`Dictionnaire des expressions vicieuses/G`) [container]
    - text: H (`Dictionnaire des expressions vicieuses/H`) [container]
    - chapter: I (`Dictionnaire des expressions vicieuses/I`) [container]
    - text: J (`Dictionnaire des expressions vicieuses/J`) [container]
    - … 14 more
1. `Souvenirs autobiographiques du Mangeur d’opium`: linked, book, 10 parts, 0 missing, order toc, proofread
    - toc: Souvenirs autobiographiques du Mangeur d’opium
    - text: La Jeunesse de Quincey (`Souvenirs autobiographiques du mangeur d’opium/La Jeunesse de Quincey`) [container]
    - text: Jours d’enfance (`Souvenirs autobiographiques du mangeur d’opium/Jours d’enfance`) [container]
    - text: Londres (`Souvenirs autobiographiques du mangeur d’opium/Londres`) [container]
    - text: L’Irlande (`Souvenirs autobiographiques du mangeur d’opium/L’Irlande`) [container]
    - text: L’Insurrection irlandaise (`Souvenirs autobiographiques du mangeur d’opium/L’Insurrection irlandaise`) [container]
    - text: Virilité prématurée (`Souvenirs autobiographiques du mangeur d’opium/Virilité prématurée`) [container]
    - text: Les Voyages (`Souvenirs autobiographiques du mangeur d’opium/Les Voyages`) [container]
    - text: Mon frère (`Souvenirs autobiographiques du mangeur d’opium/Mon frère`) [container]
    - text: Oxford : Les collèges (`Souvenirs autobiographiques du mangeur d’opium/Oxford : Les collèges`) [container]
    - text: Oxford : Le doyen. — La vie des collèges (`Souvenirs autobiographiques du mangeur d’opium/Oxford : Le doyen`) [container]
1. `Œuvres complètes de Joachim du Bellay - Tome 1`: linked, collection, 1 parts, 0 missing, order single, unproofread
    - front: Œuvres complètes de Joachim du Bellay - Tome 1
    - text: Du Bellay - Œuvres complètes, édition Séché, tome 1 [container]
1. `Rutebeuf - Œuvres complètes`: linked, collection, 64 parts, 1 missing, order toc, proofread
    - toc: Rutebeuf - Œuvres complètes
    - chapter: Préface (`Rutebeuf - Œuvres complètes, 1839/Préface`) [container]
    - text: C’est de la povretei Rutebuef (`Rutebeuf - Œuvres complètes, 1839/C’est de la povretei Rutebuef`) [container]
    - text: Le mariage Rustebeuf (`Rutebeuf - Œuvres complètes, 1839/Le mariage Rustebeuf`) [container]
    - text: La complainte Rutebeuf (`Rutebeuf - Œuvres complètes, 1839/La complainte Rutebeuf`) [container]
    - text: C’est la paiz de Rutebués (`Rutebeuf - Œuvres complètes, 1839/C’est la paiz de Rutebués`) [container]
    - text: De la griesche d’yver (`Rutebeuf - Œuvres complètes, 1839/De la griesche d’yver`) [container]
    - text: La griesche d’esté (`Rutebeuf - Œuvres complètes, 1839/La griesche d’esté`) [container]
    - text: La mort Rustebeuf (`Rutebeuf - Œuvres complètes, 1839/La mort Rustebeuf`) [container]
    - text: C’est la complainte au roi de Navarre (`Rutebeuf - Œuvres complètes, 1839/C’est la complainte au roi de Navarre`) [container]
    - text: Ci encoumence la complainte dou conte de Poitiers (`Rutebeuf - Œuvres complètes, 1839/Ci encoumence la complainte dou conte de Poitiers`) [container]
    - text: Ci encoumence la complainte ou conte Huede de Nevers (`Rutebeuf - Œuvres complètes, 1839/Ci encoumence la complainte ou conte Huede de Nevers`) [container]
    - … 53 more
1. `L’héritage`: linked, book, 9 parts, 0 missing, order toc, validated
    - toc: L’héritage
    - text: L’Héritage (`L’Héritage/01`) [container]
    - text: Nocturne (`L’Héritage/02`) [container]
    - text: L’Immortel (`L’Héritage/03`) [container]
    - text: L’Amant de Vénus (`L’Héritage/04`) [container]
    - text: La Sentinelle (`L’Héritage/05`) [container]
    - text: L’Étranger (`L’Héritage/06`) [container]
    - text: Le Sacrilège (`L’Héritage/07`) [container]
    - text: Le Bonheur (`L’Héritage/08`) [container]
    - text: Sept jours (`L’Héritage/09`) [container]
1. `À travers chants`: linked, collection, 30 parts, 0 missing, order toc, proofread
    - toc: À travers chants
    - text: Musique (`Musique (Berlioz)`) [container]
    - text: Étude critique des symphonies de Beethoven [container]
    - text: Quelques mots sur les trios et les sonates de Beethoven [container]
    - text: Fidelio, opéra en trois actes de Beethoven ; sa représentation au Théâtre-Lyrique [container]
    - text: Beethoven dans l’anneau de Saturne, les médiums [container]
    - text: Les appointements des chanteurs [container]
    - text: Sur l’état actuel de l’art du chant dans les théâtres lyriques de France et d’Italie [container]
    - text: Les mauvais chanteurs, les bons chanteurs, le public, les claqueurs [container]
    - text: L’Orphée de Gluck, au Théâtre-Lyrique [container]
    - text: Lignes écrites quelque temps après la première représentation d’Orphée [container]
    - text: L’Alceste d’Euripide, celles de Quinault et de Calsabigi ; les partitions de Lulli, de Gluck, de Schweizer, de Guglielmi et de Handel sur ce sujet [container]
    - … 19 more
1. `La Femme du capitaine Aubépin`: linked, book, 5 parts, 0 missing, order scan, unproofread
    - toc: La Femme du capitaine Aubépin
    - chapter: 1 (`La Femme du capitaine Aubepin/1`) [container]
    - chapter: 2 (`La Femme du capitaine Aubepin/2`) [container]
    - chapter: 3 (`La Femme du capitaine Aubepin/3`) [container]
    - chapter: 4 (`La Femme du capitaine Aubepin/4`) [container]
    - chapter: 22 (`La Femme du capitaine Aubepin/22`) [container]
1. `Théétète (trad. Chambry)`: linked, book, 3 parts, 0 missing, order scan, unproofread
    - front: Théétète (trad. Chambry)
    - text: Notice sur la vie et les œuvres de Platon [container]
    - text: Notice sur le Théétète [container]
    - text: Parménide (trad. Chambry) [container]
1. `Le Monde enchanté`: linked, collection, 13 parts, 0 missing, order toc, proofread
    - toc: Le Monde enchanté
    - text: Histoire des fées (`Le monde enchanté/Histoire des fées`) [container]
    - chapter: I. — : La Belle au Bois dormant (`Le monde enchanté/La Belle au Bois dormant`) [container]
    - chapter: II. — : Cendrillon (`Le monde enchanté/Cendrillon`) [container]
    - chapter: III. — : Le Petit-Poucet (`Le monde enchanté/Le Petit-Poucet`) [container]
    - chapter: IV. — : L’Adroite Princesse (`Le monde enchanté/L’Adroite Princesse`) [container]
    - chapter: V. — : L’Oiseau Bleu (`Le monde enchanté/L’Oiseau Bleu`) [container]
    - chapter: VI. — : La Biche au Bois (`Le monde enchanté/La Biche au Bois`) [container]
    - chapter: VII. — : La Chatte Blanche (`Le monde enchanté/La Chatte Blanche`) [container]
    - chapter: VIII. — : Fleur d’Épine (`Le monde enchanté/Fleur d’Épine`) [container]
    - chapter: IX. — : La Bonne Femme (`Le monde enchanté/La Bonne Femme`) [container]
    - chapter: X. — : Cadichon (`Le monde enchanté/Cadichon`) [container]
    - … 2 more
1. `Romanische Studien Volume 5 (1880)`: linked, periodical-issue, 12 parts, 0 missing, order scan, proofread
    - toc: Romanische Studien Volume 5 (1880)
    - text: Girart de Roussillon (Manuscrit d’Oxford) [container]
    - text: Girart de Roussillon (Manuscrit d’Oxford) - 1 [container]
    - text: Girart de Roussillon (Manuscrit d’Oxford) - 2 [container]
    - text: Girart de Roussillon (Manuscrit d’Oxford) - 3 [container]
    - text: Girart de Roussillon (Manuscrit d’Oxford) - 4 [container]
    - text: Girart de Roussillon (Manuscrit d’Oxford) - 5 [container]
    - text: Girart de Roussillon (Manuscrit d’Oxford) - 6 [container]
    - text: Girart de Roussillon (Manuscrit d’Oxford) - 7 [container]
    - text: Girart de Roussillon (Manuscrit d’Oxford) - 8 [container]
    - text: Girart de Roussillon (Manuscrit d’Oxford) - 9 [container]
    - text: Girart de Roussillon (Manuscrit d’Oxford) - 10 [container]
    - … 1 more
1. `Lois des Francs`: linked, book, 75 parts, 0 missing, order toc, proofread
    - toc: Lois des Francs
    - text: Des assignations (`Loi salique/I. - Des assignations`) [container]
    - text: Du vol des porcs (`Loi salique/II. - Du vol des porcs`) [container]
    - text: Du vol des bestiaux (`Loi salique/III. - Du vol des bestiaux`) [container]
    - text: Du vol des brebis (`Loi salique/IV. - Du vol des brebis`) [container]
    - text: Du vol des chèvres (`Loi salique/V. - Du vol des chèvres`) [container]
    - text: Du vol des chiens (`Loi salique/VI. - Du vol des chiens`) [container]
    - text: Du vol des oiseaux (`Loi salique/VII. - Du vol des oiseaux`) [container]
    - text: Du vol des arbres (`Loi salique/VIII. - Du vol des arbres`) [container]
    - text: Du vol des abeilles (`Loi salique/IX. - Du vol des abeilles`) [container]
    - text: Du dommage causé dans un champ de blé, ou dans un enclos quelconque (`Loi salique/X. - Du dommage causé dans un champ de blé, ou dans un enclos quelconque`) [container]
    - text: De l’enlèvement des serviteurs ou esclaves (`Loi salique/XI. De l’enlèvement des serviteurs ou esclaves`) [container]
    - … 64 more
1. `Voyages aux pays du cœur`: linked, collection, 39 parts, 0 missing, order toc, validated
    - toc: Voyages aux pays du cœur
    - text: Bohême [container]
    - text: La Vieille romance [container]
    - text: L’éclat de rire d’un Bohême (`L’Éclat de rire d’un Bohême`) [container]
    - text: Comment je mourrai [container]
    - text: Me tuer ? — Allons donc ! [container]
    - text: Ce qu’on voit dans les yeux d’une maîtresse [container]
    - text: Où est-il ? [container]
    - text: À Claudia Bachi [container]
    - text: Le chant du printemps (`Le Chant du printemps`) [container]
    - text: Ma fortune [container]
    - text: Hans Wald [container]
    - … 28 more
1. `Recueil des historiens des Gaules et de la France`: linked, collection, 3 parts, 1 missing, order toc, proofread
    - front: Recueil des historiens des Gaules et de la France
    - text: Prospectus d’une nouvelle édition du Recueil des historiens des Gaules et de la France [container]
    - chapter: Préface sur la nouvelle collection des historiens de France [container]
    - text: Privilège du Roy (`Privilege du Roy pour le Recueil des historiens des Gaules et de la France (Tome 1)`) [container]
1. `Chants hellènes`: linked, book, 3 parts, 0 missing, order toc, validated
    - toc: Chants hellènes
    - text: Avertissement (`Chants hellènes - Avertissement`) [container]
    - chapter: Chant premier : Byron (`Byron (Guiraud)`) [container]
    - chapter: Chant second : Ipsara (`Ipsara`) [container]
1. `Les Derniers Marquis — Deux mois aux Pyrénées`: linked, book, 3 parts, 0 missing, order toc, validated
    - toc: Les Derniers Marquis — Deux mois aux Pyrénées
    - chapter: Préface (`Les Derniers Marquis/Préface`) [container]
    - text: Les Derniers Marquis [container]
    - text: Deux mois aux Pyrénées [container]
1. `Orgie soldatesque`: linked, book, 12 parts, 0 missing, order scan, proofread
    - front: Orgie soldatesque
    - chapter: 01 (`La Chandelle de Sixte-Quint (éd. Blanche)/01`) [container]
    - chapter: 02 (`La Chandelle de Sixte-Quint (éd. Blanche)/02`) [container]
    - chapter: 03 (`La Chandelle de Sixte-Quint (éd. Blanche)/03`) [container]
    - chapter: 04 (`La Chandelle de Sixte-Quint (éd. Blanche)/04`) [container]
    - chapter: 05 (`La Chandelle de Sixte-Quint (éd. Blanche)/05`) [container]
    - chapter: 06 (`La Chandelle de Sixte-Quint (éd. Blanche)/06`) [container]
    - chapter: 07 (`La Chandelle de Sixte-Quint (éd. Blanche)/07`) [container]
    - chapter: 08 (`La Chandelle de Sixte-Quint (éd. Blanche)/08`) [container]
    - chapter: 09 (`La Chandelle de Sixte-Quint (éd. Blanche)/09`) [container]
    - chapter: 10 (`La Chandelle de Sixte-Quint (éd. Blanche)/10`) [container]
    - chapter: 11 (`La Chandelle de Sixte-Quint (éd. Blanche)/11`) [container]
    - … 1 more
1. `Fables et légendes du Japon`: linked, book, 13 parts, 0 missing, order toc, proofread
    - toc: Fables et légendes du Japon
    - text: Ourashima Taro et la déesse de l’océan [container]
    - text: La petite Voleuse [container]
    - text: La Vengeance du Lièvre [container]
    - text: Le monstre Yatama [container]
    - text: L’unique parapluie [container]
    - text: Les huit Chevreaux [container]
    - text: Les aventures de Benké [container]
    - text: Le vase de Kompéito [container]
    - text: Les Rats au temple [container]
    - text: Les Fraises de décembre [container]
    - text: Le Moineau sans langue [container]
    - … 2 more
1. `Les Désirs`: linked, collection, 144 parts, 0 missing, order toc, proofread
    - toc: Les Désirs
    - text: Seul et moins jeune… (`Les désirs/Seul et moins jeune…`) [container]
    - text: À peine arrives-tu… (`Les désirs/À peine arrives-tu…`) [container]
    - text: Peu d’images… (`Les désirs/Peu d’images…`) [container]
    - text: Le vent tiède (`Les désirs/Le vent tiède`) [container]
    - text: Sur tes flancs nus… (`Les désirs/Sur tes flancs nus…`) [container]
    - text: Dans la chaude, la trouble rue… (`Les désirs/Dans la chaude, la trouble rue…`) [container]
    - text: Amis, protégez-moi… (`Les désirs/Amis, protégez-moi…`) [container]
    - text: C’est un accent plus doux… (`Les désirs/C’est un accent plus doux…`) [container]
    - text: Que ce vent orageux… (`Les désirs/Que ce vent orageux…`) [container]
    - text: Comme je t’aimais mal (`Les désirs/Comme je t’aimais mal`) [container]
    - text: À la mer (`Les désirs/À la mer`) [container]
    - … 133 more
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
1. `La Vie amoureuse de François Barbazanges`: linked, book, 2 parts, 0 missing, order scan, unproofread
    - toc: La Vie amoureuse de François Barbazanges
    - chapter: 1 (`La Vie amoureuse de Francois Barbazanges/1`) [container]
    - chapter: 2 (`La Vie amoureuse de Francois Barbazanges/2`) [container]
1. `Qu’est-ce que la propriété ?`: linked, book, 6 parts, 0 missing, order scan, validated
    - toc: Qu’est-ce que la propriété ?
    - chapter: Préface (`Qu’est ce que la propriété ?/Préface`) [container]
    - chapter: CHAPITRE PREMIER (`Qu’est ce que la propriété ?/Chapitre 1`) [container]
    - chapter: CHAPITRE II (`Qu’est ce que la propriété ?/Chapitre 2`) [container]
    - chapter: CHAPITRE III (`Qu’est ce que la propriété ?/Chapitre 3`) [container]
    - chapter: CHAPITRE IV (`Qu’est ce que la propriété ?/Chapitre 4`) [container]
    - chapter: Chapitre 5 (`Qu’est ce que la propriété ?/Chapitre 5`) [container]
1. `Les Symboles`: linked, collection, 33 parts, 1 missing, order toc, unproofread
    - toc: Les Symboles
    - text: Les Symboles - Préface [container]
    - chapter: Prologue (Bouchor) [container]
    - text: Le Cycle [container]
    - text: L’Âme heureuse [container]
    - text: Adam et Ève (`Adam et Ève (Bouchor)`) [container]
    - text: Le Peuple de Dieu [container]
    - text: Consolez-vous [container]
    - text: Les Colonnes du Temple [container]
    - text: Istar [container]
    - text: Le Sacrifice (`Le Sacrifice (Bouchor)`) [container]
    - text: Le Chant de Vishnou [container]
    - … 22 more
1. `Cours d'Esthétique (Hegel)`: linked, book, 66 parts, 0 missing, order chain, proofread
    - toc: Cours d'Esthétique (Hegel)
    - chapter: Préface du traducteur (`Cours d’Esthétique (Hegel)/Préface`) [container]
    - text: Définition de l’Esthétique et réfutation de quelques objections contre la philosophie de l’art. (`Cours d’Esthétique (Hegel)/Introduction/I`) [container]
    - text: Méthodes employées dans les recherches scientifiques sur le beau et l’art. (`Cours d’Esthétique (Hegel)/Introduction/II`) [container]
    - text: Idée du beau dans l’art. (`Cours d’Esthétique (Hegel)/Introduction/III`) [container]
    - text: Opinions communes sur l’art. (`Cours d’Esthétique (Hegel)/Introduction/III/A`) [container]
    - text: Développement historique de la véritable idée de l’art. (`Cours d’Esthétique (Hegel)/Introduction/III/B`) [container]
    - text: Division (`Cours d’Esthétique (Hegel)/Introduction/Division`) [container]
    - text: Place de l’Art dans son rapport avec le monde réel et avec la religion et la philosophie. (`Cours d’Esthétique (Hegel)/I/Introduction`) [container]
    - chapter: 1 (`Cours d’Esthétique (Hegel)/I/1`) [container]
    - text: Du beau dans la nature en lui-même. (`Cours d’Esthétique (Hegel)/I/2/1`) [container]
    - text: De la beauté extérieure de la forme abstraite de l’unité. (`Cours d’Esthétique (Hegel)/I/2/2`) [container]
    - … 55 more
1. `Chronique du règne de Charles IX`: linked, book, 1 parts, 0 missing, order single, proofread
    - front: Chronique du règne de Charles IX
    - text: La Double méprise (Mérimée) [container]
1. `Recherches bibliques sur le culte des chrétiens`: linked, book, 10 parts, 0 missing, order toc, validated
    - toc: Recherches bibliques sur le culte des chrétiens
    - chapter: Introduction. (`Recherches bibliques sur le culte des chrétiens.djvu/Introduction`) [container]
    - text: L’Église primitive. (`Recherches bibliques sur le culte des chrétiens.djvu/L’Église primitive`) [container]
    - text: Formation de l’église. (`Recherches bibliques sur le culte des chrétiens.djvu/Formation de l’église`) [container]
    - text: État actuel de l’église. (`Recherches bibliques sur le culte des chrétiens.djvu/État actuel de l’église`) [container]
    - text: Le Culte au milieu de l’église démembrée. (`Recherches bibliques sur le culte des chrétiens.djvu/Le Culte au milieu de l’église démembrée`) [container]
    - text: Promesse I. (`Recherches bibliques sur le culte des chrétiens.djvu/Promesse I`) [container]
    - text: Promesse II. (`Recherches bibliques sur le culte des chrétiens.djvu/Promesse II`) [container]
    - text: Promesse III. (`Recherches bibliques sur le culte des chrétiens.djvu/Promesse III`) [container]
    - chapter: Note A. (`Recherches bibliques sur le culte des chrétiens.djvu/Note A`) [container]
    - chapter: Note B. (`Recherches bibliques sur le culte des chrétiens.djvu/Note B`) [container]
1. `Aux Champs (Paul Harel)`: linked, book, 26 parts, 0 missing, order toc, proofread
    - toc: Aux Champs (Paul Harel)
    - text: Janvier (`Janvier (Paul Harel)`) [container]
    - text: Février (`Février (Paul Harel)`) [container]
    - text: Mars (`Mars (Paul Harel)`) [container]
    - text: Avril (`Avril (Paul Harel)`) [container]
    - text: Mai (`Mai (Paul Harel)`) [container]
    - text: Juin (`Juin (Paul Harel)`) [container]
    - text: Juillet (`Juillet (Paul Harel)`) [container]
    - text: Août (`Août (Paul Harel)`) [container]
    - text: Septembre (`Septembre (Paul Harel)`) [container]
    - text: Octobre (`Octobre (Paul Harel)`) [container]
    - text: Novembre (`Novembre (Paul Harel)`) [container]
    - … 15 more

### B: mixed (413)

1. `Œuvres de Marguerite d’Oingt`: mixed, book, 6 parts, 0 missing, order toc, proofread
    - toc: Œuvres de Marguerite d’Oingt
    - text: La Chartreuse de Poleteins (`Œuvres de Marguerite d’Oingt/La Chartreuse de Poleteins`) [container]
    - text: Sa famille, ses œuvres (`Œuvres de Marguerite d’Oingt/Sa famille, ses œuvres`) [container]
    - chapter: Notes (`Œuvres de Marguerite d’Oingt/Notes`) [container]
    - text: Meditationes (`Œuvres de Marguerite d’Oingt/Meditationes`) [container]
    - text: Speculum (`Speculum (Marguerite d’Oingt)`) [container]
    - text: La via seiti Biatrix [container]
1. `Le Parricide (recueil)`: mixed, collection, 18 parts, 0 missing, order toc, unproofread
    - toc: Le Parricide (recueil)
    - text: Le Parricide (`Le Parricide (recueil)/Le Parricide`) [container]
    - text: Parisina (`Le Parricide (recueil)/Parisina`) [container]
    - text: Les Deux Aveugles [container]
    - text: Éliza [container]
    - text: Adieux d’un suicide [container]
    - text: Le Conte du foyer [container]
    - text: Hommage aux mânes d’André Chénier [container]
    - text: Corinne (`Corinne (Lefèvre-Deumier)`) [container]
    - text: Maria (`Maria (Lefèvre-Deumier)`) [container]
    - text: Les Deux Mélodies [container]
    - text: Lucie (`Lucie (Lefèvre-Deumier)`) [container]
    - … 7 more
1. `Œuvres complètes d’Élisa Mercœur`: mixed, collection, 94 parts, 0 missing, order scan-volumes, unproofread
    - front: Œuvres complètes d’Élisa Mercœur
    - text: À l’Écho (`Œuvres complètes d’Élisa Mercœur/À l’Écho`) [container]
    - chapter: Introduction (`Œuvres complètes d’Élisa Mercœur/Introduction`) [container]
    - text: Mémoires sur la vie d’Élisa Mercœur (`Œuvres complètes d’Élisa Mercœur/Mémoires sur la vie d’Élisa Mercœur`) [container]
    - text: À Elisa Mercœur (`À Elisa Mercœur (O. C. Élisa Mercœur)`) [container]
    - text: À M. de Chateaubriaud (`À M. de Chateaubriaud (O. C. Élisa Mercœur)`) [container]
    - text: Dors, mon Ami (`Dors, mon Ami (O. C. Élisa Mercœur)`) [container]
    - text: Élégie (`Élégie (O. C. Élisa Mercœur)`) [container]
    - text: Ne le dis pas (`Ne le dis pas (O. C. Élisa Mercœur)`) [container]
    - text: Le Réveil d’une Vierge (`Le Réveil d’une Vierge (O. C. Élisa Mercœur)`) [container]
    - text: Le Chant du Barde écossais (`Le Chant du Barde écossais (O. C. Élisa Mercœur)`) [container]
    - text: Le Jeune Mendiant (`Le Jeune Mendiant (O. C. Élisa Mercœur)`) [container]
    - … 83 more
1. `Les derniers Iroquois`: mixed, book, 20 parts, 0 missing, order toc, validated
    - toc: Les derniers Iroquois
    - text: La veuve indienne et ses maris (`Les derniers Iroquois /01`) [container]
    - text: Montréal (`Les derniers Iroquois/02`) [container]
    - text: Les derniers Iroquois (`Les derniers Iroquois/03`) [container]
    - text: L’île au Diable (`Les derniers Iroquois/04`) [container]
    - text: Le Montréalais (`Les derniers Iroquois/05`) [container]
    - text: Léonie de Repentigny (`Les derniers Iroquois/06`) [container]
    - text: Co-lo-mo-o le Petit-Aigle (`Les derniers Iroquois/07`) [container]
    - text: De Montréal à Caughnawagha (`Les derniers Iroquois/08`) [container]
    - text: L’emplumement (`Les derniers Iroquois/09`) [container]
    - text: Évasion et duel (`Les derniers Iroquois/10`) [container]
    - text: Les garnisaires de l’île au Diable (`Les derniers Iroquois/11`) [container]
    - … 9 more
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
1. `Poèmes barbares`: mixed, collection, 81 parts, 0 missing, order toc, proofread
    - toc: Poèmes barbares
    - text: Qaïn [container]
    - text: La Vigne de Naboth [container]
    - text: L’Ecclésiaste (`L’Ecclésiaste (Poèmes barbares)`) [container]
    - text: Néférou-Ra [container]
    - text: Ekhidna [container]
    - text: Le Combat homérique [container]
    - text: La Genèse polynésienne [container]
    - text: La Légende des Nornes [container]
    - text: La Vision de Snorr [container]
    - text: Le Barde de Temrah [container]
    - text: L’Épée d’Angantyr [container]
    - … 70 more
1. `Œuvres de Albert Glatigny`: mixed, collection, 95 parts, 39 missing, order toc, proofread
    - toc: Œuvres de Albert Glatigny
    - text: Albert Glatigny par Anatole France (`Œuvres de Albert Glatigny/Albert Glatigny par Anatole France`) [container]
    - text: À J. Lazare (`Œuvres de Albert Glatigny/À J. Lazare`) [container]
    - volume: LES VIGNES FOLLES (`Les Vignes folles`) [container]
      - text: Aurora (`Les Vignes folles/Aurora`) [hierarchy]
    - text: À Ronsard (`Œuvres de Albert Glatigny/À Ronsard`) [container]
    - chapter: Partie de campagne (`Œuvres de Albert Glatigny/Partie de campagne`) [container]
    - text: Rondel (`Œuvres de Albert Glatigny/Rondel`) [container]
    - text: Nuit d’été (`Œuvres de Albert Glatigny/Nuit d’été`) [container]
    - text: Pour une Comédienne (`Œuvres de Albert Glatigny/Pour une Comédienne`) [container]
    - text: Clotilde (`Œuvres de Albert Glatigny/Clotilde`) [container]
    - text: Distiques galants (`Œuvres de Albert Glatigny/Distiques galants`) [container]
    - … 84 more
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
1. `Œuvres complètes de Paul Verlaine`: mixed, collection, 602 parts, 1 missing, order scan-volumes, proofread
    - front: Œuvres complètes de Paul Verlaine
    - text: POÈMES SATURNIENS (`Œuvres complètes de Paul Verlaine/Poèmes saturniens/entete`) [container]
    - text: « Les Sages d’autrefois, qui valaient bien ceux-ci » (`Poèmes saturniens (1902)/« Les Sages d’autrefois, qui valaient bien ceux-ci »`) [container]
    - text: FÊTES GALANTES (`Œuvres complètes de Paul Verlaine/Fêtes galantes/Entete`) [container]
    - text: LA BONNE CHANSON (`Œuvres complètes de Paul Verlaine/La Bonne Chanson/Entete`) [container]
    - text: ROMANCES SANS PAROLES (`Œuvres complètes de Paul Verlaine/Romances sans paroles/Entete`) [container]
    - text: SAGESSE (`Œuvres complètes de Paul Verlaine/Sagesse/Entete`) [container]
    - text: JADIS ET NAGUÈRE (`Œuvres complètes de Paul Verlaine/Jadis et naguère/Entete`) [container]
    - chapter: Prologue (`Jadis et naguère (1902)/Jadis/Prologue`) [container]
    - text: À la louange de Laure et Pétrarque (`Jadis et naguère (1902)/À la louange de Laure et Pétrarque`) [container]
    - text: Pierrot (`Jadis et naguère (1902)/Pierrot`) [container]
    - text: Kaléidoscope (`Jadis et naguère (1902)/Kaléidoscope`) [container]
    - … 591 more
1. `Caroline et Saint Hilaire, ou Les putains du Palais-Royal`: mixed, book, 7 parts, 0 missing, order scan, proofread
    - toc: Caroline et Saint Hilaire, ou Les putains du Palais-Royal
    - text: Caroline et Saint Hilaire [container]
    - text: Avis du traducteur (`Caroline et Saint Hilaire, ou Les putains du Palais-Royal/00-1`) [container]
    - text: Dédicace (`Caroline et Saint Hilaire, ou Les putains du Palais-Royal/00-2`) [container]
    - text: Avis au peuple fouteur (`Caroline et Saint Hilaire, ou Les putains du Palais-Royal/00-3`) [container]
    - text: Approbation (`Caroline et Saint Hilaire, ou Les putains du Palais-Royal/00-4`) [container]
    - chapter: Première partie (`Caroline et Saint Hilaire, ou Les putains du Palais-Royal/01`) [container]
    - chapter: Seconde partie (`Caroline et Saint Hilaire, ou Les putains du Palais-Royal/02`) [container]
1. `Carnets de voyage, 1897`: mixed, book, 56 parts, 0 missing, order single, validated
    - toc: Carnets de voyage, 1897
    - volume: Carnets de voyage, notes sur la province [container]
      - chapter: Avant-propos (`Carnets de voyage, 1897/Avant-propos`) [container]
      - text: Douai (`Carnets de voyage, 1897/Douai (1863)`) [container]
      - text: Le Mans (`Carnets de voyage, 1897/Le Mans (1863)`) [container]
      - text: La Flèche (`Carnets de voyage, 1897/La Flèche (1863)`) [container]
      - text: Solesmes (`Carnets de voyage, 1897/Solesmes (1863)`) [container]
      - text: Du Mans à Rennes (`Carnets de voyage, 1897/Du Mans à Rennes (1863)`) [container]
      - text: Rennes (`Carnets de voyage, 1897/Rennes (1863)`) [container]
      - text: Rennes — 1863 (`Carnets de voyage, 1897/Musée (1863)`) [container]
      - text: De Rennes au Mans et à Tours (`Carnets de voyage, 1897/De Rennes au Mans et à Tours (1863)`) [container]
      - text: Ruelle (`Carnets de voyage, 1897/Ruelle (1863)`) [container]
    - … 45 more
1. `Notes sur l'histoire de Laghouat`: mixed, book, 8 parts, 0 missing, order scan, proofread
    - front: Notes sur l'histoire de Laghouat
    - text: GÉNÉRALITÉS (`Notes sur l'histoire de Laghouat/Généralités`) [container]
    - chapter: CHAPITRE I (`Notes sur l'histoire de Laghouat/Chapitre I`) [container]
    - chapter: CHAPITRE II (`Notes sur l'histoire de Laghouat/Chapitre II`) [container]
    - chapter: CHAPITRE III (`Notes sur l'histoire de Laghouat/Chapitre III`) [container]
    - chapter: Chapitre IV (`Notes sur l'histoire de Laghouat/Chapitre IV`) [container]
    - chapter: Chapitre V (`Notes sur l'histoire de Laghouat/Chapitre V`) [container]
    - chapter: Chapitre VI (`Notes sur l'histoire de Laghoua/Chapitre VI`) [container]
    - text: APPENDICES (`Notes sur l'histoire de Laghoua/APPENDICES`) [container]
1. `Le Livre des sonnets`: mixed, collection, 167 parts, 0 missing, order scan, proofread
    - toc: Le Livre des sonnets
    - text: Avertissement (`Le Livre des sonnets/Avertissement`) [container]
    - text: Histoire du Sonnet (`Le Livre des sonnets/Histoire du Sonnet`) [container]
    - text: Le Livre des Sonnets (`Le Livre des sonnets/Le Livre des Sonnets`) [container]
    - chapter: Notes et Variantes (`Le Livre des sonnets/Notes et Variantes`) [container]
    - text: Épitaphe d’une jeune fille [container]
    - text: . Les Règles du Sonnet (`Le Livre des sonnets/Appendice`) [container]
    - text: des Auteurs (`Le Livre des sonnets/Index des auteurs`) [container]
    - volume: des Sonnets (`Le Livre des sonnets/Index des sonnets`) [container]
      - text: Accablé de Pareſſe, & de Melancholie (`Le Livre des sonnets/Accablé de Paresse, & de Melancholie`) [container]
      - text: À cette heure où les cœurs, d’amour raſſaſiés (`Le Livre des sonnets/A cette heure où les cœurs, d’amour rassasiés`) [container]
      - text: Afin que ton honneur coule parmy la plaine (`Le Livre des sonnets/Afin que ton honneur coule parmy la plaine`) [container]
    - … 156 more
1. `Fontainebleau (1855, Hachette)`: mixed, book, 6 parts, 2 missing, order scan, unproofread
    - front: Fontainebleau (1855, Hachette)
    - text: À Fernand Desnoyers (Fontainebleau) [container]
    - text: Le Crépuscule du soir (Les Fleurs du mal) (Fontainebleau) [container]
    - text: Le Crépuscule du matin (Fontainebleau) [container]
    - text: Le Crépuscule du soir (Le Spleen de Paris) (Fontainebleau) [container]
    - text: La Solitude (Le Spleen de Paris) (Fontainebleau) [container]
    - text: Fragment d’une lettre écrite de Fontainebleau (`Fontainebleau (1855, Hachette)/Fragment d’une lettre écrite de Fontainebleau`) [container]
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
1. `La Napoléone`: mixed, book, 3 parts, 0 missing, order toc, validated
    - toc: La Napoléone
    - text: Avertissement (`La Napoléone/Avertissement`) [container]
    - text: La Napoléone (`La Napoléone (ode)`) [container]
    - text: La France aux abois [container]
1. `Dictionnaire encyclopédique de la noblesse de France`: mixed, collection, 1,089 parts, 0 missing, order natural, proofread
    - front: Dictionnaire encyclopédique de la noblesse de France
    - chapter: L (`Dictionnaire encyclopédique de la noblesse de France - volume 2 - Nicolas Viton de Saint-Allais/L`) [container]
    - chapter: M (`Dictionnaire encyclopédique de la noblesse de France - volume 2 - Nicolas Viton de Saint-Allais/M`) [container]
    - text: N (`Dictionnaire encyclopédique de la noblesse de France - volume 2 - Nicolas Viton de Saint-Allais/N`) [container]
    - text: O (`Dictionnaire encyclopédique de la noblesse de France - volume 2 - Nicolas Viton de Saint-Allais/O`) [container]
    - text: P (`Dictionnaire encyclopédique de la noblesse de France - volume 2 - Nicolas Viton de Saint-Allais/P`) [container]
    - text: Q (`Dictionnaire encyclopédique de la noblesse de France - volume 2 - Nicolas Viton de Saint-Allais/Q`) [container]
    - text: R (`Dictionnaire encyclopédique de la noblesse de France - volume 2 - Nicolas Viton de Saint-Allais/R`) [container]
    - text: AB INTESTAT (`Dictionnaire encyclopédique de la noblesse de France/Articles/AB INTESTAT`) [container]
    - text: ABAISSE (`Dictionnaire encyclopédique de la noblesse de France/Articles/ABAISSE`) [container]
    - text: ABAISSEMENT ou ABATTEMENT (`Dictionnaire encyclopédique de la noblesse de France/Articles/ABAISSEMENT ou ABATTEMENT`) [container]
    - text: ABEILLE (`Dictionnaire encyclopédique de la noblesse de France/Articles/ABEILLE`) [container]
    - … 1,078 more
1. `Lucrezia Floriani`: mixed, collection, 33 parts, 0 missing, order toc, proofread
    - toc: Lucrezia Floriani
    - text: Notice (`Lucrezia Floriani/Notice`) [container]
    - chapter: Avant-propos (`Lucrezia Floriani/Avant-propos`) [container]
    - chapter: I. (`Lucrezia Floriani/Chapitre 01`) [container]
    - chapter: II. (`Lucrezia Floriani/Chapitre 02`) [container]
    - chapter: III. (`Lucrezia Floriani/Chapitre 03`) [container]
    - chapter: IV. (`Lucrezia Floriani/Chapitre 04`) [container]
    - chapter: V. (`Lucrezia Floriani/Chapitre 05`) [container]
    - chapter: VI. (`Lucrezia Floriani/Chapitre 06`) [container]
    - chapter: VII. (`Lucrezia Floriani/Chapitre 07`) [container]
    - chapter: VIII. (`Lucrezia Floriani/Chapitre 08`) [container]
    - chapter: IX. (`Lucrezia Floriani/Chapitre 09`) [container]
    - … 22 more
1. `Chansons de l’Escalade`: mixed, collection, 28 parts, 0 missing, order scan, proofread
    - toc: Chansons de l’Escalade
    - text: Notice historique sur l’Escalade (`Chansons de l’Escalade/Notice historique sur l’Escalade`) [container]
    - text: Notice sur la fête (`Chansons de l’Escalade/Notice sur la fête`) [container]
    - text: Notice sur les chansons (`Chansons de l’Escalade/Notice sur les chansons`) [container]
    - text: Cé qu’è lainô [container]
    - text: Peuple Genevois (`Chansons de l’Escalade/Peuple Genevois`) [container]
    - text: Sus qu’on chante, Genevois (`Chansons de l’Escalade/Sus qu’on chante, Genevois`) [container]
    - text: Sus Genève, qu’on bénie (`Chansons de l’Escalade/Sus Genève, qu’on bénie`) [container]
    - text: L’an mil six cent et deux (`Chansons de l’Escalade/L’an mil six cent et deux`) [container]
    - text: Ah moi ! pauvre comte Sonas (`Chansons de l’Escalade/Ah moi ! pauvre comte Sonas`) [container]
    - text: À ce beau jour nous est donnée (`Chansons de l’Escalade/À ce beau jour nous est donnée`) [container]
    - text: Un jeune galant villageois (`Chansons de l’Escalade/Un jeune galant villageois`) [container]
    - … 17 more
1. `Théâtre de Calderón`: mixed, collection, 21 parts, 0 missing, order scan-volumes, proofread
    - toc: Théâtre de Calderón
    - volume: Tome I (`Théâtre de Calderón/Tome I`) [hierarchy]
      - text: Notice sur Calderon (`Théâtre de Calderón/Notice sur Calderón`) [container]
      - text: Maison à deux portes, maison difficile à garder [container]
      - text: Le Médecin de son honneur [container]
      - text: La Dévotion à la croix [container]
      - text: L’Alcade de Zalaméa [container]
      - text: De mal en pis [container]
      - text: La vie est un songe [container]
    - volume: Tome II (`Théâtre de Calderón/Tome II`) [hierarchy]
      - text: Le pire n’est pas toujours certain [container]
      - text: Bonheur et malheur du nom [container]
    - … 10 more
1. `Gérard de Nerval, le poète et l’homme`: mixed, book, 4 parts, 8 missing, order toc, unproofread
    - toc: Gérard de Nerval, le poète et l’homme
    - chapter: I. Œuvres inédites, manuscrits, projets (`Gérard de Nerval, le poète et l’homme/Bibliographie des Œuvres complètes/I`) [container]
    - chapter: II. Publications originales et premières éditions collectives (`Gérard de Nerval, le poète et l’homme/Bibliographie des Œuvres complètes/II`) [container]
    - text: « Madame et souveraine » [container]
    - text: Sonnet (Nerval) [container]
1. `Instructions aux domestiques`: mixed, book, 21 parts, 0 missing, order scan, validated
    - toc: Instructions aux domestiques
    - text: Notice (`Instructions aux domestiques/Notice`) [container]
    - text: . (`Instructions aux domestiques/Entete`) [container]
    - text: Instructions aux domestiques (`Instructions aux domestiques/Instructions aux domestiques`) [container]
    - text: Règles concernant tous les domestiques en général (`Instructions aux domestiques/Règles`) [container]
    - text: Instructions au butler (`Instructions aux domestiques/I`) [container]
    - text: Instructions à la Cuisinière (`Instructions aux domestiques/II`) [container]
    - text: Instructions au Laquais (`Instructions aux domestiques/III`) [container]
    - text: Instructions au Cocher (`Instructions aux domestiques/IV`) [container]
    - text: Instructions au Groom (`Instructions aux domestiques/V`) [container]
    - text: Instructions à l’Intendant et au Régisseur (`Instructions aux domestiques/VI`) [container]
    - text: Instructions au Portier (`Instructions aux domestiques/VII`) [container]
    - … 10 more
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
1. `La Tour de Percemont`: mixed, book, 40 parts, 0 missing, order single, proofread
    - toc: La Tour de Percemont
    - volume: Marianne (Sand - CL 1877) [container]
      - chapter: Chapitre I (`La Tour de Percemont/1`) [container]
      - chapter: Chapitre II (`La Tour de Percemont/2`) [container]
      - chapter: Chapitre III (`La Tour de Percemont/3`) [container]
      - chapter: Chapitre IV (`La Tour de Percemont/4`) [container]
      - chapter: Chapitre V (`La Tour de Percemont/5`) [container]
      - chapter: Chapitre VI (`La Tour de Percemont/6`) [container]
      - chapter: Chapitre VII (`La Tour de Percemont/7`) [container]
      - chapter: Chapitre VIII (`La Tour de Percemont/8`) [container]
      - chapter: Chapitre IX (`La Tour de Percemont/9`) [container]
      - chapter: Chapitre X (`La Tour de Percemont/10`) [container]
    - … 29 more
1. `Œuvres de Chapelle et de Bachaumont`: mixed, collection, 66 parts, 0 missing, order toc, proofread
    - toc: Œuvres de Chapelle et de Bachaumont
    - text: Notice (`Œuvres de Chapelle et de Bachaumont/Notice`) [container]
    - text: Voyage de Chapelle et de Bachaumont (`Œuvres de Chapelle et de Bachaumont/Voyage de Chapelle et de Bachaumont`) [container]
    - text: Épigramme en réponse à Despréaux (`Œuvres de Chapelle/Épigramme en réponse à Despréaux`) [container]
    - chapter: Lettre au duc de Nevers (`Œuvres de Chapelle/Lettre au duc de Nevers (« Pour répondre à vos deux »)`) [container]
    - chapter: Lettre deuxième au même (`Œuvres de Chapelle/Lettre au duc de Nevers (« Encore que dans votre lettre ultime »)`) [container]
    - chapter: Lettre troisième (`Œuvres de Chapelle/Lettre au duc de Nevers (« Sur cette mer d’ime au superlatif »)`) [container]
    - chapter: Lettre en vers à M. *** (`Œuvres de Chapelle/Lettre à M. *** pour l’inviter à revenir de la campagne`) [container]
    - chapter: Lettre à M. Moreau (`Œuvres de Chapelle/Lettre à Monsieur Moreau`) [container]
    - text: Description de Saint-Lazare (`Œuvres de Chapelle/Description de Saint-Lazare`) [container]
    - text: Sonnet à M. Moreau (`Œuvres de Chapelle/Sonnet contre ses parents`) [container]
    - text: Épigramme contre Ménage (`Œuvres de Chapelle/Épigramme contre Ménage`) [container]
    - … 55 more
1. `Au pied de l’échafaud`: mixed, book, 2 parts, 6 missing, order toc, proofread
    - toc: Au pied de l’échafaud
    - chapter: Préface (`Au pied de l’échafaud/Préface`) [container]
    - chapter: Lettre de Tolstoï à Korolenko [container]
1. `Œuvres complètes de Tacite`: mixed, collection, 23 parts, 0 missing, order toc, proofread
    - toc: Œuvres complètes de Tacite
    - chapter: Introduction aux Œuvres complètes de Tacite (`Œuvres complètes de Tacite/Introduction`) [container]
    - volume: Annales (`Annales (Tacite)`) [container]
      - chapter: Livre I (`Annales (Tacite)/Livre I`) [container]
      - chapter: Livre II (`Annales (Tacite)/Livre II`) [container]
      - chapter: Livre III (`Annales (Tacite)/Livre III`) [container]
      - chapter: Livre IV (`Annales (Tacite)/Livre IV`) [container]
      - chapter: Livre V (`Annales (Tacite)/Livre V`) [container]
      - chapter: Livre VI (`Annales (Tacite)/Livre VI`) [container]
      - chapter: Livre XI (`Annales (Tacite)/Livre XI`) [container]
      - chapter: Livre XII (`Annales (Tacite)/Livre XII`) [container]
      - chapter: Livre XIII (`Annales (Tacite)/Livre XIII`) [container]
    - … 12 more
1. `Poésies complètes (Le Goffic)`: mixed, collection, 96 parts, 0 missing, order toc, validated
    - toc: Poésies complètes (Le Goffic)
    - chapter: Préface de la première édition (`Poésies complètes (Le Goffic)/Préface`) [container]
    - text: Post-scriptum pour la nouvelle édition (`Poésies complètes (Le Goffic)/Post-scriptum`) [container]
    - text: AMOUR BRETON (`Amour breton`) [container]
    - text: Prélude (`Poésies complètes (Le Goffic)/Prélude`) [container]
    - text: Épithalame (`Poésies complètes (Le Goffic)/Épithalame`) [container]
    - text: Son âge, son pays, son nom (`Poésies complètes (Le Goffic)/Son âge, son pays, son nom`) [container]
    - text: Anne-Marie (`Poésies complètes (Le Goffic)/Anne-Marie`) [container]
    - text: Vos yeux (`Poésies complètes (Le Goffic)/Vos yeux`) [container]
    - text: Triolets à ma mie (`Poésies complètes (Le Goffic)/Triolets à ma mie`) [container]
    - text: Bretonne de Paris (`Poésies complètes (Le Goffic)/Bretonne de Paris`) [container]
    - text: Vision (`Poésies complètes (Le Goffic)/Vision`) [container]
    - … 85 more
1. `La Chanson française du XVe au XXe siècle`: mixed, collection, 214 parts, 0 missing, order toc, proofread
    - toc: La Chanson française du XVe au XXe siècle
    - chapter: Préface (`La Chanson française du XVe au XXe siècle/Préface`) [container]
    - text: XV siècle : Notice (`La Chanson française du XVe au XXe siècle/XVe siècle/Notice`) [container]
    - text: L’amour de moi s’y est enclose (`La Chanson française du XVe au XXe siècle/L’amour de moi s’y est enclose`) [container]
    - text: Je m’y levai par un matin [container]
    - text: Tant l’ai cherchée [container]
    - text: Gentils galants de France [container]
    - text: Gentil duc de Lorraine [container]
    - text: Réveillez-vous, Picards et Bourguignons [container]
    - text: Vrai Dieu, qui m’y confortera [container]
    - text: Laissez jouer jeunes gens [container]
    - text: Mon mari m’a diffamée [container]
    - … 203 more
1. `Œuvres complètes de Sénèque le Philosophe`: mixed, collection, 36 parts, 0 missing, order scan-volumes, proofread
    - toc: Œuvres complètes de Sénèque le Philosophe
    - text: Sénèque - Œuvres complètes, trad. Baillard, tome I [container]
    - volume: Téléchargement du livre numérique. (`Œuvres complètes de Sénèque le Philosophe/Liseuse`) [container]
      - text: De la Colère (`De la Colère (trad. Baillard)`) [container]
      - text: Consolation à Marcia (`Consolation à Marcia (trad. Baillard)`) [container]
      - text: Petites Pièces de vers (`Petites pièces de vers`) [container]
      - text: Consolation à Helvia (`Consolation à Helvia (trad. Baillard)`) [container]
      - text: Consolation à Polybe (`Consolation à Polybe (trad. Baillard)`) [container]
      - text: De la Vie heureuse (`De la Vie heureuse (trad. Baillard)`) [container]
      - text: Du Repos ou de la Retraite du sage (`Du Repos ou de la Retraite du sage (trad. Baillard)`) [container]
      - text: De la Constance du Sage (`De la constance du sage`) [container]
      - text: De la Providence (`De la Providence (trad. Baillard)`) [container]
    - … 25 more

### D: virtual roots (851)

1. `L’Égoïste (Meredith)`: virtual, book, 11 parts, 0 missing, order scan, proofread
    - text: Prélude (`L’Égoïste (Meredith)/Prélude`)
    - chapter: Chapitre 01 (`L’Égoïste (Meredith)/Chapitre 01`) [hierarchy]
    - chapter: Chapitre 02 (`L’Égoïste (Meredith)/Chapitre 02`) [hierarchy]
    - chapter: Chapitre 03 (`L’Égoïste (Meredith)/Chapitre 03`) [hierarchy]
    - chapter: Chapitre 04 (`L’Égoïste (Meredith)/Chapitre 04`) [hierarchy]
    - chapter: Chapitre 05 (`L’Égoïste (Meredith)/Chapitre 05`) [hierarchy]
    - chapter: Chapitre 06 (`L’Égoïste (Meredith)/Chapitre 06`) [hierarchy]
    - chapter: Chapitre 07 (`L’Égoïste (Meredith)/Chapitre 07`) [hierarchy]
    - chapter: Chapitre 08 (`L’Égoïste (Meredith)/Chapitre 08`) [hierarchy]
    - chapter: Chapitre 09 (`L’Égoïste (Meredith)/Chapitre 09`) [hierarchy]
    - chapter: Chapitre 10 (`L’Égoïste (Meredith)/Chapitre 10`) [hierarchy]
1. `Revue Musicale de Lyon 1904-05-04`: virtual, periodical-issue, 6 parts, 0 missing, order scan, validated
    - text: Lieder français (`Revue Musicale de Lyon 1904-05-04/Lieder français`)
    - text: Chronique lyonnaise (`Revue Musicale de Lyon 1904-05-04/Chronique lyonnaise`) [hierarchy]
    - text: Concerts (`Revue Musicale de Lyon 1904-05-04/Concerts`) [hierarchy]
    - text: À travers la presse (`Revue Musicale de Lyon 1904-05-04/À travers la presse`) [hierarchy]
    - text: Bibliographie (`Revue Musicale de Lyon 1904-05-04/Bibliographie`) [hierarchy]
    - text: Nouvelles diverses (`Revue Musicale de Lyon 1904-05-04/Nouvelles diverses`) [hierarchy]
1. `Itinéraire de Bretagne en 1636`: virtual, book, 4 parts, 0 missing, order scan, unproofread
    - chapter: Préface (`Itinéraire de Bretagne en 1636/Préface`)
    - text: De Candé à Rennes, par Châteaubriant et Bain (`Itinéraire de Bretagne en 1636/De Candé à Rennes, par Châteaubriant et Bain`) [hierarchy]
    - text: Rennes (`Itinéraire de Bretagne en 1636/Rennes`) [hierarchy]
    - text: Chemin de Rennes à Dol, par Hédé et Combourg (`Itinéraire de Bretagne en 1636/Chemin de Rennes à Dol, par Hédé et Combourg`) [hierarchy]
1. `Écrivains modernes de la France`: virtual, periodical-issue, 4 parts, 0 missing, order scan-volumes, proofread
    - text: Victor Cousin (`Écrivains modernes de la France/Victor Cousin`)
    - chapter: M. Villemain (`Écrivains modernes de la France/M. Villemain`) [hierarchy]
    - text: Prosper Mérimée (`Écrivains modernes de la France/Prosper Mérimée`) [hierarchy]
    - chapter: M. Edgar Quinet et ses œuvres (`Écrivains modernes de la France/M. Edgar Quinet et ses œuvres`) [hierarchy]
1. `Le Christianisme et l’esclavage`: virtual, book, 2 parts, 0 missing, order scan, proofread
    - chapter: Préface (`Le Christianisme et l’esclavage/Préface`)
    - text: Texte (`Le Christianisme et l’esclavage/Texte`) [hierarchy]
1. `Homélies sur Lazare`: virtual, book, 7 parts, 0 missing, order scan, unproofread
    - text: Première Homélie (`Homélies sur Lazare/Première Homélie`)
    - text: Deuxième Homélie (`Homélies sur Lazare/Deuxième Homélie`) [hierarchy]
    - text: Troisième Homélie (`Homélies sur Lazare/Troisième Homélie`) [hierarchy]
    - text: Quatrième Homélie (`Homélies sur Lazare/Quatrième Homélie`) [hierarchy]
    - text: Cinquième Homélie (`Homélies sur Lazare/Cinquième Homélie`) [hierarchy]
    - text: Sixième Homélie (`Homélies sur Lazare/Sixième Homélie`) [hierarchy]
    - text: Septième Homélie (`Homélies sur Lazare/Septième Homélie`) [hierarchy]
1. `Poèmes civiques`: virtual, book, 2 parts, 0 missing, order scan, proofread
    - text: Les Muses d’État (`Poèmes civiques/Les Muses d’État`)
    - text: La Chasse aux Vaincus (`Poèmes civiques/La Chasse aux Vaincus`) [hierarchy]
1. `Causeries, première série`: virtual, book, 1 parts, 0 missing, order single, proofread
    - text: 1er Avril 1864 (`Causeries, première série/1er Avril 1864`)
1. `Le Bel Edwards`: virtual, book, 4 parts, 0 missing, order scan, unproofread
    - chapter: I (`Le Bel Edwards/I`)
    - chapter: II (`Le Bel Edwards/II`) [hierarchy]
    - chapter: III (`Le Bel Edwards/III`) [hierarchy]
    - chapter: IV (`Le Bel Edwards/IV`) [hierarchy]
1. `Critique d’avant-garde`: virtual, book, 5 parts, 0 missing, order scan, proofread
    - text: Les Peintres impressionnistes (`Critique d’avant-garde/Les Peintres impressionnistes`)
    - text: Claude Monet (`Critique d’avant-garde/Claude Monet`) [hierarchy]
    - text: Renoir (`Critique d’avant-garde/Renoir`) [hierarchy]
    - text: Édouard Manet (`Critique d’avant-garde/Édouard Manet`) [hierarchy]
    - text: L'Art Japonais (`Critique d’avant-garde/L'Art Japonais`) [hierarchy]
1. `Vie de Benjamin Franklin`: virtual, book, 18 parts, 0 missing, order single, validated
    - volume: Volume 1 (`Vie de Benjamin Franklin/Volume 1`)
      - chapter: Préface du traducteur (`Vie de Benjamin Franklin/Volume 1/00`) [container]
      - text: de Benjamin Franklin (`Vie de Benjamin Franklin/Volume 1/01`) [container]
      - text: du Testament de Benjamin Franklin (`Vie de Benjamin Franklin/Volume 1/02`) [container]
      - chapter: 03 (`Vie de Benjamin Franklin/Volume 1/03`) [container]
      - text: les Personnes qui se marient jeunes. À John Alleyne (`Vie de Benjamin Franklin/Volume 1/04`) [container]
      - text: la mort de son frère, John Franklin. À miss Hubbard (`Vie de Benjamin Franklin/Volume 1/05`) [container]
      - text: au Docteur Mather de Boston (`Vie de Benjamin Franklin/Volume 1/06`) [container]
      - text: Sifflet, histoire véritable, adressée, par Franklin, à son Neveu (`Vie de Benjamin Franklin/Volume 1/07`) [container]
      - text: de la Main Gauche, à ceux qui sont chargés d’élever des Enfans (`Vie de Benjamin Franklin/Volume 1/08`) [container]
      - text: belle Jambe et la Jambe difforme (`Vie de Benjamin Franklin/Volume 1/09`) [container]
      - text: d’un essaim d’Éphémères, et soliloque d’un Vieillard. À Madame Brillant (`Vie de Benjamin Franklin/Volume 1/10`) [container]
    - … 6 more
1. `Almageste`: virtual, book, 17 parts, 0 missing, order scan, proofread
    - chapter: Préface (`Almageste/Préface`)
    - chapter: Avant-propos (`Almageste/Livre I/Avant-propos`) [hierarchy]
    - chapter: 01 (`Almageste/Livre I/01`) [hierarchy]
    - chapter: 02 (`Almageste/Livre I/02`) [hierarchy]
    - chapter: 03 (`Almageste/Livre I/03`) [hierarchy]
    - chapter: 04 (`Almageste/Livre I/04`) [hierarchy]
    - chapter: 05 (`Almageste/Livre I/05`) [hierarchy]
    - chapter: 06 (`Almageste/Livre I/06`) [hierarchy]
    - chapter: 07 (`Almageste/Livre I/07`) [hierarchy]
    - chapter: 08 (`Almageste/Livre I/08`) [hierarchy]
    - volume: 09 (`Almageste/Livre I/09`) [hierarchy]
      - chapter: Table des droites (`Almageste/Livre I/09/Table des droites`) [hierarchy]
    - … 5 more
1. `Éléments de la philosophie de Newton`: virtual, collection, 43 parts, 0 missing, order single, proofread
    - volume: Édition Garnier (`Éléments de la philosophie de Newton/Édition Garnier`)
      - text: des éditeurs de Kehl (`Éléments de la philosophie de Newton/Édition Garnier/Avertissement des éditeurs de Kehl`) [hierarchy]
      - text: de Beuchot (`Éléments de la philosophie de Newton/Édition Garnier/Avertissement de Beuchot`) [hierarchy]
      - text: de l’auteur à du Châtelet (`Éléments de la philosophie de Newton/Édition Garnier/Épître dédicatoire`) [hierarchy]
      - text: . I (`Éléments de la philosophie de Newton/Édition Garnier/Partie 1/Chapitre 1`) [hierarchy]
      - text: . II (`Éléments de la philosophie de Newton/Édition Garnier/Partie 1/Chapitre 2`) [hierarchy]
      - text: . III (`Éléments de la philosophie de Newton/Édition Garnier/Partie 1/Chapitre 3`) [hierarchy]
      - text: . IV (`Éléments de la philosophie de Newton/Édition Garnier/Partie 1/Chapitre 4`) [hierarchy]
      - text: . V (`Éléments de la philosophie de Newton/Édition Garnier/Partie 1/Chapitre 5`) [hierarchy]
      - text: . VI (`Éléments de la philosophie de Newton/Édition Garnier/Partie 1/Chapitre 6`) [hierarchy]
      - text: . VII (`Éléments de la philosophie de Newton/Édition Garnier/Partie 1/Chapitre 7`) [hierarchy]
      - text: . VIII (`Éléments de la philosophie de Newton/Édition Garnier/Partie 1/Chapitre 8`) [hierarchy]
    - … 31 more
1. `Souvenirs d’un aveugle : voyage autour du monde`: virtual, book, 14 parts, 0 missing, order scan, proofread
    - chapter: 01 (`Souvenirs d’un aveugle : voyage autour du monde/02/01`)
    - chapter: 02 (`Souvenirs d’un aveugle : voyage autour du monde/02/02`) [hierarchy]
    - chapter: 03 (`Souvenirs d’un aveugle : voyage autour du monde/02/03`) [hierarchy]
    - chapter: 04 (`Souvenirs d’un aveugle : voyage autour du monde/02/04`) [hierarchy]
    - chapter: 05 (`Souvenirs d’un aveugle : voyage autour du monde/02/05`) [hierarchy]
    - chapter: 06 (`Souvenirs d’un aveugle : voyage autour du monde/02/06`) [hierarchy]
    - chapter: 07 (`Souvenirs d’un aveugle : voyage autour du monde/02/07`) [hierarchy]
    - chapter: 08 (`Souvenirs d’un aveugle : voyage autour du monde/02/08`) [hierarchy]
    - chapter: 09 (`Souvenirs d’un aveugle : voyage autour du monde/02/09`) [hierarchy]
    - chapter: 10 (`Souvenirs d’un aveugle : voyage autour du monde/02/10`) [hierarchy]
    - chapter: 27 (`Souvenirs d’un aveugle : voyage autour du monde/02/27`) [hierarchy]
    - chapter: 28 (`Souvenirs d’un aveugle : voyage autour du monde/02/28`) [hierarchy]
    - … 2 more
1. `Les délices du Pais de Liége`: virtual, book, 8 parts, 0 missing, order single, validated
    - volume: Tome I (`Les délices du Pais de Liége/Tome I`)
      - text: Épître dédicatoire (`Les délices du Pais de Liége/Dedicace`) [container]
      - chapter: Préface (`Les délices du Pais de Liége/Preface`) [container]
      - chapter: Table des articles vol 1 (`Les délices du Pais de Liége/Table des articles vol 1`) [container]
      - text: Dée Idée générale du Païs de Liége. (`Les délices du Pais de Liége/Idée générale`) [container]
      - text: Établiſſement de la Réligion chrètienne dans le Païs de Liége. (`Les délices du Pais de Liége/Établissement de la religion`) [container]
      - text: Fondation de la Ville & de l’Égliſe de Liége. (`Les délices du Pais de Liége/Fondation de la ville`) [container]
      - text: Mœurs des Habitans de la Ville de Liége. (`Les délices du Pais de Liége/Mœurs des Habitans`) [container]
1. `Correspondance de H. de Balzac, 1/1830`: virtual, book, 8 parts, 0 missing, order chain, validated
    - chapter: XXXII (`Correspondance de H. de Balzac, 1/1830/XXXII`)
    - chapter: XXXIII (`Correspondance de H. de Balzac, 1/1830/XXXIII`) [hierarchy]
    - chapter: XXXIV (`Correspondance de H. de Balzac, 1/1830/XXXIV`) [hierarchy]
    - chapter: XXXV (`Correspondance de H. de Balzac, 1/1830/XXXV`) [hierarchy]
    - chapter: XXXVI (`Correspondance de H. de Balzac, 1/1830/XXXVI`) [hierarchy]
    - chapter: XXXVII (`Correspondance de H. de Balzac, 1/1830/XXXVII`) [hierarchy]
    - chapter: XXXVIII (`Correspondance de H. de Balzac, 1/1830/XXXVIII`) [hierarchy]
    - chapter: XXXIX (`Correspondance de H. de Balzac, 1/1830/XXXIX`) [hierarchy]
1. `L’École des Beaux-Arts dessinée et racontée par un élève`: virtual, book, 2 parts, 0 missing, order scan, proofread
    - chapter: Avant-propos (`L’École des Beaux-Arts dessinée et racontée par un élève/Avant-propos`)
    - chapter: Livre 1 (`L’École des Beaux-Arts dessinée et racontée par un élève/Livre 1`) [hierarchy]
1. `ADN`: virtual, book, 1 parts, 0 missing, order single, unproofread
    - text: fol. 230v (`ADN/Registre B. 2185/fol. 230v`)
1. `Le Moulin des prés`: virtual, book, 3 parts, 0 missing, order scan, unproofread
    - chapter: I (`Le Moulin des prés/I`)
    - chapter: II (`Le Moulin des prés/II`) [hierarchy]
    - chapter: III (`Le Moulin des prés/III`) [hierarchy]
1. `Écrits philosophiques`: virtual, book, 2 parts, 0 missing, order scan, proofread
    - chapter: Préface du traducteur (`Écrits philosophiques/Préface du traducteur`)
    - text: Avant propos de l’auteur (`Écrits philosophiques/Leçons sur la méthode des études académiques/Avant propos de l’auteur`) [hierarchy]
1. `Œuvres de Spinoza`: virtual, collection, 88 parts, 0 missing, order scan-volumes, unproofread
    - volume: Tome 2 (`Œuvres de Spinoza/Tome 2`)
      - volume: Traité théologico-politique [container]
        - text: Vie de Benoît de Spinoza (`Œuvres de Spinoza/Vie de Benoît de Spinoza`) [container]
        - chapter: Appendice, vie de Spinosa (`Œuvres de Spinoza/Appendice vie de Spinosa`) [container]
        - text: Notice bibliographique (`Œuvres de Spinoza/Notice bibliographique`) [container]
        - chapter: Préface (`Traité théologico-politique/Préface`) [container]
        - text: De la prophétie (`Traité théologico-politique/Chapitre 1`) [container]
        - text: Des prophètes (`Traité théologico-politique/Chapitre 2`) [container]
        - text: De la vocation des Hébreux, et si le don de prophétie leur a été propre (`Traité théologico-politique/Chapitre 3`) [container]
        - text: De la loi divine (`Traité théologico-politique/Chapitre 4`) [container]
        - text: Du véritable objet de l’institution des cérémonies religieuses (`Traité théologico-politique/Chapitre 5`) [container]
        - text: Des miracles (`Traité théologico-politique/Chapitre 6`) [container]
    - … 76 more
1. `Nouvelle Revue Française`: virtual, periodical-issue, 1 parts, 0 missing, order single, unproofread
    - text: Jules Renard : Nos frères farouches, Ragotte (`Nouvelle Revue Française/1909, volume 2/Notes/Jules Renard : Nos frères farouches, Ragotte`)
1. `Vocabulaires des arts et métiers, en ce qui concerne les constructions`: virtual, reference, 2 parts, 0 missing, order scan, proofread
    - text: Maçonnerie (`Vocabulaires des arts et métiers, en ce qui concerne les constructions/Maçonnerie`)
    - text: Vidange (`Vocabulaires des arts et métiers, en ce qui concerne les constructions/Vidange`) [hierarchy]
1. `Galerie des contemporains illustres`: virtual, book, 1 parts, 0 missing, order single, unproofread
    - text: George Sand (`Galerie des contemporains illustres/2/George Sand`)
1. `Poésies choisies de André Chénier`: virtual, collection, 87 parts, 3 missing, order single, proofread
    - volume: Derocquigny, 1907 (`Poésies choisies de André Chénier/Derocquigny, 1907`)
      - volume: BUCOLIQUES. IDYLLES ET FRAGMENTS D’IDYLLES. (`Poésies choisies de André Chénier/Derocquigny, 1907/Bucoliques. Idylles et fragments d’idylles`) [container]
        - text: L’Aveugle (`Poésies choisies de André Chénier/Derocquigny, 1907/L’Aveugle`) [container]
        - text: Le Mendiant (`Poésies choisies de André Chénier/Derocquigny, 1907/Le Mendiant`) [container]
        - text: La Liberté (`Poésies choisies de André Chénier/Derocquigny, 1907/La Liberté`) [container]
        - text: Le Malade (`Poésies choisies de André Chénier/Derocquigny, 1907/Le Malade`) [container]
        - text: Hylas (`Poésies choisies de André Chénier/Derocquigny, 1907/Hylas`) [container]
        - text: La Jeune Tarentine (`Poésies choisies de André Chénier/Derocquigny, 1907/La Jeune Tarentine`) [container]
        - text: Sur un Groupe de Jupiter et d’Europe (`Poésies choisies de André Chénier/Derocquigny, 1907/Sur un Groupe de Jupiter et d’Europe`) [container]
        - text: Pasiphaé (`Poésies choisies de André Chénier/Derocquigny, 1907/Pasiphaé`) [container]
        - text: Pannychis (`Poésies choisies de André Chénier/Derocquigny, 1907/Pannychis`) [container]
        - text: Dryas (`Poésies choisies de André Chénier/Derocquigny, 1907/Dryas`) [container]
    - … 75 more
1. `La Bastille`: virtual, collection, 1 parts, 0 missing, order single, proofread
    - text: Édition Garnier (`La Bastille/Édition Garnier`)
1. `Revue pour les Français Novembre 1907`: virtual, book, 6 parts, 0 missing, order scan, unproofread
    - chapter: I (`Revue pour les Français Novembre 1907/I`)
    - chapter: II (`Revue pour les Français Novembre 1907/II`) [hierarchy]
    - chapter: III (`Revue pour les Français Novembre 1907/III`) [hierarchy]
    - chapter: V (`Revue pour les Français Novembre 1907/V`) [hierarchy]
    - chapter: VI (`Revue pour les Français Novembre 1907/VI`) [hierarchy]
    - chapter: VII (`Revue pour les Français Novembre 1907/VII`) [hierarchy]
1. `Magasin d'Éducation et de Récréation`: virtual, periodical-issue, 1 parts, 0 missing, order single, validated
    - chapter: Tome XIII (`Magasin d'Éducation et de Récréation/Tome XIII`)
1. `Nos maîtres`: virtual, book, 1 parts, 0 missing, order single, unproofread
    - text: Stéphane Mallarmé (`Nos maîtres/Stéphane Mallarmé`)
1. `Correspondance de H. de Balzac, 1/1825`: virtual, book, 1 parts, 0 missing, order single, validated
    - chapter: XXII (`Correspondance de H. de Balzac, 1/1825/XXII`)

### E: subpage texts reached from a disambiguation or versions page (11,365)

1. text “L’Âne et le Jardinier (bilingue)” (`Fables d’Ésope (trad. Chambry, 1927)/L’Âne et le Jardinier (bilingue)`), part 333 of `Fables d’Ésope (trad. Chambry, 1927)` (collection, 725 parts, 722 texts)
1. text “La brebis à tête cornue” (`Chansons populaires de la Basse-Bretagne/La brebis à tête cornue`), part 54 of `Chansons populaires de la Basse-Bretagne` (book, 243 parts, 239 texts)
1. text “Beckford” (`Divagations (1897)/Beckford`), part 21 of `Divagations (1897)` (collection, 56 parts, 56 texts)
1. text “Lever le siège” (`Dictionnaire érotique moderne/Lever le siège`), part 1154 of `Dictionnaire érotique moderne` (book, 1,717 parts, 1,707 texts)
1. chapter “XXXI” (`Œuvres complètes de André Chénier, 1819/Élégie, XXXI`), part 46 of `Œuvres complètes de André Chénier, 1819` (collection, 91 parts, 32 texts)
1. text “À Fontainebleau (ronde)” (`Poèmes incongrus/À Fontainebleau (ronde)`), part 15 of `Poèmes incongrus` (collection, 24 parts, 22 texts)
1. text “Patrie” (`Dictionnaire philosophique/Garnier (1878)/Patrie`), part 457 of `Dictionnaire philosophique/Garnier (1878)` (collection, 637 parts, 628 texts)
1. text “Culbuter une femme” (`Dictionnaire érotique moderne/Culbuter une femme`), part 628 of `Dictionnaire érotique moderne` (book, 1,717 parts, 1,707 texts)
1. chapter “Proposition 15” (`Euclide - Les Œuvres, (trad. Peyrard)/Tome 1/Éléments - Livre 3/Proposition 15`), part 83 of `Euclide - Les Œuvres, (trad. Peyrard)` (book, 438 parts, 30 texts)
1. text “Balancer une femme” (`Dictionnaire érotique moderne/Balancer une femme`), part 240 of `Dictionnaire érotique moderne` (book, 1,717 parts, 1,707 texts)
1. text “Solenniser la Saint-Priape” (`Dictionnaire érotique moderne/Solenniser la Saint-Priape`), part 1590 of `Dictionnaire érotique moderne` (book, 1,717 parts, 1,707 texts)
1. text “Louis XI” (`Œuvres complètes de Béranger/Louis XI`), part 193 of `Œuvres complètes de Béranger` (collection, 327 parts, 320 texts)
1. chapter “2” (`Le Journal des Débats/17-03-1883/2`), part 1 of `Le Journal des Débats` (periodical-issue, 4 parts, 0 texts)
1. text “Le voyage” (`Hélika/Le voyage`), part 2 of `Hélika` (book, 23 parts, 23 texts)
1. text “Arabes” (`Dictionnaire philosophique/Garnier (1878)/Arabes`), part 55 of `Dictionnaire philosophique/Garnier (1878)` (collection, 637 parts, 628 texts)
1. text “Périclès” (`Vies des hommes illustres/Périclès`), part 13 of `Vies des hommes illustres` (book, 67 parts, 67 texts)
1. text “Les Fantômes” (`Trois contes (Moréas)/Les fantômes`), part 2 of `Trois contes (Moréas)` (collection, 3 parts, 3 texts)
1. chapter “PRÉFACE” (`Revue de Paris/tome 1/PRÉFACE`), part 2 of `Revue de Paris` (periodical-issue, 3 parts, 1 texts)
1. text “Le Départ” (`Les Médailles d’argile/Le Départ`), part 61 of `Les Médailles d’argile` (collection, 137 parts, 137 texts)
1. text “Tombeaux (les) de Juillet” (`Œuvres complètes de Béranger/Les Tombeaux de Juillet`), part 298 of `Œuvres complètes de Béranger` (collection, 327 parts, 320 texts)
1. text “Nef” (`Dictionnaire raisonné de l’architecture française du XIe au XVIe siècle/Nef`), part 382 of `Dictionnaire raisonné de l’architecture française du XIe au XVIe siècle` (collection, 530 parts, 520 texts)
1. text “Hermès et le Statuaire” (`Fables d’Ésope (trad. Chambry, 1927)/Hermès et le Statuaire`), part 526 of `Fables d’Ésope (trad. Chambry, 1927)` (collection, 725 parts, 722 texts)
1. text “Réclamer ses gants” (`Dictionnaire érotique moderne/Réclamer ses gants`), part 1518 of `Dictionnaire érotique moderne` (book, 1,717 parts, 1,707 texts)
1. chapter “Chant (le) du Cosaque” (`Œuvres complètes de Béranger/Le Chant du Cosaque`), part 73 of `Œuvres complètes de Béranger` (collection, 327 parts, 320 texts)
1. text “Le Forgeron et son Chien (bilingue)” (`Fables d’Ésope (trad. Chambry, 1927)/Le Forgeron et son Chien (bilingue)`), part 132 of `Fables d’Ésope (trad. Chambry, 1927)` (collection, 725 parts, 722 texts)
1. text “Meiners (W.)” (`La France littéraire, ou Dictionnaire bibliographique/Meiners (W.)`), part 126 of `La France littéraire, ou Dictionnaire bibliographique` (book, 342 parts, 341 texts)
1. text “Le Chasseur” (`Musique des chansons de Béranger/Le Chasseur`), part 331 of `Musique des chansons de Béranger` (collection, 345 parts, 341 texts)
1. chapter “Chapitre IX” (`Pour la patrie : roman du XXè siècle/Chapitre IX`), part 11 of `Pour la patrie : roman du XXè siècle` (book, 36 parts, 0 texts)
1. text “Fêter la Saint-Priape” (`Dictionnaire érotique moderne/Fêter la Saint-Priape`), part 921 of `Dictionnaire érotique moderne` (book, 1,717 parts, 1,707 texts)
1. volume “Doctrine du droit” (`Métaphysique des mœurs (trad. Barni)/Doctrine du droit`), part 0 of `Métaphysique des mœurs (trad. Barni)` (book, 20 parts, 9 texts)

### all searchable subpage texts (chapter titles that the label patterns miss show up here) (345,288)

1. text “Épigraphe” (`Molière, Shakspeare, la Comédie et le Rire/Épigraphe`), part 2 of `Molière, Shakspeare, la Comédie et le Rire` (book, 26 parts, 12 texts)
1. text “Les Joueurs & le Chasseur” (`Proverbes dramatiques/Le Chasseur et les Joueurs`), part 24 of `Proverbes dramatiques` (collection, 105 parts, 104 texts)
1. text “Le Travail du style” (`Le Travail du Style/Le Travail du style`), part 1 of `Le Travail du Style` (book, 3 parts, 3 texts)
1. text “Histoire générale de France” (`Littérature et philosophie mêlées/Appendice I/1821/Histoire générale de France`), part 15 of `Littérature et philosophie mêlées` (collection, 16 parts, 13 texts)
1. text “INFATUATION” (`Petit dictionnaire de la langue française (1860)/INFATUATION`), part 10763 of `Petit dictionnaire de la langue française (1860)` (reference, 20,406 parts, 20,364 texts)
1. text “Tranchant” (`Dictionnaire des patois romans de la Moselle/Articles/Tranchant`), part 20150 of `Dictionnaire des patois romans de la Moselle` (reference, 21,247 parts, 21,230 texts)
1. text “Pînson” (`Dictionnaire des patois romans de la Moselle/Articles/Pînson`), part 16106 of `Dictionnaire des patois romans de la Moselle` (reference, 21,247 parts, 21,230 texts)
1. text “Sur la mer” (`Poèmes et Poésies (Keats, trad. Gallimard)/Sur la mer`), part 7 of `Poèmes et Poésies (Keats, trad. Gallimard)` (collection, 18 parts, 18 texts)
1. text “Où il est prouvé que dans les positions les plus difficiles les grands cœurs ne perdent jamais le courage, ni les bons estomacs l’appétit” (`Vingt ans après/Chapitre 63`), part 64 of `Vingt ans après` (book, 95 parts, 92 texts)
1. text “Fascicule Moine - Monothéisme” (`Encyclopédie anarchiste/Moine - Monothéisme`), part 155 of `Encyclopédie anarchiste` (book, 276 parts, 251 texts)
1. text “Feurgueuyeu” (`Dictionnaire des patois romans de la Moselle/Articles/Feurgueuyeu`), part 9454 of `Dictionnaire des patois romans de la Moselle` (reference, 21,247 parts, 21,230 texts)
1. text “Témérité” (`Petit dictionnaire de la langue française (Hocquart, 1831)/Articles/Témérité`), part 5470 of `Petit dictionnaire de la langue française (Hocquart, 1831)` (reference, 7,352 parts, 7,333 texts)
1. text “1749 — Octobre” (`Chronique de la régence et du règne de Louis XV, (Charpentier), 1858/1749/Octobre`), part 368 of `Chronique de la régence et du règne de Louis XV, (Charpentier), 1858` (book, 554 parts, 496 texts)
1. text “Publicité dans les Salons” (`Contes de Pantruche et d’ailleurs/Publicité dans les Salons`), part 16 of `Contes de Pantruche et d’ailleurs` (book, 27 parts, 27 texts)
1. text “BINARD” (`L’Encyclopédie/1re édition/BINARD`), part 1330 of `L’Encyclopédie` (reference, 41,677 parts, 41,610 texts)
1. text “Cimetière du Nord” (`Guide dans les cimetières de Paris/Nord`), part 6 of `Guide dans les cimetières de Paris` (book, 14 parts, 12 texts)
1. text “Le Formalisme et la Vraie Foi” (`Le Vrai Livre des femmes/15`), part 16 of `Le Vrai Livre des femmes` (book, 24 parts, 23 texts)
1. text “phallorrhagie s. f.” (`Grand dictionnaire universel du XIXe siècle/phallorrhagie s. f.`), part 4352 of `Grand dictionnaire universel du XIXe siècle` (reference, 5,834 parts, 5,829 texts)
1. text “Le mariage” (`Dent pour dent/18`), part 18 of `Dent pour dent` (book, 20 parts, 20 texts)
1. text “Je te tiens” (`Les Enfantines du bon pays de France/Je te tiens`), part 84 of `Les Enfantines du bon pays de France` (collection, 347 parts, 343 texts)
1. text “L’Âge futur” (`Musique des chansons de Béranger/L’Âge futur`), part 23 of `Musique des chansons de Béranger` (collection, 345 parts, 341 texts)
1. text “CYTISUS” (`Dictionnaire de Trévoux/6e édition, 1771/CYTISUS`), part 17840 of `Dictionnaire de Trévoux/6e édition, 1771` (reference, 31,513 parts, 31,473 texts)
1. text “Déplanquer” (`Dictionnaire Argot-Français/Articles/Déplanquer`), part 476 of `Dictionnaire Argot-Français` (reference, 1,367 parts, 1,308 texts)
1. text “Le Rossignol aveugle” (`Les Pleurs/Le Rossignol aveugle`), part 36 of `Les Pleurs` (collection, 67 parts, 66 texts)
1. text “Minuit” (`Les Forces éternelles/Minuit`), part 102 of `Les Forces éternelles` (book, 197 parts, 193 texts)
1. text “SCAMACHIE” (`L’Encyclopédie/1re édition/SCAMACHIE`), part 32587 of `L’Encyclopédie` (reference, 41,677 parts, 41,610 texts)
1. text “Agricola 4” (`Dictionnaire historique et critique/11e éd., 1820/Agricola 4`), part 86 of `Dictionnaire historique et critique/11e éd., 1820` (reference, 1,019 parts, 1,015 texts)
1. text “Deiphobe” (`Dictionnaire infernal/6e éd., 1863/Deiphobe`), part 1252 of `Dictionnaire infernal/6e éd., 1863` (reference, 3,826 parts, 3,795 texts)
1. text “Il n’est pas d’amour qui résiste à l’absence” (`La Rôtisserie de la reine Pédauque/Épilogue`), part 25 of `La Rôtisserie de la reine Pédauque` (book, 25 parts, 24 texts)
1. text “CARDONERO” (`L’Encyclopédie/1re édition/CARDONERO`), part 3646 of `L’Encyclopédie` (reference, 41,677 parts, 41,610 texts)

### C: oversized (602)

1. `Œuvres complètes (Crémazie)`: subpages, collection, 76 parts, 0 missing, order toc, proofread
    - toc: Œuvres complètes (Crémazie)
    - text: Avertissement (`Œuvres complètes (Crémazie)/Avertissement`) [container]
    - text: (notice biographique) (`Œuvres complètes (Crémazie)/Octave Crémazie (notice biographique)`) [container]
    - chapter: Lettre à M. l’abbé Casgrain (2 avril 1864) (`Œuvres complètes (Crémazie)/Lettre à M. l’abbé Casgrain (2 avril 1864)`) [container]
    - chapter: Lettre à M. l’abbé Casgrain (1866) (`Œuvres complètes (Crémazie)/Lettre à M. l’abbé Casgrain (1866)`) [container]
    - chapter: Lettre à M. l’abbé Casgrain (10 août 1866) (`Œuvres complètes (Crémazie)/Lettre à M. l’abbé Casgrain (10 août 1866)`) [container]
    - chapter: Lettre à M. l’abbé Casgrain (29 janvier 1867) (`Œuvres complètes (Crémazie)/Lettre à M. l’abbé Casgrain (29 janvier 1867)`) [container]
    - chapter: Lettre à M. l’abbé Casgrain (15 décembre 1867) (`Œuvres complètes (Crémazie)/Lettre à M. l’abbé Casgrain (15 décembre 1867)`) [container]
    - chapter: Lettre à M. l’abbé Casgrain (20 octobre 1869) (`Œuvres complètes (Crémazie)/Lettre à M. l’abbé Casgrain (20 octobre 1869)`) [container]
    - chapter: Lettre à M. l’abbé Casgrain (1 mai 1870) (`Œuvres complètes (Crémazie)/Lettre à M. l’abbé Casgrain (1er mai 1870)`) [container]
    - chapter: Lettre à M. l’abbé Casgrain (Bordeaux, 29 avril 1876) (`Œuvres complètes (Crémazie)/Lettre à M. l’abbé Casgrain (Bordeaux, 29 avril 1876)`) [container]
    - chapter: Lettre à M. l’abbé Casgrain (Citry, 18 février 1877) (`Œuvres complètes (Crémazie)/Lettre à M. l’abbé Casgrain (Citry, 18 février 1877)`) [container]
    - … 65 more
1. `Œuvres posthumes (Verlaine)`: mixed, collection, 199 parts, 0 missing, order scan-volumes, proofread
    - toc: Œuvres posthumes (Verlaine)
    - text: Œuvres posthumes, premier volume (`Œuvres posthumes (Verlaine)/Texte entier/Volume 1`) [container]
    - text: VERS DE JEUNESSE (`Œuvres posthumes (Verlaine)/Vers de jeunesse`) [container]
    - text: VARIA (`Œuvres posthumes (Verlaine)/Varia`) [container]
    - text: En 17 [container]
    - text: Éventail directoire [container]
    - text: Rotterdam [container]
    - text: Mort (Verlaine, posthume) [container]
    - chapter: Épilogue (Verlaine, posthume) [container]
    - text: PARALLÈLEMENT (`Œuvres posthumes (Verlaine)/Parallèlement`) [container]
    - text: Projet en l’air (Verlaine) [container]
    - text: Nous ne sommes pas le troupeau [container]
    - … 188 more
1. `Les Pieds dans le plat`: single, book, 1 parts, 0 missing, order –, unproofread
    - text: Les Pieds dans le plat
1. `Contes de Madame de Villeneuve`: subpages, collection, 3 parts, 0 missing, order scan-volumes, proofread
    - front: Contes de Madame de Villeneuve
    - text: Contes de Madame de Villeneuve (`Contes de Madame de Villeneuve/Contes de Madame de Villeneuve`) [container]
    - text: La Belle et la Bête (`Contes de Madame de Villeneuve/La Belle et la Bête`) [container]
    - text: Les Nayades (`Contes de Madame de Villeneuve/Les Nayades`) [container]
1. `Recherches sur la nature et les causes de la richesse des nations`: subpages, book, 38 parts, 0 missing, order scan-volumes, proofread
    - toc: Recherches sur la nature et les causes de la richesse des nations
    - chapter: Préface d’Adolphe Blanqui (`Recherches sur la nature et les causes de la richesse des nations/Préface/Blanqui`) [container]
    - text: Notice sur la vie et les travaux d’Adam Smith (`Recherches sur la nature et les causes de la richesse des nations/Notice`) [container]
    - chapter: Préface de Germain Garnier (`Recherches sur la nature et les causes de la richesse des nations/Préface/Garnier`) [container]
    - chapter: Introduction (`Recherches sur la nature et les causes de la richesse des nations/Introduction`) [container]
    - chapter: Chapitre I. (`Recherches sur la nature et les causes de la richesse des nations/Livre 1/1`) [container]
    - chapter: Chapitre II. (`Recherches sur la nature et les causes de la richesse des nations/Livre 1/2`) [container]
    - chapter: Chapitre III. (`Recherches sur la nature et les causes de la richesse des nations/Livre 1/3`) [container]
    - chapter: Chapitre IV. (`Recherches sur la nature et les causes de la richesse des nations/Livre 1/4`) [container]
    - chapter: Chapitre V. (`Recherches sur la nature et les causes de la richesse des nations/Livre 1/5`) [container]
    - chapter: Chapitre VI. (`Recherches sur la nature et les causes de la richesse des nations/Livre 1/6`) [container]
    - chapter: Chapitre VII. (`Recherches sur la nature et les causes de la richesse des nations/Livre 1/7`) [container]
    - … 27 more
1. `Le colonel de Surville (Eugène Sue)`: single, collection, 1 parts, 0 missing, order –, validated
    - text: Le colonel de Surville (Eugène Sue)
1. `Table générale et analytique, 1re série`: subpages, book, 27 parts, 0 missing, order toc, proofread
    - toc: Table générale et analytique, 1re série
    - text: A (`Table générale et analytique, 1re série/A`) [container]
    - text: B (`Table générale et analytique, 1re série/B`) [container]
    - chapter: C (`Table générale et analytique, 1re série/C`) [container]
    - chapter: D (`Table générale et analytique, 1re série/D`) [container]
    - text: E (`Table générale et analytique, 1re série/E`) [container]
    - text: F (`Table générale et analytique, 1re série/F`) [container]
    - text: G (`Table générale et analytique, 1re série/G`) [container]
    - text: H (`Table générale et analytique, 1re série/H`) [container]
    - chapter: I (`Table générale et analytique, 1re série/I`) [container]
    - text: J (`Table générale et analytique, 1re série/J`) [container]
    - text: K (`Table générale et analytique, 1re série/K`) [container]
    - … 16 more
1. `Le Comte de Monte-Cristo`: subpages, book, 119 parts, 0 missing, order scan-volumes, validated
    - toc: Le Comte de Monte-Cristo
    - text: Marseille. — L’arrivée (`Le Comte de Monte-Cristo/Chapitre 1`) [container]
    - text: Le père et le fils (`Le Comte de Monte-Cristo/Chapitre 2`) [container]
    - text: Les Catalans (`Le Comte de Monte-Cristo/Chapitre 3`) [container]
    - text: Complot (`Le Comte de Monte-Cristo/Chapitre 4`) [container]
    - text: Le repas des fiançailles (`Le Comte de Monte-Cristo/Chapitre 5`) [container]
    - text: Le substitut du procureur du roi (`Le Comte de Monte-Cristo/Chapitre 6`) [container]
    - text: L’interrogatoire (`Le Comte de Monte-Cristo/Chapitre 7`) [container]
    - text: Le château d’If (`Le Comte de Monte-Cristo/Chapitre 8`) [container]
    - text: Le soir des fiançailles (`Le Comte de Monte-Cristo/Chapitre 9`) [container]
    - text: Le petit cabinet des Tuileries (`Le Comte de Monte-Cristo/Chapitre 10`) [container]
    - text: L’ogre de Corse (`Le Comte de Monte-Cristo/Chapitre 11`) [container]
    - … 108 more
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
1. `Traité élémentaire de physique (Haüy)/1803`: subpages, book, 11 parts, 0 missing, order toc, proofread
    - toc: Traité élémentaire de physique (Haüy)/1803
    - chapter: Introduction (`Traité élémentaire de physique (Haüy)/1803/Introduction`) [container]
    - text: Notion de la Physique (`Traité élémentaire de physique (Haüy)/1803/Notion`) [container]
    - text: Des Propriétés les plus générales des Corps (`Traité élémentaire de physique (Haüy)/1803/Chapitre I`) [container]
    - text: Des Propriétés relatives à certaines Forces qui sollicitent les Corps (`Traité élémentaire de physique (Haüy)/1803/Chapitre II`) [container]
    - text: De l’Eau (`Traité élémentaire de physique (Haüy)/1803/Chapitre III`) [container]
    - text: De l’Air (`Traité élémentaire de physique (Haüy)/1803/Chapitre IV`) [container]
    - text: De l’Électricité (`Traité élémentaire de physique (Haüy)/1803/Chapitre V`) [hierarchy]
    - text: Du Magnétisme (`Traité élémentaire de physique (Haüy)/1803/Chapitre VI`) [container]
    - text: De la Lumière (`Traité élémentaire de physique (Haüy)/1803/Chapitre VII`) [container]
    - chapter: Table alphabétique (`Traité élémentaire de physique (Haüy)/1803/Table alphabétique`) [container]
    - text: Annexes : 24 planches (`Traité élémentaire de physique (Haüy)/1803/Planches`) [container]
1. `Commentaire sur les Psaumes, du Psaume I au Psaume C (Jérôme de Stridon)`: single, book, 1 parts, 0 missing, order –, unproofread
    - text: Commentaire sur les Psaumes, du Psaume I au Psaume C (Jérôme de Stridon)
1. `Biographie universelle des musiciens`: subpages, reference, 3 parts, 185 missing, order chain, proofread
    - toc: Biographie universelle des musiciens
    - text: Avertissement (`Biographie universelle des musiciens/Avertissement`) [container]
    - chapter: Préface (`Biographie universelle des musiciens/Préface`) [container]
    - text: Résumé philosophique de l’histoire de la musique (`Biographie universelle des musiciens/Résumé philosophique de l’histoire de la musique`) [container]
1. `Œuvres complètes de Fénelon (1848-1851)`: linked, book, 95 parts, 0 missing, order scan-volumes, proofread
    - front: Œuvres complètes de Fénelon (1848-1851)
    - text: Réflexions saintes pour tous les jours du mois [container]
    - chapter: I (`Réflexions saintes pour tous les jours du mois/I`) [container]
    - chapter: II (`Réflexions saintes pour tous les jours du mois/II`) [container]
    - chapter: III (`Réflexions saintes pour tous les jours du mois/III`) [container]
    - chapter: IV (`Réflexions saintes pour tous les jours du mois/IV`) [container]
    - chapter: V (`Réflexions saintes pour tous les jours du mois/V`) [container]
    - chapter: VI (`Réflexions saintes pour tous les jours du mois/VI`) [container]
    - chapter: VII (`Réflexions saintes pour tous les jours du mois/VII`) [container]
    - chapter: VIII (`Réflexions saintes pour tous les jours du mois/VIII`) [container]
    - chapter: IX (`Réflexions saintes pour tous les jours du mois/IX`) [container]
    - chapter: X (`Réflexions saintes pour tous les jours du mois/X`) [container]
    - … 84 more
1. `Plérophories`: subpages, book, 2 parts, 0 missing, order toc, proofread
    - front: Plérophories
    - chapter: INTRODUCTION (`Plérophories/Introduction`) [container]
    - chapter: APPENDICE (`Plérophories/Appendice`) [container]
1. `Système des Animaux sans vertèbres`: single, book, 1 parts, 0 missing, order –, unproofread
    - text: Système des Animaux sans vertèbres
1. `Système des contradictions économiques ou Philosophie de la misère`: subpages, book, 18 parts, 0 missing, order scan-volumes, proofread
    - toc: Système des contradictions économiques ou Philosophie de la misère
    - chapter: Tome 1 (`Système des contradictions économiques ou Philosophie de la misère/Texte entier/Tome 1`) [container]
    - volume: Table des matières (`Système des contradictions économiques ou Philosophie de la misère/Table`) [container]
      - chapter: PROLOGUE (`Système des contradictions économiques ou Philosophie de la misère/Prologue`) [container]
      - chapter: CHAPITRE I (`Système des contradictions économiques ou Philosophie de la misère/Chapitre 01`) [container]
      - chapter: CHAPITRE II (`Système des contradictions économiques ou Philosophie de la misère/Chapitre 02`) [container]
      - chapter: CHAPITRE III (`Système des contradictions économiques ou Philosophie de la misère/Chapitre 03`) [container]
      - chapter: CHAPITRE IV (`Système des contradictions économiques ou Philosophie de la misère/Chapitre 04`) [container]
      - chapter: CHAPITRE V (`Système des contradictions économiques ou Philosophie de la misère/Chapitre 05`) [container]
      - chapter: CHAPITRE VI (`Système des contradictions économiques ou Philosophie de la misère/Chapitre 06`) [container]
      - chapter: CHAPITRE VII (`Système des contradictions économiques ou Philosophie de la misère/Chapitre 07`) [container]
      - chapter: CHAPITRE VIII (`Système des contradictions économiques ou Philosophie de la misère/Chapitre 08`) [container]
    - … 7 more
1. `Histoire des quatre fils Aymon (1840)`: subpages, book, 3 parts, 0 missing, order toc, proofread
    - toc: Histoire des quatre fils Aymon (1840)
    - chapter: Préface (`Histoire des quatre fils Aymon (1840)/Préface`) [container]
    - text: Au lecteur (`Histoire des quatre fils Aymon (1840)/Au lecteur`) [container]
    - text: Histoire des quatre fils Aymon (`Histoire des quatre fils Aymon (1840)/Histoire des quatre fils Aymon`) [container]
1. `Commentaire sur l’Épître de saint Paul aux Philippiens`: single, book, 1 parts, 0 missing, order –, proofread
    - text: Commentaire sur l’Épître de saint Paul aux Philippiens
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
1. `Discours sur les psaumes (Augustin)`: subpages, collection, 15 parts, 0 missing, order toc, unproofread
    - toc: Discours sur les psaumes (Augustin)
    - text: Psaumes I à X (`Discours sur les psaumes (Augustin)/Psaumes I à X`) [hierarchy]
    - text: Psaumes XI à XX (`Discours sur les psaumes (Augustin)/Psaumes XI à XX`) [hierarchy]
    - text: Psaumes XXI à XXX (`Discours sur les psaumes (Augustin)/Psaumes XXI à XXX`) [hierarchy]
    - text: Psaumes XXXI à XL (`Discours sur les psaumes (Augustin)/Psaumes XXXI à XL`) [hierarchy]
    - text: Psaumes XLI à L (`Discours sur les psaumes (Augustin)/Psaumes XLI à L`) [hierarchy]
    - text: Psaumes LI à LX (`Discours sur les psaumes (Augustin)/Psaumes LI à LX`) [hierarchy]
    - text: Psaumes LXI à LXX (`Discours sur les psaumes (Augustin)/Psaumes LXI à LXX`) [hierarchy]
    - text: Psaumes LXXI à LXXX (`Discours sur les psaumes (Augustin)/Psaumes LXXI à LXXX`) [hierarchy]
    - text: Psaumes LXXXI à XC (`Discours sur les psaumes (Augustin)/Psaumes LXXXI à XC`) [hierarchy]
    - text: Psaumes XCI à C (`Discours sur les psaumes (Augustin)/Psaumes XCI à C`) [hierarchy]
    - text: Psaumes CI à CX (`Discours sur les psaumes (Augustin)/Psaumes CI à CX`) [hierarchy]
    - … 4 more
1. `Les Nuits d’Young`: subpages, book, 32 parts, 0 missing, order scan, proofread
    - front: Les Nuits d’Young
    - chapter: Tome 1 (`Les Nuits d’Young/Texte entier/Tome 1`) [container]
    - text: Discours préliminaire (`Les Nuits d’Young/Discours préliminaire`) [container]
    - text: . Les misères de l’humanité. (`Les Nuits d’Young/Première nuit`) [container]
    - text: . L’amitié. (`Les Nuits d’Young/Deuxième nuit`) [container]
    - text: . Le temps. (`Les Nuits d’Young/Troisième nuit`) [container]
    - text: . Narcisse. (`Les Nuits d’Young/Quatrième nuit`) [container]
    - text: . Le remède contre la crainte de la mort. (`Les Nuits d’Young/Cinquième nuit`) [container]
    - text: . L’oubli de la mort. (`Les Nuits d’Young/Sixième nuit`) [container]
    - text: . Le caractère de la mort. (`Les Nuits d’Young/Septième nuit`) [container]
    - text: . L’immortalité. (`Les Nuits d’Young/Huitième nuit`) [container]
    - text: . L’immortalité. — Preuves physiques. (`Les Nuits d’Young/Neuvième nuit`) [container]
    - … 21 more
1. `Monsieur Nicolas`: subpages, book, 9 parts, 8 missing, order chain, proofread
    - front: Monsieur Nicolas
    - volume: Première époque, (`Monsieur Nicolas/Première époque`) [hierarchy]
      - chapter: Note de l’éditeur. (`Monsieur Nicolas/Première époque/Note de l’éditeur`) [container]
      - text: Prospectus. (`Monsieur Nicolas/Première époque/Prospectus`) [container]
      - chapter: Note de l’auteur. (`Monsieur Nicolas/Première époque/Note de l’auteur`) [container]
      - text: Dédicace à moi. (`Monsieur Nicolas/Première époque/Dédicace`) [container]
      - chapter: Introduction. (`Monsieur Nicolas/Première époque/Introduction`) [container]
      - text: Première époque, Mes années premières. (`Monsieur Nicolas/Première époque/Texte`) [container]
      - text: Liste des estampes. (`Monsieur Nicolas/Première époque/Estampes`) [container]
    - text: Seconde époque (`Monsieur Nicolas/Seconde époque/Texte`) [container]
1. `Dictionnaire raisonné de l’architecture française du XIe au XVIe siècle`: subpages, collection, 530 parts, 0 missing, order natural, validated
    - toc: Dictionnaire raisonné de l’architecture française du XIe au XVIe siècle
    - text: Abaque (`Dictionnaire raisonné de l’architecture française du XIe au XVIe siècle/Abaque`) [container]
    - text: Abat-sons (`Dictionnaire raisonné de l’architecture française du XIe au XVIe siècle/Abat-sons`) [container]
    - text: Abside (`Dictionnaire raisonné de l’architecture française du XIe au XVIe siècle/Abside`) [container]
    - text: Accolade (`Dictionnaire raisonné de l’architecture française du XIe au XVIe siècle/Accolade`) [hierarchy]
    - text: Accoudoir (`Dictionnaire raisonné de l’architecture française du XIe au XVIe siècle/Accoudoir`) [hierarchy]
    - text: Agrafe (`Dictionnaire raisonné de l’architecture française du XIe au XVIe siècle/Agrafe`) [hierarchy]
    - text: Aiguille (`Dictionnaire raisonné de l’architecture française du XIe au XVIe siècle/Aiguille`) [hierarchy]
    - text: Albâtre (`Dictionnaire raisonné de l’architecture française du XIe au XVIe siècle/Albâtre`) [hierarchy]
    - text: Alignement (`Dictionnaire raisonné de l’architecture française du XIe au XVIe siècle/Alignement`) [hierarchy]
    - text: Allège (`Dictionnaire raisonné de l’architecture française du XIe au XVIe siècle/Allège`) [hierarchy]
    - text: Amortissement (`Dictionnaire raisonné de l’architecture française du XIe au XVIe siècle/Amortissement`) [hierarchy]
    - … 519 more
1. `Traité instituant la Communauté européenne (1992)`: single, periodical-issue, 1 parts, 0 missing, order –, unproofread
    - text: Traité instituant la Communauté européenne (1992)
1. `Histoire de l'abbaye de Pontigny, ordre de Cîteaux`: single, book, 1 parts, 0 missing, order –, proofread
    - text: Histoire de l'abbaye de Pontigny, ordre de Cîteaux
1. `Commentaires sur le prophète Osée`: single, book, 1 parts, 0 missing, order –, unproofread
    - text: Commentaires sur le prophète Osée
1. `Piquillo Alliaga`: subpages, book, 93 parts, 0 missing, order scan, validated
    - toc: Piquillo Alliaga
    - text: Les fueros de Navarre (`Piquillo Alliaga/01`) [container]
    - text: Le triomphe (`Piquillo Alliaga/02`) [container]
    - text: Les suites d’un triomphe (`Piquillo Alliaga/03`) [container]
    - text: Le capitaine Juan-Baptista Balseiro (`Piquillo Alliaga/04`) [container]
    - text: L’hôtellerie de Buen Socorro (`Piquillo Alliaga/05`) [container]
    - text: Le carrefour de la forêt (`Piquillo Alliaga/06`) [container]
    - text: La délivrance (`Piquillo Alliaga/07`) [container]
    - text: La consulta du roi (`Piquillo Alliaga/08`) [container]
    - text: L’habitation du Maure (`Piquillo Alliaga/09`) [container]
    - text: La visite de la reine (`Piquillo Alliaga/10`) [container]
    - text: La chambre du roi et de la reine (`Piquillo Alliaga/11`) [container]
    - … 82 more
1. `Mémoires extraits des recueils de l’Académie de Turin`: subpages, collection, 16 parts, 0 missing, order toc, proofread
    - toc: Mémoires extraits des recueils de l’Académie de Turin
    - text: Recherches sur la méthode De maximis et minimis (`Mémoires extraits des recueils de l’Académie de Turin/Recherches sur la méthode De maximis et minimis`) [container]
    - text: Sur l’intégration d’une équation différentielle à différences finies, qui contient la théorie des suites récurrentes (`Mémoires extraits des recueils de l’Académie de Turin/Sur l’intégration d’une équation différentielle à différences finies`) [container]
    - text: Recherches sur la nature et la propagation du son (`Mémoires extraits des recueils de l’Académie de Turin/Recherches sur la nature et la propagation du son`) [container]
    - text: Nouvelles recherches sur la nature et la propagation du son (`Mémoires extraits des recueils de l’Académie de Turin/Nouvelles recherches sur la nature et la propagation du son`) [container]
    - text: Addition aux premières recherches sur la nature et la propagation du son (`Mémoires extraits des recueils de l’Académie de Turin/Addition aux premières recherches sur la nature et la propagation du son`) [container]
    - text: Essai d’une nouvelle méthode pour déterminer les maxima et les minima des formules intégrales indéfinies (`Mémoires extraits des recueils de l’Académie de Turin/Essai d’une nouvelle méthode pour déterminer les maxima et les minima des formules intégrales indéfinies`) [container]
    - text: Application de la méthode exposée dans le Mémoire précédent à la solution de différents Problèmes de Dynamique (`Mémoires extraits des recueils de l’Académie de Turin/Application de la méthode exposée dans le Mémoire précédent à la solution de différents Problèmes de Dynamique`) [container]
    - text: Solution de différents Problèmes de Calcul intégral (`Mémoires extraits des recueils de l’Académie de Turin/Solution de différents Problèmes de Calcul intégral`) [container]
    - text: Solution d’un Problème d’Arithmétique (`Mémoires extraits des recueils de l’Académie de Turin/Solution d’un Problème d’Arithmétique`) [container]
    - text: Sur l’intégration de quelques équations différentielles dont les indéterminées sont séparées, mais dont chaque membre en particulier n’est point intégrable (`Mémoires extraits des recueils de l’Académie de Turin/Sur l’intégration de quelques équations différentielles dont les indéterminées sont séparées, mais dont chaque membre en particulier n’est point intégrable`) [container]
    - text: Sur la méthode des variations (`Mémoires extraits des recueils de l’Académie de Turin/Sur la méthode des variations`) [container]
    - … 5 more
1. `La Convention (Jaurès)`: virtual, book, 35 parts, 0 missing, order scan-volumes, validated
    - text: 215 - 256 (`La Convention (Jaurès)/215 - 256`)
    - text: 257 - 307 (`La Convention (Jaurès)/257 - 307`) [hierarchy]
    - text: 308 - 349 (`La Convention (Jaurès)/308 - 349`) [hierarchy]
    - text: 350 - 399 (`La Convention (Jaurès)/350 - 399`) [hierarchy]
    - text: 400 - 442 (`La Convention (Jaurès)/400 - 442`) [hierarchy]
    - text: 442 - 500 (`La Convention (Jaurès)/442 - 500`) [hierarchy]
    - text: 442 - 854 (`La Convention (Jaurès)/442 - 854`) [hierarchy]
    - text: 501 - 550 (`La Convention (Jaurès)/501 - 550`) [hierarchy]
    - text: 551 - 600 (`La Convention (Jaurès)/551 - 600`) [hierarchy]
    - text: 601 - 650 (`La Convention (Jaurès)/601 - 650`) [hierarchy]
    - text: 651 - 700 (`La Convention (Jaurès)/651 - 700`) [hierarchy]
    - text: 701 - 750 (`La Convention (Jaurès)/701 - 750`) [hierarchy]
    - … 23 more
1. `La France littéraire, ou Dictionnaire bibliographique`: subpages, book, 342 parts, 0 missing, order natural, unproofread
    - front: La France littéraire, ou Dictionnaire bibliographique
    - text: Aux souscripteurs de la France littéraire (`La France littéraire, ou Dictionnaire bibliographique/Aux souscripteurs de la France littéraire`) [hierarchy]
    - text: Ciled (C.-F.-A.) (`La France littéraire, ou Dictionnaire bibliographique/Ciled (C.-F.-A.)`) [hierarchy]
    - text: Cuzey (le chevalier) (`La France littéraire, ou Dictionnaire bibliographique/Cuzey (le chevalier)`) [hierarchy]
    - text: Cuzey (Marie-Catherine-Abel de Beffroy, baronne de) (`La France littéraire, ou Dictionnaire bibliographique/Cuzey (Marie-Catherine-Abel de Beffroy, baronne de)`) [hierarchy]
    - text: Cuzieu (Hector de) (`La France littéraire, ou Dictionnaire bibliographique/Cuzieu (Hector de)`) [hierarchy]
    - text: Cyprien (S. Cécile) (`La France littéraire, ou Dictionnaire bibliographique/Cyprien (S. Cécile)`) [hierarchy]
    - text: Cyrano de Bergerac (`La France littéraire, ou Dictionnaire bibliographique/Cyrano de Bergerac`) [hierarchy]
    - text: Cyrille (S.) (`La France littéraire, ou Dictionnaire bibliographique/Cyrille (S.)`) [hierarchy]
    - text: Cyrille de La Tasse (`La France littéraire, ou Dictionnaire bibliographique/Cyrille de La Tasse`) [hierarchy]
    - text: Cyrille-Lucar (`La France littéraire, ou Dictionnaire bibliographique/Cyrille-Lucar`) [hierarchy]
    - text: Dabany (Mme) (`La France littéraire, ou Dictionnaire bibliographique/Dabany (Mme)`) [hierarchy]
    - … 331 more

### G: reference works (274)

1. `Supplément au Dictionnaire argotique`: subpages, reference, 407 parts, 0 missing, order natural, validated
    - front: Supplément au Dictionnaire argotique
    - text: Avis (`Supplément au Dictionnaire argotique/Avis`) [container]
    - volume: Index alphabétique - A (`Supplément au Dictionnaire argotique/Index alphabétique - A`) [hierarchy]
      - text: Abouler (`Supplément au Dictionnaire argotique/Abouler`) [container]
      - text: Abour (`Supplément au Dictionnaire argotique/Abour`) [container]
      - text: Abourdier (`Supplément au Dictionnaire argotique/Abourdier`) [container]
      - text: Accoerrer (`Supplément au Dictionnaire argotique/Accoerrer`) [container]
      - text: Affur (`Supplément au Dictionnaire argotique/Affur`) [container]
      - text: Agathe (`Supplément au Dictionnaire argotique/Agathe`) [container]
      - text: Ambier (`Supplément au Dictionnaire argotique/Ambier`) [container]
      - text: Amadoué (`Supplément au Dictionnaire argotique/Amadoué`) [container]
      - text: Amadouage (`Supplément au Dictionnaire argotique/Amadouage`) [container]
    - … 396 more
1. `Nouveau dictionnaire des origines, inventions et découvertes/2e éd., 1834`: subpages, reference, 1 parts, 1 missing, order single, unproofread
    - toc: Nouveau dictionnaire des origines, inventions et découvertes/2e éd., 1834
    - text: Encre (`Nouveau dictionnaire des origines, inventions et découvertes/2e éd., 1834/Encre`) [container]
1. `Dictionnaire grammatical de la langue françoise/1re éd., 1761`: subpages, reference, 4 parts, 3 missing, order scan, proofread
    - toc: Dictionnaire grammatical de la langue françoise/1re éd., 1761
    - chapter: Introduction (`Dictionnaire grammatical de la langue françoise/1re éd., 1761/Introduction`) [hierarchy]
    - chapter: Tome 1 (`Dictionnaire grammatical de la langue françoise/1re éd., 1761/Tome 1`) [container]
    - text: Epitre (`Dictionnaire grammatical de la langue françoise/1re éd., 1761/Epitre`) [container]
    - chapter: Préface (`Dictionnaire grammatical de la langue françoise/1re éd., 1761/Préface`) [container]
1. `Dictionnaire français-breton de Le Gonidec`: mixed, reference, 3 parts, 0 missing, order scan, unproofread
    - toc: Dictionnaire français-breton de Le Gonidec
    - text: Essai sur l’histoire de la langue bretonne [container]
    - text: Index alphabétique - A (`Dictionnaire français-breton de Le Gonidec/Index alphabétique - A`) [container]
    - text: Index alphabétique - B (`Dictionnaire français-breton de Le Gonidec/Index alphabétique - B`) [container]
1. `Dictionnaire des sciences philosophiques - 1844 - 1875/1re éd., 1844`: single, reference, 1 parts, 29 missing, order –, unproofread
    - text: Dictionnaire des sciences philosophiques - 1844 - 1875/1re éd., 1844
1. `Dictionnaire historique et critique/11e éd./Tome 06`: single, reference, 1 parts, 0 missing, order –, unproofread, excluded no-text
    - text: Dictionnaire historique et critique/11e éd./Tome 06
1. `Dictionnaire minier et métallurgique allemand-français`: subpages, reference, 570 parts, 0 missing, order scan, proofread
    - toc: Dictionnaire minier et métallurgique allemand-français
    - text: Abarbeiten (`Dictionnaire minier et métallurgique allemand-français/Abarbeiten`) [hierarchy]
    - text: Abbau mit breitem Blick (`Dictionnaire minier et métallurgique allemand-français/Abbau mit breitem Blick`) [hierarchy]
    - text: Abbaue (`Dictionnaire minier et métallurgique allemand-français/Abbaue`) [hierarchy]
    - text: Abbauen (`Dictionnaire minier et métallurgique allemand-français/Abbauen`) [hierarchy]
    - text: Abbauhöhe (`Dictionnaire minier et métallurgique allemand-français/Abbauhöhe`) [hierarchy]
    - text: Abbaumethode (`Dictionnaire minier et métallurgique allemand-français/Abbaumethode`) [hierarchy]
    - text: Abbaustrecke (`Dictionnaire minier et métallurgique allemand-français/Abbaustrecke`) [hierarchy]
    - text: Abbauverfahren (`Dictionnaire minier et métallurgique allemand-français/Abbauverfahren`) [hierarchy]
    - text: Abbeizen (`Dictionnaire minier et métallurgique allemand-français/Abbeizen`) [hierarchy]
    - text: Abblicken (`Dictionnaire minier et métallurgique allemand-français/Abblicken`) [hierarchy]
    - text: Abbohren (`Dictionnaire minier et métallurgique allemand-français/Abbohren`) [hierarchy]
    - … 559 more
1. `Dictionnaire critique de la langue française`: subpages, reference, 1 parts, 2 missing, order single, unproofread
    - toc: Dictionnaire critique de la langue française
    - text: Noms des auteurs cités (`Dictionnaire critique de la langue française/Auteurs cites`) [container]
1. `Nouveau dictionnaire universel`: single, reference, 1 parts, 0 missing, order –, unproofread, excluded no-text
    - text: Nouveau dictionnaire universel
1. `Dumas.- Grand dictionnaire de cuisine, 1873`: virtual, reference, 166 parts, 0 missing, order natural, proofread
    - text: Abaisse (`Dumas.- Grand dictionnaire de cuisine, 1873/Abaisse`)
    - text: Abatis (`Dumas.- Grand dictionnaire de cuisine, 1873/Abatis`) [hierarchy]
    - text: Abavo (`Dumas.- Grand dictionnaire de cuisine, 1873/Abavo`) [hierarchy]
    - text: Abdelavis (`Dumas.- Grand dictionnaire de cuisine, 1873/Abdelavis`) [hierarchy]
    - text: Able (`Dumas.- Grand dictionnaire de cuisine, 1873/Able`) [hierarchy]
    - text: Ablette (`Dumas.- Grand dictionnaire de cuisine, 1873/Ablette`) [hierarchy]
    - text: Abricot (`Dumas.- Grand dictionnaire de cuisine, 1873/Abricot`) [hierarchy]
    - text: Abrus (`Dumas.- Grand dictionnaire de cuisine, 1873/Abrus`) [hierarchy]
    - text: Absinthe (`Dumas.- Grand dictionnaire de cuisine, 1873/Absinthe`) [hierarchy]
    - text: Acacie (`Dumas.- Grand dictionnaire de cuisine, 1873/Acacie`) [hierarchy]
    - text: Acalot (`Dumas.- Grand dictionnaire de cuisine, 1873/Acalot`) [hierarchy]
    - text: Acanthe (`Dumas.- Grand dictionnaire de cuisine, 1873/Acanthe`) [hierarchy]
    - … 154 more
1. `Table des noms d'hommes et de femmes les plus usités`: single, reference, 1 parts, 0 missing, order –, unproofread
    - text: Table des noms d'hommes et de femmes les plus usités
1. `Nouveau dictionnaire classique de la langue française`: subpages, reference, 1 parts, 1 missing, order single, proofread
    - toc: Nouveau dictionnaire classique de la langue française
    - chapter: Introduction (`Nouveau dictionnaire classique de la langue française/Introduction`) [hierarchy]
1. `Le grand dictionnaire historique/éd. de 1759`: subpages, reference, 562 parts, 0 missing, order natural, unproofread
    - front: Le grand dictionnaire historique/éd. de 1759
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
    - text: Aalam (`Le grand dictionnaire historique/éd. de 1759/Aalam`) [hierarchy]
    - … 551 more
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
1. `Dictionnaire de l’Académie française/7e éd., 1878`: single, reference, 1 parts, 0 missing, order –, unproofread
    - text: Dictionnaire de l’Académie française/7e éd., 1878
1. `Catholicon (Lagadec)/1867`: single, reference, 1 parts, 0 missing, order –, unproofread
    - text: Catholicon (Lagadec)/1867
1. `Dictionnaire des métaphores de Victor Hugo`: single, reference, 1 parts, 0 missing, order –, unproofread, excluded no-text
    - text: Dictionnaire des métaphores de Victor Hugo
1. `Dictionnaire des Arts et des Sciences/Nlle éd., 1732`: subpages, reference, 3 parts, 0 missing, order toc-partial, proofread
    - toc: Dictionnaire des Arts et des Sciences/Nlle éd., 1732
    - chapter: Préface (`Dictionnaire des Arts et des Sciences/Nlle éd., 1732/Préface`) [container]
    - chapter: Tome 1 (`Dictionnaire des Arts et des Sciences/Nlle éd., 1732/Tome 1/Fascicules`) [hierarchy]
    - text: 001-010 (`Dictionnaire des Arts et des Sciences/Nlle éd., 1732/Tome 1/001-010`) [hierarchy]
1. `Dictionnaire wallon-françois (Villers)`: subpages, reference, 2,505 parts, 54 missing, order natural, proofread
    - toc: Dictionnaire wallon-françois (Villers)
    - text: aweie du balance (`Dictionnaire wallon-françois (Villers)/aweie du balance`) [container]
    - text: aweie du pack (`Dictionnaire wallon-françois (Villers)/aweie du pack`) [container]
    - text: chabotez (`Dictionnaire wallon-françois (Villers)/chabotez`) [container]
    - text: finez (`Dictionnaire wallon-françois (Villers)/finez`) [hierarchy]
    - text: France (`Dictionnaire wallon-françois (Villers)/France`) [hierarchy]
    - text: fuatmin (`Dictionnaire wallon-françois (Villers)/fuatmin`) [hierarchy]
    - text: furdji (`Dictionnaire wallon-françois (Villers)/furdji`) [container]
    - volume: Index alphabétique - A (`Dictionnaire wallon-françois (Villers)/Index alphabétique - A`) [hierarchy]
      - text: aâie (`Dictionnaire wallon-françois (Villers)/aâie`) [container]
      - text: abâbeiemin (`Dictionnaire wallon-françois (Villers)/abâbeiemin`) [container]
      - text: abâbieje (`Dictionnaire wallon-françois (Villers)/abâbieje`) [container]
    - … 2,494 more
1. `Dictionnaire historique et critique/11e éd./Tome 15`: single, reference, 1 parts, 0 missing, order –, proofread, excluded no-text
    - text: Dictionnaire historique et critique/11e éd./Tome 15
1. `Dictionnaire historique de Feller/2e éd., 1797`: subpages, reference, 4 parts, 0 missing, order scan-volumes, proofread
    - front: Dictionnaire historique de Feller/2e éd., 1797
    - text: Aristote (`Dictionnaire historique de Feller/2e éd., 1797/Aristote`) [container]
    - text: Aristote (aristotile) (`Dictionnaire historique de Feller/2e éd., 1797/Aristote (aristotile)`) [container]
    - text: Aristote (autres) (`Dictionnaire historique de Feller/2e éd., 1797/Aristote (autres)`) [container]
    - text: Zoroastre (`Dictionnaire historique de Feller/2e éd., 1797/Zoroastre`) [container]
1. `Dictionnaire historique et critique/11e éd./Tome 11`: single, reference, 1 parts, 0 missing, order –, unproofread, excluded no-text
    - text: Dictionnaire historique et critique/11e éd./Tome 11
1. `Dictionnaire d’argot ou Guide des gens du monde (1ère éd.)`: subpages, reference, 822 parts, 0 missing, order natural, validated
    - front: Dictionnaire d’argot ou Guide des gens du monde (1ère éd.)
    - text: Abbaye rusante (`Dictionnaire d’argot ou Guide des gens du monde (1ère éd.)/Articles/Abbaye rusante`) [hierarchy]
    - text: Abbé (`Dictionnaire d’argot ou Guide des gens du monde (1ère éd.)/Articles/Abbé`) [hierarchy]
    - text: Abloquir (`Dictionnaire d’argot ou Guide des gens du monde (1ère éd.)/Articles/Abloquir`) [hierarchy]
    - text: Acheter (`Dictionnaire d’argot ou Guide des gens du monde (1ère éd.)/Articles/Acheter`) [hierarchy]
    - text: Admirable, bon, excellent (`Dictionnaire d’argot ou Guide des gens du monde (1ère éd.)/Articles/Admirable, bon, excellent`) [hierarchy]
    - text: Affle (l’) (`Dictionnaire d’argot ou Guide des gens du monde (1ère éd.)/Articles/Affle (l’)`) [hierarchy]
    - text: Affurer (`Dictionnaire d’argot ou Guide des gens du monde (1ère éd.)/Articles/Affurer`) [hierarchy]
    - text: Air important (`Dictionnaire d’argot ou Guide des gens du monde (1ère éd.)/Articles/Air important`) [hierarchy]
    - text: Aller (`Dictionnaire d’argot ou Guide des gens du monde (1ère éd.)/Articles/Aller`) [hierarchy]
    - text: Aller (s’en) (`Dictionnaire d’argot ou Guide des gens du monde (1ère éd.)/Articles/Aller (s’en)`) [hierarchy]
    - text: Ambier (`Dictionnaire d’argot ou Guide des gens du monde (1ère éd.)/Articles/Ambier`) [hierarchy]
    - … 811 more
1. `Biographie nationale de Belgique`: subpages, reference, 1,528 parts, 381 missing, order toc, proofread
    - front: Biographie nationale de Belgique
    - volume: Tome 1 (`Biographie nationale de Belgique/Tome 1`) [hierarchy]
      - text: Liste des collaborateurs (`Biographie nationale de Belgique/Tome 1/Liste des collaborateurs`) [hierarchy]
      - text: Arrêté organique (`Biographie nationale de Belgique/Tome 1/Arrêté organique`) [hierarchy]
      - text: Liste des membres (`Biographie nationale de Belgique/Tome 1/Liste des membres`) [hierarchy]
      - chapter: Introduction (`Biographie nationale de Belgique/Tome 1/Introduction`) [hierarchy]
      - text: AA, VANDER (`Biographie nationale de Belgique/Tome 1/AA, VANDER`) [hierarchy]
      - text: ABBÉ, Henri (`Biographie nationale de Belgique/Tome 1/ABBÉ, Henri`) [container]
      - text: ABEETS, A.-F. (`Biographie nationale de Belgique/Tome 1/ABEETS, A.-F.`) [container]
      - text: ABEL, Saint (`Biographie nationale de Belgique/Tome 1/ABEL, Saint`) [hierarchy]
      - text: ABLEBERT, Saint (`Biographie nationale de Belgique/Tome 1/ABLEBERT, Saint`) [hierarchy]
      - text: ABLYEN, Corneille (`Biographie nationale de Belgique/Tome 1/ABLYEN, Corneille`) [container]
    - … 1,517 more
1. `Dictionnaire historique des personnages célèbres de l’antiquité/1e éd, 1806`: subpages, reference, 16 parts, 25 missing, order scan, unproofread
    - front: Dictionnaire historique des personnages célèbres de l’antiquité/1e éd, 1806
    - text: A (`Dictionnaire historique des personnages célèbres de l’antiquité/1e éd, 1806/Index alphabétique - A`) [hierarchy]
    - text: Titre (`Dictionnaire historique des personnages célèbres de l’antiquité/1e éd, 1806/Titre`) [hierarchy]
    - chapter: Préface (`Dictionnaire historique des personnages célèbres de l’antiquité/1e éd, 1806/Préface`) [hierarchy]
    - text: Essai historique (`Dictionnaire historique des personnages célèbres de l’antiquité/1e éd, 1806/Essai historique`) [hierarchy]
    - text: Abderologus (`Dictionnaire historique des personnages célèbres de l’antiquité/1e éd, 1806/Abderologus`) [hierarchy]
    - text: Abiens (`Dictionnaire historique des personnages célèbres de l’antiquité/1e éd, 1806/Abiens`) [hierarchy]
    - text: Aborigènes (`Dictionnaire historique des personnages célèbres de l’antiquité/1e éd, 1806/Aborigènes`) [hierarchy]
    - text: Abra (`Dictionnaire historique des personnages célèbres de l’antiquité/1e éd, 1806/Abra`) [hierarchy]
    - text: Abrochaitès (`Dictionnaire historique des personnages célèbres de l’antiquité/1e éd, 1806/Abrochaitès`) [hierarchy]
    - text: Abrocomès (`Dictionnaire historique des personnages célèbres de l’antiquité/1e éd, 1806/Abrocomès`) [hierarchy]
    - text: Abrodiætus (`Dictionnaire historique des personnages célèbres de l’antiquité/1e éd, 1806/Abrodiætus`) [hierarchy]
    - … 5 more
1. `Explication historique des fables/Nlle éd., 1738`: single, reference, 1 parts, 1 missing, order –, unproofread
    - text: Explication historique des fables/Nlle éd., 1738
1. `Dictionnaire d’argot ou Guide des gens du monde (2e éd.)`: subpages, reference, 832 parts, 1 missing, order natural, validated
    - front: Dictionnaire d’argot ou Guide des gens du monde (2e éd.)
    - chapter: I (`Dictionnaire d’argot ou Guide des gens du monde (2e éd.)/Articles/I`) [hierarchy]
    - chapter: C. (`Dictionnaire d’argot ou Guide des gens du monde (2e éd.)/Articles/C.`) [hierarchy]
    - chapter: C (`Dictionnaire d’argot ou Guide des gens du monde (2e éd.)/Articles/C`) [hierarchy]
    - chapter: D (`Dictionnaire d’argot ou Guide des gens du monde (2e éd.)/Articles/D`) [hierarchy]
    - chapter: M (`Dictionnaire d’argot ou Guide des gens du monde (2e éd.)/Articles/M`) [hierarchy]
    - text: A (`Dictionnaire d’argot ou Guide des gens du monde (2e éd.)/Articles/A`) [hierarchy]
    - text: Abbaye rusante (`Dictionnaire d’argot ou Guide des gens du monde (2e éd.)/Articles/Abbaye rusante`) [hierarchy]
    - text: Abbé (`Dictionnaire d’argot ou Guide des gens du monde (2e éd.)/Articles/Abbé`) [hierarchy]
    - text: Abloquir (`Dictionnaire d’argot ou Guide des gens du monde (2e éd.)/Articles/Abloquir`) [hierarchy]
    - text: Acheter (`Dictionnaire d’argot ou Guide des gens du monde (2e éd.)/Articles/Acheter`) [hierarchy]
    - text: Admirable, bon, excellent (`Dictionnaire d’argot ou Guide des gens du monde (2e éd.)/Articles/Admirable, bon, excellent`) [hierarchy]
    - … 821 more
1. `Le grand dictionnaire historique/éd. de 1717`: single, reference, 1 parts, 0 missing, order –, unproofread
    - text: Le grand dictionnaire historique/éd. de 1717
1. `Dictionnaire philosophique/La Raison par alphabet - 6e ed. - Cramer (1769)`: subpages, reference, 139 parts, 0 missing, order scan-volumes, proofread
    - toc: Dictionnaire philosophique/La Raison par alphabet - 6e ed. - Cramer (1769)
    - chapter: Préface (`Dictionnaire philosophique/La Raison par alphabet - 6e ed. - Cramer (1769)/Préface`) [container]
    - text: Abbé (`Dictionnaire philosophique/La Raison par alphabet - 6e ed. - Cramer (1769)/Abbé`) [container]
    - text: A (`Dictionnaire philosophique/La Raison par alphabet - 6e ed. - Cramer (1769)/Index alphabétique/A`) [container]
    - text: Abraham (`Dictionnaire philosophique/La Raison par alphabet - 6e ed. - Cramer (1769)/Abraham`) [container]
    - text: Adam (`Dictionnaire philosophique/La Raison par alphabet - 6e ed. - Cramer (1769)/Adam`) [container]
    - text: Ame (`Dictionnaire philosophique/La Raison par alphabet - 6e ed. - Cramer (1769)/Ame`) [container]
    - text: Amitié (`Dictionnaire philosophique/La Raison par alphabet - 6e ed. - Cramer (1769)/Amitié`) [container]
    - text: Amour (`Dictionnaire philosophique/La Raison par alphabet - 6e ed. - Cramer (1769)/Amour`) [container]
    - text: Amour nommé Socratique (`Dictionnaire philosophique/La Raison par alphabet - 6e ed. - Cramer (1769)/Amour nommé Socratique`) [container]
    - text: Amour-propre (`Dictionnaire philosophique/La Raison par alphabet - 6e ed. - Cramer (1769)/Amour-propre`) [container]
    - text: Ange (`Dictionnaire philosophique/La Raison par alphabet - 6e ed. - Cramer (1769)/Ange`) [container]
    - … 128 more
1. `Dictionnaire idéologique (Robertson)`: subpages, reference, 6 parts, 7 missing, order toc, unproofread
    - front: Dictionnaire idéologique (Robertson)
    - chapter: Préface (`Dictionnaire idéologique (Robertson)/Préface`) [container]
    - chapter: Introduction (`Dictionnaire idéologique (Robertson)/Introduction`) [container]
    - text: Plan de classification (`Dictionnaire idéologique (Robertson)/Plan de classification`) [container]
    - text: Tableaux synoptiques des catégories (`Dictionnaire idéologique (Robertson)/Tableaux synoptiques des catégories`) [container]
    - text: Abréviations (`Dictionnaire idéologique (Robertson)/Abréviations`) [container]
    - text: Dictionnaire (`Dictionnaire idéologique (Robertson)/Dictionnaire`) [container]

### H: deep nesting (164)

1. `La théorie de la physique (Rey)`: subpages, book, 32 parts, 0 missing, order toc, proofread
    - toc: La théorie de la physique (Rey)
    - chapter: Préface (`La théorie de la physique (Rey)/Préface`) [container]
    - chapter: Introduction. Délimitation du problème et intérêts divers qu’il présente (`La théorie de la physique (Rey)/Introduction`) [container]
    - volume: PREMIÈRE PARTIE. L’ANALYSE DES DOCTRINES (`La théorie de la physique (Rey)/Partie 1`) [container]
      - volume: Livre 1 (`La théorie de la physique (Rey)/Partie 1/Livre 1`) [container]
        - chapter: Chapitre 1 (`La théorie de la physique (Rey)/Partie 1/Livre 1/Chapitre 1`) [container]
        - chapter: Chapitre 2 (`La théorie de la physique (Rey)/Partie 1/Livre 1/Chapitre 2`) [container]
      - volume: Livre 2 (`La théorie de la physique (Rey)/Partie 1/Livre 2`) [container]
        - chapter: Chapitre 1 (`La théorie de la physique (Rey)/Partie 1/Livre 2/Chapitre 1`) [container]
        - chapter: Chapitre 2 (`La théorie de la physique (Rey)/Partie 1/Livre 2/Chapitre 2`) [container]
        - chapter: Chapitre 3 (`La théorie de la physique (Rey)/Partie 1/Livre 2/Chapitre 3`) [container]
      - volume: Livre 3 (`La théorie de la physique (Rey)/Partie 1/Livre 3`) [container]
    - … 21 more
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
1. `Nouveau Larousse illustré`: virtual, collection, 64 parts, 38 missing, order single, proofread
    - volume: 1898 (`Nouveau Larousse illustré/1898`)
      - volume: Index alphabétique - A (`Nouveau Larousse illustré/1898/Index alphabétique - A`) [hierarchy]
        - text: A (`Nouveau Larousse illustré/1898/A`) [container]
        - text: A de Charlemagne (`Nouveau Larousse illustré/1898/A de Charlemagne`) [container]
        - text: A, AA, AAA (`Nouveau Larousse illustré/1898/A, AA, AAA`) [container]
        - text: A^A (`Nouveau Larousse illustré/1898/A^A`) [container]
        - text: AA (`Nouveau Larousse illustré/1898/AA`) [container]
        - text: Aa, Pierre Van der (`Nouveau Larousse illustré/1898/Aa, Pierre Van der`) [container]
        - text: Aa (fleuves) (`Nouveau Larousse illustré/1898/Aa (fleuves)`) [container]
        - text: Aabam (`Nouveau Larousse illustré/1898/Aabam`) [container]
        - text: Aach (`Nouveau Larousse illustré/1898/Aach`) [container]
        - text: Aachen (`Nouveau Larousse illustré/1898/Aachen`) [container]
    - … 52 more
1. `Histoires ou Contes du temps passé (1697)`: virtual, collection, 15 parts, 0 missing, order single, proofread
    - volume: Original (`Histoires ou Contes du temps passé (1697)/Original`)
      - text: Frontispice (`Histoires ou Contes du temps passé (1697)/Original/Frontispice`) [container]
      - text: Titre (`Histoires ou Contes du temps passé (1697)/Original/Titre`) [container]
      - text: A Mademoiselle (`Histoires ou Contes du temps passé (1697)/Original/A Mademoiselle`) [container]
      - volume: Table des Contes de ce Recüeil (`Histoires ou Contes du temps passé (1697)/Original/Table`) [container]
        - text: belle au bois dormant (`Histoires ou Contes du temps passé (1697)/Original/La belle au bois dormant`) [container]
        - text: Le petit chaperon rouge (`Histoires ou Contes du temps passé (1697)/Original/Le petit chaperon rouge`) [container]
        - text: La Barbe bleüe (`Histoires ou Contes du temps passé (1697)/Original/La Barbe bleue`) [container]
        - text: Le Maistre Chat, ou le Chat Botté (`Histoires ou Contes du temps passé (1697)/Original/Le Maistre Chat`) [container]
        - text: Les Fées (`Histoires ou Contes du temps passé (1697)/Original/Les Fées`) [container]
        - text: Cendrillon, ou la petite pantoufle de verre (`Histoires ou Contes du temps passé (1697)/Original/Cendrillon`) [container]
        - text: Riquet à la Houppe (`Histoires ou Contes du temps passé (1697)/Original/Riquet à la Houppe`) [container]
    - … 3 more
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
1. `Poésies choisies de André Chénier`: virtual, collection, 87 parts, 3 missing, order single, proofread
    - volume: Derocquigny, 1907 (`Poésies choisies de André Chénier/Derocquigny, 1907`)
      - volume: BUCOLIQUES. IDYLLES ET FRAGMENTS D’IDYLLES. (`Poésies choisies de André Chénier/Derocquigny, 1907/Bucoliques. Idylles et fragments d’idylles`) [container]
        - text: L’Aveugle (`Poésies choisies de André Chénier/Derocquigny, 1907/L’Aveugle`) [container]
        - text: Le Mendiant (`Poésies choisies de André Chénier/Derocquigny, 1907/Le Mendiant`) [container]
        - text: La Liberté (`Poésies choisies de André Chénier/Derocquigny, 1907/La Liberté`) [container]
        - text: Le Malade (`Poésies choisies de André Chénier/Derocquigny, 1907/Le Malade`) [container]
        - text: Hylas (`Poésies choisies de André Chénier/Derocquigny, 1907/Hylas`) [container]
        - text: La Jeune Tarentine (`Poésies choisies de André Chénier/Derocquigny, 1907/La Jeune Tarentine`) [container]
        - text: Sur un Groupe de Jupiter et d’Europe (`Poésies choisies de André Chénier/Derocquigny, 1907/Sur un Groupe de Jupiter et d’Europe`) [container]
        - text: Pasiphaé (`Poésies choisies de André Chénier/Derocquigny, 1907/Pasiphaé`) [container]
        - text: Pannychis (`Poésies choisies de André Chénier/Derocquigny, 1907/Pannychis`) [container]
        - text: Dryas (`Poésies choisies de André Chénier/Derocquigny, 1907/Dryas`) [container]
    - … 75 more
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
1. `Principes de dressage et d’équitation`: subpages, book, 22 parts, 47 missing, order toc, proofread
    - toc: Principes de dressage et d’équitation
    - text: Dédicage (`Principes de dressage et d’équitation/Dédicage`) [container]
    - chapter: Préface (`Principes de dressage et d’équitation/Préface`) [container]
    - volume: PREMIERE PARTIE (`Principes de dressage et d’équitation/Partie I`) [container]
      - volume: DEUXIÈME PARTIE (`Principes de dressage et d’équitation/Partie II`) [container]
        - text: Le cheval (`Principes de dressage et d’équitation/Partie I/I`) [container]
        - text: Nourriture du cheval (`Principes de dressage et d’équitation/Partie I/II`) [container]
        - text: Intelligence du cheval (`Principes de dressage et d’équitation/Partie I/III`) [container]
        - text: Influence du regard de l’homme sur le cheval (`Principes de dressage et d’équitation/Partie I/IV`) [container]
        - text: Influence de la voix de l’homme sur le cheval (`Principes de dressage et d’équitation/Partie I/V`) [container]
        - text: Les caresses (`Principes de dressage et d’équitation/Partie I/VI`) [container]
        - text: Les corrections (`Principes de dressage et d’équitation/Partie I/VII`) [container]
    - … 11 more
1. `Bibliothèque historique et militaire`: subpages, book, 138 parts, 6 missing, order scan-volumes, proofread
    - toc: Bibliothèque historique et militaire
    - volume: Essai sur la tactique des Grecs (`Bibliothèque historique et militaire/Essai sur la tactique des Grecs`) [hierarchy]
      - chapter: Préface (`Bibliothèque historique et militaire/Essai sur la tactique des Grecs/Préface`) [hierarchy]
      - chapter: Chapitre I (`Bibliothèque historique et militaire/Essai sur la tactique des Grecs/Chapitre I`) [hierarchy]
      - chapter: Chapitre II (`Bibliothèque historique et militaire/Essai sur la tactique des Grecs/Chapitre II`) [hierarchy]
      - chapter: Chapitre III (`Bibliothèque historique et militaire/Essai sur la tactique des Grecs/Chapitre III`) [hierarchy]
      - chapter: Chapitre IV (`Bibliothèque historique et militaire/Essai sur la tactique des Grecs/Chapitre IV`) [hierarchy]
      - chapter: Chapitre V (`Bibliothèque historique et militaire/Essai sur la tactique des Grecs/Chapitre V`) [hierarchy]
      - chapter: Chapitre VI (`Bibliothèque historique et militaire/Essai sur la tactique des Grecs/Chapitre VI`) [hierarchy]
      - chapter: Chapitre VII (`Bibliothèque historique et militaire/Essai sur la tactique des Grecs/Chapitre VII`) [hierarchy]
      - chapter: Chapitre VIII (`Bibliothèque historique et militaire/Essai sur la tactique des Grecs/Chapitre VIII`) [hierarchy]
      - chapter: Chapitre IX (`Bibliothèque historique et militaire/Essai sur la tactique des Grecs/Chapitre IX`) [hierarchy]
    - … 127 more
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
1. `La Révolution surréaliste`: virtual, periodical-issue, 3 parts, 0 missing, order single, unproofread
    - volume: Table des numéros (`La Révolution surréaliste/Table des numéros`)
      - volume: numéro 9-10 (`La Révolution surréaliste/Table des numéros/numéro 9-10`) [hierarchy]
        - text: Revue de la Presse (`La Révolution surréaliste/Table des numéros/numéro 9-10/Revue de la Presse`) [hierarchy]
1. `Œuvres posthumes (Verlaine)`: mixed, collection, 199 parts, 0 missing, order scan-volumes, proofread
    - toc: Œuvres posthumes (Verlaine)
    - text: Œuvres posthumes, premier volume (`Œuvres posthumes (Verlaine)/Texte entier/Volume 1`) [container]
    - text: VERS DE JEUNESSE (`Œuvres posthumes (Verlaine)/Vers de jeunesse`) [container]
    - text: VARIA (`Œuvres posthumes (Verlaine)/Varia`) [container]
    - text: En 17 [container]
    - text: Éventail directoire [container]
    - text: Rotterdam [container]
    - text: Mort (Verlaine, posthume) [container]
    - chapter: Épilogue (Verlaine, posthume) [container]
    - text: PARALLÈLEMENT (`Œuvres posthumes (Verlaine)/Parallèlement`) [container]
    - text: Projet en l’air (Verlaine) [container]
    - text: Nous ne sommes pas le troupeau [container]
    - … 188 more
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
1. `Œuvres de Blaise Pascal`: mixed, collection, 233 parts, 62 missing, order scan-volumes, proofread
    - toc: Œuvres de Blaise Pascal
    - chapter: Introduction aux Œuvres de Blaise Pascal (`Œuvres de Blaise Pascal/Introduction`) [container]
    - volume: Biographies de Blaise Pascal et de son entourage (`Œuvres de Blaise Pascal/Biographies`) [container]
      - text: Étienne Pascal (`Œuvres de Blaise Pascal/Biographies/1`) [container]
      - text: Florin Perier (`Œuvres de Blaise Pascal/Biographies/2`) [container]
      - text: Gilberte Pascal (`Œuvres de Blaise Pascal/Biographies/3`) [container]
      - volume: Vie de Monsieur Pascal (`Œuvres de Blaise Pascal/Biographies/Vie de Monsieur Pascal`) [container]
        - chapter: Introduction à la Vie de Pascal par Gilberte Perier (`Œuvres de Blaise Pascal/Biographies/Vie de Monsieur Pascal/Introduction`) [container]
        - text: Vie (`Œuvres de Blaise Pascal/Biographies/Vie de Monsieur Pascal/Vie`) [container]
        - chapter: Appendice à la Vie de Monsieur Pascal par Gilberte Perier (`Œuvres de Blaise Pascal/Biographies/Vie de Monsieur Pascal/Appendice`) [container]
      - text: Blaise Pascal. Mémoires de Marguerite Perier (`Œuvres de Blaise Pascal/Biographies/Mémoire sur la vie de M. Pascal`) [hierarchy]
      - text: Relation anonyme de l’accident du pont de Neuilly (`Œuvres de Blaise Pascal/Biographies/6`) [container]
    - … 222 more
1. `La France juive`: subpages, book, 32 parts, 0 missing, order scan-volumes, proofread
    - toc: La France juive
    - chapter: Tome premier (`La France juive/Texte entier/Tome premier`) [container]
    - chapter: Introduction (`La France juive/Introduction`) [container]
    - chapter: LIVRE PREMIER. Le Juif (`La France juive/Livre Premier`) [container]
    - volume: LIVRE DEUXIÈME. Le Juif dans l’histoire de France (`La France juive/Livre Deuxième`) [container]
      - chapter: I (`La France juive/Livre Deuxième/I`) [container]
      - chapter: II (`La France juive/Livre Deuxième/II`) [container]
      - chapter: III (`La France juive/Livre Deuxième/III`) [container]
      - chapter: IV (`La France juive/Livre Deuxième/IV`) [container]
      - chapter: V (`La France juive/Livre Deuxième/V`) [container]
      - volume: VI (`La France juive/Livre Deuxième/VI`) [container]
        - chapter: 1 (`La France juive/Livre Deuxième/VI/1`) [container]
    - … 21 more
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
1. `Histoire du Privilége de Saint Romain`: subpages, book, 41 parts, 0 missing, order scan-volumes, proofread
    - front: Histoire du Privilége de Saint Romain
    - volume: Table vol 1 (`Histoire du Privilége de Saint Romain/Table vol 1`) [hierarchy]
      - chapter: Préface (`Histoire du Privilége de Saint Romain/Préface`) [container]
      - text: Dissertation sur l’origine du privilége de Saint Romain (`Histoire du Privilége de Saint Romain/Dissertation`) [container]
      - text: Histoire du privilége de saint Romain. — Première époque (`Histoire du Privilége de Saint Romain/Première époque`) [container]
      - text: 1193 à 1394 (`Histoire du Privilége de Saint Romain/1193 à 1394`) [container]
      - text: 1406 à 1431 (`Histoire du Privilége de Saint Romain/1406 à 1431`) [container]
      - text: 1434 à 1497 (`Histoire du Privilége de Saint Romain/1434 à 1497`) [container]
      - text: DEUXIÈME ÉPOQUE. (`Histoire du Privilége de Saint Romain/Deuxième époque`) [container]
      - text: 1499 à 1542 (`Histoire du Privilége de Saint Romain/1499 à 1542`) [container]
      - text: 1548 à 1574 (`Histoire du Privilége de Saint Romain/1548 à 1574`) [container]
      - text: 1575 à 1589 (`Histoire du Privilége de Saint Romain/1575 à 1589`) [container]
    - … 30 more
1. `Deux récits`: subpages, book, 22 parts, 0 missing, order single, validated
    - front: Deux récits
    - volume: À CARILLON (`Deux récits/À Carillon`) [container]
      - volume: DANS UN YACHT (`Deux récits/Dans un yacht`) [container]
        - text: Autrefois ! Aujourd’hui ! (`Deux récits/À Carillon/01`) [container]
        - text: Où l’on fait de nouvelles connaissances. (`Deux récits/À Carillon/02`) [container]
        - text: Débarquement de l’armée anglaise. (`Deux récits/À Carillon/03`) [container]
        - text: La première rencontre. (`Deux récits/À Carillon/04`) [container]
        - text: La grappe aux amours. (`Deux récits/À Carillon/05`) [container]
        - text: La débâcle. (`Deux récits/À Carillon/06`) [container]
        - text: La campagne de 1759. (`Deux récits/À Carillon/07`) [container]
        - text: Au mois de juillet de l’année dernière. (`Deux récits/Dans un yacht/01`) [container]
        - text: Québec, 4 août 186… (`Deux récits/Dans un yacht/02`) [container]
    - … 11 more
1. `Œuvres complètes de Laplace`: subpages, book, 139 parts, 0 missing, order scan-volumes, proofread
    - front: Œuvres complètes de Laplace
    - text: Avertissement (`Œuvres complètes de Laplace/Avertissement`) [hierarchy]
    - text: Notice sur le Général Marquis de Laplace (`Œuvres complètes de Laplace/Notice sur le Général Marquis de Laplace`) [hierarchy]
    - volume: Tome 08 (`Œuvres complètes de Laplace/Tome 08`) [container]
      - text: Mémoire sur les suites récurro-récurrentes et sur leurs usages dans la théorie des hasards (`Œuvres complètes de Laplace/Mémoire sur les suites récurro-récurrentes et sur leurs usages dans la théorie des hasards`) [container]
      - text: Mémoire sur la probabilité des causes par les événements (`Œuvres complètes de Laplace/Mémoire sur la probabilité des causes par les événements`) [container]
      - text: Recherches sur l’intégration des équations différentielles aux différences finies et sur leur usage dans la théorie des hasards (`Œuvres complètes de Laplace/Recherches sur l’intégration des équations différentielles aux différences finies et sur leur usage dans la théorie des hasards`) [container]
      - text: Sur le principe de la gravitation universelle et sur les inégalités séculaires des planètes qui en dépendent (`Œuvres complètes de Laplace/Sur le principe de la gravitation universelle et sur les inégalités séculaires des planètes qui en dépendent`) [container]
      - text: Mémoire sur l’inclinaison moyenne des orbites des comètes, sur la figure de la Terre et sur les fonctions (`Œuvres complètes de Laplace/Mémoire sur l’inclinaison moyenne des orbites des comètes, sur la figure de la Terre et sur les fonctions`) [container]
      - text: Mémoire sur les solutions particulières des équations différentielles et sur les inégalités séculaires des planètes (`Œuvres complètes de Laplace/Mémoire sur les solutions particulières des équations différentielles et sur les inégalités séculaires des planètes`) [container]
      - volume: Recherches sur le Calcul intégral et sur le système du monde (`Œuvres complètes de Laplace/Recherches sur le Calcul intégral et sur le système du monde`) [container]
        - text: Additions (`Œuvres complètes de Laplace/Recherches sur le Calcul intégral et sur le système du monde/Additions`) [container]
    - … 128 more
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
1. `Voyage en Orient (Nerval)`: mixed, book, 205 parts, 0 missing, order scan-volumes, validated
    - toc: Voyage en Orient (Nerval)
    - chapter: Tome 1 (`Voyage en Orient (Nerval)/Texte entier/Tome 1`) [container]
    - volume: INTRODUCTION (`Voyage en Orient (Nerval)/Introduction`) [container]
      - chapter: I (`Voyage en Orient (Nerval)/Introduction/I`) [container]
      - chapter: II (`Voyage en Orient (Nerval)/Introduction/II`) [container]
      - chapter: III (`Voyage en Orient (Nerval)/Introduction/III`) [container]
      - chapter: IV (`Voyage en Orient (Nerval)/Introduction/IV`) [container]
      - chapter: V (`Voyage en Orient (Nerval)/Introduction/V`) [container]
      - chapter: VI (`Voyage en Orient (Nerval)/Introduction/VI`) [container]
      - chapter: VII (`Voyage en Orient (Nerval)/Introduction/VII`) [container]
      - chapter: VIII (`Voyage en Orient (Nerval)/Introduction/VIII`) [container]
      - chapter: IX (`Voyage en Orient (Nerval)/Introduction/IX`) [container]
    - … 194 more
1. `Les Misérables`: mixed, book, 418 parts, 0 missing, order scan-volumes, validated
    - front: Les Misérables
    - volume: Tome 1 (`Les Misérables/Tome 1`) [hierarchy]
      - volume: LIVRE PREMIER (`Les Misérables/Tome 1/Livre 1`) [container]
        - chapter: 01 (`Les Misérables/Tome 1/Livre 1/01`) [container]
        - chapter: 02 (`Les Misérables/Tome 1/Livre 1/02`) [container]
        - chapter: 03 (`Les Misérables/Tome 1/Livre 1/03`) [container]
        - chapter: 04 (`Les Misérables/Tome 1/Livre 1/04`) [container]
        - chapter: 05 (`Les Misérables/Tome 1/Livre 1/05`) [container]
        - chapter: 06 (`Les Misérables/Tome 1/Livre 1/06`) [container]
        - chapter: 07 (`Les Misérables/Tome 1/Livre 1/07`) [container]
        - chapter: 08 (`Les Misérables/Tome 1/Livre 1/08`) [container]
        - chapter: 09 (`Les Misérables/Tome 1/Livre 1/09`) [container]
    - … 407 more
1. `Mémoires (Saint-Simon)`: subpages, book, 488 parts, 0 missing, order chain, unproofread
    - front: Mémoires (Saint-Simon)
    - volume: Tome 1 (`Mémoires (Saint-Simon)/Tome 1`) [container]
      - chapter: Introduction (`Mémoires (Saint-Simon)/Tome 1/Introduction`) [container]
      - text: écrite par Saint-Simon à M. de Rancé (`Mémoires (Saint-Simon)/Tome 1/Lettre écrite par Saint-Simon à M. de Rancé`) [container]
      - chapter: Introduction de l’auteur (`Mémoires (Saint-Simon)/Tome 1/Introduction de l’auteur`) [container]
      - chapter: 1 (`Mémoires (Saint-Simon)/Tome 1/1`) [container]
      - chapter: 2 (`Mémoires (Saint-Simon)/Tome 1/2`) [container]
      - chapter: 3 (`Mémoires (Saint-Simon)/Tome 1/3`) [container]
      - chapter: 4 (`Mémoires (Saint-Simon)/Tome 1/4`) [container]
      - chapter: 5 (`Mémoires (Saint-Simon)/Tome 1/5`) [container]
      - chapter: 6 (`Mémoires (Saint-Simon)/Tome 1/6`) [container]
      - chapter: 7 (`Mémoires (Saint-Simon)/Tome 1/7`) [container]
    - … 477 more
1. `Lettres d’Abélard et d’Héloïse`: subpages, book, 12 parts, 9 missing, order single, unproofread
    - toc: Lettres d’Abélard et d’Héloïse
    - volume: Tome I (`Lettres d’Abélard et d’Héloïse/Tome 1`) [hierarchy]
      - volume: Tome 2 (`Lettres d’Abélard et d’Héloïse/Tome 2`) [container]
        - chapter: Introduction (`Lettres d’Abélard et d’Héloïse/Tome 1/Introduction`) [container]
        - chapter: Lettre première (`Lettres d’Abélard et d’Héloïse/Tome 1/Lettre première`) [container]
        - chapter: Lettre deuxième (`Lettres d’Abélard et d’Héloïse/Tome 1/Lettre deuxième`) [container]
        - chapter: Lettre troisième (`Lettres d’Abélard et d’Héloïse/Tome 1/Lettre troisième`) [container]
        - chapter: Lettre quatrième (`Lettres d’Abélard et d’Héloïse/Tome 1/Lettre quatrième`) [container]
        - chapter: Lettre cinquième (`Lettres d’Abélard et d’Héloïse/Tome 1/Lettre cinquième`) [container]
        - chapter: Lettre sixième (`Lettres d’Abélard et d’Héloïse/Tome 1/Lettre sixième`) [container]
        - chapter: Lettre septième (`Lettres d’Abélard et d’Héloïse/Tome 1/Lettre septième`) [container]
        - chapter: Lettre huitième (`Lettres d’Abélard et d’Héloïse/Tome 2/Lettre huitième`) [container]
    - … 1 more
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

### I: works with other editions (579)

1. `Contes d’Andersen`: subpages, collection, 24 parts, 0 missing, order toc, proofread
    - toc: Contes d’Andersen
    - text: Notice sur Andersen (`Contes d’Andersen/Notice sur Andersen`) [container]
    - text: L’Intrépide Soldat de plomb (`Contes d’Andersen/L’Intrépide Soldat de plomb`) [container]
    - text: Les Habits neufs du Grand-Duc (`Contes d’Andersen/Les Habits neufs du Grand-Duc`) [container]
    - text: La Bergère et le Ramoneur (`Contes d’Andersen/La Bergère et le ramoneur`) [container]
    - text: Le Briquet (`Contes d’Andersen/Le Briquet`) [container]
    - text: L’Ange (`Contes d’Andersen/L’Ange`) [container]
    - text: Petit Claus et grand Claus (`Contes d’Andersen/Petit Claus et Grand Claus`) [container]
    - text: La Princesse sur un pois (`Contes d’Andersen/La Princesse sur un pois`) [container]
    - text: Le jardin du Paradis (`Contes d’Andersen/Le Jardin du Paradis`) [container]
    - text: La Grosse aiguille (`Contes d’Andersen/La Grosse Aiguille`) [container]
    - text: Les Fleurs de la petite Ida (`Contes d’Andersen/Les Fleurs de la petite Ida`) [container]
    - … 13 more
1. `Le Dix-Huitième Siècle (1776)`: single, book, 1 parts, 0 missing, order –, unproofread
    - text: Le Dix-Huitième Siècle (1776)
1. `Poésies (Mallarmé)`: virtual, collection, 36 parts, 0 missing, order natural, unproofread
    - volume: Édition 1887 (`Poésies (Mallarmé)/Édition 1887`)
      - text: le Guignon (`Le Guignon (Stéphane Mallarmé) (1887)`) [container]
      - text: Apparition (`Apparition (Stéphane Mallarmé) (1887)`) [container]
      - text: Placet futile (`Placet futile (1887)`) [container]
      - text: le Pitre Châtié (`Le Pitre châtié (1887)`) [container]
      - text: les Fenêtres (`Les Fenêtres (Stéphane Mallarmé) (1887)`) [container]
      - text: les Fleurs (`Les Fleurs (Stéphane Mallarmé) (1887)`) [container]
      - text: Renouveau (`Renouveau (Stéphane Mallarmé) (1887)`) [container]
      - text: Angoisse (`Angoisse (1887)`) [container]
      - text: « Las de l’amer repos où ma paresse offense » (`« Las de l’amer repos où ma paresse offense » (1887)`) [container]
      - text: le Sonneur (`Le Sonneur (1887)`) [container]
      - text: Tristesse d’Été (`Tristesse d’été (1887)`) [container]
    - … 24 more
1. `Au bon soleil`: virtual, book, 1 parts, 0 missing, order single, proofread
    - text: Mon ami Naz (`Au bon soleil/Mon ami Naz`)
1. `Le Collier de griffes`: subpages, collection, 79 parts, 0 missing, order toc, proofread
    - toc: Le Collier de griffes
    - volume: VISIONS (`Le Collier de griffes/Visions`) [container]
      - chapter: Avant-propos (`Le Collier de griffes/Avant-Propos`) [container]
      - chapter: Préface (`Le Collier de griffes/Préface`) [container]
      - text: Inscription (`Le Collier de griffes/Visions/Inscription`) [container]
      - text: Déserteuses (`Le Collier de griffes/Visions/Déserteuses`) [container]
      - text: Conquérant (`Le Collier de griffes/Visions/Conquérant`) [container]
      - text: Phantasma (`Le Collier de griffes/Visions/Phantasma`) [container]
      - text: Chanson des peintres (`Le Collier de griffes/Visions/Chanson des peintres`) [container]
      - text: Pluriel féminin (`Le Collier de griffes/Visions/Pluriel féminin`) [container]
      - text: Maussaderie (`Le Collier de griffes/Visions/Maussaderie`) [container]
      - text: Évocation (`Le Collier de griffes/Visions/Évocation`) [container]
    - … 68 more
1. `L’Ensorceleuse (recueil)`: subpages, collection, 5 parts, 0 missing, order toc, validated
    - toc: L’Ensorceleuse (recueil)
    - text: L’Ensorceleuse (`L’Ensorceleuse (recueil)/L’Ensorceleuse`) [container]
    - text: Un cœur en loterie (`L’Ensorceleuse (recueil)/Un cœur en loterie`) [container]
    - text: Le Marchand de fantômes (`L’Ensorceleuse (recueil)/Le Marchand de fantômes`) [container]
    - text: Poisson d’avril (`L’Ensorceleuse (recueil)/Poisson d’avril`) [container]
    - text: La Nuit tragique (`L’Ensorceleuse (recueil)/La Nuit tragique`) [container]
1. `À la mère polonaise (traduction P.-N. Bonaparte)`: single, collection, 1 parts, 0 missing, order –, validated
    - text: À la mère polonaise (traduction P.-N. Bonaparte)
1. `Œuvres complètes de Béranger`: mixed, collection, 327 parts, 0 missing, order toc, proofread
    - toc: Œuvres complètes de Béranger
    - chapter: Préface de l’auteur (`Œuvres complètes de Béranger/Préface de 1833`) [container]
    - text: Notice (`Œuvres complètes de Béranger/Notice`) [container]
    - chapter: Préface (novembre 1815) (`Œuvres complètes de Béranger/Préface (novembre 1815)`) [container]
    - text: Conversation entre mon censeur et moi (`Œuvres complètes de Béranger/Conversation entre mon censeur et moi`) [container]
    - text: Le Voisin (`Œuvres complètes de Béranger/Le Voisin`) [container]
    - text: À M. Lucien Bonaparte, prince de Canino (`Œuvres complètes de Béranger/À M. Lucien Bonaparte, prince de Canino`) [container]
    - chapter: Notes sur les Procès faits aux chansons de P.-J. de Béranger (`Procès faits aux chansons de P.-J. de Béranger/Notes`) [container]
    - text: Au lecteur impartial (`Procès faits aux chansons de P.-J. de Béranger/Au lecteur impartial`) [container]
    - text: Procès à M. de Béranger (`Procès faits aux chansons de P.-J. de Béranger/Premier procès`) [container]
    - text: Pièces justificatives (`Procès faits aux chansons de P.-J. de Béranger/Pièces justificatives`) [container]
    - text: Arrêt de renvoi (`Procès faits aux chansons de P.-J. de Béranger/Arrêt de renvoi`) [container]
    - … 316 more
1. `Constitution du Grand-Duché de Luxembourg de 1856`: single, book, 1 parts, 0 missing, order –, unproofread
    - text: Constitution du Grand-Duché de Luxembourg de 1856
1. `Constitution fédérale de la Confédération suisse du 29 mai 1874`: single, book, 1 parts, 0 missing, order –, proofread
    - text: Constitution fédérale de la Confédération suisse du 29 mai 1874
1. `Phédon (trad. Robin)`: subpages, book, 1 parts, 0 missing, order single, validated
    - front: Phédon (trad. Robin)
    - text: Notice du Phédon (`Phédon (trad. Robin)/Notice`) [hierarchy]
1. `Le Livre des Psaumes (traduction juxtalinéaire hébreu - français)`: subpages, book, 9 parts, 141 missing, order toc, unproofread
    - front: Le Livre des Psaumes (traduction juxtalinéaire hébreu - français)
    - chapter: 1 (`Le Livre des Psaumes (traduction juxtalinéaire hébreu - français)/Psaume 1`) [hierarchy]
    - chapter: 2 (`Le Livre des Psaumes (traduction juxtalinéaire hébreu - français)/Psaume 2`) [hierarchy]
    - chapter: 3 (`Le Livre des Psaumes (traduction juxtalinéaire hébreu - français)/Psaume 3`) [hierarchy]
    - chapter: 13 (`Le Livre des Psaumes (traduction juxtalinéaire hébreu - français)/Psaume 13`) [hierarchy]
    - chapter: 23 (`Le Livre des Psaumes (traduction juxtalinéaire hébreu - français)/Psaume 23`) [hierarchy]
    - chapter: 47 (`Le Livre des Psaumes (traduction juxtalinéaire hébreu - français)/Psaume 47`) [hierarchy]
    - chapter: 100 (`Le Livre des Psaumes (traduction juxtalinéaire hébreu - français)/Psaume 100`) [hierarchy]
    - chapter: 111 (`Le Livre des Psaumes (traduction juxtalinéaire hébreu - français)/Psaume 111`) [hierarchy]
    - chapter: 133 (`Le Livre des Psaumes (traduction juxtalinéaire hébreu - français)/Psaume 133`) [hierarchy]
1. `La Source et origine des cons sauvages`: subpages, book, 8 parts, 0 missing, order scan, proofread
    - toc: La Source et origine des cons sauvages
    - chapter: 01 (`La Source et origine des cons sauvages/01`) [container]
    - chapter: 02 (`La Source et origine des cons sauvages/02`) [container]
    - chapter: 03 (`La Source et origine des cons sauvages/03`) [container]
    - chapter: 04 (`La Source et origine des cons sauvages/04`) [container]
    - chapter: 05 (`La Source et origine des cons sauvages/05`) [container]
    - chapter: 06 (`La Source et origine des cons sauvages/06`) [container]
    - chapter: 07 (`La Source et origine des cons sauvages/07`) [container]
    - chapter: 08 (`La Source et origine des cons sauvages/08`) [container]
1. `La Commune de Paris (Kropotkine, 1937)`: single, book, 1 parts, 0 missing, order –, validated
    - text: La Commune de Paris (Kropotkine, 1937)
1. `Proverbes dramatiques`: subpages, collection, 105 parts, 0 missing, order toc, proofread
    - toc: Proverbes dramatiques
    - chapter: Préface (`Proverbes dramatiques/Préface`) [container]
    - text: Le Maître Ballets (`Proverbes dramatiques/Le Maître de Ballets`) [container]
    - text: Les deux Anglois (`Proverbes dramatiques/Les deux Anglais`) [container]
    - text: Le Poulet (`Proverbes dramatiques/Le Poulet`) [container]
    - text: Le Sourd (`Proverbes dramatiques/Le Sourd`) [container]
    - text: Le Suisse malade (`Proverbes dramatiques/Le Suisse malade`) [container]
    - text: L’Après-dînée (`Proverbes dramatiques/L’Après-dîner`) [container]
    - text: Les Faux indifférens (`Proverbes dramatiques/Les Faux Indifférents`) [container]
    - text: Le Portrait (`Proverbes dramatiques/Le Portrait`) [container]
    - text: Les deux Amis (`Proverbes dramatiques/Les Deux Amis`) [container]
    - text: Alménorade, Tragédie (`Proverbes dramatiques/Alménorade`) [container]
    - … 94 more
1. `Gustave III (éd. Dentu)`: single, collection, 1 parts, 0 missing, order –, proofread
    - text: Gustave III (éd. Dentu)
1. `La Reine Mab (Shelley, trad. Hugo)`: single, collection, 1 parts, 0 missing, order –, validated
    - text: La Reine Mab (Shelley, trad. Hugo)
1. `Essais/exemplaire de Bordeaux, 1588-1592`: linked, book, 118 parts, 0 missing, order scan-volumes, unproofread
    - toc: Essais/exemplaire de Bordeaux, 1588-1592
    - text: « C’est amour, c’est amour, c’est lui seul, je le sens » [container]
    - text: « PARDON AMOUR, pardon, ô Seigneur je te voue » [container]
    - text: « C’était alors, quand, les chaleurs passées, » [container]
    - text: « Quand à chanter ton los, parfois je m’aventure » [container]
    - text: 30D’un enfant monstrueux (`Essais/Livre II/Chapitre 30`) [container]
    - text: 31De la colere (`Essais/Livre II/Chapitre 31`) [container]
    - text: 32Deffense de Seneque & de Plutarque (`Essais/Livre II/Chapitre 32`) [container]
    - text: 33L’Histoire de Spurina (`Essais/Livre II/Chapitre 33`) [container]
    - text: 34Observations sur les moyens de faire la guerre de Jules Cæsar (`Essais/Livre II/Chapitre 34`) [container]
    - text: 35De trois bonnes femmes (`Essais/Livre II/Chapitre 35`) [container]
    - text: 36Des plus excellens hommes (`Essais/Livre II/Chapitre 36`) [container]
    - … 107 more
1. `Les Chimères`: subpages, collection, 8 parts, 0 missing, order toc, validated
    - toc: Les Chimères
    - text: El Desdichado (`Les Chimères/El Desdichado`) [hierarchy]
    - text: Myrtho (`Les Chimères/Myrtho`) [hierarchy]
    - text: Horus (`Les Chimères/Horus`) [hierarchy]
    - text: Antéros (`Les Chimères/Antéros`) [hierarchy]
    - text: Delfica (`Les Chimères/Delfica`) [hierarchy]
    - text: Artémis (`Les Chimères/Artémis`) [hierarchy]
    - text: Le Christ aux Oliviers (`Les Chimères/Le Christ aux Oliviers`) [hierarchy]
    - text: Vers dorés (`Les Chimères/Vers dorés`) [hierarchy]
1. `Les Quatre Livres`: linked, book, 34 parts, 0 missing, order toc, unproofread
    - toc: Les Quatre Livres
    - chapter: Préface du traducteur (`Les Quatre livres/Préface du traducteur`) [container]
    - text: La Grande étude (`Les Quatre livres/La Grande étude`) [container]
    - text: L’Invariable milieu (`Les Quatre livres/L’Invariable milieu`) [container]
    - text: Entretiens de Confucius (`Les Quatre livres/Entretiens de Confucius`) [container]
    - chapter: INTRODUCTION (`Les Quatre livres/Entretiens de Confucius/Introduction`) [container]
    - chapter: CHAPITRE I. HIO EUL. (`Les Quatre livres/Entretiens de Confucius/01`) [container]
    - chapter: CHAPITRE II. WEI TCHENG. (`Les Quatre livres/Entretiens de Confucius/02`) [container]
    - chapter: CHAPITRE III. PA I. (`Les Quatre livres/Entretiens de Confucius/03`) [container]
    - chapter: CHAPITRE IV. LI JENN. (`Les Quatre livres/Entretiens de Confucius/04`) [container]
    - chapter: CHAPITRE V. KOUNG IE TCH’ANG. (`Les Quatre livres/Entretiens de Confucius/05`) [container]
    - chapter: CHAPITRE VI. IOUNG IE. (`Les Quatre livres/Entretiens de Confucius/06`) [container]
    - … 23 more
1. `Les Contes de ma mère l’Oye avant Perrault`: subpages, collection, 28 parts, 0 missing, order toc, unproofread
    - toc: Les Contes de ma mère l’Oye avant Perrault
    - chapter: Introduction (`Les Contes de ma mère l’Oye avant Perrault/Introduction`) [container]
    - text: La Marquise de Salusses ou la Patience de Griselidis (`Les Contes de ma mère l’Oye avant Perrault/La Marquise de Salusses ou la Patience de Griselidis`) [container]
    - text: Les Souhaits ridicules (`Les Contes de ma mère l’Oye avant Perrault/Les Souhaits ridicules`) [container]
    - text: Peau d’Âne (`Les Contes de ma mère l’Oye avant Perrault/Peau d’Âne`) [container]
    - text: L’Ourse (`Les Contes de ma mère l’Oye avant Perrault/L’Ourse`) [container]
    - text: Peau-de·Toutes·Bêtes (`Les Contes de ma mère l’Oye avant Perrault/Peau-de·Toutes·Bêtes`) [container]
    - text: La Belle au bois dormant (`Les Contes de ma mère l’Oye avant Perrault/La Belle au bois dormant`) [container]
    - text: Rose·des-Bois (`Les Contes de ma mère l’Oye avant Perrault/Rose·des-Bois`) [container]
    - text: Le Soleil, la Lune et Thalie (`Les Contes de ma mère l’Oye avant Perrault/Le Soleil, la Lune et Thalie`) [container]
    - text: Le Petit Chaperon rouge (`Les Contes de ma mère l’Oye avant Perrault/Le Petit Chaperon rouge`) [container]
    - text: Le Petit Chaperon rouge (`Les Contes de ma mère l’Oye avant Perrault/Le Petit Chaperon rouge (Grimm - Deulin)`) [container]
    - … 17 more
1. `Chansons de France pour les petits Français`: subpages, collection, 2 parts, 6 missing, order toc, unproofread
    - toc: Chansons de France pour les petits Français
    - text: Compère Guilleri (`Chansons de France pour les petits Français/Compère Guilleri`) [container]
    - text: Cadet Rousselle (`Chansons de France pour les petits Français/Cadet Rousselle`) [container]
1. `Les Faux saulniers (1868)`: virtual, collection, 2 parts, 0 missing, order scan, proofread
    - text: Angélique (`Les Faux saulniers (1868)/Angélique`)
    - text: Histoire de l’abbé de Bucquoy (`Les Faux saulniers (1868)/Histoire de l’abbé de Bucquoy`) [hierarchy]
1. `L’Encyclopédie`: virtual, reference, 41,677 parts, 0 missing, order single, proofread
    - volume: 1re édition (`L’Encyclopédie/1re édition`)
      - chapter: I (`L’Encyclopédie/1re édition/I`) [container]
      - chapter: V (`L’Encyclopédie/1re édition/V`) [container]
      - chapter: X (`L’Encyclopédie/1re édition/X`) [container]
      - chapter: L (`L’Encyclopédie/1re édition/L`) [container]
      - chapter: C (`L’Encyclopédie/1re édition/C`) [container]
      - chapter: D (`L’Encyclopédie/1re édition/D`) [container]
      - chapter: DIX (`L’Encyclopédie/1re édition/DIX`) [container]
      - chapter: M (`L’Encyclopédie/1re édition/M`) [container]
      - chapter: MI (`L’Encyclopédie/1re édition/MI`) [container]
      - text: ACTES d’Archélaus (`L’Encyclopédie/1re édition/ACTES d’Archélaus`) [container]
      - text: AFFABILITÉ (`L’Encyclopédie/1re édition/AFFABILITÉ`) [container]
    - … 41,665 more
1. `La dernière Aldini (Hetzel, 1855)`: mixed, book, 3 parts, 0 missing, order scan, unproofread
    - front: La dernière Aldini (Hetzel, 1855)
    - chapter: Première partie (`La dernière Aldini (Hetzel, 1855)/Première partie`) [container]
    - chapter: Deuxième partie (`La dernière Aldini (Hetzel, 1855)/Deuxième partie`) [container]
    - text: Simon (1855) [container]
1. `Le Libertin de qualité (1784)`: subpages, book, 2 parts, 0 missing, order chain, proofread
    - toc: Le Libertin de qualité (1784)
    - text: À Monsieur Satan (`Le Libertin de qualité (1784)/00`) [container]
    - text: Le Libertin de qualité (`Le Libertin de qualité (1784)/01`) [container]
1. `La Chanson de la Croisade contre les Albigeois — Principaux épisodes`: subpages, book, 29 parts, 0 missing, order toc, proofread
    - toc: La Chanson de la Croisade contre les Albigeois — Principaux épisodes
    - chapter: Avant-Propos (`La Chanson de la Croisade contre les Albigeois — Principaux épisodes/Avant-Propos`) [container]
    - text: Dates des principaux événements (`La Chanson de la Croisade contre les Albigeois — Principaux épisodes/Dates des principaux événements`) [container]
    - chapter: PREMIÈRE PARTIE par GUILHEM DE TUDELA (`La Chanson de la Croisade contre les Albigeois — Principaux épisodes/Première partie`) [container]
    - text: Assassinat de Pierre de Castelnau (`La Chanson de la Croisade contre les Albigeois — Principaux épisodes/I`) [container]
    - text: Sac de Béziers (`La Chanson de la Croisade contre les Albigeois — Principaux épisodes/II`) [container]
    - text: Prise de Carcassonne (`La Chanson de la Croisade contre les Albigeois — Principaux épisodes/III`) [container]
    - text: Vaines prédications (`La Chanson de la Croisade contre les Albigeois — Principaux épisodes/IV`) [container]
    - text: Prise de Termes (`La Chanson de la Croisade contre les Albigeois — Principaux épisodes/V`) [container]
    - text: Le parlement d’Arles (`La Chanson de la Croisade contre les Albigeois — Principaux épisodes/VI`) [container]
    - text: épisode du siège de Toulouse (`La Chanson de la Croisade contre les Albigeois — Principaux épisodes/VII`) [container]
    - chapter: DEUXIÈME PARTIE (ANONYME) (`La Chanson de la Croisade contre les Albigeois — Principaux épisodes/Deuxième partie`) [container]
    - … 18 more
1. `Le Mystère de la chambre jaune (Édition Lafitte 1910)`: subpages, book, 29 parts, 0 missing, order toc, proofread
    - toc: Le Mystère de la chambre jaune (Édition Lafitte 1910)
    - text: Où l’on commence à ne pas comprendre (`Le Mystère de la chambre jaune (Édition Lafitte 1910)/I`) [container]
    - text: Où apparaît pour la première fois Joseph Rouletabille (`Le Mystère de la chambre jaune (Édition Lafitte 1910)/II`) [container]
    - text: « Un homme a passé comme une ombre à travers les volets » (`Le Mystère de la chambre jaune (Édition Lafitte 1910)/III`) [container]
    - text: « Au sein d’une nature sauvage » (`Le Mystère de la chambre jaune (Édition Lafitte 1910)/IV`) [container]
    - text: Où Joseph Rouletabille adresse à une phrase qui produit son petit effet (`Le Mystère de la chambre jaune (Édition Lafitte 1910)/V`) [container]
    - text: Au fond de la chênaie (`Le Mystère de la chambre jaune (Édition Lafitte 1910)/VI`) [container]
    - text: Où Rouletabille part en expédition sous le lit (`Le Mystère de la chambre jaune (Édition Lafitte 1910)/VII`) [container]
    - text: Le juge d’instruction interroge (`Le Mystère de la chambre jaune (Édition Lafitte 1910)/VIII`) [container]
    - text: Reporter et policier (`Le Mystère de la chambre jaune (Édition Lafitte 1910)/IX`) [container]
    - text: « Maintenant il va falloir manger du saignant » (`Le Mystère de la chambre jaune (Édition Lafitte 1910)/X`) [container]
    - text: Où Frédéric Larsan explique comment l’assassin a pu sortir de la « Chambre Jaune » (`Le Mystère de la chambre jaune (Édition Lafitte 1910)/XI`) [container]
    - … 18 more
1. `Musique des chansons de Béranger`: subpages, collection, 345 parts, 0 missing, order toc, proofread
    - toc: Musique des chansons de Béranger
    - text: Le Roi d’Yvetot (`Musique des chansons de Béranger/Le Roi d’Yvetot`) [container]
    - text: La Bacchante (`Musique des chansons de Béranger/La Bacchante`) [container]
    - text: Le Sénateur (`Musique des chansons de Béranger/Le Sénateur`) [container]
    - text: L’Académie et le Caveau (`Musique des chansons de Béranger/L’Académie et le Caveau`) [container]
    - text: La Gaudriole (`Musique des chansons de Béranger/La Gaudriole`) [container]
    - text: Roger Bontemps (`Musique des chansons de Béranger/Roger Bontemps`) [container]
    - text: Parny n’est plus (`Musique des chansons de Béranger/Parny`) [container]
    - text: Ma Grand’Mère (`Musique des chansons de Béranger/Ma Grand’Mère`) [container]
    - text: Le Mort vivant (`Musique des chansons de Béranger/Le Mort vivant`) [container]
    - text: Le Printemps et l’Automne (`Musique des chansons de Béranger/Le Printemps et l’Automne`) [container]
    - text: La Mère aveugle (`Musique des chansons de Béranger/La Mère aveugle`) [container]
    - … 334 more
1. `Un jour (recueil, Jammes)`: subpages, collection, 20 parts, 0 missing, order toc, validated
    - toc: Un jour (recueil, Jammes)
    - text: Un Jour (`Un jour (recueil, Jammes)/Un Jour`) [container]
    - text: Il y a un petit cordonnier (`Un jour (recueil, Jammes)/Il y a un petit cordonnier…`) [container]
    - text: J’aime dans le temps (`Un jour (recueil, Jammes)/J’aime dans le temps…`) [container]
    - text: C’est aujourd’hui (`Un jour (recueil, Jammes)/C’est aujourd’hui…`) [container]
    - text: Confucius rendait les honneurs (`Un jour (recueil, Jammes)/Confucius rendait les honneurs…`) [container]
    - text: Je le trouvai (`Un jour (recueil, Jammes)/Je le trouvai…`) [container]
    - text: La vallée (`Un jour (recueil, Jammes)/La vallée…`) [container]
    - text: Il vint à l’étude (`Un jour (recueil, Jammes)/Il vint à l’étude`) [container]
    - text: Le pauvre chien (`Un jour (recueil, Jammes)/Le pauvre chien…`) [container]
    - text: Le vieux village (`Un jour (recueil, Jammes)/Le vieux village…`) [container]
    - text: Les badauds (`Un jour (recueil, Jammes)/Les Badauds…`) [container]
    - … 9 more

