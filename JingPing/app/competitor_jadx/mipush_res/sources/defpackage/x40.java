package defpackage;

import androidx.work.impl.WorkDatabase_Impl;
import java.lang.ref.WeakReference;
import java.util.Arrays;
import java.util.Set;

/* JADX INFO: loaded from: classes.dex */
public final class x40 extends v40 {
    public final y40 b;
    public final WeakReference c;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public x40(y40 y40Var, pp0 pp0Var) {
        super(pp0Var.a);
        t40.n(pp0Var, "delegate");
        this.b = y40Var;
        this.c = new WeakReference(pp0Var);
    }

    @Override // defpackage.v40
    public final void a(Set set) {
        w40 w40Var;
        boolean z;
        t40.n(set, "tables");
        v40 v40Var = (v40) this.c.get();
        if (v40Var != null) {
            v40Var.a(set);
            return;
        }
        y40 y40Var = this.b;
        synchronized (y40Var.k) {
            w40Var = (w40) y40Var.k.b(this);
        }
        if (w40Var != null) {
            u40 u40Var = y40Var.i;
            int[] iArr = w40Var.b;
            int[] iArrCopyOf = Arrays.copyOf(iArr, iArr.length);
            u40Var.getClass();
            t40.n(iArrCopyOf, "tableIds");
            synchronized (u40Var) {
                z = false;
                for (int i : iArrCopyOf) {
                    long[] jArr = u40Var.a;
                    long j = jArr[i];
                    jArr[i] = j - 1;
                    if (j == 1) {
                        z = true;
                        u40Var.d = true;
                    }
                }
            }
            if (z) {
                WorkDatabase_Impl workDatabase_Impl = y40Var.a;
                if (workDatabase_Impl.isOpen()) {
                    y40Var.e(((w00) workDatabase_Impl.getOpenHelper()).j());
                }
            }
        }
    }
}
