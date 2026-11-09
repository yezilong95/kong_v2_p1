package defpackage;

import android.view.View;
import android.view.Window;
import android.view.WindowInsetsController;

/* JADX INFO: loaded from: classes.dex */
public final class bv0 extends cv0 {
    public final WindowInsetsController a;
    public final d90 b;
    public final fj0 c = new fj0();
    public Window d;

    public bv0(WindowInsetsController windowInsetsController, ev0 ev0Var, d90 d90Var) {
        this.a = windowInsetsController;
        this.b = d90Var;
    }

    @Override // defpackage.cv0
    public final void a() {
        ((gv) this.b.b).c();
        this.a.hide(0);
    }

    @Override // defpackage.cv0
    public void addOnControllableInsetsChangedListener(dv0 dv0Var) {
        fj0 fj0Var = this.c;
        if (fj0Var.containsKey(dv0Var)) {
            return;
        }
        qj0 qj0Var = new qj0(1, this);
        fj0Var.put(dv0Var, qj0Var);
        this.a.addOnControllableInsetsChangedListener(qj0Var);
    }

    @Override // defpackage.cv0
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

    @Override // defpackage.cv0
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

    @Override // defpackage.cv0
    public final void d() {
        ((gv) this.b.b).d();
        this.a.show(0);
    }

    @Override // defpackage.cv0
    public void removeOnControllableInsetsChangedListener(dv0 dv0Var) {
        WindowInsetsController.OnControllableInsetsChangedListener onControllableInsetsChangedListenerC = vu0.c(this.c.remove(dv0Var));
        if (onControllableInsetsChangedListenerC != null) {
            this.a.removeOnControllableInsetsChangedListener(onControllableInsetsChangedListenerC);
        }
    }
}
