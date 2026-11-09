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
public final class kj implements Executor, Closeable {
    public static final /* synthetic */ AtomicLongFieldUpdater L = AtomicLongFieldUpdater.newUpdater(kj.class, "parkedWorkersStack$volatile");
    public static final /* synthetic */ AtomicLongFieldUpdater M = AtomicLongFieldUpdater.newUpdater(kj.class, "controlState$volatile");
    public static final /* synthetic */ AtomicIntegerFieldUpdater N = AtomicIntegerFieldUpdater.newUpdater(kj.class, "_isTerminated$volatile");
    public static final wy0 O = new wy0("NOT_IN_STACK", 0);
    public final int E;
    public final int F;
    public final long G;
    public final String H;
    public final i20 I;
    public final i20 J;
    public final ro0 K;
    private volatile /* synthetic */ int _isTerminated$volatile;
    private volatile /* synthetic */ long controlState$volatile;
    private volatile /* synthetic */ long parkedWorkersStack$volatile;

    public kj(long j, String str, int i, int i2) {
        this.E = i;
        this.F = i2;
        this.G = j;
        this.H = str;
        if (i < 1) {
            throw new IllegalArgumentException(zn.k(i, "Core pool size ", " should be at least 1").toString());
        }
        if (i2 < i) {
            throw new IllegalArgumentException(zn.l("Max pool size ", i2, i, " should be greater than or equals to core pool size ").toString());
        }
        if (i2 > 2097150) {
            throw new IllegalArgumentException(zn.k(i2, "Max pool size ", " should not exceed maximal supported number of threads 2097150").toString());
        }
        if (j <= 0) {
            throw new IllegalArgumentException(("Idle worker keep alive time " + j + " must be positive").toString());
        }
        this.I = new i20();
        this.J = new i20();
        this.K = new ro0((i + 1) * 2);
        this.controlState$volatile = ((long) i) << 42;
        this._isTerminated$volatile = 0;
    }

    public static /* synthetic */ void w(kj kjVar, Runnable runnable, int i) {
        kjVar.l(runnable, mz0.g, (i & 4) == 0);
    }

