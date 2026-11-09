package defpackage;

/* JADX INFO: loaded from: classes.dex */
public final class gu0 extends l21 {
    public final /* synthetic */ int U;
    public final /* synthetic */ int V;
    public final /* synthetic */ long[] W;
    public final /* synthetic */ sq X;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public /* synthetic */ gu0(sq sqVar, int i, long[] jArr, int i2) {
        super(13);
        this.U = i2;
        this.X = sqVar;
        this.V = i;
        this.W = jArr;
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
                long[] jArr = new long[9];
                long[] jArr2 = new long[9];
                int i2 = 0;
                for (int i3 = 0; i3 < this.V; i3++) {
                    long j = ((i3 ^ i) - 1) >> 31;
                    for (int i4 = 0; i4 < 9; i4++) {
                        long j2 = jArr[i4];
                        long[] jArr3 = this.W;
                        jArr[i4] = j2 ^ (jArr3[i2 + i4] & j);
                        jArr2[i4] = jArr2[i4] ^ (jArr3[(i2 + 9) + i4] & j);
                    }
                    i2 += 18;
                }
                fu0 fu0Var = new fu0(jArr);
                fu0 fu0Var2 = new fu0(jArr2);
                l21[] l21VarArr = hu0.h;
                hu0 hu0Var = (hu0) this.X;
                hu0Var.getClass();
                return new nr(hu0Var, fu0Var, fu0Var2, l21VarArr, 17);
            default:
                long[] jArr4 = new long[9];
                long[] jArr5 = new long[9];
                int i5 = 0;
                for (int i6 = 0; i6 < this.V; i6++) {
                    long j3 = ((i6 ^ i) - 1) >> 31;
                    for (int i7 = 0; i7 < 9; i7++) {
                        long j4 = jArr4[i7];
                        long[] jArr6 = this.W;
                        jArr4[i7] = j4 ^ (jArr6[i5 + i7] & j3);
                        jArr5[i7] = jArr5[i7] ^ (jArr6[(i5 + 9) + i7] & j3);
                    }
                    i5 += 18;
                }
                fu0 fu0Var3 = new fu0(jArr4);
                fu0 fu0Var4 = new fu0(jArr5);
                l21[] l21VarArr2 = iu0.h;
                iu0 iu0Var = (iu0) this.X;
                iu0Var.getClass();
                return new nr(iu0Var, fu0Var3, fu0Var4, l21VarArr2, 18);
        }
    }
}
