package defpackage;

import okhttp3.TlsVersion;

/* JADX INFO: loaded from: classes.dex */
public final class no0 {
    /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue */
    public static TlsVersion a(String str) {
        oy.j(str, "javaName");
        int iHashCode = str.hashCode();
        if (iHashCode != 79201641) {
            if (iHashCode != 79923350) {
                switch (iHashCode) {
                    case -503070503:
                        if (str.equals("TLSv1.1")) {
                            return TlsVersion.TLS_1_1;
                        }
                        break;
                    case -503070502:
                        if (str.equals("TLSv1.2")) {
                            return TlsVersion.TLS_1_2;
                        }
                        break;
                    case -503070501:
                        if (str.equals("TLSv1.3")) {
                            return TlsVersion.TLS_1_3;
                        }
                        break;
                }
            } else if (str.equals("TLSv1")) {
                return TlsVersion.TLS_1_0;
            }
        } else if (str.equals("SSLv3")) {
            return TlsVersion.SSL_3_0;
        }
        throw new IllegalArgumentException("Unexpected TLS version: ".concat(str));
    }
}
