USE remote_control;

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
