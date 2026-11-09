package defpackage;

import android.view.animation.Interpolator;

/* JADX INFO: loaded from: classes.dex */
public final class t70 implements Interpolator {
    public final /* synthetic */ nm a;

    public t70(nm nmVar) {
        this.a = nmVar;
    }

    @Override // android.animation.TimeInterpolator
    public final float getInterpolation(float f) {
        return (float) this.a.a(f);
    }
}
