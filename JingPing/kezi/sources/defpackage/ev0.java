package defpackage;

import android.os.Build;
import android.view.View;
import android.view.Window;
import android.view.WindowInsetsController;

/* JADX INFO: loaded from: classes.dex */
public final class ev0 {
    public final cv0 a;

    public ev0(WindowInsetsController windowInsetsController) {
        d90 d90Var = new d90();
        rj0 rj0Var = new rj0(null);
        rj0Var.j = windowInsetsController;
        d90Var.b = rj0Var;
        this.a = new bv0(windowInsetsController, this, d90Var);
    }

    public void addOnControllableInsetsChangedListener(dv0 dv0Var) {
        this.a.addOnControllableInsetsChangedListener(dv0Var);
    }

    public void removeOnControllableInsetsChangedListener(dv0 dv0Var) {
        this.a.removeOnControllableInsetsChangedListener(dv0Var);
    }

    public ev0(Window window, View view) {
        d90 d90Var = new d90();
        if (Build.VERSION.SDK_INT >= 30) {
            rj0 rj0Var = new rj0(view);
            rj0Var.i = view;
            d90Var.b = rj0Var;
        } else {
            d90Var.b = new gv(view);
        }
        int i = Build.VERSION.SDK_INT;
        if (i >= 30) {
            bv0 bv0Var = new bv0(window.getInsetsController(), this, d90Var);
            bv0Var.d = window;
            this.a = bv0Var;
        } else if (i >= 26) {
            this.a = new av0(window, d90Var);
        } else {
            this.a = new zu0(window, d90Var);
        }
    }
}
