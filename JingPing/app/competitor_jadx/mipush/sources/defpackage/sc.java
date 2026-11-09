package defpackage;

/* JADX INFO: loaded from: classes.dex */
public final class sc implements Runnable {
    public final /* synthetic */ tc E;
    public final /* synthetic */ de0 F;
    public final /* synthetic */ zd0 G;
    public final /* synthetic */ p50 H;

    public sc(p50 p50Var, tc tcVar, de0 de0Var, zd0 zd0Var) {
        this.H = p50Var;
        this.E = tcVar;
        this.F = de0Var;
        this.G = zd0Var;
    }

    @Override // java.lang.Runnable
    public final void run() {
        tc tcVar = this.E;
        if (tcVar != null) {
            p50 p50Var = this.H;
            ((uc) p50Var.F).d0 = true;
            tcVar.b.c(false);
            ((uc) p50Var.F).d0 = false;
        }
        de0 de0Var = this.F;
        if (de0Var.isEnabled() && de0Var.hasSubMenu()) {
            this.G.q(de0Var, null, 4);
        }
    }
}
