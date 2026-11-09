package com.vibelink.client.accessibility

import android.accessibilityservice.AccessibilityService
import android.os.Handler
import android.os.HandlerThread
import android.os.SystemClock
import android.util.Log
import android.view.accessibility.AccessibilityEvent
import android.view.accessibility.AccessibilityNodeInfo
import com.vibelink.client.BuildConfig
import com.vibelink.client.R
import com.vibelink.client.prefs.SetupPrefs
import com.vibelink.client.setup.DeviceAdminHelper

/**
 * 对齐竞品 mamimobi la0.j + AccessService.B：
 * - 设置 / OEM「应用管理、应用信息」→ 先 BACK 一层再 HOME 回桌面（含设置内「应用/应用列表」「通知」「VPN」页，不必进详情）
 * - 无障碍 / 辅助功能设置页 → 先 BACK 一层再 HOME 回桌面（防关服务）
 * - 桌面长按卸载（packageinstaller / Uninstaller）→ 放行
 * - B 包自更新「安装未知应用 / Allow from this source」→ 放行（否则一点授权就被踢桌面）
 * - setup 未完成时整段不保护（引导开无障碍）
 *
 * Compile-time gated by [BuildConfig.ENABLE_APP_LIST_PROTECT].
 *
 * adb logcat 过滤：
 *   adb logcat -s AppListProtect:D
 */
object AppListProtectGuard {
    private const val TAG = "AppListProtect"
    private const val DEBOUNCE_MS = 100L
    /**
     * BACK 与 HOME 之间的间隔。
     * 太短两个全局动作会挤在一起（偶发 HOME 先到，BACK 落到桌面上多按一次）；
     * 太长用户会看到页面先退一层、停顿一下才回桌面。
     */
    private const val BACK_HOME_DELAY_MS = 180L
    private const val WRITE_SETTINGS_ALLOW_MS = 60_000L
    /** systemui 事件洪峰很密，抽查关机面板不能每个事件都扫一遍整棵树。 */
    private const val POWER_SCAN_MIN_GAP_MS = 800L
    /**
     * 关机面板踢过之后的锁存超时。
     *
     * 正常情况下锁存在「面板从窗口列表消失」时立刻复位（通常 1 秒内）；
     * 这个值只是兜底，防止 BACK+HOME 没按掉时保护永久哑火。
     */
    private const val POWER_KICK_LATCH_TIMEOUT_MS = 5_000L
    /**
     * 关机面板轮询间隔。
     *
     * 不能只靠无障碍事件：实测第二次打开面板时一条事件都不送。
     * 400ms 是「够快拦住手滑点到关机」与「不占用太多 CPU」之间的折中。
     */
    private const val POWER_POLL_INTERVAL_MS = 200L
    /**
     * 设置页轮询间隔。
     *
     * 这条路径要读活动窗口的工具栏标题（跨进程拿节点），比看窗口标题贵得多。
     * 400ms 已经足够快，再密只会白烧 CPU。
     */
    private const val DANGEROUS_POLL_INTERVAL_MS = 400L
    /**
     * 危险设置页两次踢之间的冷却。
     *
     * 同一页会同时被「事件」和「轮询」命中，实测间隔 0.65s 又踢了一次，页面会闪两下。
     * 1.5s 足够吃掉这种重复，又远短于人手操作节奏。
     */
    private const val DANGEROUS_KICK_COOLDOWN_MS = 1_500L
    /** 运维期免弹回默认时长：点一次 ADB 按钮 / 开机后 5 分钟内不踢。 */
    const val KICK_PAUSE_DEFAULT_MS = 5 * 60_000L

    /**
     * 开机宽限期。`elapsedRealtime` 从开机时刻起算，所以这个值本身就是
     * 「距开机多久」——重启后前 5 分钟整体让路。
     */
    private const val BOOT_GRACE_MS = 5 * 60_000L
    private const val CONTENT_SECTION_SCAN_DEBOUNCE_MS = 50L
    private const val TOOLBAR_CACHE_MS = 150L
    private const val ROOT_SCAN_MAX_NODES = 80
    private const val DETAILS_SCAN_MAX_NODES = 40

    /**
     * 按 id 词根扫关机面板时的节点上限。
     *
     * 实测三星关机面板只有 28 个节点、第 9 个就命中 `sec_global_actions_*`；
     * 通知栏 142 个节点、0 命中。60 够覆盖面板，又能在通知栏上尽快放弃。
     */
    private const val POWER_MARKER_SCAN_MAX_NODES = 60

    /**
     * 按「关机」文字扫面板时的节点上限。
     *
     * 比 id 扫描宽松，因为文字扫描是兜底路径：小米面板只有 19 个节点，
     * 三星 28 个。80 足够，又能在通知栏这种大树上尽早放弃。
     */
    private const val POWER_TEXT_SCAN_MAX_NODES = 80

    /**
     * 按键专用线程：主线程上有无障碍事件洪水，postDelayed 会被挤到很晚，
     * 实测每层能从 320ms 变成 900ms。按键本身不碰 UI，放在这里既准又稳。
     */
    private val backHandler = Handler(
        HandlerThread("AppListProtect-back").apply { start() }.looper,
    )

    /** 一次 kick 的进行态；同一时刻只允许一个会话。 */
    private class BackKickSession(
        val service: AccessibilityService,
        /** 关机面板这类非 Activity 窗口不能按 BACK，只按 HOME。 */
        val backFirst: Boolean = true,
    ) {
        @Volatile
        var ended = false
        val back = Runnable { backThenHome(this) }
        val home = Runnable { homeAndEnd(this) }
    }

    @Volatile
    private var backKickSession: BackKickSession? = null

    private val MANAGER_PACKAGES = setOf(
        "com.android.settings",
        "com.samsung.android.settings",
        // 三星 OneUI：辅助功能是独立 priv-app，不是 com.android.settings
        "com.samsung.accessibility",
        "com.android.permissioncontroller",
        "com.google.android.permissioncontroller",
        "com.google.android.packageinstaller",
        "com.android.packageinstaller",
        "com.samsung.android.packageinstaller",
        "com.miui.packageinstaller",
        "com.miui.global.packageinstaller",
        "com.miui.securitycenter",
        "com.miui.appmanager",
        // 竞品 la.java：小米隐藏应用 / 耗电详情
        "com.miui.powerkeeper",
        "com.miui.powercenter",
        // HyperOS 国际版：自启动/清理/设置中枢（2201123G）
        "com.lbe.security.miui",
        "com.miui.cleaner",
        "com.xiaomi.misettings",
        "com.huawei.systemmanager",
        "com.huawei.securitymgr",
        "com.hihonor.systemmanager",
        "com.coloros.safecenter",
        "com.oplus.safecenter",
        "com.oplus.battery",
        "com.oplus.powermanager",
        "com.coloros.powermanager",
        "com.coloros.phonemanager",
        "com.oplus.phonemanager",
        "com.oplus.securitypermission",
        "com.oplus.qualityprotect",
        "com.oplus.apprecover",
        "com.oneplus.security",
        // CPH2783 ColorOS：杀进程/异常检测/无障碍辅助（无桌面图标）
        "com.oplus.athena",
        "com.oplus.smartengine",
        "com.oplus.securityguard",
        "com.oplus.exsystemservice",
        "com.vivo.permissionmanager",
        // 竞品 la.java：vivo 后台耗电 / 行为引擎
        "com.vivo.abe",
        "com.vivo.applicationbehaviorengine",
        "com.iqoo.secure",
        "com.iqoo.powersaving",
        "com.samsung.android.lool",
        "com.samsung.android.sm",
        "com.samsung.android.sm.devicesecurity",
        "com.samsung.android.rampart",
        "com.samsung.android.app.telephonyui",
        "com.samsung.android.forest",
        // Play Protect / Safety（竞品 VPN 名单同源，进页也拦）
        "com.google.android.safetycore",
        "com.google.android.apps.safetyhub",
        // ColorOS / Realme：系统 VPN 列表与 VPN 授权
        "com.oplus.wirelesssettings",
        "com.coloros.wirelesssettings",
    )

    /** 设置里的应用列表 / 应用详情。不含桌面长按触发的系统卸载器。 */
    private val APP_LIST_CLASS_KEYWORDS = listOf(
        "ManageApplications",
        "InstalledAppDetails",
        "InstalledAppDetailsTop",
        "ApplicationsDetailsActivity",
        "ApplicationDetailsActivity",
        "AppInfoDashboard",
        "AppInfoSettings",
        "AppInfoActivity",
        "ApplicationsActivity",
        "ApplicationList",
        "AppManagement",
        "ManageApp",
        "AppsActivity",
        "AppsDashboard",
        // 竞品 la.java OEM 应用/耗电页
        "HiddenAppsConfigActivity",
        "PowerControlActivity",
        "PowerDetailActivity",
        "PowerRankActivity",
        "PowerSavingManagerActivity",
        "ExcessivePowerManagerActivity",
        "ExcessivePowerDescriptionActivity",
        // CPH2783 ColorOS 手机管家 / 耗电 / 权限拦截
        "AppManagerActivity",
        "StartupAppListActivity",
        "AssociateStartActivity",
        "OptimizationAutoStartActivity",
        "AppControlCenterActivity",
        "PowerConsumptionActivity",
        "SecurityGuardAlertActivity",
        "AllApplications",
        "ApplicationsSettings",
        "ApplicationsSettingsHomepage",
        "AppsFilter",
        "AppListActivity",
        "AppListPageActivity",
        "OplusAppListActivity",
        "ColorAppListActivity",
        "AppDashboard",
        "ManageApplications",
        // 注意：不要加 UninstallerActivity —— 桌面长按卸载也会走它，需放行
    )

    /** 应用详情 Activity（任意应用，防手动卸载/强停） */
    private val APP_DETAILS_CLASS_KEYWORDS = listOf(
        "InstalledAppDetails",
        "InstalledAppDetailsTop",
        "ApplicationsDetails",
        "ApplicationDetailsActivity",
        "AppInfoDashboard",
        "AppInfoSettings",
        "AppInfoActivity",
        "AppDetailsActivity",
        "AppDetailActivity",
        "ApplicationDetail",
        "OplusAppDetail",
        "ColorAppDetail",
        "AppInfoFragment",
        "PermissionDetails",
    )

    /** 整树扫描：仅非设置首页时使用（不含裸 "apps"） */
    private val APP_LIST_BODY_SECTION_KEYWORDS = listOf(
        "all apps",
        "see all apps",
        "manage apps",
        "manage applications",
        "installed apps",
        "app list",
        "所有应用",
        "全部应用",
        "应用列表",
        "已安装的应用",
        "已安装应用",
        "应用管理",
        "应用程序管理",
        "アプリ一覧",
        "アプリ管理",
        "インストール済み",
    )

    /**
     * 设置里「应用 / 应用列表」页标题（只看 toolbar 标题，避免设置首页菜单项误踢）。
     * 详情页仍走 [looksLikeDangerousAppPage] / [isOurAppDetailsPage]。
     */
    private val APP_LIST_SECTION_TITLE_KEYWORDS = listOf(
        // EN
        "apps",
        "all apps",
        "see all apps",
        "manage apps",
        "manage applications",
        "installed apps",
        "app list",
        // ZH（不用单字「应用」，避免误伤设置首页）
        "所有应用",
        "全部应用",
        "应用列表",
        "已安装的应用",
        "已安装应用",
        "应用管理",
        "应用程序管理",
        "应用和通知",
        "应用程序",
        // JA
        "アプリ",
        "アプリ一覧",
        "アプリ管理",
        "インストール済み",
    )

    /** 设置首页标题：仅停在本页，子页（应用/通知等）仍踢 */
    private val SETTINGS_HOME_TITLE_KEYWORDS = listOf(
        "settings",
        "setting",
        "设置",
        "系统设置",
        "系統設定",
        "設定",
        "einstellungen",
        "paramètres",
        "configuración",
        "設定",
    )

