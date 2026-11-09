package defpackage;

/* JADX INFO: loaded from: classes.dex */
public final class l91 extends s8 {
    public final byte[] F;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public l91(byte[] bArr) {
        super(true);
        if (bArr.length != 56) {
            throw new IllegalArgumentException("'buf' must have length 56");
        }
        byte[] bArr2 = new byte[56];
        this.F = bArr2;
        System.arraycopy(bArr, 0, bArr2, 0, 56);
    }

    public final m91 a() {
        byte[] bArr = new byte[56];
        int[] iArr = new int[16];
        int[] iArr2 = new int[16];
        byte[] bArr2 = new byte[57];
        j31.P(this.F, bArr2);
        yr yrVar = new yr(1);
        j31.Y(bArr2, yrVar);
        int[] iArr3 = new int[16];
        int[] iArr4 = new int[16];
        int[] iArr5 = new int[16];
        int[] iArr6 = new int[16];
        l21.S0(yrVar.a, iArr4);
        l21.S0(yrVar.b, iArr5);
        l21.S0(yrVar.c, iArr6);
        l21.j0(iArr4, iArr5, iArr3);
        l21.f(iArr4, iArr5, iArr4);
        l21.j0(iArr4, iArr6, iArr4);
        l21.S0(iArr6, iArr6);
        l21.i0(iArr3, iArr3);
        l21.c1(iArr3, iArr6, iArr3);
        l21.f(iArr3, iArr4, iArr3);
        l21.G0(iArr3, 1);
        l21.G0(iArr3, -1);
        l21.G0(iArr5, 1);
        l21.G0(iArr5, -1);
        l21.G0(iArr6, 1);
        l21.G0(iArr6, -1);
        if (((~l21.f0(iArr6)) & l21.f0(iArr3) & (~l21.f0(iArr5))) == 0) {
            throw new IllegalStateException();
        }
        l21.t(0, 0, yrVar.a, iArr);
        l21.t(0, 0, yrVar.b, iArr2);
        l21.X(iArr, iArr);
        l21.j0(iArr, iArr2, iArr);
        l21.S0(iArr, iArr);
        l21.G0(iArr, 1);
        l21.G0(iArr, -1);
        l21.C(0, 0, bArr, iArr);
        l21.C(2, 7, bArr, iArr);
        l21.C(4, 14, bArr, iArr);
        l21.C(6, 21, bArr, iArr);
        l21.C(8, 28, bArr, iArr);
        l21.C(10, 35, bArr, iArr);
        l21.C(12, 42, bArr, iArr);
        l21.C(14, 49, bArr, iArr);
        return new m91(bArr);
    }
}
