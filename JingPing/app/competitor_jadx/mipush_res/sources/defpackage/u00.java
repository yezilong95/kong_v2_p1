package defpackage;

import android.content.Context;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.util.Pair;
import androidx.sqlite.db.framework.FrameworkSQLiteOpenHelper$OpenHelper$CallbackName;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.UUID;

/* JADX INFO: loaded from: classes.dex */
public final class u00 extends SQLiteOpenHelper {
    public static final /* synthetic */ int L = 0;
    public final Context E;
    public final p50 F;
    public final l0 G;
    public final boolean H;
    public boolean I;
    public final tl0 J;
    public boolean K;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public u00(Context context, String str, final p50 p50Var, final l0 l0Var, boolean z) {
        String string;
        super(context, str, null, l0Var.b, new DatabaseErrorHandler() { // from class: r00
            @Override // android.database.DatabaseErrorHandler
            public final void onCorruption(SQLiteDatabase sQLiteDatabase) {
                t40.n(l0Var, "$callback");
                p50 p50Var2 = p50Var;
                int i = u00.L;
                t40.m(sQLiteDatabase, "dbObj");
                q00 q00VarJ = qm.J(p50Var2, sQLiteDatabase);
                Log.e("SupportSQLite", "Corruption reported by sqlite on database: " + q00VarJ + ".path");
                SQLiteDatabase sQLiteDatabase2 = q00VarJ.E;
                if (!sQLiteDatabase2.isOpen()) {
                    String path = sQLiteDatabase2.getPath();
                    if (path != null) {
                        l0.e(path);
                        return;
                    }
                    return;
                }
                List list = null;
                try {
                    try {
                        list = q00VarJ.F;
                    } catch (SQLiteException unused) {
                    }
                    try {
                        q00VarJ.close();
                    } catch (IOException unused2) {
                    }
                    if (list != null) {
                        return;
                    }
                } finally {
                    if (list != null) {
                        Iterator it = list.iterator();
                        while (it.hasNext()) {
                            Object obj = ((Pair) it.next()).second;
                            t40.m(obj, "p.second");
                            l0.e((String) obj);
                        }
                    } else {
                        String path2 = sQLiteDatabase2.getPath();
                        if (path2 != null) {
                            l0.e(path2);
                        }
                    }
                }
            }
        });
        t40.n(l0Var, "callback");
        this.E = context;
        this.F = p50Var;
        this.G = l0Var;
        this.H = z;
        if (str == null) {
            string = UUID.randomUUID().toString();
            t40.m(string, "randomUUID().toString()");
        } else {
            string = str;
        }
        File cacheDir = context.getCacheDir();
        t40.m(cacheDir, "context.cacheDir");
        this.J = new tl0(cacheDir, string);
    }

    @Override // android.database.sqlite.SQLiteOpenHelper, java.lang.AutoCloseable
    public final void close() {
        tl0 tl0Var = this.J;
        try {
            HashMap map = tl0.d;
            tl0Var.getClass();
            tl0Var.a(false);
            super.close();
            this.F.F = null;
            this.K = false;
        } finally {
            tl0Var.b();
        }
    }

    public final ey0 j(boolean z) {
        tl0 tl0Var = this.J;
        try {
            tl0Var.a((this.K || getDatabaseName() == null) ? false : true);
            this.I = false;
            SQLiteDatabase sQLiteDatabaseW = w(z);
            if (!this.I) {
                q00 q00VarJ = qm.J(this.F, sQLiteDatabaseW);
                tl0Var.b();
                return q00VarJ;
            }
            close();
            ey0 ey0VarJ = j(z);
            tl0Var.b();
            return ey0VarJ;
        } catch (Throwable th) {
            tl0Var.b();
            throw th;
        }
    }

    public final SQLiteDatabase l(boolean z) {
        if (z) {
            SQLiteDatabase writableDatabase = getWritableDatabase();
            t40.m(writableDatabase, "{\n                super.…eDatabase()\n            }");
            return writableDatabase;
        }
        SQLiteDatabase readableDatabase = getReadableDatabase();
        t40.m(readableDatabase, "{\n                super.…eDatabase()\n            }");
        return readableDatabase;
    }

