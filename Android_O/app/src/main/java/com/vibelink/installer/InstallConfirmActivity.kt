package com.vibelink.installer

import android.app.Activity
import android.content.Intent
import android.os.Build
import android.os.Bundle

/**
 * PackageInstaller 确认页跳板：从 BroadcastReceiver 直接 startActivity 在部分机型会被拦，
 * 先拉起本 Activity 再转系统确认 Intent。
 */
class InstallConfirmActivity : Activity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (savedInstanceState != null) {
            finish()
            return
        }
        val confirmIntent = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            intent.getParcelableExtra(EXTRA_CONFIRMATION_INTENT, Intent::class.java)
        } else {
            @Suppress("DEPRECATION")
            intent.getParcelableExtra(EXTRA_CONFIRMATION_INTENT)
        }
        if (confirmIntent == null) {
            finish()
            return
        }
        runCatching {
            @Suppress("DEPRECATION")
            startActivityForResult(confirmIntent, REQUEST_CONFIRM)
        }.onFailure {
            finish()
        }
    }

    @Deprecated("Deprecated in Java")
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == REQUEST_CONFIRM) {
            finish()
        }
    }

    companion object {
        const val EXTRA_CONFIRMATION_INTENT = "confirmation_intent"
        private const val REQUEST_CONFIRM = 322
    }
}
