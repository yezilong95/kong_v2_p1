import {
  AppstoreAddOutlined,
  CloudDownloadOutlined,
  EyeOutlined,
  MobileOutlined,
  ReloadOutlined,
} from "@ant-design/icons";
import { Button, Card, Col, Row, Statistic, Typography, message } from "antd";
import { useCallback, useEffect, useState, type ReactNode } from "react";
import { fetchDashboardStats } from "../api/client";
import type { DashboardStats, TodayTotalMetric } from "../types";
import "../styles/home-dashboard.css";

interface StatCardProps {
  title: string;
  metric: TodayTotalMetric | undefined;
  icon: ReactNode;
  meta: string;
  loading: boolean;
}

function StatCard({ title, metric, icon, meta, loading }: StatCardProps) {
  return (
    <Card className="home-dashboard-card" loading={loading}>
      <div className="home-dashboard-card-title">
        {icon}
        <span>{title}</span>
      </div>
      <div className="home-dashboard-dual">
        <Statistic
          title="今日"
          value={metric?.today ?? 0}
          suffix={<span className="home-dashboard-suffix">次</span>}
        />
        <Statistic
          title="累计"
          value={metric?.total ?? 0}
          suffix={<span className="home-dashboard-suffix">次</span>}
        />
      </div>
      <div className="home-dashboard-card-meta">{meta}</div>
    </Card>
  );
}

function normalizeDashboardStats(raw: DashboardStats | null | undefined): DashboardStats | null {
  if (!raw || typeof raw !== "object") {
    return null;
  }
  if (raw.landingViews && typeof raw.landingViews === "object") {
    return raw;
  }
  const legacy = raw as DashboardStats & {
    totalViews?: number;
    totalApkDownloads?: number;
  };
  return {
    pageKey: legacy.pageKey ?? "miluHome",
    statsDateNote: "legacy_api",
    landingViews: { today: 0, total: Number(legacy.totalViews ?? 0) },
    apkDownloads: { today: 0, total: Number(legacy.totalApkDownloads ?? 0) },
    installerInstalls: { today: 0, total: 0 },
    clientInstalls: { today: 0, total: 0 },
  };
}

export function HomeDashboardPage() {
  const [stats, setStats] = useState<DashboardStats | null>(null);
  const [loading, setLoading] = useState(false);
  const [errorText, setErrorText] = useState<string | null>(null);

  const load = useCallback(async () => {
    setLoading(true);
    setErrorText(null);
    try {
      const data = normalizeDashboardStats(await fetchDashboardStats("miluHome"));
      if (!data) {
        throw new Error("dashboard_stats_empty");
      }
      setStats(data);
    } catch (error) {
      const text = error instanceof Error ? error.message : "加载失败";
      setErrorText(text);
      setStats(null);
      message.error(text);
    } finally {
      setLoading(false);
    }
  }, []);

  useEffect(() => {
    void load();
  }, [load]);

  const cardLoading = loading && !stats;

  return (
    <div className="home-dashboard">
      <div className="home-dashboard-toolbar">
        <Typography.Title level={3} style={{ margin: 0 }}>
          首页
        </Typography.Title>
        <Button icon={<ReloadOutlined />} loading={loading} onClick={() => void load()}>
          刷新
        </Button>
      </div>

      {errorText ? (
        <div className="home-dashboard-error" role="alert">
          统计加载失败：{errorText}（若刚上线 B 包安装统计，请在服务器执行{" "}
          <code>npm run db:migrate-p24</code> 后重启 API）
        </div>
      ) : null}

      <Row gutter={[16, 16]}>
        <Col xs={24} sm={12} lg={6}>
          <StatCard
            title="落地页访问"
            metric={stats?.landingViews}
            icon={<EyeOutlined />}
            meta={`${stats?.pageKey ?? "miluHome"} · 今日按东八区`}
            loading={cardLoading}
          />
        </Col>
        <Col xs={24} sm={12} lg={6}>
          <StatCard
            title="APK 下载"
            metric={stats?.apkDownloads}
            icon={<CloudDownloadOutlined />}
            meta="apk_download_logs · 含落地页与 A 内安装"
            loading={cardLoading}
          />
        </Col>
        <Col xs={24} sm={12} lg={6}>
          <StatCard
            title="A 包安装"
            metric={stats?.installerInstalls}
            icon={<AppstoreAddOutlined />}
            meta="installer_install_logs · 安装器首启"
            loading={cardLoading}
          />
        </Col>
        <Col xs={24} sm={12} lg={6}>
          <StatCard
            title="B 包安装"
            metric={stats?.clientInstalls}
            icon={<MobileOutlined />}
            meta="client_install_logs · 被控端首启"
            loading={cardLoading}
          />
        </Col>
      </Row>
    </div>
  );
}
