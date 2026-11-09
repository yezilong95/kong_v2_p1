package org.webrtc;

/* JADX INFO: loaded from: classes.dex */
public interface SSLCertificateVerifier {
    @CalledByNative
    boolean verify(byte[] bArr);
}
