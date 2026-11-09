package defpackage;

import android.view.animation.Interpolator;

/* JADX INFO: loaded from: classes.dex */
public final class nf0 implements Interpolator {
    public final /* synthetic */ vr a;

    public nf0(vr vrVar) {
        this.a = vrVar;
    }

    @Override // android.animation.TimeInterpolator
    public final float getInterpolation(float f) {
        return (float) this.a.b(f);
    }
}
