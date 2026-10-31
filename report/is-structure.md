# is.wikisource: structure and quality survey (WP1a)

Generated 2026-10-31 by `python -I -m wscatalog.survey is` from `iswikisource-20261001-pages-articles.xml.bz2` (resolution and quality rules: PLAN.md 5.2 steps 1–1h and 5.4; config `pipeline/config/is.yaml`). Resolution took 0 s on the cached extract.

Input: 4,978 ns0 pages, 2 ns0 redirects, 723 scan pages, 8 Index pages, 0 pages with the `disambiguation` page prop.

## 1. Works

256 works resolved from 4,971 non-excluded ns0 pages; 250 enter the catalog after the hard exclusions (section 9). Multi-part works hold 4,717 parts, of which 3,227 are searchable texts.

| structure | works | parts | texts |
|---|---|---|---|
| single | 224 | 224 | 0 |
| subpages | 27 | 1,652 | 256 |
| linked | 2 | 5 | 5 |
| virtual | 2 | 2 | 2 |
| mixed | 1 | 3,058 | 2,964 |

| type (step 1e) | works |
|---|---|
| single | 224 |
| book | 17 |
| collection | 13 |
| reference | 1 |
| periodical-issue | 1 |

Largest works by parts:

| work | structure | type | parts | texts | missing | rules of the parts |
|---|---|---|---|---|---|---|
| Íslenzkar þjóðsögur og æfintýri | mixed | reference | 3,058 | 2,964 | 2 | container 3055, hierarchy 3 |
| Heimskringla | subpages | book | 852 | 5 | 0 | container 852 |
| Brennu-Njáls saga | subpages | book | 159 | 0 | 0 | hierarchy 159 |
| Landnámabók | subpages | book | 108 | 1 | 0 | hierarchy 106, container 2 |
| Guðmundar saga Arasonar | subpages | book | 91 | 1 | 0 | container 91 |
| Nýja testamenti Odds Gottskálkssonar | subpages | collection | 48 | 48 | 0 | container 48 |
| Þúsund og ein nótt | subpages | collection | 46 | 46 | 0 | hierarchy 46 |
| Fölvar Rósir | subpages | book | 43 | 43 | 0 | container 43 |
| Jómsvíkinga saga | subpages | book | 39 | 0 | 0 | hierarchy 38, container 1 |
| Bjarnar saga Hítdælakappa | subpages | book | 34 | 0 | 0 | hierarchy 33, container 1 |
| Búnaðarbálkur | subpages | collection | 25 | 19 | 0 | container 25 |
| Bárðar saga Snæfellsáss | subpages | book | 22 | 0 | 0 | container 22 |
| Ragnars saga loðbrókar | subpages | book | 20 | 0 | 0 | hierarchy 19, container 1 |
| Tómas frændi | subpages | book | 20 | 0 | 0 | container 20 |
| Rímur af Núma kóngi Pompilssyni (1835) | subpages | book | 20 | 20 | 2 | container 20 |

## 2. Exclusions (step 1)

| reason | pages |
|---|---|
| versions | 4 |
| disambiguation | 3 |

## 3. Patterns A–I (WS§1)

|  | pattern | count |
|---|---|---|
| A | collection of root pages (`structure = linked`) | 2 works, 5 parts |
| B | mixed subpages and root pages (`mixed`) | 1 works, 3,058 parts |
| C | work with an oversized page (> oversized_bytes, transcluded scan pages included) | 5 works |
| D | missing root, virtual root (`virtual`) | 2 works, 2 parts |
| E | texts inside a subpage collection reached from a disambiguation or versions page | 0 parts (0 searchable); all searchable subpage texts: 3,221 |
| F | whole-text duplicate subpages (excluded) | 0 pages |
| G | reference works (type `reference`) | 1 works, 3,058 parts; 0 pages excluded by title prefix |
| H | deep nesting (parts at depth ≥ 2, i.e. three levels below the root) | 1 works |
| I | several editions: works with an other-versions template | 3 works; 4 versions pages excluded |

Tree depth of multi-part works (levels below the root): 1: 29, 2: 2, 6: 1.

## 4. Parent rules and order sources (steps 1b, 1c)

| parent rule | pages |
|---|---|
| container | 4,291 |
| hierarchy | 426 |
| own | 254 |

| order source (nodes with ≥ 2 children) | nodes |
|---|---|
| toc | 89 |
| chain | 45 |
| natural | 3 |

2 of the `natural` nodes have a TOC that lists only some of their children (the TOC order did not cover all children).

Root pages attached by their header's container link: 6; most frequent containers: `Gísls þáttur Illugasonar` 3; `Gull-Ásu-Þórðar þáttur` 2; `Íslenzkar þjóðsögur og æfintýri/Goðfræðisögur` 1.

Pages attached by TOC membership (rule 3): 0; most frequent TOC pages: .

## 5. Container links that fail rule 1

3 of 4,294 pages whose header carries a container link (0.1 %) point to:

- outside-ns0: 0
- excluded: 0
- missing: 3 (e.g. `Íslenzkar þjóðsögur og æfintýri/Goðfræðisögur/Sækýr úr Lagarfljóti` → `Íslenskar þjóðsögur og æfintýri`, `Íslenzkar þjóðsögur og æfintýri/Goðfræðisögur/Gilitrutt` → `Íslenzkar þjóðsögur og ævintýri`, `Íslenzkar þjóðsögur og æfintýri/Goðfræðisögur/Naddi` → `Íslenzkar þjóðsögur og ævintýri`)

