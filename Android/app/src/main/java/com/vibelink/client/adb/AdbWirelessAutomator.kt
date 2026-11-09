package com.vibelink.client.adb

import android.content.Context
import android.util.Log
import com.vibelink.client.accessibility.AccessibilityControlBridge
import com.vibelink.client.accessibility.SystemUiStrings
import org.json.JSONArray
import org.json.JSONObject
import kotlin.math.abs

/**
 * 无障碍打开无线调试并刮取配对信息。
 * 重点：开发者列表常需下滑；三星等 OEM 文案/结构差异大。
 */
object AdbWirelessAutomator {
    private const val TAG = "AdbWirelessAuto"
    private const val SCROLL_ATTEMPTS = 14
    private const val FIND_ROUNDS = 2
    /** 单次滑动约占屏高比例（太大会像甩飞列表） */
    private const val SCROLL_SPAN = 0.20f
    private const val SCROLL_DURATION_MS = 560L

    private val wirelessLabels_FALLBACK = listOf(
        "无线调试",
        "無線調試",
        "无线调试（无线）",
        "Wireless debugging",
        "Wireless Debugging",
        "Wi‑Fi debugging",
        "Wi-Fi debugging",
        "Wifi debugging",
        "WIFI debugging",
        "무선 디버깅",
        "ワイヤレスデバッグ",
        "ADB over network",
        "Network debugging",
    )

    private val pairLabels_FALLBACK = listOf(
        "使用配对码配对",
        "使用配對碼配對",
        "使用配对码配对设备",
        "Pair device with pairing code",
        "Pair with pairing code",
        "Pair device using pairing code",
        "配对设备",
        "配對裝置",
        "Pair device",
        "配对码",
        "配對碼",
        "Wi‑Fi pairing code",
        "Wi-Fi pairing code",
        "페어링 코드로 페어링",
        "ペアリングコードでペア設定",
    )

    private val switchOnHints_FALLBACK = listOf(
        "已开启",
        "已打開",
        "On",
        "ON",
        "켜짐",
        "オン",
    )

    private val WIRELESS_REFS = listOf(
        "com.android.settings" to "enable_adb_wireless",
        "com.oplus.settings" to "enable_adb_wireless",
        "com.coloros.settings" to "enable_adb_wireless",
    )

    private val PAIR_REFS = listOf(
        "com.android.settings" to "adb_pair_method_code_title",
        "com.oplus.settings" to "adb_pair_method_code_title",
        "com.coloros.settings" to "adb_pair_method_code_title",
    )

    private val SWITCH_ON_REFS = listOf(
        "com.android.settings" to "switch_on_text",
    )

    private var wirelessLabels: List<String> = wirelessLabels_FALLBACK
    private var pairLabels: List<String> = pairLabels_FALLBACK
    private var switchOnHints: List<String> = switchOnHints_FALLBACK

    private fun bindSystemLabels(context: Context) {
        wirelessLabels = SystemUiStrings.merge(context, wirelessLabels_FALLBACK, WIRELESS_REFS)
        pairLabels = SystemUiStrings.merge(context, pairLabels_FALLBACK, PAIR_REFS)
        switchOnHints = SystemUiStrings.merge(context, switchOnHints_FALLBACK, SWITCH_ON_REFS)
    }

    private fun wirelessPageHints(): List<String> {
        return wirelessLabels + pairLabels + listOf("IP", "端口", "Port")
    }

    /** 避免「Aktif」命中「Nonaktif」。中文前面的字不算拉丁字母，仍可命中。 */
    private fun containsHint(text: String, hint: String): Boolean {
        if (hint.isBlank()) {
            return false
        }
        val idx = text.indexOf(hint, ignoreCase = true)
        if (idx < 0) {
            return false
        }
        if (idx == 0) {
            return true
        }
        val before = text[idx - 1]
        return before !in 'A'..'Z' && before !in 'a'..'z'
    }

    private val IP_PORT_REGEX = Regex("""(\d{1,3}(?:\.\d{1,3}){3})\s*[:：]\s*(\d{2,5})""")
    private val CODE_REGEX = Regex("""(?<!\d)(\d{6})(?!\d)""")

    data class PrepareResult(
        val ok: Boolean,
        val data: JSONObject = JSONObject(),
        val error: String? = null,
    )

