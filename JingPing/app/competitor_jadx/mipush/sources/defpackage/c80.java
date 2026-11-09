package defpackage;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.WeakHashMap;
import kotlin.uuid.Uuid;

/* JADX INFO: loaded from: classes.dex */
public final class c80 extends a80 {
    public static final b80 P;
    public static final b80[] Q;
    public final byte[] F;
    public final f80 G;
    public final y70 H;
    public final int I;
    public final byte[] J;
    public final WeakHashMap K;
    public final int L;
    public final po M;
    public int N;
    public d80 O;

    static {
        b80 b80Var = new b80(1);
        P = b80Var;
        b80[] b80VarArr = new b80[129];
        Q = b80VarArr;
        b80VarArr[1] = b80Var;
        int i = 2;
        while (true) {
            b80[] b80VarArr2 = Q;
            if (i >= b80VarArr2.length) {
                return;
            }
            b80VarArr2[i] = new b80(i);
            i++;
        }
    }

    public c80(c80 c80Var, int i, int i2) {
        super(true);
        f80 f80Var = c80Var.G;
        this.G = f80Var;
        this.H = c80Var.H;
        this.N = i;
        this.F = c80Var.F;
        this.I = i2;
        this.J = c80Var.J;
        this.L = 1 << f80Var.c;
        this.K = c80Var.K;
        this.M = l21.v(f80Var.d, f80Var.b);
        this.O = c80Var.O;
    }

    public static c80 e(Object obj) {
        Throwable th;
        DataInputStream dataInputStream;
        if (obj instanceof c80) {
            return (c80) obj;
        }
        if (!(obj instanceof DataInputStream)) {
            if (!(obj instanceof byte[])) {
                if (obj instanceof InputStream) {
                    return e(la0.Z((InputStream) obj));
                }
                throw new IllegalArgumentException("cannot parse " + obj);
            }
            DataInputStream dataInputStream2 = null;
            try {
                dataInputStream = new DataInputStream(new ByteArrayInputStream((byte[]) obj));
            } catch (Throwable th2) {
                th = th2;
            }
            try {
                c80 c80VarE = e(dataInputStream);
                dataInputStream.close();
                return c80VarE;
            } catch (Throwable th3) {
                th = th3;
                dataInputStream2 = dataInputStream;
                if (dataInputStream2 == null) {
                    throw th;
                }
                dataInputStream2.close();
                throw th;
            }
        }
        DataInputStream dataInputStream3 = (DataInputStream) obj;
        if (dataInputStream3.readInt() != 0) {
            throw new IllegalStateException("expected version 0 lms private key");
        }
        f80 f80Var = (f80) f80.e.get(Integer.valueOf(dataInputStream3.readInt()));
        y70 y70Var = (y70) y70.g.get(Integer.valueOf(dataInputStream3.readInt()));
        byte[] bArr = new byte[16];
        dataInputStream3.readFully(bArr);
        int i = dataInputStream3.readInt();
        int i2 = dataInputStream3.readInt();
        int i3 = dataInputStream3.readInt();
        if (i3 < 0) {
            throw new IllegalStateException("secret length less than zero");
        }
        if (i3 <= dataInputStream3.available()) {
            byte[] bArr2 = new byte[i3];
            dataInputStream3.readFully(bArr2);
            return new c80(f80Var, y70Var, i, bArr, i2, bArr2);
        }
        throw new IOException("secret length exceeded " + dataInputStream3.available());
    }

