package defpackage;

import java.io.Closeable;
import java.util.ArrayList;
import java.util.concurrent.Executor;
import java.util.concurrent.RejectedExecutionException;
import java.util.concurrent.atomic.AtomicIntegerFieldUpdater;
import java.util.concurrent.atomic.AtomicLongFieldUpdater;
import java.util.concurrent.locks.LockSupport;
import kotlinx.coroutines.scheduling.CoroutineScheduler$WorkerState;

/* JADX INFO: loaded from: classes.dex */
public final class wh implements Executor, Closeable {
    public static final AtomicLongFieldUpdater i = AtomicLongFieldUpdater.newUpdater(wh.class, "parkedWorkersStack");
    public static final AtomicLongFieldUpdater j = AtomicLongFieldUpdater.newUpdater(wh.class, "controlState");
    public static final AtomicIntegerFieldUpdater k = AtomicIntegerFieldUpdater.newUpdater(wh.class, "_isTerminated");
    public static final nm l = new nm("NOT_IN_STACK");
    private volatile int _isTerminated;
    public final int b;
    public final int c;
    private volatile long controlState;
    public final long d;
    public final String e;
    public final iv f;
    public final iv g;
    public final se0 h;
    private volatile long parkedWorkersStack;

    public wh(long j2, String str, int i2, int i3) {
        this.b = i2;
        this.c = i3;
        this.d = j2;
        this.e = str;
        if (i2 < 1) {
            throw new IllegalArgumentException(hk.h(i2, "Core pool size ", " should be at least 1").toString());
        }
        if (i3 < i2) {
            throw new IllegalArgumentException(hk.f(i3, i2, "Max pool size ", " should be greater than or equals to core pool size ").toString());
        }
        if (i3 > 2097150) {
            throw new IllegalArgumentException(hk.h(i3, "Max pool size ", " should not exceed maximal supported number of threads 2097150").toString());
        }
        if (j2 <= 0) {
            throw new IllegalArgumentException(("Idle worker keep alive time " + j2 + " must be positive").toString());
        }
        this.f = new iv();
        this.g = new iv();
        this.h = new se0((i2 + 1) * 2);
        this.controlState = ((long) i2) << 42;
        this._isTerminated = 0;
    }

    public static /* synthetic */ void C(wh whVar, Runnable runnable, int i2) {
        whVar.t(runnable, bn0.g, (i2 & 4) == 0);
    }

    public final void D(vh vhVar, int i2, int i3) {
        while (true) {
            long j2 = i.get(this);
            int i4 = (int) (2097151 & j2);
            long j3 = (2097152 + j2) & (-2097152);
            if (i4 == i2) {
                if (i3 == 0) {
                    Object objC = vhVar.c();
                    while (true) {
                        if (objC == l) {
                            i4 = -1;
                            break;
                        }
                        if (objC == null) {
                            i4 = 0;
                            break;
                        }
                        vh vhVar2 = (vh) objC;
                        int iB = vhVar2.b();
                        if (iB != 0) {
                            i4 = iB;
                            break;
                        }
                        objC = vhVar2.c();
                    }
                } else {
                    i4 = i3;
                }
            }
            if (i4 >= 0) {
                if (i.compareAndSet(this, j2, ((long) i4) | j3)) {
                    return;
                }
            }
        }
    }

    public final boolean E(long j2) {
        int i2 = ((int) (2097151 & j2)) - ((int) ((j2 & 4398044413952L) >> 21));
        if (i2 < 0) {
            i2 = 0;
        }
        int i3 = this.b;
        if (i2 < i3) {
            int iA = a();
            if (iA == 1 && i3 > 1) {
                a();
            }
            if (iA > 0) {
                return true;
            }
        }
        return false;
    }

