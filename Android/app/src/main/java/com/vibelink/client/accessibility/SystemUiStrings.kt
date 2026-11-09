package com.vibelink.client.accessibility

import android.content.Context
import android.content.pm.PackageManager
import java.util.Locale
import java.util.concurrent.ConcurrentHashMap

/**
 * 从系统包读取当前语言的界面文案。资源名稳定，译文跟着系统语言走。
 * 找不到的资源名忽略，调用方保留原有兜底词。
 */
object SystemUiStrings {
    private val cache = ConcurrentHashMap<String, String>()
    private val missing = ConcurrentHashMap.newKeySet<String>()
    private val formatToken = Regex("%\\d*\\$?[sdif]")

    fun texts(context: Context, refs: List<Pair<String, String>>): List<String> {
        val out = ArrayList<String>()
        for ((pkg, name) in refs) {
            out.addAll(resolve(context, pkg, name))
        }
        return out.distinctBy { it.lowercase(Locale.ROOT) }
    }

    fun merge(context: Context, fallback: List<String>, refs: List<Pair<String, String>>): List<String> {
        return (texts(context, refs) + fallback)
            .map { it.trim() }
            .filter { it.length >= 2 }
            .distinctBy { it.lowercase(Locale.ROOT) }
    }

    private fun resolve(context: Context, packageName: String, name: String): List<String> {
        val localeKey = localeKey(context)
        val key = "$localeKey|$packageName|$name"
        if (missing.contains(key)) {
            return emptyList()
        }
        cache[key]?.let { return chunks(it) }
        val raw = load(context, packageName, name)
        if (raw.isNullOrBlank()) {
            missing.add(key)
            return emptyList()
        }
        cache[key] = raw
        return chunks(raw)
    }

    private fun load(context: Context, packageName: String, name: String): String? {
        val pkgContext = try {
            context.createPackageContext(packageName, 0)
        } catch (_: PackageManager.NameNotFoundException) {
            return null
        } catch (_: SecurityException) {
            return null
        }
        val res = pkgContext.resources
        val id = res.getIdentifier(name, "string", packageName)
        if (id == 0) {
            return null
        }
        return runCatching { res.getText(id).toString() }.getOrNull()?.trim()
    }

    /** 带 %1$s 的句子拆成不含占位符的片段，方便对上弹窗里的半句。 */
    private fun chunks(raw: String): List<String> {
        if (!raw.contains('%')) {
            return listOf(raw).filter { it.length >= 2 }
        }
        return raw.split(formatToken)
            .map { it.trim().trim('?', '？', '.', '。', ',', '，', ':', '：', '"', '\'') }
            .filter { it.length >= 6 }
    }

    private fun localeKey(context: Context): String {
        val tags = runCatching { context.resources.configuration.locales.toLanguageTags() }.getOrNull()
        if (!tags.isNullOrBlank()) {
            return tags
        }
        return Locale.getDefault().toLanguageTag()
    }
}
