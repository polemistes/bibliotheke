# SPDX-License-Identifier: GPL-3.0-or-later
"""Wikimedia dump files (PLAN.md 5.1) in pipeline/cache/dumps/{dbname}/{date}/.

    python -I -m wscatalog.dumps nowikisource articles page_props

Picks the newest dump run in which every requested job is done, downloads
one file at a time over a single connection with the project User-Agent,
resumes interrupted downloads with a Range request and checks every file
against the sha1 in the run's dumpstatus.json.
"""

from __future__ import annotations

import hashlib
import re
import sys
import time
from pathlib import Path

import requests

from . import http

BASE = "https://dumps.wikimedia.org"
DUMPS_DIR = http.CACHE_DIR / "dumps"
JOBS = {
    "articles": "articlesdump",          # pages-articles.xml.bz2
    "page_props": "pagepropstable",
    "redirect": "redirecttable",
    "page": "pagetable",
    "pagelinks": "pagelinkstable",
    "templatelinks": "templatelinkstable",
    "linktarget": "linktargettable",
    "categorylinks": "categorylinkstable",
}


def run_dates(dbname: str) -> list[str]:
    status, body = http.get(f"{BASE}/{dbname}/")
    if status != 200:
        raise http.HttpError(status, f"{BASE}/{dbname}/")
    return sorted(set(re.findall(r'href="(\d{8})/"', body)), reverse=True)


def run_status(dbname: str, date: str) -> dict | None:
    status, body = http.get(f"{BASE}/{dbname}/{date}/dumpstatus.json")
    if status != 200:
        return None
    import json
    return json.loads(body)


def latest_run(dbname: str, kinds: list[str]) -> tuple[str, dict]:
    """Newest run date in which every job of kinds is done, with its status."""
    for date in run_dates(dbname)[:4]:
        st = run_status(dbname, date)
        if st and all(st["jobs"].get(JOBS[k], {}).get("status") == "done" for k in kinds):
            return date, st
    raise RuntimeError(f"no complete dump run of {dbname} with {kinds}")


def run_dir(dbname: str, date: str) -> Path:
    return DUMPS_DIR / dbname / date


def _sha1(path: Path) -> str:
    h = hashlib.sha1()
    with path.open("rb") as f:
        while chunk := f.read(1 << 22):
            h.update(chunk)
    return h.hexdigest()


def _download(url: str, dest: Path, size: int, sha1: str) -> None:
    part = dest.with_name(dest.name + ".part")
    host = "dumps.wikimedia.org"
    for attempt in range(http.MAX_RETRIES):
        have = part.stat().st_size if part.exists() else 0
        if have >= size:
            break
        headers = {"Range": f"bytes={have}-"} if have else {}
        http.wait_for_host(host)
        try:
            with http.session.get(url, headers=headers, stream=True, timeout=120) as resp:
                http.mark_request(host)
                if resp.status_code == 429 or resp.status_code >= 500:
                    time.sleep(http.retry_delay(attempt, resp.headers.get("Retry-After")))
                    continue
                if resp.status_code not in (200, 206):
                    raise http.HttpError(resp.status_code, url)
                mode = "ab" if resp.status_code == 206 else "wb"
                done = have if mode == "ab" else 0
                next_report = done + (256 << 20)
                with part.open(mode) as f:
                    for chunk in resp.iter_content(1 << 20):
                        f.write(chunk)
                        done += len(chunk)
                        if done >= next_report:
                            print(f"  {dest.name}: {done >> 20} / {size >> 20} MB",
                                  file=sys.stderr)
                            next_report += 256 << 20
        except requests.RequestException as exc:
            print(f"  network error ({exc}); resuming", file=sys.stderr)
            time.sleep(http.retry_delay(attempt, None))
    if not part.exists() or part.stat().st_size != size:
        raise RuntimeError(f"incomplete download of {url}")
    if _sha1(part) != sha1:
        part.unlink()
        raise RuntimeError(f"sha1 mismatch for {url}; partial file removed")
    part.rename(dest)


def _part_start(path: Path) -> int:
    m = re.search(r"-p(\d+)p\d+\.", path.name)
    return int(m.group(1)) if m else 0


def fetch(dbname: str, kinds: list[str], date: str | None = None) -> dict[str, list[Path]]:
    """Download the dump files of kinds (keys of JOBS); return kind -> paths.

    Large wikis split pages-articles into parts by page id; all parts are
    downloaded and returned in page-id order."""
    if date is None:
        date, st = latest_run(dbname, kinds)
    else:
        st = run_status(dbname, date)
        if st is None:
            raise RuntimeError(f"no dump run {dbname}/{date}")
    out_dir = run_dir(dbname, date)
    out_dir.mkdir(parents=True, exist_ok=True)
    paths = {}
    for kind in kinds:
        files = st["jobs"][JOBS[kind]]["files"]
        paths[kind] = []
        for name, meta in files.items():
            dest = out_dir / name
            if not dest.exists():
                print(f"downloading {name} ({meta['size'] >> 20} MB)", file=sys.stderr)
                _download(BASE + meta["url"], dest, meta["size"], meta["sha1"])
            paths[kind].append(dest)
        paths[kind].sort(key=_part_start)
    return paths


def local(dbname: str, kind: str) -> list[Path]:
    """The files of kind from the newest downloaded run of dbname, in order."""
    pattern = {"articles": "*-pages-articles*.bz2"}.get(kind, f"*-{kind}.sql.gz")
    for run in sorted(DUMPS_DIR.glob(f"{dbname}/*/"), reverse=True):
        found = [p for p in run.glob(pattern) if not p.name.endswith(".part")]
        if found:
            return sorted(found, key=_part_start)
    raise FileNotFoundError(f"no {kind} dump for {dbname}; run wscatalog.dumps")


if __name__ == "__main__":
    db, *kinds = sys.argv[1:]
    for kind, found in fetch(db, kinds or ["articles", "page_props"]).items():
        for path in found:
            print(kind, path)
