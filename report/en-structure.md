# en.wikisource: structure and quality survey (WP1a)

Generated 2026-10-31 by `python -I -m wscatalog.survey en` from `enwikisource-20261001-pages-articles1.xml-p1p1500000.bz2, enwikisource-20261001-pages-articles1.xml-p1500001p3000000.bz2, enwikisource-20261001-pages-articles1.xml-p3000001p4500000.bz2, enwikisource-20261001-pages-articles1.xml-p4500001p5244985.bz2` (resolution and quality rules: PLAN.md 5.2 steps 1–1h and 5.4; config `pipeline/config/en.yaml`). Resolution took 45 s on the cached extract.

Input: 645,616 ns0 pages, 134,282 ns0 redirects, 3,810,670 scan pages, 42,655 Index pages, 8,573 pages with the `disambiguation` page prop.

## 1. Works

56,889 works resolved from 630,739 non-excluded ns0 pages; 55,608 enter the catalog after the hard exclusions (section 9). Multi-part works hold 574,605 parts, of which 431,360 are searchable texts.

| structure | works | parts | texts |
|---|---|---|---|
| single | 38,467 | 38,467 | 0 |
| subpages | 17,526 | 521,603 | 388,525 |
| virtual | 755 | 3,078 | 1,977 |
| mixed | 85 | 48,460 | 39,990 |
| linked | 56 | 1,464 | 868 |

| type (step 1e) | works |
|---|---|
| single | 29,806 |
| book | 18,898 |
| collection | 7,511 |
| document | 382 |
| periodical-issue | 257 |
| reference | 35 |

Largest works by parts:

| work | structure | type | parts | texts | missing | rules of the parts |
|---|---|---|---|---|---|---|
| Alumni Oxonienses: the Members of the University of Oxford, 1715-1886 | subpages | reference | 63,225 | 63,216 | 0 | hierarchy 63197, container 28 |
| 1911 Encyclopædia Britannica | subpages | reference | 37,420 | 37,279 | 0 | hierarchy 37387, container 33 |
| Dictionary of National Biography, 1885-1900 | mixed | reference | 28,089 | 28,081 | 2 | hierarchy 28021, container 68 |
| Catholic Encyclopedia (1913) | subpages | reference | 11,629 | 11,592 | 3 | hierarchy 11609, container 20 |
| The New International Encyclopædia | subpages | book | 10,866 | 10,864 | 0 | hierarchy 10863, container 3 |
| An Etymological Dictionary of the German Language | subpages | book | 9,961 | 9,907 | 0 | container 9902, hierarchy 59 |
| Popular Science Monthly | subpages | reference | 8,174 | 7,613 | 15 | container 8163, hierarchy 11 |
| Men-at-the-Bar | subpages | book | 7,448 | 7,447 | 0 | hierarchy 7423, container 25 |
| Nicene and Post-Nicene Fathers: Series II | mixed | reference | 7,099 | 1,389 | 1 | container 7099 |
| Ante-Nicene Fathers | subpages | reference | 5,616 | 1,017 | 0 | container 5610, hierarchy 6 |
| Alumni Oxonienses: the Members of the University of Oxford, 1500-1714 | subpages | reference | 5,317 | 5,312 | 5 | hierarchy 5317 |
| Nicene and Post-Nicene Fathers: Series I | subpages | reference | 5,287 | 869 | 1 | container 5287 |
| A Dictionary of Music and Musicians | subpages | reference | 5,197 | 5,168 | 7 | hierarchy 5131, container 66 |
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
| D | missing root, virtual root (`virtual`) | 755 works, 3,078 parts |
| E | texts inside a subpage collection reached from a disambiguation or versions page | 25,199 parts (23,877 searchable); all searchable subpage texts: 430,048 |
| F | whole-text duplicate subpages (excluded) | 0 pages |
| G | reference works (type `reference`) | 35 works, 212,280 parts; 0 pages excluded by title prefix |
| H | deep nesting (parts at depth ≥ 2, i.e. three levels below the root) | 311 works |
| I | several editions: works with an other-versions template | 3,557 works; 7,250 versions pages excluded |

Tree depth of multi-part works (levels below the root): 1: 16,920, 2: 1,191, 3: 253, 4: 38, 5: 12, 6: 6, 8: 1, 9: 1.

## 4. Parent rules and order sources (steps 1b, 1c)

| parent rule | pages |
|---|---|
| container | 315,060 |
| hierarchy | 259,544 |
| own | 56,134 |
| toc | 1 |

| order source (nodes with ≥ 2 children) | nodes |
|---|---|
| chain | 13,507 |
| toc | 2,975 |
| natural | 1,712 |
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

**Orphan trees** (virtual roots): 755, holding 3,078 parts. Examples: `State of Tennessee Board of Respiratory Care Minutes` (6); `Biographia Dramatica` (1); `The Busy Man's Magazine` (1); `Wellington Evening Post` (1); `Corps de droit ottoman` (1); `Poems by Ingelow, Jean` (2); `Carmina Princetonia` (1); `Sydney Times` (1).

**Oversized pages** (> 300,000 bytes of wikitext including transcluded scan pages): 839. Largest: `Septuagint (Brenton 1879)/Psalms` 1.9 MB; `Matthew Henry's Commentary on the Whole Bible/Volume 4/Jeremiah` 1.8 MB; `Dictionary of Spoken Russian/English-Russian` 1.8 MB; `The Grammar of English Grammars/Part III` 1.8 MB; `Matthew Henry's Commentary on the Whole Bible/Volume 3/Psalms` 1.8 MB; `Copyright Law Revision (House Report No. 94-1476)/Annotated` 1.7 MB; `Septuagint (Brenton 1879)/Jeremias` 1.7 MB; `Virginia Resolutions of 1798/Delaware` 1.7 MB; `Septuagint (Brenton 1879)/Genesis` 1.6 MB; `Septuagint (Brenton 1879)/Jezekiel` 1.6 MB.

**Red links in TOCs** (missing parts): 1,983 multi-part works have red links, 108,198 in total; median share of the TOC 63%. Most missing: `Dictionary of Greek and Roman Biography and Mythology` 15683 of 16977; `Littell's Living Age` 7333 of 9175; `United States Code` 2633 of 3715; `Kansas: A Cyclopedia of State History` 2582 of 2708; `McClure's Magazine` 2087 of 2355; `Transactions and Proceedings of the New Zealand Institute` 1722 of 1823; `Federal Supplement` 1491 of 1501; `United Nations Treaty Series` 1399 of 1486.

## 8. Colon prefixes with ≥ 100 root pages

| prefix | root pages | status |
|---|---|---|
| Aviation Accident Report: | 107 | kept |

## 9. Quality and completeness (PLAN.md 5.4)

**Hard exclusions**: 1,281 works — no-text 574, no-text-pages 272, under-300-bytes 188, index-faulty 141, junk-template 106.

| tier | works | share | texts in them | source |
|---|---|---|---|---|
| validated | 6,986 | 12.6 % | 40,804 | page-levels 6,862, textquality 76, index-progress 48 |
| proofread | 12,434 | 22.4 % | 240,075 | page-levels 9,344, textquality 3,075, index-progress 15 |
| unproofread | 2,716 | 4.9 % | 53,296 | page-levels 2,026, textquality 679, index-progress 11 |
| unknown | 33,472 | 60.2 % | 95,007 | none 33,472 |

889 works are proofread by the ≥ 90 % rule. 1,230 of the 2,716 unproofread works have no page above level 1 (raw OCR).

**Incomplete**: 3,097 works — template 1,769, missing-parts 1,038, missing-pages 247, coverage 43.
Works with missing scan pages inside a transcluded range: 615 (37,266 pages).
Coverage was computed for 417 works that are their Index's book; 87 cover under 95 % of the Index's text pages.
24 works transclude scan pages of files the dump has no Page: pages for (counted as no signal, not as missing).
Wikisource's own translations (labelled, kept): 2,611.

Text-quality template values: `75%` 13,048, `50%` 1,802, `25%` 343, `100%` 245.
Index progress values: `C` 21,572, `V` 8,359, `T` 7,909, `L` 840, `OCR` 283, `MS` 37, `X` 1.
Works excluded as index-faulty, by the tier their page levels would give: proofread 72, unproofread 54, validated 10, none 5.

**Unknown works** (no signal): 33,472 in the catalog after the junk rules; the junk rules removed 294 (under-300-bytes 188, junk-template 106).
Size: median 6,127 bytes, p10 1,356, p90 69,279; multi-part 7,590.

30 random unknown works:

