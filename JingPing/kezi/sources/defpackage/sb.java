package defpackage;

/* JADX INFO: loaded from: classes.dex */
public final class sb extends tb {
    public final Throwable a;

    public sb(Throwable th) {
        this.a = th;
    }

    public final boolean equals(Object obj) {
        if (obj instanceof sb) {
            return oy.c(this.a, ((sb) obj).a);
        }
        return false;
    }

    public final int hashCode() {
        Throwable th = this.a;
        if (th != null) {
            return th.hashCode();
        }
        return 0;
    }

    @Override // defpackage.tb
    public final String toString() {
        return "Closed(" + this.a + ')';
    }
}
