package org.bouncycastle.jcajce.provider.drbg;

import defpackage.wl;
import java.security.SecureRandomSpi;
import org.bouncycastle.crypto.prng.SP800SecureRandom;

/* JADX INFO: loaded from: classes.dex */
public class DRBG$Default extends SecureRandomSpi {
    public static final SP800SecureRandom E = wl.a(true);

    @Override // java.security.SecureRandomSpi
    public final byte[] engineGenerateSeed(int i) {
        return E.generateSeed(i);
    }

    @Override // java.security.SecureRandomSpi
    public final void engineNextBytes(byte[] bArr) {
        E.nextBytes(bArr);
    }

    @Override // java.security.SecureRandomSpi
    public final void engineSetSeed(byte[] bArr) {
        E.setSeed(bArr);
    }
}
