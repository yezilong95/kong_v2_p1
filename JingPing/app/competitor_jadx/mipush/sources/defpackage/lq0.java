package defpackage;

/* JADX INFO: loaded from: classes.dex */
public final class lq0 extends er {
    public final d30 G;

    public lq0(jq0 jq0Var, byte[] bArr) {
        super(jq0Var, false);
        int iB = jq0Var.b.b();
        int i = iB * 2;
        if (bArr.length != i) {
            throw new IllegalArgumentException("public key encoding does not match parameters");
        }
        this.G = new d30(z91.r(bArr, 0, iB), z91.r(bArr, iB, i));
    }

    public final byte[] getEncoded() {
        d30 d30Var = this.G;
        return z91.m(d30Var.a, d30Var.b);
    }
}
