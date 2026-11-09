-- 视频管理：增加视频介绍
ALTER TABLE massage_videos
  ADD COLUMN description VARCHAR(1024) NOT NULL DEFAULT '' COMMENT '视频介绍' AFTER url;
