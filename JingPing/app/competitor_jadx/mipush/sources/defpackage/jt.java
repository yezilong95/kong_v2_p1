package defpackage;

import android.util.SparseArray;
import java.nio.ByteBuffer;

/* JADX INFO: loaded from: classes.dex */
public final class jt {
    public int a = 1;
    public final xe0 b;
    public xe0 c;
    public xe0 d;
    public int e;
    public int f;

    public jt(xe0 xe0Var) {
        this.b = xe0Var;
        this.c = xe0Var;
    }

    public final int a(int i) {
        SparseArray sparseArray = this.c.a;
        xe0 xe0Var = sparseArray == null ? null : (xe0) sparseArray.get(i);
        int i2 = 1;
        int i3 = 2;
        if (this.a == 2) {
            if (xe0Var != null) {
                this.c = xe0Var;
                this.f++;
            } else if (i == 65038) {
                b();
            } else if (i != 65039) {
                xe0 xe0Var2 = this.c;
                if (xe0Var2.b != null) {
                    i3 = 3;
                    if (this.f != 1) {
                        this.d = xe0Var2;
                        b();
                    } else if (c()) {
                        this.d = this.c;
                        b();
                    } else {
                        b();
                    }
                } else {
                    b();
                }
            }
            i2 = i3;
        } else if (xe0Var == null) {
            b();
        } else {
            this.a = 2;
            this.c = xe0Var;
            this.f = 1;
            i2 = i3;
        }
        this.e = i;
        return i2;
    }

    public final void b() {
        this.a = 1;
        this.c = this.b;
        this.f = 0;
    }

    public final boolean c() {
        ve0 ve0VarB = this.c.b.b();
        int iA = ve0VarB.a(6);
        return !(iA == 0 || ((ByteBuffer) ve0VarB.H).get(iA + ve0VarB.E) == 0) || this.e == 65039;
    }
}
