package defpackage;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.view.View;
import com.google.android.material.bottomappbar.BottomAppBar;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

/* JADX INFO: loaded from: classes.dex */
public final class sa extends AnimatorListenerAdapter {
    public final /* synthetic */ int a;
    public final /* synthetic */ BottomAppBar b;

    public /* synthetic */ sa(BottomAppBar bottomAppBar, int i) {
        this.a = i;
        this.b = bottomAppBar;
    }

    @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
    public void onAnimationEnd(Animator animator) {
        BottomAppBar bottomAppBar = this.b;
        switch (this.a) {
            case 1:
                int i = BottomAppBar.V0;
                bottomAppBar.A0 = null;
                break;
            case 2:
                int i2 = BottomAppBar.V0;
                bottomAppBar.getClass();
                bottomAppBar.N0 = false;
                bottomAppBar.B0 = null;
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
                if (!bottomAppBar.N0) {
                    bottomAppBar.E(bottomAppBar.C0, bottomAppBar.O0);
                }
                break;
            case 1:
                int i = BottomAppBar.V0;
                break;
            case 2:
                int i2 = BottomAppBar.V0;
                bottomAppBar.getClass();
                break;
            default:
                bottomAppBar.T0.onAnimationStart(animator);
                View viewA = bottomAppBar.A();
                FloatingActionButton floatingActionButton = viewA instanceof FloatingActionButton ? (FloatingActionButton) viewA : null;
                if (floatingActionButton != null) {
                    floatingActionButton.setTranslationX(bottomAppBar.getFabTranslationX());
                }
                break;
        }
    }
}
