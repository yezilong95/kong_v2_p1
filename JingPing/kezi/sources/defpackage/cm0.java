package defpackage;

import android.view.animation.Animation;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

/* JADX INFO: loaded from: classes.dex */
public final class cm0 implements Animation.AnimationListener {
    public final /* synthetic */ int a;
    public final /* synthetic */ SwipeRefreshLayout b;

    public /* synthetic */ cm0(SwipeRefreshLayout swipeRefreshLayout, int i) {
        this.a = i;
        this.b = swipeRefreshLayout;
    }

    @Override // android.view.animation.Animation.AnimationListener
    public final void onAnimationEnd(Animation animation) {
        switch (this.a) {
            case 0:
                SwipeRefreshLayout swipeRefreshLayout = this.b;
                if (!swipeRefreshLayout.c) {
                    swipeRefreshLayout.l();
                } else {
                    swipeRefreshLayout.z.setAlpha(255);
                    swipeRefreshLayout.z.start();
                    swipeRefreshLayout.n = swipeRefreshLayout.t.getTop();
                }
                break;
            default:
                SwipeRefreshLayout swipeRefreshLayout2 = this.b;
                swipeRefreshLayout2.getClass();
                dm0 dm0Var = new dm0(swipeRefreshLayout2, 1);
                swipeRefreshLayout2.B = dm0Var;
                dm0Var.setDuration(150L);
                sc scVar = swipeRefreshLayout2.t;
                scVar.b = null;
                scVar.clearAnimation();
                swipeRefreshLayout2.t.startAnimation(swipeRefreshLayout2.B);
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
