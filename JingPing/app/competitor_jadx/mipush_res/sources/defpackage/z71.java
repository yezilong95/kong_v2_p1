package defpackage;

import android.animation.ValueAnimator;
import android.os.Build;
import android.view.View;
import android.view.WindowInsets;
import java.util.Objects;
import java.util.WeakHashMap;

/* JADX INFO: loaded from: classes.dex */
public final class z71 implements View.OnApplyWindowInsetsListener {
    public final sh a;
    public s81 b;

    public z71(View view, sh shVar) {
        s81 s81VarB;
        this.a = shVar;
        WeakHashMap weakHashMap = f51.a;
        s81 s81VarA = u41.a(view);
        if (s81VarA != null) {
            int i = Build.VERSION.SDK_INT;
            s81VarB = (i >= 30 ? new i81(s81VarA) : i >= 29 ? new h81(s81VarA) : new g81(s81VarA)).b();
        } else {
            s81VarB = null;
        }
        this.b = s81VarB;
    }

    @Override // android.view.View.OnApplyWindowInsetsListener
    public final WindowInsets onApplyWindowInsets(View view, WindowInsets windowInsets) {
        p81 p81Var;
        if (!view.isLaidOut()) {
            this.b = s81.h(view, windowInsets);
            return a81.i(view, windowInsets);
        }
        s81 s81VarH = s81.h(view, windowInsets);
        if (this.b == null) {
            WeakHashMap weakHashMap = f51.a;
            this.b = u41.a(view);
        }
        if (this.b == null) {
            this.b = s81VarH;
            return a81.i(view, windowInsets);
        }
        sh shVarJ = a81.j(view);
        if (shVarJ != null && Objects.equals((WindowInsets) shVarJ.c, windowInsets)) {
            return a81.i(view, windowInsets);
        }
        s81 s81Var = this.b;
        int i = 1;
        int i2 = 0;
        while (true) {
            p81Var = s81VarH.a;
            if (i > 256) {
                break;
            }
            if (!p81Var.f(i).equals(s81Var.a.f(i))) {
                i2 |= i;
            }
            i <<= 1;
        }
        if (i2 == 0) {
            return a81.i(view, windowInsets);
        }
        s81 s81Var2 = this.b;
        e81 e81Var = new e81(i2, (i2 & 8) != 0 ? p81Var.f(8).d > s81Var2.a.f(8).d ? a81.e : a81.f : a81.g, 160L);
        e81Var.a.d(0.0f);
        ValueAnimator duration = ValueAnimator.ofFloat(0.0f, 1.0f).setDuration(e81Var.a.a());
        m40 m40VarF = p81Var.f(i2);
        m40 m40VarF2 = s81Var2.a.f(i2);
        int iMin = Math.min(m40VarF.a, m40VarF2.a);
        int i3 = m40VarF.b;
        int i4 = m40VarF2.b;
        int iMin2 = Math.min(i3, i4);
        int i5 = m40VarF.c;
        int i6 = m40VarF2.c;
        int iMin3 = Math.min(i5, i6);
        int i7 = m40VarF.d;
        int i8 = m40VarF2.d;
        int i9 = i2;
        int i10 = 4;
        n41 n41Var = new n41(m40.b(iMin, iMin2, iMin3, Math.min(i7, i8)), i10, m40.b(Math.max(m40VarF.a, m40VarF2.a), Math.max(i3, i4), Math.max(i5, i6), Math.max(i7, i8)));
        a81.f(view, windowInsets, false);
        duration.addUpdateListener(new y71(e81Var, s81VarH, s81Var2, i9, view));
        duration.addListener(new xp(e81Var, i10, view));
        mj0.a(view, new jn(view, e81Var, n41Var, duration));
        this.b = s81VarH;
        return a81.i(view, windowInsets);
    }
}
