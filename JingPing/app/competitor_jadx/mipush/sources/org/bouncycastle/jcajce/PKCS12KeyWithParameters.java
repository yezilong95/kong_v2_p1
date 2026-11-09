package org.bouncycastle.jcajce;

import defpackage.z91;
import javax.crypto.interfaces.PBEKey;

/* JADX INFO: loaded from: classes.dex */
public class PKCS12KeyWithParameters extends PKCS12Key implements PBEKey {
    public final byte[] G;
    public final int H;

    public PKCS12KeyWithParameters(char[] cArr, boolean z, byte[] bArr, int i) {
        super(cArr, z);
        this.G = z91.l(bArr);
        this.H = i;
    }

    @Override // javax.crypto.interfaces.PBEKey
    public int getIterationCount() {
        return this.H;
    }

    @Override // javax.crypto.interfaces.PBEKey
    public byte[] getSalt() {
        return this.G;
    }

    public PKCS12KeyWithParameters(char[] cArr, byte[] bArr, int i) {
        super(cArr);
        this.G = z91.l(bArr);
        this.H = i;
    }
}
