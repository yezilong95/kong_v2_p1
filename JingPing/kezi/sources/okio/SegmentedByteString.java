package okio;

import defpackage.g7;
import defpackage.hk;
import defpackage.k9;
import defpackage.oy;
import defpackage.p9;
import defpackage.th0;
import defpackage.xi;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.ByteBuffer;
import java.nio.charset.Charset;
import java.security.InvalidKeyException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;

/* JADX INFO: loaded from: classes.dex */
public final class SegmentedByteString extends ByteString {
    public final transient byte[][] e;
    public final transient int[] f;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public SegmentedByteString(byte[][] bArr, int[] iArr) {
        super(ByteString.EMPTY.getData$okio());
        oy.j(bArr, "segments");
        oy.j(iArr, "directory");
        this.e = bArr;
        this.f = iArr;
    }

    private final Object writeReplace() {
        return a();
    }

    public final ByteString a() {
        return new ByteString(toByteArray());
    }

    @Override // okio.ByteString
    public ByteBuffer asByteBuffer() {
        ByteBuffer byteBufferAsReadOnlyBuffer = ByteBuffer.wrap(toByteArray()).asReadOnlyBuffer();
        oy.i(byteBufferAsReadOnlyBuffer, "asReadOnlyBuffer(...)");
        return byteBufferAsReadOnlyBuffer;
    }

    @Override // okio.ByteString
    public String base64() {
        return a().base64();
    }

    @Override // okio.ByteString
    public String base64Url() {
        return a().base64Url();
    }

    @Override // okio.ByteString
    public void copyInto(int i, byte[] bArr, int i2, int i3) {
        oy.j(bArr, "target");
        long j = i3;
        xi.f(size(), i, j);
        xi.f(bArr.length, i2, j);
        int i4 = i3 + i;
        int iG = k9.G(this, i);
        while (i < i4) {
            int i5 = iG == 0 ? 0 : getDirectory$okio()[iG - 1];
            int i6 = getDirectory$okio()[iG] - i5;
            int i7 = getDirectory$okio()[getSegments$okio().length + iG];
            int iMin = Math.min(i4, i6 + i5) - i;
            int i8 = (i - i5) + i7;
            g7.X(getSegments$okio()[iG], i2, bArr, i8, i8 + iMin);
            i2 += iMin;
            i += iMin;
            iG++;
        }
    }

    @Override // okio.ByteString
    public ByteString digest$okio(String str) throws NoSuchAlgorithmException {
        oy.j(str, "algorithm");
        MessageDigest messageDigest = MessageDigest.getInstance(str);
        int length = getSegments$okio().length;
        int i = 0;
        int i2 = 0;
        while (i < length) {
            int i3 = getDirectory$okio()[length + i];
            int i4 = getDirectory$okio()[i];
            messageDigest.update(getSegments$okio()[i], i3, i4 - i2);
            i++;
            i2 = i4;
        }
        byte[] bArrDigest = messageDigest.digest();
        oy.g(bArrDigest);
        return new ByteString(bArrDigest);
    }

