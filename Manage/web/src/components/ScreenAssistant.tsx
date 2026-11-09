import {
  memo,
  useMemo,
  useRef,
  useState,
  type CSSProperties,
  type PointerEvent as ReactPointerEvent,
} from "react";
import { message } from "antd";
import { sendAdbInput, sendCommand } from "../api/client";
import type { UiTreeBounds, UiTreeNode, UiTreeSnapshot } from "../types";
import {
  resolveNodeLabel,
  filterHumanUsefulNodes,
  filterNodesByPrimaryPackage,
  getUiTreeDisplayMax,
  isPatternUiNode,
  selectDisplayNodes,
  type UiTreeDisplayMode,
} from "../utils/ui-tree";
import { MirrorSkeleton } from "./ScreenSkeleton";

interface ScreenAssistantProps {
  deviceId: string | null;
  isOnline: boolean;
  snapshot: UiTreeSnapshot | null;
  screenWidth: number;
  screenHeight: number;
  errorText?: string | null;
  displayMode?: UiTreeDisplayMode;
  translationMap?: Record<string, string>;
  onNodeClick?: (node: UiTreeNode) => void;
  allowPattern?: boolean;
  useAdbInput?: boolean;
}

interface SurfacePoint {
  left: number;
  top: number;
  deviceX: number;
  deviceY: number;
}

interface SwipePreview {
  x1: number;
  y1: number;
  length: number;
  angle: number;
}

const SWIPE_MIN_DEVICE_PX = 24;
const SWIPE_MIN_HORIZONTAL_DEVICE_PX = 12;
const PATTERN_SAMPLE_MIN_DEVICE_PX = 6;
const PATTERN_MIN_CELLS = 4;
/** 拖线到圆心的命中半径（相对单格边长），小于 √2/2 可避免 7→5 误吸 8/4 */
const PATTERN_DOT_LINE_HIT_RATIO = 0.34;

function nodeKey(node: UiTreeNode): string {
  return `${node.viewId}:${node.bounds.left}:${node.bounds.top}:${node.bounds.right}:${node.bounds.bottom}`;
}

function scaleBoundsStyle(
  bounds: UiTreeBounds,
  screenWidth: number,
  screenHeight: number,
): CSSProperties {
  const leftPct = (bounds.left / screenWidth) * 100;
  const topPct = (bounds.top / screenHeight) * 100;
  const widthPct = ((bounds.right - bounds.left) / screenWidth) * 100;
  const heightPct = ((bounds.bottom - bounds.top) / screenHeight) * 100;
  return {
    left: `${leftPct}%`,
    top: `${topPct}%`,
    width: `${widthPct}%`,
    height: `${heightPct}%`,
  };
}

function scaleStyle(
  node: UiTreeNode,
  screenWidth: number,
  screenHeight: number,
): CSSProperties {
  return scaleBoundsStyle(node.bounds, screenWidth, screenHeight);
}

function buildSwipePreview(start: SurfacePoint, end: SurfacePoint): SwipePreview {
  const dx = end.left - start.left;
  const dy = end.top - start.top;
  return {
    x1: start.left,
    y1: start.top,
    length: Math.hypot(dx, dy),
    angle: (Math.atan2(dy, dx) * 180) / Math.PI,
  };
}

function findNodeAtPoint(nodes: UiTreeNode[], deviceX: number, deviceY: number): UiTreeNode | null {
  let best: UiTreeNode | null = null;
  let bestArea = Number.POSITIVE_INFINITY;
  for (const node of nodes) {
    const { left, top, right, bottom } = node.bounds;
    if (deviceX < left || deviceX > right || deviceY < top || deviceY > bottom) {
      continue;
    }
    const area = Math.max(1, (right - left) * (bottom - top));
    if (area < bestArea) {
      bestArea = area;
      best = node;
    }
  }
  return best;
}

function isPatternViewNode(node: UiTreeNode): boolean {
  if (isPatternUiNode(node)) {
    return true;
  }
  const text = `${node.text} ${node.desc}`.toLowerCase();
  return text.includes("图案区域") || text.includes("绘制图案") || text.includes("draw pattern");
}

