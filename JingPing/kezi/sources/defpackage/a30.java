package defpackage;

import com.mamimobi.shell.activity.MainActivity;

/* JADX INFO: loaded from: classes.dex */
public abstract class a30 {
    public final ba0 b;
    public boolean c;
    public int d = -1;
    public final /* synthetic */ b30 e;

    public a30(b30 b30Var, ba0 ba0Var) {
        this.e = b30Var;
        this.b = ba0Var;
    }

    public final void b(boolean z) {
        if (z == this.c) {
            return;
        }
        this.c = z;
        int i = z ? 1 : -1;
        b30 b30Var = this.e;
        int i2 = b30Var.c;
        b30Var.c = i + i2;
        if (!b30Var.d) {
            b30Var.d = true;
            while (true) {
                try {
                    int i3 = b30Var.c;
                    if (i2 == i3) {
                        break;
                    }
                    boolean z2 = i2 == 0 && i3 > 0;
                    boolean z3 = i2 > 0 && i3 == 0;
                    if (z2) {
                        b30Var.e();
                    } else if (z3) {
                        b30Var.f();
                    }
                    i2 = i3;
                } catch (Throwable th) {
                    b30Var.d = false;
                    throw th;
                }
            }
            b30Var.d = false;
        }
        if (this.c) {
            b30Var.c(this);
        }
    }

    public boolean d(MainActivity mainActivity) {
        return false;
    }

    public abstract boolean e();

    public void c() {
    }
}
