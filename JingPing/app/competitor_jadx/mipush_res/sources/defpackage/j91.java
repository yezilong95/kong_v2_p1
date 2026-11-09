package defpackage;

/* JADX INFO: loaded from: classes.dex */
public final class j91 extends s8 {
    public final byte[] F;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public j91(byte[] bArr) {
        super(true);
        if (bArr.length != 32) {
            throw new IllegalArgumentException("'buf' must have length 32");
        }
        byte[] bArr2 = new byte[32];
        this.F = bArr2;
        System.arraycopy(bArr, 0, bArr2, 0, 32);
    }

    public final k91 a() {
        byte[] bArr = new byte[32];
        int[] iArr = new int[10];
        int[] iArr2 = new int[10];
        byte[] bArr2 = new byte[32];
        l21.C0(this.F, bArr2);
        c4 c4Var = new c4();
        l21.O0(bArr2, c4Var);
        int[] iArr3 = new int[10];
        int[] iArr4 = new int[10];
        int[] iArr5 = new int[10];
        int[] iArr6 = new int[10];
        t40.e1((int[]) c4Var.b, iArr4);
        t40.e1((int[]) c4Var.c, iArr5);
        t40.e1((int[]) c4Var.d, iArr6);
        t40.C0(iArr4, iArr5, iArr3);
        t40.k1(iArr4, iArr5, iArr4);
        t40.C0(iArr4, iArr6, iArr4);
        t40.e1(iArr6, iArr6);
        t40.C0(iArr3, l21.u, iArr3);
        t40.a(iArr3, iArr6, iArr3);
        t40.a(iArr3, iArr4, iArr3);
        t40.I0(iArr3);
        t40.I0(iArr5);
        t40.I0(iArr6);
        if ((t40.z0(iArr3) & (~t40.z0(iArr5)) & (~t40.z0(iArr6))) == 0) {
            throw new IllegalStateException();
        }
        t40.A(0, 0, (int[]) c4Var.c, iArr);
        t40.A(0, 0, (int[]) c4Var.d, iArr2);
        t40.e(iArr2, iArr, iArr, iArr2);
        int[] iArr7 = new int[10];
        int[] iArr8 = new int[8];
        t40.A(0, 0, iArr2, iArr7);
        t40.I0(iArr7);
        t40.N(0, 0, iArr7, iArr8);
        t40.N(5, 4, iArr7, iArr8);
        ma0.T(t40.t, iArr8, iArr8);
        t40.E(0, 0, iArr8, iArr2);
        t40.E(4, 5, iArr8, iArr2);
        iArr2[9] = iArr2[9] & 16777215;
        t40.C0(iArr, iArr2, iArr);
        t40.I0(iArr);
        t40.M(0, 0, bArr, iArr);
        t40.M(5, 16, bArr, iArr);
        return new k91(bArr);
    }
}
