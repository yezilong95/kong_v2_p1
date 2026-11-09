package defpackage;

/* JADX INFO: loaded from: classes.dex */
public abstract class h implements lh {
    public final mh b;

    public h(mh mhVar) {
        this.b = mhVar;
    }

    @Override // defpackage.nh
    public final Object fold(Object obj, ru ruVar) {
        return k9.n(this, obj, ruVar);
    }

    @Override // defpackage.nh
    public lh get(mh mhVar) {
        oy.j(mhVar, "key");
        if (oy.c(getKey(), mhVar)) {
            return this;
        }
        return null;
    }

    @Override // defpackage.lh
    public final mh getKey() {
        return this.b;
    }

    @Override // defpackage.nh
    public nh minusKey(mh mhVar) {
        return k9.y(this, mhVar);
    }

    @Override // defpackage.nh
    public final nh plus(nh nhVar) {
        return k9.A(nhVar, this);
    }
}
