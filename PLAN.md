# Bibliotheke for Wikisource books — Implementation Plan

App name: **Bibliotheke for Wikisource books** (see 1.2).

Status: approved plan, ready for implementation.
Audience: the implementing engineer/agent. Decisions in this document are final
and complete. Where a detail is marked **VERIFY**, confirm it against the
live service during work package 0 before building on it.
(WP0, 2026-10-31: all VERIFY items are resolved; passages changed by WP0 are
marked "(WP0 …)" and explained in `docs/wp0-findings.md`, cited as "F§n".)
(WS, 2026-10-31: the work-structure plan `docs/work-structure-plan.md`,
decided by the owner, defines how Wikisource pages become works, parts and
searchable texts, and the quality and completeness policy; passages it
changed are marked "(WS, 2026-10-31 …)" and cite it as "WS§n". Decisions
D1–D9 are unchanged.)

---

## 0. How to work from this plan

- Build in the work packages of section 9, in order. Each package ends with its
  acceptance criteria met and a commit.
- Commit early and often (at least after every package, preferably after every
  coherent sub-step). Keep a `STATUS.md` at the repo root with: current package,
  what is done, what is in progress, known problems. Update it before stopping
  work for any reason, so the work can be resumed after an interruption.
- Do not depend on services that block automated clients. In particular, do not
  depend on the WS Export tool (ws-export.wmcloud.org): it sits behind Anubis
  bot protection and refused automated access during planning. Production
  Wikimedia wikis (`*.wikisource.org`, `wikidata.org`, `dumps.wikimedia.org`,
  `upload.wikimedia.org`, and `thumb.wikimedia.org`, which now serves image
  thumbnails (WP0, F§2.9)) are fine to call with a proper User-Agent.
- Follow the Wikimedia User-Agent policy: every request from the app and from
  the pipeline carries a UA of the form
  `Bibliotheke/<version> (https://<repo URL>; develop@robertemilberge.no) okhttp/x`.
  The contact address `develop@robertemilberge.no` is the project owner's
  business address, chosen by the owner for this purpose. It is the only
  personal contact detail allowed anywhere in the code or metadata, and it is
  used only in the User-Agent, the store-listing contact fields and the
  licence/contact section of the README. Never use any other address.

---

## 1. Product summary

An Android app for reading the public-domain and freely licensed texts hosted on
the Wikisource projects. The user browses or searches a catalog of complete
works across all Wikisource language editions, downloads works for offline
reading, and reads them in a paginated, book-like reader with sideways swiping,
bookmarks, themes, fonts, and optional display of the original edition's page
numbers and line numbers exactly as Wikisource shows them.

### 1.1 Functional requirements

R1. **Content scope**: all Wikisource language editions (83 open wikis in the
    sitematrix at WP0, F§1) plus the multilingual edition at
    `wikisource.org`. Only works that are actually
    readable are listed (see 5.4 for the completeness filter).
    (WS, 2026-10-31, WS§10: only unreadable texts and junk are excluded;
    unproofread and incomplete works are listed, flagged and hidden by the
    app's default filter, 5.4.)
R2. **Browse facets**: language/wiki, genre, century and year, author, work type,
    text quality. Combinable. Fast and fully offline once a catalog is downloaded.
R3. **Search**: offline full-text search over titles and authors in downloaded
    catalogs; online search against a wiki's search API when connected.
R4. **Download**: whole work or selected chapters, resumable, background,
    size estimate before starting, delete per work. Everything needed to read
    (text, images, footnotes) is stored locally.
R5. **Reader**: pages turn by horizontal swipe. Chapter list, progress, seek
    slider, tap zones. Last position restored per work.
R6. **Bookmarks**: unlimited per work, each with excerpt and optional note.
    Bookmarks and positions survive font/size/theme changes and app updates.
R7. **Appearance settings**: colour schemes (light, sepia, dark, true black,
    follow system); font family from a bundled set; font size; line height;
    margins; justification; hyphenation on/off.
R8. **Page marks** (as on the Wikisource pages): optional display of the
    original edition's page breaks with their page numbers, which Wikisource
    renders from the scanned source. Modes: off / inline marker / margin
    marker. Tapping a mark offers the scan page online.
R9. **Line numbers**: optional display of (a) line numbers present in the source
    markup and (b) generated line numbers for verse (every 5 lines, configurable).
R10. **Footnotes** appear as popups (bottom sheet) and are also reachable at the
    end of the chapter.
R11. **Attribution**: each work shows its source page link, licence, and the
     Wikisource contributors notice required by CC BY-SA. The app's About screen
     credits Wikisource and Wikidata and carries the non-affiliation notice
     from 1.2.
R12. **UI languages**: English, Norwegian Bokmål, Norwegian Nynorsk, French,
     German, Italian, Swedish, Danish, Finnish, Icelandic, Spanish. Bokmål and
     Nynorsk are both first-class from the first release; many early users will
     be Norwegian. The app follows the system language and offers an in-app
     language override.

### 1.2 Name, branding and trademark rules

- Full name: **Bibliotheke for Wikisource books**. "Bibliotheke" is the
  distinctive part and is never translated or restyled. "for Wikisource books"
  is a descriptive tag that uses the Wikisource wordmark only to describe the
  content, which the Wikimedia trademark policy permits without a licence.
- Launcher label: `Bibliotheke` (launcher labels truncate after roughly 12
  characters, so the short form is used there in every language).
- Google Play title is limited to 30 characters and the full name is 32. Use
  `Bibliotheke for Wikisource` as the Play title and start the short
  description with the words "books" and "every language", e.g. "Every book
  on every Wikisource, in every language, offline." (Confirmed by the owner.)
- The descriptive tag is localised in the long app name shown on the About
  screen and in store listings, e.g. nb/nn `Bibliotheke for Wikisource-bøker`,
  de `Bibliotheke für Wikisource-Bücher`, fr `Bibliotheke pour les livres
  Wikisource`. Keep "Wikisource" in full, properly capitalised, in every
  language.
- Non-affiliation notice, shown on the About screen and in every store
  listing, localised: "Bibliotheke is an independent app and is not
  affiliated with or endorsed by the Wikimedia Foundation. Wikisource is a
  trademark of the Wikimedia Foundation."
- Do not use the Wikisource or Wikimedia logos in the app icon, splash or
  store graphics. Design an original icon (a book or shelf motif is fine).
  The Wikisource wordmark may appear in plain text only.
- Application id: `app.bibliotheke.wikisource` (confirmed by the owner).
- Never describe other apps in the listing or in-app text, and do not claim
  to hold "all" texts; say "every Wikisource language edition" and let the
  catalog filter (5.4) be explained in the About screen.

### 1.3 Non-functional requirements

N1. Offline-first: every screen except online search and downloads works with
    no network.
N2. Smooth page turns (target 60 fps on a mid-range 2021 device).
N3. Works of any size open (encyclopedias, bibles, multi-volume novels): memory
    bounded by loading one chapter at a time.
N4. Min SDK 26 (Android 8.0), target latest stable. Phone and tablet layouts.
N5. Accessible: TalkBack labels, respects system font scale in UI, high
    contrast themes, 48dp touch targets.
N6. Polite to Wikimedia: serial requests per host, exponential backoff on 429/5xx,
    conditional requests where possible, no scraping of HTML pages when an API
    exists.

---

## 2. Decisions already made

D1. **Two components**: a *catalog pipeline* (Python, runs on a schedule in CI)
    and the *Android app* (Kotlin). The pipeline produces per-wiki SQLite catalog
    files the app downloads. Rationale: each wiki has its own category tree and
    header template, so consistent genre/year/author facets cannot be derived
    live on the device; centralising the per-wiki knowledge in the pipeline lets
    it be fixed without app releases.
D2. **Own content fetching and normalising**, not WS Export EPUBs. We fetch
    Parsoid HTML per page and normalise it into our own HTML. Rationale: full
    control over styling, page marks and line numbers; no dependency on a
    bot-protected tool.
D3. **Reader renders HTML in a WebView paginated with CSS multi-column layout.**
    Rationale: Jetpack Compose cannot paginate rich HTML (poems, tables,
    footnotes, images). Fallback if this stalls: the Readium Kotlin toolkit.
D4. **Reading positions are locators, not page numbers**: `(partId, charOffset)`
    into the normalised text of a chapter. See 7.4.
D5. **Stack**: Kotlin, Jetpack Compose + Material 3, Room, DataStore,
    WorkManager, OkHttp + Retrofit (or Ktor) with kotlinx.serialization, Jsoup,
    Coil, Hilt, WebView with WebViewAssetLoader. Single-module to start; split
    into `:app`, `:core-data`, `:core-content`, `:feature-reader` when it grows.
D6. **Catalog hosting**: GitHub Releases (decided). The pipeline's GitHub
    Actions workflow publishes `manifest.json` and the gzipped per-wiki SQLite
    files as assets of a rolling release tagged `catalog-latest`, and the app
    reads them from the release's asset URLs. Keep the base URL in build config
    so it can change without code edits. Note that only the small index files
    are hosted this way; texts and images always come straight from Wikisource
    and upload.wikimedia.org to the device.
D7. **Norwegian**: UI resources for both `nb` and `nn`, plus `values-no` as a
    copy of `nb` for devices that still report the legacy `no` locale.
D8. **Licence**: GPL-3.0-or-later for the app and the pipeline. Add `LICENSE`
    at the repo root and SPDX headers (`// SPDX-License-Identifier: GPL-3.0-or-later`)
    to source files. Bundled fonts keep their own OFL/Apache licences, listed
    in the About screen. Every third-party dependency must be GPL-compatible.
D9. **Name**: Bibliotheke for Wikisource books, with the branding rules in
    1.2. Decided by the project owner; do not revisit.

---

## 3. Domain knowledge: how Wikisource is organised

Confirm specifics against the live wikis in WP0, but this is the shape.

### 3.1 Wikis
- One wiki per language: `en.wikisource.org`, `no.wikisource.org`,
  `fr.`, `de.`, `it.`, `sv.`, `da.`, `fi.`, `is.`, `es.`, etc.
- `no.wikisource.org` hosts Norwegian in both Bokmål and Nynorsk and older
  Danish-Norwegian. There is no separate `nn.wikisource.org`. Where Wikidata
  gives P407 (language of work) on an edition, expose `nb`/`nn`/`da` as a
  sub-facet for the Norwegian wiki; otherwise show "Norwegian".
- `wikisource.org` (the multilingual edition) hosts languages without their own
  wiki. Treat it as a wiki with code `mul`.
- The list of all Wikisource wikis is available from the Wikimedia sitematrix
  API: `https://meta.wikimedia.org/w/api.php?action=sitematrix` (filter
  `code == "wikisource"`). (WP0, F§1: this misses `wikisource.org`, which is
  under `specials` with `code: "sources"`, dbname `sourceswiki`; skip
  entries marked `closed`.)

### 3.2 Namespaces and page types
- Main namespace (0): the readable texts. (WS, 2026-10-31, WS§1–2: this
  replaces "a *work* is a root page; chapters are its subpages", which holds
  for books on en, fr and no but not for collections of root pages, the
  normal book structure on de.) Three concepts:
  - **Work**: a catalog entry, what a reader picks from the shelf: one
    edition of one book or text (a book, a poetry collection, a play, a
    standalone poem, a volume of a periodical). Editions are never merged:
    Grimm 1812 and Grimm 1857 are two works, and an overview page of all
    editions (de `Kinder und Hausmärchen`) is not a work. Browse lists and
    facets show works only.
  - **Part**: one reading unit, one normalised HTML file (a chapter, a poem
    inside a collection, an act, a volume's front matter, a synthetic slice
    of an oversized page). Parts form a tree under their work (`depth`,
    `parent`) in the book's reading order; the reader's TOC shows them.
    Every content page belongs to exactly one work: the container that the
    page's own header names wins over a TOC page that merely links it.
  - **Text**: a part with a title of its own (a poem, a tale, an article, a
    letter). It is findable in search and opens its work at that part. A
    standalone poem is a work and its only part at once.
  A work's pages come in several structures (WS§1, patterns A–I):
  subpages `Work/Chapter 1`, `Work/Part 2/Chapter 3` (depth up to 9 on en);
  **collections of root pages**, where a TOC root page links separate root
  pages whose headers link back (de `Der Sagenschatz des Königreichs
  Sachsen`, 990 members; no `Diktsamling`, 125 poems of which 9 exist);
  mixed collections with root-title redirects to subpages (fr `La Légende
  des siècles`); whole books on one page (de `Käthe`); texts that exist
  only inside a collection (en `…Shelley (ed. Hutchinson,
  1914)/Ozymandias`). Single-page works (poems, short texts) exist and are
  common. Section 5.2 steps 1a–1h resolve these structures; the app runs
  the same rules live (7.1).
  - **Virtual roots** (WS§4 step 6): a subpage tree whose root page does
    not exist (de `Die Heimath in der neuen Welt/{Erster,Zweiter,Dritter}
    Band`) is one work under the missing title, `is_virtual = 1`, opened at
    its first part.
  - **Reference works** (WS§2 rule 5, §8 item 6): dictionaries and
    encyclopedias with thousands of entries (en `1911 Encyclopædia
    Britannica`, 39,272 subpages) are works of type `reference`: listed and
    searchable by title, hidden by the default type filter, entries loaded
    live, never a TOC in the catalog. de's colon-prefixed entries stay
    excluded by title prefix (below).
- **Structure signals** (WS, 2026-10-31, WS§3). Each is in the wikitext
  (pages-articles dump, for the pipeline) and in Parsoid's `data-mw` (for
  the app), so both apply the same rules:
  - ProofreadPage header (`<pages index=… header=1 />`, every wiki):
    container `tittel` (no) / `titre` (fr) / `title` (en), `prev`, `next`,
    `current`, Index `type` (book, collection, journal, dictionary, …), TOC
    `sammendrag` / `sommaire`. Its `from`/`to` are displayed page labels;
    order by the integer scan indexes in the wikitext `<pages from= to=>`
    or by the transcluded `Page:` titles. A container link outside ns0 (fr
    `titre = [[Livre:…]]`) is ignored.
  - en `{{header}}` on pages without a scan: `title = [[../]]`, `section`,
    `previous`, `next`; relative links are resolved against the page title.
  - de `{{Textdaten}}`: the container is the first wikilink inside
    `HERKUNFT` when it is an existing ns0 page (`HERKUNFT = off` on a TOC
    page); the order is the `VORIGER`/`NÄCHSTER` chain; `INDEXSEITE` groups
    a scanned volume; `TITEL` may link an editions overview and is never
    used for grouping; `BEARBEITUNGSSTAND` is the quality (3.4).
  - Title hierarchy; root-title redirects to subpages (fr `Clarté d’âmes`),
    which become aliases that make texts inside collections findable; TOC
    links from `.ws-summary`, the summary parameter, else the root page body
    in document order, with red links kept and counted as missing parts.
