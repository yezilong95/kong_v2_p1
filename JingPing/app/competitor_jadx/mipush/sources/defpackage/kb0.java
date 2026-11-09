package defpackage;

/* JADX INFO: loaded from: classes.dex */
public final class kb0 {
    public final fb0 a;
    public final int b;
    public final int c;
    public final int d;
    public final tb e;
    public final int f;

    public kb0(fb0 fb0Var) {
        this.a = fb0Var;
        this.b = fb0Var.a;
        this.c = fb0Var.f;
        this.d = fb0Var.b;
        tb tbVar = (tb) fb0Var.h;
        this.e = tbVar;
        tbVar.getClass();
        this.f = 3;
    }

    public static int a(l0 l0Var, int i, int i2, byte[] bArr, int i3) {
        int i4 = 0;
        int i5 = 0;
        while (i4 < i2) {
            int i6 = i5 + 3;
            if (i6 > i3) {
                break;
            }
            short s = (short) (bArr[i5] & 255);
            short s2 = (short) (bArr[i5 + 1] & 255);
            short s3 = (short) ((s | (s2 << 8)) & 4095);
            short s4 = (short) (((((short) (bArr[i5 + 2] & 255)) << 4) | (s2 >> 4)) & 4095);
            if (s3 < 3329) {
                ((short[]) l0Var.c)[i + i4] = s3;
                i4++;
            }
            if (i4 < i2 && s4 < 3329) {
                ((short[]) l0Var.c)[i + i4] = s4;
                i4++;
            }
            i5 = i6;
        }
        return i4;
    }
}
