package defpackage;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.graphics.drawable.Drawable;
import android.view.View;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.transition.Transition;
import com.google.android.material.navigation.NavigationView;

/* JADX INFO: loaded from: classes.dex */
public final class ul extends AnimatorListenerAdapter {
    public final /* synthetic */ int a;
    public final /* synthetic */ Object b;
    public final /* synthetic */ Object c;

    public /* synthetic */ ul(Object obj, int i, Object obj2) {
        this.a = i;
        this.b = obj;
        this.c = obj2;
    }

    @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
    public final void onAnimationEnd(Animator animator) {
        switch (this.a) {
            case 0:
                NavigationView navigationView = (NavigationView) this.c;
                DrawerLayout drawerLayout = (DrawerLayout) this.b;
                drawerLayout.b(navigationView, false);
                drawerLayout.setScrimColor(-1728053248);
                break;
            case 1:
                ((gd) this.b).setCircularRevealOverlayDrawable(null);
                break;
            case 2:
                ((c7) this.b).remove(animator);
                ((Transition) this.c).n.remove(animator);
                break;
            default:
                ((ju0) this.b).a.d(1.0f);
                fu0.e((View) this.c);
                break;
        }
    }

    @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
    public void onAnimationStart(Animator animator) {
        switch (this.a) {
            case 1:
                ((gd) this.b).setCircularRevealOverlayDrawable((Drawable) this.c);
                break;
            case 2:
                ((Transition) this.c).n.add(animator);
                break;
            default:
                super.onAnimationStart(animator);
                break;
        }
    }

    public ul(Transition transition, c7 c7Var) {
        this.a = 2;
        this.c = transition;
        this.b = c7Var;
    }
}
