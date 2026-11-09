package defpackage;

import android.os.Build;
import android.view.View;
import android.view.WindowInsets;
import android.view.WindowInsetsController;
import android.view.inputmethod.InputMethodManager;
import java.util.concurrent.atomic.AtomicBoolean;

/* JADX INFO: loaded from: classes.dex */
public final class gw0 extends wl0 {
    public View F;
    public WindowInsetsController G;

    @Override // defpackage.wl0
    public final void a() {
        WindowInsetsController windowInsetsController = this.G;
        View view = this.F;
        if (windowInsetsController == null) {
            windowInsetsController = view != null ? view.getWindowInsetsController() : null;
        }
        if (windowInsetsController == null) {
            super.a();
            return;
        }
        AtomicBoolean atomicBoolean = new AtomicBoolean(false);
        fw0 fw0Var = new fw0(0, atomicBoolean);
        windowInsetsController.addOnControllableInsetsChangedListener(fw0Var);
        if (!atomicBoolean.get() && view != null) {
            ((InputMethodManager) view.getContext().getSystemService("input_method")).hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
        windowInsetsController.removeOnControllableInsetsChangedListener(fw0Var);
        windowInsetsController.hide(WindowInsets.Type.ime());
    }

    @Override // defpackage.wl0
    public final void g() {
        View view = this.F;
        if (view != null && Build.VERSION.SDK_INT < 33) {
            ((InputMethodManager) view.getContext().getSystemService("input_method")).isActive();
        }
        WindowInsetsController windowInsetsController = this.G;
        if (windowInsetsController == null) {
            windowInsetsController = view != null ? view.getWindowInsetsController() : null;
        }
        if (windowInsetsController != null) {
            windowInsetsController.show(WindowInsets.Type.ime());
        }
        super.g();
    }
}
