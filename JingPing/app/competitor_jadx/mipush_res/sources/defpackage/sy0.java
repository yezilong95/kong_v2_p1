package defpackage;

import android.view.animation.Animation;
import android.view.animation.Transformation;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

/* JADX INFO: loaded from: classes.dex */
public final class sy0 extends Animation {
    public final /* synthetic */ int E;
    public final /* synthetic */ int F;
    public final /* synthetic */ SwipeRefreshLayout G;

    public sy0(SwipeRefreshLayout swipeRefreshLayout, int i, int i2) {
        this.G = swipeRefreshLayout;
        this.E = i;
        this.F = i2;
    }

    @Override // android.view.animation.Animation
    public final void applyTransformation(float f, Transformation transformation) {
        this.G.f0.setAlpha((int) (((this.F - r0) * f) + this.E));
    }
}
