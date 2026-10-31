# WP0 findings

Results of the WP0 spike (PLAN.md section 9). Each section records what was
checked against the live services on 2026-10-31, the evidence, and what it
changes in the plan. Raw outputs live in [docs/wp0/](wp0/); the scripts that
produced them are in [pipeline/wp0/](../pipeline/wp0/).

## Summary

**Acceptance criteria of WP0: met.**

- Findings documented (this file); VERIFY items in PLAN.md updated (section 7).
- Pagination prototype turns pages at 60 fps on the emulator: 18 frames per
  turn, no skipped vsync, 99th percentile 17 ms (section 4.5).
- The locator restores to the same sentence after re-pagination: on every
  page of five parts (including a 600 k-character novel), in headless
  Chromium and in Android WebView 134, after font-size and geometry changes,
  and back to exactly the same page and offset (sections 4.3, 4.5).

The findings that change the most:

1. **Metadata**: only 13 % of no works have a Wikidata item. Header and
   Index-page data must be the main source, so WP1 needs the pages-articles
   dump (sections 3, 5).
2. **Per-wiki markup differs more than the plan assumed**: page markers
   come in four shapes, de has no microformats, no `ws-summary` and no Author
   namespace, and footnote links are `./Page#cite_note-N` (section 2).
3. **de structure**: ~196,000 lexicon entries as root pages, collections of
   separate root pages (also found on no), whole novels on one page; these need exclusion rules
   and part splitting (sections 1, 2.4).
4. **Reader**: corrected column CSS, a reading anchor that survives
   relayouts, tap forwarding through the overlay (section 4).

## 1. Wiki list and namespace numbers

Script: `python3 pipeline/wp0/namespaces.py` →
[wp0/sitematrix.json](wp0/sitematrix.json), [wp0/namespaces.json](wp0/namespaces.json).

### Sitematrix

- The sitematrix lists **85 Wikisource wikis** including `wikisource.org`
  (open: 83; closed: `ang`, `ht`). PLAN.md R1's "~75" is low; the pipeline
  should skip wikis with `closed: true`.
- `wikisource.org` appears under `specials` with `code: "sources"` and dbname
  `sourceswiki` (not `code == "wikisource"`), so the filter in PLAN.md 3.1
  misses it. Treat the `sources` special site as wiki code `mul`.

### Namespaces (`siteinfo` + ProofreadPage `meta=proofreadinfo`)

`meta=proofreadinfo&piprop=namespaces|qualitylevels` returns the Page and
Index namespace ids authoritatively; use it instead of matching names.

| wiki | sitename | siteinfo `lang` | dbname | Author | Index | Page | other even ns ≥ 100 |
|---|---|---|---|---|---|---|---|
| no | Wikikilden | **nb** | nowikisource | 102 Forfatter | 106 Indeks | 104 Side | – |
| en | Wikisource | en | enwikisource | 102 Author | 106 Index | 104 Page | 100 Portal, 114 Translation |
| fr | Wikisource | fr | frwikisource | 102 Auteur | **112 Livre** | 104 Page | 100 Transwiki, 106 Portail, 2600 Sujet |
| de | Wikisource | de | dewikisource | **none** | 104 Index | **102 Seite** | – |
| it | Wikisource | it | itwikisource | 102 Autore | 110 Indice | 108 Pagina | 104 Progetto, 106 Portale, 112 Opera |
| sv | Wikisource | sv | svwikisource | **106** Författare | 108 Index | 104 Sida | 2600 Ämne |
| da | Wikisource | da | dawikisource | 102 Forfatter | 106 Indeks | 104 Side | – |
| fi | Wikiaineisto | fi | fiwikisource | **none** | 252 Hakemisto | 250 Sivu | – |
| is | Wikiheimild | is | iswikisource | 102 Höfundur | 252 Frumrit | 250 Blaðsíða | 100 Gátt |
| es | Wikisource | es | eswikisource | **106** Autor | 104 Índice | 102 Página | 100 Portal |
| mul | Wikisource | en | sourceswiki | **108** Author | 106 Index | 104 Page | 1198 Translations |

(Common technical namespaces 710 TimedText, 828 Module, 1728 Event omitted.)

Consequences:

