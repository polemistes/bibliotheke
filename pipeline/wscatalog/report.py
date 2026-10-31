# SPDX-License-Identifier: GPL-3.0-or-later
"""Coverage report (PLAN.md 5.2 step 7): report/{wiki}.md, the tool for
deciding where to improve the metadata mappings, also printed in CI."""

from __future__ import annotations

import sys
import time
from collections import Counter

from .survey import md_table, pct, esc


def write_report(b) -> "Path":
    from .catalog import REPORT_DIR, DEFAULT_TIERS, HIDDEN_TYPES
    works, meta, q = b.works, b.meta, b.q
    n = len(works)
    cfg = b.cfg
    lines = [f"# {b.wiki}.wikisource: catalog coverage report",
             "",
             f"Generated {time.strftime('%Y-%m-%d')} by `python -I -m wscatalog.catalog {b.wiki}` from the "
             f"dump of {b.dump_date} (PLAN.md 5.2 steps 3–8; config `pipeline/config/{b.wiki}.yaml`). "
             f"Build took {b.elapsed:.0f} s on the cached extract.",
             ""]

    def has(f):
        return sum(1 for w in works if f(meta[w.root.title], w))

    def sources(attr):
        c = Counter(getattr(meta[w.root.title], attr) or "none" for w in works)
        return ", ".join(f"{k} {v:,}" for k, v in c.most_common())

    tiers = Counter(q[w.root.title][0].tier for w in works)
    lines += ["## Summary", ""]
    rows = [
        ["works in the catalog", f"{n:,}", ""],
        ["with an author", pct(has(lambda m, w: m.authors), n), sources("author_source")],
        ["with a year", pct(has(lambda m, w: m.year is not None), n), sources("year_source")],
        ["with a genre", pct(has(lambda m, w: m.genres), n), sources("genre_source")],
        ["with a quality tier (not unknown)", pct(n - tiers.get("unknown", 0), n),
         ", ".join(f"{k} {v:,}" for k, v in tiers.most_common())],
        ["with a Wikidata item", pct(has(lambda m, w: m.qid), n), ""],
        ["with a description", pct(has(lambda m, w: m.description), n), ""],
        ["with a cover image", pct(has(lambda m, w: m.cover_url), n), ""],
        ["language other than the wiki's", pct(has(lambda m, w: m.language != cfg.language), n),
         sources("language_source")],
    ]
    lines += md_table(["", "works", "sources"], rows) + [""]

    unknown = b.show_unknown
    dv = b.default_view(False)
    du = b.default_view(True)
    texts = lambda ws: sum(w.text_count for w in ws)
    hidden = Counter(meta[w.root.title].type for w in works
                     if q[w.root.title][0].tier in DEFAULT_TIERS + (("unknown",) if unknown else ())
                     and q[w.root.title][0].complete
                     and meta[w.root.title].type in HIDDEN_TYPES)
    why = ("from the config" if b.show_unknown_source == "config" else
           "derived: unknown works " + ("outnumber" if unknown else "do not outnumber")
           + " the validated and proofread ones")
    lines += ["## Default view (PLAN.md 5.4)", "",
              f"Validated or proofread, complete, not `reference` or `document`: **{len(dv):,} works** "
              f"({texts(dv):,} texts, i.e. searchable parts; single-page works are found as works); "
              f"with unknown works: {len(du):,} works ({texts(du):,} texts). "
              f"`show_unknown_by_default` is `{str(unknown).lower()}` ({why}), so the wiki picker shows "
              f"{len(du if unknown else dv):,}. Hidden by type from that view: "
              + ", ".join(f"{t} {hidden.get(t, 0):,}" for t in HIDDEN_TYPES) + ".", ""]
    rows = []
    for t in ("validated", "proofread", "unproofread", "unknown"):
        ws = [w for w in works if q[w.root.title][0].tier == t]
        rows.append([t, f"{len(ws):,}", f"{sum(1 for w in ws if q[w.root.title][0].complete):,}"])
    lines += md_table(["tier", "works", "complete"], rows) + [""]
    inc = Counter(q[w.root.title][0].reason for w in works if not q[w.root.title][0].complete)
    if inc:
        lines += ["Incomplete by reason: " + ", ".join(f"{k} {v:,}" for k, v in inc.most_common()) + ".", ""]

    lines += ["## Types, structures, genres, languages", ""]
    for label, c in (("type", Counter(meta[w.root.title].type for w in works)),
                     ("type source", Counter(meta[w.root.title].type_source for w in works)),
                     ("structure", Counter(w.structure for w in works)),
                     ("genre", Counter(g for w in works for g in meta[w.root.title].genres)),
                     ("language", Counter(meta[w.root.title].language for w in works))):
        lines.append(f"- {label}: " + ", ".join(f"{k} {v:,}" for k, v in c.most_common(25)))
    cent = Counter((meta[w.root.title].year // 100 + 1) for w in works if meta[w.root.title].year is not None
                   and meta[w.root.title].year > 0)
    lines.append("- century: " + ", ".join(f"{k}. {v:,}" for k, v in sorted(cent.items())))
    lines.append("")

    files, counts = b.files, b.counts
    lines += ["## Catalog file", "",
              f"`{b.wiki}.sqlite.gz`: {files['gz'] / 1e6:.2f} MB gzipped, {files['sqlite'] / 1e6:.1f} MB "
              f"raw, sha256 `{files['sha256']}`. Rows: works {n:,}, parts {counts['parts']:,}, "
              f"parts_fts {counts['parts_fts']:,}, aliases {counts['aliases']:,}, authors "
              f"{counts['authors']:,}, excluded {counts['excluded']:,}.", ""]
    no_parts = [w for w in works if w.structure != "single" and not b.parts_in_catalog(w)]
    if no_parts:
        lines += [f"Works without catalog parts (over `parts_cap` {cfg.parts_cap:,}, or `reference`): "
                  f"{len(no_parts):,}: " + ", ".join(
                      f"`{esc(w.root.title)}` ({w.part_count:,})" for w in
                      sorted(no_parts, key=lambda w: -w.part_count)[:12]) + ".", ""]

    lines += ["## Exclusions", ""]
    step1 = Counter(b.res.excluded.values())
    step5 = Counter(q[w.root.title][0].excluded for w in b.res.works if q[w.root.title][0].excluded)
    lines += ["Step 1 (pages): " + ", ".join(f"{k} {v:,}" for k, v in step1.most_common()) + ".", "",
              "Step 5 (works): " + (", ".join(f"{k} {v:,}" for k, v in step5.most_common()) or "none") + ".", ""]

    # mapping work: what is left unmapped
    lines += ["## Mapping work", ""]
    cats = Counter(c for w in works if not meta[w.root.title].genres
                   for c in (w.root.rec.cats if w.root.rec else ()))
    lines += ["Most common categories on root pages of works without a genre (candidates for "
              "`metadata.genre_categories`):", ""]
    lines += [", ".join(f"{esc(c)} {v:,}" for c, v in cats.most_common(40)) or "none", ""]
    if b.wd:
        unmapped = Counter()
        for w in works:
            m = meta[w.root.title]
            for qq in (m.qid, m.work_qid):
                for prop in ("P136", "P31"):
                    for x in b.wd.claims(qq, prop):
                        if x not in cfg.wikidata_genres and x not in cfg.wikidata_types:
                            unmapped[(prop, x)] += 1
        top = unmapped.most_common(40)
        b.wd.ensure([x for (_, x), _ in top])
        lines += ["Most common unmapped Wikidata values on the works' items (candidates for "
                  "`wikidata.genres` / `wikidata.types` in default.yaml):", ""]
        lines += [", ".join(f"{p} {x} “{esc(b.wd.label(x, ('en',)) or '?')}” {v:,}"
                            for (p, x), v in top) or "none", ""]
    path = REPORT_DIR / f"{b.wiki}.md"
    REPORT_DIR.mkdir(exist_ok=True)
    path.write_text("\n".join(lines) + "\n", "utf-8")
    summary = (f"{b.wiki}: {n:,} works; author {pct(has(lambda m, w: m.authors), n)}, year "
               f"{pct(has(lambda m, w: m.year is not None), n)}, genre {pct(has(lambda m, w: m.genres), n)}, "
               f"quality {pct(n - tiers.get('unknown', 0), n)}; default view {len(du if unknown else dv):,}")
    print(summary)
    return path
