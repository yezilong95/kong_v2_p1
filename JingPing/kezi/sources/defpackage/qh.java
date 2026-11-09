package defpackage;

import kotlin.coroutines.EmptyCoroutineContext;

/* JADX INFO: loaded from: classes.dex */
public abstract class qh extends h implements gh {
    public static final ph c = new ph(qm.c, oh.c);

    public qh() {
        super(qm.c);
    }

    public void C(nh nhVar, Runnable runnable) {
        t(nhVar, runnable);
    }

    public boolean D() {
        return !(this instanceof zp0);
    }

    /* JADX WARN: Type inference failed for: r3v3, types: [kotlin.jvm.internal.Lambda, pu] */
    @Override // defpackage.h, defpackage.nh
    public final lh get(mh mhVar) {
        lh lhVar;
        oy.j(mhVar, "key");
        if (!(mhVar instanceof ph)) {
            if (qm.c == mhVar) {
                return this;
            }
            return null;
        }
        ph phVar = (ph) mhVar;
        mh mhVar2 = this.b;
        if ((mhVar2 == phVar || phVar.c == mhVar2) && (lhVar = (lh) phVar.b.invoke(this)) != null) {
            return lhVar;
        }
        return null;
    }

    /* JADX WARN: Type inference failed for: r3v3, types: [kotlin.jvm.internal.Lambda, pu] */
    @Override // defpackage.h, defpackage.nh
    public final nh minusKey(mh mhVar) {
        oy.j(mhVar, "key");
        if (mhVar instanceof ph) {
            ph phVar = (ph) mhVar;
            mh mhVar2 = this.b;
            if ((mhVar2 == phVar || phVar.c == mhVar2) && ((lh) phVar.b.invoke(this)) != null) {
                return EmptyCoroutineContext.INSTANCE;
            }
        } else if (qm.c == mhVar) {
            return EmptyCoroutineContext.INSTANCE;
        }
        return this;
    }

    public abstract void t(nh nhVar, Runnable runnable);

    public String toString() {
        return getClass().getSimpleName() + '@' + xi.y(this);
    }
}
