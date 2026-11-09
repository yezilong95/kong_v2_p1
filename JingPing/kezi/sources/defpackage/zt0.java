package defpackage;

import android.view.View;
import androidx.appcompat.widget.ActionBarOverlayLayout;
import java.util.WeakHashMap;

/* JADX INFO: loaded from: classes.dex */
public final class zt0 extends za0 {
    public final /* synthetic */ int p;
    public final /* synthetic */ bu0 q;

    public /* synthetic */ zt0(bu0 bu0Var, int i) {
        this.p = i;
        this.q = bu0Var;
    }

    @Override // defpackage.dt0
    public final void a() {
        View view;
        bu0 bu0Var = this.q;
        switch (this.p) {
            case 0:
                if (bu0Var.o && (view = bu0Var.g) != null) {
                    view.setTranslationY(0.0f);
                    bu0Var.d.setTranslationY(0.0f);
                }
                bu0Var.d.setVisibility(8);
                bu0Var.d.setTransitioning(false);
                bu0Var.t = null;
                e5 e5Var = bu0Var.k;
                if (e5Var != null) {
                    e5Var.G(bu0Var.j);
                    bu0Var.j = null;
                    bu0Var.k = null;
                }
                ActionBarOverlayLayout actionBarOverlayLayout = bu0Var.c;
                if (actionBarOverlayLayout != null) {
                    WeakHashMap weakHashMap = xr0.a;
                    jr0.c(actionBarOverlayLayout);
                }
                break;
            default:
                bu0Var.t = null;
                bu0Var.d.requestLayout();
                break;
        }
    }
}
