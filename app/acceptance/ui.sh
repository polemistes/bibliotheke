#!/usr/bin/env bash
# SPDX-License-Identifier: GPL-3.0-or-later
# Small adb helpers for the WP2 acceptance walk-through (STATUS.md). Source this file.
# SERIAL (default emulator-5584) and OUT (screenshot directory) may be set.
SERIAL=${SERIAL:-emulator-5584}
OUT=${OUT:-.}
ADB="adb -s $SERIAL"

# Taps the centre of the first node whose text or content-desc contains $1.
tap_text() {
  $ADB shell uiautomator dump /sdcard/ui.xml >/dev/null 2>&1
  local b
  b=$($ADB shell cat /sdcard/ui.xml | python3 -c '
import re, sys, html
needle = sys.argv[1]
x = sys.stdin.read()
for m in re.finditer(r"<node [^>]*>", x):
    n = m.group(0)
    mt = re.search(r"text=\"([^\"]*)\"", n)
    md = re.search(r"content-desc=\"([^\"]*)\"", n)
    t = html.unescape(mt.group(1)) if mt else ""
    d = html.unescape(md.group(1)) if md else ""
    if (needle in t or needle in d) and "bounds=" in n:
        b = re.search(r"bounds=\"\[(\d+),(\d+)\]\[(\d+),(\d+)\]\"", n).groups()
        print((int(b[0]) + int(b[2])) // 2, (int(b[1]) + int(b[3])) // 2)
        break
' "$1")
  if [ -z "$b" ]; then echo "not found: $1" >&2; return 1; fi
  $ADB shell input tap $b
  sleep "${2:-1.5}"
}

# Prints every visible text, one per line (to check that a screen is translated).
texts() {
  $ADB shell uiautomator dump /sdcard/ui.xml >/dev/null 2>&1
  $ADB shell cat /sdcard/ui.xml | python3 -c '
import re, sys, html
for m in re.finditer(r"(text|content-desc)=\"([^\"]+)\"", sys.stdin.read()):
    print(html.unescape(m.group(2)))'
}

shot() { $ADB exec-out screencap -p > "$OUT/$1.png"; }
back() { $ADB shell input keyevent KEYCODE_BACK; sleep 1; }
type_text() { $ADB shell input text "$(printf %s "$1" | sed 's/ /%s/g')"; sleep 2; }

# Like tap_text, but the whole text or content-desc must equal $1 (the last such node:
# a dialog's button comes after its message).
tap_exact() {
  $ADB shell uiautomator dump /sdcard/ui.xml >/dev/null 2>&1
  local b
  b=$($ADB shell cat /sdcard/ui.xml | python3 -c '
import re, sys, html
needle = sys.argv[1]
found = None
for m in re.finditer(r"<node [^>]*>", sys.stdin.read()):
    n = m.group(0)
    t = re.search(r"text=\"([^\"]*)\"", n)
    d = re.search(r"content-desc=\"([^\"]*)\"", n)
    t, d = (html.unescape(x.group(1)) if x else "" for x in (t, d))
    if needle in (t, d):
        b = re.search(r"bounds=\"\[(\d+),(\d+)\]\[(\d+),(\d+)\]\"", n).groups()
        found = ((int(b[0]) + int(b[2])) // 2, (int(b[1]) + int(b[3])) // 2)
if found: print(*found)
' "$1")
  if [ -z "$b" ]; then echo "not found: $1" >&2; return 1; fi
  $ADB shell input tap $b
  sleep "${2:-1.5}"
}
