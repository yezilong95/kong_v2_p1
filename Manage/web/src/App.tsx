import { ConfigProvider, theme } from "antd";
import idID from "antd/locale/id_ID";
import zhCN from "antd/locale/zh_CN";
import { RouterProvider } from "react-router-dom";
import { AuthProvider } from "./auth/AuthProvider";
import { I18nProvider, useI18n } from "./i18n";
import { router } from "./router";
import "./styles/app.css";

const hudTheme = {
  algorithm: theme.darkAlgorithm,
  token: {
    colorPrimary: "#00e5ff",
    colorInfo: "#00e5ff",
    colorSuccess: "#22c55e",
    colorError: "#ff3333",
    colorWarning: "#f5a623",
    colorBgBase: "#040a12",
    colorBgContainer: "#0a1420",
    colorBgElevated: "#122030",
    colorBorder: "rgba(0,229,255,0.22)",
    colorBorderSecondary: "rgba(0,229,255,0.12)",
    colorText: "#d7f7ff",
    colorTextSecondary: "#6a9aab",
    colorTextTertiary: "#4d7585",
    borderRadius: 8,
    fontFamily: `"Fira Sans", system-ui, -apple-system, sans-serif`,
  },
  components: {
    Layout: {
      siderBg: "#060e18",
      headerBg: "rgba(10,20,32,0.92)",
      bodyBg: "#040a12",
      triggerBg: "#0a1420",
    },
    Menu: {
      darkItemBg: "transparent",
      darkItemSelectedBg: "rgba(0,229,255,0.12)",
      darkItemSelectedColor: "#00e5ff",
      darkItemHoverBg: "rgba(0,229,255,0.08)",
      darkItemColor: "#6a9aab",
    },
    Card: {
      colorBgContainer: "#0a1420",
    },
    Table: {
      headerBg: "#081018",
      rowHoverBg: "rgba(0,229,255,0.06)",
    },
    Modal: {
      contentBg: "#0a1420",
      headerBg: "#0a1420",
    },
  },
};

function ThemedApp() {
  const { locale } = useI18n();
  return (
    <ConfigProvider locale={locale === "zh-CN" ? zhCN : idID} theme={hudTheme}>
      <AuthProvider>
        <RouterProvider router={router} />
      </AuthProvider>
    </ConfigProvider>
  );
}

export function App() {
  return (
    <I18nProvider>
      <ThemedApp />
    </I18nProvider>
  );
}
