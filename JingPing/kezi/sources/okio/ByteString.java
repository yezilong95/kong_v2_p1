package okio;

import defpackage.a;
import defpackage.aa;
import defpackage.dl0;
import defpackage.g7;
import defpackage.k9;
import defpackage.oy;
import defpackage.p9;
import defpackage.xi;
import defpackage.zb;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.io.Serializable;
import java.lang.reflect.Field;
import java.nio.ByteBuffer;
import java.nio.charset.Charset;
import java.security.InvalidKeyException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;

/* JADX INFO: loaded from: classes.dex */
public class ByteString implements Serializable, Comparable<ByteString> {
    public static final aa Companion = new aa();
    public static final ByteString EMPTY = new ByteString(new byte[0]);
    private static final long serialVersionUID = 1;
    public final byte[] b;
    public transient int c;
    public transient String d;

    public ByteString(byte[] bArr) {
        oy.j(bArr, "data");
        this.b = bArr;
    }

    public static /* synthetic */ void copyInto$default(ByteString byteString, int i, byte[] bArr, int i2, int i3, int i4, Object obj) {
        if (obj != null) {
            throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: copyInto");
        }
        if ((i4 & 1) != 0) {
            i = 0;
        }
        if ((i4 & 4) != 0) {
            i2 = 0;
        }
        byteString.copyInto(i, bArr, i2, i3);
    }

    public static final ByteString decodeBase64(String str) {
        Companion.getClass();
        return aa.a(str);
    }

    public static final ByteString decodeHex(String str) {
        Companion.getClass();
        return aa.b(str);
    }

    public static final ByteString encodeString(String str, Charset charset) {
        Companion.getClass();
        oy.j(str, "<this>");
        oy.j(charset, "charset");
        byte[] bytes = str.getBytes(charset);
        oy.i(bytes, "this as java.lang.String).getBytes(charset)");
        return new ByteString(bytes);
    }

    public static final ByteString encodeUtf8(String str) {
        Companion.getClass();
        return aa.c(str);
    }

    public static /* synthetic */ int indexOf$default(ByteString byteString, ByteString byteString2, int i, int i2, Object obj) {
        if (obj != null) {
            throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: indexOf");
        }
        if ((i2 & 2) != 0) {
            i = 0;
        }
        return byteString.indexOf(byteString2, i);
    }

    public static /* synthetic */ int lastIndexOf$default(ByteString byteString, ByteString byteString2, int i, int i2, Object obj) {
        if (obj != null) {
            throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: lastIndexOf");
        }
        if ((i2 & 2) != 0) {
            i = -1234567890;
        }
        return byteString.lastIndexOf(byteString2, i);
    }

    public static final ByteString of(ByteBuffer byteBuffer) {
        Companion.getClass();
        oy.j(byteBuffer, "<this>");
        byte[] bArr = new byte[byteBuffer.remaining()];
        byteBuffer.get(bArr);
        return new ByteString(bArr);
    }

    public static final ByteString read(InputStream inputStream, int i) throws IOException {
        Companion.getClass();
        return aa.e(inputStream, i);
    }

    private final void readObject(ObjectInputStream objectInputStream) throws IllegalAccessException, NoSuchFieldException, IOException {
        int i = objectInputStream.readInt();
        Companion.getClass();
        ByteString byteStringE = aa.e(objectInputStream, i);
        Field declaredField = ByteString.class.getDeclaredField("b");
        declaredField.setAccessible(true);
        declaredField.set(this, byteStringE.b);
    }

    public static /* synthetic */ ByteString substring$default(ByteString byteString, int i, int i2, int i3, Object obj) {
        if (obj != null) {
            throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: substring");
        }
        if ((i3 & 1) != 0) {
            i = 0;
        }
        if ((i3 & 2) != 0) {
            i2 = -1234567890;
        }
        return byteString.substring(i, i2);
    }

    private final void writeObject(ObjectOutputStream objectOutputStream) throws IOException {
        objectOutputStream.writeInt(this.b.length);
        objectOutputStream.write(this.b);
    }

    /* JADX INFO: renamed from: -deprecated_getByte, reason: not valid java name */
    public final byte m16deprecated_getByte(int i) {
        return getByte(i);
    }

    /* JADX INFO: renamed from: -deprecated_size, reason: not valid java name */
    public final int m17deprecated_size() {
        return size();
    }

