# SPDX-License-Identifier: GPL-3.0-or-later
"""Extract the harness test result from a dumped DOM (stdin), save it as JSON
(argv[1]) and print a summary. Used by run_browser_test.sh."""

import html
import json
import re
import sys


def summarise(res: dict) -> None:
    print(res["ua"])
    print("viewport", res["viewport"])
    for p in res["parts"]:
        print(f"\n{p['name']}: {p['chars']} chars, page width {p['pageWidth']}px; "
              f"{p['pagesA']} pages at {p['base']}px, {p['pagesB']} at {p['other']}px; "
              f"load+layout {p['loadMs']} ms, relayout {p['relayoutMs']} ms")
        print(f"  all {p['allPagesChecked']} pages: locator on page and increasing -> "
              f"{p['allPagesFailureCount']} failures ({p['allPagesMs']} ms) {p['allPagesFailures']}")
        for q in p["probes"]:
            print(f"  page {q['page']:>4} L={q['locator']:>6} -> font: p{q['pageAfterFont']} "
                  f"visible={q['visibleAfterFont']} | rotate: p{q['pageAfterRotate']} "
                  f"visible={q['visibleAfterRotate']} | back: p{q['pageBack']} "
                  f"L={q['locatorBack']} exact={q['exactRoundTrip']}")
            print(f"         \"{q['sentence'][:110]}\"")
        print("  PASS" if p["pass"] else "  FAIL")
    print("\nOVERALL", "PASS" if res["pass"] else "FAIL")


if __name__ == "__main__":
    dom = sys.stdin.read()
    m = re.search(r'<pre id="wsr-result">(.*?)</pre>', dom, re.S)
    if not m or not m.group(1).strip():
        sys.exit("no test result in page (did a script fail?)")
    result = json.loads(html.unescape(m.group(1)))
    with open(sys.argv[1], "w", encoding="utf-8") as f:
        f.write(json.dumps(result, ensure_ascii=False, indent=1) + "\n")
    summarise(result)
