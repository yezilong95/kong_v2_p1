package defpackage;

import android.view.ViewGroup;
import androidx.transition.Transition;

/* JADX INFO: loaded from: classes.dex */
public final class ib extends hp0 {
    public boolean a = false;
    public final /* synthetic */ ViewGroup b;

    public ib(ViewGroup viewGroup) {
        this.b = viewGroup;
    }

    @Override // defpackage.hp0, defpackage.gp0
    public final void a() {
        oy.Q(this.b, false);
        this.a = true;
    }

    @Override // defpackage.hp0, defpackage.gp0
    public final void c() {
        oy.Q(this.b, false);
    }

    @Override // defpackage.gp0
    public final void d(Transition transition) {
        if (!this.a) {
            oy.Q(this.b, false);
        }
        transition.u(this);
    }

    @Override // defpackage.hp0, defpackage.gp0
    public final void e() {
        oy.Q(this.b, true);
    }
}
