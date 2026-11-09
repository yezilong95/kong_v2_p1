package defpackage;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;
import kotlin.collections.builders.ListBuilder;

/* JADX INFO: loaded from: classes.dex */
public abstract class bu extends pv0 {
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public bu(kp0 kp0Var) {
        super(kp0Var);
        t40.n(kp0Var, "database");
    }

    public abstract void bind(ky0 ky0Var, Object obj);

    public final void insert(Object obj) {
        ky0 ky0VarAcquire = acquire();
        try {
            bind(ky0VarAcquire, obj);
            ky0VarAcquire.u();
        } finally {
            release(ky0VarAcquire);
        }
    }

    public final long insertAndReturnId(Object obj) {
        ky0 ky0VarAcquire = acquire();
        try {
            bind(ky0VarAcquire, obj);
            return ky0VarAcquire.u();
        } finally {
            release(ky0VarAcquire);
        }
    }

    public final long[] insertAndReturnIdsArray(Collection<Object> collection) {
        t40.n(collection, "entities");
        ky0 ky0VarAcquire = acquire();
        try {
            long[] jArr = new long[collection.size()];
            int i = 0;
            for (Object obj : collection) {
                int i2 = i + 1;
                if (i < 0) {
                    throw new ArithmeticException("Index overflow has happened.");
                }
                bind(ky0VarAcquire, obj);
                jArr[i] = ky0VarAcquire.u();
                i = i2;
            }
            release(ky0VarAcquire);
            return jArr;
        } catch (Throwable th) {
            release(ky0VarAcquire);
            throw th;
        }
    }

    public final Long[] insertAndReturnIdsArrayBox(Collection<Object> collection) {
        t40.n(collection, "entities");
        ky0 ky0VarAcquire = acquire();
        Iterator<Object> it = collection.iterator();
        try {
            int size = collection.size();
            Long[] lArr = new Long[size];
            for (int i = 0; i < size; i++) {
                bind(ky0VarAcquire, it.next());
                lArr[i] = Long.valueOf(ky0VarAcquire.u());
            }
            return lArr;
        } finally {
            release(ky0VarAcquire);
        }
    }

    public final List<Long> insertAndReturnIdsList(Object[] objArr) {
        t40.n(objArr, "entities");
        ky0 ky0VarAcquire = acquire();
        try {
            ListBuilder listBuilder = new ListBuilder(0, 1, null);
            for (Object obj : objArr) {
                bind(ky0VarAcquire, obj);
                listBuilder.add(Long.valueOf(ky0VarAcquire.u()));
            }
            List<Long> listBuild = listBuilder.build();
            release(ky0VarAcquire);
            return listBuild;
        } catch (Throwable th) {
            release(ky0VarAcquire);
            throw th;
        }
    }

    public final void insert(Object[] objArr) {
        t40.n(objArr, "entities");
        ky0 ky0VarAcquire = acquire();
        try {
            for (Object obj : objArr) {
                bind(ky0VarAcquire, obj);
                ky0VarAcquire.u();
            }
        } finally {
            release(ky0VarAcquire);
        }
    }

    public final long[] insertAndReturnIdsArray(Object[] objArr) {
        t40.n(objArr, "entities");
        ky0 ky0VarAcquire = acquire();
        try {
            long[] jArr = new long[objArr.length];
            int length = objArr.length;
            int i = 0;
            int i2 = 0;
            while (i < length) {
                int i3 = i2 + 1;
                bind(ky0VarAcquire, objArr[i]);
                jArr[i2] = ky0VarAcquire.u();
                i++;
                i2 = i3;
            }
            return jArr;
        } finally {
            release(ky0VarAcquire);
        }
    }

    public final Long[] insertAndReturnIdsArrayBox(Object[] objArr) {
        t40.n(objArr, "entities");
        ky0 ky0VarAcquire = acquire();
        try {
            int length = objArr.length;
            Long[] lArr = new Long[length];
            int i = 0;
            int i2 = 0;
            while (i < length) {
                int i3 = i2 + 1;
                try {
                    bind(ky0VarAcquire, objArr[i2]);
                    lArr[i] = Long.valueOf(ky0VarAcquire.u());
                    i++;
                    i2 = i3;
                } catch (ArrayIndexOutOfBoundsException e) {
                    throw new NoSuchElementException(e.getMessage());
                }
            }
            release(ky0VarAcquire);
            return lArr;
        } catch (Throwable th) {
            release(ky0VarAcquire);
            throw th;
        }
    }

    public final List<Long> insertAndReturnIdsList(Collection<Object> collection) {
        t40.n(collection, "entities");
        ky0 ky0VarAcquire = acquire();
        try {
            ListBuilder listBuilder = new ListBuilder(0, 1, null);
            Iterator<T> it = collection.iterator();
            while (it.hasNext()) {
                bind(ky0VarAcquire, it.next());
                listBuilder.add(Long.valueOf(ky0VarAcquire.u()));
            }
            List<Long> listBuild = listBuilder.build();
            release(ky0VarAcquire);
            return listBuild;
        } catch (Throwable th) {
            release(ky0VarAcquire);
            throw th;
        }
    }

    public final void insert(Iterable<Object> iterable) {
        t40.n(iterable, "entities");
        ky0 ky0VarAcquire = acquire();
        try {
            Iterator<Object> it = iterable.iterator();
            while (it.hasNext()) {
                bind(ky0VarAcquire, it.next());
                ky0VarAcquire.u();
            }
        } finally {
            release(ky0VarAcquire);
        }
    }
}
