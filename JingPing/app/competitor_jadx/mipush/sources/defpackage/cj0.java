package defpackage;

/* JADX INFO: loaded from: classes.dex */
public final class cj0 implements cc {
    public final a00 E;
    public final /* synthetic */ ej0 F;

    public cj0(ej0 ej0Var, a00 a00Var) {
        t40.n(a00Var, "onBackPressedCallback");
        this.F = ej0Var;
        this.E = a00Var;
    }

    /* JADX WARN: Type inference failed for: r0v2, types: [a10, kotlin.jvm.internal.FunctionReferenceImpl] */
    @Override // defpackage.cc
    public final void cancel() {
        ej0 ej0Var = this.F;
        h8 h8Var = ej0Var.b;
        a00 a00Var = this.E;
        h8Var.remove(a00Var);
        if (t40.f(ej0Var.c, a00Var)) {
            a00Var.getClass();
            ej0Var.c = null;
        }
        a00Var.b.remove(this);
        ?? r0 = a00Var.c;
        if (r0 != 0) {
            r0.invoke();
        }
        a00Var.c = null;
    }
}
