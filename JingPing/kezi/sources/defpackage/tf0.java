package defpackage;

import androidx.work.impl.WorkDatabase_Impl;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Locale;
import java.util.concurrent.atomic.AtomicBoolean;

/* JADX INFO: loaded from: classes.dex */
public final /* synthetic */ class tf0 implements Runnable {
    public final /* synthetic */ int b;
    public final /* synthetic */ vf0 c;

    public /* synthetic */ tf0(vf0 vf0Var, int i) {
        this.b = i;
        this.c = vf0Var;
    }

    @Override // java.lang.Runnable
    public final void run() {
        AtomicBoolean atomicBoolean;
        boolean z;
        Object obj;
        ry ryVar;
        boolean z2;
        switch (this.b) {
            case 0:
                vf0 vf0Var = this.c;
                oy.j(vf0Var, "this$0");
                if (vf0Var.s.compareAndSet(false, true)) {
                    ty invalidationTracker = vf0Var.l.getInvalidationTracker();
                    uf0 uf0Var = vf0Var.p;
                    invalidationTracker.getClass();
                    oy.j(uf0Var, "observer");
                    sy syVar = new sy(invalidationTracker, uf0Var);
                    String[] strArrC = invalidationTracker.c(syVar.a);
                    ArrayList arrayList = new ArrayList(strArrC.length);
                    for (String str : strArrC) {
                        LinkedHashMap linkedHashMap = invalidationTracker.d;
                        Locale locale = Locale.US;
                        oy.i(locale, "US");
                        String lowerCase = str.toLowerCase(locale);
                        oy.i(lowerCase, "this as java.lang.String).toLowerCase(locale)");
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
                    ry ryVar2 = new ry(syVar, iArr, strArrC);
                    synchronized (invalidationTracker.k) {
                        lg0 lg0Var = invalidationTracker.k;
                        ig0 ig0VarA = lg0Var.a(syVar);
                        if (ig0VarA != null) {
                            obj = ig0VarA.c;
                        } else {
                            ig0 ig0Var = new ig0(syVar, ryVar2);
                            lg0Var.e++;
                            ig0 ig0Var2 = lg0Var.c;
                            if (ig0Var2 == null) {
                                lg0Var.b = ig0Var;
                                lg0Var.c = ig0Var;
                            } else {
                                ig0Var2.d = ig0Var;
                                ig0Var.e = ig0Var2;
                                lg0Var.c = ig0Var;
                            }
                            obj = null;
                        }
                        ryVar = (ry) obj;
                    }
                    if (ryVar == null) {
                        py pyVar = invalidationTracker.i;
                        int[] iArrCopyOf = Arrays.copyOf(iArr, size);
                        pyVar.getClass();
                        oy.j(iArrCopyOf, "tableIds");
                        synchronized (pyVar) {
                            z2 = false;
                            for (int i2 : iArrCopyOf) {
                                long[] jArr = pyVar.a;
                                long j = jArr[i2];
                                jArr[i2] = 1 + j;
                                if (j == 0) {
                                    pyVar.d = true;
                                    z2 = true;
                                }
                            }
                        }
                        if (z2) {
                            WorkDatabase_Impl workDatabase_Impl = invalidationTracker.a;
                            if (workDatabase_Impl.isOpen()) {
                                invalidationTracker.e(((ku) workDatabase_Impl.getOpenHelper()).a());
                            }
                        }
                    }
                }
                do {
                    AtomicBoolean atomicBoolean2 = vf0Var.r;
                    boolean zCompareAndSet = atomicBoolean2.compareAndSet(false, true);
                    atomicBoolean = vf0Var.q;
                    if (zCompareAndSet) {
                        z = false;
                        Object objCall = null;
                        while (atomicBoolean.compareAndSet(true, false)) {
                            try {
                                try {
                                    objCall = vf0Var.o.call();
                                    z = true;
                                } catch (Exception e) {
                                    throw new RuntimeException("Exception while computing database live data.", e);
                                }
                            } finally {
                                atomicBoolean2.set(false);
                            }
                        }
                        if (z) {
                            vf0Var.g(objCall);
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
                vf0 vf0Var2 = this.c;
                oy.j(vf0Var2, "this$0");
                boolean z3 = vf0Var2.c > 0;
                if (vf0Var2.q.compareAndSet(false, true) && z3) {
                    boolean z4 = vf0Var2.n;
                    WorkDatabase_Impl workDatabase_Impl2 = vf0Var2.l;
                    (z4 ? workDatabase_Impl2.getTransactionExecutor() : workDatabase_Impl2.getQueryExecutor()).execute(vf0Var2.t);
                    return;
                }
                return;
        }
    }
}