- Namespace numbers differ widely; the plan's `{author: 102, index: 106,
  page: 104}` holds only for no, en and da. Every per-wiki config
  must take them from siteinfo/proofreadinfo, never from defaults.
- **de and fi have no Author namespace.** Author pages are ordinary
  main-namespace root pages: on de they carry `Kategorie:Autoren`; on fi only
  a per-author category (e.g. `Luokka:Aleksis Kivi`) and no author template.
  The candidate-works filter (5.2 step 1) therefore needs an extra rule:
  exclude a root page whose Wikidata item is a human (`P31 = Q5`), plus the
  per-wiki author category where one exists (de: `Kategorie:Autoren`).
- no.wikisource reports `lang: nb`. The catalog's default language for the
  `no` wiki should be `nb` unless the Index `Malform` field or P407 says
  otherwise (sections 3, 5).
- `mul` reports `lang: en` (interface language only). Work language there
  must come from Wikidata P407 or categories, never from siteinfo.
- ProofreadPage quality levels are the same five ids on all wikis (0 without
  text, 1 not proofread, 2 problematic, 3 proofread, 4 validated); only the
  category names are localised (no: Uten tekst, Rå, Ufullstendig,
  Korrekturlest, Validert).

### Pseudo-namespaces in de mainspace

de keeps large reference works as main-namespace root pages with a colon
prefix, so "root page, no `/`" counts every lexicon entry as a work. Search
`prefix:` hit counts on 2026-10-31: `RE:` (Paulys Realencyclopädie) 98,928,
`MKL1888:` (Meyers Konversations-Lexikon) 38,931, `ADB:` (Allgemeine
Deutsche Biographie) 30,806, `BLKÖ:` 27,210; about **196,000** pages, against
656,761 content pages in total. The de config needs
`exclude_title_prefixes` for them.

## 2. Parsoid HTML: endpoint and markup shape

Tools: `python3 pipeline/wp0/parsoid.py analyse|save …`,
`python3 pipeline/wp0/search.py …`. Fixtures (raw REST HTML plus
`source.json` with title, revision, fetch date, licence note and a shape
summary) are in [app/src/test/fixtures/](../app/src/test/fixtures/).

### 2.1 Endpoint (VERIFY in PLAN.md 3.5: confirmed, with details)

- `GET https://{wiki}/w/rest.php/v1/page/{title}/html` returns Parsoid HTML,
  `Content-Type: text/html; profile="https://www.mediawiki.org/wiki/Specs/HTML/2.8.0"`,
  on all four wikis tested (no, en, fr, de). The microformat classes are
  intact where the wiki emits them (no, en, fr; de emits none, 2.5).
- **Titles must be fully percent-encoded, including `/` as `%2F`.**
  `Peer_Gynt/Personerne` gives 404 `rest-no-match`;
  `Peer_Gynt%2FPersonerne` gives 200.
- The revision id is in `<html about="…/revision/{revId}">`.
  `…/with_html` returns JSON with `latest: {id, timestamp}` and
  `license: {url, title}` (CC BY-SA 4.0) beside `html`.
- Conditional requests work: `ETag: W/"{revId}/{uuid}/view"`; sending it back
  as `If-None-Match` gives `304`. Use this for update checks (N6).
- A redirect page answers `307` to `…/{target}/html?redirect=no`; OkHttp and
  requests follow it.
- `?flavor=view` and `?flavor=fragment` do not strip `data-mw`. `data-mw`
  attributes are 4–29 % of the HTML characters in the 17 fixtures; the
  normaliser drops them.

### 2.2 Fixtures saved (17 pages; plan asked for 3 per wiki)

| fixture | title | rev | pagenum | ws-summary | poem | fn refs | img | tables |
|---|---|---|---|---|---|---|---|---|
| de/erlkoenig | Erlkönig | 3778237 | 0 (own markers, see below) | 0 | 1 | 0 | 9 | 5 |
| de/kaethe | Käthe | 3849635 | 471 | 0 | 3 | 10 | 3 | 2 |
| de/sagenschatz-sachsen | Der Sagenschatz des Königreichs Sachsen | 3765001 | 31 | 0 | 1 | 5 | 2 | 29 |
| en/city-shower | A Description of a City Shower | 15124876 | 1 | 0 | 1 | 0 | 3 | 0 |
| en/frankenstein-1831 | Frankenstein, or the Modern Prometheus (Revised Edition, 1831) | 16297094 | 3 | 2 | 0 | 0 | 4 | 2 |
| en/frankenstein-1831-ch1 | …/Chapter 1 | 15019029 | 6 | 0 | 0 | 0 | 0 | 0 |
| en/jesus-the-christ-18 | Jesus the Christ/Chapter 18 | 12778148 | 0 | 0 | 0 | 62 | 0 | 0 |
| fr/essai-moeurs-1 | Essai sur les mœurs/Chapitre 1 | 2017228 | 11 | 0 | 0 | 8 | 0 | 0 |
| fr/madame-bovary | Madame Bovary | 14104772 | 0 | 1 | 0 | 0 | 2 | 0 |
| fr/madame-bovary-1-1 | Madame Bovary/Première partie/1 | 10429205 | 14 | 0 | 0 | 0 | 0 | 0 |
| fr/phedre-1854-acte-1 | Phèdre (Racine), Didot, 1854/Acte I | 12357936 | 5 | 0 | 71 | 0 | 0 | 0 |
| fr/poete-au-ver-de-terre | La Légende des siècles/Le Poète au ver de terre | 14191700 | 2 | 0 | 2 | 0 | 0 | 0 |
| no/arne | Arne | 318255 | 3 | 1 | 0 | 1 | 1 | 1 |
| no/arne-1 | Arne/1 | 52630 | 4 | 0 | 0 | 0 | 0 | 0 |
| no/peer-gynt-1 | Peer Gynt/Første akt | 43336 | 47 | 0 | 10 | 0 | 0 | 0 |
| no/sult-02 | Sult/02 (Hamsun; long prose chapter, 71 k chars) | 302062 | 71 | 0 | 0 | 0 | 0 | 0 |
| no/ved-rundarne-1864 | Ved Rundarne (1864) (Nynorsk) | 292903 | 2 | 0 | 2 | 0 | 0 | 0 |

### 2.3 Page-number markers (VERIFY in PLAN.md 3.3: shape differs per wiki)

