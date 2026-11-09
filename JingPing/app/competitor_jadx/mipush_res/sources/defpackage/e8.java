package defpackage;

import android.os.Looper;

/* JADX INFO: loaded from: classes.dex */
public final class e8 extends o80 {
    public static volatile e8 k;
    public static final d8 l = new d8(0);
    public final rn j = new rn();

    public static e8 k0() {
        if (k != null) {
            return k;
        }
        synchronized (e8.class) {
            try {
                if (k == null) {
                    k = new e8();
                }
            } catch (Throwable th) {
                throw th;
            }
        }
        return k;
    }

    public final void l0(Runnable runnable) {
        rn rnVar = this.j;
        if (rnVar.l == null) {
            synchronized (rnVar.j) {
                try {
                    if (rnVar.l == null) {
                        rnVar.l = rn.k0(Looper.getMainLooper());
                    }
                } finally {
                }
            }
        }
        rnVar.l.post(runnable);
    }
}
