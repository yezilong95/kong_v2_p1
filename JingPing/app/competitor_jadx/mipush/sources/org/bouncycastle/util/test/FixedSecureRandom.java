package org.bouncycastle.util.test;

import defpackage.jw;
import defpackage.kw;
import defpackage.lw;
import defpackage.mw;
import defpackage.nw;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.math.BigInteger;
import java.security.SecureRandom;
import kotlin.uuid.Uuid;

/* JADX INFO: loaded from: classes.dex */
public class FixedSecureRandom extends SecureRandom {
    public static final boolean G;
    public static final boolean H;
    public static final boolean I;
    public final byte[] E;
    public int F;

    static {
        BigInteger bigInteger = new BigInteger("01020304ffffffff0506070811111111", 16);
        BigInteger bigInteger2 = new BigInteger("1111111105060708ffffffff01020304", 16);
        BigInteger bigInteger3 = new BigInteger("3020104ffffffff05060708111111", 16);
        BigInteger bigInteger4 = new BigInteger(Uuid.SIZE_BITS, new mw());
        BigInteger bigInteger5 = new BigInteger(120, new mw());
        G = bigInteger4.equals(bigInteger2);
        I = bigInteger4.equals(bigInteger);
        H = bigInteger5.equals(bigInteger3);
    }

    public FixedSecureRandom(byte[] bArr) {
        this(new nw[]{new kw(bArr)});
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
        return (a() << 24) | (a() << 16) | (a() << 8) | a();
    }

    @Override // java.util.Random
    public long nextLong() {
        return (((long) a()) << 56) | (((long) a()) << 48) | (((long) a()) << 40) | (((long) a()) << 32) | (((long) a()) << 24) | (((long) a()) << 16) | (((long) a()) << 8) | ((long) a());
    }

    public FixedSecureRandom(nw[] nwVarArr) {
        super(null, new lw());
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        int i = 0;
        if (!I) {
            if (!G) {
                throw new IllegalStateException("Unrecognized BigInteger implementation");
            }
            for (int i2 = 0; i2 != nwVarArr.length; i2++) {
                try {
                    nw nwVar = nwVarArr[i2];
                    if (nwVar instanceof jw) {
                        byte[] bArr = nwVar.a;
                        int length = bArr.length - (bArr.length % 4);
                        int i3 = 0;
                        while (i3 < length) {
                            i3 += 4;
                            byteArrayOutputStream.write(bArr, bArr.length - i3, 4);
                        }
                        if (bArr.length - length != 0) {
                            for (int i4 = 0; i4 != 4 - (bArr.length - length); i4++) {
                                byteArrayOutputStream.write(0);
                            }
                        }
                        for (int i5 = 0; i5 != bArr.length - length; i5++) {
                            byteArrayOutputStream.write(bArr[length + i5]);
                        }
                    } else {
                        byteArrayOutputStream.write(nwVar.a);
                    }
                } catch (IOException unused) {
                    throw new IllegalArgumentException("can't save value source.");
                }
            }
        } else if (H) {
            while (i != nwVarArr.length) {
                try {
                    nw nwVar2 = nwVarArr[i];
                    if (nwVar2 instanceof jw) {
                        byte[] bArr2 = nwVar2.a;
                        int length2 = bArr2.length - (bArr2.length % 4);
                        for (int length3 = (bArr2.length - length2) - 1; length3 >= 0; length3--) {
                            byteArrayOutputStream.write(bArr2[length3]);
                        }
                        for (int length4 = bArr2.length - length2; length4 < bArr2.length; length4 += 4) {
                            byteArrayOutputStream.write(bArr2, length4, 4);
                        }
                    } else {
                        byteArrayOutputStream.write(nwVar2.a);
                    }
                    i++;
                } catch (IOException unused2) {
                    throw new IllegalArgumentException("can't save value source.");
                }
            }
        } else {
            while (i != nwVarArr.length) {
                try {
                    byteArrayOutputStream.write(nwVarArr[i].a);
                    i++;
                } catch (IOException unused3) {
                    throw new IllegalArgumentException("can't save value source.");
                }
            }
        }
        this.E = byteArrayOutputStream.toByteArray();
    }

    /* JADX WARN: Illegal instructions before constructor call */
    public FixedSecureRandom(byte[][] bArr) {
        kw[] kwVarArr = new kw[bArr.length];
        for (int i = 0; i != bArr.length; i++) {
            kwVarArr[i] = new kw(bArr[i]);
        }
        this(kwVarArr);
    }
}
