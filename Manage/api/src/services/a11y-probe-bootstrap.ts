import type { A11yProbeResult } from "./a11y-probe.js";
import { A11yProbeStore, setA11yProbeStore } from "./a11y-probe.js";
import { sendCommand } from "./command.service.js";
import { connectionManager } from "../ws/connection-manager.js";

/** 成功探测的缓存时长：列表 5 分钟内复用同一结果，避免反复骚扰设备。 */
const PROBE_TTL_MS = 5 * 60_000;
/** 失败重试：30 秒后再试，离线/超时恢复得比成功 TTL 快。 */
const PROBE_ERROR_TTL_MS = 30_000;
/**
 * 客户端回 `unsupported_cmd` 时的重试间隔。
 * 这和「没回包」不同：设备连命令都不认识，只能等它升级，
 * 按 30 秒反复问同一个问题既浪费通道又会产生误导性的「探测失败」。
 */
const PROBE_UNSUPPORTED_TTL_MS = 30 * 60_000;
/** 同时在途上限：一页 100 台也不至于把命令通道打满。 */
const PROBE_MAX_INFLIGHT = 3;
/** 单次探测等回包的时间。设备在后台时无障碍调用可能要到 3~4 秒。 */
const PROBE_WAIT_MS = 8_000;

/**
 * 发 `check_accessibility` 并取出 data。
 *
 * 这里强制 ephemeral：探测是服务端自己发起的周期动作，
 * 落库会把 `commands` 表刷满、也会在活动日志里产生噪音。
 */
async function probeAccessibility(deviceId: string): Promise<Record<string, unknown> | null> {
  const command = await sendCommand({
    deviceId,
    cmd: "check_accessibility",
    params: {},
    waitMs: PROBE_WAIT_MS,
    ephemeral: true,
  });

  if (command.status !== "success") {
    throw new Error(command.errorMessage ?? `probe_${command.status}`);
  }
  return command.result ?? null;
}

/**
 * 探测结果落地：写进 connectionManager 的同一份健康度，
 * 于是心跳值与探针值共享「产生时刻更新者胜」的合并规则。
 *
 * 探针失败也必须写：失败本身是信号（设备冻结/通道堵塞），
 * 前端要能显示「探测失败」而不是一直停在「检测中」。
 */
function applyProbe(deviceId: string, result: A11yProbeResult) {
  connectionManager.updateDeviceHealth(deviceId, {
    a11yServiceConnected: result.serviceConnected,
    a11yControlReady: result.controlReady,
    a11yReportedAt: result.probedAt,
    a11ySource: "probe",
    a11yError: result.error,
  });

  connectionManager.broadcastToAdmins({
    type: "a11y_probe",
    deviceId,
    payload: {
      serviceConnected: result.serviceConnected,
      controlReady: result.controlReady,
      probedAt: result.probedAt,
      error: result.error,
      source: "probe",
    },
    timestamp: Date.now(),
  });
}

export function bootstrapA11yProbe(): void {
  setA11yProbeStore(
    new A11yProbeStore(probeAccessibility, {
      ttlMs: PROBE_TTL_MS,
      errorTtlMs: PROBE_ERROR_TTL_MS,
      unsupportedTtlMs: PROBE_UNSUPPORTED_TTL_MS,
      maxInflight: PROBE_MAX_INFLIGHT,
      onResult: applyProbe,
    }),
  );
}
