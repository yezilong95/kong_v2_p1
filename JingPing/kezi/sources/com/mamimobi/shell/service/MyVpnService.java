package com.mamimobi.shell.service;

import android.R;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.VpnService;
import android.os.Build;
import android.os.Bundle;
import android.os.ParcelFileDescriptor;
import android.text.TextUtils;
import defpackage.e0;
import defpackage.f7;
import defpackage.hk;
import defpackage.s90;
import defpackage.t90;
import defpackage.u90;
import defpackage.v90;
import defpackage.w90;
import defpackage.x90;
import defpackage.xi;
import defpackage.z1;
import java.util.ArrayList;
import java.util.Iterator;
import kotlin.uuid.Uuid;
import org.java_websocket.framing.CloseFrame;

/* JADX INFO: loaded from: classes.dex */
public class MyVpnService extends VpnService {
    public static final /* synthetic */ int e = 0;
    public ParcelFileDescriptor b;
    public Thread c;
    public volatile boolean d = true;

    public final Notification a() {
        Bundle bundle;
        NotificationManager notificationManager;
        int i = Build.VERSION.SDK_INT;
        if (i >= 26 && (notificationManager = (NotificationManager) getSystemService("notification")) != null && notificationManager.getNotificationChannel("vpn_service_channel") == null) {
            NotificationChannel notificationChannelB = e0.b();
            notificationChannelB.setDescription("VPN Running Notification");
            notificationManager.createNotificationChannel(notificationChannelB);
        }
        ArrayList arrayList = new ArrayList();
        ArrayList arrayList2 = new ArrayList();
        ArrayList arrayList3 = new ArrayList();
        Notification notification = new Notification();
        notification.when = System.currentTimeMillis();
        notification.audioStreamType = -1;
        ArrayList arrayList4 = new ArrayList();
        CharSequence charSequenceSubSequence = "VPN".length() > 5120 ? "VPN".subSequence(0, 5120) : "VPN";
        CharSequence charSequenceSubSequence2 = "Protecting your network connection".length() > 5120 ? "Protecting your network connection".subSequence(0, 5120) : "Protecting your network connection";
        notification.icon = R.drawable.ic_lock_lock;
        notification.flags |= 2;
        new ArrayList();
        Bundle bundle2 = new Bundle();
        Notification.Builder builderA = i >= 26 ? w90.a(this, "vpn_service_channel") : new Notification.Builder(this);
        builderA.setWhen(notification.when).setSmallIcon(notification.icon, notification.iconLevel).setContent(notification.contentView).setTicker(notification.tickerText, null).setVibrate(notification.vibrate).setLights(notification.ledARGB, notification.ledOnMS, notification.ledOffMS).setOngoing((notification.flags & 2) != 0).setOnlyAlertOnce((notification.flags & 8) != 0).setAutoCancel((notification.flags & 16) != 0).setDefaults(notification.defaults).setContentTitle(charSequenceSubSequence).setContentText(charSequenceSubSequence2).setContentInfo(null).setContentIntent(null).setDeleteIntent(notification.deleteIntent).setFullScreenIntent(null, (notification.flags & Uuid.SIZE_BITS) != 0).setNumber(0).setProgress(0, 0, false);
        u90.b(builderA, null);
        builderA.setSubText(null).setUsesChronometer(false).setPriority(-1);
        Iterator it = arrayList.iterator();
        if (it.hasNext()) {
            throw hk.e(it);
        }
        builderA.setShowWhen(true);
        s90.i(builderA, false);
        s90.g(builderA, null);
        s90.j(builderA, null);
        s90.h(builderA, false);
        t90.b(builderA, null);
        t90.c(builderA, 0);
        t90.f(builderA, 0);
        t90.d(builderA, null);
        t90.e(builderA, notification.sound, notification.audioAttributes);
        if (i < 28) {
            ArrayList arrayList5 = new ArrayList(arrayList2.size());
            Iterator it2 = arrayList2.iterator();
            if (it2.hasNext()) {
                throw hk.e(it2);
            }
            f7 f7Var = new f7(arrayList4.size() + arrayList5.size());
            f7Var.addAll(arrayList5);
            f7Var.addAll(arrayList4);
            arrayList4 = new ArrayList(f7Var);
        }
        if (!arrayList4.isEmpty()) {
            Iterator it3 = arrayList4.iterator();
            while (it3.hasNext()) {
                t90.a(builderA, (String) it3.next());
            }
        }
        if (arrayList3.size() > 0) {
            bundle = new Bundle();
            Bundle bundle3 = bundle.getBundle("android.car.EXTENSIONS");
            if (bundle3 == null) {
                bundle3 = new Bundle();
            }
            Bundle bundle4 = new Bundle(bundle3);
            Bundle bundle5 = new Bundle();
            if (arrayList3.size() > 0) {
                Integer.toString(0);
                if (arrayList3.get(0) != null) {
                    throw new ClassCastException();
                }
                new Bundle();
                throw null;
            }
            bundle3.putBundle("invisible_actions", bundle5);
            bundle4.putBundle("invisible_actions", bundle5);
            bundle.putBundle("android.car.EXTENSIONS", bundle3);
            bundle2.putBundle("android.car.EXTENSIONS", bundle4);
        } else {
            bundle = null;
        }
        int i2 = Build.VERSION.SDK_INT;
        builderA.setExtras(bundle);
        v90.e(builderA, null);
        if (i2 >= 26) {
            w90.b(builderA, 0);
            w90.e(builderA, null);
            w90.f(builderA, null);
            w90.g(builderA, 0L);
            w90.d(builderA, 0);
            if (!TextUtils.isEmpty("vpn_service_channel")) {
                builderA.setSound(null).setDefaults(0).setLights(0, 0, 0).setVibrate(null);
            }
        }
        if (i2 >= 28) {
            Iterator it4 = arrayList2.iterator();
            if (it4.hasNext()) {
                throw hk.e(it4);
            }
        }
        if (i2 >= 29) {
            x90.a(builderA, true);
            x90.b(builderA, null);
        }
        return i2 >= 26 ? builderA.build() : builderA.build();
    }

