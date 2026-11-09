package kotlin.coroutines.jvm.internal;

import defpackage.fh;
import defpackage.gh;
import defpackage.lh;
import defpackage.ma;
import defpackage.nh;
import defpackage.oy;
import defpackage.qh;
import defpackage.qm;
import defpackage.te;
import defpackage.yk;
import java.util.concurrent.atomic.AtomicReferenceFieldUpdater;

/* JADX INFO: loaded from: classes.dex */
public abstract class ContinuationImpl extends BaseContinuationImpl {
    public final nh c;
    public transient fh d;

    public ContinuationImpl(fh fhVar, nh nhVar) {
        super(fhVar);
        this.c = nhVar;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl, defpackage.fh
    public nh getContext() {
        nh nhVar = this.c;
        oy.g(nhVar);
        return nhVar;
    }

    public final fh intercepted() {
        fh fhVar = this.d;
        if (fhVar != null) {
            return fhVar;
        }
        gh ghVar = (gh) getContext().get(qm.c);
        fh ykVar = ghVar != null ? new yk((qh) ghVar, this) : this;
        this.d = ykVar;
        return ykVar;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public void releaseIntercepted() {
        AtomicReferenceFieldUpdater atomicReferenceFieldUpdater;
        fh fhVar = this.d;
        if (fhVar != null && fhVar != this) {
            lh lhVar = getContext().get(qm.c);
            oy.g(lhVar);
            yk ykVar = (yk) fhVar;
            do {
                atomicReferenceFieldUpdater = yk.i;
            } while (atomicReferenceFieldUpdater.get(ykVar) == oy.c);
            Object obj = atomicReferenceFieldUpdater.get(ykVar);
            ma maVar = obj instanceof ma ? (ma) obj : null;
            if (maVar != null) {
                maVar.k();
            }
        }
        this.d = te.b;
    }

    public ContinuationImpl(fh fhVar) {
        this(fhVar, fhVar != null ? fhVar.getContext() : null);
    }
}
