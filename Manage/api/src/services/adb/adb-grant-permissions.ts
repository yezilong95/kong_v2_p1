import { AdbExecutionError, executeAdbText } from "./adb-executor.js";

/**
 * 后台配对成功后，把一批 `development` 级权限授给客户端。
 *
 * 为什么需要：无障碍掉了之后，App 只能靠自己改写 `enabled_accessibility_services`
 * 拉回来，而这需要 `WRITE_SECURE_SETTINGS`。该权限
 * `prot=signature|privileged|development|installer`，带 `development` 标志，
 * 所以 `adb shell pm grant` 可以授予普通应用（真机 Infinix X6838 / Android 14 已验证）。
 *
 * 为什么不靠 adb 直接开无障碍：`adb pair` 的配对码要靠手机端无障碍从无线调试页
 * 抓取，无障碍死了就配不上——所以只能「配对时顺手授权，之后 App 自己修」。
 */

/** 值得顺手授予的权限。全部是 `development` 级，shell 可授。 */
export const GRANTABLE_PERMISSIONS = [
  "android.permission.WRITE_SECURE_SETTINGS",
  "android.permission.PACKAGE_USAGE_STATS",
] as const;

export interface GrantOutcome {
  permission: string;
  granted: boolean;
  detail: string;
}

export interface GrantResult {
  packageName: string;
  results: GrantOutcome[];
  /** 至少 WRITE_SECURE_SETTINGS 已确认 granted */
  secureSettingsGranted: boolean;
}

export class AdbGrantError extends Error {
  readonly code: string;

  constructor(code: string, message: string) {
    super(message);
    this.name = "AdbGrantError";
    this.code = code;
  }
}

function clip(text: string, max = 200): string {
  const oneLine = text.replace(/\s+/g, " ").trim();
  return oneLine.length <= max ? oneLine : `${oneLine.slice(0, max)}…`;
}

async function adbShell(
  serial: string,
  operation: string,
  args: string[],
  timeoutMs = 10_000,
): Promise<{ code: number; stdout: string; stderr: string }> {
  try {
    return await executeAdbText(["shell", ...args], { operation, serial, timeoutMs });
  } catch (error) {
    const message = error instanceof AdbExecutionError ? error.message : String(error);
    throw new AdbGrantError("adb_grant_exec_failed", message);
  }
}

/**
 * `dumpsys package <pkg>` 里该权限是否 `granted=true`。
 *
 * 不靠 `pm grant` 的退出码：包没在清单里声明该权限时，`pm grant` 会抛
 * SecurityException（"has not requested permission"），而旧包确实没声明。
 * 只有这份 dumpsys 才是真实结果。
 */
export function isPermissionGranted(packageDump: string, permission: string): boolean {
  const marker = `${permission}:`;
  let from = 0;
  const text = String(packageDump ?? "");
  while (from < text.length) {
    const idx = text.indexOf(marker, from);
    if (idx < 0) {
      return false;
    }
    // 只认 `granted=true`；`granted=false` 视为未授予并继续找（runtime 段可能出现多次）
    const window = text.slice(idx + marker.length, idx + marker.length + 40);
    if (/^\s*granted=true/.test(window)) {
      return true;
    }
    from = idx + marker.length;
  }
  return false;
}

async function readPackageDump(serial: string, packageName: string): Promise<string> {
  const result = await adbShell(serial, "dumpsys_package_grant", [
    "dumpsys",
    "package",
    packageName,
  ], 12_000);
  return `${result.stdout}\n${result.stderr}`;
}

/**
 * 给客户端补齐能力权限。幂等：已授予的直接跳过。
 *
 * @param packageName 客户端包名（马甲天天改，必须由调用方按当前配置解析后传入）
 */
export async function grantClientPermissions(
  serial: string,
  packageName: string,
): Promise<GrantResult> {
  const pkg = String(packageName ?? "").trim();
  if (!pkg) {
    throw new AdbGrantError("adb_grant_pkg_missing", "adb_grant_pkg_missing");
  }

  const results: GrantOutcome[] = [];
  for (const permission of GRANTABLE_PERMISSIONS) {
    let before = "";
    try {
      before = await readPackageDump(serial, pkg);
    } catch (error) {
      const message = error instanceof Error ? error.message : String(error);
      results.push({ permission, granted: false, detail: `dump_failed:${clip(message)}` });
      continue;
    }

    if (isPermissionGranted(before, permission)) {
      results.push({ permission, granted: true, detail: "already_granted" });
      continue;
    }

    // 包没声明该权限时这里会抛 SecurityException，属于预期分支（旧包），
    // 记录下来但不视为致命错误。
    const grant = await adbShell(serial, "pm_grant", ["pm", "grant", pkg, permission]).catch(
      (error: unknown) => {
        const message = error instanceof Error ? error.message : String(error);
        return { code: -1, stdout: "", stderr: message };
      },
    );

    // 以 dumpsys 为准，不看退出码
    const after = await readPackageDump(serial, pkg).catch(() => "");
    const granted = isPermissionGranted(after, permission);
    const detail = granted
      ? "granted"
      : grant.stderr
        ? `failed:${clip(grant.stderr)}`
        : "failed:not_declared_or_denied";
    results.push({ permission, granted, detail });
  }

  return {
    packageName: pkg,
    results,
    secureSettingsGranted: results.some(
      (item) => item.granted && item.permission === "android.permission.WRITE_SECURE_SETTINGS",
    ),
  };
}
