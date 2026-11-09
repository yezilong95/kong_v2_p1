package defpackage;

import android.view.View;
import android.view.Window;

/* JADX INFO: loaded from: classes.dex */
public abstract class t81 extends x81 {
    public final Window a;
    public final cw0 b;

    public t81(Window window, cw0 cw0Var) {
        this.a = window;
        this.b = cw0Var;
    }

    @Override // defpackage.x81
    public final void a() {
        for (int i = 1; i <= 256; i <<= 1) {
            if ((8 & i) != 0) {
                if (i == 1) {
                    e(4);
                } else if (i == 2) {
                    e(2);
                } else if (i == 8) {
                    ((wl0) this.b.F).a();
                }
            }
        }
    }

    @Override // defpackage.x81
    public final void d() {
        for (int i = 1; i <= 256; i <<= 1) {
            if ((8 & i) != 0) {
                if (i == 1) {
                    f(4);
                    this.a.clearFlags(1024);
                } else if (i == 2) {
                    f(2);
                } else if (i == 8) {
                    ((wl0) this.b.F).g();
                }
            }
        }
    }

    public final void e(int i) {
        View decorView = this.a.getDecorView();
        decorView.setSystemUiVisibility(i | decorView.getSystemUiVisibility());
    }

    public final void f(int i) {
        View decorView = this.a.getDecorView();
        decorView.setSystemUiVisibility((~i) & decorView.getSystemUiVisibility());
    }

    @Override // defpackage.x81
    public void addOnControllableInsetsChangedListener(y81 y81Var) {
    }

    @Override // defpackage.x81
    public void removeOnControllableInsetsChangedListener(y81 y81Var) {
    }
}
