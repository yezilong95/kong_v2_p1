package defpackage;

import java.lang.ref.Reference;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.TimeUnit;

/* JADX INFO: loaded from: classes.dex */
public final class zc0 {
    public final long a;
    public final zm0 b;
    public final rw c;
    public final ConcurrentLinkedQueue d;

    public zc0(an0 an0Var) {
        TimeUnit timeUnit = TimeUnit.MINUTES;
        oy.j(an0Var, "taskRunner");
        oy.j(timeUnit, "timeUnit");
        this.a = timeUnit.toNanos(5L);
        this.b = an0Var.e();
        this.c = new rw(this, hk.k(new StringBuilder(), kq0.g, " ConnectionPool"));
        this.d = new ConcurrentLinkedQueue();
    }

    public final boolean a(p2 p2Var, wc0 wc0Var, ArrayList arrayList, boolean z) {
        oy.j(wc0Var, "call");
        Iterator it = this.d.iterator();
        while (true) {
            if (!it.hasNext()) {
                return false;
            }
            yc0 yc0Var = (yc0) it.next();
            oy.i(yc0Var, "connection");
            synchronized (yc0Var) {
                if (z) {
                    if (!(yc0Var.g != null)) {
                        continue;
                    }
                }
                if (yc0Var.h(p2Var, arrayList)) {
                    wc0Var.b(yc0Var);
                    return true;
                }
            }
        }
    }

    public final int b(yc0 yc0Var, long j) {
        byte[] bArr = kq0.a;
        ArrayList arrayList = yc0Var.p;
        int i = 0;
        while (i < arrayList.size()) {
            Reference reference = (Reference) arrayList.get(i);
            if (reference.get() != null) {
                i++;
            } else {
                String str = "A connection to " + yc0Var.b.a.h + " was leaked. Did you forget to close a response body?";
                rb0 rb0Var = rb0.a;
                rb0.a.j(((uc0) reference).a, str);
                arrayList.remove(i);
                yc0Var.j = true;
                if (arrayList.isEmpty()) {
                    yc0Var.q = j - this.a;
                    return 0;
                }
            }
        }
        return arrayList.size();
    }
}
