package defpackage;

/* JADX INFO: loaded from: classes.dex */
public final class fl implements nh {
    public final Throwable b;
    public final /* synthetic */ nh c;

    public fl(nh nhVar, Throwable th) {
        this.b = th;
        this.c = nhVar;
    }

    @Override // defpackage.nh
    public final Object fold(Object obj, ru ruVar) {
        return this.c.fold(obj, ruVar);
    }

    @Override // defpackage.nh
    public final lh get(mh mhVar) {
        return this.c.get(mhVar);
    }

    @Override // defpackage.nh
    public final nh minusKey(mh mhVar) {
        return this.c.minusKey(mhVar);
    }

    @Override // defpackage.nh
    public final nh plus(nh nhVar) {
        return this.c.plus(nhVar);
    }
}