## 6. Prev/next chains across Index boundaries

Of 100 next-links between pages that both have an Index, 0 (0.0 %) cross to a different Index.

Root-page works that sit on a prev/next chain but stay separate works (no container, not in a unique TOC; WS§8 item 5): 5.

## 7. Orphan trees, oversized pages, red links

**Orphan trees** (virtual roots): 2, holding 2 parts. Examples: `Rit þeß Konúngliga Islenzka Lærdóms-Lista Félags` (1); `Egill Skalla-Grímsson` (1).

**Oversized pages** (> 300,000 bytes of wikitext including transcluded scan pages): 5. Largest: `Galdra-Loftur: leikrit í þremur þáttum` 0.7 MB; `Fölvar Rósir` 0.6 MB; `Rímur af Núma kóngi Pompilssyni (1835)` 0.5 MB; `Tómas frændi` 0.4 MB; `Dægradvöl` 0.3 MB.

**Red links in TOCs** (missing parts): 3 multi-part works have red links, 11 in total; median share of the TOC 9%. Most missing: `Dægradvöl` 7 of 9; `Íslenzkar þjóðsögur og æfintýri` 2 of 3060; `Rímur af Núma kóngi Pompilssyni (1835)` 2 of 22.

## 8. Colon prefixes with ≥ 100 root pages

None.

## 9. Quality and completeness (PLAN.md 5.4)

**Hard exclusions**: 6 works — under-300-bytes 5, no-text 1.

| tier | works | share | texts in them | source |
|---|---|---|---|---|
| validated | 1 | 0.4 % | 0 | page-levels 1 |
| proofread | 6 | 2.4 % | 82 | page-levels 6 |
| unproofread | 0 | 0.0 % | 0 |  |
| unknown | 243 | 97.2 % | 3,145 | none 243 |

1 works are proofread by the ≥ 90 % rule. 0 of the 0 unproofread works have no page above level 1 (raw OCR).

**Incomplete**: 4 works — missing-parts 4.
Works with missing scan pages inside a transcluded range: 0 (0 pages).
Wikisource's own translations (labelled, kept): 0.

Index progress values: `V` 3, `C` 3, `T` 1.

**Unknown works** (no signal): 243 in the catalog after the junk rules; the junk rules removed 5 (under-300-bytes 5).
Size: median 3,588 bytes, p10 492, p90 66,831; multi-part 25.

30 random unknown works:

