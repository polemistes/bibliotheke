# Work structure: how Wikisource pages become books

Status: **decided by the owner on 2026-10-31** (sections 8 and 10.8). Written
for the owner and the implementing agent. It extends PLAN.md (sections 3.2,
3.4, 5, 7.1) and resolves the open item "collections that link separate root
pages" in `docs/wp0-findings.md` section 6, WP1 item 6. Nothing here is
implemented yet. The first task of WP1 is to apply the PLAN.md edits listed
in section 9; WP1a (section 6) follows. Raw evidence: `docs/structure/`;
spike scripts: `pipeline/wp0/` (README there).

## 1. The problem

PLAN.md models a work as a main-namespace root page whose parts are its
subpages, with the order taken from `.ws-summary` or the subpage links.
WP0 and the checks below show that the Wikisources use several other
structures, and that readers expect the *book*, not the wiki page. The
current model gives these results:

| | pattern | example (verified) | current plan gives | reader expects |
|---|---|---|---|---|
| A | **Collection of root pages**: a table-of-contents root page links separate root pages; each member's header links back and chains to the previous and next member | de `Der Sagenschatz des Königreichs Sachsen` (990 members); de `Kinder- und Haus-Märchen Band 1 (1857)` (tales as root pages); no `Diktsamling` (Vinje, 1864: 125 poems, 9 transcribed) | 991 works, one of them a TOC page without text, 990 tales without context | one book with 990 chapters; each tale also findable by title |
| B | **Mixed**: some parts are subpages, some are root pages, some root titles are redirects to subpages | fr `La Légende des siècles`: `/Clarté d’âmes` is a subpage, `L’Épopée du ver` a root page (transcluding a different edition's scan), `Clarté d’âmes` a root-title redirect | the root-page poem becomes a separate work; prev/next cross editions | one collection; poems findable |
| C | **Whole book on one page** | de `Käthe`: 604 k characters, 471 page markers, no chapter headings in the HTML | one part; breaks N3 (memory) and relayout time | chapters in the TOC, snappy font changes |
| D | **Missing root**: a subpage tree whose root page does not exist, volumes chained by prev/next | de `Die Heimath in der neuen Welt/{Erster,Zweiter,Dritter} Band` (root missing; 14 letter subpages under volume 2) | three works called "…/Zweiter Band" | one work with three volumes |
| E | **Texts hidden inside collections**: poems, tales and articles exist only as subpages of a collection; the root title is a disambiguation or versions page, which the catalog excludes | en `…Shelley (ed. Hutchinson, 1914)/Ozymandias`; root `Ozymandias` is `{{disambiguation}}`, `Ozymandias (Shelley)` is `{{versions}}` | "Ozymandias" cannot be found at all | search finds Ozymandias and opens the collection at that poem |
| F | **Whole-text duplicate**: a subpage holding the entire work beside the chapter subpages | fr `…/Texte entier`: 4,518 such subpages | a part that doubles the download | ignored (later: a one-request download) |
| G | **Reference works**: dictionaries and encyclopedias with tens of thousands of entries, as subpages (en, fr) or as colon-prefixed root pages (de) | en `Alumni Oxonienses` 63,277 subpages, `1911 Encyclopædia Britannica` 39,272; de `RE:` 106,708 root pages, `MKL1888:` 38,532, `ADB:` 31,108, `BLKÖ:` 27,227, `Zedler:` 16,568, `Boetticher:` 5,261, `Haan:` 801 … | de: about 25,000 entries from prefixes not yet on the exclusion list become works; en: a work with 39,272 parts and a huge parts list | not in the book shelves; later, a lookup feature |
| H | **Deep nesting** | fr `Madame Bovary/Première partie/1`; en trees reach depth 9 (depth ≥ 4: 24,000 titles) | handled to depth 3 | TOC with hierarchy |
| I | **Several editions of one text** | de `{{Andere Version}}` on the text page (Froschkönig: 7 editions); en `{{versions}}` pages; no `Peker` pages | separate works (fine); versions pages excluded | later: "other editions" |

### Size of each pattern (ns0 title lists from the dumps, 2026-10-31; redirects included)

| wiki | ns0 titles | root pages | subpages (depth 1 / 2 / ≥ 3) | roots with subpages | orphan trees (subpages) | lexicon-prefix roots |
|---|---|---|---|---|---|---|
| no | 12,017 | 4,042 | 7,975 (6,549 / 1,415 / 11) | 376 (9 %) | 63 (426) | 0 |
| de | 324,362 | 307,220 | 17,142 (14,857 / 2,243 / 42) | 707 (0.2 %) | 1,190 (2,234) | 203,575 with the four known prefixes; about 25,000 more under `Zedler:`, `Boetticher:`, `Haan:`, `Rittergüter und Schlösser im Königreiche Sachsen:`, `Topographia …:` and others |
| fr | 594,094 | 86,121 | 507,973 (286,723 / 210,209 / 11,041) | 12,551 (15 %) | 1,979 (21,227) | 0 |
| en | 779,939 | 172,047 | 607,892 (479,238 / 69,552 / 59,102) | 18,405 (11 %) | 1,236 (4,349) | 0 |

Reading: on de, after the reference works are removed, under 80,000 root
pages remain (redirects included) and only 707 of them have subpages, so **pattern A is the
normal book structure on de**, not an exception. On fr and en the subpage
model holds for books, but the biggest subpage trees are reference works
(pattern G), and poems inside collections (pattern E) are invisible to the
current catalog. Orphan trees exist on every wiki (on no and de many are
court decisions such as `05-130602TVI-OTIR/…` and `1 BvR 1190/…`).

## 2. What the app should present

Three concepts replace "work = root page":

- **Work** — a catalog entry: what a reader picks from the shelf. A book, a
  poetry collection, a play, a standalone poem, a volume of a periodical.
  Browse lists and facets show works only.
- **Part** — one reading unit, one normalised HTML file: a chapter, a poem
  inside a collection, an act, a volume's front matter, or a synthetic slice
  of an oversized page. Parts form a tree under their work (`depth`,
  `parent`). The reader's TOC shows parts.
- **Text** — a part that is a distinct text with a title of its own (a poem,
  a tale, an article, a letter): it is findable in search and opens its work
  at that part. A standalone poem is a work and its only part at once.

Rules a reader would take for granted:

1. A work is **one edition of one book** (or of one text). It never merges
   editions: Grimm 1812 and Grimm 1857 are two works; `Kinder und
   Hausmärchen` (the de overview page of all editions) is not a work.
2. Every content page belongs to **exactly one work** as its primary home.
   The wiki page that a text's own header names as its container wins over
   any TOC page that merely links it. (Secondary memberships, "also in", are
   recorded later, not in v1.)
3. Parts are shown in **reading order**: the order the book has, not
   alphabetical.
4. A text inside a collection has **its own source page, licence and
   contributors**; attribution (R11) must list the member pages of a work
   that consists of several wiki pages.
5. Reference works are works of type `reference`: listed and searchable by
   title, hidden by the default type filter, with their entries loaded
   lazily (never a 39,000-row TOC in the catalog).

## 3. Structure signals, as verified on 2026-10-31

All of these appear both in the wikitext (pages-articles dump, for the
pipeline) and in the `data-mw` attributes of Parsoid HTML (for the app's
live resolution), so pipeline and app can run the same rules.

### 3.1 ProofreadPage header (scan-backed pages on every wiki)

`<pages index="…" from=… to=… header=1 />` renders
`MediaWiki:Proofreadpage_header_template` with the Index page's fields plus
navigation. Parameters seen (no / fr / en names):

| meaning | no | fr | en | example |
|---|---|---|---|---|
| container (work root) | `tittel` | `titre` | `title` | `tittel = ''[[Diktsamling]]''` on every Vinje poem; `titre = [[Madame Bovary]]`; `title = [[Frankenstein, … (Revised Edition, 1831)]]` |
| previous / next part | `prev`, `next` | `prev`, `next` | `prev`, `next` | `prev = [[Den Kvelden, du veit vel]]`, `next = [[I Draumen paa Sæteren]]` (root pages); `next = [[Madame Bovary/Première partie/2|Chapitre II]]` (subpage) |
| this part | `current` | `current` | `current` | `current = [[Arne/1|Første kapitel]]` |
| book type (Index `Type`) | `type` | `type` | `type` | `type = collection` on Diktsamling, La Légende des siècles, Racine's Œuvres; `type = book` on Arne, Madame Bovary, Frankenstein |
| TOC | `sammendrag` | `sommaire` | (`remarks`) | a wikitext list (`*[[Arne/1|Første kapitel]] …`) or transcluded scan pages (`{{Side:Vinje-Diktsamling.djvu/235}} …`) |
| scan file | via `Side:`/`Page:` titles | `index` | via `Page:` titles | `index = Flaubert - Madame Bovary, Conard, 1910.djvu` |
| page range | `from`, `to` | `from`, `to` | `from`, `to` | **displayed labels, not scan indexes**: Arne/1 has `from=5 to=8` while it transcludes djvu/6–9; Diktsamling has `from=tittel to=XVI` |

Consequences: order parts by the scan indexes in the wikitext `<pages
from= to=>` attributes (integers) or by the `Page:` titles actually
transcluded, never by the rendered `from`/`to`. The container link can
point outside ns0 (Racine: `titre = [[Livre:Racine - Œuvres, Didot,
1854.djvu|…]]`), in which case it is ignored and the title hierarchy is
used.

Index `Type` vocabularies (from `MediaWiki:Proofreadpage_index_data_config.json`):
en `book, journal, collection, phdthesis, dictionary, film`; fr `book
(Livre), collection (Recueil), journal, phdthesis, dictionary
(Dictionnaire, encyclopédie, ouvrage de référence)`; no: free string,
default `book`. The Index configs also carry series fields (no `samling`,
fr `recueil`, en `Volumes`, no `Bindfortegnelse`, fr `Tomes`); their use for
grouping volumes is to be checked in the survey (section 6).

### 3.2 en `{{header}}` (non-scan pages)

`title = [[../]]` or `title = [[../|The Complete Poetical Works …]]`,
`section = Ozymandias`, `previous = [[../Lines to a Critic/]]`,
`next = [[../Note on Poems of 1817, by Mrs. Shelley/]]`. Relative links
(`../`, `/Sub/`) must be resolved against the page title. `{{other
versions|Ozymandias (Shelley)}}` names the versions page.

### 3.3 de `{{Textdaten}}` (every de text; de has no microformats)

| field | TOC/root page | member page | standalone text |
|---|---|---|---|
| `HERKUNFT` | `off` | `[[Der Sagenschatz des Königreichs Sachsen]], Band 1. S. 1`; `[[Kinder- und Haus-Märchen Band 1 (1857)|…]], Große Ausgabe. S. 1–5` | plain text, no link: `Goethes Werke. Erster Band, S. 104` (Erlkönig), `Ausgewählte Romane` (Käthe) |
| `VORIGER` / `NÄCHSTER` | – | plain titles (no brackets); the first member's `VORIGER` is the root page itself | – |
| `INDEXSEITE` | Index page name | same value on every member of the volume | own Index |
| `TITEL` | may link an editions overview (`[[Kinder und Hausmärchen]]. Band 1`) — a *title*, not a container; never used for grouping | | |
| `BEARBEITUNGSSTAND` | quality (`fertig`, `korrigiert`, `unkorrigiert`, `unvollständig`) | | |

So on de the container is the **first wikilink inside `HERKUNFT`** when it
is an existing ns0 page; the order is the `VORIGER`/`NÄCHSTER` chain; the
Index name groups a scanned volume. `{{Andere Version}}` lists other
editions of the same text (pattern I).

### 3.4 Title hierarchy, redirects, TOC links

- Subpage titles `A/B/C`; depth up to 9 on en.
- Root-title redirects to subpages (fr `Clarté d’âmes` →
  `La Légende des siècles/Clarté d’âmes`) are how those wikis make texts
  inside collections findable; the `redirect` dump gives them as aliases.
- TOC links: `.ws-summary` where it exists (no/en/fr, not de), the
  `sammendrag`/`sommaire` parameter, else the links in the root page body
  in document order (de collections: tables). Diktsamling links 9 existing
  poems and 116 red links; red links must count towards `incomplete` but
  not become parts.

### 3.5 Dumps

`nowikisource-latest-*` offers `page`, `redirect`, `page_props`,
`pagelinks` and `templatelinks` (both need `linktarget` for the target
titles), `categorylinks`, `pages-articles.xml.bz2`, `all-titles-in-ns0.gz`.
Transcluding `Page:` pages with `<pages>` records them in `templatelinks`,
which gives the scan hub without parsing wikitext; header parameters still
need the wikitext.

## 4. Resolution algorithm (pipeline; the app runs the same rules live)

Input per wiki: ns0 non-redirect pages with wikitext; redirects; pagelinks;
templatelinks; per-wiki config (section 5.3).

**Step 0 — Exclusions** (PLAN.md 5.2 step 1, extended): disambiguation and
versions pages (page prop *and* per-wiki templates), author pages in ns0
(de, fi), colon-prefix reference works (config list, see 8.6), the
`Texte entier` kind of duplicate subpages, portals and project pages in
ns0.

**Step 1 — Structure record per page**: `title`; `container` (the semantic
parent: the header's container link — no `tittel`, fr `titre`, en `title`
(relative links resolved), de first link in `HERKUNFT` — if it is an
existing, non-excluded ns0 page other than the page itself; otherwise
null); `prev`, `next` (resolved titles); `index` (from `<pages index=>`,
de `INDEXSEITE`, or the transcluded `Page:` titles); `scan_from`,
`scan_to` (integers from `<pages>`); `index_type` (book, collection,
journal, dictionary, …); `toc_links` (ordered ns0 link targets from
`.ws-summary` / summary parameter / body, with red links kept and flagged);
`bytes`; `has_pages_tag`; quality.

**Step 2 — Parent assignment**, first rule that applies:

1. `container` if set → the page is a part of that page's work (the
   container may itself have a container; walk up, cycle-safe, max depth 6).
