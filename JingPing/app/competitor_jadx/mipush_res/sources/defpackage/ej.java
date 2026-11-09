package defpackage;

import kotlin.coroutines.EmptyCoroutineContext;

/* JADX INFO: loaded from: classes.dex */
public abstract class ej extends r0 implements vi {
    public static final dj F = new dj(is.F, cj.F);

    public ej() {
        super(is.F);
    }

    /* JADX WARN: Type inference failed for: r3v3, types: [b10, kotlin.jvm.internal.Lambda] */
    @Override // defpackage.r0, defpackage.bj
    public final zi get(aj ajVar) {
        zi ziVar;
        t40.n(ajVar, "key");
        if (!(ajVar instanceof dj)) {
            if (is.F == ajVar) {
                return this;
            }
            return null;
        }
        dj djVar = (dj) ajVar;
        aj ajVar2 = this.E;
        if ((ajVar2 == djVar || djVar.F == ajVar2) && (ziVar = (zi) djVar.E.invoke(this)) != null) {
            return ziVar;
        }
        return null;
    }

    public abstract void j(bj bjVar, Runnable runnable);

    public void l(bj bjVar, Runnable runnable) {
        j(bjVar, runnable);
    }

    /* JADX WARN: Type inference failed for: r3v3, types: [b10, kotlin.jvm.internal.Lambda] */
    @Override // defpackage.r0, defpackage.bj
    public final bj minusKey(aj ajVar) {
        t40.n(ajVar, "key");
        if (ajVar instanceof dj) {
            dj djVar = (dj) ajVar;
            aj ajVar2 = this.E;
            if ((ajVar2 == djVar || djVar.F == ajVar2) && ((zi) djVar.E.invoke(this)) != null) {
                return EmptyCoroutineContext.INSTANCE;
            }
        } else if (is.F == ajVar) {
            return EmptyCoroutineContext.INSTANCE;
        }
        return this;
    }

    public String toString() {
        return getClass().getSimpleName() + '@' + qm.C(this);
    }

    public boolean w() {
        return !(this instanceof w21);
    }
}
