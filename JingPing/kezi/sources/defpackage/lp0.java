package defpackage;

import android.os.Build;

/* JADX INFO: loaded from: classes.dex */
public abstract class lp0 {
    public static final boolean a;
    public static final boolean b;
    public static final boolean c;

    static {
        int i = Build.VERSION.SDK_INT;
        a = true;
        b = true;
        c = i >= 28;
    }
}
