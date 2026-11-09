package defpackage;

/* JADX INFO: loaded from: classes.dex */
public final class l20 extends e21 {
    public e21 a;

    @Override // defpackage.e21
    public final Object a(a60 a60Var) {
        e21 e21Var = this.a;
        if (e21Var != null) {
            return e21Var.a(a60Var);
        }
        throw new IllegalStateException();
    }

    @Override // defpackage.e21
    public final void b(f60 f60Var, Object obj) {
        e21 e21Var = this.a;
        if (e21Var == null) {
            throw new IllegalStateException();
        }
        e21Var.b(f60Var, obj);
    }
}
