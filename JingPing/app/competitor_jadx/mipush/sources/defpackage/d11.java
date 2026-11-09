package defpackage;

import android.content.Context;
import android.view.View;
import android.view.Window;
import androidx.appcompat.widget.l;
import io.nats.client.Options;

/* JADX INFO: loaded from: classes.dex */
public final class d11 implements View.OnClickListener {
    public final c3 E;
    public final /* synthetic */ l F;

    public d11(l lVar) {
        this.F = lVar;
        Context context = lVar.a.getContext();
        CharSequence charSequence = lVar.h;
        c3 c3Var = new c3();
        c3Var.e = Options.DEFAULT_MAX_CONTROL_LINE;
        c3Var.g = Options.DEFAULT_MAX_CONTROL_LINE;
        c3Var.l = null;
        c3Var.m = null;
        c3Var.n = false;
        c3Var.o = false;
        c3Var.p = 16;
        c3Var.i = context;
        c3Var.a = charSequence;
        this.E = c3Var;
    }

    @Override // android.view.View.OnClickListener
    public final void onClick(View view) {
        l lVar = this.F;
        Window.Callback callback = lVar.k;
        if (callback == null || !lVar.l) {
            return;
        }
        callback.onMenuItemSelected(0, this.E);
    }
}
