package defpackage;

import java.util.concurrent.CancellationException;

/* JADX INFO: loaded from: classes.dex */
public final class mg {
    public final Object a;
    public final b10 b;
    public final Object c;
    public final Throwable d;

    public mg(Object obj, b10 b10Var, Object obj2, Throwable th) {
        this.a = obj;
        this.b = b10Var;
        this.c = obj2;
        this.d = th;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof mg)) {
            return false;
        }
        mg mgVar = (mg) obj;
        return t40.f(this.a, mgVar.a) && t40.f(this.b, mgVar.b) && t40.f(this.c, mgVar.c) && t40.f(this.d, mgVar.d);
    }

    public final int hashCode() {
        Object obj = this.a;
        int iHashCode = (obj == null ? 0 : obj.hashCode()) * 961;
        b10 b10Var = this.b;
        int iHashCode2 = (iHashCode + (b10Var == null ? 0 : b10Var.hashCode())) * 31;
        Object obj2 = this.c;
        int iHashCode3 = (iHashCode2 + (obj2 == null ? 0 : obj2.hashCode())) * 31;
        Throwable th = this.d;
        return iHashCode3 + (th != null ? th.hashCode() : 0);
    }

    public final String toString() {
        return "CompletedContinuation(result=" + this.a + ", cancelHandler=null, onCancellation=" + this.b + ", idempotentResume=" + this.c + ", cancelCause=" + this.d + ')';
    }

    public /* synthetic */ mg(Object obj, lj0 lj0Var, CancellationException cancellationException, int i) {
        this(obj, (i & 4) != 0 ? null : lj0Var, (Object) null, (i & 16) != 0 ? null : cancellationException);
    }
}
