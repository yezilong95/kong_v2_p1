package defpackage;

import android.view.View;
import android.view.ViewGroup;
import android.view.WindowInsets;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.PathInterpolator;
import androidx.core.R$id;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public final class a81 extends d81 {
    public static final PathInterpolator e = new PathInterpolator(0.0f, 1.1f, 0.0f, 1.0f);
    public static final sv f = new sv(0);
    public static final DecelerateInterpolator g = new DecelerateInterpolator();

    public static void e(View view) {
        sh shVarJ = j(view);
        if (shVarJ != null) {
            ((View) shVarJ.d).setTranslationY(0.0f);
            return;
        }
        if (view instanceof ViewGroup) {
            ViewGroup viewGroup = (ViewGroup) view;
            for (int i = 0; i < viewGroup.getChildCount(); i++) {
                e(viewGroup.getChildAt(i));
            }
        }
    }

    public static void f(View view, WindowInsets windowInsets, boolean z) {
        sh shVarJ = j(view);
        if (shVarJ != null) {
            shVarJ.c = windowInsets;
            if (!z) {
                View view2 = (View) shVarJ.d;
                int[] iArr = (int[]) shVarJ.e;
                view2.getLocationOnScreen(iArr);
                z = true;
                shVarJ.a = iArr[1];
            }
        }
        if (view instanceof ViewGroup) {
            ViewGroup viewGroup = (ViewGroup) view;
            for (int i = 0; i < viewGroup.getChildCount(); i++) {
                f(viewGroup.getChildAt(i), windowInsets, z);
            }
        }
    }

    public static void g(View view, s81 s81Var, List list) {
        sh shVarJ = j(view);
        if (shVarJ != null) {
            shVarJ.b(s81Var, list);
            return;
        }
        if (view instanceof ViewGroup) {
            ViewGroup viewGroup = (ViewGroup) view;
            for (int i = 0; i < viewGroup.getChildCount(); i++) {
                g(viewGroup.getChildAt(i), s81Var, list);
            }
        }
    }

    public static void h(View view, n41 n41Var) {
        sh shVarJ = j(view);
        if (shVarJ != null) {
            View view2 = (View) shVarJ.d;
            int[] iArr = (int[]) shVarJ.e;
            view2.getLocationOnScreen(iArr);
            int i = shVarJ.a - iArr[1];
            shVarJ.b = i;
            view2.setTranslationY(i);
            return;
        }
        if (view instanceof ViewGroup) {
            ViewGroup viewGroup = (ViewGroup) view;
            for (int i2 = 0; i2 < viewGroup.getChildCount(); i2++) {
                h(viewGroup.getChildAt(i2), n41Var);
            }
        }
    }

    public static WindowInsets i(View view, WindowInsets windowInsets) {
        return view.getTag(R$id.tag_on_apply_window_listener) != null ? windowInsets : view.onApplyWindowInsets(windowInsets);
    }

    public static sh j(View view) {
        Object tag = view.getTag(R$id.tag_window_insets_animation_callback);
        if (tag instanceof z71) {
            return ((z71) tag).a;
        }
        return null;
    }
}
