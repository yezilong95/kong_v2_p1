package defpackage;

import java.util.concurrent.atomic.AtomicReferenceFieldUpdater;

/* JADX INFO: loaded from: classes.dex */
public class oa0 {
    public static final /* synthetic */ AtomicReferenceFieldUpdater a = AtomicReferenceFieldUpdater.newUpdater(oa0.class, Object.class, "_next$volatile");
    public static final /* synthetic */ AtomicReferenceFieldUpdater b = AtomicReferenceFieldUpdater.newUpdater(oa0.class, Object.class, "_prev$volatile");
    public static final /* synthetic */ AtomicReferenceFieldUpdater c = AtomicReferenceFieldUpdater.newUpdater(oa0.class, Object.class, "_removedRef$volatile");
    private volatile /* synthetic */ Object _next$volatile = this;
    private volatile /* synthetic */ Object _prev$volatile = this;
    private volatile /* synthetic */ Object _removedRef$volatile;

    /* JADX WARN: Code restructure failed: missing block: B:25:0x003e, code lost:
    
        r6 = ((defpackage.ko0) r6).a;
     */
    /* JADX WARN: Code restructure failed: missing block: B:27:0x0046, code lost:
    
        if (r5.compareAndSet(r4, r3, r6) == false) goto L29;
     */
    /* JADX WARN: Code restructure failed: missing block: B:30:0x004e, code lost:
    
        if (r5.get(r4) == r3) goto L51;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final defpackage.oa0 c() {
        /*
            r9 = this;
        L0:
            java.util.concurrent.atomic.AtomicReferenceFieldUpdater r0 = defpackage.oa0.b
            java.lang.Object r1 = r0.get(r9)
            oa0 r1 = (defpackage.oa0) r1
            r2 = 0
            r3 = r1
        La:
            r4 = r2
        Lb:
            java.util.concurrent.atomic.AtomicReferenceFieldUpdater r5 = defpackage.oa0.a
            java.lang.Object r6 = r5.get(r3)
            if (r6 != r9) goto L24
            if (r1 != r3) goto L16
            goto L2d
        L16:
            boolean r2 = r0.compareAndSet(r9, r1, r3)
            if (r2 == 0) goto L1d
            goto L2d
        L1d:
            java.lang.Object r2 = r0.get(r9)
            if (r2 == r1) goto L16
            goto L0
        L24:
            boolean r7 = r9.g()
            if (r7 == 0) goto L2b
            return r2
        L2b:
            if (r6 != 0) goto L2e
        L2d:
            return r3
        L2e:
            boolean r7 = r6 instanceof defpackage.nj0
            if (r7 == 0) goto L38
            nj0 r6 = (defpackage.nj0) r6
            r6.a(r3)
            goto L0
        L38:
            boolean r7 = r6 instanceof defpackage.ko0
            if (r7 == 0) goto L58
            if (r4 == 0) goto L51
            ko0 r6 = (defpackage.ko0) r6
            oa0 r6 = r6.a
        L42:
            boolean r7 = r5.compareAndSet(r4, r3, r6)
            if (r7 == 0) goto L4a
            r3 = r4
            goto La
        L4a:
            java.lang.Object r7 = r5.get(r4)
            if (r7 == r3) goto L42
            goto L0
        L51:
            java.lang.Object r3 = r0.get(r3)
            oa0 r3 = (defpackage.oa0) r3
            goto Lb
        L58:
            java.lang.String r4 = "null cannot be cast to non-null type kotlinx.coroutines.internal.LockFreeLinkedListNode{ kotlinx.coroutines.internal.LockFreeLinkedListKt.Node }"
            defpackage.t40.l(r6, r4)
            r4 = r6
            oa0 r4 = (defpackage.oa0) r4
            r8 = r4
            r4 = r3
            r3 = r8
            goto Lb
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.oa0.c():oa0");
    }

    public final void d(oa0 oa0Var) {
        while (true) {
            AtomicReferenceFieldUpdater atomicReferenceFieldUpdater = b;
            oa0 oa0Var2 = (oa0) atomicReferenceFieldUpdater.get(oa0Var);
            if (e() != oa0Var) {
                return;
            }
            while (!atomicReferenceFieldUpdater.compareAndSet(oa0Var, oa0Var2, this)) {
                if (atomicReferenceFieldUpdater.get(oa0Var) != oa0Var2) {
                    break;
                }
            }
            if (g()) {
                oa0Var.c();
                return;
            }
            return;
        }
    }

    public final Object e() {
        while (true) {
            Object obj = a.get(this);
            if (!(obj instanceof nj0)) {
                return obj;
            }
            ((nj0) obj).a(this);
        }
    }

    public final oa0 f() {
        oa0 oa0Var;
        Object objE = e();
        ko0 ko0Var = objE instanceof ko0 ? (ko0) objE : null;
        if (ko0Var != null && (oa0Var = ko0Var.a) != null) {
            return oa0Var;
        }
        t40.l(objE, "null cannot be cast to non-null type kotlinx.coroutines.internal.LockFreeLinkedListNode{ kotlinx.coroutines.internal.LockFreeLinkedListKt.Node }");
        return (oa0) objE;
    }

    public boolean g() {
        return e() instanceof ko0;
    }

    public String toString() {
        return new na0(this, qm.class, "classSimpleName", "getClassSimpleName(Ljava/lang/Object;)Ljava/lang/String;", 1) + '@' + qm.C(this);
    }
}
