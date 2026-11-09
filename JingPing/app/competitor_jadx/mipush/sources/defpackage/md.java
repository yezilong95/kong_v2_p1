package defpackage;

/* JADX INFO: loaded from: classes.dex */
public final class md extends nd {
    public final Throwable a;

    public md(Throwable th) {
        this.a = th;
    }

    public final boolean equals(Object obj) {
        if (obj instanceof md) {
            return t40.f(this.a, ((md) obj).a);
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

    @Override // defpackage.nd
    public final String toString() {
        return "Closed(" + this.a + ')';
    }
}
