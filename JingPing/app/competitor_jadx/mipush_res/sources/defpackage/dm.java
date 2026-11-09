package defpackage;

/* JADX INFO: loaded from: classes.dex */
public final class dm extends a0 {
    public int E;
    public int F;
    public int G;
    public int H;

    @Override // defpackage.a0, defpackage.t
    public final g0 b() {
        u uVar = new u(2);
        uVar.c(new y(this.E));
        int i = this.F;
        int i2 = this.G;
        if (i2 == 0) {
            uVar.c(new y(i));
        } else {
            u uVar2 = new u(3);
            uVar2.c(new y(i));
            uVar2.c(new y(i2));
            uVar2.c(new y(this.H));
            wk wkVar = new wk(uVar2, 0);
            wkVar.H = -1;
            uVar.c(wkVar);
        }
        wk wkVar2 = new wk(uVar, 0);
        wkVar2.H = -1;
        return wkVar2;
    }
}
