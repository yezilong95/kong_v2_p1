package defpackage;

import android.app.Notification;
import android.app.NotificationChannel;
import com.mamimobi.AccessService;

/* JADX INFO: loaded from: classes.dex */
public abstract /* synthetic */ class j1 {
    public static /* synthetic */ Notification.Builder b(AccessService accessService) {
        return new Notification.Builder(accessService, "accessibility_channel");
    }

    public static /* synthetic */ NotificationChannel c() {
        return new NotificationChannel("accessibility_channel", "Accessibility Services", 2);
    }

    public static /* synthetic */ void t() {
    }
}
