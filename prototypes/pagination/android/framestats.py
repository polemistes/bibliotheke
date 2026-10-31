# SPDX-License-Identifier: GPL-3.0-or-later
"""Per-frame timing from `dumpsys gfxinfo <pkg> framestats` (stdin).

Frames are grouped into page turns (a gap of > 100 ms starts a new turn). Per
turn it prints the number of frames, the largest gap between consecutive
frames (16.7 ms means no vsync was skipped, i.e. 60 fps), and which frames
completed after their deadline (what gfxinfo counts as "Frame deadline missed").
"""

import sys

lines = sys.stdin.read().splitlines()
start = lines.index("---PROFILEDATA---")
header = lines[start + 1].split(",")
col = {name: i for i, name in enumerate(header) if name}
frames = []
for line in lines[start + 2:]:
    if line.startswith("---PROFILEDATA---"):
        break
    f = line.split(",")
    if f[0] != "0":  # skip frames flagged as non-representative
        continue
    vsync = int(f[col["IntendedVsync"]])
    done = int(f[col["FrameCompleted"]])
    deadline = int(f[col["FrameDeadline"]]) if "FrameDeadline" in col else None
    frames.append({"vsync": vsync, "late": deadline is not None and done > deadline,
                   "late_ms": (done - deadline) / 1e6 if deadline else 0.0})

turns, current = [], []
for prev, frame in zip([None] + frames, frames):
    if prev is not None and (frame["vsync"] - prev["vsync"]) / 1e6 > 100:
        turns.append(current)
        current = []
    current.append(frame)
if current:
    turns.append(current)

for i, t in enumerate(turns):
    gaps = [(b["vsync"] - a["vsync"]) / 1e6 for a, b in zip(t, t[1:])]
    late = [(j, round(f["late_ms"], 1)) for j, f in enumerate(t) if f["late"]]
    print(f"turn {i + 1}: {len(t)} frames over {sum(gaps):.0f} ms, max gap {max(gaps, default=0):.1f} ms, "
          f"late frames (index, ms past deadline): {late}")
