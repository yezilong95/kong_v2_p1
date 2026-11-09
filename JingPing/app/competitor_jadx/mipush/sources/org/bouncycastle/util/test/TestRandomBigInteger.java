package org.bouncycastle.util.test;

import defpackage.jw;
import defpackage.ma0;
import defpackage.nw;
import defpackage.oa;
import java.math.BigInteger;

/* JADX INFO: loaded from: classes.dex */
public class TestRandomBigInteger extends FixedSecureRandom {
    /* JADX WARN: Illegal instructions before constructor call */
    public TestRandomBigInteger(int i, byte[] bArr) {
        int i2;
        int i3;
        int i4 = (i + 7) / 8;
        int length = bArr.length;
        boolean z = FixedSecureRandom.G;
        if (i4 > length) {
            byte[] bArr2 = new byte[i4];
            System.arraycopy(bArr, 0, bArr2, i4 - bArr.length, bArr.length);
            if (z && (i3 = i % 8) != 0) {
                ma0.K(bArr2, ma0.g(0, bArr2) << (8 - i3), 0);
            }
            bArr = bArr2;
        } else if (z && i < bArr.length * 8 && (i2 = i % 8) != 0) {
            ma0.K(bArr, ma0.g(0, bArr) << (8 - i2), 0);
        }
        super(new nw[]{new jw(bArr)});
    }

    public TestRandomBigInteger(String str) {
        this(str, 10);
    }

    public TestRandomBigInteger(String str, int i) {
        BigInteger bigInteger = new BigInteger(str, i);
        BigInteger bigInteger2 = oa.a;
        byte[] byteArray = bigInteger.toByteArray();
        if (byteArray[0] == 0 && byteArray.length != 1) {
            int length = byteArray.length - 1;
            byte[] bArr = new byte[length];
            System.arraycopy(byteArray, 1, bArr, 0, length);
            byteArray = bArr;
        }
        super(new nw[]{new jw(byteArray)});
    }

    public TestRandomBigInteger(byte[] bArr) {
        super(new nw[]{new jw(bArr)});
    }
}
