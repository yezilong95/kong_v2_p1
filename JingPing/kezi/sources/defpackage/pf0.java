package defpackage;

import android.database.Cursor;
import android.os.CancellationSignal;
import android.os.Looper;
import android.util.Log;
import androidx.room.RoomDatabase$JournalMode;
import java.util.ArrayList;
import java.util.BitSet;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.Callable;
import java.util.concurrent.Executor;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantReadWriteLock;
import kotlin.collections.EmptyList;
import kotlin.collections.EmptySet;

/* JADX INFO: loaded from: classes.dex */
public abstract class pf0 {
    public static final of0 Companion = new of0();
    public static final int MAX_BIND_PARAMETER_CNT = 999;
    private boolean allowMainThreadQueries;
    private m7 autoCloser;
    private final Map<String, Object> backingFieldMap;
    private tl0 internalOpenHelper;
    private Executor internalQueryExecutor;
    private Executor internalTransactionExecutor;
    protected List<? extends nf0> mCallbacks;
    protected volatile ql0 mDatabase;
    private final Map<Class<?>, Object> typeConverters;
    private boolean writeAheadLoggingEnabled;
    private final ty invalidationTracker = createInvalidationTracker();
    private Map<Class<? extends o7>, o7> autoMigrationSpecs = new LinkedHashMap();
    private final ReentrantReadWriteLock readWriteLock = new ReentrantReadWriteLock();
    private final ThreadLocal<Integer> suspendingTransactionId = new ThreadLocal<>();

    public pf0() {
        Map<String, Object> mapSynchronizedMap = Collections.synchronizedMap(new LinkedHashMap());
        oy.i(mapSynchronizedMap, "synchronizedMap(mutableMapOf())");
        this.backingFieldMap = mapSynchronizedMap;
        this.typeConverters = new LinkedHashMap();
    }

    public static /* synthetic */ Cursor query$default(pf0 pf0Var, vl0 vl0Var, CancellationSignal cancellationSignal, int i, Object obj) {
        if (obj != null) {
            throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: query");
        }
        if ((i & 2) != 0) {
            cancellationSignal = null;
        }
        return pf0Var.query(vl0Var, cancellationSignal);
    }

    public final void a() {
        assertNotMainThread();
        ql0 ql0VarA = ((ku) getOpenHelper()).a();
        getInvalidationTracker().e(ql0VarA);
        if (ql0VarA.h()) {
            ql0VarA.q();
        } else {
            ql0VarA.f();
        }
    }

    public void assertNotMainThread() {
        if (!this.allowMainThreadQueries && isMainThread$room_runtime_release()) {
            throw new IllegalStateException("Cannot access database on the main thread since it may potentially lock the UI for a long period of time.");
        }
    }

    public void assertNotSuspendingTransaction() {
        if (!inTransaction() && this.suspendingTransactionId.get() != null) {
            throw new IllegalStateException("Cannot access database on a different coroutine context inherited from a suspending transaction.");
        }
    }

    public final void b() {
        ((ku) getOpenHelper()).a().e();
        if (inTransaction()) {
            return;
        }
        ty invalidationTracker = getInvalidationTracker();
        if (invalidationTracker.f.compareAndSet(false, true)) {
            invalidationTracker.a.getQueryExecutor().execute(invalidationTracker.n);
        }
    }

    public void beginTransaction() {
        assertNotMainThread();
        a();
    }

    public abstract void clearAllTables();

    public void close() {
        if (isOpen()) {
            ReentrantReadWriteLock.WriteLock writeLock = this.readWriteLock.writeLock();
            oy.i(writeLock, "readWriteLock.writeLock()");
            writeLock.lock();
            try {
                getInvalidationTracker().getClass();
                ((ku) getOpenHelper()).close();
            } finally {
                writeLock.unlock();
            }
        }
    }

    public wl0 compileStatement(String str) {
        oy.j(str, "sql");
        assertNotMainThread();
        assertNotSuspendingTransaction();
        return ((ku) getOpenHelper()).a().p(str);
    }

    public abstract ty createInvalidationTracker();

    public abstract tl0 createOpenHelper(li liVar);

    public void endTransaction() {
        b();
    }

    public final Map<Class<? extends o7>, o7> getAutoMigrationSpecs() {
        return this.autoMigrationSpecs;
    }

    public List<g70> getAutoMigrations(Map<Class<? extends o7>, o7> map) {
        oy.j(map, "autoMigrationSpecs");
        return EmptyList.INSTANCE;
    }

    public final Map<String, Object> getBackingFieldMap() {
        return this.backingFieldMap;
    }

    public final Lock getCloseLock$room_runtime_release() {
        ReentrantReadWriteLock.ReadLock lock = this.readWriteLock.readLock();
        oy.i(lock, "readWriteLock.readLock()");
        return lock;
    }

    public ty getInvalidationTracker() {
        return this.invalidationTracker;
    }

