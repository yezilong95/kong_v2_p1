package defpackage;

/* JADX INFO: loaded from: classes.dex */
public final class ux implements dx {
    public final /* synthetic */ e10 E;
    public final /* synthetic */ dx F;

    public ux(e10 e10Var, dx dxVar) {
        this.E = e10Var;
        this.F = dxVar;
    }

    /* JADX WARN: Removed duplicated region for block: B:7:0x0013  */
    @Override // defpackage.dx
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object emit(java.lang.Object r5, defpackage.ui r6) {
        /*
            r4 = this;
            boolean r0 = r6 instanceof kotlinx.coroutines.flow.FlowKt__EmittersKt$transform$1$1$emit$1
            if (r0 == 0) goto L13
            r0 = r6
            kotlinx.coroutines.flow.FlowKt__EmittersKt$transform$1$1$emit$1 r0 = (kotlinx.coroutines.flow.FlowKt__EmittersKt$transform$1$1$emit$1) r0
            int r1 = r0.J
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r1 & r2
            if (r3 == 0) goto L13
            int r1 = r1 - r2
            r0.J = r1
            goto L18
        L13:
            kotlinx.coroutines.flow.FlowKt__EmittersKt$transform$1$1$emit$1 r0 = new kotlinx.coroutines.flow.FlowKt__EmittersKt$transform$1$1$emit$1
            r0.<init>(r4, r6)
        L18:
            java.lang.Object r6 = r0.H
            kotlin.coroutines.intrinsics.CoroutineSingletons r1 = kotlin.coroutines.intrinsics.CoroutineSingletons.COROUTINE_SUSPENDED
            int r2 = r0.J
            r3 = 1
            if (r2 == 0) goto L2f
            if (r2 != r3) goto L27
            kotlin.a.b(r6)
            goto L3f
        L27:
            java.lang.IllegalStateException r5 = new java.lang.IllegalStateException
            java.lang.String r6 = "call to 'resume' before 'invoke' with coroutine"
            r5.<init>(r6)
            throw r5
        L2f:
            kotlin.a.b(r6)
            r0.J = r3
            e10 r6 = r4.E
            dx r2 = r4.F
            java.lang.Object r5 = r6.invoke(r2, r5, r0)
            if (r5 != r1) goto L3f
            return r1
        L3f:
            y21 r5 = defpackage.y21.a
            return r5
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.ux.emit(java.lang.Object, ui):java.lang.Object");
    }
}
