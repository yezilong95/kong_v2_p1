package defpackage;

/* JADX INFO: loaded from: classes.dex */
public final class n31 extends a0 {
    public p E;
    public y F;

    public n31(byte[] bArr, int i) {
        if (bArr == null) {
            throw new IllegalArgumentException("'seed' cannot be null");
        }
        this.E = new lk(bArr, 0);
        this.F = new y(i);
    }

    @Override // defpackage.a0, defpackage.t
    public final g0 b() {
        u uVar = new u(2);
        uVar.c(this.E);
        uVar.c(this.F);
        wk wkVar = new wk(uVar, 0);
        wkVar.H = -1;
        return wkVar;
    }
}
