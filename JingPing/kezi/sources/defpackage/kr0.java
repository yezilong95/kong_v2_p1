package defpackage;

import android.os.Build;
import android.view.View;
import android.view.WindowInsets;
import java.util.WeakHashMap;

/* JADX INFO: loaded from: classes.dex */
public final class kr0 implements View.OnApplyWindowInsetsListener {
    public xu0 a = null;
    public final /* synthetic */ View b;
    public final /* synthetic */ ha0 c;

    public kr0(View view, ha0 ha0Var) {
        this.b = view;
        this.c = ha0Var;
    }

    @Override // android.view.View.OnApplyWindowInsetsListener
    public WindowInsets onApplyWindowInsets(View view, WindowInsets windowInsets) {
        xu0 xu0VarH = xu0.h(view, windowInsets);
        int i = Build.VERSION.SDK_INT;
        ha0 ha0Var = this.c;
        if (i < 30) {
            lr0.a(windowInsets, this.b);
            if (xu0VarH.equals(this.a)) {
                return ha0Var.e(view, xu0VarH).g();
            }
        }
        this.a = xu0VarH;
        xu0 xu0VarE = ha0Var.e(view, xu0VarH);
        if (i >= 30) {
            return xu0VarE.g();
        }
        WeakHashMap weakHashMap = xr0.a;
        jr0.c(view);
        return xu0VarE.g();
    }
}
