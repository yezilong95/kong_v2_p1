import {
  createContext,
  useCallback,
  useContext,
  useEffect,
  useMemo,
  useState,
  type ReactNode,
} from "react";
import { fetchAdminSession } from "../api/client";
import { clearAdminToken, getAdminToken } from "./session";

export type AdminRole = "super" | "admin";

interface AuthContextValue {
  authReady: boolean;
  isAuthed: boolean;
  username: string | null;
  role: AdminRole | null;
  accountExpiresAt: string | null;
  refreshSession: () => Promise<boolean>;
  logout: () => void;
}

const AuthContext = createContext<AuthContextValue | null>(null);

export function AuthProvider({ children }: { children: ReactNode }) {
  const [authReady, setAuthReady] = useState(false);
  const [isAuthed, setIsAuthed] = useState(false);
  const [username, setUsername] = useState<string | null>(null);
  const [role, setRole] = useState<AdminRole | null>(null);
  const [accountExpiresAt, setAccountExpiresAt] = useState<string | null>(null);

  const refreshSession = useCallback(async () => {
    const token = getAdminToken();
    if (!token) {
      setIsAuthed(false);
      setUsername(null);
      setRole(null);
      setAccountExpiresAt(null);
      setAuthReady(true);
      return false;
    }

    try {
      const session = await fetchAdminSession();
      setIsAuthed(true);
      setUsername(session.username);
      setRole(session.role);
      setAccountExpiresAt(session.accountExpiresAt);
      setAuthReady(true);
      return true;
    } catch {
      clearAdminToken();
      setIsAuthed(false);
      setUsername(null);
      setRole(null);
      setAccountExpiresAt(null);
      setAuthReady(true);
      return false;
    }
  }, []);

  const logout = useCallback(() => {
    clearAdminToken();
    setIsAuthed(false);
    setUsername(null);
    setRole(null);
    setAccountExpiresAt(null);
  }, []);

  useEffect(() => {
    void refreshSession();
  }, [refreshSession]);

  const value = useMemo(
    () => ({
      authReady,
      isAuthed,
      username,
      role,
      accountExpiresAt,
      refreshSession,
      logout,
    }),
    [accountExpiresAt, authReady, isAuthed, logout, refreshSession, role, username],
  );

  return <AuthContext.Provider value={value}>{children}</AuthContext.Provider>;
}

export function useAuth() {
  const context = useContext(AuthContext);
  if (!context) {
    throw new Error("useAuth must be used within AuthProvider");
  }
  return context;
}
