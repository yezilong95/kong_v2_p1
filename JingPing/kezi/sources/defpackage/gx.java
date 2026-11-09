package defpackage;

import android.view.View;

/* JADX INFO: loaded from: classes.dex */
public final class gx implements ha0 {
    public final int b;
    public final int c;
    public final Object d;

    public gx() {
        this.d = new gx[256];
        this.b = 0;
        this.c = 0;
    }

    @Override // defpackage.ha0
    public xu0 e(View view, xu0 xu0Var) {
        int i = xu0Var.a.f(7).b;
        View view2 = (View) this.d;
        int i2 = this.b;
        if (i2 >= 0) {
            view2.getLayoutParams().height = i2 + i;
            view2.setLayoutParams(view2.getLayoutParams());
        }
        view2.setPadding(view2.getPaddingLeft(), this.c + i, view2.getPaddingRight(), view2.getPaddingBottom());
        return xu0Var;
    }

    public gx(int i, int i2) {
        this.d = null;
        this.b = i;
        int i3 = i2 & 7;
        this.c = i3 == 0 ? 8 : i3;
    }

    public gx(View view, int i, int i2) {
        this.b = i;
        this.d = view;
        this.c = i2;
    }
}