    public final boolean F() {
        nm nmVar;
        int iB;
        while (true) {
            AtomicLongFieldUpdater atomicLongFieldUpdater = i;
            long j2 = atomicLongFieldUpdater.get(this);
            vh vhVar = (vh) this.h.b((int) (2097151 & j2));
            if (vhVar == null) {
                vhVar = null;
            } else {
                long j3 = (2097152 + j2) & (-2097152);
                Object objC = vhVar.c();
                while (true) {
                    nmVar = l;
                    if (objC == nmVar) {
                        iB = -1;
                        break;
                    }
                    if (objC == null) {
                        iB = 0;
                        break;
                    }
                    vh vhVar2 = (vh) objC;
                    iB = vhVar2.b();
                    if (iB != 0) {
                        break;
                    }
                    objC = vhVar2.c();
                }
                if (iB >= 0 && atomicLongFieldUpdater.compareAndSet(this, j2, j3 | ((long) iB))) {
                    vhVar.g(nmVar);
                }
            }
            if (vhVar == null) {
                return false;
            }
            if (vh.j.compareAndSet(vhVar, -1, 0)) {
                LockSupport.unpark(vhVar);
                return true;
            }
        }
    }

    public final int a() {
        synchronized (this.h) {
            try {
                if (k.get(this) != 0) {
                    return -1;
                }
                AtomicLongFieldUpdater atomicLongFieldUpdater = j;
                long j2 = atomicLongFieldUpdater.get(this);
                int i2 = (int) (j2 & 2097151);
                int i3 = i2 - ((int) ((j2 & 4398044413952L) >> 21));
                if (i3 < 0) {
                    i3 = 0;
                }
                if (i3 >= this.b) {
                    return 0;
                }
                if (i2 >= this.c) {
                    return 0;
                }
                int i4 = ((int) (atomicLongFieldUpdater.get(this) & 2097151)) + 1;
                if (i4 <= 0 || this.h.b(i4) != null) {
                    throw new IllegalArgumentException("Failed requirement.");
                }
                vh vhVar = new vh(this, i4);
                this.h.c(i4, vhVar);
                if (i4 != ((int) (2097151 & atomicLongFieldUpdater.incrementAndGet(this)))) {
                    throw new IllegalArgumentException("Failed requirement.");
                }
                int i5 = i3 + 1;
                vhVar.start();
                return i5;
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:39:0x0088  */
    @Override // java.io.Closeable, java.lang.AutoCloseable
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final void close() throws java.lang.InterruptedException {
        /*
            r8 = this;
            java.util.concurrent.atomic.AtomicIntegerFieldUpdater r0 = defpackage.wh.k
            r1 = 0
            r2 = 1
            boolean r0 = r0.compareAndSet(r8, r1, r2)
            if (r0 != 0) goto Lb
            return
        Lb:
            java.lang.Thread r0 = java.lang.Thread.currentThread()
            boolean r1 = r0 instanceof defpackage.vh
            r3 = 0
            if (r1 == 0) goto L17
            vh r0 = (defpackage.vh) r0
            goto L18
        L17:
            r0 = r3
        L18:
            if (r0 == 0) goto L23
            wh r1 = r0.i
            boolean r1 = defpackage.oy.c(r1, r8)
            if (r1 == 0) goto L23
            goto L24
        L23:
            r0 = r3
        L24:
            se0 r1 = r8.h
            monitor-enter(r1)
            java.util.concurrent.atomic.AtomicLongFieldUpdater r4 = defpackage.wh.j     // Catch: java.lang.Throwable -> Lc1
            long r4 = r4.get(r8)     // Catch: java.lang.Throwable -> Lc1
            r6 = 2097151(0x1fffff, double:1.0361303E-317)
            long r4 = r4 & r6
            int r4 = (int) r4
            monitor-exit(r1)
            if (r2 > r4) goto L76
            r1 = r2
        L36:
            se0 r5 = r8.h
            java.lang.Object r5 = r5.b(r1)
            defpackage.oy.g(r5)
            vh r5 = (defpackage.vh) r5
            if (r5 == r0) goto L71
        L43:
            boolean r6 = r5.isAlive()
            if (r6 == 0) goto L52
            java.util.concurrent.locks.LockSupport.unpark(r5)
            r6 = 10000(0x2710, double:4.9407E-320)
            r5.join(r6)
            goto L43
        L52:
            fv0 r5 = r5.b
            iv r6 = r8.g
            r5.getClass()
            java.util.concurrent.atomic.AtomicReferenceFieldUpdater r7 = defpackage.fv0.b
            java.lang.Object r7 = r7.getAndSet(r5, r3)
            wm0 r7 = (defpackage.wm0) r7
            if (r7 == 0) goto L66
            r6.a(r7)
        L66:
            wm0 r7 = r5.b()
            if (r7 != 0) goto L6d
            goto L71
        L6d:
            r6.a(r7)
            goto L66
        L71:
            if (r1 == r4) goto L76
            int r1 = r1 + 1
            goto L36
        L76:
            iv r1 = r8.g
            r1.b()
            iv r1 = r8.f
            r1.b()
        L80:
            if (r0 == 0) goto L88
            wm0 r1 = r0.a(r2)
            if (r1 != 0) goto Lb0
        L88:
            iv r1 = r8.f
            java.lang.Object r1 = r1.d()
            wm0 r1 = (defpackage.wm0) r1
            if (r1 != 0) goto Lb0
            iv r1 = r8.g
            java.lang.Object r1 = r1.d()
            wm0 r1 = (defpackage.wm0) r1
            if (r1 != 0) goto Lb0
            if (r0 == 0) goto La3
            kotlinx.coroutines.scheduling.CoroutineScheduler$WorkerState r1 = kotlinx.coroutines.scheduling.CoroutineScheduler$WorkerState.TERMINATED
            r0.h(r1)
        La3:
            java.util.concurrent.atomic.AtomicLongFieldUpdater r0 = defpackage.wh.i
            r1 = 0
            r0.set(r8, r1)
            java.util.concurrent.atomic.AtomicLongFieldUpdater r0 = defpackage.wh.j
            r0.set(r8, r1)
            return
        Lb0:
            r1.run()     // Catch: java.lang.Throwable -> Lb4
            goto L80
        Lb4:
            r1 = move-exception
            java.lang.Thread r3 = java.lang.Thread.currentThread()
            java.lang.Thread$UncaughtExceptionHandler r4 = r3.getUncaughtExceptionHandler()
            r4.uncaughtException(r3, r1)
            goto L80
        Lc1:
            r0 = move-exception
            monitor-exit(r1)
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.wh.close():void");
    }

    @Override // java.util.concurrent.Executor
    public final void execute(Runnable runnable) {
        C(this, runnable, 6);
    }

    public final void t(Runnable runnable, xm0 xm0Var, boolean z) {
        wm0 ym0Var;
        CoroutineScheduler$WorkerState coroutineScheduler$WorkerState;
        bn0.f.getClass();
        long jNanoTime = System.nanoTime();
        if (runnable instanceof wm0) {
            ym0Var = (wm0) runnable;
            ym0Var.b = jNanoTime;
            ym0Var.c = xm0Var;
        } else {
            ym0Var = new ym0(runnable, jNanoTime, xm0Var);
        }
        boolean z2 = false;
        boolean z3 = ym0Var.c.a == 1;
        AtomicLongFieldUpdater atomicLongFieldUpdater = j;
        long jAddAndGet = z3 ? atomicLongFieldUpdater.addAndGet(this, 2097152L) : 0L;
        Thread threadCurrentThread = Thread.currentThread();
        vh vhVar = threadCurrentThread instanceof vh ? (vh) threadCurrentThread : null;
        if (vhVar == null || !oy.c(vhVar.i, this)) {
            vhVar = null;
        }
        if (vhVar != null && (coroutineScheduler$WorkerState = vhVar.d) != CoroutineScheduler$WorkerState.TERMINATED && (ym0Var.c.a != 0 || coroutineScheduler$WorkerState != CoroutineScheduler$WorkerState.BLOCKING)) {
            vhVar.h = true;
            fv0 fv0Var = vhVar.b;
            if (z) {
                ym0Var = fv0Var.a(ym0Var);
            } else {
                fv0Var.getClass();
                wm0 wm0Var = (wm0) fv0.b.getAndSet(fv0Var, ym0Var);
                ym0Var = wm0Var == null ? null : fv0Var.a(wm0Var);
            }
        }
        if (ym0Var != null) {
            if (!(ym0Var.c.a == 1 ? this.g.a(ym0Var) : this.f.a(ym0Var))) {
                throw new RejectedExecutionException(hk.k(new StringBuilder(), this.e, " was terminated"));
            }
        }
        if (z && vhVar != null) {
            z2 = true;
        }
        if (z3) {
            if (z2 || F() || E(jAddAndGet)) {
                return;
            }
            F();
            return;
        }
        if (z2 || F() || E(atomicLongFieldUpdater.get(this))) {
            return;
        }
        F();
    }

    public final String toString() {
        ArrayList arrayList = new ArrayList();
        se0 se0Var = this.h;
        int iA = se0Var.a();
        int i2 = 0;
        int i3 = 0;
        int i4 = 0;
        int i5 = 0;
        int i6 = 0;
        for (int i7 = 1; i7 < iA; i7++) {
            vh vhVar = (vh) se0Var.b(i7);
            if (vhVar != null) {
                fv0 fv0Var = vhVar.b;
                fv0Var.getClass();
                int i8 = fv0.b.get(fv0Var) != null ? (fv0.c.get(fv0Var) - fv0.d.get(fv0Var)) + 1 : fv0.c.get(fv0Var) - fv0.d.get(fv0Var);
                int i9 = uh.a[vhVar.d.ordinal()];
                if (i9 == 1) {
                    i4++;
                } else if (i9 == 2) {
                    i3++;
                    StringBuilder sb = new StringBuilder();
                    sb.append(i8);
                    sb.append('b');
                    arrayList.add(sb.toString());
                } else if (i9 == 3) {
                    i2++;
                    StringBuilder sb2 = new StringBuilder();
                    sb2.append(i8);
                    sb2.append('c');
                    arrayList.add(sb2.toString());
                } else if (i9 == 4) {
                    i5++;
                    if (i8 > 0) {
                        StringBuilder sb3 = new StringBuilder();
                        sb3.append(i8);
                        sb3.append('d');
                        arrayList.add(sb3.toString());
                    }
                } else if (i9 == 5) {
                    i6++;
                }
            }
        }
        long j2 = j.get(this);
        StringBuilder sb4 = new StringBuilder();
        sb4.append(this.e);
        sb4.append('@');
        sb4.append(xi.y(this));
        sb4.append("[Pool Size {core = ");
        int i10 = this.b;
        sb4.append(i10);
        sb4.append(", max = ");
        sb4.append(this.c);
        sb4.append("}, Worker States {CPU = ");
        sb4.append(i2);
        sb4.append(", blocking = ");
        sb4.append(i3);
        sb4.append(", parked = ");
        sb4.append(i4);
        sb4.append(", dormant = ");
        sb4.append(i5);
        sb4.append(", terminated = ");
        sb4.append(i6);
        sb4.append("}, running workers queues = ");
        sb4.append(arrayList);
        sb4.append(", global CPU queue size = ");
        sb4.append(this.f.c());
        sb4.append(", global blocking queue size = ");
        sb4.append(this.g.c());
        sb4.append(", Control State {created workers= ");
        sb4.append((int) (2097151 & j2));
        sb4.append(", blocking tasks = ");
        sb4.append((int) ((4398044413952L & j2) >> 21));
        sb4.append(", CPUs acquired = ");
        sb4.append(i10 - ((int) ((j2 & 9223367638808264704L) >> 42)));
        sb4.append("}]");
        return sb4.toString();
    }
}
