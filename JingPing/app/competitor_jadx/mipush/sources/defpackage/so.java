package defpackage;

/* JADX INFO: loaded from: classes.dex */
public class so extends ao {
    public int m;

    public so(q71 q71Var) {
        super(q71Var);
        if (q71Var instanceof g30) {
            this.e = 2;
        } else {
            this.e = 3;
        }
    }

    @Override // defpackage.ao
    public final void d(int i) {
        if (this.j) {
            return;
        }
        this.j = true;
        this.g = i;
        for (xn xnVar : this.k) {
            xnVar.a(xnVar);
        }
    }
}
