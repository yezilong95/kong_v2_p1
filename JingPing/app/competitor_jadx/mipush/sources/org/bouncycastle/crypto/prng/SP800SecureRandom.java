package org.bouncycastle.crypto.prng;

import androidx.work.impl.WorkDatabaseVersions;
import defpackage.fq0;
import defpackage.fu;
import defpackage.i6;
import defpackage.oq0;
import defpackage.ou;
import defpackage.q20;
import java.security.SecureRandom;

/* JADX INFO: loaded from: classes.dex */
public class SP800SecureRandom extends SecureRandom {
    public final ou E;
    public final boolean F;
    public final fu G;
    public oq0 H;

    public SP800SecureRandom(fu fuVar, ou ouVar, boolean z) {
        super(null, null);
        this.G = fuVar;
        this.E = ouVar;
        this.F = z;
    }

    @Override // java.security.SecureRandom
    public byte[] generateSeed(int i) {
        byte[] bArr = new byte[i];
        int i2 = i * 8;
        fu fuVar = this.G;
        if (i2 <= fuVar.c()) {
            System.arraycopy(fuVar.b(), 0, bArr, 0, i);
            return bArr;
        }
        int iC = fuVar.c() / 8;
        for (int i3 = 0; i3 < i; i3 += iC) {
            byte[] bArrB = fuVar.b();
            int i4 = i - i3;
            if (bArrB.length <= i4) {
                System.arraycopy(bArrB, 0, bArr, i3, bArrB.length);
            } else {
                System.arraycopy(bArrB, 0, bArr, i3, i4);
            }
        }
        return bArr;
    }

    @Override // java.security.SecureRandom
    public String getAlgorithm() {
        ou ouVar = this.E;
        switch (ouVar.E) {
            case WorkDatabaseVersions.VERSION_13 /* 13 */:
                return "HMAC-DRBG-" + i6.b(((q20) ouVar.F).a);
            default:
                return "HASH-DRBG-" + i6.b((fq0) ouVar.F);
        }
    }

    @Override // java.security.SecureRandom, java.util.Random
    public void nextBytes(byte[] bArr) {
        synchronized (this) {
            try {
                if (this.H == null) {
                    this.H = this.E.u(this.G);
                }
                if (this.H.a(bArr, this.F) < 0) {
                    this.H.b(null);
                    this.H.a(bArr, this.F);
                }
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    public void reseed(byte[] bArr) {
        synchronized (this) {
            try {
                if (this.H == null) {
                    this.H = this.E.u(this.G);
                }
                this.H.b(bArr);
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    @Override // java.security.SecureRandom, java.util.Random
    public void setSeed(long j) {
        synchronized (this) {
        }
    }

    public String toString() {
        return getAlgorithm();
    }

    @Override // java.security.SecureRandom
    public void setSeed(byte[] bArr) {
        synchronized (this) {
        }
    }
}
