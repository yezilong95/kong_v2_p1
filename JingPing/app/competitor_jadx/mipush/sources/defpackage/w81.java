package defpackage;

import android.view.View;
import android.view.Window;
import android.view.WindowInsetsController;

/* JADX INFO: loaded from: classes.dex */
public final class w81 extends x81 {
    public final WindowInsetsController a;
    public final cw0 b;
    public final vv0 c = new vv0();
    public Window d;

    public w81(WindowInsetsController windowInsetsController, z81 z81Var, cw0 cw0Var) {
        this.a = windowInsetsController;
        this.b = cw0Var;
    }

    @Override // defpackage.x81
    public final void a() {
        ((wl0) this.b.F).a();
        this.a.hide(0);
    }

    @Override // defpackage.x81
    public void addOnControllableInsetsChangedListener(y81 y81Var) {
        vv0 vv0Var = this.c;
        if (vv0Var.containsKey(y81Var)) {
            return;
        }
        fw0 fw0Var = new fw0(1, this);
        vv0Var.put(y81Var, fw0Var);
        this.a.addOnControllableInsetsChangedListener(fw0Var);
    }

    @Override // defpackage.x81
    public final void b(boolean z) {
        Window window = this.d;
        if (z) {
            if (window != null) {
                View decorView = window.getDecorView();
                decorView.setSystemUiVisibility(decorView.getSystemUiVisibility() | 16);
            }
            this.a.setSystemBarsAppearance(16, 16);
            return;
        }
        if (window != null) {
            View decorView2 = window.getDecorView();
            decorView2.setSystemUiVisibility(decorView2.getSystemUiVisibility() & (-17));
        }
        this.a.setSystemBarsAppearance(0, 16);
    }

    @Override // defpackage.x81
    public final void c(boolean z) {
        Window window = this.d;
        if (z) {
            if (window != null) {
                View decorView = window.getDecorView();
                decorView.setSystemUiVisibility(decorView.getSystemUiVisibility() | 8192);
            }
            this.a.setSystemBarsAppearance(8, 8);
            return;
        }
        if (window != null) {
            View decorView2 = window.getDecorView();
            decorView2.setSystemUiVisibility(decorView2.getSystemUiVisibility() & (-8193));
        }
        this.a.setSystemBarsAppearance(0, 8);
    }

    @Override // defpackage.x81
    public final void d() {
        ((wl0) this.b.F).g();
        this.a.show(0);
    }

    @Override // defpackage.x81
    public void removeOnControllableInsetsChangedListener(y81 y81Var) {
        WindowInsetsController.OnControllableInsetsChangedListener onControllableInsetsChangedListenerC = q81.c(this.c.remove(y81Var));
        if (onControllableInsetsChangedListenerC != null) {
            this.a.removeOnControllableInsetsChangedListener(onControllableInsetsChangedListenerC);
        }
    }
}
