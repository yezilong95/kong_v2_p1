package defpackage;

import java.io.IOException;
import java.io.InputStream;

/* JADX INFO: loaded from: classes.dex */
public final class bi extends InputStream {
    public final l0 E;
    public boolean F = true;
    public int G = 0;
    public q H;
    public InputStream I;

    public bi(l0 l0Var) {
        this.E = l0Var;
    }

    public final q j() throws IOException {
        l0 l0Var = this.E;
        int i = ((c90) l0Var.c).read();
        t tVarG = i < 0 ? null : l0Var.g(i);
        if (tVarG == null) {
            return null;
        }
        if (tVarG instanceof q) {
            if (this.G == 0) {
                return (q) tVarG;
            }
            throw new IOException("only the last nested bitstring can have padding");
        }
        throw new IOException("unknown object encountered: " + tVarG.getClass());
    }

    @Override // java.io.InputStream
    public final int read() throws IOException {
        if (this.I == null) {
            if (this.F) {
                q qVarJ = j();
                this.H = qVarJ;
                if (qVarJ != null) {
                    this.F = false;
                    this.I = qVarJ.e();
                }
            }
            return -1;
        }
        while (true) {
            int i = this.I.read();
            if (i >= 0) {
                return i;
            }
            this.G = this.H.c();
            q qVarJ2 = j();
            this.H = qVarJ2;
            if (qVarJ2 == null) {
                this.I = null;
                return -1;
            }
            this.I = qVarJ2.e();
        }
    }

    @Override // java.io.InputStream
    public final int read(byte[] bArr, int i, int i2) throws IOException {
        int i3 = 0;
        if (this.I == null) {
            if (!this.F) {
                return -1;
            }
            q qVarJ = j();
            this.H = qVarJ;
            if (qVarJ == null) {
                return -1;
            }
            this.F = false;
            this.I = qVarJ.e();
        }
        while (true) {
            int i4 = this.I.read(bArr, i + i3, i2 - i3);
            if (i4 >= 0) {
                i3 += i4;
                if (i3 == i2) {
                    return i3;
                }
            } else {
                this.G = this.H.c();
                q qVarJ2 = j();
                this.H = qVarJ2;
                if (qVarJ2 == null) {
                    this.I = null;
                    if (i3 < 1) {
                        return -1;
                    }
                    return i3;
                }
                this.I = qVarJ2.e();
            }
        }
    }
}
