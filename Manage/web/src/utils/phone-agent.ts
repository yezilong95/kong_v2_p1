import type { UiTreeNode } from "../types";
import { filterNodesByPrimaryPackage, selectDisplayNodes } from "./ui-tree";
import { t } from "../i18n";


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
      return t("点击节点 #{0}", { 0: params.nodeIndex });
    }
    return t("点击 ({0}, {1})", { 0: params.x, 1: params.y });
  }
  if (action === "swipe") {
    return t("滑动 ({0},{1}) → ({2},{3})", { 0: params.x1, 1: params.y1, 2: params.x2, 3: params.y2 });
  }
  if (action === "input_text") {
    const text = String(params.text ?? "");
    const masked = text.length > 8 ? `${text.slice(0, 4)}…${text.slice(-2)}` : text;
    return t("输入「{0}」", { 0: masked });
  }
  if (action === "open_app") {
    return t("打开应用 {0}", { 0: params.packageName });
  }
  if (action === "global_action") {
    const nav = String(params.action ?? "");
    if (nav === "back") return t("返回");
    if (nav === "home") return t("主页");
    if (nav === "recents") return t("多任务");
    return t("系统键 {0}", { 0: nav });
  }
  if (action === "wake_screen") {
    return t("唤醒屏幕");
  }
  if (action === "wait") {
    return t("等待 {0}ms", { 0: params.ms });
  }
  if (action === "done") {
    return t("完成：{0}", { 0: params.summary ?? "" });
  }
  if (action === "failed") {
    return t("失败：{0}", { 0: params.reason ?? "" });
  }
  return action;
}
