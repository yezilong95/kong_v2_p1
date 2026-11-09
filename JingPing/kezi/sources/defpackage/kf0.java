package defpackage;

import android.animation.TimeInterpolator;
import android.view.animation.Interpolator;

/* JADX INFO: loaded from: classes.dex */
public final class kf0 implements TimeInterpolator {
    public final Interpolator a;

    public kf0(Interpolator interpolator) {
        this.a = interpolator;
    }

    public static TimeInterpolator a(boolean z, Interpolator interpolator) {
        return z ? interpolator : new kf0(interpolator);
    }

    @Override // android.animation.TimeInterpolator
    public final float getInterpolation(float f) {
        return 1.0f - this.a.getInterpolation(f);
    }
}
