package defpackage;

import android.view.WindowInsets;

/* JADX INFO: loaded from: classes.dex */
public class n81 extends m81 {
    public m40 n;
    public m40 o;
    public m40 p;

    public n81(s81 s81Var, WindowInsets windowInsets) {
        super(s81Var, windowInsets);
        this.n = null;
        this.o = null;
        this.p = null;
    }

    @Override // defpackage.p81
    public m40 g() {
        if (this.o == null) {
            this.o = m40.c(this.c.getMandatorySystemGestureInsets());
        }
        return this.o;
    }

    @Override // defpackage.p81
    public m40 i() {
        if (this.n == null) {
            this.n = m40.c(this.c.getSystemGestureInsets());
        }
        return this.n;
    }

    @Override // defpackage.p81
    public m40 k() {
        if (this.p == null) {
            this.p = m40.c(this.c.getTappableElementInsets());
        }
        return this.p;
    }

    @Override // defpackage.k81, defpackage.p81
    public s81 l(int i, int i2, int i3, int i4) {
        return s81.h(null, this.c.inset(i, i2, i3, i4));
    }

    @Override // defpackage.l81, defpackage.p81
    public void q(m40 m40Var) {
    }
}
