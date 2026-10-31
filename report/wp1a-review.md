# WP1a eyeball review (2026-10-31)

WS§6 acceptance asks that the random samples for patterns A, B, D and E be
correct for at least 27 of 30. These are the agent's verdicts on exactly the
samples listed in section 12 of each `report/{wiki}-structure.md` (seeded
per pattern, so a rerun on the same dumps lists the same works). A sample
counts as correct when the work has the right members, the parts sit in the
right place in the tree, the order is the book's order, and texts versus
chapters are classified correctly. Sample numbers below refer to the
position in the report's list.

| wiki | A | B | D | E |
|---|---|---|---|---|
| no | 30/30 | 27/30 | 30/30 | 30/30 |
| is | 2/2 | 1/1 | 2/2 | (none) |
| de | 28/30 | 30/30 | 28/30 | 25/26 |
| fr | 30/30 | 27/30 | 29/30 | 29/30 |
| en | 30/30 | **26/30** | 30/30 | 30/30 |

All pass except en B, by one sample. The errors:

**no**
- B 1 `Fru Bovary`: each part exists twice, as a subpage and as a colon root
  page (`Fru Bovary: Første del`); both are attached by their headers.
- B 5 `Historisk Tidsskrift`, B 29 `Theologisk Tidsskrift…`: the volume
  order follows the scan file names (`scan-volumes`), which put "Andet
  Bind" first.

**de**
- A 8 `Kinder- und Haus-Märchen Band 1 (1812)`, A 23 `Tragödien nebst einem
  lyrischen Intermezzo`: alphabetical; no chain, TOC or scan order covers
  the members.
- D 7 `Wikisource` (`Wikisource/Hilf mit!`), D 21 `Zedler`
  (`Zedler/Quellen`): project pages in ns0, not texts.
- E 9: the display title "A" for `Fliegende Blätter/Inhalt Band 1`, taken
  from a letter link in the TOC.

**fr**
- B 8 `Dictionnaire raisonné du mobilier français…`, B 17 `Dictionnaire
  encyclopédique de la noblesse de France`: entries of several volumes
  interleaved in natural order.
- B 22 `Instructions aux domestiques`: a part displayed as "." (a template
  stripped from the TOC link text).
- D 12 `Proudhon - De la Capacité politique…`: a `Texte complet` whole-text
  subpage; added to fr `whole_text_subpages` after the review.
- E 24: Béranger's song "Le Chant du Cosaque" classified as a chapter
  ("Chant" is a chapter label word).

**en**
- B 3 `The Boys of Columbia High on the Ice`: front matter placed last
  (`toc-partial`).
- B 19 `The History of England from the Accession of James II`: two
  volumes' chapters interleaved (natural order).
- B 21 `The People of the Black Circle`: serial parts and chapters out of
  order.
- B 25 `A Philosophical Enquiry into the Origin of our Ideas…`: the TOC
  order starts in the middle of the book.

The remaining B errors are ordering problems of mixed collections with no
usable order signal, plus odd header data; the grouping itself is right in
every B sample but `Fru Bovary`.
