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
import defpackage.aj;
import defpackage.d5;
import defpackage.h9;
import defpackage.hk;
import defpackage.ku;
import defpackage.li;
import defpackage.mf0;
import defpackage.oy;
import defpackage.pf0;
import defpackage.rl0;
import defpackage.s6;
import defpackage.sl0;
import defpackage.t6;
import defpackage.tl0;
import defpackage.vk0;
import defpackage.yo;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.concurrent.Executor;

/* JADX INFO: loaded from: classes.dex */
public abstract class WorkDatabase extends pf0 {
    public static final Companion Companion = new Companion(null);

    public static final class Companion {
        public /* synthetic */ Companion(aj ajVar) {
            this();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static final tl0 create$lambda$0(Context context, rl0 rl0Var) {
            oy.j(context, "$context");
            oy.j(rl0Var, "configuration");
            d5 d5Var = rl0Var.c;
            oy.j(d5Var, "callback");
            String str = rl0Var.b;
            if (str == null || str.length() == 0) {
                throw new IllegalArgumentException("Must set a non-null database name to a configuration that uses the no backup directory.");
            }
            rl0 rl0Var2 = new rl0(context, str, d5Var, true, true);
            return new ku(rl0Var2.a, rl0Var2.b, rl0Var2.c, rl0Var2.d, rl0Var2.e);
        }

        public final WorkDatabase create(Context context, Executor executor, boolean z) {
            mf0 mf0Var;
            String str;
            int i = 6;
            oy.j(context, "context");
            oy.j(executor, "queryExecutor");
            if (z) {
                mf0Var = new mf0(context, null);
                mf0Var.i = true;
            } else {
                if (vk0.r0(WorkDatabasePathHelperKt.WORK_DATABASE_NAME)) {
                    throw new IllegalArgumentException("Cannot build a database with null or empty name. If you are trying to create an in memory database, use Room.inMemoryDatabaseBuilder");
                }
                mf0 mf0Var2 = new mf0(context, WorkDatabasePathHelperKt.WORK_DATABASE_NAME);
                mf0Var2.h = new h9(i, context);
                mf0Var = mf0Var2;
            }
            mf0Var.f = executor;
            CleanupCallback cleanupCallback = CleanupCallback.INSTANCE;
            oy.j(cleanupCallback, "callback");
            ArrayList arrayList = mf0Var.c;
            arrayList.add(cleanupCallback);
            mf0Var.a(Migration_1_2.INSTANCE);
            mf0Var.a(new RescheduleMigration(context, 2, 3));
            mf0Var.a(Migration_3_4.INSTANCE);
            mf0Var.a(Migration_4_5.INSTANCE);
            mf0Var.a(new RescheduleMigration(context, 5, 6));
            mf0Var.a(Migration_6_7.INSTANCE);
            mf0Var.a(Migration_7_8.INSTANCE);
            mf0Var.a(Migration_8_9.INSTANCE);
            mf0Var.a(new WorkMigration9To10(context));
            mf0Var.a(new RescheduleMigration(context, 10, 11));
            mf0Var.a(Migration_11_12.INSTANCE);
            mf0Var.a(Migration_12_13.INSTANCE);
            mf0Var.a(Migration_15_16.INSTANCE);
            mf0Var.k = false;
            mf0Var.l = true;
            Executor executor2 = mf0Var.f;
            if (executor2 == null && mf0Var.g == null) {
                s6 s6Var = t6.t;
                mf0Var.g = s6Var;
                mf0Var.f = s6Var;
            } else if (executor2 != null && mf0Var.g == null) {
                mf0Var.g = executor2;
            } else if (executor2 == null) {
                mf0Var.f = mf0Var.g;
            }
            HashSet hashSet = mf0Var.p;
            LinkedHashSet linkedHashSet = mf0Var.o;
            if (hashSet != null) {
                Iterator it = hashSet.iterator();
                while (it.hasNext()) {
                    int iIntValue = ((Number) it.next()).intValue();
                    if (linkedHashSet.contains(Integer.valueOf(iIntValue))) {
                        throw new IllegalArgumentException(hk.g(iIntValue, "Inconsistency detected. A Migration was supplied to addMigration(Migration... migrations) that has a start or end version equal to a start version supplied to fallbackToDestructiveMigrationFrom(int... startVersions). Start version: ").toString());
                    }
                }
            }
            sl0 yoVar = mf0Var.h;
            if (yoVar == null) {
                yoVar = new yo(4);
            }
            sl0 sl0Var = yoVar;
            if (mf0Var.m > 0) {
                if (mf0Var.b != null) {
                    throw new IllegalArgumentException("Required value was null.");
                }
                throw new IllegalArgumentException("Cannot create auto-closing database for an in-memory database.");
            }
            boolean z2 = mf0Var.i;
            RoomDatabase$JournalMode roomDatabase$JournalMode = mf0Var.j;
            Context context2 = mf0Var.a;
            RoomDatabase$JournalMode roomDatabase$JournalModeResolve$room_runtime_release = roomDatabase$JournalMode.resolve$room_runtime_release(context2);
            Executor executor3 = mf0Var.f;
            if (executor3 == null) {
                throw new IllegalArgumentException("Required value was null.");
            }
            Executor executor4 = mf0Var.g;
            if (executor4 == null) {
                throw new IllegalArgumentException("Required value was null.");
            }
            li liVar = new li(context2, mf0Var.b, sl0Var, mf0Var.n, arrayList, z2, roomDatabase$JournalModeResolve$room_runtime_release, executor3, executor4, mf0Var.k, mf0Var.l, linkedHashSet, mf0Var.d, mf0Var.e);
            Package r2 = WorkDatabase.class.getPackage();
            oy.g(r2);
            String name = r2.getName();
            String canonicalName = WorkDatabase.class.getCanonicalName();
            oy.g(canonicalName);
            oy.i(name, "fullPackage");
            if (name.length() != 0) {
                canonicalName = canonicalName.substring(name.length() + 1);
                oy.i(canonicalName, "this as java.lang.String).substring(startIndex)");
            }
            String strReplace = canonicalName.replace('.', '_');
            oy.i(strReplace, "replace(...)");
            String strConcat = strReplace.concat("_Impl");
            try {
                if (name.length() == 0) {
                    str = strConcat;
                } else {
                    str = name + '.' + strConcat;
                }
                Class<?> cls = Class.forName(str, true, WorkDatabase.class.getClassLoader());
                oy.h(cls, "null cannot be cast to non-null type java.lang.Class<T of androidx.room.Room.getGeneratedImplementation>");
                pf0 pf0Var = (pf0) cls.newInstance();
                pf0Var.init(liVar);
                return (WorkDatabase) pf0Var;
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