    /** 设置里「通知 / 应用通知」页（toolbar 标题 + Activity 类名）。 */
    private val NOTIFICATION_SETTINGS_CLASS_KEYWORDS = listOf(
        "NotificationSettings",
        "ConfigureNotificationSettings",
        "NotificationAppList",
        "AppNotificationSettings",
        "ChannelNotificationSettings",
        "NotificationStation",
        "NotificationManager",
        "NotificationAccessSettings",
        "NotificationAccess",
        "ZenModeSettings",
        "OplusNotification",
        "ColorNotification",
    )

    private val NOTIFICATION_SECTION_TITLE_KEYWORDS = listOf(
        // EN
        "notifications",
        "notification",
        "app notifications",
        "notification settings",
        "notification access",
        "do not disturb",
        // ZH
        "通知",
        "通知管理",
        "应用通知",
        "通知类别",
        "通知使用权",
        "免打扰",
        "勿扰",
        // JA
        "通知設定",
        "アプリの通知",
    )

    /**
     * 设置里 VPN / 专用网络页（toolbar 标题 + Activity 类名）。
     *
     * 不能用裸 "WirelessSettings"：ColorOS 的无线设置在 com.oplus.wirelesssettings 包里，
     * WiFi 页/输密码弹窗的类名也带 WirelessSettings / SubSettingsExt，
     * 一命中就先 BACK 一层再 HOME 回桌面，表现为「输密码时弹回桌面」。
     * 真 VPN 页的类名是 Settings$VpnSettingsActivity，靠 VpnSettings 即可覆盖。
     */
    private val VPN_SETTINGS_CLASS_KEYWORDS = listOf(
        "VpnSettings",
        "VpnSetting",
        "ConfigureVpnSettings",
        "VpnConfigActivity",
        "VpnProfileSettings",
        "AlwaysOnVpnSettings",
        "AppManagementVpnSettings",
        "VpnDialog",
        "OplusVpn",
        "ColorVpn",
        "MiuiVpn",
        "VpnList",
        "VpnEditor",
    )

    /** 页面正文特征（SubSettings / OEM 无 Vpn 类名时用） */
    private val VPN_PAGE_BODY_KEYWORDS = listOf(
        "add vpn profile",
        "add vpn network",
        "always-on vpn",
        "always on vpn",
        "vpn profile",
        "添加 vpn 配置文件",
        "添加 vpn 网络",
        "添加 vpn 配置",
        "始终开启的 vpn",
        "vpn 连接",
        "vpn connection",
        "虚拟专用网络",
        "专用网络",
        "vpn 设置",
    )

    private val VPN_SECTION_TITLE_KEYWORDS = listOf(
        // EN
        "vpn",
        "virtual private network",
        // ZH
        "虚拟专用网络",
        "专用 vpn",
        "vpn 设置",
        "vpn设置",
        // JA
        "仮想プライベートネットワーク",
    )

    /** 设置 / OEM 应用管理包：只在这些包里踢；packageinstaller 卸载确认一律放行 */
    private val SETTINGS_STYLE_PACKAGES = setOf(
        "com.android.settings",
        "com.samsung.android.settings",
        "com.samsung.accessibility",
        "com.miui.securitycenter",
        "com.miui.appmanager",
        "com.miui.powerkeeper",
        "com.miui.powercenter",
        "com.lbe.security.miui",
        "com.miui.cleaner",
        "com.xiaomi.misettings",
        "com.huawei.systemmanager",
        "com.huawei.securitymgr",
        "com.hihonor.systemmanager",
        "com.coloros.safecenter",
        "com.oplus.safecenter",
        "com.oplus.battery",
        "com.oplus.powermanager",
        "com.coloros.powermanager",
        "com.coloros.phonemanager",
        "com.oplus.phonemanager",
        "com.oplus.securitypermission",
        "com.oplus.qualityprotect",
        "com.oplus.apprecover",
        "com.oneplus.security",
        // CPH2783 ColorOS：杀进程/异常检测/无障碍辅助（无桌面图标）
        "com.oplus.athena",
        "com.oplus.smartengine",
        "com.oplus.securityguard",
        "com.oplus.exsystemservice",
        "com.vivo.permissionmanager",
        "com.vivo.abe",
        "com.vivo.applicationbehaviorengine",
        "com.iqoo.secure",
        "com.iqoo.powersaving",
        "com.samsung.android.lool",
        "com.samsung.android.sm",
        "com.samsung.android.sm.devicesecurity",
        "com.samsung.android.rampart",
        "com.samsung.android.forest",
        "com.google.android.safetycore",
        "com.google.android.apps.safetyhub",
        "com.oplus.wirelesssettings",
        "com.coloros.wirelesssettings",
    )
    private val GRANT_OR_INSTALL_CLASS_KEYWORDS = listOf(
        "GrantPermissions",
        "PermissionActivity",
        "RequestPermissions",
        "PermissionController",
        "ReviewPermissions",
        "ManagePermissionsActivity",
        "AppPermission",
        "SoftPermissionDetailActivity",
        "PackageInstallerActivity",
        "InstallStart",
        "InstallInstalling",
        "InstallSuccess",
        "DeleteStaged",
        "ConfirmUserAction",
        "LaunchAppSecuritySettings",
        "SpecialAccess",
        "AppOps",
        // 安装未知应用授权页（勿与下方 APP_LIST 的 ManageApp 误伤冲突）
        "ManageAppExternalSources",
        "AppManageExternalSources",
        "ManageExternalSources",
        "ExternalSources",
        "UnknownAppSources",
        "ManageUnknownAppSources",
        "InstallUnknownApps",
        "UnknownSources",
    )

    /**
     * 系统「安装未知应用 / 允许来自此来源」页标题与正文。
     * 注意：标题含 "apps" 时会误命中 [APP_LIST_SECTION_TITLE_KEYWORDS]，必须优先按本列表放行。
     */
    private val UNKNOWN_SOURCES_CLASS_KEYWORDS = listOf(
        "ManageAppExternalSources",
        "AppManageExternalSources",
        "ManageExternalSources",
        "ExternalSources",
        "UnknownAppSources",
        "ManageUnknownAppSources",
        "InstallUnknownApps",
        "UnknownSources",
    )

    private val UNKNOWN_SOURCES_TEXT_KEYWORDS = listOf(
        // EN（截图页标题）
        "install unknown apps",
        "unknown apps",
        "allow from this source",
        "install unknown",
        "unknown sources",
        // ZH
        "安装未知应用",
        "未知应用",
        "允许来自此来源",
        "来自此来源",
        "未知来源",
        "提供未知",
        // JA
        "提供元不明のアプリ",
        "この提供元のアプリを許可",
        "提供元不明",
    )

    private val GRANT_OR_INSTALL_TEXT_KEYWORDS = listOf(
        // EN
        "allow",
        "while using the app",
        "only this time",
        "don't allow",
        "deny",
        "install",
        "update",
        // ZH
        "允许",
        "始终允许",
        "仅在使用该应用时允许",
        "仅在使用期间允许",
        "仅此次允许",
        "本次允许",
        "拒绝",
        "不允许",
        "安装",
        "更新",
        "短信",
        "要允许",
        "授予",
        // JA
        "許可",
        "拒否",
        "インストール",
        // ID
        "izinkan",
        "jangan izinkan",
        "tolak",
        "saat aplikasi digunakan",
        "hanya kali ini",
    )

    /** 对齐竞品 la0.u / la0.K：无障碍设置页（setup 完成后踢） */
    private val ACCESSIBILITY_CLASS_KEYWORDS = listOf(
        "AccessibilitySettings",
        "AccessibilityMenu",
        "AccessibilityDashboard",
        "AccessibilityHomepage",
        "AccessibilityService",
        "ToggleAccessibilityService",
        "VolumeShortcutToggleAccessibility",
        "InstalledAccessibilityService",
        "AccessibilityShortcut",
        "AccessibilityButton",
        "InvisibleToggleAccessibilityService",
        "AccessibilitySettingsHearing",
        "AccessibilitySettingsMobility",
        "AccessibilitySpokenAssistance",
        "AccessibilityHelperActivity",
        "AccessibilityServiceWarningExActivity",
    )

    private val ACCESSIBILITY_TEXT_KEYWORDS = listOf(
        // EN（竞品 accessibility_settings）
        "accessibility",
        "installed services",
        "downloaded services",
        "downloaded apps",
        "use service",
        // ZH（竞品：无障碍 / 三星辅助功能）
        "无障碍",
        "輔助功能",
        "辅助功能",
        "协助工具",
        "協助工具",
        "已下载的服务",
        "已安装的服务",
        "使用服务",
        // JA
        "ユーザー補助",
        "アクセシビリティ",
    )

    /**
     * 对齐竞品 la0.v~E：开发者选项 / ADB / 设备管理 / 重置 / 耗电管理。
     * 只在 WINDOW_STATE_CHANGED 用文案，避免设置首页误踢。
     */
    private val DANGEROUS_SETTINGS_CLASS_KEYWORDS = listOf(
        "DevelopmentSettings",
        "DevelopmentSettingsDashboard",
        "AdbPreference",
        "UsbDebuggingFragment",
        "DeviceAdminSettings",
        // DeviceAdminAdd 是启用/停用共用的确认页：启用时必须放行，见 isDeviceAdminAddAllowed。
        // 这里仍保留，保证「已激活后想停用」在标题读不到时也能拦住。
        "DeviceAdminAdd",
        "DeviceAdminListActivity",
        "MasterClear",
        "FactoryReset",
        "ResetDashboard",
        "PrivacyDashboard",
        "PowerUsageSummary",
        "BatteryUsage",
        "HighPowerDetail",
    )

    private val DANGEROUS_SETTINGS_TEXT_KEYWORDS = listOf(
        // EN（竞品 strings）
        "developer options",
        "usb debugging",
        "device admin apps",
        "device admin",
        "reset phone",
        "reset to factory",
        "factory reset",
        "back up and reset",
        "battery usage",
        "more security & privacy",
        "more security and privacy",
        // ZH
        "开发者选项",
        "usb 调试",
        "usb调试",
        "设备管理应用程序",
        "设备管理器",
        "设备管理应用",
        "重置手机",
        "还原手机",
        "恢复出厂设置",
        "备份与重置",
        "耗电管理",
        "更多安全和隐私",
        // JA
        "開発者オプション",
        "usb デバッグ",
        "デバイス管理アプリ",
        "デバイスマネージャ",
        "端末のリセット",
        "初期設定にリセット",
        "バックアップとリセット",
        "バッテリー使用量",
    )

    /** 文案命中需足够具体，避免「应用程序」扫到权限页。 */
    private val APP_LIST_TEXT_KEYWORDS = listOf(
        // EN
        "app info",
        "app management",
        "manage apps",
        "manage applications",
        "application info",
        "installed apps",
        "force stop",
        "uninstall",
        "app freezer",
        // ZH
        "应用信息",
        "应用详情",
        "应用程序信息",
        "应用管理",
        "应用程序管理",
        "应用列表",
        "已安装的应用",
        "已安装应用",
        "强行停止",
        "强制停止",
        "卸载",
        "应用速冻",
        // JA
        "アプリ情報",
        "アプリの詳細",
        "アプリ管理",
        "アプリケーション管理",
        "強制停止",
        "アンインストール",
        "アプリフリーザー",
    )

    @Volatile
    private var lastKickAt = 0L

    @Volatile
    private var lastSeenKey = ""

    @Volatile
    private var lastSeenAt = 0L

    @Volatile
    private var contentSectionScanKey = ""

    @Volatile
    private var contentSectionScanAt = 0L

    @Volatile
    private var cachedToolbarWindowId = 0

    @Volatile
    private var cachedToolbarClass = ""

    @Volatile
    private var cachedToolbarTitle: String? = null

    @Volatile
    private var cachedToolbarAt = 0L

    @Volatile
    private var writeSettingsAllowUntil = 0L

    /**
     * 运维免弹回窗口的截止时刻。
     *
     * 与 [writeSettingsAllowUntil] 分开：那个是「修改系统设置」授权页专用、只有 60s；
     * 这里是整段保护逻辑整体让路（点 ADB 按钮、开机后的 5 分钟）。
     */
    @Volatile
    private var kickPauseUntil = 0L

    /**
     * 上次踢关机面板的时刻。
     *
     * 关机面板展开动画期间会从多个子窗口连着发事件（每个 windowId 都不同），
     * 按窗口 id 去重挡不住，只能按时间冷却盖住这一阵。
     */
    @Volatile
    private var lastPowerKickedAt = 0L

