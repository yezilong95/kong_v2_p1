package defpackage;

import android.view.ViewGroup;
import androidx.transition.Transition;

/* JADX INFO: loaded from: classes.dex */
public final class bd extends t11 {
    public boolean a = false;
    public final ViewGroup b;

    public bd(ViewGroup viewGroup) {
        this.b = viewGroup;
    }

    @Override // defpackage.t11, defpackage.s11
    public final void b() {
        o80.g0(this.b, false);
    }

    @Override // defpackage.t11, defpackage.s11
    public final void d(Transition transition) {
        o80.g0(this.b, false);
        this.a = true;
    }

    @Override // defpackage.t11, defpackage.s11
    public final void e() {
        o80.g0(this.b, true);
    }

    @Override // defpackage.s11
    public final void g(Transition transition) {
        if (!this.a) {
            o80.g0(this.b, false);
        }
        transition.x(this);
    }
}
