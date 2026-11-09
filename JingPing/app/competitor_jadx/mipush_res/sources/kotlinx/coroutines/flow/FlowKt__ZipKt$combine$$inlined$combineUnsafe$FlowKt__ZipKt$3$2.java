package kotlinx.coroutines.flow;

import defpackage.dx;
import defpackage.e10;
import defpackage.h10;
import defpackage.mm;
import defpackage.ui;
import defpackage.y21;
import kotlin.coroutines.jvm.internal.SuspendLambda;

/* JADX INFO: loaded from: classes.dex */
@mm(c = "kotlinx.coroutines.flow.FlowKt__ZipKt$combine$$inlined$combineUnsafe$FlowKt__ZipKt$3$2", f = "Zip.kt", l = {329, 258}, m = "invokeSuspend")
public final class FlowKt__ZipKt$combine$$inlined$combineUnsafe$FlowKt__ZipKt$3$2 extends SuspendLambda implements e10 {
    public int I;
    public /* synthetic */ dx J;
    public /* synthetic */ Object[] K;
    public final /* synthetic */ h10 L;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public FlowKt__ZipKt$combine$$inlined$combineUnsafe$FlowKt__ZipKt$3$2(ui uiVar, h10 h10Var) {
        super(3, uiVar);
        this.L = h10Var;
    }

    @Override // defpackage.e10
    public final Object invoke(dx dxVar, Object[] objArr, ui uiVar) {
        FlowKt__ZipKt$combine$$inlined$combineUnsafe$FlowKt__ZipKt$3$2 flowKt__ZipKt$combine$$inlined$combineUnsafe$FlowKt__ZipKt$3$2 = new FlowKt__ZipKt$combine$$inlined$combineUnsafe$FlowKt__ZipKt$3$2(uiVar, this.L);
        flowKt__ZipKt$combine$$inlined$combineUnsafe$FlowKt__ZipKt$3$2.J = dxVar;
        flowKt__ZipKt$combine$$inlined$combineUnsafe$FlowKt__ZipKt$3$2.K = objArr;
        return flowKt__ZipKt$combine$$inlined$combineUnsafe$FlowKt__ZipKt$3$2.invokeSuspend(y21.a);
    }

    /* JADX WARN: Code restructure failed: missing block: B:14:0x004b, code lost:
    
        if (r1.emit(r13, r12) == r0) goto L15;
     */
    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object invokeSuspend(java.lang.Object r13) {
        /*
            r12 = this;
            kotlin.coroutines.intrinsics.CoroutineSingletons r0 = kotlin.coroutines.intrinsics.CoroutineSingletons.COROUTINE_SUSPENDED
            int r1 = r12.I
            r2 = 2
            r3 = 1
            if (r1 == 0) goto L20
            if (r1 == r3) goto L19
            if (r1 != r2) goto L11
            kotlin.a.b(r13)
            r11 = r12
            goto L4e
        L11:
            java.lang.IllegalStateException r13 = new java.lang.IllegalStateException
            java.lang.String r0 = "call to 'resume' before 'invoke' with coroutine"
            r13.<init>(r0)
            throw r13
        L19:
            dx r1 = r12.J
            kotlin.a.b(r13)
            r11 = r12
            goto L42
        L20:
            kotlin.a.b(r13)
            dx r1 = r12.J
            java.lang.Object[] r13 = r12.K
            r4 = 0
            r6 = r13[r4]
            r7 = r13[r3]
            r8 = r13[r2]
            r4 = 3
            r9 = r13[r4]
            r4 = 4
            r10 = r13[r4]
            r12.J = r1
            r12.I = r3
            h10 r5 = r12.L
            r11 = r12
            java.lang.Object r13 = r5.invoke(r6, r7, r8, r9, r10, r11)
            if (r13 != r0) goto L42
            goto L4d
        L42:
            r3 = 0
            r11.J = r3
            r11.I = r2
            java.lang.Object r13 = r1.emit(r13, r12)
            if (r13 != r0) goto L4e
        L4d:
            return r0
        L4e:
            y21 r13 = defpackage.y21.a
            return r13
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlinx.coroutines.flow.FlowKt__ZipKt$combine$$inlined$combineUnsafe$FlowKt__ZipKt$3$2.invokeSuspend(java.lang.Object):java.lang.Object");
    }
}
