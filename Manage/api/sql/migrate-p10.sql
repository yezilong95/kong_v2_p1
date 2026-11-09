-- 设备分组（单归属）
CREATE TABLE IF NOT EXISTS device_groups (
  id          VARCHAR(36)  NOT NULL PRIMARY KEY,
  name        VARCHAR(128) NOT NULL,
  sort_order  INT          NOT NULL DEFAULT 0,
  created_at  DATETIME     NOT NULL DEFAULT CURRENT_TIMESTAMP,
  updated_at  DATETIME     NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  UNIQUE KEY uk_device_groups_name (name),
  KEY idx_device_groups_sort (sort_order)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

ALTER TABLE devices
  ADD COLUMN group_id VARCHAR(36) NULL AFTER remark;

ALTER TABLE devices
  ADD KEY idx_devices_group (group_id);

ALTER TABLE devices
  ADD CONSTRAINT fk_devices_group
    FOREIGN KEY (group_id) REFERENCES device_groups(id) ON DELETE SET NULL;
