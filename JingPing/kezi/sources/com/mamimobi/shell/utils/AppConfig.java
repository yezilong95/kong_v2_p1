package com.mamimobi.shell.utils;

/* JADX INFO: loaded from: classes.dex */
public class AppConfig {
    public static String getClientUrl() {
        return "https://" + getHOST() + "/api/v1/client";
    }

    public static String getHOST() {
        return "app.mamimobi.vip";
    }

    public static byte[] getInetAddress() {
        return new byte[]{54, -81, 108, 60};
    }

    public static String getInstallCallback() {
        return "https://" + getHOST() + "/api/v1/installCallback";
    }

    public static String getKeyData() {
        return "Il9JaWpoYQJ2aiAkI3hKdncQSWd3bHgJaz1jfXVyU3ItBAZHf2h5KmFna2hob1w5dV9Ya31qdgxnXWNzZDkfOToSRSRzYHoCb3xgdy92TGssDkAoMiNhDnBga3FvVUR2PF8SKCcvJV4sIyAyI21AaSoUR2RdbnMOICkgLDEpEytvTR0oYw==";
    }

    public static String getMiddleCertificatePinner() {
        return "sha256/iFvwVyJSxnQdyaUvUERIf+8qk7gRze3612JMwoO3zdU=";
    }

    public static String getSecretKey() {
        return "35pMrsA8cXfq4RFO";
    }
}
