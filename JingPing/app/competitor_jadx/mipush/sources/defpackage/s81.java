package defpackage;

import android.os.Build;
import android.view.View;
import android.view.WindowInsets;
import java.util.Objects;
import java.util.WeakHashMap;

/* JADX INFO: loaded from: classes.dex */
public final class s81 {
    public static final s81 b;
    public final p81 a;

    static {
        if (Build.VERSION.SDK_INT >= 30) {
            b = o81.q;
        } else {
            b = p81.b;
        }
    }

    public s81(WindowInsets windowInsets) {
        int i = Build.VERSION.SDK_INT;
        if (i >= 30) {
            this.a = new o81(this, windowInsets);
            return;
        }
        if (i >= 29) {
            this.a = new n81(this, windowInsets);
        } else if (i >= 28) {
            this.a = new m81(this, windowInsets);
        } else {
            this.a = new l81(this, windowInsets);
        }
    }

    public static m40 e(m40 m40Var, int i, int i2, int i3, int i4) {
        int iMax = Math.max(0, m40Var.a - i);
        int iMax2 = Math.max(0, m40Var.b - i2);
        int iMax3 = Math.max(0, m40Var.c - i3);
        int iMax4 = Math.max(0, m40Var.d - i4);
        return (iMax == i && iMax2 == i2 && iMax3 == i3 && iMax4 == i4) ? m40Var : m40.b(iMax, iMax2, iMax3, iMax4);
    }

    public static s81 h(View view, WindowInsets windowInsets) {
        windowInsets.getClass();
        s81 s81Var = new s81(windowInsets);
        if (view != null && view.isAttachedToWindow()) {
            WeakHashMap weakHashMap = f51.a;
            s81 s81VarA = u41.a(view);
            p81 p81Var = s81Var.a;
            p81Var.p(s81VarA);
            p81Var.d(view.getRootView());
        }
        return s81Var;
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
        if (!(obj instanceof s81)) {
            return false;
        }
        return Objects.equals(this.a, ((s81) obj).a);
    }

    public final s81 f(int i, int i2, int i3, int i4) {
        int i5 = Build.VERSION.SDK_INT;
        j81 i81Var = i5 >= 30 ? new i81(this) : i5 >= 29 ? new h81(this) : new g81(this);
        i81Var.g(m40.b(i, i2, i3, i4));
        return i81Var.b();
    }

    public final WindowInsets g() {
        p81 p81Var = this.a;
        if (p81Var instanceof k81) {
            return ((k81) p81Var).c;
        }
        return null;
    }

    public final int hashCode() {
        p81 p81Var = this.a;
        if (p81Var == null) {
            return 0;
        }
        return p81Var.hashCode();
    }

    public s81() {
        this.a = new p81(this);
    }
}