- `Author:` (102 on en; number varies by wiki): author pages. Not works.
  (WP0, F§1: 102 on no/en/fr/it/da/is, 106 on sv/es, 108 on mul; **de and
  fi have no Author namespace** and keep author pages in the main namespace,
  so exclude root pages whose Wikidata item is a human, `P31 = Q5`, and on de
  `Kategorie:Autoren`.)
- `Index:` and `Page:`: ProofreadPage scan transcription. `Page:Foo.djvu/135`
  is one scanned page; `Index:Foo.djvu` is the book's index with a progress
  field. Main-namespace works transclude `Page:` pages with `<pages>`.
- `Portal:`, `Wikisource:`, `Help:`, `Template:`, `Translation:` (en): not works.
- Disambiguation / "versions" pages list multiple editions of the same title.
  Exclude them from the catalog (page_props `disambiguation`, plus per-wiki
  templates). (WP0, F§2.10: en versions pages such as `Ozymandias (Shelley)`
  have no `disambiguation` prop, only `{{Versions}}` and
  `Category:Versions pages`; the templates are required.)
- (WP0, F§1) de has about 196,000 mainspace root pages in lexicon
  pseudo-namespaces (`RE:`, `MKL1888:`, `ADB:`, `BLKÖ:`); exclude them by
  title prefix. (WS, 2026-10-31, WS§1, §5.3, §8 item 6: in the dump titles
  the four prefixes hold 203,575 root pages, and about 25,000 more sit under
  `Zedler:`, `Boetticher:`, `Haan:` and others; the WP1a survey lists every
  colon prefix shared by ≥ 100 root pages to complete the list. A colon
  alone excludes nothing: `Woher das Sprichwort: …` is a text.)
  (WP1a, 2026-11-01, report/wp1a-decisions.md item 4: the survey added only
  `Hantzsch:`. The other prefixes shared by ≥ 100 root pages are books
  stored page by page (`Allgemeines Deutsches Kommersbuch:47`) and Merian's
  *Topographia* volumes, whose root pages list them; they are grouped under
  those roots by the rule-3 extension of 5.2 step 1b.)
  (F§2.4) Orphan subpage trees exist (subpages whose root page is missing).
  (WS, 2026-10-31, WS§4 step 6: they become virtual roots, above, instead
  of making the top-most existing ancestor the work.)
- Namespace numbers and names per wiki: query
  `api.php?action=query&meta=siteinfo&siprop=namespaces`.

### 3.3 Microformat classes (used by WS Export; rely on them)
- `ws-title`, `ws-author`, `ws-translator`, `ws-year`, `ws-publisher`,
  `ws-place`: metadata inside the header template output.
- `ws-summary`: wrapper around the table of contents on a work's root page.
  Chapter discovery: links inside `ws-summary`; if absent, all links to
  subpages of the work, in document order, de-duplicated.
  (WP0, F§2.4: present on some no/en/fr works only and can be nested; no
  template emits it on de, where collections are TOC pages linking separate
  root pages; fr roots may link a `…/Texte entier` subpage outside it.)
  (WS, 2026-10-31, WS§3.4: TOC links may point to root pages, not only to
  subpages; discovery follows 3.2 and 5.2 step 1a.)
- `ws-noexport`: content to exclude from exports (header boxes, nav, notices).
  Drop these elements.
- `ws-pagenum` / `pagenum`: the page-number markers emitted when `Page:` pages
  are transcluded. These carry the original edition's page numbers and are
  the basis for requirement R8. Verified in WP0 (F§2.3): the shape differs
  per wiki.
  - en: `<span class="pagenum ws-pagenum" id="135" data-page-number="135"
    data-page-name="Page:Foo.djvu/135" data-page-index="135"
    data-page-quality="4" title="Page:Foo.djvu/135">` (contains U+200B);
  - fr: `class="pagenum ws-pagenum"`, `id`, `title`, no `data-*`;
  - no, de: only `class="pagenum"`, `id` (the displayed number) and `title`
    (scan page, with the local namespace name `Side:`/`Seite:`);
  - older de texts without ProofreadPage: `<span class="pr1pn"
    id="Seite_104">[…104…]</span>`, no scan page title.
  Number = `data-page-number`, else `id` (may be roman or a label such as
  `FP`); scan page = `data-page-name`, else `title` with `_` → space. In
  prose the marker sits inside the `<p>` at the exact break.
