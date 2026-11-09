package defpackage;

/* JADX INFO: loaded from: classes.dex */
public final class u91 extends a0 {
    public final tk E;
    public final xq F;
    public or G;

    public u91(xq xqVar, byte[] bArr) {
        this.F = xqVar;
        this.E = new tk(z91.l(bArr));
    }

    @Override // defpackage.a0, defpackage.t
    public final g0 b() {
        return this.E;
    }

    public final synchronized or g() {
        try {
            if (this.G == null) {
                this.G = this.F.e(this.E.E).o();
            }
        } catch (Throwable th) {
            throw th;
        }
        return this.G;
    }

    public u91(or orVar, boolean z) {
        this.G = orVar.o();
        this.E = new tk(orVar.h(z));
    }
}
