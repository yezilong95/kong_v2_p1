import type { UiTreeNode, UiTreeSnapshot } from "../types";

export type UiTreeDisplayMode = "compact" | "full";

const GENERIC_DESC_PATTERN = /^(icon|image|button|divider|separator)$/i;
const JUNK_CLASS_PATTERN =
  /^(View|FrameLayout|LinearLayout|RelativeLayout|ImageView|TextView|Space|ViewGroup)$/i;

const PATTERN_NODE_HINT =
  /lockpattern|lock_pattern|pattern_view|patternview|lockPatternView/i;

export function isPatternUiNode(node: UiTreeNode): boolean {
  const className = node.className || "";
  const viewId = node.viewId || "";
  return (
    PATTERN_NODE_HINT.test(className) ||
    PATTERN_NODE_HINT.test(viewId) ||
    className.toLowerCase().includes("patternview")
  );
}

export function isHumanUsefulNode(node: UiTreeNode): boolean {
  const text = node.text.trim();
  const desc = node.desc.trim();
  const simpleClass = node.className.split(".").pop() ?? node.className;
  const hasRealText = text.length > 0;
  const hasRealDesc = desc.length > 0 && !GENERIC_DESC_PATTERN.test(desc);

  // LockPatternView 常无 text/desc，且 class 可能被收成 View；必须保留供右侧画图案
  if (isPatternUiNode(node)) {
    return true;
  }
  if (hasRealText || hasRealDesc) {
    return true;
  }
  if (node.clickable || node.focusable || node.checkable) {
    if (JUNK_CLASS_PATTERN.test(simpleClass) && !node.viewId) {
      return false;
    }
    return true;
  }
  if (node.viewId && !JUNK_CLASS_PATTERN.test(simpleClass)) {
    return true;
  }
  return false;
}

export function filterHumanUsefulNodes(nodes: UiTreeNode[]): UiTreeNode[] {
  return nodes.filter(isHumanUsefulNode);
}

export function getUiTreeFetchMode(displayMode: UiTreeDisplayMode): "normal" | "full" {
  return displayMode === "compact" ? "normal" : "full";
}

export function getUiTreeDisplayMax(displayMode: UiTreeDisplayMode): number {
  return displayMode === "compact" ? 80 : 200;
}

function readBounds(raw: unknown): UiTreeNode["bounds"] | null {
  if (!raw || typeof raw !== "object") {
    return null;
  }
  const bounds = raw as Record<string, unknown>;
  const left = Number(bounds.left);
  const top = Number(bounds.top);
  const right = Number(bounds.right);
  const bottom = Number(bounds.bottom);
  if ([left, top, right, bottom].some((value) => Number.isNaN(value))) {
    return null;
  }
  if (right <= left || bottom <= top) {
    return null;
  }
  return { left, top, right, bottom };
}

function readUiTreeNode(raw: unknown): UiTreeNode | null {
  if (!raw || typeof raw !== "object") {
    return null;
  }
  const node = raw as Record<string, unknown>;
  const bounds = readBounds(node.bounds);
  if (!bounds) {
    return null;
  }
  return {
    className: String(node.className ?? ""),
    viewId: String(node.viewId ?? ""),
    text: String(node.text ?? ""),
    desc: String(node.desc ?? ""),
    clickable: Boolean(node.clickable),
    focusable: Boolean(node.focusable),
    checkable: Boolean(node.checkable),
    depth: Number(node.depth ?? 0),
    packageName: String(node.packageName ?? ""),
    bounds,
  };
}

export function parseUiTreeSnapshot(
  deviceId: string,
  payload: Record<string, unknown>,
): UiTreeSnapshot | null {
  const nodesRaw = payload.nodes;
  if (!Array.isArray(nodesRaw)) {
    return null;
  }

  const nodes = nodesRaw
    .map((item) => readUiTreeNode(item))
    .filter((item): item is UiTreeNode => item != null);

  const screenWidth = Number(payload.screenWidth ?? 0);
  const screenHeight = Number(payload.screenHeight ?? 0);
  if (screenWidth <= 0 || screenHeight <= 0) {
    return null;
  }

  return {
    deviceId,
    screenWidth,
    screenHeight,
    packageName: String(payload.packageName ?? ""),
    packageNames: Array.isArray(payload.packageNames)
      ? payload.packageNames.map((item) => String(item))
      : undefined,
    nodeCount: Number(payload.nodeCount ?? nodes.length),
    truncated: Boolean(payload.truncated),
    capturedAt: Date.now(),
    nodes,
    keyguardLocked: payload.keyguardLocked == null ? undefined : Boolean(payload.keyguardLocked),
    screenInteractive:
      payload.screenInteractive == null ? undefined : Boolean(payload.screenInteractive),
    scannedNodeCount:
      payload.scannedNodeCount == null ? undefined : Number(payload.scannedNodeCount),
    centerNodeCount:
      payload.centerNodeCount == null ? undefined : Number(payload.centerNodeCount),
    mode: payload.mode == null ? undefined : String(payload.mode),
    windowCount: payload.windowCount == null ? undefined : Number(payload.windowCount),
  };
}

export function filterNodesByPrimaryPackage(
  nodes: UiTreeNode[],
  primaryPackage: string,
): UiTreeNode[] {
  const normalized = primaryPackage.trim();
  if (!normalized) {
    return nodes;
  }
  return nodes.filter((node) => !node.packageName || node.packageName === normalized);
}

