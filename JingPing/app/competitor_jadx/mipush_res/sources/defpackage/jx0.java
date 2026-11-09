package defpackage;

import io.nats.client.Options;
import java.io.ByteArrayOutputStream;
import java.security.AccessController;
import kotlin.uuid.Uuid;

/* JADX INFO: loaded from: classes.dex */
public abstract class jx0 {
    public static final String a;

    static {
        try {
            try {
                a = (String) AccessController.doPrivileged(new pl(4));
            } catch (Exception unused) {
                a = String.format("%n", new Object[0]);
            }
        } catch (Exception unused2) {
            a = "\n";
        }
    }

    public static String a(byte[] bArr) {
        int length = bArr.length;
        char[] cArr = new char[length];
        for (int i = 0; i != length; i++) {
            cArr[i] = (char) (bArr[i] & 255);
        }
        return new String(cArr);
    }

    public static byte[] b(String str) {
        int length = str.length();
        byte[] bArr = new byte[length];
        for (int i = 0; i != length; i++) {
            bArr[i] = (byte) str.charAt(i);
        }
        return bArr;
    }

    public static String c(String str) {
        char[] charArray = str.toCharArray();
        boolean z = false;
        for (int i = 0; i != charArray.length; i++) {
            char c = charArray[i];
            if ('A' <= c && 'Z' >= c) {
                charArray[i] = (char) (c + ' ');
                z = true;
            }
        }
        return z ? new String(charArray) : str;
    }

    public static void d(char[] cArr, ByteArrayOutputStream byteArrayOutputStream) {
        int i;
        int i2;
        int i3 = 0;
        while (i3 < cArr.length) {
            char c = cArr[i3];
            int i4 = c;
            if (c >= 128) {
                if (c < 2048) {
                    i = (c >> 6) | 192;
                } else if (c < 55296 || c > 57343) {
                    byteArrayOutputStream.write((c >> '\f') | 224);
                    i = ((c >> 6) & 63) | Uuid.SIZE_BITS;
                } else {
                    i3++;
                    if (i3 >= cArr.length) {
                        throw new IllegalStateException("invalid UTF-16 codepoint");
                    }
                    char c2 = cArr[i3];
                    if (c > 56319) {
                        throw new IllegalStateException("invalid UTF-16 codepoint");
                    }
                    int i5 = (((c & 1023) << 10) | (c2 & 1023)) + Options.DEFAULT_BUFFER_SIZE;
                    byteArrayOutputStream.write((i5 >> 18) | 240);
                    byteArrayOutputStream.write(((i5 >> 12) & 63) | Uuid.SIZE_BITS);
                    byteArrayOutputStream.write(((i5 >> 6) & 63) | Uuid.SIZE_BITS);
                    i2 = i5;
                    i4 = (i2 & 63) | Uuid.SIZE_BITS;
                }
                byteArrayOutputStream.write(i);
                i2 = c;
                i4 = (i2 & 63) | Uuid.SIZE_BITS;
            }
            byteArrayOutputStream.write(i4);
            i3++;
        }
    }

    public static String e(String str) {
        char[] charArray = str.toCharArray();
        boolean z = false;
        for (int i = 0; i != charArray.length; i++) {
            char c = charArray[i];
            if ('a' <= c && 'z' >= c) {
                charArray[i] = (char) (c - ' ');
                z = true;
            }
        }
        return z ? new String(charArray) : str;
    }
}
