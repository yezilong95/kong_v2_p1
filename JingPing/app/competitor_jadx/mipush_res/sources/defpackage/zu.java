package defpackage;

import android.animation.Animator;
import com.google.android.material.R$animator;
import com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton;

/* JADX INFO: loaded from: classes.dex */
public final class zu extends v9 {
    public final /* synthetic */ ExtendedFloatingActionButton g;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public zu(ExtendedFloatingActionButton extendedFloatingActionButton, p50 p50Var) {
        super(extendedFloatingActionButton, p50Var);
        this.g = extendedFloatingActionButton;
    }

    @Override // defpackage.v9
    public final int c() {
        return R$animator.mtrl_extended_fab_show_motion_spec;
    }

    @Override // defpackage.v9
    public final void e() {
        this.d.F = null;
        this.g.b0 = 0;
    }

    @Override // defpackage.v9
    public final void f(Animator animator) {
        p50 p50Var = this.d;
        Animator animator2 = (Animator) p50Var.F;
        if (animator2 != null) {
            animator2.cancel();
        }
        p50Var.F = animator;
        ExtendedFloatingActionButton extendedFloatingActionButton = this.g;
        extendedFloatingActionButton.setVisibility(0);
        extendedFloatingActionButton.b0 = 2;
    }

    @Override // defpackage.v9
    public final void g() {
        ExtendedFloatingActionButton extendedFloatingActionButton = this.g;
        extendedFloatingActionButton.setVisibility(0);
        extendedFloatingActionButton.setAlpha(1.0f);
        extendedFloatingActionButton.setScaleY(1.0f);
        extendedFloatingActionButton.setScaleX(1.0f);
    }

    @Override // defpackage.v9
    public final boolean h() {
        int i = ExtendedFloatingActionButton.q0;
        ExtendedFloatingActionButton extendedFloatingActionButton = this.g;
        if (extendedFloatingActionButton.getVisibility() != 0) {
            if (extendedFloatingActionButton.b0 != 2) {
                return false;
            }
        } else if (extendedFloatingActionButton.b0 == 1) {
            return false;
        }
        return true;
    }
}
