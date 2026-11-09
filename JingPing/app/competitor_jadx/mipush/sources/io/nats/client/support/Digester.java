package io.nats.client.support;

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

/* JADX INFO: loaded from: classes.dex */
public class Digester {
    public static final String DEFAULT_DIGEST_ALGORITHM = "SHA-256";
    public static final Charset DEFAULT_STRING_ENCODING = StandardCharsets.UTF_8;
    private final MessageDigest digest;
    private String digestValue;
    private final Base64.Encoder encoder;
    private final Charset stringCharset;

    public Digester() throws NoSuchAlgorithmException {
        this(null, null, null);
    }

    public String getDigestEntry() {
        return this.digest.getAlgorithm() + "=" + getDigestValue();
    }

    public String getDigestValue() {
        if (this.digestValue == null) {
            this.digestValue = this.encoder.encodeToString(this.digest.digest());
        }
        return this.digestValue;
    }

    public boolean matches(String str) {
        String upperCase = this.digest.getAlgorithm().toUpperCase();
        if (str.toUpperCase().startsWith(upperCase)) {
            return getDigestValue().equals(str.substring(upperCase.length() + 1));
        }
        return false;
    }

    public Digester reset() {
        this.digest.reset();
        this.digestValue = null;
        return this;
    }

    public Digester update(String str) {
        this.digest.update(str.getBytes(this.stringCharset));
        this.digestValue = null;
        return this;
    }

    public Digester(Base64.Encoder encoder) throws NoSuchAlgorithmException {
        this(null, null, encoder);
    }

    public Digester(String str) throws NoSuchAlgorithmException {
        this(str, null, null);
    }

    public Digester reset(String str) {
        return reset().update(str);
    }

    public Digester update(byte[] bArr) {
        this.digest.update(bArr);
        this.digestValue = null;
        return this;
    }

    public Digester(String str, Charset charset, Base64.Encoder encoder) throws NoSuchAlgorithmException {
        this.stringCharset = charset == null ? DEFAULT_STRING_ENCODING : charset;
        this.encoder = encoder == null ? Base64.getUrlEncoder() : encoder;
        this.digest = MessageDigest.getInstance(str == null ? DEFAULT_DIGEST_ALGORITHM : str);
    }

    public Digester reset(byte[] bArr) {
        return reset().update(bArr);
    }

    public Digester reset(byte[] bArr, int i, int i2) {
        return reset().update(bArr, i, i2);
    }

    public Digester update(byte[] bArr, int i, int i2) {
        this.digest.update(bArr, i, i2);
        this.digestValue = null;
        return this;
    }
}
