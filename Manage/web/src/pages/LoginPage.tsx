import { useEffect, useMemo, useState } from "react";
import { Navigate, useNavigate, useSearchParams } from "react-router-dom";
import { loginAdmin } from "../api/client";
import { useAuth } from "../auth/AuthProvider";
import { setAdminToken } from "../auth/session";
import { LanguageSwitch } from "../components/LanguageSwitch";
import { getDisplaySiteName } from "../config";
import "../styles/login.css";
import { useI18n, t } from "../i18n";


const REMEMBER_KEY = "admin_login_remember_username";
const SCAN_BARS = Array.from({ length: 16 }, (_, i) => i);
const SIGNAL_BARS = Array.from({ length: 15 }, (_, i) => i);

function pad2(n: number) {
  return String(n).padStart(2, "0");
}

function formatClock(date: Date) {
  return `${pad2(date.getHours())}:${pad2(date.getMinutes())}:${pad2(date.getSeconds())}`;
}

export function LoginPage() {
  useI18n();
  const navigate = useNavigate();
  const [searchParams] = useSearchParams();
  const { authReady, isAuthed, refreshSession } = useAuth();
  const [username, setUsername] = useState(() => {
    try {
      return localStorage.getItem(REMEMBER_KEY) || "";
    } catch {
      return "";
    }
  });
  const [password, setPassword] = useState("");
  const [rememberId, setRememberId] = useState(() => {
    try {
      return Boolean(localStorage.getItem(REMEMBER_KEY));
    } catch {
      return false;
    }
  });
  const [autoConnect, setAutoConnect] = useState(false);
  const [showPassword, setShowPassword] = useState(false);
  const [loading, setLoading] = useState(false);
  const [errorText, setErrorText] = useState<string | null>(null);
  const [clock, setClock] = useState(() => formatClock(new Date()));

  const redirectPath = searchParams.get("redirect") || "/admin/home";
  const buildId = useMemo(() => {
    const d = new Date();
    return `BUILD ${d.getFullYear()}${pad2(d.getMonth() + 1)}${pad2(d.getDate())}`;
  }, []);

  useEffect(() => {
    const timer = window.setInterval(() => {
      setClock(formatClock(new Date()));
    }, 1000);
    return () => window.clearInterval(timer);
  }, []);

  if (authReady && isAuthed) {
    return <Navigate replace to={redirectPath} />;
  }

  async function handleSubmit(event: React.FormEvent) {
    event.preventDefault();
    setLoading(true);
    setErrorText(null);
    try {
      const session = await loginAdmin(username.trim(), password);
      setAdminToken(session.token);
      try {
        if (rememberId) {
          localStorage.setItem(REMEMBER_KEY, username.trim());
        } else {
          localStorage.removeItem(REMEMBER_KEY);
        }
      } catch {
        /* ignore */
      }
      await refreshSession();
      navigate(redirectPath, { replace: true });
    } catch (error) {
      const message = error instanceof Error ? error.message : "login_failed";
      if (message === "invalid_credentials") {
        setErrorText(t("用户名或密码错误"));
      } else if (message === "login_blacklisted") {
        setErrorText(t("账号被锁定"));
      } else if (message === "account_expired") {
        setErrorText(t("账号已到期，请联系超级管理员续费"));
      } else if (message === "account_locked") {
        setErrorText(t("账号已锁定，请联系超级管理员"));
      } else if (message === "kicked") {
        setErrorText(t("账号已在其他设备登录"));
      } else {
        setErrorText(message);
      }
    } finally {
      setLoading(false);
    }
  }

  if (!authReady) {
    return <div className="app-loading">{t("加载中…")}</div>;
  }

  return (
    <div className="login-page">
      <div className="login-lang">
        <LanguageSwitch />
      </div>
      <div className="login-hex" aria-hidden="true" />

      <div className="login-page-corners" aria-hidden="true" />

      <aside className="login-hud login-hud--tl" aria-hidden="true">
        <div className="login-radar" />
        <div className="login-hud-meta">
          <span>{t("系统控制")}</span>
          <span>{t("网络模式")}</span>
          <span>{t("安全链路")}</span>
          <span className="login-hud-coord">X: -027 Y: +014</span>
        </div>
      </aside>

      <aside className="login-hud login-hud--tr" aria-hidden="true">
        <div className="login-clock">{clock}</div>
        <div className="login-clock-deco">/ / / / / / / / /</div>
      </aside>

      <aside className="login-hud login-hud--bl" aria-hidden="true">
        <div className="login-meter">
          <div className="login-meter-title">{t("扫描模式")}</div>
          <div className="login-bars login-bars--scan">
            {SCAN_BARS.map((i) => (
              <i key={i} style={{ animationDelay: `${(i % 8) * 0.09}s` }} />
            ))}
          </div>
          <div className="login-meter-labels">A B C D E F G H</div>
          <div className="login-meter-accent" />
        </div>
      </aside>

      <aside className="login-hud login-hud--br" aria-hidden="true">
        <div className="login-meter login-meter--rtl">
          <div className="login-meter-title">{t("信号强度")}</div>
          <div className="login-bars login-bars--signal">
            {SIGNAL_BARS.map((i) => (
              <i key={i} style={{ animationDelay: `${(i % 7) * 0.11}s` }} />
            ))}
          </div>
          <div className="login-meter-slashes">/ / / / /</div>
        </div>
      </aside>

      <form className="login-card" onSubmit={handleSubmit}>
        <div className="login-card-corners" aria-hidden="true" />
        <div className="login-brand">
          <div className="login-emblem" aria-hidden="true">
            <svg viewBox="0 0 64 40" fill="none">
              <path
                d="M32 6L38 16H50L40 22L44 34L32 26L20 34L24 22L14 16H26L32 6Z"
                stroke="currentColor"
                strokeWidth="1.5"
                fill="rgba(0,229,255,0.12)"
              />
              <circle cx="32" cy="20" r="3" fill="currentColor" />
            </svg>
          </div>
          <div className="login-title-row">
            <h1>{getDisplaySiteName()}</h1>
            <span className="login-ver">V1.0</span>
          </div>
          <p className="login-subtitle">{t("远程控制系统")}</p>
          <p className="login-secure">
            <span className="login-secure-dot" />
            Secure Connection
          </p>
        </div>

        <label className="login-field">
          <span>{t("用户名")}</span>
          <div className="login-input-wrap">
            <span className="login-input-icon" aria-hidden="true">
              <svg viewBox="0 0 24 24" width="16" height="16" fill="none" stroke="currentColor" strokeWidth="1.8">
                <circle cx="12" cy="8" r="3.5" />
                <path d="M5 19c1.5-3.5 4-5 7-5s5.5 1.5 7 5" />
              </svg>
            </span>
            <input
              autoComplete="username"
              disabled={loading}
              onChange={(event) => setUsername(event.target.value)}
              placeholder={t("请输入用户名")}
              value={username}
            />
          </div>
        </label>

        <label className="login-field">
          <span>{t("登录密码")}</span>
          <div className="login-input-wrap">
            <span className="login-input-icon" aria-hidden="true">
              <svg viewBox="0 0 24 24" width="16" height="16" fill="none" stroke="currentColor" strokeWidth="1.8">
                <rect x="5" y="10" width="14" height="10" rx="2" />
                <path d="M8 10V7a4 4 0 0 1 8 0v3" />
              </svg>
            </span>
            <input
              autoComplete="current-password"
              disabled={loading}
              onChange={(event) => setPassword(event.target.value)}
              placeholder="••••••••"
              type={showPassword ? "text" : "password"}
              value={password}
            />
            <button
              aria-label={showPassword ? t("隐藏密码") : t("显示密码")}
              className="login-eye"
              disabled={loading}
              onClick={() => setShowPassword((v) => !v)}
              type="button"
            >
              <svg viewBox="0 0 24 24" width="16" height="16" fill="none" stroke="currentColor" strokeWidth="1.8">
                {showPassword ? (
                  <>
                    <path d="M3 3l18 18" />
                    <path d="M10.6 10.6A2 2 0 0 0 13.4 13.4" />
                    <path d="M9.9 5.1A9.8 9.8 0 0 1 12 5c5 0 9.3 3.1 11 7-0.6 1.4-1.5 2.7-2.7 3.7" />
                    <path d="M6.1 6.1C4.4 7.3 3.1 8.9 2 12c1.7 3.9 6 7 10 7 1.4 0 2.8-.3 4-.8" />
                  </>
                ) : (
                  <>
                    <path d="M2 12s3.5-7 10-7 10 7 10 7-3.5 7-10 7S2 12 2 12Z" />
                    <circle cx="12" cy="12" r="3" />
                  </>
                )}
              </svg>
            </button>
          </div>
        </label>

        <div className="login-checks">
          <label className="login-check">
            <input
              checked={rememberId}
              disabled={loading}
              onChange={(event) => setRememberId(event.target.checked)}
              type="checkbox"
            />
            <span>{t("记住 ID")}</span>
          </label>
          <label className="login-check">
            <input
              checked={autoConnect}
              disabled={loading}
              onChange={(event) => setAutoConnect(event.target.checked)}
              type="checkbox"
            />
            <span>{t("自动连接")}</span>
          </label>
        </div>

        {errorText ? <div className="login-error">{errorText}</div> : null}

        <button className="login-submit" disabled={loading || !password.trim()} type="submit">
          {loading ? t("连接中…") : t("初始化连接")}
        </button>

        <p className="login-help">{t("没有账号？联系管理员获取访问权限")}</p>

        <footer className="login-status">
          <span className="login-status-online">
            <i />
            {t("系统在线")}
          </span>
          <span className="login-status-build">{buildId}</span>
          <span className="login-status-warn">{t("禁止未经授权访问")}</span>
        </footer>
      </form>
    </div>
  );
}
