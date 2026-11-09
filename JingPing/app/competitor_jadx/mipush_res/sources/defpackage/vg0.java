package defpackage;

import android.view.View;

/* JADX INFO: loaded from: classes.dex */
public final class vg0 extends y1 {
    public final /* synthetic */ int d;
    public final /* synthetic */ boolean e;
    public final /* synthetic */ wg0 f;

    public vg0(wg0 wg0Var, int i, boolean z) {
        this.f = wg0Var;
        this.d = i;
        this.e = z;
    }

    @Override // defpackage.y1
    public final void d(View view, j2 j2Var) {
        this.a.onInitializeAccessibilityNodeInfo(view, j2Var.a);
        int i = this.d;
        int i2 = 0;
        int i3 = i;
        while (true) {
            wg0 wg0Var = this.f;
            if (i2 >= i) {
                wg0Var.getClass();
                j2Var.j(i2.a(i3, 1, 1, 1, this.e, view.isSelected()));
                return;
            } else {
                eh0 eh0Var = wg0Var.f;
                if (eh0Var.I.c(i2) == 2 || eh0Var.I.c(i2) == 3) {
                    i3--;
                }
                i2++;
            }
        }
    }
}
