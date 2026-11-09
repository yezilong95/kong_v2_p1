package defpackage;

import java.io.IOException;

/* JADX INFO: loaded from: classes.dex */
public final class ok extends w {
    @Override // defpackage.w, defpackage.g0
    public final void h(p50 p50Var, boolean z) throws IOException {
        p50Var.G(24, z, p());
    }

    @Override // defpackage.w, defpackage.g0
    public final int j(boolean z) {
        return p50.x(p().length, z);
    }

    public final byte[] p() {
        byte[] bArr = this.E;
        if (bArr[bArr.length - 1] == 90) {
            if (!(o(10) && o(11))) {
                byte[] bArr2 = new byte[bArr.length + 4];
                System.arraycopy(bArr, 0, bArr2, 0, bArr.length - 1);
                System.arraycopy(jx0.b("0000Z"), 0, bArr2, bArr.length - 1, 5);
                return bArr2;
            }
            if (!(o(12) && o(13))) {
                byte[] bArr3 = new byte[bArr.length + 2];
                System.arraycopy(bArr, 0, bArr3, 0, bArr.length - 1);
                System.arraycopy(jx0.b("00Z"), 0, bArr3, bArr.length - 1, 3);
                return bArr3;
            }
            boolean z = false;
            int i = 0;
            while (true) {
                byte[] bArr4 = this.E;
                if (i != bArr4.length) {
                    if (bArr4[i] == 46 && i == 14) {
                        z = true;
                        break;
                    }
                    i++;
                } else {
                    break;
                }
            }
            if (z) {
                int length = bArr.length - 2;
                while (length > 0 && bArr[length] == 48) {
                    length--;
                }
                if (bArr[length] == 46) {
                    byte[] bArr5 = new byte[length + 1];
                    System.arraycopy(bArr, 0, bArr5, 0, length);
                    bArr5[length] = 90;
                    return bArr5;
                }
                byte[] bArr6 = new byte[length + 2];
                int i2 = length + 1;
                System.arraycopy(bArr, 0, bArr6, 0, i2);
                bArr6[i2] = 90;
                return bArr6;
            }
        }
        return bArr;
    }

    @Override // defpackage.w, defpackage.g0
    public final g0 m() {
        return this;
    }

    @Override // defpackage.g0
    public final g0 n() {
        return this;
    }
}
