package defpackage;

import org.bouncycastle.crypto.CryptoServicePurpose;

/* JADX INFO: loaded from: classes.dex */
public final class gq0 extends b70 implements zq0 {
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public gq0(int i) {
        super(i, CryptoServicePurpose.ANY);
        if (i != 128 && i != 256) {
            throw new IllegalArgumentException(zn.k(i, "'bitStrength' ", " not supported for SHAKE"));
        }
    }

    @Override // defpackage.vd0
    public final vd0 a() {
        return new gq0(this);
    }

    @Override // defpackage.po
    public final String b() {
        return "SHAKE" + this.f;
    }

    @Override // defpackage.b70, defpackage.po
    public final int c() {
        return this.f / 4;
    }

    @Override // defpackage.po
    public final int d(int i, byte[] bArr) {
        int i2 = this.f / 4;
        h(bArr, i, i2);
        return i2;
    }

    @Override // defpackage.vd0
    public final void g(vd0 vd0Var) {
        if (!(vd0Var instanceof gq0)) {
            throw new IllegalArgumentException("no SHAKEDigest instance");
        }
        gq0 gq0Var = (gq0) vd0Var;
        this.e = gq0Var.e;
        this.d = gq0Var.d;
        this.g = gq0Var.g;
        this.f = gq0Var.f;
        long[] jArr = gq0Var.b;
        long[] jArr2 = this.b;
        System.arraycopy(jArr, 0, jArr2, 0, jArr2.length);
        this.a = gq0Var.a;
    }

    @Override // defpackage.zq0
    public final int h(byte[] bArr, int i, int i2) {
        p(bArr, i, i2);
        n();
        return i2;
    }

    @Override // defpackage.b70
    public final sj l() {
        return new wy0(b(), 1);
    }

    public final void p(byte[] bArr, int i, int i2) {
        if (!this.g) {
            k(15, 4);
        }
        o(((long) i2) * 8, bArr, i);
    }

    public final String toString() {
        return "SHAKE[Java]()";
    }
}
