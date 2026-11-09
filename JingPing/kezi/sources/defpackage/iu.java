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
public final class iu extends SQLiteOpenHelper {
    public static final /* synthetic */ int i = 0;
    public final Context b;
    public final o70 c;
    public final d5 d;
    public final boolean e;
    public boolean f;
    public final fc0 g;
    public boolean h;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public iu(Context context, String str, final o70 o70Var, final d5 d5Var, boolean z) {
        String string;
        super(context, str, null, d5Var.b, new DatabaseErrorHandler() { // from class: fu
            @Override // android.database.DatabaseErrorHandler
            public final void onCorruption(SQLiteDatabase sQLiteDatabase) {
                oy.j(d5Var, "$callback");
                o70 o70Var2 = o70Var;
                int i2 = iu.i;
                oy.i(sQLiteDatabase, "dbObj");
                eu euVarT = k9.t(o70Var2, sQLiteDatabase);
                Log.e("SupportSQLite", "Corruption reported by sqlite on database: " + euVarT + ".path");
                SQLiteDatabase sQLiteDatabase2 = euVarT.b;
                if (!sQLiteDatabase2.isOpen()) {
                    String path = sQLiteDatabase2.getPath();
                    if (path != null) {
                        d5.d(path);
                        return;
                    }
                    return;
                }
                List list = null;
                try {
                    try {
                        list = euVarT.c;
                    } finally {
                        if (list != null) {
                            Iterator it = list.iterator();
                            while (it.hasNext()) {
                                Object obj = ((Pair) it.next()).second;
                                oy.i(obj, "p.second");
                                d5.d((String) obj);
                            }
                        } else {
                            String path2 = sQLiteDatabase2.getPath();
                            if (path2 != null) {
                                d5.d(path2);
                            }
                        }
                    }
                } catch (SQLiteException unused) {
                }
                try {
                    euVarT.close();
                } catch (IOException unused2) {
                }
                if (list != null) {
                    return;
                }
            }
        });
        oy.j(d5Var, "callback");
        this.b = context;
        this.c = o70Var;
        this.d = d5Var;
        this.e = z;
        if (str == null) {
            string = UUID.randomUUID().toString();
            oy.i(string, "randomUUID().toString()");
        } else {
            string = str;
        }
        File cacheDir = context.getCacheDir();
        oy.i(cacheDir, "context.cacheDir");
        this.g = new fc0(string, cacheDir);
    }

    public final SQLiteDatabase C(boolean z) throws Throwable {
        File parentFile;
        String databaseName = getDatabaseName();
        Context context = this.b;
        if (databaseName != null && (parentFile = context.getDatabasePath(databaseName).getParentFile()) != null) {
            parentFile.mkdirs();
            if (!parentFile.isDirectory()) {
                Log.w("SupportSQLite", "Invalid database parent file, not a directory: " + parentFile);
            }
        }
        try {
            return t(z);
        } catch (Throwable unused) {
            super.close();
            try {
                Thread.sleep(500L);
            } catch (InterruptedException unused2) {
            }
            try {
                return t(z);
            } catch (Throwable th) {
                super.close();
                if (th instanceof gu) {
                    gu guVar = th;
                    int i2 = hu.a[guVar.b.ordinal()];
                    Throwable th2 = guVar.c;
                    if (i2 == 1 || i2 == 2 || i2 == 3 || i2 == 4 || !(th2 instanceof SQLiteException)) {
                        throw th2;
                    }
                } else if (!(th instanceof SQLiteException) || databaseName == null || !this.e) {
                    throw th;
                }
                context.deleteDatabase(databaseName);
                try {
                    return t(z);
                } catch (gu e) {
                    throw e.c;
                }
            }
        }
    }

    public final ql0 a(boolean z) {
        fc0 fc0Var = this.g;
        try {
            fc0Var.a((this.h || getDatabaseName() == null) ? false : true);
            this.f = false;
            SQLiteDatabase sQLiteDatabaseC = C(z);
            if (!this.f) {
                eu euVarT = k9.t(this.c, sQLiteDatabaseC);
                fc0Var.b();
                return euVarT;
            }
            close();
            ql0 ql0VarA = a(z);
            fc0Var.b();
            return ql0VarA;
        } catch (Throwable th) {
            fc0Var.b();
            throw th;
        }
    }

    @Override // android.database.sqlite.SQLiteOpenHelper, java.lang.AutoCloseable
    public final void close() {
        fc0 fc0Var = this.g;
        try {
            HashMap map = fc0.d;
            fc0Var.getClass();
            fc0Var.a(false);
            super.close();
            this.c.c = null;
            this.h = false;
        } finally {
            fc0Var.b();
        }
    }

    @Override // android.database.sqlite.SQLiteOpenHelper
    public final void onConfigure(SQLiteDatabase sQLiteDatabase) {
        oy.j(sQLiteDatabase, "db");
        try {
            d5 d5Var = this.d;
            k9.t(this.c, sQLiteDatabase);
            d5Var.getClass();
        } catch (Throwable th) {
            throw new gu(FrameworkSQLiteOpenHelper$OpenHelper$CallbackName.ON_CONFIGURE, th);
        }
    }

    @Override // android.database.sqlite.SQLiteOpenHelper
    public final void onCreate(SQLiteDatabase sQLiteDatabase) {
        oy.j(sQLiteDatabase, "sqLiteDatabase");
        try {
            this.d.g(k9.t(this.c, sQLiteDatabase));
        } catch (Throwable th) {
            throw new gu(FrameworkSQLiteOpenHelper$OpenHelper$CallbackName.ON_CREATE, th);
        }
    }

    @Override // android.database.sqlite.SQLiteOpenHelper
    public final void onDowngrade(SQLiteDatabase sQLiteDatabase, int i2, int i3) {
        oy.j(sQLiteDatabase, "db");
        this.f = true;
        try {
            d5 d5Var = this.d;
            eu euVarT = k9.t(this.c, sQLiteDatabase);
            d5Var.getClass();
            d5Var.i(euVarT, i2, i3);
        } catch (Throwable th) {
            throw new gu(FrameworkSQLiteOpenHelper$OpenHelper$CallbackName.ON_DOWNGRADE, th);
        }
    }

    @Override // android.database.sqlite.SQLiteOpenHelper
    public final void onOpen(SQLiteDatabase sQLiteDatabase) {
        oy.j(sQLiteDatabase, "db");
        if (!this.f) {
            try {
                this.d.h(k9.t(this.c, sQLiteDatabase));
            } catch (Throwable th) {
                throw new gu(FrameworkSQLiteOpenHelper$OpenHelper$CallbackName.ON_OPEN, th);
            }
        }
        this.h = true;
    }

    @Override // android.database.sqlite.SQLiteOpenHelper
    public final void onUpgrade(SQLiteDatabase sQLiteDatabase, int i2, int i3) {
        oy.j(sQLiteDatabase, "sqLiteDatabase");
        this.f = true;
        try {
            this.d.i(k9.t(this.c, sQLiteDatabase), i2, i3);
        } catch (Throwable th) {
            throw new gu(FrameworkSQLiteOpenHelper$OpenHelper$CallbackName.ON_UPGRADE, th);
        }
    }

    public final SQLiteDatabase t(boolean z) {
        if (z) {
            SQLiteDatabase writableDatabase = getWritableDatabase();
            oy.i(writableDatabase, "{\n                super.…eDatabase()\n            }");
            return writableDatabase;
        }
        SQLiteDatabase readableDatabase = getReadableDatabase();
        oy.i(readableDatabase, "{\n                super.…eDatabase()\n            }");
        return readableDatabase;
    }
}
