package defpackage;

import java.lang.reflect.InvocationTargetException;
import java.util.concurrent.CancellationException;
import kotlinx.coroutines.CoroutinesInternalError;

/* JADX INFO: loaded from: classes.dex */
public abstract class zk extends wm0 {
    public int d;

    public zk(int i) {
        super(0L, bn0.g);
        this.d = i;
    }

    public abstract void b(Object obj, CancellationException cancellationException);

    public abstract fh c();

    public Throwable d(Object obj) {
        ue ueVar = obj instanceof ue ? (ue) obj : null;
        if (ueVar != null) {
            return ueVar.a;
        }
        return null;
    }

    public final void f(Throwable th, Throwable th2) throws IllegalAccessException, InvocationTargetException {
        if (th == null && th2 == null) {
            return;
        }
        if (th != null && th2 != null) {
            nv0.c(th, th2);
        }
        if (th == null) {
            th = th2;
        }
        oy.g(th);
        rp0.E(c().getContext(), new CoroutinesInternalError("Fatal exception in coroutines machinery for " + this + ". Please read KDoc to 'handleFatalException' method and report this incident to maintainers", th));
    }

    public abstract Object g();

    /* JADX WARN: Removed duplicated region for block: B:18:0x0048  */
    @Override // java.lang.Runnable
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final void run() throws java.lang.IllegalAccessException, java.lang.reflect.InvocationTargetException {
        /*
            r12 = this;
            bq0 r0 = defpackage.bq0.a
            xm0 r1 = r12.c
            fh r2 = r12.c()     // Catch: java.lang.Throwable -> L9b
            java.lang.String r3 = "null cannot be cast to non-null type kotlinx.coroutines.internal.DispatchedContinuation<T of kotlinx.coroutines.DispatchedTask>"
            defpackage.oy.h(r2, r3)     // Catch: java.lang.Throwable -> L9b
            yk r2 = (defpackage.yk) r2     // Catch: java.lang.Throwable -> L9b
            kotlin.coroutines.jvm.internal.ContinuationImpl r3 = r2.f     // Catch: java.lang.Throwable -> L9b
            java.lang.Object r2 = r2.h     // Catch: java.lang.Throwable -> L9b
            nh r4 = r3.getContext()     // Catch: java.lang.Throwable -> L9b
            java.lang.Object r2 = defpackage.wn0.c(r4, r2)     // Catch: java.lang.Throwable -> L9b
            nm r5 = defpackage.wn0.a     // Catch: java.lang.Throwable -> L9b
            if (r2 == r5) goto L22
            defpackage.xi.T(r3, r4)     // Catch: java.lang.Throwable -> L9b
        L22:
            nh r5 = r3.getContext()     // Catch: java.lang.Throwable -> L46
            java.lang.Object r6 = r12.g()     // Catch: java.lang.Throwable -> L46
            java.lang.Throwable r7 = r12.d(r6)     // Catch: java.lang.Throwable -> L46
            r8 = 0
            if (r7 != 0) goto L48
            int r9 = r12.d     // Catch: java.lang.Throwable -> L46
            r10 = 1
            if (r9 == r10) goto L3b
            r11 = 2
            if (r9 != r11) goto L3a
            goto L3b
        L3a:
            r10 = 0
        L3b:
            if (r10 == 0) goto L48
            qm r9 = defpackage.qm.f     // Catch: java.lang.Throwable -> L46
            lh r5 = r5.get(r9)     // Catch: java.lang.Throwable -> L46
            bz r5 = (defpackage.bz) r5     // Catch: java.lang.Throwable -> L46
            goto L49
        L46:
            r3 = move-exception
            goto L9d
        L48:
            r5 = r8
        L49:
            if (r5 == 0) goto L66
            boolean r9 = r5.a()     // Catch: java.lang.Throwable -> L46
            if (r9 != 0) goto L66
            nz r5 = (defpackage.nz) r5     // Catch: java.lang.Throwable -> L46
            java.util.concurrent.CancellationException r5 = r5.l()     // Catch: java.lang.Throwable -> L46
            r12.b(r6, r5)     // Catch: java.lang.Throwable -> L46
            kotlin.Result$Failure r5 = kotlin.a.a(r5)     // Catch: java.lang.Throwable -> L46
            java.lang.Object r5 = kotlin.Result.m3constructorimpl(r5)     // Catch: java.lang.Throwable -> L46
            r3.resumeWith(r5)     // Catch: java.lang.Throwable -> L46
            goto L7f
        L66:
            if (r7 == 0) goto L74
            kotlin.Result$Failure r5 = kotlin.a.a(r7)     // Catch: java.lang.Throwable -> L46
            java.lang.Object r5 = kotlin.Result.m3constructorimpl(r5)     // Catch: java.lang.Throwable -> L46
            r3.resumeWith(r5)     // Catch: java.lang.Throwable -> L46
            goto L7f
        L74:
            java.lang.Object r5 = r12.e(r6)     // Catch: java.lang.Throwable -> L46
            java.lang.Object r5 = kotlin.Result.m3constructorimpl(r5)     // Catch: java.lang.Throwable -> L46
            r3.resumeWith(r5)     // Catch: java.lang.Throwable -> L46
        L7f:
            defpackage.wn0.a(r4, r2)     // Catch: java.lang.Throwable -> L9b
            r1.getClass()     // Catch: java.lang.Throwable -> L8a
            java.lang.Object r0 = kotlin.Result.m3constructorimpl(r0)     // Catch: java.lang.Throwable -> L8a
            goto L93
        L8a:
            r0 = move-exception
            kotlin.Result$Failure r0 = kotlin.a.a(r0)
            java.lang.Object r0 = kotlin.Result.m3constructorimpl(r0)
        L93:
            java.lang.Throwable r0 = kotlin.Result.m6exceptionOrNullimpl(r0)
            r12.f(r8, r0)
            goto Lb9
        L9b:
            r2 = move-exception
            goto La1
        L9d:
            defpackage.wn0.a(r4, r2)     // Catch: java.lang.Throwable -> L9b
            throw r3     // Catch: java.lang.Throwable -> L9b
        La1:
            r1.getClass()     // Catch: java.lang.Throwable -> La9
            java.lang.Object r0 = kotlin.Result.m3constructorimpl(r0)     // Catch: java.lang.Throwable -> La9
            goto Lb2
        La9:
            r0 = move-exception
            kotlin.Result$Failure r0 = kotlin.a.a(r0)
            java.lang.Object r0 = kotlin.Result.m3constructorimpl(r0)
        Lb2:
            java.lang.Throwable r0 = kotlin.Result.m6exceptionOrNullimpl(r0)
            r12.f(r2, r0)
        Lb9:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.zk.run():void");
    }

    public Object e(Object obj) {
        return obj;
    }
}
