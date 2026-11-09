package defpackage;

/* JADX INFO: loaded from: classes.dex */
public final class lr implements dl0 {
    public final /* synthetic */ boolean E;
    public final /* synthetic */ boolean F;
    public final /* synthetic */ or G;

    public lr(or orVar, boolean z, boolean z2) {
        this.G = orVar;
        this.E = z;
        this.F = z2;
    }

    @Override // defpackage.dl0
    public final el0 a(el0 el0Var) {
        q31 q31Var = el0Var instanceof q31 ? (q31) el0Var : null;
        if (q31Var == null) {
            q31Var = new q31();
            q31Var.a = false;
            q31Var.b = false;
            q31Var.c = false;
        }
        if (!q31Var.a) {
            boolean z = q31Var.b;
            or orVar = this.G;
            if (!z) {
                if (!this.E && !orVar.q()) {
                    q31Var.a = true;
                    return q31Var;
                }
                q31Var.b = true;
            }
            if (this.F && !q31Var.c) {
                if (!orVar.r()) {
                    q31Var.a = true;
                    return q31Var;
                }
                q31Var.c = true;
            }
        }
        return q31Var;
    }
}