export function formatNodeLabel(node: UiTreeNode): string {
  if (node.text) {
    return node.text;
  }
  if (node.desc) {
    return node.desc;
  }
  if (node.viewId) {
    const parts = node.viewId.split("/");
    return parts[parts.length - 1] || node.viewId;
  }
  return node.className || "node";
}

export function isLikelyChinese(text: string): boolean {
  const trimmed = text.trim();
  if (!trimmed) {
    return true;
  }
  const cjk = trimmed.match(/[\u4e00-\u9fff]/g);
  if (!cjk) {
    return false;
  }
  return cjk.length / trimmed.length >= 0.3;
}

export function collectNodeLabelTexts(nodes: UiTreeNode[]): string[] {
  const labels = new Set<string>();
  for (const node of nodes) {
    const label = formatNodeLabel(node);
    if (label && !isLikelyChinese(label)) {
      labels.add(label);
    }
  }
  return [...labels];
}

export function resolveNodeLabel(
  node: UiTreeNode,
  translationMap?: Record<string, string> | null,
): string {
  const raw = formatNodeLabel(node);
  if (!translationMap || !raw) {
    return raw;
  }
  const translated = translationMap[raw];
  return translated && translated !== raw ? translated : raw;
}

export function sortNodesForRender(nodes: UiTreeNode[]): UiTreeNode[] {
  return [...nodes].sort((a, b) => {
    const areaA = (a.bounds.right - a.bounds.left) * (a.bounds.bottom - a.bounds.top);
    const areaB = (b.bounds.right - b.bounds.left) * (b.bounds.bottom - b.bounds.top);
    return areaB - areaA;
  });
}

/** 限制 DOM 数量；按屏幕九宫格分区采样，避免中间区域节点被顶部/底部挤掉 */
export function selectDisplayNodes(
  nodes: UiTreeNode[],
  max = 80,
  screenWidth?: number,
  screenHeight?: number,
): UiTreeNode[] {
  if (nodes.length <= max) {
    return sortNodesForRender(nodes);
  }

  function scoreNode(node: UiTreeNode): { node: UiTreeNode; score: number; area: number } {
    let score = 0;
    if (node.text) {
      score += 4;
    }
    if (node.desc) {
      score += 3;
    }
    if (node.clickable) {
      score += 3;
    }
    if (node.focusable) {
      score += 1;
    }
    if (node.viewId) {
      score += 1;
    }
    const area =
      (node.bounds.right - node.bounds.left) * (node.bounds.bottom - node.bounds.top);
    return { node, score, area };
  }

  if (screenWidth && screenHeight && screenWidth > 0 && screenHeight > 0) {
    const cols = 3;
    const rows = 3;
    const perCell = Math.max(6, Math.ceil(max / (cols * rows)));
    const picked: UiTreeNode[] = [];
    const seen = new Set<string>();

    function nodeKey(node: UiTreeNode): string {
      return `${node.viewId}:${node.bounds.left}:${node.bounds.top}:${node.bounds.right}:${node.bounds.bottom}`;
    }

    for (let row = 0; row < rows; row++) {
      for (let col = 0; col < cols; col++) {
        const cellLeft = (screenWidth * col) / cols;
        const cellRight = (screenWidth * (col + 1)) / cols;
        const cellTop = (screenHeight * row) / rows;
        const cellBottom = (screenHeight * (row + 1)) / rows;

        const inCell = nodes.filter((node) => {
          const cx = (node.bounds.left + node.bounds.right) / 2;
          const cy = (node.bounds.top + node.bounds.bottom) / 2;
          return cx >= cellLeft && cx < cellRight && cy >= cellTop && cy < cellBottom;
        });

        const cellPicks = inCell
          .map(scoreNode)
          .sort((a, b) => b.score - a.score || a.area - b.area)
          .slice(0, perCell);

        for (const item of cellPicks) {
          const key = nodeKey(item.node);
          if (seen.has(key)) {
            continue;
          }
          seen.add(key);
          picked.push(item.node);
        }
      }
    }

    if (picked.length < max) {
      const rest = nodes
        .map(scoreNode)
        .sort((a, b) => b.score - a.score || a.area - b.area)
        .map((item) => item.node)
        .filter((node) => !seen.has(nodeKey(node)))
        .slice(0, max - picked.length);
      picked.push(...rest);
    }

    return sortNodesForRender(picked.slice(0, max));
  }

  return nodes
    .map(scoreNode)
    .sort((a, b) => b.score - a.score || a.area - b.area)
    .slice(0, max)
    .map((item) => item.node);
}

/** 中间内容区（约 12%~88% x 18%~78%）节点数 */
export function countCenterRegionNodes(
  nodes: UiTreeNode[],
  screenWidth: number,
  screenHeight: number,
): number {
  const left = screenWidth * 0.12;
  const right = screenWidth * 0.88;
  const top = screenHeight * 0.18;
  const bottom = screenHeight * 0.78;
  let count = 0;
  for (const node of nodes) {
    const cx = (node.bounds.left + node.bounds.right) / 2;
    const cy = (node.bounds.top + node.bounds.bottom) / 2;
    if (cx >= left && cx <= right && cy >= top && cy <= bottom) {
      count++;
    }
  }
  return count;
}

export function nodeCenter(node: UiTreeNode): { x: number; y: number } {
  const { bounds } = node;
  return {
    x: Math.round((bounds.left + bounds.right) / 2),
    y: Math.round((bounds.top + bounds.bottom) / 2),
  };
}
