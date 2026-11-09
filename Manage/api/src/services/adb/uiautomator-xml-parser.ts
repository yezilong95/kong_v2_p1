export interface ParsedUiTreeBounds {
  left: number;
  top: number;
  right: number;
  bottom: number;
}

export interface ParsedUiTreeNode {
  className: string;
  viewId: string;
  text: string;
  desc: string;
  clickable: boolean;
  focusable: boolean;
  checkable: boolean;
  depth: number;
  packageName: string;
  bounds: ParsedUiTreeBounds;
}

export interface ParsedUiTreeSnapshot {
  screenWidth: number;
  screenHeight: number;
  packageName: string;
  packageNames: string[];
  nodeCount: number;
  truncated: boolean;
  nodes: ParsedUiTreeNode[];
  scannedNodeCount: number;
}

const TAG = /<\/?node\b([^>]*?)(\/>|>)/gi;
const ATTR = /([A-Za-z0-9_:.-]+)="([^"]*)"/g;
const BOUNDS = /\[(-?\d+),(-?\d+)\]\[(-?\d+),(-?\d+)\]/;
const MAX_NODES = 2500;

function decodeXmlEntities(value: string): string {
  return value
    .replace(/&quot;/g, '"')
    .replace(/&apos;/g, "'")
    .replace(/&lt;/g, "<")
    .replace(/&gt;/g, ">")
    .replace(/&amp;/g, "&");
}

function parseAttrs(raw: string): Record<string, string> {
  const attrs: Record<string, string> = {};
  ATTR.lastIndex = 0;
  let match: RegExpExecArray | null;
  while ((match = ATTR.exec(raw))) {
    attrs[match[1]] = decodeXmlEntities(match[2]);
  }
  return attrs;
}

function parseBounds(raw: string | undefined): ParsedUiTreeBounds | null {
  if (!raw) return null;
  const match = BOUNDS.exec(raw);
  if (!match) return null;
  const left = Number(match[1]);
  const top = Number(match[2]);
  const right = Number(match[3]);
  const bottom = Number(match[4]);
  if ([left, top, right, bottom].some((value) => Number.isNaN(value))) {
    return null;
  }
  if (right <= left || bottom <= top) {
    return null;
  }
  return { left, top, right, bottom };
}

export function parseUiAutomatorXml(xml: string): ParsedUiTreeSnapshot {
  const nodes: ParsedUiTreeNode[] = [];
  const packageNames = new Set<string>();
  let depth = 0;
  let scannedNodeCount = 0;
  let truncated = false;

  TAG.lastIndex = 0;
  let match: RegExpExecArray | null;
  while ((match = TAG.exec(xml))) {
    const token = match[0];
    if (token.startsWith("</node")) {
      depth = Math.max(0, depth - 1);
      continue;
    }

    scannedNodeCount += 1;
    const attrs = parseAttrs(match[1]);
    const bounds = parseBounds(attrs.bounds);
    const selfClosing = token.endsWith("/>");

    if (bounds) {
      if (nodes.length < MAX_NODES) {
        const packageName = attrs.package ?? "";
        if (packageName) packageNames.add(packageName);
        nodes.push({
          className: attrs.class ?? "",
          viewId: attrs["resource-id"] ?? "",
          text: attrs.text ?? "",
          desc: attrs["content-desc"] ?? "",
          clickable: parseBool(attrs.clickable),
          focusable: parseBool(attrs.focusable),
          checkable: parseBool(attrs.checkable),
          depth,
          packageName,
          bounds,
        });
      } else {
        truncated = true;
      }
    }

    if (!selfClosing) {
      depth += 1;
    }
  }

  let screenWidth = 0;
  let screenHeight = 0;
  for (const node of nodes) {
    screenWidth = Math.max(screenWidth, node.bounds.right);
    screenHeight = Math.max(screenHeight, node.bounds.bottom);
  }

  const packageName =
    nodes.find((node) => node.packageName && !node.packageName.includes("systemui"))
      ?.packageName ??
    nodes[0]?.packageName ??
    "";

  return {
    screenWidth,
    screenHeight,
    packageName,
    packageNames: [...packageNames],
    nodeCount: nodes.length,
    truncated,
    nodes,
    scannedNodeCount,
  };
}

function parseBool(value: string | undefined): boolean {
  return value === "true";
}
