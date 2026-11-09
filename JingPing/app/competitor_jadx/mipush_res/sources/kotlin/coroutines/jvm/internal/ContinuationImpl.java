package kotlin.coroutines.jvm.internal;

import defpackage.bj;
import defpackage.ec;
import defpackage.ej;
import defpackage.is;
import defpackage.ng;
import defpackage.t40;
import defpackage.ui;
import defpackage.vi;
import defpackage.vo;
import defpackage.zi;
import java.util.concurrent.atomic.AtomicReferenceFieldUpdater;

/* JADX INFO: loaded from: classes.dex */
public abstract class ContinuationImpl extends BaseContinuationImpl {
    public final bj F;
    public transient ui G;

    public ContinuationImpl(ui uiVar, bj bjVar) {
        super(uiVar);
        this.F = bjVar;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl, defpackage.ui
    public bj getContext() {
        bj bjVar = this.F;
        t40.k(bjVar);
        return bjVar;
    }

    public final ui intercepted() {
        ui uiVar = this.G;
        if (uiVar != null) {
            return uiVar;
        }
        vi viVar = (vi) getContext().get(is.F);
        ui voVar = viVar != null ? new vo((ej) viVar, this) : this;
        this.G = voVar;
        return voVar;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public void releaseIntercepted() {
        AtomicReferenceFieldUpdater atomicReferenceFieldUpdater;
        ui uiVar = this.G;
        if (uiVar != null && uiVar != this) {
            zi ziVar = getContext().get(is.F);
            t40.k(ziVar);
            vo voVar = (vo) uiVar;
            do {
                atomicReferenceFieldUpdater = vo.L;
            } while (atomicReferenceFieldUpdater.get(voVar) == t40.d);
            Object obj = atomicReferenceFieldUpdater.get(voVar);
            ec ecVar = obj instanceof ec ? (ec) obj : null;
            if (ecVar != null) {
                ecVar.k();
            }
        }
        this.G = ng.E;
    }

    public ContinuationImpl(ui uiVar) {
        this(uiVar, uiVar != null ? uiVar.getContext() : null);
    }
}
