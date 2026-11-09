package org.bouncycastle.jcajce;

import defpackage.td;
import defpackage.z91;
import javax.crypto.interfaces.PBEKey;

/* JADX INFO: loaded from: classes.dex */
public class PBKDF2KeyWithParameters extends PBKDF2Key implements PBEKey {
    public final byte[] G;
    public final int H;

    public PBKDF2KeyWithParameters(char[] cArr, td tdVar, byte[] bArr, int i) {
        super(cArr, tdVar);
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
}