    @Override // android.app.Service
    public final void onCreate() {
        super.onCreate();
        int i = Build.VERSION.SDK_INT;
        if (i >= 29) {
            startForeground(CloseFrame.GOING_AWAY, a(), 1);
        } else if (i >= 26) {
            startForeground(CloseFrame.GOING_AWAY, a());
        }
        startService(new Intent(this, (Class<?>) KAService.class));
    }

    @Override // android.app.Service
    public final void onDestroy() {
        this.d = false;
        Thread thread = this.c;
        if (thread != null) {
            thread.interrupt();
        }
        ParcelFileDescriptor parcelFileDescriptor = this.b;
        if (parcelFileDescriptor != null) {
            try {
                parcelFileDescriptor.close();
            } catch (Exception unused) {
            }
        }
        super.onDestroy();
    }

    @Override // android.app.Service
    public final int onStartCommand(Intent intent, int i, int i2) {
        VpnService.Builder builder = new VpnService.Builder(this);
        builder.setSession("SmartVpn");
        builder.addAddress("10.0.0.2", 32);
        builder.addRoute("0.0.0.0", 0);
        builder.addRoute("::", 0);
        if (intent != null) {
            String[] stringArrayExtra = intent.getStringArrayExtra("pkgs");
            if (stringArrayExtra == null) {
                String strQ = xi.Q(this, "pkgs");
                stringArrayExtra = strQ != null ? strQ.split(",") : new String[]{"com.google.android.gms", "com.android.vending"};
            }
            for (String str : stringArrayExtra) {
                try {
                    builder.addAllowedApplication(str);
                } catch (PackageManager.NameNotFoundException unused) {
                    if (str.endsWith("vending") || str.endsWith("gms")) {
                        stopSelf();
                        return 2;
                    }
                }
            }
        }
        ParcelFileDescriptor parcelFileDescriptorEstablish = builder.establish();
        this.b = parcelFileDescriptorEstablish;
        if (parcelFileDescriptorEstablish == null) {
            stopSelf();
            return 2;
        }
        Thread thread = new Thread(new z1(15, this), "SmartVpn");
        this.c = thread;
        thread.start();
        return 1;
    }
}
