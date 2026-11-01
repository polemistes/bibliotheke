#!/usr/bin/env bash
# SPDX-License-Identifier: GPL-3.0-or-later
# WP5 acceptance (STATUS.md), with a downloaded work open in the reader and the app in
# Norwegian Bokmål (the labels below; override L_* for another language). Settings are
# changed through the appearance sheet; where marks and line numbers are, and what is
# shown, is read from the current reader page over the DevTools protocol (cdp.mjs).
#   wp5.sh marks [pages]  page marks inline, then in the margin: on each of [pages] pages
#                         (default 6) every mark on the screen is tapped and must open its
#                         sheet ("Side N i originalen", with the scan); back to inline
#   wp5.sh verse          a poem: verse lines numbered every 5 (heading lines not), then off
#   wp5.sh source         source line numbers shown, then hidden, then shown again
# Screenshots go to $OUT (default /tmp/wp5-acceptance).
set -u
cd "$(dirname "$0")"
OUT=${OUT:-/tmp/wp5-acceptance}
mkdir -p "$OUT"
source ./ui.sh
L_APPEARANCE=${L_APPEARANCE:-Utseende}
L_INLINE=${L_INLINE:-I teksten}
L_MARGIN=${L_MARGIN:-I margen}
L_MARK=${L_MARK:-Side %s i originalen}
L_SCAN=${L_SCAN:-Åpne skanningen på Wikisource}
L_SOURCE=${L_SOURCE:-Linjenumre fra kilden}
L_EVERY5=${L_EVERY5:-Hver 5.}
L_OFF=${L_OFF:-Av}
L_SHEET=${L_SHEET:-Håndtak}   # the drag handle of every sheet
PORT=${PORT:-9233}
fail=0

last() { $ADB logcat -d -s BibliothekeReader:I | grep -o 'part=.*' | tail -1; }
field() { sed -n "s/.*$1=\([^ ]*\).*/\1/p" <<<"$2"; }

# DevTools: forward the app's WebView socket, named by the process id (an earlier
# process can leave its socket behind).
forward() {
  local pid sock
  pid=$($ADB shell pidof app.bibliotheke.wikisource | tr -d '\r')
  sock=$($ADB shell cat /proc/net/unix | grep -o "webview_devtools_remote_$pid\b" | head -1)
  [ -n "$pid" ] && [ -n "$sock" ] || { echo "no WebView DevTools socket; is the reader open in a debug build?" >&2; exit 2; }
  $ADB forward "tcp:$PORT" "localabstract:$sock" >/dev/null
}

# Evaluates $1 in the current reader page (the one whose state matches the last logged
# position) and prints {screenX, screenY, value} as JSON.
current() {
  local pos
  pos=$(last)
  node cdp.mjs "$PORT" "$1" | python3 -I -c '
import json, re, sys
pos = sys.argv[1]
page, pages = map(int, re.search(r"page=(\d+)/(\d+)", pos).groups())
anchor = int(re.search(r"anchor=(\d+)", pos).group(1))
for line in sys.stdin:
    r = json.loads(line)
    s = (r["value"] or {}).get("state") or {}
    if s.get("loaded") and s.get("page") == page - 1 and s.get("pages") == pages and s.get("anchor") == anchor:
        print(json.dumps(r)); break
' "$pos"
}

# Sheets are closed with Back until the sheet's handle is gone: one Back does not
# always close a sheet, and a Back with nothing open would leave the reader.
sheet_open() { texts | grep -qxF "$L_SHEET"; }
close_sheets() { local k; for k in 1 2 3; do sheet_open || return 0; back; done; ! sheet_open; }

