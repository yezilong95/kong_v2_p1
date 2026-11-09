package defpackage;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;
import android.os.CancellationSignal;
import android.text.TextUtils;
import androidx.work.impl.utils.PreferenceUtils;
import java.util.List;
import org.java_websocket.extensions.ExtensionRequestData;

/* JADX INFO: loaded from: classes.dex */
public final class eu implements ql0 {
    public static final String[] d = {ExtensionRequestData.EMPTY_VALUE, " OR ROLLBACK ", " OR ABORT ", " OR FAIL ", " OR IGNORE ", " OR REPLACE "};
    public static final String[] e = new String[0];
    public final SQLiteDatabase b;
    public final List c;

    public eu(SQLiteDatabase sQLiteDatabase) {
        this.b = sQLiteDatabase;
        this.c = sQLiteDatabase.getAttachedDbs();
    }

    @Override // java.io.Closeable, java.lang.AutoCloseable
    public final void close() {
        this.b.close();
    }

    @Override // defpackage.ql0
    public final void e() {
        this.b.endTransaction();
    }

    @Override // defpackage.ql0
    public final void f() {
        this.b.beginTransaction();
    }

    @Override // defpackage.ql0
    public final Cursor g(vl0 vl0Var) {
        Cursor cursorRawQueryWithFactory = this.b.rawQueryWithFactory(new cu(1, new du(vl0Var)), vl0Var.t(), e, null);
        oy.i(cursorRawQueryWithFactory, "delegate.rawQueryWithFac…EMPTY_STRING_ARRAY, null)");
        return cursorRawQueryWithFactory;
    }

    @Override // defpackage.ql0
    public final boolean h() {
        SQLiteDatabase sQLiteDatabase = this.b;
        oy.j(sQLiteDatabase, "sQLiteDatabase");
        return sQLiteDatabase.isWriteAheadLoggingEnabled();
    }

    @Override // defpackage.ql0
    public final void i(String str) {
        oy.j(str, "sql");
        this.b.execSQL(str);
    }

    @Override // defpackage.ql0
    public final boolean isOpen() {
        return this.b.isOpen();
    }

    @Override // defpackage.ql0
    public final void k(Object[] objArr) {
        this.b.execSQL(PreferenceUtils.INSERT_PREFERENCE, objArr);
    }

    @Override // defpackage.ql0
    public final void l() {
        this.b.setTransactionSuccessful();
    }

    @Override // defpackage.ql0
    public final Cursor m(vl0 vl0Var, CancellationSignal cancellationSignal) {
        String strT = vl0Var.t();
        String[] strArr = e;
        cu cuVar = new cu(0, vl0Var);
        SQLiteDatabase sQLiteDatabase = this.b;
        oy.j(strT, "sql");
        Cursor cursorRawQueryWithFactory = sQLiteDatabase.rawQueryWithFactory(cuVar, strT, strArr, null, cancellationSignal);
        oy.i(cursorRawQueryWithFactory, "sQLiteDatabase.rawQueryW…ationSignal\n            )");
        return cursorRawQueryWithFactory;
    }

    @Override // defpackage.ql0
    public final wl0 p(String str) {
        SQLiteStatement sQLiteStatementCompileStatement = this.b.compileStatement(str);
        oy.i(sQLiteStatementCompileStatement, "delegate.compileStatement(sql)");
        return new mu(sQLiteStatementCompileStatement);
    }

    @Override // defpackage.ql0
    public final void q() {
        this.b.beginTransactionNonExclusive();
    }

    @Override // defpackage.ql0
    public final int v(ContentValues contentValues, Object[] objArr) {
        if (contentValues.size() == 0) {
            throw new IllegalArgumentException("Empty values");
        }
        int size = contentValues.size();
        int length = objArr.length + size;
        Object[] objArr2 = new Object[length];
        StringBuilder sb = new StringBuilder("UPDATE ");
        sb.append(d[3]);
        sb.append("WorkSpec SET ");
        int i = 0;
        for (String str : contentValues.keySet()) {
            sb.append(i > 0 ? "," : ExtensionRequestData.EMPTY_VALUE);
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
        oy.i(string, "StringBuilder().apply(builderAction).toString()");
        wl0 wl0VarP = p(string);
        nv0.f(wl0VarP, objArr2);
        return ((mu) wl0VarP).c.executeUpdateDelete();
    }

    @Override // defpackage.ql0
    public final Cursor y(String str) {
        oy.j(str, "query");
        return g(new e5(str));
    }

    @Override // defpackage.ql0
    public final boolean z() {
        return this.b.inTransaction();
    }
}