Each wiki customises `MediaWiki:Proofreadpage_pagenum_template`, and de also has
pre-ProofreadPage texts, so there are four shapes:

| wiki | markup |
|---|---|
| en | `<span class="pagenum ws-pagenum" id="i" data-page-number="i" data-page-name="Page:Foo.djvu/7" data-page-index="7" data-page-quality="4" title="Page:Foo.djvu/7">&#8203;</span>` |
| fr | `<span class="pagenum ws-pagenum" id="242" title="Page:Foo.djvu/254">` (no `data-*`) |
| no, de (with `<pages>`) | `<span class="pagenum" id="5" title="Side:Foo.djvu/6">` (no `ws-pagenum`, no `data-*`; local namespace name `Side:`/`Seite:`) |
| de (older texts without ProofreadPage) | `<span class="pr1pn" id="Seite_104">[<b><a href="…action=edit&image=…">104</a></b>]</span>`, with no `Page:` title; the number is visible text |

Rules for the normaliser:

- Display number: `data-page-number` if present, else `id`. It can be roman
  (`i`, `III`) or a label such as `FP`; it is not always numeric.
- Scan page title: `data-page-name` if present, else `title` with `_` → space.
  Single-page files have no `/N` suffix (`Page:Cyclopaedia … page 548.djvu`).
- Per-page proofreading quality: `data-page-quality` (en), or the `quality`
  parameter in the span's `data-mw` (present on only some spans on no). Useful later; not needed for R8.
- en markers contain a zero-width space (U+200B). The marker is excluded
  from locator text (7.4), so this is harmless as long as the normaliser
  empties the span.
- Position: in prose the marker sits **inside the `<p>`** at the exact break
  (en Frankenstein ch. 1: 436 of 1005 characters into the paragraph). Between
  blocks it sits alone in an otherwise empty `<p><span><span class="pagenum">`
  wrapper, or deep inside `dl > dd` nests in plays. Inline mode works directly;
  margin mode needs the `wsr-has-page` wrapper from 8.2.
- Config: `page_number_selectors` must include `span.pagenum` (covers all
  ProofreadPage shapes) and, for de, `span.pr1pn`.

### 2.4 Table of contents / `.ws-summary` (VERIFY 3.3; PLAN.md 7.1)

- **no, en, fr**: `.ws-summary` exists (from `{{Auxiliary Table of Contents}}`,
  `{{TOC begin}}`, `{{Dotted TOC page listing}}`, `{{Export TOC}}` on no/en;
  hand-made `<div class="ws-summary">` on fr) but only on some works:
  `Peer Gynt` has none, so the subpage-link fallback is needed.
- `.ws-summary` can be **nested** (en: a `div.ws-summary` around a
  `table.ws-summary`), so links are counted twice; de-duplicate by target.
- `.ws-summary` also links non-subpages (fr: an author biography, `Page:`
  links). Keeping only subpage targets, as 7.1 says, is right.
- Document order outside `.ws-summary` is unreliable: on the en Frankenstein
  root the first subpage links in body order are Introduction, Chapter V,
  Preface (from an illustrations list). Use `.ws-summary` order whenever it
  exists.
- fr root pages may link a `…/Texte_entier` subpage (the whole work on one
  page) outside `.ws-summary`. The fallback must skip it (per-wiki
  `exclude_subpage_titles: [Texte entier]`).
- fr nests parts: `Madame Bovary/Première partie` (a TOC page) and
  `…/Première partie/1`. The recursive expansion in 7.1 step 4 is needed.
- **de has no template that emits `ws-summary`.** de collections are TOC pages
  whose tables link **separate root pages** (Sagenschatz: about 990 links to
  root pages, 2 to subpages). Subpage-based part discovery does not work for
  them; see section 6.
- **The same pattern exists on no.** Vinje's `Diktsamling` (1864) is a TOC
  page linking 125 separate root pages, one per poem (9 transcribed, 116
  still redlinks), and each poem's header links back to it
  (`Ved Rundarne (1864)` → `Diktsamling`). The en and fr samples used
  subpages; how common the pattern is per wiki was not measured.
- de puts **whole novels on one root page**: `Käthe` is 1.25 MB of
  HTML, 604 k text characters, 471 page markers. This breaks N3 (memory
  bounded by one chapter) unless the normaliser splits large single-page works
  into synthetic parts (at headings, else at page markers) above a size
  threshold.
- Orphan subpage trees exist: `Die Heimath in der neuen Welt/Zweiter Band`
  exists but its root page does not (404). The candidate-works step should
  treat the top-most existing ancestor of such trees as the work.

### 2.5 Header, metadata microformats and noise

- no, en, fr: `ws-title`, `ws-author`, `ws-year`, `ws-publisher`, `ws-place`,
  `ws-type` are present in the header output; `ws-chapter` on chapter pages;
  fr adds `ws-cover` (`Foo.djvu/1`).
- **de: no `ws-*` microformats in any of the three samples.** The header is `div#textdaten`
  (`{{Textdaten}}`), marked `notheme noprint ws-noexport`; metadata must come
  from Wikidata or the template parameters in `data-mw` / wikitext.
- Header selectors seen: no/fr `div#headertemplate.ws-noexport` plus an inner
  `.headertemplate`; en `div.ws-header.wst-header-structure` (also
  `ws-noexport noprint`); de `div#textdaten`. All carry `ws-noexport`, so
  dropping `.ws-noexport` removes headers on all four wikis.
