package defpackage;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import org.bouncycastle.asn1.ASN1ParsingException;

/* JADX INFO: loaded from: classes.dex */
public abstract class p extends g0 implements q {
    public static final o F = new o(1, p.class);
    public static final char[] G = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F'};
    public final byte[] E;

    public p(byte[] bArr) {
        this.E = bArr;
    }

    public static p o(byte[] bArr) {
        int length = bArr.length;
        if (length < 1) {
            throw new IllegalArgumentException("truncated BIT STRING detected");
        }
        int i = bArr[0] & 255;
        if (i > 0) {
            if (i > 7 || length < 2) {
                throw new IllegalArgumentException("invalid pad bits detected");
            }
            byte b = bArr[length - 1];
            if (b != ((byte) ((255 << i) & b))) {
                return new jl(bArr);
            }
        }
        return new lk(bArr);
    }

    public static p q(Object obj) {
        if (obj == null || (obj instanceof p)) {
            return (p) obj;
        }
        if (obj instanceof t) {
            g0 g0VarB = ((t) obj).b();
            if (g0VarB instanceof p) {
                return (p) g0VarB;
            }
        } else if (obj instanceof byte[]) {
            try {
                return (p) F.b((byte[]) obj);
            } catch (IOException e) {
                throw new IllegalArgumentException("failed to construct BIT STRING from byte[]: " + e.getMessage());
            }
        }
        throw new IllegalArgumentException("illegal object in getInstance: ".concat(obj.getClass().getName()));
    }

    @Override // defpackage.q
    public final int c() {
        return this.E[0] & 255;
    }

    @Override // defpackage.q
    public final InputStream e() {
        byte[] bArr = this.E;
        return new ByteArrayInputStream(bArr, 1, bArr.length - 1);
    }

    @Override // defpackage.g0
    public final boolean g(g0 g0Var) {
        if (!(g0Var instanceof p)) {
            return false;
        }
        byte[] bArr = ((p) g0Var).E;
        byte[] bArr2 = this.E;
        int length = bArr2.length;
        if (bArr.length != length) {
            return false;
        }
        if (length == 1) {
            return true;
        }
        int i = length - 1;
        for (int i2 = 0; i2 < i; i2++) {
            if (bArr2[i2] != bArr[i2]) {
                return false;
            }
        }
        int i3 = 255 << (bArr2[0] & 255);
        return ((byte) (bArr2[i] & i3)) == ((byte) (bArr[i] & i3));
    }

    @Override // defpackage.g0, defpackage.a0
    public final int hashCode() {
        byte[] bArr = this.E;
        if (bArr.length < 2) {
            return 1;
        }
        int i = 0;
        int i2 = bArr[0] & 255;
        int length = bArr.length;
        int i3 = length - 1;
        byte b = (byte) ((255 << i2) & bArr[i3]);
        if (bArr != null) {
            i = length;
            while (true) {
                i3--;
                if (i3 < 0) {
                    break;
                }
                i = (i * 257) ^ bArr[i3];
            }
        }
        return (i * 257) ^ b;
    }

    @Override // defpackage.g0
    public g0 m() {
        return new lk(this.E);
    }

    @Override // defpackage.g0
    public g0 n() {
        return new jl(this.E);
    }

    public final byte[] p() {
        byte[] bArr = this.E;
        if (bArr.length == 1) {
            return e0.G;
        }
        int i = bArr[0] & 255;
        byte[] bArrR = z91.r(bArr, 1, bArr.length);
        int length = bArrR.length - 1;
        bArrR[length] = (byte) (((byte) (255 << i)) & bArrR[length]);
        return bArrR;
    }

    public final byte[] r() {
        byte[] bArr = this.E;
        if (bArr[0] == 0) {
            return z91.r(bArr, 1, bArr.length);
        }
        throw new IllegalStateException("attempt to get non-octet aligned data from BIT STRING");
    }

    public String toString() {
        try {
            byte[] encoded = getEncoded();
            StringBuffer stringBuffer = new StringBuffer((encoded.length * 2) + 1);
            stringBuffer.append('#');
            for (int i = 0; i != encoded.length; i++) {
                byte b = encoded[i];
                char[] cArr = G;
                stringBuffer.append(cArr[(b >>> 4) & 15]);
                stringBuffer.append(cArr[b & 15]);
            }
            return stringBuffer.toString();
        } catch (IOException e) {
            throw new ASN1ParsingException("Internal error encoding BitString: " + e.getMessage(), e);
        }
    }

    public p(byte[] bArr, int i) {
        if (bArr == null) {
            throw new NullPointerException("'data' cannot be null");
        }
        if (bArr.length == 0 && i != 0) {
            throw new IllegalArgumentException("zero length data with non-zero pad bits");
        }
        if (i > 7 || i < 0) {
            throw new IllegalArgumentException("pad bits cannot be greater than 7 or less than 0");
        }
        int length = bArr.length;
        byte[] bArr2 = new byte[length + 1];
        System.arraycopy(bArr, 0, bArr2, 1, length);
        bArr2[0] = (byte) i;
        this.E = bArr2;
    }

    @Override // defpackage.u30
    public final g0 d() {
        return this;
    }
}
