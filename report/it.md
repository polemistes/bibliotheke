# it.wikisource: catalog coverage report

Generated 2026-11-01 by `python -I -m wscatalog.catalog it` from the dump of 20261001 (PLAN.md 5.2 steps 3–8; config `pipeline/config/it.yaml`). Build took 7 s on the cached extract.

## Summary

|  | works | sources |
|---|---|---|
| works in the catalog | 17,604 |  |
| with an author | 87.2 % | header 14,885, none 2,245, index 384, wikidata 85, body 5 |
| with a year | 91.6 % | header 10,322, index 5,736, none 1,478, wikidata-edition 68 |
| with a genre | 66.3 % | header 11,442, none 5,941, category 174, wikidata 46, template 1 |
| with a quality tier (not unknown) | 100.0 % | validated 7,763, proofread 6,946, unproofread 2,895 |
| with a Wikidata item | 30.7 % |  |
| with a description | 0.6 % |  |
| with a cover image | 78.7 % |  |
| language other than the wiki's | 0.4 % | wiki 17,521, wikidata 81, index 2 |

## Default view (PLAN.md 5.4)

Validated or proofread, complete, not `reference`: **14,582 works** (29,224 texts, i.e. searchable parts; single-page works are found as works); with unknown works: 14,582 works (29,224 texts). `show_unknown_by_default` is `false`, so the wiki picker shows 14,582.

| tier | works | complete |
|---|---|---|
| validated | 7,763 | 7,760 |
| proofread | 6,946 | 6,823 |
| unproofread | 2,895 | 2,743 |
| unknown | 0 | 0 |

Incomplete by reason: missing-pages 268, missing-parts 10.

## Types, structures, genres, languages

- type: poem 7,802, other 4,920, collection 1,967, book 1,778, document 958, article 135, play 42, reference 2
- type source: genre 8,308, default 4,920, structure 3,747, template 615, wikidata 14
- structure: single 13,805, subpages 3,792, linked 5, mixed 1, virtual 1
- genre: poetry 7,608, legal-government 899, short-story 587, song-hymn 583, history 342, drama 338, novel 334, essay 316, memoir-biography 156, religious 150, letters 146, speech 108, philosophy 85, folklore 55, travel 43, periodical 28, children 7
- language: it 17,533, en 25, fr 10, la 9, de 8, grc 7, ru 7, el 1, da 1, cs 1, ar 1, es 1
- century: 1. 1, 6. 2, 7. 2, 8. 1, 10. 3, 11. 3, 12. 7, 13. 11, 14. 42, 15. 36, 16. 396, 17. 244, 18. 884, 19. 8,323, 20. 5,559, 21. 610

## Catalog file

`it.sqlite.gz`: 8.49 MB gzipped, 31.6 MB raw, sha256 `3a7f6da28ade5c1b4fe2dd918478d5710baa756d2a1bd73a5980f5274e78594d`. Rows: works 17,604, parts 88,176, parts_fts 35,245, aliases 8,795, authors 3,286, excluded 480.

Works without catalog parts (over `parts_cap` 2,000, or `reference`): 2: `Pensieri di varia filosofia e di bella letteratura` (4,588), `Donne e Uomini della Resistenza` (3,058).

## Exclusions

Step 1 (pages): disambiguation 445, duplicate 18.

Step 5 (works): no-text-pages 13, no-text 4.

## Mapping work

Most common categories on root pages of works without a genre (candidates for `metadata.genre_categories`):

Archivio Meraviglioso 158, Testi in ladino Val Badia 155, Testi in ladino Val Gardena 69, Testi in ladino Val di Fassa 59, Testi in ladino ampezzano 25, Canone delle opere della letteratura italiana 20, Testi illustrati 18, Numismatica antica 18, Testi in ladino Fodom 18, Testi in ladino 17, Biblioteca digitale pratese 14, Milano 13, Liguria 11, Fonte cartacea presente 10, Lombardia 10, Provincia di Bergamo 10, Roma 10, Trentino-Alto Adige 9, Scritture di donne 9, Documenti minori Italia 8, Genova 8, FOSS 7, Campania 7, Venezia 7, Scientia 6, Template per i testi 6, Scansioni provenienti da Internet Archive 6, Veneto 5, Fano 5, Mantova 5, Testi in latino 5, Elezioni politiche 5, Argomenti informatici 4, Software Libero 4, Licenze 4, Dialoghi 4, Regno delle Due Sicilie 4, Castiglione delle Stiviere 4, Bibliografie 4, Testi di politica 3

Most common unmapped Wikidata values on the works' items (candidates for `wikidata.genres` / `wikidata.types` in default.yaml):

P31 Q7725634 “literary work” 109, P31 Q47461344 “written work” 21, P31 Q11122 “?” 12, P136 Q21802675 “adventure fiction” 12, P136 Q5891 “?” 10, P31 Q7755 “constitution” 7, P31 Q116741026 “?” 6, P136 Q132311 “fantasy” 6, P31 Q3331189 “version, edition or translation” 6, P31 Q3497659 “?” 4, P31 Q6934728 “?” 4, P136 Q1072723 “travel literature” 4, P136 Q12617225 “religious literature” 4, P136 Q185363 “?” 4, P136 Q5774663 “?” 4, P136 Q3940786 “scientific romance” 4, P136 Q124455603 “written prayer” 2, P31 Q3627146 “Christian prayer” 2, P31 Q7239036 “prayer in the New Testament” 2, P31 Q1385610 “excerpt” 2, P31 Q1868072 “logion” 2, P31 Q3406098 “?” 2, P31 Q1898047 “?” 2, P31 Q602446 “lyrics” 2, P31 Q17442446 “?” 2, P31 Q14204246 “?” 2, P31 Q15633587 “?” 2, P31 Q4663903 “?” 2, P136 Q17991521 “tale” 2, P31 Q104840560 “?” 2, P31 Q1691434 “?” 2, P136 Q31074013 “?” 2, P136 Q265147 “?” 2, P136 Q1864008 “?” 2, P31 Q1194894 “?” 2, P31 Q62662439 “?” 2, P31 Q7048853 “?” 2, P31 Q64728694 “?” 2, P31 Q727002 “?” 2, P136 Q3025978 “?” 2

