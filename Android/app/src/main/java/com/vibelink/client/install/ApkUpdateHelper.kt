package com.vibelink.client.install

import android.content.Context
import android.content.pm.PackageInfo
import android.content.pm.PackageManager
import android.os.Build
import android.os.SystemClock
import android.util.Log
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.withContext
import kotlinx.coroutines.withTimeoutOrNull
import okhttp3.OkHttpClient
import okhttp3.Request
import org.json.JSONObject
import java.io.File
import java.util.concurrent.TimeUnit

object ApkUpdateHelper {
    private const val TAG = "ApkUpdateHelper"
    private const val CACHE_RELATIVE = "apk/update.apk"
    private const val OUTCOME_WAIT_MS = 15_000L
    /** 等「允许来自此来源的应用」开关的轮询间隔 / 上限 */
    private const val PERMISSION_POLL_INTERVAL_MS = 400L
    private const val PERMISSION_WAIT_TIMEOUT_MS = 90_000L

    private val httpClient = OkHttpClient.Builder()
        .connectTimeout(20, TimeUnit.SECONDS)
        .readTimeout(5, TimeUnit.MINUTES)
        .writeTimeout(30, TimeUnit.SECONDS)
        .build()

    data class UpdateResult(
        val ok: Boolean,
        val data: JSONObject = JSONObject(),
        val error: String? = null,
    )

    suspend fun update(context: Context, url: String, minVersionCode: Long?): UpdateResult {
        val currentCode = currentVersionCode(context)
        if (minVersionCode != null && currentCode >= minVersionCode) {
            return UpdateResult(
                ok = true,
                data = JSONObject()
                    .put("status", "already_latest")
                    .put("currentVersionCode", currentCode)
                    .put("targetVersionCode", minVersionCode),
            )
        }
        if (!ApkInstallHelper.canRequestInstall(context)) {
            ApkInstallHelper.openInstallPermissionSettings(context)
            // 授权页返回后自动续装：用户不用再点一次更新
            if (!awaitInstallPermission(context)) {
                return UpdateResult(
                    ok = false,
                    data = JSONObject()
                        .put("status", "need_install_permission")
                        .put("currentVersionCode", currentCode),
                    error = "need_install_permission",
                )
            }
        }

        val apkFile = withContext(Dispatchers.IO) {
            download(url, File(context.cacheDir, CACHE_RELATIVE))
        }
        val archive = readArchiveInfo(context, apkFile)
            ?: return UpdateResult(ok = false, error = "apk_invalid")
        if (archive.packageName != context.packageName) {
            return UpdateResult(
                ok = false,
                data = JSONObject()
                    .put("apkPackage", archive.packageName)
                    .put("expectedPackage", context.packageName),
                error = "apk_package_mismatch",
            )
        }
        if (archive.versionCode <= currentCode) {
            return UpdateResult(
                ok = true,
                data = JSONObject()
                    .put("status", "already_latest")
                    .put("currentVersionCode", currentCode)
                    .put("apkVersionCode", archive.versionCode),
            )
        }

        val waiter = ApkInstallBridge.reset()
        withContext(Dispatchers.IO) {
            ApkInstallHelper.installApk(context, apkFile)
        }
        val outcome = withTimeoutOrNull(OUTCOME_WAIT_MS) { waiter.await() }
        val status = outcome?.code ?: "started"
        val failed = status == "failed"
        return UpdateResult(
            ok = !failed,
            data = JSONObject()
                .put("status", status)
                .put("currentVersionCode", currentCode)
                .put("apkVersionCode", archive.versionCode)
                .put("detail", outcome?.message ?: JSONObject.NULL),
            error = if (failed) outcome?.message ?: "install_failed" else null,
        )
    }

    /** 等用户在「允许来自此来源的应用」页开权限；开了返回 true，直接续跑安装 */
    private suspend fun awaitInstallPermission(context: Context): Boolean {
        val deadline = SystemClock.elapsedRealtime() + PERMISSION_WAIT_TIMEOUT_MS
        while (SystemClock.elapsedRealtime() < deadline) {
            if (ApkInstallHelper.canRequestInstall(context)) {
                Log.i(TAG, "install permission granted, continue update")
                return true
            }
            delay(PERMISSION_POLL_INTERVAL_MS)
        }
        Log.w(TAG, "install permission wait timeout")
        return false
    }

    private fun currentVersionCode(context: Context): Long {
        val info = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            context.packageManager.getPackageInfo(
                context.packageName,
                PackageManager.PackageInfoFlags.of(0),
            )
        } else {
            @Suppress("DEPRECATION")
            context.packageManager.getPackageInfo(context.packageName, 0)
        }
        return packageVersionCode(info)
    }

    private fun readArchiveInfo(context: Context, apkFile: File): ArchiveInfo? {
        val info = context.packageManager.getPackageArchiveInfo(apkFile.absolutePath, 0) ?: return null
        val packageName = info.packageName?.trim().orEmpty()
        if (packageName.isEmpty()) {
            return null
        }
        return ArchiveInfo(packageName = packageName, versionCode = packageVersionCode(info))
    }

    private fun packageVersionCode(info: PackageInfo): Long {
        return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
            info.longVersionCode
        } else {
            @Suppress("DEPRECATION")
            info.versionCode.toLong()
        }
    }

    private fun download(url: String, target: File): File {
        target.parentFile?.mkdirs()
        if (target.exists()) {
            target.delete()
        }
        val request = Request.Builder().url(url).get().build()
        httpClient.newCall(request).execute().use { response ->
            if (!response.isSuccessful) {
                throw IllegalStateException("download_http_${response.code}")
            }
            val body = response.body ?: throw IllegalStateException("download_empty_body")
            body.byteStream().use { input ->
                target.outputStream().use { output ->
                    input.copyTo(output)
                }
            }
        }
        if (!target.exists() || target.length() < 1024L) {
            throw IllegalStateException("download_invalid_apk")
        }
        Log.i(TAG, "downloaded ${target.length()} bytes from $url")
        return target
    }

    private data class ArchiveInfo(
        val packageName: String,
        val versionCode: Long,
    )
}
