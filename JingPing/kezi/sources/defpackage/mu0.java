package defpackage;

import android.view.WindowInsets;

/* JADX INFO: loaded from: classes.dex */
public class mu0 extends ou0 {
    public final WindowInsets.Builder c;

    public mu0() {
        this.c = ds0.f();
    }

    @Override // defpackage.ou0
    public xu0 b() {
        a();
        xu0 xu0VarH = xu0.h(null, this.c.build());
        xu0VarH.a.o(this.b);
        return xu0VarH;
    }

    @Override // defpackage.ou0
    public void d(jy jyVar) {
        this.c.setMandatorySystemGestureInsets(jyVar.d());
    }

    @Override // defpackage.ou0
    public void e(jy jyVar) {
        this.c.setStableInsets(jyVar.d());
    }

    @Override // defpackage.ou0
    public void f(jy jyVar) {
        this.c.setSystemGestureInsets(jyVar.d());
    }

    @Override // defpackage.ou0
    public void g(jy jyVar) {
        this.c.setSystemWindowInsets(jyVar.d());
    }

    @Override // defpackage.ou0
    public void h(jy jyVar) {
        this.c.setTappableElementInsets(jyVar.d());
    }

    public mu0(xu0 xu0Var) {
        WindowInsets.Builder builderF;
        super(xu0Var);
        WindowInsets windowInsetsG = xu0Var.g();
        if (windowInsetsG != null) {
            builderF = ds0.g(windowInsetsG);
        } else {
            builderF = ds0.f();
        }
        this.c = builderF;
    }
}