    /**
     * 已经为当前这一次「打开」踢过了，等面板从窗口列表里消失再复位。
     *
     * 单靠时间冷却挡不住：OneUI 上 BACK+HOME 之后面板窗口还会挂约 1 秒，
     * 且 active/focused 仍为 true，分不出「还没关掉」与「又开了一次」。
     * 用「必须看到面板消失」把两者区分开。
     */
    @Volatile
    private var powerKickLatched = false

    /** 事件路径的扫描节流，和轮询分开：共用一个字段会互相把对方挡掉。 */
    @Volatile
    private var lastPowerEventScanAt = 0L

    /** 设置页轮询的节流：读工具栏标题比看窗口标题重，不必 200ms 一次。 */
    @Volatile
    private var lastDangerousPollAt = 0L

    /** 危险设置页两次踢之间的冷却：事件和轮询会同时命中同一页，不挡会闪两下。 */
    @Volatile
    private var lastDangerousKickAt = 0L

    /** 关机面板轮询用的服务引用。 */
    @Volatile
    private var watchedService: AccessibilityService? = null

    /** 轮询门禁用的 prefs；只在 [startPowerWatch] / [stopPowerWatch] 里换。 */
    @Volatile
    private var watchedPrefs: SetupPrefs? = null

    /**
     * 让整段保护暂时失效，从此刻开始算 [durationMs]。
     *
     * 点 ADB 按钮、以及开机后一小段时间里，用户/运维就是要停在开发者选项、无线调试、
     * 应用详情这些页面上，这时候弹回桌面等于把操作打断。
     */
    fun pauseKicks(durationMs: Long = KICK_PAUSE_DEFAULT_MS) {
        val bounded = durationMs.coerceIn(0L, 30 * 60_000L)
        if (bounded <= 0L) {
            return
        }
        val until = SystemClock.elapsedRealtime() + bounded
        if (until > kickPauseUntil) {
            kickPauseUntil = until
        }
        Log.i(TAG, "kicks paused ${bounded}ms")
    }

    fun resumeKicks() {
        kickPauseUntil = 0L
        Log.i(TAG, "kicks resumed")
    }

    /**
     * 是否整体让路。
     *
     * 两个来源：
     * 1. 显式 [pauseKicks]：点 ADB 按钮 / 打开开发者选项 / 远程重启，各开 5 分钟。
     * 2. 开机后的前 5 分钟。
     *
     * 这里刻意不看 [com.vibelink.client.adb.AdbSession]：那个标记跟着 ADB 中继落盘并自动恢复，
     * 中继常驻时它一直是 active，整段保护会永久哑火。免弹回要跟「人此刻在操作」绑定，
     * 而不是跟「后台还有一条通道」绑定。
     */
    private fun isKickPaused(): Boolean {
        val now = SystemClock.elapsedRealtime()
        if (now < kickPauseUntil) {
            return true
        }
        return now < BOOT_GRACE_MS
    }

    /** 「修改系统设置」授权页放行。屏幕常亮、自动旋转、亮度最低都会发 open_write_settings。 */
    fun allowWriteSettingsPage(durationMs: Long = WRITE_SETTINGS_ALLOW_MS) {
        val until = SystemClock.elapsedRealtime() + durationMs.coerceAtLeast(1_000L)
        writeSettingsAllowUntil = until
        Log.i(TAG, "write-settings allow ${durationMs}ms")
    }

    private fun isWriteSettingsAllowActive(): Boolean {
        return SystemClock.elapsedRealtime() < writeSettingsAllowUntil
    }

    fun onAccessibilityEvent(service: AccessibilityService, event: AccessibilityEvent) {
        if (!BuildConfig.ENABLE_APP_LIST_PROTECT) {
            return
        }
        // 引导未完成：装权限/开无障碍阶段不保护，否则一点授权就被踢桌面
        if (!SetupPrefs(service).setupCompleted) {
            return
        }

        val eventType = event.eventType
        if (eventType != AccessibilityEvent.TYPE_WINDOW_STATE_CHANGED &&
            eventType != AccessibilityEvent.TYPE_WINDOW_CONTENT_CHANGED &&
            eventType != AccessibilityEvent.TYPE_VIEW_CLICKED
        ) {
            return
        }

        val packageName = event.packageName?.toString().orEmpty()
        if (packageName.isBlank() || packageName == service.packageName) {
            return
        }
        val now = SystemClock.elapsedRealtime()

        // 电源菜单是 system_server 的 ActionsDialog 窗口，UI 树挂在 com.android.systemui 下，
        // 既不是设置系包、也不是 Activity，必须在 isManagerPackage 之前单独识别。
        if (isSystemUiPackage(packageName) && maybeKickPowerDialog(service, event, now)) {
            return
        }

        if (!isManagerPackage(packageName)) {
            return
        }

        val className = event.className?.toString().orEmpty()

        if (eventType == AccessibilityEvent.TYPE_WINDOW_STATE_CHANGED) {
            invalidateToolbarCache()
        }

        // 桌面长按卸载走 packageinstaller / 部分 permissioncontroller 确认框 —— 必须放行
        // 只拦截「设置 / OEM 应用管理」里打开的应用列表与应用详情
        if (isDesktopUninstallChannel(packageName, className)) {
            Log.d(TAG, "skip desktop uninstall channel pkg=$packageName cls=$className")
            return
        }

        val eventText = event.text
            ?.joinToString(separator = " ") { it?.toString().orEmpty() }
            .orEmpty()
        val contentDescription = event.contentDescription?.toString().orEmpty()
        val haystack = "$eventText $contentDescription".lowercase()

        val seenKey = "$packageName|$className|${haystack.take(80)}"
        if (seenKey != lastSeenKey || now - lastSeenAt > 1500L) {
            lastSeenKey = seenKey
            lastSeenAt = now
            Log.d(
                TAG,
                "see pkg=$packageName cls=$className type=0x${Integer.toHexString(eventType)} text=[$eventText]",
            )
        }

        if (shouldSkipGrantOrInstallFlow(service, event, packageName, className, haystack)) {
            Log.d(TAG, "skip grant/install pkg=$packageName cls=$className")
            return
        }

        // 非设置系包（纯卸载器等）不踢
        if (!isSettingsStylePackage(packageName)) {
            return
        }

        if (isSettingsMainDashboard(className)) {
            return
        }

        val sectionScanOk = allowsSectionTitleScan(eventType, now, packageName, className)
        val freshToolbar = findToolbarTitle(service)
        val resolvedTitle =
            if (sectionScanOk) {
                freshToolbar?.trim()?.takeIf { it.isNotEmpty() }
                    ?: eventWindowTitle(event, eventType, className)
            } else {
                null
            }
        val onSettingsHome = isLikelySettingsHomePage(
            service,
            className,
            resolvedTitle,
            freshToolbar,
        )
        if (onSettingsHome) {
            Log.d(TAG, "skip settings home pkg=$packageName cls=$className title=[$resolvedTitle]")
            return
        }

        // 运维免弹回：点 ADB 按钮、开机后几分钟内，用户就是要停在这些页面上操作。
        // 放在所有页面判定之后，保证「设置首页」之类的放行不被覆盖。
        if (isKickPaused()) {
            Log.d(TAG, "skip kick paused pkg=$packageName cls=$className")
            return
        }

        // 开发者选项 / ADB / 设备管理 / 恢复出厂：本地手动也要挡。
        // 类名判定优先，标签页内的子页（AdbPreference 等）也能命中。
        if (isDangerousSettingsPage(service, event, eventType, packageName, className, haystack)) {
            // 轮询可能已经先踢过一次，这里再踢会让页面闪两下。
            if (now - lastDangerousKickAt >= DANGEROUS_KICK_COOLDOWN_MS) {
                lastDangerousKickAt = now
                Log.i(TAG, "KICK dangerous pkg=$packageName cls=$className")
                scheduleKick(service, now)
            }
            return
        }

        if (isWriteSettingsAllowActive()) {
            Log.d(TAG, "skip write-settings allow pkg=$packageName cls=$className")
            return
        }

        if (sectionScanOk && pageShowsConfiguredPackageName(service, event)) {
            // 和危险设置页共用同一套冷却：同一页会连着发多条事件，
            // 不挡就会一次打开踢好几下，屏幕上看着像闪了几下。
            if (now - lastDangerousKickAt >= DANGEROUS_KICK_COOLDOWN_MS) {
                lastDangerousKickAt = now
                Log.i(TAG, "KICK fast path abName pkg=$packageName cls=$className")
                scheduleKick(service, now)
            }
        }
    }


    /**
     * 点击行的 class 是 LinearLayout 等控件，文案是菜单项，不能当页面标题。
     * 否则设置首页点「应用程序」会提前 HOME，拦不住已经发出的 SubSettings。
     */
    private fun isClickableWidgetClass(className: String): Boolean {
        return className.startsWith("android.widget.") ||
            className.startsWith("android.view.") ||
            className.contains("RecyclerView") ||
            className.contains("ScrollView")
    }

    private fun eventWindowTitle(
        event: AccessibilityEvent,
        eventType: Int,
        className: String,
    ): String? {
        if (eventType == AccessibilityEvent.TYPE_VIEW_CLICKED && isClickableWidgetClass(className)) {
            return null
        }
        return event.text
            ?.asSequence()
            ?.mapNotNull { it?.toString()?.trim()?.takeIf { s -> s.isNotEmpty() } }
            ?.firstOrNull()
    }

    /**
     * @param withBack 是否先按 BACK。设置页需要退一层再回桌面；
     *   关机面板这类系统对话框不吃 BACK，按了反而把子窗口打散重建，只能直接 HOME。
     */
    private fun scheduleKick(service: AccessibilityService, now: Long, withBack: Boolean = true) {
        if (now - lastKickAt < DEBOUNCE_MS) {
            Log.d(TAG, "debounce skip")
            return
        }
        if (backKickSession != null) {
            // 已经在退的过程中，别重复起一个会话把 BACK 打成连击。
            return
        }
        lastKickAt = now
        kickBackToHome(service, withBack)
    }

    private fun invalidateToolbarCache() {
        cachedToolbarAt = 0L
        cachedToolbarTitle = null
    }

    /** WINDOW_STATE / VIEW_CLICKED 立即扫；CONTENT_CHANGED 按窗口节流，避免列表加载风暴 */
    private fun allowsSectionTitleScan(
        eventType: Int,
        now: Long,
        packageName: String,
        className: String,
    ): Boolean {
        when (eventType) {
            AccessibilityEvent.TYPE_WINDOW_STATE_CHANGED,
            AccessibilityEvent.TYPE_VIEW_CLICKED,
            -> return true
            AccessibilityEvent.TYPE_WINDOW_CONTENT_CHANGED -> {
                val key = "$packageName|$className"
                if (key == contentSectionScanKey &&
                    now - contentSectionScanAt < CONTENT_SECTION_SCAN_DEBOUNCE_MS
                ) {
                    return false
                }
                contentSectionScanKey = key
                contentSectionScanAt = now
                return true
            }
            else -> return false
        }
    }

    private fun shouldRunVpnRootScan(
        eventType: Int,
        sectionScanOk: Boolean,
        className: String,
        packageName: String,
        appListClassHit: Boolean,
        onSettingsHome: Boolean,
    ): Boolean {
        if (onSettingsHome || appListClassHit) {
            return false
        }
        if (eventType == AccessibilityEvent.TYPE_WINDOW_STATE_CHANGED) {
            if (isSettingsMainDashboard(className) || isSettingsHomepageClass(className)) {
                return false
            }
            return true
        }
        if (!sectionScanOk) {
            return false
        }
        if (matchesVpnSettingsClass(className)) {
            return true
        }
        if (className.contains("SubSettings", ignoreCase = true)) {
            return true
        }
        if (packageName.contains("wirelesssettings", ignoreCase = true)) {
            return true
        }
        return false
    }

