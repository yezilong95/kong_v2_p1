package defpackage;

/* JADX INFO: loaded from: classes.dex */
public final class jd0 implements yu0 {
    public final /* synthetic */ float E;

    public jd0(float f) {
        this.E = f;
    }

    @Override // defpackage.yu0
    public final yi a(yi yiVar) {
        return yiVar instanceof jo0 ? yiVar : new d4(this.E, yiVar);
    }
}
