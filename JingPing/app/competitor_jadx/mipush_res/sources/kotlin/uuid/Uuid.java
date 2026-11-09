package kotlin.uuid;

import defpackage.cz;
import defpackage.la0;
import defpackage.m31;
import defpackage.ud;
import java.io.Serializable;

/* JADX INFO: loaded from: classes.dex */
public final class Uuid implements Serializable {
    public static final m31 Companion = new m31();
    public static final Uuid G = new Uuid(0, 0);
    public static final cz H = new cz(1);
    public static final int SIZE_BITS = 128;
    public static final int SIZE_BYTES = 16;
    public final long E;
    public final long F;

    public Uuid(long j, long j2) {
        this.E = j;
        this.F = j2;
    }

    public static /* synthetic */ void getLeastSignificantBits$annotations() {
    }

    public static /* synthetic */ void getMostSignificantBits$annotations() {
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof Uuid)) {
            return false;
        }
        Uuid uuid = (Uuid) obj;
        return this.E == uuid.E && this.F == uuid.F;
    }

    public final long getLeastSignificantBits() {
        return this.F;
    }

    public final long getMostSignificantBits() {
        return this.E;
    }

    public int hashCode() {
        long j = this.E ^ this.F;
        return ((int) j) ^ ((int) (j >> 32));
    }

    public final byte[] toByteArray() {
        byte[] bArr = new byte[16];
        for (int i = 0; i < 8; i++) {
            bArr[i] = (byte) (this.E >>> ((7 - i) * 8));
        }
        for (int i2 = 0; i2 < 8; i2++) {
            bArr[8 + i2] = (byte) (this.F >>> ((7 - i2) * 8));
        }
        return bArr;
    }

    public final String toHexString() {
        byte[] bArr = new byte[32];
        la0.b(this.F, bArr, 16, 8);
        la0.b(this.E, bArr, 0, 8);
        return new String(bArr, ud.a);
    }

    public String toString() {
        byte[] bArr = new byte[36];
        long j = this.F;
        la0.b(j, bArr, 24, 6);
        bArr[23] = 45;
        la0.b(j >>> 48, bArr, 19, 2);
        bArr[18] = 45;
        long j2 = this.E;
        la0.b(j2, bArr, 14, 2);
        bArr[13] = 45;
        la0.b(j2 >>> 16, bArr, 9, 2);
        bArr[8] = 45;
        la0.b(j2 >>> 32, bArr, 0, 4);
        return new String(bArr, ud.a);
    }
}
