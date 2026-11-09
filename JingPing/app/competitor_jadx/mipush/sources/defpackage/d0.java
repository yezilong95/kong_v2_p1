package defpackage;

import io.nats.client.support.NatsConstants;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.math.BigInteger;
import java.util.Arrays;
import java.util.concurrent.ConcurrentHashMap;

/* JADX INFO: loaded from: classes.dex */
public final class d0 extends g0 {
    public static final o G = new o(13, d0.class);
    public static final ConcurrentHashMap H = new ConcurrentHashMap();
    public final byte[] E;
    public String F;

    public d0(String str) {
        char cCharAt;
        String strSubstring;
        int i;
        String strSubstring2;
        String strSubstring3;
        if (str == null) {
            throw new NullPointerException("'identifier' cannot be null");
        }
        if (str.length() > 16385) {
            throw new IllegalArgumentException("exceeded OID contents length limit");
        }
        if (str.length() < 3 || str.charAt(1) != '.' || (cCharAt = str.charAt(0)) < '0' || cCharAt > '2' || !h0.q(2, str) || !(cCharAt == '2' || str.length() == 3 || str.charAt(3) == '.' || ((str.length() == 4 || str.charAt(4) == '.') && str.charAt(2) < '4'))) {
            throw new IllegalArgumentException(zn.n("string ", str, " not a valid OID"));
        }
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        int iIndexOf = str.indexOf(46, 0);
        if (iIndexOf == -1) {
            strSubstring = str.substring(0);
            i = -1;
        } else {
            strSubstring = str.substring(0, iIndexOf);
            i = iIndexOf + 1;
        }
        int i2 = Integer.parseInt(strSubstring) * 40;
        if (i == -1) {
            strSubstring2 = null;
        } else {
            int iIndexOf2 = str.indexOf(46, i);
            if (iIndexOf2 == -1) {
                strSubstring2 = str.substring(i);
                i = -1;
            } else {
                String strSubstring4 = str.substring(i, iIndexOf2);
                i = 1 + iIndexOf2;
                strSubstring2 = strSubstring4;
            }
        }
        if (strSubstring2.length() <= 18) {
            h0.r(byteArrayOutputStream, Long.parseLong(strSubstring2) + ((long) i2));
        } else {
            h0.s(byteArrayOutputStream, new BigInteger(strSubstring2).add(BigInteger.valueOf(i2)));
        }
        while (i != -1) {
            if (i == -1) {
                strSubstring3 = null;
            } else {
                int iIndexOf3 = str.indexOf(46, i);
                if (iIndexOf3 == -1) {
                    strSubstring3 = str.substring(i);
                    i = -1;
                } else {
                    String strSubstring5 = str.substring(i, iIndexOf3);
                    i = iIndexOf3 + 1;
                    strSubstring3 = strSubstring5;
                }
            }
            if (strSubstring3.length() <= 18) {
                h0.r(byteArrayOutputStream, Long.parseLong(strSubstring3));
            } else {
                h0.s(byteArrayOutputStream, new BigInteger(strSubstring3));
            }
        }
        byte[] byteArray = byteArrayOutputStream.toByteArray();
        p(byteArray.length);
        this.E = byteArray;
        this.F = str;
    }

    public static void p(int i) {
        if (i > 4096) {
            throw new IllegalArgumentException("exceeded OID contents length limit");
        }
    }

    public static d0 q(byte[] bArr, boolean z) {
        p(bArr.length);
        d0 d0Var = (d0) H.get(new c0(bArr));
        if (d0Var != null) {
            return d0Var;
        }
        if (!h0.p(bArr)) {
            throw new IllegalArgumentException("invalid OID contents");
        }
        if (z) {
            bArr = z91.l(bArr);
        }
        return new d0(null, bArr);
    }

    public static d0 s(Object obj) {
        if (obj == null || (obj instanceof d0)) {
            return (d0) obj;
        }
        if (obj instanceof t) {
            g0 g0VarB = ((t) obj).b();
            if (g0VarB instanceof d0) {
                return (d0) g0VarB;
            }
        } else if (obj instanceof byte[]) {
            try {
                return (d0) G.b((byte[]) obj);
            } catch (IOException e) {
                throw new IllegalArgumentException("failed to construct object identifier from byte[]: " + e.getMessage());
            }
        }
        throw new IllegalArgumentException("illegal object in getInstance: ".concat(obj.getClass().getName()));
    }

