package defpackage;

import android.view.View;

/* JADX INFO: loaded from: classes.dex */
public final class rz extends z91 {
    public final /* synthetic */ vz b0;

    public rz(vz vzVar) {
        this.b0 = vzVar;
    }

    @Override // defpackage.z91
    public final View W(int i) {
        vz vzVar = this.b0;
        View view = vzVar.i0;
        if (view != null) {
            return view.findViewById(i);
        }
        throw new IllegalStateException("Fragment " + vzVar + " does not have a view");
    }

    @Override // defpackage.z91
    public final boolean X() {
        return this.b0.i0 != null;
    }
}
