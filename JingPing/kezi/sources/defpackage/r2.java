package defpackage;

import android.content.DialogInterface;
import android.view.View;
import android.widget.AdapterView;

/* JADX INFO: loaded from: classes.dex */
public final class r2 implements AdapterView.OnItemClickListener {
    public final /* synthetic */ v2 b;
    public final /* synthetic */ s2 c;

    public r2(s2 s2Var, v2 v2Var) {
        this.c = s2Var;
        this.b = v2Var;
    }

    @Override // android.widget.AdapterView.OnItemClickListener
    public final void onItemClick(AdapterView adapterView, View view, int i, long j) {
        s2 s2Var = this.c;
        DialogInterface.OnClickListener onClickListener = s2Var.h;
        v2 v2Var = this.b;
        onClickListener.onClick(v2Var.b, i);
        if (s2Var.i) {
            return;
        }
        v2Var.b.dismiss();
    }
}
