package defpackage;

import java.util.Iterator;

/* JADX INFO: loaded from: classes.dex */
public abstract class au extends pv0 {
    public abstract void bind(ky0 ky0Var, Object obj);

    public final int handle(Object obj) {
        ky0 ky0VarAcquire = acquire();
        try {
            bind(ky0VarAcquire, obj);
            return ky0VarAcquire.f();
        } finally {
            release(ky0VarAcquire);
        }
    }

    public final int handleMultiple(Iterable<Object> iterable) {
        t40.n(iterable, "entities");
        ky0 ky0VarAcquire = acquire();
        try {
            Iterator<Object> it = iterable.iterator();
            int iF = 0;
            while (it.hasNext()) {
                bind(ky0VarAcquire, it.next());
                iF += ky0VarAcquire.f();
            }
            return iF;
        } finally {
            release(ky0VarAcquire);
        }
    }

    public final int handleMultiple(Object[] objArr) {
        t40.n(objArr, "entities");
        ky0 ky0VarAcquire = acquire();
        try {
            int iF = 0;
            for (Object obj : objArr) {
                bind(ky0VarAcquire, obj);
                iF += ky0VarAcquire.f();
            }
            return iF;
        } finally {
            release(ky0VarAcquire);
        }
    }
}