- `The Raja and the Tiger` (single, 13,892 B): There was more than one reason why Bently did not view his appointment as British Resident at Shaitanabad with enthusiasm. The climate was reported to be partic
- `Proclamation 7239` (single, 3,124 B): Although Christopher Columbus' first voyage to the New World took place more than 500 years ago, the momentous changes it brought about still resonate today. Hi
- `Baltimore & Ohio Railroad Company v. Wilson` (subpages, 6,179 B): Messrs. James B. Sheean, William J. Calhoun, Will H. Lyford, and George E. Hamilton for plaintiff in error. Mr. Morse Ives for defendant in error. Mr. Justice H
- `We, the Web Kids` (single, 11,431 B): There is probably no other word that would be as overused in the media discourse as ‘generation’. I once tried to count the ‘generations’ that have been proclai
- `The Lesser Key of Solomon` (subpages, 119,430 B): Title Page Preface Preliminary Invocation Goetia The Initiated Interpretation Of Ceremonial Magic== It is loftily amusing to the student of Magical literature w
- `North Missouri Railroad Company v. Maguire` (subpages, 42,003 B): ERROR to the Supreme Court of Missouri, the case being thus: The North Missouri Railroad Company was incorporated by act of the legislature of Missouri, March 3
- `Presidential Radio Address - 11 March 1995` (single, 7,418 B): Good morning. I ran for President to keep the American dream alive for the forgotten middle class and for all of those struggling to make it in our country, to 
- `Québec Boundaries Extension Act, 1912` (single, 4,737 B): An Act to extend the Boundaries of the Province of Québec [1st April, 1912] Whereas on the thirteenth day of July, one thousand nine hundred and eight, the Hous
- `The Great Impersonation` (subpages, 437,777 B): 300px\|frameless\|center 50px\|frameless\|center Chapter I Chapter II Chapter III Chapter IV Chapter V Chapter VI Chapter VII Chapter VIII Chapter IX Chapter X Chap
- `"Success With Small Fruits"` (single, 3,151 B): AST spring, because my wife was all run down and the children needed out-of-door life, we hired a little place in the country. Among other inconveniences there 
- `The Message (Louis Tracy)` (subpages, 467,196 B): Chapter I. Derelicts Chapter II. How the Message was Delivered Chapter III. Wherein a Strong Man Yields to Circumstances Chapter IV. Figuero Makes a Discovery C
- `Antarctic Treaty` (single, 15,577 B): The Antarctic Treaty== Done at Washington 1 December 1959 Entered into force 23 June 1961 The Governments of Argentina, Australia, Belgium, Chile, the French Re
- `United Nations Security Council Resolution 1555` (single, 2,465 B): Adopted unanimously by the Security Council at its 5014th meeting, on 29 July 2004 The Security Council, Recalling its previous relevant resolutions and stateme
- `United States v. Gettysburg Electric Railway Company` (subpages, 27,495 B): These are two writs of error to the circuit court of the United States for the Eastern district of Pennsylvania. They involve the same questions. By the act of 
- `Wainwright v. Cottle` (subpages, 2,768 B): Motion of respondent for leave to proceed in forma pauperis and certiorari granted. Judgment vacated and case remanded for further consideration in light of Gag
- `Proclamation 5306` (single, 2,853 B): America's economy has been revitalized by the highest level of consumer confidence in nearly twenty years. Our free enterprise system and the high productivity 
- `CMH 60-3: Infantry, Part I: Regular Army` (single, 2,308 B): frameless\|right\|250px ARMY LINEAGE SERIES INFANTRY Part I: Regular Army by John K. Mahon and Romana Danysh OFFICE OF THE CHIEF OF MILITARY HISTORY UNITED STATES
- `Press Briefing by Press Secretary Ari Fleischer, June 29, 2001` (single, 37,633 B): Category:2001 US press briefings The James S. Brady Briefing Room 12:03 P.M. EDT MR. FLEISCHER: Good afternoon. I have a personnel announcement and two addition
- `Proclamation 8128` (single, 2,160 B): Dutch-American Friendship Day, 2007 A Proclamation by the President of the United States of America On Dutch-American Friendship Day, we celebrate our longtime 
- `National Labor Relations Board v. Wyman-Gordon Company` (subpages, 50,519 B): [Syllabus from 760 intentionally omitted] Sol. Gen. Erwin N. Griswold for petitioner. Quentin O. Young, Boston, Mass., for respondent. Mr. Justice FORTAS announ
- `Griggs v. Duke Power Company` (subpages, 29,878 B): Negro employees at respondent's generating plant brought this action, pursuant to Title VII of the Civil Rights Act of 1964, challenging respondent's requiremen
- `Proclamation 6756` (single, 3,633 B): By the President of the United States of America A Proclamation There is a yearning among American people for a sense of community, a sense of belonging, a sens
- `Tribute to David Mitchell` (single, 2,932 B): Ms. WOOLSEY. Mr. Speaker, I rise today to honor David Mitchell of Pt. Reyes Station, California, who recently retired after 30 years as the editor and publisher
- `United Nations Security Council Resolution 1917` (single, 34,162 B): Adopted by the Security Council at its 6290th meeting, on 22 March 2010 The Security Council, Recalling its previous resolutions on Afghanistan, in particular i
- `The Indiscreet Letter` (single, 63,711 B): The Railroad Journey was very long and slow. The Traveling Salesman was rather short and quick. And the Young Electrician who lolled across the car aisle was ne
- `Proclamation 7287` (single, 3,771 B): Each year our Nation is blessed by the service of more than 100 million Americans who take time out of their busy lives to reach out to those in need. Volunteer
- `Dedication of Mount Baden-Powell` (single, 8,067 B): To all the Scouts of the World—Greetings! In obedience to your commands, we, your representatives from distant nations and the far flung frontiers beyond the se
- `Proclamation 157` (single, 14,048 B): , by proclamations of the fifteenth and nineteenth of April, eighteen hundred and sixty-one, the President of the United States, in virtue of the power vested i
- `The Enemy (Walpole)` (single, 25,654 B): T a quarter-past eight in the morning, every working day of the year, summer and winter, little Jack Harding left his little house in Ealing for the Charing Cro
- `Presidential Radio Address - 21 September 1996` (single, 5,075 B): Good morning. I want to talk to you about two important breakthroughs for the health and security of our families that took place in Congress this week, as we w

**Default view** (validated or proofread, complete, not reference): 17,860 works with 112,871 texts; with unknown works shown: 50,656 works (132,790 texts). Works in the default view whose pages are all level 1: 0. Current setting: `show_unknown_by_default = None`.

## 10. Parts table size (WS§5.2)

| parts_cap | works with catalog parts | parts rows | parts table, gzipped | parts_fts, gzipped |
|---|---|---|---|---|
| 500 | 18,170 | 290,989 | 16.80 MB | 2.61 MB |
| 2,000 | 18,219 | 341,413 | 19.46 MB | 3.19 MB |
| ∞ (all, reference works included) | 18,259 | 588,929 | 30.23 MB | 5.47 MB |
| 2,000, compact encoding | 18,219 | 341,413 | 16.88 MB | 3.19 MB |

Rows count multi-part works only, one row per part plus the root row. 37,349 single-page works would add one row each if stored. Sizes are a SQLite file holding only the table (with its index) at gzip level 9. The compact encoding (not in WS§5.1, measured for the cap decision) stores a part's page_title as the suffix after the work root's title where possible, display_title only when it differs from the last title segment, and no per-part quality_pages.

## 11. Named cases (PLAN.md WP1 acceptance, WS§9)

- `The Complete Poetical Works of Percy Bysshe Shelley (ed. Hutchinson, 1914)`: work, subpages, type book, 253 parts (231 texts), 73 missing, order toc-partial, oversized False, quality proofread (page-levels), complete False (template).
- `Ozymandias`: excluded (disambiguation); search finds 4 text(s) with this title: `Weird Tales/Volume 8/Issue 3/Ozymandias` in `Weird Tales`; `The Hundred Best Poems (lyrical) in the English language - second series/Ozymandias` in `The Hundred Best Poems (lyrical) in the English language - second series`; `The Complete Poetical Works of Percy Bysshe Shelley (ed. Hutchinson, 1914)/Ozymandias` in `The Complete Poetical Works of Percy Bysshe Shelley (ed. Hutchinson, 1914)`; `The Examiner (London)/Ozymandias` in `The Examiner (London)`.
- `Ozymandias (Shelley)`: excluded (versions).
- `Frankenstein, or the Modern Prometheus (Revised Edition, 1831)`: work, subpages, type book, 31 parts (1 texts), 0 missing, order toc, oversized False, quality validated (page-levels), complete True.
- `1911 Encyclopædia Britannica`: work, subpages, type reference, 37,420 parts (37,279 texts), 0 missing, order natural, oversized True, quality proofread (page-levels), complete False (template).

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
      - text: Prefatory Chapter (`Eleven years in the Rocky Mountains and a life on the frontier/Prefatory Chapter`) [container]
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
1. `Gesta Romanorum (1871)`: linked, collection, 187 parts, 0 missing, order chain, proofread
    - toc: Gesta Romanorum (1871)
    - volume: Volume I (`Gesta Romanorum Vol. I (1871)`) [container]
      - chapter: Preface (`Gesta Romanorum Vol. I (1871)/Preface`) [hierarchy]
      - chapter: Introduction, Section I (`Gesta Romanorum Vol. I (1871)/Introduction (a)`) [hierarchy]
      - chapter: Introduction, Section II (`Gesta Romanorum Vol. I (1871)/Introduction (b)`) [hierarchy]
      - text: Of Love (`Gesta Romanorum Vol. I (1871)/Of Love`) [hierarchy]
      - text: Of Mercy (`Gesta Romanorum Vol. I (1871)/Of Mercy`) [hierarchy]
      - text: Of Just Judgment (`Gesta Romanorum Vol. I (1871)/Of Just Judgment`) [hierarchy]
      - text: Of Justice (`Gesta Romanorum Vol. I (1871)/Of Justice`) [hierarchy]
      - text: Of Fidelity (`Gesta Romanorum Vol. I (1871)/Of Fidelity`) [hierarchy]
      - text: Of following Reason (`Gesta Romanorum Vol. I (1871)/Of following Reason`) [hierarchy]
      - text: Of the Envy of bad Men towards the good (`Gesta Romanorum Vol. I (1871)/Of the Envy of bad Men towards the good`) [hierarchy]
    - … 176 more
1. `The poems of Emma Lazarus`: linked, book, 1 parts, 0 missing, order single, proofread
    - front: The poems of Emma Lazarus
    - text: Biographical Sketch of Emma Lazarus (`The poems of Emma Lazarus volume 1/Biographical Sketch of Emma Lazarus`) [container]
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
1. `In Vain (Sienkiewicz)`: linked, book, 22 parts, 0 missing, order toc, proofread
    - front: In Vain (Sienkiewicz)
    - chapter: Chapter I (`In Vain/Chapter 1`) [container]
    - chapter: Chapter II (`In Vain/Chapter 2`) [container]
    - chapter: Chapter III (`In Vain/Chapter 3`) [container]
    - chapter: Chapter IV (`In Vain/Chapter 4`) [container]
    - chapter: Chapter V (`In Vain/Chapter 5`) [container]
    - chapter: Chapter VI (`In Vain/Chapter 6`) [container]
    - chapter: Chapter VII (`In Vain/Chapter 7`) [container]
    - chapter: Chapter VIII (`In Vain/Chapter 8`) [container]
    - chapter: Chapter IX (`In Vain/Chapter 9`) [container]
    - chapter: Chapter X (`In Vain/Chapter 10`) [container]
    - chapter: Chapter XI (`In Vain/Chapter 11`) [container]
    - … 11 more
1. `Sonnets attempted in the manner of Contemporary Writers`: linked, collection, 3 parts, 0 missing, order chain, unknown
    - toc: Sonnets attempted in the manner of Contemporary Writers
    - text: Sonnet I (`Pensive at Eve`) [container]
    - text: Sonnet II: To Simplicity (`To Simplicity`) [container]
    - text: Sonnet III: On a Ruined House in a Romantic Country (`On a Ruined House in a Romantic Country`) [container]
