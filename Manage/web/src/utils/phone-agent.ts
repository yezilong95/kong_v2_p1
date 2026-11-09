import type { UiTreeNode } from "../types";
import { filterNodesByPrimaryPackage, selectDisplayNodes } from "./ui-tree";

export interface CompactUiNode {
  i: number;
  text?: string;
  desc?: string;
  clickable: boolean;
  focusable: boolean;
  bounds: { left: number; top: number; right: number; bottom: number };
  packageName?: string;
}

export function buildCompactUiNodes(
  nodes: UiTreeNode[],
  options: {
    primaryPackage?: string;
    screenWidth: number;
    screenHeight: number;
    max?: number;
  },
): CompactUiNode[] {
  const filtered = filterNodesByPrimaryPackage(nodes, options.primaryPackage ?? "");
  const picked = selectDisplayNodes(
    filtered,
    options.max ?? 100,
    options.screenWidth,
    options.screenHeight,
  );
  return picked.map((node, index) => ({
    i: index + 1,
    text: node.text || undefined,
    desc: node.desc || undefined,
    clickable: node.clickable,
    focusable: node.focusable,
    bounds: node.bounds,
    packageName: node.packageName || undefined,
  }));
}

export function resolveNodeCenter(node: CompactUiNode): { x: number; y: number } {
  const { left, top, right, bottom } = node.bounds;
  return {
    x: Math.round((left + right) / 2),
    y: Math.round((top + bottom) / 2),
  };
}

export function formatPhoneAgentAction(plan: {
  action: string;
  params: Record<string, unknown>;
}): string {
  const { action, params } = plan;
  if (action === "click") {
    if (params.nodeIndex != null) {
      return `点击节点 #${params.nodeIndex}`;
    }
    return `点击 (${params.x}, ${params.y})`;
  }
  if (action === "swipe") {
    return `滑动 (${params.x1},${params.y1}) → (${params.x2},${params.y2})`;
  }
  if (action === "input_text") {
    const text = String(params.text ?? "");
    const masked = text.length > 8 ? `${text.slice(0, 4)}…${text.slice(-2)}` : text;
    return `输入「${masked}」`;
  }
  if (action === "open_app") {
    return `打开应用 ${params.packageName}`;
  }
  if (action === "global_action") {
    const nav = String(params.action ?? "");
    if (nav === "back") return "返回";
    if (nav === "home") return "主页";
    if (nav === "recents") return "多任务";
    return `系统键 ${nav}`;
  }
  if (action === "wake_screen") {
    return "唤醒屏幕";
  }
  if (action === "wait") {
    return `等待 ${params.ms}ms`;
  }
  if (action === "done") {
    return `完成：${params.summary ?? ""}`;
  }
  if (action === "failed") {
    return `失败：${params.reason ?? ""}`;
  }
  return action;
}
