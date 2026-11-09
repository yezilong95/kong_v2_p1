package defpackage;

import android.app.Activity;
import android.window.OnBackInvokedCallback;
import android.window.OnBackInvokedDispatcher;
import io.nats.client.BaseConsumeOptions;
import java.util.Objects;

/* JADX INFO: loaded from: classes.dex */
public abstract class v5 {
    public static OnBackInvokedDispatcher a(Activity activity) {
        return activity.getOnBackInvokedDispatcher();
    }

    public static OnBackInvokedCallback b(Object obj, c6 c6Var) {
        Objects.requireNonNull(c6Var);
        u5 u5Var = new u5(0, c6Var);
        d2.f(obj).registerOnBackInvokedCallback(BaseConsumeOptions.DEFAULT_MESSAGE_COUNT_WHEN_BYTES, u5Var);
        return u5Var;
    }

    public static void c(Object obj, Object obj2) {
        d2.f(obj).unregisterOnBackInvokedCallback(d2.b(obj2));
    }
}
