package defpackage;

import java.util.concurrent.CancellationException;
import kotlinx.coroutines.CoroutinesInternalError;

/* JADX INFO: loaded from: classes.dex */
public abstract class wo extends kz0 {
    public int G;

    public wo(int i) {
        super(0L, mz0.g);
        this.G = i;
    }

    public abstract void b(Object obj, CancellationException cancellationException);

    public abstract ui c();

    public Throwable d(Object obj) {
        og ogVar = obj instanceof og ? (og) obj : null;
        if (ogVar != null) {
            return ogVar.a;
        }
        return null;
    }

    public final void f(Throwable th, Throwable th2) {
        if (th == null && th2 == null) {
            return;
        }
        if (th != null && th2 != null) {
            l21.j(th, th2);
        }
        if (th == null) {
            th = th2;
        }
        t40.k(th);
        z91.D(c().getContext(), new CoroutinesInternalError("Fatal exception in coroutines machinery for " + this + ". Please read KDoc to 'handleFatalException' method and report this incident to maintainers", th));
    }

    public abstract Object g();

    /* JADX WARN: Removed duplicated region for block: B:18:0x0048  */
    @Override // java.lang.Runnable
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final void run() {
        /*
            r12 = this;
            y21 r0 = defpackage.y21.a
            iq0 r1 = r12.F
            ui r2 = r12.c()     // Catch: java.lang.Throwable -> L9b
            java.lang.String r3 = "null cannot be cast to non-null type kotlinx.coroutines.internal.DispatchedContinuation<T of kotlinx.coroutines.DispatchedTask>"
            defpackage.t40.l(r2, r3)     // Catch: java.lang.Throwable -> L9b
            vo r2 = (defpackage.vo) r2     // Catch: java.lang.Throwable -> L9b
            kotlin.coroutines.jvm.internal.ContinuationImpl r3 = r2.I     // Catch: java.lang.Throwable -> L9b
            java.lang.Object r2 = r2.K     // Catch: java.lang.Throwable -> L9b
            bj r4 = r3.getContext()     // Catch: java.lang.Throwable -> L9b
            java.lang.Object r2 = defpackage.k01.c(r4, r2)     // Catch: java.lang.Throwable -> L9b
            wy0 r5 = defpackage.k01.a     // Catch: java.lang.Throwable -> L9b
            if (r2 == r5) goto L22
            defpackage.t40.u1(r3, r4)     // Catch: java.lang.Throwable -> L9b
        L22:
            bj r5 = r3.getContext()     // Catch: java.lang.Throwable -> L46
            java.lang.Object r6 = r12.g()     // Catch: java.lang.Throwable -> L46
            java.lang.Throwable r7 = r12.d(r6)     // Catch: java.lang.Throwable -> L46
            r8 = 0
            if (r7 != 0) goto L48
            int r9 = r12.G     // Catch: java.lang.Throwable -> L46
            r10 = 1
            if (r9 == r10) goto L3b
            r11 = 2
            if (r9 != r11) goto L3a
            goto L3b
        L3a:
            r10 = 0
        L3b:
            if (r10 == 0) goto L48
            is r9 = defpackage.is.H     // Catch: java.lang.Throwable -> L46
            zi r5 = r5.get(r9)     // Catch: java.lang.Throwable -> L46
            f50 r5 = (defpackage.f50) r5     // Catch: java.lang.Throwable -> L46
            goto L49
        L46:
            r3 = move-exception
            goto L9d
        L48:
            r5 = r8
        L49:
            if (r5 == 0) goto L66
            boolean r9 = r5.isActive()     // Catch: java.lang.Throwable -> L46
            if (r9 != 0) goto L66
            t50 r5 = (defpackage.t50) r5     // Catch: java.lang.Throwable -> L46
            java.util.concurrent.CancellationException r5 = r5.k()     // Catch: java.lang.Throwable -> L46
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
            defpackage.k01.a(r4, r2)     // Catch: java.lang.Throwable -> L9b
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
            defpackage.k01.a(r4, r2)     // Catch: java.lang.Throwable -> L9b
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
        throw new UnsupportedOperationException("Method not decompiled: defpackage.wo.run():void");
    }

    public Object e(Object obj) {
        return obj;
    }
}
