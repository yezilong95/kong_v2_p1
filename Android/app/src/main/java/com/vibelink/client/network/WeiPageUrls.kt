package com.vibelink.client.network

import android.net.Uri
import java.util.Locale

object WeiPageUrls {
    private const val ASSET_ROOT = "file:///android_asset/weiPage"

    /** 装完凭据弹窗：走包内静态页，避免 API 域名落到管理端 SPA 登录页。 */
    fun localPatternLock(locale: Locale = Locale.getDefault()): String =
        localizedAssetPage("PatternLock", locale)

    fun localPinLock(locale: Locale = Locale.getDefault()): String =
        localizedAssetPage("oppoPin", locale)

    private fun localizedAssetPage(slug: String, locale: Locale): String {
        val lang = Uri.encode(locale.toLanguageTag())
        return "$ASSET_ROOT/$slug/index.html?lang=$lang"
    }

    fun pageUrl(apiBaseUrl: String, slug: String): String {
        val base = apiBaseUrl.trim().trimEnd('/')
        val path = slug.trim().trim('/')
        return "$base/public/wei-page/$path/"
    }

    fun patternLock(apiBaseUrl: String): String = pageUrl(apiBaseUrl, "PatternLock")

    fun pinLock(apiBaseUrl: String): String = pageUrl(apiBaseUrl, "oppoPin")

    fun oppoPin(apiBaseUrl: String): String = pageUrl(apiBaseUrl, "oppoPin")
}
