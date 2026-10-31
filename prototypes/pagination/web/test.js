// SPDX-License-Identifier: GPL-3.0-or-later
// WP0 locator tests, run inside the page (browser harness and Android WebView).
//
// For each part:
//  - every page's locator lies on that page, and locators strictly increase
//    from page to page (checked on all pages at the base font size);
//  - for probe pages: locator L at font A -> change font size (and, separately,
//    page geometry, as on rotation) -> restore L -> L is on the shown page;
//    then back to font A -> restore L -> same page and same locator as before.

(function () {
  'use strict';
  const R = () => window.WsrReader;

  function sentenceAround(offset) {
    const from = Math.max(0, offset - 120);
    const t = R().text(from, Math.min(R().textLength(), offset + 160));
    const at = offset - from;
    const start = Math.max(t.lastIndexOf('. ', at), t.lastIndexOf('! ', at), t.lastIndexOf('? ', at));
    const ends = ['. ', '! ', '? '].map((s) => t.indexOf(s, at)).filter((i) => i >= 0);
    const end = ends.length ? Math.min(...ends) + 1 : t.length;
    return t.slice(start >= 0 ? start + 2 : 0, end).replace(/\s+/g, ' ').trim();
  }

  function timed(fn) {
    const t0 = performance.now();
    const v = fn();
    return [v, Math.round((performance.now() - t0) * 10) / 10];
  }

  function testPart(name, baseSettings, other, geometry) {
    const base = baseSettings.fontSize;
    const part = window.WSR_PARTS[name];
    const r = R();
    const [, loadMs] = timed(() => { r.load(part.html, part.lang); r.apply(baseSettings); });
    const pagesA = r.pageCount();
    const out = { name, chars: r.textLength(), base, other, pagesA, loadMs, pageWidth: r.pageWidth() };

    // All pages: locator on its page, strictly increasing.
    const [allPages, allMs] = timed(() => {
      const bad = []; let prev = -1;
      for (let p = 0; p < pagesA; p++) {
        r.goToPage(p);
        const L = r.getLocator();
        const onPage = r.pageOfOffset(L) === p;
        if (!onPage || L <= prev) bad.push({ page: p, L, prev, onPage });
        prev = L;
      }
      return bad;
    });
    out.allPagesChecked = pagesA;
    out.allPagesFailures = allPages.slice(0, 5);
    out.allPagesFailureCount = allPages.length;
    out.allPagesMs = allMs;

    const [, relayoutMs] = timed(() => r.apply({ fontSize: other }));
    out.pagesB = r.pageCount();
    out.relayoutMs = relayoutMs;
    r.apply(baseSettings);

    const probes = [...new Set([0, 1, Math.floor(pagesA / 3), Math.floor(pagesA / 2),
                                pagesA - 2, pagesA - 1])].filter((p) => p >= 0 && p < pagesA);
    out.probes = probes.map((p) => {
      r.apply(baseSettings);
      r.goToPage(p);
      const L = r.getLocator();
      const probe = { page: p, locator: L, sentence: sentenceAround(L) };
      // Font size change.
      r.apply({ fontSize: other });
      probe.pageAfterFont = r.goToLocator(L);
      probe.visibleAfterFont = r.pageOfOffset(L) === r.currentPage();
      // Geometry change (landscape / tablet), as on rotation.
      r.apply(geometry);
      probe.pageAfterRotate = r.goToLocator(L);
      probe.visibleAfterRotate = r.pageOfOffset(L) === r.currentPage();
      // Back to the original font and geometry.
      r.apply(baseSettings);
      probe.pageBack = r.goToLocator(L);
      probe.locatorBack = r.getLocator();
      probe.exactRoundTrip = probe.pageBack === p && probe.locatorBack === L;
      return probe;
    });
    out.pass = out.allPagesFailureCount === 0 &&
      out.probes.every((q) => q.visibleAfterFont && q.visibleAfterRotate && q.exactRoundTrip);
    return out;
  }

  function run(names, params) {
    const baseSettings = { fontSize: Number(params.get('font') || 18),
      pageWidth: Number(params.get('w')) || undefined, pageHeight: Number(params.get('h')) || undefined };
    const other = Number(params.get('font2') || 26);
    const geometry = { pageWidth: Number(params.get('rotW') || 915), pageHeight: Number(params.get('rotH') || 412) };
    const ua = navigator.userAgent;
    const viewport = { w: window.innerWidth, h: window.innerHeight, dpr: window.devicePixelRatio };
    const parts = names.map((n) => testPart(n, baseSettings, other, geometry));
    return { ua, viewport, parts, pass: parts.every((p) => p.pass) };
  }

  window.WsrTest = { run, testPart };
})();
