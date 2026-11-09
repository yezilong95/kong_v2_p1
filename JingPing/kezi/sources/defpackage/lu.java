package defpackage;

import android.database.sqlite.SQLiteProgram;

/* JADX INFO: loaded from: classes.dex */
public class lu implements ul0 {
    public final SQLiteProgram b;

    public lu(SQLiteProgram sQLiteProgram) {
        oy.j(sQLiteProgram, "delegate");
        this.b = sQLiteProgram;
    }

    @Override // defpackage.ul0
    public final void B(byte[] bArr, int i) {
        this.b.bindBlob(i, bArr);
    }

    @Override // java.io.Closeable, java.lang.AutoCloseable
    public final void close() {
        this.b.close();
    }

    @Override // defpackage.ul0
    public final void j(int i, String str) {
        oy.j(str, "value");
        this.b.bindString(i, str);
    }

    @Override // defpackage.ul0
    public final void r(int i) {
        this.b.bindNull(i);
    }

    @Override // defpackage.ul0
    public final void s(int i, double d) {
        this.b.bindDouble(i, d);
    }

    @Override // defpackage.ul0
    public final void x(long j, int i) {
        this.b.bindLong(i, j);
    }
}
