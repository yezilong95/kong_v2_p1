package defpackage;

/* JADX INFO: loaded from: classes.dex */
public class ga0 extends o51 {
    public static final i00 c = new i00(1);
    public final lw0 b = new lw0();

    @Override // defpackage.o51
    public final void b() {
        lw0 lw0Var = this.b;
        int i = lw0Var.G;
        if (i > 0) {
            lw0Var.F[0].getClass();
            throw new ClassCastException();
        }
        Object[] objArr = lw0Var.F;
        for (int i2 = 0; i2 < i; i2++) {
            objArr[i2] = null;
        }
        lw0Var.G = 0;
    }
}
