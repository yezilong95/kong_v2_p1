package defpackage;

import android.view.View;
import android.window.OnBackInvokedCallback;
import android.window.OnBackInvokedDispatcher;
import io.nats.client.BaseConsumeOptions;
import java.util.Objects;

/* JADX INFO: loaded from: classes.dex */
public class nc0 {
    public OnBackInvokedCallback a;

    public OnBackInvokedCallback a(mc0 mc0Var) {
        Objects.requireNonNull(mc0Var);
        return new u5(1, mc0Var);
    }

    public void b(mc0 mc0Var, View view, boolean z) {
        OnBackInvokedDispatcher onBackInvokedDispatcherFindOnBackInvokedDispatcher;
        if (this.a == null && (onBackInvokedDispatcherFindOnBackInvokedDispatcher = view.findOnBackInvokedDispatcher()) != null) {
            OnBackInvokedCallback onBackInvokedCallbackA = a(mc0Var);
            this.a = onBackInvokedCallbackA;
            onBackInvokedDispatcherFindOnBackInvokedDispatcher.registerOnBackInvokedCallback(z ? BaseConsumeOptions.DEFAULT_MESSAGE_COUNT_WHEN_BYTES : 0, onBackInvokedCallbackA);
        }
    }

    public void c(View view) {
        OnBackInvokedDispatcher onBackInvokedDispatcherFindOnBackInvokedDispatcher = view.findOnBackInvokedDispatcher();
        if (onBackInvokedDispatcherFindOnBackInvokedDispatcher == null) {
            return;
        }
        onBackInvokedDispatcherFindOnBackInvokedDispatcher.unregisterOnBackInvokedCallback(this.a);
        this.a = null;
    }
}
