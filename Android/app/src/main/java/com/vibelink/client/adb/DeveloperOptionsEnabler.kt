package com.vibelink.client.adb

import android.content.Context
import android.content.Intent
import android.provider.Settings
import android.util.Log
import com.vibelink.client.accessibility.AccessibilityControlBridge
import com.vibelink.client.accessibility.SystemUiStrings
import org.json.JSONObject

/**
 * 未开开发者模式时：无障碍进「关于手机」→（OEM 再进软件信息）→ 连点「版本号/构建编号」7 次。
 */
object DeveloperOptionsEnabler {
    private const val TAG = "DevOptionsEnabler"
    private const val BUILD_TAP_COUNT = 7
    private const val SCROLL_ATTEMPTS = 12
    private const val SCROLL_SPAN = 0.14f
    private const val SCROLL_DURATION_MS = 480L

    private val ABOUT_PHONE_LABELS = listOf(
        "关于手机",
        "關於手機",
        "关于本机",
        "關於本機",
        "About phone",
        "About device",
        "My phone",
        "手机信息",
        "手機資訊",
        "디바이스 정보",
        "디바이스정보",
        "端末情報",
        "ステータス",
    )

    /** 三星等：关于手机里还要再进一层 */
    private val SOFTWARE_INFO_LABELS = listOf(
        "软件信息",
        "軟體資訊",
        "软件资讯",
        "Software information",
        "Software info",
        "Build number", // 有的机直接在本页
        "软件版本",
        "詳細情報",
        "소프트웨어 정보",
    )

    private val BUILD_NUMBER_LABELS = listOf(
        "版本号",
        "版本號",
        "内部版本号",
        "内部版本號",
        "构建编号",
        "建構編號",
        "编译编号",
        "編譯編號",
        "Build number",
        "Build Number",
        "MIUI 版本",
        "MIUI版本",
        "ColorOS 版本",
        "ColorOS版本",
        "OxygenOS 版本",
        "HarmonyOS 版本",
        "ビルド番号",
        "ビルドナンバー",
        "빌드 번호",
        "빌드번호",
    )

    private val ABOUT_REFS = listOf(
        "com.android.settings" to "about_settings",
        "com.oplus.settings" to "about_settings",
        "com.coloros.settings" to "about_settings",
    )

    private val SOFTWARE_REFS = listOf(
        "com.android.settings" to "software_info",
        "com.samsung.android.settings" to "software_info",
    )

    private val BUILD_REFS = listOf(
        "com.android.settings" to "build_number",
        "com.oplus.settings" to "build_number",
        "com.coloros.settings" to "build_number",
    )

    data class EnableResult(
        val ok: Boolean,
        val data: JSONObject = JSONObject(),
        val error: String? = null,
    )

    fun enableIfNeeded(context: Context): EnableResult {
        if (DeveloperOptionsHelper.isDevelopmentEnabled(context)) {
            return EnableResult(
                ok = true,
                data = JSONObject().put("alreadyEnabled", true),
            )
        }
        if (!AccessibilityControlBridge.isReady()) {
            return EnableResult(ok = false, error = "accessibility_service_not_connected")
        }

        AdbSession.begin(3 * 60_000L)
        Log.i(TAG, "start enable developer options via a11y")
        val aboutLabels = SystemUiStrings.merge(context, ABOUT_PHONE_LABELS, ABOUT_REFS)
        val softwareLabels = SystemUiStrings.merge(context, SOFTWARE_INFO_LABELS, SOFTWARE_REFS)
        val buildLabels = SystemUiStrings.merge(context, BUILD_NUMBER_LABELS, BUILD_REFS)

        if (!openDeviceInfo(context)) {
            return EnableResult(
                ok = false,
                data = JSONObject().put("visibleSample", sampleVisibleTexts(24)),
                error = "open_about_phone_failed",
            )
        }
        // 慢机设置页未起来就滚会划到桌面；先等页面稳定再找版本信息
        Log.i(TAG, "wait 3s after open device info before searching build number")
        sleep(3_000)

        // 若首屏没有版本号，先找「关于手机」再进；再找「软件信息」
        if (!visibleHasAny(buildLabels)) {
            findAndClickWithScroll(aboutLabels)
            sleep(3_000)
        }
        if (!visibleHasAny(buildLabels) && visibleHasAny(softwareLabels)) {
            findAndClickWithScroll(softwareLabels)
            sleep(3_000)
        }
        // 仍找不到版本号：再进一层软件信息，等稳后再滚找
        if (!findAndClickWithScroll(buildLabels, click = false)) {
            if (findAndClickWithScroll(softwareLabels)) {
                sleep(3_000)
            }
        }

        val tapped = tapBuildNumberSevenTimes(context, buildLabels)
        if (!tapped) {
            return EnableResult(
                ok = false,
                data = JSONObject()
                    .put("reason", "build_number_not_found")
                    .put("visibleSample", sampleVisibleTexts(40)),
                error = "enable_dev_build_number_not_found",
            )
        }

        val enabled = waitUntilEnabled(context, timeoutMs = 8_000L)
        if (!enabled) {
            return EnableResult(
                ok = false,
                data = JSONObject()
                    .put("tapped", true)
                    .put("visibleSample", sampleVisibleTexts(24)),
                error = "enable_dev_not_confirmed",
            )
        }
        Log.i(TAG, "developer options enabled")
        return EnableResult(
            ok = true,
            data = JSONObject()
                .put("enabledByA11y", true)
                .put("tappedBuildNumber", true),
        )
    }

