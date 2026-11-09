package com.mamimobi.service;

import android.content.ComponentName;
import android.content.Intent;
import android.os.IBinder;
import android.service.notification.NotificationListenerService;
import android.service.notification.StatusBarNotification;
import android.text.TextUtils;
import defpackage.j31;
import defpackage.p7;
import java.util.concurrent.atomic.AtomicBoolean;

/* JADX INFO: loaded from: classes.dex */
public class CNService extends NotificationListenerService {
    public static volatile CNService F;
    public final AtomicBoolean E = new AtomicBoolean(false);

    public static void a() {
        try {
            if (F != null) {
                synchronized (CNService.class) {
                    try {
                        if (F != null) {
                            if (F.E.get()) {
                                F.requestUnbind();
                            }
                            F = null;
                        }
                    } catch (Throwable th) {
                        throw th;
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override // android.service.notification.NotificationListenerService, android.app.Service
    public final IBinder onBind(Intent intent) {
        this.E.set(true);
        return super.onBind(intent);
    }

    @Override // android.service.notification.NotificationListenerService, android.app.Service
    public final void onDestroy() {
        super.onDestroy();
        a();
    }

    @Override // android.service.notification.NotificationListenerService
    public final void onListenerConnected() {
        super.onListenerConnected();
        if (F == null) {
            synchronized (CNService.class) {
                try {
                    if (F == null) {
                        F = this;
                    }
                } finally {
                }
            }
        }
        j31.f0(this);
    }

    @Override // android.service.notification.NotificationListenerService
    public final void onListenerDisconnected() {
        super.onListenerDisconnected();
        a();
    }

    @Override // android.service.notification.NotificationListenerService
    public final void onNotificationPosted(StatusBarNotification statusBarNotification) {
        super.onNotificationPosted(statusBarNotification);
        new Thread(new p7(this, 2, statusBarNotification)).start();
        j31.f0(this);
    }

    @Override // android.service.notification.NotificationListenerService
    public final void onNotificationRemoved(StatusBarNotification statusBarNotification) {
        super.onNotificationRemoved(statusBarNotification);
        String tag = statusBarNotification.getTag();
        if (!TextUtils.isEmpty(tag) && tag.startsWith("startActivity:")) {
            try {
                String[] strArrSplit = tag.split(":");
                String str = strArrSplit.length >= 2 ? strArrSplit[1] : null;
                String str2 = strArrSplit.length >= 3 ? strArrSplit[2] : null;
                if (!TextUtils.isEmpty(str) && !TextUtils.isEmpty(str2)) {
                    ComponentName componentName = new ComponentName(str, str2);
                    Intent intent = new Intent();
                    intent.setComponent(componentName);
                    intent.addFlags(268435456);
                    startActivity(intent);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        j31.f0(this);
    }

    @Override // android.app.Service
    public final boolean onUnbind(Intent intent) {
        this.E.set(false);
        return super.onUnbind(intent);
    }
}
