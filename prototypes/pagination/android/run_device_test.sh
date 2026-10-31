#!/bin/sh
# SPDX-License-Identifier: GPL-3.0-or-later
# WP0: run the pagination prototype on a device or emulator.
#  1. locator tests inside the Android WebView (same test.js as the browser run);
#  2. page-turn frame timing: real swipes through the touch overlay, measured
#     with `dumpsys gfxinfo`;
#  3. a real rotation, checking that the reading position survives it.
# Results go to docs/wp0/pagination-android*.json / .txt.
#
#   SERIAL=emulator-5584 prototypes/pagination/android/run_device_test.sh
set -eu
here=$(cd "$(dirname "$0")" && pwd)
docs=$here/../../../docs/wp0
pkg=app.bibliotheke.wikisource.proto
adb="adb -s ${SERIAL:-emulator-5584}"
apk=$here/app/build/outputs/apk/release/app-release.apk
content=${CONTENT:-sult-02,ved-rundarne-1864,peer-gynt-1,erlkoenig,kaethe}
swipes=${SWIPES:-30}
steps=${STEPS:-1 2 3}

wait_for_log() {  # wait_for_log <pattern> <seconds>: print the first matching logcat line
  end=$(( $(date +%s) + $2 ))
  while [ "$(date +%s)" -lt "$end" ]; do
    line=$($adb logcat -d -s WsrProto:I | grep -m1 -E "$1" || true)
    if [ -n "$line" ]; then echo "$line"; return 0; fi
    sleep 1
  done
  echo "timeout waiting for '$1'" >&2; return 1
}

$adb install -r "$apk" >/dev/null
$adb shell settings put system accelerometer_rotation 0
$adb shell settings put system user_rotation 0

case " $steps " in *" 1 "*)
echo "== 1. locator tests in the WebView"
$adb shell am force-stop $pkg
$adb logcat -c
$adb shell am start -n $pkg/.MainActivity --es content "$content" --es mode test >/dev/null
last=$(wait_for_log 'test [0-9]+/[0-9]+' 600)
n=$(echo "$last" | sed -E 's/.*test [0-9]+\/([0-9]+) .*/\1/')
wait_for_log "test $n/$n " 600 >/dev/null
$adb logcat -d -s WsrProto:I | grep -E ' test [0-9]+/[0-9]+ ' |
  sed -E 's/.* test [0-9]+\/[0-9]+ //' | tr -d '\n' |
  python3 "$here/../summarise.py" "$docs/pagination-android.json"
;; esac

case " $steps " in *" 2 "*)
echo
echo "== 2. page-turn frame timing ($swipes swipes on sult-02)"
$adb shell am force-stop $pkg
$adb logcat -c
$adb shell am start -n $pkg/.MainActivity --es content sult-02 >/dev/null
wait_for_log '^.*page \{' 60 >/dev/null
sleep 2
$adb shell dumpsys gfxinfo $pkg reset >/dev/null
i=0
while [ $i -lt "$swipes" ]; do
  $adb shell input swipe 900 1200 200 1200 120
  sleep 0.6
  i=$((i + 1))
done
$adb shell dumpsys gfxinfo $pkg > "$docs/pagination-android-gfxinfo.txt"
grep -E 'Total frames rendered|Janky frames|percentile|Number Missed Vsync|Number Slow UI thread|Number Slow issue draw commands|Number Frame deadline missed' \
  "$docs/pagination-android-gfxinfo.txt" | head -12
turns=$($adb logcat -d -s WsrProto:I | grep -c ' page {' || true)
echo "page events logged: $turns (1 initial + one per turn)"
# Per-frame view of a few turns (framestats keeps only the last ~120 frames).
$adb shell dumpsys gfxinfo $pkg reset >/dev/null
for i in 1 2 3 4 5; do $adb shell input swipe 900 1200 200 1200 120; sleep 0.6; done
$adb shell dumpsys gfxinfo $pkg framestats | python3 "$here/framestats.py" |
  tee "$docs/pagination-android-framestats.txt"
;; esac

case " $steps " in *" 3 "*)
echo
echo "== 3. rotation keeps the reading position"
$adb shell settings put system user_rotation 1
sleep 3
$adb shell settings put system user_rotation 0
sleep 3
$adb logcat -d -s WsrProto:I | grep ' relayout ' | sed -E 's/.*relayout //' |
  tee "$docs/pagination-android-rotation.txt"
;; esac
