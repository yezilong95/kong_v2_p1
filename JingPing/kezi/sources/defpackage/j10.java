package defpackage;

import java.lang.reflect.InvocationTargetException;
import kotlin.Result;
import kotlin.a;

/* JADX INFO: loaded from: classes.dex */
public final class j10 extends jk0 {
    public final fh e;

    public j10(nh nhVar, me meVar) {
        super(nhVar, false);
        this.e = oy.r(meVar, this, this);
    }

    @Override // defpackage.nz
    public final void w() throws IllegalAccessException, InvocationTargetException {
        try {
            oy.K(oy.D(this.e), Result.m3constructorimpl(bq0.a));
        } catch (Throwable th) {
            resumeWith(Result.m3constructorimpl(a.a(th)));
            throw th;
        }
    }
}
