package kotlinx.coroutines;

import defpackage.bq0;
import defpackage.fh;
import defpackage.nh;
import defpackage.oy;
import defpackage.pu;
import defpackage.rp0;
import defpackage.ru;
import defpackage.wn0;
import defpackage.zh;
import kotlin.NoWhenBranchMatchedException;
import kotlin.Result;
import kotlin.a;
import kotlin.coroutines.intrinsics.CoroutineSingletons;

/* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
/* JADX WARN: Unknown enum class pattern. Please report as an issue! */
/* JADX INFO: loaded from: classes.dex */
public final class CoroutineStart {
    public static final CoroutineStart ATOMIC;
    public static final CoroutineStart DEFAULT;
    public static final CoroutineStart LAZY;
    public static final CoroutineStart UNDISPATCHED;
    public static final /* synthetic */ CoroutineStart[] b;

    static {
        CoroutineStart coroutineStart = new CoroutineStart("DEFAULT", 0);
        DEFAULT = coroutineStart;
        CoroutineStart coroutineStart2 = new CoroutineStart("LAZY", 1);
        LAZY = coroutineStart2;
        CoroutineStart coroutineStart3 = new CoroutineStart("ATOMIC", 2);
        ATOMIC = coroutineStart3;
        CoroutineStart coroutineStart4 = new CoroutineStart("UNDISPATCHED", 3);
        UNDISPATCHED = coroutineStart4;
        b = new CoroutineStart[]{coroutineStart, coroutineStart2, coroutineStart3, coroutineStart4};
    }

    public static /* synthetic */ void isLazy$annotations() {
    }

    public static CoroutineStart valueOf(String str) {
        return (CoroutineStart) Enum.valueOf(CoroutineStart.class, str);
    }

    public static CoroutineStart[] values() {
        return (CoroutineStart[]) b.clone();
    }

    public final <T> void invoke(pu puVar, fh fhVar) {
        int i = zh.a[ordinal()];
        bq0 bq0Var = bq0.a;
        if (i == 1) {
            try {
                oy.K(oy.D(oy.q(puVar, fhVar)), Result.m3constructorimpl(bq0Var));
                return;
            } finally {
                fhVar.resumeWith(Result.m3constructorimpl(a.a(th)));
            }
        }
        if (i == 2) {
            oy.j(puVar, "<this>");
            oy.j(fhVar, "completion");
            oy.D(oy.q(puVar, fhVar)).resumeWith(Result.m3constructorimpl(bq0Var));
            return;
        }
        if (i != 3) {
            if (i != 4) {
                throw new NoWhenBranchMatchedException();
            }
            return;
        }
        oy.j(fhVar, "completion");
        try {
            nh context = fhVar.getContext();
            Object objC = wn0.c(context, null);
            try {
                rp0.e(1, puVar);
                Object objInvoke = puVar.invoke(fhVar);
                if (objInvoke != CoroutineSingletons.COROUTINE_SUSPENDED) {
                    fhVar.resumeWith(Result.m3constructorimpl(objInvoke));
                }
            } finally {
                wn0.a(context, objC);
            }
        } catch (Throwable th) {
        }
    }

    public final boolean isLazy() {
        return this == LAZY;
    }

    public final <R, T> void invoke(ru ruVar, R r, fh fhVar) {
        int i = zh.a[ordinal()];
        bq0 bq0Var = bq0.a;
        if (i == 1) {
            try {
                oy.K(oy.D(oy.r(ruVar, r, fhVar)), Result.m3constructorimpl(bq0Var));
                return;
            } finally {
                fhVar.resumeWith(Result.m3constructorimpl(a.a(th)));
            }
        }
        if (i == 2) {
            oy.j(ruVar, "<this>");
            oy.j(fhVar, "completion");
            oy.D(oy.r(ruVar, r, fhVar)).resumeWith(Result.m3constructorimpl(bq0Var));
            return;
        }
        if (i != 3) {
            if (i != 4) {
                throw new NoWhenBranchMatchedException();
            }
            return;
        }
        oy.j(fhVar, "completion");
        try {
            nh context = fhVar.getContext();
            Object objC = wn0.c(context, null);
            try {
                rp0.e(2, ruVar);
                Object objInvoke = ruVar.invoke(r, fhVar);
                if (objInvoke != CoroutineSingletons.COROUTINE_SUSPENDED) {
                    fhVar.resumeWith(Result.m3constructorimpl(objInvoke));
                }
            } finally {
                wn0.a(context, objC);
            }
        } catch (Throwable th) {
        }
    }
}
