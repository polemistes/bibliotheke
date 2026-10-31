# SPDX-License-Identifier: GPL-3.0-or-later
"""Wikitext helpers: templates, parameters, links, <pages> tags.

Just enough of a parser for the structure signals of PLAN.md 3.2 and 3.4.
The functions work on raw wikitext from the pages-articles dump; the app
reads the same signals from Parsoid's data-mw (PLAN.md 7.1).
"""

from __future__ import annotations

import html
import re

COMMENT = re.compile(r"<!--.*?(?:-->|$)", re.S)
NOWIKI = re.compile(r"<(nowiki|pre|math|syntaxhighlight|source)\b[^>]*?(?:/>|>.*?</\1\s*>)", re.S | re.I)
BRACES = re.compile(r"\{\{|\}\}")
TEMPLATE_START = re.compile(r"\{\{(?!\{)\s*([^{}|\n]*?)\s*(?=\||\}\}|\n)")
LINK = re.compile(r"\[\[([^\[\]|\n]*)(?:\|((?:[^\[\]]|\[\[[^\[\]]*\]\])*))?\]\]")
PAGES_TAG = re.compile(r"<pages\b((?:[^>\"']|\"[^\"]*\"|'[^']*')*?)/?>", re.I)
ATTR = re.compile(r"([\w-]+)\s*=\s*(?:\"([^\"]*)\"|'([^']*)'|([^\s\"'/>]+(?:/(?!\s*$)[^\s\"'/>]*)*))")
PAGEQUALITY = re.compile(r"<pagequality\s+level=\"?(\d)")


def clean(text: str) -> str:
    """Remove comments and nowiki-like blocks."""
    return NOWIKI.sub("", COMMENT.sub("", text))


def norm_title(title: str) -> str:
    """Normalise a page title: entities, underscores, spaces, first letter."""
    t = html.unescape(title).replace("_", " ").replace("‎", "").replace("‏", "")
    t = re.sub(r"[  ]+", " ", t).strip()
    return t[:1].upper() + t[1:] if t else t


def norm_template(name: str) -> str:
    """Template name with first letter uppercased and spaces normalised."""
    n = name.replace("_", " ").strip()
    n = re.sub(r"\s+", " ", n)
    return n[:1].upper() + n[1:] if n else n


def template_names(text: str) -> set[str]:
    """Names of all templates used (normalised, namespace prefix kept)."""
    return {norm_template(m.group(1)) for m in TEMPLATE_START.finditer(text) if m.group(1)}


def _matching_close(text: str, start: int) -> int:
    """Index just past the '}}' closing the '{{' at start, or -1."""
    depth = 0
    for m in BRACES.finditer(text, start):
        if m.group() == "{{":
            depth += 1
        else:
            depth -= 1
            if depth == 0:
                return m.end()
    return -1


def split_params(body: str) -> list[str]:
    """Split a template body at top-level pipes (not inside {{ }} or [[ ]])."""
    parts, depth_t, depth_l, cur, i = [], 0, 0, [], 0
    n = len(body)
    while i < n:
        two = body[i:i + 2]
        if two == "{{":
            depth_t += 1; cur.append(two); i += 2; continue
        if two == "}}" and depth_t:
            depth_t -= 1; cur.append(two); i += 2; continue
        if two == "[[":
            depth_l += 1; cur.append(two); i += 2; continue
        if two == "]]" and depth_l:
            depth_l -= 1; cur.append(two); i += 2; continue
        c = body[i]
        if c == "|" and not depth_t and not depth_l:
            parts.append("".join(cur)); cur = []
        else:
            cur.append(c)
        i += 1
    parts.append("".join(cur))
    return parts


def templates(text: str, names: set[str]) -> list[tuple[str, dict[str, str], int, int]]:
    """Top-level-or-nested occurrences of the named templates.

    Returns (name, params, start, end); params maps lower-cased names (or
    positional '1', '2', ...) to stripped values. names are normalised.
    """
    out = []
    for m in TEMPLATE_START.finditer(text):
        name = norm_template(m.group(1))
        if name not in names:
            continue
        start = m.start()
        end = _matching_close(text, start)
        if end < 0:
            continue
        body = text[start + 2:end - 2]
        pieces = split_params(body)
        params: dict[str, str] = {}
        pos = 0
        for piece in pieces[1:]:
            if "=" in piece and not piece.lstrip().startswith(("{{", "[[")) or (
                    "=" in piece and piece.split("=", 1)[0].strip().replace(" ", "").isalnum()):
                k, v = piece.split("=", 1)
                k = k.strip().lower()
                if k and "\n" not in k and len(k) < 60:
                    params.setdefault(k, v.strip())
                    continue
            pos += 1
            params.setdefault(str(pos), piece.strip())
        out.append((name, params, start, end))
    return out


def pages_tags(text: str) -> list[dict[str, str]]:
    """Attributes of every <pages .../> tag, keys lower-cased."""
    out = []
    for m in PAGES_TAG.finditer(text):
        attrs = {}
        for k, a, b, c in ATTR.findall(m.group(1)):
            attrs[k.lower()] = (a or b or c).strip()
        out.append(attrs)
    return out


def links(text: str) -> list[tuple[str, str | None]]:
    """(target, link text) of every wikilink in document order, raw."""
    return [(m.group(1), m.group(2)) for m in LINK.finditer(text)]


def first_link(value: str) -> str | None:
    m = LINK.search(value or "")
    return m.group(1) if m else None


def strip_markup(text: str) -> str:
    """Rough plain text: drop templates, tags, link brackets, quotes."""
    out, i = [], 0
    for m in TEMPLATE_START.finditer(text):
        if m.start() < i:
            continue
        end = _matching_close(text, m.start())
        if end < 0:
            break
        out.append(text[i:m.start()])
        i = end
    out.append(text[i:])
    t = "".join(out)
    t = re.sub(r"<[^>]+>", " ", t)
    t = re.sub(r"\[\[(?:[^\[\]|]*\|)?([^\[\]]*)\]\]", r"\1", t)
    t = re.sub(r"\[https?://\S+\s*([^\]]*)\]", r"\1", t)
    t = re.sub(r"'{2,}", "", t)
    t = re.sub(r"^[*#:;=|!{}-]+", "", t, flags=re.M)
    return re.sub(r"\s+", " ", t).strip()


def page_ranges(spec: str) -> set[int]:
    """Parse an include/exclude list such as '1-3,5 7'."""
    out: set[int] = set()
    for part in re.split(r"[,\s]+", spec or ""):
        if re.fullmatch(r"\d+", part):
            out.add(int(part))
        elif m := re.fullmatch(r"(\d+)-(\d+)", part):
            a, b = int(m.group(1)), int(m.group(2))
            if 0 <= b - a <= 5000:
                out.update(range(a, b + 1))
    return out
