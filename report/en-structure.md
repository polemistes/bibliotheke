# en.wikisource: structure and quality survey (WP1a)

Generated 2026-11-01 by `python -I -m wscatalog.survey en` from `enwikisource-20261001-pages-articles1.xml-p1p1500000.bz2, enwikisource-20261001-pages-articles1.xml-p1500001p3000000.bz2, enwikisource-20261001-pages-articles1.xml-p3000001p4500000.bz2, enwikisource-20261001-pages-articles1.xml-p4500001p5244985.bz2` (resolution and quality rules: PLAN.md 5.2 steps 1–1h and 5.4; config `pipeline/config/en.yaml`). Resolution took 47 s on the cached extract.

Input: 645,616 ns0 pages, 134,282 ns0 redirects, 3,810,670 scan pages, 42,655 Index pages, 8,573 pages with the `disambiguation` page prop.

## 1. Works

57,148 works resolved from 630,739 non-excluded ns0 pages; 55,994 enter the catalog after the hard exclusions (section 9). Multi-part works hold 574,329 parts, of which 430,977 are searchable texts.

| structure | works | parts | texts |
|---|---|---|---|
| single | 38,741 | 38,741 | 0 |
| subpages | 17,527 | 521,616 | 388,226 |
| virtual | 738 | 2,785 | 1,891 |
| mixed | 85 | 48,460 | 39,990 |
| linked | 57 | 1,468 | 870 |

| type (step 1e) | works |
|---|---|
| single | 29,899 |
| book | 19,069 |
| collection | 7,511 |
| document | 383 |
| periodical-issue | 256 |
| reference | 30 |

Largest works by parts:

| work | structure | type | parts | texts | missing | rules of the parts |
|---|---|---|---|---|---|---|
| Alumni Oxonienses: the Members of the University of Oxford, 1715-1886 | subpages | reference | 63,225 | 63,216 | 0 | hierarchy 63197, container 28 |
| 1911 Encyclopædia Britannica | subpages | reference | 37,420 | 37,278 | 0 | hierarchy 37387, container 33 |
| Dictionary of National Biography, 1885-1900 | mixed | reference | 28,089 | 28,081 | 2 | hierarchy 28021, container 68 |
| Catholic Encyclopedia (1913) | subpages | reference | 11,629 | 11,592 | 3 | hierarchy 11609, container 20 |
| The New International Encyclopædia | subpages | book | 10,866 | 10,864 | 0 | hierarchy 10863, container 3 |
| An Etymological Dictionary of the German Language | subpages | book | 9,961 | 9,907 | 0 | container 9902, hierarchy 59 |
| Popular Science Monthly | subpages | collection | 8,174 | 7,613 | 15 | container 8163, hierarchy 11 |
| Men-at-the-Bar | subpages | book | 7,448 | 7,447 | 0 | hierarchy 7423, container 25 |
| Nicene and Post-Nicene Fathers: Series II | mixed | book | 7,099 | 1,389 | 1 | container 7099 |
| Ante-Nicene Fathers | subpages | book | 5,616 | 1,017 | 0 | container 5610, hierarchy 6 |
| Alumni Oxonienses: the Members of the University of Oxford, 1500-1714 | subpages | reference | 5,317 | 5,312 | 5 | hierarchy 5317 |
| Nicene and Post-Nicene Fathers: Series I | subpages | book | 5,287 | 723 | 1 | container 5287 |
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
| A | collection of root pages (`structure = linked`) | 57 works, 1,468 parts |
| B | mixed subpages and root pages (`mixed`) | 85 works, 48,460 parts |
| C | work with an oversized page (> oversized_bytes, transcluded scan pages included) | 399 works |
| D | missing root, virtual root (`virtual`) | 738 works, 2,785 parts |
| E | texts inside a subpage collection reached from a disambiguation or versions page | 25,313 parts (23,932 searchable); all searchable subpage texts: 429,663 |
| F | whole-text duplicate subpages (excluded) | 0 pages |
| G | reference works (type `reference`) | 30 works, 183,922 parts; 0 pages excluded by title prefix |
| H | deep nesting (parts at depth ≥ 2, i.e. three levels below the root) | 311 works |
| I | several editions: works with an other-versions template | 3,557 works; 7,250 versions pages excluded |

Tree depth of multi-part works (levels below the root): 1: 16,906, 2: 1,190, 3: 253, 4: 38, 5: 12, 6: 6, 8: 1, 9: 1.

## 4. Parent rules and order sources (steps 1b, 1c)

| parent rule | pages |
|---|---|
| container | 315,060 |
| hierarchy | 259,264 |
| own | 56,410 |
| toc | 5 |

| order source (nodes with ≥ 2 children) | nodes |
|---|---|
| chain | 13,499 |
| toc | 2,976 |
| natural | 1,623 |
| scan | 1,175 |
| toc-partial | 357 |
| scan-volumes | 142 |

`toc-partial` (implementation choice, see section 13): the TOC lists at least half of the children, which keep its order; the rest follow in natural order. 689 `natural` nodes have a TOC that lists fewer than half.

Root pages attached by their header's container link: 279; most frequent containers: `Notebook` 175; `Constitution of the Republic of China (1947)` 7; `Brazilian tales` 6; `Hague Conventions of 1899 and 1907` 5; `Constitution of the People's Republic of China (1982)` 5; `Sonnets attempted in the manner of Contemporary Writers` 3; `Itar-Tass Russian News Agency v. Russian Kurier, Inc.` 3; `Labour: The Divine Command` 3; `Public Law 110-343` 3; `Judicial Improvements Act of 1990` 3.

Pages attached by TOC membership (rule 3): 5; most frequent TOC pages: `The Message and Ministrations of Dewan Bahadur R. Venkata Ratnam` 4; `Socrates (Voltaire)` 1.

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

**Orphan trees** (virtual roots): 738, holding 2,785 parts. Examples: `Liberator` (1); `The Sunday Times` (1); `Niagara Gazette` (1); `Electronics Technician` (12); `Sunday Star` (5); `The House of Ptolemy` (3); `People's Favorite Magazine` (1); `Ex parte in the Matter of Wells` (2).

**Oversized pages** (> 300,000 bytes of wikitext including transcluded scan pages): 839. Largest: `Septuagint (Brenton 1879)/Psalms` 1.9 MB; `Matthew Henry's Commentary on the Whole Bible/Volume 4/Jeremiah` 1.8 MB; `Dictionary of Spoken Russian/English-Russian` 1.8 MB; `The Grammar of English Grammars/Part III` 1.8 MB; `Matthew Henry's Commentary on the Whole Bible/Volume 3/Psalms` 1.8 MB; `Copyright Law Revision (House Report No. 94-1476)/Annotated` 1.7 MB; `Septuagint (Brenton 1879)/Jeremias` 1.7 MB; `Virginia Resolutions of 1798/Delaware` 1.7 MB; `Septuagint (Brenton 1879)/Genesis` 1.6 MB; `Septuagint (Brenton 1879)/Jezekiel` 1.6 MB.

**Red links in TOCs** (missing parts): 1,984 multi-part works have red links, 108,205 in total; median share of the TOC 63%. Most missing: `Dictionary of Greek and Roman Biography and Mythology` 15683 of 16977; `Littell's Living Age` 7333 of 9175; `United States Code` 2633 of 3715; `Kansas: A Cyclopedia of State History` 2582 of 2708; `McClure's Magazine` 2087 of 2355; `Transactions and Proceedings of the New Zealand Institute` 1722 of 1823; `Federal Supplement` 1491 of 1501; `United Nations Treaty Series` 1399 of 1486.

## 8. Colon prefixes with ≥ 100 root pages

| prefix | root pages | status |
|---|---|---|
| Aviation Accident Report: | 107 | kept |

## 9. Quality and completeness (PLAN.md 5.4)

**Hard exclusions**: 1,154 works — no-text 574, no-text-pages 272, under-300-bytes 188, junk-template 106, index-faulty 14.

| tier | works | share | texts in them | source |
|---|---|---|---|---|
| validated | 6,997 | 12.5 % | 41,456 | page-levels 6,873, textquality 76, index-progress 48 |
| proofread | 12,616 | 22.5 % | 240,541 | page-levels 9,527, textquality 3,075, index-progress 14 |
| unproofread | 2,817 | 5.0 % | 53,563 | page-levels 2,126, textquality 679, index-progress 12 |
| unknown | 33,564 | 59.9 % | 95,005 | none 33,564 |

908 works are proofread by the ≥ 90 % rule. 1,235 of the 2,817 unproofread works have no page above level 1 (raw OCR).

**Incomplete**: 3,134 works — template 1,796, missing-parts 1,056, missing-pages 255, coverage 27.
Works with missing scan pages inside a transcluded range: 633 (39,105 pages).
Coverage was computed for 421 works that are their Index's book; 85 cover under 95% of the Index's text pages that no other work transcludes, and 27 are incomplete for that reason alone (more than 3 pages left out).
24 works transclude scan pages of files the dump has no Page: pages for (counted as no signal, not as missing).
Wikisource's own translations (labelled, kept): 2,625.

Text-quality template values: `75%` 13,048, `50%` 1,802, `25%` 343, `100%` 245.
Index progress values: `C` 21,572, `V` 8,359, `T` 7,909, `L` 840, `OCR` 283, `MS` 37, `X` 1.
Works excluded as index-faulty, by the tier their page levels would give: unproofread 9, none 5.

**Unknown works** (no signal): 33,564 in the catalog after the junk rules; the junk rules removed 294 (under-300-bytes 188, junk-template 106).
Size: median 6,131 bytes, p10 1,358, p90 69,158; multi-part 7,589.

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
- `The Black Jarl` (subpages, 173,859 B): 
- `A Desperate Street Fight (Daily Nugget)` (single, 9,947 B): Marshal Virgil Earp, Morgan and Wyatt Earp and Doc Holliday Meet the Cowboys - Three Men Killed and Two Wounded, One Seriously - Origins of the Trouble and its 
- `Antarctic Treaty` (single, 15,577 B): The Antarctic Treaty== Done at Washington 1 December 1959 Entered into force 23 June 1961 The Governments of Argentina, Australia, Belgium, Chile, the French Re
- `United Nations Security Council Resolution 1555` (single, 2,465 B): Adopted unanimously by the Security Council at its 5014th meeting, on 29 July 2004 The Security Council, Recalling its previous relevant resolutions and stateme
- `United States v. Gettysburg Electric Railway Company` (subpages, 27,495 B): These are two writs of error to the circuit court of the United States for the Eastern district of Pennsylvania. They involve the same questions. By the act of 
- `Mark Carney's 2026 WEF speech` (single, 14,315 B): Thank you very much, Larry. I'm going to start in French, and then I'll switch back to English. [The following is translated from French] Thank you, Larry. It i
- `Proclamation 5306` (single, 2,853 B): America's economy has been revitalized by the highest level of consumer confidence in nearly twenty years. Our free enterprise system and the high productivity 
- `CMH 60-3: Infantry, Part I: Regular Army` (single, 2,308 B): frameless\|right\|250px ARMY LINEAGE SERIES INFANTRY Part I: Regular Army by John K. Mahon and Romana Danysh OFFICE OF THE CHIEF OF MILITARY HISTORY UNITED STATES
- `Press Briefing by Press Secretary Ari Fleischer, May 22, 2001` (single, 37,436 B): Category:2001 US press briefings The James S. Brady Briefing Room 1:52 P.M. EDT MR. FLEISCHER: Good afternoon. I have a series of personnel announcements, and t
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
- `Patsy` (single, 15,023 B): E made his first appearance one after noon a week or so before the Fall Handicap Meeting. Mosher, Fosgill, Allen, Ronimus, and several more of us were down at t
- `Success Magazine` (virtual, 28,551 B): heard that yarn about King Cophetua, or whatever his name was, until last night; you remember, it was about the rich old boy who had no use for women until he f

**Default view** (validated or proofread, complete, not reference): 18,044 works with 113,220 texts; with unknown works shown: 50,932 works (133,138 texts). Works in the default view whose pages are all level 1: 0. Current setting: `show_unknown_by_default = True`.

## 10. Parts table size (WS§5.2)

| parts_cap | works with catalog parts | parts rows | parts table, raw SQLite | parts table, gzipped | parts_fts, gzipped |
|---|---|---|---|---|---|
| 500 | 18,238 | 292,752 | 56.2 MB | 15.85 MB | 2.62 MB |
| 2,000 (decided) | 18,288 | 343,804 | 67.8 MB | 18.38 MB | 3.22 MB |
| ∞ (all, reference works included) | 18,328 | 591,320 | 122.6 MB | 28.42 MB | 5.49 MB |
| 2,000, index_title as text and display_title always set (WS§5.1 as first written) | 18,288 | 343,804 | 83.2 MB | 19.59 MB | 3.22 MB |
| 2,000, page_title as suffix of the root title (deferred) | 18,288 | 343,804 | 45.6 MB | 16.90 MB | 3.22 MB |

Rows count multi-part works only, one row per part plus the root row. 37,666 single-page works would add one row each if stored. Sizes are a SQLite file holding only the table (with its index); gzipped at level 9. The decided encoding (2026-11-01, STATUS.md item 2) stores `index_ref` as the position of the part's Index in `works.index_titles` and `display_title` as NULL when it equals the last segment of `page_title`; per-part `quality_pages` stays. The suffix encoding (page_title stored as the suffix after the work root's title) halves the raw size and is deferred: it complicates open-by-URL and link rewriting.

## 11. Named cases (PLAN.md WP1 acceptance, WS§9)

- `The Complete Poetical Works of Percy Bysshe Shelley (ed. Hutchinson, 1914)`: work, subpages, type book, 253 parts (231 texts), 73 missing, order toc-partial, oversized False, quality proofread (page-levels), complete False (template).
- `Ozymandias`: excluded (disambiguation); search finds 4 text(s) with this title: `Weird Tales/Volume 8/Issue 3/Ozymandias` in `Weird Tales`; `The Hundred Best Poems (lyrical) in the English language - second series/Ozymandias` in `The Hundred Best Poems (lyrical) in the English language - second series`; `The Complete Poetical Works of Percy Bysshe Shelley (ed. Hutchinson, 1914)/Ozymandias` in `The Complete Poetical Works of Percy Bysshe Shelley (ed. Hutchinson, 1914)`; `The Examiner (London)/Ozymandias` in `The Examiner (London)`.
- `Ozymandias (Shelley)`: excluded (versions).
- `Frankenstein, or the Modern Prometheus (Revised Edition, 1831)`: work, subpages, type book, 31 parts (1 texts), 0 missing, order toc, oversized False, quality validated (page-levels), complete True.
- `1911 Encyclopædia Britannica`: work, subpages, type reference, 37,420 parts (37,278 texts), 0 missing, order natural, oversized True, quality proofread (page-levels), complete False (template).

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

### A: collections of root pages (57)

1. `Nostradamus: Life and Literature`: linked, book, 2 parts, 0 missing, order toc, proofread
    - front: Nostradamus: Life and Literature
    - chapter: Preface to Cesar Nostradamus (`Nostradamus (1961)/Preface to Cesar Nostradamus`) [container]
    - text: Epistle to Henry II (`Nostradamus (1961)/Epistle to Henry II`) [container]
1. `The Guide for the Perplexed (1904)`: linked, book, 7 parts, 7 missing, order chain, proofread
    - front: The Guide for the Perplexed (1904)
    - text: Translator’s Introduction (`The Guide for the Perplexed (Friedlander)/Translator's Introduction`) [container]
    - text: Author's Introduction (`The Guide for the Perplexed (Friedlander)/Introduction`) [container]
    - chapter: Part I (`The Guide for the Perplexed (Friedlander)/Part I`) [container]
    - text: Propositions (Introduction to Part II) (`The Guide for the Perplexed (Friedlander)/Part II/Propositions`) [container]
    - text: Chapters of Part II (`The Guide for the Perplexed (Friedlander)/Part II/Chapters`) [container]
    - chapter: Introduction to Part III (`The Guide for the Perplexed (Friedlander)/Part III/Introduction`) [container]
    - text: Chapters of Part III (`The Guide for the Perplexed (Friedlander)/Part III/Chapters`) [container]
1. `Trivia, or the Art of Walking the Streets of London to which is added Dr. Johnson's London`: linked, book, 6 parts, 0 missing, order single, proofread
    - front: Trivia, or the Art of Walking the Streets of London to which is added Dr. Johnson's London
    - volume: Trivia, by John Gay (`Trivia (Gay)`) [container]
      - text: Trivia, Book I. (`Trivia (Gay)/Part 1`) [container]
      - text: Trivia, Book II. (`Trivia (Gay)/Part 2`) [container]
      - text: Trivia, Book III. (`Trivia (Gay)/Part 3`) [container]
      - chapter: Index to Trivia (`Trivia (Gay)/Index`) [container]
      - text: London (`Trivia (Gay)/London`) [container]
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
1. `The World's Redemption`: linked, book, 16 parts, 25 missing, order chain, proofread
    - toc: The World's Redemption
    - chapter: Preface (`The Worlds Redemption/Preface`) [container]
    - chapter: 1 (`The Worlds Redemption/1`) [container]
    - chapter: 2 (`The Worlds Redemption/2`) [container]
    - chapter: 3 (`The Worlds Redemption/3`) [container]
    - chapter: CHAPTER IV — The Covenants of Promise (`The Worlds Redemption/4`) [container]
    - chapter: Chapter V - The Covenants of Promise - Continued (`The Worlds Redemption/5`) [container]
    - chapter: CHAPTER IV — Confirmation of the Covenants of Promise (`The Worlds Redemption/6`) [container]
    - chapter: CHAPTER VII — The Restoration of Israel in Relation to the World's Redemption (`The Worlds Redemption/7`) [container]
    - chapter: CHAPTER VIII — The Messianic Restoration of the Kingdom of Israel and Throne of David (`The Worlds Redemption/8`) [container]
    - chapter: CHAPTER IX — The Literal Return of Christ to the Earth (`The Worlds Redemption/9`) [container]
    - chapter: CHAPTER X — The Heavens and the Earth, Old and New (`The Worlds Redemption/10`) [container]
    - … 5 more
1. `The Everlasting Gospel`: linked, collection, 1 parts, 0 missing, order single, unknown
    - front: The Everlasting Gospel
    - text: The Everlasting Gospel (complete) [container]
1. `The Naturalist on the River Amazons, volume 1`: linked, book, 8 parts, 0 missing, order toc, proofread
    - front: The Naturalist on the River Amazons, volume 1
    - chapter: PREFACE. (`The Naturalist on the River Amazons/Volume 1/Preface`) [container]
    - chapter: CHAPTER I. (`The Naturalist on the River Amazons/Volume 1/Chapter 1`) [container]
    - chapter: CHAPTER II. (`The Naturalist on the River Amazons/Volume 1/Chapter 2`) [container]
    - chapter: CHAPTER III. (`The Naturalist on the River Amazons/Volume 1/Chapter 3`) [container]
    - chapter: CHAPTER IV. (`The Naturalist on the River Amazons/Volume 1/Chapter 4`) [container]
    - chapter: CHAPTER V. (`The Naturalist on the River Amazons/Volume 1/Chapter 5`) [container]
    - chapter: CHAPTER VI. (`The Naturalist on the River Amazons/Volume 1/Chapter 6`) [container]
    - chapter: CHAPTER VII. (`The Naturalist on the River Amazons/Volume 1/Chapter 7`) [container]
1. `Malaysia, Report of the Inter-Governmental Committee, 1962`: linked, book, 21 parts, 0 missing, order chain, proofread
    - toc: Malaysia, Report of the Inter-Governmental Committee, 1962
    - chapter: Introduction (`Report of The Inter-Governmental Committee, Malaysia/Introduction`) [container]
    - text: Establishment of the Federation of Malaysia (`Report of The Inter-Governmental Committee, Malaysia/Establishment of the Federation of Malaysia`) [container]
    - text: Proposed Constitutional Arrangements (`Report of The Inter-Governmental Committee, Malaysia/Proposed Constitutional Arrangements`) [container]
    - text: Financial Provisions (`Report of The Inter-Governmental Committee, Malaysia/Financial provisions`) [container]
    - text: Elections (`Report of The Inter-Governmental Committee, Malaysia/Elections`) [container]
    - text: The Judiciary (`Report of The Inter-Governmental Committee, Malaysia/Judiciary`) [container]
    - text: Public Service (`Report of The Inter-Governmental Committee, Malaysia/Public Service`) [container]
    - text: National language (`Report of The Inter-Governmental Committee, Malaysia/National language`) [container]
    - text: Special position of Indigenous races (`Report of The Inter-Governmental Committee, Malaysia/Special position of Indigenous races`) [container]
    - text: Constitutional Safeguards (`Report of The Inter-Governmental Committee, Malaysia/Constitutional Safeguards`) [container]
    - text: Proceedings questioning the validity of a Federal or State Law (`Report of The Inter-Governmental Committee, Malaysia/Proceedings questioning the validity of a Federal or State Law`) [container]
    - … 10 more
