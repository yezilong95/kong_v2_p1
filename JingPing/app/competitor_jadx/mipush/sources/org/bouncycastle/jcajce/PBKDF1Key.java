package org.bouncycastle.jcajce;

import defpackage.td;

/* JADX INFO: loaded from: classes.dex */
public class PBKDF1Key implements PBKDFKey {
    public final char[] E;
    public final td F;

    public PBKDF1Key(char[] cArr, td tdVar) {
        char[] cArr2 = new char[cArr.length];
        this.E = cArr2;
        this.F = tdVar;
        System.arraycopy(cArr, 0, cArr2, 0, cArr.length);
    }

    @Override // java.security.Key
    public String getAlgorithm() {
        return "PBKDF1";
    }

    @Override // java.security.Key
    public byte[] getEncoded() {
        return this.F.convert(this.E);
    }

    @Override // java.security.Key
    public String getFormat() {
        return this.F.getType();
    }

    public char[] getPassword() {
        return this.E;
    }
}
