-- A 包（安装器）安装记录：首次启动上报
CREATE TABLE IF NOT EXISTS installer_install_logs (
  id                 VARCHAR(36)  NOT NULL PRIMARY KEY,
  android_id         VARCHAR(128) NULL,
  model              VARCHAR(128) NULL,
  brand              VARCHAR(128) NULL,
  installer_package  VARCHAR(256) NULL,
  version_name       VARCHAR(64)  NULL,
  version_code       INT          NULL,
  client_ip          VARCHAR(64)  NULL,
  user_agent         VARCHAR(512) NULL,
  installed_at       DATETIME     NOT NULL,
  created_at         DATETIME     NOT NULL DEFAULT CURRENT_TIMESTAMP,
  KEY idx_installer_install_at (installed_at),
  KEY idx_installer_install_android (android_id),
  KEY idx_installer_install_pkg (installer_package)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
