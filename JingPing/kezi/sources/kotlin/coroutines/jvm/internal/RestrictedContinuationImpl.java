package kotlin.coroutines.jvm.internal;

import defpackage.fh;
import defpackage.nh;
import kotlin.coroutines.EmptyCoroutineContext;

/* JADX INFO: loaded from: classes.dex */
public abstract class RestrictedContinuationImpl extends BaseContinuationImpl {
    public RestrictedContinuationImpl(fh fhVar) {
        super(fhVar);
        if (fhVar != null && fhVar.getContext() != EmptyCoroutineContext.INSTANCE) {
            throw new IllegalArgumentException("Coroutines with restricted suspension must have EmptyCoroutineContext");
        }
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl, defpackage.fh
    public nh getContext() {
        return EmptyCoroutineContext.INSTANCE;
    }
}
