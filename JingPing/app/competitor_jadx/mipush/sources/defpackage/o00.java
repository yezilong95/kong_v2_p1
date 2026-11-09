package defpackage;

import android.database.Cursor;
import android.database.sqlite.SQLiteCursor;
import android.database.sqlite.SQLiteCursorDriver;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteQuery;

/* JADX INFO: loaded from: classes.dex */
public final /* synthetic */ class o00 implements SQLiteDatabase.CursorFactory {
    public final /* synthetic */ int a;
    public final /* synthetic */ Object b;

    public /* synthetic */ o00(int i, Object obj) {
        this.a = i;
        this.b = obj;
    }

    @Override // android.database.sqlite.SQLiteDatabase.CursorFactory
    public final Cursor newCursor(SQLiteDatabase sQLiteDatabase, SQLiteCursorDriver sQLiteCursorDriver, String str, SQLiteQuery sQLiteQuery) {
        switch (this.a) {
            case 0:
                jy0 jy0Var = (jy0) this.b;
                t40.k(sQLiteQuery);
                jy0Var.l(new x00(sQLiteQuery));
                return new SQLiteCursor(sQLiteCursorDriver, str, sQLiteQuery);
            default:
                return (Cursor) ((p00) this.b).invoke(sQLiteDatabase, sQLiteCursorDriver, str, sQLiteQuery);
        }
    }
}
