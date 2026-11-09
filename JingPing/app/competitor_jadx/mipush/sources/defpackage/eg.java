package defpackage;

import kotlin.coroutines.jvm.internal.ContinuationImpl;

/* JADX INFO: loaded from: classes.dex */
public final class eg extends ContinuationImpl {
    public /* synthetic */ Object H;
    public final /* synthetic */ fg I;
    public int J;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public eg(fg fgVar, ui uiVar) {
        super(uiVar);
        this.I = fgVar;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        this.H = obj;
        this.J |= Integer.MIN_VALUE;
        return this.I.emit(null, this);
    }
}
