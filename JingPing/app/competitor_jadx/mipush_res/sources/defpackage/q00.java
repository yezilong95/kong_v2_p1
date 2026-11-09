package defpackage;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;
import android.os.CancellationSignal;
import android.text.TextUtils;
import androidx.work.impl.utils.PreferenceUtils;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public final class q00 implements ey0 {
    public static final String[] G = {"", " OR ROLLBACK ", " OR ABORT ", " OR FAIL ", " OR IGNORE ", " OR REPLACE "};
    public static final String[] H = new String[0];
    public final SQLiteDatabase E;
    public final List F;

    public q00(SQLiteDatabase sQLiteDatabase) {
        this.E = sQLiteDatabase;
        this.F = sQLiteDatabase.getAttachedDbs();
    }

    @Override // defpackage.ey0
    public final void a() {
        this.E.endTransaction();
    }

    @Override // defpackage.ey0
    public final void b() {
        this.E.beginTransaction();
    }

    @Override // defpackage.ey0
    public final Cursor c(jy0 jy0Var) {
        Cursor cursorRawQueryWithFactory = this.E.rawQueryWithFactory(new o00(1, new p00(jy0Var)), jy0Var.j(), H, null);
        t40.m(cursorRawQueryWithFactory, "delegate.rawQueryWithFac…EMPTY_STRING_ARRAY, null)");
        return cursorRawQueryWithFactory;
    }

    @Override // java.io.Closeable, java.lang.AutoCloseable
    public final void close() {
        this.E.close();
    }

    @Override // defpackage.ey0
    public final void d(String str) {
        t40.n(str, "sql");
        this.E.execSQL(str);
    }

    @Override // defpackage.ey0
    public final ky0 g(String str) {
        SQLiteStatement sQLiteStatementCompileStatement = this.E.compileStatement(str);
        t40.m(sQLiteStatementCompileStatement, "delegate.compileStatement(sql)");
        return new y00(sQLiteStatementCompileStatement);
    }

    @Override // defpackage.ey0
    public final boolean isOpen() {
        return this.E.isOpen();
    }

    @Override // defpackage.ey0
    public final boolean k() {
        return this.E.inTransaction();
    }

    @Override // defpackage.ey0
    public final boolean m() {
        SQLiteDatabase sQLiteDatabase = this.E;
        t40.n(sQLiteDatabase, "sQLiteDatabase");
        return sQLiteDatabase.isWriteAheadLoggingEnabled();
    }

    @Override // defpackage.ey0
    public final void o(Object[] objArr) {
        this.E.execSQL(PreferenceUtils.INSERT_PREFERENCE, objArr);
    }

    @Override // defpackage.ey0
    public final void p() {
        this.E.setTransactionSuccessful();
    }

    @Override // defpackage.ey0
    public final Cursor q(jy0 jy0Var, CancellationSignal cancellationSignal) {
        String strJ = jy0Var.j();
        String[] strArr = H;
        o00 o00Var = new o00(0, jy0Var);
        SQLiteDatabase sQLiteDatabase = this.E;
        t40.n(strJ, "sql");
        Cursor cursorRawQueryWithFactory = sQLiteDatabase.rawQueryWithFactory(o00Var, strJ, strArr, null, cancellationSignal);
        t40.m(cursorRawQueryWithFactory, "sQLiteDatabase.rawQueryW…ationSignal\n            )");
        return cursorRawQueryWithFactory;
    }

    @Override // defpackage.ey0
    public final void r() {
        this.E.beginTransactionNonExclusive();
    }

    @Override // defpackage.ey0
    public final int t(ContentValues contentValues, Object[] objArr) {
        if (contentValues.size() == 0) {
            throw new IllegalArgumentException("Empty values");
        }
        int size = contentValues.size();
        int length = objArr.length + size;
        Object[] objArr2 = new Object[length];
        StringBuilder sb = new StringBuilder("UPDATE ");
        sb.append(G[3]);
        sb.append("WorkSpec SET ");
        int i = 0;
        for (String str : contentValues.keySet()) {
            sb.append(i > 0 ? "," : "");
            sb.append(str);
            objArr2[i] = contentValues.get(str);
            sb.append("=?");
            i++;
        }
        for (int i2 = size; i2 < length; i2++) {
            objArr2[i2] = objArr[i2 - size];
        }
        if (!TextUtils.isEmpty("last_enqueue_time = 0 AND interval_duration <> 0 ")) {
            sb.append(" WHERE last_enqueue_time = 0 AND interval_duration <> 0 ");
        }
        String string = sb.toString();
        t40.m(string, "StringBuilder().apply(builderAction).toString()");
        ky0 ky0VarG = g(string);
        ma0.h(ky0VarG, objArr2);
        return ((y00) ky0VarG).F.executeUpdateDelete();
    }

    @Override // defpackage.ey0
    public final Cursor v(String str) {
        t40.n(str, "query");
        return c(new i6(str));
    }
}
