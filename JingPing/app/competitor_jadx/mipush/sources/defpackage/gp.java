package defpackage;

/* JADX INFO: loaded from: classes.dex */
public final class gp implements bj {
    public final Throwable E;
    public final /* synthetic */ bj F;

    public gp(bj bjVar, Throwable th) {
        this.E = th;
        this.F = bjVar;
    }

    @Override // defpackage.bj
    public final Object fold(Object obj, d10 d10Var) {
        return this.F.fold(obj, d10Var);
    }

    @Override // defpackage.bj
    public final zi get(aj ajVar) {
        return this.F.get(ajVar);
    }

    @Override // defpackage.bj
    public final bj minusKey(aj ajVar) {
        return this.F.minusKey(ajVar);
    }

    @Override // defpackage.bj
    public final bj plus(bj bjVar) {
        return this.F.plus(bjVar);
    }
}