- COinS `span.Z3988` carries title, author, publisher, place, date, page
  range and genre in its `title` attribute on no/en/fr; a secondary metadata source for the pipeline.
- Licence boxes are **not** `ws-noexport`: on no it is a bare `<table>` at the
  end of the page (template `PD-old`). The normaliser needs per-wiki
  `licence_templates` to drop them (match on the template name in `data-mw`),
  and the app shows the licence itself (R11).
- en adds `.similar.ws-noexport` ("For other versions…") and an
  authority-control `.navbox`; both are removed by existing rules.

### 2.6 Verse

- `<poem>` renders as `div.poem[typeof~="mw:Extension/poem"] > p` with
  `<br>` between lines, as PLAN.md 7.2 assumed. Stanza breaks are blank
  lines (`<br>` `<br>`) inside the same `<p>`, not separate `<p>`s (4.4). fr
  adds class `verse` (`<poem class=verse>`). The `.poem` can also carry
  `typeof="mw:Extension/poem mw:Transclusion"`.
- de inserts line-number blocks **between** `<p>`s inside one `.poem`
  (Parsoid splits the `<p>` around each template, sometimes mid-stanza), so
  a "line" is not always a direct `p` child; the verse converter
  must walk text and `<br>` in order across `p` and `div` children.
- Plays indent speeches with `:` wikitext, giving `dl > dd` nests (no
  Peer Gynt). **`dl`, `dd`, `dt` are not in the normaliser whitelist (7.2)**;
  add them, or map `dd` to an indented `div`, or plays lose their structure.

### 2.7 Source line numbers (R9; populates `line_number_selectors`)

- **fr**: `{{NumVers}}` / `{{NumVersDroite}}` render
  `<span style="visibility:hidden"><span class="OptionText" title="Vers numérotés">…<span …>5</span></span></span>`
  inside the verse line. Selector: `span.OptionText[title="Vers numérotés"]`;
  number = its text. (`{{Numérotation}}`, the plan's example, is a running
  page header, not line numbers. fr Bible verse numbers via `{{Nv}}` are bare
  `<sup>` with no class and cannot be detected.)
- **de**: `<div class="ws-noexport" style="position:relative"><span class="zeilennummer">5</span></div>`
  between the poem's paragraphs. Selector: `span.zeilennummer`. **It sits inside
  `.ws-noexport`, so the normaliser must extract line numbers before it
  removes `.ws-noexport`** (rule order in 7.2).
- no, en: no source line numbers found in the samples.

### 2.8 Footnotes (PLAN.md 7.2 selector needs a change)

- Reference: `<sup class="mw-ref reference" id="cite_ref-1" typeof="mw:Extension/ref"><a href="./Arne#cite_note-1">…</a></sup>`.
  **The href is `./{Page}#cite_note-N`, not `#cite_note-N`**, so the plan's
  selector `sup.reference a[href^=#cite_note]` matches nothing. Use
  `sup.mw-ref` and take the fragment after `#`.
- Notes: `<ol class="mw-references references"><li id="cite_note-1" data-mw-footnote-number="1">`
  with `span.mw-cite-backlink` (drop it) and `span.reference-text`.
- Several groups per page: de has a `WS` group for editorial corrections
  (`data-mw-group="WS"`, refs render as `[WS 1]`). Keep each `ol` as its own
  list inside `section.wsr-footnotes`, preserving the group label.

### 2.9 Images (affects PLAN.md 0, 3.5, 7.2)

- `img[src]` now points to **`//thumb.wikimedia.org/wikipedia/commons/thumb/…`**
  (protocol-relative, with `?utm_source=…` parameters), not
  `upload.wikimedia.org`. `srcset` gives a 2x variant on the same host.
  Add `thumb.wikimedia.org` to the allowed hosts in PLAN.md section 0,
  prefix `https:`, and strip the `utm_*` parameters before downloading.
- `resource="./File:…"` (localised: `Fil:`, `Datei:`) names the file, so the
  normaliser can build a specific width itself via the thumb URL pattern or
  `prop=imageinfo&iiurlwidth=800`.
- Icons (PD-icon 40 px, Wikimedia/Wikidata logos 20 px) appear in licence and
  sister-project boxes; the "skip icons < 32 px" rule drops them.

### 2.10 Versions and disambiguation pages

- no: versions pages (`Ved Rundarne`, template `Mal:Peker`,
  `Kategori:Pekere`) carry the `disambiguation` page prop, as PLAN.md 3.2
  assumes.
- **en: `Ozymandias (Shelley)` is a versions page without the
  `disambiguation` prop.** It is marked only by `Template:Versions` and
  `Category:Versions pages`, and it has a `wikibase_item` (Q662166) that
  points to the *work* item (P31 literary work), not an edition. Per-wiki versions templates/categories are
  therefore required in the exclusion rules, not optional.
- Many en poems exist only as subpages of collections
  (`The Complete Poetical Works of Percy Bysshe Shelley (ed. Hutchinson,
  1914)/Ozymandias`); the catalog lists the collection, and the poem is a
  part of it. This follows from "a work is a root page" and is acceptable.

## 3. Wikidata coverage for Norwegian works

