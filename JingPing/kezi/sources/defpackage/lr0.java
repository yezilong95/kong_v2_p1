package defpackage;

import android.content.res.ColorStateList;
import android.graphics.PorterDuff;
import android.graphics.Rect;
import android.os.Build;
import android.util.Log;
import android.view.View;
import android.view.WindowInsets;
import androidx.core.R$id;

/* JADX INFO: loaded from: classes.dex */
public abstract class lr0 {
    public static void a(WindowInsets windowInsets, View view) {
        View.OnApplyWindowInsetsListener onApplyWindowInsetsListener = (View.OnApplyWindowInsetsListener) view.getTag(R$id.tag_window_insets_animation_callback);
        if (onApplyWindowInsetsListener != null) {
            onApplyWindowInsetsListener.onApplyWindowInsets(view, windowInsets);
        }
    }

    public static xu0 b(View view, xu0 xu0Var, Rect rect) {
        WindowInsets windowInsetsG = xu0Var.g();
        if (windowInsetsG != null) {
            return xu0.h(view, view.computeSystemWindowInsets(windowInsetsG, rect));
        }
        rect.setEmpty();
        return xu0Var;
    }

    public static boolean c(View view, float f, float f2, boolean z) {
        return view.dispatchNestedFling(f, f2, z);
    }

    public static boolean d(View view, float f, float f2) {
        return view.dispatchNestedPreFling(f, f2);
    }

    public static boolean e(View view, int i, int i2, int[] iArr, int[] iArr2) {
        return view.dispatchNestedPreScroll(i, i2, iArr, iArr2);
    }

    public static boolean f(View view, int i, int i2, int i3, int i4, int[] iArr) {
        return view.dispatchNestedScroll(i, i2, i3, i4, iArr);
    }

    public static ColorStateList g(View view) {
        return view.getBackgroundTintList();
    }

    public static PorterDuff.Mode h(View view) {
        return view.getBackgroundTintMode();
    }

    public static float i(View view) {
        return view.getElevation();
    }

    public static xu0 j(View view) {
        if (!ku0.d || !view.isAttachedToWindow()) {
            return null;
        }
        try {
            Object obj = ku0.a.get(view.getRootView());
            if (obj == null) {
                return null;
            }
            Rect rect = (Rect) ku0.b.get(obj);
            Rect rect2 = (Rect) ku0.c.get(obj);
            if (rect == null || rect2 == null) {
                return null;
            }
            int i = Build.VERSION.SDK_INT;
            ou0 nu0Var = i >= 30 ? new nu0() : i >= 29 ? new mu0() : new lu0();
            nu0Var.e(jy.b(rect.left, rect.top, rect.right, rect.bottom));
            nu0Var.g(jy.b(rect2.left, rect2.top, rect2.right, rect2.bottom));
            xu0 xu0VarB = nu0Var.b();
            xu0VarB.a.p(xu0VarB);
            xu0VarB.a.d(view.getRootView());
            return xu0VarB;
        } catch (IllegalAccessException e) {
            Log.w("WindowInsetsCompat", "Failed to get insets from AttachInfo. " + e.getMessage(), e);
            return null;
        }
    }

    public static String k(View view) {
        return view.getTransitionName();
    }

    public static float l(View view) {
        return view.getTranslationZ();
    }

    public static float m(View view) {
        return view.getZ();
    }

    public static boolean n(View view) {
        return view.hasNestedScrollingParent();
    }

    public static boolean o(View view) {
        return view.isImportantForAccessibility();
    }

    public static boolean p(View view) {
        return view.isNestedScrollingEnabled();
    }

    public static void q(View view, ColorStateList colorStateList) {
        view.setBackgroundTintList(colorStateList);
    }

    public static void r(View view, PorterDuff.Mode mode) {
        view.setBackgroundTintMode(mode);
    }

    public static void s(View view, float f) {
        view.setElevation(f);
    }

    public static void t(View view, boolean z) {
        view.setNestedScrollingEnabled(z);
    }

    public static void u(View view, ha0 ha0Var) {
        if (Build.VERSION.SDK_INT < 30) {
            view.setTag(R$id.tag_on_apply_window_listener, ha0Var);
        }
        if (ha0Var == null) {
            view.setOnApplyWindowInsetsListener((View.OnApplyWindowInsetsListener) view.getTag(R$id.tag_window_insets_animation_callback));
        } else {
            view.setOnApplyWindowInsetsListener(new kr0(view, ha0Var));
        }
    }

    public static void v(View view, String str) {
        view.setTransitionName(str);
    }

    public static void w(View view, float f) {
        view.setTranslationZ(f);
    }

    public static void x(View view, float f) {
        view.setZ(f);
    }

    public static boolean y(View view, int i) {
        return view.startNestedScroll(i);
    }

    public static void z(View view) {
        view.stopNestedScroll();
    }
}
