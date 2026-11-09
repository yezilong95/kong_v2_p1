package defpackage;

import android.widget.PopupWindow;

/* JADX INFO: loaded from: classes.dex */
public final class s60 implements PopupWindow.OnDismissListener {
    public final /* synthetic */ u60 b;

    public s60(u60 u60Var) {
        this.b = u60Var;
    }

    @Override // android.widget.PopupWindow.OnDismissListener
    public final void onDismiss() {
        this.b.c();
    }
}
