package defpackage;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.view.View;
import com.google.android.material.bottomappbar.BottomAppBar;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

/* JADX INFO: loaded from: classes.dex */
public final class t8 extends AnimatorListenerAdapter {
    public final /* synthetic */ int a;
    public final /* synthetic */ BottomAppBar b;

    public /* synthetic */ t8(BottomAppBar bottomAppBar, int i) {
        this.a = i;
        this.b = bottomAppBar;
    }

    @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
    public void onAnimationEnd(Animator animator) {
        BottomAppBar bottomAppBar = this.b;
        switch (this.a) {
            case 1:
                int i = BottomAppBar.s0;
                bottomAppBar.U = null;
                break;
            case 2:
                int i2 = BottomAppBar.s0;
                bottomAppBar.getClass();
                bottomAppBar.k0 = false;
                bottomAppBar.V = null;
                break;
            default:
                super.onAnimationEnd(animator);
                break;
        }
    }

    @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
    public final void onAnimationStart(Animator animator) {
        BottomAppBar bottomAppBar = this.b;
        switch (this.a) {
            case 0:
                if (!bottomAppBar.k0) {
                    bottomAppBar.E(bottomAppBar.W, bottomAppBar.l0);
                }
                break;
            case 1:
                int i = BottomAppBar.s0;
                break;
            case 2:
                int i2 = BottomAppBar.s0;
                bottomAppBar.getClass();
                break;
            default:
                bottomAppBar.q0.onAnimationStart(animator);
                View viewA = bottomAppBar.A();
                FloatingActionButton floatingActionButton = viewA instanceof FloatingActionButton ? (FloatingActionButton) viewA : null;
                if (floatingActionButton != null) {
                    floatingActionButton.setTranslationX(bottomAppBar.getFabTranslationX());
                }
                break;
        }
    }
}
