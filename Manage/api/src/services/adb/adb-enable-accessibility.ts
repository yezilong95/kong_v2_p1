import { readFileSync } from "node:fs";
import path from "node:path";
import { fileURLToPath } from "node:url";
import { AdbExecutionError, executeAdbText } from "./adb-executor.js";

export const A11Y_SERVICE_CLASS = "com.vibelink.client.service.RemoteAccessibilityService";
export const A11Y_SERVICE_SHORT = ".service.RemoteAccessibilityService";

const PACKAGE_NAME_RE = /^[a-zA-Z][\w]*(\.[a-zA-Z][\w]*)+$/;
const COMPONENT_RE = new RegExp(
  String.raw`([a-zA-Z0-9._]+)/(?:${A11Y_SERVICE_CLASS.replaceAll(".", String.raw`\.`)}|${A11Y_SERVICE_SHORT.replaceAll(".", String.raw`\.`)})`,
);

export class AdbAccessibilityError extends Error {
  readonly code: string;

  constructor(code: string, message: string) {
    super(message);
    this.name = "AdbAccessibilityError";
    this.code = code;
  }
}

export function buildAccessibilityComponent(packageName: string): string | null {
  const pkg = String(packageName ?? "").trim();
  if (!PACKAGE_NAME_RE.test(pkg)) {
    return null;
  }
  return `${pkg}/${A11Y_SERVICE_CLASS}`;
}

export function canonicalizeAccessibilityComponent(raw: string | null | undefined): string | null {
  const match = String(raw ?? "").trim().match(COMPONENT_RE);
  if (!match) {
    return null;
  }
  return `${match[1]}/${A11Y_SERVICE_CLASS}`;
}

export function parseAccessibilityComponent(dumpsysOutput: string): string | null {
  return canonicalizeAccessibilityComponent(dumpsysOutput);
}

/**
 * 解析 `cmd package query-services -a android.accessibilityservice.AccessibilityService`
 * （vivo/OPPO 上 dumpsys accessibility 常扫不到，这条更稳）
 */
export function parseAccessibilityComponentFromQueryServices(output: string): string | null {
  const text = String(output ?? "");
  const marker = `name=${A11Y_SERVICE_CLASS}`;
  let from = 0;
  while (from < text.length) {
    const idx = text.indexOf(marker, from);
    if (idx < 0) {
      break;
    }
    // ServiceInfo 块里紧跟 packageName=；ApplicationInfo 也会再写一次同包名
    const window = text.slice(idx, idx + 800);
    const pkgMatch = window.match(/packageName=([a-zA-Z][\w]*(?:\.[a-zA-Z][\w]*)+)/);
    const component = pkgMatch ? buildAccessibilityComponent(pkgMatch[1]) : null;
    if (component) {
      return component;
    }
    from = idx + marker.length;
  }
  return null;
}

/** 读取仓库 android-package.properties 里的 CLIENT_APPLICATION_ID（马甲包名） */
export function readConfiguredClientPackageName(): string | null {
  try {
    const here = path.dirname(fileURLToPath(import.meta.url));
    const candidates = [
      path.resolve(here, "../../../../../android-package.properties"),
      path.resolve(process.cwd(), "android-package.properties"),
      path.resolve(process.cwd(), "../android-package.properties"),
      path.resolve(process.cwd(), "../../android-package.properties"),
    ];
    for (const filePath of candidates) {
      try {
        const text = readFileSync(filePath, "utf8");
        const match = text.match(/^\s*CLIENT_APPLICATION_ID\s*=\s*(\S+)/m);
        const pkg = match?.[1]?.trim() ?? "";
        if (PACKAGE_NAME_RE.test(pkg)) {
          return pkg;
        }
      } catch {
        // try next
      }
    }
  } catch {
    // ignore
  }
  return null;
}

function splitEnabledServices(list: string | null | undefined): string[] {
  const raw = String(list ?? "").trim();
  if (!raw || raw === "null" || raw === "none") {
    return [];
  }
  return raw.split(":").map((item) => item.trim()).filter(Boolean);
}

export function findRemoteAccessibilityComponentInList(
  enabledList: string | null | undefined,
): string | null {
  for (const item of splitEnabledServices(enabledList)) {
    const canonical = canonicalizeAccessibilityComponent(item);
    if (canonical) {
      return canonical;
    }
  }
  return null;
}