2. Title hierarchy: parent = the longest existing proper title prefix
   `A/B` of `A/B/C`. If no ancestor exists, the page joins the **virtual
   root** `A` (pattern D).
3. TOC membership: the page is in `toc_links` of exactly one root page
   that is TOC-like (links ≥ 3 ns0 pages, has `HERKUNFT = off` or an Index
   `Type`, is not a versions page) **and** shares that page's `index` or
   is reached from it by the prev/next chain. This catches members whose
   header lacks a container link.
4. Otherwise the page is a work of its own.

Rules 1 and 2 agree for ordinary subpage books; rule 1 is what makes
patterns A and B work; rule 3 is the fallback for sloppy headers. A
prev/next chain alone never merges root pages into one work (two
root-page volumes with `HERKUNFT = off` stay two works in v1; see 8.5).

**Step 3 — Order of the children of a node**, first source that covers all
children: the prev/next chain among siblings; the parent's `toc_links`
order; ascending `scan_from` within the same `index`; natural sort of the
titles (so `Chapter 2` precedes `Chapter 10`). Record `order_source`.

**Step 4 — Classification**: a node with children or without a parent is a
work. Its children are parts, recursively, with `depth`. Part kinds:
`volume` (has children), `chapter`, `text`, `front` (the root's own text
beyond header and TOC, PLAN.md 7.1 step 3), `toc` (a TOC page with no text
of its own: not downloaded, only structure). A part is a **text**
(searchable) when its display title does not match the per-language
chapter-label pattern (config `chapter_label_regex`: `^(Chapter|Kapitel|
Kapittel|Chapitre|Capitolo|Acte?|Akt|Letter|Brief|Part|Partie|Book|Band|
Bind|Tome|Volume)\b`, roman numerals, bare numbers) — "Ozymandias" and
"Ved Rundarne" are texts, "Chapter 1", "Første akt" and "Zweiter Band" are
not. Display title of a part: header `current`/`section`/`TITEL`, else the
link text in the parent's TOC, else the last title segment.

