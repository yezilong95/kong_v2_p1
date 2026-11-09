import {
  LogoutOutlined,
  MobileOutlined,
  CloudDownloadOutlined,
  AppstoreAddOutlined,
  VideoCameraOutlined,
  CreditCardOutlined,
  HomeOutlined,
  RobotOutlined,
  TeamOutlined,
  CloudServerOutlined,
} from "@ant-design/icons";
import { ProLayout } from "@ant-design/pro-components";
import { Button, Dropdown } from "antd";
import { useMemo, useState } from "react";
import { Outlet, useLocation, useNavigate } from "react-router-dom";
import { useAuth } from "../auth/AuthProvider";
import { LanguageSwitch } from "../components/LanguageSwitch";
import { getDisplaySiteName } from "../config";
import { useI18n, t } from "../i18n";

export function AdminLayout() {
  const { locale } = useI18n();
  const navigate = useNavigate();
  const location = useLocation();
  const { username, role, logout } = useAuth();
  const [collapsed, setCollapsed] = useState(true);

  const menuRoutes = useMemo(() => {
    const routes = [
      { path: "/admin/home", name: t("首页"), icon: <HomeOutlined /> },
      { path: "/admin/devices", name: t("设备列表"), icon: <MobileOutlined /> },
      { path: "/admin/payment-config", name: t("支付配置"), icon: <CreditCardOutlined /> },
      { path: "/admin/videos", name: t("视频管理"), icon: <VideoCameraOutlined /> },
      { path: "/admin/apk-downloads", name: t("下载记录"), icon: <CloudDownloadOutlined /> },
      { path: "/admin/installer-installs", name: t("A包安装记录"), icon: <AppstoreAddOutlined /> },
      { path: "/admin/client-installs", name: t("B包安装记录"), icon: <AppstoreAddOutlined /> },
      { path: "/admin/client-apk", name: t("B包更新"), icon: <CloudDownloadOutlined /> },
      { path: "/admin/ai-config", name: t("AI 配置"), icon: <RobotOutlined /> },
      { path: "/admin/peer-servers", name: t("设备转移"), icon: <CloudServerOutlined /> },
    ];
    if (role === "super") {
      routes.push({
        path: "/admin/admins",
        name: t("管理员管理"),
        icon: <TeamOutlined />,
      });
    }
    return routes;
  }, [locale, role]);

  const roleLabel = role === "super" ? t("超管") : role === "admin" ? t("管理员") : "";

  return (
    <ProLayout
      title={getDisplaySiteName()}
      logo={false}
      layout="mix"
      fixSiderbar
      breakpoint={false}
      collapsed={collapsed}
      onCollapse={setCollapsed}
      contentWidth="Fluid"
      location={{ pathname: location.pathname }}
      token={{
        bgLayout: "#040a12",
        colorTextAppListIcon: "#00e5ff",
        header: {
          colorBgHeader: "rgba(10,20,32,0.88)",
          colorHeaderTitle: "#00e5ff",
          colorTextMenu: "#6a9aab",
          colorTextMenuSelected: "#00e5ff",
          colorBgMenuItemSelected: "rgba(0,229,255,0.12)",
          colorTextMenuActive: "#00e5ff",
        },
        sider: {
          colorMenuBackground: "#060e18",
          colorTextMenu: "#6a9aab",
          colorTextMenuSelected: "#00e5ff",
          colorBgMenuItemSelected: "rgba(0,229,255,0.12)",
          colorTextMenuActive: "#00e5ff",
          colorTextMenuItemHover: "#d7f7ff",
        },
      }}
      route={{
        path: "/admin",
        routes: menuRoutes,
      }}
      menuItemRender={(item, dom) => (
        <button
          type="button"
          className="admin-menu-link"
          onClick={() => navigate(item.path ?? "/admin/home")}
        >
          {dom}
        </button>
      )}
      actionsRender={() => [
        <LanguageSwitch key="lang" className="admin-lang-switch" />,
        <Dropdown
          key="user"
          menu={{
            items: [
              {
                key: "logout",
                icon: <LogoutOutlined />,
                label: t("退出登录"),
                onClick: () => {
                  logout();
                  navigate("/login", { replace: true });
                },
              },
            ],
          }}
        >
          <Button type="text">
            {username ?? t("管理员")}
            {roleLabel ? `（${roleLabel}）` : ""}
          </Button>
        </Dropdown>,
      ]}
    >
      <div className="admin-layout-content">
        <Outlet />
      </div>
    </ProLayout>
  );
}