    @Override // android.database.sqlite.SQLiteOpenHelper
    public final void onConfigure(SQLiteDatabase sQLiteDatabase) {
        t40.n(sQLiteDatabase, "db");
        try {
            l0 l0Var = this.G;
            qm.J(this.F, sQLiteDatabase);
            l0Var.getClass();
        } catch (Throwable th) {
            throw new s00(FrameworkSQLiteOpenHelper$OpenHelper$CallbackName.ON_CONFIGURE, th);
        }
    }

    @Override // android.database.sqlite.SQLiteOpenHelper
    public final void onCreate(SQLiteDatabase sQLiteDatabase) {
        t40.n(sQLiteDatabase, "sqLiteDatabase");
        try {
            this.G.k(qm.J(this.F, sQLiteDatabase));
        } catch (Throwable th) {
            throw new s00(FrameworkSQLiteOpenHelper$OpenHelper$CallbackName.ON_CREATE, th);
        }
    }

    @Override // android.database.sqlite.SQLiteOpenHelper
    public final void onDowngrade(SQLiteDatabase sQLiteDatabase, int i, int i2) {
        t40.n(sQLiteDatabase, "db");
        this.I = true;
        try {
            l0 l0Var = this.G;
            q00 q00VarJ = qm.J(this.F, sQLiteDatabase);
            l0Var.getClass();
            l0Var.m(q00VarJ, i, i2);
        } catch (Throwable th) {
            throw new s00(FrameworkSQLiteOpenHelper$OpenHelper$CallbackName.ON_DOWNGRADE, th);
        }
    }

    @Override // android.database.sqlite.SQLiteOpenHelper
    public final void onOpen(SQLiteDatabase sQLiteDatabase) {
        t40.n(sQLiteDatabase, "db");
        if (!this.I) {
            try {
                this.G.l(qm.J(this.F, sQLiteDatabase));
            } catch (Throwable th) {
                throw new s00(FrameworkSQLiteOpenHelper$OpenHelper$CallbackName.ON_OPEN, th);
            }
        }
        this.K = true;
    }

    @Override // android.database.sqlite.SQLiteOpenHelper
    public final void onUpgrade(SQLiteDatabase sQLiteDatabase, int i, int i2) {
        t40.n(sQLiteDatabase, "sqLiteDatabase");
        this.I = true;
        try {
            this.G.m(qm.J(this.F, sQLiteDatabase), i, i2);
        } catch (Throwable th) {
            throw new s00(FrameworkSQLiteOpenHelper$OpenHelper$CallbackName.ON_UPGRADE, th);
        }
    }

    public final SQLiteDatabase w(boolean z) throws Throwable {
        File parentFile;
        String databaseName = getDatabaseName();
        Context context = this.E;
        if (databaseName != null && (parentFile = context.getDatabasePath(databaseName).getParentFile()) != null) {
            parentFile.mkdirs();
            if (!parentFile.isDirectory()) {
                Log.w("SupportSQLite", "Invalid database parent file, not a directory: " + parentFile);
            }
        }
        try {
            return l(z);
        } catch (Throwable unused) {
            super.close();
            try {
                Thread.sleep(500L);
            } catch (InterruptedException unused2) {
            }
            try {
                return l(z);
            } catch (Throwable th) {
                super.close();
                if (th instanceof s00) {
                    s00 s00Var = th;
                    int i = t00.a[s00Var.E.ordinal()];
                    Throwable th2 = s00Var.F;
                    if (i == 1 || i == 2 || i == 3 || i == 4 || !(th2 instanceof SQLiteException)) {
                        throw th2;
                    }
                } else if (!(th instanceof SQLiteException) || databaseName == null || !this.H) {
                    throw th;
                }
                context.deleteDatabase(databaseName);
                try {
                    return l(z);
                } catch (s00 e) {
                    throw e.F;
                }
            }
        }
    }
}
