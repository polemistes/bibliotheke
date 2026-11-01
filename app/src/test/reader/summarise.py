# SPDX-License-Identifier: GPL-3.0-or-later
"""Read the DOM dumped by headless Chromium (run.sh) on stdin, save the reader test
result as JSON (argv[1]) and print a summary; exit 1 if any part fails."""
import html
import json
import re
import sys

m = re.search(r'<pre id="wsr-result">(.*?)</pre>', sys.stdin.read(), re.S)
if not m or not m.group(1).strip():
    sys.exit("no test result in the page (did a script fail?)")
res = json.loads(html.unescape(m.group(1)))
with open(sys.argv[1], "w") as f:
    json.dump(res, f, indent=1, ensure_ascii=False)


def marks(p):
    t = p.get("marksTapped")
    return f" ({t['inline']}/{t['margin']} of {p['marks']} tapped inline/margin)" if t else ""


def lines(p):
    if "verseLines" not in p:
        return ""
    return f", {p['verseLines']} verse lines ({p['verseNumbers']} numbered every 5), {p['sourceNumbers']} source numbers"


print(res["ua"])
for p in res["parts"]:
    status = "ok  " if p["pass"] else "FAIL"
    line = (f"{status} {p['name']}: {p.get('chars')} chars, {p.get('pages')} pages, "
            f"{p.get('textless', 0)} text-less, {p.get('probes')} probes, {p.get('notes', 0)} notes, mark {p.get('pageMark', '-')}{marks(p)}{lines(p)}, "
            f"{p.get('loadMs')} ms")
    if not p["pass"]:
        line += f"  {p.get('fails') or p.get('error')}"
    print(line)
n = sum(p["pass"] for p in res["parts"])
print(f"\n{n} of {len(res['parts'])} parts pass")
sys.exit(0 if res["pass"] else 1)
