package defpackage;

import android.os.Trace;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.l;
import androidx.recyclerview.widget.n;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

/* JADX INFO: loaded from: classes.dex */
public final class y10 implements Runnable {
    public static final ThreadLocal I = new ThreadLocal();
    public static final q4 J = new q4(2);
    public ArrayList E;
    public long F;
    public long G;
    public ArrayList H;

    public static n c(RecyclerView recyclerView, int i, long j) {
        int I2 = recyclerView.I.I();
        for (int i2 = 0; i2 < I2; i2++) {
            n nVarI = RecyclerView.I(recyclerView.I.H(i2));
            if (nVarI.c == i && !nVarI.f()) {
                return null;
            }
        }
        l lVar = recyclerView.F;
        try {
            recyclerView.P();
            n nVarI2 = lVar.i(i, j);
            if (nVarI2 != null) {
                if (!nVarI2.e() || nVarI2.f()) {
                    lVar.a(nVarI2, false);
                } else {
                    lVar.f(nVarI2.a);
                }
            }
            recyclerView.Q(false);
            return nVarI2;
        } catch (Throwable th) {
            recyclerView.Q(false);
            throw th;
        }
    }

    public final void a(RecyclerView recyclerView, int i, int i2) {
        if (recyclerView.T && this.F == 0) {
            this.F = recyclerView.getNanoTime();
            recyclerView.post(this);
        }
        w10 w10Var = recyclerView.G0;
        w10Var.a = i;
        w10Var.b = i2;
    }

    /* JADX WARN: Removed duplicated region for block: B:46:0x00ca  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final void b(long r17) {
        /*
            Method dump skipped, instruction units count: 331
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.y10.b(long):void");
    }

    @Override // java.lang.Runnable
    public final void run() {
        try {
            int i = m11.a;
            Trace.beginSection("RV Prefetch");
            ArrayList arrayList = this.E;
            if (arrayList.isEmpty()) {
                this.F = 0L;
                Trace.endSection();
                return;
            }
            int size = arrayList.size();
            long jMax = 0;
            for (int i2 = 0; i2 < size; i2++) {
                RecyclerView recyclerView = (RecyclerView) arrayList.get(i2);
                if (recyclerView.getWindowVisibility() == 0) {
                    jMax = Math.max(recyclerView.getDrawingTime(), jMax);
                }
            }
            if (jMax == 0) {
                this.F = 0L;
                Trace.endSection();
            } else {
                b(TimeUnit.MILLISECONDS.toNanos(jMax) + this.G);
                this.F = 0L;
                Trace.endSection();
            }
        } catch (Throwable th) {
            this.F = 0L;
            int i3 = m11.a;
            Trace.endSection();
            throw th;
        }
    }
}
