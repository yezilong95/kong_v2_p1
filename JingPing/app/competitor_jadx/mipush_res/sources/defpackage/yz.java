package defpackage;

import android.view.View;
import android.view.ViewGroup;
import androidx.fragment.app.d;

/* JADX INFO: loaded from: classes.dex */
public final class yz implements View.OnAttachStateChangeListener {
    public final /* synthetic */ d E;
    public final /* synthetic */ zz F;

    public yz(zz zzVar, d dVar) {
        this.F = zzVar;
        this.E = dVar;
    }

    @Override // android.view.View.OnAttachStateChangeListener
    public final void onViewAttachedToWindow(View view) {
        d dVar = this.E;
        dVar.k();
        on.f((ViewGroup) dVar.c.i0.getParent(), this.F.E.z()).e();
    }

    @Override // android.view.View.OnAttachStateChangeListener
    public final void onViewDetachedFromWindow(View view) {
    }
}