1. `Poems of Letitia Elizabeth Landon (L. E. L.) in Friendship’s Offering, 1827`: linked, book, 3 parts, 0 missing, order chain, validated
    - toc: Poems of Letitia Elizabeth Landon (L. E. L.) in Friendship’s Offering, 1827
    - text: The Spirit and The Angel of Death (`Poems of Letitia Elizabeth Landon in Friendship’s Offering, 1827/Spirit`) [container]
    - text: Song - I wrote my name upon the sand (`Poems of Letitia Elizabeth Landon in Friendship’s Offering, 1827/Song`) [container]
    - text: The Lyrist (`Poems of Letitia Elizabeth Landon in Friendship’s Offering, 1827/The Lyrist`) [container]
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
1. `Sonnets attempted in the manner of Contemporary Writers`: linked, collection, 3 parts, 0 missing, order chain, unknown
    - toc: Sonnets attempted in the manner of Contemporary Writers
    - text: Sonnet I (`Pensive at Eve`) [container]
    - text: Sonnet II: To Simplicity (`To Simplicity`) [container]
    - text: Sonnet III: On a Ruined House in a Romantic Country (`On a Ruined House in a Romantic Country`) [container]
1. `United Nations General Assembly Resolution 2200 A (XXI) A /Annex`: linked, book, 3 parts, 0 missing, order single, unproofread
    - front: United Nations General Assembly Resolution 2200 A (XXI) A /Annex
    - volume: United Nations General Assembly Resolution 2200 (XXI) [container]
      - text: United Nations General Assembly Resolution 2200 A (XXI) B [container]
      - text: United Nations General Assembly Resolution 2200 A (XXI) C [container]
1. `The Cow Chase`: linked, collection, 1 parts, 0 missing, order single, unknown
    - front: The Cow Chase
    - text: Anonymous of Andre's Death [container]
1. `Poems of Letitia Elizabeth Landon (L. E. L.) in The Literary Souvenir, 1827`: linked, book, 4 parts, 0 missing, order chain, validated
    - toc: Poems of Letitia Elizabeth Landon (L. E. L.) in The Literary Souvenir, 1827
    - text: Stanzas. Written beneath the portrait of Lord Byron, painted by Mr. West. (`Poems of Letitia Elizabeth Landon in The Literary Souvenir, 1827/Lord Byron`) [container]
    - text: The Minstrel’s Monitor (`Poems of Letitia Elizabeth Landon in The Literary Souvenir, 1827/Monitor`) [container]
    - text: The Inconstant (`Poems of Letitia Elizabeth Landon in The Literary Souvenir, 1827/Inconstant`) [container]
    - text: Cupid and Psyche (`Poems of Letitia Elizabeth Landon in The Literary Souvenir, 1827/Cupid and Psyche`) [container]
1. `The Case for India`: linked, book, 6 parts, 0 missing, order chain, proofread
    - toc: The Case for India
    - chapter: Introduction (`The Case For India/Introduction`) [container]
    - chapter: Chapter I: Pre-War Military Expenditure (`The Case For India/Chapter I`) [container]
    - chapter: Chapter II: Causes of the New Spirit in India (`The Case For India/Chapter II`) [container]
    - chapter: Chapter III: Why India Demands Home Rule (`The Case For India/Chapter III`) [container]
    - chapter: Chapter III/I: The Vital Reason (`The Case For India/Chapter III/I`) [container]
    - chapter: Chapter III/II: The Secondary Reasons (`The Case For India/Chapter III/II`) [container]
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
1. `Itar-Tass Russian News Agency v. Russian Kurier, Inc.`: linked, collection, 3 parts, 0 missing, order chain, unknown
    - front: Itar-Tass Russian News Agency v. Russian Kurier, Inc.
    - text: 886 F. Supp. 1120 (S.D.N.Y. 1995) (`Itar-Tass Russian News Agency v. Russian Kurier, Inc. - 886 F. Supp. 1120 (S.D.N.Y. 1995)`) [container]
    - text: No. 95 Civ. 2144(JGK) (S.D.N.Y. March 10, 1997) (`Itar-Tass Russian News Agency v. Russian Kurier, Inc. - No. 95 Civ. 2144(JGK) (S.D.N.Y. March 10, 1997)`) [container]
    - text: 153 F.3d 82 (2d Cir. 1998) (`Itar-Tass Russian News Agency v. Russian Kurier, Inc. - 153 F.3d 82 (2d Cir. 1998)`) [container]
1. `Public Law 110-343`: linked, collection, 3 parts, 0 missing, order chain, unknown
    - front: Public Law 110-343
    - text: Division A: Emergency Economic Stabilization Act of 2008 (`Emergency Economic Stabilization Act of 2008`) [container]
    - text: 'Division B: Energy Improvement and Extension Act of 2008 (`Energy Improvement and Extension Act of 2008`) [container]
    - text: Division C: Tax Extenders and Alternative Minimum Tax Relief Act of 2008 (`Tax Extenders and Alternative Minimum Tax Relief Act of 2008`) [container]
1. `The Naturalist on the River Amazons, volume 2`: linked, book, 7 parts, 0 missing, order toc, proofread
    - front: The Naturalist on the River Amazons, volume 2
    - chapter: CHAPTER I. (`The Naturalist on the River Amazons/Volume 2/Chapter 1`) [container]
    - chapter: CHAPTER II. (`The Naturalist on the River Amazons/Volume 2/Chapter 2`) [container]
    - chapter: CHAPTER III. (`The Naturalist on the River Amazons/Volume 2/Chapter 3`) [container]
    - chapter: CHAPTER IV. (`The Naturalist on the River Amazons/Volume 2/Chapter 4`) [container]
    - chapter: CHAPTER V. (`The Naturalist on the River Amazons/Volume 2/Chapter 5`) [container]
    - chapter: CHAPTER VI. (`The Naturalist on the River Amazons/Volume 2/Chapter 6`) [container]
    - chapter: INDEX. (`The Naturalist on the River Amazons/Volume 2/Index`) [container]
1. `Brigands of the Moon`: linked, periodical-issue, 1 parts, 0 missing, order single, proofread
    - front: Brigands of the Moon
    - chapter: Part 3 (`Astounding Stories of Super Science/Volume 02/Number 02/Brigands of the Moon`) [container]
1. `The Harvard Classics`: linked, collection, 1 parts, 1 missing, order single, unknown
    - front: The Harvard Classics
    - text: Black-Eyed Susan (`The Harvard Classics Vol. 40/Black-Eyed Susan`) [container]
1. `Constitution of the Republic of China (1947)`: linked, collection, 7 parts, 0 missing, order chain, unknown
    - front: Constitution of the Republic of China (1947)
    - text: Additional Articles of the Constitution of the Republic of China (1991) [container]
    - text: Additional Articles of the Constitution of the Republic of China (1992) [container]
    - text: Additional Articles of the Constitution of the Republic of China (1994) [container]
    - text: Additional Articles of the Constitution of the Republic of China (1997) [container]
    - text: Additional Articles of the Constitution of the Republic of China (1999) [container]
    - text: Additional Articles of the Constitution of the Republic of China (2000) [container]
    - text: Additional Articles of the Constitution of the Republic of China (2005) [container]
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
1. `Treaty of Portsmouth`: linked, book, 1 parts, 0 missing, order single, unproofread
    - front: Treaty of Portsmouth
    - chapter: Appendix 3 (`A History of the Japanese People/Appendix 3`) [container]
1. `A Posthumous Poem of Letitia Elizabeth Landon (L. E. L.) in Friendship’s Offering, 1844`: linked, book, 1 parts, 0 missing, order single, validated
    - front: A Posthumous Poem of Letitia Elizabeth Landon (L. E. L.) in Friendship’s Offering, 1844
    - text: The Soldier’s Bride (`A Posthumous Poem of Letitia Elizabeth Landon in Friendship’s Offering, 1828/The Soldier’s Bride`) [container]
1. `The Inner Life: volume I`: linked, book, 63 parts, 0 missing, order chain, unknown
    - front: The Inner Life: volume I
    - text: Foreword to the Indian edition (`The Inner Life, v. I/Foreword to the Indian edition`) [container]
    - chapter: Introduction (`The Inner Life, v. I/Introduction`) [container]
    - chapter: First Section: The Great Ones and the Way to Them (`The Inner Life, v. I/First Section`) [container]
    - chapter: First Section/I: The Great Ones (`The Inner Life, v. I/First Section/I`) [container]
    - chapter: First Section/II: The Work of The Christ (`The Inner Life, v. I/First Section/II`) [container]
    - chapter: First Section/III: The Work of The Masters (`The Inner Life, v. I/First Section/III`) [container]
    - chapter: First Section/IV: Masters and Pupils (`The Inner Life, v. I/First Section/IV`) [container]
    - chapter: First Section/V: The Path of Progress (`The Inner Life, v. I/First Section/V`) [container]
    - chapter: First Section/VI: The Ancient Mysteries (`The Inner Life, v. I/First Section/VI`) [container]
    - chapter: Second Section: Religion (`The Inner Life, v. I/Second Section`) [container]
    - chapter: Second Section/I: The Logos (`The Inner Life, v. I/Second Section/I`) [container]
    - … 52 more
1. `Judicial Improvements Act of 1990`: linked, collection, 3 parts, 9 missing, order chain, unknown
    - front: Judicial Improvements Act of 1990
    - text: Title V - Television Program Improvement (`Television Program Improvement Act of 1990`) [container]
    - text: Title VI - Visual Artists Rights (`Visual Artists Rights Act of 1990`) [container]
    - text: Title VII - Architectural Works (`Architectural Works Copyright Protection Act`) [container]
1. `Poems of Letitia Elizabeth Landon (L. E. L.) in Friendship's Offering, 1828`: linked, book, 4 parts, 0 missing, order chain, validated
    - toc: Poems of Letitia Elizabeth Landon (L. E. L.) in Friendship's Offering, 1828
    - chapter: Title Page (`Poems of Letitia Elizabeth Landon (L. E. L.) in Friendship’s Offering, 1828`) [container]
    - text: The Banner of Five Byzants (`Poems of Letitia Elizabeth Landon in Friendship's Offering, 1828/The Banner of Five Byzants`) [container]
    - text: Venus taking the Bow from a Sleeping Cupid (`Poems of Letitia Elizabeth Landon in Friendship's Offering, 1828/Venus`) [container]
    - text: The First Ball (`Poems of Letitia Elizabeth Landon in Friendship's Offering, 1828/First Ball`) [container]
1. `House Page Board Revision Act of 2007`: linked, collection, 1 parts, 1 missing, order single, validated
    - front: House Page Board Revision Act of 2007
    - text: 110th Congress, Public Law 110-2 (`United States Statutes at Large/Volume 122/110th Congress/Public Law 110-2`) [container]
1. `The Mahabharat`: linked, collection, 397 parts, 0 missing, order toc-partial, unproofread
    - toc: The Mahabharat
    - volume: Book 1: Adi Parva (`The Mahabharata/Book 1: Adi Parva`) [container]
      - chapter: Introduction — Section I (`The Mahabharata/Book 1: Adi Parva/Section 1`) [container]
      - chapter: Introduction — Section II (`The Mahabharata/Book 1: Adi Parva/Section 2`) [container]
      - text: Paushya Parva — Section III (`The Mahabharata/Book 1: Adi Parva/Section 3`) [container]
      - text: Pauloma Parva — Section IV (`The Mahabharata/Book 1: Adi Parva/Section 4`) [container]
      - text: Pauloma Parva — Section V (`The Mahabharata/Book 1: Adi Parva/Section 5`) [container]
      - text: Pauloma Parva — Section VI (`The Mahabharata/Book 1: Adi Parva/Section 6`) [container]
      - text: Pauloma Parva — Section VII (`The Mahabharata/Book 1: Adi Parva/Section 7`) [container]
      - text: Pauloma Parva — Section VIII (`The Mahabharata/Book 1: Adi Parva/Section 8`) [container]
      - text: Pauloma Parva — Section IX (`The Mahabharata/Book 1: Adi Parva/Section 9`) [container]
      - text: Pauloma Parva — Section X (`The Mahabharata/Book 1: Adi Parva/Section 10`) [container]
    - … 386 more

### B: mixed (85)

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
1. `The Works of Voltaire`: mixed, collection, 62 parts, 47 missing, order scan-volumes, proofread
    - front: The Works of Voltaire
    - volume: Volume 1 (`The Works of Voltaire/Volume 1`) [container]
      - text: Publisher's Preface (`The Works of Voltaire/Volume 1/Publisher's Preface`) [container]
      - chapter: Introduction (`The Works of Voltaire/Volume 1/Introduction`) [container]
      - text: The Many-Sided Voltaire (`The Works of Voltaire/Volume 1/The Many-Sided Voltaire`) [container]
      - text: On Liberty (`The Works of Voltaire/Volume 1/On Liberty`) [container]
      - text: On the Nature of Man (`The Works of Voltaire/Volume 1/On the Nature of Man`) [container]
      - text: On Moderation in All Things (`The Works of Voltaire/Volume 1/On Moderation in All Things`) [container]
    - volume: Volume 2 (`The Works of Voltaire/Volume 2`) [container]
      - volume: Contents (`The Story of Johnny; or, The Atheist and the Sage`) [container]
        - chapter: Introduction (`The Story of Johnny; or, The Atheist and the Sage/Introduction`) [container]
        - chapter: Chapter I (`The Story of Johnny; or, The Atheist and the Sage/Chapter 1`) [container]
    - … 51 more
1. `The Boys of Columbia High on the Ice`: mixed, book, 24 parts, 0 missing, order toc-partial, proofread
    - front: The Boys of Columbia High on the Ice
    - chapter: Chapter 1 (`The Boys of Columbia High on the Ice/Chapter 1`) [hierarchy]
    - chapter: Chapter 2 (`The Boys of Columbia High on the Ice/Chapter 2`) [hierarchy]
    - chapter: Chapter 3 (`The Boys of Columbia High on the Ice/Chapter 3`) [hierarchy]
    - chapter: Chapter 4 (`The Boys of Columbia High on the Ice/Chapter 4`) [hierarchy]
    - chapter: Chapter 5 (`The Boys of Columbia High on the Ice/Chapter 5`) [hierarchy]
    - chapter: Chapter 6 (`The Boys of Columbia High on the Ice/Chapter 6`) [hierarchy]
    - chapter: Chapter 7 (`The Boys of Columbia High on the Ice/Chapter 7`) [hierarchy]
    - chapter: Chapter 8 (`The Boys of Columbia High on the Ice/Chapter 8`) [hierarchy]
    - chapter: Chapter 9 (`The Boys of Columbia High on the Ice/Chapter 9`) [hierarchy]
    - chapter: Chapter 10 (`The Boys of Columbia High on the Ice/Chapter 10`) [hierarchy]
    - chapter: Chapter 11 (`The Boys of Columbia High on the Ice/Chapter 11`) [hierarchy]
    - … 13 more
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
1. `Little Mr. Bouncer; and Tales of College Life`: mixed, book, 50 parts, 0 missing, order scan, proofread
    - front: Little Mr. Bouncer; and Tales of College Life
    - volume: Little Mr Bouncer and His Friend Verdant Green [container]
      - chapter: Chapter 1 (`Little Mr Bouncer and His Friend Verdant Green/Chapter 1`) [container]
      - chapter: Chapter 2 (`Little Mr Bouncer and His Friend Verdant Green/Chapter 2`) [container]
      - chapter: Chapter 3 (`Little Mr Bouncer and His Friend Verdant Green/Chapter 3`) [container]
      - chapter: Chapter 4 (`Little Mr Bouncer and His Friend Verdant Green/Chapter 4`) [container]
      - chapter: Chapter 5 (`Little Mr Bouncer and His Friend Verdant Green/Chapter 5`) [container]
      - chapter: Chapter 6 (`Little Mr Bouncer and His Friend Verdant Green/Chapter 6`) [container]
      - chapter: Chapter 7 (`Little Mr Bouncer and His Friend Verdant Green/Chapter 7`) [container]
      - chapter: Chapter 8 (`Little Mr Bouncer and His Friend Verdant Green/Chapter 8`) [container]
      - chapter: Chapter 9 (`Little Mr Bouncer and His Friend Verdant Green/Chapter 9`) [container]
      - chapter: Chapter 10 (`Little Mr Bouncer and His Friend Verdant Green/Chapter 10`) [container]
    - … 39 more
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
1. `The New Monthly Magazine`: mixed, collection, 33 parts, 118 missing, order toc-partial, proofread
    - front: The New Monthly Magazine
    - volume: Volume 11 (`The New Monthly Magazine and Universal Register/Volume 11`) [container]
      - volume: Number 63; April 1, 1819 (`The New Monthly Magazine and Universal Register/Volume 11/Number 63`) [hierarchy]
        - text: Extract of a Letter from Geneva (`The New Monthly Magazine and Universal Register/Volume 11/Number 63/Extract of a Letter from Geneva`) [hierarchy]
        - text: The Vampyre (`The New Monthly Magazine and Universal Register/Volume 11/Number 63/The Vampyre`) [hierarchy]
      - volume: Number 64; May 1, 1819 (`The New Monthly Magazine and Universal Register/Volume 11/Number 64`) [hierarchy]
        - chapter: Letter from Dr. Polidori (`The New Monthly Magazine and Universal Register/Volume 11/Number 64/Letter from Dr. Polidori`) [hierarchy]
        - text: National Series of Medals (`The New Monthly Magazine and Universal Register/Volume 11/Number 64/National Series of Medals`) [hierarchy]
    - volume: Volume 7 (`The New Monthly Magazine/Volume 7`) [container]
      - text: Puns and Punsters (`The New Monthly Magazine/Volume 7/Puns and Punsters`) [container]
      - text: Sporting Without a Licence (`The New Monthly Magazine/Volume 7/Sporting Without a Licence`) [container]
      - text: Dinner Company to Let.—A Card (`The New Monthly Magazine/Volume 7/Dinner Company to Let`) [container]
    - … 22 more
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
1. `The Collected Works of Mahatma Gandhi`: mixed, book, 47 parts, 1142 missing, order toc, unproofread
    - front: The Collected Works of Mahatma Gandhi
    - volume: Volume I (`The Collected Works of Mahatma Gandhi/Volume 1`) [container]
      - text: General Preface (`The Collected Works of Mahatma Gandhi/Volume 1/General Preface`) [container]
      - chapter: Preface to this Volume (`The Collected Works of Mahatma Gandhi/Volume 1/Preface to this Volume`) [container]
      - text: Historical background of the South African Indian problem (`The Collected Works of Mahatma Gandhi/Volume 1/Historical background of the South African Indian problem`) [container]
      - text: Note to the reader (`The Collected Works of Mahatma Gandhi/Volume 1/Note to the reader`) [container]
      - text: Acknowledgements (`The Collected Works of Mahatma Gandhi/Volume 1/Acknowledgements`) [container]
      - text: A Confession (`The Collected Works of Mahatma Gandhi/Volume 1/A Confession`) [container]
      - text: Speech at Alfred High School, Rajkot (`The Collected Works of Mahatma Gandhi/Volume 1/Speech at Alfred High School, Rajkot`) [container]
      - chapter: Letter to Lakshmidas Gandhi (`The Collected Works of Mahatma Gandhi/Volume 1/Letter to Lakshmidas Gandhi`) [container]
      - text: London Diary (`The Collected Works of Mahatma Gandhi/Volume 1/London Diary`) [container]
      - chapter: Volume I, 1894, Guide to London (`The Collected Works of Mahatma Gandhi/Volume 1/Guide to London`) [container]
    - … 36 more
1. `Two Treatises of Government (1764)`: mixed, book, 10 parts, 6 missing, order chain, proofread
    - front: Two Treatises of Government (1764)
    - chapter: Preface (`Two Treatises of Government (1764)/Preface`) [container]
    - volume: The First Treatise of Government (`Two Treatises of Government (1764)/Book I`) [container]
      - chapter: Chapter 1 (`Two Treatises of Government (1764)/Book I/Chapter 1`) [hierarchy]
      - chapter: Chapter 2 (`Two Treatises of Government (1764)/Book I/Chapter 2`) [hierarchy]
      - chapter: Chapter III. (`Two Treatises of Government (1764)/Book I/Chapter 3`) [hierarchy]
      - chapter: Chapter 4 (`Two Treatises of Government (1764)/Book I/Chapter 4`) [hierarchy]
      - chapter: Chapter 5 (`Two Treatises of Government (1764)/Book I/Chapter 5`) [hierarchy]
    - volume: The Second Treatise of Government: An Essay Concerning the True Origin, Extent, and End of Civil Government (`Two Treatises of Government (1764)/Book II`) [container]
      - chapter: Chapter I. (`Two Treatises of Government/Book II/Chapter I`) [container]
      - chapter: Chapter II. (`Two Treatises of Government/Book II/Chapter II`) [container]
1. `The Collected Works of Henrik Ibsen`: mixed, book, 55 parts, 9 missing, order toc, unproofread
    - toc: The Collected Works of Henrik Ibsen
    - volume: Volume 1 (`The Collected Works of Henrik Ibsen/Volume 1`) [container]
      - volume: LADY INGER OF ÖSTRÅT (`The Collected Works of Henrik Ibsen/Volume 1/Lady Inger of Östråt`) [container]
        - chapter: Introduction (`The Collected Works of Henrik Ibsen/Volume 1/Lady Inger of Östråt/Introduction`) [container]
        - chapter: Act 1 (`The Collected Works of Henrik Ibsen/Volume 1/Lady Inger of Östråt/Act 1`) [container]
        - chapter: Act 2 (`The Collected Works of Henrik Ibsen/Volume 1/Lady Inger of Östråt/Act 2`) [container]
        - chapter: Act 3 (`The Collected Works of Henrik Ibsen/Volume 1/Lady Inger of Östråt/Act 3`) [container]
        - chapter: Act 4 (`The Collected Works of Henrik Ibsen/Volume 1/Lady Inger of Östråt/Act 4`) [container]
        - chapter: Act 5 (`The Collected Works of Henrik Ibsen/Volume 1/Lady Inger of Östråt/Act 5`) [container]
      - volume: THE FEAST AT SOLHOUG (`The Collected Works of Henrik Ibsen/Volume 1/The Feast at Solhoug`) [container]
        - chapter: Introduction (`The Collected Works of Henrik Ibsen/Volume 1/The Feast at Solhoug/Introduction`) [container]
        - text: Author’s Preface (`The Collected Works of Henrik Ibsen/Volume 1/The Feast at Solhoug/Author’s Preface`) [container]
    - … 44 more
1. `The Dialogues of Plato (Jowett)`: mixed, book, 62 parts, 20 missing, order scan-volumes, proofread
    - toc: The Dialogues of Plato (Jowett)
    - volume: Volume I (`The Dialogues of Plato (Jowett)/Volume I`) [container]
      - chapter: Introduction to the Charmides (`The Dialogues of Plato (Jowett)/Charmides/Introduction`) [container]
      - text: Charmides (`The Dialogues of Plato (Jowett)/Charmides`) [container]
      - volume: Cratylus (`The Dialogues of Plato (Jowett)/Cratylus`) [container]
        - chapter: Introduction to the Cratylus (`The Dialogues of Plato (Jowett)/Cratylus/Introduction`) [container]
    - chapter: Preface to the First Edition (`The Dialogues of Plato (Jowett)/Preface to the First Edition`) [container]
    - chapter: Preface to the Second and Third Editions (`The Dialogues of Plato (Jowett)/Preface to the Second and Third Editions`) [container]
    - text: Note (`The Dialogues of Plato (Jowett)/Note`) [container]
    - volume: Vol. I Contents (`The Dialogues of Plato (Jowett)/Vol. I Contents`) [container]
      - volume: Lysis (`The Dialogues of Plato (Jowett)/Lysis`) [container]
        - text: Lysis Introduction (`The Dialogues of Plato (Jowett)/Lysis/Introduction`) [container]
    - … 51 more
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
1. `Arbitration Act 2025`: mixed, book, 2 parts, 0 missing, order scan-volumes, proofread
    - front: Arbitration Act 2025
    - text: Explanatory Notes (`Arbitration Act 2025/Explanatory Notes`) [container]
    - text: Explanatory Notes (`Commonwealth Parliamentary Association and International Committee of the Red Cross (Status) Act 2025/Explanatory Notes`) [container]
1. `Poems of Letitia Elizabeth Landon (L. E. L.) in Friendship’s Offering, 1836`: mixed, book, 6 parts, 0 missing, order chain, validated
    - toc: Poems of Letitia Elizabeth Landon (L. E. L.) in Friendship’s Offering, 1836
    - volume: Poems of Letitia Elizabeth Landon (L. E. L.) in Friendship's Offering, 1836 [container]
      - text: The Boon (`Poems of Letitia Elizabeth Landon (L. E. L.) in Friendship’s Offering, 1836/The Boon`) [container]
      - text: The Festival (`Poems of Letitia Elizabeth Landon (L. E. L.) in Friendship’s Offering, 1836/The Festival`) [container]
      - text: The Black Seal (`Poems of Letitia Elizabeth Landon (L. E. L.) in Friendship’s Offering, 1836/The Black Seal`) [container]
    - text: The Boon (`Poems of Letitia Elizabeth Landon in Friendship’s Offering, 1828/The Boon`) [container]
    - text: The Festival (`Poems of Letitia Elizabeth Landon in Friendship’s Offering, 1836/The Festival`) [container]
1. `Oriental Stories`: mixed, collection, 23 parts, 134 missing, order toc, proofread
    - front: Oriental Stories
    - volume: Volume 1, Issue 1 (`Oriental Stories/Volume 1/Issue 1`) [container]
      - text: The Yellow River (`Oriental Stories/Volume 1/Issue 1/The Yellow River`) [container]
      - text: Singapore Nights (`Oriental Stories/Volume 1/Issue 1/Singapore Nights`) [container]
      - text: The Man Who Limped (`Oriental Stories/Volume 1/Issue 1/The Man Who Limped`) [container]
      - text: The White Queen (`Oriental Stories/Volume 1/Issue 1/The White Queen`) [container]
      - text: Flower Profiles (`Oriental Stories/Volume 1/Issue 1/Flower Profiles`) [container]
      - text: The Tiger's Eye (`Oriental Stories/Volume 1/Issue 1/The Tiger's Eye`) [container]
      - text: Eyes of the Dead (`Oriental Stories/Volume 1/Issue 1/Eyes of the Dead`) [container]
      - text: The Desert Woman (`Oriental Stories/Volume 1/Issue 1/The Desert Woman`) [container]
      - text: The Cobra Den (`Oriental Stories/Volume 1/Issue 1/The Cobra Den`) [container]
      - text: The Black Camel (`Oriental Stories/Volume 1/Issue 1/The Black Camel`) [container]
    - … 12 more
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
1. `Labour: The Divine Command`: mixed, book, 4 parts, 0 missing, order toc, proofread
    - front: Labour: The Divine Command
    - chapter: Introduction (`Labour: The Divine Command/intro`) [container]
    - text: Labor and Bondareff's Theory [container]
    - text: Labor, According to the Bible [container]
    - text: Love and Labor [container]
