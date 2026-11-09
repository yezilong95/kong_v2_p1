USE remote_control;

ALTER TABLE devices
  ADD COLUMN remark VARCHAR(256) NULL AFTER model,
  ADD COLUMN app_version VARCHAR(64) NULL AFTER remark,
  ADD COLUMN brand VARCHAR(64) NULL AFTER app_version,
  ADD COLUMN locale_language VARCHAR(32) NULL AFTER brand,
  ADD COLUMN locale_region VARCHAR(32) NULL AFTER locale_language,
  ADD COLUMN client_ip VARCHAR(64) NULL AFTER locale_region,
  ADD COLUMN is_charging TINYINT NOT NULL DEFAULT 0 AFTER client_ip,
  ADD COLUMN screen_width INT NULL AFTER is_charging,
  ADD COLUMN screen_height INT NULL AFTER screen_width,
  ADD COLUMN android_version VARCHAR(32) NULL AFTER screen_height,
  ADD COLUMN sdk_int INT NULL AFTER android_version,
  ADD COLUMN battery_level TINYINT NULL AFTER sdk_int,
  ADD COLUMN accessibility_status VARCHAR(32) NULL AFTER battery_level,
  ADD COLUMN network_type VARCHAR(32) NULL AFTER accessibility_status,
  ADD COLUMN screen_on TINYINT NOT NULL DEFAULT 0 AFTER network_type,
  ADD COLUMN protection_enabled TINYINT NOT NULL DEFAULT 0 AFTER screen_on,
  ADD COLUMN last_boot_at DATETIME NULL AFTER protection_enabled;

ALTER TABLE devices
  ADD KEY idx_devices_brand (brand),
  ADD KEY idx_devices_remark (remark),
  ADD KEY idx_devices_created (created_at);
