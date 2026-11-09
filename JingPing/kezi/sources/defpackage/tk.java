package defpackage;

import android.app.Dialog;
import android.view.View;

/* JADX INFO: loaded from: classes.dex */
public final class tk extends nv0 {
    public final /* synthetic */ et t;
    public final /* synthetic */ uk u;

    public tk(uk ukVar, et etVar) {
        this.u = ukVar;
        this.t = etVar;
    }

    @Override // defpackage.nv0
    public final View E(int i) {
        et etVar = this.t;
        if (etVar.F()) {
            return etVar.E(i);
        }
        Dialog dialog = this.u.d0;
        if (dialog != null) {
            return dialog.findViewById(i);
        }
        return null;
    }

    @Override // defpackage.nv0
    public final boolean F() {
        return this.t.F() || this.u.g0;
    }
}
