#!/usr/bin/env bash
# SPDX-License-Identifier: GPL-3.0-or-later
# WP2 acceptance on a device or emulator (PLAN.md section 9): offline browsing after the
# catalog download, facets combining, nb and nn strings. Needs the debug APK installed
# with the en and no catalogs downloaded (debug builds fetch them from
# `python3 -m http.server 8765` in pipeline/out). Screenshots go to $OUT.
set -u
cd "$(dirname "$0")"
OUT=${OUT:-/tmp/wp2-acceptance}; mkdir -p "$OUT"
source ./ui.sh
APP=app.bibliotheke.wikisource
fail=0
expect() {  # $1 = label, $2 = text that must be visible
  if texts | grep -qF -- "$2"; then echo "ok   $1"; else echo "FAIL $1 (no '$2')"; fail=1; fi
}
restart() {  # $1 = locale
  $ADB shell cmd locale set-app-locales $APP --locales "$1"
  $ADB shell am force-stop $APP; $ADB shell am start -n $APP/.MainActivity >/dev/null; sleep 4
}
chips_right() { $ADB shell input swipe 900 370 300 370 300; sleep 1; }

$ADB shell cmd connectivity airplane-mode enable; sleep 3
for loc in nn nb; do
  restart $loc
  if [ $loc = nn ]; then S=Søk; SET=Innstillingar; CAT="Administrer katalogar"; MANY="treff til er gøymde"
  else S=Søk; SET=Innstillinger; CAT="Administrer kataloger"; MANY="treff til er skjult"; fi
  # Offline browsing of the no catalog (picker count 618, PLAN.md 5.4).
  tap_text "Engelsk" 2 && tap_text "(no)" 3
  expect "$loc browse offline" "618 verk"; shot $loc-browse
  # Facets combine: genre, period, language, author.
  chips_right; tap_text Sjanger 2; tap_text Lyrikk; tap_text Ferdig
  chips_right; tap_text Periode 2; tap_text "1800-ta"; tap_text Ferdig
  chips_right; tap_text Språk 2; tap_text "(nn)"; tap_text Ferdig
  expect "$loc genre+period+language" "5 verk"
  chips_right; tap_text Forfatt 2; tap_text "Arne Garborg"; tap_text Ferdig
  expect "$loc four facets" "1 verk"; shot $loc-facets
  tap_text "Dei gamle fjelli" 3; expect "$loc detail" "Sidehistorikk"; shot $loc-detail; back
  # Search, with the hidden-matches row.
  tap_text $S 2; type_text "rundarne"; back
  expect "$loc search hidden row" "$MANY"; shot $loc-search; back
  tap_text $SET 2; expect "$loc settings" "Kvalitetsfilter"; shot $loc-settings
  tap_text "Om" 2; expect "$loc about" "Wikimedia Foundation"; shot $loc-about; back
  tap_text "$CAT" 3; expect "$loc picker (cached manifest)" "no.wikisource.org"; shot $loc-picker; back; back
  # Open by URL through parts and aliases.
  $ADB shell am start -a android.intent.action.VIEW -d "https://no.m.wikisource.org/wiki/Et_Dukkehjem/F%C3%B8rste_akt" $APP >/dev/null; sleep 3
  expect "$loc open by URL" "Henrik Ibsen"; back
done
$ADB shell cmd connectivity airplane-mode disable
exit $fail
