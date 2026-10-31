# SPDX-License-Identifier: GPL-3.0-or-later
"""Structure spike (2026-10-31): per-work quality tiers for nowikisource from the
pages-articles dump, plus a characterisation of works without any scan page.
Prototype of the WP1a structure survey (docs/work-structure-plan.md section 6).

    curl -A "$UA" -O https://dumps.wikimedia.org/nowikisource/latest/nowikisource-latest-pages-articles.xml.bz2
    python3 pipeline/wp0/quality_works_no.py nowikisource-latest-pages-articles.xml.bz2

Works are grouped by title hierarchy only (root-page collections are not merged).
Evidence: docs/structure/no-quality-report.txt."""
import bz2, random, re, sys
from collections import Counter, defaultdict
import xml.etree.ElementTree as ET

PAGE_NS, INDEX_NS = 104, 106
path = sys.argv[1]
ns0 = {}            # title -> dict(text, bytes, redirect)
page_level = {}     # (index, num) -> level ; index = file name, num = int page or None
index_pages = defaultdict(dict)
redirects = set()

def strip(tag): return tag.split("}")[-1]
with bz2.open(path, "rb") as f:
    for ev, el in ET.iterparse(f, events=("end",)):
        if strip(el.tag) != "page": continue
        ns = int(el.find("{*}ns").text); title = el.find("{*}title").text
        is_redirect = el.find("{*}redirect") is not None
        rev = el.find("{*}revision"); textel = rev.find("{*}text") if rev is not None else None
        text = textel.text or "" if textel is not None else ""
        if ns == 0:
            if is_redirect: redirects.add(title)
            else: ns0[title] = text
        elif ns == PAGE_NS:
            m = re.search(r'<pagequality level="(\d)"', text)
            lvl = int(m.group(1)) if m else None
            name = title.split(":", 1)[1]
            if "/" in name and name.rsplit("/", 1)[1].isdigit():
                idx, num = name.rsplit("/", 1); num = int(num)
            else:
                idx, num = name, None
            page_level[(idx, num)] = lvl
            index_pages[idx][num] = lvl
        el.clear()

print(f"ns0 non-redirect pages {len(ns0)}, ns0 redirects {len(redirects)}, Side: pages {len(page_level)}, indexes {len(index_pages)}")

PAGES_TAG = re.compile(r"<pages\s+([^>]*?)/?>", re.I)
ATTR = re.compile(r'(\w+)\s*=\s*(?:"([^"]*)"|\'([^\']*)\'|([^\s/>]+))')
SIDE_TR = re.compile(r"\{\{\s*(?:#lst:|:)?\s*Side:([^|}#]+)", re.I)
TEMPLATE = re.compile(r"\{\{\s*([^{}|#:\n][^{}|\n]*?)\s*(?:[|}]|\n)")
CATEGORY = re.compile(r"\[\[\s*Kategori:([^\]|]+)", re.I)

def levels_for(text):
    """Return list of levels ('missing' for non-existing pages) of all scan pages used by a text."""
    out = []
    for m in PAGES_TAG.finditer(text):
        attrs = {k: (a or b or c) for k, a, b, c in ATTR.findall(m.group(1))}
        idx = attrs.get("index", "").replace("_", " ").strip()
        if not idx: continue
        pages = index_pages.get(idx, {})
        fr, to = attrs.get("from"), attrs.get("to")
        if fr and fr.isdigit() and to and to.isdigit():
            rng = range(int(fr), int(to) + 1)
        elif fr and fr.isdigit():
            rng = [int(fr)]
        else:
            rng = sorted(k for k in pages if k is not None) or [None]
        for n in rng:
            out.append(pages.get(n, "missing") if (n in pages) else "missing")
    for m in SIDE_TR.finditer(text):
        name = m.group(1).replace("_", " ").strip()
        if "/" in name and name.rsplit("/", 1)[1].isdigit():
            idx, num = name.rsplit("/", 1); key = (idx, int(num))
        else:
            key = (name, None)
        out.append(page_level.get(key, "missing"))
    return out

# group ns0 pages into works by root title (title hierarchy only)
works = defaultdict(list)
for t in ns0: works[t.split("/")[0]].append(t)
virtual = sum(1 for r in works if r not in ns0)

