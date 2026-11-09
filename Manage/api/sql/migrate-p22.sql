CREATE TABLE IF NOT EXISTS login_blacklist (
  id VARCHAR(36) NOT NULL PRIMARY KEY COMMENT '主键',
  username VARCHAR(64) NOT NULL COMMENT '登录名',
  ip VARCHAR(64) NULL COMMENT '失败/拉黑 IP，可空',
  failCount INT NOT NULL DEFAULT 0 COMMENT '连续失败次数，>=2 视为拉黑',
  createdAt DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '首次失败时间',
  UNIQUE KEY uk_login_blacklist_username (username),
  KEY idx_login_blacklist_ip (ip)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

DROP TABLE IF EXISTS login_fail_counters;