1. `The People of the Black Circle`: mixed, book, 11 parts, 0 missing, order scan-volumes, proofread
    - toc: The People of the Black Circle
    - chapter: Chapter VI: The Mountain of the Black Seers (`The People of the Black Circle/Chapter VI`) [container]
    - chapter: Part 2 of 3 (`Weird Tales/Volume 24/Issue 4/The People of the Black Circle`) [container]
    - chapter: Chapter VII: On to Yimsha (`The People of the Black Circle/Chapter VII`) [container]
    - chapter: Chapter VIII: Yasmina Knows Stark Terror (`The People of the Black Circle/Chapter VIII`) [container]
    - chapter: Chapter IX: The Castle of the Wizards (`The People of the Black Circle/Chapter IX`) [container]
    - chapter: Chapter X: Yasmina and Conan (`The People of the Black Circle/Chapter X`) [container]
    - chapter: Chapter I: Death Strikes a King (`The People of the Black Circle/Chapter I`) [container]
    - chapter: Chapter II: A Barbarian from the Hills (`The People of the Black Circle/Chapter II`) [container]
    - chapter: Chapter III: Khemsa Uses Magic (`The People of the Black Circle/Chapter III`) [container]
    - chapter: Chapter IV: An Encounter in the Pass (`The People of the Black Circle/Chapter IV`) [container]
    - chapter: Chapter V: The Black Stallion (`The People of the Black Circle/Chapter V`) [container]
1. `The International Jew`: mixed, collection, 89 parts, 0 missing, order toc-partial, unknown
    - toc: The International Jew
    - volume: Volume 1: The World's Foremost Problem (`The International Jew/Volume 1`) [container]
      - chapter: Preface (`The International Jew/Volume 1/Preface`) [hierarchy]
      - text: The Jew in Character and Business (`The International Jew/Volume 1/Chapter 1`) [container]
      - text: Germany's Reaction Against the Jew (`The International Jew/Volume 1/Chapter 2`) [container]
      - text: Jewish History in the United States (`The International Jew/Volume 1/Chapter 3`) [hierarchy]
      - text: The Jewish Question -- Fact or Fancy? (`The International Jew/Volume 1/Chapter 4`) [hierarchy]
      - text: Anti-Semitism—Will It Appear in the U.S.? (`The International Jew/Volume 1/Chapter 5`) [hierarchy]
      - text: Jewish Question Breaks Into the Magazines (`The International Jew/Volume 1/Chapter 6`) [hierarchy]
      - text: Arthur Brisbane Leaps to the Help of Jewry (`The International Jew/Volume 1/Chapter 7`) [hierarchy]
      - text: Does a Definite Jewish World Program Exist? (`The International Jew/Volume 1/Chapter 8`) [hierarchy]
      - text: The Historic Basis of Jewish Imperialism (`The International Jew/Volume 1/Chapter 9`) [hierarchy]
    - … 78 more
1. `A Practical Grammar of the Latin Language`: mixed, collection, 34 parts, 5 missing, order toc-partial, unknown
    - front: A Practical Grammar of the Latin Language
    - text: Changelog (`A Practical Grammar of the Latin Language/Changelog`) [container]
    - chapter: Preface (`A Practical Grammar of the Latin Language/Preface`) [container]
    - text: Abbreviations (`A Practical Grammar of the Latin Language/Abbreviations`) [container]
    - text: Lesson I.&mdash;Pēnsum Prīmum. (`A Practical Grammar of the Latin Language/Lesson 1`) [container]
    - text: Lesson II.&mdash;Pensum Alterum. (`A Practical Grammar of the Latin Language/Lesson 2`) [container]
    - text: Lesson 3 (`A Practical Grammar of the Latin Language/Lesson 3`) [container]
    - text: Lesson IV.&mdash;Pensum Quārtum. (`A Practical Grammar of the Latin Language/Lesson 4`) [container]
    - text: Lesson V.&mdash;Pēnsum Quintum. (`A Practical Grammar of the Latin Language/Lesson 5`) [container]
    - text: Lesson VI.&mdash;Pēnsum Sextum. (`A Practical Grammar of the Latin Language/Lesson 6`) [container]
    - text: Lesson VII.&mdash;Pēnsum Septimum. (`A Practical Grammar of the Latin Language/Lesson 7`) [container]
    - text: Lesson VIII.&mdash;Pēnsum Octāvum. (`A Practical Grammar of the Latin Language/Lesson 8`) [container]
    - … 23 more
1. `United States v. Chas. Pfizer & Co., Inc.`: mixed, collection, 2 parts, 0 missing, order natural, unknown
    - front: United States v. Chas. Pfizer & Co., Inc.
    - text: Opinion of the Court (`United States v. Chapfizer & Co/Opinion of the Court`) [container]
    - text: Opinion of the Court (`United States v. Chas. Pfizer & Co., Inc./Opinion of the Court`) [container]
1. `A Philosophical Enquiry into the Origin of our Ideas of the Sublime and Beautiful`: mixed, book, 109 parts, 61 missing, order toc, unproofread
    - front: A Philosophical Enquiry into the Origin of our Ideas of the Sublime and Beautiful
    - chapter: Part I., Sect. XX. The same. (`A Philosophical Enquiry into the Origin of our Ideas of the Sublime and Beautiful/Part I/Chapter 20`) [container]
    - chapter: Part I., Sect. XXI. The Conclusion. (`A Philosophical Enquiry into the Origin of our Ideas of the Sublime and Beautiful/Part I/Chapter 21`) [container]
    - text: Introductory Note (`On the Sublime and Beautiful/Introductory Note`) [container]
    - volume: Part I (`On the Sublime and Beautiful/Part I`) [container]
      - chapter: Part I., Sect. I. Novelty (`A Philosophical Enquiry into the Origin of our Ideas of the Sublime and Beautiful/Part I/Chapter 1`) [container]
      - chapter: Part I., Sect. II. Pain and Pleasure (`A Philosophical Enquiry into the Origin of our Ideas of the Sublime and Beautiful/Part I/Chapter 2`) [container]
      - chapter: Part I., Sect. III. The difference between the removal of Pain and positive Pleasure. (`A Philosophical Enquiry into the Origin of our Ideas of the Sublime and Beautiful/Part I/Chapter 3`) [container]
      - chapter: Part I., Sect. IV. Of Delight and Pleasure, as opposed to each other (`A Philosophical Enquiry into the Origin of our Ideas of the Sublime and Beautiful/Part I/Chapter 4`) [container]
      - chapter: Part I., Sect. V. Joy and Grief (`A Philosophical Enquiry into the Origin of our Ideas of the Sublime and Beautiful/Part I/Chapter 5`) [container]
      - chapter: Part I., Sect. VI. Of the passions which belong to self-preservation. (`A Philosophical Enquiry into the Origin of our Ideas of the Sublime and Beautiful/Part I/Chapter 6`) [container]
      - chapter: Part I., Sect. VII. Of the Sublime. (`A Philosophical Enquiry into the Origin of our Ideas of the Sublime and Beautiful/Part I/Chapter 7`) [container]
    - … 98 more
1. `Report of the Park Board, Portland, Oregon (1903)`: mixed, book, 2 parts, 0 missing, order chain, proofread
    - front: Report of the Park Board, Portland, Oregon (1903)
    - text: Annual report of the Park Board (`Report of the Park Board, Portland, Oregon (1903)/Annual report of the Park Board`) [hierarchy]
    - chapter: Appendix: Olmsted report (`Olmsted report on Portland, Oregon parks`) [container]
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
1. `Works of Michael de Montaigne`: mixed, collection, 9 parts, 1 missing, order scan-volumes, proofread
    - toc: Works of Michael de Montaigne
    - volume: Volume 1 (`Works of Michael de Montaigne/Volume 1`) [container]
      - text: Editor's Advertisement (`Works of Michael de Montaigne (1866)/Editor's Advertisement`) [container]
      - chapter: Preface (`Works of Michael de Montaigne (1866)/Preface`) [container]
      - text: The Life of Michael de Montaigne (`Works of Michael de Montaigne (1866)/The Life of Michael de Montaigne`) [container]
      - text: The Author to the Reader (`Works of Michael de Montaigne (1866)/The Author to the Reader`) [container]
    - chapter: Volume 3 (`Works of Michael de Montaigne/Volume 3`) [container]
    - text: Extracts From the Essays of Montaigne (`Works of Michael de Montaigne (1866)/Extracts From the Essays of Montaigne`) [container]
    - chapter: Volume 4 (`Works of Michael de Montaigne/Volume 4`) [container]
    - chapter: Advertisement for the Works of La Boétie to the Reader (`Works of Michael de Montaigne (1866)/Advertisement for the Works of La Boétie to the Reader`) [container]
1. `The Posthumous Papers of the Pickwick Club (1837)`: mixed, book, 109 parts, 0 missing, order toc-partial, proofread
    - front: The Posthumous Papers of the Pickwick Club (1837)
    - chapter: Chapter 1 (`The Posthumous Papers of the Pickwick Club (1837)/Chapter 1`) [container]
    - chapter: Chapter 2 (`The Posthumous Papers of the Pickwick Club (1837)/Chapter 2`) [container]
    - chapter: Chapter 3 (`The Posthumous Papers of the Pickwick Club (1837)/Chapter 3`) [container]
    - chapter: Chapter 4 (`The Posthumous Papers of the Pickwick Club (1837)/Chapter 4`) [container]
    - chapter: Chapter 5 (`The Posthumous Papers of the Pickwick Club (1837)/Chapter 5`) [container]
    - chapter: Chapter 6 (`The Posthumous Papers of the Pickwick Club (1837)/Chapter 6`) [container]
    - chapter: Chapter 7 (`The Posthumous Papers of the Pickwick Club (1837)/Chapter 7`) [container]
    - chapter: Chapter 8 (`The Posthumous Papers of the Pickwick Club (1837)/Chapter 8`) [container]
    - chapter: Chapter 9 (`The Posthumous Papers of the Pickwick Club (1837)/Chapter 9`) [container]
    - chapter: Chapter 10 (`The Posthumous Papers of the Pickwick Club (1837)/Chapter 10`) [container]
    - chapter: Chapter 11 (`The Posthumous Papers of the Pickwick Club (1837)/Chapter 11`) [container]
    - … 98 more
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

### D: virtual roots (738)

1. `Tribute to Gallaudet`: virtual, collection, 1 parts, 0 missing, order single, unknown
    - text: Laurent Clerc (`Tribute to Gallaudet/Laurent Clerc`)
1. `Van Deursen Family`: virtual, collection, 1 parts, 0 missing, order single, unknown
    - text: Abraham Pietersen (Van Deursen) (`Van Deursen Family/Abraham Pietersen (Van Deursen)`)
1. `Massillon's sermons for all the Sundays and festivals throughout the year`: virtual, book, 2 parts, 0 missing, order chain, proofread
    - chapter: XVI. Septuagesima Sunday (`Massillon's sermons for all the Sundays and festivals throughout the year/XVI. Septuagesima Sunday`)
    - chapter: XVII. Sexagesima Sunday (`Massillon's sermons for all the Sundays and festivals throughout the year/XVII. Sexagesima Sunday`) [hierarchy]
1. `Smart Set (White)`: virtual, collection, 1 parts, 0 missing, order single, unknown
    - text: The Story of Three People (`Smart Set (White)/The Story of Three People`)
