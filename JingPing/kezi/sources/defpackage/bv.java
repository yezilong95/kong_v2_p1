package defpackage;

import android.os.Trace;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.l;
import androidx.recyclerview.widget.n;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

/* JADX INFO: loaded from: classes.dex */
public final class bv implements Runnable {
    public static final ThreadLocal f = new ThreadLocal();
    public static final l3 g = new l3(3);
    public ArrayList b;
    public long c;
    public long d;
    public ArrayList e;

    public static n c(RecyclerView recyclerView, int i, long j) {
        int iG = recyclerView.f.G();
        for (int i2 = 0; i2 < iG; i2++) {
            n nVarI = RecyclerView.I(recyclerView.f.F(i2));
            if (nVarI.c == i && !nVarI.f()) {
                return null;
            }
        }
        l lVar = recyclerView.c;
        try {
            recyclerView.P();
            n nVarI2 = lVar.i(j, i);
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
        if (recyclerView.q && this.c == 0) {
            this.c = recyclerView.getNanoTime();
            recyclerView.post(this);
        }
        zu zuVar = recyclerView.d0;
        zuVar.a = i;
        zuVar.b = i2;
    }

    /* JADX WARN: Removed duplicated region for block: B:46:0x00ca  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final void b(long r17) {
        /*
            Method dump skipped, instruction units count: 331
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.bv.b(long):void");
    }

    @Override // java.lang.Runnable
    public final void run() {
        try {
            int i = ap0.a;
            Trace.beginSection("RV Prefetch");
            ArrayList arrayList = this.b;
            if (arrayList.isEmpty()) {
                this.c = 0L;
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
                this.c = 0L;
                Trace.endSection();
            } else {
                b(TimeUnit.MILLISECONDS.toNanos(jMax) + this.d);
                this.c = 0L;
                Trace.endSection();
            }
        } catch (Throwable th) {
            this.c = 0L;
            int i3 = ap0.a;
            Trace.endSection();
            throw th;
        }
    }
}