# Opens the appearance sheet (centre tap for the bars, then the icon) scrolled down to
# the page-mark and line-number settings. The bars may be showing already; a centre tap
# that lands on a mark opens its sheet instead.
open_appearance() {
  local y
  close_sheets
  for y in 1300 900 1700 1100; do
    tap_text "$L_APPEARANCE" 1.5 2>/dev/null && sheet_open && break
    close_sheets
    $ADB shell input tap 540 $y; sleep 1.5
  done
  sheet_open || { echo "FAIL the appearance sheet did not open"; fail=1; return 1; }
  local k   # to the end of the sheet: slowly (no fling), in the side padding, off the sliders
  for k in 1 2; do $ADB shell input swipe 30 2000 30 600 1000; sleep 1; done
}
set_option() { open_appearance || return; tap_exact "$1" 2; close_sheets; sleep 1; }

JS_MARKS='(() => {
  const W = innerWidth, H = innerHeight;
  const margin = !!document.querySelector("#wsr-content.wsr-pm-margin");
  const els = margin ? [...document.querySelectorAll("#wsr-gutter .wsr-gmark")].filter((e) => !e.hidden)
                     : [...document.querySelectorAll("#wsr-content .wsr-page[data-num]")].filter((e) => e.dataset.num);
  const marks = [];
  for (const e of els) {
    const r = e.getClientRects()[0];
    if (!r || r.left < 0 || r.right > W || r.top < 0 || r.bottom > H) continue;
    marks.push({ num: margin ? e.textContent : e.dataset.num, x: r.left + r.width / 2, y: r.top + r.height / 2 });
  }
  return { state: WsrReader.state(), dpr: devicePixelRatio, margin, marks };
})()'

# Taps every mark on the current page; each must open its sheet.
tap_marks() { # mode label
  local j n=0 ok=0 num x y
  j=$(current "$JS_MARKS")
  [ -n "$j" ] || { echo "FAIL $1: the current reader page was not found"; fail=1; return; }
  while read -r num x y <&3; do   # fd 3: adb shell would read the list from stdin
    [ -n "$num" ] || continue
    n=$((n + 1))
    $ADB shell input tap "$x" "$y"; sleep 1.5
    local t
    t=$(texts)
    # shellcheck disable=SC2059
    if grep -qF "$(printf "$L_MARK" "$num")" <<<"$t" && grep -qF "$L_SCAN" <<<"$t"; then
      ok=$((ok + 1)); [ $ok -eq 1 ] && shot "$1-sheet-$num"
    else
      echo "FAIL $1: mark $num at $x,$y opened no sheet"; fail=1; shot "$1-fail-$num"
    fi
    close_sheets
  done 3< <(python3 -I -c '
import json, sys
r = json.loads(sys.argv[1]); v = r["value"]; d = v["dpr"]
for m in v["marks"]:
    print(m["num"], round(r["screenX"] + m["x"] * d), round(r["screenY"] + m["y"] * d))
' "$j")
  pos=$(last)
  echo "     $1 page $(field page "$pos"): $ok of $n marks tapped"
  tapped=$((tapped + ok)); seen=$((seen + n))
}

marks() {
  local pages=${1:-6} mode
  for mode in inline margin; do
    set_option "$([ $mode = inline ] && echo "$L_INLINE" || echo "$L_MARGIN")"
    local margin
    margin=$(current "$JS_MARKS" | python3 -I -c 'import json,sys; print(json.loads(sys.stdin.read())["value"]["margin"])')
    [ "$margin" = "$([ $mode = margin ] && echo True || echo False)" ] || { echo "FAIL $mode: the page is not in $mode mode"; fail=1; }
    shot "marks-$mode"
    tapped=0 seen=0
    for ((p = 0; p < pages; p++)); do
      tap_marks "$mode"
      $ADB shell input swipe 900 1200 200 1200 120; sleep 1
    done
    for ((p = 0; p < pages; p++)); do $ADB shell input swipe 200 1200 900 1200 120; sleep 0.6; done
    if [ "$seen" -gt 0 ] && [ "$tapped" -eq "$seen" ]; then echo "ok   $mode: $tapped of $seen marks open their sheet"
    else echo "FAIL $mode: $tapped of $seen marks open their sheet"; fail=1; fi
  done
  set_option "$L_INLINE"
}

JS_VERSE='(() => {
  const W = innerWidth, H = innerHeight;
  const on = (r) => r && r.right > 0 && r.left < W && r.top >= 0 && r.bottom <= H;
  const lines = [...document.querySelectorAll("#wsr-content .wsr-line")].filter((l) => on(l.getClientRects()[0]));
  const numbered = lines.filter((l) => getComputedStyle(l, "::before").content !== "none").map((l) => Number(l.dataset.n));
  const src = [...document.querySelectorAll("#wsr-content .wsr-ln-src")].filter((e) => on(e.getClientRects()[0] || e.parentElement.getClientRects()[0]));
  const srcShown = src.filter((e) => getComputedStyle(e).display !== "none").map((e) => e.textContent);
  const heads = lines.filter((l) => l.classList.contains("wsr-vhead")).length;
  const due = lines.filter((l) => l.dataset.n && Number(l.dataset.n) % 5 === 0).map((l) => Number(l.dataset.n));
  return { state: WsrReader.state(), lines: lines.length, numbered, due, heads, src: src.length, srcShown };
})()'

