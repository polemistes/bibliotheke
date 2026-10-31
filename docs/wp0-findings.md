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
  `no` wiki should be `nb` unless P407 says otherwise (see section 4).
- `mul` reports `lang: en` (interface language only). Work language there
  must come from Wikidata P407 or categories, never from siteinfo.
- ProofreadPage quality levels are the same five ids on all wikis (0 without
  text, 1 not proofread, 2 problematic, 3 proofread, 4 validated); only the
  category names are localised (no: Uten tekst, Rå, Ufullstendig,
  Korrekturlest, Validert).
