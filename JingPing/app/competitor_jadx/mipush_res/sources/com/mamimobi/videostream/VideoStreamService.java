package com.mamimobi.videostream;

import android.R;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.IBinder;
import defpackage.ai0;
import defpackage.k41;
import defpackage.o71;
import defpackage.oj0;
import defpackage.p7;
import defpackage.t40;
import io.nats.client.support.ApiConstants;

/* JADX INFO: loaded from: classes.dex */
public class VideoStreamService extends Service {
    public o71 E;

    public static int a(VideoSourceType videoSourceType) {
        int i = k41.a[videoSourceType.ordinal()];
        if (i != 1) {
            return (i == 2 || i == 3 || i == 4) ? 64 : 0;
        }
        return 32;
    }

    public static void b(Context context, VideoSourceType videoSourceType) {
        Intent intent = new Intent(context, (Class<?>) VideoStreamService.class);
        intent.putExtra(ApiConstants.SOURCE, videoSourceType.name());
        if (Build.VERSION.SDK_INT >= 26) {
            t40.j1(context, intent);
        } else {
            context.startService(intent);
        }
    }

    @Override // android.app.Service
    public final IBinder onBind(Intent intent) {
        return null;
    }

    @Override // android.app.Service
    public final void onCreate() {
        super.onCreate();
        if (Build.VERSION.SDK_INT >= 26) {
            ((NotificationManager) getSystemService(NotificationManager.class)).createNotificationChannel(oj0.c());
        }
        this.E = o71.f(this);
    }

    @Override // android.app.Service
    public final void onDestroy() {
        stopForeground(true);
        stopSelf();
        super.onDestroy();
    }

    @Override // android.app.Service
    public final int onStartCommand(Intent intent, int i, int i2) {
        if (intent != null) {
            try {
                String stringExtra = intent.getStringExtra(ApiConstants.SOURCE);
                if (stringExtra != null) {
                    ai0 ai0Var = new ai0(this, "video_stream_channel");
                    ai0Var.e = ai0.b("Service");
                    ai0Var.f = ai0.b("Transferring video");
                    ai0Var.n.icon = R.drawable.ic_media_play;
                    Notification notificationA = ai0Var.a();
                    VideoSourceType videoSourceType = VideoSourceType.SCREEN;
                    if (stringExtra.equals(videoSourceType.name())) {
                        if (Build.VERSION.SDK_INT >= 29) {
                            startForeground(1, notificationA, a(videoSourceType));
                        } else {
                            startForeground(1, notificationA);
                        }
                        if (this.E.g != null) {
                            new Thread(new p7(this, 19, stringExtra)).start();
                        }
                    } else {
                        if (!stringExtra.equals(VideoSourceType.SCREENHELPER.name())) {
                            if (Build.VERSION.SDK_INT >= 29) {
                                startForeground(1, notificationA, a(VideoSourceType.CAMERA_FRONT));
                            } else {
                                startForeground(1, notificationA);
                            }
                            if (t40.o(this, "android.permission.CAMERA") == 0) {
                            }
                        } else if (Build.VERSION.SDK_INT >= 29) {
                            startForeground(1, notificationA, a(VideoSourceType.CAMERA_FRONT));
                        } else {
                            startForeground(1, notificationA);
                        }
                        new Thread(new p7(this, 19, stringExtra)).start();
                    }
                }
            } catch (Exception unused) {
            }
        }
        return 1;
    }
}
