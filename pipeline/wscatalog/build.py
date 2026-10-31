# SPDX-License-Identifier: GPL-3.0-or-later
"""End-to-end catalog build (PLAN.md 5.6): dumps of the newest complete
run, extract, catalog, coverage report, manifest.

    python -I -m wscatalog.build no is          # `make catalog WIKI=no`
    python -I -m wscatalog.build --configured   # the wikis with a config of their own
    python -I -m wscatalog.build --all          # every open Wikisource

Idempotent: downloaded dumps are reused (sha1-checked), and the extract is
redone only when the dump or the extract format changed (--re-extract
forces it).
"""

from __future__ import annotations

import argparse
import pickle
import sys
import time
import traceback
from pathlib import Path

from . import catalog, config, dumps, extract, sites


def extract_current(wiki: str, files: list[Path]) -> bool:
    path = extract.EXTRACT_DIR / f"{wiki}.pickle"
    if not path.exists():
        return False
    with path.open("rb") as f:
        ext = pickle.load(f)
    return ext.get("version") == extract.EXTRACT_VERSION and ext.get("dump") == [p.name for p in files]


def build_one(wiki: str, download: bool = True, re_extract: bool = False, offline: bool = False) -> None:
    t0 = time.time()
    cfg = config.load(wiki)
    if download:
        dumps.fetch(cfg.dbname, ["articles", "page_props"])
    files = dumps.local(cfg.dbname, "articles")
    if re_extract or not extract_current(wiki, files):
        extract.run(wiki)
    catalog.build(wiki, offline=offline)
    print(f"{wiki}: done in {time.time() - t0:.0f} s", file=sys.stderr)


def main(argv: list[str]) -> int:
    ap = argparse.ArgumentParser(prog="wscatalog.build")
    ap.add_argument("wikis", nargs="*")
    ap.add_argument("--configured", action="store_true", help="every wiki with a config file")
    ap.add_argument("--all", action="store_true", help="every open Wikisource (sitematrix)")
    ap.add_argument("--no-download", action="store_true", help="use the dumps on disk")
    ap.add_argument("--re-extract", action="store_true")
    ap.add_argument("--offline", action="store_true", help="Wikidata from the cache only")
    ap.add_argument("--previous-manifest", type=Path,
                    help="carry over the entries of wikis not built in this run")
    args = ap.parse_args(argv)
    wikis = list(args.wikis)
    if args.configured:
        wikis += config.configured()
    if args.all:
        wikis += [s["code"] for s in sites.wikis()]
    failed = []
    for wiki in dict.fromkeys(wikis):
        try:
            build_one(wiki, download=not args.no_download, re_extract=args.re_extract,
                      offline=args.offline)
        except Exception:
            traceback.print_exc()
            failed.append(wiki)
    path = catalog.write_manifest(previous=args.previous_manifest)
    print(f"manifest: {path}" + (f"; failed: {' '.join(failed)}" if failed else ""), file=sys.stderr)
    return 1 if failed else 0


if __name__ == "__main__":
    sys.exit(main(sys.argv[1:]))
