package defpackage;

import androidx.activity.ComponentActivity;

/* JADX INFO: loaded from: classes.dex */
public final /* synthetic */ class qg implements a10 {
    public final /* synthetic */ int E;
    public final /* synthetic */ kr0 F;

    public /* synthetic */ qg(kr0 kr0Var, int i) {
        this.E = i;
        this.F = kr0Var;
    }

    @Override // defpackage.a10
    public final Object invoke() {
        kr0 kr0Var = this.F;
        switch (this.E) {
            case 0:
                int i = ComponentActivity.V;
                ((ComponentActivity) kr0Var).reportFullyDrawn();
                return null;
            case 1:
                return t40.l0((ComponentActivity) kr0Var);
            default:
                kr0Var.getLifecycle().a(new ym0(0, kr0Var));
                return y21.a;
        }
    }
}
