package defpackage;

import android.view.View;
import android.view.Window;

/* JADX INFO: loaded from: classes.dex */
public abstract class yu0 extends cv0 {
    public final Window a;
    public final d90 b;

    public yu0(Window window, d90 d90Var) {
        this.a = window;
        this.b = d90Var;
    }

    @Override // defpackage.cv0
    public final void a() {
        for (int i = 1; i <= 256; i <<= 1) {
            if ((8 & i) != 0) {
                if (i == 1) {
                    e(4);
                } else if (i == 2) {
                    e(2);
                } else if (i == 8) {
                    ((gv) this.b.b).c();
                }
            }
        }
    }

    @Override // defpackage.cv0
    public final void d() {
        for (int i = 1; i <= 256; i <<= 1) {
            if ((8 & i) != 0) {
                if (i == 1) {
                    f(4);
                    this.a.clearFlags(1024);
                } else if (i == 2) {
                    f(2);
                } else if (i == 8) {
                    ((gv) this.b.b).d();
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

    @Override // defpackage.cv0
    public void addOnControllableInsetsChangedListener(dv0 dv0Var) {
    }

    @Override // defpackage.cv0
    public void removeOnControllableInsetsChangedListener(dv0 dv0Var) {
    }
}
