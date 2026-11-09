-- APK 下载记录（Android_O 下载 client.apk）
CREATE TABLE IF NOT EXISTS apk_download_logs (
  id                 VARCHAR(36)  NOT NULL PRIMARY KEY,
  android_id         VARCHAR(128) NULL,
  model              VARCHAR(128) NULL,
  installer_package  VARCHAR(256) NULL,
  apk_url            VARCHAR(512) NULL,
  status             VARCHAR(32)  NOT NULL DEFAULT 'success',
  error_message      VARCHAR(512) NULL,
  client_ip          VARCHAR(64)  NULL,
  user_agent         VARCHAR(512) NULL,
  downloaded_at      DATETIME     NOT NULL,
  created_at         DATETIME     NOT NULL DEFAULT CURRENT_TIMESTAMP,
  KEY idx_apk_dl_downloaded (downloaded_at),
  KEY idx_apk_dl_android (android_id),
  KEY idx_apk_dl_status (status)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
