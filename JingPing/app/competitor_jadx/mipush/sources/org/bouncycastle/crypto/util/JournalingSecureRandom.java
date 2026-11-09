package org.bouncycastle.crypto.util;

import defpackage.bs;
import defpackage.z91;
import java.io.IOException;
import java.security.SecureRandom;
import java.util.Arrays;
import org.bouncycastle.crypto.a;

/* JADX INFO: loaded from: classes.dex */
public class JournalingSecureRandom extends SecureRandom {
    public static final byte[] I = new byte[0];
    public final SecureRandom E;
    public final bs F;
    public byte[] G;
    public int H;

    public JournalingSecureRandom() {
        this(a.b());
    }

    public void clear() {
        Arrays.fill(this.G, (byte) 0);
        this.F.j();
    }

    public byte[] getFullTranscript() {
        int i = this.H;
        byte[] bArr = this.G;
        return i == bArr.length ? this.F.toByteArray() : z91.l(bArr);
    }

    public byte[] getTranscript() {
        return this.F.toByteArray();
    }

    @Override // java.security.SecureRandom, java.util.Random
    public final void nextBytes(byte[] bArr) {
        int i = this.H;
        int length = this.G.length;
        SecureRandom secureRandom = this.E;
        if (i >= length) {
            secureRandom.nextBytes(bArr);
        } else {
            int i2 = 0;
            while (i2 != bArr.length) {
                int i3 = this.H;
                byte[] bArr2 = this.G;
                if (i3 >= bArr2.length) {
                    break;
                }
                this.H = i3 + 1;
                bArr[i2] = bArr2[i3];
                i2++;
            }
            if (i2 != bArr.length) {
                int length2 = bArr.length - i2;
                byte[] bArr3 = new byte[length2];
                secureRandom.nextBytes(bArr3);
                System.arraycopy(bArr3, 0, bArr, i2, length2);
            }
        }
        try {
            this.F.write(bArr);
        } catch (IOException e) {
            throw new IllegalStateException("unable to record transcript: " + e.getMessage());
        }
    }

    public void reset() {
        this.H = 0;
        int length = this.G.length;
        bs bsVar = this.F;
        if (length == 0) {
            this.G = bsVar.toByteArray();
        }
        bsVar.reset();
    }

    public JournalingSecureRandom(SecureRandom secureRandom) {
        this.F = new bs(1);
        this.H = 0;
        this.E = secureRandom;
        this.G = I;
    }

    public JournalingSecureRandom(byte[] bArr, SecureRandom secureRandom) {
        this.F = new bs(1);
        this.H = 0;
        this.E = secureRandom;
        this.G = z91.l(bArr);
    }
}
