package defpackage;

import io.nats.client.support.Digester;
import java.util.Hashtable;

/* JADX INFO: loaded from: classes.dex */
public final class x20 implements oq0 {
    public static final byte[] h = {1};
    public static final Hashtable i;
    public fq0 a;
    public byte[] b;
    public byte[] c;
    public long d;
    public fu e;
    public int f;
    public int g;

    static {
        Hashtable hashtable = new Hashtable();
        i = hashtable;
        hashtable.put("SHA-1", 440);
        hashtable.put("SHA-224", 440);
        hashtable.put(Digester.DEFAULT_DIGEST_ALGORITHM, 440);
        hashtable.put("SHA-512/256", 440);
        hashtable.put("SHA-512/224", 440);
        hashtable.put("SHA-384", 888);
        hashtable.put("SHA-512", 888);
    }

    public static void c(byte[] bArr, byte[] bArr2) {
        int i2 = 0;
        for (int i3 = 1; i3 <= bArr2.length; i3++) {
            int i4 = (bArr[bArr.length - i3] & 255) + (bArr2[bArr2.length - i3] & 255) + i2;
            i2 = i4 > 255 ? 1 : 0;
            bArr[bArr.length - i3] = (byte) i4;
        }
        for (int length = bArr2.length + 1; length <= bArr.length; length++) {
            int i5 = (bArr[bArr.length - length] & 255) + i2;
            i2 = i5 > 255 ? 1 : 0;
            bArr[bArr.length - length] = (byte) i5;
        }
    }

    @Override // defpackage.oq0
    public final int a(byte[] bArr, boolean z) {
        int length = bArr.length * 8;
        if (length > 262144) {
            throw new IllegalArgumentException("Number of bits per request limited to 262144");
        }
        if (this.d > 140737488355328L) {
            return -1;
        }
        if (z) {
            b(null);
        }
        byte[] bArr2 = this.b;
        this.a.getClass();
        int i2 = length / 8;
        int i3 = i2 / 64;
        int length2 = bArr2.length;
        byte[] bArr3 = new byte[length2];
        System.arraycopy(bArr2, 0, bArr3, 0, bArr2.length);
        byte[] bArr4 = new byte[i2];
        byte[] bArr5 = new byte[64];
        for (int i4 = 0; i4 <= i3; i4++) {
            fq0 fq0Var = this.a;
            fq0Var.e(bArr3, 0, length2);
            fq0Var.d(0, bArr5);
            int i5 = i4 * 64;
            int i6 = i2 - i5;
            if (i6 > 64) {
                i6 = 64;
            }
            System.arraycopy(bArr5, 0, bArr4, i5, i6);
            c(bArr3, h);
        }
        byte[] bArr6 = this.b;
        int length3 = bArr6.length + 1;
        byte[] bArr7 = new byte[length3];
        System.arraycopy(bArr6, 0, bArr7, 1, bArr6.length);
        bArr7[0] = 3;
        this.a.getClass();
        byte[] bArr8 = new byte[64];
        fq0 fq0Var2 = this.a;
        fq0Var2.e(bArr7, 0, length3);
        fq0Var2.d(0, bArr8);
        c(this.b, bArr8);
        c(this.b, this.c);
        c(this.b, new byte[]{(byte) (r9 >> 24), (byte) (r9 >> 16), (byte) (r9 >> 8), (byte) this.d});
        this.d++;
        System.arraycopy(bArr4, 0, bArr, 0, bArr.length);
        return length;
    }

    @Override // defpackage.oq0
    public final void b(byte[] bArr) {
        byte[] bArrB = this.e.b();
        if (bArrB.length < (this.f + 7) / 8) {
            throw new IllegalStateException("Insufficient entropy provided by entropy source");
        }
        byte[] bArrO = z91.o(h, this.b, bArrB, bArr);
        fq0 fq0Var = this.a;
        int i2 = this.g;
        byte[] bArrA = k31.a(fq0Var, bArrO, i2);
        this.b = bArrA;
        byte[] bArr2 = new byte[bArrA.length + 1];
        bArr2[0] = 0;
        System.arraycopy(bArrA, 0, bArr2, 1, bArrA.length);
        this.c = k31.a(fq0Var, bArr2, i2);
        this.d = 1L;
    }
}
