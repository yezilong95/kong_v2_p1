package defpackage;

import android.view.WindowInsets;

/* JADX INFO: loaded from: classes.dex */
public class qu0 extends pu0 {
    public jy m;

    public qu0(xu0 xu0Var, WindowInsets windowInsets) {
        super(xu0Var, windowInsets);
        this.m = null;
    }

    @Override // defpackage.uu0
    public xu0 b() {
        return xu0.h(null, this.c.consumeStableInsets());
    }

    @Override // defpackage.uu0
    public xu0 c() {
        return xu0.h(null, this.c.consumeSystemWindowInsets());
    }

    @Override // defpackage.uu0
    public final jy h() {
        if (this.m == null) {
            WindowInsets windowInsets = this.c;
            this.m = jy.b(windowInsets.getStableInsetLeft(), windowInsets.getStableInsetTop(), windowInsets.getStableInsetRight(), windowInsets.getStableInsetBottom());
        }
        return this.m;
    }

    @Override // defpackage.uu0
    public boolean m() {
        return this.c.isConsumed();
    }

    @Override // defpackage.uu0
    public void q(jy jyVar) {
        this.m = jyVar;
    }
}