    public static String u(byte[] bArr) {
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
                        if (j2 < 40) {
                            sb.append('0');
                        } else if (j2 < 80) {
                            sb.append('1');
                            j2 -= 40;
                        } else {
                            sb.append('2');
                            j2 -= 80;
                        }
                        z = false;
                    }
                    sb.append('.');
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
                        sb.append('2');
                        bigIntegerOr = bigIntegerOr.subtract(BigInteger.valueOf(80L));
                        z = false;
                    }
                    sb.append('.');
                    sb.append(bigIntegerOr);
                    bigIntegerShiftLeft = null;
                    j = 0;
                } else {
                    bigIntegerShiftLeft = bigIntegerOr.shiftLeft(7);
                }
            }
        }
        return sb.toString();
    }

    @Override // defpackage.g0
    public final boolean g(g0 g0Var) {
        if (this == g0Var) {
            return true;
        }
        if (!(g0Var instanceof d0)) {
            return false;
        }
        return Arrays.equals(this.E, ((d0) g0Var).E);
    }

    @Override // defpackage.g0
    public final void h(p50 p50Var, boolean z) throws IOException {
        p50Var.G(6, z, this.E);
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

    public final d0 o(String str) {
        byte[] bArrM;
        String strSubstring;
        ConcurrentHashMap concurrentHashMap = h0.G;
        if (str.length() > 16383) {
            throw new IllegalArgumentException("exceeded relative OID contents length limit");
        }
        int i = 0;
        if (!h0.q(0, str)) {
            throw new IllegalArgumentException(zn.n("string ", str, " not a valid relative OID"));
        }
        int length = str.length();
        byte[] bArr = this.E;
        if (length <= 2) {
            p(bArr.length + 1);
            int iCharAt = str.charAt(0) - '0';
            if (str.length() == 2) {
                iCharAt = (iCharAt * 10) + (str.charAt(1) - '0');
            }
            int length2 = bArr.length;
            bArrM = new byte[1 + length2];
            System.arraycopy(bArr, 0, bArrM, 0, length2);
            bArrM[length2] = (byte) iCharAt;
        } else {
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            while (i != -1) {
                if (i == -1) {
                    strSubstring = null;
                } else {
                    int iIndexOf = str.indexOf(46, i);
                    if (iIndexOf == -1) {
                        strSubstring = str.substring(i);
                        i = -1;
                    } else {
                        strSubstring = str.substring(i, iIndexOf);
                        i = iIndexOf + 1;
                    }
                }
                if (strSubstring.length() <= 18) {
                    h0.r(byteArrayOutputStream, Long.parseLong(strSubstring));
                } else {
                    h0.s(byteArrayOutputStream, new BigInteger(strSubstring));
                }
            }
            byte[] byteArray = byteArrayOutputStream.toByteArray();
            p(bArr.length + byteArray.length);
            bArrM = z91.m(bArr, byteArray);
        }
        return new d0(r() + NatsConstants.DOT + str, bArrM);
    }

    public final synchronized String r() {
        try {
            if (this.F == null) {
                this.F = u(this.E);
            }
        } catch (Throwable th) {
            throw th;
        }
        return this.F;
    }

    public final d0 t() {
        c0 c0Var = new c0(this.E);
        ConcurrentHashMap concurrentHashMap = H;
        d0 d0Var = (d0) concurrentHashMap.get(c0Var);
        if (d0Var != null) {
            return d0Var;
        }
        synchronized (concurrentHashMap) {
            try {
                if (concurrentHashMap.containsKey(c0Var)) {
                    return (d0) concurrentHashMap.get(c0Var);
                }
                concurrentHashMap.put(c0Var, this);
                return this;
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    public final String toString() {
        return r();
    }

    public d0(String str, byte[] bArr) {
        this.E = bArr;
        this.F = str;
    }
}