1. `The Naturalist on the River Amazons, volume 2`: linked, book, 7 parts, 0 missing, order toc, proofread
    - front: The Naturalist on the River Amazons, volume 2
    - chapter: CHAPTER I. (`The Naturalist on the River Amazons/Volume 2/Chapter 1`) [container]
    - chapter: CHAPTER II. (`The Naturalist on the River Amazons/Volume 2/Chapter 2`) [container]
    - chapter: CHAPTER III. (`The Naturalist on the River Amazons/Volume 2/Chapter 3`) [container]
    - chapter: CHAPTER IV. (`The Naturalist on the River Amazons/Volume 2/Chapter 4`) [container]
    - chapter: CHAPTER V. (`The Naturalist on the River Amazons/Volume 2/Chapter 5`) [container]
    - chapter: CHAPTER VI. (`The Naturalist on the River Amazons/Volume 2/Chapter 6`) [container]
    - chapter: INDEX. (`The Naturalist on the River Amazons/Volume 2/Index`) [container]
1. `Judicial Improvements Act of 1990`: linked, collection, 3 parts, 9 missing, order chain, unknown
    - front: Judicial Improvements Act of 1990
    - text: Title V - Television Program Improvement (`Television Program Improvement Act of 1990`) [container]
    - text: Title VI - Visual Artists Rights (`Visual Artists Rights Act of 1990`) [container]
    - text: Title VII - Architectural Works (`Architectural Works Copyright Protection Act`) [container]
1. `Constitution of the People's Republic of China (1982)`: linked, collection, 5 parts, 0 missing, order chain, unknown
    - front: Constitution of the People's Republic of China (1982)
    - text: Amendment to the Constitution of the People's Republic of China (1988) [container]
    - text: Amendment to the Constitution of the People's Republic of China (1993) [container]
    - text: Amendment to the Constitution of the People's Republic of China (1999) [container]
    - text: Amendment to the Constitution of the People's Republic of China (2004) [container]
    - text: Amendment to the Constitution of the People's Republic of China (2018) [container]
1. `Darbyism and its new Bible`: linked, book, 8 parts, 0 missing, order toc, proofread
    - front: Darbyism and its new Bible
    - text: Apostolic succession, and Church position. (`Darbyism and Its New Bible/Apostolic succession, and Church position`) [container]
    - text: The new Bible (`Darbyism and Its New Bible/The new Bible.`) [container]
    - text: Doctrinal reasons. (`Darbyism and Its New Bible/Doctrinal reasons`) [container]
    - text: Critical reasons. (`Darbyism and Its New Bible/Critical reasons`) [container]
    - text: Justification by faith. (`Darbyism and Its New Bible/Justification by faith`) [container]
    - chapter: Conclusion. (`Darbyism and Its New Bible/Conclusion`) [container]
    - text: The power of the keys. Appendix A. (`Darbyism and Its New Bible/Appendix A`) [container]
    - chapter: Appendix B. (`Darbyism and Its New Bible/Appendix B`) [container]
1. `Poems of Letitia Elizabeth Landon (L. E. L.) in Friendship's Offering, 1828`: linked, book, 4 parts, 0 missing, order chain, validated
    - toc: Poems of Letitia Elizabeth Landon (L. E. L.) in Friendship's Offering, 1828
    - chapter: Title Page (`Poems of Letitia Elizabeth Landon (L. E. L.) in Friendship’s Offering, 1828`) [container]
    - text: The Banner of Five Byzants (`Poems of Letitia Elizabeth Landon in Friendship's Offering, 1828/The Banner of Five Byzants`) [container]
    - text: Venus taking the Bow from a Sleeping Cupid (`Poems of Letitia Elizabeth Landon in Friendship's Offering, 1828/Venus`) [container]
    - text: The First Ball (`Poems of Letitia Elizabeth Landon in Friendship's Offering, 1828/First Ball`) [container]
1. `European Convention for the Protection of Human Rights and Fundamental Freedoms`: linked, collection, 2 parts, 7 missing, order chain, unknown
    - front: European Convention for the Protection of Human Rights and Fundamental Freedoms
    - text: Protocol No. 12 (`Convention for the Protection of Human Rights and Fundamental Freedoms/Protocol No. 12`) [container]
    - text: Protocol No. 13 (`Convention for the Protection of Human Rights and Fundamental Freedoms/Protocol No. 13`) [container]
1. `The Everlasting Gospel`: linked, collection, 1 parts, 0 missing, order single, unknown
    - front: The Everlasting Gospel
    - text: The Everlasting Gospel (complete) [container]
1. `Brigands of the Moon`: linked, periodical-issue, 1 parts, 0 missing, order single, proofread
    - front: Brigands of the Moon
    - chapter: Part 3 (`Astounding Stories of Super Science/Volume 02/Number 02/Brigands of the Moon`) [container]
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
      - text: A letter from Virginia (`Chicago Tribune/1859/10/20/A letter from Virginia`) [container]
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

### D: virtual roots (755)

1. `The Philosophy of Bhagawad-Gita`: virtual, book, 5 parts, 0 missing, order chain, proofread
    - text: T. Subba Row : An Appreciation (`The Philosophy of Bhagawad-Gita/T. Subba Row : An Appreciation`)
    - chapter: First Lecture (`The Philosophy of Bhagawad-Gita/First Lecture`) [hierarchy]
    - chapter: Second Lecture (`The Philosophy of Bhagawad-Gita/Second Lecture`) [hierarchy]
    - chapter: Third Lecture (`The Philosophy of Bhagawad-Gita/Third Lecture`) [hierarchy]
    - chapter: Fourth Lecture (`The Philosophy of Bhagawad-Gita/Fourth Lecture`) [hierarchy]
1. `Siam in 1688`: virtual, book, 3 parts, 0 missing, order scan, proofread
    - chapter: Preface (`Siam in 1688/Preface`)
    - text: The Massacre in Siam (`Siam in 1688/Part 2`) [hierarchy]
    - chapter: Notes (`Siam in 1688/Notes`) [hierarchy]
1. `Short Stories (magazine)`: virtual, book, 24 parts, 0 missing, order natural, unknown
    - text: George, Tete-Beche (`Short Stories (magazine)/George, Tete-Beche`)
    - volume: Plundered Cargo (`Short Stories (magazine)/Plundered Cargo`) [hierarchy]
      - chapter: I. Introducing a Ferris Wheel (`Short Stories (magazine)/Plundered Cargo/Chapter 1`) [container]
      - chapter: II. Fate amid the Cabbages (`Short Stories (magazine)/Plundered Cargo/Chapter 2`) [container]
      - chapter: III. Shanghaied (`Short Stories (magazine)/Plundered Cargo/Chapter 3`) [container]
      - chapter: IV. Quarterdeck Law (`Short Stories (magazine)/Plundered Cargo/Chapter 4`) [container]
      - chapter: V. Dividends of Sin (`Short Stories (magazine)/Plundered Cargo/Chapter 5`) [container]
      - chapter: VI. The Matter of a Boiled Cigar (`Short Stories (magazine)/Plundered Cargo/Chapter 6`) [container]
      - chapter: VII. A Worm Plans to Turn (`Short Stories (magazine)/Plundered Cargo/Chapter 7`) [container]
      - chapter: VIII. Mutiny (`Short Stories (magazine)/Plundered Cargo/Chapter 8`) [container]
      - chapter: IX. Spike Horn Talks Business (`Short Stories (magazine)/Plundered Cargo/Chapter 9`) [container]
      - chapter: X. Where a Water Tram Led (`Short Stories (magazine)/Plundered Cargo/Chapter 10`) [container]
    - … 12 more
1. `Colour studies in Paris`: virtual, book, 2 parts, 0 missing, order scan, proofread
    - text: The Poet of the Bats (1895) (`Colour studies in Paris/The Poet of the Bats`)
    - text: Odilon Redon (`Colour studies in Paris/Odilon Redon`) [hierarchy]
1. `Messenger-Inquirer`: virtual, collection, 1 parts, 0 missing, order single, unknown
    - text: Mental Health Group Of Women's Club To Meet Friday At Lunch (`Messenger-Inquirer/1960/Mental Health Group Of Women's Club To Meet Friday At Lunch`)
1. `Illustrated London News`: virtual, collection, 1 parts, 0 missing, order single, unknown
    - text: Henry Petty-Fitzmaurice, 3rd Marquess of Lansdowne (`Illustrated London News/Henry Petty-Fitzmaurice, 3rd Marquess of Lansdowne`)
1. `Vedic Metre in Its Historical Development`: virtual, book, 1 parts, 0 missing, order single, unproofread
    - chapter: Chapter 1 (`Vedic Metre in Its Historical Development/Chapter 1`)
1. `The strange story book`: virtual, book, 12 parts, 0 missing, order scan, proofread
    - text: The Drowned Buccaneer (`The strange story book/The Drowned Buccaneer`)
    - text: The Perplexity of Zadig (`The strange story book/The Perplexity of Zadig`) [hierarchy]
    - text: The Return of the Dead Wife (`The strange story book/The Return of the Dead Wife`) [hierarchy]
    - text: Young Amazon Snell (`The strange story book/Young Amazon Snell`) [hierarchy]
    - text: The Good Sir James (`The strange story book/The Good Sir James`) [hierarchy]
    - text: The Pets of Aurore Dupin (`The strange story book/The Pets of Aurore Dupin`) [hierarchy]
    - text: The Trials of M. Deschartres (`The strange story book/The Trials of M. Deschartres`) [hierarchy]
    - text: Aurore at Play (`The strange story book/Aurore at Play`) [hierarchy]
    - text: How Aurore learned to Ride (`The strange story book/How Aurore learned to Ride`) [hierarchy]
    - text: The Siege of Rhodes (`The strange story book/The Siege of Rhodes`) [hierarchy]
    - text: The Princess of Babylon (`The strange story book/The Princess of Babylon`) [hierarchy]
    - text: The Boyhood of a Painter (`The strange story book/The Boyhood of a Painter`) [hierarchy]
1. `A Treasury of War Poetry (2nd Series)`: virtual, collection, 3 parts, 0 missing, order natural, unknown
    - text: At the Movies (`A Treasury of War Poetry (2nd Series)/At the Movies`)
    - text: Old War (`A Treasury of War Poetry (2nd Series)/Old War`) [hierarchy]
    - text: St. George's Day (`A Treasury of War Poetry (2nd Series)/St. George's Day`) [hierarchy]
