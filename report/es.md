# es.wikisource: catalog coverage report

Generated 2026-11-01 by `python -I -m wscatalog.catalog es` from the dump of 20261001 (PLAN.md 5.2 steps 3–8; config `pipeline/config/es.yaml`). Build took 4 s on the cached extract.

## Summary

|  | works | sources |
|---|---|---|
| works in the catalog | 17,792 |  |
| with an author | 44.6 % | none 9,863, header 7,474, index 209, wikidata 159, body 87 |
| with a year | 27.5 % | none 12,898, header 1,831, category 1,783, index 646, wikidata-edition 632, wikidata-work 2 |
| with a genre | 75.0 % | category 12,273, none 4,444, template 1,000, wikidata 74, type 1 |
| with a quality tier (not unknown) | 16.0 % | unknown 14,945, proofread 1,878, validated 564, unproofread 405 |
| with a Wikidata item | 70.1 % |  |
| with a description | 4.0 % |  |
| with a cover image | 14.1 % |  |
| language other than the wiki's | 0.9 % | wiki 17,624, wikidata 168 |

## Default view (PLAN.md 5.4)

Validated or proofread, complete, not `reference`: **2,282 works** (6,127 texts, i.e. searchable parts; single-page works are found as works); with unknown works: 17,129 works (13,588 texts). `show_unknown_by_default` is `false`, so the wiki picker shows 2,282.

| tier | works | complete |
|---|---|---|
| validated | 564 | 560 |
| proofread | 1,878 | 1,722 |
| unproofread | 405 | 333 |
| unknown | 14,945 | 14,847 |

Incomplete by reason: missing-parts 240, missing-pages 88, index-progress 2.

## Types, structures, genres, languages

- type: other 8,644, poem 4,355, document 2,626, book 950, collection 731, article 424, play 60, reference 1, periodical-issue 1
- type source: default 8,644, genre 5,392, template 2,043, structure 1,682, wikidata 31
- structure: single 16,093, subpages 1,322, linked 249, mixed 79, virtual 49
- genre: poetry 4,137, reference 1,954, legal-government 1,849, history 1,212, short-story 1,021, speech 791, essay 492, novel 452, drama 356, song-hymn 353, folklore 338, memoir-biography 300, periodical 285, letters 129, religious 4, philosophy 1
- language: es 17,635, en 60, zh 25, fr 22, la 13, grc 9, ru 7, cs 5, de 5, it 5, ar 1, ja 1, ro 1, el 1, ca 1, is 1
- century: 2. 1, 12. 1, 13. 2, 14. 1, 15. 4, 16. 44, 17. 301, 18. 158, 19. 2,046, 20. 2,065, 21. 271

## Catalog file

`es.sqlite.gz`: 5.26 MB gzipped, 17.6 MB raw, sha256 `53602aaf3a58407c52ba2e499b5255603f14a03bc0e4737cfac8f27058b6e9d2`. Rows: works 17,792, parts 38,257, parts_fts 17,903, aliases 14,459, authors 1,551, excluded 3,138.

Works without catalog parts (over `parts_cap` 2,000, or `reference`): 1: `Sesiones de los Cuerpos Lejislativos de la República de Chile (1810-1845)` (3,595).

## Exclusions

Step 1 (pages): disambiguation 734, versions 509, duplicate 1.

Step 5 (works): no-text 1,816, under-300-bytes 51, no-text-pages 27.

## Mapping work

Most common categories on root pages of works without a genre (candidates for `metadata.genre_categories`):

Atentado a la AMIA 200, DH-C 187, Obras por capítulos 184, Almagesto 156, Literatura española (Títulos) 127, El Museo Universal 1869 101, ES-L 94, Siglo XIX 60, ES-E 59, Clasicismo 51, DH-D 51, ES-C 51, DH-P 49, Obras de Eusebio Blasco 48, Obras del Cancionero de Uppsala 47, ES-A 45, Reflexiones de Fidel 44, DH-A 42, Documentos de Práxedis G. Guerrero 42, Literatura argentina (Títulos) 39, ES-D 37, D1936 37, ES-S 34, Guerra del Pacífico 34, Documentos 34, Literatura uruguaya (Títulos) 34, Cartas carlistas 33, Cancionero de Elvas 33, ES-P 32, Manifiestos carlistas 32, Autos Sacramentales de Pedro Calderón de la Barca 32, Revistas de España 30, Documentos del Nacionalcatolicismo 30, Literatura rusa (Títulos) 30, Sonetos de Francisco de Quevedo 28, Documentos de Karl Marx 28, Traducciones de Wikisource 28, Testimonios sobre violación de derechos humanos en Argentina 28, DH-M 26, Periódicos de España 26

Most common unmapped Wikidata values on the works' items (candidates for `wikidata.genres` / `wikidata.types` in default.yaml):

P31 Q877358 “United Nations Security Council resolution” 1,006, P31 Q7725634 “literary work” 181, P31 Q47461344 “written work” 39, P31 Q3331189 “version, edition or translation” 39, P31 Q1330251 “United Nations General Assembly resolution” 22, P31 Q1691434 “?” 18, P136 Q21802675 “adventure fiction” 18, P136 Q1196408 “historical fiction” 14, P31 Q7755 “constitution” 11, P31 Q6934728 “?” 10, P136 Q1347298 “?” 10, P136 Q384515 “treatise” 8, P31 Q1414472 “?” 8, P31 Q79700418 “?” 8, P136 Q24925 “science fiction” 8, P136 Q19765983 “romantic fiction” 7, P136 Q223945 “?” 7, P136 Q1152184 “?” 7, P31 Q49848 “document” 6, P31 Q11122 “?” 6, P136 Q5937792 “crime fiction” 6, P136 Q16575965 “horror fiction” 6, P136 Q5151421 “?” 6, P136 Q179805 “?” 4, P31 Q116741026 “?” 4, P31 Q3497659 “?” 4, P136 Q12617225 “religious literature” 4, P31 Q179461 “religious text” 4, P136 Q45996 “?” 4, P136 Q645717 “?” 4, P31 Q7979513 “?” 4, P31 Q105774620 “Public General Act of the Parliament of the United Kingdom” 4, P136 Q3025978 “?” 4, P31 Q2891173 “?” 4, P31 Q625298 “?” 4, P136 Q44563 “?” 4, P136 Q27801 “?” 4, P136 Q1097273 “?” 4, P136 Q185363 “?” 4, P136 Q2994242 “?” 4

