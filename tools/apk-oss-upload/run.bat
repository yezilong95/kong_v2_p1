@echo off
cd /d "%~dp0"
if not exist "config.toml" (
  echo missing config.toml
  pause
  exit /b 1
)
python "%~dp0upload_apk_oss.py" --config "%~dp0config.toml" %*
if errorlevel 1 pause