**Step 5 — Work type**: Index `Type` → `book`, `collection`, `periodical`
(journal), `reference` (dictionary), `document` (phdthesis); else from
structure (a work whose parts are mostly texts → `collection`; a single
text → `poem`/`article`/… via categories and header type); PLAN.md 5.2's
Wikidata and category mappings stay as further sources. Works with
`part_count` above `reference_threshold` (default 2,000) and no Index type
are `reference`.

**Step 6 — Virtual roots** (pattern D): the work's `page_title` is the
missing title (`Die Heimath in der neuen Welt`), `is_virtual = 1`,
`entry_title` = the first child in reading order; display title from the
common prefix, cleaned of volume suffixes; metadata (author, year) from
the children's headers; the source link points to the entry page.

**Step 7 — Oversized pages** (pattern C): `oversized = bytes >
oversized_bytes` (default 300 kB of wikitext, about 100 k characters of
text). The pipeline only flags; the app splits at download time (section
7.3). Käthe shows that headings cannot be assumed.

**Step 8 — Completeness**: `missing_part_count` = red links in the TOC;
`incomplete = 1` when missing parts exist or the per-wiki incomplete
template is present. Diktsamling: 9 parts, 116 missing, incomplete.

## 5. Catalog changes (schema_version 2)

### 5.1 Tables