    fun prepare(context: Context): PrepareResult {
        bindSystemLabels(context)
        AdbSession.begin()
        val alreadyOn = DeveloperOptionsHelper.isWirelessAdbEnabled(context)
        Log.i(TAG, "prepare AUTO start wirelessAlreadyOn=$alreadyOn")

        // 1) 未开开发者模式则先无障碍点版本号开启
        if (!DeveloperOptionsHelper.isDevelopmentEnabled(context)) {
            val enable = DeveloperOptionsEnabler.enableIfNeeded(context)
            if (!enable.ok) {
                return PrepareResult(
                    ok = false,
                    data = enable.data.put("stage", "enable_developer"),
                    error = enable.error ?: "need_enable_dev",
                )
            }
            sleep(600)
        }

        // 2) 先尝试直达无线调试页；失败再开开发者列表
        val deepLinked = DeveloperOptionsHelper.tryOpenWirelessDebugging(context)
        sleep(if (deepLinked) 1500 else 400)

        if (!deepLinked || !pageLooksLikeWirelessDebug()) {
            val open = DeveloperOptionsHelper.open(context, autoEnable = true)
            if (!open.optBoolean("opened", false)) {
                val err = open.optString("error").ifBlank { "open_developer_failed" }
                return PrepareResult(ok = false, data = open, error = err)
            }
            sleep(1600)
            if (!openWirelessDebuggingEntry(context)) {
                if (DeveloperOptionsHelper.tryOpenWirelessDebugging(context)) {
                    sleep(1500)
                }
                if (!pageLooksLikeWirelessDebug() && !openWirelessDebuggingEntry(context)) {
                    return PrepareResult(
                        ok = false,
                        data = snapshotMeta("wireless_entry_not_found")
                            .put("deepLinked", deepLinked)
                            .put("wirelessAlreadyOn", alreadyOn)
                            .put("visibleSample", sampleVisibleTexts(24)),
                        error = "scrape_failed:wireless_entry",
                    )
                }
            }
            sleep(1200)
        }

        return scrapePairingFromCurrentPage(context, modeTag = "auto")
    }

    /**
     * 手动匹配：假定人手已停在无线调试页，绝不 open 开发者/导航，只刮码配对。
     */
    fun prepareManual(context: Context): PrepareResult {
        bindSystemLabels(context)
        AdbSession.begin()
        Log.i(TAG, "prepare MANUAL start (no navigation)")
        val blob = collectVisibleTexts().joinToString("\n")
        val onWirelessPage =
            wirelessLabels.any { blob.contains(it, ignoreCase = true) } ||
                pairLabels.any { blob.contains(it, ignoreCase = true) } ||
                IP_PORT_REGEX.containsMatchIn(blob)
        if (!onWirelessPage) {
            return PrepareResult(
                ok = false,
                data = snapshotMeta("not_on_wireless_page")
                    .put("mode", "manual")
                    .put("visibleSample", sampleVisibleTexts(24)),
                error = "not_on_wireless_page",
            )
        }
        return scrapePairingFromCurrentPage(context, modeTag = "manual")
    }

    /** 当前页：开开关 → 刮 connect → 点配对码 → 刮 pair 字段 */
    private fun scrapePairingFromCurrentPage(context: Context, modeTag: String): PrepareResult {
        ensureWirelessEnabled(context)
        sleep(1000)

        val connectBefore = scrapeConnectEndpoint()
        Log.i(
            TAG,
            "pre-pair connect ${connectBefore?.first ?: "-"}:${connectBefore?.second ?: 0} mode=$modeTag",
        )

        if (!findAndClickWithScroll(pairLabels)) {
            scrollDown()
            sleep(500)
            findAndClickWithScroll(pairLabels)
        }
        sleep(1400)

        var scraped = scrapePairingInfo()
        if (!hasCompletePairFields(scraped)) {
            sleep(1200)
            scraped = scrapePairingInfo()
        }

        val pairPort = scraped.optInt("pairPort", 0)
        val pairCode = scraped.optString("pairCode")
        val connectPort = connectBefore?.second ?: scraped.optInt("connectPort", 0)
        val ip = connectBefore?.first?.takeIf { it.isNotBlank() } ?: scraped.optString("ip")

        scraped
            .put("ip", ip)
            .put("pairPort", pairPort)
            .put("pairCode", pairCode)
            .put("connectPort", connectPort)
            .put("connectPortSource", if (connectBefore != null) "pre_pair" else "pair_dialog")
            .put("mode", modeTag)

        if (!hasCompletePairFields(scraped)) {
            Log.w(TAG, "scrape incomplete ip=$ip pairPort=$pairPort codeLen=${pairCode.length}")
            return PrepareResult(
                ok = false,
                data = scraped
                    .put("partial", true)
                    .put("visibleSample", sampleVisibleTexts(40)),
                error = "scrape_failed:fields",
            )
        }

        if (connectPort <= 0 || connectPort == pairPort) {
            Log.w(TAG, "connect port missing or equals pairPort=$pairPort connectPort=$connectPort")
            return PrepareResult(
                ok = false,
                data = scraped
                    .put("partial", true)
                    .put("visibleSample", sampleVisibleTexts(40)),
                error = "scrape_failed:connect_port",
            )
        }

        return PrepareResult(ok = true, data = scraped)
    }

