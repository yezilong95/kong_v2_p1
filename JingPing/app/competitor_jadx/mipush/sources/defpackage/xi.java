package defpackage;

import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import androidx.coordinatorlayout.widget.CoordinatorLayout;

/* JADX INFO: loaded from: classes.dex */
public final class xi implements ViewTreeObserver.OnPreDrawListener {
    public final /* synthetic */ int E;
    public final /* synthetic */ Object F;

    public /* synthetic */ xi(int i, Object obj) {
        this.E = i;
        this.F = obj;
    }

    @Override // android.view.ViewTreeObserver.OnPreDrawListener
    public final boolean onPreDraw() {
        View view;
        switch (this.E) {
            case 0:
                ((CoordinatorLayout) this.F).u(0);
                break;
            case 1:
                xw xwVar = (xw) this.F;
                float rotation = xwVar.v.getRotation();
                if (xwVar.o != rotation) {
                    xwVar.o = rotation;
                    xwVar.q();
                }
                break;
            default:
                h20 h20Var = (h20) this.F;
                h20Var.postInvalidateOnAnimation();
                ViewGroup viewGroup = h20Var.E;
                if (viewGroup != null && (view = h20Var.F) != null) {
                    viewGroup.endViewTransition(view);
                    h20Var.E.postInvalidateOnAnimation();
                    h20Var.E = null;
                    h20Var.F = null;
                    break;
                }
                break;
        }
        return true;
    }
}
