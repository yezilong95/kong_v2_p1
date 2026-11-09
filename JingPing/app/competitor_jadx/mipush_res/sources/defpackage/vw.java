package defpackage;

/* JADX INFO: loaded from: classes.dex */
public final class vw extends ww {
    public final /* synthetic */ int e;
    public final /* synthetic */ zw f;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public /* synthetic */ vw(zw zwVar, int i) {
        super(zwVar);
        this.e = i;
        this.f = zwVar;
    }

    @Override // defpackage.ww
    public final float a() {
        switch (this.e) {
            case 0:
                zw zwVar = this.f;
                return zwVar.h + zwVar.i;
            case 1:
                zw zwVar2 = this.f;
                return zwVar2.h + zwVar2.j;
            default:
                return this.f.h;
        }
    }
}
