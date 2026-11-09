package defpackage;

import android.database.sqlite.SQLiteProgram;

/* JADX INFO: loaded from: classes.dex */
public class x00 implements iy0 {
    public final SQLiteProgram E;

    public x00(SQLiteProgram sQLiteProgram) {
        t40.n(sQLiteProgram, "delegate");
        this.E = sQLiteProgram;
    }

    @Override // java.io.Closeable, java.lang.AutoCloseable
    public final void close() {
        this.E.close();
    }

    @Override // defpackage.iy0
    public final void e(int i, String str) {
        t40.n(str, "value");
        this.E.bindString(i, str);
    }

    @Override // defpackage.iy0
    public final void h(int i) {
        this.E.bindNull(i);
    }

    @Override // defpackage.iy0
    public final void i(int i, double d) {
        this.E.bindDouble(i, d);
    }

    @Override // defpackage.iy0
    public final void n(int i, long j) {
        this.E.bindLong(i, j);
    }

    @Override // defpackage.iy0
    public final void s(int i, byte[] bArr) {
        this.E.bindBlob(i, bArr);
    }
}
