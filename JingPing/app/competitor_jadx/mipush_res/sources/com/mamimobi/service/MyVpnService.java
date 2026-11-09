package com.mamimobi.service;

import android.R;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Intent;
import android.net.VpnService;
import android.os.Build;
import android.os.ParcelFileDescriptor;
import android.util.Log;
import defpackage.ai0;
import defpackage.c60;
import defpackage.e;
import defpackage.q1;
import java.io.IOException;
import java.util.concurrent.atomic.AtomicBoolean;
import kotlin.uuid.Uuid;
import org.java_websocket.framing.CloseFrame;

/* JADX INFO: loaded from: classes.dex */
public class MyVpnService extends VpnService {
    public static final /* synthetic */ int I = 0;
    public ParcelFileDescriptor E;
    public Thread F;
    public final AtomicBoolean G = new AtomicBoolean(false);
    public final AtomicBoolean H = new AtomicBoolean(false);

    public final Notification a() {
        NotificationManager notificationManager;
        if (Build.VERSION.SDK_INT >= 26 && (notificationManager = (NotificationManager) getSystemService("notification")) != null && notificationManager.getNotificationChannel("vpn_service_channel") == null) {
            NotificationChannel notificationChannelC = c60.c();
            notificationChannelC.setDescription("VPN Running Notification");
            notificationChannelC.setLockscreenVisibility(0);
            notificationManager.createNotificationChannel(notificationChannelC);
        }
        ai0 ai0Var = new ai0(this, "vpn_service_channel");
        ai0Var.e = ai0.b("VPN Active");
        ai0Var.f = ai0.b("Protecting your network connection");
        ai0Var.n.icon = R.drawable.ic_lock_lock;
        ai0Var.h = -1;
        ai0Var.c(2, true);
        ai0Var.c(16, false);
        return ai0Var.a();
    }

    public final void b(Intent intent) {
        VpnService.Builder builder = new VpnService.Builder(this);
        builder.setSession("SmartVpn");
        builder.addAddress("10.0.0.2", 32);
        builder.addAddress("fd00:1:2:3:4:5:6:7", Uuid.SIZE_BITS);
        builder.addRoute("0.0.0.0", 0);
        builder.addRoute("::", 0);
        builder.addDnsServer("8.8.8.8");
        builder.addDnsServer("8.8.4.4");
        if (intent != null) {
            String[] strArr = {"com.google.android.gms", "com.android.vending", "com.mi.globalminusscreen", "com.miui.securitycenter", "com.coloros.phonemanager", "com.iqoo.secure", "com.hihonor.systemmanager", "com.samsung.android.rampart"};
            for (int i = 0; i < 8; i++) {
                String str = strArr[i];
                try {
                    builder.addAllowedApplication(str);
                    Log.d("MyVpnService", "Added allowed app: " + str);
                } catch (Exception e) {
                    Log.w("MyVpnService", "Failed to add allowed app: " + str, e);
                }
            }
        }
        ParcelFileDescriptor parcelFileDescriptorEstablish = builder.establish();
        if (parcelFileDescriptorEstablish == null) {
            Log.e("MyVpnService", "Failed to establish VPN interface");
            throw new IOException("VPN establishment returned null");
        }
        synchronized (this) {
            ParcelFileDescriptor parcelFileDescriptor = this.E;
            if (parcelFileDescriptor != null) {
                try {
                    parcelFileDescriptor.close();
                } catch (IOException unused) {
                }
            }
            this.E = parcelFileDescriptorEstablish;
        }
        this.G.set(true);
        Thread thread = new Thread(new e(20, this), "SmartVpn");
        this.F = thread;
        thread.start();
        Log.d("MyVpnService", "VPN established successfully");
    }

    @Override // android.app.Service
    public final void onCreate() {
        super.onCreate();
        if (Build.VERSION.SDK_INT >= 29) {
            startForeground(CloseFrame.GOING_AWAY, a(), 16);
        } else {
            startForeground(CloseFrame.GOING_AWAY, a());
        }
    }

    @Override // android.app.Service
    public final void onDestroy() {
        ParcelFileDescriptor parcelFileDescriptor;
        Log.d("MyVpnService", "onDestroy called");
        this.G.set(false);
        synchronized (this) {
            parcelFileDescriptor = this.E;
            this.E = null;
        }
        if (parcelFileDescriptor != null) {
            try {
                parcelFileDescriptor.close();
                Log.d("MyVpnService", "TUN interface closed");
            } catch (Exception e) {
                Log.e("MyVpnService", "Error closing TUN", e);
            }
        }
        Thread thread = this.F;
        if (thread != null) {
            thread.interrupt();
            try {
                this.F.join(2000L);
            } catch (InterruptedException unused) {
                Thread.currentThread().interrupt();
            }
            this.F = null;
        }
        stopForeground(1);
        super.onDestroy();
    }

    @Override // android.app.Service
    public final int onStartCommand(Intent intent, int i, int i2) {
        Thread thread;
        if ((this.G.get() && this.E != null && (thread = this.F) != null && thread.isAlive()) || !this.H.compareAndSet(false, true)) {
            return 2;
        }
        new Thread(new q1(this, intent, i2, 2)).start();
        return 2;
    }
}
