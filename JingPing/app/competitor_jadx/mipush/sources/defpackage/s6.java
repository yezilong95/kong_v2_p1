package defpackage;

import android.view.ViewTreeObserver;
import android.widget.PopupWindow;

/* JADX INFO: loaded from: classes.dex */
public final class s6 implements PopupWindow.OnDismissListener {
    public final /* synthetic */ q3 E;
    public final /* synthetic */ t6 F;

    public s6(t6 t6Var, q3 q3Var) {
        this.F = t6Var;
        this.E = q3Var;
    }

    @Override // android.widget.PopupWindow.OnDismissListener
    public final void onDismiss() {
        ViewTreeObserver viewTreeObserver = this.F.k0.getViewTreeObserver();
        if (viewTreeObserver != null) {
            viewTreeObserver.removeGlobalOnLayoutListener(this.E);
        }
    }
}