    private fun openDeviceInfo(context: Context): Boolean {
        val intents = listOf(
            Intent(Settings.ACTION_DEVICE_INFO_SETTINGS),
            Intent(Settings.ACTION_SETTINGS),
        )
        for (raw in intents) {
            val ok = runCatching {
                context.startActivity(
                    Intent(raw).apply {
                        addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                        addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
                    },
                )
                true
            }.getOrDefault(false)
            if (ok) {
                return true
            }
        }
        return false
    }

    private fun tapBuildNumberSevenTimes(context: Context, labels: List<String>): Boolean {
        var hit: Pair<Float, Float>? = null
        for (attempt in 0 until 2) {
            hit = locateLabelCenter(labels)
            if (hit != null) {
                break
            }
            if (!findAndClickWithScroll(labels, click = false)) {
                scrollDown()
                sleep(400)
            }
        }
        val target = hit ?: locateLabelCenter(labels) ?: return false
        val (x, y) = target
        Log.i(TAG, "tap build number 7x at $x,$y")
        repeat(BUILD_TAP_COUNT) { index ->
            if (DeveloperOptionsHelper.isDevelopmentEnabled(context)) {
                Log.i(TAG, "enabled early after tap ${index}")
                return true
            }
            val clicked = AccessibilityControlBridge.click(x, y)
            if (!clicked.ok) {
                Log.w(TAG, "build tap ${index + 1} failed: ${clicked.error}")
            }
            sleep(280)
        }
        return true
    }

    private fun waitUntilEnabled(context: Context, timeoutMs: Long): Boolean {
        val deadline = System.currentTimeMillis() + timeoutMs
        while (System.currentTimeMillis() < deadline) {
            if (DeveloperOptionsHelper.isDevelopmentEnabled(context)) {
                return true
            }
            sleep(300)
        }
        return DeveloperOptionsHelper.isDevelopmentEnabled(context)
    }

    private fun findAndClickWithScroll(labels: List<String>, click: Boolean = true): Boolean {
        if (click && clickFirstMatching(labels)) {
            return true
        }
        if (!click && locateLabelCenter(labels) != null) {
            return true
        }
        for (scroll in 0 until SCROLL_ATTEMPTS) {
            scrollDown()
            sleep(400)
            if (click) {
                if (clickFirstMatching(labels)) {
                    return true
                }
            } else if (locateLabelCenter(labels) != null) {
                return true
            }
        }
        return false
    }

    private fun clickFirstMatching(labels: List<String>): Boolean {
        val hit = locateLabelCenter(labels) ?: return false
        return AccessibilityControlBridge.click(hit.first, hit.second).ok
    }

    private fun locateLabelCenter(labels: List<String>): Pair<Float, Float>? {
        val tree = AccessibilityControlBridge.dumpUiTree(maxDepth = 22, maxNodes = 1600, mode = "full")
        val nodes = tree.optJSONArray("nodes") ?: return null
        data class Hit(val score: Int, val x: Float, val y: Float)
        val hits = ArrayList<Hit>()
        for (i in 0 until nodes.length()) {
            val node = nodes.optJSONObject(i) ?: continue
            val text = node.optString("text")
            val desc = node.optString("desc")
            val hay = "$text $desc"
            val matched = labels.firstOrNull { hay.contains(it, ignoreCase = true) } ?: continue
            val bounds = node.optJSONObject("bounds") ?: continue
            val left = bounds.optInt("left")
            val top = bounds.optInt("top")
            val right = bounds.optInt("right")
            val bottom = bounds.optInt("bottom")
            if (right <= left || bottom <= top) {
                continue
            }
            val score = matched.length * 10 - hay.length.coerceAtMost(80)
            hits.add(Hit(score, (left + right) / 2f, (top + bottom) / 2f))
        }
        val best = hits.maxByOrNull { it.score } ?: return null
        return best.x to best.y
    }

    private fun visibleHasAny(labels: List<String>): Boolean {
        return locateLabelCenter(labels) != null
    }

    private fun scrollDown() {
        val tree = AccessibilityControlBridge.dumpUiTree(maxDepth = 4, maxNodes = 40, mode = "full")
        val w = tree.optInt("screenWidth", 1080).takeIf { it > 0 } ?: 1080
        val h = tree.optInt("screenHeight", 1920).takeIf { it > 0 } ?: 1920
        val x = w / 2f
        val mid = h * 0.52f
        val half = h * SCROLL_SPAN / 2f
        AccessibilityControlBridge.swipe(x, mid + half, x, mid - half, SCROLL_DURATION_MS)
    }

    private fun sampleVisibleTexts(limit: Int): String {
        val tree = AccessibilityControlBridge.dumpUiTree(maxDepth = 18, maxNodes = 800, mode = "full")
        val nodes = tree.optJSONArray("nodes") ?: return ""
        val out = ArrayList<String>()
        for (i in 0 until nodes.length()) {
            val node = nodes.optJSONObject(i) ?: continue
            val text = node.optString("text").trim()
            val desc = node.optString("desc").trim()
            if (text.isNotEmpty()) out.add(text)
            if (desc.isNotEmpty()) out.add(desc)
        }
        return out.distinct().take(limit).joinToString(" | ")
    }

    private fun sleep(ms: Long) {
        try {
            Thread.sleep(ms)
        } catch (_: InterruptedException) {
            Thread.currentThread().interrupt()
        }
    }
}
