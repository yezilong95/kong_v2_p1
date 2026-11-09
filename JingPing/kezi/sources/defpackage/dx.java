package defpackage;

import okio.ByteString;

/* JADX INFO: loaded from: classes.dex */
public abstract class dx {
    static {
        ByteString.Companion.getClass();
        aa.c("\"\\");
        aa.c("\t ,=");
    }

    public static final boolean a(gf0 gf0Var) {
        if (oy.c((String) gf0Var.b.c, "HEAD")) {
            return false;
        }
        int i = gf0Var.e;
        return (((i >= 100 && i < 200) || i == 204 || i == 304) && kq0.k(gf0Var) == -1 && !"chunked".equalsIgnoreCase(gf0.a(gf0Var, "Transfer-Encoding"))) ? false : true;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:21:0x0080  */
    /* JADX WARN: Type inference failed for: r24v3 */
    /* JADX WARN: Type inference failed for: r25v2 */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static final void b(defpackage.qm r35, defpackage.fx r36, defpackage.tv r37) {
        /*
            Method dump skipped, instruction units count: 581
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.dx.b(qm, fx, tv):void");
    }
}
