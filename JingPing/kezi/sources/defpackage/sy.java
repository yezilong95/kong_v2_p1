package defpackage;

import androidx.work.impl.WorkDatabase_Impl;
import java.lang.ref.WeakReference;
import java.util.Arrays;
import java.util.Set;

/* JADX INFO: loaded from: classes.dex */
public final class sy extends qy {
    public final ty b;
    public final WeakReference c;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public sy(ty tyVar, uf0 uf0Var) {
        super(uf0Var.a);
        oy.j(uf0Var, "delegate");
        this.b = tyVar;
        this.c = new WeakReference(uf0Var);
    }

    @Override // defpackage.qy
    public final void a(Set set) {
        ry ryVar;
        boolean z;
        oy.j(set, "tables");
        qy qyVar = (qy) this.c.get();
        if (qyVar != null) {
            qyVar.a(set);
            return;
        }
        ty tyVar = this.b;
        synchronized (tyVar.k) {
            ryVar = (ry) tyVar.k.b(this);
        }
        if (ryVar != null) {
            py pyVar = tyVar.i;
            int[] iArr = ryVar.b;
            int[] iArrCopyOf = Arrays.copyOf(iArr, iArr.length);
            pyVar.getClass();
            oy.j(iArrCopyOf, "tableIds");
            synchronized (pyVar) {
                z = false;
                for (int i : iArrCopyOf) {
                    long[] jArr = pyVar.a;
                    long j = jArr[i];
                    jArr[i] = j - 1;
                    if (j == 1) {
                        z = true;
                        pyVar.d = true;
                    }
                }
            }
            if (z) {
                WorkDatabase_Impl workDatabase_Impl = tyVar.a;
                if (workDatabase_Impl.isOpen()) {
                    tyVar.e(((ku) workDatabase_Impl.getOpenHelper()).a());
                }
            }
        }
    }
}
