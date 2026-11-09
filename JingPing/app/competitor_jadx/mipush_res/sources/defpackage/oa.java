package defpackage;

import java.math.BigInteger;
import java.security.SecureRandom;

/* JADX INFO: loaded from: classes.dex */
public abstract class oa {
    public static final BigInteger a = BigInteger.valueOf(0);
    public static final BigInteger b = BigInteger.valueOf(1);

    static {
        BigInteger.valueOf(2L);
        BigInteger.valueOf(3L);
        new BigInteger("8138e8a0fcf3a4e84a771d40fd305d7f4aa59306d7251de54d98af8fe95729a1f73d893fa424cd2edc8636a6c3285e022b0e3866a565ae8108eed8591cd4fe8d2ce86165a978d719ebf647f362d33fca29cd179fb42401cbaf3df0c614056f9c8f3cfd51e474afb6bc6974f78db8aba8e9e517fded658591ab7502bd41849462f", 16);
        BigInteger.valueOf(743L).bitLength();
    }

    public static byte[] a(int i, BigInteger bigInteger) {
        byte[] byteArray = bigInteger.toByteArray();
        if (byteArray.length == i) {
            return byteArray;
        }
        int i2 = 0;
        if (byteArray[0] == 0 && byteArray.length != 1) {
            i2 = 1;
        }
        int length = byteArray.length - i2;
        if (length > i) {
            throw new IllegalArgumentException("standard length exceeded for value");
        }
        byte[] bArr = new byte[i];
        System.arraycopy(byteArray, i2, bArr, i - length, length);
        return bArr;
    }

    public static BigInteger b(int i, SecureRandom secureRandom) {
        if (i < 1) {
            throw new IllegalArgumentException("bitLength must be at least 1");
        }
        int i2 = (i + 7) / 8;
        byte[] bArr = new byte[i2];
        secureRandom.nextBytes(bArr);
        bArr[0] = (byte) (bArr[0] & ((byte) (255 >>> ((i2 * 8) - i))));
        return new BigInteger(1, bArr);
    }

    public static BigInteger c(BigInteger bigInteger, BigInteger bigInteger2, SecureRandom secureRandom) {
        BigInteger bigIntegerB;
        int iCompareTo = bigInteger.compareTo(bigInteger2);
        if (iCompareTo >= 0) {
            if (iCompareTo <= 0) {
                return bigInteger;
            }
            throw new IllegalArgumentException("'min' may not be greater than 'max'");
        }
        if (bigInteger.bitLength() > bigInteger2.bitLength() / 2) {
            bigIntegerB = c(a, bigInteger2.subtract(bigInteger), secureRandom);
        } else {
            for (int i = 0; i < 1000; i++) {
                BigInteger bigIntegerB2 = b(bigInteger2.bitLength(), secureRandom);
                if (bigIntegerB2.compareTo(bigInteger) >= 0 && bigIntegerB2.compareTo(bigInteger2) <= 0) {
                    return bigIntegerB2;
                }
            }
            bigIntegerB = b(bigInteger2.subtract(bigInteger).bitLength() - 1, secureRandom);
        }
        return bigIntegerB.add(bigInteger);
    }

    public static BigInteger d(byte[] bArr, int i, int i2) {
        if (i != 0 || i2 != bArr.length) {
            byte[] bArr2 = new byte[i2];
            System.arraycopy(bArr, i, bArr2, 0, i2);
            bArr = bArr2;
        }
        return new BigInteger(1, bArr);
    }
}
