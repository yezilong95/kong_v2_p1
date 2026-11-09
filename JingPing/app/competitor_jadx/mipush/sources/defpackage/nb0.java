package defpackage;

import io.nats.client.support.NatsJetStreamConstants;

/* JADX INFO: loaded from: classes.dex */
public final class nb0 extends er {
    public final byte[] G;
    public final byte[] H;
    public final byte[] I;
    public final byte[] J;
    public final byte[] K;
    public final byte[] L;

    public nb0(mb0 mb0Var, byte[] bArr) {
        super(mb0Var, true);
        fb0 fb0Var = new fb0(mb0Var.F, 1);
        int length = bArr.length;
        int i = fb0Var.d;
        int i2 = fb0Var.e;
        if (length != 64) {
            this.G = z91.r(bArr, 0, i2);
            this.J = z91.r(bArr, i2, (i2 + i) - 32);
            int i3 = (i - 32) + i2;
            int i4 = i3 + 32;
            this.K = z91.r(bArr, i3, i4);
            int i5 = 64 + i3;
            this.H = z91.r(bArr, i4, i5);
            this.I = z91.r(bArr, i5, i3 + 96);
            this.L = null;
            return;
        }
        byte[] bArrR = z91.r(bArr, 0, 32);
        byte[] bArrR2 = z91.r(bArr, 32, bArr.length);
        kb0 kb0Var = (kb0) fb0Var.g;
        fb0 fb0Var2 = kb0Var.a;
        gd0 gd0Var = new gd0(fb0Var2, (byte) 0);
        Object obj = gd0Var.H;
        gd0 gd0Var2 = new gd0(fb0Var2, (byte) 0);
        int i6 = gd0Var2.F;
        Object obj2 = gd0Var2.H;
        gd0 gd0Var3 = new gd0(fb0Var2, (byte) 0);
        Object obj3 = gd0Var3.H;
        byte[] bArr2 = new byte[64];
        int i7 = kb0Var.b;
        byte[] bArrM = z91.m(bArrR, new byte[]{(byte) i7});
        tb tbVar = kb0Var.e;
        tbVar.getClass();
        int length2 = bArrM.length;
        dq0 dq0Var = (dq0) tbVar.b;
        byte b = 0;
        dq0Var.e(bArrM, 0, length2);
        dq0Var.d(0, bArr2);
        byte[] bArr3 = new byte[32];
        byte[] bArr4 = new byte[32];
        System.arraycopy(bArr2, 0, bArr3, 0, 32);
        System.arraycopy(bArr2, 32, bArr4, 0, 32);
        gd0[] gd0VarArr = new gd0[i7];
        int i8 = 0;
        while (i8 < i7) {
            gd0[] gd0VarArr2 = gd0VarArr;
            gd0VarArr2[i8] = new gd0(fb0Var2, b);
            i8++;
            gd0VarArr = gd0VarArr2;
            b = 0;
        }
        gd0[] gd0VarArr3 = gd0VarArr;
        int i9 = kb0Var.f * 168;
        byte[] bArr5 = new byte[i9 + 2];
        int i10 = 0;
        while (i10 < i7) {
            int i11 = 0;
            while (i11 < i7) {
                byte[] bArr6 = bArrR;
                byte b2 = (byte) i11;
                int i12 = i11;
                gq0 gq0Var = (gq0) tbVar.a;
                gq0Var.n();
                tb tbVar2 = tbVar;
                byte[] bArr7 = new byte[34];
                int i13 = i10;
                System.arraycopy(bArr3, 0, bArr7, 0, 32);
                bArr7[32] = b2;
                bArr7[33] = (byte) i10;
                gq0Var.e(bArr7, 0, 34);
                gq0Var.p(bArr5, 0, i9);
                l0 l0Var = ((l0[]) gd0VarArr3[i13].H)[i12];
                int iA = kb0.a(l0Var, 0, NatsJetStreamConstants.MAX_PULL_SIZE, bArr5, i9);
                int i14 = i9;
                for (int i15 = NatsJetStreamConstants.MAX_PULL_SIZE; iA < i15; i15 = NatsJetStreamConstants.MAX_PULL_SIZE) {
                    int i16 = i14 % 3;
                    int i17 = i14;
                    for (int i18 = 0; i18 < i16; i18++) {
                        bArr5[i18] = bArr5[(i17 - i16) + i18];
                    }
                    gq0Var.p(bArr5, i16, 336);
                    i14 = i16 + 168;
                    iA += kb0.a(((l0[]) gd0VarArr3[i13].H)[i12], iA, 256 - iA, bArr5, i14);
                    gq0Var = gq0Var;
                    i9 = i9;
                }
                i11 = i12 + 1;
                bArrR = bArr6;
                tbVar = tbVar2;
                i10 = i13;
            }
            i10++;
        }
        byte[] bArr8 = bArrR;
        byte b3 = 0;
        for (int i19 = 0; i19 < i7; i19++) {
            ((l0[]) obj)[i19].f(bArr4, b3);
            b3 = (byte) (b3 + 1);
        }
        for (int i20 = 0; i20 < i7; i20++) {
            ((l0[]) obj3)[i20].f(bArr4, b3);
            b3 = (byte) (b3 + 1);
        }
        gd0Var.a();
        gd0Var3.a();
        int i21 = 0;
        while (i21 < i7) {
            l0 l0Var2 = ((l0[]) obj2)[i21];
            gd0 gd0Var4 = gd0VarArr3[i21];
            l0 l0Var3 = new l0(fb0Var2);
            l0.c(l0Var2, ((l0[]) gd0Var4.H)[0], ((l0[]) obj)[0]);
            int i22 = 1;
            while (i22 < fb0Var2.a) {
                l0.c(l0Var3, ((l0[]) gd0Var4.H)[i22], ((l0[]) obj)[i22]);
                int i23 = 0;
                while (i23 < 256) {
                    short[] sArr = (short[]) l0Var2.c;
                    sArr[i23] = (short) (sArr[i23] + ((short[]) l0Var3.c)[i23]);
                    i23++;
                    i21 = i21;
                }
                l0Var2.getClass();
                i22++;
                i21 = i21;
            }
            int i24 = i21;
            l0Var2.o();
            l0 l0Var4 = ((l0[]) obj2)[i24];
            for (int i25 = 0; i25 < 256; i25++) {
                ((short[]) l0Var4.c)[i25] = la0.U(((short[]) l0Var4.c)[i25] * 1353);
            }
            l0Var4.getClass();
            i21 = i24 + 1;
        }
        for (int i26 = 0; i26 < i6; i26++) {
            l0 l0Var5 = ((l0[]) obj2)[i26];
            l0 l0Var6 = ((l0[]) obj3)[i26];
            for (int i27 = 0; i27 < 256; i27++) {
                short[] sArr2 = (short[]) l0Var5.c;
                sArr2[i27] = (short) (sArr2[i27] + ((short[]) l0Var6.c)[i27]);
            }
            l0Var5.getClass();
        }
        for (int i28 = 0; i28 < i6; i28++) {
            ((l0[]) obj2)[i28].o();
        }
        byte[] bArr9 = new byte[kb0Var.c];
        byte[] bArrB = gd0Var2.b();
        int i29 = kb0Var.d;
        System.arraycopy(bArrB, 0, bArr9, 0, i29);
        System.arraycopy(bArr3, 0, bArr9, i29, 32);
        byte[][] bArr10 = {bArr9, gd0Var.b()};
        byte[] bArr11 = new byte[i2];
        System.arraycopy(bArr10[1], 0, bArr11, 0, i2);
        byte[] bArr12 = new byte[32];
        byte[] bArr13 = bArr10[0];
        tb tbVar3 = (tb) fb0Var.h;
        tbVar3.getClass();
        int length3 = bArr13.length;
        dq0 dq0Var2 = (dq0) tbVar3.c;
        dq0Var2.e(bArr13, 0, length3);
        dq0Var2.d(0, bArr12);
        byte[] bArr14 = new byte[i];
        System.arraycopy(bArr10[0], 0, bArr14, 0, i);
        int i30 = i - 32;
        byte[][] bArr15 = {z91.r(bArr14, 0, i30), z91.r(bArr14, i30, i), bArr11, bArr12, bArrR2, z91.m(bArr8, bArrR2)};
        this.G = bArr15[2];
        this.H = bArr15[3];
        this.I = bArr15[4];
        this.J = bArr15[0];
        this.K = bArr15[1];
        this.L = bArr15[5];
    }

    public final byte[] getEncoded() {
        return z91.p(new byte[][]{this.G, this.J, this.K, this.H, this.I});
    }
}