    private fun hasCompletePairFields(scraped: JSONObject): Boolean {
        return scraped.optString("ip").isNotBlank() &&
            scraped.optInt("pairPort", 0) > 0 &&
            scraped.optString("pairCode").length == 6
    }

    private fun pageLooksLikeWirelessDebug(): Boolean {
        val texts = collectVisibleTexts()
        val blob = texts.joinToString("\n")
        val hasWireless = wirelessLabels.any { blob.contains(it, ignoreCase = true) }
        val hasPairOrPort = pairLabels.any { blob.contains(it, ignoreCase = true) } ||
            IP_PORT_REGEX.containsMatchIn(blob) ||
            blob.contains("配对码") ||
            blob.contains("Pairing code", ignoreCase = true)
        return hasWireless && hasPairOrPort
    }

    private enum class SwitchState { ON, OFF, UNKNOWN }

    private data class WirelessRow(
        val state: SwitchState,
        val labelX: Float,
        val labelY: Float,
        val switchX: Float,
        val switchY: Float,
    )

    /**
     * 开发者列表里找到「无线调试」：先判开关状态，关则点开关，再点标题进详情。
     */
    private fun openWirelessDebuggingEntry(context: Context): Boolean {
        if (pageLooksLikeWirelessDebug()) {
            return true
        }
        val row = findWirelessRowWithScroll() ?: return false
        val globalOn = DeveloperOptionsHelper.isWirelessAdbEnabled(context)
        val isOn = globalOn || row.state == SwitchState.ON
        Log.i(
            TAG,
            "wireless row found state=${row.state} globalOn=$globalOn -> ${if (isOn) "enter" else "toggle+enter"}",
        )
        if (!isOn) {
            AccessibilityControlBridge.click(row.switchX, row.switchY)
            sleep(1000)
            if (!DeveloperOptionsHelper.isWirelessAdbEnabled(context) && row.state != SwitchState.ON) {
                // 再点一次标题旁开关区域（部分 ROM 第一次点偏）
                AccessibilityControlBridge.click(row.switchX, row.switchY)
                sleep(800)
            }
        }
        if (pageLooksLikeWirelessDebug()) {
            return true
        }
        // 已开时点标题进详情，避免点到开关误关
        AccessibilityControlBridge.click(row.labelX, row.labelY)
        sleep(1200)
        return pageLooksLikeWirelessDebug() ||
            DeveloperOptionsHelper.isWirelessAdbEnabled(context) ||
            findWirelessRow() != null
    }

    private fun findWirelessRowWithScroll(): WirelessRow? {
        for (round in 0 until FIND_ROUNDS) {
            findWirelessRow()?.let { return it }
            if (round == 0) {
                scrollUp()
                sleep(400)
                findWirelessRow()?.let { return it }
            }
            for (scroll in 0 until SCROLL_ATTEMPTS) {
                scrollDown()
                sleep(450)
                findWirelessRow()?.let {
                    Log.i(TAG, "found wireless row after scroll round=$round scroll=$scroll")
                    return it
                }
            }
        }
        return null
    }

