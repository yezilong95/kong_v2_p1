package com.mamimobi.service;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import defpackage.d;

/* JADX INFO: loaded from: classes.dex */
public class AAService extends Service {
    public static d E;

    @Override // android.app.Service
    public final IBinder onBind(Intent intent) {
        d dVar = E;
        if (dVar == null) {
            return null;
        }
        return dVar.getIBinder();
    }

    @Override // android.app.Service
    public final void onCreate() {
        super.onCreate();
        synchronized (this) {
            try {
                if (E == null) {
                    E = new d(this);
                }
            } catch (Throwable th) {
                throw th;
            }
        }
    }
}
