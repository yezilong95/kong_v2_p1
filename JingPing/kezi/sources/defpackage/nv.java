package defpackage;

import android.os.Handler;
import android.os.Looper;
import java.lang.reflect.InvocationTargetException;
import java.util.concurrent.CancellationException;

/* JADX INFO: loaded from: classes.dex */
public final class nv extends qh implements bk {
    private volatile nv _immediate;
    public final Handler d;
    public final boolean e;
    public final nv f;

    public nv(Handler handler, boolean z) {
        this.d = handler;
        this.e = z;
        this._immediate = z ? this : null;
        nv nvVar = this._immediate;
        if (nvVar == null) {
            nvVar = new nv(handler, true);
            this._immediate = nvVar;
        }
        this.f = nvVar;
    }

    @Override // defpackage.qh
    public final boolean D() {
        return (this.e && oy.c(Looper.myLooper(), this.d.getLooper())) ? false : true;
    }

    public final boolean equals(Object obj) {
        return (obj instanceof nv) && ((nv) obj).d == this.d;
    }

    public final int hashCode() {
        return System.identityHashCode(this.d);
    }

    @Override // defpackage.qh
    public final void t(nh nhVar, Runnable runnable) throws IllegalAccessException, InvocationTargetException {
        if (this.d.post(runnable)) {
            return;
        }
        CancellationException cancellationException = new CancellationException("The task was rejected, the handler underlying the dispatcher '" + this + "' was closed");
        bz bzVar = (bz) nhVar.get(qm.f);
        if (bzVar != null) {
            ((nz) bzVar).e(cancellationException);
        }
        al.b.t(nhVar, runnable);
    }

    @Override // defpackage.qh
    public final String toString() {
        nv nvVar;
        String str;
        pj pjVar = al.a;
        nv nvVar2 = b40.a;
        if (this == nvVar2) {
            str = "Dispatchers.Main";
        } else {
            try {
                nvVar = nvVar2.f;
            } catch (UnsupportedOperationException unused) {
                nvVar = null;
            }
            str = this == nvVar ? "Dispatchers.Main.immediate" : null;
        }
        if (str != null) {
            return str;
        }
        String string = this.d.toString();
        return this.e ? hk.i(string, ".immediate") : string;
    }
}
