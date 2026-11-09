package defpackage;

/* JADX INFO: loaded from: classes.dex */
public final class r40 implements s40 {
    public final n50 a;

    public r40(n50 n50Var) {
        this.a = n50Var;
    }

    @Override // defpackage.s40
    public final void b(Throwable th) {
        this.a.invoke(th);
    }

    public final String toString() {
        return "InternalCompletionHandler.UserSupplied[" + n50.class.getSimpleName() + '@' + qm.C(this) + ']';
    }
}
