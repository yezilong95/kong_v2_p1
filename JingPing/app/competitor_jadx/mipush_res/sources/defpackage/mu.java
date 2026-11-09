package defpackage;

import java.util.concurrent.atomic.AtomicIntegerFieldUpdater;
import java.util.concurrent.atomic.AtomicReferenceFieldUpdater;

/* JADX INFO: loaded from: classes.dex */
public abstract class mu extends nu implements tn {
    public static final /* synthetic */ AtomicReferenceFieldUpdater J = AtomicReferenceFieldUpdater.newUpdater(mu.class, Object.class, "_queue$volatile");
    public static final /* synthetic */ AtomicReferenceFieldUpdater K = AtomicReferenceFieldUpdater.newUpdater(mu.class, Object.class, "_delayed$volatile");
    public static final /* synthetic */ AtomicIntegerFieldUpdater L = AtomicIntegerFieldUpdater.newUpdater(mu.class, "_isCompleted$volatile");
    private volatile /* synthetic */ Object _delayed$volatile;
    private volatile /* synthetic */ int _isCompleted$volatile = 0;
    private volatile /* synthetic */ Object _queue$volatile;

    @Override // defpackage.nu
    public void C() {
        l01.a.set(null);
        L.set(this, 1);
        loop0: while (true) {
            AtomicReferenceFieldUpdater atomicReferenceFieldUpdater = J;
            Object obj = atomicReferenceFieldUpdater.get(this);
            wy0 wy0Var = qm.e;
            if (obj != null) {
                if (!(obj instanceof ra0)) {
                    if (obj != wy0Var) {
                        ra0 ra0Var = new ra0(8, true);
                        ra0Var.a((Runnable) obj);
                        while (!atomicReferenceFieldUpdater.compareAndSet(this, obj, ra0Var)) {
                            if (atomicReferenceFieldUpdater.get(this) != obj) {
                                break;
                            }
                        }
                        break loop0;
                    }
                    break;
                }
                ((ra0) obj).b();
                break;
            }
            while (!atomicReferenceFieldUpdater.compareAndSet(this, null, wy0Var)) {
                if (atomicReferenceFieldUpdater.get(this) != null) {
                    break;
                }
            }
            break loop0;
        }
        while (F() <= 0) {
        }
        System.nanoTime();
    }

    /* JADX WARN: Code restructure failed: missing block: B:29:0x004a, code lost:
    
        defpackage.vm.M.D(r6);
     */
    /* JADX WARN: Code restructure failed: missing block: B:30:0x004f, code lost:
    
        return;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public void D(java.lang.Runnable r6) {
        /*
            r5 = this;
        L0:
            java.util.concurrent.atomic.AtomicReferenceFieldUpdater r0 = defpackage.mu.J
            java.lang.Object r1 = r0.get(r5)
            java.util.concurrent.atomic.AtomicIntegerFieldUpdater r2 = defpackage.mu.L
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
            boolean r2 = r1 instanceof defpackage.ra0
            r3 = 1
            if (r2 == 0) goto L46
            r2 = r1
            ra0 r2 = (defpackage.ra0) r2
            int r4 = r2.a(r6)
            if (r4 == 0) goto L66
            if (r4 == r3) goto L34
            r0 = 2
            if (r4 == r0) goto L4a
            goto L0
        L34:
            ra0 r2 = r2.c()
        L38:
            boolean r3 = r0.compareAndSet(r5, r1, r2)
            if (r3 == 0) goto L3f
            goto L0
        L3f:
            java.lang.Object r3 = r0.get(r5)
            if (r3 == r1) goto L38
            goto L0
        L46:
            wy0 r2 = defpackage.qm.e
            if (r1 != r2) goto L50
        L4a:
            vm r0 = defpackage.vm.M
            r0.D(r6)
            return
        L50:
            ra0 r2 = new ra0
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
            java.lang.Thread r6 = r5.z()
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
        throw new UnsupportedOperationException("Method not decompiled: defpackage.mu.D(java.lang.Runnable):void");
    }

    public final boolean E() {
        h8 h8Var = this.I;
        if (h8Var != null ? h8Var.isEmpty() : true) {
            Object obj = J.get(this);
            if (obj != null) {
                if (obj instanceof ra0) {
                    long j = ra0.f.get((ra0) obj);
                    return ((int) (1073741823 & j)) == ((int) ((j & 1152921503533105152L) >> 30));
                }
                if (obj == qm.e) {
                }
            }
            return true;
        }
        return false;
    }

    public final long F() {
        Runnable runnable;
        if (!B()) {
            loop0: while (true) {
                AtomicReferenceFieldUpdater atomicReferenceFieldUpdater = J;
                Object obj = atomicReferenceFieldUpdater.get(this);
                runnable = null;
                if (obj == null) {
                    break;
                }
                if (!(obj instanceof ra0)) {
                    if (obj != qm.e) {
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
                ra0 ra0Var = (ra0) obj;
                Object objD = ra0Var.d();
                if (objD != ra0.g) {
                    runnable = (Runnable) objD;
                    break;
                }
                ra0 ra0VarC = ra0Var.c();
                while (!atomicReferenceFieldUpdater.compareAndSet(this, obj, ra0VarC) && atomicReferenceFieldUpdater.get(this) == obj) {
                }
            }
            if (runnable != null) {
                runnable.run();
                return 0L;
            }
            h8 h8Var = this.I;
            if (((h8Var == null || h8Var.isEmpty()) ? Long.MAX_VALUE : 0L) != 0) {
                Object obj2 = J.get(this);
                if (obj2 == null) {
                } else if (obj2 instanceof ra0) {
                    long j = ra0.f.get((ra0) obj2);
                    if (((int) (1073741823 & j)) != ((int) ((j & 1152921503533105152L) >> 30))) {
                        return 0L;
                    }
                } else if (obj2 == qm.e) {
                }
                return Long.MAX_VALUE;
            }
        }
        return 0L;
    }

    @Override // defpackage.ej
    public final void j(bj bjVar, Runnable runnable) {
        D(runnable);
    }
}
