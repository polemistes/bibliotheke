# SPDX-License-Identifier: GPL-3.0-or-later
"""WP0: search a wiki to find sample pages.

    python3 pipeline/wp0/search.py no.wikisource.org 'insource:"<poem>"' [namespace] [limit]
"""

from __future__ import annotations

import sys

import wmhttp


def search(domain: str, query: str, namespace: int = 0, limit: int = 15) -> list[dict]:
    data = wmhttp.action_api(domain, action="query", list="search", srsearch=query,
                             srnamespace=namespace, srlimit=limit, srprop="size|wordcount")
    return data["query"]["search"]


if __name__ == "__main__":
    domain, query = sys.argv[1], sys.argv[2]
    ns = int(sys.argv[3]) if len(sys.argv) > 3 else 0
    limit = int(sys.argv[4]) if len(sys.argv) > 4 else 15
    for hit in search(domain, query, ns, limit):
        print(f"{hit['size']:>8}  {hit['title']}")
