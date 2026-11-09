package kotlin.enums;

import defpackage.hu;
import defpackage.t40;
import java.io.Serializable;
import java.lang.Enum;

/* JADX INFO: loaded from: classes.dex */
public final class EnumEntriesSerializationProxy<E extends Enum<E>> implements Serializable {
    private static final long serialVersionUID = 0;
    public final Class E;

    public EnumEntriesSerializationProxy(E[] eArr) {
        t40.n(eArr, "entries");
        Class<?> componentType = eArr.getClass().getComponentType();
        t40.k(componentType);
        this.E = componentType;
    }

    private final Object readResolve() {
        Object[] enumConstants = this.E.getEnumConstants();
        t40.m(enumConstants, "getEnumConstants(...)");
        return new hu((Enum[]) enumConstants);
    }
}
