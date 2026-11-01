#!/bin/sh
# SPDX-License-Identifier: GPL-3.0-or-later
# A screenshot of one fixture page in the reader page at a phone size (412 × 915 CSS px):
#   app/src/test/reader/shot.sh no/oslo-hus 3 '{"pageMarks":"margin"}' out.png
# The page may be "mark=19" for the page of a page mark of the original.
set -eu
here=$(cd "$(dirname "$0")" && pwd)
src=$(cd "$here/../.." && pwd)
part=$1 page=${2:-0} settings=${3:-'{}'} out=${4:-shot.png}
port=${PORT:-8766}
profile=$(mktemp -d)
python3 -m http.server "$port" --bind 127.0.0.1 --directory "$src" >/dev/null 2>&1 &
server=$!
trap 'kill $server 2>/dev/null; rm -rf "$profile"' EXIT
sleep 1
case $page in mark=*) at="mark=${page#mark=}" ;; *) at="page=$page" ;; esac
enc=$(python3 -I -c 'import sys, urllib.parse; print(urllib.parse.quote(sys.argv[1]))' "$settings")
chromium_tmp=${TMPDIR:-/tmp}
[ ${#chromium_tmp} -gt 60 ] && chromium_tmp=/tmp
TMPDIR=$chromium_tmp ${CHROMIUM:-chromium} --headless=new --disable-gpu --user-data-dir="$profile" \
  --window-size=412,915 --hide-scrollbars --virtual-time-budget=20000 \
  --screenshot="$(realpath "$out")" "http://127.0.0.1:$port/test/reader/view.html?part=$part&$at&s=$enc" 2>/dev/null