1. `Kansas City Times`: virtual, collection, 1 parts, 0 missing, order single, unknown
    - text: Pilot in Red Baron's Circus Dies (`Kansas City Times/1976/Pilot in Red Baron's Circus Dies`)
1. `Poetical works of William Blake (Sampson, 1913)`: virtual, book, 3 parts, 0 missing, order scan, proofread
    - text: The Book of Thel, by William Blake. (`Poetical works of William Blake (Sampson, 1913)/The Book of Thel`)
    - text: A Song of Liberty, by William Blake. (`Poetical works of William Blake (Sampson, 1913)/A Song of Liberty`) [hierarchy]
    - text: All religions are one (`Poetical works of William Blake (Sampson, 1913)/All religions are one`) [hierarchy]
1. `The Examiner (London)`: virtual, collection, 1 parts, 0 missing, order single, validated, excluded no-text-pages
    - text: Ozymandias (`The Examiner (London)/Ozymandias`)
1. `Prairie Flowers`: virtual, book, 1 parts, 0 missing, order single, proofread
    - text: Winter (`Prairie Flowers/Winter`)
1. `Harvard Monthly`: virtual, book, 1 parts, 0 missing, order single, unknown
    - chapter: Volume 36 (`Harvard Monthly/Volume 36`)
1. `Daily Prayer Book: Ha-Siddur ha-Shalem (Birnbaum)`: virtual, book, 1 parts, 0 missing, order single, validated
    - chapter: Introduction (`Daily Prayer Book: Ha-Siddur ha-Shalem (Birnbaum)/Introduction`)
1. `FW`: virtual, collection, 6 parts, 0 missing, order single, unknown
    - volume: Syllabus (`FW/PBS, Inc. v. Dallas`)
      - text: Concurrence Brennan (`FW/PBS, Inc. v. Dallas/Concurrence Brennan`) [hierarchy]
      - text: Concurrence Scalia (`FW/PBS, Inc. v. Dallas/Concurrence Scalia`) [hierarchy]
      - text: Concurrence Stevens (`FW/PBS, Inc. v. Dallas/Concurrence Stevens`) [hierarchy]
      - text: Concurrence White (`FW/PBS, Inc. v. Dallas/Concurrence White`) [hierarchy]
      - text: Opinion of the Court (`FW/PBS, Inc. v. Dallas/Opinion of the Court`) [hierarchy]
1. `To-Day`: virtual, book, 1 parts, 0 missing, order single, unproofread, excluded no-text-pages
    - text: The Agnostic (`To-Day/The Agnostic`)
1. `The Windsor Magazine`: virtual, collection, 7 parts, 0 missing, order natural, unknown
    - text: "And Love Is Still the Lord of All!" (`The Windsor Magazine/'And Love Is Still the Lord of All!'`)
    - text: 'Padlocks!' (`The Windsor Magazine/'Padlocks!'`) [hierarchy]
    - text: 'The Engagement Is Announced...' (`The Windsor Magazine/'The Engagement Is Announced...'`) [hierarchy]
    - text: 'There Are More Things...' (`The Windsor Magazine/'There Are More Things...'`) [hierarchy]
    - text: A Breaker of Hearts (`The Windsor Magazine/A Breaker of Hearts`) [hierarchy]
    - text: A Charming Young Couple (`The Windsor Magazine/A Charming Young Couple`) [hierarchy]
    - text: Peach-Coloured Plumes (`The Windsor Magazine/Peach-Coloured Plumes`) [hierarchy]
1. `A History of the Indian Medical Service, 1600-1913`: virtual, book, 2 parts, 0 missing, order scan, unproofread
    - chapter: Chapter 7 : Early History; Madras and the Coast. (`A History of the Indian Medical Service, 1600-1913/Chapter 7`)
    - chapter: Chapter 13 : THE FOUNDATION OF THE SERVICE (`A History of the Indian Medical Service, 1600-1913/Chapter 13`) [hierarchy]
1. `The Army Almanac`: virtual, collection, 1 parts, 0 missing, order single, unknown
    - text: 1st Infantry Division (`The Army Almanac/1st Infantry Division (United States)`)
1. `Illustrated London News`: virtual, collection, 1 parts, 0 missing, order single, unknown
    - text: Henry Petty-Fitzmaurice, 3rd Marquess of Lansdowne (`Illustrated London News/Henry Petty-Fitzmaurice, 3rd Marquess of Lansdowne`)
1. `Greensburg Daily Tribune`: virtual, collection, 1 parts, 0 missing, order single, unknown
    - text: Famous Fliers Enter Air Tour (`Greensburg Daily Tribune/1931/Famous Fliers Enter Air Tour`)
1. `Report of a Committee Set up to Consider Certain Correspondence Between Sir Henry McMahon and the Sharif of Mecca in 1915 and 1916`: virtual, collection, 1 parts, 0 missing, order single, unknown
    - text: Annex I (`Report of a Committee Set up to Consider Certain Correspondence Between Sir Henry McMahon and the Sharif of Mecca in 1915 and 1916/Annex I`)
1. `Burton's Gentleman's Magazine`: virtual, periodical-issue, 1 parts, 0 missing, order single, proofread
    - text: Morella (`Burton's Gentleman's Magazine/Morella`)
1. `The Philippine Islands, 1493–1898`: virtual, book, 198 parts, 0 missing, order chain, proofread
    - volume: Volume 6: 1583–1588 (`The Philippine Islands, 1493–1898/Volume 6`)
      - chapter: Preface (`The Philippine Islands, 1493–1898/Volume 6/Preface`) [container]
      - text: Documents of 1583–85 (`The Philippine Islands, 1493–1898/Volume 6/Documents of 1583–85`) [container]
      - text: Foundation of the Audiencia of Manila (concluded) (`The Philippine Islands, 1493–1898/Volume 6/Foundation`) [container]
      - text: Two decrees regarding the religious (`The Philippine Islands, 1493–1898/Volume 6/Two decrees`) [container]
      - text: Annual income of the royal exchequer in the Philippines (`The Philippine Islands, 1493–1898/Volume 6/Annual income`) [container]
      - chapter: Letter to Felipe II (1584) (`The Philippine Islands, 1493–1898/Volume 6/Letter to Felipe II (1584)`) [container]
      - chapter: Letter to the archbishop of Mexico (`The Philippine Islands, 1493–1898/Volume 6/Letter to the archbishop of Mexico`) [container]
      - text: Two letters to Felipe II (`The Philippine Islands, 1493–1898/Volume 6/Two letters to Felipe II`) [container]
      - text: History of the great kingdom of China (extracts relating to the Philippines) (`The Philippine Islands, 1493–1898/Volume 6/History of the great kingdom of China`) [container]
      - text: Documents of 1586 (`The Philippine Islands, 1493–1898/Volume 6/Documents of 1586`) [container]
      - text: Memorial to the Council by citizens of the Filipinas Islands (`The Philippine Islands, 1493–1898/Volume 6/Memorial`) [container]
    - … 186 more
1. `To-day`: virtual, collection, 1 parts, 0 missing, order single, unknown
    - text: A brief comic opera (`To-day/A brief comic opera`)
1. `The Poetical Works of Samuel Johnson`: virtual, book, 3 parts, 0 missing, order scan, proofread
    - text: The Winter's Walk (`The Poetical Works of Samuel Johnson/The Winter's Walk`)
    - text: Epitaph on Claude Phillips (`The Poetical Works of Samuel Johnson/Epitaph on Claude Phillips`) [hierarchy]
    - text: Friendship: An Ode (`The Poetical Works of Samuel Johnson/Friendship`) [hierarchy]
1. `Oriental Religions - China`: virtual, book, 2 parts, 0 missing, order chain, unproofread
    - chapter: III. Language (`Oriental Religions - China/2/3`)
    - chapter: IV. Literature (`Oriental Religions - China/2/4`) [hierarchy]
1. `Tales of Terror`: virtual, book, 1 parts, 0 missing, order single, proofread
    - text: The Dance of the Dead (`Tales of Terror/The Dance of the Dead`)
1. `Greensboro Record`: virtual, collection, 1 parts, 0 missing, order single, unknown
    - text: Youth Captures Flight Record (`Greensboro Record/1930/Youth Captures Flight Record`)
1. `Executive Order`: virtual, collection, 2 parts, 0 missing, order single, unknown
    - volume: 1862 (`Executive Order/1862`)
      - text: Executive Order 1 (`Executive Order/1862/000001.0`) [hierarchy]
1. `A Voyage round the World (Bougainville)`: virtual, book, 2 parts, 0 missing, order chain, proofread
    - text: The Translator's Preface (`A Voyage round the World (Bougainville)/Preface`)
    - chapter: Introduction (`A Voyage round the World (Bougainville)/Introduction`) [hierarchy]
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
1. `The Writings of Bret Harte`: virtual, book, 3 parts, 0 missing, order natural, proofread
    - text: Further Language from Truthful James (`The Writings of Bret Harte/Volume 12/Further Language from Truthful James`)
    - text: Her Letter (`The Writings of Bret Harte/Volume 12/Her Letter`) [hierarchy]
    - text: His Answer to "Her Letter" (`The Writings of Bret Harte/Volume 12/His Answer to "Her Letter"`) [hierarchy]
1. `History of the Anglo-Saxons`: virtual, book, 1 parts, 0 missing, order single, unknown
    - chapter: Chapter 4 (`History of the Anglo-Saxons/Book 2/Appendix/Chapter 4`)

### E: subpage texts reached from a disambiguation or versions page (25,313)

1. text “Encouragement” (`Poems (Sharpless)/Encouragement`), part 79 of `Poems (Sharpless)` (book, 124 parts, 124 texts)
1. text “The Broom of Cowdenknowes” (`The Haughs of Crumdel (n.d., Stirling)/The Broom of Cowdenknowes`), part 3 of `The Haughs of Crumdel (n.d., Stirling)` (book, 3 parts, 3 texts)
1. text “Two Poems Bear Same Title” (`Oregonian/1915/February/6/Two Poems Bear Same Title`), part 19 of `Oregonian` (collection, 28 parts, 16 texts)
1. text “The Sleeping Beauty” (`A Sheaf Gleaned in French Fields/The Sleeping Beauty (Louise-Victorine Ackermann)`), part 185 of `A Sheaf Gleaned in French Fields` (book, 197 parts, 195 texts)
1. text “On the Queen’s Picture, given in Exchange for another” (`Poems upon Several Occasions/9`), part 9 of `Poems upon Several Occasions` (book, 97 parts, 83 texts)
1. text “John Was The Prophet Of The Lord” (`Hymns and Spiritual Songs/John Was The Prophet Of The Lord`), part 0 of `Hymns and Spiritual Songs` (collection, 1 parts, 1 texts)
1. text “The Cup” (`Mandragora/The Cup`), part 3 of `Mandragora` (book, 110 parts, 109 texts)
1. text “Lord Gregory” (`Two favourite ballads/Lord Gregory`), part 2 of `Two favourite ballads` (book, 2 parts, 2 texts)
1. text “Stanzas” (`Poems (Charlotte Allen)/Stanzas (Sadness with all its busy train)`), part 64 of `Poems (Charlotte Allen)` (book, 102 parts, 101 texts)
1. text “An Allegory” (`Poems of Charles Baudelaire/An Allegory`), part 4 of `Poems of Charles Baudelaire` (book, 36 parts, 36 texts)
1. text “Christmas” (`The Complete Poems of Paul Laurence Dunbar/Christmas`), part 51 of `The Complete Poems of Paul Laurence Dunbar` (book, 413 parts, 412 texts)
1. text “Pebbles and Shells” (`Pebbles and Shells (Hawkes collection)/Pebbles and Shells (Happy is he who takes life as it seems)`), part 152 of `Pebbles and Shells (Hawkes collection)` (book, 152 parts, 151 texts)
1. text “In May” (`The Complete Poems of Paul Laurence Dunbar/In May`), part 145 of `The Complete Poems of Paul Laurence Dunbar` (book, 413 parts, 412 texts)
1. text “Outward Bound” (`Poems (Craik)/Outward Bound`), part 136 of `Poems (Craik)` (book, 136 parts, 136 texts)
1. text “My Love was Born in Aberdeen” (`Six popular songs (1850-1860)/My love was born in Aberdeen`), part 5 of `Six popular songs (1850-1860)` (book, 6 parts, 6 texts)
1. text “The Sailor's Adieu” (`The Braes o' Bowhither (1817, Edinburgh)/The Sailor's Adieu`), part 2 of `The Braes o' Bowhither (1817, Edinburgh)` (book, 4 parts, 4 texts)
1. text “Verses” (`Poems (Hornblower)/Verses (In early mom, ere yet the light)`), part 79 of `Poems (Hornblower)` (book, 134 parts, 134 texts)
1. text “The Wild Swans at Coole” (`The Wild Swans at Coole (Collection)/The Wild Swans at Coole`), part 1 of `The Wild Swans at Coole (Collection)` (book, 42 parts, 42 texts)
1. text “The Text: Prologue” (`Romeo and Juliet (1917) Yale/Text/Prologue`), part 2 of `Romeo and Juliet (1917) Yale` (book, 13 parts, 6 texts)
1. text “The King of Arragon's Lament for his Brother” (`Songs of the Affections, with Other Poems/The King of Arragon's Lament for his Brother`), part 9 of `Songs of the Affections, with Other Poems` (book, 62 parts, 62 texts)
1. text “Elegiac Stanzas” (`Fugitive Poetry. 1600–1878/Elegiac Stanzas`), part 554 of `Fugitive Poetry. 1600–1878` (book, 643 parts, 633 texts)
1. text “⁠ Song of a Soldier” (`Bohemian legends and other poems/Song of a Soldier`), part 49 of `Bohemian legends and other poems` (book, 86 parts, 84 texts)
1. text “The Marriage Act” (`Crazy Jane (1820)/The Marriage Act`), part 6 of `Crazy Jane (1820)` (book, 6 parts, 6 texts)
1. text “Old Adam” (`Old Adam the father of us all/Old Adam`), part 1 of `Old Adam the father of us all` (book, 2 parts, 2 texts)
1. text “The Wish” (`The Poems of Charles Fenno Hoffman/The Wish`), part 39 of `The Poems of Charles Fenno Hoffman` (book, 98 parts, 95 texts)
1. text “Bonny Wood of Craigielee” (`The Melodist/Bonny Wood of Craigielee`), part 20 of `The Melodist` (book, 39 parts, 39 texts)
1. text “To” (`The Poetical Works of Robert Burns/To — (3)`), part 207 of `The Poetical Works of Robert Burns` (book, 508 parts, 501 texts)
1. text “Scottish Whisky” (`Young Lochinvar (2)/Scottish Whisky`), part 3 of `Young Lochinvar (2)` (book, 6 parts, 6 texts)
1. text “Retirement” (`The Complete Poems of Emily Brontë/Retirement`), part 122 of `The Complete Poems of Emily Brontë` (book, 182 parts, 182 texts)
1. text “Leonora” (`Poems of Letitia Elizabeth Landon (L. E. L.) in Heath’s Book of Beauty, 1833/Leonora`), part 3 of `Poems of Letitia Elizabeth Landon (L. E. L.) in Heath’s Book of Beauty, 1833` (book, 11 parts, 10 texts)

### all searchable subpage texts (chapter titles that the label patterns miss show up here) (429,663)

1. text “Folks, Homer” (`The New International Encyclopædia/Folks, Homer`), part 4679 of `The New International Encyclopædia` (book, 10,866 parts, 10,864 texts)
1. text “Opinion of the Court” (`Morgan v. Beloit City and Town/Opinion of the Court`), part 1 of `Morgan v. Beloit City and Town` (collection, 1 parts, 1 texts)
1. text “The fairest, brightest” (`Poems (Wordsworth, 1815)/Volume 2/The fairest, brightest`), part 132 of `Poems (Wordsworth, 1815)` (collection, 259 parts, 250 texts)
1. text “as at 1 April 1972” (`Suppression of Communism Act, 1950/1972-04-01`), part 1 of `Suppression of Communism Act, 1950` (book, 18 parts, 17 texts)
1. text “Lyrics” (`The poems of Richard Watson Gilder/Lyrics`), part 3 of `The poems of Richard Watson Gilder` (book, 12 parts, 10 texts)
1. text “That whatever moves and acts, does so mechanically and necessarily.” (`Cato's Letters/Letter 116`), part 117 of `Cato's Letters` (collection, 145 parts, 137 texts)
1. text “Massachusetts to Virginia.” (`State Documents on Federal Relations/9`), part 13 of `State Documents on Federal Relations` (book, 56 parts, 53 texts)
1. text “Bryant, William Frederick” (`Alumni Oxonienses: the Members of the University of Oxford, 1715-1886/Bryant, William Frederick`), part 7781 of `Alumni Oxonienses: the Members of the University of Oxford, 1715-1886` (reference, 63,225 parts, 63,216 texts)
1. text “Robert Ciboule” (`Catholic Encyclopedia (1913)/Robert Ciboule`), part 9022 of `Catholic Encyclopedia (1913)` (reference, 11,629 parts, 11,592 texts)
1. text “Ostervald, Jean Frédéric” (`1911 Encyclopædia Britannica/Ostervald, Jean Frédéric`), part 25892 of `1911 Encyclopædia Britannica` (reference, 37,420 parts, 37,278 texts)
1. text “A Former Life” (`Poems of Charles Baudelaire/A Former Life`), part 1 of `Poems of Charles Baudelaire` (book, 36 parts, 36 texts)
1. text “Dalrymple, Hew (1652-1737)” (`Dictionary of National Biography, 1885-1900/Dalrymple, Hew (1652-1737)`), part 6538 of `Dictionary of National Biography, 1885-1900` (reference, 28,089 parts, 28,081 texts)
1. text “Hunte, Richard le (3)” (`Alumni Oxonienses: the Members of the University of Oxford, 1715-1886/Hunte, Richard le (3)`), part 29112 of `Alumni Oxonienses: the Members of the University of Oxford, 1715-1886` (reference, 63,225 parts, 63,216 texts)
1. text “Vilna (town)” (`1911 Encyclopædia Britannica/Vilna (town)`), part 35351 of `1911 Encyclopædia Britannica` (reference, 37,420 parts, 37,278 texts)
1. text “Bliss, Cornelius Newton” (`1911 Encyclopædia Britannica/Bliss, Cornelius Newton`), part 4911 of `1911 Encyclopædia Britannica` (reference, 37,420 parts, 37,278 texts)
1. text “Holford, Robert Stayner” (`Alumni Oxonienses: the Members of the University of Oxford, 1715-1886/Holford, Robert Stayner`), part 27562 of `Alumni Oxonienses: the Members of the University of Oxford, 1715-1886` (reference, 63,225 parts, 63,216 texts)
1. text “Miscellany” (`Littell's Living Age/Volume 135/Issue 1748/Miscellany`), part 1059 of `Littell's Living Age` (collection, 1,842 parts, 1,160 texts)
1. text “Novomoskovsk” (`1911 Encyclopædia Britannica/Novomoskovsk`), part 25259 of `1911 Encyclopædia Britannica` (reference, 37,420 parts, 37,278 texts)
1. text “Sigismund Albicus” (`Catholic Encyclopedia (1913)/Sigismund Albicus`), part 9378 of `Catholic Encyclopedia (1913)` (reference, 11,629 parts, 11,592 texts)
1. text “Largo e Mesto” (`Poems (Henley)/Largo e Mesto`), part 105 of `Poems (Henley)` (book, 133 parts, 131 texts)
1. text “Clutterbuck, Edmund Lewis” (`Alumni Oxonienses: the Members of the University of Oxford, 1715-1886/Clutterbuck, Edmund Lewis`), part 11274 of `Alumni Oxonienses: the Members of the University of Oxford, 1715-1886` (reference, 63,225 parts, 63,216 texts)
1. text “Quartley, Henry (1)” (`Alumni Oxonienses: the Members of the University of Oxford, 1715-1886/Quartley, Henry (1)`), part 46109 of `Alumni Oxonienses: the Members of the University of Oxford, 1715-1886` (reference, 63,225 parts, 63,216 texts)
1. text “ABE, Taizo” (`Who's Who in the Far East/ABE, Taizo`), part 1 of `Who's Who in the Far East` (collection, 2,182 parts, 2,181 texts)
1. text “Lesser Celandine” (`The New International Encyclopædia/Lesser Celandine`), part 6816 of `The New International Encyclopædia` (book, 10,866 parts, 10,864 texts)
1. text “'Bearsden-hall'” (`Notes and Queries/Series 11/Volume 8/Number 184/'Bearsden-hall'`), part 158 of `Notes and Queries` (collection, 225 parts, 182 texts)
1. text “Jenkins, Thomas Lionel” (`Men-at-the-Bar/Jenkins, Thomas Lionel`), part 3520 of `Men-at-the-Bar` (book, 7,448 parts, 7,447 texts)
1. text “Dingwall, John Duff” (`Alumni Oxonienses: the Members of the University of Oxford, 1715-1886/Dingwall, John Duff`), part 15646 of `Alumni Oxonienses: the Members of the University of Oxford, 1715-1886` (reference, 63,225 parts, 63,216 texts)
1. text “Keate, Robert” (`Dictionary of National Biography, 1885-1900/Keate, Robert`), part 14169 of `Dictionary of National Biography, 1885-1900` (reference, 28,089 parts, 28,081 texts)
1. text “Tudor, Owen Davies” (`Men-at-the-Bar/Tudor, Owen Davies`), part 6731 of `Men-at-the-Bar` (book, 7,448 parts, 7,447 texts)
1. text “Athanasia” (`The Writings of Oscar Wilde/Volume 1/Athanasia`), part 9 of `The Writings of Oscar Wilde` (book, 78 parts, 77 texts)

### C: oversized (399)

1. `Scientific Papers of Josiah Willard Gibbs, Volume 1`: subpages, book, 11 parts, 0 missing, order chain, proofread
    - front: Scientific Papers of Josiah Willard Gibbs, Volume 1
    - text: Biographical Sketch (`Scientific Papers of Josiah Willard Gibbs, Volume 1/Biographical Sketch`) [container]
    - text: Graphical Methods in the Thermodynamics of Fluids (`Scientific Papers of Josiah Willard Gibbs, Volume 1/Chapter I`) [container]
    - text: A Method of Geometrical Representation of the Thermodynamic Properties of Substances by means of Surfaces (`Scientific Papers of Josiah Willard Gibbs, Volume 1/Chapter II`) [container]
    - text: On the Equilibrium of Heterogeneous Substances, Part 1 (`Scientific Papers of Josiah Willard Gibbs, Volume 1/Chapter IIIa`) [container]
    - text: On the Equilibrium of Heterogeneous Substances, Part 2 (`Scientific Papers of Josiah Willard Gibbs, Volume 1/Chapter IIIb`) [container]
    - text: Abstract of the "Equilibrium of Heterogeneous Substances" (`Scientific Papers of Josiah Willard Gibbs, Volume 1/Chapter IV`) [container]
    - text: On the Vapor-Densities of Peroxide of Nitrogen, Formic Acid, Acetic Acid, and Perchloride of Phosphorus (`Scientific Papers of Josiah Willard Gibbs, Volume 1/Chapter V`) [container]
    - text: On an Alleged Exception to the Second Law of Thermodynamics (`Scientific Papers of Josiah Willard Gibbs, Volume 1/Chapter VI`) [container]
    - text: Electrochemical Thermodynamics. Two Letters to the Secretary of the Electrolysis Committee of the British Association for the Advancement of Science (`Scientific Papers of Josiah Willard Gibbs, Volume 1/Chapter VII`) [container]
    - text: Semi-Permeable Films and Osmotic Pressure (`Scientific Papers of Josiah Willard Gibbs, Volume 1/Chapter VIII`) [container]
    - text: Unpublished Fragments of a Supplement to the "Equilibrium of Heterogeneous Substances" (`Scientific Papers of Josiah Willard Gibbs, Volume 1/Chapter IX`) [container]
1. `A Graduated English-Welsh Spelling-Book`: subpages, book, 1 parts, 0 missing, order single, unknown
    - front: A Graduated English-Welsh Spelling-Book
    - chapter: Appendix (`A Graduated English-Welsh Spelling-Book/Appendix`) [container]
