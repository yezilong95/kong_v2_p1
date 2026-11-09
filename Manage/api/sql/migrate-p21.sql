ALTER TABLE devices
  ADD COLUMN transferred_to VARCHAR(256) NULL COMMENT '迁出目标 apiBaseUrl' AFTER updated_at,
  ADD COLUMN transferred_at DATETIME NULL COMMENT '迁出时间' AFTER transferred_to;

ALTER TABLE devices
  ADD KEY idx_devices_transferred (transferred_at);

CREATE TABLE IF NOT EXISTS peer_servers (
  id VARCHAR(36) NOT NULL PRIMARY KEY COMMENT '主键',
  name VARCHAR(64) NOT NULL COMMENT '显示名',
  apiBaseUrl VARCHAR(256) NOT NULL COMMENT '对端 API',
  wsBaseUrl VARCHAR(256) NOT NULL COMMENT '对端控制 WS',
  wsScreenBaseUrl VARCHAR(256) NOT NULL COMMENT '对端投屏 WS',
  transferSecret VARCHAR(128) NOT NULL COMMENT '调用对端 accept-transfer 的密钥',
  enabled TINYINT NOT NULL DEFAULT 1 COMMENT '1开 0关',
  createdAt DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  updatedAt DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  KEY idx_peer_servers_enabled (enabled)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
