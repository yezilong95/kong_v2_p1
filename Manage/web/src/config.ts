import { t } from "./i18n/store";

const DEFAULT_SITE_NAME = "安卓 APP 管理系统";

function readEnvSiteName(): string {
  return String(import.meta.env.VITE_SITE_NAME ?? "").trim();
}

function readSiteName(): string {
  return readEnvSiteName() || DEFAULT_SITE_NAME;
}

/** 后台站点名称（登录页 / 顶栏 / document.title） */
export const SITE_NAME = readSiteName();

export function getDisplaySiteName(): string {
  return readEnvSiteName() || t(DEFAULT_SITE_NAME);
}