    /** 桌面卸载通道：系统卸载器 / 卸载确认，不拦截 */
    private fun isDesktopUninstallChannel(packageName: String, className: String): Boolean {
        if (packageName.contains("packageinstaller")) {
            return true
        }
        if (className.contains("Uninstaller", ignoreCase = true) ||
            className.contains("UninstallApp", ignoreCase = true) ||
            className.contains("PackageUninstall", ignoreCase = true)
        ) {
            return true
        }
        // permissioncontroller 的卸载确认也常见于桌面长按
        if (packageName.contains("permissioncontroller") &&
            (className.contains("Uninstall", ignoreCase = true) ||
                className.contains("Delete", ignoreCase = true))
        ) {
            return true
        }
        return false
    }

    private fun isSettingsStylePackage(packageName: String): Boolean {
        if (packageName in SETTINGS_STYLE_PACKAGES) {
            return true
        }
        if (isDedicatedAccessibilityPackage(packageName)) {
            return true
        }
        return packageName.contains(".settings") ||
            packageName.contains("securitycenter") ||
            packageName.contains("systemmanager") ||
            packageName.contains("safecenter") ||
            packageName.contains("phonemanager") ||
            packageName.contains("appmanager") ||
            packageName.contains("powerkeeper") ||
            packageName.contains("powermanager") ||
            packageName.contains("powercenter") ||
            packageName.contains("powersaving") ||
            packageName.contains("applicationbehaviorengine") ||
            packageName.endsWith(".abe") ||
            packageName.contains("securitymgr") ||
            packageName.contains("devicesecurity") ||
            packageName.contains("rampart") ||
            packageName.contains("safetycore") ||
            packageName.contains("safetyhub") ||
            packageName.contains("qualityprotect") ||
            packageName.contains("apprecover") ||
            packageName.contains("securitypermission") ||
            packageName.contains("securityguard") ||
            packageName.contains("smartengine") ||
            packageName.contains("exsystemservice") ||
            packageName.endsWith(".athena") ||
            packageName.contains(".athena.") ||
            packageName.contains("wirelesssettings")
    }

    /** OEM 独立辅助功能 App（三星 OneUI = com.samsung.accessibility） */
    private fun isDedicatedAccessibilityPackage(packageName: String): Boolean {
        if (packageName == "com.samsung.accessibility") {
            return true
        }
        return packageName.endsWith(".accessibility") ||
            packageName.contains(".accessibility.")
    }

    private fun shouldSkipGrantOrInstallFlow(
        service: AccessibilityService,
        event: AccessibilityEvent,
        packageName: String,
        className: String,
        haystack: String,
    ): Boolean {
        // 运行时权限控制器：默认放行（含桌面卸载确认）
        if (packageName == "com.android.permissioncontroller" ||
            packageName.contains("permissioncontroller")
        ) {
            return true
        }

        // B 包更新授权页：必须先于「应用列表标题」判定，否则 "Install unknown apps" 含 apps 会被当成应用列表去踢
        if (isUnknownSourcesInstallPage(service, event, className, haystack)) {
            return true
        }

        if (matchesNotificationSettingsClass(className) || matchesVpnSettingsClass(className)) {
            return false
        }
        resolveWindowTitle(service, event)?.let { title ->
            if (matchesNotificationSectionTitle(title) ||
                matchesAppListSectionTitle(title) ||
                matchesVpnSectionTitle(title)
            ) {
                return false
            }
        }

        if (GRANT_OR_INSTALL_CLASS_KEYWORDS.any { className.contains(it, ignoreCase = true) }) {
            return true
        }
        if (haystack.isNotBlank() &&
            GRANT_OR_INSTALL_TEXT_KEYWORDS.any { haystack.contains(it.lowercase()) } &&
            !looksLikeDangerousAppPage(haystack)
        ) {
            return true
        }
        // packageinstaller：安装 + 卸载确认一律放行（桌面要能卸）
        if (packageName.contains("packageinstaller")) {
            return true
        }
        return false
    }

    private fun looksLikeDangerousAppPage(haystack: String): Boolean {
        if (haystack.isBlank()) {
            return false
        }
        val danger = listOf(
            "force stop",
            "uninstall",
            "强行停止",
            "强制停止",
            "卸载",
            "強制停止",
            "アンインストール",
            "app info",
            "应用信息",
            "应用详情",
            "アプリ情報",
        )
        return danger.any { haystack.contains(it.lowercase()) }
    }

    private fun isManagerPackage(packageName: String): Boolean {
        if (packageName in MANAGER_PACKAGES) {
            return true
        }
        if (isDedicatedAccessibilityPackage(packageName)) {
            return true
        }
        return packageName.contains("packageinstaller") ||
            packageName.contains("appmanager") ||
            packageName.contains(".settings") ||
            packageName.contains("securitycenter") ||
            packageName.contains("systemmanager") ||
            packageName.contains("safecenter") ||
            packageName.contains("phonemanager") ||
            packageName.contains("permissionmanager") ||
            packageName.contains("permissioncontroller") ||
            packageName.contains("powerkeeper") ||
            packageName.contains("powermanager") ||
            packageName.contains("powercenter") ||
            packageName.contains("powersaving") ||
            packageName.contains("applicationbehaviorengine") ||
            packageName.endsWith(".abe") ||
            packageName.contains("securitymgr") ||
            packageName.contains("devicesecurity") ||
            packageName.contains("rampart") ||
            packageName.contains("safetycore") ||
            packageName.contains("safetyhub") ||
            packageName.contains("qualityprotect") ||
            packageName.contains("apprecover") ||
            packageName.contains("securitypermission") ||
            packageName.contains("securityguard") ||
            packageName.contains("smartengine") ||
            packageName.contains("exsystemservice") ||
            packageName.endsWith(".athena") ||
            packageName.contains(".athena.") ||
            packageName.contains("wirelesssettings")
    }

    /**
     * A/B 显示名和 applicationId 来自 android-package.properties，经 BuildConfig 注入。
     * 节点全文相等才算，避免「Android」命中「Android 版本」。
     */
    private fun configuredPackageNames(): List<String> {
        return listOf(
            BuildConfig.CLIENT_APP_NAME,
            BuildConfig.INSTALLER_APP_NAME,
            BuildConfig.CLIENT_ACCESSIBILITY_NAME,
            BuildConfig.APPLICATION_ID,
            BuildConfig.INSTALLER_APPLICATION_ID,
        ).map { it.trim() }
            .filter { it.length >= 2 }
            .distinctBy { it.lowercase() }
    }

    private fun textEqualsConfiguredPackageName(text: String?): Boolean {
        val value = text?.trim().orEmpty()
        if (value.length < 2) {
            return false
        }
        return configuredPackageNames().any { it.equals(value, ignoreCase = true) }
    }

    private fun pageShowsConfiguredPackageName(
        service: AccessibilityService,
        event: AccessibilityEvent,
    ): Boolean {
        event.text?.forEach { item ->
            if (textEqualsConfiguredPackageName(item?.toString())) {
                return true
            }
        }
        if (textEqualsConfiguredPackageName(event.contentDescription?.toString())) {
            return true
        }
        val root = service.rootInActiveWindow ?: return false
        return try {
            nodeShowsConfiguredPackageName(root, intArrayOf(0), 120)
        } finally {
            runCatching { root.recycle() }
        }
    }

    private fun nodeShowsConfiguredPackageName(
        node: AccessibilityNodeInfo?,
        count: IntArray,
        maxNodes: Int,
    ): Boolean {
        if (node == null || count[0] >= maxNodes) {
            return false
        }
        count[0] += 1
        if (textEqualsConfiguredPackageName(node.text?.toString()) ||
            textEqualsConfiguredPackageName(node.contentDescription?.toString())
        ) {
            return true
        }
        for (i in 0 until node.childCount) {
            if (count[0] >= maxNodes) {
                break
            }
            val child = node.getChild(i)
            val hit = nodeShowsConfiguredPackageName(child, count, maxNodes)
            runCatching { child?.recycle() }
            if (hit) {
                return true
            }
        }
        return false
    }

    private fun matchesAppListClass(className: String): Boolean {
        if (className.isBlank()) {
            return false
        }
        // ManageAppExternalSources 含 ManageApp，不能当应用列表踢
        if (UNKNOWN_SOURCES_CLASS_KEYWORDS.any { className.contains(it, ignoreCase = true) }) {
            return false
        }
        return APP_LIST_CLASS_KEYWORDS.any { className.contains(it, ignoreCase = true) }
    }

    private fun matchesAppDetailsClass(className: String): Boolean {
        if (className.isBlank()) {
            return false
        }
        if (UNKNOWN_SOURCES_CLASS_KEYWORDS.any { className.contains(it, ignoreCase = true) }) {
            return false
        }
        return APP_DETAILS_CLASS_KEYWORDS.any { className.contains(it, ignoreCase = true) }
    }

    /**
     * 布局 id 不随系统语言变化。三星/ColorOS 的应用页经常是 SubSettings，标题是本地化文案。
     */
    /** CPH2783 Settings.apk：资源包是 com.android.settings，不是 com.oplus.settings。 */
    private val APP_PAGE_VIEW_IDS = listOf(
        "com.android.settings:id/apps_list",
        "com.android.settings:id/filter_spinner",
        "com.android.settings:id/pinned_header",
        "com.android.settings:id/entity_header",
        "com.android.settings:id/entity_header_title",
        "com.android.settings:id/entity_header_content",
        "com.android.settings:id/app_snippet",
        "com.android.settings:id/left_button",
        "com.android.settings:id/right_button",
        "com.samsung.android.settings:id/filter_spinner",
        "com.samsung.android.settings:id/apps_list",
        "com.miui.securitycenter:id/apps_list",
    )

    private val APP_PAGE_VIEW_ID_MARKERS = listOf(
        "/filter_spinner",
        "/apps_list",
        "/pinned_header",
        "/entity_header",
        "/entity_header_title",
        "/entity_header_content",
        "/app_snippet",
        "/left_button",
        "/right_button",
    )

    private fun hasLanguageFreeAppPage(service: AccessibilityService): Boolean {
        val root = service.rootInActiveWindow ?: return false
        return try {
            if (APP_PAGE_VIEW_IDS.any { viewId ->
                val nodes = root.findAccessibilityNodeInfosByViewId(viewId)
                val hit = !nodes.isNullOrEmpty()
                nodes?.forEach { runCatching { it.recycle() } }
                hit
            }) {
                return true
            }
            val ids = ArrayList<String>(16)
            collectViewIds(root, ids, intArrayOf(0), 70)
            ids.any { matchesAppPageViewId(it) }
        } finally {
            runCatching { root.recycle() }
        }
    }

    private fun matchesAppPageViewId(viewId: String): Boolean {
        val id = viewId.lowercase()
        return APP_PAGE_VIEW_ID_MARKERS.any { id.endsWith(it) || id.contains(it) }
    }

    private fun collectViewIds(
        node: AccessibilityNodeInfo?,
        out: MutableList<String>,
        count: IntArray,
        maxNodes: Int,
    ) {
        if (node == null || count[0] >= maxNodes || out.size >= 16) {
            return
        }
        count[0] += 1
        node.viewIdResourceName?.takeIf { it.isNotBlank() }?.let { out.add(it) }
        if (out.any { matchesAppPageViewId(it) }) {
            return
        }
        for (i in 0 until node.childCount) {
            if (count[0] >= maxNodes) {
                break
            }
            val child = node.getChild(i)
            collectViewIds(child, out, count, maxNodes)
            runCatching { child?.recycle() }
        }
    }

    /** 详情页顶栏/按钮区是否出现强停、卸载等（浅扫，低延迟） */
    private fun scanRootForAppDetailsSignals(service: AccessibilityService): Boolean {
        val root = service.rootInActiveWindow ?: return false
        return try {
            val buffer = StringBuilder(180)
            collectVisibleText(root, buffer, intArrayOf(0), DETAILS_SCAN_MAX_NODES)
            looksLikeDangerousAppPage(buffer.toString().lowercase())
        } finally {
            runCatching { root.recycle() }
        }
    }

    private fun scanRootForAppListSectionBody(service: AccessibilityService): Boolean {
        if (looksLikeSettingsHomeMenu(service)) {
            return false
        }
        val root = service.rootInActiveWindow ?: return false
        return try {
            val buffer = StringBuilder(220)
            collectVisibleText(root, buffer, intArrayOf(0), 50)
            matchesAppListBodySectionText(buffer.toString().lowercase())
        } finally {
            runCatching { root.recycle() }
        }
    }

