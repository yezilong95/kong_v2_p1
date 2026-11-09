package defpackage;

import android.window.OnBackInvokedCallback;

/* JADX INFO: loaded from: classes.dex */
public final class aj0 {
    public static final aj0 a = new aj0();

    public final OnBackInvokedCallback a(b10 b10Var, b10 b10Var2, a10 a10Var, a10 a10Var2) {
        t40.n(b10Var, "onBackStarted");
        t40.n(b10Var2, "onBackProgressed");
        t40.n(a10Var, "onBackInvoked");
        t40.n(a10Var2, "onBackCancelled");
        return new zi0(b10Var, b10Var2, a10Var, a10Var2);
    }
}
