package defpackage;

import android.view.View;
import androidx.appcompat.widget.ActionBarOverlayLayout;
import java.util.WeakHashMap;

/* JADX INFO: loaded from: classes.dex */
public final class v71 extends s90 {
    public final /* synthetic */ int w;
    public final /* synthetic */ x71 x;

    public /* synthetic */ v71(x71 x71Var, int i) {
        this.w = i;
        this.x = x71Var;
    }

    @Override // defpackage.j61
    public final void a() {
        View view;
        x71 x71Var = this.x;
        switch (this.w) {
            case 0:
                if (x71Var.o && (view = x71Var.g) != null) {
                    view.setTranslationY(0.0f);
                    x71Var.d.setTranslationY(0.0f);
                }
                x71Var.d.setVisibility(8);
                x71Var.d.setTransitioning(false);
                x71Var.s = null;
                i6 i6Var = x71Var.k;
                if (i6Var != null) {
                    i6Var.F(x71Var.j);
                    x71Var.j = null;
                    x71Var.k = null;
                }
                ActionBarOverlayLayout actionBarOverlayLayout = x71Var.c;
                if (actionBarOverlayLayout != null) {
                    WeakHashMap weakHashMap = f51.a;
                    r41.c(actionBarOverlayLayout);
                }
                break;
            default:
                x71Var.s = null;
                x71Var.d.requestLayout();
                break;
        }
    }
}
