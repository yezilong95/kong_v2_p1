package androidx.activity.contextaware;

import defpackage.b10;
import defpackage.mi;
import defpackage.oi;
import defpackage.y21;
import kotlin.jvm.internal.Lambda;

/* JADX INFO: loaded from: classes.dex */
public final class ContextAwareKt$withContextAvailable$2$1 extends Lambda implements b10 {
    public final /* synthetic */ mi E;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public ContextAwareKt$withContextAvailable$2$1(mi miVar, oi oiVar) {
        super(1);
        this.E = miVar;
    }

    @Override // defpackage.b10
    public /* bridge */ /* synthetic */ Object invoke(Object obj) {
        invoke((Throwable) obj);
        return y21.a;
    }

    public final void invoke(Throwable th) {
        this.E.removeOnContextAvailableListener(null);
    }
}
