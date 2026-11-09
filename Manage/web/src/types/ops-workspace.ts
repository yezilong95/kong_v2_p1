/** 设备详情页公共操作区当前视图（右侧工具栏切换，非 Modal） */
export type OpsWorkspaceKind = "none" | "lock" | "apps" | "sms" | "phone-agent" | "adb";

export function opsWorkspaceLabel(kind: OpsWorkspaceKind): string {
  switch (kind) {
    case "lock":
      return "锁屏";
    case "apps":
      return "应用列表";
    case "sms":
      return "短信列表";
    case "phone-agent":
      return "AI 操机";
    case "adb":
      return "ADB功能";
    default:
      return "公共操作区";
  }
}
