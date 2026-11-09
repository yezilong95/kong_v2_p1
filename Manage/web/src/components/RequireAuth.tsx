import { Navigate, useLocation } from "react-router-dom";
import { useAuth } from "../auth/AuthProvider";

export function RequireAuth({ children }: { children: React.ReactNode }) {
  const { authReady, isAuthed } = useAuth();
  const location = useLocation();

  if (!authReady) {
    return <div className="app-loading">加载中…</div>;
  }

  if (!isAuthed) {
    const redirect = encodeURIComponent(`${location.pathname}${location.search}`);
    return <Navigate replace to={`/login?redirect=${redirect}`} />;
  }

  return children;
}
