package defpackage;

import android.app.Dialog;
import android.content.DialogInterface;

/* JADX INFO: loaded from: classes.dex */
public final class qk implements DialogInterface.OnCancelListener {
    public final /* synthetic */ uk b;

    public qk(uk ukVar) {
        this.b = ukVar;
    }

    @Override // android.content.DialogInterface.OnCancelListener
    public final void onCancel(DialogInterface dialogInterface) {
        uk ukVar = this.b;
        Dialog dialog = ukVar.d0;
        if (dialog != null) {
            ukVar.onCancel(dialog);
        }
    }
}
