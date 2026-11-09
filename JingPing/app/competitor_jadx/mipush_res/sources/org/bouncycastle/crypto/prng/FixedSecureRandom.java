package org.bouncycastle.crypto.prng;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.security.SecureRandom;

/* JADX INFO: loaded from: classes.dex */
public class FixedSecureRandom extends SecureRandom {
    public final byte[] E;
    public int F;
    public int G;

    public FixedSecureRandom(boolean z, byte[] bArr) {
        this(z, new byte[][]{bArr});
    }

    public final int a() {
        int i = this.F;
        this.F = i + 1;
        return this.E[i] & 255;
    }

    @Override // java.security.SecureRandom
    public byte[] generateSeed(int i) {
        byte[] bArr = new byte[i];
        nextBytes(bArr);
        return bArr;
    }

    public boolean isExhausted() {
        return this.F == this.E.length;
    }

    @Override // java.security.SecureRandom, java.util.Random
    public void nextBytes(byte[] bArr) {
        System.arraycopy(this.E, this.F, bArr, 0, bArr.length);
        this.F += bArr.length;
    }

    @Override // java.util.Random
    public int nextInt() {
        int iA = (a() << 24) | (a() << 16);
        int i = this.G;
        if (i == 2) {
            this.G = i - 1;
        } else {
            iA |= a() << 8;
        }
        int i2 = this.G;
        if (i2 != 1) {
            return iA | a();
        }
        this.G = i2 - 1;
        return iA;
    }

    @Override // java.util.Random
    public long nextLong() {
        return (((long) a()) << 56) | (((long) a()) << 48) | (((long) a()) << 40) | (((long) a()) << 32) | (((long) a()) << 24) | (((long) a()) << 16) | (((long) a()) << 8) | ((long) a());
    }

    public FixedSecureRandom(boolean z, byte[][] bArr) {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        for (int i = 0; i != bArr.length; i++) {
            try {
                byteArrayOutputStream.write(bArr[i]);
            } catch (IOException unused) {
                throw new IllegalArgumentException("can't save value array.");
            }
        }
        byte[] byteArray = byteArrayOutputStream.toByteArray();
        this.E = byteArray;
        if (z) {
            this.G = byteArray.length % 4;
        }
    }

    public FixedSecureRandom(byte[] bArr) {
        this(false, new byte[][]{bArr});
    }

    public FixedSecureRandom(byte[][] bArr) {
        this(false, bArr);
    }
}
