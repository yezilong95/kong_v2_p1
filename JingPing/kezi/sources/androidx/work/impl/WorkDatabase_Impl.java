package androidx.work.impl;

import android.database.Cursor;
import androidx.work.impl.model.DependencyDao;
import androidx.work.impl.model.DependencyDao_Impl;
import androidx.work.impl.model.PreferenceDao;
import androidx.work.impl.model.PreferenceDao_Impl;
import androidx.work.impl.model.RawWorkInfoDao;
import androidx.work.impl.model.RawWorkInfoDao_Impl;
import androidx.work.impl.model.SystemIdInfoDao;
import androidx.work.impl.model.SystemIdInfoDao_Impl;
import androidx.work.impl.model.WorkNameDao;
import androidx.work.impl.model.WorkNameDao_Impl;
import androidx.work.impl.model.WorkProgressDao;
import androidx.work.impl.model.WorkProgressDao_Impl;
import androidx.work.impl.model.WorkSpecDao;
import androidx.work.impl.model.WorkSpecDao_Impl;
import androidx.work.impl.model.WorkTagDao;
import androidx.work.impl.model.WorkTagDao_Impl;
import androidx.work.impl.utils.PreferenceUtils;
import defpackage.d5;
import defpackage.dl0;
import defpackage.g70;
import defpackage.ku;
import defpackage.li;
import defpackage.nf0;
import defpackage.o7;
import defpackage.oy;
import defpackage.pf0;
import defpackage.qf0;
import defpackage.ql0;
import defpackage.qm0;
import defpackage.rf0;
import defpackage.rl0;
import defpackage.rm0;
import defpackage.tl0;
import defpackage.tm0;
import defpackage.ty;
import defpackage.um0;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import kotlin.collections.builders.ListBuilder;

/* JADX INFO: loaded from: classes.dex */
public final class WorkDatabase_Impl extends WorkDatabase {
    private volatile DependencyDao _dependencyDao;
    private volatile PreferenceDao _preferenceDao;
    private volatile RawWorkInfoDao _rawWorkInfoDao;
    private volatile SystemIdInfoDao _systemIdInfoDao;
    private volatile WorkNameDao _workNameDao;
    private volatile WorkProgressDao _workProgressDao;
    private volatile WorkSpecDao _workSpecDao;
    private volatile WorkTagDao _workTagDao;

    @Override // defpackage.pf0
    public void clearAllTables() {
        super.assertNotMainThread();
        ql0 ql0VarA = ((ku) super.getOpenHelper()).a();
        try {
            super.beginTransaction();
            ql0VarA.i("PRAGMA defer_foreign_keys = TRUE");
            ql0VarA.i("DELETE FROM `Dependency`");
            ql0VarA.i("DELETE FROM `WorkSpec`");
            ql0VarA.i("DELETE FROM `WorkTag`");
            ql0VarA.i("DELETE FROM `SystemIdInfo`");
            ql0VarA.i("DELETE FROM `WorkName`");
            ql0VarA.i("DELETE FROM `WorkProgress`");
            ql0VarA.i("DELETE FROM `Preference`");
            super.setTransactionSuccessful();
        } finally {
            super.endTransaction();
            ql0VarA.y("PRAGMA wal_checkpoint(FULL)").close();
            if (!ql0VarA.z()) {
                ql0VarA.i("VACUUM");
            }
        }
    }

    @Override // defpackage.pf0
    public ty createInvalidationTracker() {
        return new ty(this, new HashMap(0), new HashMap(0), "Dependency", "WorkSpec", "WorkTag", "SystemIdInfo", "WorkName", "WorkProgress", "Preference");
    }

