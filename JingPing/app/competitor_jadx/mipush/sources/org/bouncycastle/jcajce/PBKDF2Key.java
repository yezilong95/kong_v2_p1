package org.bouncycastle.jcajce;

import defpackage.td;

/* JADX INFO: loaded from: classes.dex */
public class PBKDF2Key implements PBKDFKey {
    public final char[] E;
    public final td F;

    public PBKDF2Key(char[] cArr, td tdVar) {
        this.E = cArr == null ? null : (char[]) cArr.clone();
        this.F = tdVar;
    }

    @Override // java.security.Key
    public String getAlgorithm() {
        return "PBKDF2";
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
