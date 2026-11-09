package defpackage;

import android.util.SparseArray;
import java.nio.ByteBuffer;

/* JADX INFO: loaded from: classes.dex */
public final class rn {
    public int a = 1;
    public final f70 b;
    public f70 c;
    public f70 d;
    public int e;
    public int f;

    public rn(f70 f70Var) {
        this.b = f70Var;
        this.c = f70Var;
    }

    public final int a(int i) {
        SparseArray sparseArray = this.c.a;
        f70 f70Var = sparseArray == null ? null : (f70) sparseArray.get(i);
        int i2 = 1;
        int i3 = 2;
        if (this.a == 2) {
            if (f70Var != null) {
                this.c = f70Var;
                this.f++;
            } else if (i == 65038) {
                b();
            } else if (i != 65039) {
                f70 f70Var2 = this.c;
                if (f70Var2.b != null) {
                    i3 = 3;
                    if (this.f != 1) {
                        this.d = f70Var2;
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
        } else if (f70Var == null) {
            b();
        } else {
            this.a = 2;
            this.c = f70Var;
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
        d70 d70VarB = this.c.b.b();
        int iA = d70VarB.a(6);
        return !(iA == 0 || ((ByteBuffer) d70VarB.e).get(iA + d70VarB.b) == 0) || this.e == 65039;
    }
}
