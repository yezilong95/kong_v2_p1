package defpackage;

import java.util.concurrent.atomic.AtomicIntegerFieldUpdater;
import java.util.concurrent.atomic.AtomicReferenceFieldUpdater;

/* JADX INFO: loaded from: classes.dex */
public abstract class no extends oo implements bk {
    public static final AtomicReferenceFieldUpdater g = AtomicReferenceFieldUpdater.newUpdater(no.class, Object.class, "_queue");
    public static final AtomicReferenceFieldUpdater h = AtomicReferenceFieldUpdater.newUpdater(no.class, Object.class, "_delayed");
    public static final AtomicIntegerFieldUpdater i = AtomicIntegerFieldUpdater.newUpdater(no.class, "_isCompleted");
    private volatile Object _delayed;
    private volatile int _isCompleted = 0;
    private volatile Object _queue;

    @Override // defpackage.oo
    public void J() {
        xn0.a.set(null);
        i.set(this, 1);
        loop0: while (true) {
            AtomicReferenceFieldUpdater atomicReferenceFieldUpdater = g;
            Object obj = atomicReferenceFieldUpdater.get(this);
            nm nmVar = rp0.b;
            if (obj != null) {
                if (!(obj instanceof l30)) {
                    if (obj != nmVar) {
                        l30 l30Var = new l30(8, true);
                        l30Var.a((Runnable) obj);
                        while (!atomicReferenceFieldUpdater.compareAndSet(this, obj, l30Var)) {
                            if (atomicReferenceFieldUpdater.get(this) != obj) {
                                break;
                            }
                        }
                        break loop0;
                    }
                    break;
                }
                ((l30) obj).b();
                break;
            }
            while (!atomicReferenceFieldUpdater.compareAndSet(this, null, nmVar)) {
                if (atomicReferenceFieldUpdater.get(this) != null) {
                    break;
                }
            }
            break loop0;
        }
        while (M() <= 0) {
        }
        System.nanoTime();
    }

    /* JADX WARN: Code restructure failed: missing block: B:29:0x004a, code lost:
    
        defpackage.cj.j.K(r6);
     */
    /* JADX WARN: Code restructure failed: missing block: B:30:0x004f, code lost:
    
        return;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public void K(java.lang.Runnable r6) {
        /*
            r5 = this;
        L0:
            java.util.concurrent.atomic.AtomicReferenceFieldUpdater r0 = defpackage.no.g
            java.lang.Object r1 = r0.get(r5)
            java.util.concurrent.atomic.AtomicIntegerFieldUpdater r2 = defpackage.no.i
            int r2 = r2.get(r5)
            if (r2 == 0) goto Lf
            goto L4a
        Lf:
            if (r1 != 0) goto L20
        L11:
            r1 = 0
            boolean r1 = r0.compareAndSet(r5, r1, r6)
            if (r1 == 0) goto L19
            goto L66
        L19:
            java.lang.Object r1 = r0.get(r5)
            if (r1 == 0) goto L11
            goto L0
        L20:
            boolean r2 = r1 instanceof defpackage.l30
            r3 = 1
            if (r2 == 0) goto L46
            r2 = r1
            l30 r2 = (defpackage.l30) r2
            int r4 = r2.a(r6)
            if (r4 == 0) goto L66
            if (r4 == r3) goto L34
            r0 = 2
            if (r4 == r0) goto L4a
            goto L0
        L34:
            l30 r2 = r2.c()
        L38:
            boolean r3 = r0.compareAndSet(r5, r1, r2)
            if (r3 == 0) goto L3f
            goto L0
        L3f:
            java.lang.Object r3 = r0.get(r5)
            if (r3 == r1) goto L38
            goto L0
        L46:
            nm r2 = defpackage.rp0.b
            if (r1 != r2) goto L50
        L4a:
            cj r0 = defpackage.cj.j
            r0.K(r6)
            return
        L50:
            l30 r2 = new l30
            r4 = 8
            r2.<init>(r4, r3)
            r3 = r1
            java.lang.Runnable r3 = (java.lang.Runnable) r3
            r2.a(r3)
            r2.a(r6)
        L60:
            boolean r3 = r0.compareAndSet(r5, r1, r2)
            if (r3 == 0) goto L74
        L66:
            java.lang.Thread r6 = r5.G()
            java.lang.Thread r0 = java.lang.Thread.currentThread()
            if (r0 == r6) goto L73
            java.util.concurrent.locks.LockSupport.unpark(r6)
        L73:
            return
        L74:
            java.lang.Object r3 = r0.get(r5)
            if (r3 == r1) goto L60
            goto L0
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.no.K(java.lang.Runnable):void");
    }

    public final boolean L() {
        z6 z6Var = this.f;
        if (z6Var != null ? z6Var.isEmpty() : true) {
            Object obj = g.get(this);
            if (obj != null) {
                if (obj instanceof l30) {
                    long j = l30.f.get((l30) obj);
                    return ((int) (1073741823 & j)) == ((int) ((j & 1152921503533105152L) >> 30));
                }
                if (obj == rp0.b) {
                }
            }
            return true;
        }
        return false;
    }

    public final long M() {
        Runnable runnable;
        if (!I()) {
            loop0: while (true) {
                AtomicReferenceFieldUpdater atomicReferenceFieldUpdater = g;
                Object obj = atomicReferenceFieldUpdater.get(this);
                runnable = null;
                if (obj == null) {
                    break;
                }
                if (!(obj instanceof l30)) {
                    if (obj != rp0.b) {
                        while (!atomicReferenceFieldUpdater.compareAndSet(this, obj, null)) {
                            if (atomicReferenceFieldUpdater.get(this) != obj) {
                                break;
                            }
                        }
                        runnable = (Runnable) obj;
                        break loop0;
                    }
                    break;
                }
                l30 l30Var = (l30) obj;
                Object objD = l30Var.d();
                if (objD != l30.g) {
                    runnable = (Runnable) objD;
                    break;
                }
                l30 l30VarC = l30Var.c();
                while (!atomicReferenceFieldUpdater.compareAndSet(this, obj, l30VarC) && atomicReferenceFieldUpdater.get(this) == obj) {
                }
            }
            if (runnable != null) {
                runnable.run();
                return 0L;
            }
            z6 z6Var = this.f;
            if (((z6Var == null || z6Var.isEmpty()) ? Long.MAX_VALUE : 0L) != 0) {
                Object obj2 = g.get(this);
                if (obj2 == null) {
                } else if (obj2 instanceof l30) {
                    long j = l30.f.get((l30) obj2);
                    if (((int) (1073741823 & j)) != ((int) ((j & 1152921503533105152L) >> 30))) {
                        return 0L;
                    }
                } else if (obj2 == rp0.b) {
                }
                return Long.MAX_VALUE;
            }
        }
        return 0L;
    }

    @Override // defpackage.qh
    public final void t(nh nhVar, Runnable runnable) {
        K(runnable);
    }
}