1. `Tip Top Library`: virtual, collection, 1 parts, 0 missing, order single, unknown
    - text: Frank Merriwell in the South Seas (`Tip Top Library/Volume 1/Number 38/Frank Merriwell in the South Seas`)
1. `Immortal Hymns And Their Story`: virtual, collection, 1 parts, 0 missing, order single, unknown
    - text: Jesus, Lover of My Soul (`Immortal Hymns And Their Story/Jesus, Lover of My Soul (unsourced)`)
1. `Chronologies and Calendars`: virtual, book, 16 parts, 0 missing, order chain, proofread
    - chapter: Chapter 1: Introduction (`Chronologies and Calendars/Chapter 1`)
    - chapter: Chapter 2: The Evolution of Eras (`Chronologies and Calendars/Chapter 2`) [hierarchy]
    - chapter: Chapter 3: The Roman and Roman Catholic Reckonings (`Chronologies and Calendars/Chapter 3`) [hierarchy]
    - chapter: Chapter 4: The Eras of the Creation (`Chronologies and Calendars/Chapter 4`) [hierarchy]
    - chapter: Chapter 5: Biblical Chronology (`Chronologies and Calendars/Chapter 5`) [hierarchy]
    - chapter: Chapter 6: Sundry Cycles and Chronological Details (`Chronologies and Calendars/Chapter 6`) [hierarchy]
    - chapter: Chapter 7: The Chronology of England (`Chronologies and Calendars/Chapter 7`) [hierarchy]
    - chapter: Chapter 8: The Chronology of Scotland (`Chronologies and Calendars/Chapter 8`) [hierarchy]
    - chapter: Chapter 9: Upon Great Britain's Calendar (`Chronologies and Calendars/Chapter 9`) [hierarchy]
    - chapter: Chapter 10: Chronology in the Indian Empire (`Chronologies and Calendars/Chapter 10`) [hierarchy]
    - chapter: Chapter 11: The French Chronologies (`Chronologies and Calendars/Chapter 11`) [hierarchy]
    - chapter: Chapter 12: Kindred Sciences.—Astronomy, History and Paleography (`Chronologies and Calendars/Chapter 12`) [hierarchy]
    - … 4 more
1. `Commission Decision 2004/209`: virtual, book, 1 parts, 0 missing, order single, proofread
    - text: EC (`Commission Decision 2004/209/EC`)
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
1. `Queens of Song`: virtual, book, 1 parts, 0 missing, order single, proofread
    - chapter: Chapter V: Rival Queens—Francesca Cuzzoni and Faustina Bordoni (`Queens of Song/Chapter V`)
1. `Chattanooga Daily Times`: virtual, collection, 1 parts, 0 missing, order single, unproofread
    - text: Aviator C.P. Rodgers Almost Instantly Killed. His Biplane Falls Distance of 200 Feet (`Chattanooga Daily Times/1912/Aviator C.P. Rodgers Almost Instantly Killed. His Biplane Falls Distance of 200 Feet`)
1. `Doctor Marigold's Prescriptions, the Extra Christmas Number of All the Year Round`: virtual, book, 1 parts, 0 missing, order single, unproofread
    - chapter: 6. To Be Taken With a Grain of Salt (`Doctor Marigold's Prescriptions, the Extra Christmas Number of All the Year Round/Chapter 6`)
1. `Christianity in China, Tartary and Thibet`: virtual, book, 2 parts, 8 missing, order single, unproofread
    - volume: Volume 1 (`Christianity in China, Tartary and Thibet/Volume 1`)
      - chapter: CHAPTER I. (`Christianity in China, Tartary and Thibet/Volume 1/Chapter 1`) [container]
1. `The Examiner`: virtual, periodical-issue, 2 parts, 0 missing, order scan, unproofread
    - text: England and Wales - Review (`The Examiner/1844/England and Wales - Review`)
    - text: Memoirs of Eminent Englishwomen - Review (`The Examiner/1844/Memoirs of Eminent Englishwomen - Review`) [hierarchy]
1. `Fremantle Herald`: virtual, collection, 1 parts, 0 missing, order single, unknown
    - text: To the Editor of the Herald (`Fremantle Herald/1871/01/14/To the Editor of the Herald`)
1. `The Illustrated English Magazine`: virtual, collection, 2 parts, 0 missing, order natural, unknown
    - text: A Complete Recovery (`The Illustrated English Magazine/A Complete Recovery`)
    - text: Freckles (`The Illustrated English Magazine/Freckles`) [hierarchy]
1. `The mammals of Australia`: virtual, book, 4 parts, 0 missing, order single, validated
    - volume: Title page and preface. p. i-xii. (`The mammals of Australia/Introduction`)
      - chapter: Introduction. p. 1-23. (`The mammals of Australia/Introduction/Introduction`) [container]
      - text: List of the Australian mammals. p. 24-51 . (`The mammals of Australia/Introduction/List`) [container]
      - text: Prospectus (`The mammals of Australia/Introduction/Prospectus`) [container]
1. `Firdausi in Exile and Other Poems`: virtual, book, 1 parts, 0 missing, order single, proofread
    - text: Two Points of View (`Firdausi in Exile and Other Poems/Two Points of View`)
1. `The Flint Journal`: virtual, collection, 1 parts, 0 missing, order single, unknown
    - text: Missionary, Wife Coming To Tuscola (`The Flint Journal/1963/Missionary, Wife Coming To Tuscola`)
1. `Commercial Advertiser`: virtual, collection, 2 parts, 0 missing, order natural, unknown
    - text: A Rather Singular Case Of Death (`Commercial Advertiser/1868/A Rather Singular Case Of Death`)
    - text: Salter (`Commercial Advertiser/1874/09/14/Salter`) [hierarchy]
1. `Tea and Coffee Journal`: virtual, periodical-issue, 1 parts, 0 missing, order single, proofread
    - text: The Green Wave (`Tea and Coffee Journal/Volume 51/August 1926/The Green Wave`)
1. `The book of saints and heroes`: virtual, book, 1 parts, 0 missing, order single, proofread
    - text: The Patron Saint of England (`The book of saints and heroes/The Patron Saint of England`)
1. `Aberdeen Press and Journal`: virtual, collection, 1 parts, 0 missing, order single, unknown
    - text: Obituary: Mr. T. F. Henderson (`Aberdeen Press and Journal/1923/Obituary: Mr. T. F. Henderson`)
1. `Heralds of Grace`: virtual, collection, 1 parts, 0 missing, order single, unknown, excluded no-text
    - text: When Morning Gilds the Sky (`Heralds of Grace/When Morning Gilds the Sky`)
1. `The Purcell Papers`: virtual, book, 4 parts, 2 missing, order single, unproofread
    - volume: Volume 1 (`The Purcell Papers/Volume 1`)
      - text: Memoir of Joseph Sheridan Le Fanu (`The Purcell Papers/Volume 1/Memoir of Joseph Sheridan Le Fanu`) [hierarchy]
      - text: The Ghost and the Bone-Setter (`The Purcell Papers/Volume 1/The Ghost and the Bone-Setter`) [hierarchy]
      - text: The Fortunes of Sir Robert Ardagh (`The Purcell Papers/Volume 1/The Fortunes of Sir Robert Ardagh`) [hierarchy]

### E: subpage texts reached from a disambiguation or versions page (25,199)

