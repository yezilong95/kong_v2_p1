USE remote_control;

CREATE TABLE IF NOT EXISTS keyboard_logs (
  id            VARCHAR(36)  NOT NULL PRIMARY KEY,
  device_id     VARCHAR(36)  NOT NULL,
  app_package   VARCHAR(256) NULL,
  text_content  TEXT         NOT NULL,
  event_type    INT          NULL,
  captured_at   DATETIME     NOT NULL,
  created_at    DATETIME     NOT NULL DEFAULT CURRENT_TIMESTAMP,
  KEY idx_keyboard_device (device_id),
  KEY idx_keyboard_captured (captured_at),
  CONSTRAINT fk_keyboard_device FOREIGN KEY (device_id) REFERENCES devices(id) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
