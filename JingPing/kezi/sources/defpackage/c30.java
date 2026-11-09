package defpackage;

/* JADX INFO: loaded from: classes.dex */
public class c30 extends gs0 {
    public static final wt c = new wt(1);
    public final xj0 b = new xj0();

    @Override // defpackage.gs0
    public final void b() {
        xj0 xj0Var = this.b;
        int i = xj0Var.d;
        if (i > 0) {
            xj0Var.c[0].getClass();
            throw new ClassCastException();
        }
        Object[] objArr = xj0Var.c;
        for (int i2 = 0; i2 < i; i2++) {
            objArr[i2] = null;
        }
        xj0Var.d = 0;
    }
}
