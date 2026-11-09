package defpackage;

import java.util.Iterator;

/* JADX INFO: loaded from: classes.dex */
public abstract class ho extends aj0 {
    public abstract void bind(wl0 wl0Var, Object obj);

    public final int handle(Object obj) {
        wl0 wl0VarAcquire = acquire();
        try {
            bind(wl0VarAcquire, obj);
            return wl0VarAcquire.n();
        } finally {
            release(wl0VarAcquire);
        }
    }

    public final int handleMultiple(Iterable<Object> iterable) {
        oy.j(iterable, "entities");
        wl0 wl0VarAcquire = acquire();
        try {
            Iterator<Object> it = iterable.iterator();
            int iN = 0;
            while (it.hasNext()) {
                bind(wl0VarAcquire, it.next());
                iN += wl0VarAcquire.n();
            }
            return iN;
        } finally {
            release(wl0VarAcquire);
        }
    }

    public final int handleMultiple(Object[] objArr) {
        oy.j(objArr, "entities");
        wl0 wl0VarAcquire = acquire();
        try {
            int iN = 0;
            for (Object obj : objArr) {
                bind(wl0VarAcquire, obj);
                iN += wl0VarAcquire.n();
            }
            return iN;
        } finally {
            release(wl0VarAcquire);
        }
    }
}