1. text “I'll Clout Johnny's Grey Breeks” (`Bonny lass/I'll clout Johnny's grey breeks`), part 2 of `Bonny lass` (book, 4 parts, 4 texts)
1. text “"Honor, not Honors" Motto of Sir Richard Burton.” (`Poems (Coates 1916)/Volume II/"Honor, Not Honors"`), part 172 of `Poems (Coates 1916)` (collection, 347 parts, 343 texts)
1. text “Articles of Religion” (`Book of Common Prayer (1863)/Articles of Religion`), part 0 of `Book of Common Prayer (1863)` (book, 1 parts, 1 texts)
1. text “A Thought” (`Moral Pieces, in Prose and Verse/A Thought`), part 60 of `Moral Pieces, in Prose and Verse` (book, 136 parts, 135 texts)
1. text “Whether my bark went down at sea” (`Poems (Dickinson)/Whether my bark went down at sea`), part 25 of `Poems (Dickinson)` (book, 116 parts, 116 texts)
1. text “A Voyager's Dream of Land” (`Felicia Hemans in The New Monthly Magazine Volume 14 1825/A Voyager's Dream of Land`), part 2 of `Felicia Hemans in The New Monthly Magazine Volume 14 1825` (book, 12 parts, 12 texts)
1. text “Parting” (`Poems (Hazlett-Bevis)/Parting`), part 9 of `Poems (Hazlett-Bevis)` (book, 76 parts, 76 texts)
1. volume “The Sacrifice of Jesus Christ” (`The Complete Ascetical Works of St. Alphonsus/Volume 6/The Sacrifice of Jesus Christ`), part 2 of `The Complete Ascetical Works of St. Alphonsus` (book, 17 parts, 2 texts)
1. text “My Little Dreams” (`The Book of American Negro Poetry/My Little Dreams`), part 73 of `The Book of American Negro Poetry` (book, 124 parts, 121 texts)
1. text “La Fuite de la Lune” (`The Poems of Oscar Wilde/La Fuite de la Lune`), part 36 of `The Poems of Oscar Wilde` (book, 91 parts, 91 texts)
1. text “The Poem of Antara” (`Arabian Poetry for English Readers/The Moallakát/The Poem of Antara`), part 6 of `Arabian Poetry for English Readers` (book, 9 parts, 8 texts)
1. text “The Scottish Blue Bells” (`The Scottish Minstrel (First Series)/The Scottish Blue Bells`), part 12 of `The Scottish Minstrel (First Series)` (book, 42 parts, 42 texts)
1. text “Cavalier” (`Caroling Dusk/Cavalier`), part 229 of `Caroling Dusk` (book, 259 parts, 258 texts)
1. text “Gervais” (`A treasury of war poetry, British and American poems of the world war, 1914-1919/Gervais`), part 297 of `A treasury of war poetry, British and American poems of the world war, 1914-1919` (book, 338 parts, 319 texts)
1. text “The House of Pain” (`Poems (Coates 1916)/Volume II/The House of Pain`), part 185 of `Poems (Coates 1916)` (collection, 347 parts, 343 texts)
1. text “Fragments. 6th Series. The Lamp.” (`Landon in The Literary Gazette 1824/Lamp`), part 16 of `Landon in The Literary Gazette 1824` (book, 99 parts, 99 texts)
1. chapter “Appendix II: The Hymn” (`The Writings of Saint Patrick, the Apostle of Ireland/Version of Mrs. Alexander`), part 10 of `The Writings of Saint Patrick, the Apostle of Ireland` (book, 15 parts, 9 texts)
1. text “The Language and Literature of Bohemia” (`Bohemian Section at the Austrian Exhibition, Earl's Court London 1906/The Language and Literature of Bohemia`), part 9 of `Bohemian Section at the Austrian Exhibition, Earl's Court London 1906` (book, 18 parts, 16 texts)
1. text “An Epitaph” (`Battle-Pieces and Aspects of the War/An Epitaph`), part 58 of `Battle-Pieces and Aspects of the War` (book, 74 parts, 73 texts)
1. text “The Treasure and the Law” (`Puck of Pook's Hill (Rackham)/The Treasure and the Law`), part 23 of `Puck of Pook's Hill (Rackham)` (book, 24 parts, 24 texts)
1. text “My Pictures” (`Poems (Howard)/My Pictures`), part 6 of `Poems (Howard)` (book, 56 parts, 56 texts)
1. text “Robin and Jean's Courtship” (`Five Songs (n.d., Kilmarnock)/Robin and Jean's Courtship`), part 4 of `Five Songs (n.d., Kilmarnock)` (book, 5 parts, 5 texts)
1. text “Lines” (`The Works of Lord Byron (ed. Coleridge, Prothero)/Poetry/Volume 1/Lines Addressed to the Rev. J. T. Becher`), part 48 of `The Works of Lord Byron (ed. Coleridge, Prothero)` (collection, 340 parts, 319 texts)
1. text “Yeats, W. B.: Cradle-Song” (`The Year's at the Spring: An Anthology of Recent Poetry/Yeats, W. B`), part 2 of `The Year's at the Spring: An Anthology of Recent Poetry` (book, 39 parts, 39 texts)
1. text “Ancient Greek Song of Exile” (`The Forest Sanctuary, and Other Poems/Ancient Greek Song of Exile`), part 20 of `The Forest Sanctuary, and Other Poems` (book, 33 parts, 32 texts)
1. text “Sorrow” (`Poems (Taggart)/Sorrow`), part 54 of `Poems (Taggart)` (book, 61 parts, 59 texts)
1. text “The Brook” (`Maud, and other poems/The Brook`), part 2 of `Maud, and other poems` (book, 9 parts, 9 texts)
1. text “Sir Neil and Glengyle, the Highland Chieftains” (`Sir Neil and Glengyle, the Highand chieftains (1)/Sir Neil and Glengyle, the Highand chieftains`), part 1 of `Sir Neil and Glengyle, the Highand chieftains (1)` (book, 2 parts, 2 texts)
1. volume “Volume II.” (`Poems, in Two Volumes (Wordsworth, 1807)/Volume 2`), part 72 of `Poems, in Two Volumes (Wordsworth, 1807)` (collection, 119 parts, 115 texts)
1. text “Socrates” (`Mine and Thine (1904)/Socrates`), part 47 of `Mine and Thine (1904)` (book, 79 parts, 79 texts)

### all searchable subpage texts (chapter titles that the label patterns miss show up here) (430,048)

1. text “Martha Perry Lowe” (`Representative women of New England/Martha Perry Lowe`), part 232 of `Representative women of New England` (reference, 283 parts, 282 texts)
1. text “Ant” (`Domestic Encyclopædia (1802)/Ant`), part 82 of `Domestic Encyclopædia (1802)` (collection, 1,612 parts, 1,554 texts)
1. text “Cleaning Up” (`Rhymes From the Mines, and Other Lines/Cleaning Up`), part 1 of `Rhymes From the Mines, and Other Lines` (book, 10 parts, 10 texts)
1. text “Noble and Plebeian in Alliance.” (`Ninety-three/1.2.3`), part 4 of `Ninety-three` (book, 84 parts, 84 texts)
1. text “The Guardian Angels” (`New Poems (Shorter)/The Guardian Angels`), part 5 of `New Poems (Shorter)` (book, 15 parts, 15 texts)
1. text “Labor Delegate” (`The Masses (periodical)/Volume 1/Number 1/Labor Delegate`), part 13 of `The Masses (periodical)` (collection, 31 parts, 28 texts)
1. text “Richardson, Benjamin Ward” (`Men of the Time, eleventh edition/Richardson, Benjamin Ward`), part 521 of `Men of the Time, eleventh edition` (book, 627 parts, 627 texts)
1. text “Consulates” (`Abbott's Guide to Ottawa and Vicinity/Consulates`), part 47 of `Abbott's Guide to Ottawa and Vicinity` (book, 63 parts, 63 texts)
1. text “Riou, Edward” (`1911 Encyclopædia Britannica/Riou, Edward`), part 29393 of `1911 Encyclopædia Britannica` (reference, 37,420 parts, 37,279 texts)
1. text “Puebla” (`The New Student's Reference Work/Puebla`), part 3367 of `The New Student's Reference Work` (book, 4,695 parts, 4,645 texts)
1. text “Coprolites” (`1911 Encyclopædia Britannica/Coprolites`), part 9255 of `1911 Encyclopædia Britannica` (reference, 37,420 parts, 37,279 texts)
1. text “Heart to Heart” (`Poems (Hazlett-Bevis)/Heart to Heart`), part 3 of `Poems (Hazlett-Bevis)` (book, 76 parts, 76 texts)
1. text “Purdue University” (`The Encyclopedia Americana (1920)/Purdue University`), part 2425 of `The Encyclopedia Americana (1920)` (reference, 3,166 parts, 3,162 texts)
1. text “Dandelion clocks” (`Poems (Probyn)/Dandelion clocks`), part 20 of `Poems (Probyn)` (book, 31 parts, 31 texts)
1. text “Rumania” (`The New Student's Reference Work/Rumania`), part 3521 of `The New Student's Reference Work` (book, 4,695 parts, 4,645 texts)
1. text “The Wetmore Family” (`Acadiensis/Volume 1/Number 4/The Wetmore Family`), part 59 of `Acadiensis` (collection, 114 parts, 90 texts)
1. text “Vere, Aubrey de (1626-1703)” (`Dictionary of National Biography, 1885-1900/Vere, Aubrey de (1626-1703)`), part 26056 of `Dictionary of National Biography, 1885-1900` (reference, 28,089 parts, 28,081 texts)
1. text “Blackwell, John” (`Dictionary of National Biography, 1885-1900/Blackwell, John`), part 2343 of `Dictionary of National Biography, 1885-1900` (reference, 28,089 parts, 28,081 texts)
1. text “The Dust Content of the Air” (`Handbook of Meteorology/The Dust Content of the Air`), part 11 of `Handbook of Meteorology` (book, 23 parts, 21 texts)
1. text “Fuller, Arthur Buckminster” (`The New International Encyclopædia/Fuller, Arthur Buckminster`), part 4882 of `The New International Encyclopædia` (book, 10,866 parts, 10,864 texts)
1. text “Lybbe, Philip Lybbe Powys” (`Alumni Oxonienses: the Members of the University of Oxford, 1715-1886/Lybbe, Philip Lybbe Powys`), part 35600 of `Alumni Oxonienses: the Members of the University of Oxford, 1715-1886` (reference, 63,225 parts, 63,216 texts)
1. text “Keates, William Ernest” (`Alumni Oxonienses: the Members of the University of Oxford, 1715-1886/Keates, William Ernest`), part 31654 of `Alumni Oxonienses: the Members of the University of Oxford, 1715-1886` (reference, 63,225 parts, 63,216 texts)
1. text “Breyer, J., dissenting” (`Baker Botts L.L.P. v. ASARCO LLC/Dissent Breyer`), part 3 of `Baker Botts L.L.P. v. ASARCO LLC` (book, 3 parts, 3 texts)
1. text “HART, Dr. Edgerton H.” (`Who's Who in the Far East/HART, Dr. Edgerton H.`), part 735 of `Who's Who in the Far East` (reference, 2,182 parts, 2,181 texts)
1. text “'Mid the Piteous Heaps of Dead” (`Flower of youth, poems in war time/Mid the Piteous Heaps of Dead`), part 17 of `Flower of youth, poems in war time` (book, 41 parts, 41 texts)
1. text “The White Conduit House” (`Constant swain/The white conduit house`), part 2 of `Constant swain` (book, 6 parts, 6 texts)
1. text “Ambrose, Isaac” (`1911 Encyclopædia Britannica/Ambrose, Isaac`), part 1457 of `1911 Encyclopædia Britannica` (reference, 37,420 parts, 37,279 texts)
1. text “February” (`An Anthology of Czechoslovak Literature/February`), part 121 of `An Anthology of Czechoslovak Literature` (book, 145 parts, 138 texts)
1. text “Urmston, William Brabazon” (`A Naval Biographical Dictionary/Urmston, William Brabazon`), part 4486 of `A Naval Biographical Dictionary` (reference, 4,955 parts, 4,953 texts)
1. text “Burke, Robert O'Hara” (`1911 Encyclopædia Britannica/Burke, Robert O'Hara`), part 6177 of `1911 Encyclopædia Britannica` (reference, 37,420 parts, 37,279 texts)

### C: oversized (399)

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
1. `Hobson-Jobson`: subpages, collection, 27 parts, 0 missing, order chain, unproofread
    - front: Hobson-Jobson
    - text: A (`Hobson-Jobson/A`) [container]
    - text: B (`Hobson-Jobson/B`) [container]
    - chapter: C (`Hobson-Jobson/C`) [container]
    - chapter: D (`Hobson-Jobson/D`) [container]
    - text: E (`Hobson-Jobson/E`) [container]
    - text: F (`Hobson-Jobson/F`) [container]
    - text: G (`Hobson-Jobson/G`) [container]
    - text: H (`Hobson-Jobson/H`) [container]
    - chapter: I (`Hobson-Jobson/I`) [container]
    - text: J (`Hobson-Jobson/J`) [container]
    - text: K (`Hobson-Jobson/K`) [container]
    - … 16 more
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
1. `Constitution of Malaysia`: single, single, 1 parts, 0 missing, order –, unknown
    - text: Constitution of Malaysia
1. `1911 Encyclopædia Britannica`: subpages, reference, 37,420 parts, 0 missing, order natural, proofread
    - toc: 1911 Encyclopædia Britannica
    - chapter: I (`1911 Encyclopædia Britannica/I`) [hierarchy]
    - chapter: V (`1911 Encyclopædia Britannica/V`) [hierarchy]
    - chapter: X (`1911 Encyclopædia Britannica/X`) [hierarchy]
    - chapter: L (`1911 Encyclopædia Britannica/L`) [hierarchy]
    - chapter: C (`1911 Encyclopædia Britannica/C`) [hierarchy]
    - chapter: D (`1911 Encyclopædia Britannica/D`) [hierarchy]
    - chapter: M (`1911 Encyclopædia Britannica/M`) [hierarchy]
    - text: "Address, The" (`1911 Encyclopædia Britannica/"Address, The"`) [hierarchy]
    - text: "Alabama" Arbitration (`1911 Encyclopædia Britannica/"Alabama" Arbitration`) [hierarchy]
    - text: "Challenger" Expedition (`1911 Encyclopædia Britannica/"Challenger" Expedition`) [hierarchy]
    - text: "Constitution of Athens" (`1911 Encyclopædia Britannica/"Constitution of Athens"`) [hierarchy]
    - … 37,409 more
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
1. `Secretary Root's Record:"Marked Severities" in Philippine Warfare`: single, single, 1 parts, 0 missing, order –, unknown
    - text: Secretary Root's Record:"Marked Severities" in Philippine Warfare