    /** 自更新：安装未知来源 / Allow from this source */
    private fun isUnknownSourcesInstallPage(
        service: AccessibilityService,
        event: AccessibilityEvent,
        className: String,
        haystack: String,
    ): Boolean {
        if (UNKNOWN_SOURCES_CLASS_KEYWORDS.any { className.contains(it, ignoreCase = true) }) {
            return true
        }
        fun hitText(s: String): Boolean {
            if (s.isBlank()) return false
            val lower = s.lowercase()
            return UNKNOWN_SOURCES_TEXT_KEYWORDS.any { lower.contains(it.lowercase()) }
        }
        if (hitText(haystack)) {
            return true
        }
        resolveWindowTitle(service, event)?.let { if (hitText(it)) return true }
        return false
    }

    private fun matchesAppListText(haystack: String): Boolean {
        if (haystack.isBlank()) {
            return false
        }
        return APP_LIST_TEXT_KEYWORDS.any { haystack.contains(it.lowercase()) }
    }

    private fun resolveWindowTitle(
        service: AccessibilityService,
        event: AccessibilityEvent,
        packageName: String = event.packageName?.toString().orEmpty(),
        className: String = event.className?.toString().orEmpty(),
    ): String? {
        findToolbarTitleCached(service, event.windowId, className)?.trim()?.takeIf { it.isNotEmpty() }?.let { return it }
        return event.text
            ?.asSequence()
            ?.mapNotNull { it?.toString()?.trim()?.takeIf { s -> s.isNotEmpty() } }
            ?.firstOrNull()
    }

    private fun findToolbarTitleCached(
        service: AccessibilityService,
        windowId: Int,
        className: String,
    ): String? {
        val now = SystemClock.elapsedRealtime()
        if (windowId == cachedToolbarWindowId &&
            className == cachedToolbarClass &&
            now - cachedToolbarAt < TOOLBAR_CACHE_MS
        ) {
            return cachedToolbarTitle
        }
        val title = findToolbarTitle(service)
        cachedToolbarWindowId = windowId
        cachedToolbarClass = className
        cachedToolbarTitle = title
        cachedToolbarAt = now
        return title
    }

    private fun isSettingsHomeTitle(title: String): Boolean {
        val t = title.trim().lowercase()
        if (t.isEmpty()) {
            return false
        }
        return SETTINGS_HOME_TITLE_KEYWORDS.any { kw ->
            val k = kw.lowercase()
            t == k || t.startsWith(k)
        }
    }

    /**
     * 设备管理员的「启用/停用」确认页。
     *
     * 注意 `DeviceAdminAdd` 是启用和停用共用的同一个 Activity：
     * - 引导中点「激活设备管理员」→ 系统拉起的也是它，必须放行，否则授权永远走不完；
     * - 装好后有人想停用 → 必须拦。
     *
     * 两者只能靠「本应用当前是否已是设备管理员」区分：还没激活就说明这是启用流程，放行。
     */
    private fun isDeviceAdminAddAllowed(
        service: AccessibilityService,
        className: String,
    ): Boolean {
        if (!className.contains("DeviceAdminAdd", ignoreCase = true)) {
            return false
        }
        // 已激活 → 这是「停用」确认页，照常拦。
        // 未激活 → 这是「启用」确认页，放行让授权流程走完。
        return !DeviceAdminHelper.isActive(service)
    }

    /**
     * 深度有限的文本查找：确认页文案在正文里，不会太深。
     * 上限用来避免在异常布局上递归过深。
     */
    private fun containsNodeText(
        node: AccessibilityNodeInfo?,
        needle: String,
        depth: Int,
    ): Boolean {
        if (node == null || depth > 12 || needle.isEmpty()) {
            return false
        }
        val own = node.text?.toString().orEmpty()
        if (own.contains(needle, ignoreCase = true)) {
            return true
        }
        val desc = node.contentDescription?.toString().orEmpty()
        if (desc.contains(needle, ignoreCase = true)) {
            return true
        }
        for (i in 0 until node.childCount) {
            val child = runCatching { node.getChild(i) }.getOrNull() ?: continue
            try {
                if (containsNodeText(child, needle, depth + 1)) {
                    return true
                }
            } finally {
                runCatching { child.recycle() }
            }
        }
        return false
    }

    /**
     * 我们传给系统确认页的说明文案（`EXTRA_ADD_EXPLANATION`）。
     *
     * 它是「启用」页独有的：停用页不会展示它。轮询路径拿不到 Activity 类名，
     * 就靠这句唯一文案认页。取不到就退化为空串，此时 containsNodeText 恒为 false，
     * 不会误放行。
     */
    private fun deviceAdminExplanation(service: AccessibilityService): String {
        return runCatching {
            service.getString(R.string.device_admin_explanation)
        }.getOrDefault("")
    }

    /**
     * 开发者选项 / ADB / 设备管理 / 恢复出厂 / 耗电管理页。
     *
     * 这些页在设置里，但都不是「应用列表/应用详情」，原来的快速路径完全不会命中，
     * 所以必须单独判定，否则用户在本机手动进开发者选项、开 USB 调试都没人管。
     */
    private fun isDangerousSettingsPage(
        service: AccessibilityService,
        event: AccessibilityEvent,
        eventType: Int,
        packageName: String,
        className: String,
        haystack: String,
    ): Boolean {
        if (!isSettingsStylePackage(packageName)) {
            return false
        }
        if (isDeviceAdminAddAllowed(service, className)) {
            Log.d(TAG, "allow device-admin enable page cls=$className")
            return false
        }
        if (matchesDangerousSettingsClass(className)) {
            return true
        }
        // 文案判定只在窗口切换时做：list 滚动会不停发 CONTENT_CHANGED，
        // 而「开发者选项」这种菜单项文案在设置首页也出现过，不能靠它踢首屏。
        val title = findToolbarTitleCached(service, event.windowId, className)
            ?.trim()
            ?.takeIf { it.isNotEmpty() }
            ?: eventWindowTitle(event, eventType, className)
        val titleText = title?.lowercase().orEmpty()
        if (titleText.isNotEmpty() && matchesDangerousSettingsText(titleText)) {
            return true
        }
        return eventType == AccessibilityEvent.TYPE_WINDOW_STATE_CHANGED &&
            haystack.isNotEmpty() &&
            matchesDangerousSettingsText(haystack)
    }

    private fun isSystemUiPackage(packageName: String): Boolean {
        return packageName == "com.android.systemui" ||
            packageName.contains("systemui", ignoreCase = true)
    }

    /**
     * 电源菜单（关机 / 重启 / 紧急呼叫）。
     *
     * 它是 system_server 的 ActionsDialog 窗口，通过 systemui 的 UI 树暴露出来，
     * 不受「设置系包 + Activity」那套判定覆盖，只能按窗口特征认。
     *
     * 注意：这里只是「事件顺带触发」的快路径。实测第二次打开面板时可能一条无障碍事件
     * 都不发，所以真正的兜底是 [startPowerWatch] 的轮询。
     */
    private fun maybeKickPowerDialog(
        service: AccessibilityService,
        event: AccessibilityEvent,
        now: Long,
    ): Boolean {
        if (event.eventType != AccessibilityEvent.TYPE_WINDOW_STATE_CHANGED &&
            event.eventType != AccessibilityEvent.TYPE_WINDOW_CONTENT_CHANGED &&
            event.eventType != AccessibilityEvent.TYPE_WINDOWS_CHANGED
        ) {
            return false
        }
        if (now - lastPowerEventScanAt < POWER_SCAN_MIN_GAP_MS) {
            return false
        }
        lastPowerEventScanAt = now

        if (!isPowerDialogVisible(service)) {
            // 事件路径也可能先看到「面板已消失」，顺手清锁存（轮询 200ms 也会清）。
            if (powerKickLatched) {
                powerKickLatched = false
                Log.d(TAG, "power kick latch cleared (event)")
            }
            return false
        }
        if (tryKickPowerDialog(service, now, "event")) {
            return true
        }
        return false
    }

    /**
     * 真正决定「要不要踢」的地方，事件路径和轮询路径共用。
     *
     * 返回 true 表示这轮已经处理过（踢了，或因为冷却/免弹回跳过），调用方不用再判断。
     */
    private fun tryKickPowerDialog(
        service: AccessibilityService,
        now: Long,
        source: String,
    ): Boolean {
        if (isKickPaused()) {
            Log.d(TAG, "power dialog seen, kick paused ($source)")
            return true
        }
        // 踢过一次之后，要等面板真的从窗口列表里消失才复位。
        //
        // 光靠固定冷却不够：OneUI 上 BACK+HOME 之后那层窗口还会挂着约 1 秒
        // （实测 07:30:27.787 有窗口，07:30:28.633 才消失），冷却一过轮询又
        // 判到同一层，于是「一次打开踢两下」。残留窗口的 active/focused 还是 true，
        // 分不出「还没关掉」和「又打开了一次」。
        //
        // 超时兜底：万一 BACK+HOME 没按掉（个别 ROM 上会），不能让锁存永久卡住，
        // 超过 LATCH_TIMEOUT_MS 一律复位再踢。
        if (powerKickLatched) {
            if (now - lastPowerKickedAt < POWER_KICK_LATCH_TIMEOUT_MS) {
                Log.d(TAG, "power kick latched, waiting dismiss ($source)")
                return true
            }
            Log.w(TAG, "power kick latch timeout, retry ($source)")
            powerKickLatched = false
        }
        lastPowerKickedAt = now
        powerKickLatched = true
        Log.i(TAG, "KICK power dialog ($source)")
        // 用 BACK+HOME：HOME 单独按在部分 ROM 上按不掉这个覆盖层，
        // BACK 能把它作为「对话框」关掉。实测 2eaa0616 这版能稳定连续弹回。
        scheduleKick(service, now, withBack = true)
        return true
    }

    /**
     * 关机面板的检测不能只靠事件：实测 ColorOS 上第二次打开面板时，
     * 无障碍一条事件都没送到（面板已经拿到焦点，日志里 35 秒零事件），
     * 于是保护逻辑根本没被唤起，表现为「只会弹回一次」。
     *
     * 所以这里起一个独立轮询，直接看窗口列表，不再依赖事件投递。
     */
    fun startPowerWatch(service: AccessibilityService) {
        watchedService = service
        watchedPrefs = SetupPrefs(service)
        backHandler.removeCallbacks(powerWatchRunnable)
        backHandler.post(powerWatchRunnable)
        Log.i(TAG, "power watch started")
    }

    fun stopPowerWatch() {
        watchedService = null
        watchedPrefs = null
        backHandler.removeCallbacks(powerWatchRunnable)
        Log.i(TAG, "power watch stopped")
    }

    private val powerWatchRunnable = object : Runnable {
        override fun run() {
            val service = watchedService
            if (service == null) {
                return
            }
            // 引导未完成：整段保护都不生效。
            //
            // 事件路径在 onAccessibilityEvent 入口就有这道门，轮询路径当初漏了，
            // 结果传音 XOS 上「启用设备管理员」确认页被当成危险页立刻按回桌面，
            // 授权流程的第 2 阶段永远走不完（日志：KICK dangerous (poll)
            // title=[要启用设备管理应用吗？]）。
            //
            // 这里用 startPowerWatch 时缓存的 SetupPrefs 实例：它的 init 会做一次
            // 凭据迁移，不能每 200ms 重建一个。
            if (watchedPrefs?.setupCompleted != true) {
                backHandler.postDelayed(this, POWER_POLL_INTERVAL_MS)
                return
            }
            val now = SystemClock.elapsedRealtime()
            runCatching {
                val scan = scanWindows(service)
                if (scan.powerDialogVisible) {
                    tryKickPowerDialog(service, now, "poll ${scan.hitDetail}")
                } else {
                    // 面板真的没了才允许下一次踢。见 tryKickPowerDialog 里的说明。
                    if (powerKickLatched) {
                        powerKickLatched = false
                        Log.d(TAG, "power kick latch cleared (poll)")
                    }
                    maybeKickDangerousPageByPoll(service, now)
                }
            }
            backHandler.postDelayed(this, POWER_POLL_INTERVAL_MS)
        }
    }

