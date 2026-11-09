package defpackage;

/* JADX INFO: loaded from: classes.dex */
public class xk extends ik {
    public int m;

    public xk(ut0 ut0Var) {
        super(ut0Var);
        if (ut0Var instanceof yv) {
            this.e = 2;
        } else {
            this.e = 3;
        }
    }

    @Override // defpackage.ik
    public final void d(int i) {
        if (this.j) {
            return;
        }
        this.j = true;
        this.g = i;
        for (fk fkVar : this.k) {
            fkVar.a(fkVar);
        }
    }
}
