package defpackage;

/* JADX INFO: loaded from: classes.dex */
public abstract class r0 implements zi {
    public final aj E;

    public r0(aj ajVar) {
        this.E = ajVar;
    }

    @Override // defpackage.bj
    public final Object fold(Object obj, d10 d10Var) {
        return qm.p(this, obj, d10Var);
    }

    @Override // defpackage.bj
    public zi get(aj ajVar) {
        t40.n(ajVar, "key");
        if (t40.f(getKey(), ajVar)) {
            return this;
        }
        return null;
    }

    @Override // defpackage.zi
    public final aj getKey() {
        return this.E;
    }

    @Override // defpackage.bj
    public bj minusKey(aj ajVar) {
        return qm.T(this, ajVar);
    }

    @Override // defpackage.bj
    public final bj plus(bj bjVar) {
        return qm.a0(bjVar, this);
    }
}
