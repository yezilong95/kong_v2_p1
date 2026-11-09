package kotlinx.coroutines.flow;

import defpackage.bq0;
import defpackage.fh;
import defpackage.qq;
import defpackage.si;
import defpackage.su;
import defpackage.vu;
import kotlin.coroutines.jvm.internal.SuspendLambda;

/* JADX INFO: loaded from: classes.dex */
@si(c = "kotlinx.coroutines.flow.FlowKt__ZipKt$combine$$inlined$combineUnsafe$FlowKt__ZipKt$3$2", f = "Zip.kt", l = {333, 262}, m = "invokeSuspend")
public final class FlowKt__ZipKt$combine$$inlined$combineUnsafe$FlowKt__ZipKt$3$2 extends SuspendLambda implements su {
    public int f;
    public /* synthetic */ qq g;
    public /* synthetic */ Object[] h;
    public final /* synthetic */ vu i;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public FlowKt__ZipKt$combine$$inlined$combineUnsafe$FlowKt__ZipKt$3$2(fh fhVar, vu vuVar) {
        super(3, fhVar);
        this.i = vuVar;
    }

    @Override // defpackage.su
    public final Object invoke(qq qqVar, Object[] objArr, fh fhVar) {
        FlowKt__ZipKt$combine$$inlined$combineUnsafe$FlowKt__ZipKt$3$2 flowKt__ZipKt$combine$$inlined$combineUnsafe$FlowKt__ZipKt$3$2 = new FlowKt__ZipKt$combine$$inlined$combineUnsafe$FlowKt__ZipKt$3$2(fhVar, this.i);
        flowKt__ZipKt$combine$$inlined$combineUnsafe$FlowKt__ZipKt$3$2.g = qqVar;
        flowKt__ZipKt$combine$$inlined$combineUnsafe$FlowKt__ZipKt$3$2.h = objArr;
        return flowKt__ZipKt$combine$$inlined$combineUnsafe$FlowKt__ZipKt$3$2.invokeSuspend(bq0.a);
    }

    /* JADX WARN: Code restructure failed: missing block: B:14:0x004b, code lost:
    
        if (r1.emit(r13, r12) == r0) goto L15;
     */
    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final java.lang.Object invokeSuspend(java.lang.Object r13) throws java.lang.Throwable {
        /*
            r12 = this;
            kotlin.coroutines.intrinsics.CoroutineSingletons r0 = kotlin.coroutines.intrinsics.CoroutineSingletons.COROUTINE_SUSPENDED
            int r1 = r12.f
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
            qq r1 = r12.g
            kotlin.a.b(r13)
            r11 = r12
            goto L42
        L20:
            kotlin.a.b(r13)
            qq r1 = r12.g
            java.lang.Object[] r13 = r12.h
            r4 = 0
            r6 = r13[r4]
            r7 = r13[r3]
            r8 = r13[r2]
            r4 = 3
            r9 = r13[r4]
            r4 = 4
            r10 = r13[r4]
            r12.g = r1
            r12.f = r3
            vu r5 = r12.i
            r11 = r12
            java.lang.Object r13 = r5.invoke(r6, r7, r8, r9, r10, r11)
            if (r13 != r0) goto L42
            goto L4d
        L42:
            r3 = 0
            r11.g = r3
            r11.f = r2
            java.lang.Object r13 = r1.emit(r13, r12)
            if (r13 != r0) goto L4e
        L4d:
            return r0
        L4e:
            bq0 r13 = defpackage.bq0.a
            return r13
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlinx.coroutines.flow.FlowKt__ZipKt$combine$$inlined$combineUnsafe$FlowKt__ZipKt$3$2.invokeSuspend(java.lang.Object):java.lang.Object");
    }
}
