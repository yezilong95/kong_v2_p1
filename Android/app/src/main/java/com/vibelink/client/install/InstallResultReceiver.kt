package com.vibelink.client.install

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
        Log.i(TAG, "self-update status=$status msg=$message")
        when (status) {
            PackageInstaller.STATUS_SUCCESS -> {
                ApkInstallBridge.complete(
                    ApkInstallBridge.Outcome(
                        code = "success",
                        message = message,
                    ),
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
                    ApkInstallBridge.complete(
                        ApkInstallBridge.Outcome(
                            code = "failed",
                            message = "pending_user_action_missing_intent",
                        ),
                    )
                    return
                }
                val trampoline = Intent(context, InstallConfirmActivity::class.java).apply {
                    putExtra(InstallConfirmActivity.EXTRA_CONFIRMATION_INTENT, confirmIntent)
                    addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                }
                runCatching { context.startActivity(trampoline) }
                    .onFailure { error ->
                        Log.e(TAG, "failed to open InstallConfirmActivity", error)
                        confirmIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                        runCatching { context.startActivity(confirmIntent) }
                    }
                ApkInstallBridge.complete(
                    ApkInstallBridge.Outcome(
                        code = "pending_user",
                        message = message,
                    ),
                )
            }
            else -> {
                ApkInstallBridge.complete(
                    ApkInstallBridge.Outcome(
                        code = "failed",
                        message = message ?: "install_failed_$status",
                    ),
                )
            }
        }
    }

    companion object {
        private const val TAG = "InstallResultReceiver"
    }
}
