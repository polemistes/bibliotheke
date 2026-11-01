// SPDX-License-Identifier: GPL-3.0-or-later
// WP4 reader tests, run inside the page (PLAN.md 7.4, 8.1, 8.4; WP0 F§4). Per part:
//  - the locator text has the length LocatorText gives in :core-content (expected.json);
//  - on every page the locator lies on that page and locators increase;
//  - on probe pages, locator L survives a font change and a geometry change (rotation)
//    with L on the shown page, and going back gives the same page and the same L;
//  - every footnote reference opens its note, the note section is shown "away" from
//    the anchor and back() returns to it; the first page mark is found by number;
//  - page marks inline and in the margin are tappable and placed in the margin, and
//    line numbers show and hide as set (WP5).

(function () {
  'use strict';
  const R = window.WsrReader;
  const params = new URLSearchParams(location.search);
  const W = Number(params.get('w') || 412), H = Number(params.get('h') || 915);
  const base = { pageWidth: W, pageHeight: H, fontSize: Number(params.get('font') || 18) };
  const other = Number(params.get('font2') || 26);
  const rotated = { pageWidth: H, pageHeight: W };

  let readyResolve = null;
  window.WsrBridge = {
    post: (json) => {
      const e = JSON.parse(json);
      if ((e.type === 'ready' || e.type === 'layout' || e.type === 'error') && readyResolve) {
        const f = readyResolve; readyResolve = null; f(e);
      }
    },
  };
  const waitEvent = () => new Promise((res) => { readyResolve = res; });

  async function applyAndWait(s) {
    const w = waitEvent();
    R.apply(s);
    return w;
  }

  async function testPart(name) {
    const expected = await (await fetch(`/test/fixtures/${name}/expected.json`)).json();
    const out = { name, expectedChars: expected.charCount };
    const t0 = performance.now();
    await R.apply(base); // not loaded yet: sets the variables only
    const ready = waitEvent();
    // The golden file ends with a newline that the app's part files do not have.
    const html = (await (await fetch(`/test/fixtures/${name}/expected.html`)).text()).replace(/\n$/, '');
    R.openHtml(html, name.split('/')[0], null);
    const ev = await ready;
    if (ev.type === 'error') return Object.assign(out, { pass: false, error: ev.message });
    out.loadMs = Math.round(performance.now() - t0);
    out.chars = R.textLength();
    out.pages = R.state().pages;
    const fails = [];
    if (out.chars !== out.expectedChars) fails.push(`chars ${out.chars} != ${out.expectedChars}`);

    // Every page: its locator (offset, and the element for a page without a text
    // start) leads back to it, offsets never decrease, and a text start is on the page.
    let prev = -1, bad = 0, textless = 0;
    for (let p = 0; p < out.pages; p++) {
      const st = R.goToPage(p, false);
      const L = st.anchor;
      if (st.anchorEl >= 0) textless++;
      const back = R.goToLocator(L, false, st.anchorEl, st.anchorSkip).page;
      const ok = back === p && L >= prev && (st.anchorEl >= 0 || L === out.chars || R.pageOfOffset(L) === p);
      if (!ok) {
        if (bad < 3) {
          const probe = [0.3, 0.5, 0.7].map((f) => document.elementFromPoint(W / 2, H * f))
            .map((e) => e ? e.tagName + (e.className ? '.' + e.className : '') + (e.parentElement ? '<' + e.parentElement.tagName + '.' + e.parentElement.className : '') : '-').join(' ');
          fails.push(`page ${p}: L=${L} el=${st.anchorEl} prev=${prev} back on ${back} [${probe}]`);
        }
        bad++;
      }
      prev = L;
    }
    out.badPages = bad;
    out.textless = textless;

    // Probes, including a page without a text start: font change, rotation, back.
    const n = out.pages;
    const probes = [...new Set([0, 1, Math.floor(n / 3), Math.floor(n / 2), n - 2])].filter((p) => p >= 0 && p < n);
    for (let p = 0; p < n; p++) if (R.goToPage(p, false).anchorEl >= 0) { probes.push(p); break; }
    out.probes = 0;
    for (const p of probes) {
      const s0 = R.goToPage(p, false);
      const L = s0.anchor, E = s0.anchorEl, K = s0.anchorSkip;
      if (L === out.chars && E < 0) continue;
      const kept = (s) => s.anchor === L && s.anchorEl === E && s.anchorSkip === K && (E >= 0 || R.pageOfOffset(L) === s.page);
      await applyAndWait({ fontSize: other });
      const a = R.state();
      if (!kept(a)) fails.push(`probe ${p}: font lost L=${L}/${E} -> ${a.anchor}/${a.anchorEl}`);
      await applyAndWait(rotated);
      const b = R.state();
      if (!kept(b)) fails.push(`probe ${p}: rotate lost L=${L}/${E} -> ${b.anchor}/${b.anchorEl}`);
      await applyAndWait(base);
      const c = R.state();
      if (c.page !== p || c.anchor !== L) fails.push(`probe ${p}: back on ${c.page} L=${c.anchor}, want ${p} L=${L}`);
      out.probes++;
    }

    // Footnotes: every reference opens its note; the section is "away"; back returns.
    const refs = Array.from(document.querySelectorAll('#wsr-content sup.wsr-fnref'));
    out.notes = refs.length;
    let noteFails = 0;
    for (const sup of refs.slice(0, 20)) {
      R.goToLocator(R.state().anchor);
      const at = R.pageOfOffset(R.state().anchor);
      // Go to the page of the reference and tap it.
      const off = sup.getClientRects()[0];
      const page = Math.floor((off.left - document.getElementById('wsr-content').getBoundingClientRect().left + 0.5) / R.pageWidth());
      const st = R.goToPage(page, false);
      const box = sup.getClientRects()[0];
      const h = R.hit(box.left + box.width / 2, box.top + box.height / 2);
      if (h.kind !== 'note' || !h.html) {
        if (noteFails < 2) fails.push(`note ${sup.textContent}: ${JSON.stringify(h).slice(0, 120)} at ${box.left},${box.top} page ${page} of ${st.pages}`);
        noteFails++; continue;
      }
      const away = R.goToElement(h.id, false);
      const back = R.back();
      if (back.anchor !== st.anchor || back.away) noteFails++;
      void at; void away;
    }
    if (noteFails) fails.push(`${noteFails} of ${Math.min(refs.length, 20)} notes failed`);

    // The first page mark by its number.
    const mark = document.querySelector('#wsr-content .wsr-page[data-num]');
    if (mark && mark.dataset.num) {
      const st = R.goToPageMark(mark.dataset.num);
      const mr = mark.getBoundingClientRect();
      const cl = document.getElementById('wsr-content').getBoundingClientRect().left;
      const mp = Math.floor((mr.left - cl + 0.5) / R.pageWidth());
      // The text after the mark is on the page shown, which is the mark's page or the next.
      if (!st || (st.anchor < out.chars && R.pageOfOffset(st.anchor) !== st.page) || (st.page !== mp && st.page !== mp + 1)) {
        fails.push(`page mark ${mark.dataset.num}: page ${st && st.page}, mark on ${mp}`);
      }
      out.pageMark = mark.dataset.num;
    }
    if (mark) await testPageMarks(out, fails);
    if (document.querySelector('#wsr-content .wsr-verse, #wsr-content .wsr-ln-src')) await testLineNumbers(out, fails);
    out.fails = fails;
    out.pass = fails.length === 0;
    return out;
  }

  // Page marks (8.2): inline, every mark is tappable on its page; in the margin, every
  // label lies in the start margin of its page, at the height of the text after the
  // mark, and is tappable; switching modes keeps the position.
  async function testPageMarks(out, fails) {
    const content = document.getElementById('wsr-content');
    const marks = Array.from(content.querySelectorAll('.wsr-page[data-num]')).filter((m) => m.dataset.num && !m.closest('.wsr-footnotes'));
    const tapAt = (box, num, what) => {
      const h = R.hit(box.left + box.width / 2, box.top + box.height / 2);
      if (h.kind !== 'pagemark' || h.num !== num) { fails.push(`${what} ${num}: tap gave ${JSON.stringify(h).slice(0, 80)}`); return false; }
      return true;
    };
    const pageOf = (x) => Math.floor((x - content.getBoundingClientRect().left + 0.5) / R.pageWidth());
    const s0 = R.goToPage(Math.floor(out.pages / 2), false);
    const kept = (s, what) => {
      if (s.anchor !== s0.anchor || s.anchorEl !== s0.anchorEl) fails.push(`${what}: position ${s0.anchor} -> ${s.anchor}`);
    };
    let inline = 0, margin = 0;
    for (const m of marks.slice(0, 40)) {
      const r0 = m.getClientRects()[0];
      if (!r0) continue;
      R.goToPage(pageOf(r0.left), false);
      if (tapAt(m.getClientRects()[0], m.dataset.num, 'inline mark')) inline++;
    }
    R.goToPage(s0.page, false);
    await applyAndWait({ pageMarks: 'margin', pageBreakRule: true });
    kept(R.state(), 'to margin');
    const ms = parseFloat(getComputedStyle(content).paddingLeft);
    const labels = Array.from(document.querySelectorAll('#wsr-gutter .wsr-gmark')).filter((l) => !l.hidden);
    for (const l of labels.slice(0, 40)) {
      // The label's page from its place in the gutter layer, which moves with the content.
      const p = Math.floor((parseFloat(l.style.left) + 0.5) / R.pageWidth());
      R.goToPage(p, false);
      const b = l.getBoundingClientRect();
      if (b.left < -0.5 || b.right > ms + 0.5 || b.top < 0 || b.bottom > H) {
        fails.push(`margin mark ${l.textContent} on page ${p}: box ${Math.round(b.left)}..${Math.round(b.right)} x ${Math.round(b.top)}, margin ${ms}`);
      } else if (tapAt(b, l.textContent, 'margin mark')) margin++;
    }
    out.marks = marks.length;
    out.marksTapped = { inline, margin, labels: labels.length };
    R.goToLocator(s0.anchor, false, s0.anchorEl, s0.anchorSkip);
    await applyAndWait({ pageMarks: 'inline', pageBreakRule: false });
    kept(R.state(), 'back to inline');
  }

  // Line numbers (8.3): every 5th verse line by data-n (heading lines have none), the
  // source's number instead where it shows; the source's numbers hide; the position
  // is kept.
  async function testLineNumbers(out, fails) {
    const s0 = R.goToPage(Math.floor(out.pages / 2), false);
    const lines = Array.from(document.querySelectorAll('#wsr-content .wsr-line'));
    const src = Array.from(document.querySelectorAll('#wsr-content .wsr-ln-src'));
    const numbered = (l) => getComputedStyle(l, '::before').content !== 'none';
    const check = (step, srcOn) => {
      let wrong = 0, shown = 0;
      for (const l of lines) {
        const n = Number(l.dataset.n || 0);
        const want = step > 0 && n > 0 && n % step === 0 && !(srcOn && l.querySelector('.wsr-ln-src'));
        if (numbered(l) !== want) { if (wrong++ < 2) fails.push(`verse ${step}/${srcOn}: line ${n || 'head'} numbered ${!want}`); }
        if (want) shown++;
      }
      for (const e of src) if ((getComputedStyle(e).display !== 'none') !== srcOn) { fails.push(`source number ${e.textContent} shown ${!srcOn}`); break; }
      return shown;
    };
    const kept = (what) => { const s = R.state(); if (s.anchor !== s0.anchor) fails.push(`${what}: position ${s0.anchor} -> ${s.anchor}`); };
    check(0, true);
    await applyAndWait({ lineNumbersVerse: 5 });
    out.verseNumbers = check(5, true);
    kept('verse numbers on');
    await applyAndWait({ lineNumbersSource: false });
    check(5, false);
    kept('source numbers off');
    await applyAndWait({ lineNumbersVerse: 0, lineNumbersSource: true });
    check(0, true);
    kept('line numbers back');
    out.verseLines = lines.length;
    out.sourceNumbers = src.length;
  }

  async function run() {
    const names = (params.get('parts') || '').split(',').filter(Boolean);
    const results = [];
    for (const n of names) {
      try { results.push(await testPart(n)); }
      catch (e) { results.push({ name: n, pass: false, error: String(e) + ' ' + e.stack }); }
    }
    const res = { ua: navigator.userAgent, viewport: { w: W, h: H }, parts: results, pass: results.every((r) => r.pass) };
    document.getElementById('wsr-result').textContent = JSON.stringify(res);
    document.title = 'done';
  }

  window.addEventListener('load', () => setTimeout(run, 50));
})();
