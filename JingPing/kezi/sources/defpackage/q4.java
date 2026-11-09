package defpackage;

import android.app.Activity;
import android.window.OnBackInvokedCallback;
import android.window.OnBackInvokedDispatcher;
import java.util.Objects;

/* JADX INFO: loaded from: classes.dex */
public abstract class q4 {
    public static OnBackInvokedDispatcher a(Activity activity) {
        return activity.getOnBackInvokedDispatcher();
    }

    public static OnBackInvokedCallback b(Object obj, x4 x4Var) {
        Objects.requireNonNull(x4Var);
        p4 p4Var = new p4(0, x4Var);
        i0.f(obj).registerOnBackInvokedCallback(1000000, p4Var);
        return p4Var;
    }

    public static void c(Object obj, Object obj2) {
        i0.f(obj).unregisterOnBackInvokedCallback(i0.b(obj2));
    }
}