Script: `python3 pipeline/wp0/wikidata_no.py` →
[wp0/wikidata-no.json](wp0/wikidata-no.json) (every sampled title with its
values, so the numbers can be checked).

### 3.1 How many works have a Wikidata item at all

- no.wikisource has **3,850 main-namespace root pages** (non-redirect, no
  `/`) out of 15,071 content pages; 58 of them are disambiguation pages.
- **Only 497 root pages (13 %) have a `wikibase_item`.** Most Norwegian works are
  not connected to Wikidata (e.g. Vinje's `Ved Rundarne (1864)`); their page
  props carry `unexpectedUnconnectedPage` instead.

### 3.2 Coverage among works that have an item (50 evenly spaced)

Sample: every 9.6th root page with a QID, alphabetically, excluding
disambiguation pages.

| property | on the linked item | added via P629 work item | either |
|---|---|---|---|
| P577 publication date | 35/50 | 0 | **35/50 (70 %)** |
| P136 genre | 4/50 | 4 | **8/50 (16 %)** |
| P50 author | 41/50 | 0 | **41/50 (82 %)** |
| P407 language of work | 47/50 | 0 | **47/50 (94 %)** |

- The linked item is an edition (`P31 = Q3331189`) for only 28 of 50; the
  rest are linked straight to work-level items (literary work 6, article 5,
  fairy tale 2, folk tale, prayer, magazine, book series, eddic poem…).
  Only 5 have P629, so the edition → work hop adds little. The pipeline must
  read P50/P577/P136 from whatever item is linked, edition or not.
- P407: Bokmål (Q25167) 22, Norwegian (Q9043) 19, Nynorsk (Q25164) 3, other
  languages 6 (Greek, Czech, English, Russian, Old Icelandic), none 3.
  Danish (Q9035) does not occur. Wikidata labels 19th-century
  Dano-Norwegian texts as "Bokmål", so an `nb`/`nn`/`da` sub-facet from P407
  is possible but historically loose; "Norwegian" (Q9043) must map to the
  plain "Norwegian" bucket.
- Sitelink badges: 48 none, 1 proofread (Q20748092), 1 validated
  (Q20748093). Badges are not a useful quality source on no.

### 3.3 Header microformats as the main fallback

The same measurement on **50 evenly spaced root pages of any kind** (14 of
them have a QID), reading the Parsoid header microformats:

| field | present |
|---|---|
| `ws-author` | 44/50 (88 %) |
| `ws-year` (all numeric) | 46/50 (92 %) |
| `ws-title` | 46/50 |
| COinS `span.Z3988` | 46/50 |
| uses `<pages>` (scan-backed) | 46/50 |

Of the 6 without `ws-author`, 4 have no header microformats at all (older
header templates, e.g. the hymn `Dagen viger og gaar bort`) and 2 are
scan-backed texts with a year but no author (probably anonymous).
`ws-author` is a display string and can name several people
(`Asbjørnsen og Moe`); take author identities from the `Forfatter:` links
inside the header, not by splitting the text.

### 3.4 Consequences for WP1

- With Wikidata alone, the WP1 acceptance target for `no` (≥ 90 % with an
  author, ≥ 60 % with a year) is out of reach: at most about 11 % of works
  would have an author (82 % of the 13 % that are connected).
- The header data is what reaches the target. Scan-backed works (92 % here)
  get it from `<pages … header=1>`, which pulls the fields of the `Indeks:`
  page, so **Index pages are the main metadata source on no**.
- PLAN.md 5.1 does not list an input that contains header data: the SQL
  dumps carry no wikitext. WP1 needs one of:
  - the `{dbname}-latest-pages-articles.xml.bz2` dump, parsing ns 0 root
    pages (header template params, `<pages index=… header=1>`) and Index
    pages (their field values); this scales to en and de; or
  - Parsoid HTML (or `action=parse`) per root page, reading the `ws-*`
    microformats; fine for no (3,850 requests) and is, not for en or de.
  Recommendation: pages-articles dump as the primary source on every wiki,
  Parsoid microformats as a cross-check in the smoke test.
- Genre needs categories (no: `Kategori:Dikt` has 419 members,
  `Kategori:Diktsamlinger` 45) and header `ws-type`; Wikidata gives genre
  for 16 % of connected works.

## 4. Pagination prototype and locator round-trip

Code: [prototypes/pagination/](../prototypes/pagination/). A minimal
normaliser (`make_content.py`) turns fixtures into reader HTML; `reader.js`
paginates and computes locators; `test.js` checks them inside the page.

### 4.1 CSS layout (correction to PLAN.md 8.1)

The plan's literal CSS (`body{column-width:100vw;column-gap:0}` with the
margin as padding on an inner wrapper) puts the side margin only on the first
page. What works:

```css
#wsr-content {                    /* one box, exactly one page wide */
  box-sizing: border-box; width: var(--page-w); height: var(--page-h);
  padding: var(--margin-v) var(--margin-h);
  column-width: calc(var(--page-w) - 2 * var(--margin-h));
  column-gap: calc(2 * var(--margin-h));
  column-fill: auto;
}
```

