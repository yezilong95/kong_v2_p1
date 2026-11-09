package defpackage;

import java.util.regex.Matcher;
import kotlin.coroutines.jvm.internal.RestrictedSuspendLambda;
import kotlin.text.Regex;

/* JADX INFO: loaded from: classes.dex */
public final class ie0 extends RestrictedSuspendLambda implements ru {
    public Matcher d;
    public int e;
    public int f;
    public /* synthetic */ Object g;
    public final /* synthetic */ Regex h;
    public final /* synthetic */ CharSequence i;
    public final /* synthetic */ int j;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public ie0(Regex regex, CharSequence charSequence, int i, fh fhVar) {
        super(2, fhVar);
        this.h = regex;
        this.i = charSequence;
        this.j = i;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final fh create(Object obj, fh fhVar) {
        ie0 ie0Var = new ie0(this.h, this.i, this.j, fhVar);
        ie0Var.g = obj;
        return ie0Var;
    }

    @Override // defpackage.ru
    public final Object invoke(Object obj, Object obj2) {
        return ((ie0) create((yh0) obj, (fh) obj2)).invokeSuspend(bq0.a);
    }

    /* JADX WARN: Removed duplicated region for block: B:23:0x006f A[PHI: r1 r9 r12
      0x006f: PHI (r1v7 int) = (r1v6 int), (r1v10 int) binds: [B:21:0x006c, B:11:0x0021] A[DONT_GENERATE, DONT_INLINE]
      0x006f: PHI (r9v2 yh0) = (r9v1 yh0), (r9v4 yh0) binds: [B:21:0x006c, B:11:0x0021] A[DONT_GENERATE, DONT_INLINE]
      0x006f: PHI (r12v5 java.util.regex.Matcher) = (r12v4 java.util.regex.Matcher), (r12v10 java.util.regex.Matcher) binds: [B:21:0x006c, B:11:0x0021] A[DONT_GENERATE, DONT_INLINE]] */
    /* JADX WARN: Removed duplicated region for block: B:30:0x009c A[RETURN] */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:21:0x006c -> B:23:0x006f). Please report as a decompilation issue!!! */
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
            bq0 r2 = defpackage.bq0.a
            java.lang.CharSequence r3 = r11.i
            r4 = 3
            int r5 = r11.j
            r6 = 2
            r7 = 1
            if (r1 == 0) goto L32
            if (r1 == r7) goto L2e
            if (r1 == r6) goto L21
            if (r1 != r4) goto L19
            kotlin.a.b(r12)
            return r2
        L19:
            java.lang.IllegalStateException r12 = new java.lang.IllegalStateException
            java.lang.String r0 = "call to 'resume' before 'invoke' with coroutine"
            r12.<init>(r0)
            throw r12
        L21:
            int r1 = r11.e
            java.util.regex.Matcher r8 = r11.d
            java.lang.Object r9 = r11.g
            yh0 r9 = (defpackage.yh0) r9
            kotlin.a.b(r12)
            r12 = r8
            goto L6f
        L2e:
            kotlin.a.b(r12)
            return r2
        L32:
            kotlin.a.b(r12)
            java.lang.Object r12 = r11.g
            yh0 r12 = (defpackage.yh0) r12
            kotlin.text.Regex r1 = r11.h
            java.util.regex.Pattern r1 = kotlin.text.Regex.access$getNativePattern$p(r1)
            java.util.regex.Matcher r1 = r1.matcher(r3)
            if (r5 == r7) goto L9d
            boolean r8 = r1.find()
            if (r8 != 0) goto L4c
            goto L9d
        L4c:
            r8 = 0
            r9 = r12
            r12 = r1
            r1 = r8
        L50:
            int r10 = r12.start()
            java.lang.CharSequence r8 = r3.subSequence(r8, r10)
            java.lang.String r8 = r8.toString()
            r11.g = r9
            r11.d = r12
            r11.e = r1
            r11.f = r6
            r9.c = r8
            r9.b = r4
            r9.d = r11
            kotlin.coroutines.intrinsics.CoroutineSingletons r8 = kotlin.coroutines.intrinsics.CoroutineSingletons.COROUTINE_SUSPENDED
            if (r8 != r0) goto L6f
            goto La9
        L6f:
            int r8 = r12.end()
            int r1 = r1 + r7
            int r10 = r5 + (-1)
            if (r1 == r10) goto L7e
            boolean r10 = r12.find()
            if (r10 != 0) goto L50
        L7e:
            int r12 = r3.length()
            java.lang.CharSequence r12 = r3.subSequence(r8, r12)
            java.lang.String r12 = r12.toString()
            r1 = 0
            r11.g = r1
            r11.d = r1
            r11.f = r4
            r9.c = r12
            r9.b = r4
            r9.d = r11
            kotlin.coroutines.intrinsics.CoroutineSingletons r12 = kotlin.coroutines.intrinsics.CoroutineSingletons.COROUTINE_SUSPENDED
            if (r12 != r0) goto L9c
            goto La9
        L9c:
            return r2
        L9d:
            java.lang.String r1 = r3.toString()
            r11.f = r7
            r12.c = r1
            r12.b = r4
            r12.d = r11
        La9:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.ie0.invokeSuspend(java.lang.Object):java.lang.Object");
    }
}
