package defpackage;

import java.lang.reflect.InvocationTargetException;
import kotlin.Result;
import kotlinx.coroutines.CompletionHandlerException;

/* JADX INFO: loaded from: classes.dex */
public abstract class g extends nz implements fh, xh {
    public final nh d;

    /* JADX WARN: Removed duplicated region for block: B:29:0x005e A[ADDED_TO_REGION] */
    /* JADX WARN: Removed duplicated region for block: B:36:0x0061 A[EDGE_INSN: B:36:0x0061->B:31:0x0061 BREAK  A[LOOP:0: B:6:0x0018->B:38:?], SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public g(defpackage.nh r9, boolean r10) {
        /*
            r8 = this;
            r8.<init>(r10)
            qm r10 = defpackage.qm.f
            lh r10 = r9.get(r10)
            bz r10 = (defpackage.bz) r10
            q90 r0 = defpackage.q90.b
            java.util.concurrent.atomic.AtomicReferenceFieldUpdater r1 = defpackage.nz.c
            if (r10 != 0) goto L16
            r1.set(r8, r0)
            goto L7e
        L16:
            nz r10 = (defpackage.nz) r10
        L18:
            java.lang.Object r2 = r10.o()
            boolean r3 = r2 instanceof defpackage.xn
            r4 = 0
            r5 = 1
            r6 = -1
            java.util.concurrent.atomic.AtomicReferenceFieldUpdater r7 = defpackage.nz.b
            if (r3 == 0) goto L42
            r3 = r2
            xn r3 = (defpackage.xn) r3
            boolean r3 = r3.b
            if (r3 == 0) goto L2d
            goto L5c
        L2d:
            xn r3 = defpackage.nv0.i
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
            boolean r3 = r2 instanceof defpackage.rx
            if (r3 == 0) goto L5c
            r3 = r2
            rx r3 = (defpackage.rx) r3
            p90 r3 = r3.b
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
            ec r2 = new ec
            r2.<init>(r8)
            r3 = 2
            dl r10 = defpackage.rp0.F(r10, r2, r3)
            dc r10 = (defpackage.dc) r10
            r1.set(r8, r10)
            java.lang.Object r2 = r8.o()
            boolean r2 = r2 instanceof defpackage.sx
            if (r2 != 0) goto L7e
            r10.dispose()
            r1.set(r8, r0)
        L7e:
            nh r9 = r9.plus(r8)
            r8.d = r9
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.g.<init>(nh, boolean):void");
    }

    @Override // defpackage.nz
    public final String g() {
        return getClass().getSimpleName().concat(" was cancelled");
    }

    @Override // defpackage.fh
    public final nh getContext() {
        return this.d;
    }

    @Override // defpackage.nz
    public final void q(CompletionHandlerException completionHandlerException) throws IllegalAccessException, InvocationTargetException {
        rp0.E(this.d, completionHandlerException);
    }

    @Override // defpackage.fh
    public final void resumeWith(Object obj) throws IllegalAccessException, InvocationTargetException {
        Throwable thM6exceptionOrNullimpl = Result.m6exceptionOrNullimpl(obj);
        if (thM6exceptionOrNullimpl != null) {
            obj = new ue(false, thM6exceptionOrNullimpl);
        }
        Object objS = s(obj);
        if (objS == nv0.d) {
            return;
        }
        d(objS);
    }

    @Override // defpackage.nz
    public final void v(Object obj) {
        if (obj instanceof ue) {
            ue ueVar = (ue) obj;
            Throwable th = ueVar.a;
            ueVar.getClass();
            ue.b.get(ueVar);
        }
    }
}
