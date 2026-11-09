package defpackage;

import androidx.activity.ComponentActivity;

/* JADX INFO: loaded from: classes.dex */
public final /* synthetic */ class we implements ou {
    public final /* synthetic */ int b;
    public final /* synthetic */ vg0 c;

    public /* synthetic */ we(vg0 vg0Var, int i) {
        this.b = i;
        this.c = vg0Var;
    }

    @Override // defpackage.ou
    public final Object invoke() {
        vg0 vg0Var = this.c;
        switch (this.b) {
            case 0:
                int i = ComponentActivity.u;
                ((ComponentActivity) vg0Var).reportFullyDrawn();
                return null;
            case 1:
                return xi.B((ComponentActivity) vg0Var);
            default:
                vg0Var.getLifecycle().a(new dd0(0, vg0Var));
                return bq0.a;
        }
    }
}
