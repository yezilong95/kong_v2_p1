package defpackage;

/* JADX INFO: loaded from: classes.dex */
public final class r20 implements oq0 {
    public byte[] a;
    public byte[] b;
    public long c;
    public fu d;
    public q20 e;
    public int f;

    @Override // defpackage.oq0
    public final int a(byte[] bArr, boolean z) {
        int length = bArr.length * 8;
        if (length > 262144) {
            throw new IllegalArgumentException("Number of bits per request limited to 262144");
        }
        if (this.c > 140737488355328L) {
            return -1;
        }
        if (z) {
            b(null);
        }
        int length2 = bArr.length;
        byte[] bArr2 = new byte[length2];
        int length3 = bArr.length;
        byte[] bArr3 = this.b;
        int length4 = length3 / bArr3.length;
        q70 q70Var = new q70(this.a);
        q20 q20Var = this.e;
        q20Var.b(q70Var);
        for (int i = 0; i < length4; i++) {
            q20Var.a.e(bArr3, 0, bArr3.length);
            q20Var.a(bArr3);
            System.arraycopy(bArr3, 0, bArr2, bArr3.length * i, bArr3.length);
        }
        if (bArr3.length * length4 < length2) {
            q20Var.a.e(bArr3, 0, bArr3.length);
            q20Var.a(bArr3);
            System.arraycopy(bArr3, 0, bArr2, bArr3.length * length4, length2 - (length4 * bArr3.length));
        }
        c(null, (byte) 0);
        this.c++;
        System.arraycopy(bArr2, 0, bArr, 0, bArr.length);
        return length;
    }

    @Override // defpackage.oq0
    public final void b(byte[] bArr) {
        byte[] bArrB = this.d.b();
        if (bArrB.length < (this.f + 7) / 8) {
            throw new IllegalStateException("Insufficient entropy provided by entropy source");
        }
        byte[] bArrM = z91.m(bArrB, bArr);
        c(bArrM, (byte) 0);
        if (bArrM != null) {
            c(bArrM, (byte) 1);
        }
        this.c = 1L;
    }

    public final void c(byte[] bArr, byte b) {
        byte[] bArr2 = this.a;
        q70 q70Var = new q70(bArr2);
        q20 q20Var = this.e;
        q20Var.b(q70Var);
        byte[] bArr3 = this.b;
        q20Var.a.e(bArr3, 0, bArr3.length);
        q20Var.a.f(b);
        if (bArr != null) {
            q20Var.a.e(bArr, 0, bArr.length);
        }
        q20Var.a(bArr2);
        q20Var.b(new q70(bArr2));
        q20Var.a.e(bArr3, 0, bArr3.length);
        q20Var.a(bArr3);
    }
}