    @Override // okio.ByteString
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof ByteString) {
            ByteString byteString = (ByteString) obj;
            if (byteString.size() == size() && rangeEquals(0, byteString, 0, size())) {
                return true;
            }
        }
        return false;
    }

    public final int[] getDirectory$okio() {
        return this.f;
    }

    public final byte[][] getSegments$okio() {
        return this.e;
    }

    @Override // okio.ByteString
    public int getSize$okio() {
        return getDirectory$okio()[getSegments$okio().length - 1];
    }

    @Override // okio.ByteString
    public int hashCode() {
        int hashCode$okio = getHashCode$okio();
        if (hashCode$okio != 0) {
            return hashCode$okio;
        }
        int length = getSegments$okio().length;
        int i = 0;
        int i2 = 1;
        int i3 = 0;
        while (i < length) {
            int i4 = getDirectory$okio()[length + i];
            int i5 = getDirectory$okio()[i];
            byte[] bArr = getSegments$okio()[i];
            int i6 = (i5 - i3) + i4;
            while (i4 < i6) {
                i2 = (i2 * 31) + bArr[i4];
                i4++;
            }
            i++;
            i3 = i5;
        }
        setHashCode$okio(i2);
        return i2;
    }

    @Override // okio.ByteString
    public String hex() {
        return a().hex();
    }

    @Override // okio.ByteString
    public ByteString hmac$okio(String str, ByteString byteString) throws NoSuchAlgorithmException {
        oy.j(str, "algorithm");
        oy.j(byteString, "key");
        try {
            Mac mac = Mac.getInstance(str);
            mac.init(new SecretKeySpec(byteString.toByteArray(), str));
            int length = getSegments$okio().length;
            int i = 0;
            int i2 = 0;
            while (i < length) {
                int i3 = getDirectory$okio()[length + i];
                int i4 = getDirectory$okio()[i];
                mac.update(getSegments$okio()[i], i3, i4 - i2);
                i++;
                i2 = i4;
            }
            byte[] bArrDoFinal = mac.doFinal();
            oy.i(bArrDoFinal, "doFinal(...)");
            return new ByteString(bArrDoFinal);
        } catch (InvalidKeyException e) {
            throw new IllegalArgumentException(e);
        }
    }

    @Override // okio.ByteString
    public int indexOf(byte[] bArr, int i) {
        oy.j(bArr, "other");
        return a().indexOf(bArr, i);
    }

    @Override // okio.ByteString
    public byte[] internalArray$okio() {
        return toByteArray();
    }

    @Override // okio.ByteString
    public byte internalGet$okio(int i) {
        xi.f(getDirectory$okio()[getSegments$okio().length - 1], i, 1L);
        int iG = k9.G(this, i);
        return getSegments$okio()[iG][(i - (iG == 0 ? 0 : getDirectory$okio()[iG - 1])) + getDirectory$okio()[getSegments$okio().length + iG]];
    }

    @Override // okio.ByteString
    public int lastIndexOf(byte[] bArr, int i) {
        oy.j(bArr, "other");
        return a().lastIndexOf(bArr, i);
    }

    @Override // okio.ByteString
    public boolean rangeEquals(int i, ByteString byteString, int i2, int i3) {
        oy.j(byteString, "other");
        if (i < 0 || i > size() - i3) {
            return false;
        }
        int i4 = i3 + i;
        int iG = k9.G(this, i);
        while (i < i4) {
            int i5 = iG == 0 ? 0 : getDirectory$okio()[iG - 1];
            int i6 = getDirectory$okio()[iG] - i5;
            int i7 = getDirectory$okio()[getSegments$okio().length + iG];
            int iMin = Math.min(i4, i6 + i5) - i;
            if (!byteString.rangeEquals(i2, getSegments$okio()[iG], (i - i5) + i7, iMin)) {
                return false;
            }
            i2 += iMin;
            i += iMin;
            iG++;
        }
        return true;
    }

    @Override // okio.ByteString
    public String string(Charset charset) {
        oy.j(charset, "charset");
        return a().string(charset);
    }

    @Override // okio.ByteString
    public ByteString substring(int i, int i2) {
        if (i2 == -1234567890) {
            i2 = size();
        }
        if (i < 0) {
            throw new IllegalArgumentException(hk.h(i, "beginIndex=", " < 0").toString());
        }
        if (i2 > size()) {
            throw new IllegalArgumentException(("endIndex=" + i2 + " > length(" + size() + ')').toString());
        }
        int i3 = i2 - i;
        if (i3 < 0) {
            throw new IllegalArgumentException(hk.f(i2, i, "endIndex=", " < beginIndex=").toString());
        }
        if (i == 0 && i2 == size()) {
            return this;
        }
        if (i == i2) {
            return ByteString.EMPTY;
        }
        int iG = k9.G(this, i);
        int iG2 = k9.G(this, i2 - 1);
        byte[][] bArr = (byte[][]) g7.a0(getSegments$okio(), iG, iG2 + 1);
        int[] iArr = new int[bArr.length * 2];
        if (iG <= iG2) {
            int i4 = iG;
            int i5 = 0;
            while (true) {
                iArr[i5] = Math.min(getDirectory$okio()[i4] - i, i3);
                int i6 = i5 + 1;
                iArr[i5 + bArr.length] = getDirectory$okio()[getSegments$okio().length + i4];
                if (i4 == iG2) {
                    break;
                }
                i4++;
                i5 = i6;
            }
        }
        int i7 = iG != 0 ? getDirectory$okio()[iG - 1] : 0;
        int length = bArr.length;
        iArr[length] = (i - i7) + iArr[length];
        return new SegmentedByteString(bArr, iArr);
    }

    @Override // okio.ByteString
    public ByteString toAsciiLowercase() {
        return a().toAsciiLowercase();
    }

    @Override // okio.ByteString
    public ByteString toAsciiUppercase() {
        return a().toAsciiUppercase();
    }

    @Override // okio.ByteString
    public byte[] toByteArray() {
        byte[] bArr = new byte[size()];
        int length = getSegments$okio().length;
        int i = 0;
        int i2 = 0;
        int i3 = 0;
        while (i < length) {
            int i4 = getDirectory$okio()[length + i];
            int i5 = getDirectory$okio()[i];
            int i6 = i5 - i2;
            g7.X(getSegments$okio()[i], i3, bArr, i4, i4 + i6);
            i3 += i6;
            i++;
            i2 = i5;
        }
        return bArr;
    }

    @Override // okio.ByteString
    public String toString() {
        return a().toString();
    }

    @Override // okio.ByteString
    public void write(OutputStream outputStream) throws IOException {
        oy.j(outputStream, "out");
        int length = getSegments$okio().length;
        int i = 0;
        int i2 = 0;
        while (i < length) {
            int i3 = getDirectory$okio()[length + i];
            int i4 = getDirectory$okio()[i];
            outputStream.write(getSegments$okio()[i], i3, i4 - i2);
            i++;
            i2 = i4;
        }
    }

    @Override // okio.ByteString
    public void write$okio(p9 p9Var, int i, int i2) {
        oy.j(p9Var, "buffer");
        int i3 = i + i2;
        int iG = k9.G(this, i);
        while (i < i3) {
            int i4 = iG == 0 ? 0 : getDirectory$okio()[iG - 1];
            int i5 = getDirectory$okio()[iG] - i4;
            int i6 = getDirectory$okio()[getSegments$okio().length + iG];
            int iMin = Math.min(i3, i5 + i4) - i;
            int i7 = (i - i4) + i6;
            th0 th0Var = new th0(getSegments$okio()[iG], i7, i7 + iMin, true);
            th0 th0Var2 = p9Var.b;
            if (th0Var2 == null) {
                th0Var.g = th0Var;
                th0Var.f = th0Var;
                p9Var.b = th0Var;
            } else {
                th0 th0Var3 = th0Var2.g;
                oy.g(th0Var3);
                th0Var3.b(th0Var);
            }
            i += iMin;
            iG++;
        }
        p9Var.c += (long) i2;
    }

    @Override // okio.ByteString
    public boolean rangeEquals(int i, byte[] bArr, int i2, int i3) {
        oy.j(bArr, "other");
        if (i < 0 || i > size() - i3 || i2 < 0 || i2 > bArr.length - i3) {
            return false;
        }
        int i4 = i3 + i;
        int iG = k9.G(this, i);
        while (i < i4) {
            int i5 = iG == 0 ? 0 : getDirectory$okio()[iG - 1];
            int i6 = getDirectory$okio()[iG] - i5;
            int i7 = getDirectory$okio()[getSegments$okio().length + iG];
            int iMin = Math.min(i4, i6 + i5) - i;
            if (!xi.c(getSegments$okio()[iG], (i - i5) + i7, bArr, i2, iMin)) {
                return false;
            }
            i2 += iMin;
            i += iMin;
            iG++;
        }
        return true;
    }
}
