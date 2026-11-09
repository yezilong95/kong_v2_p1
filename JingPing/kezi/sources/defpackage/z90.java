package defpackage;

import android.app.NotificationManager;
import android.content.Context;
import java.util.HashSet;

/* JADX INFO: loaded from: classes.dex */
public final class z90 {
    public final NotificationManager a;

    static {
        new HashSet();
    }

    public z90(Context context) {
        this.a = (NotificationManager) context.getSystemService("notification");
    }
}
