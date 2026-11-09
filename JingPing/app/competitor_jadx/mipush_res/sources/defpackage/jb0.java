package defpackage;

/* JADX INFO: loaded from: classes.dex */
public final class jb0 extends er {
    public final byte[] G;
    public final byte[] H;

    public jb0(hb0 hb0Var, byte[] bArr) {
        super(hb0Var, false);
        this.G = z91.r(bArr, 0, 32);
        this.H = z91.r(bArr, 32, bArr.length);
    }

    public final byte[] getEncoded() {
        return z91.m(this.G, this.H);
    }

    public jb0(hb0 hb0Var, byte[] bArr, byte[] bArr2) {
        super(hb0Var, false);
        this.G = z91.l(bArr);
        this.H = z91.l(bArr2);
    }
}
