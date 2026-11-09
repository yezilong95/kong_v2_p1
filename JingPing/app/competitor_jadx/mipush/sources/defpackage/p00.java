package defpackage;

import android.database.sqlite.SQLiteCursor;
import android.database.sqlite.SQLiteCursorDriver;
import android.database.sqlite.SQLiteQuery;
import kotlin.jvm.internal.Lambda;

/* JADX INFO: loaded from: classes.dex */
public final class p00 extends Lambda implements f10 {
    public final /* synthetic */ jy0 E;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public p00(jy0 jy0Var) {
        super(4);
        this.E = jy0Var;
    }

    @Override // defpackage.f10
    public final Object invoke(Object obj, Object obj2, Object obj3, Object obj4) {
        SQLiteQuery sQLiteQuery = (SQLiteQuery) obj4;
        t40.k(sQLiteQuery);
        this.E.l(new x00(sQLiteQuery));
        return new SQLiteCursor((SQLiteCursorDriver) obj2, (String) obj3, sQLiteQuery);
    }
}
