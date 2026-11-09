package org.bouncycastle.crypto;

import defpackage.td;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

/* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
/* JADX WARN: Unknown enum class pattern. Please report as an issue! */
/* JADX INFO: loaded from: classes.dex */
public abstract class PasswordConverter implements td {
    public static final PasswordConverter ASCII;
    public static final /* synthetic */ PasswordConverter[] E;
    public static final PasswordConverter PKCS12;
    public static final PasswordConverter UTF8;

    static {
        PasswordConverter passwordConverter = new PasswordConverter() { // from class: hk0
            @Override // org.bouncycastle.crypto.PasswordConverter, defpackage.td
            public final byte[] convert(char[] cArr) {
                if (cArr == null) {
                    return new byte[0];
                }
                int length = cArr.length;
                byte[] bArr = new byte[length];
                for (int i = 0; i != length; i++) {
                    bArr[i] = (byte) cArr[i];
                }
                return bArr;
            }

            @Override // org.bouncycastle.crypto.PasswordConverter, defpackage.td
            public final String getType() {
                return "ASCII";
            }
        };
        ASCII = passwordConverter;
        PasswordConverter passwordConverter2 = new PasswordConverter() { // from class: ik0
            @Override // org.bouncycastle.crypto.PasswordConverter, defpackage.td
            public final byte[] convert(char[] cArr) {
                if (cArr == null) {
                    return new byte[0];
                }
                String str = jx0.a;
                ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                try {
                    jx0.d(cArr, byteArrayOutputStream);
                    return byteArrayOutputStream.toByteArray();
                } catch (IOException unused) {
                    throw new IllegalStateException("cannot encode string to byte array!");
                }
            }

            @Override // org.bouncycastle.crypto.PasswordConverter, defpackage.td
            public final String getType() {
                return "UTF8";
            }
        };
        UTF8 = passwordConverter2;
        PasswordConverter passwordConverter3 = new PasswordConverter() { // from class: jk0
            @Override // org.bouncycastle.crypto.PasswordConverter, defpackage.td
            public final byte[] convert(char[] cArr) {
                return la0.a(cArr);
            }

            @Override // org.bouncycastle.crypto.PasswordConverter, defpackage.td
            public final String getType() {
                return "PKCS12";
            }
        };
        PKCS12 = passwordConverter3;
        E = new PasswordConverter[]{passwordConverter, passwordConverter2, passwordConverter3};
    }

    public static PasswordConverter valueOf(String str) {
        return (PasswordConverter) Enum.valueOf(PasswordConverter.class, str);
    }

    public static PasswordConverter[] values() {
        return (PasswordConverter[]) E.clone();
    }

    public abstract /* synthetic */ byte[] convert(char[] cArr);

    public abstract /* synthetic */ String getType();
}
