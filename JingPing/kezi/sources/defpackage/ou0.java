package defpackage;

/* JADX INFO: loaded from: classes.dex */
public abstract class ou0 {
    public final xu0 a;
    public jy[] b;

    public ou0() {
        this(new xu0());
    }

    public final void a() {
        jy[] jyVarArr = this.b;
        if (jyVarArr != null) {
            jy jyVarF = jyVarArr[0];
            jy jyVarF2 = jyVarArr[1];
            xu0 xu0Var = this.a;
            if (jyVarF2 == null) {
                jyVarF2 = xu0Var.a.f(2);
            }
            if (jyVarF == null) {
                jyVarF = xu0Var.a.f(1);
            }
            g(jy.a(jyVarF, jyVarF2));
            jy jyVar = this.b[nv0.B(16)];
            if (jyVar != null) {
                f(jyVar);
            }
            jy jyVar2 = this.b[nv0.B(32)];
            if (jyVar2 != null) {
                d(jyVar2);
            }
            jy jyVar3 = this.b[nv0.B(64)];
            if (jyVar3 != null) {
                h(jyVar3);
            }
        }
    }

    public abstract xu0 b();

    public void c(int i, jy jyVar) {
        if (this.b == null) {
            this.b = new jy[9];
        }
        for (int i2 = 1; i2 <= 256; i2 <<= 1) {
            if ((i & i2) != 0) {
                this.b[nv0.B(i2)] = jyVar;
            }
        }
    }

    public abstract void e(jy jyVar);

    public abstract void g(jy jyVar);

    public ou0(xu0 xu0Var) {
        this.a = xu0Var;
    }

    public void d(jy jyVar) {
    }

    public void f(jy jyVar) {
    }

    public void h(jy jyVar) {
    }
}
