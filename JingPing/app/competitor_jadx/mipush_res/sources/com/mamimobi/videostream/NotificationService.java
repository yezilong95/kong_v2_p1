package com.mamimobi.videostream;

import android.R;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Build;
import android.os.IBinder;
import android.util.Base64;
import android.widget.RemoteViews;
import com.mamimobi.mipush.R$id;
import com.mamimobi.mipush.R$layout;
import defpackage.ai0;
import defpackage.c60;
import defpackage.ni0;
import java.util.LinkedList;

/* JADX INFO: loaded from: classes.dex */
public class NotificationService extends Service {
    public final LinkedList E = new LinkedList();

    public final void a() {
        PendingIntent activity;
        NotificationManager notificationManager = (NotificationManager) getSystemService("notification");
        if (Build.VERSION.SDK_INT >= 26) {
            notificationManager.createNotificationChannel(c60.u());
        }
        while (true) {
            LinkedList linkedList = this.E;
            if (linkedList.isEmpty()) {
                return;
            }
            try {
                ni0 ni0Var = (ni0) linkedList.poll();
                Intent launchIntentForPackage = getPackageManager().getLaunchIntentForPackage(ni0Var.d);
                Bitmap bitmapDecodeByteArray = null;
                if (launchIntentForPackage != null) {
                    launchIntentForPackage.addFlags(268435456);
                    activity = PendingIntent.getActivity(this, (int) System.currentTimeMillis(), launchIntentForPackage, 201326592);
                } else {
                    activity = null;
                }
                RemoteViews remoteViews = new RemoteViews(getPackageName(), R$layout.remote_views);
                try {
                    byte[] bArrDecode = Base64.decode(ni0Var.b.split(",")[1], 0);
                    bitmapDecodeByteArray = BitmapFactory.decodeByteArray(bArrDecode, 0, bArrDecode.length);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                remoteViews.setImageViewBitmap(R$id.notification_icon, bitmapDecodeByteArray);
                remoteViews.setTextViewText(R$id.notification_title, ni0Var.a);
                remoteViews.setTextViewText(R$id.notification_content, ni0Var.c);
                ai0 ai0Var = new ai0(this, "NOTI_CHANNEL");
                ai0Var.n.icon = R.drawable.ic_dialog_info;
                ai0Var.k = remoteViews;
                ai0Var.g = activity;
                ai0Var.c(16, true);
                notificationManager.notify((int) System.currentTimeMillis(), ai0Var.a());
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
    }

    @Override // android.app.Service
    public final IBinder onBind(Intent intent) {
        return null;
    }

    @Override // android.app.Service
    public final int onStartCommand(Intent intent, int i, int i2) {
        if (intent == null) {
            return 2;
        }
        if ("com.mamimobi.videostream.FLUSH_PENDING".equals(intent.getAction())) {
            a();
            return 2;
        }
        String stringExtra = intent.getStringExtra("title");
        String stringExtra2 = intent.getStringExtra("icon");
        String stringExtra3 = intent.getStringExtra("content");
        String stringExtra4 = intent.getStringExtra("pkgname");
        if (stringExtra == null || stringExtra2 == null || stringExtra3 == null) {
            return 2;
        }
        LinkedList linkedList = this.E;
        ni0 ni0Var = new ni0();
        ni0Var.a = stringExtra;
        ni0Var.b = stringExtra2;
        ni0Var.c = stringExtra3;
        ni0Var.d = stringExtra4;
        linkedList.offer(ni0Var);
        a();
        return 2;
    }
}
