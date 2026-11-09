package org.bouncycastle.jcajce.provider.symmetric.util;

import defpackage.d0;
import defpackage.jx0;
import defpackage.la0;
import defpackage.le;
import defpackage.q70;
import defpackage.z91;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Arrays;
import java.util.concurrent.atomic.AtomicBoolean;
import javax.crypto.interfaces.PBEKey;
import javax.crypto.spec.PBEKeySpec;
import javax.security.auth.Destroyable;

/* JADX INFO: loaded from: classes.dex */
public class BCPBEKey implements PBEKey, Destroyable {
    public final AtomicBoolean E;
    public final String F;
    public final d0 G;
    public final int H;
    public final int I;
    public final char[] J;
    public final byte[] K;
    public final int L;
    public final le M;

    public BCPBEKey(String str, d0 d0Var, int i, int i2, int i3, int i4, PBEKeySpec pBEKeySpec, le leVar) {
        this.E = new AtomicBoolean(false);
        this.F = str;
        this.G = d0Var;
        this.H = i;
        this.I = i4;
        this.J = pBEKeySpec.getPassword();
        this.L = pBEKeySpec.getIterationCount();
        this.K = pBEKeySpec.getSalt();
        this.M = leVar;
    }

    public static void a(BCPBEKey bCPBEKey) {
        if (bCPBEKey.isDestroyed()) {
            throw new IllegalStateException("key has been destroyed");
        }
    }

    @Override // javax.security.auth.Destroyable
    public void destroy() {
        if (this.E.getAndSet(true)) {
            return;
        }
        char[] cArr = this.J;
        if (cArr != null) {
            Arrays.fill(cArr, (char) 0);
        }
        byte[] bArr = this.K;
        if (bArr != null) {
            Arrays.fill(bArr, (byte) 0);
        }
    }

    @Override // java.security.Key
    public String getAlgorithm() {
        a(this);
        return this.F;
    }

    @Override // java.security.Key
    public byte[] getEncoded() {
        byte[] byteArray;
        le leVar = this.M;
        if (leVar != null) {
            byteArray = ((q70) leVar).E;
        } else {
            int i = this.H;
            char[] cArr = this.J;
            if (i == 2) {
                byteArray = la0.a(cArr);
            } else {
                if (i == 5) {
                    if (cArr != null) {
                        String str = jx0.a;
                        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                        try {
                            jx0.d(cArr, byteArrayOutputStream);
                            byteArray = byteArrayOutputStream.toByteArray();
                        } catch (IOException unused) {
                            throw new IllegalStateException("cannot encode string to byte array!");
                        }
                    } else {
                        byteArray = new byte[0];
                    }
                } else if (cArr != null) {
                    int length = cArr.length;
                    byte[] bArr = new byte[length];
                    for (int i2 = 0; i2 != length; i2++) {
                        bArr[i2] = (byte) cArr[i2];
                    }
                    byteArray = bArr;
                } else {
                    byteArray = new byte[0];
                }
            }
        }
        a(this);
        return byteArray;
    }

    @Override // java.security.Key
    public String getFormat() {
        a(this);
        return "RAW";
    }

    @Override // javax.crypto.interfaces.PBEKey
    public int getIterationCount() {
        a(this);
        return this.L;
    }

    public int getIvSize() {
        a(this);
        return this.I;
    }

    public d0 getOID() {
        a(this);
        return this.G;
    }

    public le getParam() {
        a(this);
        return this.M;
    }

    @Override // javax.crypto.interfaces.PBEKey
    public char[] getPassword() {
        char[] cArr = this.J;
        char[] cArr2 = cArr == null ? null : (char[]) cArr.clone();
        a(this);
        if (cArr2 != null) {
            return cArr2;
        }
        throw new IllegalStateException("no password available");
    }

    @Override // javax.crypto.interfaces.PBEKey
    public byte[] getSalt() {
        byte[] bArrL = z91.l(this.K);
        a(this);
        return bArrL;
    }

    @Override // javax.security.auth.Destroyable
    public boolean isDestroyed() {
        return this.E.get();
    }

    public void setTryWrongPKCS12Zero(boolean z) {
    }

    public BCPBEKey(String str, le leVar) {
        this.E = new AtomicBoolean(false);
        this.F = str;
        this.M = leVar;
        this.J = null;
        this.L = -1;
        this.K = null;
    }
}
