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

## Re-sample of the rule-3 extension (WP1, 2026-11-01)

Decision 4 changed de after this review: the rule-3 extension
(`headerless_toc_members`) attached 6,690 pages to 38 works (de works
26,257 → 19,660 before the bibliography exclusion). Thirty of the 38, drawn
with the seed `wp1-rule3-de` by `pipeline/wp1a/rule3_sample.py`, are listed
in `report/de-rule3-sample.md`, with the same criteria as above.

**25 of 30 correct.** The grouping itself is right in all 30: every
attached page belongs to the work, including the Topographia volumes
(samples 3–5, 7, 11, 17, 19, 24, 25, 29, 30) and the page-by-page books
(Kommersbuch, Hertz, Schedel, Ständebuch, the Passau registers). The five
errors are order, tree position and classification:

- 2 `Annalen der Physik`: articles in natural (alphabetical) order; no
  chain, TOC or scan order covers them (as before the extension).
- 9 `Allgemeines Deutsches Kommersbuch` and 23 `Untersuchungen über die
  Ausbreitung der elektrischen Kraft`: the root's editorial notes link two
  or three example pages before the table of contents, and the TOC order
  takes each link's first occurrence, so those pages come first; the
  Kommersbuch also shows "hier" as a display title (ticket in STATUS.md).
- 28 `Schwere, Elektricität und Magnetismus`: the § sections sit under
  the `Zusammenstellungen` page, which lists them (volume placement).
- 30 `Topographia Helvetiae, Rhaetiae et Valesiae`: numbered town entries
  ("1. Bulach") are chapters by the leading-numbering label rule.

Fixed while sampling: page-by-page registers showed "Seite 1", "Seite 2"
as searchable texts (samples 6, 13, 16, 20); `Seite` and `Blatt` are now
de chapter labels. Without that fix the count was 21 of 30.

Accepted by the owner on 2026-11-01 (`report/wp1-decisions.md` item 6): the
grouping is what the extension changes, and it is right in all 30; the
ordering defects are ticketed. The sv textless-TOC-root rule of the same
decision (item 5) is sampled in `report/sv-textless-toc-sample.md`: 15 of
15 correct.
