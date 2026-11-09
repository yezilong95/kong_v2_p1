package defpackage;

import android.view.ViewTreeObserver;
import android.widget.PopupWindow;

/* JADX INFO: loaded from: classes.dex */
public final class o5 implements PopupWindow.OnDismissListener {
    public final /* synthetic */ v1 b;
    public final /* synthetic */ p5 c;

    public o5(p5 p5Var, v1 v1Var) {
        this.c = p5Var;
        this.b = v1Var;
    }

    @Override // android.widget.PopupWindow.OnDismissListener
    public final void onDismiss() {
        ViewTreeObserver viewTreeObserver = this.c.H.getViewTreeObserver();
        if (viewTreeObserver != null) {
            viewTreeObserver.removeGlobalOnLayoutListener(this.b);
        }
    }
}
