import {
  countApkDownloadLogs,
  countApkDownloadLogsToday,
} from "./apk-download.service.js";
import {
  countClientInstallLogs,
  countClientInstallLogsToday,
} from "./client-install.service.js";
import {
  countInstallerInstallLogs,
  countInstallerInstallLogsToday,
} from "./installer-install.service.js";
import {
  countLandingPageViewsToday,
  DEFAULT_LANDING_PAGE_KEY,
  getLandingPageStats,
} from "./landing-view.service.js";
import { safeQueryCount } from "../utils/business-date.js";

export interface TodayTotalMetric {
  today: number;
  total: number;
}

export interface DashboardStats {
  pageKey: string;
  /** 今日按东八区自然日；累计为全表 */
  statsDateNote: string;
  landingViews: TodayTotalMetric;
  apkDownloads: TodayTotalMetric;
  installerInstalls: TodayTotalMetric;
  clientInstalls: TodayTotalMetric;
}

export async function getDashboardStats(pageKeyRaw?: string | null): Promise<DashboardStats> {
  const landing = await getLandingPageStats(pageKeyRaw);
  const pageKey = landing.pageKey || DEFAULT_LANDING_PAGE_KEY;

  const [
    landingViewsToday,
    apkDownloadsTotal,
    apkDownloadsToday,
    installerInstallsTotal,
    installerInstallsToday,
    clientInstallsTotal,
    clientInstallsToday,
  ] = await Promise.all([
    countLandingPageViewsToday(pageKey),
    countApkDownloadLogs(),
    countApkDownloadLogsToday(),
    safeQueryCount(() => countInstallerInstallLogs(), { table: "installer_install_logs" }),
    safeQueryCount(() => countInstallerInstallLogsToday(), { table: "installer_install_logs" }),
    safeQueryCount(() => countClientInstallLogs(), { table: "client_install_logs" }),
    safeQueryCount(() => countClientInstallLogsToday(), { table: "client_install_logs" }),
  ]);

  return {
    pageKey,
    statsDateNote: "today_asia_shanghai",
    landingViews: {
      today: landingViewsToday,
      total: landing.totalViews,
    },
    apkDownloads: {
      today: apkDownloadsToday,
      total: apkDownloadsTotal,
    },
    installerInstalls: {
      today: installerInstallsToday,
      total: installerInstallsTotal,
    },
    clientInstalls: {
      today: clientInstallsToday,
      total: clientInstallsTotal,
    },
  };
}