Column n then starts at exactly `n × page-w`, and page n is shown with
`transform: translate3d(-n × page-w, 0, 0)`. Extra columns overflow to the
right because the height is fixed. `--page-w` is set from JS and **snapped
down to 1/64 px**, Blink's layout unit. With a fractional CSS width (a
1080 px screen at DPR 2.625 is 411.43 CSS px), an unsnapped width can let
the column step (rounded to layout units) and the exact translate disagree
by up to 1/64 px per page, which would add up to a visible shift after a
few hundred pages. This is reasoned from Blink's layout units, not
measured; with snapping, no drift showed over 1177 pages (4.5).

### 4.2 Locator algorithm (PLAN.md 7.4, confirmed)

- Text index: all text nodes in document order except inside `.wsr-footnotes`,
  `.wsr-page`, `.wsr-ln-src`; cumulative start offsets.
- Text fills each column top to bottom, then the next column, so a
  character's page never decreases with its offset. Both directions are
  binary searches over `Range.getClientRects()`:
  - page of an offset: rect of that character, `floor((x − contentLeft) / page-w)`;
  - locator of a page: first measurable text node whose last box is on page
    ≥ n, then the first character in it on page ≥ n.
- Collapsed whitespace (newlines between blocks) has no box; it counts as the
  next character that has one.

### 4.3 Results in headless Chromium (phone page 412 × 915 CSS px, DPR 2.625)

`prototypes/pagination/run_browser_test.sh` →
[wp0/pagination-browser.json](wp0/pagination-browser.json). Chromium 154.
Checks per part: on **every** page the locator lies on that page and
locators strictly increase; on 6 probe pages, locator L at 18 px → 26 px →
landscape 915 × 412 → back to 18 px portrait.

| part | chars | pages 18 px / 26 px | layout / relayout | all-page check | probes |
|---|---|---|---|---|---|
| no/sult-02 (Hamsun, prose) | 71,377 | 60 / 124 | 37 / 17 ms | 0 failures | 6/6 |
| no/ved-rundarne-1864 (Nynorsk poem) | 1,310 | 2 / 4 | 2 / 1 ms | 0 failures | 2/2 |
| no/peer-gynt-1 (play, verse) | 29,292 | 59 / 101 | 22 / 15 ms | 0 failures | 6/6 |
| de/erlkoenig (poem, source line numbers) | 1,296 | 2 / 4 | 4 / 1 ms | 0 failures | 2/2 |
| de/kaethe (whole novel, one page) | 602,553 | 547 / 1126 | 139 / 115 ms | 0 failures | 6/6 |

In every probe the locator was visible on the page shown after the font
change and after the geometry change, and going back gave **exactly the same
page and the same locator** (e.g. Sult page 20, L = 25132, "Jeg kasted mig
flad paa Gulvet og græd af Glæde…" → page 42 at 26 px → page 48 landscape →
page 20, L = 25132). The acceptance criterion "locator restores to the same
sentence after re-pagination" is met in the browser engine; section 4.5
covers the Android WebView.

### 4.4 Normaliser lessons from the prototype (input for WP3)

- **Verse stanzas** are blank lines (`<br>` `<br>`) inside one `<p>`; on no
  and fr Parsoid puts a whole poem in a single `<p>`. On de, Parsoid also
  splits `<p>` around templates, sometimes mid-stanza, so a `<p>` boundary
  ends a line but must not end a stanza.
- **Generated line numbers** must re-sync to source line numbers where both
  exist: Erlkönig has a bold title line inside `<poem>`, so plain counting is
  off by one against the printed numbers (source "5" would be generated 6).
- **Locator text needs whitespace at block boundaries.** Verse lines and
  stanzas are `display:block` spans/divs with no text between them, so the
  locator text read "Dalar,som deim…" and "Erlkönig.Wer reitet…". Insert a
  `\n` text node between such blocks (invisible in layout) so bookmark
  excerpts (R6) and in-work search see separate words.
- Drop `lang` attributes from Parsoid wrappers (`prp-pages-output` carries
  the wiki language, `nb`, on Nynorsk text); set `lang` on `<html>` from the
  work's language for hyphenation.
- A 600 k-character part lays out in about 140 ms in desktop Chromium; on a
  phone expect several times that on every font change. This supports
  splitting oversized single-page works (section 2.4).

### 4.5 Results in the Android WebView on the emulator

Code: [prototypes/pagination/android/](../prototypes/pagination/android/)
(plain Views, no Compose: a WebView loading the same reader files through
`WebViewAssetLoader`, no INTERNET permission, and a transparent overlay
that takes every touch and turns pages via `evaluateJavascript`). Run with
`prototypes/pagination/android/run_device_test.sh`.

Device: dedicated AVD `bibliotheke_wp0` (Pixel 7 profile, 1080 × 2400,
420 dpi, Android 16, system WebView 134.0.6998.135), rendering on the
host's desktop-class GPU through the emulator's GLES translator, 60 Hz
display, headless. **Caveat:** this is faster than the mid-range 2021 phone
of N2; WP4 should repeat the frame measurement on a real device.

**Locators** ([wp0/pagination-android.json](wp0/pagination-android.json)):
the same tests as 4.3 pass on all pages of all five parts. The CSS width is
fractional here (411.43 px); the snapped page width is 411.421875 px, and no
page drifted (Käthe: 580 pages at 18 px, 1177 at 26 px). Layout and relayout
of the 600 k-character Käthe take about 0.2 s (185–232 ms over two runs);
the other parts 2–36 ms.