    public final byte[] a(int i) {
        int i2 = 1 << this.G.c;
        byte[] bArr = this.F;
        po poVar = this.M;
        if (i < i2) {
            int i3 = i * 2;
            byte[] bArrB = b(i3);
            byte[] bArrB2 = b(i3 + 1);
            byte[] bArrL = z91.l(bArr);
            poVar.e(bArrL, 0, bArrL.length);
            fa0.N0(i, poVar);
            poVar.f((byte) 16777091);
            poVar.f((byte) (-31869));
            poVar.e(bArrB, 0, bArrB.length);
            poVar.e(bArrB2, 0, bArrB2.length);
            byte[] bArr2 = new byte[poVar.c()];
            poVar.d(0, bArr2);
            return bArr2;
        }
        byte[] bArrL2 = z91.l(bArr);
        poVar.e(bArrL2, 0, bArrL2.length);
        fa0.N0(i, poVar);
        poVar.f((byte) 16777090);
        poVar.f((byte) (-32126));
        byte[] bArrL3 = z91.l(bArr);
        int i4 = i - i2;
        byte[] bArrL4 = z91.l(this.J);
        y70 y70Var = this.H;
        po poVarH = l21.H(y70Var);
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        try {
            byteArrayOutputStream.write(bArrL3);
            byte b = (byte) (i4 >>> 24);
            byteArrayOutputStream.write(b);
            byte b2 = (byte) (i4 >>> 16);
            byteArrayOutputStream.write(b2);
            byte b3 = (byte) (i4 >>> 8);
            byteArrayOutputStream.write(b3);
            byte b4 = (byte) i4;
            byteArrayOutputStream.write(b4);
            byteArrayOutputStream.write((byte) Uuid.SIZE_BITS);
            byteArrayOutputStream.write((byte) 32896);
            while (byteArrayOutputStream.size() < 22) {
                byteArrayOutputStream.write(0);
            }
            byte[] byteArray = byteArrayOutputStream.toByteArray();
            poVarH.e(byteArray, 0, byteArray.length);
            d0 d0Var = y70Var.f;
            int i5 = y70Var.b;
            po poVarV = l21.v(d0Var, i5);
            ByteArrayOutputStream byteArrayOutputStream2 = new ByteArrayOutputStream();
            try {
                byteArrayOutputStream2.write(bArrL3);
                byteArrayOutputStream2.write(b);
                byteArrayOutputStream2.write(b2);
                byteArrayOutputStream2.write(b3);
                byteArrayOutputStream2.write(b4);
                int iC = poVarV.c() + 23;
                while (byteArrayOutputStream2.size() < iC) {
                    byteArrayOutputStream2.write(0);
                }
                byte[] byteArray2 = byteArrayOutputStream2.toByteArray();
                sh shVar = new sh(bArrL3, bArrL4, l21.v(d0Var, i5));
                shVar.a = i4;
                shVar.b = 0;
                int i6 = (1 << y70Var.c) - 1;
                int i7 = 0;
                while (true) {
                    int i8 = y70Var.d;
                    if (i7 >= i8) {
                        int iC2 = poVarH.c();
                        byte[] bArr3 = new byte[iC2];
                        poVarH.d(0, bArr3);
                        poVar.e(bArr3, 0, iC2);
                        byte[] bArr4 = new byte[poVar.c()];
                        poVar.d(0, bArr4);
                        return bArr4;
                    }
                    shVar.a(23, i7 < i8 + (-1), byteArray2);
                    short s = (short) i7;
                    byteArray2[20] = (byte) (s >>> 8);
                    byteArray2[21] = (byte) s;
                    for (int i9 = 0; i9 < i6; i9++) {
                        byteArray2[22] = (byte) i9;
                        poVarV.e(byteArray2, 0, byteArray2.length);
                        poVarV.d(23, byteArray2);
                    }
                    poVarH.e(byteArray2, 23, i5);
                    i7++;
                }
            } catch (Exception e) {
                throw new RuntimeException(e.getMessage(), e);
            }
        } catch (Exception e2) {
            throw new RuntimeException(e2.getMessage(), e2);
        }
    }

    public final byte[] b(int i) {
        if (i < this.L) {
            return c(i < 129 ? Q[i] : new b80(i));
        }
        return a(i);
    }

    public final byte[] c(b80 b80Var) {
        synchronized (this.K) {
            try {
                byte[] bArr = (byte[]) this.K.get(b80Var);
                if (bArr != null) {
                    return bArr;
                }
                byte[] bArrA = a(b80Var.a);
                this.K.put(b80Var, bArrA);
                return bArrA;
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    public final synchronized int d() {
        return this.N;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || c80.class != obj.getClass()) {
            return false;
        }
        c80 c80Var = (c80) obj;
        if (this.N != c80Var.N || this.I != c80Var.I || !Arrays.equals(this.F, c80Var.F)) {
            return false;
        }
        f80 f80Var = c80Var.G;
        f80 f80Var2 = this.G;
        if (f80Var2 == null ? f80Var != null : !f80Var2.equals(f80Var)) {
            return false;
        }
        y70 y70Var = c80Var.H;
        y70 y70Var2 = this.H;
        if (y70Var2 == null ? y70Var == null : y70Var2.equals(y70Var)) {
            return Arrays.equals(this.J, c80Var.J);
        }
        return false;
    }

    public final d80 f() {
        d80 d80Var;
        synchronized (this) {
            try {
                if (this.O == null) {
                    this.O = new d80(this.G, this.H, c(P), this.F);
                }
                d80Var = this.O;
            } catch (Throwable th) {
                throw th;
            }
        }
        return d80Var;
    }

    @Override // defpackage.tt
    public final byte[] getEncoded() {
        z8 z8Var = new z8(12);
        z8Var.D(0);
        z8Var.D(this.G.a);
        z8Var.D(this.H.a);
        z8Var.u(this.F);
        z8Var.D(this.N);
        z8Var.D(this.I);
        byte[] bArr = this.J;
        z8Var.D(bArr.length);
        z8Var.u(bArr);
        return ((ByteArrayOutputStream) z8Var.F).toByteArray();
    }

    public final int hashCode() {
        int iE = (z91.E(this.F) + (this.N * 31)) * 31;
        f80 f80Var = this.G;
        int iHashCode = (iE + (f80Var != null ? f80Var.hashCode() : 0)) * 31;
        y70 y70Var = this.H;
        return z91.E(this.J) + ((((iHashCode + (y70Var != null ? y70Var.hashCode() : 0)) * 31) + this.I) * 31);
    }

    public c80(f80 f80Var, y70 y70Var, int i, byte[] bArr, int i2, byte[] bArr2) {
        super(true);
        this.G = f80Var;
        this.H = y70Var;
        this.N = i;
        this.F = z91.l(bArr);
        this.I = i2;
        this.J = z91.l(bArr2);
        this.L = 1 << (f80Var.c + 1);
        this.K = new WeakHashMap();
        this.M = l21.v(f80Var.d, f80Var.b);
    }
}
