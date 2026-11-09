package androidx.work.impl;

import android.content.Context;
import androidx.room.RoomDatabase$JournalMode;
import androidx.work.impl.model.DependencyDao;
import androidx.work.impl.model.PreferenceDao;
import androidx.work.impl.model.RawWorkInfoDao;
import androidx.work.impl.model.SystemIdInfoDao;
import androidx.work.impl.model.WorkNameDao;
import androidx.work.impl.model.WorkProgressDao;
import androidx.work.impl.model.WorkSpecDao;
import androidx.work.impl.model.WorkTagDao;
import defpackage.d8;
import defpackage.di;
import defpackage.e8;
import defpackage.fb;
import defpackage.fy0;
import defpackage.gy0;
import defpackage.hm;
import defpackage.hp0;
import defpackage.hy0;
import defpackage.kp0;
import defpackage.l0;
import defpackage.qx0;
import defpackage.t40;
import defpackage.tm;
import defpackage.w00;
import defpackage.zn;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.concurrent.Executor;

/* JADX INFO: loaded from: classes.dex */
public abstract class WorkDatabase extends kp0 {
    public static final Companion Companion = new Companion(null);

    public static final class Companion {
        public /* synthetic */ Companion(tm tmVar) {
            this();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static final hy0 create$lambda$0(Context context, fy0 fy0Var) {
            t40.n(context, "$context");
            t40.n(fy0Var, "configuration");
            l0 l0Var = fy0Var.c;
            t40.n(l0Var, "callback");
            String str = fy0Var.b;
            if (str == null || str.length() == 0) {
                throw new IllegalArgumentException("Must set a non-null database name to a configuration that uses the no backup directory.");
            }
            fy0 fy0Var2 = new fy0(context, str, l0Var, true, true);
            return new w00(fy0Var2.a, fy0Var2.b, fy0Var2.c, fy0Var2.d, fy0Var2.e);
        }

        public final WorkDatabase create(Context context, Executor executor, boolean z) {
            hp0 hp0Var;
            String str;
            int i = 10;
            t40.n(context, "context");
            t40.n(executor, "queryExecutor");
            if (z) {
                hp0Var = new hp0(context, null);
                hp0Var.i = true;
            } else {
                if (qx0.o0(WorkDatabasePathHelperKt.WORK_DATABASE_NAME)) {
                    throw new IllegalArgumentException("Cannot build a database with null or empty name. If you are trying to create an in memory database, use Room.inMemoryDatabaseBuilder");
                }
                hp0 hp0Var2 = new hp0(context, WorkDatabasePathHelperKt.WORK_DATABASE_NAME);
                hp0Var2.h = new fb(i, context);
                hp0Var = hp0Var2;
            }
            hp0Var.f = executor;
            CleanupCallback cleanupCallback = CleanupCallback.INSTANCE;
            t40.n(cleanupCallback, "callback");
            ArrayList arrayList = hp0Var.c;
            arrayList.add(cleanupCallback);
            hp0Var.a(Migration_1_2.INSTANCE);
            hp0Var.a(new RescheduleMigration(context, 2, 3));
            hp0Var.a(Migration_3_4.INSTANCE);
            hp0Var.a(Migration_4_5.INSTANCE);
            hp0Var.a(new RescheduleMigration(context, 5, 6));
            hp0Var.a(Migration_6_7.INSTANCE);
            hp0Var.a(Migration_7_8.INSTANCE);
            hp0Var.a(Migration_8_9.INSTANCE);
            hp0Var.a(new WorkMigration9To10(context));
            hp0Var.a(new RescheduleMigration(context, 10, 11));
            hp0Var.a(Migration_11_12.INSTANCE);
            hp0Var.a(Migration_12_13.INSTANCE);
            hp0Var.a(Migration_15_16.INSTANCE);
            hp0Var.k = false;
            hp0Var.l = true;
            Executor executor2 = hp0Var.f;
            if (executor2 == null && hp0Var.g == null) {
                d8 d8Var = e8.l;
                hp0Var.g = d8Var;
                hp0Var.f = d8Var;
            } else if (executor2 != null && hp0Var.g == null) {
                hp0Var.g = executor2;
            } else if (executor2 == null) {
                hp0Var.f = hp0Var.g;
            }
            HashSet hashSet = hp0Var.p;
            LinkedHashSet linkedHashSet = hp0Var.o;
            if (hashSet != null) {
                Iterator it = hashSet.iterator();
                while (it.hasNext()) {
                    int iIntValue = ((Number) it.next()).intValue();
                    if (linkedHashSet.contains(Integer.valueOf(iIntValue))) {
                        throw new IllegalArgumentException(zn.j(iIntValue, "Inconsistency detected. A Migration was supplied to addMigration(Migration... migrations) that has a start or end version equal to a start version supplied to fallbackToDestructiveMigrationFrom(int... startVersions). Start version: ").toString());
                    }
                }
            }
            gy0 diVar = hp0Var.h;
            if (diVar == null) {
                diVar = new di(17);
            }
            gy0 gy0Var = diVar;
            if (hp0Var.m > 0) {
                if (hp0Var.b != null) {
                    throw new IllegalArgumentException("Required value was null.");
                }
                throw new IllegalArgumentException("Cannot create auto-closing database for an in-memory database.");
            }
            boolean z2 = hp0Var.i;
            RoomDatabase$JournalMode roomDatabase$JournalMode = hp0Var.j;
            Context context2 = hp0Var.a;
            RoomDatabase$JournalMode roomDatabase$JournalModeResolve$room_runtime_release = roomDatabase$JournalMode.resolve$room_runtime_release(context2);
            Executor executor3 = hp0Var.f;
            if (executor3 == null) {
                throw new IllegalArgumentException("Required value was null.");
            }
            Executor executor4 = hp0Var.g;
            if (executor4 == null) {
                throw new IllegalArgumentException("Required value was null.");
            }
            hm hmVar = new hm(context2, hp0Var.b, gy0Var, hp0Var.n, arrayList, z2, roomDatabase$JournalModeResolve$room_runtime_release, executor3, executor4, hp0Var.k, hp0Var.l, linkedHashSet, hp0Var.d, hp0Var.e);
            Package r2 = WorkDatabase.class.getPackage();
            t40.k(r2);
            String name = r2.getName();
            String canonicalName = WorkDatabase.class.getCanonicalName();
            t40.k(canonicalName);
            t40.m(name, "fullPackage");
            if (name.length() != 0) {
                canonicalName = canonicalName.substring(name.length() + 1);
                t40.m(canonicalName, "this as java.lang.String).substring(startIndex)");
            }
            String strReplace = canonicalName.replace('.', '_');
            t40.m(strReplace, "replace(...)");
            String strConcat = strReplace.concat("_Impl");
            try {
                if (name.length() == 0) {
                    str = strConcat;
                } else {
                    str = name + '.' + strConcat;
                }
                Class<?> cls = Class.forName(str, true, WorkDatabase.class.getClassLoader());
                t40.l(cls, "null cannot be cast to non-null type java.lang.Class<T of androidx.room.Room.getGeneratedImplementation>");
                kp0 kp0Var = (kp0) cls.newInstance();
                kp0Var.init(hmVar);
                return (WorkDatabase) kp0Var;
            } catch (ClassNotFoundException unused) {
                throw new RuntimeException("Cannot find implementation for " + WorkDatabase.class.getCanonicalName() + ". " + strConcat + " does not exist");
            } catch (IllegalAccessException unused2) {
                throw new RuntimeException("Cannot access the constructor " + WorkDatabase.class + ".canonicalName");
            } catch (InstantiationException unused3) {
                throw new RuntimeException("Failed to create an instance of " + WorkDatabase.class + ".canonicalName");
            }
        }

        private Companion() {
        }
    }

    public static final WorkDatabase create(Context context, Executor executor, boolean z) {
        return Companion.create(context, executor, z);
    }

    public abstract DependencyDao dependencyDao();

    public abstract PreferenceDao preferenceDao();

    public abstract RawWorkInfoDao rawWorkInfoDao();

    public abstract SystemIdInfoDao systemIdInfoDao();

    public abstract WorkNameDao workNameDao();

    public abstract WorkProgressDao workProgressDao();

    public abstract WorkSpecDao workSpecDao();

    public abstract WorkTagDao workTagDao();
}
