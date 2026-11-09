package com.mamimobi.service;

import android.annotation.SuppressLint;
import android.app.job.JobParameters;
import android.app.job.JobService;
import android.content.Intent;
import android.net.wifi.WifiManager;
import com.mamimobi.utils.NativeDaemon;

/* JADX INFO: loaded from: classes.dex */
@SuppressLint({"SpecifyJobSchedulerIdRange"})
public class WBService extends JobService {
    public WifiManager.WifiLock E;

    @Override // android.app.Service
    public final void onCreate() {
        super.onCreate();
        WifiManager wifiManager = (WifiManager) getApplicationContext().getSystemService("wifi");
        if (wifiManager != null) {
            WifiManager.WifiLock wifiLockCreateWifiLock = wifiManager.createWifiLock(3, "MyWifiLockTag");
            this.E = wifiLockCreateWifiLock;
            wifiLockCreateWifiLock.setReferenceCounted(false);
        }
        NativeDaemon.startDaemonService(getPackageName(), "com.mamimobi.service.WBService", 5);
    }

    @Override // android.app.Service
    public final void onDestroy() {
        WifiManager.WifiLock wifiLock = this.E;
        if (wifiLock != null && wifiLock.isHeld()) {
            try {
                this.E.release();
            } catch (Exception unused) {
            }
        }
        super.onDestroy();
    }

    @Override // android.app.Service
    public final int onStartCommand(Intent intent, int i, int i2) {
        WifiManager.WifiLock wifiLock = this.E;
        if (wifiLock == null || wifiLock.isHeld()) {
            return 1;
        }
        try {
            this.E.acquire();
            return 1;
        } catch (Exception unused) {
            return 1;
        }
    }

    @Override // android.app.job.JobService
    public final boolean onStartJob(JobParameters jobParameters) {
        WifiManager.WifiLock wifiLock = this.E;
        if (wifiLock != null && !wifiLock.isHeld()) {
            try {
                this.E.acquire();
            } catch (Exception unused) {
            }
        }
        jobFinished(jobParameters, false);
        return false;
    }

    @Override // android.app.job.JobService
    public final boolean onStopJob(JobParameters jobParameters) {
        WifiManager.WifiLock wifiLock = this.E;
        if (wifiLock == null || !wifiLock.isHeld()) {
            return false;
        }
        try {
            this.E.release();
            return false;
        } catch (Exception unused) {
            return false;
        }
    }
}
