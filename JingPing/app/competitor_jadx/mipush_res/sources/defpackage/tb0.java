package defpackage;

import java.util.Map;
import kotlin.collections.builders.MapBuilder;

/* JADX INFO: loaded from: classes.dex */
public final class tb0 implements Map.Entry {
    public final MapBuilder E;
    public final int F;

    public tb0(MapBuilder mapBuilder, int i) {
        this.E = mapBuilder;
        this.F = i;
    }

    @Override // java.util.Map.Entry
    public final boolean equals(Object obj) {
        if (!(obj instanceof Map.Entry)) {
            return false;
        }
        Map.Entry entry = (Map.Entry) obj;
        return t40.f(entry.getKey(), getKey()) && t40.f(entry.getValue(), getValue());
    }

    @Override // java.util.Map.Entry
    public final Object getKey() {
        return this.E.E[this.F];
    }

    @Override // java.util.Map.Entry
    public final Object getValue() {
        Object[] objArr = this.E.F;
        t40.k(objArr);
        return objArr[this.F];
    }

    @Override // java.util.Map.Entry
    public final int hashCode() {
        Object key = getKey();
        int iHashCode = key != null ? key.hashCode() : 0;
        Object value = getValue();
        return iHashCode ^ (value != null ? value.hashCode() : 0);
    }

    @Override // java.util.Map.Entry
    public final Object setValue(Object obj) {
        MapBuilder mapBuilder = this.E;
        mapBuilder.checkIsMutable$kotlin_stdlib();
        Object[] objArrAccess$allocateValuesArray = MapBuilder.access$allocateValuesArray(mapBuilder);
        int i = this.F;
        Object obj2 = objArrAccess$allocateValuesArray[i];
        objArrAccess$allocateValuesArray[i] = obj;
        return obj2;
    }

    public final String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getKey());
        sb.append('=');
        sb.append(getValue());
        return sb.toString();
    }
}
