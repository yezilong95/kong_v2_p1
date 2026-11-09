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
public class s9 implements rb {
    public static final AtomicLongFieldUpdater c = AtomicLongFieldUpdater.newUpdater(s9.class, "sendersAndCloseStatus");
    public static final AtomicLongFieldUpdater d = AtomicLongFieldUpdater.newUpdater(s9.class, "receivers");
    public static final AtomicLongFieldUpdater e = AtomicLongFieldUpdater.newUpdater(s9.class, "bufferEnd");
    public static final AtomicLongFieldUpdater f = AtomicLongFieldUpdater.newUpdater(s9.class, "completedExpandBuffersAndPauseFlag");
    public static final AtomicReferenceFieldUpdater g = AtomicReferenceFieldUpdater.newUpdater(s9.class, Object.class, "sendSegment");
    public static final AtomicReferenceFieldUpdater h = AtomicReferenceFieldUpdater.newUpdater(s9.class, Object.class, "receiveSegment");
    public static final AtomicReferenceFieldUpdater i = AtomicReferenceFieldUpdater.newUpdater(s9.class, Object.class, "bufferEndSegment");
    public static final AtomicReferenceFieldUpdater j = AtomicReferenceFieldUpdater.newUpdater(s9.class, Object.class, "_closeCause");
    public static final AtomicReferenceFieldUpdater k = AtomicReferenceFieldUpdater.newUpdater(s9.class, Object.class, "closeHandler");
    private volatile Object _closeCause;
    public final int b;
    private volatile long bufferEnd;
    private volatile Object bufferEndSegment;
    private volatile Object closeHandler;
    private volatile long completedExpandBuffersAndPauseFlag;
    private volatile Object receiveSegment;
    private volatile long receivers;
    private volatile Object sendSegment;
    private volatile long sendersAndCloseStatus;

    public s9(int i2) {
        this.b = i2;
        if (i2 < 0) {
            throw new IllegalArgumentException(hk.h(i2, "Invalid channel capacity: ", ", should be >=0").toString());
        }
        vb vbVar = u9.a;
        this.bufferEnd = i2 != 0 ? i2 != Integer.MAX_VALUE ? i2 : Long.MAX_VALUE : 0L;
        this.completedExpandBuffersAndPauseFlag = e.get(this);
        vb vbVar2 = new vb(0L, null, this, 3);
        this.sendSegment = vbVar2;
        this.receiveSegment = vbVar2;
        if (q()) {
            vbVar2 = u9.a;
            oy.h(vbVar2, "null cannot be cast to non-null type kotlinx.coroutines.channels.ChannelSegment<E of kotlinx.coroutines.channels.BufferedChannel>");
        }
        this.bufferEndSegment = vbVar2;
        this._closeCause = u9.r;
    }

    public static final vb b(s9 s9Var, long j2, vb vbVar) {
        Object objN;
        s9 s9Var2;
        s9Var.getClass();
        vb vbVar2 = u9.a;
        t9 t9Var = t9.j;
        loop0: while (true) {
            objN = nv0.n(vbVar, j2, t9Var);
            if (!oy.E(objN)) {
                uh0 uh0VarB = oy.B(objN);
                while (true) {
                    AtomicReferenceFieldUpdater atomicReferenceFieldUpdater = g;
                    uh0 uh0Var = (uh0) atomicReferenceFieldUpdater.get(s9Var);
                    if (uh0Var.c >= uh0VarB.c) {
                        break loop0;
                    }
                    if (!uh0VarB.i()) {
                        break;
                    }
                    while (!atomicReferenceFieldUpdater.compareAndSet(s9Var, uh0Var, uh0VarB)) {
                        if (atomicReferenceFieldUpdater.get(s9Var) != uh0Var) {
                            if (uh0VarB.e()) {
                                uh0VarB.d();
                            }
                        }
                    }
                    if (uh0Var.e()) {
                        uh0Var.d();
                    }
                }
            } else {
                break;
            }
        }
        boolean zE = oy.E(objN);
        AtomicLongFieldUpdater atomicLongFieldUpdater = d;
        if (zE) {
            s9Var.g();
            if (vbVar.c * ((long) u9.b) < atomicLongFieldUpdater.get(s9Var)) {
                vbVar.a();
                return null;
            }
        } else {
            vb vbVar3 = (vb) oy.B(objN);
            long j3 = vbVar3.c;
            if (j3 <= j2) {
                return vbVar3;
            }
            long j4 = ((long) u9.b) * j3;
            while (true) {
                AtomicLongFieldUpdater atomicLongFieldUpdater2 = c;
                long j5 = atomicLongFieldUpdater2.get(s9Var);
                long j6 = 1152921504606846975L & j5;
                if (j6 >= j4) {
                    s9Var2 = s9Var;
                    break;
                }
                s9Var2 = s9Var;
                if (atomicLongFieldUpdater2.compareAndSet(s9Var2, j5, j6 + (((long) ((int) (j5 >> 60))) << 60))) {
                    break;
                }
                s9Var = s9Var2;
            }
            if (j3 * ((long) u9.b) < atomicLongFieldUpdater.get(s9Var2)) {
                vbVar3.a();
            }
        }
        return null;
    }

