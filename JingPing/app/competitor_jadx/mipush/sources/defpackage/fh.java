package defpackage;

import java.util.Map;

/* JADX INFO: loaded from: classes.dex */
public final class fh implements Map.Entry {
    public final Object E;
    public final Object F;

    public fh(Object obj, Object obj2) {
        this.E = obj;
        this.F = obj2;
    }

    @Override // java.util.Map.Entry
    public final Object getKey() {
        return this.E;
    }

    @Override // java.util.Map.Entry
    public final Object getValue() {
        return this.F;
    }

    @Override // java.util.Map.Entry
    public final Object setValue(Object obj) {
        throw new UnsupportedOperationException("not implemented");
    }
}