**Page turns** ([wp0/pagination-android-gfxinfo.txt](wp0/pagination-android-gfxinfo.txt),
[wp0/pagination-android-framestats.txt](wp0/pagination-android-framestats.txt)):
30 real swipes (`adb shell input swipe`) through the overlay on Sult.

| measure | value |
|---|---|
| frames rendered | 540 (18 per turn: 260 ms transition) |
| frame time 50th / 90th / 95th / 99th percentile | 16 / 16 / 16 / 17 ms |
| missed vsync | 0 |
| largest gap between frames within a turn | 16.7 ms (no skipped vsync) |
| "janky" (new metric) | 30 = the first frame of each turn, 0.1–0.4 ms past its deadline |
| "janky" (legacy metric) | 0 |

**Acceptance "turns pages at 60 fps on an emulator": met.** Every turn
renders a frame on every vsync. The one late first frame per turn finishes
just after its deadline without delaying the next frame. It comes from
starting the transition through `evaluateJavascript` (a main-thread style
change); WP4 can avoid it by starting the animation natively (the WebView's
`translationX`, as PLAN.md 8.1 suggests) or with the Web Animations API.

**Rotation** ([wp0/pagination-android-rotation.txt](wp0/pagination-android-rotation.txt)):
the first version re-read the locator on every resize, and the position
drifted: portrait page 30 → landscape → portrait page 29, because each new
page starts before the stored position. With a reading *anchor* (below),
portrait → landscape (page 42/79, anchor visible) → portrait returned to
page 35/64 with its first character exactly at the anchor (39729).

### 4.6 Lessons for WP4

- **Keep a reading anchor.** The stored locator changes only on navigation
  (turns, jumps, TOC, bookmarks). Relayouts (font, margins, rotation, window
  resize) restore it without re-reading it. Re-reading after each relayout
  makes the position creep backwards.
