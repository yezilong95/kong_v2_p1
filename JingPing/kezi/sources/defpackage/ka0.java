package defpackage;

import android.window.OnBackInvokedCallback;
import android.window.OnBackInvokedDispatcher;

/* JADX INFO: loaded from: classes.dex */
public final class ka0 {
    public static final ka0 a = new ka0();

    public final OnBackInvokedCallback a(ou ouVar) {
        oy.j(ouVar, "onBackInvoked");
        return new p4(2, ouVar);
    }

    public final void b(Object obj, int i, Object obj2) {
        oy.j(obj, "dispatcher");
        oy.j(obj2, "callback");
        ((OnBackInvokedDispatcher) obj).registerOnBackInvokedCallback(i, (OnBackInvokedCallback) obj2);
    }

    public final void c(Object obj, Object obj2) {
        oy.j(obj, "dispatcher");
        oy.j(obj2, "callback");
        ((OnBackInvokedDispatcher) obj).unregisterOnBackInvokedCallback((OnBackInvokedCallback) obj2);
    }
}
