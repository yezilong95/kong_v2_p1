package defpackage;

/* JADX INFO: loaded from: classes.dex */
public abstract class j81 {
    public final s81 a;
    public m40[] b;

    public j81() {
        this(new s81());
    }

    public final void a() {
        m40[] m40VarArr = this.b;
        if (m40VarArr != null) {
            m40 m40VarF = m40VarArr[0];
            m40 m40VarF2 = m40VarArr[1];
            s81 s81Var = this.a;
            if (m40VarF2 == null) {
                m40VarF2 = s81Var.a.f(2);
            }
            if (m40VarF == null) {
                m40VarF = s81Var.a.f(1);
            }
            g(m40.a(m40VarF, m40VarF2));
            m40 m40Var = this.b[ma0.I(16)];
            if (m40Var != null) {
                f(m40Var);
            }
            m40 m40Var2 = this.b[ma0.I(32)];
            if (m40Var2 != null) {
                d(m40Var2);
            }
            m40 m40Var3 = this.b[ma0.I(64)];
            if (m40Var3 != null) {
                h(m40Var3);
            }
        }
    }

    public abstract s81 b();

    public void c(int i, m40 m40Var) {
        if (this.b == null) {
            this.b = new m40[9];
        }
        for (int i2 = 1; i2 <= 256; i2 <<= 1) {
            if ((i & i2) != 0) {
                this.b[ma0.I(i2)] = m40Var;
            }
        }
    }

    public abstract void e(m40 m40Var);

    public abstract void g(m40 m40Var);

    public j81(s81 s81Var) {
        this.a = s81Var;
    }

    public void d(m40 m40Var) {
    }

    public void f(m40 m40Var) {
    }

    public void h(m40 m40Var) {
    }
}
