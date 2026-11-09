import { Navigate, useLocation } from "react-router-dom";
import { useAuth } from "../auth/AuthProvider";
import { useI18n, t } from "../i18n";


export function RequireAuth({ children }: { children: React.ReactNode }) {
  useI18n();
  const { authReady, isAuthed } = useAuth();
  const location = useLocation();

  if (!authReady) {
    return <div className="app-loading">{t("加载中…")}</div>;
  }

  if (!isAuthed) {
    const redirect = encodeURIComponent(`${location.pathname}${location.search}`);
    return <Navigate replace to={`/login?redirect=${redirect}`} />;
  }

  return children;
}
