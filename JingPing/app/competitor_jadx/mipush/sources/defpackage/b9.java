package defpackage;

import java.io.IOException;
import kotlin.uuid.Uuid;

/* JADX INFO: loaded from: classes.dex */
public final class b9 extends p {
    public final int H;
    public final p[] I;

    public b9(byte[] bArr, int i) {
        super(bArr, i);
        this.I = null;
        this.H = 1000;
    }

    public static byte[] s(p[] pVarArr) {
        int length = pVarArr.length;
        if (length == 0) {
            return new byte[]{0};
        }
        if (length == 1) {
            return pVarArr[0].E;
        }
        int i = length - 1;
        int length2 = 0;
        for (int i2 = 0; i2 < i; i2++) {
            byte[] bArr = pVarArr[i2].E;
            if (bArr[0] != 0) {
                throw new IllegalArgumentException("only the last nested bitstring can have padding");
            }
            length2 += bArr.length - 1;
        }
        byte[] bArr2 = pVarArr[i].E;
        byte b = bArr2[0];
        byte[] bArr3 = new byte[length2 + bArr2.length];
        bArr3[0] = b;
        int i3 = 1;
        for (p pVar : pVarArr) {
            byte[] bArr4 = pVar.E;
            int length3 = bArr4.length - 1;
            System.arraycopy(bArr4, 1, bArr3, i3, length3);
            i3 += length3;
        }
        return bArr3;
    }

    @Override // defpackage.g0
    public final void h(p50 p50Var, boolean z) throws IOException {
        boolean zI = i();
        byte[] bArr = this.E;
        if (!zI) {
            int length = bArr.length;
            p50Var.J(3, z);
            p50Var.E(length);
            p50Var.D(bArr, 0, length);
            return;
        }
        p50Var.J(35, z);
        p50Var.C(Uuid.SIZE_BITS);
        p[] pVarArr = this.I;
        if (pVarArr != null) {
            p50Var.L(pVarArr);
        } else if (bArr.length >= 2) {
            byte b = bArr[0];
            int length2 = bArr.length;
            int i = length2 - 1;
            int i2 = this.H;
            int i3 = i2 - 1;
            while (i > i3) {
                p50Var.C(3);
                p50Var.E(i2);
                p50Var.C(0);
                p50Var.D(bArr, length2 - i, i3);
                i -= i3;
            }
            p50Var.C(3);
            p50Var.E(i + 1);
            p50Var.C(b);
            p50Var.D(bArr, length2 - i, i);
        }
        p50Var.C(0);
        p50Var.C(0);
    }

    @Override // defpackage.g0
    public final boolean i() {
        return this.I != null || this.E.length > this.H;
    }

    @Override // defpackage.g0
    public final int j(boolean z) {
        boolean zI = i();
        byte[] bArr = this.E;
        if (!zI) {
            return p50.x(bArr.length, z);
        }
        int iJ = z ? 4 : 3;
        p[] pVarArr = this.I;
        if (pVarArr != null) {
            for (p pVar : pVarArr) {
                iJ += pVar.j(true);
            }
            return iJ;
        }
        if (bArr.length < 2) {
            return iJ;
        }
        int length = bArr.length - 2;
        int i = this.H;
        int i2 = i - 1;
        int i3 = length / i2;
        return p50.x(bArr.length - (i2 * i3), true) + (p50.x(i, true) * i3) + iJ;
    }

    public b9(p[] pVarArr) {
        super(s(pVarArr));
        this.I = pVarArr;
        this.H = 1000;
    }
}
