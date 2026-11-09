package defpackage;

/* JADX INFO: loaded from: classes.dex */
public final class kz extends jz {
    public final nz f;
    public final lz g;
    public final ec h;
    public final Object i;

    public kz(nz nzVar, lz lzVar, ec ecVar, Object obj) {
        this.f = nzVar;
        this.g = lzVar;
        this.h = ecVar;
        this.i = obj;
    }

    @Override // defpackage.pu
    public final /* bridge */ /* synthetic */ Object invoke(Object obj) {
        j((Throwable) obj);
        return bq0.a;
    }

    @Override // defpackage.jz
    public final void j(Throwable th) {
        ec ecVar = this.h;
        nz nzVar = this.f;
        nzVar.getClass();
        ec ecVarT = nz.t(ecVar);
        lz lzVar = this.g;
        Object obj = this.i;
        if (ecVarT != null) {
            while (rp0.F(ecVarT.f, new kz(nzVar, lzVar, ecVarT, obj), 1) == q90.b) {
                ecVarT = nz.t(ecVarT);
                if (ecVarT == null) {
                }
            }
            return;
        }
        nzVar.c(nzVar.k(lzVar, obj));
    }
}
