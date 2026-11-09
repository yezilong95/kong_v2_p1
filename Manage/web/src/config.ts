const DEFAULT_SITE_NAME = "安卓 APP 管理系统";

function readSiteName(): string {
  const value = String(import.meta.env.VITE_SITE_NAME ?? "").trim();
  return value || DEFAULT_SITE_NAME;
}

/** 后台站点名称（登录页 / 顶栏 / document.title） */
export const SITE_NAME = readSiteName();
