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
public abstract class kp0 {
    public static final jp0 Companion = new jp0();
    public static final int MAX_BIND_PARAMETER_CNT = 999;
    private boolean allowMainThreadQueries;
    private u8 autoCloser;
    private final Map<String, Object> backingFieldMap;
    private hy0 internalOpenHelper;
    private Executor internalQueryExecutor;
    private Executor internalTransactionExecutor;
    protected List<? extends ip0> mCallbacks;
    protected volatile ey0 mDatabase;
    private final Map<Class<?>, Object> typeConverters;
    private boolean writeAheadLoggingEnabled;
    private final y40 invalidationTracker = createInvalidationTracker();
    private Map<Class<? extends w8>, w8> autoMigrationSpecs = new LinkedHashMap();
    private final ReentrantReadWriteLock readWriteLock = new ReentrantReadWriteLock();
    private final ThreadLocal<Integer> suspendingTransactionId = new ThreadLocal<>();

    public kp0() {
        Map<String, Object> mapSynchronizedMap = Collections.synchronizedMap(new LinkedHashMap());
        t40.m(mapSynchronizedMap, "synchronizedMap(mutableMapOf())");
        this.backingFieldMap = mapSynchronizedMap;
        this.typeConverters = new LinkedHashMap();
    }

    public static /* synthetic */ Cursor query$default(kp0 kp0Var, jy0 jy0Var, CancellationSignal cancellationSignal, int i, Object obj) {
        if (obj != null) {
            throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: query");
        }
        if ((i & 2) != 0) {
            cancellationSignal = null;
        }
        return kp0Var.query(jy0Var, cancellationSignal);
    }

    public final void a() {
        assertNotMainThread();
        ey0 ey0VarJ = ((w00) getOpenHelper()).j();
        getInvalidationTracker().e(ey0VarJ);
        if (ey0VarJ.m()) {
            ey0VarJ.r();
        } else {
            ey0VarJ.b();
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
        ((w00) getOpenHelper()).j().a();
        if (inTransaction()) {
            return;
        }
        y40 invalidationTracker = getInvalidationTracker();
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
            t40.m(writeLock, "readWriteLock.writeLock()");
            writeLock.lock();
            try {
                getInvalidationTracker().getClass();
                ((w00) getOpenHelper()).close();
            } finally {
                writeLock.unlock();
            }
        }
    }

    public ky0 compileStatement(String str) {
        t40.n(str, "sql");
        assertNotMainThread();
        assertNotSuspendingTransaction();
        return ((w00) getOpenHelper()).j().g(str);
    }

    public abstract y40 createInvalidationTracker();

    public abstract hy0 createOpenHelper(hm hmVar);

    public void endTransaction() {
        b();
    }

    public final Map<Class<? extends w8>, w8> getAutoMigrationSpecs() {
        return this.autoMigrationSpecs;
    }

    public List<ye0> getAutoMigrations(Map<Class<? extends w8>, w8> map) {
        t40.n(map, "autoMigrationSpecs");
        return EmptyList.INSTANCE;
    }

    public final Map<String, Object> getBackingFieldMap() {
        return this.backingFieldMap;
    }

    public final Lock getCloseLock$room_runtime_release() {
        ReentrantReadWriteLock.ReadLock lock = this.readWriteLock.readLock();
        t40.m(lock, "readWriteLock.readLock()");
        return lock;
    }

    public y40 getInvalidationTracker() {
        return this.invalidationTracker;
    }

    public hy0 getOpenHelper() {
        hy0 hy0Var = this.internalOpenHelper;
        if (hy0Var != null) {
            return hy0Var;
        }
        t40.o1("internalOpenHelper");
        throw null;
    }

    public Executor getQueryExecutor() {
        Executor executor = this.internalQueryExecutor;
        if (executor != null) {
            return executor;
        }
        t40.o1("internalQueryExecutor");
        throw null;
    }

    public Set<Class<? extends w8>> getRequiredAutoMigrationSpecs() {
        return EmptySet.INSTANCE;
    }

    public Map<Class<?>, List<Class<?>>> getRequiredTypeConverters() {
        return st.E;
    }

    public final ThreadLocal<Integer> getSuspendingTransactionId() {
        return this.suspendingTransactionId;
    }

    public Executor getTransactionExecutor() {
        Executor executor = this.internalTransactionExecutor;
        if (executor != null) {
            return executor;
        }
        t40.o1("internalTransactionExecutor");
        throw null;
    }

    public <T> T getTypeConverter(Class<T> cls) {
        t40.n(cls, "klass");
        return (T) this.typeConverters.get(cls);
    }

    public boolean inTransaction() {
        return ((w00) getOpenHelper()).j().k();
    }

