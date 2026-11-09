package defpackage;

import java.nio.ByteBuffer;

/* JADX INFO: loaded from: classes.dex */
public final class qn {
    public static final ThreadLocal d = new ThreadLocal();
    public final int a;
    public final ba b;
    public volatile int c = 0;

    public qn(ba baVar, int i) {
        this.b = baVar;
        this.a = i;
    }

    public final int a(int i) {
        d70 d70VarB = b();
        int iA = d70VarB.a(16);
        if (iA == 0) {
            return 0;
        }
        ByteBuffer byteBuffer = (ByteBuffer) d70VarB.e;
        int i2 = iA + d70VarB.b;
        return byteBuffer.getInt((i * 4) + byteBuffer.getInt(i2) + i2 + 4);
    }

    public final d70 b() {
        ThreadLocal threadLocal = d;
        d70 d70Var = (d70) threadLocal.get();
        if (d70Var == null) {
            d70Var = new d70();
            threadLocal.set(d70Var);
        }
        e70 e70Var = (e70) this.b.a;
        int iA = e70Var.a(6);
        if (iA != 0) {
            int i = iA + e70Var.b;
            int i2 = (this.a * 4) + ((ByteBuffer) e70Var.e).getInt(i) + i + 4;
            int i3 = ((ByteBuffer) e70Var.e).getInt(i2) + i2;
            ByteBuffer byteBuffer = (ByteBuffer) e70Var.e;
            d70Var.e = byteBuffer;
            if (byteBuffer != null) {
                d70Var.b = i3;
                int i4 = i3 - byteBuffer.getInt(i3);
                d70Var.c = i4;
                d70Var.d = ((ByteBuffer) d70Var.e).getShort(i4);
                return d70Var;
            }
            d70Var.b = 0;
            d70Var.c = 0;
            d70Var.d = 0;
        }
        return d70Var;
    }

    public final String toString() {
        int i;
        StringBuilder sb = new StringBuilder();
        sb.append(super.toString());
        sb.append(", id:");
        d70 d70VarB = b();
        int iA = d70VarB.a(4);
        sb.append(Integer.toHexString(iA != 0 ? ((ByteBuffer) d70VarB.e).getInt(iA + d70VarB.b) : 0));
        sb.append(", codepoints:");
        d70 d70VarB2 = b();
        int iA2 = d70VarB2.a(16);
        if (iA2 != 0) {
            int i2 = iA2 + d70VarB2.b;
            i = ((ByteBuffer) d70VarB2.e).getInt(((ByteBuffer) d70VarB2.e).getInt(i2) + i2);
        } else {
            i = 0;
        }
        for (int i3 = 0; i3 < i; i3++) {
            sb.append(Integer.toHexString(a(i3)));
            sb.append(" ");
        }
        return sb.toString();
    }
}
