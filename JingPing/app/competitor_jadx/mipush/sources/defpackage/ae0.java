package defpackage;

import android.content.DialogInterface;
import android.view.KeyEvent;
import android.view.View;
import android.view.Window;

/* JADX INFO: loaded from: classes.dex */
public final class ae0 implements DialogInterface.OnKeyListener, DialogInterface.OnClickListener, DialogInterface.OnDismissListener, pe0 {
    public rx0 E;
    public k4 F;
    public u90 G;

    @Override // defpackage.pe0
    public final void a(zd0 zd0Var, boolean z) {
        k4 k4Var;
        if ((z || zd0Var == this.E) && (k4Var = this.F) != null) {
            k4Var.dismiss();
        }
    }

    @Override // defpackage.pe0
    public final boolean n(zd0 zd0Var) {
        return false;
    }

    @Override // android.content.DialogInterface.OnClickListener
    public final void onClick(DialogInterface dialogInterface, int i) {
        u90 u90Var = this.G;
        if (u90Var.K == null) {
            u90Var.K = new t90(u90Var);
        }
        this.E.q(u90Var.K.getItem(i), null, 0);
    }

    @Override // android.content.DialogInterface.OnDismissListener
    public final void onDismiss(DialogInterface dialogInterface) {
        this.G.a(this.E, true);
    }

    @Override // android.content.DialogInterface.OnKeyListener
    public final boolean onKey(DialogInterface dialogInterface, int i, KeyEvent keyEvent) {
        Window window;
        View decorView;
        KeyEvent.DispatcherState keyDispatcherState;
        View decorView2;
        KeyEvent.DispatcherState keyDispatcherState2;
        rx0 rx0Var = this.E;
        if (i == 82 || i == 4) {
            if (keyEvent.getAction() == 0 && keyEvent.getRepeatCount() == 0) {
                Window window2 = this.F.getWindow();
                if (window2 != null && (decorView2 = window2.getDecorView()) != null && (keyDispatcherState2 = decorView2.getKeyDispatcherState()) != null) {
                    keyDispatcherState2.startTracking(keyEvent, this);
                    return true;
                }
            } else if (keyEvent.getAction() == 1 && !keyEvent.isCanceled() && (window = this.F.getWindow()) != null && (decorView = window.getDecorView()) != null && (keyDispatcherState = decorView.getKeyDispatcherState()) != null && keyDispatcherState.isTracking(keyEvent)) {
                rx0Var.c(true);
                dialogInterface.dismiss();
                return true;
            }
        }
        return rx0Var.performShortcut(i, keyEvent, 0);
    }
}