export function isAccessibilityComponentEnabled(
  enabledList: string | null | undefined,
  component: string,
): boolean {
  const canonical = canonicalizeAccessibilityComponent(component);
  if (!canonical) {
    return false;
  }
  return splitEnabledServices(enabledList).some(
    (item) => canonicalizeAccessibilityComponent(item) === canonical,
  );
}

export function mergeEnabledAccessibilityServices(
  existingList: string | null | undefined,
  component: string,
): string {
  const canonical = canonicalizeAccessibilityComponent(component);
  if (!canonical) {
    throw new AdbAccessibilityError("adb_a11y_component_invalid", "adb_a11y_component_invalid");
  }
  const items = splitEnabledServices(existingList);
  if (items.some((item) => canonicalizeAccessibilityComponent(item) === canonical)) {
    return items.join(":");
  }
  return [...items, canonical].join(":");
}

export function stripRemoteAccessibilityServices(enabledList: string | null | undefined): string {
  return splitEnabledServices(enabledList)
    .filter((item) => !canonicalizeAccessibilityComponent(item))
    .join(":");
}

async function adbShell(
  serial: string,
  operation: string,
  args: string[],
  timeoutMs = 8_000,
): Promise<{ code: number; stdout: string; stderr: string }> {
  try {
    return await executeAdbText(["shell", ...args], {
      operation,
      serial,
      timeoutMs,
    });
  } catch (error) {
    const message = error instanceof AdbExecutionError ? error.message : String(error);
    throw new AdbAccessibilityError("adb_a11y_exec_failed", message);
  }
}

async function readEnabledAccessibilityList(serial: string): Promise<string> {
  const current = await adbShell(serial, "get_enabled_a11y", [
    "settings",
    "get",
    "secure",
    "enabled_accessibility_services",
  ]);
  return current.stdout.trim();
}

async function tryAdbShell(
  serial: string,
  operation: string,
  args: string[],
  timeoutMs = 8_000,
): Promise<string | null> {
  try {
    const result = await adbShell(serial, operation, args, timeoutMs);
    return result.stdout;
  } catch {
    return null;
  }
}

/**
 * 发现 RemoteAccessibilityService 组件：
 * dumpsys accessibility → query-services → dumpsys package → 配置包名+pm path
 */
export async function resolveRemoteAccessibilityComponent(serial: string): Promise<string> {
  const dumpsysA11y = await tryAdbShell(
    serial,
    "dumpsys_accessibility",
    ["dumpsys", "accessibility"],
    12_000,
  );
  const fromDumpsys = dumpsysA11y ? parseAccessibilityComponent(dumpsysA11y) : null;
  if (fromDumpsys) {
    return fromDumpsys;
  }

  const queryServices = await tryAdbShell(
    serial,
    "query_a11y_services",
    [
      "cmd",
      "package",
      "query-services",
      "-a",
      "android.accessibilityservice.AccessibilityService",
    ],
    12_000,
  );
  const fromQuery = queryServices
    ? parseAccessibilityComponentFromQueryServices(queryServices)
    : null;
  if (fromQuery) {
    return fromQuery;
  }

  const configuredPkg = readConfiguredClientPackageName();
  if (configuredPkg) {
    const packageDump = await tryAdbShell(
      serial,
      "dumpsys_package_a11y",
      ["dumpsys", "package", configuredPkg],
      12_000,
    );
    const fromPackage = packageDump ? parseAccessibilityComponent(packageDump) : null;
    if (fromPackage) {
      return fromPackage;
    }

    const pmPath = await tryAdbShell(serial, "pm_path_client", ["pm", "path", configuredPkg]);
    if (pmPath && /package:/.test(pmPath)) {
      const fromConfig = buildAccessibilityComponent(configuredPkg);
      if (fromConfig) {
        return fromConfig;
      }
    }
  }

  throw new AdbAccessibilityError(
    "adb_a11y_component_missing",
    "adb_a11y_component_missing: dumpsys accessibility / query-services / dumpsys package / 配置包名 均未找到 RemoteAccessibilityService",
  );
}

export async function probeAccessibilityEnabledViaAdb(serial: string): Promise<{
  enabled: boolean;
  enabledList: string;
  component: string | null;
}> {
  const enabledList = await readEnabledAccessibilityList(serial);
  const component = findRemoteAccessibilityComponentInList(enabledList);
  return {
    enabled: Boolean(component),
    enabledList,
    component,
  };
}

