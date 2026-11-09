package defpackage;

/* JADX INFO: loaded from: classes.dex */
public final class kq0 extends er {
    public final d30 G;
    public final d30 H;

    public kq0(jq0 jq0Var, byte[] bArr) {
        super(jq0Var, true);
        int iB = jq0Var.b.b();
        int i = iB * 4;
        if (bArr.length != i) {
            throw new IllegalArgumentException("private key encoding does not match parameters");
        }
        int i2 = iB * 2;
        this.G = new d30(z91.r(bArr, 0, iB), z91.r(bArr, iB, i2));
        int i3 = iB * 3;
        this.H = new d30(z91.r(bArr, i2, i3), z91.r(bArr, i3, i));
    }

    public final byte[] getEncoded() {
        d30 d30Var = this.G;
        byte[] bArr = d30Var.a;
        byte[] bArr2 = d30Var.b;
        d30 d30Var2 = this.H;
        return z91.p(new byte[][]{bArr, bArr2, d30Var2.a, d30Var2.b});
    }
}
