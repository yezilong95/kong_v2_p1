"""访问/下载事件：jsonl 落盘（Python http 垫片）。"""

from __future__ import annotations

import json
import os
import re
import uuid
from datetime import datetime, timedelta, timezone
from typing import Any
from urllib.error import URLError
from urllib.parse import urlparse
from urllib.request import Request, urlopen

TZ = timezone(timedelta(hours=8))
LOG_NAME = ".visits.jsonl"
GEO_CACHE_NAME = ".geo-cache.json"


def now_str() -> str:
    return datetime.now(TZ).strftime("%Y-%m-%d %H:%M:%S")


def today_str() -> str:
    return datetime.now(TZ).strftime("%Y-%m-%d")


def data_dir(root: str) -> str:
    path = os.path.join(root, "data")
    os.makedirs(path, exist_ok=True)
    return path


def log_path(root: str) -> str:
    return os.path.join(data_dir(root), LOG_NAME)


def geo_cache_path(root: str) -> str:
    return os.path.join(data_dir(root), GEO_CACHE_NAME)


def _flock_ex(fh) -> None:
    try:
        import fcntl

        fcntl.flock(fh.fileno(), fcntl.LOCK_EX)
    except (ImportError, OSError):
        pass


def _flock_un(fh) -> None:
    try:
        import fcntl

        fcntl.flock(fh.fileno(), fcntl.LOCK_UN)
    except (ImportError, OSError):
        pass


def client_ip(headers: dict[str, str], fallback: str = "") -> str:
    for key in ("CF-Connecting-IP", "X-Real-IP", "X-Forwarded-For"):
        raw = headers.get(key) or headers.get(key.lower()) or ""
        if not raw:
            continue
        ip = raw.split(",")[0].strip()
        if ip:
            return ip
    return fallback or ""


def parse_ua(ua: str) -> tuple[str, str, str]:
    device = "Mobile" if re.search(r"Mobile|Android|iPhone|iPad", ua, re.I) else "Desktop"
    if re.search(r"iPad|Tablet", ua, re.I):
        device = "Tablet"
    os_name = "Unknown"
    if re.search(r"Android", ua, re.I):
        os_name = "Android"
    elif re.search(r"iPhone|iPad", ua, re.I):
        os_name = "iOS"
    elif re.search(r"Windows", ua, re.I):
        os_name = "Windows"
    elif re.search(r"Mac OS X", ua, re.I):
        os_name = "macOS"
    elif re.search(r"Linux", ua, re.I):
        os_name = "Linux"
    browser = "Unknown"
    if re.search(r"MicroMessenger", ua, re.I):
        browser = "WeChat"
    elif re.search(r"FBAN|FBAV|\[FB|Facebook", ua, re.I):
        browser = "Facebook"
    elif re.search(r"Instagram", ua, re.I):
        browser = "Instagram"
    elif re.search(r"QQ/", ua):
        browser = "QQ"
    elif re.search(r"Edg/", ua):
        browser = "Edge"
    elif re.search(r"OPR/|Opera", ua, re.I):
        browser = "Opera"
    elif re.search(r"Chrome", ua, re.I):
        browser = "Chrome"
    elif re.search(r"Safari", ua, re.I):
        browser = "Safari"
    elif re.search(r"Firefox", ua, re.I):
        browser = "Firefox"
    return device, os_name, browser


def _is_public_ip(ip: str) -> bool:
    if not ip or ip in ("127.0.0.1", "::1"):
        return False
    if ip.startswith("10.") or ip.startswith("192.168.") or ip.startswith("127."):
        return False
    if re.match(r"^172\.(1[6-9]|2\d|3[0-1])\.", ip):
        return False
    return True


def lookup_geo(root: str, ip: str) -> tuple[str, str, str]:
    empty = ("", "", "")
    if not _is_public_ip(ip):
        return empty
    cache_file = geo_cache_path(root)
    cache: dict[str, Any] = {}
    if os.path.isfile(cache_file):
        try:
            with open(cache_file, encoding="utf-8") as fh:
                cache = json.load(fh) or {}
        except (OSError, json.JSONDecodeError):
            cache = {}
    hit = cache.get(ip)
    if isinstance(hit, dict) and "country" in hit:
        return str(hit.get("country") or ""), str(hit.get("city") or ""), str(hit.get("isp") or "")
    country = city = isp = ""
    try:
        req = Request(
            "http://ip-api.com/json/" + ip + "?fields=status,country,city,isp",
            headers={"User-Agent": "neonchat-stats/1"},
        )
        with urlopen(req, timeout=2) as resp:
            data = json.loads(resp.read().decode("utf-8", "replace"))
        if data.get("status") == "success":
            country = str(data.get("country") or "")
            city = str(data.get("city") or "")
            isp = str(data.get("isp") or "")
    except (URLError, TimeoutError, json.JSONDecodeError, OSError, ValueError):
        pass
    cache[ip] = {"country": country, "city": city, "isp": isp}
    try:
        with open(cache_file, "w", encoding="utf-8") as fh:
            json.dump(cache, fh, ensure_ascii=False)
    except OSError:
        pass
    return country, city, isp


