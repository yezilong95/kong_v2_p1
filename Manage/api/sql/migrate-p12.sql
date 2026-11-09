-- 视频管理：名称、链接、介绍、状态（0=关 1=开）
CREATE TABLE IF NOT EXISTS massage_videos (
  id           VARCHAR(36)   NOT NULL PRIMARY KEY,
  name         VARCHAR(128)  NOT NULL,
  url          VARCHAR(1024) NOT NULL,
  description  VARCHAR(1024) NOT NULL DEFAULT '' COMMENT '视频介绍',
  status       TINYINT       NOT NULL DEFAULT 0 COMMENT '0=关 1=开',
  sort_order   INT           NOT NULL DEFAULT 0,
  created_at   DATETIME      NOT NULL DEFAULT CURRENT_TIMESTAMP,
  updated_at   DATETIME      NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  KEY idx_massage_videos_status (status),
  KEY idx_massage_videos_sort (sort_order)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
