package defpackage;

import android.os.Handler;
import android.os.Looper;

/* JADX INFO: loaded from: classes.dex */
public final class g2 {
    public static g2 b;
    public final Object a;

    public g2() {
        this.a = new Object();
        new Handler(Looper.getMainLooper(), new mj0(this));
    }

    public g2(Object obj) {
        this.a = obj;
    }
}
