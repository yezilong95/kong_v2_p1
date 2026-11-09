package defpackage;

import java.util.concurrent.atomic.AtomicLongFieldUpdater;
import java.util.concurrent.atomic.AtomicReferenceArray;
import java.util.concurrent.atomic.AtomicReferenceFieldUpdater;
import kotlin.Result;
import kotlin.a;
import kotlin.coroutines.intrinsics.CoroutineSingletons;
import kotlinx.coroutines.channels.ClosedReceiveChannelException;
import kotlinx.coroutines.channels.ClosedSendChannelException;

/* JADX INFO: loaded from: classes.dex */
public class nb implements ld {
    public static final /* synthetic */ AtomicLongFieldUpdater c = AtomicLongFieldUpdater.newUpdater(nb.class, "sendersAndCloseStatus$volatile");
    public static final /* synthetic */ AtomicLongFieldUpdater d = AtomicLongFieldUpdater.newUpdater(nb.class, "receivers$volatile");
    public static final /* synthetic */ AtomicLongFieldUpdater e = AtomicLongFieldUpdater.newUpdater(nb.class, "bufferEnd$volatile");
    public static final /* synthetic */ AtomicLongFieldUpdater f = AtomicLongFieldUpdater.newUpdater(nb.class, "completedExpandBuffersAndPauseFlag$volatile");
    public static final /* synthetic */ AtomicReferenceFieldUpdater g = AtomicReferenceFieldUpdater.newUpdater(nb.class, Object.class, "sendSegment$volatile");
    public static final /* synthetic */ AtomicReferenceFieldUpdater h = AtomicReferenceFieldUpdater.newUpdater(nb.class, Object.class, "receiveSegment$volatile");
    public static final /* synthetic */ AtomicReferenceFieldUpdater i = AtomicReferenceFieldUpdater.newUpdater(nb.class, Object.class, "bufferEndSegment$volatile");
    public static final /* synthetic */ AtomicReferenceFieldUpdater j = AtomicReferenceFieldUpdater.newUpdater(nb.class, Object.class, "_closeCause$volatile");
    public static final /* synthetic */ AtomicReferenceFieldUpdater k = AtomicReferenceFieldUpdater.newUpdater(nb.class, Object.class, "closeHandler$volatile");
    private volatile /* synthetic */ Object _closeCause$volatile;
    public final int b;
    private volatile /* synthetic */ long bufferEnd$volatile;
    private volatile /* synthetic */ Object bufferEndSegment$volatile;
    private volatile /* synthetic */ Object closeHandler$volatile;
    private volatile /* synthetic */ long completedExpandBuffersAndPauseFlag$volatile;
    private volatile /* synthetic */ Object receiveSegment$volatile;
    private volatile /* synthetic */ long receivers$volatile;
    private volatile /* synthetic */ Object sendSegment$volatile;
    private volatile /* synthetic */ long sendersAndCloseStatus$volatile;

    public nb(int i2) {
        this.b = i2;
        if (i2 < 0) {
            throw new IllegalArgumentException(zn.k(i2, "Invalid channel capacity: ", ", should be >=0").toString());
        }
        pd pdVar = pb.a;
        this.bufferEnd$volatile = i2 != 0 ? i2 != Integer.MAX_VALUE ? i2 : Long.MAX_VALUE : 0L;
        this.completedExpandBuffersAndPauseFlag$volatile = e.get(this);
        pd pdVar2 = new pd(0L, null, this, 3);
        this.sendSegment$volatile = pdVar2;
        this.receiveSegment$volatile = pdVar2;
        if (q()) {
            pdVar2 = pb.a;
            t40.l(pdVar2, "null cannot be cast to non-null type kotlinx.coroutines.channels.ChannelSegment<E of kotlinx.coroutines.channels.BufferedChannel>");
        }
        this.bufferEndSegment$volatile = pdVar2;
        this._closeCause$volatile = pb.r;
    }

