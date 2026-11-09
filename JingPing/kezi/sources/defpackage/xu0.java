package defpackage;

import android.os.Build;
import android.view.View;
import android.view.WindowInsets;
import java.util.Objects;
import java.util.WeakHashMap;

/* JADX INFO: loaded from: classes.dex */
public final class xu0 {
    public static final xu0 b;
    public final uu0 a;

    static {
        if (Build.VERSION.SDK_INT >= 30) {
            b = tu0.q;
        } else {
            b = uu0.b;
        }
    }

    public xu0(WindowInsets windowInsets) {
        int i = Build.VERSION.SDK_INT;
        if (i >= 30) {
            this.a = new tu0(this, windowInsets);
            return;
        }
        if (i >= 29) {
            this.a = new su0(this, windowInsets);
        } else if (i >= 28) {
            this.a = new ru0(this, windowInsets);
        } else {
            this.a = new qu0(this, windowInsets);
        }
    }

    public static jy e(jy jyVar, int i, int i2, int i3, int i4) {
        int iMax = Math.max(0, jyVar.a - i);
        int iMax2 = Math.max(0, jyVar.b - i2);
        int iMax3 = Math.max(0, jyVar.c - i3);
        int iMax4 = Math.max(0, jyVar.d - i4);
        return (iMax == i && iMax2 == i2 && iMax3 == i3 && iMax4 == i4) ? jyVar : jy.b(iMax, iMax2, iMax3, iMax4);
    }

    public static xu0 h(View view, WindowInsets windowInsets) {
        windowInsets.getClass();
        xu0 xu0Var = new xu0(windowInsets);
        if (view != null && view.isAttachedToWindow()) {
            WeakHashMap weakHashMap = xr0.a;
            xu0 xu0VarA = mr0.a(view);
            uu0 uu0Var = xu0Var.a;
            uu0Var.p(xu0VarA);
            uu0Var.d(view.getRootView());
        }
        return xu0Var;
    }

    public final int a() {
        return this.a.j().d;
    }

    public final int b() {
        return this.a.j().a;
    }

    public final int c() {
        return this.a.j().c;
    }

    public final int d() {
        return this.a.j().b;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof xu0)) {
            return false;
        }
        return Objects.equals(this.a, ((xu0) obj).a);
    }

    public final xu0 f(int i, int i2, int i3, int i4) {
        int i5 = Build.VERSION.SDK_INT;
        ou0 nu0Var = i5 >= 30 ? new nu0(this) : i5 >= 29 ? new mu0(this) : new lu0(this);
        nu0Var.g(jy.b(i, i2, i3, i4));
        return nu0Var.b();
    }

    public final WindowInsets g() {
        uu0 uu0Var = this.a;
        if (uu0Var instanceof pu0) {
            return ((pu0) uu0Var).c;
        }
        return null;
    }

    public final int hashCode() {
        uu0 uu0Var = this.a;
        if (uu0Var == null) {
            return 0;
        }
        return uu0Var.hashCode();
    }

    public xu0() {
        this.a = new uu0(this);
    }
}
