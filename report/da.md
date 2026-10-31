# da.wikisource: catalog coverage report

Generated 2026-11-01 by `python -I -m wscatalog.catalog da` from the dump of 20261001 (PLAN.md 5.2 steps 3–8; config `pipeline/config/da.yaml`). Build took 0 s on the cached extract.

## Summary

|  | works | sources |
|---|---|---|
| works in the catalog | 1,168 |  |
| with an author | 71.1 % | header 642, none 338, index 156, body 21, wikidata 11 |
| with a year | 60.8 % | none 458, category 381, index 307, wikidata-edition 11, header 11 |
| with a genre | 68.8 % | category 757, none 364, template 42, wikidata 5 |
| with a quality tier (not unknown) | 28.2 % | unknown 839, unproofread 203, proofread 104, validated 22 |
| with a Wikidata item | 28.1 % |  |
| with a description | 1.5 % |  |
| with a cover image | 27.0 % |  |
| language other than the wiki's | 0.9 % | wiki 1,156, wikidata 12 |

## Default view (PLAN.md 5.4)

Validated or proofread, complete, not `reference` or `document`: **102 works** (764 texts, i.e. searchable parts; single-page works are found as works); with unknown works: 875 works (836 texts). `show_unknown_by_default` is `true` (from the config), so the wiki picker shows 875. Hidden by type from that view: reference 0, document 73.

| tier | works | complete |
|---|---|---|
| validated | 22 | 21 |
| proofread | 104 | 91 |
| unproofread | 203 | 129 |
| unknown | 839 | 836 |

Incomplete by reason: missing-pages 69, missing-parts 22.

## Types, structures, genres, languages

- type: other 488, poem 420, document 141, collection 75, book 42, article 2
- type source: default 488, genre 423, template 139, structure 117, wikidata 1
- structure: single 1,051, subpages 88, linked 23, virtual 4, mixed 2
- genre: song-hymn 348, legal-government 140, short-story 80, poetry 77, folklore 50, novel 46, philosophy 30, history 27, periodical 15, religious 12, essay 2, speech 1
- language: da 1,158, de 3, en 2, fr 2, el 1, ar 1, ru 1
- century: 14. 1, 15. 1, 16. 8, 17. 3, 18. 65, 19. 435, 20. 147, 21. 50

## Catalog file

`da.sqlite.gz`: 0.40 MB gzipped, 1.4 MB raw, sha256 `ec0904bc9723e65fdcd46e75058a367e5eebf27a11f959f5ab4b4be857a49d5f`. Rows: works 1,168, parts 2,279, parts_fts 1,383, aliases 147, authors 402, excluded 21.

## Exclusions

Step 1 (pages): disambiguation 3, duplicate 1.

Step 5 (works): no-text 13, under-300-bytes 4.

## Mapping work

Most common categories on root pages of works without a genre (candidates for `metadata.genre_categories`):

Årstal mangler 127, Kategori mangler 49, Chronologisk Register over de Kongelige Forordninger og Aabne Breve, samt andre trykte Anordninger (1670–1849) 26, Kongelige Rescripter, Resolutioner og Collegialbreve for Danmark og Norge udtogsvis udgivne i chronologisk Orden (1660–1813) 26, Islands Højesterets danske domssamling 23, 2001 22, De Troendes Skat og Klenodie, Som er Den foragtede JESUS af Nazareth, betragtet i nogle Psalmer 15, 1758 15, Af en renaissancepaves erindringer 14, Folkekalender for Danmark 14, 1795 13, Bidrag til Professor Jakob Baden’s Levnet, Karakteristik og Videnskabelige Fortjeneste som Skolemand og Lærd 10, Aarsberetninger fra Det Kongelige Geheimearchiv, indeholdende Bidrag til dansk Historie af utrykte Kilder 9, Politik 8, Årbøger 8, Juridiske artikler 8, Anonym komponist 7, Tidsskriftsartikler 7, Anonym forfatter 7, Kanon 7, Almanakker 7, Nordisk mytologi 6, Gaius Valerius Catullus forfatter 6, Corpus constitutionum Daniæ : Forordninger, Recesser og andre kongelige Breve, Danmarks Lovgivning vedkommende 1558-1660 6, Bekendelsesskrifter i kristendommen 5, Livets gang 5, 1870 5, Partiprogrammer 5, Aviser 5, Flyveskrifter 5, Samling af gamle danske Love udgivne med Indledninger og Anmærkninger og tildels Oversættelse 5, Satirer 4, Nyeste Skilderie af Kjøbenhavn 4, Pædagogik 4, 1855 4, Skuespil 4, 1883 4, Personalhistorie 3, Forfatninger 3, 1872 3

Most common unmapped Wikidata values on the works' items (candidates for `wikidata.genres` / `wikidata.types` in default.yaml):

P31 Q7725634 “literary work” 11, P31 Q47461344 “written work” 4, P31 Q116741026 “?” 4, P31 Q17442446 “?” 2, P31 Q14204246 “?” 2, P31 Q15633587 “?” 2, P31 Q4663903 “?” 2, P31 Q7755 “constitution” 2, P31 Q113627831 “?” 2, P136 Q503354 “Christmas carol” 2, P136 Q21848887 “?” 2, P31 Q21848887 “?” 2, P136 Q124455603 “written prayer” 2, P31 Q3627146 “Christian prayer” 2, P31 Q7239036 “prayer in the New Testament” 2, P31 Q1385610 “excerpt” 2, P31 Q1868072 “logion” 2, P31 Q1464916 “?” 2, P136 Q1864008 “?” 2, P31 Q1194894 “?” 2, P31 Q62662439 “?” 2, P31 Q7048853 “?” 2, P136 Q189279 “?” 2, P31 Q49848 “document” 2, P31 Q2703920 “ordinance” 2, P31 Q534026 “?” 2, P31 Q1691434 “?” 2, P31 Q727002 “?” 2, P31 Q3497659 “?” 2, P31 Q104840560 “?” 2, P136 Q104783349 “?” 2, P31 Q46337 “?” 2, P136 Q16575965 “horror fiction” 2, P136 Q21609546 “?” 2, P136 Q37654 “?” 2, P31 Q3331189 “version, edition or translation” 1

