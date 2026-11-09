package defpackage;

/* JADX INFO: loaded from: classes.dex */
public final class ii0 {
    public nv0 a = new yf0();
    public nv0 b = new yf0();
    public nv0 c = new yf0();
    public nv0 d = new yf0();
    public kh e = new e(0.0f);
    public kh f = new e(0.0f);
    public kh g = new e(0.0f);
    public kh h = new e(0.0f);
    public qm i = new qm();
    public qm j = new qm();
    public qm k = new qm();
    public qm l = new qm();

    public static void b(nv0 nv0Var) {
        if (nv0Var instanceof yf0) {
            ((yf0) nv0Var).getClass();
        } else if (nv0Var instanceof ii) {
            ((ii) nv0Var).getClass();
        }
    }

    public final ki0 a() {
        ki0 ki0Var = new ki0();
        ki0Var.a = this.a;
        ki0Var.b = this.b;
        ki0Var.c = this.c;
        ki0Var.d = this.d;
        ki0Var.e = this.e;
        ki0Var.f = this.f;
        ki0Var.g = this.g;
        ki0Var.h = this.h;
        ki0Var.i = this.i;
        ki0Var.j = this.j;
        ki0Var.k = this.k;
        ki0Var.l = this.l;
        return ki0Var;
    }

    public final void c(float f) {
        this.e = new e(f);
        this.f = new e(f);
        this.g = new e(f);
        this.h = new e(f);
    }
}
