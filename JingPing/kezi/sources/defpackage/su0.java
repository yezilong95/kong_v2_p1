package defpackage;

import android.view.WindowInsets;

/* JADX INFO: loaded from: classes.dex */
public class su0 extends ru0 {
    public jy n;
    public jy o;
    public jy p;

    public su0(xu0 xu0Var, WindowInsets windowInsets) {
        super(xu0Var, windowInsets);
        this.n = null;
        this.o = null;
        this.p = null;
    }

    @Override // defpackage.uu0
    public jy g() {
        if (this.o == null) {
            this.o = jy.c(this.c.getMandatorySystemGestureInsets());
        }
        return this.o;
    }

    @Override // defpackage.uu0
    public jy i() {
        if (this.n == null) {
            this.n = jy.c(this.c.getSystemGestureInsets());
        }
        return this.n;
    }

    @Override // defpackage.uu0
    public jy k() {
        if (this.p == null) {
            this.p = jy.c(this.c.getTappableElementInsets());
        }
        return this.p;
    }

    @Override // defpackage.pu0, defpackage.uu0
    public xu0 l(int i, int i2, int i3, int i4) {
        return xu0.h(null, this.c.inset(i, i2, i3, i4));
    }

    @Override // defpackage.qu0, defpackage.uu0
    public void q(jy jyVar) {
    }
}
