// SPDX-License-Identifier: GPL-3.0-or-later
// WP0 pagination prototype: CSS-column pages and (partId, charOffset)
// locators as specified in PLAN.md 7.4 and 8.1.
//
// Locator text = concatenation of all text nodes of the part in document
// order, excluding .wsr-footnotes, .wsr-page and .wsr-ln-src. Text flows down
// each column and then into the next, so the page index of a character never
// decreases with its offset; both directions (offset -> page and
// page -> first offset) are binary searches over Range rectangles.

(function () {
  'use strict';

  const EXCLUDE = '.wsr-footnotes, .wsr-page, .wsr-ln-src';
  const LAYOUT_UNIT = 64; // Blink lays out in 1/64 px; snapping avoids drift

  let content = null;
  let pageW = 0;
  let page = 0;
  let pages = 1;
  let nodes = [];       // locator text nodes
  let starts = [];      // locator offset of each node
  let total = 0;
  let measurable = [];  // indices of nodes that produce boxes (not collapsed)
  let settings = {};
  const range = document.createRange();

  function bridge(event, data) {
    if (window.WsrBridge && window.WsrBridge.onEvent) {
      window.WsrBridge.onEvent(event, JSON.stringify(data));
    }
  }

  function init(el) {
    content = el;
    window.addEventListener('resize', () => relayoutKeeping());
  }

  function buildIndex() {
    nodes = []; starts = []; total = 0;
    const walker = document.createTreeWalker(content, NodeFilter.SHOW_TEXT, {
      acceptNode: (n) => (n.parentElement.closest(EXCLUDE) ? NodeFilter.FILTER_REJECT
                                                            : NodeFilter.FILTER_ACCEPT),
    });
    for (let n = walker.nextNode(); n; n = walker.nextNode()) {
      nodes.push(n); starts.push(total); total += n.data.length;
    }
    measurable = [];
    for (let i = 0; i < nodes.length; i++) {
      range.selectNodeContents(nodes[i]);
      if (range.getClientRects().length > 0) measurable.push(i);
    }
  }

  function load(html, lang) {
    content.innerHTML = html;
    document.documentElement.lang = lang || '';
    page = 0;
    buildIndex();
    layout();
  }

  function setVar(name, value) {
    document.documentElement.style.setProperty(name, value);
  }

  function layout() {
    const raw = settings.pageWidth || document.documentElement.getBoundingClientRect().width;
    const h = settings.pageHeight || document.documentElement.getBoundingClientRect().height;
    pageW = Math.floor(raw * LAYOUT_UNIT) / LAYOUT_UNIT;
    setVar('--page-w', pageW + 'px');
    setVar('--page-h', h + 'px');
    pages = countPages();
    page = Math.min(page, pages - 1);
    setTransform(false);
  }

  function apply(next) {
    settings = Object.assign({}, settings, next);
    const map = {
      fontSize: ['--font-size', (v) => v + 'px'],
      lineHeight: ['--line-height', String],
      marginH: ['--margin-h', (v) => v + 'px'],
      marginV: ['--margin-v', (v) => v + 'px'],
      fontFamily: ['--font-family', String],
      textAlign: ['--text-align', String],
      hyphens: ['--hyphens', String],
    };
    for (const [key, [cssVar, fmt]] of Object.entries(map)) {
      if (settings[key] !== undefined) setVar(cssVar, fmt(settings[key]));
    }
    layout();
  }

  // Re-layout after a geometry or settings change, keeping the reading position.
  function relayoutKeeping(next) {
    const loc = getLocator();
    if (next) apply(next); else layout();
    return goToLocator(loc);
  }

  function originX() {
    return content.getBoundingClientRect().left; // includes the page transform
  }

  function pageOfX(x, origin) {
    return Math.floor((x - origin) / pageW);
  }

  function nodeRects(i) {
    range.selectNodeContents(nodes[i]);
    return range.getClientRects();
  }

  function charRect(i, k) {
    const len = nodes[i].data.length;
    if (len === 0) return null;
    k = Math.max(0, Math.min(k, len - 1));
    range.setStart(nodes[i], k);
    range.setEnd(nodes[i], k + 1);
    const rects = range.getClientRects();
    for (const r of rects) if (r.width > 0 || r.height > 0) return r;
    return null;
  }

  function countPages() {
    if (!measurable.length) return 1;
    const origin = originX();
    const last = nodeRects(measurable[measurable.length - 1]);
    const textPages = pageOfX(last[last.length - 1].left, origin) + 1;
    // Non-text content after the last text node (an image, a rule) can add a page.
    const boxPages = Math.ceil((content.scrollWidth - 1) / pageW);
    return Math.max(1, textPages, boxPages);
  }

  function setTransform(animate) {
    content.classList.toggle('wsr-animating', !!animate);
    content.style.transform = `translate3d(${-page * pageW}px,0,0)`;
    bridge('page', { page, pages });
  }

  function goToPage(p, animate) {
    page = Math.max(0, Math.min(p, pages - 1));
    setTransform(animate);
    return page;
  }

  function turn(dir) {
    return goToPage(page + dir, true);
  }

  function nodeOf(offset) {
    let lo = 0, hi = starts.length - 1;
    while (lo < hi) {
      const mid = (lo + hi + 1) >> 1;
      if (starts[mid] <= offset) lo = mid; else hi = mid - 1;
    }
    return lo;
  }

  // Page on which the character at `offset` is laid out. Collapsed
  // whitespace has no box; it counts as the next character that has one.
  function pageOfOffset(offset) {
    const origin = originX();
    let i = nodeOf(Math.max(0, Math.min(offset, total - 1)));
    let k = offset - starts[i];
    for (;;) {
      const r = charRect(i, k);
      if (r) return pageOfX(r.left, origin);
      k++;
      if (k >= nodes[i].data.length) {
        i++; k = 0;
        if (i >= nodes.length) return pages - 1;
      }
    }
  }

  // First locator offset laid out on page p (or the first one after it).
  function firstOffsetOnPage(p) {
    if (!measurable.length) return 0;
    const origin = originX();
    // 1. First measurable node whose last box is on page >= p.
    let lo = 0, hi = measurable.length - 1, found = measurable.length;
    while (lo <= hi) {
      const mid = (lo + hi) >> 1;
      const rects = nodeRects(measurable[mid]);
      if (pageOfX(rects[rects.length - 1].left, origin) >= p) { found = mid; hi = mid - 1; }
      else lo = mid + 1;
    }
    if (found === measurable.length) return total;
    const i = measurable[found];
    const first = nodeRects(i)[0];
    if (pageOfX(first.left, origin) >= p) return starts[i];
    // 2. Inside that node: first character on page >= p.
    let a = 0, b = nodes[i].data.length - 1, k = b;
    while (a <= b) {
      const mid = (a + b) >> 1;
      let m = mid, r = charRect(i, m);
      while (!r && m < nodes[i].data.length - 1) r = charRect(i, ++m);
      if (r && pageOfX(r.left, origin) >= p) { k = mid; b = mid - 1; }
      else a = mid + 1;
    }
    return starts[i] + k;
  }

  function getLocator() {
    return firstOffsetOnPage(page);
  }

  function goToLocator(offset) {
    return goToPage(pageOfOffset(offset));
  }

  function text(from, to) {
    let out = '';
    for (let i = nodeOf(from); i < nodes.length && starts[i] < to; i++) {
      out += nodes[i].data.slice(Math.max(0, from - starts[i]), to - starts[i]);
    }
    return out;
  }

  window.WsrReader = {
    init, load, apply, layout, relayoutKeeping, goToPage, turn, getLocator, goToLocator,
    pageOfOffset, firstOffsetOnPage, text,
    pageCount: () => pages,
    currentPage: () => page,
    textLength: () => total,
    pageWidth: () => pageW,
  };
})();
