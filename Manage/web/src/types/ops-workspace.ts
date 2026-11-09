import { t } from "../i18n";
/** 设备详情页公共操作区当前视图（右侧工具栏切换，非 Modal） */
export type OpsWorkspaceKind = "none" | "lock" | "apps" | "sms" | "phone-agent" | "adb";

export function opsWorkspaceLabel(kind: OpsWorkspaceKind): string {
  switch (kind) {
    case "lock":
      return t("锁屏");
    case "apps":
      return t("应用列表");
    case "sms":
      return t("短信列表");
    case "phone-agent":
      return t("AI 操机");
    case "adb":
      return t("ADB功能");
    default:
      return t("公共操作区");
  }
}
