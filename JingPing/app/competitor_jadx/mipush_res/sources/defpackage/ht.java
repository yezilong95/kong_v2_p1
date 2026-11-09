package defpackage;

import io.nats.client.support.NatsConstants;
import java.nio.ByteBuffer;

/* JADX INFO: loaded from: classes.dex */
public final class ht {
    public static final ThreadLocal d = new ThreadLocal();
    public final int a;
    public final tb b;
    public volatile int c = 0;

    public ht(tb tbVar, int i) {
        this.b = tbVar;
        this.a = i;
    }

    public final int a(int i) {
        ve0 ve0VarB = b();
        int iA = ve0VarB.a(16);
        if (iA == 0) {
            return 0;
        }
        ByteBuffer byteBuffer = (ByteBuffer) ve0VarB.H;
        int i2 = iA + ve0VarB.E;
        return byteBuffer.getInt((i * 4) + byteBuffer.getInt(i2) + i2 + 4);
    }

    public final ve0 b() {
        ThreadLocal threadLocal = d;
        ve0 ve0Var = (ve0) threadLocal.get();
        if (ve0Var == null) {
            ve0Var = new ve0();
            threadLocal.set(ve0Var);
        }
        we0 we0Var = (we0) this.b.a;
        int iA = we0Var.a(6);
        if (iA != 0) {
            int i = iA + we0Var.E;
            int i2 = (this.a * 4) + ((ByteBuffer) we0Var.H).getInt(i) + i + 4;
            int i3 = ((ByteBuffer) we0Var.H).getInt(i2) + i2;
            ByteBuffer byteBuffer = (ByteBuffer) we0Var.H;
            ve0Var.H = byteBuffer;
            if (byteBuffer != null) {
                ve0Var.E = i3;
                int i4 = i3 - byteBuffer.getInt(i3);
                ve0Var.F = i4;
                ve0Var.G = ((ByteBuffer) ve0Var.H).getShort(i4);
                return ve0Var;
            }
            ve0Var.E = 0;
            ve0Var.F = 0;
            ve0Var.G = 0;
        }
        return ve0Var;
    }

    public final String toString() {
        int i;
        StringBuilder sb = new StringBuilder();
        sb.append(super.toString());
        sb.append(", id:");
        ve0 ve0VarB = b();
        int iA = ve0VarB.a(4);
        sb.append(Integer.toHexString(iA != 0 ? ((ByteBuffer) ve0VarB.H).getInt(iA + ve0VarB.E) : 0));
        sb.append(", codepoints:");
        ve0 ve0VarB2 = b();
        int iA2 = ve0VarB2.a(16);
        if (iA2 != 0) {
            int i2 = iA2 + ve0VarB2.E;
            i = ((ByteBuffer) ve0VarB2.H).getInt(((ByteBuffer) ve0VarB2.H).getInt(i2) + i2);
        } else {
            i = 0;
        }
        for (int i3 = 0; i3 < i; i3++) {
            sb.append(Integer.toHexString(a(i3)));
            sb.append(NatsConstants.SPACE);
        }
        return sb.toString();
    }
}
