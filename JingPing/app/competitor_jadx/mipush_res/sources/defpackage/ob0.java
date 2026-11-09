package defpackage;

/* JADX INFO: loaded from: classes.dex */
public final class ob0 extends er {
    public final byte[] G;
    public final byte[] H;

    public ob0(mb0 mb0Var, byte[] bArr) {
        super(mb0Var, false);
        this.G = z91.r(bArr, 0, bArr.length - 32);
        this.H = z91.r(bArr, bArr.length - 32, bArr.length);
    }

    public ob0(mb0 mb0Var, byte[] bArr, byte[] bArr2) {
        super(mb0Var, false);
        this.G = z91.l(bArr);
        this.H = z91.l(bArr2);
    }
}
