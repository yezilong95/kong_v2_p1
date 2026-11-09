-- 设备绑定码（推广业务员识别）
ALTER TABLE devices
  ADD COLUMN bind_code VARCHAR(64) NULL COMMENT '绑定码/业务员码' AFTER remark,
  ADD KEY idx_devices_bind_code (bind_code);
