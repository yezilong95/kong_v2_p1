package defpackage;

import java.math.BigInteger;

/* JADX INFO: loaded from: classes.dex */
public final class q10 extends a0 {
    public final y E;
    public final y F;
    public final y G;

    public q10(BigInteger bigInteger, BigInteger bigInteger2, BigInteger bigInteger3) {
        this.E = new y(bigInteger);
        this.F = new y(bigInteger2);
        this.G = new y(bigInteger3);
    }

    @Override // defpackage.a0, defpackage.t
    public final g0 b() {
        u uVar = new u(4);
        uVar.c(new y(1024));
        uVar.c(this.E);
        uVar.c(this.F);
        uVar.c(this.G);
        wk wkVar = new wk(uVar, 0);
        wkVar.H = -1;
        return wkVar;
    }
}
