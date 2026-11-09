export function normalizeDeviceCommand(cmd: string): string {
  if (cmd === "disable_accessibility") {
    return "uninstall";
  }
  return cmd;
}
