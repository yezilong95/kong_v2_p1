package defpackage;

import android.app.Dialog;
import android.content.DialogInterface;

/* JADX INFO: loaded from: classes.dex */
public final class jo implements DialogInterface.OnDismissListener {
    public final /* synthetic */ mo E;

    public jo(mo moVar) {
        this.E = moVar;
    }

    @Override // android.content.DialogInterface.OnDismissListener
    public final void onDismiss(DialogInterface dialogInterface) {
        mo moVar = this.E;
        Dialog dialog = moVar.G0;
        if (dialog != null) {
            moVar.onDismiss(dialog);
        }
    }
}
