package defpackage;

/* JADX INFO: loaded from: classes.dex */
public final class ds extends s8 {
    public final p50 F;

    public ds(p50 p50Var) {
        super(false);
        this.F = p50Var;
    }

    public final void a(int i, byte[] bArr) {
        p50 p50Var = this.F;
        int[] iArr = (int[]) p50Var.F;
        l21.C(16, i, bArr, iArr);
        l21.C(18, i + 7, bArr, iArr);
        l21.C(20, i + 14, bArr, iArr);
        l21.C(22, i + 21, bArr, iArr);
        l21.C(24, i + 28, bArr, iArr);
        l21.C(26, i + 35, bArr, iArr);
        l21.C(28, i + 42, bArr, iArr);
        l21.C(30, i + 49, bArr, iArr);
        bArr[i + 56] = (byte) ((((int[]) p50Var.F)[0] & 1) << 7);
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public ds(byte[] bArr) {
        p50 p50Var;
        super(false);
        if (bArr.length != 57) {
            throw new IllegalArgumentException("'buf' must have length 57");
        }
        byte[] bArr2 = new byte[57];
        System.arraycopy(bArr, 0, bArr2, 0, 57);
        if ((bArr2[56] & 127) != 0) {
            p50Var = null;
        } else {
            int I = t40.I(52, bArr2);
            int[] iArr = j31.g;
            int i = iArr[13] ^ I;
            int i2 = 12;
            while (true) {
                if (i2 > 0) {
                    int I2 = t40.I(i2 * 4, bArr2);
                    if (i == 0 && I2 - Integer.MIN_VALUE > iArr[i2] - Integer.MIN_VALUE) {
                        break;
                    }
                    I |= I2;
                    i |= iArr[i2] ^ I2;
                    i2--;
                } else {
                    int I3 = t40.I(0, bArr2);
                    if ((I != 0 || I3 - Integer.MIN_VALUE > -2147483647) && (i != 0 || I3 - Integer.MIN_VALUE < iArr[0] - (-2147483647))) {
                        int[] iArr2 = new int[16];
                        int[] iArr3 = new int[16];
                        int i3 = (bArr2[56] & 128) >>> 7;
                        l21.y(0, 0, bArr2, iArr3);
                        l21.y(7, 2, bArr2, iArr3);
                        l21.y(14, 4, bArr2, iArr3);
                        l21.y(21, 6, bArr2, iArr3);
                        l21.y(28, 8, bArr2, iArr3);
                        l21.y(35, 10, bArr2, iArr3);
                        l21.y(42, 12, bArr2, iArr3);
                        l21.y(49, 14, bArr2, iArr3);
                        int[] iArr4 = new int[16];
                        int[] iArr5 = new int[16];
                        l21.S0(iArr3, iArr4);
                        l21.i0(iArr4, iArr5);
                        l21.c1(new int[16], iArr4, iArr4);
                        iArr4[0] = iArr4[0] + 1;
                        iArr5[0] = iArr5[0] + 1;
                        int[] iArr6 = new int[16];
                        int[] iArr7 = new int[16];
                        l21.S0(iArr4, iArr6);
                        l21.j0(iArr6, iArr5, iArr6);
                        l21.S0(iArr6, iArr7);
                        l21.j0(iArr6, iArr4, iArr6);
                        l21.j0(iArr7, iArr4, iArr7);
                        l21.j0(iArr7, iArr5, iArr7);
                        int[] iArr8 = new int[16];
                        int[] iArr9 = new int[16];
                        l21.S0(iArr7, iArr9);
                        l21.j0(iArr7, iArr9, iArr9);
                        int[] iArr10 = new int[16];
                        l21.S0(iArr9, iArr10);
                        l21.j0(iArr7, iArr10, iArr10);
                        int[] iArr11 = new int[16];
                        l21.R0(3, iArr10, iArr11);
                        l21.j0(iArr10, iArr11, iArr11);
                        int[] iArr12 = new int[16];
                        l21.R0(3, iArr11, iArr12);
                        l21.j0(iArr10, iArr12, iArr12);
                        int[] iArr13 = new int[16];
                        l21.R0(9, iArr12, iArr13);
                        l21.j0(iArr12, iArr13, iArr13);
                        int[] iArr14 = new int[16];
                        l21.S0(iArr13, iArr14);
                        l21.j0(iArr7, iArr14, iArr14);
                        int[] iArr15 = new int[16];
                        l21.R0(18, iArr14, iArr15);
                        l21.j0(iArr13, iArr15, iArr15);
                        int[] iArr16 = new int[16];
                        l21.R0(37, iArr15, iArr16);
                        l21.j0(iArr15, iArr16, iArr16);
                        int[] iArr17 = new int[16];
                        l21.R0(37, iArr16, iArr17);
                        l21.j0(iArr15, iArr17, iArr17);
                        int[] iArr18 = new int[16];
                        l21.R0(111, iArr17, iArr18);
                        l21.j0(iArr17, iArr18, iArr18);
                        int[] iArr19 = new int[16];
                        l21.S0(iArr18, iArr19);
                        l21.j0(iArr7, iArr19, iArr19);
                        int[] iArr20 = new int[16];
                        l21.R0(223, iArr19, iArr20);
                        l21.j0(iArr20, iArr18, iArr8);
                        l21.j0(iArr8, iArr6, iArr8);
                        int[] iArr21 = new int[16];
                        l21.S0(iArr8, iArr21);
                        l21.j0(iArr21, iArr5, iArr21);
                        l21.c1(iArr4, iArr21, iArr21);
                        l21.G0(iArr21, 1);
                        l21.G0(iArr21, -1);
                        if (l21.f0(iArr21) != 0) {
                            l21.t(0, 0, iArr8, iArr2);
                            l21.G0(iArr2, 1);
                            l21.G0(iArr2, -1);
                            if (i3 != 1 || l21.f0(iArr2) == 0) {
                                if (i3 != (iArr2[0] & 1)) {
                                    l21.c1(new int[16], iArr2, iArr2);
                                    l21.G0(iArr2, 1);
                                    l21.G0(iArr2, -1);
                                }
                                int[] iArr22 = new int[32];
                                l21.t(0, 0, iArr2, iArr22);
                                l21.t(0, 16, iArr3, iArr22);
                                p50Var = new p50(18, iArr22);
                            }
                        }
                    }
                }
            }
            p50Var = null;
        }
        if (p50Var == null) {
            throw new IllegalArgumentException("invalid public key");
        }
        this.F = p50Var;
    }
}
