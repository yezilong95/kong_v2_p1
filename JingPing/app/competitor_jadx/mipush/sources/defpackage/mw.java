package defpackage;

import java.security.SecureRandom;

/* JADX INFO: loaded from: classes.dex */
public final class mw extends SecureRandom {
    public final byte[] E;
    public int F;

    public mw() {
        super(null, new lw());
        this.E = c30.a("01020304ffffffff0506070811111111");
        this.F = 0;
    }

    @Override // java.security.SecureRandom, java.util.Random
    public final void nextBytes(byte[] bArr) {
        System.arraycopy(this.E, this.F, bArr, 0, bArr.length);
        this.F += bArr.length;
    }
}
