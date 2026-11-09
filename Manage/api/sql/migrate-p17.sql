-- 落地页浏览量（每次访问 index 记一条）
CREATE TABLE IF NOT EXISTS landing_page_views (
  id BIGINT UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '主键',
  page_key VARCHAR(64) NOT NULL DEFAULT 'miluHome' COMMENT '落地页标识',
  client_ip VARCHAR(64) NULL COMMENT '客户端 IP',
  user_agent VARCHAR(512) NULL COMMENT 'UA',
  created_at DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '访问时间',
  PRIMARY KEY (id),
  KEY idx_landing_views_page_created (page_key, created_at)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
