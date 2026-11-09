package androidx.work.impl;

import android.content.Context;
import androidx.work.Logger;
import defpackage.o40;
import defpackage.oy;
import java.io.File;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;
import kotlin.Pair;

/* JADX INFO: loaded from: classes.dex */
public final class WorkDatabasePathHelper {
    public static final WorkDatabasePathHelper INSTANCE = new WorkDatabasePathHelper();

    private WorkDatabasePathHelper() {
    }

    private final File getNoBackupPath(Context context) {
        return new File(Api21Impl.INSTANCE.getNoBackupFilesDir(context), WorkDatabasePathHelperKt.WORK_DATABASE_NAME);
    }

    public static final void migrateDatabase(Context context) {
        oy.j(context, "context");
        WorkDatabasePathHelper workDatabasePathHelper = INSTANCE;
        if (workDatabasePathHelper.getDefaultDatabasePath(context).exists()) {
            Logger.get().debug(WorkDatabasePathHelperKt.TAG, "Migrating WorkDatabase to the no-backup directory");
            for (Map.Entry<File, File> entry : workDatabasePathHelper.migrationPaths(context).entrySet()) {
                File key = entry.getKey();
                File value = entry.getValue();
                if (key.exists()) {
                    if (value.exists()) {
                        Logger.get().warning(WorkDatabasePathHelperKt.TAG, "Over-writing contents of " + value);
                    }
                    Logger.get().debug(WorkDatabasePathHelperKt.TAG, key.renameTo(value) ? "Migrated " + key + "to " + value : "Renaming " + key + " to " + value + " failed");
                }
            }
        }
    }

    public final File getDatabasePath(Context context) {
        oy.j(context, "context");
        return getNoBackupPath(context);
    }

    public final File getDefaultDatabasePath(Context context) {
        oy.j(context, "context");
        File databasePath = context.getDatabasePath(WorkDatabasePathHelperKt.WORK_DATABASE_NAME);
        oy.i(databasePath, "context.getDatabasePath(WORK_DATABASE_NAME)");
        return databasePath;
    }

    public final Map<File, File> migrationPaths(Context context) {
        oy.j(context, "context");
        File defaultDatabasePath = getDefaultDatabasePath(context);
        File databasePath = getDatabasePath(context);
        String[] strArr = WorkDatabasePathHelperKt.DATABASE_EXTRA_FILES;
        int iU0 = o40.u0(strArr.length);
        if (iU0 < 16) {
            iU0 = 16;
        }
        LinkedHashMap linkedHashMap = new LinkedHashMap(iU0);
        for (String str : strArr) {
            Pair pair = new Pair(new File(defaultDatabasePath.getPath() + str), new File(databasePath.getPath() + str));
            linkedHashMap.put(pair.getFirst(), pair.getSecond());
        }
        Pair pair2 = new Pair(defaultDatabasePath, databasePath);
        if (linkedHashMap.isEmpty()) {
            Map<File, File> mapSingletonMap = Collections.singletonMap(pair2.getFirst(), pair2.getSecond());
            oy.i(mapSingletonMap, "singletonMap(...)");
            return mapSingletonMap;
        }
        LinkedHashMap linkedHashMap2 = new LinkedHashMap(linkedHashMap);
        linkedHashMap2.put(pair2.getFirst(), pair2.getSecond());
        return linkedHashMap2;
    }
}