def _clip(value: Any, n: int) -> str:
    return str(value or "")[:n]


def safe_redirect(url: str) -> str:
    url = (url or "").strip()
    parsed = urlparse(url)
    if parsed.scheme not in ("http", "https") or not parsed.netloc or parsed.username:
        return ""
    return url


def default_apk(root: str) -> str:
    path = os.path.join(root, "index.html")
    try:
        with open(path, encoding="utf-8") as fh:
            text = fh.read(8000)
    except OSError:
        return ""
    match = re.search(r'"apk"\s*:\s*"([^"]+)"', text)
    return match.group(1) if match else ""


def append_event(root: str, body: dict[str, Any], headers: dict[str, str], peer_ip: str) -> dict[str, Any]:
    event_type = "download" if body.get("type") == "download" else "visit"
    sid = re.sub(r"[^a-zA-Z0-9\-]", "", str(body.get("sid") or ""))[:36]
    if not sid:
        sid = uuid.uuid4().hex
    ua = _clip(headers.get("User-Agent") or headers.get("user-agent") or "", 512)
    ip = client_ip(headers, peer_ip)
    device, os_name, browser = parse_ua(ua)
    country, city, isp = lookup_geo(root, ip)
    row = {
        "id": uuid.uuid4().hex[:16],
        "t": now_str(),
        "type": event_type,
        "sid": sid,
        "ip": ip,
        "country": country,
        "city": city,
        "isp": isp,
        "device": device,
        "os": os_name,
        "browser": browser,
        "ua": ua,
        "fbclid": _clip(body.get("fbclid"), 255),
        "source_ad": _clip(body.get("source_ad"), 128),
        "utm_campaign": _clip(body.get("utm_campaign"), 128),
        "utm_source": _clip(body.get("utm_source"), 64),
        "ad_id": _clip(body.get("ad_id"), 64),
        "referrer": _clip(body.get("referrer"), 512),
    }
    path = log_path(root)
    line = json.dumps(row, ensure_ascii=False) + "\n"
    with open(path, "a", encoding="utf-8") as fh:
        _flock_ex(fh)
        try:
            fh.write(line)
            fh.flush()
        finally:
            _flock_un(fh)
    return row


def read_rows(root: str, limit: int = 2000) -> list[dict[str, Any]]:
    path = log_path(root)
    if not os.path.isfile(path):
        return []
    rows: list[dict[str, Any]] = []
    with open(path, encoding="utf-8") as fh:
        lines = fh.read().splitlines()
    for line in lines[-limit:]:
        line = line.strip()
        if not line:
            continue
        try:
            item = json.loads(line)
        except json.JSONDecodeError:
            continue
        if isinstance(item, dict):
            rows.append(item)
    rows.reverse()
    return rows


def kpi_from(rows: list[dict[str, Any]]) -> dict[str, Any]:
    today = today_str()
    visit_today = dl_today = visit_all = dl_all = 0
    for row in rows:
        kind = row.get("type")
        is_today = str(row.get("t") or "").startswith(today)
        if kind == "download":
            dl_all += 1
            if is_today:
                dl_today += 1
        else:
            visit_all += 1
            if is_today:
                visit_today += 1
    rate = "{:.2f}%".format(dl_today / visit_today * 100) if visit_today else "0%"
    return {
        "visit_today": visit_today,
        "dl_today": dl_today,
        "rate_today": rate,
        "visit_all": visit_all,
        "dl_all": dl_all,
    }


def delete_id(root: str, event_id: str) -> bool:
    event_id = str(event_id or "")
    if not event_id:
        return False
    path = log_path(root)
    if not os.path.isfile(path):
        return False
    kept: list[str] = []
    found = False
    with open(path, "r+", encoding="utf-8") as fh:
        _flock_ex(fh)
        try:
            for line in fh.read().splitlines():
                raw = line.strip()
                if not raw:
                    continue
                try:
                    item = json.loads(raw)
                except json.JSONDecodeError:
                    kept.append(raw + "\n")
                    continue
                if isinstance(item, dict) and str(item.get("id")) == event_id:
                    found = True
                    continue
                kept.append(json.dumps(item, ensure_ascii=False) + "\n")
            fh.seek(0)
            fh.truncate()
            fh.writelines(kept)
            fh.flush()
        finally:
            _flock_un(fh)
    return found
