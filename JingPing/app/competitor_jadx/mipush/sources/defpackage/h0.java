package defpackage;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.math.BigInteger;
import java.util.Arrays;
import java.util.concurrent.ConcurrentHashMap;
import kotlin.uuid.Uuid;

/* JADX INFO: loaded from: classes.dex */
public final class h0 extends g0 {
    public static final ConcurrentHashMap G;
    public final byte[] E;
    public String F = null;

    static {
        new o(16, h0.class);
        G = new ConcurrentHashMap();
    }

    public h0(byte[] bArr) {
        this.E = bArr;
    }

    public static h0 o(byte[] bArr, boolean z) {
        if (bArr.length > 4096) {
            throw new IllegalArgumentException("exceeded relative OID contents length limit");
        }
        h0 h0Var = (h0) G.get(new c0(bArr));
        if (h0Var != null) {
            return h0Var;
        }
        if (!p(bArr)) {
            throw new IllegalArgumentException("invalid relative OID contents");
        }
        if (z) {
            bArr = z91.l(bArr);
        }
        return new h0(bArr);
    }

    public static boolean p(byte[] bArr) {
        if (bArr.length < 1) {
            return false;
        }
        boolean z = true;
        for (int i = 0; i < bArr.length; i++) {
            if (z && (bArr[i] & 255) == 128) {
                return false;
            }
            z = (bArr[i] & 128) == 0;
        }
        return z;
    }

    public static boolean q(int i, String str) {
        int length = str.length();
        int i2 = 0;
        while (true) {
            int i3 = length - 1;
            if (i3 < i) {
                if (i2 == 0 || (i2 > 1 && str.charAt(length) == '0')) {
                    break;
                }
                return true;
            }
            char cCharAt = str.charAt(i3);
            if (cCharAt == '.') {
                if (i2 == 0 || (i2 > 1 && str.charAt(length) == '0')) {
                    break;
                }
                i2 = 0;
                length = i3;
            } else {
                if ('0' > cCharAt || cCharAt > '9') {
                    break;
                }
                i2++;
                length = i3;
            }
        }
        return false;
    }

    public static void r(ByteArrayOutputStream byteArrayOutputStream, long j) {
        byte[] bArr = new byte[9];
        int i = 8;
        bArr[8] = (byte) (((int) j) & 127);
        while (j >= 128) {
            j >>= 7;
            i--;
            bArr[i] = (byte) (((int) j) | Uuid.SIZE_BITS);
        }
        byteArrayOutputStream.write(bArr, i, 9 - i);
    }

    public static void s(ByteArrayOutputStream byteArrayOutputStream, BigInteger bigInteger) {
        int iBitLength = (bigInteger.bitLength() + 6) / 7;
        if (iBitLength == 0) {
            byteArrayOutputStream.write(0);
            return;
        }
        byte[] bArr = new byte[iBitLength];
        int i = iBitLength - 1;
        for (int i2 = i; i2 >= 0; i2--) {
            bArr[i2] = (byte) (bigInteger.intValue() | Uuid.SIZE_BITS);
            bigInteger = bigInteger.shiftRight(7);
        }
        bArr[i] = (byte) (bArr[i] & 127);
        byteArrayOutputStream.write(bArr, 0, iBitLength);
    }

    @Override // defpackage.g0
    public final boolean g(g0 g0Var) {
        if (this == g0Var) {
            return true;
        }
        if (!(g0Var instanceof h0)) {
            return false;
        }
        return Arrays.equals(this.E, ((h0) g0Var).E);
    }

    @Override // defpackage.g0
    public final void h(p50 p50Var, boolean z) throws IOException {
        p50Var.G(13, z, this.E);
    }

    @Override // defpackage.g0, defpackage.a0
    public final int hashCode() {
        return z91.E(this.E);
    }

    @Override // defpackage.g0
    public final boolean i() {
        return false;
    }

    @Override // defpackage.g0
    public final int j(boolean z) {
        return p50.x(this.E.length, z);
    }

    public final String toString() {
        String str;
        synchronized (this) {
            try {
                if (this.F == null) {
                    byte[] bArr = this.E;
                    StringBuilder sb = new StringBuilder();
                    boolean z = true;
                    BigInteger bigIntegerShiftLeft = null;
                    long j = 0;
                    for (int i = 0; i != bArr.length; i++) {
                        byte b = bArr[i];
                        if (j <= 72057594037927808L) {
                            long j2 = j + ((long) (b & 127));
                            if ((b & 128) == 0) {
                                if (z) {
                                    z = false;
                                } else {
                                    sb.append('.');
                                }
                                sb.append(j2);
                                j = 0;
                            } else {
                                j = j2 << 7;
                            }
                        } else {
                            if (bigIntegerShiftLeft == null) {
                                bigIntegerShiftLeft = BigInteger.valueOf(j);
                            }
                            BigInteger bigIntegerOr = bigIntegerShiftLeft.or(BigInteger.valueOf(b & 127));
                            if ((b & 128) == 0) {
                                if (z) {
                                    z = false;
                                } else {
                                    sb.append('.');
                                }
                                sb.append(bigIntegerOr);
                                bigIntegerShiftLeft = null;
                                j = 0;
                            } else {
                                bigIntegerShiftLeft = bigIntegerOr.shiftLeft(7);
                            }
                        }
                    }
                    this.F = sb.toString();
                }
                str = this.F;
            } catch (Throwable th) {
                throw th;
            }
        }
        return str;
    }
}