    public static final pd b(nb nbVar, long j2, pd pdVar) {
        Object objF;
        nb nbVar2;
        nbVar.getClass();
        pd pdVar2 = pb.a;
        ob obVar = ob.M;
        loop0: while (true) {
            objF = l21.F(pdVar, j2, obVar);
            if (!o80.I(objF)) {
                ju0 ju0VarB = o80.B(objF);
                while (true) {
                    AtomicReferenceFieldUpdater atomicReferenceFieldUpdater = g;
                    ju0 ju0Var = (ju0) atomicReferenceFieldUpdater.get(nbVar);
                    if (ju0Var.c >= ju0VarB.c) {
                        break loop0;
                    }
                    if (!ju0VarB.i()) {
                        break;
                    }
                    while (!atomicReferenceFieldUpdater.compareAndSet(nbVar, ju0Var, ju0VarB)) {
                        if (atomicReferenceFieldUpdater.get(nbVar) != ju0Var) {
                            if (ju0VarB.e()) {
                                ju0VarB.d();
                            }
                        }
                    }
                    if (ju0Var.e()) {
                        ju0Var.d();
                    }
                }
            } else {
                break;
            }
        }
        boolean zI = o80.I(objF);
        AtomicLongFieldUpdater atomicLongFieldUpdater = d;
        if (zI) {
            nbVar.g();
            if (pdVar.c * ((long) pb.b) < atomicLongFieldUpdater.get(nbVar)) {
                pdVar.a();
                return null;
            }
        } else {
            pd pdVar3 = (pd) o80.B(objF);
            long j3 = pdVar3.c;
            if (j3 <= j2) {
                return pdVar3;
            }
            long j4 = ((long) pb.b) * j3;
            while (true) {
                AtomicLongFieldUpdater atomicLongFieldUpdater2 = c;
                long j5 = atomicLongFieldUpdater2.get(nbVar);
                long j6 = 1152921504606846975L & j5;
                if (j6 >= j4) {
                    nbVar2 = nbVar;
                    break;
                }
                nbVar2 = nbVar;
                if (atomicLongFieldUpdater2.compareAndSet(nbVar2, j5, j6 + (((long) ((int) (j5 >> 60))) << 60))) {
                    break;
                }
                nbVar = nbVar2;
            }
            if (j3 * ((long) pb.b) < atomicLongFieldUpdater.get(nbVar2)) {
                pdVar3.a();
            }
        }
        return null;
    }

    public static final void c(nb nbVar, b40 b40Var, ec ecVar) {
        nbVar.getClass();
        ecVar.resumeWith(Result.m3constructorimpl(a.a(nbVar.l())));
    }

    public static final int d(nb nbVar, pd pdVar, int i2, b40 b40Var, long j2, Object obj, boolean z) {
        nbVar.getClass();
        pdVar.m(i2, b40Var);
        if (z) {
            return nbVar.z(pdVar, i2, b40Var, j2, obj, z);
        }
        Object objK = pdVar.k(i2);
        if (objK == null) {
            if (nbVar.e(j2)) {
                if (pdVar.j(null, i2, pb.d)) {
                    return 1;
                }
            } else {
                if (obj == null) {
                    return 3;
                }
                if (pdVar.j(null, i2, obj)) {
                    return 2;
                }
            }
        } else if (objK instanceof d71) {
            pdVar.m(i2, null);
            if (nbVar.w(objK, b40Var)) {
                pdVar.n(i2, pb.i);
                return 0;
            }
            wy0 wy0Var = pb.k;
            if (pdVar.f.getAndSet((i2 * 2) + 1, wy0Var) == wy0Var) {
                return 5;
            }
            pdVar.l(i2, true);
            return 5;
        }
        return nbVar.z(pdVar, i2, b40Var, j2, obj, z);
    }