    public tl0 getOpenHelper() {
        tl0 tl0Var = this.internalOpenHelper;
        if (tl0Var != null) {
            return tl0Var;
        }
        oy.S("internalOpenHelper");
        throw null;
    }

    public Executor getQueryExecutor() {
        Executor executor = this.internalQueryExecutor;
        if (executor != null) {
            return executor;
        }
        oy.S("internalQueryExecutor");
        throw null;
    }

    public Set<Class<? extends o7>> getRequiredAutoMigrationSpecs() {
        return EmptySet.INSTANCE;
    }

    public Map<Class<?>, List<Class<?>>> getRequiredTypeConverters() {
        return ao.b;
    }

    public final ThreadLocal<Integer> getSuspendingTransactionId() {
        return this.suspendingTransactionId;
    }

    public Executor getTransactionExecutor() {
        Executor executor = this.internalTransactionExecutor;
        if (executor != null) {
            return executor;
        }
        oy.S("internalTransactionExecutor");
        throw null;
    }

    public <T> T getTypeConverter(Class<T> cls) {
        oy.j(cls, "klass");
        return (T) this.typeConverters.get(cls);
    }

    public boolean inTransaction() {
        return ((ku) getOpenHelper()).a().z();
    }

    public void init(li liVar) {
        oy.j(liVar, "configuration");
        this.internalOpenHelper = createOpenHelper(liVar);
        Set<Class<? extends o7>> requiredAutoMigrationSpecs = getRequiredAutoMigrationSpecs();
        BitSet bitSet = new BitSet();
        Iterator<Class<? extends o7>> it = requiredAutoMigrationSpecs.iterator();
        while (true) {
            boolean zHasNext = it.hasNext();
            ArrayList arrayList = liVar.n;
            int i = -1;
            if (zHasNext) {
                Class<? extends o7> next = it.next();
                int size = arrayList.size() - 1;
                if (size >= 0) {
                    while (true) {
                        int i2 = size - 1;
                        if (next.isAssignableFrom(arrayList.get(size).getClass())) {
                            bitSet.set(size);
                            i = size;
                            break;
                        } else if (i2 < 0) {
                            break;
                        } else {
                            size = i2;
                        }
                    }
                }
                if (i < 0) {
                    throw new IllegalArgumentException(("A required auto migration spec (" + next.getCanonicalName() + ") is missing in the database configuration.").toString());
                }
                this.autoMigrationSpecs.put(next, (o7) arrayList.get(i));
            } else {
                int size2 = arrayList.size() - 1;
                if (size2 >= 0) {
                    while (true) {
                        int i3 = size2 - 1;
                        if (!bitSet.get(size2)) {
                            throw new IllegalArgumentException("Unexpected auto migration specs found. Annotate AutoMigrationSpec implementation with @ProvidedAutoMigrationSpec annotation or remove this spec from the builder.");
                        }
                        if (i3 < 0) {
                            break;
                        } else {
                            size2 = i3;
                        }
                    }
                }
                Iterator<g70> it2 = getAutoMigrations(this.autoMigrationSpecs).iterator();
                while (true) {
                    boolean zContainsKey = false;
                    if (!it2.hasNext()) {
                        break;
                    }
                    g70 next2 = it2.next();
                    int i4 = next2.startVersion;
                    int i5 = next2.endVersion;
                    d90 d90Var = liVar.d;
                    LinkedHashMap linkedHashMap = (LinkedHashMap) d90Var.b;
                    if (linkedHashMap.containsKey(Integer.valueOf(i4))) {
                        Map map = (Map) linkedHashMap.get(Integer.valueOf(i4));
                        if (map == null) {
                            map = ao.b;
                        }
                        zContainsKey = map.containsKey(Integer.valueOf(i5));
                    }
                    if (!zContainsKey) {
                        d90Var.a(next2);
                    }
                }
                tl0 openHelper = getOpenHelper();
                if (!cg0.class.isInstance(openHelper)) {
                    openHelper = null;
                }
                if (openHelper != null) {
                    throw new ClassCastException();
                }
                tl0 openHelper2 = getOpenHelper();
                if ((n7.class.isInstance(openHelper2) ? openHelper2 : null) != null) {
                    throw new ClassCastException();
                }
                boolean z = liVar.g == RoomDatabase$JournalMode.WRITE_AHEAD_LOGGING;
                ku kuVar = (ku) getOpenHelper();
                if (kuVar.g.c != qm.k) {
                    iu iuVar = (iu) kuVar.g.getValue();
                    oy.j(iuVar, "sQLiteOpenHelper");
                    iuVar.setWriteAheadLoggingEnabled(z);
                }
                kuVar.h = z;
                this.mCallbacks = liVar.e;
                this.internalQueryExecutor = liVar.h;
                this.internalTransactionExecutor = new m6(liVar.i);
                this.allowMainThreadQueries = liVar.f;
                this.writeAheadLoggingEnabled = z;
                Map<Class<?>, List<Class<?>>> requiredTypeConverters = getRequiredTypeConverters();
                BitSet bitSet2 = new BitSet();
                Iterator<Map.Entry<Class<?>, List<Class<?>>>> it3 = requiredTypeConverters.entrySet().iterator();
                while (true) {
                    boolean zHasNext2 = it3.hasNext();
                    ArrayList arrayList2 = liVar.m;
                    if (zHasNext2) {
                        Map.Entry<Class<?>, List<Class<?>>> next3 = it3.next();
                        Class<?> key = next3.getKey();
                        for (Class<?> cls : next3.getValue()) {
                            int size3 = arrayList2.size() - 1;
                            if (size3 >= 0) {
                                while (true) {
                                    int i6 = size3 - 1;
                                    if (cls.isAssignableFrom(arrayList2.get(size3).getClass())) {
                                        bitSet2.set(size3);
                                        break;
                                    } else if (i6 < 0) {
                                        break;
                                    } else {
                                        size3 = i6;
                                    }
                                }
                            } else {
                                size3 = -1;
                            }
                            if (!(size3 >= 0)) {
                                throw new IllegalArgumentException(("A required type converter (" + cls + ") for " + key.getCanonicalName() + " is missing in the database configuration.").toString());
                            }
                            this.typeConverters.put(cls, arrayList2.get(size3));
                        }
                    } else {
                        int size4 = arrayList2.size() - 1;
                        if (size4 < 0) {
                            return;
                        }
                        while (true) {
                            int i7 = size4 - 1;
                            if (!bitSet2.get(size4)) {
                                throw new IllegalArgumentException("Unexpected type converter " + arrayList2.get(size4) + ". Annotate TypeConverter class with @ProvidedTypeConverter annotation or remove this converter from the builder.");
                            }
                            if (i7 < 0) {
                                return;
                            } else {
                                size4 = i7;
                            }
                        }
                    }
                }
            }
        }
    }

