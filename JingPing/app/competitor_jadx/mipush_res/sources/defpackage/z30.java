package defpackage;

import java.io.EOFException;
import java.io.IOException;
import java.io.InputStream;

/* JADX INFO: loaded from: classes.dex */
public final class z30 extends c90 {
    public int G;
    public int H;
    public boolean I;
    public boolean J;

    public z30(InputStream inputStream, int i) throws IOException {
        super(inputStream, i);
        this.I = false;
        this.J = true;
        this.G = inputStream.read();
        int i2 = inputStream.read();
        this.H = i2;
        if (i2 < 0) {
            throw new EOFException();
        }
        l();
    }

    public final boolean l() {
        if (!this.I && this.J && this.G == 0 && this.H == 0) {
            this.I = true;
            j();
        }
        return this.I;
    }

    @Override // java.io.InputStream
    public final int read() throws IOException {
        if (l()) {
            return -1;
        }
        int i = this.E.read();
        if (i < 0) {
            throw new EOFException();
        }
        int i2 = this.G;
        this.G = this.H;
        this.H = i;
        return i2;
    }

    @Override // java.io.InputStream
    public final int read(byte[] bArr, int i, int i2) throws IOException {
        if (this.J || i2 < 3) {
            return super.read(bArr, i, i2);
        }
        if (this.I) {
            return -1;
        }
        InputStream inputStream = this.E;
        int i3 = inputStream.read(bArr, i + 2, i2 - 2);
        if (i3 < 0) {
            throw new EOFException();
        }
        bArr[i] = (byte) this.G;
        bArr[i + 1] = (byte) this.H;
        this.G = inputStream.read();
        int i4 = inputStream.read();
        this.H = i4;
        if (i4 >= 0) {
            return i3 + 2;
        }
        throw new EOFException();
    }
}