    public static final void c(s9 s9Var, wx wxVar, ma maVar) {
        s9Var.getClass();
        maVar.resumeWith(Result.m3constructorimpl(a.a(s9Var.l())));
    }

    public static final int d(s9 s9Var, vb vbVar, int i2, wx wxVar, long j2, Object obj, boolean z) {
        s9Var.getClass();
        vbVar.m(i2, wxVar);
        if (z) {
            return s9Var.z(vbVar, i2, wxVar, j2, obj, z);
        }
        Object objK = vbVar.k(i2);
        if (objK == null) {
            if (s9Var.e(j2)) {
                if (vbVar.j(null, i2, u9.d)) {
                    return 1;
                }
            } else {
                if (obj == null) {
                    return 3;
                }
                if (vbVar.j(null, i2, obj)) {
                    return 2;
                }
            }
        } else if (objK instanceof rt0) {
            vbVar.m(i2, null);
            if (s9Var.w(objK, wxVar)) {
                vbVar.n(i2, u9.i);
                return 0;
            }
            nm nmVar = u9.k;
            if (vbVar.f.getAndSet((i2 * 2) + 1, nmVar) == nmVar) {
                return 5;
            }
            vbVar.l(i2, true);
            return 5;
        }
        return s9Var.z(vbVar, i2, wxVar, j2, obj, z);
    }

