package defpackage;

import java.io.EOFException;
import java.io.IOException;
import java.io.InputStream;

/* JADX INFO: loaded from: classes.dex */
public final class sn extends c90 {
    public static final byte[] I = new byte[0];
    public final int G;
    public int H;

    public sn(InputStream inputStream, int i, int i2) {
        super(inputStream, i2);
        if (i <= 0) {
            if (i < 0) {
                throw new IllegalArgumentException("negative lengths not allowed");
            }
            j();
        }
        this.G = i;
        this.H = i;
    }

    public final byte[] l() {
        int i = this.H;
        if (i == 0) {
            return I;
        }
        int i2 = this.F;
        if (i >= i2) {
            throw new IOException("corrupted stream - out of bounds length found: " + this.H + " >= " + i2);
        }
        byte[] bArr = new byte[i];
        int iA0 = i - la0.a0(this.E, bArr, i);
        this.H = iA0;
        if (iA0 == 0) {
            j();
            return bArr;
        }
        throw new EOFException("DEF length " + this.G + " object truncated by " + this.H);
    }

    @Override // java.io.InputStream
    public final int read() throws IOException {
        if (this.H == 0) {
            return -1;
        }
        int i = this.E.read();
        if (i >= 0) {
            int i2 = this.H - 1;
            this.H = i2;
            if (i2 == 0) {
                j();
            }
            return i;
        }
        throw new EOFException("DEF length " + this.G + " object truncated by " + this.H);
    }

    @Override // java.io.InputStream
    public final int read(byte[] bArr, int i, int i2) throws IOException {
        int i3 = this.H;
        if (i3 == 0) {
            return -1;
        }
        int i4 = this.E.read(bArr, i, Math.min(i2, i3));
        if (i4 >= 0) {
            int i5 = this.H - i4;
            this.H = i5;
            if (i5 == 0) {
                j();
            }
            return i4;
        }
        throw new EOFException("DEF length " + this.G + " object truncated by " + this.H);
    }
}
