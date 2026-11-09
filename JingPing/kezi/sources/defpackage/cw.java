package defpackage;

import java.io.IOException;

/* JADX INFO: loaded from: classes.dex */
public abstract class cw implements uj0 {
    public final dt b;
    public boolean c;
    public final /* synthetic */ iw d;

    public cw(iw iwVar) {
        this.d = iwVar;
        this.b = new dt(((sc0) iwVar.d).b.b());
    }

    public final void a() {
        iw iwVar = this.d;
        int i = iwVar.a;
        if (i == 6) {
            return;
        }
        if (i != 5) {
            throw new IllegalStateException("state: " + iwVar.a);
        }
        dt dtVar = this.b;
        jo0 jo0Var = dtVar.e;
        dtVar.e = jo0.d;
        jo0Var.a();
        jo0Var.b();
        iwVar.a = 6;
    }

    @Override // defpackage.uj0
    public final jo0 b() {
        return this.b;
    }

    @Override // defpackage.uj0
    public long d(long j, p9 p9Var) throws IOException {
        iw iwVar = this.d;
        oy.j(p9Var, "sink");
        try {
            return ((sc0) iwVar.d).d(j, p9Var);
        } catch (IOException e) {
            ((yc0) iwVar.c).k();
            a();
            throw e;
        }
    }
}
