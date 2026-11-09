package defpackage;

import android.window.BackEvent;

/* JADX INFO: loaded from: classes.dex */
public final class r7 {
    public final float a;
    public final float b;
    public final float c;
    public final int d;

    public r7(BackEvent backEvent) {
        oy.j(backEvent, "backEvent");
        w3 w3Var = w3.a;
        float fD = w3Var.d(backEvent);
        float fE = w3Var.e(backEvent);
        float fB = w3Var.b(backEvent);
        int iC = w3Var.c(backEvent);
        this.a = fD;
        this.b = fE;
        this.c = fB;
        this.d = iC;
    }

    public final String toString() {
        return "BackEventCompat{touchX=" + this.a + ", touchY=" + this.b + ", progress=" + this.c + ", swipeEdge=" + this.d + '}';
    }
}