- Header template output also has `previous` / `next` links (classes like
  `ws-prev`, `ws-next` on some wikis). Use as a secondary chapter-order signal.
  (WS, 2026-10-31, WS§4 step 3: the prev/next chain is now the first order
  source among siblings, ahead of the TOC order.)

### 3.4 Quality and completeness signals
(WS, 2026-10-31, WS§10.2: replaces the earlier list, which took the lowest
page level and Wikidata badges. The policy built on these signals is in 5.4.)
- **Per-page ProofreadPage level 0–4**, the same five levels on every wiki:
  `<pagequality level="n" user="…" />` in the `Page:` wikitext (dump); in
  Parsoid HTML `data-page-quality` (en) or the `quality` parameter of the
  pagenum template in `data-mw` (no), so the app can judge a text it opens
  by URL. Level 0 means "without text" (blank pages) and is left out of
  ratios. Only 751 de texts use `<pages>`; the rest transclude scans through
  templates (`{{SeitePR}}`, `{{Seite}}`), so follow those or use
  `templatelinks`.
- **de `BEARBEITUNGSSTAND`** in `{{Textdaten}}` on every de text: `fertig`
  82.5 %, `korrigiert` 12.8 %, `unkorrigiert` 0.6 %, `unvollständig` 4.1 %
  of 53,893 texts (also `teilkorrigiert`; the WP1a survey verifies the value
  list).
- **Text-quality templates**: fr `{{TextQuality}}` on 178,361 pages (mostly
  100 % or `Textes validés`), en `{{TextQuality}}` on 15,654, it SAL
  25/50/75/100 %.
- **Index progress**, enumerated on en, mul, fr (`Avancement`), es
  (`Progreso`) and da (`Status`: F/V/K); it and no use free text; sv, de,
  fi, is have none. en codes as corrected in WP0 (F§5): `T` all validated,
  `V` to be validated, `C` to be proofread (complete but unproofread);
  `MS`, `OCR`, `L` (es `L dañado`) mean a faulty or missing text layer
  (WP1a, 2026-11-01: the codes are often stale, en `Cane (Toomer)` carries
  `L` with 244 proofread pages, so they exclude a work only when none of
  its pages is proofread, 5.4); `X` a missing pagelist. no's Index pages also carry `Malform`
  (NB/NN/ND/NO), the best source for the nb/nn sub-facet (3.1).
- **Incompleteness**: en `{{incomplete}}` (2,758 pages), no
  `{{ufullstendig}}`, de `unvollständig`; red links in a work's TOC
  (`missing_part_count`); missing `Page:` pages inside a transcluded range
  (`missing_page_count`; 64 works on no); coverage of the Index's text
  pages, only for a work that *is* the Index's book (header container link
  = work root, Index type book).
- **Junk markers on typed-in texts**: cleanup or deletion templates (no
  `opprydning`, `slett`, `sletting`), size under 300 bytes, placeholders
  ("Ennå ikke fri"). Wikisource's own translations (no `oversetter =
  Wikikilden`) are kept but labelled.

### 3.5 APIs
- **Action API**: `https://{wiki}/w/api.php`. Used for: page lists
  (`list=allpages` with namespace 0, `apfilterredir=nonredirects`), page props
  (`prop=pageprops` gives `wikibase_item` = Wikidata QID, and `disambiguation`),
  categories, revisions, parse (`action=parse&prop=text|categories|templates`),
  search (`list=search`), site info. Always `format=json&formatversion=2`.
- **MediaWiki core REST** (Parsoid HTML): `https://{wiki}/w/rest.php/v1/page/{title}/html`
  and `.../page/{title}/with_html`. Verified in WP0 (F§2.1): returns Parsoid
  HTML (spec 2.8.0) on no/en/fr/de, with the microformat classes intact
  where the wiki emits them (de emits none, F§2.5).
  Titles must be fully percent-encoded, **including `/` as `%2F`** (a raw
  slash gives 404 `rest-no-match`); `ETag` + `If-None-Match` gives 304;
  `with_html` adds `latest.id` (revision) and `license`. The older
  `https://{wiki}/api/rest_v1/page/html/{title}` (RESTBase) is being retired by
  Wikimedia; do not build on it.
- **Wikidata**: `https://www.wikidata.org/w/api.php?action=wbgetentities`
  (batches of 50 QIDs), SPARQL at `https://query.wikidata.org/sparql`
  (use for bulk facet extraction; respect its 60 s timeout; paginate).
- **Dumps**: `https://dumps.wikimedia.org/{dbname}/latest/` where dbname is e.g.
  `nowikisource`, `enwikisource`. Useful files: `-page.sql.gz`,
  `-page_props.sql.gz` (QID per page, disambiguation flag), `-categorylinks.sql.gz`,
  `-redirect.sql.gz`, `-templatelinks.sql.gz`. Dumps avoid millions of API calls
  for the large wikis.
- **Images**: `https://upload.wikimedia.org/...`; Parsoid `<img>` has `src` and
  `srcset`; pick the variant closest to 800 px wide. (WP0, F§2.9: thumbnails
  now come from `//thumb.wikimedia.org/wikipedia/commons/thumb/…` with
  `utm_*` parameters; prefix `https:` and strip them.)
- **Scan pages** (for R8 "open scan"): the `Page:` title from the pagenum span,
  opened online as `https://{wiki}/wiki/{Page title}`. Optional later: fetch the
  page image via `prop=imageinfo&iiprop=url&iiurlwidth=1200` on the `File:`.

### 3.6 Wikidata book model
- Edition items (instance of Q3331189 "version, edition or translation") are
  what Wikisource pages link to via sitelinks. Work items (Q47461344 "written
  work" and subclasses) are the abstract work.
- Edition → work: P629. Work → editions: P747.
- Author P50 (also translator P655, editor P98). Genre P136. Publication date
  P577 (edition: that edition; work: first publication). Language P407.
  Instance-of P31 on the work often encodes type (Q8261 novel, Q5185279 poem,
  Q25379 play, Q49084 short story, Q35760 essay, ...).
- Expect gaps: many editions lack P136/P577. The pipeline fills from the work
  item, then from header template params, then from per-wiki categories, and
  records the source of each value.

### 3.7 Licensing
Texts are public domain or CC BY-SA 4.0 (contributor layer). The app must link
to the source page and name the licence per work, and must not strip the
contributor attribution Wikisource expects. The app's own About screen credits
Wikisource and Wikidata (CC0).

---

## 4. System architecture

```
+------------------------+        +---------------------------------------+
|  Catalog pipeline (CI) |        |  Android app                           |
|  Python, weekly        |        |                                         |
|                        | static |  catalog store  <-- downloads manifest  |
|  dumps + Action API    | files  |  (Room/SQLite per wiki)                 |
|  + Wikidata            | -----> |                                         |
|  -> per-wiki SQLite    |        |  content fetcher --> normaliser --> files|
|  -> manifest.json      |        |  (Parsoid HTML)      (Jsoup)     (disk) |
+------------------------+        |                                         |
                                  |  reader (WebView, CSS columns)          |
        *.wikisource.org <------- |  bookmarks/positions (Room)             |
        wikidata.org              |  settings (DataStore)                   |
        upload.wikimedia.org      +---------------------------------------+
```

The app never needs the pipeline to *read*; it needs it only to *browse*. Online
search and "open by URL/title" work against the wiki directly, so a user can
always reach a text even if the catalog lags.

---

## 5. Catalog pipeline (Python 3.12, `pipeline/`)

### 5.1 Inputs
1. Sitematrix → list of Wikisource wikis (code, domain, dbname, language name).
2. Per wiki: latest dump of `page`, `page_props`, `redirect`, `categorylinks`,
   `templatelinks` (fall back to Action API `allpages` + `pageprops` for small
   wikis or when the dump is missing).
   (Added in WP0, F§3) Also `{dbname}-latest-pages-articles.xml.bz2`
   (wikitext): header template parameters, `<pages index=… header=1>` and the
   Index page fields are the main metadata source. Only 13 % of no root
   pages have a Wikidata item; header microformats give an author for 88 %
   and a year for 92 %.
   (WS, 2026-10-31, WS§3.5, §4) Inputs and their use in the structure
   resolution: pages-articles for the header parameters (container,
   prev/next, current, Index, summary), `<pages>` tags with their scan
   ranges, the `Page:` quality levels and the Index fields; `redirect` for
   `aliases` (root-title redirects to parts, open-by-URL); `pagelinks` for
   TOC membership and red links (missing parts) without parsing;
   `templatelinks` for the `Page:` pages each text transcludes, including
   de's template transclusion, and for header, quality and exclusion
   templates; `linktarget`, which both link tables need for their target
   titles.
3. Wikidata: for every root page with a QID, fetch the edition item and its
   work item (wbgetentities in batches of 50, or a SPARQL `VALUES` query in
   chunks of ~500). Cache raw JSON on disk between runs.
4. Per-wiki config `pipeline/config/{wiki}.yaml` (section 5.5).

### 5.2 Steps
0. (WS, 2026-10-31, WS§6) **Structure survey**: steps 1–1h and 4 in report
   mode, writing `report/{wiki}-structure.md` (contents in WP1a, section 9).
   Run first in WP1a, before the schema is frozen; kept as a pipeline mode
   afterwards.
1. **Candidate works**: namespace 0, not redirect, title has no `/`, not
   disambiguation, not matching per-wiki exclusion categories/templates.
   (WS, 2026-10-31, WS§4 step 0: candidates are now all ns0 non-redirect
   pages at any depth, since steps 1a–1h decide which are works; redirects
   become `aliases`. Excluded, with the reason in `excluded`:
   disambiguation and versions pages (page prop *and* per-wiki templates),
   author pages in ns0 (de, fi; 3.2), colon-prefix reference works
   (`exclude_title_prefixes`), whole-text duplicate subpages
   (`whole_text_subpages`, fr `Texte entier`), portals and project pages in
   ns0, and the per-wiki exclusion categories and templates.)

   (WS, 2026-10-31: steps 1a–1h resolve pages into works and parts, WS§4.
   The app runs the same rules live on Parsoid HTML, 7.1. Per-wiki
   parameters are in the `structure` config block, 5.5.)

1a. **Structure record** per candidate page: `title`; `container`, the
    header's container link (no `tittel`, fr `titre`, en `title` with
    relative links resolved, de the first wikilink inside `HERKUNFT`) when
    it is an existing, non-excluded ns0 page other than the page itself,
    else null; `prev`, `next` (resolved titles); `index` (from `<pages
    index=>`, de `INDEXSEITE`, or the transcluded `Page:` titles);
    `scan_from`, `scan_to` (integers from `<pages>`); `index_type` (book,
    collection, journal, dictionary, …); `toc_links` (ordered ns0 link
    targets from `.ws-summary`, the summary parameter or the body, red
    links kept and flagged); `bytes`; `has_pages_tag`; the quality signals
    of 3.4.
