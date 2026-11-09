package androidx.activity.contextaware;

import defpackage.bq0;
import defpackage.pu;
import defpackage.xg;
import defpackage.zg;
import kotlin.jvm.internal.Lambda;

/* JADX INFO: loaded from: classes.dex */
public final class ContextAwareKt$withContextAvailable$2$1 extends Lambda implements pu {
    public final /* synthetic */ xg b;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public ContextAwareKt$withContextAvailable$2$1(xg xgVar, zg zgVar) {
        super(1);
        this.b = xgVar;
    }

    @Override // defpackage.pu
    public /* bridge */ /* synthetic */ Object invoke(Object obj) {
        invoke((Throwable) obj);
        return bq0.a;
    }

    public final void invoke(Throwable th) {
        this.b.removeOnContextAvailableListener(null);
    }
}