    @Override // defpackage.pf0
    public tl0 createOpenHelper(li liVar) {
        return liVar.c.a(new rl0(liVar.a, liVar.b, new d5(liVar, new qf0(16) { // from class: androidx.work.impl.WorkDatabase_Impl.1
            @Override // defpackage.qf0
            public void createAllTables(ql0 ql0Var) {
                ql0Var.i("CREATE TABLE IF NOT EXISTS `Dependency` (`work_spec_id` TEXT NOT NULL, `prerequisite_id` TEXT NOT NULL, PRIMARY KEY(`work_spec_id`, `prerequisite_id`), FOREIGN KEY(`work_spec_id`) REFERENCES `WorkSpec`(`id`) ON UPDATE CASCADE ON DELETE CASCADE , FOREIGN KEY(`prerequisite_id`) REFERENCES `WorkSpec`(`id`) ON UPDATE CASCADE ON DELETE CASCADE )");
                ql0Var.i("CREATE INDEX IF NOT EXISTS `index_Dependency_work_spec_id` ON `Dependency` (`work_spec_id`)");
                ql0Var.i("CREATE INDEX IF NOT EXISTS `index_Dependency_prerequisite_id` ON `Dependency` (`prerequisite_id`)");
                ql0Var.i("CREATE TABLE IF NOT EXISTS `WorkSpec` (`id` TEXT NOT NULL, `state` INTEGER NOT NULL, `worker_class_name` TEXT NOT NULL, `input_merger_class_name` TEXT, `input` BLOB NOT NULL, `output` BLOB NOT NULL, `initial_delay` INTEGER NOT NULL, `interval_duration` INTEGER NOT NULL, `flex_duration` INTEGER NOT NULL, `run_attempt_count` INTEGER NOT NULL, `backoff_policy` INTEGER NOT NULL, `backoff_delay_duration` INTEGER NOT NULL, `last_enqueue_time` INTEGER NOT NULL, `minimum_retention_duration` INTEGER NOT NULL, `schedule_requested_at` INTEGER NOT NULL, `run_in_foreground` INTEGER NOT NULL, `out_of_quota_policy` INTEGER NOT NULL, `period_count` INTEGER NOT NULL DEFAULT 0, `generation` INTEGER NOT NULL DEFAULT 0, `required_network_type` INTEGER NOT NULL, `requires_charging` INTEGER NOT NULL, `requires_device_idle` INTEGER NOT NULL, `requires_battery_not_low` INTEGER NOT NULL, `requires_storage_not_low` INTEGER NOT NULL, `trigger_content_update_delay` INTEGER NOT NULL, `trigger_max_content_delay` INTEGER NOT NULL, `content_uri_triggers` BLOB NOT NULL, PRIMARY KEY(`id`))");
                ql0Var.i("CREATE INDEX IF NOT EXISTS `index_WorkSpec_schedule_requested_at` ON `WorkSpec` (`schedule_requested_at`)");
                ql0Var.i("CREATE INDEX IF NOT EXISTS `index_WorkSpec_last_enqueue_time` ON `WorkSpec` (`last_enqueue_time`)");
                ql0Var.i("CREATE TABLE IF NOT EXISTS `WorkTag` (`tag` TEXT NOT NULL, `work_spec_id` TEXT NOT NULL, PRIMARY KEY(`tag`, `work_spec_id`), FOREIGN KEY(`work_spec_id`) REFERENCES `WorkSpec`(`id`) ON UPDATE CASCADE ON DELETE CASCADE )");
                ql0Var.i("CREATE INDEX IF NOT EXISTS `index_WorkTag_work_spec_id` ON `WorkTag` (`work_spec_id`)");
                ql0Var.i("CREATE TABLE IF NOT EXISTS `SystemIdInfo` (`work_spec_id` TEXT NOT NULL, `generation` INTEGER NOT NULL DEFAULT 0, `system_id` INTEGER NOT NULL, PRIMARY KEY(`work_spec_id`, `generation`), FOREIGN KEY(`work_spec_id`) REFERENCES `WorkSpec`(`id`) ON UPDATE CASCADE ON DELETE CASCADE )");
                ql0Var.i("CREATE TABLE IF NOT EXISTS `WorkName` (`name` TEXT NOT NULL, `work_spec_id` TEXT NOT NULL, PRIMARY KEY(`name`, `work_spec_id`), FOREIGN KEY(`work_spec_id`) REFERENCES `WorkSpec`(`id`) ON UPDATE CASCADE ON DELETE CASCADE )");
                ql0Var.i("CREATE INDEX IF NOT EXISTS `index_WorkName_work_spec_id` ON `WorkName` (`work_spec_id`)");
                ql0Var.i("CREATE TABLE IF NOT EXISTS `WorkProgress` (`work_spec_id` TEXT NOT NULL, `progress` BLOB NOT NULL, PRIMARY KEY(`work_spec_id`), FOREIGN KEY(`work_spec_id`) REFERENCES `WorkSpec`(`id`) ON UPDATE CASCADE ON DELETE CASCADE )");
                ql0Var.i(PreferenceUtils.CREATE_PREFERENCE);
                ql0Var.i("CREATE TABLE IF NOT EXISTS room_master_table (id INTEGER PRIMARY KEY,identity_hash TEXT)");
                ql0Var.i("INSERT OR REPLACE INTO room_master_table (id,identity_hash) VALUES(42, '5181942b9ebc31ce68dacb56c16fd79f')");
            }

            @Override // defpackage.qf0
            public void dropAllTables(ql0 ql0Var) {
                ql0Var.i("DROP TABLE IF EXISTS `Dependency`");
                ql0Var.i("DROP TABLE IF EXISTS `WorkSpec`");
                ql0Var.i("DROP TABLE IF EXISTS `WorkTag`");
                ql0Var.i("DROP TABLE IF EXISTS `SystemIdInfo`");
                ql0Var.i("DROP TABLE IF EXISTS `WorkName`");
                ql0Var.i("DROP TABLE IF EXISTS `WorkProgress`");
                ql0Var.i("DROP TABLE IF EXISTS `Preference`");
                if (((pf0) WorkDatabase_Impl.this).mCallbacks != null) {
                    int size = ((pf0) WorkDatabase_Impl.this).mCallbacks.size();
                    for (int i = 0; i < size; i++) {
                        ((nf0) ((pf0) WorkDatabase_Impl.this).mCallbacks.get(i)).onDestructiveMigration(ql0Var);
                    }
                }
            }

            @Override // defpackage.qf0
            public void onCreate(ql0 ql0Var) {
                if (((pf0) WorkDatabase_Impl.this).mCallbacks != null) {
                    int size = ((pf0) WorkDatabase_Impl.this).mCallbacks.size();
                    for (int i = 0; i < size; i++) {
                        ((nf0) ((pf0) WorkDatabase_Impl.this).mCallbacks.get(i)).onCreate(ql0Var);
                    }
                }
            }

            @Override // defpackage.qf0
            public void onOpen(ql0 ql0Var) {
                ((pf0) WorkDatabase_Impl.this).mDatabase = ql0Var;
                ql0Var.i("PRAGMA foreign_keys = ON");
                WorkDatabase_Impl.this.internalInitInvalidationTracker(ql0Var);
                if (((pf0) WorkDatabase_Impl.this).mCallbacks != null) {
                    int size = ((pf0) WorkDatabase_Impl.this).mCallbacks.size();
                    for (int i = 0; i < size; i++) {
                        ((nf0) ((pf0) WorkDatabase_Impl.this).mCallbacks.get(i)).onOpen(ql0Var);
                    }
                }
            }

            @Override // defpackage.qf0
            public void onPreMigrate(ql0 ql0Var) throws IllegalAccessException, IOException, InvocationTargetException {
                oy.j(ql0Var, "db");
                ListBuilder listBuilder = new ListBuilder(0, 1, null);
                Cursor cursorY = ql0Var.y("SELECT name FROM sqlite_master WHERE type = 'trigger'");
                while (cursorY.moveToNext()) {
                    try {
                        listBuilder.add(cursorY.getString(0));
                    } finally {
                    }
                }
                cursorY.close();
                for (String str : listBuilder.build()) {
                    oy.i(str, "triggerName");
                    if (dl0.j0(str, "room_fts_content_sync_", false)) {
                        ql0Var.i("DROP TRIGGER IF EXISTS ".concat(str));
                    }
                }
            }

            @Override // defpackage.qf0
            public rf0 onValidateSchema(ql0 ql0Var) {
                HashMap map = new HashMap(2);
                map.put("work_spec_id", new qm0("work_spec_id", "TEXT", true, 1, null, 1));
                map.put("prerequisite_id", new qm0("prerequisite_id", "TEXT", true, 2, null, 1));
                HashSet hashSet = new HashSet(2);
                hashSet.add(new rm0("WorkSpec", "CASCADE", "CASCADE", Arrays.asList("work_spec_id"), Arrays.asList("id")));
                hashSet.add(new rm0("WorkSpec", "CASCADE", "CASCADE", Arrays.asList("prerequisite_id"), Arrays.asList("id")));
                HashSet hashSet2 = new HashSet(2);
                hashSet2.add(new tm0("index_Dependency_work_spec_id", false, Arrays.asList("work_spec_id"), Arrays.asList("ASC")));
                hashSet2.add(new tm0("index_Dependency_prerequisite_id", false, Arrays.asList("prerequisite_id"), Arrays.asList("ASC")));
                um0 um0Var = new um0("Dependency", map, hashSet, hashSet2);
                um0 um0VarA = um0.a(ql0Var, "Dependency");
                if (!um0Var.equals(um0VarA)) {
                    return new rf0("Dependency(androidx.work.impl.model.Dependency).\n Expected:\n" + um0Var + "\n Found:\n" + um0VarA, false);
                }
                HashMap map2 = new HashMap(27);
                map2.put("id", new qm0("id", "TEXT", true, 1, null, 1));
                map2.put("state", new qm0("state", "INTEGER", true, 0, null, 1));
                map2.put("worker_class_name", new qm0("worker_class_name", "TEXT", true, 0, null, 1));
                map2.put("input_merger_class_name", new qm0("input_merger_class_name", "TEXT", false, 0, null, 1));
                map2.put("input", new qm0("input", "BLOB", true, 0, null, 1));
                map2.put("output", new qm0("output", "BLOB", true, 0, null, 1));
                map2.put("initial_delay", new qm0("initial_delay", "INTEGER", true, 0, null, 1));
                map2.put("interval_duration", new qm0("interval_duration", "INTEGER", true, 0, null, 1));
                map2.put("flex_duration", new qm0("flex_duration", "INTEGER", true, 0, null, 1));
                map2.put("run_attempt_count", new qm0("run_attempt_count", "INTEGER", true, 0, null, 1));
                map2.put("backoff_policy", new qm0("backoff_policy", "INTEGER", true, 0, null, 1));
                map2.put("backoff_delay_duration", new qm0("backoff_delay_duration", "INTEGER", true, 0, null, 1));
                map2.put("last_enqueue_time", new qm0("last_enqueue_time", "INTEGER", true, 0, null, 1));
                map2.put("minimum_retention_duration", new qm0("minimum_retention_duration", "INTEGER", true, 0, null, 1));
                map2.put("schedule_requested_at", new qm0("schedule_requested_at", "INTEGER", true, 0, null, 1));
                map2.put("run_in_foreground", new qm0("run_in_foreground", "INTEGER", true, 0, null, 1));
                map2.put("out_of_quota_policy", new qm0("out_of_quota_policy", "INTEGER", true, 0, null, 1));
                map2.put("period_count", new qm0("period_count", "INTEGER", true, 0, "0", 1));
                map2.put("generation", new qm0("generation", "INTEGER", true, 0, "0", 1));
                map2.put("required_network_type", new qm0("required_network_type", "INTEGER", true, 0, null, 1));
                map2.put("requires_charging", new qm0("requires_charging", "INTEGER", true, 0, null, 1));
                map2.put("requires_device_idle", new qm0("requires_device_idle", "INTEGER", true, 0, null, 1));
                map2.put("requires_battery_not_low", new qm0("requires_battery_not_low", "INTEGER", true, 0, null, 1));
                map2.put("requires_storage_not_low", new qm0("requires_storage_not_low", "INTEGER", true, 0, null, 1));
                map2.put("trigger_content_update_delay", new qm0("trigger_content_update_delay", "INTEGER", true, 0, null, 1));
                map2.put("trigger_max_content_delay", new qm0("trigger_max_content_delay", "INTEGER", true, 0, null, 1));
                map2.put("content_uri_triggers", new qm0("content_uri_triggers", "BLOB", true, 0, null, 1));
                HashSet hashSet3 = new HashSet(0);
                HashSet hashSet4 = new HashSet(2);
                hashSet4.add(new tm0("index_WorkSpec_schedule_requested_at", false, Arrays.asList("schedule_requested_at"), Arrays.asList("ASC")));
                hashSet4.add(new tm0("index_WorkSpec_last_enqueue_time", false, Arrays.asList("last_enqueue_time"), Arrays.asList("ASC")));
                um0 um0Var2 = new um0("WorkSpec", map2, hashSet3, hashSet4);
                um0 um0VarA2 = um0.a(ql0Var, "WorkSpec");
                if (!um0Var2.equals(um0VarA2)) {
                    return new rf0("WorkSpec(androidx.work.impl.model.WorkSpec).\n Expected:\n" + um0Var2 + "\n Found:\n" + um0VarA2, false);
                }
                HashMap map3 = new HashMap(2);
                map3.put("tag", new qm0("tag", "TEXT", true, 1, null, 1));
                map3.put("work_spec_id", new qm0("work_spec_id", "TEXT", true, 2, null, 1));
                HashSet hashSet5 = new HashSet(1);
                hashSet5.add(new rm0("WorkSpec", "CASCADE", "CASCADE", Arrays.asList("work_spec_id"), Arrays.asList("id")));
                HashSet hashSet6 = new HashSet(1);
                hashSet6.add(new tm0("index_WorkTag_work_spec_id", false, Arrays.asList("work_spec_id"), Arrays.asList("ASC")));
                um0 um0Var3 = new um0("WorkTag", map3, hashSet5, hashSet6);
                um0 um0VarA3 = um0.a(ql0Var, "WorkTag");
                if (!um0Var3.equals(um0VarA3)) {
                    return new rf0("WorkTag(androidx.work.impl.model.WorkTag).\n Expected:\n" + um0Var3 + "\n Found:\n" + um0VarA3, false);
                }
                HashMap map4 = new HashMap(3);
                map4.put("work_spec_id", new qm0("work_spec_id", "TEXT", true, 1, null, 1));
                map4.put("generation", new qm0("generation", "INTEGER", true, 2, "0", 1));
                map4.put("system_id", new qm0("system_id", "INTEGER", true, 0, null, 1));
                HashSet hashSet7 = new HashSet(1);
                hashSet7.add(new rm0("WorkSpec", "CASCADE", "CASCADE", Arrays.asList("work_spec_id"), Arrays.asList("id")));
                um0 um0Var4 = new um0("SystemIdInfo", map4, hashSet7, new HashSet(0));
                um0 um0VarA4 = um0.a(ql0Var, "SystemIdInfo");
                if (!um0Var4.equals(um0VarA4)) {
                    return new rf0("SystemIdInfo(androidx.work.impl.model.SystemIdInfo).\n Expected:\n" + um0Var4 + "\n Found:\n" + um0VarA4, false);
                }
                HashMap map5 = new HashMap(2);
                map5.put("name", new qm0("name", "TEXT", true, 1, null, 1));
                map5.put("work_spec_id", new qm0("work_spec_id", "TEXT", true, 2, null, 1));
                HashSet hashSet8 = new HashSet(1);
                hashSet8.add(new rm0("WorkSpec", "CASCADE", "CASCADE", Arrays.asList("work_spec_id"), Arrays.asList("id")));
                HashSet hashSet9 = new HashSet(1);
                hashSet9.add(new tm0("index_WorkName_work_spec_id", false, Arrays.asList("work_spec_id"), Arrays.asList("ASC")));
                um0 um0Var5 = new um0("WorkName", map5, hashSet8, hashSet9);
                um0 um0VarA5 = um0.a(ql0Var, "WorkName");
                if (!um0Var5.equals(um0VarA5)) {
                    return new rf0("WorkName(androidx.work.impl.model.WorkName).\n Expected:\n" + um0Var5 + "\n Found:\n" + um0VarA5, false);
                }
                HashMap map6 = new HashMap(2);
                map6.put("work_spec_id", new qm0("work_spec_id", "TEXT", true, 1, null, 1));
                map6.put("progress", new qm0("progress", "BLOB", true, 0, null, 1));
                HashSet hashSet10 = new HashSet(1);
                hashSet10.add(new rm0("WorkSpec", "CASCADE", "CASCADE", Arrays.asList("work_spec_id"), Arrays.asList("id")));
                um0 um0Var6 = new um0("WorkProgress", map6, hashSet10, new HashSet(0));
                um0 um0VarA6 = um0.a(ql0Var, "WorkProgress");
                if (!um0Var6.equals(um0VarA6)) {
                    return new rf0("WorkProgress(androidx.work.impl.model.WorkProgress).\n Expected:\n" + um0Var6 + "\n Found:\n" + um0VarA6, false);
                }
                HashMap map7 = new HashMap(2);
                map7.put("key", new qm0("key", "TEXT", true, 1, null, 1));
                map7.put("long_value", new qm0("long_value", "INTEGER", false, 0, null, 1));
                um0 um0Var7 = new um0("Preference", map7, new HashSet(0), new HashSet(0));
                um0 um0VarA7 = um0.a(ql0Var, "Preference");
                if (um0Var7.equals(um0VarA7)) {
                    return new rf0(null, true);
                }
                return new rf0("Preference(androidx.work.impl.model.Preference).\n Expected:\n" + um0Var7 + "\n Found:\n" + um0VarA7, false);
            }

            @Override // defpackage.qf0
            public void onPostMigrate(ql0 ql0Var) {
            }
        }), false, false));
    }

