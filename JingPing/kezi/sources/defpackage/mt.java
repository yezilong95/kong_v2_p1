package defpackage;

import android.view.View;
import android.view.ViewGroup;
import androidx.fragment.app.d;

/* JADX INFO: loaded from: classes.dex */
public final class mt implements View.OnAttachStateChangeListener {
    public final /* synthetic */ d b;
    public final /* synthetic */ nt c;

    public mt(nt ntVar, d dVar) {
        this.c = ntVar;
        this.b = dVar;
    }

    @Override // android.view.View.OnAttachStateChangeListener
    public final void onViewAttachedToWindow(View view) {
        d dVar = this.b;
        dVar.k();
        vj.f((ViewGroup) dVar.c.F.getParent(), this.c.b.A()).e();
    }

    @Override // android.view.View.OnAttachStateChangeListener
    public final void onViewDetachedFromWindow(View view) {
    }
}
