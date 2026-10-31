# WP0 findings

Results of the WP0 spike (PLAN.md section 9). Each section records what was
checked against the live services on 2026-10-31, the evidence, and what it
changes in the plan. Raw outputs live in [docs/wp0/](wp0/); the scripts that
produced them are in [pipeline/wp0/](../pipeline/wp0/).

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
  page: 104}` holds only for no, en (index/page) and da. Every per-wiki config
  must take them from siteinfo/proofreadinfo, never from defaults.
- **de and fi have no Author namespace.** Author pages are ordinary
  main-namespace root pages: on de they carry `Kategorie:Autoren`; on fi only
  a per-author category (e.g. `Luokka:Aleksis Kivi`) and no author template.
  The candidate-works filter (5.2 step 1) therefore needs an extra rule:
  exclude a root page whose Wikidata item is a human (`P31 = Q5`), plus the
  per-wiki author category where one exists (de: `Kategorie:Autoren`).
- no.wikisource reports `lang: nb`. The catalog's default language for the
  `no` wiki should be `nb` unless P407 says otherwise (see section 3).
- `mul` reports `lang: en` (interface language only). Work language there
  must come from Wikidata P407 or categories, never from siteinfo.
- ProofreadPage quality levels are the same five ids on all wikis (0 without
  text, 1 not proofread, 2 problematic, 3 proofread, 4 validated); only the
  category names are localised (no: Uten tekst, Rå, Ufullstendig,
  Korrekturlest, Validert).

## 2. Parsoid HTML: endpoint and markup shape

Tools: `python3 pipeline/wp0/parsoid.py analyse|save …`,
`python3 pipeline/wp0/search.py …`. Fixtures (raw REST HTML plus
`source.json` with title, revision, fetch date, licence note and a shape
summary) are in [app/src/test/fixtures/](../app/src/test/fixtures/).

### 2.1 Endpoint (VERIFY in PLAN.md 3.5: confirmed, with details)

- `GET https://{wiki}/w/rest.php/v1/page/{title}/html` returns Parsoid HTML,
  `Content-Type: text/html; profile="https://www.mediawiki.org/wiki/Specs/HTML/2.8.0"`,
  on all four wikis tested (no, en, fr, de). The microformat classes are
  intact.
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
  attributes are 4–29 % of the HTML characters in the 16 fixtures; the
  normaliser drops them.

### 2.2 Fixtures saved (16 pages; plan asked for 3 per wiki)

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
  parameter in the span's `data-mw` (present only on the first span of each
  transclusion group on no). Useful later; not needed for R8.
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

### 2.4 Table of contents / `.ws-summary` (VERIFY 3.3, 7.1)

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
  them; see section 5.
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
- COinS `span.Z3988` carries title/author/publisher/place/date in its `title`
  attribute on no/en/fr; a secondary metadata source for the pipeline.
- Licence boxes are **not** `ws-noexport`: on no it is a bare `<table>` at the
  end of the page (template `PD-old`). The normaliser needs per-wiki
  `licence_templates` to drop them (match on the template name in `data-mw`),
  and the app shows the licence itself (R11).
- en adds `.similar.ws-noexport` ("For other versions…") and an
  authority-control `.navbox`; both are removed by existing rules.

### 2.6 Verse

- `<poem>` renders as `div.poem[typeof~="mw:Extension/poem"] > p` with
  `<br>` between lines and a `<p>` per stanza (as PLAN.md 8.3 assumed). fr
  adds class `verse` (`<poem class=verse>`). The `.poem` can also carry
  `typeof="mw:Extension/poem mw:Transclusion"`.
- de inserts line-number blocks **between** the stanza `<p>`s inside one
  `.poem`, so a "line" is not always a direct `p` child; the verse converter
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
  between stanza paragraphs. Selector: `span.zeilennummer`. **It sits inside
  `.ws-noexport`, so the normaliser must extract line numbers before it
  removes `.ws-noexport`** (rule order in 7.2).
- no, en: no source line numbers found in the samples.

### 2.8 Footnotes (VERIFY 7.2 selector: needs a change)

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
  `upload.wikimedia.org`. `srcset` gives 1.5x/2x variants on the same host.
  Add `thumb.wikimedia.org` to the allowed hosts in PLAN.md section 0 and
  N6, prefix `https:`, and strip the `utm_*` parameters before downloading.
- `resource="./File:…"` (localised: `Fil:`, `Datei:`) names the file, so the
  normaliser can build a specific width itself via the thumb URL pattern or
  `prop=imageinfo&iiurlwidth=800`.
- Icons (PD-icon 40 px, Wikimedia/Wikidata logos 20 px) appear in licence and
  sister-project boxes; the "skip icons < 32 px" rule drops them.
