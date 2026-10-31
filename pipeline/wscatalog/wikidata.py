# SPDX-License-Identifier: GPL-3.0-or-later
"""Wikidata items for the metadata merge (PLAN.md 5.1 input 3, 5.2 step 3).

Edition items (the root pages' sitelinks), their work items (P629) and the
author items (P50, P655, and the QIDs of the author pages that headers link)
are fetched with wbgetentities in batches of 50 and trimmed to what the
merge reads. The trimmed JSON is cached in pipeline/cache/wikidata.sqlite;
an item is fetched again when it is older than max_age_days.

Trimmed form: {"l": {lang: label}, "d": {lang: description},
"c": {prop: [QID | [year, precision], ...]}, "s": {site: title}}; claim
values are ordered preferred rank first, deprecated values dropped.
"""

from __future__ import annotations

import json
import sqlite3
import sys
import time
from pathlib import Path

from . import http

API = "https://www.wikidata.org/w/api.php"
DB_PATH = http.CACHE_DIR / "wikidata.sqlite"
PROPS = ("P31", "P50", "P98", "P136", "P407", "P577", "P629", "P655", "P569", "P570")
TIME_PROPS = {"P577", "P569", "P570"}
# Label languages kept for every item: the configured wikis' languages,
# multilingual labels and English as the fallback.
LANGS = ("mul", "en", "nb", "nn", "da", "sv", "fi", "is", "de", "fr", "it", "es", "la")
BATCH = 50


def _time(v: dict) -> list[int] | None:
    t = v.get("time") or ""
    try:
        year = int(t[:t.index("-", 1)])
    except ValueError:
        return None
    return [year, int(v.get("precision") or 9)]


def trim(entity: dict, sites: set[str]) -> dict:
    out = {"l": {k: v["value"] for k, v in (entity.get("labels") or {}).items()},
           "d": {k: v["value"] for k, v in (entity.get("descriptions") or {}).items()},
           "c": {}, "s": {}}
    for prop in PROPS:
        vals = []
        for st in sorted(entity.get("claims", {}).get(prop, ()),
                         key=lambda s: s.get("rank") != "preferred"):
            if st.get("rank") == "deprecated":
                continue
            dv = (st.get("mainsnak") or {}).get("datavalue") or {}
            v = dv.get("value")
            if prop in TIME_PROPS:
                v = _time(v) if isinstance(v, dict) else None
            elif isinstance(v, dict) and v.get("id"):
                v = v["id"]
            else:
                v = None
            if v is not None and v not in vals:
                vals.append(v)
        if vals:
            out["c"][prop] = vals
    for site, sl in (entity.get("sitelinks") or {}).items():
        if site in sites:
            out["s"][site] = sl["title"]
    redirect = entity.get("redirects")
    if redirect:
        out["r"] = redirect.get("to")
    return out


class Wikidata:
    def __init__(self, langs: tuple[str, ...] = LANGS, sites: set[str] | None = None,
                 max_age_days: float = 28, offline: bool = False, path: Path = DB_PATH):
        self.langs = tuple(dict.fromkeys(langs))
        self.sites = set(sites or ())
        self.max_age = max_age_days * 86400
        self.offline = offline
        path.parent.mkdir(parents=True, exist_ok=True)
        self.db = sqlite3.connect(path, timeout=120)
        self.db.execute("PRAGMA journal_mode=WAL")      # several builds may share the cache
        self.db.execute("CREATE TABLE IF NOT EXISTS items(qid TEXT PRIMARY KEY, fetched REAL, "
                        "json TEXT)")
        self.mem: dict[str, dict | None] = {}
        self.fetched = 0

    def ensure(self, qids) -> None:
        """Load qids into memory, fetching missing or stale items."""
        want = [q for q in dict.fromkeys(qids) if q and q not in self.mem]
        if not want:
            return
        now, stale = time.time(), []
        for i in range(0, len(want), 500):
            chunk = want[i:i + 500]
            rows = self.db.execute(
                f"SELECT qid, fetched, json FROM items WHERE qid IN ({','.join('?' * len(chunk))})",
                chunk).fetchall()
            got = {q: (f, j) for q, f, j in rows}
            for q in chunk:
                if q in got:
                    f, j = got[q]
                    self.mem[q] = json.loads(j) if j else None
                    if now - f > self.max_age:
                        stale.append(q)
                else:
                    stale.append(q)
        if self.offline or not stale:
            return
        for i in range(0, len(stale), BATCH):
            self._fetch(stale[i:i + BATCH])
            self.db.commit()
            if (i // BATCH) % 40 == 39:
                print(f"  wikidata: {i + BATCH:,} of {len(stale):,} items", file=sys.stderr)

    def _fetch(self, qids: list[str]) -> None:
        params = {"action": "wbgetentities", "ids": "|".join(qids), "format": "json",
                  "props": "labels|descriptions|claims|sitelinks", "maxlag": "5",
                  "languages": "|".join(self.langs)}
        if self.sites:
            params["sitefilter"] = "|".join(sorted(self.sites))
        data = http.get_json(API, params)
        if "error" in data:
            # an invalid id fails the whole batch: fetch one by one
            if len(qids) > 1:
                for q in qids:
                    self._fetch([q])
                return
            ents = {qids[0]: {"missing": ""}}
        else:
            ents = data.get("entities") or {}
        now = time.time()
        for q in qids:
            e = ents.get(q)
            if e is None:          # redirected: wbgetentities keys the result by the target
                e = next((x for x in ents.values() if (x.get("redirects") or {}).get("from") == q),
                         {"missing": ""})
            t = None if "missing" in e else trim(e, self.sites)
            self.mem[q] = t
            self.db.execute("INSERT OR REPLACE INTO items VALUES (?,?,?)",
                            (q, now, json.dumps(t, ensure_ascii=False) if t else None))
        self.fetched += len(qids)

    # ---- access ----------------------------------------------------------
    def get(self, qid: str | None) -> dict | None:
        return self.mem.get(qid) if qid else None

    def claims(self, qid: str | None, prop: str) -> list:
        it = self.get(qid)
        return list((it or {}).get("c", {}).get(prop, ()))

    def label(self, qid: str | None, langs: tuple[str, ...]) -> str | None:
        it = self.get(qid)
        if not it:
            return None
        for lang in langs + ("mul", "en"):
            if it["l"].get(lang):
                return it["l"][lang]
        return next(iter(it["l"].values()), None)

    def description(self, qid: str | None, langs: tuple[str, ...]) -> str | None:
        it = self.get(qid)
        if not it:
            return None
        for lang in langs + ("en",):
            if it["d"].get(lang):
                return it["d"][lang]
        return None

    def close(self) -> None:
        self.db.commit()
        self.db.close()
