# SPDX-License-Identifier: GPL-3.0-or-later
"""Structure spike (2026-10-31): header template parameters and link targets of a page.

  inspect.py fixture <raw.html>          header templates + link summary of a fixture
  inspect.py page <domain> <title>       same for a live page (via wmhttp cache)
  inspect.py redirects <domain> <t1> ... resolve redirects for titles
  inspect.py stats <domain> ...          siteinfo statistics
"""
import html, json, re, sys
from collections import Counter
from urllib.parse import unquote
import wmhttp, parsoid

SKIP = {"BlockSatzStart", "BlockSatzEnd", "SeitePR", "SeitePR1", "References", "references",
        "Prettytable", "SperrSchrift", "Zeile", "idt", "center", "Center", "c", "ppb", "rule",
        "clear", "Interwiki-Info", "Gesprochener Text", "PD-old", "TextQuality", "featured",
        "authority control", "DEFAULTSORT", ":MediaWiki:Proofreadpage_pagenum_template"}

def templates(text):
    for m in re.finditer(r'data-mw=(["\'])(.*?)\1', text):
        try:
            mw = json.loads(html.unescape(m.group(2)))
        except json.JSONDecodeError:
            continue
        for part in mw.get("parts", []):
            if isinstance(part, dict) and "template" in part:
                t = part["template"]
                name = t.get("target", {}).get("wt", "?").strip()
                if name in SKIP or name.startswith(":Seite:") or name.startswith(":Page:") \
                        or name.startswith(":Side:") or name.startswith("#lst"):
                    continue
                print(f"  {{{{{name}}}}}")
                for k, v in t.get("params", {}).items():
                    wt = v.get("wt", "") if isinstance(v, dict) else str(v)
                    wt = re.sub(r"\s+", " ", wt).strip()
                    if wt:
                        print(f"      {k} = {wt[:150]}")

def links(text, own_title):
    kinds, examples = Counter(), {}
    for m in re.finditer(r'<a [^>]*href="\./([^"#?]+)[^"]*"[^>]*>', text):
        tag = m.group(0)
        if 'rel="mw:WikiLink"' not in tag:
            continue
        target = unquote(m.group(1)).replace("_", " ")
        if "new" in tag and 'class="new"' in tag:
            kind = "redlink"
        elif ":" in target.split("/")[0] and not target.startswith(own_title):
            kind = "other-ns:" + target.split(":")[0]
        elif target.startswith(own_title + "/"):
            kind = "own-subpage"
        elif "/" in target:
            kind = "other-subpage"
        else:
            kind = "root"
        kinds[kind] += 1
        examples.setdefault(kind, [])
        if len(examples[kind]) < 6 and target not in examples[kind]:
            examples[kind].append(target)
    for kind, n in kinds.most_common():
        print(f"  links {kind:22} {n:5}  e.g. {examples[kind]}")

def report(text, title):
    print(f"\n##### {title}  ({len(text)//1024} KB)")
    templates(text)
    links(text, title)

cmd = sys.argv[1]
if cmd == "fixture":
    for path in sys.argv[2:]:
        src = json.load(open(path.replace("raw.html", "source.json")))
        report(open(path, encoding="utf-8").read(), src["title"])
elif cmd == "page":
    domain, title = sys.argv[2], sys.argv[3]
    report(parsoid.fetch(domain, title), title)
elif cmd == "redirects":
    domain = sys.argv[2]
    data = wmhttp.action_api(domain, action="query", titles="|".join(sys.argv[3:]),
                             redirects=1, prop="info")
    print(json.dumps(data["query"], ensure_ascii=False, indent=1)[:3000])
elif cmd == "stats":
    for domain in sys.argv[2:]:
        data = wmhttp.action_api(domain, action="query", meta="siteinfo", siprop="statistics")
        s = data["query"]["statistics"]
        print(f"{domain:22} pages={s['pages']:>9} articles(content)={s['articles']:>8}")
elif cmd == "wikitext":
    domain = sys.argv[2]
    data = wmhttp.action_api(domain, action="query", prop="revisions", rvslots="main",
                             rvprop="content", titles="|".join(sys.argv[3:]))
    for p in data["query"]["pages"]:
        print(f"\n##### {p['title']} (missing={p.get('missing', False)})")
        for r in p.get("revisions", []):
            print(r["slots"]["main"]["content"][:int(sys.argv[-1]) if sys.argv[-1].isdigit() else 2500])
elif cmd == "dumps":
    status, body = wmhttp.get(f"https://dumps.wikimedia.org/{sys.argv[2]}/latest/")
    for m in re.finditer(r'href="([^"]+\.(?:gz|bz2))"', body):
        print(m.group(1))