```sql
-- works: as PLAN.md 5.3, plus
  structure TEXT NOT NULL,     -- single | subpages | linked | mixed | virtual
  is_virtual INTEGER NOT NULL DEFAULT 0,
  entry_title TEXT,            -- page to open/link when is_virtual (else = page_title)
  part_count INTEGER NOT NULL, -- existing parts; a single-page work has 1
  text_count INTEGER NOT NULL, -- searchable parts
  missing_part_count INTEGER NOT NULL DEFAULT 0,
  parts_in_catalog INTEGER NOT NULL DEFAULT 1,  -- 0 for reference works and works over parts_cap
  oversized INTEGER NOT NULL DEFAULT 0,
  bytes_total INTEGER,         -- sum of wikitext bytes, for the size estimate
  index_titles TEXT;           -- JSON list of Index pages (quality, scans)
-- type gains the key 'reference'.

CREATE TABLE parts(
  work_id INTEGER NOT NULL, ord INTEGER NOT NULL,   -- reading order, 0-based, depth-first
  depth INTEGER NOT NULL, parent_ord INTEGER,       -- tree
  page_title TEXT NOT NULL,                         -- exact title; '#anchor' never stored
  display_title TEXT NOT NULL,
  kind TEXT NOT NULL,                               -- volume | chapter | text | front | toc
  searchable INTEGER NOT NULL DEFAULT 0,
  bytes INTEGER, scan_from INTEGER, scan_to INTEGER, index_title TEXT,
  PRIMARY KEY(work_id, ord));
CREATE INDEX parts_title ON parts(page_title);      -- open-by-URL, link rewriting

CREATE TABLE aliases(alias_title TEXT PRIMARY KEY, work_id INTEGER NOT NULL, ord INTEGER);
  -- redirects to a work or a part; used by search and open-by-URL

CREATE VIRTUAL TABLE parts_fts USING fts5(display_title, work_title, author_names, content='');
  -- only searchable parts
-- excluded: keeps every page excluded in step 0, with reason; pages absorbed as parts are
-- not "excluded" and do not appear here.
```

### 5.2 Size budget

Storing every subpage of en as a part would add about 608,000 rows. With
`parts_cap` = 2,000 (reference works and giant periodicals get
`parts_in_catalog = 0`), the five largest trees alone drop 158,000 rows and
the remainder is an estimated 300,000–350,000 rows, roughly 5–8 MB gzipped
on top of the en catalog; fr is similar, de and no are small. The survey
(section 6) measures this exactly before the schema is frozen; the owner
sets the cap (decision 8.4). The app needs catalog parts for: the TOC and
size estimate before download, search for texts, and pattern A works
(whose members cannot be derived from titles). Everything else can fall
back to live resolution.

### 5.3 Per-wiki config additions (`pipeline/config/{wiki}.yaml`)

