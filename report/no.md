# no.wikisource: catalog coverage report

Generated 2026-11-01 by `python -I -m wscatalog.catalog no` from the dump of 20261001 (PLAN.md 5.2 steps 3–8; config `pipeline/config/no.yaml`). Build took 1 s on the cached extract.

## Summary

|  | works | sources |
|---|---|---|
| works in the catalog | 1,069 |  |
| with an author | 81.0 % | index 372, wikidata 341, none 203, header 118, body 34, category 1 |
| with a year | 85.4 % | index 481, wikidata-edition 349, none 156, category 80, wikidata-work 2, header 1 |
| with a genre | 60.2 % | category 565, none 425, wikidata 41, template 32, type 6 |
| with a quality tier (not unknown) | 79.1 % | proofread 466, unproofread 307, unknown 223, validated 73 |
| with a Wikidata item | 35.7 % |  |
| with a description | 25.1 % |  |
| with a cover image | 76.0 % |  |
| language other than the wiki's | 69.2 % | index 486, wikidata 355, wiki 228 |

## Default view (PLAN.md 5.4)

Validated or proofread, complete, not `reference`: **470 works** (2,312 texts, i.e. searchable parts; single-page works are found as works); with unknown works: 685 works (2,353 texts). `show_unknown_by_default` is `true`, so the wiki picker shows 685.

| tier | works | complete |
|---|---|---|
| validated | 73 | 73 |
| proofread | 466 | 397 |
| unproofread | 307 | 268 |
| unknown | 223 | 215 |

Incomplete by reason: missing-parts 88, missing-pages 17, template 6, coverage 5.

## Types, structures, genres, languages

- type: book 433, other 337, poem 162, document 76, collection 35, periodical-issue 12, article 11, play 3
- type source: index 425, default 337, genre 178, template 57, structure 54, wikidata 18
- structure: single 639, subpages 281, linked 82, mixed 35, virtual 32
- genre: poetry 180, novel 86, folklore 78, legal-government 74, history 48, memoir-biography 34, song-hymn 29, essay 27, short-story 26, religious 19, periodical 12, speech 10, drama 9, children 7, travel 6, philosophy 6, letters 4, reference 4, science 4, other 2
- language: nb 572, no 329, nn 146, ru 9, da 4, de 3, fr 3, sv 2, en 1
- century: 4. 1, 13. 1, 17. 2, 18. 12, 19. 520, 20. 343, 21. 34

## Catalog file

`no.sqlite.gz`: 0.95 MB gzipped, 3.0 MB raw, sha256 `ae36c3cab54cbb658d0436fd2def24f94aeaaa159bebcf262d6e1852c47732bb`. Rows: works 1,069, parts 10,729, parts_fts 6,465, aliases 422, authors 327, excluded 184.

## Exclusions

Step 1 (pages): duplicate 76, disambiguation 59.

Step 5 (works): no-text 20, junk-template 13, under-300-bytes 12, no-text-pages 3, placeholder 1.

## Mapping work

Most common categories on root pages of works without a genre (candidates for `metadata.genre_categories`):

Henrik Wergeland 29, 1829 27, Prosa 10, Anton Tsjekhov 10, Norsk språk 8, Unionen mellom Norge og Sverige 7, Tekster fra 1919 6, Musikk 5, Tekster fra 1920 5, Tekster fra 1850 5, Tekster fra 1906 4, Nynorsk 4, Tekster fra 1893 4, Tekster fra 1922 4, Jakt 4, Tekster fra 1913 3, Tekster fra 1916 3, Tekster fra 1897 3, Tekster fra 1908 3, Oversettelser fra tysk 3, Tekster fra 1853 3, Tekster fra 1844 3, Tekster fra 1857 3, Tekster fra 1836 3, Tekster fra 1892 3, Tekster fra 1896 3, Eugenikk 3, Tekster fra 1880 3, Tekster fra 1899 3, Tekster fra 1885 3, Tekster fra 1887 3, Sakprosa 3, Tekster fra 1860 3, Tekster fra 1848 3, Morgenbladet 3, Tekster fra 1868 3, H. Aschehoug & Co. 3, Tekster fra 1924 3, Oversettelser fra gammelgresk 3, Tekster fra 1930 2

Most common unmapped Wikidata values on the works' items (candidates for `wikidata.genres` / `wikidata.types` in default.yaml):

P31 Q3331189 “version, edition or translation” 308, P31 Q7725634 “literary work” 116, P31 Q47461344 “written work” 14, P136 Q21802675 “adventure fiction” 5, P136 Q1196408 “historical fiction” 4, P136 Q213051 “non-fiction” 3, P136 Q16575965 “horror fiction” 3, P31 Q4830453 “business” 2, P31 Q20540385 “non-fiction literary work” 2, P136 Q3481604 “Fornyrðislag” 2, P136 Q676 “prose” 2, P31 Q384515 “treatise” 2, P31 Q5633421 “scientific journal” 2, P31 Q875508 “Reich law” 2, P31 Q59861107 “?” 2, P136 Q186424 “detective fiction” 2, P136 Q5937792 “crime fiction” 2, P31 Q2085381 “?” 2, P31 Q163740 “?” 2, P136 Q693 “?” 2, P136 Q5891 “?” 2, P31 Q28869365 “?” 2, P136 Q24925 “science fiction” 2, P136 Q12617225 “religious literature” 2, P31 Q179461 “religious text” 2, P136 Q8253 “fiction” 2, P136 Q12799318 “?” 2, P31 Q1572600 “?” 1, P136 Q8275050 “?” 1, P31 Q108329152 “?” 1, P31 Q24455797 “Norse cultural artifact” 1, P31 Q21112633 “edition of a translation” 1, P136 Q503354 “Christmas carol” 1, P136 Q21848887 “?” 1, P31 Q21848887 “?” 1, P136 Q104783349 “?” 1, P31 Q46337 “?” 1, P31 Q7099402 “?” 1, P136 Q1248788 “?” 1, P31 Q18168594 “?” 1