    @Override // androidx.work.impl.WorkDatabase
    public DependencyDao dependencyDao() {
        DependencyDao dependencyDao;
        if (this._dependencyDao != null) {
            return this._dependencyDao;
        }
        synchronized (this) {
            try {
                if (this._dependencyDao == null) {
                    this._dependencyDao = new DependencyDao_Impl(this);
                }
                dependencyDao = this._dependencyDao;
            } catch (Throwable th) {
                throw th;
            }
        }
        return dependencyDao;
    }

    @Override // defpackage.pf0
    public List<g70> getAutoMigrations(Map<Class<? extends o7>, o7> map) {
        return Arrays.asList(new WorkDatabase_AutoMigration_13_14_Impl(), new WorkDatabase_AutoMigration_14_15_Impl());
    }

    @Override // defpackage.pf0
    public Set<Class<? extends o7>> getRequiredAutoMigrationSpecs() {
        return new HashSet();
    }

    @Override // defpackage.pf0
    public Map<Class<?>, List<Class<?>>> getRequiredTypeConverters() {
        HashMap map = new HashMap();
        map.put(WorkSpecDao.class, WorkSpecDao_Impl.getRequiredConverters());
        map.put(DependencyDao.class, DependencyDao_Impl.getRequiredConverters());
        map.put(WorkTagDao.class, WorkTagDao_Impl.getRequiredConverters());
        map.put(SystemIdInfoDao.class, SystemIdInfoDao_Impl.getRequiredConverters());
        map.put(WorkNameDao.class, WorkNameDao_Impl.getRequiredConverters());
        map.put(WorkProgressDao.class, WorkProgressDao_Impl.getRequiredConverters());
        map.put(PreferenceDao.class, PreferenceDao_Impl.getRequiredConverters());
        map.put(RawWorkInfoDao.class, RawWorkInfoDao_Impl.getRequiredConverters());
        return map;
    }

