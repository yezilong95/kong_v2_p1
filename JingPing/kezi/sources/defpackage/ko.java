package defpackage;

import java.io.Serializable;
import kotlin.enums.EnumEntriesSerializationProxy;

/* JADX INFO: loaded from: classes.dex */
public final class ko extends l implements jo, Serializable {
    public final Enum[] b;

    public ko(Enum[] enumArr) {
        this.b = enumArr;
    }

    private final Object writeReplace() {
        return new EnumEntriesSerializationProxy(this.b);
    }

    @Override // defpackage.l
    public final int a() {
        return this.b.length;
    }

    @Override // defpackage.l, java.util.List, java.util.Collection
    public final boolean contains(Object obj) {
        if (!(obj instanceof Enum)) {
            return false;
        }
        Enum r4 = (Enum) obj;
        oy.j(r4, "element");
        Enum[] enumArr = this.b;
        int iOrdinal = r4.ordinal();
        return ((iOrdinal < 0 || iOrdinal >= enumArr.length) ? null : enumArr[iOrdinal]) == r4;
    }

    @Override // java.util.List
    public final Object get(int i) {
        Enum[] enumArr = this.b;
        int length = enumArr.length;
        if (i < 0 || i >= length) {
            throw new IndexOutOfBoundsException(hk.f(i, length, "index: ", ", size: "));
        }
        return enumArr[i];
    }

    @Override // defpackage.l, java.util.List
    public final int indexOf(Object obj) {
        if (!(obj instanceof Enum)) {
            return -1;
        }
        Enum r4 = (Enum) obj;
        oy.j(r4, "element");
        int iOrdinal = r4.ordinal();
        Enum[] enumArr = this.b;
        if (((iOrdinal < 0 || iOrdinal >= enumArr.length) ? null : enumArr[iOrdinal]) == r4) {
            return iOrdinal;
        }
        return -1;
    }

    @Override // defpackage.l, java.util.List
    public final int lastIndexOf(Object obj) {
        if (!(obj instanceof Enum)) {
            return -1;
        }
        Enum r2 = (Enum) obj;
        oy.j(r2, "element");
        return indexOf(r2);
    }
}
