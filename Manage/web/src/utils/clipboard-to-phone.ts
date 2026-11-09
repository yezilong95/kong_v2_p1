export async function readComputerClipboardText(): Promise<string> {
  if (navigator.clipboard?.readText) {
    try {
      return await navigator.clipboard.readText();
    } catch {
      // fall through to prompt
    }
  }

  const manual = window.prompt("无法读取系统剪贴板，请在此粘贴要发送到手机输入框的内容：");
  return manual ?? "";
}
