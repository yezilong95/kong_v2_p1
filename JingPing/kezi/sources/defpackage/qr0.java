package defpackage;

import android.view.View;
import androidx.core.R$id;
import java.util.Objects;

/* JADX INFO: loaded from: classes.dex */
public abstract class qr0 {
    public static void a(View view, vr0 vr0Var) {
        fj0 fj0Var = (fj0) view.getTag(R$id.tag_unhandled_key_listeners);
        if (fj0Var == null) {
            fj0Var = new fj0();
            view.setTag(R$id.tag_unhandled_key_listeners, fj0Var);
        }
        Objects.requireNonNull(vr0Var);
        View.OnUnhandledKeyEventListener pr0Var = new pr0();
        fj0Var.put(vr0Var, pr0Var);
        view.addOnUnhandledKeyEventListener(pr0Var);
    }

    public static CharSequence b(View view) {
        return view.getAccessibilityPaneTitle();
    }

    public static boolean c(View view) {
        return view.isAccessibilityHeading();
    }

    public static boolean d(View view) {
        return view.isScreenReaderFocusable();
    }

    public static void e(View view, vr0 vr0Var) {
        View.OnUnhandledKeyEventListener onUnhandledKeyEventListener;
        fj0 fj0Var = (fj0) view.getTag(R$id.tag_unhandled_key_listeners);
        if (fj0Var == null || (onUnhandledKeyEventListener = (View.OnUnhandledKeyEventListener) fj0Var.getOrDefault(vr0Var, null)) == null) {
            return;
        }
        view.removeOnUnhandledKeyEventListener(onUnhandledKeyEventListener);
    }

    public static <T> T f(View view, int i) {
        return (T) view.requireViewById(i);
    }

    public static void g(View view, boolean z) {
        view.setAccessibilityHeading(z);
    }

    public static void h(View view, CharSequence charSequence) {
        view.setAccessibilityPaneTitle(charSequence);
    }

    public static void i(View view, q7 q7Var) {
        view.setAutofillId(null);
    }

    public static void j(View view, boolean z) {
        view.setScreenReaderFocusable(z);
    }
}
