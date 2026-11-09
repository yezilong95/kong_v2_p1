package io.nats.client.support;

import io.nats.client.Options;
import java.io.BufferedInputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.OpenOption;
import java.nio.file.Paths;
import java.security.GeneralSecurityException;
import java.security.KeyStore;
import java.security.cert.X509Certificate;
import javax.net.ssl.KeyManager;
import javax.net.ssl.KeyManagerFactory;
import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.TrustManagerFactory;
import javax.net.ssl.X509TrustManager;

/* JADX INFO: loaded from: classes.dex */
public class SSLUtils {
    public static final String DEFAULT_KEYSTORE_TYPE = "JKS";
    public static final String DEFAULT_TLS_ALGORITHM = "SunX509";
    private static final TrustManager[] DEFAULT_TRUST_MANAGERS = {new X509TrustManager() { // from class: io.nats.client.support.SSLUtils.1
        @Override // javax.net.ssl.X509TrustManager
        public void checkClientTrusted(X509Certificate[] x509CertificateArr, String str) {
            if (SSLUtils.TRUST_MANAGER_DELEGATE != null) {
                SSLUtils.TRUST_MANAGER_DELEGATE.checkClientTrusted(x509CertificateArr, str);
            }
        }

        @Override // javax.net.ssl.X509TrustManager
        public void checkServerTrusted(X509Certificate[] x509CertificateArr, String str) {
            if (SSLUtils.TRUST_MANAGER_DELEGATE != null) {
                SSLUtils.TRUST_MANAGER_DELEGATE.checkServerTrusted(x509CertificateArr, str);
            }
        }

        @Override // javax.net.ssl.X509TrustManager
        public X509Certificate[] getAcceptedIssuers() {
            if (SSLUtils.TRUST_MANAGER_DELEGATE == null) {
                return null;
            }
            return SSLUtils.TRUST_MANAGER_DELEGATE.getAcceptedIssuers();
        }
    }};
    private static TrustManagerDelegate TRUST_MANAGER_DELEGATE;

    public interface TrustManagerDelegate {
        void checkClientTrusted(X509Certificate[] x509CertificateArr, String str);

        void checkServerTrusted(X509Certificate[] x509CertificateArr, String str);

        X509Certificate[] getAcceptedIssuers();
    }

    public static KeyManager[] createKeyManagers(String str, char[] cArr) throws GeneralSecurityException, IOException {
        return createKeyManagers(str, cArr, DEFAULT_TLS_ALGORITHM);
    }

    public static SSLContext createOpenTLSContext() {
        try {
            return createTrustAllTlsContext();
        } catch (Exception unused) {
            return null;
        }
    }

    public static SSLContext createSSLContext(String str, char[] cArr, String str2, char[] cArr2) throws GeneralSecurityException, IOException {
        return createSSLContext(str, cArr, str2, cArr2, DEFAULT_TLS_ALGORITHM);
    }

    public static SSLContext createTrustAllTlsContext() throws GeneralSecurityException {
        SSLContext sSLContext = SSLContext.getInstance(Options.DEFAULT_SSL_PROTOCOL);
        sSLContext.init(null, DEFAULT_TRUST_MANAGERS, RandomUtils.SRAND);
        return sSLContext;
    }

    public static TrustManager[] createTrustManagers(String str, char[] cArr) throws GeneralSecurityException, IOException {
        return createTrustManagers(str, cArr, DEFAULT_TLS_ALGORITHM);
    }

    public static KeyStore loadKeystore(String str, char[] cArr) throws GeneralSecurityException, IOException {
        KeyStore keyStore = KeyStore.getInstance(DEFAULT_KEYSTORE_TYPE);
        BufferedInputStream bufferedInputStream = new BufferedInputStream(Files.newInputStream(Paths.get(str, new String[0]), new OpenOption[0]));
        try {
            keyStore.load(bufferedInputStream, cArr);
            bufferedInputStream.close();
            return keyStore;
        } catch (Throwable th) {
            try {
                throw th;
            } catch (Throwable th2) {
                try {
                    bufferedInputStream.close();
                } catch (Throwable th3) {
                    th.addSuppressed(th3);
                }
                throw th2;
            }
        }
    }

    public static void setDefaultTrustManagerDelegate(TrustManagerDelegate trustManagerDelegate) {
        TRUST_MANAGER_DELEGATE = trustManagerDelegate;
    }

    public static KeyManager[] createKeyManagers(String str, char[] cArr, String str2) throws GeneralSecurityException, IOException {
        if (str == null) {
            return null;
        }
        KeyStore keyStoreLoadKeystore = loadKeystore(str, cArr);
        KeyManagerFactory keyManagerFactory = KeyManagerFactory.getInstance(str2);
        keyManagerFactory.init(keyStoreLoadKeystore, cArr);
        return keyManagerFactory.getKeyManagers();
    }

    public static SSLContext createSSLContext(String str, char[] cArr, String str2, char[] cArr2, String str3) throws GeneralSecurityException, IOException {
        SSLContext sSLContext = SSLContext.getInstance(Options.DEFAULT_SSL_PROTOCOL);
        sSLContext.init(createKeyManagers(str, cArr, str3), createTrustManagers(str2, cArr2, str3), RandomUtils.SRAND);
        return sSLContext;
    }

    public static TrustManager[] createTrustManagers(String str, char[] cArr, String str2) throws GeneralSecurityException, IOException {
        if (str == null) {
            return null;
        }
        KeyStore keyStoreLoadKeystore = loadKeystore(str, cArr);
        TrustManagerFactory trustManagerFactory = TrustManagerFactory.getInstance(str2);
        trustManagerFactory.init(keyStoreLoadKeystore);
        return trustManagerFactory.getTrustManagers();
    }
}
