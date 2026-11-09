package defpackage;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import kotlin.uuid.Uuid;

/* JADX INFO: loaded from: classes.dex */
public final class d30 {
    public final byte[] a;
    public final byte[] b;

    public d30() {
        byte[] bArr;
        this.a = new byte[]{48, 49, 50, 51, 52, 53, 54, 55, 56, 57, 97, 98, 99, 100, 101, 102};
        this.b = new byte[Uuid.SIZE_BITS];
        int i = 0;
        int i2 = 0;
        while (true) {
            bArr = this.b;
            if (i2 >= bArr.length) {
                break;
            }
            bArr[i2] = -1;
            i2++;
        }
        while (true) {
            byte[] bArr2 = this.a;
            if (i >= bArr2.length) {
                bArr[65] = bArr[97];
                bArr[66] = bArr[98];
                bArr[67] = bArr[99];
                bArr[68] = bArr[100];
                bArr[69] = bArr[101];
                bArr[70] = bArr[102];
                return;
            }
            bArr[bArr2[i]] = (byte) i;
            i++;
        }
    }

    public static boolean c(char c) {
        return c == '\n' || c == '\r' || c == '\t' || c == ' ';
    }

    public void a(String str, ByteArrayOutputStream byteArrayOutputStream) throws IOException {
        byte[] bArr = new byte[36];
        int length = str.length();
        while (length > 0 && c(str.charAt(length - 1))) {
            length--;
        }
        int i = 0;
        int i2 = 0;
        while (i < length) {
            while (i < length && c(str.charAt(i))) {
                i++;
            }
            int i3 = i + 1;
            char cCharAt = str.charAt(i);
            byte[] bArr2 = this.b;
            byte b = bArr2[cCharAt];
            while (i3 < length && c(str.charAt(i3))) {
                i3++;
            }
            int i4 = i3 + 1;
            byte b2 = bArr2[str.charAt(i3)];
            if ((b | b2) < 0) {
                throw new IOException("invalid characters encountered in Hex string");
            }
            int i5 = i2 + 1;
            bArr[i2] = (byte) ((b << 4) | b2);
            if (i5 == 36) {
                byteArrayOutputStream.write(bArr);
                i2 = 0;
            } else {
                i2 = i5;
            }
            i = i4;
        }
        if (i2 > 0) {
            byteArrayOutputStream.write(bArr, 0, i2);
        }
    }

    public byte[] b(int i, int i2, String str) throws IOException {
        if (str == null) {
            throw new NullPointerException("'str' cannot be null");
        }
        if (i < 0 || i2 < 0 || i > str.length() - i2) {
            throw new IndexOutOfBoundsException("invalid offset and/or length specified");
        }
        if ((i2 & 1) != 0) {
            throw new IOException("a hexadecimal encoding must have an even number of characters");
        }
        int i3 = i2 >>> 1;
        byte[] bArr = new byte[i3];
        for (int i4 = 0; i4 < i3; i4++) {
            char cCharAt = str.charAt(i);
            byte[] bArr2 = this.b;
            i += 2;
            int i5 = bArr2[str.charAt(i + 1)] | (bArr2[cCharAt] << 4);
            if (i5 < 0) {
                throw new IOException("invalid characters encountered in Hex string");
            }
            bArr[i4] = (byte) i5;
        }
        return bArr;
    }

    public /* synthetic */ d30(byte[] bArr, byte[] bArr2) {
        this.a = bArr;
        this.b = bArr2;
    }
}
