#!/bin/sh
# SPDX-License-Identifier: GPL-3.0-or-later
# WP0: run the locator tests of the pagination prototype in headless Chromium
# with a phone viewport and print a summary. Full JSON goes to $OUT.
#
#   prototypes/pagination/run_browser_test.sh
#   CONTENT=sult-02 FONT=16 FONT2=28 prototypes/pagination/run_browser_test.sh
set -eu
here=$(cd "$(dirname "$0")" && pwd)
content=${CONTENT:-sult-02,ved-rundarne-1864,peer-gynt-1,erlkoenig,kaethe}
out=${OUT:-$here/../../docs/wp0/pagination-browser.json}
profile=$(mktemp -d)
trap 'rm -rf "$profile"' EXIT

# Headless Chromium keeps the window at least 500 px wide, so the phone page
# size (412x915 CSS px, a 6.1" phone) is passed to the reader as w/h.
url="file://$here/web/harness.html?content=$content&test=1&font=${FONT:-18}&font2=${FONT2:-26}&w=412&h=915"
# Chromium creates a Unix socket under $TMPDIR and aborts if the path is
# longer than the socket path limit (~100 bytes); use /tmp for it then.
chromium_tmp=${TMPDIR:-/tmp}
[ ${#chromium_tmp} -gt 60 ] && chromium_tmp=/tmp
TMPDIR=$chromium_tmp ${CHROMIUM:-chromium} --headless=new --disable-gpu --user-data-dir="$profile" \
  --window-size=412,915 --force-device-scale-factor=2.625 \
  --virtual-time-budget=600000 --dump-dom "$url" 2>/dev/null |
python3 "$here/summarise.py" "$out"