    public static void n(s9 s9Var) {
        s9Var.getClass();
        AtomicLongFieldUpdater atomicLongFieldUpdater = f;
        if ((atomicLongFieldUpdater.addAndGet(s9Var, 1L) & 4611686018427387904L) != 0) {
            while ((atomicLongFieldUpdater.get(s9Var) & 4611686018427387904L) != 0) {
            }
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:8:0x0014  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static java.lang.Object t(defpackage.s9 r13, kotlin.coroutines.jvm.internal.ContinuationImpl r14) throws java.lang.Throwable {
        /*
            boolean r0 = r14 instanceof defpackage.q9
            if (r0 == 0) goto L14
            r0 = r14
            q9 r0 = (defpackage.q9) r0
            int r1 = r0.g
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r1 & r2
            if (r3 == 0) goto L14
            int r1 = r1 - r2
            r0.g = r1
        L12:
            r6 = r0
            goto L1a
        L14:
            q9 r0 = new q9
            r0.<init>(r13, r14)
            goto L12
        L1a:
            java.lang.Object r14 = r6.e
            kotlin.coroutines.intrinsics.CoroutineSingletons r0 = kotlin.coroutines.intrinsics.CoroutineSingletons.COROUTINE_SUSPENDED
            int r1 = r6.g
            r2 = 1
            if (r1 == 0) goto L35
            if (r1 != r2) goto L2d
            kotlin.a.b(r14)
            ub r14 = (defpackage.ub) r14
            java.lang.Object r13 = r14.a
            return r13
        L2d:
            java.lang.IllegalStateException r13 = new java.lang.IllegalStateException
            java.lang.String r14 = "call to 'resume' before 'invoke' with coroutine"
            r13.<init>(r14)
            throw r13
        L35:
            kotlin.a.b(r14)
            java.util.concurrent.atomic.AtomicReferenceFieldUpdater r14 = defpackage.s9.h
            java.lang.Object r14 = r14.get(r13)
            vb r14 = (defpackage.vb) r14
        L40:
            java.util.concurrent.atomic.AtomicLongFieldUpdater r1 = defpackage.s9.c
            long r3 = r1.get(r13)
            boolean r1 = r13.o(r3, r2)
            if (r1 == 0) goto L56
            java.lang.Throwable r13 = r13.k()
            sb r14 = new sb
            r14.<init>(r13)
            return r14
        L56:
            java.util.concurrent.atomic.AtomicLongFieldUpdater r1 = defpackage.s9.d
            long r4 = r1.getAndIncrement(r13)
            int r1 = defpackage.u9.b
            long r7 = (long) r1
            long r9 = r4 / r7
            long r7 = r4 % r7
            int r3 = (int) r7
            long r7 = r14.c
            int r1 = (r7 > r9 ? 1 : (r7 == r9 ? 0 : -1))
            if (r1 == 0) goto L73
            vb r1 = r13.j(r9, r14)
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
            nm r14 = defpackage.u9.m
            if (r13 == r14) goto La6
            nm r14 = defpackage.u9.o
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
            nm r14 = defpackage.u9.n
            if (r13 != r14) goto La2
            r6.g = r2
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
        throw new UnsupportedOperationException("Method not decompiled: defpackage.s9.t(s9, kotlin.coroutines.jvm.internal.ContinuationImpl):java.lang.Object");
    }

    public static boolean x(Object obj) {
        if (obj instanceof la) {
            oy.h(obj, "null cannot be cast to non-null type kotlinx.coroutines.CancellableContinuation<kotlin.Unit>");
            return u9.a((la) obj, bq0.a, null);
        }
        throw new IllegalStateException(("Unexpected waiter: " + obj).toString());
    }

    public final void A(long j2) {
        AtomicLongFieldUpdater atomicLongFieldUpdater;
        s9 s9Var = this;
        if (s9Var.q()) {
            return;
        }
        while (true) {
            atomicLongFieldUpdater = e;
            if (atomicLongFieldUpdater.get(s9Var) > j2) {
                break;
            } else {
                s9Var = this;
            }
        }
        int i2 = u9.c;
        int i3 = 0;
        while (true) {
            AtomicLongFieldUpdater atomicLongFieldUpdater2 = f;
            if (i3 < i2) {
                long j3 = atomicLongFieldUpdater.get(s9Var);
                if (j3 == (4611686018427387903L & atomicLongFieldUpdater2.get(s9Var)) && j3 == atomicLongFieldUpdater.get(s9Var)) {
                    return;
                } else {
                    i3++;
                }
            } else {
                while (true) {
                    long j4 = atomicLongFieldUpdater2.get(s9Var);
                    if (atomicLongFieldUpdater2.compareAndSet(s9Var, j4, (j4 & 4611686018427387903L) + 4611686018427387904L)) {
                        break;
                    } else {
                        s9Var = this;
                    }
                }
                while (true) {
                    long j5 = atomicLongFieldUpdater.get(s9Var);
                    long j6 = atomicLongFieldUpdater2.get(s9Var);
                    long j7 = j6 & 4611686018427387903L;
                    boolean z = (j6 & 4611686018427387904L) != 0;
                    if (j5 == j7 && j5 == atomicLongFieldUpdater.get(s9Var)) {
                        break;
                    }
                    if (!z) {
                        atomicLongFieldUpdater2.compareAndSet(this, j6, 4611686018427387904L + j7);
                    }
                    s9Var = this;
                }
                while (true) {
                    long j8 = atomicLongFieldUpdater2.get(s9Var);
                    if (atomicLongFieldUpdater2.compareAndSet(s9Var, j8, j8 & 4611686018427387903L)) {
                        return;
                    } else {
                        s9Var = this;
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
    @Override // defpackage.wh0
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public java.lang.Object a(defpackage.wx r24, defpackage.ke r25) throws java.lang.Throwable {
        /*
            Method dump skipped, instruction units count: 412
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.s9.a(wx, ke):java.lang.Object");
    }

    public final boolean e(long j2) {
        return j2 < e.get(this) || j2 < d.get(this) + ((long) this.b);
    }

    /* JADX WARN: Code restructure failed: missing block: B:37:0x008f, code lost:
    
        r1 = (defpackage.vb) ((defpackage.Cif) defpackage.Cif.b.get(r1));
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final defpackage.vb f(long r13) {
        /*
            Method dump skipped, instruction units count: 308
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.s9.f(long):vb");
    }

    public final void g() {
        o(c.get(this), false);
    }

    public final void h(long j2) {
        vb vbVar = (vb) h.get(this);
        while (true) {
            AtomicLongFieldUpdater atomicLongFieldUpdater = d;
            long j3 = atomicLongFieldUpdater.get(this);
            if (j2 < Math.max(((long) this.b) + j3, e.get(this))) {
                return;
            }
            if (atomicLongFieldUpdater.compareAndSet(this, j3, 1 + j3)) {
                long j4 = u9.b;
                long j5 = j3 / j4;
                int i2 = (int) (j3 % j4);
                if (vbVar.c != j5) {
                    vb vbVarJ = j(j5, vbVar);
                    if (vbVarJ != null) {
                        vbVar = vbVarJ;
                    }
                }
                vb vbVar2 = vbVar;
                if (y(vbVar2, i2, null, j3) != u9.o || j3 < m()) {
                    vbVar2.a();
                }
                vbVar = vbVar2;
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
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final void i() {
        /*
            Method dump skipped, instruction units count: 402
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.s9.i():void");
    }

    public final vb j(long j2, vb vbVar) {
        Object objN;
        AtomicLongFieldUpdater atomicLongFieldUpdater;
        long j3;
        vb vbVar2 = u9.a;
        t9 t9Var = t9.j;
        loop0: while (true) {
            objN = nv0.n(vbVar, j2, t9Var);
            if (!oy.E(objN)) {
                uh0 uh0VarB = oy.B(objN);
                while (true) {
                    AtomicReferenceFieldUpdater atomicReferenceFieldUpdater = h;
                    uh0 uh0Var = (uh0) atomicReferenceFieldUpdater.get(this);
                    if (uh0Var.c >= uh0VarB.c) {
                        break loop0;
                    }
                    if (!uh0VarB.i()) {
                        break;
                    }
                    while (!atomicReferenceFieldUpdater.compareAndSet(this, uh0Var, uh0VarB)) {
                        if (atomicReferenceFieldUpdater.get(this) != uh0Var) {
                            if (uh0VarB.e()) {
                                uh0VarB.d();
                            }
                        }
                    }
                    if (uh0Var.e()) {
                        uh0Var.d();
                    }
                }
            } else {
                break;
            }
        }
        if (oy.E(objN)) {
            g();
            if (vbVar.c * ((long) u9.b) < m()) {
                vbVar.a();
                return null;
            }
        } else {
            vb vbVar3 = (vb) oy.B(objN);
            boolean zQ = q();
            long j4 = vbVar3.c;
            if (!zQ && j2 <= e.get(this) / ((long) u9.b)) {
                while (true) {
                    AtomicReferenceFieldUpdater atomicReferenceFieldUpdater2 = i;
                    uh0 uh0Var2 = (uh0) atomicReferenceFieldUpdater2.get(this);
                    if (uh0Var2.c >= j4 || !vbVar3.i()) {
                        break;
                    }
                    while (!atomicReferenceFieldUpdater2.compareAndSet(this, uh0Var2, vbVar3)) {
                        if (atomicReferenceFieldUpdater2.get(this) != uh0Var2) {
                            if (vbVar3.e()) {
                                vbVar3.d();
                            }
                        }
                    }
                    if (uh0Var2.e()) {
                        uh0Var2.d();
                    }
                }
            }
            if (j4 <= j2) {
                return vbVar3;
            }
            long j5 = j4 * ((long) u9.b);
            do {
                atomicLongFieldUpdater = d;
                j3 = atomicLongFieldUpdater.get(this);
                if (j3 >= j5) {
                    break;
                }
            } while (!atomicLongFieldUpdater.compareAndSet(this, j3, j5));
            if (j4 * ((long) u9.b) < m()) {
                vbVar3.a();
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
    
        r0 = (defpackage.vb) ((defpackage.Cif) defpackage.Cif.b.get(r0));
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final boolean o(long r15, boolean r17) {
        /*
            Method dump skipped, instruction units count: 368
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.s9.o(long, boolean):boolean");
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
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final void r(long r5, defpackage.vb r7) {
        /*
            r4 = this;
        L0:
            long r0 = r7.c
            int r0 = (r0 > r5 ? 1 : (r0 == r5 ? 0 : -1))
            if (r0 >= 0) goto L11
            if r0 = r7.b()
            vb r0 = (defpackage.vb) r0
            if (r0 != 0) goto Lf
            goto L11
        Lf:
            r7 = r0
            goto L0
        L11:
            boolean r5 = r7.c()
            if (r5 == 0) goto L22
            if r5 = r7.b()
            vb r5 = (defpackage.vb) r5
            if (r5 != 0) goto L20
            goto L22
        L20:
            r7 = r5
            goto L11
        L22:
            java.util.concurrent.atomic.AtomicReferenceFieldUpdater r5 = defpackage.s9.i
            java.lang.Object r6 = r5.get(r4)
            uh0 r6 = (defpackage.uh0) r6
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
        throw new UnsupportedOperationException("Method not decompiled: defpackage.s9.r(long, vb):void");
    }

    public final Object s(wx wxVar, ke keVar) throws Throwable {
        ma maVar = new ma(1, oy.D(keVar));
        dl dlVarN = maVar.n();
        if (dlVarN != null && !(ma.h.get(maVar) instanceof r90)) {
            dlVarN.dispose();
            ma.i.set(maVar, q90.b);
        }
        maVar.resumeWith(Result.m3constructorimpl(a.a(l())));
        Object objM = maVar.m();
        return objM == CoroutineSingletons.COROUTINE_SUSPENDED ? objM : bq0.a;
    }

    /* JADX WARN: Code restructure failed: missing block: B:80:0x01b2, code lost:
    
        r16 = r7;
        r3 = (defpackage.vb) r3.b();
     */
    /* JADX WARN: Code restructure failed: missing block: B:81:0x01bb, code lost:
    
        if (r3 != null) goto L91;
     */
    /* JADX WARN: Multi-variable type inference failed */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final java.lang.String toString() {
        /*
            Method dump skipped, instruction units count: 505
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.s9.toString():java.lang.String");
    }

    /* JADX WARN: Removed duplicated region for block: B:7:0x0015  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final java.lang.Object u(defpackage.vb r16, int r17, long r18, kotlin.coroutines.jvm.internal.ContinuationImpl r20) throws java.lang.Throwable {
        /*
            Method dump skipped, instruction units count: 269
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.s9.u(vb, int, long, kotlin.coroutines.jvm.internal.ContinuationImpl):java.lang.Object");
    }

    public final void v(rt0 rt0Var, boolean z) {
        Throwable thL;
        if (!(rt0Var instanceof la)) {
            if (rt0Var instanceof cd0) {
                ((cd0) rt0Var).b.resumeWith(Result.m3constructorimpl(new ub(new sb(k()))));
                return;
            } else {
                throw new IllegalStateException(("Unexpected waiter: " + rt0Var).toString());
            }
        }
        fh fhVar = (fh) rt0Var;
        if (z) {
            thL = k();
            if (thL == null) {
                thL = new ClosedReceiveChannelException("Channel was closed");
            }
        } else {
            thL = l();
        }
        fhVar.resumeWith(Result.m3constructorimpl(a.a(thL)));
    }

    public final boolean w(Object obj, wx wxVar) {
        if (obj instanceof cd0) {
            oy.h(obj, "null cannot be cast to non-null type kotlinx.coroutines.channels.ReceiveCatching<E of kotlinx.coroutines.channels.BufferedChannel>");
            return u9.a(((cd0) obj).b, new ub(wxVar), null);
        }
        if (obj instanceof la) {
            oy.h(obj, "null cannot be cast to non-null type kotlinx.coroutines.CancellableContinuation<E of kotlinx.coroutines.channels.BufferedChannel>");
            return u9.a((la) obj, wxVar, null);
        }
        throw new IllegalStateException(("Unexpected receiver type: " + obj).toString());
    }

    public final Object y(vb vbVar, int i2, Object obj, long j2) {
        Object objK = vbVar.k(i2);
        AtomicReferenceArray atomicReferenceArray = vbVar.f;
        AtomicLongFieldUpdater atomicLongFieldUpdater = c;
        if (objK == null) {
            if (j2 >= (atomicLongFieldUpdater.get(this) & 1152921504606846975L)) {
                if (obj == null) {
                    return u9.n;
                }
                if (vbVar.j(objK, i2, obj)) {
                    i();
                    return u9.m;
                }
            }
        } else if (objK == u9.d && vbVar.j(objK, i2, u9.i)) {
            i();
            Object obj2 = atomicReferenceArray.get(i2 * 2);
            vbVar.m(i2, null);
            return obj2;
        }
        while (true) {
            Object objK2 = vbVar.k(i2);
            if (objK2 == null || objK2 == u9.e) {
                if (j2 < (atomicLongFieldUpdater.get(this) & 1152921504606846975L)) {
                    if (vbVar.j(objK2, i2, u9.h)) {
                        i();
                        return u9.o;
                    }
                } else {
                    if (obj == null) {
                        return u9.n;
                    }
                    if (vbVar.j(objK2, i2, obj)) {
                        i();
                        return u9.m;
                    }
                }
            } else if (objK2 != u9.d) {
                nm nmVar = u9.j;
                if (objK2 == nmVar) {
                    return u9.o;
                }
                if (objK2 == u9.h) {
                    return u9.o;
                }
                if (objK2 == u9.l) {
                    i();
                    return u9.o;
                }
                if (objK2 != u9.g && vbVar.j(objK2, i2, u9.f)) {
                    boolean z = objK2 instanceof st0;
                    if (z) {
                        objK2 = ((st0) objK2).a;
                    }
                    if (x(objK2)) {
                        vbVar.n(i2, u9.i);
                        i();
                        Object obj3 = atomicReferenceArray.get(i2 * 2);
                        vbVar.m(i2, null);
                        return obj3;
                    }
                    vbVar.n(i2, nmVar);
                    vbVar.h();
                    if (z) {
                        i();
                    }
                    return u9.o;
                }
            } else if (vbVar.j(objK2, i2, u9.i)) {
                i();
                Object obj4 = atomicReferenceArray.get(i2 * 2);
                vbVar.m(i2, null);
                return obj4;
            }
        }
    }

    public final int z(vb vbVar, int i2, wx wxVar, long j2, Object obj, boolean z) {
        while (true) {
            Object objK = vbVar.k(i2);
            if (objK == null) {
                if (!e(j2) || z) {
                    if (z) {
                        if (vbVar.j(null, i2, u9.j)) {
                            vbVar.h();
                            return 4;
                        }
                    } else {
                        if (obj == null) {
                            return 3;
                        }
                        if (vbVar.j(null, i2, obj)) {
                            return 2;
                        }
                    }
                } else if (vbVar.j(null, i2, u9.d)) {
                    break;
                }
            } else {
                if (objK != u9.e) {
                    nm nmVar = u9.k;
                    if (objK == nmVar) {
                        vbVar.m(i2, null);
                        return 5;
                    }
                    if (objK == u9.h) {
                        vbVar.m(i2, null);
                        return 5;
                    }
                    if (objK == u9.l) {
                        vbVar.m(i2, null);
                        g();
                        return 4;
                    }
                    vbVar.m(i2, null);
                    if (objK instanceof st0) {
                        objK = ((st0) objK).a;
                    }
                    if (w(objK, wxVar)) {
                        vbVar.n(i2, u9.i);
                        return 0;
                    }
                    if (vbVar.f.getAndSet((i2 * 2) + 1, nmVar) != nmVar) {
                        vbVar.l(i2, true);
                    }
                    return 5;
                }
                if (vbVar.j(objK, i2, u9.d)) {
                    break;
                }
            }
        }
        return 1;
    }
}
