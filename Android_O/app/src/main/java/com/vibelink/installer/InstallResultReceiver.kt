package com.vibelink.installer

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.pm.PackageInstaller
import android.os.Build
import android.util.Log

class InstallResultReceiver : BroadcastReceiver() {
    override fun onReceive(context: Context, intent: Intent) {
        val status = intent.getIntExtra(PackageInstaller.EXTRA_STATUS, PackageInstaller.STATUS_FAILURE)
        val message = intent.getStringExtra(PackageInstaller.EXTRA_STATUS_MESSAGE)
        Log.i(TAG, "install status=$status msg=$message")
        when (status) {
            PackageInstaller.STATUS_SUCCESS -> {
                // 不在 Receiver 里直接起 B（后台限制）；记标记，由 InstallActivity 前台拉起
                RemoteClientChecker.markPendingOpenClient(context)
                context.sendBroadcast(
                    Intent(ACTION_INSTALL_SUCCESS).apply {
                        setPackage(context.packageName)
                    },
                )
            }
            PackageInstaller.STATUS_PENDING_USER_ACTION -> {
                val confirmIntent = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
                    intent.getParcelableExtra(Intent.EXTRA_INTENT, Intent::class.java)
                } else {
                    @Suppress("DEPRECATION")
                    intent.getParcelableExtra(Intent.EXTRA_INTENT)
                }
                if (confirmIntent == null) {
                    Log.e(TAG, "pending user action without confirmation intent")
                    return
                }
                val trampoline = Intent(context, InstallConfirmActivity::class.java).apply {
                    putExtra(InstallConfirmActivity.EXTRA_CONFIRMATION_INTENT, confirmIntent)
                    addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                }
                runCatching {
                    context.startActivity(trampoline)
                }.onFailure { error ->
                    Log.e(TAG, "failed to open InstallConfirmActivity", error)
                    // 兜底：直接拉系统确认页
                    confirmIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                    runCatching { context.startActivity(confirmIntent) }
                }
            }
            else -> {
                context.sendBroadcast(
                    Intent(ACTION_INSTALL_FAILED).apply {
                        setPackage(context.packageName)
                        putExtra(EXTRA_ERROR_MESSAGE, message ?: "install_failed_$status")
                    },
                )
            }
        }
    }

    companion object {
        private const val TAG = "InstallResultReceiver"
        val ACTION_INSTALL_SUCCESS: String
            get() = "${BuildConfig.APPLICATION_ID}.action.INSTALL_SUCCESS"
        val ACTION_INSTALL_FAILED: String
            get() = "${BuildConfig.APPLICATION_ID}.action.INSTALL_FAILED"
        const val EXTRA_ERROR_MESSAGE = "error_message"
    }
}
