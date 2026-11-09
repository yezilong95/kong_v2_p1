package defpackage;

import android.window.BackEvent;

/* JADX INFO: loaded from: classes.dex */
public final class l9 {
    public final float a;
    public final float b;
    public final float c;
    public final int d;

    public l9(BackEvent backEvent) {
        t40.n(backEvent, "backEvent");
        a5 a5Var = a5.a;
        float fD = a5Var.d(backEvent);
        float fE = a5Var.e(backEvent);
        float fB = a5Var.b(backEvent);
        int iC = a5Var.c(backEvent);
        this.a = fD;
        this.b = fE;
        this.c = fB;
        this.d = iC;
    }

    public final String toString() {
        return "BackEventCompat{touchX=" + this.a + ", touchY=" + this.b + ", progress=" + this.c + ", swipeEdge=" + this.d + '}';
    }
}
