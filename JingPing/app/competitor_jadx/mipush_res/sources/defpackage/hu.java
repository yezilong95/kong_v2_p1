package defpackage;

import java.io.Serializable;
import kotlin.enums.EnumEntriesSerializationProxy;

/* JADX INFO: loaded from: classes.dex */
public final class hu extends v0 implements gu, Serializable {
    public final Enum[] E;

    public hu(Enum[] enumArr) {
        this.E = enumArr;
    }

    private final Object writeReplace() {
        return new EnumEntriesSerializationProxy(this.E);
    }

    @Override // defpackage.v0
    public final int a() {
        return this.E.length;
    }

    @Override // defpackage.v0, java.util.List, java.util.Collection
    public final boolean contains(Object obj) {
        if (!(obj instanceof Enum)) {
            return false;
        }
        Enum r4 = (Enum) obj;
        t40.n(r4, "element");
        Enum[] enumArr = this.E;
        int iOrdinal = r4.ordinal();
        return ((iOrdinal < 0 || iOrdinal >= enumArr.length) ? null : enumArr[iOrdinal]) == r4;
    }

    @Override // java.util.List
    public final Object get(int i) {
        Enum[] enumArr = this.E;
        int length = enumArr.length;
        if (i < 0 || i >= length) {
            throw new IndexOutOfBoundsException(zn.l("index: ", i, length, ", size: "));
        }
        return enumArr[i];
    }

    @Override // defpackage.v0, java.util.List
    public final int indexOf(Object obj) {
        if (!(obj instanceof Enum)) {
            return -1;
        }
        Enum r4 = (Enum) obj;
        t40.n(r4, "element");
        int iOrdinal = r4.ordinal();
        Enum[] enumArr = this.E;
        if (((iOrdinal < 0 || iOrdinal >= enumArr.length) ? null : enumArr[iOrdinal]) == r4) {
            return iOrdinal;
        }
        return -1;
    }

    @Override // defpackage.v0, java.util.List
    public final int lastIndexOf(Object obj) {
        if (!(obj instanceof Enum)) {
            return -1;
        }
        Enum r2 = (Enum) obj;
        t40.n(r2, "element");
        return indexOf(r2);
    }
}
