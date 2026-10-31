# SPDX-License-Identifier: GPL-3.0-or-later
"""Polite HTTP access to Wikimedia for the WP0 spike scripts.

Implements the network rules of PLAN.md section 0 and N6:
- every request carries the project User-Agent;
- requests are serial, with a minimum interval per host;
- 429 / 5xx / maxlag responses are retried with exponential backoff,
  honouring Retry-After;
- responses are cached on disk so re-running a script does not hit the
  wikis again (pipeline/cache/ is gitignored).

This module is spike code; WP1 moves it into the pipeline package.
"""

from __future__ import annotations

import hashlib
import json
import random
import sys
import time
from pathlib import Path
from urllib.parse import urlencode, urlsplit

import requests

APP_VERSION = "0.1.0-dev"
# Public repository URL for the User-Agent. Not known yet (see STATUS.md);
# while it is None the UA carries only the contact address.
REPO_URL: str | None = None
CONTACT = "develop@robertemilberge.no"


def user_agent() -> str:
    contact = f"{REPO_URL}; {CONTACT}" if REPO_URL else CONTACT
    return f"Bibliotheke/{APP_VERSION} ({contact}) python-requests/{requests.__version__}"


CACHE_DIR = Path(__file__).resolve().parents[1] / "cache" / "wp0"
MIN_INTERVAL_S = 0.2
MAX_RETRIES = 6

_session = requests.Session()
_session.headers["User-Agent"] = user_agent()
_session.headers["Accept-Encoding"] = "gzip"
_last_request_at: dict[str, float] = {}


class HttpError(Exception):
    def __init__(self, status: int, url: str, body: str = ""):
        super().__init__(f"HTTP {status} for {url}")
        self.status = status
        self.url = url
        self.body = body


def _cache_path(url: str) -> Path:
    digest = hashlib.sha256(url.encode()).hexdigest()
    return CACHE_DIR / digest[:2] / digest


def _wait_for_host(host: str) -> None:
    last = _last_request_at.get(host)
    if last is not None:
        delay = MIN_INTERVAL_S - (time.monotonic() - last)
        if delay > 0:
            time.sleep(delay)


def _retry_delay(attempt: int, retry_after: str | None) -> float:
    if retry_after and retry_after.isdigit():
        return float(retry_after)
    return min(60.0, 2.0**attempt) + random.uniform(0, 1)


def get(url: str, params: dict | None = None, *, use_cache: bool = True,
        accept: str | None = None) -> tuple[int, str]:
    """GET url and return (status, body). 404 is returned, not raised."""
    if params:
        url = f"{url}?{urlencode(params)}"
    cache_file = _cache_path(url)
    if use_cache and cache_file.exists():
        cached = json.loads(cache_file.read_text("utf-8"))
        return cached["status"], cached["body"]

    host = urlsplit(url).netloc
    headers = {"Accept": accept} if accept else {}
    for attempt in range(MAX_RETRIES):
        _wait_for_host(host)
        try:
            resp = _session.get(url, headers=headers, timeout=60)
        except requests.RequestException as exc:
            _last_request_at[host] = time.monotonic()
            print(f"  network error ({exc}); retrying", file=sys.stderr)
            time.sleep(_retry_delay(attempt, None))
            continue
        _last_request_at[host] = time.monotonic()

        maxlagged = resp.headers.get("MediaWiki-API-Error") == "maxlag"
        if resp.status_code == 429 or resp.status_code >= 500 or maxlagged:
            delay = _retry_delay(attempt, resp.headers.get("Retry-After"))
            print(f"  HTTP {resp.status_code} from {host}; waiting {delay:.1f}s",
                  file=sys.stderr)
            time.sleep(delay)
            continue
        if resp.status_code not in (200, 404):
            raise HttpError(resp.status_code, url, resp.text[:500])

        resp.encoding = "utf-8"
        if use_cache:
            cache_file.parent.mkdir(parents=True, exist_ok=True)
            cache_file.write_text(json.dumps({"status": resp.status_code,
                                              "body": resp.text}), "utf-8")
        return resp.status_code, resp.text
    raise HttpError(-1, url, "retries exhausted")


def get_json(url: str, params: dict | None = None, **kw) -> dict:
    status, body = get(url, params, accept="application/json", **kw)
    if status != 200:
        raise HttpError(status, url, body[:500])
    return json.loads(body)


def action_api(domain: str, **params) -> dict:
    """Call the MediaWiki Action API on domain (e.g. 'no.wikisource.org')."""
    query = {"format": "json", "formatversion": "2", "maxlag": "5", **params}
    data = get_json(f"https://{domain}/w/api.php", query)
    if "error" in data:
        raise HttpError(200, domain, json.dumps(data["error"]))
    return data
