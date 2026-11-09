import type { CSSProperties } from "react";
import { useI18n, t } from "../i18n";


/** Fallback phone aspect when frame / UI tree size unknown */
export const DEFAULT_DEVICE_SIZE = { width: 1080, height: 2408 } as const;

export function deviceStageStyle(width: number, height: number): CSSProperties {
  return {
    aspectRatio: `${width} / ${height}`,
  };
}

interface MirrorSkeletonProps {
  caption?: string;
  hint?: string;
}

/** Phone-shaped shimmer for mirror pane while waiting / idle */
export function MirrorSkeleton({
  caption = t("加载画面中…"),
  hint,
}: MirrorSkeletonProps) {
  useI18n();
  return (
    <div className="screen-skeleton screen-skeleton--mirror" aria-hidden="true">
      <div className="screen-skeleton-status">
        <span className="screen-skeleton-bone screen-skeleton-bone--pill" />
        <span className="screen-skeleton-bone screen-skeleton-bone--dot" />
      </div>
      <div className="screen-skeleton-hero">
        <span className="screen-skeleton-bone screen-skeleton-bone--block" />
        <span className="screen-skeleton-bone screen-skeleton-bone--line" />
        <span className="screen-skeleton-bone screen-skeleton-bone--line screen-skeleton-bone--short" />
      </div>
      <div className="screen-skeleton-grid">
        {Array.from({ length: 6 }).map((_, index) => (
          <span key={index} className="screen-skeleton-bone screen-skeleton-bone--card" />
        ))}
      </div>
      <div className="screen-skeleton-caption">
        <span>{caption}</span>
        {hint ? <span className="screen-skeleton-hint">{hint}</span> : null}
      </div>
    </div>
  );
}

/** Wireframe + tile list shimmer for assistant pane */
export function AssistantSkeleton({ caption = t("加载节点…") }: { caption?: string }) {
  useI18n();
  return (
    <div className="screen-assistant-wrap screen-skeleton-assistant" aria-busy="true">
      <div className="screen-assistant-body">
        <div className="screen-pane-stage screen-assistant-wireframe">
          <div
            className="screen-stage screen-stage--device screen-stage--assistant"
            style={deviceStageStyle(DEFAULT_DEVICE_SIZE.width, DEFAULT_DEVICE_SIZE.height)}
          >
            <MirrorSkeleton caption={caption} />
          </div>
        </div>
        <div className="screen-assistant-tile">
          <div className="screen-assistant-tile-head">{t("节点平铺")}</div>
          <div className="screen-skeleton-tile-list">
            {Array.from({ length: 8 }).map((_, index) => (
              <span key={index} className="screen-skeleton-bone screen-skeleton-bone--tile" />
            ))}
          </div>
        </div>
      </div>
    </div>
  );
}

/** Compact ops panel shimmer */
export function OpsPanelSkeleton() {
  useI18n();
  return (
    <div className="screen-ops-panel screen-skeleton-ops" aria-busy="true">
      <section className="screen-ops-section">
        <div className="screen-ops-section-title">{t("节点显示")}</div>
        <div className="screen-skeleton-ops-row">
          <span className="screen-skeleton-bone screen-skeleton-bone--btn" />
          <span className="screen-skeleton-bone screen-skeleton-bone--btn" />
        </div>
        <span className="screen-skeleton-bone screen-skeleton-bone--line" />
        <span className="screen-skeleton-bone screen-skeleton-bone--line screen-skeleton-bone--short" />
      </section>
      <section className="screen-ops-section">
        <div className="screen-ops-section-title">{t("翻译")}</div>
        <span className="screen-skeleton-bone screen-skeleton-bone--btn screen-skeleton-bone--wide" />
        <span className="screen-skeleton-bone screen-skeleton-bone--line" />
      </section>
    </div>
  );
}
