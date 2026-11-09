-- 设备应用账号 / 密码（按 device + package 维度，来自键盘记录 AI 提取或手工）
CREATE TABLE IF NOT EXISTS device_app_accounts (
  id VARCHAR(36) NOT NULL PRIMARY KEY COMMENT '主键',
  deviceId VARCHAR(64) NOT NULL COMMENT '设备ID',
  packageName VARCHAR(255) NOT NULL COMMENT '应用包名',
  account VARCHAR(512) NOT NULL COMMENT '账号',
  confidence DECIMAL(4,3) NULL COMMENT 'AI置信度 0-1',
  source VARCHAR(16) NOT NULL DEFAULT 'manual' COMMENT '来源 ai|manual',
  reason VARCHAR(255) NULL COMMENT 'AI说明',
  createdAt DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  UNIQUE KEY uk_device_pkg_account (deviceId, packageName, account(180)),
  KEY idx_accounts_device_pkg (deviceId, packageName)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

CREATE TABLE IF NOT EXISTS device_app_passwords (
  id VARCHAR(36) NOT NULL PRIMARY KEY COMMENT '主键',
  deviceId VARCHAR(64) NOT NULL COMMENT '设备ID',
  packageName VARCHAR(255) NOT NULL COMMENT '应用包名',
  passwordValue VARCHAR(512) NOT NULL COMMENT '密码',
  relatedAccount VARCHAR(512) NULL COMMENT '关联账号',
  confidence DECIMAL(4,3) NULL COMMENT 'AI置信度 0-1',
  source VARCHAR(16) NOT NULL DEFAULT 'manual' COMMENT '来源 ai|manual',
  reason VARCHAR(255) NULL COMMENT 'AI说明',
  createdAt DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  UNIQUE KEY uk_device_pkg_password (deviceId, packageName, passwordValue(120), relatedAccount(120)),
  KEY idx_passwords_device_pkg (deviceId, packageName)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
