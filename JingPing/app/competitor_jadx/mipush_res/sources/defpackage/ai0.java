package defpackage;

import android.app.Notification;
import android.app.PendingIntent;
import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.RemoteViews;
import java.util.ArrayList;
import java.util.Iterator;
import kotlin.uuid.Uuid;

/* JADX INFO: loaded from: classes.dex */
public final class ai0 {
    public final Context a;
    public CharSequence e;
    public CharSequence f;
    public PendingIntent g;
    public int h;
    public Bundle j;
    public RemoteViews k;
    public final String l;
    public final boolean m;
    public final Notification n;
    public final ArrayList o;
    public final ArrayList b = new ArrayList();
    public final ArrayList c = new ArrayList();
    public final ArrayList d = new ArrayList();
    public final boolean i = true;

    public ai0(Context context, String str) {
        Notification notification = new Notification();
        this.n = notification;
        this.a = context;
        this.l = str;
        notification.when = System.currentTimeMillis();
        notification.audioStreamType = -1;
        this.h = 0;
        this.o = new ArrayList();
        this.m = true;
    }

    public static CharSequence b(String str) {
        return str.length() > 5120 ? str.subSequence(0, 5120) : str;
    }

    public final Notification a() {
        ArrayList arrayList;
        new ArrayList();
        Bundle bundle = new Bundle();
        int i = Build.VERSION.SDK_INT;
        Context context = this.a;
        String str = this.l;
        Notification.Builder builderA = i >= 26 ? fi0.a(context, str) : new Notification.Builder(context);
        Notification notification = this.n;
        builderA.setWhen(notification.when).setSmallIcon(notification.icon, notification.iconLevel).setContent(notification.contentView).setTicker(notification.tickerText, null).setVibrate(notification.vibrate).setLights(notification.ledARGB, notification.ledOnMS, notification.ledOffMS).setOngoing((notification.flags & 2) != 0).setOnlyAlertOnce((notification.flags & 8) != 0).setAutoCancel((notification.flags & 16) != 0).setDefaults(notification.defaults).setContentTitle(this.e).setContentText(this.f).setContentInfo(null).setContentIntent(this.g).setDeleteIntent(notification.deleteIntent).setFullScreenIntent(null, (notification.flags & Uuid.SIZE_BITS) != 0).setNumber(0).setProgress(0, 0, false);
        di0.b(builderA, null);
        builderA.setSubText(null).setUsesChronometer(false).setPriority(this.h);
        Iterator it = this.b.iterator();
        if (it.hasNext()) {
            throw zn.i(it);
        }
        Bundle bundle2 = this.j;
        if (bundle2 != null) {
            bundle.putAll(bundle2);
        }
        builderA.setShowWhen(this.i);
        bi0.i(builderA, false);
        bi0.g(builderA, null);
        bi0.j(builderA, null);
        bi0.h(builderA, false);
        ci0.b(builderA, null);
        ci0.c(builderA, 0);
        ci0.f(builderA, 0);
        ci0.d(builderA, null);
        ci0.e(builderA, notification.sound, notification.audioAttributes);
        ArrayList arrayList2 = this.o;
        ArrayList arrayList3 = this.c;
        if (i < 28) {
            if (arrayList3 == null) {
                arrayList = null;
            } else {
                arrayList = new ArrayList(arrayList3.size());
                Iterator it2 = arrayList3.iterator();
                if (it2.hasNext()) {
                    throw zn.i(it2);
                }
            }
            if (arrayList != null) {
                if (arrayList2 == null) {
                    arrayList2 = arrayList;
                } else {
                    n8 n8Var = new n8(arrayList2.size() + arrayList.size());
                    n8Var.addAll(arrayList);
                    n8Var.addAll(arrayList2);
                    arrayList2 = new ArrayList(n8Var);
                }
            }
        }
        if (arrayList2 != null && !arrayList2.isEmpty()) {
            Iterator it3 = arrayList2.iterator();
            while (it3.hasNext()) {
                ci0.a(builderA, (String) it3.next());
            }
        }
        ArrayList arrayList4 = this.d;
        if (arrayList4.size() > 0) {
            if (this.j == null) {
                this.j = new Bundle();
            }
            Bundle bundle3 = this.j.getBundle("android.car.EXTENSIONS");
            if (bundle3 == null) {
                bundle3 = new Bundle();
            }
            Bundle bundle4 = new Bundle(bundle3);
            Bundle bundle5 = new Bundle();
            if (arrayList4.size() > 0) {
                Integer.toString(0);
                if (arrayList4.get(0) != null) {
                    throw new ClassCastException();
                }
                new Bundle();
                throw null;
            }
            bundle3.putBundle("invisible_actions", bundle5);
            bundle4.putBundle("invisible_actions", bundle5);
            if (this.j == null) {
                this.j = new Bundle();
            }
            this.j.putBundle("android.car.EXTENSIONS", bundle3);
            bundle.putBundle("android.car.EXTENSIONS", bundle4);
        }
        int i2 = Build.VERSION.SDK_INT;
        builderA.setExtras(this.j);
        ei0.e(builderA, null);
        RemoteViews remoteViews = this.k;
        if (remoteViews != null) {
            ei0.c(builderA, remoteViews);
        }
        if (i2 >= 26) {
            fi0.b(builderA, 0);
            fi0.e(builderA, null);
            fi0.f(builderA, null);
            fi0.g(builderA, 0L);
            fi0.d(builderA, 0);
            if (!TextUtils.isEmpty(str)) {
                builderA.setSound(null).setDefaults(0).setLights(0, 0, 0).setVibrate(null);
            }
        }
        if (i2 >= 28) {
            Iterator it4 = arrayList3.iterator();
            if (it4.hasNext()) {
                throw zn.i(it4);
            }
        }
        if (i2 >= 29) {
            gi0.a(builderA, this.m);
            gi0.b(builderA, null);
        }
        Notification notificationBuild = i2 >= 26 ? builderA.build() : builderA.build();
        RemoteViews remoteViews2 = this.k;
        if (remoteViews2 != null) {
            notificationBuild.contentView = remoteViews2;
        }
        return notificationBuild;
    }

    public final void c(int i, boolean z) {
        Notification notification = this.n;
        if (z) {
            notification.flags = i | notification.flags;
        } else {
            notification.flags = (~i) & notification.flags;
        }
    }
}
