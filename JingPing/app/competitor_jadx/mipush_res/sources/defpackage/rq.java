package defpackage;

import java.math.BigInteger;

/* JADX INFO: loaded from: classes.dex */
public final class rq extends l21 {
    public final /* synthetic */ int U;
    public final /* synthetic */ int V;
    public final /* synthetic */ byte[] W;
    public final /* synthetic */ xq X;

    public rq(xq xqVar, int i, int i2, byte[] bArr) {
        super(13);
        this.X = xqVar;
        this.U = i;
        this.V = i2;
        this.W = bArr;
    }

    @Override // defpackage.l21
    public final int P() {
        return this.U;
    }

    @Override // defpackage.l21
    public final or h0(int i) {
        int i2 = this.V;
        byte[] bArr = new byte[i2];
        byte[] bArr2 = new byte[i2];
        int i3 = 0;
        for (int i4 = 0; i4 < this.U; i4++) {
            int i5 = ((i4 ^ i) - 1) >> 31;
            for (int i6 = 0; i6 < i2; i6++) {
                byte b = bArr[i6];
                byte[] bArr3 = this.W;
                bArr[i6] = (byte) (b ^ (bArr3[i3 + i6] & i5));
                bArr2[i6] = (byte) (bArr2[i6] ^ (bArr3[(i3 + i2) + i6] & i5));
            }
            i3 += i2 * 2;
        }
        BigInteger bigInteger = new BigInteger(1, bArr);
        xq xqVar = this.X;
        return xqVar.d(xqVar.h(bigInteger), xqVar.h(new BigInteger(1, bArr2)));
    }
}
