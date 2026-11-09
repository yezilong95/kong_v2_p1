package defpackage;

import android.animation.Animator;
import com.google.android.material.R$animator;
import com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton;

/* JADX INFO: loaded from: classes.dex */
public final class yu extends v9 {
    public boolean g;
    public final /* synthetic */ ExtendedFloatingActionButton h;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public yu(ExtendedFloatingActionButton extendedFloatingActionButton, p50 p50Var) {
        super(extendedFloatingActionButton, p50Var);
        this.h = extendedFloatingActionButton;
    }

    @Override // defpackage.v9
    public final int c() {
        return R$animator.mtrl_extended_fab_hide_motion_spec;
    }

    @Override // defpackage.v9
    public final void d() {
        super.d();
        this.g = true;
    }

    @Override // defpackage.v9
    public final void e() {
        this.d.F = null;
        ExtendedFloatingActionButton extendedFloatingActionButton = this.h;
        extendedFloatingActionButton.b0 = 0;
        if (this.g) {
            return;
        }
        extendedFloatingActionButton.setVisibility(8);
    }

    @Override // defpackage.v9
    public final void f(Animator animator) {
        p50 p50Var = this.d;
        Animator animator2 = (Animator) p50Var.F;
        if (animator2 != null) {
            animator2.cancel();
        }
        p50Var.F = animator;
        this.g = false;
        ExtendedFloatingActionButton extendedFloatingActionButton = this.h;
        extendedFloatingActionButton.setVisibility(0);
        extendedFloatingActionButton.b0 = 1;
    }

    @Override // defpackage.v9
    public final void g() {
        this.h.setVisibility(8);
    }

    @Override // defpackage.v9
    public final boolean h() {
        int i = ExtendedFloatingActionButton.q0;
        ExtendedFloatingActionButton extendedFloatingActionButton = this.h;
        if (extendedFloatingActionButton.getVisibility() == 0) {
            if (extendedFloatingActionButton.b0 != 1) {
                return false;
            }
        } else if (extendedFloatingActionButton.b0 == 2) {
            return false;
        }
        return true;
    }
}