    public void internalInitInvalidationTracker(ql0 ql0Var) {
        oy.j(ql0Var, "db");
        ty invalidationTracker = getInvalidationTracker();
        invalidationTracker.getClass();
        synchronized (invalidationTracker.m) {
            if (invalidationTracker.g) {
                Log.e("ROOM", "Invalidation tracker is initialized twice :/.");
                return;
            }
            ql0Var.i("PRAGMA temp_store = MEMORY;");
            ql0Var.i("PRAGMA recursive_triggers='ON';");
            ql0Var.i("CREATE TEMP TABLE room_table_modification_log (table_id INTEGER PRIMARY KEY, invalidated INTEGER NOT NULL DEFAULT 0)");
            invalidationTracker.e(ql0Var);
            invalidationTracker.h = ql0Var.p("UPDATE room_table_modification_log SET invalidated = 0 WHERE invalidated = 1");
            invalidationTracker.g = true;
        }
    }

    public final boolean isMainThread$room_runtime_release() {
        return Looper.getMainLooper().getThread() == Thread.currentThread();
    }

    public boolean isOpen() {
        ql0 ql0Var = this.mDatabase;
        return oy.c(ql0Var != null ? Boolean.valueOf(ql0Var.isOpen()) : null, Boolean.TRUE);
    }

    public final Cursor query(vl0 vl0Var) {
        oy.j(vl0Var, "query");
        return query$default(this, vl0Var, null, 2, null);
    }

    public void runInTransaction(Runnable runnable) {
        oy.j(runnable, "body");
        beginTransaction();
        try {
            runnable.run();
            setTransactionSuccessful();
        } finally {
            endTransaction();
        }
    }

    public final void setAutoMigrationSpecs(Map<Class<? extends o7>, o7> map) {
        oy.j(map, "<set-?>");
        this.autoMigrationSpecs = map;
    }

    public void setTransactionSuccessful() {
        ((ku) getOpenHelper()).a().l();
    }

    public Cursor query(String str, Object[] objArr) {
        oy.j(str, "query");
        return ((ku) getOpenHelper()).a().g(new e5(str, objArr));
    }

    public Cursor query(vl0 vl0Var, CancellationSignal cancellationSignal) {
        oy.j(vl0Var, "query");
        assertNotMainThread();
        assertNotSuspendingTransaction();
        if (cancellationSignal != null) {
            return ((ku) getOpenHelper()).a().m(vl0Var, cancellationSignal);
        }
        return ((ku) getOpenHelper()).a().g(vl0Var);
    }

    public <V> V runInTransaction(Callable<V> callable) {
        oy.j(callable, "body");
        beginTransaction();
        try {
            V vCall = callable.call();
            setTransactionSuccessful();
            return vCall;
        } finally {
            endTransaction();
        }
    }

    public static /* synthetic */ void getMCallbacks$annotations() {
    }

    public static /* synthetic */ void getMDatabase$annotations() {
    }

    public static /* synthetic */ void isOpen$annotations() {
    }
}
