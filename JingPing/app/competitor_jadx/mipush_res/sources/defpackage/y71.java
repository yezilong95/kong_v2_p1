package defpackage;

import android.animation.ValueAnimator;
import android.os.Build;
import android.view.View;
import android.view.animation.PathInterpolator;
import java.util.Collections;

/* JADX INFO: loaded from: classes.dex */
public final class y71 implements ValueAnimator.AnimatorUpdateListener {
    public final /* synthetic */ e81 a;
    public final /* synthetic */ s81 b;
    public final /* synthetic */ s81 c;
    public final /* synthetic */ int d;
    public final /* synthetic */ View e;

    public y71(e81 e81Var, s81 s81Var, s81 s81Var2, int i, View view) {
        this.a = e81Var;
        this.b = s81Var;
        this.c = s81Var2;
        this.d = i;
        this.e = view;
    }

    @Override // android.animation.ValueAnimator.AnimatorUpdateListener
    public final void onAnimationUpdate(ValueAnimator valueAnimator) {
        float f;
        int i;
        float animatedFraction = valueAnimator.getAnimatedFraction();
        e81 e81Var = this.a;
        e81Var.a.d(animatedFraction);
        float fB = e81Var.a.b();
        PathInterpolator pathInterpolator = a81.e;
        int i2 = Build.VERSION.SDK_INT;
        s81 s81Var = this.b;
        j81 i81Var = i2 >= 30 ? new i81(s81Var) : i2 >= 29 ? new h81(s81Var) : new g81(s81Var);
        int i3 = 1;
        while (i3 <= 256) {
            int i4 = this.d & i3;
            p81 p81Var = s81Var.a;
            if (i4 == 0) {
                i81Var.c(i3, p81Var.f(i3));
                f = fB;
                i = 1;
            } else {
                m40 m40VarF = p81Var.f(i3);
                m40 m40VarF2 = this.c.a.f(i3);
                float f2 = 1.0f - fB;
                f = fB;
                i = 1;
                i81Var.c(i3, s81.e(m40VarF, (int) (((double) ((m40VarF.a - m40VarF2.a) * f2)) + 0.5d), (int) (((double) ((m40VarF.b - m40VarF2.b) * f2)) + 0.5d), (int) (((double) ((m40VarF.c - m40VarF2.c) * f2)) + 0.5d), (int) (((double) ((m40VarF.d - m40VarF2.d) * f2)) + 0.5d)));
            }
            i3 <<= i;
            fB = f;
        }
        a81.g(this.e, i81Var.b(), Collections.singletonList(e81Var));
    }
}
