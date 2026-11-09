package defpackage;

import java.math.BigInteger;

/* JADX INFO: loaded from: classes.dex */
public final class qs extends a0 {
    public final /* synthetic */ int E;
    public final Object F;
    public final Object G;

    public qs(BigInteger bigInteger, BigInteger bigInteger2, int i) {
        this.E = i;
        switch (i) {
            case 1:
                this.F = bigInteger;
                this.G = bigInteger2;
                break;
            default:
                this.F = new y(bigInteger);
                this.G = new y(bigInteger2);
                break;
        }
    }

    @Override // defpackage.a0, defpackage.t
    public final g0 b() {
        switch (this.E) {
            case 0:
                u uVar = new u(2);
                uVar.c((y) this.F);
                uVar.c((y) this.G);
                wk wkVar = new wk(uVar, 0);
                wkVar.H = -1;
                return wkVar;
            default:
                u uVar2 = new u(2);
                uVar2.c(new y((BigInteger) this.F));
                uVar2.c(new y((BigInteger) this.G));
                wk wkVar2 = new wk(uVar2, 0);
                wkVar2.H = -1;
                return wkVar2;
        }
    }
}
