package defpackage;

import java.util.Map;

/* JADX INFO: loaded from: classes.dex */
public final class lf implements Map.Entry, wz {
    public final Object b;
    public final Object c;

    public lf(Object obj, Object obj2) {
        this.b = obj;
        this.c = obj2;
    }

    @Override // java.util.Map.Entry
    public final Object getKey() {
        return this.b;
    }

    @Override // java.util.Map.Entry
    public final Object getValue() {
        return this.c;
    }

    @Override // java.util.Map.Entry
    public final Object setValue(Object obj) {
        throw new UnsupportedOperationException("not implemented");
    }
}
