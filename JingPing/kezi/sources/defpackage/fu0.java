package defpackage;

import android.view.View;
import android.view.ViewGroup;
import android.view.WindowInsets;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.PathInterpolator;
import androidx.core.R$id;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public final class fu0 extends iu0 {
    public static final PathInterpolator e = new PathInterpolator(0.0f, 1.1f, 0.0f, 1.0f);
    public static final mp f = new mp(0);
    public static final DecelerateInterpolator g = new DecelerateInterpolator();

    public static void e(View view) {
        gg ggVarJ = j(view);
        if (ggVarJ != null) {
            ((View) ggVarJ.d).setTranslationY(0.0f);
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
        gg ggVarJ = j(view);
        if (ggVarJ != null) {
            ggVarJ.c = windowInsets;
            if (!z) {
                View view2 = (View) ggVarJ.d;
                int[] iArr = (int[]) ggVarJ.e;
                view2.getLocationOnScreen(iArr);
                z = true;
                ggVarJ.a = iArr[1];
            }
        }
        if (view instanceof ViewGroup) {
            ViewGroup viewGroup = (ViewGroup) view;
            for (int i = 0; i < viewGroup.getChildCount(); i++) {
                f(viewGroup.getChildAt(i), windowInsets, z);
            }
        }
    }

    public static void g(View view, xu0 xu0Var, List list) {
        gg ggVarJ = j(view);
        if (ggVarJ != null) {
            ggVarJ.a(xu0Var, list);
            return;
        }
        if (view instanceof ViewGroup) {
            ViewGroup viewGroup = (ViewGroup) view;
            for (int i = 0; i < viewGroup.getChildCount(); i++) {
                g(viewGroup.getChildAt(i), xu0Var, list);
            }
        }
    }

    public static void h(View view, e5 e5Var) {
        gg ggVarJ = j(view);
        if (ggVarJ != null) {
            View view2 = (View) ggVarJ.d;
            int[] iArr = (int[]) ggVarJ.e;
            view2.getLocationOnScreen(iArr);
            int i = ggVarJ.a - iArr[1];
            ggVarJ.b = i;
            view2.setTranslationY(i);
            return;
        }
        if (view instanceof ViewGroup) {
            ViewGroup viewGroup = (ViewGroup) view;
            for (int i2 = 0; i2 < viewGroup.getChildCount(); i2++) {
                h(viewGroup.getChildAt(i2), e5Var);
            }
        }
    }

    public static WindowInsets i(View view, WindowInsets windowInsets) {
        return view.getTag(R$id.tag_on_apply_window_listener) != null ? windowInsets : view.onApplyWindowInsets(windowInsets);
    }

    public static gg j(View view) {
        Object tag = view.getTag(R$id.tag_window_insets_animation_callback);
        if (tag instanceof eu0) {
            return ((eu0) tag).a;
        }
        return null;
    }
}
