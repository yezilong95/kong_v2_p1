package defpackage;

import java.io.EOFException;
import java.io.InputStream;
import java.util.Arrays;
import okio.ByteString;

/* JADX INFO: loaded from: classes.dex */
public final class aa {
    public static ByteString a(String str) {
        char c;
        int i;
        char cCharAt;
        char c2 = 'A';
        oy.j(str, "<this>");
        byte[] bArr = a.a;
        int length = str.length();
        while (true) {
            c = '\t';
            if (length <= 0 || !((cCharAt = str.charAt(length - 1)) == '=' || cCharAt == '\n' || cCharAt == '\r' || cCharAt == ' ' || cCharAt == '\t')) {
                break;
            }
            length--;
        }
        int i2 = (int) ((((long) length) * 6) / 8);
        byte[] bArrCopyOf = new byte[i2];
        int i3 = 0;
        int i4 = 0;
        int i5 = 0;
        int i6 = 0;
        while (true) {
            if (i3 < length) {
                char cCharAt2 = str.charAt(i3);
                char c3 = c2;
                if (c2 <= cCharAt2 && cCharAt2 < '[') {
                    i = cCharAt2 - 'A';
                } else if ('a' <= cCharAt2 && cCharAt2 < '{') {
                    i = cCharAt2 - 'G';
                } else if ('0' <= cCharAt2 && cCharAt2 < ':') {
                    i = cCharAt2 + 4;
                } else if (cCharAt2 != '+' && cCharAt2 != '-') {
                    if (cCharAt2 != '/' && cCharAt2 != '_') {
                        if (cCharAt2 != '\n' && cCharAt2 != '\r' && cCharAt2 != ' ' && cCharAt2 != c) {
                            break;
                        }
                        i3++;
                        c2 = c3;
                        c = '\t';
                    } else {
                        i = 63;
                    }
                } else {
                    i = 62;
                }
                int i7 = i | (i5 << 6);
                i4++;
                if (i4 % 4 == 0) {
                    bArrCopyOf[i6] = (byte) (i7 >> 16);
                    int i8 = i6 + 2;
                    bArrCopyOf[i6 + 1] = (byte) (i7 >> 8);
                    i6 += 3;
                    bArrCopyOf[i8] = (byte) i7;
                }
                i5 = i7;
                i3++;
                c2 = c3;
                c = '\t';
            } else {
                int i9 = i4 % 4;
                if (i9 != 1) {
                    if (i9 == 2) {
                        bArrCopyOf[i6] = (byte) ((i5 << 12) >> 16);
                        i6 = 1 + i6;
                    } else if (i9 == 3) {
                        int i10 = i5 << 6;
                        int i11 = 1 + i6;
                        bArrCopyOf[i6] = (byte) (i10 >> 16);
                        i6 += 2;
                        bArrCopyOf[i11] = (byte) (i10 >> 8);
                    }
                    if (i6 != i2) {
                        bArrCopyOf = Arrays.copyOf(bArrCopyOf, i6);
                        oy.i(bArrCopyOf, "copyOf(this, newSize)");
                    }
                }
            }
        }
        bArrCopyOf = null;
        if (bArrCopyOf != null) {
            return new ByteString(bArrCopyOf);
        }
        return null;
    }

    public static ByteString b(String str) {
        oy.j(str, "<this>");
        if (str.length() % 2 != 0) {
            throw new IllegalArgumentException("Unexpected hex string: ".concat(str).toString());
        }
        int length = str.length() / 2;
        byte[] bArr = new byte[length];
        for (int i = 0; i < length; i++) {
            int i2 = i * 2;
            bArr[i] = (byte) (k9.c(str.charAt(i2 + 1)) + (k9.c(str.charAt(i2)) << 4));
        }
        return new ByteString(bArr);
    }

    public static ByteString c(String str) {
        oy.j(str, "<this>");
        byte[] bytes = str.getBytes(zb.a);
        oy.i(bytes, "this as java.lang.String).getBytes(charset)");
        ByteString byteString = new ByteString(bytes);
        byteString.setUtf8$okio(str);
        return byteString;
    }

    public static ByteString d(byte[] bArr, int i, int i2) {
        oy.j(bArr, "<this>");
        if (i2 == -1234567890) {
            i2 = bArr.length;
        }
        xi.f(bArr.length, i, i2);
        return new ByteString(g7.Z(bArr, i, i2 + i));
    }

    public static ByteString e(InputStream inputStream, int i) {
        oy.j(inputStream, "<this>");
        if (i < 0) {
            throw new IllegalArgumentException(hk.g(i, "byteCount < 0: ").toString());
        }
        byte[] bArr = new byte[i];
        int i2 = 0;
        while (i2 < i) {
            int i3 = inputStream.read(bArr, i2, i - i2);
            if (i3 == -1) {
                throw new EOFException();
            }
            i2 += i3;
        }
        return new ByteString(bArr);
    }
}
