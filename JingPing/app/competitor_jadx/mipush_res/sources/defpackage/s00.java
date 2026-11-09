package defpackage;

import androidx.sqlite.db.framework.FrameworkSQLiteOpenHelper$OpenHelper$CallbackName;

/* JADX INFO: loaded from: classes.dex */
public final class s00 extends RuntimeException {
    public final FrameworkSQLiteOpenHelper$OpenHelper$CallbackName E;
    public final Throwable F;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public s00(FrameworkSQLiteOpenHelper$OpenHelper$CallbackName frameworkSQLiteOpenHelper$OpenHelper$CallbackName, Throwable th) {
        super(th);
        t40.n(frameworkSQLiteOpenHelper$OpenHelper$CallbackName, "callbackName");
        this.E = frameworkSQLiteOpenHelper$OpenHelper$CallbackName;
        this.F = th;
    }

    @Override // java.lang.Throwable
    public final Throwable getCause() {
        return this.F;
    }
}
