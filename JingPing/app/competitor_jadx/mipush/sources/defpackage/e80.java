package defpackage;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.InputStream;
import java.util.Arrays;

/* JADX INFO: loaded from: classes.dex */
public final class e80 implements tt {
    public final int E;
    public final z70 F;
    public final f80 G;
    public final byte[][] H;

    public e80(int i, z70 z70Var, f80 f80Var, byte[][] bArr) {
        this.E = i;
        this.F = z70Var;
        this.G = f80Var;
        this.H = bArr;
    }

    public static e80 a(Object obj) throws Throwable {
        if (obj instanceof e80) {
            return (e80) obj;
        }
        if (obj instanceof DataInputStream) {
            DataInputStream dataInputStream = (DataInputStream) obj;
            int i = dataInputStream.readInt();
            z70 z70VarA = z70.a(obj);
            f80 f80Var = (f80) f80.e.get(Integer.valueOf(dataInputStream.readInt()));
            int i2 = f80Var.c;
            byte[][] bArr = new byte[i2][];
            for (int i3 = 0; i3 < i2; i3++) {
                byte[] bArr2 = new byte[f80Var.b];
                bArr[i3] = bArr2;
                dataInputStream.readFully(bArr2);
            }
            return new e80(i, z70VarA, f80Var, bArr);
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
                e80 e80VarA = a(dataInputStream3);
                dataInputStream3.close();
                return e80VarA;
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

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || e80.class != obj.getClass()) {
            return false;
        }
        e80 e80Var = (e80) obj;
        if (this.E != e80Var.E) {
            return false;
        }
        z70 z70Var = e80Var.F;
        z70 z70Var2 = this.F;
        if (z70Var2 == null ? z70Var != null : !z70Var2.equals(z70Var)) {
            return false;
        }
        f80 f80Var = e80Var.G;
        f80 f80Var2 = this.G;
        if (f80Var2 == null ? f80Var == null : f80Var2.equals(f80Var)) {
            return Arrays.deepEquals(this.H, e80Var.H);
        }
        return false;
    }

    @Override // defpackage.tt
    public final byte[] getEncoded() {
        z8 z8Var = new z8(12);
        z8Var.D(this.E);
        z8Var.u(this.F.getEncoded());
        z8Var.D(this.G.a);
        byte[][] bArr = this.H;
        try {
            int length = bArr.length;
            int i = 0;
            while (true) {
                ByteArrayOutputStream byteArrayOutputStream = (ByteArrayOutputStream) z8Var.F;
                if (i >= length) {
                    return byteArrayOutputStream.toByteArray();
                }
                byteArrayOutputStream.write(bArr[i]);
                i++;
            }
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage(), e);
        }
    }

    public final int hashCode() {
        int i = this.E * 31;
        z70 z70Var = this.F;
        int iHashCode = (i + (z70Var != null ? z70Var.hashCode() : 0)) * 31;
        f80 f80Var = this.G;
        return Arrays.deepHashCode(this.H) + ((iHashCode + (f80Var != null ? f80Var.hashCode() : 0)) * 31);
    }
}
