# fr.wikisource: catalog coverage report

Generated 2026-11-01 by `python -I -m wscatalog.catalog fr` from the dump of 20261001 (PLAN.md 5.2 steps 3–8; config `pipeline/config/fr.yaml`). Build took 55 s on the cached extract.

## Summary

|  | works | sources |
|---|---|---|
| works in the catalog | 40,242 |  |
| with an author | 89.4 % | wikidata 21,983, index 12,465, none 4,250, body 1,544 |
| with a year | 98.0 % | wikidata-edition 21,023, index 18,072, none 813, category 177, wikidata-work 157 |
| with a genre | 60.7 % | category 22,612, none 15,819, wikidata 1,097, type 447, template 267 |
| with a quality tier (not unknown) | 99.7 % | proofread 21,282, validated 11,257, unproofread 7,583, unknown 120 |
| with a Wikidata item | 66.6 % |  |
| with a description | 44.5 % |  |
| with a cover image | 98.3 % |  |
| language other than the wiki's | 0.8 % | wikidata 24,059, wiki 16,183 |

## Default view (PLAN.md 5.4)

Validated or proofread, complete, not `reference`: **31,190 works** (120,121 texts, i.e. searchable parts; single-page works are found as works); with unknown works: 31,301 works (120,173 texts). `show_unknown_by_default` is `false`, so the wiki picker shows 31,190.

| tier | works | complete |
|---|---|---|
| validated | 11,257 | 11,040 |
| proofread | 21,282 | 20,231 |
| unproofread | 7,583 | 6,505 |
| unknown | 120 | 111 |

Incomplete by reason: missing-parts 1,202, missing-pages 847, coverage 305, index-progress 1.

## Types, structures, genres, languages

- type: article 11,997, other 11,062, book 9,633, poem 2,312, periodical-issue 2,166, collection 1,632, play 608, document 577, reference 255
- type source: index 12,594, wikidata 12,025, default 11,062, genre 3,067, structure 1,168, template 326
- structure: single 28,462, subpages 9,717, virtual 841, linked 815, mixed 407
- genre: periodical 12,958, poetry 3,204, novel 2,194, drama 1,003, history 990, short-story 950, essay 750, science 521, philosophy 472, legal-government 405, memoir-biography 359, song-hymn 337, letters 330, reference 307, travel 224, folklore 192, speech 171, religious 127, children 116
- language: fr 39,937, en 82, de 36, grc 34, zh 34, la 32, ru 25, br 14, pl 12, it 7, es 7, ar 4, cs 4, nl 3, pt 2, xno 1, hu 1, he 1, sv 1, ro 1, da 1, cy 1, el 1, az 1
- century: 1. 1, 4. 2, 8. 1, 9. 3, 10. 1, 11. 2, 12. 11, 13. 35, 14. 12, 15. 11, 16. 107, 17. 603, 18. 1,568, 19. 24,407, 20. 12,442, 21. 218

## Catalog file

`fr.sqlite.gz`: 28.61 MB gzipped, 99.7 MB raw, sha256 `b903a5f4c2e928b41eced6beefb514939b015be9b2610934296553d6c73f8e4c`. Rows: works 40,242, parts 262,973, parts_fts 150,866, aliases 44,421, authors 9,041, excluded 13,594.

Works without catalog parts (over `parts_cap` 2,000, or `reference`): 196: `L’Encyclopédie` (41,677), `Dictionnaire de Trévoux/6e édition, 1771` (31,513), `Petit dictionnaire de la langue française (1836)` (23,874), `Dictionnaire des patois romans de la Moselle` (21,247), `Petit dictionnaire de la langue française (1860)` (20,406), `Correspondance de Voltaire` (10,426), `Petit dictionnaire de la langue française (Hocquart, 1831)` (7,352), `Grand dictionnaire universel du XIXe siècle` (5,834), `Dictionnaire liégeois-français (Forir)` (4,534), `Le Littré de la Grand’Côte/3e éd., 1903` (4,250), `Encyclopédie, ou Dictionnaire raisonné des sciences, des arts et des métiers` (3,858), `Dictionnaire infernal/6e éd., 1863` (3,826).

## Exclusions

Step 1 (pages): disambiguation 8,455, whole-text 4,284, duplicate 153, versions 55.

Step 5 (works): no-text 315, no-text-pages 165, under-300-bytes 105, index-faulty 59, author-page 3.

## Mapping work

Most common categories on root pages of works without a genre (candidates for `metadata.genre_categories`):

Bon pour export 2,573, XIXe siècle 607, Revue littéraire (RDDM) 402, Anarchisme 398, Revue musicale (RDDM) 286, Publiés à Paris 224, Revue dramatique (RDDM) 214, Articles de René Doumic 206, Articles de Camille Bellaigue 178, XXe siècle 176, Articles de Ferdinand Brunetière 134, Éducation en Allemagne 127, XVIIIe siècle 124, Courrier de l'extérieur (RP) 100, Articles de 1885 99, Traduction Wikisource 91, Articles du Tour du monde 89, Revue scientifique (RDDM) 86, 1883 80, Articles de 1881 80, 1887 70, La Revue blanche 70, Articles du Journal asiatique 70, La presse et les livres (RP) 70, Antiquité 69, Articles de 1899 68, Articles de 1878 68, L’école d’Ancien régime 67, Articles d’André Beaunier 67, Nécrologie de la Revue pédagogique 66, Musique 64, Articles de 1898 64, Inde 63, Paris 61, 1903 61, Articles de 1880 61, Articles de Charles Nordmann 61, XVIIe siècle 59, Journal asiatique 58, Économie 57

Most common unmapped Wikidata values on the works' items (candidates for `wikidata.genres` / `wikidata.types` in default.yaml):

P31 Q3331189 “version, edition or translation” 9,865, P31 Q7725634 “literary work” 3,832, P31 Q17518557 “collection of articles” 3,125, P31 Q47461344 “written work” 805, P31 Q877358 “United Nations Security Council resolution” 532, P31 Q17586363 “poetry collection” 219, P136 Q17628188 “crônica” 186, P136 Q747483 “erotic literature” 175, P136 Q21802675 “adventure fiction” 124, P136 Q24925 “science fiction” 96, P136 Q1196408 “historical fiction” 89, P31 Q58483083 “dramatico-musical work” 84, P136 Q8253 “fiction” 67, P31 Q21112633 “edition of a translation” 67, P31 Q1238720 “volume” 67, P31 Q7755 “constitution” 63, P136 Q213051 “non-fiction” 53, P136 Q785479 “opéra comique” 50, P31 Q234460 “text” 46, P136 Q5937792 “crime fiction” 45, P136 Q1072723 “travel literature” 44, P136 Q186424 “detective fiction” 41, P31 Q87167 “manuscript” 41, P31 Q42402646 “Volksmärchen” 40, P136 Q16575965 “horror fiction” 40, P136 Q19765983 “romantic fiction” 39, P31 Q187685 “?” 34, P136 Q384515 “treatise” 32, P136 Q5151421 “?” 32, P136 Q223945 “?” 31, P136 Q18536349 “?” 31, P31 Q625298 “?” 29, P136 Q132311 “fantasy” 29, P31 Q1980247 “?” 28, P31 Q28869365 “?” 28, P136 Q3084465 “?” 26, P31 Q386724 “?” 26, P31 Q13136 “?” 25, P31 Q17537576 “?” 25, P136 Q20669641 “?” 25

