package defpackage;

import android.media.projection.MediaProjection;

/* JADX INFO: loaded from: classes.dex */
public final class nr0 extends MediaProjection.Callback {
    public final /* synthetic */ o71 a;
    public final /* synthetic */ or0 b;

    public nr0(or0 or0Var, o71 o71Var) {
        this.b = or0Var;
        this.a = o71Var;
    }

    @Override // android.media.projection.MediaProjection.Callback
    public final void onStop() {
        super.onStop();
        this.a.g = null;
        this.b.a();
    }
}
