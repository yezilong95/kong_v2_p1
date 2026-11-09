package defpackage;

import android.view.WindowInsets;

/* JADX INFO: loaded from: classes.dex */
public class l81 extends k81 {
    public m40 m;

    public l81(s81 s81Var, WindowInsets windowInsets) {
        super(s81Var, windowInsets);
        this.m = null;
    }

    @Override // defpackage.p81
    public s81 b() {
        return s81.h(null, this.c.consumeStableInsets());
    }

    @Override // defpackage.p81
    public s81 c() {
        return s81.h(null, this.c.consumeSystemWindowInsets());
    }

    @Override // defpackage.p81
    public final m40 h() {
        if (this.m == null) {
            WindowInsets windowInsets = this.c;
            this.m = m40.b(windowInsets.getStableInsetLeft(), windowInsets.getStableInsetTop(), windowInsets.getStableInsetRight(), windowInsets.getStableInsetBottom());
        }
        return this.m;
    }

    @Override // defpackage.p81
    public boolean m() {
        return this.c.isConsumed();
    }

    @Override // defpackage.p81
    public void q(m40 m40Var) {
        this.m = m40Var;
    }
}
