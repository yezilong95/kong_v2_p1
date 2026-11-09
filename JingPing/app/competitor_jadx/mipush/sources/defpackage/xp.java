package defpackage;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.graphics.drawable.Drawable;
import android.view.View;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.transition.Transition;
import com.google.android.material.navigation.NavigationView;
import com.mamimobi.videostream.view.PatternLockView;

/* JADX INFO: loaded from: classes.dex */
public final class xp extends AnimatorListenerAdapter {
    public final /* synthetic */ int a;
    public final /* synthetic */ Object b;
    public final /* synthetic */ Object c;

    public /* synthetic */ xp(int i, Object obj, Object obj2, boolean z) {
        this.a = i;
        this.c = obj;
        this.b = obj2;
    }

    @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
    public void onAnimationCancel(Animator animator) {
        switch (this.a) {
            case 2:
                rk0 rk0Var = (rk0) this.b;
                rk0Var.b = false;
                rk0Var.c = null;
                int i = PatternLockView.k0;
                ((PatternLockView) this.c).getClass();
                break;
            default:
                super.onAnimationCancel(animator);
                break;
        }
    }

    @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
    public final void onAnimationEnd(Animator animator) {
        Object obj = this.c;
        Object obj2 = this.b;
        switch (this.a) {
            case 0:
                DrawerLayout drawerLayout = (DrawerLayout) obj2;
                drawerLayout.b((NavigationView) obj, false);
                drawerLayout.setScrimColor(-1728053248);
                break;
            case 1:
                ((af) obj2).setCircularRevealOverlayDrawable(null);
                break;
            case 2:
                rk0 rk0Var = (rk0) obj2;
                rk0Var.b = false;
                rk0Var.c = null;
                int i = PatternLockView.k0;
                ((PatternLockView) obj).getClass();
                break;
            case 3:
                ((k8) obj2).remove(animator);
                ((Transition) obj).R.remove(animator);
                break;
            default:
                ((e81) obj2).a.d(1.0f);
                a81.e((View) obj);
                break;
        }
    }

    @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
    public void onAnimationStart(Animator animator) {
        switch (this.a) {
            case 1:
                ((af) this.b).setCircularRevealOverlayDrawable((Drawable) this.c);
                break;
            case 2:
            default:
                super.onAnimationStart(animator);
                break;
            case 3:
                ((Transition) this.c).R.add(animator);
                break;
        }
    }

    public /* synthetic */ xp(Object obj, int i, Object obj2) {
        this.a = i;
        this.b = obj;
        this.c = obj2;
    }
}