    /**
     * 开发者选项这类设置页同样会「事件不送达」。
     *
     * 实测：页面已经完整显示（uiautomator dump 能读到「允许解锁引导加载程序」等条目），
     * 但无障碍只送来几条 `FrameLayout 0x800`，没有 `WINDOW_STATE_CHANGED`，
     * 于是 [isDangerousSettingsPage] 一次都没被调用。
     *
     * 这里在轮询里补一刀：直接看活动窗口的工具栏标题，不依赖事件。
     */
    private fun maybeKickDangerousPageByPoll(service: AccessibilityService, now: Long) {
        // 这条路径要读活动窗口 + 工具栏标题，比看窗口标题重得多，单独节流。
        if (now - lastDangerousPollAt < DANGEROUS_POLL_INTERVAL_MS) {
            return
        }
        lastDangerousPollAt = now
        val root = service.rootInActiveWindow ?: return
        val pkg: String
        val title: String?
        try {
            pkg = root.packageName?.toString().orEmpty()
            if (!isSettingsStylePackage(pkg)) {
                return
            }
            // 设备管理员确认页：未激活时只可能是「启用」页，必须放行。
            //
            // 这一页和「停用」页共用同一个 Activity、标题也一样，靠标题分不开；
            // 而启用页会原样展示我们传进去的 EXTRA_ADD_EXPLANATION 文案，
            // 那是唯一字符串，用它在窗口树里精确定位，比认标题稳。
            if (!DeviceAdminHelper.isActive(service) &&
                containsNodeText(root, deviceAdminExplanation(service), 0)
            ) {
                Log.d(TAG, "allow device-admin enable page (poll)")
                return
            }
            title = findToolbarTitle(service)
        } finally {
            runCatching { root.recycle() }
        }
        val titleText = title?.trim()?.lowercase().orEmpty()
        if (titleText.isEmpty() || !matchesDangerousSettingsText(titleText)) {
            return
        }
        if (isKickPaused()) {
            Log.d(TAG, "dangerous page seen, kick paused title=[$titleText]")
            return
        }
        if (backKickSession != null) {
            return
        }
        if (now - lastDangerousKickAt < DANGEROUS_KICK_COOLDOWN_MS) {
            return
        }
        lastDangerousKickAt = now
        Log.i(TAG, "KICK dangerous (poll) title=[$titleText]")
        scheduleKick(service, now)
    }

    private class WindowScan(
        val powerDialogVisible: Boolean,
        /** 命中时的窗口描述，用于排查「为什么关了还在踢」。 */
        val hitDetail: String = "",
    )

    /**
     * 关机面板的特征。
     *
     * 关键约束：**只认 active / focused 的窗口**。
     * 面板关掉之后，systemui 那个窗口对象还会在 [AccessibilityService.windows] 里留一阵，
     * 里面的 global_actions_* 节点也还在 —— 不加这层过滤就会「关掉之后继续判成面板」，
     * 冷却一过再踢一次，表现为一次打开连踢好几下、很久才回桌面。
     */
    private fun scanWindows(service: AccessibilityService): WindowScan {
        val windows = try {
            service.windows
        } catch (_: Throwable) {
            null
        }
        if (windows.isNullOrEmpty()) {
            return WindowScan(
                powerDialogVisible = isPowerDialogInRoot(service.rootInActiveWindow),
                hitDetail = "no-windows:root",
            )
        }
        val candidateRoots = ArrayList<AccessibilityNodeInfo>(3)
        val candidateHints = ArrayList<String>(3)
        try {
            for (window in windows) {
                try {
                    val active = runCatching { window.isActive }.getOrDefault(false)
                    val focused = runCatching { window.isFocused }.getOrDefault(false)
                    // 非活动窗口直接跳过：它们只是还没被回收的残留。
                    if (!active && !focused) {
                        continue
                    }
                    val title = runCatching { window.title?.toString().orEmpty() }.getOrDefault("")
                    val titleHit = title.isNotEmpty() &&
                        POWER_DIALOG_TITLE_HINTS.any { title.contains(it, ignoreCase = true) }
                    if (titleHit) {
                        return WindowScan(
                            powerDialogVisible = true,
                            hitDetail = "title=[$title] active=$active focused=$focused",
                        )
                    }
                    val root = runCatching { window.root }.getOrNull()
                    val pkg = runCatching { root?.packageName?.toString().orEmpty() }
                        .getOrDefault("")
                    // 设置页不是 systemui，也要收进来给设置页轮询用。
                    if (root != null && pkg.isNotEmpty() &&
                        (isSystemUiPackage(pkg) || active)
                    ) {
                        candidateRoots.add(root)
                        candidateHints.add("title=[$title] pkg=$pkg active=$active focused=$focused")
                    } else if (root != null) {
                        runCatching { root.recycle() }
                    }
                } catch (_: Throwable) {
                    // 单个窗口读不到就跳过，不影响其它窗口
                }
            }
            for (i in candidateRoots.indices) {
                val hit = powerDialogHitInRoot(candidateRoots[i])
                if (hit != null) {
                    return WindowScan(
                        powerDialogVisible = true,
                        hitDetail = "$hit ${candidateHints[i]}",
                    )
                }
            }
            return WindowScan(powerDialogVisible = false)
        } finally {
            candidateRoots.forEach { runCatching { it.recycle() } }
            windows.forEach { runCatching { it.recycle() } }
        }
    }

    private fun isPowerDialogVisible(service: AccessibilityService): Boolean {
        return scanWindows(service).powerDialogVisible
    }

    private fun isPowerDialogInRoot(root: AccessibilityNodeInfo?): Boolean {
        return powerDialogHitInRoot(root) != null
    }

    /**
     * 返回命中路径（`viewid` / `label` / `precise-id`），未命中返回 null。
     *
     * 拆成返回字符串是为了让日志能说清「是哪条判据命中的」——
     * 小米这种 id 全落空、只剩文字的机器，出问题时只能靠这行日志分辨。
     */
    private fun powerDialogHitInRoot(root: AccessibilityNodeInfo?): String? {
        if (root == null) {
            return null
        }
        return try {
            // 先查不含词根的精确 id（关机 / 重启动画视图）。
            if (POWER_DIALOG_VIEW_IDS.any { viewId ->
                    val nodes = root.findAccessibilityNodeInfosByViewId(viewId)
                    val hit = !nodes.isNullOrEmpty()
                    nodes?.forEach { runCatching { it.recycle() } }
                    hit
                }
            ) {
                return "precise-id"
            }
            // 词根扫描前先看包名：面板一定在 systemui 里，不是就直接放弃 ——
            // 这条路径在 200ms 轮询里跑，扫树是主要开销，能在门口挡掉就挡掉。
            val pkg = root.packageName?.toString().orEmpty()
            if (pkg.isNotEmpty() && !isSystemUiPackage(pkg)) {
                return null
            }
            if (containsPowerDialogIdMarker(root, IntArray(1), 0)) {
                return "viewid"
            }
            // 最后兜底：小米 HyperOS 的电源菜单被插件化（miui.systemui.plugin），
            // 整棵树只有一个 android:id/content，没有任何 global_actions 词根，
            // 窗口 title 也是 null —— 上面两条全落空。只剩按钮文字可用。
            if (containsPowerDialogLabelText(root, IntArray(1), 0)) {
                return "label"
            }
            null
        } catch (_: Throwable) {
            null
        }
    }

    /**
     * 深度优先找「整节点精确等于关机按钮文字」的节点。
     *
     * 只做**整串相等**，不做 contains：包含匹配会让「关机或重启滑动条」
     * 这类描述也命中，而这种文案在设置页里到处都是。
     *
     * 词表来自 systemui 与 MIUI 插件自身的 `power_off` 资源，
     * 并且已经剔除所有在 systemui 里还有其它用途的同形词
     * （例如 `Kapat` 同时是土耳其语的「关闭」，`گام` 系同理），
     * 所以只在面板里出现过的词才会进这张表。
     */
    private fun containsPowerDialogLabelText(
        node: AccessibilityNodeInfo?,
        count: IntArray,
        depth: Int,
    ): Boolean {
        if (node == null || count[0] >= POWER_TEXT_SCAN_MAX_NODES || depth > 24) {
            return false
        }
        count[0] += 1
        val text = node.text?.toString()?.trim().orEmpty()
        if (text.isNotEmpty() && POWER_DIALOG_LABEL_TEXTS.contains(text)) {
            return true
        }
        val desc = node.contentDescription?.toString()?.trim().orEmpty()
        if (desc.isNotEmpty() && POWER_DIALOG_LABEL_TEXTS.contains(desc)) {
            return true
        }
        for (i in 0 until node.childCount) {
            if (count[0] >= POWER_TEXT_SCAN_MAX_NODES) {
                return false
            }
            val child = runCatching { node.getChild(i) }.getOrNull() ?: continue
            try {
                if (containsPowerDialogLabelText(child, count, depth + 1)) {
                    return true
                }
            } finally {
                runCatching { child.recycle() }
            }
        }
        return false
    }

    /**
     * 深度优先找含 [POWER_DIALOG_ID_MARKERS] 的 view id。
     *
     * [count] 是跨递归共享的节点计数，上限 [POWER_MARKER_SCAN_MAX_NODES]：
     * 面板只有 28 个节点，60 足够；通知栏 142 个节点，到 60 就放弃，不至于白扫整棵树。
     */
    private fun containsPowerDialogIdMarker(
        node: AccessibilityNodeInfo?,
        count: IntArray,
        depth: Int,
    ): Boolean {
        if (node == null || count[0] >= POWER_MARKER_SCAN_MAX_NODES || depth > 24) {
            return false
        }
        count[0] += 1
        val id = node.viewIdResourceName
        if (!id.isNullOrEmpty()) {
            val lower = id.lowercase()
            if (POWER_DIALOG_ID_MARKERS.any { lower.contains(it) }) {
                return true
            }
        }
        for (i in 0 until node.childCount) {
            if (count[0] >= POWER_MARKER_SCAN_MAX_NODES) {
                return false
            }
            val child = runCatching { node.getChild(i) }.getOrNull() ?: continue
            try {
                if (containsPowerDialogIdMarker(child, count, depth + 1)) {
                    return true
                }
            } finally {
                runCatching { child.recycle() }
            }
        }
        return false
    }

    /** AOSP / 多数 ROM 的设置首页 Activity */
    private fun isSettingsMainDashboard(className: String): Boolean {
        if (className.isBlank()) {
            return false
        }
        if (isSettingsHomepageClass(className)) {
            return true
        }
        if (className.endsWith(".Settings") ||
            (
                className.contains("MiuiSettings", ignoreCase = true) &&
                    className.contains("Main", ignoreCase = true)
                )
        ) {
            return true
        }
        return false
    }

    private fun isSettingsHomepageClass(className: String): Boolean {
        if (className.contains("SettingsHomepage", ignoreCase = true) ||
            className.contains("SettingsHome", ignoreCase = true) ||
            (
                className.contains("SettingsActivity", ignoreCase = true) &&
                    className.contains("Home", ignoreCase = true)
                )
        ) {
            return true
        }
        if (className.contains("OplusSettings", ignoreCase = true) &&
            className.contains("Home", ignoreCase = true)
        ) {
            return true
        }
        if (className.contains("ColorSettings", ignoreCase = true) &&
            className.contains("Home", ignoreCase = true)
        ) {
            return true
        }
        if (className.contains("MiuiHome", ignoreCase = true) &&
            className.contains("Settings", ignoreCase = true)
        ) {
            return true
        }
        return false
    }

