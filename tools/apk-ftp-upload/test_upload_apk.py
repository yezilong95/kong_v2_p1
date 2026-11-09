from __future__ import annotations

import os
import tempfile
import time
import unittest
from pathlib import Path
from unittest.mock import patch

from upload_apk import assert_safe_wipe_dir, pick_apk, resolve_password, wipe_dir


class PickApkTest(unittest.TestCase):
    def test_empty_dir_returns_none(self) -> None:
        with tempfile.TemporaryDirectory() as raw:
            self.assertIsNone(pick_apk(Path(raw)))

    def test_picks_newest_apk(self) -> None:
        with tempfile.TemporaryDirectory() as raw:
            folder = Path(raw)
            old = folder / "old.apk"
            new = folder / "new.apk"
            old.write_bytes(b"old")
            time.sleep(0.05)
            new.write_bytes(b"new")
            (folder / "notes.txt").write_text("keep until wipe")
            picked = pick_apk(folder)
            assert picked is not None
            self.assertEqual(picked.name, "new.apk")


class WipeDirTest(unittest.TestCase):
    def test_wipes_files_and_subdir(self) -> None:
        with tempfile.TemporaryDirectory() as raw:
            folder = Path(raw)
            (folder / "a.apk").write_bytes(b"apk")
            (folder / "notes.txt").write_text("x")
            nested = folder / "sub"
            nested.mkdir()
            (nested / "b.bin").write_bytes(b"b")
            wipe_dir(folder)
            self.assertEqual(list(folder.iterdir()), [])

    def test_refuse_root(self) -> None:
        root = Path(Path.cwd().anchor)
        with self.assertRaises(RuntimeError):
            assert_safe_wipe_dir(root)


def _clear_pw_env() -> None:
    os.environ.pop("SFTP_PASSWORD", None)
    os.environ.pop("FTP_PASSWORD", None)


class PasswordTest(unittest.TestCase):
    def test_from_sftp_config(self) -> None:
        with patch.dict(os.environ, {}, clear=False):
            _clear_pw_env()
            self.assertEqual(resolve_password({"sftp": {"password": "from-toml"}}), "from-toml")

    def test_from_ftp_config_fallback(self) -> None:
        with patch.dict(os.environ, {}, clear=False):
            _clear_pw_env()
            self.assertEqual(resolve_password({"ftp": {"password": "from-ftp"}}), "from-ftp")

    def test_env_overrides_config(self) -> None:
        with patch.dict(os.environ, {"SFTP_PASSWORD": "from-env"}):
            self.assertEqual(resolve_password({"sftp": {"password": "from-toml"}}), "from-env")

    def test_missing_raises(self) -> None:
        with patch.dict(os.environ, {}, clear=False):
            _clear_pw_env()
            with self.assertRaises(SystemExit):
                resolve_password({"sftp": {"password": ""}})


if __name__ == "__main__":
    unittest.main()
