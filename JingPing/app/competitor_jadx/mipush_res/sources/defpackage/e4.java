package defpackage;

import android.content.DialogInterface;
import android.view.View;
import android.widget.AdapterView;

/* JADX INFO: loaded from: classes.dex */
public final class e4 implements AdapterView.OnItemClickListener {
    public final /* synthetic */ i4 E;
    public final /* synthetic */ f4 F;

    public e4(f4 f4Var, i4 i4Var) {
        this.F = f4Var;
        this.E = i4Var;
    }

    @Override // android.widget.AdapterView.OnItemClickListener
    public final void onItemClick(AdapterView adapterView, View view, int i, long j) {
        f4 f4Var = this.F;
        DialogInterface.OnClickListener onClickListener = f4Var.h;
        i4 i4Var = this.E;
        onClickListener.onClick(i4Var.b, i);
        if (f4Var.i) {
            return;
        }
        i4Var.b.dismiss();
    }
}
