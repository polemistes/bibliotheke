#!/usr/bin/env bash
# SPDX-License-Identifier: GPL-3.0-or-later
# WP4 acceptance (STATUS.md), with a downloaded work of several parts open in the reader,
# not on its last part. Positions come from the reader's log (tag BibliothekeReader).
#  1. Turns across part boundaries without a gap: swipes forward until the part changes,
#     recording the screen; no frame of the turn may be blank (ink in the text area at
#     least half of the lesser of the pages before and after). Then one swipe back.
#  2. Rotation: landscape and back keep the anchor and return to the same page.
#  3. Process death: the app killed in the background comes back at the same anchor.
# Usage: wp4.sh [max-swipes]
set -u
cd "$(dirname "$0")"
source ./ui.sh
MAX=${1:-80}
TMP=$(mktemp -d)
trap 'rm -rf "$TMP"' EXIT
fail=0

last() { $ADB logcat -d -s BibliothekeReader:I | grep -o 'part=.*' | tail -1; }
field() { sed -n "s/.*$1=\([^ ]*\).*/\1/p" <<<"$2"; }

# Swipe with the screen recorded; prints "min first last" ink of the turn's frames.
recorded_swipe() { # x1 x2
  $ADB shell rm -f /sdcard/wp4.mp4
  $ADB shell screenrecord --time-limit 3 /sdcard/wp4.mp4 &
  local rec=$!
  sleep 1
  $ADB shell input swipe "$1" 1200 "$2" 1200 150
  wait $rec
  $ADB pull /sdcard/wp4.mp4 "$TMP/turn.mp4" >/dev/null 2>&1
  rm -f "$TMP"/f*.png
  ffmpeg -loglevel error -i "$TMP/turn.mp4" -vf fps=60 "$TMP/f%03d.png"
  python3 -I - "$TMP" <<'EOF'
import os, sys
from PIL import Image
d = sys.argv[1]
ink = []
for f in sorted(x for x in os.listdir(d) if x.endswith(".png")):
    im = Image.open(os.path.join(d, f)).convert("L")
    w, h = im.size
    b = im.crop((0, int(h * .08), w, int(h * .9))).resize((w // 4, h // 4)).tobytes()
    ink.append(100 * sum(1 for v in b if v < 110) / len(b))
print(round(min(ink), 2), round(ink[0], 2), round(ink[-1], 2))
EOF
}

gap_check() { # label "min first last"
  read -r lo a b <<<"$2"
  if python3 -c "import sys; lo,a,b=map(float,sys.argv[1:]); sys.exit(0 if lo >= 0.5*min(a,b) and lo > 0.1 else 1)" "$lo" "$a" "$b"; then
    echo "ok   $1: no gap (ink min $lo, before $a, after $b)"
  else
    echo "FAIL $1: a blank frame (ink min $lo, before $a, after $b)"; fail=1
  fi
}

# 1. Forward across a boundary, then back.
$ADB logcat -c
start=$(last)
$ADB shell input tap 990 1200; sleep 1   # a tap on the right turns too, and logs the position
cur=$(last)
[ -n "$cur" ] || { echo "no reader position in the log; is the reader open?" >&2; exit 2; }
part=$(field part "$cur")
for ((i = 0; i < MAX; i++)); do
  page=$(field page "$cur")
  if [ "${page%/*}" = "${page#*/}" ]; then # on the last page of the part
    r=$(recorded_swipe 900 200); sleep 1; cur=$(last)
    [ "$(field part "$cur")" != "$part" ] || { echo "FAIL forward: part did not change ($cur)"; fail=1; break; }
    gap_check "forward $part -> $(field part "$cur")" "$r"
    r=$(recorded_swipe 200 900); sleep 1; back_at=$(last)
    [ "$(field part "$back_at")" = "$part" ] || { echo "FAIL back: not in part $part ($back_at)"; fail=1; }
    gap_check "back $(field part "$cur") -> $part" "$r"
    break
  fi
  $ADB shell input swipe 900 1200 200 1200 120; sleep 0.8; cur=$(last)
done
[ $i -lt $MAX ] || { echo "FAIL no boundary within $MAX swipes"; fail=1; }
: "$start"

# 2. Rotation.
before=$(last)
$ADB shell settings put system accelerometer_rotation 0
$ADB shell settings put system user_rotation 1; sleep 4; land=$(last)
$ADB shell settings put system user_rotation 0; sleep 4; after=$(last)
if [ "$(field anchor "$land")" = "$(field anchor "$before")" ] && [ "$(field anchor "$after")" = "$(field anchor "$before")" ] \
   && [ "$(field page "$after")" = "$(field page "$before")" ]; then
  echo "ok   rotation: anchor $(field anchor "$before"), page $(field page "$before") -> $(field page "$land") landscape -> $(field page "$after")"
else
  echo "FAIL rotation: $before | $land | $after"; fail=1
fi

# 3. Killed in the background.
before=$(last)
$ADB shell input keyevent KEYCODE_HOME; sleep 2
$ADB shell am kill app.bibliotheke.wikisource; sleep 1
$ADB logcat -c
$ADB shell am start -n app.bibliotheke.wikisource/.MainActivity >/dev/null 2>&1; sleep 5
after=$(last)
if [ -n "$after" ] && [ "$(field part "$after")" = "$(field part "$before")" ] && [ "$(field anchor "$after")" = "$(field anchor "$before")" ]; then
  echo "ok   process death: part $(field part "$after"), anchor $(field anchor "$after"), page $(field page "$after")"
else
  echo "FAIL process death: $before -> ${after:-nothing}"; fail=1
fi

[ $fail = 0 ] && echo "PASS" || echo "FAIL"
exit $fail
