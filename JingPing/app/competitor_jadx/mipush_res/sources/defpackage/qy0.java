package defpackage;

import android.view.animation.Animation;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

/* JADX INFO: loaded from: classes.dex */
public final class qy0 implements Animation.AnimationListener {
    public final /* synthetic */ int a;
    public final /* synthetic */ SwipeRefreshLayout b;

    public /* synthetic */ qy0(SwipeRefreshLayout swipeRefreshLayout, int i) {
        this.a = i;
        this.b = swipeRefreshLayout;
    }

    @Override // android.view.animation.Animation.AnimationListener
    public final void onAnimationEnd(Animation animation) {
        switch (this.a) {
            case 0:
                SwipeRefreshLayout swipeRefreshLayout = this.b;
                if (!swipeRefreshLayout.F) {
                    swipeRefreshLayout.l();
                } else {
                    swipeRefreshLayout.f0.setAlpha(255);
                    swipeRefreshLayout.f0.start();
                    swipeRefreshLayout.Q = swipeRefreshLayout.W.getTop();
                }
                break;
            default:
                SwipeRefreshLayout swipeRefreshLayout2 = this.b;
                swipeRefreshLayout2.getClass();
                ry0 ry0Var = new ry0(swipeRefreshLayout2, 1);
                swipeRefreshLayout2.h0 = ry0Var;
                ry0Var.setDuration(150L);
                me meVar = swipeRefreshLayout2.W;
                meVar.E = null;
                meVar.clearAnimation();
                swipeRefreshLayout2.W.startAnimation(swipeRefreshLayout2.h0);
                break;
        }
    }

    @Override // android.view.animation.Animation.AnimationListener
    public final void onAnimationRepeat(Animation animation) {
        int i = this.a;
    }

    @Override // android.view.animation.Animation.AnimationListener
    public final void onAnimationStart(Animation animation) {
        int i = this.a;
    }

    private final void a(Animation animation) {
    }

    private final void b(Animation animation) {
    }

    private final void c(Animation animation) {
    }

    private final void d(Animation animation) {
    }
}