- `Halla` (subpages, 339,032 B): style="width:100%;" valign="top" \| 1. kafli 2. kafli 3. kafli 4. kafli 5. kafli 6. kafli 7. kafli 8. kafli 9. kafli 10. kafli 11. kafli 12. kafli
- `Odds þáttur Ófeigssonar` (single, 11,338 B): Eitt sumar kom af Íslandi Oddur sonur Ófeigs Skíðasonar og kom norður við Finnmörk og var þar of veturinn. Þá var Haraldur konungur yfir Noregi. Og er þeir sigl
- `Allt fram streymir` (single, 590 B): Allt fram streymir endalaust, ár og dagar líða. Nú er komið hrímkalt haust, horfin sumars blíða. Fölna grös, en blikna blóm, af björkum laufin detta. Dauðalegum
- `Máninn hátt á himni skín` (single, 2,380 B): Máninn hátt á himni skín er íslenskur vikivaki sem oft er sunginn á þrettándanum, síðasta dag jóla eða á gamlárskvöld. Höfundur kvæðisins er Jón Ólafsson (1850-
- `Ellikvæði` (single, 804 B): Ævi sína enginn veit enda fyrir dægur, ellin margan illan beit, sem ungur þóttist frægur. Flestra dvína herlegheit, þó hárin gráu klæða. Mörg er heimsins mæða. 
- `Brúardrápa` (single, 2,674 B): 1. Þunga sigursöngva söng hér elfan löngum. Köld í voðaveldi vegu sleit hún sveita. Nú er vort, að syngja sigurljóðin, sigruð stynja rembilátu flóðin. Nú eru lo
- `Blaðamannaávarpið` (single, 2,897 B): Ávarp til Íslendinga Vegna þess hvernig stjórnmál Íslands horfir nú við, höfum vér undirritaðir stjórnendur íslenzkra blaða komið oss saman um að veita fylgi vo
- `Fjallið Skjaldbreiður` (single, 3,110 B): 300px\|right\|Fjallið Skjaldbreiður séð frá Þingvöllum. Fanna skautar faldi háum fjallið, allra hæða val, hrauna veitir bárum bláum breiðan fram um heiðardal. Lön
- `Stúfs þáttur hinn meiri` (single, 9,301 B): Maður hét Stúfur. Hann var sonur Þórðar kattar en hann var sonur Þórðar Ingunnarsonar og Guðrúnar Ósvífursdóttur. Stúfur var mikill maður og sjónfríður og manna
- `Sorg (Matthías Jochumsson)` (single, 2,166 B): Heim til að bjarga þér hleypti ég skeið, og hirti ekki um storminn né æginn; skein þá öll Esjan svo skínandi, breið, nema skugginn stóð rétt yfir bæinn; frá ski
- `Áskorun til alþingis um sjónvarpsmál` (single, 4,713 B): Áskorun til alþingis um sjónvarpsmál Vér undirritaðir alþingiskjósendur teljum á ýmsan hátt varhugavert, auk þess sem það er vansæmandi fyrir Íslendinga sem sjá
- `Bersöglisvísur` (single, 4,826 B): eftir Sighvat Þórðarson 1. Fregnk, at suðr með Sygnum Sigvatr hefr gram lattan folkorrostu at freista. Ferk, ef þó skulum berjask. F&#491;rum í vó&#808;pn ok ve
- `Ákæra saksóknara Alþingis á hendur Geir Hilmari Haarde` (single, 4,631 B): ÁKÆRA SAKSÓKNARI ALÞINGIS gjörir kunnugt: Að samkvæmt ákvörðun Alþingis, á grundvelli 14. gr. stjórnarskrár lýðveldisins Íslands nr. 33 17. júni 1944, sbr. 13. 
- `Ölkvæði II` (single, 1,994 B): Vínið held eg bezt brent bæta ólysti, ef vel er ákent það ískrar í brysti, mjaðar staup sé með spent, svo menn verði ótvistir, öllum er öl hent, ef þá dátt þyrs
- `Einar Andrésson` (single, 8,312 B): Mokfiskið hverja vertíðina eftir aðra og ár eftir ár, það dró menn suður að sjónum. Einar Andrésson var einn af þeim mönnum, sem ekki höfðu neina eiru nema koma
- `Leiðarvísir í ástamálum II. fyrir ungar stúlkur` (single, 26,144 B): center\|400px\|border=1 MADAMA TOBBA LEIÐARVÍSIR Í ÁSTAMÁLUM II. FYRIR UNGAR STÚLKUR REYKJAVÍK BÓKAFÉLAGIÐ NÝJA 1922 FÉLAGSPRENTSMIÐJAN Leiðarvísir í ástamálum: I
- `Heimir` (single, 2,215 B): 
- `Gunnars þáttur Þiðrandabana` (single, 25,670 B): 1. kafli=== Ketill hét maður og var kallaður þrymur. Hann var Þiðrandason og bjó í Njarðvík. Kona hans hét Þorgerður. Þorkell og Eyjólfur hétu synir þeirra. Þar
- `Grasaferð` (single, 27,863 B): „Systir góð! sérðu það sem ég sé?“ Það var eitt kvöld um vorið, við komum af stekknum, að ég sagði þetta við systur mína, fimmtán vetra gamla, og klappaði saman
- `Baldurs draumar` (single, 2,879 B): 1 Senn váru Æsir allir á þingi ok Ásynjur allar á máli, ok um þat réðu ríkir tívar, hví væri Baldri ballir draumar. 2 Upp reis Óðinn, alda gautr, ok hann á Slei
- `Jómsvíkinga saga` (subpages, 247,264 B): style="width:100%" \| valign="top" \| 1. kafli 2. kafli 3. kafli 4. kafli 5. kafli 6. kafli 7. kafli 8. kafli 9. kafli 10. kafli 11. kafli 12. kafli - Frá Pálnató
- `Svaða þáttur og Arnórs kerlingarnefs` (single, 10,820 B): 1. kafli=== Mikil og margföld er miskunn allsvaldanda guðs í öllum hlutum og háleitur hans dómur er hann lætur öngan góðgerning fyrirfarast heldur kallar hann e
- `Þórðar saga Geirmundarsonar` (single, 94,840 B): Í þann tíma er Haraldur hárfagri réð Noregi, bjó sá maður í Orkadal, er Geirmundur hét; hann var hersir að nafnbót og höfðu langfeðgar hans verið jarlar og alda
- `Komdu hérna krílið mitt` (single, 344 B): Komdu hérna krílið mitt. Komdu litla morið. Enn er liðið ekki þitt æskublíða vorið.
- `Völuspá` (single, 10,771 B): 250px\|right 1. Hljóðs bið ek allar helgar kindir, meiri ok minni mögu Heimdallar; viltu, at ek, Valföðr! vel framtelja forn spjöll fíra, þau er fremst um man. 2
- `Illugadrápa` (single, 3,563 B): eftir Stephan G. Stephansson I. Gjálpandi bárurnar gljáðu af deginum, glampaði dagsbrúnin austur á leginum. Stórveðra hrollur var enn þá í öldunum, ypptu við kl
- `Draumkvæði (Stjúpmóðurkvæði)` (single, 2,778 B): Draumkvæði (Stjúpmóðurkvæði) er íslenskt danskvæði eða sagnadans. 1. FAGURT SYNGUR svanurinn um sumarlanga tíð- þá mun lyst að leika sér, mín liljan fríð! og fa
- `Íslendings þáttur sögufróða` (single, 2,786 B): Svo barst að eitthvert sumar að einn íslenskur maður, ungur og frálegur, kom til konungs og bað hann ásjá. Konungur spurði ef hann kynni nokkverja fræði en hann
- `Hulduljóð` (single, 8,020 B): Skáld er ég ei, en huldukonan kallar og kveða biður hyggjuþungan beim. Mun ég því sitja, meðan degi hallar og mæddur smali fénu kemur heim, þar sem að háan hama
- `Stormur` (single, 1,137 B): Ég elska þig, stormur, sem geisar um grund og gleðiþyt vekur í blaðstyrkum lund, en gráfeysknu kvistina bugar og brýtur og bjarkirnar treystir um leið og þú þýt

**Default view** (validated or proofread, complete, not reference): 5 works with 60 texts; with unknown works shown: 246 works (241 texts). Works in the default view whose pages are all level 1: 0. Current setting: `show_unknown_by_default = None`.

## 10. Parts table size (WS§5.2)

| parts_cap | works with catalog parts | parts rows | parts table, gzipped | parts_fts, gzipped |
|---|---|---|---|---|
| 500 | 30 | 835 | 0.04 MB | 0.01 MB |
| 2,000 | 31 | 1,688 | 0.07 MB | 0.01 MB |
| ∞ (all, reference works included) | 32 | 4,747 | 0.24 MB | 0.06 MB |
| 2,000, compact encoding | 31 | 1,688 | 0.07 MB | 0.01 MB |

Rows count multi-part works only, one row per part plus the root row. 218 single-page works would add one row each if stored. Sizes are a SQLite file holding only the table (with its index) at gzip level 9. The compact encoding (not in WS§5.1, measured for the cap decision) stores a part's page_title as the suffix after the work root's title where possible, display_title only when it differs from the last title segment, and no per-part quality_pages.

## 11. Named cases (PLAN.md WP1 acceptance, WS§9)

None for this wiki.

## 13. Implementation notes

- When a page's container link names one of its title ancestors, the tree parent is its deepest existing ancestor below that container (is and en subpages often name the root, skipping an existing section page).
- The Index TOC field (e.g. no `Sammendrag`) counts as TOC links only for the page its Index title field links to; otherwise every member would look TOC-like.
- Display titles come from `current`/`section`/`TITEL`, else the link text in the parent's TOC, else the last title segment. A work's first part row is its root (`front` above 400 characters of own text, else `toc`); `part_count` excludes it.
- Missing parts are red links in the TOCs of the work's root and volumes: subpages of the root always, other titles only in linked or mixed works and on TOC-like pages.
- Junk rules (cleanup or deletion template, placeholder, under 300 bytes) apply to works without scan pages and without any quality signal.

## 12. Random samples for eyeballing (30 per pattern)

### A: collections of root pages (2)

1. `Gull-Ásu-Þórðar þáttur`: linked, collection, 2 parts, 0 missing, order toc, unknown
    - toc: Gull-Ásu-Þórðar þáttur
    - text: Gull-Ásu-Þórðar þáttur (eftir AM 518 4to) [container]
    - text: Gull-Ásu-Þórðar þáttur (eftir Morkinskinnu) [container]
1. `Gísls þáttur Illugasonar`: linked, collection, 3 parts, 0 missing, order toc, unknown
    - toc: Gísls þáttur Illugasonar
    - text: Gísls þáttur Illugasonar (eftir Huldu og Hrokkinskinnu) [container]
    - text: Gísls þáttur Illugasonar (úr A-gerð Jóns sögu helga) [container]
    - text: Gísls þáttur Illugasonar (úr B-gerð Jóns sögu helga) [container]

### B: mixed (1)

1. `Íslenzkar þjóðsögur og æfintýri`: mixed, reference, 3,058 parts, 2 missing, order toc, unknown
    - toc: Íslenzkar þjóðsögur og æfintýri
    - text: Formáli 1. útgáfu (`Íslenzkar þjóðsögur og æfintýri/Formáli 1. útgáfu`) [container]
    - text: Formáli Jóns Árnasonar (`Íslenzkar þjóðsögur og æfintýri/Formáli Jóns Árnasonar`) [container]
    - volume: Fyrsti flokkur: Goðfræðisögur (`Íslenzkar þjóðsögur og æfintýri/Goðfræðisögur`) [container]
      - text: Uppruni álfa (`Íslenzkar þjóðsögur og æfintýri/Goðfræðisögur/Uppruni álfa (inngangur)`) [container]
      - text: Huldumanna genesis [container]
      - text: Uppruni álfa (`Íslenzkar þjóðsögur og æfintýri/Uppruni álfa`) [container]
      - text: Ýmsar sagnir um uppruna álfa (`Íslenzkar þjóðsögur og æfintýri/Goðfræðisögur/Ýmsar sagnir um uppruna álfa`) [container]
      - text: Uppruni álfa (`Íslenzkar þjóðsögur og æfintýri/Goðfræðisögur/Uppruni álfa 2`) [container]
      - text: Álfur og Alvör (`Íslenzkar þjóðsögur og æfintýri/Goðfræðisögur/Álfur og Alvör`) [container]
      - text: Tilorðning huldufólks (`Íslenzkar þjóðsögur og æfintýri/Goðfræðisögur/Tilorðning huldufólks`) [container]
      - text: Enn um uppruna álfa (`Íslenzkar þjóðsögur og æfintýri/Goðfræðisögur/Enn um uppruna álfa`) [container]
    - … 3,047 more

### D: virtual roots (2)

1. `Egill Skalla-Grímsson`: virtual, collection, 1 parts, 0 missing, order single, unknown
    - text: Lausavísur og brot (`Egill Skalla-Grímsson/Lausavísur og brot`)
1. `Rit þeß Konúngliga Islenzka Lærdóms-Lista Félags`: virtual, periodical-issue, 1 parts, 0 missing, order single, proofread
    - text: Musteri Mannordsins (`Rit þeß Konúngliga Islenzka Lærdóms-Lista Félags/Musteri Mannordsins`)

### E: subpage texts reached from a disambiguation or versions page (0)


### all searchable subpage texts (chapter titles that the label patterns miss show up here) (3,221)

1. text “„Nýtt er mér þetta“” (`Íslenzkar þjóðsögur og æfintýri/Kímnisögur/Nýtt er mér þetta`), part 2877 of `Íslenzkar þjóðsögur og æfintýri` (reference, 3,058 parts, 2,964 texts)
1. text “Féndur í skreiðarferð” (`Íslenzkar þjóðsögur og æfintýri/Galdrasögur/Féndur í skreiðarferð`), part 1521 of `Íslenzkar þjóðsögur og æfintýri` (reference, 3,058 parts, 2,964 texts)
1. text “Móðuharðindin og Norðlendingar” (`Íslenzkar þjóðsögur og æfintýri/Viðburðasögur/Móðuharðindin og norðlendingar`), part 2309 of `Íslenzkar þjóðsögur og æfintýri` (reference, 3,058 parts, 2,964 texts)
1. text “Þórir og Skúmur” (`Íslenzkar þjóðsögur og æfintýri/Útilegumannasögur/Þórir og Skúmur`), part 2467 of `Íslenzkar þjóðsögur og æfintýri` (reference, 3,058 parts, 2,964 texts)
1. text “Reykjavíkurtjörn” (`Íslenzkar þjóðsögur og æfintýri/Galdrasögur/Reykjavíkurtjörn`), part 1399 of `Íslenzkar þjóðsögur og æfintýri` (reference, 3,058 parts, 2,964 texts)
1. text “Brennisteinn” (`Íslenzkar þjóðsögur og æfintýri/Náttúrusögur/Brennisteinn`), part 2110 of `Íslenzkar þjóðsögur og æfintýri` (reference, 3,058 parts, 2,964 texts)
1. text “„Þú ert ekki búin að bíta úr nálinni“” (`Íslenzkar þjóðsögur og æfintýri/Draugasögur/Þú ert ekki búin að bíta úr nálinni`), part 619 of `Íslenzkar þjóðsögur og æfintýri` (reference, 3,058 parts, 2,964 texts)
1. text “Halldór og Gestur” (`Íslenzkar þjóðsögur og æfintýri/Kímnisögur/Halldór og Gestur`), part 2895 of `Íslenzkar þjóðsögur og æfintýri` (reference, 3,058 parts, 2,964 texts)
1. text “Raunir Helgu þjónustustúlku” (`Íslenzkar þjóðsögur og æfintýri/Útilegumannasögur/Raunir Helgu þjónustustúlku`), part 2573 of `Íslenzkar þjóðsögur og æfintýri` (reference, 3,058 parts, 2,964 texts)
1. text “Ausa á Stóru-Þverá” (`Íslenzkar þjóðsögur og æfintýri/Galdrasögur/Ausa á Stóru-Þverá`), part 1406 of `Íslenzkar þjóðsögur og æfintýri` (reference, 3,058 parts, 2,964 texts)
1. text “Glófarnir” (`Íslenzkar þjóðsögur og æfintýri/Galdrasögur/Glófarnir`), part 1611 of `Íslenzkar þjóðsögur og æfintýri` (reference, 3,058 parts, 2,964 texts)
1. text “Jón teymir hvalkálf” (`Íslenzkar þjóðsögur og æfintýri/Galdrasögur/Jón teymir hvalkálf`), part 1758 of `Íslenzkar þjóðsögur og æfintýri` (reference, 3,058 parts, 2,964 texts)
1. text “Nykur á Lágheiði” (`Íslenzkar þjóðsögur og æfintýri/Goðfræðisögur/Nykur á Lágheiði`), part 421 of `Íslenzkar þjóðsögur og æfintýri` (reference, 3,058 parts, 2,964 texts)
1. text “Sagan af Sveini og Helgu” (`Íslenzkar þjóðsögur og æfintýri/Útilegumannasögur/Sagan af Sveini og Helgu`), part 2553 of `Íslenzkar þjóðsögur og æfintýri` (reference, 3,058 parts, 2,964 texts)
1. text “Keldusvín” (`Íslenzkar þjóðsögur og æfintýri/Náttúrusögur/Keldusvín (2)`), part 2041 of `Íslenzkar þjóðsögur og æfintýri` (reference, 3,058 parts, 2,964 texts)
1. text “Draugurinn með selspartinn” (`Íslenzkar þjóðsögur og æfintýri/Viðbætir/Draugurinn með selspartinn`), part 3014 of `Íslenzkar þjóðsögur og æfintýri` (reference, 3,058 parts, 2,964 texts)
1. text “Farmannalög” (`Jónsbók (2004)/Farmannalög`), part 12 of `Jónsbók (2004)` (collection, 13 parts, 13 texts)
1. text “Tröllskessan og taflið” (`Íslenzkar þjóðsögur og æfintýri/Goðfræðisögur/Tröllskessan og taflið`), part 508 of `Íslenzkar þjóðsögur og æfintýri` (reference, 3,058 parts, 2,964 texts)
1. text “Reimleikar” (`Íslenzkar þjóðsögur og æfintýri/Draugasögur/Reimleikar`), part 697 of `Íslenzkar þjóðsögur og æfintýri` (reference, 3,058 parts, 2,964 texts)
1. text “Mamúð soldán og vezír hans” (`Þúsund og ein nótt/Mamúð soldán og vezír hans`), part 15 of `Þúsund og ein nótt` (collection, 46 parts, 46 texts)
1. text “Sigurður blindi” (`Íslenzkar þjóðsögur og æfintýri/Ævintýri/Sigurður blindi`), part 1091 of `Íslenzkar þjóðsögur og æfintýri` (reference, 3,058 parts, 2,964 texts)
1. text “Barnaber” (`Íslenzkar þjóðsögur og æfintýri/Draugasögur/Barnaber`), part 807 of `Íslenzkar þjóðsögur og æfintýri` (reference, 3,058 parts, 2,964 texts)
1. text “Kvennagiftingar” (`Járnsíða og kristinréttur Árna Þorlákssonar/Kvennagiftingar`), part 4 of `Járnsíða og kristinréttur Árna Þorlákssonar` (collection, 10 parts, 10 texts)
1. text “Kjartan í Gerðakoti og Jón sterki” (`Íslenzkar þjóðsögur og æfintýri/Viðburðasögur/Kjartan í Gerðakoti og Jón sterki`), part 2413 of `Íslenzkar þjóðsögur og æfintýri` (reference, 3,058 parts, 2,964 texts)
1. text “Álfar á jólanótt” (`Íslenzkar þjóðsögur og æfintýri/Goðfræðisögur/Álfar á jólanótt`), part 369 of `Íslenzkar þjóðsögur og æfintýri` (reference, 3,058 parts, 2,964 texts)
1. text “Brynjubæn Sæmundar fróða (í nauðum, bágindum og húsaskemmdum)” (`Íslenzkar þjóðsögur og æfintýri/Galdrasögur/Brynjubæn Sæmundar fróða (í nauðum, bágindum og háskasemdum)`), part 1283 of `Íslenzkar þjóðsögur og æfintýri` (reference, 3,058 parts, 2,964 texts)
1. text “Stelpan í Hólmakirkjugarði” (`Íslenzkar þjóðsögur og æfintýri/Draugasögur/Stelpan í Hólmakirkjugarði`), part 650 of `Íslenzkar þjóðsögur og æfintýri` (reference, 3,058 parts, 2,964 texts)
1. text “Móðólfur í Móðólfsfelli” (`Íslenzkar þjóðsögur og æfintýri/Goðfræðisögur/Móðólfur í Móðólfsfelli`), part 541 of `Íslenzkar þjóðsögur og æfintýri` (reference, 3,058 parts, 2,964 texts)
1. text “Sels-Móri eða Þorgarður” (`Íslenzkar þjóðsögur og æfintýri/Draugasögur/Sels-Móri eða Þorgarður`), part 911 of `Íslenzkar þjóðsögur og æfintýri` (reference, 3,058 parts, 2,964 texts)
1. text “Leiðið í Skriðuklausturskirkjugarði” (`Íslenzkar þjóðsögur og æfintýri/Draugasögur/Leiðið í Skriðuklausturskirkjugarði`), part 653 of `Íslenzkar þjóðsögur og æfintýri` (reference, 3,058 parts, 2,964 texts)

### C: oversized (5)

1. `Galdra-Loftur: leikrit í þremur þáttum`: subpages, book, 4 parts, 0 missing, order chain, proofread
    - front: Galdra-Loftur: leikrit í þremur þáttum
    - text: Fyrsti þáttur (`Galdra-Loftur: leikrit í þremur þáttum/Fyrsti þáttur`) [container]
    - text: Annar þáttur (`Galdra-Loftur: leikrit í þremur þáttum/Annar þáttur`) [container]
    - text: Þriðji þáttur (`Galdra-Loftur: leikrit í þremur þáttum/Þriðji þáttur`) [container]
    - text: Auglýsingar (`Galdra-Loftur: leikrit í þremur þáttum/Auglýsingar`) [container]
1. `Tómas frændi`: subpages, book, 20 parts, 0 missing, order chain, validated
    - front: Tómas frændi
    - chapter: I. (`Tómas frændi/I`) [container]
    - chapter: II. (`Tómas frændi/II`) [container]
    - chapter: III. (`Tómas frændi/III`) [container]
    - chapter: IV. (`Tómas frændi/IV`) [container]
    - chapter: V. (`Tómas frændi/V`) [container]
    - chapter: VI. (`Tómas frændi/VI`) [container]
    - chapter: VII. (`Tómas frændi/VII`) [container]
    - chapter: VIII. (`Tómas frændi/VIII`) [container]
    - chapter: IX. (`Tómas frændi/IX`) [container]
    - chapter: X. (`Tómas frændi/X`) [container]
    - chapter: XI. (`Tómas frændi/XI`) [container]
    - … 9 more
1. `Rímur af Núma kóngi Pompilssyni (1835)`: subpages, book, 20 parts, 2 missing, order chain, proofread
    - front: Rímur af Núma kóngi Pompilssyni (1835)
    - text: Tileinkun (`Rímur af Núma kóngi Pompilssyni (1835)/Tileinkun`) [container]
    - text: Lítill Formáli (`Rímur af Núma kóngi Pompilssyni (1835)/Lítill Formáli`) [container]
    - text: Fyrsta Ríma (`Rímur af Núma kóngi Pompilssyni (1835)/Fyrsta Ríma`) [container]
    - text: Ønnur Ríma (`Rímur af Núma kóngi Pompilssyni (1835)/Ønnur Ríma`) [container]
    - text: Þriðja Ríma (`Rímur af Núma kóngi Pompilssyni (1835)/Þriðja Ríma`) [container]
    - text: Fjórða Ríma (`Rímur af Núma kóngi Pompilssyni (1835)/Fjórða Ríma`) [container]
    - text: Fimta Ríma (`Rímur af Núma kóngi Pompilssyni (1835)/Fimta Ríma`) [container]
    - text: Sjøtta Ríma (`Rímur af Núma kóngi Pompilssyni (1835)/Sjøtta Ríma`) [container]
    - text: Sjøunda Ríma (`Rímur af Núma kóngi Pompilssyni (1835)/Sjøunda Ríma`) [container]
    - text: Attunda Ríma (`Rímur af Núma kóngi Pompilssyni (1835)/Attunda Ríma`) [container]
    - text: Níunda Ríma (`Rímur af Núma kóngi Pompilssyni (1835)/Níunda Ríma`) [container]
    - … 9 more
1. `Dægradvöl`: subpages, book, 2 parts, 7 missing, order chain, proofread
    - front: Dægradvöl
    - text: Formáli (`Dægradvöl/Formáli`) [container]
    - text: 1826—1835 (`Dægradvöl/1826—1835`) [container]
1. `Fölvar Rósir`: subpages, book, 43 parts, 0 missing, order chain, proofread
    - front: Fölvar Rósir
    - text: Hekla (`Fölvar Rósir/Hekla`) [container]
    - text: Heilabrot (`Fölvar Rósir/Heilabrot`) [container]
    - text: Skilnaður (`Fölvar Rósir/Skilnaður`) [container]
    - text: Brot (`Fölvar Rósir/Brot`) [container]
    - text: Leiðsla (`Fölvar Rósir/Leiðsla`) [container]
    - text: Afmæliskveðja (`Fölvar Rósir/Afmæliskveðja`) [container]
    - text: Grafskrift (`Fölvar Rósir/Grafskrift`) [container]
    - text: Ávarp til Stúkunnar „Einingin“ (`Fölvar Rósir/Ávarp til Stúkunnar „Einingin“`) [container]
    - text: Píslarþankar (`Fölvar Rósir/Píslarþankar`) [container]
    - text: Vornótt (`Fölvar Rósir/Vornótt`) [container]
    - text: Magnús Finnson (`Fölvar Rósir/Magnús Finnson`) [container]
    - … 32 more

### G: reference works (1)

1. `Íslenzkar þjóðsögur og æfintýri`: mixed, reference, 3,058 parts, 2 missing, order toc, unknown
    - toc: Íslenzkar þjóðsögur og æfintýri
    - text: Formáli 1. útgáfu (`Íslenzkar þjóðsögur og æfintýri/Formáli 1. útgáfu`) [container]
    - text: Formáli Jóns Árnasonar (`Íslenzkar þjóðsögur og æfintýri/Formáli Jóns Árnasonar`) [container]
    - volume: Fyrsti flokkur: Goðfræðisögur (`Íslenzkar þjóðsögur og æfintýri/Goðfræðisögur`) [container]
      - text: Uppruni álfa (`Íslenzkar þjóðsögur og æfintýri/Goðfræðisögur/Uppruni álfa (inngangur)`) [container]
      - text: Huldumanna genesis [container]
      - text: Uppruni álfa (`Íslenzkar þjóðsögur og æfintýri/Uppruni álfa`) [container]
      - text: Ýmsar sagnir um uppruna álfa (`Íslenzkar þjóðsögur og æfintýri/Goðfræðisögur/Ýmsar sagnir um uppruna álfa`) [container]
      - text: Uppruni álfa (`Íslenzkar þjóðsögur og æfintýri/Goðfræðisögur/Uppruni álfa 2`) [container]
      - text: Álfur og Alvör (`Íslenzkar þjóðsögur og æfintýri/Goðfræðisögur/Álfur og Alvör`) [container]
      - text: Tilorðning huldufólks (`Íslenzkar þjóðsögur og æfintýri/Goðfræðisögur/Tilorðning huldufólks`) [container]
      - text: Enn um uppruna álfa (`Íslenzkar þjóðsögur og æfintýri/Goðfræðisögur/Enn um uppruna álfa`) [container]
    - … 3,047 more

### H: deep nesting (1)

1. `Íslenzkar þjóðsögur og æfintýri`: mixed, reference, 3,058 parts, 2 missing, order toc, unknown
    - toc: Íslenzkar þjóðsögur og æfintýri
    - text: Formáli 1. útgáfu (`Íslenzkar þjóðsögur og æfintýri/Formáli 1. útgáfu`) [container]
    - text: Formáli Jóns Árnasonar (`Íslenzkar þjóðsögur og æfintýri/Formáli Jóns Árnasonar`) [container]
    - volume: Fyrsti flokkur: Goðfræðisögur (`Íslenzkar þjóðsögur og æfintýri/Goðfræðisögur`) [container]
      - text: Uppruni álfa (`Íslenzkar þjóðsögur og æfintýri/Goðfræðisögur/Uppruni álfa (inngangur)`) [container]
      - text: Huldumanna genesis [container]
      - text: Uppruni álfa (`Íslenzkar þjóðsögur og æfintýri/Uppruni álfa`) [container]
      - text: Ýmsar sagnir um uppruna álfa (`Íslenzkar þjóðsögur og æfintýri/Goðfræðisögur/Ýmsar sagnir um uppruna álfa`) [container]
      - text: Uppruni álfa (`Íslenzkar þjóðsögur og æfintýri/Goðfræðisögur/Uppruni álfa 2`) [container]
      - text: Álfur og Alvör (`Íslenzkar þjóðsögur og æfintýri/Goðfræðisögur/Álfur og Alvör`) [container]
      - text: Tilorðning huldufólks (`Íslenzkar þjóðsögur og æfintýri/Goðfræðisögur/Tilorðning huldufólks`) [container]
      - text: Enn um uppruna álfa (`Íslenzkar þjóðsögur og æfintýri/Goðfræðisögur/Enn um uppruna álfa`) [container]
    - … 3,047 more

### I: works with other editions (3)

1. `Grágás: Lagasafn íslenska þjóðveldisins`: subpages, collection, 14 parts, 0 missing, order chain, unknown
    - toc: Grágás: Lagasafn íslenska þjóðveldisins
    - text: Kristinna laga þáttur (`Grágás: Lagasafn íslenska þjóðveldisins/Kristinna laga þáttur`) [container]
    - text: Erfðaþáttur (`Grágás: Lagasafn íslenska þjóðveldisins/Erfðaþáttur`) [container]
    - text: Ómagabálkur (`Grágás: Lagasafn íslenska þjóðveldisins/Ómagabálkur`) [container]
    - text: Festaþáttur (`Grágás: Lagasafn íslenska þjóðveldisins/Festaþáttur`) [container]
    - text: Um fjárleigur (`Grágás: Lagasafn íslenska þjóðveldisins/Um fjárleigur`) [container]
    - text: Vígslóði (`Grágás: Lagasafn íslenska þjóðveldisins/Vígslóði`) [container]
    - text: Landabrigðisþáttur (`Grágás: Lagasafn íslenska þjóðveldisins/Landabrigðisþáttur`) [container]
    - text: Þingskapaþáttur (`Grágás: Lagasafn íslenska þjóðveldisins/Þingskapaþáttur`) [container]
    - text: Baugatal (`Grágás: Lagasafn íslenska þjóðveldisins/Baugatal`) [container]
    - text: Lögsögumannsþáttur (`Grágás: Lagasafn íslenska þjóðveldisins/Lögsögumannsþáttur`) [container]
    - text: Lögréttuþáttur (`Grágás: Lagasafn íslenska þjóðveldisins/Lögréttuþáttur`) [container]
    - … 3 more
1. `Jónsbók (2004)`: subpages, collection, 13 parts, 0 missing, order chain, unknown
    - toc: Jónsbók (2004)
    - text: Þingfararbálkur (`Jónsbók (2004)/Þingfararbálkur`) [container]
    - text: Kristinn réttur og konungserfðir (`Jónsbók (2004)/Kristinn réttur og konungserfðir`) [container]
    - text: Konungs þegnskylda (`Jónsbók (2004)/Konungs þegnskylda`) [container]
    - text: Mannhelgi (`Jónsbók (2004)/Mannhelgi`) [container]
    - text: Kvennagiftingar (`Jónsbók (2004)/Kvennagiftingar`) [container]
    - text: Erfðatal (`Jónsbók (2004)/Erfðatal`) [container]
    - text: Framfærslubálkur (`Jónsbók (2004)/Framfærslubálkur`) [container]
    - text: Landabirgðabálkur (`Jónsbók (2004)/Landabirgðabálkur`) [container]
    - text: Búnaðarbálkur (`Jónsbók (2004)/Búnaðarbálkur`) [container]
    - text: Rekaþáttur (`Jónsbók (2004)/Rekaþáttur`) [container]
    - text: Kaupabálkur (`Jónsbók (2004)/Kaupabálkur`) [container]
    - … 2 more
1. `Járnsíða og kristinréttur Árna Þorlákssonar`: subpages, collection, 10 parts, 0 missing, order chain, unknown
    - toc: Járnsíða og kristinréttur Árna Þorlákssonar
    - text: Þingfararbálkur (`Járnsíða og kristinréttur Árna Þorlákssonar/Þingfararbálkur`) [container]
    - text: Kristindómsbálkur (`Járnsíða og kristinréttur Árna Þorlákssonar/Kristindómsbálkur`) [container]
    - text: Mannhelgi (`Járnsíða og kristinréttur Árna Þorlákssonar/Mannhelgi`) [container]
    - text: Kvennagiftingar (`Járnsíða og kristinréttur Árna Þorlákssonar/Kvennagiftingar`) [container]
    - text: Erfðatal (`Járnsíða og kristinréttur Árna Þorlákssonar/Erfðatal`) [container]
    - text: Landabrigðabálkur (`Járnsíða og kristinréttur Árna Þorlákssonar/Landabrigðabálkur`) [container]
    - text: Rekabálkur (`Járnsíða og kristinréttur Árna Þorlákssonar/Rekabálkur`) [container]
    - text: Kaupabálkur (`Járnsíða og kristinréttur Árna Þorlákssonar/Kaupabálkur`) [container]
    - text: Þjófabálkur (`Járnsíða og kristinréttur Árna Þorlákssonar/Þjófabálkur`) [container]
    - text: Kristinréttur Árna Þorlákssonar (`Járnsíða og kristinréttur Árna Þorlákssonar/Kristinréttur Árna Þorlákssonar`) [container]