1. `Report of the Federation of Malaya Constitutional Commission, 1957`: single, single, 1 parts, 0 missing, order –, unknown
    - text: Report of the Federation of Malaya Constitutional Commission, 1957
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
1. `The Education of Uncle Paul`: single, single, 1 parts, 0 missing, order –, unknown
    - text: The Education of Uncle Paul
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
1. `Mathematical Collections and Translations, in Two Tomes`: subpages, book, 5 parts, 0 missing, order single, proofread, excluded index-faulty
    - front: Mathematical Collections and Translations, in Two Tomes
    - volume: System of the World in Four Dialogues (`Mathematical Collections and Translations, in Two Tomes/System of the World in Four Dialogues`) [container]
      - text: System of the World in Four Dialogues: Dialogue I. (`Mathematical Collections and Translations, in Two Tomes/System of the World in Four Dialogues/Dialogue 1`) [container]
      - text: System of the World in Four Dialogues: Second Dialogue (`Mathematical Collections and Translations, in Two Tomes/System of the World in Four Dialogues/Dialogue 2`) [container]
      - text: System of the World in Four Dialogues: Third Dialogue (`Mathematical Collections and Translations, in Two Tomes/System of the World in Four Dialogues/Dialogue 3`) [container]
      - text: System of the World in Four Dialogues: Fourth Dialogue (`Mathematical Collections and Translations, in Two Tomes/System of the World in Four Dialogues/Dialogue 4`) [container]
1. `Cassell's Illustrated History of England`: subpages, book, 113 parts, 5 missing, order toc, validated
    - toc: Cassell's Illustrated History of England
    - volume: Front Matter and Preface (`Cassell's Illustrated History of England/Volume 1`) [container]
      - chapter: Chapter 1 (`Cassell's Illustrated History of England/Volume 1/Chapter 1`) [container]
      - chapter: Chapter 2 (`Cassell's Illustrated History of England/Volume 1/Chapter 2`) [container]
      - chapter: Chapter 3 (`Cassell's Illustrated History of England/Volume 1/Chapter 3`) [container]
      - chapter: Chapter 4 (`Cassell's Illustrated History of England/Volume 1/Chapter 4`) [container]
      - chapter: Chapter 5 (`Cassell's Illustrated History of England/Volume 1/Chapter 5`) [container]
      - chapter: Chapter 6 (`Cassell's Illustrated History of England/Volume 1/Chapter 6`) [container]
      - chapter: Chapter 7 (`Cassell's Illustrated History of England/Volume 1/Chapter 7`) [container]
      - chapter: Chapter 8 (`Cassell's Illustrated History of England/Volume 1/Chapter 8`) [container]
      - chapter: Chapter 9 (`Cassell's Illustrated History of England/Volume 1/Chapter 9`) [container]
      - chapter: Chapter 10 (`Cassell's Illustrated History of England/Volume 1/Chapter 10`) [container]
    - … 102 more
1. `The Castle of Wolfenbach (1854)`: single, book, 1 parts, 0 missing, order –, unproofread
    - text: The Castle of Wolfenbach (1854)
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
1. `The Gulf of Tonkin, the 1964 Incidents`: subpages, book, 2 parts, 0 missing, order chain, proofread
    - front: The Gulf of Tonkin, the 1964 Incidents
    - chapter: Preface (`The Gulf of Tonkin, the 1964 Incidents/Preface`) [container]
    - text: Statement of Hon. Robert S. McNamara, Secretary of Defense (`The Gulf of Tonkin, the 1964 Incidents/Statement of Hon. Robert S. McNamara, Secretary of Defense`) [container]
1. `Manual for Revolutionary Leaders`: subpages, book, 3 parts, 0 missing, order chain, unproofread
    - front: Manual for Revolutionary Leaders
    - text: Generation of Revolutionaries (`Manual for Revolutionary Leaders/Part 1`) [hierarchy]
    - chapter: Part II: Rise to Leadership (`Manual for Revolutionary Leaders/Part 2`) [hierarchy]
    - chapter: III. Seizure of State Power (`Manual for Revolutionary Leaders/Part 3`) [hierarchy]
1. `Constitution of the State of Florida (1968)`: single, single, 1 parts, 0 missing, order –, validated
    - text: Constitution of the State of Florida (1968)
1. `Parents and Children`: single, single, 1 parts, 0 missing, order –, unknown
    - text: Parents and Children
1. `An Etymological Dictionary of the German Language`: subpages, book, 9,961 parts, 0 missing, order toc-partial, proofread
    - front: An Etymological Dictionary of the German Language
    - volume: A (`An Etymological Dictionary of the German Language/A`) [hierarchy]
      - text: a, -ach (`An Etymological Dictionary of the German Language/-a, -ach`) [container]
      - text: Aal (`An Etymological Dictionary of the German Language/Aal`) [container]
      - text: Aalraupe (`An Etymological Dictionary of the German Language/Aalraupe`) [container]
      - text: Aar (`An Etymological Dictionary of the German Language/Aar`) [container]
      - text: Aas (`An Etymological Dictionary of the German Language/Aas`) [container]
      - text: ab (`An Etymological Dictionary of the German Language/ab`) [container]
      - text: Abend (`An Etymological Dictionary of the German Language/Abend`) [container]
      - text: Abenteuer (`An Etymological Dictionary of the German Language/Abenteuer`) [container]
      - text: aber (`An Etymological Dictionary of the German Language/aber`) [container]
      - text: Aberglaube (`An Etymological Dictionary of the German Language/Aberglaube`) [container]
    - … 9,950 more
1. `Copyright Law Revision (House Report No. 94-1476)`: subpages, book, 2 parts, 0 missing, order natural, validated
    - front: Copyright Law Revision (House Report No. 94-1476)
    - text: Annotated (`Copyright Law Revision (House Report No. 94-1476)/Annotated`) [container]
    - chapter: Errata (`Copyright Law Revision (House Report No. 94-1476)/Errata`) [container]
1. `History of Woman Suffrage`: subpages, book, 248 parts, 0 missing, order toc, proofread
    - toc: History of Woman Suffrage
    - volume: Volume 1 (`History of Woman Suffrage/Volume 1`) [hierarchy]
      - chapter: Preface (`History of Woman Suffrage/Volume 1/Preface`) [hierarchy]
      - chapter: Contents (`History of Woman Suffrage/Volume 1/Contents`) [hierarchy]
      - chapter: Introduction (`History of Woman Suffrage/Volume 1/Introduction`) [hierarchy]
      - chapter: CHAPTER I. (`History of Woman Suffrage/Volume 1/Chapter 1`) [hierarchy]
      - chapter: CHAPTER II. (`History of Woman Suffrage/Volume 1/Chapter 2`) [hierarchy]
      - chapter: CHAPTER III. (`History of Woman Suffrage/Volume 1/Chapter 3`) [hierarchy]
      - chapter: CHAPTER IV. (`History of Woman Suffrage/Volume 1/Chapter 4`) [hierarchy]
      - chapter: CHAPTER V. (`History of Woman Suffrage/Volume 1/Chapter 5`) [hierarchy]
      - chapter: CHAPTER VI. (`History of Woman Suffrage/Volume 1/Chapter 6`) [hierarchy]
      - chapter: CHAPTER VII. (`History of Woman Suffrage/Volume 1/Chapter 7`) [hierarchy]
    - … 237 more
1. `The Catholic Dogma: Extra Ecclesiam Nullus Omnino Salvatur`: subpages, collection, 7 parts, 0 missing, order chain, unknown
    - front: The Catholic Dogma: Extra Ecclesiam Nullus Omnino Salvatur
    - chapter: Introduction (`The Catholic Dogma: Extra Ecclesiam Nullus Omnino Salvatur/Chapter I`) [container]
    - text: The Infallible and Only True Guide to Heaven (`The Catholic Dogma: Extra Ecclesiam Nullus Omnino Salvatur/Chapter II`) [container]
    - text: The Great Revolt Against Christ (`The Catholic Dogma: Extra Ecclesiam Nullus Omnino Salvatur/Chapter III`) [container]
    - text: Bishop Coxe's Dishonesty (`The Catholic Dogma: Extra Ecclesiam Nullus Omnino Salvatur/Chapter IV`) [container]
    - volume: Introduction: Refutation of the False Assertions of Reverends S. O. Cronin and Young (`The Catholic Dogma: Extra Ecclesiam Nullus Omnino Salvatur/Chapter V`) [container]
      - chapter: Part I. There is No Salvation Out of the Roman Catholic Church for those who die without being united to her (`The Catholic Dogma: Extra Ecclesiam Nullus Omnino Salvatur/Chapter V/Part 1`) [container]
      - text: Of Those Heretics who are not guilty of the sin of Heresy (`The Catholic Dogma: Extra Ecclesiam Nullus Omnino Salvatur/Chapter V/Part 2`) [container]
