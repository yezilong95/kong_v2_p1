package defpackage;

import androidx.lifecycle.Lifecycle$Event;
import androidx.lifecycle.Lifecycle$State;
import com.mamimobi.shell.activity.MainActivity;

/* JADX INFO: loaded from: classes.dex */
public final class z20 extends a30 implements s10 {
    public final MainActivity f;
    public final /* synthetic */ c80 g;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public z20(c80 c80Var, MainActivity mainActivity, y30 y30Var) {
        super(c80Var, y30Var);
        this.g = c80Var;
        this.f = mainActivity;
    }

    @Override // defpackage.s10
    public final void a(u10 u10Var, Lifecycle$Event lifecycle$Event) {
        w10 w10Var = this.f.e;
        Lifecycle$State lifecycle$State = w10Var.c;
        if (lifecycle$State == Lifecycle$State.DESTROYED) {
            this.g.h(this.b);
            return;
        }
        Lifecycle$State lifecycle$State2 = null;
        while (lifecycle$State2 != lifecycle$State) {
            b(e());
            lifecycle$State2 = lifecycle$State;
            lifecycle$State = w10Var.c;
        }
    }

    @Override // defpackage.a30
    public final void c() {
        this.f.e.b(this);
    }

    @Override // defpackage.a30
    public final boolean d(MainActivity mainActivity) {
        return this.f == mainActivity;
    }

    @Override // defpackage.a30
    public final boolean e() {
        return this.f.e.c.isAtLeast(Lifecycle$State.STARTED);
    }
}
