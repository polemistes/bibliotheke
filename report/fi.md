# fi.wikisource: catalog coverage report

Generated 2026-11-01 by `python -I -m wscatalog.catalog fi` from the dump of 20261001 (PLAN.md 5.2 steps 3–8; config `pipeline/config/fi.yaml`). Build took 1 s on the cached extract.

## Summary

|  | works | sources |
|---|---|---|
| works in the catalog | 13,282 |  |
| with an author | 76.4 % | header 10,124, none 3,133, wikidata 21, index 4 |
| with a year | 20.3 % | none 10,588, category 2,647, index 26, wikidata-edition 21 |
| with a genre | 26.2 % | none 9,802, category 3,470, wikidata 10 |
| with a quality tier (not unknown) | 0.4 % | unknown 13,234, proofread 36, unproofread 12 |
| with a Wikidata item | 5.7 % |  |
| with a description | 0.2 % |  |
| with a cover image | 0.2 % |  |
| language other than the wiki's | 0.2 % | wiki 13,261, wikidata 21 |

## Default view (PLAN.md 5.4)

Validated or proofread, complete, not `reference`: **34 works** (79 texts, i.e. searchable parts; single-page works are found as works); with unknown works: 13,260 works (233 texts). `show_unknown_by_default` is `false`, so the wiki picker shows 34.

| tier | works | complete |
|---|---|---|
| validated | 0 | 0 |
| proofread | 36 | 34 |
| unproofread | 12 | 10 |
| unknown | 13,234 | 13,226 |

Incomplete by reason: missing-parts 12.

## Types, structures, genres, languages

- type: other 10,179, poem 2,910, document 130, collection 35, book 27, article 1
- type source: default 10,179, genre 2,912, template 128, structure 62, wikidata 1
- structure: single 13,220, subpages 49, virtual 8, linked 5
- genre: poetry 2,726, short-story 348, song-hymn 293, legal-government 129, history 87, philosophy 1, speech 1, essay 1
- language: fi 13,262, de 7, en 6, ru 2, fr 2, grc 1, el 1, no 1
- century: 16. 6, 17. 6, 18. 28, 19. 1,884, 20. 764, 21. 5

## Catalog file

`fi.sqlite.gz`: 1.72 MB gzipped, 5.6 MB raw, sha256 `92a7cf27d811088dc5728ae7f7a210ebac181f97b6580f2f2160bd61dae7dc40`. Rows: works 13,282, parts 642, parts_fts 327, aliases 411, authors 912, excluded 1,164.

## Exclusions

Step 1 (pages): disambiguation 243.

Step 5 (works): under-300-bytes 744, no-text 145, author-page 32.

## Mapping work

Most common categories on root pages of works without a genre (candidates for `metadata.genre_categories`):

Anonyymi 543, Asiatekstit 336, Maamme kirja 189, 1890 188, Uusi Suometar 153, Aamulehti 109, Tao-te-king 84, Idmanin kavallusjuttu 84, Kustaa Paturi 82, Minna Canth 78, Sivukulman Santerin pakinat 78, Helsinki 75, Satanen muistelmia Pohjanmaalta 67, Työmies 67, Suometar 54, 1917 54, Kansallisarkisto 53, Karjala 51, 1913 51, Gustaf Fredrik Brask 50, 1912 46, Suomen kansanvaltuuskunnan asetuskokoelma 46, Pentti Lyytinen 45, Merenkulku 44, Heinrich Heine 43, Taajaväkiset yhdyskunnat 43, 1887 41, Kirjallisuusarvostelut 41, Päivälehti 40, Kauppalat 39, Qventin Durward 38, Valtioneuvoston päätökset 38, Arkistolainsäädäntö 38, Valtioneuvoston kanslian kirjeet 37, Matti Jurva 37, Hallituksen esitykset 36, Hämäläinen 35, Tapio 35, 1878 34, Bibliografiat 34

Most common unmapped Wikidata values on the works' items (candidates for `wikidata.genres` / `wikidata.types` in default.yaml):

P31 Q7725634 “literary work” 30, P31 Q3331189 “version, edition or translation” 4, P31 Q17442446 “?” 2, P31 Q14204246 “?” 2, P31 Q15633587 “?” 2, P31 Q4663903 “?” 2, P31 Q1412901 “?” 2, P31 Q1498487 “?” 2, P31 Q1464916 “?” 2, P136 Q64448878 “?” 2, P136 Q111972381 “?” 2, P136 Q2254211 “?” 2, P136 Q1196408 “historical fiction” 2, P136 Q3440961 “?” 2, P136 Q20669641 “?” 2, P136 Q24925 “science fiction” 2, P136 Q5937792 “crime fiction” 2, P136 Q16575965 “horror fiction” 2, P136 Q543673 “?” 2, P136 Q9730 “?” 2, P31 Q234460 “text” 2, P31 Q49848 “document” 2, P136 Q124455603 “written prayer” 2, P31 Q3627146 “Christian prayer” 2, P31 Q7239036 “prayer in the New Testament” 2, P31 Q1385610 “excerpt” 2, P31 Q1868072 “logion” 2, P31 Q7755 “constitution” 2, P136 Q1864008 “?” 2, P31 Q1194894 “?” 2, P31 Q62662439 “?” 2, P31 Q47461344 “written work” 2, P31 Q7048853 “?” 2, P31 Q116741026 “?” 2, P31 Q104840560 “?” 2

