package org.bouncycastle.crypto.prng;

import java.security.SecureRandom;

/* JADX INFO: loaded from: classes.dex */
public class X931SecureRandom extends SecureRandom {
    @Override // java.security.SecureRandom
    public byte[] generateSeed(int i) {
        throw null;
    }

    @Override // java.security.SecureRandom, java.util.Random
    public void nextBytes(byte[] bArr) {
        synchronized (this) {
            throw null;
        }
    }

    @Override // java.security.SecureRandom, java.util.Random
    public void setSeed(long j) {
        synchronized (this) {
        }
    }

    @Override // java.security.SecureRandom
    public void setSeed(byte[] bArr) {
        synchronized (this) {
        }
    }
}