    /* JADX WARN: Removed duplicated region for block: B:39:0x008a  */
    @Override // java.io.Closeable, java.lang.AutoCloseable
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final void close() throws java.lang.InterruptedException {
        /*
            r8 = this;
            java.util.concurrent.atomic.AtomicIntegerFieldUpdater r0 = defpackage.kj.N
            r1 = 0
            r2 = 1
            boolean r0 = r0.compareAndSet(r8, r1, r2)
            if (r0 != 0) goto Lb
            return
        Lb:
            java.lang.Thread r0 = java.lang.Thread.currentThread()
            boolean r1 = r0 instanceof defpackage.jj
            r3 = 0
            if (r1 == 0) goto L17
            jj r0 = (defpackage.jj) r0
            goto L18
        L17:
            r0 = r3
        L18:
            if (r0 == 0) goto L23
            kj r1 = r0.L
            boolean r1 = defpackage.t40.f(r1, r8)
            if (r1 == 0) goto L23
            goto L24
        L23:
            r0 = r3
        L24:
            ro0 r1 = r8.K
            monitor-enter(r1)
            java.util.concurrent.atomic.AtomicLongFieldUpdater r4 = defpackage.kj.M     // Catch: java.lang.Throwable -> Lc3
            long r4 = r4.get(r8)     // Catch: java.lang.Throwable -> Lc3
            r6 = 2097151(0x1fffff, double:1.0361303E-317)
            long r4 = r4 & r6
            int r4 = (int) r4
            monitor-exit(r1)
            if (r2 > r4) goto L78
            r1 = r2
        L36:
            ro0 r5 = r8.K
            java.lang.Object r5 = r5.b(r1)
            defpackage.t40.k(r5)
            jj r5 = (defpackage.jj) r5
            if (r5 == r0) goto L73
        L43:
            java.lang.Thread$State r6 = r5.getState()
            java.lang.Thread$State r7 = java.lang.Thread.State.TERMINATED
            if (r6 == r7) goto L54
            java.util.concurrent.locks.LockSupport.unpark(r5)
            r6 = 10000(0x2710, double:4.9407E-320)
            r5.join(r6)
            goto L43
        L54:
            e91 r5 = r5.E
            i20 r6 = r8.J
            r5.getClass()
            java.util.concurrent.atomic.AtomicReferenceFieldUpdater r7 = defpackage.e91.b
            java.lang.Object r7 = r7.getAndSet(r5, r3)
            kz0 r7 = (defpackage.kz0) r7
            if (r7 == 0) goto L68
            r6.a(r7)
        L68:
            kz0 r7 = r5.b()
            if (r7 != 0) goto L6f
            goto L73
        L6f:
            r6.a(r7)
            goto L68
        L73:
            if (r1 == r4) goto L78
            int r1 = r1 + 1
            goto L36
        L78:
            i20 r1 = r8.J
            r1.b()
            i20 r1 = r8.I
            r1.b()
        L82:
            if (r0 == 0) goto L8a
            kz0 r1 = r0.a(r2)
            if (r1 != 0) goto Lb2
        L8a:
            i20 r1 = r8.I
            java.lang.Object r1 = r1.d()
            kz0 r1 = (defpackage.kz0) r1
            if (r1 != 0) goto Lb2
            i20 r1 = r8.J
            java.lang.Object r1 = r1.d()
            kz0 r1 = (defpackage.kz0) r1
            if (r1 != 0) goto Lb2
            if (r0 == 0) goto La5
            kotlinx.coroutines.scheduling.CoroutineScheduler$WorkerState r1 = kotlinx.coroutines.scheduling.CoroutineScheduler$WorkerState.TERMINATED
            r0.h(r1)
        La5:
            java.util.concurrent.atomic.AtomicLongFieldUpdater r0 = defpackage.kj.L
            r1 = 0
            r0.set(r8, r1)
            java.util.concurrent.atomic.AtomicLongFieldUpdater r0 = defpackage.kj.M
            r0.set(r8, r1)
            return
        Lb2:
            r1.run()     // Catch: java.lang.Throwable -> Lb6
            goto L82
        Lb6:
            r1 = move-exception
            java.lang.Thread r3 = java.lang.Thread.currentThread()
            java.lang.Thread$UncaughtExceptionHandler r4 = r3.getUncaughtExceptionHandler()
            r4.uncaughtException(r3, r1)
            goto L82
        Lc3:
            r0 = move-exception
            monitor-exit(r1)
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.kj.close():void");
    }

    @Override // java.util.concurrent.Executor
    public final void execute(Runnable runnable) {
        w(this, runnable, 6);
    }

    public final int j() {
        synchronized (this.K) {
            try {
                if (N.get(this) != 0) {
                    return -1;
                }
                AtomicLongFieldUpdater atomicLongFieldUpdater = M;
                long j = atomicLongFieldUpdater.get(this);
                int i = (int) (j & 2097151);
                int i2 = i - ((int) ((j & 4398044413952L) >> 21));
                if (i2 < 0) {
                    i2 = 0;
                }
                if (i2 >= this.E) {
                    return 0;
                }
                if (i >= this.F) {
                    return 0;
                }
                int i3 = ((int) (atomicLongFieldUpdater.get(this) & 2097151)) + 1;
                if (i3 <= 0 || this.K.b(i3) != null) {
                    throw new IllegalArgumentException("Failed requirement.");
                }
                jj jjVar = new jj(this, i3);
                this.K.c(i3, jjVar);
                if (i3 != ((int) (2097151 & atomicLongFieldUpdater.incrementAndGet(this)))) {
                    throw new IllegalArgumentException("Failed requirement.");
                }
                int i4 = i2 + 1;
                jjVar.start();
                return i4;
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    public final void l(Runnable runnable, iq0 iq0Var, boolean z) {
        kz0 lz0Var;
        CoroutineScheduler$WorkerState coroutineScheduler$WorkerState;
        mz0.f.getClass();
        long jNanoTime = System.nanoTime();
        if (runnable instanceof kz0) {
            lz0Var = (kz0) runnable;
            lz0Var.E = jNanoTime;
            lz0Var.F = iq0Var;
        } else {
            lz0Var = new lz0(runnable, jNanoTime, iq0Var);
        }
        boolean z2 = false;
        boolean z3 = lz0Var.F.F == 1;
        AtomicLongFieldUpdater atomicLongFieldUpdater = M;
        long jAddAndGet = z3 ? atomicLongFieldUpdater.addAndGet(this, 2097152L) : 0L;
        Thread threadCurrentThread = Thread.currentThread();
        jj jjVar = threadCurrentThread instanceof jj ? (jj) threadCurrentThread : null;
        if (jjVar == null || !t40.f(jjVar.L, this)) {
            jjVar = null;
        }
        if (jjVar != null && (coroutineScheduler$WorkerState = jjVar.G) != CoroutineScheduler$WorkerState.TERMINATED && (lz0Var.F.F != 0 || coroutineScheduler$WorkerState != CoroutineScheduler$WorkerState.BLOCKING)) {
            jjVar.K = true;
            e91 e91Var = jjVar.E;
            if (z) {
                lz0Var = e91Var.a(lz0Var);
            } else {
                e91Var.getClass();
                kz0 kz0Var = (kz0) e91.b.getAndSet(e91Var, lz0Var);
                lz0Var = kz0Var == null ? null : e91Var.a(kz0Var);
            }
        }
        if (lz0Var != null) {
            if (!(lz0Var.F.F == 1 ? this.J.a(lz0Var) : this.I.a(lz0Var))) {
                throw new RejectedExecutionException(zn.o(new StringBuilder(), this.H, " was terminated"));
            }
        }
        if (z && jjVar != null) {
            z2 = true;
        }
        if (z3) {
            if (z2 || z() || y(jAddAndGet)) {
                return;
            }
            z();
            return;
        }
        if (z2 || z() || y(atomicLongFieldUpdater.get(this))) {
            return;
        }
        z();
    }

    public final String toString() {
        ArrayList arrayList = new ArrayList();
        ro0 ro0Var = this.K;
        int iA = ro0Var.a();
        int i = 0;
        int i2 = 0;
        int i3 = 0;
        int i4 = 0;
        int i5 = 0;
        for (int i6 = 1; i6 < iA; i6++) {
            jj jjVar = (jj) ro0Var.b(i6);
            if (jjVar != null) {
                e91 e91Var = jjVar.E;
                e91Var.getClass();
                int i7 = e91.b.get(e91Var) != null ? (e91.c.get(e91Var) - e91.d.get(e91Var)) + 1 : e91.c.get(e91Var) - e91.d.get(e91Var);
                int i8 = ij.a[jjVar.G.ordinal()];
                if (i8 == 1) {
                    i3++;
                } else if (i8 == 2) {
                    i2++;
                    StringBuilder sb = new StringBuilder();
                    sb.append(i7);
                    sb.append('b');
                    arrayList.add(sb.toString());
                } else if (i8 == 3) {
                    i++;
                    StringBuilder sb2 = new StringBuilder();
                    sb2.append(i7);
                    sb2.append('c');
                    arrayList.add(sb2.toString());
                } else if (i8 == 4) {
                    i4++;
                    if (i7 > 0) {
                        StringBuilder sb3 = new StringBuilder();
                        sb3.append(i7);
                        sb3.append('d');
                        arrayList.add(sb3.toString());
                    }
                } else if (i8 == 5) {
                    i5++;
                }
            }
        }
        long j = M.get(this);
        StringBuilder sb4 = new StringBuilder();
        sb4.append(this.H);
        sb4.append('@');
        sb4.append(qm.C(this));
        sb4.append("[Pool Size {core = ");
        int i9 = this.E;
        sb4.append(i9);
        sb4.append(", max = ");
        sb4.append(this.F);
        sb4.append("}, Worker States {CPU = ");
        sb4.append(i);
        sb4.append(", blocking = ");
        sb4.append(i2);
        sb4.append(", parked = ");
        sb4.append(i3);
        sb4.append(", dormant = ");
        sb4.append(i4);
        sb4.append(", terminated = ");
        sb4.append(i5);
        sb4.append("}, running workers queues = ");
        sb4.append(arrayList);
        sb4.append(", global CPU queue size = ");
        sb4.append(this.I.c());
        sb4.append(", global blocking queue size = ");
        sb4.append(this.J.c());
        sb4.append(", Control State {created workers= ");
        sb4.append((int) (2097151 & j));
        sb4.append(", blocking tasks = ");
        sb4.append((int) ((4398044413952L & j) >> 21));
        sb4.append(", CPUs acquired = ");
        sb4.append(i9 - ((int) ((j & 9223367638808264704L) >> 42)));
        sb4.append("}]");
        return sb4.toString();
    }

    public final void x(jj jjVar, int i, int i2) {
        while (true) {
            long j = L.get(this);
            int i3 = (int) (2097151 & j);
            long j2 = (2097152 + j) & (-2097152);
            if (i3 == i) {
                if (i2 == 0) {
                    Object objC = jjVar.c();
                    while (true) {
                        if (objC == O) {
                            i3 = -1;
                            break;
                        }
                        if (objC == null) {
                            i3 = 0;
                            break;
                        }
                        jj jjVar2 = (jj) objC;
                        int iB = jjVar2.b();
                        if (iB != 0) {
                            i3 = iB;
                            break;
                        }
                        objC = jjVar2.c();
                    }
                } else {
                    i3 = i2;
                }
            }
            if (i3 >= 0) {
                if (L.compareAndSet(this, j, ((long) i3) | j2)) {
                    return;
                }
            }
        }
    }

    public final boolean y(long j) {
        int i = ((int) (2097151 & j)) - ((int) ((j & 4398044413952L) >> 21));
        if (i < 0) {
            i = 0;
        }
        int i2 = this.E;
        if (i < i2) {
            int iJ = j();
            if (iJ == 1 && i2 > 1) {
                j();
            }
            if (iJ > 0) {
                return true;
            }
        }
        return false;
    }

    public final boolean z() {
        wy0 wy0Var;
        int iB;
        while (true) {
            AtomicLongFieldUpdater atomicLongFieldUpdater = L;
            long j = atomicLongFieldUpdater.get(this);
            jj jjVar = (jj) this.K.b((int) (2097151 & j));
            if (jjVar == null) {
                jjVar = null;
            } else {
                long j2 = (2097152 + j) & (-2097152);
                Object objC = jjVar.c();
                while (true) {
                    wy0Var = O;
                    if (objC == wy0Var) {
                        iB = -1;
                        break;
                    }
                    if (objC == null) {
                        iB = 0;
                        break;
                    }
                    jj jjVar2 = (jj) objC;
                    iB = jjVar2.b();
                    if (iB != 0) {
                        break;
                    }
                    objC = jjVar2.c();
                }
                if (iB >= 0 && atomicLongFieldUpdater.compareAndSet(this, j, j2 | ((long) iB))) {
                    jjVar.g(wy0Var);
                }
            }
            if (jjVar == null) {
                return false;
            }
            if (jj.M.compareAndSet(jjVar, -1, 0)) {
                LockSupport.unpark(jjVar);
                return true;
            }
        }
    }
}
