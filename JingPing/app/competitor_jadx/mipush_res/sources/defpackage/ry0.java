package defpackage;

import android.view.animation.Animation;
import android.view.animation.Transformation;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

/* JADX INFO: loaded from: classes.dex */
public final class ry0 extends Animation {
    public final /* synthetic */ int E;
    public final /* synthetic */ SwipeRefreshLayout F;

    public /* synthetic */ ry0(SwipeRefreshLayout swipeRefreshLayout, int i) {
        this.E = i;
        this.F = swipeRefreshLayout;
    }

    @Override // android.view.animation.Animation
    public final void applyTransformation(float f, Transformation transformation) {
        switch (this.E) {
            case 0:
                this.F.setAnimationProgress(f);
                break;
            case 1:
                this.F.setAnimationProgress(1.0f - f);
                break;
            case 2:
                SwipeRefreshLayout swipeRefreshLayout = this.F;
                int iAbs = swipeRefreshLayout.d0 - Math.abs(swipeRefreshLayout.c0);
                swipeRefreshLayout.setTargetOffsetTopAndBottom((swipeRefreshLayout.b0 + ((int) ((iAbs - r1) * f))) - swipeRefreshLayout.W.getTop());
                te teVar = swipeRefreshLayout.f0;
                float f2 = 1.0f - f;
                se seVar = teVar.E;
                if (f2 != seVar.p) {
                    seVar.p = f2;
                }
                teVar.invalidateSelf();
                break;
            default:
                this.F.k(f);
                break;
        }
    }
}