    public static void n(nb nbVar) {
        nbVar.getClass();
        AtomicLongFieldUpdater atomicLongFieldUpdater = f;
        if ((atomicLongFieldUpdater.addAndGet(nbVar, 1L) & 4611686018427387904L) != 0) {
            while ((atomicLongFieldUpdater.get(nbVar) & 4611686018427387904L) != 0) {
            }
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:8:0x0014  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static java.lang.Object t(defpackage.nb r13, kotlin.coroutines.jvm.internal.ContinuationImpl r14) throws java.lang.Throwable {
        /*
            boolean r0 = r14 instanceof defpackage.lb
            if (r0 == 0) goto L14
            r0 = r14
            lb r0 = (defpackage.lb) r0
            int r1 = r0.J
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r1 & r2
            if (r3 == 0) goto L14
            int r1 = r1 - r2
            r0.J = r1
        L12:
            r6 = r0
            goto L1a
        L14:
            lb r0 = new lb
            r0.<init>(r13, r14)
            goto L12
        L1a:
            java.lang.Object r14 = r6.H
            kotlin.coroutines.intrinsics.CoroutineSingletons r0 = kotlin.coroutines.intrinsics.CoroutineSingletons.COROUTINE_SUSPENDED
            int r1 = r6.J
            r2 = 1
            if (r1 == 0) goto L35
            if (r1 != r2) goto L2d
            kotlin.a.b(r14)
            od r14 = (defpackage.od) r14
            java.lang.Object r13 = r14.a
            return r13
        L2d:
            java.lang.IllegalStateException r13 = new java.lang.IllegalStateException
            java.lang.String r14 = "call to 'resume' before 'invoke' with coroutine"
            r13.<init>(r14)
            throw r13
        L35:
            kotlin.a.b(r14)
            java.util.concurrent.atomic.AtomicReferenceFieldUpdater r14 = defpackage.nb.h
            java.lang.Object r14 = r14.get(r13)
            pd r14 = (defpackage.pd) r14
        L40:
            java.util.concurrent.atomic.AtomicLongFieldUpdater r1 = defpackage.nb.c
            long r3 = r1.get(r13)
            boolean r1 = r13.o(r3, r2)
            if (r1 == 0) goto L56
            java.lang.Throwable r13 = r13.k()
            md r14 = new md
            r14.<init>(r13)
            return r14
        L56:
            java.util.concurrent.atomic.AtomicLongFieldUpdater r1 = defpackage.nb.d
            long r4 = r1.getAndIncrement(r13)
            int r1 = defpackage.pb.b
            long r7 = (long) r1
            long r9 = r4 / r7
            long r7 = r4 % r7
            int r3 = (int) r7
            long r7 = r14.c
            int r1 = (r7 > r9 ? 1 : (r7 == r9 ? 0 : -1))
            if (r1 == 0) goto L73
            pd r1 = r13.j(r9, r14)
            if (r1 != 0) goto L71
            goto L40
        L71:
            r8 = r1
            goto L74
        L73:
            r8 = r14
        L74:
            r10 = 0
            r7 = r13
            r9 = r3
            r11 = r4
            java.lang.Object r13 = r7.y(r8, r9, r10, r11)
            r1 = r7
            wy0 r14 = defpackage.pb.m
            if (r13 == r14) goto La6
            wy0 r14 = defpackage.pb.o
            if (r13 != r14) goto L93
            long r13 = r1.m()
            int r13 = (r4 > r13 ? 1 : (r4 == r13 ? 0 : -1))
            if (r13 >= 0) goto L90
            r8.a()
        L90:
            r13 = r1
            r14 = r8
            goto L40
        L93:
            wy0 r14 = defpackage.pb.n
            if (r13 != r14) goto La2
            r6.J = r2
            r2 = r8
            java.lang.Object r13 = r1.u(r2, r3, r4, r6)
            if (r13 != r0) goto La1
            return r0
        La1:
            return r13
        La2:
            r8.a()
            return r13
        La6:
            java.lang.IllegalStateException r13 = new java.lang.IllegalStateException
            java.lang.String r14 = "unexpected"
            r13.<init>(r14)
            throw r13
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.nb.t(nb, kotlin.coroutines.jvm.internal.ContinuationImpl):java.lang.Object");
    }

    public static boolean x(Object obj) {
        if (obj instanceof dc) {
            t40.l(obj, "null cannot be cast to non-null type kotlinx.coroutines.CancellableContinuation<kotlin.Unit>");
            return pb.a((dc) obj, y21.a, null);
        }
        throw new IllegalStateException(("Unexpected waiter: " + obj).toString());
    }

    public final void A(long j2) {
        AtomicLongFieldUpdater atomicLongFieldUpdater;
        nb nbVar = this;
        if (nbVar.q()) {
            return;
        }
        while (true) {
            atomicLongFieldUpdater = e;
            if (atomicLongFieldUpdater.get(nbVar) > j2) {
                break;
            } else {
                nbVar = this;
            }
        }
        int i2 = pb.c;
        int i3 = 0;
        while (true) {
            AtomicLongFieldUpdater atomicLongFieldUpdater2 = f;
            if (i3 < i2) {
                long j3 = atomicLongFieldUpdater.get(nbVar);
                if (j3 == (4611686018427387903L & atomicLongFieldUpdater2.get(nbVar)) && j3 == atomicLongFieldUpdater.get(nbVar)) {
                    return;
                } else {
                    i3++;
                }
            } else {
                while (true) {
                    long j4 = atomicLongFieldUpdater2.get(nbVar);
                    if (atomicLongFieldUpdater2.compareAndSet(nbVar, j4, (j4 & 4611686018427387903L) + 4611686018427387904L)) {
                        break;
                    } else {
                        nbVar = this;
                    }
                }
                while (true) {
                    long j5 = atomicLongFieldUpdater.get(nbVar);
                    long j6 = atomicLongFieldUpdater2.get(nbVar);
                    long j7 = j6 & 4611686018427387903L;
                    boolean z = (j6 & 4611686018427387904L) != 0;
                    if (j5 == j7 && j5 == atomicLongFieldUpdater.get(nbVar)) {
                        break;
                    }
                    if (!z) {
                        atomicLongFieldUpdater2.compareAndSet(this, j6, 4611686018427387904L + j7);
                    }
                    nbVar = this;
                }
                while (true) {
                    long j8 = atomicLongFieldUpdater2.get(nbVar);
                    if (atomicLongFieldUpdater2.compareAndSet(nbVar, j8, j8 & 4611686018427387903L)) {
                        return;
                    } else {
                        nbVar = this;
                    }
                }
            }
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:104:0x0196, code lost:
    
        return r10;
     */
    /* JADX WARN: Code restructure failed: missing block: B:45:0x00c6, code lost:
    
        c(r1, r4, r7);
     */
    /* JADX WARN: Removed duplicated region for block: B:94:0x017d  */
    /* JADX WARN: Removed duplicated region for block: B:96:0x0180 A[RETURN] */
    @Override // defpackage.ku0
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public java.lang.Object a(defpackage.b40 r24, defpackage.eg r25) throws java.lang.Throwable {
        /*
            Method dump skipped, instruction units count: 412
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.nb.a(b40, eg):java.lang.Object");
    }

    public final boolean e(long j2) {
        return j2 < e.get(this) || j2 < d.get(this) + ((long) this.b);
    }

    /* JADX WARN: Code restructure failed: missing block: B:37:0x008f, code lost:
    
        r1 = (defpackage.pd) ((defpackage.ch) defpackage.ch.b.get(r1));
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final defpackage.pd f(long r13) {
        /*
            Method dump skipped, instruction units count: 308
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.nb.f(long):pd");
    }

    public final void g() {
        o(c.get(this), false);
    }

    public final void h(long j2) {
        pd pdVar = (pd) h.get(this);
        while (true) {
            AtomicLongFieldUpdater atomicLongFieldUpdater = d;
            long j3 = atomicLongFieldUpdater.get(this);
            if (j2 < Math.max(((long) this.b) + j3, e.get(this))) {
                return;
            }
            if (atomicLongFieldUpdater.compareAndSet(this, j3, 1 + j3)) {
                long j4 = pb.b;
                long j5 = j3 / j4;
                int i2 = (int) (j3 % j4);
                if (pdVar.c != j5) {
                    pd pdVarJ = j(j5, pdVar);
                    if (pdVarJ != null) {
                        pdVar = pdVarJ;
                    }
                }
                pd pdVar2 = pdVar;
                if (y(pdVar2, i2, null, j3) != pb.o || j3 < m()) {
                    pdVar2.a();
                }
                pdVar = pdVar2;
            }
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:102:0x018e, code lost:
    
        n(r15);
     */
    /* JADX WARN: Code restructure failed: missing block: B:103:0x0191, code lost:
    
        return;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final void i() {
        /*
            Method dump skipped, instruction units count: 402
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.nb.i():void");
    }

    public final pd j(long j2, pd pdVar) {
        Object objF;
        AtomicLongFieldUpdater atomicLongFieldUpdater;
        long j3;
        pd pdVar2 = pb.a;
        ob obVar = ob.M;
        loop0: while (true) {
            objF = l21.F(pdVar, j2, obVar);
            if (!o80.I(objF)) {
                ju0 ju0VarB = o80.B(objF);
                while (true) {
                    AtomicReferenceFieldUpdater atomicReferenceFieldUpdater = h;
                    ju0 ju0Var = (ju0) atomicReferenceFieldUpdater.get(this);
                    if (ju0Var.c >= ju0VarB.c) {
                        break loop0;
                    }
                    if (!ju0VarB.i()) {
                        break;
                    }
                    while (!atomicReferenceFieldUpdater.compareAndSet(this, ju0Var, ju0VarB)) {
                        if (atomicReferenceFieldUpdater.get(this) != ju0Var) {
                            if (ju0VarB.e()) {
                                ju0VarB.d();
                            }
                        }
                    }
                    if (ju0Var.e()) {
                        ju0Var.d();
                    }
                }
            } else {
                break;
            }
        }
        if (o80.I(objF)) {
            g();
            if (pdVar.c * ((long) pb.b) < m()) {
                pdVar.a();
                return null;
            }
        } else {
            pd pdVar3 = (pd) o80.B(objF);
            boolean zQ = q();
            long j4 = pdVar3.c;
            if (!zQ && j2 <= e.get(this) / ((long) pb.b)) {
                while (true) {
                    AtomicReferenceFieldUpdater atomicReferenceFieldUpdater2 = i;
                    ju0 ju0Var2 = (ju0) atomicReferenceFieldUpdater2.get(this);
                    if (ju0Var2.c >= j4 || !pdVar3.i()) {
                        break;
                    }
                    while (!atomicReferenceFieldUpdater2.compareAndSet(this, ju0Var2, pdVar3)) {
                        if (atomicReferenceFieldUpdater2.get(this) != ju0Var2) {
                            if (pdVar3.e()) {
                                pdVar3.d();
                            }
                        }
                    }
                    if (ju0Var2.e()) {
                        ju0Var2.d();
                    }
                }
            }
            if (j4 <= j2) {
                return pdVar3;
            }
            long j5 = j4 * ((long) pb.b);
            do {
                atomicLongFieldUpdater = d;
                j3 = atomicLongFieldUpdater.get(this);
                if (j3 >= j5) {
                    break;
                }
            } while (!atomicLongFieldUpdater.compareAndSet(this, j3, j5));
            if (j4 * ((long) pb.b) < m()) {
                pdVar3.a();
            }
        }
        return null;
    }

    public final Throwable k() {
        return (Throwable) j.get(this);
    }

    public final Throwable l() {
        Throwable thK = k();
        return thK == null ? new ClosedSendChannelException("Channel was closed") : thK;
    }

    public final long m() {
        return c.get(this) & 1152921504606846975L;
    }

    /* JADX WARN: Code restructure failed: missing block: B:53:0x00a2, code lost:
    
        r0 = (defpackage.pd) ((defpackage.ch) defpackage.ch.b.get(r0));
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final boolean o(long r15, boolean r17) {
        /*
            Method dump skipped, instruction units count: 368
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.nb.o(long, boolean):boolean");
    }

    public boolean p() {
        return false;
    }

    public final boolean q() {
        long j2 = e.get(this);
        return j2 == 0 || j2 == Long.MAX_VALUE;
    }

    /* JADX WARN: Code restructure failed: missing block: B:39:0x0011, code lost:
    
        continue;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final void r(long r5, defpackage.pd r7) {
        /*
            r4 = this;
        L0:
            long r0 = r7.c
            int r0 = (r0 > r5 ? 1 : (r0 == r5 ? 0 : -1))
            if (r0 >= 0) goto L11
            ch r0 = r7.b()
            pd r0 = (defpackage.pd) r0
            if (r0 != 0) goto Lf
            goto L11
        Lf:
            r7 = r0
            goto L0
        L11:
            boolean r5 = r7.c()
            if (r5 == 0) goto L22
            ch r5 = r7.b()
            pd r5 = (defpackage.pd) r5
            if (r5 != 0) goto L20
            goto L22
        L20:
            r7 = r5
            goto L11
        L22:
            java.util.concurrent.atomic.AtomicReferenceFieldUpdater r5 = defpackage.nb.i
            java.lang.Object r6 = r5.get(r4)
            ju0 r6 = (defpackage.ju0) r6
            long r0 = r6.c
            long r2 = r7.c
            int r0 = (r0 > r2 ? 1 : (r0 == r2 ? 0 : -1))
            if (r0 < 0) goto L33
            goto L49
        L33:
            boolean r0 = r7.i()
            if (r0 != 0) goto L3a
            goto L11
        L3a:
            boolean r0 = r5.compareAndSet(r4, r6, r7)
            if (r0 == 0) goto L4a
            boolean r5 = r6.e()
            if (r5 == 0) goto L49
            r6.d()
        L49:
            return
        L4a:
            java.lang.Object r0 = r5.get(r4)
            if (r0 == r6) goto L3a
            boolean r5 = r7.e()
            if (r5 == 0) goto L22
            r7.d()
            goto L22
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.nb.r(long, pd):void");
    }

    public final Object s(b40 b40Var, eg egVar) throws Throwable {
        ec ecVar = new ec(1, t40.u0(egVar));
        ap apVarN = ecVar.n();
        if (apVarN != null && !(ec.K.get(ecVar) instanceof xh0)) {
            apVarN.dispose();
            ec.L.set(ecVar, wh0.a);
        }
        ecVar.resumeWith(Result.m3constructorimpl(a.a(l())));
        Object objM = ecVar.m();
        return objM == CoroutineSingletons.COROUTINE_SUSPENDED ? objM : y21.a;
    }

    /* JADX WARN: Code restructure failed: missing block: B:80:0x01b7, code lost:
    
        r16 = r7;
        r3 = (defpackage.pd) r3.b();
     */
    /* JADX WARN: Code restructure failed: missing block: B:81:0x01c0, code lost:
    
        if (r3 != null) goto L91;
     */
    /* JADX WARN: Multi-variable type inference failed */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.String toString() {
        /*
            Method dump skipped, instruction units count: 510
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.nb.toString():java.lang.String");
    }

    /* JADX WARN: Removed duplicated region for block: B:7:0x0015  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object u(defpackage.pd r16, int r17, long r18, kotlin.coroutines.jvm.internal.ContinuationImpl r20) throws java.lang.Throwable {
        /*
            Method dump skipped, instruction units count: 269
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.nb.u(pd, int, long, kotlin.coroutines.jvm.internal.ContinuationImpl):java.lang.Object");
    }

    public final void v(d71 d71Var, boolean z) {
        Throwable thL;
        if (!(d71Var instanceof dc)) {
            if (d71Var instanceof tm0) {
                ((tm0) d71Var).E.resumeWith(Result.m3constructorimpl(new od(new md(k()))));
                return;
            } else {
                throw new IllegalStateException(("Unexpected waiter: " + d71Var).toString());
            }
        }
        ui uiVar = (ui) d71Var;
        if (z) {
            thL = k();
            if (thL == null) {
                thL = new ClosedReceiveChannelException("Channel was closed");
            }
        } else {
            thL = l();
        }
        uiVar.resumeWith(Result.m3constructorimpl(a.a(thL)));
    }

    public final boolean w(Object obj, b40 b40Var) {
        if (obj instanceof tm0) {
            t40.l(obj, "null cannot be cast to non-null type kotlinx.coroutines.channels.ReceiveCatching<E of kotlinx.coroutines.channels.BufferedChannel>");
            return pb.a(((tm0) obj).E, new od(b40Var), null);
        }
        if (obj instanceof dc) {
            t40.l(obj, "null cannot be cast to non-null type kotlinx.coroutines.CancellableContinuation<E of kotlinx.coroutines.channels.BufferedChannel>");
            return pb.a((dc) obj, b40Var, null);
        }
        throw new IllegalStateException(("Unexpected receiver type: " + obj).toString());
    }

    public final Object y(pd pdVar, int i2, Object obj, long j2) {
        Object objK = pdVar.k(i2);
        AtomicReferenceArray atomicReferenceArray = pdVar.f;
        AtomicLongFieldUpdater atomicLongFieldUpdater = c;
        if (objK == null) {
            if (j2 >= (atomicLongFieldUpdater.get(this) & 1152921504606846975L)) {
                if (obj == null) {
                    return pb.n;
                }
                if (pdVar.j(objK, i2, obj)) {
                    i();
                    return pb.m;
                }
            }
        } else if (objK == pb.d && pdVar.j(objK, i2, pb.i)) {
            i();
            Object obj2 = atomicReferenceArray.get(i2 * 2);
            pdVar.m(i2, null);
            return obj2;
        }
        while (true) {
            Object objK2 = pdVar.k(i2);
            if (objK2 == null || objK2 == pb.e) {
                if (j2 < (atomicLongFieldUpdater.get(this) & 1152921504606846975L)) {
                    if (pdVar.j(objK2, i2, pb.h)) {
                        i();
                        return pb.o;
                    }
                } else {
                    if (obj == null) {
                        return pb.n;
                    }
                    if (pdVar.j(objK2, i2, obj)) {
                        i();
                        return pb.m;
                    }
                }
            } else if (objK2 != pb.d) {
                wy0 wy0Var = pb.j;
                if (objK2 == wy0Var) {
                    return pb.o;
                }
                if (objK2 == pb.h) {
                    return pb.o;
                }
                if (objK2 == pb.l) {
                    i();
                    return pb.o;
                }
                if (objK2 != pb.g && pdVar.j(objK2, i2, pb.f)) {
                    boolean z = objK2 instanceof e71;
                    if (z) {
                        objK2 = ((e71) objK2).a;
                    }
                    if (x(objK2)) {
                        pdVar.n(i2, pb.i);
                        i();
                        Object obj3 = atomicReferenceArray.get(i2 * 2);
                        pdVar.m(i2, null);
                        return obj3;
                    }
                    pdVar.n(i2, wy0Var);
                    pdVar.h();
                    if (z) {
                        i();
                    }
                    return pb.o;
                }
            } else if (pdVar.j(objK2, i2, pb.i)) {
                i();
                Object obj4 = atomicReferenceArray.get(i2 * 2);
                pdVar.m(i2, null);
                return obj4;
            }
        }
    }

    public final int z(pd pdVar, int i2, b40 b40Var, long j2, Object obj, boolean z) {
        while (true) {
            Object objK = pdVar.k(i2);
            if (objK == null) {
                if (!e(j2) || z) {
                    if (z) {
                        if (pdVar.j(null, i2, pb.j)) {
                            pdVar.h();
                            return 4;
                        }
                    } else {
                        if (obj == null) {
                            return 3;
                        }
                        if (pdVar.j(null, i2, obj)) {
                            return 2;
                        }
                    }
                } else if (pdVar.j(null, i2, pb.d)) {
                    break;
                }
            } else {
                if (objK != pb.e) {
                    wy0 wy0Var = pb.k;
                    if (objK == wy0Var) {
                        pdVar.m(i2, null);
                        return 5;
                    }
                    if (objK == pb.h) {
                        pdVar.m(i2, null);
                        return 5;
                    }
                    if (objK == pb.l) {
                        pdVar.m(i2, null);
                        g();
                        return 4;
                    }
                    pdVar.m(i2, null);
                    if (objK instanceof e71) {
                        objK = ((e71) objK).a;
                    }
                    if (w(objK, b40Var)) {
                        pdVar.n(i2, pb.i);
                        return 0;
                    }
                    if (pdVar.f.getAndSet((i2 * 2) + 1, wy0Var) != wy0Var) {
                        pdVar.l(i2, true);
                    }
                    return 5;
                }
                if (pdVar.j(objK, i2, pb.d)) {
                    break;
                }
            }
        }
        return 1;
    }
}
