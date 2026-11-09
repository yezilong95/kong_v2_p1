package defpackage;

import java.io.IOException;
import java.net.Socket;

/* JADX INFO: loaded from: classes.dex */
public final class rw extends vm0 {
    public final /* synthetic */ int e;
    public final /* synthetic */ Object f;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public /* synthetic */ rw(String str, Object obj, int i) {
        super(str, true);
        this.e = i;
        this.f = obj;
    }

    @Override // defpackage.vm0
    public final long a() {
        switch (this.e) {
            case 0:
                uw uwVar = (uw) this.f;
                uwVar.getClass();
                try {
                    uwVar.x.F(2, 0, false);
                    return -1L;
                } catch (IOException e) {
                    uwVar.t(e);
                    return -1L;
                }
            case 1:
                zc0 zc0Var = (zc0) this.f;
                long jNanoTime = System.nanoTime();
                int i = 0;
                long j = Long.MIN_VALUE;
                yc0 yc0Var = null;
                int i2 = 0;
                for (yc0 yc0Var2 : zc0Var.d) {
                    oy.i(yc0Var2, "connection");
                    synchronized (yc0Var2) {
                        if (zc0Var.b(yc0Var2, jNanoTime) > 0) {
                            i2++;
                        } else {
                            i++;
                            long j2 = jNanoTime - yc0Var2.q;
                            if (j2 > j) {
                                yc0Var = yc0Var2;
                                j = j2;
                            }
                        }
                    }
                }
                long j3 = zc0Var.a;
                if (j < j3 && i <= 5) {
                    if (i > 0) {
                        return j3 - j;
                    }
                    if (i2 > 0) {
                        return j3;
                    }
                    return -1L;
                }
                oy.g(yc0Var);
                synchronized (yc0Var) {
                    if (!yc0Var.p.isEmpty()) {
                        return 0L;
                    }
                    if (yc0Var.q + j != jNanoTime) {
                        return 0L;
                    }
                    yc0Var.j = true;
                    zc0Var.d.remove(yc0Var);
                    Socket socket = yc0Var.d;
                    oy.g(socket);
                    kq0.e(socket);
                    if (!zc0Var.d.isEmpty()) {
                        return 0L;
                    }
                    zc0Var.b.a();
                    return 0L;
                }
            default:
                ((ow) this.f).invoke();
                return -1L;
        }
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public rw(zc0 zc0Var, String str) {
        super(str, true);
        this.e = 1;
        this.f = zc0Var;
    }
}
