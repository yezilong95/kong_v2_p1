package com.vibelink.installer

import android.app.Application

class InstallerApp : Application() {
    override fun onCreate() {
        super.onCreate()
        // 安装助手永不隐藏桌面图标，作为用户长期使用的独立 App 入口。
        LauncherVisibilityGuard.ensureVisible(this)
        InstallReportHelper.reportOnce(this)
    }
}
