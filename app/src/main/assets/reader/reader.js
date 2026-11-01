// SPDX-License-Identifier: GPL-3.0-or-later
// The reader page (PLAN.md 8.1, 8.4, 7.4; WP0 F§4): one part laid out in CSS columns,
// one column per screen page, and (part, charOffset) locators.
//
// Locator text = the text nodes of the part in document order, excluding
// .wsr-footnotes, .wsr-page and .wsr-ln-src (the same string as LocatorText in
// :core-content). Text flows down each column and then into the next, so the page of
// a character never decreases with its offset; both directions (offset → page and
// page → first offset) are binary searches over Range rectangles.
//
// The page talks to the app through WsrBridge.post(json) for events that come later
// (ready, layout); everything else returns its result to evaluateJavascript.

(function () {
  'use strict';

  const EXCLUDE = '.wsr-footnotes, .wsr-page, .wsr-ln-src';
  const LAYOUT_UNIT = 64; // Blink lays out in 1/64 px; snapping avoids drift (F§4.1)

  const content = document.getElementById('wsr-content');
  const marks = document.getElementById('wsr-marks');
  const range = document.createRange();

  let pageW = 0;
  let page = 0;
  let pages = 1;
  let nodes = [];       // locator text nodes
  let starts = [];      // locator offset of each node
  let total = 0;
  let measurable = [];  // indices of nodes that have boxes (not collapsed whitespace)
  let elements = [];    // every element of the part, in document order
  // Reading position. Only navigation (turns, jumps) sets it; a relayout restores it
  // without re-reading it, or every rotation or font change would move the position
  // back to the start of the new page (F§4.6).
  let anchor = 0;
  // A page with no locator text starting on it (an image, a run of footnotes: F§4.6)
  // is anchored at an element as well, by its index in [elements] (-1 otherwise): the
  // first element starting on the page, or else the last one starting before it, then
  // [anchorSkip] pages on (a long note running over several pages).
  let anchorEl = -1;
  let anchorSkip = 0;
  // Showing a page away from the anchor (a footnote section opened from a note); the
  // app offers to go back.
  let away = false;
  let settings = { tableHint: '' };
  let loaded = false;
  let generation = 0;
  let fadeTimer = 0;

  function post(type, data) {
    if (window.WsrBridge && window.WsrBridge.post) {
      window.WsrBridge.post(JSON.stringify(Object.assign({ type }, data)));
    }
  }

  function state() {
    return { page, pages, anchor, anchorEl, anchorSkip, total, away, loaded };
  }

  // --- Loading -------------------------------------------------------------------

  // Loads a part and goes to target: {offset, el}, {last: true}, {page}, {element: id}
  // or nothing (the start). Posts 'ready' with the state when it is laid out.
  async function open(url, lang, target) {
    const gen = ++generation;
    loaded = false;
    let html;
    try {
      const r = await fetch(url);
      if (!r.ok) throw new Error('HTTP ' + r.status);
      html = await r.text();
    } catch (e) {
      if (gen === generation) post('error', { message: String(e) });
      return;
    }
    if (gen !== generation) return;
    show(html, lang);
    await settle();
    if (gen !== generation) return;
    layout();
    loaded = true;
    go(target || {});
    post('ready', state());
  }

  // The same for an HTML string (tests, and parts given directly).
  async function openHtml(html, lang, target) {
    const gen = ++generation;
    loaded = false;
    show(html, lang);
    await settle();
    if (gen !== generation) return;
    layout();
    loaded = true;
    go(target || {});
    post('ready', state());
  }

  function show(html, lang) {
    clearMarks();
    content.classList.remove('wsr-animating');
    content.innerHTML = html;
    document.documentElement.lang = lang || '';
    // Every table gets a wrapper so a wide one can be marked and opened (8.1).
    for (const t of Array.from(content.querySelectorAll('table'))) {
      if (t.parentElement.closest('table')) continue;
      const w = document.createElement('div');
      w.className = 'wsr-table';
      t.replaceWith(w);
      w.appendChild(t);
    }
    page = 0;
    anchor = 0;
    anchorEl = -1;
    anchorSkip = 0;
    away = false;
    elements = Array.from(content.getElementsByTagName('*'));
    buildIndex();
  }

  // Waits for the fonts in use and for the images, which can change the layout.
  async function settle() {
    content.getBoundingClientRect(); // starts the font loads for the laid-out text
    const imgs = Array.from(content.querySelectorAll('img'));
    await Promise.all(imgs.map((i) => (i.decode ? i.decode() : Promise.resolve()).catch(() => {})));
    if (document.fonts) await document.fonts.ready;
  }

  function go(t) {
    if (t.element) goToElement(t.element, true);
    else if (t.last) goToPage(pages - 1, false);
    else if (t.page !== undefined) goToPage(t.page, false);
    else goToLocator(t.offset || 0, false, t.el, t.skip);
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
  }

  function measure() {
    measurable = [];
    for (let i = 0; i < nodes.length; i++) {
      range.selectNodeContents(nodes[i]);
      if (range.getClientRects().length > 0) measurable.push(i);
    }
  }

  // --- Layout --------------------------------------------------------------------

  function setVar(name, value) {
    document.documentElement.style.setProperty(name, value);
  }

  function layout() {
    const box = document.documentElement.getBoundingClientRect();
    const raw = settings.pageWidth || box.width;
    const h = settings.pageHeight || box.height;
    pageW = Math.floor(raw * LAYOUT_UNIT) / LAYOUT_UNIT;
    setVar('--page-w', pageW + 'px');
    setVar('--page-h', h + 'px');
    const col = content.clientWidth - 2 * parseFloat(getComputedStyle(content).paddingLeft);
    for (const w of content.querySelectorAll('.wsr-table')) {
      w.dataset.hint = settings.tableHint || '';
      w.classList.toggle('wsr-wide', w.firstElementChild.scrollWidth > col + 1);
    }
    measure();
    pages = countPages();
    page = Math.min(page, pages - 1);
    setTransform(false);
  }

  // Applies reader settings (8.5). Keys: fontSize, lineHeight, marginH, marginV,
  // fontFamily, textAlign, hyphens, bg, fg, muted, mark, tableHint. Posts 'layout'
  // once the new layout is done, with the position kept.
  async function apply(next) {
    settings = Object.assign({}, settings, next);
    const map = {
      fontSize: ['--font-size', (v) => v + 'px'],
      lineHeight: ['--line-height', String],
      marginH: ['--margin-h', (v) => v + 'px'],
      marginV: ['--margin-v', (v) => v + 'px'],
      fontFamily: ['--font-family', String],
      textAlign: ['--text-align', String],
      hyphens: ['--hyphens', String],
      bg: ['--bg', String],
      fg: ['--fg', String],
      muted: ['--muted', String],
      mark: ['--mark', String],
    };
    for (const [key, [cssVar, fmt]] of Object.entries(map)) {
      if (settings[key] !== undefined) setVar(cssVar, fmt(settings[key]));
    }
    if (!loaded) return;
    const gen = generation;
    await settle();
    if (gen !== generation || !loaded) return;
    relayout();
    post('layout', state());
  }

  // After a geometry or settings change: lay out again and go back to the anchor.
  function relayout() {
    const wasAway = away;
    const p = page;
    layout();
    if (wasAway) goToPage(Math.min(p, pages - 1), false, true);
    else goToLocator(anchor, false, anchorEl, anchorSkip);
  }

  window.addEventListener('resize', () => {
    if (!loaded) return;
    relayout();
    post('layout', state());
  });

  function originX() {
    return content.getBoundingClientRect().left; // includes the page transform
  }

  function pageOfX(x, origin) {
    return Math.floor((x - origin + 0.5) / pageW);
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
    const boxPages = Math.ceil((content.scrollWidth - 1) / pageW);
    if (!measurable.length) return Math.max(1, boxPages);
    const origin = originX();
    const last = nodeRects(measurable[measurable.length - 1]);
    const textPages = pageOfX(last[last.length - 1].left, origin) + 1;
    // Content after the last text node (an image, the footnotes) can add pages.
    return Math.max(1, textPages, boxPages);
  }

  function setTransform(animate) {
    content.classList.toggle('wsr-animating', !!animate);
    content.style.transform = `translate3d(${-page * pageW}px,0,0)`;
  }

  // --- Navigation ----------------------------------------------------------------

  function goToPage(p, animate, keepAnchor) {
    page = Math.max(0, Math.min(p, pages - 1));
    if (page !== p) animate = false;
    setTransform(animate);
    clearMarks();
    // Rects are measured relative to the content box, which carries the same
    // (possibly mid-animation) transform, so this is valid during a turn (F§4.6).
    if (!keepAnchor) {
      anchor = firstOffsetOnPage(page);
      [anchorEl, anchorSkip] = anchor < total && pageOfOffset(anchor) === page ? [-1, 0] : elementAnchor(page);
      away = false;
    }
    return state();
  }

  // One page forward (+1) or back (-1); {edge: dir} at either end of the part, where
  // the app turns to the neighbouring part.
  function turn(dir) {
    const p = page + dir;
    if (p < 0 || p >= pages) return Object.assign(state(), { edge: dir });
    return goToPage(p, true);
  }

  function nodeOf(offset) {
    let lo = 0, hi = starts.length - 1;
    while (lo < hi) {
      const mid = (lo + hi + 1) >> 1;
      if (starts[mid] <= offset) lo = mid; else hi = mid - 1;
    }
    return lo;
  }

  // Page on which the character at `offset` is laid out. Collapsed whitespace has no
  // box; it counts as the next character that has one. Past the text: the last page
  // that has text.
  function pageOfOffset(offset) {
    if (!nodes.length) return 0;
    const origin = originX();
    let i = nodeOf(Math.max(0, Math.min(offset, total - 1)));
    let k = Math.max(0, offset - starts[i]);
    for (;;) {
      const r = k < nodes[i].data.length ? charRect(i, k) : null;
      if (r) return Math.max(0, Math.min(pageOfX(r.left, origin), pages - 1));
      k++;
      if (k >= nodes[i].data.length) {
        i++; k = 0;
        if (i >= nodes.length) {
          if (!measurable.length) return 0;
          const last = nodeRects(measurable[measurable.length - 1]);
          return Math.min(pageOfX(last[last.length - 1].left, origin), pages - 1);
        }
      }
    }
  }

  // First locator offset laid out on page p (or the first one after it; `total` when
  // no text follows, e.g. a page holding only the footnotes).
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

  // Page of the first box of element i; -1 if it has none.
  function elementPage(i, origin) {
    const r = elements[i].getClientRects();
    return r.length ? pageOfX(r[0].left, origin) : -1;
  }

  // [element, skip] for page p (see anchorEl). Elements start in document order, so
  // this is a binary search; one without boxes counts as the next that has one.
  function elementAnchor(p) {
    const origin = originX();
    const key = (i) => {
      for (; i < elements.length; i++) {
        const pg = elementPage(i, origin);
        if (pg >= 0) return [i, pg];
      }
      return [elements.length, Infinity];
    };
    let lo = 0, hi = elements.length;
    while (lo < hi) {
      const mid = (lo + hi) >> 1;
      if (key(mid)[1] >= p) hi = mid; else lo = mid + 1;
    }
    const [i, pg] = key(lo);
    if (pg === p) return [i, 0];
    // None starts on p: the last element with a box that starts before it.
    for (let j = lo - 1; j >= 0; j--) {
      const pj = elementPage(j, origin);
      if (pj >= 0) return [j, p - pj];
    }
    return [-1, 0];
  }

  // To a locator: the page of character `offset`, or `skip` pages after the start of
  // element `el` (>= 0) when the position was a page without a text start.
  function goToLocator(offset, animate, el, skip) {
    anchor = Math.max(0, Math.min(offset, total));
    anchorEl = el !== undefined && el >= 0 && el < elements.length ? el : -1;
    anchorSkip = anchorEl >= 0 ? skip || 0 : 0;
    away = false;
    page = anchorPage();
    setTransform(!!animate);
    clearMarks();
    return state();
  }

  function anchorPage() {
    const ep = anchorEl >= 0 ? elementPage(anchorEl, originX()) : -1;
    return ep >= 0 ? Math.min(ep + anchorSkip, pages - 1) : pageOfOffset(anchor);
  }

  // Locator offset of an element: its first locator character, or the first one after
  // it (an empty anchor, a page mark).
  function offsetOfElement(el) {
    if (!nodes.length) return 0;
    let lo = 0, hi = nodes.length;
    while (lo < hi) {
      const mid = (lo + hi) >> 1;
      const n = nodes[mid];
      const after = el === n || el.contains(n) ||
        (el.compareDocumentPosition(n) & Node.DOCUMENT_POSITION_FOLLOWING);
      if (after) hi = mid; else lo = mid + 1;
    }
    return lo < nodes.length ? starts[lo] : total;
  }

  function byId(id) {
    try { return document.getElementById(id) || content.querySelector(`[name="${CSS.escape(id)}"]`); }
    catch (e) { return null; }
  }

  // An element by id: text elements move the anchor (a link into the part); anything
  // outside the locator text (the footnotes) is shown "away" from it.
  function goToElement(id, asNavigation) {
    const el = byId(id);
    if (!el) return goToLocator(anchor, false, anchorEl, anchorSkip);
    if (asNavigation && !el.closest(EXCLUDE)) return goToLocator(offsetOfElement(el));
    const r = el.getBoundingClientRect();
    const st = goToPage(pageOfX(r.left, originX()), false, true);
    away = page !== anchorPage();
    st.away = away;
    return st;
  }

  // Back from a footnote section to the anchor.
  function back() {
    return goToLocator(anchor, false, anchorEl, anchorSkip);
  }

  // The page with an original page mark (8.6), anchored at the text after the mark.
  function goToPageMark(num) {
    for (const m of content.querySelectorAll('.wsr-page')) {
      if (m.dataset.num === num) return goToLocator(offsetOfElement(m));
    }
    return null;
  }

  function text(from, to) {
    let out = '';
    if (!nodes.length) return out;
    for (let i = nodeOf(from); i < nodes.length && starts[i] < to; i++) {
      out += nodes[i].data.slice(Math.max(0, from - starts[i]), Math.max(0, to - starts[i]));
    }
    return out;
  }

  // --- Taps, notes, highlights -----------------------------------------------------

  // What is under a tap at (x, y) in CSS px of the viewport (the overlay takes every
  // touch, F§4.6): a footnote reference, a link, a page mark, a wide table or an
  // image. Small targets get a radius of about a finger tip.
  function hit(x, y) {
    const probes = [[0, 0], [0, -8], [0, 8], [-8, 0], [8, 0], [-12, -12], [12, -12], [-12, 12], [12, 12]];
    for (const [dx, dy] of probes) {
      const r = hitAt(x + dx, y + dy);
      if (r) return r;
    }
    return { kind: 'none' };
  }

  function hitAt(x, y) {
    const el = document.elementFromPoint(x, y);
    if (!el || !content.contains(el)) return null;
    const fn = el.closest('sup.wsr-fnref');
    if (fn) {
      const a = fn.querySelector('a[href^="#"]');
      const id = a ? decodeURIComponent(a.getAttribute('href').slice(1)) : '';
      const note = id ? byId(id) : null;
      return { kind: 'note', id, label: a ? a.textContent.trim() : '', html: note ? noteHtml(note) : '' };
    }
    const mark = el.closest('.wsr-page');
    if (mark) return { kind: 'pagemark', num: mark.dataset.num || '', title: mark.dataset.pageTitle || '' };
    const a = el.closest('a[href]');
    if (a) {
      const href = a.getAttribute('href');
      if (href.startsWith('#')) return { kind: 'anchor', id: decodeURIComponent(href.slice(1)) };
      if (href.startsWith('app://part/')) {
        const m = /^app:\/\/part\/(\d+)(?:#(.*))?$/.exec(href);
        if (m) return { kind: 'part', index: Number(m[1]), id: m[2] ? decodeURIComponent(m[2]) : '' };
      }
      return { kind: 'external', href: a.href };
    }
    const wide = el.closest('.wsr-table.wsr-wide');
    if (wide) return { kind: 'table', html: wide.innerHTML };
    if (el.tagName === 'IMG') return { kind: 'image', src: el.src, alt: el.alt || '' };
    return null;
  }

  function noteHtml(li) {
    const c = li.cloneNode(true);
    for (const b of c.querySelectorAll('.mw-cite-backlink')) b.remove();
    return c.innerHTML.trim();
  }

  // The text of a footnote by id (for the app's note sheet).
  function note(id) {
    const n = byId(id);
    return n ? noteHtml(n) : '';
  }

  // Highlights [from, to) for a moment: a bookmark or a search hit (8.6).
  function flash(from, to, ms) {
    clearMarks();
    if (!nodes.length || to <= from) return;
    const r = document.createRange();
    const a = nodeOf(from), b = nodeOf(Math.max(from, to - 1));
    r.setStart(nodes[a], Math.min(from - starts[a], nodes[a].data.length));
    r.setEnd(nodes[b], Math.min(to - starts[b], nodes[b].data.length));
    const vw = window.innerWidth;
    for (const q of r.getClientRects()) {
      if (q.width <= 0 || q.right < 0 || q.left > vw) continue;
      const d = document.createElement('div');
      d.style.left = q.left + 'px'; d.style.top = q.top + 'px';
      d.style.width = q.width + 'px'; d.style.height = q.height + 'px';
      marks.appendChild(d);
    }
    fadeTimer = setTimeout(() => {
      marks.classList.add('wsr-fading');
      fadeTimer = setTimeout(clearMarks, 700);
    }, ms || 1800);
  }

  function clearMarks() {
    clearTimeout(fadeTimer);
    marks.classList.remove('wsr-fading');
    marks.textContent = '';
  }

  window.WsrReader = {
    open, openHtml, apply, layout, relayout, state, turn, goToPage, goToLocator,
    goToElement, goToPageMark, back, hit, note, flash, text,
    pageOfOffset, firstOffsetOnPage,
    excerpt: (from, n) => text(from, from + (n || 160)).replace(/\s+/g, ' ').trim(),
    textLength: () => total,
    pageWidth: () => pageW,
  };
})();
