package defpackage;

import android.os.Build;
import android.view.View;
import android.view.WindowInsets;
import java.util.WeakHashMap;

/* JADX INFO: loaded from: classes.dex */
public final class s41 implements View.OnApplyWindowInsetsListener {
    public s81 a = null;
    public final /* synthetic */ View b;
    public final /* synthetic */ vi0 c;

    public s41(View view, vi0 vi0Var) {
        this.b = view;
        this.c = vi0Var;
    }

    @Override // android.view.View.OnApplyWindowInsetsListener
    public WindowInsets onApplyWindowInsets(View view, WindowInsets windowInsets) {
        s81 s81VarH = s81.h(view, windowInsets);
        int i = Build.VERSION.SDK_INT;
        vi0 vi0Var = this.c;
        if (i < 30) {
            t41.a(windowInsets, this.b);
            if (s81VarH.equals(this.a)) {
                return vi0Var.h(view, s81VarH).g();
            }
        }
        this.a = s81VarH;
        s81 s81VarH2 = vi0Var.h(view, s81VarH);
        if (i >= 30) {
            return s81VarH2.g();
        }
        WeakHashMap weakHashMap = f51.a;
        r41.c(view);
        return s81VarH2.g();
    }
}
