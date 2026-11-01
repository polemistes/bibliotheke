#!/usr/bin/env bash
# SPDX-License-Identifier: GPL-3.0-or-later
# WP3 acceptance, last step (STATUS.md): with a downloaded work open in the part viewer at
# its first part and airplane mode on, steps through every part with "Next" and checks
# that each opens: a new part title in the top bar, no "not downloaded" message, and text
# on the page (dark pixels in a screenshot of the WebView: uiautomator sees the WebView's
# text only now and then). Usage: wp3.sh <parts> [next-label] [back-label]
set -u
cd "$(dirname "$0")"
source ./ui.sh
PARTS=$1
NEXT=${2:-Neste}
BACK=${3:-Tilbake}
SHOT=$(mktemp --suffix=.png)

[ "$($ADB shell settings get global airplane_mode_on | tr -d '\r')" = "1" ] || { echo "airplane mode is off" >&2; exit 2; }

title() { texts | grep -A1 -x "$BACK" | sed -n 2p; }

inked() { # percentage of dark pixels between the bars, waiting for the WebView to draw
  local p
  for _ in 1 2 3 4 5; do
    sleep 1
    $ADB exec-out screencap -p > "$SHOT"
    p=$(python3 -c '
import sys
from PIL import Image
im = Image.open(sys.argv[1]).convert("L"); w, h = im.size
box = im.crop((0, int(h * 0.15), w, int(h * 0.85)))
print(round(100 * sum(1 for v in box.tobytes() if v < 110) / (box.width * box.height), 2))' "$SHOT")
    python3 -c "import sys; sys.exit(0 if float('$p') > 1.0 else 1)" && { echo "$p"; return 0; }
  done
  echo "$p"; return 1
}

ok=0; fail=0; prev=""
for i in $(seq 1 "$PARTS"); do
  t=$(title)
  if [ -z "$t" ] || [ "$t" = "$prev" ]; then
    fail=$((fail + 1)); echo "FAIL $i no new part (title '$t')"
  elif texts | grep -q "ikke lastet ned\|ikkje lasta ned\|not downloaded"; then
    fail=$((fail + 1)); echo "FAIL $i $t: not downloaded"
  elif ink=$(inked); then
    ok=$((ok + 1)); echo "ok   $i $t ($ink % ink)"
  else
    fail=$((fail + 1)); echo "FAIL $i $t: blank page ($ink % ink)"
  fi
  prev=$t
  [ "$i" -lt "$PARTS" ] && { tap_exact "$NEXT" 1 || { echo "no Next button at $i" >&2; break; }; }
done
rm -f "$SHOT"
echo "parts opened offline: $ok of $PARTS, failures: $fail"
[ "$fail" -eq 0 ] && [ "$ok" -eq "$PARTS" ]
