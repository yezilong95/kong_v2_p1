package defpackage;

import android.view.View;
import android.view.WindowInsets;

/* JADX INFO: loaded from: classes.dex */
public final class tu0 extends su0 {
    public static final xu0 q = xu0.h(null, WindowInsets.CONSUMED);

    public tu0(xu0 xu0Var, WindowInsets windowInsets) {
        super(xu0Var, windowInsets);
    }

    @Override // defpackage.pu0, defpackage.uu0
    public jy f(int i) {
        return jy.c(this.c.getInsets(wu0.a(i)));
    }

    @Override // defpackage.pu0, defpackage.uu0
    public final void d(View view) {
    }
}