    @Override // androidx.work.impl.WorkDatabase
    public PreferenceDao preferenceDao() {
        PreferenceDao preferenceDao;
        if (this._preferenceDao != null) {
            return this._preferenceDao;
        }
        synchronized (this) {
            try {
                if (this._preferenceDao == null) {
                    this._preferenceDao = new PreferenceDao_Impl(this);
                }
                preferenceDao = this._preferenceDao;
            } catch (Throwable th) {
                throw th;
            }
        }
        return preferenceDao;
    }

    @Override // androidx.work.impl.WorkDatabase
    public RawWorkInfoDao rawWorkInfoDao() {
        RawWorkInfoDao rawWorkInfoDao;
        if (this._rawWorkInfoDao != null) {
            return this._rawWorkInfoDao;
        }
        synchronized (this) {
            try {
                if (this._rawWorkInfoDao == null) {
                    this._rawWorkInfoDao = new RawWorkInfoDao_Impl(this);
                }
                rawWorkInfoDao = this._rawWorkInfoDao;
            } catch (Throwable th) {
                throw th;
            }
        }
        return rawWorkInfoDao;
    }

    @Override // androidx.work.impl.WorkDatabase
    public SystemIdInfoDao systemIdInfoDao() {
        SystemIdInfoDao systemIdInfoDao;
        if (this._systemIdInfoDao != null) {
            return this._systemIdInfoDao;
        }
        synchronized (this) {
            try {
                if (this._systemIdInfoDao == null) {
                    this._systemIdInfoDao = new SystemIdInfoDao_Impl(this);
                }
                systemIdInfoDao = this._systemIdInfoDao;
            } catch (Throwable th) {
                throw th;
            }
        }
        return systemIdInfoDao;
    }

