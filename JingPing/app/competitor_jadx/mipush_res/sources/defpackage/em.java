package defpackage;

import java.math.BigInteger;

/* JADX INFO: loaded from: classes.dex */
public final class em extends a0 {
    public BigInteger E;
    public dm F;
    public y G;
    public e0 H;
    public y I;
    public e0 J;

    @Override // defpackage.a0, defpackage.t
    public final g0 b() {
        u uVar = new u(6);
        BigInteger bigIntegerValueOf = BigInteger.valueOf(0L);
        BigInteger bigInteger = this.E;
        if (bigInteger.compareTo(bigIntegerValueOf) != 0) {
            uVar.c(new j9(true, 0, (t) new y(bigInteger), 1));
        }
        uVar.c(this.F);
        uVar.c(this.G);
        uVar.c(this.H);
        uVar.c(this.I);
        uVar.c(this.J);
        wk wkVar = new wk(uVar, 0);
        wkVar.H = -1;
        return wkVar;
    }
}