1b. **Parent assignment**, first rule that applies:
    1. `container` if set: the page is a part of that page's work (the
       container may have a container of its own; walk up, cycle-safe,
       max depth 6).
    2. Title hierarchy: parent = the longest existing proper title prefix
       `A/B` of `A/B/C`; if no ancestor exists, the page joins the virtual
       root `A`.
    3. TOC membership: the page is in `toc_links` of exactly one TOC-like
       root page (links ≥ 3 ns0 pages, has `HERKUNFT = off` or an Index
       `Type`, is not a versions page) **and** shares that page's `index`
       or is reached from it by the prev/next chain. This catches members
       whose header lacks a container link.
       (WP1a, 2026-11-01, report/wp1a-decisions.md item 4, config
       `headerless_toc_members`, on by default only for de: a page with
       no header template and no status that is listed by exactly one
       TOC-like page is its member regardless of Index or chain, and a
       root page with a header and an Index that lists at least three
       such pages counts as TOC-like. de stores some books page by page
       under a colon prefix, and Merian's *Topographia* roots name the
       series in `HERKUNFT`, not `off`; on de a headerless root page
       cannot be a text of its own, step 1.)
    4. Otherwise the page is a work of its own.
    A prev/next chain alone never merges root pages into one work (two
    root-page volumes with `HERKUNFT = off` stay two works in v1), and rule
    1 never climbs through `TITEL` or title fields, so editions are not
    merged.
1c. **Order** of the children of a node, first source that covers all of
    them: the prev/next chain among siblings; the parent's `toc_links`
    order; ascending `scan_from` within the same `index`; natural sort of
    the titles (`Chapter 2` before `Chapter 10`). Record `order_source`.
1d. **Classification**: a node with children or without a parent is a
    work. Its children are parts, recursively, with `depth`. Part kinds:
    `volume` (has children), `chapter`, `text`, `front` (the root's own
    text beyond header and TOC, 7.1 step 4), `toc` (a TOC page without text
    of its own: structure only, not downloaded). A part is a searchable
    **text** when its display title does not match the per-language
    `chapter_label_regex` (Chapter, Kapitel, Kapittel, Chapitre, Capitolo,
    Act(e), Akt, Letter, Brief, Part(ie), Book, Band, Bind, Tome, Volume,
    roman numerals, bare numbers): "Ozymandias" and "Ved Rundarne" are
    texts, "Chapter 1", "Første akt" and "Zweiter Band" are not. Display
    title of a part: header `current` / `section` / `TITEL`, else the link
    text in the parent's TOC, else the last title segment.
1e. **Work type**: Index `Type` → `book`, `collection`,
    `periodical-issue` (journal), `reference` (dictionary), `document`
    (phdthesis); else from structure (a work whose parts are mostly texts →
    `collection`; a single text → `poem`, `article`, … from categories and
    header type); the Wikidata and category mappings of step 3 stay as
    further sources. A work with more than `reference_threshold` (2,000)
    parts and no Index type is `reference` (WP1a, 2026-11-01,
    report/wp1a-decisions.md item 3: only when its root's children have no
    reading-order signal, order `natural` or `toc-partial`; a prev/next
    chain or a covering TOC marks a periodical or collection, en *Popular
    Science Monthly*, the *Nicene and Post-Nicene Fathers*, is *Íslenzkar
    þjóðsögur og æfintýri*). Display titles: a colon prefix shared by at
    least three siblings is the book's name and is stripped.
1f. **Virtual roots**: the work's `page_title` is the missing title,
    `is_virtual = 1`, `entry_title` = the first child in reading order; display title
    from the common prefix, cleaned of volume suffixes; author and year
    from the children's headers; the source link points to the entry page.
1g. **Oversized pages**: `oversized = 1` when a page's wikitext exceeds
    `oversized_bytes` (300 kB, about 100 k characters of text). The
    pipeline only flags; the app splits at download time (7.1 step 6).
1h. **Completeness counts**: `missing_part_count` = red links in the TOC
    (they never become parts; Diktsamling: 9 parts, 116 missing);
    `complete` follows in step 4.
2. **Parts count**: number of subpages `Title/...` (from the page table). Used for
   size estimate and to decide single-page vs multi-part.
   (WS, 2026-10-31, WS§5.1–5.2: now from the resolution: `part_count`
   (existing parts; 1 for a single-page work), `text_count`,
   `missing_part_count`, `bytes_total`. Works over `parts_cap` (2,000,
   confirmed after the survey, WS§8 item 4; WP1a, 2026-11-01,
   report/wp1a-decisions.md item 2: measured on en at 341,000 rows, about
   18 MB gzipped and 67 MB raw SQLite with the encoding of 5.3; a cap of
   500 would save 2.7 MB) and reference works get
   `parts_in_catalog = 0` and carry counts only.)
3. **Metadata merge** (priority order; record `*_source` for each):
   title (display title from page_props `displaytitle`, else page title),
   authors (Wikidata P50 → header `author` param), year (edition P577 → work
   P577 → header `year`), language (P407 → wiki language), genres (P136 and
   P31 on edition then work → category mapping), type (P31 mapping),
   description (Wikidata description in wiki language, else English).
   (WP1, 2026-11-01, `pipeline/wscatalog/metadata.py`) Implementation:
   authors and years also come from the Index fields (`index_fields`;
   most no works take them from `<pages header=1>`), from an author-link
   template (no `{{forfatterl}}`), from a category named like an author
   page (no) and, for typed-in pages, from an author link in the first 600
   characters of the text; values that name nobody (`anonymous_names`:
   "Ukjent", "Anonymous") and header placeholders (de `off`, which then
   falls through to `ENTSTEHUNGSJAHR`) are skipped. Year categories give a
   year, decade or century (`year_categories`: no "Tekster fra 1896", de
   "1940er Jahre", fr "XIXe siècle"). Language: P407, then the Index
   language field (no `Malform` NB/NN), else the wiki's language (`no` on
   no.wikisource, 3.1). Type, in order: `reference` from the resolution; an
   Index type other than book, when the work is that Index's book or has
   parts; government and legal documents marked by licence, case or law
   templates and categories (`document_templates`, en `PD-USGov`,
   `USSCcase`, de `Gesetzestext`; decision 1 of 2026-11-01); Wikidata P31;
   Index type book; the resolution's book or collection; for a single page
   its genre (poetry → poem, drama → play); else `other`. Description:
   Wikidata in the wiki's language, the header's description (de
   `KURZBESCHREIBUNG`), Wikidata in English. A work whose Wikidata item is
   a human (P31 = Q5) is excluded as an author page (3.2). The Wikidata
   mappings (P136/P31 → genre, P31 → type, P407 → language) are in
   `pipeline/config/default.yaml`; categories and templates per wiki.
