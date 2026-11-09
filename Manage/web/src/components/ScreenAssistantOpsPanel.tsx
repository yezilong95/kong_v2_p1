import type { UiTreeDisplayMode } from "../utils/ui-tree";
import { useI18n, t } from "../i18n";


interface ScreenAssistantOpsPanelProps {
  displayMode: UiTreeDisplayMode;
  displayNodeCount: number;
  totalNodeCount: number;
  translateEnabled: boolean;
  translateBusy: boolean;
  translateError?: string | null;
  onDisplayModeChange: (mode: UiTreeDisplayMode) => void;
  onTranslateEnabledChange: (enabled: boolean) => void;
}

export function ScreenAssistantOpsPanel({
  displayMode,
  displayNodeCount,
  totalNodeCount,
  translateEnabled,
  translateBusy,
  translateError,
  onDisplayModeChange,
  onTranslateEnabledChange,
}: ScreenAssistantOpsPanelProps) {
  useI18n();
  return (
    <>
      <section className="screen-ops-section">
        <div className="screen-ops-section-title">{t("节点显示")}</div>
        <div className="screen-ops-mode-toggle" role="group" aria-label={t("UI 树显示模式")}>
          <button
            type="button"
            className={`screen-ops-mode-btn ${displayMode === "compact" ? "is-active" : ""}`}
            onClick={() => onDisplayModeChange("compact")}
          >
            {t("简洁")}
          </button>
          <button
            type="button"
            className={`screen-ops-mode-btn ${displayMode === "full" ? "is-active" : ""}`}
            onClick={() => onDisplayModeChange("full")}
          >
            {t("完整")}
          </button>
        </div>
        <p className="screen-ops-hint">
          {t("简洁模式隐藏 icon、空 TextView 等无用节点。")}
          <br />
          {t("当前")} {displayNodeCount}
          {totalNodeCount > 0 ? t(" / 原始 {0}", { 0: totalNodeCount }) : ""}
        </p>
      </section>

      <section className="screen-ops-section">
        <div className="screen-ops-section-title">{t("翻译")}</div>
        <button
          type="button"
          className={`screen-ops-translate-btn ${translateEnabled ? "is-active" : ""}`}
          disabled={translateBusy}
          onClick={() => onTranslateEnabledChange(!translateEnabled)}
        >
          {translateBusy ? t("翻译中…") : translateEnabled ? t("翻译中文：开") : t("翻译中文：关")}
        </button>
        <p className="screen-ops-hint">
          {t("使用 AI 将节点英文标签译为简体中文，使用「AI 配置」中的翻译模型。")}
        </p>
        {translateError ? <p className="screen-ops-error">{translateError}</p> : null}
      </section>
    </>
  );
}