- **Pages without locator text.** Locator text excludes `.wsr-footnotes`,
  so a page holding only the footnote section (Käthe's last page) has no
  locator of its own: `firstOffsetOnPage` returns the end of the text. Two
  such pages in a row would share a locator, and a full-page image would
  share the next text page's. WP4 needs a secondary position for these
  pages (e.g. footnote index, or "page n after offset x").
- **Taps must be forwarded.** With the overlay taking every touch, links,
  footnote references (8.4) and page marks (8.2) never get clicks. On a tap,
  Kotlin must ask JS what is under the point (`document.elementFromPoint`)
  and act on `.wsr-fnref`, `.wsr-page` or links before falling back to the
  tap zones.
- **Measure positions relative to the content box.** Character rects and
  the content box carry the same, possibly mid-animation, transform, so
  `rect.left − contentBox.left` is valid even during a turn.
- Large single-page parts relayout in about 0.2 s on the emulator; splitting
  them (section 2.4) keeps font changes snappy on slower phones.
- Compose was not used, to avoid an uncached dependency download in the
  spike; the production reader wraps the same WebView in `AndroidView`, with
  the overlay as a `pointerInput` modifier. The gesture logic is identical.

## 5. Quality signals: Index progress values (VERIFY in PLAN.md 3.4)

Script: `python3 pipeline/wp0/index_progress.py` →
[wp0/index-progress.json](wp0/index-progress.json). Source: each wiki's
`MediaWiki:Proofreadpage_index_data_config` (on several wikis a redirect to
`….json`), which defines the Index page fields and their allowed values.

| wiki | field | values → catalog quality |
|---|---|---|
| en | `Progress` | **T** done, all validated → validated; **V** to be validated → proofread; **C** to be proofread → unproofread; MS ready for match & split, OCR needs text layer, L faulty source file, X pagelist needed → unproofread |
| mul | `Progress` | as en; X here means "incomplete source (extracts) or compilation" |
| fr | `Avancement` | T terminé → validated; V à valider → proofread; C à corriger → unproofread; MS, OCR, L, D (duplicate file) → unproofread |
| es | `Progreso` | T terminado → validated; V por validar → proofread; C, P por corregir → unproofread; L dañado → unproofread; E extracto → mark incomplete |
| da | `Status` | F færdig → validated; V mangler validering → proofread; K mangler korrekturlæsning → unproofread |
| it | `Qualità` | free string, default `25%` (SAL 25/50/75/100 %) |
| no | `Fremgang` | free string, no enumerated values |
| sv | – | no progress field |
| de, fi, is | – | no index data config page |

- **PLAN.md 3.4 had the en codes wrong**: `V` is "to be validated" (i.e.
  proofread, not validated), `T` is "done (all validated)", and `X` means
  "pagelist needed", not "incomplete". Mapping `V` to validated would
  overstate quality.
- Only 5 of the 11 wikis have an enumerated progress field. A source that
  works everywhere is the per-page quality: each `Page:` page's wikitext
  starts with `<pagequality level="0–4" …>` (same 5 levels on all wikis,
  section 1), and the `pagenum` markers carry it too (section 2.3).
  Recommended for WP1: work quality = the lowest level among the `Page:`
  pages a work transcludes (from the pages-articles dump), with the
  enumerated Index progress as a cheaper shortcut where present.
- no's Index config also has **`Malform`** (målform) with values
  `NB` Bokmål/riksmål, `NN` Nynorsk/landsmål, `ND` Norwegian dialect, `NO`
  mixed, plus `Sprak` (original language, ISO 639). For scan-backed
  Norwegian works (92 % of the no sample, section 3.3) this gives the
  nb/nn sub-facet (PLAN.md 3.1) more reliably than Wikidata P407, which
  labels 19th-century Dano-Norwegian as Bokmål.

## 6. Consequences for later work packages

### WP1 (catalog pipeline)

1. Inputs: add the **`pages-articles` dump** (wikitext). Without it there
   is no header/Index metadata, and Wikidata covers only 13 % of no works
   (3.4).
2. Candidate works (5.2 step 1), extra exclusions:
   - root pages whose Wikidata item is a human (`P31 = Q5`) and, on de,
     `Kategorie:Autoren`; de and fi keep author pages in mainspace (1);
   - versions pages by **template/category**, not only the `disambiguation`
     page prop: en `{{Versions}}` / `Category:Versions pages` has no such
     prop (2.10);
   - de lexicon pseudo-namespaces by title prefix (`RE:`, `MKL1888:`, `ADB:`,
     `BLKÖ:`, about 196,000 pages) via a per-wiki `exclude_title_prefixes`;
   - orphan subpage trees: use the top-most existing ancestor (2.4).
3. Wiki list: skip closed wikis; take `wikisource.org` from sitematrix
   `specials` (`code: "sources"`) as `mul` (1).
4. Namespace ids per wiki from siteinfo + `meta=proofreadinfo` (1).
5. Metadata: authors from the `Forfatter:`/`Author:` links in the header,
   not by splitting the display string (3.3); language for `no` from Index
   `Malform`, then P407 (5); quality from page levels / Index progress with
   the corrected mapping (5).
6. Collections that are TOC pages linking separate root pages (de, and
   also no, 2.4): the catalog lists each linked text as its own work, which
   matches how these wikis present them; grouping them under the collection
   is a later improvement, not needed for WP1. The coverage report should
   count such pages per wiki. (de also has no microformats and no
   `ws-summary`.)

### WP3 (content and normaliser)

1. REST titles fully percent-encoded (`/` → `%2F`); use ETag/304 (2.1).
2. Page markers: `span.pagenum` (all ProofreadPage shapes) and de
   `span.pr1pn`; number from `data-page-number` or `id`, scan page from
   `data-page-name` or `title` (2.3).
3. Rule order: convert page markers and **extract line numbers before
   removing `.ws-noexport`** (de `zeilennummer` lives inside it) (2.7).
4. Footnotes from `sup.mw-ref`, fragment after `#`; keep each references
   group (2.8).
5. Add `dl`, `dd`, `dt` to the element whitelist (2.6).
6. Drop licence boxes by template name (`licence_templates`) (2.5).
7. Images from `thumb.wikimedia.org`; strip `utm_*` (2.9).
8. Part discovery: `.ws-summary` first (de-duplicated, nested), skip
   `Texte entier`-style subpages, recurse into nested TOC pages (2.4).
9. **Split oversized single-page works** into synthetic parts (at
   headings, else at page markers) above a threshold, e.g. 100 k characters
   (2.4, 4.3, 4.5).
10. Verse: stanzas from blank lines only; re-sync generated line numbers to
    source numbers; newline text nodes between blocks for locator text;
    drop Parsoid `lang` attributes (4.4).

### WP4 (reader)

Corrected column CSS and 1/64 px page width (4.1); reading anchor, pages
without locator text, tap forwarding, starting turns natively (4.6).

## 7. Changes made to PLAN.md

All three **VERIFY** items are resolved in place (3.3 page markers, 3.4 Index
progress, 3.5 REST endpoint), as is the "verify via siteinfo" note in the
5.5 config example.
Other passages are corrected where the plan would otherwise mislead
implementation. Every change is marked "(WP0 …)" and cites this file as
F§n; decisions D1–D9 are unchanged.

- Header note: VERIFY items resolved, how WP0 changes are marked.
- 0: `thumb.wikimedia.org` added to the hosts that may be called.
- R1: 83 open wikis.
- 3.1: sitematrix filter misses `wikisource.org` (`specials`, `sources`).
- 3.2: Author namespace numbers; de/fi without one (exclude `P31 = Q5`);
  en versions pages without the `disambiguation` prop; de lexicon prefixes;
  orphan subpage trees.
- 3.3: page-marker shapes per wiki; `.ws-summary` coverage.
- 3.4: Index progress per wiki with corrected en codes; per-page quality as
  the universal source; no `Malform` for nb/nn.
- 3.5: REST endpoint details; thumbnails from `thumb.wikimedia.org`.
- 5.1: pages-articles dump added as an input.
- 5.5: namespace comment; new keys `exclude_title_prefixes`,
  `exclude_subpage_titles`, `licence_templates`; fr/de selectors.
- 7.1: step 6, split oversized parts.
- 7.2: `dl dd dt` allowed; rule 0 (markers and line numbers before removing
  `.ws-noexport`); line-number selectors; footnote selector.
- 8.1: column CSS corrected; reading anchor and tap forwarding.

Not changed, for the owner to decide: STATUS.md location (section 0 says the
repo root, sections 6 and 12 say `docs/`; kept at the root), and whether
collections that link separate root pages (de, no) should be grouped under
their TOC page (section 6, WP1 item 6).
