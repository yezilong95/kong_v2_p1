package kotlin;

import defpackage.t40;
import kotlin.Result;

/* JADX INFO: loaded from: classes.dex */
public abstract class a {
    public static final Result.Failure a(Throwable th) {
        t40.n(th, "exception");
        return new Result.Failure(th);
    }

    public static final void b(Object obj) {
        if (obj instanceof Result.Failure) {
            throw ((Result.Failure) obj).exception;
        }
    }
}
