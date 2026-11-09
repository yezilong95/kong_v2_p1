package defpackage;

import androidx.work.impl.WorkDatabase_Impl;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Locale;
import java.util.concurrent.atomic.AtomicBoolean;

/* JADX INFO: loaded from: classes.dex */
public final /* synthetic */ class op0 implements Runnable {
    public final /* synthetic */ int E;
    public final /* synthetic */ qp0 F;

    public /* synthetic */ op0(qp0 qp0Var, int i) {
        this.E = i;
        this.F = qp0Var;
    }

    @Override // java.lang.Runnable
    public final void run() {
        AtomicBoolean atomicBoolean;
        boolean z;
        Object obj;
        w40 w40Var;
        boolean z2;
        switch (this.E) {
            case 0:
                qp0 qp0Var = this.F;
                t40.n(qp0Var, "this$0");
                if (qp0Var.s.compareAndSet(false, true)) {
                    y40 invalidationTracker = qp0Var.l.getInvalidationTracker();
                    pp0 pp0Var = qp0Var.p;
                    invalidationTracker.getClass();
                    t40.n(pp0Var, "observer");
                    x40 x40Var = new x40(invalidationTracker, pp0Var);
                    String[] strArrC = invalidationTracker.c(x40Var.a);
                    ArrayList arrayList = new ArrayList(strArrC.length);
                    for (String str : strArrC) {
                        LinkedHashMap linkedHashMap = invalidationTracker.d;
                        Locale locale = Locale.US;
                        t40.m(locale, "US");
                        String lowerCase = str.toLowerCase(locale);
                        t40.m(lowerCase, "this as java.lang.String).toLowerCase(locale)");
                        Integer num = (Integer) linkedHashMap.get(lowerCase);
                        if (num == null) {
                            throw new IllegalArgumentException("There is no table with name ".concat(str));
                        }
                        arrayList.add(num);
                    }
                    int size = arrayList.size();
                    int[] iArr = new int[size];
                    Iterator it = arrayList.iterator();
                    int i = 0;
                    while (it.hasNext()) {
                        iArr[i] = ((Number) it.next()).intValue();
                        i++;
                    }
                    w40 w40Var2 = new w40(x40Var, iArr, strArrC);
                    synchronized (invalidationTracker.k) {
                        yq0 yq0Var = invalidationTracker.k;
                        vq0 vq0VarA = yq0Var.a(x40Var);
                        if (vq0VarA != null) {
                            obj = vq0VarA.F;
                        } else {
                            vq0 vq0Var = new vq0(x40Var, w40Var2);
                            yq0Var.H++;
                            vq0 vq0Var2 = yq0Var.F;
                            if (vq0Var2 == null) {
                                yq0Var.E = vq0Var;
                                yq0Var.F = vq0Var;
                            } else {
                                vq0Var2.G = vq0Var;
                                vq0Var.H = vq0Var2;
                                yq0Var.F = vq0Var;
                            }
                            obj = null;
                        }
                        w40Var = (w40) obj;
                    }
                    if (w40Var == null) {
                        u40 u40Var = invalidationTracker.i;
                        int[] iArrCopyOf = Arrays.copyOf(iArr, size);
                        u40Var.getClass();
                        t40.n(iArrCopyOf, "tableIds");
                        synchronized (u40Var) {
                            z2 = false;
                            for (int i2 : iArrCopyOf) {
                                long[] jArr = u40Var.a;
                                long j = jArr[i2];
                                jArr[i2] = 1 + j;
                                if (j == 0) {
                                    u40Var.d = true;
                                    z2 = true;
                                }
                            }
                        }
                        if (z2) {
                            WorkDatabase_Impl workDatabase_Impl = invalidationTracker.a;
                            if (workDatabase_Impl.isOpen()) {
                                invalidationTracker.e(((w00) workDatabase_Impl.getOpenHelper()).j());
                            }
                        }
                    }
                }
                do {
                    AtomicBoolean atomicBoolean2 = qp0Var.r;
                    boolean zCompareAndSet = atomicBoolean2.compareAndSet(false, true);
                    atomicBoolean = qp0Var.q;
                    if (zCompareAndSet) {
                        z = false;
                        Object objCall = null;
                        while (atomicBoolean.compareAndSet(true, false)) {
                            try {
                                try {
                                    objCall = qp0Var.o.call();
                                    z = true;
                                } catch (Exception e) {
                                    throw new RuntimeException("Exception while computing database live data.", e);
                                }
                            } finally {
                                atomicBoolean2.set(false);
                            }
                        }
                        if (z) {
                            qp0Var.f(objCall);
                        }
                    } else {
                        z = false;
                    }
                    if (!z) {
                        return;
                    }
                } while (atomicBoolean.get());
                return;
            default:
                qp0 qp0Var2 = this.F;
                t40.n(qp0Var2, "this$0");
                boolean z3 = qp0Var2.c > 0;
                if (qp0Var2.q.compareAndSet(false, true) && z3) {
                    boolean z4 = qp0Var2.n;
                    WorkDatabase_Impl workDatabase_Impl2 = qp0Var2.l;
                    (z4 ? workDatabase_Impl2.getTransactionExecutor() : workDatabase_Impl2.getQueryExecutor()).execute(qp0Var2.t);
                    return;
                }
                return;
        }
    }
}
