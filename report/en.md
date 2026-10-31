# en.wikisource: catalog coverage report

Generated 2026-11-01 by `python -I -m wscatalog.catalog en` from the dump of 20261001 (PLAN.md 5.2 steps 3–8; config `pipeline/config/en.yaml`). Build took 56 s on the cached extract.

## Summary

|  | works | sources |
|---|---|---|
| works in the catalog | 55,987 |  |
| with an author | 83.5 % | header 34,221, wikidata 11,491, none 9,212, index 919, body 144 |
| with a year | 68.6 % | header 25,222, none 17,578, wikidata-edition 11,449, index 1,512, wikidata-work 226 |
| with a genre | 68.0 % | template 22,079, none 17,939, category 8,387, wikidata 7,558, type 24 |
| with a quality tier (not unknown) | 40.1 % | unknown 33,558, proofread 12,615, validated 6,997, unproofread 2,817 |
| with a Wikidata item | 67.3 % |  |
| with a description | 31.2 % |  |
| with a cover image | 31.2 % |  |
| language other than the wiki's | 1.8 % | wiki 35,609, index 12,063, wikidata 8,315 |

## Default view (PLAN.md 5.4)

Validated or proofread, complete, not `reference`: **18,042 works** (113,176 texts, i.e. searchable parts; single-page works are found as works); with unknown works: 50,924 works (133,073 texts). `show_unknown_by_default` is `true`, so the wiki picker shows 50,924.

| tier | works | complete |
|---|---|---|
| validated | 6,997 | 6,855 |
| proofread | 12,615 | 11,191 |
| unproofread | 2,817 | 1,923 |
| unknown | 33,558 | 32,884 |

Incomplete by reason: template 1,796, missing-parts 1,056, missing-pages 255, coverage 27.

## Types, structures, genres, languages

- type: document 29,118, other 11,747, book 10,271, poem 2,281, collection 1,643, periodical-issue 436, article 311, play 149, reference 31
- type source: template 28,437, default 11,747, index 8,161, structure 3,754, genre 3,040, wikidata 848
- structure: single 37,661, subpages 17,468, virtual 716, mixed 85, linked 57
- genre: legal-government 26,095, speech 3,095, poetry 2,435, short-story 1,448, novel 1,402, periodical 675, song-hymn 643, letters 416, essay 372, children 325, memoir-biography 322, religious 246, drama 230, science 206, philosophy 176, folklore 124, reference 114, travel 109, history 45
- language: en 54,999, zh 469, fr 105, grc 70, de 64, ru 62, la 49, cs 32, ja 21, pl 14, ang 12, ko 11, es 8, it 8, nl 7, sa 6, ga 5, uk 5, ar 4, da 4, pt 4, he 3, sv 3, fa 3, gd 3
- century: 1. 1, 2. 3, 3. 1, 4. 2, 6. 1, 8. 2, 9. 2, 11. 2, 12. 6, 13. 21, 14. 28, 15. 17, 16. 123, 17. 382, 18. 1,680, 19. 10,317, 20. 17,591, 21. 8,223

## Catalog file

`en.sqlite.gz`: 36.19 MB gzipped, 120.6 MB raw, sha256 `9b27eb0d2ebb89779e5092530099ea09bcdc860679b6210bbc42fa042bc45bd8`. Rows: works 55,987, parts 343,680, parts_fts 199,166, aliases 75,075, authors 15,165, excluded 16,110.

Works without catalog parts (over `parts_cap` 2,000, or `reference`): 41: `Alumni Oxonienses: the Members of the University of Oxford, 1715-1886` (63,225), `1911 Encyclopædia Britannica` (37,420), `Dictionary of National Biography, 1885-1900` (28,089), `Catholic Encyclopedia (1913)` (11,629), `The New International Encyclopædia` (10,866), `An Etymological Dictionary of the German Language` (9,961), `Popular Science Monthly` (8,174), `Men-at-the-Bar` (7,448), `Nicene and Post-Nicene Fathers: Series II` (7,099), `Ante-Nicene Fathers` (5,616), `Alumni Oxonienses: the Members of the University of Oxford, 1500-1714` (5,317), `Nicene and Post-Nicene Fathers: Series I` (5,287).

## Exclusions

Step 1 (pages): disambiguation 7,627, versions 7,250, duplicate 72.

Step 5 (works): no-text 574, no-text-pages 272, under-300-bytes 188, junk-template 106, index-faulty 14, author-page 7.

## Mapping work

Most common categories on root pages of works without a genre (candidates for `metadata.genre_categories`):

Ready for export 333, Silent film 173, Non-fiction books 134, Drama film 104, Chapbooks 97, United States National Film Registry films 78, Modern works 74, Works originally in Latin 65, Works originally in French 64, Fiction 60, Pictures, with illustrations in verse 54, Comedy film 47, Australian literature 46, People's Republic of China 46, Stratemeyer Syndicate 46, Works originally in German 45, Films with historical settings 45, India 43, Early modern works 42, Cookbooks 42, Pre-1945 State Roads in Florida 41, Films based on books 40, Pulp fiction 39, Sheet music 38, Pamphlets 38, Slavery in the United States (Non-fiction) 37, Russian non-fiction 36, United States patents 36, United Kingdom 35, Economics 35, Works originally in Greek 33, Journals 32, Socialism 30, Serialized works in Amazing Stories 30, The Charles Henry Gauss Family Papers 29, American literature 27, NASA document collections 27, Exploration of Western Australia 27, United States 27, Silent cartoons 27

Most common unmapped Wikidata values on the works' items (candidates for `wikidata.genres` / `wikidata.types` in default.yaml):

P31 Q3331189 “version, edition or translation” 13,402, P31 Q1338798 “United States executive order” 8,102, P31 Q7725634 “literary work” 7,070, P31 Q7241458 “presidential proclamation” 6,670, P31 Q19692072 “decision of the Supreme Court of the United States” 6,106, P31 Q877358 “United Nations Security Council resolution” 2,742, P31 Q47461344 “written work” 1,051, P31 Q105774620 “Public General Act of the Parliament of the United Kingdom” 727, P31 Q132115645 “standard analytical method” 712, P31 Q476068 “Act of Congress in the United States” 632, P31 Q10870555 “report” 580, P31 Q835068 “law of the People's Republic of China” 470, P31 Q554211 “State of the Union address” 468, P31 Q47126552 “Weekly List” 415, P136 Q226730 “silent film” 407, P31 Q11424 “film” 323, P31 Q7755 “constitution” 308, P31 Q97480682 “act of the Parliament of South Africa” 286, P31 Q7604686 “UK Statutory Instrument” 274, P136 Q16575965 “horror fiction” 225, P136 Q132311 “fantasy” 222, P136 Q6585139 “mystery fiction” 201, P31 Q602446 “lyrics” 200, P136 Q130232 “drama film” 200, P136 Q24925 “science fiction” 199, P136 Q21802675 “adventure fiction” 196, P136 Q116953130 “hymn text” 196, P136 Q213051 “non-fiction” 175, P31 Q190399 “pamphlet” 168, P31 Q49848 “document” 162, P31 Q686822 “bill” 148, P31 Q5633421 “scientific journal” 142, P31 Q1330251 “United Nations General Assembly resolution” 140, P136 Q1196408 “historical fiction” 136, P31 Q223729 “white paper” 136, P31 Q6503489 “Law of Taiwan” 131, P136 Q56451354 “children's fiction” 130, P31 Q2085515 “minutes” 126, P31 Q20737420 “Republic Act” 118, P31 Q43305660 “United States patent” 114

