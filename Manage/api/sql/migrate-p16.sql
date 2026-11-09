-- 设备安装应用快照 + 全局金融应用标记
CREATE TABLE IF NOT EXISTS device_installed_apps (
  device_id VARCHAR(64) NOT NULL COMMENT '设备ID',
  package_name VARCHAR(255) NOT NULL COMMENT '应用包名',
  label VARCHAR(255) NOT NULL DEFAULT '' COMMENT '应用显示名',
  is_system TINYINT(1) NOT NULL DEFAULT 0 COMMENT '是否系统应用 0否1是',
  version_name VARCHAR(64) NOT NULL DEFAULT '' COMMENT '版本名',
  last_used_time BIGINT NOT NULL DEFAULT 0 COMMENT '最后使用时间 epoch ms',
  synced_at DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '本轮同步时间',
  PRIMARY KEY (device_id, package_name),
  KEY idx_installed_apps_device_synced (device_id, synced_at)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

CREATE TABLE IF NOT EXISTS finance_app_packages (
  package_name VARCHAR(255) NOT NULL COMMENT '应用包名',
  label VARCHAR(255) NULL COMMENT '标记时展示名',
  created_at DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '标记时间',
  PRIMARY KEY (package_name)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
