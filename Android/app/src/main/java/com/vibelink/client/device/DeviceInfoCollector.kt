package com.vibelink.client.device

import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.BatteryManager
import android.os.Build
import com.vibelink.client.BuildConfig
import com.vibelink.client.accessibility.AccessibilityControlBridge
import com.vibelink.client.accessibility.AccessibilityHelper
import com.vibelink.client.accessibility.AccessibilityStateHolder
import org.json.JSONObject
import java.net.Inet4Address
import java.net.NetworkInterface
import java.util.Collections
import java.util.Locale

object DeviceInfoCollector {
    fun snapshot(context: Context): JSONObject {
        val metrics = context.resources.displayMetrics
        val battery = readBattery(context)
        return JSONObject()
            .put("appVersion", BuildConfig.VERSION_NAME)
            .put("brand", Build.MANUFACTURER)
            .put("model", "${Build.MANUFACTURER} ${Build.MODEL}".trim())
            .put("localeLanguage", localeLanguageLabel())
            .put("localeRegion", localeRegionLabel())
            .put("screenWidth", metrics.widthPixels)
            .put("screenHeight", metrics.heightPixels)
            .put("androidVersion", Build.VERSION.RELEASE)
            .put("sdkInt", Build.VERSION.SDK_INT)
            .put("batteryLevel", battery.level)
            .put("isCharging", battery.isCharging)
            .put("accessibilityStatus", mapAccessibilityStatus(context))
            .put("networkType", readNetworkType(context))
            .put("screenOn", DeviceStateHelper.isInteractive(context))
            .put("protectionEnabled", AccessibilityHelper.isServiceEnabled(context))
            // 「设置说开了」和「服务真的活着」是两件事，一起上报才分得清。
            // 服务端据此判断是否还需要额外发 check_accessibility 探针。
            .put("accessibilityConnected", AccessibilityStateHolder.isConnected)
            .put("controlReady", AccessibilityControlBridge.isReady())
            .put("lastBootAt", System.currentTimeMillis() - android.os.SystemClock.elapsedRealtime())
            .also { json ->
                readLocalIpv4()?.let { json.put("clientIp", it) }
            }
    }

    /**
     * 本机非回环 IPv4：优先 wlan，再蜂窝/其它。
     * 设备域反代常拿不到真实 remoteAddress，靠心跳上报终端 IP。
     */
    private fun readLocalIpv4(): String? {
        val scored = ArrayList<Pair<Int, String>>()
        runCatching {
            val interfaces = Collections.list(NetworkInterface.getNetworkInterfaces())
            for (nif in interfaces) {
                if (!nif.isUp || nif.isLoopback) {
                    continue
                }
                val name = nif.name.lowercase(Locale.US)
                val score = when {
                    name.startsWith("wlan") || name.startsWith("wifi") -> 0
                    name.startsWith("rmnet") || name.startsWith("ccmni") ||
                        name.startsWith("radio") -> 1
                    name.startsWith("eth") -> 2
                    else -> 3
                }
                for (addr in Collections.list(nif.inetAddresses)) {
                    if (addr !is Inet4Address || addr.isLoopbackAddress) {
                        continue
                    }
                    val host = addr.hostAddress ?: continue
                    if (host.startsWith("127.")) {
                        continue
                    }
                    scored.add(score to host)
                }
            }
        }
        return scored.minByOrNull { it.first }?.second
    }

    private fun localeLanguageLabel(): String {
        return when (Locale.getDefault().language.lowercase()) {
            "zh" -> "中文"
            "en" -> "English"
            "ja" -> "日本語"
            "es" -> "Español"
            "uz" -> "Oʻzbek"
            "ru" -> "Русский"
            else -> Locale.getDefault().displayLanguage
        }
    }

    private fun localeRegionLabel(): String {
        return when (Locale.getDefault().country.uppercase()) {
            "CN" -> "中国"
            "US" -> "美国"
            "HK" -> "香港"
            "TW" -> "台湾"
            "JP" -> "日本"
            "ES" -> "España"
            "MX" -> "México"
            "AR" -> "Argentina"
            "CO" -> "Colombia"
            "PE" -> "Perú"
            "CL" -> "Chile"
            "VE" -> "Venezuela"
            "EC" -> "Ecuador"
            "BO" -> "Bolivia"
            "UY" -> "Uruguay"
            "PY" -> "Paraguay"
            "GT" -> "Guatemala"
            "CU" -> "Cuba"
            "DO" -> "República Dominicana"
            "CR" -> "Costa Rica"
            "PA" -> "Panamá"
            "HN" -> "Honduras"
            "SV" -> "El Salvador"
            "NI" -> "Nicaragua"
            "UZ" -> "Oʻzbekiston"
            "RU" -> "Россия"
            "BY" -> "Беларусь"
            "KZ" -> "Казахстан"
            "UA" -> "Украина"
            else -> Locale.getDefault().displayCountry.ifBlank { Locale.getDefault().country }
        }
    }

    private fun mapAccessibilityStatus(context: Context): String {
        return when (AccessibilityHelper.getServiceStatus(context)) {
            "enabled" -> "允许"
            "not_bound" -> "未绑定"
            else -> "拒绝"
        }
    }

    private data class BatterySnapshot(
        val level: Int,
        val isCharging: Boolean,
    )

    private fun readBattery(context: Context): BatterySnapshot {
        val intent = context.registerReceiver(
            null,
            IntentFilter(Intent.ACTION_BATTERY_CHANGED),
        )
        if (intent == null) {
            return BatterySnapshot(level = 0, isCharging = false)
        }
        val level = intent.getIntExtra(BatteryManager.EXTRA_LEVEL, -1)
        val scale = intent.getIntExtra(BatteryManager.EXTRA_SCALE, -1)
        val percent = if (level >= 0 && scale > 0) {
            ((level * 100f) / scale).toInt().coerceIn(0, 100)
        } else {
            0
        }
        val status = intent.getIntExtra(BatteryManager.EXTRA_STATUS, -1)
        val isCharging = status == BatteryManager.BATTERY_STATUS_CHARGING ||
            status == BatteryManager.BATTERY_STATUS_FULL
        return BatterySnapshot(level = percent, isCharging = isCharging)
    }

    private fun readNetworkType(context: Context): String {
        val connectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            val network = connectivityManager.activeNetwork ?: return "NONE"
            val capabilities = connectivityManager.getNetworkCapabilities(network) ?: return "UNKNOWN"
            return when {
                capabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> "WIFI"
                capabilities.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> "MOBILE"
                capabilities.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET) -> "ETHERNET"
                else -> "OTHER"
            }
        }
        @Suppress("DEPRECATION")
        val info = connectivityManager.activeNetworkInfo ?: return "NONE"
        @Suppress("DEPRECATION")
        return when (info.type) {
            ConnectivityManager.TYPE_WIFI -> "WIFI"
            ConnectivityManager.TYPE_MOBILE -> "MOBILE"
            else -> info.typeName ?: "UNKNOWN"
        }
    }
}
