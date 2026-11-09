package defpackage;

import android.content.DialogInterface;
import android.graphics.drawable.Drawable;
import android.util.Log;
import android.widget.ListAdapter;
import androidx.appcompat.app.AlertController$RecycleListView;
import androidx.appcompat.widget.AppCompatSpinner;

/* JADX INFO: loaded from: classes.dex */
public final class l5 implements q5, DialogInterface.OnClickListener {
    public x2 b;
    public m5 c;
    public CharSequence d;
    public final /* synthetic */ AppCompatSpinner e;

    public l5(AppCompatSpinner appCompatSpinner) {
        this.e = appCompatSpinner;
    }

    @Override // defpackage.q5
    public final boolean b() {
        x2 x2Var = this.b;
        if (x2Var != null) {
            return x2Var.isShowing();
        }
        return false;
    }

    @Override // defpackage.q5
    public final void c(int i) {
        Log.e("AppCompatSpinner", "Cannot set horizontal offset for MODE_DIALOG, ignoring");
    }

    @Override // defpackage.q5
    public final int d() {
        return 0;
    }

    @Override // defpackage.q5
    public final void dismiss() {
        x2 x2Var = this.b;
        if (x2Var != null) {
            x2Var.dismiss();
            this.b = null;
        }
    }

    @Override // defpackage.q5
    public final void e(int i, int i2) {
        if (this.c == null) {
            return;
        }
        AppCompatSpinner appCompatSpinner = this.e;
        w2 w2Var = new w2(appCompatSpinner.getPopupContext());
        CharSequence charSequence = this.d;
        s2 s2Var = (s2) w2Var.c;
        if (charSequence != null) {
            s2Var.d = charSequence;
        }
        m5 m5Var = this.c;
        int selectedItemPosition = appCompatSpinner.getSelectedItemPosition();
        s2Var.g = m5Var;
        s2Var.h = this;
        s2Var.j = selectedItemPosition;
        s2Var.i = true;
        x2 x2VarA = w2Var.a();
        this.b = x2VarA;
        AlertController$RecycleListView alertController$RecycleListView = x2VarA.g.e;
        j5.d(alertController$RecycleListView, i);
        j5.c(alertController$RecycleListView, i2);
        this.b.show();
    }

    @Override // defpackage.q5
    public final int g() {
        return 0;
    }

    @Override // defpackage.q5
    public final Drawable h() {
        return null;
    }

    @Override // defpackage.q5
    public final CharSequence i() {
        return this.d;
    }

    @Override // defpackage.q5
    public final void k(CharSequence charSequence) {
        this.d = charSequence;
    }

    @Override // defpackage.q5
    public final void l(Drawable drawable) {
        Log.e("AppCompatSpinner", "Cannot set popup background for MODE_DIALOG, ignoring");
    }

    @Override // defpackage.q5
    public final void m(int i) {
        Log.e("AppCompatSpinner", "Cannot set vertical offset for MODE_DIALOG, ignoring");
    }

    @Override // defpackage.q5
    public final void n(ListAdapter listAdapter) {
        this.c = (m5) listAdapter;
    }

    @Override // android.content.DialogInterface.OnClickListener
    public final void onClick(DialogInterface dialogInterface, int i) {
        AppCompatSpinner appCompatSpinner = this.e;
        appCompatSpinner.setSelection(i);
        if (appCompatSpinner.getOnItemClickListener() != null) {
            appCompatSpinner.performItemClick(null, i, this.c.getItemId(i));
        }
        dismiss();
    }

    @Override // defpackage.q5
    public final void p(int i) {
        Log.e("AppCompatSpinner", "Cannot set horizontal (original) offset for MODE_DIALOG, ignoring");
    }
}
