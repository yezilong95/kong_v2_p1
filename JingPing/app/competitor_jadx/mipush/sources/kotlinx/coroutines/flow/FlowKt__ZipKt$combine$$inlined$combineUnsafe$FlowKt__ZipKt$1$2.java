package kotlinx.coroutines.flow;

import defpackage.dx;
import defpackage.e10;
import defpackage.f10;
import defpackage.mm;
import defpackage.ui;
import defpackage.y21;
import kotlin.coroutines.jvm.internal.SuspendLambda;

/* JADX INFO: loaded from: classes.dex */
@mm(c = "kotlinx.coroutines.flow.FlowKt__ZipKt$combine$$inlined$combineUnsafe$FlowKt__ZipKt$1$2", f = "Zip.kt", l = {329, 258}, m = "invokeSuspend")
public final class FlowKt__ZipKt$combine$$inlined$combineUnsafe$FlowKt__ZipKt$1$2 extends SuspendLambda implements e10 {
    public int I;
    public /* synthetic */ dx J;
    public /* synthetic */ Object[] K;
    public final /* synthetic */ f10 L;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public FlowKt__ZipKt$combine$$inlined$combineUnsafe$FlowKt__ZipKt$1$2(ui uiVar, f10 f10Var) {
        super(3, uiVar);
        this.L = f10Var;
    }

    @Override // defpackage.e10
    public final Object invoke(dx dxVar, Object[] objArr, ui uiVar) {
        FlowKt__ZipKt$combine$$inlined$combineUnsafe$FlowKt__ZipKt$1$2 flowKt__ZipKt$combine$$inlined$combineUnsafe$FlowKt__ZipKt$1$2 = new FlowKt__ZipKt$combine$$inlined$combineUnsafe$FlowKt__ZipKt$1$2(uiVar, this.L);
        flowKt__ZipKt$combine$$inlined$combineUnsafe$FlowKt__ZipKt$1$2.J = dxVar;
        flowKt__ZipKt$combine$$inlined$combineUnsafe$FlowKt__ZipKt$1$2.K = objArr;
        return flowKt__ZipKt$combine$$inlined$combineUnsafe$FlowKt__ZipKt$1$2.invokeSuspend(y21.a);
    }

    /* JADX WARN: Code restructure failed: missing block: B:14:0x0042, code lost:
    
        if (r1.emit(r7, r6) == r0) goto L15;
     */
    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object invokeSuspend(java.lang.Object r7) {
        /*
            r6 = this;
            kotlin.coroutines.intrinsics.CoroutineSingletons r0 = kotlin.coroutines.intrinsics.CoroutineSingletons.COROUTINE_SUSPENDED
            int r1 = r6.I
            r2 = 2
            r3 = 1
            if (r1 == 0) goto L1e
            if (r1 == r3) goto L18
            if (r1 != r2) goto L10
            kotlin.a.b(r7)
            goto L45
        L10:
            java.lang.IllegalStateException r7 = new java.lang.IllegalStateException
            java.lang.String r0 = "call to 'resume' before 'invoke' with coroutine"
            r7.<init>(r0)
            throw r7
        L18:
            dx r1 = r6.J
            kotlin.a.b(r7)
            goto L39
        L1e:
            kotlin.a.b(r7)
            dx r1 = r6.J
            java.lang.Object[] r7 = r6.K
            r4 = 0
            r4 = r7[r4]
            r5 = r7[r3]
            r7 = r7[r2]
            r6.J = r1
            r6.I = r3
            f10 r3 = r6.L
            java.lang.Object r7 = r3.invoke(r4, r5, r7, r6)
            if (r7 != r0) goto L39
            goto L44
        L39:
            r3 = 0
            r6.J = r3
            r6.I = r2
            java.lang.Object r7 = r1.emit(r7, r6)
            if (r7 != r0) goto L45
        L44:
            return r0
        L45:
            y21 r7 = defpackage.y21.a
            return r7
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlinx.coroutines.flow.FlowKt__ZipKt$combine$$inlined$combineUnsafe$FlowKt__ZipKt$1$2.invokeSuspend(java.lang.Object):java.lang.Object");
    }
}
