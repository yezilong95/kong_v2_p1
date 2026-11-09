package defpackage;

import android.app.Dialog;
import android.content.DialogInterface;

/* JADX INFO: loaded from: classes.dex */
public final class io implements DialogInterface.OnCancelListener {
    public final /* synthetic */ mo E;

    public io(mo moVar) {
        this.E = moVar;
    }

    @Override // android.content.DialogInterface.OnCancelListener
    public final void onCancel(DialogInterface dialogInterface) {
        mo moVar = this.E;
        Dialog dialog = moVar.G0;
        if (dialog != null) {
            moVar.onCancel(dialog);
        }
    }
}
