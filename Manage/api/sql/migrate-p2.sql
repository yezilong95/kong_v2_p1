USE remote_control;

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
