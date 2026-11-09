package defpackage;

import java.math.BigInteger;
import java.util.Arrays;

/* JADX INFO: loaded from: classes.dex */
public final class br extends zq {
    public final int U;
    public final int V;
    public final int[] W;
    public final za0 X;

    public br(int i, int[] iArr, za0 za0Var) {
        super(23);
        this.V = i;
        this.U = iArr.length == 1 ? 2 : 3;
        this.W = iArr;
        this.X = za0Var;
    }

    @Override // defpackage.l21
    public final l21 A(l21 l21Var) {
        return k0(l21Var.Y());
    }

    @Override // defpackage.l21
    public final int N() {
        return this.V;
    }

    @Override // defpackage.l21
    public final l21 T0() {
        za0 za0Var = this.X;
        for (long j : za0Var.E) {
            if (j != 0) {
                return za0Var.m() ? this : b1(this.V - 1);
            }
        }
        return this;
    }

    @Override // defpackage.l21
    public final l21 U0() {
        za0 za0Var = this.X;
        int iK = za0Var.k();
        int i = this.V;
        int[] iArr = this.W;
        if (iK != 0) {
            int i2 = iK << 1;
            long[] jArr = new long[i2];
            int i3 = 0;
            while (i3 < i2) {
                long j = za0Var.E[i3 >>> 1];
                int i4 = i3 + 1;
                jArr[i3] = za0.l((int) j);
                i3 += 2;
                jArr[i4] = za0.l((int) (j >>> 32));
            }
            za0Var = new za0(jArr, za0.p(jArr, i2, i, iArr));
        }
        return new br(i, iArr, za0Var);
    }

    @Override // defpackage.l21
    public final l21 Y() {
        int i;
        za0 za0Var = this.X;
        int iF = za0Var.f();
        if (iF == 0) {
            throw new IllegalStateException();
        }
        int i2 = this.V;
        int[] iArr = this.W;
        int i3 = 1;
        if (iF != 1) {
            za0 za0Var2 = (za0) za0Var.clone();
            int i4 = (i2 + 63) >>> 6;
            za0 za0Var3 = new za0(i4);
            long[] jArr = za0Var3.E;
            za0.h(jArr, i2);
            int length = iArr.length;
            while (true) {
                length--;
                if (length < 0) {
                    break;
                }
                za0.h(jArr, iArr[length]);
            }
            int iG = 0;
            za0.h(jArr, 0);
            za0 za0Var4 = new za0(i4);
            za0Var4.E[0] = 1;
            za0 za0Var5 = new za0(i4);
            int[] iArr2 = new int[2];
            iArr2[0] = iF;
            iArr2[1] = i2 + 1;
            za0[] za0VarArr = {za0Var2, za0Var3};
            int[] iArr3 = new int[2];
            iArr3[0] = 1;
            iArr3[1] = 0;
            za0[] za0VarArr2 = {za0Var4, za0Var5};
            int i5 = iArr2[1];
            int i6 = i5 - iArr2[0];
            while (true) {
                if (i6 < 0) {
                    i6 = -i6;
                    iArr2[i3] = i5;
                    iArr3[i3] = iG;
                    i3 = 1 - i3;
                    i5 = iArr2[i3];
                    iG = iArr3[i3];
                }
                i = 1 - i3;
                za0VarArr[i3].b(za0VarArr[i], iArr2[i], i6);
                int iG2 = za0VarArr[i3].g(i5);
                if (iG2 == 0) {
                    break;
                }
                int i7 = iArr3[i];
                za0VarArr2[i3].b(za0VarArr2[i], i7, i6);
                int i8 = i7 + i6;
                if (i8 > iG) {
                    iG = i8;
                } else if (i8 == iG) {
                    iG = za0VarArr2[i3].g(iG);
                }
                i6 += iG2 - i5;
                i5 = iG2;
            }
            za0Var = za0VarArr2[i];
        }
        return new br(i2, iArr, za0Var);
    }

    @Override // defpackage.l21
    public final l21 a1(l21 l21Var, l21 l21Var2) {
        za0 za0Var;
        za0 za0Var2 = ((br) l21Var).X;
        za0 za0Var3 = ((br) l21Var2).X;
        za0 za0Var4 = this.X;
        int iK = za0Var4.k();
        if (iK == 0) {
            za0Var = za0Var4;
        } else {
            int i = iK << 1;
            long[] jArr = new long[i];
            int i2 = 0;
            while (i2 < i) {
                long j = za0Var4.E[i2 >>> 1];
                int i3 = i2 + 1;
                jArr[i2] = za0.l((int) j);
                i2 += 2;
                jArr[i3] = za0.l((int) (j >>> 32));
            }
            za0Var = new za0(jArr, i);
        }
        int i4 = this.V;
        int[] iArr = this.W;
        za0 za0VarN = za0Var2.n(za0Var3);
        if (za0Var == za0Var4) {
            za0Var = (za0) za0Var.clone();
        }
        za0Var.c(za0VarN);
        long[] jArr2 = za0Var.E;
        int iP = za0.p(jArr2, jArr2.length, i4, iArr);
        if (iP < jArr2.length) {
            long[] jArr3 = new long[iP];
            za0Var.E = jArr3;
            System.arraycopy(jArr2, 0, jArr3, 0, iP);
        }
        return new br(i4, iArr, za0Var);
    }

    @Override // defpackage.l21
    public final boolean b0() {
        return this.X.m();
    }

