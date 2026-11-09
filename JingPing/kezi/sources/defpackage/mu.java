package defpackage;

import android.database.sqlite.SQLiteStatement;

/* JADX INFO: loaded from: classes.dex */
public final class mu extends lu implements wl0 {
    public final SQLiteStatement c;

    public mu(SQLiteStatement sQLiteStatement) {
        super(sQLiteStatement);
        this.c = sQLiteStatement;
    }

    @Override // defpackage.wl0
    public final int n() {
        return this.c.executeUpdateDelete();
    }

    @Override // defpackage.wl0
    public final long w() {
        return this.c.executeInsert();
    }
}
