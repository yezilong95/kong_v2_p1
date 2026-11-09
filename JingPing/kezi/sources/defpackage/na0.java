package defpackage;

import androidx.lifecycle.Lifecycle$Event;

/* JADX INFO: loaded from: classes.dex */
public final class na0 implements s10, ka {
    public final p10 b;
    public final ot c;
    public oa0 d;
    public final /* synthetic */ qa0 e;

    public na0(qa0 qa0Var, p10 p10Var, ot otVar) {
        oy.j(p10Var, "lifecycle");
        oy.j(otVar, "onBackPressedCallback");
        this.e = qa0Var;
        this.b = p10Var;
        this.c = otVar;
        p10Var.a(this);
    }

    @Override // defpackage.s10
    public final void a(u10 u10Var, Lifecycle$Event lifecycle$Event) {
        if (lifecycle$Event == Lifecycle$Event.ON_START) {
            qa0 qa0Var = this.e;
            ot otVar = this.c;
            oy.j(otVar, "onBackPressedCallback");
            qa0Var.b.addLast(otVar);
            oa0 oa0Var = new oa0(qa0Var, otVar);
            otVar.b.add(oa0Var);
            qa0Var.c();
            otVar.c = new pa0(0, qa0Var, qa0.class, "updateEnabledCallbacks", "updateEnabledCallbacks()V", 0, 1);
            this.d = oa0Var;
            return;
        }
        if (lifecycle$Event != Lifecycle$Event.ON_STOP) {
            if (lifecycle$Event == Lifecycle$Event.ON_DESTROY) {
                cancel();
            }
        } else {
            oa0 oa0Var2 = this.d;
            if (oa0Var2 != null) {
                oa0Var2.cancel();
            }
        }
    }

    @Override // defpackage.ka
    public final void cancel() {
        this.b.b(this);
        this.c.b.remove(this);
        oa0 oa0Var = this.d;
        if (oa0Var != null) {
            oa0Var.cancel();
        }
        this.d = null;
    }
}