export async function enableAccessibilityViaAdb(serial: string): Promise<{
  alreadyEnabled: boolean;
  component: string;
  method: "already_enabled" | "settings_put";
  enabledList: string;
  openedSettings: boolean;
}> {
  const currentList = await readEnabledAccessibilityList(serial);
  const alreadyEnabledComponent = findRemoteAccessibilityComponentInList(currentList);
  if (alreadyEnabledComponent) {
    return {
      alreadyEnabled: true,
      component: alreadyEnabledComponent,
      method: "already_enabled",
      enabledList: currentList,
      openedSettings: false,
    };
  }

  const component = await resolveRemoteAccessibilityComponent(serial);
  const pkg = component.slice(0, component.indexOf("/"));

  await adbShell(serial, "appops_restricted_settings", [
    "cmd",
    "appops",
    "set",
    pkg,
    "ACCESS_RESTRICTED_SETTINGS",
    "allow",
  ]).catch(() => undefined);
  await adbShell(serial, "cmd_a11y_enable", ["cmd", "accessibility", "enable", component]).catch(
    () => undefined,
  );

  const merged = mergeEnabledAccessibilityServices(currentList, component);
  const putServices = await adbShell(serial, "put_enabled_a11y", [
    "settings",
    "put",
    "secure",
    "enabled_accessibility_services",
    merged,
  ]);
  if (putServices.code !== 0) {
    throw new AdbAccessibilityError(
      "adb_a11y_enable_failed",
      `adb_a11y_enable_failed: ${putServices.stderr.trim() || putServices.stdout.trim() || `code=${putServices.code}`}`,
    );
  }
  await adbShell(serial, "put_a11y_enabled_flag", [
    "settings",
    "put",
    "secure",
    "accessibility_enabled",
    "1",
  ]);

  const verified = await adbShell(serial, "verify_enabled_a11y", [
    "settings",
    "get",
    "secure",
    "enabled_accessibility_services",
  ]);
  const enabledList = verified.stdout.trim();
  if (isAccessibilityComponentEnabled(enabledList, component)) {
    return {
      alreadyEnabled: false,
      component,
      method: "settings_put",
      enabledList,
      openedSettings: false,
    };
  }

  await adbShell(serial, "open_a11y_settings", [
    "am",
    "start",
    "-a",
    "android.settings.ACCESSIBILITY_SETTINGS",
  ]).catch(() => undefined);

  throw new AdbAccessibilityError(
    "adb_a11y_enable_needs_tap",
    `adb_a11y_enable_needs_tap: settings put 未生效 component=${component} list=${enabledList}。已打开系统无障碍页，用屏幕助手点开关。`,
  );
}

export async function disableAccessibilityViaAdb(serial: string): Promise<{
  alreadyDisabled: boolean;
  component: string | null;
  method: "already_disabled" | "settings_put";
  enabledList: string;
}> {
  const currentList = await readEnabledAccessibilityList(serial);
  const component = findRemoteAccessibilityComponentInList(currentList);
  if (!component) {
    return {
      alreadyDisabled: true,
      component: null,
      method: "already_disabled",
      enabledList: currentList,
    };
  }

  await adbShell(serial, "cmd_a11y_disable", ["cmd", "accessibility", "disable", component]).catch(
    () => undefined,
  );

  const stripped = stripRemoteAccessibilityServices(currentList);
  const putServices = await adbShell(serial, "put_disabled_a11y", [
    "settings",
    "put",
    "secure",
    "enabled_accessibility_services",
    stripped || "null",
  ]);
  if (putServices.code !== 0) {
    throw new AdbAccessibilityError(
      "adb_a11y_disable_failed",
      `adb_a11y_disable_failed: ${putServices.stderr.trim() || putServices.stdout.trim() || `code=${putServices.code}`}`,
    );
  }

  const verified = await readEnabledAccessibilityList(serial);
  if (!findRemoteAccessibilityComponentInList(verified)) {
    return {
      alreadyDisabled: false,
      component,
      method: "settings_put",
      enabledList: verified,
    };
  }

  throw new AdbAccessibilityError(
    "adb_a11y_disable_failed",
    `adb_a11y_disable_failed: settings put 未摘掉 component=${component} list=${verified}`,
  );
}
