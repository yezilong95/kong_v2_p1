package defpackage;

import java.io.ByteArrayOutputStream;
import org.bouncycastle.util.encoders.DecoderException;
import org.bouncycastle.util.encoders.EncoderException;

/* JADX INFO: loaded from: classes.dex */
public abstract class c30 {
    public static final d30 a = new d30();

    public static byte[] a(String str) {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        try {
            a.a(str, byteArrayOutputStream);
            return byteArrayOutputStream.toByteArray();
        } catch (Exception e) {
            throw new DecoderException("exception decoding Hex string: " + e.getMessage(), e);
        }
    }

    public static byte[] b(String str) {
        try {
            return a.b(0, str.length(), str);
        } catch (Exception e) {
            throw new DecoderException("exception decoding Hex string: " + e.getMessage(), e);
        }
    }

    public static byte[] c(byte[] bArr, int i, int i2) {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        try {
            d30 d30Var = a;
            d30Var.getClass();
            if (i2 >= 0) {
                byte[] bArr2 = new byte[72];
                while (i2 > 0) {
                    int iMin = Math.min(36, i2);
                    int i3 = i + iMin;
                    int i4 = 0;
                    while (i < i3) {
                        int i5 = i + 1;
                        byte b = bArr[i];
                        int i6 = i4 + 1;
                        byte[] bArr3 = d30Var.a;
                        bArr2[i4] = bArr3[(b & 255) >>> 4];
                        i4 += 2;
                        bArr2[i6] = bArr3[b & 15];
                        i = i5;
                    }
                    byteArrayOutputStream.write(bArr2, 0, i4);
                    i2 -= iMin;
                    i = i3;
                }
            }
            return byteArrayOutputStream.toByteArray();
        } catch (Exception e) {
            EncoderException encoderException = new EncoderException("exception encoding Hex string: " + e.getMessage());
            encoderException.E = e;
            throw encoderException;
        }
    }
}
