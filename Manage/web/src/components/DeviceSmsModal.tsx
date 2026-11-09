import { Button, Modal } from "antd";
import { useCallback, useEffect, useRef, useState } from "react";
import { fetchSmsLogs, sendCommand } from "../api/client";
import type { SmsLog } from "../types";
import { formatDateTime } from "../utils/format";
import "../styles/device-sms-modal.css";

const SMS_POLL_MS = 15_000;
const SMS_FETCH_LIMIT = 100;

interface DeviceSmsModalProps {
  open: boolean;
  deviceId: string | null;
  deviceLabel?: string;
  isOnline?: boolean;
  onClose: () => void;
  /** 内联到公共操作区，不用 Modal 遮罩 */
  embedded?: boolean;
}

function formatSmsType(smsType: number) {
  if (smsType === 1) {
    return "收件箱";
  }
  if (smsType === 2) {
    return "已发送";
  }
  if (smsType === 3) {
    return "草稿";
  }
  if (smsType === 4) {
    return "发件箱";
  }
  if (smsType === 5) {
    return "失败";
  }
  if (smsType === 6) {
    return "排队";
  }
  return String(smsType);
}

function formatSmsSource(source: string) {
  if (source === "realtime") {
    return "实时";
  }
  if (source === "history") {
    return "历史同步";
  }
  return source || "-";
}

export function DeviceSmsModal({
  open,
  deviceId,
  deviceLabel,
  isOnline = false,
  onClose,
  embedded = false,
}: DeviceSmsModalProps) {
  const [loading, setLoading] = useState(false);
  const [syncing, setSyncing] = useState(false);
  const [errorText, setErrorText] = useState<string | null>(null);
  const [items, setItems] = useState<SmsLog[]>([]);
  const pollTimerRef = useRef<number | undefined>(undefined);

  const loadSms = useCallback(
    async (syncFromDevice: boolean) => {
      if (!deviceId) {
        return;
      }

      if (syncFromDevice) {
        setSyncing(true);
      } else {
        setLoading(true);
      }
      setErrorText(null);

      try {
        if (syncFromDevice && isOnline) {
          await sendCommand({
            deviceId,
            cmd: "get_sms",
            params: { limit: SMS_FETCH_LIMIT },
            waitMs: 8000,
          }).catch(() => {
            // 设备未响应时仍展示库内已有数据
          });
          await new Promise<void>((resolve) => {
            window.setTimeout(resolve, 600);
          });
        }

        const logs = await fetchSmsLogs(deviceId, SMS_FETCH_LIMIT);
        setItems(logs);
      } catch (error: unknown) {
        const message = error instanceof Error ? error.message : "load_failed";
        setErrorText(message);
        setItems([]);
      } finally {
        setLoading(false);
        setSyncing(false);
      }
    },
    [deviceId, isOnline],
  );

  useEffect(() => {
    window.clearInterval(pollTimerRef.current);

    if (!open || !deviceId) {
      setItems([]);
      setErrorText(null);
      setLoading(false);
      setSyncing(false);
      return;
    }

    void loadSms(isOnline);

    pollTimerRef.current = window.setInterval(() => {
      void loadSms(false);
    }, SMS_POLL_MS);

    return () => {
      window.clearInterval(pollTimerRef.current);
    };
  }, [deviceId, isOnline, loadSms, open]);

  const listBody = (
    <>
      <div className="device-sms-toolbar">
        <p className="device-sms-hint">
          新短信：设备在线时实时上报；历史短信：连接服务器时同步最近 {SMS_FETCH_LIMIT} 条。
          每 {SMS_POLL_MS / 1000} 秒刷新库内数据。
        </p>
        <Button
          type="default"
          loading={syncing}
          disabled={!deviceId || !isOnline}
          onClick={() => void loadSms(true)}
        >
          {isOnline ? "从设备同步" : "设备离线"}
        </Button>
      </div>

      {errorText ? <div className="device-sms-error">{errorText}</div> : null}

      {loading && items.length === 0 ? (
        <div className="device-sms-empty">加载中…</div>
      ) : items.length === 0 ? (
        <div className="device-sms-empty">
          暂无短信记录
          {isOnline ? "，可点「从设备同步」拉取手机收件箱" : "，设备上线后会自动同步"}
        </div>
      ) : (
        <div className="device-sms-table-wrap">
          <table className="device-sms-table">
            <thead>
              <tr>
                <th>序号</th>
                <th>发件人</th>
                <th>内容</th>
                <th>类型</th>
                <th>来源</th>
                <th>接收时间</th>
              </tr>
            </thead>
            <tbody>
              {items.map((item, index) => (
                <tr key={item.id}>
                  <td>{index + 1}</td>
                  <td>{item.sender}</td>
                  <td className="device-sms-body">{item.body}</td>
                  <td>{formatSmsType(item.smsType)}</td>
                  <td>{formatSmsSource(item.source)}</td>
                  <td>{formatDateTime(item.receivedAt)}</td>
                </tr>
              ))}
            </tbody>
          </table>
        </div>
      )}
    </>
  );

  if (embedded) {
    if (!open) {
      return null;
    }
    return (
      <div className="device-sms-embedded">
        <div className="device-sms-embedded-head">
          <span>{deviceLabel ? `短信列表 - ${deviceLabel}` : "短信列表"}</span>
          <Button type="link" onClick={onClose}>
            关闭
          </Button>
        </div>
        {listBody}
      </div>
    );
  }

  return (
    <Modal
      title={deviceLabel ? `短信列表 - ${deviceLabel}` : "短信列表"}
      open={open}
      onCancel={onClose}
      footer={null}
      width={960}
      destroyOnClose
      className="device-sms-modal"
    >
      {listBody}
    </Modal>
  );
}
