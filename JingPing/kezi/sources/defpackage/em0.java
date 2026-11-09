package defpackage;

import android.view.animation.Animation;
import android.view.animation.Transformation;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

/* JADX INFO: loaded from: classes.dex */
public final class em0 extends Animation {
    public final /* synthetic */ int b;
    public final /* synthetic */ int c;
    public final /* synthetic */ SwipeRefreshLayout d;

    public em0(SwipeRefreshLayout swipeRefreshLayout, int i, int i2) {
        this.d = swipeRefreshLayout;
        this.b = i;
        this.c = i2;
    }

    @Override // android.view.animation.Animation
    public final void applyTransformation(float f, Transformation transformation) {
        this.d.z.setAlpha((int) (((this.c - r0) * f) + this.b));
    }
}
