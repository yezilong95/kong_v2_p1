package defpackage;

/* JADX INFO: loaded from: classes.dex */
public final class aq0 implements lh, mh {
    public static final aq0 b = new aq0();

    @Override // defpackage.nh
    public final Object fold(Object obj, ru ruVar) {
        return k9.n(this, obj, ruVar);
    }

    @Override // defpackage.nh
    public final lh get(mh mhVar) {
        oy.j(mhVar, "key");
        if (oy.c(this, mhVar)) {
            return this;
        }
        return null;
    }

    @Override // defpackage.nh
    public final nh minusKey(mh mhVar) {
        return k9.y(this, mhVar);
    }

    @Override // defpackage.nh
    public final nh plus(nh nhVar) {
        return k9.A(nhVar, this);
    }

    @Override // defpackage.lh
    public final mh getKey() {
        return this;
    }
}
