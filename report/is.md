# is.wikisource: catalog coverage report

Generated 2026-11-01 by `python -I -m wscatalog.catalog is` from the dump of 20261001 (PLAN.md 5.2 steps 3–8; config `pipeline/config/is.yaml`). Build took 0 s on the cached extract.

## Summary

|  | works | sources |
|---|---|---|
| works in the catalog | 250 |  |
| with an author | 52.8 % | none 118, header 98, body 20, wikidata 14 |
| with a year | 14.0 % | none 215, header 18, wikidata-edition 15, wikidata-work 1, index 1 |
| with a genre | 56.8 % | category 133, none 108, wikidata 9 |
| with a quality tier (not unknown) | 2.8 % | unknown 243, proofread 6, validated 1 |
| with a Wikidata item | 22.0 % |  |
| with a description | 15.6 % |  |
| with a cover image | 2.4 % |  |
| language other than the wiki's | 1.6 % | wiki 230, wikidata 15, index 5 |

## Default view (PLAN.md 5.4)

Validated or proofread, complete, not `reference`: **5 works** (60 texts, i.e. searchable parts; single-page works are found as works); with unknown works: 246 works (241 texts). `show_unknown_by_default` is `true`, so the wiki picker shows 246.

| tier | works | complete |
|---|---|---|
| validated | 1 | 1 |
| proofread | 6 | 4 |
| unproofread | 0 | 0 |
| unknown | 243 | 241 |

Incomplete by reason: missing-parts 4.

## Types, structures, genres, languages

- type: other 157, poem 60, book 18, collection 14, document 1
- type source: default 157, genre 59, structure 27, index 5, wikidata 2
- structure: single 218, subpages 27, linked 2, virtual 2, mixed 1
- genre: other 55, poetry 53, song-hymn 17, history 11, short-story 6, legal-government 5, folklore 4, letters 2, religious 1, novel 1
- language: is 246, de 2, fr 1, el 1
- century: 12. 1, 13. 2, 14. 7, 15. 2, 16. 2, 18. 3, 19. 7, 20. 8, 21. 3

## Catalog file

`is.sqlite.gz`: 0.12 MB gzipped, 0.5 MB raw, sha256 `e152d007ff2dc2f783892af81b3c80462ddfbfb50f3c4b0bd4f835fa6ae3e1b3`. Rows: works 250, parts 1,688, parts_fts 263, aliases 1, authors 52, excluded 13.

Works without catalog parts (over `parts_cap` 2,000, or `reference`): 1: `Íslenzkar þjóðsögur og æfintýri` (3,058).

## Exclusions

Step 1 (pages): versions 4, disambiguation 3.

Step 5 (works): under-300-bytes 5, no-text 1.

## Mapping work

Most common categories on root pages of works without a genre (candidates for `metadata.genre_categories`):

Fræðslubókmenntir 2, Fornsögur 1, Máldagar 1, Verk 19. aldar 1, Verk 20. aldar 1

Most common unmapped Wikidata values on the works' items (candidates for `wikidata.genres` / `wikidata.types` in default.yaml):

P31 Q7725634 “literary work” 51, P136 Q2336402 “Þáttr” 16, P136 Q180494 “saga” 12, P136 Q17991521 “tale” 10, P31 Q3331189 “version, edition or translation” 6, P136 Q676 “prose” 6, P31 Q24457232 “skaldic poem” 2, P136 Q24925 “science fiction” 2, P136 Q7440128 “nautical fiction” 2, P136 Q3940786 “scientific romance” 2, P136 Q1072723 “travel literature” 2, P136 Q21802675 “adventure fiction” 2, P136 Q67467614 “merveilleux scientifique” 2, P136 Q124455603 “written prayer” 2, P31 Q3627146 “Christian prayer” 2, P31 Q7239036 “prayer in the New Testament” 2, P31 Q1385610 “excerpt” 2, P31 Q1868072 “logion” 2, P31 Q867335 “literary cycle” 2, P31 Q47307 “Classical genealogy” 2, P31 Q321839 “agreement” 2, P136 Q1417288 “Old Norse literature” 2, P136 Q2469348 “legendary saga” 2, P31 Q46783 “oral lore” 2, P136 Q3481604 “Fornyrðislag” 2, P31 Q8502 “mountain” 2, P31 Q24455797 “Norse cultural artifact” 2, P136 Q503354 “Christmas carol” 2, P31 Q503354 “Christmas carol” 2, P136 Q16255517 “thula” 2, P136 Q178985 “ode” 1, P136 Q12617225 “religious literature” 1, P136 Q179461 “religious text” 1, P31 Q179461 “religious text” 1, P31 Q12910132 “?” 1, P31 Q2668072 “?” 1, P136 Q1347298 “?” 1

