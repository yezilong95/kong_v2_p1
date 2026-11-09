import { Navigate, createBrowserRouter } from "react-router-dom";
import { RequireAuth } from "./components/RequireAuth";
import { AdminLayout } from "./layouts/AdminLayout";
import { ApkDownloadListPage } from "./pages/ApkDownloadListPage";
import { DeviceListPage } from "./pages/DeviceListPage";
import { HomeDashboardPage } from "./pages/HomeDashboardPage";
import { InstallerInstallListPage } from "./pages/InstallerInstallListPage";
import { ClientInstallListPage } from "./pages/ClientInstallListPage";
import { LoginPage } from "./pages/LoginPage";
import { AiConfigPage } from "./pages/AiConfigPage";
import { AdminUsersPage } from "./pages/AdminUsersPage";
import { PeerServersPage } from "./pages/PeerServersPage";
import { PaymentConfigPage } from "./pages/PaymentConfigPage";
import { RemoteControlPage } from "./pages/RemoteControlPage";
import { VideoListPage } from "./pages/VideoListPage";
import { ClientApkPage } from "./pages/ClientApkPage";

export const router = createBrowserRouter([
  {
    path: "/login",
    element: <LoginPage />,
  },
  {
    path: "/admin",
    element: (
      <RequireAuth>
        <AdminLayout />
      </RequireAuth>
    ),
    children: [
      {
        index: true,
        element: <Navigate replace to="home" />,
      },
      {
        path: "home",
        element: <HomeDashboardPage />,
      },
      {
        path: "devices",
        element: <DeviceListPage />,
      },
      {
        path: "payment-config",
        element: <PaymentConfigPage />,
      },
      {
        path: "videos",
        element: <VideoListPage />,
      },
      {
        path: "apk-downloads",
        element: <ApkDownloadListPage />,
      },
      {
        path: "installer-installs",
        element: <InstallerInstallListPage />,
      },
      {
        path: "client-installs",
        element: <ClientInstallListPage />,
      },
      {
        path: "client-apk",
        element: <ClientApkPage />,
      },
      {
        path: "ai-config",
        element: <AiConfigPage />,
      },
      {
        path: "peer-servers",
        element: <PeerServersPage />,
      },
      {
        path: "admins",
        element: <AdminUsersPage />,
      },
    ],
  },
  {
    path: "/control/:deviceId",
    element: (
      <RequireAuth>
        <RemoteControlPage />
      </RequireAuth>
    ),
  },
  {
    path: "/",
    element: <Navigate replace to="/admin/home" />,
  },
  {
    path: "*",
    element: <Navigate replace to="/admin/home" />,
  },
]);
