package defpackage;

/* JADX INFO: loaded from: classes.dex */
public final class xa implements Runnable {
    public final /* synthetic */ ya b;
    public final /* synthetic */ m60 c;
    public final /* synthetic */ i60 d;
    public final /* synthetic */ o70 e;

    public xa(o70 o70Var, ya yaVar, m60 m60Var, i60 i60Var) {
        this.e = o70Var;
        this.b = yaVar;
        this.c = m60Var;
        this.d = i60Var;
    }

    @Override // java.lang.Runnable
    public final void run() {
        ya yaVar = this.b;
        if (yaVar != null) {
            o70 o70Var = this.e;
            ((za) o70Var.c).A = true;
            yaVar.b.c(false);
            ((za) o70Var.c).A = false;
        }
        m60 m60Var = this.c;
        if (m60Var.isEnabled() && m60Var.hasSubMenu()) {
            this.d.q(m60Var, null, 4);
        }
    }
}