verse_state() { current "$JS_VERSE" | python3 -I -c 'import json,sys; print(json.dumps(json.loads(sys.stdin.read())["value"]))'; }

verse() {
  set_option "$L_EVERY5"
  local v; v=$(verse_state); shot verse-every-5
  python3 -I -c '
import json, sys
v = json.loads(sys.argv[1])
shown = sorted(set(v["numbered"]) | {int(s) for s in v["srcShown"]})
ok = v["lines"] > 0 and shown == sorted(set(v["due"])) and len(v["due"]) > 0
lines, due, heads = v["lines"], v["due"], v["heads"]
print(("ok  " if ok else "FAIL") + f" verse every 5: {lines} lines on the page, numbers {shown}, due {due}, {heads} heading lines unnumbered")
sys.exit(0 if ok else 1)' "$v" || fail=1
  set_option "$L_OFF"
  v=$(verse_state); shot verse-off
  python3 -I -c '
import json, sys
v = json.loads(sys.argv[1])
ok = not v["numbered"]
numbered = v["numbered"]
print(("ok  " if ok else "FAIL") + f" verse off: generated numbers {numbered}")
sys.exit(0 if ok else 1)' "$v" || fail=1
}

source_numbers() {
  local v a b
  v=$(verse_state); a=$(python3 -I -c 'import json,sys; v=json.loads(sys.argv[1]); print(len(v["srcShown"]), v["src"])' "$v")
  shot source-shown
  set_option "$L_SOURCE"
  v=$(verse_state); b=$(python3 -I -c 'import json,sys; v=json.loads(sys.argv[1]); print(len(v["srcShown"]), v["src"])' "$v")
  shot source-hidden
  set_option "$L_SOURCE"
  v=$(verse_state); c=$(python3 -I -c 'import json,sys; v=json.loads(sys.argv[1]); print(len(v["srcShown"]), v["src"])' "$v")
  read -r a1 a2 <<<"$a"; read -r b1 _ <<<"$b"; read -r c1 _ <<<"$c"
  if [ "$a2" -gt 0 ] && [ "$a1" -eq "$a2" ] && [ "$b1" -eq 0 ] && [ "$c1" -eq "$a2" ]; then
    echo "ok   source line numbers: $a1 of $a2 shown, then $b1, then $c1"
  else
    echo "FAIL source line numbers: shown $a1 of $a2, hidden $b1, again $c1"; fail=1
  fi
}

forward
[ -n "$(last)" ] || { $ADB shell input swipe 900 1200 200 1200 120; sleep 1; $ADB shell input swipe 200 1200 900 1200 120; sleep 1; }
case ${1:-marks} in
  marks) marks "${2:-6}" ;;
  verse) verse ;;
  source) source_numbers ;;
  *) echo "usage: wp5.sh marks [pages] | verse | source" >&2; exit 2 ;;
esac
[ $fail -eq 0 ] && echo PASS || echo FAIL
exit $fail
