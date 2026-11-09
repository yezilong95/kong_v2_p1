package defpackage;

import android.os.Looper;
import android.util.AndroidRuntimeException;

/* JADX INFO: loaded from: classes.dex */
public final class tw0 extends oq {
    public uw0 r;
    public float s;
    public boolean t;

    public final void b() {
        if (this.r.b <= 0.0d) {
            throw new UnsupportedOperationException("Spring animations can only come to an end when there is damping");
        }
        if (Looper.myLooper() != Looper.getMainLooper()) {
            throw new AndroidRuntimeException("Animations may only be started on the main thread");
        }
        if (this.f) {
            this.t = true;
        }
    }
}
