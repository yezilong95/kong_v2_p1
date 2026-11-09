package defpackage;

import java.util.ArrayList;
import java.util.Set;

/* JADX INFO: loaded from: classes.dex */
public final class cb {
    public static final cb c = new cb(ae.g0(new ArrayList()), null);
    public final Set a;
    public final oy b;

    public cb(Set set, oy oyVar) {
        oy.j(set, "pins");
        this.a = set;
        this.b = oyVar;
    }

    /* JADX WARN: Removed duplicated region for block: B:12:0x0054  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final void a(java.lang.String r15, defpackage.ou r16) throws javax.net.ssl.SSLPeerUnverifiedException {
        /*
            Method dump skipped, instruction units count: 420
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.cb.a(java.lang.String, ou):void");
    }

    public final boolean equals(Object obj) {
        if (!(obj instanceof cb)) {
            return false;
        }
        cb cbVar = (cb) obj;
        return oy.c(cbVar.a, this.a) && oy.c(cbVar.b, this.b);
    }

    public final int hashCode() {
        int iHashCode = (this.a.hashCode() + 1517) * 41;
        oy oyVar = this.b;
        return iHashCode + (oyVar != null ? oyVar.hashCode() : 0);
    }
}
