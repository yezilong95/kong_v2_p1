package defpackage;

import android.content.Context;
import android.view.View;
import android.view.Window;
import androidx.appcompat.widget.l;

/* JADX INFO: loaded from: classes.dex */
public final class so0 implements View.OnClickListener {
    public final h1 b;
    public final /* synthetic */ l c;

    public so0(l lVar) {
        this.c = lVar;
        Context context = lVar.a.getContext();
        CharSequence charSequence = lVar.h;
        h1 h1Var = new h1();
        h1Var.e = 4096;
        h1Var.g = 4096;
        h1Var.l = null;
        h1Var.m = null;
        h1Var.n = false;
        h1Var.o = false;
        h1Var.p = 16;
        h1Var.i = context;
        h1Var.a = charSequence;
        this.b = h1Var;
    }

    @Override // android.view.View.OnClickListener
    public final void onClick(View view) {
        l lVar = this.c;
        Window.Callback callback = lVar.k;
        if (callback == null || !lVar.l) {
            return;
        }
        callback.onMenuItemSelected(0, this.b);
    }
}
