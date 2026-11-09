package defpackage;

/* JADX INFO: loaded from: classes.dex */
public abstract class nu extends ej {
    public long G;
    public boolean H;
    public h8 I;

    public final void A(boolean z) {
        this.G = (z ? 4294967296L : 1L) + this.G;
        if (z) {
            return;
        }
        this.H = true;
    }

    public final boolean B() {
        h8 h8Var = this.I;
        if (h8Var == null) {
            return false;
        }
        wo woVar = (wo) (h8Var.isEmpty() ? null : h8Var.removeFirst());
        if (woVar == null) {
            return false;
        }
        woVar.run();
        return true;
    }

    public abstract void C();

    public final void x() {
        long j = this.G - 4294967296L;
        this.G = j;
        if (j <= 0 && this.H) {
            C();
        }
    }

    public final void y(wo woVar) {
        h8 h8Var = this.I;
        if (h8Var == null) {
            h8Var = new h8();
            this.I = h8Var;
        }
        h8Var.addLast(woVar);
    }

    public abstract Thread z();
}
