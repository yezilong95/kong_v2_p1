package defpackage;

import android.content.DialogInterface;
import android.graphics.drawable.Drawable;
import android.util.Log;
import android.widget.ListAdapter;
import androidx.appcompat.app.AlertController$RecycleListView;
import androidx.appcompat.widget.AppCompatSpinner;

/* JADX INFO: loaded from: classes.dex */
public final class p6 implements u6, DialogInterface.OnClickListener {
    public k4 E;
    public q6 F;
    public CharSequence G;
    public final /* synthetic */ AppCompatSpinner H;

    public p6(AppCompatSpinner appCompatSpinner) {
        this.H = appCompatSpinner;
    }

    @Override // defpackage.u6
    public final boolean b() {
        k4 k4Var = this.E;
        if (k4Var != null) {
            return k4Var.isShowing();
        }
        return false;
    }

    @Override // defpackage.u6
    public final int c() {
        return 0;
    }

    @Override // defpackage.u6
    public final void dismiss() {
        k4 k4Var = this.E;
        if (k4Var != null) {
            k4Var.dismiss();
            this.E = null;
        }
    }

    @Override // defpackage.u6
    public final Drawable e() {
        return null;
    }

    @Override // defpackage.u6
    public final void f(CharSequence charSequence) {
        this.G = charSequence;
    }

    @Override // defpackage.u6
    public final void g(Drawable drawable) {
        Log.e("AppCompatSpinner", "Cannot set popup background for MODE_DIALOG, ignoring");
    }

    @Override // defpackage.u6
    public final void k(int i) {
        Log.e("AppCompatSpinner", "Cannot set vertical offset for MODE_DIALOG, ignoring");
    }

    @Override // defpackage.u6
    public final void l(int i) {
        Log.e("AppCompatSpinner", "Cannot set horizontal (original) offset for MODE_DIALOG, ignoring");
    }

    @Override // defpackage.u6
    public final void m(int i) {
        Log.e("AppCompatSpinner", "Cannot set horizontal offset for MODE_DIALOG, ignoring");
    }

    @Override // defpackage.u6
    public final void n(int i, int i2) {
        if (this.F == null) {
            return;
        }
        AppCompatSpinner appCompatSpinner = this.H;
        j4 j4Var = new j4(appCompatSpinner.getPopupContext());
        CharSequence charSequence = this.G;
        f4 f4Var = (f4) j4Var.F;
        if (charSequence != null) {
            f4Var.d = charSequence;
        }
        q6 q6Var = this.F;
        int selectedItemPosition = appCompatSpinner.getSelectedItemPosition();
        f4Var.g = q6Var;
        f4Var.h = this;
        f4Var.j = selectedItemPosition;
        f4Var.i = true;
        k4 k4VarA = j4Var.a();
        this.E = k4VarA;
        AlertController$RecycleListView alertController$RecycleListView = k4VarA.J.e;
        n6.d(alertController$RecycleListView, i);
        n6.c(alertController$RecycleListView, i2);
        this.E.show();
    }

    @Override // defpackage.u6
    public final int o() {
        return 0;
    }

    @Override // android.content.DialogInterface.OnClickListener
    public final void onClick(DialogInterface dialogInterface, int i) {
        AppCompatSpinner appCompatSpinner = this.H;
        appCompatSpinner.setSelection(i);
        if (appCompatSpinner.getOnItemClickListener() != null) {
            appCompatSpinner.performItemClick(null, i, this.F.getItemId(i));
        }
        dismiss();
    }

    @Override // defpackage.u6
    public final CharSequence p() {
        return this.G;
    }

    @Override // defpackage.u6
    public final void q(ListAdapter listAdapter) {
        this.F = (q6) listAdapter;
    }
}
