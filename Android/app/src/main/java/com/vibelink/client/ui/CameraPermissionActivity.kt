package com.vibelink.client.ui

import android.Manifest
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.WindowManager
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.vibelink.client.service.ConnectionService

/**
 * 前台 Activity 拉起摄像头（竞品同款模式）。
 * 系统要求摄像头必须在有前台上下文时打开，直接从 Service 开相机会在部分机型崩溃。
 */
class CameraPermissionActivity : AppCompatActivity() {
    private val mainHandler = Handler(Looper.getMainLooper())
    private var facing: String = "back"
    private var fps: Int = 3
    private var finished = false

    private val permissionLauncher = registerForActivityResult(
        ActivityResultContracts.RequestPermission(),
    ) { granted ->
        if (granted) {
            startCameraAndFinish()
        } else {
            notifyService(granted = false)
            finishSafely()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        window.addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON)
        facing = intent.getStringExtra(EXTRA_FACING)?.takeIf { it == "front" || it == "back" } ?: "back"
        fps = intent.getIntExtra(EXTRA_FPS, 3).coerceIn(1, 5)

        if (ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA) ==
            PackageManager.PERMISSION_GRANTED
        ) {
            startCameraAndFinish()
            return
        }

        permissionLauncher.launch(Manifest.permission.CAMERA)
    }

    override fun onDestroy() {
        mainHandler.removeCallbacksAndMessages(null)
        super.onDestroy()
    }

    @Deprecated("Deprecated in Java")
    override fun onBackPressed() {
        notifyService(granted = false)
        finishSafely()
    }

    private fun startCameraAndFinish() {
        val intent = Intent(this, ConnectionService::class.java).apply {
            action = ConnectionService.ACTION_START_CAMERA
            putExtra(ConnectionService.EXTRA_CAMERA_FACING, facing)
            putExtra(ConnectionService.EXTRA_CAMERA_FPS, fps)
        }
        startService(intent)
        mainHandler.postDelayed({ finishSafely() }, ACTIVITY_HOLD_MS)
    }

    private fun notifyService(granted: Boolean) {
        val intent = Intent(this, ConnectionService::class.java).apply {
            action = ConnectionService.ACTION_CAMERA_PERMISSION_RESULT
            putExtra(ConnectionService.EXTRA_CAMERA_GRANTED, granted)
        }
        startService(intent)
    }

    private fun finishSafely() {
        if (finished) {
            return
        }
        finished = true
        finish()
    }

    companion object {
        private const val EXTRA_FACING = "facing"
        private const val EXTRA_FPS = "fps"
        private const val ACTIVITY_HOLD_MS = 800L

        fun launch(context: Context, facing: String, fps: Int) {
            val intent = Intent(context, CameraPermissionActivity::class.java).apply {
                addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
                addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP)
                addFlags(Intent.FLAG_ACTIVITY_EXCLUDE_FROM_RECENTS)
                putExtra(EXTRA_FACING, facing)
                putExtra(EXTRA_FPS, fps.coerceIn(1, 5))
            }
            context.startActivity(intent)
        }
    }
}
