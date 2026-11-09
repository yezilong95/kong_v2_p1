package kotlinx.coroutines.flow;

import defpackage.dx;
import defpackage.e10;
import defpackage.g10;
import defpackage.mm;
import defpackage.ui;
import defpackage.y21;
import kotlin.coroutines.jvm.internal.SuspendLambda;

/* JADX INFO: loaded from: classes.dex */
@mm(c = "kotlinx.coroutines.flow.FlowKt__ZipKt$combine$$inlined$combineUnsafe$FlowKt__ZipKt$2$2", f = "Zip.kt", l = {329, 258}, m = "invokeSuspend")
public final class FlowKt__ZipKt$combine$$inlined$combineUnsafe$FlowKt__ZipKt$2$2 extends SuspendLambda implements e10 {
    public int I;
    public /* synthetic */ dx J;
    public /* synthetic */ Object[] K;
    public final /* synthetic */ g10 L;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public FlowKt__ZipKt$combine$$inlined$combineUnsafe$FlowKt__ZipKt$2$2(ui uiVar, g10 g10Var) {
        super(3, uiVar);
        this.L = g10Var;
    }

    @Override // defpackage.e10
    public final Object invoke(dx dxVar, Object[] objArr, ui uiVar) {
        FlowKt__ZipKt$combine$$inlined$combineUnsafe$FlowKt__ZipKt$2$2 flowKt__ZipKt$combine$$inlined$combineUnsafe$FlowKt__ZipKt$2$2 = new FlowKt__ZipKt$combine$$inlined$combineUnsafe$FlowKt__ZipKt$2$2(uiVar, this.L);
        flowKt__ZipKt$combine$$inlined$combineUnsafe$FlowKt__ZipKt$2$2.J = dxVar;
        flowKt__ZipKt$combine$$inlined$combineUnsafe$FlowKt__ZipKt$2$2.K = objArr;
        return flowKt__ZipKt$combine$$inlined$combineUnsafe$FlowKt__ZipKt$2$2.invokeSuspend(y21.a);
    }

    /* JADX WARN: Code restructure failed: missing block: B:14:0x0048, code lost:
    
        if (r1.emit(r12, r11) == r0) goto L15;
     */
    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object invokeSuspend(java.lang.Object r12) {
        /*
            r11 = this;
            kotlin.coroutines.intrinsics.CoroutineSingletons r0 = kotlin.coroutines.intrinsics.CoroutineSingletons.COROUTINE_SUSPENDED
            int r1 = r11.I
            r2 = 2
            r3 = 1
            if (r1 == 0) goto L20
            if (r1 == r3) goto L19
            if (r1 != r2) goto L11
            kotlin.a.b(r12)
            r10 = r11
            goto L4b
        L11:
            java.lang.IllegalStateException r12 = new java.lang.IllegalStateException
            java.lang.String r0 = "call to 'resume' before 'invoke' with coroutine"
            r12.<init>(r0)
            throw r12
        L19:
            dx r1 = r11.J
            kotlin.a.b(r12)
            r10 = r11
            goto L3f
        L20:
            kotlin.a.b(r12)
            dx r1 = r11.J
            java.lang.Object[] r12 = r11.K
            r4 = 0
            r6 = r12[r4]
            r7 = r12[r3]
            r8 = r12[r2]
            r4 = 3
            r9 = r12[r4]
            r11.J = r1
            r11.I = r3
            g10 r5 = r11.L
            r10 = r11
            java.lang.Object r12 = r5.invoke(r6, r7, r8, r9, r10)
            if (r12 != r0) goto L3f
            goto L4a
        L3f:
            r3 = 0
            r10.J = r3
            r10.I = r2
            java.lang.Object r12 = r1.emit(r12, r11)
            if (r12 != r0) goto L4b
        L4a:
            return r0
        L4b:
            y21 r12 = defpackage.y21.a
            return r12
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlinx.coroutines.flow.FlowKt__ZipKt$combine$$inlined$combineUnsafe$FlowKt__ZipKt$2$2.invokeSuspend(java.lang.Object):java.lang.Object");
    }
}
