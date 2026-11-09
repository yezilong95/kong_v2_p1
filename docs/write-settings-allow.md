# 「修改系统设置」授权页放行

应用列表保护只认 A/B 包名。系统「修改系统设置」授权页上会显示这两个名字，一点进去就会被踢回桌面。

`open_write_settings` 真正打开授权页时，放行 **60 秒**。这 60 秒内看到 A/B 包名也不回桌面。时间到后恢复。

后台这三个按钮在没有该权限时都会发同一条命令，共用这一次放行：

| 后台按钮 | 命令 |
|---|---|
| 屏幕常亮 | `start_remote_keep_awake` 失败后发 `open_write_settings` |
| 自动旋转 | `set_auto_rotation` 返回 `write_settings_required` 后发 `open_write_settings` |
| 亮度最低 | `set_brightness_min` 返回 `write_settings_required` 后发 `open_write_settings` |

已经授过权、没有打开授权页时，不开始计时。