function findPatternNode(nodes: UiTreeNode[]): UiTreeNode | null {
  let best: UiTreeNode | null = null;
  let bestArea = 0;
  for (const node of nodes) {
    if (!isPatternViewNode(node)) {
      continue;
    }
    const area = Math.max(
      0,
      (node.bounds.right - node.bounds.left) * (node.bounds.bottom - node.bounds.top),
    );
    // Prefer real LockPatternView over tiny "绘制图案" labels
    const classHit =
      node.className.toLowerCase().includes("pattern") ||
      node.viewId.toLowerCase().includes("pattern");
    const score = area + (classHit ? 1_000_000 : 0);
    if (score > bestArea) {
      bestArea = score;
      best = node;
    }
  }
  return best;
}

function pointInBounds(bounds: UiTreeBounds, x: number, y: number): boolean {
  return x >= bounds.left && x <= bounds.right && y >= bounds.top && y <= bounds.bottom;
}

function cellRowCol(cell: number): { row: number; col: number } {
  const index = cell - 1;
  return { row: Math.floor(index / 3), col: index % 3 };
}

function cellFromRowCol(row: number, col: number): number {
  return row * 3 + col + 1;
}

/** 与 Android LockPatternView 一致：跨两格时自动补中间格点（含斜对角） */
function intermediateCellBetween(from: number, to: number): number | null {
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

function appendPatternCell(cells: number[], next: number) {
  if (cells.length === 0) {
    cells.push(next);
    return;
  }
  const last = cells[cells.length - 1];
  if (last === next) {
    return;
  }
  const bridge = intermediateCellBetween(last, next);
  if (bridge != null && cells[cells.length - 1] !== bridge) {
    cells.push(bridge);
  }
  if (cells[cells.length - 1] !== next) {
    cells.push(next);
  }
}

function patternHitRadius(bounds: UiTreeBounds): number {
  const cellSize = Math.min(bounds.right - bounds.left, bounds.bottom - bounds.top) / 3;
  return cellSize * PATTERN_DOT_LINE_HIT_RATIO;
}

function distancePointToSegment(
  px: number,
  py: number,
  x1: number,
  y1: number,
  x2: number,
  y2: number,
): { distance: number; t: number } {
  const dx = x2 - x1;
  const dy = y2 - y1;
  const lenSq = dx * dx + dy * dy;
  if (lenSq === 0) {
    return { distance: Math.hypot(px - x1, py - y1), t: 0 };
  }
  let t = ((px - x1) * dx + (py - y1) * dy) / lenSq;
  t = Math.max(0, Math.min(1, t));
  const projX = x1 + t * dx;
  const projY = y1 + t * dy;
  return { distance: Math.hypot(px - projX, py - projY), t };
}

function nearestDotCell(
  dots: Array<{ cell: number; x: number; y: number }>,
  x: number,
  y: number,
  hitRadius: number,
): number | null {
  let best: number | null = null;
  let bestDist = Number.POSITIVE_INFINITY;
  for (const dot of dots) {
    const dist = Math.hypot(x - dot.x, y - dot.y);
    if (dist <= hitRadius && dist < bestDist) {
      bestDist = dist;
      best = dot.cell;
    }
  }
  return best;
}

/** 从上一格圆心到当前手指：取拖线附近的圆心（LockPatternView 思路，非网格分区） */
function cellsAlongDragSegment(
  dots: Array<{ cell: number; x: number; y: number }>,
  from: { x: number; y: number },
  to: { x: number; y: number },
  hitRadius: number,
): number[] {
  const hits: Array<{ cell: number; t: number }> = [];
  for (const dot of dots) {
    const { distance, t } = distancePointToSegment(dot.x, dot.y, from.x, from.y, to.x, to.y);
    if (distance <= hitRadius) {
      hits.push({ cell: dot.cell, t });
    }
  }
  hits.sort((a, b) => a.t - b.t || a.cell - b.cell);
  const seen = new Set<number>();
  const cells: number[] = [];
  for (const hit of hits) {
    if (seen.has(hit.cell)) {
      continue;
    }
    seen.add(hit.cell);
    cells.push(hit.cell);
  }
  return cells;
}

function extendCellsFromDrag(
  dots: Array<{ cell: number; x: number; y: number }>,
  cells: number[],
  finger: { x: number; y: number },
  hitRadius: number,
) {
  if (cells.length === 0) {
    const start = nearestDotCell(dots, finger.x, finger.y, hitRadius);
    if (start != null) {
      appendPatternCell(cells, start);
    }
    return;
  }
  const lastCell = cells[cells.length - 1];
  const fromDot = dots.find((dot) => dot.cell === lastCell);
  if (!fromDot) {
    return;
  }
  const along = cellsAlongDragSegment(
    dots,
    { x: fromDot.x, y: fromDot.y },
    finger,
    hitRadius,
  );
  for (const cell of along) {
    appendPatternCell(cells, cell);
  }
}

function cellsFromPathPoints(
  bounds: UiTreeBounds,
  dots: Array<{ cell: number; x: number; y: number }>,
  points: Array<{ x: number; y: number }>,
): number[] {
  const cells: number[] = [];
  const hitRadius = patternHitRadius(bounds);
  for (const point of points) {
    extendCellsFromDrag(dots, cells, point, hitRadius);
  }
  return cells;
}

function deviceToSurfacePoint(
  deviceX: number,
  deviceY: number,
  screenWidth: number,
  screenHeight: number,
  surfaceWidth: number,
  surfaceHeight: number,
): Pick<SurfacePoint, "left" | "top" | "deviceX" | "deviceY"> {
  const relX = deviceX / Math.max(1, screenWidth);
  const relY = deviceY / Math.max(1, screenHeight);
  return {
    left: relX * surfaceWidth,
    top: relY * surfaceHeight,
    deviceX,
    deviceY,
  };
}

function patternDotCenters(bounds: UiTreeBounds): Array<{ cell: number; x: number; y: number }> {
  const w = Math.max(1, bounds.right - bounds.left);
  const h = Math.max(1, bounds.bottom - bounds.top);
  const cellW = w / 3;
  const cellH = h / 3;
  const dots: Array<{ cell: number; x: number; y: number }> = [];
  for (let cell = 1; cell <= 9; cell += 1) {
    const index = cell - 1;
    const col = index % 3;
    const row = Math.floor(index / 3);
    dots.push({
      cell,
      x: bounds.left + (col + 0.5) * cellW,
      y: bounds.top + (row + 0.5) * cellH,
    });
  }
  return dots;
}

function nodeCenter(node: UiTreeNode): { x: number; y: number } {
  return {
    x: Math.round((node.bounds.left + node.bounds.right) / 2),
    y: Math.round((node.bounds.top + node.bounds.bottom) / 2),
  };
}

/** 列表平铺用：有文案/可点的优先，避免大容器刷屏 */
function listNodes(nodes: UiTreeNode[]): UiTreeNode[] {
  const scored = nodes.map((node) => {
    let score = 0;
    if (node.text) score += 4;
    if (node.desc) score += 3;
    if (node.clickable) score += 3;
    if (node.viewId) score += 1;
    const area =
      (node.bounds.right - node.bounds.left) * (node.bounds.bottom - node.bounds.top);
    return { node, score, area };
  });
  return scored
    .sort((a, b) => b.score - a.score || a.area - b.area)
    .map((item) => item.node);
}

/** 窄节点内嵌文字会被裁切；标签改为浮层，字号随节点高度自适应 */
function labelFontSizePx(node: UiTreeNode, screenHeight: number): number {
  const heightPct = ((node.bounds.bottom - node.bounds.top) / Math.max(1, screenHeight)) * 100;
  const approx = Math.round(heightPct * 3.2);
  return Math.min(12, Math.max(9, approx));
}

const AssistantNode = memo(function AssistantNode({
  node,
  screenWidth,
  screenHeight,
  active,
  translationMap,
}: {
  node: UiTreeNode;
  screenWidth: number;
  screenHeight: number;
  active: boolean;
  translationMap?: Record<string, string>;
}) {
  const label = resolveNodeLabel(node, translationMap);
  const classNames = [
    "screen-assistant-node",
    node.clickable ? "screen-assistant-node--clickable" : "",
    label ? "screen-assistant-node--labeled" : "",
    active ? "screen-assistant-node--active" : "",
  ]
    .filter(Boolean)
    .join(" ");

  return (
    <div
      className={classNames}
      style={scaleStyle(node, screenWidth, screenHeight)}
      title={`${label}\n${node.viewId || node.className}`}
    >
      {label ? (
        <span
          className="screen-assistant-node-label"
          style={{ fontSize: `${labelFontSizePx(node, screenHeight)}px` }}
        >
          {label}
        </span>
      ) : null}
    </div>
  );
});

export function ScreenAssistant({
  deviceId,
  isOnline,
  snapshot,
  screenWidth,
  screenHeight,
  errorText,
  displayMode = "compact",
  translationMap,
  onNodeClick,
  allowPattern = false,
  useAdbInput = false,
}: ScreenAssistantProps) {
  const surfaceRef = useRef<HTMLDivElement>(null);
  const dragStartRef = useRef<SurfacePoint | null>(null);
  const dragEndRef = useRef<SurfacePoint | null>(null);
  const pathPointsRef = useRef<SurfacePoint[]>([]);
  const patternCellsRef = useRef<number[]>([]);
  const patternDrawingRef = useRef(false);
  const [isDragging, setIsDragging] = useState(false);
  const [swipePreview, setSwipePreview] = useState<SwipePreview | null>(null);
  const [patternPolyline, setPatternPolyline] = useState<SurfacePoint[]>([]);
  const [patternCellsHit, setPatternCellsHit] = useState<number[]>([]);
  const [patternBusy, setPatternBusy] = useState(false);
  const [activeKey, setActiveKey] = useState<string | null>(null);

  const nodes = useMemo(() => {
    if (!snapshot) {
      return [];
    }
    const scoped = filterNodesByPrimaryPackage(snapshot.nodes, snapshot.packageName);
    const filtered = displayMode === "compact" ? filterHumanUsefulNodes(scoped) : scoped;
    return selectDisplayNodes(
      filtered,
      getUiTreeDisplayMax(displayMode),
      screenWidth,
      screenHeight,
    );
  }, [displayMode, snapshot, screenWidth, screenHeight]);

  const tiledNodes = useMemo(() => listNodes(nodes), [nodes]);

  // 必须在完整 UI 树上找图案区，不能用简洁/采样后的 nodes（lockPattern 常被滤掉 → 画了没反应）
  const patternNode = useMemo(() => {
    if (!allowPattern || !snapshot) {
      return null;
    }
    const scoped = filterNodesByPrimaryPackage(snapshot.nodes, snapshot.packageName);
    return findPatternNode(scoped);
  }, [allowPattern, snapshot]);
  const patternBounds = patternNode?.bounds ?? null;
  const patternDots = useMemo(
    () => (patternBounds ? patternDotCenters(patternBounds) : []),
    [patternBounds],
  );

  const surfaceKey = snapshot ? `${snapshot.packageName}:${snapshot.capturedAt}` : "empty";

  function resolvePoint(clientX: number, clientY: number, clampToBounds = false): SurfacePoint | null {
    const surface = surfaceRef.current;
    if (!surface || screenWidth <= 0 || screenHeight <= 0) {
      return null;
    }
    const rect = surface.getBoundingClientRect();
    if (rect.width <= 0 || rect.height <= 0) {
      return null;
    }
    let relX = (clientX - rect.left) / rect.width;
    let relY = (clientY - rect.top) / rect.height;
    if (clampToBounds) {
      relX = Math.min(1, Math.max(0, relX));
      relY = Math.min(1, Math.max(0, relY));
    } else if (relX < 0 || relX > 1 || relY < 0 || relY > 1) {
      return null;
    }
    return {
      left: relX * rect.width,
      top: relY * rect.height,
      deviceX: Math.round(relX * screenWidth),
      deviceY: Math.round(relY * screenHeight),
    };
  }

  function syncPatternVisual(cells: number[]) {
    setPatternCellsHit(cells);
    if (!patternBounds || patternDots.length === 0) {
      return;
    }
    const surface = surfaceRef.current;
    if (!surface) {
      return;
    }
    const rect = surface.getBoundingClientRect();
    const snapped = cells
      .map((hitCell) => patternDots.find((dot) => dot.cell === hitCell))
      .filter((dot): dot is { cell: number; x: number; y: number } => Boolean(dot))
      .map((dot) =>
        deviceToSurfacePoint(dot.x, dot.y, screenWidth, screenHeight, rect.width, rect.height),
      );
    setPatternPolyline(snapped);
  }

  function appendPathPoint(point: SurfacePoint) {
    const list = pathPointsRef.current;
    const last = list[list.length - 1];
    if (last) {
      const dist = Math.hypot(point.deviceX - last.deviceX, point.deviceY - last.deviceY);
      if (dist < PATTERN_SAMPLE_MIN_DEVICE_PX) {
        return;
      }
      if (patternBounds && patternDots.length > 0) {
        const cells = [...patternCellsRef.current];
        extendCellsFromDrag(
          patternDots,
          cells,
          { x: point.deviceX, y: point.deviceY },
          patternHitRadius(patternBounds),
        );
        patternCellsRef.current = cells;
        syncPatternVisual(cells);
      }
    }
    list.push(point);
    if (!patternBounds) {
      setPatternPolyline([...list]);
    }
  }

  async function sendClickAt(deviceX: number, deviceY: number, hit?: UiTreeNode | null) {
    if (!deviceId || !isOnline) {
      return;
    }
    const target = hit ?? findNodeAtPoint(nodes, deviceX, deviceY);
    if (target) {
      setActiveKey(nodeKey(target));
    }
    if (onNodeClick && target) {
      onNodeClick(target);
      return;
    }
    if (useAdbInput) {
      void sendAdbInput(deviceId, { action: "tap", x: deviceX, y: deviceY }).catch(() => {
        // 静默
      });
      return;
    }
    void sendCommand({
      deviceId,
      cmd: "click",
      params: { x: deviceX, y: deviceY },
      waitMs: 800,
    }).catch(() => {
      // 静默
    });
  }

  async function sendSwipe(start: SurfacePoint, end: SurfacePoint) {
    if (!deviceId || !isOnline) {
      return;
    }
    const dx = end.deviceX - start.deviceX;
    const dy = end.deviceY - start.deviceY;
    const distance = Math.hypot(dx, dy);
    const isHorizontal = Math.abs(dx) > Math.abs(dy);
    const durationMs = isHorizontal
      ? Math.min(1200, Math.max(450, Math.round(Math.abs(dx) * 0.9)))
      : Math.min(800, Math.max(200, Math.round(distance * 0.6)));

    if (useAdbInput) {
      void sendAdbInput(deviceId, {
        action: "swipe",
        x1: start.deviceX,
        y1: start.deviceY,
        x2: end.deviceX,
        y2: end.deviceY,
        durationMs,
      }).catch(() => {
        // 静默
      });
      return;
    }

    void sendCommand({
      deviceId,
      cmd: "swipe",
      params: {
        x1: start.deviceX,
        y1: start.deviceY,
        x2: end.deviceX,
        y2: end.deviceY,
        duration: durationMs,
      },
      waitMs: 2000,
    }).catch(() => {
      // 静默
    });
  }

  async function sendPatternUnlock(cells: string) {
    if (!allowPattern) {
      message.warning("当前无法下发图案（无障碍不可用）");
      return;
    }
    const normalized = cells.trim();
    const cellCount = normalized ? normalized.split("-").filter(Boolean).length : 0;
    if (!deviceId || !isOnline || cellCount < PATTERN_MIN_CELLS) {
      message.warning(`图案至少需要 ${PATTERN_MIN_CELLS} 个格点`);
      return;
    }
    setPatternBusy(true);
    try {
      const result = await sendCommand({
        deviceId,
        cmd: "unlock_pattern",
        params: {
          gesturePath: "",
          path: normalized,
          pin: normalized,
          cellsOnly: true,
          swipeUp: false,
        },
        waitMs: 12000,
      });
      if (result.status === "success") {
        message.success(`图案已下发（${normalized}）`);
      } else {
        message.error(result.errorMessage ?? result.status);
      }
    } catch (error) {
      message.error(error instanceof Error ? error.message : "图案解锁失败");
    } finally {
      setPatternBusy(false);
    }
  }

  function handlePointerDown(event: ReactPointerEvent<HTMLDivElement>) {
    if (!deviceId || !isOnline || !snapshot || patternBusy) {
      return;
    }
    const point = resolvePoint(event.clientX, event.clientY);
    if (!point) {
      return;
    }
    event.preventDefault();
    event.currentTarget.setPointerCapture(event.pointerId);
    dragStartRef.current = point;
    dragEndRef.current = point;
    const startInPattern =
      patternBounds != null && pointInBounds(patternBounds, point.deviceX, point.deviceY);
    patternDrawingRef.current = startInPattern;
    pathPointsRef.current = startInPattern ? [point] : [];
    patternCellsRef.current = [];
    if (startInPattern && patternBounds && patternDots.length > 0) {
      const cells: number[] = [];
      extendCellsFromDrag(
        patternDots,
        cells,
        { x: point.deviceX, y: point.deviceY },
        patternHitRadius(patternBounds),
      );
      patternCellsRef.current = cells;
      syncPatternVisual(cells);
    } else {
      setPatternPolyline([]);
      setPatternCellsHit([]);
    }
    setIsDragging(true);
    setSwipePreview(null);
  }

  function handlePointerMove(event: ReactPointerEvent<HTMLDivElement>) {
    if (!isDragging || !dragStartRef.current) {
      return;
    }
    const point = resolvePoint(event.clientX, event.clientY, true);
    if (!point) {
      return;
    }
    dragEndRef.current = point;
    if (patternDrawingRef.current) {
      appendPathPoint(point);
      return;
    }
    setSwipePreview(buildSwipePreview(dragStartRef.current, point));
  }

  function handlePointerUp(event: ReactPointerEvent<HTMLDivElement>) {
    if (!isDragging) {
      return;
    }
    const start = dragStartRef.current;
    const end =
      resolvePoint(event.clientX, event.clientY, true) ??
      dragEndRef.current ??
      start;
    const wasPattern = patternDrawingRef.current;
    const pathPoints = [...pathPointsRef.current];
    dragStartRef.current = null;
    dragEndRef.current = null;
    patternDrawingRef.current = false;
    pathPointsRef.current = [];
    patternCellsRef.current = [];
    setIsDragging(false);
    setSwipePreview(null);
    setPatternPolyline([]);
    setPatternCellsHit([]);
    if (event.currentTarget.hasPointerCapture(event.pointerId)) {
      event.currentTarget.releasePointerCapture(event.pointerId);
    }
    if (!start || !end) {
      return;
    }

    if (wasPattern) {
      if (end !== pathPoints[pathPoints.length - 1]) {
        pathPoints.push(end);
      }
      if (!patternBounds) {
        message.warning("未识别图案区域，请刷新 UI 树后再画");
        return;
      }
      const cells = cellsFromPathPoints(
        patternBounds,
        patternDots,
        pathPoints.map((point) => ({ x: point.deviceX, y: point.deviceY })),
      );
      if (cells.length >= PATTERN_MIN_CELLS) {
        void sendPatternUnlock(cells.join("-"));
      } else {
        message.warning(`图案格点不足（${cells.length}/${PATTERN_MIN_CELLS}），请经过至少 4 个圆点`);
      }
      return;
    }

    const dx = end.deviceX - start.deviceX;
    const dy = end.deviceY - start.deviceY;
    const distance = Math.hypot(dx, dy);
    const isHorizontal = Math.abs(dx) > Math.abs(dy);
    const shouldSwipe =
      distance >= SWIPE_MIN_DEVICE_PX ||
      (isHorizontal && Math.abs(dx) >= SWIPE_MIN_HORIZONTAL_DEVICE_PX);

    if (shouldSwipe) {
      void sendSwipe(start, end);
      return;
    }
    void sendClickAt(start.deviceX, start.deviceY);
  }

  function handleListClick(node: UiTreeNode) {
    const center = nodeCenter(node);
    void sendClickAt(center.x, center.y, node);
  }

  if (!deviceId) {
    return <div className="empty-state">请选择设备后查看屏幕助手。</div>;
  }

  if (!isOnline) {
    return <div className="empty-state">设备离线，无法读取 UI 树。</div>;
  }

  const polylinePointsAttr = patternPolyline
    .map((point) => `${point.left},${point.top}`)
    .join(" ");

  const wireframe = (
    <div
      className="screen-stage screen-stage--device screen-stage--assistant"
      style={{ aspectRatio: `${screenWidth} / ${screenHeight}` }}
    >
      {!snapshot ? (
        errorText ? (
          <div className="screen-stage-placeholder">{errorText}</div>
        ) : (
          <MirrorSkeleton caption="请稍候" hint="正在拉取 UI 树" />
        )
      ) : (
        <div
          key={surfaceKey}
          ref={surfaceRef}
          className={`screen-assistant-surface screen-assistant-surface--interactive${
            patternBounds ? " screen-assistant-surface--pattern" : ""
          }`}
          onPointerDown={handlePointerDown}
          onPointerMove={handlePointerMove}
          onPointerUp={handlePointerUp}
          onPointerCancel={handlePointerUp}
        >
          {nodes.map((node) => {
            const key = nodeKey(node);
            const active = activeKey === key;
            return (
              <AssistantNode
                key={`${surfaceKey}:${key}`}
                node={node}
                screenWidth={screenWidth}
                screenHeight={screenHeight}
                active={active}
                translationMap={translationMap}
              />
            );
          })}
          {patternBounds ? (
            <div
              className="screen-assistant-pattern-overlay"
              style={scaleBoundsStyle(patternBounds, screenWidth, screenHeight)}
            >
              {patternDots.map((dot) => {
                const leftPct =
                  ((dot.x - patternBounds.left) / Math.max(1, patternBounds.right - patternBounds.left)) *
                  100;
                const topPct =
                  ((dot.y - patternBounds.top) /
                    Math.max(1, patternBounds.bottom - patternBounds.top)) *
                  100;
                const hit = patternCellsHit.includes(dot.cell);
                return (
                  <span
                    key={dot.cell}
                    className={`screen-assistant-pattern-dot${hit ? " is-hit" : ""}`}
                    style={{ left: `${leftPct}%`, top: `${topPct}%` }}
                  />
                );
              })}
            </div>
          ) : null}
          {patternPolyline.length >= 2 ? (
            <svg className="screen-assistant-pattern-path" aria-hidden="true">
              <polyline
                points={polylinePointsAttr}
                fill="none"
                stroke="rgba(79, 209, 255, 0.95)"
                strokeWidth="3"
                strokeLinecap="round"
                strokeLinejoin="round"
              />
            </svg>
          ) : null}
          {swipePreview && swipePreview.length >= 4 ? (
            <div
              className="screen-swipe-trail"
              style={{
                left: swipePreview.x1,
                top: swipePreview.y1,
                width: swipePreview.length,
                transform: `rotate(${swipePreview.angle}deg)`,
              }}
            />
          ) : null}
        </div>
      )}
    </div>
  );

  return (
    <div className="screen-assistant-wrap">
      {patternCellsHit.length > 0 ? (
        <div className="screen-assistant-toolbar">
          <span className="screen-assistant-pattern-cells">{patternCellsHit.join("-")}</span>
        </div>
      ) : null}
      <div className="screen-assistant-body">
        <div className="screen-pane-stage screen-assistant-wireframe">{wireframe}</div>
        <div className="screen-assistant-tile">
          <div className="screen-assistant-tile-head">
            节点平铺（{tiledNodes.length}/{snapshot?.nodeCount ?? 0}）
          </div>
          {!snapshot ? (
            errorText ? (
              <div className="screen-assistant-tile-empty">{errorText}</div>
            ) : (
              <div className="screen-skeleton-tile-list" aria-busy="true">
                {Array.from({ length: 8 }).map((_, index) => (
                  <span key={index} className="screen-skeleton-bone screen-skeleton-bone--tile" />
                ))}
              </div>
            )
          ) : tiledNodes.length === 0 ? (
            <div className="screen-assistant-tile-empty">无节点</div>
          ) : (
            <div className="screen-assistant-tile-list">
              {tiledNodes.map((node) => {
                const key = nodeKey(node);
                const label = resolveNodeLabel(node, translationMap);
                const active = activeKey === key;
                return (
                  <button
                    key={`${surfaceKey}:tile:${key}`}
                    type="button"
                    className={`screen-assistant-tile-item ${active ? "active" : ""} ${node.clickable ? "clickable" : ""}`}
                    title={`${label}\n${node.viewId || node.className}\n[${node.bounds.left},${node.bounds.top}]-[${node.bounds.right},${node.bounds.bottom}]`}
                    onClick={() => handleListClick(node)}
                    onMouseEnter={() => setActiveKey(key)}
                  >
                    <span className="screen-assistant-tile-label">{label}</span>
                    <span className="screen-assistant-tile-meta">
                      {node.clickable ? "可点 · " : ""}
                      {node.bounds.right - node.bounds.left}×
                      {node.bounds.bottom - node.bounds.top}
                    </span>
                  </button>
                );
              })}
            </div>
          )}
        </div>
      </div>
    </div>
  );
}
