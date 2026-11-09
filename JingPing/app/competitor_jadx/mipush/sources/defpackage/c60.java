package defpackage;

import android.app.NotificationChannel;
import java.time.ZonedDateTime;

/* JADX INFO: loaded from: classes.dex */
public abstract /* synthetic */ class c60 {
    public static /* synthetic */ NotificationChannel c() {
        return new NotificationChannel("vpn_service_channel", "VPN Service", 2);
    }

    public static /* bridge */ /* synthetic */ ZonedDateTime k(Object obj) {
        return (ZonedDateTime) obj;
    }

    public static /* synthetic */ NotificationChannel u() {
        return new NotificationChannel("NOTI_CHANNEL", "通知", 4);
    }

    public static /* synthetic */ NotificationChannel x() {
        return new NotificationChannel("ScreenRequest", "Screen Request Channel", 4);
    }
}