1. `Rob Roy (Scott)`: subpages, book, 6 parts, 11 missing, order chain, unproofread
    - toc: Rob Roy (Scott)
    - volume: Volume I. (`Rob Roy (Scott)/Volume 1`) [hierarchy]
      - chapter: Advertisement (`Rob Roy (Scott)/Volume 1/Advertisement`) [hierarchy]
      - chapter: Chapter I. (`Rob Roy (Scott)/Volume 1/Chapter 1`) [hierarchy]
      - chapter: Chapter II. (`Rob Roy (Scott)/Volume 1/Chapter 2`) [hierarchy]
    - chapter: Volume II. (`Rob Roy (Scott)/Volume 2`) [hierarchy]
    - chapter: Volume III. (`Rob Roy (Scott)/Volume 3`) [hierarchy]
1. `The Collected Works of Henrik Ibsen`: mixed, book, 55 parts, 9 missing, order toc, unproofread
    - toc: The Collected Works of Henrik Ibsen
    - volume: Volume 1 (`The Collected Works of Henrik Ibsen/Volume 1`) [container]
      - volume: LADY INGER OF ÖSTRÅT (`The Collected Works of Henrik Ibsen/Volume 1/Lady Inger of Östråt`) [container]
        - chapter: Introduction (`The Collected Works of Henrik Ibsen/Volume 1/Lady Inger of Östråt/Introduction`) [container]
        - chapter: Act 1 (`The Collected Works of Henrik Ibsen/Volume 1/Lady Inger of Östråt/Act 1`) [container]
        - chapter: Act 2 (`The Collected Works of Henrik Ibsen/Volume 1/Lady Inger of Östråt/Act 2`) [container]
        - chapter: Act 3 (`The Collected Works of Henrik Ibsen/Volume 1/Lady Inger of Östråt/Act 3`) [container]
        - chapter: Act 4 (`The Collected Works of Henrik Ibsen/Volume 1/Lady Inger of Östråt/Act 4`) [container]
        - chapter: Act 5 (`The Collected Works of Henrik Ibsen/Volume 1/Lady Inger of Östråt/Act 5`) [container]
      - volume: THE FEAST AT SOLHOUG (`The Collected Works of Henrik Ibsen/Volume 1/The Feast at Solhoug`) [container]
        - chapter: Introduction (`The Collected Works of Henrik Ibsen/Volume 1/The Feast at Solhoug/Introduction`) [container]
        - text: Author’s Preface (`The Collected Works of Henrik Ibsen/Volume 1/The Feast at Solhoug/Author’s Preface`) [container]
    - … 44 more
1. `Black and White: Land, Labor, and Politics in the South`: single, single, 1 parts, 0 missing, order –, unknown
    - text: Black and White: Land, Labor, and Politics in the South
1. `Dictionary of Spoken Russian`: subpages, book, 38 parts, 0 missing, order scan, proofread
    - front: Dictionary of Spoken Russian
    - text: Foreword (`Dictionary of Spoken Russian/Foreword`) [hierarchy]
    - text: English-Russian (`Dictionary of Spoken Russian/English-Russian`) [hierarchy]
    - volume: Russian-English (`Dictionary of Spoken Russian/Russian-English`) [hierarchy]
      - text: Grammatical Introduction (`Dictionary of Spoken Russian/Russian-English/Grammatical Introduction`) [hierarchy]
      - text: §1. Sounds (`Dictionary of Spoken Russian/Russian-English/Section 1`) [container]
      - text: §2. Russian Writing (`Dictionary of Spoken Russian/Russian-English/Section 2`) [container]
      - text: §3. Alternation of Sounds (`Dictionary of Spoken Russian/Russian-English/Section 3`) [container]
      - text: §4. Alternations of Sound and Spelling (`Dictionary of Spoken Russian/Russian-English/Section 4`) [container]
      - text: §5. Inflection (`Dictionary of Spoken Russian/Russian-English/Section 5`) [container]
      - text: §6. Nouns (`Dictionary of Spoken Russian/Russian-English/Section 6`) [container]
      - text: §7. Nouns with пол- (`Dictionary of Spoken Russian/Russian-English/Section 7`) [container]
    - … 27 more
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
1. `Journal of a Voyage to Greenland, in the Year 1821`: single, book, 1 parts, 0 missing, order –, validated
    - text: Journal of a Voyage to Greenland, in the Year 1821
1. `Confederate Military History`: subpages, book, 79 parts, 54 missing, order toc, proofread
    - toc: Confederate Military History
    - volume: Volume I (`Confederate Military History/Volume 1`) [container]
      - chapter: Part 1 (`Confederate Military History/Volume 1/Part 1`) [container]
      - volume: WILLIAM R. GARRETT. (`Confederate Military History/Volume 1/Part 2`) [container]
        - chapter: Chapter 1 (`Confederate Military History/Volume 1/Part 2/Chapter 1`) [container]
        - chapter: Chapter 2 (`Confederate Military History/Volume 1/Part 2/Chapter 2`) [container]
        - chapter: Chapter 3 (`Confederate Military History/Volume 1/Part 2/Chapter 3`) [container]
        - chapter: Chapter 4 (`Confederate Military History/Volume 1/Part 2/Chapter 4`) [container]
    - volume: Volume II (`Confederate Military History/Volume 2`) [container]
      - chapter: Chapter 1 (`Confederate Military History/Volume 2/Chapter 1`) [container]
    - volume: Volume 3 (`Confederate Military History/Volume 3`) [container]
      - chapter: Chapter 1 (`Confederate Military History/Volume 3/Chapter 1`) [container]
    - … 68 more
1. `Constitution of Nigeria (1999)`: single, single, 1 parts, 0 missing, order –, unknown
    - text: Constitution of Nigeria (1999)
1. `Constitution of Yugoslavia (1974)`: single, single, 1 parts, 0 missing, order –, unknown
    - text: Constitution of Yugoslavia (1974)
1. `La Sorcière, The Witch of the Middle Ages`: mixed, book, 7 parts, 0 missing, order scan, proofread
    - front: La Sorcière, The Witch of the Middle Ages
    - chapter: Introduction (`La Sorcière, The Witch of the Middle Ages./Introduction`) [container]
    - chapter: Chapter 1 (`La Sorcière, The Witch of the Middle Ages./Book 1/Chapter 1`) [container]
    - chapter: BOOK I (`La Sorcière, The Witch of the Middle Ages./BOOK I`) [container]
    - chapter: BOOK 2 (`La Sorcière, The Witch of the Middle Ages./Chapter 1`) [container]
    - chapter: Book 2 (`La Sorcière, The Witch of the Middle Ages/Book 2`) [container]
    - chapter: BOOK I. (`La Sorcière, The Witch of the Middle Ages/BOOK I.`) [container]
    - chapter: BOOK II. (`La Sorcière, The Witch of the Middle Ages/BOOK II.`) [container]
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
1. `Past, Future and the Problem of Communication in the Work of V V Khlebnikov`: single, document, 1 parts, 0 missing, order –, unproofread
    - text: Past, Future and the Problem of Communication in the Work of V V Khlebnikov
1. `Abraham Lincoln: A History`: subpages, book, 1 parts, 0 missing, order single, unknown
    - toc: Abraham Lincoln: A History
    - chapter: Volume II (`Abraham Lincoln: A History/Volume 2`) [container]
1. `Legal Tender Cases (79 U.S. 457)`: subpages, collection, 1 parts, 0 missing, order single, unknown
    - front: Legal Tender Cases (79 U.S. 457)
    - text: Opinion of the Court (`Legal Tender Cases (79 U.S. 457)/Opinion of the Court`) [container]
1. `Marathon speech against ObamaCare`: single, single, 1 parts, 0 missing, order –, unknown
    - text: Marathon speech against ObamaCare
1. `Living My Life`: subpages, book, 2 parts, 0 missing, order chain, unknown
    - toc: Living My Life
    - chapter: Volume 1 (`Living My Life/Volume 1`) [container]
    - chapter: Volume 2 (`Living My Life/Volume 2`) [container]
1. `The Variation of Animals and Plants under Domestication`: subpages, book, 30 parts, 0 missing, order chain, unknown
    - toc: The Variation of Animals and Plants under Domestication
    - chapter: Volume I. Introduction (`The Variation of Animals and Plants under Domestication/Introduction`) [container]
    - chapter: Volume I. Chapter I (`The Variation of Animals and Plants under Domestication/I`) [container]
    - chapter: Volume I. Chapter II (`The Variation of Animals and Plants under Domestication/II`) [container]
    - chapter: Volume I. Chapter III (`The Variation of Animals and Plants under Domestication/III`) [container]
    - chapter: Volume I. Chapter IV (`The Variation of Animals and Plants under Domestication/IV`) [container]
    - chapter: Volume I. Chapter V (`The Variation of Animals and Plants under Domestication/V`) [container]
    - chapter: Chapter VI (`The Variation of Animals and Plants under Domestication/VI`) [container]
    - chapter: Volume I. Chapter VII (`The Variation of Animals and Plants under Domestication/VII`) [container]
    - chapter: Volume I. Chapter VIII (`The Variation of Animals and Plants under Domestication/VIII`) [container]
    - chapter: Volume I. Chapter IX (`The Variation of Animals and Plants under Domestication/IX`) [container]
    - chapter: Volume I. Chapter X (`The Variation of Animals and Plants under Domestication/X`) [container]
    - … 19 more
1. `A Letter to Grover Cleveland, on His False Inaugural Address, The Usurpations and Crimes of Lawmakers and Judges, and the Consequent Poverty, Ignorance, and Servitude of the People`: single, single, 1 parts, 0 missing, order –, unknown
    - text: A Letter to Grover Cleveland, on His False Inaugural Address, The Usurpations and Crimes of Lawmakers and Judges, and the Consequent Poverty, Ignorance, and Servitude of the People
1. `The Education of Uncle Paul`: single, single, 1 parts, 0 missing, order –, unknown
    - text: The Education of Uncle Paul
1. `Bible (Douay-Rheims, Challoner)`: subpages, book, 74 parts, 0 missing, order toc-partial, unproofread, excluded index-faulty
    - front: Bible (Douay-Rheims, Challoner)
    - text: The Book of Genesis (`Bible (Douay-Rheims, Challoner)/Genesis`) [hierarchy]
    - text: The Book of Exodus (`Bible (Douay-Rheims, Challoner)/Exodus`) [hierarchy]
    - text: The Book of Leviticus (`Bible (Douay-Rheims, Challoner)/Leviticus`) [hierarchy]
    - text: The Book of Numbers (`Bible (Douay-Rheims, Challoner)/Numbers`) [hierarchy]
    - text: The Book of Deuteronomy (`Bible (Douay-Rheims, Challoner)/Deuteronomy`) [hierarchy]
    - text: The Book of Josue (`Bible (Douay-Rheims, Challoner)/Josue`) [hierarchy]
    - text: The Book of Judges (`Bible (Douay-Rheims, Challoner)/Judges`) [hierarchy]
    - text: The Book of Ruth (`Bible (Douay-Rheims, Challoner)/Ruth`) [hierarchy]
    - text: The First Book of Kings (`Bible (Douay-Rheims, Challoner)/3 Kings`) [hierarchy]
    - text: The Second Book of Kings (`Bible (Douay-Rheims, Challoner)/4 Kings`) [hierarchy]
    - text: The First Book of Paralipomenon (`Bible (Douay-Rheims, Challoner)/1 Chronicles`) [hierarchy]
    - … 63 more
1. `Works of John C. Calhoun`: virtual, book, 2 parts, 0 missing, order scan, unproofread
    - text: Disquisition on Government (`Works of John C. Calhoun/Disquisition on Government`)
    - text: A Discourse on the Constitution and Government of the United States (`Works of John C. Calhoun/A Discourse on the Constitution and Government of the United States`) [hierarchy]
1. `Judgement of IMT at Nuremberg`: single, single, 1 parts, 0 missing, order –, unknown
    - text: Judgement of IMT at Nuremberg
1. `The Catholic Dogma: Extra Ecclesiam Nullus Omnino Salvatur`: subpages, collection, 7 parts, 0 missing, order chain, unknown
    - front: The Catholic Dogma: Extra Ecclesiam Nullus Omnino Salvatur
    - chapter: Introduction (`The Catholic Dogma: Extra Ecclesiam Nullus Omnino Salvatur/Chapter I`) [container]
    - text: The Infallible and Only True Guide to Heaven (`The Catholic Dogma: Extra Ecclesiam Nullus Omnino Salvatur/Chapter II`) [container]
    - text: The Great Revolt Against Christ (`The Catholic Dogma: Extra Ecclesiam Nullus Omnino Salvatur/Chapter III`) [container]
    - text: Bishop Coxe's Dishonesty (`The Catholic Dogma: Extra Ecclesiam Nullus Omnino Salvatur/Chapter IV`) [container]
    - volume: Introduction: Refutation of the False Assertions of Reverends S. O. Cronin and Young (`The Catholic Dogma: Extra Ecclesiam Nullus Omnino Salvatur/Chapter V`) [container]
      - chapter: Part I. There is No Salvation Out of the Roman Catholic Church for those who die without being united to her (`The Catholic Dogma: Extra Ecclesiam Nullus Omnino Salvatur/Chapter V/Part 1`) [container]
      - text: Of Those Heretics who are not guilty of the sin of Heresy (`The Catholic Dogma: Extra Ecclesiam Nullus Omnino Salvatur/Chapter V/Part 2`) [container]
1. `Korean Air Flight 801 investigation`: virtual, collection, 14 parts, 0 missing, order natural, unknown
    - text: Abstract (`Korean Air Flight 801 investigation/Abstract`)
    - text: Biographies KAL Flight 801 Public Hearing (`Korean Air Flight 801 investigation/Bios`) [hierarchy]
    - text: PUBLIC HEARING CONTACTS (`Korean Air Flight 801 investigation/Contacts`) [hierarchy]
    - text: Public Docket (`Korean Air Flight 801 investigation/Docket`) [hierarchy]
    - text: PUBLIC HEARING EXHIBIT LIST (`Korean Air Flight 801 investigation/Exhibits`) [hierarchy]
    - text: Opening Statement of Gregory A. Feith Investigator-In-Charge (`Korean Air Flight 801 investigation/IIC`) [hierarchy]
    - text: March 24, 1998 meeting transcript (`Korean Air Flight 801 investigation/March 24`) [hierarchy]
    - text: March 25, 1998 meeting transcript (`Korean Air Flight 801 investigation/March 25`) [hierarchy]
    - text: March 26, 1998 meeting transcript (`Korean Air Flight 801 investigation/March 26`) [hierarchy]
    - text: Public Hearing Opening Statement by Honorable Robert T. Francis (`Korean Air Flight 801 investigation/Opening Statement`) [hierarchy]
    - text: FOR IMMEDIATE RELEASE: January 7, 1998 SB 98-01 NTSB TO HOLD PUBLIC HEARING, MARCH 24-26, IN HONOLULU ON CRASH OF KOREAN AIRLINES B-747 (`Korean Air Flight 801 investigation/Pressrel`) [hierarchy]
    - text: NTSB BOARD MEETING ON KAL FLIGHT 801 CRASH INTO MOUNTAINSIDE IN GUAM; 228 DIED (`Korean Air Flight 801 investigation/Pressrel0`) [hierarchy]
    - … 2 more
1. `Nicholson v. Williams`: single, single, 1 parts, 0 missing, order –, unknown
    - text: Nicholson v. Williams
1. `The Afro-American Press and Its Editors`: subpages, book, 4 parts, 0 missing, order chain, proofread
    - front: The Afro-American Press and Its Editors
    - chapter: Part 1 (`The Afro-American Press and Its Editors/Part 1`) [container]
    - chapter: Part 2 (`The Afro-American Press and Its Editors/Part 2`) [container]
    - chapter: Index (`The Afro-American Press and Its Editors/Index`) [container]
    - chapter: Appendix (`The Afro-American Press and Its Editors/Appendix`) [container]
1. `Korean Air Flight 801 - Aircraft Accident Report (NTSB)`: subpages, document, 9 parts, 0 missing, order scan, proofread
    - front: Korean Air Flight 801 - Aircraft Accident Report (NTSB)
    - text: Abbreviations (`Korean Air Flight 801 - Aircraft Accident Report (NTSB)/Abbreviations`) [container]
    - text: Executive Summary (`Korean Air Flight 801 - Aircraft Accident Report (NTSB)/Executive Summary`) [container]
    - text: Factual Information (`Korean Air Flight 801 - Aircraft Accident Report (NTSB)/Factual Information`) [container]
    - text: Analysis (`Korean Air Flight 801 - Aircraft Accident Report (NTSB)/Analysis`) [container]
    - text: Conclusions (`Korean Air Flight 801 - Aircraft Accident Report (NTSB)/Conclusions`) [container]
    - text: Recommendations (`Korean Air Flight 801 - Aircraft Accident Report (NTSB)/Recommendations`) [container]
    - text: Investigation and Hearing (`Korean Air Flight 801 - Aircraft Accident Report (NTSB)/Investigation and Hearing`) [container]
    - text: Cockpit Voice Recorder Transcript (`Korean Air Flight 801 - Aircraft Accident Report (NTSB)/Cockpit Voice Recorder Transcript`) [container]
    - text: Flight Safety Foundation Study Recommendations (`Korean Air Flight 801 - Aircraft Accident Report (NTSB)/Flight Safety Foundation Study Recommendations`) [container]
1. `Psychopathia Sexualis`: subpages, book, 9 parts, 0 missing, order chain, proofread
    - front: Psychopathia Sexualis
    - chapter: Preface to the First Edition (`Psychopathia Sexualis/Preface to the First Edition`) [container]
    - text: Translator's Preface (`Psychopathia Sexualis/Translator's Preface`) [container]
    - chapter: Chapter 1 (`Psychopathia Sexualis/Chapter 1`) [hierarchy]
    - chapter: Chapter 2 (`Psychopathia Sexualis/Chapter 2`) [hierarchy]
    - chapter: Chapter 3 (`Psychopathia Sexualis/Chapter 3`) [hierarchy]
    - chapter: Chapter 4 (`Psychopathia Sexualis/Chapter 4`) [hierarchy]
    - chapter: Chapter 5 (`Psychopathia Sexualis/Chapter 5`) [hierarchy]
    - chapter: Index (`Psychopathia Sexualis/Index`) [hierarchy]
    - text: Catalogue (`Psychopathia Sexualis/Catalogue`) [hierarchy]

### G: reference works (30)

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
1. `Encyclopædia Britannica, Ninth Edition`: subpages, reference, 3,435 parts, 10 missing, order natural, unproofread
    - toc: Encyclopædia Britannica, Ninth Edition
    - chapter: C (`Encyclopædia Britannica, Ninth Edition/C`) [hierarchy]
    - chapter: M (`Encyclopædia Britannica, Ninth Edition/M`) [hierarchy]
    - text: A (`Encyclopædia Britannica, Ninth Edition/A`) [hierarchy]
    - text: A'Beckett, Gilbert Abbott (`Encyclopædia Britannica, Ninth Edition/A'Beckett, Gilbert Abbott`) [hierarchy]
    - text: Aa (`Encyclopædia Britannica, Ninth Edition/Aa`) [hierarchy]
    - text: Aachen (`Encyclopædia Britannica, Ninth Edition/Aachen`) [hierarchy]
    - text: Aalborg (`Encyclopædia Britannica, Ninth Edition/Aalborg`) [hierarchy]
    - text: Aalen (`Encyclopædia Britannica, Ninth Edition/Aalen`) [hierarchy]
    - text: Aar (`Encyclopædia Britannica, Ninth Edition/Aar`) [hierarchy]
    - text: Aarau (`Encyclopædia Britannica, Ninth Edition/Aarau`) [hierarchy]
    - text: Aard-vark (`Encyclopædia Britannica, Ninth Edition/Aard-vark`) [hierarchy]
    - … 3,424 more
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
1. `Foclóir Gaeḋilge agus Béarla`: virtual, reference, 5 parts, 0 missing, order chain, proofread
    - text: Council's Preface (`Foclóir Gaeḋilge agus Béarla/Council's Preface`)
    - text: Editor's Preface (`Foclóir Gaeḋilge agus Béarla/Editor's Preface`) [hierarchy]
    - text: Abbreviations (`Foclóir Gaeḋilge agus Béarla/Abbreviations`) [hierarchy]
    - text: A (`Foclóir Gaeḋilge agus Béarla/A`) [hierarchy]
    - text: B (`Foclóir Gaeḋilge agus Béarla/B`) [hierarchy]
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
1. `Collier's New Encyclopedia (1921)`: subpages, reference, 2,368 parts, 368 missing, order natural, proofread
    - toc: Collier's New Encyclopedia (1921)
    - chapter: M (`Collier's New Encyclopedia (1921)/M`) [hierarchy]
    - text: A (`Collier's New Encyclopedia (1921)/A`) [hierarchy]
    - text: Aachen (`Collier's New Encyclopedia (1921)/Aachen`) [hierarchy]
    - text: Aalborg (`Collier's New Encyclopedia (1921)/Aalborg`) [hierarchy]
    - text: Aar (`Collier's New Encyclopedia (1921)/Aar`) [hierarchy]
    - text: Aarau (`Collier's New Encyclopedia (1921)/Aarau`) [hierarchy]
    - text: Aard-vark (`Collier's New Encyclopedia (1921)/Aard-vark`) [hierarchy]
    - text: Aard-wolf (`Collier's New Encyclopedia (1921)/Aard-wolf`) [hierarchy]
    - text: Aargau (`Collier's New Encyclopedia (1921)/Aargau`) [hierarchy]
    - text: Aarhuus (city) (`Collier's New Encyclopedia (1921)/Aarhuus (city)`) [hierarchy]
    - text: Aarhuus (district) (`Collier's New Encyclopedia (1921)/Aarhuus (district)`) [hierarchy]
    - … 2,357 more
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
1. `A Dictionary of Slang, Jargon & Cant`: subpages, reference, 4 parts, 11 missing, order single, proofread
    - front: A Dictionary of Slang, Jargon & Cant
    - volume: Table of Contents (`A Dictionary of Slang, Jargon & Cant/TOC`) [container]
      - chapter: Preface (`A Dictionary of Slang, Jargon & Cant/Preface`) [container]
      - text: A Brief History of English Slang (`A Dictionary of Slang, Jargon & Cant/History`) [container]
      - text: Contributors (`A Dictionary of Slang, Jargon & Cant/Contributors`) [container]
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
1. `Dictionary of the Foochow Dialect`: subpages, reference, 3 parts, 17 missing, order toc, unproofread
    - front: Dictionary of the Foochow Dialect
    - text: A (`Dictionary of the Foochow Dialect/A`) [container]
    - text: List of the 214 radicals (`Dictionary of the Foochow Dialect/List of the 214 radicals`) [container]
    - chapter: Index of Characters (`Dictionary of the Foochow Dialect/Index of Characters`) [container]
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

