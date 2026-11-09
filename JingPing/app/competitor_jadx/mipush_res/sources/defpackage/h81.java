package defpackage;

import android.view.WindowInsets;

/* JADX INFO: loaded from: classes.dex */
public class h81 extends j81 {
    public final WindowInsets.Builder c;

    public h81() {
        this.c = s61.d();
    }

    @Override // defpackage.j81
    public s81 b() {
        a();
        s81 s81VarH = s81.h(null, this.c.build());
        s81VarH.a.o(this.b);
        return s81VarH;
    }

    @Override // defpackage.j81
    public void d(m40 m40Var) {
        this.c.setMandatorySystemGestureInsets(m40Var.d());
    }

    @Override // defpackage.j81
    public void e(m40 m40Var) {
        this.c.setStableInsets(m40Var.d());
    }

    @Override // defpackage.j81
    public void f(m40 m40Var) {
        this.c.setSystemGestureInsets(m40Var.d());
    }

    @Override // defpackage.j81
    public void g(m40 m40Var) {
        this.c.setSystemWindowInsets(m40Var.d());
    }

    @Override // defpackage.j81
    public void h(m40 m40Var) {
        this.c.setTappableElementInsets(m40Var.d());
    }

    public h81(s81 s81Var) {
        WindowInsets.Builder builderD;
        super(s81Var);
        WindowInsets windowInsetsG = s81Var.g();
        if (windowInsetsG != null) {
            builderD = s61.e(windowInsetsG);
        } else {
            builderD = s61.d();
        }
        this.c = builderD;
    }
}
