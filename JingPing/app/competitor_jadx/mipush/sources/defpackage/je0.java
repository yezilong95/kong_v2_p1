package defpackage;

import android.widget.PopupWindow;

/* JADX INFO: loaded from: classes.dex */
public final class je0 implements PopupWindow.OnDismissListener {
    public final /* synthetic */ le0 E;

    public je0(le0 le0Var) {
        this.E = le0Var;
    }

    @Override // android.widget.PopupWindow.OnDismissListener
    public final void onDismiss() {
        this.E.c();
    }
}
