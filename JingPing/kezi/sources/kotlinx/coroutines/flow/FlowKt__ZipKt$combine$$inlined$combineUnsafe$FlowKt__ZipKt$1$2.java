package kotlinx.coroutines.flow;

import defpackage.bq0;
import defpackage.fh;
import defpackage.qq;
import defpackage.si;
import defpackage.su;
import defpackage.tu;
import kotlin.coroutines.jvm.internal.SuspendLambda;

/* JADX INFO: loaded from: classes.dex */
@si(c = "kotlinx.coroutines.flow.FlowKt__ZipKt$combine$$inlined$combineUnsafe$FlowKt__ZipKt$1$2", f = "Zip.kt", l = {333, 262}, m = "invokeSuspend")
public final class FlowKt__ZipKt$combine$$inlined$combineUnsafe$FlowKt__ZipKt$1$2 extends SuspendLambda implements su {
    public int f;
    public /* synthetic */ qq g;
    public /* synthetic */ Object[] h;
    public final /* synthetic */ tu i;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public FlowKt__ZipKt$combine$$inlined$combineUnsafe$FlowKt__ZipKt$1$2(fh fhVar, tu tuVar) {
        super(3, fhVar);
        this.i = tuVar;
    }

    @Override // defpackage.su
    public final Object invoke(qq qqVar, Object[] objArr, fh fhVar) {
        FlowKt__ZipKt$combine$$inlined$combineUnsafe$FlowKt__ZipKt$1$2 flowKt__ZipKt$combine$$inlined$combineUnsafe$FlowKt__ZipKt$1$2 = new FlowKt__ZipKt$combine$$inlined$combineUnsafe$FlowKt__ZipKt$1$2(fhVar, this.i);
        flowKt__ZipKt$combine$$inlined$combineUnsafe$FlowKt__ZipKt$1$2.g = qqVar;
        flowKt__ZipKt$combine$$inlined$combineUnsafe$FlowKt__ZipKt$1$2.h = objArr;
        return flowKt__ZipKt$combine$$inlined$combineUnsafe$FlowKt__ZipKt$1$2.invokeSuspend(bq0.a);
    }

    /* JADX WARN: Code restructure failed: missing block: B:14:0x0042, code lost:
    
        if (r1.emit(r7, r6) == r0) goto L15;
     */
    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final java.lang.Object invokeSuspend(java.lang.Object r7) throws java.lang.Throwable {
        /*
            r6 = this;
            kotlin.coroutines.intrinsics.CoroutineSingletons r0 = kotlin.coroutines.intrinsics.CoroutineSingletons.COROUTINE_SUSPENDED
            int r1 = r6.f
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
            qq r1 = r6.g
            kotlin.a.b(r7)
            goto L39
        L1e:
            kotlin.a.b(r7)
            qq r1 = r6.g
            java.lang.Object[] r7 = r6.h
            r4 = 0
            r4 = r7[r4]
            r5 = r7[r3]
            r7 = r7[r2]
            r6.g = r1
            r6.f = r3
            tu r3 = r6.i
            java.lang.Object r7 = r3.invoke(r4, r5, r7, r6)
            if (r7 != r0) goto L39
            goto L44
        L39:
            r3 = 0
            r6.g = r3
            r6.f = r2
            java.lang.Object r7 = r1.emit(r7, r6)
            if (r7 != r0) goto L45
        L44:
            return r0
        L45:
            bq0 r7 = defpackage.bq0.a
            return r7
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlinx.coroutines.flow.FlowKt__ZipKt$combine$$inlined$combineUnsafe$FlowKt__ZipKt$1$2.invokeSuspend(java.lang.Object):java.lang.Object");
    }
}
