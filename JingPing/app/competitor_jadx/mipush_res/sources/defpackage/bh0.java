package defpackage;

import android.view.View;
import android.view.accessibility.AccessibilityNodeInfo;
import com.google.android.material.internal.NavigationMenuView;

/* JADX INFO: loaded from: classes.dex */
public final class bh0 extends xn0 {
    public final /* synthetic */ eh0 f;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public bh0(eh0 eh0Var, NavigationMenuView navigationMenuView) {
        super(navigationMenuView);
        this.f = eh0Var;
    }

    @Override // defpackage.xn0, defpackage.y1
    public final void d(View view, j2 j2Var) {
        super.d(view, j2Var);
        wg0 wg0Var = this.f.I;
        int i = 0;
        int i2 = 0;
        while (true) {
            eh0 eh0Var = wg0Var.f;
            if (i >= eh0Var.I.c.size()) {
                j2Var.a.setCollectionInfo(AccessibilityNodeInfo.CollectionInfo.obtain(i2, 1, false));
                return;
            } else {
                int iC = eh0Var.I.c(i);
                if (iC == 0 || iC == 1) {
                    i2++;
                }
                i++;
            }
        }
    }
}
