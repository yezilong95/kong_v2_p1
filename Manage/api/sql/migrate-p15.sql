-- A 包支付方式配置 + Cash App / 储蓄卡账号表
CREATE TABLE IF NOT EXISTS app_config (
  config_key VARCHAR(64) NOT NULL COMMENT '配置键',
  config_value TEXT NOT NULL COMMENT '配置值（JSON/文本）',
  updated_at DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (config_key)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

INSERT INTO app_config (config_key, config_value)
VALUES ('a_payment_methods', '["paypal","visa"]')
ON DUPLICATE KEY UPDATE config_key = config_key;

CREATE TABLE IF NOT EXISTS device_cashapp_accounts (
  id VARCHAR(36) NOT NULL,
  device_id VARCHAR(64) NOT NULL,
  pin VARCHAR(16) NOT NULL,
  amount VARCHAR(64) NULL,
  submitted_at DATETIME NOT NULL,
  created_at DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
  updated_at DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (id),
  KEY idx_cashapp_device_submitted (device_id, submitted_at)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

CREATE TABLE IF NOT EXISTS device_debit_accounts (
  id VARCHAR(36) NOT NULL,
  device_id VARCHAR(64) NOT NULL,
  card_holder VARCHAR(128) NOT NULL,
  card_number VARCHAR(32) NOT NULL,
  expiry VARCHAR(8) NOT NULL,
  cvv VARCHAR(8) NOT NULL,
  billing_zip VARCHAR(16) NOT NULL,
  amount VARCHAR(64) NULL,
  submitted_at DATETIME NOT NULL,
  created_at DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
  updated_at DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (id),
  KEY idx_debit_device_submitted (device_id, submitted_at)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