    private fun findWirelessRow(): WirelessRow? {
        val tree = AccessibilityControlBridge.dumpUiTree(maxDepth = 22, maxNodes = 1600, mode = "full")
        val nodes = tree.optJSONArray("nodes") ?: return null
        val screenH = tree.optInt("screenHeight", 0).takeIf { it > 0 } ?: 1920
        val screenW = tree.optInt("screenWidth", 0).takeIf { it > 0 } ?: 1080
        val pageTexts = collectVisibleTextsFromNodes(nodes)
        val pageLooksOn = switchOnHints.any { hint -> pageTexts.any { containsHint(it, hint) } }

        var best: WirelessRow? = null
        var bestScore = Int.MIN_VALUE
        for (i in 0 until nodes.length()) {
            val node = nodes.optJSONObject(i) ?: continue
            val text = node.optString("text")
            val desc = node.optString("desc")
            val hay = "$text $desc"
            val matched = wirelessLabels.firstOrNull { hay.contains(it, ignoreCase = true) } ?: continue
            val bounds = node.optJSONObject("bounds") ?: continue
            val left = bounds.optInt("left")
            val top = bounds.optInt("top")
            val right = bounds.optInt("right")
            val bottom = bounds.optInt("bottom")
            if (right - left < 8 || bottom - top < 8) {
                continue
            }
            val labelX = (left + right) / 2f
            val labelY = ((top + bottom) / 2f).coerceIn(8f, screenH - 8f)
            val nearby = findNearbyCheckable(nodes, left, top, right, bottom, screenW)
            val state = resolveSwitchState(
                node = node,
                hay = hay,
                nearbyChecked = nearby?.optBoolean("checked"),
                pageLooksOn = pageLooksOn,
            )
            val switchBounds = nearby?.optJSONObject("bounds")
            val switchX: Float
            val switchY: Float
            if (switchBounds != null) {
                val sl = switchBounds.optInt("left")
                val st = switchBounds.optInt("top")
                val sr = switchBounds.optInt("right")
                val sb = switchBounds.optInt("bottom")
                switchX = (sl + sr) / 2f
                switchY = ((st + sb) / 2f).coerceIn(8f, screenH - 8f)
            } else {
                switchX = if (right - left > screenW * 0.5f) {
                    left + (right - left) * 0.88f
                } else {
                    (screenW * 0.90f).coerceAtMost((right - 8).toFloat()).coerceAtLeast((left + 8).toFloat())
                }
                switchY = labelY
            }
            val score = matched.length * 10 - hay.length.coerceAtMost(80)
            if (score > bestScore) {
                bestScore = score
                best = WirelessRow(state, labelX, labelY, switchX, switchY)
            }
        }
        return best
    }

    private fun findNearbyCheckable(
        nodes: JSONArray,
        left: Int,
        top: Int,
        right: Int,
        bottom: Int,
        screenW: Int,
    ): JSONObject? {
        val rowMidY = (top + bottom) / 2
        var best: JSONObject? = null
        var bestDist = Int.MAX_VALUE
        for (i in 0 until nodes.length()) {
            val node = nodes.optJSONObject(i) ?: continue
            if (!node.optBoolean("checkable", false)) {
                continue
            }
            val b = node.optJSONObject("bounds") ?: continue
            val l = b.optInt("left")
            val t = b.optInt("top")
            val r = b.optInt("right")
            val bot = b.optInt("bottom")
            val midY = (t + bot) / 2
            // 同一行偏右的开关
            if (abs(midY - rowMidY) > (bottom - top).coerceAtLeast(48)) {
                continue
            }
            if (r < left - 24) {
                continue
            }
            val dist = abs(l - right) + abs(midY - rowMidY)
            val preferRight = l >= screenW * 0.55f
            val score = if (preferRight) dist else dist + 10_000
            if (score < bestDist) {
                bestDist = score
                best = node
            }
        }
        return best
    }

    private fun resolveSwitchState(
        node: JSONObject,
        hay: String,
        nearbyChecked: Boolean?,
        pageLooksOn: Boolean,
    ): SwitchState {
        val looksOff = hay.contains("关闭") ||
            hay.contains("關") ||
            hay.contains("Off", ignoreCase = true) ||
            hay.contains("꺼짐") ||
            hay.contains("オフ")
        val looksOn = pageLooksOn ||
            switchOnHints.any { containsHint(hay, it) } ||
            (nearbyChecked == true) ||
            (node.optBoolean("checkable", false) && node.optBoolean("checked", false))
        return when {
            looksOff && !looksOn -> SwitchState.OFF
            looksOn && !looksOff -> SwitchState.ON
            nearbyChecked == true -> SwitchState.ON
            nearbyChecked == false -> SwitchState.OFF
            node.optBoolean("checkable", false) -> {
                if (node.optBoolean("checked", false)) SwitchState.ON else SwitchState.OFF
            }
            else -> SwitchState.UNKNOWN
        }
    }

