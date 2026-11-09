-- 无线 ADB 端点持久化。
-- 为什么要落库：`adb pair` 只需一次，之后靠 adbkey 信任，`adb connect`
-- 直接用「手机 IP + 无线调试 connect 端口」就能重连。而这两个值原本只
-- 活在进程内存里（hubs / adbSessionRegistry），服务器一重启就没了，
-- 只能重新走一遍配对码流程。落库后重启可以免配对直连。
CREATE TABLE IF NOT EXISTS device_adb_sessions (
  device_id      VARCHAR(36)  NOT NULL PRIMARY KEY COMMENT '设备ID',
  phone_ip       VARCHAR(64)  NOT NULL COMMENT '手机无线调试 IP',
  connect_port   INT          NOT NULL COMMENT '无线调试 connect 端口',
  pair_port      INT          NULL COMMENT '配对端口（仅诊断用，重连不用）',
  state          VARCHAR(16)  NOT NULL DEFAULT 'connected' COMMENT 'connected|offline|stale',
  fail_count     INT          NOT NULL DEFAULT 0 COMMENT '连续恢复失败次数',
  last_ok_at     DATETIME     NULL COMMENT '最近一次连接成功时间',
  created_at     DATETIME     NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '入库时间',
  updated_at     DATETIME     NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  KEY idx_adb_sessions_state (state)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
