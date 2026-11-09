package defpackage;

/* JADX INFO: loaded from: classes.dex */
public final class ca0 extends qm {
    public final q40 l;
    public final float m;

    public ca0(q40 q40Var, float f) {
        this.l = q40Var;
        this.m = f;
    }

    @Override // defpackage.qm
    public final boolean i() {
        return true;
    }

    @Override // defpackage.qm
    public final void j(float f, float f2, float f3, ui0 ui0Var) {
        this.l.j(f, f2 - this.m, f3, ui0Var);
    }
}
