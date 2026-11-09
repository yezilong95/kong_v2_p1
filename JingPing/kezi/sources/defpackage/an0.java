package defpackage;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.logging.Logger;

/* JADX INFO: loaded from: classes.dex */
public final class an0 {
    public static final an0 h;
    public static final Logger i;
    public final d90 a;
    public boolean c;
    public long d;
    public int b = 10000;
    public final ArrayList e = new ArrayList();
    public final ArrayList f = new ArrayList();
    public final v6 g = new v6(16, this);

    static {
        String str = kq0.g + " TaskRunner";
        oy.j(str, "name");
        hq0 hq0Var = new hq0(str, true);
        d90 d90Var = new d90();
        d90Var.b = new ThreadPoolExecutor(0, Integer.MAX_VALUE, 60L, TimeUnit.SECONDS, new SynchronousQueue(), hq0Var);
        h = new an0(d90Var);
        Logger logger = Logger.getLogger(an0.class.getName());
        oy.i(logger, "getLogger(TaskRunner::class.java.name)");
        i = logger;
    }

    public an0(d90 d90Var) {
        this.a = d90Var;
    }

    public static final void a(an0 an0Var, vm0 vm0Var) {
        an0Var.getClass();
        byte[] bArr = kq0.a;
        Thread threadCurrentThread = Thread.currentThread();
        String name = threadCurrentThread.getName();
        threadCurrentThread.setName(vm0Var.a);
        try {
            long jA = vm0Var.a();
            synchronized (an0Var) {
                an0Var.b(vm0Var, jA);
            }
            threadCurrentThread.setName(name);
        } catch (Throwable th) {
            synchronized (an0Var) {
                an0Var.b(vm0Var, -1L);
                threadCurrentThread.setName(name);
                throw th;
            }
        }
    }

    public final void b(vm0 vm0Var, long j) {
        byte[] bArr = kq0.a;
        zm0 zm0Var = vm0Var.c;
        oy.g(zm0Var);
        if (zm0Var.d != vm0Var) {
            throw new IllegalStateException("Check failed.");
        }
        boolean z = zm0Var.f;
        zm0Var.f = false;
        zm0Var.d = null;
        this.e.remove(zm0Var);
        if (j != -1 && !z && !zm0Var.c) {
            zm0Var.d(vm0Var, j, true);
        }
        if (zm0Var.e.isEmpty()) {
            return;
        }
        this.f.add(zm0Var);
    }

    public final vm0 c() {
        long j;
        vm0 vm0Var;
        boolean z;
        byte[] bArr = kq0.a;
        while (true) {
            ArrayList arrayList = this.f;
            if (arrayList.isEmpty()) {
                return null;
            }
            d90 d90Var = this.a;
            long jNanoTime = System.nanoTime();
            Iterator it = arrayList.iterator();
            long jMin = Long.MAX_VALUE;
            vm0 vm0Var2 = null;
            while (true) {
                if (!it.hasNext()) {
                    j = jNanoTime;
                    vm0Var = null;
                    z = false;
                    break;
                }
                vm0 vm0Var3 = (vm0) ((zm0) it.next()).e.get(0);
                j = jNanoTime;
                vm0Var = null;
                long jMax = Math.max(0L, vm0Var3.d - j);
                if (jMax > 0) {
                    jMin = Math.min(jMax, jMin);
                } else {
                    if (vm0Var2 != null) {
                        z = true;
                        break;
                    }
                    vm0Var2 = vm0Var3;
                }
                jNanoTime = j;
            }
            ArrayList arrayList2 = this.e;
            if (vm0Var2 != null) {
                byte[] bArr2 = kq0.a;
                vm0Var2.d = -1L;
                zm0 zm0Var = vm0Var2.c;
                oy.g(zm0Var);
                zm0Var.e.remove(vm0Var2);
                arrayList.remove(zm0Var);
                zm0Var.d = vm0Var2;
                arrayList2.add(zm0Var);
                if (z || (!this.c && !arrayList.isEmpty())) {
                    v6 v6Var = this.g;
                    oy.j(v6Var, "runnable");
                    ((ThreadPoolExecutor) d90Var.b).execute(v6Var);
                }
                return vm0Var2;
            }
            if (this.c) {
                if (jMin >= this.d - j) {
                    return vm0Var;
                }
                notify();
                return vm0Var;
            }
            this.c = true;
            this.d = j + jMin;
            try {
                try {
                    long j2 = jMin / 1000000;
                    long j3 = jMin - (1000000 * j2);
                    if (j2 > 0 || jMin > 0) {
                        wait(j2, (int) j3);
                    }
                } catch (InterruptedException unused) {
                    for (int size = arrayList2.size() - 1; -1 < size; size--) {
                        ((zm0) arrayList2.get(size)).b();
                    }
                    for (int size2 = arrayList.size() - 1; -1 < size2; size2--) {
                        zm0 zm0Var2 = (zm0) arrayList.get(size2);
                        zm0Var2.b();
                        if (zm0Var2.e.isEmpty()) {
                            arrayList.remove(size2);
                        }
                    }
                }
            } finally {
                this.c = false;
            }
        }
    }

    public final void d(zm0 zm0Var) {
        oy.j(zm0Var, "taskQueue");
        byte[] bArr = kq0.a;
        if (zm0Var.d == null) {
            boolean zIsEmpty = zm0Var.e.isEmpty();
            ArrayList arrayList = this.f;
            if (zIsEmpty) {
                arrayList.remove(zm0Var);
            } else {
                oy.j(arrayList, "<this>");
                if (!arrayList.contains(zm0Var)) {
                    arrayList.add(zm0Var);
                }
            }
        }
        boolean z = this.c;
        d90 d90Var = this.a;
        if (z) {
            notify();
            return;
        }
        v6 v6Var = this.g;
        oy.j(v6Var, "runnable");
        ((ThreadPoolExecutor) d90Var.b).execute(v6Var);
    }

    public final zm0 e() {
        int i2;
        synchronized (this) {
            i2 = this.b;
            this.b = i2 + 1;
        }
        return new zm0(this, hk.g(i2, "Q"));
    }
}
