package kotlin.enums;

import defpackage.ko;
import defpackage.oy;
import java.io.Serializable;
import java.lang.Enum;

/* JADX INFO: loaded from: classes.dex */
public final class EnumEntriesSerializationProxy<E extends Enum<E>> implements Serializable {
    private static final long serialVersionUID = 0;
    public final Class b;

    public EnumEntriesSerializationProxy(E[] eArr) {
        oy.j(eArr, "entries");
        Class<?> componentType = eArr.getClass().getComponentType();
        oy.g(componentType);
        this.b = componentType;
    }

    private final Object readResolve() {
        Object[] enumConstants = this.b.getEnumConstants();
        oy.i(enumConstants, "getEnumConstants(...)");
        return new ko((Enum[]) enumConstants);
    }
}
