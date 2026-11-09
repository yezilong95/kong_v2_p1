package defpackage;

import android.database.sqlite.SQLiteStatement;

/* JADX INFO: loaded from: classes.dex */
public final class y00 extends x00 implements ky0 {
    public final SQLiteStatement F;

    public y00(SQLiteStatement sQLiteStatement) {
        super(sQLiteStatement);
        this.F = sQLiteStatement;
    }

    @Override // defpackage.ky0
    public final int f() {
        return this.F.executeUpdateDelete();
    }

    @Override // defpackage.ky0
    public final long u() {
        return this.F.executeInsert();
    }
}
