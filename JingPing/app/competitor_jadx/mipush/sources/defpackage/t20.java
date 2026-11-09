package defpackage;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.InputStream;

/* JADX INFO: loaded from: classes.dex */
public final class t20 extends a80 {
    public final int F;
    public final d80 G;

    public t20(int i, d80 d80Var) {
        super(false);
        if (d80Var == null) {
            throw new NullPointerException("lmsPublicKey");
        }
        this.F = i;
        this.G = d80Var;
    }

    public static t20 a(Object obj) {
        if (obj instanceof t20) {
            return (t20) obj;
        }
        if (obj instanceof DataInputStream) {
            return new t20(((DataInputStream) obj).readInt(), d80.a(obj));
        }
        if (!(obj instanceof byte[])) {
            if (obj instanceof InputStream) {
                return a(la0.Z((InputStream) obj));
            }
            throw new IllegalArgumentException("cannot parse " + obj);
        }
        DataInputStream dataInputStream = null;
        try {
            DataInputStream dataInputStream2 = new DataInputStream(new ByteArrayInputStream((byte[]) obj));
            try {
                t20 t20VarA = a(dataInputStream2);
                dataInputStream2.close();
                return t20VarA;
            } catch (Throwable th) {
                th = th;
                dataInputStream = dataInputStream2;
                if (dataInputStream != null) {
                    dataInputStream.close();
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
        if (obj == null || t20.class != obj.getClass()) {
            return false;
        }
        t20 t20Var = (t20) obj;
        if (this.F != t20Var.F) {
            return false;
        }
        return this.G.equals(t20Var.G);
    }

    @Override // defpackage.tt
    public final byte[] getEncoded() {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        int i = this.F;
        byteArrayOutputStream.write((byte) (i >>> 24));
        byteArrayOutputStream.write((byte) (i >>> 16));
        byteArrayOutputStream.write((byte) (i >>> 8));
        byteArrayOutputStream.write((byte) i);
        try {
            byteArrayOutputStream.write(this.G.b());
            return byteArrayOutputStream.toByteArray();
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage(), e);
        }
    }

    public final int hashCode() {
        return this.G.hashCode() + (this.F * 31);
    }
}
