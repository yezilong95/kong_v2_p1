package defpackage;

import android.annotation.SuppressLint;
import android.graphics.Rect;
import android.os.Build;
import android.util.Log;
import android.view.View;
import android.view.WindowInsets;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Objects;

/* JADX INFO: loaded from: classes.dex */
public abstract class pu0 extends uu0 {
    public static boolean h = false;
    public static Method i;
    public static Class j;
    public static Field k;
    public static Field l;
    public final WindowInsets c;
    public jy[] d;
    public jy e;
    public xu0 f;
    public jy g;

    public pu0(xu0 xu0Var, WindowInsets windowInsets) {
        super(xu0Var);
        this.e = null;
        this.c = windowInsets;
    }

    @SuppressLint({"WrongConstant"})
    private jy r(int i2, boolean z) {
        jy jyVarA = jy.e;
        for (int i3 = 1; i3 <= 256; i3 <<= 1) {
            if ((i2 & i3) != 0) {
                jyVarA = jy.a(jyVarA, s(i3, z));
            }
        }
        return jyVarA;
    }

    private jy t() {
        xu0 xu0Var = this.f;
        return xu0Var != null ? xu0Var.a.h() : jy.e;
    }

    private jy u(View view) {
        if (Build.VERSION.SDK_INT >= 30) {
            throw new UnsupportedOperationException("getVisibleInsets() should not be called on API >= 30. Use WindowInsets.isVisible() instead.");
        }
        if (!h) {
            v();
        }
        Method method = i;
        if (method != null && j != null && k != null) {
            try {
                Object objInvoke = method.invoke(view, new Object[0]);
                if (objInvoke == null) {
                    Log.w("WindowInsetsCompat", "Failed to get visible insets. getViewRootImpl() returned null from the provided view. This means that the view is either not attached or the method has been overridden", new NullPointerException());
                    return null;
                }
                Rect rect = (Rect) k.get(l.get(objInvoke));
                if (rect != null) {
                    return jy.b(rect.left, rect.top, rect.right, rect.bottom);
                }
            } catch (ReflectiveOperationException e) {
                Log.e("WindowInsetsCompat", "Failed to get visible insets. (Reflection error). " + e.getMessage(), e);
            }
        }
        return null;
    }

    @SuppressLint({"PrivateApi"})
    private static void v() {
        try {
            i = View.class.getDeclaredMethod("getViewRootImpl", new Class[0]);
            Class<?> cls = Class.forName("android.view.View$AttachInfo");
            j = cls;
            k = cls.getDeclaredField("mVisibleInsets");
            l = Class.forName("android.view.ViewRootImpl").getDeclaredField("mAttachInfo");
            k.setAccessible(true);
            l.setAccessible(true);
        } catch (ReflectiveOperationException e) {
            Log.e("WindowInsetsCompat", "Failed to get visible insets. (Reflection error). " + e.getMessage(), e);
        }
        h = true;
    }

    @Override // defpackage.uu0
    public void d(View view) {
        jy jyVarU = u(view);
        if (jyVarU == null) {
            jyVarU = jy.e;
        }
        w(jyVarU);
    }

    @Override // defpackage.uu0
    public boolean equals(Object obj) {
        if (super.equals(obj)) {
            return Objects.equals(this.g, ((pu0) obj).g);
        }
        return false;
    }

    @Override // defpackage.uu0
    public jy f(int i2) {
        return r(i2, false);
    }

    @Override // defpackage.uu0
    public final jy j() {
        if (this.e == null) {
            WindowInsets windowInsets = this.c;
            this.e = jy.b(windowInsets.getSystemWindowInsetLeft(), windowInsets.getSystemWindowInsetTop(), windowInsets.getSystemWindowInsetRight(), windowInsets.getSystemWindowInsetBottom());
        }
        return this.e;
    }

    @Override // defpackage.uu0
    public xu0 l(int i2, int i3, int i4, int i5) {
        xu0 xu0VarH = xu0.h(null, this.c);
        int i6 = Build.VERSION.SDK_INT;
        ou0 nu0Var = i6 >= 30 ? new nu0(xu0VarH) : i6 >= 29 ? new mu0(xu0VarH) : new lu0(xu0VarH);
        nu0Var.g(xu0.e(j(), i2, i3, i4, i5));
        nu0Var.e(xu0.e(h(), i2, i3, i4, i5));
        return nu0Var.b();
    }

    @Override // defpackage.uu0
    public boolean n() {
        return this.c.isRound();
    }

    @Override // defpackage.uu0
    public void o(jy[] jyVarArr) {
        this.d = jyVarArr;
    }

    @Override // defpackage.uu0
    public void p(xu0 xu0Var) {
        this.f = xu0Var;
    }

    public jy s(int i2, boolean z) {
        jy jyVarH;
        int i3;
        if (i2 == 1) {
            return z ? jy.b(0, Math.max(t().b, j().b), 0, 0) : jy.b(0, j().b, 0, 0);
        }
        if (i2 == 2) {
            if (z) {
                jy jyVarT = t();
                jy jyVarH2 = h();
                return jy.b(Math.max(jyVarT.a, jyVarH2.a), 0, Math.max(jyVarT.c, jyVarH2.c), Math.max(jyVarT.d, jyVarH2.d));
            }
            jy jyVarJ = j();
            xu0 xu0Var = this.f;
            jyVarH = xu0Var != null ? xu0Var.a.h() : null;
            int iMin = jyVarJ.d;
            if (jyVarH != null) {
                iMin = Math.min(iMin, jyVarH.d);
            }
            return jy.b(jyVarJ.a, 0, jyVarJ.c, iMin);
        }
        jy jyVar = jy.e;
        if (i2 == 8) {
            jy[] jyVarArr = this.d;
            jyVarH = jyVarArr != null ? jyVarArr[nv0.B(8)] : null;
            if (jyVarH != null) {
                return jyVarH;
            }
            jy jyVarJ2 = j();
            jy jyVarT2 = t();
            int i4 = jyVarJ2.d;
            if (i4 > jyVarT2.d) {
                return jy.b(0, 0, 0, i4);
            }
            jy jyVar2 = this.g;
            return (jyVar2 == null || jyVar2.equals(jyVar) || (i3 = this.g.d) <= jyVarT2.d) ? jyVar : jy.b(0, 0, 0, i3);
        }
        if (i2 == 16) {
            return i();
        }
        if (i2 == 32) {
            return g();
        }
        if (i2 == 64) {
            return k();
        }
        if (i2 != 128) {
            return jyVar;
        }
        xu0 xu0Var2 = this.f;
        cl clVarE = xu0Var2 != null ? xu0Var2.a.e() : e();
        if (clVarE == null) {
            return jyVar;
        }
        int i5 = Build.VERSION.SDK_INT;
        return jy.b(i5 >= 28 ? bl.d(clVarE.a) : 0, i5 >= 28 ? bl.f(clVarE.a) : 0, i5 >= 28 ? bl.e(clVarE.a) : 0, i5 >= 28 ? bl.c(clVarE.a) : 0);
    }

    public void w(jy jyVar) {
        this.g = jyVar;
    }
}
