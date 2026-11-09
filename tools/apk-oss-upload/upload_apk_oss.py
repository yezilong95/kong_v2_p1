from __future__ import annotations

import argparse
import logging
import os
import sys
import time
from pathlib import Path

try:
    import tomllib
except ImportError:  # Python 3.10
    import tomli as tomllib  # type: ignore[no-redef]

try:
    import oss2
except ImportError:
    oss2 = None  # type: ignore[assignment]

LOG = logging.getLogger("upload_apk_oss")


def load_cfg(path: Path) -> dict:
    if not path.is_file():
        raise SystemExit(f"配置文件不存在: {path}")
    with path.open("rb") as f:
        return tomllib.load(f)


def resolve_ak(cfg: dict) -> tuple[str, str]:
    oss = cfg.get("oss") or {}
    ak = os.environ.get("OSS_ACCESS_KEY_ID", "").strip() or str(oss.get("access_key_id") or "").strip()
    sk = os.environ.get("OSS_ACCESS_KEY_SECRET", "").strip() or str(oss.get("access_key_secret") or "").strip()
    if not ak or not sk:
        raise SystemExit(
            "缺少 OSS 密钥：请在 config.toml 的 [oss].access_key_id / access_key_secret 填写，"
            "或设置环境变量 OSS_ACCESS_KEY_ID / OSS_ACCESS_KEY_SECRET"
        )
    return ak, sk


def object_keys(cfg: dict) -> tuple[str, str]:
    oss = cfg["oss"]
    prefix = str(oss.get("prefix") or "").strip().strip("/")
    filename = str(oss.get("filename") or "app.apk").lstrip("/")
    dest = f"{prefix}/{filename}" if prefix else filename
    tmp = dest + str(cfg["upload"]["tmp_suffix"])
    return dest, tmp


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
        size = total or _total or 0
        now = time.monotonic()
        if sent == last_n[0]:
            return
        if size and sent < size and now - last_t[0] < 2:
            return
        last_t[0] = now
        last_n[0] = sent
        pct = (sent / size * 100) if size else 100.0
        LOG.info("上传中 %.1f%% (%d/%d)", pct, sent, size)

    return cb


def oss_upload(cfg: dict, local: Path) -> None:
    if oss2 is None:
        raise SystemExit("缺少 oss2，请先执行: pip install oss2")

    oss = cfg["oss"]
    endpoint = str(oss.get("endpoint") or "").strip()
    bucket_name = str(oss.get("bucket") or "").strip()
    if not endpoint or not bucket_name:
        raise SystemExit("缺少 OSS endpoint 或 bucket")

    ak, sk = resolve_ak(cfg)
    dest_key, tmp_key = object_keys(cfg)
    retries = int(cfg["upload"]["retries"])
    delay = int(cfg["upload"]["retry_delay_sec"])
    timeout = int(oss.get("timeout") or 180)
    headers = {"Content-Type": "application/vnd.android.package-archive"}

    last_err: Exception | None = None
    for i in range(1, retries + 1):
        try:
            size = local.stat().st_size
            auth = oss2.Auth(ak, sk)
            bucket = oss2.Bucket(auth, endpoint, bucket_name, connect_timeout=timeout)
            LOG.info("开始上传 %s (%d bytes) -> oss://%s/%s", local.name, size, bucket_name, tmp_key)
            bucket.put_object_from_file(
                tmp_key,
                str(local),
                headers=headers,
                progress_callback=_put_progress(size),
            )
            bucket.copy_object(bucket_name, tmp_key, dest_key)
            bucket.delete_object(tmp_key)
            LOG.info("上传成功 %s -> oss://%s/%s (%d bytes)", local.name, bucket_name, dest_key, size)
            return
        except Exception as e:
            last_err = e
            LOG.warning("第 %d/%d 次失败: %s", i, retries, e)
            if i < retries:
                time.sleep(delay)
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
    oss_upload(cfg, apk)
    wipe_dir(folder)


def main() -> None:
    parser = argparse.ArgumentParser(description="定时把本地 apk 覆盖上传到阿里云 OSS 为 app.apk")
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
            logging.FileHandler(Path(__file__).with_name("upload_apk_oss.log"), encoding="utf-8"),
            logging.StreamHandler(sys.stdout),
        ],
    )
    logging.getLogger("oss2").setLevel(logging.WARNING)

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
