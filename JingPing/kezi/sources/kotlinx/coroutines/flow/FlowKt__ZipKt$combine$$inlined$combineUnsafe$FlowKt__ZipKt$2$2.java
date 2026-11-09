package kotlinx.coroutines.flow;

import defpackage.bq0;
import defpackage.fh;
import defpackage.qq;
import defpackage.si;
import defpackage.su;
import defpackage.uu;
import kotlin.coroutines.jvm.internal.SuspendLambda;

/* JADX INFO: loaded from: classes.dex */
@si(c = "kotlinx.coroutines.flow.FlowKt__ZipKt$combine$$inlined$combineUnsafe$FlowKt__ZipKt$2$2", f = "Zip.kt", l = {333, 262}, m = "invokeSuspend")
public final class FlowKt__ZipKt$combine$$inlined$combineUnsafe$FlowKt__ZipKt$2$2 extends SuspendLambda implements su {
    public int f;
    public /* synthetic */ qq g;
    public /* synthetic */ Object[] h;
    public final /* synthetic */ uu i;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public FlowKt__ZipKt$combine$$inlined$combineUnsafe$FlowKt__ZipKt$2$2(fh fhVar, uu uuVar) {
        super(3, fhVar);
        this.i = uuVar;
    }

    @Override // defpackage.su
    public final Object invoke(qq qqVar, Object[] objArr, fh fhVar) {
        FlowKt__ZipKt$combine$$inlined$combineUnsafe$FlowKt__ZipKt$2$2 flowKt__ZipKt$combine$$inlined$combineUnsafe$FlowKt__ZipKt$2$2 = new FlowKt__ZipKt$combine$$inlined$combineUnsafe$FlowKt__ZipKt$2$2(fhVar, this.i);
        flowKt__ZipKt$combine$$inlined$combineUnsafe$FlowKt__ZipKt$2$2.g = qqVar;
        flowKt__ZipKt$combine$$inlined$combineUnsafe$FlowKt__ZipKt$2$2.h = objArr;
        return flowKt__ZipKt$combine$$inlined$combineUnsafe$FlowKt__ZipKt$2$2.invokeSuspend(bq0.a);
    }

    /* JADX WARN: Code restructure failed: missing block: B:14:0x0048, code lost:
    
        if (r1.emit(r12, r11) == r0) goto L15;
     */
    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final java.lang.Object invokeSuspend(java.lang.Object r12) throws java.lang.Throwable {
        /*
            r11 = this;
            kotlin.coroutines.intrinsics.CoroutineSingletons r0 = kotlin.coroutines.intrinsics.CoroutineSingletons.COROUTINE_SUSPENDED
            int r1 = r11.f
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
            qq r1 = r11.g
            kotlin.a.b(r12)
            r10 = r11
            goto L3f
        L20:
            kotlin.a.b(r12)
            qq r1 = r11.g
            java.lang.Object[] r12 = r11.h
            r4 = 0
            r6 = r12[r4]
            r7 = r12[r3]
            r8 = r12[r2]
            r4 = 3
            r9 = r12[r4]
            r11.g = r1
            r11.f = r3
            uu r5 = r11.i
            r10 = r11
            java.lang.Object r12 = r5.invoke(r6, r7, r8, r9, r10)
            if (r12 != r0) goto L3f
            goto L4a
        L3f:
            r3 = 0
            r10.g = r3
            r10.f = r2
            java.lang.Object r12 = r1.emit(r12, r11)
            if (r12 != r0) goto L4b
        L4a:
            return r0
        L4b:
            bq0 r12 = defpackage.bq0.a
            return r12
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlinx.coroutines.flow.FlowKt__ZipKt$combine$$inlined$combineUnsafe$FlowKt__ZipKt$2$2.invokeSuspend(java.lang.Object):java.lang.Object");
    }
}
