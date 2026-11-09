package defpackage;

/* JADX INFO: loaded from: classes.dex */
public final class ui0 extends is {
    public final gc0 P;
    public final float Q;

    public ui0(gc0 gc0Var, float f) {
        super(0);
        this.P = gc0Var;
        this.Q = f;
    }

    @Override // defpackage.is
    public final boolean c() {
        return true;
    }

    @Override // defpackage.is
    public final void d(float f, float f2, float f3, jv0 jv0Var) {
        this.P.d(f, f2 - this.Q, f3, jv0Var);
    }
}
