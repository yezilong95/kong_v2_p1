from __future__ import annotations

import os
import tempfile
import time
import unittest
from pathlib import Path
from unittest.mock import patch

from upload_apk_oss import (
    assert_safe_wipe_dir,
    object_keys,
    pick_apk,
    resolve_ak,
    wipe_dir,
)


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


class ObjectKeyTest(unittest.TestCase):
    def test_root_object(self) -> None:
        dest, tmp = object_keys({"oss": {"filename": "app.apk"}, "upload": {"tmp_suffix": ".tmp"}})
        self.assertEqual(dest, "app.apk")
        self.assertEqual(tmp, "app.apk.tmp")

    def test_prefix(self) -> None:
        dest, tmp = object_keys(
            {"oss": {"prefix": "/apk/", "filename": "app.apk"}, "upload": {"tmp_suffix": ".tmp"}}
        )
        self.assertEqual(dest, "apk/app.apk")
        self.assertEqual(tmp, "apk/app.apk.tmp")


def _clear_ak_env() -> None:
    os.environ.pop("OSS_ACCESS_KEY_ID", None)
    os.environ.pop("OSS_ACCESS_KEY_SECRET", None)


class AkTest(unittest.TestCase):
    def test_from_config(self) -> None:
        with patch.dict(os.environ, {}, clear=False):
            _clear_ak_env()
            ak, sk = resolve_ak({"oss": {"access_key_id": "id", "access_key_secret": "secret"}})
            self.assertEqual((ak, sk), ("id", "secret"))

    def test_env_overrides_config(self) -> None:
        with patch.dict(os.environ, {"OSS_ACCESS_KEY_ID": "eid", "OSS_ACCESS_KEY_SECRET": "esecret"}):
            ak, sk = resolve_ak({"oss": {"access_key_id": "id", "access_key_secret": "secret"}})
            self.assertEqual((ak, sk), ("eid", "esecret"))

    def test_missing_raises(self) -> None:
        with patch.dict(os.environ, {}, clear=False):
            _clear_ak_env()
            with self.assertRaises(SystemExit):
                resolve_ak({"oss": {"access_key_id": "", "access_key_secret": ""}})


if __name__ == "__main__":
    unittest.main()
