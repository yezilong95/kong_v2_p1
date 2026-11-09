package defpackage;

/* JADX INFO: loaded from: classes.dex */
public final class s50 implements ji0 {
    public final /* synthetic */ float b;

    public s50(float f) {
        this.b = f;
    }

    @Override // defpackage.ji0
    public final kh a(kh khVar) {
        return khVar instanceof je0 ? khVar : new q2(this.b, khVar);
    }
}
