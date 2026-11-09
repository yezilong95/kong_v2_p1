package defpackage;

import androidx.sqlite.db.framework.FrameworkSQLiteOpenHelper$OpenHelper$CallbackName;

/* JADX INFO: loaded from: classes.dex */
public final class gu extends RuntimeException {
    public final FrameworkSQLiteOpenHelper$OpenHelper$CallbackName b;
    public final Throwable c;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public gu(FrameworkSQLiteOpenHelper$OpenHelper$CallbackName frameworkSQLiteOpenHelper$OpenHelper$CallbackName, Throwable th) {
        super(th);
        oy.j(frameworkSQLiteOpenHelper$OpenHelper$CallbackName, "callbackName");
        this.b = frameworkSQLiteOpenHelper$OpenHelper$CallbackName;
        this.c = th;
    }

    @Override // java.lang.Throwable
    public final Throwable getCause() {
        return this.c;
    }
}
