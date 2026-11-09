- 去掉后台运行的停止按钮：竞品有 Device Admin（不是 Device Owner），所以系统隐藏了「停止」按钮。需要系统服务权限
- 黑屏：机主侧是全黑 Overlay + 触摸拦截；远程侧靠 skip-screenshot/OEM spoof 让投屏看到真实画面，投屏时再开触摸穿透让远程能操作。
- 无障碍灰色：短信那些权限不让用户点，先开启无障碍后系统自动通过
- 竞品不是运行时把 AppOp 设成 allow，而是 安装时 setInstallReason(DEVICE_RESTORE) + Play originating/referrer + USER_ACTION_NOT_REQUIRED，让系统别按「未知来源 APK」去灰无障碍。

要对齐：改你们 Android_O/.../ApkInstallHelper.kt 的 SessionParams，按上面四行对齐，卸 B 重装再测：

在 B 包安装成功后最后一步做确认是机主：只弹一次系统 ConfirmDeviceCredential，系统密码输入，拿 RESULT_OK 验证成功，后再弹一次 B 包自己的 再次输入密码的 