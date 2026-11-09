package defpackage;

import android.os.Build;
import android.view.View;
import android.view.Window;
import android.window.OnBackInvokedDispatcher;
import androidx.activity.ComponentActivity;
import androidx.lifecycle.Lifecycle$Event;

/* JADX INFO: loaded from: classes.dex */
public final class af implements s10 {
    public final /* synthetic */ int b;
    public final /* synthetic */ ComponentActivity c;

    public /* synthetic */ af(ComponentActivity componentActivity, int i) {
        this.b = i;
        this.c = componentActivity;
    }

    @Override // defpackage.s10
    public final void a(u10 u10Var, Lifecycle$Event lifecycle$Event) {
        switch (this.b) {
            case 0:
                if (lifecycle$Event == Lifecycle$Event.ON_STOP) {
                    Window window = this.c.getWindow();
                    View viewPeekDecorView = window != null ? window.peekDecorView() : null;
                    if (viewPeekDecorView != null) {
                        viewPeekDecorView.cancelPendingInputEvents();
                    }
                }
                break;
            case 1:
                if (lifecycle$Event == Lifecycle$Event.ON_DESTROY) {
                    this.c.c.b = null;
                    if (!this.c.isChangingConfigurations()) {
                        this.c.d().a();
                    }
                    df dfVar = this.c.j;
                    ComponentActivity componentActivity = dfVar.e;
                    componentActivity.getWindow().getDecorView().removeCallbacks(dfVar);
                    componentActivity.getWindow().getDecorView().getViewTreeObserver().removeOnDrawListener(dfVar);
                }
                break;
            case 2:
                ComponentActivity componentActivity2 = this.c;
                if (componentActivity2.g == null) {
                    cf cfVar = (cf) componentActivity2.getLastNonConfigurationInstance();
                    if (cfVar != null) {
                        componentActivity2.g = cfVar.a;
                    }
                    if (componentActivity2.g == null) {
                        componentActivity2.g = new ks0();
                    }
                }
                componentActivity2.e.b(this);
                break;
            default:
                if (lifecycle$Event == Lifecycle$Event.ON_CREATE && Build.VERSION.SDK_INT >= 33) {
                    qa0 qa0Var = this.c.i;
                    OnBackInvokedDispatcher onBackInvokedDispatcherA = bf.a((ComponentActivity) u10Var);
                    qa0Var.getClass();
                    oy.j(onBackInvokedDispatcherA, "invoker");
                    qa0Var.e = onBackInvokedDispatcherA;
                    qa0Var.b(qa0Var.g);
                    break;
                }
                break;
        }
    }
}
