-- 管理员账号（超级管理员 / 管理员）
CREATE TABLE IF NOT EXISTS admin_users (
  id VARCHAR(36) NOT NULL PRIMARY KEY COMMENT '主键',
  username VARCHAR(64) NOT NULL COMMENT '登录名',
  passwordHash VARCHAR(255) NOT NULL COMMENT 'scrypt 哈希',
  role VARCHAR(16) NOT NULL COMMENT 'super|admin',
  status TINYINT NOT NULL DEFAULT 1 COMMENT '1正常 0锁定',
  expiresAt DATETIME NULL COMMENT '到期时间，super 为 NULL',
  sessionVersion INT NOT NULL DEFAULT 0 COMMENT '单端登录版本',
  createdBy VARCHAR(36) NULL COMMENT '创建人',
  createdAt DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  updatedAt DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  UNIQUE KEY uk_admin_users_username (username),
  KEY idx_admin_users_role (role),
  KEY idx_admin_users_expires (expiresAt)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

CREATE TABLE IF NOT EXISTS admin_renewals (
  id VARCHAR(36) NOT NULL PRIMARY KEY COMMENT '主键',
  adminId VARCHAR(36) NOT NULL COMMENT '被续费管理员',
  months TINYINT NOT NULL COMMENT '续费月数 1|2|3',
  fromExpiresAt DATETIME NULL COMMENT '续费前到期',
  toExpiresAt DATETIME NOT NULL COMMENT '续费后到期',
  operatorId VARCHAR(36) NOT NULL COMMENT '操作人',
  createdAt DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  KEY idx_admin_renewals_admin (adminId),
  KEY idx_admin_renewals_created (createdAt)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
