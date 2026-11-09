package defpackage;

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.c;
import androidx.lifecycle.runtime.R$id;

/* JADX INFO: loaded from: classes.dex */
public class uk extends jt implements DialogInterface.OnCancelListener, DialogInterface.OnDismissListener {
    public Handler S;
    public boolean b0;
    public Dialog d0;
    public boolean e0;
    public boolean f0;
    public final v6 T = new v6(5, this);
    public final qk U = new qk(this);
    public final rk V = new rk(this);
    public int W = 0;
    public int X = 0;
    public boolean Y = true;
    public boolean Z = true;
    public int a0 = -1;
    public final sk c0 = new sk(this);
    public boolean g0 = false;

    public final void C(boolean z, boolean z2) {
        if (this.f0) {
            return;
        }
        this.f0 = true;
        Dialog dialog = this.d0;
        if (dialog != null) {
            dialog.setOnDismissListener(null);
            this.d0.dismiss();
            if (!z2) {
                if (Looper.myLooper() == this.S.getLooper()) {
                    onDismiss(this.d0);
                } else {
                    this.S.post(this.T);
                }
            }
        }
        this.e0 = true;
        if (this.a0 >= 0) {
            c cVarI = i();
            int i = this.a0;
            if (i < 0) {
                throw new IllegalArgumentException(hk.g(i, "Bad id: "));
            }
            cVarI.s(new tt(cVarI, i), false);
            this.a0 = -1;
            return;
        }
        s7 s7Var = new s7(i());
        c cVar = this.s;
        if (cVar != null && cVar != s7Var.p) {
            throw new IllegalStateException("Cannot remove Fragment attached to a different FragmentManager. Fragment " + toString() + " is already attached to a FragmentManager.");
        }
        s7Var.b(new au(3, this));
        if (z) {
            s7Var.d(true);
        } else {
            s7Var.d(false);
        }
    }

    public Dialog D() {
        if (c.C(3)) {
            Log.d("FragmentManager", "onCreateDialog called for DialogFragment " + this);
        }
        return new Dialog(y(), this.X);
    }

    @Override // defpackage.jt
    public final nv0 c() {
        return new tk(this, new et(this));
    }

    @Override // defpackage.jt
    public final void k(FragmentActivity fragmentActivity) {
        super.k(fragmentActivity);
        this.O.d(this.c0);
        this.f0 = false;
    }

    @Override // defpackage.jt
    public void l(Bundle bundle) {
        super.l(bundle);
        this.S = new Handler();
        this.Z = this.x == 0;
        if (bundle != null) {
            this.W = bundle.getInt("android:style", 0);
            this.X = bundle.getInt("android:theme", 0);
            this.Y = bundle.getBoolean("android:cancelable", true);
            this.Z = bundle.getBoolean("android:showsDialog", this.Z);
            this.a0 = bundle.getInt("android:backStackId", -1);
        }
    }

    @Override // defpackage.jt
    public final void n() {
        this.D = true;
        Dialog dialog = this.d0;
        if (dialog != null) {
            this.e0 = true;
            dialog.setOnDismissListener(null);
            this.d0.dismiss();
            if (!this.f0) {
                onDismiss(this.d0);
            }
            this.d0 = null;
            this.g0 = false;
        }
    }

    @Override // defpackage.jt
    public final void o() {
        this.D = true;
        if (!this.f0) {
            this.f0 = true;
        }
        this.O.h(this.c0);
    }

    public void onDismiss(DialogInterface dialogInterface) {
        if (this.e0) {
            return;
        }
        if (c.C(3)) {
            Log.d("FragmentManager", "onDismiss called for DialogFragment " + this);
        }
        C(true, true);
    }

    /* JADX WARN: Removed duplicated region for block: B:26:0x0044 A[Catch: all -> 0x004c, TryCatch #0 {all -> 0x004c, blocks: (B:12:0x001a, B:14:0x0026, B:24:0x003e, B:26:0x0044, B:29:0x004e, B:20:0x0030, B:22:0x0036, B:23:0x003b, B:30:0x0066), top: B:49:0x001a }] */
    @Override // defpackage.jt
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final android.view.LayoutInflater p(android.os.Bundle r8) {
        /*
            Method dump skipped, instruction units count: 212
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.uk.p(android.os.Bundle):android.view.LayoutInflater");
    }

    @Override // defpackage.jt
    public void q(Bundle bundle) {
        Dialog dialog = this.d0;
        if (dialog != null) {
            Bundle bundleOnSaveInstanceState = dialog.onSaveInstanceState();
            bundleOnSaveInstanceState.putBoolean("android:dialogShowing", false);
            bundle.putBundle("android:savedDialogState", bundleOnSaveInstanceState);
        }
        int i = this.W;
        if (i != 0) {
            bundle.putInt("android:style", i);
        }
        int i2 = this.X;
        if (i2 != 0) {
            bundle.putInt("android:theme", i2);
        }
        boolean z = this.Y;
        if (!z) {
            bundle.putBoolean("android:cancelable", z);
        }
        boolean z2 = this.Z;
        if (!z2) {
            bundle.putBoolean("android:showsDialog", z2);
        }
        int i3 = this.a0;
        if (i3 != -1) {
            bundle.putInt("android:backStackId", i3);
        }
    }

    @Override // defpackage.jt
    public void r() {
        this.D = true;
        Dialog dialog = this.d0;
        if (dialog != null) {
            this.e0 = false;
            dialog.show();
            View decorView = this.d0.getWindow().getDecorView();
            oy.j(decorView, "<this>");
            decorView.setTag(R$id.view_tree_lifecycle_owner, this);
            decorView.setTag(androidx.lifecycle.viewmodel.R$id.view_tree_view_model_store_owner, this);
            decorView.setTag(androidx.savedstate.R$id.view_tree_saved_state_registry_owner, this);
        }
    }

    @Override // defpackage.jt
    public void s() {
        this.D = true;
        Dialog dialog = this.d0;
        if (dialog != null) {
            dialog.hide();
        }
    }

    @Override // defpackage.jt
    public final void t(Bundle bundle) {
        Bundle bundle2;
        this.D = true;
        if (this.d0 == null || bundle == null || (bundle2 = bundle.getBundle("android:savedDialogState")) == null) {
            return;
        }
        this.d0.onRestoreInstanceState(bundle2);
    }

    @Override // defpackage.jt
    public final void u(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        Bundle bundle2;
        super.u(layoutInflater, viewGroup, bundle);
        if (this.F != null || this.d0 == null || bundle == null || (bundle2 = bundle.getBundle("android:savedDialogState")) == null) {
            return;
        }
        this.d0.onRestoreInstanceState(bundle2);
    }

    public void onCancel(DialogInterface dialogInterface) {
    }
}
