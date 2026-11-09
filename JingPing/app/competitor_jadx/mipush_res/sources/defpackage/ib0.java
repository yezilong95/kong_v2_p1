package defpackage;

import io.nats.client.support.NatsJetStreamConstants;
import kotlin.uuid.Uuid;

/* JADX INFO: loaded from: classes.dex */
public final class ib0 extends er {
    public final byte[] G;
    public final byte[] H;
    public final byte[] I;
    public final byte[] J;
    public final byte[] K;
    public final byte[] L;
    public final byte[] M;
    public final byte[] N;

    public ib0(hb0 hb0Var, byte[] bArr, jb0 jb0Var) {
        zk0[] zk0VarArr;
        int i;
        int[] iArr;
        int i2;
        int i3;
        int i4;
        super(hb0Var, true);
        fb0 fb0Var = new fb0(hb0Var.a, 0);
        int length = bArr.length;
        int i5 = fb0Var.c;
        int i6 = fb0Var.a;
        int i7 = fb0Var.b;
        if (length != 32) {
            this.G = z91.r(bArr, 0, 32);
            this.H = z91.r(bArr, 32, 64);
            this.I = z91.r(bArr, 64, Uuid.SIZE_BITS);
            int i8 = (i5 * i6) + Uuid.SIZE_BITS;
            this.J = z91.r(bArr, Uuid.SIZE_BITS, i8);
            int i9 = (i7 * i6) + i8;
            this.K = z91.r(bArr, i8, i9);
            this.L = z91.r(bArr, i9, (i7 * 416) + i9);
            if (jb0Var != null) {
                this.M = z91.l(jb0Var.H);
            } else {
                this.M = null;
            }
            this.N = null;
            return;
        }
        byte[] bArr2 = new byte[Uuid.SIZE_BITS];
        byte[] bArr3 = new byte[64];
        byte[] bArr4 = new byte[32];
        byte[] bArr5 = new byte[64];
        byte[] bArr6 = new byte[32];
        gd0 gd0Var = new gd0(fb0Var);
        zk0 zk0Var = new zk0(fb0Var, 1);
        int i10 = zk0Var.c;
        ou[] ouVarArr = zk0Var.b;
        zk0 zk0Var2 = new zk0(fb0Var, 0);
        ou[] ouVarArr2 = zk0Var2.b;
        zk0 zk0Var3 = new zk0(fb0Var, 0);
        int i11 = zk0Var3.c;
        ou[] ouVarArr3 = zk0Var3.b;
        ou[] ouVarArr4 = new zk0(fb0Var, 0).b;
        gq0 gq0Var = (gq0) fb0Var.g;
        gq0Var.e(bArr, 0, 32);
        gq0Var.f((byte) i7);
        gq0Var.f((byte) i5);
        gq0Var.h(bArr2, 0, Uuid.SIZE_BITS);
        System.arraycopy(bArr2, 0, bArr4, 0, 32);
        System.arraycopy(bArr2, 32, bArr5, 0, 64);
        System.arraycopy(bArr2, 96, bArr6, 0, 32);
        int i12 = 0;
        while (true) {
            zk0VarArr = (zk0[]) gd0Var.H;
            i = gd0Var.F;
            if (i12 >= i) {
                break;
            }
            int i13 = 0;
            while (i13 < gd0Var.G) {
                ou ouVar = zk0VarArr[i12].b[i13];
                int i14 = i12;
                short s = (short) ((i12 << 8) + i13);
                zk0[] zk0VarArr2 = zk0VarArr;
                i6 i6Var = (i6) ouVar.H;
                i6Var.getClass();
                gd0 gd0Var2 = gd0Var;
                byte[] bArr7 = new byte[842];
                gq0 gq0Var2 = (gq0) i6Var.F;
                gq0Var2.n();
                int i15 = i13;
                byte[] bArr8 = bArr6;
                gq0Var2.e(bArr4, 0, 32);
                gq0Var2.e(new byte[]{(byte) s, (byte) (s >> 8)}, 0, 2);
                int i16 = 840;
                gq0Var2.p(bArr7, 0, 840);
                int iU = ou.U(ouVar, 0, NatsJetStreamConstants.MAX_PULL_SIZE, bArr7, 840);
                for (int i17 = NatsJetStreamConstants.MAX_PULL_SIZE; iU < i17; i17 = NatsJetStreamConstants.MAX_PULL_SIZE) {
                    int i18 = i16 % 3;
                    int i19 = i16;
                    for (int i20 = 0; i20 < i18; i20++) {
                        bArr7[i20] = bArr7[(i19 - i18) + i20];
                    }
                    gq0Var2.p(bArr7, i18, 168);
                    i16 = 168 + i18;
                    iU += ou.U(ouVar, iU, 256 - iU, bArr7, i16);
                    gq0Var2 = gq0Var2;
                }
                i13 = i15 + 1;
                zk0VarArr = zk0VarArr2;
                i12 = i14;
                gd0Var = gd0Var2;
                bArr6 = bArr8;
            }
            i12++;
        }
        byte[] bArr9 = bArr6;
        int i21 = 0;
        short s2 = 0;
        while (i21 < i10) {
            ouVarArr[i21].X(bArr5, s2);
            i21++;
            s2 = (short) (s2 + 1);
        }
        short s3 = (short) i5;
        int i22 = 0;
        while (i22 < zk0Var2.c) {
            ouVarArr2[i22].X(bArr5, s3);
            i22++;
            s3 = (short) (s3 + 1);
        }
        zk0 zk0Var4 = new zk0(fb0Var, 1);
        ou[] ouVarArr5 = zk0Var4.b;
        for (int i23 = 0; i23 < i10; i23++) {
            for (int i24 = 0; i24 < 256; i24++) {
                ((int[]) ouVarArr5[i23].F)[i24] = ((int[]) ouVarArr[i23].F)[i24];
            }
        }
        int i25 = 0;
        while (true) {
            int i26 = zk0Var4.c;
            iArr = l21.C;
            if (i25 >= i26) {
                break;
            }
            ou ouVar2 = ouVarArr5[i25];
            int[] iArr2 = (int[]) ouVar2.F;
            int iY = z91.y(0, iArr2.length);
            int[] iArr3 = new int[iY];
            zk0 zk0Var5 = zk0Var4;
            System.arraycopy(iArr2, 0, iArr3, 0, Math.min(iArr2.length, iY));
            int i27 = Uuid.SIZE_BITS;
            int i28 = 0;
            while (i27 > 0) {
                int i29 = i27;
                int i30 = 0;
                while (i30 < 256) {
                    i28++;
                    int i31 = iArr[i28];
                    ou[] ouVarArr6 = ouVarArr5;
                    int i32 = i25;
                    int i33 = i30;
                    while (i33 < i30 + i29) {
                        int i34 = i33 + i29;
                        int i35 = i31;
                        int i36 = i33;
                        int iV = ma0.V(((long) i31) * ((long) iArr3[i34]));
                        iArr3[i34] = iArr3[i36] - iV;
                        iArr3[i36] = iArr3[i36] + iV;
                        i33 = i36 + 1;
                        i5 = i5;
                        i30 = i30;
                        i31 = i35;
                    }
                    i30 = i33 + i29;
                    ouVarArr5 = ouVarArr6;
                    i25 = i32;
                }
                i27 = i29 >>> 1;
                i25 = i25;
            }
            ouVar2.F = iArr3;
            i5 = i5;
            ouVarArr5 = ouVarArr5;
            i25++;
            zk0Var4 = zk0Var5;
        }
        ou[] ouVarArr7 = ouVarArr5;
        int i37 = i5;
        int i38 = 0;
        while (i38 < i) {
            ou ouVar3 = ouVarArr3[i38];
            zk0 zk0Var6 = zk0VarArr[i38];
            fb0 fb0Var2 = (fb0) ouVar3.G;
            int[] iArr4 = new int[NatsJetStreamConstants.MAX_PULL_SIZE];
            ((i6) fb0Var2.h).getClass();
            ou ouVar4 = zk0Var6.b[0];
            ou ouVar5 = ouVarArr7[0];
            int i39 = i38;
            int i40 = i37;
            int i41 = 0;
            while (i41 < 256) {
                ((int[]) ouVar3.F)[i41] = ma0.V(((long) ((int[]) ouVar5.F)[i41]) * ((long) ((int[]) ouVar4.F)[i41]));
                i41++;
                i = i;
                iArr = iArr;
            }
            int[] iArr5 = iArr;
            int i42 = i;
            int i43 = 1;
            while (i43 < fb0Var2.c) {
                ou ouVar6 = zk0Var6.b[i43];
                ou ouVar7 = ouVarArr7[i43];
                int i44 = 0;
                while (true) {
                    if (i44 >= 256) {
                        break;
                    }
                    iArr4[i44] = ma0.V(((long) ((int[]) ouVar6.F)[i44]) * ((long) ((int[]) ouVar7.F)[i44]));
                    i44++;
                    i43 = i43;
                    zk0Var6 = zk0Var6;
                    ouVar6 = ouVar6;
                }
                int i45 = i43;
                zk0 zk0Var7 = zk0Var6;
                int i46 = 0;
                for (i4 = NatsJetStreamConstants.MAX_PULL_SIZE; i46 < i4; i4 = NatsJetStreamConstants.MAX_PULL_SIZE) {
                    int[] iArr6 = (int[]) ouVar3.F;
                    iArr6[i46] = iArr6[i46] + iArr4[i46];
                    i46++;
                }
                i43 = i45 + 1;
                zk0Var6 = zk0Var7;
            }
            i38 = i39 + 1;
            i37 = i40;
            i = i42;
            iArr = iArr5;
        }
        int i47 = i37;
        int[] iArr7 = iArr;
        int i48 = 0;
        while (true) {
            i2 = 8380417;
            if (i48 >= i11) {
                break;
            }
            ou ouVar8 = ouVarArr3[i48];
            for (int i49 = 0; i49 < 256; i49++) {
                int[] iArr8 = (int[]) ouVar8.F;
                int i50 = iArr8[i49];
                iArr8[i49] = i50 - (((4194304 + i50) >> 23) * 8380417);
            }
            ouVar8.getClass();
            i48++;
        }
        int i51 = 0;
        while (i51 < i11) {
            ou ouVar9 = ouVarArr3[i51];
            int[] iArr9 = (int[]) ouVar9.F;
            int iY2 = z91.y(0, iArr9.length);
            int[] iArr10 = new int[iY2];
            System.arraycopy(iArr9, 0, iArr10, 0, Math.min(iArr9.length, iY2));
            int i52 = 1;
            int i53 = NatsJetStreamConstants.MAX_PULL_SIZE;
            while (true) {
                i3 = NatsJetStreamConstants.MAX_PULL_SIZE;
                if (i52 >= 256) {
                    break;
                }
                int i54 = i53;
                int i55 = 0;
                while (i55 < i3) {
                    i54--;
                    int i56 = iArr7[i54] * (-1);
                    int i57 = i55;
                    while (i57 < i55 + i52) {
                        int i58 = iArr10[i57];
                        int i59 = i57 + i52;
                        iArr10[i57] = i58 + iArr10[i59];
                        int i60 = i58 - iArr10[i59];
                        iArr10[i59] = i60;
                        iArr10[i59] = ma0.V(((long) i56) * ((long) i60));
                        i57++;
                        bArr3 = bArr3;
                        i2 = i2;
                        i51 = i51;
                    }
                    i55 = i57 + i52;
                    i3 = NatsJetStreamConstants.MAX_PULL_SIZE;
                }
                i52 <<= 1;
                i53 = i54;
            }
            int i61 = i51;
            int i62 = i2;
            byte[] bArr10 = bArr3;
            int i63 = 0;
            while (i63 < i3) {
                iArr10[i63] = ma0.V(((long) iArr10[i63]) * 41978);
                i63++;
                i3 = NatsJetStreamConstants.MAX_PULL_SIZE;
            }
            ouVar9.F = iArr10;
            i51 = i61 + 1;
            bArr3 = bArr10;
            i2 = i62;
        }
        int i64 = i2;
        byte[] bArr11 = bArr3;
        for (int i65 = 0; i65 < i11; i65++) {
            ou ouVar10 = ouVarArr3[i65];
            ou ouVar11 = ouVarArr2[i65];
            for (int i66 = 0; i66 < 256; i66++) {
                int[] iArr11 = (int[]) ouVar10.F;
                iArr11[i66] = iArr11[i66] + ((int[]) ouVar11.F)[i66];
            }
            ouVar10.getClass();
        }
        for (int i67 = 0; i67 < i11; i67++) {
            ou ouVar12 = ouVarArr3[i67];
            for (int i68 = 0; i68 < 256; i68++) {
                int[] iArr12 = (int[]) ouVar12.F;
                int i69 = iArr12[i68];
                iArr12[i68] = i69 + ((i69 >> 31) & i64);
            }
            ouVar12.getClass();
        }
        for (int i70 = 0; i70 < i11; i70++) {
            ou ouVar13 = ouVarArr3[i70];
            ou ouVar14 = ouVarArr4[i70];
            for (int i71 = 0; i71 < 256; i71++) {
                int[] iArr13 = (int[]) ouVar13.F;
                int i72 = iArr13[i71];
                int i73 = (i72 + 4095) >> 13;
                int[] iArr14 = {i73, i72 - (i73 << 13)};
                iArr13[i71] = iArr14[0];
                ((int[]) ouVar14.F)[i71] = iArr14[1];
            }
            ouVar13.getClass();
        }
        int i74 = fb0Var.f - 32;
        byte[] bArr12 = new byte[i74];
        for (int i75 = 0; i75 < i7; i75++) {
            ou ouVar15 = ouVarArr3[i75];
            byte[] bArr13 = new byte[320];
            for (int i76 = 0; i76 < 64; i76++) {
                int i77 = i76 * 5;
                int[] iArr15 = (int[]) ouVar15.F;
                int i78 = i76 * 4;
                int i79 = iArr15[i78];
                bArr13[i77] = (byte) i79;
                int i80 = iArr15[i78 + 1];
                bArr13[i77 + 1] = (byte) ((i79 >> 8) | (i80 << 2));
                int i81 = iArr15[i78 + 2];
                bArr13[i77 + 2] = (byte) ((i80 >> 6) | (i81 << 4));
                int i82 = iArr15[i78 + 3];
                bArr13[i77 + 3] = (byte) ((i81 >> 4) | (i82 << 6));
                bArr13[i77 + 4] = (byte) (i82 >> 2);
            }
            ouVar15.getClass();
            System.arraycopy(bArr13, 0, bArr12, i75 * 320, 320);
        }
        gq0Var.e(bArr4, 0, 32);
        gq0Var.e(bArr12, 0, i74);
        gq0Var.h(bArr11, 0, 64);
        byte[][] bArr14 = new byte[6][];
        bArr14[0] = bArr4;
        bArr14[1] = bArr9;
        bArr14[2] = bArr11;
        bArr14[3] = new byte[i47 * i6];
        for (int i83 = 0; i83 < i47; i83++) {
            ouVarArr[i83].Q(i83 * i6, bArr14[3]);
        }
        bArr14[4] = new byte[i7 * i6];
        for (int i84 = 0; i84 < i7; i84++) {
            ouVarArr2[i84].Q(i84 * i6, bArr14[4]);
        }
        bArr14[5] = new byte[i7 * 416];
        for (int i85 = 0; i85 < i7; i85++) {
            ou ouVar16 = ouVarArr4[i85];
            byte[] bArr15 = bArr14[5];
            int i86 = i85 * 416;
            int i87 = 0;
            while (i87 < 32) {
                int i88 = i87 * 8;
                int[] iArr16 = (int[]) ouVar16.F;
                ou ouVar17 = ouVar16;
                int[] iArr17 = {4096 - iArr16[i88], 4096 - iArr16[i88 + 1], 4096 - iArr16[i88 + 2], 4096 - iArr16[i88 + 3], 4096 - iArr16[i88 + 4], 4096 - iArr16[i88 + 5], 4096 - iArr16[i88 + 6], 4096 - iArr16[i88 + 7]};
                int i89 = (i87 * 13) + i86;
                int i90 = iArr17[0];
                bArr15[i89] = (byte) i90;
                int i91 = i89 + 1;
                byte b = (byte) (i90 >> 8);
                bArr15[i91] = b;
                int i92 = iArr17[1];
                bArr15[i91] = (byte) (b | ((byte) (i92 << 5)));
                bArr15[i89 + 2] = (byte) (i92 >> 3);
                int i93 = i89 + 3;
                byte b2 = (byte) (i92 >> 11);
                bArr15[i93] = b2;
                int i94 = iArr17[2];
                bArr15[i93] = (byte) (b2 | ((byte) (i94 << 2)));
                int i95 = i89 + 4;
                byte b3 = (byte) (i94 >> 6);
                bArr15[i95] = b3;
                int i96 = iArr17[3];
                bArr15[i95] = (byte) (b3 | ((byte) (i96 << 7)));
                bArr15[i89 + 5] = (byte) (i96 >> 1);
                int i97 = i89 + 6;
                byte b4 = (byte) (i96 >> 9);
                bArr15[i97] = b4;
                int i98 = iArr17[4];
                bArr15[i97] = (byte) (b4 | ((byte) (i98 << 4)));
                bArr15[i89 + 7] = (byte) (i98 >> 4);
                int i99 = i89 + 8;
                byte b5 = (byte) (i98 >> 12);
                bArr15[i99] = b5;
                int i100 = iArr17[5];
                bArr15[i99] = (byte) (b5 | ((byte) (i100 << 1)));
                int i101 = i89 + 9;
                byte b6 = (byte) (i100 >> 7);
                bArr15[i101] = b6;
                int i102 = iArr17[6];
                bArr15[i101] = (byte) (b6 | ((byte) (i102 << 6)));
                bArr15[i89 + 10] = (byte) (i102 >> 2);
                int i103 = i89 + 11;
                byte b7 = (byte) (i102 >> 10);
                bArr15[i103] = b7;
                int i104 = iArr17[7];
                bArr15[i103] = (byte) (b7 | ((byte) (i104 << 3)));
                bArr15[i89 + 12] = (byte) (i104 >> 5);
                i87++;
                ouVar16 = ouVar17;
            }
            ouVar16.getClass();
        }
        byte[][] bArr16 = {bArr14[0], bArr14[1], bArr14[2], bArr14[3], bArr14[4], bArr14[5], bArr12, bArr};
        this.G = bArr16[0];
        this.H = bArr16[1];
        this.I = bArr16[2];
        this.J = bArr16[3];
        this.K = bArr16[4];
        this.L = bArr16[5];
        this.M = bArr16[6];
        this.N = bArr16[7];
    }

    public final byte[] getEncoded() {
        return z91.p(new byte[][]{this.G, this.H, this.I, this.J, this.K, this.L});
    }
}
