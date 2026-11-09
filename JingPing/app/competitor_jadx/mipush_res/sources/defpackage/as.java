package defpackage;

/* JADX INFO: loaded from: classes.dex */
public final class as extends s8 {
    public final z8 F;

    public as(z8 z8Var) {
        super(false);
        this.F = z8Var;
    }

    public final byte[] getEncoded() {
        byte[] bArr = new byte[32];
        l21.D(this.F, bArr, 0);
        return bArr;
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public as(byte[] bArr) {
        boolean z;
        super(false);
        if (bArr.length != 32) {
            throw new IllegalArgumentException("'buf' must have length 32");
        }
        byte[] bArr2 = new byte[32];
        System.arraycopy(bArr, 0, bArr2, 0, 32);
        int I = t40.I(28, bArr2) & Integer.MAX_VALUE;
        int[] iArr = l21.n;
        int i = iArr[7] ^ I;
        int[] iArr2 = l21.o;
        int i2 = iArr2[7] ^ I;
        int[] iArr3 = l21.p;
        int i3 = iArr3[7] ^ I;
        for (int i4 = 6; i4 > 0; i4--) {
            int I2 = t40.I(i4 * 4, bArr2);
            I |= I2;
            i |= iArr[i4] ^ I2;
            i2 |= iArr2[i4] ^ I2;
            i3 |= I2 ^ iArr3[i4];
        }
        int I3 = t40.I(0, bArr2);
        if ((I != 0 || I3 - Integer.MIN_VALUE > -2147483647) && (i != 0 || Integer.MIN_VALUE + I3 < iArr[0] - (-2147483647))) {
            z = (((iArr2[0] ^ I3) | i2) != 0) & (((iArr3[0] ^ I3) | i3) != 0);
        } else {
            z = false;
        }
        z8 z8Var = null;
        if (z) {
            wr wrVar = new wr(0);
            if (l21.z(bArr2, false, wrVar)) {
                int[] iArr4 = new int[20];
                t40.A(0, 0, wrVar.a, iArr4);
                t40.A(0, 10, wrVar.b, iArr4);
                z8Var = new z8(16, iArr4);
            }
        }
        if (z8Var == null) {
            throw new IllegalArgumentException("invalid public key");
        }
        this.F = z8Var;
    }
}
