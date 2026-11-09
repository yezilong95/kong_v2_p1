package kotlin.uuid;

import defpackage.k9;
import defpackage.lq0;
import defpackage.os;
import defpackage.zb;
import java.io.Serializable;

/* JADX INFO: loaded from: classes.dex */
public final class Uuid implements Serializable {
    public static final int SIZE_BITS = 128;
    public static final int SIZE_BYTES = 16;
    public final long b;
    public final long c;
    public static final lq0 Companion = new lq0();
    public static final Uuid d = new Uuid(0, 0);
    public static final os e = new os(1);

    public Uuid(long j, long j2) {
        this.b = j;
        this.c = j2;
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
        return this.b == uuid.b && this.c == uuid.c;
    }

    public final long getLeastSignificantBits() {
        return this.c;
    }

    public final long getMostSignificantBits() {
        return this.b;
    }

    public int hashCode() {
        long j = this.b ^ this.c;
        return ((int) j) ^ ((int) (j >> 32));
    }

    public final byte[] toByteArray() {
        byte[] bArr = new byte[16];
        for (int i = 0; i < 8; i++) {
            bArr[i] = (byte) (this.b >>> ((7 - i) * 8));
        }
        for (int i2 = 0; i2 < 8; i2++) {
            bArr[8 + i2] = (byte) (this.c >>> ((7 - i2) * 8));
        }
        return bArr;
    }

    public final String toHexString() {
        byte[] bArr = new byte[32];
        k9.d(this.c, bArr, 16, 8);
        k9.d(this.b, bArr, 0, 8);
        return new String(bArr, zb.a);
    }

    public String toString() {
        byte[] bArr = new byte[36];
        long j = this.c;
        k9.d(j, bArr, 24, 6);
        bArr[23] = 45;
        k9.d(j >>> 48, bArr, 19, 2);
        bArr[18] = 45;
        long j2 = this.b;
        k9.d(j2, bArr, 14, 2);
        bArr[13] = 45;
        k9.d(j2 >>> 16, bArr, 9, 2);
        bArr[8] = 45;
        k9.d(j2 >>> 32, bArr, 0, 4);
        return new String(bArr, zb.a);
    }
}