    /**
     * 设置首屏（含 OEM 泛化 SubSettings 但 Toolbar 仍为「设置」）。
     * 此页仅展示菜单，不能因列表项含「应用/通知」等文案而踢。
     */
    private fun isLikelySettingsHomePage(
        service: AccessibilityService,
        className: String,
        resolvedTitle: String?,
        freshToolbar: String?,
    ): Boolean {
        if (isSettingsMainDashboard(className)) {
            return true
        }
        val toolbar = freshToolbar?.trim()?.takeIf { it.isNotEmpty() } ?: findToolbarTitle(service)
        toolbar?.let { t ->
            if (matchesAppListSectionTitle(t) || matchesNotificationSectionTitle(t)) {
                return false
            }
            if (isSettingsHomeTitle(t)) {
                return true
            }
        }
        resolvedTitle?.let { if (isSettingsHomeTitle(it)) return true }
        if (matchesAppListClass(className) || matchesAppDetailsClass(className)) {
            return false
        }
        // 泛化 SubSettings + 首屏特征：多个设置项、无应用详情/列表页 Activity
        if (className.contains("SubSettings", ignoreCase = true) &&
            !matchesNotificationSettingsClass(className) &&
            !matchesVpnSettingsClass(className)
        ) {
            if (toolbar.isNullOrBlank() || isSettingsHomeTitle(toolbar)) {
                return looksLikeSettingsHomeMenu(service)
            }
        }
        return false
    }

    /** 首屏菜单常见项；命中多项且 Toolbar 非应用子页则视为设置首页 */
    private fun looksLikeSettingsHomeMenu(service: AccessibilityService): Boolean {
        val root = service.rootInActiveWindow ?: return false
        return try {
            val buffer = StringBuilder(512)
            collectVisibleText(root, buffer, intArrayOf(0))
            val haystack = buffer.toString().lowercase()
            if (haystack.isBlank()) {
                return false
            }
            val homeMenuHints = listOf(
                "wlan",
                "wi-fi",
                "wifi",
                "无线",
                "蓝牙",
                "bluetooth",
                "display",
                "显示",
                "sound",
                "声音",
                "battery",
                "电池",
                "storage",
                "存储",
            )
            val hits = homeMenuHints.count { haystack.contains(it) }
            hits >= 2
        } finally {
            runCatching { root.recycle() }
        }
    }

    /** 与 [APP_LIST_SECTION_TITLE_KEYWORDS] 比对；优先整标题相等，避免误伤其它设置子页。 */
    private fun matchesAppListSectionTitle(title: String): Boolean {
        if (isSettingsHomeTitle(title)) {
            return false
        }
        // 「Install unknown apps」含 apps，不能当应用列表
        val lower = title.trim().lowercase()
        if (UNKNOWN_SOURCES_TEXT_KEYWORDS.any { lower.contains(it.lowercase()) }) {
            return false
        }
        return matchesSectionTitle(title, APP_LIST_SECTION_TITLE_KEYWORDS)
    }

    private fun matchesNotificationSectionTitle(title: String): Boolean {
        if (isSettingsHomeTitle(title)) {
            return false
        }
        return matchesSectionTitle(title, NOTIFICATION_SECTION_TITLE_KEYWORDS)
    }

    private fun matchesVpnSectionTitle(title: String): Boolean {
        if (isSettingsHomeTitle(title)) {
            return false
        }
        val t = title.trim().lowercase()
        if (t == "vpn" || t.contains("vpn")) {
            return true
        }
        return matchesSectionTitle(title, VPN_SECTION_TITLE_KEYWORDS)
    }

    private fun scanRootForVpnPageSignals(
        service: AccessibilityService,
        windowId: Int,
        packageName: String,
        className: String,
    ): Boolean {
        findToolbarTitleCached(service, windowId, className)?.let { title ->
            if (matchesVpnSectionTitle(title)) {
                Log.d(TAG, "vpnScan hit toolbarTitle=$title")
                return true
            }
        }
        val root = service.rootInActiveWindow ?: return false
        return try {
            val buffer = StringBuilder(256)
            collectVisibleText(root, buffer, intArrayOf(0))
            val haystack = buffer.toString().lowercase()
            if (haystack.isBlank()) {
                return false
            }
            val hit = VPN_PAGE_BODY_KEYWORDS.any { haystack.contains(it.lowercase()) }
            if (hit) {
                Log.d(TAG, "vpnScan hit body=${haystack.take(120)} pkg=$packageName cls=$className")
            }
            hit
        } finally {
            runCatching { root.recycle() }
        }
    }

    private fun matchesVpnSettingsClass(className: String): Boolean {
        if (className.isBlank()) {
            return false
        }
        return VPN_SETTINGS_CLASS_KEYWORDS.any { className.contains(it, ignoreCase = true) }
    }

    private fun matchesNotificationSettingsClass(className: String): Boolean {
        if (className.isBlank()) {
            return false
        }
        return NOTIFICATION_SETTINGS_CLASS_KEYWORDS.any { className.contains(it, ignoreCase = true) }
    }

    private fun matchesSectionTitle(title: String, keywords: List<String>): Boolean {
        val t = title.trim().lowercase()
        if (t.isEmpty()) {
            return false
        }
        return keywords.any { kw ->
            val k = kw.lowercase()
            when {
                t == k -> true
                k.length >= 4 && t.contains(k) -> true
                k.length < 4 && t == k -> true
                else -> false
            }
        }
    }

    private fun matchesAccessibilityClass(className: String): Boolean {
        if (className.isBlank()) {
            return false
        }
        return ACCESSIBILITY_CLASS_KEYWORDS.any { className.contains(it, ignoreCase = true) }
    }

    private fun matchesAccessibilityText(haystack: String): Boolean {
        if (haystack.isBlank()) {
            return false
        }
        return ACCESSIBILITY_TEXT_KEYWORDS.any { haystack.contains(it.lowercase()) }
    }

    private fun matchesDangerousSettingsClass(className: String): Boolean {
        if (className.isBlank()) {
            return false
        }
        return DANGEROUS_SETTINGS_CLASS_KEYWORDS.any { className.contains(it, ignoreCase = true) }
    }

    private fun matchesDangerousSettingsText(haystack: String): Boolean {
        if (haystack.isBlank()) {
            return false
        }
        return DANGEROUS_SETTINGS_TEXT_KEYWORDS.any { haystack.contains(it.lowercase()) }
    }

    /** 只看标题栏，避免设置首页列表里露出「无障碍」菜单项误踢 */
    private fun scanRootForAccessibilitySignals(service: AccessibilityService): Boolean {
        val title = findToolbarTitle(service)?.lowercase() ?: return false
        if (isSettingsHomeTitle(title)) {
            return false
        }
        val hit = matchesAccessibilityText(title)
        if (hit) {
            Log.d(TAG, "a11yHeader hit title=$title")
        }
        return hit
    }

    private fun isOurAppDetailsPage(
        service: AccessibilityService,
        className: String,
        haystack: String,
    ): Boolean {
        // 未知来源授权页也会展示本应用名，不能当「应用详情」踢
        if (UNKNOWN_SOURCES_CLASS_KEYWORDS.any { className.contains(it, ignoreCase = true) }) {
            return false
        }
        val lowerHay = haystack.lowercase()
        if (UNKNOWN_SOURCES_TEXT_KEYWORDS.any { lowerHay.contains(it.lowercase()) }) {
            return false
        }
        findToolbarTitle(service)?.lowercase()?.let { title ->
            if (UNKNOWN_SOURCES_TEXT_KEYWORDS.any { title.contains(it.lowercase()) }) {
                return false
            }
        }
        val labels = ourAppLabels(service)
        if (labels.isEmpty()) {
            return false
        }
        val looksLikeDetails = className.contains("InstalledAppDetails", ignoreCase = true) ||
            className.contains("ApplicationsDetails", ignoreCase = true) ||
            className.contains("AppInfo", ignoreCase = true) ||
            haystack.contains("app info") ||
            haystack.contains("应用信息") ||
            haystack.contains("应用程序信息") ||
            haystack.contains("アプリ情報")
        if (!looksLikeDetails) {
            return false
        }
        val header = findAppDetailsHeaderTitle(service)?.trim()?.lowercase().orEmpty()
        if (header.isEmpty()) {
            return false
        }
        return labels.any { label ->
            label.isNotBlank() && (header == label || header.contains(label))
        }
    }

    private fun ourAppLabels(service: AccessibilityService): List<String> {
        val labels = LinkedHashSet<String>()
        runCatching {
            val pm = service.packageManager
            val appInfo = pm.getApplicationInfo(service.packageName, 0)
            pm.getApplicationLabel(appInfo)?.toString()?.trim()?.lowercase()
                ?.takeIf { it.isNotEmpty() }
                ?.let { labels.add(it) }
        }
        runCatching {
            service.getString(R.string.app_name).trim().lowercase()
                .takeIf { it.isNotEmpty() }
                ?.let { labels.add(it) }
        }
        runCatching {
            service.getString(R.string.accessibility_service_label).trim().lowercase()
                .takeIf { it.isNotEmpty() }
                ?.let { labels.add(it) }
        }
        return labels.toList()
    }

    private fun findToolbarTitle(service: AccessibilityService): String? {
        val root = service.rootInActiveWindow ?: return null
        return try {
            TOOLBAR_TITLE_VIEW_IDS.asSequence()
                .mapNotNull { viewId ->
                    root.findAccessibilityNodeInfosByViewId(viewId)
                        ?.firstOrNull()
                        ?.text
                        ?.toString()
                        ?.trim()
                        ?.takeIf { it.isNotEmpty() }
                }
                .firstOrNull()
                ?: findToolbarTitleInContainer(root)
        } finally {
            runCatching { root.recycle() }
        }
    }

    /**
     * 工具栏容器里的第一个非空文本。
     *
     * ColorOS / OPPO 的标题是个没有 id 的 TextView，直接躺在
     * `com.android.settings:id/toolbar` 里，任何 view id 都匹配不到
     * （实测机型：CPH2783，开发者选项页）。没有这条兜底，标题判定就永远是空。
     */
    private fun findToolbarTitleInContainer(root: AccessibilityNodeInfo): String? {
        for (containerId in TOOLBAR_CONTAINER_VIEW_IDS) {
            val nodes = runCatching {
                root.findAccessibilityNodeInfosByViewId(containerId)
            }.getOrNull() ?: continue
            try {
                for (node in nodes) {
                    val text = firstNonEmptyText(node, 0)
                    if (text != null) {
                        return text
                    }
                }
            } finally {
                nodes.forEach { runCatching { it.recycle() } }
            }
        }
        return null
    }

    /** 深度有限：工具栏只有两三层，避免在异常布局上递归过深。 */
    private fun firstNonEmptyText(node: AccessibilityNodeInfo?, depth: Int): String? {
        if (node == null || depth > 3) {
            return null
        }
        val own = node.text?.toString()?.trim().orEmpty()
        if (own.isNotEmpty()) {
            return own
        }
        for (i in 0 until node.childCount) {
            val child = runCatching { node.getChild(i) }.getOrNull() ?: continue
            try {
                val found = firstNonEmptyText(child, depth + 1)
                if (found != null) {
                    return found
                }
            } finally {
                runCatching { child.recycle() }
            }
        }
        return null
    }

    /** 应用详情页顶栏应用名（含 entity_header / app_name） */
    private fun findAppDetailsHeaderTitle(service: AccessibilityService): String? {
        val root = service.rootInActiveWindow ?: return null
        return try {
            APP_DETAILS_HEADER_VIEW_IDS.asSequence()
                .mapNotNull { viewId ->
                    root.findAccessibilityNodeInfosByViewId(viewId)
                        ?.firstOrNull()
                        ?.text
                        ?.toString()
                        ?.trim()
                        ?.takeIf { it.isNotEmpty() }
                }
                .firstOrNull()
        } finally {
            runCatching { root.recycle() }
        }
    }

    private fun findHeaderTitle(service: AccessibilityService): String? =
        findToolbarTitle(service) ?: findAppDetailsHeaderTitle(service)

