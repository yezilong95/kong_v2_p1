package defpackage;

import java.util.concurrent.atomic.AtomicReferenceFieldUpdater;

/* JADX INFO: loaded from: classes.dex */
public class i30 {
    public static final AtomicReferenceFieldUpdater b = AtomicReferenceFieldUpdater.newUpdater(i30.class, Object.class, "_next");
    public static final AtomicReferenceFieldUpdater c = AtomicReferenceFieldUpdater.newUpdater(i30.class, Object.class, "_prev");
    public static final AtomicReferenceFieldUpdater d = AtomicReferenceFieldUpdater.newUpdater(i30.class, Object.class, "_removedRef");
    private volatile Object _next = this;
    private volatile Object _prev = this;
    private volatile Object _removedRef;

    /* JADX WARN: Code restructure failed: missing block: B:25:0x003e, code lost:
    
        r6 = ((defpackage.ke0) r6).a;
     */
    /* JADX WARN: Code restructure failed: missing block: B:27:0x0046, code lost:
    
        if (r5.compareAndSet(r4, r3, r6) == false) goto L29;
     */
    /* JADX WARN: Code restructure failed: missing block: B:30:0x004e, code lost:
    
        if (r5.get(r4) == r3) goto L51;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final defpackage.i30 d() {
        /*
            r9 = this;
        L0:
            java.util.concurrent.atomic.AtomicReferenceFieldUpdater r0 = defpackage.i30.c
            java.lang.Object r1 = r0.get(r9)
            i30 r1 = (defpackage.i30) r1
            r2 = 0
            r3 = r1
        La:
            r4 = r2
        Lb:
            java.util.concurrent.atomic.AtomicReferenceFieldUpdater r5 = defpackage.i30.b
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
            boolean r7 = r9.h()
            if (r7 == 0) goto L2b
            return r2
        L2b:
            if (r6 != 0) goto L2e
        L2d:
            return r3
        L2e:
            boolean r7 = r6 instanceof defpackage.ya0
            if (r7 == 0) goto L38
            ya0 r6 = (defpackage.ya0) r6
            r6.a(r3)
            goto L0
        L38:
            boolean r7 = r6 instanceof defpackage.ke0
            if (r7 == 0) goto L58
            if (r4 == 0) goto L51
            ke0 r6 = (defpackage.ke0) r6
            i30 r6 = r6.a
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
            i30 r3 = (defpackage.i30) r3
            goto Lb
        L58:
            java.lang.String r4 = "null cannot be cast to non-null type kotlinx.coroutines.internal.LockFreeLinkedListNode{ kotlinx.coroutines.internal.LockFreeLinkedListKt.Node }"
            defpackage.oy.h(r6, r4)
            r4 = r6
            i30 r4 = (defpackage.i30) r4
            r8 = r4
            r4 = r3
            r3 = r8
            goto Lb
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.i30.d():i30");
    }

    public final void e(i30 i30Var) {
        while (true) {
            AtomicReferenceFieldUpdater atomicReferenceFieldUpdater = c;
            i30 i30Var2 = (i30) atomicReferenceFieldUpdater.get(i30Var);
            if (f() != i30Var) {
                return;
            }
            while (!atomicReferenceFieldUpdater.compareAndSet(i30Var, i30Var2, this)) {
                if (atomicReferenceFieldUpdater.get(i30Var) != i30Var2) {
                    break;
                }
            }
            if (h()) {
                i30Var.d();
                return;
            }
            return;
        }
    }

    public final Object f() {
        while (true) {
            Object obj = b.get(this);
            if (!(obj instanceof ya0)) {
                return obj;
            }
            ((ya0) obj).a(this);
        }
    }

    public final i30 g() {
        i30 i30Var;
        Object objF = f();
        ke0 ke0Var = objF instanceof ke0 ? (ke0) objF : null;
        if (ke0Var != null && (i30Var = ke0Var.a) != null) {
            return i30Var;
        }
        oy.h(objF, "null cannot be cast to non-null type kotlinx.coroutines.internal.LockFreeLinkedListNode{ kotlinx.coroutines.internal.LockFreeLinkedListKt.Node }");
        return (i30) objF;
    }

    public boolean h() {
        return f() instanceof ke0;
    }

    public String toString() {
        return new h30(this, xi.class, "classSimpleName", "getClassSimpleName(Ljava/lang/Object;)Ljava/lang/String;", 1) + '@' + xi.y(this);
    }
}