1. `Montaigne's Essays`: subpages, collection, 35 parts, 78 missing, order toc, unproofread
    - toc: Montaigne's Essays
    - text: The Author to the Reader (`Montaigne's Essays/The Author to the Reader`) [container]
    - text: The First Chapter: By diverse meanes men come unto a like end (`Montaigne's Essays/Book I/Chapter I`) [container]
    - text: The Second Chapter: Of Sadnesse or Sorrowe (`Montaigne's Essays/Book I/Chapter II`) [container]
    - text: The Third Chapter: Our Affections are transported beyond our selves (`Montaigne's Essays/Book I/Chapter III`) [container]
    - text: The Fourth Chapter: How the Soule dischargeth her Passions upon false objects, when the true faile it (`Montaigne's Essays/Book I/Chapter IV`) [container]
    - text: The Fifth Chapter: Whether the Captaine of a Place Besieged ought to sallie forth to Parlie (`Montaigne's Essays/Book I/Chapter V`) [container]
    - text: The Sixth Chapter: That the Houre of Parlies is dangerous (`Montaigne's Essays/Book I/Chapter VI`) [container]
    - text: The Seventh Chapter: That our Intention judgeth our Actions (`Montaigne's Essays/Book I/Chapter VII`) [container]
    - text: The Eighth Chapter: Of Idlenesse (`Montaigne's Essays/Book I/Chapter VIII`) [container]
    - text: The Ninth Chapter: Of Lyers (`Montaigne's Essays/Book I/Chapter IX`) [container]
    - text: The Tenth Chapter: Of Readie or Slow Speech (`Montaigne's Essays/Book I/Chapter X`) [container]
    - … 24 more

### G: reference works (35)

1. `1911 Encyclopædia Britannica`: subpages, reference, 37,420 parts, 0 missing, order natural, proofread
    - toc: 1911 Encyclopædia Britannica
    - chapter: I (`1911 Encyclopædia Britannica/I`) [hierarchy]
    - chapter: V (`1911 Encyclopædia Britannica/V`) [hierarchy]
    - chapter: X (`1911 Encyclopædia Britannica/X`) [hierarchy]
    - chapter: L (`1911 Encyclopædia Britannica/L`) [hierarchy]
    - chapter: C (`1911 Encyclopædia Britannica/C`) [hierarchy]
    - chapter: D (`1911 Encyclopædia Britannica/D`) [hierarchy]
    - chapter: M (`1911 Encyclopædia Britannica/M`) [hierarchy]
    - text: "Address, The" (`1911 Encyclopædia Britannica/"Address, The"`) [hierarchy]
    - text: "Alabama" Arbitration (`1911 Encyclopædia Britannica/"Alabama" Arbitration`) [hierarchy]
    - text: "Challenger" Expedition (`1911 Encyclopædia Britannica/"Challenger" Expedition`) [hierarchy]
    - text: "Constitution of Athens" (`1911 Encyclopædia Britannica/"Constitution of Athens"`) [hierarchy]
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
1. `Thom's Irish Who's Who`: subpages, reference, 2,378 parts, 0 missing, order natural, unknown
    - toc: Thom's Irish Who's Who
    - text: Names: I (`Thom's Irish Who's Who/I`) [container]
    - text: Names: V (`Thom's Irish Who's Who/V`) [container]
    - text: Names: L (`Thom's Irish Who's Who/L`) [container]
    - text: Names: C (`Thom's Irish Who's Who/C`) [container]
    - text: Names: D (`Thom's Irish Who's Who/D`) [container]
    - text: Names: M (`Thom's Irish Who's Who/M`) [container]
    - text: Names: A (`Thom's Irish Who's Who/A`) [container]
    - text: Abercorn, Duke of (`Thom's Irish Who's Who/Abercorn, Duke of`) [hierarchy]
    - text: Abraham, Lieut.-Col. James Johnston (`Thom's Irish Who's Who/Abraham, Lieut.-Col. James Johnston`) [hierarchy]
    - text: Acheson, Hon. Patrick Charles George Cavendish (`Thom's Irish Who's Who/Acheson, Hon. Patrick Charles George Cavendish`) [hierarchy]
    - text: Acheson, Viscount, Archibald Alexander John Stanley Acheson (`Thom's Irish Who's Who/Acheson, Viscount, Archibald Alexander John Stanley Acheson`) [hierarchy]
    - … 2,367 more
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
1. `Dictionary of Indian Biography`: subpages, reference, 663 parts, 0 missing, order natural, proofread
    - front: Dictionary of Indian Biography
    - chapter: I (`Dictionary of Indian Biography/I`) [container]
    - chapter: V (`Dictionary of Indian Biography/V`) [container]
    - chapter: X (`Dictionary of Indian Biography/X`) [container]
    - chapter: L (`Dictionary of Indian Biography/L`) [container]
    - chapter: C (`Dictionary of Indian Biography/C`) [container]
    - chapter: D (`Dictionary of Indian Biography/D`) [container]
    - chapter: M (`Dictionary of Indian Biography/M`) [container]
    - text: A (`Dictionary of Indian Biography/A`) [container]
    - text: Abadie, Henry Richard (`Dictionary of Indian Biography/Abadie, Henry Richard`) [hierarchy]
    - text: Abbott, Augustus (`Dictionary of Indian Biography/Abbott, Augustus`) [hierarchy]
    - text: Abbott, H. Edward Stacey (`Dictionary of Indian Biography/Abbott, H. Edward Stacey`) [hierarchy]
    - … 652 more
1. `Handbook of the Swatow Vernacular`: subpages, reference, 32 parts, 0 missing, order chain, unproofread
    - front: Handbook of the Swatow Vernacular
    - text: Introductory (`Handbook of the Swatow Vernacular/Introductory`) [container]
    - text: A List of Introductory Verbs (`Handbook of the Swatow Vernacular/A List of Introductory Verbs`) [container]
    - text: Exercises (`Handbook of the Swatow Vernacular/Exercises (Lesson V)`) [container]
    - text: A List of Introductory Adjectives (`Handbook of the Swatow Vernacular/A List of Introductory Adjectives`) [container]
    - text: Exercises (`Handbook of the Swatow Vernacular/Exercises (Lesson VII)`) [container]
    - text: Numeral (`Handbook of the Swatow Vernacular/Numeral`) [container]
    - text: Tones, Hyphens (`Handbook of the Swatow Vernacular/Tones, Hyphens`) [container]
    - text: Grammar (`Handbook of the Swatow Vernacular/Grammar`) [container]
    - text: Time generally (`Handbook of the Swatow Vernacular/Time generally`) [container]
    - text: A Building &c. (`Handbook of the Swatow Vernacular/A Building &c.`) [container]
    - text: Human Body &c. (`Handbook of the Swatow Vernacular/Human Body &c.`) [container]
    - … 21 more
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
1. `Alumni Oxonienses: the Members of the University of Oxford, 1500-1714`: subpages, reference, 5,317 parts, 5 missing, order natural, unknown
    - front: Alumni Oxonienses: the Members of the University of Oxford, 1500-1714
    - chapter: L (`Alumni Oxonienses: the Members of the University of Oxford, 1500-1714/L`) [hierarchy]
    - chapter: C (`Alumni Oxonienses: the Members of the University of Oxford, 1500-1714/C`) [hierarchy]
    - chapter: D (`Alumni Oxonienses: the Members of the University of Oxford, 1500-1714/D`) [hierarchy]
    - chapter: M (`Alumni Oxonienses: the Members of the University of Oxford, 1500-1714/M`) [hierarchy]
    - text: A (`Alumni Oxonienses: the Members of the University of Oxford, 1500-1714/A`) [hierarchy]
    - text: Abannan (`Alumni Oxonienses: the Members of the University of Oxford, 1500-1714/Abannan`) [hierarchy]
    - text: Abba, Francis (`Alumni Oxonienses: the Members of the University of Oxford, 1500-1714/Abba, Francis`) [hierarchy]
    - text: Abberley (`Alumni Oxonienses: the Members of the University of Oxford, 1500-1714/Abberley`) [hierarchy]
    - text: Abbey, George (`Alumni Oxonienses: the Members of the University of Oxford, 1500-1714/Abbey, George`) [hierarchy]
    - text: Abbinton, John (`Alumni Oxonienses: the Members of the University of Oxford, 1500-1714/Abbinton, John`) [hierarchy]
    - text: Abbot, Henry (1) (`Alumni Oxonienses: the Members of the University of Oxford, 1500-1714/Abbot, Henry (1)`) [hierarchy]
    - … 5,306 more
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
1. `Foclóir Gaeḋilge agus Béarla`: virtual, reference, 5 parts, 0 missing, order chain, proofread
    - text: Council's Preface (`Foclóir Gaeḋilge agus Béarla/Council's Preface`)
    - text: Editor's Preface (`Foclóir Gaeḋilge agus Béarla/Editor's Preface`) [hierarchy]
    - text: Abbreviations (`Foclóir Gaeḋilge agus Béarla/Abbreviations`) [hierarchy]
    - text: A (`Foclóir Gaeḋilge agus Béarla/A`) [hierarchy]
    - text: B (`Foclóir Gaeḋilge agus Béarla/B`) [hierarchy]
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
1. `A Dictionary of Slang, Jargon & Cant`: subpages, reference, 4 parts, 11 missing, order single, proofread
    - front: A Dictionary of Slang, Jargon & Cant
    - volume: Table of Contents (`A Dictionary of Slang, Jargon & Cant/TOC`) [container]
      - chapter: Preface (`A Dictionary of Slang, Jargon & Cant/Preface`) [container]
      - text: A Brief History of English Slang (`A Dictionary of Slang, Jargon & Cant/History`) [container]
      - text: Contributors (`A Dictionary of Slang, Jargon & Cant/Contributors`) [container]
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
1. `Dictionary of the Foochow Dialect`: subpages, reference, 3 parts, 17 missing, order toc, unproofread
    - front: Dictionary of the Foochow Dialect
    - text: A (`Dictionary of the Foochow Dialect/A`) [container]
    - text: List of the 214 radicals (`Dictionary of the Foochow Dialect/List of the 214 radicals`) [container]
    - chapter: Index of Characters (`Dictionary of the Foochow Dialect/Index of Characters`) [container]
