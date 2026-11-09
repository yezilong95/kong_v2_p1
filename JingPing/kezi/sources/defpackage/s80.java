package defpackage;

import android.view.View;

/* JADX INFO: loaded from: classes.dex */
public final class s80 extends b0 {
    public final /* synthetic */ int d;
    public final /* synthetic */ boolean e;
    public final /* synthetic */ t80 f;

    public s80(t80 t80Var, int i, boolean z) {
        this.f = t80Var;
        this.d = i;
        this.e = z;
    }

    @Override // defpackage.b0
    public final void d(View view, o0 o0Var) {
        this.a.onInitializeAccessibilityNodeInfo(view, o0Var.a);
        int i = this.d;
        int i2 = 0;
        int i3 = i;
        while (true) {
            t80 t80Var = this.f;
            if (i2 >= i) {
                t80Var.getClass();
                o0Var.j(n0.a(i3, 1, 1, 1, this.e, view.isSelected()));
                return;
            } else {
                b90 b90Var = t80Var.f;
                if (b90Var.f.c(i2) == 2 || b90Var.f.c(i2) == 3) {
                    i3--;
                }
                i2++;
            }
        }
    }
}
