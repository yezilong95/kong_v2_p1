package defpackage;

import java.util.concurrent.CancellationException;

/* JADX INFO: loaded from: classes.dex */
public final class se {
    public final Object a;
    public final pu b;
    public final Object c;
    public final Throwable d;

    public se(Object obj, pu puVar, Object obj2, Throwable th) {
        this.a = obj;
        this.b = puVar;
        this.c = obj2;
        this.d = th;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof se)) {
            return false;
        }
        se seVar = (se) obj;
        return oy.c(this.a, seVar.a) && oy.c(this.b, seVar.b) && oy.c(this.c, seVar.c) && oy.c(this.d, seVar.d);
    }

    public final int hashCode() {
        Object obj = this.a;
        int iHashCode = (obj == null ? 0 : obj.hashCode()) * 961;
        pu puVar = this.b;
        int iHashCode2 = (iHashCode + (puVar == null ? 0 : puVar.hashCode())) * 31;
        Object obj2 = this.c;
        int iHashCode3 = (iHashCode2 + (obj2 == null ? 0 : obj2.hashCode())) * 31;
        Throwable th = this.d;
        return iHashCode3 + (th != null ? th.hashCode() : 0);
    }

    public final String toString() {
        return "CompletedContinuation(result=" + this.a + ", cancelHandler=null, onCancellation=" + this.b + ", idempotentResume=" + this.c + ", cancelCause=" + this.d + ')';
    }

    public /* synthetic */ se(Object obj, wa0 wa0Var, CancellationException cancellationException, int i) {
        this(obj, (i & 4) != 0 ? null : wa0Var, (Object) null, (i & 16) != 0 ? null : cancellationException);
    }
}
