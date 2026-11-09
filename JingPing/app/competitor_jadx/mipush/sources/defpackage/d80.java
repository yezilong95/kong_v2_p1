package defpackage;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.InputStream;
import java.util.Arrays;

/* JADX INFO: loaded from: classes.dex */
public final class d80 extends a80 {
    public final f80 F;
    public final y70 G;
    public final byte[] H;
    public final byte[] I;

    public d80(f80 f80Var, y70 y70Var, byte[] bArr, byte[] bArr2) {
        super(false);
        this.F = f80Var;
        this.G = y70Var;
        this.H = z91.l(bArr2);
        this.I = z91.l(bArr);
    }

    public static d80 a(Object obj) throws Throwable {
        if (obj instanceof d80) {
            return (d80) obj;
        }
        if (obj instanceof DataInputStream) {
            DataInputStream dataInputStream = (DataInputStream) obj;
            f80 f80Var = (f80) f80.e.get(Integer.valueOf(dataInputStream.readInt()));
            y70 y70Var = (y70) y70.g.get(Integer.valueOf(dataInputStream.readInt()));
            byte[] bArr = new byte[16];
            dataInputStream.readFully(bArr);
            byte[] bArr2 = new byte[f80Var.b];
            dataInputStream.readFully(bArr2);
            return new d80(f80Var, y70Var, bArr2, bArr);
        }
        if (!(obj instanceof byte[])) {
            if (obj instanceof InputStream) {
                return a(la0.Z((InputStream) obj));
            }
            throw new IllegalArgumentException("cannot parse " + obj);
        }
        DataInputStream dataInputStream2 = null;
        try {
            DataInputStream dataInputStream3 = new DataInputStream(new ByteArrayInputStream((byte[]) obj));
            try {
                d80 d80VarA = a(dataInputStream3);
                dataInputStream3.close();
                return d80VarA;
            } catch (Throwable th) {
                th = th;
                dataInputStream2 = dataInputStream3;
                if (dataInputStream2 != null) {
                    dataInputStream2.close();
                }
                throw th;
            }
        } catch (Throwable th2) {
            th = th2;
        }
    }

    public final byte[] b() {
        z8 z8Var = new z8(12);
        z8Var.D(this.F.a);
        z8Var.D(this.G.a);
        z8Var.u(this.H);
        z8Var.u(this.I);
        return ((ByteArrayOutputStream) z8Var.F).toByteArray();
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || d80.class != obj.getClass()) {
            return false;
        }
        d80 d80Var = (d80) obj;
        if (this.F.equals(d80Var.F) && this.G.equals(d80Var.G) && Arrays.equals(this.H, d80Var.H)) {
            return Arrays.equals(this.I, d80Var.I);
        }
        return false;
    }

    @Override // defpackage.tt
    public final byte[] getEncoded() {
        return b();
    }

    public final int hashCode() {
        return z91.E(this.I) + ((z91.E(this.H) + ((this.G.hashCode() + (this.F.hashCode() * 31)) * 31)) * 31);
    }
}
