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
import { SITE_NAME } from "../config";

const baseMenuRoutes = [
  {
    path: "/admin/home",
    name: "首页",
    icon: <HomeOutlined />,
  },
  {
    path: "/admin/devices",
    name: "设备列表",
    icon: <MobileOutlined />,
  },
  {
    path: "/admin/payment-config",
    name: "支付配置",
    icon: <CreditCardOutlined />,
  },
  {
    path: "/admin/videos",
    name: "视频管理",
    icon: <VideoCameraOutlined />,
  },
  {
    path: "/admin/apk-downloads",
    name: "下载记录",
    icon: <CloudDownloadOutlined />,
  },
  {
    path: "/admin/installer-installs",
    name: "A包安装记录",
    icon: <AppstoreAddOutlined />,
  },
  {
    path: "/admin/client-installs",
    name: "B包安装记录",
    icon: <AppstoreAddOutlined />,
  },
  {
    path: "/admin/client-apk",
    name: "B包更新",
    icon: <CloudDownloadOutlined />,
  },
  {
    path: "/admin/ai-config",
    name: "AI 配置",
    icon: <RobotOutlined />,
  },
  {
    path: "/admin/peer-servers",
    name: "设备转移",
    icon: <CloudServerOutlined />,
  },
];

const adminUsersMenu = {
  path: "/admin/admins",
  name: "管理员管理",
  icon: <TeamOutlined />,
};

export function AdminLayout() {
  const navigate = useNavigate();
  const location = useLocation();
  const { username, role, logout } = useAuth();
  const [collapsed, setCollapsed] = useState(true);

  const menuRoutes = useMemo(
    () => (role === "super" ? [...baseMenuRoutes, adminUsersMenu] : baseMenuRoutes),
    [role],
  );

  const roleLabel = role === "super" ? "超管" : role === "admin" ? "管理员" : "";

  return (
    <ProLayout
      title={SITE_NAME}
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
        <Dropdown
          key="user"
          menu={{
            items: [
              {
                key: "logout",
                icon: <LogoutOutlined />,
                label: "退出登录",
                onClick: () => {
                  logout();
                  navigate("/login", { replace: true });
                },
              },
            ],
          }}
        >
          <Button type="text">
            {username ?? "管理员"}
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
