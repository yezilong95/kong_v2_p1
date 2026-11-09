package defpackage;

import java.lang.reflect.InvocationTargetException;

/* JADX INFO: loaded from: classes.dex */
public final class ec extends cz implements dc {
    public final g f;

    public ec(g gVar) {
        this.f = gVar;
    }

    @Override // defpackage.dc
    public final boolean b(Throwable th) {
        return i().h(th);
    }

    @Override // defpackage.pu
    public final /* bridge */ /* synthetic */ Object invoke(Object obj) throws IllegalAccessException, InvocationTargetException {
        j((Throwable) obj);
        return bq0.a;
    }

    @Override // defpackage.jz
    public final void j(Throwable th) throws IllegalAccessException, InvocationTargetException {
        this.f.e(i());
    }
}
