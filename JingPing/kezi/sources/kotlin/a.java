package kotlin;

import defpackage.oy;
import kotlin.Result;

/* JADX INFO: loaded from: classes.dex */
public abstract class a {
    public static final Result.Failure a(Throwable th) {
        oy.j(th, "exception");
        return new Result.Failure(th);
    }

    public static final void b(Object obj) throws Throwable {
        if (obj instanceof Result.Failure) {
            throw ((Result.Failure) obj).exception;
        }
    }
}
