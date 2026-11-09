package defpackage;

import android.os.Build;
import android.view.View;
import android.view.Window;
import android.view.WindowInsetsController;

/* JADX INFO: loaded from: classes.dex */
public final class z81 {
    public final x81 a;

    public z81(WindowInsetsController windowInsetsController) {
        this.a = new w81(windowInsetsController, this, new cw0(windowInsetsController));
    }

    public void addOnControllableInsetsChangedListener(y81 y81Var) {
        this.a.addOnControllableInsetsChangedListener(y81Var);
    }

    public void removeOnControllableInsetsChangedListener(y81 y81Var) {
        this.a.removeOnControllableInsetsChangedListener(y81Var);
    }

    public z81(Window window, View view) {
        cw0 cw0Var = new cw0(view);
        int i = Build.VERSION.SDK_INT;
        if (i >= 30) {
            w81 w81Var = new w81(window.getInsetsController(), this, cw0Var);
            w81Var.d = window;
            this.a = w81Var;
        } else if (i >= 26) {
            this.a = new v81(window, cw0Var);
        } else {
            this.a = new u81(window, cw0Var);
        }
    }
}
