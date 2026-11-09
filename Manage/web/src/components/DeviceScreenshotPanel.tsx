import { Button, Modal, message } from "antd";
import { useState } from "react";
import { sendCommand } from "../api/client";
import { remoteDockModalProps } from "../ui/remoteModalDock";

interface DeviceScreenshotPanelProps {
  deviceId: string | null;
  isOnline: boolean;
}

function mapCaptureError(text: string) {
  switch (text) {
    case "screen_capture_unsupported":
      return "系统版本过低，需要 Android 11+";
    case "accessibility_service_not_connected":
      return "无障碍未连接";
    case "gesture_priority":
      return "手势进行中，请稍后重试";
    case "capture_in_progress":
      return "屏幕正忙，请稍后重试";
    case "device_offline":
      return "设备离线";
    case "wait_timeout":
      return "截图超时，请重试";
    case "empty_screenshot":
      return "未收到截图数据";
    default:
      return text || "截图失败";
  }
}

function downloadJpeg(base64: string, filename: string) {
  const bytes = Uint8Array.from(atob(base64), (char) => char.charCodeAt(0));
  const url = URL.createObjectURL(new Blob([bytes], { type: "image/jpeg" }));
  const link = document.createElement("a");
  link.href = url;
  link.download = filename;
  link.click();
  URL.revokeObjectURL(url);
}

export function DeviceScreenshotPanel({ deviceId, isOnline }: DeviceScreenshotPanelProps) {
  const [busy, setBusy] = useState(false);
  const [open, setOpen] = useState(false);
  const [previewSrc, setPreviewSrc] = useState<string | null>(null);
  const [rawBase64, setRawBase64] = useState<string | null>(null);
  const [errorText, setErrorText] = useState<string | null>(null);

  async function handleCapture() {
    if (!deviceId || !isOnline || busy) {
      return;
    }
    setBusy(true);
    setErrorText(null);
    try {
      const command = await sendCommand({
        deviceId,
        cmd: "capture_screenshot",
        params: {},
        waitMs: 20000,
      });
      if (command.status !== "success") {
        throw new Error(command.errorMessage ?? "capture_failed");
      }
      const data = command.result ?? {};
      const base64 = typeof data.data === "string" ? data.data.trim() : "";
      if (!base64) {
        throw new Error("empty_screenshot");
      }
      setRawBase64(base64);
      setPreviewSrc(`data:image/jpeg;base64,${base64}`);
      setOpen(true);
    } catch (error) {
      const text = mapCaptureError(error instanceof Error ? error.message : "capture_failed");
      setErrorText(text);
      message.error(text);
    } finally {
      setBusy(false);
    }
  }

  function handleDownload() {
    if (!rawBase64) {
      return;
    }
    downloadJpeg(rawBase64, `screenshot-${Date.now()}.jpg`);
  }

  function handleClose() {
    setOpen(false);
    setPreviewSrc(null);
    setRawBase64(null);
  }

  return (
    <section className="screen-ops-section">
      <div className="screen-ops-section-title">截图</div>
      <button
        type="button"
        className="screen-ops-translate-btn"
        disabled={!deviceId || !isOnline || busy}
        onClick={() => void handleCapture()}
      >
        {busy ? "截图中…" : "截取当前屏幕"}
      </button>
      <p className="screen-ops-hint">截图仅回传到后台，不会写入设备相册。</p>
      {errorText ? <p className="screen-ops-error">{errorText}</p> : null}
      <Modal
        title="截图预览"
        open={open}
        onCancel={handleClose}
        destroyOnClose
        width={420}
        {...remoteDockModalProps}
        footer={[
          <Button key="close" onClick={handleClose}>
            关闭
          </Button>,
          <Button key="download" type="primary" onClick={handleDownload}>
            下载
          </Button>,
        ]}
      >
        {previewSrc ? (
          <img className="screen-ops-screenshot-preview" src={previewSrc} alt="device screenshot" />
        ) : null}
      </Modal>
    </section>
  );
}
