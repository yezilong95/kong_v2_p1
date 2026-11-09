package defpackage;

import java.util.Map;

/* JADX INFO: loaded from: classes.dex */
public final class vq0 implements Map.Entry {
    public final Object E;
    public final Object F;
    public vq0 G;
    public vq0 H;

    public vq0(Object obj, Object obj2) {
        this.E = obj;
        this.F = obj2;
    }

    @Override // java.util.Map.Entry
    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof vq0)) {
            return false;
        }
        vq0 vq0Var = (vq0) obj;
        return this.E.equals(vq0Var.E) && this.F.equals(vq0Var.F);
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
    public final int hashCode() {
        return this.E.hashCode() ^ this.F.hashCode();
    }

    @Override // java.util.Map.Entry
    public final Object setValue(Object obj) {
        throw new UnsupportedOperationException("An entry modification is not supported");
    }

    public final String toString() {
        return this.E + "=" + this.F;
    }
}
