package defpackage;

import android.animation.ValueAnimator;
import android.os.Build;
import android.view.View;
import android.view.WindowInsets;
import java.util.Objects;
import java.util.WeakHashMap;

/* JADX INFO: loaded from: classes.dex */
public final class eu0 implements View.OnApplyWindowInsetsListener {
    public final gg a;
    public xu0 b;

    public eu0(View view, gg ggVar) {
        xu0 xu0VarB;
        this.a = ggVar;
        WeakHashMap weakHashMap = xr0.a;
        xu0 xu0VarA = mr0.a(view);
        if (xu0VarA != null) {
            int i = Build.VERSION.SDK_INT;
            xu0VarB = (i >= 30 ? new nu0(xu0VarA) : i >= 29 ? new mu0(xu0VarA) : new lu0(xu0VarA)).b();
        } else {
            xu0VarB = null;
        }
        this.b = xu0VarB;
    }

    @Override // android.view.View.OnApplyWindowInsetsListener
    public final WindowInsets onApplyWindowInsets(View view, WindowInsets windowInsets) {
        uu0 uu0Var;
        if (!view.isLaidOut()) {
            this.b = xu0.h(view, windowInsets);
            return fu0.i(view, windowInsets);
        }
        xu0 xu0VarH = xu0.h(view, windowInsets);
        if (this.b == null) {
            WeakHashMap weakHashMap = xr0.a;
            this.b = mr0.a(view);
        }
        if (this.b == null) {
            this.b = xu0VarH;
            return fu0.i(view, windowInsets);
        }
        gg ggVarJ = fu0.j(view);
        if (ggVarJ != null && Objects.equals((WindowInsets) ggVarJ.c, windowInsets)) {
            return fu0.i(view, windowInsets);
        }
        xu0 xu0Var = this.b;
        int i = 1;
        int i2 = 0;
        while (true) {
            uu0Var = xu0VarH.a;
            if (i > 256) {
                break;
            }
            if (!uu0Var.f(i).equals(xu0Var.a.f(i))) {
                i2 |= i;
            }
            i <<= 1;
        }
        if (i2 == 0) {
            return fu0.i(view, windowInsets);
        }
        xu0 xu0Var2 = this.b;
        ju0 ju0Var = new ju0(i2, (i2 & 8) != 0 ? uu0Var.f(8).d > xu0Var2.a.f(8).d ? fu0.e : fu0.f : fu0.g, 160L);
        ju0Var.a.d(0.0f);
        ValueAnimator duration = ValueAnimator.ofFloat(0.0f, 1.0f).setDuration(ju0Var.a.a());
        jy jyVarF = uu0Var.f(i2);
        jy jyVarF2 = xu0Var2.a.f(i2);
        int iMin = Math.min(jyVarF.a, jyVarF2.a);
        int i3 = jyVarF.b;
        int i4 = jyVarF2.b;
        int iMin2 = Math.min(i3, i4);
        int i5 = jyVarF.c;
        int i6 = jyVarF2.c;
        int iMin3 = Math.min(i5, i6);
        int i7 = jyVarF.d;
        int i8 = jyVarF2.d;
        int i9 = i2;
        e5 e5Var = new e5(jy.b(iMin, iMin2, iMin3, Math.min(i7, i8)), jy.b(Math.max(jyVarF.a, jyVarF2.a), Math.max(i3, i4), Math.max(i5, i6), Math.max(i7, i8)), 27, false);
        fu0.f(view, windowInsets, false);
        duration.addUpdateListener(new du0(ju0Var, xu0VarH, xu0Var2, i9, view));
        duration.addListener(new ul(ju0Var, 3, view));
        xa0.a(view, new qj(view, ju0Var, e5Var, duration));
        this.b = xu0VarH;
        return fu0.i(view, windowInsets);
    }
}
