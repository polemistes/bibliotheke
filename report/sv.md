# sv.wikisource: catalog coverage report

Generated 2026-11-01 by `python -I -m wscatalog.catalog sv` from the dump of 20261001 (PLAN.md 5.2 steps 3–8; config `pipeline/config/sv.yaml`). Build took 2 s on the cached extract.

## Summary

|  | works | sources |
|---|---|---|
| works in the catalog | 6,580 |  |
| with an author | 74.1 % | header 3,323, none 1,702, index 1,509, body 35, wikidata 11 |
| with a year | 48.8 % | none 3,370, index 2,376, category 825, wikidata-edition 9 |
| with a genre | 74.3 % | category 4,880, none 1,689, wikidata 10, type 1 |
| with a quality tier (not unknown) | 36.8 % | unknown 4,158, proofread 1,793, validated 536, unproofread 93 |
| with a Wikidata item | 54.7 % |  |
| with a description | 0.3 % |  |
| with a cover image | 33.6 % |  |
| language other than the wiki's | 0.2 % | wiki 6,565, wikidata 15 |

## Default view (PLAN.md 5.4)

Validated or proofread, complete, not `reference` or `document`: **2,280 works** (7,281 texts, i.e. searchable parts; single-page works are found as works); with unknown works: 6,420 works (8,182 texts). `show_unknown_by_default` is `true` (from the config), so the wiki picker shows 6,420. Hidden by type from that view: reference 0, document 3.

| tier | works | complete |
|---|---|---|
| validated | 536 | 533 |
| proofread | 1,793 | 1,748 |
| unproofread | 93 | 76 |
| unknown | 4,158 | 4,142 |

Incomplete by reason: missing-parts 50, missing-pages 31.

## Types, structures, genres, languages

- type: poem 4,132, other 1,907, collection 377, book 161, document 3
- type source: genre 4,132, default 1,907, structure 538, wikidata 3
- structure: single 6,042, subpages 458, virtual 52, linked 26, mixed 2
- genre: song-hymn 2,288, poetry 2,132, folklore 192, history 156, novel 143, short-story 70, memoir-biography 55, periodical 52, travel 46, legal-government 2, speech 1
- language: sv 6,568, de 3, en 2, fr 1, la 1, el 1, cs 1, ar 1, uk 1, ang 1
- century: 16. 6, 17. 196, 18. 326, 19. 1,839, 20. 835, 21. 8

## Catalog file

`sv.sqlite.gz`: 2.16 MB gzipped, 6.9 MB raw, sha256 `2022c794c147ab0fcc573b5117e8e1520bb0393fe270d99ecadf4b52f518ace9`. Rows: works 6,580, parts 14,204, parts_fts 9,439, aliases 3,958, authors 1,089, excluded 310.

## Exclusions

Step 1 (pages): disambiguation 147, duplicate 1.

Step 5 (works): no-text 133, under-300-bytes 29.

## Mapping work

Most common categories on root pages of works without a genre (candidates for `metadata.genre_categories`):

Linköpings Wecko-tidningar 105, Nordstjernan 95, August Blanche 88, Freja 85, 1700-talets verk 77, Koral1697 73, 1890-talets verk 55, Samlade dikter af G. L. Sommelius 53, 1690-talets verk 50, TV-program 46, Riksarkivet i Finland 45, Vallmoknoppar, plockade på Steppen 39, Gustaf Lorentz Sommelius 32, Henrik Bernhard Palmær 31, Lagar och författningar angående Riksarkivet i Finland 31, 1910-talets verk 30, 1870-talets verk 29, 1880-talets verk 29, Lagfarenhets-Bibliothek del 5 avd 1 29, Eldbränder och gnistor 29, Humoresker 29, Riksregistraturet 28, Riksdagstrycket 28, Stockholm 28, Talismanen 28, Svenska regeringsförklaringar 27, Zätterbloms och annat hyggligt folk 26, 1900-talets verk 24, Sköldebrev 24, Andliga sångböcker 23, Prosa 23, Facklitteratur 23, Fögderier 23, Översättningar 22, Novellsamlingar 21, Post- och Inrikes Tidningar 21, 1860-talets verk 21, 1940-talets verk 21, Teater 21, Brunnsorter 20

Most common unmapped Wikidata values on the works' items (candidates for `wikidata.genres` / `wikidata.types` in default.yaml):

P31 Q1691434 “?” 4, P31 Q1414472 “?” 4, P31 Q79700418 “?” 4, P31 Q47461344 “written work” 4, P136 Q265147 “?” 2, P136 Q12300621 “?” 2, P31 Q3406098 “?” 2, P31 Q1898047 “?” 2, P31 Q602446 “lyrics” 2, P136 Q124455603 “written prayer” 2, P31 Q3627146 “Christian prayer” 2, P31 Q7239036 “prayer in the New Testament” 2, P31 Q1385610 “excerpt” 2, P31 Q1868072 “logion” 2, P31 Q116741026 “?” 2, P31 Q727002 “?” 2, P31 Q3497659 “?” 2, P31 Q1464916 “?” 2, P136 Q605076 “?” 2, P31 Q605076 “?” 2, P31 Q7725634 “literary work” 2, P136 Q1601739 “?” 2

