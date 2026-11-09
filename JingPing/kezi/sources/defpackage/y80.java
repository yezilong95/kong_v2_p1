package defpackage;

import android.view.View;
import android.view.accessibility.AccessibilityNodeInfo;
import com.google.android.material.internal.NavigationMenuView;

/* JADX INFO: loaded from: classes.dex */
public final class y80 extends be0 {
    public final /* synthetic */ b90 f;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public y80(b90 b90Var, NavigationMenuView navigationMenuView) {
        super(navigationMenuView);
        this.f = b90Var;
    }

    @Override // defpackage.be0, defpackage.b0
    public final void d(View view, o0 o0Var) {
        super.d(view, o0Var);
        t80 t80Var = this.f.f;
        int i = 0;
        int i2 = 0;
        while (true) {
            b90 b90Var = t80Var.f;
            if (i >= b90Var.f.c.size()) {
                o0Var.a.setCollectionInfo(AccessibilityNodeInfo.CollectionInfo.obtain(i2, 1, false));
                return;
            } else {
                int iC = b90Var.f.c(i);
                if (iC == 0 || iC == 1) {
                    i2++;
                }
                i++;
            }
        }
    }
}
