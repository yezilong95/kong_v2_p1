package defpackage;

import android.view.View;
import android.view.WindowInsets;

/* JADX INFO: loaded from: classes.dex */
public abstract class mr0 {
    public static xu0 a(View view) {
        WindowInsets rootWindowInsets = view.getRootWindowInsets();
        if (rootWindowInsets == null) {
            return null;
        }
        xu0 xu0VarH = xu0.h(null, rootWindowInsets);
        uu0 uu0Var = xu0VarH.a;
        uu0Var.p(xu0VarH);
        uu0Var.d(view.getRootView());
        return xu0VarH;
    }

    public static int b(View view) {
        return view.getScrollIndicators();
    }

    public static void c(View view, int i) {
        view.setScrollIndicators(i);
    }

    public static void d(View view, int i, int i2) {
        view.setScrollIndicators(i, i2);
    }
}
