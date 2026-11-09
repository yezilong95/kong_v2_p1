from __future__ import annotations

import argparse
import logging
import os
import posixpath
import sys
import time
from pathlib import Path

try:
    import tomllib
except ImportError:  # Python 3.10
    import tomli as tomllib  # type: ignore[no-redef]

try:
    import paramiko
except ImportError:
    paramiko = None  # type: ignore[assignment]

LOG = logging.getLogger("upload_apk")


def load_cfg(path: Path) -> dict:
    if not path.is_file():
        raise SystemExit(f"配置文件不存在: {path}")
    with path.open("rb") as f:
        return tomllib.load(f)


def _auth_section(cfg: dict) -> dict:
    return dict(cfg.get("sftp") or cfg.get("ftp") or {})


def resolve_password(cfg: dict) -> str:
    env = os.environ.get("SFTP_PASSWORD", "").strip() or os.environ.get("FTP_PASSWORD", "").strip()
    if env:
        return env
    cfg_pw = str(_auth_section(cfg).get("password") or "").strip()
    if cfg_pw:
        return cfg_pw
    raise SystemExit(
        "缺少 SFTP 密码：请在 config.toml 的 [sftp].password 填写，或设置环境变量 SFTP_PASSWORD"
    )


def pick_apk(folder: Path) -> Path | None:
    apks = [p for p in folder.glob("*.apk") if p.is_file()]
    if not apks:
        return None
    apks.sort(key=lambda p: p.stat().st_mtime)
    if len(apks) > 1:
        LOG.warning("多个 apk，只传最新: %s", apks[-1].name)
    return apks[-1]


def assert_safe_wipe_dir(folder: Path) -> Path:
    resolved = folder.resolve()
    if resolved == resolved.anchor or resolved.parent == resolved:
        raise RuntimeError(f"拒绝清空盘符根目录: {resolved}")
    return resolved


def wipe_dir(folder: Path) -> None:
    target = assert_safe_wipe_dir(folder)
    n = 0
    for p in target.iterdir():
        if p.is_file() or p.is_symlink():
            p.unlink()
            n += 1
            continue
        if p.is_dir():
            for q in sorted(p.rglob("*"), reverse=True):
                if q.is_file() or q.is_symlink():
                    q.unlink()
                elif q.is_dir():
                    q.rmdir()
            p.rmdir()
            n += 1
    LOG.info("已清空 %s，删除 %d 项", target, n)


def _put_progress(total: int):
    last_t = [0.0]
    last_n = [-1]

    def cb(sent: int, _total: int) -> None:
        now = time.monotonic()
        if sent == last_n[0]:
            return
        if sent < total and now - last_t[0] < 2:
            return
        last_t[0] = now
        last_n[0] = sent
        pct = (sent / total * 100) if total else 100.0
        LOG.info("上传中 %.1f%% (%d/%d)", pct, sent, total)

    return cb


def sftp_upload(cfg: dict, local: Path) -> None:
    if paramiko is None:
        raise SystemExit("缺少 paramiko，请先执行: pip install paramiko")

    remote = cfg["remote"]
    auth = _auth_section(cfg)
    user = str(auth.get("user") or "").strip()
    if not user:
        raise SystemExit("缺少 SFTP 用户：请填写 [sftp].user")
    password = resolve_password(cfg)

    dest = str(remote["filename"])
    tmp = dest + str(cfg["upload"]["tmp_suffix"])
    remote_dir = str(remote["dir"]).rstrip("/") or "/"
    dest_path = posixpath.join(remote_dir, dest)
    tmp_path = posixpath.join(remote_dir, tmp)
    retries = int(cfg["upload"]["retries"])
    delay = int(cfg["upload"]["retry_delay_sec"])
    timeout = int(remote.get("timeout") or 180)
    host = str(remote["host"])
    port = int(remote.get("port") or 22)

    last_err: Exception | None = None
    for i in range(1, retries + 1):
        transport = None
        sftp = None
        try:
            size = local.stat().st_size
            transport = paramiko.Transport((host, port))
            transport.banner_timeout = timeout
            transport.default_window_size = 2 * 1024 * 1024
            transport.default_max_packet_size = 32768
            transport.connect(username=user, password=password)
            transport.set_keepalive(15)
            sftp = paramiko.SFTPClient.from_transport(transport)
            if sftp is None:
                raise RuntimeError("无法创建 SFTPClient")
            channel = sftp.get_channel()
            if channel is not None:
                channel.settimeout(timeout)
            LOG.info("开始上传 %s (%d bytes) -> %s", local.name, size, tmp_path)
            sftp.put(str(local), tmp_path, callback=_put_progress(size), confirm=True)
            try:
                sftp.remove(dest_path)
            except FileNotFoundError:
                pass
            sftp.rename(tmp_path, dest_path)
            LOG.info(
                "上传成功 %s -> %s (%d bytes)",
                local.name,
                dest_path,
                local.stat().st_size,
            )
            return
        except Exception as e:
            last_err = e
            LOG.warning("第 %d/%d 次失败: %s", i, retries, e)
            if i < retries:
                time.sleep(delay)
        finally:
            if sftp is not None:
                sftp.close()
            if transport is not None:
                transport.close()
    raise RuntimeError(f"上传失败: {last_err}") from last_err


def run_once(cfg: dict) -> None:
    folder = Path(cfg["local"]["dir"])
    if not folder.is_dir():
        LOG.error("本地目录不存在: %s", folder)
        return
    apk = pick_apk(folder)
    if apk is None:
        LOG.info("无 apk，跳过")
        return
    sftp_upload(cfg, apk)
    wipe_dir(folder)


def main() -> None:
    parser = argparse.ArgumentParser(description="定时把本地 apk 覆盖上传为远端 app.apk（SFTP）")
    parser.add_argument(
        "--config",
        default=str(Path(__file__).with_name("config.toml")),
        help="toml 配置路径",
    )
    parser.add_argument(
        "--once",
        action="store_true",
        help="只跑一轮后退出",
    )
    args = parser.parse_args()

    logging.basicConfig(
        level=logging.INFO,
        format="%(asctime)s %(levelname)s %(message)s",
        handlers=[
            logging.FileHandler(Path(__file__).with_name("upload_apk.log"), encoding="utf-8"),
            logging.StreamHandler(sys.stdout),
        ],
    )
    logging.getLogger("paramiko").setLevel(logging.WARNING)

    cfg_path = Path(args.config)
    if args.once:
        run_once(load_cfg(cfg_path))
        return

    LOG.info("常驻启动，配置 %s", cfg_path)
    while True:
        cfg = load_cfg(cfg_path)
        interval = max(1, int(cfg["schedule"]["interval_minutes"]))
        try:
            run_once(cfg)
        except Exception:
            LOG.exception("本轮失败，文件保留，下轮重试")
        LOG.info("休眠 %d 分钟", interval)
        time.sleep(interval * 60)


if __name__ == "__main__":
    main()
