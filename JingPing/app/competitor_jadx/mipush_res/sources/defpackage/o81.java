package defpackage;

import android.view.View;
import android.view.WindowInsets;

/* JADX INFO: loaded from: classes.dex */
public final class o81 extends n81 {
    public static final s81 q = s81.h(null, WindowInsets.CONSUMED);

    public o81(s81 s81Var, WindowInsets windowInsets) {
        super(s81Var, windowInsets);
    }

    @Override // defpackage.k81, defpackage.p81
    public m40 f(int i) {
        return m40.c(this.c.getInsets(r81.a(i)));
    }

    @Override // defpackage.k81, defpackage.p81
    public final void d(View view) {
    }
}
