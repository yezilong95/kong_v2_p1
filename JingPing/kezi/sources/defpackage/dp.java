package defpackage;

import android.animation.Animator;
import com.google.android.material.R$animator;
import com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton;

/* JADX INFO: loaded from: classes.dex */
public final class dp extends z7 {
    public boolean g;
    public final /* synthetic */ ExtendedFloatingActionButton h;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public dp(ExtendedFloatingActionButton extendedFloatingActionButton, v3 v3Var) {
        super(extendedFloatingActionButton, v3Var);
        this.h = extendedFloatingActionButton;
    }

    @Override // defpackage.z7
    public final int c() {
        return R$animator.mtrl_extended_fab_hide_motion_spec;
    }

    @Override // defpackage.z7
    public final void d() {
        super.d();
        this.g = true;
    }

    @Override // defpackage.z7
    public final void e() {
        this.d.b = null;
        ExtendedFloatingActionButton extendedFloatingActionButton = this.h;
        extendedFloatingActionButton.v = 0;
        if (this.g) {
            return;
        }
        extendedFloatingActionButton.setVisibility(8);
    }

    @Override // defpackage.z7
    public final void f(Animator animator) {
        v3 v3Var = this.d;
        Animator animator2 = v3Var.b;
        if (animator2 != null) {
            animator2.cancel();
        }
        v3Var.b = animator;
        this.g = false;
        ExtendedFloatingActionButton extendedFloatingActionButton = this.h;
        extendedFloatingActionButton.setVisibility(0);
        extendedFloatingActionButton.v = 1;
    }

    @Override // defpackage.z7
    public final void g() {
        this.h.setVisibility(8);
    }

    @Override // defpackage.z7
    public final boolean h() {
        int i = ExtendedFloatingActionButton.K;
        ExtendedFloatingActionButton extendedFloatingActionButton = this.h;
        if (extendedFloatingActionButton.getVisibility() == 0) {
            if (extendedFloatingActionButton.v != 1) {
                return false;
            }
        } else if (extendedFloatingActionButton.v == 2) {
            return false;
        }
        return true;
    }
}
