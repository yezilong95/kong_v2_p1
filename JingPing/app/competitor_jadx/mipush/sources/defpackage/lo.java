package defpackage;

import android.app.Dialog;
import android.view.View;

/* JADX INFO: loaded from: classes.dex */
public final class lo extends z91 {
    public final /* synthetic */ rz b0;
    public final /* synthetic */ mo c0;

    public lo(mo moVar, rz rzVar) {
        this.c0 = moVar;
        this.b0 = rzVar;
    }

    @Override // defpackage.z91
    public final View W(int i) {
        rz rzVar = this.b0;
        if (rzVar.X()) {
            return rzVar.W(i);
        }
        Dialog dialog = this.c0.G0;
        if (dialog != null) {
            return dialog.findViewById(i);
        }
        return null;
    }

    @Override // defpackage.z91
    public final boolean X() {
        return this.b0.X() || this.c0.J0;
    }
}
