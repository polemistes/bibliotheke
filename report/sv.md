# sv.wikisource: catalog coverage report

Generated 2026-11-01 by `python -I -m wscatalog.catalog sv` from the dump of 20261001 (PLAN.md 5.2 steps 3–8; config `pipeline/config/sv.yaml`). Build took 2 s on the cached extract.

## Summary

|  | works | sources |
|---|---|---|
| works in the catalog | 6,761 |  |
| with an author | 74.8 % | header 3,505, none 1,701, index 1,509, body 35, wikidata 11 |
| with a year | 47.7 % | none 3,533, index 2,376, category 843, wikidata-edition 9 |
| with a genre | 75.0 % | category 5,062, none 1,688, wikidata 10, type 1 |
| with a quality tier (not unknown) | 35.8 % | unknown 4,339, proofread 1,793, validated 536, unproofread 93 |
| with a Wikidata item | 54.3 % |  |
| with a description | 0.3 % |  |
| with a cover image | 32.7 % |  |
| language other than the wiki's | 0.2 % | wiki 6,746, wikidata 15 |

## Default view (PLAN.md 5.4)

Validated or proofread, complete, not `reference`: **2,281 works** (7,281 texts, i.e. searchable parts; single-page works are found as works); with unknown works: 6,609 works (8,039 texts). `show_unknown_by_default` is `false`, so the wiki picker shows 2,281.

| tier | works | complete |
|---|---|---|
| validated | 536 | 533 |
| proofread | 1,793 | 1,748 |
| unproofread | 93 | 76 |
| unknown | 4,339 | 4,328 |

Incomplete by reason: missing-parts 45, missing-pages 31.

## Types, structures, genres, languages

- type: poem 4,314, other 1,919, collection 367, book 158, document 3
- type source: genre 4,314, default 1,919, structure 525, wikidata 3
- structure: single 6,236, subpages 458, virtual 52, linked 13, mixed 2
- genre: song-hymn 2,329, poetry 2,276, folklore 192, history 156, novel 143, short-story 74, memoir-biography 55, travel 53, periodical 52, legal-government 2, speech 1
- language: sv 6,749, de 3, en 2, fr 1, la 1, el 1, cs 1, ar 1, uk 1, ang 1
- century: 16. 6, 17. 196, 18. 326, 19. 1,858, 20. 834, 21. 8

## Catalog file

`sv.sqlite.gz`: 2.21 MB gzipped, 7.0 MB raw, sha256 `305c639d7c6c3a58c7675bc13e5a9d19ea6f29a8cf9231fc2645ede50ea122af`. Rows: works 6,761, parts 13,991, parts_fts 9,247, aliases 3,957, authors 1,089, excluded 332.

## Exclusions

Step 1 (pages): disambiguation 147, duplicate 1.

Step 5 (works): no-text 155, under-300-bytes 29.

## Mapping work

Most common categories on root pages of works without a genre (candidates for `metadata.genre_categories`):

Linköpings Wecko-tidningar 105, Nordstjernan 95, August Blanche 88, Freja 85, 1700-talets verk 77, Koral1697 73, 1890-talets verk 55, Samlade dikter af G. L. Sommelius 53, 1690-talets verk 50, TV-program 46, Riksarkivet i Finland 45, Vallmoknoppar, plockade på Steppen 39, Gustaf Lorentz Sommelius 32, Henrik Bernhard Palmær 31, Lagar och författningar angående Riksarkivet i Finland 31, 1910-talets verk 29, 1870-talets verk 29, 1880-talets verk 29, Lagfarenhets-Bibliothek del 5 avd 1 29, Eldbränder och gnistor 29, Humoresker 29, Prosa 28, Riksregistraturet 28, Riksdagstrycket 28, Stockholm 28, Talismanen 28, Svenska regeringsförklaringar 27, Zätterbloms och annat hyggligt folk 26, Sköldebrev 24, 1900-talets verk 23, Facklitteratur 23, Fögderier 23, Andliga sångböcker 22, Översättningar 22, Post- och Inrikes Tidningar 21, 1860-talets verk 21, 1940-talets verk 21, Teater 21, Brunnsorter 20, 1920-talets verk 18

Most common unmapped Wikidata values on the works' items (candidates for `wikidata.genres` / `wikidata.types` in default.yaml):

P31 Q1691434 “?” 4, P31 Q1414472 “?” 4, P31 Q79700418 “?” 4, P31 Q47461344 “written work” 4, P136 Q265147 “?” 2, P136 Q12300621 “?” 2, P31 Q3406098 “?” 2, P31 Q1898047 “?” 2, P31 Q602446 “lyrics” 2, P136 Q124455603 “written prayer” 2, P31 Q3627146 “Christian prayer” 2, P31 Q7239036 “prayer in the New Testament” 2, P31 Q1385610 “excerpt” 2, P31 Q1868072 “logion” 2, P31 Q116741026 “?” 2, P31 Q727002 “?” 2, P31 Q3497659 “?” 2, P31 Q1464916 “?” 2, P136 Q605076 “?” 2, P31 Q605076 “?” 2, P31 Q7725634 “literary work” 2, P136 Q1601739 “?” 2