### H: deep nesting (311)

1. `The Complete Works of Swami Vivekananda`: mixed, collection, 1,371 parts, 0 missing, order toc-partial, unproofread
    - toc: The Complete Works of Swami Vivekananda
    - volume: Volume 1, Addresses at The Parliament of Religions (`The Complete Works of Swami Vivekananda/Volume 1/Addresses at The Parliament of Religions`) [container]
      - text: Response to Welcome (`The Complete Works of Swami Vivekananda/Volume 1/Addresses at The Parliament of Religions/Response to Welcome`) [hierarchy]
      - text: Why we disagree (`The Complete Works of Swami Vivekananda/Volume 1/Addresses at The Parliament of Religions/Why we disagree`) [hierarchy]
      - text: Paper on Hinduism (`The Complete Works of Swami Vivekananda/Volume 1/Addresses at The Parliament of Religions/Paper on Hinduism`) [hierarchy]
      - text: Religion not the Crying need of India (`The Complete Works of Swami Vivekananda/Volume 1/Addresses at The Parliament of Religions/Religion not the Crying need of India`) [hierarchy]
      - text: Buddhism, the Fulfillment of Hinduism (`The Complete Works of Swami Vivekananda/Volume 1/Addresses at The Parliament of Religions/Buddhism, the Fulfilment of Hinduism`) [hierarchy]
      - text: Address at the Final Session (`The Complete Works of Swami Vivekananda/Volume 1/Addresses at The Parliament of Religions/Address at the Final Session`) [hierarchy]
    - volume: Volume 1, Karma Yoga (`The Complete Works of Swami Vivekananda/Volume 1/Karma-Yoga`) [container]
      - text: Karma in its Effect on Character (`The Complete Works of Swami Vivekananda/Volume 1/Karma-Yoga/Karma in its Effect on Character`) [container]
      - text: Each is great in his own place (`The Complete Works of Swami Vivekananda/Volume 1/Karma-Yoga/Each is great in his own place`) [container]
      - text: The Secret of Work (`The Complete Works of Swami Vivekananda/Volume 1/Karma-Yoga/The Secret of Work`) [container]
    - … 1,360 more
1. `Literary Research Guide`: subpages, collection, 34 parts, 0 missing, order toc, unknown
    - front: Literary Research Guide
    - volume: The Research Process (`Literary Research Guide/The Research Process`) [container]
      - text: The Research Process: Primary Works, Manuscript, Step 1 (`Literary Research Guide/The Research Process/Manuscript Step 1`) [container]
      - text: The Research Process: Primary Works, Manuscript, Step 2 (`Literary Research Guide/The Research Process/Manuscript Step 2`) [container]
      - text: The Research Process: Primary Works, Manuscript, Step 3 (`Literary Research Guide/The Research Process/Manuscript Step 3`) [container]
      - text: The Research Process: Primary Works, Manuscript, Step 4 (`Literary Research Guide/The Research Process/Manuscript Step 4`) [container]
      - text: The Research Process: Primary Works, Manuscript, Step 5 (`Literary Research Guide/The Research Process/Manuscript Step 5`) [container]
      - text: The Research Process: Primary Works, Printed, Step 1 (`Literary Research Guide/The Research Process/Printed Step 1`) [container]
      - text: The Research Process: Primary Works, Printed, Step 2 (`Literary Research Guide/The Research Process/Printed Step 2`) [container]
      - text: The Research Process: Primary Works, Printed, Step 3 (`Literary Research Guide/The Research Process/Printed Step 3`) [container]
      - volume: The Research Process: Primary Works, Printed, Step 4 (`Literary Research Guide/The Research Process/Printed Step 4`) [container]
        - text: Biographical Sources (`Literary Research Guide/J`) [container]
    - … 23 more
1. `Adam's Reports on Vernacular Education in Bengal and Behar`: subpages, book, 63 parts, 0 missing, order chain, proofread
    - front: Adam's Reports on Vernacular Education in Bengal and Behar
    - chapter: Introduction (`Adam's Reports on Vernacular Education in Bengal and Behar/Introduction`) [hierarchy]
    - chapter: Letter to Lord William Bentinck on Vernacular Education (`Adam's Reports on Vernacular Education in Bengal and Behar/Letter to Lord Bentinck`) [hierarchy]
    - volume: First Report on the State of Education in Bengal (`Adam's Reports on Vernacular Education in Bengal and Behar/Report 1`) [hierarchy]
      - text: The Twenty-four Pergunnahs, including Calcutta (`Adam's Reports on Vernacular Education in Bengal and Behar/Report 1/Section 1`) [hierarchy]
      - text: The District of Midnapore (`Adam's Reports on Vernacular Education in Bengal and Behar/Report 1/Section 2`) [hierarchy]
      - text: The District of Orissa Proper or Cuttack (`Adam's Reports on Vernacular Education in Bengal and Behar/Report 1/Section 3`) [hierarchy]
      - text: The District of Hugly (`Adam's Reports on Vernacular Education in Bengal and Behar/Report 1/Section 4`) [hierarchy]
      - text: The District of Burdwan (`Adam's Reports on Vernacular Education in Bengal and Behar/Report 1/Section 5`) [hierarchy]
      - text: The District of Jessore (`Adam's Reports on Vernacular Education in Bengal and Behar/Report 1/Section 6`) [hierarchy]
      - text: The District of Nuddea (`Adam's Reports on Vernacular Education in Bengal and Behar/Report 1/Section 7`) [hierarchy]
      - text: The District of Dacca, Jelalpoor, including the City of Dacca (`Adam's Reports on Vernacular Education in Bengal and Behar/Report 1/Section 8`) [hierarchy]
    - … 52 more
1. `Notes and Queries`: subpages, collection, 225 parts, 237 missing, order scan-volumes, proofread
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
1. `The Collected Works of Henrik Ibsen`: mixed, book, 55 parts, 9 missing, order toc, unproofread
    - toc: The Collected Works of Henrik Ibsen
    - volume: Volume 1 (`The Collected Works of Henrik Ibsen/Volume 1`) [container]
      - volume: LADY INGER OF ÖSTRÅT (`The Collected Works of Henrik Ibsen/Volume 1/Lady Inger of Östråt`) [container]
        - chapter: Introduction (`The Collected Works of Henrik Ibsen/Volume 1/Lady Inger of Östråt/Introduction`) [container]
        - chapter: Act 1 (`The Collected Works of Henrik Ibsen/Volume 1/Lady Inger of Östråt/Act 1`) [container]
        - chapter: Act 2 (`The Collected Works of Henrik Ibsen/Volume 1/Lady Inger of Östråt/Act 2`) [container]
        - chapter: Act 3 (`The Collected Works of Henrik Ibsen/Volume 1/Lady Inger of Östråt/Act 3`) [container]
        - chapter: Act 4 (`The Collected Works of Henrik Ibsen/Volume 1/Lady Inger of Östråt/Act 4`) [container]
        - chapter: Act 5 (`The Collected Works of Henrik Ibsen/Volume 1/Lady Inger of Östråt/Act 5`) [container]
      - volume: THE FEAST AT SOLHOUG (`The Collected Works of Henrik Ibsen/Volume 1/The Feast at Solhoug`) [container]
        - chapter: Introduction (`The Collected Works of Henrik Ibsen/Volume 1/The Feast at Solhoug/Introduction`) [container]
        - text: Author’s Preface (`The Collected Works of Henrik Ibsen/Volume 1/The Feast at Solhoug/Author’s Preface`) [container]
    - … 44 more
1. `Works of Gustave Flaubert`: subpages, book, 33 parts, 32 missing, order scan-volumes, proofread
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
1. `The Works of Voltaire`: mixed, collection, 62 parts, 47 missing, order scan-volumes, proofread
    - front: The Works of Voltaire
    - volume: Volume 1 (`The Works of Voltaire/Volume 1`) [container]
      - text: Publisher's Preface (`The Works of Voltaire/Volume 1/Publisher's Preface`) [container]
      - chapter: Introduction (`The Works of Voltaire/Volume 1/Introduction`) [container]
      - text: The Many-Sided Voltaire (`The Works of Voltaire/Volume 1/The Many-Sided Voltaire`) [container]
      - text: On Liberty (`The Works of Voltaire/Volume 1/On Liberty`) [container]
      - text: On the Nature of Man (`The Works of Voltaire/Volume 1/On the Nature of Man`) [container]
      - text: On Moderation in All Things (`The Works of Voltaire/Volume 1/On Moderation in All Things`) [container]
    - volume: Volume 2 (`The Works of Voltaire/Volume 2`) [container]
      - volume: Contents (`The Story of Johnny; or, The Atheist and the Sage`) [container]
        - chapter: Introduction (`The Story of Johnny; or, The Atheist and the Sage/Introduction`) [container]
        - chapter: Chapter I (`The Story of Johnny; or, The Atheist and the Sage/Chapter 1`) [container]
    - … 51 more
1. `The Amateur Emigrant and The Silverado Squatters`: subpages, book, 26 parts, 0 missing, order scan, validated
    - front: The Amateur Emigrant and The Silverado Squatters
    - chapter: Front matter (`The Amateur Emigrant and The Silverado Squatters/Front matter`) [container]
    - volume: The Amateur Emigrant (`The Amateur Emigrant and The Silverado Squatters/The Amateur Emigrant`) [hierarchy]
      - text: The Second Cabin (`The Amateur Emigrant and The Silverado Squatters/The Amateur Emigrant/The Second Cabin`) [container]
      - text: Early Impressions (`The Amateur Emigrant and The Silverado Squatters/The Amateur Emigrant/Early Impressions`) [container]
      - text: Steerage Scenes (`The Amateur Emigrant and The Silverado Squatters/The Amateur Emigrant/Steerage Scenes`) [container]
      - text: Steerage Types (`The Amateur Emigrant and The Silverado Squatters/The Amateur Emigrant/Steerage Types`) [container]
      - text: The Sick Man (`The Amateur Emigrant and The Silverado Squatters/The Amateur Emigrant/The Sick Man`) [container]
      - text: The Stowaways (`The Amateur Emigrant and The Silverado Squatters/The Amateur Emigrant/The Stowaways`) [container]
      - text: Personal Experience and Review (`The Amateur Emigrant and The Silverado Squatters/The Amateur Emigrant/Personal Experience and Review`) [container]
      - text: New York (`The Amateur Emigrant and The Silverado Squatters/The Amateur Emigrant/New York`) [container]
    - volume: Contents (`The Amateur Emigrant and The Silverado Squatters/The Silverado Squatters`) [hierarchy]
    - … 15 more
1. `The Novels and Letters of Jane Austen`: subpages, book, 92 parts, 12 missing, order single, proofread
    - toc: The Novels and Letters of Jane Austen
    - volume: Volume Eleven (`The Novels and Letters of Jane Austen/Volume 11`) [container]
      - volume: Lady Susan (`The Novels and Letters of Jane Austen/Volume 11/Lady Susan`) [container]
        - chapter: Chapter I (`The Novels and Letters of Jane Austen/Volume 11/Lady Susan/Chapter 1`) [container]
        - chapter: Chapter II (`The Novels and Letters of Jane Austen/Volume 11/Lady Susan/Chapter 2`) [container]
        - chapter: Chapter III (`The Novels and Letters of Jane Austen/Volume 11/Lady Susan/Chapter 3`) [container]
        - chapter: Chapter IV (`The Novels and Letters of Jane Austen/Volume 11/Lady Susan/Chapter 4`) [container]
        - chapter: Chapter V (`The Novels and Letters of Jane Austen/Volume 11/Lady Susan/Chapter 5`) [container]
        - chapter: Chapter VI (`The Novels and Letters of Jane Austen/Volume 11/Lady Susan/Chapter 6`) [container]
        - chapter: Chapter VII (`The Novels and Letters of Jane Austen/Volume 11/Lady Susan/Chapter 7`) [container]
        - chapter: Chapter VIII (`The Novels and Letters of Jane Austen/Volume 11/Lady Susan/Chapter 8`) [container]
        - chapter: Chapter IX (`The Novels and Letters of Jane Austen/Volume 11/Lady Susan/Chapter 9`) [container]
    - … 81 more
1. `The Sense of the Past (London: W. Collins Sons & Co., 1917)`: subpages, book, 6 parts, 3 missing, order single, proofread
    - front: The Sense of the Past (London: W. Collins Sons & Co., 1917)
    - volume: The Sense of the Past (`The Sense of the Past (London: W. Collins Sons & Co., 1917)/The Sense of the Past`) [container]
      - chapter: Book 1 (`The Sense of the Past (London: W. Collins Sons & Co., 1917)/The Sense of the Past/Book 1`) [container]
      - chapter: Book 2 (`The Sense of the Past (London: W. Collins Sons & Co., 1917)/The Sense of the Past/Book 2`) [container]
      - chapter: Book 3 (`The Sense of the Past (London: W. Collins Sons & Co., 1917)/The Sense of the Past/Book 3`) [container]
      - volume: Book 4 (`The Sense of the Past (London: W. Collins Sons & Co., 1917)/The Sense of the Past/Book 4`) [container]
        - chapter: Chapter 1 (`The Sense of the Past (London: W. Collins Sons & Co., 1917)/The Sense of the Past/Book 4/Chapter 1`) [container]
1. `Administrative Instruction ST`: virtual, collection, 7 parts, 0 missing, order natural, proofread
    - volume: 189 (`Administrative Instruction ST/AI/189`)
      - text: Rev.1 (`Administrative Instruction ST/AI/189/Add.9/Rev.1`) [container]
      - volume: Rev.2 (`Administrative Instruction ST/AI/189/Add.9/Rev.2`) [container]
        - text: Add.1 (`Administrative Instruction ST/AI/189/Add.9/Rev.2/Add.1`) [hierarchy]
        - text: Add.2 (`Administrative Instruction ST/AI/189/Add.9/Rev.2/Add.2`) [container]
      - chapter: index of addenda and revisions (`Administrative Instruction ST/AI/189/Addenda`) [hierarchy]
    - text: Administrative Instruction ST/AI/189/Add.9/Rev.2 (`Administrative Instruction ST/AI/2001/5`) [hierarchy]
1. `The Complete Ascetical Works of St. Alphonsus`: subpages, book, 17 parts, 18 missing, order single, proofread
    - front: The Complete Ascetical Works of St. Alphonsus
    - volume: Volume VI (`The Complete Ascetical Works of St. Alphonsus/Volume 6`) [container]
      - volume: The Sacrifice of Jesus Christ (`The Complete Ascetical Works of St. Alphonsus/Volume 6/The Sacrifice of Jesus Christ`) [container]
        - text: Short Explanation of the Prayers of Mass (`The Complete Ascetical Works of St. Alphonsus/Volume 6/The Sacrifice of Jesus Christ/Short Explanation of the Prayers of Mass`) [container]
      - text: Hearing Mass (`The Complete Ascetical Works of St. Alphonsus/Volume 6/Hearing Mass`) [container]
      - volume: THE PRACTICE OF THE LOVE OF JESUS CHRIST. (`The Complete Ascetical Works of St. Alphonsus/Volume 6/The Practice of the Love of Jesus Christ`) [container]
        - chapter: Introduction (`The Complete Ascetical Works of St. Alphonsus/Volume 6/The Practice of the Love of Jesus Christ/Introduction`) [container]
        - chapter: Chapter 1 (`The Complete Ascetical Works of St. Alphonsus/Volume 6/The Practice of the Love of Jesus Christ/Chapter 1`) [container]
        - chapter: Chapter 2 (`The Complete Ascetical Works of St. Alphonsus/Volume 6/The Practice of the Love of Jesus Christ/Chapter 2`) [container]
        - chapter: Chapter 3 (`The Complete Ascetical Works of St. Alphonsus/Volume 6/The Practice of the Love of Jesus Christ/Chapter 3`) [container]
        - chapter: Chapter 4 (`The Complete Ascetical Works of St. Alphonsus/Volume 6/The Practice of the Love of Jesus Christ/Chapter 4`) [container]
        - chapter: Chapter 5 (`The Complete Ascetical Works of St. Alphonsus/Volume 6/The Practice of the Love of Jesus Christ/Chapter 5`) [container]
    - … 6 more
1. `The Literary Magnet`: subpages, collection, 12 parts, 7 missing, order chain, proofread
    - front: The Literary Magnet
    - volume: Series 1 (`The Literary Magnet/Series 1`) [container]
      - volume: Volume 2 (`The Literary Magnet/Series 1/Volume 2`) [container]
        - text: The Dance of the Dead (`The Literary Magnet/Series 1/Volume 2/The Dance of the Dead`) [hierarchy]
        - text: Stanzas of Wail (`The Literary Magnet/Series 1/Volume 2/Stanzas of Wail`) [hierarchy]
        - text: Maredata (`The Literary Magnet/Series 1/Volume 2/Maredata`) [hierarchy]
      - volume: Volume 4 (`The Literary Magnet/Series 1/Volume 4`) [container]
        - text: The Veiled Bride (`The Literary Magnet/Series 1/Volume 4/The Veiled Bride`) [hierarchy]
        - text: You Ask a Song—You Bid Me Sing (`The Literary Magnet/Series 1/Volume 4/You Ask a Song—You Bid Me Sing`) [hierarchy]
        - text: The Veiled Bride—Part II (`The Literary Magnet/Series 1/Volume 4/The Veiled Bride—Part II`) [hierarchy]
        - text: To Alaric A. Watts, Esq. (`The Literary Magnet/Series 1/Volume 4/To Alaric A. Watts, Esq.`) [hierarchy]
        - text: The Elopement (`The Literary Magnet/Series 1/Volume 4/The Elopement`) [hierarchy]
    - … 1 more
1. `Under the Greenwood Tree (Hardy)`: subpages, book, 69 parts, 0 missing, order toc-partial, proofread
    - toc: Under the Greenwood Tree (Hardy)
    - chapter: Preface (`Under the Greenwood Tree (Hardy)/Preface`) [container]
    - chapter: Part 1/Chapter 1 (`Under the Greenwood Tree (Hardy)/Part 1/Chapter 1`) [container]
    - chapter: Part 1/Chapter 2 (`Under the Greenwood Tree (Hardy)/Part 1/Chapter 2`) [container]
    - chapter: Part 1/Chapter 3 (`Under the Greenwood Tree (Hardy)/Part 1/Chapter 3`) [container]
    - chapter: Part 1/Chapter 4 (`Under the Greenwood Tree (Hardy)/Part 1/Chapter 4`) [container]
    - chapter: Part 1/Chapter 5 (`Under the Greenwood Tree (Hardy)/Part 1/Chapter 5`) [container]
    - chapter: Part 1/Chapter 6 (`Under the Greenwood Tree (Hardy)/Part 1/Chapter 6`) [container]
    - chapter: Part 1/Chapter 7 (`Under the Greenwood Tree (Hardy)/Part 1/Chapter 7`) [container]
    - chapter: Part 1/Chapter 8 (`Under the Greenwood Tree (Hardy)/Part 1/Chapter 8`) [container]
    - chapter: Part 1/Chapter 9 (`Under the Greenwood Tree (Hardy)/Part 1/Chapter 9`) [container]
    - chapter: Part 2/Chapter 1 (`Under the Greenwood Tree (Hardy)/Part 2/Chapter 1`) [container]
    - … 58 more
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
1. `Characteristicks of Men, Manners, Opinions, Times`: subpages, book, 13 parts, 4 missing, order chain, proofread
    - front: Characteristicks of Men, Manners, Opinions, Times
    - volume: Volume 1 (`Characteristicks of Men, Manners, Opinions, Times/Volume 1`) [container]
      - text: Treatise I: A Letter concerning Enthusiasm (`Characteristicks of Men, Manners, Opinions, Times/Volume 1/Treatise 1`) [container]
    - volume: Volume 2 (`Characteristicks of Men, Manners, Opinions, Times/Volume 2`) [container]
      - volume: Treatise IV: An Inquiry concerning Virtue and Merit (`Characteristicks of Men, Manners, Opinions, Times/Volume 2/Treatise 4`) [container]
        - text: An Inquiry concerning Virtue and Merit, Book I (`Characteristicks of Men, Manners, Opinions, Times/Volume 2/Treatise 4/Book 1`) [container]
        - text: An Inquiry concerning Virtue and Merit, Book II (`Characteristicks of Men, Manners, Opinions, Times/Volume 2/Treatise 4/Book 2`) [container]
      - volume: Treatise V: The Moralists; a Philosophical Rhapsody (`Characteristicks of Men, Manners, Opinions, Times/Volume 2/Treatise 5`) [container]
        - text: The Moralists; a Philosophical Rhapsody. Part I. (`Characteristicks of Men, Manners, Opinions, Times/Volume 2/Treatise 5/Part 1`) [container]
        - text: The Moralists; a Philosophical Rhapsody. Part II. (`Characteristicks of Men, Manners, Opinions, Times/Volume 2/Treatise 5/Part 2`) [container]
        - text: The Moralists; a Philosophical Rhapsody. Part III. (`Characteristicks of Men, Manners, Opinions, Times/Volume 2/Treatise 5/Part 3`) [container]
    - volume: Volume 3 (`Characteristicks of Men, Manners, Opinions, Times/Volume 3`) [container]
    - … 2 more
1. `The United States Democratic Review`: subpages, book, 6 parts, 53 missing, order toc, proofread
    - front: The United States Democratic Review
    - volume: Volume 13 (`The United States Democratic Review/Volume 13`) [container]
      - text: The Landlord (`The United States Democratic Review/Volume 13/The Landlord`) [container]
    - volume: Volume 43 (`The United States Democratic Review/Volume 43`) [container]
      - volume: Volume 43, Issue 2 (Oct. 1859) (`The United States Democratic Review/Volume 43/Issue 2`) [container]
        - chapter: Volume 43, Issue 2 (October, 1859): Biographical Sketches. Hon. Isaac Toucey—Secretary of the Navy (`The United States Democratic Review/Volume 43/Issue 2/Biographical Sketches. Hon. Isaac Toucey—Secretary of the Navy`) [container]
        - chapter: Volume 43, Issue 2 (October, 1859): What Can Be Done with the Mormons? (`The United States Democratic Review/Volume 43/Issue 2/What Can Be Done with the Mormons?`) [container]
1. `U.S. Troop Readiness, Veterans' Care, Katrina Recovery, and Iraq Accountability Appropriations Act, 2007`: subpages, collection, 14 parts, 1 missing, order chain, unknown
    - front: U.S. Troop Readiness, Veterans' Care, Katrina Recovery, and Iraq Accountability Appropriations Act, 2007
    - text: Title I &minus; Supplemental Appropriations For Defense, International Affairs, and Other Security-Related Needs (`U.S. Troop Readiness, Veterans' Care, Katrina Recovery, and Iraq Accountability Appropriations Act, 2007/Title I`) [container]
    - text: Title II &minus; Hurricane Katrina Recovery (`U.S. Troop Readiness, Veterans' Care, Katrina Recovery, and Iraq Accountability Appropriations Act, 2007/Title II`) [container]
    - text: Title III &minus; Additional Defense, International Affairs, And Homeland Security Provisions (`U.S. Troop Readiness, Veterans' Care, Katrina Recovery, and Iraq Accountability Appropriations Act, 2007/Title III`) [container]
    - text: Title IV &minus; Additional Hurricane Disaster Relief And Recovery (`U.S. Troop Readiness, Veterans' Care, Katrina Recovery, and Iraq Accountability Appropriations Act, 2007/Title IV`) [container]
    - text: Title V &minus; Other Emergency Appropriations (`U.S. Troop Readiness, Veterans' Care, Katrina Recovery, and Iraq Accountability Appropriations Act, 2007/Title V`) [container]
    - text: Title VI &minus; Other Matters (`U.S. Troop Readiness, Veterans' Care, Katrina Recovery, and Iraq Accountability Appropriations Act, 2007/Title VI`) [container]
    - text: Title VII &minus; Elimination Of SCHIP Shortfall And Other Health Matters (`U.S. Troop Readiness, Veterans' Care, Katrina Recovery, and Iraq Accountability Appropriations Act, 2007/Title VII`) [container]
    - volume: Title VIII &minus; Fair Minimum Wage And Tax Relief (`U.S. Troop Readiness, Veterans' Care, Katrina Recovery, and Iraq Accountability Appropriations Act, 2007/Title VIII`) [container]
      - text: Title VIII &minus; Fair Minimum Wage And Tax Relief Subtitle A &minus; Fair Minimum Wage (`U.S. Troop Readiness, Veterans' Care, Katrina Recovery, and Iraq Accountability Appropriations Act, 2007/Title VIII/Subtitle A`) [container]
      - volume: Title VIII &minus; Fair Minimum Wage And Tax Relief Subtitle B &minus; Small Business Tax Incentives (`U.S. Troop Readiness, Veterans' Care, Katrina Recovery, and Iraq Accountability Appropriations Act, 2007/Title VIII/Subtitle B`) [container]
        - text: Title VIII &minus; Fair Minimum Wage And Tax Relief Subtitle B &minus; Small Business Tax Incentives Part 1 &minus; Small Business Tax Relief Provisions (`U.S. Troop Readiness, Veterans' Care, Katrina Recovery, and Iraq Accountability Appropriations Act, 2007/Title VIII/Subtitle B/Part 1`) [container]
    - … 3 more