1. `A Dictionary of Music and Musicians`: subpages, reference, 5,197 parts, 7 missing, order natural, proofread
    - toc: A Dictionary of Music and Musicians
    - chapter: C (`A Dictionary of Music and Musicians/C`) [hierarchy]
    - chapter: D (`A Dictionary of Music and Musicians/D`) [hierarchy]
    - text: A (`A Dictionary of Music and Musicians/A`) [hierarchy]
    - text: A battuta (`A Dictionary of Music and Musicians/A battuta`) [hierarchy]
    - text: A Capella (`A Dictionary of Music and Musicians/A Capella`) [hierarchy]
    - text: A Capriccio (`A Dictionary of Music and Musicians/A Capriccio`) [hierarchy]
    - text: A Deux Mains (`A Dictionary of Music and Musicians/A Deux Mains`) [hierarchy]
    - text: A Due (`A Dictionary of Music and Musicians/A Due`) [hierarchy]
    - text: A Piacere (`A Dictionary of Music and Musicians/A Piacere`) [hierarchy]
    - text: A Prima Vista (`A Dictionary of Music and Musicians/A Prima Vista`) [hierarchy]
    - text: A Punta d'Arco (`A Dictionary of Music and Musicians/A Punta d'Arco`) [hierarchy]
    - … 5,186 more
1. `Alumni Oxonienses: the Members of the University of Oxford, 1715-1886`: subpages, reference, 63,225 parts, 0 missing, order natural, unknown
    - toc: Alumni Oxonienses: the Members of the University of Oxford, 1715-1886
    - chapter: I (`Alumni Oxonienses: the Members of the University of Oxford, 1715-1886/I`) [container]
    - chapter: V (`Alumni Oxonienses: the Members of the University of Oxford, 1715-1886/V`) [container]
    - chapter: L (`Alumni Oxonienses: the Members of the University of Oxford, 1715-1886/L`) [container]
    - chapter: C (`Alumni Oxonienses: the Members of the University of Oxford, 1715-1886/C`) [container]
    - chapter: D (`Alumni Oxonienses: the Members of the University of Oxford, 1715-1886/D`) [container]
    - chapter: M (`Alumni Oxonienses: the Members of the University of Oxford, 1715-1886/M`) [container]
    - text: A (`Alumni Oxonienses: the Members of the University of Oxford, 1715-1886/A`) [container]
    - text: A'Beckett, Gilbert Arthur (`Alumni Oxonienses: the Members of the University of Oxford, 1715-1886/A'Beckett, Gilbert Arthur`) [hierarchy]
    - text: A'Beckett, Thomas (`Alumni Oxonienses: the Members of the University of Oxford, 1715-1886/A'Beckett, Thomas`) [hierarchy]
    - text: A'Court, Peirce (`Alumni Oxonienses: the Members of the University of Oxford, 1715-1886/A'Court, Peirce`) [hierarchy]
    - text: Abbay, Richard (`Alumni Oxonienses: the Members of the University of Oxford, 1715-1886/Abbay, Richard`) [hierarchy]
    - … 63,214 more
1. `Easton's Bible Dictionary (1897)`: subpages, reference, 2,050 parts, 0 missing, order natural, proofread
    - toc: Easton's Bible Dictionary (1897)
    - chapter: I (`Easton's Bible Dictionary (1897)/I`) [container]
    - chapter: V (`Easton's Bible Dictionary (1897)/V`) [container]
    - chapter: X (`Easton's Bible Dictionary (1897)/X`) [container]
    - chapter: L (`Easton's Bible Dictionary (1897)/L`) [container]
    - chapter: C (`Easton's Bible Dictionary (1897)/C`) [container]
    - chapter: D (`Easton's Bible Dictionary (1897)/D`) [container]
    - chapter: M (`Easton's Bible Dictionary (1897)/M`) [container]
    - text: A (`Easton's Bible Dictionary (1897)/A`) [container]
    - text: A (letter) (`Easton's Bible Dictionary (1897)/A (letter)`) [hierarchy]
    - text: Aaron (`Easton's Bible Dictionary (1897)/Aaron`) [hierarchy]
    - text: Aaronites (`Easton's Bible Dictionary (1897)/Aaronites`) [hierarchy]
    - … 2,039 more

### H: deep nesting (311)

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
1. `Administrative Instruction ST`: virtual, collection, 7 parts, 0 missing, order natural, proofread
    - volume: 189 (`Administrative Instruction ST/AI/189`)
      - text: Rev.1 (`Administrative Instruction ST/AI/189/Add.9/Rev.1`) [container]
      - volume: Rev.2 (`Administrative Instruction ST/AI/189/Add.9/Rev.2`) [container]
        - text: Add.2 (`Administrative Instruction ST/AI/189/Add.9/Rev.2/Add.2`) [container]
        - text: Add.1 (`Administrative Instruction ST/AI/189/Add.9/Rev.2/Add.1`) [hierarchy]
      - chapter: index of addenda and revisions (`Administrative Instruction ST/AI/189/Addenda`) [hierarchy]
    - text: Administrative Instruction ST/AI/189/Add.9/Rev.2 (`Administrative Instruction ST/AI/2001/5`) [hierarchy]
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
      - text: The Book of the Dead: A Hymn to the Setting Sun (`Egyptian Literature/The Book of the Dead/A Hymn to the Setting Sun`) [container]
      - text: The Book of the Dead: Hymn and Litany to Osiris (`Egyptian Literature/The Book of the Dead/Hymn and Litany to Osiris`) [container]
      - text: The Book of the Dead: Hymn to Ra (`Egyptian Literature/The Book of the Dead/Hymn to Ra`) [container]
      - text: Hymn to the Setting Sun (`Egyptian Literature/The Book of the Dead/Hymn to the Setting Sun (1)`) [container]
      - text: Hymn to the Setting Sun (`Egyptian Literature/The Book of the Dead/Hymn to the Setting Sun (2)`) [container]
      - text: The Book of the Dead: The Chaplet of Victory (`Egyptian Literature/The Book of the Dead/The Chaplet of Victory`) [container]
      - text: The Book of the Dead: The Victory Over Enemies (`Egyptian Literature/The Book of the Dead/The Victory Over Enemies`) [container]
      - text: The Book of the Dead: Of Giving a Mouth to the Overseer (`Egyptian Literature/The Book of the Dead/Of Giving a Mouth to the Overseer`) [container]
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
1. `Beeton's Christmas Annual`: virtual, book, 18 parts, 0 missing, order scan, proofread
    - chapter: 1887 (`Beeton's Christmas Annual/1887`)
    - volume: A Study in Scarlet (`Beeton's Christmas Annual/A Study in Scarlet`) [hierarchy]
      - volume: Part I. (`Beeton's Christmas Annual/A Study in Scarlet/Part 1`) [container]
        - chapter: Chapter 1 (`Beeton's Christmas Annual/A Study in Scarlet/Part 1/Chapter 1`) [container]
        - chapter: Chapter 2 (`Beeton's Christmas Annual/A Study in Scarlet/Part 1/Chapter 2`) [container]
        - chapter: Chapter 3 (`Beeton's Christmas Annual/A Study in Scarlet/Part 1/Chapter 3`) [container]
        - chapter: Chapter 4 (`Beeton's Christmas Annual/A Study in Scarlet/Part 1/Chapter 4`) [container]
        - chapter: Chapter 5 (`Beeton's Christmas Annual/A Study in Scarlet/Part 1/Chapter 5`) [container]
        - chapter: Chapter 6 (`Beeton's Christmas Annual/A Study in Scarlet/Part 1/Chapter 6`) [container]
        - chapter: Chapter 7 (`Beeton's Christmas Annual/A Study in Scarlet/Part 1/Chapter 7`) [container]
      - volume: Part II. (`Beeton's Christmas Annual/A Study in Scarlet/Part 2`) [container]
        - chapter: Chapter 1 (`Beeton's Christmas Annual/A Study in Scarlet/Part 2/Chapter 1`) [container]
    - … 6 more
1. `The Bulletin`: subpages, collection, 11 parts, 158 missing, order chain, proofread
    - front: The Bulletin
    - volume: Volume 9 (`The Bulletin/Volume 9`) [hierarchy]
      - volume: Number 462 (`The Bulletin/Volume 9/Number 462`) [hierarchy]
        - text: Old Pardon, the Son of Reprieve (`The Bulletin/Volume 9/Number 462/Old Pardon, the Son of Reprieve`) [hierarchy]
        - text: Nasty Remarks (`The Bulletin/Volume 9/Number 462/Nasty Remarks`) [hierarchy]
        - text: Our Chinese Ancestor (`The Bulletin/Volume 9/Number 462/Our Chinese Ancestor`) [hierarchy]
    - volume: Volume 10 (`The Bulletin/Volume 10`) [hierarchy]
      - volume: Number 514 (`The Bulletin/Volume 10/Number 514`) [hierarchy]
        - text: Branded (`The Bulletin/Volume 10/Number 514/Branded`) [hierarchy]
        - text: The Priest's Dinner (`The Bulletin/Volume 10/Number 514/The Priest's Dinner`) [hierarchy]
        - text: An Idyll of Dandaloo (`The Bulletin/Volume 10/Number 514/An Idyll of Dandaloo`) [hierarchy]
        - text: A Very Pathetic Incident (`The Bulletin/Volume 10/Number 514/A Very Pathetic Incident`) [hierarchy]

### I: works with other editions (3,557)

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
    - text: A LETTER FROM A GIRL TO HER OWN OLD AGE (`Preludes (Meynell)/A Letter from a Girl to her own Old Age`) [container]
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
1. `The Book of Navy Songs`: virtual, collection, 1 parts, 0 missing, order single, unknown
    - text: The Maid of Amsterdam (`The Book of Navy Songs/The Maid of Amsterdam`)

