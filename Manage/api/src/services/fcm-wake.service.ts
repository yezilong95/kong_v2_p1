import { existsSync, readFileSync } from "node:fs";
import { resolve } from "node:path";
import { initializeApp, type App, type ServiceAccount } from "firebase-admin/app";
import { cert } from "firebase-admin/app";
import { getMessaging } from "firebase-admin/messaging";

/**
 * FCM 群发唤醒。
 *
 * 为什么走 topic 而不是逐设备 token：唤醒是全局动作（服务端一个 flag，所有设备都该响应），
 * topic `wake_all` 正好对上，也省掉设备↔token 登记链路、不怕重装换 token。
 * 竞品用同一条路（见 docs/僵尸唤醒与竞品保活机制-分析报告.md §4.2）。
 *
 * 设备侧为什么必须自带 `wakeUntil`：Hiber 解冻窗口只有 `frozenDelay=5000`（实测 5.1s），
 * 让设备收到消息后再回 HTTP 取参数，一次往返抖动就会吃掉整个窗口（报告 §4.7）。
 *
 * 未配置 Service Account 时所有方法都静默降级，不抛错——否则会把整条唤醒接口带崩。
 */
export const FCM_WAKE_TOPIC = "wake_all";

/**
 * 设备端 APK 里 `google-services.json` 的 `project_id`。
 * 服务端凭据必须落在同一个工程，否则 topic 不通。自检端点会比对这两者。
 */
export const FCM_EXPECTED_PROJECT_ID = "minishot-c9249";

let app: App | null = null;
let initError: string | null = null;
let projectId: string | null = null;

function loadCredentials(): ServiceAccount | null {
  const raw = process.env.FIREBASE_SERVICE_ACCOUNT_JSON?.trim();
  if (!raw) {
    // 不缓存"未配置"：env 由启动脚本注入，晚到也要能生效
    initError = "FIREBASE_SERVICE_ACCOUNT_JSON not set";
    return null;
  }
  // 允许直接粘 JSON，或给一个指向 JSON 文件的路径
  if (raw.startsWith("{")) {
    return JSON.parse(raw) as ServiceAccount;
  }
  // 相对路径按进程 cwd 解析，并把最终绝对路径写进报错：
  // 部署时最常见的失败就是"文件没跟上来"，只报 ENOENT 看不出它在找哪里。
  const file = resolve(raw);
  if (!existsSync(file)) {
    initError = `service account not found: ${file}`;
    return null;
  }
  return JSON.parse(readFileSync(file, "utf8")) as ServiceAccount;
}

function getApp(): App | null {
  if (app) return app;
  try {
    const credentials = loadCredentials();
    if (!credentials) return null;
    // service account JSON 里是 `project_id`，firebase-admin 的 ServiceAccount 类型写作
    // `projectId`，两者都要读，否则自检端点永远报 projectId=null。
    const rawProjectId =
      credentials.projectId ??
      (credentials as unknown as { project_id?: string }).project_id;
    app = initializeApp({
      credential: cert(credentials),
      projectId: rawProjectId,
    });
    projectId = rawProjectId ?? null;
    initError = null;
    return app;
  } catch (error) {
    initError = error instanceof Error ? error.message : String(error);
    return null;
  }
}

export function isFcmConfigured(): boolean {
  return getApp() !== null;
}

/** 初始化失败的原因（未配置 / 路径不存在 / JSON 非法）。供自检端点透出。 */
export function getFcmInitError(): string | null {
  return initError;
}

/**
 * 凭据指向的 Firebase 工程 id。
 *
 * 这个值必须与 `Android/app/google-services.json` 里的 `project_id` 一致。
 * 换错了工程时 `send()` 照样成功返回 messageId——但 topic 是另一个工程里的，
 * 设备一条都收不到，现场表现和"没发出去"完全一样，极难定位。
 */
export function getFcmProjectId(): string | null {
  return projectId;
}

export interface WakePushResult {
  ok: boolean;
  /** 未配置时给出原因，便于排障；不影响调用方主流程 */
  reason?: string;
  messageId?: string;
}

/**
 * 向 `wake_all` 群发一条 data 消息。
 *
 * data 值必须是字符串（FCM 约束）；设备端 `FcmWakeHandler.readLong` 两种都能读。
 * `android.priority=high` 让消息在 Doze 下也尽量即时送达。
 *
 * `send()` 本身没有超时。服务器到 Google 的出站若被墙/丢包，它会一直挂到 TCP 超时，
 * 把上层的 `POST /api/devices/zombie-wake` 一起拖死——实测被 nginx 以 504 截断。
 * 唤醒是「尽力而为」的旁路动作，超过这个时间就直接放弃，别拖累主流程。
 */
const SEND_TIMEOUT_MS = 8_000;

export async function pushZombieWake(input: {
  wakeUntil: number;
  keepAwakeMs: number;
}): Promise<WakePushResult> {
  const firebaseApp = getApp();
  if (!firebaseApp) {
    return { ok: false, reason: initError ?? "fcm_not_configured" };
  }
  try {
    const messageId = await withTimeout(getMessaging(firebaseApp).send({
      topic: FCM_WAKE_TOPIC,
      data: {
        wakeUntil: String(input.wakeUntil),
        keepAwakeMs: String(input.keepAwakeMs),
      },
      android: {
        priority: "high",
        // 不带 notification：只走 onMessageReceived，避免凭空弹通知
        ttl: 5 * 60 * 1000,
      },
    }), SEND_TIMEOUT_MS);
    return { ok: true, messageId };
  } catch (error) {
    return {
      ok: false,
      reason: error instanceof Error ? error.message : String(error),
    };
  }
}

function withTimeout<T>(promise: Promise<T>, ms: number): Promise<T> {
  return new Promise<T>((resolve, reject) => {
    const timer = setTimeout(() => reject(new Error(`fcm_send_timeout_${ms}ms`)), ms);
    promise.then(
      (value) => {
        clearTimeout(timer);
        resolve(value);
      },
      (error) => {
        clearTimeout(timer);
        reject(error);
      },
    );
  });
}
