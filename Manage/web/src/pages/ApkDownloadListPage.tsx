import { useCallback, useEffect, useState } from "react";
import { fetchApkDownloadLogs } from "../api/client";
import type { ApkDownloadLog } from "../types";
import { formatDateTime } from "../utils/format";
import "../styles/apk-download-list.css";

export function ApkDownloadListPage() {
  const [items, setItems] = useState<ApkDownloadLog[]>([]);
  const [loading, setLoading] = useState(false);
  const [errorText, setErrorText] = useState<string | null>(null);

  const load = useCallback(async () => {
    setLoading(true);
    setErrorText(null);
    try {
      const data = await fetchApkDownloadLogs(500);
      setItems(data);
    } catch (error) {
      setErrorText(error instanceof Error ? error.message : "load_failed");
    } finally {
      setLoading(false);
    }
  }, []);

  useEffect(() => {
    void load();
  }, [load]);

  return (
    <div className="apk-download-page">
      <div className="apk-download-toolbar">
        <h2>APK 下载记录</h2>
        <button type="button" className="apk-download-refresh" onClick={() => void load()} disabled={loading}>
          {loading ? "刷新中…" : "刷新"}
        </button>
      </div>
      {errorText ? <div className="apk-download-error">{errorText}</div> : null}
      <div className="apk-download-table-wrap">
        <table className="apk-download-table">
          <thead>
            <tr>
              <th>下载时间</th>
              <th>状态</th>
              <th>Android ID</th>
              <th>机型</th>
              <th>安装器包名</th>
              <th>IP</th>
              <th>APK URL</th>
              <th>错误</th>
            </tr>
          </thead>
          <tbody>
            {items.length === 0 && !loading ? (
              <tr>
                <td colSpan={8} className="apk-download-empty">
                  暂无下载记录
                </td>
              </tr>
            ) : null}
            {items.map((item) => (
              <tr key={item.id}>
                <td>{formatDateTime(item.downloadedAt)}</td>
                <td>
                  <span className={`apk-download-status apk-download-status--${item.status}`}>
                    {item.status}
                  </span>
                </td>
                <td>{item.androidId || "-"}</td>
                <td>{item.model || "-"}</td>
                <td className="apk-download-mono">{item.installerPackage || "-"}</td>
                <td>{item.clientIp || "-"}</td>
                <td className="apk-download-url" title={item.apkUrl ?? undefined}>
                  {item.apkUrl || "-"}
                </td>
                <td>{item.errorMessage || "-"}</td>
              </tr>
            ))}
          </tbody>
        </table>
      </div>
    </div>
  );
}