def tier(levels):
    real = [l for l in levels if l != "missing" and l is not None]
    missing = sum(1 for l in levels if l == "missing")
    if not levels: return "unknown", missing, 0
    if not real: return "no-text-pages", missing, 0
    nontext0 = sum(1 for l in real if l == 0)
    textp = [l for l in real if l != 0]
    if not textp: return "no-text-pages", missing, nontext0
    share3 = sum(1 for l in textp if l >= 3) / len(textp)
    if all(l == 4 for l in textp): return "validated", missing, nontext0
    if all(l >= 3 for l in textp): return "proofread", missing, nontext0
    if share3 >= 0.9: return "mostly-proofread", missing, nontext0
    return "unproofread", missing, nontext0

tiers = Counter(); tiers_pages = Counter(); unknown_works = []; dab = 0
incomplete_missing = 0; tier_by_root = {}
for root, pages in works.items():
    texts = [ns0[t] for t in pages]
    if any(re.search(r"\{\{\s*Peker\b", tx, re.I) for tx in texts if tx) and len(pages) == 1:
        dab += 1; continue
    lv = []
    for tx in texts: lv += levels_for(tx)
    tr, missing, _ = tier(lv)
    tiers[tr] += 1; tiers_pages[tr] += len(pages)
    if missing: incomplete_missing += 1
    tier_by_root[root] = (tr, missing, len(lv))
    if tr == "unknown": unknown_works.append(root)

print(f"\nworks (root-title groups): {sum(tiers.values())} (+{dab} Peker pages skipped; {virtual} groups with missing root)")
print("tier                 works   pages")
for k, v in tiers.most_common(): print(f"{k:18} {v:7} {tiers_pages[k]:7}")
print(f"works with missing scan pages in a transcluded range: {incomplete_missing}")

# characterize the unknown works
print(f"\n=== {len(unknown_works)} works without any scan page ===")
sizes = sorted(sum(len(ns0[t]) for t in works[r]) for r in unknown_works)
if sizes:
    print(f"size bytes: median {sizes[len(sizes)//2]}, p10 {sizes[len(sizes)//10]}, p90 {sizes[9*len(sizes)//10]}, max {sizes[-1]}")
tpl = Counter(); cats = Counter(); multi = 0
for r in unknown_works:
    seen = set()
    for t in works[r]:
        for m in TEMPLATE.finditer(ns0[t]): seen.add(m.group(1).strip()[:40])
        for m in CATEGORY.finditer(ns0[t]): cats[m.group(1).strip()] += 1
    if len(works[r]) > 1: multi += 1
    tpl.update(seen)
print(f"works with subpages among them: {multi}")
print("top templates (works using):", tpl.most_common(30))
print("top categories:", cats.most_common(25))
random.seed(7)
for r in random.sample(unknown_works, min(25, len(unknown_works))):
    tx = ns0[r] if r in ns0 else ns0[works[r][0]]
    head = re.sub(r"\s+", " ", tx[:420])
    print(f"\n--- {r}  [{len(works[r])} page(s), {sum(len(ns0[t]) for t in works[r])} B]\n{head}")

# --- pure raw vs mixed among unproofread works; junk rules among unknown works ---
pure_raw = mixed = 0
for root, (tr, missing, n) in tier_by_root.items():
    if tr == "unproofread":
        lv = []
        for t in works[root]: lv += levels_for(ns0[t])
        textp = [l for l in lv if l not in ("missing", None, 0)]
        if textp and all(l <= 1 for l in textp): pure_raw += 1
        else: mixed += 1
print(f"\nunproofread works: pure level-1 (or 0) = {pure_raw}, mixed = {mixed}")
junk = Counter(); has_header = 0; own_translation = 0
for r in unknown_works:
    tx = " ".join(ns0[t] for t in works[r])
    if len(tx) < 300: junk["under 300 bytes"] += 1
    if re.search(r"\{\{\s*(ufullstendig|opprydning|slett(ing)?)\b", tx, re.I): junk["ufullstendig/opprydning/slett template"] += 1
    if "Ennå ikke fri" in tx: junk["'not yet free' placeholder"] += 1
    if re.search(r"\{\{\s*topp2?\b", tx, re.I): has_header += 1
    if re.search(r"oversetter\s*=\s*Wikikilden", tx, re.I): own_translation += 1
print(f"unknown works: {len(unknown_works)}; with a topp header: {has_header}; "
      f"Wikikilden's own translations: {own_translation}; junk flags: {dict(junk)}")
