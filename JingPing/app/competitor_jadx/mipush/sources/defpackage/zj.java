package defpackage;

import androidx.work.Configuration;
import androidx.work.impl.WorkDatabaseVersions;
import androidx.work.impl.WorkManagerImpl;
import io.nats.client.BaseConsumeOptions;

/* JADX INFO: loaded from: classes.dex */
public final class zj extends l21 {
    public final /* synthetic */ int U;
    public final /* synthetic */ int V;
    public final /* synthetic */ Object W;
    public final /* synthetic */ xq X;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public /* synthetic */ zj(xq xqVar, int i, Object obj, int i2) {
        super(13);
        this.U = i2;
        this.X = xqVar;
        this.V = i;
        this.W = obj;
    }

    private final or i1(int i) {
        long[] jArr = new long[7];
        long[] jArr2 = new long[7];
        int i2 = 0;
        for (int i3 = 0; i3 < this.V; i3++) {
            long j = ((i3 ^ i) - 1) >> 31;
            for (int i4 = 0; i4 < 7; i4++) {
                long j2 = jArr[i4];
                long[] jArr3 = (long[]) this.W;
                jArr[i4] = j2 ^ (jArr3[i2 + i4] & j);
                jArr2[i4] = jArr2[i4] ^ (jArr3[(i2 + 7) + i4] & j);
            }
            i2 += 14;
        }
        cu0 cu0Var = new cu0(jArr);
        cu0 cu0Var2 = new cu0(jArr2);
        l21[] l21VarArr = du0.h;
        du0 du0Var = (du0) this.X;
        du0Var.getClass();
        return new nr(du0Var, cu0Var, cu0Var2, l21VarArr, 15);
    }

    @Override // defpackage.l21
    public final int P() {
        switch (this.U) {
        }
        return this.V;
    }

