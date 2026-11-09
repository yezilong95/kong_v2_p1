package defpackage;

import android.os.Build;
import android.view.View;
import android.view.Window;
import android.window.OnBackInvokedDispatcher;
import androidx.activity.ComponentActivity;
import androidx.lifecycle.Lifecycle$Event;

/* JADX INFO: loaded from: classes.dex */
public final class ug implements v80 {
    public final /* synthetic */ int E;
    public final /* synthetic */ ComponentActivity F;

    public /* synthetic */ ug(ComponentActivity componentActivity, int i) {
        this.E = i;
        this.F = componentActivity;
    }

    @Override // defpackage.v80
    public final void j(x80 x80Var, Lifecycle$Event lifecycle$Event) {
        switch (this.E) {
            case 0:
                if (lifecycle$Event == Lifecycle$Event.ON_STOP) {
                    Window window = this.F.getWindow();
                    View viewPeekDecorView = window != null ? window.peekDecorView() : null;
                    if (viewPeekDecorView != null) {
                        viewPeekDecorView.cancelPendingInputEvents();
                    }
                }
                break;
            case 1:
                if (lifecycle$Event == Lifecycle$Event.ON_DESTROY) {
                    this.F.F.b = null;
                    if (!this.F.isChangingConfigurations()) {
                        this.F.d().a();
                    }
                    xg xgVar = this.F.L;
                    ComponentActivity componentActivity = xgVar.H;
                    componentActivity.getWindow().getDecorView().removeCallbacks(xgVar);
                    componentActivity.getWindow().getDecorView().getViewTreeObserver().removeOnDrawListener(xgVar);
                }
                break;
            case 2:
                ComponentActivity componentActivity2 = this.F;
                if (componentActivity2.J == null) {
                    wg wgVar = (wg) componentActivity2.getLastNonConfigurationInstance();
                    if (wgVar != null) {
                        componentActivity2.J = wgVar.a;
                    }
                    if (componentActivity2.J == null) {
                        componentActivity2.J = new r51();
                    }
                }
                componentActivity2.H.b(this);
                break;
            default:
                if (lifecycle$Event == Lifecycle$Event.ON_CREATE && Build.VERSION.SDK_INT >= 33) {
                    ej0 ej0Var = this.F.K;
                    OnBackInvokedDispatcher onBackInvokedDispatcherA = vg.a((ComponentActivity) x80Var);
                    ej0Var.getClass();
                    t40.n(onBackInvokedDispatcherA, "invoker");
                    ej0Var.e = onBackInvokedDispatcherA;
                    ej0Var.b(ej0Var.g);
                    break;
                }
                break;
        }
    }
}
