import type { UiTreeDisplayMode } from "../utils/ui-tree";

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
  return (
    <>
      <section className="screen-ops-section">
        <div className="screen-ops-section-title">节点显示</div>
        <div className="screen-ops-mode-toggle" role="group" aria-label="UI 树显示模式">
          <button
            type="button"
            className={`screen-ops-mode-btn ${displayMode === "compact" ? "is-active" : ""}`}
            onClick={() => onDisplayModeChange("compact")}
          >
            简洁
          </button>
          <button
            type="button"
            className={`screen-ops-mode-btn ${displayMode === "full" ? "is-active" : ""}`}
            onClick={() => onDisplayModeChange("full")}
          >
            完整
          </button>
        </div>
        <p className="screen-ops-hint">
          简洁模式隐藏 icon、空 TextView 等无用节点。
          <br />
          当前 {displayNodeCount}
          {totalNodeCount > 0 ? ` / 原始 ${totalNodeCount}` : ""}
        </p>
      </section>

      <section className="screen-ops-section">
        <div className="screen-ops-section-title">翻译</div>
        <button
          type="button"
          className={`screen-ops-translate-btn ${translateEnabled ? "is-active" : ""}`}
          disabled={translateBusy}
          onClick={() => onTranslateEnabledChange(!translateEnabled)}
        >
          {translateBusy ? "翻译中…" : translateEnabled ? "翻译中文：开" : "翻译中文：关"}
        </button>
        <p className="screen-ops-hint">
          使用 AI 将节点英文标签译为简体中文，使用「AI 配置」中的翻译模型。
        </p>
        {translateError ? <p className="screen-ops-error">{translateError}</p> : null}
      </section>
    </>
  );
}
