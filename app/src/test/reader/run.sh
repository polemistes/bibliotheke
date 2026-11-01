#!/bin/sh
# SPDX-License-Identifier: GPL-3.0-or-later
# WP4: run the reader page's tests (test.js) over the fixture corpus in headless
# Chromium at a phone page size, and print a summary. Full JSON goes to $OUT.
#
#   app/src/test/reader/run.sh                      # every fixture page
#   PARTS=no/sult-02,de/kaethe app/src/test/reader/run.sh
set -eu
here=$(cd "$(dirname "$0")" && pwd)
src=$(cd "$here/../.." && pwd)
out=${OUT:-$src/../build/reader-test.json}
mkdir -p "$(dirname "$out")"
parts=${PARTS:-$(cd "$src/test/fixtures" && ls -d */*/ | sed 's:/$::' | paste -sd, -)}
port=${PORT:-8765}
profile=$(mktemp -d)
python3 -m http.server "$port" --bind 127.0.0.1 --directory "$src" >/dev/null 2>&1 &
server=$!
trap 'kill $server 2>/dev/null; rm -rf "$profile"' EXIT
sleep 1

# Chromium creates a Unix socket under $TMPDIR and aborts if the path is longer than
# the socket path limit; use /tmp for it then.
chromium_tmp=${TMPDIR:-/tmp}
[ ${#chromium_tmp} -gt 60 ] && chromium_tmp=/tmp
url="http://127.0.0.1:$port/test/reader/harness.html?parts=$parts&w=412&h=915&font=${FONT:-18}&font2=${FONT2:-26}"
TMPDIR=$chromium_tmp ${CHROMIUM:-chromium} --headless=new --disable-gpu --user-data-dir="$profile" \
  --window-size=1000,1000 --force-device-scale-factor=2.625 \
  --virtual-time-budget=1800000 --dump-dom "$url" 2>/dev/null |
python3 -I "$here/summarise.py" "$out"
