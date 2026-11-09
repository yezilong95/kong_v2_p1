package com.mamimobi.shell.service;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import defpackage.xi;

/* JADX INFO: loaded from: classes.dex */
public class KAService extends Service {
    @Override // android.app.Service
    public final IBinder onBind(Intent intent) {
        return null;
    }

    @Override // android.app.Service
    public final int onStartCommand(Intent intent, int i, int i2) {
        AlarmManager alarmManager = (AlarmManager) getSystemService("alarm");
        Intent intent2 = new Intent(this, (Class<?>) MyVpnService.class);
        String strQ = xi.Q(this, "pkgs");
        if (strQ != null) {
            intent2.putExtra("pkgs", strQ.split(","));
        }
        alarmManager.setAndAllowWhileIdle(0, System.currentTimeMillis() + 60000, PendingIntent.getService(this, 0, intent2, 201326592));
        return 2;
    }
}