4. **Quality and completeness** (WS, 2026-10-31, WS§10.3: replaces "from
   Index progress, text-quality template, incomplete template, Wikidata
   badges → one value plus an `incomplete` boolean"): a tier, `complete`
   and their sources for every work and every part, by the rules of 5.4.
5. **Hard exclusions** (5.4; WS, 2026-10-31, WS§10.4: replaces the
   completeness filter). Excluded works are kept in a separate
   `excluded` table with a reason, for debugging coverage.
6. **Write SQLite** per wiki (5.3), gzip, compute sha256.
   (WP1, 2026-11-01) Also in step 1b, implementation choices found while
   fixing the WP1a eyeball tickets: siblings whose only `<pages>` tag
   transcludes the same range of two or more pages of one Index are one
   text stored twice (no `Fru Bovary/1` and `Fru Bovary: Første del`); the
   subpage stays, the other page is excluded as `duplicate` and becomes an
   alias. Natural order reads ordinal words per language ("Andet Bind"
   after "Første Bind"). Navigation link texts ("hier") and single letters
   are never display titles.
7. **Coverage report** per wiki: % of works with year, genre, author, quality.
   Written to `report/{wiki}.md` and printed in CI. This is the tool for deciding
   where to improve mappings. (WS, 2026-10-31) Also works per quality tier
   and per `structure`, and the size of the default view (5.4).
8. **Manifest**: `manifest.json` listing every wiki: code, language name
   (autonym), url, sha256, size, work_count, generated_at, schema_version.
   (WS, 2026-10-31, WS§10.5) Also `show_unknown_by_default` (from the
   wiki's config) and the work counts per tier.
   (WP1, 2026-11-01) Each entry also has `dbname`, `domain`, `language`,
   `size_uncompressed`, `dump_date`, `tiers` and `default_view_count` (the
   wiki picker's number, 5.4); `url` is relative to the manifest's own URL
   (`no.sqlite.gz`). A weekly run that fails for one wiki keeps that wiki's
   previous entry and asset.

### 5.3 Catalog schema (per-wiki SQLite, schema_version 2)
(WS, 2026-10-31, WS§5.1, §10.5: schema_version 2 replaces version 1. It adds
the structure columns, the `parts`, `aliases` and `parts_fts` tables, and
replaces version 1's `quality` / `incomplete` with the quality and
completeness fields of 5.4.)
```sql
CREATE TABLE meta(key TEXT PRIMARY KEY, value TEXT);   -- wiki, generated_at, schema_version
CREATE TABLE works(
  id INTEGER PRIMARY KEY,            -- page_id on the wiki
  page_title TEXT NOT NULL UNIQUE,   -- exact title for API calls
  display_title TEXT NOT NULL,
  sort_title TEXT NOT NULL,          -- lowercase, articles stripped per language
  qid TEXT,                          -- Wikidata edition item
  work_qid TEXT,                     -- Wikidata work item
  year INTEGER, year_precision INTEGER, year_source TEXT,
  language TEXT,                     -- BCP47, e.g. nb, nn, da, en
  type TEXT,                         -- see canonical taxonomy
  -- structure (WS§5.1)
  structure TEXT NOT NULL,           -- single | subpages | linked | mixed | virtual
  is_virtual INTEGER NOT NULL DEFAULT 0,
  entry_title TEXT,                  -- page to open/link when is_virtual (else = page_title)
  part_count INTEGER NOT NULL,       -- existing parts; a single-page work has 1
  text_count INTEGER NOT NULL,       -- searchable parts
  missing_part_count INTEGER NOT NULL DEFAULT 0,  -- red links in the TOC
  parts_in_catalog INTEGER NOT NULL DEFAULT 1,    -- 0 for reference works and works over parts_cap
  oversized INTEGER NOT NULL DEFAULT 0,
  bytes_total INTEGER,               -- sum of wikitext bytes, for the size estimate
  index_titles TEXT,                 -- JSON list of Index pages (quality, scans)
  -- quality and completeness (WS§10.5, rules in 5.4)
  quality TEXT NOT NULL,             -- validated | proofread | unproofread | unknown
  quality_source TEXT,               -- page-levels | status-template | textquality | index-progress | none
  quality_pages TEXT,                -- JSON {"4":n,"3":n,"2":n,"1":n,"0":n,"missing":n}
  complete INTEGER NOT NULL,         -- 1 unless a rule in 5.4 says otherwise
  incomplete_reason TEXT,            -- template | missing-parts | missing-pages | index-progress | coverage
  missing_page_count INTEGER NOT NULL DEFAULT 0,
  coverage REAL,                     -- transcluded / text pages of the Index that no other work transcludes (WP1a), when applicable
  own_translation INTEGER NOT NULL DEFAULT 0,
  description TEXT,
  cover_url TEXT,
  rev_id INTEGER, touched TEXT
);
CREATE TABLE parts(
  work_id INTEGER NOT NULL, ord INTEGER NOT NULL,   -- reading order, 0-based, depth-first
  depth INTEGER NOT NULL, parent_ord INTEGER,       -- tree
  page_title TEXT NOT NULL,                         -- exact title; '#anchor' never stored
  display_title TEXT,                               -- (WP1a) NULL when it equals the last segment of page_title
  kind TEXT NOT NULL,                               -- volume | chapter | text | front | toc
  searchable INTEGER NOT NULL DEFAULT 0,
  bytes INTEGER, scan_from INTEGER, scan_to INTEGER,
  index_ref INTEGER,                                -- (WP1a) position of the part's Index in works.index_titles
  quality TEXT, quality_pages TEXT, complete INTEGER,
  PRIMARY KEY(work_id, ord));
  -- (WP1a, 2026-11-01, report/wp1a-decisions.md item 2: index_ref and the NULL display_title
  -- replace index_title TEXT and display_title NOT NULL; en 19.5 -> 18.3 MB gzipped, 83 -> 67 MB raw)
CREATE INDEX parts_title ON parts(page_title);      -- open-by-URL, link rewriting
CREATE TABLE aliases(alias_title TEXT PRIMARY KEY, work_id INTEGER NOT NULL, ord INTEGER);
  -- redirects to a work or a part; used by search and open-by-URL
CREATE VIRTUAL TABLE parts_fts USING fts5(display_title, work_title, author_names, content='');
  -- only searchable parts
CREATE TABLE authors(id INTEGER PRIMARY KEY, name TEXT NOT NULL, sort_name TEXT NOT NULL,
  qid TEXT, birth_year INTEGER, death_year INTEGER, author_page_title TEXT);
CREATE TABLE work_authors(work_id INTEGER, author_id INTEGER, role TEXT, position INTEGER,
  PRIMARY KEY(work_id, author_id, role));
CREATE TABLE genres(id INTEGER PRIMARY KEY, key TEXT UNIQUE);           -- canonical keys
CREATE TABLE work_genres(work_id INTEGER, genre_id INTEGER, PRIMARY KEY(work_id, genre_id));
CREATE VIRTUAL TABLE works_fts USING fts5(display_title, author_names, content='');
CREATE TABLE excluded(page_title TEXT PRIMARY KEY, reason TEXT);
  -- (WS) every page excluded in step 1 and every work excluded in step 5, with the
  -- reason; pages absorbed as parts are not "excluded" and do not appear here
-- indexes on works(year), works(type), works(quality), works(sort_title), work_genres(genre_id)
```
(WP1, 2026-11-01, `pipeline/wscatalog/catalog.py`) Details settled when the
writer was built: `works` also has `author_source`, `language_source`,
`genre_source` and `type_source` (the `*_source` of 5.2 step 3);
`entry_title` is NULL unless `is_virtual`; a virtual root, which has no
page, gets a negative id. `parts` holds rows only for multi-part works with
`parts_in_catalog = 1`; a work with a root page starts with the root's own
row (`ord` 0, kind `front` or `toc`). `works_fts.rowid` = `works.id`;
`parts_fts.rowid` = `work_id * 4096 + ord`, so `work_id = rowid >> 12` and
`ord = rowid & 4095` (ord stays below 4,096 because `parts_cap` is 2,000);
both FTS tables use `tokenize='unicode61 remove_diacritics 2'`. `aliases`
holds redirects to a work's root page (`ord` NULL) or to a catalog part,
and the titles of `duplicate` pages. `excluded` holds pages excluded by a
title prefix as one row per prefix (`RE:*`, "reference-prefix (n pages)"),
not 200,000 rows. `meta` keys: wiki, domain, dbname, language,
schema_version, generated_at, dump_date, pipeline_version, namespaces
(local names of author/index/page), show_unknown_by_default,
parts_rowid_stride.
Genre keys are a fixed canonical list (localised in the app):
`novel, short-story, poetry, drama, essay, letters, memoir-biography, history,
religious, legal-government, speech, periodical, children, reference, song-hymn,
travel, philosophy, science, folklore, other`.
Type keys: `book, poem, play, article, document, collection, periodical-issue, other`,
and (WS, 2026-10-31, WS§5.1) `reference`.

### 5.4 Quality and completeness policy
(WS, 2026-10-31, WS§10, decided by the owner in WS§10.8: replaces the
completeness filter.) Two stages: the pipeline excludes only what nobody can
read; the catalog records quality and completeness for every work and every
part; the app hides unproofread and incomplete works by default and lets the
reader opt in. Typed-in texts carry no signal, and a hard cut would empty
the small wikis.

**Tiers** are computed over the text pages (levels 1–4) a work transcludes.
Sources in order of precedence: page levels, then a status or text-quality
template, then Index progress, else unknown. A collection aggregates over
all its parts; every part also gets its own tier, so a proofread poem inside
an unfinished collection keeps its standing.

| tier | rule |
|---|---|
| `validated` | every text page at level 4; or de `fertig`; or a 100 % / validated text-quality template |
| `proofread` | every text page at level 3 or better, or at least 90 % at level 3 or better (the app shows "n of m pages not yet proofread" from `quality_pages`); or de `korrigiert`; or a 75 % template |
| `unproofread` | any other work that has a signal |
| `unknown` | no signal at all (typed-in text without a template) |

**Completeness**: `complete = 0` when an incomplete template or de
`unvollständig` is present; `missing_part_count > 0`; `missing_page_count >
0`; Index progress is `MS`/`OCR`/`L`/`X`; or the work is the Index's book
and covers under 95 % of that Index's text pages. Otherwise `complete = 1`
(unknown completeness counts as complete, since nothing says otherwise).
(WP1a, 2026-11-01, report/wp1a-decisions.md item 7: coverage counts only
the Index's text pages that no other work transcludes, so texts bound
together in one scan do not count against each other, and up to
`coverage_slack` (3) untranscluded pages are tolerated, so a missing title
page does not make a work incomplete; `coverage_min` stays 0.95. fr went
from 700 to 305 works incomplete by coverage alone.)

**Hard exclusions** (pipeline, into `excluded` with the reason): the work
has no part with text; all referenced scan pages are missing or at level 0;
its Index progress says the source is faulty (WP1a, 2026-11-01, item 6: only
when none of its pages is at level 3 or 4; en kept 127 of 141 such works,
fr 50 of 109); or a junk marker applies (cleanup or deletion template, under
300 bytes, placeholder; WP1a, item 1: a bibliography page, a single root
whose only header is a `bibliography_templates` template such as de
`{{Zeitschrift}}`, without scans or status, 2,029 on de). Everything
else enters the catalog with its flags.

**App defaults** (WS§10.6): the default filter is `quality IN (validated,
proofread)`, plus `unknown` where the wiki's manifest sets
`show_unknown_by_default`, and `complete = 1`. no shows unknown works by
default (after the junk rules, labelled "Not verified against a scan"); for
every other wiki the owner sets `show_unknown_by_default` from the WP1a
survey (WP1a, 2026-11-01, report/wp1a-decisions.md item 1: is `true`, en
`true`, de `false`, fr `false`). Filter chips, off by default and also reachable from Settings:
"Include texts not yet proofread", "Include incomplete works", "Include
unverified texts". Search collapses hidden matches into one row ("n more
texts hidden by your quality filter"); nothing disappears silently. Labels
on the detail screen, in the reader's info sheet and for texts opened by
URL: "Validated", "Proofread", "Proofread, 3 of 210 pages not yet
proofread", "Not proofread, may contain OCR errors", "Not verified against
a scan", "Incomplete: 116 of 125 poems not yet transcribed", "Wikisource
translation". The wiki picker shows the number of works in the default view
per wiki.

Measured on no (WS§10.7): of 3,845 works, 1,523 are validated or
proofread, about 220 unknown works remain after the junk rules, and 2,041
unproofread works (1,835 of them level 1 throughout, i.e. raw OCR) are
hidden by default.

### 5.5 Per-wiki config (`pipeline/config/{wiki}.yaml`)
```yaml
wiki: no
domain: no.wikisource.org
dbname: nowikisource
namespaces: {author: 102, index: 106, page: 104}   # verified for no (WP0); differs per wiki, F§1
header_templates: [Forfatterhode, Hode, header]      # names of header templates
header_params: {author: [forfatter, author], year: [år, year], title: [tittel, title]}
incomplete_templates: [Ufullstendig]
disambiguation_templates: [Flertydig]
exclude_categories: [...]
genre_categories:                                   # category -> canonical genre key
  "Kategori:Dikt": poetry
  "Kategori:Romaner": novel
year_categories_regex: "^Kategori:(\\d{4})$"
quality_sources: {index_progress: true, textquality_template: null}
line_number_selectors: []                             # see 7.3; shared with app
page_number_selectors: ["span.pagenum", "span.ws-pagenum"]
# Added in WP0:
exclude_title_prefixes: []          # de: ["RE:", "MKL1888:", "ADB:", "BLKÖ:"] (F§1)
exclude_subpage_titles: []          # fr: ["Texte entier"] (F§2.4)
licence_templates: [PD-old]         # licence boxes the normaliser drops (F§2.5)
# line_number_selectors, fr: ['span.OptionText[title="Vers numérotés"]'];
#   de: ["span.zeilennummer"] (F§2.7). page_number_selectors, de: add "span.pr1pn".
# Added by WS (WS§5.3; the de prefix list is extended from the WP1a survey,
#   WS§8 item 6: [RE:, MKL1888:, ADB:, BLKÖ:, Zedler:, Boetticher:, Haan:, …]):
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
  headerless_toc_members: false         # de: true (WP1a, 2026-11-01, 5.2 step 1b rule 3)
# Quality markers and the app default (WS§10): show_unknown_by_default
# (no: true, owner decision WS§10.8; other wikis set by the owner from the
# survey: is true, en true, de false, fr false, WP1a 2026-11-01), the status
# parameter (de: BEARBEITUNGSSTAND), text-quality,
# incomplete, junk (no: opprydning, slett, sletting) and own-translation
# markers (no: oversetter = Wikikilden); WP1a adds bibliography_templates
# (de: [Zeitschrift]), coverage_min (0.95) and coverage_slack (3).
```
Start with configs for: no, en, fr, de, it, sv, da, fi, is, es, mul. Other
wikis run with a generic default config (Wikidata-only metadata) and still
produce a catalog.

### 5.6 Running
- `make catalog WIKI=no` locally; GitHub Actions workflow weekly for all wikis,
  uploading artifacts to the release/static host and refreshing `manifest.json`.
- Idempotent; caches dumps and Wikidata JSON in `pipeline/cache/` (gitignored).
- Tests: unit tests for the merge logic and filters using fixture rows; a smoke
  test that builds the `is` (Icelandic, small) catalog end-to-end.

---

## 6. Android app: structure

```
app/
  src/main/java/.../
    di/                 Hilt modules
    data/catalog/       manifest + catalog download, attach per-wiki SQLite, DAOs
    data/content/       fetcher (Parsoid), normaliser, image store, work storage
    data/library/       Room: downloaded works, parts, bookmarks, positions
    data/settings/      DataStore: appearance + reader + UI language
    domain/             use cases, models (Work, Part, Locator, Bookmark, ...)
    ui/browse/          wiki picker, facets, lists, work detail
    ui/search/          offline + online search
    ui/library/         downloaded works, download queue
    ui/reader/          WebView pager, toolbar, TOC, bookmarks, settings sheet
    ui/settings/        appearance, language, storage, about/attribution
  src/main/assets/reader/   reader.css, reader.js, fonts/
  src/main/res/values-{en,nb,nn,no,fr,de,it,sv,da,fi,is,es}/strings.xml
pipeline/               Python (section 5)
docs/                   this plan, STATUS.md, decisions
```

### 6.1 Catalog on device
- Catalogs are downloaded per wiki chosen by the user on first run (default
  preselect: the wiki matching the system language, plus `en`). Stored as
  separate SQLite files, opened read-only via Room `createFromFile` or
  `SupportSQLiteOpenHelper` and queried directly; do not import rows into the
  main DB (catalogs are tens of MB for `en`).
- Update check against `manifest.json` at most daily; download when sha differs.
- (WS, 2026-10-31, WS§10.5–10.6) `manifest.json` carries per wiki
  `show_unknown_by_default` and the work counts per quality tier. The app
  reads the flag for its default filter (5.4) and shows the default-view
  count in the wiki picker.
- The app's own Room database (`library.db`) holds downloaded works, parts,
  bookmarks, positions, download jobs, and settings not in DataStore.

### 6.2 Library schema (Room)
```
DownloadedWork(id PK = "{wiki}:{page_title}", wiki, pageTitle, displayTitle,
  authors JSON, year, language, licence, sourceUrl, coverPath, addedAt,
  lastOpenedAt, totalChars, state: queued|downloading|ready|failed|partial)
Part(id PK, workId FK, index, title, pageTitle, filePath, charCount,
  state: pending|ready|failed, revId)
ReadingPosition(workId PK, partId, charOffset, progress REAL, updatedAt)
Bookmark(id PK, workId, partId, charOffset, excerpt, note, createdAt)
DownloadJob(id, workId, kind: full|parts, partIds JSON, state, bytesDone, bytesTotal, error)
```
(WS, 2026-10-31, WS§7.2) `DownloadedWork` adds `structure`
(single|subpages|linked|mixed|virtual) and `entryTitle`. `Part` adds
`sourceUrl`, `kind` (volume|chapter|text|front|toc|synthetic), `depth`,
`parentIndex` and, for synthetic parts, `anchor`; `pageTitle` is the part's
own wiki page, a root page or a subpage.

---

## 7. Content: fetching and normalising

### 7.1 Resolving a work into parts
(WS, 2026-10-31, WS§7.2: catalog parts first, live resolution with the same
rules second. This replaces the subpage-only resolution from the root page's
`.ws-summary`, flattened to depth 3.)
1. Use the catalog `parts` rows when the work has them (`parts_in_catalog =
   1`).
2. Otherwise (reference works, works over `parts_cap`, pages opened by URL
   that the catalog lacks) resolve live with the rules of 5.2 steps 1a–1h
   on Parsoid HTML: `data-mw` holds the same header parameters,
   `.ws-summary` or the body links give the TOC, and the `Page:` titles in
   the page markers give the Index. A reference work's TOC loads live with
   a filter box.
3. A refresh re-resolves live and merges new parts.
4. Each part is fetched by its own page title, root pages and subpages
   alike. If the root page has readable text of its own beyond header/TOC
   (e.g. a preface), it is the `front` part. Heuristic: > 400 characters of
   paragraph text after removing `ws-noexport`, header, and TOC. A TOC page
   without text of its own is a `toc` part: structure only, not downloaded.
5. Store the resolved part tree (`depth`, parent) before downloading
   content, so a partial download still knows the whole structure.
6. (Added in WP0, F§2.4) Split oversized parts into synthetic parts (at
   headings, else at page markers) above a threshold such as 100 k
   characters: de puts whole novels on one page (`Käthe`: 604 k characters),
   which breaks N3 and makes every relayout slow (about 0.2 s on the
   emulator, F§4.5).
   (WS, 2026-10-31, WS§7.2, §8 item 8: the catalog flags such pages as
   `oversized`; the app splits at download time when a page exceeds
   `oversized_chars` (100 k), with this fallback ladder: at `h2`/`h3`
   headings when at least three exist; else at chapter-like blocks (a short
   centred or bold paragraph followed by a page marker or a `<br>` gap);
   else every N page markers so that no part exceeds 100 k characters.
   Synthetic parts get `kind = synthetic`, `anchor` = the first element id,
   and as display title the heading text or "Pages 1–40". Locators stay
   `(partId, charOffset)`, the part being the synthetic one. `Käthe` has
   471 page markers and no headings.)

### 7.2 Normaliser (Jsoup) — Parsoid HTML → reader HTML
Output: one self-contained HTML fragment per part, UTF-8, with only these
elements allowed: `h1-h6 p div span br hr blockquote ol ul li table thead tbody
tr th td img figure figcaption sup sub em strong i b small a section`, plus
`dl dd dt` (added in WP0: plays indent speeches with `:`, F§2.6).
Rules:
0. (WP0, F§2.7) First convert page markers and extract source line numbers:
   de puts `span.zeilennummer` inside a `.ws-noexport` block, so rule 1
   would delete it.
1. Remove: `.ws-noexport`, `.noprint`, `.mw-editsection`, `.navbox`,
   header template boxes (per-wiki selectors; default `#headertemplate,
   .headertemplate, .ws-header`), the `.ws-summary` TOC on chapter pages,
   `style` and `script` elements, all inline `style` attributes except a
   whitelist (`text-align`, `font-style`, `font-weight`, `font-variant`,
   `text-indent`, `margin-left` for verse indentation), all `on*` attributes.
2. Keep semantic classes, prefixed into our namespace:
   - page markers → `<span class="wsr-page" data-num="135"
     data-page-title="Page:Foo.djvu/135"></span>` (from `.pagenum` /
     `.ws-pagenum`; config selectors per wiki).
   - source line numbers → `<span class="wsr-ln-src">12</span>` (per-wiki
     selectors in `line_number_selectors`. WP0, F§2.7: fr
     `span.OptionText[title="Vers numérotés"]`, de `span.zeilennummer`;
     `.numerotation` is a running page header, not line numbers).
   - verse: Parsoid renders `<poem>` as `<div class="poem"><p>line<br>line…`.
     Convert to `<div class="wsr-verse"><span class="wsr-line">…</span>…`
     so the reader can number lines with CSS counters.
   - footnote references `sup.reference a[href^=#cite_note]` → keep, add
     `class="wsr-fnref"`; the references list `ol.references` → `<section
     class="wsr-footnotes">`, each `li` id kept. (WP0, F§2.8: Parsoid hrefs
     are `./Page#cite_note-N`, so match `sup.mw-ref` and take the fragment;
     keep each references group, e.g. de's `WS` group, as its own list.)
   - drop-caps, small-caps, letter-spacing → classes `wsr-dropcap`, `wsr-sc`.
3. Links: internal links to other parts of the same work → `app://part/{index}#anchor`;
   links to other Wikisource pages → `https://{wiki}/wiki/...` (opened in
   browser with a confirmation); `Author:` links likewise.
   (WS, 2026-10-31, WS§7.2: a link to *any* page that is a part of the same
   work, root page or subpage, becomes `app://part/{index}`; de member pages
   link to their neighbours and to the TOC root.)
4. Images: download chosen `srcset` variant to `works/{workId}/img/`, rewrite
   `src` to `https://appassets.androidplatform.net/works/...` served via
   `WebViewAssetLoader` with an internal-storage path handler. Keep `alt`.
   Skip images > 5 MB and icons < 32 px.
5. Compute `charCount` and store the normalised plain text offsets as described
   in 7.4.
6. Emit a per-part `meta.json`: title, revId, fetchedAt, pageNumbers present
   (bool), sourceLineNumbers present (bool), verse present (bool).

### 7.3 Fixture corpus
`app/src/test/fixtures/{wiki}/{slug}/raw.html` + `expected.html` for at least 8
works per wiki for no, en, fr, de, it, sv, da, fi, is, es, covering: a novel
with chapters, a single poem, a poetry collection, a play, a scan-backed work
with page numbers, a work with footnotes, a work with illustrations, a work
with tables. Normaliser unit tests run against all fixtures. The Norwegian set
should include Bokmål, Nynorsk and Danish-Norwegian texts.

### 7.4 Locators
For each part, define the *normalised text* as the concatenation of text
nodes in document order of the normalised HTML, excluding `.wsr-footnotes`,
`.wsr-page` and `.wsr-ln-src`. A locator is `(partId, charOffset)` into that
string. The reader JS finds the text node containing the offset, gets its
bounding rect, and maps that to a column index. To store the current
position, the reader asks JS for the offset of the first visible text node in
the current column. This survives font, size, margin and orientation changes,
and works on both phones and tablets. Also store `progress` (0–1 over the whole
work by charCount) for display.

---

## 8. Reader

### 8.1 Pagination mechanics
- One `WebView` shows the current part. `reader.css` sets
  `html,body{margin:0;height:100vh;overflow:hidden}`,
  `body{column-width:100vw;column-gap:0;column-fill:auto}` with padding for the
  chosen margins applied to an inner wrapper, so each CSS column is exactly one
  screen page. Page count = `scrollWidth / clientWidth`. Page `n` is shown by
  `translateX(-n * clientWidth)` on the wrapper (not `scrollLeft`, which is
  unreliable with columns).
  (WP0 correction, F§4.1: padding on a wrapper puts the margin on the first
  page only. Use one box exactly one page wide, padded by the margin, with
  `column-width: page − 2·margin` and `column-gap: 2·margin`; set the page
  width from JS snapped down to 1/64 px (Blink's layout unit), since a
  fractional width such as 411.43 px can otherwise drift over hundreds of
  pages. Verified on all pages of five parts
  in Chromium and in Android WebView 134; page turns ran at 60 fps on the
  emulator, F§4.3, F§4.5.)
- Swipes are handled in Kotlin by a transparent overlay (`pointerInput`) so the
  WebView never receives horizontal gestures. Page turn animation: slide the
  WebView's `translationX` for intra-part turns; for the cross-part turn,
  pre-render the neighbouring part in a second, hidden `WebView` and swap, so
  the boundary is seamless. Keep two WebViews alive (current, prefetch).
- Tap zones: left third = previous, right third = next, centre = toggle
  toolbar. Volume keys optional.
- Re-paginate on font/size/margin change, then restore the locator.
  (WP0, F§4.6: keep a reading anchor that only navigation changes; restore
  it after every relayout and never re-read it, or the position creeps back
  one page start per rotation. The overlay takes every touch, so taps must be
  forwarded to JS to hit footnote refs, page marks and links.)
- Tablet landscape: option for two columns per screen (`column-count` of 2 per
  page width) presented as a book spread.
- Images taller than a page: constrain with `max-height: 100vh` and
  `break-inside: avoid`. Tables wider than a page: `overflow-x:auto` wrapper
  with a hint, since columns clip overflow.

### 8.2 Page marks (R8)
Setting `pageMarks: off | inline | margin`.
- `inline`: `.wsr-page::after{content:"[" attr(data-num) "]"}` in small muted
  text at the break position.
- `margin`: render the number in the outer margin at the line where the break
  occurs. Inside a column layout, absolute positioning is relative to the
  column box, so implement as `position:absolute; left:-(margin - 2px)` on a
  `position:relative` wrapper of each block that contains a mark (the
  normaliser can wrap such blocks in `<div class="wsr-has-page">`). Also offer a
  hairline rule across the text at the break (`pageBreakRule: on/off`).
- Tap on a mark → bottom sheet: "Page 135 of the original" with "Open scan on
  Wikisource" (browser).
- Default: `inline` on, because the user explicitly wants pages marked as on
  Wikisource; make the first-run reader tip mention the setting.

### 8.3 Line numbers (R9)
- `lineNumbersSource: on/off` toggles `.wsr-ln-src{display:inline}`; off hides.
- `lineNumbersVerse: off | every 5 | every 10 | every line` uses CSS counters
  on `.wsr-line` with `counter-increment` and `::before` showing the number
  when `counter % N == 0` (implemented by having the normaliser add
  `data-n` to each line and CSS attribute selectors, or by JS marking every Nth
  line with a class after load; prefer the normaliser's `data-n`).
- Prose line numbers (per printed line) are not generated: lines reflow.

### 8.4 Footnotes (R10)
Tap `.wsr-fnref` → JS posts the footnote id → Kotlin shows a bottom sheet with
the footnote HTML rendered in a small WebView or as styled text. "Go to note"
jumps to the footnote section; "Back" returns to the saved locator.

### 8.5 Appearance (R7)
- Themes as CSS variables injected into the WebView and matching Compose theme:
  light (#FFFFFF/#1A1A1A), sepia (#F4ECD8/#5B4636), dark (#1E1E1E/#D6D6D6),
  black (#000000/#C8C8C8), follow system (light/dark).
- Fonts bundled in `assets/reader/fonts` (all OFL/Apache): Literata, EB Garamond,
  Noto Serif, Source Sans 3, Atkinson Hyperlegible, OpenDyslexic, plus
  "System default". Sizes 12–32 sp in 1 sp steps; line height 1.2–2.0; margins
  small/medium/large; justified on/off; hyphenation on/off (`hyphens:auto` with
  `lang` set on `<html>` from the work's language so Norwegian, Finnish, German
  etc. hyphenate correctly).
- The settings sheet applies changes live with a 150 ms debounce and
  repaginates preserving the locator.

### 8.6 Navigation
TOC drawer (parts with depth), bookmarks list, progress slider (jumps by
locator), "go to page number of original" when page marks exist, search within
work (JS `window.find` or own index over normalised text), chapter title and
page x/y of chapter in the footer.
(WS, 2026-10-31, WS§7.3) The TOC drawer renders the part tree, with volumes
collapsible; the footer shows the part's display title; for a text opened
from search the reader's info sheet shows "Part of: Diktsamling".

---

## 9. Work packages and acceptance criteria

### WP0 — Spike and verification (1–2 weeks)
- Confirm Parsoid endpoint, HTML shape for `.ws-pagenum`, `.ws-summary`,
  `<poem>` output, references, on no/en/fr/de wikis. Save 3 raw samples per
  wiki as fixtures.
- Prototype the WebView column pagination with a long Norwegian chapter and a
  poem; verify locator round-trip after font-size change.
- Confirm siteinfo namespace numbers for the 11 configured wikis.
- Pull Wikidata for 50 Norwegian works; measure how many have P577/P136/P50.
- Output: `docs/wp0-findings.md`; update **VERIFY** items in this plan.
Acceptance: findings documented; pagination prototype turns pages at 60 fps
on an emulator; locator restores to the same sentence after re-pagination.

### WP1 — Catalog pipeline for the 11 configured wikis (3–4 weeks)
- (WS, 2026-10-31, WS§6, §10.8 item 4) **WP1a, structure and quality survey
  (3–4 days), first.** Run 5.2 steps 1–1h and 4 over the pages-articles
  dumps of no, is, de, fr and en and write `report/{wiki}-structure.md`:
  counts per pattern A–I (WS§1) and per `structure` value; the parent-rule
  histogram (container / hierarchy / TOC / own) and the `order_source`
  histogram; container links that point outside ns0 or to excluded pages;
  prev/next chains that cross `index` boundaries; orphan trees (count,
  examples), oversized pages (count, largest), red-link ratios in TOCs;
  colon prefixes with ≥ 100 root pages; the parts-table row count and
  gzipped size at `parts_cap` ∈ {500, 2,000, ∞}; works per quality tier,
  the unknown count with a 30-text random sample, junk-rule yields,
  missing-page counts and the size of the default view; 30 random works
  per pattern with their resolved TOC, for eyeballing. Acceptance: the
  report exists for the five wikis; the eyeball samples for patterns A, B,
  D and E are correct for at least 27 of 30; the owner has set the
  thresholds of WS§8 and each wiki's `show_unknown_by_default` from the
  numbers. (Done 2026-11-01: the owner's nine decisions and their evidence
  are in report/wp1a-decisions.md.)
- Implement sections 5.1–5.6. Start with `no` and `is` (small), then `en`.
- CI workflow producing manifest + catalogs.
Acceptance: `make catalog WIKI=no` produces a catalog where ≥ 90 % of works
have an author, ≥ 60 % have a year, and the coverage report prints; FTS query
"Ibsen" returns Ibsen's works; `excluded` table explains every exclusion.
(WS, 2026-10-31, WS§9; figures amended by WP1a, 2026-11-01,
report/wp1a-decisions.md item 5) Also: `Der Sagenschatz des Königreichs
Sachsen` is one work with 1,001 parts in chain order; `Diktsamling` is one
work with 4 parts (the 5 other existing TOC targets belong to other
editions by rule 2), 116 missing, incomplete; `Ved Rundarne (1864)` is
found by search as a text of `Diktsamling`; `Die Heimath in der neuen
Welt` is one virtual-root work with three volumes; `Ozymandias` finds the
Hutchinson text (and any other edition on en); `Käthe` is flagged
`oversized`; `Kinder und Hausmärchen` (the editions overview) is not a
work and `Kinder- und Haus-Märchen Band 1 (1857)` is; `Topographia
Hassiae` is one work of about 200 texts and `Allgemeines Deutsches
Kommersbuch:47` is a part, not a work; *Popular Science Monthly* is a
collection, not a reference work; the en parts table stays within the
measured budget (about 18 MB gzipped plus 3 MB of `parts_fts` at
`parts_cap` 2,000); the default view of the no catalog holds about 470
works and 2,400 texts and none of them is raw OCR; the `excluded` table
names every hard exclusion.

### WP2 — App skeleton: catalog, browse, search, work detail (3 weeks)
- First-run wiki selection and catalog download; browse facets; offline FTS;
  online search; work detail with metadata, attribution, parts list, size
  estimate.
- (WS, 2026-10-31, WS§7.1) Search returns works and texts; open-by-URL
  resolves through `parts` and `aliases`; quality filter chips and labels
  (5.4); the parts tree and "9 of 125 texts transcribed" on the detail
  screen.
Acceptance: fully offline browsing after catalog download; all facets combine;
switching UI language to nb and nn shows translated strings on every screen.

### WP3 — Content fetching, normaliser, downloads (3 weeks)
- Sections 7.1–7.4 with fixture tests; WorkManager queue; partial downloads;
  images; storage management (per-work size, delete).
- (WS, 2026-10-31, WS§7.2, §7.4) Live resolution, per-page attribution for
  works made of several wiki pages (R11), synthetic parts, and the
  structure fixtures listed in WS§7.4.
Acceptance: all fixtures pass; a 60-chapter novel downloads in the background
and survives app kill; airplane mode after download still opens every chapter.

### WP4 — Reader core (3–4 weeks)
- Section 8.1, 8.4, 8.5, 8.6, positions and bookmarks (R5–R7, R10).
Acceptance: swipe paging across chapter boundaries without a visible gap;
position restored after font change, rotation, process death; bookmarks list
jumps to the exact sentence; footnote popups work.

### WP5 — Page marks and line numbers (1–2 weeks)
- Section 8.2 and 8.3 (R8, R9) across the fixture corpus.
Acceptance: a scan-backed Norwegian work shows the original page numbers in
inline and margin modes, each tappable; a poem shows generated numbers every 5
lines; a French text with source line numbers shows and hides them.

### WP6 — Localization, accessibility, tablets (2 weeks)
- All 11 locales complete (en, nb, nn, no→nb copy, fr, de, it, sv, da, fi, is,
  es). Machine-draft translations are acceptable initially but every file gets a
  header comment `<!-- needs native review -->` until reviewed; Norwegian (nb
  and nn) will be reviewed by the project owner. Per-app language picker
  (`LocaleManager` / AppCompat `setApplicationLocales`, `locales_config.xml`).
- TalkBack pass; large-font UI; tablet two-page spread.
Acceptance: no hard-coded strings (lint `MissingTranslation` is an error);
every screen usable with TalkBack; nn and nb differ where the languages differ
(e.g. "Innstillingar" vs "Innstillinger").

### WP7 — Polish and release (2–3 weeks)
- App icon (original design, no Wikimedia logos), onboarding tips, error
  states, empty states, crash-free run on 3 devices, Play closed testing,
  F-Droid metadata (the app is GPL-3.0), privacy notice (no analytics, no
  accounts, network only to Wikimedia and the catalog host).
- Store listing per 1.2: title `Bibliotheke for Wikisource`, localised long
  name and short description, non-affiliation notice in every language.
Acceptance: release build signed; store listing in all 11 languages with the
notice present; launcher shows `Bibliotheke`.

### WP8 — Later (not scheduled)
Text-to-speech, dictionary lookup, highlights with notes, cross-device sync,
"random text" and "recently added" feeds, EPUB export, scan image view beside
text, reading statistics. (WS, 2026-10-31, WS§8 item 6) Lookup in reference
works.

---

## 10. Testing

- Unit: normaliser against the fixture corpus; locator maths; pipeline merge
  and filter logic.
- Instrumented: Room migrations; download queue with a mock server (MockWebServer).
- UI: Compose tests for browse/search/settings; a reader test harness with a
  fixed viewport that asserts page counts and locator restoration.
- Manual matrix: Android 8, 12, 14+, one tablet, one small phone (≤ 5.5"),
  dark mode, system font scale 1.3.
- Network politeness: a test asserting the UA header and that no more than one
  concurrent request per host is in flight.

---

## 11. Risks and mitigations

| Risk | Mitigation |
|---|---|
| Markup differs per wiki and per era of template | Per-wiki selector config + fixture corpus; normaliser degrades to plain paragraphs rather than failing |
| Metadata gaps (genre/year) make facets look thin | Coverage report drives mapping work; "Unknown" bucket always shown; browsing by author and title works regardless |
| WebView column pagination quirks (tall images, wide tables, RTL) | Constrain media in CSS; wrap tables; test RTL (he, ar, fa) early with `dir` from wiki |
| Very large works | Part-level downloads, one part in memory, lazy TOC |
| Wikimedia API changes / RESTBase retirement | All network access behind one interface; endpoints in config; WP0 verification |
| Catalog size on device for `en` | Read-only attached SQLite, FTS5, no row import |
| Translations wrong in 9 languages | Native review flags; strings kept short and literal; glossary in `docs/glossary.md` |
| (WS, 2026-10-31, WS§9) Header container links are wrong or missing | Rule 3 (TOC membership) as fallback, 5.2 step 1b; the WP1a survey counts how often rule 1 fails |
| (WS) Editions merged by mistake | Rule 1 never climbs through `TITEL` or title fields; a work is one edition (3.2) |
| (WS) Good texts drowned by raw OCR | Default filter of 5.4 (unproofread and incomplete hidden), measured per wiki by the WP1a survey |

---

## 12. Conventions

- Kotlin official style, ktlint, detekt. Compose previews for every screen.
- Repo layout as in section 6. `docs/STATUS.md` updated on every stop.
- Commit after every work-package sub-step with messages prefixed `WPn:`.
- No analytics, no third-party trackers, no accounts.
- The only contact detail permitted in code, UA or metadata is
  `develop@robertemilberge.no` (section 0). No other personal information
  about the owner goes into the repository, the app or the store listing.

## 13. Open items

None. All decisions are made. One recommendation left to the implementer:
bundle a seed catalog for `no` inside the APK (adds ~2–5 MB) so Norwegian
users can browse before the first catalog download finishes.

Decided since the first draft: licence GPL-3.0-or-later (D8), contact address
for the User-Agent (section 0), application id and Play title (1.2), catalog
host GitHub Releases (D6).

(WS, 2026-10-31, WS§8–9) Later items, not in v1: grouping editions of one
text (versions pages, de `{{Andere Version}}`, Wikidata P629/P747, the it
`Opera:` namespace); "also in" memberships of a text in a second work; a
lookup feature for reference works (WP8); merging root-page volumes that
are chained only by prev/next (revisit with the WP1a survey's counts).
(WP1a, 2026-11-01, report/wp1a-decisions.md) Also later: the suffix
encoding of `parts.page_title` (halves the raw parts table, complicates
open-by-URL); a title regex for reference works typed book or untyped
(*The New International Encyclopædia*, lexica under 2,000 parts); ranking
search hits whose page title is a chapter label below texts with a title of
their own.
