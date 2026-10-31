# de.wikisource: catalog coverage report

Generated 2026-11-01 by `python -I -m wscatalog.catalog de` from the dump of 20261001 (PLAN.md 5.2 steps 3–8; config `pipeline/config/de.yaml`). Build took 10 s on the cached extract.

## Summary

|  | works | sources |
|---|---|---|
| works in the catalog | 17,388 |  |
| with an author | 66.5 % | header 8,111, none 5,823, wikidata 3,454 |
| with a year | 97.3 % | header 9,428, wikidata-edition 4,476, category 2,990, none 475, wikidata-work 19 |
| with a genre | 87.5 % | category 13,744, none 2,168, wikidata 1,390, template 84, type 2 |
| with a quality tier (not unknown) | 98.3 % | validated 9,833, proofread 6,999, unknown 290, unproofread 266 |
| with a Wikidata item | 46.6 % |  |
| with a description | 57.7 % |  |
| with a cover image | 20.0 % |  |
| language other than the wiki's | 1.2 % | wiki 13,611, wikidata 3,777 |

## Default view (PLAN.md 5.4)

Validated or proofread, complete, not `reference` or `document`: **10,140 works** (38,907 texts, i.e. searchable parts; single-page works are found as works); with unknown works: 10,381 works (40,243 texts). `show_unknown_by_default` is `false` (from the config), so the wiki picker shows 10,140. Hidden by type from that view: reference 22, document 4,571.

| tier | works | complete |
|---|---|---|
| validated | 9,833 | 9,583 |
| proofread | 6,999 | 5,150 |
| unproofread | 266 | 228 |
| unknown | 290 | 258 |

Incomplete by reason: template 1,991, missing-parts 168, missing-pages 10.

## Types, structures, genres, languages

- type: other 6,503, document 4,614, poem 4,186, article 1,039, collection 690, periodical-issue 134, book 130, play 50, reference 42
- type source: default 6,503, genre 4,714, template 4,531, wikidata 833, structure 807
- structure: single 16,434, linked 447, subpages 353, mixed 108, virtual 46
- genre: legal-government 4,534, poetry 4,122, memoir-biography 4,003, history 818, periodical 455, essay 394, science 245, religious 227, short-story 200, song-hymn 176, folklore 162, letters 100, novel 89, speech 85, drama 57, reference 53, travel 38, philosophy 38, children 2
- language: de 17,175, fr 156, en 26, la 8, grc 5, it 4, zh 3, ru 3, uk 2, cs 1, el 1, hu 1, pl 1, sv 1, ar 1
- century: 1. 1, 8. 1, 9. 4, 10. 1, 11. 2, 12. 711, 13. 2,062, 14. 50, 15. 22, 16. 98, 17. 271, 18. 430, 19. 5,601, 20. 7,537, 21. 122

## Catalog file

`de.sqlite.gz`: 7.85 MB gzipped, 26.3 MB raw, sha256 `a914e7781c3aadb6727a6537d073165c26a15d071969eebc868c9565ef37f493`. Rows: works 17,388, parts 61,243, parts_fts 52,475, aliases 3,312, authors 2,197, excluded 14,847.

## Exclusions

Step 1 (pages): reference-prefix 218,464, author-page 8,822, excluded-template 1,763, disambiguation 773, under-excluded 668, no-header 488, excluded-category 53.

Step 5 (works): bibliography 2,029, under-300-bytes 227, no-text 13, author-page 3.

## Mapping work

Most common categories on root pages of works without a genre (candidates for `metadata.genre_categories`):

Neuhochdeutsch 859, Deutschland 740, Darstellung 365, Deutsche Philologie 198, 1910er Jahre 192, Kunstwissenschaft 139, Sachsen als Thema 113, Nachricht 103, Walther Kabel 82, 1900er Jahre 80, 1880er Jahre 76, 1890er Jahre 75, 1920er Jahre 68, Kriminalität 62, Kleiner Briefkasten Einzelartikel 58, Geografie 56, 19. Jahrhundert 52, 1870er Jahre 52, Zeitschriftenartikelliste 49, Publizistik 49, 1860er Jahre 48, Historische Studierendenunterlagen der HMT Leipzig 47, Die Gartenlaube 41, 1850er Jahre 36, Geschichte Schwäbisch Gmünds 36, 1840er Jahre 35, Königreich Sachsen 35, Zeugnis 35, 1820er Jahre 34, Schifffahrtstechnik 33, Sachprosa 32, Frühneuhochdeutsch 30, Erzählprosa 29, Musik 27, Bildungswesen, Pädagogik 27, Österreich 27, 1830er Jahre 26, Anekdote 24, Handschrift 22, 1790er Jahre 22

Most common unmapped Wikidata values on the works' items (candidates for `wikidata.genres` / `wikidata.types` in default.yaml):

P31 Q7725634 “literary work” 2,670, P31 Q1004391 “public notice” 1,769, P31 Q47461344 “written work” 1,338, P31 Q2703920 “ordinance” 819, P31 Q3331189 “version, edition or translation” 346, P31 Q567696 “order” 225, P31 Q1791610 “cultural convention” 208, P31 Q780605 “exhibition catalogue” 180, P31 Q858157 “German folklore” 171, P31 Q117599884 “gloss” 170, P31 Q13433827 “encyclopedia article” 162, P136 Q213051 “non-fiction” 129, P31 Q19816504 “annual collection” 94, P31 Q4230425 “collection volume” 80, P31 Q875508 “Reich law” 76, P31 Q732577 “publication” 72, P31 Q93288 “contract” 64, P31 Q651270 “letter to the editor” 62, P31 Q19362971 “legendary ballad” 48, P31 Q5633421 “scientific journal” 47, P31 Q9557810 “bilateral treaty” 44, P31 Q265158 “review” 40, P31 Q104178698 “postal contract” 38, P31 Q1006079 “Federal Act (Germany)” 34, P31 Q1238720 “volume” 34, P31 Q819647 “right of mining” 34, P31 Q17518557 “collection of articles” 32, P31 Q26271743 “?” 29, P31 Q182659 “?” 25, P31 Q846882 “?” 24, P31 Q193495 “?” 24, P31 Q162827 “?” 22, P31 Q234460 “text” 21, P31 Q1741846 “?” 20, P136 Q951487 “?” 20, P31 Q28869365 “?” 19, P31 Q625298 “?” 18, P31 Q1298239 “?” 18, P31 Q658274 “?” 18, P31 Q87167 “manuscript” 17

