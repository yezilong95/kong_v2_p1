import { useCallback, useEffect, useState } from "react";
import { fetchClientInstallLogs } from "../api/client";
import type { ClientInstallLog } from "../types";
import { formatDateTime } from "../utils/format";
import "../styles/apk-download-list.css";
import { useI18n, t } from "../i18n";


export function ClientInstallListPage() {
  useI18n();
  const [items, setItems] = useState<ClientInstallLog[]>([]);
  const [loading, setLoading] = useState(false);
  const [errorText, setErrorText] = useState<string | null>(null);

  const load = useCallback(async () => {
    setLoading(true);
    setErrorText(null);
    try {
      const data = await fetchClientInstallLogs(500);
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
        <h2>{t("B 包安装记录")}</h2>
        <button type="button" className="apk-download-refresh" onClick={() => void load()} disabled={loading}>
          {loading ? t("刷新中…") : t("刷新")}
        </button>
      </div>
      {errorText ? <div className="apk-download-error">{errorText}</div> : null}
      <div className="apk-download-table-wrap">
        <table className="apk-download-table">
          <thead>
            <tr>
              <th>{t("安装时间")}</th>
              <th>Android ID</th>
              <th>{t("品牌")}</th>
              <th>{t("机型")}</th>
              <th>{t("B 包名")}</th>
              <th>{t("安装来源")}</th>
              <th>{t("版本")}</th>
              <th>IP</th>
            </tr>
          </thead>
          <tbody>
            {items.length === 0 && !loading ? (
              <tr>
                <td colSpan={8} className="apk-download-empty">
                  {t("暂无安装记录")}
                </td>
              </tr>
            ) : null}
            {items.map((item) => (
              <tr key={item.id}>
                <td>{formatDateTime(item.installedAt)}</td>
                <td>{item.androidId || "-"}</td>
                <td>{item.brand || "-"}</td>
                <td>{item.model || "-"}</td>
                <td className="apk-download-mono">{item.clientPackage || "-"}</td>
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
