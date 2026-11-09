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
public class mo extends vz implements DialogInterface.OnCancelListener, DialogInterface.OnDismissListener {
    public boolean E0;
    public Dialog G0;
    public boolean H0;
    public boolean I0;
    public Handler v0;
    public final f w0 = new f(8, this);
    public final io x0 = new io(this);
    public final jo y0 = new jo(this);
    public int z0 = 0;
    public int A0 = 0;
    public boolean B0 = true;
    public boolean C0 = true;
    public int D0 = -1;
    public final ko F0 = new ko(this);
    public boolean J0 = false;

    public final void C(boolean z, boolean z2) {
        if (this.I0) {
            return;
        }
        this.I0 = true;
        Dialog dialog = this.G0;
        if (dialog != null) {
            dialog.setOnDismissListener(null);
            this.G0.dismiss();
            if (!z2) {
                if (Looper.myLooper() == this.v0.getLooper()) {
                    onDismiss(this.G0);
                } else {
                    this.v0.post(this.w0);
                }
            }
        }
        this.H0 = true;
        if (this.D0 >= 0) {
            c cVarI = i();
            int i = this.D0;
            if (i < 0) {
                throw new IllegalArgumentException(zn.j(i, "Bad id: "));
            }
            cVarI.r(new f00(cVarI, i), false);
            this.D0 = -1;
            return;
        }
        m9 m9Var = new m9(i());
        c cVar = this.V;
        if (cVar != null && cVar != m9Var.p) {
            throw new IllegalStateException("Cannot remove Fragment attached to a different FragmentManager. Fragment " + toString() + " is already attached to a FragmentManager.");
        }
        m9Var.b(new m00(3, this));
        if (z) {
            m9Var.d(true);
        } else {
            m9Var.d(false);
        }
    }

    public Dialog D() {
        if (c.B(3)) {
            Log.d("FragmentManager", "onCreateDialog called for DialogFragment " + this);
        }
        return new Dialog(y(), this.A0);
    }

    @Override // defpackage.vz
    public final z91 b() {
        return new lo(this, new rz(this));
    }

    @Override // defpackage.vz
    public final void k(FragmentActivity fragmentActivity) {
        super.k(fragmentActivity);
        this.r0.c(this.F0);
        this.I0 = false;
    }

    @Override // defpackage.vz
    public void l(Bundle bundle) {
        super.l(bundle);
        this.v0 = new Handler();
        this.C0 = this.a0 == 0;
        if (bundle != null) {
            this.z0 = bundle.getInt("android:style", 0);
            this.A0 = bundle.getInt("android:theme", 0);
            this.B0 = bundle.getBoolean("android:cancelable", true);
            this.C0 = bundle.getBoolean("android:showsDialog", this.C0);
            this.D0 = bundle.getInt("android:backStackId", -1);
        }
    }

    @Override // defpackage.vz
    public final void n() {
        this.g0 = true;
        Dialog dialog = this.G0;
        if (dialog != null) {
            this.H0 = true;
            dialog.setOnDismissListener(null);
            this.G0.dismiss();
            if (!this.I0) {
                onDismiss(this.G0);
            }
            this.G0 = null;
            this.J0 = false;
        }
    }

    @Override // defpackage.vz
    public final void o() {
        this.g0 = true;
        if (!this.I0) {
            this.I0 = true;
        }
        wf0 wf0Var = this.r0;
        ko koVar = this.F0;
        wf0Var.getClass();
        ea0.a("removeObserver");
        da0 da0Var = (da0) wf0Var.b.b(koVar);
        if (da0Var == null) {
            return;
        }
        da0Var.a(false);
    }

    public void onDismiss(DialogInterface dialogInterface) {
        if (this.H0) {
            return;
        }
        if (c.B(3)) {
            Log.d("FragmentManager", "onDismiss called for DialogFragment " + this);
        }
        C(true, true);
    }

    /* JADX WARN: Removed duplicated region for block: B:26:0x0044 A[Catch: all -> 0x004c, TryCatch #0 {all -> 0x004c, blocks: (B:12:0x001a, B:14:0x0026, B:24:0x003e, B:26:0x0044, B:29:0x004e, B:20:0x0030, B:22:0x0036, B:23:0x003b, B:30:0x0066), top: B:49:0x001a }] */
    @Override // defpackage.vz
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final android.view.LayoutInflater p(android.os.Bundle r8) {
        /*
            Method dump skipped, instruction units count: 212
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.mo.p(android.os.Bundle):android.view.LayoutInflater");
    }

    @Override // defpackage.vz
    public void q(Bundle bundle) {
        Dialog dialog = this.G0;
        if (dialog != null) {
            Bundle bundleOnSaveInstanceState = dialog.onSaveInstanceState();
            bundleOnSaveInstanceState.putBoolean("android:dialogShowing", false);
            bundle.putBundle("android:savedDialogState", bundleOnSaveInstanceState);
        }
        int i = this.z0;
        if (i != 0) {
            bundle.putInt("android:style", i);
        }
        int i2 = this.A0;
        if (i2 != 0) {
            bundle.putInt("android:theme", i2);
        }
        boolean z = this.B0;
        if (!z) {
            bundle.putBoolean("android:cancelable", z);
        }
        boolean z2 = this.C0;
        if (!z2) {
            bundle.putBoolean("android:showsDialog", z2);
        }
        int i3 = this.D0;
        if (i3 != -1) {
            bundle.putInt("android:backStackId", i3);
        }
    }

    @Override // defpackage.vz
    public void r() {
        this.g0 = true;
        Dialog dialog = this.G0;
        if (dialog != null) {
            this.H0 = false;
            dialog.show();
            View decorView = this.G0.getWindow().getDecorView();
            t40.n(decorView, "<this>");
            decorView.setTag(R$id.view_tree_lifecycle_owner, this);
            decorView.setTag(androidx.lifecycle.viewmodel.R$id.view_tree_view_model_store_owner, this);
            decorView.setTag(androidx.savedstate.R$id.view_tree_saved_state_registry_owner, this);
        }
    }

    @Override // defpackage.vz
    public void s() {
        this.g0 = true;
        Dialog dialog = this.G0;
        if (dialog != null) {
            dialog.hide();
        }
    }

    @Override // defpackage.vz
    public final void t(Bundle bundle) {
        Bundle bundle2;
        this.g0 = true;
        if (this.G0 == null || bundle == null || (bundle2 = bundle.getBundle("android:savedDialogState")) == null) {
            return;
        }
        this.G0.onRestoreInstanceState(bundle2);
    }

    @Override // defpackage.vz
    public final void u(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        Bundle bundle2;
        super.u(layoutInflater, viewGroup, bundle);
        if (this.i0 != null || this.G0 == null || bundle == null || (bundle2 = bundle.getBundle("android:savedDialogState")) == null) {
            return;
        }
        this.G0.onRestoreInstanceState(bundle2);
    }

    public void onCancel(DialogInterface dialogInterface) {
    }
}
