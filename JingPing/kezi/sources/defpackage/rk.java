package defpackage;

import android.app.Dialog;
import android.content.DialogInterface;

/* JADX INFO: loaded from: classes.dex */
public final class rk implements DialogInterface.OnDismissListener {
    public final /* synthetic */ uk b;

    public rk(uk ukVar) {
        this.b = ukVar;
    }

    @Override // android.content.DialogInterface.OnDismissListener
    public final void onDismiss(DialogInterface dialogInterface) {
        uk ukVar = this.b;
        Dialog dialog = ukVar.d0;
        if (dialog != null) {
            ukVar.onDismiss(dialog);
        }
    }
}
