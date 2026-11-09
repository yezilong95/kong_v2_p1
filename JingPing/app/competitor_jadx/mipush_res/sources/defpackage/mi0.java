package defpackage;

import android.app.NotificationManager;
import android.content.Context;
import java.util.HashSet;

/* JADX INFO: loaded from: classes.dex */
public final class mi0 {
    public static String c;
    public static li0 f;
    public final NotificationManager a;
    public static final Object b = new Object();
    public static HashSet d = new HashSet();
    public static final Object e = new Object();

    public mi0(Context context) {
        this.a = (NotificationManager) context.getSystemService("notification");
    }
}
