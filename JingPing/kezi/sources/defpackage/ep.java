package defpackage;

import android.animation.Animator;
import com.google.android.material.R$animator;
import com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton;

/* JADX INFO: loaded from: classes.dex */
public final class ep extends z7 {
    public final /* synthetic */ ExtendedFloatingActionButton g;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public ep(ExtendedFloatingActionButton extendedFloatingActionButton, v3 v3Var) {
        super(extendedFloatingActionButton, v3Var);
        this.g = extendedFloatingActionButton;
    }

    @Override // defpackage.z7
    public final int c() {
        return R$animator.mtrl_extended_fab_show_motion_spec;
    }

    @Override // defpackage.z7
    public final void e() {
        this.d.b = null;
        this.g.v = 0;
    }

    @Override // defpackage.z7
    public final void f(Animator animator) {
        v3 v3Var = this.d;
        Animator animator2 = v3Var.b;
        if (animator2 != null) {
            animator2.cancel();
        }
        v3Var.b = animator;
        ExtendedFloatingActionButton extendedFloatingActionButton = this.g;
        extendedFloatingActionButton.setVisibility(0);
        extendedFloatingActionButton.v = 2;
    }

    @Override // defpackage.z7
    public final void g() {
        ExtendedFloatingActionButton extendedFloatingActionButton = this.g;
        extendedFloatingActionButton.setVisibility(0);
        extendedFloatingActionButton.setAlpha(1.0f);
        extendedFloatingActionButton.setScaleY(1.0f);
        extendedFloatingActionButton.setScaleX(1.0f);
    }

    @Override // defpackage.z7
    public final boolean h() {
        int i = ExtendedFloatingActionButton.K;
        ExtendedFloatingActionButton extendedFloatingActionButton = this.g;
        if (extendedFloatingActionButton.getVisibility() != 0) {
            if (extendedFloatingActionButton.v != 2) {
                return false;
            }
        } else if (extendedFloatingActionButton.v == 1) {
            return false;
        }
        return true;
    }
}
