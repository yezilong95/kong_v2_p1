(() => {
  const MIN_CELLS = 4;
  const HIT_RATIO = 0.34;
  // 视觉半径相对 hitRadius；命中区仍用 HIT_RATIO，只缩小圆点外观
  const DOT_RATIO_IDLE = 0.16;
  const DOT_RATIO_ACTIVE = 0.22;

  const canvas = document.getElementById("patternCanvas");
  const wrap = document.getElementById("patternWrap");
  const ctx = canvas.getContext("2d");

  let cells = [];
  let drawing = false;
  let finger = { x: 0, y: 0 };
  let dots = [];
  let hitRadius = 24;

  function bridge() {
    try {
      return window.MiluBridge || null;
    } catch (_) {
      return null;
    }
  }

  function reportDone(payload) {
    const b = bridge();
    if (b && typeof b.done === "function") {
      try {
        b.done(payload);
      } catch (_) {
        /* ignore */
      }
    }
  }

  function cellRowCol(cell) {
    const index = cell - 1;
    return { row: Math.floor(index / 3), col: index % 3 };
  }

  function cellFromRowCol(row, col) {
    return row * 3 + col + 1;
  }

  function intermediateCellBetween(from, to) {
    const a = cellRowCol(from);
    const b = cellRowCol(to);
    const dr = b.row - a.row;
    const dc = b.col - a.col;
    const absDr = Math.abs(dr);
    const absDc = Math.abs(dc);
    if (absDr === 2 && absDc === 0) {
      return cellFromRowCol(a.row + dr / 2, a.col);
    }
    if (absDc === 2 && absDr === 0) {
      return cellFromRowCol(a.row, a.col + dc / 2);
    }
    if (absDr === 2 && absDc === 2) {
      return cellFromRowCol(a.row + dr / 2, a.col + dc / 2);
    }
    return null;
  }

  function appendCell(next) {
    if (!cells.length) {
      cells.push(next);
      return;
    }
    const last = cells[cells.length - 1];
    if (last === next) {
      return;
    }
    const bridgeCell = intermediateCellBetween(last, next);
    if (bridgeCell != null && cells[cells.length - 1] !== bridgeCell) {
      cells.push(bridgeCell);
    }
    if (cells[cells.length - 1] !== next) {
      cells.push(next);
    }
  }

  function buildDots(width, height) {
    const cellW = width / 3;
    const cellH = height / 3;
    hitRadius = Math.min(cellW, cellH) * HIT_RATIO;
    dots = [];
    for (let cell = 1; cell <= 9; cell += 1) {
      const col = (cell - 1) % 3;
      const row = Math.floor((cell - 1) / 3);
      dots.push({
        cell,
        x: col * cellW + cellW / 2,
        y: row * cellH + cellH / 2,
      });
    }
  }

  function nearestDot(x, y) {
    let best = null;
    let bestDist = Number.POSITIVE_INFINITY;
    dots.forEach((dot) => {
      const dist = Math.hypot(x - dot.x, y - dot.y);
      if (dist <= hitRadius && dist < bestDist) {
        bestDist = dist;
        best = dot.cell;
      }
    });
    return best;
  }

  function sampleSegment(x1, y1, x2, y2, steps) {
    const list = [];
    for (let i = 0; i <= steps; i += 1) {
      const t = i / steps;
      list.push({
        x: x1 + (x2 - x1) * t,
        y: y1 + (y2 - y1) * t,
      });
    }
    return list;
  }

  function extendFromFinger(x, y) {
    if (!cells.length) {
      const hit = nearestDot(x, y);
      if (hit != null) {
        appendCell(hit);
      }
      return;
    }
    const lastDot = dots.find((dot) => dot.cell === cells[cells.length - 1]);
    if (!lastDot) {
      return;
    }
    sampleSegment(lastDot.x, lastDot.y, x, y, 16).forEach((point) => {
      const hit = nearestDot(point.x, point.y);
      if (hit != null) {
        appendCell(hit);
      }
    });
  }

  function resizeCanvas() {
    const rect = wrap.getBoundingClientRect();
    const size = Math.min(rect.width, rect.height, 320);
    const dpr = window.devicePixelRatio || 1;
    canvas.width = Math.round(size * dpr);
    canvas.height = Math.round(size * dpr);
    canvas.style.width = `${size}px`;
    canvas.style.height = `${size}px`;
    ctx.setTransform(dpr, 0, 0, dpr, 0, 0);
    buildDots(size, size);
    draw();
  }

  function draw() {
    const width = canvas.width / (window.devicePixelRatio || 1);
    const height = canvas.height / (window.devicePixelRatio || 1);
    ctx.clearRect(0, 0, width, height);

    if (cells.length) {
      ctx.beginPath();
      cells.forEach((cell, index) => {
        const dot = dots.find((item) => item.cell === cell);
        if (!dot) {
          return;
        }
        if (index === 0) {
          ctx.moveTo(dot.x, dot.y);
        } else {
          ctx.lineTo(dot.x, dot.y);
        }
      });
      if (drawing && cells.length) {
        ctx.lineTo(finger.x, finger.y);
      }
      ctx.strokeStyle = "rgba(255,255,255,0.9)";
      ctx.lineWidth = 3;
      ctx.lineCap = "round";
      ctx.lineJoin = "round";
      ctx.stroke();
    }

    dots.forEach((dot) => {
      const active = cells.includes(dot.cell);
      ctx.beginPath();
      ctx.fillStyle = "#ffffff";
      ctx.arc(
        dot.x,
        dot.y,
        active ? hitRadius * DOT_RATIO_ACTIVE : hitRadius * DOT_RATIO_IDLE,
        0,
        Math.PI * 2,
      );
      ctx.fill();
    });
  }

  function localPoint(event) {
    const rect = canvas.getBoundingClientRect();
    return {
      x: event.clientX - rect.left,
      y: event.clientY - rect.top,
    };
  }

  function onStart(event) {
    event.preventDefault();
    drawing = true;
    cells = [];
    const point = localPoint(event);
    finger = point;
    extendFromFinger(point.x, point.y);
    draw();
  }

  function onMove(event) {
    if (!drawing) {
      return;
    }
    event.preventDefault();
    const point = localPoint(event);
    finger = point;
    extendFromFinger(point.x, point.y);
    draw();
  }

  function onEnd(event) {
    if (!drawing) {
      return;
    }
    event.preventDefault();
    drawing = false;
    const point = localPoint(event);
    finger = point;
    extendFromFinger(point.x, point.y);
    draw();

    if (cells.length >= MIN_CELLS) {
      reportDone(cells.join("-"));
      return;
    }
    cells = [];
    draw();
  }

  canvas.addEventListener("pointerdown", onStart);
  canvas.addEventListener("pointermove", onMove);
  canvas.addEventListener("pointerup", onEnd);
  canvas.addEventListener("pointercancel", onEnd);
  canvas.addEventListener("pointerleave", onEnd);
  window.addEventListener("resize", resizeCanvas);
  resizeCanvas();
})();