    public void init(hm hmVar) {
        t40.n(hmVar, "configuration");
        this.internalOpenHelper = createOpenHelper(hmVar);
        Set<Class<? extends w8>> requiredAutoMigrationSpecs = getRequiredAutoMigrationSpecs();
        BitSet bitSet = new BitSet();
        Iterator<Class<? extends w8>> it = requiredAutoMigrationSpecs.iterator();
        while (true) {
            boolean zHasNext = it.hasNext();
            ArrayList arrayList = hmVar.n;
            int i = -1;
            if (zHasNext) {
                Class<? extends w8> next = it.next();
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
                this.autoMigrationSpecs.put(next, (w8) arrayList.get(i));
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
                Iterator<ye0> it2 = getAutoMigrations(this.autoMigrationSpecs).iterator();
                while (true) {
                    boolean zContainsKey = false;
                    if (!it2.hasNext()) {
                        break;
                    }
                    ye0 next2 = it2.next();
                    int i4 = next2.startVersion;
                    int i5 = next2.endVersion;
                    z8 z8Var = hmVar.d;
                    LinkedHashMap linkedHashMap = (LinkedHashMap) z8Var.F;
                    if (linkedHashMap.containsKey(Integer.valueOf(i4))) {
                        Map map = (Map) linkedHashMap.get(Integer.valueOf(i4));
                        if (map == null) {
                            map = st.E;
                        }
                        zContainsKey = map.containsKey(Integer.valueOf(i5));
                    }
                    if (!zContainsKey) {
                        z8Var.s(next2);
                    }
                }
                hy0 openHelper = getOpenHelper();
                if (!pq0.class.isInstance(openHelper)) {
                    openHelper = null;
                }
                if (openHelper != null) {
                    throw new ClassCastException();
                }
                hy0 openHelper2 = getOpenHelper();
                if ((v8.class.isInstance(openHelper2) ? openHelper2 : null) != null) {
                    throw new ClassCastException();
                }
                boolean z = hmVar.g == RoomDatabase$JournalMode.WRITE_AHEAD_LOGGING;
                w00 w00Var = (w00) getOpenHelper();
                if (w00Var.J.F != is.O) {
                    u00 u00Var = (u00) w00Var.J.getValue();
                    t40.n(u00Var, "sQLiteOpenHelper");
                    u00Var.setWriteAheadLoggingEnabled(z);
                }
                w00Var.K = z;
                this.mCallbacks = hmVar.e;
                this.internalQueryExecutor = hmVar.h;
                this.internalTransactionExecutor = new q7(hmVar.i);
                this.allowMainThreadQueries = hmVar.f;
                this.writeAheadLoggingEnabled = z;
                Map<Class<?>, List<Class<?>>> requiredTypeConverters = getRequiredTypeConverters();
                BitSet bitSet2 = new BitSet();
                Iterator<Map.Entry<Class<?>, List<Class<?>>>> it3 = requiredTypeConverters.entrySet().iterator();
                while (true) {
                    boolean zHasNext2 = it3.hasNext();
                    ArrayList arrayList2 = hmVar.m;
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

    public void internalInitInvalidationTracker(ey0 ey0Var) {
        t40.n(ey0Var, "db");
        y40 invalidationTracker = getInvalidationTracker();
        invalidationTracker.getClass();
        synchronized (invalidationTracker.m) {
            if (invalidationTracker.g) {
                Log.e("ROOM", "Invalidation tracker is initialized twice :/.");
                return;
            }
            ey0Var.d("PRAGMA temp_store = MEMORY;");
            ey0Var.d("PRAGMA recursive_triggers='ON';");
            ey0Var.d("CREATE TEMP TABLE room_table_modification_log (table_id INTEGER PRIMARY KEY, invalidated INTEGER NOT NULL DEFAULT 0)");
            invalidationTracker.e(ey0Var);
            invalidationTracker.h = ey0Var.g("UPDATE room_table_modification_log SET invalidated = 0 WHERE invalidated = 1");
            invalidationTracker.g = true;
        }
    }

    public final boolean isMainThread$room_runtime_release() {
        return Looper.getMainLooper().getThread() == Thread.currentThread();
    }

    public boolean isOpen() {
        ey0 ey0Var = this.mDatabase;
        return t40.f(ey0Var != null ? Boolean.valueOf(ey0Var.isOpen()) : null, Boolean.TRUE);
    }

    public final Cursor query(jy0 jy0Var) {
        t40.n(jy0Var, "query");
        return query$default(this, jy0Var, null, 2, null);
    }

    public void runInTransaction(Runnable runnable) {
        t40.n(runnable, "body");
        beginTransaction();
        try {
            runnable.run();
            setTransactionSuccessful();
        } finally {
            endTransaction();
        }
    }

    public final void setAutoMigrationSpecs(Map<Class<? extends w8>, w8> map) {
        t40.n(map, "<set-?>");
        this.autoMigrationSpecs = map;
    }

    public void setTransactionSuccessful() {
        ((w00) getOpenHelper()).j().p();
    }

    public Cursor query(String str, Object[] objArr) {
        t40.n(str, "query");
        return ((w00) getOpenHelper()).j().c(new i6(str, objArr));
    }

    public Cursor query(jy0 jy0Var, CancellationSignal cancellationSignal) {
        t40.n(jy0Var, "query");
        assertNotMainThread();
        assertNotSuspendingTransaction();
        if (cancellationSignal != null) {
            return ((w00) getOpenHelper()).j().q(jy0Var, cancellationSignal);
        }
        return ((w00) getOpenHelper()).j().c(jy0Var);
    }

    public <V> V runInTransaction(Callable<V> callable) {
        t40.n(callable, "body");
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
