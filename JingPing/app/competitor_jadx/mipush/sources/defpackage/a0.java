package defpackage;

import java.io.ByteArrayOutputStream;

/* JADX INFO: loaded from: classes.dex */
public abstract class a0 implements t, tt {
    @Override // defpackage.t
    public abstract g0 b();

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof t) {
            return b().k(((t) obj).b());
        }
        return false;
    }

    public final byte[] f() {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        g0 g0VarB = b();
        g0VarB.getClass();
        new uk(2, byteArrayOutputStream).K(g0VarB);
        return byteArrayOutputStream.toByteArray();
    }

    @Override // defpackage.tt
    public final byte[] getEncoded() {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        g0 g0VarB = b();
        g0VarB.getClass();
        new p50(2, byteArrayOutputStream).K(g0VarB);
        return byteArrayOutputStream.toByteArray();
    }

    public int hashCode() {
        return b().hashCode();
    }
}
