package defpackage;

import android.database.sqlite.SQLiteCursor;
import android.database.sqlite.SQLiteCursorDriver;
import android.database.sqlite.SQLiteQuery;
import kotlin.jvm.internal.Lambda;

/* JADX INFO: loaded from: classes.dex */
public final class du extends Lambda implements tu {
    public final /* synthetic */ vl0 b;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public du(vl0 vl0Var) {
        super(4);
        this.b = vl0Var;
    }

    @Override // defpackage.tu
    public final Object invoke(Object obj, Object obj2, Object obj3, Object obj4) {
        SQLiteQuery sQLiteQuery = (SQLiteQuery) obj4;
        oy.g(sQLiteQuery);
        this.b.a(new lu(sQLiteQuery));
        return new SQLiteCursor((SQLiteCursorDriver) obj2, (String) obj3, sQLiteQuery);
    }
}
