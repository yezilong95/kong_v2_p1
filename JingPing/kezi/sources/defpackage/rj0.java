package defpackage;

import android.os.Build;
import android.view.View;
import android.view.WindowInsets;
import android.view.WindowInsetsController;
import android.view.inputmethod.InputMethodManager;
import java.util.concurrent.atomic.AtomicBoolean;

/* JADX INFO: loaded from: classes.dex */
public final class rj0 extends gv {
    public View i;
    public WindowInsetsController j;

    @Override // defpackage.gv
    public final void c() {
        WindowInsetsController windowInsetsController = this.j;
        View view = this.i;
        if (windowInsetsController == null) {
            windowInsetsController = view != null ? view.getWindowInsetsController() : null;
        }
        if (windowInsetsController == null) {
            super.c();
            return;
        }
        AtomicBoolean atomicBoolean = new AtomicBoolean(false);
        qj0 qj0Var = new qj0(0, atomicBoolean);
        windowInsetsController.addOnControllableInsetsChangedListener(qj0Var);
        if (!atomicBoolean.get() && view != null) {
            ((InputMethodManager) view.getContext().getSystemService("input_method")).hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
        windowInsetsController.removeOnControllableInsetsChangedListener(qj0Var);
        windowInsetsController.hide(WindowInsets.Type.ime());
    }

    @Override // defpackage.gv
    public final void d() {
        View view = this.i;
        if (view != null && Build.VERSION.SDK_INT < 33) {
            ((InputMethodManager) view.getContext().getSystemService("input_method")).isActive();
        }
        WindowInsetsController windowInsetsController = this.j;
        if (windowInsetsController == null) {
            windowInsetsController = view != null ? view.getWindowInsetsController() : null;
        }
        if (windowInsetsController != null) {
            windowInsetsController.show(WindowInsets.Type.ime());
        }
        super.d();
    }
}
