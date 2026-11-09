package defpackage;

import android.os.Handler;
import android.os.Looper;
import java.util.concurrent.CancellationException;

/* JADX INFO: loaded from: classes.dex */
public final class u20 extends ej implements tn {
    public final Handler G;
    public final boolean H;
    public final u20 I;

    public u20(Handler handler, boolean z) {
        this.G = handler;
        this.H = z;
        this.I = z ? this : new u20(handler, true);
    }

    public final boolean equals(Object obj) {
        if (!(obj instanceof u20)) {
            return false;
        }
        u20 u20Var = (u20) obj;
        return u20Var.G == this.G && u20Var.H == this.H;
    }

    public final int hashCode() {
        return System.identityHashCode(this.G) ^ (this.H ? 1231 : 1237);
    }

    @Override // defpackage.ej
    public final void j(bj bjVar, Runnable runnable) {
        if (this.G.post(runnable)) {
            return;
        }
        CancellationException cancellationException = new CancellationException("The task was rejected, the handler underlying the dispatcher '" + this + "' was closed");
        f50 f50Var = (f50) bjVar.get(is.H);
        if (f50Var != null) {
            ((t50) f50Var).d(cancellationException);
        }
        xo.b.j(bjVar, runnable);
    }

    @Override // defpackage.ej
    public final String toString() {
        u20 u20Var;
        String str;
        in inVar = xo.a;
        u20 u20Var2 = qb0.a;
        if (this == u20Var2) {
            str = "Dispatchers.Main";
        } else {
            try {
                u20Var = u20Var2.I;
            } catch (UnsupportedOperationException unused) {
                u20Var = null;
            }
            str = this == u20Var ? "Dispatchers.Main.immediate" : null;
        }
        if (str != null) {
            return str;
        }
        String string = this.G.toString();
        return this.H ? zn.m(string, ".immediate") : string;
    }

    @Override // defpackage.ej
    public final boolean w() {
        return (this.H && t40.f(Looper.myLooper(), this.G.getLooper())) ? false : true;
    }
}