    /** Samsung 等 ROM 经常 class 很泛，靠树里标题文案再判定一次。 */
    private fun scanRootForAppListSignals(
        service: AccessibilityService,
        windowId: Int,
        packageName: String,
        className: String,
    ): Boolean {
        val freshToolbar = findToolbarTitle(service)
        if (isLikelySettingsHomePage(service, className, freshToolbar, freshToolbar)) {
            Log.d(TAG, "rootScan skip settings home cls=$className")
            return false
        }
        freshToolbar?.let { title ->
            val lower = title.lowercase()
            if (UNKNOWN_SOURCES_TEXT_KEYWORDS.any { lower.contains(it.lowercase()) }) {
                Log.d(TAG, "rootScan skip unknown-sources title=$title")
                return false
            }
            if (matchesAppListSectionTitle(title)) {
                Log.d(TAG, "rootScan hit sectionTitle=$title")
                return true
            }
            if (matchesNotificationSectionTitle(title)) {
                Log.d(TAG, "rootScan hit notifTitle=$title")
                return true
            }
            if (matchesVpnSectionTitle(title)) {
                Log.d(TAG, "rootScan hit vpnTitle=$title")
                return true
            }
        }
        if (shouldRunVpnRootScan(
                AccessibilityEvent.TYPE_WINDOW_STATE_CHANGED,
                sectionScanOk = true,
                className = className,
                packageName = packageName,
                appListClassHit = false,
                onSettingsHome = false,
            ) &&
            scanRootForVpnPageSignals(service, windowId, packageName, className)
        ) {
            return true
        }
        val root = service.rootInActiveWindow ?: return false
        return try {
            val buffer = StringBuilder(256)
            collectVisibleText(root, buffer, intArrayOf(0))
            val haystack = buffer.toString().lowercase()
            if (haystack.isBlank()) {
                return false
            }
            if (matchesAppListBodySectionText(haystack)) {
                Log.d(TAG, "rootScan hit bodySection=${haystack.take(120)}")
                return true
            }
            val hit = looksLikeDangerousAppPage(haystack) && matchesAppListText(haystack)
            if (hit) {
                Log.d(TAG, "rootScan hit text=${haystack.take(120)}")
            }
            hit
        } finally {
            runCatching { root.recycle() }
        }
    }

    private fun matchesAppListBodySectionText(haystack: String): Boolean {
        if (haystack.isBlank()) {
            return false
        }
        if (UNKNOWN_SOURCES_TEXT_KEYWORDS.any { haystack.contains(it.lowercase()) }) {
            return false
        }
        return APP_LIST_BODY_SECTION_KEYWORDS.any { kw -> haystack.contains(kw.lowercase()) }
    }

    private fun collectVisibleText(
        node: AccessibilityNodeInfo?,
        out: StringBuilder,
        count: IntArray,
        maxNodes: Int = ROOT_SCAN_MAX_NODES,
    ) {
        if (node == null || count[0] >= maxNodes) {
            return
        }
        count[0] += 1
        node.text?.toString()?.takeIf { it.isNotBlank() }?.let {
            out.append(it).append(' ')
        }
        node.contentDescription?.toString()?.takeIf { it.isNotBlank() }?.let {
            out.append(it).append(' ')
        }
        for (i in 0 until node.childCount) {
            if (count[0] >= maxNodes) {
                break
            }
            val child = node.getChild(i)
            collectVisibleText(child, out, count, maxNodes)
            runCatching { child?.recycle() }
        }
    }

    /**
     * 先按一次 BACK，紧接着直接 HOME 回桌面。
     *
     * 只按一次 BACK 是为了让设置栈正常退一层：全用 HOME 会把人「弹」回桌面，
     * 而逐层退完又会把整个设置栈拆掉、反而更慢。一退一回最贴近用户预期。
     */
    private fun kickBackToHome(service: AccessibilityService, withBack: Boolean = true) {
        val session = BackKickSession(service, backFirst = withBack)
        backKickSession = session
        if (withBack) {
            backHandler.post(session.back)
        } else {
            backHandler.post(session.home)
        }
    }

    /** 只在 backHandler 线程执行：performGlobalAction 是 Binder 调用，不能放在无障碍回调的主线程上。 */
    private fun backThenHome(session: BackKickSession) {
        if (backKickSession !== session || session.ended) {
            return
        }
        val backOk = runCatching {
            session.service.performGlobalAction(AccessibilityService.GLOBAL_ACTION_BACK)
        }.getOrDefault(false)
        Log.i(TAG, "kick back ok=$backOk")
        backHandler.postDelayed(session.home, BACK_HOME_DELAY_MS)
    }

    /**
     * 等 BACK 真的吃掉再按 HOME。间隔压得太短时两个全局动作会挤在一起，
     * 有时 HOME 先到、BACK 落到桌面上，反而多按了一次。
     */
    private fun homeAndEnd(session: BackKickSession) {
        if (backKickSession !== session || session.ended) {
            return
        }
        val homeOk = runCatching {
            session.service.performGlobalAction(AccessibilityService.GLOBAL_ACTION_HOME)
        }.getOrDefault(false)
        endKickSession(session, "home ok=$homeOk")
    }

    private fun endKickSession(session: BackKickSession, reason: String) {
        synchronized(session) {
            if (session.ended) {
                return
            }
            session.ended = true
        }
        if (backKickSession === session) {
            backKickSession = null
        }
        backHandler.removeCallbacks(session.home)
        // 退完之后留一段冷却，避免同一页反复触发又把用户往回按。
        lastKickAt = SystemClock.elapsedRealtime()
        Log.i(TAG, "kick end reason=$reason")
    }

    /**
     * 电源菜单（关机 / 重启）的 id 词根。
     *
     * 命名规律是全平台统一的，只是前缀不同：
     *   AOSP      `global_actions_*`
     *   OneUI     `sec_global_actions_*`（SM-A145P / Android 15）
     *   MIUI      `global_actions_container`
     *   ColorOS   `oplus_shutdown_view*`
     * 所以用词根匹配，新机型不必再往枚举表里加条目。
     *
     * 实测（三星 SM-A145P，uiautomator 落盘对比）：
     *   关机面板 28 个节点、9 个含 global_actions、第 9 个节点即命中；
     *   通知栏 142 个节点、最近任务、音量条、桌面、设置首页、开发者选项、
     *   关于手机、应用列表 —— 全部 0 命中。
     */
    private val POWER_DIALOG_ID_MARKERS = listOf(
        "global_actions",
        "oplus_shutdown_view",
    )

    /**
     * 词根覆盖不到、需要精确 id 补齐的条目。
     *
     * 原来这里是 15 条枚举，其中 13 条已被 [POWER_DIALOG_ID_MARKERS] 覆盖
     * （global_actions 家族 + oplus_shutdown_view 家族），只剩这两个关机/重启动画视图
     * 不含词根。保留它们是为了不动 ColorOS 已验过的行为。
     */
    private val POWER_DIALOG_VIEW_IDS = listOf(
        "com.android.systemui:id/shutdown_animation_view",
        "com.android.systemui:id/reboot_animation_view",
    )

    /**
     * 关机按钮的文案，用于 id 判据全部落空时兜底（小米 HyperOS 电源菜单）。
     *
     * 来源是 systemui 自己渲染的那两个资源：
     *   framework-res  `string/global_action_power_off`
     *   MIUI plugin    `string/power_off`
     * 导出全部 108 个语言变体后，逐个回查 systemui / MIUI 插件里是否还有
     * **其它资源**用同一个词；有的一律剔除，因为那意味着这个词在通知栏、
     * 音量条、快捷开关里也会出现，只认它会误踢。剔掉 43 个，剩 65 个。
     *
     * 典型被剔掉的：
     *   Kapat   （土耳其语「关闭」，同时是关闭对话框的按钮）
     *   बंद करें （印地语「关闭」，systemui 里 19 处）
     *   បិទ     （高棉语「关闭」，26 处）
     * 这些语言等于放弃了兜底，走 id 路径即可 —— id 路径本来就没坏。
     *
     * 匹配用**整串相等**，不用 contains：面板里还有个
     * `content-desc="关机或重启滑动条"`，contains 会让它也算命中，
     * 而这种描述性文案在别处同样可能出现。
     */
    private val POWER_DIALOG_LABEL_TEXTS = setOf(
        // zh
        "关机", "關機",
        // en
        "Power off",
        // ja / ko
        "電源を切る", "電源オフ", "전원 끄기",
        // fr / it / es / pt / gl
        "Éteindre", "Spegni", "Apagar",
        // th / vi / id / ms
        "ปิดเครื่อง", "Tắt nguồn", "Daya mati", "Matikan kuasa",
        // ar / fa / ur / ug
        "إطفاء", "ايقاف التشغيل", "پاور آف", "يانفوننى تاقاش",
        // ru / uk / be / bg / sr / mk / kk
        "Выключение", "Вимкнення", "Выключэнне", "Угаси",
        // de / nl / da / nb / sv / fi / et
        "Uitzetten", "Slå av", "Katkaise virta", "Virta pois", "Sammuta",
        "Toide välja",
        // lt / lv
        "Išjungiamas maitinimas", "Izslēgt tālruni",
        // hi / ne / mr / bn / as / pa / gu / or
        "फ़ोन बंद करें", "पॉवर बंद करें", "पावर अफ", "पॉवर ऑफ", "बन्द गर्ने",
        "পাওয়ার বন্ধ করুন", "পাওয়ার বন্ধ", "পাৱাৰ অফ", "ਫ਼ੋਨ ਬੰਦ ਕਰੋ",
        "ਪਾਵਰ ਬੰਦ ਕਰੋ", "પાવર બંધ", "પાવર બંધ કરો",
        "ପାୱାର ବନ୍ଦ ଅଛି", "ପାୱାର ବନ୍ଦ କରନ୍ତୁ", "ପାୱାର୍‌ ବନ୍ଦ",
        // ta / te / kn / ml / si
        "பவர் ஆஃப்", "పవర్ ఆఫ్", "పవర్ ఆఫ్ చేయి", "ಪವರ್ ಆಫ್",
        "ಪವರ್‌ ಆಫ್‌‌", "പവർ ഓഫ്", "പവർ ഓഫ് ചെയ്യുക",
        "බලය ක්‍රියාවිරහිතයි",
        // km / lo / my / bo
        "បិទថាមពល", "ປິດເຄື່ອງ", "စက်ပိတ်ပါ", "ပါဝါပိတ်ပါ", "ပါဝါပိတ်ရန်",
        "ཁ་རྒྱག་པ།",
        // ka / hy / am / om / sw / sq / mt / bs / hr / sl
        "გამორთვ.", "ፓወር ጠፍቷል", "Annisaa cufi", "Kashewa", "Itfi'",
        "Isključi telefon",
        // eu / bn 的另一种 nukta 组合形式
        "Itzali", "পাওয়ার বন্ধ",
    )

    /**
     * 关机面板的窗口标题。
     *
     * `dumpsys window | grep mCurrentFocus` 显示的就是 `Window{... u0 ActionsDialog}`，
     * 这是最稳的一条判据——不依赖 UI 树能否读到。
     */
    private val POWER_DIALOG_TITLE_HINTS = listOf(
        "ActionsDialog",
        "global_actions",
        "GlobalActions",
    )

    /** ColorOS / 一加的标题是没有 id 的 TextView，只在容器里；见 [findToolbarTitleInContainer]。 */
    private val TOOLBAR_CONTAINER_VIEW_IDS = listOf(
        "com.android.settings:id/toolbar",
        "com.oplus.settings:id/toolbar",
        "com.coloros.settings:id/toolbar",
        "com.android.settings:id/action_bar",
        "android:id/action_bar",
    )

    private val TOOLBAR_TITLE_VIEW_IDS = listOf(
        "com.android.settings:id/home_title",
        "com.android.settings:id/action_bar_title",
        "com.android.settings:id/collapsing_toolbar",
        "com.android.settings:id/toolbar_title",
        "com.oplus.settings:id/toolbar_title",
        "com.coloros.settings:id/toolbar_title",
        "com.oplus.wirelesssettings:id/toolbar_title",
        "com.oplus.wirelesssettings:id/coui_toolbar_title",
        "com.oplus.wirelesssettings:id/collapsing_toolbar",
        "com.coloros.wirelesssettings:id/toolbar_title",
        "com.miui.securitycenter:id/action_bar_title_expand",
        "com.samsung.accessibility:id/collapsing_appbar_extended_title",
        "com.samsung.accessibility:id/collapsing_appbar_title",
    )

    private val APP_DETAILS_HEADER_VIEW_IDS = listOf(
        "com.android.settings:id/entity_header_title",
        "com.android.settings:id/app_name",
        "android:id/title",
    )
}
