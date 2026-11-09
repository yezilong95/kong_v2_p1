package defpackage;

import android.view.View;
import androidx.core.R$id;
import java.util.Objects;

/* JADX INFO: loaded from: classes.dex */
public abstract class y41 {
    public static void a(View view, d51 d51Var) {
        vv0 vv0Var = (vv0) view.getTag(R$id.tag_unhandled_key_listeners);
        if (vv0Var == null) {
            vv0Var = new vv0();
            view.setTag(R$id.tag_unhandled_key_listeners, vv0Var);
        }
        Objects.requireNonNull(d51Var);
        View.OnUnhandledKeyEventListener x41Var = new x41();
        vv0Var.put(d51Var, x41Var);
        view.addOnUnhandledKeyEventListener(x41Var);
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

    public static void e(View view, d51 d51Var) {
        View.OnUnhandledKeyEventListener onUnhandledKeyEventListener;
        vv0 vv0Var = (vv0) view.getTag(R$id.tag_unhandled_key_listeners);
        if (vv0Var == null || (onUnhandledKeyEventListener = (View.OnUnhandledKeyEventListener) vv0Var.getOrDefault(d51Var, null)) == null) {
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

    public static void i(View view, y8 y8Var) {
        view.setAutofillId(null);
    }

    public static void j(View view, boolean z) {
        view.setScreenReaderFocusable(z);
    }
}
