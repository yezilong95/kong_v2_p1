package defpackage;

/* JADX INFO: loaded from: classes.dex */
public final class v91 extends a0 implements w91 {
    public d0 E = w91.b;
    public g0 F;

    public v91(int i, int i2, int i3, int i4) {
        u uVar = new u(3);
        uVar.c(new y(i));
        if (i3 == 0) {
            if (i4 != 0) {
                throw new IllegalArgumentException("inconsistent k values");
            }
            uVar.c(w91.c);
            uVar.c(new y(i2));
        } else {
            if (i3 <= i2 || i4 <= i3) {
                throw new IllegalArgumentException("inconsistent k values");
            }
            uVar.c(w91.d);
            u uVar2 = new u(3);
            uVar2.c(new y(i2));
            uVar2.c(new y(i3));
            uVar2.c(new y(i4));
            wk wkVar = new wk(uVar2, 0);
            wkVar.H = -1;
            uVar.c(wkVar);
        }
        wk wkVar2 = new wk(uVar, 0);
        wkVar2.H = -1;
        this.F = wkVar2;
    }

    @Override // defpackage.a0, defpackage.t
    public final g0 b() {
        u uVar = new u(2);
        uVar.c(this.E);
        uVar.c(this.F);
        wk wkVar = new wk(uVar, 0);
        wkVar.H = -1;
        return wkVar;
    }
}
