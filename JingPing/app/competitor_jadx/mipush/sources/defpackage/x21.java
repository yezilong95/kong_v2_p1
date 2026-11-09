package defpackage;

/* JADX INFO: loaded from: classes.dex */
public final class x21 implements zi, aj {
    public static final x21 E = new x21();

    @Override // defpackage.bj
    public final Object fold(Object obj, d10 d10Var) {
        return qm.p(this, obj, d10Var);
    }

    @Override // defpackage.bj
    public final zi get(aj ajVar) {
        t40.n(ajVar, "key");
        if (t40.f(this, ajVar)) {
            return this;
        }
        return null;
    }

    @Override // defpackage.bj
    public final bj minusKey(aj ajVar) {
        return qm.T(this, ajVar);
    }

    @Override // defpackage.bj
    public final bj plus(bj bjVar) {
        return qm.a0(bjVar, this);
    }

    @Override // defpackage.zi
    public final aj getKey() {
        return this;
    }
}
