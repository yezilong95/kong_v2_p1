package org.bouncycastle.jcajce;

import defpackage.la0;

/* JADX INFO: loaded from: classes.dex */
public class PKCS12Key implements PBKDFKey {
    public final char[] E;
    public final boolean F;

    public PKCS12Key(char[] cArr) {
        this(cArr, false);
    }

    @Override // java.security.Key
    public String getAlgorithm() {
        return "PKCS12";
    }

    @Override // java.security.Key
    public byte[] getEncoded() {
        boolean z = this.F;
        char[] cArr = this.E;
        return (z && cArr.length == 0) ? new byte[2] : la0.a(cArr);
    }

    @Override // java.security.Key
    public String getFormat() {
        return "PKCS12";
    }

    public char[] getPassword() {
        return this.E;
    }

    public PKCS12Key(char[] cArr, boolean z) {
        cArr = cArr == null ? new char[0] : cArr;
        char[] cArr2 = new char[cArr.length];
        this.E = cArr2;
        this.F = z;
        System.arraycopy(cArr, 0, cArr2, 0, cArr.length);
    }
}
