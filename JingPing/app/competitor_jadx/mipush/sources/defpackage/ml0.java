package defpackage;

import io.nats.client.support.NatsConstants;

/* JADX INFO: loaded from: classes.dex */
public final class ml0 implements Comparable {
    public hw0 E;
    public final /* synthetic */ nl0 F;

    public ml0(nl0 nl0Var) {
        this.F = nl0Var;
    }

    @Override // java.lang.Comparable
    public final int compareTo(Object obj) {
        return this.E.b - ((hw0) obj).b;
    }

    public final String toString() {
        String string = "[ ";
        if (this.E != null) {
            for (int i = 0; i < 9; i++) {
                StringBuilder sbP = zn.p(string);
                sbP.append(this.E.h[i]);
                sbP.append(NatsConstants.SPACE);
                string = sbP.toString();
            }
        }
        StringBuilder sbQ = zn.q(string, "] ");
        sbQ.append(this.E);
        return sbQ.toString();
    }
}
