package defpackage;

import android.view.View;
import android.view.WindowInsets;

/* JADX INFO: loaded from: classes.dex */
public abstract class u41 {
    public static s81 a(View view) {
        WindowInsets rootWindowInsets = view.getRootWindowInsets();
        if (rootWindowInsets == null) {
            return null;
        }
        s81 s81VarH = s81.h(null, rootWindowInsets);
        p81 p81Var = s81VarH.a;
        p81Var.p(s81VarH);
        p81Var.d(view.getRootView());
        return s81VarH;
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
