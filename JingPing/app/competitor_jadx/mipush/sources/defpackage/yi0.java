package defpackage;

import android.window.OnBackInvokedCallback;
import android.window.OnBackInvokedDispatcher;

/* JADX INFO: loaded from: classes.dex */
public final class yi0 {
    public static final yi0 a = new yi0();

    public final OnBackInvokedCallback a(a10 a10Var) {
        t40.n(a10Var, "onBackInvoked");
        return new u5(2, a10Var);
    }

    public final void b(Object obj, int i, Object obj2) {
        t40.n(obj, "dispatcher");
        t40.n(obj2, "callback");
        ((OnBackInvokedDispatcher) obj).registerOnBackInvokedCallback(i, (OnBackInvokedCallback) obj2);
    }

    public final void c(Object obj, Object obj2) {
        t40.n(obj, "dispatcher");
        t40.n(obj2, "callback");
        ((OnBackInvokedDispatcher) obj).unregisterOnBackInvokedCallback((OnBackInvokedCallback) obj2);
    }
}
