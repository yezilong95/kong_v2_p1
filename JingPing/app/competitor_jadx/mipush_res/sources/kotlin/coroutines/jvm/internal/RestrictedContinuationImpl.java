package kotlin.coroutines.jvm.internal;

import defpackage.bj;
import defpackage.ui;
import kotlin.coroutines.EmptyCoroutineContext;

/* JADX INFO: loaded from: classes.dex */
public abstract class RestrictedContinuationImpl extends BaseContinuationImpl {
    public RestrictedContinuationImpl(ui uiVar) {
        super(uiVar);
        if (uiVar != null && uiVar.getContext() != EmptyCoroutineContext.INSTANCE) {
            throw new IllegalArgumentException("Coroutines with restricted suspension must have EmptyCoroutineContext");
        }
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl, defpackage.ui
    public bj getContext() {
        return EmptyCoroutineContext.INSTANCE;
    }
}
