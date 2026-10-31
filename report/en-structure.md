# en.wikisource: structure and quality survey (WP1a)

Generated 2026-10-31 by `python -I -m wscatalog.survey en` from `enwikisource-20261001-pages-articles1.xml-p1p1500000.bz2, enwikisource-20261001-pages-articles1.xml-p1500001p3000000.bz2, enwikisource-20261001-pages-articles1.xml-p3000001p4500000.bz2, enwikisource-20261001-pages-articles1.xml-p4500001p5244985.bz2` (resolution and quality rules: PLAN.md 5.2 steps 1–1h and 5.4; config `pipeline/config/en.yaml`). Resolution took 45 s on the cached extract.

Input: 645,616 ns0 pages, 134,282 ns0 redirects, 3,810,670 scan pages, 42,655 Index pages, 8,573 pages with the `disambiguation` page prop.

## 1. Works

56,742 works resolved from 630,739 non-excluded ns0 pages; 55,464 enter the catalog after the hard exclusions (section 9). Multi-part works hold 574,821 parts, of which 430,186 are searchable texts.

| structure | works | parts | texts |
|---|---|---|---|
| single | 38,256 | 38,256 | 0 |
| subpages | 17,521 | 521,534 | 387,305 |
| virtual | 824 | 3,363 | 2,061 |
| mixed | 85 | 48,460 | 39,956 |
| linked | 56 | 1,464 | 864 |

| type (step 1e) | works |
|---|---|
| single | 29,653 |
| book | 18,897 |
| collection | 7,518 |
| document | 382 |
| periodical-issue | 257 |
| reference | 35 |

Largest works by parts:

| work | structure | type | parts | texts | missing | rules of the parts |
|---|---|---|---|---|---|---|
| Alumni Oxonienses: the Members of the University of Oxford, 1715-1886 | subpages | reference | 63,225 | 63,216 | 0 | hierarchy 63197, container 28 |
| 1911 Encyclopædia Britannica | subpages | reference | 37,420 | 37,256 | 0 | hierarchy 37387, container 33 |
| Dictionary of National Biography, 1885-1900 | mixed | reference | 28,089 | 28,080 | 2 | hierarchy 28021, container 68 |
| Catholic Encyclopedia (1913) | subpages | reference | 11,629 | 11,584 | 3 | hierarchy 11609, container 20 |
| The New International Encyclopædia | subpages | book | 10,866 | 10,851 | 0 | hierarchy 10863, container 3 |
| An Etymological Dictionary of the German Language | subpages | book | 9,961 | 9,907 | 0 | container 9902, hierarchy 59 |
| Popular Science Monthly | subpages | reference | 8,174 | 7,607 | 15 | container 8163, hierarchy 11 |
| Men-at-the-Bar | subpages | book | 7,448 | 7,447 | 0 | hierarchy 7423, container 25 |
| Nicene and Post-Nicene Fathers: Series II | mixed | reference | 7,099 | 1,377 | 1 | container 7099 |
| Ante-Nicene Fathers | subpages | reference | 5,616 | 1,009 | 0 | container 5610, hierarchy 6 |
| Alumni Oxonienses: the Members of the University of Oxford, 1500-1714 | subpages | reference | 5,317 | 5,312 | 5 | hierarchy 5317 |
| Nicene and Post-Nicene Fathers: Series I | subpages | reference | 5,287 | 867 | 1 | container 5287 |
| A Dictionary of Music and Musicians | subpages | reference | 5,197 | 5,165 | 7 | hierarchy 5131, container 66 |
| A Naval Biographical Dictionary | subpages | reference | 4,955 | 4,953 | 1 | hierarchy 4945, container 10 |
| United States Statutes at Large | mixed | reference | 4,888 | 4,686 | 721 | container 4815, hierarchy 73 |

## 2. Exclusions (step 1)

| reason | pages |
|---|---|
| disambiguation | 7,627 |
| versions | 7,250 |

## 3. Patterns A–I (WS§1)

|  | pattern | count |
|---|---|---|
| A | collection of root pages (`structure = linked`) | 56 works, 1,464 parts |
| B | mixed subpages and root pages (`mixed`) | 85 works, 48,460 parts |
| C | work with an oversized page (> oversized_bytes, transcluded scan pages included) | 399 works |
| D | missing root, virtual root (`virtual`) | 824 works, 3,363 parts |
| E | texts inside a subpage collection reached from a disambiguation or versions page | 25,458 parts (24,014 searchable); all searchable subpage texts: 428,897 |
| F | whole-text duplicate subpages (excluded) | 0 pages |
| G | reference works (type `reference`) | 35 works, 212,280 parts; 0 pages excluded by title prefix |
| H | deep nesting (parts at depth ≥ 2, i.e. three levels below the root) | 312 works |
| I | several editions: works with an other-versions template | 3,557 works; 7,250 versions pages excluded |

Tree depth of multi-part works (levels below the root): 1: 16,981, 2: 1,193, 3: 254, 4: 38, 5: 12, 6: 6, 8: 1, 9: 1.

## 4. Parent rules and order sources (steps 1b, 1c)

| parent rule | pages |
|---|---|
| container | 315,060 |
| hierarchy | 259,760 |
| own | 55,918 |
| toc | 1 |

| order source (nodes with ≥ 2 children) | nodes |
|---|---|
| chain | 13,512 |
| toc | 2,975 |
| natural | 1,738 |
| scan | 1,178 |
| toc-partial | 411 |

`toc-partial` (implementation choice, see section 13): the TOC lists at least half of the children, which keep its order; the rest follow in natural order. 716 `natural` nodes have a TOC that lists fewer than half.