1. `The Statutes of the Realm`: subpages, book, 48 parts, 25 missing, order single, proofread
    - front: The Statutes of the Realm
    - volume: Volume I (`The Statutes of the Realm/Volume 1`) [container]
      - text: Half-title (`The Statutes of the Realm/Volume 1/Half-title`) [container]
      - text: Title (`The Statutes of the Realm/Volume 1/Title`) [container]
      - text: Colophon (`The Statutes of the Realm/Volume 1/Colophon`) [container]
      - volume: Contents of the First Volume (`The Statutes of the Realm/Volume 1/Contents`) [container]
        - text: His Majesty’s Answer, 17th July 1800 (`The Statutes of the Realm/Volume 1/His Majesty's Answer`) [container]
        - text: First Record Commission, 19th July 1800 (`The Statutes of the Realm/Volume 1/First Record Commission`) [container]
        - text: Second Record Commission, 23d May 1806 (`The Statutes of the Realm/Volume 1/Second Record Commission`) [container]
        - text: Proceedings of the Commissioners, relating to The Statutes of the Realm: From 19th December 1800 to 25th March 1811 (`The Statutes of the Realm/Volume 1/Proceedings of the Commissioners`) [container]
        - text: Report of the Commissioners to The Prince Regent: 25th March 1811 (`The Statutes of the Realm/Volume 1/Report of the Commissioners`) [container]
        - volume: Introduction (`The Statutes of the Realm/Volume 1/Introduction`) [container]
    - … 37 more
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
1. `The Dialogues of Plato (Jowett)`: mixed, book, 62 parts, 20 missing, order scan-volumes, proofread
    - toc: The Dialogues of Plato (Jowett)
    - volume: Volume I (`The Dialogues of Plato (Jowett)/Volume I`) [container]
      - chapter: Introduction to the Charmides (`The Dialogues of Plato (Jowett)/Charmides/Introduction`) [container]
      - text: Charmides (`The Dialogues of Plato (Jowett)/Charmides`) [container]
      - volume: Cratylus (`The Dialogues of Plato (Jowett)/Cratylus`) [container]
        - chapter: Introduction to the Cratylus (`The Dialogues of Plato (Jowett)/Cratylus/Introduction`) [container]
    - chapter: Preface to the First Edition (`The Dialogues of Plato (Jowett)/Preface to the First Edition`) [container]
    - chapter: Preface to the Second and Third Editions (`The Dialogues of Plato (Jowett)/Preface to the Second and Third Editions`) [container]
    - text: Note (`The Dialogues of Plato (Jowett)/Note`) [container]
    - volume: Vol. I Contents (`The Dialogues of Plato (Jowett)/Vol. I Contents`) [container]
      - volume: Lysis (`The Dialogues of Plato (Jowett)/Lysis`) [container]
        - text: Lysis Introduction (`The Dialogues of Plato (Jowett)/Lysis/Introduction`) [container]
    - … 51 more
1. `The Tales of Chekhov`: subpages, collection, 158 parts, 67 missing, order natural, unproofread
    - toc: The Tales of Chekhov
    - volume: Volume 1 (`The Tales of Chekhov/Volume 1`) [hierarchy]
      - chapter: Introduction (`The Tales of Chekhov/Volume 1/Introduction`) [container]
      - text: The Darling (`The Tales of Chekhov/Volume 1/The Darling`) [container]
      - text: Tolstoy's Criticism on "The Darling" (`The Tales of Chekhov/Volume 1/Tolstoy's criticism on "The Darling"`) [container]
      - text: Ariadne (`The Tales of Chekhov/Volume 1/Ariadne`) [container]
      - text: Polinka (`The Tales of Chekhov/Volume 1/Polinka`) [container]
      - text: Anyuta (`The Tales of Chekhov/Volume 1/Anyuta`) [container]
      - text: The Two Volodyas (`The Tales of Chekhov/Volume 1/The Two Volodyas`) [container]
      - text: The Trousseau (`The Tales of Chekhov/Volume 1/The Trousseau`) [container]
      - text: The Helpmate (`The Tales of Chekhov/Volume 1/The Helpmate`) [container]
      - text: Talent (`The Tales of Chekhov/Volume 1/Talent`) [container]
    - … 147 more
1. `The Santa Fe New Mexican`: subpages, collection, 12 parts, 78 missing, order toc, unproofread
    - front: The Santa Fe New Mexican
    - volume: 1849 (`The Santa Fe New Mexican/1849`) [hierarchy]
      - volume: 11 (`The Santa Fe New Mexican/1849/11`) [hierarchy]
        - volume: 24 (`The Santa Fe New Mexican/1849/11/24`) [hierarchy]
          - text: Prospectus (`The Santa Fe New Mexican/1849/11/24/Prospectus`) [hierarchy]
    - volume: 1889 (`The Santa Fe New Mexican/1889`) [hierarchy]
      - volume: 01 (`The Santa Fe New Mexican/1889/01`) [hierarchy]
        - volume: 02 (`The Santa Fe New Mexican/1889/01/02`) [hierarchy]
          - text: A Few Facts for the General Information of Tourists and Sight-Seers Visiting the Capital City of New Mexico (`The Santa Fe New Mexican/1889/01/02/A Few Facts for the General Information of Tourists and Sight-Seers Visiting the Capital City of New Mexico`) [hierarchy]
    - volume: 1911 (`The Santa Fe New Mexican/1911`) [hierarchy]
      - volume: 10 (`The Santa Fe New Mexican/1911/10`) [hierarchy]
        - volume: 12 (`The Santa Fe New Mexican/1911/10/12`) [hierarchy]
    - … 1 more
1. `Scientific Memoirs`: subpages, book, 26 parts, 1 missing, order chain, unproofread
    - front: Scientific Memoirs
    - volume: Volume 1 (`Scientific Memoirs/1`) [container]
      - chapter: Errata (`Scientific Memoirs/1/Errata`) [container]
      - text: List of Plates (`Scientific Memoirs/1/List of Plates`) [container]
      - text: Memoir on the Free Transmission of Radiant Heat through different Solid and Liquid Bodies (`Scientific Memoirs/1/Memoir on the Free Transmission of Radiant Heat through different Solid and Liquid Bodies`) [container]
      - text: New Researches relative to the Immediate Transmission of Radiant Heat through different Solid and Liquid Bodies (`Scientific Memoirs/1/New Researches relative to the Immediate Transmission of Radiant Heat through different Solid and Liquid Bodies`) [container]
      - text: Experiments on the Circular Polarization of Light (`Scientific Memoirs/1/Experiments on the Circular Polarization of Light`) [container]
      - text: Description of an Apparatus for exhibiting the Phaenomena of the Rectilinear, Elliptic, and Circular Polarization of Light (`Scientific Memoirs/1/Description of an Apparatus for exhibiting the Phaenomena of the Rectilinear, Elliptic, and Circular Polarization of Light`) [container]
      - text: Memoirs on Colours in general, and particularly on a new Chromatic Scale deduced from Metallochromy for Scientific and Practical Purposes (`Scientific Memoirs/1/Memoirs on Colours in general, and particularly on a new Chromatic Scale deduced from Metallochromy for Scientific and Practical Purposes`) [container]
      - text: On the Mathematical Theory of Heat (`Scientific Memoirs/1/On the Mathematical Theory of Heat`) [container]
      - text: Researches on the Elasticity of Bodies which crystallize regularly (`Scientific Memoirs/1/Researches on the Elasticity of Bodies which crystallize regularly`) [container]
      - text: Experiments on the Essential Oil of the Spirea Ulmaria, or Meadow-Sweet (`Scientific Memoirs/1/Experiments on the Essential Oil of the Spirea Ulmaria, or Meadow-Sweet`) [container]
    - … 15 more
1. `Ainsworth’s Magazine`: subpages, collection, 22 parts, 26 missing, order chain, proofread
    - front: Ainsworth’s Magazine
    - volume: Volume 1 (`Ainsworth’s Magazine/Volume 1`) [container]
      - text: Legends of the Monts-Dores (`Ainsworth’s Magazine/Volume 1/Number 1/Legends of the Monts-Dores`) [hierarchy]
      - text: Popular Song of the Bourbonnais (`Ainsworth’s Magazine/Volume 1/Number 1/Popular Song of the Bourbonnais`) [hierarchy]
      - volume: Number 2 (`Ainsworth’s Magazine/Volume 1/Number 2`) [hierarchy]
        - text: A Day and Night on the Banks of the Euphrates (`Ainsworth’s Magazine/Volume 1/Number 2/A Day and Night on the Banks of the Euphrates`) [hierarchy]
        - text: The Awakener in the Desert (`Ainsworth’s Magazine/Volume 1/Number 2/The Awakener in the Desert`) [hierarchy]
      - text: A Visit to Persepolis (`Ainsworth’s Magazine/Volume 1/Number 3/A Visit to Persepolis`) [hierarchy]
      - text: Legends of the Monts-Dores (`Ainsworth’s Magazine/Volume 1/Number 3/Legends of the Monts-Dores`) [hierarchy]
      - volume: Number 4 (`Ainsworth’s Magazine/Volume 1/Number 4`) [hierarchy]
        - text: The Two Minstrel Bands (`Ainsworth’s Magazine/Volume 1/Number 4/The Two Minstrel Bands`) [hierarchy]
      - volume: Number 5 (`Ainsworth’s Magazine/Volume 1/Number 5`) [hierarchy]
    - … 11 more
1. `For the People Act of 2021 (H.R. 1; 117th Congress)`: subpages, collection, 92 parts, 0 missing, order chain, unknown
    - front: For the People Act of 2021 (H.R. 1; 117th Congress)
    - text: Sec. 3. − Findings of general constitutional authority. (`For the People Act of 2021 (H.R. 1; 117th Congress)/Sec. 3.`) [hierarchy]
    - text: Sec. 4. − Standards for judicial review (`For the People Act of 2021 (H.R. 1; 117th Congress)/Sec. 4.`) [hierarchy]
    - volume: DIVISION A − VOTING (`For the People Act of 2021 (H.R. 1; 117th Congress)/Division A`) [hierarchy]
      - volume: TITLE I − ELECTION ACCESS (`For the People Act of 2021 (H.R. 1; 117th Congress)/Division A/Title I`) [hierarchy]
        - text: Subtitle A − Voter Registration Modernization (`For the People Act of 2021 (H.R. 1; 117th Congress)/Division A/Title I/Subtitle A`) [hierarchy]
        - text: Subtitle B − Access To Voting For Individuals With Disabilities (`For the People Act of 2021 (H.R. 1; 117th Congress)/Division A/Title I/Subtitle B`) [hierarchy]
        - text: Subtitle C − Prohibiting Voter Caging (`For the People Act of 2021 (H.R. 1; 117th Congress)/Division A/Title I/Subtitle C`) [hierarchy]
        - text: Subtitle D − Prohibiting Deceptive Practices And Preventing Voter Intimidation (`For the People Act of 2021 (H.R. 1; 117th Congress)/Division A/Title I/Subtitle D`) [hierarchy]
        - text: Subtitle E − Democracy Restoration (`For the People Act of 2021 (H.R. 1; 117th Congress)/Division A/Title I/Subtitle E`) [hierarchy]
        - text: Subtitle F − Promoting Accuracy, Integrity, And Security Through Voter-Verified Permanent Paper Ballot (`For the People Act of 2021 (H.R. 1; 117th Congress)/Division A/Title I/Subtitle F`) [hierarchy]
        - text: Subtitle G − Provisional Ballots (`For the People Act of 2021 (H.R. 1; 117th Congress)/Division A/Title I/Subtitle G`) [hierarchy]
    - … 81 more
1. `The Writings of Prosper Mérimée`: subpages, collection, 39 parts, 9 missing, order chain, unproofread
    - front: The Writings of Prosper Mérimée
    - volume: Vol. I (`The Writings of Prosper Mérimée/Volume 1`) [container]
      - chapter: Introduction (`The Writings of Prosper Mérimée/Volume 1/Introduction`) [container]
      - volume: CARMEN (`The Writings of Prosper Mérimée/Volume 1/Carmen`) [container]
        - text: CARMEN: Chapter I (`The Writings of Prosper Mérimée/Volume 1/Carmen/Chapter 1`) [container]
        - text: CARMEN: Chapter II (`The Writings of Prosper Mérimée/Volume 1/Carmen/Chapter 2`) [container]
        - text: CARMEN: Chapter III (`The Writings of Prosper Mérimée/Volume 1/Carmen/Chapter 3`) [container]
        - text: CARMEN: Chapter 4 (`The Writings of Prosper Mérimée/Volume 1/Carmen/Chapter 4`) [container]
      - volume: ARSÈNE GUILLOT (`The Writings of Prosper Mérimée/Volume 1/Arsène Guillot`) [container]
        - text: Arsène Guillot: Chapter I (`The Writings of Prosper Mérimée/Volume 1/Arsène Guillot/Chapter 1`) [container]
        - text: Arsène Guillot: Chapter II (`The Writings of Prosper Mérimée/Volume 1/Arsène Guillot/Chapter 2`) [container]
        - text: Arsène Guillot: Chapter III (`The Writings of Prosper Mérimée/Volume 1/Arsène Guillot/Chapter 3`) [container]
    - … 28 more
1. `Faust (trans. Bayard Taylor)`: subpages, book, 45 parts, 24 missing, order toc, unproofread
    - front: Faust (trans. Bayard Taylor)
    - chapter: Preface (`Faust (trans. Bayard Taylor)/Preface`) [container]
    - text: An Goethe (`Faust (trans. Bayard Taylor)/An Goethe`) [container]
    - text: Dedication (`Faust (trans. Bayard Taylor)/Dedication`) [container]
    - text: Prelude on the Stage (`Faust (trans. Bayard Taylor)/Prelude on the stage`) [container]
    - chapter: Prologue in Heaven (`Faust (trans. Bayard Taylor)/Prologue in heaven`) [container]
    - chapter: First Part (`Faust (trans. Bayard Taylor)/First part`) [container]
    - text: Night () (`Faust (trans. Bayard Taylor)/I night`) [container]
    - text: Before the City-Gate (`Faust (trans. Bayard Taylor)/II before the-city gate`) [container]
    - text: (The Exorcism) (`Faust (trans. Bayard Taylor)/III the study exorcism`) [container]
    - text: (The Compact) (`Faust (trans. Bayard Taylor)/IV The Study Compact`) [container]
    - text: Auerbach's Cellar (`Faust (trans. Bayard Taylor)/V Auerbachs Cellar`) [container]
    - … 34 more
1. `Opticks (4th Ed)`: subpages, book, 8 parts, 9 missing, order natural, unknown
    - toc: Opticks (4th Ed)
    - chapter: Advertisement to the Fourth Edition (`Opticks (4th Ed)/Advertisement4th`) [container]
    - chapter: Advertisement I (`Opticks (4th Ed)/AdvertisementI`) [container]
    - chapter: Advertisement II (`Opticks (4th Ed)/AdvertisementII`) [container]
    - chapter: Advertisements (`Opticks (4th Ed)/Advertisements`) [container]
    - volume: The First Book (`Opticks (4th Ed)/The First Book`) [container]
      - volume: The First Book:Part I (`Opticks (4th Ed)/The First Book/Part I`) [container]
        - text: The First Book: Part I - Definitions (`Opticks (4th Ed)/The First Book/Part I/Definitions`) [container]
        - text: The First Book: Part I - Axioms (`Opticks (4th Ed)/The First Book/Part I/Axioms`) [container]
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

### I: works with other editions (3,557)

1. `The lamentable Ditty of Little Mousgroue, and the Lady Barnet (H. Gosson)`: single, book, 1 parts, 0 missing, order –, proofread, excluded no-text
    - text: The lamentable Ditty of Little Mousgroue, and the Lady Barnet (H. Gosson)
1. `An Elegy on the Death and Burial of Cock Robin`: single, book, 1 parts, 0 missing, order –, proofread
    - text: An Elegy on the Death and Burial of Cock Robin
1. `Excellent old song intitled Young Beichan and Susie Pye`: subpages, book, 2 parts, 0 missing, order chain, validated
    - toc: Excellent old song intitled Young Beichan and Susie Pye
    - text: Young Beichan and Susie Pye (`Excellent old song intitled Young Beichan and Susie Pye/Young Beichan and Susie Pye`) [container]
    - text: Britannia's Call (`Excellent old song intitled Young Beichan and Susie Pye/Britannia's call`) [container]