    public ByteBuffer asByteBuffer() {
        ByteBuffer byteBufferAsReadOnlyBuffer = ByteBuffer.wrap(this.b).asReadOnlyBuffer();
        oy.i(byteBufferAsReadOnlyBuffer, "asReadOnlyBuffer(...)");
        return byteBufferAsReadOnlyBuffer;
    }

    public String base64() {
        return a.a(getData$okio(), a.a);
    }

    public String base64Url() {
        return a.a(getData$okio(), a.b);
    }

    public void copyInto(int i, byte[] bArr, int i2, int i3) {
        oy.j(bArr, "target");
        g7.X(getData$okio(), i2, bArr, i, i3 + i);
    }

    public ByteString digest$okio(String str) throws NoSuchAlgorithmException {
        oy.j(str, "algorithm");
        MessageDigest messageDigest = MessageDigest.getInstance(str);
        messageDigest.update(this.b, 0, size());
        byte[] bArrDigest = messageDigest.digest();
        oy.g(bArrDigest);
        return new ByteString(bArrDigest);
    }

    public final boolean endsWith(ByteString byteString) {
        oy.j(byteString, "suffix");
        return rangeEquals(size() - byteString.size(), byteString, 0, byteString.size());
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof ByteString) {
            ByteString byteString = (ByteString) obj;
            if (byteString.size() == getData$okio().length && byteString.rangeEquals(0, getData$okio(), 0, getData$okio().length)) {
                return true;
            }
        }
        return false;
    }

    public final byte getByte(int i) {
        return internalGet$okio(i);
    }

    public final byte[] getData$okio() {
        return this.b;
    }

    public final int getHashCode$okio() {
        return this.c;
    }

    public int getSize$okio() {
        return getData$okio().length;
    }

    public final String getUtf8$okio() {
        return this.d;
    }

    public int hashCode() {
        int hashCode$okio = getHashCode$okio();
        if (hashCode$okio != 0) {
            return hashCode$okio;
        }
        int iHashCode = Arrays.hashCode(getData$okio());
        setHashCode$okio(iHashCode);
        return iHashCode;
    }

    public String hex() {
        char[] cArr = new char[getData$okio().length * 2];
        int i = 0;
        for (byte b : getData$okio()) {
            int i2 = i + 1;
            char[] cArr2 = k9.a;
            cArr[i] = cArr2[(b >> 4) & 15];
            i += 2;
            cArr[i2] = cArr2[b & 15];
        }
        return new String(cArr);
    }

    public ByteString hmac$okio(String str, ByteString byteString) throws NoSuchAlgorithmException {
        oy.j(str, "algorithm");
        oy.j(byteString, "key");
        try {
            Mac mac = Mac.getInstance(str);
            mac.init(new SecretKeySpec(byteString.toByteArray(), str));
            byte[] bArrDoFinal = mac.doFinal(this.b);
            oy.i(bArrDoFinal, "doFinal(...)");
            return new ByteString(bArrDoFinal);
        } catch (InvalidKeyException e) {
            throw new IllegalArgumentException(e);
        }
    }

    public ByteString hmacSha1(ByteString byteString) {
        oy.j(byteString, "key");
        return hmac$okio("HmacSHA1", byteString);
    }

    public ByteString hmacSha256(ByteString byteString) {
        oy.j(byteString, "key");
        return hmac$okio("HmacSHA256", byteString);
    }

    public ByteString hmacSha512(ByteString byteString) {
        oy.j(byteString, "key");
        return hmac$okio("HmacSHA512", byteString);
    }

    public final int indexOf(ByteString byteString) {
        oy.j(byteString, "other");
        return indexOf$default(this, byteString, 0, 2, (Object) null);
    }

    public byte[] internalArray$okio() {
        return getData$okio();
    }

    public byte internalGet$okio(int i) {
        return getData$okio()[i];
    }

    public final int lastIndexOf(ByteString byteString) {
        oy.j(byteString, "other");
        return lastIndexOf$default(this, byteString, 0, 2, (Object) null);
    }

    public final ByteString md5() {
        return digest$okio("MD5");
    }

    public boolean rangeEquals(int i, ByteString byteString, int i2, int i3) {
        oy.j(byteString, "other");
        return byteString.rangeEquals(i2, getData$okio(), i, i3);
    }

    public final void setHashCode$okio(int i) {
        this.c = i;
    }

    public final void setUtf8$okio(String str) {
        this.d = str;
    }

    public final ByteString sha1() {
        return digest$okio("SHA-1");
    }

    public final ByteString sha256() {
        return digest$okio("SHA-256");
    }

    public final ByteString sha512() {
        return digest$okio("SHA-512");
    }

    public final int size() {
        return getSize$okio();
    }

    public final boolean startsWith(ByteString byteString) {
        oy.j(byteString, "prefix");
        return rangeEquals(0, byteString, 0, byteString.size());
    }

    public String string(Charset charset) {
        oy.j(charset, "charset");
        return new String(this.b, charset);
    }

    public final ByteString substring() {
        return substring$default(this, 0, 0, 3, null);
    }

    public ByteString toAsciiLowercase() {
        for (int i = 0; i < getData$okio().length; i++) {
            byte b = getData$okio()[i];
            if (b >= 65 && b <= 90) {
                byte[] data$okio = getData$okio();
                byte[] bArrCopyOf = Arrays.copyOf(data$okio, data$okio.length);
                oy.i(bArrCopyOf, "copyOf(this, size)");
                bArrCopyOf[i] = (byte) (b + 32);
                for (int i2 = i + 1; i2 < bArrCopyOf.length; i2++) {
                    byte b2 = bArrCopyOf[i2];
                    if (b2 >= 65 && b2 <= 90) {
                        bArrCopyOf[i2] = (byte) (b2 + 32);
                    }
                }
                return new ByteString(bArrCopyOf);
            }
        }
        return this;
    }

    public ByteString toAsciiUppercase() {
        for (int i = 0; i < getData$okio().length; i++) {
            byte b = getData$okio()[i];
            if (b >= 97 && b <= 122) {
                byte[] data$okio = getData$okio();
                byte[] bArrCopyOf = Arrays.copyOf(data$okio, data$okio.length);
                oy.i(bArrCopyOf, "copyOf(this, size)");
                bArrCopyOf[i] = (byte) (b - 32);
                for (int i2 = i + 1; i2 < bArrCopyOf.length; i2++) {
                    byte b2 = bArrCopyOf[i2];
                    if (b2 >= 97 && b2 <= 122) {
                        bArrCopyOf[i2] = (byte) (b2 - 32);
                    }
                }
                return new ByteString(bArrCopyOf);
            }
        }
        return this;
    }

    public byte[] toByteArray() {
        byte[] data$okio = getData$okio();
        byte[] bArrCopyOf = Arrays.copyOf(data$okio, data$okio.length);
        oy.i(bArrCopyOf, "copyOf(this, size)");
        return bArrCopyOf;
    }

    public String toString() {
        byte b;
        int i;
        if (getData$okio().length == 0) {
            return "[size=0]";
        }
        byte[] data$okio = getData$okio();
        int length = data$okio.length;
        int i2 = 0;
        int i3 = 0;
        int i4 = 0;
        loop0: while (true) {
            if (i2 >= length) {
                break;
            }
            byte b2 = data$okio[i2];
            if (b2 >= 0) {
                int i5 = i4 + 1;
                if (i4 == 64) {
                    break;
                }
                if ((b2 != 10 && b2 != 13 && ((b2 >= 0 && b2 < 32) || (127 <= b2 && b2 < 160))) || b2 == 65533) {
                    break;
                }
                i3 += b2 < 65536 ? 1 : 2;
                i2++;
                while (true) {
                    i4 = i5;
                    if (i2 < length && (b = data$okio[i2]) >= 0) {
                        i2++;
                        i5 = i4 + 1;
                        if (i4 == 64) {
                            break loop0;
                        }
                        if ((b != 10 && b != 13 && ((b >= 0 && b < 32) || (127 <= b && b < 160))) || b == 65533) {
                            break loop0;
                        }
                        i3 += b < 65536 ? 1 : 2;
                    } else {
                        break;
                    }
                }
            } else if ((b2 >> 5) == -2) {
                int i6 = i2 + 1;
                if (length > i6) {
                    byte b3 = data$okio[i6];
                    if ((b3 & 192) == 128) {
                        int i7 = (b3 ^ 3968) ^ (b2 << 6);
                        if (i7 >= 128) {
                            i = i4 + 1;
                            if (i4 == 64) {
                                break;
                            }
                            if ((i7 != 10 && i7 != 13 && ((i7 >= 0 && i7 < 32) || (127 <= i7 && i7 < 160))) || i7 == 65533) {
                                break;
                            }
                            i3 += i7 < 65536 ? 1 : 2;
                            i2 += 2;
                            i4 = i;
                        } else if (i4 != 64) {
                            break;
                        }
                    } else if (i4 != 64) {
                        break;
                    }
                } else if (i4 != 64) {
                    break;
                }
            } else if ((b2 >> 4) == -2) {
                int i8 = i2 + 2;
                if (length > i8) {
                    byte b4 = data$okio[i2 + 1];
                    if ((b4 & 192) == 128) {
                        byte b5 = data$okio[i8];
                        if ((b5 & 192) == 128) {
                            int i9 = ((b5 ^ (-123008)) ^ (b4 << 6)) ^ (b2 << 12);
                            if (i9 < 2048) {
                                if (i4 != 64) {
                                    break;
                                }
                            } else if (55296 > i9 || i9 >= 57344) {
                                i = i4 + 1;
                                if (i4 == 64) {
                                    break;
                                }
                                if ((i9 != 10 && i9 != 13 && ((i9 >= 0 && i9 < 32) || (127 <= i9 && i9 < 160))) || i9 == 65533) {
                                    break;
                                }
                                i3 += i9 < 65536 ? 1 : 2;
                                i2 += 3;
                                i4 = i;
                            } else if (i4 != 64) {
                                break;
                            }
                        } else if (i4 != 64) {
                            break;
                        }
                    } else if (i4 != 64) {
                        break;
                    }
                } else if (i4 != 64) {
                    break;
                }
            } else if ((b2 >> 3) == -2) {
                int i10 = i2 + 3;
                if (length > i10) {
                    byte b6 = data$okio[i2 + 1];
                    if ((b6 & 192) == 128) {
                        byte b7 = data$okio[i2 + 2];
                        if ((b7 & 192) == 128) {
                            byte b8 = data$okio[i10];
                            if ((b8 & 192) == 128) {
                                int i11 = (((b8 ^ 3678080) ^ (b7 << 6)) ^ (b6 << 12)) ^ (b2 << 18);
                                if (i11 > 1114111) {
                                    if (i4 != 64) {
                                        break;
                                    }
                                } else if (55296 > i11 || i11 >= 57344) {
                                    if (i11 >= 65536) {
                                        i = i4 + 1;
                                        if (i4 == 64) {
                                            break;
                                        }
                                        if ((i11 != 10 && i11 != 13 && ((i11 >= 0 && i11 < 32) || (127 <= i11 && i11 < 160))) || i11 == 65533) {
                                            break;
                                        }
                                        i3 += i11 < 65536 ? 1 : 2;
                                        i2 += 4;
                                        i4 = i;
                                    } else if (i4 != 64) {
                                        break;
                                    }
                                } else if (i4 != 64) {
                                    break;
                                }
                            } else if (i4 != 64) {
                                break;
                            }
                        } else if (i4 != 64) {
                            break;
                        }
                    } else if (i4 != 64) {
                        break;
                    }
                } else if (i4 != 64) {
                    break;
                }
            } else if (i4 != 64) {
                break;
            }
        }
        i3 = -1;
        if (i3 != -1) {
            String strUtf8 = utf8();
            String strSubstring = strUtf8.substring(0, i3);
            oy.i(strSubstring, "this as java.lang.String…ing(startIndex, endIndex)");
            String strH0 = dl0.h0(dl0.h0(dl0.h0(strSubstring, "\\", "\\\\"), "\n", "\\n"), "\r", "\\r");
            if (i3 >= strUtf8.length()) {
                return "[text=" + strH0 + ']';
            }
            return "[size=" + getData$okio().length + " text=" + strH0 + "…]";
        }
        if (getData$okio().length <= 64) {
            return "[hex=" + hex() + ']';
        }
        StringBuilder sb = new StringBuilder("[size=");
        sb.append(getData$okio().length);
        sb.append(" hex=");
        if (64 <= getData$okio().length) {
            sb.append((64 == getData$okio().length ? this : new ByteString(g7.Z(getData$okio(), 0, 64))).hex());
            sb.append("…]");
            return sb.toString();
        }
        throw new IllegalArgumentException(("endIndex > length(" + getData$okio().length + ')').toString());
    }

    public String utf8() {
        String utf8$okio = getUtf8$okio();
        if (utf8$okio != null) {
            return utf8$okio;
        }
        byte[] bArrInternalArray$okio = internalArray$okio();
        oy.j(bArrInternalArray$okio, "<this>");
        String str = new String(bArrInternalArray$okio, zb.a);
        setUtf8$okio(str);
        return str;
    }

    public void write(OutputStream outputStream) throws IOException {
        oy.j(outputStream, "out");
        outputStream.write(this.b);
    }

    public void write$okio(p9 p9Var, int i, int i2) {
        oy.j(p9Var, "buffer");
        p9Var.M(getData$okio(), i, i2);
    }

    public static /* synthetic */ int indexOf$default(ByteString byteString, byte[] bArr, int i, int i2, Object obj) {
        if (obj != null) {
            throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: indexOf");
        }
        if ((i2 & 2) != 0) {
            i = 0;
        }
        return byteString.indexOf(bArr, i);
    }

    public static /* synthetic */ int lastIndexOf$default(ByteString byteString, byte[] bArr, int i, int i2, Object obj) {
        if (obj != null) {
            throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: lastIndexOf");
        }
        if ((i2 & 2) != 0) {
            i = -1234567890;
        }
        return byteString.lastIndexOf(bArr, i);
    }

    @Override // java.lang.Comparable
    public int compareTo(ByteString byteString) {
        oy.j(byteString, "other");
        int size = size();
        int size2 = byteString.size();
        int iMin = Math.min(size, size2);
        for (int i = 0; i < iMin; i++) {
            int i2 = getByte(i) & 255;
            int i3 = byteString.getByte(i) & 255;
            if (i2 != i3) {
                return i2 < i3 ? -1 : 1;
            }
        }
        if (size == size2) {
            return 0;
        }
        return size < size2 ? -1 : 1;
    }

    public final boolean endsWith(byte[] bArr) {
        oy.j(bArr, "suffix");
        return rangeEquals(size() - bArr.length, bArr, 0, bArr.length);
    }

    public final int indexOf(byte[] bArr) {
        oy.j(bArr, "other");
        return indexOf$default(this, bArr, 0, 2, (Object) null);
    }

    public final int lastIndexOf(byte[] bArr) {
        oy.j(bArr, "other");
        return lastIndexOf$default(this, bArr, 0, 2, (Object) null);
    }

    public boolean rangeEquals(int i, byte[] bArr, int i2, int i3) {
        oy.j(bArr, "other");
        return i >= 0 && i <= getData$okio().length - i3 && i2 >= 0 && i2 <= bArr.length - i3 && xi.c(getData$okio(), i, bArr, i2, i3);
    }

    public final boolean startsWith(byte[] bArr) {
        oy.j(bArr, "prefix");
        return rangeEquals(0, bArr, 0, bArr.length);
    }

    public final ByteString substring(int i) {
        return substring$default(this, i, 0, 2, null);
    }

    public final int indexOf(ByteString byteString, int i) {
        oy.j(byteString, "other");
        return indexOf(byteString.internalArray$okio(), i);
    }

    public int lastIndexOf(byte[] bArr, int i) {
        oy.j(bArr, "other");
        if (i == -1234567890) {
            i = size();
        }
        for (int iMin = Math.min(i, getData$okio().length - bArr.length); -1 < iMin; iMin--) {
            if (xi.c(getData$okio(), iMin, bArr, 0, bArr.length)) {
                return iMin;
            }
        }
        return -1;
    }

    public ByteString substring(int i, int i2) {
        if (i2 == -1234567890) {
            i2 = size();
        }
        if (i >= 0) {
            if (i2 <= getData$okio().length) {
                if (i2 - i >= 0) {
                    return (i == 0 && i2 == getData$okio().length) ? this : new ByteString(g7.Z(getData$okio(), i, i2));
                }
                throw new IllegalArgumentException("endIndex < beginIndex");
            }
            throw new IllegalArgumentException(("endIndex > length(" + getData$okio().length + ')').toString());
        }
        throw new IllegalArgumentException("beginIndex < 0");
    }

    public int indexOf(byte[] bArr, int i) {
        oy.j(bArr, "other");
        int length = getData$okio().length - bArr.length;
        int iMax = Math.max(i, 0);
        if (iMax > length) {
            return -1;
        }
        while (!xi.c(getData$okio(), iMax, bArr, 0, bArr.length)) {
            if (iMax == length) {
                return -1;
            }
            iMax++;
        }
        return iMax;
    }

    public static final ByteString of(byte... bArr) {
        Companion.getClass();
        oy.j(bArr, "data");
        byte[] bArrCopyOf = Arrays.copyOf(bArr, bArr.length);
        oy.i(bArrCopyOf, "copyOf(this, size)");
        return new ByteString(bArrCopyOf);
    }

    public static final ByteString of(byte[] bArr, int i, int i2) {
        Companion.getClass();
        return aa.d(bArr, i, i2);
    }

    public final int lastIndexOf(ByteString byteString, int i) {
        oy.j(byteString, "other");
        return lastIndexOf(byteString.internalArray$okio(), i);
    }
}
