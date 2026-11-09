package defpackage;

import org.bouncycastle.crypto.CryptoServicePurpose;

/* JADX INFO: loaded from: classes.dex */
public final class dq0 extends b70 implements vd0 {
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public dq0(int i) {
        super(i, CryptoServicePurpose.ANY);
        if (i != 224 && i != 256 && i != 384 && i != 512) {
            throw new IllegalArgumentException(zn.k(i, "'bitLength' ", " not supported for SHA-3"));
        }
    }

    @Override // defpackage.vd0
    public final vd0 a() {
        return new dq0(this);
    }

    @Override // defpackage.po
    public final String b() {
        return "SHA3-" + this.f;
    }

    @Override // defpackage.po
    public final int d(int i, byte[] bArr) {
        k(2, 2);
        o(this.f, bArr, i);
        n();
        return c();
    }

    @Override // defpackage.vd0
    public final void g(vd0 vd0Var) {
        if (!(vd0Var instanceof dq0)) {
            throw new IllegalArgumentException("no SHA3Digest instance");
        }
        dq0 dq0Var = (dq0) vd0Var;
        this.e = dq0Var.e;
        this.d = dq0Var.d;
        this.g = dq0Var.g;
        this.f = dq0Var.f;
        long[] jArr = dq0Var.b;
        long[] jArr2 = this.b;
        System.arraycopy(jArr, 0, jArr2, 0, jArr2.length);
        this.a = dq0Var.a;
    }

    public final String toString() {
        return "SHA3[Java]";
    }
}
