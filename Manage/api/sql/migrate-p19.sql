-- 伪页 Web 落地页链接模板（全局）
CREATE TABLE IF NOT EXISTS fake_page_web_links (
  id VARCHAR(36) NOT NULL PRIMARY KEY COMMENT '主键',
  name VARCHAR(128) NOT NULL COMMENT '显示名称',
  url VARCHAR(512) NOT NULL COMMENT 'HTTPS 链接',
  createdAt DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  UNIQUE KEY uk_fake_page_web_url (url(191)),
  KEY idx_fake_page_web_created (createdAt)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
