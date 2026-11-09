package io.nats.client.impl;

/* JADX INFO: loaded from: classes.dex */
public class SSLContextFactoryProperties {
    public final char[] keystorePassword;
    public final String keystorePath;
    public final String tlsAlgorithm;
    public final char[] truststorePassword;
    public final String truststorePath;

    public static class Builder {
        String keystore;
        char[] keystorePassword;
        String tlsAlgorithm;
        String truststore;
        char[] truststorePassword;

        public SSLContextFactoryProperties build() {
            return new SSLContextFactoryProperties(this);
        }

        public Builder keystore(String str) {
            this.keystore = str;
            return this;
        }

        public Builder keystorePassword(char[] cArr) {
            this.keystorePassword = cArr;
            return this;
        }

        public Builder tlsAlgorithm(String str) {
            this.tlsAlgorithm = str;
            return this;
        }

        public Builder truststore(String str) {
            this.truststore = str;
            return this;
        }

        public Builder truststorePassword(char[] cArr) {
            this.truststorePassword = cArr;
            return this;
        }
    }

    public char[] getKeystorePassword() {
        return this.keystorePassword;
    }

    public String getKeystorePath() {
        return this.keystorePath;
    }

    public String getTlsAlgorithm() {
        return this.tlsAlgorithm;
    }

    public char[] getTruststorePassword() {
        return this.truststorePassword;
    }

    public String getTruststorePath() {
        return this.truststorePath;
    }

    private SSLContextFactoryProperties(Builder builder) {
        this.keystorePath = builder.keystore;
        this.keystorePassword = builder.keystorePassword;
        this.truststorePath = builder.truststore;
        this.truststorePassword = builder.truststorePassword;
        this.tlsAlgorithm = builder.tlsAlgorithm;
    }
}