1. `The beggar girl`: subpages, book, 6 parts, 0 missing, order chain, validated
    - toc: The beggar girl
    - text: The Beggar Girl (`The beggar girl/The Beggar Girl`) [container]
    - text: The Shepherd's Holiday (`The beggar girl/The Shepherd's Holiday`) [container]
    - text: Roslin Castle (`The beggar girl/Roslin Castle`) [container]
    - text: The Weaver and the Tailor (`The beggar girl/The Weaver and the Tailor`) [container]
    - text: The Modern Beau (`The beggar girl/The Modern Beau`) [container]
    - text: Hap me with thy Petticoat (`The beggar girl/Hap me with thy Petticoat`) [container]
1. `Reasons for not eating animal food`: single, book, 1 parts, 0 missing, order –, validated
    - text: Reasons for not eating animal food
1. `Constitution Act 1902 (New South Wales)`: single, book, 1 parts, 0 missing, order –, proofread
    - text: Constitution Act 1902 (New South Wales)
1. `Bible (King James)`: subpages, book, 394 parts, 0 missing, order chain, unproofread
    - front: Bible (King James)
    - chapter: Preface (`Bible (King James)/Preface`) [container]
    - volume: Genesis (`Bible (King James)/Genesis`) [container]
      - chapter: Chapter I (`Bible (King James)/Genesis/Chapter 1`) [container]
      - chapter: Chapter II (`Bible (King James)/Genesis/Chapter 2`) [container]
      - chapter: Chapter III (`Bible (King James)/Genesis/Chapter 3`) [container]
      - chapter: Chapter IV (`Bible (King James)/Genesis/Chapter 4`) [container]
      - chapter: Chapter V (`Bible (King James)/Genesis/Chapter 5`) [container]
      - chapter: Chapter VI (`Bible (King James)/Genesis/Chapter 6`) [container]
      - chapter: Chapter VII (`Bible (King James)/Genesis/Chapter 7`) [container]
      - chapter: Chapter VIII (`Bible (King James)/Genesis/Chapter 8`) [container]
      - chapter: Chapter IX (`Bible (King James)/Genesis/Chapter 9`) [container]
    - … 383 more
1. `Ah Q and Others`: subpages, book, 12 parts, 0 missing, order chain, proofread
    - front: Ah Q and Others
    - chapter: Introduction (`Ah Q and Others/Introduction`) [container]
    - text: My Native Heath (`Ah Q and Others/My Native Heath`) [container]
    - text: The Cake of Soap (`Ah Q and Others/The Cake of Soap`) [container]
    - text: The Divorce (`Ah Q and Others/The Divorce`) [container]
    - text: Reunion in a Restaurant (`Ah Q and Others/Reunion in a Restaurant`) [container]
    - text: The Story of Hair (`Ah Q and Others/The Story of Hair`) [container]
    - text: Cloud over Luchen (`Ah Q and Others/Cloud over Luchen`) [container]
    - text: Our Story of Ah Q (`Ah Q and Others/Our Story of Ah Q`) [container]
    - text: A Hermit at Large (`Ah Q and Others/A Hermit at Large`) [container]
    - text: Remorse (`Ah Q and Others/Remorse`) [container]
    - text: The Widow (`Ah Q and Others/The Widow`) [container]
    - … 1 more
1. `Duke of Gordon's Three Daughters, to which are added, Mary I believ'd thee true and Prince Charlie`: subpages, book, 3 parts, 0 missing, order chain, validated
    - front: Duke of Gordon's Three Daughters, to which are added, Mary I believ'd thee true and Prince Charlie
    - text: The Duke of Gordon's Daughters (`Duke of Gordon's Three Daughters, to which are added, Mary I believ'd thee true and Prince Charlie/The Duke of Gordon's Daughters`) [container]
    - text: Prince Charlie (`Duke of Gordon's Three Daughters, to which are added, Mary I believ'd thee true and Prince Charlie/Prince Charlie`) [container]
    - text: Mary, I Believ'd thee True (`Duke of Gordon's Three Daughters, to which are added, Mary I believ'd thee true and Prince Charlie/Mary, I believed thee true`) [container]
1. `Poems (Chesterton, 1915)`: subpages, book, 59 parts, 0 missing, order chain, proofread
    - front: Poems (Chesterton, 1915)
    - text: To Edmund Clerihew Bentley (`Poems (Chesterton, 1915)/To Edmund Clerihew Bentley`) [container]
    - text: To Hilaire Belloc (`Poems (Chesterton, 1915)/To Hilaire Belloc`) [container]
    - text: To M. E. W. (`Poems (Chesterton, 1915)/To M. E. W.`) [container]
    - text: Lepanto (`Poems (Chesterton, 1915)/Lepanto`) [container]
    - text: The March of the Black Mountain (`Poems (Chesterton, 1915)/The March of the Black Mountain`) [container]
    - text: Blessed are the Peacemakers (`Poems (Chesterton, 1915)/Blessed are the Peacemakers`) [container]
    - text: The Wife of Flanders (`Poems (Chesterton, 1915)/The Wife of Flanders`) [container]
    - text: The Crusader Returns from Captivity (`Poems (Chesterton, 1915)/The Crusader Returns from Captivity`) [container]
    - text: Glencoe (`Poems (Chesterton, 1915)/Glencoe`) [container]
    - text: Love's Trappist (`Poems (Chesterton, 1915)/Love's Trappist`) [container]
    - text: Confessional (`Poems (Chesterton, 1915)/Confessional`) [container]
    - … 48 more
1. `When I Survey the Wondrous Cross (unsourced)`: single, single, 1 parts, 0 missing, order –, unknown
    - text: When I Survey the Wondrous Cross (unsourced)
1. `The Disobedient Kids and other Czecho-Slovak fairy tales`: subpages, book, 11 parts, 0 missing, order scan, validated
    - front: The Disobedient Kids and other Czecho-Slovak fairy tales
    - chapter: Front matter (`The Disobedient Kids and other Czecho-Slovak fairy tales/Front matter`) [container]
    - text: A Tale Without End (`The Disobedient Kids and other Czecho-Slovak fairy tales/A Tale Without End`) [container]
    - text: The Little Cock and the Little Hen (`The Disobedient Kids and other Czecho-Slovak fairy tales/The Little Cock and the Little Hen`) [container]
    - text: The Disobedient Kids (`The Disobedient Kids and other Czecho-Slovak fairy tales/The Disobedient Kids`) [container]
    - text: The Little Stars of Gold (`The Disobedient Kids and other Czecho-Slovak fairy tales/The Little Stars of Gold`) [container]
    - text: Castle Bousin (`The Disobedient Kids and other Czecho-Slovak fairy tales/Castle Bousin`) [container]
    - text: Smolineck (`The Disobedient Kids and other Czecho-Slovak fairy tales/Smolineck`) [container]
    - text: The House of Candy (`The Disobedient Kids and other Czecho-Slovak fairy tales/The House of Candy`) [container]
    - text: The Frog and Belinka (`The Disobedient Kids and other Czecho-Slovak fairy tales/The Frog and Belinka`) [container]
    - text: A Story about Palecek, "Little Thumb" (`The Disobedient Kids and other Czecho-Slovak fairy tales/A Story about Palecek, "Little Thumb"`) [container]
    - text: The Magician, the Cat and the Stove (`The Disobedient Kids and other Czecho-Slovak fairy tales/The Magician, the Cat and the Stove`) [container]
1. `What's to be done? A romance`: subpages, book, 4 parts, 5 missing, order chain, proofread
    - front: What's to be done? A romance
    - text: An Imbecile. (`What's to be done? A romance/I`) [hierarchy]
    - text: First Consequences of the Imbecile Act. (`What's to be done? A romance/II`) [hierarchy]
    - chapter: Preface. (`What's to be done? A romance/III`) [hierarchy]
    - chapter: Chapter First. (`What's to be done? A romance/Chapter First`) [hierarchy]
1. `Constitution Act, 1871 (annotated)`: single, single, 1 parts, 0 missing, order –, unknown
    - text: Constitution Act, 1871 (annotated)
1. `The Wife of Beith (1840s)`: single, book, 1 parts, 0 missing, order –, validated
    - text: The Wife of Beith (1840s)
1. `Poems on Various Subjects (Coleridge)`: subpages, book, 55 parts, 0 missing, order chain, validated
    - front: Poems on Various Subjects (Coleridge)
    - text: Monody on the Death of Chatterton (`Poems on Various Subjects (Coleridge)/Monody on the Death of Chatterton`) [container]
    - text: To the Rev. W. J. H. (`Poems on Various Subjects (Coleridge)/To the Rev. W. J. H.`) [container]
    - text: Songs of the Pixies (`Poems on Various Subjects (Coleridge)/Songs of the Pixies`) [container]
    - text: Lines on the Man of Ross (`Poems on Various Subjects (Coleridge)/Lines on the Man of Ross`) [container]
    - text: Lines to a Beautiful Spring (`Poems on Various Subjects (Coleridge)/Lines to a Beautiful Spring`) [container]
    - text: Epitaph on an Infant (`Poems on Various Subjects (Coleridge)/Epitaph on an Infant`) [container]
    - text: Lines on a Friend (`Poems on Various Subjects (Coleridge)/Lines on a Friend`) [container]
    - text: To a Young Lady with a Poem (`Poems on Various Subjects (Coleridge)/To a Young Lady with a Poem`) [container]
    - text: Absence, a farewell Ode (`Poems on Various Subjects (Coleridge)/Absence, a farewell Ode`) [container]
    - text: Effusion 1, to Bowles (`Poems on Various Subjects (Coleridge)/Effusion 1, to Bowles`) [container]
    - text: Effusion 2, to Burke (`Poems on Various Subjects (Coleridge)/Effusion 2, to Burke`) [container]
    - … 44 more
1. `The Battle of Roslin (n.d., Glasgow)`: subpages, book, 2 parts, 0 missing, order chain, validated
    - toc: The Battle of Roslin (n.d., Glasgow)
    - text: The Battle of Roslin (`The Battle of Roslin (n.d., Glasgow)/The Battle of Roslin`) [container]
    - text: John Highlandman's Remarks on Glasgow (`The Battle of Roslin (n.d., Glasgow)/John Highlandman's Remarks on Glasgow`) [container]
1. `The Farmer`: subpages, book, 5 parts, 0 missing, order chain, validated
    - toc: The Farmer
    - text: The Farmer (`The Farmer/The Farmer`) [container]
    - text: Lilies of the Valley (`The Farmer/Lilies of the Valley`) [container]
    - text: Young Allan (`The Farmer/Young Allan`) [container]
    - text: Last May a Braw Wooer (`The Farmer/Last May a Braw Wooer`) [container]
    - text: The Careful Wife (`The Farmer/The Careful Wife`) [container]
1. `Halek's Stories and Evensongs`: subpages, book, 10 parts, 0 missing, order chain, proofread
    - front: Halek's Stories and Evensongs
    - chapter: Preface (`Halek's Stories and Evensongs/Preface`) [container]
    - text: Publisher's introduction to the edition of 1923 (`Halek's Stories and Evensongs/Publisher's introduction to the edition of 1923`) [container]
    - text: Evensongs (`Halek's Stories and Evensongs/Evensongs`) [container]
    - text: Under the Hollow Tree (`Halek's Stories and Evensongs/Under the Hollow Tree`) [container]
    - text: In Lordly Halls (`Halek's Stories and Evensongs/In Lordly Halls`) [container]
    - text: Our Grandfather (`Halek's Stories and Evensongs/Our Grandfather`) [container]
    - text: The Domestic Teacher (`Halek's Stories and Evensongs/The Domestic Teacher`) [container]
    - text: Poldik the Scavenger (`Halek's Stories and Evensongs/Poldik the Scavenger`) [container]
    - text: Pensioned Off (`Halek's Stories and Evensongs/Pensioned Off`) [container]
    - text: Bibliography (`Halek's Stories and Evensongs/Bibliography`) [container]
1. `Poems of Felicia Hemans in The Literary Souvenir, 1826`: subpages, book, 4 parts, 0 missing, order chain, proofread
    - toc: Poems of Felicia Hemans in The Literary Souvenir, 1826
    - text: The Wreck (`Poems of Felicia Hemans in The Literary Souvenir, 1826/The Wreck`) [container]
    - text: Aymer's Tomb (`Poems of Felicia Hemans in The Literary Souvenir, 1826/Aymer's Tomb`) [container]
    - text: The Child and Dove (`Poems of Felicia Hemans in The Literary Souvenir, 1826/The Child and Dove`) [container]
    - text: The Troubadour and Richard Cœur de Lion (`Poems of Felicia Hemans in The Literary Souvenir, 1826/The Troubadour and Richard Cœur de Lion`) [container]
1. `Poems of Charles Baudelaire`: subpages, book, 36 parts, 0 missing, order natural, unproofread
    - front: Poems of Charles Baudelaire
    - text: A Former Life (`Poems of Charles Baudelaire/A Former Life`) [container]
    - text: A Landscape (`Poems of Charles Baudelaire/A Landscape`) [container]
    - text: A Madrigal of Sorrow (`Poems of Charles Baudelaire/A Madrigal of Sorrow`) [container]
    - text: An Allegory (`Poems of Charles Baudelaire/An Allegory`) [container]
    - text: Beauty (`Poems of Charles Baudelaire/Beauty`) [container]
    - text: Contemplation (`Poems of Charles Baudelaire/Contemplation`) [container]
    - text: Correspondences (`Poems of Charles Baudelaire/Correspondences`) [container]
    - text: Don Juan in Hades (`Poems of Charles Baudelaire/Don Juan in Hades`) [container]
    - text: Exotic Perfume (`Poems of Charles Baudelaire/Exotic Perfume`) [container]
    - text: La Beatrice (`Poems of Charles Baudelaire/La Beatrice`) [container]
    - text: Reversibility (`Poems of Charles Baudelaire/Reversibility`) [container]
    - … 25 more
1. `The Water is Wide (Alan Sim 2)`: single, single, 1 parts, 0 missing, order –, unknown
    - text: The Water is Wide (Alan Sim 2)
1. `In Triumph, Joy, and Holy Fear`: single, single, 1 parts, 0 missing, order –, unknown
    - text: In Triumph, Joy, and Holy Fear
1. `Hours of Idleness`: subpages, book, 5 parts, 35 missing, order scan, proofread
    - front: Hours of Idleness
    - chapter: Preface (`Hours of Idleness/Preface`) [container]
    - text: On Leaving Newstead Abbey (`Hours of Idleness/On Leaving Newstead Abbey`) [container]
    - text: On leaving Newstead Abbey (`Hours of Idleness/On leaving Newstead Abbey`) [container]
    - text: On a distant view of the Village and School of Harrow on the Hill (`Hours of Idleness/On a distant view of the Village and School of Harrow on the Hill`) [container]
    - text: Epitaph on a Friend (`Hours of Idleness/Epitaph on a Friend`) [container]
1. `The Wife of Beith (Glasgow)`: single, book, 1 parts, 0 missing, order –, validated
    - text: The Wife of Beith (Glasgow)
1. `Fairy tales and stories (Andersen, Tegner)`: subpages, book, 43 parts, 0 missing, order chain, proofread
    - front: Fairy tales and stories (Andersen, Tegner)
    - chapter: Introduction (`Fairy tales and stories (Andersen, Tegner)/Introduction`) [container]
    - text: The Nightingale (`Fairy tales and stories (Andersen, Tegner)/The Nightingale`) [container]
    - text: The Jumpers (`Fairy tales and stories (Andersen, Tegner)/The Jumpers`) [container]
    - text: The Traveling Companion (`Fairy tales and stories (Andersen, Tegner)/The Traveling Companion`) [container]
    - text: The Money-Pig (`Fairy tales and stories (Andersen, Tegner)/The Money-Pig`) [container]
    - text: The Galoshes of Fortune (`Fairy tales and stories (Andersen, Tegner)/The Galoshes of Fortune`) [container]
    - text: Aunty Toothache (`Fairy tales and stories (Andersen, Tegner)/Aunty Toothache`) [container]
    - text: The Tinderbox (`Fairy tales and stories (Andersen, Tegner)/The Tinderbox`) [container]
    - text: Little Ida's Flowers (`Fairy tales and stories (Andersen, Tegner)/Little Ida's Flowers`) [container]
    - text: Elder-Tree Mother (`Fairy tales and stories (Andersen, Tegner)/Elder-Tree Mother`) [container]
    - text: The Brownie at the Buttermans (`Fairy tales and stories (Andersen, Tegner)/The Brownie at the Buttermans`) [container]
    - … 32 more
1. `The Civil Code of Japan`: subpages, book, 8 parts, 0 missing, order chain, proofread
    - front: The Civil Code of Japan
    - chapter: Book 1 (`The Civil Code of Japan/Book 1`) [hierarchy]
    - chapter: Book 2 (`The Civil Code of Japan/Book 2`) [hierarchy]
    - chapter: Book 3 (`The Civil Code of Japan/Book 3`) [hierarchy]
    - chapter: Book 4 (`The Civil Code of Japan/Book 4`) [hierarchy]
    - chapter: Book 5 (`The Civil Code of Japan/Book 5`) [hierarchy]
    - text: Law Concerning the Application of Laws in General (`The Civil Code of Japan/Law Concerning the Application of Laws in General`) [hierarchy]
    - text: Law Amending the Decree No. 113 (`The Civil Code of Japan/Law Amending the Decree No. 113`) [hierarchy]
    - text: Law of Nationality (`The Civil Code of Japan/Law of Nationality`) [hierarchy]
1. `Sophocles (Storr 1919)`: subpages, book, 1 parts, 3 missing, order single, proofread
    - front: Sophocles (Storr 1919)
    - text: Electra (`Sophocles (Storr 1919)/Electra`) [container]
1. `The Mavis (1819, Falkirk)`: subpages, book, 28 parts, 0 missing, order chain, validated
    - front: The Mavis (1819, Falkirk)
    - text: The Mariner's Wife (`The Mavis (1819, Falkirk)/The Mariner's Wife`) [container]
    - text: Nae luck about the House (`The Mavis (1819, Falkirk)/Nae luck about the House`) [container]
    - text: The Wayward Wife (`The Mavis (1819, Falkirk)/The Wayward Wife`) [container]
    - text: The Answer to the Wayward Wife (`The Mavis (1819, Falkirk)/The Answer to the Wayward Wife`) [container]
    - text: The Wedding Day (`The Mavis (1819, Falkirk)/The Wedding Day`) [container]
    - text: The Bonny Lad (`The Mavis (1819, Falkirk)/The Bonny Lad`) [container]
    - text: Ralph of the Mill (`The Mavis (1819, Falkirk)/Ralph of the Mill`) [container]
    - text: The Ewie wi' the Crooked Horn (`The Mavis (1819, Falkirk)/The Ewie wi' the Crooked Horn`) [container]
    - text: A Man to my mind (`The Mavis (1819, Falkirk)/A Man to my mind`) [container]
    - text: Gramachree Molly (`The Mavis (1819, Falkirk)/Gramachree Molly`) [container]
    - text: The Answer to Gramachree Molly (`The Mavis (1819, Falkirk)/The Answer to Gramachree Molly`) [container]
    - … 17 more
1. `Hemans Miscellaneous Poetry 7`: subpages, book, 4 parts, 0 missing, order chain, validated
    - toc: Hemans Miscellaneous Poetry 7
    - chapter: Prologue to 'Fiesco (`Hemans Miscellaneous Poetry 7/Prologue to 'Fiesco'`) [container]
    - text: The Hebe of Canova (`Hemans Miscellaneous Poetry 7/The Hebe of Canova`) [container]
    - text: Song. Look on me with thy cloudless eyes (`Hemans Miscellaneous Poetry 7/Song`) [container]
    - text: The Last Words of the Last Wasp of Scotland (`Hemans Miscellaneous Poetry 7/The Last Wasp of Scotland`) [container]

