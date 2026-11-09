package defpackage;

import java.util.Map;
import kotlin.collections.builders.MapBuilder;

/* JADX INFO: loaded from: classes.dex */
public final class e40 implements Map.Entry, wz {
    public final MapBuilder b;
    public final int c;

    public e40(MapBuilder mapBuilder, int i) {
        this.b = mapBuilder;
        this.c = i;
    }

    @Override // java.util.Map.Entry
    public final boolean equals(Object obj) {
        if (!(obj instanceof Map.Entry)) {
            return false;
        }
        Map.Entry entry = (Map.Entry) obj;
        return oy.c(entry.getKey(), getKey()) && oy.c(entry.getValue(), getValue());
    }

    @Override // java.util.Map.Entry
    public final Object getKey() {
        return this.b.b[this.c];
    }

    @Override // java.util.Map.Entry
    public final Object getValue() {
        Object[] objArr = this.b.c;
        oy.g(objArr);
        return objArr[this.c];
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
        MapBuilder mapBuilder = this.b;
        mapBuilder.checkIsMutable$kotlin_stdlib();
        Object[] objArrAccess$allocateValuesArray = MapBuilder.access$allocateValuesArray(mapBuilder);
        int i = this.c;
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