```yaml
structure:
  container_params: [tittel]            # fr: [titre]; en: [title]; de: [HERKUNFT]  (first wikilink)
  prev_next_params: [[prev, next]]      # en also [previous, next]; de [[VORIGER, NÄCHSTER]]
  current_params: [current]             # en: [current, section]; de: [TITEL]
  index_params: [index]                 # de: [INDEXSEITE]
  toc_sources: [ws-summary, sammendrag, body-links]   # fr: sommaire; en: ws-summary, remarks
  chapter_label_regex: '^((Første|Anden|Andet|Tredje|Fj[æe]rde|Femte|\d+\.?) (kapit+el|akt|del|bind|brev)|Kapit+el|Akt|Del|Bind|Brev)\b'  # starting point, tuned from the survey
  oversized_bytes: 300000
  parts_cap: 2000
  reference_threshold: 2000
  whole_text_subpages: []               # fr: [Texte entier]
exclude_title_prefixes: []              # de: [RE:, MKL1888:, ADB:, BLKÖ:, Zedler:, Boetticher:, Haan:, …]
```

The survey prints every colon prefix shared by ≥ 100 root pages so the de
list is complete and nothing is dropped by accident (titles with a colon
are common and legitimate: `Woher das Sprichwort: …`).

## 6. Structure survey (new first step of WP1, "WP1a", 3–4 days)

Before the schema is frozen, run the extractor of section 4 steps 1–5 over
the pages-articles dumps of no, is, de, fr and en and write
`report/{wiki}-structure.md` with:

- counts per pattern A–I and per `structure` value; parent-rule histogram
  (container / hierarchy / TOC / own) and `order_source` histogram;
