package defpackage;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.InputStream;
import java.util.Arrays;

/* JADX INFO: loaded from: classes.dex */
public final class z70 implements tt {
    public final y70 E;
    public final byte[] F;
    public final byte[] G;

    public z70(y70 y70Var, byte[] bArr, byte[] bArr2) {
        this.E = y70Var;
        this.F = bArr;
        this.G = bArr2;
    }

    public static z70 a(Object obj) throws Throwable {
        DataInputStream dataInputStream;
        if (obj instanceof z70) {
            return (z70) obj;
        }
        if (obj instanceof DataInputStream) {
            DataInputStream dataInputStream2 = (DataInputStream) obj;
            y70 y70Var = (y70) y70.g.get(Integer.valueOf(dataInputStream2.readInt()));
            byte[] bArr = new byte[y70Var.b];
            dataInputStream2.readFully(bArr);
            byte[] bArr2 = new byte[y70Var.d * y70Var.b];
            dataInputStream2.readFully(bArr2);
            return new z70(y70Var, bArr, bArr2);
        }
        if (!(obj instanceof byte[])) {
            if (obj instanceof InputStream) {
                return a(la0.Z((InputStream) obj));
            }
            throw new IllegalArgumentException("cannot parse " + obj);
        }
        DataInputStream dataInputStream3 = null;
        try {
            dataInputStream = new DataInputStream(new ByteArrayInputStream((byte[]) obj));
        } catch (Throwable th) {
            th = th;
        }
        try {
            z70 z70VarA = a(dataInputStream);
            dataInputStream.close();
            return z70VarA;
        } catch (Throwable th2) {
            th = th2;
            dataInputStream3 = dataInputStream;
            if (dataInputStream3 != null) {
                dataInputStream3.close();
            }
            throw th;
        }
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || z70.class != obj.getClass()) {
            return false;
        }
        z70 z70Var = (z70) obj;
        y70 y70Var = z70Var.E;
        y70 y70Var2 = this.E;
        if (y70Var2 == null ? y70Var != null : !y70Var2.equals(y70Var)) {
            return false;
        }
        if (Arrays.equals(this.F, z70Var.F)) {
            return Arrays.equals(this.G, z70Var.G);
        }
        return false;
    }

    @Override // defpackage.tt
    public final byte[] getEncoded() {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        int i = this.E.a;
        byteArrayOutputStream.write((byte) 0);
        byteArrayOutputStream.write((byte) 0);
        byteArrayOutputStream.write((byte) 0);
        byteArrayOutputStream.write((byte) i);
        try {
            byteArrayOutputStream.write(this.F);
            try {
                byteArrayOutputStream.write(this.G);
                return byteArrayOutputStream.toByteArray();
            } catch (Exception e) {
                throw new RuntimeException(e.getMessage(), e);
            }
        } catch (Exception e2) {
            throw new RuntimeException(e2.getMessage(), e2);
        }
    }

    public final int hashCode() {
        y70 y70Var = this.E;
        return Arrays.hashCode(this.G) + ((Arrays.hashCode(this.F) + ((y70Var != null ? y70Var.hashCode() : 0) * 31)) * 31);
    }
}
