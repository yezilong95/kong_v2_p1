package com.mamimobi.service;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import defpackage.xp0;

/* JADX INFO: loaded from: classes.dex */
public class SyncService extends Service {
    public static xp0 E;
    public static final Object F = new Object();

    @Override // android.app.Service
    public final IBinder onBind(Intent intent) {
        return E.getSyncAdapterBinder();
    }

    @Override // android.app.Service
    public final void onCreate() {
        super.onCreate();
        synchronized (F) {
            E = new xp0(getApplicationContext(), true);
        }
    }
}