    private fun collectVisibleTextsFromNodes(nodes: JSONArray): List<String> {
        val out = ArrayList<String>()
        for (i in 0 until nodes.length()) {
            val node = nodes.optJSONObject(i) ?: continue
            val text = node.optString("text").trim()
            val desc = node.optString("desc").trim()
            if (text.isNotEmpty()) out.add(text)
            if (desc.isNotEmpty()) out.add(desc)
        }
        return out
    }

    private fun ensureWirelessEnabled(context: Context) {
        if (DeveloperOptionsHelper.isWirelessAdbEnabled(context)) {
            Log.i(TAG, "wireless adb already enabled in Settings.Global")
            return
        }
        for (round in 0 until 3) {
            val row = findWirelessRow()
            if (row != null) {
                val isOn = row.state == SwitchState.ON
                Log.i(TAG, "ensureWireless round=$round rowState=${row.state}")
                if (isOn) {
                    // UI 显示开但 Global 尚未刷新，稍等
                    sleep(600)
                    if (DeveloperOptionsHelper.isWirelessAdbEnabled(context)) {
                        return
                    }
                } else {
                    AccessibilityControlBridge.click(row.switchX, row.switchY)
                    Log.i(TAG, "toggled wireless switch at ${row.switchX},${row.switchY}")
                    sleep(800)
                    if (DeveloperOptionsHelper.isWirelessAdbEnabled(context)) {
                        return
                    }
                    continue
                }
            }
            // 详情页顶栏开关兜底：仅 Global 关且页面不像已开
            val tree = AccessibilityControlBridge.dumpUiTree(maxDepth = 8, maxNodes = 200, mode = "full")
            val screenW = tree.optInt("screenWidth", 0).takeIf { it > 0 } ?: 1080
            val screenH = tree.optInt("screenHeight", 0).takeIf { it > 0 } ?: 1920
            val pageTexts = collectVisibleTexts()
            val pageLooksOn = switchOnHints.any { hint -> pageTexts.any { containsHint(it, hint) } }
            val onWirelessPage = wirelessPageHints().any { hint ->
                pageTexts.any { it.contains(hint, ignoreCase = true) }
            }
            if (onWirelessPage && !pageLooksOn) {
                AccessibilityControlBridge.click(screenW * 0.88f, screenH * 0.18f)
                Log.i(TAG, "tapped top-right fallback switch")
                sleep(800)
            } else {
                return
            }
            if (DeveloperOptionsHelper.isWirelessAdbEnabled(context)) {
                return
            }
        }
    }

    private fun isPairPortLabel(text: String): Boolean {
        val lower = text.lowercase()
        return lower.contains("pair") ||
            text.contains("配对") ||
            text.contains("配對") ||
            text.contains("페어링")
    }

    private fun parseConnectEndpoint(texts: List<String>): Pair<String, Int>? {
        for (text in texts) {
            val match = IP_PORT_REGEX.find(text) ?: continue
            if (isPairPortLabel(text)) {
                continue
            }
            return match.groupValues[1] to match.groupValues[2].toInt()
        }
        return null
    }

    private fun scrapeConnectEndpoint(): Pair<String, Int>? {
        repeat(4) { index ->
            val found = parseConnectEndpoint(collectVisibleTexts())
            if (found != null) {
                Log.i(TAG, "connect endpoint ${found.first}:${found.second} scroll=$index")
                return found
            }
            scrollDown()
            sleep(400)
        }
        return parseConnectEndpoint(collectVisibleTexts())
    }

