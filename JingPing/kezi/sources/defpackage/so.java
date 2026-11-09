package defpackage;

import java.io.IOException;
import okhttp3.internal.http2.ConnectionShutdownException;
import okhttp3.internal.http2.ErrorCode;
import okhttp3.internal.http2.StreamResetException;

/* JADX INFO: loaded from: classes.dex */
public final class so {
    public final wc0 a;
    public final uo b;
    public final to c;
    public boolean d;
    public final yc0 e;

    public so(wc0 wc0Var, uo uoVar, to toVar) {
        oy.j(wc0Var, "call");
        oy.j(uoVar, "finder");
        this.a = wc0Var;
        this.b = uoVar;
        this.c = toVar;
        this.e = toVar.h();
    }

    public final IOException a(boolean z, boolean z2, IOException iOException) {
        if (iOException != null) {
            d(iOException);
        }
        wc0 wc0Var = this.a;
        if (z2) {
            if (iOException != null) {
                oy.j(wc0Var, "call");
            } else {
                oy.j(wc0Var, "call");
            }
        }
        if (z) {
            if (iOException != null) {
                oy.j(wc0Var, "call");
            } else {
                oy.j(wc0Var, "call");
            }
        }
        return wc0Var.h(this, z2, z, iOException);
    }

    public final bd0 b(gf0 gf0Var) throws IOException {
        to toVar = this.c;
        try {
            String strA = gf0.a(gf0Var, "Content-Type");
            long jG = toVar.g(gf0Var);
            return new bd0(strA, jG, new sc0(new ro(this, toVar.c(gf0Var), jG)));
        } catch (IOException e) {
            oy.j(this.a, "call");
            d(e);
            throw e;
        }
    }

    public final ff0 c(boolean z) throws IOException {
        try {
            ff0 ff0VarF = this.c.f(z);
            if (ff0VarF != null) {
                ff0VarF.m = this;
            }
            return ff0VarF;
        } catch (IOException e) {
            oy.j(this.a, "call");
            d(e);
            throw e;
        }
    }

    public final void d(IOException iOException) {
        this.d = true;
        this.b.c(iOException);
        yc0 yc0VarH = this.c.h();
        wc0 wc0Var = this.a;
        synchronized (yc0VarH) {
            try {
                oy.j(wc0Var, "call");
                if (!(iOException instanceof StreamResetException)) {
                    if (!(yc0VarH.g != null) || (iOException instanceof ConnectionShutdownException)) {
                        yc0VarH.j = true;
                        if (yc0VarH.m == 0) {
                            yc0.d(wc0Var.b, yc0VarH.b, iOException);
                            yc0VarH.l++;
                        }
                    }
                } else if (((StreamResetException) iOException).errorCode == ErrorCode.REFUSED_STREAM) {
                    int i = yc0VarH.n + 1;
                    yc0VarH.n = i;
                    if (i > 1) {
                        yc0VarH.j = true;
                        yc0VarH.l++;
                    }
                } else if (((StreamResetException) iOException).errorCode != ErrorCode.CANCEL || !wc0Var.n) {
                    yc0VarH.j = true;
                    yc0VarH.l++;
                }
            } catch (Throwable th) {
                throw th;
            }
        }
    }
}
