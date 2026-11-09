package defpackage;

import kotlin.Result;
import kotlinx.coroutines.CompletionHandlerException;

/* JADX INFO: loaded from: classes.dex */
public abstract class q0 extends t50 implements ui, lj {
    public final bj G;

    /* JADX WARN: Removed duplicated region for block: B:29:0x005e A[ADDED_TO_REGION] */
    /* JADX WARN: Removed duplicated region for block: B:36:0x0061 A[EDGE_INSN: B:36:0x0061->B:31:0x0061 BREAK  A[LOOP:0: B:6:0x0018->B:38:?], SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public q0(defpackage.bj r9, boolean r10) {
        /*
            r8 = this;
            r8.<init>(r10)
            is r10 = defpackage.is.H
            zi r10 = r9.get(r10)
            f50 r10 = (defpackage.f50) r10
            wh0 r0 = defpackage.wh0.a
            java.util.concurrent.atomic.AtomicReferenceFieldUpdater r1 = defpackage.t50.F
            if (r10 != 0) goto L16
            r1.set(r8, r0)
            goto L7e
        L16:
            t50 r10 = (defpackage.t50) r10
        L18:
            java.lang.Object r2 = r10.n()
            boolean r3 = r2 instanceof defpackage.pt
            r4 = 0
            r5 = 1
            r6 = -1
            java.util.concurrent.atomic.AtomicReferenceFieldUpdater r7 = defpackage.t50.E
            if (r3 == 0) goto L42
            r3 = r2
            pt r3 = (defpackage.pt) r3
            boolean r3 = r3.a
            if (r3 == 0) goto L2d
            goto L5c
        L2d:
            pt r3 = defpackage.j31.v
        L2f:
            boolean r4 = r7.compareAndSet(r10, r2, r3)
            if (r4 == 0) goto L3a
            r10.w()
        L38:
            r4 = r5
            goto L5c
        L3a:
            java.lang.Object r4 = r7.get(r10)
            if (r4 == r2) goto L2f
        L40:
            r4 = r6
            goto L5c
        L42:
            boolean r3 = r2 instanceof defpackage.v30
            if (r3 == 0) goto L5c
            r3 = r2
            v30 r3 = (defpackage.v30) r3
            th0 r3 = r3.a
        L4b:
            boolean r4 = r7.compareAndSet(r10, r2, r3)
            if (r4 == 0) goto L55
            r10.w()
            goto L38
        L55:
            java.lang.Object r4 = r7.get(r10)
            if (r4 == r2) goto L4b
            goto L40
        L5c:
            if (r4 == 0) goto L61
            if (r4 == r5) goto L61
            goto L18
        L61:
            zd r2 = new zd
            r2.<init>(r8)
            r3 = 2
            ap r10 = defpackage.j31.y(r10, r2, r3)
            yd r10 = (defpackage.yd) r10
            r1.set(r8, r10)
            java.lang.Object r2 = r8.n()
            boolean r2 = r2 instanceof defpackage.w30
            if (r2 != 0) goto L7e
            r10.dispose()
            r1.set(r8, r0)
        L7e:
            bj r9 = r9.plus(r8)
            r8.G = r9
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.q0.<init>(bj, boolean):void");
    }

    @Override // defpackage.t50
    public final String f() {
        return getClass().getSimpleName().concat(" was cancelled");
    }

    @Override // defpackage.ui
    public final bj getContext() {
        return this.G;
    }

    @Override // defpackage.t50
    public final void p(CompletionHandlerException completionHandlerException) {
        z91.D(this.G, completionHandlerException);
    }

    @Override // defpackage.ui
    public final void resumeWith(Object obj) {
        Throwable thM6exceptionOrNullimpl = Result.m6exceptionOrNullimpl(obj);
        if (thM6exceptionOrNullimpl != null) {
            obj = new og(false, thM6exceptionOrNullimpl);
        }
        Object objS = s(obj);
        if (objS == j31.q) {
            return;
        }
        c(objS);
    }

    @Override // defpackage.t50
    public final void v(Object obj) {
        if (obj instanceof og) {
            og ogVar = (og) obj;
            Throwable th = ogVar.a;
            ogVar.getClass();
            og.b.get(ogVar);
        }
    }
}
