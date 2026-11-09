package defpackage;

/* JADX INFO: loaded from: classes.dex */
public final class uq extends l21 {
    public final /* synthetic */ int U;
    public final /* synthetic */ int V;
    public final /* synthetic */ long[] W;
    public final /* synthetic */ int[] X;
    public final /* synthetic */ vq Y;

    public uq(vq vqVar, int i, int i2, long[] jArr, int[] iArr) {
        super(13);
        this.Y = vqVar;
        this.U = i;
        this.V = i2;
        this.W = jArr;
        this.X = iArr;
    }

    @Override // defpackage.l21
    public final int P() {
        return this.U;
    }

    @Override // defpackage.l21
    public final or h0(int i) {
        int i2 = this.V;
        long[] jArr = new long[i2];
        long[] jArr2 = new long[i2];
        int i3 = 0;
        for (int i4 = 0; i4 < this.U; i4++) {
            long j = ((i4 ^ i) - 1) >> 31;
            for (int i5 = 0; i5 < i2; i5++) {
                long j2 = jArr[i5];
                long[] jArr3 = this.W;
                jArr[i5] = j2 ^ (jArr3[i3 + i5] & j);
                jArr2[i5] = jArr2[i5] ^ (jArr3[(i3 + i2) + i5] & j);
            }
            i3 += i2 * 2;
        }
        vq vqVar = this.Y;
        int i6 = vqVar.g;
        za0 za0Var = new za0(jArr);
        int[] iArr = this.X;
        return new nr(vqVar, new br(i6, iArr, za0Var), new br(vqVar.g, iArr, new za0(jArr2)), 0);
    }
}
