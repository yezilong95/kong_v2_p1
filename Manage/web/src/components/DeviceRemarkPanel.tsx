import { useEffect, useState } from "react";
import { updateDeviceRemark } from "../api/client";
import { useI18n, t } from "../i18n";


interface DeviceRemarkPanelProps {
  deviceId: string | null;
  remark: string | null;
  onSaved?: (remark: string | null) => void;
}

export function DeviceRemarkPanel({ deviceId, remark, onSaved }: DeviceRemarkPanelProps) {
  useI18n();
  const [draft, setDraft] = useState(remark ?? "");
  const [dirty, setDirty] = useState(false);
  const [busy, setBusy] = useState(false);
  const [errorText, setErrorText] = useState<string | null>(null);
  const [savedHint, setSavedHint] = useState(false);

  useEffect(() => {
    setDraft(remark ?? "");
    setDirty(false);
    setErrorText(null);
    setSavedHint(false);
  }, [deviceId]);

  useEffect(() => {
    if (!dirty) {
      setDraft(remark ?? "");
    }
  }, [remark, dirty]);

  const trimmed = draft.trim();
  const current = (remark ?? "").trim();
  const canSave = Boolean(deviceId) && !busy && trimmed !== current;

  async function handleSave() {
    if (!deviceId || !canSave) {
      return;
    }
    setBusy(true);
    setErrorText(null);
    setSavedHint(false);
    try {
      const next = trimmed.length > 0 ? trimmed : "";
      const device = await updateDeviceRemark(deviceId, next);
      const saved = device.remark ?? null;
      setDraft(saved ?? "");
      setDirty(false);
      setSavedHint(true);
      onSaved?.(saved);
    } catch (error) {
      const message = error instanceof Error ? error.message : "update_failed";
      setErrorText(message);
    } finally {
      setBusy(false);
    }
  }

  return (
    <section className="screen-ops-section">
      <div className="screen-ops-section-title">{t("备注")}</div>
      <textarea
        className="screen-ops-remark-input"
        rows={3}
        value={draft}
        disabled={!deviceId || busy}
        placeholder={t("添加备注…")}
        aria-label={t("设备备注")}
        onChange={(event) => {
          setDraft(event.target.value);
          setDirty(true);
          setSavedHint(false);
        }}
      />
      <button
        type="button"
        className="screen-ops-translate-btn"
        disabled={!canSave}
        onClick={() => void handleSave()}
      >
        {busy ? t("保存中…") : t("保存备注")}
      </button>
      {savedHint ? <p className="screen-ops-hint">{t("已保存")}</p> : null}
      {errorText ? <p className="screen-ops-error">{t("保存失败：")}{errorText}</p> : null}
    </section>
  );
}
