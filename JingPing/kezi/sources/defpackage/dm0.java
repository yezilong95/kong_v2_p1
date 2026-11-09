package defpackage;

import android.view.animation.Animation;
import android.view.animation.Transformation;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

/* JADX INFO: loaded from: classes.dex */
public final class dm0 extends Animation {
    public final /* synthetic */ int b;
    public final /* synthetic */ SwipeRefreshLayout c;

    public /* synthetic */ dm0(SwipeRefreshLayout swipeRefreshLayout, int i) {
        this.b = i;
        this.c = swipeRefreshLayout;
    }

    @Override // android.view.animation.Animation
    public final void applyTransformation(float f, Transformation transformation) {
        switch (this.b) {
            case 0:
                this.c.setAnimationProgress(f);
                break;
            case 1:
                this.c.setAnimationProgress(1.0f - f);
                break;
            case 2:
                SwipeRefreshLayout swipeRefreshLayout = this.c;
                int iAbs = swipeRefreshLayout.x - Math.abs(swipeRefreshLayout.w);
                swipeRefreshLayout.setTargetOffsetTopAndBottom((swipeRefreshLayout.v + ((int) ((iAbs - r1) * f))) - swipeRefreshLayout.t.getTop());
                zc zcVar = swipeRefreshLayout.z;
                float f2 = 1.0f - f;
                yc ycVar = zcVar.b;
                if (f2 != ycVar.p) {
                    ycVar.p = f2;
                }
                zcVar.invalidateSelf();
                break;
            default:
                this.c.k(f);
                break;
        }
    }
}
