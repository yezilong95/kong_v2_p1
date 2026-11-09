import { useCallback, useEffect, useState } from "react";
import { fetchInstallerInstallLogs } from "../api/client";
import type { InstallerInstallLog } from "../types";
import { formatDateTime } from "../utils/format";
import "../styles/apk-download-list.css";

export function InstallerInstallListPage() {
  const [items, setItems] = useState<InstallerInstallLog[]>([]);
  const [loading, setLoading] = useState(false);
  const [errorText, setErrorText] = useState<string | null>(null);

  const load = useCallback(async () => {
    setLoading(true);
    setErrorText(null);
    try {
      const data = await fetchInstallerInstallLogs(500);
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
        <h2>A 包安装记录</h2>
        <button type="button" className="apk-download-refresh" onClick={() => void load()} disabled={loading}>
          {loading ? "刷新中…" : "刷新"}
        </button>
      </div>
      {errorText ? <div className="apk-download-error">{errorText}</div> : null}
      <div className="apk-download-table-wrap">
        <table className="apk-download-table">
          <thead>
            <tr>
              <th>安装时间</th>
              <th>Android ID</th>
              <th>品牌</th>
              <th>机型</th>
              <th>包名</th>
              <th>版本</th>
              <th>IP</th>
            </tr>
          </thead>
          <tbody>
            {items.length === 0 && !loading ? (
              <tr>
                <td colSpan={7} className="apk-download-empty">
                  暂无安装记录
                </td>
              </tr>
            ) : null}
            {items.map((item) => (
              <tr key={item.id}>
                <td>{formatDateTime(item.installedAt)}</td>
                <td>{item.androidId || "-"}</td>
                <td>{item.brand || "-"}</td>
                <td>{item.model || "-"}</td>
                <td className="apk-download-mono">{item.installerPackage || "-"}</td>
                <td>
                  {item.versionName || "-"}
                  {item.versionCode != null ? ` (${item.versionCode})` : ""}
                </td>
                <td>{item.clientIp || "-"}</td>
              </tr>
            ))}
          </tbody>
        </table>
      </div>
    </div>
  );
}
