package kotlinx.coroutines;

import defpackage.bj;
import defpackage.d10;
import defpackage.gu;
import defpackage.hu;
import defpackage.k01;
import defpackage.l21;
import defpackage.nj;
import defpackage.t40;
import defpackage.ui;
import defpackage.y21;
import kotlin.NoWhenBranchMatchedException;
import kotlin.Result;
import kotlin.a;
import kotlin.coroutines.EmptyCoroutineContext;
import kotlin.coroutines.intrinsics.CoroutineSingletons;
import kotlin.coroutines.intrinsics.IntrinsicsKt__IntrinsicsJvmKt$createSimpleCoroutineForSuspendFunction$1;
import kotlin.coroutines.intrinsics.IntrinsicsKt__IntrinsicsJvmKt$createSimpleCoroutineForSuspendFunction$2;
import kotlin.coroutines.jvm.internal.BaseContinuationImpl;

/* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
/* JADX WARN: Unknown enum class pattern. Please report as an issue! */
/* JADX INFO: loaded from: classes.dex */
public final class CoroutineStart {
    public static final CoroutineStart ATOMIC;
    public static final CoroutineStart DEFAULT;
    public static final /* synthetic */ CoroutineStart[] E;
    public static final /* synthetic */ hu F;
    public static final CoroutineStart LAZY;
    public static final CoroutineStart UNDISPATCHED;

    static {
        CoroutineStart coroutineStart = new CoroutineStart("DEFAULT", 0);
        DEFAULT = coroutineStart;
        CoroutineStart coroutineStart2 = new CoroutineStart("LAZY", 1);
        LAZY = coroutineStart2;
        CoroutineStart coroutineStart3 = new CoroutineStart("ATOMIC", 2);
        ATOMIC = coroutineStart3;
        CoroutineStart coroutineStart4 = new CoroutineStart("UNDISPATCHED", 3);
        UNDISPATCHED = coroutineStart4;
        CoroutineStart[] coroutineStartArr = {coroutineStart, coroutineStart2, coroutineStart3, coroutineStart4};
        E = coroutineStartArr;
        F = new hu(coroutineStartArr);
    }

    public static gu getEntries() {
        return F;
    }

    public static /* synthetic */ void isLazy$annotations() {
    }

    public static CoroutineStart valueOf(String str) {
        return (CoroutineStart) Enum.valueOf(CoroutineStart.class, str);
    }

    public static CoroutineStart[] values() {
        return (CoroutineStart[]) E.clone();
    }

    public final <R, T> void invoke(d10 d10Var, R r, ui uiVar) {
        Object objInvoke;
        int i = nj.a[ordinal()];
        y21 y21Var = y21.a;
        if (i == 1) {
            try {
                t40.b1(t40.u0(t40.C(d10Var, r, uiVar)), Result.m3constructorimpl(y21Var));
                return;
            } finally {
                uiVar.resumeWith(Result.m3constructorimpl(a.a(th)));
            }
        }
        if (i == 2) {
            t40.n(d10Var, "<this>");
            t40.n(uiVar, "completion");
            t40.u0(t40.C(d10Var, r, uiVar)).resumeWith(Result.m3constructorimpl(y21Var));
            return;
        }
        if (i != 3) {
            if (i != 4) {
                throw new NoWhenBranchMatchedException();
            }
            return;
        }
        t40.n(uiVar, "completion");
        try {
            bj context = uiVar.getContext();
            Object objC = k01.c(context, null);
            try {
                if (d10Var instanceof BaseContinuationImpl) {
                    l21.m(2, d10Var);
                    objInvoke = d10Var.invoke(r, uiVar);
                } else {
                    t40.n(d10Var, "<this>");
                    bj context2 = uiVar.getContext();
                    Object intrinsicsKt__IntrinsicsJvmKt$createSimpleCoroutineForSuspendFunction$1 = context2 == EmptyCoroutineContext.INSTANCE ? new IntrinsicsKt__IntrinsicsJvmKt$createSimpleCoroutineForSuspendFunction$1(uiVar) : new IntrinsicsKt__IntrinsicsJvmKt$createSimpleCoroutineForSuspendFunction$2(uiVar, context2);
                    l21.m(2, d10Var);
                    objInvoke = d10Var.invoke(r, intrinsicsKt__IntrinsicsJvmKt$createSimpleCoroutineForSuspendFunction$1);
                }
                if (objInvoke != CoroutineSingletons.COROUTINE_SUSPENDED) {
                    uiVar.resumeWith(Result.m3constructorimpl(objInvoke));
                }
            } finally {
                k01.a(context, objC);
            }
        } catch (Throwable th) {
        }
    }

    public final boolean isLazy() {
        return this == LAZY;
    }
}