- container links that point outside ns0 or to excluded pages (how often
  rule 1 fails, e.g. Racine's `Livre:` link);
- prev/next chains that cross `index` boundaries (fr Hugo), as a measure
  of how far chains can be trusted;
- orphan trees (count, examples), oversized pages (count, largest), red
  link ratios in TOCs;
- colon prefixes with ≥ 100 root pages (de reference works);
- the parts-table row count and gzipped size per wiki at `parts_cap` ∈
  {500, 2,000, ∞};
- 30 random works per pattern with their resolved TOC, for eyeballing.

Acceptance of WP1a: the report exists for the five wikis; the eyeball
samples for patterns A, B, D and E are correct for at least 27 of 30; the
owner has set the thresholds in section 8.

## 7. App changes by work package

### 7.1 WP2 (browse, search, detail)

- Browse lists and facets show works. The type facet gains `collection`
  and `reference`; the default filter hides `reference` and `incomplete`.
- Search returns two groups: **Works** and **Texts** ("Ved Rundarne (1864)
  · in Diktsamling"), matching `works_fts`, `parts_fts` and `aliases`.
  Opening a text opens the reader at that part when the work is
  downloaded, else the work detail with the part highlighted and a
  "download this text" action (parts are downloadable individually, R4).
- Work detail: TOC tree from `parts` (depth, kinds), "9 of 125 texts
  transcribed" when `missing_part_count > 0`, size estimate from
  `bytes_total`. When `parts_in_catalog = 0`, the TOC loads live with a
  filter box (reference works).
- Open-by-URL: resolve a pasted Wikisource URL through `parts.page_title`
  and `aliases` to (work, part), so a member page URL opens its book.

### 7.2 WP3 (content)

- `resolveParts(work)`: use catalog `parts` when present; else run section
  4 live on Parsoid HTML (`data-mw` holds the same header parameters;
  `.ws-summary`/body links for the TOC; the `Page:` titles in page markers
  for the Index). A refresh re-resolves live and merges new parts.
- Download fetches each part by its own page title (root pages and
  subpages alike). `Part` stores `pageTitle`, `revId`, `sourceUrl`,
  `kind`, `depth`, `parentIndex`; `DownloadedWork` stores `structure`,
  `entryTitle`.
- Attribution (R11) lists every wiki page the work consists of, with a
  link to each page and its history; for one-page works it is one line.
- Link rewriting (PLAN.md 7.2 rule 3): a link to **any page that is a part
  of the same work** (not only subpages) becomes `app://part/{index}`; the
  de member pages link to their neighbours and to the TOC root.
- Oversized pages (pattern C): split into synthetic parts at download
  time: at `h2`/`h3` headings when at least three exist; else at
  chapter-like blocks (a short centred or bold paragraph followed by a
  page marker or a `<br>` gap); else every N page markers so that no part
  exceeds `oversized_chars` (100 k). Synthetic parts get `kind =
  synthetic`, `anchor` = the first element id, display title = heading
  text or "Pages 1–40". Locators stay `(partId, charOffset)`; the part is
  the synthetic one.

### 7.3 WP4 (reader)

TOC drawer renders the tree (volumes collapsible); the footer shows the
part's display title; "Part of: Diktsamling" in the reader's info sheet
for texts opened from search. No other reader change.

### 7.4 Fixtures (PLAN.md 7.3)

Add raw Parsoid fixtures and expected structure JSON for: de
`Der Sagenschatz des Königreichs Sachsen` root + two members (A); de
`Kinder- und Haus-Märchen Band 1 (1857)` + `Der Froschkönig … (1857)` (A,
I); no `Diktsamling` + `Ved Rundarne (1864)` + one neighbour (A, red
links); fr `La Légende des siècles` root + `/Clarté d’âmes` +
`L’Épopée du ver` (B); de `Käthe` (C, already saved); de `Die Heimath in
der neuen Welt/Zweiter Band` + `/Erster Band` (D); en Hutchinson Shelley
root + `/Ozymandias` + `Ozymandias` + `Ozymandias (Shelley)` (E); fr
`Madame Bovary` + `/Première partie` + `/Première partie/1` (H, already
saved); an en reference work root (G) by title only, no content.

## 8. Decisions (taken by the owner on 2026-10-31)

The owner accepted all ten recommendations below as written. Section 10.8
holds the quality and completeness decisions.

1. **Group root-page members under their collection** (patterns A, B):
   yes. This reverses WP0's "list each text as its own work" for WP1; de
   is unusable as a book catalog otherwise.
2. **Members become searchable texts, not browse entries**: yes. Browse
   shows works; search shows works and texts.
3. **Where structure is resolved**: pipeline is authoritative (catalog
   `parts`), the app re-resolves live as fallback and refresh.
4. **Parts in the catalog**: all works up to `parts_cap` = 2,000 parts;
   reference works and larger works carry counts only. Confirm after the
   survey reports the exact size; the alternative is a cap of 500, which
   drops long periodicals from the offline TOC.
5. **Virtual roots** for orphan subpage trees: yes. Merging root-page
   volumes by prev/next alone: no in v1 (survey counts how many such chains
   exist; revisit with numbers).
6. **Reference works**: de colon prefixes stay excluded, with the list
   extended from the survey; subpage-tree reference works (en, fr) become
   `reference` works without catalog parts, hidden by the default filter.
   A lookup feature is WP8.
7. **`Texte entier` and similar**: skipped as parts (as decided in WP0);
   later possibly used as a one-request download.
8. **Oversized threshold**: 300 kB wikitext / 100 k characters, app-side
   splitting with the fallback ladder in 7.2.
9. **Chapter-label patterns** per language start from the list in section
   4 step 4 and are tuned from the survey's samples.
10. **Opening a text from search**: straight into the reader when the work
    is downloaded; otherwise the detail screen with the part highlighted.

## 9. PLAN.md edits (first task of WP1)

- 3.2: replace "A *work* is a root page …" with the work/part/text model
  (section 2) and the structure signals (section 3); add virtual roots and
  reference works.
- 5.1: inputs gain `pagelinks`, `templatelinks`, `linktarget`, `redirect`
  (already listed) with their use; 5.2 gets the resolution steps (section
  4) between steps 1 and 2 and the survey as a step 0; 5.3 becomes
  schema_version 2 (section 5.1); 5.5 gains the `structure` block (5.3).
- 6.2: `Part` and `DownloadedWork` fields (7.2).
- 7.1: replaced by "catalog parts first, live resolution with the same
  rules second"; step 6 (oversized) gets the fallback ladder.
- 7.2 rule 3: links to any part of the same work.
- 8.6: TOC tree; "Part of" info.
- 9: WP1 gains WP1a and these acceptance criteria: Sagenschatz is one work
  with about 990 parts in TOC order; Diktsamling is one work with 9 parts,
  116 missing, `incomplete`; `Ved Rundarne (1864)` is found by search as a
  text of Diktsamling; `Die Heimath in der neuen Welt` is one virtual-root
  work with three volumes; `Ozymandias` finds the Hutchinson text (and any
  other edition on en); `Käthe` is flagged `oversized`; `Kinder und
  Hausmärchen` (overview) is not a work and `Kinder- und Haus-Märchen Band
  1 (1857)` is; the en catalog grows by no more than the agreed budget.
- 3.4, 5.2 step 4 and 5.4: replaced by the quality and completeness policy
  of section 10 (tiers, completeness rules, hard exclusions, catalog fields,
  app defaults); 6.1 gains the per-wiki `show_unknown_by_default` manifest
  field; WP1 acceptance gains: "the default view of the no catalog holds
  about 1,500 works and none of them is raw OCR; the excluded table names
  every hard exclusion".
- 11 (risks): "header container links are wrong or missing" → rule-3
  fallback and the survey's failure counts; "editions merged by mistake"
  → rule 1 never climbs through `TITEL`/title fields; "good texts drowned
  by raw OCR" → section 10 defaults, measured per wiki by the survey.
- 13: add the later items: editions grouping (versions pages, `{{Andere
  Version}}`, Wikidata P629/P747, it `Opera:` namespace), "also in"
  memberships, reference lookup, chain merging.

## 10. Quality and completeness policy (decided 2026-10-31)

### 10.1 Principle

Two stages. The pipeline excludes only what nobody can read. The catalog
records quality and completeness for every work and every part. The app
hides unproofread and incomplete works by default and lets the reader opt
in. Reasons: the signals are uneven (typed-in texts carry none), a hard
cut would empty the small wikis and drop good texts for lack of a label,
and a reader may knowingly want a raw text; the toggle gives breadth
without presenting bad texts as good.

### 10.2 Signals (verified 2026-10-31)

- **Per-page ProofreadPage level 0–4**, identical on all wikis: in the
  `Page:` wikitext as `<pagequality level="n" user="…" />` (dump), and in
  Parsoid as `data-page-quality` (en) or the `quality` parameter of the
  pagenum template in `data-mw` (no fixtures: Peer Gynt mixes levels 3 and
  1, Arne 3 and 4). The app can therefore judge a text it opens by URL.
  Level 0 means "without text" (blank pages) and is left out of ratios.
- **de `BEARBEITUNGSSTAND`** on every text: `fertig`, `korrigiert`,
  `unkorrigiert`, `unvollständig` (also `teilkorrigiert`; verify the value
  list in the survey). Measured over 53,893 `Textdaten` texts: fertig 44,469
  (82.5 %), korrigiert 6,889 (12.8 %), unkorrigiert 318 (0.6 %),
  unvollständig 2,187 (4.1 %). Only 751 de texts use `<pages>`; the rest
  transclude scans through templates (`{{SeitePR}}`, `{{Seite}}`), so the
  level computation must follow those or use `templatelinks`.
- **Text-quality templates**: fr `{{TextQuality}}` on 178,361 pages (100 %
  100,181; `Textes validés` 53,433; 75 % 18,247; 50 % 3,331; 25 % 2,810);
  en `{{TextQuality}}` 15,654; it SAL 25/50/75/100 %.
- **Index progress** (enumerated on en, mul, fr, es, da; F§5): `MS`, `OCR`,
  `L` (es `L dañado`) mean a faulty or missing text layer → hard exclusion;
  `C` means the text is complete but unproofread.
- **Incompleteness**: en `{{incomplete}}` (2,758 pages), no `{{ufullstendig}}`,
  de `unvollständig`; red links in the TOC (`missing_part_count`); missing
  `Page:` pages inside a transcluded range (`missing_page_count`; 64 works
  on no); coverage of the Index's text pages, only for works that *are*
  the Index's book (header container link = work root, Index type book).
- **Junk markers on typed-in texts**: cleanup or deletion templates (no
  `opprydning`, `slett`, `sletting`), size under 300 bytes, placeholders
  ("Ennå ikke fri"); Wikisource's own translations (no `oversetter =
  Wikikilden`) are kept but labelled.

### 10.3 Tiers and completeness

Computed over the *text pages* (levels 1–4) a work transcludes; precedence
of sources: page levels, then a status or text-quality template, then
Index progress, else unknown. A collection aggregates over all its parts;
every part also gets its own tier, so a proofread poem inside an
unfinished collection keeps its standing.

| tier | rule |
|---|---|
| `validated` | every text page at level 4; or de `fertig`; or a 100 % / validated text-quality template |
| `proofread` | every text page at level 3 or better; or at least 90 % at level 3 or better (the app shows "n of m pages not yet proofread" from `quality_pages`); or de `korrigiert`; or a 75 % template |
| `unproofread` | any other work that has a signal |
| `unknown` | no signal at all (typed-in text without a template) |

`complete = 0` when: an incomplete template or de `unvollständig` is
present; `missing_part_count > 0`; `missing_page_count > 0`; Index progress
is `MS`/`OCR`/`L`/`X`; or the work is the Index's book and covers under
95 % of that Index's text pages. Otherwise `complete = 1` (unknown
completeness counts as complete, since nothing says otherwise).

### 10.4 Hard exclusions (pipeline)

A work is left out of the catalog, with its reason in `excluded`, only
when: it has no part with text; all referenced scan pages are missing or at
level 0; its Index progress says the source is faulty; or a junk marker
applies (cleanup or deletion template, under 300 bytes, placeholder).
Everything else enters the catalog with its flags.

### 10.5 Catalog fields (part of schema version 2, with 5.1)

```sql
-- works (replaces PLAN.md 5.3 quality / incomplete)
  quality TEXT NOT NULL,          -- validated | proofread | unproofread | unknown
  quality_source TEXT,            -- page-levels | status-template | textquality | index-progress | none
  quality_pages TEXT,             -- JSON {"4":n,"3":n,"2":n,"1":n,"0":n,"missing":n}
  complete INTEGER NOT NULL,      -- 1 unless a rule in 10.3 says otherwise
  incomplete_reason TEXT,         -- template | missing-parts | missing-pages | index-progress | coverage
  missing_part_count INTEGER NOT NULL DEFAULT 0,
  missing_page_count INTEGER NOT NULL DEFAULT 0,
  coverage REAL,                  -- transcluded / existing text pages of the Index, when applicable
  own_translation INTEGER NOT NULL DEFAULT 0;
-- parts: quality TEXT, quality_pages TEXT, complete INTEGER
-- manifest.json per wiki: show_unknown_by_default (bool), work counts per tier
```

### 10.6 App behaviour

- Default filter: `quality IN (validated, proofread)`, plus `unknown` where
  the wiki's manifest says so, and `complete = 1`.
- Filter chips, off by default: "Include texts not yet proofread", "Include
  incomplete works", "Include unverified texts". The chips are also
  reachable from Settings.
- Search collapses hidden matches into one row: "n more texts hidden by your
  quality filter"; nothing disappears silently.
- Labels on the detail screen, in the reader's info sheet and for texts
  opened by URL: "Validated", "Proofread", "Proofread, 3 of 210 pages not
  yet proofread", "Not proofread, may contain OCR errors", "Not verified
  against a scan", "Incomplete: 116 of 125 poems not yet transcribed",
  "Wikisource translation".
- The wiki picker shows the number of works in the default view per wiki.

### 10.7 Measured state (2026-10-31; raw data in `docs/structure/`)

**no, per work** (3,845 works grouped by title hierarchy from the
pages-articles dump of 2026-10-01; 54 `Peker` pages skipped):

| tier | works | share |
|---|---|---|
| validated | 299 | 8 % |
| proofread | 1,202 | 31 % |
| mostly proofread (≥ 90 %) | 22 | 1 % |
| unproofread | 2,041 | 53 % |
| unknown (no scan page) | 252 | 7 % |
| scan pages without text | 29 | 1 % |

1,835 of the 2,041 unproofread works are level 1 from the first page to
the last: "unproofread" on no means raw OCR. With the defaults, the no
shelf holds 1,523 verified works plus about 220 unknown ones after the junk
rules; it hides 2,041 raw works, 29 empty ones and about 30 junk pages.

**The 252 unknown works on no** are typed-in texts, mostly short (median
about 2 KB; 152 carry the `topp` header). Largest groups: Wergeland's poems
from *Digte, 1ste Ring* (1829), hymns, children's and folk songs,
Wikikilden's own Chekhov translations (10, one reworked from machine
translation), party programmes, modern regulations, court decisions; some
come from reliable digital editions (DokPro). Junk rules catch 18 with
incomplete, cleanup or deletion templates, 14 under 300 bytes and one
placeholder. None of a 25-text random sample was OCR garbage; the risk is
typos and unpublished translations.

**Scan pages per level, all configured wikis** (quality categories):

| wiki | not proofread | proofread | validated |
|---|---|---|---|
| no | 48 % | 45 % | 3 % |
| da | 79 % | 15 % | 4 % |
| fi | 43 % | 37 % | 7 % |
| es | 37 % | 35 % | 20 % |
| en | 28 % | 42 % | 21 % |
| mul | 17 % | 65 % | 11 % |
| fr | 17 % | 50 % | 27 % |
| it | 14 % | 30 % | 48 % |
| sv | 10 % | 60 % | 26 % |
| de | 3 % | 23 % | 71 % |
| is | 0 % | 88 % | 3 % |

de has practically no unknown texts: every text declares a status, and
95 % are `fertig` or `korrigiert`. en has about 153,000 header pages
without a scan (over a third of its header pages), many classics among
them, so hiding unknown there would gut the catalog. fr has text-quality
templates on 178,000 pages, most at 100 % or validated.

### 10.8 Decisions (owner, 2026-10-31)

1. Unproofread and incomplete works are hidden by default; the reader can
   opt in per filter chip.
2. Unknown works are shown by default on **no**, after the junk rules and
   labelled "Not verified against a scan". For every other wiki the owner
   sets `show_unknown_by_default` from the survey's numbers (en: about a
   third of texts, classics included; de: none).
3. The pipeline hard-excludes only the unreadable and the junk (10.4).
4. The survey (WP1a) adds per wiki: works per tier, unknown count with a
   30-text random sample, junk-rule yields, missing-page counts, and the
   size of the default view.

## Appendix: how the evidence was gathered

Live checks on 2026-10-31 through the WP0 helper (`pipeline/wp0/wmhttp.py`,
project User-Agent, cached): Parsoid HTML of no `Diktsamling`; de `Wie die
Raute in das sächsische Wappenschild gekommen ist`, `Der Froschkönig oder
der eiserne Heinrich (1857)`, `Kinder- und Haus-Märchen Band 1 (1857)`,
`Die Heimath in der neuen Welt/Zweiter Band`; en `The Complete Poetical
Works of Percy Bysshe Shelley (ed. Hutchinson, 1914)/Ozymandias`; wikitext
of en `Ozymandias`, `Ozymandias (Shelley)`, fr `L’Épopée du ver`, and
`MediaWiki:Proofreadpage_index_data_config.json` on en, no, fr; redirect
resolution for the fr and de titles named above; `all-titles-in-ns0.gz`
from `dumps.wikimedia.org` for nowikisource, dewikisource, frwikisource,
enwikisource (counts include redirects). Header parameters were read from
the `data-mw` attributes of the 17 WP0 fixtures in
`app/src/test/fixtures/`. Quality data (section 10): `prop=categoryinfo` on
the five ProofreadPage quality categories of the 11 wikis
(`pipeline/wp0/quality_categories.py`); CirrusSearch `totalhits` for the
template and `insource:` queries listed in `docs/structure/search-counts.txt`
(`pipeline/wp0/search_counts.py`); the nowikisource pages-articles dump of
2026-10-01 parsed by `pipeline/wp0/quality_works_no.py` (output
`docs/structure/no-quality-report.txt`).