    @Override // defpackage.l21
    public final or h0(int i) {
        switch (this.U) {
            case 0:
                int[] iArr = new int[8];
                int[] iArr2 = new int[8];
                int i2 = 0;
                for (int i3 = 0; i3 < this.V; i3++) {
                    int i4 = ((i3 ^ i) - 1) >> 31;
                    for (int i5 = 0; i5 < 8; i5++) {
                        int i6 = iArr[i5];
                        int[] iArr3 = (int[]) this.W;
                        iArr[i5] = i6 ^ (iArr3[i2 + i5] & i4);
                        iArr2[i5] = iArr2[i5] ^ (iArr3[(i2 + 8) + i5] & i4);
                    }
                    i2 += 16;
                }
                bk bkVar = new bk(iArr);
                bk bkVar2 = new bk(iArr2);
                l21[] l21VarArr = ak.k;
                ak akVar = (ak) this.X;
                akVar.getClass();
                return new ck(akVar, bkVar, bkVar2, l21VarArr, 0);
            case 1:
                int[] iArr4 = new int[8];
                int[] iArr5 = new int[8];
                int i7 = 0;
                for (int i8 = 0; i8 < this.V; i8++) {
                    int i9 = ((i8 ^ i) - 1) >> 31;
                    for (int i10 = 0; i10 < 8; i10++) {
                        int i11 = iArr4[i10];
                        int[] iArr6 = (int[]) this.W;
                        iArr4[i10] = i11 ^ (iArr6[i7 + i10] & i9);
                        iArr5[i10] = iArr5[i10] ^ (iArr6[(i7 + 8) + i10] & i9);
                    }
                    i7 += 16;
                }
                nq0 nq0Var = new nq0(iArr4);
                nq0 nq0Var2 = new nq0(iArr5);
                l21[] l21VarArr2 = mq0.i;
                mq0 mq0Var = (mq0) this.X;
                mq0Var.getClass();
                return new ck(mq0Var, nq0Var, nq0Var2, l21VarArr2, 2);
            case 2:
                int[] iArr7 = new int[4];
                int[] iArr8 = new int[4];
                int i12 = 0;
                for (int i13 = 0; i13 < this.V; i13++) {
                    int i14 = ((i13 ^ i) - 1) >> 31;
                    for (int i15 = 0; i15 < 4; i15++) {
                        int i16 = iArr7[i15];
                        int[] iArr9 = (int[]) this.W;
                        iArr7[i15] = i16 ^ (iArr9[i12 + i15] & i14);
                        iArr8[i15] = iArr8[i15] ^ (iArr9[(i12 + 4) + i15] & i14);
                    }
                    i12 += 8;
                }
                ls0 ls0Var = new ls0(iArr7);
                ls0 ls0Var2 = new ls0(iArr8);
                l21[] l21VarArr3 = ks0.i;
                ks0 ks0Var = (ks0) this.X;
                ks0Var.getClass();
                return new ck(ks0Var, ls0Var, ls0Var2, l21VarArr3, 3);
            case 3:
                int[] iArr10 = new int[5];
                int[] iArr11 = new int[5];
                int i17 = 0;
                for (int i18 = 0; i18 < this.V; i18++) {
                    int i19 = ((i18 ^ i) - 1) >> 31;
                    for (int i20 = 0; i20 < 5; i20++) {
                        int i21 = iArr10[i20];
                        int[] iArr12 = (int[]) this.W;
                        iArr10[i20] = i21 ^ (iArr12[i17 + i20] & i19);
                        iArr11[i20] = iArr11[i20] ^ (iArr12[(i17 + 5) + i20] & i19);
                    }
                    i17 += 10;
                }
                qs0 qs0Var = new qs0(iArr10);
                qs0 qs0Var2 = new qs0(iArr11);
                l21[] l21VarArr4 = ms0.i;
                ms0 ms0Var = (ms0) this.X;
                ms0Var.getClass();
                return new ck(ms0Var, qs0Var, qs0Var2, l21VarArr4, 4);
            case 4:
                int[] iArr13 = new int[5];
                int[] iArr14 = new int[5];
                int i22 = 0;
                for (int i23 = 0; i23 < this.V; i23++) {
                    int i24 = ((i23 ^ i) - 1) >> 31;
                    for (int i25 = 0; i25 < 5; i25++) {
                        int i26 = iArr13[i25];
                        int[] iArr15 = (int[]) this.W;
                        iArr13[i25] = i26 ^ (iArr15[i22 + i25] & i24);
                        iArr14[i25] = iArr14[i25] ^ (iArr15[(i22 + 5) + i25] & i24);
                    }
                    i22 += 10;
                }
                os0 os0Var = new os0(iArr13);
                os0 os0Var2 = new os0(iArr14);
                l21[] l21VarArr5 = ns0.i;
                ns0 ns0Var = (ns0) this.X;
                ns0Var.getClass();
                return new ck(ns0Var, os0Var, os0Var2, l21VarArr5, 5);
            case 5:
                int[] iArr16 = new int[5];
                int[] iArr17 = new int[5];
                int i27 = 0;
                for (int i28 = 0; i28 < this.V; i28++) {
                    int i29 = ((i28 ^ i) - 1) >> 31;
                    for (int i30 = 0; i30 < 5; i30++) {
                        int i31 = iArr16[i30];
                        int[] iArr18 = (int[]) this.W;
                        iArr16[i30] = i31 ^ (iArr18[i27 + i30] & i29);
                        iArr17[i30] = iArr17[i30] ^ (iArr18[(i27 + 5) + i30] & i29);
                    }
                    i27 += 10;
                }
                qs0 qs0Var3 = new qs0(iArr16);
                qs0 qs0Var4 = new qs0(iArr17);
                l21[] l21VarArr6 = ps0.i;
                ps0 ps0Var = (ps0) this.X;
                ps0Var.getClass();
                return new ck(ps0Var, qs0Var3, qs0Var4, l21VarArr6, 6);
            case 6:
                int[] iArr19 = new int[6];
                int[] iArr20 = new int[6];
                int i32 = 0;
                for (int i33 = 0; i33 < this.V; i33++) {
                    int i34 = ((i33 ^ i) - 1) >> 31;
                    for (int i35 = 0; i35 < 6; i35++) {
                        int i36 = iArr19[i35];
                        int[] iArr21 = (int[]) this.W;
                        iArr19[i35] = i36 ^ (iArr21[i32 + i35] & i34);
                        iArr20[i35] = iArr20[i35] ^ (iArr21[(i32 + 6) + i35] & i34);
                    }
                    i32 += 12;
                }
                ss0 ss0Var = new ss0(iArr19);
                ss0 ss0Var2 = new ss0(iArr20);
                l21[] l21VarArr7 = rs0.i;
                rs0 rs0Var = (rs0) this.X;
                rs0Var.getClass();
                return new ck(rs0Var, ss0Var, ss0Var2, l21VarArr7, 7);
            case 7:
                int[] iArr22 = new int[6];
                int[] iArr23 = new int[6];
                int i37 = 0;
                for (int i38 = 0; i38 < this.V; i38++) {
                    int i39 = ((i38 ^ i) - 1) >> 31;
                    for (int i40 = 0; i40 < 6; i40++) {
                        int i41 = iArr22[i40];
                        int[] iArr24 = (int[]) this.W;
                        iArr22[i40] = i41 ^ (iArr24[i37 + i40] & i39);
                        iArr23[i40] = iArr23[i40] ^ (iArr24[(i37 + 6) + i40] & i39);
                    }
                    i37 += 12;
                }
                us0 us0Var = new us0(iArr22);
                us0 us0Var2 = new us0(iArr23);
                l21[] l21VarArr8 = ts0.i;
                ts0 ts0Var = (ts0) this.X;
                ts0Var.getClass();
                return new ck(ts0Var, us0Var, us0Var2, l21VarArr8, 8);
            case 8:
                int[] iArr25 = new int[7];
                int[] iArr26 = new int[7];
                int i42 = 0;
                for (int i43 = 0; i43 < this.V; i43++) {
                    int i44 = ((i43 ^ i) - 1) >> 31;
                    for (int i45 = 0; i45 < 7; i45++) {
                        int i46 = iArr25[i45];
                        int[] iArr27 = (int[]) this.W;
                        iArr25[i45] = i46 ^ (iArr27[i42 + i45] & i44);
                        iArr26[i45] = iArr26[i45] ^ (iArr27[(i42 + 7) + i45] & i44);
                    }
                    i42 += 14;
                }
                ws0 ws0Var = new ws0(iArr25);
                ws0 ws0Var2 = new ws0(iArr26);
                l21[] l21VarArr9 = vs0.i;
                vs0 vs0Var = (vs0) this.X;
                vs0Var.getClass();
                return new ck(vs0Var, ws0Var, ws0Var2, l21VarArr9, 9);
            case WorkDatabaseVersions.VERSION_9 /* 9 */:
                int[] iArr28 = new int[7];
                int[] iArr29 = new int[7];
                int i47 = 0;
                for (int i48 = 0; i48 < this.V; i48++) {
                    int i49 = ((i48 ^ i) - 1) >> 31;
                    for (int i50 = 0; i50 < 7; i50++) {
                        int i51 = iArr28[i50];
                        int[] iArr30 = (int[]) this.W;
                        iArr28[i50] = i51 ^ (iArr30[i47 + i50] & i49);
                        iArr29[i50] = iArr29[i50] ^ (iArr30[(i47 + 7) + i50] & i49);
                    }
                    i47 += 14;
                }
                ys0 ys0Var = new ys0(iArr28);
                ys0 ys0Var2 = new ys0(iArr29);
                l21[] l21VarArr10 = xs0.i;
                xs0 xs0Var = (xs0) this.X;
                xs0Var.getClass();
                return new ck(xs0Var, ys0Var, ys0Var2, l21VarArr10, 10);
            case 10:
                int[] iArr31 = new int[8];
                int[] iArr32 = new int[8];
                int i52 = 0;
                for (int i53 = 0; i53 < this.V; i53++) {
                    int i54 = ((i53 ^ i) - 1) >> 31;
                    for (int i55 = 0; i55 < 8; i55++) {
                        int i56 = iArr31[i55];
                        int[] iArr33 = (int[]) this.W;
                        iArr31[i55] = i56 ^ (iArr33[i52 + i55] & i54);
                        iArr32[i55] = iArr32[i55] ^ (iArr33[(i52 + 8) + i55] & i54);
                    }
                    i52 += 16;
                }
                at0 at0Var = new at0(iArr31);
                at0 at0Var2 = new at0(iArr32);
                l21[] l21VarArr11 = zs0.i;
                zs0 zs0Var = (zs0) this.X;
                zs0Var.getClass();
                return new ck(zs0Var, at0Var, at0Var2, l21VarArr11, 11);
            case WorkDatabaseVersions.VERSION_11 /* 11 */:
                int[] iArr34 = new int[8];
                int[] iArr35 = new int[8];
                int i57 = 0;
                for (int i58 = 0; i58 < this.V; i58++) {
                    int i59 = ((i58 ^ i) - 1) >> 31;
                    for (int i60 = 0; i60 < 8; i60++) {
                        int i61 = iArr34[i60];
                        int[] iArr36 = (int[]) this.W;
                        iArr34[i60] = i61 ^ (iArr36[i57 + i60] & i59);
                        iArr35[i60] = iArr35[i60] ^ (iArr36[(i57 + 8) + i60] & i59);
                    }
                    i57 += 16;
                }
                ct0 ct0Var = new ct0(iArr34);
                ct0 ct0Var2 = new ct0(iArr35);
                l21[] l21VarArr12 = bt0.i;
                bt0 bt0Var = (bt0) this.X;
                bt0Var.getClass();
                return new ck(bt0Var, ct0Var, ct0Var2, l21VarArr12, 12);
            case WorkDatabaseVersions.VERSION_12 /* 12 */:
                int[] iArr37 = new int[12];
                int[] iArr38 = new int[12];
                int i62 = 0;
                for (int i63 = 0; i63 < this.V; i63++) {
                    int i64 = ((i63 ^ i) - 1) >> 31;
                    for (int i65 = 0; i65 < 12; i65++) {
                        int i66 = iArr37[i65];
                        int[] iArr39 = (int[]) this.W;
                        iArr37[i65] = i66 ^ (iArr39[i62 + i65] & i64);
                        iArr38[i65] = iArr38[i65] ^ (iArr39[(i62 + 12) + i65] & i64);
                    }
                    i62 += 24;
                }
                et0 et0Var = new et0(iArr37);
                et0 et0Var2 = new et0(iArr38);
                l21[] l21VarArr13 = dt0.i;
                dt0 dt0Var = (dt0) this.X;
                dt0Var.getClass();
                return new ck(dt0Var, et0Var, et0Var2, l21VarArr13, 13);
            case WorkDatabaseVersions.VERSION_13 /* 13 */:
                int[] iArr40 = new int[17];
                int[] iArr41 = new int[17];
                int i67 = 0;
                for (int i68 = 0; i68 < this.V; i68++) {
                    int i69 = ((i68 ^ i) - 1) >> 31;
                    for (int i70 = 0; i70 < 17; i70++) {
                        int i71 = iArr40[i70];
                        int[] iArr42 = (int[]) this.W;
                        iArr40[i70] = i71 ^ (iArr42[i67 + i70] & i69);
                        iArr41[i70] = iArr41[i70] ^ (iArr42[(i67 + 17) + i70] & i69);
                    }
                    i67 += 34;
                }
                gt0 gt0Var = new gt0(iArr40);
                gt0 gt0Var2 = new gt0(iArr41);
                l21[] l21VarArr14 = ft0.i;
                ft0 ft0Var = (ft0) this.X;
                ft0Var.getClass();
                return new ck(ft0Var, gt0Var, gt0Var2, l21VarArr14, 14);
            case WorkDatabaseVersions.VERSION_14 /* 14 */:
                long[] jArr = new long[2];
                long[] jArr2 = new long[2];
                int i72 = 0;
                for (int i73 = 0; i73 < this.V; i73++) {
                    long j = ((i73 ^ i) - 1) >> 31;
                    for (int i74 = 0; i74 < 2; i74++) {
                        long j2 = jArr[i74];
                        long[] jArr3 = (long[]) this.W;
                        jArr[i74] = j2 ^ (jArr3[i72 + i74] & j);
                        jArr2[i74] = jArr2[i74] ^ (jArr3[(i72 + 2) + i74] & j);
                    }
                    i72 += 4;
                }
                ht0 ht0Var = new ht0(jArr);
                ht0 ht0Var2 = new ht0(jArr2);
                l21[] l21VarArr15 = it0.h;
                it0 it0Var = (it0) this.X;
                it0Var.getClass();
                return new nr(it0Var, ht0Var, ht0Var2, l21VarArr15, 1);
            case WorkDatabaseVersions.VERSION_15 /* 15 */:
                long[] jArr4 = new long[2];
                long[] jArr5 = new long[2];
                int i75 = 0;
                for (int i76 = 0; i76 < this.V; i76++) {
                    long j3 = ((i76 ^ i) - 1) >> 31;
                    for (int i77 = 0; i77 < 2; i77++) {
                        long j4 = jArr4[i77];
                        long[] jArr6 = (long[]) this.W;
                        jArr4[i77] = j4 ^ (jArr6[i75 + i77] & j3);
                        jArr5[i77] = jArr5[i77] ^ (jArr6[(i75 + 2) + i77] & j3);
                    }
                    i75 += 4;
                }
                ht0 ht0Var3 = new ht0(jArr4);
                ht0 ht0Var4 = new ht0(jArr5);
                l21[] l21VarArr16 = jt0.h;
                jt0 jt0Var = (jt0) this.X;
                jt0Var.getClass();
                return new nr(jt0Var, ht0Var3, ht0Var4, l21VarArr16, 2);
            case 16:
                long[] jArr7 = new long[3];
                long[] jArr8 = new long[3];
                int i78 = 0;
                for (int i79 = 0; i79 < this.V; i79++) {
                    long j5 = ((i79 ^ i) - 1) >> 31;
                    for (int i80 = 0; i80 < 3; i80++) {
                        long j6 = jArr7[i80];
                        long[] jArr9 = (long[]) this.W;
                        jArr7[i80] = j6 ^ (jArr9[i78 + i80] & j5);
                        jArr8[i80] = jArr8[i80] ^ (jArr9[(i78 + 3) + i80] & j5);
                    }
                    i78 += 6;
                }
                kt0 kt0Var = new kt0(jArr7);
                kt0 kt0Var2 = new kt0(jArr8);
                l21[] l21VarArr17 = lt0.h;
                lt0 lt0Var = (lt0) this.X;
                lt0Var.getClass();
                return new nr(lt0Var, kt0Var, kt0Var2, l21VarArr17, 3);
            case 17:
                long[] jArr10 = new long[3];
                long[] jArr11 = new long[3];
                int i81 = 0;
                for (int i82 = 0; i82 < this.V; i82++) {
                    long j7 = ((i82 ^ i) - 1) >> 31;
                    for (int i83 = 0; i83 < 3; i83++) {
                        long j8 = jArr10[i83];
                        long[] jArr12 = (long[]) this.W;
                        jArr10[i83] = j8 ^ (jArr12[i81 + i83] & j7);
                        jArr11[i83] = jArr11[i83] ^ (jArr12[(i81 + 3) + i83] & j7);
                    }
                    i81 += 6;
                }
                kt0 kt0Var3 = new kt0(jArr10);
                kt0 kt0Var4 = new kt0(jArr11);
                l21[] l21VarArr18 = mt0.h;
                mt0 mt0Var = (mt0) this.X;
                mt0Var.getClass();
                return new nr(mt0Var, kt0Var3, kt0Var4, l21VarArr18, 4);
            case 18:
                long[] jArr13 = new long[3];
                long[] jArr14 = new long[3];
                int i84 = 0;
                for (int i85 = 0; i85 < this.V; i85++) {
                    long j9 = ((i85 ^ i) - 1) >> 31;
                    for (int i86 = 0; i86 < 3; i86++) {
                        long j10 = jArr13[i86];
                        long[] jArr15 = (long[]) this.W;
                        jArr13[i86] = j10 ^ (jArr15[i84 + i86] & j9);
                        jArr14[i86] = jArr14[i86] ^ (jArr15[(i84 + 3) + i86] & j9);
                    }
                    i84 += 6;
                }
                nt0 nt0Var = new nt0(jArr13);
                nt0 nt0Var2 = new nt0(jArr14);
                l21[] l21VarArr19 = ot0.h;
                ot0 ot0Var = (ot0) this.X;
                ot0Var.getClass();
                return new nr(ot0Var, nt0Var, nt0Var2, l21VarArr19, 5);
            case 19:
                long[] jArr16 = new long[3];
                long[] jArr17 = new long[3];
                int i87 = 0;
                for (int i88 = 0; i88 < this.V; i88++) {
                    long j11 = ((i88 ^ i) - 1) >> 31;
                    for (int i89 = 0; i89 < 3; i89++) {
                        long j12 = jArr16[i89];
                        long[] jArr18 = (long[]) this.W;
                        jArr16[i89] = j12 ^ (jArr18[i87 + i89] & j11);
                        jArr17[i89] = jArr17[i89] ^ (jArr18[(i87 + 3) + i89] & j11);
                    }
                    i87 += 6;
                }
                nt0 nt0Var3 = new nt0(jArr16);
                nt0 nt0Var4 = new nt0(jArr17);
                l21[] l21VarArr20 = pt0.h;
                pt0 pt0Var = (pt0) this.X;
                pt0Var.getClass();
                return new nr(pt0Var, nt0Var3, nt0Var4, l21VarArr20, 6);
            case Configuration.MIN_SCHEDULER_LIMIT /* 20 */:
                long[] jArr19 = new long[3];
                long[] jArr20 = new long[3];
                int i90 = 0;
                for (int i91 = 0; i91 < this.V; i91++) {
                    long j13 = ((i91 ^ i) - 1) >> 31;
                    for (int i92 = 0; i92 < 3; i92++) {
                        long j14 = jArr19[i92];
                        long[] jArr21 = (long[]) this.W;
                        jArr19[i92] = j14 ^ (jArr21[i90 + i92] & j13);
                        jArr20[i92] = jArr20[i92] ^ (jArr21[(i90 + 3) + i92] & j13);
                    }
                    i90 += 6;
                }
                nt0 nt0Var5 = new nt0(jArr19);
                nt0 nt0Var6 = new nt0(jArr20);
                l21[] l21VarArr21 = qt0.h;
                qt0 qt0Var = (qt0) this.X;
                qt0Var.getClass();
                return new nr(qt0Var, nt0Var5, nt0Var6, l21VarArr21, 7);
            case 21:
                long[] jArr22 = new long[4];
                long[] jArr23 = new long[4];
                int i93 = 0;
                for (int i94 = 0; i94 < this.V; i94++) {
                    long j15 = ((i94 ^ i) - 1) >> 31;
                    for (int i95 = 0; i95 < 4; i95++) {
                        long j16 = jArr22[i95];
                        long[] jArr24 = (long[]) this.W;
                        jArr22[i95] = j16 ^ (jArr24[i93 + i95] & j15);
                        jArr23[i95] = jArr23[i95] ^ (jArr24[(i93 + 4) + i95] & j15);
                    }
                    i93 += 8;
                }
                rt0 rt0Var = new rt0(jArr22);
                rt0 rt0Var2 = new rt0(jArr23);
                l21[] l21VarArr22 = st0.h;
                st0 st0Var = (st0) this.X;
                st0Var.getClass();
                return new nr(st0Var, rt0Var, rt0Var2, l21VarArr22, 8);
            case WorkManagerImpl.MAX_PRE_JOB_SCHEDULER_API_LEVEL /* 22 */:
                long[] jArr25 = new long[4];
                long[] jArr26 = new long[4];
                int i96 = 0;
                for (int i97 = 0; i97 < this.V; i97++) {
                    long j17 = ((i97 ^ i) - 1) >> 31;
                    for (int i98 = 0; i98 < 4; i98++) {
                        long j18 = jArr25[i98];
                        long[] jArr27 = (long[]) this.W;
                        jArr25[i98] = j18 ^ (jArr27[i96 + i98] & j17);
                        jArr26[i98] = jArr26[i98] ^ (jArr27[(i96 + 4) + i98] & j17);
                    }
                    i96 += 8;
                }
                rt0 rt0Var3 = new rt0(jArr25);
                rt0 rt0Var4 = new rt0(jArr26);
                l21[] l21VarArr23 = tt0.h;
                tt0 tt0Var = (tt0) this.X;
                tt0Var.getClass();
                return new nr(tt0Var, rt0Var3, rt0Var4, l21VarArr23, 9);
            case WorkManagerImpl.MIN_JOB_SCHEDULER_API_LEVEL /* 23 */:
                long[] jArr28 = new long[4];
                long[] jArr29 = new long[4];
                int i99 = 0;
                for (int i100 = 0; i100 < this.V; i100++) {
                    long j19 = ((i100 ^ i) - 1) >> 31;
                    for (int i101 = 0; i101 < 4; i101++) {
                        long j20 = jArr28[i101];
                        long[] jArr30 = (long[]) this.W;
                        jArr28[i101] = j20 ^ (jArr30[i99 + i101] & j19);
                        jArr29[i101] = jArr29[i101] ^ (jArr30[(i99 + 4) + i101] & j19);
                    }
                    i99 += 8;
                }
                ut0 ut0Var = new ut0(jArr28);
                ut0 ut0Var2 = new ut0(jArr29);
                l21[] l21VarArr24 = vt0.h;
                vt0 vt0Var = (vt0) this.X;
                vt0Var.getClass();
                return new nr(vt0Var, ut0Var, ut0Var2, l21VarArr24, 10);
            case 24:
                long[] jArr31 = new long[4];
                long[] jArr32 = new long[4];
                int i102 = 0;
                for (int i103 = 0; i103 < this.V; i103++) {
                    long j21 = ((i103 ^ i) - 1) >> 31;
                    for (int i104 = 0; i104 < 4; i104++) {
                        long j22 = jArr31[i104];
                        long[] jArr33 = (long[]) this.W;
                        jArr31[i104] = j22 ^ (jArr33[i102 + i104] & j21);
                        jArr32[i104] = jArr32[i104] ^ (jArr33[(i102 + 4) + i104] & j21);
                    }
                    i102 += 8;
                }
                ut0 ut0Var3 = new ut0(jArr31);
                ut0 ut0Var4 = new ut0(jArr32);
                l21[] l21VarArr25 = wt0.h;
                wt0 wt0Var = (wt0) this.X;
                wt0Var.getClass();
                return new nr(wt0Var, ut0Var3, ut0Var4, l21VarArr25, 11);
            case BaseConsumeOptions.DEFAULT_THRESHOLD_PERCENT /* 25 */:
                long[] jArr34 = new long[4];
                long[] jArr35 = new long[4];
                int i105 = 0;
                for (int i106 = 0; i106 < this.V; i106++) {
                    long j23 = ((i106 ^ i) - 1) >> 31;
                    for (int i107 = 0; i107 < 4; i107++) {
                        long j24 = jArr34[i107];
                        long[] jArr36 = (long[]) this.W;
                        jArr34[i107] = j24 ^ (jArr36[i105 + i107] & j23);
                        jArr35[i107] = jArr35[i107] ^ (jArr36[(i105 + 4) + i107] & j23);
                    }
                    i105 += 8;
                }
                xt0 xt0Var = new xt0(jArr34);
                xt0 xt0Var2 = new xt0(jArr35);
                l21[] l21VarArr26 = yt0.h;
                yt0 yt0Var = (yt0) this.X;
                yt0Var.getClass();
                return new nr(yt0Var, xt0Var, xt0Var2, l21VarArr26, 12);
            case 26:
                long[] jArr37 = new long[5];
                long[] jArr38 = new long[5];
                int i108 = 0;
                for (int i109 = 0; i109 < this.V; i109++) {
                    long j25 = ((i109 ^ i) - 1) >> 31;
                    for (int i110 = 0; i110 < 5; i110++) {
                        long j26 = jArr37[i110];
                        long[] jArr39 = (long[]) this.W;
                        jArr37[i110] = j26 ^ (jArr39[i108 + i110] & j25);
                        jArr38[i110] = jArr38[i110] ^ (jArr39[(i108 + 5) + i110] & j25);
                    }
                    i108 += 10;
                }
                zt0 zt0Var = new zt0(jArr37);
                zt0 zt0Var2 = new zt0(jArr38);
                l21[] l21VarArr27 = au0.h;
                au0 au0Var = (au0) this.X;
                au0Var.getClass();
                return new nr(au0Var, zt0Var, zt0Var2, l21VarArr27, 13);
            case 27:
                long[] jArr40 = new long[5];
                long[] jArr41 = new long[5];
                int i111 = 0;
                for (int i112 = 0; i112 < this.V; i112++) {
                    long j27 = ((i112 ^ i) - 1) >> 31;
                    for (int i113 = 0; i113 < 5; i113++) {
                        long j28 = jArr40[i113];
                        long[] jArr42 = (long[]) this.W;
                        jArr40[i113] = j28 ^ (jArr42[i111 + i113] & j27);
                        jArr41[i113] = jArr41[i113] ^ (jArr42[(i111 + 5) + i113] & j27);
                    }
                    i111 += 10;
                }
                zt0 zt0Var3 = new zt0(jArr40);
                zt0 zt0Var4 = new zt0(jArr41);
                l21[] l21VarArr28 = bu0.h;
                bu0 bu0Var = (bu0) this.X;
                bu0Var.getClass();
                return new nr(bu0Var, zt0Var3, zt0Var4, l21VarArr28, 14);
            case 28:
                return i1(i);
            default:
                long[] jArr43 = new long[7];
                long[] jArr44 = new long[7];
                int i114 = 0;
                for (int i115 = 0; i115 < this.V; i115++) {
                    long j29 = ((i115 ^ i) - 1) >> 31;
                    for (int i116 = 0; i116 < 7; i116++) {
                        long j30 = jArr43[i116];
                        long[] jArr45 = (long[]) this.W;
                        jArr43[i116] = j30 ^ (jArr45[i114 + i116] & j29);
                        jArr44[i116] = jArr44[i116] ^ (jArr45[(i114 + 7) + i116] & j29);
                    }
                    i114 += 14;
                }
                cu0 cu0Var = new cu0(jArr43);
                cu0 cu0Var2 = new cu0(jArr44);
                l21[] l21VarArr29 = eu0.h;
                eu0 eu0Var = (eu0) this.X;
                eu0Var.getClass();
                return new nr(eu0Var, cu0Var, cu0Var2, l21VarArr29, 16);
        }
    }
}
