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
public abstract class k81 extends p81 {
    public static boolean h = false;
    public static Method i;
    public static Class j;
    public static Field k;
    public static Field l;
    public final WindowInsets c;
    public m40[] d;
    public m40 e;
    public s81 f;
    public m40 g;

    public k81(s81 s81Var, WindowInsets windowInsets) {
        super(s81Var);
        this.e = null;
        this.c = windowInsets;
    }

    @SuppressLint({"WrongConstant"})
    private m40 r(int i2, boolean z) {
        m40 m40VarA = m40.e;
        for (int i3 = 1; i3 <= 256; i3 <<= 1) {
            if ((i2 & i3) != 0) {
                m40VarA = m40.a(m40VarA, s(i3, z));
            }
        }
        return m40VarA;
    }

    private m40 t() {
        s81 s81Var = this.f;
        return s81Var != null ? s81Var.a.h() : m40.e;
    }

    private m40 u(View view) {
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
                    return m40.b(rect.left, rect.top, rect.right, rect.bottom);
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

    @Override // defpackage.p81
    public void d(View view) {
        m40 m40VarU = u(view);
        if (m40VarU == null) {
            m40VarU = m40.e;
        }
        w(m40VarU);
    }

    @Override // defpackage.p81
    public boolean equals(Object obj) {
        if (super.equals(obj)) {
            return Objects.equals(this.g, ((k81) obj).g);
        }
        return false;
    }

    @Override // defpackage.p81
    public m40 f(int i2) {
        return r(i2, false);
    }

    @Override // defpackage.p81
    public final m40 j() {
        if (this.e == null) {
            WindowInsets windowInsets = this.c;
            this.e = m40.b(windowInsets.getSystemWindowInsetLeft(), windowInsets.getSystemWindowInsetTop(), windowInsets.getSystemWindowInsetRight(), windowInsets.getSystemWindowInsetBottom());
        }
        return this.e;
    }

    @Override // defpackage.p81
    public s81 l(int i2, int i3, int i4, int i5) {
        s81 s81VarH = s81.h(null, this.c);
        int i6 = Build.VERSION.SDK_INT;
        j81 i81Var = i6 >= 30 ? new i81(s81VarH) : i6 >= 29 ? new h81(s81VarH) : new g81(s81VarH);
        i81Var.g(s81.e(j(), i2, i3, i4, i5));
        i81Var.e(s81.e(h(), i2, i3, i4, i5));
        return i81Var.b();
    }

    @Override // defpackage.p81
    public boolean n() {
        return this.c.isRound();
    }

    @Override // defpackage.p81
    public void o(m40[] m40VarArr) {
        this.d = m40VarArr;
    }

    @Override // defpackage.p81
    public void p(s81 s81Var) {
        this.f = s81Var;
    }

    public m40 s(int i2, boolean z) {
        m40 m40VarH;
        int i3;
        if (i2 == 1) {
            return z ? m40.b(0, Math.max(t().b, j().b), 0, 0) : m40.b(0, j().b, 0, 0);
        }
        if (i2 == 2) {
            if (z) {
                m40 m40VarT = t();
                m40 m40VarH2 = h();
                return m40.b(Math.max(m40VarT.a, m40VarH2.a), 0, Math.max(m40VarT.c, m40VarH2.c), Math.max(m40VarT.d, m40VarH2.d));
            }
            m40 m40VarJ = j();
            s81 s81Var = this.f;
            m40VarH = s81Var != null ? s81Var.a.h() : null;
            int iMin = m40VarJ.d;
            if (m40VarH != null) {
                iMin = Math.min(iMin, m40VarH.d);
            }
            return m40.b(m40VarJ.a, 0, m40VarJ.c, iMin);
        }
        m40 m40Var = m40.e;
        if (i2 == 8) {
            m40[] m40VarArr = this.d;
            m40VarH = m40VarArr != null ? m40VarArr[ma0.I(8)] : null;
            if (m40VarH != null) {
                return m40VarH;
            }
            m40 m40VarJ2 = j();
            m40 m40VarT2 = t();
            int i4 = m40VarJ2.d;
            if (i4 > m40VarT2.d) {
                return m40.b(0, 0, 0, i4);
            }
            m40 m40Var2 = this.g;
            return (m40Var2 == null || m40Var2.equals(m40Var) || (i3 = this.g.d) <= m40VarT2.d) ? m40Var : m40.b(0, 0, 0, i3);
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
            return m40Var;
        }
        s81 s81Var2 = this.f;
        zo zoVarE = s81Var2 != null ? s81Var2.a.e() : e();
        if (zoVarE == null) {
            return m40Var;
        }
        int i5 = Build.VERSION.SDK_INT;
        return m40.b(i5 >= 28 ? yo.d(zoVarE.a) : 0, i5 >= 28 ? yo.f(zoVarE.a) : 0, i5 >= 28 ? yo.e(zoVarE.a) : 0, i5 >= 28 ? yo.c(zoVarE.a) : 0);
    }

    public void w(m40 m40Var) {
        this.g = m40Var;
    }
}
