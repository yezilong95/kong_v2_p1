package defpackage;

/* JADX INFO: loaded from: classes.dex */
public final class fg implements dx {
    public final /* synthetic */ nb E;
    public final /* synthetic */ int F;

    public fg(nb nbVar, int i) {
        this.E = nbVar;
        this.F = i;
    }

    /* JADX WARN: Removed duplicated region for block: B:7:0x0013  */
    @Override // defpackage.dx
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object emit(java.lang.Object r7, defpackage.ui r8) {
        /*
            r6 = this;
            boolean r0 = r8 instanceof defpackage.eg
            if (r0 == 0) goto L13
            r0 = r8
            eg r0 = (defpackage.eg) r0
            int r1 = r0.J
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r1 & r2
            if (r3 == 0) goto L13
            int r1 = r1 - r2
            r0.J = r1
            goto L18
        L13:
            eg r0 = new eg
            r0.<init>(r6, r8)
        L18:
            java.lang.Object r8 = r0.H
            kotlin.coroutines.intrinsics.CoroutineSingletons r1 = kotlin.coroutines.intrinsics.CoroutineSingletons.COROUTINE_SUSPENDED
            int r2 = r0.J
            y21 r3 = defpackage.y21.a
            r4 = 1
            r5 = 2
            if (r2 == 0) goto L38
            if (r2 == r4) goto L34
            if (r2 != r5) goto L2c
            kotlin.a.b(r8)
            return r3
        L2c:
            java.lang.IllegalStateException r7 = new java.lang.IllegalStateException
            java.lang.String r8 = "call to 'resume' before 'invoke' with coroutine"
            r7.<init>(r8)
            throw r7
        L34:
            kotlin.a.b(r8)
            goto L4d
        L38:
            kotlin.a.b(r8)
            b40 r8 = new b40
            int r2 = r6.F
            r8.<init>(r2, r7)
            r0.J = r4
            nb r7 = r6.E
            java.lang.Object r7 = r7.a(r8, r0)
            if (r7 != r1) goto L4d
            goto La6
        L4d:
            r0.J = r5
            bj r7 = r0.getContext()
            is r8 = defpackage.is.H
            zi r8 = r7.get(r8)
            f50 r8 = (defpackage.f50) r8
            if (r8 == 0) goto L6b
            boolean r2 = r8.isActive()
            if (r2 == 0) goto L64
            goto L6b
        L64:
            t50 r8 = (defpackage.t50) r8
            java.util.concurrent.CancellationException r7 = r8.k()
            throw r7
        L6b:
            ui r8 = defpackage.t40.u0(r0)
            boolean r0 = r8 instanceof defpackage.vo
            r2 = 0
            if (r0 == 0) goto L77
            r2 = r8
            vo r2 = (defpackage.vo) r2
        L77:
            if (r2 != 0) goto L7b
            r7 = r3
            goto L9e
        L7b:
            ej r8 = r2.H
            boolean r0 = r8.w()
            if (r0 == 0) goto L8b
            r2.J = r3
            r2.G = r4
            r8.l(r7, r2)
            goto L9d
        L8b:
            y91 r0 = new y91
            er0 r5 = defpackage.y91.F
            r0.<init>(r5)
            bj r7 = r7.plus(r0)
            r2.J = r3
            r2.G = r4
            r8.l(r7, r2)
        L9d:
            r7 = r1
        L9e:
            kotlin.coroutines.intrinsics.CoroutineSingletons r8 = kotlin.coroutines.intrinsics.CoroutineSingletons.COROUTINE_SUSPENDED
            if (r7 != r8) goto La3
            goto La4
        La3:
            r7 = r3
        La4:
            if (r7 != r1) goto La7
        La6:
            return r1
        La7:
            return r3
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.fg.emit(java.lang.Object, ui):java.lang.Object");
    }
}
