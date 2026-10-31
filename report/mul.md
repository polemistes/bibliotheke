# mul.wikisource: catalog coverage report

Generated 2026-11-01 by `python -I -m wscatalog.catalog mul` from the dump of 20261001 (PLAN.md 5.2 steps 3–8; config `pipeline/config/mul.yaml`). Build took 6 s on the cached extract.

## Summary

|  | works | sources |
|---|---|---|
| works in the catalog | 28,582 |  |
| with an author | 77.3 % | header 21,966, none 6,490, index 86, body 25, wikidata 15 |
| with a year | 43.7 % | none 16,090, header 12,369, index 110, wikidata-edition 13 |
| with a genre | 6.8 % | none 26,639, category 1,934, wikidata 8, type 1 |
| with a quality tier (not unknown) | 3.9 % | unknown 27,458, proofread 680, unproofread 316, validated 128 |
| with a Wikidata item | 1.0 % |  |
| with a description | 0.1 % |  |
| with a cover image | 3.5 % |  |
| language other than the wiki's | 41.4 % | wiki 16,752, category 10,840, index 977, wikidata 13 |

## Default view (PLAN.md 5.4)

Validated or proofread, complete, not `reference` or `document`: **774 works** (1,956 texts, i.e. searchable parts; single-page works are found as works); with unknown works: 28,138 works (9,829 texts). `show_unknown_by_default` is `true` (from the config), so the wiki picker shows 28,138. Hidden by type from that view: reference 0, document 2.

| tier | works | complete |
|---|---|---|
| validated | 128 | 126 |
| proofread | 680 | 648 |
| unproofread | 316 | 283 |
| unknown | 27,458 | 27,366 |

Incomplete by reason: missing-parts 129, missing-pages 29, index-progress 1.

## Types, structures, genres, languages

- type: other 26,242, poem 1,634, collection 598, book 105, document 2, reference 1
- type source: default 26,242, genre 1,634, structure 704, wikidata 2
- structure: single 27,878, subpages 584, virtual 88, linked 30, mixed 2
- genre: poetry 1,412, children 555, song-hymn 224, short-story 171, folklore 81, religious 3, legal-government 2, reference 1
- language: mul 16,760, xmf 3,140, ba 1,955, vo 794, tt 771, lld 726, lmo 649, pl 441, bo 427, udm 285, zza 258, hsb 228, mrj 221, fur 220, gag 166, haw 156, lo 148, ku 138, oc 125, tyv 121, nds 116, si 110, kk 106, sq 85, pi 80
- century: 6. 1, 7. 1, 9. 2, 11. 1, 12. 11, 13. 9, 14. 6, 15. 7, 16. 19, 17. 94, 18. 702, 19. 1,593, 20. 9,107, 21. 938

## Catalog file

`mul.sqlite.gz`: 5.62 MB gzipped, 20.5 MB raw, sha256 `475664b1519edc69172c6adb9e245ba50cd617f99988bdad662bd0cd651fb46a`. Rows: works 28,582, parts 19,325, parts_fts 15,252, aliases 3,576, authors 3,083, excluded 1,158.

Works without catalog parts (over `parts_cap` 2,000, or `reference`): 1: `Słownik geograficzny Królestwa Polskiego` (14,068).

## Exclusions

Step 1 (pages): disambiguation 220.

Step 5 (works): no-text 554, under-300-bytes 367, no-text-pages 12, index-faulty 4, author-page 1.

## Mapping work

Most common categories on root pages of works without a genre (candidates for `metadata.genre_categories`):

Mingrelian 3,140, Volapük 795, Tatar 770, Ladin 727, Lumbaart 648, Polski 436, Volapük rigik 411, Tibetan 397, Volapük perevidöl 368, Ნათანგეფი 349, Башҡортса 332, Габдулла Тукай шигырьләре 319, Leopold Staff 271, Zazaki 256, Ზაალ ჯალაღონიაშ ნახანდეფი 247, Upper Sorbian 228, Hill Mari 221, Furlan 220, Გიგა ქავთარაძეშ ნახანდეფი 204, Რამაზ კუპრაშ ნახანდეფი 199, Муса Җәлил 198, Udmurt 195, Ჯვეში მარგალური პოეზიაშ ართმურთეფი 192, Gagauz 166, Lautans 164, ʻŌlelo Hawaiʻi 155, Მარგალური შაირეფი 153, ລາວ 148, Tiäds-P 140, Абай Құнанбайұлының өлеңдері 139, Bayniyaz Qayıpnazarovtıń qosıqları 139, Kurdî 136, Occitan 127, Გიორგი სიჭინავაშ ნახანდეფი 127, Volapükajenav (Vrig) 121, Тыва дыл 119, Plattdüütsch 116, සිංහල 112, Tiäds-V 105, Kazakh 103

Most common unmapped Wikidata values on the works' items (candidates for `wikidata.genres` / `wikidata.types` in default.yaml):

P31 Q7725634 “literary work” 16, P31 Q47461344 “written work” 6, P31 Q179461 “religious text” 6, P31 Q3331189 “version, edition or translation” 6, P136 Q179461 “religious text” 4, P136 Q1084059 “?” 2, P136 Q6912675 “?” 2, P31 Q3406098 “?” 2, P31 Q1898047 “?” 2, P31 Q602446 “lyrics” 2, P31 Q63203971 “?” 2, P136 Q12617225 “religious literature” 2, P31 Q12910132 “?” 2, P136 Q1864008 “?” 2, P31 Q1194894 “?” 2, P31 Q62662439 “?” 2, P31 Q7048853 “?” 2, P136 Q546729 “?” 2, P136 Q180494 “saga” 2, P136 Q2254211 “?” 2, P136 Q1196408 “historical fiction” 2, P136 Q10552833 “?” 2, P31 Q223729 “white paper” 2, P31 Q884375 “?” 1, P136 Q213051 “non-fiction” 1, P136 Q384515 “treatise” 1

