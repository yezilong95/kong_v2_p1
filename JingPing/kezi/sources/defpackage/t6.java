package defpackage;

import android.os.Looper;

/* JADX INFO: loaded from: classes.dex */
public final class t6 extends xi {
    public static volatile t6 s;
    public static final s6 t = new s6(0);
    public final yj r = new yj();

    public static t6 W() {
        if (s != null) {
            return s;
        }
        synchronized (t6.class) {
            try {
                if (s == null) {
                    s = new t6();
                }
            } catch (Throwable th) {
                throw th;
            }
        }
        return s;
    }

    public final void X(Runnable runnable) {
        yj yjVar = this.r;
        if (yjVar.t == null) {
            synchronized (yjVar.r) {
                try {
                    if (yjVar.t == null) {
                        yjVar.t = yj.W(Looper.getMainLooper());
                    }
                } finally {
                }
            }
        }
        yjVar.t.post(runnable);
    }
}
