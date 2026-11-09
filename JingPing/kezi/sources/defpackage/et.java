package defpackage;

import android.view.View;

/* JADX INFO: loaded from: classes.dex */
public final class et extends nv0 {
    public final /* synthetic */ jt t;

    public et(jt jtVar) {
        this.t = jtVar;
    }

    @Override // defpackage.nv0
    public final View E(int i) {
        jt jtVar = this.t;
        View view = jtVar.F;
        if (view != null) {
            return view.findViewById(i);
        }
        throw new IllegalStateException("Fragment " + jtVar + " does not have a view");
    }

    @Override // defpackage.nv0
    public final boolean F() {
        return this.t.F != null;
    }
}
