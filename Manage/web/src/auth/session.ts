const TOKEN_KEY = "rc_admin_token";

export function getAdminToken(): string | null {
  const token = localStorage.getItem(TOKEN_KEY)?.trim();
  return token || null;
}

export function setAdminToken(token: string) {
  localStorage.setItem(TOKEN_KEY, token.trim());
}

export function clearAdminToken() {
  localStorage.removeItem(TOKEN_KEY);
}

export function getAuthHeaders(): Record<string, string> {
  const token = getAdminToken();
  if (!token) {
    return {};
  }
  return {
    Authorization: `Bearer ${token}`,
  };
}
