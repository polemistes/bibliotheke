// SPDX-License-Identifier: GPL-3.0-or-later
// Evaluates a JavaScript expression in every reader page of the app's WebViews through
// the DevTools protocol (debug builds allow WebView debugging) and prints one JSON line
// per page: {i, screenX, screenY, value}. Node 22 or later (built-in fetch, WebSocket).
//   node cdp.mjs <forwarded port> '<expression>'
const [port, expr] = process.argv.slice(2);
const list = await (await fetch(`http://127.0.0.1:${port}/json`)).json();
const pages = list.filter((p) => p.type === 'page' && p.url.includes('/reader/reader.html'));
for (const [i, p] of pages.entries()) {
  const ws = new WebSocket(p.webSocketDebuggerUrl);
  await new Promise((resolve, reject) => { ws.onopen = resolve; ws.onerror = reject; });
  const value = await new Promise((resolve) => {
    ws.onmessage = (e) => {
      const m = JSON.parse(e.data);
      if (m.id === 1) resolve(m.result?.result?.value ?? null);
    };
    ws.send(JSON.stringify({ id: 1, method: 'Runtime.evaluate', params: { expression: expr, returnByValue: true, awaitPromise: true } }));
  });
  ws.close();
  const d = JSON.parse(p.description || '{}');
  console.log(JSON.stringify({ i, screenX: d.screenX ?? 0, screenY: d.screenY ?? 0, value }));
}
