package defpackage;

import java.io.IOException;
import java.io.InputStream;

/* JADX INFO: loaded from: classes.dex */
public final class ci extends InputStream {
    public final l0 E;
    public boolean F = true;
    public InputStream G;

    public ci(l0 l0Var) {
        this.E = l0Var;
    }

    public final f0 j() throws IOException {
        l0 l0Var = this.E;
        int i = ((c90) l0Var.c).read();
        t tVarG = i < 0 ? null : l0Var.g(i);
        if (tVarG == null) {
            return null;
        }
        if (tVarG instanceof f0) {
            return (f0) tVarG;
        }
        throw new IOException("unknown object encountered: " + tVarG.getClass());
    }

    @Override // java.io.InputStream
    public final int read() throws IOException {
        f0 f0VarJ;
        if (this.G == null) {
            if (!this.F || (f0VarJ = j()) == null) {
                return -1;
            }
            this.F = false;
            this.G = f0VarJ.a();
        }
        while (true) {
            int i = this.G.read();
            if (i >= 0) {
                return i;
            }
            f0 f0VarJ2 = j();
            if (f0VarJ2 == null) {
                this.G = null;
                return -1;
            }
            this.G = f0VarJ2.a();
        }
    }

    @Override // java.io.InputStream
    public final int read(byte[] bArr, int i, int i2) throws IOException {
        f0 f0VarJ;
        int i3 = 0;
        if (this.G == null) {
            if (!this.F || (f0VarJ = j()) == null) {
                return -1;
            }
            this.F = false;
            this.G = f0VarJ.a();
        }
        while (true) {
            int i4 = this.G.read(bArr, i + i3, i2 - i3);
            if (i4 >= 0) {
                i3 += i4;
                if (i3 == i2) {
                    return i3;
                }
            } else {
                f0 f0VarJ2 = j();
                if (f0VarJ2 == null) {
                    this.G = null;
                    if (i3 < 1) {
                        return -1;
                    }
                    return i3;
                }
                this.G = f0VarJ2.a();
            }
        }
    }
}
