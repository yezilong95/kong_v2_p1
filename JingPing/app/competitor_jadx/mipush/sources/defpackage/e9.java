package defpackage;

import java.io.IOException;
import kotlin.uuid.Uuid;

/* JADX INFO: loaded from: classes.dex */
public final class e9 extends e0 {
    public final int H;
    public final e0[] I;

    public e9(byte[] bArr, e0[] e0VarArr) {
        super(bArr);
        this.I = e0VarArr;
        this.H = 1000;
    }

    public static byte[] p(e0[] e0VarArr) {
        int length = e0VarArr.length;
        if (length == 0) {
            return e0.G;
        }
        if (length == 1) {
            return e0VarArr[0].E;
        }
        int length2 = 0;
        for (e0 e0Var : e0VarArr) {
            length2 += e0Var.E.length;
        }
        byte[] bArr = new byte[length2];
        int length3 = 0;
        for (e0 e0Var2 : e0VarArr) {
            byte[] bArr2 = e0Var2.E;
            System.arraycopy(bArr2, 0, bArr, length3, bArr2.length);
            length3 += bArr2.length;
        }
        return bArr;
    }

    @Override // defpackage.g0
    public final void h(p50 p50Var, boolean z) throws IOException {
        p50Var.J(36, z);
        p50Var.C(Uuid.SIZE_BITS);
        e0[] e0VarArr = this.I;
        if (e0VarArr == null) {
            int i = 0;
            while (true) {
                byte[] bArr = this.E;
                if (i >= bArr.length) {
                    break;
                }
                int iMin = Math.min(bArr.length - i, this.H);
                byte[] bArr2 = this.E;
                p50Var.J(4, true);
                p50Var.E(iMin);
                p50Var.D(bArr2, i, iMin);
                i += iMin;
            }
        } else {
            p50Var.L(e0VarArr);
        }
        p50Var.C(0);
        p50Var.C(0);
    }

    @Override // defpackage.g0
    public final boolean i() {
        return true;
    }

    @Override // defpackage.g0
    public final int j(boolean z) {
        int iJ = z ? 4 : 3;
        e0[] e0VarArr = this.I;
        if (e0VarArr != null) {
            for (e0 e0Var : e0VarArr) {
                iJ += e0Var.j(true);
            }
            return iJ;
        }
        byte[] bArr = this.E;
        int length = bArr.length;
        int i = this.H;
        int i2 = length / i;
        int iX = (p50.x(i, true) * i2) + iJ;
        int length2 = bArr.length - (i2 * i);
        return length2 > 0 ? p50.x(length2, true) + iX : iX;
    }
}