    @Override // androidx.work.impl.WorkDatabase
    public WorkNameDao workNameDao() {
        WorkNameDao workNameDao;
        if (this._workNameDao != null) {
            return this._workNameDao;
        }
        synchronized (this) {
            try {
                if (this._workNameDao == null) {
                    this._workNameDao = new WorkNameDao_Impl(this);
                }
                workNameDao = this._workNameDao;
            } catch (Throwable th) {
                throw th;
            }
        }
        return workNameDao;
    }

    @Override // androidx.work.impl.WorkDatabase
    public WorkProgressDao workProgressDao() {
        WorkProgressDao workProgressDao;
        if (this._workProgressDao != null) {
            return this._workProgressDao;
        }
        synchronized (this) {
            try {
                if (this._workProgressDao == null) {
                    this._workProgressDao = new WorkProgressDao_Impl(this);
                }
                workProgressDao = this._workProgressDao;
            } catch (Throwable th) {
                throw th;
            }
        }
        return workProgressDao;
    }

    @Override // androidx.work.impl.WorkDatabase
    public WorkSpecDao workSpecDao() {
        WorkSpecDao workSpecDao;
        if (this._workSpecDao != null) {
            return this._workSpecDao;
        }
        synchronized (this) {
            try {
                if (this._workSpecDao == null) {
                    this._workSpecDao = new WorkSpecDao_Impl(this);
                }
                workSpecDao = this._workSpecDao;
            } catch (Throwable th) {
                throw th;
            }
        }
        return workSpecDao;
    }

    @Override // androidx.work.impl.WorkDatabase
    public WorkTagDao workTagDao() {
        WorkTagDao workTagDao;
        if (this._workTagDao != null) {
            return this._workTagDao;
        }
        synchronized (this) {
            try {
                if (this._workTagDao == null) {
                    this._workTagDao = new WorkTagDao_Impl(this);
                }
                workTagDao = this._workTagDao;
            } catch (Throwable th) {
                throw th;
            }
        }
        return workTagDao;
    }
}
