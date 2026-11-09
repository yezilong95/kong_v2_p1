package defpackage;

import android.animation.AnimatorSet;
import android.view.View;
import com.google.android.material.bottomappbar.BottomAppBar;

/* JADX INFO: loaded from: classes.dex */
public final class u8 implements ht0 {
    public final /* synthetic */ BottomAppBar b;

    public /* synthetic */ u8(BottomAppBar bottomAppBar) {
        this.b = bottomAppBar;
    }

    @Override // defpackage.ht0
    public xu0 f(View view, xu0 xu0Var, it0 it0Var) {
        boolean z;
        BottomAppBar bottomAppBar = this.b;
        if (bottomAppBar.h0) {
            bottomAppBar.n0 = xu0Var.a();
        }
        boolean z2 = false;
        if (bottomAppBar.i0) {
            z = bottomAppBar.p0 != xu0Var.b();
            bottomAppBar.p0 = xu0Var.b();
        } else {
            z = false;
        }
        if (bottomAppBar.j0) {
            boolean z3 = bottomAppBar.o0 != xu0Var.c();
            bottomAppBar.o0 = xu0Var.c();
            z2 = z3;
        }
        if (!z && !z2) {
            return xu0Var;
        }
        AnimatorSet animatorSet = bottomAppBar.V;
        if (animatorSet != null) {
            animatorSet.cancel();
        }
        AnimatorSet animatorSet2 = bottomAppBar.U;
        if (animatorSet2 != null) {
            animatorSet2.cancel();
        }
        bottomAppBar.G();
        bottomAppBar.F();
        return xu0Var;
    }
}
