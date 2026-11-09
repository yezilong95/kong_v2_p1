package defpackage;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import kotlin.collections.builders.ListBuilder;

/* JADX INFO: loaded from: classes.dex */
public abstract class io extends aj0 {
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public io(pf0 pf0Var) {
        super(pf0Var);
        oy.j(pf0Var, "database");
    }

    public abstract void bind(wl0 wl0Var, Object obj);

    public final void insert(Object obj) {
        wl0 wl0VarAcquire = acquire();
        try {
            bind(wl0VarAcquire, obj);
            wl0VarAcquire.w();
        } finally {
            release(wl0VarAcquire);
        }
    }

    public final long insertAndReturnId(Object obj) {
        wl0 wl0VarAcquire = acquire();
        try {
            bind(wl0VarAcquire, obj);
            return wl0VarAcquire.w();
        } finally {
            release(wl0VarAcquire);
        }
    }

    public final long[] insertAndReturnIdsArray(Collection<Object> collection) {
        oy.j(collection, "entities");
        wl0 wl0VarAcquire = acquire();
        try {
            long[] jArr = new long[collection.size()];
            int i = 0;
            for (Object obj : collection) {
                int i2 = i + 1;
                if (i < 0) {
                    be.Y();
                    throw null;
                }
                bind(wl0VarAcquire, obj);
                jArr[i] = wl0VarAcquire.w();
                i = i2;
            }
            release(wl0VarAcquire);
            return jArr;
        } catch (Throwable th) {
            release(wl0VarAcquire);
            throw th;
        }
    }

    public final Long[] insertAndReturnIdsArrayBox(Collection<Object> collection) {
        oy.j(collection, "entities");
        wl0 wl0VarAcquire = acquire();
        Iterator<Object> it = collection.iterator();
        try {
            int size = collection.size();
            Long[] lArr = new Long[size];
            for (int i = 0; i < size; i++) {
                bind(wl0VarAcquire, it.next());
                lArr[i] = Long.valueOf(wl0VarAcquire.w());
            }
            return lArr;
        } finally {
            release(wl0VarAcquire);
        }
    }

    public final List<Long> insertAndReturnIdsList(Object[] objArr) {
        oy.j(objArr, "entities");
        wl0 wl0VarAcquire = acquire();
        try {
            ListBuilder listBuilder = new ListBuilder(0, 1, null);
            for (Object obj : objArr) {
                bind(wl0VarAcquire, obj);
                listBuilder.add(Long.valueOf(wl0VarAcquire.w()));
            }
            List<Long> listBuild = listBuilder.build();
            release(wl0VarAcquire);
            return listBuild;
        } catch (Throwable th) {
            release(wl0VarAcquire);
            throw th;
        }
    }

    public final void insert(Object[] objArr) {
        oy.j(objArr, "entities");
        wl0 wl0VarAcquire = acquire();
        try {
            for (Object obj : objArr) {
                bind(wl0VarAcquire, obj);
                wl0VarAcquire.w();
            }
        } finally {
            release(wl0VarAcquire);
        }
    }

    public final long[] insertAndReturnIdsArray(Object[] objArr) {
        oy.j(objArr, "entities");
        wl0 wl0VarAcquire = acquire();
        try {
            long[] jArr = new long[objArr.length];
            int length = objArr.length;
            int i = 0;
            int i2 = 0;
            while (i < length) {
                int i3 = i2 + 1;
                bind(wl0VarAcquire, objArr[i]);
                jArr[i2] = wl0VarAcquire.w();
                i++;
                i2 = i3;
            }
            return jArr;
        } finally {
            release(wl0VarAcquire);
        }
    }

    public final Long[] insertAndReturnIdsArrayBox(Object[] objArr) {
        oy.j(objArr, "entities");
        wl0 wl0VarAcquire = acquire();
        i iVarW = k9.w(objArr);
        try {
            int length = objArr.length;
            Long[] lArr = new Long[length];
            for (int i = 0; i < length; i++) {
                bind(wl0VarAcquire, iVarW.next());
                lArr[i] = Long.valueOf(wl0VarAcquire.w());
            }
            return lArr;
        } finally {
            release(wl0VarAcquire);
        }
    }

    public final List<Long> insertAndReturnIdsList(Collection<Object> collection) {
        oy.j(collection, "entities");
        wl0 wl0VarAcquire = acquire();
        try {
            ListBuilder listBuilder = new ListBuilder(0, 1, null);
            Iterator<T> it = collection.iterator();
            while (it.hasNext()) {
                bind(wl0VarAcquire, it.next());
                listBuilder.add(Long.valueOf(wl0VarAcquire.w()));
            }
            List<Long> listBuild = listBuilder.build();
            release(wl0VarAcquire);
            return listBuild;
        } catch (Throwable th) {
            release(wl0VarAcquire);
            throw th;
        }
    }

    public final void insert(Iterable<Object> iterable) {
        oy.j(iterable, "entities");
        wl0 wl0VarAcquire = acquire();
        try {
            Iterator<Object> it = iterable.iterator();
            while (it.hasNext()) {
                bind(wl0VarAcquire, it.next());
                wl0VarAcquire.w();
            }
        } finally {
            release(wl0VarAcquire);
        }
    }
}
