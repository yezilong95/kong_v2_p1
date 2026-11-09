package defpackage;

import kotlin.Result;
import kotlin.a;

/* JADX INFO: loaded from: classes.dex */
public final class l80 extends yw0 {
    public final ui H;

    public l80(bj bjVar, gg ggVar) {
        super(bjVar, false);
        this.H = t40.C(ggVar, this, this);
    }

    @Override // defpackage.t50
    public final void w() {
        try {
            t40.b1(t40.u0(this.H), Result.m3constructorimpl(y21.a));
        } catch (Throwable th) {
            resumeWith(Result.m3constructorimpl(a.a(th)));
            throw th;
        }
    }
}