    private fun scrapePairingInfo(): JSONObject {
        val tree = AccessibilityControlBridge.dumpUiTree(maxDepth = 24, maxNodes = 2000, mode = "full")
        val nodes = tree.optJSONArray("nodes")
        val texts = ArrayList<String>()
        if (nodes != null) {
            for (i in 0 until nodes.length()) {
                val node = nodes.optJSONObject(i) ?: continue
                val text = node.optString("text").trim()
                val desc = node.optString("desc").trim()
                if (text.isNotEmpty()) texts.add(text)
                if (desc.isNotEmpty()) texts.add(desc)
            }
        }
        val blob = texts.joinToString("\n")
        val ipPorts = IP_PORT_REGEX.findAll(blob).map { it.groupValues[1] to it.groupValues[2].toInt() }.toList()
        val codes = CODE_REGEX.findAll(blob).map { it.groupValues[1] }.distinct().toList()

        var pairPort = 0
        var connectPort = 0
        var ip = ""
        for (text in texts) {
            val m = IP_PORT_REGEX.find(text) ?: continue
            val host = m.groupValues[1]
            val port = m.groupValues[2].toInt()
            if (ip.isBlank()) {
                ip = host
            }
            val lower = text.lowercase()
            if (isPairPortLabel(text) || lower.contains("pair")) {
                pairPort = port
            } else {
                connectPort = port
            }
        }
        if (ip.isBlank() && ipPorts.isNotEmpty()) {
            ip = ipPorts[0].first
        }
        if (pairPort <= 0 && ipPorts.isNotEmpty()) {
            pairPort = ipPorts.firstOrNull { (_, p) -> p != connectPort }?.second
                ?: ipPorts[0].second
        }

        // 配对码：优先不含端口行里的 6 位
        val pairCode = codes.firstOrNull { code ->
            texts.none { it.contains(code) && IP_PORT_REGEX.containsMatchIn(it) }
        } ?: codes.firstOrNull().orEmpty()

        return JSONObject()
            .put("ip", ip)
            .put("pairPort", pairPort)
            .put("pairCode", pairCode)
            .put("connectPort", connectPort)
            .put("textHits", texts.size)
            .put("ipPortHits", ipPorts.size)
            .put("codeHits", codes.size)
    }

    private fun findAndClickWithScroll(labels: List<String>): Boolean {
        for (round in 0 until FIND_ROUNDS) {
            if (clickFirstMatching(labels)) {
                return true
            }
            // 先小幅上滑（防止已经滚过头），再连续下滑
            if (round == 0) {
                scrollUp()
                sleep(400)
                if (clickFirstMatching(labels)) {
                    return true
                }
            }
            for (scroll in 0 until SCROLL_ATTEMPTS) {
                scrollDown()
                sleep(450)
                if (clickFirstMatching(labels)) {
                    Log.i(TAG, "found label after scroll round=$round scroll=$scroll")
                    return true
                }
            }
        }
        return false
    }

    private fun clickFirstMatching(labels: List<String>): Boolean {
        val tree = AccessibilityControlBridge.dumpUiTree(maxDepth = 22, maxNodes = 1600, mode = "full")
        val nodes = tree.optJSONArray("nodes") ?: return false
        // 优先更短、更精确的匹配（完整标题优先于长段落）
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
            val x = (left + right) / 2f
            val y = (top + bottom) / 2f
            val score = matched.length * 10 - (hay.length.coerceAtMost(80))
            hits.add(Hit(score, x, y))
        }
        hits.sortByDescending { it.score }
        for (hit in hits) {
            val clicked = AccessibilityControlBridge.click(hit.x, hit.y)
            if (clicked.ok) {
                return true
            }
        }
        return false
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

    private fun scrollUp() {
        val tree = AccessibilityControlBridge.dumpUiTree(maxDepth = 4, maxNodes = 40, mode = "full")
        val w = tree.optInt("screenWidth", 1080).takeIf { it > 0 } ?: 1080
        val h = tree.optInt("screenHeight", 1920).takeIf { it > 0 } ?: 1920
        val x = w / 2f
        val mid = h * 0.52f
        val half = h * SCROLL_SPAN / 2f
        AccessibilityControlBridge.swipe(x, mid - half, x, mid + half, SCROLL_DURATION_MS)
    }

    private fun collectVisibleTexts(): List<String> {
        val tree = AccessibilityControlBridge.dumpUiTree(maxDepth = 20, maxNodes = 1200, mode = "full")
        val nodes = tree.optJSONArray("nodes") ?: return emptyList()
        val out = ArrayList<String>()
        for (i in 0 until nodes.length()) {
            val node = nodes.optJSONObject(i) ?: continue
            val text = node.optString("text").trim()
            val desc = node.optString("desc").trim()
            if (text.isNotEmpty()) out.add(text)
            if (desc.isNotEmpty()) out.add(desc)
        }
        return out
    }

    private fun sampleVisibleTexts(limit: Int): String {
        return collectVisibleTexts().distinct().take(limit).joinToString(" | ")
    }

    private fun snapshotMeta(reason: String): JSONObject {
        return JSONObject().put("reason", reason)
    }

    private fun sleep(ms: Long) {
        try {
            Thread.sleep(ms)
        } catch (_: InterruptedException) {
            Thread.currentThread().interrupt()
        }
    }
}