Root pages attached by their header's container link: 279; most frequent containers: `Notebook` 175; `Constitution of the Republic of China (1947)` 7; `Brazilian tales` 6; `Hague Conventions of 1899 and 1907` 5; `Constitution of the People's Republic of China (1982)` 5; `Sonnets attempted in the manner of Contemporary Writers` 3; `Itar-Tass Russian News Agency v. Russian Kurier, Inc.` 3; `Labour: The Divine Command` 3; `Public Law 110-343` 3; `Judicial Improvements Act of 1990` 3.

Pages attached by TOC membership (rule 3): 1; most frequent TOC pages: `Socrates (Voltaire)` 1.

## 5. Container links that fail rule 1

3,535 of 319,292 pages whose header carries a container link (1.1 %) point to:

- outside-ns0: 182 (e.g. `The Mathematical Principles of Natural Philosophy (1729)/Section 5` → `../../`, `The Wizardry of George H. Earle, Jr.` → `Author:George Howard Earle (1856-1928)`, `Constitution Act, 1867 (annotated)/Schedule 3` → `w:Constitution Act, 1867`, `On the Musical Service Held in Commemoration of James Russell Lowell` → `Author:James Russell Lowell`)
- excluded: 454 (e.g. `Beowulf (Harrison and Sharp)/Preface` → `Beowulf`, `The Phantom of the Opera (1911)/Chapter XXIII` → `The Phantom of the Opera`, `Songs of Innocence and of Experience (1826)/Songs of Experience/The Little Vagabond` → `Songs of Experience`, `The Journal of Speculative Philosophy/Volume 1/Number 3/Monadology` → `Monadology`)
- missing: 2,899 (e.g. `Travels in the Interior of Africa to the Sources of the Senegal and Gambia/Appendix/Observations on the Discoveries in Africa` → `../../`, `The Political Anatomy of Ireland (1899)/Chapter I` → `../The Political Anatomy of Ireland`, `Kosrae State Code/Title 6/Part 4/Chapter 41` → `{{ROOTPAGENAME}}`, `We (Zamyatin, Zilboorg translation)/Record 15` → `We (Zilboorg translation){{!}}We`)

## 6. Prev/next chains across Index boundaries

Of 221,547 next-links between pages that both have an Index, 2,054 (0.9 %) cross to a different Index.
Examples: `The Yellow Book/Volume 5` (The Yellow Book - 05.djvu) → `The Yellow Book/Volume 6` (The Yellow Book - 06.djvu); `United Nations Security Council Resolution 1838` (UN Security Council Resolution 1838.djvu) → `United Nations Security Council Resolution 1839` (UN Security Council Resolution 1839.djvu); `Poems (Tennyson, 1843)/Volume 1/Advert` (Poems - Tennyson (1843) - Volume 1 of 2.djvu) → `Poems (Tennyson, 1843)/Volume 2` (Poems - Tennyson (1843) - Volume 2 of 2.djvu); `CSRT Summary of Evidence memo for Mustafa Ahmed Al Hawsawi` (ISN10011.pdf) → `CSRT Summary of Evidence memo for Ahmed Khalfan Ghailani` (ISN10012.pdf).

Root-page works that sit on a prev/next chain but stay separate works (no container, not in a unique TOC; WS§8 item 5): 11,910.

## 7. Orphan trees, oversized pages, red links

**Orphan trees** (virtual roots): 824, holding 3,363 parts. Examples: `Council Regulation (EU) No 833` (1); `Der Freischütz (The Free-Shooter); A Lyric Folk-Drama` (1); `COUNCIL DECISION (CFSP) 2022` (17); `The Albatross Book of Living Verse` (1); `Jornal Brasileiro de Psiquiatria` (1); `The Bayonne Herald` (1); `Poems (Gage)` (1); `Scarsdale Inquirer` (2).

**Oversized pages** (> 300,000 bytes of wikitext including transcluded scan pages): 839. Largest: `Septuagint (Brenton 1879)/Psalms` 1.9 MB; `Matthew Henry's Commentary on the Whole Bible/Volume 4/Jeremiah` 1.8 MB; `Dictionary of Spoken Russian/English-Russian` 1.8 MB; `The Grammar of English Grammars/Part III` 1.8 MB; `Matthew Henry's Commentary on the Whole Bible/Volume 3/Psalms` 1.8 MB; `Copyright Law Revision (House Report No. 94-1476)/Annotated` 1.7 MB; `Septuagint (Brenton 1879)/Jeremias` 1.7 MB; `Virginia Resolutions of 1798/Delaware` 1.7 MB; `Septuagint (Brenton 1879)/Genesis` 1.6 MB; `Septuagint (Brenton 1879)/Jezekiel` 1.6 MB.

**Red links in TOCs** (missing parts): 1,983 multi-part works have red links, 108,198 in total; median share of the TOC 63%. Most missing: `Dictionary of Greek and Roman Biography and Mythology` 15683 of 16977; `Littell's Living Age` 7333 of 9175; `United States Code` 2633 of 3715; `Kansas: A Cyclopedia of State History` 2582 of 2708; `McClure's Magazine` 2087 of 2355; `Transactions and Proceedings of the New Zealand Institute` 1722 of 1823; `Federal Supplement` 1491 of 1501; `United Nations Treaty Series` 1399 of 1486.

## 8. Colon prefixes with ≥ 100 root pages

| prefix | root pages | status |
|---|---|---|
| Aviation Accident Report: | 107 | kept |

## 9. Quality and completeness (PLAN.md 5.4)

**Hard exclusions**: 1,278 works — no-text 573, no-text-pages 272, under-300-bytes 187, index-faulty 140, junk-template 106.

| tier | works | share | texts in them | source |
|---|---|---|---|---|
| validated | 6,951 | 12.5 % | 40,699 | page-levels 6,827, textquality 76, index-progress 48 |
| proofread | 12,428 | 22.4 % | 239,393 | page-levels 9,338, textquality 3,075, index-progress 15 |
| unproofread | 2,711 | 4.9 % | 53,033 | page-levels 2,025, textquality 675, index-progress 11 |
| unknown | 33,374 | 60.2 % | 94,926 | none 33,374 |

889 works are proofread by the ≥ 90 % rule. 1,226 of the 2,711 unproofread works have no page above level 1 (raw OCR).

**Incomplete**: 3,094 works — template 1,768, missing-parts 1,038, missing-pages 245, coverage 43.
Works with missing scan pages inside a transcluded range: 613 (37,409 pages).
Coverage was computed for 417 works that are their Index's book; 87 cover under 95 % of the Index's text pages.
24 works transclude scan pages of files the dump has no Page: pages for (counted as no signal, not as missing).
Wikisource's own translations (labelled, kept): 2,611.

Text-quality template values: `75%` 13,048, `50%` 1,802, `25%` 343, `100%` 245.
Index progress values: `C` 21,572, `V` 8,359, `T` 7,909, `L` 840, `OCR` 283, `MS` 37, `X` 1.
Works excluded as index-faulty, by the tier their page levels would give: proofread 72, unproofread 53, validated 10, none 5.

**Unknown works** (no signal): 33,374 in the catalog after the junk rules; the junk rules removed 293 (under-300-bytes 187, junk-template 106).
Size: median 6,103 bytes, p10 1,356, p90 69,277; multi-part 7,630.

30 random unknown works:

- `Thirty Charades` (subpages, 14,144 B): __noTOC__ block center\| ballad has rehearsed The placid waters of my first. The hero bold, his noble friend, The heroine's sad, untimely end, Who by a traitor w
- `August (Smith)` (single, 607 B): In silence now the purpling summer passes, The swallows fly; The failing river scantly glasses, Where amber twilights wane, Our dreaming kiss above the flow'rs 
- `Proclamation 7251` (single, 3,396 B): Tobacco use continues to be the leading preventable cause of death and disease in the United States, costing more than 400,000 lives and $50 billion in medical 
- `Matters v. Ryan` (subpages, 7,411 B): Messrs. Horace Kent Tenney, Roger Sherman, and Harry A. Parkin, all of Chicago, Ill., for appellant. Mr. Chief Justice WHITE delivered the opinion of the Court.
- `Address to the Knights of Columbus` (single, 36,482 B): Four centuries and a quarter have gone by since Columbus by discovering America opened the greatest era in world history. Four centuries have passed since the S
- `The Pathfinder` (subpages, 995,721 B): Table of Contents== Preface Chapter 1 Chapter 2 Chapter 3 Chapter 4 Chapter 5 Chapter 6 Chapter 7 Chapter 8 Chapter 9 Chapter 10 Chapter 11 Chapter 12 Chapter 1
- `Trist v. Child` (subpages, 26,935 B): APPEAL from the Supreme Court of the District of Columbia; the case being thus: N. P. Trist having a claim against the United States for his services, rendered 
- `Presidential Radio Address - 8 April 1995` (single, 5,849 B): Good morning. I ran for President because I believed the American dream was at risk for millions of our fellow citizens. I wanted to grow the middle class, shri
- `Prince Bull` (single, 12,631 B): Once upon a time, and of course it was in the Golden Age, and I hope you may know when that was, for I am sure I don't, though I have tried hard to find out, th
- `I am the Spirit of War! ...` (single, 597 B): I am the Spirit of War! My hands are red with gore. They know my heel Where the clash of steel Matches the cannon's roar! My hands are gory red Ranks crumple be
- `The Four-Leafed Clover` (single, 15,441 B): the world knew that the Princess Cyrilla was the rightful heiress to the throne, but no one in all the wide world dared to say so. If any person had been so bol
- `Sheffield Rules (October 1867)` (single, 5,474 B): Rules== I. The maximum length of ground shall be 200 yards; the maximum breadth shall be 100 yards; the length and breadth shall be marked off with rouge flags;
- `Israel Potter` (subpages, 431,208 B): Dedication TO HIS HIGHNESS THE Bunker-Hill Monument Biography, in its purer form, confined to the ended lives of the true and brave, may be held the fairest mee
- `United Nations Security Council Resolution 1573` (single, 4,908 B): Adopted unanimously by the Security Council at its 5079th meeting, on 16 November 2004 The Security Council, Reaffirming its previous resolutions on the situati
- `Central Pacific Company v. Nevada` (subpages, 29,861 B): This (No. 170) was an action originally begun in the district court of Lander county by the state of Nevada against the Central Pacific Railroad Company and its
- `2013 Human Rights Report: China (includes Tibet, Hong Kong, and Macau)` (subpages, 382,984 B): 2013 Human Rights Report: China (includes Tibet, Hong Kong, and Macau) China = Hong Kong \| Macau \| Tibet EXECUTIVE SUMMARY == The People’s Republic of China (PR
- `Proclamation 5318` (single, 2,779 B): The countries of the Western Hemisphere are bound together by their humanitarian ideals, their respect for individual liberty, and their yearning for peace and 
- `Lad, A Dog` (subpages, 463,845 B): Author’s Dedication== My book is dedicated To The Memory Of Lad Thoroughbred in body and soul Contents== Chapter I. His Mate Chapter II. “Quiet!” Chapter III. A
- `The Benevolent Liar` (subpages, 286,402 B): Category:Western fiction Category:mystery novels Category:adventure novels Category:romance Category:novels in periodicals
- `NSTC-4` (single, 16,993 B): This directive establishes national policy, guidelines, and implementing actions for the conduct of National space transportation programs that will sustain and
- `Forcible Entry Act 1429` (single, 13,837 B): Revised version of the original text== Item come p le Roy Richard nadgairs Roi Dengletre puis le conquest scde, a son plement tenuz a Westm lendemain des Almes 
- `United States v. Bass` (subpages, 46,525 B): Respondent was convicted of possessing firearms in violation of § 1202 of the Omnibus Crime Control and Safe Streets Act, which provides that a person convicted
- `Proclamation 6768` (single, 3,825 B): Throughout history, the heart has been a symbol of health and well-being. Yet nothing now overshadows Americans' health as much as heart disease-the leading cau
- `Remarks on President-elect Barack Obama's resignation letter` (single, 1,638 B): Mikulski: Mister President, I ask unaminous consent that the Senate recess today from 2:30 to 4:00 to allow the Senators to attend today's briefing with Secreta
- `Acknowledging and Commending National Library Week` (single, 2,994 B): Mr. BLUMENAUER. Mr. Speaker, from April 12-18, 2009, our nation celebrated National Library Week and the vital role that these institutions and their dedicated 
- `Acts of Paul and Thecla (Jeremiah Jones translation)` (single, 37,280 B): THE LIFE OF THE HOLY MARTYR THECLA OF ICONIUM, EQUAL TO THE APOSTLES== NOTE IN THE 1820 EDITION EDITED BY WILLIAM HONE== Tertullian says that this piece was for
- `Proclamation 7299` (single, 3,782 B): Over the last two centuries, Asian Americans and Pacific Islanders have contributed immeasurably to the richness of our dynamic, multicultural society. Whether 
- `United Nations Security Council Resolution 218` (single, 4,738 B): Adopted by the Security Council at its 1268th meeting, by 7 votes to none, with 4 abstentions (France, Netherlands, United Kingdom of Great Britain and Northern
- `J. R. L.` (single, 3,478 B): fifty years, how many frosty polls We see, whose wintry solitude begins; How many faces hard as Chaldee scrolls, Crowfeet on parchment skins. At fifty, Time has
- `Fact Sheet: President Biden Signs Executive Order Enabling All Qualified Americans to Serve Their Country in Uniform` (single, 4,942 B): President Biden signed today an Executive Order that sets the policy that all Americans who are qualified to serve in the Armed Forces of the United States shou

**Default view** (validated or proofread, complete, not reference): 17,819 works with 112,405 texts; with unknown works shown: 50,519 works (132,298 texts). Works in the default view whose pages are all level 1: 0. Current setting: `show_unknown_by_default = None`.

## 10. Parts table size (WS§5.2)

| parts_cap | works with catalog parts | parts rows | parts table, gzipped | parts_fts, gzipped |
|---|---|---|---|---|
| 500 | 18,234 | 291,200 | 16.81 MB | 2.59 MB |
| 2,000 | 18,283 | 341,624 | 19.46 MB | 3.18 MB |
| ∞ (all, reference works included) | 18,323 | 589,140 | 30.24 MB | 5.45 MB |
| 2,000, compact encoding | 18,283 | 341,624 | 16.90 MB | 3.18 MB |

Rows count multi-part works only, one row per part plus the root row. 37,141 single-page works would add one row each if stored. Sizes are a SQLite file holding only the table (with its index) at gzip level 9. The compact encoding (not in WS§5.1, measured for the cap decision) stores a part's page_title as the suffix after the work root's title where possible, display_title only when it differs from the last title segment, and no per-part quality_pages.

## 11. Named cases (PLAN.md WP1 acceptance, WS§9)

- `The Complete Poetical Works of Percy Bysshe Shelley (ed. Hutchinson, 1914)`: work, subpages, type book, 253 parts (231 texts), 73 missing, order toc-partial, oversized False, quality proofread (page-levels), complete False (template).
- `Ozymandias`: excluded (disambiguation); search finds 4 text(s) with this title: `Weird Tales/Volume 8/Issue 3/Ozymandias` in `Weird Tales`; `The Hundred Best Poems (lyrical) in the English language - second series/Ozymandias` in `The Hundred Best Poems (lyrical) in the English language - second series`; `The Complete Poetical Works of Percy Bysshe Shelley (ed. Hutchinson, 1914)/Ozymandias` in `The Complete Poetical Works of Percy Bysshe Shelley (ed. Hutchinson, 1914)`; `The Examiner (London)/Ozymandias` in `The Examiner (London)`.
- `Ozymandias (Shelley)`: excluded (versions).
- `Frankenstein, or the Modern Prometheus (Revised Edition, 1831)`: work, subpages, type book, 31 parts (1 texts), 0 missing, order toc, oversized False, quality validated (page-levels), complete True.
- `1911 Encyclopædia Britannica`: work, subpages, type reference, 37,420 parts (37,256 texts), 0 missing, order natural, oversized True, quality proofread (page-levels), complete False (template).

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

### A: collections of root pages (56)

1. `Sonnets attempted in the manner of Contemporary Writers`: linked, collection, 3 parts, 0 missing, order chain, unknown
    - toc: Sonnets attempted in the manner of Contemporary Writers
    - text: Sonnet I (`Pensive at Eve`) [container]
    - text: Sonnet II: To Simplicity (`To Simplicity`) [container]
    - text: Sonnet III: On a Ruined House in a Romantic Country (`On a Ruined House in a Romantic Country`) [container]
1. `The poems of Emma Lazarus`: linked, book, 1 parts, 0 missing, order single, proofread
    - front: The poems of Emma Lazarus
    - text: Biographical Sketch of Emma Lazarus (`The poems of Emma Lazarus volume 1/Biographical Sketch of Emma Lazarus`) [container]
1. `The Wealth of Nations (1902)`: linked, book, 38 parts, 3 missing, order natural, proofread
    - toc: The Wealth of Nations (1902)
    - chapter: Appendix (`The Wealth of Nations/Appendix`) [container]
    - chapter: Chapter 1: Of the Division of Labour (`The Wealth of Nations/Book I/Chapter 1`) [container]
    - chapter: Chapter 2: Of the Principle which gives Occasion to the Division of Labour (`The Wealth of Nations/Book I/Chapter 2`) [container]
    - chapter: Chapter 3: That the Division of Labour is limited by the Extent of the Market (`The Wealth of Nations/Book I/Chapter 3`) [container]
    - chapter: Chapter 4: Of the Origin and Use of Money (`The Wealth of Nations/Book I/Chapter 4`) [container]
    - chapter: Chapter 5: Of the real and nominal Price of Commodities, or of their Price in Labour, and their Price in Money (`The Wealth of Nations/Book I/Chapter 5`) [container]
    - chapter: Chapter 6: Of the component Parts of the Price of Commodities (`The Wealth of Nations/Book I/Chapter 6`) [container]
    - chapter: Chapter 7: Of the natural and market Price of Commodities (`The Wealth of Nations/Book I/Chapter 7`) [container]
    - chapter: Chapter 8: Of the Wages of Labour (`The Wealth of Nations/Book I/Chapter 8`) [container]
    - chapter: Chapter 9: Of the Profits of Stock (`The Wealth of Nations/Book I/Chapter 9`) [container]
    - chapter: Chapter 10: Of Wages and Profit in the different Employments of Labour and Stock (`The Wealth of Nations/Book I/Chapter 10`) [container]
    - … 27 more
1. `The Prose Edda (Brodeur 1916)`: linked, book, 6 parts, 0 missing, order scan, proofread
    - front: The Prose Edda (Brodeur 1916)
    - chapter: INTRODUCTION (`The Prose Edda (1916 translation by Arthur Gilchrist Brodeur)/Introduction`) [container]
    - chapter: PROLOGUE (`The Prose Edda (1916 translation by Arthur Gilchrist Brodeur)/Prologue`) [container]
    - text: GYLFAGINNING (`The Prose Edda (1916 translation by Arthur Gilchrist Brodeur)/Gylfaginning`) [container]
    - text: SKÁLDSKAPARMÁL (`The Prose Edda (1916 translation by Arthur Gilchrist Brodeur)/Skáldskaparmál`) [container]
    - chapter: INDEX (`The Prose Edda (1916 translation by Arthur Gilchrist Brodeur)/Index`) [container]
    - text: Back matter (`The Prose Edda (1916 translation by Arthur Gilchrist Brodeur)/Back matter`) [container]
1. `The Novels of Ivan Turgenev`: linked, collection, 56 parts, 1 missing, order single, proofread
    - toc: The Novels of Ivan Turgenev
    - volume: Title and ToC (`Dream Tales and Prose Poems`) [container]
      - text: Clara Militch (`Dream Tales and Prose Poems/Clara Militch`) [container]
      - text: Phantoms (`Dream Tales and Prose Poems/Phantoms`) [container]
      - text: The Song of Triumphant Love (`Dream Tales and Prose Poems/The Song of Triumphant Love`) [container]
      - text: The Dream (`Dream Tales and Prose Poems/The Dream`) [container]
      - volume: POEMS IN PROSE (`Dream Tales and Prose Poems/Poems in Prose`) [container]
        - text: The Country (`Dream Tales and Prose Poems/Poems in Prose/The Country`) [container]
        - text: A Conversation (`Dream Tales and Prose Poems/Poems in Prose/A Conversation`) [container]
        - text: The Old Woman (`Dream Tales and Prose Poems/Poems in Prose/The Old Woman`) [container]
        - text: The Dog (`Dream Tales and Prose Poems/Poems in Prose/The Dog`) [container]
        - text: My Adversary (`Dream Tales and Prose Poems/Poems in Prose/My Adversary`) [container]
    - … 45 more
1. `The Story of the Other Wise Man`: linked, book, 6 parts, 2 missing, order toc, validated
    - front: The Story of the Other Wise Man
    - chapter: Preface (`The Story of The Other Wise Man/Preface`) [container]
    - text: The Sign in the Sky (`The Story of The Other Wise Man/Chapter 1`) [container]
    - text: By the Waters of Babylon (`The Story of The Other Wise Man/Chapter 2`) [container]
    - text: For the Sake of a Little Child (`The Story of The Other Wise Man/Chapter 3`) [container]
    - text: In the Hidden Way of Sorrow (`The Story of The Other Wise Man/Chapter 4`) [container]
    - text: A Pearl of Great Price (`The Story of The Other Wise Man/Chapter 5`) [container]
1. `Trent's Trust and Other Stories`: linked, collection, 2 parts, 5 missing, order toc, unknown
    - toc: Trent's Trust and Other Stories
    - text: The Convalescence of Jack Hamlin [container]
    - text: Dick Doyle's Business Card (`Dick Boyle's Business Card`) [container]
1. `Pittsburgh Post-Gazette`: linked, collection, 1 parts, 0 missing, order single, validated
    - toc: Pittsburgh Post-Gazette
    - text: Resigning Czech Consul Assails Red Regime [container]
1. `Eleven Years in the Rocky Mountains & A History of the Sioux War`: linked, book, 64 parts, 0 missing, order scan, proofread
    - front: Eleven Years in the Rocky Mountains & A History of the Sioux War
    - volume: Eleven years in the Rocky Mountains and a life on the frontier [container]
      - chapter: Contents (`Eleven years in the Rocky Mountains and a life on the frontier/ToC`) [container]
      - text: Illustrations (`Eleven years in the Rocky Mountains and a life on the frontier/Illustrations`) [container]
      - chapter: Prefatory Chapter (`Eleven years in the Rocky Mountains and a life on the frontier/Prefatory Chapter`) [container]
      - chapter: Chapter I (`Eleven years in the Rocky Mountains and a life on the frontier/Chapter 1`) [container]
      - chapter: Chapter II (`Eleven years in the Rocky Mountains and a life on the frontier/Chapter 2`) [container]
      - chapter: Chapter III (`Eleven years in the Rocky Mountains and a life on the frontier/Chapter 3`) [container]
      - chapter: Chapter IV (`Eleven years in the Rocky Mountains and a life on the frontier/Chapter 4`) [container]
      - chapter: Chapter 5 (`Eleven years in the Rocky Mountains and a life on the frontier/Chapter 5`) [container]
      - chapter: Chapter VI (`Eleven years in the Rocky Mountains and a life on the frontier/Chapter 6`) [container]
      - chapter: Chapter VII (`Eleven years in the Rocky Mountains and a life on the frontier/Chapter 7`) [container]
    - … 53 more
1. `Poems of Letitia Elizabeth Landon (L. E. L.) in Friendship’s Offering, 1827`: linked, book, 3 parts, 0 missing, order chain, validated
    - toc: Poems of Letitia Elizabeth Landon (L. E. L.) in Friendship’s Offering, 1827
    - text: The Spirit and The Angel of Death (`Poems of Letitia Elizabeth Landon in Friendship’s Offering, 1827/Spirit`) [container]
    - text: Song - I wrote my name upon the sand (`Poems of Letitia Elizabeth Landon in Friendship’s Offering, 1827/Song`) [container]
    - text: The Lyrist (`Poems of Letitia Elizabeth Landon in Friendship’s Offering, 1827/The Lyrist`) [container]
1. `The Inner Life: volume II`: linked, book, 63 parts, 0 missing, order chain, unknown
    - front: The Inner Life: volume II
    - text: Foreword to American edition (`The Inner Life, v. II/Foreword to American edition`) [container]
    - text: Author's note (`The Inner Life, v. II/Author's note`) [container]
    - chapter: First Section: The After-death Life (`The Inner Life, v. II/First Section`) [container]
    - chapter: First Section/I: The Theosophist after Death (`The Inner Life, v. II/First Section/I`) [container]
    - chapter: First Section/II: The Relation of the Dead to Earth (`The Inner Life, v. II/First Section/II`) [container]
    - chapter: First Section/III: Conditions after Death (`The Inner Life, v. II/First Section/III`) [container]
    - chapter: First Section/IV: Animal Obsession (`The Inner Life, v. II/First Section/IV`) [container]
    - chapter: First Section/V: Individualised Animals (`The Inner Life, v. II/First Section/V`) [container]
    - chapter: First Section/VI: Localisation of States (`The Inner Life, v. II/First Section/VI`) [container]
    - chapter: First Section/VII: Heaven-life Conditions (`The Inner Life, v. II/First Section/VII`) [container]
    - chapter: First Section/VIII: Karma in the Heaven-life (`The Inner Life, v. II/First Section/VIII`) [container]
    - … 52 more
1. `The Way to Christ`: linked, collection, 1 parts, 5 missing, order single, unknown
    - front: The Way to Christ
    - text: Of True Repentance [container]
1. `United Nations General Assembly Resolution 2200 A (XXI) A /Annex`: linked, book, 3 parts, 0 missing, order single, unproofread
    - front: United Nations General Assembly Resolution 2200 A (XXI) A /Annex
    - volume: United Nations General Assembly Resolution 2200 (XXI) [container]
      - text: United Nations General Assembly Resolution 2200 A (XXI) B [container]
      - text: United Nations General Assembly Resolution 2200 A (XXI) C [container]
1. `Folk Tales and Fairy Lore in Gaelic and English`: linked, book, 55 parts, 0 missing, order toc, proofread
    - front: Folk Tales and Fairy Lore in Gaelic and English
    - text: Ridire nam Beann 's nan Gleann 's nam Bealach. The Knight of the Bens and Glens and Passes. (`Folk Tales and Fairy Lore/Ridire nam Beann 's nan Gleann 's nam Bealach. The Knight of the Bens and Glens and Passes.`) [container]
    - text: Gobhainn Dubh nan Soc. The Swarthy Smith of the Socks. (`Folk Tales and Fairy Lore/Gobhainn Dubh nan Soc, The Swarthy Smith of the Socks,`) [container]
    - text: An Taillear agus Taibhse Chill-an-Iubhair, The Tailor and the Kilnure Animated Corpse, (`Folk Tales and Fairy Lore/An Taillear agus Taibhse Chill-an-Iubhair, The Tailor and the Kilnure Animated Corpse,`) [container]
    - text: Ian Dubh Mor Mac Righ na Sorcha, Big Black John, son of the King of Sorcha, (`Folk Tales and Fairy Lore/Ian Dubh Mor Mac Righ na Sorcha, Big Black John, son of the King of Sorcha,`) [container]
    - text: Cathal O' Cruachan agus Buachaille na Greighe Cathal O'Cruachan and the Herd of the Stud (`Folk Tales and Fairy Lore/Cathal O' Cruachan agus Buachaille na Greighe, Cathal O'Cruachan and the Herd of the Stud`) [container]
    - text: Dobhnull nan Cual, Donald of the Burthens (`Folk Tales and Fairy Lore/Dobhnull nan Cual, Donald of the Burthens`) [container]
    - text: Iain Dubh Leodhasach, Seoladair, Black-haired John of Lewis (`Folk Tales and Fairy Lore/Iain Dubh Leodhasach, Seoladair, Black-haired John of Lewis`) [container]
    - text: Beithrichean Beinn Bheithir The Ben Vehir Dragons (`Folk Tales and Fairy Lore/Beithrichean Beinn Bheithir, The Ben Vehir Dragons,`) [container]
    - text: Torr-a-Bhuilg, (`Folk Tales and Fairy Lore/Torr-a-Bhuilg,`) [container]
    - text: Tàladh na Mnà-Sìthe The Fostering Fairy's Lullaby (`Folk Tales and Fairy Lore/Tàladh na Mnà-Sìthe, The Fostering Fairy's Lullaby,`) [container]
    - text: Oran Sìthe Fairy Song (`Folk Tales and Fairy Lore/Oran Sìthe, Fairy Song`) [container]
    - … 44 more
1. `Notebook`: linked, collection, 175 parts, 6 missing, order chain, unknown
    - front: Notebook
    - text: A flower was offer’d to me [container]
    - text: Never pain to tell thy Love [container]
    - text: Love seeketh not itself to please [container]
    - text: I laid me down upon a bank [container]
    - text: I went to the garden of love [container]
    - text: I saw a chapel all of gold [container]
    - text: I asked a thief to steal me a peach [container]
    - text: I heard an Angel singing [container]
    - text: A Cradle Song (Blake, 1794) [container]
    - text: A Poison Tree (`Christian Forbearance`) [container]
    - text: I fear'd the fury of my wind [container]
    - … 164 more
1. `Altai-Himalaya, a Travel Diary`: linked, book, 4 parts, 0 missing, order toc, proofread
    - toc: Altai-Himalaya, a Travel Diary
    - text: Publisher's Note (`Altai Himalaya, a Travel Diary/Publisher's Note`) [container]
    - chapter: Introduction (`Altai Himalaya, a Travel Diary/Introduction`) [container]
    - chapter: I. (`Altai Himalaya, a Travel Diary/Chapter 1`) [container]
    - chapter: II. (`Altai Himalaya, a Travel Diary/Chapter 2`) [container]
1. `Brigands of the Moon`: linked, periodical-issue, 1 parts, 0 missing, order single, proofread
    - front: Brigands of the Moon
    - chapter: Part 3 (`Astounding Stories of Super Science/Volume 02/Number 02/Brigands of the Moon`) [container]
1. `Judicial Improvements Act of 1990`: linked, collection, 3 parts, 9 missing, order chain, unknown
    - front: Judicial Improvements Act of 1990
    - text: Title V - Television Program Improvement (`Television Program Improvement Act of 1990`) [container]
    - text: Title VI - Visual Artists Rights (`Visual Artists Rights Act of 1990`) [container]
    - text: Title VII - Architectural Works (`Architectural Works Copyright Protection Act`) [container]
1. `A Posthumous Poem of Letitia Elizabeth Landon (L. E. L.) in Friendship’s Offering, 1844`: linked, book, 1 parts, 0 missing, order single, validated
    - front: A Posthumous Poem of Letitia Elizabeth Landon (L. E. L.) in Friendship’s Offering, 1844
    - text: The Soldier’s Bride (`A Posthumous Poem of Letitia Elizabeth Landon in Friendship’s Offering, 1828/The Soldier’s Bride`) [container]
1. `Principles of Political Economy (Mill)`: linked, book, 40 parts, 0 missing, order natural, proofread
    - toc: Principles of Political Economy (Mill)
    - volume: Principles of Political Economy (Mill), vol. 1 [container]
      - text: Preliminary Remarks (`Principles of Political Economy (J.S. Mill, 1871), vol. 1/Remarks`) [container]
      - chapter: Book I, Chapter I (`Principles of Political Economy (J.S. Mill, 1871), vol. 1/Book I, Chapter I`) [container]
      - chapter: Book I, Chapter II (`Principles of Political Economy (J.S. Mill, 1871), vol. 1/Book I, Chapter II`) [container]
      - chapter: Book I, Chapter III (`Principles of Political Economy (J.S. Mill, 1871), vol. 1/Book I, Chapter III`) [container]
      - chapter: Book I, Chapter IV (`Principles of Political Economy (J.S. Mill, 1871), vol. 1/Book I, Chapter IV`) [container]
      - chapter: Book I, Chapter V (`Principles of Political Economy (J.S. Mill, 1871), vol. 1/Book I, Chapter V`) [container]
      - chapter: Book I, Chapter VI (`Principles of Political Economy (J.S. Mill, 1871), vol. 1/Book I, Chapter VI`) [container]
      - chapter: Book I, Chapter VII (`Principles of Political Economy (J.S. Mill, 1871), vol. 1/Book I, Chapter VII`) [container]
      - chapter: Book I, Chapter VIII (`Principles of Political Economy (J.S. Mill, 1871), vol. 1/Book I, Chapter VIII`) [container]
      - chapter: Book I, Chapter IX (`Principles of Political Economy (J.S. Mill, 1871), vol. 1/Book I, Chapter IX`) [container]
    - … 29 more
1. `European Convention for the Protection of Human Rights and Fundamental Freedoms`: linked, collection, 2 parts, 7 missing, order chain, unknown
    - front: European Convention for the Protection of Human Rights and Fundamental Freedoms
    - text: Protocol No. 12 (`Convention for the Protection of Human Rights and Fundamental Freedoms/Protocol No. 12`) [container]
    - text: Protocol No. 13 (`Convention for the Protection of Human Rights and Fundamental Freedoms/Protocol No. 13`) [container]
1. `The Everlasting Gospel`: linked, collection, 1 parts, 0 missing, order single, unknown
    - front: The Everlasting Gospel
    - text: The Everlasting Gospel (complete) [container]
1. `Speech of Senator Stephen Douglas before the Legislature of Illinois`: linked, collection, 1 parts, 0 missing, order single, unknown
    - front: Speech of Senator Stephen Douglas before the Legislature of Illinois
    - text: Speech of Senator Stephen Douglas before the Legislature of Illinois (annotated) [container]
1. `House Page Board Revision Act of 2007`: linked, collection, 1 parts, 1 missing, order single, validated
    - front: House Page Board Revision Act of 2007
    - text: 110th Congress, Public Law 110-2 (`United States Statutes at Large/Volume 122/110th Congress/Public Law 110-2`) [container]
1. `Hague Conventions of 1899 and 1907`: linked, collection, 15 parts, 0 missing, order scan, proofread
    - front: Hague Conventions of 1899 and 1907
    - text: Hague I (1899) [container]
    - text: Hague II (1899) [container]
    - text: Hague III (1899) [container]
    - text: Hague IV, 1 (1899) [container]
    - text: Hague I (1907) [container]
    - text: Hague II (1907) (`United States Treaty Series/Volume 1/Hague II (1907)`) [container]
    - text: Hague III (1907) (`United States Treaty Series/Volume 1/Hague III (1907)`) [container]
    - text: Hague IV (1907) (`United States Treaty Series/Volume 1/Hague IV (1907)`) [container]
    - text: Hague V (1907) (`United States Treaty Series/Volume 1/Hague V (1907)`) [container]
    - text: Hague VIII (1907) (`United States Treaty Series/Volume 1/Hague VIII (1907)`) [container]
    - text: Hague IX (1907) (`United States Treaty Series/Volume 1/Hague IX (1907)`) [container]
    - … 4 more
1. `The Harvard Classics`: linked, collection, 1 parts, 1 missing, order single, unknown
    - front: The Harvard Classics
    - text: Black-Eyed Susan (`The Harvard Classics Vol. 40/Black-Eyed Susan`) [container]
1. `The Fortunes of Richard Mahony`: linked, book, 46 parts, 1 missing, order single, unproofread
    - toc: The Fortunes of Richard Mahony
    - volume: Book 1: Australia Felix (`Australia Felix`) [container]
      - text: Proem (`Australia Felix/Proem`) [container]
      - volume: Part I (`Australia Felix/Part I`) [container]
        - chapter: Part I, Chapter I (`Australia Felix/Part I/Chapter I`) [container]
        - chapter: Part I, Chapter II (`Australia Felix/Part I/Chapter II`) [container]
        - chapter: Part I, Chapter III (`Australia Felix/Part I/Chapter III`) [container]
        - chapter: Part I, Chapter IV (`Australia Felix/Part I/Chapter IV`) [container]
        - chapter: Part I, Chapter V (`Australia Felix/Part I/Chapter V`) [container]
        - chapter: Part I, Chapter VI (`Australia Felix/Part I/Chapter VI`) [container]
        - chapter: Part I, Chapter VII (`Australia Felix/Part I/Chapter VII`) [container]
        - chapter: Part I, Chapter VIII (`Australia Felix/Part I/Chapter VIII`) [container]
    - … 35 more
1. `The Satires of Juvenal`: linked, collection, 2 parts, 0 missing, order chain, unknown
    - front: The Satires of Juvenal
    - text: Satire 8 [container]
    - text: Satire IX (`Satire 9`) [container]
1. `Stories by Foreign Authors`: linked, book, 7 parts, 0 missing, order toc, proofread
    - toc: Stories by Foreign Authors
    - text: The Shot (`Stories by Foreign Authors (Russian)/The Shot`) [container]
    - text: St. John's Eve (`Stories by Foreign Authors (Russian)/St. John's Eve`) [container]
    - text: A Great Day (`Stories by Foreign Authors (Italian)/A Great Day`) [container]
    - text: Pereat Rochus (`Stories by Foreign Authors (Italian)/Pereat Rochus`) [container]
    - text: San Pantaleone (`Stories by Foreign Authors (Italian)/San Pantaleone`) [container]
    - text: It Snows (`Stories by Foreign Authors (Italian)/It Snows`) [container]
    - text: College Friends (`Stories by Foreign Authors (Italian)/College Friends`) [container]
1. `Annual Report of the Archæological Survey of India`: linked, book, 6 parts, 29 missing, order single, proofread
    - front: Annual Report of the Archæological Survey of India
    - volume: Annual Report of the Archæological Survey of India 1927-28 [container]
      - chapter: Introduction (`Annual Report of the Archæological Survey of India 1927-28/Introduction`) [hierarchy]
      - volume: SECTION I (`Annual Report of the Archæological Survey of India 1927-28/Section 1`) [container]
        - volume: Northern Circle (Muhammadan and British Monuments) (`Annual Report of the Archæological Survey of India 1927-28/Section 1/Northern Circle (Muhammadan and British Monuments)`) [hierarchy]
          - text: United Provinces (`Annual Report of the Archæological Survey of India 1927-28/Section 1/Northern Circle (Muhammadan and British Monuments)/United Provinces`) [hierarchy]
      - chapter: Section VIII (`Annual Report of the Archæological Survey of India 1927-28/Section 8`) [container]

### B: mixed (85)

1. `Chess Fundamentals`: mixed, book, 48 parts, 0 missing, order chain, proofread
    - front: Chess Fundamentals
    - chapter: Preface (`Chess fundamentals/Preface`) [container]
    - chapter: Part I, Chapter I. 1. Some Simple Mates (`Chess Fundamentals/Part I/1`) [container]
    - chapter: Part I, Chapter I. 2. Pawn Promotion (`Chess Fundamentals/Part I/2`) [container]
    - chapter: Part I, Chapter I. 3. Pawn Endings (`Chess Fundamentals/Part I/3`) [container]
    - chapter: Part I, Chapter I. 4. Some Winning Positions in the Middle-game (`Chess Fundamentals/Part I/4`) [container]
    - chapter: Part I, Chapter I. 5. Relative Value of the Pieces (`Chess Fundamentals/Part I/5`) [container]
    - chapter: Part I, Chapter I. 6. General Strategy of the Opening (`Chess Fundamentals/Part I/6`) [container]
    - chapter: Part I, Chapter I. 7. Control of the Centre (`Chess Fundamentals/Part I/7`) [container]
    - chapter: Part I, Chapter I. 8. Traps (`Chess Fundamentals/Part I/8`) [container]
    - chapter: Part I, Chapter II. Further Principles in End-Game Play 9. A Cardinal Principle (`Chess Fundamentals/Part I/9`) [container]
    - chapter: Part I, Chapter II. 10. A Classical Ending (`Chess Fundamentals/Part I/10`) [container]
    - … 37 more
1. `Shakespeare - First Folio facsimile (1910)`: mixed, book, 669 parts, 0 missing, order toc-partial, proofread
    - front: Shakespeare - First Folio facsimile (1910)
    - volume: The Life and Death of King John (`Shakespeare - First Folio facsimile (1910)/The Life and Death of King John`) [hierarchy]
      - chapter: Act 1 Scene 1 (`Shakespeare - First Folio facsimile (1910)/The Life and Death of King John/Act 1 Scene 1`) [container]
      - chapter: Act I. Scene II. (`Shakespeare - First Folio facsimile (1910)/The Life and Death of King John/Act 1 Scene 2`) [container]
      - chapter: Act II. (`Shakespeare - First Folio facsimile (1910)/The Life and Death of King John/Act 2 Scene 1`) [container]
      - chapter: Act III. Scene I. (`Shakespeare - First Folio facsimile (1910)/The Life and Death of King John/Act 3 Scene 1`) [container]
      - chapter: Act III. Scene II. (`Shakespeare - First Folio facsimile (1910)/The Life and Death of King John/Act 3 Scene 2`) [container]
      - chapter: Act III. Scene III. (`Shakespeare - First Folio facsimile (1910)/The Life and Death of King John/Act 3 Scene 3`) [container]
      - chapter: Act IV. Scene I. (`Shakespeare - First Folio facsimile (1910)/The Life and Death of King John/Act 4 Scene 1`) [container]
      - chapter: Act IV. Scene II. (`Shakespeare - First Folio facsimile (1910)/The Life and Death of King John/Act 4 Scene 2`) [container]
      - chapter: Act IV. Scene III. (`Shakespeare - First Folio facsimile (1910)/The Life and Death of King John/Act 4 Scene 3`) [container]
      - chapter: Act V. Scene I. (`Shakespeare - First Folio facsimile (1910)/The Life and Death of King John/Act 5 Scene 1`) [container]
    - … 658 more
1. `Statutes of Canada`: mixed, book, 28 parts, 243 missing, order single, proofread
    - front: Statutes of Canada
    - volume: SC 1867-68 (`Statutes of Canada/1867-68`) [container]
      - volume: Volume I (`Statutes of Canada/1867-68/Volume 1`) [container]
        - volume: Table of Contents (`Statutes of Canada/1867-68/Volume 1/Table of Contents`) [container]
          - chapter: Title Page (`Statutes of Canada/1867-68/Volume 1/Statutes`) [container]
          - volume: Imperial Acts, 1866-67, 30-31 Victoria (`Statutes of Canada/1867-68/Volume 1/Imperial Acts`) [container]
            - text: British North America, 1867 (`Statutes of Canada/1867-68/Volume 1/Imperial Acts/British North America Act, 1867`) [container]
            - text: Canada Railway Loan Act, 1867 (`Statutes of Canada/1867-68/Volume 1/Imperial Acts/Canada Railway Loan Act, 1867`) [container]
            - text: Merchant Shipping Act, 1867 (`Statutes of Canada/1867-68/Volume 1/Imperial Acts/Merchant Shipping Act, 1867`) [container]
      - volume: Volume II (`Statutes of Canada/1867-68/Volume 2`) [container]
        - chapter: Title Page (`Statutes of Canada/1867-68/Volume 2/Statutes`) [container]
        - text: Table of Contents (`Statutes of Canada/1867-68/Volume 2/Table of Contents`) [container]
    - … 17 more
1. `The Overland Monthly`: mixed, collection, 78 parts, 22 missing, order toc-partial, unproofread
    - front: The Overland Monthly
    - volume: Volume 1 (`The Overland Monthly/Volume 1`) [container]
      - volume: Issue 1 (`The Overland Monthly/Volume 1/Issue 1`) [container]
        - text: A Breeze from the Woods (`The Overland Monthly/Volume 1/A Breeze from the Woods`) [container]
        - text: Longing (`The Overland Monthly/Volume 1/Longing`) [container]
        - text: By Rail through France (`The Overland Monthly/Volume 1/By Rail through France`) [container]
        - text: Art Beginnings on the Pacific (`The Overland Monthly/Volume 1/Art Beginnings on the Pacific`) [container]
        - text: Portland-On-Wallamet (`The Overland Monthly/Volume 1/Issue 1/Portland-On-Wallamet`) [container]
        - text: In the Sierras (`The Overland Monthly/Volume 1/In the Sierras`) [container]
        - text: The Diamond Maker of Sacramento (`The Overland Monthly/Volume 1/The Diamond Maker of Sacramento`) [container]
        - text: Family Resemblances and Differences (`The Overland Monthly/Volume 1/Family Resemblances and Differences`) [container]
        - text: San Francisco (`The Overland Monthly/Volume 1/San Francisco`) [container]
    - … 67 more
1. `Against Verres`: mixed, collection, 7 parts, 0 missing, order chain, unknown
    - front: Against Verres
    - text: Divination against Quintus Caecilius [container]
    - text: First pleading (`Against Verres/First pleading`) [container]
    - text: Second pleading, Book 1 (`Against Verres/Second pleading/Book 1`) [container]
    - text: Second pleading, Book 2 (`Against Verres/Second pleading/Book 2`) [container]
    - text: Second pleading, book 3 (`Against Verres/Second pleading/Book 3`) [container]
    - text: Second pleading, book 4 (`Against Verres/Second pleading/Book 4`) [container]
    - text: Second pleading, book 5 (`Against Verres/Second pleading/Book 5`) [container]
1. `Loeb Classical Library`: mixed, book, 39 parts, 370 missing, order natural, proofread
    - front: Loeb Classical Library
    - text: Letters to his Friends [container]
    - chapter: Book 1 (`Letters to his Friends/Book 1`) [container]
    - chapter: Book 2 (`Letters to his Friends/Book 2`) [container]
    - chapter: Book 3 (`Letters to his Friends/Book 3`) [container]
    - chapter: Book 4 (`Letters to his Friends/Book 4`) [container]
    - chapter: Book 5 (`Letters to his Friends/Book 5`) [container]
    - chapter: Book 6 (`Letters to his Friends/Book 6`) [container]
    - chapter: Index of Names (`Letters to his Friends/Index of Names`) [container]
    - text: Order of Letters (`Letters to his Friends/Order of Letters`) [container]
    - volume: L002 (`Loeb Classical Library/L002`) [container]
      - chapter: Title Page (`Loeb Classical Library/L002/Title`) [container]
    - … 28 more
1. `The Saturday Evening Post`: mixed, collection, 99 parts, 4 missing, order natural, unknown
    - toc: The Saturday Evening Post
    - text: A Critique of Monsieur Poe (`The Saturday Evening Post/A Critique of Monsieur Poe`) [hierarchy]
    - volume: A Damsel in Distress (`The Saturday Evening Post/A Damsel in Distress`) [hierarchy]
      - chapter: Part I (`The Saturday Evening Post/A Damsel in Distress/Part 1`) [container]
      - chapter: Part II (`The Saturday Evening Post/A Damsel in Distress/Part 2`) [container]
      - chapter: Part III (`The Saturday Evening Post/A Damsel in Distress/Part 3`) [container]
      - chapter: Part IV (`The Saturday Evening Post/A Damsel in Distress/Part 4`) [container]
      - chapter: Part V (`The Saturday Evening Post/A Damsel in Distress/Part 5`) [container]
      - chapter: Part VI (`The Saturday Evening Post/A Damsel in Distress/Part 6`) [container]
      - chapter: Part VII (`The Saturday Evening Post/A Damsel in Distress/Part 7`) [container]
      - chapter: Part VIII (`The Saturday Evening Post/A Damsel in Distress/Part 8`) [container]
    - text: A Friend of Napoleon (`The Saturday Evening Post/A Friend of Napoleon`) [hierarchy]
    - … 88 more
1. `Barnaby Rudge`: mixed, book, 84 parts, 0 missing, order toc-partial, validated
    - toc: Barnaby Rudge
    - chapter: Preface (`Barnaby Rudge/Preface`) [container]
    - chapter: Chapter 1 (`Barnaby Rudge/Chapter 1`) [container]
    - chapter: Chapter 2 (`Barnaby Rudge/Chapter 2`) [container]
    - chapter: Chapter 3 (`Barnaby Rudge/Chapter 3`) [container]
    - chapter: Chapter 4 (`Barnaby Rudge/Chapter 4`) [container]
    - chapter: Chapter 5 (`Barnaby Rudge/Chapter 5`) [container]
    - chapter: Chapter 6 (`Barnaby Rudge/Chapter 6`) [container]
    - chapter: Chapter 7 (`Barnaby Rudge/Chapter 7`) [container]
    - chapter: Chapter 8 (`Barnaby Rudge/Chapter 8`) [container]
    - chapter: Chapter 9 (`Barnaby Rudge/Chapter 9`) [container]
    - chapter: Chapter 10 (`Barnaby Rudge/Chapter 10`) [container]
    - … 73 more
1. `Personal Narrative of a Pilgrimage to Al Madinah and Meccah`: mixed, book, 8 parts, 1 missing, order toc, unknown
    - toc: Personal Narrative of a Pilgrimage to Al Madinah and Meccah
    - chapter: Preface to the First Edition (`Personal Narrative of a Pilgrimage to Al Madinah and Meccah/preface1`) [container]
    - chapter: Preface to the Third Edition (`Personal Narrative of a Pilgrimage to Al Madinah and Meccah/preface3`) [container]
    - chapter: Preface to the Memorial Edition (`Personal Narrative of a Pilgrimage to Al Madinah and Meccah/prefaceMem`) [container]
    - chapter: Letter to Colonel William Sykes (`Burton letter to Sykes`) [container]
    - chapter: Volume I (`Personal Narrative of a Pilgrimage to Al Madinah and Meccah/Volume I`) [container]
    - chapter: Volume II (`Personal Narrative of a Pilgrimage to Al Madinah and Meccah/Volume II`) [container]
    - chapter: Volume III (`Personal Narrative of a Pilgrimage to Al Madinah and Meccah/Volume III`) [container]
    - text: Appendices (`Personal Narrative of a Pilgrimage to Al Madinah and Meccah/Appendices`) [container]
1. `Back to Methuselah`: mixed, collection, 11 parts, 1 missing, order single, validated
    - toc: Back to Methuselah
    - volume: An Infidel Half Century (`Back to Methuselah/Preface`) [hierarchy]
      - text: &sect; i (`Back to Methuselah/Preface/sect; i`) [container]
      - text: &sect; ii (`Back to Methuselah/Preface/sect; ii`) [container]
      - text: &sect; iii (`Back to Methuselah/Preface/sect; iii`) [container]
      - text: &sect; iv (`Back to Methuselah/Preface/sect; iv`) [container]
      - text: &sect; v (`Back to Methuselah/Preface/sect; v`) [container]
      - text: &sect; vi (`Back to Methuselah/Preface/sect; vi`) [container]
      - text: &sect; vii (`Back to Methuselah/Preface/sect; vii`) [container]
      - text: &sect; viii (`Back to Methuselah/Preface/sect; viii`) [container]
      - text: &sect; ix (`Back to Methuselah Preface/sect; ix`) [container]
      - text: &sect; x (`Back to Methuselah/Preface/sect; x`) [container]
1. `Suppression of Communism Act, 1950`: mixed, book, 18 parts, 16 missing, order natural, validated
    - front: Suppression of Communism Act, 1950
    - text: as at 1 April 1972 (`Suppression of Communism Act, 1950/1972-04-01`) [container]
    - volume: Available versions (`Suppression of Communism Act, 1950/Versions`) [container]
      - text: as at 20 July 1951 (`Suppression of Communism Act, 1950/1951-07-20`) [container]
      - text: as at 15 April 1954 (`Suppression of Communism Act, 1950/1954-04-15`) [container]
      - text: as at 27 June 1962 (`Suppression of Communism Act, 1950/1962-06-27`) [container]
      - text: as at 2 May 1963 (`Suppression of Communism Act, 1950/1963-05-02`) [container]
      - text: as at 24 June 1964 (`Suppression of Communism Act, 1950/1964-06-24`) [container]
      - text: as at 22 June 1965 (`Suppression of Communism Act, 1950/1965-06-22`) [container]
      - text: as at 21 February 1966 (`Suppression of Communism Act, 1950/1966-02-21`) [container]
      - text: as at 4 November 1966 (`Suppression of Communism Act, 1950/1966-11-04`) [container]
      - text: as at 8 March 1967 (`Suppression of Communism Act, 1950/1967-03-08`) [container]
    - … 7 more
1. `Labour: The Divine Command`: mixed, book, 4 parts, 0 missing, order toc, proofread
    - front: Labour: The Divine Command
    - chapter: Introduction (`Labour: The Divine Command/intro`) [container]
    - text: Labor and Bondareff's Theory [container]
    - text: Labor, According to the Bible [container]
    - text: Love and Labor [container]
1. `United States ex rel. Annaloro v. Elias`: mixed, collection, 2 parts, 0 missing, order natural, unknown
    - front: United States ex rel. Annaloro v. Elias
    - text: Opinion of the Court (`Elias v. Catena/Opinion of the Court`) [container]
    - text: Dissent Douglas (`United States ex rel. Annaloro v. Elias/Dissent Douglas`) [container]
1. `John Silence: Physician Extraordinary`: mixed, book, 6 parts, 0 missing, order chain, proofread, excluded index-faulty
    - front: John Silence: Physician Extraordinary
    - text: Case I (`John Silence: Physician Extraordinary/Case 1`) [container]
    - text: Case II (`John Silence: Physician Extraordinary/Case 2`) [container]
    - text: Case III (`John Silence: Physician Extraordinary/Case 3`) [container]
    - text: Case IV (`John Silence: Physician Extraordinary/Case 4`) [container]
    - text: Case V (`John Silence: Physician Extraordinary/Case 5`) [container]
    - text: Case VI (`John Silence, Physician Extraordinary/Case VI`) [container]
1. `Evelina, or: a Young Lady's Entrance into the World`: mixed, book, 91 parts, 0 missing, order chain, proofread
    - toc: Evelina, or: a Young Lady's Entrance into the World
    - volume: Volume I. (`Evelina, or: a Young Lady's Entrance into the World/Volume 1`) [container]
      - text: Inscription (`Evelina, or: a Young Lady's Entrance into the World/Volume 1/Inscription`) [container]
      - text: Dedication (`Evelina, or: a Young Lady's Entrance into the World/Volume 1/Dedication`) [container]
      - chapter: Preface (`Evelina, or: a Young Lady's Entrance into the World/Volume 1/Preface`) [container]
      - chapter: Errata (`Evelina, or: a Young Lady's Entrance into the World/Volume 1/Errata`) [container]
      - chapter: Letter I: Lady Howard to the Rev. Mr. Villars. Howard Grove, Kent. (`Evelina, or: a Young Lady's Entrance into the World/Volume 1/Letter I`) [container]
      - chapter: Letter II: Mr. Villars to Lady Howard Berry Hill, Dorsetshire. (`Evelina, or: a Young Lady's Entrance into the World/Volume 1/Letter II`) [container]
      - chapter: Letter III: LADY HOWARD TO THE REV. MR. VILLARS Howard Grove, March 8. (`Evelina, or: a Young Lady's Entrance into the World/Volume 1/Letter III`) [container]
      - chapter: Letter IV: MR. VILLARS TO LADY HOWARD Berry Hill, March 12. (`Evelina, or: a Young Lady's Entrance into the World/Volume 1/Letter IV`) [container]
      - chapter: Letter V: MR. VILLARS TO LADY HOWARD March 18. (`Evelina, or: a Young Lady's Entrance into the World/Volume 1/Letter V`) [container]
      - chapter: Letter VI: LADY HOWARD TO THE REV. MR. VILLARS Howard Grove. (`Evelina, or: a Young Lady's Entrance into the World/Volume 1/Letter VI`) [container]
    - … 80 more
1. `The History of England from the Accession of James II`: mixed, book, 31 parts, 0 missing, order natural, unproofread
    - front: The History of England from the Accession of James II
    - chapter: Chapter I (`The History of England from the Accession of James II (annotated)/Chapter I`) [container]
    - chapter: Chapter I (`The History of England from the Accession of James II/Chapter I`) [container]
    - chapter: Chapter II (`The History of England from the Accession of James II/Chapter II`) [container]
    - chapter: Chapter III (`The History of England from the Accession of James II/Chapter III`) [container]
    - chapter: Chapter IV (`The History of England from the Accession of James II/Chapter IV`) [container]
    - chapter: Chapter IX (`The History of England from the Accession of James II/Chapter IX`) [container]
    - chapter: Chapter V (`The History of England from the Accession of James II/Chapter V`) [container]
    - chapter: Chapter VI (`The History of England from the Accession of James II/Chapter VI`) [container]
    - chapter: Chapter VII (`The History of England from the Accession of James II/Chapter VII`) [container]
    - chapter: Chapter VIII (`The History of England from the Accession of James II/Chapter VIII`) [container]
    - chapter: Chapter X (`The History of England from the Accession of James II/Chapter X`) [container]
    - … 20 more
1. `When God Laughs & Other Stories`: mixed, collection, 13 parts, 0 missing, order chain, unknown
    - toc: When God Laughs & Other Stories
    - text: Daughters of the Rich: A One Act Play [container]
    - text: When God Laughs (`When God Laughs & Other Stories/When God Laughs`) [container]
    - text: The Apostate (`When God Laughs & Other Stories/The Apostate`) [container]
    - text: A Wicked Woman (`When God Laughs & Other Stories/A Wicked Woman`) [container]
    - text: Just Meat (`When God Laughs & Other Stories/Just Meat`) [container]
    - text: Created He Them (`When God Laughs & Other Stories/Created He Them`) [container]
    - text: The Chinago (`When God Laughs & Other Stories/The Chinago`) [container]
    - text: Make Westing (`When God Laughs & Other Stories/Make Westing`) [container]
    - text: Semper Idem (`When God Laughs & Other Stories/Semper Idem`) [container]
    - text: A Nose for the King (`When God Laughs & Other Stories/A Nose for the King`) [container]
    - text: The "Francis Spaight" (`When God Laughs & Other Stories/The "Francis Spaight"`) [container]
    - … 2 more
1. `Chicago Tribune`: mixed, collection, 69 parts, 11 missing, order toc-partial, proofread
    - front: Chicago Tribune
    - volume: October 20, 1859 (`Chicago Tribune/1859/10/20`) [container]
      - text: Where the responsibility belongs (`Chicago Tribune/1859/10/20/Where the responsibility belongs`) [container]
      - text: The Times is displeased (`Chicago Tribune/1859/10/20/The Times is displeased`) [container]
      - text: The Times for the African slave trade (`Chicago Tribune/1859/10/20/The Times for the African slave trade`) [container]
      - text: R. E. Goodell notice (`Chicago Tribune/1859/10/20/R. E. Goodell notice`) [container]
      - text: Osawatomie Brown (`Chicago Tribune/1859/10/20/Osawatomie Brown`) [container]
      - chapter: A letter from Virginia (`Chicago Tribune/1859/10/20/A letter from Virginia`) [container]
      - text: Excerpt from "Catskill Mountains" (`Chicago Tribune/1859/10/20/Excerpt from "Catskill Mountains"`) [container]
      - text: Adams County Fair (`Chicago Tribune/1859/10/20/Adams County Fair`) [container]
      - text: Desmoines County Fair (`Chicago Tribune/1859/10/20/Desmoines County Fair`) [container]
      - text: Census of Iowa (`Chicago Tribune/1859/10/20/Census of Iowa`) [container]
    - … 58 more
1. `Freedom's Battle`: mixed, book, 9 parts, 7 missing, order natural, unproofread
    - front: Freedom's Battle
    - chapter: I. (`Freedom's Battle/1`) [container]
    - chapter: Introduction (`Freedom's Battle/Introduction`) [container]
    - text: Non Cooperation (`Freedom's Battle/Non Cooperation`) [container]
    - text: Swaraj (`Freedom's Battle/Swaraj`) [container]
    - text: The Khilafat (`Freedom's Battle/The Khilafat`) [container]
    - text: The Punjab Wrongs (`Freedom's Battle/The Punjab Wrongs`) [container]
    - text: Treatment of Indians Abroad (`Freedom's Battle/Treatment of Indians Abroad`) [container]
    - text: Treatment of the Depressed Classes (`Freedom's Battle/Treatment of the Depressed Classes`) [container]
    - text: Hind Swaraj or Indian Home Rule (`Hind Swaraj (1938 edition)`) [container]
1. `United States v. Chas. Pfizer & Co., Inc.`: mixed, collection, 2 parts, 0 missing, order natural, unknown
    - front: United States v. Chas. Pfizer & Co., Inc.
    - text: Opinion of the Court (`United States v. Chapfizer & Co/Opinion of the Court`) [container]
    - text: Opinion of the Court (`United States v. Chas. Pfizer & Co., Inc./Opinion of the Court`) [container]
1. `The Complete Works of Geoffrey Chaucer`: mixed, collection, 169 parts, 126 missing, order toc, unproofread
    - toc: The Complete Works of Geoffrey Chaucer
    - volume: Volume I. - Romaunt of the Rose & Minor Poems (`The Complete Works of Geoffrey Chaucer/Volume 1`) [hierarchy]
      - text: General Introduction (`Chaucer's Works (ed. Skeat) Vol. I/Introduction`) [container]
      - text: Life of Chaucer (`Chaucer's Works (ed. Skeat) Vol. I/Life`) [container]
      - text: List of Chaucer's Works (`Chaucer's Works (ed. Skeat) Vol. I/Works`) [container]
      - chapter: Errata and Addenda (`Chaucer's Works (ed. Skeat) Vol. I/Errata`) [container]
      - chapter: Introduction to Romaunt of the Rose (`Chaucer's Works (ed. Skeat) Vol. I/Romaunt - Introduction`) [container]
      - chapter: Introduction to Minor Poems (`Chaucer's Works (ed. Skeat) Vol. I/Minor Poems - Introduction`) [container]
      - text: Fragment A (`Chaucer's Works (ed. Skeat) Vol. I/Fragment A`) [container]
      - text: Fragment B (`Chaucer's Works (ed. Skeat) Vol. I/Fragment B`) [container]
      - text: Fragment C (`Chaucer's Works (ed. Skeat) Vol. I/Fragment C`) [container]
      - text: An A. B. C. (`Chaucer's Works (ed. Skeat) Vol. I/I`) [container]
    - … 158 more
1. `Poems of Letitia Elizabeth Landon (L. E. L.) in Friendship’s Offering, 1836`: mixed, book, 6 parts, 0 missing, order chain, validated
    - toc: Poems of Letitia Elizabeth Landon (L. E. L.) in Friendship’s Offering, 1836
    - volume: Poems of Letitia Elizabeth Landon (L. E. L.) in Friendship's Offering, 1836 [container]
      - text: The Boon (`Poems of Letitia Elizabeth Landon (L. E. L.) in Friendship’s Offering, 1836/The Boon`) [container]
      - text: The Festival (`Poems of Letitia Elizabeth Landon (L. E. L.) in Friendship’s Offering, 1836/The Festival`) [container]
      - text: The Black Seal (`Poems of Letitia Elizabeth Landon (L. E. L.) in Friendship’s Offering, 1836/The Black Seal`) [container]
    - text: The Boon (`Poems of Letitia Elizabeth Landon in Friendship’s Offering, 1828/The Boon`) [container]
    - text: The Festival (`Poems of Letitia Elizabeth Landon in Friendship’s Offering, 1836/The Festival`) [container]
1. `The Prince (Marriott)`: mixed, book, 34 parts, 0 missing, order scan, proofread
    - toc: The Prince (Marriott)
    - chapter: front matter (`The Prince (Marriott)/front matter`) [container]
    - chapter: Introduction (`The Prince (Marriott)/Introduction`) [container]
    - text: Dedication to The Magnificent Lorenzo di Piero de' Medici (`The Prince (Marriott)/Dedication`) [container]
    - text: How Many Kinds of Principalities there are, and by what Means they are acquired (`The Prince (Marriott)/Chapter 1`) [container]
    - text: Concerning Hereditary Principalities (`The Prince (Marriott)/Chapter 2`) [container]
    - text: Concerning Mixed Principalities (`The Prince (Marriott)/Chapter 3`) [container]
    - text: Why the Kingdom of Darius, conquered by Alexander, did not rebel against the Successors of Alexander at his Death (`The Prince (Marriott)/Chapter 4`) [container]
    - text: Concerning the way to govern Cities or Principalities which lived under their own Laws before they were annexed (`The Prince (Marriott)/Chapter 5`) [container]
    - text: Concerning Principalities which are acquired by one's own Arms and Ability (`The Prince (Marriott)/Chapter 6`) [container]
    - text: Concerning new Principalities which are acquired either by the Arms of of others or by Good Fortune (`The Prince (Marriott)/Chapter 7`) [container]
    - text: Concerning those who have obtained a Principality by Wickedness (`The Prince (Marriott)/Chapter 8`) [container]
    - … 23 more
1. `Dictionary of National Biography, 1885-1900`: mixed, reference, 28,089 parts, 2 missing, order natural, proofread
    - toc: Dictionary of National Biography, 1885-1900
    - text: 'Plantagenet,' Edward (`Dictionary of National Biography, 1885-1900/'Plantagenet,' Edward`) [hierarchy]
    - text: Abbadie, Jacques (`Dictionary of National Biography, 1885-1900/Abbadie, Jacques`) [hierarchy]
    - text: Abbot, Charles (1757-1829) (`Dictionary of National Biography, 1885-1900/Abbot, Charles (1757-1829)`) [hierarchy]
    - text: Abbot, Charles (d.1817) (`Dictionary of National Biography, 1885-1900/Abbot, Charles (d.1817)`) [hierarchy]
    - text: Abbot, George (1562-1633) (`Dictionary of National Biography, 1885-1900/Abbot, George (1562-1633)`) [hierarchy]
    - text: Abbot, George (1603-1648) (`Dictionary of National Biography, 1885-1900/Abbot, George (1603-1648)`) [hierarchy]
    - text: Abbot, John (`Dictionary of National Biography, 1885-1900/Abbot, John`) [hierarchy]
    - text: Abbot, Maurice (`Dictionary of National Biography, 1885-1900/Abbot, Maurice`) [hierarchy]
    - text: Abbot, Robert (1560-1617) (`Dictionary of National Biography, 1885-1900/Abbot, Robert (1560-1617)`) [hierarchy]
    - text: Abbot, Robert (1588?-1662?) (`Dictionary of National Biography, 1885-1900/Abbot, Robert (1588?-1662?)`) [hierarchy]
    - text: Abbot, William (`Dictionary of National Biography, 1885-1900/Abbot, William`) [hierarchy]
    - … 28,078 more
1. `The Female Portrait Gallery`: mixed, book, 23 parts, 0 missing, order scan, proofread
    - front: The Female Portrait Gallery
    - text: Title Pages (`Female Portrait Gallery`) [container]
    - text: Flora Mac Ivor (`The Female Portrait Gallery/Flora Mac Ivor`) [container]
    - text: Rose Bradwardine (`The Female Portrait Gallery/Rose Bradwardine`) [container]
    - text: Julia Mannering (`The Female Portrait Gallery/Julia Mannering`) [container]
    - text: Lucy Bertram (`The Female Portrait Gallery/Lucy Bertram`) [container]
    - text: Miss Wardour (`The Female Portrait Gallery/Miss Wardour`) [container]
    - text: Mary Mac Intyre (`The Female Portrait Gallery/Mary Mac Intyre`) [container]
    - text: Diana Vernon (`The Female Portrait Gallery/Diana Vernon`) [container]
    - text: Isabel Vere (`The Female Portrait Gallery/Isabel Vere`) [container]
    - text: Edith Bellenden (`The Female Portrait Gallery/Edith Bellenden`) [container]
    - text: Jenny Dennison (`The Female Portrait Gallery/Jenny Dennison`) [container]
    - … 12 more
1. `Who's Who`: mixed, collection, 5 parts, 0 missing, order toc-partial, unknown
    - front: Who's Who
    - text: Who's Who in 1858 (`Who's Who/1858`) [hierarchy]
    - text: Who's Who, 1909 (`Who's Who/1909`) [hierarchy]
    - text: Who's Who, 1912 (`Who's Who/1912`) [hierarchy]
    - text: Who's Who, 1916 (`Who's Who/1916`) [hierarchy]
    - text: Who Was Who (1897 to 1916) [container]
1. `Felicia Hemans in The Monthly Magazine Volume 1 1826`: mixed, book, 5 parts, 0 missing, order scan, validated
    - toc: Felicia Hemans in The Monthly Magazine Volume 1 1826
    - text: Tasso and his Sister (`Felicia Hemans in The Monthly Magazine Volume 1 1826/Tasso and his Sister`) [container]
    - text: Tasso and his Sister (`Poems of Felicia Hemans in The Monthly Magazine Volume 1 1826/Tasso and his Sister`) [container]
    - text: The Song of the Curfew (`Felicia Hemans in The Monthly Magazine Volume 1 1826/The Song of the Curfew`) [container]
    - text: The Songs of our Fathers (`Felicia Hemans in The Monthly Magazine Volume 1 1826/The Songs of our Fathers`) [container]
    - text: The Songs of our Fathers (`Poems of Felicia Hemans in The Monthly Magazine Volume 1 1826/The Songs of our Fathers`) [container]
1. `Herodotus The Persian Wars (Godley)`: mixed, book, 11 parts, 0 missing, order toc-partial, unknown
    - front: Herodotus The Persian Wars (Godley)
    - chapter: Book I (`Herodotus The Persian Wars (Godley)/Book I`) [hierarchy]
    - chapter: Book II (`Herodotus The Persian Wars (Godley)/Book II`) [hierarchy]
    - chapter: Book III (`Herodotus The Persian Wars (Godley)/Book III`) [hierarchy]
    - chapter: Book IV (`Herodotus The Persian Wars (Godley)/Book IV`) [hierarchy]
    - chapter: Book V (`Herodotus The Persian Wars (Godley)/Book V`) [hierarchy]
    - chapter: Book VI (`Herodotus The Persian Wars (Godley)/Book VI`) [hierarchy]
    - chapter: Book VII (`Herodotus The Persian Wars (Godley)/Book VII`) [hierarchy]
    - chapter: Book VIII (`Herodotus The Persian Wars (Godley)/Book VIII`) [hierarchy]
    - chapter: Book IX (`Herodotus The Persian Wars (Godley)/Book IX`) [hierarchy]
    - chapter: Book 2: Euterpe (`Histories (Godley translation)/Book 2`) [container]
    - chapter: Book 7: Polymnia (`Histories (Godley translation)/Book 7`) [container]
1. `The Gift of Black Folk: The Negroes in the Making of America`: mixed, book, 18 parts, 0 missing, order scan, proofread
    - front: The Gift of Black Folk: The Negroes in the Making of America
    - text: Foreword (`The Gift of Black Folk/Foreword`) [container]
    - text: Foreword (`The Gift of Black Folk: The Negroes in the Making of America/Foreword`) [container]
    - text: The Racial Contributions to the United States (`The Gift of Black Folk/The Racial Contributions to the United States`) [container]
    - text: Prescript (`The Gift of Black Folk/Prescript`) [container]
    - text: Prescript (`The Gift of Black Folk: The Negroes in the Making of America/Prescript`) [container]
    - text: The Black Explorers (`The Gift of Black Folk/The Black Explorers`) [container]
    - chapter: Chapter 1: The Black Explorers (`The Gift of Black Folk: The Negroes in the Making of America/Chapter 1`) [container]
    - text: Black Labor (`The Gift of Black Folk/Black Labor`) [container]
    - chapter: Chapter 2: Black Labor (`The Gift of Black Folk: The Negroes in the Making of America/Chapter 2`) [container]
    - chapter: Chapter 3: Black Soldiers (`The Gift of Black Folk: The Negroes in the Making of America/Chapter 3`) [container]
    - chapter: Chapter 4: The Emancipation of Democracy (`The Gift of Black Folk: The Negroes in the Making of America/Chapter 4`) [container]
    - … 7 more
1. `Nicene and Post-Nicene Fathers: Series II`: mixed, reference, 7,099 parts, 1 missing, order chain, unproofread
    - front: Nicene and Post-Nicene Fathers: Series II
    - volume: Volume I: Eusebius: Church History from A.D. 1-324, Life of Constantine the Great, Oration in Praise of Constantine (`Nicene and Post-Nicene Fathers: Series II/Volume I`) [container]
      - chapter: Title Page (`Nicene and Post-Nicene Fathers: Series II/Volume I/Title Page`) [container]
      - chapter: Preface (`Nicene and Post-Nicene Fathers: Series II/Volume I/Preface`) [container]
      - volume: Church History of Eusebius (`Nicene and Post-Nicene Fathers: Series II/Volume I/Church History of Eusebius`) [container]
        - chapter: Title Page (`Nicene and Post-Nicene Fathers: Series II/Volume I/Church History of Eusebius/Title Page`) [container]
        - chapter: Preface (`Nicene and Post-Nicene Fathers: Series II/Volume I/Church History of Eusebius/Preface`) [container]
        - volume: Prolegomena (`Nicene and Post-Nicene Fathers: Series II/Volume I/Church History of Eusebius/Prolegomena`) [container]
          - volume: The Life of Eusebius (`Nicene and Post-Nicene Fathers: Series II/Volume I/Church History of Eusebius/Prolegomena/The Life of Eusebius`) [container]
            - chapter: Section 1 (`Nicene and Post-Nicene Fathers: Series II/Volume I/Church History of Eusebius/Prolegomena/The Life of Eusebius/Section 1`) [container]
            - chapter: Section II (`Nicene and Post-Nicene Fathers: Series II/Volume I/Church History of Eusebius/Prolegomena/The Life of Eusebius/Section II`) [container]
            - chapter: Section III (`Nicene and Post-Nicene Fathers: Series II/Volume I/Church History of Eusebius/Prolegomena/The Life of Eusebius/Section III`) [container]
    - … 7,088 more

### D: virtual roots (824)

1. `The German Manual for the Young and for Self-Tuition`: virtual, book, 1 parts, 0 missing, order single, proofread
    - text: 33: Der Alp / The night-mare (`The German Manual for the Young and for Self-Tuition/Volume 2/Part 2/Section 33`)
1. `Some Chinese Ghosts`: virtual, book, 2 parts, 0 missing, order chain, proofread
    - chapter: Notes (`Some Chinese Ghosts/Appendix 1`)
    - text: Glossary (`Some Chinese Ghosts/Appendix 2`) [hierarchy]
1. `Miscellaneous Essays Relating to Indian Subjects`: virtual, book, 1 parts, 0 missing, order single, unproofread, excluded no-text-pages
    - chapter: Volume II (`Miscellaneous Essays Relating to Indian Subjects/Volume II`)
1. `Evening Herald (Dublin)`: virtual, collection, 1 parts, 0 missing, order single, unknown
    - text: Death of Prof. R. Donovan at age of 72 (`Evening Herald (Dublin)/1934/Death of Prof. R. Donovan at age of 72`)
1. `Success Magazine`: virtual, collection, 1 parts, 0 missing, order single, unknown
    - text: King Cophetua of Klondyke (`Success Magazine/King Cophetua of Klondyke`)
1. `Acts of the Parliament of India`: virtual, book, 1 parts, 0 missing, order single, unproofread
    - text: Scheduled Castes and the Scheduled Tribes (Prevention of Atrocities) Act, 1989 (`Acts of the Parliament of India/1989/Scheduled Castes and the Scheduled Tribes (Prevention of Atrocities) Act, 1989`)
1. `Reno Evening Gazette`: virtual, collection, 1 parts, 0 missing, order single, unknown
    - text: Hired Soldiers (`Reno Evening Gazette/1937/Hired Soldiers`)
1. `Major Prophets of To-day`: virtual, book, 1 parts, 0 missing, order single, unproofread
    - text: Ernst Haeckel (`Major Prophets of To-day/Ernst Haeckel`)
1. `Pittsburgh Post`: virtual, collection, 1 parts, 0 missing, order single, unknown
    - text: Icy-Looking Mystery Of Ice Skating (`Pittsburgh Post/1914/Icy-Looking Mystery Of Ice Skating`)
1. `Curtains (Hall)`: virtual, book, 1 parts, 0 missing, order single, proofread
    - text: The World's Voice (`Curtains (Hall)/The World's Voice`)
1. `British and Foreign State Papers`: virtual, book, 1 parts, 0 missing, order single, unproofread, excluded no-text-pages
    - text: Constitution of the Kingdom of Siam (1949) (`British and Foreign State Papers/Volume 155/Constitution of the Kingdom of Siam`)
1. `The Criterion`: virtual, periodical-issue, 13 parts, 0 missing, order scan, proofread
    - text: The Idea of a Literary Review (`The Criterion/Volume 4/Number 1/The Idea of a Literary Review`)
    - text: Aristotle on Democracy and Socialism (`The Criterion/Volume 4/Number 1/Aristotle on Democracy and Socialism`) [hierarchy]
    - text: On Being Ill (`The Criterion/Volume 4/Number 1/On Being Ill`) [hierarchy]
    - text: The River Flows (`The Criterion/Volume 4/Number 1/The River Flows`) [hierarchy]
    - text: The Monocle (`The Criterion/Volume 4/Number 1/The Monocle`) [hierarchy]
    - text: The Fifteenth of November (`The Criterion/Volume 4/Number 1/The Fifteenth of November`) [hierarchy]
    - chapter: II. (`The Criterion/Volume 4/Number 1/The Woman Who Rode Away (Part 2)`) [hierarchy]
    - text: The Novels of Virginia Woolf (`The Criterion/Volume 4/Number 2/The Novels of Virginia Woolf`) [hierarchy]
    - text: Our Need for Religious Sincerity (`The Criterion/Volume 4/Number 2/Our Need for Religious Sincerity`) [hierarchy]
    - text: Poem (`The Criterion/Volume 4/Number 2/Poem`) [hierarchy]
    - text: Spring Ode (`The Criterion/Volume 4/Number 3/Spring Ode`) [hierarchy]
    - text: Mornings in Mexico (`The Criterion/Volume 4/Number 3/Mornings in Mexico`) [hierarchy]
    - … 1 more
1. `Old Savage`: virtual, book, 6 parts, 0 missing, order scan, proofread
    - text: Friends of God’s Acre (`Old Savage/Friends of God's Acre`)
    - text: The Woman (`Old Savage/The Woman`) [hierarchy]
    - text: Mrs Salgast’s Baby (`Old Savage/Mrs Salgast's Baby`) [hierarchy]
    - text: “Old Savage” (`Old Savage/"Old Savage"`) [hierarchy]
    - text: That “Almost” (`Old Savage/That "Almost"`) [hierarchy]
    - text: Her Big Idea (`Old Savage/Her Big Idea`) [hierarchy]
1. `Monthly Notices of the Royal Astronomical Society`: virtual, collection, 1 parts, 0 missing, order single, unknown
    - text: Obituary: James Sweetman Eiffe (`Monthly Notices of the Royal Astronomical Society/1881/Obituary: James Sweetman Eiffe`)
1. `Arquivos de Neuro-Psiquiatria`: virtual, document, 1 parts, 0 missing, order single, proofread
    - text: Henrique Dumont: how a traumatic brain injury contributed to the development of the airplane (`Arquivos de Neuro-Psiquiatria/Volume 77/Issue 1/Henrique Dumont: how a traumatic brain injury contributed to the development of the airplane`)
1. `Liberator`: virtual, collection, 1 parts, 0 missing, order single, unknown
    - text: To Nicolai Lenin (`Liberator/Volume 1/Issue 9/To Nicolai Lenin`)
1. `Al Qaeda Operative Convicted by Jury in One of the Most Serious Terrorist Plots Against America since 9`: virtual, book, 1 parts, 0 missing, order single, unknown
    - chapter: 11 (`Al Qaeda Operative Convicted by Jury in One of the Most Serious Terrorist Plots Against America since 9/11`)
1. `The Burlington Free Press`: virtual, collection, 1 parts, 0 missing, order single, unknown
    - text: Eddie Schneider Lowers 3 Marks (`The Burlington Free Press/1930/Eddie Schneider Lowers 3 Marks`)
1. `Age of Innocence (Appleton 1920)`: virtual, book, 12 parts, 0 missing, order chain, validated
    - chapter: Chapter I (`Age of Innocence (Appleton 1920)/Chapter 1`)
    - chapter: Chapter II (`Age of Innocence (Appleton 1920)/Chapter 2`) [hierarchy]
    - chapter: Chapter III (`Age of Innocence (Appleton 1920)/Chapter 3`) [hierarchy]
    - chapter: Chapter IV (`Age of Innocence (Appleton 1920)/Chapter 4`) [hierarchy]
    - chapter: Chapter V (`Age of Innocence (Appleton 1920)/Chapter 5`) [hierarchy]
    - chapter: Chapter VI (`Age of Innocence (Appleton 1920)/Chapter 6`) [hierarchy]
    - chapter: Chapter VII (`Age of Innocence (Appleton 1920)/Chapter 7`) [hierarchy]
    - chapter: Chapter VIII (`Age of Innocence (Appleton 1920)/Chapter 8`) [hierarchy]
    - chapter: Chapter IX (`Age of Innocence (Appleton 1920)/Chapter 9`) [hierarchy]
    - chapter: Chapter X (`Age of Innocence (Appleton 1920)/Chapter 10`) [hierarchy]
    - chapter: Chapter XI (`Age of Innocence (Appleton 1920)/Chapter 11`) [hierarchy]
    - chapter: Chapter XII (`Age of Innocence (Appleton 1920)/Chapter 12`) [hierarchy]
1. `Trewman's Exeter Flying Post`: virtual, collection, 1 parts, 0 missing, order single, unknown
    - text: Charles Worthy (`Trewman's Exeter Flying Post/1899/Charles Worthy`)
1. `The True Republican`: virtual, collection, 1 parts, 0 missing, order single, unknown
    - text: Dashed To Death (`The True Republican/1891/Dashed To Death`)
1. `Biographia Dramatica`: virtual, collection, 1 parts, 0 missing, order single, unknown
    - text: Granville, George, Lord Lansdown (`Biographia Dramatica/Granville, George, Lord Lansdown`)
1. `Harpers`: virtual, collection, 1 parts, 0 missing, order single, unknown
    - text: A Boy and a Girl (`Harpers/A Boy and a Girl`)
1. `The Undivine Comedy, and Other Poems`: virtual, book, 6 parts, 0 missing, order scan, proofread
    - chapter: Preface (`The Undivine Comedy, and Other Poems/Preface`)
    - text: Biography of Krasinski (`The Undivine Comedy, and Other Poems/Biography of Krasinski`) [hierarchy]
    - chapter: Preface to the French Edition (`The Undivine Comedy, and Other Poems/Preface to the French Edition`) [hierarchy]
    - text: Analysis of the Undivine Comedy (`The Undivine Comedy, and Other Poems/Analysis of the Undivine Comedy`) [hierarchy]
    - text: Resurrecturis (`The Undivine Comedy, and Other Poems/Resurrecturis`) [hierarchy]
    - text: In Memoriam (`The Undivine Comedy, and Other Poems/In Memoriam`) [hierarchy]
1. `The Indianapolis Star`: virtual, collection, 1 parts, 0 missing, order single, unknown
    - text: Carl Schneider Dies (`The Indianapolis Star/1976/Carl Schneider Dies`)
1. `The London Standard`: virtual, collection, 1 parts, 0 missing, order single, unknown
    - text: Death of Mr. J. Bailey Denton (`The London Standard/1893/Death of Mr. J. Bailey Denton`)
1. `Oakland Young Men`: virtual, collection, 1 parts, 0 missing, order single, unknown
    - text: Membership Secretary (`Oakland Young Men/1913/Membership Secretary`)
1. `The Life of Lokamanya Tilak`: virtual, book, 13 parts, 0 missing, order scan, proofread
    - chapter: Chapter 1 : Mr. Blunt. (`The Life of Lokamanya Tilak/Chapter 1`)
    - chapter: Chapter 2 : The First Ordeal. (`The Life of Lokamanya Tilak/Chapter 2`) [hierarchy]
    - chapter: Chapter 3 : The School and The College. (`The Life of Lokamanya Tilak/Chapter 3`) [hierarchy]
    - chapter: Chapter 4 : Tilak and Social Reform. (`The Life of Lokamanya Tilak/Chapter 4`) [hierarchy]
    - chapter: Chapter 5 : Tilak and the Congress—I. (`The Life of Lokamanya Tilak/Chapter 5`) [hierarchy]
    - chapter: Chapter 6 : The Honourable and——!. (`The Life of Lokamanya Tilak/Chapter 6`) [hierarchy]
    - chapter: Chapter 7 : The Lokamanya. (`The Life of Lokamanya Tilak/Chapter 7`) [hierarchy]
    - chapter: Chapter 8 : The School of Moderation. (`The Life of Lokamanya Tilak/Chapter 8`) [hierarchy]
    - chapter: Chapter 9 : The Fierry Cross. (`The Life of Lokamanya Tilak/Chapter 9`) [hierarchy]
    - chapter: Chapter 10 : The New Party. (`The Life of Lokamanya Tilak/Chapter 10`) [hierarchy]
    - chapter: Chapter 11 : Tilak and the Congress——II. (`The Life of Lokamanya Tilak/Chapter 11`) [hierarchy]
    - chapter: Appendix A : The Orion. (`The Life of Lokamanya Tilak/Appendix A`) [hierarchy]
    - … 1 more
1. `Cocoa and Chocolate: A Short History of Their Production and Use`: virtual, book, 1 parts, 0 missing, order single, proofread
    - text: Prefatory Note (`Cocoa and Chocolate: A Short History of Their Production and Use/Prefatory Note`)
1. `The Indian Journal of Medical Research`: virtual, book, 1 parts, 0 missing, order single, unproofread
    - text: The Vaccine Institute, Belgaum (`The Indian Journal of Medical Research/Volume 8/The Vaccine Institute, Belgaum`)

### E: subpage texts reached from a disambiguation or versions page (25,458)

1. text “Lay by your Pleading” (`A Collection, of One Hundred and Eighty Loyal Songs/Lay by your Pleading`), part 1 of `A Collection, of One Hundred and Eighty Loyal Songs` (book, 1 parts, 1 texts)
1. text “Boat Song” (`The Poems of Charles Fenno Hoffman/Boat Song`), part 84 of `The Poems of Charles Fenno Hoffman` (book, 98 parts, 95 texts)
1. chapter “192. The Master-Thief” (`Grimm's Household Tales, Volume 2/The Master-Thief`), part 107 of `Grimm's Household Tales, Volume 2` (book, 126 parts, 5 texts)
1. text “Wealth” (`Poems (Emerson, Household Edition, 1904)/Wealth`), part 118 of `Poems (Emerson, Household Edition, 1904)` (book, 174 parts, 171 texts)
1. text “THE CHARGE OF THE LIGHT BRIGADE” (`War, the Liberator, and Other Pieces/The Charge of the Light Brigade`), part 30 of `War, the Liberator, and Other Pieces` (book, 46 parts, 46 texts)
1. text “Holy Willie's Prayer” (`The Beauties of Burn's Poems/Holy Willie's Prayer`), part 14 of `The Beauties of Burn's Poems` (book, 38 parts, 38 texts)
1. text “An Election Address” (`Lapsus Calami (Aug 1891)/An Election Address`), part 26 of `Lapsus Calami (Aug 1891)` (book, 49 parts, 49 texts)
1. text “Pastoral” (`Al Que Quiere!/Pastoral (2nd)`), part 11 of `Al Que Quiere!` (book, 52 parts, 51 texts)
1. text “Poem V. The Passionate Shepherd to his Love” (`The Golden Treasury of English Songs and Lyrics/Book 1/Poem 5`), part 9 of `The Golden Treasury of English Songs and Lyrics` (book, 35 parts, 31 texts)
1. text “Battle of Waterloo” (`Battle of Waterloo, The Bonnet so Blue, and Love has Eyes/Battle of Waterloo`), part 1 of `Battle of Waterloo, The Bonnet so Blue, and Love has Eyes` (book, 3 parts, 3 texts)
1. text “Tèma Con Variazióni” (`Rhyme? and Reason?/Tèma Con Variazióni`), part 9 of `Rhyme? and Reason?` (book, 18 parts, 18 texts)
1. text “The Ploughman” (`The Complete Poetical Works of Oliver Wendell Holmes/The Ploughman`), part 11 of `The Complete Poetical Works of Oliver Wendell Holmes` (book, 60 parts, 60 texts)
1. text “Fame is a fickle food” (`The Single Hound/Fame is a fickle food`), part 6 of `The Single Hound` (book, 144 parts, 143 texts)
1. chapter “Letter 81: To the Philosopher (Hypatia)” (`The Letters of Synesius of Cyrene/Letter 81`), part 13 of `The Letters of Synesius of Cyrene` (book, 16 parts, 1 texts)
1. text “O Captain! My Captain!” (`Poems That Every Child Should Know/O Captain! My Captain!`), part 52 of `Poems That Every Child Should Know` (book, 197 parts, 188 texts)
1. text “Eros” (`Poems (Coates 1916)/Volume I/Eros`), part 116 of `Poems (Coates 1916)` (collection, 347 parts, 342 texts)
1. text “The Falcon” (`The Golden Violet with its Tales of Romance and Chivalry and Other Poems/The Falcon`), part 4 of `The Golden Violet with its Tales of Romance and Chivalry and Other Poems` (book, 28 parts, 27 texts)
1. text “To My Wife” (`McClure's Magazine/Volume 2/Number 1/To My Wife`), part 16 of `McClure's Magazine` (collection, 268 parts, 234 texts)
1. text “The Titmouse” (`Poems (Emerson, Household Edition, 1904)/The Titmouse`), part 82 of `Poems (Emerson, Household Edition, 1904)` (book, 174 parts, 171 texts)
1. text “Two Pictures” (`The Atlantic Monthly/Volume 17/Number 100/Two Pictures`), part 360 of `The Atlantic Monthly` (collection, 587 parts, 478 texts)
1. text “The Wanderer” (`Poems (Vadlan)/The Wanderer`), part 25 of `Poems (Vadlan)` (book, 30 parts, 30 texts)
1. text “April Days” (`Poems (Thaxter)/April Days`), part 29 of `Poems (Thaxter)` (book, 66 parts, 66 texts)
1. text “Clarinda” (`Six Excellent Songs (Newton Stewart)/Clarinda`), part 3 of `Six Excellent Songs (Newton Stewart)` (book, 6 parts, 6 texts)
1. text “The Puppet Player” (`Caroling Dusk/The Puppet Player`), part 44 of `Caroling Dusk` (book, 259 parts, 257 texts)
1. text “Providence” (`Poems (Smith)/Providence`), part 37 of `Poems (Smith)` (book, 92 parts, 92 texts)
1. text “Bonie Lesley” (`The Poetical Works of Robert Burns/Bonie Lesley`), part 382 of `The Poetical Works of Robert Burns` (book, 508 parts, 500 texts)
1. text “The Suicide” (`Summer and Winter Hours/The Suicide`), part 31 of `Summer and Winter Hours` (book, 60 parts, 58 texts)
1. text “A Sketch” (`Poems (Hale)/A Sketch`), part 99 of `Poems (Hale)` (book, 128 parts, 128 texts)
1. text “The Brave Tin Soldier” (`Hans Andersen's Fairy Tales/The Brave Tin Soldier`), part 3 of `Hans Andersen's Fairy Tales` (book, 97 parts, 97 texts)
1. text “Along the Potomac” (`Poems (Dickinson)/Along the Potomac`), part 109 of `Poems (Dickinson)` (book, 116 parts, 114 texts)

### all searchable subpage texts (chapter titles that the label patterns miss show up here) (428,897)

1. text “Grey, (Sir) George (Bart.)” (`Alumni Oxonienses: the Members of the University of Oxford, 1715-1886/Grey, (Sir) George (Bart.)`), part 23162 of `Alumni Oxonienses: the Members of the University of Oxford, 1715-1886` (reference, 63,225 parts, 63,216 texts)
1. text “Parker, John (1799-1881)” (`Dictionary of National Biography, 1885-1900/Parker, John (1799-1881)`), part 19751 of `Dictionary of National Biography, 1885-1900` (reference, 28,089 parts, 28,080 texts)
1. text “kuah” (`Dictionary of the Swatow dialect/kuah`), part 349 of `Dictionary of the Swatow dialect` (reference, 837 parts, 829 texts)
1. text “Knot-berries” (`Domestic Encyclopædia (1802)/Knot-berries`), part 790 of `Domestic Encyclopædia (1802)` (collection, 1,612 parts, 1,554 texts)
1. text “The Rosy Morn” (`Five Songs ("Jockey to the Fair")/The Rosy Morn`), part 5 of `Five Songs ("Jockey to the Fair")` (book, 5 parts, 5 texts)
1. text “Accent” (`Encyclopædia Britannica, Ninth Edition/Accent`), part 154 of `Encyclopædia Britannica, Ninth Edition` (reference, 3,435 parts, 3,370 texts)
1. text “The unconscious body-guard” (`Once a Week (magazine)/Series 1/Volume 3/The unconscious body-guard`), part 448 of `Once a Week (magazine)` (collection, 1,851 parts, 1,832 texts)
1. text “Bashtchelik (or, Real Steel)” (`Edmund Dulac's fairy book/Bashtchelik (or, Real Steel)`), part 10 of `Edmund Dulac's fairy book` (book, 15 parts, 15 texts)
1. text “p.377” (`Transactions of the Geological Society/Series 1/Volume 2/On some new Varieties of Fossil Alcyonia`), part 43 of `Transactions of the Geological Society` (collection, 110 parts, 97 texts)
1. text “Gaduge” (`Castes and Tribes of Southern India/Gaduge`), part 699 of `Castes and Tribes of Southern India` (book, 2,228 parts, 2,218 texts)
1. text “Rives, William Cabell” (`1911 Encyclopædia Britannica/Rives, William Cabell`), part 29428 of `1911 Encyclopædia Britannica` (reference, 37,420 parts, 37,256 texts)
1. text “Sail” (`The New Student's Reference Work/Sail`), part 3568 of `The New Student's Reference Work` (book, 4,695 parts, 4,644 texts)
1. text “Coraës, Adamantios” (`1911 Encyclopædia Britannica/Coraës, Adamantios`), part 9271 of `1911 Encyclopædia Britannica` (reference, 37,420 parts, 37,256 texts)
1. text “To Julia Homesley” (`Poems (Katz)/To Julia Homesley`), part 17 of `Poems (Katz)` (book, 26 parts, 26 texts)
1. text “Stone, Lucy Blackwell” (`The Encyclopedia Americana (1920)/Stone, Lucy Blackwell`), part 2809 of `The Encyclopedia Americana (1920)` (reference, 3,166 parts, 3,158 texts)
1. text “The Triumph of Death” (`Poems (Hooper)/The Triumph of Death`), part 48 of `Poems (Hooper)` (book, 79 parts, 78 texts)
1. text “Shrew” (`The New Student's Reference Work/Shrew`), part 3722 of `The New Student's Reference Work` (book, 4,695 parts, 4,644 texts)
1. text “Ephemerides of the Planets” (`Astrology (Shoemaker)/Ephemerides of the Planets`), part 12 of `Astrology (Shoemaker)` (book, 14 parts, 13 texts)
1. text “Walford, Edward” (`Dictionary of National Biography, 1885-1900/Walford, Edward`), part 26346 of `Dictionary of National Biography, 1885-1900` (reference, 28,089 parts, 28,080 texts)
1. text “Bonython, Charles” (`Dictionary of National Biography, 1885-1900/Bonython, Charles`), part 2627 of `Dictionary of National Biography, 1885-1900` (reference, 28,089 parts, 28,080 texts)
1. text “Bisset, Sir William Sinclair Smith” (`Dictionary of Indian Biography/Bisset, Sir William Sinclair Smith`), part 243 of `Dictionary of Indian Biography` (reference, 663 parts, 656 texts)
1. text “Grillparzer, Franz” (`The New International Encyclopædia/Grillparzer, Franz`), part 5255 of `The New International Encyclopædia` (book, 10,866 parts, 10,851 texts)
1. text “Mackness, George Owen Carr” (`Alumni Oxonienses: the Members of the University of Oxford, 1715-1886/Mackness, George Owen Carr`), part 35942 of `Alumni Oxonienses: the Members of the University of Oxford, 1715-1886` (reference, 63,225 parts, 63,216 texts)
1. text “Ker, David (1)” (`Alumni Oxonienses: the Members of the University of Oxford, 1715-1886/Ker, David (1)`), part 31994 of `Alumni Oxonienses: the Members of the University of Oxford, 1715-1886` (reference, 63,225 parts, 63,216 texts)
1. text “She Keeps a Good Man Dangling, but—” (`O Genteel Lady!/Chapter 13`), part 13 of `O Genteel Lady!` (book, 15 parts, 15 texts)
1. text “SPARHAM. Rev. Charles George” (`Who's Who in the Far East/SPARHAM. Rev. Charles George`), part 1695 of `Who's Who in the Far East` (reference, 2,182 parts, 2,181 texts)
1. text “What is True Civilization?” (`Indian Home Rule (5th edition)/What is True Civilization`), part 25 of `Indian Home Rule (5th edition)` (collection, 26 parts, 22 texts)
1. text “Dramatis Personae” (`The Adventures of David Simple (1904)/Dramatis Personae`), part 3 of `The Adventures of David Simple (1904)` (book, 42 parts, 1 texts)
1. text “American Civil War” (`1911 Encyclopædia Britannica/American Civil War`), part 1469 of `1911 Encyclopædia Britannica` (reference, 37,420 parts, 37,256 texts)
1. text “Treatment of Skin Disorders” (`Beauty Culture (Woodbury)/Chapter 18`), part 19 of `Beauty Culture (Woodbury)` (book, 29 parts, 26 texts)

### C: oversized (399)

1. `The Speech (Sanders)`: single, book, 1 parts, 0 missing, order –, unproofread
    - text: The Speech (Sanders)
1. `1911 Encyclopædia Britannica`: subpages, reference, 37,420 parts, 0 missing, order natural, proofread
    - toc: 1911 Encyclopædia Britannica
    - text: "Address, The" (`1911 Encyclopædia Britannica/"Address, The"`) [hierarchy]
    - text: "Alabama" Arbitration (`1911 Encyclopædia Britannica/"Alabama" Arbitration`) [hierarchy]
    - text: "Challenger" Expedition (`1911 Encyclopædia Britannica/"Challenger" Expedition`) [hierarchy]
    - text: "Constitution of Athens" (`1911 Encyclopædia Britannica/"Constitution of Athens"`) [hierarchy]
    - text: "Consulate of the Sea" (`1911 Encyclopædia Britannica/"Consulate of the Sea"`) [hierarchy]
    - text: "Flying Dutchman" (`1911 Encyclopædia Britannica/"Flying Dutchman"`) [hierarchy]
    - text: "Indies, Laws of the" (`1911 Encyclopædia Britannica/"Indies, Laws of the"`) [hierarchy]
    - text: "Surville, Clotilde de" (`1911 Encyclopædia Britannica/"Surville, Clotilde de"`) [hierarchy]
    - text: 'Abaye (`1911 Encyclopædia Britannica/'Abaye`) [hierarchy]
    - text: 'Abba 'Arika (`1911 Encyclopædia Britannica/'Abba 'Arika`) [hierarchy]
    - text: 'Abbahu (`1911 Encyclopædia Britannica/'Abbahu`) [hierarchy]
    - … 37,409 more
1. `Committee on Financial Services Thursday, September 25, 2003`: single, single, 1 parts, 0 missing, order –, unknown, excluded junk-template
    - text: Committee on Financial Services Thursday, September 25, 2003
1. `The Eternal Husband and Other Stories`: subpages, book, 2 parts, 1 missing, order toc, proofread
    - toc: The Eternal Husband and Other Stories
    - text: The Eternal Husband (`The Eternal Husband and Other Stories/The Eternal Husband`) [container]
    - text: A Gentle Spirit (`The Eternal Husband and Other Stories/A Gentle Spirit`) [container]
1. `Story of the War in South Africa 1899-1900`: single, single, 1 parts, 0 missing, order –, unproofread
    - text: Story of the War in South Africa 1899-1900
1. `Bible (Tyndale)`: subpages, collection, 33 parts, 0 missing, order toc, unknown
    - toc: Bible (Tyndale)
    - text: Genesis (`Bible (Tyndale)/Genesis`) [container]
    - text: Exodus (`Bible (Tyndale)/Exodus`) [container]
    - text: Leviticus (`Bible (Tyndale)/Leviticus`) [container]
    - text: Numbers (`Bible (Tyndale)/Numbers`) [container]
    - text: Deuteronomy (`Bible (Tyndale)/Deuteronomy`) [container]
    - text: Jonah (`Bible (Tyndale)/Jonah`) [container]
    - text: Matthew (`Bible (Tyndale)/Matthew`) [container]
    - text: Mark (`Bible (Tyndale)/Mark`) [container]
    - text: Luke (`Bible (Tyndale)/Luke`) [container]
    - text: John (`Bible (Tyndale)/John`) [container]
    - text: Acts (`Bible (Tyndale)/Acts`) [container]
    - … 22 more
1. `The Conquest: The True Story of Lewis and Clark`: subpages, book, 3 parts, 0 missing, order chain, unproofread
    - front: The Conquest: The True Story of Lewis and Clark
    - chapter: Book 1 (`The Conquest: The True Story of Lewis and Clark/Book 1`) [container]
    - chapter: Book 2 (`The Conquest: The True Story of Lewis and Clark/Book 2`) [container]
    - chapter: Book 3 (`The Conquest: The True Story of Lewis and Clark/Book 3`) [container]
1. `Nobody's Boy`: subpages, book, 35 parts, 0 missing, order scan, proofread
    - front: Nobody's Boy
    - chapter: Introduction (`Nobody's Boy/Introduction`) [container]
    - text: My Village Home (`Nobody's Boy/Chapter I`) [container]
    - text: Whole text (`Nobody's Boy/Whole text`) [container]
    - text: My Adopted Father (`Nobody's Boy/Chapter II`) [container]
    - text: Signor Vitalis' Company (`Nobody's Boy/Chapter III`) [container]
    - text: The Maternal House (`Nobody's Boy/Chapter IV`) [container]
    - text: En Route (`Nobody's Boy/Chapter V`) [container]
    - text: My Début (`Nobody's Boy/Chapter VI`) [container]
    - text: Child and Animal Learning (`Nobody's Boy/Chapter VII`) [container]
    - text: One Who Had Known a King (`Nobody's Boy/Chapter VIII`) [container]
    - text: Arrested (`Nobody's Boy/Chapter IX`) [container]
    - … 24 more
1. `Impressions of Theophrastus Such, Essays and Leaves from a note-book`: virtual, book, 1 parts, 0 missing, order single, proofread
    - text: Theophrastus Such (`Impressions of Theophrastus Such, Essays and Leaves from a note-book/Theophrastus Such`)
1. `Constitution of Malaysia`: single, single, 1 parts, 0 missing, order –, unknown
    - text: Constitution of Malaysia
1. `Secretary Root's Record:"Marked Severities" in Philippine Warfare`: single, single, 1 parts, 0 missing, order –, unknown
    - text: Secretary Root's Record:"Marked Severities" in Philippine Warfare
1. `Marathon speech against ObamaCare`: single, single, 1 parts, 0 missing, order –, unknown
    - text: Marathon speech against ObamaCare
1. `Better Eyesight Magazine`: subpages, collection, 97 parts, 0 missing, order toc, unknown
    - toc: Better Eyesight Magazine
    - text: July 1919 (`Better Eyesight Magazine/July 1919`) [container]
    - text: August 1919 (`Better Eyesight Magazine/August 1919`) [container]
    - text: September 1919 (`Better Eyesight Magazine/September 1919`) [container]
    - text: October 1919 (`Better Eyesight Magazine/October 1919`) [container]
    - text: November 1919 (`Better Eyesight Magazine/November 1919`) [container]
    - text: December 1919 (`Better Eyesight Magazine/December 1919`) [container]
    - text: January 1920 (`Better Eyesight Magazine/January 1920`) [container]
    - text: February 1920 (`Better Eyesight Magazine/February 1920`) [container]
    - text: March 1920 (`Better Eyesight Magazine/March 1920`) [container]
    - text: April 1920 (`Better Eyesight Magazine/April 1920`) [container]
    - text: May 1920 (`Better Eyesight Magazine/May 1920`) [container]
    - … 86 more
1. `A Dark Night's Work and Other Tales`: subpages, book, 10 parts, 0 missing, order scan, proofread
    - front: A Dark Night's Work and Other Tales
    - text: A Dark Night's Work (`A Dark Night's Work and Other Tales/A Dark Night's Work`) [container]
    - text: Cousin Phillis (`A Dark Night's Work and Other Tales/Cousin Phillis`) [container]
    - text: The Grey Woman (`A Dark Night's Work and Other Tales/The Grey Woman`) [container]
    - text: The Heart of John Middleton (`A Dark Night's Work and Other Tales/John Middleton`) [container]
    - text: Traits and Stories of the Huguenots (`A Dark Night's Work and Other Tales/Stories of the Hugenots`) [container]
    - text: Stories of the Huguenots (`A Dark Night's Work and Other Tales/Stories of the Huguenots`) [container]
    - text: Six Weeks at Heppenheim (`A Dark Night's Work and Other Tales/Six Weeks at Heppenheim`) [container]
    - text: Lizzie Leigh (`A Dark Night's Work and Other Tales/Lizzie Leigh`) [container]
    - text: The Crooked Branch (`A Dark Night's Work and Other Tales/The Crooked Branch`) [container]
    - text: The Sextons Hero (`A Dark Night's Work and Other Tales/Sextons Hero`) [container]
1. `Men of 1914`: subpages, collection, 12 parts, 0 missing, order chain, unknown
    - toc: Men of 1914
    - text: A (`Men of 1914/A`) [container]
    - text: B (`Men of 1914/B`) [container]
    - chapter: C (`Men of 1914/C`) [container]
    - chapter: D (`Men of 1914/D`) [container]
    - text: E (`Men of 1914/E`) [container]
    - text: F (`Men of 1914/F`) [container]
    - text: G (`Men of 1914/G`) [container]
    - text: H (`Men of 1914/H`) [container]
    - chapter: I (`Men of 1914/I`) [container]
    - text: J (`Men of 1914/J`) [container]
    - text: K (`Men of 1914/K`) [container]
    - … 1 more
1. `History of Norfolk`: subpages, book, 56 parts, 5 missing, order toc, unproofread
    - front: History of Norfolk
    - chapter: Introduction (`History of Norfolk/Introduction`) [container]
    - volume: Volume 1 (`History of Norfolk/Volume 1`) [container]
      - text: The Hundred of Diss (`History of Norfolk/Volume 1/Diss`) [container]
      - text: The Hundred of Giltcross (`History of Norfolk/Volume 1/Giltcross`) [container]
      - text: The Hundred of Shropham (`History of Norfolk/Volume 1/Shropham`) [container]
    - volume: Volume 2 (`History of Norfolk/Volume 2`) [container]
      - text: Thetford and its history (`History of Norfolk/Volume 2/Thetford`) [container]
      - text: The Hundred of Grimshou (`History of Norfolk/Volume 2/Grimeshou`) [container]
      - text: The Hundred of Wayland (`History of Norfolk/Volume 2/Wayland`) [container]
      - text: The Hundred of Forehoe (`History of Norfolk/Volume 2/Forehoe`) [container]
    - volume: Volume 3 (`History of Norfolk/Volume 3`) [container]
    - … 45 more
1. `Marcus Aurelius Antoninus - His Meditations concerning himselfe`: single, single, 1 parts, 0 missing, order –, unknown
    - text: Marcus Aurelius Antoninus - His Meditations concerning himselfe
1. `Selected Letters of Friedrich Nietzsche`: single, single, 1 parts, 0 missing, order –, unknown
    - text: Selected Letters of Friedrich Nietzsche
1. `The Complete Works of Geoffrey Chaucer`: mixed, collection, 169 parts, 126 missing, order toc, unproofread
    - toc: The Complete Works of Geoffrey Chaucer
    - volume: Volume I. - Romaunt of the Rose & Minor Poems (`The Complete Works of Geoffrey Chaucer/Volume 1`) [hierarchy]
      - text: General Introduction (`Chaucer's Works (ed. Skeat) Vol. I/Introduction`) [container]
      - text: Life of Chaucer (`Chaucer's Works (ed. Skeat) Vol. I/Life`) [container]
      - text: List of Chaucer's Works (`Chaucer's Works (ed. Skeat) Vol. I/Works`) [container]
      - chapter: Errata and Addenda (`Chaucer's Works (ed. Skeat) Vol. I/Errata`) [container]
      - chapter: Introduction to Romaunt of the Rose (`Chaucer's Works (ed. Skeat) Vol. I/Romaunt - Introduction`) [container]
      - chapter: Introduction to Minor Poems (`Chaucer's Works (ed. Skeat) Vol. I/Minor Poems - Introduction`) [container]
      - text: Fragment A (`Chaucer's Works (ed. Skeat) Vol. I/Fragment A`) [container]
      - text: Fragment B (`Chaucer's Works (ed. Skeat) Vol. I/Fragment B`) [container]
      - text: Fragment C (`Chaucer's Works (ed. Skeat) Vol. I/Fragment C`) [container]
      - text: An A. B. C. (`Chaucer's Works (ed. Skeat) Vol. I/I`) [container]
    - … 158 more
1. `Rural Hours`: subpages, book, 6 parts, 0 missing, order scan, proofread
    - front: Rural Hours
    - chapter: front matter (`Rural Hours/front matter`) [container]
    - chapter: Preface (`Rural Hours/Preface`) [container]
    - text: Spring (`Rural Hours/Spring`) [container]
    - text: Summer (`Rural Hours/Summer`) [container]
    - text: Autumn (`Rural Hours/Autumn`) [container]
    - text: Winter (`Rural Hours/Winter`) [container]
1. `Lehrmann v Network Ten Pty Limited (Trial Judgment)`: single, book, 1 parts, 0 missing, order –, proofread
    - text: Lehrmann v Network Ten Pty Limited (Trial Judgment)
1. `A literal translation of the Saxon Chronicle`: single, book, 1 parts, 0 missing, order –, proofread
    - text: A literal translation of the Saxon Chronicle
1. `A Century of Birmingham Life, 1`: subpages, book, 30 parts, 0 missing, order chain, proofread
    - front: A Century of Birmingham Life, 1
    - chapter: Preface (`A Century of Birmingham Life, 1/Preface`) [container]
    - chapter: Contents (`A Century of Birmingham Life, 1/Contents`) [container]
    - chapter: Introduction (`A Century of Birmingham Life, 1/Introduction`) [container]
    - chapter: Chapter 1:1 (`A Century of Birmingham Life, 1/Chapter 1:1`) [container]
    - chapter: Chapter 1:2 (`A Century of Birmingham Life, 1/Chapter 1:2`) [container]
    - chapter: Chapter 1:4 (`A Century of Birmingham Life, 1/Chapter 1:4`) [container]
    - chapter: Chapter 1:5 (`A Century of Birmingham Life, 1/Chapter 1:5`) [container]
    - chapter: Chapter 1:6 (`A Century of Birmingham Life, 1/Chapter 1:6`) [container]
    - chapter: Chapter 2:1 (`A Century of Birmingham Life, 1/Chapter 2:1`) [container]
    - chapter: Chapter 2:2 (`A Century of Birmingham Life, 1/Chapter 2:2`) [container]
    - chapter: Chapter 2:3 (`A Century of Birmingham Life, 1/Chapter 2:3`) [container]
    - … 19 more
1. `Manual for Revolutionary Leaders`: subpages, book, 3 parts, 0 missing, order chain, unproofread
    - front: Manual for Revolutionary Leaders
    - text: Generation of Revolutionaries (`Manual for Revolutionary Leaders/Part 1`) [hierarchy]
    - chapter: Part II: Rise to Leadership (`Manual for Revolutionary Leaders/Part 2`) [hierarchy]
    - chapter: III. Seizure of State Power (`Manual for Revolutionary Leaders/Part 3`) [hierarchy]
1. `Constitution of the State of Florida (1968)`: single, single, 1 parts, 0 missing, order –, validated
    - text: Constitution of the State of Florida (1968)
1. `Parents and Children`: single, single, 1 parts, 0 missing, order –, unknown
    - text: Parents and Children
1. `Chester Rand`: single, single, 1 parts, 0 missing, order –, unknown
    - text: Chester Rand
1. `Copyright Law Revision (House Report No. 94-1476)`: subpages, book, 2 parts, 0 missing, order natural, validated
    - front: Copyright Law Revision (House Report No. 94-1476)
    - text: Annotated (`Copyright Law Revision (House Report No. 94-1476)/Annotated`) [container]
    - chapter: Errata (`Copyright Law Revision (House Report No. 94-1476)/Errata`) [container]
1. `Putney debates`: single, single, 1 parts, 0 missing, order –, unknown
    - text: Putney debates
1. `The Catholic Dogma: Extra Ecclesiam Nullus Omnino Salvatur`: subpages, collection, 7 parts, 0 missing, order chain, unknown
    - front: The Catholic Dogma: Extra Ecclesiam Nullus Omnino Salvatur
    - chapter: Introduction (`The Catholic Dogma: Extra Ecclesiam Nullus Omnino Salvatur/Chapter I`) [container]
    - text: The Infallible and Only True Guide to Heaven (`The Catholic Dogma: Extra Ecclesiam Nullus Omnino Salvatur/Chapter II`) [container]
    - text: The Great Revolt Against Christ (`The Catholic Dogma: Extra Ecclesiam Nullus Omnino Salvatur/Chapter III`) [container]
    - text: Bishop Coxe's Dishonesty (`The Catholic Dogma: Extra Ecclesiam Nullus Omnino Salvatur/Chapter IV`) [container]
    - volume: Introduction: Refutation of the False Assertions of Reverends S. O. Cronin and Young (`The Catholic Dogma: Extra Ecclesiam Nullus Omnino Salvatur/Chapter V`) [container]
      - chapter: Part I. There is No Salvation Out of the Roman Catholic Church for those who die without being united to her (`The Catholic Dogma: Extra Ecclesiam Nullus Omnino Salvatur/Chapter V/Part 1`) [container]
      - text: Of Those Heretics who are not guilty of the sin of Heresy (`The Catholic Dogma: Extra Ecclesiam Nullus Omnino Salvatur/Chapter V/Part 2`) [container]

### G: reference works (35)

1. `Popular Science Monthly`: subpages, reference, 8,174 parts, 15 missing, order chain, proofread
    - front: Popular Science Monthly
    - volume: Volume 1 (`Popular Science Monthly/Volume 1`) [container]
      - volume: May 1872 (`Popular Science Monthly/Volume 1/May 1872`) [container]
        - text: The Study of Sociology, Chapter I (`Popular Science Monthly/Volume 1/May 1872/The Study of Sociology I`) [container]
        - text: The Recent Eclipse of the Sun (`Popular Science Monthly/Volume 1/May 1872/The Recent Eclipse of the Sun`) [container]
        - text: Science and Immortality (`Popular Science Monthly/Volume 1/May 1872/Science and Immortality`) [container]
        - text: The Source of Labor (`Popular Science Monthly/Volume 1/May 1872/The Source of Labor`) [container]
        - text: Quetelet on the Science of Man (`Popular Science Monthly/Volume 1/May 1872/Quetelet on the Science of Man`) [container]
        - text: Disinfection and Disinfectants (`Popular Science Monthly/Volume 1/May 1872/Disinfection and Disinfectants`) [container]
        - text: Natural History of Man I By Jean Louis Armand de Quatrefages de Bréau translated by Eliza Ann Youmans (`Popular Science Monthly/Volume 1/May 1872/The Natural History of Man I`) [container]
        - text: The Causes of Dyspepsia By Arthur Leared (`Popular Science Monthly/Volume 1/May 1872/The Causes of Dyspepsia`) [container]
        - text: Woman and Political Power By Luke Owen Pike (`Popular Science Monthly/Volume 1/May 1872/Woman and Political Power`) [container]
    - … 8,163 more
1. `1911 Encyclopædia Britannica`: subpages, reference, 37,420 parts, 0 missing, order natural, proofread
    - toc: 1911 Encyclopædia Britannica
    - text: "Address, The" (`1911 Encyclopædia Britannica/"Address, The"`) [hierarchy]
    - text: "Alabama" Arbitration (`1911 Encyclopædia Britannica/"Alabama" Arbitration`) [hierarchy]
    - text: "Challenger" Expedition (`1911 Encyclopædia Britannica/"Challenger" Expedition`) [hierarchy]
    - text: "Constitution of Athens" (`1911 Encyclopædia Britannica/"Constitution of Athens"`) [hierarchy]
    - text: "Consulate of the Sea" (`1911 Encyclopædia Britannica/"Consulate of the Sea"`) [hierarchy]
    - text: "Flying Dutchman" (`1911 Encyclopædia Britannica/"Flying Dutchman"`) [hierarchy]
    - text: "Indies, Laws of the" (`1911 Encyclopædia Britannica/"Indies, Laws of the"`) [hierarchy]
    - text: "Surville, Clotilde de" (`1911 Encyclopædia Britannica/"Surville, Clotilde de"`) [hierarchy]
    - text: 'Abaye (`1911 Encyclopædia Britannica/'Abaye`) [hierarchy]
    - text: 'Abba 'Arika (`1911 Encyclopædia Britannica/'Abba 'Arika`) [hierarchy]
    - text: 'Abbahu (`1911 Encyclopædia Britannica/'Abbahu`) [hierarchy]
    - … 37,409 more
1. `An Ainu–English–Japanese Dictionary`: subpages, reference, 30 parts, 20 missing, order toc, unproofread
    - front: An Ainu–English–Japanese Dictionary
    - text: (Printing information) (`An Ainu–English–Japanese Dictionary/Printing information`) [container]
    - text: Dedication (`An Ainu–English–Japanese Dictionary/Dedication`) [container]
    - text: Forward (`An Ainu–English–Japanese Dictionary/Foreword`) [container]
    - chapter: Preface (`An Ainu–English–Japanese Dictionary/Preface`) [container]
    - chapter: Introduction (`An Ainu–English–Japanese Dictionary/Introduction`) [container]
    - text: List of Abbreviations (`An Ainu–English–Japanese Dictionary/List of Abbreviations`) [container]
    - text: A (`An Ainu–English–Japanese Dictionary/A`) [container]
    - text: B (`An Ainu–English–Japanese Dictionary/B`) [container]
    - chapter: C (`An Ainu–English–Japanese Dictionary/C`) [container]
    - text: F (`An Ainu–English–Japanese Dictionary/F`) [container]
    - text: Y (`An Ainu–English–Japanese Dictionary/Y`) [container]
    - … 19 more
1. `Western Mandarin`: subpages, reference, 22 parts, 0 missing, order chain, unproofread
    - front: Western Mandarin
    - chapter: Introduction (`Western Mandarin/Introduction`) [container]
    - text: Radical Index (`Western Mandarin/Radical Index`) [container]
    - volume: Words with Characters (`Western Mandarin/Words with Characters`) [container]
      - text: The 1st radical. (一) (`Western Mandarin/Words with Characters/1`) [container]
      - text: The 2nd radical. (丨) (`Western Mandarin/Words with Characters/2`) [container]
      - text: The 3rd radical. (丶) (`Western Mandarin/Words with Characters/3`) [container]
      - text: The 4th radical. (丿) (`Western Mandarin/Words with Characters/4`) [container]
      - text: The 5th radical. (乙) (`Western Mandarin/Words with Characters/5`) [container]
      - text: The 6th radical. (亅) (`Western Mandarin/Words with Characters/6`) [container]
      - text: The 7th radical. (二) (`Western Mandarin/Words with Characters/7`) [container]
      - text: The 8th radical. (亠) (`Western Mandarin/Words with Characters/8`) [container]
    - … 11 more
1. `Representative women of New England`: subpages, reference, 283 parts, 0 missing, order scan, unproofread
    - front: Representative women of New England
    - text: Elizabeth C. Agassiz (`Representative women of New England/Elizabeth C. Agassiz`) [container]
    - text: Cheney, Ednah D. (`Representative women of New England/Ednah D. Cheney`) [container]
    - text: Elizabeth Porter Gould (`Representative women of New England/Elizabeth Porter Gould`) [container]
    - text: Louise Chandler Moulton (`Representative women of New England/Louise Chandler Moulton`) [container]
    - text: Lillian M. N. Stevens (`Representative women of New England/Lillian M. N. Stevens`) [container]
    - text: Abby K. Foster (`Representative women of New England/Abby K. Foster`) [container]
    - text: Partington, Lura C. (`Representative women of New England/Lura C. Partington`) [container]
    - text: Evelyn G. Sutherland (`Representative women of New England/Evelyn G. Sutherland`) [container]
    - text: Mary J. B. Lincoln (`Representative women of New England/Mary J. B. Lincoln`) [container]
    - text: Mary A. Greene (`Representative women of New England/Mary A. Greene`) [container]
    - text: Mary D. H. Prang (`Representative women of New England/Mary D. H. Prang`) [container]
    - … 272 more
1. `A Chinese Biographical Dictionary`: subpages, reference, 430 parts, 558 missing, order toc-partial, proofread
    - front: A Chinese Biographical Dictionary
    - chapter: Front matter (`A Chinese Biographical Dictionary/Preface`) [container]
    - chapter: Index to Proper Names and Other Terms for which no Characters are Given (`A Chinese Biographical Dictionary/Index to Proper Names`) [container]
    - text: A-chiao (`A Chinese Biographical Dictionary/A-chiao`) [container]
    - text: A-lao-wa-ting (`A Chinese Biographical Dictionary/A-lao-wa-ting`) [container]
    - text: A-lu-t'ai (`A Chinese Biographical Dictionary/A-lu-t'ai`) [container]
    - text: Achakpa (`A Chinese Biographical Dictionary/Achakpa`) [container]
    - text: Ai-hsing-a (`A Chinese Biographical Dictionary/Ai-hsing-a`) [container]
    - text: Ai Ti (`A Chinese Biographical Dictionary/Ai Ti`) [container]
    - text: Ai Tsung (`A Chinese Biographical Dictionary/Ai Tsung`) [container]
    - text: Akuta (`A Chinese Biographical Dictionary/Akuta`) [container]
    - text: Amôgha (`A Chinese Biographical Dictionary/Amôgha`) [container]
    - … 419 more
1. `Dictionary of Indian Biography`: subpages, reference, 663 parts, 0 missing, order natural, proofread
    - front: Dictionary of Indian Biography
    - text: A (`Dictionary of Indian Biography/A`) [container]
    - text: Abadie, Henry Richard (`Dictionary of Indian Biography/Abadie, Henry Richard`) [hierarchy]
    - text: Abbott, Augustus (`Dictionary of Indian Biography/Abbott, Augustus`) [hierarchy]
    - text: Abbott, H. Edward Stacey (`Dictionary of Indian Biography/Abbott, H. Edward Stacey`) [hierarchy]
    - text: Abbott, Saunders Alexius (`Dictionary of Indian Biography/Abbott, Saunders Alexius`) [hierarchy]
    - text: Abbott, Sir Frederick (`Dictionary of Indian Biography/Abbott, Sir Frederick`) [hierarchy]
    - text: Abbott, Sir James (`Dictionary of Indian Biography/Abbott, Sir James`) [hierarchy]
    - text: Abdul Hak, Sirdar Diler Jung ul Mulk (`Dictionary of Indian Biography/Abdul Hak, Sirdar Diler Jung ul Mulk`) [hierarchy]
    - text: Abdul Latif, Nawab Bahadur (`Dictionary of Indian Biography/Abdul Latif, Nawab Bahadur`) [hierarchy]
    - text: Abdul Musseah, Rev. (`Dictionary of Indian Biography/Abdul Musseah, Rev.`) [hierarchy]
    - text: Abel, Clarke (`Dictionary of Indian Biography/Abel, Clarke`) [hierarchy]
    - … 652 more
1. `Encyclopædia Britannica, Ninth Edition`: subpages, reference, 3,435 parts, 10 missing, order natural, unproofread
    - toc: Encyclopædia Britannica, Ninth Edition
    - text: A (`Encyclopædia Britannica, Ninth Edition/A`) [hierarchy]
    - text: A'Beckett, Gilbert Abbott (`Encyclopædia Britannica, Ninth Edition/A'Beckett, Gilbert Abbott`) [hierarchy]
    - text: Aa (`Encyclopædia Britannica, Ninth Edition/Aa`) [hierarchy]
    - text: Aachen (`Encyclopædia Britannica, Ninth Edition/Aachen`) [hierarchy]
    - text: Aalborg (`Encyclopædia Britannica, Ninth Edition/Aalborg`) [hierarchy]
    - text: Aalen (`Encyclopædia Britannica, Ninth Edition/Aalen`) [hierarchy]
    - text: Aar (`Encyclopædia Britannica, Ninth Edition/Aar`) [hierarchy]
    - text: Aarau (`Encyclopædia Britannica, Ninth Edition/Aarau`) [hierarchy]
    - text: Aard-vark (`Encyclopædia Britannica, Ninth Edition/Aard-vark`) [hierarchy]
    - text: Aargau (`Encyclopædia Britannica, Ninth Edition/Aargau`) [hierarchy]
    - text: Aarhuus (`Encyclopædia Britannica, Ninth Edition/Aarhuus`) [hierarchy]
    - … 3,424 more
1. `Thom's Irish Who's Who`: subpages, reference, 2,378 parts, 0 missing, order natural, unknown
    - toc: Thom's Irish Who's Who
    - text: Names: A (`Thom's Irish Who's Who/A`) [container]
    - text: Abercorn, Duke of (`Thom's Irish Who's Who/Abercorn, Duke of`) [hierarchy]
    - text: Abraham, Lieut.-Col. James Johnston (`Thom's Irish Who's Who/Abraham, Lieut.-Col. James Johnston`) [hierarchy]
    - text: Acheson, Hon. Patrick Charles George Cavendish (`Thom's Irish Who's Who/Acheson, Hon. Patrick Charles George Cavendish`) [hierarchy]
    - text: Acheson, Viscount, Archibald Alexander John Stanley Acheson (`Thom's Irish Who's Who/Acheson, Viscount, Archibald Alexander John Stanley Acheson`) [hierarchy]
    - text: Achonry, Bishop of (`Thom's Irish Who's Who/Achonry, Bishop of`) [hierarchy]
    - text: Adair, General Sir William Thompson (`Thom's Irish Who's Who/Adair, General Sir William Thompson`) [hierarchy]
    - text: Adair, Sir Robert Shafto (`Thom's Irish Who's Who/Adair, Sir Robert Shafto`) [hierarchy]
    - text: Adam, Major W. Augustus (`Thom's Irish Who's Who/Adam, Major W. Augustus`) [hierarchy]
    - text: Adams, Samuel Allen (`Thom's Irish Who's Who/Adams, Samuel Allen`) [hierarchy]
    - text: Adams, William Henry (`Thom's Irish Who's Who/Adams, William Henry`) [hierarchy]
    - … 2,367 more
1. `The Indian Biographical Dictionary (1915)`: subpages, reference, 2,494 parts, 0 missing, order toc-partial, proofread
    - front: The Indian Biographical Dictionary (1915)
    - chapter: Advertisements 1 (`The Indian Biographical Dictionary (1915)/Advertisements 1`) [container]
    - text: Frontispiece (`The Indian Biographical Dictionary (1915)/Frontispiece`) [hierarchy]
    - chapter: Contents (`The Indian Biographical Dictionary (1915)/Contents`) [hierarchy]
    - chapter: Advertisements 2 (`The Indian Biographical Dictionary (1915)/Advertisements 2`) [hierarchy]
    - chapter: Index to Advertisements (`The Indian Biographical Dictionary (1915)/Index to Advertisements`) [hierarchy]
    - chapter: Preface (`The Indian Biographical Dictionary (1915)/Preface`) [hierarchy]
    - text: List of Abbreviations (`The Indian Biographical Dictionary (1915)/List of Abbreviations`) [hierarchy]
    - chapter: Errata (`The Indian Biographical Dictionary (1915)/Errata`) [hierarchy]
    - text: The Royal Family of Great Britain and Ireland (`The Indian Biographical Dictionary (1915)/The Royal Family of Great Britain and Ireland`) [hierarchy]
    - text: Abbas Ali Baig, Mirza (`The Indian Biographical Dictionary (1915)/Abbas Ali Baig, Mirza`) [hierarchy]
    - text: Abbott, Col. Herbert Edward Stacey (`The Indian Biographical Dictionary (1915)/Abbott, Col. Herbert Edward Stacey`) [hierarchy]
    - … 2,483 more
1. `Appletons' Cyclopædia of American Biography`: mixed, reference, 2,539 parts, 0 missing, order natural, unproofread
    - toc: Appletons' Cyclopædia of American Biography
    - text: Frelinghuysen, John (`Appleton's Cyclopedia of American Biography/Frelinghuysen, John`) [container]
    - text: Aaron, Samuel (`Appletons' Cyclopædia of American Biography/Aaron, Samuel`) [hierarchy]
    - text: Abad y Queipo, Manuel (`Appletons' Cyclopædia of American Biography/Abad y Queipo, Manuel`) [hierarchy]
    - text: Abad, Diego José (`Appletons' Cyclopædia of American Biography/Abad, Diego José`) [hierarchy]
    - text: Abadie, Eugene Hilarian (`Appletons' Cyclopædia of American Biography/Abadie, Eugene Hilarian`) [hierarchy]
    - text: Abascal, José Fernando (`Appletons' Cyclopædia of American Biography/Abascal, José Fernando`) [hierarchy]
    - text: Abasolo, Mariano (`Appletons' Cyclopædia of American Biography/Abasolo, Mariano`) [hierarchy]
    - text: Abbadie de St. Germain (`Appletons' Cyclopædia of American Biography/Abbadie de St. Germain`) [hierarchy]
    - text: Abbadie, Antoine Thomson d’ (`Appletons' Cyclopædia of American Biography/Abbadie, Antoine Thomson d’`) [hierarchy]
    - text: Abbe, Cleveland (`Appletons' Cyclopædia of American Biography/Abbe, Cleveland`) [hierarchy]
    - text: Abbeville, Claude d’ (`Appletons' Cyclopædia of American Biography/Abbeville, Claude d’`) [hierarchy]
    - … 2,528 more
1. `United States Statutes at Large`: mixed, reference, 4,888 parts, 721 missing, order toc-partial, proofread
    - toc: United States Statutes at Large
    - volume: Volume 1 (`United States Statutes at Large/Volume 1`) [container]
      - volume: Cover and Front Matter (`United States Statutes at Large/Volume 1/Front Matter`) [container]
        - chapter: Front Matter (`United States Statutes at Large/Volume 1/Front Matter/all`) [container]
        - chapter: Front Matter > Front pages (`United States Statutes at Large/Volume 1/Front pages`) [container]
        - chapter: Front Matter &gt; To the Honorable Joseph Story (`United States Statutes at Large/Volume 1/To the Honorable Joseph Story`) [container]
        - chapter: Front Matter > Advertisement (`United States Statutes at Large/Volume 1/Advertisement`) [container]
        - chapter: Front Matter > Letters Addressed to the Publishers (`United States Statutes at Large/Volume 1/Letters Addressed to the Publishers`) [container]
        - chapter: Front Matter &gt; Preface (`United States Statutes at Large/Volume 1/Preface`) [container]
        - chapter: Front Matter > Extracts from Letters Addressed to the Editor (`United States Statutes at Large/Volume 1/Extracts from Letters Addressed to the Editor`) [container]
      - volume: Lists and Tables (`United States Statutes at Large/Volume 1/Lists and Tables`) [container]
        - text: Acts of the First Congress of the United States (`United States Statutes at Large/Volume 1/Lists and Tables/1st Congress`) [container]
    - … 4,877 more
1. `Nicene and Post-Nicene Fathers: Series II`: mixed, reference, 7,099 parts, 1 missing, order chain, unproofread
    - front: Nicene and Post-Nicene Fathers: Series II
    - volume: Volume I: Eusebius: Church History from A.D. 1-324, Life of Constantine the Great, Oration in Praise of Constantine (`Nicene and Post-Nicene Fathers: Series II/Volume I`) [container]
      - chapter: Title Page (`Nicene and Post-Nicene Fathers: Series II/Volume I/Title Page`) [container]
      - chapter: Preface (`Nicene and Post-Nicene Fathers: Series II/Volume I/Preface`) [container]
      - volume: Church History of Eusebius (`Nicene and Post-Nicene Fathers: Series II/Volume I/Church History of Eusebius`) [container]
        - chapter: Title Page (`Nicene and Post-Nicene Fathers: Series II/Volume I/Church History of Eusebius/Title Page`) [container]
        - chapter: Preface (`Nicene and Post-Nicene Fathers: Series II/Volume I/Church History of Eusebius/Preface`) [container]
        - volume: Prolegomena (`Nicene and Post-Nicene Fathers: Series II/Volume I/Church History of Eusebius/Prolegomena`) [container]
          - volume: The Life of Eusebius (`Nicene and Post-Nicene Fathers: Series II/Volume I/Church History of Eusebius/Prolegomena/The Life of Eusebius`) [container]
            - chapter: Section 1 (`Nicene and Post-Nicene Fathers: Series II/Volume I/Church History of Eusebius/Prolegomena/The Life of Eusebius/Section 1`) [container]
            - chapter: Section II (`Nicene and Post-Nicene Fathers: Series II/Volume I/Church History of Eusebius/Prolegomena/The Life of Eusebius/Section II`) [container]
            - chapter: Section III (`Nicene and Post-Nicene Fathers: Series II/Volume I/Church History of Eusebius/Prolegomena/The Life of Eusebius/Section III`) [container]
    - … 7,088 more
1. `Atlas of the World's Languages in Danger (Third Edition)`: subpages, reference, 9 parts, 11 missing, order chain, proofread
    - front: Atlas of the World's Languages in Danger (Third Edition)
    - chapter: Preface by Irina Bokova (`Atlas of the World's Languages in Danger (Third Edition)/Preface`) [container]
    - chapter: Introduction by Christopher Moseley (`Atlas of the World's Languages in Danger (Third Edition)/Introduction`) [container]
    - text: Cartographic representation of the world's endangered languages (`Atlas of the World's Languages in Danger (Third Edition)/Cartographic representation of the world's endangered languages`) [container]
    - text: Sub-Saharan Africa (`Atlas of the World's Languages in Danger (Third Edition)/Sub-Saharan Africa`) [container]
    - text: North Africa and the Middle East (`Atlas of the World's Languages in Danger (Third Edition)/North Africa and the Middle East`) [container]
    - text: Europe and the Caucasus (`Atlas of the World's Languages in Danger (Third Edition)/Europe and the Caucasus`) [container]
    - text: Western and Central Asia (`Atlas of the World's Languages in Danger (Third Edition)/Western and Central Asia`) [container]
    - text: North-east Asia (`Atlas of the World's Languages in Danger (Third Edition)/North-east Asia`) [container]
    - text: India and the Himalayan chain (`Atlas of the World's Languages in Danger (Third Edition)/India and the Himalayan chain`) [container]
1. `Foclóir Gaeḋilge agus Béarla`: virtual, reference, 5 parts, 0 missing, order chain, proofread
    - text: Council's Preface (`Foclóir Gaeḋilge agus Béarla/Council's Preface`)
    - text: Editor's Preface (`Foclóir Gaeḋilge agus Béarla/Editor's Preface`) [hierarchy]
    - text: Abbreviations (`Foclóir Gaeḋilge agus Béarla/Abbreviations`) [hierarchy]
    - text: A (`Foclóir Gaeḋilge agus Béarla/A`) [hierarchy]
    - text: B (`Foclóir Gaeḋilge agus Béarla/B`) [hierarchy]
1. `A Naval Biographical Dictionary`: subpages, reference, 4,955 parts, 1 missing, order toc-partial, validated
    - front: A Naval Biographical Dictionary
    - text: Frontispiece (`A Naval Biographical Dictionary/Frontispiece`) [container]
    - text: Dedication (`A Naval Biographical Dictionary/Dedication`) [hierarchy]
    - chapter: Preface (`A Naval Biographical Dictionary/Preface`) [container]
    - text: Abbreviations (`A Naval Biographical Dictionary/Abbreviations`) [hierarchy]
    - text: Abbott, Jonas Archer (`A Naval Biographical Dictionary/Abbott, Jonas Archer`) [hierarchy]
    - text: Absolon, William (`A Naval Biographical Dictionary/Absolon, William`) [hierarchy]
    - text: Achmuty, Robert Ross (`A Naval Biographical Dictionary/Achmuty, Robert Ross`) [hierarchy]
    - text: Ackerley, Charles Henry (`A Naval Biographical Dictionary/Ackerley, Charles Henry`) [hierarchy]
    - text: Ackland, Edward (`A Naval Biographical Dictionary/Ackland, Edward`) [hierarchy]
    - text: A’Court, Edward Henry (`A Naval Biographical Dictionary/A’Court, Edward Henry`) [hierarchy]
    - text: Adam, Charles (`A Naval Biographical Dictionary/Adam, Charles`) [hierarchy]
    - … 4,944 more
1. `A Dictionary of Slang, Jargon & Cant`: subpages, reference, 4 parts, 11 missing, order single, proofread
    - front: A Dictionary of Slang, Jargon & Cant
    - volume: Table of Contents (`A Dictionary of Slang, Jargon & Cant/TOC`) [container]
      - chapter: Preface (`A Dictionary of Slang, Jargon & Cant/Preface`) [container]
      - chapter: A Brief History of English Slang (`A Dictionary of Slang, Jargon & Cant/History`) [container]
      - text: Contributors (`A Dictionary of Slang, Jargon & Cant/Contributors`) [container]
1. `Royal Naval Biography`: subpages, reference, 2,322 parts, 1 missing, order toc-partial, proofread
    - front: Royal Naval Biography
    - text: Frontispiece (`Royal Naval Biography/Frontispiece`) [container]
    - chapter: Title page (`Royal Naval Biography/Title page`) [container]
    - text: Dedication (`Royal Naval Biography/Dedication`) [container]
    - chapter: Preface (`Royal Naval Biography/Preface`) [container]
    - chapter: Advertisement (`Royal Naval Biography/Advertisement`) [container]
    - chapter: Contents (`Royal Naval Biography/Contents`) [container]
    - chapter: Errata & Corrigenda (`Royal Naval Biography/Errata & Corrigenda`) [container]
    - text: William Henry, Duke of Clarence (`Royal Naval Biography/William Henry, Duke of Clarence`) [container]
    - text: John Jervis, Earl of St Vincent (`Royal Naval Biography/John Jervis, Earl of St Vincent`) [container]
    - text: Williams Freeman, William Peere (`Royal Naval Biography/Williams Freeman, William Peere`) [container]
    - text: Montagu, George (`Royal Naval Biography/Montagu, George`) [container]
    - … 2,311 more
1. `Nicene and Post-Nicene Fathers: Series I`: subpages, reference, 5,287 parts, 1 missing, order chain, unproofread
    - front: Nicene and Post-Nicene Fathers: Series I
    - volume: Volume I: Prolegomena: St. Augustine's Life and Work, Confessions, Letters (`Nicene and Post-Nicene Fathers: Series I/Volume I`) [container]
      - chapter: Title Page (`Nicene and Post-Nicene Fathers: Series I/Volume I/Title Page`) [container]
      - chapter: Preface (`Nicene and Post-Nicene Fathers: Series I/Volume I/Preface`) [container]
      - chapter: Contents (`Nicene and Post-Nicene Fathers: Series I/Volume I/Contents`) [container]
      - volume: Prolegomena (`Nicene and Post-Nicene Fathers: Series I/Volume I/Prolegomena`) [container]
        - volume: Literature (`Nicene and Post-Nicene Fathers: Series I/Volume I/Prolegomena/Literature`) [container]
          - text: Sources (`Nicene and Post-Nicene Fathers: Series I/Volume I/Prolegomena/Literature/Sources`) [container]
          - text: Biographies (`Nicene and Post-Nicene Fathers: Series I/Volume I/Prolegomena/Literature/Biographies`) [container]
          - text: Special Treatises on the System of Augustin (`Nicene and Post-Nicene Fathers: Series I/Volume I/Prolegomena/Literature/Special Treatises on the System of Augustin`) [container]
        - text: A Sketch of the Life of St. Augustin (`Nicene and Post-Nicene Fathers: Series I/Volume I/Prolegomena/A Sketch of the Life of St. Augustin`) [container]
        - text: Estimate of St. Augustin (`Nicene and Post-Nicene Fathers: Series I/Volume I/Prolegomena/Estimate of St. Augustin`) [container]
    - … 5,276 more
1. `Dictionary of the Swatow dialect`: subpages, reference, 837 parts, 0 missing, order chain, unproofread
    - front: Dictionary of the Swatow dialect
    - chapter: Introduction (`Dictionary of the Swatow dialect/Introduction`) [container]
    - text: The Radicals (`Dictionary of the Swatow dialect/The Radicals`) [container]
    - text: a (`Dictionary of the Swatow dialect/a`) [container]
    - text: aⁿ (`Dictionary of the Swatow dialect/aⁿ`) [container]
    - text: ah (`Dictionary of the Swatow dialect/ah`) [container]
    - text: ai (`Dictionary of the Swatow dialect/ai`) [container]
    - text: aiⁿ (`Dictionary of the Swatow dialect/aiⁿ`) [container]
    - text: ak (`Dictionary of the Swatow dialect/ak`) [container]
    - text: am (`Dictionary of the Swatow dialect/am`) [container]
    - text: ang (`Dictionary of the Swatow dialect/ang`) [container]
    - text: ap (`Dictionary of the Swatow dialect/ap`) [container]
    - … 826 more
1. `United States Government Glossary of Interagency and Associated Terms`: subpages, reference, 99 parts, 8 missing, order chain, proofread
    - front: United States Government Glossary of Interagency and Associated Terms
    - text: abatement (`United States Government Glossary of Interagency and Associated Terms/abatement`) [container]
    - text: abducted child (`United States Government Glossary of Interagency and Associated Terms/abducted child`) [container]
    - text: abduction (`United States Government Glossary of Interagency and Associated Terms/abduction`) [container]
    - text: abduction case (`United States Government Glossary of Interagency and Associated Terms/abduction case`) [container]
    - text: aboveground storage tank (`United States Government Glossary of Interagency and Associated Terms/aboveground storage tank`) [container]
    - text: abroad (`United States Government Glossary of Interagency and Associated Terms/abroad`) [container]
    - text: absence without official leave (`United States Government Glossary of Interagency and Associated Terms/absence without official leave`) [container]
    - text: absent uniformed services voter (`United States Government Glossary of Interagency and Associated Terms/absent uniformed services voter`) [container]
    - text: absolute risk (`United States Government Glossary of Interagency and Associated Terms/absolute risk`) [container]
    - text: absolute risk [unmitigated] (`United States Government Glossary of Interagency and Associated Terms/absolute risk (unmitigated)`) [container]
    - text: abuse (`United States Government Glossary of Interagency and Associated Terms/abuse`) [container]
    - … 88 more
1. `Easton's Bible Dictionary (1897)`: subpages, reference, 2,050 parts, 0 missing, order natural, proofread
    - toc: Easton's Bible Dictionary (1897)
    - text: A (`Easton's Bible Dictionary (1897)/A`) [container]
    - text: A (letter) (`Easton's Bible Dictionary (1897)/A (letter)`) [hierarchy]
    - text: Aaron (`Easton's Bible Dictionary (1897)/Aaron`) [hierarchy]
    - text: Aaronites (`Easton's Bible Dictionary (1897)/Aaronites`) [hierarchy]
    - text: Abaddon (`Easton's Bible Dictionary (1897)/Abaddon`) [hierarchy]
    - text: Abagtha (`Easton's Bible Dictionary (1897)/Abagtha`) [hierarchy]
    - text: Abana (`Easton's Bible Dictionary (1897)/Abana`) [hierarchy]
    - text: Abarim (`Easton's Bible Dictionary (1897)/Abarim`) [hierarchy]
    - text: Abba (`Easton's Bible Dictionary (1897)/Abba`) [hierarchy]
    - text: Abda (`Easton's Bible Dictionary (1897)/Abda`) [hierarchy]
    - text: Abdeel (`Easton's Bible Dictionary (1897)/Abdeel`) [hierarchy]
    - … 2,039 more
1. `A Critical Pronouncing Dictionary (4th edition)`: subpages, reference, 12 parts, 21 missing, order chain, proofread
    - front: A Critical Pronouncing Dictionary (4th edition)
    - chapter: Preface (`A Critical Pronouncing Dictionary (4th edition)/Preface`) [container]
    - chapter: Advertisement to the Fourth Edition (`A Critical Pronouncing Dictionary (4th edition)/Advertisement`) [container]
    - text: Rules to Be Observed by the Natives of Ireland in Order to Obtain a Just Pronunciation of English (`A Critical Pronouncing Dictionary (4th edition)/Rules to Be Observed by the Natives of Ireland`) [container]
    - text: Rules to Be Observed by the Natives of Scotland for Attaining a Just Pronunciation of English (`A Critical Pronouncing Dictionary (4th edition)/Rules to Be Observed by the Natives of Scotland`) [container]
    - text: Directions to Foreigners, in Order to Attain a Knowledge of the Marks in This Dictionary, and to Acquire a Right Pronunciation of Every Word in the English Language. (`A Critical Pronouncing Dictionary (4th edition)/Directions to Foreigners`) [container]
    - text: Principles of English Pronunciation. (`A Critical Pronouncing Dictionary (4th edition)/Principles`) [container]
    - volume: A Critical Pronouncing Dictionary (`A Critical Pronouncing Dictionary (4th edition)/Dictionary`) [container]
      - chapter: X (`A Critical Pronouncing Dictionary (4th edition)/Dictionary/X`) [container]
      - text: Y (`A Critical Pronouncing Dictionary (4th edition)/Dictionary/Y`) [container]
      - text: Z (`A Critical Pronouncing Dictionary (4th edition)/Dictionary/Z`) [container]
    - chapter: Appendix (`A Critical Pronouncing Dictionary (4th edition)/Appendix`) [container]
    - … 1 more
1. `Catholic Encyclopedia (1922 supplement)`: virtual, reference, 21 parts, 0 missing, order scan, proofread
    - text: Jaca, Diocese of (`Catholic Encyclopedia (1922 supplement)/Jaca, Diocese of`)
    - text: Jaffna, Diocese of (`Catholic Encyclopedia (1922 supplement)/Jaffna, Diocese of`) [hierarchy]
    - text: Jamaica, Vicariate Apostolic of (`Catholic Encyclopedia (1922 supplement)/Jamaica, Vicariate Apostolic of`) [hierarchy]
    - text: Japan (`Catholic Encyclopedia (1922 supplement)/Japan`) [hierarchy]
    - text: Jaén, Diocese of (`Catholic Encyclopedia (1922 supplement)/Jaén, Diocese of`) [hierarchy]
    - text: Jaro, Diocese of (`Catholic Encyclopedia (1922 supplement)/Jaro, Diocese of`) [hierarchy]
    - text: Oliver Plunket, Blessed (`Catholic Encyclopedia (1922 supplement)/Oliver Plunket, Blessed`) [hierarchy]
    - text: Olivetans (`Catholic Encyclopedia (1922 supplement)/Olivetans`) [hierarchy]
    - text: Olmutz, Archdiocese of (`Catholic Encyclopedia (1922 supplement)/Olmutz, Archdiocese of`) [hierarchy]
    - text: Olomouc, Archdiocese of (`Catholic Encyclopedia (1922 supplement)/Olomouc, Archdiocese of`) [hierarchy]
    - text: Omaha, Diocese of (`Catholic Encyclopedia (1922 supplement)/Omaha, Diocese of`) [hierarchy]
    - text: Ontario, Northern, Vicariate Apostolic of (`Catholic Encyclopedia (1922 supplement)/Ontario, Northern, Vicariate Apostolic of`) [hierarchy]
    - … 9 more
1. `Alumni Oxonienses: the Members of the University of Oxford, 1715-1886`: subpages, reference, 63,225 parts, 0 missing, order natural, unknown
    - toc: Alumni Oxonienses: the Members of the University of Oxford, 1715-1886
    - text: A (`Alumni Oxonienses: the Members of the University of Oxford, 1715-1886/A`) [container]
    - text: A'Beckett, Gilbert Arthur (`Alumni Oxonienses: the Members of the University of Oxford, 1715-1886/A'Beckett, Gilbert Arthur`) [hierarchy]
    - text: A'Beckett, Thomas (`Alumni Oxonienses: the Members of the University of Oxford, 1715-1886/A'Beckett, Thomas`) [hierarchy]
    - text: A'Court, Peirce (`Alumni Oxonienses: the Members of the University of Oxford, 1715-1886/A'Court, Peirce`) [hierarchy]
    - text: Abbay, Richard (`Alumni Oxonienses: the Members of the University of Oxford, 1715-1886/Abbay, Richard`) [hierarchy]
    - text: Abbey, Charles John (`Alumni Oxonienses: the Members of the University of Oxford, 1715-1886/Abbey, Charles John`) [hierarchy]
    - text: Abbey, John (`Alumni Oxonienses: the Members of the University of Oxford, 1715-1886/Abbey, John`) [hierarchy]
    - text: Abbiss, John (`Alumni Oxonienses: the Members of the University of Oxford, 1715-1886/Abbiss, John`) [hierarchy]
    - text: Abbot, Bradley (`Alumni Oxonienses: the Members of the University of Oxford, 1715-1886/Abbot, Bradley`) [hierarchy]
    - text: Abbot, Charles (1) (`Alumni Oxonienses: the Members of the University of Oxford, 1715-1886/Abbot, Charles (1)`) [hierarchy]
    - text: Abbot, Charles (2) (`Alumni Oxonienses: the Members of the University of Oxford, 1715-1886/Abbot, Charles (2)`) [hierarchy]
    - … 63,214 more
1. `Catholic Encyclopedia (1913)`: subpages, reference, 11,629 parts, 3 missing, order natural, unproofread
    - toc: Catholic Encyclopedia (1913)
    - text: A and Ω (`Catholic Encyclopedia (1913)/A and Ω`) [hierarchy]
    - text: Aachen (`Catholic Encyclopedia (1913)/Aachen`) [hierarchy]
    - text: Aaron (`Catholic Encyclopedia (1913)/Aaron`) [hierarchy]
    - text: Aaron (2) (`Catholic Encyclopedia (1913)/Aaron (2)`) [hierarchy]
    - text: Aaronites (`Catholic Encyclopedia (1913)/Aaronites`) [hierarchy]
    - text: Abaddon (`Catholic Encyclopedia (1913)/Abaddon`) [hierarchy]
    - text: Abana (`Catholic Encyclopedia (1913)/Abana`) [hierarchy]
    - text: Abandonment (`Catholic Encyclopedia (1913)/Abandonment`) [hierarchy]
    - text: Abarim (`Catholic Encyclopedia (1913)/Abarim`) [hierarchy]
    - text: Abba (`Catholic Encyclopedia (1913)/Abba`) [hierarchy]
    - text: Abbacy (`Catholic Encyclopedia (1913)/Abbacy`) [hierarchy]
    - … 11,618 more
1. `The Encyclopedia Americana (1920)`: subpages, reference, 3,166 parts, 0 missing, order natural, proofread
    - front: The Encyclopedia Americana (1920)
    - text: A (`The Encyclopedia Americana (1920)/A`) [hierarchy]
    - text: A B C Club (`The Encyclopedia Americana (1920)/A B C Club`) [hierarchy]
    - text: A B C Process (`The Encyclopedia Americana (1920)/A B C Process`) [hierarchy]
    - text: A cheval position (`The Encyclopedia Americana (1920)/A cheval position`) [hierarchy]
    - text: Aachen (`The Encyclopedia Americana (1920)/Aachen`) [hierarchy]
    - text: Aagesen, Andrew (`The Encyclopedia Americana (1920)/Aagesen, Andrew`) [hierarchy]
    - text: Aahmes (`The Encyclopedia Americana (1920)/Aahmes`) [hierarchy]
    - text: Aahmes I (`The Encyclopedia Americana (1920)/Aahmes I`) [hierarchy]
    - text: Aahmes II (`The Encyclopedia Americana (1920)/Aahmes II`) [hierarchy]
    - text: Aalborg (`The Encyclopedia Americana (1920)/Aalborg`) [hierarchy]
    - text: Aalen (`The Encyclopedia Americana (1920)/Aalen`) [hierarchy]
    - … 3,155 more
1. `Ante-Nicene Fathers`: subpages, reference, 5,616 parts, 0 missing, order chain, unproofread
    - front: Ante-Nicene Fathers
    - volume: Volume I: The Apostolic Fathers with Justin Martyr and Irenaeus (`Ante-Nicene Fathers/Volume I`) [container]
      - volume: Title Page (`Ante-Nicene Fathers/Volume I/Title Page`) [container]
        - chapter: Preface (`Ante-Nicene Fathers/Volume I/Title Page/Preface`) [container]
        - text: Introductory Notice (`Ante-Nicene Fathers/Volume I/Title Page/Introductory Notice`) [container]
      - text: CLEMENT OF ROME (`Ante-Nicene Fathers/Volume I/CLEMENT OF ROME`) [container]
      - text: Introductory Note to the First Epistle of Clement to the Corinthians (`Ante-Nicene Fathers/Volume I/Introductory Note to the First Epistle of Clement to the Corinthians`) [container]
      - text: First Epistle to the Corinthians (`Ante-Nicene Fathers/Volume I/First Epistle to the Corinthians`) [hierarchy]
      - text: MATHETES (`Ante-Nicene Fathers/Volume I/MATHETES`) [container]
      - text: Introductory Note to the Epistle of Mathetes to Diognetus (`Ante-Nicene Fathers/Volume I/Introductory Note to the Epistle of Mathetes to Diognetus`) [container]
      - text: Epistle to Diognetus (`Ante-Nicene Fathers/Volume I/Epistle to Diognetus`) [hierarchy]
      - volume: POLYCARP (`Ante-Nicene Fathers/Volume I/POLYCARP`) [container]
    - … 5,605 more
1. `Dictionary of the Foochow Dialect`: subpages, reference, 3 parts, 17 missing, order toc, unproofread
    - front: Dictionary of the Foochow Dialect
    - text: A (`Dictionary of the Foochow Dialect/A`) [container]
    - text: List of the 214 radicals (`Dictionary of the Foochow Dialect/List of the 214 radicals`) [container]
    - chapter: Index of Characters (`Dictionary of the Foochow Dialect/Index of Characters`) [container]
1. `Who's Who in the Far East`: subpages, reference, 2,182 parts, 0 missing, order toc, unproofread
    - toc: Who's Who in the Far East
    - text: ABE, Taizo (`Who's Who in the Far East/ABE, Taizo`) [hierarchy]
    - text: ACTON, Roger David (`Who's Who in the Far East/ACTON, Roger David`) [hierarchy]
    - text: ACTON, William Walter (`Who's Who in the Far East/ACTON, William Walter`) [hierarchy]
    - text: ADACHI, Tsunayuki (`Who's Who in the Far East/ADACHI, Tsunayuki`) [hierarchy]
    - text: ADAM, Charles M. (`Who's Who in the Far East/ADAM, Charles M.`) [hierarchy]
    - text: ADAMS, Arthur Robert (`Who's Who in the Far East/ADAMS, Arthur Robert`) [hierarchy]
    - text: ADVOCAAT, Gysbert Diederik (`Who's Who in the Far East/ADVOCAAT, Gysbert Diederik`) [hierarchy]
    - text: AGER, Albert Powtrill (`Who's Who in the Far East/AGER, Albert Powtrill`) [hierarchy]
    - text: AIKEN, Rev. Edwin Edgerton (`Who's Who in the Far East/AIKEN, Rev. Edwin Edgerton`) [hierarchy]
    - text: AINSLIE, Dr. David Hunter (`Who's Who in the Far East/AINSLIE, Dr. David Hunter`) [hierarchy]
    - text: AITKEN, Lieut.-Col. Arthur Edward (`Who's Who in the Far East/AITKEN, Lieut.-Col. Arthur Edward`) [hierarchy]
    - … 2,171 more

### H: deep nesting (312)

1. `Folk-Lore Record`: subpages, collection, 21 parts, 4 missing, order single, proofread
    - toc: Folk-Lore Record
    - volume: Volume 1 (`Folk-Lore Record/Volume 1`) [container]
      - volume: Some West Sussex Superstitions Lingering in 1868 (`Folk-Lore Record/Volume 1/Some West Sussex Superstitions Lingering in 1868`) [container]
        - chapter: Chapter 1 (`Folk-Lore Record/Volume 1/Some West Sussex Superstitions Lingering in 1868/Chapter 1`) [container]
        - chapter: Chapter 2 (`Folk-Lore Record/Volume 1/Some West Sussex Superstitions Lingering in 1868/Chapter 2`) [container]
        - chapter: Chapter 3 (`Folk-Lore Record/Volume 1/Some West Sussex Superstitions Lingering in 1868/Chapter 3`) [container]
        - chapter: Chapter 4 (`Folk-Lore Record/Volume 1/Some West Sussex Superstitions Lingering in 1868/Chapter 4`) [container]
        - chapter: Chapter 5 (`Folk-Lore Record/Volume 1/Some West Sussex Superstitions Lingering in 1868/Chapter 5`) [container]
        - chapter: Chapter 6 (`Folk-Lore Record/Volume 1/Some West Sussex Superstitions Lingering in 1868/Chapter 6`) [container]
        - chapter: Index (`Folk-Lore Record/Volume 1/Some West Sussex Superstitions Lingering in 1868/Index`) [container]
      - chapter: Notes on Folk-tales (`Folk-Lore Record/Volume 1/Notes on Folk-tales`) [container]
      - text: The Folk-lore of France (`Folk-Lore Record/Volume 1/The Folk-lore of France`) [container]
    - … 10 more
1. `The Journal of Negro History`: subpages, collection, 24 parts, 0 missing, order single, proofread
    - toc: The Journal of Negro History
    - volume: Volume 7 (`The Journal of Negro History/Volume 7`) [container]
      - volume: Number 1 (`The Journal of Negro History/Volume 7/Number 1`) [container]
        - text: Slave Society on the Southern Plantation (`The Journal of Negro History/Volume 7/Number 1/Slave Society on the Southern Plantation`) [container]
        - text: The Evolution of the Negro Baptist Church (`The Journal of Negro History/Volume 7/Number 1/The Evolution of the Negro Baptist Church`) [container]
        - text: Early Negro Education in West Virginia (`The Journal of Negro History/Volume 7/Number 1/Early Negro Education in West Virginia`) [container]
        - text: First Negro Churches in the District of Columbia (`The Journal of Negro History/Volume 7/Number 1/First Negro Churches in the District of Columbia`) [container]
        - text: The Experience of a Georgia Peon—My Escape from Bondage. (`The Journal of Negro History/Volume 7/Number 1/The Experience of a Georgia Peon`) [container]
        - text: Correspondence (`The Journal of Negro History/Volume 7/Number 1/Communications`) [container]
        - text: Brawley's A Social History of the American Negro (`The Journal of Negro History/Volume 7/Number 1/Brawley's A Social History of the American Negro`) [container]
        - text: William Lloyd Garrison (`The Journal of Negro History/Volume 7/Number 1/Chapman's William Lloyd Garrison`) [container]
        - text: The Education of the South African Native (`The Journal of Negro History/Volume 7/Number 1/Lobam's The Education of the South African Native`) [container]
    - … 13 more
1. `The Fortunes of Richard Mahony`: linked, book, 46 parts, 1 missing, order single, unproofread
    - toc: The Fortunes of Richard Mahony
    - volume: Book 1: Australia Felix (`Australia Felix`) [container]
      - text: Proem (`Australia Felix/Proem`) [container]
      - volume: Part I (`Australia Felix/Part I`) [container]
        - chapter: Part I, Chapter I (`Australia Felix/Part I/Chapter I`) [container]
        - chapter: Part I, Chapter II (`Australia Felix/Part I/Chapter II`) [container]
        - chapter: Part I, Chapter III (`Australia Felix/Part I/Chapter III`) [container]
        - chapter: Part I, Chapter IV (`Australia Felix/Part I/Chapter IV`) [container]
        - chapter: Part I, Chapter V (`Australia Felix/Part I/Chapter V`) [container]
        - chapter: Part I, Chapter VI (`Australia Felix/Part I/Chapter VI`) [container]
        - chapter: Part I, Chapter VII (`Australia Felix/Part I/Chapter VII`) [container]
        - chapter: Part I, Chapter VIII (`Australia Felix/Part I/Chapter VIII`) [container]
    - … 35 more
1. `New Edition of the Babylonian Talmud`: mixed, book, 37 parts, 15 missing, order toc-partial, proofread
    - front: New Edition of the Babylonian Talmud
    - volume: Volume 1 (`New Edition of the Babylonian Talmud/Volume 1`) [container]
      - chapter: Preface to the Second Edition (`New Edition of the Babylonian Talmud/Volume 1/Preface to the Second Edition`) [container]
      - text: Editor's Preface (`New Edition of the Babylonian Talmud/Volume 1/Editor's Preface`) [container]
      - chapter: Brief General Introduction to the Babylonian Talmud (`New Edition of the Babylonian Talmud/Volume 1/Brief General Introduction`) [container]
      - chapter: Introduction to Tract Sabbath (`New Edition of the Babylonian Talmud/Volume 1/Introduction to Tract Sabbath`) [container]
      - text: Synopsis of Subjects (`New Edition of the Babylonian Talmud/Volume 1/Synopsis of Subjects`) [container]
      - chapter: Chapter I. Regulations Regarding Transfer on Sabbath (`New Edition of the Babylonian Talmud/Volume 1/Chapter 1`) [container]
      - chapter: Chapter II. Regulations Concerning The Sabbath And 'Hanukah Light (`New Edition of the Babylonian Talmud/Volume 1/Chapter 2`) [container]
      - chapter: Chapter III. Regulations Concerning Stoves, Hearths, and Ovens (`New Edition of the Babylonian Talmud/Volume 1/Chapter 3`) [container]
      - chapter: Chapter IV. Regulations Concerning Victuals, Where They May or May (`New Edition of the Babylonian Talmud/Volume 1/Chapter 4`) [container]
      - chapter: Chapter V. Regulations Concerning What May and May Not Be Worn by Animals on the Sabbath (`New Edition of the Babylonian Talmud/Volume 1/Chapter 5`) [container]
    - … 26 more
1. `Monthly Weather Review`: mixed, collection, 35 parts, 19 missing, order natural, proofread
    - toc: Monthly Weather Review
    - text: Monthly Weather Review, Volume 1, Issue 3, TEMPERATURE [container]
    - text: Monthly Weather Review, Volume 1, Issue 4, STORMS [container]
    - volume: Volume 1 (`Monthly Weather Review/Volume 1`) [container]
      - chapter: Issue 1 (`Monthly Weather Review/Volume 1/Issue 1`) [container]
      - chapter: Issue 2 (`Monthly Weather Review/Volume 1/Issue 2`) [container]
      - chapter: Issue 3 (`Monthly Weather Review/Volume 1/Issue 3`) [container]
      - chapter: Issue 4 (`Monthly Weather Review/Volume 1/Issue 4`) [container]
      - chapter: Issue 5 (`Monthly Weather Review/Volume 1/Issue 5`) [container]
      - chapter: Issue 6 (`Monthly Weather Review/Volume 1/Issue 6`) [container]
    - volume: Volume 24 (`Monthly Weather Review/Volume 24`) [container]
      - volume: Issue 4 (`Monthly Weather Review/Volume 24/Issue 4`) [container]
    - … 24 more
1. `All the Year Round (magazine)`: mixed, collection, 15 parts, 62 missing, order natural, proofread
    - toc: All the Year Round (magazine)
    - volume: Series 1 (`All the Year Round (magazine)/Series 1`) [container]
      - volume: Volume 16 (`All the Year Round (magazine)/Series 1/Volume 16`) [hierarchy]
        - volume: Christmas 1866 (`All the Year Round (magazine)/Series 1/Volume 16/Christmas 1866`) [hierarchy]
          - text: Mugby Junction: Barbox Brothers (`All the Year Round (magazine)/Series 1/Volume 16/Christmas 1866/Barbox Brothers`) [hierarchy]
          - text: Mugby Junction: Barbox Brothers and Co. (`All the Year Round (magazine)/Series 1/Volume 16/Christmas 1866/Barbox Brothers and Co.`) [hierarchy]
          - text: Mugby Junction: Main Line. The Boy at Mugby (`All the Year Round (magazine)/Series 1/Volume 16/Christmas 1866/Main Line. The Boy at Mugby`) [hierarchy]
    - text: Series 2 (`All the Year Round (magazine)/Series 2`) [container]
    - text: Series 3 (`All the Year Round (magazine)/Series 3`) [container]
    - text: A fly-leaf in a life (`New Uncommercial Samples/A fly-leaf in a life`) [container]
    - text: A plea for total abstinence (`New Uncommercial Samples/A plea for total abstinence`) [container]
    - text: A small dinner in an hour (`New Uncommercial Samples/A small dinner in an hour`) [container]
    - … 4 more
1. `Electronics Technician`: virtual, book, 12 parts, 0 missing, order single, proofread
    - volume: Volume 7 (`Electronics Technician/Volume 7`)
      - chapter: front matter (`Electronics Technician/Volume 7/front matter`) [container]
      - chapter: Preface (`Electronics Technician/Volume 7/Preface`) [hierarchy]
      - volume: Table of Contents (`Electronics Technician/Volume 7/Table of Contents`) [container]
        - chapter: Chapter 1 (`Electronics Technician/Volume 7/Chapter 1`) [container]
        - chapter: Chapter 2 (`Electronics Technician/Volume 7/Chapter 2`) [container]
        - chapter: Chapter 3 (`Electronics Technician/Volume 7/Chapter 3`) [container]
        - chapter: Appendix I (`Electronics Technician/Volume 7/Appendix I`) [container]
        - chapter: Appendix II (`Electronics Technician/Volume 7/Appendix II`) [container]
        - text: Assignment Questions (`Electronics Technician/Volume 7/Assignment Questions`) [container]
      - text: Summary of the Electronics Technician Training Series (`Electronics Technician/Volume 7/Summary of the Electronics Technician Training Series`) [container]
      - text: Instructions for taking the course (`Electronics Technician/Volume 7/Instructions for taking the course`) [container]
1. `H.R. 3962`: subpages, collection, 21 parts, 63 missing, order chain, unknown
    - front: H.R. 3962
    - volume: Division A − Affordable Health Care Choices (`H.R. 3962/Division A`) [container]
      - text: Division A − Affordable Health Care Choices Title I − Immediate Reforms (`H.R. 3962/Division A/Title I`) [container]
      - volume: Division A − Affordable Health Care Choices Title II − Protections and Standards for Qualified Health Benefits Plans (`H.R. 3962/Division A/Title II`) [container]
        - text: Division A − Affordable Health Care Choices Title II − Protections and Standards for Qualified Health Benefits Plans Subtitle F − Relation to Other Requirements; Miscellaneous (`H.R. 3962/Division A/Title II/Subtitle F`) [container]
      - volume: Division A − Affordable Health Care Choices Title III − Health Insurance Exchange and Related Programs (`H.R. 3962/Division A/Title III`) [container]
        - text: Division A − Affordable Health Care Choices Title III − Health Insurance Exchange and Related Programs Subtitle C − Individual Affordability Credits (`H.R. 3962/Division A/Title III/Subtitle C`) [container]
      - volume: Division A − Affordable Health Care Choices Title IV − Shared Responsibility (`H.R. 3962/Division A/Title IV`) [container]
        - text: Division A − Affordable Health Care Choices Title IV − Shared Responsibility Subtitle A − Individual Responsibility (`H.R. 3962/Division A/Title IV/Subtitle A`) [container]
      - volume: Division A − Affordable Health Care Choices Title V − Amendments to Internal Revenue Code of 1986 (`H.R. 3962/Division A/Title V`) [container]
        - volume: Division A − Affordable Health Care Choices Title V − Amendments to Internal Revenue Code of 1986 Subtitle A − Provisions Relating to Health Care Reform (`H.R. 3962/Division A/Title V/Subtitle A`) [container]
          - volume: Division A − Affordable Health Care Choices Title V − Amendments to Internal Revenue Code of 1986 Subtitle A − Provisions Relating to Health Care Reform Part 1 − Shared Responsibility (`H.R. 3962/Division A/Title V/Subtitle A/Part 1`) [container]
    - … 10 more
1. `Public Law 106-113`: subpages, collection, 7 parts, 21 missing, order single, unknown
    - front: Public Law 106-113
    - volume: Division B - Appendixes (`Public Law 106-113/Division B`) [container]
      - volume: Division B &minus; Appendix I (S. 1948) (`Public Law 106-113/Division B/Appendix I`) [container]
        - text: Division B Appendix I Title I - Television Program Improvement (`Public Law 106-113/Division B/Appendix I/Title I`) [container]
        - text: Division B Appendix I Title II - Rural Local Television Signals (`Public Law 106-113/Division B/Appendix I/Title II`) [container]
        - text: Division B Appendix I Title III - Trademark Cyberpiracy Prevention (`Public Law 106-113/Division B/Appendix I/Title III`) [container]
        - volume: Division B Appendix I Title IV - Inventor Protection (`Public Law 106-113/Division B/Appendix I/Title IV`) [container]
          - text: Division B &minus; Appendix I Title IV &minus; Inventor Protection Subtitle G &minus; Patent and Trademark Office (`Public Law 106-113/Division B/Appendix I/Title IV/Subtitle G`) [container]
1. `The Works of the Rev. Jonathan Swift`: subpages, book, 1,767 parts, 1 missing, order chain, proofread
    - front: The Works of the Rev. Jonathan Swift
    - volume: Volume 1 (`The Works of the Rev. Jonathan Swift/Volume 1`) [container]
      - text: Testimonials (`The Works of the Rev. Jonathan Swift/Volume 1/Testimonials`) [container]
      - chapter: Advertisement (`The Works of the Rev. Jonathan Swift/Volume 1/Advertisement`) [container]
      - text: Dedication (`The Works of the Rev. Jonathan Swift/Volume 1/Dedication`) [container]
      - chapter: Introduction (`The Works of the Rev. Jonathan Swift/Volume 1/Introduction`) [container]
      - text: Life of Dr. Swift, Section I (`The Works of the Rev. Jonathan Swift/Volume 1/Life of Dr. Swift, Section I`) [container]
      - text: Life of Dr. Swift, Section II (`The Works of the Rev. Jonathan Swift/Volume 1/Life of Dr. Swift, Section II`) [container]
      - text: Life of Dr. Swift, Section III (`The Works of the Rev. Jonathan Swift/Volume 1/Life of Dr. Swift, Section III`) [container]
      - text: Life of Dr. Swift, Section IV (`The Works of the Rev. Jonathan Swift/Volume 1/Life of Dr. Swift, Section IV`) [container]
      - text: Life of Dr. Swift, Section V (`The Works of the Rev. Jonathan Swift/Volume 1/Life of Dr. Swift, Section V`) [container]
      - text: Life of Dr. Swift, Section VI (`The Works of the Rev. Jonathan Swift/Volume 1/Life of Dr. Swift, Section VI`) [container]
    - … 1,756 more
1. `Egyptian Literature`: subpages, book, 120 parts, 26 missing, order toc, proofread
    - front: Egyptian Literature
    - text: Special Introduction (`Egyptian Literature/Introduction`) [container]
    - text: Illustrations (`Egyptian Literature/Contents`) [container]
    - volume: The Book of the Dead (`Egyptian Literature/The Book of the Dead`) [container]
      - chapter: The Book of the Dead: A Hymn to the Setting Sun (`Egyptian Literature/The Book of the Dead/A Hymn to the Setting Sun`) [container]
      - chapter: The Book of the Dead: Hymn and Litany to Osiris (`Egyptian Literature/The Book of the Dead/Hymn and Litany to Osiris`) [container]
      - chapter: The Book of the Dead: Hymn to Ra (`Egyptian Literature/The Book of the Dead/Hymn to Ra`) [container]
      - text: Hymn to the Setting Sun (`Egyptian Literature/The Book of the Dead/Hymn to the Setting Sun (1)`) [container]
      - text: Hymn to the Setting Sun (`Egyptian Literature/The Book of the Dead/Hymn to the Setting Sun (2)`) [container]
      - chapter: The Book of the Dead: The Chaplet of Victory (`Egyptian Literature/The Book of the Dead/The Chaplet of Victory`) [container]
      - chapter: The Book of the Dead: The Victory Over Enemies (`Egyptian Literature/The Book of the Dead/The Victory Over Enemies`) [container]
      - chapter: The Book of the Dead: Of Giving a Mouth to the Overseer (`Egyptian Literature/The Book of the Dead/Of Giving a Mouth to the Overseer`) [container]
    - … 109 more
1. `Aunt Jo's Scrap-Bag`: subpages, collection, 83 parts, 0 missing, order toc, proofread
    - toc: Aunt Jo's Scrap-Bag
    - volume: Volume 3 (`Aunt Jo's Scrap-Bag/Volume 3`) [hierarchy]
      - text: Cupid and Chow-Chow (`Aunt Jo's Scrap-Bag/Volume 3/Chapter 1`) [container]
      - text: Huckleberry (`Aunt Jo's Scrap-Bag/Volume 3/Chapter 2`) [container]
      - text: Nelly's Hospital (`Aunt Jo's Scrap-Bag/Volume 3/Chapter 3`) [container]
      - text: Grandma's Team (`Aunt Jo's Scrap-Bag/Volume 3/Chapter 4`) [container]
      - text: Fairy Pinafores (`Aunt Jo's Scrap-Bag/Volume 3/Chapter 5`) [container]
      - text: Mamma's Plot (`Aunt Jo's Scrap-Bag/Volume 3/Chapter 6`) [container]
      - text: Kate's Choice (`Aunt Jo's Scrap-Bag/Volume 3/Chapter 7`) [container]
      - text: The Moss People (`Aunt Jo's Scrap-Bag/Volume 3/Chapter 8`) [container]
      - text: What Fanny heard (`Aunt Jo's Scrap-Bag/Volume 3/Chapter 9`) [container]
      - text: A Marine Merry-making (`Aunt Jo's Scrap-Bag/Volume 3/Chapter 10`) [container]
    - … 72 more
1. `Frank Leslie's Popular Monthly`: subpages, collection, 5 parts, 113 missing, order single, proofread
    - front: Frank Leslie's Popular Monthly
    - volume: Volume 1 (`Frank Leslie's Popular Monthly/Volume 1`) [container]
      - volume: Number 4 (`Frank Leslie's Popular Monthly/Volume 1/Number 4`) [hierarchy]
        - text: Why (`Frank Leslie's Popular Monthly/Volume 1/Number 4/Why`) [hierarchy]
        - text: Enigmas (`Frank Leslie's Popular Monthly/Volume 1/Number 4/Enigmas`) [hierarchy]
        - text: Miscellany 6 (`Frank Leslie's Popular Monthly/Volume 1/Number 4/Miscellany 6`) [hierarchy]
1. `Frank Leslie's Illustrated Newspaper`: subpages, collection, 59 parts, 107 missing, order toc, proofread
    - toc: Frank Leslie's Illustrated Newspaper
    - chapter: Volume 9 (`Frank Leslie's Illustrated Newspaper/Volume 9`) [container]
    - chapter: Volume 10 (`Frank Leslie's Illustrated Newspaper/Volume 10`) [container]
    - chapter: Volume 11 (`Frank Leslie's Illustrated Newspaper/Volume 11`) [container]
    - chapter: Volume 12 (`Frank Leslie's Illustrated Newspaper/Volume 12`) [container]
    - chapter: Volume 13 (`Frank Leslie's Illustrated Newspaper/Volume 13`) [container]
    - chapter: Volume 14 (`Frank Leslie's Illustrated Newspaper/Volume 14`) [container]
    - chapter: Volume 15 (`Frank Leslie's Illustrated Newspaper/Volume 15`) [container]
    - chapter: Volume 16 (`Frank Leslie's Illustrated Newspaper/Volume 16`) [container]
    - chapter: Volume 17 (`Frank Leslie's Illustrated Newspaper/Volume 17`) [container]
    - volume: Volume 18 (`Frank Leslie's Illustrated Newspaper/Volume 18`) [container]
      - volume: Number 450 (`Frank Leslie's Illustrated Newspaper/Volume 18/Number 450`) [hierarchy]
    - … 48 more
1. `The Historical, Political, and Diplomatic Writings of Niccolo Machiavelli`: subpages, book, 29 parts, 0 missing, order single, proofread
    - front: The Historical, Political, and Diplomatic Writings of Niccolo Machiavelli
    - volume: Volume 2 (`The Historical, Political, and Diplomatic Writings of Niccolo Machiavelli/Volume 2`) [container]
      - volume: The Prince (`The Historical, Political, and Diplomatic Writings of Niccolo Machiavelli/Volume 2/The Prince`) [container]
        - text: To the Magnificent Lorenzo (`The Historical, Political, and Diplomatic Writings of Niccolo Machiavelli/Volume 2/The Prince/To the Magnificent Lorenzo`) [container]
        - chapter: Chapter 1 (`The Historical, Political, and Diplomatic Writings of Niccolo Machiavelli/Volume 2/The Prince/Chapter 1`) [container]
        - chapter: Chapter 2 (`The Historical, Political, and Diplomatic Writings of Niccolo Machiavelli/Volume 2/The Prince/Chapter 2`) [container]
        - chapter: Chapter 3 (`The Historical, Political, and Diplomatic Writings of Niccolo Machiavelli/Volume 2/The Prince/Chapter 3`) [container]
        - chapter: Chapter 4 (`The Historical, Political, and Diplomatic Writings of Niccolo Machiavelli/Volume 2/The Prince/Chapter 4`) [container]
        - chapter: Chapter 5 (`The Historical, Political, and Diplomatic Writings of Niccolo Machiavelli/Volume 2/The Prince/Chapter 5`) [container]
        - chapter: Chapter 6 (`The Historical, Political, and Diplomatic Writings of Niccolo Machiavelli/Volume 2/The Prince/Chapter 6`) [container]
        - chapter: Chapter 7 (`The Historical, Political, and Diplomatic Writings of Niccolo Machiavelli/Volume 2/The Prince/Chapter 7`) [container]
        - chapter: Chapter 8 (`The Historical, Political, and Diplomatic Writings of Niccolo Machiavelli/Volume 2/The Prince/Chapter 8`) [container]
    - … 18 more
1. `Historic Highways of America`: subpages, collection, 155 parts, 0 missing, order toc, validated
    - toc: Historic Highways of America
    - volume: 1 - Paths of the Mound-Building Indians and Great Game Animals. (`Historic Highways of America/Volume 1`) [hierarchy]
      - chapter: Preface (`Historic Highways of America/Volume 1/Preface`) [container]
      - text: General Introduction (`Historic Highways of America/Volume 1/General Introduction`) [container]
      - chapter: PART I (`Historic Highways of America/Volume 1/Part 1`) [container]
      - text: The Comparative Method of Study (`Historic Highways of America/Volume 1/Chapter 1`) [container]
      - text: Distribution of Mound-building Indians (`Historic Highways of America/Volume 1/Chapter 2`) [container]
      - text: Early Travel in the Interior (`Historic Highways of America/Volume 1/Chapter 3`) [container]
      - text: Highland Location of Archæological Remains (`Historic Highways of America/Volume 1/Chapter 4`) [container]
      - text: Watershed Migrations (`Historic Highways of America/Volume 1/Chapter 5`) [container]
      - volume: PART II (`Historic Highways of America/Volume 1/Part 2`) [container]
        - chapter: Chapter 1 (`Historic Highways of America/Volume 1/Part 2/Chapter 1`) [container]
    - … 144 more
1. `Matthew Henry's Commentary on the Whole Bible`: subpages, book, 132 parts, 0 missing, order chain, unknown
    - toc: Matthew Henry's Commentary on the Whole Bible
    - volume: Volume 1 (Genesis - Deuteronomy) (`Matthew Henry's Commentary on the Whole Bible/Volume 1`) [container]
      - chapter: Volume 1: Preface (`Matthew Henry's Commentary on the Whole Bible/Volume 1/Preface`) [container]
      - volume: Volume 1: Genesis (`Matthew Henry's Commentary on the Whole Bible/Volume 1/Genesis`) [container]
        - chapter: Volume 1: Genesis: Preface (`Matthew Henry's Commentary on the Whole Bible/Volume 1/Genesis/Preface`) [container]
        - chapter: Volume 1: Genesis: Chapter 1 (`Matthew Henry's Commentary on the Whole Bible/Volume 1/Genesis/Chapter 1`) [container]
        - chapter: Volume 1: Genesis: Chapter 2 (`Matthew Henry's Commentary on the Whole Bible/Volume 1/Genesis/Chapter 2`) [container]
        - chapter: Volume 1: Genesis: Chapter 3 (`Matthew Henry's Commentary on the Whole Bible/Volume 1/Genesis/Chapter 3`) [container]
        - chapter: Volume 1: Genesis: Chapter 4 (`Matthew Henry's Commentary on the Whole Bible/Volume 1/Genesis/Chapter 4`) [container]
        - chapter: Volume 1: Genesis: Chapter 5 (`Matthew Henry's Commentary on the Whole Bible/Volume 1/Genesis/Chapter 5`) [container]
        - chapter: Volume 1: Genesis: Chapter 6 (`Matthew Henry's Commentary on the Whole Bible/Volume 1/Genesis/Chapter 6`) [container]
        - chapter: Volume 1: Genesis: Chapter 7 (`Matthew Henry's Commentary on the Whole Bible/Volume 1/Genesis/Chapter 7`) [container]
    - … 121 more
1. `The Works of Francis Bacon`: subpages, collection, 81 parts, 1 missing, order chain, proofread
    - toc: The Works of Francis Bacon
    - volume: Volume 1 (`The Works of Francis Bacon/Volume 1`) [hierarchy]
      - text: Life of Bacon, Part I. (`The Works of Francis Bacon/Volume 1/Life of Bacon Part I`) [hierarchy]
      - text: Life of Bacon, Part II. (`The Works of Francis Bacon/Volume 1/Life of Bacon Part II`) [hierarchy]
      - volume: Essays (`The Works of Francis Bacon/Volume 1/Essays`) [hierarchy]
        - text: Of Truth (`The Works of Francis Bacon/Volume 1/Essays/Of Truth`) [hierarchy]
        - text: Of Death (`The Works of Francis Bacon/Volume 1/Essays/Of Death`) [hierarchy]
        - text: Of Unity in Religion (`The Works of Francis Bacon/Volume 1/Essays/Of Unity in Religion`) [hierarchy]
        - text: Of Revenge (`The Works of Francis Bacon/Volume 1/Essays/Of Revenge`) [hierarchy]
        - text: Of Adversity (`The Works of Francis Bacon/Volume 1/Essays/Of Adversity`) [hierarchy]
        - text: Of Simulation and Dissimulation (`The Works of Francis Bacon/Volume 1/Essays/Of Simulation and Dissimulation`) [hierarchy]
        - text: Of Parents and Children (`The Works of Francis Bacon/Volume 1/Essays/Of Parents and Children`) [hierarchy]
    - … 70 more
1. `The Chap-Book`: subpages, collection, 10 parts, 25 missing, order single, unproofread
    - front: The Chap-Book
    - volume: Volume 6 (`The Chap-Book/Volume 6`) [container]
      - volume: Number 3 (`The Chap-Book/Volume 6/Number 3`) [hierarchy]
        - volume: A Christmas Garland (`The Chap-Book/Volume 6/Number 3/A Christmas Garland`) [container]
          - text: A Christmas Garland: The Sorrows of Millicent (`The Chap-Book/Volume 6/Number 3/A Christmas Garland/The Sorrows of Millicent`) [container]
          - text: A Christmas Garland: The Blessedness of Apple-Pie Beds (`The Chap-Book/Volume 6/Number 3/A Christmas Garland/The Blessedness of Apple-Pie Beds`) [container]
          - text: A Christmas Garland: The Defossilized Plum-Pudding (`The Chap-Book/Volume 6/Number 3/A Christmas Garland/The Defossilized Plum-Pudding`) [container]
          - text: A Christmas Garland: Beside the Bonnie Mark (`The Chap-Book/Volume 6/Number 3/A Christmas Garland/Beside the Bonnie Mark`) [container]
          - text: A Christmas Garland: Holly (`The Chap-Book/Volume 6/Number 3/A Christmas Garland/Holly`) [container]
          - text: A Christmas Garland: The Victory of Aphasia Gibberish (`The Chap-Book/Volume 6/Number 3/A Christmas Garland/The Victory of Aphasia Gibberish`) [container]
          - text: A Christmas Garland: A Vain Child (`The Chap-Book/Volume 6/Number 3/A Christmas Garland/A Vain Child`) [container]
1. `The Dial`: mixed, collection, 167 parts, 24 missing, order natural, proofread
    - front: The Dial
    - chapter: Volume 1 (July 1840 – April 1841) (`The Dial (First Series)/Volume 1`) [container]
    - volume: Volume 1, Issue 1 (`The Dial (First Series)/Volume 1/Issue 1`) [container]
      - text: The Editors to the Reader (`The Dial (First Series)/Volume 1/Issue 1/The Editors to the Reader`) [container]
      - text: A Short Essay on Critics (`The Dial (First Series)/Volume 1/Issue 1/A Short Essay on Critics`) [container]
      - text: To the Aurora Borealis (`The Dial (First Series)/Volume 1/Issue 1/To the Aurora Borealis`) [container]
      - chapter: Notes from the Journal of a Scholar (`The Dial (First Series)/Volume 1/Issue 1/Notes from the Journal of a Scholar`) [container]
      - text: The Religion of Beauty (`The Dial (First Series)/Volume 1/Issue 1/The Religion of Beauty`) [container]
      - text: Channing's Translation of Jouffroy (`The Dial (First Series)/Volume 1/Issue 1/Channing's Translation of Jouffroy`) [container]
      - text: ../Aulus Persius Flaccus/ (`The Dial (First Series)/Volume 1/Issue 1/Aulus Persius Flaccus`) [container]
      - text: ../The Shield/ (`The Dial (First Series)/Volume 1/Issue 1/The Shield`) [container]
      - text: ../The Problem/ (`The Dial (First Series)/Volume 1/Issue 1/The Problem`) [container]
    - … 156 more
1. `Notes and Queries`: subpages, collection, 225 parts, 237 missing, order toc-partial, proofread
    - front: Notes and Queries
    - volume: Volume 1: November, 1849–May, 1850 (`Notes and Queries/Series 1/Volume 1`) [hierarchy]
      - volume: Number 1: 3rd November 1849 (`Notes and Queries/Series 1/Volume 1/Number 1`) [hierarchy]
        - chapter: Notes and Queries (`Notes and Queries/Series 1/Volume 1/Number 1/Notes and Queries`) [container]
        - text: Capture of the Duke of Monmouth (`Notes and Queries/Series 1/Volume 1/Number 1/Place of Capture of the Duke of Monmouth`) [container]
        - text: Shakespeare and Deer-Stealing (`Notes and Queries/Series 1/Volume 1/Number 1/Shakespeare and Deer-Stealing`) [container]
        - text: "Pray remember the Grotto!" on St. James's Day (`Notes and Queries/Series 1/Volume 1/Number 1/"Pray remember the Grotto!" on St. James's Day`) [container]
        - text: Note of a MS. Volume of Chronicles at Reigate (`Notes and Queries/Series 1/Volume 1/Number 1/Note of a MS. Volume of Chronicles at Reigate`) [hierarchy]
        - text: The Morning Chronicle, Etc. (`Notes and Queries/Series 1/Volume 1/Number 1/The Morning Chronicle, Etc.`) [hierarchy]
        - text: Value of a Repository For "Notes" (`Notes and Queries/Series 1/Volume 1/Number 1/Value of a Repository For "Notes"`) [hierarchy]
        - text: A Flemish Account (`Notes and Queries/Series 1/Volume 1/Number 1/A Flemish Account`) [hierarchy]
        - text: A Bibliographic Project (`Notes and Queries/Series 1/Volume 1/Number 1/A Bibliographic Project`) [hierarchy]
    - … 214 more
1. `The Royal Lady's Magazine`: subpages, collection, 9 parts, 32 missing, order single, proofread
    - front: The Royal Lady's Magazine
    - volume: Series 1 (`The Royal Lady's Magazine/Series 1`) [container]
      - volume: Volume 1 (`The Royal Lady's Magazine/Series 1/Volume 1`) [hierarchy]
        - text: Auld Robin Gray (`The Royal Lady's Magazine/Series 1/Volume 1/January 1831/Auld Robin Gray`) [hierarchy]
      - volume: Volume 2 (`The Royal Lady's Magazine/Series 1/Volume 2`) [hierarchy]
        - volume: July 1831 (`The Royal Lady's Magazine/Series 1/Volume 2/July 1831`) [hierarchy]
          - text: Cuddy Clew (`The Royal Lady's Magazine/Series 1/Volume 2/July 1831/Cuddy Clew`) [hierarchy]
          - volume: German Prolusions (`The Royal Lady's Magazine/Series 1/Volume 2/July 1831/German Prolusions`) [hierarchy]
            - text: The Grateful Ghost (`The Royal Lady's Magazine/Series 1/Volume 2/July 1831/German Prolusions/The Grateful Ghost`) [container]
            - text: Hans in Luck (`The Royal Lady's Magazine/Series 1/Volume 2/July 1831/German Prolusions/Hans in Luck`) [container]
1. `Works of Gustave Flaubert`: subpages, book, 33 parts, 32 missing, order natural, proofread
    - front: Works of Gustave Flaubert
    - volume: Madame Bovary (`Works of Gustave Flaubert/Madame Bovary`) [container]
      - text: Critical Introduction by Ferdinand Brunetière (`Works of Gustave Flaubert/Madame Bovary/Critical Introduction`) [container]
      - text: Biographical Preface by Robert Arnot (`Works of Gustave Flaubert/Madame Bovary/Biographical Preface`) [container]
      - chapter: Part I, Chapter I (`Works of Gustave Flaubert/Madame Bovary/Part 1/Chapter 1`) [container]
      - chapter: Part I, Chapter II (`Works of Gustave Flaubert/Madame Bovary/Part 1/Chapter 2`) [container]
      - chapter: Part I, Chapter III (`Works of Gustave Flaubert/Madame Bovary/Part 1/Chapter 3`) [container]
      - chapter: Part I, Chapter IV (`Works of Gustave Flaubert/Madame Bovary/Part 1/Chapter 4`) [container]
      - chapter: Part I, Chapter V (`Works of Gustave Flaubert/Madame Bovary/Part 1/Chapter 5`) [container]
      - chapter: Part I, Chapter VI (`Works of Gustave Flaubert/Madame Bovary/Part 1/Chapter 6`) [container]
      - chapter: Part I, Chapter VII (`Works of Gustave Flaubert/Madame Bovary/Part 1/Chapter 7`) [container]
      - chapter: Part I, Chapter VIII (`Works of Gustave Flaubert/Madame Bovary/Part 1/Chapter 8`) [container]
    - … 22 more
1. `The American Journal of Sociology`: subpages, collection, 91 parts, 204 missing, order toc-partial, unproofread
    - front: The American Journal of Sociology
    - volume: Volume 1 (`The American Journal of Sociology/Volume 01`) [container]
      - text: A Belated Industry (`The American Journal of Sociology/Volume 01/Number 5/A Belated Industry`) [container]
      - text: Recent Legislation in Restraint of Trade (`The American Journal of Sociology/Volume 01/Number 6/Recent Legislation in Restraint of Trade`) [container]
      - text: The Relation of Anthropology to the Study of History (`The American Journal of Sociology/Volume 1/Number 1/The Relation of Anthropology to the Study of History`) [container]
      - text: Sociology in Italy (`The American Journal of Sociology/Volume 01/Number 3/Sociology in Italy`) [container]
      - text: Anti-Monopoly Legislation in the United States (`The American Journal of Sociology/Volume 01/Number 4/Anti-Monopoly Legislation in the United States`) [container]
      - text: The Place and Functions of Voluntary Associations (`The American Journal of Sociology/Volume 01/Number 3/The Place and Functions of Voluntary Associations`) [container]
      - text: Business Men and Social Theorists (`The American Journal of Sociology/Volume 01/Number 4/Business Men and Social Theorists`) [container]
      - text: Rise of the German Inner Mission (`The American Journal of Sociology/Volume 01/Number 5/Rise of the German Inner Mission`) [container]
      - text: The German Inner Mission (`The American Journal of Sociology/Volume 01/Number 6/The German Inner Mission`) [container]
      - text: The American University (`The American Journal of Sociology/Volume 01/Number 2/The American University`) [container]
    - … 80 more
1. `The Leisure Hour (periodical)`: subpages, collection, 5 parts, 0 missing, order toc, unknown
    - toc: The Leisure Hour (periodical)
    - volume: 1871 (`The Leisure Hour (periodical)/1871`) [container]
      - volume: Number 1032 (`The Leisure Hour (periodical)/1871/Number 1032`) [container]
        - text: Mrs Mary Somerville (`The Leisure Hour (periodical)/1871/Number 1032/Mrs Mary Somerville`) [container]
    - volume: 1890 (`The Leisure Hour (periodical)/1890`) [container]
      - text: Vol. 39 (`The Leisure Hour (periodical)/1890/Vol. 39`) [container]
1. `The Poems and Prose Remains of Arthur Hugh Clough`: subpages, collection, 110 parts, 20 missing, order toc, proofread
    - toc: The Poems and Prose Remains of Arthur Hugh Clough
    - volume: Volume 1 (`The Poems and Prose Remains of Arthur Hugh Clough/Volume 1`) [container]
      - text: Memoir (`The Poems and Prose Remains of Arthur Hugh Clough/Volume 1/Memoir`) [container]
      - text: Letters from 1853 to 1861 (London) (`The Poems and Prose Remains of Arthur Hugh Clough/Volume 1/Letters from 1853 to 1861`) [container]
    - volume: Volume 2 (`The Poems and Prose Remains of Arthur Hugh Clough/Volume 2`) [container]
      - text: Thoughts of Home (`The Poems and Prose Remains of Arthur Hugh Clough/Volume 2/Thoughts of Home`) [container]
      - text: An Evening Walk in Spring (`The Poems and Prose Remains of Arthur Hugh Clough/Volume 2/An Evening Walk in Spring`) [container]
      - text: An Incident (`The Poems and Prose Remains of Arthur Hugh Clough/Volume 2/An Incident`) [container]
      - text: The Thread of Truth (`The Poems and Prose Remains of Arthur Hugh Clough/Volume 2/The Thread of Truth`) [container]
      - text: Revival (`The Poems and Prose Remains of Arthur Hugh Clough/Volume 2/Revival`) [container]
      - text: The Shady Lane (`The Poems and Prose Remains of Arthur Hugh Clough/Volume 2/The Shady Lane`) [container]
      - text: The Higher Courage (`The Poems and Prose Remains of Arthur Hugh Clough/Volume 2/The Higher Courage`) [container]
    - … 99 more
1. `The Golden Bowl (New York: Charles Scribner's Sons, 1909)`: subpages, book, 51 parts, 0 missing, order chain, proofread
    - toc: The Golden Bowl (New York: Charles Scribner's Sons, 1909)
    - volume: Volume 1 (`The Golden Bowl (New York: Charles Scribner's Sons, 1909)/Volume 1`) [container]
      - chapter: Preface (`The Golden Bowl (New York: Charles Scribner's Sons, 1909)/Volume 1/Preface`) [container]
      - volume: Book 1 (`The Golden Bowl (New York: Charles Scribner's Sons, 1909)/Volume 1/Book 1`) [container]
        - chapter: Chapter 1 (`The Golden Bowl (New York: Charles Scribner's Sons, 1909)/Volume 1/Book 1/Chapter 1`) [container]
        - chapter: Chapter 2 (`The Golden Bowl (New York: Charles Scribner's Sons, 1909)/Volume 1/Book 1/Chapter 2`) [container]
        - chapter: Chapter 3 (`The Golden Bowl (New York: Charles Scribner's Sons, 1909)/Volume 1/Book 1/Chapter 3`) [container]
        - chapter: Chapter 4 (`The Golden Bowl (New York: Charles Scribner's Sons, 1909)/Volume 1/Book 1/Chapter 4`) [container]
        - chapter: Chapter 5 (`The Golden Bowl (New York: Charles Scribner's Sons, 1909)/Volume 1/Book 1/Chapter 5`) [container]
        - chapter: Chapter 6 (`The Golden Bowl (New York: Charles Scribner's Sons, 1909)/Volume 1/Book 1/Chapter 6`) [container]
      - volume: Book 2 (`The Golden Bowl (New York: Charles Scribner's Sons, 1909)/Volume 1/Book 2`) [container]
        - chapter: Chapter 1 (`The Golden Bowl (New York: Charles Scribner's Sons, 1909)/Volume 1/Book 2/Chapter 1`) [container]
    - … 40 more
1. `The Kaleidoscope; or, Literary and Scientific Mirror`: subpages, collection, 26 parts, 151 missing, order chain, unproofread
    - toc: The Kaleidoscope; or, Literary and Scientific Mirror
    - text: Series 1 (`The Kaleidoscope; or, Literary and Scientific Mirror/Series 1`) [container]
    - volume: Series 2 (`The Kaleidoscope; or, Literary and Scientific Mirror/Series 2`) [container]
      - volume: Volume 4 (`The Kaleidoscope; or, Literary and Scientific Mirror/Series 2/Volume 4`) [hierarchy]
        - volume: Number 193 (`The Kaleidoscope; or, Literary and Scientific Mirror/Series 2/Volume 4/Number 193`) [hierarchy]
          - text: The Dog With Two Masters (`The Kaleidoscope; or, Literary and Scientific Mirror/Series 2/Volume 4/Number 193/The Dog With Two Masters`) [hierarchy]
        - volume: Number 194 (`The Kaleidoscope; or, Literary and Scientific Mirror/Series 2/Volume 4/Number 194`) [hierarchy]
          - text: The Dog With Two Masters (`The Kaleidoscope; or, Literary and Scientific Mirror/Series 2/Volume 4/Number 194/The Dog With Two Masters`) [hierarchy]
        - volume: Number 195 (`The Kaleidoscope; or, Literary and Scientific Mirror/Series 2/Volume 4/Number 195`) [hierarchy]
          - text: The Dog With Two Masters (`The Kaleidoscope; or, Literary and Scientific Mirror/Series 2/Volume 4/Number 195/The Dog With Two Masters`) [hierarchy]
        - volume: Number 196 (`The Kaleidoscope; or, Literary and Scientific Mirror/Series 2/Volume 4/Number 196`) [hierarchy]
          - text: The Dog With Two Masters (`The Kaleidoscope; or, Literary and Scientific Mirror/Series 2/Volume 4/Number 196/The Dog With Two Masters`) [hierarchy]
    - … 15 more
1. `The English and Scottish Popular Ballads`: subpages, book, 363 parts, 17 missing, order chain, proofread
    - front: The English and Scottish Popular Ballads
    - volume: Part 1 (`The English and Scottish Popular Ballads/Part 1`) [container]
      - chapter: 1. Riddles Wisely Expounded (`The English and Scottish Popular Ballads/Part 1/Chapter 1`) [hierarchy]
      - chapter: 2. The Elfin Knight (`The English and Scottish Popular Ballads/Part 1/Chapter 2`) [hierarchy]
      - chapter: 3. The Fause Knight on the Road (`The English and Scottish Popular Ballads/Part 1/Chapter 3`) [hierarchy]
      - chapter: 4. Lady Isabel and the Elf Knight (`The English and Scottish Popular Ballads/Part 1/Chapter 4`) [hierarchy]
      - chapter: 5. Gil Brenton (`The English and Scottish Popular Ballads/Part 1/Chapter 5`) [hierarchy]
      - chapter: 6. Willie's Lady (`The English and Scottish Popular Ballads/Part 1/Chapter 6`) [hierarchy]
      - chapter: 7. Earl Brand (`The English and Scottish Popular Ballads/Part 1/Chapter 7`) [hierarchy]
      - chapter: 8. Erlinton (`The English and Scottish Popular Ballads/Part 1/Chapter 8`) [hierarchy]
      - chapter: 9. The Fair Flower of Northumberland (`The English and Scottish Popular Ballads/Part 1/Chapter 9`) [hierarchy]
      - chapter: 10. The Twa Sisters (`The English and Scottish Popular Ballads/Part 1/Chapter 10`) [hierarchy]
    - … 352 more
1. `Victor Hugo's Works (Guernsey Edition)`: virtual, book, 5 parts, 2 missing, order single, proofread
    - volume: Volume 14 (`Victor Hugo's Works (Guernsey Edition)/Volume 14`)
      - volume: The Fool's Revenge (`Victor Hugo's Works (Guernsey Edition)/Volume 14/The Fool's Revenge`) [container]
        - text: The Fool's Revenge, Act I. (`Victor Hugo's Works (Guernsey Edition)/Volume 14/The Fool's Revenge/Act 1`) [container]
        - text: The Fool's Revenge, Act II. (`Victor Hugo's Works (Guernsey Edition)/Volume 14/The Fool's Revenge/Act 2`) [container]
        - text: The Fool's Revenge, Act III. (`Victor Hugo's Works (Guernsey Edition)/Volume 14/The Fool's Revenge/Act 3`) [container]

### I: works with other editions (3,557)

1. `Bold dragoon`: subpages, book, 7 parts, 0 missing, order chain, validated
    - toc: Bold dragoon
    - text: The Bold Dragoon (`Bold dragoon/The Bold Dragoon`) [container]
    - text: Gin I Had a Wee House (`Bold dragoon/Gin I Had a Wee House`) [container]
    - text: Paddy's Legacy (`Bold dragoon/Paddy's Legacy`) [container]
    - text: Why, Soldiers, Why (`Bold dragoon/Why, Soldiers, Why`) [container]
    - text: The Lassie I Loe Best of A (`Bold dragoon/The Lassie I Loe Best of A`) [container]
    - text: O Wha's at My Chamber Door (`Bold dragoon/O Wha's at My Chamber Door`) [container]
    - text: Yarrow (`Bold dragoon/Yarrow`) [container]
1. `Zodiac Killer letter, Vallejo Times-Herald, July 31st 1969`: single, book, 1 parts, 0 missing, order –, validated
    - text: Zodiac Killer letter, Vallejo Times-Herald, July 31st 1969
1. `Route across the Rocky Mountains with a Description of Oregon and California (OHQ)`: single, book, 1 parts, 0 missing, order –, validated
    - text: Route across the Rocky Mountains with a Description of Oregon and California (OHQ)
1. `Rubáiyát of Omar Khayyám (tr. Fitzgerald, 1905)`: single, book, 1 parts, 0 missing, order –, unproofread
    - text: Rubáiyát of Omar Khayyám (tr. Fitzgerald, 1905)
1. `Preludes (Meynell)`: subpages, book, 37 parts, 0 missing, order toc, validated
    - front: Preludes (Meynell)
    - text: IN EARLY SPRING (`Preludes (Meynell)/In Early Spring`) [container]
    - text: PARTED (`Preludes (Meynell)/Parted`) [container]
    - text: TO A POET (`Preludes (Meynell)/To a Poet`) [container]
    - text: SONG OF THE SPRING TO THE SUMMER (`Preludes (Meynell)/Song of the Spring to the Summer`) [container]
    - text: TO THE BELOVED (`Preludes (Meynell)/To the Beloved`) [container]
    - text: MEDITATION (`Preludes (Meynell)/Meditation`) [container]
    - text: TO THE BELOVED DEAD (`Preludes (Meynell)/To the Beloved Dead`) [container]
    - text: SONNET (`Preludes (Meynell)/Sonnet1`) [container]
    - text: IN AUTUMN (`Preludes (Meynell)/In Autumn`) [container]
    - chapter: A LETTER FROM A GIRL TO HER OWN OLD AGE (`Preludes (Meynell)/A Letter from a Girl to her own Old Age`) [container]
    - text: SONG (`Preludes (Meynell)/Song1`) [container]
    - … 26 more
1. `Some Sort of Somebody (1915)`: single, single, 1 parts, 0 missing, order –, unknown
    - text: Some Sort of Somebody (1915)
1. `Willie was a wanton wag (1823)`: subpages, book, 4 parts, 0 missing, order chain, validated
    - toc: Willie was a wanton wag (1823)
    - text: Willie was a wanton wag (`Willie was a wanton wag (1823)/Willie was a wanton wag`) [container]
    - text: The jolly miller (`Willie was a wanton wag (1823)/The jolly miller`) [container]
    - text: When trees did bud (`Willie was a wanton wag (1823)/When trees did bud`) [container]
    - text: The brown jug (`Willie was a wanton wag (1823)/The brown jug`) [container]
1. `Willy Rilly, or, The constant lovers`: subpages, book, 3 parts, 0 missing, order chain, validated
    - toc: Willy Rilly, or, The constant lovers
    - text: Willy Rilly (`Willy Rilly, or, The constant lovers/Willy Rilly`) [container]
    - text: The Hurl Barrow (`Willy Rilly, or, The constant lovers/The Hurl Barrow`) [container]
    - text: The Lass O'Glenshee (`Willy Rilly, or, The constant lovers/The Lass O'Glenshee`) [container]
1. `The Ancient Stone Implements, Weapons, and Ornaments of Great Britain`: subpages, book, 29 parts, 0 missing, order chain, proofread
    - front: The Ancient Stone Implements, Weapons, and Ornaments of Great Britain
    - text: Illustrations (`The Ancient Stone Implements, Weapons, and Ornaments of Great Britain/Illustrations`) [container]
    - chapter: Chapter 1 (`The Ancient Stone Implements, Weapons, and Ornaments of Great Britain/Chapter 1`) [container]
    - chapter: Chapter 2 (`The Ancient Stone Implements, Weapons, and Ornaments of Great Britain/Chapter 2`) [container]
    - chapter: Chapter 3 (`The Ancient Stone Implements, Weapons, and Ornaments of Great Britain/Chapter 3`) [container]
    - chapter: Chapter 4 (`The Ancient Stone Implements, Weapons, and Ornaments of Great Britain/Chapter 4`) [container]
    - chapter: Chapter 5 (`The Ancient Stone Implements, Weapons, and Ornaments of Great Britain/Chapter 5`) [container]
    - chapter: Chapter 6 (`The Ancient Stone Implements, Weapons, and Ornaments of Great Britain/Chapter 6`) [container]
    - chapter: Chapter 7 (`The Ancient Stone Implements, Weapons, and Ornaments of Great Britain/Chapter 7`) [container]
    - chapter: Chapter 8 (`The Ancient Stone Implements, Weapons, and Ornaments of Great Britain/Chapter 8`) [container]
    - chapter: Chapter 9 (`The Ancient Stone Implements, Weapons, and Ornaments of Great Britain/Chapter 9`) [container]
    - chapter: Chapter 10 (`The Ancient Stone Implements, Weapons, and Ornaments of Great Britain/Chapter 10`) [container]
    - … 18 more
1. `Bonny Barbara Allan (1823)`: subpages, book, 6 parts, 0 missing, order chain, validated
    - front: Bonny Barbara Allan (1823)
    - text: Bonny Barbara Allan (`Bonny Barbara Allan (1823)/Bonny Barbara Allan`) [container]
    - text: Yarrow braes (`Bonny Barbara Allan (1823)/Yarrow braes`) [container]
    - text: The Hills of the Highlands (`Bonny Barbara Allan (1823)/The hills of the Highlands`) [container]
    - text: The lassie I lo'e best of a' (`Bonny Barbara Allan (1823)/The lassie I lo'e best of a'`) [container]
    - text: Bessie Bell and Mary Gray (`Bonny Barbara Allan (1823)/Bessie Bell and Mary Gray`) [container]
    - text: O meikle thinks my love (`Bonny Barbara Allan (1823)/O meikle thinks my love`) [container]
1. `Austria and Europe (The Bohemian Review, 1918)`: single, single, 1 parts, 0 missing, order –, unknown
    - text: Austria and Europe (The Bohemian Review, 1918)
1. `Lyra Davidica`: subpages, book, 34 parts, 0 missing, order chain, proofread
    - front: Lyra Davidica
    - text: A Morning Hymn (`Lyra Davidica/A Morning Hymn`) [container]
    - text: An Evening Hymn (`Lyra Davidica/An Evening Hymn`) [container]
    - text: A Mid-Night Hymn (`Lyra Davidica/A Mid-Night Hymn`) [container]
    - text: The Doxology to the Three foregoing Hymns (`Lyra Davidica/The Doxology to the Three foregoing Hymns`) [container]
    - text: On the birth of Christ (`Lyra Davidica/On the birth of Christ`) [container]
    - text: Commemoration of Christ's Death (`Lyra Davidica/Commemoration of Christ's Death`) [container]
    - text: The Resurrection (`Lyra Davidica/The Resurrection`) [container]
    - text: A Resurrection Dialogue (`Lyra Davidica/A Resurrection Dialogue`) [container]
    - text: St. Bernard's Jubile (`Lyra Davidica/St. Bernard's Jubile`) [container]
    - text: The Song of the 3 Children corrected (`Lyra Davidica/The Song of the 3 Children corrected`) [container]
    - text: The Lord's Prayer Paraphras'd (`Lyra Davidica/The Lord's Prayer paraphras'd`) [container]
    - … 23 more
1. `Henry IV Part 1 (1917) Yale`: subpages, book, 12 parts, 0 missing, order chain, validated
    - front: Henry IV Part 1 (1917) Yale
    - volume: The Text (`Henry IV Part 1 (1917) Yale/Text`) [container]
      - text: The Text: Act I (`Henry IV Part 1 (1917) Yale/Text/Act I`) [container]
      - text: The Text: Act II (`Henry IV Part 1 (1917) Yale/Text/Act II`) [container]
      - text: The Text: Act III (`Henry IV Part 1 (1917) Yale/Text/Act III`) [container]
      - text: The Text: Act IV (`Henry IV Part 1 (1917) Yale/Text/Act IV`) [container]
      - text: The Text: Act V (`Henry IV Part 1 (1917) Yale/Text/Act V`) [container]
    - chapter: Notes (`Henry IV Part 1 (1917) Yale/Notes`) [container]
    - chapter: Appendix A (`Henry IV Part 1 (1917) Yale/Appendix A`) [container]
    - chapter: Appendix B (`Henry IV Part 1 (1917) Yale/Appendix B`) [container]
    - chapter: Appendix C (`Henry IV Part 1 (1917) Yale/Appendix C`) [container]
    - text: Bibliography (`Henry IV Part 1 (1917) Yale/Bibliography`) [container]
    - … 1 more
1. `Littell's Living Age`: subpages, collection, 1,842 parts, 7333 missing, order toc-partial, proofread
    - front: Littell's Living Age
    - volume: Volume 1 (`Littell's Living Age/Volume 1`) [container]
      - chapter: Volume 1, Issue 1 : Simpson's Discoveries on the North Coast of America (`Littell's Living Age/Volume 1/Issue 1/Simpson's Discoveries on the North Coast of America`) [container]
      - chapter: Volume 1, Issue 1 : Prescott's History of the Conquest of Mexico (`Littell's Living Age/Volume 1/Issue 1/Prescott's History of the Conquest of Mexico`) [container]
      - chapter: Volume 1, Issue 1 : Letter from Charles Edwards, Esq. (`Littell's Living Age/Volume 1/Issue 1/Letter from Charles Edwards, Esq.`) [container]
      - chapter: Volume 1, Issue 1 : Ralph Waldo Emerson (`Littell's Living Age/Volume 1/Issue 1/Ralph Waldo Emerson`) [container]
      - chapter: Volume 1, Issue 1 : Fitz-Greene Halleck (`Littell's Living Age/Volume 1/Issue 1/Fitz-Greene Halleck`) [container]
      - chapter: Volume 1, Issue 1 : W. C. Bryant (`Littell's Living Age/Volume 1/Issue 1/W. C. Bryant`) [container]
      - chapter: Volume 1, Issue 1 : H. W. Longfellow (`Littell's Living Age/Volume 1/Issue 1/H. W. Longfellow`) [container]
      - chapter: Volume 1, Issue 1 : The Mauvais Pas (`Littell's Living Age/Volume 1/Issue 1/The Mauvais Pas`) [container]
      - chapter: Volume 1, Issue 1 : On French Cookery (`Littell's Living Age/Volume 1/Issue 1/On French Cookery`) [container]
      - chapter: Volume 1, Issue 1 : Oh, ask me not how long (`Littell's Living Age/Volume 1/Issue 1/Oh, ask me not how long`) [container]
    - … 1,831 more
1. `The Four Just Men (1920)`: subpages, book, 29 parts, 0 missing, order toc, unproofread
    - toc: The Four Just Men (1920)
    - chapter: CHAPTER I. (`The Four Just Men (1920)/Part 1/Chapter 1`) [container]
    - chapter: Chapter 2 (`The Four Just Men (1920)/Part 1/Chapter 2`) [container]
    - chapter: Chapter 3 (`The Four Just Men (1920)/Part 1/Chapter 3`) [container]
    - chapter: Chapter 4 (`The Four Just Men (1920)/Part 1/Chapter 4`) [container]
    - chapter: Chapter 5 (`The Four Just Men (1920)/Part 1/Chapter 5`) [container]
    - chapter: Chapter 6 (`The Four Just Men (1920)/Part 1/Chapter 6`) [container]
    - chapter: Chapter 7 (`The Four Just Men (1920)/Part 1/Chapter 7`) [container]
    - chapter: Chapter 8 (`The Four Just Men (1920)/Part 1/Chapter 8`) [container]
    - chapter: Chapter 9 (`The Four Just Men (1920)/Part 1/Chapter 9`) [container]
    - chapter: Chapter 10 (`The Four Just Men (1920)/Part 1/Chapter 10`) [container]
    - chapter: Chapter 11 (`The Four Just Men (1920)/Part 1/Chapter 11`) [container]
    - … 18 more
1. `The Quaker Poets of Great Britain and Ireland`: subpages, book, 66 parts, 0 missing, order toc, proofread
    - front: The Quaker Poets of Great Britain and Ireland
    - text: Rise of Society, etc. (`The Quaker Poets of Great Britain and Ireland/Rise of Society, etc.`) [container]
    - text: Adams, Jessie (`The Quaker Poets of Great Britain and Ireland/Adams, Jessie`) [container]
    - text: Baker, Gulielma A. Wheeler (`The Quaker Poets of Great Britain and Ireland/Baker, Gulielma A. Wheeler`) [container]
    - text: Ball, William (`The Quaker Poets of Great Britain and Ireland/Ball, William`) [container]
    - text: Barber, William the Elder (`The Quaker Poets of Great Britain and Ireland/Barber, William the Elder`) [container]
    - text: Barton, Bernard (`The Quaker Poets of Great Britain and Ireland/Barton, Bernard`) [container]
    - text: Binns, Henry (`The Quaker Poets of Great Britain and Ireland/Binns, Henry`) [container]
    - text: Beale, James (`The Quaker Poets of Great Britain and Ireland/Beale, James`) [container]
    - text: Beck, Mary Elizabeth (`The Quaker Poets of Great Britain and Ireland/Beck, Mary Elizabeth`) [container]
    - text: Bigg, Louisa (`The Quaker Poets of Great Britain and Ireland/Bigg, Louisa`) [container]
    - text: Bird, Robert (`The Quaker Poets of Great Britain and Ireland/Bird, Robert`) [container]
    - … 55 more
1. `Our Land and Land Policy (1871)`: subpages, book, 1 parts, 0 missing, order single, proofread
    - front: Our Land and Land Policy (1871)
    - chapter: Chapter 1 (`Our Land and Land Policy (1871)/Chapter 1`) [container]
1. `The Papyrus of Ani (1913)`: single, single, 1 parts, 0 missing, order –, unknown
    - text: The Papyrus of Ani (1913)
1. `Book of Common Prayer (ECUSA)`: subpages, book, 92 parts, 0 missing, order scan, proofread
    - front: Book of Common Prayer (ECUSA)
    - text: The Ratification of the Book of Common Prayer (`Book of Common Prayer (ECUSA)/The Ratification of the Book of Common Prayer`) [container]
    - text: The Preface (`Book of Common Prayer (ECUSA)/The Preface`) [container]
    - text: Concerning the Service of the Church (`Book of Common Prayer (ECUSA)/Concerning the Service of the Church`) [container]
    - text: The Calendar of the Church Year (`Book of Common Prayer (ECUSA)/The Calendar of the Church Year`) [container]
    - volume: The Daily Office (`Book of Common Prayer (ECUSA)/The Daily Office`) [container]
      - text: Daily Morning Prayer: Rite One (`Book of Common Prayer (ECUSA)/The Daily Office/Daily Morning Prayer: Rite One`) [container]
      - text: Daily Evening Prayer: Rite One (`Book of Common Prayer (ECUSA)/The Daily Office/Daily Evening Prayer: Rite One`) [container]
      - text: Daily Morning Prayer: Rite Two (`Book of Common Prayer (ECUSA)/The Daily Office/Daily Morning Prayer: Rite Two`) [container]
      - text: An Order of Service for Noonday (`Book of Common Prayer (ECUSA)/The Daily Office/Noonday Prayer`) [container]
      - text: An Order of Worship for the Evening (`Book of Common Prayer (ECUSA)/The Daily Office/Order of Worship for the Evening`) [container]
      - text: Daily Evening Prayer: Rite Two (`Book of Common Prayer (ECUSA)/The Daily Office/Daily Evening Prayer: Rite Two`) [container]
    - … 81 more
1. `The Souls of Black Folk (2nd ed)`: subpages, book, 16 parts, 0 missing, order chain, proofread
    - front: The Souls of Black Folk (2nd ed)
    - text: The Forethought (`The Souls of Black Folk (2nd ed)/The Forethought`) [container]
    - chapter: I. Of our Spiritual Strivings (`The Souls of Black Folk (2nd ed)/Chapter 1`) [container]
    - chapter: II. Of the Dawn of Freedom (`The Souls of Black Folk (2nd ed)/Chapter 2`) [container]
    - chapter: III. Of Mr. Booker T. Washington and Others (`The Souls of Black Folk (2nd ed)/Chapter 3`) [container]
    - chapter: IV. Of the Meaning of Progress (`The Souls of Black Folk (2nd ed)/Chapter 4`) [container]
    - chapter: V. Of the Wings of Atalanta (`The Souls of Black Folk (2nd ed)/Chapter 5`) [container]
    - chapter: VI. Of the Training of Black Men (`The Souls of Black Folk (2nd ed)/Chapter 6`) [container]
    - chapter: VII. Of the Black Belt (`The Souls of Black Folk (2nd ed)/Chapter 7`) [container]
    - chapter: VIII. Of the Quest of the Golden Fleece (`The Souls of Black Folk (2nd ed)/Chapter 8`) [container]
    - chapter: IX. Of the Sons of Master and Man (`The Souls of Black Folk (2nd ed)/Chapter 9`) [container]
    - chapter: X. Of the Faith of the Fathers (`The Souls of Black Folk (2nd ed)/Chapter 10`) [container]
    - … 5 more
1. `Stories Revived (3 volumes, London: Macmillan & Co., 1885)`: subpages, book, 3 parts, 0 missing, order chain, proofread
    - toc: Stories Revived (3 volumes, London: Macmillan & Co., 1885)
    - chapter: Volume 1 (`Stories Revived (3 volumes, London: Macmillan & Co., 1885)/Volume 1`) [container]
    - chapter: Volume 2 (`Stories Revived (3 volumes, London: Macmillan & Co., 1885)/Volume 2`) [container]
    - chapter: Volume 3 (`Stories Revived (3 volumes, London: Macmillan & Co., 1885)/Volume 3`) [container]
1. `The Rubaiyat of Omar Khayyam (tr. Fitzgerald, 3rd edition)`: subpages, book, 1 parts, 0 missing, order single, unknown
    - front: The Rubaiyat of Omar Khayyam (tr. Fitzgerald, 3rd edition)
    - chapter: Introduction (`The Rubaiyat of Omar Khayyam (tr. Fitzgerald, 3rd edition)/Introduction`) [container]
1. `An Elegy Wrote in a Country Church Yard (1751)`: single, book, 1 parts, 0 missing, order –, proofread, excluded index-faulty
    - text: An Elegy Wrote in a Country Church Yard (1751)
1. `The Jolly Miller (1799)`: subpages, book, 6 parts, 0 missing, order chain, validated
    - front: The Jolly Miller (1799)
    - text: The Jolly Miller (`The Jolly Miller (1799)/The Jolly Miller`) [container]
    - text: Jamie Out at Sea (`The Jolly Miller (1799)/Jamie Out at Sea`) [container]
    - text: Jack the Sailor Parting With Molly (`The Jolly Miller (1799)/Jack the Sailor Parting With Molly`) [container]
    - text: Charming Celia (`The Jolly Miller (1799)/Charming Celia`) [container]
    - text: Homeward Bound (`The Jolly Miller (1799)/Homeward Bound`) [container]
    - text: The Heaving of the Lead (`The Jolly Miller (1799)/The Heaving of the Lead`) [container]
1. `Hills o' Gallowa`: subpages, book, 5 parts, 0 missing, order chain, validated
    - toc: Hills o' Gallowa
    - text: Hills o' Gallowa (`Hills o' Gallowa/Hills o' Gallowa`) [container]
    - text: Last May a Braw Wooer (`Hills o' Gallowa/Last May a Braw Wooer`) [container]
    - text: Green Grow the Rashes, O (`Hills o' Gallowa/Green Grow the Rashes, O`) [container]
    - text: Sic a Wife as Willie Had (`Hills o' Gallowa/Sic a Wife as Willie Had`) [container]
    - text: How Sweet the Rose Blaws (`Hills o' Gallowa/How Sweet the Rose Blaws`) [container]
1. `Constitution of the Esthonian Republic (1920)`: single, single, 1 parts, 0 missing, order –, unknown
    - text: Constitution of the Esthonian Republic (1920)
1. `Poems of Nature (Whittier)`: subpages, book, 29 parts, 0 missing, order chain, proofread
    - front: Poems of Nature (Whittier)
    - text: Illustration 1 (`Poems of Nature (Whittier)/Illustration 1`) [container]
    - text: A Summer Pilgrimage (`Poems of Nature (Whittier)/A Summer Pilgrimage`) [container]
    - text: Night after a Storm at Sea (`Poems of Nature (Whittier)/Illustration 2`) [container]
    - text: The Three Bells (`Poems of Nature (Whittier)/The Three Bells`) [container]
    - text: Mount Chocorua (`Poems of Nature (Whittier)/Illustration 3`) [container]
    - text: A Mystery (`Poems of Nature (Whittier)/A Mystery`) [container]
    - text: STORM ON LAKE ASQUAM (`Poems of Nature (Whittier)/Illustration 4`) [container]
    - text: STORM ON LAKE ASQUAM (`Poems of Nature (Whittier)/Storm on Lake Asquam`) [container]
    - text: Evening by the Lakeside (`Poems of Nature (Whittier)/Illustration 5`) [container]
    - text: Summer by the Lakeside (`Poems of Nature (Whittier)/Summer by the Lakeside`) [container]
    - text: A Winter Storm (`Poems of Nature (Whittier)/Illustration 6`) [container]
    - … 18 more
1. `John Brown's Body (unsourced)`: single, single, 1 parts, 0 missing, order –, unknown
    - text: John Brown's Body (unsourced)
1. `Tales of Three Cities (Boston: James R. Osgood & Co., 1884)`: subpages, book, 18 parts, 0 missing, order chain, proofread
    - front: Tales of Three Cities (Boston: James R. Osgood & Co., 1884)
    - volume: "The Impressions of a Cousin" (`Tales of Three Cities (Boston: James R. Osgood & Co., 1884)/The Impressions of a Cousin`) [container]
      - text: "The Impressions of a Cousin", Part 1 (`Tales of Three Cities (Boston: James R. Osgood & Co., 1884)/The Impressions of a Cousin/Part 1`) [container]
      - text: "The Impressions of a Cousin", Part 2 (`Tales of Three Cities (Boston: James R. Osgood & Co., 1884)/The Impressions of a Cousin/Part 2`) [container]
    - volume: "Lady Barberina" (`Tales of Three Cities (Boston: James R. Osgood & Co., 1884)/Lady Barberina`) [container]
      - text: "Lady Barberina", Chapter 1 (`Tales of Three Cities (Boston: James R. Osgood & Co., 1884)/Lady Barberina/Chapter 1`) [container]
      - text: "Lady Barberina", Chapter 2 (`Tales of Three Cities (Boston: James R. Osgood & Co., 1884)/Lady Barberina/Chapter 2`) [container]
      - text: "Lady Barberina", Chapter 3 (`Tales of Three Cities (Boston: James R. Osgood & Co., 1884)/Lady Barberina/Chapter 3`) [container]
      - text: "Lady Barberina", Chapter 4 (`Tales of Three Cities (Boston: James R. Osgood & Co., 1884)/Lady Barberina/Chapter 4`) [container]
      - text: "Lady Barberina", Chapter 5 (`Tales of Three Cities (Boston: James R. Osgood & Co., 1884)/Lady Barberina/Chapter 5`) [container]
      - text: "Lady Barberina", Chapter 6 (`Tales of Three Cities (Boston: James R. Osgood & Co., 1884)/Lady Barberina/Chapter 6`) [container]
    - volume: "A New England Winter" (`Tales of Three Cities (Boston: James R. Osgood & Co., 1884)/A New England Winter`) [container]
    - … 7 more
1. `The Complete Poems of Paul Laurence Dunbar`: subpages, book, 413 parts, 60 missing, order toc-partial, proofread
    - front: The Complete Poems of Paul Laurence Dunbar
    - text: Li'l' Gal (`The Complete Poems of Paul Laurence Dunbar/Li'l' Gal`) [container]
    - text: Joggin' Erlong (`The Complete Poems of Paul Laurence Dunbar/Joggin' Erlong`) [container]
    - text: Absence (`The Complete Poems of Paul Laurence Dunbar/Absence`) [container]
    - text: Accountability (`The Complete Poems of Paul Laurence Dunbar/Accountability`) [container]
    - text: Advice (`The Complete Poems of Paul Laurence Dunbar/Advice`) [container]
    - text: After a Visit (`The Complete Poems of Paul Laurence Dunbar/After a Visit`) [container]
    - text: After The Quarrel (`The Complete Poems of Paul Laurence Dunbar/After The Quarrel`) [container]
    - text: After While (`The Complete Poems of Paul Laurence Dunbar/After While`) [container]
    - text: Alexander Crummell-Dead (`The Complete Poems of Paul Laurence Dunbar/Alexander Crummell-Dead`) [container]
    - text: Alice (`The Complete Poems of Paul Laurence Dunbar/Alice`) [container]
    - text: Anchored (`The Complete Poems of Paul Laurence Dunbar/Anchored`) [container]
    - … 402 more

