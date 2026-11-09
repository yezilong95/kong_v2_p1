package defpackage;

/* JADX INFO: loaded from: classes.dex */
public final class da0 {
    public final ti0 a;
    public boolean b;
    public int c = -1;
    public final /* synthetic */ ea0 d;

    public da0(ea0 ea0Var, ti0 ti0Var) {
        this.d = ea0Var;
        this.a = ti0Var;
    }

    public final void a(boolean z) {
        if (z == this.b) {
            return;
        }
        this.b = z;
        int i = z ? 1 : -1;
        ea0 ea0Var = this.d;
        int i2 = ea0Var.c;
        ea0Var.c = i + i2;
        if (!ea0Var.d) {
            ea0Var.d = true;
            while (true) {
                try {
                    int i3 = ea0Var.c;
                    if (i2 == i3) {
                        break;
                    }
                    boolean z2 = i2 == 0 && i3 > 0;
                    boolean z3 = i2 > 0 && i3 == 0;
                    if (z2) {
                        ea0Var.d();
                    } else if (z3) {
                        ea0Var.e();
                    }
                    i2 = i3;
                } catch (Throwable th) {
                    ea0Var.d = false;
                    throw th;
                }
            }
            ea0Var.d = false;
        }
        if (this.b) {
            ea0Var.b(this);
        }
    }
}
