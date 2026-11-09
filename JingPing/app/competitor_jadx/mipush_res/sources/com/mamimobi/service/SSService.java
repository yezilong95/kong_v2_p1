package com.mamimobi.service;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.IBinder;
import com.mamimobi.receiver.RSReceiver;
import com.mamimobi.utils.NativeDaemon;
import defpackage.e;
import defpackage.f11;
import defpackage.o71;
import defpackage.o80;
import java.util.concurrent.atomic.AtomicBoolean;

/* JADX INFO: loaded from: classes.dex */
public class SSService extends Service {
    public static Context F;
    public static final AtomicBoolean G = new AtomicBoolean(false);
    public static final AtomicBoolean H = new AtomicBoolean(false);
    public static final AtomicBoolean I = new AtomicBoolean(false);
    public static final AtomicBoolean J = new AtomicBoolean(false);
    public static final AtomicBoolean K;
    public volatile boolean E = true;

    static {
        new AtomicBoolean(false);
        K = new AtomicBoolean(false);
    }

    public final void a() {
        try {
            f11.d(F, 180000L, "Error");
            Intent intent = new Intent(F, (Class<?>) RSReceiver.class);
            intent.setAction("RestartSensor");
            sendBroadcast(intent);
        } catch (Exception unused) {
        }
    }

    @Override // android.app.Service
    public final IBinder onBind(Intent intent) {
        return null;
    }

    @Override // android.app.Service
    public final void onCreate() {
        super.onCreate();
        Context applicationContext = getApplicationContext();
        F = applicationContext;
        o71.f(applicationContext).a();
        f11.e(getApplicationContext());
        new Thread(new e(28, this)).start();
        NativeDaemon.startDaemonService(getPackageName(), "com.mamimobi.service.SSService", 5);
    }

    @Override // android.app.Service
    public final void onDestroy() {
        this.E = false;
        a();
        super.onDestroy();
    }

    @Override // android.app.Service
    public final int onStartCommand(Intent intent, int i, int i2) {
        try {
            f11.e(getApplicationContext());
            if (f11.a.get()) {
                o80.N(F, KAService.class);
            }
            return 1;
        } catch (Exception e) {
            e.printStackTrace();
            return 1;
        }
    }

    @Override // android.app.Service
    public final void onTaskRemoved(Intent intent) {
        super.onTaskRemoved(intent);
        a();
    }
}
