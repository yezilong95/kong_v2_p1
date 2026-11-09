"""兼容垫片：静态文件服务器（多线程 + HTTP/1.1，支持视频 Range 流式加载）。

背景：
1. 沙箱预览守护进程用 exec 形式运行 `python3 -m http.server ${DEPLOY_RUN_PORT} ...`，参数不经
   shell 展开，端口字面量 `${DEPLOY_RUN_PORT}` 会被当成非法端口，标准库直接报错退出。
2. 标准库 `python3 -m http.server` 默认是单线程 + HTTP/1.0：单线程会被多个视频的长连接占住，
   导致图片排队、视频黑屏；HTTP/1.0 不返回 206 Range，视频无法流式/拖动加载。

本垫片因 cwd 在 sys.path 最前而被优先加载：
- 忽略非法的命令行端口，改从环境变量 DEPLOY_RUN_PORT 读取端口；
- 用标准库 SimpleHTTPRequestHandler 从项目根目录提供静态服务，但显式启用
  ThreadingHTTPServer（多线程并发）与 HTTP/1.1（长连接 + Range 206 视频支持）。
"""

import importlib.util
import json
import os
import sys
from urllib.parse import parse_qs, urlparse


def _load_stdlib_http_server():
    """绕过同名包遮蔽，加载 Python 标准库的 http.server。"""
    here = os.path.dirname(os.path.abspath(__file__))
    proj = os.path.dirname(here)
    cwd = os.path.abspath(os.getcwd())
    blocked = {os.path.abspath(p) for p in (here, proj, cwd)}
    sys.path[:] = [p for p in sys.path if os.path.abspath(p or os.getcwd()) not in blocked]
    for name in [m for m in list(sys.modules) if m == "http" or m.startswith("http.")]:
        del sys.modules[name]
    import http.server as stdlib_http_server  # noqa: E402
    sys.path.insert(0, proj)
    return stdlib_http_server


def _resolve_port():
    raw = os.environ.get("DEPLOY_RUN_PORT", "").strip()
    if raw.isdigit():
        return int(raw)
    for arg in sys.argv[1:]:
        if arg.isdigit():
            return int(arg)
    return 5000


def _load_statslib():
    path = os.path.join(os.path.dirname(os.path.abspath(__file__)), "statslib.py")
    spec = importlib.util.spec_from_file_location("neonchat_statslib", path)
    mod = importlib.util.module_from_spec(spec)
    spec.loader.exec_module(mod)
    return mod


def _qs_first(qs, key):
    vals = qs.get(key) or []
    return vals[0] if vals else ""


def main():
    stdlib = _load_stdlib_http_server()
    project_root = os.path.dirname(os.path.dirname(os.path.abspath(__file__)))
    stats = _load_statslib()
    port = _resolve_port()
    bind = "0.0.0.0"

    class Handler(stdlib.SimpleHTTPRequestHandler):
        protocol_version = "HTTP/1.1"

        def __init__(self, *args, **kwargs):
            kwargs["directory"] = project_root
            super().__init__(*args, **kwargs)

        def _json(self, code, payload):
            raw = json.dumps(payload, ensure_ascii=False).encode("utf-8")
            self.send_response(code)
            self.send_header("Content-Type", "application/json; charset=utf-8")
            self.send_header("Cache-Control", "no-store")
            self.send_header("Content-Length", str(len(raw)))
            self.end_headers()
            self.wfile.write(raw)

        def _read_json_body(self):
            length = int(self.headers.get("Content-Length") or 0)
            raw = self.rfile.read(length) if length > 0 else b""
            if not raw:
                return {}
            try:
                data = json.loads(raw.decode("utf-8", "replace"))
            except json.JSONDecodeError:
                return {}
            return data if isinstance(data, dict) else {}

        def _header_map(self):
            return {k: v for k, v in self.headers.items()}

        def _peer_ip(self):
            return self.client_address[0] if self.client_address else ""

        def _handle_down(self):
            parsed = urlparse(self.path)
            qs = parse_qs(parsed.query)
            target = stats.safe_redirect(_qs_first(qs, "to")) or stats.safe_redirect(
                stats.default_apk(project_root)
            )
            if not target:
                self._json(400, {"ok": 0, "error": "missing apk url"})
                return
            stats.append_event(
                project_root,
                {
                    "type": "download",
                    "sid": _qs_first(qs, "sid"),
                    "fbclid": _qs_first(qs, "fbclid"),
                    "source_ad": _qs_first(qs, "source_ad"),
                    "utm_campaign": _qs_first(qs, "utm_campaign"),
                    "utm_source": _qs_first(qs, "utm_source"),
                    "ad_id": _qs_first(qs, "ad_id"),
                    "referrer": self.headers.get("Referer") or "",
                },
                self._header_map(),
                self._peer_ip(),
            )
            self.send_response(302)
            self.send_header("Location", target)
            self.send_header("Cache-Control", "no-store")
            self.send_header("Content-Length", "0")
            self.end_headers()

        def do_GET(self):
            path = urlparse(self.path).path
            if path.startswith("/data/") or path in ("/data", "/data/"):
                self.send_error(403, "Forbidden")
                return
            if path in ("/stats-lib.php", "/http/statslib.py", "/http/server.py"):
                self.send_error(403, "Forbidden")
                return
            if path in ("/down.php", "/down"):
                self._handle_down()
                return
            if path in ("/stats-api.php", "/stats-api"):
                rows = stats.read_rows(project_root)
                self._json(200, {
                    "ok": 1,
                    "today": stats.today_str(),
                    "kpi": stats.kpi_from(rows),
                    "rows": rows,
                })
                return
            if path in ("/track.php", "/track"):
                self._json(405, {"ok": 0})
                return
            super().do_GET()

        def do_POST(self):
            path = urlparse(self.path).path
            if path in ("/track.php", "/track"):
                body = self._read_json_body()
                body["type"] = "visit"
                stats.append_event(project_root, body, self._header_map(), self._peer_ip())
                self._json(200, {"ok": 1})
                return
            if path in ("/stats-api.php", "/stats-api"):
                body = self._read_json_body()
                if body.get("action") == "delete":
                    ok = stats.delete_id(project_root, str(body.get("id") or ""))
                    self._json(200, {"ok": 1 if ok else 0})
                    return
                self._json(400, {"ok": 0})
                return
            self.send_error(404, "Not found")

    server_cls = stdlib.ThreadingHTTPServer
    server_cls.allow_reuse_address = True
    server_cls.daemon_threads = True
    with server_cls((bind, port), Handler) as httpd:
        print(
            f"[http shim] ThreadingHTTPServer HTTP/1.1 on {bind}:{port} (dir: {project_root})",
            flush=True,
        )
        try:
            httpd.serve_forever()
        except KeyboardInterrupt:
            pass


if __name__ == "__main__":
    main()