    @Override // defpackage.l21
    public final l21 b1(int i) {
        if (i < 1) {
            return this;
        }
        za0 za0Var = this.X;
        int iK = za0Var.k();
        int i2 = this.V;
        int[] iArr = this.W;
        if (iK != 0) {
            int i3 = ((i2 + 63) >>> 6) << 1;
            long[] jArr = new long[i3];
            System.arraycopy(za0Var.E, 0, jArr, 0, iK);
            while (true) {
                i--;
                if (i < 0) {
                    break;
                }
                int i4 = iK << 1;
                while (true) {
                    iK--;
                    if (iK >= 0) {
                        long j = jArr[iK];
                        jArr[i4 - 1] = za0.l((int) (j >>> 32));
                        i4 -= 2;
                        jArr[i4] = za0.l((int) j);
                    }
                }
                iK = za0.p(jArr, i3, i2, iArr);
            }
            za0Var = new za0(jArr, iK);
        }
        return new br(i2, iArr, za0Var);
    }

    @Override // defpackage.l21
    public final boolean d0() {
        for (long j : this.X.E) {
            if (j != 0) {
                return false;
            }
        }
        return true;
    }

    @Override // defpackage.l21
    public final l21 e(l21 l21Var) {
        za0 za0Var = (za0) this.X.clone();
        za0Var.c(((br) l21Var).X);
        return new br(this.V, this.W, za0Var);
    }

    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof br)) {
            return false;
        }
        br brVar = (br) obj;
        return this.V == brVar.V && this.U == brVar.U && Arrays.equals(this.W, brVar.W) && this.X.equals(brVar.X);
    }

    @Override // defpackage.l21
    public final boolean g1() {
        long[] jArr = this.X.E;
        return jArr.length > 0 && (1 & jArr[0]) != 0;
    }

    @Override // defpackage.l21
    public final l21 h() {
        za0 za0Var;
        za0 za0Var2 = this.X;
        if (za0Var2.E.length == 0) {
            za0Var = new za0(new long[]{1});
        } else {
            int iMax = Math.max(1, za0Var2.k());
            long[] jArr = new long[iMax];
            long[] jArr2 = za0Var2.E;
            System.arraycopy(jArr2, 0, jArr, 0, Math.min(jArr2.length, iMax));
            jArr[0] = 1 ^ jArr[0];
            za0Var = new za0(jArr);
        }
        return new br(this.V, this.W, za0Var);
    }

    @Override // defpackage.l21
    public final BigInteger h1() {
        za0 za0Var = this.X;
        int iK = za0Var.k();
        if (iK == 0) {
            return qq.a;
        }
        int i = iK - 1;
        long j = za0Var.E[i];
        byte[] bArr = new byte[8];
        int i2 = 0;
        boolean z = false;
        for (int i3 = 7; i3 >= 0; i3--) {
            byte b = (byte) (j >>> (i3 * 8));
            if (z || b != 0) {
                bArr[i2] = b;
                i2++;
                z = true;
            }
        }
        byte[] bArr2 = new byte[(i * 8) + i2];
        for (int i4 = 0; i4 < i2; i4++) {
            bArr2[i4] = bArr[i4];
        }
        for (int i5 = iK - 2; i5 >= 0; i5--) {
            long j2 = za0Var.E[i5];
            int i6 = 7;
            while (i6 >= 0) {
                bArr2[i2] = (byte) (j2 >>> (i6 * 8));
                i6--;
                i2++;
            }
        }
        return new BigInteger(1, bArr2);
    }

    public final int hashCode() {
        int i;
        int iHashCode = this.X.hashCode() ^ this.V;
        int[] iArr = this.W;
        if (iArr == null) {
            i = 0;
        } else {
            int length = iArr.length;
            int i2 = length + 1;
            while (true) {
                length--;
                if (length < 0) {
                    break;
                }
                i2 = (i2 * 257) ^ iArr[length];
            }
            i = i2;
        }
        return iHashCode ^ i;
    }

    /* JADX WARN: Removed duplicated region for block: B:8:0x0020 A[PHI: r2
      0x0020: PHI (r2v25 za0) = (r2v2 za0), (r2v3 za0) binds: [B:7:0x001e, B:14:0x0047] A[DONT_GENERATE, DONT_INLINE]] */
    @Override // defpackage.l21
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final defpackage.l21 k0(defpackage.l21 r29) {
        /*
            Method dump skipped, instruction units count: 292
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.br.k0(l21):l21");
    }

    @Override // defpackage.l21
    public final int n() {
        return this.X.f();
    }

    @Override // defpackage.l21
    public final l21 o0(l21 l21Var, l21 l21Var2, l21 l21Var3) {
        return p0(l21Var, l21Var2, l21Var3);
    }

    @Override // defpackage.l21
    public final l21 p0(l21 l21Var, l21 l21Var2, l21 l21Var3) {
        za0 za0Var = ((br) l21Var).X;
        za0 za0Var2 = ((br) l21Var2).X;
        za0 za0Var3 = ((br) l21Var3).X;
        za0 za0Var4 = this.X;
        int i = this.V;
        int[] iArr = this.W;
        za0 za0VarN = za0Var4.n(za0Var);
        za0 za0VarN2 = za0Var2.n(za0Var3);
        if (za0VarN == za0Var4 || za0VarN == za0Var) {
            za0VarN = (za0) za0VarN.clone();
        }
        za0VarN.c(za0VarN2);
        long[] jArr = za0VarN.E;
        int iP = za0.p(jArr, jArr.length, i, iArr);
        if (iP < jArr.length) {
            long[] jArr2 = new long[iP];
            za0VarN.E = jArr2;
            System.arraycopy(jArr, 0, jArr2, 0, iP);
        }
        return new br(i, iArr, za0VarN);
    }

    @Override // defpackage.l21
    public final l21 q0() {
        return this;
    }
}
