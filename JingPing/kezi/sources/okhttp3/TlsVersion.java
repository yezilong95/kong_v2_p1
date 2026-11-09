package okhttp3;

import defpackage.no0;

/* JADX INFO: loaded from: classes.dex */
public enum TlsVersion {
    TLS_1_3("TLSv1.3"),
    TLS_1_2("TLSv1.2"),
    TLS_1_1("TLSv1.1"),
    TLS_1_0("TLSv1"),
    SSL_3_0("SSLv3");

    public static final no0 Companion = new no0();
    public final String b;

    TlsVersion(String str) {
        this.b = str;
    }

    public static final TlsVersion forJavaName(String str) {
        Companion.getClass();
        return no0.a(str);
    }

    /* JADX INFO: renamed from: -deprecated_javaName, reason: not valid java name */
    public final String m15deprecated_javaName() {
        return this.b;
    }

    public final String javaName() {
        return this.b;
    }
}
