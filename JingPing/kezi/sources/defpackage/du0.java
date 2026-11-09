package defpackage;

import android.animation.ValueAnimator;
import android.os.Build;
import android.view.View;
import android.view.animation.PathInterpolator;
import java.util.Collections;

/* JADX INFO: loaded from: classes.dex */
public final class du0 implements ValueAnimator.AnimatorUpdateListener {
    public final /* synthetic */ ju0 a;
    public final /* synthetic */ xu0 b;
    public final /* synthetic */ xu0 c;
    public final /* synthetic */ int d;
    public final /* synthetic */ View e;

    public du0(ju0 ju0Var, xu0 xu0Var, xu0 xu0Var2, int i, View view) {
        this.a = ju0Var;
        this.b = xu0Var;
        this.c = xu0Var2;
        this.d = i;
        this.e = view;
    }

    @Override // android.animation.ValueAnimator.AnimatorUpdateListener
    public final void onAnimationUpdate(ValueAnimator valueAnimator) {
        float f;
        int i;
        float animatedFraction = valueAnimator.getAnimatedFraction();
        ju0 ju0Var = this.a;
        ju0Var.a.d(animatedFraction);
        float fB = ju0Var.a.b();
        PathInterpolator pathInterpolator = fu0.e;
        int i2 = Build.VERSION.SDK_INT;
        xu0 xu0Var = this.b;
        ou0 nu0Var = i2 >= 30 ? new nu0(xu0Var) : i2 >= 29 ? new mu0(xu0Var) : new lu0(xu0Var);
        int i3 = 1;
        while (i3 <= 256) {
            int i4 = this.d & i3;
            uu0 uu0Var = xu0Var.a;
            if (i4 == 0) {
                nu0Var.c(i3, uu0Var.f(i3));
                f = fB;
                i = 1;
            } else {
                jy jyVarF = uu0Var.f(i3);
                jy jyVarF2 = this.c.a.f(i3);
                float f2 = 1.0f - fB;
                f = fB;
                i = 1;
                nu0Var.c(i3, xu0.e(jyVarF, (int) (((double) ((jyVarF.a - jyVarF2.a) * f2)) + 0.5d), (int) (((double) ((jyVarF.b - jyVarF2.b) * f2)) + 0.5d), (int) (((double) ((jyVarF.c - jyVarF2.c) * f2)) + 0.5d), (int) (((double) ((jyVarF.d - jyVarF2.d) * f2)) + 0.5d)));
            }
            i3 <<= i;
            fB = f;
        }
        fu0.g(this.e, nu0Var.b(), Collections.singletonList(ju0Var));
    }
}
